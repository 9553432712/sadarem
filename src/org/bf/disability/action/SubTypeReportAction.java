/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.SubTypeReportForm;
import org.bf.disability.servicefactory.SubTypeReportServiceFactory;
import org.bf.disability.serviceimpl.SubTypeReportServiceImpl;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import javax.mail.Session;
import javax.servlet.http.HttpSession;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.Alignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import org.bf.disability.dao.FunctionalNeedReportDAO;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.service.SubTypeReportService;

/**
 *
 * @author 747577
 */
public class SubTypeReportAction extends DispatchAction {

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
    String id = null, id1 = null;

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        SubTypeReportForm subTypeReportForm = (SubTypeReportForm) form;
        HttpSession session = request.getSession();
        id = request.getParameter("ids");
        DataSource ds = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        ArrayList subDisabilityList = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            SubTypeReportService subTypeReportService = SubTypeReportServiceFactory.getsubTypeReportServiceImpl();
            if (id != null) {
                id1 = id;
                request.setAttribute("id1", id1);
                subDisabilityList = subTypeReportService.getSubtypeOfDisability(ds, id);
                subTypeReportForm.setSubTypeOfDisabilityList(subDisabilityList);
            } else if (id == null) {
                request.setAttribute("id1", id1);
                subDisabilityList = subTypeReportService.getSubtypeOfDisability(ds, id1);
                subTypeReportForm.setSubTypeOfDisabilityList(subDisabilityList);
            }
            String disabilityId = request.getParameter("typeOfDisability_id");
            if (id != null && id.equalsIgnoreCase("lds")) {
                disabilityId = "1";
            } else if (id != null && id.equalsIgnoreCase("vids")) {
                disabilityId = "2";
            } else if (id != null && id.equalsIgnoreCase("hids")) {
                disabilityId = "3";
            } else if (id != null && id.equalsIgnoreCase("mrds")) {
                disabilityId = "4";
            } else if (id != null && id.equalsIgnoreCase("mids")) {
                disabilityId = "5";
            }
            if (request.getParameter("district_id") == null) {
                district_id = "0";
                subTypeReportForm.setDistricts_id("0");
            } else {
                district_id = request.getParameter("district_id");
                subTypeReportForm.setDistricts_id(request.getParameter("district_id"));
            }
            if (request.getParameter("mandal_id") == null) {
                mandal_id = "0";
                subTypeReportForm.setMandal_id("0");
            } else {
                mandal_id = request.getParameter("mandal_id");
                subTypeReportForm.setMandal_id(request.getParameter("mandal_id"));
            }
            if (request.getParameter("village_id") == null) {
                village_id = "0";
                subTypeReportForm.setVillage_id("0");
            } else {
                village_id = request.getParameter("village_id");
                subTypeReportForm.setVillage_id(request.getParameter("village_id"));
            }
            if (request.getParameter("habitation_id") == null) {
                habitation_id = "0";
                subTypeReportForm.setHabitation_id("0");
            } else {
                habitation_id = request.getParameter("habitation_id");
                subTypeReportForm.setHabitation_id(request.getParameter("habitation_id"));
            }
            String subdisabilityid = null;
            if (subDisabilityList.size() > 0) {
                Map m = new HashMap();
                m = (Map) subDisabilityList.get(0);
                int i = 1;
                if (i == 1) {
                    subdisabilityid = (String) m.get("subdisabilityid");
                     i++;
                }else{
                    subdisabilityid="1";
                }
            }
            subTypeReportForm.setTypeOfDisability_id(disabilityId);
            subTypeReportForm.setSubTypeOfDisability_id(subdisabilityid);
            request.setAttribute("ddid", disabilityId);
            request.setAttribute("sdid", subTypeReportForm.getSubTypeOfDisability_id());
            subTypeReportForm.setTypeOfDisability_id(disabilityId);
            ArrayList subTypeReportList = subTypeReportService.getSubTypeReportDetails(ds, subTypeReportForm);
            if (subTypeReportList.size() > 0) {
                request.setAttribute("subTypeReportList", subTypeReportList);
                session.setAttribute("subTypeReportLists", subTypeReportList);
            } else {
                request.setAttribute("nodata", "No Data Found!");
            }
            request.setAttribute("ExcelHeader", "District");
            request.setAttribute("district", "district");
            session.setAttribute("ExcelHeaders", "District");
            String disabilityName = subTypeReportService.getdisabulityName(ds, subTypeReportForm);
            String sdn = subTypeReportService.getsubdisabulityName(ds, subTypeReportForm);
            String subdisabilityName = "";
            if (sdn.contains("A-")) {
                subdisabilityName = sdn.substring(4);
            } else {
                subdisabilityName = sdn;
            }
            String name = dao.getDistMandVilHabname(ds, district_id, mandal_id, village_id, "0");
            request.setAttribute("names", name + ", Disability:" + disabilityName + ", Subtype of Disability:" + subdisabilityName);
            session.setAttribute("names1", name + ", Disability:" + disabilityName + ", Subtype of Disability:" + subdisabilityName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getSubReportDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SubTypeReportService subTypeReportService = SubTypeReportServiceFactory.getsubTypeReportServiceImpl();
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        SubTypeReportForm subTypeReportForm = (SubTypeReportForm) form;
        HttpSession session = request.getSession();
        DataSource ds = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (request.getParameter("district_id") == null) {
                district_id = "0";
                subTypeReportForm.setDistricts_id("0");
            } else {
                district_id = request.getParameter("district_id");
                subTypeReportForm.setDistricts_id(request.getParameter("district_id"));
            }
            if (request.getParameter("mandal_id") == null) {
                mandal_id = "0";
                subTypeReportForm.setMandal_id("0");
            } else {
                mandal_id = request.getParameter("mandal_id");
                subTypeReportForm.setMandal_id(request.getParameter("mandal_id"));
            }
            if (request.getParameter("village_id") == null) {
                village_id = "0";
                subTypeReportForm.setVillage_id("0");
            } else {
                village_id = request.getParameter("village_id");
                subTypeReportForm.setVillage_id(request.getParameter("village_id"));
            }
            if (request.getParameter("habitation_id") == null) {
                habitation_id = "0";
                subTypeReportForm.setHabitation_id("0");
            } else {
                habitation_id = request.getParameter("habitation_id");
                subTypeReportForm.setHabitation_id(request.getParameter("habitation_id"));
            }
            if (id != null) {
                id1 = id;
                request.setAttribute("id1", id1);
                ArrayList subDisabilityList = subTypeReportService.getSubtypeOfDisability(ds, id);
                subTypeReportForm.setSubTypeOfDisabilityList(subDisabilityList);
            } else if (id == null) {
                request.setAttribute("id1", id1);
                ArrayList subDisabilityList = subTypeReportService.getSubtypeOfDisability(ds, id1);
                subTypeReportForm.setSubTypeOfDisabilityList(subDisabilityList);
            }
            if (district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) {
                request.setAttribute("district", "district");
                request.setAttribute("ExcelHeader", "District");
            } else if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) {
                request.setAttribute("mandal", "mandal");
                request.setAttribute("ExcelHeader", "Mandal");
            } else if (!district_id.equals("0") && !mandal_id.equals("0") && village_id.equals("0")) {
                request.setAttribute("village", "village");
                request.setAttribute("ExcelHeader", "Village");
            } else if (!district_id.equals("0") && !mandal_id.equals("0") && !village_id.equals("0")) {
                request.setAttribute("habitation", "habitation");
                request.setAttribute("ExcelHeader", "Habitation");
            }
            session.setAttribute("ExcelHeaders", request.getAttribute("ExcelHeader"));
            String disabilityId = request.getParameter("typeOfDisability_id");
            if (id != null && id.equalsIgnoreCase("lds")) {
                disabilityId = "1";
            } else if (id != null && id.equalsIgnoreCase("vids")) {
                disabilityId = "2";
            } else if (id != null && id.equalsIgnoreCase("hids")) {
                disabilityId = "3";
            } else if (id != null && id.equalsIgnoreCase("mrds")) {
                disabilityId = "4";
            } else if (id != null && id.equalsIgnoreCase("mids")) {
                disabilityId = "5";
            }
            subTypeReportForm.setTypeOfDisability_id(disabilityId);
            subTypeReportForm.setSubTypeOfDisability_id(request.getParameter("subTypeOfDisability_id"));
            ArrayList subTypeReportList = subTypeReportService.getSubTypeReportDetails(ds, subTypeReportForm);
            if (subTypeReportList.size() > 0) {
                request.setAttribute("subTypeReportList", subTypeReportList);
                session.setAttribute("subTypeReportLists", subTypeReportList);
            } else {
                request.setAttribute("nodata", "No Data Found!");
            }
            String disabilityName = subTypeReportService.getdisabulityName(ds, subTypeReportForm);
            String sdn = subTypeReportService.getsubdisabulityName(ds, subTypeReportForm);
            String subdisabilityName = "";
            if (sdn.contains("A-")) {
                subdisabilityName = sdn.substring(4);
            } else {
                subdisabilityName = sdn;
            }
            String name = dao.getDistMandVilHabname(ds, district_id, mandal_id, village_id, "0");
            request.setAttribute("names", name + ", Disability:" + disabilityName + ", Subtype of Disability:" + subdisabilityName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getFullDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        SubTypeReportForm subTypeReportForm = (SubTypeReportForm) form;
        String mandalName = "", villageName = "", habName = "", disabilityName = "", sdn = "";
        DataSource ds = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        HttpSession session = request.getSession();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (request.getParameter("ddID").equals("")) {
                district_id = "0";
                subTypeReportForm.setDistricts_id("0");
            } else {
                district_id = request.getParameter("ddID");
                subTypeReportForm.setDistricts_id(request.getParameter("ddID"));
            }
            if (request.getParameter("mmID").equals("")) {
                mandal_id = "0";
                subTypeReportForm.setMandal_id("0");
            } else {
                mandal_id = request.getParameter("mmID");
                subTypeReportForm.setMandal_id(request.getParameter("mmID"));
            }
            if (request.getParameter("vvID").equals("")) {
                village_id = "0";
                subTypeReportForm.setVillage_id("0");
            } else {
                village_id = request.getParameter("vvID");
                subTypeReportForm.setVillage_id(request.getParameter("vvID"));
            }
            if (request.getParameter("hhID").equals("")) {
                habitation_id = "0";
                subTypeReportForm.setHabitation_id("0");
            } else {
                habitation_id = request.getParameter("hhID");
                subTypeReportForm.setHabitation_id(request.getParameter("hhID"));
            }
            SubTypeReportServiceImpl subTypeReportService = SubTypeReportServiceFactory.getsubTypeReportServiceImpl();
            String type = request.getParameter("types").toString().trim();
            String countid = request.getParameter("countId").toString();
            subTypeReportForm.setTypeOfDisability_id(request.getParameter("ddsID"));
            subTypeReportForm.setSubTypeOfDisability_id(request.getParameter("ssID"));
            TerritoryDAO territoryDAO = new TerritoryDAO();
            String distName = territoryDAO.getDistrictsName(ds, request.getParameter("ddID"));
            if (type.equals("Mandal")) {
                subTypeReportForm.setMandal_id(countid);
            } else if (type.equals("Village")) {
                subTypeReportForm.setVillage_id(countid);
            } else if (type.equals("Habitation")) {
                subTypeReportForm.setHabitation_id(countid);
            }
            request.setAttribute("did", subTypeReportForm.getDistricts_id());
            request.setAttribute("mid", subTypeReportForm.getMandal_id());
            request.setAttribute("vid", subTypeReportForm.getVillage_id());
            request.setAttribute("hid", subTypeReportForm.getHabitation_id());
            request.setAttribute("ddid", subTypeReportForm.getTypeOfDisability_id());
            request.setAttribute("sdid", subTypeReportForm.getSubTypeOfDisability_id());


            ArrayList addressDetailsList = subTypeReportService.getAddressDetails(ds, subTypeReportForm);
            if (addressDetailsList.size() > 0) {
                request.setAttribute("addressDetailsList", addressDetailsList);
                session.setAttribute("addressDetailsList1", addressDetailsList);
            } else {
                request.setAttribute("nodata", "Details of Persons with SubType Reports Personal Details Not Available");
            }

            mandalName = subTypeReportService.getMandalName(ds, subTypeReportForm);
            villageName = subTypeReportService.getvillageName(ds, subTypeReportForm);
            habName = subTypeReportService.gethabitationName(ds, subTypeReportForm);
            disabilityName = subTypeReportService.getdisabulityName(ds, subTypeReportForm);
            sdn = subTypeReportService.getsubdisabulityName(ds, subTypeReportForm);
            String subdisabilityName = "";
            if (sdn.contains("A-")) {
                subdisabilityName = sdn.substring(4);
            } else {
                subdisabilityName = sdn;
            }
            request.setAttribute("dn", distName);
            request.setAttribute("mn", mandalName);
            request.setAttribute("vn", villageName);
            request.setAttribute("hn", habName);
            request.setAttribute("ddn", disabilityName);
            request.setAttribute("sdn", subdisabilityName);
            request.setAttribute("type", type);
            String address = "";
            address = "DistrictName:" + distName + ", MandalName:" + mandalName + ", Village:ALL, Habitation:ALL, Disabulity:" + disabilityName + ", SubDisabulity:" + subdisabilityName + "";
            if (mandalName != null && !mandalName.equals("")) {
                address = "DistrictName:" + distName + ", MandalName:" + mandalName + ", Village:ALL, Habitation:ALL, Disabulity:" + disabilityName + ", SubDisabulity:" + subdisabilityName + "";
            }
            if (villageName != null && !villageName.equals("")) {
                address = "DistrictName:" + distName + ", MandalName:" + mandalName + ", Village:" + villageName + ", Habitation:ALL, Disabulity:" + disabilityName + ", SubDisabulity:" + subdisabilityName + "";
            }
            if (habName != null && !habName.equals("")) {
                address = "DistrictName:" + distName + ", MandalName:" + mandalName + ", Village:" + villageName + ", Habitation:" + habName + ", Disabulity:" + disabilityName + ", SubDisabulity:" + subdisabilityName + "";
            }
            request.setAttribute("addressdetails", address);
            String name = dao.getDistMandVilHabname(ds, district_id, mandal_id, village_id, "0");
            request.setAttribute("names", name + ", Disability:" + disabilityName + ", Subtype of Disability:" + subdisabilityName);
            session.setAttribute("names1", name + ", Disability:" + disabilityName + ", Subtype of Disability:" + subdisabilityName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward("subReport");
    }

    public ActionForward getSubTypeReportExcelData(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SubTypeReportForm subTypeReportForm = (SubTypeReportForm) form;
        String reportType = null;
        DataSource ds = null;
        HttpSession session = request.getSession();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            response.setContentType("application/MyExcel.ms-excel");
            ArrayList list = (ArrayList) session.getAttribute("addressDetailsList1");

            exportExcel(list, reportType, response, request);
            unspecified(mapping, form, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("");
    }

    public ActionForward getSubTypeReportCountExcelData(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SubTypeReportForm subTypeReportForm = new SubTypeReportForm();
        String reportType = null;
        DataSource ds = null;
        HttpSession session = request.getSession();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            reportType = request.getParameter("reportType");
            response.setContentType("application/MyExcel.ms-excel");
            request.setAttribute("count", request.getParameter("totalCount"));
            ArrayList list = (ArrayList) session.getAttribute("subTypeReportLists");
            exportExcelCount(list, reportType, response, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("");
    }

    public static void exportExcelCount(ArrayList dataList, String reportType, HttpServletResponse response, HttpServletRequest request) {
        OutputStream out = null;
        HttpSession session = request.getSession();
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=subTypeReport.xls");
            WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
            ArrayList headerList = new ArrayList();
            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);
            WritableCellFormat cellFormat = new WritableCellFormat();
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat cell = new WritableCellFormat(bold);
            cell.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell.setAlignment(Alignment.CENTRE);
            s.addCell(new Label(0, 0, "Details of Persons with SubType Reports Personal Details", cell));
            s.mergeCells(0, 0, 6, 0);
            String type = session.getAttribute("ExcelHeaders").toString();
            s.addCell(new Label(0, 1, session.getAttribute("names1").toString(), cell));
            s.mergeCells(0, 1, 9, 1);
            if (dataList.size() == 0) {
                s.addCell(new Label(0, 0, "No Data Found................", cell));
            } else {
                headerList.add("S No");
                headerList.add(type);
                headerList.add("Count");
                setWidth(s, 6, 60);
                addHeaders(s, headerList);
            }
            int x = 3;
            int k = 1;
            for (int i = 0; i < dataList.size(); i++, x++) {
                int j = 0;
                Map m = (Map) dataList.get(i);
                s.addCell(new Label(j++, x, i + 1 + "", cellFormat));
                s.addCell(new Label(j++, x, m.get("name").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("count").toString(), cellFormat));
                if (i == (50000) * page) {
                    int sheet = ++page;
                    s = w.createSheet("Sheet" + sheet, sheet - 1);
                    j = 0;
                    x = 2;
                    if (reportType.equals("parta")) {
                        setWidth(s, 7, 20);
                    } else {
                        setWidth(s, 9, 20);
                    }
                    addHeaders(s, headerList);
                }
            }
            s.addCell(new Label(0, x, "Total", cell));
            s.mergeCells(0, x, 1, x);
            s.addCell(new Label(2, x, request.getAttribute("count").toString(), cell));

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

    public static void exportExcel(ArrayList dataList, String reportType, HttpServletResponse response, HttpServletRequest request) {
        OutputStream out = null;
        HttpSession session = request.getSession();
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=subTypeReportExcel.xls");
            WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
            ArrayList headerList = new ArrayList();
            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);
            WritableCellFormat cellFormat = new WritableCellFormat();
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat cell = new WritableCellFormat(bold);
            //cell.setBackground(Colour.BLUE);
            cell.setBorder(Border.ALL, BorderLineStyle.THIN);
            s.addCell(new Label(0, 0, "Details of Persons with SubType Reports Personal Details", cell));
            s.mergeCells(0, 0, 6, 0);

            s.addCell(new Label(0, 1, session.getAttribute("names1").toString(), cell));
            s.mergeCells(0, 1, 9, 1);
            if (dataList.size() == 0) {
                s.addCell(new Label(0, 0, "No Data Found................", cellFormat));
            } else {
                headerList.add("S No");
                headerList.add("SADAREM ID");
                headerList.add("Name");
                headerList.add("Relation Name");
                headerList.add("Age");
                headerList.add("Caste");
                headerList.add("Address");
                setWidth(s, 6, 60);
                addHeaders(s, headerList);
            }
            int x = 3;
            for (int i = 0; i < dataList.size(); i++, x++) {
                int j = 0;
                Map m = (Map) dataList.get(i);
                s.addCell(new Label(j++, x, i + 1 + "", cellFormat));
                s.addCell(new Label(j++, x, m.get("sadaremcode").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("name").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("relationname").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("age").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("caste").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("address").toString(), cellFormat));
                if (i == (50000) * page) {
                    int sheet = ++page;
                    s = w.createSheet("Sheet" + sheet, sheet - 1);
                    j = 0;
                    x = 2;
                    if (reportType.equals("parta")) {
                        setWidth(s, 7, 20);
                    } else {
                        setWidth(s, 9, 20);
                    }
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

    public static void addHeaders(WritableSheet s, ArrayList list) throws Exception {
        WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        WritableCellFormat cell = new WritableCellFormat(bold);
        //cell.setBackground(Colour.BLUE);
        cell.setBorder(Border.ALL, BorderLineStyle.THIN);
        for (int x = 0; x < list.size(); x++) {
            try {
                s.addCell(new Label(x, 2, list.get(x).toString(), cell));
            } catch (WriteException ex) {
                ex.printStackTrace();
            }
        }
    }
}
