/*
 * VisualImpairmentDispatchAction.java
 *
 * Created on July 10, 2008, 10:21 AM
 */
package org.bf.disability.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMessage;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.CardioPulmonaryDTO;
import org.bf.disability.form.CardioPulmonaryActionForm;
import org.bf.disability.service.VisualImpairmentService;
import org.bf.disability.servicefactory.VisualImpairmentServiceFactory;

/**
 * this dispatch action class will manipulate the visual impairment details
 * @author kiran h
 * @version 1.0
 */
public class VisualImpairmentDispatchAction extends BaseDispatchAction {

    /* forward name="success" path="" */
    ActionMessages actionMessages = null;
    DataSource ds = null;

    /**
     * this method will inset visual impairment details
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    public ActionForward insertVisualImpairment(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        int i = 0;
        float visualimparment = 0;
        String target = "success";
        String personcode = null;
        double visualimparmenttotalvalue = 0;
        CardioPulmonaryActionForm cform = (CardioPulmonaryActionForm) form;
        CardioPulmonaryDTO cardioPulmonarydto = new CardioPulmonaryDTO();

        HttpSession session = request.getSession();
        // personcode=(String)session.getAttribute("personcode");

        if (session.getAttribute("sadaremCodeAu") != null) {
            personcode = (String) session.getAttribute("sadaremCodeAu");
        } else {
            personcode = (String) session.getAttribute("personcode");
        }

        String loginid = (String) session.getAttribute("loginid");
        //DataSource ds=getDataSource(request);


        String systemip = request.getRemoteAddr();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            if (cform.getVisualimpairment() != null) {
                visualimparment = Float.parseFloat(cform.getVisualimpairment());
                visualimparmenttotalvalue = Double.parseDouble(cform.getVisualimpairment());
                if (visualimparmenttotalvalue == 101.0) {
                    visualimparmenttotalvalue = 100.0;
                }
                VisualImpairmentService visulaImpairmentService = VisualImpairmentServiceFactory.getVisualImparmentImpl();
                if (visualimparment != 0) {
                    try {
                        BeanUtils.copyProperties(cardioPulmonarydto, cform);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    }

                    if (session.getAttribute("sadaremCodeAu") != null) {
                        i = visulaImpairmentService.insertVisualImparmentAU(ds, personcode, visualimparment, systemip, loginid, cardioPulmonarydto, request);
                    } else {
                        i = visulaImpairmentService.insertVisualImparment(ds, personcode, visualimparment, systemip, loginid, cardioPulmonarydto, request);
                    }



                }
                if (i == 1) {
                    request.setAttribute("msg", "Visual Impairment Details Inserted Successfully");
                    target = "success";

                    // session.setAttribute("visualimpairment",new Double(visualimparmenttotalvalue));
                } else {
                    request.setAttribute("msg", "Error in Visual Impairment Details");
                    target = "failure";
                }

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
     * this method will inset visual impairment details
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward insertVisualImpairmentAU(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        int i = 0;
        float visualimparment = 0;
        String target = "success";
        String personcode = null;
        double visualimparmenttotalvalue = 0;
        CardioPulmonaryActionForm cform = (CardioPulmonaryActionForm) form;
        CardioPulmonaryDTO cardioPulmonarydto = new CardioPulmonaryDTO();

        HttpSession session = request.getSession();
        personcode = (String) session.getAttribute("sadaremCodeAu");
        String loginid = (String) session.getAttribute("loginid");
        //DataSource ds=getDataSource(request);

        String systemip = request.getRemoteAddr();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            if (cform.getVisualimpairment() != null) {
                visualimparment = Float.parseFloat(cform.getVisualimpairment());
                visualimparmenttotalvalue = Double.parseDouble(cform.getVisualimpairment());
                if (visualimparmenttotalvalue == 101.0) {
                    visualimparmenttotalvalue = 100.0;
                }
                VisualImpairmentService visulaImpairmentService = VisualImpairmentServiceFactory.getVisualImparmentImpl();
                if (visualimparment != 0) {
                    try {
                        BeanUtils.copyProperties(cardioPulmonarydto, cform);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    }

                    i = visulaImpairmentService.insertVisualImparmentAU(ds, personcode, visualimparment, systemip, loginid, cardioPulmonarydto, request);

                }
                if (i == 1) {
                    request.setAttribute("msgSuccess", "Visual Impairment Details Added Successfully");
                    target = "success";
                    // session.setAttribute("visualimpairment",new Double(visualimparmenttotalvalue));
                } else {
                    request.setAttribute("msgSuccess", "Erro in Visual Impairment Details");
                }
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
     * this method will get visual impairment details
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    public ActionForward getVisualImpairment(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        int i = 0;
        float visual = 0;
        String target = null;
        String personcode = null;
        CardioPulmonaryDTO cardioPulmonarydto = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            //personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }

            cardioPulmonarydto = new CardioPulmonaryDTO();
            CardioPulmonaryActionForm cform = (CardioPulmonaryActionForm) form;
            VisualImpairmentService visulaImpairmentService = VisualImpairmentServiceFactory.getVisualImparmentImpl();
            cardioPulmonarydto = visulaImpairmentService.getVisualImpairment(ds, personcode);
            try {
                BeanUtils.copyProperties(cform, cardioPulmonarydto);
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
                //throw new SADAREMDBException();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
                //throw new SADAREMDBException();
            }
            saveToken(request);
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
     * this method will update the visual impairment details
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    public ActionForward updateVisualImpairment(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        int i = 0;
        double visualimparment = 0;
        float visualimparmentI = 0;
        double visualimparmenttotalvalue = 0;
        String target = null;
        String personcode = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            boolean flag = isTokenValid(request, true);
            if (flag) {
                CardioPulmonaryDTO cardioPulmonarydto = new CardioPulmonaryDTO();
                HttpSession session = request.getSession();

                //  personcode = (String) session.getAttribute("personcode");

                if (session.getAttribute("sadaremCodeAu") != null) {
                    personcode = (String) session.getAttribute("sadaremCodeAu");
                } else {
                    personcode = (String) session.getAttribute("personcode");
                }


                String loginid = (String) session.getAttribute("loginid");

                String systemip = request.getRemoteAddr();
                CardioPulmonaryActionForm cform = (CardioPulmonaryActionForm) form;

                VisualImpairmentService visulaImpairmentService = VisualImpairmentServiceFactory.getVisualImparmentImpl();
                if (cform.getVisualimpairment() != null) {
                    visualimparment = Double.parseDouble(cform.getVisualimpairment());
                    visualimparmentI = Float.parseFloat(cform.getVisualimpairment());
                    visualimparmenttotalvalue = Double.parseDouble(cform.getVisualimpairment());
                    if (visualimparmenttotalvalue == 101.0) {
                        visualimparmenttotalvalue = 100.0;
                    }
                } else {
                    if (cform.getVisualimpairment() == null || "".equals(cform.getVisualimpairment())) {
                        visualimparment = 0;
                    }
                }
                try {
                    BeanUtils.copyProperties(cardioPulmonarydto, cform);
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                }

                i = visulaImpairmentService.updateVisualImparment(ds, personcode, visualimparment, systemip, loginid, cardioPulmonarydto, request);

            }
            if (i == 0 || i == 1) {
                target = "success";
            } else {
                target = "failure";
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
