/*
 * PhysicalReportAction.java
 *
 * Created on July 29, 2008, 5:51 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.CommonReportDTO;
import org.bf.disability.form.CommonReportForm;
import org.bf.disability.service.PersonalReportService;
import org.bf.disability.util.CommonReportDetails;

/**
 * This dispatch action class will generate the personal report in district, mandal,
 * village and habitation.
 * @author sunima
 * @version 1.0
 */
public class PersonalReportAction extends DispatchAction {

    CommonReportDTO surgeryreportdto = new CommonReportDTO();
    String success = "repeat";
    String failure = "failure";

    /**
     * this method will generate personal report as district wise
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws java.lang.Exception 
     * @throws javax.servlet.ServletException 
     * @return Action Forward
     */
    public ActionForward districtwise(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception, ServletException {
        CommonReportForm surgeryform = (CommonReportForm) form;
        ArrayList reportlist = new ArrayList();
        DataSource datasource = null;
        //DataSource datasource= getDataSource(request);

        HttpSession session = request.getSession();
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            String surgerytype = (String) session.getAttribute("SurgeryType");
            String District_Id = request.getParameter("districtid");
            String s = (String) request.getAttribute("Table_Value");
            String mandalID = request.getParameter("field");
            surgeryform.setFieldvalue(mandalID);
            String secondfieldvalue = request.getParameter("secondfield");
            surgeryform.setSecondfieldvalue(secondfieldvalue);
            PersonalReportService reportservice = new PersonalReportService();
            surgeryform.setDistrictid(District_Id);

            CommonReportDetails commonreportdetails = new CommonReportDetails();

            surgeryform = commonreportdetails.getCommonReportDetails(surgerytype, surgeryform);

            BeanUtils.copyProperties(surgeryreportdto, surgeryform);
            reportlist = reportservice.getPersonalReportDetails(datasource, surgeryreportdto, surgerytype);

            String value = surgeryform.getFieldvalue();
            request.setAttribute("value", value);
            String secondvalue = surgeryform.getSecondfieldvalue();
            String mandalid = surgeryform.getMandalid();
            request.setAttribute("secondvalue", secondvalue);
            request.setAttribute("mandalid", mandalid);
            request.setAttribute("secondfieldvalue", secondfieldvalue);
            request.setAttribute("districtid", District_Id);
            String fieldvalue = (String) request.getAttribute("field");
            request.setAttribute("fieldvalue", fieldvalue);
            request.setAttribute("reportlist", reportlist);


            request.setAttribute("reportlist", reportlist);


        } catch (Exception e) {
            e.printStackTrace();
        }
        if (reportlist != null && !reportlist.isEmpty()) {
            return mapping.findForward(success);
        } else {
            return mapping.findForward(failure);
        }
    }

    /**
     * this method will generate the report as mandal wise
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws java.lang.Exception 
     * @throws javax.servlet.ServletException 
     * @return Action Forward
     */
    public ActionForward reportmandal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception, ServletException {


        CommonReportForm surgeryform = (CommonReportForm) form;

        ArrayList reportlist = new ArrayList();
        DataSource datasource = null;
        HttpSession session = request.getSession();
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

            surgeryform.setFieldvalue(fieldvalue);

            surgeryform.setSecondfieldvalue(secondfieldvalue);
            surgeryform.setFieldvalue(fieldvalue);
            surgeryform.setDistrictid(District_Id);
            PersonalReportService reportservice = new PersonalReportService();
            surgeryform.setDistrictid(District_Id);
            surgeryform.setMandalid(mandalid);

            CommonReportDetails commonreportdetails = new CommonReportDetails();
            surgeryform = commonreportdetails.getCommonReportDetails(surgerytype, surgeryform);
            BeanUtils.copyProperties(surgeryreportdto, surgeryform);
            reportlist = reportservice.getPersonalReportformandalDetails(datasource, surgeryreportdto, surgerytype);
            String value1 = surgeryform.getFieldvalue();

            request.setAttribute("mandalid", mandalid);
            request.setAttribute("secondfieldvalue", secondfieldvalue);
            request.setAttribute("districtid", District_Id);
            request.setAttribute("fieldvalue", fieldvalue);
            request.setAttribute("reportlist", reportlist);


            request.setAttribute("reportlist", reportlist);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!reportlist.isEmpty()) {
            return mapping.findForward(success);
        } else {
            return mapping.findForward(failure);
        }
    }

    /**
     * this method will generate the village level personal report
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws java.lang.Exception 
     * @throws javax.servlet.ServletException 
     * @return Action Forward
     */
    public ActionForward reportvillage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception, ServletException {


        CommonReportForm surgeryform = (CommonReportForm) form;

        ArrayList reportlist = new ArrayList();

        DataSource datasource = null;

        HttpSession session = request.getSession();
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

            surgeryform.setSecondfieldvalue(secondfieldvalue);
            surgeryform.setFieldvalue(fieldvalue);
            surgeryform.setDistrictid(District_Id);
            surgeryform.setMandalid(mandalid);
            String villageid = request.getParameter("villageid");
            surgeryform.setVillageid(villageid);

            PersonalReportService reportservice = new PersonalReportService();
            CommonReportDetails commonreportdetails = new CommonReportDetails();
            surgeryform = commonreportdetails.getCommonReportDetails(surgerytype, surgeryform);
            BeanUtils.copyProperties(surgeryreportdto, surgeryform);
            reportlist = reportservice.getPersonalReportforvillageDetails(datasource, surgeryreportdto, surgerytype);

            String habaitationid = surgeryform.getHabitationid();
            request.setAttribute("mandalid", mandalid);
            request.setAttribute("secondfieldvalue", secondfieldvalue);
            request.setAttribute("districtid", District_Id);
            request.setAttribute("fieldvalue", fieldvalue);
            request.setAttribute("villageid", villageid);
            request.setAttribute("habaitationid", habaitationid);
            request.setAttribute("reportlist", reportlist);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!reportlist.isEmpty()) {
            return mapping.findForward(success);
        } else {
            return mapping.findForward(failure);
        }


    }

    /**
     * this method will generate the habitation wise personal report
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws java.lang.Exception 
     * @throws javax.servlet.ServletException 
     * @return Action Forward
     */
    public ActionForward habitationwise(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception, ServletException {

        CommonReportForm surgeryform = (CommonReportForm) form;

        ArrayList reportlist = new ArrayList();

        DataSource datasource = null;

        HttpSession session = request.getSession();
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            String surgerytype = (String) session.getAttribute("SurgeryType");
            String District_Id = request.getParameter("districtid");

            String mandalid = request.getParameter("mandalid");
            String villageid = request.getParameter("villageid");
            String fieldvalue = request.getParameter("fieldvalue");
            String secondfieldvalue = request.getParameter("secondfieldvalue");

            surgeryform.setSecondfieldvalue(secondfieldvalue);
            surgeryform.setFieldvalue(fieldvalue);
            surgeryform.setDistrictid(District_Id);
            surgeryform.setMandalid(mandalid);
            surgeryform.setVillageid(villageid);
            PersonalReportService reportservice = new PersonalReportService();
            CommonReportDetails commonreportdetails = new CommonReportDetails();
            surgeryform = commonreportdetails.getCommonReportDetails(surgerytype, surgeryform);
            BeanUtils.copyProperties(surgeryreportdto, surgeryform);
            reportlist = reportservice.getPersonalReportforhabitationDetails(datasource, surgeryreportdto, surgerytype);
            String habitationid = surgeryform.getHabitationid();
            request.setAttribute("habitationid", habitationid);

            request.setAttribute("mandalid", mandalid);
            request.setAttribute("secondfieldvalue", secondfieldvalue);
            request.setAttribute("districtid", District_Id);
            request.setAttribute("fieldvalue", fieldvalue);
            request.setAttribute("reportlist", reportlist);
            request.setAttribute("villageid", villageid);
            request.setAttribute("reportlist", reportlist);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!reportlist.isEmpty()) {
            return mapping.findForward(success);
        } else {
            return mapping.findForward(failure);
        }


    }
}









