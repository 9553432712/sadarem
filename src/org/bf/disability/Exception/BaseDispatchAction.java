/*
 * BaseDispatchAction.java
 *
 * Created on September 22, 2008, 4:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.Exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public abstract class BaseDispatchAction extends DispatchAction{
   
        public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
        { 
        	 HttpSession session=request.getSession(false);
             
             if(session.getAttribute("services")!=null)
             {
                 return  super.execute(mapping, form, request, response);
             }
             else
             {
             	request.setAttribute("errorMessage", "Sorry we are not able to process your request as session expired.<br>Please login again."); 
                 response.sendRedirect("./common/error.jsp");
             }
             
             return null;
    
        }
}
