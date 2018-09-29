package org.bf.disability.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForward;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.EvaluationActionForm;
import org.bf.disability.service.EvaluationService;
import org.bf.disability.dto.EvaluationDTO;
import org.bf.disability.servicefactory.EvaluationServiceFactory;
import org.bf.disability.util.InsertAndUpdateCalculation;
import org.bf.disability.common.DataBase.JNDIDataSource;

/**
 * 
 * @description  Class for Inserting ,Selecting and Updating the Physical Impairment Details
 * @author pramodh kumar g
 * @version 1.0
 */
public class EvaluationDispatchAction extends BaseDispatchAction {

    ActionMessages actionMessages = null;

    /**
     * This method is used for Inserting the percentages of Physical Impairment.
     * @param request 
     * @param response 
     * @param mapping mapping action
     * @param form EvaluationActionForm
     * @return Action Forward
     * @throws org.bf.disability.Exception.SADAREMException 
     */
    public ActionForward insertEvaluation(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        DataSource ds = null;    //DataSource ds=getDataSource(request);
        double totalpercent = 0;
        int tot = 0;
        String loginid = null;
        String target = "success";
        String personcode = null;


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            EvaluationDTO evaluationdto = new EvaluationDTO();
            EvaluationActionForm evaluationform = (EvaluationActionForm) form;
            EvaluationService evaluationservice = EvaluationServiceFactory.getEvaluationServiceImpl();
            InsertAndUpdateCalculation cal = new InsertAndUpdateCalculation();
            // personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }


            loginid = (String) session.getAttribute("loginid");

            boolean personcodecheckflag = evaluationservice.checkPersoncode(personcode, ds);
            if (personcodecheckflag == false) {
                evaluationform = cal.calculation(evaluationform);
                if (evaluationform.getTotalpercent() > 0) {
                    totalpercent = evaluationform.getTotalpercent();
                    evaluationform.setPersoncode(personcode);
                    evaluationform.setLoginid(loginid);
                    evaluationform.setSystemip(request.getRemoteAddr());
                    try {
                        BeanUtils.copyProperties(evaluationdto, evaluationform);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    }


                    tot = evaluationservice.insertData(ds, evaluationdto, request);


                    request.setAttribute("msg", "PhysicalImpairment Added Successfully");
                    target = "success";
                    session.setAttribute("Evaluation", new Double(totalpercent));
                    target = "success";
                } else {
                    target = "success";
                }
            } else if (session.getAttribute("sadaremCodeAu") != null) {
                evaluationform = cal.calculation(evaluationform);
                if (evaluationform.getTotalpercent() > 0) {
                    totalpercent = evaluationform.getTotalpercent();
                    evaluationform.setPersoncode(personcode);
                    evaluationform.setLoginid(loginid);
                    evaluationform.setSystemip(request.getRemoteAddr());
                    try {
                        BeanUtils.copyProperties(evaluationdto, evaluationform);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    }

                    //  tot = evaluationservice.insertData(ds, evaluationdto,request);


                    tot = evaluationservice.insertDataAU(ds, evaluationdto, request);
                    if (tot == -1) {
                        target = "failure";
                    } else {
                        request.setAttribute("msg", "PhysicalImpairment Added Successfully");
                        target = "success";
                        session.setAttribute("Evaluation", new Double(totalpercent));
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
     * This method is used for Inserting the percentages of Physical Impairment.
     * @param request
     * @param response
     * @param mapping mapping action
     * @param form EvaluationActionForm
     * @return Action Forward
     * @throws org.bf.disability.Exception.SADAREMException
     */
    public ActionForward insertEvaluationAU(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        DataSource ds = null;       //DataSource ds=getDataSource(request);
        double totalpercent = 0;
        int tot = 0;
        String loginid = null;
        String target = "success";
        String personcode = null;


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            EvaluationDTO evaluationdto = new EvaluationDTO();
            EvaluationActionForm evaluationform = (EvaluationActionForm) form;
            EvaluationService evaluationservice = EvaluationServiceFactory.getEvaluationServiceImpl();
            InsertAndUpdateCalculation cal = new InsertAndUpdateCalculation();
            personcode = (String) session.getAttribute("sadaremCodeAu");
            loginid = (String) session.getAttribute("loginid");

            boolean personcodecheckflag = evaluationservice.checkPersoncode(personcode, ds);
            if (personcodecheckflag == false) {
                evaluationform = cal.calculation(evaluationform);
                if (evaluationform.getTotalpercent() > 0) {
                    totalpercent = evaluationform.getTotalpercent();
                    evaluationform.setPersoncode(personcode);
                    evaluationform.setLoginid(loginid);
                    evaluationform.setSystemip(request.getRemoteAddr());
                    try {
                        BeanUtils.copyProperties(evaluationdto, evaluationform);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    }

                    tot = evaluationservice.insertDataAU(ds, evaluationdto, request);
                    if (tot > 0) {
                        request.setAttribute("msgSuccess", "PhysicalImpairment Added Successfully");
                        target = "success";
                    } else {
                        request.setAttribute("msgFailure", "Error in Adding the PhysicalImpairment");
                        target = "failure";
                    }
                    session.setAttribute("Evaluation", new Double(totalpercent));
                    target = "success";
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
     * this method used for retriving the details of Physical Impairment
     * @param request 
     * @param response 
     * @param mapping 
     * @param form EvaluationActionForm
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    public ActionForward selectEvaluation(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = null;
        String loginid = null;
        DataSource datasource = null;
        String personcode = null;

        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }


            EvaluationActionForm evaluationform = (EvaluationActionForm) form;
            EvaluationService evaluationservice = EvaluationServiceFactory.getEvaluationServiceImpl();
            //DataSource datasource=getDataSource(request);

            HttpSession session = request.getSession(true);
            //  personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }


            EvaluationDTO evaluationdto = new EvaluationDTO();
            evaluationdto = evaluationservice.getEvaluationDetails(datasource, personcode);
            evaluationdto.setPersoncode(personcode);
            evaluationdto.setLoginid(loginid);
            evaluationdto.setSystemip(request.getRemoteAddr());
            try {
                BeanUtils.copyProperties(evaluationform, evaluationdto);
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
     * This method is used for modifying the percentages of Physical Impairment.
     * @param mapping 
     * @param request 
     * @param response 
     * @param form EvaluationActionForm
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    public ActionForward updateEvaluationAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        DataSource ds = null;

        String loginid = null;
        String personcode = null;
        String target = "success";
        //DataSource ds=getDataSource(request);
        double totalpercent = 0;
        int tot = 0;
        HttpSession session = request.getSession(true);
        try { ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
            boolean flag = isTokenValid(request, true);
            if (flag) {

                EvaluationDTO evaluationdto = new EvaluationDTO();
                InsertAndUpdateCalculation cal = new InsertAndUpdateCalculation();
                EvaluationActionForm evaluationform = (EvaluationActionForm) form;
                EvaluationService evaluationservice = EvaluationServiceFactory.getEvaluationServiceImpl();

                //personcode = (String) session.getAttribute("personcode");

                if (session.getAttribute("sadaremCodeAu") != null) {
                    personcode = (String) session.getAttribute("sadaremCodeAu");
                } else {
                    personcode = (String) session.getAttribute("personcode");
                }


                boolean personcodecheckflag = evaluationservice.checkPersoncode(personcode, ds);
                if (personcodecheckflag == false) {
                    evaluationform = cal.calculation(evaluationform);
                    totalpercent = evaluationform.getTotalpercent();
                    session.setAttribute("Evaluation", new Double(totalpercent));
                    try {
                        BeanUtils.copyProperties(evaluationdto, evaluationform);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    }
                    evaluationdto.setPersoncode(personcode);
                    evaluationdto.setLoginid(loginid);
                    evaluationdto.setSystemip(request.getRemoteAddr());
                    tot = evaluationservice.insertData(ds, evaluationdto, request);

                } else {
                    evaluationform = cal.calculation(evaluationform);

                    try {
                        BeanUtils.copyProperties(evaluationdto, evaluationform);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    }
                    evaluationdto.setPersoncode(personcode);
                    evaluationdto.setLoginid(loginid);
                    evaluationdto.setSystemip(request.getRemoteAddr());
                    //  if(session.getAttribute("sadaremCodeAu")!=null) {
                    //   tot = evaluationservice.insertData(ds, evaluationdto,request);
                    //  }else {
                    evaluationservice.updateDetails(ds, evaluationdto, request);
                    //   }
                    totalpercent = evaluationform.getTotalpercent();
                    session.setAttribute("Evaluation", new Double(totalpercent));
                    request.setAttribute("msg", "PhysicalImpairment Updated Successfully");
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
}   
        
  
  



  
    

    

   

 
     
    


