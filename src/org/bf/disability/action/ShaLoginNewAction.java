/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.String_Enc_Dec;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.NewsandEventsDAO;
import org.bf.disability.dto.LoginDTO;
import org.bf.disability.form.ShgloginForm;
import org.bf.disability.service.LoginService;
import org.bf.disability.servicefactory.LoginServiceFactory;

import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.PasswordEncriptDecrypt;
import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.common.DAO.UserloginDAO;
import com.tcs.sadarem.common.DAO.UserloginDAOImpl;

/**
 *
 * @author 484898
 */
public class ShaLoginNewAction extends DispatchAction {

    ActionMessages actionMessages = null;

    private void setSessionVariables
    	(
    		HttpServletRequest request, Vector services, String userid,
    		String userName, String districtId, int campId, 
    		int roleId, String mandalId,String personName,
    		String contactNo,String emilId
    	) 
    	throws SADAREMDBException, SQLException 
    {

        HttpSession session = request.getSession(true);
        try 
        {
        	CommonDAO comObj = new CommonDAOImpl();
        	
        	String logDistName = "";
        	String logCampName = "";
        	String logRoleName = "";
        	
        	logDistName = comObj.getDistrictName(districtId);
        	logCampName = comObj.getCampName(campId);
        	logRoleName	= comObj.getRoleName(roleId);
        	
        	ArrayList loginurllist 			= comObj.getLoginUrlList(roleId);
        	ArrayList loginAllUrMenulList 	= comObj.getAllMenuUrlList();
        	
        	
        	if(logDistName.equals(""))
        	{
        		logDistName="-";
        	}
        	
        	if(logCampName.equals(""))
        	{
        		logCampName="-";
        	}
        	
        	if(logRoleName.equals(""))
        	{
        		logRoleName="-";
        	}
        	
        	 
        	
        	for( int i = 0; i < CommonConstants.SUPER_ADMIN_ROLE_IDS.length; i++)
        	{
        	    if(CommonConstants.SUPER_ADMIN_ROLE_IDS[i]==roleId)
        	    {
        	    	session.setAttribute(CommonConstants.SUPER_ADMIN_SESSION_ROLE_ID,roleId);   // Setting the SUPER Admin Role in Session
        	    	
        	    	 	UserloginDAO userObj = new UserloginDAOImpl(); 
        	    	
        	    	 	session.setAttribute("SALogRoleList", userObj.getUserRolesList()); 
        	    	 	session.setAttribute("SALogDistrictList", userObj.getDistrictListbyRoleId(""+roleId)); 
        	    	 	session.setAttribute("SALogCampList", userObj.getCampListbyRoleIdDistId(""+roleId,districtId)); 
        	    	 	session.setAttribute("SALogMandalList", userObj.getMandalListbyRoleIdDistId(""+roleId,districtId));

        	    	 	session.setAttribute("SALog_RoleId", roleId);
        	    	 	session.setAttribute("SALog_DistId", districtId);
        	    	 	session.setAttribute("SALog_CampId", campId);
        	    	 	session.setAttribute("SALog_MandId", mandalId);
        	    	 	
        	    	 	
        	    	 	 session.setAttribute("SALog_logDistName", logDistName);
        	             session.setAttribute("SALog_logCampName", logCampName);
        	             session.setAttribute("SALog_logRoleName", logRoleName);

        	             CommonUtility.generateRandomStrSession(5,session,"SALogCaptchaImageCode");
        	    	break;
        	    } 
        	}
        	
        	session.setAttribute("persondispname", personName);
        	session.setAttribute("username", userName);
        	session.setAttribute("loginid", userName);
            session.setAttribute("services", services);
            session.setAttribute("districtId", districtId);
            session.setAttribute("campId", campId);
            session.setAttribute("roleId", roleId);
            session.setAttribute("mandalId", mandalId);
            session.setAttribute("logincontactNo", contactNo);
            session.setAttribute("loginemailid", emilId);

            session.setAttribute("logDistName", logDistName);
            session.setAttribute("logCampName", logCampName);
            session.setAttribute("logRoleName", logRoleName);
            
            session.setAttribute("loginurllist", loginurllist);
            session.setAttribute("loginAllUrMenulList", loginAllUrMenulList);
            

        } catch (Exception e) {
            e.printStackTrace();
            //throw new SADAREMDBException();
        }
    }

   

	public ActionForward unspecified(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException 
            {
        String randomInt = null;
        Random randomGenerator = new Random();
        String code = "";
        String codeGen = "";
        DataSource ds = null;
        String target = "welcomePage";
        String lastUpdatedDate = null;
        String visitorCount = null;
        int p = 0;
        String path = null;
        String action = null;

        path = ((HttpServletRequest) request).getRequestURI();
        p = path.lastIndexOf("/");
        action = path.substring(p + 1, path.length() -3);
        try 
        {
            HttpSession session = request.getSession();
            NewsandEventsDAO dao = new NewsandEventsDAO();
            LoginService loginService = LoginServiceFactory.getLoginServiceImpl();
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds))
            {
                ds = JNDIDataSource.getConnection();
            }
            
            if(session!=null && session.getAttribute("roleId")!=null)
            {
            	String roleid = CommonUtility.checkNullObj(session.getAttribute("roleId"));
            
	             if (roleid.equals("23") || roleid.equals("24") || roleid.equals("25")) 
	             {
	                                ArrayList list = loginService.getCountOfApprovalPendingofRole(ds, (String)session.getAttribute("districtId"), roleid,
	                                        (String)session.getAttribute("mandalId"));
	                                session.setAttribute("greivanceList", list);
	                            
	             }
            }
          
            for (int idx = 1; idx <= 5; ++idx) 
            {
                randomInt = Integer.toHexString(randomGenerator.nextInt(6));
                code = code + "" + randomInt;
                codeGen = codeGen + " " + randomInt;

            }
            
            if (code != null) 
            {
                request.setAttribute("captchaCode", codeGen);
                session.setAttribute("captcha", code);
            }
            request.setAttribute("news", dao.getNewsTitles(ds));

        } catch (SADAREMDBException sADAREMException) {

            target = "exception";
            sADAREMException.printStackTrace();
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremservicemessage"));
            saveErrors(request, actionMessages);

        } catch (Exception sADAREMException) {

            target = "exception";
            sADAREMException.printStackTrace();
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        }
        return mapping.findForward("success");
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
    public ActionForward checkLogin(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException 
            {
        
    	ShgloginForm shgloginForm = (ShgloginForm) form;
    	
        LoginDTO logindto;
//        LoginDTO userDetailsdto;
        String userid = null;
        String password = null;
        boolean flag = true;
//        String dt = null;
//        String todays_date = null;
//        ArrayList RequestNames = new ArrayList();
        DataSource ds = null;
        String loginStatus = null;
//        PrintWriter out = null;
        String target = "welcomePage";
        try 
        {
            LoginService loginService = LoginServiceFactory.getLoginServiceImpl();
            HttpSession session = request.getSession();
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) 
            {
                ds = JNDIDataSource.getConnection();
            }

            if (  !CommonUtility.checkNullObj(shgloginForm.getUserid()).equals("") && !CommonUtility.checkNullObj(shgloginForm.getPassword()).equals("")) 
            {
            	
                if (session != null && session.getAttribute("captcha")!=null && session.getAttribute("captcha").equals(request.getParameter("txtInput"))) 
                {
                    //out = response.getWriter();
                    logindto = new LoginDTO();
                    String Systemip = request.getRemoteAddr();
                    userid = shgloginForm.getUserid();
                    password = shgloginForm.getPassword();
                    ServletContext context = servlet.getServletContext();
                    
                    String userId  = PasswordEncriptDecrypt.decrypt(userid);
	    			String userPwd = PasswordEncriptDecrypt.decrypt(password);
                    
                    logindto = loginService.getValidUser(ds, userId, userPwd);

                    if (logindto != null && !"".equals(logindto)) 
                    {
                        //String roleid = loginService.getRoleId(ds,userid);

                    	userId =  logindto.getUserName();
                    	
                        if (logindto.isUserStatus())  
                        {
                            form.reset(mapping, request);
                            request.setAttribute("msg", "Another person logged by using this Username");
                            loginStatus = "Login Failed";
                            int i = loginService.insertLoginStatus(ds, logindto.getUserName(), Systemip, loginStatus);
                            //out.println("Failed2");
                            target = "failure";
                        } 
                        else 
                        {
                            if (logindto.getStatus() != null && !logindto.getStatus().equalsIgnoreCase("")) 
                            {
                                if (logindto.getStatus().equalsIgnoreCase("Active"))
                                {
                                    session.invalidate();
                                    session = request.getSession(true);

                                    String roleid = String.valueOf(logindto.getRoleId());
                                    if (roleid != null) 
                                    {
                                        loginStatus = "Login Success";
                                        Vector services = new Vector();
                                        services = loginService.getServicesSQL(ds, roleid);

                                        setSessionVariables(request, services, logindto.getUserName(), logindto.getUserName(), logindto.getDistrictId(), logindto.getCampId(), logindto.getRoleId(), logindto.getMandal_Id(),logindto.getPersonName(),logindto.getContact_no(),logindto.getEmail_id());
                                        request.setAttribute("userid", userId);
                                        session.setAttribute("useridData", userId);
                                        
                                        if (logindto.getPwdFlag() != null && logindto.getPwdFlag().equalsIgnoreCase("Y")) 
                                        {
                                        	session.setAttribute("defaultPassword", logindto.getPwdFlag().trim());
                                        	
                                            request.setAttribute("MSG", "For Security Reasons Please Change Password as below Password Policies");
                                            target = "PasswordChangePage";
                                            // out.println("OK");
                                        } 
                                        else 
                                        {
                                            target = "welcomePage";

                                                //out.println("PasswordChangePage");
                                        }


                                        int i = loginService.insertLoginStatus(ds, userId, Systemip, loginStatus);
                                    }

                                } else if (logindto.getStatus().equalsIgnoreCase("Inactive")) {
                                    loginStatus = "Login Failed";
                                    int i = loginService.insertLoginStatus(ds, userId, Systemip, loginStatus);
                                    form.reset(mapping, request);
                                    request.setAttribute("msg", "User account has been locked. Contact Camp Incharge to Unlock");
                                    target = "failure";
                                    //out.println("Failed3");
                                    // return null;
                                } else if (logindto.getStatus().equalsIgnoreCase("Delete")) {
                                    loginStatus = "Login Failed";
                                    int i = loginService.insertLoginStatus(ds, userId, Systemip, loginStatus);
                                    form.reset(mapping, request);
                                    request.setAttribute("msg", "UserName not exist");
                                    target = "failure";
                                    //out.println("Failed1");
                                    //  return null;
                                }
                            }
                        }
                    } 
                    else
                    {
                        loginStatus = "Login Failed";
                        int i = loginService.insertLoginStatus(ds, userId, Systemip, loginStatus);
                        form.reset(mapping, request);
//                    int cnt = loginService.loginStatusCnt(ds, userid);
//                    if (cnt >= 3) {
//                        request.setAttribute("msg", "User account has been locked. Contact Camp Incharge to Unlock");
//                    } else {
                        request.setAttribute("msg", "Invalid UserName/Password");
//                    }
                        //out.println("Failed5");
                        target = "failure";
                        //return null;
                    }
                } 
                else 
                {
                    request.setAttribute("msg", "Entered Captcha Code not Match. Please Enter Correct Code");
                    target = "failure";
                }
                
            }
            else 
            {
                //out.println("Failed5");
                request.setAttribute("msg", "Invalid UserName/Password");
                target = "failure";
            }

        } 
        catch (SADAREMDBException sADAREMException) 
        {

            target = "exception";
            sADAREMException.printStackTrace();
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremservicemessage"));
            saveErrors(request, actionMessages);
            
            request.setAttribute("msg", "Error :: We are not able to process your request. Please try after some time.");

        } 
        catch (Exception sADAREMException)
        { 
            target = "exception";
            sADAREMException.printStackTrace();
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
            request.setAttribute("msg", "Error :: We are not able to process your request. Please try after some time.");

        }
       // System.out.println("target : "+target);
        
        unspecified(mapping, form, request, response);
        return mapping.findForward(target);
    }

    public ActionForward changePassword(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {
        ShgloginForm shgloginForm = (ShgloginForm) form;
        DataSource ds = null;
        String target = null;
        try {
            LoginService loginService = LoginServiceFactory.getLoginServiceImpl();
            HttpSession session = request.getSession();
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            target = "failure";
            boolean status = checkPasswordPolicies(shgloginForm.getNewpassword(), shgloginForm.getConfirmpassword(), request);
            if (!status) {
                target = "PasswordChangePage";
            } else {
                loginService.updatePasswordFlag(ds, session.getAttribute("username").toString(), shgloginForm.getNewpassword(), shgloginForm.getEncrptPwd(), session.getAttribute("districtId").toString(), session.getAttribute("roleId").toString());

                request.setAttribute("SuccessMSG", "Password Changed Successfully");
                target = "welcomePage";
                //out.println("OK");
            }
        } catch (SADAREMDBException sADAREMException) 
        {
            target = "exception";
            sADAREMException.printStackTrace();
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremservicemessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) 
        {
            target = "exception";
            sADAREMException.printStackTrace();
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    public ActionForward RD(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {
        ShgloginForm shgloginForm = (ShgloginForm) form;

        LoginDTO logindto;
        LoginDTO userDetailsdto;
        String userid = null;
        String password = null;
        String encrptPassword = null;
        boolean flag = true;
        String dt = null;
        String todays_date = null;
        ArrayList RequestNames = new ArrayList();
        DataSource ds = null;
        String loginStatus = null;
        PrintWriter out = null;
        String target = null;
        try {
            LoginService loginService = LoginServiceFactory.getLoginServiceImpl();
            HttpSession session = request.getSession();
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            out = response.getWriter();
            logindto = new LoginDTO();
            String Systemip = request.getRemoteAddr();
            if (request.getParameter("userid") != null) {
                userid = request.getParameter("userid");
                String reqUserId = request.getParameter("userid");
                reqUserId = reqUserId.substring(0, 1);
                if (reqUserId.equals("\"")) {
                    userid = userid.substring(1, userid.length() - 1);
                }
            }
            logindto = loginService.getRDCallCenterValidUser(ds, userid);
            if (logindto != null && !"".equals(logindto)) {
                //String roleid = loginService.getRoleId(ds,userid);
                if (logindto.getStatus() != null && !logindto.getStatus().equalsIgnoreCase("")) {
                    if (logindto.getStatus().equalsIgnoreCase("Active")) {
                        String roleid = String.valueOf(logindto.getRoleId());
                        if (roleid != null) {
                            loginStatus = "Login Success";
                            Vector services = new Vector();
                            services = loginService.getServicesSQL(ds, roleid);

                            setSessionVariables(request, services, userid, logindto.getUserName(), logindto.getDistrictId(), logindto.getCampId(), logindto.getRoleId(), logindto.getMandal_Id(),logindto.getPersonName(),logindto.getContact_no(),logindto.getEmail_id());
                            request.setAttribute("userid", userid);
                            session.setAttribute("useridData", userid);
                            if (roleid.equals("23") || roleid.equals("24") || roleid.equals("25")) {
                                ArrayList list = loginService.getCountOfApprovalPendingofRole(ds, logindto.getDistrictId(), roleid, logindto.getMandal_Id());
                                session.setAttribute("greivanceList", list);
                            }
                            if (roleid.equals("23")) {
                                target = "rdCallCenterDPMRequests";
                                //out.println("rdCallCenterDPMRequests");
                            } else {
                                target = "rdCallCenterPDRequests";
                                //out.println("rdCallCenterPDRequests");
                            }

                            int i = loginService.insertLoginStatus(ds, userid, Systemip, loginStatus);
                        }

                    } else if (logindto.getStatus().equalsIgnoreCase("Inactive")) {
                        loginStatus = "Login Failed";
                        int i = loginService.insertLoginStatus(ds, userid, Systemip, loginStatus);
                        form.reset(mapping, request);
                        request.setAttribute("msg", "User account has been locked. Contact Camp Incharge to Unlock");
                        target = "exception";
                    } else if (logindto.getStatus().equalsIgnoreCase("Delete")) {
                        loginStatus = "Login Failed";
                        int i = loginService.insertLoginStatus(ds, userid, Systemip, loginStatus);
                        form.reset(mapping, request);
                        request.setAttribute("msg", "UserName not exist");
                        target = "exception";
                    }
                }

            } else {
                loginStatus = "Login Failed";
                int i = loginService.insertLoginStatus(ds, userid, Systemip, loginStatus);
                form.reset(mapping, request);
                target = "exception";
            }
        } catch (SADAREMDBException sADAREMException) {
            target = "exception";
            sADAREMException.printStackTrace();
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremservicemessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            sADAREMException.printStackTrace();
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    public ActionForward sessionChecking(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        request.getSession(false).invalidate();
        request.getSession(true);

        String SE = request.getParameter("loginStatus");

        if (SE != null && !SE.equalsIgnoreCase("")) {
            SE = SE.substring(SE.indexOf(".(") + 2, SE.lastIndexOf(")."));
            String_Enc_Dec s = new String_Enc_Dec();
            try {
                SE = s.decrypt("secret", SE);
                if (SE.equals(request.getSession(false).getId())) {
                    request.getRequestDispatcher("/403.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mapping.findForward("success");
    }

    public ActionForward sessionvalidator( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            {
    			HttpSession session = request.getSession(true);
    			   String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
    			   if(sesUserId.equals(""))
    			   {
    				   request.setAttribute("errorMessage", "Please login again as your session expired.");
    			   }
    			return mapping.findForward("sessionexpiredPage");
            }
    
    
    private boolean checkPasswordPolicies(String newpassword, String confirmation, HttpServletRequest request) {
        boolean flag = true;

        if (!newpassword.matches("^(?=.*[A-Z])(?=.*[!@#$%^&*])(?=.*\\d)(?!.*(AND)|(NOT)).*[a-z].*")) {
            request.setAttribute("MSG", "New Password Must Have Atleast One combination of Capital Letter,Special Symbol,Alphabet and Number");
            return false;
        }
        if (newpassword.length() < 8) {
            request.setAttribute("MSG", "New Password Must Have atleast 8 letters");
            return false;
        }
        if (!newpassword.equals(confirmation)) {
            request.setAttribute("MSG", "New Password and Conform Password must be Same");
            return false;
        }
        return flag;
    }
    
    public ActionForward reloadhomepage( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
    	String target = "welcomePage";
		HttpSession session = request.getSession(true);
		   String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
		   if(sesUserId.equals(""))
		   {
			   request.setAttribute("errorMessage", "Please login again as your session expired.");
			   target="sessionexpiredPage";
		   }
		   else
		   { 
	             CommonUtility.generateRandomStrSession(5,session,"SALogCaptchaImageCode");
		   }
		return mapping.findForward(target);
    }
}
