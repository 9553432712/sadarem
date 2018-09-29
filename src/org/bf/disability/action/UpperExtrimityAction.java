package org.bf.disability.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.form.UpperExtrimityActionForm;
import org.bf.disability.dto.UpperExtrimityDto;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.servicefactory.UpperExterimityServiceFactory;
import org.bf.disability.util.UpperExtremityCalculation;
import javax.sql.DataSource;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.service.UpperExtrimityService;

/**
 * this dispatch action class will manipulate the lower extremity details
 * @author svsganesh
 * @version 1.0
 */
public class UpperExtrimityAction extends BaseDispatchAction {
    //creating instance of form,dto and service

    //UpperExtrimityActionForm upperextrimityform = new UpperExtrimityActionForm();
    /**
     * this method will add the loweextremity details into data base
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    public ActionForward addUpperExterimityData(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        HttpSession session = request.getSession(true);

        String personcode = null;
        String loginid = null;
        DataSource ds = null;
        ActionMessages actionMessages = null;
        String target = "success";

        if (session.getAttribute("sadaremCodeAu") != null) {
            personcode = (String) session.getAttribute("sadaremCodeAu");
        } else {
            personcode = (String) session.getAttribute("personcode");
        }

        loginid = (String) session.getAttribute("loginid");

        //DataSource ds=getDataSource(request);


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            UpperExtremityCalculation upperextrimitycalculation =
                    new UpperExtremityCalculation();
            UpperExtrimityService upperextrimityservice =
                    UpperExterimityServiceFactory.getUpperExterimityServiceImpl();
            UpperExtrimityDto upperExtrimitydto = new UpperExtrimityDto();
            boolean personcodeflag =
                    upperextrimityservice.checkPersoncode(personcode, ds);
            if (personcodeflag == false) {
                upperExtrimitydto = upperextrimitycalculation.Calculation(form, request);
                if ((upperExtrimitydto.getUpperExterimity_total()) > 0.0) {
                    upperExtrimitydto.setPersoncode(personcode);
                    upperExtrimitydto.setLoginid(loginid);


                    int rominsert = upperextrimityservice.inserUpperExtremityData(ds, upperExtrimitydto, request);
                    request.setAttribute("msg", "Upper Extremity Added Successfully !");
                    target = "success";
                } else {
                    target = "success";
                }
            } else if (session.getAttribute("sadaremCodeAu") != null) {
                upperExtrimitydto = upperextrimitycalculation.Calculation(form, request);
                if ((upperExtrimitydto.getUpperExterimity_total()) > 0.0) {
                    upperExtrimitydto.setPersoncode(personcode);
                    upperExtrimitydto.setLoginid(loginid);
                    int rominsert = upperextrimityservice.inserUpperExtremityDataAU(ds, upperExtrimitydto, request);
                    if (rominsert == -1) {
                        target = "failure";
                    } else {
                        request.setAttribute("msg", "Upper Extremity Added Successfully !");
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
     * this method will get the lowerextremity details form the database of a particular person
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    public ActionForward selectUpperExterimity(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        //DataSource ds=getDataSource(request);
        DataSource ds = null;
        ActionMessages actionMessages = null;
        String target = "success";
        String personcode = null;
        double upperextrimitytotal = 0;
        int checkBeforeUpdationflag = 0;

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


            UpperExtrimityActionForm upperextrimityform =
                    (UpperExtrimityActionForm) form;
            UpperExtrimityDto upperExtrimitydto = new UpperExtrimityDto();
            UpperExtrimityService upperextrimityservice =
                    UpperExterimityServiceFactory.getUpperExterimityServiceImpl();
            upperExtrimitydto.setPersoncode(personcode);
            upperExtrimitydto =
                    upperextrimityservice.selectUpperExterimityData(ds, personcode);

            if ((upperExtrimitydto.getUpperExterimity_total()) != 0) {
                checkBeforeUpdationflag = 1;
            }
            upperextrimitytotal = upperExtrimitydto.getUpperExterimity_total();
            try {
                BeanUtils.copyProperties(upperextrimityform, upperExtrimitydto);
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
     * this method will add the loweextremity details into data base
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward addUpperExterimityDataAU(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        HttpSession session = request.getSession(true);

        String personcode = null;
        String loginid = null;
        DataSource ds = null;
        ActionMessages actionMessages = null;
        String target = "success";
        personcode = (String) session.getAttribute("sadaremCodeAu");
        loginid = (String) session.getAttribute("loginid");

        //DataSource ds=getDataSource(request);


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            UpperExtremityCalculation upperextrimitycalculation =
                    new UpperExtremityCalculation();
            UpperExtrimityService upperextrimityservice =
                    UpperExterimityServiceFactory.getUpperExterimityServiceImpl();
            UpperExtrimityDto upperExtrimitydto = new UpperExtrimityDto();
            boolean personcodeflag =
                    upperextrimityservice.checkPersoncode(personcode, ds);
            if (personcodeflag == false) {
                upperExtrimitydto = upperextrimitycalculation.Calculation(form, request);
                if ((upperExtrimitydto.getUpperExterimity_total()) > 0.0) {
                    upperExtrimitydto.setPersoncode(personcode);
                    upperExtrimitydto.setLoginid(loginid);
                    int rominsert = upperextrimityservice.inserUpperExtremityDataAU(ds, upperExtrimitydto, request);
                    if (rominsert > 0) {
                        request.setAttribute("msg", "Upper Extremity Added Successfully !");
                        target = "success";
                    } else {
                        request.setAttribute("msg", "Error in Upper Extremity Adding !");
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


    /*
     * This  Method is for updating data
     */
    /**
     * this method will update the lowerextremity details form the database of a particular person
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    public ActionForward updateUpperExterimity(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        //DataSource ds=getDataSource(request);
        String personcode = null;
        String loginid = null;
        DataSource ds = null;
        String target = "success";
        ActionMessages actionMessages = null;

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


            boolean flag = isTokenValid(request, true);
            if (flag) {
                UpperExtremityCalculation upperextrimitycalculation =
                        new UpperExtremityCalculation();
                UpperExtrimityService upperextrimityservice =
                        UpperExterimityServiceFactory.getUpperExterimityServiceImpl();
                UpperExtrimityDto upperExtrimitydto = new UpperExtrimityDto();
                loginid = (String) session.getAttribute("loginid");


                /*
                 * Calculation takes place for updated value
                 */
                upperExtrimitydto =
                        upperextrimitycalculation.Calculation(form, request);
                upperExtrimitydto.setPersoncode(personcode);
                upperExtrimitydto.setLoginid(loginid);
                /*
                 * Checking for Pwd
                 */
                boolean personcodeflag =
                        upperextrimityservice.checkPersoncode(personcode, ds);

                if (personcodeflag == false) {
                    if ((upperExtrimitydto.getUpperExterimity_total()) > 0.0) {
                        /*
                         * If pwd doesnot exist
                         * inserting data by calling insert method
                         */
                        int rominsert =
                                upperextrimityservice.inserUpperExtremityData(ds, upperExtrimitydto, request);
                    }
                } else {
                    /*
                     * Checking for totalUpperExterimity value
                     * if its is not equal to zero then data will update
                     */
                    if ((upperExtrimitydto.getUpperExterimity_total()) > 0.0) {
                        /*
                         * If Pwd exist  data
                         *  data will update by calling update method
                         */
                        int romUpdate = 0;
                        // if (session.getAttribute("sadaremCodeAu") != null) {
                        //      romUpdate = upperextrimityservice.inserUpperExtremityDataAU(ds, upperExtrimitydto, request);
                        //  } else {
                        romUpdate = upperextrimityservice.updateRomData(ds, upperExtrimitydto, request);
                        //   }

                    } else {
                        /*
                         * Checking for totalUpperExterimity value
                         * if its is  equal to zero then data will delete
                         * for particular Pwd
                         */
                        upperextrimityservice.deleteUpperExtremityUpdateRecord(ds, personcode);
                    }
                }
                request.setAttribute("msg", "Upper Extremity Updated Successfully !");
            }
            target = "success";
            /*
             * Throwing our own exception
             */
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
