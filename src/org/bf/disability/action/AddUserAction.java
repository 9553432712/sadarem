package org.bf.disability.action;

import java.io.IOException;

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

import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @description this action class will add new user into database
 * @author deviprasad.t
 * @version 1.0 
 */
public class AddUserAction extends BaseAction {

    ActionMessages actionMessages = null;

    /** Creates a new instance of DailyEntryAction */
    public AddUserAction() {
    }

    /**
     *
     * @description this method is used to add the new user in to the database
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return Action Forward
     * @throws java.io.IOException
     * @throws java.lang.Exception
     */
    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException, Exception {


        String target = "success";
        DataSource ds = null;


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            super.execute(mapping, form, request, response);
            HttpSession session = request.getSession();
            String localaddr = CommonUtility.getClientIPAddress(request);
            String districtid_id = CommonUtility.checkNullObj(session.getAttribute("districtId"));
            String loginid_id = CommonUtility.checkNullObj(session.getAttribute("loginid"));
            int campid_id = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
            String userId = CommonUtility.checkNullObj(request.getParameter("userid"));


            //DataSource ds = getDataSource(request);


//            AddUser adduser = (AddUser) form;
//            AddUserService adduserservice =
//                    AddUserServiceFactory.getAddUserServiceImpl();
//
//            int i = adduserservice.addUser(ds, adduser, districtid_id, loginid_id, campid_id, localaddr, userId);
//            if (i == 0) {
//                target = "failure";
//                request.setAttribute("message", "UserName "+userId+" Already Exists !");
//            }
//            if (i == 1) {
//                target = "success";
//                request.setAttribute("msg", "User Added Successfully !");
//            }
//            adduser.setUsername(null);
//            adduser.setRoleid(null);

        } catch (SADAREMException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return (mapping.findForward(target));
    }
}
