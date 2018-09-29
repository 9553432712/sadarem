/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import com.lowagie.text.pdf.codec.Base64.OutputStream;

import jxl.format.Border;
import jxl.format.Alignment;

import org.bf.disability.dao.CertificatesUploadDAO;
import org.bf.disability.form.PartAForm;
import org.bf.disability.common.DataBase.JNDIDataSource;

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

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import org.bf.disability.Exception.SADAREMDBException;

import jxl.write.Label;

//import jxl.write.WritableCellFormat;
//import jxl.write.WritableFont;

import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;

/**
 *
 *
 *
 * @author 740996
 */
public class CertificateUploadReportAction extends DispatchAction {

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PartAForm partAForm = (PartAForm) form;
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();

            }


            partAForm.setFromdate("01/01/2010"); 


        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("selFromDate", "01/11/2014");
        request.setAttribute("selToDate", "");
        String target = "success";
        return mapping.findForward(target);
    }

    public ActionForward submitdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataSource ds = null;
        ArrayList list = new ArrayList();
        CertificatesUploadDAO certificatesUploadDAO = new CertificatesUploadDAO();
        PartAForm partAForm = (PartAForm) form;
        try {


            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();

            }

            list = certificatesUploadDAO.certificateUploadDetailsReport(ds, partAForm, partAForm.getFromdate(), partAForm.getTodate());



            if (list.size() > 0) {
                request.setAttribute("list", list);

            } else {
                request.setAttribute("nodata", "Certificate Upload Details are not Available!");
            }
            request.setAttribute("selFromDate", partAForm.getFromdate());
            request.setAttribute("selToDate", partAForm.getTodate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("success");
    }

   

    public ActionForward excelWriting(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException, Exception {

        PartAForm partAForm = new PartAForm();
        DataSource ds = null;
        HttpSession session = null;
        CertificatesUploadDAO certificatesUploadDAO = new CertificatesUploadDAO();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            session = request.getSession(true);

            ArrayList certificatedetails = new ArrayList();
            //ReportForm reportForm = (ReportForm) form;
            String districtName = (String) request.getParameter("district_name");
            String mandalName = (String) request.getParameter("mandal_name");
            String villageName = (String) request.getParameter("village_name");

            String fromdate = null;
            if (request.getParameter("fDate") != null) {
                fromdate = request.getParameter("fDate");

            }

            String todate = null;
            if (request.getParameter("toDate") != null) {
                todate = request.getParameter("toDate");

            }


            certificatedetails = certificatesUploadDAO.certificateUploadDetailsReport(ds, partAForm, fromdate, todate);
            if (certificatedetails != null && certificatedetails.size() > 0) {
                request.setAttribute("certificatedetails", certificatedetails);

            } else {
                request.setAttribute("msg", "No Data Found");
            }



            exportExcel(certificatedetails, form, request, response);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public static void exportExcel(ArrayList dataList, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        OutputStream out = null;
        PartAForm reportForm = (PartAForm) form;
        String fromdate = (String) request.getParameter("fDate");
        String todate = (String) request.getParameter("toDate");



        try {
            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=Certificate Upload Report.xls");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());
            ArrayList headerList = new ArrayList();
            WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat cellFormat = new WritableCellFormat(bold);
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat.setAlignment(Alignment.LEFT);


            WritableCellFormat cell1 = new WritableCellFormat();
            cell1.setBorder(Border.ALL, BorderLineStyle.THICK);
            cell1.setAlignment(Alignment.CENTRE);

            WritableCellFormat cellcentr = new WritableCellFormat(bold);
            cellcentr.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellcentr.setAlignment(Alignment.CENTRE);

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


            if (dataList.size() == 0) {
                s.addCell(new Label(0, 0, "Certificate Upload Report  are not Available................"));
            } else {


                headerList.add("S.No");
                headerList.add("District");
                headerList.add("Total Assessed");
                headerList.add("Complete Certificates");
                headerList.add("Pending Certificates");

                s.mergeCells(0, 0, 4, 0);
                s.addCell(new Label(0, 0, " Certificate Upload Report From " + fromdate + " To " + todate, cell111));
//                s.mergeCells(0, 1, 4, 1);
//                s.addCell(new Label(0, 1, "From Date: " + fromdate + " ,   To Date: " + todate, cell111));
//                    s.mergeCells(0, 1, 4, 2);
//                s.addCell(new Label(0, 1, "From Date: " + fromdate + " ,   To Date: " + todate, cell111));
                addHeaders(s, headerList);
            }

            int x = 3;
            int k = 0;
            for (int i = 0; i < dataList.size(); i++, x++) {
                int j = 0;
                k++;
                Map m = (Map) dataList.get(i);
                s.addCell(new Label(j++, x, i + 1 + "", cellLeft));

                s.addCell(new Label(j++, x, m.get("district_name").toString(), cellLeft));
                s.addCell(new Label(j++, x, m.get("assessed").toString(), cell));
                s.addCell(new Label(j++, x, m.get("validationdone").toString(), cell));
                s.addCell(new Label(j++, x, m.get("pending").toString(), cell));

                if (i == (50000) * page) {
                    int sheet = ++page;
                    s = w.createSheet("Sheet" + sheet, sheet - 1);
                    j = 0;
                    x = 2;

                    addHeaders(s, headerList);

                }
            }
            Map m = (Map) dataList.get(k - 1);
            s.mergeCells(0, x, 1, x);
            s.addCell(new Label(0, x, "Total", cellcentr));
            s.addCell(new Label(2, x, m.get("totalassessed").toString(), cellcentr));
            s.addCell(new Label(3, x, m.get("totalvalidationdone").toString(), cellcentr));
            s.addCell(new Label(4, x, m.get("totalpending").toString(), cellcentr));
            w.write();
            w.close();

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
        for (int i = 0; i < col; i++) {
        	
        	if(i==0){
            s.setColumnView(i, 7);}else
        { s.setColumnView(i, 30);}
        }

    }

    public static void addHeaders(WritableSheet s, ArrayList list) throws WriteException {

        WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        WritableCellFormat cellFormat = new WritableCellFormat(bold);
        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        cellFormat.setAlignment(Alignment.CENTRE);
        cellFormat.setBackground(Colour.GRAY_25);
        for (int x = 0; x < list.size(); x++) {
            try {
                s.addCell(new Label(x, 2, list.get(x).toString(), cellFormat));
                setWidth(s, 12, 40);
            } catch (WriteException ex) {
                ex.printStackTrace();
            }
        }
    }
}
