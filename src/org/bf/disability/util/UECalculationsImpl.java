package org.bf.disability.util;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.bean.UpperExtremityBean;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dto.UpperExtrimityDto;
import org.bf.disability.service.UpperExtrimityService;
import org.bf.disability.servicefactory.UpperExterimityServiceFactory;
import org.bf.disability.serviceimpl.ShowCalcualationsServiceImpl;

public class UECalculationsImpl extends ShowCalcualationsServiceImpl {

    int input1;
    double right;
    double add;
    int value;
    StringBuffer totalRightCalculation = new StringBuffer();
    StringBuffer totalLeftCalculation = new StringBuffer();

    public void populateUpperExtremityCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException {
        UpperExtrimityDto upperExtrimityDto = new UpperExtrimityDto();
        UpperExtremityBean upperExtremityBean = new UpperExtremityBean();
        UpperExtrimityService upperextrimityservice =
                UpperExterimityServiceFactory.getUpperExterimityServiceImpl();
        try {
            upperExtrimityDto = upperextrimityservice.selectUpperExterimityData(dataSource, personcode);
            BeanUtils.copyProperties(upperExtremityBean, upperExtrimityDto);
            upperExtremityBean = upperExtremityCalculationsLogic(upperExtremityBean);
            request.setAttribute("upperExtremityBean", upperExtremityBean);

        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource,sqlEx.toString(), "populateUpperExtremityCalculations", "UECalculationsImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UECalculationsImpl", "populateUpperExtremityCalculations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource,sqlEx.toString(), "populateUpperExtremityCalculations", "UECalculationsImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UECalculationsImpl", "populateUpperExtremityCalculations");
        }//end of catch block

    }

    public UpperExtremityBean upperExtremityCalculationsLogic(UpperExtremityBean upperExtremityBean) {
        double mcright;
        double mcleft;
        double mc;
        double msright = 0;
        double msleft = 0;
        double shortining;
        double arm;
        double totalextra = 0;

        int index;
        int middle;
        int ring;
        int little;
        int keyholding;
        int senthumb;
        int cylarge;
        int cysmall;
        int splarge;
        int spsmall;
        int senindex;
        int senmiddle;
        int senring;
        int senlittle;
        int hook;
        int strgrip;
        int strpinch;
        int prehention;
        int sensation;
        int strength;
        double upper;
        double upperextrimity;
        double upperExtremityRound;
        int Rom_sf_right = Integer.parseInt(upperExtremityBean.getRom_sf_right());
        int Rom_sr_right = Integer.parseInt(upperExtremityBean.getRom_sr_right());
        int Rom_sa_right = Integer.parseInt(upperExtremityBean.getRom_sa_right());
        int Rom_sf_left = Integer.parseInt(upperExtremityBean.getRom_sf_left());
        int Rom_sr_left = Integer.parseInt(upperExtremityBean.getRom_sr_left());
        int Rom_sa_left = Integer.parseInt(upperExtremityBean.getRom_sa_left());
        int Rom_ef_right = Integer.parseInt(upperExtremityBean.getRom_ef_right());
        int Rom_es_right = Integer.parseInt(upperExtremityBean.getRom_es_right());
        int Rom_ef_left = Integer.parseInt(upperExtremityBean.getRom_ef_left());
        int Rom_es_left = Integer.parseInt(upperExtremityBean.getRom_es_left());
        int Rom_wd_right = Integer.parseInt(upperExtremityBean.getRom_wd_right());
        int Rom_wr_right = Integer.parseInt(upperExtremityBean.getRom_wr_right());
        int Rom_wd_left = Integer.parseInt(upperExtremityBean.getRom_wd_left());
        int Rom_wr_left = Integer.parseInt(upperExtremityBean.getRom_wr_left());
        int Ms_sf_right = Integer.parseInt(upperExtremityBean.getMs_sf_right());
        int Ms_se_right = Integer.parseInt(upperExtremityBean.getMs_se_right());
        int Ms_sab_right = Integer.parseInt(upperExtremityBean.getMs_sab_right());
        int Ms_sad_right = Integer.parseInt(upperExtremityBean.getMs_sad_right());
        int Ms_sext_right = Integer.parseInt(upperExtremityBean.getMs_sext_right());
        int Ms_sint_right = Integer.parseInt(upperExtremityBean.getMs_sint_right());
        int Ms_sf_left = Integer.parseInt(upperExtremityBean.getMs_sf_left());
        int Ms_se_left = Integer.parseInt(upperExtremityBean.getMs_se_left());
        int Ms_sab_left = Integer.parseInt(upperExtremityBean.getMs_sab_left());
        int Ms_sad_left = Integer.parseInt(upperExtremityBean.getMs_sad_left());
        int Ms_sext_left = Integer.parseInt(upperExtremityBean.getMs_sext_left());
        int Ms_sint_left = Integer.parseInt(upperExtremityBean.getMs_sint_left());
        int Ms_ef_right = Integer.parseInt(upperExtremityBean.getMs_ef_right());
        int Ms_ee_right = Integer.parseInt(upperExtremityBean.getMs_ee_right());
        int Ms_ep_right = Integer.parseInt(upperExtremityBean.getMs_ep_right());
        int Ms_es_right = Integer.parseInt(upperExtremityBean.getMs_es_right());
        int Ms_ef_left = Integer.parseInt(upperExtremityBean.getMs_ef_left());
        int Ms_ee_left = Integer.parseInt(upperExtremityBean.getMs_ee_left());
        int Ms_ep_left = Integer.parseInt(upperExtremityBean.getMs_ep_left());
        int Ms_es_left = Integer.parseInt(upperExtremityBean.getMs_es_left());
        int Ms_wd_right = Integer.parseInt(upperExtremityBean.getMs_wd_right());
        int Ms_wp_right = Integer.parseInt(upperExtremityBean.getMs_wp_right());
        int Ms_wr_right = Integer.parseInt(upperExtremityBean.getMs_wr_right());
        int Ms_wu_right = Integer.parseInt(upperExtremityBean.getMs_wu_right());
        int Ms_wd_left = Integer.parseInt(upperExtremityBean.getMs_wd_left());
        int Ms_wp_left = Integer.parseInt(upperExtremityBean.getMs_wp_left());
        int Ms_wr_left = Integer.parseInt(upperExtremityBean.getMs_wr_left());
        int Ms_wu_left = Integer.parseInt(upperExtremityBean.getMs_wu_left());


//Rotation of motion  calculations
        float hrom_sf_right = (((220 - Rom_sf_right) * 100) / 220);
        float hrom_sr_right = (((180 - Rom_sr_right) * 100) / 180);
        float hrom_sa_right = (((180 - Rom_sa_right) * 100) / 180);
        int romflexion = 0;
        int romabduction = 0;
        int romrotation = 0;
        int romshoulderright = 0;
        int romshoulderleft = 0;
        int romshoulder = 0;
        double romhipr = ((hrom_sf_right + hrom_sr_right + hrom_sa_right) / 3) * 0.3;

        float hrom_sf_left = (((220 - Rom_sf_left) * 100) / 220);
        float hrom_sr_left = (((180 - Rom_sr_left) * 100) / 180);
        float hrom_sa_left = (((180 - Rom_sa_left) * 100) / 180);
        double romhipl = ((hrom_sf_left + hrom_sr_left + hrom_sa_left) / 3) * 0.3;
        if (hrom_sf_right == 0.0 && hrom_sf_left == 0.0) {
            romflexion = 1;
        }
        if (hrom_sa_right == 0.0 && hrom_sa_left == 0.0) {
            romabduction = 1;
        }
        if (hrom_sr_right == 0.0 && hrom_sr_left == 0.0) {
            romrotation = 1;
        }
        if (romhipr == 0.0 && romhipl == 0.0) {
            romshoulder = 1;
        }
        if (romhipr == 0.0) {
            romshoulderright = 1;
        }
        if (romhipl == 0.0) {
            romshoulderleft = 1;
        }


        double romknee21r = (((150 - Rom_ef_right) * 100) / 150);
        double romknee22r = (((180 - Rom_es_right) * 100) / 180);
        double romkneer = (((romknee21r + romknee22r) / 2) * 0.3);
        double romknee21l = (((150 - Rom_ef_left) * 100) / 150);
        double romknee22l = (((180 - Rom_es_left) * 100) / 180);
        double romkneel = (((romknee21l + romknee22l) / 2) * 0.3);
        int romkeen = 0;
        int romkneeright = 0;
        int romkneeleft = 0;
        int romkneeflexion = 0;
        int romkneesupination = 0;

        if (romkneer == 0.0 && romkneel == 0.0) {
            romkeen = 1;
        }
        if (romkneer == 0.0) {
            romkneeright = 1;
        }
        if (romkneel == 0.0) {
            romkneeleft = 1;
        }
        if (romknee21r == 0.0 && romknee21l == 0.0) {
            romkneeflexion = 1;
        }
        if (romknee22r == 0.0 & romknee22l == 0.0) {
            romkneesupination = 1;
        }



        float arom_wd_right = (((160 - Rom_wd_right) * 100) / 160);
        float aRom_wr_right = (((55 - Rom_wr_right) * 100) / 55);
        double rowankler = ((arom_wd_right + aRom_wr_right) / 2) * 0.3;
        float aRom_wd_left = (((160 - Rom_wd_left) * 100) / 160);
        float aRom_wr_left = (((55 - Rom_wr_left) * 100) / 55);
        double rowanklel = ((aRom_wd_left + aRom_wr_left) / 2) * 0.3;
        int wristright = 0;
        int wristleft = 0;
        int wrist = 0;
        int wristpalmar = 0;
        int wristulnar = 0;
        if (rowankler == 0.0 && rowanklel == 0.0) {
            wrist = 1;
        }
        if (rowanklel == 0.0) {
            wristleft = 1;
        }
        if (rowankler == 0.0) {
            wristright = 1;
        }
        if (arom_wd_right == 0.0 && aRom_wd_left == 0.0) {
            wristpalmar = 1;
        }
        if (aRom_wr_left == 0.0 && aRom_wr_right == 0.0) {
            wristulnar = 1;
        }

        double romright = (romhipr + romkneer + rowankler);
        double romleft = (romhipl + romkneel + rowanklel);

        int rom = 0;
        if (romright == 0.0 && romleft == 0.0) {
            rom = 1;
        }
        upperExtremityBean.setHrom_sf_right(String.valueOf(hrom_sf_right));
        upperExtremityBean.setHrom_sa_right(String.valueOf(hrom_sa_right));
        upperExtremityBean.setHrom_sr_right(String.valueOf(hrom_sr_right));

        upperExtremityBean.setHrom_sf_left(String.valueOf(hrom_sf_left));
        upperExtremityBean.setHrom_sa_left(String.valueOf(hrom_sa_left));
        upperExtremityBean.setHrom_sr_left(String.valueOf(hrom_sr_left));
        upperExtremityBean.setRomflexion(String.valueOf(romflexion));
        upperExtremityBean.setRomrotation(String.valueOf(romrotation));
        upperExtremityBean.setRomabduction(String.valueOf(romabduction));
        upperExtremityBean.setRomshoulder(String.valueOf(romshoulder));
        upperExtremityBean.setRomshoulderleft(String.valueOf(romshoulderleft));
        upperExtremityBean.setRomshoulderright(String.valueOf(romshoulderright));
        upperExtremityBean.setRomhipr(String.valueOf(romhipr));
        upperExtremityBean.setRomhipl(String.valueOf(romhipl));


        upperExtremityBean.setRomkeen(String.valueOf(romkeen));
        upperExtremityBean.setRomkneeleft(String.valueOf(romkneeleft));
        upperExtremityBean.setRomkneeright(String.valueOf(romkneeright));
        upperExtremityBean.setRomknee21l(String.valueOf(romknee21l));
        upperExtremityBean.setRomknee21r(String.valueOf(romknee21r));
        upperExtremityBean.setRomknee22l(String.valueOf(romknee22l));
        upperExtremityBean.setRomknee22r(String.valueOf(romknee22r));
        upperExtremityBean.setRomkneel(String.valueOf(romkneel));
        upperExtremityBean.setRomkneer(String.valueOf(romkneer));
        upperExtremityBean.setRomkneeflexion(String.valueOf(romkneeflexion));
        upperExtremityBean.setRomkneesupination(String.valueOf(romkneesupination));

        upperExtremityBean.setWrist(String.valueOf(wrist));
        upperExtremityBean.setWristleft(String.valueOf(wristleft));
        upperExtremityBean.setWristright(String.valueOf(wristright));
        upperExtremityBean.setArom_wd_left(String.valueOf(aRom_wd_left));
        upperExtremityBean.setArom_wd_right(String.valueOf(arom_wd_right));
        upperExtremityBean.setArom_wr_left(String.valueOf(aRom_wr_left));
        upperExtremityBean.setArom_wr_right(String.valueOf(aRom_wr_right));
        upperExtremityBean.setWristpalmar(String.valueOf(wristpalmar));
        upperExtremityBean.setWristulnar(String.valueOf(wristulnar));
        upperExtremityBean.setRowankler(String.valueOf(rowankler));
        upperExtremityBean.setRowanklel(String.valueOf(rowanklel));
        upperExtremityBean.setRom(String.valueOf(rom));
        upperExtremityBean.setRomleft(String.valueOf(romleft));
        upperExtremityBean.setRomright(String.valueOf(romright));

        int Ms_sf_right_per = msMethod(Ms_sf_right);
        int Ms_se_right_per = msMethod(Ms_se_right);
        int Ms_sab_right_per = msMethod(Ms_sab_right);
        int Ms_sad_right_per = msMethod(Ms_sad_right);
        int Ms_sext_right_per = msMethod(Ms_sext_right);
        int Ms_sint_right_per = msMethod(Ms_sint_right);
        int Ms_sf_left_per = msMethod(Ms_sf_left);
        int Ms_se_left_per = msMethod(Ms_se_left);
        int Ms_sab_left_per = msMethod(Ms_sab_left);
        int Ms_sad_left_per = msMethod(Ms_sad_left);
        int Ms_sext_left_per = msMethod(Ms_sext_left);
        int Ms_sint_left_per = msMethod(Ms_sint_left);
        int Ms_ef_right_per = msMethod(Ms_ef_right);
        int Ms_ee_right_per = msMethod(Ms_ee_right);
        int Ms_ep_right_per = msMethod(Ms_ep_right);
        int Ms_es_right_per = msMethod(Ms_es_right);
        int Ms_ef_left_per = msMethod(Ms_ef_left);
        int Ms_ee_left_per = msMethod(Ms_ee_left);
        int Ms_ep_left_per = msMethod(Ms_ep_left);
        int Ms_es_left_per = msMethod(Ms_es_left);
        int Ms_wd_right_per = msMethod(Ms_wd_right);
        int Ms_wp_right_per = msMethod(Ms_wp_right);
        int Ms_wr_right_per = msMethod(Ms_wr_right);
        int Ms_wu_right_per = msMethod(Ms_wu_right);
        int Ms_wd_left_per = msMethod(Ms_wd_left);
        int Ms_wp_left_per = msMethod(Ms_wp_left);
        int Ms_wr_left_per = msMethod(Ms_wr_left);
        int Ms_wu_left_per = msMethod(Ms_wu_left);
        double hipright = (((Ms_sf_right_per + Ms_se_right_per + Ms_sab_right_per
                + Ms_sad_right_per + Ms_sext_right_per
                + Ms_sint_right_per) / 6) * 0.3);
        double kneeright = (((Ms_ef_right_per + Ms_ee_right_per + Ms_ep_right_per
                + Ms_es_right_per) / 4) * 0.3);
        double ankleright = (((Ms_wd_right_per + Ms_wp_right_per + Ms_wr_right_per
                + Ms_wu_right_per) / 4) * 0.3);
        msright = hipright + kneeright + ankleright;
        double hipleft = (((Ms_sf_left_per + Ms_se_left_per + Ms_sab_left_per
                + Ms_sad_left_per + Ms_sext_left_per
                + Ms_sint_left_per) / 6) * 0.3);
        double kneeleft = (((Ms_ef_left_per + Ms_ee_left_per + Ms_ep_left_per
                + Ms_es_left_per) / 4) * 0.3);
        double ankleleft = (((Ms_wd_left_per + Ms_wp_left_per + Ms_wr_left_per
                + Ms_wu_left_per) / 4) * 0.3);
        msleft = hipleft + kneeleft + ankleleft;
        mcright = add(romright, msright);
        mcleft = add(romleft, msleft);
        mc = add(mcright, mcleft);



        mcright = add(romright, msright);

        if (mcright != 0) {
            String staticformula = "Applying Formula between (RomTotalRight,MsTotalRight)";
            totalRightCalculation = formulaforTotalRight(romright, msright, mcright, staticformula);
            upperExtremityBean.setTotalArmRight(totalRightCalculation);
        }

        mcleft = add(romleft, msleft);

        if (mcleft != 0) {
            String staticformula = "Applying Formula between (RomTotalLeft,MsTotalLeft)";
            totalLeftCalculation = formulaforTotalRight(romleft, msleft, mcleft, staticformula);
            upperExtremityBean.setTotalArmLeft(totalLeftCalculation);
        }


        mc = add(mcright, mcleft);

        if (mc != 0) {
            String staticformula = "Applying Formula between (ArmRight,ArmLeft)";
            totalLeftCalculation = formulaforTotalRight(mcright, mcleft, mc, staticformula);
            upperExtremityBean.setArmRightLeft(totalLeftCalculation);
        }

        upperExtremityBean.setArmRight(String.valueOf(mcright));
        upperExtremityBean.setArmLeft(String.valueOf(mcleft));
        upperExtremityBean.setRightLeftArm(String.valueOf(mc));



        //Coordinate calculations
        int coordinate_lifting = Integer.parseInt(upperExtremityBean.getCoordinate_lifting());
        int coordinate_touching = Integer.parseInt(upperExtremityBean.getCoordinate_touching());
        int coordinate_eating = Integer.parseInt(upperExtremityBean.getCoordinate_eating());
        int coordinate_combing = Integer.parseInt(upperExtremityBean.getCoordinate_combing());
        int coordinate_putting = Integer.parseInt(upperExtremityBean.getCoordinate_putting());
        int coordinate_ablution = Integer.parseInt(upperExtremityBean.getCoordinate_ablution());
        int coordinate_buttoning = Integer.parseInt(upperExtremityBean.getCoordinate_buttoning());
        int coordinate_tie = Integer.parseInt(upperExtremityBean.getCoordinate_tie());
        int coordinate_writing = Integer.parseInt(upperExtremityBean.getCoordinate_writing());
        int coordinate_drinking = Integer.parseInt(upperExtremityBean.getCoordinate_drinking());
        int coordinate = coordinate_lifting + coordinate_touching
                + coordinate_eating + coordinate_combing
                + coordinate_putting + coordinate_ablution
                + coordinate_buttoning + coordinate_tie + coordinate_writing + coordinate_drinking;
        upperExtremityBean.setCoordinate(String.valueOf(coordinate));
        arm = add(mc, coordinate);
        if (arm != 0) {
            String staticformula = "Applying Formula between (Arm(Right+Left),Coordinate)";
            totalLeftCalculation = formulaforTotalRight(mc, coordinate, arm, staticformula);
            upperExtremityBean.setTotalArm(totalLeftCalculation);
        }
        upperExtremityBean.setArmRight(String.valueOf(mcright));
        upperExtremityBean.setArmLeft(String.valueOf(mcleft));
        upperExtremityBean.setRightLeftArm(String.valueOf(mc));
        upperExtremityBean.setArm(String.valueOf(arm));


        //Hand component calculations
        int Hand_opindex_right = Integer.parseInt(upperExtremityBean.getHand_opindex_right());
        int Hand_opmiddle_right = Integer.parseInt(upperExtremityBean.getHand_opmiddle_right());
        int Hand_opring_right = Integer.parseInt(upperExtremityBean.getHand_opring_right());
        int Hand_oplittle_right = Integer.parseInt(upperExtremityBean.getHand_oplittle_right());
        int Hand_opindex_left = Integer.parseInt(upperExtremityBean.getHand_opindex_left());
        int Hand_opmiddle_left = Integer.parseInt(upperExtremityBean.getHand_opmiddle_left());
        int Hand_opring_left = Integer.parseInt(upperExtremityBean.getHand_opring_left());
        int Hand_oplittle_left = Integer.parseInt(upperExtremityBean.getHand_oplittle_left());
        int Hand_lakey_right = Integer.parseInt(upperExtremityBean.getHand_lakey_right());
        int Hand_lakey_left = Integer.parseInt(upperExtremityBean.getHand_lakey_left());
        int Hand_cylarge_right = Integer.parseInt(upperExtremityBean.getHand_cylarge_right());
        int Hand_cysmall_right = Integer.parseInt(upperExtremityBean.getHand_cysmall_right());
        int Hand_cylarge_left = Integer.parseInt(upperExtremityBean.getHand_cylarge_left());
        int Hand_cysmall_left = Integer.parseInt(upperExtremityBean.getHand_cysmall_left());
        int Hand_splarge_right = Integer.parseInt(upperExtremityBean.getHand_splarge_right());
        int Hand_spsmall_right = Integer.parseInt(upperExtremityBean.getHand_spsmall_right());
        int Hand_splarge_left = Integer.parseInt(upperExtremityBean.getHand_splarge_left());
        int Hand_spsmall_left = Integer.parseInt(upperExtremityBean.getHand_spsmall_left());
        int Hand_hook_right = Integer.parseInt(upperExtremityBean.getHand_hook_right());
        int Hand_hook_left = Integer.parseInt(upperExtremityBean.getHand_hook_left());
        int Hand_sethumb_right = Integer.parseInt(upperExtremityBean.getHand_sethumb_right());
        int Hand_seindex_right = Integer.parseInt(upperExtremityBean.getHand_seindex_right());
        int Hand_semiddle_right = Integer.parseInt(upperExtremityBean.getHand_semiddle_right());
        int Hand_sering_right = Integer.parseInt(upperExtremityBean.getHand_sering_right());
        int Hand_selittle_right = Integer.parseInt(upperExtremityBean.getHand_selittle_right());
        int Hand_sethumb_left = Integer.parseInt(upperExtremityBean.getHand_sethumb_left());
        int Hand_seindex_left = Integer.parseInt(upperExtremityBean.getHand_seindex_left());
        int Hand_semiddle_left = Integer.parseInt(upperExtremityBean.getHand_semiddle_left());
        int Hand_sering_left = Integer.parseInt(upperExtremityBean.getHand_sering_left());
        int Hand_selittle_left = Integer.parseInt(upperExtremityBean.getHand_selittle_left());
        int Hand_stgrip_right = Integer.parseInt(upperExtremityBean.getHand_stgrip_right());
        int Hand_stpinch_right = Integer.parseInt(upperExtremityBean.getHand_stpinch_right());
        int Hand_stgrip_left = Integer.parseInt(upperExtremityBean.getHand_stgrip_left());
        int Hand_stpinch_left = Integer.parseInt(upperExtremityBean.getHand_stpinch_left());
        index = count(Hand_opindex_right + Hand_opindex_left);
        middle = count(Hand_opmiddle_right + Hand_opmiddle_left);
        ring = count(Hand_opring_right + Hand_opring_left);
        little = count(Hand_oplittle_right + Hand_oplittle_left);
        keyholding = count1(Hand_lakey_right + Hand_lakey_left);
        cylarge = count2(Hand_cylarge_right + Hand_cylarge_left);
        cysmall = count2(Hand_cysmall_right + Hand_cysmall_left);
        splarge = count2(Hand_splarge_right + Hand_splarge_left);
        spsmall = count2(Hand_spsmall_right + Hand_spsmall_left);
        hook = count1(Hand_hook_right + Hand_hook_left);
        prehention = index + middle + ring + little + keyholding + cylarge + cysmall
                + splarge + spsmall + hook;
        senthumb = count3(Hand_sethumb_right + Hand_sethumb_left);
        senindex = count6(Hand_seindex_right + Hand_seindex_left);
        senmiddle = count1(Hand_semiddle_right + Hand_semiddle_left);
        senring = count1(Hand_sering_right + Hand_sering_left);
        senlittle = count1(Hand_selittle_right + Hand_selittle_left);
        sensation = senthumb + senmiddle + senindex + senring + senlittle;
        strgrip = count4(Hand_stgrip_right + Hand_stgrip_left);
        strpinch = count5(Hand_stpinch_right + Hand_stpinch_left);
        strength = strgrip + strpinch;
        int hand = prehention + sensation + strength;
        //Extra complications
        int com_inflection = Integer.parseInt(upperExtremityBean.getCom_inflection());
        int com_Deformity = Integer.parseInt(upperExtremityBean.getCom_Deformity());
        int com_Misalignment = Integer.parseInt(upperExtremityBean.getCom_Misalignment());
        int com_Contracture = Integer.parseInt(upperExtremityBean.getCom_Contracture());
        int com_LossofCosmeticappearance = Integer.parseInt(upperExtremityBean.getCom_LossofCosmeticappearance());
        int com_domionantupperextremity = Integer.parseInt(upperExtremityBean.getCom_domionantupperextremity());
        double inches = Double.parseDouble(upperExtremityBean.getInches());
        if (inches <= 1) {
            shortining = 0;
        } else {
            shortining = (inches - 1) * 2;
        }
        double complication = com_inflection + com_Deformity + com_Misalignment
                + com_Contracture + com_LossofCosmeticappearance;
        double extraCoomplication = com_inflection + com_Deformity + com_Misalignment
                + com_Contracture + com_LossofCosmeticappearance + com_domionantupperextremity;
        // double totalextra=complication+com_domionantupperextremity+shortining;
        upperExtremityBean.setComplication(String.valueOf(complication));
        upperExtremityBean.setShortining(String.valueOf(shortining));

        upperExtremityBean.setExtraCoomplication(String.valueOf(extraCoomplication));
        //Adding arm and hand component(arm component + Hand component)
        upper = add(arm, hand);
        if (upper != 0) {
            String staticformula = "Applying Formula between (Arm Component,Hand Component)";
            totalLeftCalculation = formulaforTotalRight(arm, hand, upper, staticformula);
            upperExtremityBean.setUpper(totalLeftCalculation);
        }
        //upperextrimity =(Arm component + Hand component + Extras)
        if (complication > 10) {
            totalextra = 10
                    + com_domionantupperextremity + shortining;
            upperextrimity = add(upper, totalextra);
            if (upperextrimity != 0) {
                String staticformula = "Applying Formula between (Arm Component,Hand Component)";
                totalLeftCalculation = formulaforTotalRight(upper, totalextra, upperextrimity, staticformula);
                upperExtremityBean.setUpperExtremity(totalLeftCalculation);
                upperExtremityBean.setExtra(String.valueOf(totalextra));
            }
        } else if (complication <= 10) {
            totalextra = complication
                    + com_domionantupperextremity + shortining;
            upperextrimity = add(upper, totalextra);
            if (upperextrimity != 0) {
                String staticformula = "Applying Formula between (Arm Component,Hand Component)";
                totalLeftCalculation = formulaforTotalRight(upper, totalextra, upperextrimity, staticformula);
                upperExtremityBean.setUpperExtremity(totalLeftCalculation);
                upperExtremityBean.setExtra(String.valueOf(totalextra));
            }
            upperExtremityRound = Math.round(upperextrimity);

            upperExtremityBean.setUpperExtremityRound(String.valueOf(upperExtremityRound));

        }
        return upperExtremityBean;
    }

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

    public double add(double a, double b) {
        if (a > b) {
            add = cal(a, b);
        } else {
            add = cal(b, a);
        }
        return add;
    }

    public double cal(double a, double b) {
        right = a + (((90 - a) / 90) * b);
        return right;
    }

    public StringBuffer formulaforTotalRight(double a, double b, double c, String staticformula) {
        totalRightCalculation = new StringBuffer();
        totalRightCalculation.append(staticformula);

        totalRightCalculation.append("\n                                  = (a+(((90-a)/90)*b)) 'a' value is Greater value(a > b)");

        //totalRightCalculation.append("(a+(((90-a)/90)*b)) 'a' value is Greater value");
        totalRightCalculation.append("\n                                  = ");
        if (a > b) {
            totalRightCalculation.append("(" + a + ">" + b + ")");
            totalRightCalculation.append(" \n                                  = ");
            totalRightCalculation.append("(" + a + "+((90-" + a + ")/90)*" + b + "))");

        } else {
            totalRightCalculation.append("(" + b + ">" + a + ")");
            totalRightCalculation.append(" \n                                  = ");
            totalRightCalculation.append("(" + b + "+((90-" + b + ")/90)*" + a + "))");
            add = cal(b, a);
        }
        totalRightCalculation.append("\n                                  = ");
        totalRightCalculation.append(c);
        return totalRightCalculation;
    }

    public int count(int a) {
        if ((a == 4) || (a == 2)) {
            value = 2;
        } else {
            value = 0;
        }
        return value;
    }

    public int count1(int a) {
        if ((a == 10) || (a == 5)) {
            value = 5;
        } else {
            value = 0;
        }
        return value;
    }

    public int count2(int a) {
        if ((a == 6) || (a == 3)) {
            value = 3;
        } else {
            value = 0;
        }
        return value;
    }

    public int count3(int a) {
        if ((a == 18) || (a == 9)) {
            value = 9;
        } else {
            value = 0;
        }
        return value;
    }

    public int count4(int a) {
        if ((a == 40) || (a == 20)) {
            value = 20;
        } else {
            value = 0;
        }
        return value;
    }

    public int count5(int a) {
        if ((a == 20) || (a == 10)) {
            value = 10;
        } else {
            value = 0;
        }
        return value;
    }

    public int count6(int a) {
        if ((a == 12) || (a == 6)) {
            value = 6;
        } else {
            value = 0;
        }
        return value;
    }
}
