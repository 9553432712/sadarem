package org.bf.disability.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.HearingImpairmentActionForm;
import org.bf.disability.service.HearingImpairmentService;
import org.bf.disability.dto.HearingImpairmentDto;
import org.bf.disability.dao.HearingImpairmentDao;
import org.bf.disability.servicefactory.HearingImpairmentServiceFactory;
import org.bf.disability.common.DataBase.JNDIDataSource;

/**
 * Class for Inserting ,Selecting and Updating the Hearing Impairment Details
 * @author Sunima
 * @version 1.0
 */
public class HearingImpairmentAction extends BaseDispatchAction {
    // int dblevel;

    ActionMessages actionMessages = null;

    /**
     * This method is used for Inserting the percentages of  Hearing Impairment.
     * @param request 
     * @param response 
     * @param mapping mapping action
     * @param form HearingImpairmentActionForm
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    public ActionForward insertHearingImpairment(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

        double percent = 0;
        String personcode = null;
        String loginid = null;
        int tot1 = 0;
        String target = "success";
        DataSource ds = null;           //DataSource ds=getDataSource(request);

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            HearingImpairmentService hearingservice = HearingImpairmentServiceFactory.getHearingServiceImpl();
            HearingImpairmentDto hearingdto = new HearingImpairmentDto();
            // personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }

            loginid = (String) session.getAttribute("loginid");
            boolean personcodecheckflag = hearingservice.checkPersoncode(personcode, ds);
            if (personcodecheckflag == false) {
                HearingImpairmentActionForm hearingform = insertAndUpdateCalculationhearing(form, request);

                percent = hearingform.getTotalpercent();
                hearingform.setPersoncode(personcode);
                hearingform.setLoginid(loginid);
                hearingform.setSystemip(request.getRemoteAddr());
                hearingform = hearingFormNull(form, request);
                try {
                    BeanUtils.copyProperties(hearingdto, hearingform);
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                    //throw new SADAREMDBException();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                    //throw new SADAREMDBException();
                }
                // tot1 = hearingservice.insertData(ds, hearingdto,request);


                tot1 = hearingservice.insertData(ds, hearingdto, request);




                request.setAttribute("msg", "HearingImpairment Added Successfully");
                //session.setAttribute("hearingimpairment", new Double(percent));
                target = "success";
            } else if (session.getAttribute("sadaremCodeAu") != null) {
                HearingImpairmentActionForm hearingform = insertAndUpdateCalculationhearing(form, request);

                percent = hearingform.getTotalpercent();
                hearingform.setPersoncode(personcode);
                hearingform.setLoginid(loginid);
                hearingform.setSystemip(request.getRemoteAddr());
                hearingform = hearingFormNull(form, request);
                try {
                    BeanUtils.copyProperties(hearingdto, hearingform);
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                    //throw new SADAREMDBException();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                    //throw new SADAREMDBException();
                }
                // tot1 = hearingservice.insertData(ds, hearingdto,request);


                tot1 = hearingservice.insertDataAU(ds, hearingdto, request);
                if (tot1 == -1) {
                    target = "failure";
                } else {

                    request.setAttribute("msg", "HearingImpairment Added Successfully");
                    //session.setAttribute("hearingimpairment", new Double(percent));
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
     * This method is used for Inserting the percentages of  Hearing Impairment.
     * @param request
     * @param response
     * @param mapping mapping action
     * @param form HearingImpairmentActionForm
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward insertHearingImpairmentAU(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

        double percent = 0;
        String personcode = null;
        String loginid = null;
        int tot1 = 0;
        String target = "success";
        DataSource ds = null;         //DataSource ds=getDataSource(request);

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            HttpSession session = request.getSession();
            HearingImpairmentService hearingservice = HearingImpairmentServiceFactory.getHearingServiceImpl();
            HearingImpairmentDto hearingdto = new HearingImpairmentDto();
            personcode = (String) session.getAttribute("sadaremCodeAu");
            loginid = (String) session.getAttribute("loginid");
            boolean personcodecheckflag = hearingservice.checkPersoncode(personcode, ds);
            if (personcodecheckflag == false) {
                HearingImpairmentActionForm hearingform = insertAndUpdateCalculationhearing(form, request);

                percent = hearingform.getTotalpercent();
                hearingform.setPersoncode(personcode);
                hearingform.setLoginid(loginid);
                hearingform.setSystemip(request.getRemoteAddr());
                hearingform = hearingFormNull(form, request);
                try {
                    BeanUtils.copyProperties(hearingdto, hearingform);
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                    //throw new SADAREMDBException();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                    //throw new SADAREMDBException();
                }
                tot1 = hearingservice.insertDataAU(ds, hearingdto, request);

                request.setAttribute("msg", "HearingImpairment Added Successfully");
                //session.setAttribute("hearingimpairment", new Double(percent));
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

    /**
     * this method used for retriving the details of  Hearing Impairment.
     * @param request 
     * @param response 
     * @param mapping 
     * @param form HearingImpairmentActionForm
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    public ActionForward selectHearing(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        DataSource datasource = null;
        String personcode = null;
        String loginid = null;
        String target = "success";
        //DataSource datasource=getDataSource(request);


        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }


            HttpSession session = request.getSession(true);
            // personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }


            loginid = (String) session.getAttribute("loginid");
            HearingImpairmentActionForm hearingform = (HearingImpairmentActionForm) form;
            HearingImpairmentService hearingservice = HearingImpairmentServiceFactory.getHearingServiceImpl();
            HearingImpairmentDto hearingdto = new HearingImpairmentDto();
            hearingdto = hearingservice.getHearingDetails(datasource, personcode);
            hearingdto.setPersoncode(personcode);
            hearingdto.setLoginid(loginid);
            hearingdto.setSystemip(request.getRemoteAddr());
            try {
                BeanUtils.copyProperties(hearingform, hearingdto);

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
     * This method is used for modifying the percentages of  Hearing Impairment.
     * @param mapping 
     * @param request 
     * @param response 
     * @param form HearingImpairmentActionForm
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    public ActionForward updateHearing(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        HearingImpairmentActionForm hearingforms = (HearingImpairmentActionForm) form;
        HearingImpairmentDao hDao = new HearingImpairmentDao();
        double percent = 0.0;
        DataSource ds = null;

        String personcode = null;
        String loginid = null;
        int tot1 = 0;
        String target = "success";
        //DataSource ds=getDataSource(request);

        try { ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
            boolean flag = isTokenValid(request, true);
            if (flag) {


                HttpSession session = request.getSession(true);
                //personcode = (String) session.getAttribute("personcode");
                loginid = (String) session.getAttribute("loginid");
                HearingImpairmentService hearingservice = HearingImpairmentServiceFactory.getHearingServiceImpl();
                HearingImpairmentDto hearingdto = new HearingImpairmentDto();


                // personcode = (String) session.getAttribute("personcode");

                if (session.getAttribute("sadaremCodeAu") != null) {
                    personcode = (String) session.getAttribute("sadaremCodeAu");
                } else {
                    personcode = (String) session.getAttribute("personcode");
                }



                boolean personcodecheckflag = hearingservice.checkPersoncode(personcode, ds);

                if (personcodecheckflag == false) {
                    HearingImpairmentActionForm hearingform = insertAndUpdateCalculationhearing(form, request);
                    percent = hearingform.getTotalpercent();
                    hearingform.setPersoncode(personcode);
                    hearingform.setLoginid(loginid);
                    hearingform.setSystemip(request.getRemoteAddr());
                    hearingform = hearingFormNull(form, request);
                    try {
                        BeanUtils.copyProperties(hearingdto, hearingform);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    }
                    tot1 = hearingservice.insertData(ds, hearingdto, request);
                } else {
                    HearingImpairmentActionForm hearingform = insertAndUpdateCalculationhearing(form, request);
                    percent = hearingform.getTotalpercent();
                    hearingform.setPersoncode(personcode);
                    hearingform.setLoginid(loginid);
                    hearingform.setSystemip(request.getRemoteAddr());
                    hearingform = hearingFormNull(form, request);
                    try {
                        BeanUtils.copyProperties(hearingdto, hearingform);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    }
                    hearingservice.updateDetails(ds, hearingform, request);
                    //session.setAttribute("hearingimpairment", new Double(percent));
                    request.setAttribute("msg", "HearingImpairment Updated Successfully");

                    target = "success";
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
     * This method is used for Calculating the percentages of  Hearing Impairment.
     */
    private HearingImpairmentActionForm insertAndUpdateCalculationhearing(ActionForm form, HttpServletRequest request) {
        double betterearmonauralvalue;
        double poorerearmonauralvalue;
        int betterear;
        int poorerear;
        double percent = 0;
        double percenttotal = 0;




        HearingImpairmentActionForm hearingform = (HearingImpairmentActionForm) form;

//        if(hearingform.getHearing_dblr()=="")
//        {
//        hearingform.setHearing_dblr("0");
//        }
//       int dblevel=Integer.parseInt(hearingform.getHearing_dblr());
//       

        if (!"".equals(hearingform.getRighteardblevel()) && !"".equals(hearingform.getLefteardblevel())) {
            if ((Integer.parseInt(hearingform.getRighteardblevel())) < 26 || (Integer.parseInt(hearingform.getLefteardblevel()) < 26)) {
                percent = 0.0;
            } else {
                if ((Integer.parseInt(hearingform.getRighteardblevel())) < (Integer.parseInt(hearingform.getLefteardblevel()))) {
                    betterear = Integer.parseInt(hearingform.getRighteardblevel());
                    betterearmonauralvalue = monauralFormula(betterear);
                    poorerear = Integer.parseInt(hearingform.getLefteardblevel());
                    poorerearmonauralvalue = monauralFormula(poorerear);

                } else {
                    betterear = Integer.parseInt(hearingform.getLefteardblevel());
                    betterearmonauralvalue = monauralFormula(betterear);
                    poorerear = Integer.parseInt(hearingform.getRighteardblevel());
                    poorerearmonauralvalue = monauralFormula(poorerear);
                }
                percent = ((betterearmonauralvalue * 5) + poorerearmonauralvalue) / 6;
            }
        } else {
            hearingform.setRighteardblevel("0");
            hearingform.setLefteardblevel("0");
        }


        if (percent > 100) {
            percenttotal = 100;

        } else if (percent <= 100) {
            percenttotal = percent;
        }

        hearingform.setTotalpercent(percenttotal);

        return hearingform;
    }

    public double monauralFormula(int a) {
        double monauralFormula = (a - 25) * 1.5;
        return monauralFormula;
    }

    private HearingImpairmentActionForm hearingFormNull(ActionForm form, HttpServletRequest request) {
        HearingImpairmentActionForm hearingform = (HearingImpairmentActionForm) form;
        if ("".equals(hearingform.getRightear250())) {
            hearingform.setRightear250(null);
        }
        if ("".equals(hearingform.getRightear500())) {
            hearingform.setRightear500(null);
        }
        if ("".equals(hearingform.getRightear1000())) {
            hearingform.setRightear1000(null);
        }
        if ("".equals(hearingform.getRightear2000())) {
            hearingform.setRightear2000(null);
        }
        if ("".equals(hearingform.getRightear4000())) {
            hearingform.setRightear4000(null);
        }
        if ("".equals(hearingform.getRightear8000())) {
            hearingform.setRightear8000(null);
        }
        if ("".equals(hearingform.getLeftear250())) {
            hearingform.setLeftear250(null);
        }
        if ("".equals(hearingform.getLeftear500())) {
            hearingform.setLeftear500(null);
        }
        if ("".equals(hearingform.getLeftear1000())) {
            hearingform.setLeftear1000(null);
        }
        if ("".equals(hearingform.getLeftear2000())) {
            hearingform.setLeftear2000(null);
        }
        if ("".equals(hearingform.getLeftear4000())) {
            hearingform.setLeftear4000(null);
        }
        if ("".equals(hearingform.getLeftear8000())) {
            hearingform.setLeftear8000(null);
        }
        if ("".equals(hearingform.getSpeechaudiometryrightear_pta())) {
            hearingform.setSpeechaudiometryrightear_pta(null);
        }


        return hearingform;
    }
}
  



   