package org.bf.disability.action;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.common.CommonService;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.ServiceBean;
import org.bf.disability.service.AddRoleDisplayService;
import org.bf.disability.servicefactory.AddRoleDisplayServiceFactory;
import org.bf.disability.servicefactory.CommonServiceFactory;

/**
 * 
 * @description this class is used to display the add role screen
 * @author deviprasad.t
 * @version 1.0
 */
public class AddRoleDisplayAction extends BaseAction {

    ActionMessages actionMessages = null;

    /** Creates a new instance of DailyEntryAction */
    public AddRoleDisplayAction() {
    }

    /**
     * 
     * @description this method is used to display the add role screen
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
        DataSource ds = null;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            super.execute(mapping, form, request, response);
            HttpSession session = request.getSession();
            //DataSource ds = getDataSource(request);

            ArrayList servicelist = new ArrayList();
            DynaActionForm addroleform = (DynaActionForm) form;
            CommonService commonservice = new CommonService();
            AddRoleDisplayService addroledisplayservice =
                    AddRoleDisplayServiceFactory.getAddRoleDisplayServiceImpl();

            servicelist = addroledisplayservice.getService(ds);
            servicelist = commonservice.sortServices(servicelist);
            request.setAttribute("servicelist", servicelist);
            ServiceBean[] socialauditlist1 =
                    (ServiceBean[]) servicelist.toArray(new ServiceBean[0]);
            addroleform.set("ServiceList", socialauditlist1);
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
        return (mapping.findForward(target));
    }
}
