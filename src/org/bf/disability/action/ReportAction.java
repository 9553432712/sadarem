/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.SQLException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.ReportDAO;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.form.ReportForm;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import java.util.Map;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 484898
 */
public class ReportAction extends DispatchAction {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
      
        getSHGAbstract(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }

    /**
     * This method is for getting the data based on district and mandal selection
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getSHGAbstract(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        HttpSession session = null;
        FunctionalNeedReportService functionalNeedService =
                FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            session = request.getSession(true);
            ReportDAO reportDAO = new ReportDAO();
            ArrayList shgData = new ArrayList();
            ReportForm reportForm = (ReportForm) form;
            TerritoryDAO territoryDAO = new TerritoryDAO();
            ArrayList districtsList = territoryDAO.getDistricts(ds);
            ArrayList mandallist = new ArrayList();
            reportForm.setDistrictlist(districtsList);
            if (reportForm.getDistrict_id() != null && reportForm.getDistrict_id().length() > 0) {

                if (reportForm.getMandal_id() == null || reportForm.getMandal_id().equals("0")) { // If mandalid is not select
                    reportForm.setMandal_id("All");
                }

                shgData = reportDAO.getSHGAbstract(ds, reportForm.getDistrict_id(), reportForm.getMandal_id(), request);
                mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"), "");
                reportForm.setMandallist(mandallist);
                if (shgData != null && shgData.size() > 0) {
                    request.setAttribute("shgAbstract", shgData);
                    session.setAttribute("excelData", shgData);
                    session.setAttribute("totalList", request.getAttribute("totalList"));

                    request.setAttribute("excel", "<a href=\"shgAbstractExcel.xls\">Excel</a>");
                } else {
                    request.setAttribute("msg", "Details are Not Available!");
                }

            }
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSHGAbstract", "ReportAction", "DataBase");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportAction", "getSHGAbstract");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSHGAbstract", "ReportAction", "Code");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportAction", "getSHGAbstract");
        }

        return mapping.findForward(SUCCESS);
    }

    public ActionForward getShgPersonalDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        HttpSession session = null;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            session = request.getSession(true);
            ReportDAO reportDAO = new ReportDAO();
            ArrayList shgPersonalData = new ArrayList();
            Map map = null;
            String flag = null;


            if (request.getParameter("districtId") != null && request.getParameter("mandalId") != null && request.getParameter("flag") != null) {
                shgPersonalData = reportDAO.getShgPersonalDetails(ds, request.getParameter("districtId"), request.getParameter("mandalId"), request.getParameter("flag"));
                if (shgPersonalData != null && shgPersonalData.size() > 0) {
                    session.setAttribute("shgPersonalExcel", shgPersonalData);
                    Iterator it = shgPersonalData.iterator();
                    if (it.hasNext()) {
                        map = (Map) it.next();
                        flag = map.get("flag").toString();

                    }
                    if (flag != null && flag.equals("9")) {
                        request.setAttribute("personalData1", shgPersonalData);
                        request.setAttribute("shgflag", map.get("flag"));
                        request.setAttribute("shgflagData", flag);
                    } else if (flag != null && flag.equals("10")) {
                        request.setAttribute("unmappedFlag", "unmappedFlag");
                        request.setAttribute("excelFlag", flag);
                        request.setAttribute("personalData", shgPersonalData);
                    } else {
                        request.setAttribute("personalData", shgPersonalData);
                        request.setAttribute("flag", map.get("flag"));

                    }
                   
                    request.setAttribute("excel", "<a href=\"shgPersonalDetailsExcel.xls?districtName=" + request.getParameter("districtName") + "&mandalName=" + request.getParameter("mandalName") + "\"> Export To Excel <img src=\"DisabilityUITG/images/excel.jpg\" height=\"25\" width=\"25\"/</a>");
                } else {
                    request.setAttribute("msg", "<font color='red' size='3'>Details are Not Available!</font>");
                }
                request.setAttribute("districtName", request.getParameter("districtName"));
                request.setAttribute("mandalName", request.getParameter("mandalName"));
                request.setAttribute("districtId", request.getParameter("districtId"));
                request.setAttribute("mandalId", request.getParameter("mandalId"));


            }
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getShgPersonalDetails", "ReportAction", "DataBase");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AgeReport", "execute");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getShgPersonalDetails", "ReportAction", "Code");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AgeReport", "execute");
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward eligiblePersonalDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            request.setAttribute("districtName", request.getParameter("districtName"));
            request.setAttribute("mandalName", request.getParameter("mandalName"));
            request.setAttribute("panchayathName", request.getParameter("panchayathName"));
            request.setAttribute("VillagelName", request.getParameter("VillagelName"));

            ReportDAO reportDAO = new ReportDAO();
            ArrayList shgPersonalData = new ArrayList();
            shgPersonalData = reportDAO.getShgEligiblePersonalDetails(ds, request.getParameter("district_id"), request.getParameter("mandal_id"), request.getParameter("panchayat_id"), request.getParameter("village_id"), request.getParameter("requestId"), request.getParameter("flag"));
            if (shgPersonalData != null && shgPersonalData.size() > 0) {
                request.setAttribute("personalData", shgPersonalData);
                if (request.getParameter("page") != null) {
                    StringBuffer excelHeading = new StringBuffer();
                    if (request.getParameter("districtName") != null) {
                        excelHeading.append("District: " + request.getParameter("districtName"));
                    }
                    if (request.getParameter("mandalName") != null) {
                        excelHeading.append(" ,Mandal: " + request.getParameter("mandalName"));
                    }
                    if (request.getParameter("panchayathName") != null) {
                        excelHeading.append(" ,Panchayat: " + request.getParameter("panchayathName"));
                    }
                    if (request.getParameter("VillagelName") != null) {
                        excelHeading.append(" ,Village: " + request.getParameter("VillagelName"));
                    }
                    if (request.getParameter("habiationName") != null) {
                        excelHeading.append(" ,Habiation: " + request.getParameter("habiationName"));
                    }
                    ArrayList headersList = new ArrayList();


                    headersList.add("SNo");
                    headersList.add("Person Name");
                    headersList.add("SADAREM ID");
                    headersList.add("Pension ID");
                    headersList.add(" Ration Card No");
                    headersList.add("Gender");
                    headersList.add("Age");
                    headersList.add("Education");
                    headersList.add("Caste");
                    headersList.add("Relation Name");
                    headersList.add("Type Of Disability");
                    headersList.add("Percentage");
                    headersList.add("Address");
                    headersList.add("Phone Number");
                    headersList.add("SHG Name");
                    headersList.add("SHG Bank Account");
                    headersList.add("Mamber Bank Account");
                    headersList.add("Member Bank Name ");
                    headersList.add("Bank Branch Name ");
                    ArrayList keyList = new ArrayList();

                    keyList.add("PERSONNAME");
                    keyList.add("SADAREMCODE");
                    keyList.add("PENSIONID");
                    keyList.add("RATIONCARD_NUMBER");
                    keyList.add("Gender");
                    keyList.add("age_years");
                    keyList.add("EDUCATION");
                    keyList.add("CASTE");
                    keyList.add("RELATIONNAME");
                    keyList.add("TYPEOFDISABILITY");
                    keyList.add("DISABILITY");
                    keyList.add("ADDRESS");
                    keyList.add("phone_no");



                    exportExcel(shgPersonalData, headersList, keyList, excelHeading.toString(), "SHG_PersonalDetails_Report", request, response);
                }
                request.setAttribute("excel",
                        "<a href=\"sHGEligibleReport.xls?mode=eligiblePersonalDetails&page=excel&districtName=" + request.getParameter("districtName") + ""
                        + " &mandalName=" + request.getParameter("mandalName") + ""
                        + " &panchayathName=" + request.getParameter("panchayathName") + ""
                        + " &VillagelName=" + request.getParameter("VillagelName") + ""
                        + " &district_id=" + request.getParameter("district_id") + ""
                        + " &mandal_id=" + request.getParameter("mandal_id") + ""
                        + " &panchayat_id=" + request.getParameter("panchayat_id") + ""
                        + " &village_id=" + request.getParameter("village_id") + ""
                        + " &requestId=" + request.getParameter("requestId") + "\"> "
                        + " Export To Excel <img src=\"DisabilityUITG/images/excel.jpg\" height=\"25\" width=\"25\"/</a>");
            }
        } //end of try block
        catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getShgPersonalDetails", "ReportAction", "Code");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AgeReport", "execute");
        }
        return mapping.findForward("personalDetails");
    }

    public ActionForward getSHGEligible(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            ReportForm reportForm = (ReportForm) form;
            TerritoryDAO territoryDAO = new TerritoryDAO();
            ArrayList districtsList = territoryDAO.getDistricts(ds);

            reportForm.setDistrictlist(districtsList);
            if (reportForm.getDistrict_id() != null && !reportForm.getDistrict_id().equals("0")) {


                FunctionalNeedReportService functionalNeedService =
                        FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();




                ReportDAO reportDAO = new ReportDAO();
                ArrayList shgData = new ArrayList();

                request.setAttribute("districtName", request.getParameter("districtName"));
                request.setAttribute("mandalName", request.getParameter("mandalName"));
                request.setAttribute("panchayathName", request.getParameter("panchayathName"));
                request.setAttribute("VillagelName", request.getParameter("VillagelName"));


                shgData = reportDAO.getSHGEligibleReport(ds, reportForm.getDistrict_id(), reportForm.getMandal_id(), reportForm.getPanchayat_id(), reportForm.getVillage_id());

                request.setAttribute("shgAbstract", shgData);

                if (request.getParameter("page") != null) {
                    StringBuffer excelHeading = new StringBuffer();
                    if (request.getParameter("districtName") != null) {
                        excelHeading.append("District: " + request.getParameter("districtName"));
                    }
                    if (request.getParameter("mandalName") != null) {
                        excelHeading.append(" ,Mandal: " + request.getParameter("mandalName"));
                    }
                    if (request.getParameter("panchayathName") != null) {
                        excelHeading.append(" ,Panchayat: " + request.getParameter("panchayathName"));
                    }
                    if (request.getParameter("VillagelName") != null) {
                        excelHeading.append(" ,Village: " + request.getParameter("VillagelName"));
                    }
                    if (request.getParameter("habiationName") != null) {
                        excelHeading.append(" ,Habiation: " + request.getParameter("habiationName"));
                    }
                    ArrayList headersList = new ArrayList();
                    String variable = "Habitation";
                     if (reportForm.getPanchayat_id() != null && reportForm.getPanchayat_id().equals("0")) {
                        variable = "Panchayat";
                    }
                    if (reportForm.getMandal_id() != null && reportForm.getMandal_id().equals("0")) {
                        variable = "Mandal";
                    }
                    if (reportForm.getVillage_id() != null && reportForm.getVillage_id().equals("0")) {
                        variable = "Village";
                    }

                    headersList.add("SNo");
                    headersList.add(variable);
                    headersList.add("Eligible PwDs");
                    ArrayList keyList = new ArrayList();
                    keyList.add("name");
                    keyList.add("count");

                    exportExcel(shgData, headersList, keyList, excelHeading.toString(), "SHG_Eligible_Report", request, response);
                }




                ArrayList mandallist = new ArrayList();
                ArrayList panchayatList = new ArrayList();
                ArrayList villageList = new ArrayList();
                mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"), "");
                villageList = territoryDAO.getVillages(ds, reportForm.getDistrict_id(), reportForm.getMandal_id(), reportForm.getPanchayat_id());
                panchayatList = territoryDAO.getPanchayats(ds, reportForm.getDistrict_id(), reportForm.getMandal_id());
                reportForm.setDistrictlist(districtsList);
                reportForm.setMandallist(mandallist);
                reportForm.setPanchayatlist(panchayatList);
                reportForm.setVillagelist(villageList);

                request.setAttribute("district_id", reportForm.getDistrict_id());
                request.setAttribute("mandal_id", reportForm.getMandal_id());
                request.setAttribute("village_id", reportForm.getVillage_id());
                request.setAttribute("panchayat_id", reportForm.getPanchayat_id());

            } //end of try block

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getShgPersonalDetails", "ReportAction", "DataBase");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AgeReport", "execute");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getShgPersonalDetails", "ReportAction", "Code");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AgeReport", "execute");
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getShgPersonalDetailsExcel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        HttpSession session = null;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            session = request.getSession(true);
            ReportDAO reportDAO = new ReportDAO();
            ArrayList shgPersonalData = new ArrayList();
            ArrayList headersList = new ArrayList();
            String flag = null;
            Map map = null;
            if (request.getParameter("districtId") != null
                    && request.getParameter("mandalId") != null && request.getParameter("flag") != null) {
                shgPersonalData = reportDAO.getShgPersonalDetails(ds, request.getParameter("districtId"), request.getParameter("mandalId"), request.getParameter("flag"));

                if (shgPersonalData != null && shgPersonalData.size() > 0) {
                    Iterator it = shgPersonalData.iterator();
                    if (it.hasNext()) {
                        map = (Map) it.next();
                        flag = map.get("flag").toString();

                    }
                    if (flag != null && flag.equals("9")) {
                        request.setAttribute("personalData1", shgPersonalData);
                        request.setAttribute("shgflag", map.get("flag"));
                    } else if (flag != null && flag.equals("10")) {
                        request.setAttribute("unmappedFlag", "unmappedFlag");
                        request.setAttribute("personalData", shgPersonalData);
                    } else {
                        request.setAttribute("personalData", shgPersonalData);
                        request.setAttribute("flag", map.get("flag"));
                    }
                    session.setAttribute("shgPersonalExcel", shgPersonalData);
                    //request.setAttribute("excel", "<a href=\"shgPersonalDetailsExcel.xls?districtName=" + request.getParameter("districtName") + "&mandalName=" + request.getParameter("mandalName") + "\"> Export To Excel <img src=\"DisabilityUI/images/excel.jpg\" height=\"25\" width=\"25\"/</a>");
                }
                request.setAttribute("districtName", request.getParameter("district_name"));
                request.setAttribute("mandalName", request.getParameter("mandal_name"));
                request.setAttribute("districtId", request.getParameter("districtId"));
                request.setAttribute("mandalId", request.getParameter("mandalId"));
                request.setAttribute("flag", flag);
                request.getParameter("heading");

                if (flag != null && flag.equals("9")) {
                    headersList.add("SNo");
                    headersList.add("Name");
                    headersList.add("Relation Name");
                    headersList.add("SADAREM ID");
                    headersList.add("Education");
                    headersList.add("Employement");
                    headersList.add("Martital Status");
                    headersList.add("Disabled Perscentage");
                    headersList.add("caste");
                    headersList.add("Habitation Name");
                    headersList.add("Village Name");
                    headersList.add("Mandal Name");
                    headersList.add("District Name");

                } else {

                    headersList.add("SNo");
                    headersList.add("Name");
                    headersList.add("Relation Name");
                    headersList.add("SADAREM ID");
                    headersList.add("Pension ID");
                    headersList.add("Age");
                    headersList.add("Gender");
                    headersList.add("Education");
                    headersList.add("Caste");
                    headersList.add("Type Of Disability");
                    headersList.add("House No");
                    headersList.add("Habitation Name");
                    headersList.add("Village Name");
                    headersList.add("Mandal Name");
                    headersList.add("District Name");
                    headersList.add("Phone No");
                    headersList.add("Assessment Status");
                    if (flag != null && flag.equals("10")) {
                        headersList.add("SHG Name");
                        headersList.add("SHG Bank Account");
                        headersList.add("Mamber Bank Account");
                        headersList.add("Member Bank Name");
                        headersList.add("Bank Branch Name");
                    }
                }

                ArrayList keyList = new ArrayList();
                keyList.add("name");
                keyList.add("count");


                individualExportExcel(shgPersonalData, headersList, keyList, "Individual SHG Report", "Individual SHG Report", flag, request, response);

            }

        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getShgPersonalDetailsExcel", "ReportAction", "DataBase");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AgeReport", "execute");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getShgPersonalDetailsExcel", "ReportAction", "Code");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AgeReport", "execute");
        }
        return null;
    }

    public static void exportExcel(ArrayList dataList, ArrayList headerList, ArrayList keyList, String reportHeading, String reportName, HttpServletRequest request, HttpServletResponse response) {
        OutputStream out = null;
        try {
            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=" + reportName + ".xls");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());


            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);

            if (dataList.size() == 0) {
                s.addCell(new Label(0, 0, "No Data Found................"));
            } else {
                s.addCell(new Label(0, 0, reportHeading));
                s.mergeCells(0, 0, 6, 0);
                addHeaders(s, headerList);
            }

            int x = 2;
            for (int i = 0; i < dataList.size(); i++, x++) {
                int j = 0;
                Map map = (Map) dataList.get(i);

                s.addCell(new Label(j++, x, i + 1 + ""));
                for (int u = 0; u < keyList.size(); u++) {
                    s.addCell(new Label(j++, x, map.get(keyList.get(u)) + ""));
                }

                if (i == (50000) * page) {
                    int sheet = ++page;
                    s = w.createSheet("Sheet" + sheet, sheet - 1);
                    j = 0;
                    x = 2;
                    addHeaders(s, headerList);
                }



            }

            w.write();

            w.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void setWidth(WritableSheet s, int col, int widthInChars) {

        s.setColumnView(col, widthInChars);
    }

    public static void addHeaders(WritableSheet s, ArrayList list) {

        for (int x = 0; x < list.size(); x++) {
            try {
                s.addCell(new Label(x, 1, list.get(x).toString()));
            } catch (WriteException ex) {
                ex.printStackTrace();
            }
            setWidth(s, x, 20);
        }
    }

    /**
     * This method is used for retrive the Data and display in Excel for individual Details
     * @param dataList
     * @param headerList
     * @param keyList
     * @param reportHeading
     * @param reportName
     * @param request
     * @param response
     */
    public static void individualExportExcel(ArrayList dataList, ArrayList headerList, ArrayList keyList, String reportHeading, String reportName, String flag, HttpServletRequest request, HttpServletResponse response) {

        OutputStream out = null;
        try {

            response.setContentType("application/vnd.ms-excel");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());

            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);
            if (dataList.size() == 0) {
                s.addCell(new Label(0, 0, "No Data Found................"));
            } else {

                //mergeCells(int col1, int row1, int col2, int row2)
                reportHeading = "SHG's Individual Details";

                s.addCell(new Label(0, 0, reportHeading));
                s.mergeCells(0, 0, 6, 0);
                addHeaders(s, headerList);
                s.mergeCells(0, 0, 0, 0);
                s.mergeCells(1, 0, 15, 0);

                addHeaders(s, headerList);
            }
            int x = 2;
            if (flag != null && flag.equalsIgnoreCase("9")) {
            for (int i = 0; i < dataList.size(); i++, x++) {
                    int j = 0;
                    Map m = (Map) dataList.get(i);
                    s.addCell(new Label(j++, x, i + 1 + ""));
                    s.addCell(new Label(j++, x, m.get("personName").toString()));
                    s.addCell(new Label(j++, x, m.get("relationName").toString()));
                    s.addCell(new Label(j++, x, m.get("SADAREMCODE").toString()));
                    s.addCell(new Label(j++, x, m.get("education").toString()));
                    s.addCell(new Label(j++, x, m.get("emp").toString()));
                    s.addCell(new Label(j++, x, m.get("martitalStatus").toString()));
                    s.addCell(new Label(j++, x, m.get("disabledperscentage").toString()));
                    s.addCell(new Label(j++, x, m.get("caste").toString()));

                    s.addCell(new Label(j++, x, m.get("habitationName").toString()));
                    s.addCell(new Label(j++, x, m.get("villageName").toString()));
                    s.addCell(new Label(j++, x, m.get("mandalName").toString()));
                    s.addCell(new Label(j++, x, m.get("districtName").toString()));
                    if (i == (50000) * page) {
                        int sheet = ++page;
                        s = w.createSheet("Sheet" + sheet, sheet - 1);
                        j = 0;
                        x = 2;

                        addHeaders(
                                s, headerList);
                    }


                }
            } else {
                for (int i = 0; i
                        < dataList.size(); i++, x++) {
                int j = 0;
                Map m = (Map) dataList.get(i);
                s.addCell(new Label(j++, x, i + 1 + ""));
                s.addCell(new Label(j++, x, m.get("personName").toString()));
                s.addCell(new Label(j++, x, m.get("relationName").toString()));
                s.addCell(new Label(j++, x, m.get("SADAREMCODE").toString()));
                s.addCell(new Label(j++, x, m.get("pensionId").toString()));
                s.addCell(new Label(j++, x, m.get("age").toString()));
                s.addCell(new Label(j++, x, m.get("gender").toString()));
                s.addCell(new Label(j++, x, m.get("education").toString()));
                s.addCell(new Label(j++, x, m.get("caste").toString()));
                s.addCell(new Label(j++, x, m.get("typeOfDisability").toString()));

                s.addCell(new Label(j++, x, m.get("houseNo").toString()));
                s.addCell(new Label(j++, x, m.get("habitationName").toString()));
                s.addCell(new Label(j++, x, m.get("villageName").toString()));
                s.addCell(new Label(j++, x, m.get("mandalName").toString()));
                s.addCell(new Label(j++, x, m.get("districtName").toString()));
                s.addCell(new Label(j++, x, m.get("phoneNo").toString()));
                s.addCell(new Label(j++, x, m.get("assesmentStatus").toString()));


                if (i == (50000) * page) {
                    int sheet = ++page;
                    s = w.createSheet("Sheet" + sheet, sheet - 1);
                    j = 0;
                    x = 2;

                    addHeaders(s, headerList);
                }


                }
            }

            w.write();

            w.close();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
