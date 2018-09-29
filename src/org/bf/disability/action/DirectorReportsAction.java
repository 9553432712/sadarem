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
import java.util.Map;

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

import org.bf.disability.dao.TerritoryDAO;

/**
 *
 * @author Administrator
 */
public class DirectorReportsAction extends DispatchAction {

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
        ArrayList disabilityList = new ArrayList();
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

            // Fetching the district for director login
            //  if (!session.getAttribute("roleId").toString().equals(CommonConstants.COLLECTORROLE)) {
            districtList = territoryDAO.getDistricts(ds);
            if (districtList.size() > 0) {
                formBean.setDistrictlist(districtList);
            }
            //   }

          //  if ((String) session.getAttribute("districtId") != null) {
             //   districtId = (String) session.getAttribute("districtId");
                //  formBean.setDistrict_id((String) session.getAttribute("districtId"));
         //   }
            yearList = commonDAO.getYears();
            if (formBean.getYear() != null && !formBean.getYear().equalsIgnoreCase("") && !formBean.getYear().equalsIgnoreCase("0")) {
                monthList = commonDAO.getMonths(Integer.parseInt(formBean.getYear()));
                //System.out.println("monthList"+monthList);
                formBean.setMonthList(monthList);
            }

            if (formBean.getDistrict_id() != null && formBean.getDistrict_id().length() > 0 && !formBean.getDistrict_id().equals("0")) {
                campList = campDailyReportService.getCampBasedOnLoginDetails(ds, formBean.getDistrict_id());

                if (campList != null && campList.size() > 0) {
                    formBean.setCampList(campList);
                }
                request.setAttribute("cmpStat", "cmpStat");
            }

            //campList = campDailyReportService.getCampBasedOnLoginDetails(ds, districtId);
            financialYearList = commonDAO.getFinancialYears();
            //disabilityList = territoryDAO.getDisabilityDetails(ds);
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

                //  request.setAttribute("camp", "camp");
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
                //request.setAttribute("heading", "SADAREM Assessed and Pensioner Data : From " + givenFromDate + " To :" + givenToDate);
            }
            formBean.setYearList(yearList);
            formBean.setFinancialYearList(financialYearList);
            formBean.setCampList(campList);
            formBean.setMonthList(monthList);
           
            if (formBean.getFromdate() == null) {
                formBean.setFromdate("01/01/2010");
                request.setAttribute("selFromDate", formBean.getFromdate());
                request.setAttribute("selToDate", formBean.getTodate());
            } else {
                formBean.setFromdate(formBean.getFromdate());
            }
            //
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(target);
    }

    public ActionForward getResultsForDirector(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMException, SQLException {
        DataSource ds = null;

        String districtName = null;
        ArrayList yearList = new ArrayList();
        ArrayList monthList = new ArrayList();
        DashboardReportsForm formBean = (DashboardReportsForm) form;

        DashboardReportsService dashboardReportsService =
                DashboardReportsServiceFactory.getDashboardReportsServiceImpl();

        request.setAttribute("typeOfSearchValue", request.getParameter("srchType"));
        request.setAttribute("reportTypeValue", formBean.getReportType());

        String camp = "";
        String district = "";
        String disability = "";
        String mandal = "";
        String givenFromDate = "";
        String givenToDate = "";

        TerritoryDAO territoryDAO = new TerritoryDAO();
        ReportDTO reportDTO = new ReportDTO();
        try {
            CommonDAO commonDAO = new CommonDAO();
            DailyDisabilityAndPercentageDAO dailyDisabilityAndPercentageDAO = new DailyDisabilityAndPercentageDAO();
            HttpSession session = request.getSession();
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            yearList = commonDAO.getYears();
            if (formBean.getYear() != null && !formBean.getYear().equalsIgnoreCase("") && !formBean.getYear().equalsIgnoreCase("0")) {
                monthList = commonDAO.getMonths(Integer.parseInt(formBean.getYear()));
                formBean.setMonthList(monthList);
            }

            if (formBean.getReportType() != null && !formBean.getReportType().equalsIgnoreCase("")) {

                if (formBean.getReportType().equalsIgnoreCase("Camp")) {
                    camp = "0";
                } else if (formBean.getReportType().equalsIgnoreCase("District")) {
                    district = "0";
                } else if (formBean.getReportType().equalsIgnoreCase("Disability")) {
                    disability = "0";
                }
            }
            if (request.getParameter("repType") != null && request.getParameter("repType") != "" && request.getParameter("repType").equals("Disability")) {
                formBean.setTypeOfSearch(request.getParameter("srchType"));
                formBean.setFinancialYear(request.getParameter("finYear"));
                formBean.setReportType(request.getParameter("repType"));
                formBean.setFromdate(request.getParameter("frDate"));
                formBean.setTodate(request.getParameter("tDate"));
                formBean.setReportType(request.getParameter("repType"));
                request.setAttribute("disabilitywise", "disabilitywise");

                formBean.setDistrict_name(request.getParameter("districtname"));
                formBean.setVillage_name(request.getParameter("villagename"));
                formBean.setMandal_name(request.getParameter("mandalname"));
                if (request.getParameter("villageId") != null && request.getParameter("villageId").length() > 0) {
                    formBean.setMandal_id(request.getParameter("mandalId"));
                    formBean.setVillage_id(request.getParameter("villageId"));
                    request.setAttribute("habitationwise", "habitationwise");
                    request.setAttribute("districtName", formBean.getDistrict_name());
                    request.setAttribute("mandalName", formBean.getMandal_name());
                     request.setAttribute("villageName", formBean.getVillage_name());
                    request.setAttribute("mandalName", formBean.getMandal_name());
                } else if (request.getParameter("mandalId") != null && request.getParameter("mandalId").length() > 0) {
                    request.setAttribute("villagewise", "villagewise");
                    formBean.setMandal_id(request.getParameter("mandalId"));
                    request.setAttribute("districtName", formBean.getDistrict_name());
                    request.setAttribute("mandalName", formBean.getMandal_name());
                } else if (request.getParameter("districtId") != null && request.getParameter("districtId").length() > 0) {
                    reportDTO.setReportType(request.getParameter("repType"));


                    request.setAttribute("districtName", formBean.getDistrict_name());

                    //request.setAttribute("districtName", reportDTO.getDistrict_name());
                    request.setAttribute("mandalwise", "mandalwise");

                } else {
                    request.setAttribute("districtwise", "districtwise");
                }
                request.setAttribute("districtName", formBean.getDistrict_name());

            }
            if (formBean.getFromdate() != null) {
                givenFromDate = formBean.getFromdate();
            }
            if (formBean.getTodate() != null) {
                givenToDate = formBean.getTodate();
            }

            ReportDAO reportDAO = new ReportDAO();
            BeanUtils.copyProperties(reportDTO, formBean);
            ArrayList reportList = new ArrayList();


            if (request.getParameter("districtId") != null && request.getParameter("districtId").length() > 0) {
                reportDTO.setDistrict_id(request.getParameter("districtId"));
                if (request.getParameter("repType") != null && !request.getParameter("repType").equalsIgnoreCase("Disability")) {
                    reportDTO.setReportType("Mandal");
                }
                mandal = "0";
            }
            if (request.getParameter("finYear") != null) {
                request.setAttribute("financialYear", request.getParameter("finYear"));
            }
            if (request.getParameter("yr") != null && request.getParameter("yr") != "") {
                reportDTO.setYear(request.getParameter("yr"));
                request.setAttribute("year", request.getParameter("yr"));
            }
            if (request.getParameter("mnt") != null && request.getParameter("mnt") != "") {
                reportDTO.setMonth(request.getParameter("mnt"));
                request.setAttribute("month", request.getParameter("mnt"));
            }
            if (request.getParameter("srchType") != null) {
                reportDTO.setTypeOfSearch(request.getParameter("srchType"));
            }
            if (request.getParameter("frDate") != null) {
                reportDTO.setFromdate(request.getParameter("frDate"));
            }
             if (request.getParameter("tDate") != null) {
                reportDTO.setTodate(request.getParameter("tDate"));
            }
            if (request.getParameter("finYear") != null) {
                reportDTO.setFinancialYear(request.getParameter("finYear"));
            }
            

            
            if (reportDTO.getReportType() != null
                    && (reportDTO.getReportType().equalsIgnoreCase("Disability") || reportDTO.getReportType().equalsIgnoreCase("Mandal"))
                    && request.getParameter("directorFlag") != null && request.getParameter("directorFlag").equalsIgnoreCase("1")) {
                reportDTO.setDistrict_id(reportDTO.getDistrict_id());
                //
            } else if (reportDTO.getReportType() != null
                    && reportDTO.getReportType().equalsIgnoreCase("Camp")) {
                reportDTO.setDistrict_id(reportDTO.getDistrict_id());
            } else {
                reportDTO.setDistrict_id("0");
               // 
            }


            reportList = reportDAO.directorReport(ds, reportDTO);



            if (reportList.isEmpty()) {
                request.setAttribute("msg", "No Records Found");
            } else {
                request.setAttribute("stateReport", reportList);
            }
            if (camp.equals("0")) {
                request.setAttribute("camplist", "camplist");
                request.setAttribute("camp", "camp");
                request.setAttribute("distCode", formBean.getDistrict_id());
                getCamps(mapping, form, request, response);
            } else if (district.equals("0")) {
                request.setAttribute("camplist", "camplist");
                request.setAttribute("district", "district");
            } else if (disability.equals("0")) {

                if (request.getParameter("srchType") != null) {
                    formBean.setTypeOfSearch(request.getParameter("srchType"));
                }
                if (request.getParameter("repType") != null) {
                    formBean.setReportType(request.getParameter("repType"));
                }

                if (request.getParameter("frDate") != null) {
                    formBean.setFromdate(request.getParameter("frDate"));
                } else {
                    formBean.setFromdate(formBean.getFromdate());
                }
                if (request.getParameter("tDate") != null) {
                    formBean.setTodate(request.getParameter("tDate"));
                } else {
                    formBean.setTodate(formBean.getTodate());
                }


                request.setAttribute("disabilitywise", "disabilitywise");
                request.setAttribute("districtwise", "districtwise");
            } else if (mandal.equals("0")) {
                request.setAttribute("mandal", "mandal");
                districtName = territoryDAO.getDistrictsName(ds, request.getParameter("districtId").toString());
                if (request.getParameter("srchType") != null && request.getParameter("srchType").equals("Date")) {
                    formBean.setTypeOfSearch(request.getParameter("srchType"));
                    formBean.setReportType(request.getParameter("repType"));
                    formBean.setFromdate(request.getParameter("frDate"));
                    formBean.setTodate(request.getParameter("tDate"));
                    request.setAttribute("heading", request.getParameter("srchType") + "," + request.getParameter("frDate") + "," + request.getParameter("tDate")+ "," + "Mandal" + "," + districtName);
                } else if (request.getParameter("srchType") != null && request.getParameter("srchType").equals("Month")) {
                    formBean.setTypeOfSearch(request.getParameter("srchType"));
                    formBean.setReportType(request.getParameter("repType"));
                    formBean.setMonth(request.getParameter("mnt"));
                    formBean.setYear(request.getParameter("yr"));
                    request.setAttribute("heading", request.getParameter("srchType") + "," + request.getParameter("yr") + "," + request.getParameter("mnt") + "," + "Mandal" + "," + districtName);
                } else if (request.getParameter("srchType") != null && request.getParameter("srchType").equals("FinancialYear")) {
                    formBean.setTypeOfSearch(request.getParameter("srchType"));
                    formBean.setReportType(request.getParameter("repType"));
                    formBean.setFinancialYear(request.getParameter("finYear"));
                    request.setAttribute("heading", request.getParameter("srchType") + "," + request.getParameter("finYear") + "," + "Mandal" + "," + districtName);
                }
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

            if (formBean.getDistrict_id() != null && formBean.getDistrict_id().toString().length() > 0) {
                districtName = territoryDAO.getDistrictsName(ds, formBean.getDistrict_id().toString());

                if (formBean.getDistrict_id() != null && formBean.getDistrict_id().equals("0")) {
                    districtName = "All";
                }
            }
            if (reportDTO.getTypeOfSearch() != null && reportDTO.getTypeOfSearch().equalsIgnoreCase("Date")) {
                request.setAttribute("heading", reportDTO.getTypeOfSearch() + "," + reportDTO.getFromdate() + "," + reportDTO.getTodate() + "," + reportDTO.getReportType() + "," + reportDTO.getDistrict_id() + "," + reportDTO.getMandal_id() + "," + reportDTO.getVillage_id() + "," + districtName);
            } else if (reportDTO.getTypeOfSearch() != null && reportDTO.getTypeOfSearch().equalsIgnoreCase("Month")) {
                request.setAttribute("heading", reportDTO.getTypeOfSearch() + "," + reportDTO.getYear() + "," + reportDTO.getMonth() + "," + reportDTO.getReportType() + "," + reportDTO.getDistrict_id() + "," + reportDTO.getMandal_id() + "," + reportDTO.getVillage_id() + "," + districtName);
            } else if (reportDTO.getTypeOfSearch() != null && reportDTO.getTypeOfSearch().equalsIgnoreCase("FinancialYear")) {
                request.setAttribute("heading", reportDTO.getTypeOfSearch() + "," + reportDTO.getFinancialYear() + "," + reportDTO.getReportType() + "," + reportDTO.getDistrict_id() + "," + reportDTO.getMandal_id() + "," + reportDTO.getVillage_id() + "," + districtName);
            }
            request.setAttribute("districtName", formBean.getDistrict_name());
            formBean.setFromdate(formBean.getFromdate());

        } catch (Exception e) {
            e.printStackTrace();
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(target);
    }

    /**
     * This method is for getting the camps based on district
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws SADAREMException
     * @throws SQLException
     */
    public ActionForward getCamps(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMException, SQLException {
        CampDailyReportService campDailyReportService =
                CampDailyReportServiceFactory.getcampDailyReportServiceImpl();
        ArrayList campDetails = new ArrayList();
        DataSource ds = null;
        DashboardReportsForm formBean = (DashboardReportsForm) form;
        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            campDetails = campDailyReportService.getCampBasedOnLoginDetails(ds, formBean.getDistrict_id());

            if (campDetails != null && campDetails.size() > 0) {
                formBean.setCampList(campDetails);
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
        DashboardReportsForm formBean = (DashboardReportsForm) form;
        String district_name = "ALL";
        String mandal_name = "ALL";
        String village_name = "ALL";
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            TerritoryDAO territoryDAO = new TerritoryDAO();
            ReportDAO reportDAO = new ReportDAO();
            ReportDTO reportDTO = new ReportDTO();
            ArrayList stateReport = new ArrayList();

            if (session.getAttribute("stateReport") != null) {
                stateReport = (ArrayList) session.getAttribute("stateReport");
            }
            if (request.getParameter("individualList") != null && request.getParameter("individualList") != "") {

                if (request.getParameter("heading").split(",")[0].equals("FinancialYear")) {
                    formBean.setTypeOfSearch(request.getParameter("heading").split(",")[0]);
                    if (request.getParameter("heading").split(",")[1] != null) {
                        formBean.setFinancialYear(request.getParameter("heading").split(",")[1]);
                    }
                    if (request.getParameter("heading").split(",")[2] != null) {
                        formBean.setReportType(request.getParameter("heading").split(",")[2]);
                    }
                    if (request.getParameter("heading").split(",")[2] != null) {
                        formBean.setReportType(request.getParameter("heading").split(",")[2]);
                    }

                } else if (request.getParameter("heading").split(",")[0].equals("Date")) {
                    if (request.getParameter("heading").split(",")[3] != null) {
                        formBean.setReportType(request.getParameter("heading").split(",")[3]);
                    }
                } else if (request.getParameter("heading").split(",")[0].equals("Month")) {
                    formBean.setTypeOfSearch(request.getParameter("heading").split(",")[0]);
                    if (request.getParameter("heading").split(",")[3] != null) {
                        formBean.setReportType(request.getParameter("heading").split(",")[3]);
                    }
                    if (request.getParameter("heading").split(",")[2] != null) {
                        formBean.setMonth(request.getParameter("heading").split(",")[2]);
                    }
                    if (request.getParameter("heading").split(",")[1] != null) {
                        formBean.setYear(request.getParameter("heading").split(",")[1]);
                    }
                    if (request.getParameter("heading").split(",")[3] != null) {
                        formBean.setReportType(request.getParameter("heading").split(",")[3]);
                    }
                }
                if (request.getParameter("heading").split(",")[1] != null) {
                    formBean.setFromdate(request.getParameter("heading").split(",")[1]);
                }
                if (request.getParameter("heading").split(",")[2] != null) {
                    formBean.setTodate(request.getParameter("heading").split(",")[2]);
                }
                if (request.getParameter("disabilityId") != null && request.getParameter("districtId") != "") {
                    reportDTO.setDisabilityId(request.getParameter("disabilityId"));
                }

                if (request.getParameter("heading").split(",")[3].equals("Disability") || request.getParameter("heading").split(",")[2].equals("Disability")) {
                    if (request.getParameter("mandalId") != null && request.getParameter("mandalId") != "") {
                        reportDTO.setMandal_id(request.getParameter("mandalId"));
                    }

                } else if (request.getParameter("heading").split(",")[3].equals("District") || request.getParameter("heading").split(",")[2].equals("District")) {
                    if (request.getParameter("reporttypeId") != null && request.getParameter("reporttypeId") != "") {
                        reportDTO.setReporttypeId(request.getParameter("reporttypeId"));
                    }
                } else if (request.getParameter("heading").split(",")[3].equals("Mandal") || request.getParameter("heading").split(",")[2].equals("Mandal")) {
                    if (request.getParameter("reporttypeId") != null && request.getParameter("reporttypeId") != "") {
                        reportDTO.setReporttypeId(request.getParameter("reporttypeId"));
                    }
                } else if (request.getParameter("heading").split(",")[3].equals("Camp") || request.getParameter("heading").split(",")[2].equals("Camp")) {
                    if (request.getParameter("reporttypeId") != null && request.getParameter("reporttypeId") != "") {
                        reportDTO.setReporttypeId(request.getParameter("reporttypeId"));
                    }
                }
                BeanUtils.copyProperties(reportDTO, formBean);
                if (request.getParameter("districtId") != null && request.getParameter("districtId") != "") {
                    reportDTO.setDistrict_id(request.getParameter("districtId"));
                }
                if (request.getParameter("villageId") != null && request.getParameter("villageId") != "") {
                    reportDTO.setVillage_id(request.getParameter("villageId"));
                }
                if (request.getParameter("mandalId") != null && request.getParameter("mandalId") != "") {
                    reportDTO.setMandal_id(request.getParameter("mandalId"));
                }
                if (request.getParameter("habcode") != null && request.getParameter("habcode") != "") {
                    reportDTO.setHabcode(request.getParameter("habcode"));
                }
                if (request.getParameter("campId") != null && request.getParameter("campId") != "") {
                    reportDTO.setCampId(request.getParameter("campId"));
                }
                String heading = request.getParameter("heading");
                stateReport = reportDAO.individualDetails(ds, reportDTO, heading);
                //reportList = reportDAO.directorReport(ds, reportDTO);
            }
            ArrayList headerList = new ArrayList();
            if (request.getParameter("individualList") == null) {
            if (request.getParameter("heading").split(",")[3].equals("Disability") || request.getParameter("heading").split(",")[2].equals("Disability")) {
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
                    exportExcelDisability(stateReport, headerList, "SADAREM Assessed & Pension Status", "Assessment_Report", request.getParameter("heading"), request, response);

                } else if (request.getParameter("heading").split(",")[3].equals("Camp") || request.getParameter("heading").split(",")[2].equals("Camp")) {
                    headerList.add("Total Applied");
                    headerList.add("Assessed");
                   
                    headerList.add("Eligible (>40%)\n(5-7)");
                     headerList.add("Rejected");
                    headerList.add("Ortho");
                    headerList.add("Visual");
                    headerList.add("Speech & Hearing");
                    headerList.add("Mental Retardation");
                    headerList.add("Mental Illness");
                    headerList.add("Multiple Disability");
                    headerList.add("Total Pensioners\n(8+9+10+11+12)");
                    exportExcelCamp(stateReport, headerList, "SADAREM Assessed & Pension Status", "Assessment_Report", request.getParameter("heading"), request, response);
                } else {
                    headerList.add("Total Applied");
                    headerList.add("Assessed");
                   
                      headerList.add("Eligible (>40%)\n(4-6)");
                       headerList.add("Rejected");
                    headerList.add("Ortho");
                    headerList.add("Visual");
                    headerList.add("Speech & Hearing");
                    headerList.add("Mental Retardation");
                    headerList.add("Mental Illness");
                    headerList.add("Multiple Disability");
                    headerList.add("Total Pensioners\n(7+8+9+10+11+12)");
                    exportExcel(stateReport, headerList, "SADAREM Assessed & Pension Status", "Assessment_Report", request.getParameter("heading"), request, response);
                }
            } else {
                if (request.getParameter("individualList") != null && request.getParameter("individualList") != "") {
                    //reportDTO.setDistrict_id(request.getParameter("districtId"));

                    //if (request.getParameter("heading").split(",")[3].equals("Disability") || request.getParameter("heading").split(",")[2].equals("Disability")) {
                   // headerList.add("S No.");
                    headerList.add("Pension ID");
                    headerList.add("Hab Code");
                    headerList.add("SADAREM ID");
                    headerList.add("Name");
                    headerList.add("Gender");
                    headerList.add("Age");
                    headerList.add("Education");
                    headerList.add("Cast");
                    headerList.add("Relation Name");
                    headerList.add("Rationcard Number");
                    headerList.add("Disability");
                    headerList.add("Percentage");
                    headerList.add("Assessment Status");
                    headerList.add("House No");
                    headerList.add("Habitation Name");
                   headerList.add("Village Name");
                    headerList.add("Mandal Name");
                    headerList.add("District Name");
                    headerList.add("Phone No");
                    headerList.add("Date");
//                headerList.add("Pension Status");
                    
                    individualDetails(stateReport, headerList, "SADAREM Assessed & Pension Status", "Assessment_Report", request.getParameter("heading"), request, response);
                    // }

//                stateReport=reportDAO.individualDetails(ds, reportDTO);
//
//

                    //reportList = reportDAO.directorReport(ds, reportDTO);
                }
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
                        s.addCell(new Label(0, 1, "Assessed Abstract Report Date From " + heading.split(",")[1] + " To " + heading.split(",")[2] + " :: District :" + heading.split(",")[7], cell));
                    } else if (heading.split(",")[0].equals("Month")) {
                        s.addCell(new Label(0, 1, "Assessed Abstract Report From the Month of " + getFullMonths(Integer.parseInt(heading.split(",")[2].toString())) + ", " + heading.split(",")[1] + " :: District :" + heading.split(",")[7], cell));
                    } else if (heading.split(",")[0].equals("FinancialYear")) {
                        s.addCell(new Label(0, 1, "Assessed Abstract Report For the Financial Year " + heading.split(",")[1] + " :: District :" + heading.split(",")[6], cell));
                    }

                    s.mergeCells(0, 1, 12, 1);
                    s.addCell(new Label(2, 2, "PwDs - ASSESSMENT STATUS", cell));
                    s.mergeCells(2, 2, 5, 2);
                    s.addCell(new Label(6, 2, "PwDs - PENSIONS COVERED", cell));
                    s.mergeCells(6, 2, 12, 2);
                    s.addCell(new Label(0, 2, "S No", cell));
                    s.mergeCells(0, 2, 0, 3);

                    if (heading.split(",")[0].equals("Date")) {
                        if (heading.split(",")[3].equals("District")) {
                            s.addCell(new Label(1, 2, "District", cell));
                            s.mergeCells(1, 2, 1, 3);
                        } else {
                            s.addCell(new Label(1, 2, "Mandal", cell));
                            s.mergeCells(1, 2, 1, 3);
                        }
                    } else if (heading.split(",")[0].equals("Month")) {

                        if (heading.split(",")[3].equals("District")) {
                            s.addCell(new Label(1, 2, "District", cell));
                            s.mergeCells(1, 2, 1, 3);
                        } else {
                            s.addCell(new Label(1, 2, "Mandal", cell));
                            s.mergeCells(1, 2, 1, 3);
                        }
                    } else if (heading.split(",")[0].equals("FinancialYear")) {

                        if (heading.split(",")[2].equals("District")) {
                            s.addCell(new Label(1, 2, "District", cell));
                            s.mergeCells(1, 2, 1, 3);
                        } else {
                            s.addCell(new Label(1, 2, "Mandal", cell));
                            s.mergeCells(1, 2, 1, 3);
                        }
                    }
                    addHeaders(s, headerList, cell, "mandal");
                }

                int x = 5;
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
                int stat = 1;
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
                        s.addCell(new Label(10, 4, "11", cell));
                        s.addCell(new Label(11, 4, "12", cell));
                        s.addCell(new Label(12, 4, "13", cell));
                        stat++;
                    }

                    s.addCell(new Label(j++, x, i + 1 + "", cellFormat));

                    if (reportDetailsDTO.getMandal_name() != null) {
                        s.addCell(new Label(j++, x, reportDetailsDTO.getMandal_name() + "", cellFormat));
                    } else {
                        s.addCell(new Label(j++, x, reportDetailsDTO.getDistrict_name() + "", cellFormat));
                    }

                    s.addCell(new Label(j++, x, reportDetailsDTO.getTotalApplied() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getTotalAssessed() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getEligible() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getRejected() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getOh() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getVi() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getHi() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getMr() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getMi() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getMd() + "", cellFormat));

                    s.addCell(new Label(j++, x, reportDetailsDTO.getMd() + reportDetailsDTO.getTotalAssessed() + reportDetailsDTO.getRejected() + reportDetailsDTO.getEligible()
                            + reportDetailsDTO.getOh() + reportDetailsDTO.getVi() + reportDetailsDTO.getHi() + reportDetailsDTO.getMr() + reportDetailsDTO.getMi() + reportDetailsDTO.getMd() + "", cellFormat));

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
                        s.addCell(new Label(j++, x, eligible + "", cell));
                        s.addCell(new Label(j++, x, rejected + "", cell));
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
                    
                    s.addCell(new Label(j++, x, reportDetailsDTO.getEligible() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getRejected() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getOh() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getVi() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getHi() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getMr() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getMi() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getMd() + "", cellFormat));

                    s.addCell(new Label(j++, x, reportDetailsDTO.getMd() + reportDetailsDTO.getOh() + reportDetailsDTO.getVi()
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
                        
                        s.addCell(new Label(j++, x, eligible + "", cell));
                        s.addCell(new Label(j++, x, rejected + "", cell));
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

    public static void exportExcelDisability(ArrayList dataList, ArrayList headerList, String reportHeading, String reportName, String heading, HttpServletRequest request, HttpServletResponse response) {
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
                    s.mergeCells(0, 0, 19, 0);


                    if (heading.split(",")[0].equals("Date")) {
                        s.addCell(new Label(0, 1, "Assessed Abstract Report Date From " + heading.split(",")[1] + " To " + heading.split(",")[2] + " :: District :" + heading.split(",")[4], cell));
                    } else if (heading.split(",")[0].equals("Month")) {
                        s.addCell(new Label(0, 1, "Assessed Abstract Report From the Month of " + getFullMonths(Integer.parseInt(heading.split(",")[2].toString())) + ", " + heading.split(",")[1] + " :: District :" + heading.split(",")[4], cell));
                    } else if (heading.split(",")[0].equals("FinancialYear")) {
                        s.addCell(new Label(0, 1, "Assessed Abstract Report For the Financial Year " + heading.split(",")[1] + " :: District :" + heading.split(",")[3], cell));
                    }

                    s.mergeCells(0, 1, 19, 1);

                    s.addCell(new Label(0, 2, "S No", cell));
                    s.mergeCells(0, 2, 0, 3);


                    if (heading.split(",")[0].equals("Date")) {
                        if (!heading.split(",")[6].equals("null")) {
                            s.addCell(new Label(1, 2, "Habitation", cell));
                        } else if (!heading.split(",")[5].equals("null")) {
                            s.addCell(new Label(1, 2, "Village", cell));
                        } else if (!heading.split(",")[4].equals("0") && !heading.split(",")[4].equals("null")) {
                            s.addCell(new Label(1, 2, "Mandal", cell));
                        } else {
                            s.addCell(new Label(1, 2, "District", cell));
                        }
                    } else if (heading.split(",")[0].equals("Month")) {
                        if (!heading.split(",")[6].equals("null")) {
                            s.addCell(new Label(1, 2, "Habitation", cell));
                        } else if (!heading.split(",")[5].equals("null")) {
                            s.addCell(new Label(1, 2, "Village", cell));
                        } else if (!heading.split(",")[4].equals("0") && !heading.split(",")[4].equals("null")) {
                            s.addCell(new Label(1, 2, "Mandal", cell));
                        } else {
                            s.addCell(new Label(1, 2, "District", cell));
                        }
                    } else if (heading.split(",")[0].equals("FinancialYear")) {
                        if (!heading.split(",")[5].equals("null")) {
                            s.addCell(new Label(1, 2, "Habitation", cell));
                        } else if (!heading.split(",")[4].equals("null")) {
                            s.addCell(new Label(1, 2, "Village", cell));
                        } else if (!heading.split(",")[3].equals("0")) {
                            s.addCell(new Label(1, 2, "Mandal", cell));
                        } else {
                            s.addCell(new Label(1, 2, "District", cell));
                        }
                    }

                    s.mergeCells(1, 2, 1, 3);

                    s.addCell(new Label(2, 2, "Locomotor/OH", cell));
                    s.mergeCells(2, 2, 4, 2);

                    s.addCell(new Label(5, 2, "Visual Impairment", cell));
                    s.mergeCells(5, 2, 7, 2);

                    s.addCell(new Label(8, 2, "Hearing Impairment", cell));
                    s.mergeCells(8, 2, 10, 2);

                    s.addCell(new Label(11, 2, "Mental Retardation ", cell));
                    s.mergeCells(11, 2, 13, 2);

                    s.addCell(new Label(14, 2, "Mental Illness ", cell));
                    s.mergeCells(14, 2, 16, 2);

                    s.addCell(new Label(17, 2, "Multiple Disability ", cell));
                    s.mergeCells(17, 2, 19, 2);
                    s.addCell(new Label(20, 2, "Total Disability ", cell));
                    s.mergeCells(20, 2, 23, 2);
                    addHeaders(s, headerList, cell, "Disability");
                }

                int x = 4;

                for (int i = 0; i < dataList.size(); i++, x++) {
                    int j = 0;
                    ReportDTO reportDetailsDTO =
                            new ReportDTO();
                    reportDetailsDTO = (ReportDTO) dataList.get(i);

                    s.addCell(new Label(j++, x, i + 1 + "", cellFormat));

                    if (heading.split(",")[0].equals("Date")) {
                        if (!heading.split(",")[6].equals("null")) {
                            s.addCell(new Label(j++, x, reportDetailsDTO.getHabitation_name() + "", cellFormat));
                        } else if (!heading.split(",")[5].equals("null")) {
                            s.addCell(new Label(j++, x, reportDetailsDTO.getVillage_name() + "", cellFormat));
                        } else if (!heading.split(",")[4].equals("0") && !heading.split(",")[4].equals("null")) {
                            s.addCell(new Label(j++, x, reportDetailsDTO.getMandal_name() + "", cellFormat));
                        } else {
                            s.addCell(new Label(j++, x, reportDetailsDTO.getDistrict_name() + "", cellFormat));
                        }
                    } else if (heading.split(",")[0].equals("Month")) {
                        if (!heading.split(",")[6].equals("null")) {
                            s.addCell(new Label(j++, x, reportDetailsDTO.getHabitation_name() + "", cellFormat));
                        } else if (!heading.split(",")[5].equals("null")) {
                            s.addCell(new Label(j++, x, reportDetailsDTO.getVillage_name() + "", cellFormat));
                        } else if (!heading.split(",")[4].equals("0") && !heading.split(",")[4].equals("null")) {
                            s.addCell(new Label(j++, x, reportDetailsDTO.getMandal_name() + "", cellFormat));
                        } else {
                            s.addCell(new Label(j++, x, reportDetailsDTO.getDistrict_name() + "", cellFormat));
                        }
                    } else if (heading.split(",")[0].equals("FinancialYear")) {
                        if (!heading.split(",")[5].equals("null")) {
                            s.addCell(new Label(j++, x, reportDetailsDTO.getHabitation_name() + "", cellFormat));
                        } else if (!heading.split(",")[4].equals("null")) {
                            s.addCell(new Label(j++, x, reportDetailsDTO.getVillage_name() + "", cellFormat));
                        } else if (!heading.split(",")[3].equals("0")) {
                            s.addCell(new Label(j++, x, reportDetailsDTO.getMandal_name() + "", cellFormat));
                        } else {
                            s.addCell(new Label(j++, x, reportDetailsDTO.getDistrict_name() + "", cellFormat));
                        }
                    }



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

                    s.addCell(new Label(j++, x, reportDetailsDTO.getTotalAssessedDis() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getTotalEligible() + "", cellFormat));
                    s.addCell(new Label(j++, x, reportDetailsDTO.getTotalRejected() + "", cellFormat));
                    if (i == (50000) * page) {
                        int sheet = ++page;
                        s = w.createSheet("Sheet" + sheet, sheet - 1);
                        j = 0;
                        x = 2;
                        //addHeaders(s, headerList, cell, "camp");
                    }

                    // Total

                    if (i == dataList.size() - 1) {
                        j = 0;
                        x = x + 1;
//                        s.addCell(new Label(j++, x, "", cell));
                        s.addCell(new Label(j++, x, "", cell));
                        s.addCell(new Label(j++, x, "Total", cell));

                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalOHassessed() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalOHeligible() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalOHrejected() + "", cell));

                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalVIassessed() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalVIeligible() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalVIrejected() + "", cell));

                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalHIassessed() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalHIeligible() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalHIrejected() + "", cell));

                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalMRassessed() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalMReligible() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalMRrejected() + "", cell));

                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalMIassessed() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalMIeligible() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalMIrejected() + "", cell));

                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalMDassessed() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalMDeligible() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalMDrejected() + "", cell));

                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalAssessmentDisability() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalEligibleDisability() + "", cell));
                        s.addCell(new Label(j++, x, reportDetailsDTO.getTotalRejectedDisability() + "", cell));
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
            setWidth(s, x + 2, 23);
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


    public ActionForward getDirectorReportIndividualDetails(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMException, SQLException {
        String target = "individual";
        DataSource ds = null;
        ReportDTO reportDTO = new ReportDTO();
        ReportDAO reportDAO = new ReportDAO();
        DashboardReportsForm formBean = (DashboardReportsForm) form;
        ArrayList stateReport = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            if (request.getParameter("individualList") != null && request.getParameter("individualList") != "") {
                if (request.getParameter("heading").split(",")[0].equals("FinancialYear")) {
                    formBean.setTypeOfSearch(request.getParameter("heading").split(",")[0]);
                    if (request.getParameter("heading").split(",")[1] != null) {
                        formBean.setFinancialYear(request.getParameter("heading").split(",")[1]);
                    }
                    if (request.getParameter("heading").split(",")[2] != null) {
                        formBean.setReportType(request.getParameter("heading").split(",")[2]);
                    }
                    if (request.getParameter("heading").split(",")[2] != null) {
                        formBean.setReportType(request.getParameter("heading").split(",")[2]);
                    }
                    request.setAttribute("FinancialYear", "FinancialYear");
                } else if (request.getParameter("heading").split(",")[0].equals("Date")) { 
                    if (request.getParameter("heading").split(",")[3] != null) {
                        formBean.setReportType(request.getParameter("heading").split(",")[3]);
                    }
                    request.setAttribute("Date", "Date");
                } else if (request.getParameter("heading").split(",")[0].equals("Month")) {
                    formBean.setTypeOfSearch(request.getParameter("heading").split(",")[0]);
                    if (request.getParameter("heading").split(",")[3] != null) {
                        formBean.setReportType(request.getParameter("heading").split(",")[3]);
                    }
                    if (request.getParameter("heading").split(",")[2] != null) {
                        formBean.setMonth(request.getParameter("heading").split(",")[2]);
                    }
                    if (request.getParameter("heading").split(",")[1] != null) {
                        formBean.setYear(request.getParameter("heading").split(",")[1]);
                    }
                    if (request.getParameter("heading").split(",")[3] != null) {
                        formBean.setReportType(request.getParameter("heading").split(",")[3]);
                    }
                    request.setAttribute("Month", "Month");
                    String monthName = getFullMonths(Integer.parseInt(formBean.getMonth()));
                    
                    if (monthName != null && monthName != "") {
                   
                        request.setAttribute("monthName", monthName);
                    }
                }

                if (request.getParameter("heading").split(",")[1] != null) {
                    formBean.setFromdate(request.getParameter("heading").split(",")[1]);
                }
                if (request.getParameter("heading").split(",")[2] != null) {
                    formBean.setTodate(request.getParameter("heading").split(",")[2]);
                }
                if (request.getParameter("disabilityId") != null && request.getParameter("districtId") != "") {
                    reportDTO.setDisabilityId(request.getParameter("disabilityId"));
                }

                if (request.getParameter("heading").split(",")[3].equals("Disability") || request.getParameter("heading").split(",")[2].equals("Disability")) {
                    if (request.getParameter("mandalId") != null && request.getParameter("mandalId") != "") {
                        reportDTO.setMandal_id(request.getParameter("mandalId"));
                    }

                } else if (request.getParameter("heading").split(",")[3].equals("District") || request.getParameter("heading").split(",")[2].equals("District")) {
                    if (request.getParameter("reporttypeId") != null && request.getParameter("reporttypeId") != "") {
                        reportDTO.setReporttypeId(request.getParameter("reporttypeId"));
                    }
                } else if (request.getParameter("heading").split(",")[3].equals("Mandal") || request.getParameter("heading").split(",")[2].equals("Mandal")) {
                    if (request.getParameter("reporttypeId") != null && request.getParameter("reporttypeId") != "") {
                        reportDTO.setReporttypeId(request.getParameter("reporttypeId"));
                    }
                } else if (request.getParameter("heading").split(",")[3].equals("Camp") || request.getParameter("heading").split(",")[2].equals("Camp")) {
                    if (request.getParameter("reporttypeId") != null && request.getParameter("reporttypeId") != "") {
                        reportDTO.setReporttypeId(request.getParameter("reporttypeId"));
                    }
                }
                BeanUtils.copyProperties(reportDTO, formBean);
                if (request.getParameter("districtId") != null && request.getParameter("districtId") != "") {
                    reportDTO.setDistrict_id(request.getParameter("districtId"));
                }
                if (request.getParameter("villageId") != null && request.getParameter("villageId") != "") {
                    reportDTO.setVillage_id(request.getParameter("villageId"));
                }
                if (request.getParameter("mandalId") != null && request.getParameter("mandalId") != "") {
                    reportDTO.setMandal_id(request.getParameter("mandalId"));
                }
                if (request.getParameter("habcode") != null && request.getParameter("habcode") != "") {
                    reportDTO.setHabcode(request.getParameter("habcode"));
                }
                String heading = request.getParameter("heading");
                
                stateReport = reportDAO.individualDetails(ds, reportDTO, heading);
                
                if (stateReport.size() > 0) {
                    request.setAttribute("stateReport", stateReport);
                }else{
                request.setAttribute("msgData", "<font color ='red'>Assessment Data is not Available!</font>");
                }
                //reportList = reportDAO.directorReport(ds, reportDTO);
            }
        } catch (Exception e) {
        }
        return mapping.findForward(target);
    }

    public static void individualDetails(ArrayList dataList, ArrayList headerList, String reportHeading, String reportName, String heading, HttpServletRequest request, HttpServletResponse response) {
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
            cell.setBackground(Colour.GRAY_25);

            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);
            if (dataList != null) {
                if (dataList.size() == 0) {
                    s.addCell(new Label(0, 0, "No Data Found................"));
                } else {
                    s.addCell(new Label(0, 0, reportHeading, cell));
                    s.mergeCells(0, 0, 19, 0);


                    if (heading.split(",")[0].equals("Date")) {
                        s.addCell(new Label(0, 1, "Assessed Abstract Report Date From " + heading.split(",")[1] + " To " + heading.split(",")[2] + " :: District :" + heading.split(",")[7], cell));
                    } else if (heading.split(",")[0].equals("Month")) {
                        s.addCell(new Label(0, 1, "Assessed Abstract Report From the Month of " + getFullMonths(Integer.parseInt(heading.split(",")[2].toString())) + ", " + heading.split(",")[1] + " :: District :" + heading.split(",")[7], cell));
                    } else if (heading.split(",")[0].equals("FinancialYear")) {
                        s.addCell(new Label(0, 1, "Assessed Abstract Report For the Financial Year " + heading.split(",")[1] + " :: District :" + heading.split(",")[6], cell));
                    }

                    s.mergeCells(0, 1, 19, 1);

                    s.addCell(new Label(0, 3, "S No", cell));
                    addHeaders(s, headerList, cell, "individualList");
                }

                int x = 4;

                for (int i = 0; i < dataList.size(); i++, x++) {
                    int j = 0;
                    ReportDTO reportDetailsDTO =
                            new ReportDTO();
                    Map m = (Map) dataList.get(i);

                    s.addCell(new Label(j++, x, i + 1 + "", cellFormat));

                    s.addCell(new Label(j++, x, m.get("pensionid").toString(), cellFormat));
                    s.addCell(new Label(j++, x, m.get("habcode").toString(), cellFormat));
                    s.addCell(new Label(j++, x, m.get("sadaremcode").toString(), cellFormat));
                    s.addCell(new Label(j++, x, m.get("pensionname").toString(), cellFormat));
                    s.addCell(new Label(j++, x, m.get("gender").toString(), cellFormat));
                    s.addCell(new Label(j++, x, m.get("age").toString(), cellFormat));
                    s.addCell(new Label(j++, x, m.get("education").toString(), cellFormat));
                    s.addCell(new Label(j++, x, m.get("cast").toString(), cellFormat));
                    s.addCell(new Label(j++, x, m.get("relationname").toString(), cellFormat));
                    s.addCell(new Label(j++, x, m.get("rationcardnumber").toString(), cellFormat));
                    s.addCell(new Label(j++, x, m.get("typeofdisability").toString(), cellFormat));
                    s.addCell(new Label(j++, x, m.get("percentage").toString(), cellFormat));
                    s.addCell(new Label(j++, x, m.get("assessmentstatus").toString(), cellFormat));
                    s.addCell(new Label(j++, x, m.get("houseno").toString(), cellFormat));
                    s.addCell(new Label(j++, x, m.get("habitationname").toString(), cellFormat));
                    s.addCell(new Label(j++, x, m.get("villagename").toString(), cellFormat));
                    s.addCell(new Label(j++, x, m.get("mandalname").toString(), cellFormat));
                    s.addCell(new Label(j++, x, m.get("districtname").toString(), cellFormat));
                    s.addCell(new Label(j++, x, m.get("phone").toString(), cellFormat));
                    s.addCell(new Label(j++, x, m.get("date").toString(), cellFormat));

                    if (i == (50000) * page) {
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
