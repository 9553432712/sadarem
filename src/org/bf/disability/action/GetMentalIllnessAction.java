package org.bf.disability.action;

import java.lang.reflect.InvocationTargetException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.dto.MentalIllnessDTO;
import org.bf.disability.form.MentalIllnessActionForm;
import org.bf.disability.service.VisualImpairmentService;
import org.bf.disability.servicefactory.VisualImpairmentServiceFactory;
import org.bf.disability.common.DataBase.JNDIDataSource;

/**
 * this action class is used to manipulate the mental illness disability details
 * @author sunima
 * @version 1.0
 */
public class GetMentalIllnessAction extends BaseAction {

    ActionMessages actionMessages = null;

    /**
     * this method is  used to maipulate the mental illness disability details
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws java.lang.Exception 
     * @return ActionForward
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String target = null;
        DataSource ds = null;
        String personcode = null;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            MentalIllnessActionForm mentalillnessform = (MentalIllnessActionForm) form;

            HttpSession session = request.getSession();
            //  personcode=(String)session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }


            VisualImpairmentService visulaImpairmentService = VisualImpairmentServiceFactory.getVisualImparmentImpl();
            MentalIllnessDTO millnessdto = new MentalIllnessDTO();

            super.execute(mapping, form, request, response);
            millnessdto = (MentalIllnessDTO) visulaImpairmentService.getMentalIllnessDetails(ds, personcode);
            try {
                BeanUtils.copyProperties(mentalillnessform, millnessdto);

            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
                throw new SADAREMException();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
                throw new SADAREMException();
            }
            saveToken(request);
            target = "success";

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
