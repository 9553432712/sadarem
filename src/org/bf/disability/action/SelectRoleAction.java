
package org.bf.disability.action;


import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.form.Roles;

/**
 * this action class will get the roles from the database 
 * @author deviprasad t
 * @version 1.0
 */
public class SelectRoleAction extends BaseAction{
    
    /** Creates a new instance of DailyEntryAction */
    public SelectRoleAction() {
    }
    /**
     * this method will get the roles form the data base
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws java.io.IOException 
     * @throws java.lang.Exception 
     * @return Action Forward
     */
     public ActionForward execute(ActionMapping mapping,
                               ActionForm form, 
                               HttpServletRequest request, 
                               HttpServletResponse response) throws IOException,Exception {
   
				    String target="success";
				   	Roles roles=(Roles)form;
					HttpSession session = request.getSession();  
					String roleid=roles.getRoleid();
					session.setAttribute("roleid",roleid);
					
					return (mapping.findForward(target));    
    }
	
}
