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
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.service.PersonCodeCheckService;
import org.bf.disability.servicefactory.PersonCodeCheckServiceFactory;

/**
 * this class will check whether a person exists or not
 * @author deviprasad t
 * @version 1.0
 */
public class PersonCodeCheckAction extends BaseAction {

    ActionMessages actionMessages = null;
    DataSource ds = null;

    /**
     * this method will check whether a particular person is there in the data base with
     * that person code
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws java.lang.Exception 
     * @return Action Forward
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //DataSource ds=getDataSource(request);


        HttpSession session = request.getSession();
        PersonCodeCheckService personCodeCheckService = PersonCodeCheckServiceFactory.getPersonCodeCheckServiceImpl();
        String presoncode = request.getParameter("personcode");
        ArrayList calculatedValueslist;
        String target = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            super.execute(mapping, form, request, response);
            boolean flag = personCodeCheckService.checkPersonCode(presoncode, ds);
            if (flag == true) {
                session.setAttribute("personcode", presoncode);
                //   calculatedValueslist = personCodeCheckService.getCalculatedValues(presoncode,ds);

                target = "success";
            } else {
                request.setAttribute("msg", "Person Details not Available");
                target = "failure";
            }
        } catch (SADAREMException sADAREMException) {
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
