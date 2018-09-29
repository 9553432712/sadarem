/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.SQLException;
import java.text.ParseException;
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
import org.bf.disability.dao.FunctionalReportDAO;

/**
 *
 * @author 490058
 */
public class AgeReport extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = "success";
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String emp = null;
        DataSource ds = null;

        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        FunctionalReportDAO funcDao = new FunctionalReportDAO();
        ArrayList habitationlist = new ArrayList();
        HttpSession session = request.getSession();
        String fdate = (String) request.getParameter("fromdate");
        String tdate = (String) request.getParameter("todate");
        functionalNeedForm.setFromdate(fdate);
        functionalNeedForm.setTodate(tdate);
        String fage = (String) request.getParameter("fromAge");
        String tage = (String) request.getParameter("toAge");

        FunctionalNeedReportService functionalNeedService =
                FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
        ArrayList empWiseDetails = new ArrayList();
        ArrayList empWiseSingleDetails = new ArrayList();
        ArrayList areaDetails = new ArrayList();
        ArrayList mandallist = new ArrayList();
        ArrayList villagelist = new ArrayList();
        //getDistMandVilHabname

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String type = (String) request.getParameter("type");
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
            if (fdate == null) {
                fdate = "01/01/2010";
            }
            if (tdate == null) {
                tdate = currentDate;
            }
            if (fage == null) {
                fage = "0";
            }
            if (tage == null) {
                tage = "10";
            }
            if (request.getParameter("mode") == null && request.getParameter("details") == null && request.getParameter("status") == null) {
                functionalNeedForm.setFromAge("0");
                functionalNeedForm.setToAge("10");
                request.setAttribute("district", "district");
                functionalNeedForm.setFromdate("01/01/2010");
                functionalNeedForm.setTodate(currentDate);
                empWiseDetails = funcDao.getAppellateageCount(ds, "0", mandalId, villageId, "01/01/2010", currentDate, functionalNeedForm.getFromAge(), functionalNeedForm.getToAge());
                if (empWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");
                } else {
                    request.setAttribute("empWiseDetails", empWiseDetails);
                    session.setAttribute("empWiseDetails1", empWiseDetails);
                }
                session.setAttribute("ExcelHeader", "District");
                String message = "As On: " + "01/01/2010" + " To: " + currentDate + ", District:ALL, Mandal:ALL, Village:ALL";
                request.setAttribute("names", message);
                session.setAttribute("names1", message);
                target = "Agereports";
            }
            if ("getDetails".equalsIgnoreCase(request.getParameter("mode"))) {
                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                 String message = "As On: " + functionalNeedForm.getFromdate() + " To: " + functionalNeedForm.getTodate() + ", ";
                String names = message + name;
                request.setAttribute("names", names);
                session.setAttribute("names1", names);

                empWiseDetails = funcDao.getAppellateageCount(ds, districtId, mandalId, villageId, fdate, tdate, fage, tage);
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
                request.setAttribute("backButton", "backButton");
                session.setAttribute("ExcelHeaders", request.getAttribute("ExcelHeader"));
                functionalNeedForm.setFromdate(new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fdate)));
                functionalNeedForm.setTodate(new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(tdate)));
                 message = "As On: " + functionalNeedForm.getFromdate() + " To: " + functionalNeedForm.getTodate() + ", ";
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
            } else if ("getDetails".equalsIgnoreCase(request.getParameter("details"))) {
                String dist = null;
                String mandal = null;
                String village = null;
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


                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }

                StringBuffer sb = new StringBuffer();

                if (type != null && type.equalsIgnoreCase("eli")) {
                    sb.append("Eligible Category");
                } else if (type != null && type.equalsIgnoreCase("tar")) {
                    sb.append("Rejected Category");
                } else if (type != null && type.equalsIgnoreCase("tdr")) {
                    sb.append("Direct Rejected Category");
                }
                request.setAttribute("type1", sb.toString());
                request.setAttribute("type", type);
                request.setAttribute("values", "fromAge=" + fage + "&toAge=" + tage);
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                empWiseDetails = funcDao.getPersonalAgeWiseDetails(ds, dist, mandal, village, hab, fdate, tdate, fage, tage, type);
                request.setAttribute("ageDetails", empWiseDetails);
                target = "personal";

            } else if ("getempDetailsAction".equalsIgnoreCase(request.getParameter("empStatus"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                StringBuffer sb = new StringBuffer();

                if (type != null && type.equalsIgnoreCase("eli")) {
                    sb.append("Eligible Category");
                } else if (type != null && type.equalsIgnoreCase("tar")) {
                    sb.append("Rejected Category");
                } else if (type != null && type.equalsIgnoreCase("tdr")) {
                    sb.append("Direct Rejected Category");
                }
                request.setAttribute("type1", sb.toString());

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
                empWiseDetails = funcDao.getPersonalAgeWiseDetails(ds, dist, mandal, village, hab, fdate, tdate, fage, tage, type);
                request.setAttribute("ageDetails", empWiseDetails);

                target = "EmpWisePersonalDetailsExcel";
            }

        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "execute", "AgeReport", "DataBase");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AgeReport", "execute");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "execute", "AgeReport", "Code");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AgeReport", "execute");
        }



        /** END **/
        return mapping.findForward(target);
    }
}
