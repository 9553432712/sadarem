/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.PartADAO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.form.PartAForm;

/**
 *
 * @author 490058
 */
public class certificatewithPhysicalRequirements extends BaseDispatchAction {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    ActionMessages actionMessages = null;

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
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "success";
        String mode = null;
        PartAForm partAForm = (PartAForm) form;
        String personcode = partAForm.getPersoncode();
        PartADTO partADTO = new PartADTO();
        PartADAO dao = new PartADAO();
        String disability = "";
        HttpSession session = request.getSession();
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            String Systemip = request.getRemoteAddr();
            String loginid = (String) session.getAttribute("loginid");

            mode = partAForm.getMode();
            if (personcode != null && personcode.length() == 17) {

                if (mode != null && mode.equalsIgnoreCase("update")) {

                    BeanUtils.copyProperties(partADTO, partAForm);
                    partADTO.setLoginid(loginid);
                    partADTO.setSystemip(Systemip);
                    partAForm.setMode("");
                    // partAForm.setPersoncode("");
                    int i = dao.updatePhysicalRequirements(ds, partADTO);
                    if (i > 0) {
                        request.setAttribute("msg", "Updated Successfully");
                        request.setAttribute("personcode", personcode);
                        target = "certificate";
                    } else {
                        request.setAttribute("msg", "Not Updated ");
                        target = "success";
                    }

                } else {
                    BeanUtils.copyProperties(partADTO, partAForm);
                    disability = dao.getDisabilityIdforCertificate(ds, personcode);


                    if (disability != null) {
                        partADTO = dao.setPreviousPhysicalRequirements(ds, partADTO);
                        BeanUtils.copyProperties(partAForm, partADTO);
                        partAForm.setDisabilityId(Integer.parseInt(disability));
                        partAForm.setPersoncode(personcode);
                        target = "radiobut";
                    } else {
                        request.setAttribute("msg", "Please enter valid number");
                        target = "success";
                    }


                }

            }

        } catch (SADAREMException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }

        return mapping.findForward(target);
    }
}
