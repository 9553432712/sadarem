/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.UpdateSadaremDetailsForm;
import org.bf.disability.service.UpdateSadaremDetailsService;
import org.bf.disability.servicefactory.UpdateSadaremDetailsServiceFactory;

import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author 728056
 */
public class UpdateSadaremDetailsAction extends DispatchAction {

    /* forward name="success" path="" */
    String SUCCESS = "success";
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

        ArrayList Details = new ArrayList();
        UpdateSadaremDetailsForm detailsForm = (UpdateSadaremDetailsForm) form;
        UpdateSadaremDetailsService service = UpdateSadaremDetailsServiceFactory.getdetailsServiceImpl();

        HttpSession session = request.getSession(true);
        session.getAttribute("districtId").toString();

        String loginID = (String) session.getAttribute("loginid");
        String systemIP = CommonUtility.getClientIPAddress(request);


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            Details = service.getDetails(ds, session);

            request.setAttribute("sadarem", Details);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return mapping.findForward(SUCCESS);
    }

    public ActionForward getdata(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        HttpSession session = request.getSession(true);
//        session.getAttribute("districtId").toString();


        boolean update = false;
        ArrayList Details1 = new ArrayList();

        UpdateSadaremDetailsForm detailsForm = (UpdateSadaremDetailsForm) form;
        UpdateSadaremDetailsService service = UpdateSadaremDetailsServiceFactory.getdetailsServiceImpl();


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            update = service.insertData(ds, detailsForm, session);
            if (update == true) {

                request.setAttribute("Success", "Successfully Updated");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }
}
