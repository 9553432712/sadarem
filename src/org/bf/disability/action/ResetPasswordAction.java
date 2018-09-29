package org.bf.disability.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionForm;
import java.io.IOException;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.Users;
import org.bf.disability.service.ResetPasswordService;
import org.bf.disability.servicefactory.ResetPasswordServiceFactory;

/**
 * this action class will reset the password for a particular user
 * @author deviparasad t
 * @version 1.0
 */
public class ResetPasswordAction extends BaseAction {

    ActionMessages actionMessages = null;
    DataSource ds = null;

    /**
     * this method will reset the password for a particular user
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws java.io.IOException 
     * @throws java.lang.Exception 
     * @return Action Foward
     */
    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException, Exception {

        String target = "success";
        HttpSession session = request.getSession();
        //DataSource ds = getDataSource(request);


        Users user = (Users) form;
        ResetPasswordService resetpasswordservice = ResetPasswordServiceFactory.getResetPasswordServiceImpl();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            super.execute(mapping, form, request, response);
            int i = resetpasswordservice.resetPassword(ds, user);
            if (i == 0) {
                target = "failure";
            }
            request.setAttribute("msg", "Password Changed Successfully !");
            target = "success";
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
