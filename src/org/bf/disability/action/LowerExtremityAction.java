/*
 * LowerExtremityAction.java
 *
 * Created on June 25, 2008, 12:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.IOException;
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
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.dto.LowerExtremityDTO;
import org.bf.disability.form.LowerExtremityForm;
import org.bf.disability.service.LowerExtremityService;
import org.bf.disability.dao.LowerExtremityDAO;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.servicefactory.LowerExtremityServiceFactory;
import org.bf.disability.util.LowerExtremityCalculation;
import org.bf.disability.common.DataBase.JNDIDataSource;

/**
 * Class for Inserting ,Selecting and Updating the LowerExtremityDetails
 * @author Bapinaidu.t
 */
public class LowerExtremityAction extends BaseDispatchAction {

    ActionMessages actionMessages = null;

    /**
     * This method is used for Inserting the percentages of LowerExtremity.
     * @param mapping mapping action
     * @param form lowerextremityform
     * @throws java.io.IOException
     * @throws java.lang.Exception
     */
    public ActionForward insertLowerExtremityDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

        //DataSource datasource=getDataSource(request);
        double lowerextremity = 0;
        DataSource datasource = null;
        HttpSession session = request.getSession(true);
        String personcode = null;
        String target = "success";
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }

            LowerExtremityCalculation lowerextremitycalculation = new LowerExtremityCalculation();
            LowerExtremityService lowerextremityservice = LowerExtremityServiceFactory.getLowerExtremityServiceImpl();
            LowerExtremityDAO lowerextremitydao = new LowerExtremityDAO();
            LowerExtremityDTO lowerextremitydto = new LowerExtremityDTO();
            LowerExtremityForm lef = (LowerExtremityForm) form;
            //   personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }

            String loginid = (String) session.getAttribute("loginid");
            lef.setSystemip(request.getRemoteAddr());
            lef.setLoginid(loginid);
            boolean personcodecheck = lowerextremitydao.personCode(datasource, personcode);
            if (personcodecheck == false) {
                lef = lowerextremitycalculation.calculation(lef, request);
                if (lef.getLowerextremity() > 0) {
                    lowerextremity = lef.getLowerextremity();
                    try {
                        BeanUtils.copyProperties(lowerextremitydto, lef);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    }
                    lowerextremitydto.setPersoncode(personcode);


                    int lower = lowerextremityservice.insertLowerExtremityDetails(datasource, lowerextremitydto, request);

                    // int lower = lowerextremityservice.insertLowerExtremityDetails(datasource, lowerextremitydto,request);
                    request.setAttribute("msg", "Lower Extremity Added Successfully");
                    target = "success";
                } else {
                    target = "success";
                }
            } else if (session.getAttribute("sadaremCodeAu") != null) {
                lef = lowerextremitycalculation.calculation(lef, request);
                if (lef.getLowerextremity() > 0) {
                    lowerextremity = lef.getLowerextremity();
                    try {
                        BeanUtils.copyProperties(lowerextremitydto, lef);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    }
                    lowerextremitydto.setPersoncode(personcode);

                    int lower = lowerextremityservice.insertLowerExtremityDetailsAU(datasource, lowerextremitydto, request);
                    if (lower == -1) {
                        target = "failure";
                    } else {
                        request.setAttribute("msg", "LowerExtremity Added Successfully");
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
     * This method is used for Inserting the percentages of LowerExtremity.
     * @param mapping mapping action
     * @param form lowerextremityform
     * @throws java.io.IOException
     * @throws java.lang.Exception
     */
    public ActionForward insertLowerExtremityDetailsAU(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

        //DataSource datasource=getDataSource(request);
        double lowerextremity = 0;
        DataSource datasource = null;
        HttpSession session = request.getSession(true);
        String personcode = null;
        String target = "success";
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }

            LowerExtremityCalculation lowerextremitycalculation = new LowerExtremityCalculation();
            LowerExtremityService lowerextremityservice = LowerExtremityServiceFactory.getLowerExtremityServiceImpl();
            LowerExtremityDAO lowerextremitydao = new LowerExtremityDAO();
            LowerExtremityDTO lowerextremitydto = new LowerExtremityDTO();
            LowerExtremityForm lef = (LowerExtremityForm) form;
            personcode = (String) session.getAttribute("sadaremCodeAu");
            String loginid = (String) session.getAttribute("loginid");
            lef.setSystemip(request.getRemoteAddr());
            lef.setLoginid(loginid);
            boolean personcodecheck = lowerextremitydao.personCode(datasource, personcode);
            if (personcodecheck == false) {
                lef = lowerextremitycalculation.calculation(lef, request);
                if (lef.getLowerextremity() > 0) {
                    lowerextremity = lef.getLowerextremity();
                    try {
                        BeanUtils.copyProperties(lowerextremitydto, lef);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    }
                    lowerextremitydto.setPersoncode(personcode);
                    int lower = lowerextremityservice.insertLowerExtremityDetailsAU(datasource, lowerextremitydto, request);
                    if (lower > 0) {
                        request.setAttribute("msgSuccess", "Lower Extremity Added Successfully");
                        target = "success";
                    } else {
                        request.setAttribute("msgFailure", "Error in Lower Extremity Adding");
                        target = "failure";
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
     * this method used for retriving the details of lowerextremity
     * @param mapping
     * @param form lowerxtremityform
     * @throws java.io.IOException
     * @throws java.lang.Exception
     */
    public ActionForward getLowerExtremityDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        LowerExtremityForm lef = (LowerExtremityForm) form;

        //DataSource datasource=getDataSource(request);
        DataSource datasource = null;
        String personcode = null;
        String target = "success";
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession(true);
            LowerExtremityDTO lowerextremitydto = new LowerExtremityDTO();
            LowerExtremityService lowerextremityservice = LowerExtremityServiceFactory.getLowerExtremityServiceImpl();
            lef.setSystemip(request.getRemoteAddr());
            //personcode = (String) session.getAttribute("personcode");


            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }


            lowerextremitydto = lowerextremityservice.getLowerExtremityDetails(datasource, personcode);
            try {
                BeanUtils.copyProperties(lef, lowerextremitydto);
                saveToken(request);
                target = "success";
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
                //throw new SADAREMDBException();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
                //throw new SADAREMDBException();
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
     * This method is used for modifying the percentages of LowerExtremity.
     * @param form lowerextremityform
     * @throws java.io.IOException
     * @throws java.lang.Exception
     */
    public ActionForward updateLowerExtremityDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        double lowerextremity = 0;
        String personcode = null;
        DataSource datasource = null;
        String target = "success";
        try {
            
            boolean flag = isTokenValid(request, true);
            if (flag) {
                HttpSession session = request.getSession(true);
                LowerExtremityDTO lowerextremitydto = new LowerExtremityDTO();
                LowerExtremityCalculation lowerextremitycalculation = new LowerExtremityCalculation();
                LowerExtremityService lowerextremityservice = LowerExtremityServiceFactory.getLowerExtremityServiceImpl();
                LowerExtremityDAO lowerextremitydao = new LowerExtremityDAO();
                LowerExtremityForm lef = (LowerExtremityForm) form;
                datasource = getDataSource(request);
                if (datasource == null || "null".equals(datasource)) {
                     datasource = JNDIDataSource.getConnection();
                }
                //personcode = (String) session.getAttribute("personcode");

                if (session.getAttribute("sadaremCodeAu") != null) {
                    personcode = (String) session.getAttribute("sadaremCodeAu");
                } else {
                    personcode = (String) session.getAttribute("personcode");
                }



                String loginid = (String) session.getAttribute("loginid");

                lef.setLoginid(loginid);
                lef.setSystemip(request.getRemoteAddr());
                boolean personcodecheck = lowerextremitydao.personCode(datasource, personcode);
                if (personcodecheck == false) {
                    lef = lowerextremitycalculation.calculation(lef, request);
                    lowerextremity = lef.getLowerextremity();
                    try {
                        BeanUtils.copyProperties(lowerextremitydto, lef);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    }
                    lowerextremitydto.setPersoncode(personcode);
                    int lower1 = lowerextremityservice.insertLowerExtremityDetails(datasource, lowerextremitydto, request);
                } else {
                    lef = lowerextremitycalculation.calculation(lef, request);
                    try {
                        BeanUtils.copyProperties(lowerextremitydto, lef);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    }
                    lowerextremitydto.setPersoncode(personcode);

                    int lower = 0;

                    //  if(session.getAttribute("sadaremCodeAu")!=null) {
                    //    lower = lowerextremityservice.insertLowerExtremityDetails(datasource, lowerextremitydto,request);
                    //  }else {
                    lower = lowerextremityservice.updateLowerExtremityDetails(datasource, lowerextremitydto, request);
                    //  }

                    lowerextremity = lowerextremitydto.getLowerextremity();
                }
                session.setAttribute("lowerextremity", new Double(lowerextremity));
                request.setAttribute("msg", "Lower Extremity Updated Successfully");
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



