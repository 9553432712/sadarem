/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.ValidPersonDetailsForm;
import org.bf.disability.service.ValidPersonDetailsService;
import org.bf.disability.servicefactory.ValidPersonDetailsServiceFactory;

/**
 *
 * @author 728056
 */
public class ValidPersonDetailsAction extends DispatchAction {

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

        return mapping.findForward(SUCCESS);
    }

    public ActionForward personIDDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        boolean personid = false;
        ValidPersonDetailsForm detailsForm = (ValidPersonDetailsForm) form;
        ValidPersonDetailsService service = ValidPersonDetailsServiceFactory.getDetailsServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            personid = service.checkDetails(ds, detailsForm);

            request.setAttribute("status", personid);

            if (personid == false) {
                request.setAttribute("msg", "Invalid PersonID");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(SUCCESS);
    }

    public ActionForward updateDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int update = 0;
        ValidPersonDetailsForm detailsForm = (ValidPersonDetailsForm) form;
        ValidPersonDetailsService service = ValidPersonDetailsServiceFactory.getDetailsServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            update = service.updateDetails(ds, detailsForm);

            request.setAttribute("status", update);

            if (update == 0) {
                request.setAttribute("msg", "Invalid Process");
            } else {
                request.setAttribute("msg", "Successfully Updated");
            }
            detailsForm.setPersonId("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }
}
