/*
 * SurgeryReportAction.java
 *
 * Created on August 4, 2008, 5:57 PM
 */
package org.bf.disability.action;

import java.sql.SQLException;
import java.util.*;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.service.DailyDisabilityAndPercentageService;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.bf.disability.servicefactory.DailyDisabilityAndPercentageServiceFactory;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.form.PartAForm;
import javax.servlet.http.HttpSession;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.dao.DailyDisabilityAndPercentageDAO;
import org.bf.disability.dao.PartADAO;

/**
 * 
 * @description this action class will be used to generate daily disability and percentage report
 * @author ragahavendra_t
 * @version 1.0
 */
public class DailyDisabilityAndPercentageReportAction extends DispatchAction {

    /**
     * 
     * @description this method is used to get the disbility wise report
     * @param mapping 
     * @param form 
     * @param request 
     * @param response s
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    public ActionForward unspecified(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        DataSource ds = null;

        CommonDAO commonDAO = new CommonDAO();
        ArrayList yearList = new ArrayList();
        ArrayList monthList = new ArrayList();
        ArrayList financialYearList = new ArrayList();

        String fromdate = null;
        String todate = null;


        PartAForm partAForm = (PartAForm) form;
        PartADTO partADTO = new PartADTO();
        String tdate = Calendar.getInstance().get(Calendar.DATE) + "/" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "/" + Calendar.getInstance().get(Calendar.YEAR);
        String fdate = "01/01/2010";
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            DailyDisabilityAndPercentageDAO dao = new DailyDisabilityAndPercentageDAO();
            DailyDisabilityAndPercentageService dailyreportservice =
                    DailyDisabilityAndPercentageServiceFactory.getDailyDisabilityAndPercentageServiceImpl();

            if (partAForm.getDistrict_id() == null) {
                partAForm.setDistrict_id("0");
            }
            if (partAForm.getMandal_id() == null) {
                partAForm.setMandal_id("0");
            }
            if (partAForm.getPensionPhase() == null) {
                //partAForm.setPensionPhase("ALL");
                partAForm.setPensionPhase("1");
            }


            // start
            yearList = commonDAO.getYears();
            financialYearList = commonDAO.getFinancialYears();
            partAForm.setFinancialYearList(financialYearList);
            partAForm.setYearList(yearList);
            if (partAForm.getYear() != null && !partAForm.getYear().equalsIgnoreCase("") && !partAForm.getYear().equalsIgnoreCase("0")) {
                monthList = commonDAO.getMonths(Integer.parseInt(partAForm.getYear()));
                partAForm.setMonthList(monthList);
            }

            if (partAForm.getTypeOfSearch() == null) {
                partAForm.setTypeOfSearch("1");
            }
            if (partAForm.getTypeOfSearch().equals("1")) {
                if (partAForm.getFromdate() == null) {
                    partAForm.setFromdate(fdate);
                }
                if (partAForm.getTodate() == null) {
                    partAForm.setTodate(tdate);
                }
                request.setAttribute("dates", "dates");
            } else if (partAForm.getTypeOfSearch().equals("2")) {
                fromdate = commonDAO.getMonthStartDate(ds, partAForm.getMonth(), partAForm.getYear());
                todate = commonDAO.getMonthEndtDate(ds, partAForm.getMonth(), partAForm.getYear());
                partAForm.setFromdate(fromdate);
                partAForm.setTodate(todate);
                if (!partAForm.getMonth().equals("0")) {
                    request.setAttribute("monthname", commonDAO.getMonth(partAForm.getMonth()));
                }
                request.setAttribute("mth", partAForm.getMonth());
                request.setAttribute("yr", partAForm.getYear());
                request.setAttribute("month", "month");
            } else if (partAForm.getTypeOfSearch().equals("3")) {
                String[] financialYear = partAForm.getFinancialYear().split("-");
                int givenYear = Integer.parseInt(financialYear[0]);
                fromdate = "01/04/" + givenYear;
                todate = dao.getAssignedDate(givenYear + 1, ds, fromdate);
                partAForm.setFromdate(fromdate);
                partAForm.setTodate(todate);
                request.setAttribute("fyr", partAForm.getFinancialYear());
                request.setAttribute("fyear", "fyear");
            }

            //end

            BeanUtils.copyProperties(partADTO, partAForm);

            ArrayList<PartADTO> partBList = null;
            partBList = new ArrayList<PartADTO>();
            partBList = dailyreportservice.statusreportforPartB(ds, partADTO);
            if (partBList.isEmpty()) {
                request.setAttribute("msg", "No Records Found");
            }
            request.setAttribute("dailyreportlist", partBList);
//            HttpSession session = request.getSession(true);
//            session.setAttribute("sadaremCodeAu",partBList);

            request.setAttribute("fromdate", partAForm.getFromdate());
            request.setAttribute("todate", partAForm.getTodate());
            request.setAttribute("pensionPhase", partAForm.getPensionPhase());
            request.setAttribute("type", partAForm.getTypeOfSearch());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward getReport(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        DataSource ds = null;

        CommonDAO commonDAO = new CommonDAO();
        ArrayList yearList = new ArrayList();
        ArrayList monthList = new ArrayList();
        ArrayList financialYearList = new ArrayList();

        PartAForm partAForm = (PartAForm) form;
        PartADTO partADTO = new PartADTO();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            PartADAO dDAO = new PartADAO();
            DailyDisabilityAndPercentageDAO dao = new DailyDisabilityAndPercentageDAO();
            DailyDisabilityAndPercentageService dailyreportservice =
                    DailyDisabilityAndPercentageServiceFactory.getDailyDisabilityAndPercentageServiceImpl();

            if (request.getParameter("distid") != null) {
                partAForm.setDistrict_id(request.getParameter("distid").toString());
            }
            if (request.getParameter("phase") != null) {
                partAForm.setPensionPhase(request.getParameter("phase").toString());
            }
            if (request.getParameter("type") != null) {
                partAForm.setTypeOfSearch(request.getParameter("type").toString());
            }
            if (request.getParameter("fdate") != null) {
                partAForm.setFromdate(request.getParameter("fdate").toString());
            }
            if (request.getParameter("tdate") != null) {
                partAForm.setTodate(request.getParameter("tdate").toString());
            }
            if (request.getParameter("mth") != null) {
                partAForm.setMonth(request.getParameter("mth").toString());
            }
            if (request.getParameter("yr") != null) {
                partAForm.setYear(request.getParameter("yr").toString());
            }
            if (request.getParameter("fyear") != null) {
                partAForm.setFinancialYear(request.getParameter("fyear").toString());
            }


            partAForm.setMandal_id("0");
            String districtname = dDAO.getDistrictOrMandalname(ds, partAForm.getDistrict_id(), "0");
            request.setAttribute("dname", districtname);
            // start
            yearList = commonDAO.getYears();
            financialYearList = commonDAO.getFinancialYears();
            partAForm.setFinancialYearList(financialYearList);
            partAForm.setYearList(yearList);

            if (partAForm.getTypeOfSearch().equals("1")) {
                request.setAttribute("dates", "dates");
            } else if (partAForm.getTypeOfSearch().equals("2")) {
                if (partAForm.getYear() != null && !partAForm.getYear().equalsIgnoreCase("") && !partAForm.getYear().equalsIgnoreCase("0")) {
                    monthList = commonDAO.getMonths(Integer.parseInt(partAForm.getYear()));
                    partAForm.setMonthList(monthList);
                }
                if (!partAForm.getMonth().equals("0")) {
                    request.setAttribute("monthname", commonDAO.getMonth(partAForm.getMonth()));
                }
                request.setAttribute("mth", partAForm.getMonth());
                request.setAttribute("yr", partAForm.getYear());
                request.setAttribute("month", "month");
            } else if (partAForm.getTypeOfSearch().equals("3")) {
                request.setAttribute("fyr", partAForm.getFinancialYear());
                request.setAttribute("fyear", "fyear");
            }
            //end

            BeanUtils.copyProperties(partADTO, partAForm);

            ArrayList<PartADTO> partBList = null;
            partBList = new ArrayList<PartADTO>();
            partBList = dailyreportservice.statusreportforPartB(ds, partADTO);
            if (partBList.isEmpty()) {
                request.setAttribute("msg", "No Records Found");
            }
            request.setAttribute("mandallist", partBList);

            request.setAttribute("fromdate", partAForm.getFromdate());
            request.setAttribute("todate", partAForm.getTodate());
            request.setAttribute("pensionPhase", partAForm.getPensionPhase());
            request.setAttribute("type", partAForm.getTypeOfSearch());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("success");
    }

    public ActionForward getVillageReport(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        DataSource ds = null;
        CommonDAO commonDAO = new CommonDAO();
        ArrayList yearList = new ArrayList();
        ArrayList monthList = new ArrayList();
        ArrayList financialYearList = new ArrayList();

        PartAForm partAForm = (PartAForm) form;
        PartADTO partADTO = new PartADTO();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            PartADAO dDAO = new PartADAO();
            DailyDisabilityAndPercentageDAO dao = new DailyDisabilityAndPercentageDAO();
            DailyDisabilityAndPercentageService dailyreportservice =
                    DailyDisabilityAndPercentageServiceFactory.getDailyDisabilityAndPercentageServiceImpl();

            if (request.getParameter("distid") != null) {
                partAForm.setDistrict_id(request.getParameter("distid").toString());
            }
            if (request.getParameter("phase") != null) {
                partAForm.setPensionPhase(request.getParameter("phase").toString());
            }
            if (request.getParameter("fdate") != null) {
                partAForm.setFromdate(request.getParameter("fdate"));
            }
            if (request.getParameter("tdate") != null) {
                partAForm.setTodate(request.getParameter("tdate"));
            }
            if (request.getParameter("mandal") != null) {
                partAForm.setMandal_id(request.getParameter("mandal").toString());
            }
            if (request.getParameter("type") != null) {
                partAForm.setTypeOfSearch(request.getParameter("type").toString());
            }
            if (request.getParameter("mth") != null) {
                partAForm.setMonth(request.getParameter("mth").toString());
            }
            if (request.getParameter("yr") != null) {
                partAForm.setYear(request.getParameter("yr").toString());
            }
            if (request.getParameter("fyear") != null) {
                partAForm.setFinancialYear(request.getParameter("fyear").toString());
            }

            String districtname = dDAO.getDistrictOrMandalname(ds, partAForm.getDistrict_id(), "0");
            request.setAttribute("dname", districtname);

            String mandalname = dDAO.getDistrictOrMandalname(ds, partAForm.getDistrict_id(), partAForm.getMandal_id());
            request.setAttribute("mname", mandalname);

            // start
            yearList = commonDAO.getYears();
            financialYearList = commonDAO.getFinancialYears();
            partAForm.setFinancialYearList(financialYearList);
            partAForm.setYearList(yearList);
            if (partAForm.getYear() != null && !partAForm.getYear().equalsIgnoreCase("") && !partAForm.getYear().equalsIgnoreCase("0")) {
                monthList = commonDAO.getMonths(Integer.parseInt(partAForm.getYear()));
                partAForm.setMonthList(monthList);
            }
            if (partAForm.getTypeOfSearch().equals("1")) {
                request.setAttribute("dates", "dates");
            } else if (partAForm.getTypeOfSearch().equals("2")) {
                if (!partAForm.getMonth().equals("0")) {
                    request.setAttribute("monthname", commonDAO.getMonth(partAForm.getMonth()));
                }
                request.setAttribute("yr", partAForm.getYear());
                request.setAttribute("month", "month");
            } else if (partAForm.getTypeOfSearch().equals("3")) {
                request.setAttribute("fyr", partAForm.getFinancialYear());
                request.setAttribute("fyear", "fyear");
            }
            //end

            BeanUtils.copyProperties(partADTO, partAForm);

            ArrayList<PartADTO> partBList = null;
            partBList = new ArrayList<PartADTO>();
            partBList = dailyreportservice.statusreportforPartB(ds, partADTO);
            if (partBList.isEmpty()) {
                request.setAttribute("msg", "No Records Found");
            }
            request.setAttribute("villagelist", partBList);

            request.setAttribute("fromdate", partAForm.getFromdate());
            request.setAttribute("todate", partAForm.getTodate());
            request.setAttribute("pensionPhase", partAForm.getPensionPhase());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("success");
    }

    public ActionForward getExcel(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";

        try {
            HttpSession session = request.getSession(true);
            session.getAttribute("excel");
            if (request.getParameter("id").equals("0")) {
                request.setAttribute("dailyreportlist", session.getAttribute("excel"));
            } else if (request.getParameter("id").equals("1")) {
                request.setAttribute("mandallist", session.getAttribute("excel"));
            } else if (request.getParameter("id").equals("2")) {
                request.setAttribute("villagelist", session.getAttribute("excel"));
            }





        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("excel");
    }
}
