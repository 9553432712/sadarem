/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.bf.disability.Exception.SADAREMException;

/**
 *
 * @author 484898
 */
public class CommonFiledsChecking {

    /**
     * This method is for date format
     * @param frmDate
     * @throws  org.bf.disability.Exception.SADAREMException
     * @return String
     */
    public String gettingFromDate(String frmDate) throws SADAREMException {
        String fromDate = null;

        try {

            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(frmDate);
            fromDate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fromDate;
    }

    /**
     * This method is for date format
     * @param todate
     * @throws org.bf.disability.Exception.SADAREMException
     * @return String
     */
    public String gettingToDate(String tDate) throws SADAREMException {
        String toDate = null;

        try {

            Date todate = new SimpleDateFormat("dd/mm/yyyy").parse(tDate);
            toDate = new SimpleDateFormat("mm/dd/yyyy").format(todate);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return toDate;
    }

    /**
     * This method is used for checking the gender
     * @param genderID
     * @throws org.bf.disability.Exception.SADAREMException
     * @retrun String
     */
    public String genderChecking(String genderId) throws SADAREMException {
        String genderCheckId = null;

        if (genderId != null) {

            if (genderId.equals("1")) {

                genderCheckId = "Male";

            } else if (genderId.equals("2")) {

                genderCheckId = "Female";

            } else {

                genderCheckId = "-";

            }

        } else {

            genderCheckId = "-";

        }

        return genderCheckId;

    }

    /**
     * This method is used for checking the Religion
     * @param religionId
     * @throws org.bf.disability.Exception.SADAREMException
     * @return String
     */
    public String religionChecking(String religionId) throws SADAREMException {
        String religionCheckId = null;

        if (religionId != null) {

            if (religionId.equals("1")) {

                religionCheckId = "Hindu";

            } else if (religionId.equals("2")) {

                religionCheckId = "Muslim";

            } else if (religionId.equals("3")) {

                religionCheckId = "Christian";

            } else if (religionId.equals("4")) {

                religionCheckId = "Sikh";

            } else if (religionId.equals("5")) {

                religionCheckId = "Jain";

            } else if (religionId.equals("6")) {

                religionCheckId = "Budhist";

            } else if (religionId.equals("7")) {

                religionCheckId = "Others";

            } else {

                religionCheckId = "-";

            }
        } else {

            religionCheckId = "-";

        }

        return religionCheckId;

    }

    /**
     * This method is used for checking the caste
     * @param casteId
     * @throws org.bf.disability.Exception.SADAREMException
     * @return String
     */
    public String casteChecking(String casteId) throws SADAREMException {
        String casteName = null;

        if (casteId != null) {

            if (casteId.equals("1")) {

                casteName = "ST";

            } else if (casteId.equals("2")) {

                casteName = "SC";

            } else if (casteId.equals("3")) {

                casteName = "BC";

            } else if (casteId.equals("4")) {

                casteName = "OC";

            } else if (casteId.equals("5")) {

                casteName = "Minority";

            } else if (casteId.equals("6")) {

                casteName = "NA";

            } else {

                casteName = "-";

            }

        } else {

            casteName = "-";

        }

        return casteName;

    }

    /**
     * This method is for checking RelationShip
     * @param religionId
     * @throws org.bf.disability.Exception.SADAREMException
     * @return String
     */
    public String relationShipCheck(String religionId) throws SADAREMException {
        String religionName = null;

        if (religionId != null) {

            if (religionId.equals("1")) {

                religionName = "Father";

            } else if (religionId.equals("2")) {

                religionName = "Mother";

            } else if (religionId.equals("3")) {

                religionName = "Husband";

            } else if (religionId.equals("4")) {

                religionName = "Guardian";

            } else if (religionId.equals("5")) {

                religionName = "Brother";

            } else if (religionId.equals("6")) {

                religionName = "Sister";

            } else if (religionId.equals("7")) {

                religionName = "Wife";

            } else {

                religionName = "-";

            }

        } else {

            religionName = "-";

        }

        return religionName;

    }

    /**
     * This method is used for checking the RationCard type
     * @param rationCardId
     * @throws org.bf.disability.Exception.SADAREMException
     * @return String
     */
    public String checkRationCard(String rationCardId) throws SADAREMException {
        String rationCardName = null;

        if (rationCardId != null) {

            if (rationCardId.equals("1")) {

                rationCardName = "White";

            } else if (rationCardId.equals("2")) {

                rationCardName = "Pink";

            } else if (rationCardId.equals("3")) {

                rationCardName = "Anthyodaya";

            } else if (rationCardId.equals("4")) {

                rationCardName = "Annapurna";

            } else if (rationCardId.equals("5")) {

                rationCardName = "Yellow";

            } else {

                rationCardName = "-";

            }

        } else {

            rationCardName = "-";

        }

        return rationCardName;

    }

    /**
     * This method is for gettion the disability Type
     * @param disabilityId
     * @throws org.bf.disability.Exception.SADAREMException
     * @return String
     */
    public String checkDisability(String disabilityId) throws SADAREMException {
        String disabilityName = null;

        if (disabilityId != null) {

            if (disabilityId.equals("1")) {

                disabilityName = "Locomotor";

            } else if (disabilityId.equals("2")) {

                disabilityName = "Visual Impairment";

            } else if (disabilityId.equals("3")) {

                disabilityName = "HearingImpairment";

            } else if (disabilityId.equals("4")) {

                disabilityName = "MentalRetardation";

            } else if (disabilityId.equals("5")) {

                disabilityName = "MentalIllness";

            } else if (disabilityId.equals("6")) {

                disabilityName = "MultipleDisability";

            } else {

                disabilityName = "-";

            }

        } else {

            disabilityName = "-";

        }

        return disabilityName;

    }

     /**
     * This method is for gettion the Phases
     * @param phaseId
     * @throws org.bf.disability.Exception.SADAREMException
     * @return String
     */
    public String checkPhases(String phaseId) throws SADAREMException {
        String phaseName = null;

        if (phaseId != null) {

            if (phaseId.equals("1")) {

                phaseName = "PhaseI";

            } else if (phaseId.equals("2")) {

                phaseName = "PhaseII";

            } else if (phaseId.equals("3")) {

                phaseName = "PhaseIII";

            } else if (phaseId.equals("4")) {

                phaseName = "PhaseIV";

            } else {

                phaseName = "ALL";

            }

        } else {

            phaseName = "-";

        }

        return phaseName;

    }

    public static String firstLetterAsCapital(String letter) throws SADAREMException {
        String modifiedLetter = "";
        if (letter != null) {
            String upper = letter.toUpperCase();
            modifiedLetter = upper.charAt(0) + letter.substring(1);

        }
        return modifiedLetter;
    }

}