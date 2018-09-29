package org.bf.disability.action;

import java.util.ArrayList;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.ServiceBean;
import org.bf.disability.service.AddRoleService;
import org.bf.disability.servicefactory.AddRoleServiceFactory;

/**
 * 
 * @description this action class is used to add new role into database
 * @author deviprasad.t
 * @version 1.0
 */
public class AddRoleAction extends BaseAction {

    ActionMessages actionMessages = null;

    /** Creates a new instance of DailyEntryAction */
    public AddRoleAction() {
    }

    /**
     * @description this method is used to add new role into database
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @return Action Forward
     * @throws org.bf.disability.Exception.SADAREMException 
     */
    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {

        String target = "success";
        DataSource ds = null;


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            super.execute(mapping, form, request, response);
            HttpSession session = request.getSession();
            //DataSource ds = getDataSource(request);

            ArrayList serviceslist = new ArrayList();
            String rolename = (String) request.getParameter("rolename");
            AddRoleService addroleservice =
                    AddRoleServiceFactory.getAddRoleServiceImpl();
            DynaActionForm addroleform = (DynaActionForm) form;


            ServiceBean[] socialauditdata =
                    (ServiceBean[]) addroleform.get("ServiceList");
            for (int i = 0; i < socialauditdata.length; i++) {
                ServiceBean socialauditdata1 = (ServiceBean) socialauditdata[i];
                serviceslist.add(socialauditdata1);
            }
            int i = addroleservice.addRole(ds, rolename);
            if (i == 0) {
                target = "failure";
                return (mapping.findForward(target));
            } else {
                int roleid = i;
                int j = addroleservice.addServicestoRole(ds, roleid, serviceslist);
                if (j == 0) {
                    target = "failure";
                }

            }

        } catch (SADAREMDBException sADAREMException) {
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
        request.setAttribute("msg", "New Role Added Successfully !");
        return (mapping.findForward(target));
    }
}
