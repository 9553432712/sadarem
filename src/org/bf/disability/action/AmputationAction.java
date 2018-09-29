package org.bf.disability.action;

import java.lang.reflect.InvocationTargetException;
import javax.servlet.ServletException;
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
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.dto.AmputationDto;
import org.bf.disability.form.AmputationForm;
import org.bf.disability.service.AmputationService;
import org.bf.disability.servicefactory.AmputationServiceFactory;
import java.sql.SQLException;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;

/**
 *
 * @description this dispatch action class is used to manipulate the Amputation disability details
 * @author deviprasad.t
 * @version 1.0
 */
public class AmputationAction extends BaseDispatchAction {
    //class variables
    //int checkBeforeUpdationflag=0;

    ActionMessages actionMessages = null;

    /**
     *
     * @description this method is used to add the amputation details into data base for a particular person
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return ActionForward
     */
    public ActionForward addAmputationdetails(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        AmputationDto ampdto = new AmputationDto();
        AmputationService amputationService =
                AmputationServiceFactory.getAmputationServiceImpl();

        DataSource ds = null;
       
        String personcode = null;
        //DataSource ds=getDataSource(request);


        HttpSession session = request.getSession(true);
        //  personcode = (String) session.getAttribute("personcode");

        if (session.getAttribute("sadaremCodeAu") != null) {
            personcode = (String) session.getAttribute("sadaremCodeAu");
        } else {
            personcode = (String) session.getAttribute("personcode");
        }

        String target = null;
        try { ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }

            boolean personcodecheckflag = amputationService.checkPersoncode(personcode, ds);

            if (personcodecheckflag == false) {
                ampdto = addOrUpdateCalculations(form, request);
                if (ampdto.getAmputationtotal() > 0) {
                    ampdto.setPersoncode(personcode);
                    ampdto.setLoginid((String) session.getAttribute("loginid"));
                    ampdto.setSystemIP(request.getRemoteAddr());



                    amputationService.insertAmputationDetails(ds, ampdto, request);
                    request.setAttribute("msg", "Amutation Details Inserted Successfully");
                    target = "success";
                } else {

                    target = "success";
                }
            } else if (session.getAttribute("sadaremCodeAu") != null) {
                ampdto = addOrUpdateCalculations(form, request);
                if (ampdto.getAmputationtotal() > 0) {
                    ampdto.setPersoncode(personcode);
                    ampdto.setLoginid((String) session.getAttribute("loginid"));
                    ampdto.setSystemIP(request.getRemoteAddr());
                    int i = amputationService.insertAmputationDetailsAU(ds, ampdto, request);
                    if (i == -1) {

                        target = "failure";
                    } else {
                        request.setAttribute("msg", "Amutation Details Inserted Successfully");
                        target = "success";
                    }

                } else {
                    request.setAttribute("msg", "Amutation Details Inserted Successfully");
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
     *
     * @description this method is used to add the amputation details into data base for a particular person
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return ActionForward
     */
    public ActionForward addAmputationdetailsAU(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        AmputationDto ampdto = new AmputationDto();
        AmputationService amputationService =
                AmputationServiceFactory.getAmputationServiceImpl();

        DataSource ds = null;
        
        String personcode = null;
        //DataSource ds=getDataSource(request);

        HttpSession session = request.getSession(true);
        personcode = (String) session.getAttribute("sadaremCodeAu");
        String target = null;
        try {ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }

            boolean personcodecheckflag = amputationService.checkPersoncode(personcode, ds);

            if (personcodecheckflag == false) {
                ampdto = addOrUpdateCalculations(form, request);
                if (ampdto.getAmputationtotal() > 0) {
                    ampdto.setPersoncode(personcode);
                    ampdto.setLoginid((String) session.getAttribute("loginid"));
                    ampdto.setSystemIP(request.getRemoteAddr());

                    amputationService.insertAmputationDetailsAU(ds, ampdto, request);
                    request.setAttribute("msgSuccess", "Data Inserted Successfully");
                    target = "success";
                } else {
                    request.setAttribute("msgSuccess", "Data Inserted Successfully");
                    target = "success";
                }
            } else {
                request.setAttribute("msgFailure", "Error in Inserting the Data");
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
     *
     * @description this method is used to get the amputation details into data base for a particular person
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws org.bf.disability.Exception.SADAREMException
     */
    public ActionForward getAmputationDetails(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        DataSource ds = null;
       
        String personcode = null;
        //DataSource ds=getDataSource(request);\

        String target = null;
        try { ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
            AmputationDto ampdto = new AmputationDto();
            AmputationForm af = (AmputationForm) form;
            HttpSession session = request.getSession(true);
            // personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }



            AmputationService amputationService =
                    AmputationServiceFactory.getAmputationServiceImpl();
            ampdto = amputationService.getAmputationDetails(personcode, ds);
            BeanUtils.copyProperties(af, ampdto);
            saveToken(request);
            target = "success";
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
     *
     * @description this method is used to update the amputation details into data base for a particular person
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws java.lang.Exception
     * @throws javax.servlet.ServletException
     * @return Action Forward
     */
    public ActionForward updateAmputationDetails(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception,
            ServletException {
        DataSource ds = null;
       
        String personcode = null;
        String target = null;
        try { ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
            boolean flag = isTokenValid(request, true);
            if (flag) {

                HttpSession session = request.getSession(true);
                // personcode = (String) session.getAttribute("personcode");

                if (session.getAttribute("sadaremCodeAu") != null) {
                    personcode = (String) session.getAttribute("sadaremCodeAu");
                } else {
                    personcode = (String) session.getAttribute("personcode");
                }



                AmputationDto ampdto = new AmputationDto();
                AmputationService amputationService =
                        AmputationServiceFactory.getAmputationServiceImpl();
                ampdto = addOrUpdateCalculations(form, request);
                ampdto.setPersoncode(personcode);
                ampdto.setLoginid((String) session.getAttribute("loginid"));
                ampdto.setSystemIP(request.getRemoteAddr());
                boolean personcodecheckflag = amputationService.checkPersoncode(personcode, ds);

//            if(checkBeforeUpdationflag==0) {
                if (personcodecheckflag == false) {
                    if (ampdto.getAmputationtotal() > 0) {
                        amputationService.insertAmputationDetails(ds, ampdto, request);
                    }
                } else {
                    if (ampdto.getAmputationtotal() > 0) {

                        //  if (session.getAttribute("sadaremCodeAu") != null) {
                        //        amputationService.insertAmputationDetails(ds, ampdto, request);
                        //   }else {
                        amputationService.updateAmputationDetails(ds, ampdto, request);
                        //   }


                    } else {
                        //delete record
                        //amputationService.deleteAmputaionUpdateRecord(ds,personcode);
                        CommonDAO.changeStatusToInactiveForResetInUpdate(ds, personcode, "tblAmputation_UpperLimbAmputation_Details");
                        CommonDAO.changeStatusToInactiveForResetInUpdate(ds, personcode, "tblAmputaion_UpperLimbAmputation_Finger_Details");
                        CommonDAO.changeStatusToInactiveForResetInUpdate(ds, personcode, "tblAmputation_LowerLimbAmputation_Details");
                        CommonDAO.changeStatusToInactiveForResetInUpdate(ds, personcode, "tblAmputation_Complication_Details");
                    }
                }
            }
            target = "success";
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
     *
     * @description this method checks value for double
     * @param a
     * @param b
     * @return double
     */
    public double check(double a, double b) {
        double add;
        if (a > b) {
            add = cal(a, b);
        } else {
            add = cal(b, a);
        }
        return add;
    }

    /**
     *
     * @description this method calculates the double value
     * @param a
     * @param b
     * @return double
     */
    public double cal(double a, double b) {
        double right = a + (((90 - a) / 90) * b);
        return right;
    }

    /**
     *
     * @description this methos takes the inputs and calculate the percentage for amputation
     * @param form
     * @param request
     * @return dto
     * @throws org.bf.disability.Exception.SADAREMException
     */
    public AmputationDto addOrUpdateCalculations(ActionForm form,
            HttpServletRequest request)
            throws SADAREMDBException, SQLException {


        AmputationForm af = (AmputationForm) form;
        AmputationDto ampdto = new AmputationDto();


        int upper_fore_right = af.getUpper_fore_right();
        int upper_shoulder_right = af.getUpper_shoulder_right();
        int upper_aboveelbowupper_right = af.getUpper_aboveelbowupper_right();
        int upper_elbowlower_right = af.getUpper_elbowlower_right();
        int upper_elbowdis_right = af.getUpper_elbowdis_right();
        int upper_belowelbowupper_right = af.getUpper_belowelbowupper_right();
        int upper_belowelbowlower_right = af.getUpper_belowelbowlower_right();
        int upper_waistdis_right = af.getUpper_waistdis_right();
        int upper_handcarpel_right = af.getUpper_handcarpel_right();
        int upper_thumbCM_right = af.getUpper_thumbCM_right();
        int upper_thumbMCP_right = af.getUpper_thumbMCP_right();
        int upper_thumbIP_right = af.getUpper_thumbIP_right();

//upper left
        int upper_fore_left = af.getUpper_fore_left();
        int upper_shoulder_left = af.getUpper_shoulder_left();
        int upper_aboveelbowupper_left = af.getUpper_aboveelbowupper_left();
        int upper_elbowlower_left = af.getUpper_elbowlower_left();
        int upper_elbowdis_left = af.getUpper_elbowdis_left();
        int upper_belowelbowupper_left = af.getUpper_belowelbowupper_left();
        int upper_belowelbowlower_left = af.getUpper_belowelbowlower_left();
        int upper_waistdis_left = af.getUpper_waistdis_left();
        int upper_handcarpel_left = af.getUpper_handcarpel_left();
        int upper_thumbCM_left = af.getUpper_thumbCM_left();
        int upper_thumbMCP_left = af.getUpper_thumbMCP_left();
        int upper_thumbIP_left = af.getUpper_thumbIP_left();


//upper

        int upper_MPIndex_right = af.getUpper_MPIndex_right();

        int upper_MPMiddle_right = af.getUpper_MPMiddle_right();
        int upper_MPRing_right = af.getUpper_MPRing_right();
        int upper_MPLittle_right = af.getUpper_MPLittle_right();

        int upper_MPIndex_left = af.getUpper_MPIndex_left();
        int upper_MPMiddle_left = af.getUpper_MPMiddle_left();
        int upper_MPRing_left = af.getUpper_MPRing_left();
        int upper_MPLittle_left = af.getUpper_MPLittle_left();

        int upper_DIPIndex_right = af.getUpper_DIPIndex_right();
        int upper_DIPMiddle_right = af.getUpper_DIPMiddle_right();
        int upper_DIPRing_right = af.getUpper_DIPRing_right();
        int upper_DIPLittle_right = af.getUpper_DIPLittle_right();

        int upper_DIPIndex_left = af.getUpper_DIPIndex_left();
        int upper_DIPMiddle_left = af.getUpper_DIPMiddle_left();
        int upper_DIPRing_left = af.getUpper_DIPRing_left();
        int upper_DIPLittle_left = af.getUpper_DIPLittle_left();


        int upper_PIPIndex_right = af.getUpper_PIPIndex_right();
        int upper_PIPMiddle_right = af.getUpper_PIPMiddle_right();
        int upper_PIPRing_right = af.getUpper_PIPRing_right();
        int upper_PIPLittle_right = af.getUpper_PIPLittle_right();


        int upper_PIPIndex_left = af.getUpper_PIPIndex_left();
        int upper_PIPMiddle_left = af.getUpper_PIPMiddle_left();
        int upper_PIPRing_left = af.getUpper_PIPRing_left();
        int upper_PIPLittle_left = af.getUpper_PIPLittle_left();


//Lower right
        int lower_hind_right = af.getLower_hind_right();
        int lower_hip_right = af.getLower_hip_right();
        int lower_AKupper_right = af.getLower_AKupper_right();
        int lower_AKlower_right = af.getLower_AKlower_right();
        int lower_truknee_right = af.getLower_truknee_right();
        int lower_bk8cm_right = af.getLower_bk8cm_right();
        int lower_bklower_right = af.getLower_bklower_right();
        int lower_truankle_right = af.getLower_truankle_right();
        int lower_symes_right = af.getLower_symes_right();
        int lower_uptomid_right = af.getLower_uptomid_right();
        int lower_uptofore_right = af.getLower_uptofore_right();
        int lower_alltoe_right = af.getLower_alltoe_right();
        int lower_1sttoe_right = af.getLower_1sttoe_right();
        int lower_2ndtoe_right = af.getLower_2ndtoe_right();
        int lower_3rdtoe_right = af.getLower_3rdtoe_right();
        int lower_4thtoe_right = af.getLower_4thtoe_right();
        int lower_5thtoe_right = af.getLower_5thtoe_right();
//Lower left
        int lower_hind_left = af.getLower_hind_left();
        int lower_hip_left = af.getLower_hip_left();
        int lower_AKupper_left = af.getLower_AKupper_left();
        int lower_AKlower_left = af.getLower_AKlower_left();
        int lower_truknee_left = af.getLower_truknee_left();
        int lower_bk8cm_left = af.getLower_bk8cm_left();
        int lower_bklower_left = af.getLower_bklower_left();
        int lower_truankle_left = af.getLower_truankle_left();
        int lower_symes_left = af.getLower_symes_left();
        int lower_uptomid_left = af.getLower_uptomid_left();
        int lower_uptofore_left = af.getLower_uptofore_left();
        int lower_alltoe_left = af.getLower_alltoe_left();
        int lower_1sttoe_left = af.getLower_1sttoe_left();
        int lower_2ndtoe_left = af.getLower_2ndtoe_left();
        int lower_3rdtoe_left = af.getLower_3rdtoe_left();
        int lower_4thtoe_left = af.getLower_4thtoe_left();
        int lower_5thtoe_left = af.getLower_5thtoe_left();

//complications

        int fitting_of_prosthesis = af.getFitting_of_prosthesis();
        int proximal_joint = af.getProximal_joint();
        int neuroma = af.getNeuroma();
        int infection = af.getInfection();
        int dominant = af.getDominant();
//method level variables
        int upperrighttotal = 0;
        int upperrightlimb = 0;
        int upperrightfingers = 0;

        int upperlefttotal = 0;
        int upperleftlimb = 0;
        int upperleftfingers = 0;

        int lowerrighttotal = 0;
        int lowerrightlimb = 0;
        int lowerrighttoes = 0;

        int lowerlefttotal = 0;
        int lowerleftlimb = 0;
        int lowerlefttoes = 0;

        int complicationstotal = 0;
        double upperamputation = 0;
        double loweramputation = 0;
        double amputationresult = 0;
        double upperloweramputation = 0;
        double amputationuplofinlimb = 0;


        upperrightlimb = (upper_fore_right
                + upper_shoulder_right
                + upper_aboveelbowupper_right
                + upper_elbowlower_right
                + upper_elbowdis_right
                + upper_belowelbowupper_right
                + upper_belowelbowlower_right
                + upper_waistdis_right
                + upper_handcarpel_right);


        upperrightfingers = (upper_thumbCM_right
                + upper_thumbMCP_right
                + upper_thumbIP_right
                + upper_MPIndex_right
                + upper_MPMiddle_right
                + upper_MPRing_right
                + upper_MPLittle_right
                + upper_DIPIndex_right
                + upper_DIPMiddle_right
                + upper_DIPRing_right
                + upper_DIPLittle_right
                + upper_PIPIndex_right
                + upper_PIPMiddle_right
                + upper_PIPRing_right
                + upper_PIPLittle_right);
        upperrighttotal = upperrightlimb + upperrightfingers;


        upperleftlimb = (upper_fore_left
                + upper_shoulder_left
                + upper_aboveelbowupper_left
                + upper_elbowlower_left
                + upper_elbowdis_left
                + upper_belowelbowupper_left
                + upper_belowelbowlower_left
                + upper_waistdis_left
                + upper_handcarpel_left);


        upperleftfingers = (upper_thumbCM_left
                + upper_thumbMCP_left
                + upper_thumbIP_left
                + upper_MPIndex_left
                + upper_MPMiddle_left
                + upper_MPRing_left
                + upper_MPLittle_left
                + upper_DIPIndex_left
                + upper_DIPMiddle_left
                + upper_DIPRing_left
                + upper_DIPLittle_left
                + upper_PIPIndex_left
                + upper_PIPMiddle_left
                + upper_PIPRing_left
                + upper_PIPLittle_left);
        upperlefttotal = upperleftlimb + upperleftfingers;


        lowerrightlimb = (lower_hind_right
                + lower_hip_right
                + lower_AKupper_right
                + lower_AKlower_right
                + lower_truknee_right
                + lower_bk8cm_right
                + lower_bklower_right
                + lower_truankle_right
                + lower_symes_right
                + lower_uptomid_right
                + lower_uptofore_right);


        lowerrighttoes = (lower_alltoe_right
                + lower_1sttoe_right
                + lower_2ndtoe_right
                + lower_3rdtoe_right
                + lower_4thtoe_right
                + lower_5thtoe_right);
        lowerrighttotal = lowerrightlimb + lowerrighttoes;


        lowerleftlimb = (lower_hind_left
                + lower_hip_left
                + lower_AKupper_left
                + lower_AKlower_left
                + lower_truknee_left
                + lower_bk8cm_left
                + lower_bklower_left
                + lower_truankle_left
                + lower_symes_left
                + lower_uptomid_left
                + lower_uptofore_left);


        lowerlefttoes = (lower_alltoe_left
                + lower_1sttoe_left
                + lower_2ndtoe_left
                + lower_3rdtoe_left
                + lower_4thtoe_left
                + lower_5thtoe_left);
        lowerlefttotal = lowerleftlimb + lowerlefttoes;


        int[] limb = new int[]{upperrightlimb, upperleftlimb,
            lowerrightlimb, lowerleftlimb};
        int[] finger = new int[]{upperrightfingers, upperleftfingers,
            lowerrighttoes, lowerlefttoes};

        int flaglimb = 0;
        for (int i = 0; i < limb.length; i++) {
            for (int j = i + 1; j < limb.length; j++) {
                if (limb[i] != 0 && limb[j] != 0) {
                    flaglimb = 1;
                }
            }
        }


        int flagfinger = 0;
        if (lower_alltoe_left != 0 || lower_alltoe_right != 0) {
            flagfinger = 1;
        }

        int[] upperfingerright = new int[]{upper_thumbCM_right,
            upper_thumbMCP_right, upper_thumbIP_right, upper_MPIndex_right,
            upper_MPMiddle_right, upper_MPRing_right, upper_MPLittle_right,
            upper_DIPIndex_right, upper_DIPMiddle_right, upper_DIPRing_right,
            upper_DIPLittle_right, upper_PIPIndex_right, upper_PIPMiddle_right,
            upper_PIPRing_right, upper_PIPLittle_right};
        int[] upperfingerleft = new int[]{upper_thumbCM_left, upper_thumbMCP_left,
            upper_thumbIP_left, upper_MPIndex_left, upper_MPMiddle_left,
            upper_MPRing_left, upper_MPLittle_left, upper_DIPIndex_left,
            upper_DIPMiddle_left, upper_DIPRing_left, upper_DIPLittle_left,
            upper_PIPIndex_left, upper_PIPMiddle_left, upper_PIPRing_left,
            upper_PIPLittle_left};
        int[] lowerfingerright = new int[]{lower_1sttoe_right, lower_2ndtoe_right,
            lower_3rdtoe_right, lower_4thtoe_right, lower_5thtoe_right};
        int[] lowerfingerleft = new int[]{lower_1sttoe_left, lower_2ndtoe_left,
            lower_3rdtoe_left, lower_4thtoe_left, lower_5thtoe_left};

        for (int i = 0; i < upperfingerright.length; i++) {
            for (int j = i + 1; j < upperfingerleft.length; j++) {
                if ((upperfingerright[i] != 0 && upperfingerright[j] != 0)
                        || (upperfingerleft[i] != 0 && upperfingerleft[j] != 0)) {
                    flagfinger = 1;
                }
            }
        }
        for (int i = 0; i < upperfingerright.length; i++) {
            for (int j = 0; j < upperfingerleft.length; j++) {
                if ((upperfingerright[i] != 0 && upperfingerleft[j] != 0)) {
                    flagfinger = 1;
                }
            }
        }

        for (int i = 0; i < lowerfingerright.length; i++) {
            for (int j = i + 1; j < lowerfingerleft.length; j++) {
                if ((lowerfingerright[i] != 0 && lowerfingerright[j] != 0)
                        || (lowerfingerleft[i] != 0 && lowerfingerleft[j] != 0)) {
                    flagfinger = 1;
                }
            }
        }
        for (int i = 0; i < lowerfingerright.length; i++) {
            for (int j = 0; j < lowerfingerright.length; j++) {
                if ((lowerfingerright[i] != 0 && lowerfingerleft[j] != 0)) {
                    flagfinger = 1;
                }
            }
        }
        for (int i = 0; i < upperfingerright.length; i++) {
            for (int j = 0; j < lowerfingerright.length; j++) {
                if ((upperfingerright[i] != 0 && lowerfingerright[j] != 0)
                        || (upperfingerleft[i] != 0 && lowerfingerright[j] != 0)
                        || (upperfingerleft[i] != 0 && lowerfingerleft[j] != 0)
                        || (upperfingerright[i] != 0 && lowerfingerleft[j] != 0)) {
                    flagfinger = 1;
                }
            }
        }


        int flaglimbfinger = 0;
        for (int i = 0; i < limb.length; i++) {
            for (int j = 0; j < finger.length; j++) {
                if (limb[i] != 0 && finger[j] != 0) {
                    flaglimbfinger = 1;
                }
            }
        }



        if ((upperrighttotal > 90 && upperlefttotal > 90)
                || (upperrighttotal > 90 && upperlefttotal < 90)
                || (upperrighttotal < 90 && upperlefttotal > 90)) {
            if ((upperrighttotal > upperlefttotal)) {
                upperamputation = upperrighttotal;
            } else {
                upperamputation = upperlefttotal;
            }
        } else {
            upperamputation = check(upperrighttotal, upperlefttotal);
        }

        if ((lowerrighttotal > 90 && lowerlefttotal > 90)
                || (lowerrighttotal > 90 && lowerlefttotal < 90)
                && (lowerrighttotal < 90 || lowerlefttotal > 90)) {
            if ((lowerrighttotal > lowerlefttotal)) {
                loweramputation = lowerrighttotal;
            } else {
                loweramputation = lowerlefttotal;
            }
        } else {
            loweramputation = check(lowerrighttotal, lowerlefttotal);
        }

        if ((upperamputation > 90 && loweramputation > 90)
                || (upperamputation > 90 && loweramputation < 90)
                || (upperamputation < 90 && loweramputation > 90)) {
            if ((upperamputation > loweramputation)) {
                upperloweramputation = upperamputation;
            } else {
                upperloweramputation = loweramputation;
            }
        } else {
            upperloweramputation = check(upperamputation, loweramputation);
        }



        complicationstotal = (proximal_joint + neuroma + infection);
        if (complicationstotal >= 10) {
            complicationstotal = (10 + fitting_of_prosthesis + dominant);
        } else {
            complicationstotal = complicationstotal + fitting_of_prosthesis + dominant;
        }



        if (flagfinger == 1) {
            amputationuplofinlimb = amputationuplofinlimb + 5;
        }
        if (flaglimb == 1) {
            amputationuplofinlimb = amputationuplofinlimb + 10;
        }
        if (flaglimbfinger == 1) {
            amputationuplofinlimb = amputationuplofinlimb + 5;
        }


        amputationresult =
                upperloweramputation + amputationuplofinlimb + complicationstotal;

        if (amputationresult > 100) {
            amputationresult = 100;
        }
        af.setComplicationstotal(complicationstotal);
        af.setUpperamputation(upperamputation);
        af.setLoweramputation(loweramputation);
        af.setAmputationtotal(amputationresult);
        try {
            BeanUtils.copyProperties(ampdto, af);
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }

        HttpSession ses = request.getSession();
        ses.setAttribute("amputation", new Double(amputationresult));


        return ampdto;


    }
}






