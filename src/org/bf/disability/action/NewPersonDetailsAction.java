/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.NewpersonDAO;
import org.bf.disability.form.NewPersonDetailsForm;
import org.bf.disability.service.NewpersonService;
import org.bf.disability.servicefactory.NewpersonServicefactory;

/**
 *
 * @author 695536
 */
public class NewPersonDetailsAction extends DispatchAction {

    /* forward name="success" path="" */
    String SUCCESS = "vic";

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward(SUCCESS);
    }

    public ActionForward physicalImparimentDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        NewPersonDetailsForm newPersonDetailsForm = (NewPersonDetailsForm) form;

        NewpersonDAO newpersonDAO = new NewpersonDAO();
        DataSource ds = null;

        String physicalData = null;
        String readOnly = "readOnly";
        HttpSession session = request.getSession();
        boolean districtLevelAccessFlag = false;
        NewpersonService newpersonService = NewpersonServicefactory.getNewpersonServiceimpl();
        String districtid = null;
        int sadaremIdExist = 0;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            CommonDetails commondetails = new CommonDetails();
            districtid = (String) session.getAttribute("districtId");

            districtLevelAccessFlag = commondetails.checkDistrictFlag(newPersonDetailsForm.getPerson(), districtid);

            if (districtLevelAccessFlag) {
                sadaremIdExist = newpersonDAO.getIdDetails(ds, newPersonDetailsForm.getPerson());
                if (sadaremIdExist == 1) {
                    physicalData = newpersonService.getPhysicalImpairmentDetails(ds, newPersonDetailsForm);

                } else {
                    request.setAttribute("msg", "Please raise the request to update for given SADAREM ID");
                }
            } else {
                newPersonDetailsForm.setPerson("");
                request.setAttribute("msg", "Invalid Sadarem Id");
            }
            request.setAttribute("physicalData", physicalData);
            request.setAttribute("readOnly", readOnly);
        } catch (Exception e) {
            e.printStackTrace();

        }

        return mapping.findForward(SUCCESS);
    }

    public ActionForward updatedata(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        NewPersonDetailsForm newPersonDetailsForm = (NewPersonDetailsForm) form;
        NewpersonDAO newpersonDAO = new NewpersonDAO();
        DataSource ds = null;

        int update = 0;
        int requestId = 0;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            NewpersonService newpersonService = NewpersonServicefactory.getNewpersonServiceimpl();
            requestId = newpersonDAO.getRequestId(ds, newPersonDetailsForm.getPerson());
            update = newpersonService.updatedetails(ds, newPersonDetailsForm, requestId);
            request.setAttribute("msg", "Updated Successfully");
            request.setAttribute("updateData", update);
            newPersonDetailsForm.setPerson("");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }
}
