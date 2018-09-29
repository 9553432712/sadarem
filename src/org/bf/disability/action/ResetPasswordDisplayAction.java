package org.bf.disability.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.common.CommonService;
import java.io.IOException;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionForm;
import javax.sql.DataSource;
import org.bf.disability.common.DataBase.JNDIDataSource;

/**
 * this action class will display the screen relating to reset password for a particular user
 * @author deviprasad t
 * @version 1.0
 */
public class ResetPasswordDisplayAction extends BaseAction {

    /** Creates a new instance of DailyEntryAction */
    public ResetPasswordDisplayAction() {
    }

    /**
     * this method will this action class will display the screen relating to reset password for a particular user
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
            HttpServletResponse response) throws IOException, Exception {

        String target = "success";
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            HttpSession session = request.getSession();
            //DataSource ds = getDataSource(request);



            ArrayList userslist = new ArrayList();
            CommonService commonservice = new CommonService();
            userslist = commonservice.getUsers(ds);
            request.setAttribute("users", userslist);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (mapping.findForward(target));
    }
}
