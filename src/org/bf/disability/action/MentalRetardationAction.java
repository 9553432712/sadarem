
/*
 * MentalRetardationAction.java
 *
 * Created on July 16, 2008, 12:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.MentalRetardationActionForm;
import org.bf.disability.service.MentalRetardationService;
import org.bf.disability.dto.MentalRetardationDTO;
import org.bf.disability.servicefactory.MentalRetardationServiceFactory;
import org.bf.disability.dto.MentalRetardationTestsDTO;

/**
 * Class for Inserting ,Selecting and Updating the Mental Retardation Details
 * @author Sunima
 */
public class MentalRetardationAction extends BaseDispatchAction {

    ActionMessages actionMessages = null;

    /**
     * This method is used for Inserting the percentages of  Mental Retardation.
     * @param mapping mapping action
     * @param form MentalRetardationActionForm
     * @throws java.io.IOException
     * @throws java.lang.Exception
     */
    public ActionForward insertMentalRetardation(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

        //MentalRetardationActionForm mentalform=(MentalRetardationActionForm)form;
        //DataSource ds=getDataSource(request);
        DataSource ds = null;

        String personcode = null;
        double percent = 0;
        int tot1 = 0;
        String loginid = null;
        String target = "success";


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
            MentalRetardationDTO modifydto = new MentalRetardationDTO();
            //  personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }

            loginid = (String) session.getAttribute("loginid");
            boolean personcodecheckflag = mentalretarservice.checkPersoncode(personcode, ds);

            if (personcodecheckflag == false) {
                MentalRetardationActionForm mentalform = insertAndUpdateCalculationMental(form, request);
                if (mentalform.getTotalpercent() >= 0 && !"".equals(mentalform.getIq())
                        && null != mentalform.getIq()) {
                    if (Double.parseDouble(mentalform.getIq()) > 0) {
                        percent = mentalform.getTotalpercent();
                        mentalform.setPersoncode(personcode);
                        mentalform.setLoginid(loginid);
                        mentalform.setSystemip(request.getRemoteAddr());
                        try {
                            BeanUtils.copyProperties(modifydto, mentalform);
                        } catch (InvocationTargetException ex) {
                            ex.printStackTrace();
                            //throw new SADAREMDBException();
                        } catch (IllegalAccessException ex) {
                            ex.printStackTrace();
                            //throw new SADAREMDBException();
                        }


                        // tot1 = mentalretarservice.insertData(ds, modifydto,request);

                        tot1 = mentalretarservice.insertData(ds, modifydto, request);



                        request.setAttribute("msg", "MentalRetardation Added Successfully");
                        target = "success";
                        session.setAttribute("mentalretardation", new Double(percent));
                        target = "success";
                    }
                } else {
                    target = "success";
                }
            } else if (session.getAttribute("sadaremCodeAu") != null) {
                MentalRetardationActionForm mentalform = insertAndUpdateCalculationMental(form, request);
                if (mentalform.getTotalpercent() >= 0 && !"".equals(mentalform.getIq())
                        && null != mentalform.getIq()) {
                    if (Double.parseDouble(mentalform.getIq()) > 0) {
                        percent = mentalform.getTotalpercent();
                        mentalform.setPersoncode(personcode);
                        mentalform.setLoginid(loginid);
                        mentalform.setSystemip(request.getRemoteAddr());
                        try {
                            BeanUtils.copyProperties(modifydto, mentalform);
                        } catch (InvocationTargetException ex) {
                            ex.printStackTrace();
                            //throw new SADAREMDBException();
                        } catch (IllegalAccessException ex) {
                            ex.printStackTrace();
                            //throw new SADAREMDBException();
                        }


                        tot1 = mentalretarservice.insertDataAU(ds, modifydto, request);



                        request.setAttribute("msg", "MentalRetardation Added Successfully");
                        target = "success";
                        session.setAttribute("mentalretardation", new Double(percent));
                        target = "success";
                    }
                } else {
                    target = "success";
                }
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

    /**
     * This method is used for Inserting the percentages of  Mental Retardation.
     * @param mapping mapping action
     * @param form MentalRetardationActionForm
     * @throws java.io.IOException
     * @throws java.lang.Exception
     */
    public ActionForward insertMentalRetardationAU(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

        //MentalRetardationActionForm mentalform=(MentalRetardationActionForm)form;
        //DataSource ds=getDataSource(request);
        DataSource ds = null;

        String personcode = null;
        double percent = 0;
        int tot1 = 0;
        String loginid = null;
        String target = "success";


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            HttpSession session = request.getSession();
            MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
            MentalRetardationDTO modifydto = new MentalRetardationDTO();
            personcode = (String) session.getAttribute("sadaremCodeAu");
            loginid = (String) session.getAttribute("loginid");
            boolean personcodecheckflag = mentalretarservice.checkPersoncode(personcode, ds);
            if (personcodecheckflag == false) {
                MentalRetardationActionForm mentalform = insertAndUpdateCalculationMental(form, request);
                if (mentalform.getTotalpercent() >= 0 && !"".equals(mentalform.getIq())
                        && null != mentalform.getIq()) {
                    if (Double.parseDouble(mentalform.getIq()) > 0) {
                        percent = mentalform.getTotalpercent();
                        mentalform.setPersoncode(personcode);
                        mentalform.setLoginid(loginid);
                        mentalform.setSystemip(request.getRemoteAddr());
                        try {
                            BeanUtils.copyProperties(modifydto, mentalform);
                        } catch (InvocationTargetException ex) {
                            ex.printStackTrace();
                            //throw new SADAREMDBException();
                        } catch (IllegalAccessException ex) {
                            ex.printStackTrace();
                            //throw new SADAREMDBException();
                        }


                        tot1 = mentalretarservice.insertData(ds, modifydto, request);
                        request.setAttribute("msg", "MentalRetardation Added Successfully");
                        target = "success";
                        session.setAttribute("mentalretardation", new Double(percent));
                        target = "success";
                    }
                } else {
                    target = "success";
                }
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

    /**
     * this method used for retriving the details of Mental Retardation.
     * @param mapping
     * @param form MentalRetardationActionForm
     * @throws java.io.IOException
     * @throws java.lang.Exception
     */
    public ActionForward selectMentalRetardation(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        //DataSource datasource=getDataSource(request);
        DataSource datasource = null;
        String personcode = null;
        String target = "success";
        String loginid = null;

        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession(true);
            //  personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }

            MentalRetardationActionForm mentalform = (MentalRetardationActionForm) form;
            MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
            MentalRetardationDTO modifydto = new MentalRetardationDTO();
            modifydto = mentalretarservice.getMentalDetails(datasource, personcode);

            modifydto.setPersoncode(personcode);
            modifydto.setLoginid(loginid);
            modifydto.setSystemip(request.getRemoteAddr());
            try {
                BeanUtils.copyProperties(mentalform, modifydto);
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
     * This method is used for modifying the percentages of Mental Retardation.
     * @param form MentalRetardationActionForm
     * @throws java.io.IOException
     * @throws java.lang.Exception
     */
    public ActionForward updateMentalRetardation(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        //MentalRetardationActionForm mentalform=(MentalRetardationActionForm)form;
        DataSource ds = null;

        String personcode = null;
        String target = "success";
        double percent = 0;
        int tot1 = 0;
        String loginid = null;
        //DataSource ds=getDataSource(request);


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            HttpSession session = request.getSession(true);
            // personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }


            MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
            MentalRetardationDTO modifydto = new MentalRetardationDTO();
            MentalRetardationActionForm mentalform = new MentalRetardationActionForm();
            //  personcode = (String) session.getAttribute("personcode");
            boolean personcodecheckflag = mentalretarservice.checkPersoncode(personcode, ds);
            if (personcodecheckflag == false) {
                mentalform = insertAndUpdateCalculationMental(form, request);
                if (mentalform.getTotalpercent() >= 0 && !"".equals(mentalform.getIq())
                        && null != mentalform.getIq()) {
                    if (Double.parseDouble(mentalform.getIq()) > 0) {
                        percent = mentalform.getTotalpercent();
                        mentalform.setPersoncode(personcode);
                        mentalform.setLoginid(loginid);
                        mentalform.setSystemip(request.getRemoteAddr());
                        session.setAttribute("mentalretardation", new Double(percent));
                        try {
                            BeanUtils.copyProperties(modifydto, mentalform);
                        } catch (InvocationTargetException ex) {
                            ex.printStackTrace();
                            //throw new SADAREMDBException();
                        } catch (IllegalAccessException ex) {
                            ex.printStackTrace();
                            //throw new SADAREMDBException();
                        }
                        modifydto.setPersoncode(personcode);
                        modifydto.setLoginid(loginid);
                        modifydto.setSystemip(request.getRemoteAddr());
                        tot1 = mentalretarservice.insertData(ds, modifydto, request);
                    }
                }
            } else {
                mentalform = insertAndUpdateCalculationMental(form, request);
                try {
                    BeanUtils.copyProperties(modifydto, mentalform);
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                    //throw new SADAREMDBException();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                    //throw new SADAREMDBException();
                }
                modifydto.setPersoncode(personcode);
                modifydto.setLoginid(loginid);
                modifydto.setSystemip(request.getRemoteAddr());
                if (session.getAttribute("sadaremCodeAu") != null) {
                    tot1 = mentalretarservice.insertData(ds, modifydto, request);
                } else {
                    mentalretarservice.updateDetails(ds, modifydto, request);
                }
                percent = modifydto.getTotalpercent();
                session.setAttribute("mentalretardation", new Double(percent));
                request.setAttribute("msg", "MentalRetardation Updated Successfully");
                target = "success";
            }
            if (!session.getAttribute("roleId").equals("5")) {
                MentalRetardationTestsDTO disableMentalItems = mentalretarservice.getMentalRetardationIQValues(ds, personcode);
                request.setAttribute("disableMentalItems", disableMentalItems);
            }
            MentalRetardationDTO testDetails = mentalretarservice.getTestDetails(ds, personcode);
            request.setAttribute("testDetails", testDetails);



            //added for Certificate generation
            session.setAttribute("mentalRetardationIQ", mentalform.getIq());
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
     * This method is used for Calculating the percentages of  Mental Retardation.
     */
    public MentalRetardationActionForm insertAndUpdateCalculationMental(ActionForm form, HttpServletRequest request) {
        double percent = 0;
        double range = 0;
        MentalRetardationActionForm mentalform = (MentalRetardationActionForm) form;


        range = Double.parseDouble(mentalform.getIq());

        if (range >= 70 && range < 80) {
            percent = 25.0;
        } else if (range >= 50 && range < 70) {
            percent = 50.0;
        } else if (range >= 35 && range < 50) {
            percent = 75.0;
        } else if (range >= 20 && range < 35) {
            percent = 90.0;
        } else if (range < 20 && range != 0) {
            percent = 100.0;
        } else {
            percent = 0.0;
        }
        mentalform.setTotalpercent(percent);
        return mentalform;
    }
}





