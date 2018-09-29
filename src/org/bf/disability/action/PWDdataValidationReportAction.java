/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import com.lowagie.text.pdf.codec.Base64.OutputStream;

import jxl.format.Alignment;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import java.util.Map;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.awt.Color;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




import jxl.write.Label;

//import jxl.write.WritableCellFormat;
//import jxl.write.WritableFont;

import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;

import org.bf.disability.common.DataBase.JNDIDataSource;


import org.bf.disability.dao.PWDdataValidationReportDAO;
import org.bf.disability.form.ReportForm;

/**
 *
 * @author apoisadmin
 */
public class PWDdataValidationReportAction extends DispatchAction {

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        //HttpSession session = request.getSession();
        ArrayList reportList = new ArrayList();
        DataSource ds = null;
        String districtId = null;
        ReportForm reportForm = new ReportForm();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            PWDdataValidationReportDAO pWDdataValidationReportDAO = new PWDdataValidationReportDAO();
//            if (session.getAttribute("districtId").toString() != null) {
//                districtId = session.getAttribute("districtId").toString();
//            }
//            Date date = new Date();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            String currentDate = dateFormat.format(date);
//            reportForm.setFromDate("01/01/2010");
//            reportForm.setToDate(currentDate);



            String districtname = null;
            String mandal_id = null;


            if (request.getParameter("districtName") != null) {
                districtname = request.getParameter("districtName");
            }
            String districtid = null;
            String mandal_name = null;

            if (request.getParameter("district_id") != null) {
                districtId = request.getParameter("district_id");
            }
            if (request.getParameter("mandal_id") != null) {
                mandal_id = request.getParameter("mandal_id");
            }
            if (request.getParameter("mandal_name") != null) {
                mandal_name = request.getParameter("mandal_name");
            }

            reportList = pWDdataValidationReportDAO.getpwdValidationReport(ds, districtId, mandal_id, null, null);


            if (districtId != null && districtId.equalsIgnoreCase("0") && mandal_id != null && !mandal_id.equalsIgnoreCase("0")) {
            }
            if (districtId != null && districtId.equalsIgnoreCase("0") && mandal_id != null) {
                request.setAttribute("hier", "<a href='./dataValidationReport.do?mode=unspecified'>Back</a>");
            }
            request.setAttribute("mandal_id", mandal_id);
            request.setAttribute("districtname", districtname);
            request.setAttribute("mandal_name", mandal_name);
            request.setAttribute("districtId", request.getParameter("district_id"));


            if (reportList != null && reportList.size() > 0) {
                request.setAttribute("reportList", reportList);
            } else {
                request.setAttribute("msg", "PWD Validation Details Not Available");
            }
            request.setAttribute("onload", "onload");

//            request.setAttribute("fromdate", reportForm.getFromDate());
//            request.setAttribute("todate", reportForm.getToDate());
//            String message = "Report From: " + reportForm.getFromDate() + " To: " + reportForm.getToDate();

//            request.setAttribute("names", message);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward("success");
    }

    public ActionForward excel(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
        ArrayList stateReport = new ArrayList();

        PWDdataValidationReportDAO pWDdataValidationReportDAO = new PWDdataValidationReportDAO();

        DataSource ds = null;
        ArrayList headerList = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            String districtname = null;
            String mandal_id = null;


            if (request.getParameter("districtName") != null) {

                districtname = request.getParameter("districtName");
            }

            String districtid = null;
            String mandal_name = null;

            if (request.getParameter("district_id") != null) {
                districtid = request.getParameter("district_id");
            }
            if (request.getParameter("mandal_id") != null) {
                mandal_id = request.getParameter("mandal_id");
            }
            if (request.getParameter("mandal_name") != null) {
                mandal_name = request.getParameter("mandal_name");
            }
            stateReport = pWDdataValidationReportDAO.getpwdValidationReport(ds, districtid, mandal_id, null, null);

            if (stateReport.size() > 0) {
                request.setAttribute("stateReport", stateReport);
                request.setAttribute("display", "display");
            } else {
                request.setAttribute("nodata", "PwD Data Validation  Details are not Available!");
            }

            request.setAttribute("mandal_id", mandal_id);
            request.setAttribute("districtname", districtname);
            request.setAttribute("mandal_name", mandal_name);
            request.setAttribute("districtId", request.getParameter("district_id"));
            exportExcel(stateReport, headerList, form, request, response, districtid, mandal_id, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void exportExcel(ArrayList stateReport, ArrayList headerList, ActionForm form, HttpServletRequest request, HttpServletResponse response,
            String district, String mandal, String panchayat, String month, String Year) {
        OutputStream out = null;

        ReportForm formBean = (ReportForm) form;

        String districtname = null;
        String mandal_id = null;
        String districtid = null;
        String mandal_name = null;


        if (request.getParameter("district_id") != null) {

            districtid = request.getParameter("district_id");
        }

        if (request.getParameter("mandal_id") != null) {

            mandal_id = request.getParameter("mandal_id");
        }

        if (request.getParameter("mandal_name") != null) {

            mandal_name = request.getParameter("mandal_name");
        }


        if (request.getParameter("districtName") != null) {

            districtname = request.getParameter("districtName");
        }

        try {


            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition", "attachment; PwD Data Validation Report.xls");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());

            WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);


            WritableCellFormat cell = new WritableCellFormat();
            WritableCellFormat cellLeft = new WritableCellFormat();
            cell.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellLeft.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell.setAlignment(Alignment.CENTRE);
            cellLeft.setAlignment(Alignment.LEFT);
            cell.setVerticalAlignment(VerticalAlignment.CENTRE);

            WritableCellFormat cell111 = new WritableCellFormat(bold);
            cell111.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell111.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell111.setAlignment(Alignment.CENTRE);
            cell111.setBackground(Colour.GRAY_25);
            cell111.setVerticalAlignment(VerticalAlignment.CENTRE);
            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);


            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            Date date = new Date();



            if (stateReport.size() == 0) {
                s.addCell(new Label(0, 0, "PwD Data Validation Report are not Available................"));
            } else {

                s.mergeCells(0, 0, 4, 0);

                if (district != null && !district.equalsIgnoreCase("0") && mandal != null && !mandal.equalsIgnoreCase("0")) {
                    s.addCell(new Label(0, 0, "  R1.5 PwD Data Validation Rural Report From 01/01/2010 To" + dateFormat.format(date) + " of District:  " + districtname + ",Mandal:" + mandal_name, cell111));
                } else if (district != null && !district.equalsIgnoreCase("0")) {
                    s.addCell(new Label(0, 0, "  R1.5 PwD Data Validation Rural Report From 01/01/2010 To" + dateFormat.format(date) + " of District: " + districtname, cell111));
                } else if (district == null) {

                    s.addCell(new Label(0, 0, " R1.5 PwD Data Validation Rural Report From 01/01/2010 To" + dateFormat.format(date), cell111));

                }
                s.addCell(new Label(0, 2, "S.No", cell111));
                setWidth(s, 0, 10);
                if (district != null && !district.equalsIgnoreCase("0") && mandal != null && !mandal.equalsIgnoreCase("0")) {
                    s.addCell(new Label(1, 2, "Village", cell111));
                    setWidth(s, 1, 30);
                } else if (district != null && !district.equalsIgnoreCase("0")) {
                    s.addCell(new Label(1, 2, "Mandal", cell111));
                    setWidth(s, 1, 30);
                } else if (district == null) {
                    s.addCell(new Label(1, 2, "District", cell111));
                    setWidth(s, 1, 30);
                }


                s.addCell(new Label(2, 2, "Total Assessed", cell111));
                setWidth(s, 2, 30);

                s.addCell(new Label(3, 2, "PwD Validation Done", cell111));
                setWidth(s, 3, 30);
                s.addCell(new Label(4, 2, "PwD Validation Pending", cell111));
                setWidth(s, 4, 30);
                
                s.addCell(new Label(0, 3, "1", cell111));
                setWidth(s, 0, 30);
                s.addCell(new Label(1, 3, "2", cell111));
                setWidth(s, 1, 30);
                s.addCell(new Label(2, 3, "3", cell111));
                setWidth(s, 2, 30);

                s.addCell(new Label(3, 3, "4", cell111));
                setWidth(s, 3, 30);
                s.addCell(new Label(4, 3, "5", cell111));
                setWidth(s, 4, 30);
                
            }
            int x = 4;
            int k = 0;
            for (int i = 0; i < stateReport.size(); i++, x++) {
                int j = 0;
                k++;
                Map m = (Map) stateReport.get(i);
                s.addCell(new Label(j++, x, i + 1 + "", cellLeft));


                if (district != null && !district.equalsIgnoreCase("0") && mandal != null && !mandal.equalsIgnoreCase("0")) {
                    s.addCell(new Label(j++, x, m.get("village_name").toString(), cellLeft));
                } else if (district != null && !district.equalsIgnoreCase("0")) {
                    s.addCell(new Label(j++, x, m.get("mandal_name").toString(), cellLeft));
                } else if (district == null) {
                    s.addCell(new Label(j++, x, m.get("district_name").toString(), cellLeft));
                }

                s.addCell(new Label(j++, x, m.get("Assessed").toString(), cell));

                s.addCell(new Label(j++, x, m.get("validationDone").toString(), cell));

                s.addCell(new Label(j++, x, m.get("validationPending").toString(), cell));
                if (i == (100000) * page) {
                    int sheet = ++page;
                    s = w.createSheet("Sheet" + sheet, sheet - 1);
                    j = 0;
                    x = 2;


                }
            }

            Map m = (Map) stateReport.get(k - 1);
            s.mergeCells(0, x, 1, x);
            s.addCell(new Label(0, x, "Total", cell111));
            s.addCell(new Label(2, x, m.get("totalassessed1").toString(), cell111));
            s.addCell(new Label(3, x, m.get("validationdone1").toString(), cell111));
            s.addCell(new Label(4, x, m.get("validationPending1").toString(), cell111));
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

    public static void addHeaders(WritableSheet s, ArrayList list, WritableCellFormat cell) {

        for (int x = 0; x < list.size(); x++) {
            try {
                s.addCell(new Label(x, 1, list.get(x).toString(), cell));
            } catch (WriteException ex) {
                ex.printStackTrace();
            }
            setWidth(s, x, 20);
        }
    }

    public static void setWidth(WritableSheet s, int col, int widthInChars) {

        s.setColumnView(col, widthInChars);
    }

    public ActionForward excelIndividualDetails(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
        ArrayList stateReport = new ArrayList();

        PWDdataValidationReportDAO pWDdataValidationReportDAO = new PWDdataValidationReportDAO();

        DataSource ds = null;
        ArrayList headerList = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String districtname = null;
            String mandal_id = null;
            String districtid = null;
            String mandal_name = null;

            if (request.getParameter("districtName") != null) {
                districtname = request.getParameter("districtName");
            }

            if (request.getParameter("district_id") != null) {
                districtid = request.getParameter("district_id");
            }
            if (request.getParameter("mandal_id") != null) {
                mandal_id = request.getParameter("mandal_id");
            }
            if (request.getParameter("mandal_name") != null) {
                mandal_name = request.getParameter("mandal_name");
            }

            stateReport = pWDdataValidationReportDAO.getpwdValidationReportIndividual(ds, districtid, mandal_id);
           
            request.setAttribute("mandal_id", mandal_id);
            request.setAttribute("districtname", districtname);
            request.setAttribute("mandal_name", mandal_name);
            request.setAttribute("districtId", request.getParameter("district_id"));

            headerList.add("S.No");
            headerList.add("Pension ID");
            headerList.add("SADAREM ID");
            headerList.add("Name");
            headerList.add("Relation Name");
            headerList.add("Gender");
            headerList.add("Age");
            headerList.add("Ration Card Number");
            headerList.add("Type of Disability");
            headerList.add("House No");
//            headerList.add("Mandal");
            headerList.add("Village");
            headerList.add("Habitation");
            headerList.add("Pension Phase");
//            headerList.add("Status");

            exportExcelIndividual(stateReport, headerList, form, request, response, districtid, mandal_id, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void exportExcelIndividual(ArrayList stateReport, ArrayList headerList, ActionForm form, HttpServletRequest request, HttpServletResponse response,
            String district, String mandal, String panchayat, String month, String Year) {
        OutputStream out = null;

        ReportForm formBean = (ReportForm) form;

        String districtname = null;
        String mandal_id = null;
        String districtid = null;
        String mandal_name = null;


        if (request.getParameter("district_id") != null) {

            districtid = request.getParameter("district_id");
        }

        if (request.getParameter("mandal_id") != null) {

            mandal_id = request.getParameter("mandal_id");
        }

        if (request.getParameter("mandal_name") != null) {

            mandal_name = request.getParameter("mandal_name");
        }


        if (request.getParameter("districtName") != null) {

            districtname = request.getParameter("districtName");
        }

        try {


            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition", "attachment; PwD Data Validation Report.xls");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());

            WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);


            WritableCellFormat cell = new WritableCellFormat();
            WritableCellFormat cellLeft = new WritableCellFormat();
            cell.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellLeft.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell.setAlignment(Alignment.CENTRE);
            cellLeft.setAlignment(Alignment.LEFT);
            cell.setVerticalAlignment(VerticalAlignment.CENTRE);

            WritableCellFormat cell111 = new WritableCellFormat(bold);
            cell111.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell111.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell111.setAlignment(Alignment.CENTRE);

            cell111.setVerticalAlignment(VerticalAlignment.CENTRE);
            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);


            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            Date date = new Date();



            if (stateReport.size() == 0) {
                s.addCell(new Label(0, 0, "PwD Data Validation Report are not Available................"));
            } else {

                s.mergeCells(0, 0, 12, 0);

                s.addCell(new Label(0, 0, "  R1.5 PwD Data Validation Rural Pending Individual Details of District : " + districtname + ", Mandal : " + mandal_name, cell111));

                addHeaders(s, headerList, cell111);

                int x = 2;
                int k = 0;
                for (int i = 0; i < stateReport.size(); i++, x++) {
                    int j = 0;
                    k++;
                    Map m = (Map) stateReport.get(i);
                    s.addCell(new Label(j++, x, m.get("sno").toString(), cellLeft));
                    s.addCell(new Label(j++, x, m.get("PENSIONCARD_NO").toString(), cellLeft));
                    s.addCell(new Label(j++, x, m.get("PERSON_CODE").toString(), cellLeft));
                    s.addCell(new Label(j++, x, m.get("PERSONNAME").toString(), cellLeft));
                    s.addCell(new Label(j++, x, m.get("RELATION_NAME").toString(), cellLeft));
                    s.addCell(new Label(j++, x, m.get("Gender").toString(), cellLeft));
                    s.addCell(new Label(j++, x, m.get("AGE_YEARS").toString(), cellLeft));
                    s.addCell(new Label(j++, x, m.get("RATIONCARD_NUMBER").toString(), cellLeft));
                    s.addCell(new Label(j++, x, m.get("TYPEOF_DISABILITY").toString(), cellLeft));
                    s.addCell(new Label(j++, x, m.get("HOUSE_NUMBER").toString(), cellLeft));

                    s.addCell(new Label(j++, x, m.get("VILLAGE_NAME").toString(), cellLeft));
                    s.addCell(new Label(j++, x, m.get("HABITATION_NAME").toString(), cellLeft));

                    s.addCell(new Label(j++, x, m.get("PENSIONPHASE").toString(), cellLeft));



                    if (i == (100000) * page) {
                        int sheet = ++page;
                        s = w.createSheet("Sheet" + sheet, sheet - 1);
                        j = 0;
                        x = 2;
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


