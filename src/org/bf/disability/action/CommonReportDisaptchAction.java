package org.bf.disability.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.CommonReportDTO;
import org.bf.disability.service.CommonReportService;
import org.apache.struts.action.*;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.util.CommonReportDetails;
import org.bf.disability.form.CommonReportForm;
import org.bf.disability.servicefactory.CommonReportServiceFactory;
import org.bf.disability.common.DataBase.JNDIDataSource;

/**
 * 
 * @description this dispatch action class is used in common for generating the report
 * @author deviprasad.t
 * @version 1.0
 */
public class CommonReportDisaptchAction extends BaseDispatchAction {

    DataSource ds = null;

    /**
     * 
     * @description this method is used to generate the district wise report
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws java.lang.Exception 
     * @return Action Forward
     */
    public ActionForward getDistrictReport(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        String target = "";
        String success = "repeat";
        String Failure = "failure";
        String str;
        //DataSource ds=getDataSource(request);
       

        ArrayList reportlist = new ArrayList();
        ActionMessages actionMessages = null;
        CommonReportForm commonreportform = (CommonReportForm) form;
        CommonReportDTO surgeryreportdto = new CommonReportDTO();
        CommonReportService surgeryreportservice = CommonReportServiceFactory.getCommonReportServiceImpl();
        CommonReportDetails commonreportdetails = new CommonReportDetails();
        String columnvalue = request.getParameter("columnnvalue");
        String secondcolumnvalue = request.getParameter("secondcolumnvalue");
        String surgerytype = request.getParameter("columnname");
        String fromdate = (String) request.getParameter("FromDate");
        String todate = (String) request.getParameter("ToDate");
        commonreportform.setFromdate(fromdate);
        commonreportform.setTodate(todate);
        HttpSession session = request.getSession(true);
        session.setAttribute("SurgeryType", surgerytype);
        session.setAttribute("TableHeading", columnvalue);
        commonreportform.setFieldvalue(columnvalue);
        commonreportform.setSecondfieldvalue(secondcolumnvalue);
        request.setAttribute("reportlist", surgerytype);
        String Surgerytype = (String) session.getAttribute("SurgeryType");
        try { ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
            commonreportform =
                    commonreportdetails.getCommonReportDetails(Surgerytype, commonreportform);
            BeanUtils.copyProperties(surgeryreportdto, commonreportform);
            reportlist =
                    surgeryreportservice.getDistrictReport(ds, surgeryreportdto, Surgerytype);
            String fieldvalue = commonreportform.getFieldvalue();
            request.setAttribute("fieldvalue", fieldvalue);
            String secondfieldvalue = commonreportform.getSecondfieldvalue();
            commonreportform.setFromdate(fromdate);
            commonreportform.setTodate(todate);
            request.setAttribute("fromDate", fromdate);
            request.setAttribute("toDate", todate);
            request.setAttribute("secondfieldvalue", secondfieldvalue);
            if (reportlist.isEmpty()) {
                success = "ReportException";
            }
            request.setAttribute("reportlist", reportlist);
            success = "repeat";
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

    /**
     * 
     * @description this method is used to generate the mandal wise report
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws java.lang.Exception 
     * @return ActionForward
     */
    public ActionForward getMandalReport(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        String success = "repeat";
        String failure = "failure";
        ActionMessages actionMessages = null;
        //DataSource ds=getDataSource(request);
        


        ArrayList reportlist = new ArrayList();
        CommonReportDTO surgeryreportdto = new CommonReportDTO();
        CommonReportService surgeryreportservice = CommonReportServiceFactory.getCommonReportServiceImpl();
        CommonReportDetails commonreportdetails = new CommonReportDetails();
        CommonReportForm commonreportform = (CommonReportForm) form;
        HttpSession session = request.getSession(true);
        String fromdate = (String) request.getParameter("FromDate");
        String todate = (String) request.getParameter("ToDate");
        commonreportform.setFromdate(fromdate);
        commonreportform.setTodate(todate);
        String surgerytype = (String) session.getAttribute("SurgeryType");
        String District_Id = request.getParameter("districtid");
        String s = (String) request.getAttribute("Table_Value");
        String fieldvalue = request.getParameter("field");
        String secondfieldvalue = request.getParameter("secondfield");
        commonreportform.setSecondfieldvalue(secondfieldvalue);
        commonreportform.setFieldvalue(fieldvalue);
        commonreportform.setDistrictid(District_Id);
        try {ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
            commonreportform = commonreportdetails.getCommonReportDetails(surgerytype, commonreportform);
            BeanUtils.copyProperties(surgeryreportdto, commonreportform);
            reportlist = surgeryreportservice.getMandalReport(ds, surgeryreportdto, surgerytype);
            String fvalue = commonreportform.getFieldvalue();
            String distictid = commonreportform.getDistrictid();
            request.setAttribute("fromDate", fromdate);
            request.setAttribute("toDate", todate);
            request.setAttribute("secondfieldvalue", secondfieldvalue);
            request.setAttribute("districtid", distictid);
            request.setAttribute("fieldvalue", fvalue);
            request.setAttribute("reportlist", reportlist);
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

    /**
     * 
     * @description this method is used to generate the village wise report
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws java.lang.Exception 
     * @return ActionForward
     */
    public ActionForward getVillageReport(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String success = "repeat";
        ActionMessages actionMessages = null;
        try {
            //DataSource ds=getDataSource(request);
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            ArrayList reportlist = new ArrayList();
            CommonReportDetails commonreportdetails = new CommonReportDetails();
            CommonReportService surgeryreportservice = CommonReportServiceFactory.getCommonReportServiceImpl();
            CommonReportDTO commonreportdto = new CommonReportDTO();
            CommonReportForm commonreportform = (CommonReportForm) form;
            HttpSession session = request.getSession(true);
            String fromdate = (String) request.getParameter("FromDate");
            String todate = (String) request.getParameter("ToDate");
            commonreportform.setFromdate(fromdate);
            commonreportform.setTodate(todate);
            String surgerytype = (String) session.getAttribute("SurgeryType");
            String districtid = request.getParameter("districtid");
            String mandalid = request.getParameter("mandalid");
            String fieldvalue = request.getParameter("fieldvalue");
            String secondfieldvalue = request.getParameter("secondfieldvalue");
            commonreportform.setFieldvalue(fieldvalue);
            commonreportform.setDistrictid(districtid);
            commonreportform.setMandalid(mandalid);
            commonreportform.setDistrictid(districtid);
            commonreportform = commonreportdetails.getCommonReportDetails(surgerytype, commonreportform);
            BeanUtils.copyProperties(commonreportdto, commonreportform);
            reportlist = surgeryreportservice.getVillageReport(ds, commonreportdto, surgerytype);
            BeanUtils.copyProperties(commonreportform, commonreportdto);
            String villageid = (String) commonreportdto.getVillageid();
            request.setAttribute("fromDate", fromdate);
            request.setAttribute("toDate", todate);
            request.setAttribute("districtid", districtid);
            request.setAttribute("mandalid", mandalid);
            request.setAttribute("villageid", villageid);
            request.setAttribute("surgerytype", surgerytype);
            request.setAttribute("secondfieldvalue", secondfieldvalue);
            request.setAttribute("fieldvalue", fieldvalue);
            session.setAttribute("reportlist", reportlist);
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

    /**
     * 
     * @description this method is used to generate the habitation wise report
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws java.lang.Exception 
     * @return ActionForward
     */
    public ActionForward getHabitationReport(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
        //DataSource ds=getDataSource(request);
       


        String success = "repeat";
        ActionMessages actionMessages = null;
        try { ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
            ArrayList reportlist = new ArrayList();
            String villagename = null;
            CommonReportDetails commonreportdetails = new CommonReportDetails();
            CommonReportService surgeryreportservice = CommonReportServiceFactory.getCommonReportServiceImpl();
            CommonReportDTO commonreportdto = new CommonReportDTO();
            CommonReportForm commonreportform = (CommonReportForm) form;
            HttpSession session = request.getSession(true);
            String fromdate = (String) request.getParameter("FromDate");
            String todate = (String) request.getParameter("ToDate");
            commonreportform.setFromdate(fromdate);
            commonreportform.setTodate(todate);
            String surgerytype = (String) session.getAttribute("SurgeryType");
            String fieldvalue = request.getParameter("fieldvalue");
            String districtid = (String) request.getParameter("districtid");
            String mandalid = (String) request.getParameter("mandalid");
            String villageid = (String) request.getParameter("villageid");
            String secondfieldvalue = request.getParameter("secondfieldvalue");
            commonreportform.setFieldvalue(fieldvalue);
            commonreportform.setDistrictid(districtid);
            commonreportform.setMandalid(mandalid);
            commonreportform.setVillageid(villageid);
            Iterator iterator = ((ArrayList) session.getAttribute("reportlist")).iterator();
            iterator.hasNext();
            commonreportdto = (CommonReportDTO) iterator.next();
            if (villageid.equals(commonreportdto.getVillageid())) {
                villagename = commonreportdto.getVillagename();
            }

            commonreportform = commonreportdetails.getCommonReportDetails(surgerytype, commonreportform);
            BeanUtils.copyProperties(commonreportdto, commonreportform);
            reportlist = surgeryreportservice.getHabitationReport(ds, commonreportdto, surgerytype);
            BeanUtils.copyProperties(commonreportform, commonreportdto);
            request.setAttribute("fromDate", fromdate);
            request.setAttribute("toDate", todate);
            request.setAttribute("report", reportlist);
            request.setAttribute("districtid", districtid);
            request.setAttribute("mandalid", mandalid);
            request.setAttribute("villageid", villageid);
            request.setAttribute("villagename", villagename);
            request.setAttribute("surgerytype", surgerytype);
            request.setAttribute("secondfieldvalue", secondfieldvalue);
            request.setAttribute("fieldvalue", fieldvalue);
            request.setAttribute("reportlist", reportlist);
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
