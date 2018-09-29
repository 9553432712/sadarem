package org.bf.disability.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.LocomotorneedsDTO;
import org.bf.disability.form.LocomotorneedsForm;
import org.bf.disability.service.LocomotorneedsService;
import org.bf.disability.servicefactory.LocomotorNeedsServiceFactory;
import org.bf.disability.common.DataBase.JNDIDataSource;

/**
 * This class is used to manipulate the locomotor needs details
 * @author bapinaidu
 * @version 1.0
 */
public class LocomotorneedsAction extends BaseDispatchAction {

    ActionMessages actionMessages = null;

    /**
     * this method is used to inset the locomotor needs details into database
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return ActionForward
     */
    public ActionForward insertLocomotorneeds(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        //DataSource datasource=getDataSource(request);
        String personcode = null;
        String target = "success";
        DataSource datasource = null;

        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            LocomotorneedsService locomotorneedsservice = LocomotorNeedsServiceFactory.getLocomotorNeedsServiceImpl();
            LocomotorneedsForm locomotorneedsform = (LocomotorneedsForm) form;
            HttpSession session = request.getSession(true);
            LocomotorneedsDTO locomotorneedsdto = new LocomotorneedsDTO();
            // personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }



            locomotorneedsform.setPersoncode(personcode);
            String loginid = (String) session.getAttribute("loginid");
            locomotorneedsform.setLoginid(loginid);
            locomotorneedsform.setSystemip(request.getRemoteAddr());
            try {
                BeanUtils.copyProperties(locomotorneedsdto, locomotorneedsform);
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            }
            int i = 0;
            //int i = locomotorneedsservice.insertLocomotorneeds(datasource, locomotorneedsdto,request);
            if (session.getAttribute("sadaremCodeAu") != null) {
                i = locomotorneedsservice.insertLocomotorneedsAU(datasource, locomotorneedsdto, request);
            } else {
                i = locomotorneedsservice.insertLocomotorneeds(datasource, locomotorneedsdto, request);
            }
            if (i == -1) {
                target = "failure";
            } else {
                target = "success";
            }
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

    /**
     * This method is used to get the locomotor needs details form the database for a particular person
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    public ActionForward getLocomotorneeds(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

        //DataSource datasource=getDataSource(request);
        String personcode = null;
        String target = "success";
        DataSource datasource = null;

        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            LocomotorneedsService locomotorneedsservice = LocomotorNeedsServiceFactory.getLocomotorNeedsServiceImpl();
            LocomotorneedsForm locomotorneedsform = (LocomotorneedsForm) form;
            HttpSession session = request.getSession(true);
            LocomotorneedsDTO locomotorneedsdto = new LocomotorneedsDTO();
            personcode = (String) session.getAttribute("personcode");
            locomotorneedsdto = locomotorneedsservice.getLocomotorneeds(datasource, personcode);
            try {
                BeanUtils.copyProperties(locomotorneedsform, locomotorneedsdto);
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            }
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

    /**
     * this method is used to update the locomotor need details to the database
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    public ActionForward updateLocomotorneeds(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

        //DataSource datasource=getDataSource(request);

        DataSource datasource = null;
        String target = "success";

        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            HttpSession session = request.getSession(true);
            LocomotorneedsDTO locomotorneedsdto = new LocomotorneedsDTO();
            LocomotorneedsForm locomotorneedsform = (LocomotorneedsForm) form;
            LocomotorneedsService locomotorneedsservice = LocomotorNeedsServiceFactory.getLocomotorNeedsServiceImpl();
            String personcode = null;//(String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }


            locomotorneedsform.setPersoncode(personcode);
            String loginid = (String) session.getAttribute("loginid");
            locomotorneedsform.setLoginid(loginid);
            locomotorneedsform.setSystemip(request.getRemoteAddr());
            try {
                BeanUtils.copyProperties(locomotorneedsdto, locomotorneedsform);
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            }
            int i = 0;
            //  if(session.getAttribute("sadaremCodeAu")!=null) {
            //       i = locomotorneedsservice.insertLocomotorneeds(datasource, locomotorneedsdto,request);
            //  }else {
            i = locomotorneedsservice.updateLocomotorneeds(datasource, locomotorneedsdto, request);
            //   }


            if (i == -1) {
                target = "failure";
            } else {
                target = "success";
            }
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
