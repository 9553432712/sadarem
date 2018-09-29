package org.bf.disability.action;

import java.io.File;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.service.DataBackupService;
import org.bf.disability.servicefactory.DataBackupServiceFactory;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.ExceptionDAO;

/**
 * 
 * @description this action class is used to get the database abck up on the server machine
 * @author pramodh kumar .g
 * @version 1.0
 */
public class DataBackupAction extends BaseDispatchAction {

    private final static String SUCCESS = "success";

    /**
     * 
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     * @description this method will take the back up for the database in server machine
     */
    public ActionForward takeBackup(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        DataSource ds = null;
//        DataBackupForm f=(DataBackupForm)form;
//        DynaActionForm f=(DynaActionForm)form;
        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            //DataSource datasource=getDataSource(request);

            HttpSession session = request.getSession();
            DataBackupService databackupservice =
                    DataBackupServiceFactory.getDataBackupServiceImpl();
            databackupservice.takeDataBackup(ds);
        } //end of try block
        catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return mapping.findForward(SUCCESS);

    }

    /**
     * 
     * @description this method will create the directory for the back up to place
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @return Action Forward
     * @throws org.bf.disability.Exception.SADAREMException 
     */
    public ActionForward createDirectory(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        try {
            String DirectoyPath = "C:\\SQLEXPRESS DATABASE BACKUP";
            File directory = new File(DirectoyPath);
            if (!directory.exists()) {
                directory.mkdir();
            }
        } //end of try block
        catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return mapping.findForward("success");
    }
}
