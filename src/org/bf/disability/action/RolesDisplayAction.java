package org.bf.disability.action;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.service.RolesDisplayService;
import org.bf.disability.servicefactory.RolesDisplayServiceFactory;

/**
 * this action class will dispaly the roles
 * @author deviprasad t
 * @version 1.0
 */
public class RolesDisplayAction extends BaseAction {

    /** Creates a new instance of DailyEntryAction */
    public RolesDisplayAction() {
    }

    /**
     * this method will display the roles
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {

        String target = "success";
        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;

        DataSource ds = null;
        //DataSource ds = getDataSource(request);



        ArrayList roleslist = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            super.execute(mapping, form, request, response);
            RolesDisplayService rolesdisplayservice = RolesDisplayServiceFactory.getRolesDisplayServiceImpl();
            roleslist = rolesdisplayservice.getRoles(ds);
            request.setAttribute("roleslist", roleslist);
        } catch (SADAREMDBException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return (mapping.findForward(target));
    }
}
