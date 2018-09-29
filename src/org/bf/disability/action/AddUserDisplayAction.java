package org.bf.disability.action;

import java.util.ArrayList;

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
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.AddUserDAO;
import org.bf.disability.form.Roles;
import org.bf.disability.service.AddUserService;
import org.bf.disability.servicefactory.AddUserServiceFactory;

import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;
/**
 *
 * @author deviprasad.t
 * @version 1.0
 * @description this action class is used to display the add user screen
 */
public class AddUserDisplayAction extends DispatchAction {


    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
    {


        ActionMessages actionMessages = null;
        String target = "success";
        DataSource ds = null;
        Roles formBean = (Roles) form;
        CommonDAO commonDAO = new CommonDAO();

        try 
        {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) 
            {
                ds = JNDIDataSource.getConnection();
            }
            ArrayList roleslist = new ArrayList();
            roleslist = commonDAO.getCampInchargeRoles(ds);
            formBean.setRolesList(roleslist);
            target = "success";
        } 
        catch (SADAREMDBException sADAREMException) 
        {
            sADAREMException.printStackTrace();
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        catch (Exception sADAREMException) 
        {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    
    }

    public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception 
    {

        ActionMessages actionMessages = null;
        String target = "success";
        DataSource ds = null;
        Roles formBean = (Roles) form;
        HttpSession session = request.getSession();
        String localaddr =  CommonUtility.getClientIPAddress(request); 
        
        String districtid_id = (String) session.getAttribute("districtId");
        String loginid_id = (String) session.getAttribute("loginid");
        int campid_id = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId"))); 
        String userId = (String) request.getParameter("userid");
         
        try 
        {
        	 
        	
        	
            ds = getDataSource(request);
            
            if (ds == null || "null".equals(ds))
            {
                ds = JNDIDataSource.getConnection();
            }
            AddUserService adduserservice = AddUserServiceFactory.getAddUserServiceImpl();

            int i = adduserservice.addUser(ds, formBean, districtid_id, loginid_id, campid_id, localaddr, userId);
            if (i == 0) 
            {
                target = "failure";
                request.setAttribute("message", "UserName " + userId + " Already Exists !");
            }
            if (i == 1) 
            {
                target = "success";
                request.setAttribute("msg", "User Added Successfully !");
            }
            
            formBean.setUsername(null);
            formBean.setRoleid(null);
            formBean.setPassword(null);
            formBean.setUserid(null);
            formBean.setUserencrptPwd(null); 
            formBean.setPassword(null);
            
            unspecified(mapping, form, request, response);
            target = "success";
        } 
        catch (SADAREMDBException sADAREMException) 
        {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        catch (Exception sADAREMException) 
        {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }
    
    
    public ActionForward campadmindetailsreport(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "campadmindtls";
	       try
	       {

	            CommonDAOImpl comObj = new CommonDAOImpl(); 
	    	    AddUserDAO obj 		 = new AddUserDAO();
	    	    
	    	    String selRoleId   = CommonUtility.checkNullObj(request.getParameter("roleid"));
	    	    String selRoleName = "";
	    	   
	    	    if(selRoleId.equals(""))
	    	    {
	    	    	selRoleId = "4";
	    	    }
	    	    
	    	    
	           ArrayList roleslist =  comObj.getAllRolesList(); 
	    	   ArrayList resultList = new ArrayList ();
	    	   
	    	   selRoleName = comObj.getRoleName(Integer.parseInt(selRoleId));
	    	   resultList = (ArrayList)obj.getDistrictWiseLoginDetailsReport(selRoleId);
	    	   
	    	   

	    	   request.setAttribute("selRoleId", selRoleId);
	    	   request.setAttribute("selRoleName", selRoleName);
	    	   request.setAttribute("roleslist", roleslist);
	    	   request.setAttribute("campadminDtlsList", resultList);
	       }
	       catch(Exception e)
	       {
	    	   e.printStackTrace();
	       }
       return mapping.findForward(target);
   }
    public ActionForward exportExcel(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		String title = "Login Details of ";
		ArrayList BodyList = new ArrayList (); 
		String selRoleName = "";
		ArrayList HeaderList = new ArrayList();
		ArrayList resultList = new ArrayList ();
		try
		{
			com.tcs.sadarem.common.DAO.CommonDAO cobj= new com.tcs.sadarem.common.DAO.CommonDAOImpl();
			CommonDAOImpl comObj = new CommonDAOImpl();
			AddUserDAO obj 		 = new AddUserDAO();			
			String selRoleId   = CommonUtility.checkNullObj(request.getParameter("roleid"));
			selRoleName = comObj.getRoleName(Integer.parseInt(selRoleId));
			title = title +selRoleName;
			resultList = (ArrayList)obj.getDistrictWiseLoginDetailsReport(selRoleId);
			HeaderList = (ArrayList)resultList.get(0);
			for(int i=1;i<resultList.size();i++)
			{
				BodyList.add(resultList.get(i));
			}		
	    	cobj.exportExcel(HeaderList, BodyList , request,  response,  title);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return null;
	}    
}
