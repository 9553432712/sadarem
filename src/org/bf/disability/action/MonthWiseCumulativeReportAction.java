/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.MonthWiseCumulativeReportForm;

import org.bf.disability.servicefactory.MonthWiseCumulativeReportServiceFactory;

import org.bf.disability.serviceimpl.MonthWiseCumulativeReportServiceImpl;

/**
 *
 * @author 693461
 */
public class MonthWiseCumulativeReportAction extends DispatchAction {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    /**
     * This is the Struts action method called on
     * http://.../actionPath?method=myAction1,
     * where "method" is the value specified in <action> element : 
     * ( <action parameter="method" .../> )
     */
    String id = null, id1 = null;

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource datasource = null;
        MonthWiseCumulativeReportForm monthWiseCumulativeReportForm = (MonthWiseCumulativeReportForm) form;

        HttpSession hs = request.getSession();
        id = request.getParameter("ids");

        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            MonthWiseCumulativeReportServiceImpl monthWiseCumulativeReportService = MonthWiseCumulativeReportServiceFactory.getMonthWiseCumulativeServiceImpl();


            ArrayList distList = monthWiseCumulativeReportService.getDistricts(datasource);
            monthWiseCumulativeReportForm.setDistrictList(distList);

            if (distList.size() > 0) {
            } else {
                request.setAttribute("nodats", "No Data Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getMandalsList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource datasource = null;
        MonthWiseCumulativeReportForm monthWiseCumulativeReportForm = (MonthWiseCumulativeReportForm) form;
        String district = request.getParameter("district_id");

        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            MonthWiseCumulativeReportServiceImpl monthWiseCumulativeReportService = MonthWiseCumulativeReportServiceFactory.getMonthWiseCumulativeServiceImpl();
            ArrayList mandalList = monthWiseCumulativeReportService.getMandals(datasource, district);
            monthWiseCumulativeReportForm.setMandalList(mandalList);
            unspecified(mapping, form, request, response);
            if (mandalList.size() > 0) {
            } else {
                request.setAttribute("nodats", "No Data Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getVillagesList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource datasource = null;
        MonthWiseCumulativeReportForm monthWiseCumulativeReportForm = (MonthWiseCumulativeReportForm) form;
        String district = request.getParameter("district_id");
        String mandal = request.getParameter("mandal_id");



        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            MonthWiseCumulativeReportServiceImpl monthWiseCumulativeReportService = MonthWiseCumulativeReportServiceFactory.getMonthWiseCumulativeServiceImpl();
            ArrayList villageList = monthWiseCumulativeReportService.getVillages(datasource, district, mandal, "");
            monthWiseCumulativeReportForm.setVillageList(villageList);
            getMandalsList(mapping, form, request, response);
            // unspecified(mapping, form, request, response);
            if (villageList.size() > 0) {
            } else {
                request.setAttribute("nodats", "No Data Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getHabitationsList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource datasource = null;
        MonthWiseCumulativeReportForm monthWiseCumulativeReportForm = (MonthWiseCumulativeReportForm) form;
        String district = request.getParameter("district_id");
        String mandal = request.getParameter("mandal_id");
        String village = request.getParameter("village_id");
        String habitation = request.getParameter("habitation_id");
        HttpSession hs = request.getSession();
        hs.setAttribute("vilid", request.getParameter("village_id"));


        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            MonthWiseCumulativeReportServiceImpl monthWiseCumulativeReportService = MonthWiseCumulativeReportServiceFactory.getMonthWiseCumulativeServiceImpl();
            ArrayList habitaionList = monthWiseCumulativeReportService.getHabitations(datasource, district, mandal, "", village);
            monthWiseCumulativeReportForm.setHabitationList(habitaionList);
            //unspecified(mapping, form, request, response);
            getVillagesList(mapping, form, request, response);

            if (habitaionList.size() > 0) {
            } else {
                request.setAttribute("nodats", "No Data Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getMonthReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        DataSource datasource = null;
        MonthWiseCumulativeReportForm monthWiseCumulativeReportForm = (MonthWiseCumulativeReportForm) form;
        String district = request.getParameter("district_id");
        String mandal = request.getParameter("mandal_id");
        String village = request.getParameter("village_id");
        String habitation = request.getParameter("habitation_id");
        MonthWiseCumulativeReportServiceImpl monthwise = MonthWiseCumulativeReportServiceFactory.getMonthWiseCumulativeServiceImpl();

        ArrayList habitaionList = new ArrayList();

        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            if (request.getParameter("district_id") != null && !request.getParameter("district_id").equals("")) {
                ArrayList mandalList = monthwise.getMandals(datasource, request.getParameter("district_id").toString());
                monthWiseCumulativeReportForm.setMandalList(mandalList);
                unspecified(mapping, form, request, response);
            }
            if (mandal != null && !mandal.equals("")) {
                ArrayList villageList = monthwise.getVillages(datasource, district, mandal, "");
                monthWiseCumulativeReportForm.setVillageList(villageList);
                getMandalsList(mapping, form, request, response);
            }

            if (village != null && !village.equals("")) {
                habitaionList = monthwise.getHabitations(datasource, district, mandal, "", village);
                monthWiseCumulativeReportForm.setHabitationList(habitaionList);
                getVillagesList(mapping, form, request, response);
            }
            monthWiseCumulativeReportForm.setFrmdt(request.getParameter("frmdt"));
            monthWiseCumulativeReportForm.setToDate(request.getParameter("toDate"));
            monthWiseCumulativeReportForm.setDistrict_id(request.getParameter("district_id"));
            monthWiseCumulativeReportForm.setMandal_id(request.getParameter("mandal_id"));
            monthWiseCumulativeReportForm.setVillage_id(request.getParameter("village_id"));


            ArrayList reportList = new ArrayList();
            reportList = monthwise.getReportMonthWise(datasource, monthWiseCumulativeReportForm.getFrmdt(), monthWiseCumulativeReportForm.getToDate(), monthWiseCumulativeReportForm.getDistrict_id(), monthWiseCumulativeReportForm.getMandal_id(), monthWiseCumulativeReportForm.getVillage_id());


            if (reportList.size() > 0) {
                request.setAttribute("reportList", reportList);
                request.setAttribute("district_id", district);
                request.setAttribute("mandal_id", mandal);
                request.setAttribute("village_id", village);
                request.setAttribute("habitation_id", habitation);
                request.setAttribute("districtname", request.getParameter("districtName"));
                request.setAttribute("mandalname", request.getParameter("mandalName"));
                request.setAttribute("vname", request.getParameter("villageName"));
                request.setAttribute("hname", request.getParameter("habitationName"));

            } else {
                request.setAttribute("noData", "No Data Found");
            }
        } catch (Exception ex) {
            Logger.getLogger(MonthWiseCumulativeReportAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward genExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        DataSource datasource = null;
        String district = request.getParameter("district_id");
        String mandal = request.getParameter("mandal_id");
        String village = request.getParameter("village_id");
        String habitation = request.getParameter("habitation_id");
        String fromdate = request.getParameter("fromdate");
        String todate = request.getParameter("todate");
        ArrayList reportList = null;
        String districtname = request.getParameter("districtName");
        String mandalname = request.getParameter("mandalName");
        String villagename = request.getParameter("villageName");
        //reportList=(ArrayList)request.getAttribute("reportList");

        response.setContentType("application/MyExcel.ms-excel");

        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            MonthWiseCumulativeReportServiceImpl monthwise = MonthWiseCumulativeReportServiceFactory.getMonthWiseCumulativeServiceImpl();

            reportList = monthwise.getReportMonthWise(datasource, fromdate, todate, district, mandal, village);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (district != null && district.equalsIgnoreCase("0")) {

            exportExcel(reportList, 1, districtname, mandalname, villagename, fromdate, todate, response);

        }
        if (district != null && !district.equalsIgnoreCase("0") && mandal.equalsIgnoreCase("0")) {

            exportExcel(reportList, 2, districtname, mandalname, villagename, fromdate, todate, response);

        }
        if (mandal != null && !mandal.equalsIgnoreCase("0") && village.equalsIgnoreCase("0")) {

            exportExcel(reportList, 3, districtname, mandalname, villagename, fromdate, todate, response);

        }
        if (village != null && !village.equalsIgnoreCase("0")) {

            exportExcel(reportList, 4, districtname, mandalname, villagename, fromdate, todate, response);

        }


        return mapping.findForward("");
    }

    public static void exportExcel(ArrayList dataList, int exceltype, String districtname, String mandalname, String villagename, String fromdate, String todate, HttpServletResponse response) {
        OutputStream out = null;
        try {
            response.setContentType("application/vnd.ms-excel");
            int t1 = 0, t2 = 0, t3 = 0, t4 = 0, t5 = 0, t6 = 0, t7 = 0, t8 = 0, t9 = 0, t10 = 0, t11 = 0, t12 = 0, t13 = 0, t14 = 0, t15 = 0, t16 = 0, t17 = 0, t18 = 0, t19 = 0, t20 = 0;

            Iterator ir = dataList.iterator();
            while (ir.hasNext()) {
                Map m = new HashMap();
                m = (Map) ir.next();
                t1 = t1 + Integer.parseInt(m.get("phaseI").toString());
                t2 = t2 + Integer.parseInt(m.get("phaseII").toString());
                t3 = t3 + Integer.parseInt(m.get("phaseIII").toString());
                t4 = t4 + Integer.parseInt(m.get("phaseIV").toString());
                t5 = t5 + Integer.parseInt(m.get("rachabandaI").toString());
                t6 = t6 + Integer.parseInt(m.get("rachabandaII").toString());

                t7 = t7 + Integer.parseInt(m.get("attended").toString());
                t8 = t8 + Integer.parseInt(m.get("eligible4150").toString());
                t9 = t9 + Integer.parseInt(m.get("eligible5160").toString());
                t10 = t10 + Integer.parseInt(m.get("eligible6170").toString());
                t11 = t11 + Integer.parseInt(m.get("eligible7180").toString());
                t12 = t12 + Integer.parseInt(m.get("eligible8190").toString());
                t13 = t13 + Integer.parseInt(m.get("eligibl9100").toString());
                t14 = t14 + Integer.parseInt(m.get("neligible0010").toString());
                t15 = t15 + Integer.parseInt(m.get("eligible1120").toString());
                t16 = t16 + Integer.parseInt(m.get("eligible2130").toString());
                t17 = t17 + Integer.parseInt(m.get("eligible3140").toString());
                t18 = t18 + Integer.parseInt(m.get("total").toString());
                t19 = t19 + Integer.parseInt(m.get("unattended").toString());
            }

            response.setHeader("Content-Disposition", "attachment; filename=MonthWiseCumulativeReport.xls");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());
            ArrayList headerList = new ArrayList();
            WritableCellFormat cellFormat = new WritableCellFormat();
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat cell = new WritableCellFormat(bold);
            cell.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell.setAlignment(Alignment.CENTRE);
            cell.setVerticalAlignment(VerticalAlignment.CENTRE);
            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);
            String address = "";
            switch (exceltype) {
                case 1:
                    address = "SADAREM Phase wise Assessed PWD's Cumulative Report - (Abstract) For all Districts from: " + fromdate + " to: " + todate;
                    break;
                case 2:
                    address = "SADAREM Phase wise Assessed PWD's Cumulative Report - (Abstract) For District: " + districtname + " from: " + fromdate + " to: " + todate;
                    break;
                case 3:
                    address = "SADAREM Phase wise Assessed PWD's Cumulative Report - (Abstract) For District: " + districtname + " Mandal:" + mandalname + " from: " + fromdate + " to: " + todate;
                    break;
                case 4:
                    address = "SADAREM Phase wise Assessed PWD's Cumulative Report - (Abstract) For District: " + districtname + " Mandal:  " + mandalname + " Village:" + villagename + " from: " + fromdate + " to: " + todate;
                    break;

            }
            s.addCell(new Label(0, 0, address, cell));
            s.mergeCells(0, 0, 25, 0);
            s.addCell(new Label(0, 1, "", cell));
            s.mergeCells(0, 1, 4, 0);
            s.addCell(new Label(5, 1, "Phase-IV", cell));

            s.mergeCells(5, 1, 7, 0);
            s.addCell(new Label(9, 1, "Eligible\n(Phase-I+Phase-II+Phase-III+Phase-IV)", cell));
            s.mergeCells(9, 1, 14, 0);
            s.addCell(new Label(15, 1, "Not Eligible\n(Phase-I+Phase-II+Phase-III+Phase-IV)", cell));
            s.mergeCells(15, 1, 18, 0);
            s.addCell(new Label(20, 1, "Not Attended\n(Phase-I+Phase-II+Phase-III+Phase-IV)", cell));
            s.mergeCells(20, 1, 25, 0);
            if (dataList.size() == 0) {
                s.addCell(new Label(0, 0, "No Data Found................"));
            } else {
                headerList.add("S No");
                switch (exceltype) {
                    case 1:
                        headerList.add("District");
                        break;
                    case 2:
                        headerList.add("Mandal");
                        break;
                    case 3:
                        headerList.add("Village");

                        break;
                    case 4:
                        headerList.add("Habitation");
                        break;
                }
                headerList.add("Phase-I");
                headerList.add("Phase-II");
                headerList.add("Phase-III");
                headerList.add("Old");
                headerList.add("Rachabanda-I");
                headerList.add("Rachabanda-II");
                headerList.add("Attended\n(Phase-I\n+\nPhase-II\n+\nPhase-III\n+\nPhase-IV)");

                headerList.add("40-50");
                headerList.add("51-60");
                headerList.add("61-70");
                headerList.add("71-80");
                headerList.add("81-90");
                headerList.add("91-100");
                headerList.add("0-10");
                headerList.add("11-20");
                headerList.add("21-30");
                headerList.add("31-39");


                headerList.add("Total");
                headerList.add("Total");
                headerList.add("Notice\nServed\nNot\nAttended");
                headerList.add("Dead");
                headerList.add("Not\nEligible");
                headerList.add("Not\nTraceable");
                headerList.add("Duplicate\ncan\nbe\nremoved");
                setWidth(s, 7, 20);
                addHeaders(s, headerList);
            }

            int x = 3;
            for (int i = 0; i < dataList.size(); i++, x++) {
                int j = 0;
                Map m = (Map) dataList.get(i);
                s.addCell(new Label(j++, x, Integer.toString(i + 1), cellFormat));
                s.addCell(new Label(j++, x, m.get("district").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("phaseI").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("phaseII").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("phaseIII").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("phaseIV").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("rachabandaI").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("rachabandaII").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("attended").toString(), cellFormat));

                s.addCell(new Label(j++, x, m.get("eligible4150").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("eligible5160").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("eligible6170").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("eligible7180").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("eligible8190").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("eligibl9100").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("neligible0010").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("eligible1120").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("eligible2130").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("eligible3140").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("total").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("unattended").toString(), cellFormat));
                s.addCell(new Label(j++, x, "0", cellFormat));
                s.addCell(new Label(j++, x, "0", cellFormat));
                s.addCell(new Label(j++, x, "0", cellFormat));
                s.addCell(new Label(j++, x, "0", cellFormat));
                s.addCell(new Label(j++, x, "0", cellFormat));
                if (i == (50000) * page) {
                    int sheet = ++page;
                    s = w.createSheet("Sheet" + sheet, sheet - 1);
                    j = 0;
                    x = 2;

                    setWidth(s, 7, 15);

                    addHeaders(s, headerList);
                }


            }
            s.addCell(new Label(0, x, "Total", cell));
            s.addCell(new Label(2, x, String.valueOf(t1), cell));
            s.addCell(new Label(3, x, String.valueOf(t2), cell));
            s.addCell(new Label(4, x, String.valueOf(t3), cell));
            s.addCell(new Label(5, x, String.valueOf(t4), cell));
            s.addCell(new Label(6, x, String.valueOf(t5), cell));

            s.addCell(new Label(7, x, String.valueOf(t6), cell));
            s.addCell(new Label(8, x, String.valueOf(t7), cell));
            s.addCell(new Label(9, x, String.valueOf(t8), cell));
            s.addCell(new Label(10, x, String.valueOf(t9), cell));
            s.addCell(new Label(11, x, String.valueOf(t10), cell));
            s.addCell(new Label(12, x, String.valueOf(t11), cell));
            s.addCell(new Label(13, x, String.valueOf(t12), cell));
            s.addCell(new Label(14, x, String.valueOf(t13), cell));
            s.addCell(new Label(15, x, String.valueOf(t14), cell));
            s.addCell(new Label(16, x, String.valueOf(t15), cell));
            s.addCell(new Label(17, x, String.valueOf(t16), cell));
            s.addCell(new Label(18, x, String.valueOf(t17), cell));
            s.addCell(new Label(19, x, String.valueOf(t18), cell));
            s.addCell(new Label(20, x, String.valueOf(t19), cell));
            s.addCell(new Label(21, x, "0", cell));
            s.addCell(new Label(22, x, "0", cell));
            s.addCell(new Label(23, x, "0", cell));
            s.addCell(new Label(24, x, "0", cell));
            s.addCell(new Label(25, x, "0", cell));
            setWidth(s, 6, 15);
            setWidth(s, 1, 20);
            s.mergeCells(0, x, 1, x);


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

    public static void addHeaders(WritableSheet s, ArrayList list) throws WriteException {
        WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        WritableCellFormat cell = new WritableCellFormat(bold);

        cell.setBorder(Border.ALL, BorderLineStyle.THIN);
        cell.setAlignment(Alignment.CENTRE);
        cell.setVerticalAlignment(VerticalAlignment.CENTRE);
        for (int x = 0; x < list.size(); x++) {
            try {

                s.addCell(new Label(x, 2, list.get(x).toString(), cell));
            } catch (WriteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String mandal = request.getParameter("mandal_id");
        String district = request.getParameter("district_id");
        String village = request.getParameter("village_id");
        String habitation = request.getParameter("habitation_id");
        String fromdate = request.getParameter("fromdate");
        String todate = request.getParameter("todate");
        ArrayList reportList = null;
        DataSource datasource = null;
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            MonthWiseCumulativeReportServiceImpl monthwise = MonthWiseCumulativeReportServiceFactory.getMonthWiseCumulativeServiceImpl();

            reportList = monthwise.getReportMonthWise(datasource, fromdate, todate, district, mandal, village);
            if (reportList.size() > 0) {
                request.setAttribute("reportList", reportList);
                request.setAttribute("district_id", district);
                request.setAttribute("mandal_id", mandal);
                request.setAttribute("village_id", village);
                request.setAttribute("habitation_id", habitation);
                request.setAttribute("fromdate", fromdate);
                request.setAttribute("todate", todate);
                request.setAttribute("districtname", request.getParameter("districtName"));
                request.setAttribute("mandalname", request.getParameter("mandalName"));
                request.setAttribute("vname", request.getParameter("villageName"));
                request.setAttribute("hname", request.getParameter("habitationName"));
            } else {
                request.setAttribute("noData", "No Data Found");
            }
        } catch (Exception ex) {
            Logger.getLogger(MonthWiseCumulativeReportAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapping.findForward("print");
    }
}
