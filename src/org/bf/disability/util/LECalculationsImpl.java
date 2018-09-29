/*
 * LECalculationsImpl.java
 *
 * Created on January 2, 2009, 5:08 PM
 *
 */
package org.bf.disability.util;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.bean.LowerExtremityBean;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.LowerExtremityDAO;
import org.bf.disability.dto.LowerExtremityDTO;
import org.bf.disability.serviceimpl.ShowCalcualationsServiceImpl;

/**
 *
 * @author Deviprasad_t
 */
public class LECalculationsImpl extends ShowCalcualationsServiceImpl {

    private int input1;
    private double extradata;
    private double right;
    private double lowerextrimityright;
    private double lowerextrimityleft;
    private double mcright;
    private double mcleft;
    private double shortininginches;
    private double add;
    private double mobilitycomponentright;
    private double mobilitycomponentleft;
    private double mobilitycomponent;
    private double lowerwithoutextra;
    private double lowerextremity;
    private double lowerextremitytotal;
    StringBuffer romrightcalculation = new StringBuffer();
    StringBuffer romTotalCalculation = new StringBuffer();
    StringBuffer romkneerightleftcalculation = new StringBuffer();
    StringBuffer romAnklecalculation = new StringBuffer();
    StringBuffer romTotalRightcalculation = new StringBuffer();
    StringBuffer msTotal = new StringBuffer();
    StringBuffer totalRightCalculation = new StringBuffer();
    ArrayList mstotal = new ArrayList();

    public void populateLowerExtremityCalculations(DataSource datasource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException {
        try {
            LowerExtremityDAO lowerExtremityDAO = new LowerExtremityDAO();
            LowerExtremityBean lowerExtremityBean = new LowerExtremityBean();

            LowerExtremityDTO lowerExtremityDTO = lowerExtremityDAO.getLowerExtremityDetails(datasource, personcode);
            BeanUtils.copyProperties(lowerExtremityBean, lowerExtremityDTO);

            lowerExtremityBean = lowerExtremityCalculationsLogic(lowerExtremityBean);

            request.setAttribute("lowerExtremityBean", lowerExtremityBean);
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "populateLowerExtremityCalculations", "LECalculationsImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LECalculationsImpl", "populateLowerExtremityCalculations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "populateLowerExtremityCalculations", "LECalculationsImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LECalculationsImpl", "populateLowerExtremityCalculations");
        } //end of catch block
    }

    public LowerExtremityBean lowerExtremityCalculationsLogic(LowerExtremityBean lowerExtremityBean) {


        int romhipflexionextensionright = Integer.parseInt(lowerExtremityBean.getRomhipflexionextensionright());
        int romhiprotationright = Integer.parseInt(lowerExtremityBean.getRomhiprotationright());
        int romhipabductionadductionright = Integer.parseInt(lowerExtremityBean.getRomhipabductionadductionright());
        int romhipflexionextensionleft = Integer.parseInt(lowerExtremityBean.getRomhipflexionextensionleft());
        int romhiprotationleft = Integer.parseInt(lowerExtremityBean.getRomhiprotationleft());
        int romhipabductionadductionleft = Integer.parseInt(lowerExtremityBean.getRomhipabductionadductionleft());
        int romkneeflexionextensionright = Integer.parseInt(lowerExtremityBean.getRomkneeflexionextensionright());
        int romkneeflexionextensionleft = Integer.parseInt(lowerExtremityBean.getRomkneeflexionextensionleft());
        int romankledorsiflexionpalmarflexionright = Integer.parseInt(lowerExtremityBean.getRomankledorsiflexionpalmarflexionright());
        int romankleinversioneversionright = Integer.parseInt(lowerExtremityBean.getRomankleinversioneversionright());
        int romankledorsiflexionpalmarflexionleft = Integer.parseInt(lowerExtremityBean.getRomankledorsiflexionpalmarflexionleft());
        int romankleinversioneversionleft = Integer.parseInt(lowerExtremityBean.getRomankleinversioneversionleft());
        int mshipflexormusclesright = Integer.parseInt(lowerExtremityBean.getMshipflexormusclesright());
        int mshipextensormusclesright = Integer.parseInt(lowerExtremityBean.getMshipextensormusclesright());
        int mshiprotatormusclesright = Integer.parseInt(lowerExtremityBean.getMshiprotatormusclesright());
        int mshipabductormusclesright = Integer.parseInt(lowerExtremityBean.getMshipabductormusclesright());
        int mshipadductormusclesright = Integer.parseInt(lowerExtremityBean.getMshipadductormusclesright());
        int mshipflexormusclesleft = Integer.parseInt(lowerExtremityBean.getMshipflexormusclesleft());
        int mshipextensormusclesleft = Integer.parseInt(lowerExtremityBean.getMshipextensormusclesleft());
        int mshiprotatormusclesleft = Integer.parseInt(lowerExtremityBean.getMshiprotatormusclesleft());
        int mshipabductormusclesleft = Integer.parseInt(lowerExtremityBean.getMshipabductormusclesleft());
        int mshipadductormusclesleft = Integer.parseInt(lowerExtremityBean.getMshipadductormusclesleft());
        int mskneeflexormusclesright = Integer.parseInt(lowerExtremityBean.getMskneeflexormusclesright());
        int mskneeextensormusclesright = Integer.parseInt(lowerExtremityBean.getMskneeextensormusclesright());
        int mskneeflexormusclesleft = Integer.parseInt(lowerExtremityBean.getMskneeflexormusclesleft());
        int mskneeextensormusclesleft = Integer.parseInt(lowerExtremityBean.getMskneeextensormusclesleft());
        int msankleplanterflexormusclesright = Integer.parseInt(lowerExtremityBean.getMsankleplanterflexormusclesright());
        int msankledorsiflexormusclesright = Integer.parseInt(lowerExtremityBean.getMsankledorsiflexormusclesright());
        int msankleinvertormusclesright = Integer.parseInt(lowerExtremityBean.getMsankleinvertormusclesright());
        int msankleevertormusclesright = Integer.parseInt(lowerExtremityBean.getMsankleevertormusclesright());
        int msankleplanterflexormusclesleft = Integer.parseInt(lowerExtremityBean.getMsankleplanterflexormusclesleft());
        int msankledorsiflexormusclesleft = Integer.parseInt(lowerExtremityBean.getMsankledorsiflexormusclesleft());
        int msankleinvertormusclesleft = Integer.parseInt(lowerExtremityBean.getMsankleinvertormusclesleft());
        int msankleevertormusclesleft = Integer.parseInt(lowerExtremityBean.getMsankleevertormusclesleft());
        double shortening = Double.parseDouble(lowerExtremityBean.getShortening());

        int Deformity = Integer.parseInt(lowerExtremityBean.getDeformity());


        int Pain = Integer.parseInt(lowerExtremityBean.getPain());
        int Loss_of_Function = Integer.parseInt(lowerExtremityBean.getLoss_of_Function());
        int Complications = Integer.parseInt(lowerExtremityBean.getComplications());
        int working_on_plane = Integer.parseInt(lowerExtremityBean.getWorking_on_plane());
        int working_on_slope = Integer.parseInt(lowerExtremityBean.getWorking_on_slope());
        int working_on_stairs = Integer.parseInt(lowerExtremityBean.getWorking_on_stairs());
        int Standing = Integer.parseInt(lowerExtremityBean.getStanding_on_both_legs());
        int Standing_on_effected = Integer.parseInt(lowerExtremityBean.getStanding_on_effected());
        int Kneeling = Integer.parseInt(lowerExtremityBean.getKneeling());
        int Squatting = Integer.parseInt(lowerExtremityBean.getSquatting_on_floor());
        int Sitting = Integer.parseInt(lowerExtremityBean.getSitting_cross_leg());
        int Taking = Integer.parseInt(lowerExtremityBean.getTaking_turns());

        /*
         * calculating the Range of motion at rightside and leftside.
         *
         */



        float rhflexionextensionright = (((140 - romhipflexionextensionright) * 100) / 140);
        if (String.valueOf(romhipflexionextensionright) != "" && romhipflexionextensionright != 140) {
            String staticformula = "(((MinRomhipFlexionExtensionRight-romhipflexionextensionright)*100)/MinRomhipFlexionExtensionRight)";
            romrightcalculation = formula(140, romhipflexionextensionright, rhflexionextensionright, staticformula);
            lowerExtremityBean.setRhflexrightcalculations(romrightcalculation);
        }



        float rhrotationright = (((90 - romhiprotationright) * 100) / 90);
        if (String.valueOf(romhiprotationright) != "" && romhiprotationright != 90) {
            String staticformula = "(((MinRomhipRotationRight-romhiprotationright)*100)/MinRomhipRotationRight)";
            romrightcalculation = formula(90, romhiprotationright, rhrotationright, staticformula);
            lowerExtremityBean.setRhrotationculations(romrightcalculation);
        }


        float rhabductionadductionright = (((90 - romhipabductionadductionright) * 100) / 90);
        if (String.valueOf(romhipabductionadductionright) != "" && romhipabductionadductionright != 90) {
            String staticformula = "(((MinRomhipAbductionAdductionRight-romhipabductionadductionright)*100)/MinRomhipAbductionAdductionRight)";
            romrightcalculation = formula(90, romhipabductionadductionright, rhabductionadductionright, staticformula);
            lowerExtremityBean.setRhabductionadductioncalculation(romrightcalculation);
        }

        double romhipright = ((rhflexionextensionright + rhrotationright + rhabductionadductionright) / 3) * 0.3;
        if (romhipright != 0) {
            String staticformula = "((RomHipFlexionExtensionRight+RomHipRotationRight+RomHipAbductionadductionRight)/3)*0.3";
            romTotalCalculation = formulaforRomTotalRightIndividual(rhflexionextensionright, rhrotationright, rhabductionadductionright, romhipright, staticformula);
            lowerExtremityBean.setRomhiptotalrightcalculation(romTotalCalculation);
        }


        float rhflexionextensionleft = (((140 - romhipflexionextensionleft) * 100) / 140);
        if (String.valueOf(romhipflexionextensionleft) != "" && romhipflexionextensionleft != 140) {
            String staticformula = "(((MinRomhipFlexionExtensionLeft-romhipflexionextensionleft)*100)/MinRomhipFlexionExtensionLeft)";
            romrightcalculation = formula(140, romhipflexionextensionleft, rhflexionextensionleft, staticformula);
            lowerExtremityBean.setRhflexleftcalculations(romrightcalculation);
        }

        float rhrotationleft = (((90 - romhiprotationleft) * 100) / 90);
        if (String.valueOf(romhiprotationleft) != "" && romhiprotationleft != 90) {
            String staticformula = "(((MinRomhipRotationLeft-romhiprotationleft)*100)/MinRomhipRotationLeft)";
            romrightcalculation = formula(90, romhiprotationleft, rhrotationleft, staticformula);
            lowerExtremityBean.setRhrotationleftculations(romrightcalculation);
        }
        float rhabductionadductionleft = (((90 - romhipabductionadductionleft) * 100) / 90);
        if (String.valueOf(romhipabductionadductionleft) != "" && romhipabductionadductionleft != 90) {
            String staticformula = "(((MinRomhipAbductionAdductionLeft-romhipabductionadductionleft)*100)/MinRomhipAbductionAdductionLeft)";
            romrightcalculation = formula(90, romhipabductionadductionleft, rhabductionadductionleft, staticformula);
            lowerExtremityBean.setRhabductionadductionleftcalculation(romrightcalculation);
        }

        double romhipleft = ((rhflexionextensionleft + rhrotationleft + rhabductionadductionleft) / 3) * 0.3;
        if (romhipleft != 0) {
            String staticformula = "((RomHipFlexionExtensionLeft+RomHipRotationLeft+RomHipAbductionadductionLeft)/3)*0.3";
            romTotalCalculation = formulaforRomTotalRightIndividual(rhflexionextensionleft, rhrotationleft, rhabductionadductionleft, romhipleft, staticformula);
            lowerExtremityBean.setRomhiptotalleftcalculation(romTotalCalculation);
        }
        if (romhipright != 0) {
            lowerExtremityBean.setRomhipright(true);
        }
        if (romhipleft != 0) {
            lowerExtremityBean.setRomhipleft(true);
        }

        double rkflexionextensionright = (((125 - romkneeflexionextensionright) * 100) / 125) * 0.3;
        if (String.valueOf(romkneeflexionextensionright) != "" && romkneeflexionextensionright != 125) {
            String staticformula = "(((MinRomKneeFlexionExtentionRight-romkneeflexionextensionright)*100)/MinRomKneeFlexionExtentionRight)";
            romkneerightleftcalculation = formulaforRomKneeJoint(125, romkneeflexionextensionright, rkflexionextensionright, staticformula);
            lowerExtremityBean.setRomkneerightcalulation(romrightcalculation);
        }
        double rkflexionextensionleft = (((125 - romkneeflexionextensionleft) * 100) / 125) * 0.3;
        if (String.valueOf(romkneeflexionextensionleft) != "" && romkneeflexionextensionleft != 125) {
            String staticformula = "(((MinRomKneeFlexionExtentionLeft-romkneeflexionextensionleft)*100)/MinRomKneeFlexionExtentionLeft)";
            romkneerightleftcalculation = formulaforRomKneeJoint(125, romkneeflexionextensionleft, rkflexionextensionleft, staticformula);
            lowerExtremityBean.setRomkneeleftcalculation(romrightcalculation);
        }



        float radorsiflexionpalmarflexionright = (((70 - romankledorsiflexionpalmarflexionright) * 100) / 70);
        if (String.valueOf(romankledorsiflexionpalmarflexionright) != "" && romankledorsiflexionpalmarflexionright != 70) {
            String staticformula = "(((MinAnkleDorsiflexionPalmarflexionRight-romankledorsiflexionpalmarflexionright)*100)/MinAnkleDorsiflexionPalmarflexionRight)";
            romrightcalculation = formula(70, romankledorsiflexionpalmarflexionright, radorsiflexionpalmarflexionright, staticformula);
            lowerExtremityBean.setRomankledorflexionrightcalulation(romrightcalculation);
        }



        float rainversioneversionright = (((60 - romankleinversioneversionright) * 100) / 60);
        if (String.valueOf(romankleinversioneversionright) != "" && romankleinversioneversionright != 60) {
            String staticformula = "(((MinAnkleInversionEversionRight-romankleinversioneversionright)*100)/MinAnkleInversionEversionRight)";
            romrightcalculation = formula(60, romankleinversioneversionright, rainversioneversionright, staticformula);
            lowerExtremityBean.setRomankleinversionrightcalulation(romrightcalculation);
        }



        double romankleright = ((radorsiflexionpalmarflexionright + rainversioneversionright) / 2) * 0.3;
        if (romankleright != 0) {
            String staticformula = "((RomDorsiflexionpalmarflexionRight+RomInversionEversionRight)/2)*0.3";
            romAnklecalculation = formulaforRomTotalAnkle(radorsiflexionpalmarflexionright, rainversioneversionright, romankleright, staticformula);
            lowerExtremityBean.setRomankletotalrightcalulation(romAnklecalculation);
        }

        float radorsiflexionpalmarflexionleft = (((70 - romankledorsiflexionpalmarflexionleft) * 100) / 70);
        if (String.valueOf(romankledorsiflexionpalmarflexionleft) != "" && romankledorsiflexionpalmarflexionleft != 70) {
            String staticformula = "(((MinAnkleDorsiflexionPalmarflexionLeft-romankledorsiflexionpalmarflexionleft)*100)/MinAnkleDorsiflexionPalmarflexionLeft)";
            romrightcalculation = formula(70, romankledorsiflexionpalmarflexionleft, radorsiflexionpalmarflexionleft, staticformula);
            lowerExtremityBean.setRomankledorflexionleftcalulation(romrightcalculation);
        }



        float rainversioneversionleft = (((60 - romankleinversioneversionleft) * 100) / 60);
        if (String.valueOf(romankledorsiflexionpalmarflexionleft) != "" && romankleinversioneversionleft != 60) {
            String staticformula = "(((MinAnkleInversionEversionLeft-romankleinversioneversionleft)*100)/MinAnkleInversionEversionLeft)";
            romrightcalculation = formula(60, romankleinversioneversionleft, rainversioneversionleft, staticformula);
            lowerExtremityBean.setRomankleinversionleftcalulation(romrightcalculation);
        }
        double romankleleft = ((radorsiflexionpalmarflexionleft + rainversioneversionleft) / 2) * 0.3;
        if (romankleleft != 0) {
            String staticformula = "((RomDorsiflexionpalmarflexionLeft+RomInversionEversionLeft)/2)*0.3";
            romAnklecalculation = formulaforRomTotalAnkle(radorsiflexionpalmarflexionleft, rainversioneversionleft, romankleleft, staticformula);
            lowerExtremityBean.setRomankletotalleftcalulation(romAnklecalculation);
        }

        double romright = (romhipright + rkflexionextensionright + romankleright);
        if (romright != 0) {
            String staticformula = "(romhipright+rkflexionextensionright+romankleright)";
            romTotalRightcalculation = formulaforRomTotalRight(romhipright, rkflexionextensionright, romankleright, romright, staticformula);
            lowerExtremityBean.setRomtotalrightcalulation(romTotalRightcalculation);
        }
        double romleft = (romhipleft + rkflexionextensionleft + romankleleft);
        if (romleft != 0) {
            String staticformula = "(romhipleft+rkflexionextensionleft+romankleleft)";
            romTotalRightcalculation = formulaforRomTotalRight(romhipleft, rkflexionextensionleft, romankleleft, romleft, staticformula);
            lowerExtremityBean.setRomtotalleftcalulation(romTotalRightcalculation);
        }
        if (romright != 0 || romleft != 0) {
            lowerExtremityBean.setCheckforrom(true);
        }

        /*
         *calculating the presence of complications means extra value.
         *
         */

        int extra = Deformity + Pain + Loss_of_Function + Complications;
        if (extra != 0) {
            mstotal.removeAll(mstotal);
            mstotal.add(new Integer(Deformity));
            mstotal.add(new Integer(Pain));
            mstotal.add(new Integer(Loss_of_Function));
            mstotal.add(new Integer(Complications));
            String staticformula = "(Deformity+Pain+Loss_of_Function+Complications)";
            msTotal = formulaMuselStrength(mstotal, 0, staticformula, extra);
            lowerExtremityBean.setExtracalculation(msTotal);
        }



        if (shortening <= 0.5) {
            shortininginches = 0;
        } else {
            shortininginches = (shortening - 0.5) * 8;
        }
        if (shortening != 0) {
            StringBuffer shortningcalculation = new StringBuffer();
            shortningcalculation.append("Shortening is up to 0.5 nill and next 0.5 it will add 4% =");
            if (shortening <= 0.5) {
                shortningcalculation.append("Shortening is lessthen 0.5 so its value ");
                shortningcalculation.append("(" + shortening + "<=0.5)");
                shortningcalculation.append("= 0");
            } else {
                shortningcalculation.append("(Shortening value-0.5)*8 = ");
                shortningcalculation.append("(" + shortening + "-0.5)*8");
                shortningcalculation.append(" = ");
                shortningcalculation.append(shortininginches);

            }
            lowerExtremityBean.setShortningcalculation(shortningcalculation);
        }



        /*
         *calculating the Stability component value.
         */


        int stability = working_on_plane + working_on_slope + working_on_stairs + Standing + Standing_on_effected + Kneeling + Squatting + Sitting + Taking;
        if (stability != 0) {
            mstotal.removeAll(mstotal);
            mstotal.add(new Integer(working_on_plane));
            mstotal.add(new Integer(working_on_slope));
            mstotal.add(new Integer(working_on_stairs));
            mstotal.add(new Integer(Standing));
            mstotal.add(new Integer(Standing_on_effected));
            mstotal.add(new Integer(Kneeling));
            mstotal.add(new Integer(Squatting));
            mstotal.add(new Integer(Sitting));
            mstotal.add(new Integer(Taking));
            String staticformula = "(working_on_plane+working_on_slope+working_on_stairs+Standing+Standing_on_effected+Kneeling+Squatting+Sitting+Taking)";
            msTotal = formulaMuselStrength(mstotal, 0, staticformula, stability);
            lowerExtremityBean.setStabilitycalculation(msTotal);
        }



        /*
         *fetch the muscle strength values from LowerExtremityForm.
         *and calculating the muscle strength right and left.
         *
         */
        int mshflexormusclesright = msMethod(mshipflexormusclesright);
        if (mshflexormusclesright != 5) {
            lowerExtremityBean.setMshflexormusclesrightcalculation(String.valueOf(mshflexormusclesright));
        }
        int mshextensormusclesright = msMethod(mshipextensormusclesright);
        if (mshextensormusclesright != 5) {
            lowerExtremityBean.setMshextensormusclesrightcalculation(String.valueOf(mshextensormusclesright));
        }
        int mshrotatormusclesright = msMethod(mshiprotatormusclesright);
        if (mshrotatormusclesright != 5) {
            lowerExtremityBean.setMshrotatormusclesrightcalculation(String.valueOf(mshrotatormusclesright));
        }
        int mshabductormusclesright = msMethod(mshipabductormusclesright);
        if (mshabductormusclesright != 5) {
            lowerExtremityBean.setMshabductormusclesrightcalculation(String.valueOf(mshabductormusclesright));
        }
        int mshadductormusclesright = msMethod(mshipadductormusclesright);
        if (mshadductormusclesright != 5) {
            lowerExtremityBean.setMshadductormusclesrightcalculation(String.valueOf(mshadductormusclesright));
        }
        int mshflexormusclesleft = msMethod(mshipflexormusclesleft);
        if (mshflexormusclesleft != 5) {
            lowerExtremityBean.setMshflexormusclesleftcalculation(String.valueOf(mshflexormusclesleft));
        }
        int mshextensormusclesleft = msMethod(mshipextensormusclesleft);
        if (mshextensormusclesleft != 5) {
            lowerExtremityBean.setMshextensormusclesleftcalculation(String.valueOf(mshextensormusclesleft));
        }
        int mshrotatormusclesleft = msMethod(mshiprotatormusclesleft);
        if (mshrotatormusclesleft != 5) {
            lowerExtremityBean.setMshrotatormusclesleftcalculation(String.valueOf(mshrotatormusclesleft));
        }
        int mshabductormusclesleft = msMethod(mshipabductormusclesleft);
        if (mshabductormusclesleft != 5) {
            lowerExtremityBean.setMshabductormusclesleftcalculation(String.valueOf(mshabductormusclesleft));
        }
        int mshadductormusclesleft = msMethod(mshipadductormusclesleft);
        if (mshadductormusclesleft != 5) {
            lowerExtremityBean.setMshadductormusclesleftcalculation(String.valueOf(mshadductormusclesleft));
        }
        int mskflexormusclesright = msMethod(mskneeflexormusclesright);
        if (mskflexormusclesright != 5) {
            lowerExtremityBean.setMskflexormusclesrightcalculation(String.valueOf(mskflexormusclesright));
        }
        int mskextensormusclesright = msMethod(mskneeextensormusclesright);
        if (mskextensormusclesright != 5) {
            lowerExtremityBean.setMskextensormusclesrightcalculation(String.valueOf(mskextensormusclesright));
        }
        int mskflexormusclesleft = msMethod(mskneeflexormusclesleft);
        if (mskflexormusclesleft != 5) {
            lowerExtremityBean.setMskflexormusclesleftcalculation(String.valueOf(mskflexormusclesleft));
        }
        int mskextensormusclesleft = msMethod(mskneeextensormusclesleft);
        if (mskextensormusclesleft != 5) {
            lowerExtremityBean.setMskextensormusclesleftcalculation(String.valueOf(mskextensormusclesleft));
        }
        int msaplanterflexormusclesright = msMethod(msankleplanterflexormusclesright);
        if (msaplanterflexormusclesright != 5) {
            lowerExtremityBean.setMsaplanterflexormusclesrightcalculation(String.valueOf(msaplanterflexormusclesright));
        }
        int msadorsiflexormusclesright = msMethod(msankledorsiflexormusclesright);
        if (msadorsiflexormusclesright != 5) {
            lowerExtremityBean.setMsadorsiflexormusclesrightcalculation(String.valueOf(msadorsiflexormusclesright));
        }
        int msainvertormusclesright = msMethod(msankleinvertormusclesright);
        if (msainvertormusclesright != 5) {
            lowerExtremityBean.setMsainvertormusclesrightcalculation(String.valueOf(msainvertormusclesright));
        }
        int msaevertormusclesright = msMethod(msankleevertormusclesright);
        if (msaevertormusclesright != 5) {
            lowerExtremityBean.setMsaevertormusclesrightcalculation(String.valueOf(msaevertormusclesright));
        }
        int msaplanterflexormusclesleft = msMethod(msankleplanterflexormusclesleft);
        if (msaplanterflexormusclesleft != 5) {
            lowerExtremityBean.setMsaplanterflexormusclesleftcalculation(String.valueOf(msaplanterflexormusclesleft));
        }
        int msadorsiflexormusclesleft = msMethod(msankledorsiflexormusclesleft);
        if (msadorsiflexormusclesleft != 5) {
            lowerExtremityBean.setMsadorsiflexormusclesleftcalculation(String.valueOf(msadorsiflexormusclesleft));
        }
        int msainvertormusclesleft = msMethod(msankleinvertormusclesleft);
        if (msainvertormusclesleft != 5) {
            lowerExtremityBean.setMsainvertormusclesleftcalculation(String.valueOf(msainvertormusclesleft));
        }
        int msaevertormusclesleft = msMethod(msankleevertormusclesleft);
        if (msaevertormusclesleft != 5) {
            lowerExtremityBean.setMsaevertormusclesleftcalculation(String.valueOf(msaevertormusclesleft));
        }



        double hipright = (((mshflexormusclesright + mshextensormusclesright + mshrotatormusclesright + mshabductormusclesright + mshadductormusclesright) / 5) * 0.3);

        if (hipright != 0) {
            mstotal.removeAll(mstotal);
            mstotal.add(new Integer(mshflexormusclesright));
            mstotal.add(new Integer(mshextensormusclesright));
            mstotal.add(new Integer(mshrotatormusclesright));
            mstotal.add(new Integer(mshabductormusclesright));
            mstotal.add(new Integer(mshadductormusclesright));
            String staticformula = "(((mshflexormusclesright+ mshextensormusclesright+mshrotatormusclesright+mshabductormusclesright+mshadductormusclesright)/5)*0.3)";
            msTotal = formulaMuselStrength(mstotal, 5, staticformula, hipright);
            lowerExtremityBean.setMshiptotalrightcalculation(msTotal);
        }

        double kneeright = (((mskflexormusclesright + mskextensormusclesright) / 2) * 0.3);
        if (kneeright != 0) {
            mstotal.removeAll(mstotal);
            mstotal.add(new Integer(mskflexormusclesright));
            mstotal.add(new Integer(mskextensormusclesright));
            String staticformula = "(((mskflexormusclesright+mskextensormusclesright)/2)*0.3)";
            msTotal = formulaMuselStrength(mstotal, 2, staticformula, kneeright);
            lowerExtremityBean.setMskneerightcalulation(msTotal);
        }

        double ankleright = (((msaplanterflexormusclesright + msadorsiflexormusclesright + msainvertormusclesright + msaevertormusclesright) / 4) * 0.3);
        if (ankleright != 0) {
            mstotal.removeAll(mstotal);
            mstotal.add(new Integer(msaplanterflexormusclesright));
            mstotal.add(new Integer(msadorsiflexormusclesright));
            mstotal.add(new Integer(msainvertormusclesright));
            mstotal.add(new Integer(msaevertormusclesright));
            String staticformula = "(((msaplanterflexormusclesright+msadorsiflexormusclesright+msainvertormusclesright+msaevertormusclesright)/4)*0.3)";
            msTotal = formulaMuselStrength(mstotal, 4, staticformula, ankleright);
            lowerExtremityBean.setMsankletotalrightcalulation(msTotal);
        }

        double ms_right = hipright + kneeright + ankleright;
        if (ms_right != 0) {
            String staticformula = "hipright+kneeright+ankleright";
            romTotalRightcalculation = formulaforRomTotalRight(hipright, kneeright, ankleright, ms_right, staticformula);
            lowerExtremityBean.setMstotalrightcalulation(romTotalRightcalculation);
        }
        double hipleft = (((mshflexormusclesleft + mshextensormusclesleft + mshrotatormusclesleft + mshabductormusclesleft + mshadductormusclesleft) / 5) * 0.3);
        if (hipleft != 0) {
            mstotal.removeAll(mstotal);
            mstotal.add(new Integer(mshflexormusclesleft));
            mstotal.add(new Integer(mshextensormusclesleft));
            mstotal.add(new Integer(mshrotatormusclesleft));
            mstotal.add(new Integer(mshabductormusclesleft));
            mstotal.add(new Integer(mshadductormusclesleft));
            String staticformula = "(((mshflexormusclesleft+ mshextensormusclesleft+mshrotatormusclesleft+mshabductormusclesleft+mshadductormusclesleft)/5)*0.3)";
            msTotal = formulaMuselStrength(mstotal, 5, staticformula, hipleft);
            lowerExtremityBean.setMshiptotalleftcalculation(msTotal);
        }



        double kneeleft = (((mskflexormusclesleft + mskextensormusclesleft) / 2) * 0.3);
        if (kneeleft != 0) {
            mstotal.removeAll(mstotal);
            mstotal.add(new Integer(mskflexormusclesleft));
            mstotal.add(new Integer(mskextensormusclesleft));
            String staticformula = "(((mskflexormusclesleft+mskextensormusclesleft)/2)*0.3)";
            msTotal = formulaMuselStrength(mstotal, 2, staticformula, kneeleft);
            lowerExtremityBean.setMskneeleftcalculation(msTotal);
        }
        double ankleleft = (((msaplanterflexormusclesleft + msadorsiflexormusclesleft + msainvertormusclesleft + msaevertormusclesleft) / 4) * 0.3);
        if (ankleleft != 0) {
            mstotal.removeAll(mstotal);
            mstotal.add(new Integer(msaplanterflexormusclesleft));
            mstotal.add(new Integer(msadorsiflexormusclesleft));
            mstotal.add(new Integer(msainvertormusclesleft));
            mstotal.add(new Integer(msaevertormusclesleft));
            String staticformula = "(((msaplanterflexormusclesleft+msadorsiflexormusclesleft+msainvertormusclesleft+msaevertormusclesleft)/4)*0.3)";
            msTotal = formulaMuselStrength(mstotal, 4, staticformula, ankleleft);
            lowerExtremityBean.setMsankletotalleftcalulation(msTotal);
        }



        double ms_left = hipleft + kneeleft + ankleleft;
        if (ms_left != 0) {
            String staticformula = "hipleft+kneeleft+ankleleft";
            romTotalRightcalculation = formulaforRomTotalRight(hipleft, kneeleft, ankleleft, ms_left, staticformula);
            lowerExtremityBean.setMstotalleftcalulation(romTotalRightcalculation);
        }
        if (ms_right != 0 || ms_left != 0) {
            lowerExtremityBean.setCheckmstotal(true);
        }


        /*
         *By caling the method "add" we are applying the formula a+(((90-a)/90)*b)for ROM and MS.
         *and finding the total mobility component.
         */
        mobilitycomponentright = add(romright, ms_right);

        if (mobilitycomponentright != 0) {
            String staticformula = "Applying Formula between (RomTotalRight,MsTotalRight)";
            totalRightCalculation = formulaforTotalRight(romright, ms_right, mobilitycomponentright, staticformula);
            lowerExtremityBean.setTotalrightmobilitycomponent(totalRightCalculation);
        }



        mobilitycomponentleft = add(romleft, ms_left);
        if (mobilitycomponentleft != 0) {
            String staticformula = "Applying Formula between (RomTotalLeft,MsTotalLeft)";
            totalRightCalculation = formulaforTotalRight(romleft, ms_left, mobilitycomponentleft, staticformula);
            lowerExtremityBean.setTotalleftmobilitycomponent(totalRightCalculation);
        }





        mobilitycomponent = add(mobilitycomponentright, mobilitycomponentleft);
        if (mobilitycomponent != 0) {
            String staticformula = "Applying Formula between (MobilityComponentRight,MobilityComponentLeft)";
            totalRightCalculation = formulaforTotalRight(mobilitycomponentright, mobilitycomponentleft, mobilitycomponent, staticformula);
            lowerExtremityBean.setTotalmobilitycomponent(totalRightCalculation);
        }

        /*
         *By caling the method "add" we are applying the formula a+(((90-a)/90)*b)for mobilitycomponent and stability.
         *and finding the lowerextrimity with out adding extra percentage.
         *
         */
        lowerwithoutextra = add(mobilitycomponent, stability);
        if (lowerwithoutextra != 0) {
            String staticformula = "Applying Formula between (TotalMobilityComponent,Stability)";
            totalRightCalculation = formulaforTotalRight(mobilitycomponent, stability, lowerwithoutextra, staticformula);
            lowerExtremityBean.setLowerextremitywithoutextra(totalRightCalculation);
        }





        /*
         *By caling the method "add" we are applying the formula a+(((90-a)/90)*b)for lowerwithoutextra and extradata.
         *finding the total LowerExtremity percentage .
         */
        if (extra > 10) {
            extradata = 10 + shortininginches;
            lowerextremity = add(lowerwithoutextra, extradata);
        } else if (extra <= 10) {
            extradata = extra + shortininginches;
            lowerextremity = add(lowerwithoutextra, extradata);
        }

        if (lowerextremity != 0) {
            StringBuffer lowerextremityfinalvalue = new StringBuffer();
            if (extra > 10) {
                lowerextremityfinalvalue.append("(" + extra + ">10)");
                lowerextremityfinalvalue.append("extradata= 10+" + shortininginches);
            } else if (extra <= 10) {
                lowerextremityfinalvalue.append("(" + extra + "<=10)");
                lowerextremityfinalvalue.append("(" + extra + "+" + shortininginches + ")");
            }

            lowerextremityfinalvalue.append("=" + extradata);
            String staticformula = "Applying Formula between (lowerwithoutextra,extradata)";
            totalRightCalculation = formulaforTotalRight(lowerwithoutextra, extradata, lowerextremity, staticformula);
            lowerExtremityBean.setLowerextremityfinalvalue(totalRightCalculation);
        }












        // request.setAttribute("lowerextremity",new Double(lowerextremity));

        if (lowerextremity > 100) {
            lowerextremitytotal = 100;

        } else if (lowerextremity <= 100) {
            lowerextremitytotal = lowerextremity;
        }
        if (lowerextremitytotal != 0) {
            lowerExtremityBean.setLowerextremitytotal(String.valueOf(lowerextremitytotal));
        }



        lowerExtremityBean.setRomright(romright);
        lowerExtremityBean.setRomleft(romleft);
        lowerExtremityBean.setExtra(extra);
        lowerExtremityBean.setStability(stability);
        lowerExtremityBean.setMsright(ms_right);
        lowerExtremityBean.setMsleft(ms_left);
        lowerExtremityBean.setLowerextremity(lowerextremitytotal);

        return lowerExtremityBean;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public double cal(double a, double b) {
        right = a + (((90 - a) / 90) * b);

        return right;
    }

    /**
     *
     * @param input
     * @return
     */
    public int msMethod(int input) {

        switch (input) {
            case 0:
                input1 = 100;
                break;
            case 1:
                input1 = 80;
                break;
            case 2:
                input1 = 60;
                break;
            case 3:
                input1 = 40;
                break;
            case 4:
                input1 = 20;
                break;
            case 5:
                input1 = 0;
                break;
        }
        return input1;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public double add(double a, double b) {
        if (a > b) {
            add = cal(a, b);
        } else {
            add = cal(b, a);
        }
        return add;
    }

    // formula for ROM Hip joint Right and Left
    public StringBuffer formula(int a, int b, float c, String staticformula) {
        romrightcalculation = new StringBuffer();
        //romrightcalculation.append(staticformula);
        //romrightcalculation.append(" = ");
        romrightcalculation.append("(((" + a + "-" + b + ")*100)/" + a + ")");
        romrightcalculation.append(" = ");
        romrightcalculation.append(formatResult(c));
        return romrightcalculation;
    }

    public StringBuffer formulaforRomTotalRightIndividual(float a, float b, float c, double d, String staticformula) {
        romTotalCalculation = new StringBuffer();
        // romTotalCalculation.append(staticformula);
        // romTotalCalculation.append(" = ");
        romTotalCalculation.append("(((" + a + "+" + b + "+\n" + c + ")/3)0.3)");
        romTotalCalculation.append("\n = ");
        romTotalCalculation.append(formatResult(d));
        return romTotalCalculation;
    }
    // formula for ROM Knee joint Right and Left

    public StringBuffer formulaforRomKneeJoint(int a, int b, double c, String staticformula) {
        romkneerightleftcalculation = new StringBuffer();
        // romkneerightleftcalculation.append(staticformula);
        //romkneerightleftcalculation.append(" = ");
        romkneerightleftcalculation.append("(((" + a + "-" + b + ")*100)/" + a + ")");
        romkneerightleftcalculation.append(" = ");
        romkneerightleftcalculation.append(formatResult(c));
        return romkneerightleftcalculation;
    }

    public StringBuffer formulaforRomTotalAnkle(float a, float b, double c, String staticformula) {
        romAnklecalculation = new StringBuffer();
        //romAnklecalculation.append(staticformula);
        // romAnklecalculation.append(" = ");
        romAnklecalculation.append("(((" + a + "+\n" + b + ")/2)*0.3)");
        romAnklecalculation.append("\n = ");
        romAnklecalculation.append(formatResult(c));
        return romAnklecalculation;
    }

    public StringBuffer formulaforRomTotalRight(double a, double b, double c, double d, String staticformula) {
        romTotalRightcalculation = new StringBuffer();
        //romAnklecalculation.append(staticformula);
        // romAnklecalculation.append(" = ");
        romTotalRightcalculation.append("(" + formatResult(a) + "+" + formatResult(b) + "+" + formatResult(c) + ")");
        romTotalRightcalculation.append("\n = ");
        romTotalRightcalculation.append(formatResult(d));
        return romTotalRightcalculation;
    }

    public StringBuffer formulaMuselStrength(ArrayList values, int id, String staticformula, double total) {
        StringBuffer msvalue = new StringBuffer();
        msTotal = new StringBuffer();


        Iterator it = values.iterator();
        while (it.hasNext()) {
            msvalue.append(((Integer) it.next()).intValue() + " + ");
        }
        String ms = new String(msvalue.substring(0, msvalue.length() - 2));
        if (id != 0) {
            // msTotal.append(staticformula);
            // msTotal.append(" = ");
            msTotal.append("((" + ms + ")/" + id + "*0.3)");
        } else {
            msTotal.append(staticformula);
            msTotal.append(" = ");
            msTotal.append(ms);
        }
        msTotal.append(" = ");
        msTotal.append(formatResult(total));
        return msTotal;
    }

    public StringBuffer formulaforTotalRight(double a, double b, double c, String staticformula) {
        totalRightCalculation = new StringBuffer();
        totalRightCalculation.append(staticformula);

        totalRightCalculation.append("\n                                  = (a+(((90-a)/90)*b)) 'a' value is Greater value(a > b)\n                                  =");

        //totalRightCalculation.append("(a+(((90-a)/90)*b)) 'a' value is Greater value");
        //totalRightCalculation.append("\n                                  = ");
        if (a > b) {
            totalRightCalculation.append("(" + formatResult(a) + ">" + formatResult(b) + ")");
            totalRightCalculation.append(" \n                                  = ");

            totalRightCalculation.append("(" + formatResult(a) + "+((90-" + formatResult(a) + ")/90)*" + formatResult(b) + "))");


        } else {
            totalRightCalculation.append("(" + formatResult(b) + ">" + formatResult(a) + ")");
            totalRightCalculation.append(" \n                                  = ");

            totalRightCalculation.append("(" + formatResult(b) + "+((90-" + formatResult(b) + ")/90)*" + formatResult(a) + "))");

            add = cal(b, a);
        }
        totalRightCalculation.append("\n                                  = ");
        totalRightCalculation.append(formatResult(c));
        return totalRightCalculation;
    }

    public double formatResult(double val) {
        BigDecimal temp1 = new BigDecimal(val).setScale(2, BigDecimal.ROUND_HALF_UP);
        double temp2 = (new Double(temp1.doubleValue())).doubleValue();
        return temp2;
    }
}



