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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;
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
public class AppealAuthorityReportAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = "success";
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String emp = null;
        DataSource ds = null;

        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        HttpSession session = request.getSession();
        ArrayList habitationlist = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String fdate = (String) request.getParameter("fromdate");
            String tdate = (String) request.getParameter("todate");
            functionalNeedForm.setFromdate(fdate);
            functionalNeedForm.setTodate(tdate);


            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();

            ArrayList districtList = new ArrayList();
            ArrayList empWiseDetails = new ArrayList();
            ArrayList empWiseSingleDetails = new ArrayList();
            ArrayList areaDetails = new ArrayList();
            ArrayList mandallist = new ArrayList();
            ArrayList villagelist = new ArrayList();
            //getDistMandVilHabname

            // Get District list

            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }

            // Get District list and mandal List

            if (request.getParameter("district_id") != null && !request.getParameter("district_id").equals("")) {
                mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"), functionalNeedForm.getUrban_id());
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

            if (request.getParameter("mode") == null && request.getParameter("empStatus") == null && request.getParameter("status") == null) {
                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                request.setAttribute("names", name);
                empWiseDetails = dao.getApealAuthorityCount(ds, districtId, mandalId, villageId, "27/08/2011", currentDate);
                if (empWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");
                } else {
                    request.setAttribute("empWiseDetails", empWiseDetails);
                    session.setAttribute("empWiseDetails1", empWiseDetails);
                }
                 functionalNeedForm.setFromdate("27/08/2011");
                 functionalNeedForm.setTodate(currentDate);
                session.setAttribute("ExcelHeader", "District");
                String message = " As On: " + "27/08/2011" + " To: " + currentDate + ", District:ALL, Mandal:ALL, Village:ALL";
                request.setAttribute("names", message);
                session.setAttribute("names1", message);
                request.setAttribute("district", "district");
            } else if ("getDetails".equalsIgnoreCase(request.getParameter("mode"))) {
                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                request.setAttribute("names", name);
                empWiseDetails = dao.getApealAuthorityCount(ds, districtId, mandalId, villageId, fdate, tdate);
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
                    session.setAttribute("empWiseDetails1", empWiseDetails);
                }
                functionalNeedForm.setFromdate(functionalNeedForm.getFromdate());
                request.setAttribute("backButton", "backButton");
                session.setAttribute("ExcelHeaders", request.getAttribute("ExcelHeader"));
                String message = "As On: " + functionalNeedForm.getFromdate() + " To: " + functionalNeedForm.getTodate() + ", ";
                name = message + name;
                request.setAttribute("names", name);
                session.setAttribute("names1", name);
            } else if ("getempWiseReport".equalsIgnoreCase(request.getParameter("status"))) {
                request.setAttribute("names", session.getAttribute("names1"));
                request.setAttribute("empWiseDetails", session.getAttribute("empWiseDetails1"));
                request.setAttribute("ExcelHeader", session.getAttribute("ExcelHeaders"));
                target = "excel";
            } else if ("getempWiseReportPrint".equalsIgnoreCase(request.getParameter("status"))) {
                request.setAttribute("names", session.getAttribute("names1"));
                request.setAttribute("empWiseDetails", session.getAttribute("empWiseDetails1"));
                request.setAttribute("ExcelHeader", session.getAttribute("ExcelHeaders"));
                target = "print";
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
        } //end of try block
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();




            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "execute", "AppealAuthorityReportAction", "DataBase");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppealAuthorityReportAction", "execute");




        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();




            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "execute", "AppealAuthorityReportAction", "Code");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppealAuthorityReportAction", "execute");




        }
        request.setAttribute("fromdate", functionalNeedForm.getFromdate());
        request.setAttribute("todate", functionalNeedForm.getTodate());
        return mapping.findForward(target);


    }
}
