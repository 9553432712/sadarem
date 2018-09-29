package org.bf.disability.action;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.http.*;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.form.CommonReportForm;

import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;



/**
 * 
 * @description this action class is used to generate the repot
 * @author deviprasad.t
 * @version 1.0
 */
public class CommonDisabilityTypeReportAction  extends Action {
    /**
     * 
     * @description this method is used to generate the report
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws java.lang.Exception 
     * @return Action Forward
     */
    public  ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String target="success";
        CommonReportForm commonreportform=(CommonReportForm)form;
        HttpSession session = request.getSession();
        String disabilityName=(String)request.getParameter("Disability_Name");
        session.setAttribute("DisabilityName",disabilityName);
        commonreportform.setFromdate(null);
        commonreportform.setTodate(null);
        return mapping.findForward(target);
    }
}