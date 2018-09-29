/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.FunctionalNeedReportDAO;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.dto.FunctionalNeedReportDTO;
import org.bf.disability.form.FunctionalNeedReportForm;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;

/**
 *
 * @author 484898
 */
public class CasteWiseReportAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = "success";

        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String caste = null;
        DataSource ds = null;
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        HttpSession session = request.getSession();
        ArrayList habitationlist = new ArrayList();


        FunctionalNeedReportService functionalNeedService =
                FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
        FunctionalNeedReportDTO functionalNeedReportDTO = new FunctionalNeedReportDTO();
        ArrayList casteWiseDetails = null;
        ArrayList casteWiseSingleDetails = new ArrayList();
        ArrayList areaDetails = new ArrayList();

        ArrayList mandallist = new ArrayList();
        ArrayList villagelist = new ArrayList();
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        // Get District list and mandal List
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            TerritoryDAO territoryDAO = new TerritoryDAO();
            ArrayList districtsList = territoryDAO.getDistricts(ds);
            functionalNeedForm.setDistrictList(districtsList);

            if (request.getParameter("district_id") != null && !request.getParameter("district_id").equals("")) {
                mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"), functionalNeedForm.getUrban_id());
                functionalNeedForm.setMandallist(mandallist);
            }

            if (request.getParameter("mandal_id") != null && !request.getParameter("mandal_id").equals("")) {
                habitationlist = functionalNeedService.getVillageAll(ds, request.getParameter("district_id"), request.getParameter("mandal_id"));
                functionalNeedForm.setVillagelist(habitationlist);

            }
            String urbanid = (String) request.getParameter("urban_id");
            if ("hide".equals(request.getParameter("mode"))) {
                session.removeAttribute("casteWiseDetailsExcelDetails");
            }

            if (request.getParameter("district_id") == null) {
                districtId = "0";
            } else {
                districtId = request.getParameter("district_id");
            }
            if (request.getParameter("mandal_id") == null) {
                mandalId = "0";
            } else {
                mandalId = request.getParameter("mandal_id");
            }
            if (request.getParameter("village_id") == null) {
                villageId = "0";
            } else {
                villageId = request.getParameter("village_id");
            }

            if (request.getParameter("caste") == null) {
                caste = "0";
            } else {
                caste = request.getParameter("caste");
            }
            String fdate = (String) request.getParameter("fromdate");
            String tdate = (String) request.getParameter("todate");
            try {
                BeanUtils.copyProperties(functionalNeedReportDTO, functionalNeedForm);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String currentDate = dateFormat.format(date);
            if (request.getParameter("mode") == null && request.getParameter("status") == null && request.getParameter("casteStatus") == null) {
                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                request.setAttribute("names", name);
                functionalNeedForm.setUrban_id("0");
                functionalNeedForm.setDistrict_id("0");
                functionalNeedForm.setFromdate("01/01/2010");
                functionalNeedForm.setTodate(currentDate);
                request.setAttribute("fromdate", "01/01/2010");
                
                request.setAttribute("todate", currentDate);
                BeanUtils.copyProperties(functionalNeedReportDTO, functionalNeedForm);
                casteWiseDetails = functionalNeedService.getCasteWiseDetails(ds, districtId, mandalId, villageId, functionalNeedForm.getUrban_id(), functionalNeedReportDTO);
                if (casteWiseDetails.isEmpty()) {
                    request.setAttribute("msg", "Details are Not Available!");
                    target = "casteWise";
                } else {
                    request.setAttribute("casteWiseDetails", casteWiseDetails);
                    session.setAttribute("casteWiseDetails1", casteWiseDetails);
                    target = "casteWise";
                }
                request.setAttribute("district", "district");
                request.setAttribute("ExcelHeaders", "district");
                session.setAttribute("ExcelHeaders", request.getAttribute("ExcelHeader"));
                String message = "As On: " + functionalNeedForm.getFromdate() + " To: " + functionalNeedForm.getTodate() + ",AreaType:ALL,  ";
                name = message + name;
                request.setAttribute("names", name);
            } else if ("getDetails".equalsIgnoreCase(request.getParameter("mode"))) {
                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                request.setAttribute("names", name);
                casteWiseDetails = new ArrayList();
                request.setAttribute("fromdate", functionalNeedForm.getFromdate());
                request.setAttribute("todate", functionalNeedForm.getTodate());

                casteWiseDetails = functionalNeedService.getCasteWiseDetails(ds, districtId, mandalId, villageId, caste, functionalNeedReportDTO);
                if (casteWiseDetails.size() > 0) {
                    session.setAttribute("casteWiseDetailsExcelDetails", casteWiseDetails);
                    request.setAttribute("casteWiseDetails", casteWiseDetails);
                } else {
                    request.setAttribute("msg", "Details are Not Available!");
                }
                areaDetails = functionalNeedService.getAreaDetails(ds, districtId, mandalId, villageId, caste);
                session.setAttribute("areaDetails", areaDetails);

                if (districtId.equals("0") && mandalId.equals("0") && villageId.equals("0")) {
                    request.setAttribute("district", "district");
                    request.setAttribute("ExcelHeader", "District");
                } else if (!districtId.equals("0") && mandalId.equals("0") && villageId.equals("0")) {
                    request.setAttribute("mandal", "mandal");
                    request.setAttribute("ExcelHeader", "Mandal");
                } else if (!districtId.equals("0") && !mandalId.equals("0") && villageId.equals("0")) {
                    request.setAttribute("village", "village");
                    request.setAttribute("ExcelHeader", "Village");
                } else if (!districtId.equals("0") && !mandalId.equals("0") && !villageId.equals("0")) {
                    request.setAttribute("habitation", "habitation");
                    request.setAttribute("ExcelHeader", "Habitation");
                }
                if (functionalNeedForm.getUrban_id().equals("0")) {
                    functionalNeedForm.setUrban_id("ALL");
                }
                String message = "As On: " + functionalNeedForm.getFromdate() + " To: " + functionalNeedForm.getTodate() + ",AreaType:" + functionalNeedForm.getUrban_id() + ", ";
                name = message + name;
                request.setAttribute("names", name);
                if (request.getParameter("returnType") != null && request.getParameter("returnType").equals("Excel")) {
                    target = "excel";
                } else if (request.getParameter("returnType") != null && request.getParameter("returnType").equals("Print")) {
                    target = "print";
                } else {
                    target = "casteWise";
                }

            } else if ("getCasteWiseReport".equalsIgnoreCase(request.getParameter("status"))) {

                String dist = null;
                String mandal = null;
                String village = null;
                String cast = null;
                String vi = request.getParameter("villagesId");

                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }

                if (request.getParameter("caste") == null) {
                    caste = "0";
                } else {
                    caste = request.getParameter("caste");
                }
                casteWiseDetails = new ArrayList();
                casteWiseDetails = functionalNeedService.getCasteWiseDetails(ds, dist, mandal, village, caste, functionalNeedReportDTO);
                request.setAttribute("casteWiseDetails", casteWiseDetails);
                target = "excel";


                areaDetails = functionalNeedService.getAreaDetails(ds, dist, mandal, village, caste);
                request.setAttribute("areaDetails", areaDetails);

            } else if ("getCasteWiseReportPrint".equalsIgnoreCase(request.getParameter("status"))) {

                String dist = null;
                String mandal = null;
                String village = null;
                String cast = null;

                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }

                if (request.getParameter("caste") == null) {
                    caste = "0";
                } else {
                    caste = request.getParameter("caste");
                }
                casteWiseDetails = new ArrayList();
                areaDetails = functionalNeedService.getAreaDetails(ds, dist, mandal, village, caste);
                request.setAttribute("areaDetails", areaDetails);
                casteWiseDetails = functionalNeedService.getCasteWiseDetails(ds, dist, mandal, village, caste, functionalNeedReportDTO);
                request.setAttribute("casteWiseDetails", casteWiseDetails);

                target = "print";



            } else if ("getcasteDetails".equalsIgnoreCase(request.getParameter("casteStatus"))) {

                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                String habCode = null;
                String urbanId = null;
                String fromDate=null;
                  String toDate=null;

                  if(request.getParameter("fDate")!=null){
                      fromDate=request.getParameter("fDate");

                  }

                  if(request.getParameter("toDate")!=null){
                      toDate=request.getParameter("toDate");


                  }
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }

                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }

                if (!mandal.equals("0") && !dist.equals("0") && !village.equals("0")) {
                    if (request.getParameter("habCode") == null || request.getParameter("habCode") == "") {
                        habCode = "0";
                    } else {
                        habCode = request.getParameter("habCode");
                    }
                } else {
                    habCode = "0";
                }


                if (request.getParameter("caste") == null) {
                    caste = "0";
                } else {
                    caste = request.getParameter("caste");
                }

                if (request.getParameter("refID") == null) {
                    refId = "0";
                } else {
                    refId = request.getParameter("refID");
                }

                if (request.getParameter("getSingle") == null) {
                    refId = "0";
                } else {
                    refId = request.getParameter("getSingle");
                }
                if (request.getParameter("urbanId") == null) {
                    urbanId = "0";
                } else {
                    urbanId = request.getParameter("urbanId");
                }
                casteWiseDetails = new ArrayList();
                FunctionalNeedReportDAO functionalNeedReportDAO=new FunctionalNeedReportDAO();
                casteWiseDetails = functionalNeedReportDAO.getPersonalCasteWiseDetails(ds, dist, mandal, village, habCode, caste, urbanId,fromDate,toDate);
              
                   request.setAttribute("casteWiseDetails", casteWiseDetails); 
                

                areaDetails = functionalNeedService.getAreaDetails(ds, dist, mandal, village, habCode);
                request.setAttribute("areaDetails", areaDetails);

                if (refId.equals("excel")) {
                    target = "personalCasteDetailsExcel";
                }
                if (refId.equals("single")) {
                    target = "personalSingle";
                } else {
                    target = "personal";
                }


            } else if ("getcasteDetailsAction".equalsIgnoreCase(request.getParameter("casteStatus"))) {

                String dist = null;
                String mandal = null;
                String village = null;
                String urbanId = null;
                String habCode = null;

                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }

                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }

                if (!mandal.equals("0") && !dist.equals("0") && !village.equals("0")) {
                    if (request.getParameter("habCode") == null || request.getParameter("habCode") == "") {
                        habCode = "0";
                    } else {
                        habCode = request.getParameter("habCode");
                    }
                } else {
                    habCode = "0";
                }

                if (request.getParameter("caste") == null) {
                    caste = "0";
                } else {
                    caste = request.getParameter("caste");
                }



                if (request.getParameter("urbanId") == null) {
                    urbanId = "0";
                } else {
                    urbanId = request.getParameter("urbanId");
                }

                 String fromDate=null;
                  String toDate=null;

                  if(request.getParameter("fDate")!=null){
                      fromDate=request.getParameter("fDate");

                     
                  }

                  if(request.getParameter("toDate")!=null){
                      toDate=request.getParameter("toDate");


                  }
                casteWiseDetails = new ArrayList();
                casteWiseDetails = functionalNeedService.getPersonalCasteWiseDetails(ds, dist, mandal, village, habCode, caste, urbanId,fromDate,toDate);
                request.setAttribute("casteWiseDetails", casteWiseDetails);
                areaDetails = functionalNeedService.getAreaDetails(ds, dist, mandal, village, caste);
                request.setAttribute("areaDetails", areaDetails);


                target = "CasteWisePersonalDetailsExcel";

            } else if ("getCasteWiseReportExcelExport".equalsIgnoreCase(request.getParameter("modes"))) {
                //  request.setAttribute("casteWiseDetailsExcel", session.getAttribute("casteWiseDetailsExcelDetails"));
                target = "personalDetailsExcelExport";
            } else if ("getCasteWiseReportPrintExport".equalsIgnoreCase(request.getParameter("modes"))) {
                //request.setAttribute("casteWiseDetailsExcel", session.getAttribute("casteWiseDetailsExcelDetails"));
                target = "personalDetailsPrintExport";
            }
        } //end of try block
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return mapping.findForward(target);
    }
}
