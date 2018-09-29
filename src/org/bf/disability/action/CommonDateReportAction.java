package org.bf.disability.action;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.http.*;
import javax.sql.DataSource;
import org.bf.disability.form.CommonReportForm;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.bf.disability.common.DataBase.JNDIDataSource;

/**
 * 
 * @description this action class is used to generate the reports
 * @version 1.0
 * @author deviprasad.t
 */
public class CommonDateReportAction extends Action {

    /**
     * 
     * @description this method is used to generate the reports
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws java.lang.Exception 
     * @return Action Forward
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "failure";

        String Surgery = "SurgeryWiseReport";
        String AssistiveDevice = "AssistiveDeviceReport";
        String EducationService = "EducationbServiceReport";
        ArrayList reportlist = new ArrayList();


        HttpSession session = request.getSession(true);
        CommonReportForm commonreportform = (CommonReportForm) form;
        //DataSource ds=getDataSource(request);

        String fromdate = commonreportform.getFromdate();
        String todate = commonreportform.getTodate();
        request.setAttribute("fromDate", fromdate);
        request.setAttribute("toDate", todate);
        String Disability_Name = (String) session.getAttribute("DisabilityName");
        session.setAttribute("disability", Disability_Name);
        if (Disability_Name == null) {
            Disability_Name = "null";
        }
        if (Disability_Name.equals("SurgeryWise")) {
            return mapping.findForward(Surgery);
        } else if (Disability_Name.equals("AssistiveDevices")) {
            return mapping.findForward(AssistiveDevice);
        } else if (Disability_Name.equals("EducationServices")) {
            return mapping.findForward(EducationService);
        } else {
            return mapping.findForward(target);
        }
    }
}
