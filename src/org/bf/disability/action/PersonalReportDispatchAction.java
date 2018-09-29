/*
 * PhysicalReportAction.java
 *
 * Created on July 29, 2008, 5:51 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.CommonReportDTO;
import org.bf.disability.form.CommonReportForm;
import org.bf.disability.service.CommonPersonalReportService;
import org.bf.disability.servicefactory.CommonPersonalReportServiceFactory;
import org.bf.disability.util.CommonReportDetails;

/**
 *
 * @author ragahavendra_t
 */
public class PersonalReportDispatchAction extends BaseDispatchAction {

    CommonReportDTO surgeryreportdto = new CommonReportDTO();
    String success = "repeat";
    String failure = "failure";
    DataSource datasource = null;

    public ActionForward getdistrictPersonalReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        CommonReportForm surgeryform = (CommonReportForm) form;

        ArrayList reportlist = new ArrayList();
        //DataSource datasource= getDataSource(request);


        ActionMessages actionMessages = null;
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            String fromdate = request.getParameter("FromDate");
            String todate = request.getParameter("ToDate");
            HttpSession session = request.getSession();
            String surgerytype = (String) session.getAttribute("SurgeryType");
            String District_Id = request.getParameter("districtid");
            String s = (String) request.getAttribute("Table_Value");
            String mandalID = request.getParameter("FieldValue");
            surgeryform.setFieldvalue(mandalID);
            String secondfieldvalue = request.getParameter("SecondFieldValue");
            surgeryform.setSecondfieldvalue(secondfieldvalue);
            CommonPersonalReportService reportservice =
                    CommonPersonalReportServiceFactory.getCommonPersonalReportServiceImpl();
            surgeryform.setDistrictid(District_Id);
            CommonReportDetails commonreportdetails =
                    new CommonReportDetails();
            surgeryform = commonreportdetails.getCommonReportDetails(surgerytype, surgeryform);
            surgeryform.setFromdate(fromdate);
            surgeryform.setTodate(todate);
            BeanUtils.copyProperties(surgeryreportdto, surgeryform);


            int totalresults = Integer.parseInt(request.getParameter("distcount"));
            String totresstr = String.valueOf(totalresults);
            reportlist = reportservice.getPersonalReportDetails(datasource, surgeryreportdto, surgerytype, 1, totalresults);
            session.setAttribute("arraylist", reportlist);

            String actionnamewithdo = request.getRequestURI();
            String actionnamewithdoarr[] = actionnamewithdo.split("/");
            String url = actionnamewithdoarr[2] + "?getdistrictPersonalReport=getdistrictPersonalReport&districtid=" + District_Id + "&FieldValue=" + mandalID + "&SecondFieldValue=" + secondfieldvalue + "&FromDate=" + fromdate + "&ToDate=" + todate + "&distcount=" + totresstr;
            request.setAttribute("url", url);
            CommonDAO commonDAO = new CommonDAO();
            ArrayList startAndEndList = new ArrayList();
            startAndEndList = commonDAO.populateStartEnd(request, totalresults);
            int start = 0;
            int endrange = 0;
            start = ((Integer) startAndEndList.get(0)).intValue();
            endrange = ((Integer) startAndEndList.get(1)).intValue();
            reportlist = reportservice.getPersonalReportDetails(datasource, surgeryreportdto, surgerytype, start, endrange);

            surgeryform.setFromdate(fromdate);
            surgeryform.setTodate(todate);
            String value = surgeryform.getFieldvalue();
            request.setAttribute("value", value);
            String secondvalue = surgeryform.getSecondfieldvalue();
            String mandalid = surgeryform.getMandalid();
            request.setAttribute("secondvalue", secondvalue);
            request.setAttribute("mandalid", mandalid);
            request.setAttribute("secondfieldvalue", secondfieldvalue);
            request.setAttribute("districtid", District_Id);
            String fieldvalue = (String) request.getAttribute("deviprasad");
            request.setAttribute("fieldvalue", fieldvalue);
            request.setAttribute("reportlist", reportlist);
            request.setAttribute("fromDate", fromdate);
            request.setAttribute("toDate", todate);
            request.setAttribute("reportlist", reportlist);
            session.setAttribute("arraylist1", reportlist);

            if (!reportlist.isEmpty()) {
                success = "repeat";
            } else {
                success = "failure";
            }

        } catch (SADAREMDBException sADAREMException) {
            success = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            success = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(success);
    }

    public ActionForward getmandalPersonalReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        CommonReportForm surgeryform = (CommonReportForm) form;
        ArrayList reportlist = new ArrayList();
        //DataSource datasource= getDataSource(request);


        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            String surgerytype = (String) session.getAttribute("SurgeryType");
            String District_Id = request.getParameter("districtid");
            String mandalid = request.getParameter("mandalid");
            String fieldvalue = request.getParameter("fieldvalue");
            String secondfieldvalue = request.getParameter("secondfieldvalue");
            String fromdate = request.getParameter("FromDate");
            String todate = request.getParameter("ToDate");
            surgeryform.setFieldvalue(fieldvalue);
            surgeryform.setSecondfieldvalue(secondfieldvalue);
            surgeryform.setFieldvalue(fieldvalue);
            surgeryform.setDistrictid(District_Id);
            CommonPersonalReportService reportservice =
                    CommonPersonalReportServiceFactory.getCommonPersonalReportServiceImpl();
            surgeryform.setDistrictid(District_Id);
            surgeryform.setMandalid(mandalid);
            CommonReportDetails commonreportdetails = new CommonReportDetails();
            surgeryform = commonreportdetails.getCommonReportDetails(surgerytype, surgeryform);
            surgeryform.setFromdate(fromdate);
            surgeryform.setTodate(todate);
            BeanUtils.copyProperties(surgeryreportdto, surgeryform);

            int totalresults = Integer.parseInt(request.getParameter("distcount"));


            reportlist = reportservice.getPersonalReportformandalDetails(datasource, surgeryreportdto, surgerytype, 1, totalresults);
            session.setAttribute("arraylist", reportlist);

            String totresstr = String.valueOf(totalresults);
            String actionnamewithdo = request.getRequestURI();
            String actionnamewithdoarr[] = actionnamewithdo.split("/");
            String url = actionnamewithdoarr[2] + "?getmandalPersonalReport=getmandalPersonalReport&districtid=" + District_Id + "&fieldvalue=" + fieldvalue + "&secondfieldvalue=" + secondfieldvalue + "&FromDate=" + fromdate + "&ToDate=" + todate + "&distcount=" + totresstr + "&mandalid=" + mandalid;
            request.setAttribute("url", url);
            CommonDAO commonDAO = new CommonDAO();
            ArrayList startAndEndList = new ArrayList();
            startAndEndList = commonDAO.populateStartEnd(request, totalresults);
            int start = 0;
            int endrange = 0;
            start = ((Integer) startAndEndList.get(0)).intValue();
            endrange = ((Integer) startAndEndList.get(1)).intValue();


            reportlist = reportservice.getPersonalReportformandalDetails(datasource, surgeryreportdto, surgerytype, start, endrange);
            String value1 = surgeryform.getFieldvalue();
            surgeryform.setFromdate(fromdate);
            surgeryform.setTodate(todate);
            request.setAttribute("mandalid", mandalid);
            request.setAttribute("secondfieldvalue", secondfieldvalue);
            request.setAttribute("districtid", District_Id);
            request.setAttribute("fieldvalue", fieldvalue);
            request.setAttribute("reportlist", reportlist);
            request.setAttribute("fromDate", fromdate);
            request.setAttribute("toDate", todate);
            request.setAttribute("reportlist", reportlist);
            session.setAttribute("arraylist1", reportlist);
            if (!reportlist.isEmpty()) {
                success = "repeat";
            } else {
                success = "failure";
            }
        } catch (SADAREMDBException sADAREMException) {
            success = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            success = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(success);
    }

    public ActionForward getvillagePersonalReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        CommonReportForm surgeryform = (CommonReportForm) form;
        ArrayList reportlist = new ArrayList();
        //DataSource datasource= getDataSource(request);


        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            String surgerytype = (String) session.getAttribute("SurgeryType");
            String District_Id = request.getParameter("districtid");
            String fromdate = request.getParameter("FromDate");
            String todate = request.getParameter("ToDate");
            String mandalid = request.getParameter("mandalid");
            String fieldvalue = request.getParameter("fieldvalue");
            String secondfieldvalue = request.getParameter("secondfieldvalue");
            surgeryform.setSecondfieldvalue(secondfieldvalue);
            surgeryform.setFieldvalue(fieldvalue);
            surgeryform.setDistrictid(District_Id);
            surgeryform.setMandalid(mandalid);
            String villageid = request.getParameter("villageid");
            surgeryform.setVillageid(villageid);
            CommonPersonalReportService reportservice =
                    CommonPersonalReportServiceFactory.getCommonPersonalReportServiceImpl();
            CommonReportDetails commonreportdetails =
                    new CommonReportDetails();
            surgeryform = commonreportdetails.getCommonReportDetails(surgerytype, surgeryform);
            surgeryform.setFromdate(fromdate);
            surgeryform.setTodate(todate);
            BeanUtils.copyProperties(surgeryreportdto, surgeryform);


            int totalresults = Integer.parseInt(request.getParameter("distcount"));


            reportlist = reportservice.getPersonalReportforvillageDetails(datasource, surgeryreportdto, surgerytype, 1, totalresults);
            session.setAttribute("arraylist", reportlist);

            String totresstr = String.valueOf(totalresults);
            String actionnamewithdo = request.getRequestURI();
            String actionnamewithdoarr[] = actionnamewithdo.split("/");
            String url = actionnamewithdoarr[2] + "?getvillagePersonalReport=getvillagePersonalReport&districtid=" + District_Id + "&fieldvalue=" + fieldvalue + "&secondfieldvalue=" + secondfieldvalue + "&FromDate=" + fromdate + "&ToDate=" + todate + "&distcount=" + totresstr + "&mandalid=" + mandalid + "&villageid=" + villageid;
            request.setAttribute("url", url);
            CommonDAO commonDAO = new CommonDAO();
            ArrayList startAndEndList = new ArrayList();
            startAndEndList = commonDAO.populateStartEnd(request, totalresults);
            int start = 0;
            int endrange = 0;
            start = ((Integer) startAndEndList.get(0)).intValue();
            endrange = ((Integer) startAndEndList.get(1)).intValue();

            reportlist = reportservice.getPersonalReportforvillageDetails(datasource, surgeryreportdto, surgerytype, start, endrange);
            String habaitationid = surgeryform.getHabitationid();
            surgeryform.setFromdate(fromdate);
            surgeryform.setTodate(todate);
            request.setAttribute("mandalid", mandalid);
            request.setAttribute("secondfieldvalue", secondfieldvalue);
            request.setAttribute("districtid", District_Id);
            request.setAttribute("fieldvalue", fieldvalue);
            request.setAttribute("villageid", villageid);
            request.setAttribute("habaitationid", habaitationid);
            request.setAttribute("reportlist", reportlist);
            request.setAttribute("fromDate", fromdate);
            request.setAttribute("toDate", todate);
            session.setAttribute("arraylist1", reportlist);
            if (!reportlist.isEmpty()) {
                success = "repeat";
            } else {
                success = "failure";
            }
        } catch (SADAREMDBException sADAREMException) {
            success = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            success = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(success);
    }

    public ActionForward gethabitationPersonalReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        CommonReportForm surgeryform = (CommonReportForm) form;
        ActionMessages actionMessages = null;
        ArrayList reportlist = new ArrayList();

        HttpSession session = request.getSession();
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            String surgerytype = (String) session.getAttribute("SurgeryType");
            String District_Id = request.getParameter("districtid");
            String fromdate = request.getParameter("FromDate");
            String todate = request.getParameter("ToDate");
            String mandalid = request.getParameter("mandalid");
            String villageid = request.getParameter("villageid");
            String fieldvalue = request.getParameter("fieldvalue");
            String secondfieldvalue = request.getParameter("secondfieldvalue");
            surgeryform.setSecondfieldvalue(secondfieldvalue);
            surgeryform.setFieldvalue(fieldvalue);
            surgeryform.setDistrictid(District_Id);
            surgeryform.setMandalid(mandalid);
            surgeryform.setVillageid(villageid);
            CommonPersonalReportService reportservice =
                    CommonPersonalReportServiceFactory.getCommonPersonalReportServiceImpl();
            CommonReportDetails commonreportdetails =
                    new CommonReportDetails();
            surgeryform = commonreportdetails.getCommonReportDetails(surgerytype, surgeryform);
            surgeryform.setFromdate(fromdate);
            surgeryform.setTodate(todate);
            BeanUtils.copyProperties(surgeryreportdto, surgeryform);
            String habitationid = surgeryform.getHabitationid();

            int totalresults = Integer.parseInt(request.getParameter("distcount"));

            reportlist = reportservice.getPersonalReportforhabitationDetails(datasource, surgeryreportdto, surgerytype, 1, totalresults);
            session.setAttribute("arraylist", reportlist);

            String totresstr = String.valueOf(totalresults);
            String actionnamewithdo = request.getRequestURI();
            String actionnamewithdoarr[] = actionnamewithdo.split("/");
            String url = actionnamewithdoarr[2] + "?gethabitationPersonalReport=gethabitationPersonalReport&districtid=" + District_Id + "&fieldvalue=" + fieldvalue + "&secondfieldvalue=" + secondfieldvalue + "&FromDate=" + fromdate + "&ToDate=" + todate + "&distcount=" + totresstr + "&mandalid=" + mandalid + "&villageid=" + villageid + "&habitationid=" + habitationid;
            request.setAttribute("url", url);
            CommonDAO commonDAO = new CommonDAO();
            ArrayList startAndEndList = new ArrayList();
            startAndEndList = commonDAO.populateStartEnd(request, totalresults);
            int start = 0;
            int endrange = 0;
            start = ((Integer) startAndEndList.get(0)).intValue();
            endrange = ((Integer) startAndEndList.get(1)).intValue();


            reportlist = reportservice.getPersonalReportforhabitationDetails(datasource, surgeryreportdto, surgerytype, start, endrange);

            request.setAttribute("habitationid", habitationid);
            surgeryform.setFromdate(fromdate);
            surgeryform.setTodate(todate);
            request.setAttribute("mandalid", mandalid);
            request.setAttribute("secondfieldvalue", secondfieldvalue);
            request.setAttribute("districtid", District_Id);
            request.setAttribute("fieldvalue", fieldvalue);
            request.setAttribute("reportlist", reportlist);
            request.setAttribute("villageid", villageid);
            request.setAttribute("reportlist", reportlist);
            request.setAttribute("fromDate", fromdate);
            request.setAttribute("toDate", todate);
            session.setAttribute("arraylist1", reportlist);
            if (!reportlist.isEmpty()) {
                success = "repeat";
            } else {
                success = "failure";
            }
        } catch (SADAREMDBException sADAREMException) {
            success = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            success = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(success);
    }
}









