package org.bf.disability.action;

import java.lang.reflect.InvocationTargetException;
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
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.MentalIllnessDTO;
import org.bf.disability.form.MentalIllnessActionForm;
import org.bf.disability.service.VisualImpairmentService;
import org.bf.disability.servicefactory.VisualImpairmentServiceFactory;

/**
 * this action class will maipulate the mental mental ill ness disability details
 * @author kiran h
 * @version 1.0
 */
public class UpdateMentalIllnessAction extends BaseAction {

    /**
     * this method will manipulate the mental illness disability details
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws java.lang.Exception 
     * @return Action Froward
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int i = 0, globalScore = 0, mentaldisability = 0;
        String target = null;
        String personcode = null;
        ActionMessages actionMessages = null;
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            boolean flag = isTokenValid(request, true);
            if (flag) {
                super.execute(mapping, form, request, response);
                HttpSession session = request.getSession();
                // personcode = (String) session.getAttribute("personcode");

                if (session.getAttribute("sadaremCodeAu") != null) {
                    personcode = (String) session.getAttribute("sadaremCodeAu");
                } else {
                    personcode = (String) session.getAttribute("personcode");
                }


                String loginid = (String) session.getAttribute("loginid");

                MentalIllnessActionForm millnessform = (MentalIllnessActionForm) form;
                MentalIllnessDTO millnessdto = new MentalIllnessDTO();
                try {
                    BeanUtils.copyProperties(millnessdto, millnessform);
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                    throw new SADAREMException();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                    throw new SADAREMException();
                }
                millnessdto.setPersoncode(personcode);
                VisualImpairmentService visulaImpairmentService = VisualImpairmentServiceFactory.getVisualImparmentImpl();


                if (millnessform.getGlobalscore() != null || millnessform.getGlobalscore() != "") {
                    globalScore = Integer.parseInt(millnessform.getGlobalscore());
                }

                if (globalScore != 0) {

                    //These values are hard coaded.based on Table no 3.in Mental Illness
                    switch (globalScore) {
                        case 0:
                            mentaldisability = 0;
                            break;
                        case 1:
                            mentaldisability = 7;
                            break;
                        case 2:
                            mentaldisability = 13;
                            break;
                        case 3:
                            mentaldisability = 20;
                            break;
                        case 4:
                            mentaldisability = 26;
                            break;
                        case 5:
                            mentaldisability = 33;
                            break;
                        case 6:
                            mentaldisability = 39;
                            break;
                        case 7:
                            mentaldisability = 40;
                            break;
                        case 8:
                            mentaldisability = 45;
                            break;
                        case 9:
                            mentaldisability = 50;
                            break;
                        case 10:
                            mentaldisability = 55;
                            break;
                        case 11:
                            mentaldisability = 60;
                            break;
                        case 12:
                            mentaldisability = 65;
                            break;
                        case 13:
                            mentaldisability = 70;
                            break;
                        case 14:
                            mentaldisability = 71;
                            break;
                        case 15:
                            mentaldisability = 77;
                            break;
                        case 16:
                            mentaldisability = 83;
                            break;
                        case 17:
                            mentaldisability = 89;
                            break;
                        case 18:
                            mentaldisability = 95;
                            break;
                        case 19:
                            mentaldisability = 99;
                            break;
                        case 20:
                            mentaldisability = 100;
                            break;
                    }

                    millnessdto.setGlobalscore(globalScore);
                    millnessdto.setMentaldisability(mentaldisability);
                    millnessdto.setLoginid(loginid);
                    millnessdto.setSystemip(request.getRemoteAddr());

                }
                // if(session.getAttribute("sadaremCodeAu")!=null) {
                //   i = visulaImpairmentService.insertMentalIllnessAU(ds, millnessdto,request);

                //  }else {
                i = visulaImpairmentService.updateMentalIllnesDetails(ds, millnessdto, request);

                //  }

                session.setAttribute("mentalillness", new Double(String.valueOf(mentaldisability)));
            }

            if (i == 0 || i != 0) {
                target = "success";
                request.setAttribute("msg", "Mental Illness Details Updated Successfully");
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
