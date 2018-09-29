/*
 * SurgeryReportAction.java
 *
 * Created on August 4, 2008, 5:57 PM
 */
package org.bf.disability.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.DailyDisabilityAndPercentageForm;
import org.bf.disability.dto.DailyDisabilityAndPercentageDTO;
import org.bf.disability.service.DailyDisabilityAndPercentageService;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.bf.disability.servicefactory.DailyDisabilityAndPercentageServiceFactory;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.form.PartAForm;
import javax.servlet.http.HttpSession;
import org.apache.struts.actions.DispatchAction;
import java.text.SimpleDateFormat;

/**
 * 
 * @description this action class will be used to generate daily disability and percentage report
 * @author ragahavendra_t
 * @version 1.0
 */
public class DailyDisabilityAndPercentageAction extends DispatchAction {

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
    public ActionForward getDisabilityWise(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        DataSource ds = null;

        //DataSource ds=getDataSource(request);


        ArrayList reportlist = new ArrayList();
        ActionMessages actionMessages = null;
        DailyDisabilityAndPercentageForm dailyreportform =
                (DailyDisabilityAndPercentageForm) form;
        DailyDisabilityAndPercentageDTO dailyreportdto =
                new DailyDisabilityAndPercentageDTO();
        DailyDisabilityAndPercentageService dailyreportservice =
                DailyDisabilityAndPercentageServiceFactory.getDailyDisabilityAndPercentageServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            String fromdate = dailyreportform.getFromdate();
            String todate = dailyreportform.getTodate();
            request.setAttribute("fromdate_jsp", fromdate);
            request.setAttribute("todate_jsp", todate);
            BeanUtils.copyProperties(dailyreportdto, dailyreportform);
            reportlist = dailyreportservice.getDisabilityWise(ds, dailyreportdto);
            if (reportlist.isEmpty()) {
                target = "ReportException";
            }
            String Fdate = (String) request.getAttribute("fromdate_jsp");
            String Tdate = (String) request.getAttribute("todate_jsp");
            dailyreportform.setFromdate(Fdate);
            dailyreportform.setTodate(Tdate);
            request.setAttribute("dailyreportlist", reportlist);
            request.setAttribute("From_Date", dailyreportform.getFromdate());
            request.setAttribute("To_Date", dailyreportform.getTodate());
            target = "success";
        } catch (SADAREMDBException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    /**
     * 
     * @desription this method is used to generate disability percentage wise report
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws java.lang.Exception 
     * @return Action Forward
     */
    public ActionForward getDisabilityPercentage(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        DataSource ds = null;

        //DataSource ds=getDataSource(request);

        ArrayList reportlist = new ArrayList();
        String target = "success";
        ActionMessages actionMessages = null;
        DailyDisabilityAndPercentageDTO dailyreportdto1;
        DailyDisabilityAndPercentageForm dailyreportform =
                (DailyDisabilityAndPercentageForm) form;
        DailyDisabilityAndPercentageDTO dailyreportdto =
                new DailyDisabilityAndPercentageDTO();
        DailyDisabilityAndPercentageService dailyreportservice =
                DailyDisabilityAndPercentageServiceFactory.getDailyDisabilityAndPercentageServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String fromdate = dailyreportform.getFromdate();
            String todate = dailyreportform.getTodate();
            request.setAttribute("fromdate_jsp", fromdate);
            request.setAttribute("todate_jsp", todate);
            BeanUtils.copyProperties(dailyreportdto, dailyreportform);
            reportlist = dailyreportservice.getDisabilityPercentage(ds, dailyreportdto);
            Iterator ite1 = reportlist.iterator();
            while (ite1.hasNext()) {
                dailyreportdto1 = (DailyDisabilityAndPercentageDTO) ite1.next();
                if (dailyreportdto1.getNo_of_persons().equals("0")
                        && dailyreportdto1.getEligible_persons().equals("0")
                        && dailyreportdto1.getRejected_persons().equals("0")
                        && dailyreportdto1.getLessthan_fourty().equals("0")
                        && dailyreportdto1.getFourty_to_sixty().equals("0")
                        && dailyreportdto1.getSixtyone_to_eighty().equals("0")
                        && dailyreportdto1.getAbove_eighty().equals("0")) {
                    target = "ReportException";
                }
            }
            String Fdate = (String) request.getAttribute("fromdate_jsp");
            String Tdate = (String) request.getAttribute("todate_jsp");
            dailyreportform.setFromdate(Fdate);
            dailyreportform.setTodate(Tdate);
            request.setAttribute("dailyreportlist", reportlist);
            request.setAttribute("From_Date", dailyreportform.getFromdate());
            request.setAttribute("To_Date", dailyreportform.getTodate());
            target = "success";
        } catch (SADAREMDBException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }
    //personpersonaldetails report

    public ActionForward getPersonPersonalDetailsWise(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        DataSource ds = null;


        //DataSource ds=getDataSource(request);


        ArrayList reportlist = new ArrayList();
        ActionMessages actionMessages = null;
        HttpSession session = request.getSession(true);

        String districtid = (String) session.getAttribute("districtId");

        PartAForm partAForm = (PartAForm) form;
        PartADTO partADTO = new PartADTO();
        DailyDisabilityAndPercentageService dailyreportservice =
                DailyDisabilityAndPercentageServiceFactory.getDailyDisabilityAndPercentageServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            // String fromdate=dailyreportform.getFromdate();
            String fromdate = partAForm.getFromdate();
            String todate = partAForm.getTodate();
            request.setAttribute("fromdate_jsp", fromdate);
            request.setAttribute("todate_jsp", todate);
            BeanUtils.copyProperties(partADTO, partAForm);
            reportlist = dailyreportservice.getPersonPersonalDetailswise(ds, partADTO, districtid);
            if (reportlist.isEmpty()) {

                target = "ReportException";
            }
            String Fdate = (String) request.getAttribute("fromdate_jsp");
            String Tdate = (String) request.getAttribute("todate_jsp");
            partAForm.setFromdate(Fdate);
            partAForm.setTodate(Tdate);
            request.setAttribute("dailyreportlist", reportlist);
            request.setAttribute("From_Date", partAForm.getFromdate());
            request.setAttribute("To_Date", partAForm.getTodate());
            target = "success";
        } catch (SADAREMDBException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    public ActionForward getReportForLoginWiseCount(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        DataSource ds = null;

        //DataSource ds=getDataSource(request);

        ArrayList reportlist = new ArrayList();
        ActionMessages actionMessages = null;
        HttpSession session = request.getSession(true);
        String districtid = (String) session.getAttribute("districtId");
        PartAForm partAForm = (PartAForm) form;
        PartADTO partADTO = new PartADTO();
        DailyDisabilityAndPercentageService dailyreportservice =
                DailyDisabilityAndPercentageServiceFactory.getDailyDisabilityAndPercentageServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            // String fromdate=dailyreportform.getFromdate();
            String fromdate = partAForm.getFromdate();
            String todate = partAForm.getTodate();
            request.setAttribute("fromdate_jsp", fromdate);
            request.setAttribute("todate_jsp", todate);
            BeanUtils.copyProperties(partADTO, partAForm);
            reportlist = dailyreportservice.getReportForLoginWiseCount(ds, partADTO, districtid);
            if (reportlist.isEmpty()) {
                target = "ReportException";
            }
            String Fdate = (String) request.getAttribute("fromdate_jsp");
            String Tdate = (String) request.getAttribute("todate_jsp");
            partAForm.setFromdate(Fdate);
            partAForm.setTodate(Tdate);
            request.setAttribute("dailyreportlist", reportlist);
            if (reportlist.size() == 0) {
                request.setAttribute("noResults", "noResults Found");
            }
            request.setAttribute("From_Date", partAForm.getFromdate());
            request.setAttribute("To_Date", partAForm.getTodate());
            target = "success";
        } catch (SADAREMDBException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    public ActionForward getStatusCountForMandal(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        DataSource ds = null;


        //DataSource ds=getDataSource(request);

        ArrayList reportlist = new ArrayList();
        ActionMessages actionMessages = null;
        HttpSession session = request.getSession(true);
        String districtid = (String) session.getAttribute("districtId");
        PartAForm partAForm = (PartAForm) form;
        PartADTO partADTO = new PartADTO();
        DailyDisabilityAndPercentageService dailyreportservice =
                DailyDisabilityAndPercentageServiceFactory.getDailyDisabilityAndPercentageServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            // String fromdate=dailyreportform.getFromdate();
            String fromdate = partAForm.getFromdate();
            String todate = partAForm.getTodate();
            request.setAttribute("fromdate_jsp", fromdate);
            request.setAttribute("todate_jsp", todate);
            BeanUtils.copyProperties(partADTO, partAForm);
            reportlist = dailyreportservice.getStatusCountForMandal(ds, partADTO, districtid);
            if (reportlist.isEmpty()) {
                target = "ReportException";
            }
            String Fdate = (String) request.getAttribute("fromdate_jsp");
            String Tdate = (String) request.getAttribute("todate_jsp");
            partAForm.setFromdate(Fdate);
            partAForm.setTodate(Tdate);
            request.setAttribute("dailyreportlist", reportlist);
            request.setAttribute("From_Date", partAForm.getFromdate());
            request.setAttribute("To_Date", partAForm.getTodate());
            target = "success";
        } catch (SADAREMDBException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    public ActionForward getStatusCountForVillage(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        DataSource ds = null;

        //DataSource ds=getDataSource(request);

        ArrayList reportlist = new ArrayList();
        ActionMessages actionMessages = null;
        HttpSession session = request.getSession(true);
        String districtid = (String) session.getAttribute("districtId");
        String mandalid = (String) request.getParameter("mandalid");
        PartAForm partAForm = (PartAForm) form;
        PartADTO partADTO = new PartADTO();
        DailyDisabilityAndPercentageService dailyreportservice =
                DailyDisabilityAndPercentageServiceFactory.getDailyDisabilityAndPercentageServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            String fromdate = request.getParameter("fromdate");
            String todate = request.getParameter("todate");
            request.setAttribute("fromdate_jsp", fromdate);
            request.setAttribute("todate_jsp", todate);
            BeanUtils.copyProperties(partADTO, partAForm);
            reportlist = dailyreportservice.getStatusCountForVillage(ds, partADTO, districtid, mandalid);
            if (reportlist.isEmpty()) {

                target = "ReportException";
            }
            String Fdate = (String) request.getAttribute("fromdate_jsp");
            String Tdate = (String) request.getAttribute("todate_jsp");
            partAForm.setFromdate(Fdate);
            partAForm.setTodate(Tdate);
            request.setAttribute("dailyreportlist", reportlist);
            request.setAttribute("From_Date", partAForm.getFromdate());
            request.setAttribute("To_Date", partAForm.getTodate());
            target = "success";
        } catch (SADAREMDBException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }
    /*Mandalwise Status Count For PartA */

    public ActionForward getStatusCountForPartA(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        DataSource ds = null;

        //DataSource ds=getDataSource(request);

        ArrayList reportlist = new ArrayList();
        ActionMessages actionMessages = null;
        HttpSession session = request.getSession(true);
        String districtid = (String) request.getParameter("districtid");
        String districtname = (String) request.getParameter("districtname");
        PartAForm partAForm = (PartAForm) form;
        PartADTO partADTO = new PartADTO();
        DailyDisabilityAndPercentageService dailyreportservice =
                DailyDisabilityAndPercentageServiceFactory.getDailyDisabilityAndPercentageServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            // String fromdate=dailyreportform.getFromdate();
            //String fromdate = partAForm.getFromdate();
            //String todate = partAForm.getTodate();
            String fromdate = request.getParameter("fromdate");
            String todate = request.getParameter("todate");
            request.setAttribute("fromdate_jsp", fromdate);
            request.setAttribute("todate_jsp", todate);
            BeanUtils.copyProperties(partADTO, partAForm);
            reportlist = dailyreportservice.getStatusCountForPartA(ds, partADTO, districtid);
            if (reportlist.isEmpty()) {
                target = "ReportException";
            }
            String Fdate = (String) request.getAttribute("fromdate_jsp");
            String Tdate = (String) request.getAttribute("todate_jsp");
            partAForm.setFromdate(Fdate);
            partAForm.setTodate(Tdate);
            request.setAttribute("dailyreportlist", reportlist);
            request.setAttribute("fromdate_jsp", partAForm.getFromdate());
            request.setAttribute("todate_jsp", partAForm.getTodate());
            int existingPensionersCount = 0, partACount = 0;
            int orthoCount = 0, visualCount = 0, hearingCount = 0, MRCount = 0;
            int MICount = 0, MDCount = 0;
            Iterator iter = reportlist.iterator();
            /*this is for find the district wise total of all the values displayed in the report*/
            while (iter.hasNext()) {
                PartADTO partAdto = (PartADTO) iter.next();
                existingPensionersCount = existingPensionersCount + Integer.parseInt(partAdto.getExistingpensioners());
                partACount = partACount + Integer.parseInt(partAdto.getPartacount());
                orthoCount = orthoCount + Integer.parseInt(partAdto.getOrtho());
                visualCount = visualCount + Integer.parseInt(partAdto.getVisual());
                hearingCount = hearingCount + Integer.parseInt(partAdto.getHearing());
                MRCount = MRCount + Integer.parseInt(partAdto.getMentalretardation());
                MICount = MICount + Integer.parseInt(partAdto.getMentalillness());
                MDCount = MDCount + Integer.parseInt(partAdto.getMultipledisability());
            }
            request.setAttribute("existingcount", existingPensionersCount);
            request.setAttribute("partACount", partACount);
            request.setAttribute("orthoCount", orthoCount);
            request.setAttribute("visualCount", visualCount);
            request.setAttribute("hearingCount", hearingCount);
            request.setAttribute("MRCount", MRCount);
            request.setAttribute("MICount", MICount);
            request.setAttribute("MDCount", MDCount);
            target = "success";
        } catch (SADAREMDBException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }
    /* Distrtict wise status report for PartA */

    public ActionForward distWisestatusreportforPartA(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        DataSource ds = null;

        //  String currentRoleId = null;
        //  boolean editPermissionFlag = false;
        //DataSource ds=getDataSource(request);

        ArrayList reportlist = new ArrayList();
        // ArrayList rolelist = new ArrayList();
        ActionMessages actionMessages = null;
        // String roleid = "";
        HttpSession session = request.getSession(true);
        // String loginid = (String) session.getAttribute("loginid");
        PartAForm partAForm = (PartAForm) form;
        PartADTO partADTO = new PartADTO();
        //CommonDetails commondetails = new CommonDetails();
        /*Based on RoleID Display the result either district wise
         * or mandal wise.(if roleid='4' display only mandalto village
         */
        String districtid = (String) session.getAttribute("districtId");
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            BeanUtils.copyProperties(partADTO, partAForm);
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
        DailyDisabilityAndPercentageService dailyreportservice =
                DailyDisabilityAndPercentageServiceFactory.getDailyDisabilityAndPercentageServiceImpl();
        try {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String currentDate = dateFormat.format(date);
            if (partAForm.getFromdate() == null) {
                partAForm.setFromdate("01/01/2010");

            }
            if (partAForm.getTodate() == null) {
                partAForm.setTodate(currentDate);
            }

            String fromdate = partAForm.getFromdate();
            String todate = partAForm.getTodate();
            request.setAttribute("fromdate_jsp", fromdate);
            request.setAttribute("todate_jsp", todate);
            partAForm.setFromdate(fromdate);
            partAForm.setTodate(todate);
            BeanUtils.copyProperties(partADTO, partAForm);
            reportlist = dailyreportservice.distWisestatusreportforPartA(ds, partADTO, districtid);
            if (reportlist.isEmpty()) {
                target = "ReportException";
            }
            int existingPensionersCount = 0, partACount = 0;
            int orthoCount = 0, visualCount = 0, hearingCount = 0, MRCount = 0;
            int MICount = 0, MDCount = 0;
            Iterator iter = reportlist.iterator();
            while (iter.hasNext()) {
                PartADTO partAdto = (PartADTO) iter.next();
                existingPensionersCount = existingPensionersCount
                        + Integer.parseInt(partAdto.getExistingpensioners());
                partACount = partACount + Integer.parseInt(partAdto.getPartacount());
                orthoCount = orthoCount + Integer.parseInt(partAdto.getOrtho());
                visualCount = visualCount + Integer.parseInt(partAdto.getVisual());
                hearingCount = hearingCount + Integer.parseInt(partAdto.getHearing());
                MRCount = MRCount + Integer.parseInt(partAdto.getMentalretardation());
                MICount = MICount + Integer.parseInt(partAdto.getMentalillness());
                MDCount = MDCount + Integer.parseInt(partAdto.getMultipledisability());

            }
            request.setAttribute("existingcount", existingPensionersCount);
            request.setAttribute("partACount", partACount);
            request.setAttribute("orthoCount", orthoCount);
            request.setAttribute("visualCount", visualCount);
            request.setAttribute("hearingCount", hearingCount);
            request.setAttribute("MRCount", MRCount);
            request.setAttribute("MICount", MICount);
            request.setAttribute("MDCount", MDCount);
            // String Fdate = (String) request.getAttribute("fromdate_jsp");
            // String Tdate = (String) request.getAttribute("todate_jsp");
            // partAForm.setFromdate(Fdate);
            // partAForm.setTodate(Tdate);
            request.setAttribute("dailyreportlist", reportlist);
            request.setAttribute("From_Date", partAForm.getFromdate());
            request.setAttribute("To_Date", partAForm.getTodate());
            target = "success";
        } catch (SADAREMDBException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }

        //   }
        //   }


        return mapping.findForward(target);
    }

    public ActionForward distWisestatusreportforPartB(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        String pensionPhase = null;
        DataSource ds = null;



        ArrayList reportlist = new ArrayList();
        ActionMessages actionMessages = null;
        HttpSession session = request.getSession(true);
        PartAForm partAForm = (PartAForm) form;
        PartADTO partADTO = new PartADTO();
        String fromdate = partAForm.getFromdate();
        String todate = partAForm.getTodate();
        pensionPhase = partAForm.getPensionPhase();
        request.setAttribute("formdate", fromdate);
        request.setAttribute("todate", todate);
        request.setAttribute("pensionPhase", pensionPhase);
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            DailyDisabilityAndPercentageService dailyreportservice =
                    DailyDisabilityAndPercentageServiceFactory.getDailyDisabilityAndPercentageServiceImpl();
            String districtid = (String) session.getAttribute("districtId");
            BeanUtils.copyProperties(partADTO, partAForm);
            BeanUtils.copyProperties(partADTO, partAForm);
            reportlist = dailyreportservice.distWisestatusreportforPartB(ds, partADTO, districtid);
            if (reportlist.isEmpty()) {

                target = "ReportException";
            }
            request.setAttribute("dailyreportlist", reportlist);
            target = "success";
        } catch (SADAREMDBException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    public ActionForward mandalWisestatusreportforPartB(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        DataSource ds = null;


        ArrayList reportlist = new ArrayList();
        ActionMessages actionMessages = null;
        String districtid = (String) request.getParameter("districtid");
        String districtname = (String) request.getParameter("districtname");
        PartAForm partAForm = (PartAForm) form;
        PartADTO partADTO = new PartADTO();
        DailyDisabilityAndPercentageService dailyreportservice =
                DailyDisabilityAndPercentageServiceFactory.getDailyDisabilityAndPercentageServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String fromdate = partAForm.getFromdate();
            String todate = partAForm.getTodate();
            request.setAttribute("fromdate_jsp", fromdate);
            request.setAttribute("todate_jsp", todate);
            BeanUtils.copyProperties(partADTO, partAForm);
            reportlist = dailyreportservice.mandalWisestatusreportforPartB(ds, partADTO, districtid);
            if (reportlist.isEmpty()) {
                target = "ReportException";
            }
            request.setAttribute("partBMandalReport", reportlist);
            request.setAttribute("districtname", districtname);
            request.setAttribute("districtid", districtid);
            target = "success";
        } catch (SADAREMDBException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    public ActionForward villageWiseStatusReportForPartB(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        String target = "success";
        DataSource ds = null;

        //DataSource ds=getDataSource(request);

        ArrayList reportlist = new ArrayList();
        ActionMessages actionMessages = null;
        String districtid = (String) request.getParameter("districtid");
        String districtname = (String) request.getParameter("districtname");
        String mandalid = (String) request.getParameter("mandalid");
        String mandalname = (String) request.getParameter("mandalname");
        String fromdate = (String) request.getParameter("fromdate");
        String todate = (String) request.getParameter("todate");
        request.setAttribute("districtname", districtname);
        request.setAttribute("mandalname", mandalname);
        request.setAttribute("districtid", districtid);
        request.setAttribute("mandalid", mandalid);
        request.setAttribute("fromdate", fromdate);
        request.setAttribute("todate", todate);
        PartAForm partAForm = (PartAForm) form;
        PartADTO partADTO = new PartADTO();
        DailyDisabilityAndPercentageService dailyreportservice =
                DailyDisabilityAndPercentageServiceFactory.getDailyDisabilityAndPercentageServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            BeanUtils.copyProperties(partADTO, partAForm);
            reportlist = dailyreportservice.villageWiseStatusReportForPartB(ds, partADTO, districtid, mandalid);
            if (reportlist.isEmpty()) {
                target = "ReportException";
            }
            request.setAttribute("partBVillageReport", reportlist);
            target = "success";
        } catch (SADAREMDBException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    /**
     *
     * @description this method is used to generate the Status report of Part - B
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws java.lang.Exception
     * @return Action Forward
     */
    public ActionForward statusreportforPartB(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {


        String target = null;
        DataSource ds = null;


        ActionMessages actionMessages = null;
        PartAForm partAForm = (PartAForm) form;
        PartADTO partADTO = new PartADTO();
        DailyDisabilityAndPercentageService dailyreportservice =
                DailyDisabilityAndPercentageServiceFactory.getDailyDisabilityAndPercentageServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            BeanUtils.copyProperties(partADTO, partAForm);
            ArrayList<PartADTO> partBList = null;
            partBList = new ArrayList<PartADTO>();
            partBList = dailyreportservice.statusreportforPartB(ds, partADTO);
            if (partBList.isEmpty()) {
                request.setAttribute("msg", "No Records Found");
                target = "ReportException";
            }
            request.setAttribute("dailyreportlist", partBList);
            target = "success";

        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    /**
     *
     * @description this method is used to generate the Status report of Part - B
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws java.lang.Exception
     * @return Action Forward
     */
    public ActionForward getPensionsDetails(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {


        String target = null;
        DataSource ds = null;

        String phaseName = null;

        ActionMessages actionMessages = null;
        PartAForm partAForm = (PartAForm) form;
        // PartADTO partADTO = new PartADTO();
        String distCode = null;
        DailyDisabilityAndPercentageService dailyreportservice =
                DailyDisabilityAndPercentageServiceFactory.getDailyDisabilityAndPercentageServiceImpl();
        ArrayList partBList = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            if (request.getParameter("dID").toString().equals("") || request.getParameter("dID") == null) {
                distCode = request.getParameter("discode").toString();
            } else {
                distCode = request.getParameter("dID").toString();
            }

            partBList = dailyreportservice.statusReportforPartBAssessed(ds, distCode, request.getParameter("mID"), request.getParameter("vID").toString(), request.getParameter("phase").toString(), request.getParameter("pensionStatus").toString());
            if (partBList.isEmpty()) {
                request.setAttribute("msg", "No Records Found");
                target = "ReportException";
            }
            request.setAttribute("dailyReportList", partBList);
            target = "statusReportforPartBAssessed";

        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }
}
