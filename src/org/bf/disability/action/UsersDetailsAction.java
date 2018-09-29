/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.service.UserDetailsService;
import org.bf.disability.servicefactory.UserDetailsServiceFactory;

import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author t_bapinaidu
 */
public class UsersDetailsAction extends BaseDispatchAction {

    ActionMessages actionMessages = null;

    public ActionForward getUserDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        String target = "success";
        String loginid = null;
        int campId = 0;
        DataSource ds = null;
        List userDetailsList = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession(true);
            loginid = (String) session.getAttribute("loginid");
            campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));

            UserDetailsService userDetailsService = UserDetailsServiceFactory.getUserDetailsServiceImpl();
            userDetailsList = userDetailsService.getUserDetails(ds, campId, loginid);
            target = "success";
            if (userDetailsList.isEmpty() && userDetailsList == null) 
            {
                request.setAttribute("message", "UserIds are Not Created in this Camp");
            }
            request.setAttribute("userDetailsList", userDetailsList);

        } catch (SADAREMDBException sADAREMException) 
        {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } 
        catch (Exception sADAREMException) 
        {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }

        return mapping.findForward(target);
    }

    public ActionForward updateLoginDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws SADAREMDBException, SQLException 
    {
    	
        String target = "success";
        String loginid = null;
        int campId = 0;
        DataSource ds = null;
        List userDetailsList = null;
        String LoginIdsStatus = null;
        String currentRoleId = null;
        boolean editPermissionFlag = false;
        int j = 0;
        try
        {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession(true);
            loginid = CommonUtility.checkNullObj(session.getAttribute("loginid"));
            campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
            LoginIdsStatus = CommonUtility.checkNullObj(request.getParameter("LoginIdsStatus"));
 

            UserDetailsService userDetailsService = UserDetailsServiceFactory.getUserDetailsServiceImpl();
            
            if (LoginIdsStatus != null && !"".equals(LoginIdsStatus)) 
            {
                CommonDetails commonDetails = new CommonDetails();
                currentRoleId = commonDetails.getLoginDetails(ds, loginid);
                if (currentRoleId != null && !"".equals(currentRoleId))
                {
                    editPermissionFlag = commonDetails.campareRoleIds(currentRoleId, CommonConstants.REPORTS_CAMPLOGIN);
                    if (editPermissionFlag) 
                    {
                       
                        String[] selectedIds = request.getParameterValues("rowIds");
                        if (selectedIds != null && selectedIds.length > 0) 
                        {
                            for (int i = 0; i < selectedIds.length; i++) 
                            {
                                String selectedRowId = selectedIds[i];
                              //  System.out.println("loginid : "+loginid+"===selectedRowId : "+selectedRowId+"=="+LoginIdsStatus);
                                
                                j = userDetailsService.updateLoginDetails(ds, loginid, LoginIdsStatus, selectedRowId);
                            }
                        }
                        target = "success";
                        if (j == 1) 
                        {
                            if ("RleaseLoginIds".equals(LoginIdsStatus)) 
                            {
                                request.setAttribute("msg", "selected userIds are Released.");
                            } else if ("Lock".equals(LoginIdsStatus)) 
                            {
                                request.setAttribute("msg", "selected userIds are Locked.");
                            } 
                            else if ("UnLock".equals(LoginIdsStatus)) 
                            {
                                request.setAttribute("msg", "selected userIds are UnLocked.");
                            }
                        } 
                        else if (j != 1) 
                        {
                            request.setAttribute("msg", "UserIds status not updated");
                        }
                    } 
                    else
                    {
                        request.setAttribute("message", "UserIds status not updated You dont Have Permissions");
                    }
                }
                // request.setAttribute("userDetailsList", userDetailsList);
            } 
            else 
            {
                request.setAttribute("message", "Incorrect User status");
            }


            userDetailsList = userDetailsService.getUserDetails(ds, campId, loginid);
            request.setAttribute("userDetailsList", userDetailsList);
        }  
        catch (SADAREMDBException sADAREMException) 
        {
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
        return mapping.findForward(target);
    }
}
