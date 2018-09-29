/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.DataBase.JNDIDataSource;
import javax.servlet.http.HttpSession;
import org.apache.axis.utils.BeanUtils;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.dao.TemporaryCertificateReportDAO;
import org.bf.disability.dto.ReportDTO;
import org.bf.disability.form.ReportForm;

import java.io.IOException;
import java.io.OutputStream;
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

/**
 *
 * @author apoisadmin
 */
public class TemporaryCertificateReportAction extends DispatchAction {

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        ArrayList reportList = new ArrayList();
        DataSource ds = null;
        String districtId = null;
        ReportForm reportForm = new ReportForm();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            TemporaryCertificateReportDAO temporaryCertificateReportDAO = new TemporaryCertificateReportDAO();
            if (session.getAttribute("districtId").toString() != null) {
                districtId = session.getAttribute("districtId").toString();
            }
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String currentDate = dateFormat.format(date);
            reportForm.setFromDate("01/01/2010");
            reportForm.setToDate(currentDate);

            reportList = temporaryCertificateReportDAO.getTemporaryCertificateReport(ds, districtId, reportForm.getFromDate(), reportForm.getToDate());

            if (reportList != null && reportList.size() > 0) {
                request.setAttribute("reportList", reportList);
            } else {
                request.setAttribute("msg", "Temporary Certificate Details Not Available");
            }
            request.setAttribute("onload", "onload");

            request.setAttribute("fromdate", reportForm.getFromDate());
            request.setAttribute("todate", reportForm.getToDate());
            String message = "Report From: " + reportForm.getFromDate() + " To: " + reportForm.getToDate();

            request.setAttribute("names", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("success");
    }

    public ActionForward getDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        ArrayList reportList = new ArrayList();
        DataSource ds = null;
        String districtId = null;
        ReportForm reportForm = (ReportForm) form;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            TemporaryCertificateReportDAO temporaryCertificateReportDAO = new TemporaryCertificateReportDAO();
            if (session.getAttribute("districtId").toString() != null) {
                districtId = session.getAttribute("districtId").toString();
            }
          
            reportList = temporaryCertificateReportDAO.getTemporaryCertificateReport(ds, districtId, reportForm.getFromDate(), reportForm.getToDate());
            if (reportList != null && reportList.size() > 0) {
                request.setAttribute("reportList", reportList);
            } else {
                request.setAttribute("msg", "Temporary Certificate Details Not Available");
            }

            String message = "Report From: " + reportForm.getFromDate() + " To: " + reportForm.getToDate();
            request.setAttribute("fromdate", reportForm.getFromDate());
            request.setAttribute("todate", reportForm.getToDate());

            request.setAttribute("names", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("success");
    }

    public ActionForward excel(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMException, SQLException {
        DataSource ds = null;
        String fromDate = "";
        String toDate = "";
        String districtId = null;
        try {
            HttpSession session = request.getSession();
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            ArrayList stateReport = new ArrayList();


            if (request.getParameter("fromDate") != null) {
                fromDate = request.getParameter("fromDate");
            }
            if (request.getParameter("toDate") != null) {
                toDate = request.getParameter("toDate");
            }

            if (session.getAttribute("districtId").toString() != null) {
                districtId = session.getAttribute("districtId").toString();
            }
            TemporaryCertificateReportDAO temporaryCertificateReportDAO = new TemporaryCertificateReportDAO();

            stateReport = temporaryCertificateReportDAO.getTemporaryCertificateReport(ds, districtId, fromDate, toDate);


            ArrayList headerList = new ArrayList();


            headerList.add("S No.");
            headerList.add("SADAREM ID");
            headerList.add("Name");
            headerList.add("Gender");

            headerList.add("Age");
            headerList.add("Relation Name");
            headerList.add("Rationcard No");

            headerList.add("Type of disability");
            headerList.add("Percentage");
            headerList.add("Address");
            exportExcel(stateReport, headerList, null, "Temporary Certificate Report from:" + fromDate + " to " + toDate, request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void exportExcel(ArrayList dataList, ArrayList headerList, ArrayList keyList, String reportHeading, HttpServletRequest request, HttpServletResponse response) {

        OutputStream out = null;
        try {

            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=TemporaryCertificateReport.xls");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());
            WritableCellFormat cellFormat = new WritableCellFormat();
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat.setAlignment(Alignment.CENTRE);
            cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat cell = new WritableCellFormat(bold);
            cell.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell.setAlignment(Alignment.CENTRE);
            cell.setVerticalAlignment(VerticalAlignment.CENTRE);
            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);
            if (dataList.size() == 0) {
                s.addCell(new Label(0, 0, "Temporary Certificate Details Not Available................"));

            } else {

                //mergeCells(int col1, int row1, int col2, int row2)
                // reportHeading = "Assessment Status Report" + " Distrct :" + distrctName + " Mandal :" + mandalName + " Village :" + villageName;

                s.addCell(new Label(0, 0, reportHeading, cell));
                s.mergeCells(0, 0, 9, 0);
                addHeaders(s, headerList, cell);

            }
            int x = 2;
            for (int i = 0; i < dataList.size(); i++, x++) {
                int j = 0;
                Map m = (Map) dataList.get(i);

                s.addCell(new Label(j++, x, i + 1 + "", cellFormat));
                s.addCell(new Label(j++, x, m.get("sadaremid").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("personname").toString()));
                s.addCell(new Label(j++, x, m.get("gender").toString()));

                s.addCell(new Label(j++, x, m.get("age").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("relationname").toString()));
                s.addCell(new Label(j++, x, m.get("rationcardno").toString()));
                s.addCell(new Label(j++, x, m.get("typeofdisability").toString()));
                s.addCell(new Label(j++, x, m.get("percentage").toString(), cellFormat));
                s.addCell(new Label(j++, x, m.get("address").toString()));
                if (i == (30000) * page) {
                    int sheet = ++page;
                    s = w.createSheet("Sheet" + sheet, sheet - 1);
                    j = 0;
                    x = 2;

                    addHeaders(s, headerList, cell);
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
}
