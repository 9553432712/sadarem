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
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.MentalIllnessDTO;
import org.bf.disability.form.MentalIllnessActionForm;
import org.bf.disability.service.VisualImpairmentService;
import org.bf.disability.servicefactory.VisualImpairmentServiceFactory;

/**
 * This action class is used to manipulate the mental illness details
 * @author kiran h
 * @version 1.0
 */
public class MentalIllnessAction extends BaseAction {

    ActionMessages actionMessages = null;

    /**
     * this method is used to manipulate the mental illness disability details
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {



        int globalScore = 0, i = 0, range = 0, mentaldisability = 0;
        String target = "success";
        DataSource ds = null;

        String personcode = null;
        //DataSource ds=getDataSource(request);


        HttpSession session = request.getSession();
        // personcode = (String) session.getAttribute("personcode");

        if (session.getAttribute("sadaremCodeAu") != null) {
            personcode = (String) session.getAttribute("sadaremCodeAu");
        } else {
            personcode = (String) session.getAttribute("personcode");
        }


        String loginid = (String) session.getAttribute("loginid");
        MentalIllnessActionForm mform = (MentalIllnessActionForm) form;
        MentalIllnessDTO mdto = new MentalIllnessDTO();
        VisualImpairmentService visulaImpairmentService = VisualImpairmentServiceFactory.getVisualImparmentImpl();
        //This is the formula to count the Global Disability Score
        //GlobalScore= mform.getSelfcare()+mform.getPersonalactivities()+mform.getCommunication()+mform.getWork()+mform.getDuration();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            super.execute(mapping, form, request, response);
            if (mform.getGlobalscore() != null || mform.getGlobalscore() != "") {
                globalScore = Integer.parseInt(mform.getGlobalscore());
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

                try {
                    BeanUtils.copyProperties(mdto, mform);
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                    //throw new SADAREMDBException();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                    //throw new SADAREMDBException();
                }


                mdto.setGlobalscore(globalScore);
                mdto.setMentaldisability(mentaldisability);
                mdto.setPersoncode(personcode);
                mdto.setLoginid(loginid);
                mdto.setSystemip(request.getRemoteAddr());


                if (session.getAttribute("sadaremCodeAu") != null) {
                    i = visulaImpairmentService.insertMentalIllnessAU(ds, mdto, request);
                } else {
                    i = visulaImpairmentService.insertMentalIllness(ds, mdto, request);
                }


                if (i == 1) {
                    target = "success";
                    session.setAttribute("mentalillness", new Double(String.valueOf(mentaldisability)));
                } else {
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
}
