/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.bf.disability.service.LoginService;
import org.bf.disability.form.ShgloginForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.servicefactory.LoginServiceFactory;

public class changePwdAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {

        String target = "changepwd"; 
        DataSource ds = null;

        try
        {
            ds = getDataSource(request);
            
            if (ds == null || "null".equals(ds)) 
            {
                ds = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");           

            ShgloginForm loginform = (ShgloginForm) form;

            LoginService chgpwdservice = LoginServiceFactory.getLoginServiceImpl();
        
            if (chgpwdservice.changePassword(ds, loginform, username) == 1)
            {
            	String loginStatus = "Password Changed";
                String Systemip = request.getRemoteAddr();
                int i = chgpwdservice.insertLoginStatus(ds, username, Systemip, loginStatus);
                 
                request.setAttribute("errorMessage", "Your Password has been Changed Succesfully. Please login again.!");
                session.invalidate();
                
                target = "success";

            }
            else 
            { 
                request.setAttribute("msg", "Please enter valid old password");
                target = "changepwdpage";
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return (mapping.findForward(target));

    }
}
