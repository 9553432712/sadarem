package org.bf.disability.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
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
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.DwarfismDTO;
import org.bf.disability.form.DwarfismActionForm;
import org.bf.disability.service.VisualImpairmentService;
import org.bf.disability.servicefactory.VisualImpairmentServiceFactory;
import org.bf.disability.common.DataBase.JNDIDataSource;

/**
 * 
 * @description this action class is used to manipulate the drawfism use case
 * @author kiran h
 * @version 1.0
 */
public class DwarfismAction extends BaseAction {

    /**
     * 
     * @description this method will be used to manipulate the drawfism disability use case
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @return Action Forward
     * @throws org.bf.disability.Exception.SADAREMException 
     */
    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        int age = 0, i = 0;
        float heighthavetobe = 0;
        float actualheight = 0;
        float heightloss = 0;
        float dwarfism = 0;

        String target = null;
        String gender = null;
        DataSource ds = null;
        DwarfismActionForm dform = (DwarfismActionForm) form;
        ActionMessages actionMessages = null;
        VisualImpairmentService visulaImpairmentService =
                VisualImpairmentServiceFactory.getVisualImparmentImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            if (dform.getAgeyears() != "") {
                age = Integer.parseInt(dform.getAgeyears());
            }
            if (dform.getAgemonths() == "") {
                dform.setAgemonths("0");
            }
            //DataSource ds=getDataSource(request);



            DwarfismDTO dto = new DwarfismDTO();

            if (age >= 21) //After 21 years height will not increased
            {
                age = 21; //
            }
            HttpSession session = request.getSession();
            String personcode = null;

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }

            String loginid = (String) session.getAttribute("loginid");



            super.execute(mapping, form, request, response);
            if (dform.getAgeyears() != "" && dform.getAgemonths() != "" && dform.getHeight() != "") {

                gender = visulaImpairmentService.getGender(ds, personcode);

                if (gender.equalsIgnoreCase("1")) {
                    gender = "Male";
                }
                if (gender.equalsIgnoreCase("2")) {
                    gender = "FeMale";
                }

                //This Switch case defines Normal heights of the persons according to their age
                switch (age) {
                    case 0:
                        if ((Integer.parseInt(dform.getAgemonths()) >= 1)
                                && (Integer.parseInt(dform.getAgemonths()) < 3)) {
                            if (gender.equalsIgnoreCase("Male")) {
                                heighthavetobe = 17.49f;
                            }
                            if (gender.equalsIgnoreCase("Female")) {
                                heighthavetobe = 17.39f;
                            }
                        }

                        if (Integer.parseInt(dform.getAgemonths()) >= 3
                                && Integer.parseInt(dform.getAgemonths()) < 6) {
                            if (gender.equalsIgnoreCase("Male")) {
                                heighthavetobe = 21.52f;
                            }
                            if (gender.equalsIgnoreCase("Female")) {
                                heighthavetobe = 21.80f;
                            }
                        }

                        if (Integer.parseInt(dform.getAgemonths()) >= 6
                                && Integer.parseInt(dform.getAgemonths()) < 9) {
                            if (gender.equalsIgnoreCase("Male")) {
                                heighthavetobe = 19.17f;
                            }
                            if (gender.equalsIgnoreCase("Female")) {
                                heighthavetobe = 22.49f;
                            }
                        }
                        if (Integer.parseInt(dform.getAgemonths()) >= 9
                                && Integer.parseInt(dform.getAgemonths()) < 12) {
                            if (gender.equalsIgnoreCase("Male")) {
                                heighthavetobe = 23.82f;
                            }
                            if (gender.equalsIgnoreCase("Female")) {
                                heighthavetobe = 23.22f;
                            }
                        }
                        break;
                    case 1:
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 24.95f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 24.46f;
                        }
                        break;
                    case 2:
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 27.93f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 26.97f;
                        }
                        break;
                    case 3:
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 29.80f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 29.33f;
                        }
                        break;
                    case 4:
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 32.50f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 32.20f;
                        }
                        break;
                    case 5:
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 33.84f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 34.12f;
                        }
                        break;
                    case 6:
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 37.09f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 35.46f;
                        }
                        break;
                    case 7:
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 38.02f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 37.72f;
                        }
                        break;
                    case 8:
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 41.18f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 40.47f;
                        }
                        break;
                    case 9:
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 41.40f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 42.46f;
                        }
                        break;
                    case 10:
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 41.11f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 44.25f;
                        }
                        break;
                    case 11:
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 44.86f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 45.14f;
                        }
                        break;
                    case 12:
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 46.47f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 46.74f;
                        }
                        break;
                    case 13:
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 49.25f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 49.39f;
                        }
                        break;
                    case 14:
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 51.20f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 50.43f;
                        }
                        break;
                    case 15:
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 53.34f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 52.35f;
                        }
                        break;
                    case 16:
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 55.11f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 53.84f;
                        }
                        break;
                    case 17:
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 55.32f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 53.74f;
                        }
                        break;
                    case 18:
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 56.69f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 55.10f;
                        }
                        break;
                    case 19:
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 56.79f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 55.10f;
                        }
                        break;
                    case 20:
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 59.10f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 55.08f;
                        }
                        break;
                    case 21:
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 59.84f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 55.76f;
                        }
                        break;
                }


                if (dform.getHeight() != "") {
                    actualheight = Float.parseFloat(dform.getHeight());
                }


                if (heighthavetobe > actualheight) {
                    heightloss = heighthavetobe - actualheight;

                    dwarfism = 4 * heightloss;  //This is the formula.
                    if (dwarfism > 100) {
                        dwarfism = 100;
                    }
                }

                try {
                    BeanUtils.copyProperties(dto, dform);
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                    //throw new SADAREMDBException();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                    //throw new SADAREMDBException();
                }
                dto.setDwarfism(dwarfism);
                dto.setSystemip(request.getRemoteAddr());
                dto.setPersoncode(personcode);
                dto.setLoginid(loginid);
                //  i = visulaImpairmentService.insertDwarfism(ds, dto,request);

                if (session.getAttribute("sadaremCodeAu") != null) {
                    i = visulaImpairmentService.insertDwarfismAU(ds, dto, request);
                } else {
                    i = visulaImpairmentService.insertDwarfism(ds, dto, request);
                }


                if (i == -1) {
                    request.setAttribute("msg", "Error in Dwarfism Details Inserting");
                    target = "failure";
                } else {
                    request.setAttribute("msg", "Dwarfism Details Inserted Successfully");
                    target = "success";
                    session.setAttribute("dwarfism",
                            new Double(String.valueOf(dwarfism)));
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
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }
}
