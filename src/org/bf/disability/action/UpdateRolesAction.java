package org.bf.disability.action;

import java.util.ArrayList;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.form.ServiceBean;
import org.bf.disability.service.UpdateRolesService;

/**
 * this action class will update the roles
 * @author deviprasad t
 * @version 1.0
 */
public class UpdateRolesAction extends BaseAction {

    /** Creates a new instance of DailyEntryAction */
    public UpdateRolesAction() {
    }

    /**
     * this method will update the roles
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
            HttpServletResponse response) throws SADAREMDBException, SQLException {

        String target = "success";
        DataSource ds = null;
        ActionMessages actionMessages;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            //DataSource ds = getDataSource(request);


            ArrayList serviceslist = new ArrayList();
            String roleid = (String) session.getAttribute("roleid");
            UpdateRolesService updaterolesservice = new UpdateRolesService();
            DynaActionForm servicesform = (DynaActionForm) form;
            ServiceBean[] socialauditdata = (ServiceBean[]) servicesform.get("ServiceList");
            for (int i = 0; i < socialauditdata.length; i++) {
                ServiceBean socialauditdata1 = (ServiceBean) socialauditdata[i];
                serviceslist.add(socialauditdata1);
            }

            int j = updaterolesservice.updateServicestoRole(ds, roleid, serviceslist);


            request.setAttribute("msg", "New Role Added Successfully !");

        } catch (Exception sqlEx) {
            target = "exception";
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "execute", "UpdateRolesAction", "Code");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpdateRolesAction", "execute");
        }
        return (mapping.findForward(target));
    }
}
