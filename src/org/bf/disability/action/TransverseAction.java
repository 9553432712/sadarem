package org.bf.disability.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.TransverseDTO;
import org.bf.disability.form.TransverseForm;
import org.bf.disability.service.TransverseService;
import org.bf.disability.dao.TransverseDAO;
import org.bf.disability.servicefactory.TransverseServiceFactory;

/**
 * this action class will manipulate the details relating to disabilty, transverse
 * @author pramodh kumar g
 * @version 1.0
 */
public class TransverseAction extends BaseDispatchAction {

    /**
     * Deals with insertion of the form details into database.
     * Here we are using DispatchAction class.
     * @return Action Forward
     * @param mapping 
     * @param request 
     * @param response 
     * @param form Transverseform
     * @throws org.bf.disability.Exception.SADAREMException 
     */
    public ActionForward insertTransverseDetails(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        ActionMessages actionMessages = null;
        String target = null;
        String personcode = null;
        DataSource datasource = null;
        int val = 0;
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            TransverseService transverseservice = TransverseServiceFactory.getTransverseServiceImpl();
            TransverseForm transverseform = (TransverseForm) form;
            HttpSession session = request.getSession();
            // personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }


            TransverseDAO obj = new TransverseDAO();
            TransverseDTO transversedto = new TransverseDTO();
            String loginid = (String) session.getAttribute("loginid");
            boolean personcodecheck = false;
            try {
                personcodecheck = obj.personCode(datasource, personcode);
            } catch (Exception ex) {
                target = "exception";
                ex.printStackTrace();
            }

            if (personcodecheck == false) {
                /**
                 *insertCalculationPart() takes the formbean object as parameter,
                 *and performs the calculation part of the insertTransverseDetails().
                 */
                transverseform = insertCalculationPart(transverseform);
                if (transverseform.getFinalres() > 0) {
                    double res = transverseform.getFinalres();
                    session.setAttribute("transverse", new Double(transverseform.getFinalres()));
                    transverseform.setSystemip((String) request.getRemoteAddr());
                    try {
                        BeanUtils.copyProperties(transversedto, transverseform);
                    } catch (IllegalAccessException ex) {
                        target = "exception";
                        ex.printStackTrace();
                    } catch (InvocationTargetException ex) {
                        target = "exception";
                        ex.printStackTrace();
                    }
                    transversedto.setPersoncode(personcode);
                    transversedto.setLoginid(loginid);
                    /**
                     * With respect to the object created for the TransverseService class,
                     * invoke the method insertTransverseDetails() by passing
                     * the DataSource object
                     * and TransverseDTO object.
                     */
                    // val = transverseservice.insertTransverseDetails(datasource, transversedto,request);
                    val = transverseservice.insertTransverseDetails(datasource, transversedto, request);


                    target = "success";
                } else {
                    target = "success";
                }
            } else if (session.getAttribute("sadaremCodeAu") != null) {
                transverseform = insertCalculationPart(transverseform);
                if (transverseform.getFinalres() > 0) {
                    double res = transverseform.getFinalres();
                    session.setAttribute("transverse", new Double(transverseform.getFinalres()));
                    transverseform.setSystemip((String) request.getRemoteAddr());
                    try {
                        BeanUtils.copyProperties(transversedto, transverseform);
                    } catch (IllegalAccessException ex) {
                        target = "exception";
                        ex.printStackTrace();
                    } catch (InvocationTargetException ex) {
                        target = "exception";
                        ex.printStackTrace();
                    }
                    transversedto.setPersoncode(personcode);
                    transversedto.setLoginid(loginid);
                    val = transverseservice.insertTransverseDetailsAU(datasource, transversedto, request);
                    if (val == -1) {
                        target = "failure";
                    } else {
                        request.setAttribute("msg", "CONGENTIAL DEFICIENCIES Added SuccessFully");
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
    }//End of insertTransverseDetails().

    /**
     * Deals with insertion of the form details into database.
     * Here we are using DispatchAction class.
     * @return Action Forward
     * @param mapping
     * @param request
     * @param response
     * @param form Transverseform
     * @throws org.bf.disability.Exception.SADAREMException
     */
    public ActionForward insertTransverseDetailsAU(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        ActionMessages actionMessages = null;
        String target = null;
        String personcode = null;
        DataSource datasource = null;
        int val = 0;
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            TransverseService transverseservice = TransverseServiceFactory.getTransverseServiceImpl();
            TransverseForm transverseform = (TransverseForm) form;
            HttpSession session = request.getSession();
            personcode = (String) session.getAttribute("sadaremCodeAu");

            TransverseDAO obj = new TransverseDAO();
            TransverseDTO transversedto = new TransverseDTO();
            String loginid = (String) session.getAttribute("loginid");
            boolean personcodecheck = false;
            try {
                personcodecheck = obj.personCode(datasource, personcode);
            } catch (Exception ex) {
                target = "exception";
                ex.printStackTrace();
            }

            if (personcodecheck == false) {
                /**
                 *insertCalculationPart() takes the formbean object as parameter,
                 *and performs the calculation part of the insertTransverseDetails().
                 */
                transverseform = insertCalculationPart(transverseform);
                if (transverseform.getFinalres() > 0) {
                    double res = transverseform.getFinalres();
                    session.setAttribute("transverse", new Double(transverseform.getFinalres()));
                    transverseform.setSystemip((String) request.getRemoteAddr());
                    try {

                        BeanUtils.copyProperties(transversedto, transverseform);
                    } catch (IllegalAccessException ex) {
                        target = "exception";
                        ex.printStackTrace();
                    } catch (InvocationTargetException ex) {
                        target = "exception";
                        ex.printStackTrace();
                    }
                    transversedto.setPersoncode(personcode);
                    transversedto.setLoginid(loginid);
                    /**
                     * With respect to the object created for the TransverseService class,
                     * invoke the method insertTransverseDetails() by passing
                     * the DataSource object
                     * and TransverseDTO object.
                     */
                    val = transverseservice.insertTransverseDetailsAU(datasource, transversedto, request);
                    if (val > 0) {
                        request.setAttribute("msgSuccess", "Data Inserted Successfully");
                        target = "success";
                    } else {
                        request.setAttribute("msgFailure", "Error in Data Inserting");
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
    }//End of insertTransverseDetails().

    /**
     * Deals with  retrieving the values from the database.
     * Here we are using DispatchAction class.
     * @return Action Forward
     * @param mapping 
     * @param request 
     * @param response 
     * @param form Transverseform
     * @throws org.bf.disability.Exception.SADAREMException 
     */
    public ActionForward getTransverseDetails(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = null;
        ActionMessages actionMessages = null;
        String personcode = null;
        DataSource datasource = null;
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            // personcode = (String) session.getAttribute("personcode");
            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }



            TransverseForm transverseform = (TransverseForm) form;
            TransverseDTO transverseupdatedto = new TransverseDTO();
            TransverseService transverupdateseservice = TransverseServiceFactory.getTransverseServiceImpl();
            transverseupdatedto = transverupdateseservice.getTransverseDetails(datasource, personcode);
            BeanUtils.copyProperties(transverseform, transverseupdatedto);
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
     * Deals with  updating the values of the database.
     * Here we are using DispatchAction class.
     * @return Instructs the controller to forward to the LocomotorSublinks.jsp
     * @param mapping 
     * @param request 
     * @param response 
     * @param form TransverseForm
     * @throws org.bf.disability.Exception.SADAREMException 
     */
    public ActionForward updateTransverseDetails(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = null;
        double res1;
        ActionMessages actionMessages = null;
        TransverseForm transverseupdateform = null;
        TransverseDTO transverseupdatedto = null;
        String personcode = null;
        DataSource datasource = null;
        /**
         * As the calculation part to find out the finalresult for based on all the
         * form values is same as that of we have used in insert module,
         * resue the same method for this update module too.
         */
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            HttpSession session = request.getSession();
            boolean flag = isTokenValid(request, true);
            if (flag) {

                transverseupdateform = (TransverseForm) form;
                transverseupdatedto = new TransverseDTO();
                TransverseService transverseupdateservice = TransverseServiceFactory.getTransverseServiceImpl();
                TransverseDAO obj = new TransverseDAO();
                //personcode = (String) session.getAttribute("personcode");

                if (session.getAttribute("sadaremCodeAu") != null) {
                    personcode = (String) session.getAttribute("sadaremCodeAu");
                } else {
                    personcode = (String) session.getAttribute("personcode");
                }


                String loginid = (String) session.getAttribute("loginid");
                transverseupdateform = insertCalculationPart(transverseupdateform);
                res1 = transverseupdateform.getFinalres();
                session.setAttribute("transverse", new Double(res1));
                transverseupdateform.setSystemip((String) request.getRemoteAddr());
                try {
                    BeanUtils.copyProperties(transverseupdatedto, transverseupdateform);
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                }

                transverseupdatedto.setPersoncode(personcode);
                transverseupdatedto.setLoginid(loginid);
                transverseupdatedto.setSystemip((String) request.getRemoteAddr());
                boolean personcodecheck = false;
                try {
                    personcodecheck = obj.personCode(datasource, personcode);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if (personcodecheck == false) {
                    if (transverseupdatedto.getFinalres() > 0) {
                        transverseupdateservice.insertTransverseDetails(datasource, transverseupdatedto, request);
                    }
                } else {

                    //  if(session.getAttribute("sadaremCodeAu")!=null) {
                    //       transverseupdateservice.insertTransverseDetails(datasource, transverseupdatedto,request);
                    //  }else {
                    transverseupdateservice.updateTransverseDetails(datasource, transverseupdatedto, request);
                    //   }
                }
                target = "success1";
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
    } //end of updateTransverseDetails()

    /**
     * This function receives the FormBean object.
     * Gets the values of all form elements into two ArrayList objects.
     * Sends each array to the needCalculation() to perform calculation.
     * Which returns a double value as the result.
     * This process will be repeated for the second ArrayList object too.
     * At last these two result will be sent as parameters to internalCalculation().
     * Which returns the finalresult value. 
     * set the final result value, by using the FormBean method setFinalres().
     * This method performs the calculation part of inserting of data into the database. 
     * Methods needCalculation(),internalCalculation() 
     * are available in the org.bf.disability.dao.calculateResult class.
     * @param transverseform 
     * @return TransverseForm
     */
    public TransverseForm insertCalculationPart(TransverseForm transverseform) {
        double finalresult, final1, final2;


        ArrayList a1 = new ArrayList();
        ArrayList a2 = new ArrayList();

        a1.add(new Integer(transverseform.getShoulderdisarticulationrightside()));
        a1.add(new Integer(transverseform.getHipdisarticulationrightside()));
        a1.add(new Integer(transverseform.getAboveelbowamputeerightside()));
        a1.add(new Integer(transverseform.getKneeamputeerightside()));
        a1.add(new Integer(transverseform.getElbowdisarticulationrightside()));
        a1.add(new Integer(transverseform.getBelowelbowamputeerightside()));
        a1.add(new Integer(transverseform.getWristdisarticulationrightside()));
        a1.add(new Integer(transverseform.getCarpalbonesrightside()));

        final1 = needCalculation(a1);

        a2.add(new Integer(transverseform.getShoulderdisarticulationleftside()));
        a2.add(new Integer(transverseform.getHipdisarticulationleftside()));
        a2.add(new Integer(transverseform.getAboveelbowamputeeleftside()));
        a2.add(new Integer(transverseform.getKneeamputeeleftside()));
        a2.add(new Integer(transverseform.getElbowdisarticulationleftside()));
        a2.add(new Integer(transverseform.getBelowelbowamputeeleftside()));
        a2.add(new Integer(transverseform.getWristdisarticulationleftside()));
        a2.add(new Integer(transverseform.getCarpalbonesleftside()));


        final2 = needCalculation(a2);

        if (final1 >= final2) {
            finalresult = internalCalculation(final1, final2);
        } else {
            finalresult = internalCalculation(final2, final1);
        }

        if (finalresult > 100) {
            finalresult = 100.00;
        }

        transverseform.setFinalres(finalresult);
        return transverseform;
    }//end of insertCalculationPart()

    /**
     * this is calculation method
     * @param a 
     * @return double
     */
    public double needCalculation(ArrayList a) {
        double final1;
        // Findout the Highest value in the arraylist.
        int max1 = ((Integer) Collections.max(a)).intValue();


        //sorting the arraylist into ascending order.
        Collections.sort(a);

        //if highest value is 0 means no elements are there in the array.
        if (max1 == 0) {
            final1 = max1;
        } //if atleast one value is 90 or >90 then directly take that value as
        //final1.
        else if (max1 >= 90) {
            final1 = max1;
        } else {
            final1 = calcResult(a);
        }

        return final1;

    }//end of needCalculation()

    /**
     * used for calculation
     * @param obj 
     * @return double
     */
    public double calcResult(ArrayList obj) {
        double max1 = 0;


        Collections.reverse(obj);

        Object o[] = obj.toArray();
        int a[] = new int[o.length];

        for (int i = 0; i < o.length; i++) {
            a[i] = ((Integer) o[i]).intValue();
        }

        if (a.length == 1) {
            max1 = a[0];
        } else if (a.length == 2) {
            max1 = internalCalculation(a[0], a[1]);
        } else {
            max1 = internalCalculation(a[0], a[1]);
            for (int i = 2; i < a.length; i++) {
                if (a[i] == 0) {
                    break;
                }
                max1 = internalCalculation(max1, a[i]);
            }
        }

        return max1;
    }//end of calcResult()

    /**
     * used for calcualtions
     * @param a 
     * @param b 
     * @return double
     */
    public double internalCalculation(double a, double b) {

        a = a + ((b * (90 - a)) / 90);

        return a;
    }//end of internalCalculation()
}//end of TransverseAction()

