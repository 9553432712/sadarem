/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.ReportDAO;
import org.bf.disability.dto.ReportDTO;
import org.bf.disability.form.ReportForm;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author 310926
 */
public class CMDashboardReportAction extends DispatchAction {
    /* forward name="success" path="" */

    private String target = "success";

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMException, SQLException {
        DataSource ds = null;
        ReportForm formBean = (ReportForm) form;
        ArrayList stateReport = new ArrayList();
        ReportDTO reportDTO = new ReportDTO();
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String district_name = "ALL";
        String mandal_name = "ALL";
        String village_name = "ALL";
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            ReportDAO reportDAO = new ReportDAO();
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


            if (request.getParameter("district_name") != null) {
                district_name = request.getParameter("district_name");
            }
            if (request.getParameter("mandal_name") != null) {
                mandal_name = request.getParameter("mandal_name");
            }
            if (request.getParameter("village_name") != null) {
                village_name = request.getParameter("village_name");
            }
            formBean.setDistrict_id(districtId);
            formBean.setMandal_id(mandalId);
            formBean.setVillage_id(villageId);
            BeanUtils.copyProperties(reportDTO, formBean);


            stateReport = reportDAO.getCMReport_v1(ds, reportDTO);
            if (stateReport != null && stateReport.size() > 0) {
                request.setAttribute("stateReport", stateReport);
            } else {
                request.setAttribute("noData", "SADAREM ASSESSMENT,Pensions Percentage Report Details Not AVailable! ");
            }
            if (districtId.equals("0")) {
                request.setAttribute("district", "district");

            } else if (mandalId.equals("0")) {
                request.setAttribute("mandal", "mandal");

            } else if (villageId.equals("0")) {
                request.setAttribute("village", "village");

            } else {
                request.setAttribute("habitation", "habitation");
            }
            request.setAttribute("district_name", district_name);
            request.setAttribute("mandal_name", mandal_name);
            request.setAttribute("village_name", village_name);
            request.setAttribute("heading", "District : " + district_name + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                    + "Mandal :" + mandal_name + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Village :" + village_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward excel(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMException, SQLException {
        String district_name = "ALL";
        String mandal_name = "ALL";
        String village_name = "ALL";
        String heading = "";

        try {
            HttpSession session = request.getSession();
            ArrayList stateReport = new ArrayList();
            if (session.getAttribute("stateReport") != null) {
                stateReport = (ArrayList) session.getAttribute("stateReport");
            }
            if (request.getParameter("district_name") != null) {
                district_name = request.getParameter("district_name");
            }
            if (request.getParameter("mandal_name") != null) {
                mandal_name = request.getParameter("mandal_name");
            }
            if (request.getParameter("village_name") != null) {
                village_name = request.getParameter("village_name");
            }
            if (request.getParameter("heading") != null) {
                heading = request.getParameter("heading");
            }
            //
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);

            ArrayList headerList = new ArrayList();


            headerList.add("");
            headerList.add("Assessed");
            headerList.add("Eligible");
            headerList.add("Rejected");

            headerList.add("Assessed");
            headerList.add("Eligible");
            headerList.add("Rejected");

            headerList.add("Assessed");
            headerList.add("Eligible");
            headerList.add("Rejected");

            headerList.add("Assessed");
            headerList.add("Eligible");
            headerList.add("Rejected");

            headerList.add("Assessed");
            headerList.add("Eligible");
            headerList.add("Rejected");

            headerList.add("Assessed");
            headerList.add("Eligible");
            headerList.add("Rejected");

            headerList.add("Assessed");
            headerList.add("Eligible");
            headerList.add("Rejected");
            headerList.add("");
            //
            exportExcel(stateReport, headerList, "R1.1 : SADAREM Assessment Status Report", "SADAREM DashBoard", "District :" + district_name + "         Mandal : " + mandal_name + "        Village : " + village_name + "      SADAREM Assessment and Pension status as on:" + df.format(cal.getTime()), district_name, mandal_name,
                    village_name, request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ActionForward CMexcel(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMException, SQLException {
        String district_name = "ALL";
        String mandal_name = "ALL";
        String village_name = "ALL";
        String heading = "";

        try {
            HttpSession session = request.getSession();
            ArrayList stateReport = new ArrayList();
            if (session.getAttribute("stateReport") != null) {
                stateReport = (ArrayList) session.getAttribute("stateReport");
            }
            if (request.getParameter("district_name") != null) {
                district_name = request.getParameter("district_name");
            }
            if (request.getParameter("mandal_name") != null) {
                mandal_name = request.getParameter("mandal_name");
            }
            if (request.getParameter("village_name") != null) {
                village_name = request.getParameter("village_name");
            }
            if (request.getParameter("heading") != null) {
                heading = request.getParameter("heading");
            }
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);

            ArrayList headerList = new ArrayList();

            headerList.add("Total Applied");
            headerList.add("Assessed (%)");
            headerList.add("Disability (<40%)\n(%)");
            headerList.add("(>=40%)\n(%)");
            headerList.add("(>=40% to <80%) \n (%)");
            headerList.add("(>=80%) \n (%)");
            headerList.add("PwDs - Pensions \nCovered (%)\n");
            headerList.add("Pendency Certificate\n (%)");

            CMexportExcel(stateReport, headerList, "SADAREM Assessed & Pension Status DashBoard", "SADAREM DashBoard", "District :" + district_name + "         Mandal : " + mandal_name + "        Village : " + village_name + "      SADAREM Assessment and Pension status as on:" + df.format(cal.getTime()), district_name, mandal_name,
                    village_name, request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void CMexportExcel(ArrayList dataList, ArrayList headerList, String reportHeading, String reportName, String heading, String district_name,
            String mandal_name, String village_name, HttpServletRequest request, HttpServletResponse response) {
        OutputStream out = null;
        try {

            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=" + reportName + ".xls");

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

            WritableCellFormat textFormat = new WritableCellFormat();
            textFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            textFormat.setAlignment(Alignment.LEFT);
            textFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);


            if (dataList != null) {
                if (dataList.size() == 0) {
                    s.addCell(new Label(0, 0, "No Data Found................"));
                } else {
                    s.addCell(new Label(0, 0, reportHeading, cell));
                    s.mergeCells(0, 0, 9, 0);
                    s.addCell(new Label(0, 1, heading, cell));
                    s.mergeCells(0, 1, 9, 1);
                    s.addCell(new Label(2, 2, "PwDs - ASSESSMENT STATUS", cell));
                    s.mergeCells(2, 2, 9, 2);
                    s.addCell(new Label(0, 2, "S No", cell));
                    s.mergeCells(0, 2, 0, 3);
                    setWidth(s, 0, 20);
                    if (village_name != null && !village_name.equals("ALL")) {
                        s.addCell(new Label(0, 2, "Habitation", cell));
                    } else if (mandal_name != null && !mandal_name.equals("ALL")) {
                        s.addCell(new Label(0, 2, "Village", cell));
                    } else if (district_name != null && !district_name.equals("ALL")) {
                        s.addCell(new Label(0, 2, "Mandal", cell));
                    } else {

                        s.addCell(new Label(0, 2, "District", cell));
                    }
                    setWidth(s, 1, 20);
                    s.mergeCells(1, 2, 1, 3);

                    addHeaders(s, headerList, cell);
                }

                int x = 5;
                int stat = 1;

                for (int i = 0; i < dataList.size(); i++) {
                    int j = 0;
                    ReportDTO reportDetailsDTO =
                            new ReportDTO();
                    reportDetailsDTO = (ReportDTO) dataList.get(i);


                    // Total
                    if (i == dataList.size() - 1) {
                        j = 0;


                        s.addCell(new Label(j++, x, "", cell));
                        s.addCell(new Label(j++, x, "Total", cell));
                        s.mergeCells(0, 3, 1, 3);
                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalAppliedCM() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalAssessedCM() + " (" + reportDetailsDTO.getTotalassessedPercentage() + ")", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalrejectedCM() + " (" + reportDetailsDTO.getTotalrejectedPercentage() + ")", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotaleligibleCM() + " (" + reportDetailsDTO.getTotaleligiblePercentage() + ")", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotaleligiblebelow80() + " (" + reportDetailsDTO.getTotaleligiblebelow80Percentage() + ")", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotaleligibleabove80() + " (" + reportDetailsDTO.getTotaleligibleabove80Percentage() + ")", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotaltotalCM() + " (" + reportDetailsDTO.getTotalpensionerPercentage() + ")", cell));

                        s.addCell(new Label(j++, x, reportDetailsDTO.getShowDifference() + " (" + reportDetailsDTO.getCertificatePercentage() + ")" + "", cell));

                    }
                    //End
                }
                int cc = dataList.size() - 1;

                x = x + 1;
                for (int i = 0; i < dataList.size() - 1; i++, x++) {
                    int j = 0;
                    ReportDTO reportDetailsDTO =
                            new ReportDTO();
                    reportDetailsDTO = (ReportDTO) dataList.get(i);



                    if (stat == 1) {
                        s.addCell(new Label(0, 4, "1", cell));
                        s.addCell(new Label(1, 4, "2", cell));
                        s.addCell(new Label(2, 4, "3", cell));
                        s.addCell(new Label(3, 4, "4", cell));
                        s.addCell(new Label(4, 4, "5", cell));
                        s.addCell(new Label(5, 4, "6", cell));
                        s.addCell(new Label(6, 4, "7", cell));
                        s.addCell(new Label(7, 4, "8", cell));
                        s.addCell(new Label(8, 4, "9", cell));
                        s.addCell(new Label(9, 4, "10", cell));

                        stat++;
                    }


                    s.addCell(new Label(j++, x, i + 1 + "", cellFormat));
                    if (village_name != null && !village_name.equals("ALL")) {
                        s.addCell(new Label(j++, x, reportDetailsDTO.getHabitation_name() + "", textFormat));
                    } else if (mandal_name != null && !mandal_name.equals("ALL")) {
                        s.addCell(new Label(j++, x, reportDetailsDTO.getVillage_name() + "", textFormat));
                    } else if (district_name != null && !district_name.equals("ALL")) {
                        s.addCell(new Label(j++, x, reportDetailsDTO.getMandal_name() + "", textFormat));
                    } else {
                        s.addCell(new Label(j++, x, reportDetailsDTO.getDistrict_name() + "", textFormat));
                    }
                    s.addCell(new Label(j++, x, reportDetailsDTO.getTotalAppliedCM() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getTotalAssessedCM() + " (" + reportDetailsDTO.getAssessedPercentage() + ")", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getRejectedCM() + " (" + reportDetailsDTO.getRejectedPercentage() + ")", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getEligibleCM() + " (" + reportDetailsDTO.getEligiblePercentage() + ")", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getEligiblebelow80() + " (" + reportDetailsDTO.getEligiblebelow80Percentage() + ")", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getEligibleabove80() + " (" + reportDetailsDTO.getEligibleabove80Percentage() + ")", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getTotalCM() + " (" + reportDetailsDTO.getPensionerPercentage() + ")", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getShowDifference() + " (" + reportDetailsDTO.getCertificatePercentage() + ")", cellFormat));
                    if (i == (50000) * page) {
                        int sheet = ++page;
                        s = w.createSheet("Sheet" + sheet, sheet - 1);
                        j = 0;
                        x = 2;
                        addHeaders(s, headerList, cell);
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

    public static void exportExcel(ArrayList dataList, ArrayList headerList, String reportHeading, String reportName,
            String heading, String district_name,
            String mandal_name, String village_name, HttpServletRequest request, HttpServletResponse response) {
        OutputStream out = null;
        try {
            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=" + reportName + ".xls");

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
            WritableCellFormat cell111 = new WritableCellFormat(bold);
            cell111.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell111.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell111.setAlignment(Alignment.CENTRE);
            cell111.setBackground(Colour.GRAY_25);

            cell111.setVerticalAlignment(VerticalAlignment.CENTRE);
            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);
//            if (dataList != null) {
//                if (dataList.size() == 0) {
//                    s.addCell(new Label(0, 0, "No Data Found................"));
//                } else {
//                    s.addCell(new Label(0, 0, reportHeading, cell));
//                    s.mergeCells(0, 0, 12, 0);
//                    s.addCell(new Label(0, 1, heading, cell));
//                    s.mergeCells(0, 1, 12, 1);
//                    s.addCell(new Label(2, 2, "PwDs - ASSESSMENT STATUS", cell));
//                    s.mergeCells(2, 2, 5, 2);
//
//                   // s.mergeCells(6, 2, 12, 2);
//                    s.addCell(new Label(0, 2, "S No", cell));
//                    s.mergeCells(0, 2, 0, 3);
//                    setWidth(s, 0, 20);
//                    if (village_name != null && !village_name.equals("ALL")) {
//                        s.addCell(new Label(1, 2, "Habitation", cell));
//                    } else if (mandal_name != null && !mandal_name.equals("ALL")) {
//                        s.addCell(new Label(1, 2, "Village", cell));
//                    } else if (district_name != null && !district_name.equals("ALL")) {
//                        s.addCell(new Label(1, 2, "Mandal", cell));
//                    } else {
//                        s.addCell(new Label(1, 2, "District", cell));
//                    }
//                    setWidth(s, 1, 20);
//                    s.mergeCells(1, 2, 1, 3);
//
//                    addHeaders(s, headerList, cell);
//                }
//
//                int x = 5;
//                int stat = 1;
//
//                for (int i = 0; i < dataList.size(); i++) {
//                    int j = 0;
//                    ReportDTO reportDetailsDTO =
//                            new ReportDTO();
//                    reportDetailsDTO = (ReportDTO) dataList.get(i);
//
//
//                    // Total
////                    if (i == dataList.size() - 1) {
////                        j = 0;
////
////
////                        s.addCell(new Label(j++, x, "", cell));
////                        s.addCell(new Label(j++, x, "Total", cell));
////                        s.mergeCells(0, 3, 1, 3);
////                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalAppliedCM() + "", cell));
////                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalAssessedCM() + "", cell));
////                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalrejectedCM() + "", cell));
////                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotaleligibleCM() + "", cell));
////                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalohCM() + "", cell));
////                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalviCM() + "", cell));
////                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalhiCM() + "", cell));
////                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalmrCM() + "", cell));
////                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalmiCM() + "", cell));
////                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalmdCM() + "", cell));
////                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotaltotalCM() + "", cell));
////                        s.addCell(new Label(j++, x, reportDetailsDTO.getCertificatePercentage()+"%"+ "", cell));
////
////                    }
//                    //End
//                }
//                x = x + 1;
//                for (int i = 0; i < dataList.size() - 1; i++, x++) {
//                    int j = 0;
//                    ReportDTO reportDetailsDTO =
//                            new ReportDTO();
//                    reportDetailsDTO = (ReportDTO) dataList.get(i);
//
//
//
//                    if (stat == 1) {
//                        s.addCell(new Label(0, 4, "1", cell));
//                        s.addCell(new Label(1, 4, "2", cell));
//                        s.addCell(new Label(2, 4, "3", cell));
//                        s.addCell(new Label(3, 4, "4", cell));
////                        s.addCell(new Label(4, 4, "5", cell));
////                        s.addCell(new Label(5, 4, "6", cell));
////                        s.addCell(new Label(6, 4, "7", cell));
////                        s.addCell(new Label(7, 4, "8", cell));
////                        s.addCell(new Label(8, 4, "9", cell));
////                        s.addCell(new Label(9, 4, "10", cell));
////                        s.addCell(new Label(10, 4, "11", cell));
////                        s.addCell(new Label(11, 4, "12", cell));
////                        s.addCell(new Label(12, 4, "13", cell));
////                        s.addCell(new Label(13, 4, "14", cell));
//                        stat++;
//                    }
//
//
//                    s.addCell(new Label(j++, x, i + 1 + "", cellFormat));
//                    if (village_name != null && !village_name.equals("ALL")) {
//                        s.addCell(new Label(j++, x, reportDetailsDTO.getHabitation_name() + "", cellFormat));
//                    } else if (mandal_name != null && !mandal_name.equals("ALL")) {
//                        s.addCell(new Label(j++, x, reportDetailsDTO.getVillage_name() + "", cellFormat));
//                    } else if (district_name != null && !district_name.equals("ALL")) {
//                        s.addCell(new Label(j++, x, reportDetailsDTO.getMandal_name() + "", cellFormat));
//                    } else {
//                        s.addCell(new Label(j++, x, reportDetailsDTO.getDistrict_name() + "", cellFormat));
//                    }
////                    s.addCell(new Label(j++, x, reportDetailsDTO.getTotalAppliedCM() + "", cellFormat));
////                    s.addCell(new Label(j++, x, reportDetailsDTO.getTotalAssessedCM() + "", cellFormat));
////                    s.addCell(new Label(j++, x, reportDetailsDTO.getRejectedCM() + "", cellFormat));
////                    s.addCell(new Label(j++, x, reportDetailsDTO.getEligibleCM() + "", cellFormat));
////                    s.addCell(new Label(j++, x, reportDetailsDTO.getOhCM() + "", cellFormat));
////                    s.addCell(new Label(j++, x, reportDetailsDTO.getViCM() + "", cellFormat));
////                    s.addCell(new Label(j++, x, reportDetailsDTO.getHiCM() + "", cellFormat));
////                    s.addCell(new Label(j++, x, reportDetailsDTO.getMrCM() + "", cellFormat));
////                    s.addCell(new Label(j++, x, reportDetailsDTO.getMiCM() + "", cellFormat));
////                    s.addCell(new Label(j++, x, reportDetailsDTO.getMdCM() + "", cellFormat));
//
////                    s.addCell(new Label(j++, x, Integer.parseInt(reportDetailsDTO.getOhCM().replaceAll(",", ""))
////                            + Integer.parseInt(reportDetailsDTO.getViCM().replaceAll(",", ""))
////                            + Integer.parseInt(reportDetailsDTO.getHiCM().replaceAll(",", ""))
////                            + Integer.parseInt(reportDetailsDTO.getMrCM().replaceAll(",", ""))
////                            + Integer.parseInt(reportDetailsDTO.getMiCM().replaceAll(",", ""))
////                            + Integer.parseInt(reportDetailsDTO.getMdCM().replaceAll(",", "")) + "", cellFormat));
//                    s.addCell(new Label(j++, x, reportDetailsDTO.getCertificatePercentage() + "%", cellFormat));
//                    if (i == (50000) * page) {
//                        int sheet = ++page;
//                        s = w.createSheet("Sheet" + sheet, sheet - 1);
//                        j = 0;
//                        x = 2;
//                        addHeaders(s, headerList, cell);
//                    }
//
//                }
//            }


            if (dataList != null) {
                if (dataList.size() == 0) {
                    s.addCell(new Label(0, 0, "No Data Found................"));
                } else {
                    s.addCell(new Label(0, 0, reportHeading, cell111));
                    s.mergeCells(0, 0, 24, 0);


                    s.addCell(new Label(0, 2, "S No", cell111));
                    s.mergeCells(0, 2, 0, 3);

                    //s.addCell(new Label(0, 1, heading, cell));
                    if (village_name != null && !village_name.equals("ALL")) {
                        s.addCell(new Label(1, 2, "Habitation", cell111));
                    } else if (mandal_name != null && !mandal_name.equals("ALL")) {
                        s.addCell(new Label(1, 2, "Village", cell111));
                    } else if (district_name != null && !district_name.equals("ALL")) {
                        s.addCell(new Label(1, 2, "Mandal", cell111));
                    } else {
                        s.addCell(new Label(1, 2, "District", cell111));
                    }
                    s.mergeCells(1, 2, 1, 3);

                    s.addCell(new Label(2, 2, "Total Applied", cell111));
                    s.mergeCells(2, 2, 2, 3);

                    s.addCell(new Label(3, 2, "Total", cell111));
                    s.mergeCells(3, 2, 5, 2);

                    s.addCell(new Label(6, 2, "Locomotor/OH", cell111));
                    s.mergeCells(6, 2, 8, 2);

                    s.addCell(new Label(9, 2, "Visual Impairment", cell111));
                    s.mergeCells(9, 2, 11, 2);

                    s.addCell(new Label(12, 2, "Hearing Impairment", cell111));
                    s.mergeCells(12, 2, 14, 2);

                    s.addCell(new Label(15, 2, "Mental Retardation", cell111));
                    s.mergeCells(15, 2, 17, 2);

                    s.addCell(new Label(18, 2, "Mental Illness ", cell111));
                    s.mergeCells(18, 2, 20, 2);
                    s.addCell(new Label(21, 2, "Multiple Disability ", cell111));
                    s.mergeCells(21, 2, 23, 2);

                    s.addCell(new Label(24, 2, "To be Assessed", cell111));
                    s.mergeCells(24, 2, 24, 3);



                    addHeaders(s, headerList, cell111, "Disability");
                }

                int x = 4;
                
                for (int i = 0; i < dataList.size(); i++, x++) {
                    int j = 0;
                    ReportDTO reportDetailsDTO =
                            new ReportDTO();
                    reportDetailsDTO = (ReportDTO) dataList.get(i);

                    if (i == dataList.size() - 1) {
                        j = 0;
                       // x = x + 1;
//                        s.addCell(new Label(j++, x, "", cell));
                        s.addCell(new Label(j++, x, "", cell));
                        s.addCell(new Label(j++, x, "Total", cell));

                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalAppliedCM() + "", cell));

                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalAssessedCM() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotaleligibleCM() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalrejectedCM() + "", cell));


                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalOhAssessed() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalOhEligible() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalOhRejected() + "", cell));

                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalViAssessed() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalViEligible() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalViRejected() + "", cell));

                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalHiAssessed() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalHiEligible() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalHiRejected() + "", cell));

                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalMrAssessed() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalMrEligible() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalMrRejected() + "", cell));

                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalMiAssessed() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalMiEligible() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalMiRejected() + "", cell));

                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalMdAssessed() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalMdEligible() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandtotalMdRejected() + "", cell));

                        s.addCell(new Label(j++, x, reportDetailsDTO.getGrandTotalShowDifference() + "(" + reportDetailsDTO.getGrandTotalCertificate() + ")", cellFormat));




                    } // Total


                    else{
                    
                    s.addCell(new Label(j++, x, i + 1 + "", cellFormat));

                    if (village_name != null && !village_name.equals("ALL")) {
                        s.addCell(new Label(j++, x, reportDetailsDTO.getHabitation_name() + "", cellFormat));
                    } else if (mandal_name != null && !mandal_name.equals("ALL")) {
                        s.addCell(new Label(j++, x, reportDetailsDTO.getVillage_name() + "", cellFormat));
                    } else if (district_name != null && !district_name.equals("ALL")) {
                        s.addCell(new Label(j++, x, reportDetailsDTO.getMandal_name() + "", cellFormat));
                    } else {
                        s.addCell(new Label(j++, x, reportDetailsDTO.getDistrict_name() + "", cellFormat));
                    }


                    s.addCell(new Label(j++, x, reportDetailsDTO.getTotalAppliedCM() + "", cellFormat));

                    s.addCell(new Label(j++, x, reportDetailsDTO.getTotalAssessedCM() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getEligibleCM() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getRejectedCM() + "", cellFormat));

                    s.addCell(new Label(j++, x, reportDetailsDTO.getOhassessed() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getOheligible() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getOhrejected() + "", cellFormat));

                    s.addCell(new Label(j++, x, reportDetailsDTO.getViassessed() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getVieligible() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getVirejected() + "", cellFormat));

                    s.addCell(new Label(j++, x, reportDetailsDTO.getHiassessed() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getHieligible() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getHirejected() + "", cellFormat));

                    s.addCell(new Label(j++, x, reportDetailsDTO.getMrassessed() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getMreligible() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getMrrejected() + "", cellFormat));

                    s.addCell(new Label(j++, x, reportDetailsDTO.getMiassessed() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getMieligible() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getMirejected() + "", cellFormat));

                    s.addCell(new Label(j++, x, reportDetailsDTO.getMdassessed() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getMdeligible() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getMdrejected() + "", cellFormat));


                    s.addCell(new Label(j++, x, reportDetailsDTO.getShowDifference() + "(" + reportDetailsDTO.getCertificatePercentage() + ")", cellFormat));


                    reportDetailsDTO.setGrandTotalShowDifference(reportDetailsDTO.getShowDifference());

                    reportDetailsDTO.setGrandTotalCertificate(reportDetailsDTO.getCertificatePercentage());

                    if (i == (50000) * page) {
                        int sheet = ++page;
                        s = w.createSheet("Sheet" + sheet, sheet - 1);
                        j = 0;
                        x = 2;
                        //addHeaders(s, headerList, cell, "camp");
                    }
 //End
                   
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

    public static void setWidth(WritableSheet s, int col, int widthInChars) {

        s.setColumnView(col, widthInChars);
    }

    public static void addHeaders(WritableSheet s, ArrayList list, WritableCellFormat cell) {

        for (int x = 0; x < list.size(); x++) {
            try {
                s.addCell(new Label(x + 2, 3, list.get(x).toString(), cell));

            } catch (WriteException ex) {
                ex.printStackTrace();
            }
            setWidth(s, x + 2, 20);
        }
    }

    public ActionForward getIndividualDashBoardDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        ReportForm formBean = (ReportForm) form;
        ArrayList individualReport = new ArrayList();
        ReportDTO reportDTO = new ReportDTO();
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String habCode = null;
        String district_name = "ALL";
        String mandal_name = "ALL";
        String village_name = "ALL";
        ArrayList headerList = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            ReportDAO reportDAO = new ReportDAO();
            if (request.getParameter("district_id") != null) {
                districtId = request.getParameter("district_id");
                reportDTO.setDistrict_id(districtId);
                request.setAttribute("district", "district");
            }
            if (request.getParameter("mandal_id") != null) {
                mandalId = request.getParameter("mandal_id");
                reportDTO.setMandal_id(mandalId);
                request.setAttribute("mandal", "mandal");

            }
            if (request.getParameter("village_id") != null) {
                villageId = request.getParameter("village_id");
                reportDTO.setVillage_id(villageId);
                request.setAttribute("village", "village");
            }
            if (request.getParameter("habitation_Code") != null) {
                habCode = request.getParameter("habitation_Code");
                reportDTO.setHabitation_id(habCode);
                request.setAttribute("habitation", "habitation");
            }



            if (request.getParameter("district_name") != null) {
                district_name = request.getParameter("district_name");
            }
            if (request.getParameter("mandal_name") != null) {
                mandal_name = request.getParameter("mandal_name");
            }
            if (request.getParameter("village_name") != null) {
                village_name = request.getParameter("village_name");
            }



            if (request.getParameter("flag") != null) {
                reportDTO.setFlag(request.getParameter("flag"));
            }
            //individual Details Report
            individualReport = reportDAO.getIndividualDashBoardDetails(ds, reportDTO);

            if (individualReport != null && individualReport.size() > 0) {
                request.setAttribute("stateReport", individualReport);
            } else {

                request.setAttribute("noDatas", "SADAREM ASSESSMENT,Pensions Percentage  individual Details Not AVailable! ");


            }

            request.setAttribute("district_name", district_name);
            request.setAttribute("mandal_name", mandal_name);
            request.setAttribute("village_name", village_name);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("excel");
    }

    public ActionForward getIndividualDashBoardDetailsExcel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMException, SQLException {

        DataSource ds = null;
        ReportForm formBean = (ReportForm) form;
        ArrayList individualReport = new ArrayList();
        ReportDTO reportDTO = new ReportDTO();
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String habCode = null;
        String district_name = "ALL";
        String mandal_name = "ALL";
        String village_name = "ALL";
        ArrayList headerList = new ArrayList();
        try {
            HttpSession session = request.getSession();
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            ReportDAO reportDAO = new ReportDAO();
            if (request.getParameter("district_id") != null) {
                districtId = request.getParameter("district_id");
                reportDTO.setDistrict_id(districtId);
                request.setAttribute("district", "district");
            }
            if (request.getParameter("mandal_id") != null) {
                mandalId = request.getParameter("mandal_id");
                reportDTO.setMandal_id(mandalId);
                request.setAttribute("mandal", "mandal");

            }
            if (request.getParameter("village_id") != null) {
                villageId = request.getParameter("village_id");
                reportDTO.setVillage_id(villageId);
                request.setAttribute("village", "village");
            }
            if (request.getParameter("habitation_Code") != null) {
                habCode = request.getParameter("habitation_Code");
                reportDTO.setHabitation_id(habCode);
                request.setAttribute("habitation", "habitation");
            }



            if (request.getParameter("district_name") != null) {
                district_name = request.getParameter("district_name");
            }
            if (request.getParameter("mandal_name") != null) {
                mandal_name = request.getParameter("mandal_name");
            }
            if (request.getParameter("village_name") != null) {
                village_name = request.getParameter("village_name");
            }



            if (request.getParameter("flag") != null) {
                reportDTO.setFlag(request.getParameter("flag"));
            }
            //individual Details Report
//            individualReport = reportDAO.getIndividualDashBoardDetails(ds, reportDTO);
//            if (individualReport != null && individualReport.size() > 0) {
//                request.setAttribute("stateReport", individualReport);
//            }

            if (session.getAttribute("stateReport") != null) {
                individualReport = (ArrayList) session.getAttribute("stateReport");
                request.setAttribute("stateReport", individualReport);
            } else {
                request.setAttribute("noDatas", "SADAREM ASSESSMENT,Pensions Percentage  individual Details Not AVailable! ");
            }
            request.setAttribute("district_name", district_name);
            request.setAttribute("mandal_name", mandal_name);
            request.setAttribute("village_name", village_name);


            headerList.add("S.No");
            headerList.add("Person Name");
            headerList.add("Relation Name");
            headerList.add("SADAREM ID");
            headerList.add("Pension ID");
            headerList.add("HabCode");
            headerList.add("RationCard No");
            headerList.add("Age");
            headerList.add("Gender");
            headerList.add("Education");
            headerList.add("Caste");
            headerList.add("Type Of Disability");
            headerList.add("Percentage");
            headerList.add("House No.");
            headerList.add("Habitation");
            headerList.add("Village");
            headerList.add("Mandal");
            headerList.add("District");
            headerList.add("Phone No.");
            headerList.add("Assessment Status");
            headerList.add("Date");
            ArrayList keyList = new ArrayList();
            keyList.add("name");
            keyList.add("count");

            individualExportExcel(individualReport, headerList, keyList, "Individual Assessment Status Details", district_name, mandal_name, village_name, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void individualExportExcel(ArrayList dataList, ArrayList headerList, ArrayList keyList, String reportHeading, String distrctName, String mandalName, String villageName, HttpServletRequest request, HttpServletResponse response) {

        OutputStream out = null;
        try {

            response.setContentType("application/vnd.ms-excel");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());
            WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat cell111 = new WritableCellFormat(bold);
            cell111.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell111.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell111.setAlignment(Alignment.CENTRE);
            cell111.setBackground(Colour.GRAY_25);

            cell111.setVerticalAlignment(VerticalAlignment.CENTRE);
            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);
            if (dataList.size() == 0) {
                s.addCell(new Label(0, 0, "No Data Found................"));

            } else {

                //mergeCells(int col1, int row1, int col2, int row2)
                reportHeading = "Assessment Status Report" + " Distrct :" + distrctName + " Mandal :" + mandalName + " Village :" + villageName;

                s.addCell(new Label(0, 0, reportHeading,cell111));
                s.mergeCells(0, 0,20, 0);
                addHeaders(s, headerList);
                s.mergeCells(0, 0, 0, 0);
                s.mergeCells(1, 0, 15, 0);

                addHeaders(s, headerList);
            }
            int x = 2;
            for (int i = 0; i < dataList.size(); i++, x++) {
                int j = 0;
                Map m = (Map) dataList.get(i);

                s.addCell(new Label(j++, x, i + 1 + ""));
                s.addCell(new Label(j++, x, m.get("personName").toString()));
                s.addCell(new Label(j++, x, m.get("relationName").toString()));
                s.addCell(new Label(j++, x, m.get("SADAREMCODE").toString()));

                s.addCell(new Label(j++, x, m.get("pensionId").toString()));
                s.addCell(new Label(j++, x, m.get("habCode").toString()));
                s.addCell(new Label(j++, x, m.get("rationCardNo").toString()));
                s.addCell(new Label(j++, x, m.get("age").toString()));
                s.addCell(new Label(j++, x, m.get("gender").toString()));
                s.addCell(new Label(j++, x, m.get("education").toString()));
                s.addCell(new Label(j++, x, m.get("caste").toString()));
                s.addCell(new Label(j++, x, m.get("typeOfDisability").toString()));
                s.addCell(new Label(j++, x, m.get("percentage").toString()));
                s.addCell(new Label(j++, x, m.get("houseNo").toString()));
                s.addCell(new Label(j++, x, m.get("habitationName").toString()));
                s.addCell(new Label(j++, x, m.get("villageName").toString()));
                s.addCell(new Label(j++, x, m.get("mandalName").toString()));
                s.addCell(new Label(j++, x, m.get("districtName").toString()));
                s.addCell(new Label(j++, x, m.get("phoneNo").toString()));
                s.addCell(new Label(j++, x, m.get("assessmentStatus").toString()));
                s.addCell(new Label(j++, x, m.get("updatedDate").toString()));

                if (i == (30000) * page) {
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

    public static void addHeaders(WritableSheet s, ArrayList list) {

    	 WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
    	 WritableCellFormat cell111 = new WritableCellFormat(bold);
    	 try{
         cell111.setBorder(Border.ALL, BorderLineStyle.THIN);
         cell111.setBorder(Border.ALL, BorderLineStyle.THIN);
         cell111.setAlignment(Alignment.CENTRE);
         cell111.setBackground(Colour.GRAY_25);

         cell111.setVerticalAlignment(VerticalAlignment.CENTRE);
         
        for (int x = 0; x < list.size(); x++) {
            try {
                s.addCell(new Label(x, 1, list.get(x).toString(),cell111));
            } catch (WriteException ex) {
                ex.printStackTrace();
            }
            setWidth(s, x, 20);
        }
    	 }catch(Exception e){
    		 System.out.println(e);
    	 }
    }

    public ActionForward gerDashBoardReport6(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMException, SQLException {
        DataSource ds = null;
        ReportForm formBean = (ReportForm) form;
        ArrayList stateReport = new ArrayList();
        ReportDTO reportDTO = new ReportDTO();
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String district_name = "ALL";
        String mandal_name = "ALL";
        String village_name = "ALL";
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            ReportDAO reportDAO = new ReportDAO();
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


            if (request.getParameter("district_name") != null) {
                district_name = request.getParameter("district_name");
            }
            if (request.getParameter("mandal_name") != null) {
                mandal_name = request.getParameter("mandal_name");
            }
            if (request.getParameter("village_name") != null) {
                village_name = request.getParameter("village_name");
            }
            formBean.setDistrict_id(districtId);
            formBean.setMandal_id(mandalId);
            formBean.setVillage_id(villageId);
            BeanUtils.copyProperties(reportDTO, formBean);


            stateReport = reportDAO.getCMReport(ds, reportDTO);

            if (stateReport != null && stateReport.size() > 0) {
                request.setAttribute("stateReport", stateReport);
            } else {
                request.setAttribute("noData", "SADAREM Assessment And Pension Status Report Details Not AVailable! ");
            }
            if (districtId.equals("0")) {
                request.setAttribute("district", "district");

            } else if (mandalId.equals("0")) {
                request.setAttribute("mandal", "mandal");

            } else if (villageId.equals("0")) {
                request.setAttribute("village", "village");

            } else {
                request.setAttribute("habitation", "habitation");
            }
            request.setAttribute("district_name", district_name);
            request.setAttribute("mandal_name", mandal_name);
            request.setAttribute("village_name", village_name);
            request.setAttribute("heading", "District : " + district_name + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                    + "Mandal :" + mandal_name + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Village :" + village_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("report6");
    }

    public static void addHeaders(WritableSheet s, ArrayList list, WritableCellFormat cell, String flag) {

        for (int x = 0; x < list.size(); x++) {
            try {
                if (flag.equals("individualList")) {
                    s.addCell(new Label(x + 1, 3, list.get(x).toString(), cell));
                } else if (flag.equals("Disability")) {
                    s.addCell(new Label(x + 2, 3, list.get(x).toString(), cell));
                } else if (flag.equals("mandal")) {
                    s.addCell(new Label(x + 2, 3, list.get(x).toString(), cell));
                } else {
                    s.addCell(new Label(x + 3, 3, list.get(x).toString(), cell));
                }

            } catch (WriteException ex) {
                ex.printStackTrace();
            }
            setWidth(s, x + 2, 20);
        }
    }
}
