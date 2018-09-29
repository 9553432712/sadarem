/*
 * 
 *
 * 
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonService;

import org.bf.disability.form.ServiceBean;
import org.bf.disability.service.GetServicesService;
import org.bf.disability.servicefactory.CommonServiceFactory;
import org.bf.disability.servicefactory.GetServicesServiceFactory;
import org.bf.disability.common.DataBase.JNDIDataSource;

/**
 * This action class will be used to get the services to a particualr user
 * @author deviparasad t
 * @version 1.0
 */
public class GetServicesAction extends BaseAction {

    /** Creates a new instance of DailyEntryAction */
    public GetServicesAction() {
    }

    /**
     * this method is used to get the services for a particular user
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
        ActionMessages actionMessages = null;
        DataSource ds = null;
        String target = "success";
        HttpSession session = request.getSession();
        // DataSource ds = getDataSource(request);



        ArrayList servicelist = new ArrayList();
        String roleid = (String) session.getAttribute("roleid");

        CommonService commonservice = new CommonService();
        DynaActionForm servicesform = (DynaActionForm) form;
        GetServicesService getservicesservice = GetServicesServiceFactory.getServicesServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            super.execute(mapping, form, request, response);
            servicelist = getservicesservice.getService(ds, roleid);

            servicelist = commonservice.sortServices(servicelist);

            request.setAttribute("servicelist", servicelist);
            ServiceBean[] socialauditlist1 = (ServiceBean[]) servicelist.toArray(new ServiceBean[0]);
            servicesform.set("ServiceList", socialauditlist1);
            target = "success";
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
        return mapping.findForward(target);
    }
}
