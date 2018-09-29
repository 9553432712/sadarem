/*
 * UpperExtremityCalculation.java
 *
 * Created on July 22, 2008, 1:01 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.util;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.dto.UpperExtrimityDto;
import org.bf.disability.form.UpperExtrimityActionForm;

import com.tcs.sadarem.util.CommonUtility;
/**
 *
 * @author svsganesh
 */
public class UpperExtremityCalculation {
    
    /** Creates a new instance of UpperExtremityCalculation */
     public UpperExtrimityDto Calculation(ActionForm form,HttpServletRequest request)throws SADAREMException{

       //Local Variables declarations
        double shortining = 0;
        double upperextrimity = 0;
        double extradata = 0;
         
         
       UpperExtrimityActionForm upperextrimityform=(UpperExtrimityActionForm)form;
        UpperExtrimityDto upperExtrimitydto =new UpperExtrimityDto();
       // getting rom components
        if("".equals(upperextrimityform.getRom_sf_right())){upperextrimityform.setRom_sf_right("220");}
        if("".equals(upperextrimityform.getRom_sr_right())){upperextrimityform.setRom_sr_right("180");}
        if("".equals(upperextrimityform.getRom_sa_right())){upperextrimityform.setRom_sa_right("180");}
        if("".equals(upperextrimityform.getRom_sf_left())){upperextrimityform.setRom_sf_left("220");}
        if("".equals(upperextrimityform.getRom_sr_left())){upperextrimityform.setRom_sr_left("180");}
        if("".equals(upperextrimityform.getRom_sa_left())){upperextrimityform.setRom_sa_left("180");}
        if("".equals(upperextrimityform.getRom_ef_right())){upperextrimityform.setRom_ef_right("150");}
        if("".equals(upperextrimityform.getRom_es_right())){upperextrimityform.setRom_es_right("180");}
        if("".equals(upperextrimityform.getRom_ef_left())){upperextrimityform.setRom_ef_left("150");}
        if("".equals(upperextrimityform.getRom_es_left())){upperextrimityform.setRom_es_left("180");}
        if("".equals(upperextrimityform.getRom_wd_right())){upperextrimityform.setRom_wd_right("160");}
        if("".equals(upperextrimityform.getRom_wr_right())){upperextrimityform.setRom_wr_right("55");}
        if("".equals(upperextrimityform.getRom_wd_left())){upperextrimityform.setRom_wd_left("160");}
        if("".equals(upperextrimityform.getRom_wr_left())){upperextrimityform.setRom_wr_left("55");}
//getting Muscle strength components
        if("".equals(upperextrimityform.getMs_sf_right())){upperextrimityform.setMs_sf_right("5");}
        if("".equals(upperextrimityform.getMs_se_right())){upperextrimityform.setMs_se_right("5");}
        if("".equals(upperextrimityform.getMs_sab_right())){upperextrimityform.setMs_sab_right("5");}
        if("".equals(upperextrimityform.getMs_sad_right())){upperextrimityform.setMs_sad_right("5");}
        if("".equals(upperextrimityform.getMs_sext_right())){upperextrimityform.setMs_sext_right("5");}
        if("".equals(upperextrimityform.getMs_sint_right())){upperextrimityform.setMs_sint_right("5");}
        if("".equals(upperextrimityform.getMs_sf_left())){upperextrimityform.setMs_sf_left("5");}
        if("".equals(upperextrimityform.getMs_se_left())){upperextrimityform.setMs_se_left("5");}
        if("".equals(upperextrimityform.getMs_sab_left())){upperextrimityform.setMs_sab_left("5");}
        if("".equals(upperextrimityform.getMs_sad_left())){upperextrimityform.setMs_sad_left("5");}
        if("".equals(upperextrimityform.getMs_sext_left())){upperextrimityform.setMs_sext_left("5");}
        if("".equals(upperextrimityform.getMs_sint_left())){upperextrimityform.setMs_sint_left("5");}
        if("".equals(upperextrimityform.getMs_ef_right())){upperextrimityform.setMs_ef_right("5");}
        if("".equals(upperextrimityform.getMs_ee_right())){upperextrimityform.setMs_ee_right("5");}
        if("".equals(upperextrimityform.getMs_ep_right())){upperextrimityform.setMs_ep_right("5");}
        if("".equals(upperextrimityform.getMs_es_right())){upperextrimityform.setMs_es_right("5");}
        if("".equals(upperextrimityform.getMs_ef_left())){upperextrimityform.setMs_ef_left("5");}
        if("".equals(upperextrimityform.getMs_ee_left())){upperextrimityform.setMs_ee_left("5");}
        if("".equals(upperextrimityform.getMs_ep_left())){upperextrimityform.setMs_ep_left("5");}
        if("".equals(upperextrimityform.getMs_es_left())){upperextrimityform.setMs_es_left("5");}
        if("".equals(upperextrimityform.getMs_wd_right())){upperextrimityform.setMs_wd_right("5");}
        if("".equals(upperextrimityform.getMs_wp_right())){upperextrimityform.setMs_wp_right("5");}
        if("".equals(upperextrimityform.getMs_wr_right())){upperextrimityform.setMs_wr_right("5");}
        if("".equals(upperextrimityform.getMs_wu_right())){upperextrimityform.setMs_wu_right("5");}
        if("".equals(upperextrimityform.getMs_wd_left())){upperextrimityform.setMs_wd_left("5");}
        if("".equals(upperextrimityform.getMs_wp_left())){upperextrimityform.setMs_wp_left("5");}
        if("".equals(upperextrimityform.getMs_wr_left())){upperextrimityform.setMs_wr_left("5");}
        if("".equals(upperextrimityform.getMs_wu_left())){upperextrimityform.setMs_wu_left("5");}
        if("".equals(upperextrimityform.getInches())){upperextrimityform.setInches("0");}

        
        int Rom_sf_right = Integer.parseInt(upperextrimityform.getRom_sf_right());
        int Rom_sr_right = Integer.parseInt(upperextrimityform.getRom_sr_right());
        int Rom_sa_right = Integer.parseInt(upperextrimityform.getRom_sa_right());
        int Rom_sf_left =Integer.parseInt(upperextrimityform.getRom_sf_left());
        int Rom_sr_left =Integer.parseInt(upperextrimityform.getRom_sr_left());
        int Rom_sa_left =Integer.parseInt(upperextrimityform.getRom_sa_left());
        int Rom_ef_right =Integer.parseInt(upperextrimityform.getRom_ef_right());
        int Rom_es_right = Integer.parseInt(upperextrimityform.getRom_es_right());
        int Rom_ef_left = Integer.parseInt(upperextrimityform.getRom_ef_left());
        int Rom_es_left = Integer.parseInt(upperextrimityform.getRom_es_left());
        int Rom_wd_right = Integer.parseInt(upperextrimityform.getRom_wd_right());
        int Rom_wr_right = Integer.parseInt(upperextrimityform.getRom_wr_right());
        int Rom_wd_left = Integer.parseInt(upperextrimityform.getRom_wd_left());
        int Rom_wr_left = Integer.parseInt(upperextrimityform.getRom_wr_left());
        int Ms_sf_right = Integer.parseInt(upperextrimityform.getMs_sf_right());
        int Ms_se_right = Integer.parseInt(upperextrimityform.getMs_se_right());
        int Ms_sab_right = Integer.parseInt(upperextrimityform.getMs_sab_right());
        int Ms_sad_right = Integer.parseInt(upperextrimityform.getMs_sad_right());
        int Ms_sext_right = Integer.parseInt(upperextrimityform.getMs_sext_right());
        int Ms_sint_right = Integer.parseInt(upperextrimityform.getMs_sint_right());
        int Ms_sf_left = Integer.parseInt(upperextrimityform.getMs_sf_left());
        int Ms_se_left = Integer.parseInt(upperextrimityform.getMs_se_left());
        int Ms_sab_left = Integer.parseInt(upperextrimityform.getMs_sab_left());
        int Ms_sad_left = Integer.parseInt(upperextrimityform.getMs_sad_left());
        int Ms_sext_left = Integer.parseInt(upperextrimityform.getMs_sext_left());
        int Ms_sint_left = Integer.parseInt(upperextrimityform.getMs_sint_left());
        int Ms_ef_right = Integer.parseInt(upperextrimityform.getMs_ef_right());
        int Ms_ee_right = Integer.parseInt(upperextrimityform.getMs_ee_right());
        int Ms_ep_right = Integer.parseInt(upperextrimityform.getMs_ep_right());
        int Ms_es_right = Integer.parseInt(upperextrimityform.getMs_es_right());
        int Ms_ef_left = Integer.parseInt(upperextrimityform.getMs_ef_left());
        int Ms_ee_left = Integer.parseInt(upperextrimityform.getMs_ee_left());
        int Ms_ep_left =Integer.parseInt(upperextrimityform.getMs_ep_left());
        int Ms_es_left = Integer.parseInt(upperextrimityform.getMs_es_left());
        int Ms_wd_right = Integer.parseInt(upperextrimityform.getMs_wd_right());
        int Ms_wp_right = Integer.parseInt(upperextrimityform.getMs_wp_right());
        int Ms_wr_right = Integer.parseInt(upperextrimityform.getMs_wr_right());
        int Ms_wu_right = Integer.parseInt(upperextrimityform.getMs_wu_right());
        int Ms_wd_left = Integer.parseInt(upperextrimityform.getMs_wd_left());
        int Ms_wp_left = Integer.parseInt(upperextrimityform.getMs_wp_left());
        int Ms_wr_left = Integer.parseInt(upperextrimityform.getMs_wr_left());
        int Ms_wu_left = Integer.parseInt(upperextrimityform.getMs_wu_left());
        upperextrimityform.setSystemip((String)CommonUtility.getClientIPAddress(request));
        
//Rotation of motion  calculations
        float hRom_sf_right = (((220-Rom_sf_right)*100)/220);
        float hRom_sr_right = (((180-Rom_sr_right)*100)/180);
        float hRom_sa_right = (((180-Rom_sa_right)*100)/180);
        double romhipr= ((hRom_sf_right+hRom_sr_right+hRom_sa_right)/3)*0.3;
        float hRom_sf_left = (((220-Rom_sf_left)*100)/220);
        float hRom_sr_left = (((180-Rom_sr_left)*100)/180);
        float hRom_sa_left = (((180-Rom_sa_left)*100)/180);
        double romhipl= ((hRom_sf_left+hRom_sr_left+hRom_sa_left)/3)*0.3;
        double romknee21r = (((150-Rom_ef_right)*100)/150);
        double romknee22r = (((180-Rom_es_right)*100)/180);
        double romkneer = (((romknee21r+romknee22r)/2)*0.3);
        double romknee21l = (((150-Rom_ef_left)*100)/150);
        double romknee22l = (((180-Rom_es_left)*100)/180);
        double romkneel = (((romknee21l+romknee22l)/2)*0.3);
        float aRom_wd_right = (((160-Rom_wd_right)*100)/160);
        float aRom_wr_right = (((55-Rom_wr_right)*100)/55);
        double rowankler= ((aRom_wd_right+aRom_wr_right)/2)*0.3;
        float aRom_wd_left = (((160-Rom_wd_left)*100)/160);
        float aRom_wr_left = (((55-Rom_wr_left)*100)/55);
        double rowanklel= ((aRom_wd_left+aRom_wr_left)/2)*0.3;
        double romright =(romhipr+romkneer+rowankler);
        double romleft =(romhipl+romkneel+rowanklel);
// Muscle strength calculations
        int Ms_sf_right_per= msMethod(Ms_sf_right);
        int Ms_se_right_per= msMethod(Ms_se_right);
        int Ms_sab_right_per= msMethod(Ms_sab_right);
        int Ms_sad_right_per= msMethod(Ms_sad_right);
        int Ms_sext_right_per= msMethod(Ms_sext_right);
        int Ms_sint_right_per= msMethod(Ms_sint_right);
        int Ms_sf_left_per= msMethod(Ms_sf_left);
        int Ms_se_left_per= msMethod(Ms_se_left);
        int Ms_sab_left_per= msMethod(Ms_sab_left);
        int Ms_sad_left_per= msMethod(Ms_sad_left);
        int Ms_sext_left_per= msMethod(Ms_sext_left);
        int Ms_sint_left_per=msMethod(Ms_sint_left);
        int Ms_ef_right_per= msMethod(Ms_ef_right);
        int Ms_ee_right_per= msMethod(Ms_ee_right);
        int Ms_ep_right_per=msMethod(Ms_ep_right);
        int Ms_es_right_per=msMethod(Ms_es_right);
        int Ms_ef_left_per= msMethod(Ms_ef_left);
        int Ms_ee_left_per= msMethod(Ms_ee_left);
        int Ms_ep_left_per=msMethod(Ms_ep_left);
        int Ms_es_left_per=msMethod(Ms_es_left);
        int Ms_wd_right_per= msMethod(Ms_wd_right);
        int Ms_wp_right_per= msMethod(Ms_wp_right);
        int Ms_wr_right_per= msMethod(Ms_wr_right);
        int Ms_wu_right_per= msMethod(Ms_wu_right);
        int Ms_wd_left_per= msMethod(Ms_wd_left);
        int Ms_wp_left_per= msMethod(Ms_wp_left);
        int Ms_wr_left_per= msMethod(Ms_wr_left);
        int Ms_wu_left_per= msMethod(Ms_wu_left);
        double hipright = (((Ms_sf_right_per+ Ms_se_right_per+Ms_sab_right_per+
                Ms_sad_right_per+Ms_sext_right_per+
                Ms_sint_right_per)/6)*0.3);
        double kneeright=(((Ms_ef_right_per+Ms_ee_right_per+Ms_ep_right_per+
                Ms_es_right_per)/4)*0.3);
        double ankleright=(((Ms_wd_right_per+Ms_wp_right_per+Ms_wr_right_per+
                Ms_wu_right_per)/4)*0.3);
        double msright = hipright+kneeright+ankleright;
        double hipleft= (((Ms_sf_left_per+ Ms_se_left_per+Ms_sab_left_per+
                Ms_sad_left_per+Ms_sext_left_per+
                Ms_sint_left_per)/6)*0.3);
        double kneeleft=(((Ms_ef_left_per+Ms_ee_left_per+Ms_ep_left_per+
                Ms_es_left_per)/4)*0.3);
        double ankleleft=(((Ms_wd_left_per+Ms_wp_left_per+Ms_wr_left_per+
                Ms_wu_left_per)/4)*0.3);
        double msleft = hipleft+kneeleft+ankleleft;
        double mcright= add(romright , msright);
        double mcleft= add(romleft,msleft);
        double mc= add(mcright,mcleft);
//Coordinate calculations
        int coordinate_lifting = Integer.parseInt(upperextrimityform.getCoordinate_lifting());
        int coordinate_touching = Integer.parseInt(upperextrimityform.getCoordinate_touching());
        int coordinate_eating = Integer.parseInt(upperextrimityform.getCoordinate_eating());
        int coordinate_combing = Integer.parseInt(upperextrimityform.getCoordinate_combing());
        int coordinate_putting = Integer.parseInt(upperextrimityform.getCoordinate_putting());
        int coordinate_ablution = Integer.parseInt(upperextrimityform.getCoordinate_ablution());
        int coordinate_buttoning = Integer.parseInt(upperextrimityform.getCoordinate_buttoning());
        int coordinate_tie = Integer.parseInt(upperextrimityform.getCoordinate_tie());
        int coordinate_writing = Integer.parseInt(upperextrimityform.getCoordinate_writing());
        int coordinate_drinking = Integer.parseInt(upperextrimityform.getCoordinate_drinking());
        int coordinate =coordinate_lifting+coordinate_touching+
                coordinate_eating+coordinate_combing+
                coordinate_putting+coordinate_ablution+
                coordinate_buttoning+coordinate_tie+coordinate_writing+coordinate_drinking;
// total Arm Component  calculations
        double arm = add(mc,coordinate);
//Extra complications
        int com_inflection = Integer.parseInt(upperextrimityform.getCom_inflection());
        int com_Deformity =Integer .parseInt(upperextrimityform.getCom_Deformity());
        int com_Misalignment = Integer.parseInt(upperextrimityform.getCom_Misalignment());
        int com_Contracture  = Integer.parseInt(upperextrimityform.getCom_Contracture());
        int com_LossofCosmeticappearance  = Integer.parseInt(upperextrimityform.getCom_LossofCosmeticappearance());
        int com_domionantupperextremity = Integer.parseInt(upperextrimityform.getCom_domionantupperextremity());
        float inches = Float.parseFloat(upperextrimityform.getInches());
        if(inches<=1){
            shortining=0;
        }else{
            shortining=(inches-1)*2;
        }
        double complication =com_inflection+com_Deformity+com_Misalignment+
                com_Contracture+ com_LossofCosmeticappearance ;
        double totalextra=complication+com_domionantupperextremity+shortining;
//Hand component calculations
        int Hand_opindex_right = Integer.parseInt(upperextrimityform.getHand_opindex_right());
        int Hand_opmiddle_right = Integer.parseInt(upperextrimityform.getHand_opmiddle_right());
        int Hand_opring_right = Integer.parseInt(upperextrimityform.getHand_opring_right());
        int Hand_oplittle_right = Integer.parseInt(upperextrimityform.getHand_oplittle_right());
        int Hand_opindex_left = Integer.parseInt(upperextrimityform.getHand_opindex_left());
        int Hand_opmiddle_left = Integer.parseInt(upperextrimityform.getHand_opmiddle_left());
        int Hand_opring_left = Integer.parseInt(upperextrimityform.getHand_opring_left());
        int Hand_oplittle_left = Integer.parseInt(upperextrimityform.getHand_oplittle_left());
        int Hand_lakey_right = Integer.parseInt(upperextrimityform.getHand_lakey_right());
        int Hand_lakey_left = Integer.parseInt(upperextrimityform.getHand_lakey_left());
        int Hand_cylarge_right = Integer.parseInt(upperextrimityform.getHand_cylarge_right());
        int Hand_cysmall_right = Integer.parseInt(upperextrimityform.getHand_cysmall_right());
        int Hand_cylarge_left = Integer.parseInt(upperextrimityform.getHand_cylarge_left());
        int Hand_cysmall_left = Integer.parseInt(upperextrimityform.getHand_cysmall_left());
        int Hand_splarge_right = Integer.parseInt(upperextrimityform.getHand_splarge_right());
        int Hand_spsmall_right = Integer.parseInt(upperextrimityform.getHand_spsmall_right());
        int Hand_splarge_left = Integer.parseInt(upperextrimityform.getHand_splarge_left());
        int Hand_spsmall_left = Integer.parseInt(upperextrimityform.getHand_spsmall_left());
        int Hand_hook_right = Integer.parseInt(upperextrimityform.getHand_hook_right());
        int Hand_hook_left = Integer.parseInt(upperextrimityform.getHand_hook_left());
        int Hand_sethumb_right = Integer.parseInt(upperextrimityform.getHand_sethumb_right());
        int Hand_seindex_right = Integer.parseInt(upperextrimityform.getHand_seindex_right());
        int Hand_semiddle_right = Integer.parseInt(upperextrimityform.getHand_semiddle_right());
        int Hand_sering_right = Integer.parseInt(upperextrimityform.getHand_sering_right());
        int Hand_selittle_right = Integer.parseInt(upperextrimityform.getHand_selittle_right());
        int Hand_sethumb_left = Integer.parseInt(upperextrimityform.getHand_sethumb_left());
        int Hand_seindex_left = Integer.parseInt(upperextrimityform.getHand_seindex_left());
        int Hand_semiddle_left = Integer.parseInt(upperextrimityform.getHand_semiddle_left());
        int Hand_sering_left = Integer.parseInt(upperextrimityform.getHand_sering_left());
        int Hand_selittle_left = Integer.parseInt(upperextrimityform.getHand_selittle_left());
        int Hand_stgrip_right= Integer.parseInt(upperextrimityform.getHand_stgrip_right());
        int Hand_stpinch_right= Integer.parseInt(upperextrimityform.getHand_stpinch_right());
        int Hand_stgrip_left= Integer.parseInt(upperextrimityform.getHand_stgrip_left());
        int Hand_stpinch_left= Integer.parseInt(upperextrimityform.getHand_stpinch_left());
        int index= count(Hand_opindex_right+ Hand_opindex_left);
        int middle=count(Hand_opmiddle_right+Hand_opmiddle_left);
        int ring=count(Hand_opring_right+Hand_opring_left);
        int little=count(Hand_oplittle_right+Hand_oplittle_left);
        int keyholding=count1(Hand_lakey_right+Hand_lakey_left);
        int cylarge = count2(Hand_cylarge_right+Hand_cylarge_left);
        int cysmall = count2(Hand_cysmall_right+Hand_cysmall_left);
        int splarge = count2(Hand_splarge_right+Hand_splarge_left);
        int spsmall = count2(Hand_spsmall_right+Hand_spsmall_left);
        int hook = count1(Hand_hook_right+Hand_hook_left);
        int prehention=index+middle+ring+little+keyholding+cylarge+cysmall+
                splarge+spsmall+hook;
        int senthumb= count3(Hand_sethumb_right+Hand_sethumb_left);
        int senindex= count6(Hand_seindex_right+Hand_seindex_left);
        int senmiddle= count1(Hand_semiddle_right+Hand_semiddle_left);
        int senring =count1(Hand_sering_right+Hand_sering_left);
        int senlittle= count1(Hand_selittle_right+Hand_selittle_left);
        int sensation=senthumb+senmiddle+senindex+senring+senlittle;
        int strgrip= count4(Hand_stgrip_right+Hand_stgrip_left);
        int strpinch = count5(Hand_stpinch_right+Hand_stpinch_left);
        int strength=strgrip+strpinch;
        int hand = prehention+ sensation+strength;
//Adding arm and hand component(arm component + Hand component)
        double upper= add(arm , hand);
//upperextrimity =(Arm component + Hand component + Extras)
        if(complication>10){
            extradata= 10+
                    com_domionantupperextremity+shortining;
            upperextrimity= add(upper,extradata);
        }else if(complication<=10){
            extradata= complication+
                    com_domionantupperextremity+shortining;
            upperextrimity= add(upper,extradata);
        }

        upperextrimityform.setUpperExterimity_total((upperextrimity));
        upperextrimityform.setPrehention((String.valueOf(prehention)));
        upperextrimityform.setSensation((String.valueOf(sensation)));
        upperextrimityform.setStrength((String.valueOf(strength)));
        upperextrimityform.setRomleft((String.valueOf(romleft)));
        upperextrimityform.setRomright((String.valueOf(romright)));
        upperextrimityform.setMsleft((String.valueOf(msleft)));
        upperextrimityform.setMsright((String.valueOf(msright)));
        upperextrimityform.setCoordinate((String.valueOf(coordinate)));
        upperextrimityform.setTotalextra((String.valueOf(totalextra)));
        try{
        BeanUtils.copyProperties(upperExtrimitydto,upperextrimityform);
        }
         catch (InvocationTargetException ex) {
                ex.printStackTrace();
                 throw new SADAREMException();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
                 throw new SADAREMException();
            }
       //HttpSession session=request.getSession();
       // session.setAttribute("upperextrimity",new Double(upperextrimity));
        return upperExtrimitydto;
    }
     public double add(double a,double b){
         double add = 0;
        if(a>b){
            add=cal(a,b);
        }else{
            add= cal(b,a);
        }
        return add;
    }
    public int msMethod(int input) {
        int input1 = 0;
        switch (input) {
            case 0: input1=100; break;
            case 1: input1=80; break;
            case 2: input1=60; break;
            case 3: input1=40; break;
            case 4: input1=20; break;
            case 5: input1=0; break;
        }
        return input1;
    }
    public double cal(double a,double b) {
       double right= a+(((90-a)/90)*b);
        return right;
    }
    public int count(int a){
        int value = 0;
        if((a==4)||(a==2)) {
            value = 2;
        }else{
            value = 0;
        }
        return value ;
    }
    public int count1(int a){
        int value = 0;
        if((a==10)||(a==5)) {
            value = 5;
        }else{
            value = 0;
        }
        return value ;
    }
    public int count2(int a){
        int value = 0;
        if((a==6)||(a==3)) {
            value = 3;
        }else{
            value = 0;
        }
        return value ;
    }
    public int count3(int a){
        int value = 0;
        if((a==18)||(a==9)) {
            value = 9;
        }else{
            value = 0;
        }
        return value ;
    }
    public int count4(int a){
        int value = 0;
        if((a==40)||(a==20)) {
            value = 20;
        }else{
            value = 0;
        }
        return value ;
    }
    public int count5(int a){
        int value = 0;
        if((a==20)||(a==10)) {
            value = 10;
        }else{
            value = 0;
        }
        return value ;
    }
    public int count6(int a){
        int value = 0;
        if((a==12)||(a==6)) {
            value = 6;
        }else{
            value = 0;
        }
        return value ;
    }
}
