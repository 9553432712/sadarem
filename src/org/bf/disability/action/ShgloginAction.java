package org.bf.disability.action;

import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.service.LoginService;
import org.bf.disability.form.ShgloginForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.*;
import javax.sql.DataSource;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.servicefactory.LoginServiceFactory;
import org.bf.disability.dto.LoginDTO;

/**
 * this action class is used to authenticate the user at login time
 * @author deviprasad t
 * @version 1.0
 */
public class ShgloginAction extends BaseAction {

    ActionMessages actionMessages = null;

    private void setSessionVariables(HttpServletRequest request, Vector services, String userid, String userName, String districtId, int campId, int roleId) throws SADAREMDBException, SQLException {
        HttpSession sess = request.getSession(true);
        try {
            sess.setAttribute("username", userName);
            sess.setAttribute("loginid", userName);
            sess.setAttribute("services", services);
            sess.setAttribute("districtId", districtId);
            sess.setAttribute("campId", campId);
            sess.setAttribute("roleId", roleId);

        } catch (Exception e) {
            e.printStackTrace();
            //throw new SADAREMDBException();
        }
    }

    /**
     * this method will be used to authenticate the user at login time
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    @Override
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {
        ShgloginForm shgloginForm = (ShgloginForm) form;
        String target = "failure";
        LoginDTO logindto;
        String userid = null;
        String password = null;
        String dt = null;
        String todays_date = null;
        DataSource ds = null;
        PrintWriter out = null;
        if (request.getParameter("mode") != null && request.getParameter("mode").equalsIgnoreCase("getCampNames")) {
            target = "java";
            String camp = "";
            LoginService loginService = LoginServiceFactory.getLoginServiceImpl();
            try {
                ds = getDataSource(request);
                if (ds == null || "null".equals(ds)) {
                    ds = JNDIDataSource.getConnection();
                }
                String disId = request.getParameter("disId");
                camp = loginService.getCampNames(ds, disId);
                if (camp.isEmpty()) {
                    request.setAttribute("campData", "No Data Found");
                }
                PrintWriter pw = response.getWriter();
                pw.write(camp);
                response.flushBuffer();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        try {
            if (!shgloginForm.getUserid().equals("") || shgloginForm.getUserid() != null
                    || shgloginForm.getPassword() != null || !shgloginForm.getPassword().equals("")) {
                out = response.getWriter();
                ds = getDataSource(request);
                if (ds == null || "null".equals(ds)) {
                    ds = JNDIDataSource.getConnection();
                }
                logindto = new LoginDTO();
                userid = shgloginForm.getUserid();
                password = shgloginForm.getPassword();
                ServletContext context = servlet.getServletContext();
                //DataSource ds = getDataSource(request);
                //ds = getDataSource(request);
                HttpSession session = request.getSession();
//                if (ds == null || "null".equals(ds)) {
//                    ds = JNDIDataSource.getConnection();
//                }
                LoginService loginService = LoginServiceFactory.getLoginServiceImpl();
                //  logindto = loginService.getValidUser(ds, userid, password);
                if (logindto != null && !"".equals(logindto)) {
                    //String roleid = loginService.getRoleId(ds,userid);
                    if (logindto.isUserStatus()) {
                        form.reset(mapping, request);
                     //   request.setAttribute("msg", "another person logged by using this userid");
                        target = "failure";
                        //return null;
                    } else {
                        String roleid = String.valueOf(logindto.getRoleId());
                        if (roleid != null) {
                            Vector services = new Vector();
                            services = loginService.getServicesSQL(ds, roleid);
                            setSessionVariables(request, services, userid, logindto.getUserName(), logindto.getDistrictId(), logindto.getCampId(), logindto.getRoleId());
                            request.setAttribute("userid", userid);
                            session.setAttribute("useridData", userid);
                           target = "OK";
                           
                        }
                    }
                } else {
                    form.reset(mapping, request);
                    //   request.setAttribute("msg", "Invalid Userid/password");
                    target = "failure";
                    //return null;
                }
            } else {
                // request.setAttribute("msg", "Invalid Userid/password");
                target = "failure";                
            } 
        } catch (SADAREMDBException sADAREMException) {

            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremservicemessage"));
            saveErrors(request, actionMessages);

        } catch (Exception sADAREMException) {

            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        }
        out.print(target);
        return null;
    }
}













