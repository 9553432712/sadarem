/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.util.ArrayList;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;
import org.bf.disability.dto.FunctionalNeedReportDTO;
import org.bf.disability.form.FunctionalNeedReportForm;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.FunctionalNeedReportDAO;
import org.bf.disability.dao.TerritoryDAO;

/**
 *
 * @author 484898
 */
public class AgeWiseReportAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        String target = "success";
        DataSource ds = null;
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        ArrayList ageList = new ArrayList();
        ArrayList areaDetails = new ArrayList();
        ArrayList ageWiseDetails = new ArrayList();
        ArrayList habitationlist = new ArrayList();
        HttpSession session = request.getSession();
        FunctionalNeedReportDTO functionalNeedReportDTO = new FunctionalNeedReportDTO();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();

            ArrayList mandallist = new ArrayList();
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
            try {
                BeanUtils.copyProperties(functionalNeedReportDTO, functionalNeedForm);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String district = null;
            String mandal = null;
            String village = null;
            if (request.getParameter("district_id") == null) {
                district = "0";
            } else {
                district = request.getParameter("district_id");
            }
            if (request.getParameter("mandal_id") == null) {
                mandal = "0";
            } else {
                mandal = request.getParameter("mandal_id");
            }
            if (request.getParameter("village_id") == null) {
                village = "0";
            } else {
                village = request.getParameter("village_id");
            }
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String currentDate = dateFormat.format(date);
            if (request.getParameter("mode") == null && request.getParameter("status") == null) {
                functionalNeedForm.setDistrict_id("0");
                functionalNeedForm.setFromAge("0");
                functionalNeedForm.setToAge("10");
                functionalNeedForm.setFromdate("01/01/2010");
                functionalNeedForm.setTodate(currentDate);
                functionalNeedForm.setUrban_id("0");
                BeanUtils.copyProperties(functionalNeedReportDTO, functionalNeedForm);
                ageList = functionalNeedService.getAgeDetails(ds, district, mandal, village, functionalNeedForm.getFromAge().toString(), functionalNeedForm.getToAge().toString(), functionalNeedReportDTO);
                if (ageList.size() > 0) {
                    request.setAttribute("ageList", ageList);
                    session.setAttribute("ageListExcels", ageList);
                } else {
                    request.setAttribute("mag", "Details are Not Available!");
                }
                request.setAttribute("ExcelHeader", "District");
                request.setAttribute("district", "district");
                areaDetails = functionalNeedService.getAreaDetails(ds, district, mandal, village, "0");
                if (areaDetails.size() > 0) {
                    session.setAttribute("areaDetails", areaDetails);
                }
                String name = dao.getDistMandVilHabname(ds, district, mandal, village, "0");
                String message = "As On: " + functionalNeedForm.getFromdate() + " To: " + functionalNeedForm.getTodate() + ",AreaType:ALL,  ";
                name = message + name;
                request.setAttribute("names", name);
            } else if ("getDetails".equalsIgnoreCase(request.getParameter("mode"))) {
                String name = dao.getDistMandVilHabname(ds, district, mandal, village, "0");
                request.setAttribute("names", name);
                if (request.getParameter("fromage") != null) {
                    functionalNeedForm.setFromAge(request.getParameter("fromage"));
                }
                if (request.getParameter("toage") != null) {
                    functionalNeedForm.setToAge(request.getParameter("toage"));
                }
                if (request.getParameter("urban_id") != null) {
                    functionalNeedForm.setUrban_id(request.getParameter("urban_id"));
                }

                BeanUtils.copyProperties(functionalNeedReportDTO, functionalNeedForm);
                ageList = functionalNeedService.getAgeDetails(ds, district, mandal, village, functionalNeedForm.getFromAge().toString(), functionalNeedForm.getToAge().toString(), functionalNeedReportDTO);
                if (district.equals("0") && mandal.equals("0") && village.equals("0")) {
                    request.setAttribute("district", "district");
                    request.setAttribute("ExcelHeader", "District");
                } else if (!district.equals("0") && mandal.equals("0") && village.equals("0")) {
                    request.setAttribute("mandal", "mandal");
                    request.setAttribute("ExcelHeader", "Mandal");
                } else if (!district.equals("0") && !mandal.equals("0") && village.equals("0")) {
                    request.setAttribute("village", "village");
                    request.setAttribute("ExcelHeader", "Village");
                } else if (!district.equals("0") && !mandal.equals("0") && !village.equals("0")) {
                    request.setAttribute("habitation", "habitation");
                    request.setAttribute("ExcelHeader", "Habitation");
                }
                if (functionalNeedForm.getUrban_id().equals("0")) {
                    functionalNeedForm.setUrban_id("ALL");
                }
                String message = "As On: " + functionalNeedForm.getFromdate() + " To: " + functionalNeedForm.getTodate() + ",AreaType:" + functionalNeedForm.getUrban_id() + ", ";
                name = message + name;
                request.setAttribute("names", name);
                if (ageList.size() > 0) {
                    request.setAttribute("ageList", ageList);
                    session.setAttribute("ageListExcels", ageList);
                } else {
                    request.setAttribute("mag", "Details are Not Available!");
                }
                if (request.getParameter("returnType") != null && request.getParameter("returnType").equals("Excel")) {
                    target = "excel";
                } else if (request.getParameter("returnType") != null && request.getParameter("returnType").equals("Print")) {
                    target = "print";
                } else {
                    target = "success";
                }

                areaDetails = functionalNeedService.getAreaDetails(ds, district, mandal, village, "0");
                if (areaDetails.size() > 0) {
                    session.setAttribute("areaDetails", areaDetails);
                }

            } else if ("getAgeWiseReportExcel".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String fromAge = null;
                String toAge = null;
                String habID = "";

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

                if (request.getParameter("fromAge") == null) {
                    fromAge = "0";
                } else {
                    fromAge = request.getParameter("fromAge");
                }
                if (request.getParameter("toAge") == null) {
                    toAge = "0";
                } else {
                    toAge = request.getParameter("toAge");
                }
                if (request.getParameter("habID") == null) {
                    habID = "0";
                } else {
                    habID = request.getParameter("habID");
                }
                ageWiseDetails = functionalNeedService.getAgeDetails(ds, dist, mandal, village, fromAge, toAge, functionalNeedReportDTO);
                request.setAttribute("ageWiseDetails", ageWiseDetails);
                areaDetails = functionalNeedService.getAreaDetails(ds, dist, mandal, village, habID);
                if (areaDetails.size() > 0) {
                    request.setAttribute("areaDetails", areaDetails);
                }
                target = "excel";
            } else if ("getAgeWiseReportPrint".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String fromAge = null;
                String toAge = null;
                String habID = "";


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

                if (request.getParameter("fromAge") == null) {
                    fromAge = "0";
                } else {
                    fromAge = request.getParameter("fromAge");
                }
                if (request.getParameter("toAge") == null) {
                    toAge = "0";
                } else {
                    toAge = request.getParameter("toAge");
                }
                if (request.getParameter("habID") == null) {
                    habID = "0";
                } else {
                    habID = request.getParameter("habID");
                }
                ageWiseDetails = functionalNeedService.getAgeDetails(ds, dist, mandal, village, fromAge, toAge, functionalNeedReportDTO);
                request.setAttribute("ageWiseDetails", ageWiseDetails);
                areaDetails = functionalNeedService.getAreaDetails(ds, dist, mandal, village, "0");
                if (areaDetails.size() > 0) {
                    request.setAttribute("areaDetails", areaDetails);
                }

                target = "print";
            } else if ("getPersonalAgeDetails".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String refId = null;
                String gender = null;
                String fromAge = null;
                String toAge = null;
                String habId = null;
                String urbanId = null;
                String habID = "";
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }

                if (request.getParameter("mID").equals("")) {
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
                    if (request.getParameter("habID") == null || request.getParameter("habID") == "") {
                        habId = "0";
                    } else {
                        habId = request.getParameter("habID");
                    }
                } else {
                    habId = "0";
                }

                if (request.getParameter("gender") == null) {
                    gender = "0";
                } else {
                    gender = request.getParameter("gender");
                }

                if (request.getParameter("fromAge") == null) {
                    fromAge = "0";
                } else {
                    fromAge = request.getParameter("fromAge");
                }

                if (request.getParameter("toAge") == null) {
                    toAge = "0";
                } else {
                    toAge = request.getParameter("toAge");
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
                if (request.getParameter("habID") == null) {
                    habID = "0";
                } else {
                    habID = request.getParameter("habID");
                }
                String fromdate = null;
                String todate = null;

                if (request.getParameter("fDate") != null) {
                    fromdate = request.getParameter("fDate");
                  
                }

                if (request.getParameter("toDate") != null) {
                    todate = request.getParameter("toDate");
                    
                }
                ageWiseDetails = functionalNeedService.getPersonalAgeWiseDetails(ds, dist, mandal, village, habId, gender, fromAge, toAge, urbanId, fromdate, todate);
                request.setAttribute("ageWiseDetails", ageWiseDetails);

                // session.setAttribute("ageWiseDetailsList",ageWiseDetails);
                areaDetails = functionalNeedService.getAreaDetails(ds, dist, mandal, village, "0");
                request.setAttribute("areaDetails", areaDetails);
                if (refId.equals("excel")) {
                    request.setAttribute("ageWiseDetails", ageWiseDetails);
                    target = "personalCasteDetailsExcel";
                } else {
                    target = "personalDetails";
                }


            } else if ("ageWiseReportExcelExport".equalsIgnoreCase(request.getParameter("modes"))) {
                target = "ageWiseReportExcelExport";
            } else if ("ageWiseReportPrintExport".equalsIgnoreCase(request.getParameter("modes"))) {
                target = "ageWiseReportPrintExport";
            }
            request.setAttribute("fromdate",functionalNeedForm.getFromdate());
            request.setAttribute("todate",functionalNeedForm.getTodate());
        } catch (Exception e) {
            e.printStackTrace();

        }

        return mapping.findForward(target);
    }
}
