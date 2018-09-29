package org.bf.disability.action;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.NewsandEventsDAO;
import org.bf.disability.service.LoginService;
import org.bf.disability.servicefactory.LoginServiceFactory;

import com.tcs.sadarem.util.CommonUtility;

/**
 * this action class is used for logout use case
 * @author deviprasad t
 * @version 1.0
 */
public class LogoutAction extends BaseAction {

    /** Creates a new instance of LogoutAction */
    public LogoutAction() {
    }

    /**
     * this method is used to logout of the application
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws java.lang.Exception 
     * @return Action Forward
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
        NewsandEventsDAO dao = new NewsandEventsDAO();
        String target = "success";
        ActionMessages actionMessages = null;
        HttpSession session = null;
        DataSource ds = null;
        String userid = null;
        String visitorCount = null;
        HttpSession visitorCountSession = null;
        
        String urlErrorMsg = "";
        Random randomGenerator = new Random();
        String code = "";
        String codeGen = "";
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) 
            {
                ds = JNDIDataSource.getConnection();
            }
            
         
            LoginService loginService = LoginServiceFactory.getLoginServiceImpl();
            session = request.getSession(true);
            userid = (String) session.getAttribute("loginid");
            
            String loginStatus = "Logout";
            String Systemip = request.getRemoteAddr();
            int i = loginService.insertLoginStatus(ds, userid, Systemip, loginStatus);

            urlErrorMsg = CommonUtility.checkNullObj(session.getAttribute("urlErrorMsg"));
        	
        	if(urlErrorMsg.length()==0)
        	{
        		urlErrorMsg ="You have successfully logged out";
        	}
            
            
            
            visitorCount = dao.visitorCount(ds); // Getting visitor Count

            loginService.unAuthorisedAccessStatus(ds, userid);

//        session.removeAttribute("hearingimpairment");
//        session.removeAttribute("lowerextremity");
//        session.removeAttribute("district_name");
//        session.removeAttribute("upperextrimity");
//        session.removeAttribute("mentalretardation");
//        session.removeAttribute("totallist");
//        session.removeAttribute("hearingimpairment");
//        session.removeAttribute("mandal_name");
//        
//        session.removeAttribute("personcode");
//        session.removeAttribute("telugupersonname");
//        session.removeAttribute("village_id");
//        session.removeAttribute("total");
//        session.removeAttribute("habitation_id");
//        session.removeAttribute("totalphysical");
//        session.removeAttribute("username");
//        session.removeAttribute("village_name");
//        
//        session.removeAttribute("visualimpairment");
//        session.removeAttribute("disabilitytype");
//        session.removeAttribute("gender");
//        session.removeAttribute("loginidl");
//        session.removeAttribute("amputation");
//        session.removeAttribute("trunk");
//        session.removeAttribute("reportlist");
//        session.removeAttribute("habitation_name");
//        
//        session.removeAttribute("district_id");
//        session.removeAttribute("cardiopulmonary");
//        session.removeAttribute("telugufathername");
//        session.removeAttribute("logintime");
//        session.removeAttribute("mentalillness");
//        session.removeAttribute("mandal_id");
//        session.removeAttribute("Idcardlist");
//        session.removeAttribute("Evaluation");
//        
//        
//        session.removeAttribute("dwarfism");
//        session.removeAttribute("services");
//        session.removeAttribute("transverse");

            session.invalidate();
  
//        
//        if (session!=null){
//            //Update user befor invalidating the session
//            
//            Enumeration devi=session.getAttributeNames();
//            while(devi.hasMoreElements())
//            {

//            }
//            session.removeAttribute("personcode");
//            
//            session.invalidate();
//        }
//        
//        
//        Enumeration deviprasad=request.getAttributeNames();
//            while(deviprasad.hasMoreElements())
//            {

//            }


            String randomInt = null;
            for (int idx = 1; idx <= 5; ++idx) 
            {
                randomInt = Integer.toHexString(randomGenerator.nextInt(6));
                code = code + "" + randomInt;
                codeGen = codeGen + " " + randomInt;

            }
            
            
        	session =  request.getSession(true);
            if (code != null) 
            {
                request.setAttribute("captchaCode", codeGen);
                session.setAttribute("captcha", code);
            }
        }
        catch (SADAREMException sADAREMException) 
        {

            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        }
        catch (Exception sADAREMException)
        {

            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        }

        request.setAttribute("msg", urlErrorMsg);

        return mapping.findForward(target);
    }
}
