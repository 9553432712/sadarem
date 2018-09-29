/*
 * AmputationImpl.java
 *
 * Created on January 2, 2009, 5:09 PM
 *
 */
package org.bf.disability.util;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.bean.AmputationBean;
import org.bf.disability.dao.AmputationDao;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dto.AmputationDto;
import org.bf.disability.serviceimpl.ShowCalcualationsServiceImpl;

/**
 *
 * @author Deviprasad_t
 */
public class AmputationImpl extends ShowCalcualationsServiceImpl {

    StringBuffer amputationStrBuffUE = new StringBuffer();
    StringBuffer amputationStrBuffLE = new StringBuffer();
    StringBuffer amputationStrBuffULE = new StringBuffer();
    StringBuffer amputationStrBuffCS = new StringBuffer();
    StringBuffer amputationStrBuffTot = new StringBuffer();
    StringBuffer amputationStrBuffEx = new StringBuffer();

    public void populateAmputationCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException {

        try {
            AmputationDao amputationDao = new AmputationDao();

            AmputationBean amputationBean = new AmputationBean();
            AmputationDto amputationDto = amputationDao.getAmputationDetails(personcode, dataSource);
            BeanUtils.copyProperties(amputationBean, amputationDto);
            amputationBean = amputationCalculationsLogic(amputationBean);
            request.setAttribute("amputationBean", amputationBean);
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource,sqlEx.toString(), "populateAmputationCalculations", "AmputationImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AmputationImpl", "populateAmputationCalculations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource,sqlEx.toString(), "populateAmputationCalculations", "AmputationImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AmputationImpl", "populateAmputationCalculations");
        } //end of catch block

    }

    public AmputationBean amputationCalculationsLogic(AmputationBean amputationBean) {
        int upper_fore_right = amputationBean.getUpper_fore_right();
        int upper_shoulder_right = amputationBean.getUpper_shoulder_right();
        int upper_aboveelbowupper_right = amputationBean.getUpper_aboveelbowupper_right();
        int upper_elbowlower_right = amputationBean.getUpper_elbowlower_right();
        int upper_elbowdis_right = amputationBean.getUpper_elbowdis_right();
        int upper_belowelbowupper_right = amputationBean.getUpper_belowelbowupper_right();
        int upper_belowelbowlower_right = amputationBean.getUpper_belowelbowlower_right();
        int upper_waistdis_right = amputationBean.getUpper_waistdis_right();
        int upper_handcarpel_right = amputationBean.getUpper_handcarpel_right();
        int upper_thumbCM_right = amputationBean.getUpper_thumbCM_right();
        int upper_thumbMCP_right = amputationBean.getUpper_thumbMCP_right();
        int upper_thumbIP_right = amputationBean.getUpper_thumbIP_right();

//upper left
        int upper_fore_left = amputationBean.getUpper_fore_left();
        int upper_shoulder_left = amputationBean.getUpper_shoulder_left();
        int upper_aboveelbowupper_left = amputationBean.getUpper_aboveelbowupper_left();
        int upper_elbowlower_left = amputationBean.getUpper_elbowlower_left();
        int upper_elbowdis_left = amputationBean.getUpper_elbowdis_left();
        int upper_belowelbowupper_left = amputationBean.getUpper_belowelbowupper_left();
        int upper_belowelbowlower_left = amputationBean.getUpper_belowelbowlower_left();
        int upper_waistdis_left = amputationBean.getUpper_waistdis_left();
        int upper_handcarpel_left = amputationBean.getUpper_handcarpel_left();
        int upper_thumbCM_left = amputationBean.getUpper_thumbCM_left();
        int upper_thumbMCP_left = amputationBean.getUpper_thumbMCP_left();
        int upper_thumbIP_left = amputationBean.getUpper_thumbIP_left();

        int upper1to12total = upper_fore_right
                + upper_shoulder_right
                + upper_aboveelbowupper_right
                + upper_elbowlower_right
                + upper_elbowdis_right
                + upper_belowelbowupper_right
                + upper_belowelbowlower_right
                + upper_waistdis_right
                + upper_handcarpel_right
                + upper_thumbCM_right
                + upper_thumbMCP_right
                + upper_thumbIP_right
                + upper_fore_left
                + upper_shoulder_left
                + upper_aboveelbowupper_left
                + upper_elbowlower_left
                + upper_elbowdis_left
                + upper_belowelbowupper_left
                + upper_belowelbowlower_left
                + upper_waistdis_left
                + upper_handcarpel_left
                + upper_thumbCM_left
                + upper_thumbMCP_left
                + upper_thumbIP_left;
        if (upper1to12total > 0) {
            //set Upper1to12total true
            amputationBean.setUpper1to12totalHeading("true");

        }
//upper

        int upper_MPIndex_right = amputationBean.getUpper_MPIndex_right();
        int upper_MPMiddle_right = amputationBean.getUpper_MPMiddle_right();
        int upper_MPRing_right = amputationBean.getUpper_MPRing_right();
        int upper_MPLittle_right = amputationBean.getUpper_MPLittle_right();

        int upper_MPIndex_left = amputationBean.getUpper_MPIndex_left();
        int upper_MPMiddle_left = amputationBean.getUpper_MPMiddle_left();
        int upper_MPRing_left = amputationBean.getUpper_MPRing_left();
        int upper_MPLittle_left = amputationBean.getUpper_MPLittle_left();

        if (upper_MPIndex_right > 0 || upper_MPMiddle_right > 0 || upper_MPRing_right > 0 || upper_MPLittle_right > 0 || upper_MPIndex_left > 0 || upper_MPMiddle_left > 0 || upper_MPRing_left > 0 || upper_MPLittle_left > 0) {
            //set MP true
            amputationBean.setMpJointHeading("true");
        }

        int upper_DIPIndex_right = amputationBean.getUpper_DIPIndex_right();
        int upper_DIPMiddle_right = amputationBean.getUpper_DIPMiddle_right();
        int upper_DIPRing_right = amputationBean.getUpper_DIPRing_right();
        int upper_DIPLittle_right = amputationBean.getUpper_DIPLittle_right();

        int upper_DIPIndex_left = amputationBean.getUpper_DIPIndex_left();
        int upper_DIPMiddle_left = amputationBean.getUpper_DIPMiddle_left();
        int upper_DIPRing_left = amputationBean.getUpper_DIPRing_left();
        int upper_DIPLittle_left = amputationBean.getUpper_DIPLittle_left();

        if (upper_DIPIndex_right > 0 || upper_DIPMiddle_right > 0 || upper_DIPRing_right > 0 || upper_DIPLittle_right > 0 || upper_DIPIndex_left > 0 || upper_DIPMiddle_left > 0 || upper_DIPRing_left > 0 || upper_DIPLittle_left > 0) {
            //set DIP true
            amputationBean.setDipJointHeading("true");
        }


        int upper_PIPIndex_right = amputationBean.getUpper_PIPIndex_right();
        int upper_PIPMiddle_right = amputationBean.getUpper_PIPMiddle_right();
        int upper_PIPRing_right = amputationBean.getUpper_PIPRing_right();
        int upper_PIPLittle_right = amputationBean.getUpper_PIPLittle_right();


        int upper_PIPIndex_left = amputationBean.getUpper_PIPIndex_left();
        int upper_PIPMiddle_left = amputationBean.getUpper_PIPMiddle_left();
        int upper_PIPRing_left = amputationBean.getUpper_PIPRing_left();
        int upper_PIPLittle_left = amputationBean.getUpper_PIPLittle_left();

        if (upper_PIPIndex_right > 0 || upper_PIPMiddle_right > 0 || upper_PIPRing_right > 0 || upper_PIPLittle_right > 0 || upper_PIPIndex_left > 0 || upper_PIPMiddle_left > 0 || upper_PIPRing_left > 0 || upper_PIPLittle_left > 0) {
            //set PIP true
            amputationBean.setPipJointHeading("true");
        }

//Lower right
        int lower_hind_right = amputationBean.getLower_hind_right();
        int lower_hip_right = amputationBean.getLower_hip_right();
        int lower_AKupper_right = amputationBean.getLower_AKupper_right();
        int lower_AKlower_right = amputationBean.getLower_AKlower_right();
        int lower_truknee_right = amputationBean.getLower_truknee_right();
        int lower_bk8cm_right = amputationBean.getLower_bk8cm_right();
        int lower_bklower_right = amputationBean.getLower_bklower_right();
        int lower_truankle_right = amputationBean.getLower_truankle_right();
        int lower_symes_right = amputationBean.getLower_symes_right();
        int lower_uptomid_right = amputationBean.getLower_uptomid_right();
        int lower_uptofore_right = amputationBean.getLower_uptofore_right();
        int lower_alltoe_right = amputationBean.getLower_alltoe_right();
        int lower_1sttoe_right = amputationBean.getLower_1sttoe_right();
        int lower_2ndtoe_right = amputationBean.getLower_2ndtoe_right();
        int lower_3rdtoe_right = amputationBean.getLower_3rdtoe_right();
        int lower_4thtoe_right = amputationBean.getLower_4thtoe_right();
        int lower_5thtoe_right = amputationBean.getLower_5thtoe_right();
//Lower left
        int lower_hind_left = amputationBean.getLower_hind_left();
        int lower_hip_left = amputationBean.getLower_hip_left();
        int lower_AKupper_left = amputationBean.getLower_AKupper_left();
        int lower_AKlower_left = amputationBean.getLower_AKlower_left();
        int lower_truknee_left = amputationBean.getLower_truknee_left();
        int lower_bk8cm_left = amputationBean.getLower_bk8cm_left();
        int lower_bklower_left = amputationBean.getLower_bklower_left();
        int lower_truankle_left = amputationBean.getLower_truankle_left();
        int lower_symes_left = amputationBean.getLower_symes_left();
        int lower_uptomid_left = amputationBean.getLower_uptomid_left();
        int lower_uptofore_left = amputationBean.getLower_uptofore_left();
        int lower_alltoe_left = amputationBean.getLower_alltoe_left();
        int lower_1sttoe_left = amputationBean.getLower_1sttoe_left();
        int lower_2ndtoe_left = amputationBean.getLower_2ndtoe_left();
        int lower_3rdtoe_left = amputationBean.getLower_3rdtoe_left();
        int lower_4thtoe_left = amputationBean.getLower_4thtoe_left();
        int lower_5thtoe_left = amputationBean.getLower_5thtoe_left();

//complications

        int fitting_of_prosthesis = amputationBean.getFitting_of_prosthesis();
        int proximal_joint = amputationBean.getProximal_joint();
        int neuroma = amputationBean.getNeuroma();
        int infection = amputationBean.getInfection();
        int dominant = amputationBean.getDominant();
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
        //set upper right total
        amputationBean.setUpperRightTotal(upperrighttotal);
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
        //set upper left total
        amputationBean.setUpperLeftTotal(upperlefttotal);


        if (upperrighttotal > 0 || upperlefttotal > 0) {
            //set UpperExtremity to true
            amputationBean.setUpperExtrmHeading("true");
        }
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
        //set lower right total
        amputationBean.setLowerRightTotal(lowerrighttotal);

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
        //set lower left total
        amputationBean.setLowerLeftTotal(lowerlefttotal);
        if (lowerrighttotal > 0 || lowerlefttotal > 0) {
            //set LowerExtremity to true
            amputationBean.setLowerExtrmHeading("true");
        }
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
        //set flag limb
        amputationBean.setFlagLimb(flaglimb);

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


        //set flag finger
        amputationBean.setFlagFinger(flagfinger);
        int flaglimbfinger = 0;
        for (int i = 0; i < limb.length; i++) {
            for (int j = 0; j < finger.length; j++) {
                if (limb[i] != 0 && finger[j] != 0) {
                    flaglimbfinger = 1;
                }
            }
        }

        //set flag limb finger
        amputationBean.setFlagLimbFinger(flaglimbfinger);
        if ((upperrighttotal > 90 && upperlefttotal > 90)
                || (upperrighttotal > 90 && upperlefttotal < 90)
                || (upperrighttotal < 90 && upperlefttotal > 90)) {
            if ((upperrighttotal > upperlefttotal)) {
                upperamputation = upperrighttotal;
                amputationStrBuffUE.append("Since Right Total is Greater than Left Total and greater than 90 so we should consider greater value\n");
                amputationStrBuffUE.append("UPPERLIMB AMPUTATION TOTAL : " + upperamputation);
            } else {
                upperamputation = upperlefttotal;
                amputationStrBuffUE.append("Since Left Total is greater than Right Total and greater than 90 so we should consider greater value\n");
                amputationStrBuffUE.append("UPPERLIMB AMPUTATION TOTAL : " + upperamputation);
            }
        } else {
            upperamputation = check(upperrighttotal, upperlefttotal);
            if (upperrighttotal > upperlefttotal) {
                amputationStrBuffUE.append("Since " + upperrighttotal + " is greater than " + upperlefttotal + ", the UPPERLIMB AMPUTAION TOTAL Calculation is : \n");
                amputationStrBuffUE.append(+upperrighttotal + "+(((90-" + upperrighttotal + ")/90)*" + upperlefttotal + ") = " + upperamputation + "\n");
            } else {
                amputationStrBuffUE.append("Since " + upperlefttotal + " is greater than " + upperrighttotal + ", the UPPERLIMB AMPUTAION TOTAL Calculation is : \n");
                amputationStrBuffUE.append(+upperlefttotal + "+(((90-" + upperlefttotal + ")/90)*" + upperrighttotal + ") = " + upperamputation + "\n");
            }
            amputationStrBuffUE.append("UPPERLIMB AMPUTAION TOTAL : " + upperamputation);
        }


        if ((lowerrighttotal > 90 && lowerlefttotal > 90)
                || (lowerrighttotal > 90 && lowerlefttotal < 90)
                && (lowerrighttotal < 90 || lowerlefttotal > 90)) {
            if ((lowerrighttotal > lowerlefttotal)) {
                loweramputation = lowerrighttotal;
                amputationStrBuffLE.append("Since Right Total is greater than or equal to Left Total and greater than 90 so we should consider greater value\n");
                amputationStrBuffLE.append("LOWERLIMB AMPUTATION TOTAL : " + loweramputation);
            } else {
                loweramputation = lowerlefttotal;
                amputationStrBuffLE.append("Since Left Total is greater than or equal to Right Total and greater than 90 so we should consider greater value\n");
                amputationStrBuffLE.append("LOWERLIMB AMPUTATION TOTAL : " + loweramputation);
            }
        } else {
            loweramputation = check(lowerrighttotal, lowerlefttotal);
            if (lowerrighttotal > lowerlefttotal) {
                amputationStrBuffLE.append("Since " + lowerrighttotal + " is greater than " + lowerlefttotal + ", the LOWERLIMB AMPUTATION TOTAL Calculation is : \n");
                amputationStrBuffLE.append(+lowerrighttotal + "+(((90-" + lowerrighttotal + ")/90)*" + lowerlefttotal + ") = " + loweramputation + "\n");
            } else {
                amputationStrBuffLE.append("Since " + lowerlefttotal + " is greater than " + lowerrighttotal + ", the LOWERLIMB AMPUTATION TOTAL Calculation is : \n");
                amputationStrBuffLE.append(+lowerlefttotal + "+(((90-" + lowerlefttotal + ")/90)*" + lowerrighttotal + ") = " + loweramputation + "\n");
            }
            amputationStrBuffLE.append("LOWERLIMB AMPUTATION TOTAL : " + loweramputation);
        }



        if ((upperamputation > 90 && loweramputation > 90)
                || (upperamputation > 90 && loweramputation < 90)
                || (upperamputation < 90 && loweramputation > 90)) {
            if ((upperamputation > loweramputation)) {
                upperloweramputation = upperamputation;
                amputationStrBuffULE.append("Since UpperLimbAmputation Total is greater than or equal to LowerLimbAmputation\n");
                amputationStrBuffULE.append("Total and greater than 90 so we should consider greater value\n");

                amputationStrBuffULE.append("UPPER AND LOWER LIMB AMPUTATION TOTAL : " + upperloweramputation);
            } else {
                upperloweramputation = loweramputation;
                amputationStrBuffULE.append("Since LowerLimbAmputation Total is greater than or equal to UpperLimbAmputation\n");
                amputationStrBuffULE.append("Total and greater than 90 so we should consider greater value\n");

                amputationStrBuffULE.append("UPPER AND LOWER LIMB AMPUTATION TOTAL : " + upperloweramputation);
            }
        } else {
            upperloweramputation = check(upperamputation, loweramputation);
            if (upperamputation > loweramputation) {
                amputationStrBuffULE.append("Since " + upperamputation + " is greater than " + loweramputation + "\n");
                amputationStrBuffULE.append("the UPPER AND LOWER LIMB AMPUTATION TOTAL Calculation is : \n");
                amputationStrBuffULE.append(+upperamputation + "+(((90-" + upperamputation + ")/90)*" + loweramputation + ") = " + upperloweramputation + "\n");
            } else {
                amputationStrBuffULE.append("Since " + loweramputation + " is greater than " + upperamputation + "\n");
                amputationStrBuffULE.append("the UPPER AND LOWER LIMB AMPUTATION TOTAL Calculation is : \n");
                amputationStrBuffULE.append(+loweramputation + "+(((90-" + loweramputation + ")/90)*" + upperamputation + ") = " + upperloweramputation + "\n");
            }
            amputationStrBuffULE.append("UPPER AND LOWER LIMB AMPUTATION TOTAL : " + upperloweramputation);
        }




        complicationstotal = (proximal_joint + neuroma + infection);

        if (complicationstotal >= 10) {

            complicationstotal = (10 + fitting_of_prosthesis + dominant);
            amputationStrBuffCS.append("Since the Total of (3.3.2 + 3.3.3 + 3.3.4) is greater than 10 \n");
            amputationStrBuffCS.append("we should consider it as 10 Only\n");
            amputationStrBuffCS.append("Complications Total is  10 + (3.3.1 + 3.3.5) = 10 + " + (fitting_of_prosthesis + dominant) + " = " + complicationstotal + "\n");
        } else {
            amputationStrBuffCS.append("(3.3.2 + 3.3.3 + 3.3.4) = " + complicationstotal + "\n");
            int complicationstotallessthan10 = complicationstotal;
            complicationstotal = complicationstotal + fitting_of_prosthesis + dominant;
            amputationStrBuffCS.append("Complications Total is  (3.3.1 + 3.3.2 + 3.3.3 + 3.3.4 + 3.3.5) = " + complicationstotallessthan10 + " + " + (fitting_of_prosthesis + dominant) + " = " + complicationstotal + "\n");
        }



        if (flagfinger == 1) {
            amputationuplofinlimb = amputationuplofinlimb + 5;
            amputationStrBuffEx.append("Since More than One Finger Amputation is involved we should Add 5% Extra\n");
        }

        if (flaglimb == 1) {
            amputationuplofinlimb = amputationuplofinlimb + 10;
            amputationStrBuffEx.append("Since More than One Limb Amputation is involved we should Add 10% Extra\n");
        }
        if (flaglimbfinger == 1) {
            amputationuplofinlimb = amputationuplofinlimb + 5;
            amputationStrBuffEx.append("Since Both Limb and Finger Amputation is involved we should Add 5% Extra\n");
        }

        amputationStrBuffEx.append("EXTRAS = " + amputationuplofinlimb + "\n");
        amputationresult =
                upperloweramputation + amputationuplofinlimb + complicationstotal;
        amputationStrBuffTot.append("TOTAL AMPUTATION  = (UPPER AND LOWER LIMB AMPUTATION TOTAL +\n");
        amputationStrBuffTot.append("EXTRAS + COMPLICATIONS) = \n");
        amputationStrBuffTot.append(+upperloweramputation + " + " + amputationuplofinlimb + " + " + complicationstotal + " = " + amputationresult + "\n");
        if (amputationresult > 100) {
            amputationresult = 100;
            amputationStrBuffTot.append("Since Total Amputation is greater than 100 we should take it as 100 Only");
        }

        if (complicationstotal > 0) {
            //set complications true
            amputationBean.setComplicationsHeading("true");
        }

        amputationBean.setComplicationstotal(complicationstotal);
        amputationBean.setUpperamputation(upperamputation);
        amputationBean.setLoweramputation(loweramputation);
        amputationBean.setAmputationtotal(amputationresult);


        amputationBean.setAmputationStrBuffUE(amputationStrBuffUE);
        amputationBean.setAmputationStrBuffLE(amputationStrBuffLE);
        amputationBean.setAmputationStrBuffULE(amputationStrBuffULE);
        amputationBean.setAmputationStrBuffCS(amputationStrBuffCS);
        amputationBean.setAmputationStrBuffTot(amputationStrBuffTot);
        amputationBean.setAmputationStrBuffEx(amputationStrBuffEx);
        return amputationBean;
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
}
