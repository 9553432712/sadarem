/*
 * UpperExtrimityDto.java
 *
 * Created on June 11, 2008, 11:09 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.dto;

import java.io.Serializable;

/**
 * This class contains the fields, required to compute Upper Extremity Disability type
 * @author Ganesh
 * @version 1.0
 */
public class UpperExtrimityDto implements Serializable {
    private String systemip=null;
    private String personcode;
    private String loginid;
            private double UpperExterimity_total;
            
    //this is for ROM
        private String Rom_sf_right=null;
        private String Rom_sr_right=null;
        private String Rom_sa_right=null;
        private String Rom_sf_left=null;
        private String Rom_sr_left=null;
        private String Rom_sa_left=null;
        private String Rom_ef_right=null;
        private String Rom_es_right=null;
        private String Rom_ef_left=null;
        private String Rom_es_left=null;
        private String Rom_wd_right=null;
        private String Rom_wr_right=null;
        private String Rom_wd_left=null;
        private String Rom_wr_left=null;
//this is for Muscle strength
        private String Ms_sf_right =null;
        private String Ms_se_right =null;
        private String Ms_sab_right =null;
        private String Ms_sad_right =null;
        private String Ms_sext_right =null;
        private String Ms_sint_right =null;
        private String Ms_sf_left =null;
        private String Ms_se_left =null;
        private String Ms_sab_left =null;
        private String Ms_sad_left =null;
        private String Ms_sext_left =null;
        private String Ms_sint_left =null;
        private String Ms_ef_right =null;
        private String Ms_ee_right =null;
        private String Ms_ep_right =null;
        private String Ms_es_right =null;
        private String Ms_ef_left =null;
        private String Ms_ee_left =null;
        private String Ms_ep_left =null;
        private String Ms_es_left =null;
        private String Ms_wd_right =null;
        private String Ms_wp_right =null;
        private String Ms_wr_right =null;
        private String Ms_wu_right =null;
        private String Ms_wd_left =null;
        private String Ms_wp_left =null;
        private String Ms_wr_left =null;
        private String Ms_wu_left =null;
//check box varible for coordinated activities
        private String coordinate_lifting;
        private String coordinate_touching;
        private String coordinate_eating;
        private String coordinate_combing;
        private String coordinate_putting;
        private String coordinate_ablution;
        private String coordinate_buttoning;
        private String coordinate_tie;
        private String coordinate_writing;
        // New field added by siva
        private String coordinate_drinking;

        
//check box variables for Hand component      
        private String Hand_opindex_right;
        private String Hand_opindex_left;
        private String Hand_opmiddle_right;
        private String Hand_opmiddle_left;
        private String Hand_opring_right;
        private String Hand_opring_left;
        private String Hand_oplittle_right;
        private String Hand_oplittle_left;
        private String Hand_lakey_right;
        private String Hand_lakey_left;    
        private String Hand_cylarge_right;
        private String Hand_cylarge_left;
        private String Hand_cysmall_right;
        private String Hand_cysmall_left;
        private String Hand_splarge_right;
        private String Hand_splarge_left;
        private String Hand_spsmall_right;
        private String Hand_spsmall_left;
        private String Hand_hook_right;
        private String Hand_hook_left;    
        private String Hand_sethumb_right;
        private String Hand_sethumb_left;
        private String Hand_seindex_right;
        private String Hand_seindex_left;
        private String Hand_semiddle_right;
        private String Hand_semiddle_left;
        private String Hand_sering_right;
        private String Hand_sering_left;
        private String Hand_selittle_right;
        private String Hand_selittle_left; 
        private String Hand_stgrip_right;
        private String Hand_stgrip_left;
        private String Hand_stpinch_right;
        private String Hand_stpinch_left;
//radio button varible
        private String com_inflection=null;
        private String com_Deformity=null;
        private String com_Misalignment=null;
        private String com_Contracture=null;
        private String com_LossofCosmeticappearance=null;    
//check boxlast        
        private String com_domionantupperextremity=null;
        
//Needs Assesssment
       private String interventionservices;
        private String physiotheraphy;  
        private String occupationaltherapy;
         private String physiotherapy;
         private String occupationaltheraphy;
        
         private String selectwheelchair;
        private String selecttricycle;
        private String selectwalkingstick;        
        private String selectcrutches;
        private String selectwalkingframe;
        private String aeroplanesplint;
        private String figure8splint;
       private String forearmsplint;
       private String handsplint;
        private String shoulderprosthesis;
       private String aboveelbowprosthesis;
       private String elbowdisarticulationprosthesis;
       private String belowelbowprosthesis;
       private String wristdisarticulationprosthesis;
       private String handprosthesis;
       private String cosmeticfingerprosthesis;
        
        
        
        
        
        
        
//inches 
        private String inches=null; 
 private String  prehention;
    private String  sensation;
    private String  strength;
    private String  romright;
    private String  romleft;
    private String  msright;
    private String  msleft;
    private String  coordinate;
    private String  totalextra;
    public String getRom_sf_right() {
        return Rom_sf_right;
    }

    public void setRom_sf_right(String Rom_sf_right) {
        this.Rom_sf_right = Rom_sf_right;
    }

    public String getRom_sr_right() {
        return Rom_sr_right;
    }

    public void setRom_sr_right(String Rom_sr_right) {
        this.Rom_sr_right = Rom_sr_right;
    }

    public String getRom_sa_right() {
        return Rom_sa_right;
    }

    public void setRom_sa_right(String Rom_sa_right) {
        this.Rom_sa_right = Rom_sa_right;
    }

    public String getRom_sf_left() {
        return Rom_sf_left;
    }

    public void setRom_sf_left(String Rom_sf_left) {
        this.Rom_sf_left = Rom_sf_left;
    }

    public String getRom_sr_left() {
        return Rom_sr_left;
    }

    public void setRom_sr_left(String Rom_sr_left) {
        this.Rom_sr_left = Rom_sr_left;
    }

    public String getRom_sa_left() {
        return Rom_sa_left;
    }

    public void setRom_sa_left(String Rom_sa_left) {
        this.Rom_sa_left = Rom_sa_left;
    }

    public String getRom_ef_right() {
        return Rom_ef_right;
    }

    public void setRom_ef_right(String Rom_ef_right) {
        this.Rom_ef_right = Rom_ef_right;
    }

    public String getRom_es_right() {
        return Rom_es_right;
    }

    public void setRom_es_right(String Rom_es_right) {
        this.Rom_es_right = Rom_es_right;
    }

    public String getRom_ef_left() {
        return Rom_ef_left;
    }

    public void setRom_ef_left(String Rom_ef_left) {
        this.Rom_ef_left = Rom_ef_left;
    }

    public String getRom_es_left() {
        return Rom_es_left;
    }

    public void setRom_es_left(String Rom_es_left) {
        this.Rom_es_left = Rom_es_left;
    }

    public String getRom_wd_right() {
        return Rom_wd_right;
    }

    public void setRom_wd_right(String Rom_wd_right) {
        this.Rom_wd_right = Rom_wd_right;
    }

    public String getRom_wr_right() {
        return Rom_wr_right;
    }

    public void setRom_wr_right(String Rom_wr_right) {
        this.Rom_wr_right = Rom_wr_right;
    }

    public String getRom_wd_left() {
        return Rom_wd_left;
    }

    public void setRom_wd_left(String Rom_wd_left) {
        this.Rom_wd_left = Rom_wd_left;
    }

    public String getRom_wr_left() {
        return Rom_wr_left;
    }

    public void setRom_wr_left(String Rom_wr_left) {
        this.Rom_wr_left = Rom_wr_left;
    }

    public String getMs_sf_right() {
        return Ms_sf_right;
    }

    public void setMs_sf_right(String Ms_sf_right) {
        this.Ms_sf_right = Ms_sf_right;
    }

    public String getMs_se_right() {
        return Ms_se_right;
    }

    public void setMs_se_right(String Ms_se_right) {
        this.Ms_se_right = Ms_se_right;
    }

    public String getMs_sab_right() {
        return Ms_sab_right;
    }

    public void setMs_sab_right(String Ms_sab_right) {
        this.Ms_sab_right = Ms_sab_right;
    }

    public String getMs_sad_right() {
        return Ms_sad_right;
    }

    public void setMs_sad_right(String Ms_sad_right) {
        this.Ms_sad_right = Ms_sad_right;
    }

    public String getMs_sext_right() {
        return Ms_sext_right;
    }

    public void setMs_sext_right(String Ms_sext_right) {
        this.Ms_sext_right = Ms_sext_right;
    }

    public String getMs_sint_right() {
        return Ms_sint_right;
    }

    public void setMs_sint_right(String Ms_sint_right) {
        this.Ms_sint_right = Ms_sint_right;
    }

    public String getMs_sf_left() {
        return Ms_sf_left;
    }

    public void setMs_sf_left(String Ms_sf_left) {
        this.Ms_sf_left = Ms_sf_left;
    }

    public String getMs_se_left() {
        return Ms_se_left;
    }

    public void setMs_se_left(String Ms_se_left) {
        this.Ms_se_left = Ms_se_left;
    }

    public String getMs_sab_left() {
        return Ms_sab_left;
    }

    public void setMs_sab_left(String Ms_sab_left) {
        this.Ms_sab_left = Ms_sab_left;
    }

    public String getMs_sad_left() {
        return Ms_sad_left;
    }

    public void setMs_sad_left(String Ms_sad_left) {
        this.Ms_sad_left = Ms_sad_left;
    }

    public String getMs_sext_left() {
        return Ms_sext_left;
    }

    public void setMs_sext_left(String Ms_sext_left) {
        this.Ms_sext_left = Ms_sext_left;
    }

    public String getMs_sint_left() {
        return Ms_sint_left;
    }

    public void setMs_sint_left(String Ms_sint_left) {
        this.Ms_sint_left = Ms_sint_left;
    }

    public String getMs_ef_right() {
        return Ms_ef_right;
    }

    public void setMs_ef_right(String Ms_ef_right) {
        this.Ms_ef_right = Ms_ef_right;
    }

    public String getMs_ee_right() {
        return Ms_ee_right;
    }

    public void setMs_ee_right(String Ms_ee_right) {
        this.Ms_ee_right = Ms_ee_right;
    }

    public String getMs_ep_right() {
        return Ms_ep_right;
    }

    public void setMs_ep_right(String Ms_ep_right) {
        this.Ms_ep_right = Ms_ep_right;
    }

    public String getMs_es_right() {
        return Ms_es_right;
    }

    public void setMs_es_right(String Ms_es_right) {
        this.Ms_es_right = Ms_es_right;
    }

    public String getMs_ef_left() {
        return Ms_ef_left;
    }

    public void setMs_ef_left(String Ms_ef_left) {
        this.Ms_ef_left = Ms_ef_left;
    }

    public String getMs_ee_left() {
        return Ms_ee_left;
    }

    public void setMs_ee_left(String Ms_ee_left) {
        this.Ms_ee_left = Ms_ee_left;
    }

    public String getMs_ep_left() {
        return Ms_ep_left;
    }

    public void setMs_ep_left(String Ms_ep_left) {
        this.Ms_ep_left = Ms_ep_left;
    }

    public String getMs_es_left() {
        return Ms_es_left;
    }

    public void setMs_es_left(String Ms_es_left) {
        this.Ms_es_left = Ms_es_left;
    }

    public String getMs_wd_right() {
        return Ms_wd_right;
    }

    public void setMs_wd_right(String Ms_wd_right) {
        this.Ms_wd_right = Ms_wd_right;
    }

    public String getMs_wp_right() {
        return Ms_wp_right;
    }

    public void setMs_wp_right(String Ms_wp_right) {
        this.Ms_wp_right = Ms_wp_right;
    }

    public String getMs_wr_right() {
        return Ms_wr_right;
    }

    public void setMs_wr_right(String Ms_wr_right) {
        this.Ms_wr_right = Ms_wr_right;
    }

    public String getMs_wu_right() {
        return Ms_wu_right;
    }

    public void setMs_wu_right(String Ms_wu_right) {
        this.Ms_wu_right = Ms_wu_right;
    }

    public String getMs_wd_left() {
        return Ms_wd_left;
    }

    public void setMs_wd_left(String Ms_wd_left) {
        this.Ms_wd_left = Ms_wd_left;
    }

    public String getMs_wp_left() {
        return Ms_wp_left;
    }

    public void setMs_wp_left(String Ms_wp_left) {
        this.Ms_wp_left = Ms_wp_left;
    }

    public String getMs_wr_left() {
        return Ms_wr_left;
    }

    public void setMs_wr_left(String Ms_wr_left) {
        this.Ms_wr_left = Ms_wr_left;
    }

    public String getMs_wu_left() {
        return Ms_wu_left;
    }

    public void setMs_wu_left(String Ms_wu_left) {
        this.Ms_wu_left = Ms_wu_left;
    }

   

    public String getHand_opindex_right() {
        return Hand_opindex_right;
    }

    public void setHand_opindex_right(String Hand_opindex_right) {
        this.Hand_opindex_right = Hand_opindex_right;
    }

    public String getHand_opindex_left() {
        return Hand_opindex_left;
    }

    public void setHand_opindex_left(String Hand_opindex_left) {
        this.Hand_opindex_left = Hand_opindex_left;
    }

    public String getHand_opmiddle_right() {
        return Hand_opmiddle_right;
    }

    public void setHand_opmiddle_right(String Hand_opmiddle_right) {
        this.Hand_opmiddle_right = Hand_opmiddle_right;
    }

    public String getHand_opmiddle_left() {
        return Hand_opmiddle_left;
    }

    public void setHand_opmiddle_left(String Hand_opmiddle_left) {
        this.Hand_opmiddle_left = Hand_opmiddle_left;
    }

    public String getHand_opring_right() {
        return Hand_opring_right;
    }

    public void setHand_opring_right(String Hand_opring_right) {
        this.Hand_opring_right = Hand_opring_right;
    }

    public String getHand_opring_left() {
        return Hand_opring_left;
    }

    public void setHand_opring_left(String Hand_opring_left) {
        this.Hand_opring_left = Hand_opring_left;
    }

    public String getHand_oplittle_right() {
        return Hand_oplittle_right;
    }

    public void setHand_oplittle_right(String Hand_oplittle_right) {
        this.Hand_oplittle_right = Hand_oplittle_right;
    }

    public String getHand_oplittle_left() {
        return Hand_oplittle_left;
    }

    public void setHand_oplittle_left(String Hand_oplittle_left) {
        this.Hand_oplittle_left = Hand_oplittle_left;
    }

    public String getHand_lakey_right() {
        return Hand_lakey_right;
    }

    public void setHand_lakey_right(String Hand_lakey_right) {
        this.Hand_lakey_right = Hand_lakey_right;
    }

    public String getHand_lakey_left() {
        return Hand_lakey_left;
    }

    public void setHand_lakey_left(String Hand_lakey_left) {
        this.Hand_lakey_left = Hand_lakey_left;
    }

    public String getHand_cylarge_right() {
        return Hand_cylarge_right;
    }

    public void setHand_cylarge_right(String Hand_cylarge_right) {
        this.Hand_cylarge_right = Hand_cylarge_right;
    }

    public String getHand_cylarge_left() {
        return Hand_cylarge_left;
    }

    public void setHand_cylarge_left(String Hand_cylarge_left) {
        this.Hand_cylarge_left = Hand_cylarge_left;
    }

    public String getHand_cysmall_right() {
        return Hand_cysmall_right;
    }

    public void setHand_cysmall_right(String Hand_cysmall_right) {
        this.Hand_cysmall_right = Hand_cysmall_right;
    }

    public String getHand_cysmall_left() {
        return Hand_cysmall_left;
    }

    public void setHand_cysmall_left(String Hand_cysmall_left) {
        this.Hand_cysmall_left = Hand_cysmall_left;
    }

    public String getHand_splarge_right() {
        return Hand_splarge_right;
    }

    public void setHand_splarge_right(String Hand_splarge_right) {
        this.Hand_splarge_right = Hand_splarge_right;
    }

    public String getHand_splarge_left() {
        return Hand_splarge_left;
    }

    public void setHand_splarge_left(String Hand_splarge_left) {
        this.Hand_splarge_left = Hand_splarge_left;
    }

    public String getHand_spsmall_right() {
        return Hand_spsmall_right;
    }

    public void setHand_spsmall_right(String Hand_spsmall_right) {
        this.Hand_spsmall_right = Hand_spsmall_right;
    }

    public String getHand_spsmall_left() {
        return Hand_spsmall_left;
    }

    public void setHand_spsmall_left(String Hand_spsmall_left) {
        this.Hand_spsmall_left = Hand_spsmall_left;
    }

    public String getHand_hook_right() {
        return Hand_hook_right;
    }

    public void setHand_hook_right(String Hand_hook_right) {
        this.Hand_hook_right = Hand_hook_right;
    }

    public String getHand_hook_left() {
        return Hand_hook_left;
    }

    public void setHand_hook_left(String Hand_hook_left) {
        this.Hand_hook_left = Hand_hook_left;
    }

    public String getHand_sethumb_right() {
        return Hand_sethumb_right;
    }

    public void setHand_sethumb_right(String Hand_sethumb_right) {
        this.Hand_sethumb_right = Hand_sethumb_right;
    }

    public String getHand_sethumb_left() {
        return Hand_sethumb_left;
    }

    public void setHand_sethumb_left(String Hand_sethumb_left) {
        this.Hand_sethumb_left = Hand_sethumb_left;
    }

    public String getHand_seindex_right() {
        return Hand_seindex_right;
    }

    public void setHand_seindex_right(String Hand_seindex_right) {
        this.Hand_seindex_right = Hand_seindex_right;
    }

    public String getHand_seindex_left() {
        return Hand_seindex_left;
    }

    public void setHand_seindex_left(String Hand_seindex_left) {
        this.Hand_seindex_left = Hand_seindex_left;
    }

    public String getHand_semiddle_right() {
        return Hand_semiddle_right;
    }

    public void setHand_semiddle_right(String Hand_semiddle_right) {
        this.Hand_semiddle_right = Hand_semiddle_right;
    }

    public String getHand_semiddle_left() {
        return Hand_semiddle_left;
    }

    public void setHand_semiddle_left(String Hand_semiddle_left) {
        this.Hand_semiddle_left = Hand_semiddle_left;
    }

    public String getHand_sering_right() {
        return Hand_sering_right;
    }

    public void setHand_sering_right(String Hand_sering_right) {
        this.Hand_sering_right = Hand_sering_right;
    }

    public String getHand_sering_left() {
        return Hand_sering_left;
    }

    public void setHand_sering_left(String Hand_sering_left) {
        this.Hand_sering_left = Hand_sering_left;
    }

    public String getHand_selittle_right() {
        return Hand_selittle_right;
    }

    public void setHand_selittle_right(String Hand_selittle_right) {
        this.Hand_selittle_right = Hand_selittle_right;
    }

    public String getHand_selittle_left() {
        return Hand_selittle_left;
    }

    public void setHand_selittle_left(String Hand_selittle_left) {
        this.Hand_selittle_left = Hand_selittle_left;
    }

  

  
    public String getHand_stpinch_right() {
        return Hand_stpinch_right;
    }

    public void setHand_stpinch_right(String Hand_stpinch_right) {
        this.Hand_stpinch_right = Hand_stpinch_right;
    }

    public String getHand_stpinch_left() {
        return Hand_stpinch_left;
    }

    public void setHand_stpinch_left(String Hand_stpinch_left) {
        this.Hand_stpinch_left = Hand_stpinch_left;
    }

    public String getCom_inflection() {
        return com_inflection;
    }

    public void setCom_inflection(String com_inflection) {
        this.com_inflection = com_inflection;
    }

    public String getCom_Deformity() {
        return com_Deformity;
    }

    public void setCom_Deformity(String com_Deformity) {
        this.com_Deformity = com_Deformity;
    }

    public String getCom_Misalignment() {
        return com_Misalignment;
    }

    public void setCom_Misalignment(String com_Misalignment) {
        this.com_Misalignment = com_Misalignment;
    }

    public String getCom_Contracture() {
        return com_Contracture;
    }

    public void setCom_Contracture(String com_Contracture) {
        this.com_Contracture = com_Contracture;
    }

    public String getCom_LossofCosmeticappearance() {
        return com_LossofCosmeticappearance;
    }

    public void setCom_LossofCosmeticappearance(String com_LossofCosmeticappearance) {
        this.com_LossofCosmeticappearance = com_LossofCosmeticappearance;
    }

    public String getCom_domionantupperextremity() {
        return com_domionantupperextremity;
    }

    public void setCom_domionantupperextremity(String com_domionantupperextremity) {
        this.com_domionantupperextremity = com_domionantupperextremity;
    }

    public String getInches() {
        return inches;
    }

    public void setInches(String inches) {
        this.inches = inches;
    }

    public String getCoordinate_lifting() {
        return coordinate_lifting;
    }

    public void setCoordinate_lifting(String coordinate_lifting) {
        this.coordinate_lifting = coordinate_lifting;
    }

    public String getCoordinate_touching() {
        return coordinate_touching;
    }

    public void setCoordinate_touching(String coordinate_touching) {
        this.coordinate_touching = coordinate_touching;
    }

    public String getCoordinate_eating() {
        return coordinate_eating;
    }

    public void setCoordinate_eating(String coordinate_eating) {
        this.coordinate_eating = coordinate_eating;
    }

    public String getCoordinate_combing() {
        return coordinate_combing;
    }

    public void setCoordinate_combing(String coordinate_combing) {
        this.coordinate_combing = coordinate_combing;
    }

    public String getCoordinate_putting() {
        return coordinate_putting;
    }

    public void setCoordinate_putting(String coordinate_putting) {
        this.coordinate_putting = coordinate_putting;
    }

    public String getCoordinate_ablution() {
        return coordinate_ablution;
    }

    public void setCoordinate_ablution(String coordinate_ablution) {
        this.coordinate_ablution = coordinate_ablution;
    }

    public String getCoordinate_buttoning() {
        return coordinate_buttoning;
    }

    public void setCoordinate_buttoning(String coordinate_buttoning) {
        this.coordinate_buttoning = coordinate_buttoning;
    }

    public String getCoordinate_tie() {
        return coordinate_tie;
    }

    public void setCoordinate_tie(String coordinate_tie) {
        this.coordinate_tie = coordinate_tie;
    }

    public String getCoordinate_writing() {
        return coordinate_writing;
    }

    public void setCoordinate_writing(String coordinate_writing) {
        this.coordinate_writing = coordinate_writing;
    }

    public String getHand_stgrip_right() {
        return Hand_stgrip_right;
    }

    public void setHand_stgrip_right(String Hand_stgrip_right) {
        this.Hand_stgrip_right = Hand_stgrip_right;
    }

    public String getHand_stgrip_left() {
        return Hand_stgrip_left;
    }

    public void setHand_stgrip_left(String Hand_stgrip_left) {
        this.Hand_stgrip_left = Hand_stgrip_left;
    }

    public String getSystemip() {
        return systemip;
    }

    public void setSystemip(String systemip) {
        this.systemip = systemip;
    }

    public String getPrehention() {
        return prehention;
    }

    public void setPrehention(String prehention) {
        this.prehention = prehention;
    }

    public String getSensation() {
        return sensation;
    }

    public void setSensation(String sensation) {
        this.sensation = sensation;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getRomright() {
        return romright;
    }

    public void setRomright(String romright) {
        this.romright = romright;
    }

    public String getRomleft() {
        return romleft;
    }

    public void setRomleft(String romleft) {
        this.romleft = romleft;
    }

    public String getMsright() {
        return msright;
    }

    public void setMsright(String msright) {
        this.msright = msright;
    }

    public String getMsleft() {
        return msleft;
    }

    public void setMsleft(String msleft) {
        this.msleft = msleft;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getTotalextra() {
        return totalextra;
    }

    public void setTotalextra(String totalextra) {
        this.totalextra = totalextra;
    }

  /*  public String getUpperExterimity_total() {
        return UpperExterimity_total;
    }

    public void setUpperExterimity_total(String UpperExterimity_total) {
        this.UpperExterimity_total = UpperExterimity_total;
    }
*/
    public String getPersoncode() {
        return personcode;
    }

    public void setPersoncode(String personcode) {
        this.personcode = personcode;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public double getUpperExterimity_total() {
        return UpperExterimity_total;
    }

    public void setUpperExterimity_total(double UpperExterimity_total) {
        this.UpperExterimity_total = UpperExterimity_total;
    }

    

    public String getInterventionservices() {
        return interventionservices;
    }

    public void setInterventionservices(String interventionservices) {
        this.interventionservices = interventionservices;
    }

    public String getPhysiotheraphy() {
        return physiotheraphy;
    }

    public void setPhysiotheraphy(String physiotheraphy) {
        this.physiotheraphy = physiotheraphy;
    }

    public String getOccupationaltherapy() {
        return occupationaltherapy;
    }

    public void setOccupationaltherapy(String occupationaltherapy) {
        this.occupationaltherapy = occupationaltherapy;
    }

    public String getPhysiotherapy() {
        return physiotherapy;
    }

    public void setPhysiotherapy(String physiotherapy) {
        this.physiotherapy = physiotherapy;
    }

    public String getOccupationaltheraphy() {
        return occupationaltheraphy;
    }

    public void setOccupationaltheraphy(String occupationaltheraphy) {
        this.occupationaltheraphy = occupationaltheraphy;
    }

    public String getSelectwheelchair() {
        return selectwheelchair;
    }

    public void setSelectwheelchair(String selectwheelchair) {
        this.selectwheelchair = selectwheelchair;
    }

    public String getSelecttricycle() {
        return selecttricycle;
    }

    public void setSelecttricycle(String selecttricycle) {
        this.selecttricycle = selecttricycle;
    }

    public String getSelectwalkingstick() {
        return selectwalkingstick;
    }

    public void setSelectwalkingstick(String selectwalkingstick) {
        this.selectwalkingstick = selectwalkingstick;
    }

    public String getSelectcrutches() {
        return selectcrutches;
    }

    public void setSelectcrutches(String selectcrutches) {
        this.selectcrutches = selectcrutches;
    }

    public String getSelectwalkingframe() {
        return selectwalkingframe;
    }

    public void setSelectwalkingframe(String selectwalkingframe) {
        this.selectwalkingframe = selectwalkingframe;
    }

    public String getAeroplanesplint() {
        return aeroplanesplint;
    }

    public void setAeroplanesplint(String aeroplanesplint) {
        this.aeroplanesplint = aeroplanesplint;
    }

    public String getFigure8splint() {
        return figure8splint;
    }

    public void setFigure8splint(String figure8splint) {
        this.figure8splint = figure8splint;
    }

    public String getForearmsplint() {
        return forearmsplint;
    }

    public void setForearmsplint(String forearmsplint) {
        this.forearmsplint = forearmsplint;
    }

    public String getHandsplint() {
        return handsplint;
    }

    public void setHandsplint(String handsplint) {
        this.handsplint = handsplint;
    }

    public String getShoulderprosthesis() {
        return shoulderprosthesis;
    }

    public void setShoulderprosthesis(String shoulderprosthesis) {
        this.shoulderprosthesis = shoulderprosthesis;
    }

    public String getAboveelbowprosthesis() {
        return aboveelbowprosthesis;
    }

    public void setAboveelbowprosthesis(String aboveelbowprosthesis) {
        this.aboveelbowprosthesis = aboveelbowprosthesis;
    }

    public String getElbowdisarticulationprosthesis() {
        return elbowdisarticulationprosthesis;
    }

    public void setElbowdisarticulationprosthesis(String elbowdisarticulationprosthesis) {
        this.elbowdisarticulationprosthesis = elbowdisarticulationprosthesis;
    }

    public String getBelowelbowprosthesis() {
        return belowelbowprosthesis;
    }

    public void setBelowelbowprosthesis(String belowelbowprosthesis) {
        this.belowelbowprosthesis = belowelbowprosthesis;
    }

    public String getWristdisarticulationprosthesis() {
        return wristdisarticulationprosthesis;
    }

    public void setWristdisarticulationprosthesis(String wristdisarticulationprosthesis) {
        this.wristdisarticulationprosthesis = wristdisarticulationprosthesis;
    }

    public String getHandprosthesis() {
        return handprosthesis;
    }

    public void setHandprosthesis(String handprosthesis) {
        this.handprosthesis = handprosthesis;
    }

    public String getCosmeticfingerprosthesis() {
        return cosmeticfingerprosthesis;
    }

    public void setCosmeticfingerprosthesis(String cosmeticfingerprosthesis) {
        this.cosmeticfingerprosthesis = cosmeticfingerprosthesis;
    }

    /**
     * @return the coordinate_drinking
     */
    public String getCoordinate_drinking() {
        return coordinate_drinking;
    }

    /**
     * @param coordinate_drinking the coordinate_drinking to set
     */
    public void setCoordinate_drinking(String coordinate_drinking) {
        this.coordinate_drinking = coordinate_drinking;
    }
    
}
