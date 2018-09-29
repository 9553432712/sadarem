package org.bf.disability.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Exception.BaseDispatchAction;
import java.sql.SQLException;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.CardioPulmonaryActionForm;
import org.bf.disability.service.VisualImpairmentService;
import org.bf.disability.servicefactory.VisualImpairmentServiceFactory;
import org.bf.disability.common.DataBase.JNDIDataSource;

/**
 * 
 * @description this calss is a dispatch action used to insert,get and update the cardiopulmanary details
 * @author deviprasad.t    
 * @version 1.0
 */
public class CardioPulmonaryDispatchAction extends BaseDispatchAction {

    /**
     * 
     * @description this method is used to insert cardiopulmanary details
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @return 
     * @throws org.bf.disability.Exception.SADAREMException 
     */
    public ActionForward insertCardioPulmonary(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        DataSource ds = null;
        
        String personcode = null;
        ActionMessages actionMessages = null;
        double cardiopulmonarytotalvalue = 0;
        String target = "success";
        int i = 0;
        float cardiopulmonaryvalue = 0;
        try {
ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
            CardioPulmonaryActionForm cadioform = (CardioPulmonaryActionForm) form;
            String cardiopulmonary = cadioform.getCardiopulmonary();
            String Systemip = request.getRemoteAddr();
            VisualImpairmentService visulaImpairmentService =
                    VisualImpairmentServiceFactory.getVisualImparmentImpl();
            HttpSession session = request.getSession();
            //personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }

            String loginid = (String) session.getAttribute("loginid");

            if (cadioform.getCardiopulmonary() != null) {
                cardiopulmonaryvalue = Float.parseFloat(cardiopulmonary);
                cardiopulmonarytotalvalue = Double.parseDouble(cardiopulmonary);

                if (session.getAttribute("sadaremCodeAu") != null) {
                    i = visulaImpairmentService.insertCardioPulmonaryAU(ds, personcode, cardiopulmonaryvalue, Systemip, loginid, request);
                } else {
                    i = visulaImpairmentService.insertCardioPulmonary(ds, personcode, cardiopulmonaryvalue, Systemip, loginid, request);
                }
            }

            if (i == 1) {
                session.setAttribute("cardiopulmonary",
                        new Double(cardiopulmonarytotalvalue));
                request.setAttribute("msg", "Cardio Pulmonary Details Inserted Successfully");
                target = "success";
            }
            if (i == -1) {
                request.setAttribute("msg", "Error in Cardio Pulmonary Details Inserting");
                target = "dontstore";
            }

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
        return mapping.findForward(target);
    }

    /**
     *
     * @description this method is used to insert cardiopulmanary details
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws org.bf.disability.Exception.SADAREMException
     */
    public ActionForward insertCardioPulmonaryAU(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        DataSource ds = null;
        
        String personcode = null;
        ActionMessages actionMessages = null;
        double cardiopulmonarytotalvalue = 0;
        String target = null;
        int i = 0;
        float cardiopulmonaryvalue = 0;
        try {ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
           
            CardioPulmonaryActionForm cadioform = (CardioPulmonaryActionForm) form;
            String cardiopulmonary = cadioform.getCardiopulmonary();
            String Systemip = request.getRemoteAddr();
            VisualImpairmentService visulaImpairmentService =
                    VisualImpairmentServiceFactory.getVisualImparmentImpl();
            HttpSession session = request.getSession();
            personcode = (String) session.getAttribute("sadaremCodeAu");
            String loginid = (String) session.getAttribute("loginid");

            if (cadioform.getCardiopulmonary() != null) {
                cardiopulmonaryvalue = Float.parseFloat(cardiopulmonary);
                cardiopulmonarytotalvalue = Double.parseDouble(cardiopulmonary);
                i = visulaImpairmentService.insertCardioPulmonaryAU(ds, personcode, cardiopulmonaryvalue, Systemip, loginid, request);

            }
            target = "success";
            if (i == 1) {
                session.setAttribute("cardiopulmonary",
                        new Double(cardiopulmonarytotalvalue));
                request.setAttribute("msgSuccess", "CardioPulmonary Details Added Successfully");
            }
            if (i == -1) {
                request.setAttribute("msgFailure", "Error in CardioPulmonary Details Addding");
                target = "dontstore";
            }
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
        return mapping.findForward(target);
    }

    /**
     * 
     * @description this method is used to get the cardiopulmanary details
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @return 
     * @throws org.bf.disability.Exception.SADAREMException 
     */
    public ActionForward getCardio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        CardioPulmonaryActionForm cardiopulmonaryform =
                (CardioPulmonaryActionForm) form;
        String target = "success";
        float cardiopulmonary = -1;
        int i;
        String cpvalue = null;
        DataSource ds = null;
        
        String personcode = null;
        ActionMessages actionMessages = null;
        try {
            ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
            VisualImpairmentService visulaImpairmentService =
                    VisualImpairmentServiceFactory.getVisualImparmentImpl();
            HttpSession session = request.getSession();
            // personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }


            cardiopulmonary = visulaImpairmentService.getCardioPulmonaryDetails(ds,
                    personcode);
            cpvalue = String.valueOf(cardiopulmonary);
            for (i = 0; i < cpvalue.length(); i++) {
                char ch = cpvalue.charAt(i);
                if (ch == '.') {
                    break;
                }
            }
            cpvalue = cpvalue.substring(0, i);
            if (cardiopulmonary >= 0) {
                saveToken(request);
                cardiopulmonaryform.setCardiopulmonary(cpvalue);
                target = "success";

            } else {
                target = "failure";
            }
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
        return mapping.findForward(target);
    }

    /**
     * 
     * @description this method is used to update the cardio pulmanay details
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @return 
     * @throws org.bf.disability.Exception.SADAREMException 
     */
    public ActionForward updateCardio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        int i = 0;
        float cardio = 0;
        String target = null;
        DataSource ds = null;
        
        String personcode = null;

        ActionMessages actionMessages = null;
        //DataSource ds=getDataSource(request);

        try {
            boolean flag = isTokenValid(request, true);
            if (flag) {
                ds = getDataSource(request);
                if (ds == null || "null".equals(ds)) {
                    ds = JNDIDataSource.getConnection();
                }

                HttpSession session = request.getSession();
                CardioPulmonaryActionForm cardiopulmonaryform =
                        (CardioPulmonaryActionForm) form;

                // personcode = (String) session.getAttribute("personcode");

                if (session.getAttribute("sadaremCodeAu") != null) {
                    personcode = (String) session.getAttribute("sadaremCodeAu");
                } else {
                    personcode = (String) session.getAttribute("personcode");
                }


                String loginid = (String) session.getAttribute("loginid");
                String systemip = request.getRemoteAddr();
                VisualImpairmentService visulaImpairmentService =
                        VisualImpairmentServiceFactory.getVisualImparmentImpl();
                if (cardiopulmonaryform.getCardiopulmonary() != null) {
                    cardio = Float.parseFloat(cardiopulmonaryform.getCardiopulmonary());
                }
                //  if(session.getAttribute("sadaremCodeAu")!=null) {
                //       i = visulaImpairmentService.insertCardioPulmonary(ds, personcode,
                //           cardio, systemip, loginid,request);
                //    }else {
                i = visulaImpairmentService.updateCardioPolumonary(ds, personcode,
                        cardio, systemip, loginid, request);
                //   }
                session.setAttribute("cardiopulmonary", new Double(cardio));
            }
            if (i == 1) {
                target = "success";
                request.setAttribute("msg", "Updated Successfully");
            }
            if (i == 0) {
                target = "dontstore";
            }

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

        return mapping.findForward(target);
    }
}
