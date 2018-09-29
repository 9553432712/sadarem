/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.bf.disability.common.CommonDAO;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.DailyDisabilityAndPercentageDAO;
import org.bf.disability.dao.ReportDAO;
import org.bf.disability.dto.ReportDTO;
import org.bf.disability.form.DashboardReportsForm;
import org.bf.disability.service.CampDailyReportService;
import org.bf.disability.service.DashboardReportsService;
import org.bf.disability.servicefactory.CampDailyReportServiceFactory;
import org.bf.disability.servicefactory.DashboardReportsServiceFactory;
import jxl.Workbook;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
import org.bf.disability.dao.TerritoryDAO;

/**
 *
 * @author Administrator
 */
public class DashboardReportsAction extends DispatchAction {

    private String target = "success";

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMException, SQLException {
        DataSource ds = null;
        DashboardReportsForm formBean = (DashboardReportsForm) form;
        CommonDAO commonDAO = new CommonDAO();
        ArrayList yearList = new ArrayList();
        ArrayList monthList = new ArrayList();
        ArrayList campList = new ArrayList();
        ArrayList financialYearList = new ArrayList();
        ArrayList districtList = new ArrayList();
        String districtId = null;
        String givenFromDate = "";
        String givenToDate = "";
        HttpSession session = request.getSession();
        try {
            ReportDTO reportDTO = new ReportDTO();
            ReportDAO reportDAO = new ReportDAO();
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            CampDailyReportService campDailyReportService =
                    CampDailyReportServiceFactory.getcampDailyReportServiceImpl();

            DashboardReportsService dashboardReportsService =
                    DashboardReportsServiceFactory.getDashboardReportsServiceImpl();

            TerritoryDAO territoryDAO = new TerritoryDAO();

            if ((String) session.getAttribute("districtId") != null) {
                districtId = (String) session.getAttribute("districtId");
                formBean.setDistrict_id((String) session.getAttribute("districtId"));
            }
            yearList = commonDAO.getYears();
            if (formBean.getYear() != null && !formBean.getYear().equalsIgnoreCase("") && !formBean.getYear().equalsIgnoreCase("0")) {
                monthList = commonDAO.getMonths(Integer.parseInt(formBean.getYear()));
                formBean.setMonthList(monthList);
            }
            campList = campDailyReportService.getCampBasedOnLoginDetails(ds, districtId);
            financialYearList = commonDAO.getFinancialYears();
            request.setAttribute("typeOfSearchValue", formBean.getTypeOfSearch());
            request.setAttribute("reportTypeValue", formBean.getReportType());
            if (formBean.getReportType() == null) {
                BeanUtils.copyProperties(reportDTO, formBean);
                ArrayList reportList = new ArrayList();
                // reportList = reportDAO.collectorReport(ds, reportDTO);

                //BeanUtils.copyProperties(formBean, reportDTO);
                if (reportList.isEmpty()) {
                    request.setAttribute("msg", "No Records Found");
                } else {
                    request.setAttribute("stateReport", reportList);
                }

                request.setAttribute("camp", "camp");
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                //get current date time with Date()
                Date date = new Date();
                java.util.Date da = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(da);
                cal.add(Calendar.MONTH, -1);
                da = cal.getTime();
                givenFromDate = dateFormat.format(da);
                givenToDate = dateFormat.format(date);
                request.setAttribute("heading", "SADAREM Assessed and Pensioner Data : From " + givenFromDate + " To :" + givenToDate);
            }
            formBean.setYearList(yearList);
            formBean.setFinancialYearList(financialYearList);
            formBean.setCampList(campList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(target);
    }

    public ActionForward getResults(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMException, SQLException {
        DataSource ds = null;

        String districtName = null;

        DashboardReportsForm formBean = (DashboardReportsForm) form;

        DashboardReportsService dashboardReportsService =
                DashboardReportsServiceFactory.getDashboardReportsServiceImpl();
        request.setAttribute("typeOfSearchValue", formBean.getTypeOfSearch());
        request.setAttribute("reportTypeValue", formBean.getReportType());

        String camp = "";
        String mandal = "";
        String givenFromDate = "";
        String givenToDate = "";

        TerritoryDAO territoryDAO = new TerritoryDAO();
        try {
            CommonDAO commonDAO = new CommonDAO();
            DailyDisabilityAndPercentageDAO dailyDisabilityAndPercentageDAO = new DailyDisabilityAndPercentageDAO();
            HttpSession session = request.getSession();
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            //   if (formBean.getDistrict_id() != null && formBean.getDistrict_id().length() > 0) {
            //       formBean.setDistrict_id(formBean.getDistrict_id());
            //    } else {
            formBean.setDistrict_id((String) session.getAttribute("districtId"));
            //     }

            if (formBean.getReportType() != null && !formBean.getReportType().equalsIgnoreCase("")) {
                if (formBean.getReportType().equalsIgnoreCase("Camp")) {
                    camp = "0";
                } else if (formBean.getReportType().equalsIgnoreCase("Mandal")) {
                    mandal = "0";
                }

            }
            if (formBean.getFromdate() != null) {
                givenFromDate = formBean.getFromdate();
            }
            if (formBean.getTodate() != null) {
                givenToDate = formBean.getTodate();
            }
            ReportDTO reportDTO = new ReportDTO();
            ReportDAO reportDAO = new ReportDAO();
            BeanUtils.copyProperties(reportDTO, formBean);
            ArrayList reportList = new ArrayList();
            reportList = reportDAO.collectorReport(ds, reportDTO);


            if (reportList.isEmpty()) {
                request.setAttribute("msg", "No Records Found");
            } else {
                request.setAttribute("stateReport", reportList);
            }
            if (camp.equals("0")) {
                request.setAttribute("camp", "camp");

            } else if (mandal.equals("0")) {
                request.setAttribute("mandal", "mandal");

            }

            if (reportDTO.getTypeOfSearch() != null && reportDTO.getTypeOfSearch().equalsIgnoreCase("FinancialYear")) {
                String[] financialYear = reportDTO.getFinancialYear().split("-");
                int givenYear = Integer.parseInt(financialYear[0]);
                givenFromDate = "01/04/" + givenYear;
                givenToDate = dailyDisabilityAndPercentageDAO.getAssignedDate(givenYear + 1, ds, givenFromDate);

            } else if (reportDTO.getTypeOfSearch() != null && reportDTO.getTypeOfSearch().equalsIgnoreCase("Month")) {
                givenFromDate = commonDAO.getMonthStartDate(ds, reportDTO.getMonth(), reportDTO.getYear());
                givenToDate = commonDAO.getMonthEndtDate(ds, reportDTO.getMonth(), reportDTO.getYear());
            }

            districtName = territoryDAO.getDistrictsName(ds, formBean.getDistrict_id().toString());

            if (reportDTO.getTypeOfSearch().equalsIgnoreCase("Date")) {
                request.setAttribute("heading", reportDTO.getTypeOfSearch() + "," + reportDTO.getFromdate() + "," + reportDTO.getTodate() + "," + reportDTO.getReportType() + "," + districtName);
            } else if (reportDTO.getTypeOfSearch().equalsIgnoreCase("Month")) {
                request.setAttribute("heading", reportDTO.getTypeOfSearch() + "," + reportDTO.getYear() + "," + reportDTO.getMonth() + "," + reportDTO.getReportType() + "," + districtName);
            } else if (reportDTO.getTypeOfSearch().equalsIgnoreCase("FinancialYear")) {
                request.setAttribute("heading", reportDTO.getTypeOfSearch() + "," + reportDTO.getFinancialYear() + "," + reportDTO.getReportType() + "," + districtName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(target);
    }

   


    public ActionForward excel(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMException, SQLException {
        String district_name = "ALL";
        String mandal_name = "ALL";
        String village_name = "ALL";
        String heading = null;
        try {
            HttpSession session = request.getSession();
            ArrayList stateReport = new ArrayList();

            if (session.getAttribute("stateReport") != null) {
                stateReport = (ArrayList) session.getAttribute("stateReport");
            }

            ArrayList headerList = new ArrayList();


            if (request.getParameter("heading").split(",")[3].equals("Camp") || request.getParameter("heading").split(",")[2].equals("Camp")) {
                headerList.add("Total Applied\n(4)");
                headerList.add("Assessed\n(5)");
                headerList.add("Rejected\n(6)");
                headerList.add("Eligible (>40%)\n(5-6)\n(7)");
                headerList.add("Ortho\n(8)");
                headerList.add("Visual\n(9)");
                headerList.add("Speech & Hearing\n(10)");
                headerList.add("Mental Retardation\n(11)");
                headerList.add("Mental Illness\n(12)");
                headerList.add("Multiple Disability\n(13)");
                headerList.add("Total Pensioners\n(8+9+10+11+12)\n(13)");
                exportExcelCamp(stateReport, headerList, "SADAREM Assessed & Pension Status", "Assessment_Report", request.getParameter("heading"), request, response);
            } else {
                headerList.add("Total Applied\n(3)");
                headerList.add("Assessed\n(4)");
                headerList.add("Rejected\n(5)");
                headerList.add("Eligible (>40%)\n(4-5)\n(6)");
                headerList.add("Ortho\n(7)");
                headerList.add("Visual\n(8)");
                headerList.add("Speech & Hearing\n(9)");
                headerList.add("Mental Retardation\n(10)");
                headerList.add("Mental Illness\n(11)");
                headerList.add("Multiple Disability\n(12)");
                headerList.add("Total Pensioners\n(7+8+9+10+11+12)\n(13)");
                exportExcel(stateReport, headerList, "SADAREM Assessed & Pension Status", "Assessment_Report", request.getParameter("heading"), request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void exportExcel(ArrayList dataList, ArrayList headerList, String reportHeading, String reportName, String heading, HttpServletRequest request, HttpServletResponse response) {
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

            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);
            if (dataList != null) {
                if (dataList.size() == 0) {
                    s.addCell(new Label(0, 0, "No Data Found................"));
                } else {
                    s.addCell(new Label(0, 0, reportHeading, cell));
                    s.mergeCells(0, 0, 12, 0);


                    if (heading.split(",")[0].equals("Date")) {
                        s.addCell(new Label(0, 1, "Assessed Abstract Report Date From " + heading.split(",")[1] + " To " + heading.split(",")[2] + " :: District :" + heading.split(",")[4], cell));
                    } else if (heading.split(",")[0].equals("Month")) {
                        s.addCell(new Label(0, 1, "Assessed Abstract Report From the Month of " + getFullMonths(Integer.parseInt(heading.split(",")[2].toString())) + ", " + heading.split(",")[1] + " :: District :" + heading.split(",")[4], cell));
                    } else if (heading.split(",")[0].equals("FinancialYear")) {
                        s.addCell(new Label(0, 1, "Assessed Abstract Report For the Financial Year " + heading.split(",")[1] + " :: District :" + heading.split(",")[3], cell));
                    }

                    s.mergeCells(0, 1, 12, 1);
                    s.addCell(new Label(2, 2, "PwDs - ASSESSMENT STATUS", cell));
                    s.mergeCells(2, 2, 5, 2);
                    s.addCell(new Label(6, 2, "PwDs - PENSIONS COVERED", cell));
                    s.mergeCells(6, 2, 12, 2);
                    s.addCell(new Label(0, 2, "S No\n(1)", cell));
                    s.mergeCells(0, 2, 0, 3);
                    s.addCell(new Label(1, 2, "Mandal\n(2)", cell));
                    s.mergeCells(1, 2, 1, 3);

                    addHeaders(s, headerList, cell, "mandal");
                }

                int x = 4;
                int totalApplyed = 0;
                int asessed = 0;
                int rejected = 0;
                int eligible = 0;
                int ortho = 0;
                int visual = 0;
                int hearing = 0;
                int mr = 0;
                int mi = 0;
                int md = 0;
                int total = 0;
                for (int i = 0; i < dataList.size(); i++, x++) {
                    int j = 0;
                    ReportDTO reportDetailsDTO =
                            new ReportDTO();
                    reportDetailsDTO = (ReportDTO) dataList.get(i);

                    //Totals

                    totalApplyed = totalApplyed + reportDetailsDTO.getTotalApplied();
                    asessed = asessed + reportDetailsDTO.getTotalAssessed();
                    rejected = rejected + reportDetailsDTO.getRejected();
                    eligible = eligible + reportDetailsDTO.getEligible();
                    ortho = ortho + reportDetailsDTO.getOh();
                    visual = visual + reportDetailsDTO.getVi();
                    hearing = hearing + reportDetailsDTO.getHi();
                    mr = mr + reportDetailsDTO.getMr();
                    mi = mi + reportDetailsDTO.getMi();
                    md = md + reportDetailsDTO.getMd();
                    total = ortho + visual + hearing + mr + mi + md;

                    // End

                    s.addCell(new Label(j++, x, i + 1 + "", cellFormat));

                    s.addCell(new Label(j++, x, reportDetailsDTO.getMandal_name() + "", cellFormat));

                    s.addCell(new Label(j++, x, reportDetailsDTO.getTotalApplied() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getTotalAssessed() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getRejected() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getEligible() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getOh() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getVi() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getHi() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getMr() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getMi() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getMd() + "", cellFormat));

                    s.addCell(new Label(j++, x, 
                             reportDetailsDTO.getOh() + reportDetailsDTO.getVi() + reportDetailsDTO.getHi() + reportDetailsDTO.getMr() + reportDetailsDTO.getMi() + reportDetailsDTO.getMd() + "", cellFormat));

                    if (i == (50000) * page) {
                        int sheet = ++page;
                        s = w.createSheet("Sheet" + sheet, sheet - 1);
                        j = 0;
                        x = 2;
                        addHeaders(s, headerList, cell, "mandal");
                    }

                    // Total

                    if (i == dataList.size() - 1) {
                        j = 0;
                        x = x + 1;
                        s.addCell(new Label(j++, x, "", cell));
                        s.addCell(new Label(j++, x, "Total", cell));

                        s.addCell(new Label(j++, x, totalApplyed + "", cell));
                        s.addCell(new Label(j++, x, asessed + "", cell));
                        s.addCell(new Label(j++, x, rejected + "", cell));
                        s.addCell(new Label(j++, x, eligible + "", cell));
                        s.addCell(new Label(j++, x, ortho + "", cell));
                        s.addCell(new Label(j++, x, visual + "", cell));
                        s.addCell(new Label(j++, x, hearing + "", cell));
                        s.addCell(new Label(j++, x, mr + "", cell));
                        s.addCell(new Label(j++, x, mi + "", cell));
                        s.addCell(new Label(j++, x, md + "", cell));
                        s.addCell(new Label(j++, x, total + "", cell));

                    }

                    //End
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

    public static void exportExcelCamp(ArrayList dataList, ArrayList headerList, String reportHeading, String reportName, String heading, HttpServletRequest request, HttpServletResponse response) {
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

            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);
            if (dataList != null) {
                if (dataList.size() == 0) {
                    s.addCell(new Label(0, 0, "No Data Found................"));
                } else {
                    s.addCell(new Label(0, 0, reportHeading, cell));
                    s.mergeCells(0, 0, 15, 0);


                    if (heading.split(",")[0].equals("Date")) {
                        s.addCell(new Label(0, 1, "Assessed Abstract Report Date From " + heading.split(",")[1] + " To " + heading.split(",")[2] + " :: District :" + heading.split(",")[4], cell));
                    } else if (heading.split(",")[0].equals("Month")) {
                        s.addCell(new Label(0, 1, "Assessed Abstract Report From the Month of " + getFullMonths(Integer.parseInt(heading.split(",")[2].toString())) + ", " + heading.split(",")[1] + " :: District :" + heading.split(",")[4], cell));
                    } else if (heading.split(",")[0].equals("FinancialYear")) {
                        s.addCell(new Label(0, 1, "Assessed Abstract Report For the Financial Year " + heading.split(",")[1] + " :: District :" + heading.split(",")[3], cell));
                    }

                    s.mergeCells(0, 1, 15, 1);

                    s.addCell(new Label(0, 2, "S No\n(1)", cell));
                    s.mergeCells(0, 2, 0, 3);

                    s.addCell(new Label(1, 2, "Medical Board\n(2)", cell));
                    s.mergeCells(1, 2, 1, 3);

                    s.addCell(new Label(2, 2, "Medical Board Address\n(3)", cell));
                    s.mergeCells(2, 2, 2, 3);

                    s.addCell(new Label(3, 2, "PwDs - ASSESSMENT STATUS", cell));
                    s.mergeCells(3, 2, 6, 2);

                    s.addCell(new Label(7, 2, "PwDs - PENSIONS COVERED", cell));
                    s.mergeCells(7, 2, 15, 2);


                    addHeaders(s, headerList, cell, "camp");
                }

                int x = 4;
                int totalApplyed = 0;
                int asessed = 0;
                int rejected = 0;
                int eligible = 0;
                int ortho = 0;
                int visual = 0;
                int hearing = 0;
                int mr = 0;
                int mi = 0;
                int md = 0;
                int total = 0;
                for (int i = 0; i < dataList.size(); i++, x++) {
                    int j = 0;
                    ReportDTO reportDetailsDTO =
                            new ReportDTO();
                    reportDetailsDTO = (ReportDTO) dataList.get(i);

                    //Totals

                    totalApplyed = totalApplyed + reportDetailsDTO.getTotalApplied();
                    asessed = asessed + reportDetailsDTO.getTotalAssessed();
                    rejected = rejected + reportDetailsDTO.getRejected();
                    eligible = eligible + reportDetailsDTO.getEligible();
                    ortho = ortho + reportDetailsDTO.getOh();
                    visual = visual + reportDetailsDTO.getVi();
                    hearing = hearing + reportDetailsDTO.getHi();
                    mr = mr + reportDetailsDTO.getMr();
                    mi = mi + reportDetailsDTO.getMi();
                    md = md + reportDetailsDTO.getMd();
                    total = ortho + visual + hearing + mr + mi + md;

                    // End

                    s.addCell(new Label(j++, x, i + 1 + "", cellFormat));

                    s.addCell(new Label(j++, x, reportDetailsDTO.getCampName() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getCampVenue() + "", cellFormat));


                    s.addCell(new Label(j++, x, reportDetailsDTO.getTotalApplied() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getTotalAssessed() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getRejected() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getEligible() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getOh() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getVi() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getHi() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getMr() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getMi() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getMd() + "", cellFormat));

                    s.addCell(new Label(j++, x,  reportDetailsDTO.getOh() + reportDetailsDTO.getVi()
                            + reportDetailsDTO.getHi() + reportDetailsDTO.getMr() + reportDetailsDTO.getMi() + reportDetailsDTO.getMd() + "", cellFormat));

                    if (i == (50000) * page) {
                        int sheet = ++page;
                        s = w.createSheet("Sheet" + sheet, sheet - 1);
                        j = 0;
                        x = 2;
                        addHeaders(s, headerList, cell, "camp");
                    }

                    // Total

                    if (i == dataList.size() - 1) {
                        j = 0;
                        x = x + 1;
                        s.addCell(new Label(j++, x, "", cell));
                        s.addCell(new Label(j++, x, "", cell));
                        s.addCell(new Label(j++, x, "Total", cell));

                        s.addCell(new Label(j++, x, totalApplyed + "", cell));
                        s.addCell(new Label(j++, x, asessed + "", cell));
                        s.addCell(new Label(j++, x, rejected + "", cell));
                        s.addCell(new Label(j++, x, eligible + "", cell));
                        s.addCell(new Label(j++, x, ortho + "", cell));
                        s.addCell(new Label(j++, x, visual + "", cell));
                        s.addCell(new Label(j++, x, hearing + "", cell));
                        s.addCell(new Label(j++, x, mr + "", cell));
                        s.addCell(new Label(j++, x, mi + "", cell));
                        s.addCell(new Label(j++, x, md + "", cell));
                        s.addCell(new Label(j++, x, total + "", cell));

                    }

                    //End
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

    public static void addHeaders(WritableSheet s, ArrayList list, WritableCellFormat cell, String flag) {

        for (int x = 0; x < list.size(); x++) {
            try {
                if (flag.equals("mandal")) {
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

    public static String getFullMonths(int monthNo) {
        String month = null;
        if (monthNo == 1) {
            month = "January";
        } else if (monthNo == 2) {
            month = "February";
        } else if (monthNo == 3) {
            month = "March";
        } else if (monthNo == 4) {
            month = "April";
        } else if (monthNo == 5) {
            month = "May";
        } else if (monthNo == 6) {
            month = "June";
        } else if (monthNo == 7) {
            month = "July";
        } else if (monthNo == 8) {
            month = "August";
        } else if (monthNo == 9) {
            month = "September";
        } else if (monthNo == 10) {
            month = "October";
        } else if (monthNo == 11) {
            month = "November";
        } else if (monthNo == 12) {
            month = "December";
        }
        return month;
    }
}
