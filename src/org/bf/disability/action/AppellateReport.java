/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.form.FunctionalNeedReportForm;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;
import org.bf.disability.dao.FunctionalNeedReportDAO;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

/**
 *
 * @author 490058
 */
public class AppellateReport extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = "success";
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String emp = null;
        DataSource ds = null;

        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        ArrayList habitationlist = new ArrayList();
        HttpSession session = request.getSession();
        ArrayList empWiseDetails = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String fdate = (String) request.getParameter("fromdate");
            String tdate = (String) request.getParameter("todate");
            String pensionphase = (String) request.getParameter("pensionPhase");
            functionalNeedForm.setFromdate(fdate);
            functionalNeedForm.setTodate(tdate);
            //functionalNeedForm.setPensionPhase(pensionphase);

            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();

            ArrayList empWiseSingleDetails = new ArrayList();
            ArrayList areaDetails = new ArrayList();
            ArrayList mandallist = new ArrayList();
            ArrayList villagelist = new ArrayList();
            ArrayList districtList = new ArrayList();
            //getDistMandVilHabname

            // Get District list 


            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }
            
            // Get mandal List
            if (request.getParameter("district_id") != null && !request.getParameter("district_id").equals("")) {
                mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"), "");
                functionalNeedForm.setMandallist(mandallist);
            }
            if (request.getParameter("mandal_id") != null && !request.getParameter("mandal_id").equals("")) {
                habitationlist = functionalNeedService.getVillageAll(ds, request.getParameter("district_id"), request.getParameter("mandal_id"));
                functionalNeedForm.setVillagelist(habitationlist);
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
            if (request.getParameter("emp") == null) {
                emp = "0";
            } else {
                emp = request.getParameter("emp");
            }
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String currentDate = dateFormat.format(date);
            if (request.getParameter("mode") == null && request.getParameter("status") == null) {
                functionalNeedForm.setPensionPhase("1");
                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                request.setAttribute("names", name);
                empWiseDetails = dao.getAppellateCount(ds, districtId, mandalId, villageId, "27/08/2011", currentDate, functionalNeedForm.getPensionPhase());
                if (empWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");
                } else {
                    request.setAttribute("empWiseDetails", empWiseDetails);
                }
                request.setAttribute("district", "district");
                session.setAttribute("ExcelHeader", "District");
                String phaseId = functionalNeedForm.getPensionPhase();
                if (phaseId.equals("1")) {
                    phaseId = "PhaseI";
                } else if (phaseId.equals("2")) {
                    phaseId = "PhaseII";
                } else if (phaseId.equals("4")) {
                    phaseId = "PhaseIV";
                } else {
                    phaseId = "ALL";
                }
                String message = " As On: " + "27/08/2011" + " To: " + currentDate + ", Pension Phase:" + phaseId + ", District:ALL, Mandal:ALL, Village:ALL";
                request.setAttribute("names", message);
                session.setAttribute("names1", message);
                request.setAttribute("district", "district");
            } else if ("getDetails".equalsIgnoreCase(request.getParameter("mode"))) {
                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                request.setAttribute("names", name);
                empWiseDetails = dao.getAppellateCount(ds, districtId, mandalId, villageId, fdate, tdate, pensionphase);
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
                if (empWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");
                } else {
                    request.setAttribute("empWiseDetails", empWiseDetails);
                }
                session.setAttribute("ExcelHeader", "District");
                 String phaseId = functionalNeedForm.getPensionPhase();
                if (phaseId.equals("1")) {
                    phaseId = "PhaseI";
                } else if (phaseId.equals("2")) {
                    phaseId = "PhaseII";
                } else if (phaseId.equals("4")) {
                    phaseId = "PhaseIV";
                } else {
                    phaseId = "ALL";
                }
                String message = " As On: " + "27/08/2011" + " To: " + currentDate + ", Pension Phase:" + phaseId + ", "+name;
                request.setAttribute("names", message);
                session.setAttribute("names1", message);
                if(request.getParameter("returnType")!=null && request.getParameter("returnType").equals("Excel")){
                     target="excel";
                }else if(request.getParameter("returnType")!=null && request.getParameter("returnType").equals("Print")){
                     target="print";
                }else{
                    target="success";
                }
            }else if (request.getParameter("mode")!=null && request.getParameter("mode").length()==0) {
                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                request.setAttribute("names", name);
                empWiseDetails = dao.getAppellateCount(ds, districtId, mandalId, villageId, fdate, tdate, pensionphase);
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
                if (empWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");
                } else {
                    request.setAttribute("empWiseDetails", empWiseDetails);
                }
                session.setAttribute("ExcelHeader", "District");
                 String phaseId = functionalNeedForm.getPensionPhase();
                if (phaseId.equals("1")) {
                    phaseId = "PhaseI";
                } else if (phaseId.equals("2")) {
                    phaseId = "PhaseII";
                } else if (phaseId.equals("4")) {
                    phaseId = "PhaseIV";
                } else {
                    phaseId = "ALL";
                }
                String message = " As On: " + "27/08/2011" + " To: " + currentDate + ", Pension Phase:" + phaseId + ", "+name;
                request.setAttribute("names", message);
                session.setAttribute("names1", message);
                if(request.getParameter("returnType")!=null && request.getParameter("returnType").equals("Excel")){
                     target="excel";
                }else if(request.getParameter("returnType")!=null && request.getParameter("returnType").equals("Print")){
                     target="print";
                }else{
                    target="success";
                }
            } else if ("getempWiseReport".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;

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
//            if (!mandal.equals("0") && !dist.equals("0")) {
//                if (request.getParameter("villagesId") == null) {
//                    village = "0";
//                } else {
//                    village = request.getParameter("villagesId");
//                }
//            } else {
//                village = "0";
//            }
                village = "0";
                if (request.getParameter("emp") == null) {
                    emp = "0";
                } else {
                    emp = request.getParameter("emp");
                }
                if (emp.equals("0")) {
                    // empWiseDetails = functionalNeedService.getEmpWiseDetails(ds, dist, mandal, village);
                    String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                    request.setAttribute("names", name);
                    empWiseDetails = dao.getAppellateCount(ds, dist, mandal, village, fdate, tdate, pensionphase);
                    request.setAttribute("empWiseDetails", empWiseDetails);
                    target = "excel";
                }
            } else if ("getempWiseReportPrint".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String cast = null;
                String hab = null;
                if (request.getParameter("hid") == null) {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }

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
                    // empWiseDetails = functionalNeedService.getEmpWiseDetails(ds, dist, mandal, village);
                    String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                    request.setAttribute("names", name);

                    empWiseDetails = dao.getAppellateCount(ds, dist, mandal, village, fdate, tdate, pensionphase);
                    request.setAttribute("empWiseDetails", empWiseDetails);
                    target = "print";
                }
            } else if ("getempDetails".equalsIgnoreCase(request.getParameter("empStatus"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
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
                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }


                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);

                empWiseDetails = dao.getPersonalDisWiseDetails(ds, dist, mandal, village, emp, hab, fdate, tdate);
                request.setAttribute("empWiseDetails", empWiseDetails);


                if (refId.equals("single")) {
                    target = "personalSingle";
                } else {
                    target = "personal";
                }
            } else if ("getempDetailsAction".equalsIgnoreCase(request.getParameter("empStatus"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
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
                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }
                // empWiseDetails = functionalNeedService.getPersonalempWiseDetails(ds, dist, mandal, village, emp);

                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                empWiseDetails = dao.getPersonalDisWiseDetails(ds, dist, mandal, village, emp, hab, fdate, tdate);
                request.setAttribute("empWiseDetails", empWiseDetails);

                target = "EmpWisePersonalDetailsExcel";
            }

            /** GET PERSONAL DETAILS FROM APPLEA REPORT */
            ArrayList<Object> appealPersonalDetails = new ArrayList<Object>();
            ArrayList<Object> areaDetailsExcel = new ArrayList<Object>();
            if ("getpersonalDetails".equalsIgnoreCase(request.getParameter("status"))) {
                appealPersonalDetails = functionalNeedService.getAppealPersonalDetails(ds, (String) request.getParameter("districtCode"), (String) request.getParameter("mandalCode"), (String) request.getParameter("villageCode"), (String) request.getParameter("fromDate"), (String) request.getParameter("toDate"), (String) request.getParameter("disabilityStatus"), (String) request.getParameter("disability"), (String) request.getParameter("baseDistrict"), (String) request.getParameter("baseMandal"), (String) request.getParameter("baseVillage"), (String) request.getParameter("basehab"), (String) request.getParameter("pensionPhase"));
                areaDetailsExcel = functionalNeedService.getAreaDetails(ds, (String) request.getParameter("districtCode"), (String) request.getParameter("mandalCode"), (String) request.getParameter("villageCode"), "0");
                if (appealPersonalDetails.size() > 0) {
                    request.setAttribute("appealPersonlReportDetails", appealPersonalDetails);
                    session.setAttribute("appealPersonlReportDetailsExcel", appealPersonalDetails);
                    session.setAttribute("areadetailsExcel", areaDetailsExcel);
                    request.setAttribute("name", session.getAttribute("name1"));
                } else {
                    request.setAttribute("msg", "No Data Found");
                }
                target = "AppealPersonalDetailsReport";
            } else if ("getExcel".equalsIgnoreCase(request.getParameter("status"))) {

                target = "appealReportExcel";
            }

        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "execute", "AppellateReport", "DataBase");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppellateReport", "execute");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "execute", "AppellateReport", "Code");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppellateReport", "execute");
        }
        /** END **/
        return mapping.findForward(target);
    }
}
