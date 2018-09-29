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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dto.FunctionalNeedReportDTO;
import org.bf.disability.form.FunctionalNeedReportForm;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.dao.FunctionalNeedReportDAO;
import org.bf.disability.dao.TerritoryDAO;

/**
 *
 * @author 490058
 */
public class EmpReportAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = "success";
        ArrayList empWiseDetails = new ArrayList();
        //if (request.getParameter("status") != null || request.getParameter("empStatus") != null) {
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String emp = null;
        DataSource ds = null;
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        ArrayList habitationlist = new ArrayList();
        FunctionalNeedReportDTO functionalNeedReportDTO = new FunctionalNeedReportDTO();
        FunctionalNeedReportService functionalNeedService =
                FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();

        ArrayList empWiseSingleDetails = new ArrayList();
        ArrayList areaDetails = new ArrayList();
        ArrayList mandallist = new ArrayList();
        ArrayList villagelist = new ArrayList();
        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            // Get District list and mandal List

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

            functionalNeedForm.setFromdate(request.getParameter("fromdate"));
            functionalNeedForm.setTodate(request.getParameter("todate"));
            functionalNeedForm.setUrban_id(request.getParameter("urban_id"));

            TerritoryDAO territoryDAO = new TerritoryDAO();
            ArrayList districtsList = territoryDAO.getDistricts(ds);
            functionalNeedForm.setDistrictList(districtsList);

            if (request.getParameter("district_id") != null && !request.getParameter("district_id").equals("")) {
                mandallist = functionalNeedService.getMandals(ds, functionalNeedForm.getDistrict_id(), functionalNeedForm.getUrban_id());
                functionalNeedForm.setMandallist(mandallist);
            }
            if (request.getParameter("mandal_id") != null && !request.getParameter("mandal_id").equals("")) {
                habitationlist = functionalNeedService.getVillageAll(ds, request.getParameter("district_id"), request.getParameter("mandal_id"));
                functionalNeedForm.setVillagelist(habitationlist);
            }
            try {
                BeanUtils.copyProperties(functionalNeedReportDTO, functionalNeedForm);
            } catch (Exception e) {
                e.printStackTrace();
            }


            if (request.getParameter("mode") == null && request.getParameter("empStatus") == null && request.getParameter("status") == null) {
                districtId = "0";
                Date date1 = new Date();
                SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                String currentDates = dateFormat1.format(date1);
                functionalNeedForm.setUrban_id("0");
                functionalNeedForm.setFromdate("01/01/2010");
                functionalNeedForm.setTodate(currentDates);
              
                BeanUtils.copyProperties(functionalNeedReportDTO, functionalNeedForm);
                empWiseDetails = functionalNeedService.getEmpWiseDetails(ds, districtId, mandalId, villageId, functionalNeedReportDTO);
                areaDetails = functionalNeedService.getAreaDetails(ds, districtId, mandalId, villageId, "0");
                if (empWiseDetails.size() > 0) {
                    request.setAttribute("empWiseDetails", empWiseDetails);
                    request.setAttribute("areaDetails", areaDetails);
                    target = "success";
                } else {
                    request.setAttribute("msg", "Details are Not Available!");
                    target = "failure";
                }
                if (request.getParameter("returnType") != null && request.getParameter("returnType").equals("Excel")) {
                    target = "excel";
                } else if (request.getParameter("returnType") != null && request.getParameter("returnType").equals("Print")) {
                    target = "print";
                } else {
                    target = "success";
                }

                request.setAttribute("ExcelHeader", "District");
                request.setAttribute("district", "district");
                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                String message = "As On: " + functionalNeedForm.getFromdate() + " To: " + functionalNeedForm.getTodate() + ",AreaType:ALL,  ";
                name = message + name;
                request.setAttribute("names", name);
            } else if ("getDetails".equalsIgnoreCase(request.getParameter("status"))) {
                BeanUtils.copyProperties(functionalNeedReportDTO, functionalNeedForm);
                empWiseDetails = functionalNeedService.getEmpWiseDetails(ds, districtId, mandalId, villageId, functionalNeedReportDTO);

             request.setAttribute("empWiseDetails", empWiseDetails);


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
                if (request.getParameter("returnType") != null && request.getParameter("returnType").equals("Excel")) {
                    target = "excel";
                } else if (request.getParameter("returnType") != null && request.getParameter("returnType").equals("Print")) {
                    target = "print";
                } else {
                    target = "success";
                }
                if (functionalNeedForm.getUrban_id().equals("0")) {
                    functionalNeedForm.setUrban_id("ALL");
                }
                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                String message = "As On: " + functionalNeedForm.getFromdate() + " To: " + functionalNeedForm.getTodate() + ",AreaType:" + functionalNeedForm.getUrban_id() + ", ";
                name = message + name;
                request.setAttribute("names", name);
            } else if ("getempWiseReport".equalsIgnoreCase(request.getParameter("status"))) {

                String dist = null;
                String mandal = null;
                String village = null;
                //   String emp = null;
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
                if (request.getParameter("emp") == null) {
                    emp = "0";
                } else {
                    emp = request.getParameter("emp");
                }
                if (emp.equals("0")) {
                    empWiseDetails = functionalNeedService.getEmpWiseDetails(ds, dist, mandal, village, functionalNeedReportDTO);
                    if (empWiseDetails.size() > 0) {
                        request.setAttribute("empWiseDetails", empWiseDetails);
                    }
                    target = "excel";
                } //else {
                //empWiseSingleDetails = functionalNeedService.getempWiseSingleDetails(ds, districtId, mandalId, villageId, emp);
                // request.setAttribute("empWiseSingleDetails", empWiseSingleDetails);
                //   target = "personalempWiseSingle";
                //  }
                //  areaDetails = functionalNeedService.getAreaDetails(ds, dist, mandal, village, emp);
                //    request.setAttribute("areaDetails", areaDetails);
            } else if ("getempWiseReportPrint".equalsIgnoreCase(request.getParameter("status"))) {

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
                if (request.getParameter("emp") == null) {
                    emp = "0";
                } else {
                    emp = request.getParameter("emp");
                }
                areaDetails = functionalNeedService.getAreaDetails(ds, dist, mandal, village, emp);
                request.setAttribute("areaDetails", areaDetails);
                if (emp.equals("0")) {
                    empWiseDetails = functionalNeedService.getEmpWiseDetails(ds, dist, mandal, village, functionalNeedReportDTO);
                    if (empWiseDetails.size() > 0) {
                        request.setAttribute("empWiseDetails", empWiseDetails);
                    }
                    target = "print";
                }// else {
                //  empWiseSingleDetails = functionalNeedService.getempWiseSingleDetails(ds, districtId, mandalId, villageId, emp);
                //  request.setAttribute("empWiseSingleDetails", empWiseSingleDetails);
                //  target = "empWiseDetailsPrint";
                //   }
            } else if ("getempDetails".equalsIgnoreCase(request.getParameter("empStatus"))) {

                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                String habId = null;
                String urbanId = "";
                String fromdate=null;
                 String todate=null;

                 if(request.getParameter("fDate")!=null){
                     fromdate=request.getParameter("fDate");

                 }
                 
                  if(request.getParameter("toDate")!=null){
                     todate=request.getParameter("toDate");

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

                if (request.getParameter("habId") != null || request.getParameter("habId") != "") {
                    habId = request.getParameter("habId");
                } else {
                    habId = "0";
                }

                if (request.getParameter("emp") == null) {
                    emp = "0";
                } else {
                    emp = request.getParameter("emp");
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
                empWiseDetails = functionalNeedService.getPersonalempWiseDetails(ds, dist, mandal, village, habId, emp, urbanId,fromdate,todate);
                request.setAttribute("empWiseDetails", empWiseDetails);
                areaDetails = functionalNeedService.getAreaDetails(ds, dist, mandal, village, habId);
                request.setAttribute("areaDetails", areaDetails);

                //  if (refId.equals("excel")) {
                //  }
                if (refId.equals("single")) {
                    target = "personalSingle";
                } else {
                    target = "personal";
                }
            } else if ("getempDetailsAction".equalsIgnoreCase(request.getParameter("empStatus"))) {

                String dist = null;
                String mandal = null;
                String village = null;
                String habCode = null;
                String refId = null;
                String urbanId = "";
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

                if (request.getParameter("habId") != null) {
                    habCode = request.getParameter("habId");
                } else {
                    habCode = "0";
                }

                if (request.getParameter("emp") == null) {
                    emp = "0";
                } else {
                    emp = request.getParameter("emp");
                }
                if (request.getParameter("refID") == null) {
                    refId = "0";
                } else {
                    refId = request.getParameter("refID");
                }
                if (request.getParameter("urbanId") == null) {
                    urbanId = "0";
                } else {
                    urbanId = request.getParameter("urbanId");
                }
                String fromdate=null;
                 String todate=null;

                 if(request.getParameter("fDate")!=null){
                     fromdate=request.getParameter("fDate");

                 }

                  if(request.getParameter("toDate")!=null){
                     todate=request.getParameter("toDate");

                 }
                empWiseDetails = functionalNeedService.getPersonalempWiseDetails(ds, dist, mandal, village, habCode, emp, urbanId,fromdate,todate);

                 request.setAttribute("empWiseDetails", empWiseDetails);

                areaDetails = functionalNeedService.getAreaDetails(ds, dist, mandal, village, habCode);
                request.setAttribute("areaDetails", areaDetails);
                target = "EmpWisePersonalDetailsExcel";
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "execute", "EmpReportAction", "DataBase");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EmpReportAction", "execute");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "execute", "EmpReportAction", "Code");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EmpReportAction", "execute");
        }//end of catch block



        //}
        request.setAttribute("fromdate", functionalNeedForm.getFromdate());
        request.setAttribute("todate", functionalNeedForm.getTodate());
        return mapping.findForward(target);
    }
}
