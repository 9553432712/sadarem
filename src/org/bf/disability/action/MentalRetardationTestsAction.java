/*
 * MentalRetardationTestsAction.java
 *
 * Created on October 1, 2008, 11:14 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;


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
import org.bf.disability.dto.MentalRetardationDTO;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.MentalRetardationTestsDTO;
import org.bf.disability.form.MentalRetardationTestsForm;
import org.bf.disability.service.MentalRetardationService;
import org.bf.disability.servicefactory.MentalRetardationServiceFactory;

/**
 *
 * @author svsganesh
 * @ This class is use for Inserting,Selecting,Updating the MentalRetardationTestDetails.
 */
public class MentalRetardationTestsAction extends BaseDispatchAction {

    ActionMessages actionMessages = null;

    /**
     *
     * @ This method is use for Inserting the AlexanderTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward insertMRAlexanderTestDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = "0";
        double patIQ = 0;
        DataSource ds = null;

        MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;
        HttpSession session = request.getSession(true);
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String personcode = null;

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }

            String loginID = (String) session.getAttribute("loginid");
            String sysgtemIP = request.getRemoteAddr();
            //DataSource ds=getDataSource(request);


            double ca = 0;
            if (session.getAttribute("chronologicalageAU") != null) {
                ca = ((Double) session.getAttribute("chronologicalageAU")).doubleValue();
            } else {
                ca = ((Double) session.getAttribute("chronologicalage")).doubleValue();
            }


            if (ca < 16) {
                patIQ = calculateIQ(convertToYears(mentalRetardationTestsForm.getPat_Year(), mentalRetardationTestsForm.getPat_Month()), ca);
            } else {
                ca = 16;
                patIQ = calculateIQ(convertToYears(mentalRetardationTestsForm.getPat_Year(), mentalRetardationTestsForm.getPat_Month()), ca);
            }

            mentalRetardationTestsForm.setPat_IQ(patIQ);
            MentalRetardationTestsDTO mentalRetardationTestsDTO = new MentalRetardationTestsDTO();
            mentalRetardationTestsForm.setLoginID(loginID);
            mentalRetardationTestsForm.setSystemIP(sysgtemIP);
            mentalRetardationTestsForm.setPersoncode(personcode);

            if ("".equals(mentalRetardationTestsForm.getPat_Second_One())) {
                mentalRetardationTestsForm.setPat_Second_One("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Second_Two())) {
                mentalRetardationTestsForm.setPat_Second_Two("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Second_Three())) {
                mentalRetardationTestsForm.setPat_Second_Three("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Second_Four())) {
                mentalRetardationTestsForm.setPat_Second_Four("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Second_Five())) {
                mentalRetardationTestsForm.setPat_Second_Five("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Second_Six())) {
                mentalRetardationTestsForm.setPat_Second_Six("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Second_Seven())) {
                mentalRetardationTestsForm.setPat_Second_Seven("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Second_Eight())) {
                mentalRetardationTestsForm.setPat_Second_Eight("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Second_Nine())) {
                mentalRetardationTestsForm.setPat_Second_Nine("0");
            }

            if ("".equals(mentalRetardationTestsForm.getPat_SA_One())) {
                mentalRetardationTestsForm.setPat_SA_One("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_SA_Two())) {
                mentalRetardationTestsForm.setPat_SA_Two("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_SA_Three())) {
                mentalRetardationTestsForm.setPat_SA_Three("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_SA_Four())) {
                mentalRetardationTestsForm.setPat_SA_Four("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_SA_Five())) {
                mentalRetardationTestsForm.setPat_SA_Five("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_SA_Six())) {
                mentalRetardationTestsForm.setPat_SA_Six("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_SA_Seven())) {
                mentalRetardationTestsForm.setPat_SA_Seven("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_SA_Eight())) {
                mentalRetardationTestsForm.setPat_SA_Eight("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_SA_Nine())) {
                mentalRetardationTestsForm.setPat_SA_Nine("0");
            }

            if ("".equals(mentalRetardationTestsForm.getPat_Remarks_One())) {
                mentalRetardationTestsForm.setPat_Remarks_One("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Two())) {
                mentalRetardationTestsForm.setPat_Remarks_Two("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Three())) {
                mentalRetardationTestsForm.setPat_Remarks_Three("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Four())) {
                mentalRetardationTestsForm.setPat_Remarks_Four("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Five())) {
                mentalRetardationTestsForm.setPat_Remarks_Five("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Six())) {
                mentalRetardationTestsForm.setPat_Remarks_Six("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Seven())) {
                mentalRetardationTestsForm.setPat_Remarks_Seven("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Eight())) {
                mentalRetardationTestsForm.setPat_Remarks_Eight("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Nine())) {
                mentalRetardationTestsForm.setPat_Remarks_Nine("0");
            }

            BeanUtils.copyProperties(mentalRetardationTestsDTO, mentalRetardationTestsForm);
            MentalRetardationService mentalRetardationService = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();



            boolean isPerosonCodeExist = mentalRetardationService.checkForPersonCode(ds, personcode, "tblPerson_MR_PAT_Test_Details");

            if (isPerosonCodeExist) {
                if (session.getAttribute("sadaremCodeAu") != null) {
                    int i = mentalRetardationService.insertMRAlexanderTestDetailsAU(ds, personcode, mentalRetardationTestsDTO, request);
                    if (i == -1) {
                        target = "failure";
                    } else {
                        target = "success";
                    }

                } else {
                }

            } else {
                mentalRetardationService.insertMRAlexanderTestDetails(ds, personcode, mentalRetardationTestsDTO, request);
                target = "success";
            }

            //session.setAttribute("pat", new Double(patIQ));

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
     *
     * @ This method is use for Inserting the AlexanderTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward insertMRAlexanderTestDetailsAU(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = "0";
        double patIQ = 0;
        DataSource ds = null;

        MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;
        HttpSession session = request.getSession(true);
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String personcode = (String) session.getAttribute("sadaremCodeAu");
            String loginID = (String) session.getAttribute("loginid");
            String sysgtemIP = request.getRemoteAddr();
            //DataSource ds=getDataSource(request);



            double ca = ((Double) session.getAttribute("chronologicalageAU")).doubleValue();
            if (ca < 16) {
                patIQ = calculateIQ(convertToYears(mentalRetardationTestsForm.getPat_Year(), mentalRetardationTestsForm.getPat_Month()), ca);
            } else {
                ca = 16;
                patIQ = calculateIQ(convertToYears(mentalRetardationTestsForm.getPat_Year(), mentalRetardationTestsForm.getPat_Month()), ca);
            }

            mentalRetardationTestsForm.setPat_IQ(patIQ);
            MentalRetardationTestsDTO mentalRetardationTestsDTO = new MentalRetardationTestsDTO();
            mentalRetardationTestsForm.setLoginID(loginID);
            mentalRetardationTestsForm.setSystemIP(sysgtemIP);
            mentalRetardationTestsForm.setPersoncode(personcode);

            if ("".equals(mentalRetardationTestsForm.getPat_Second_One())) {
                mentalRetardationTestsForm.setPat_Second_One("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Second_Two())) {
                mentalRetardationTestsForm.setPat_Second_Two("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Second_Three())) {
                mentalRetardationTestsForm.setPat_Second_Three("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Second_Four())) {
                mentalRetardationTestsForm.setPat_Second_Four("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Second_Five())) {
                mentalRetardationTestsForm.setPat_Second_Five("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Second_Six())) {
                mentalRetardationTestsForm.setPat_Second_Six("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Second_Seven())) {
                mentalRetardationTestsForm.setPat_Second_Seven("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Second_Eight())) {
                mentalRetardationTestsForm.setPat_Second_Eight("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Second_Nine())) {
                mentalRetardationTestsForm.setPat_Second_Nine("0");
            }

            if ("".equals(mentalRetardationTestsForm.getPat_SA_One())) {
                mentalRetardationTestsForm.setPat_SA_One("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_SA_Two())) {
                mentalRetardationTestsForm.setPat_SA_Two("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_SA_Three())) {
                mentalRetardationTestsForm.setPat_SA_Three("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_SA_Four())) {
                mentalRetardationTestsForm.setPat_SA_Four("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_SA_Five())) {
                mentalRetardationTestsForm.setPat_SA_Five("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_SA_Six())) {
                mentalRetardationTestsForm.setPat_SA_Six("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_SA_Seven())) {
                mentalRetardationTestsForm.setPat_SA_Seven("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_SA_Eight())) {
                mentalRetardationTestsForm.setPat_SA_Eight("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_SA_Nine())) {
                mentalRetardationTestsForm.setPat_SA_Nine("0");
            }

            if ("".equals(mentalRetardationTestsForm.getPat_Remarks_One())) {
                mentalRetardationTestsForm.setPat_Remarks_One("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Two())) {
                mentalRetardationTestsForm.setPat_Remarks_Two("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Three())) {
                mentalRetardationTestsForm.setPat_Remarks_Three("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Four())) {
                mentalRetardationTestsForm.setPat_Remarks_Four("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Five())) {
                mentalRetardationTestsForm.setPat_Remarks_Five("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Six())) {
                mentalRetardationTestsForm.setPat_Remarks_Six("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Seven())) {
                mentalRetardationTestsForm.setPat_Remarks_Seven("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Eight())) {
                mentalRetardationTestsForm.setPat_Remarks_Eight("0");
            }
            if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Nine())) {
                mentalRetardationTestsForm.setPat_Remarks_Nine("0");
            }

            BeanUtils.copyProperties(mentalRetardationTestsDTO, mentalRetardationTestsForm);
            MentalRetardationService mentalRetardationService = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();



            boolean isPerosonCodeExist = mentalRetardationService.checkForPersonCode(ds, personcode, "tblPerson_MR_PAT_Test_Details");

            if (isPerosonCodeExist) {
                target = "failure";

            } else {
                mentalRetardationService.insertMRAlexanderTestDetailsAU(ds, personcode, mentalRetardationTestsDTO, request);
                target = "success";
            }

            //session.setAttribute("pat", new Double(patIQ));

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
     *
     * @ This Method is use for getting the AlexanderTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward getMRAlexanderTestDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

        String target = "0";
        DataSource ds = null;

        HttpSession session = request.getSession(true);


        MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            String personcode = null;//(String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }


            MentalRetardationService mentalRetardationService = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
            MentalRetardationTestsDTO mentalRetardationTestsDTO = mentalRetardationService.getMRAlexanderTestDetails(ds, personcode);
            BeanUtils.copyProperties(mentalRetardationTestsForm, mentalRetardationTestsDTO);
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
     *
     * @ This method is use for Updating the AlexanderTestDetails.
     * @param mapping ActionMapping
     * @param form MentalRetardationTestsForm
     * @throws java.lang.SADAREMException
     */
    public ActionForward updateMRAlexanderTestDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = null;
        double patIQ = 0;
        DataSource ds = null;


        MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;
        HttpSession session = request.getSession(true);
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            boolean flag = isTokenValid(request, true);
            if (flag) {
                String personcode = null;//(String) session.getAttribute("personcode");
                String loginID = (String) session.getAttribute("loginid");
                String sysgtemIP = request.getRemoteAddr();
                //DataSource ds=getDataSource(request);


                // double ca = ((Double) session.getAttribute("chronologicalage")).doubleValue();

                if (session.getAttribute("sadaremCodeAu") != null) {
                    personcode = (String) session.getAttribute("sadaremCodeAu");
                } else {
                    personcode = (String) session.getAttribute("personcode");
                }


                double ca = 0;

                if (session.getAttribute("chronologicalageAU") != null) {
                    ca = ((Double) session.getAttribute("chronologicalageAU")).doubleValue();
                } else {
                    ca = ((Double) session.getAttribute("chronologicalage")).doubleValue();
                }



                if (ca < 16) {
                    patIQ = calculateIQ(convertToYears(mentalRetardationTestsForm.getPat_Year(), mentalRetardationTestsForm.getPat_Month()), ca);
                } else {
                    ca = 16;
                    patIQ = calculateIQ(convertToYears(mentalRetardationTestsForm.getPat_Year(), mentalRetardationTestsForm.getPat_Month()), ca);
                }
                mentalRetardationTestsForm.setPat_IQ(patIQ);
                MentalRetardationTestsDTO mentalRetardationTestsDTO = new MentalRetardationTestsDTO();
                mentalRetardationTestsForm.setLoginID(loginID);
                mentalRetardationTestsForm.setSystemIP(sysgtemIP);
                mentalRetardationTestsForm.setPersoncode(personcode);

                if ("".equals(mentalRetardationTestsForm.getPat_Second_One())) {
                    mentalRetardationTestsForm.setPat_Second_One("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_Second_Two())) {
                    mentalRetardationTestsForm.setPat_Second_Two("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_Second_Three())) {
                    mentalRetardationTestsForm.setPat_Second_Three("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_Second_Four())) {
                    mentalRetardationTestsForm.setPat_Second_Four("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_Second_Five())) {
                    mentalRetardationTestsForm.setPat_Second_Five("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_Second_Six())) {
                    mentalRetardationTestsForm.setPat_Second_Six("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_Second_Seven())) {
                    mentalRetardationTestsForm.setPat_Second_Seven("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_Second_Eight())) {
                    mentalRetardationTestsForm.setPat_Second_Eight("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_Second_Nine())) {
                    mentalRetardationTestsForm.setPat_Second_Nine("0");
                }

                if ("".equals(mentalRetardationTestsForm.getPat_SA_One())) {
                    mentalRetardationTestsForm.setPat_SA_One("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_SA_Two())) {
                    mentalRetardationTestsForm.setPat_SA_Two("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_SA_Three())) {
                    mentalRetardationTestsForm.setPat_SA_Three("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_SA_Four())) {
                    mentalRetardationTestsForm.setPat_SA_Four("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_SA_Five())) {
                    mentalRetardationTestsForm.setPat_SA_Five("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_SA_Six())) {
                    mentalRetardationTestsForm.setPat_SA_Six("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_SA_Seven())) {
                    mentalRetardationTestsForm.setPat_SA_Seven("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_SA_Eight())) {
                    mentalRetardationTestsForm.setPat_SA_Eight("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_SA_Nine())) {
                    mentalRetardationTestsForm.setPat_SA_Nine("0");
                }

                if ("".equals(mentalRetardationTestsForm.getPat_Remarks_One())) {
                    mentalRetardationTestsForm.setPat_Remarks_One("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Two())) {
                    mentalRetardationTestsForm.setPat_Remarks_Two("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Three())) {
                    mentalRetardationTestsForm.setPat_Remarks_Three("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Four())) {
                    mentalRetardationTestsForm.setPat_Remarks_Four("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Five())) {
                    mentalRetardationTestsForm.setPat_Remarks_Five("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Six())) {
                    mentalRetardationTestsForm.setPat_Remarks_Six("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Seven())) {
                    mentalRetardationTestsForm.setPat_Remarks_Seven("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Eight())) {
                    mentalRetardationTestsForm.setPat_Remarks_Eight("0");
                }
                if ("".equals(mentalRetardationTestsForm.getPat_Remarks_Nine())) {
                    mentalRetardationTestsForm.setPat_Remarks_Nine("0");
                }
                BeanUtils.copyProperties(mentalRetardationTestsDTO, mentalRetardationTestsForm);
                MentalRetardationService mentalRetardationService = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
                // modified by ganesh

                boolean isPerosonCodeExist = mentalRetardationService.checkForPersonCode(ds, personcode, "tblPerson_MR_PAT_Test_Details");
                if (isPerosonCodeExist) {
                    //  if (session.getAttribute("sadaremCodeAu") != null) {
                    //    mentalRetardationService.insertMRAlexanderTestDetails(ds, personcode, mentalRetardationTestsDTO,request);
                    //   }else {
                    mentalRetardationService.updateMRAlexanderTestDetails(ds, personcode, mentalRetardationTestsDTO, request);
                    //  }
                } else {
                    mentalRetardationService.insertMRAlexanderTestDetails(ds, personcode, mentalRetardationTestsDTO, request);
                }
                // end of modification
                // session.setAttribute("pat", new Double(patIQ));
                if (!session.getAttribute("roleId").equals("5")) {
                    MentalRetardationTestsDTO disableMentalItems = mentalRetardationService.getMentalRetardationIQValues(ds, personcode);
                    request.setAttribute("disableMentalItems", disableMentalItems);
                }
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
     *
     * @ This Method is use for inserting the DevelopmentalTestScreeningDetails.
     * @param mapping ActionMapping
     * @param form MentalRetardationTestsForm
     * @throws java.lang.SADAREMException
     */
    public ActionForward insertDevelopmentalTestScreening(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        DataSource ds = null;

        //DataSource ds= getDataSource(request);




        HttpSession session = request.getSession();

        ActionMessages actionMessages = null;
        double developmentalresult = 0;
        String month;
        String year;
        String personcode, loginid, systemip;
        MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;
        MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
        double ca = 0;
        double ma = 0;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            // personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }

            loginid = (String) session.getAttribute("loginid");
            systemip = request.getRemoteAddr();

            if (session.getAttribute("chronologicalageAU") != null) {
                ca = ((Double) session.getAttribute("chronologicalageAU")).doubleValue();
            } else {
                ca = ((Double) session.getAttribute("chronologicalage")).doubleValue();
            }
            month = mentalRetardationTestsForm.getDstmonth();
            year = mentalRetardationTestsForm.getDstyear();
            ma = convertToYears(year, month);
            if (ca < 15) {
                developmentalresult = calculateIQ(ma, ca);
            } else {
                ca = 15;
                developmentalresult = calculateIQ(ma, ca);
            }
            //session.setAttribute("developmentalresult", new Double(developmentalresult));
            boolean isPerosonCodeExist = mentalretarservice.checkForPersonCode(ds, personcode, "tblPerson_MR_DST_Test_Details");
            int i = 0;
            if (isPerosonCodeExist) {
                if (session.getAttribute("sadaremCodeAu") != null) {
                    i = mentalretarservice.insertDevelopmentalDataAU(ds, personcode, year, month, developmentalresult, loginid, systemip, request);
                } else {
                    target = "failure";
                }

            } else {
                i = mentalretarservice.insertDevelopmentalData(ds, personcode, year, month, developmentalresult, loginid, systemip, request);
                target = "success";
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
     *
     * @ This Method is use for inserting the DevelopmentalTestScreeningDetails.
     * @param mapping ActionMapping
     * @param form MentalRetardationTestsForm
     * @throws java.lang.SADAREMException
     */
    public ActionForward insertDevelopmentalTestScreeningAU(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        DataSource ds = null;

        //DataSource ds= getDataSource(request);



        HttpSession session = request.getSession();

        ActionMessages actionMessages = null;
        double developmentalresult = 0;
        String month;
        String year;
        String personcode, loginid, systemip;
        MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;
        MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
        double ca = 0;
        double ma = 0;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            personcode = (String) session.getAttribute("sadaremCodeAu");
            loginid = (String) session.getAttribute("loginid");

            systemip = request.getRemoteAddr();
            ca = ((Double) session.getAttribute("chronologicalageAU")).doubleValue();

            month = mentalRetardationTestsForm.getDstmonth();
            year = mentalRetardationTestsForm.getDstyear();
            ma = convertToYears(year, month);
            if (ca < 15) {
                developmentalresult = calculateIQ(ma, ca);
            } else {
                ca = 15;
                developmentalresult = calculateIQ(ma, ca);
            }
            //session.setAttribute("developmentalresult", new Double(developmentalresult));
            boolean isPerosonCodeExist = mentalretarservice.checkForPersonCode(ds, personcode, "tblPerson_MR_DST_Test_Details");

            if (isPerosonCodeExist) {
                target = "failure";

            } else {
                mentalretarservice.insertDevelopmentalDataAU(ds, personcode, year, month, developmentalresult, loginid, systemip, request);
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
     *
     * @ This Method is use for getting the DevelopmentalTestScreeningDetails.
     * @param mapping ActionMapping
     * @param form MentalRetardationTestsForm
     * @throws java.lang.SADAREMException
     */
    public ActionForward getDevelopmentalData(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        String personcode;
        DataSource ds = null;

        //DataSource ds= getDataSource(request);

        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;
        MentalRetardationTestsDTO mentalretardationtestdto = new MentalRetardationTestsDTO();
        MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            personcode = (String) session.getAttribute("personcode");
            mentalretardationtestdto = mentalretarservice.getDevelopmentalData(ds, personcode);
            BeanUtils.copyProperties(mentalRetardationTestsForm, mentalretardationtestdto);
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
     *
     * @ This method is use for Updating the DevelopmentalTestScreeningDetails.
     * @param mapping ActionMapping
     * @param form MentalRetardationTestsForm
     * @throws java.lang.SADAREMException
     */
    public ActionForward updateDevelopmentalTestScreening(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        DataSource ds = null;


        //DataSource ds= getDataSource(request);


        HttpSession session = request.getSession();

        ActionMessages actionMessages = null;
        double developmentalresult = 0;
        String month;
        String year;
        String personcode, loginid, systemip;
        MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;
        MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
        double ca = 0;
        double ma = 0;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            // personcode = (String) session.getAttribute("personcode");
            loginid = (String) session.getAttribute("loginid");
            systemip = request.getRemoteAddr();
            // ca = ((Double) session.getAttribute("chronologicalage")).doubleValue();

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }

            loginid = (String) session.getAttribute("loginid");


            if (session.getAttribute("chronologicalageAU") != null) {
                ca = ((Double) session.getAttribute("chronologicalageAU")).doubleValue();
            } else {
                ca = ((Double) session.getAttribute("chronologicalage")).doubleValue();
            }


            month = mentalRetardationTestsForm.getDstmonth();
            year = mentalRetardationTestsForm.getDstyear();
            ma = convertToYears(year, month);
            if (ca < 15) {
                developmentalresult = calculateIQ(ma, ca);
            } else {
                ca = 15;
                developmentalresult = calculateIQ(ma, ca);
            }
            // session.setAttribute("developmentalresult", new Double(developmentalresult));
            boolean isPerosonCodeExist = mentalretarservice.checkForPersonCode(ds, personcode, "tblPerson_MR_DST_Test_Details");
            if (isPerosonCodeExist) {
                // if (session.getAttribute("sadaremCodeAu") != null) {
                //  mentalretarservice.insertDevelopmentalData(ds, personcode, year, month, developmentalresult, loginid, systemip,request);
                //   target = "success";
                //     }else {
                mentalretarservice.updateDevelopmentalData(ds, personcode, year, month, developmentalresult, loginid, systemip, request);
                target = "success";
                //     }
            } else {
                mentalretarservice.insertDevelopmentalData(ds, personcode, year, month, developmentalresult, loginid, systemip, request);
                target = "success";
            }
            if (!session.getAttribute("roleId").equals("5")) {
                MentalRetardationTestsDTO disableMentalItems = mentalretarservice.getMentalRetardationIQValues(ds, personcode);
                request.setAttribute("disableMentalItems", disableMentalItems);
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
     *
     * @ This method is use for Inserting the VinelandScreeningTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward insertVinelandTestScreening(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        DataSource ds = null;


        //DataSource ds= getDataSource(request);


        String target = "success";
        double vinelandresult = 0;
        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        String month;

        String year;
        String personcode, loginid, systemip;
        MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;
        MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
        double ca = 0;
        double ma = 0;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            // personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }

            loginid = (String) session.getAttribute("loginid");
            systemip = request.getRemoteAddr();

            if (session.getAttribute("chronologicalageAU") != null) {
                ca = ((Double) session.getAttribute("chronologicalageAU")).doubleValue();
            } else {
                ca = ((Double) session.getAttribute("chronologicalage")).doubleValue();
            }


            month = mentalRetardationTestsForm.getVsmsmonth();
            year = mentalRetardationTestsForm.getVsmsyear();

            ma = convertToYears(year, month);
            if (ca < 15) {
                vinelandresult = calculateIQ(ma, ca);
            } else {
                ca = 15;
                vinelandresult = calculateIQ(ma, ca);
            }
            //session.setAttribute("vinelandresult", new Double(vinelandresult));
            boolean isPerosonCodeExist = mentalretarservice.checkForPersonCode(ds, personcode, "tblPerson_MR_VSMS_Test_Details");

            if (isPerosonCodeExist) {
                if (session.getAttribute("sadaremCodeAu") != null) {
                    int i = mentalretarservice.insertVinelandDataAU(ds, personcode, year, month, vinelandresult, loginid, systemip, request);
                    if (i == -1) {
                        target = "failure";
                    } else {
                        target = "success";
                    }
                } else {
                    target = "failure";
                }

            } else {
                mentalretarservice.insertVinelandData(ds, personcode, year, month, vinelandresult, loginid, systemip, request);
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
     *
     * @ This method is use for Inserting the VinelandScreeningTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward insertVinelandTestScreeningAU(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        DataSource ds = null;


        //DataSource ds= getDataSource(request);


        String target = "success";
        double vinelandresult = 0;
        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        String month;

        String year;
        String personcode, loginid, systemip;
        MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;
        MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
        double ca = 0;
        double ma = 0;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            personcode = (String) session.getAttribute("sadaremCodeAu");
            loginid = (String) session.getAttribute("loginid");
            systemip = request.getRemoteAddr();

            ca = ((Double) session.getAttribute("chronologicalageAU")).doubleValue();
            month = mentalRetardationTestsForm.getVsmsmonth();
            year = mentalRetardationTestsForm.getVsmsyear();

            ma = convertToYears(year, month);
            if (ca < 15) {
                vinelandresult = calculateIQ(ma, ca);
            } else {
                ca = 15;
                vinelandresult = calculateIQ(ma, ca);
            }
            //session.setAttribute("vinelandresult", new Double(vinelandresult));
            boolean isPerosonCodeExist = mentalretarservice.checkForPersonCode(ds, personcode, "tblPerson_MR_VSMS_Test_Details");

            if (isPerosonCodeExist) {
                target = "failure";

            } else {
                mentalretarservice.insertVinelandDataAU(ds, personcode, year, month, vinelandresult, loginid, systemip, request);
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
     *
     * @ This method is use for getting the VinelandScreeningTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward getVinelandData(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        String personcode;

        DataSource ds = null;


        //DataSource ds= getDataSource(request);

        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;
        MentalRetardationTestsDTO mentalretardationtestdto = new MentalRetardationTestsDTO();
        MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            personcode = (String) session.getAttribute("personcode");
            mentalretardationtestdto = mentalretarservice.getVinelandData(ds, personcode);
            BeanUtils.copyProperties(mentalRetardationTestsForm, mentalretardationtestdto);
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
     *
     * @ This method is use for Updating the VinelandScreeningTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward updateVinelandTestScreening(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        DataSource ds = null;


        //DataSource ds= getDataSource(request);


        String target = "success";
        double vinelandresult = 0;
        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        String month;

        String year;
        String personcode, loginid, systemip;
        MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;
        MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
        double ca = 0;
        double ma = 0;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            // personcode = (String) session.getAttribute("personcode");
            loginid = (String) session.getAttribute("loginid");
            systemip = request.getRemoteAddr();

            //  ca = ((Double) session.getAttribute("chronologicalage")).doubleValue();


            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }

            loginid = (String) session.getAttribute("loginid");


            if (session.getAttribute("chronologicalageAU") != null) {
                ca = ((Double) session.getAttribute("chronologicalageAU")).doubleValue();
            } else {
                ca = ((Double) session.getAttribute("chronologicalage")).doubleValue();
            }




            month = mentalRetardationTestsForm.getVsmsmonth();
            year = mentalRetardationTestsForm.getVsmsyear();

            ma = convertToYears(year, month);
            if (ca < 15) {
                vinelandresult = calculateIQ(ma, ca);
            } else {
                ca = 15;
                vinelandresult = calculateIQ(ma, ca);
            }
            // session.setAttribute("vinelandresult", new Double(vinelandresult));
            boolean isPerosonCodeExist = mentalretarservice.checkForPersonCode(ds, personcode, "tblPerson_MR_VSMS_Test_Details");
            if (isPerosonCodeExist) {
                //   if (session.getAttribute("sadaremCodeAu") != null) {
                //        mentalretarservice.insertVinelandData(ds, personcode, year, month, vinelandresult, loginid, systemip,request);
                //   target = "success";
                //   }else {
                mentalretarservice.updateVinelandData(ds, personcode, year, month, vinelandresult, loginid, systemip, request);
                target = "success";
                //   }
            } else {
                mentalretarservice.insertVinelandData(ds, personcode, year, month, vinelandresult, loginid, systemip, request);
                target = "success";
            }
            if (!session.getAttribute("roleId").equals("5")) {
                MentalRetardationTestsDTO disableMentalItems = mentalretarservice.getMentalRetardationIQValues(ds, personcode);
                request.setAttribute("disableMentalItems", disableMentalItems);
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
     *
     * @ This method is use for Inserting the BinetKamatScreeningTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward insertBinetKamatTestScreening(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        DataSource ds = null;


        //DataSource ds= getDataSource(request);


        String target = "success";
        double binetkamalresult = 0;
        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        String month;

        String year;
        String personcode, loginid, systemip;
        MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;
        MentalRetardationTestsDTO mentalretardationtestdto = new MentalRetardationTestsDTO();
        MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
        double ca = 0;
        double ma = 0;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            // personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }

            loginid = (String) session.getAttribute("loginid");
            systemip = request.getRemoteAddr();
            if (session.getAttribute("chronologicalageAU") != null) {
                ca = ((Double) session.getAttribute("chronologicalageAU")).doubleValue();
            } else {
                ca = ((Double) session.getAttribute("chronologicalage")).doubleValue();
            }

            if (mentalRetardationTestsForm.getBictbasalage() == "") {
                mentalRetardationTestsForm.setBictbasalage("0");
            }
            if (mentalRetardationTestsForm.getBictterminalage() == "") {
                mentalRetardationTestsForm.setBictterminalage("0");
            }
            month = mentalRetardationTestsForm.getBictmonth();
            year = mentalRetardationTestsForm.getBictyear();
            ma = convertToYears(year, month);
            if (ca < 16) {
                binetkamalresult = calculateIQ(ma, ca);
            } else {
                ca = 16;
                binetkamalresult = calculateIQ(ma, ca);
            }
            //session.setAttribute("binetkamalresult", new Double(binetkamalresult));
            mentalRetardationTestsForm.setBictiq(binetkamalresult);
            mentalRetardationTestsForm.setSystemip(systemip);
            mentalRetardationTestsForm.setLoginid(loginid);
            mentalRetardationTestsForm.setPersoncode(personcode);
            BeanUtils.copyProperties(mentalretardationtestdto, mentalRetardationTestsForm);
            boolean isPerosonCodeExist = mentalretarservice.checkForPersonCode(ds, personcode, "tblPerson_MR_BKT_Test_Details");
            if (isPerosonCodeExist) {
                target = "failure";
            } else {
                mentalretarservice.insertBinetKamaldata(ds, mentalretardationtestdto, request);
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
     *
     * @ This method is use for Inserting the BinetKamatScreeningTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward insertBinetKamatTestScreeningAU(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        DataSource ds = null;


        //DataSource ds= getDataSource(request);


        String target = "success";
        double binetkamalresult = 0;
        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        String month;

        String year;
        String personcode, loginid, systemip;
        MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;
        MentalRetardationTestsDTO mentalretardationtestdto = new MentalRetardationTestsDTO();
        MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
        double ca = 0;
        double ma = 0;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            personcode = (String) session.getAttribute("sadaremCodeAu");
            loginid = (String) session.getAttribute("loginid");
            systemip = request.getRemoteAddr();
            ca = ((Double) session.getAttribute("chronologicalageAU")).doubleValue();
            if (mentalRetardationTestsForm.getBictbasalage() == "") {
                mentalRetardationTestsForm.setBictbasalage("0");
            }
            if (mentalRetardationTestsForm.getBictterminalage() == "") {
                mentalRetardationTestsForm.setBictterminalage("0");
            }
            month = mentalRetardationTestsForm.getBictmonth();
            year = mentalRetardationTestsForm.getBictyear();
            ma = convertToYears(year, month);
            if (ca < 16) {
                binetkamalresult = calculateIQ(ma, ca);
            } else {
                ca = 16;
                binetkamalresult = calculateIQ(ma, ca);
            }
            //session.setAttribute("binetkamalresult", new Double(binetkamalresult));
            mentalRetardationTestsForm.setBictiq(binetkamalresult);
            mentalRetardationTestsForm.setSystemip(systemip);
            mentalRetardationTestsForm.setLoginid(loginid);
            mentalRetardationTestsForm.setPersoncode(personcode);
            BeanUtils.copyProperties(mentalretardationtestdto, mentalRetardationTestsForm);
            boolean isPerosonCodeExist = mentalretarservice.checkForPersonCode(ds, personcode, "tblPerson_MR_BKT_Test_Details");
            if (isPerosonCodeExist) {
                target = "failure";
            } else {
                mentalretarservice.insertBinetKamaldataAU(ds, mentalretardationtestdto);
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
     *
     * @ This method is use for getting the BinetKamatScreeningTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward gettBinetKamaldata(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        String personcode;

        DataSource ds = null;


        //DataSource ds= getDataSource(request);



        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;
        MentalRetardationTestsDTO mentalretardationtestdto = new MentalRetardationTestsDTO();
        MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            personcode = (String) session.getAttribute("personcode");
            mentalretardationtestdto = mentalretarservice.gettBinetKamaldata(ds, personcode);
            BeanUtils.copyProperties(mentalRetardationTestsForm, mentalretardationtestdto);
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
     *
     * @ This method is use for Updating the BinetKamatScreeningTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward updateBinetKamatTestScreening(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        DataSource ds = null;


        //DataSource ds= getDataSource(request);

        String target = "success";
        double binetkamalresult = 0;
        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        String month;

        String year;
        String personcode, loginid, systemip;
        MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;
        MentalRetardationTestsDTO mentalretardationtestdto = new MentalRetardationTestsDTO();
        MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
        double ca = 0;
        double ma = 0;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            // personcode = (String) session.getAttribute("personcode");
            loginid = (String) session.getAttribute("loginid");
            systemip = request.getRemoteAddr();
            // ca = ((Double) session.getAttribute("chronologicalage")).doubleValue();


            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }

            loginid = (String) session.getAttribute("loginid");


            if (session.getAttribute("chronologicalageAU") != null) {
                ca = ((Double) session.getAttribute("chronologicalageAU")).doubleValue();
            } else {
                ca = ((Double) session.getAttribute("chronologicalage")).doubleValue();
            }


            if (mentalRetardationTestsForm.getBictbasalage() == "") {
                mentalRetardationTestsForm.setBictbasalage("0");
            }
            if (mentalRetardationTestsForm.getBictterminalage() == "") {
                mentalRetardationTestsForm.setBictterminalage("0");
            }
            month = mentalRetardationTestsForm.getBictmonth();
            year = mentalRetardationTestsForm.getBictyear();
            ma = convertToYears(year, month);
            if (ca < 16) {
                binetkamalresult = calculateIQ(ma, ca);
            } else {
                ca = 16;
                binetkamalresult = calculateIQ(ma, ca);
            }
            //session.setAttribute("binetkamalresult", new Double(binetkamalresult));
            mentalRetardationTestsForm.setBictiq(binetkamalresult);
            mentalRetardationTestsForm.setSystemip(systemip);
            mentalRetardationTestsForm.setLoginid(loginid);
            mentalRetardationTestsForm.setPersoncode(personcode);
            BeanUtils.copyProperties(mentalretardationtestdto, mentalRetardationTestsForm);
            boolean isPerosonCodeExist = mentalretarservice.checkForPersonCode(ds, personcode, "tblPerson_MR_BKT_Test_Details");
            if (isPerosonCodeExist) {
                //    if (session.getAttribute("sadaremCodeAu") != null) {
                //    mentalretarservice.insertBinetKamaldata(ds, mentalretardationtestdto,request);
                //    target = "success";
                //    }else{
                mentalretarservice.updateBinetKamaldata(ds, mentalretardationtestdto, request);
                target = "success";
                //     }
            } else {
                mentalretarservice.insertBinetKamaldata(ds, mentalretardationtestdto, request);
                target = "success";
            }
            if (!session.getAttribute("roleId").equals("5")) {
                MentalRetardationTestsDTO disableMentalItems = mentalretarservice.getMentalRetardationIQValues(ds, personcode);
                request.setAttribute("disableMentalItems", disableMentalItems);
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
     *
     * @ This method is use for Inserting the SeguinScreeningTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward MRseguintestdetailsinsert(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        int tot1;
        double sfbiq = 0.0;
        DataSource ds = null;

        String loginid = null;

        MentalRetardationTestsForm mentaltestform = (MentalRetardationTestsForm) form;
        //DataSource ds=getDataSource(request);


        HttpSession session = request.getSession();
        MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
        MentalRetardationTestsDTO mentalretardationtestdto = new MentalRetardationTestsDTO();
        double iqrange = 0;
        String personcode = null;
        String target = "success";
        double caage = 0;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            // personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }

            loginid = (String) session.getAttribute("loginid");




            if (session.getAttribute("chronologicalageAU") != null) {
                Double ca = ((Double) session.getAttribute("chronologicalageAU")).doubleValue();
                caage = ca.doubleValue();
            } else {
                Double ca = ((Double) session.getAttribute("chronologicalage")).doubleValue();
                caage = ca.doubleValue();
            }


            if (mentaltestform.getSfbyear() == "") {
                mentaltestform.setSfbyear(null);
            }
            if (mentaltestform.getSfbmonth() == "") {
                mentaltestform.setSfbmonth(null);
            }

            if (mentaltestform.getSfbtrialone() == "") {
                mentaltestform.setSfbtrialone(null);
            }
            if (mentaltestform.getSfbtrialtwo() == "") {
                mentaltestform.setSfbtrialtwo(null);
            }
            if (mentaltestform.getSfbtrialthree() == "") {
                mentaltestform.setSfbtrialthree(null);
            }
            String ageyears = mentaltestform.getSfbyear();
            String agemonths = mentaltestform.getSfbmonth();
            String trialone = mentaltestform.getSfbtrialone();
            String trialtwo = mentaltestform.getSfbtrialtwo();
            String trialthree = mentaltestform.getSfbtrialthree();
            double mentalage = convertToYears(ageyears, agemonths);


            if (caage < 15) {
                iqrange = calculateIQ(mentalage, caage);
            } else {
                caage = 15;
                iqrange = calculateIQ(mentalage, caage);
            }



            mentaltestform.setSfbiq(iqrange);
            sfbiq = mentaltestform.getSfbiq();


            mentaltestform.setPersoncode(personcode);
            mentaltestform.setLoginid(loginid);
            mentaltestform.setSystemip(request.getRemoteAddr());
            try {
                BeanUtils.copyProperties(mentalretardationtestdto, mentaltestform);

            } catch (InvocationTargetException ex) {

                ex.printStackTrace();
                //throw new SADAREMDBException();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
                //throw new SADAREMDBException();
            }

            //session.setAttribute("sfbiq", new Double(sfbiq));
            boolean isPerosonCodeExist = mentalretarservice.checkForPersonCode(ds, personcode, "tblPerson_MR_SFB_Test_Details");

            if (isPerosonCodeExist) {
                if (session.getAttribute("sadaremCodeAu") != null) {
                    tot1 = mentalretarservice.MRSfbinsertDataAU(ds, mentalretardationtestdto, request);
                    if (tot1 == -1) {
                        target = "failure";
                    } else {
                        target = "success";
                    }
                } else {
                    target = "failure";
                }

            } else {
                tot1 = mentalretarservice.MRSfbinsertData(ds, mentalretardationtestdto, request);
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
     *
     * @ This method is use for Inserting the SeguinScreeningTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward MRseguintestdetailsinsertAU(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        int tot1;
        double sfbiq = 0.0;
        DataSource ds = null;

        String loginid = null;

        MentalRetardationTestsForm mentaltestform = (MentalRetardationTestsForm) form;
        //DataSource ds=getDataSource(request);



        HttpSession session = request.getSession();
        MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
        MentalRetardationTestsDTO mentalretardationtestdto = new MentalRetardationTestsDTO();
        double iqrange = 0;
        String personcode = null;
        String target = "success";
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            personcode = (String) session.getAttribute("sadaremCodeAu");
            loginid = (String) session.getAttribute("loginid");
            Double ca = (Double) session.getAttribute("chronologicalage");
            double caage = ca.doubleValue();
            if (mentaltestform.getSfbyear() == "") {
                mentaltestform.setSfbyear(null);
            }
            if (mentaltestform.getSfbmonth() == "") {
                mentaltestform.setSfbmonth(null);
            }

            if (mentaltestform.getSfbtrialone() == "") {
                mentaltestform.setSfbtrialone(null);
            }
            if (mentaltestform.getSfbtrialtwo() == "") {
                mentaltestform.setSfbtrialtwo(null);
            }
            if (mentaltestform.getSfbtrialthree() == "") {
                mentaltestform.setSfbtrialthree(null);
            }
            String ageyears = mentaltestform.getSfbyear();
            String agemonths = mentaltestform.getSfbmonth();
            String trialone = mentaltestform.getSfbtrialone();
            String trialtwo = mentaltestform.getSfbtrialtwo();
            String trialthree = mentaltestform.getSfbtrialthree();
            double mentalage = convertToYears(ageyears, agemonths);


            if (caage < 15) {
                iqrange = calculateIQ(mentalage, caage);
            } else {
                caage = 15;
                iqrange = calculateIQ(mentalage, caage);
            }



            mentaltestform.setSfbiq(iqrange);
            sfbiq = mentaltestform.getSfbiq();


            mentaltestform.setPersoncode(personcode);
            mentaltestform.setLoginid(loginid);
            mentaltestform.setSystemip(request.getRemoteAddr());
            try {
                BeanUtils.copyProperties(mentalretardationtestdto, mentaltestform);

            } catch (InvocationTargetException ex) {

                ex.printStackTrace();
                //throw new SADAREMDBException();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
                //throw new SADAREMDBException();
            }

            //session.setAttribute("sfbiq", new Double(sfbiq));
            boolean isPerosonCodeExist = mentalretarservice.checkForPersonCode(ds, personcode, "tblPerson_MR_SFB_Test_Details");

            if (isPerosonCodeExist) {
                target = "failure";

            } else {
                tot1 = mentalretarservice.MRSfbinsertDataAU(ds, mentalretardationtestdto, request);
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
     *
     * @ This method is use for getting the SeguinScreeningTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward MRseguintestdetailsselect(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String personcode;

        DataSource datasource = null;
        String target = "success";

        //DataSource datasource=getDataSource(request);


        HttpSession session = request.getSession();

        MentalRetardationTestsForm mentaltestform = (MentalRetardationTestsForm) form;
        MentalRetardationTestsDTO mentalretardationtestdto = new MentalRetardationTestsDTO();
        MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            //   personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }


            mentalretardationtestdto = mentalretarservice.getMRseguintestDetails(datasource, personcode);

            try {
                BeanUtils.copyProperties(mentaltestform, mentalretardationtestdto);
            } catch (IllegalAccessException ex) {

                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
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
     *
     * @ This method is use for Updating the SeguinScreeningTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward updateMRseguintestdetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        int tot1;
        double sfbiq = 0.0;
        DataSource ds = null;

        String loginid = null;
        double iqrange = 0;
        String personcode = null;
        String target = "success";

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            boolean flag = isTokenValid(request, true);
            if (flag) {
                MentalRetardationTestsForm mentaltestform = (MentalRetardationTestsForm) form;
                MentalRetardationTestsDTO mentalretardationtestdto = new MentalRetardationTestsDTO();

                HttpSession session = request.getSession();
                MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
                //  personcode = (String) session.getAttribute("personcode");
                loginid = (String) session.getAttribute("loginid");
                // Double ca = (Double) session.getAttribute("chronologicalage");

                if (session.getAttribute("sadaremCodeAu") != null) {
                    personcode = (String) session.getAttribute("sadaremCodeAu");
                } else {
                    personcode = (String) session.getAttribute("personcode");
                }



                Double ca = 0.0;

                if (session.getAttribute("chronologicalageAU") != null) {
                    ca = ((Double) session.getAttribute("chronologicalageAU")).doubleValue();
                } else {
                    ca = ((Double) session.getAttribute("chronologicalage")).doubleValue();
                }


                double caage = ca.doubleValue();
                if ("".equals(mentaltestform.getSfbyear())) {
                    mentaltestform.setSfbyear(null);
                }
                if ("".equals(mentaltestform.getSfbmonth())) {
                    mentaltestform.setSfbmonth(null);
                }

                if ("".equals(mentaltestform.getSfbtrialone())) {
                    mentaltestform.setSfbtrialone(null);
                }
                if ("".equals(mentaltestform.getSfbtrialtwo())) {
                    mentaltestform.setSfbtrialtwo(null);
                }
                if ("".equals(mentaltestform.getSfbtrialthree())) {
                    mentaltestform.setSfbtrialthree(null);
                }

                double mentalage = convertToYears(mentaltestform.getSfbyear(), mentaltestform.getSfbmonth());

                if (caage < 15) {
                    iqrange = calculateIQ(mentalage, caage);
                } else {
                    caage = 15;
                    iqrange = calculateIQ(mentalage, caage);
                }

                iqrange = calculateIQ(mentalage, caage);
                mentaltestform.setSfbiq(iqrange);
                sfbiq = mentaltestform.getSfbiq();
                // session.setAttribute("sfbiq", new Double(sfbiq));
                mentaltestform.setSfbiq(iqrange);
                mentaltestform.setPersoncode(personcode);
                mentaltestform.setLoginid(loginid);
                mentaltestform.setSystemip(request.getRemoteAddr());
                BeanUtils.copyProperties(mentalretardationtestdto, mentaltestform);
                boolean isPerosonCodeExist = mentalretarservice.checkForPersonCode(ds, personcode, "tblPerson_MR_SFB_Test_Details");
                if (isPerosonCodeExist) {
                    //  if (session.getAttribute("sadaremCodeAu") != null) {
                    //     tot1 = mentalretarservice.MRSfbinsertData(ds, mentalretardationtestdto,request);
                    //  }else {
                    mentalretarservice.updateMRSfbDetails(ds, mentalretardationtestdto, request);
                    // }
                } else {
                    tot1 = mentalretarservice.MRSfbinsertData(ds, mentalretardationtestdto, request);
                }
                if (!session.getAttribute("roleId").equals("5")) {
                    MentalRetardationTestsDTO disableMentalItems = mentalretarservice.getMentalRetardationIQValues(ds, personcode);
                    request.setAttribute("disableMentalItems", disableMentalItems);
                }
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
     *
     * @ This method is use for Inserting the MalinsScreeningTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward MRmalinstestdetailsinsert(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

        double misiciq;
        int tot1;
        String personcode, loginid;
        DataSource ds = null;

        String target = "success";

        MentalRetardationTestsForm mentaltestform = (MentalRetardationTestsForm) form;
        //DataSource ds=getDataSource(request);



        HttpSession session = request.getSession();
        MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
        MentalRetardationTestsDTO mentalretardationtestdto = new MentalRetardationTestsDTO();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            // personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }

            loginid = (String) session.getAttribute("loginid");



            Double ca = 0.0;

            if (session.getAttribute("chronologicalageAU") != null) {
                ca = ((Double) session.getAttribute("chronologicalageAU")).doubleValue();
            } else {
                ca = ((Double) session.getAttribute("chronologicalage")).doubleValue();
            }

            double caage = ca.doubleValue();
            if (mentaltestform.getMisicinformationraw() == "") {
                mentaltestform.setMisicinformationraw("0");
            }
            if (mentaltestform.getMisicinformationtq() == "") {
                mentaltestform.setMisicinformationtq("0");
            }
            if (mentaltestform.getMisiccomprehensionrawscore() == "") {
                mentaltestform.setMisiccomprehensionrawscore("0");
            }
            if (mentaltestform.getMisiccomprehensiontq() == "") {
                mentaltestform.setMisiccomprehensiontq("0");
            }
            if (mentaltestform.getMisicarithmeticrawscore() == "") {
                mentaltestform.setMisicarithmeticrawscore("0");
            }
            if (mentaltestform.getMisicarithmetictq() == "") {
                mentaltestform.setMisicarithmetictq("0");
            }
            if (mentaltestform.getMisicsimilaritiesrawscore() == "") {
                mentaltestform.setMisicsimilaritiesrawscore("0");
            }
            if (mentaltestform.getMisicsimilaritiestq() == "") {
                mentaltestform.setMisicsimilaritiestq("0");
            }
            if (mentaltestform.getMisicvocabularyrawscore() == "") {
                mentaltestform.setMisicvocabularyrawscore("0");
            }
            if (mentaltestform.getMisicvocabularytq() == "") {
                mentaltestform.setMisicvocabularytq("0");
            }
            if (mentaltestform.getMisicdigitspanrawscore() == "") {
                mentaltestform.setMisicdigitspanrawscore("0");
            }
            if (mentaltestform.getMisicdigitspantq() == "") {
                mentaltestform.setMisicdigitspantq("0");
            }
            if (mentaltestform.getMisicpcrawscore() == "") {
                mentaltestform.setMisicpcrawscore("0");
            }
            if (mentaltestform.getMisicpctq() == "") {
                mentaltestform.setMisicpctq("0");
            }
            if (mentaltestform.getMisicbdrawscore() == "") {
                mentaltestform.setMisicbdrawscore("0");
            }
            if (mentaltestform.getMisicbdtq() == "") {
                mentaltestform.setMisicbdtq("0");
            }
            if (mentaltestform.getMisicoarawscore() == "") {
                mentaltestform.setMisicoarawscore("0");
            }
            if (mentaltestform.getMisicoatq() == "") {
                mentaltestform.setMisicoatq("0");
            }
            if (mentaltestform.getMisiccodingrawscore() == "") {
                mentaltestform.setMisiccodingrawscore("0");
            }
            if (mentaltestform.getMisiccodingtq() == "") {
                mentaltestform.setMisiccodingtq("0");
            }
            if (mentaltestform.getMisicmazesrawscore() == "") {
                mentaltestform.setMisicmazesrawscore("0");
            }
            if (mentaltestform.getMisicmazestq() == "") {
                mentaltestform.setMisicmazestq("0");
            }

            int misicinformation = Integer.parseInt(mentaltestform.getMisicinformationraw());
            int informationtq = Integer.parseInt(mentaltestform.getMisicinformationtq());
            int comprehensionscore = Integer.parseInt(mentaltestform.getMisiccomprehensionrawscore());
            int comprehensiontq = Integer.parseInt(mentaltestform.getMisiccomprehensiontq());
            int arithmeticscore = Integer.parseInt(mentaltestform.getMisicarithmeticrawscore());
            int arithmetictq = Integer.parseInt(mentaltestform.getMisicarithmetictq());
            int similartiesscore = Integer.parseInt(mentaltestform.getMisicsimilaritiesrawscore());
            int similartiestq = Integer.parseInt(mentaltestform.getMisicsimilaritiestq());

            int vocabularyrawscore = Integer.parseInt(mentaltestform.getMisicvocabularyrawscore());
            int vocabularytq = Integer.parseInt(mentaltestform.getMisicvocabularytq());

            int digitspanscore = Integer.parseInt(mentaltestform.getMisicdigitspanrawscore());
            int digitspantq = Integer.parseInt(mentaltestform.getMisicdigitspantq());

            int pcrawscore = Integer.parseInt(mentaltestform.getMisicpcrawscore());
            int pctq = Integer.parseInt(mentaltestform.getMisicpctq());
            int bdrawscore = Integer.parseInt(mentaltestform.getMisicbdrawscore());
            int bdtq = Integer.parseInt(mentaltestform.getMisicbdtq());
            int misicoascore = Integer.parseInt(mentaltestform.getMisicoarawscore());
            int misicoatq = Integer.parseInt(mentaltestform.getMisicoatq());
            int codingrawscore = Integer.parseInt(mentaltestform.getMisiccodingrawscore());
            int codingtq = Integer.parseInt(mentaltestform.getMisiccodingtq());
            int mazesrawscore = Integer.parseInt(mentaltestform.getMisicmazesrawscore());
            int mazestq = Integer.parseInt(mentaltestform.getMisicmazestq());
            double avergeverbalquotient = (informationtq + comprehensiontq + arithmetictq + similartiestq + vocabularytq + digitspantq) / 5;
            double avergeperformancequotient = (pctq + bdtq + misicoatq + codingtq + mazestq) / 5;

            double iqrangeformisic = (avergeverbalquotient + avergeperformancequotient) / 2;
            mentaltestform.setMisiciq(iqrangeformisic);
            misiciq = mentaltestform.getMisiciq();

            mentaltestform.setMisiciq(iqrangeformisic);
            mentaltestform.setPersoncode(personcode);
            mentaltestform.setLoginid(loginid);
            mentaltestform.setSystemip(request.getRemoteAddr());
            try {
                BeanUtils.copyProperties(mentalretardationtestdto, mentaltestform);

            } catch (InvocationTargetException ex) {

                ex.printStackTrace();
                //throw new SADAREMDBException();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();

                //throw new SADAREMDBException();

            }
            // session.setAttribute("misiciq", new Double(misiciq));
            boolean isPerosonCodeExist = mentalretarservice.checkForPersonCode(ds, personcode, "tblPerson_MR_MISIC_Test_Details");

            if (isPerosonCodeExist) {
                if (session.getAttribute("sadaremCodeAu") != null) {
                    if (misiciq > 0) {
                        tot1 = mentalretarservice.MRMalinsinsertDataAU(ds, mentalretardationtestdto, request);
                        if (tot1 == -1) {
                            target = "failure";
                        } else {
                            target = "success";
                        }
                    } else {
                        target = "success";
                    }
                } else {
                    target = "failure";
                }

            } else if (misiciq > 0) {
                tot1 = mentalretarservice.MRMalinsinsertData(ds, mentalretardationtestdto, request);
                target = "success";
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
     *
     * @ This method is use for Inserting the MalinsScreeningTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward MRmalinstestdetailsinsertAU(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

        double misiciq;
        int tot1;
        String personcode, loginid;
        DataSource ds = null;

        String target = "success";

        MentalRetardationTestsForm mentaltestform = (MentalRetardationTestsForm) form;
        //DataSource ds=getDataSource(request);



        HttpSession session = request.getSession();
        MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
        MentalRetardationTestsDTO mentalretardationtestdto = new MentalRetardationTestsDTO();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            personcode = (String) session.getAttribute("sadaremCodeAu");
            loginid = (String) session.getAttribute("loginid");
            Double ca = (Double) session.getAttribute("chronologicalageAU");
            double caage = ca.doubleValue();
            if (mentaltestform.getMisicinformationraw() == "") {
                mentaltestform.setMisicinformationraw("0");
            }
            if (mentaltestform.getMisicinformationtq() == "") {
                mentaltestform.setMisicinformationtq("0");
            }
            if (mentaltestform.getMisiccomprehensionrawscore() == "") {
                mentaltestform.setMisiccomprehensionrawscore("0");
            }
            if (mentaltestform.getMisiccomprehensiontq() == "") {
                mentaltestform.setMisiccomprehensiontq("0");
            }
            if (mentaltestform.getMisicarithmeticrawscore() == "") {
                mentaltestform.setMisicarithmeticrawscore("0");
            }
            if (mentaltestform.getMisicarithmetictq() == "") {
                mentaltestform.setMisicarithmetictq("0");
            }
            if (mentaltestform.getMisicsimilaritiesrawscore() == "") {
                mentaltestform.setMisicsimilaritiesrawscore("0");
            }
            if (mentaltestform.getMisicsimilaritiestq() == "") {
                mentaltestform.setMisicsimilaritiestq("0");
            }
            if (mentaltestform.getMisicvocabularyrawscore() == "") {
                mentaltestform.setMisicvocabularyrawscore("0");
            }
            if (mentaltestform.getMisicvocabularytq() == "") {
                mentaltestform.setMisicvocabularytq("0");
            }
            if (mentaltestform.getMisicdigitspanrawscore() == "") {
                mentaltestform.setMisicdigitspanrawscore("0");
            }
            if (mentaltestform.getMisicdigitspantq() == "") {
                mentaltestform.setMisicdigitspantq("0");
            }
            if (mentaltestform.getMisicpcrawscore() == "") {
                mentaltestform.setMisicpcrawscore("0");
            }
            if (mentaltestform.getMisicpctq() == "") {
                mentaltestform.setMisicpctq("0");
            }
            if (mentaltestform.getMisicbdrawscore() == "") {
                mentaltestform.setMisicbdrawscore("0");
            }
            if (mentaltestform.getMisicbdtq() == "") {
                mentaltestform.setMisicbdtq("0");
            }
            if (mentaltestform.getMisicoarawscore() == "") {
                mentaltestform.setMisicoarawscore("0");
            }
            if (mentaltestform.getMisicoatq() == "") {
                mentaltestform.setMisicoatq("0");
            }
            if (mentaltestform.getMisiccodingrawscore() == "") {
                mentaltestform.setMisiccodingrawscore("0");
            }
            if (mentaltestform.getMisiccodingtq() == "") {
                mentaltestform.setMisiccodingtq("0");
            }
            if (mentaltestform.getMisicmazesrawscore() == "") {
                mentaltestform.setMisicmazesrawscore("0");
            }
            if (mentaltestform.getMisicmazestq() == "") {
                mentaltestform.setMisicmazestq("0");
            }

            int misicinformation = Integer.parseInt(mentaltestform.getMisicinformationraw());
            int informationtq = Integer.parseInt(mentaltestform.getMisicinformationtq());
            int comprehensionscore = Integer.parseInt(mentaltestform.getMisiccomprehensionrawscore());
            int comprehensiontq = Integer.parseInt(mentaltestform.getMisiccomprehensiontq());
            int arithmeticscore = Integer.parseInt(mentaltestform.getMisicarithmeticrawscore());
            int arithmetictq = Integer.parseInt(mentaltestform.getMisicarithmetictq());
            int similartiesscore = Integer.parseInt(mentaltestform.getMisicsimilaritiesrawscore());
            int similartiestq = Integer.parseInt(mentaltestform.getMisicsimilaritiestq());

            int vocabularyrawscore = Integer.parseInt(mentaltestform.getMisicvocabularyrawscore());
            int vocabularytq = Integer.parseInt(mentaltestform.getMisicvocabularytq());

            int digitspanscore = Integer.parseInt(mentaltestform.getMisicdigitspanrawscore());
            int digitspantq = Integer.parseInt(mentaltestform.getMisicdigitspantq());

            int pcrawscore = Integer.parseInt(mentaltestform.getMisicpcrawscore());
            int pctq = Integer.parseInt(mentaltestform.getMisicpctq());
            int bdrawscore = Integer.parseInt(mentaltestform.getMisicbdrawscore());
            int bdtq = Integer.parseInt(mentaltestform.getMisicbdtq());
            int misicoascore = Integer.parseInt(mentaltestform.getMisicoarawscore());
            int misicoatq = Integer.parseInt(mentaltestform.getMisicoatq());
            int codingrawscore = Integer.parseInt(mentaltestform.getMisiccodingrawscore());
            int codingtq = Integer.parseInt(mentaltestform.getMisiccodingtq());
            int mazesrawscore = Integer.parseInt(mentaltestform.getMisicmazesrawscore());
            int mazestq = Integer.parseInt(mentaltestform.getMisicmazestq());
            double avergeverbalquotient = (informationtq + comprehensiontq + arithmetictq + similartiestq + vocabularytq + digitspantq) / 5;
            double avergeperformancequotient = (pctq + bdtq + misicoatq + codingtq + mazestq) / 5;

            double iqrangeformisic = (avergeverbalquotient + avergeperformancequotient) / 2;
            mentaltestform.setMisiciq(iqrangeformisic);
            misiciq = mentaltestform.getMisiciq();

            mentaltestform.setMisiciq(iqrangeformisic);
            mentaltestform.setPersoncode(personcode);
            mentaltestform.setLoginid(loginid);
            mentaltestform.setSystemip(request.getRemoteAddr());
            try {
                BeanUtils.copyProperties(mentalretardationtestdto, mentaltestform);

            } catch (InvocationTargetException ex) {

                ex.printStackTrace();
                //throw new SADAREMDBException();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();

                //throw new SADAREMDBException();

            }
            // session.setAttribute("misiciq", new Double(misiciq));
            boolean isPerosonCodeExist = mentalretarservice.checkForPersonCode(ds, personcode, "tblPerson_MR_MISIC_Test_Details");

            if (isPerosonCodeExist) {
                target = "failure";

            } else if (misiciq > 0) {
                tot1 = mentalretarservice.MRMalinsinsertDataAU(ds, mentalretardationtestdto, request);
                target = "success";
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
     *
     * @ This method is use for getting the MalinsScreeningTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward MRmalinstestdetailsselect(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String personcode;

        DataSource datasource = null;
        String target = "success";

        //DataSource datasource=getDataSource(request);

        HttpSession session = request.getSession(true);

        MentalRetardationTestsForm mentaltestform = (MentalRetardationTestsForm) form;
        MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
        MentalRetardationTestsDTO mentalretardationtestdto = new MentalRetardationTestsDTO();
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            // personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }


            mentalretardationtestdto = mentalretarservice.getMRmalinstestDetails(datasource, personcode);

            try {
                BeanUtils.copyProperties(mentaltestform, mentalretardationtestdto);
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
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
     *
     * @ This method is use for Updating the MalinsScreeningTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward MRmalinstestdetailsupdate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

        double misiciq;
        int tot1;
        String personcode, loginid;
        DataSource ds = null;

        String target = "success";
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            boolean flag = isTokenValid(request, true);
            if (flag) {
                MentalRetardationTestsForm mentaltestform = (MentalRetardationTestsForm) form;

                HttpSession session = request.getSession();
                MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
                MentalRetardationTestsDTO mentalretardationtestdto = new MentalRetardationTestsDTO();
                // personcode = (String) session.getAttribute("personcode");
                loginid = (String) session.getAttribute("loginid");
                // Double ca = (Double) session.getAttribute("chronologicalage");

                if (session.getAttribute("sadaremCodeAu") != null) {
                    personcode = (String) session.getAttribute("sadaremCodeAu");
                } else {
                    personcode = (String) session.getAttribute("personcode");
                }

                loginid = (String) session.getAttribute("loginid");



                Double ca = 0.0;

                if (session.getAttribute("chronologicalageAU") != null) {
                    ca = ((Double) session.getAttribute("chronologicalageAU")).doubleValue();
                } else {
                    ca = ((Double) session.getAttribute("chronologicalage")).doubleValue();
                }



                double caage = ca.doubleValue();
                if ("".equals(mentaltestform.getMisicinformationraw())) {
                    mentaltestform.setMisicinformationraw("0");
                }
                if ("".equals(mentaltestform.getMisicinformationtq())) {
                    mentaltestform.setMisicinformationtq("0");
                }
                if ("".equals(mentaltestform.getMisiccomprehensionrawscore())) {
                    mentaltestform.setMisiccomprehensionrawscore("0");
                }
                if ("".equals(mentaltestform.getMisiccomprehensiontq())) {
                    mentaltestform.setMisiccomprehensiontq("0");
                }
                if ("".equals(mentaltestform.getMisicarithmeticrawscore())) {
                    mentaltestform.setMisicarithmeticrawscore("0");
                }
                if ("".equals(mentaltestform.getMisicarithmetictq())) {
                    mentaltestform.setMisicarithmetictq("0");
                }
                if ("".equals(mentaltestform.getMisicsimilaritiesrawscore())) {
                    mentaltestform.setMisicsimilaritiesrawscore("0");
                }
                if ("".equals(mentaltestform.getMisicsimilaritiestq())) {
                    mentaltestform.setMisicsimilaritiestq("0");
                }
                if ("".equals(mentaltestform.getMisicvocabularyrawscore())) {
                    mentaltestform.setMisicvocabularyrawscore("0");
                }
                if ("".equals(mentaltestform.getMisicvocabularytq())) {
                    mentaltestform.setMisicvocabularytq("0");
                }
                if ("".equals(mentaltestform.getMisicdigitspanrawscore())) {
                    mentaltestform.setMisicdigitspanrawscore("0");
                }
                if ("".equals(mentaltestform.getMisicdigitspantq())) {
                    mentaltestform.setMisicdigitspantq("0");
                }
                if ("".equals(mentaltestform.getMisicpcrawscore())) {
                    mentaltestform.setMisicpcrawscore("0");
                }
                if ("".equals(mentaltestform.getMisicpctq())) {
                    mentaltestform.setMisicpctq("0");
                }
                if ("".equals(mentaltestform.getMisicbdrawscore())) {
                    mentaltestform.setMisicbdrawscore("0");
                }
                if ("".equals(mentaltestform.getMisicbdtq())) {
                    mentaltestform.setMisicbdtq("0");
                }
                if ("".equals(mentaltestform.getMisicoarawscore())) {
                    mentaltestform.setMisicoarawscore("0");
                }
                if ("".equals(mentaltestform.getMisicoatq())) {
                    mentaltestform.setMisicoatq("0");
                }
                if ("".equals(mentaltestform.getMisiccodingrawscore())) {
                    mentaltestform.setMisiccodingrawscore("0");
                }
                if ("".equals(mentaltestform.getMisiccodingtq())) {
                    mentaltestform.setMisiccodingtq("0");
                }
                if ("".equals(mentaltestform.getMisicmazesrawscore())) {
                    mentaltestform.setMisicmazesrawscore("0");
                }
                if ("".equals(mentaltestform.getMisicmazestq())) {
                    mentaltestform.setMisicmazestq("0");
                }
                int misicinformation = Integer.parseInt(mentaltestform.getMisicinformationraw());
                int informationtq = Integer.parseInt(mentaltestform.getMisicinformationtq());
                int comprehensionscore = Integer.parseInt(mentaltestform.getMisiccomprehensionrawscore());
                int comprehensiontq = Integer.parseInt(mentaltestform.getMisiccomprehensiontq());
                int arithmeticscore = Integer.parseInt(mentaltestform.getMisicarithmeticrawscore());
                int arithmetictq = Integer.parseInt(mentaltestform.getMisicarithmetictq());
                int similartiesscore = Integer.parseInt(mentaltestform.getMisicsimilaritiesrawscore());
                int similartiestq = Integer.parseInt(mentaltestform.getMisicsimilaritiestq());

                int vocabularyrawscore = Integer.parseInt(mentaltestform.getMisicvocabularyrawscore());
                int vocabularytq = Integer.parseInt(mentaltestform.getMisicvocabularytq());

                int digitspanscore = Integer.parseInt(mentaltestform.getMisicdigitspanrawscore());
                int digitspantq = Integer.parseInt(mentaltestform.getMisicdigitspantq());

                int pcrawscore = Integer.parseInt(mentaltestform.getMisicpcrawscore());
                int pctq = Integer.parseInt(mentaltestform.getMisicpctq());
                int bdrawscore = Integer.parseInt(mentaltestform.getMisicbdrawscore());
                int bdtq = Integer.parseInt(mentaltestform.getMisicbdtq());
                int misicoascore = Integer.parseInt(mentaltestform.getMisicoarawscore());
                int misicoatq = Integer.parseInt(mentaltestform.getMisicoatq());
                int codingrawscore = Integer.parseInt(mentaltestform.getMisiccodingrawscore());
                int codingtq = Integer.parseInt(mentaltestform.getMisiccodingtq());
                int mazesrawscore = Integer.parseInt(mentaltestform.getMisicmazesrawscore());
                int mazestq = Integer.parseInt(mentaltestform.getMisicmazestq());
                double avergeverbalquotient = (informationtq + comprehensiontq + arithmetictq + similartiestq + vocabularytq + digitspantq) / 5;
                double avergeperformancequotient = (pctq + bdtq + misicoatq + codingtq + mazestq) / 5;

                double iqrangeformisic = (avergeverbalquotient + avergeperformancequotient) / 2;
                mentaltestform.setMisiciq(iqrangeformisic);
                misiciq = mentaltestform.getMisiciq();
                //session.setAttribute("misiciq", new Double(misiciq));
                mentaltestform.setMisiciq(iqrangeformisic);
                mentaltestform.setPersoncode(personcode);
                mentaltestform.setLoginid(loginid);
                mentaltestform.setSystemip(request.getRemoteAddr());
                BeanUtils.copyProperties(mentalretardationtestdto, mentaltestform);
                boolean isPerosonCodeExist = mentalretarservice.checkForPersonCode(ds, personcode, "tblPerson_MR_MISIC_Test_Details");
                if (isPerosonCodeExist) {
                    //      if (session.getAttribute("sadaremCodeAu") != null) {
                    //      tot1 = mentalretarservice.MRMalinsinsertData(ds, mentalretardationtestdto,request);
                    //      }else {
                    mentalretarservice.updateMalinstestDetails(ds, mentalretardationtestdto, request);
                    //       }

                } else {
                    tot1 = mentalretarservice.MRMalinsinsertData(ds, mentalretardationtestdto, request);
                }
                if (!session.getAttribute("roleId").equals("5")) {
                    MentalRetardationTestsDTO disableMentalItems = mentalretarservice.getMentalRetardationIQValues(ds, personcode);
                    request.setAttribute("disableMentalItems", disableMentalItems);
                }

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
     *
     * @ This method is use for getting the IOvalues.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward getMentalRetardationIQValues(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        DataSource ds = null;

        String personcode;
        //DataSource ds= getDataSource(request);
        ActionMessages actionMessages = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            MentalRetardationDTO mentalretardationdto = new MentalRetardationDTO();
            MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;
            MentalRetardationTestsDTO mentalretardationtestdto = new MentalRetardationTestsDTO();
            MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
            //   personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }


            mentalretardationdto = mentalretarservice.getTestDetails(ds, personcode);
            mentalretardationtestdto = mentalretarservice.getMentalRetardationIQValues(ds, personcode);
            request.setAttribute("sfbiq", new Double(mentalretardationtestdto.getSfbiq()));
            request.setAttribute("misiciq", new Double(mentalretardationtestdto.getMisiciq()));
            request.setAttribute("developmentalresult", new Double(mentalretardationtestdto.getDstdq()));
            request.setAttribute("vinelandresult", new Double(mentalretardationtestdto.getVsmsvq()));
            request.setAttribute("binetkamalresult", new Double(mentalretardationtestdto.getBictiq()));
            request.setAttribute("pat", new Double(mentalretardationtestdto.getPat_IQ()));
            request.setAttribute("bhatia", new Double(mentalretardationtestdto.getBbpti_IQ()));
            request.setAttribute("iqvalue", mentalretardationdto.getMentalage());
            request.setAttribute("iqtestname", mentalretardationdto.getChronologicalage());
            if (!session.getAttribute("roleId").equals("5")) {
                MentalRetardationTestsDTO disableMentalItems = mentalretarservice.getMentalRetardationIQValues(ds, personcode);
                request.setAttribute("disableMentalItems", disableMentalItems);
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
     *
     * @ This method is use for getting the IOvalues.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward getMentalRetardationIQValuesAU(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        DataSource ds = null;

        String personcode;
        //DataSource ds= getDataSource(request);
        ActionMessages actionMessages = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            HttpSession session = request.getSession();
            MentalRetardationDTO mentalretardationdto = new MentalRetardationDTO();
            MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;
            MentalRetardationTestsDTO mentalretardationtestdto = new MentalRetardationTestsDTO();
            MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
            personcode = (String) session.getAttribute("sadaremCodeAu");
            mentalretardationdto = mentalretarservice.getTestDetails(ds, personcode);
            mentalretardationtestdto = mentalretarservice.getMentalRetardationIQValues(ds, personcode);
            request.setAttribute("sfbiq", new Double(mentalretardationtestdto.getSfbiq()));
            request.setAttribute("misiciq", new Double(mentalretardationtestdto.getMisiciq()));
            request.setAttribute("developmentalresult", new Double(mentalretardationtestdto.getDstdq()));
            request.setAttribute("vinelandresult", new Double(mentalretardationtestdto.getVsmsvq()));
            request.setAttribute("binetkamalresult", new Double(mentalretardationtestdto.getBictiq()));
            request.setAttribute("pat", new Double(mentalretardationtestdto.getPat_IQ()));
            request.setAttribute("bhatia", new Double(mentalretardationtestdto.getBbpti_IQ()));
            request.setAttribute("iqvalue", mentalretardationdto.getMentalage());
            request.setAttribute("iqtestname", mentalretardationdto.getChronologicalage());
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
     *
     * @ This method is use for Inserting the BhatiaScreeningTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward insertBhatiaTestDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = "0";
        DataSource ds = null;


        MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;
        HttpSession session = request.getSession(true);
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String personcode = null;

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }


            String loginID = (String) session.getAttribute("loginid");
            String sysgtemIP = request.getRemoteAddr();
            //DataSource ds=getDataSource(request);

            MentalRetardationTestsDTO mentalRetardationTestsDTO = new MentalRetardationTestsDTO();
            mentalRetardationTestsForm.setLoginID(loginID);
            mentalRetardationTestsForm.setSystemIP(sysgtemIP);
            mentalRetardationTestsForm.setPersoncode(personcode);

            if (mentalRetardationTestsForm.getBbpti_KOHS_Block_Design_Test() == "") {
                mentalRetardationTestsForm.setBbpti_KOHS_Block_Design_Test("0");
            }
            if (mentalRetardationTestsForm.getBbpti_Passalong_Test() == "") {
                mentalRetardationTestsForm.setBbpti_Passalong_Test("0");
            }
            if (mentalRetardationTestsForm.getBbpti_PatternDrawing_Test() == "") {
                mentalRetardationTestsForm.setBbpti_PatternDrawing_Test("0");
            }
            if (mentalRetardationTestsForm.getBbpti_ImmediateMemory_Test() == "") {
                mentalRetardationTestsForm.setBbpti_ImmediateMemory_Test("0");
            }
            if (mentalRetardationTestsForm.getBbpti_PictureConstruction_Test() == "") {
                mentalRetardationTestsForm.setBbpti_PictureConstruction_Test("0");
            }



            BeanUtils.copyProperties(mentalRetardationTestsDTO, mentalRetardationTestsForm);
            MentalRetardationService mentalRetardationService = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();


            boolean isPerosonCodeExist = mentalRetardationService.checkForPersonCode(ds, personcode, "tblPerson_MR_BBPTI_Test_Details");

            if (isPerosonCodeExist) {
                if (session.getAttribute("sadaremCodeAu") != null) {
                    int i = mentalRetardationService.insertBhatiaTestDetailsAU(ds, personcode, mentalRetardationTestsDTO, request);
                    if (i == -1) {
                        target = "failure";
                    } else {
                        target = "success";
                    }
                } else {
                    target = "failure";
                }

            } else {
                mentalRetardationService.insertBhatiaTestDetails(ds, personcode, mentalRetardationTestsDTO, request);
                target = "success";
            }

            //session.setAttribute("bhatia", new Double(mentalRetardationTestsForm.getBbpti_IQ()));

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
     *
     * @ This method is use for Inserting the BhatiaScreeningTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward insertBhatiaTestDetailsAU(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = "0";
        DataSource ds = null;


        MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;
        HttpSession session = request.getSession(true);
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String personcode = (String) session.getAttribute("sadaremCodeAu");
            String loginID = (String) session.getAttribute("loginid");
            String sysgtemIP = request.getRemoteAddr();
            //DataSource ds=getDataSource(request);


            MentalRetardationTestsDTO mentalRetardationTestsDTO = new MentalRetardationTestsDTO();
            mentalRetardationTestsForm.setLoginID(loginID);
            mentalRetardationTestsForm.setSystemIP(sysgtemIP);
            mentalRetardationTestsForm.setPersoncode(personcode);

            if (mentalRetardationTestsForm.getBbpti_KOHS_Block_Design_Test() == "") {
                mentalRetardationTestsForm.setBbpti_KOHS_Block_Design_Test("0");
            }
            if (mentalRetardationTestsForm.getBbpti_Passalong_Test() == "") {
                mentalRetardationTestsForm.setBbpti_Passalong_Test("0");
            }
            if (mentalRetardationTestsForm.getBbpti_PatternDrawing_Test() == "") {
                mentalRetardationTestsForm.setBbpti_PatternDrawing_Test("0");
            }
            if (mentalRetardationTestsForm.getBbpti_ImmediateMemory_Test() == "") {
                mentalRetardationTestsForm.setBbpti_ImmediateMemory_Test("0");
            }
            if (mentalRetardationTestsForm.getBbpti_PictureConstruction_Test() == "") {
                mentalRetardationTestsForm.setBbpti_PictureConstruction_Test("0");
            }



            BeanUtils.copyProperties(mentalRetardationTestsDTO, mentalRetardationTestsForm);
            MentalRetardationService mentalRetardationService = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();


            boolean isPerosonCodeExist = mentalRetardationService.checkForPersonCode(ds, personcode, "tblPerson_MR_BBPTI_Test_Details");

            if (isPerosonCodeExist) {
                target = "failure";

            } else {
                mentalRetardationService.insertBhatiaTestDetailsAU(ds, personcode, mentalRetardationTestsDTO, request);
                target = "success";
            }

            //session.setAttribute("bhatia", new Double(mentalRetardationTestsForm.getBbpti_IQ()));

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
     *
     * @ This method is use for getting the BhatiaScreeningTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward getBhatiaTestDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

        String target = null;
        DataSource ds = null;

        HttpSession session = request.getSession(true);


        MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            //DataSource ds=getDataSource(request);

            String personcode = null;//(String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }


            MentalRetardationService mentalRetardationService = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
            MentalRetardationTestsDTO mentalRetardationTestsDTO = mentalRetardationService.getBhatiaTestDetails(ds, personcode);
            BeanUtils.copyProperties(mentalRetardationTestsForm, mentalRetardationTestsDTO);
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
     *
     * @ This method is use for Updating the BhatiaScreeningTestDetails.
     * @param form MentalRetardationTestsForm
     * @param mapping ActionMapping
     * @throws java.lang.SADAREMException
     */
    public ActionForward updateBhatiaTestDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = null;
        DataSource ds = null;


        MentalRetardationTestsForm mentalRetardationTestsForm = (MentalRetardationTestsForm) form;
        HttpSession session = request.getSession(true);
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            boolean flag = isTokenValid(request, true);
            if (flag) {
                String personcode = null;//(String) session.getAttribute("personcode");
                String loginID = (String) session.getAttribute("loginid");
                String sysgtemIP = request.getRemoteAddr();
                //DataSource ds=getDataSource(request);

                if (session.getAttribute("sadaremCodeAu") != null) {
                    personcode = (String) session.getAttribute("sadaremCodeAu");
                } else {
                    personcode = (String) session.getAttribute("personcode");
                }
                MentalRetardationTestsDTO mentalRetardationTestsDTO = new MentalRetardationTestsDTO();
                mentalRetardationTestsForm.setLoginID(loginID);
                mentalRetardationTestsForm.setSystemIP(sysgtemIP);
                mentalRetardationTestsForm.setPersoncode(personcode);
                if ("".equals(mentalRetardationTestsForm.getBbpti_KOHS_Block_Design_Test())) {
                    mentalRetardationTestsForm.setBbpti_KOHS_Block_Design_Test("0");
                }
                if ("".equals(mentalRetardationTestsForm.getBbpti_Passalong_Test())) {
                    mentalRetardationTestsForm.setBbpti_Passalong_Test("0");
                }
                if ("".equals(mentalRetardationTestsForm.getBbpti_PatternDrawing_Test())) {
                    mentalRetardationTestsForm.setBbpti_PatternDrawing_Test("0");
                }
                if ("".equals(mentalRetardationTestsForm.getBbpti_ImmediateMemory_Test())) {
                    mentalRetardationTestsForm.setBbpti_ImmediateMemory_Test("0");
                }
                if ("".equals(mentalRetardationTestsForm.getBbpti_PictureConstruction_Test())) {
                    mentalRetardationTestsForm.setBbpti_PictureConstruction_Test("0");
                }

                BeanUtils.copyProperties(mentalRetardationTestsDTO, mentalRetardationTestsForm);
                MentalRetardationService mentalRetardationService = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();

                // modified by ganesh

                boolean isPerosonCodeExist = mentalRetardationService.checkForPersonCode(ds, personcode, "tblPerson_MR_BBPTI_Test_Details");
                if (isPerosonCodeExist) {
                    //   if(session.getAttribute("sadaremCodeAu")!=null) {
                    //        mentalRetardationService.insertBhatiaTestDetails(ds, personcode, mentalRetardationTestsDTO,request);
                    //   }else {
                    mentalRetardationService.updateBhatiaTestDetails(ds, personcode, mentalRetardationTestsDTO, request);
                    //    }
                } else {
                    mentalRetardationService.insertBhatiaTestDetails(ds, personcode, mentalRetardationTestsDTO, request);
                }

                // end of modification
                // session.setAttribute("bhatia", new Double(mentalRetardationTestsForm.getBbpti_IQ()));
                if (!session.getAttribute("roleId").equals("5")) {
                    MentalRetardationTestsDTO disableMentalItems = mentalRetardationService.getMentalRetardationIQValues(ds, personcode);
                    request.setAttribute("disableMentalItems", disableMentalItems);

                }



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

    public double convertToYears(String ageinyears, String ageinmonths) {
        double years = Double.parseDouble(ageinyears) + (Double.parseDouble(ageinmonths) / 12);
        return years;
    }

    public double calculateIQ(double ma, double ca) {
        double iq = (ma / ca) * 100;
        return iq;
    }
}
