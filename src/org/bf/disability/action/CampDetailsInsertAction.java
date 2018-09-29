/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.dto.CampDetailsDTO;
import org.bf.disability.form.CampDetailsForm;
import org.bf.disability.service.CampDailyReportService;
import org.bf.disability.servicefactory.CampDailyReportServiceFactory;

import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author 728056
 */
public class CampDetailsInsertAction extends DispatchAction {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    DataSource ds = null;

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
        ArrayList campList = new ArrayList();
        String url = request.getRequestURI();
        CampDetailsForm campDetailsForm = (CampDetailsForm) form;
        ArrayList diabilityList = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            Date today = new Date();
            String month = (today.getMonth() + 1) + "";
            if (month.length() == 1) {
                month = "0" + month;
            }
            if (url.contains("campDetailsDateWiseReport.do")) {

                TerritoryDAO territoryDAO = new TerritoryDAO();
                ArrayList districts = territoryDAO.getDistricts(ds);
                campDetailsForm.setDistrictList(districts);

                campDetailsForm.setMonth(month);
                campDetailsForm.setYear((today.getYear() + 1900));

            }
            else 
            {
                CampDailyReportService campDailyReportService = CampDailyReportServiceFactory.getcampDailyReportServiceImpl();
                HttpSession session = request.getSession();
                String districtId = CommonUtility.checkNullObj(session.getAttribute("districtId"));
                
              //  System.out.println("Sess districtId : "+districtId);
                
                campList = campDailyReportService.getCampBasedOnLoginDetails(ds, districtId);

                campDetailsForm.setCampList(campList);
                CampDetailsDTO campDetailsDTO = new CampDetailsDTO();
                campDetailsDTO.setDistrict_id(districtId);
//                campDetailsDTO.setMonth(month);
//                campDetailsDTO.setYear((today.getYear() + 1900));
                campDetailsDTO.setCampDate(campDetailsForm.getCampDate());

                ArrayList campReportList = campDailyReportService.getCampReportBasedOnMonthYearDistrict(ds, campDetailsDTO, "S");
            
                if (campReportList.size() > 0) 
                {
                    request.setAttribute("campReportList", campReportList);
                  //  request.setAttribute("PreMsg", "Camp Details Submitted For This Month");
                } else {
                    request.setAttribute("PreMsg", "No Camp Details Are Submitted For This Month");
                }
                // Method for displaying the disability Data
                diabilityList = new ArrayList();
                diabilityList = campDailyReportService.getDisabilityList(ds);
                if (diabilityList != null && diabilityList.size() > 0) 
                {
                    request.setAttribute("diabilityList", diabilityList);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getCampReport(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward insertCampDetails(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        CampDetailsForm campDetailsForm = (CampDetailsForm) form;
        HttpSession session = request.getSession();
        CampDailyReportService campDailyReportService = CampDailyReportServiceFactory.getcampDailyReportServiceImpl();

        StringBuffer columnNames = null;
        StringBuffer columns = null;
        StringBuffer columnNamesPending = null;
        StringBuffer columnsPending = null; 
        int count = 0;
        String checkPendingAssessment = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            //campDetailsForm.setCampId((String) session.getAttribute("districtId"));
            campDetailsForm.setDistrict_id((String) session.getAttribute("districtId"));
//            checkPendingAssessment = campDailyReportService.checkCampAssessmentStatus(ds, campDetailsForm); // Method for checking the previous camp assessment details

//            if (checkPendingAssessment != null && checkPendingAssessment.equals("success") && checkPendingAssessment.length() > 0) {
            // Added By Mohan

            String disCount[] = request.getParameterValues("locCount");
            
            System.out.println("disCount : "+disCount.length);
            
            String discount = null;
            for (String sa : disCount) 
            {
                if(sa.length()>0)
                {
                      discount = sa;
                }
            }

            System.out.println("locValue : "+request.getParameterValues("locValue"));
            
            if (request.getParameterValues("locValue") != null && request.getParameterValues("locCount") != null)
            {
                columnNames = new StringBuffer();
                columns = new StringBuffer();
                columnsPending = new StringBuffer();
                for (int i = 0, j = 0; i < request.getParameterValues("locValue").length && j < request.getParameterValues("locCount").length; i++, j++) 
                {
                    if (!request.getParameterValues("locValue")[i].toString().equals("") && !request.getParameterValues("locCount")[j].toString().equals(""))
                    {
                        if (request.getParameterValues("locValue")[i].toString().equals("1"))
                        {
                            columnNames.append("" + request.getParameterValues("locCount")[j] + ",");
                            columns.append("OHCount,");
                            columnsPending.append("OHCountpending,");
                        }
                        else if (request.getParameterValues("locValue")[i].toString().equals("2"))
                        {
                            columnNames.append("" + request.getParameterValues("locCount")[j] + ",");
                            columns.append("VICount,");
                            columnsPending.append("viCountpending,");
                        } 
                        else if (request.getParameterValues("locValue")[i].toString().equals("3")) 
                        {
                            columnNames.append("" + request.getParameterValues("locCount")[j] + ",");
                            columns.append("HICount,");
                            columnsPending.append("hiCountpending,");
                        }
                        else if (request.getParameterValues("locValue")[i].toString().equals("4"))
                        {
                            columnNames.append("" + request.getParameterValues("locCount")[j] + ",");
                            columns.append("MRCount,");
                            columnsPending.append("mrCountpending,");
                        } 
                        else if (request.getParameterValues("locValue")[i].toString().equals("5"))
                        {
                            columnNames.append("" + request.getParameterValues("locCount")[j] + ",");
                            columns.append("MICount,");
                            columnsPending.append("miCountpending,");
                        }
                    }



                }
                campDetailsForm.setColumns(columns.toString());
                campDetailsForm.setDisabilityCount(columnNames.toString());


                campDetailsForm.setColumnsPending(columnsPending.toString());
//                }
                // End

                System.out.println("discount : "+discount+"\n IP : "+CommonUtility.getClientIPAddress(request));
                
                if (discount != null && discount.length() > 0) 
                {
                    count = campDailyReportService.getCampDetailsDateWise(ds, campDetailsForm, (String) session.getAttribute("districtId"), (String) session.getAttribute("loginid"), CommonUtility.getClientIPAddress(request));
                    if (count == 0) 
                    {
                        int insertResult = campDailyReportService.insertCampDetailsDateWise(ds, campDetailsForm, (String) session.getAttribute("districtId"), (String) session.getAttribute("loginid"), CommonUtility.getClientIPAddress(request));
                        if (insertResult != 0) 
                        {
                            campDetailsForm.setCampId("0");
                            request.setAttribute("msg", "Camp Details Inserted Successfully!!!");
                        }
                    }
                    else
                    {
                        request.setAttribute("msg", "Camp Details on Selected Date Already Inserted!!!");
                    }
                } 
                else 
                {
                    request.setAttribute("msg", "<font color='red' size='2'>Enter Atlest One Disability Count</font>");
                }
                ArrayList campList = campDailyReportService.getCampBasedOnLoginDetails(ds, (String) session.getAttribute("districtId"));
                campDetailsForm.setCampList(campList);
                Date today = new Date();
                String month = (today.getMonth() + 1) + "";
                if (month.length() == 1) {
                    month = "0" + month;
                }
                CampDetailsDTO campDetailsDTO = new CampDetailsDTO();
                campDetailsDTO.setDistrict_id((String) session.getAttribute("districtId"));
                campDetailsDTO.setMonth(month);
                campDetailsDTO.setYear((today.getYear() + 1900));
                ArrayList campReportList = campDailyReportService.getCampReportBasedOnMonthYearDistrict(ds, campDetailsDTO, "S");

                if (campReportList.size() > 0) {
                    request.setAttribute("campReportList", campReportList);
                    request.setAttribute("PreMsg", "Camp Details in Seleted Month");
                } else {
                    request.setAttribute("PreMsg", "No Camp Details Are Submitted");
                }
                ArrayList diabilityList = campDailyReportService.getDisabilityList(ds);
                if (diabilityList != null && diabilityList.size() > 0) {
                    request.setAttribute("diabilityList", diabilityList);
                }

                request.setAttribute("After", "results");


            } else 
            {
                request.setAttribute("pendingCampDetails", checkPendingAssessment);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getCampReportDetails(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        CampDetailsForm campDetailsForm = (CampDetailsForm) form;
        CampDailyReportService campDailyReportService =
                CampDailyReportServiceFactory.getcampDailyReportServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            CampDetailsDTO campDetailsDTO = new CampDetailsDTO();
            BeanUtils.copyProperties(campDetailsDTO, campDetailsForm);
            ArrayList campReportList = campDailyReportService.getCampReportBasedOnMonthYearDistrict(ds, campDetailsDTO,"R");

            if (campReportList.size() > 0) {
                request.setAttribute("campReportList", campReportList);

            } else {
                request.setAttribute("msg", "No Camp Details Are Found");
            }
            TerritoryDAO territoryDAO = new TerritoryDAO();
            ArrayList districts = territoryDAO.getDistricts(ds);
            campDetailsForm.setDistrictList(districts);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getCampReport(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        CampDetailsForm campDetailsForm = (CampDetailsForm) form;
        CampDailyReportService campDailyReportService =
                CampDailyReportServiceFactory.getcampDailyReportServiceImpl();
        ArrayList campList = new ArrayList();
        ArrayList yearsList = new ArrayList();
        ArrayList monthList = new ArrayList();
        ArrayList campReportList = new ArrayList();

        try {
            HttpSession session = request.getSession();
            CommonDAO commonDAO = new CommonDAO();
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String districtId = (String) session.getAttribute("districtId");
            campDetailsForm.setDistrict_id(districtId);
            campList = campDailyReportService.getCampBasedOnLoginDetails(ds, districtId);
            yearsList = commonDAO.getYears();
            campDetailsForm.setCampList(campList);
            campDetailsForm.setYearsList(yearsList);
            if (campDetailsForm.getStatus() != null && !campDetailsForm.getStatus().equalsIgnoreCase("")) {
                monthList = commonDAO.getMonths(campDetailsForm.getYear());
                campDetailsForm.setMonth("0");
                campDetailsForm.setStatus(null);
            }
            if (campDetailsForm != null && campDetailsForm.getCampId() != null && campDetailsForm.getMonth() != null && !campDetailsForm.getMonth().equalsIgnoreCase("0")
                    && campDetailsForm.getYear() != 0) {
                CampDetailsDTO campDetailsDTO = new CampDetailsDTO();
                BeanUtils.copyProperties(campDetailsDTO, campDetailsForm);
                campReportList = campDailyReportService.getCampDisabilityWiseReport(ds, campDetailsDTO);
                if (campReportList != null && campReportList.size() > 0) {
                    request.setAttribute("campReportList", campReportList);
                    request.setAttribute("campId", campDetailsForm.getCampId());
                    request.setAttribute("year", campDetailsForm.getYear());
                    request.setAttribute("month", campDetailsForm.getMonth());
                } else {
                    request.setAttribute("msg", "No Details Found");
                }
                monthList = commonDAO.getMonths(campDetailsForm.getYear());
            }
            campDetailsForm.setMonthList(monthList);
            if (request.getParameter("flag") != null) {
                StringBuffer excelHeading = new StringBuffer();
                if (request.getParameter("campId") != null) {
                    if (!request.getParameter("campId").equalsIgnoreCase("0")) {
                        excelHeading.append("Camp: " + campDailyReportService.getCampName(ds, request.getParameter("campId")));
                    } else {
                        excelHeading.append("Camp: All");
                    }
                }
                if (request.getParameter("year") != null) {
                    excelHeading.append(" ,Year: " + request.getParameter("year"));
                }
                if (request.getParameter("month") != null) {
                    excelHeading.append(" ,Month: " + commonDAO.getMonth(request.getParameter("month")));
                }

                ArrayList headersList = new ArrayList();

                headersList.add("S No");
                headersList.add("Date");
                headersList.add("Part A");
                headersList.add("Total Persons Assessed");
                headersList.add("Physical Impairment");
                headersList.add("Visual Impairment");
                headersList.add("Hearing Impairment");
                headersList.add("Mental Retardation");
                headersList.add("Mental Illness");
                headersList.add("Multiple Disability");
                headersList.add("Total");
                headersList.add("Direct Rejected");
                headersList.add("Assessed And Rejected");
                headersList.add("T Rejected");
                ArrayList keyList = new ArrayList();

                keyList.add("date");
                keyList.add("partA");
                keyList.add("TAP");
                keyList.add("PI");
                keyList.add("VI");
                keyList.add("HI");
                keyList.add("MR");
                keyList.add("MI");
                keyList.add("MD");
                keyList.add("total");
                keyList.add("DR");
                keyList.add("AR");
                keyList.add("TR");



                commonDAO.exportExcel(campReportList, headersList, keyList, excelHeading.toString(), "CampReport", request, response);
                // exportExcel(campReportList, response);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return mapping.findForward("success");
    }
}
