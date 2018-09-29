/*
 * UpperExtremityBean.java
 *
 * Created on January 2, 2009, 2:45 PM
 *
 */

package org.bf.disability.bean;

/**
 *
 * @author Deviprasad_t
 */
public class UpperExtremityBean {
    
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
        private String hand_opindex_right;
        private String hand_opindex_left;
        private String hand_opmiddle_right;
        private String hand_opmiddle_left;
        private String hand_opring_right;
        private String hand_opring_left;
        private String hand_oplittle_right;
        private String hand_oplittle_left;
        private String hand_lakey_right;
        private String hand_lakey_left;    
        private String hand_cylarge_right;
        private String hand_cylarge_left;
        private String hand_cysmall_right;
        private String hand_cysmall_left;
        private String hand_splarge_right;
        private String hand_splarge_left;
        private String hand_spsmall_right;
        private String hand_spsmall_left;
        private String hand_hook_right;
        private String hand_hook_left;    
        private String hand_sethumb_right;
        private String hand_sethumb_left;
        private String hand_seindex_right;
        private String hand_seindex_left;
        private String hand_semiddle_right;
        private String hand_semiddle_left;
        private String hand_sering_right;
        private String hand_sering_left;
        private String hand_selittle_right;
        private String hand_selittle_left; 
        private String hand_stgrip_right;
        private String hand_stgrip_left;
        private String hand_stpinch_right;
        private String hand_stpinch_left;
//radio button varible
        private String com_inflection=null;
        private String com_Deformity=null;
        private String com_Misalignment=null;
        private String com_Contracture=null;
        private String com_LossofCosmeticappearance=null;    
//check boxlast        
        private String com_domionantupperextremity=null;
//inches 
        private String inches=null;  
    private String  prehention;
    private String  sensation;
    private String  strength;
  //  private String  romright;
   // private String  romleft;
    private String  msright;
    private String  msleft;
    private String  coordinate;
    private String  totalextra;
    
      private String hrom_sf_right=null ;
        private String hrom_sr_right=null;
        private String hrom_sa_right=null;
          private String hrom_sf_left=null ;
        private String hrom_sr_left=null;
        private String hrom_sa_left=null;
    
    private String romflexion=null;
    private String romrotation=null;
    private String romabduction=null;
    private String romshoulder=null;
    private String romhipr=null;
    private String romhipl =null;
    private String romshoulderright=null;
    private String romshoulderleft=null;
    
   private String romknee21r = null;
        private String romknee22r = null;
        private String romkneer = null;
        private String romknee21l = null;
        private String romknee22l =null;
        private String romkneel = null;
    private String romkeen=null;
        private String romkneeright=null;
       private String romkneeleft=null;
        private String romkneeflexion=null;
        private String romkneesupination=null;
        private String wristright=null;
        private String wristleft=null;
        private String wrist =null;
        
      private String arom_wd_right=null;
       
        private String rowankler= null;
         private String arom_wr_right = null;
        private String arom_wd_left = null;
        private String arom_wr_left = null;
        private String rowanklel= null;
        private String wristpalmar=null;
        private String wristulnar=null;
       private String rom=null;
       private String romright=null;
       private String romleft=null;
private String complication=null;
private String shortining=null;
private String extra=null;
private StringBuffer totalArmRight=null;
private StringBuffer totalArmLeft=null;
private StringBuffer totalArm=null;
private String ArmRight=null;
private String ArmLeft=null;
private String rightLeftArm=null;
private String arm=null;
private StringBuffer armRightLeft=null;
private String extraCoomplication=null;
  private StringBuffer upper=null;
  private StringBuffer upperExtremity = null;
  private String upperExtremityRound = null;
    public String getSystemip() {
        return systemip;
    }

    public void setSystemip(String systemip) {
        this.systemip = systemip;
    }

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

    public String getHand_opindex_right() {
        return hand_opindex_right;
    }

    public void setHand_opindex_right(String hand_opindex_right) {
        this.hand_opindex_right = hand_opindex_right;
    }

    public String getHand_opindex_left() {
        return hand_opindex_left;
    }

    public void setHand_opindex_left(String hand_opindex_left) {
        this.hand_opindex_left = hand_opindex_left;
    }

    public String getHand_opmiddle_right() {
        return hand_opmiddle_right;
    }

    public void setHand_opmiddle_right(String hand_opmiddle_right) {
        this.hand_opmiddle_right = hand_opmiddle_right;
    }

    public String getHand_opmiddle_left() {
        return hand_opmiddle_left;
    }

    public void setHand_opmiddle_left(String hand_opmiddle_left) {
        this.hand_opmiddle_left = hand_opmiddle_left;
    }

    public String getHand_opring_right() {
        return hand_opring_right;
    }

    public void setHand_opring_right(String hand_opring_right) {
        this.hand_opring_right = hand_opring_right;
    }

    public String getHand_opring_left() {
        return hand_opring_left;
    }

    public void setHand_opring_left(String hand_opring_left) {
        this.hand_opring_left = hand_opring_left;
    }

    public String getHand_oplittle_right() {
        return hand_oplittle_right;
    }

    public void setHand_oplittle_right(String hand_oplittle_right) {
        this.hand_oplittle_right = hand_oplittle_right;
    }

    public String getHand_oplittle_left() {
        return hand_oplittle_left;
    }

    public void setHand_oplittle_left(String hand_oplittle_left) {
        this.hand_oplittle_left = hand_oplittle_left;
    }

    public String getHand_lakey_right() {
        return hand_lakey_right;
    }

    public void setHand_lakey_right(String hand_lakey_right) {
        this.hand_lakey_right = hand_lakey_right;
    }

    public String getHand_lakey_left() {
        return hand_lakey_left;
    }

    public void setHand_lakey_left(String hand_lakey_left) {
        this.hand_lakey_left = hand_lakey_left;
    }

    public String getHand_cylarge_right() {
        return hand_cylarge_right;
    }

    public void setHand_cylarge_right(String hand_cylarge_right) {
        this.hand_cylarge_right = hand_cylarge_right;
    }

    public String getHand_cylarge_left() {
        return hand_cylarge_left;
    }

    public void setHand_cylarge_left(String hand_cylarge_left) {
        this.hand_cylarge_left = hand_cylarge_left;
    }

    public String getHand_cysmall_right() {
        return hand_cysmall_right;
    }

    public void setHand_cysmall_right(String hand_cysmall_right) {
        this.hand_cysmall_right = hand_cysmall_right;
    }

    public String getHand_cysmall_left() {
        return hand_cysmall_left;
    }

    public void setHand_cysmall_left(String hand_cysmall_left) {
        this.hand_cysmall_left = hand_cysmall_left;
    }

    public String getHand_splarge_right() {
        return hand_splarge_right;
    }

    public void setHand_splarge_right(String hand_splarge_right) {
        this.hand_splarge_right = hand_splarge_right;
    }

    public String getHand_splarge_left() {
        return hand_splarge_left;
    }

    public void setHand_splarge_left(String hand_splarge_left) {
        this.hand_splarge_left = hand_splarge_left;
    }

    public String getHand_spsmall_right() {
        return hand_spsmall_right;
    }

    public void setHand_spsmall_right(String hand_spsmall_right) {
        this.hand_spsmall_right = hand_spsmall_right;
    }

    public String getHand_spsmall_left() {
        return hand_spsmall_left;
    }

    public void setHand_spsmall_left(String hand_spsmall_left) {
        this.hand_spsmall_left = hand_spsmall_left;
    }

    public String getHand_hook_right() {
        return hand_hook_right;
    }

    public void setHand_hook_right(String hand_hook_right) {
        this.hand_hook_right = hand_hook_right;
    }

    public String getHand_hook_left() {
        return hand_hook_left;
    }

    public void setHand_hook_left(String hand_hook_left) {
        this.hand_hook_left = hand_hook_left;
    }

    public String getHand_sethumb_right() {
        return hand_sethumb_right;
    }

    public void setHand_sethumb_right(String hand_sethumb_right) {
        this.hand_sethumb_right = hand_sethumb_right;
    }

    public String getHand_sethumb_left() {
        return hand_sethumb_left;
    }

    public void setHand_sethumb_left(String hand_sethumb_left) {
        this.hand_sethumb_left = hand_sethumb_left;
    }

    public String getHand_seindex_right() {
        return hand_seindex_right;
    }

    public void setHand_seindex_right(String hand_seindex_right) {
        this.hand_seindex_right = hand_seindex_right;
    }

    public String getHand_seindex_left() {
        return hand_seindex_left;
    }

    public void setHand_seindex_left(String hand_seindex_left) {
        this.hand_seindex_left = hand_seindex_left;
    }

    public String getHand_semiddle_right() {
        return hand_semiddle_right;
    }

    public void setHand_semiddle_right(String hand_semiddle_right) {
        this.hand_semiddle_right = hand_semiddle_right;
    }

    public String getHand_semiddle_left() {
        return hand_semiddle_left;
    }

    public void setHand_semiddle_left(String hand_semiddle_left) {
        this.hand_semiddle_left = hand_semiddle_left;
    }

    public String getHand_sering_right() {
        return hand_sering_right;
    }

    public void setHand_sering_right(String hand_sering_right) {
        this.hand_sering_right = hand_sering_right;
    }

    public String getHand_sering_left() {
        return hand_sering_left;
    }

    public void setHand_sering_left(String hand_sering_left) {
        this.hand_sering_left = hand_sering_left;
    }

    public String getHand_selittle_right() {
        return hand_selittle_right;
    }

    public void setHand_selittle_right(String hand_selittle_right) {
        this.hand_selittle_right = hand_selittle_right;
    }

    public String getHand_selittle_left() {
        return hand_selittle_left;
    }

    public void setHand_selittle_left(String hand_selittle_left) {
        this.hand_selittle_left = hand_selittle_left;
    }

    public String getHand_stgrip_right() {
        return hand_stgrip_right;
    }

    public void setHand_stgrip_right(String hand_stgrip_right) {
        this.hand_stgrip_right = hand_stgrip_right;
    }

    public String getHand_stgrip_left() {
        return hand_stgrip_left;
    }

    public void setHand_stgrip_left(String hand_stgrip_left) {
        this.hand_stgrip_left = hand_stgrip_left;
    }

    public String getHand_stpinch_right() {
        return hand_stpinch_right;
    }

    public void setHand_stpinch_right(String hand_stpinch_right) {
        this.hand_stpinch_right = hand_stpinch_right;
    }

    public String getHand_stpinch_left() {
        return hand_stpinch_left;
    }

    public void setHand_stpinch_left(String hand_stpinch_left) {
        this.hand_stpinch_left = hand_stpinch_left;
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

 /*   public String getRomright() {
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
*/
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

  

   

  

    public String getHrom_sf_right() {
        return hrom_sf_right;
    }

    public void setHrom_sf_right(String hrom_sf_right) {
        this.hrom_sf_right = hrom_sf_right;
    }

    public String getHrom_sr_right() {
        return hrom_sr_right;
    }

    public void setHrom_sr_right(String hrom_sr_right) {
        this.hrom_sr_right = hrom_sr_right;
    }

    public String getHrom_sa_right() {
        return hrom_sa_right;
    }

    public void setHrom_sa_right(String hrom_sa_right) {
        this.hrom_sa_right = hrom_sa_right;
    }

    public String getHrom_sf_left() {
        return hrom_sf_left;
    }

    public void setHrom_sf_left(String hrom_sf_left) {
        this.hrom_sf_left = hrom_sf_left;
    }

    public String getHrom_sr_left() {
        return hrom_sr_left;
    }

    public void setHrom_sr_left(String hrom_sr_left) {
        this.hrom_sr_left = hrom_sr_left;
    }

    public String getHrom_sa_left() {
        return hrom_sa_left;
    }

    public void setHrom_sa_left(String hrom_sa_left) {
        this.hrom_sa_left = hrom_sa_left;
    }

    public String getRomflexion() {
        return romflexion;
    }

    public void setRomflexion(String romflexion) {
        this.romflexion = romflexion;
    }

    public String getRomrotation() {
        return romrotation;
    }

    public void setRomrotation(String romrotation) {
        this.romrotation = romrotation;
    }

    public String getRomabduction() {
        return romabduction;
    }

    public void setRomabduction(String romabduction) {
        this.romabduction = romabduction;
    }

    public String getRomshoulder() {
        return romshoulder;
    }

    public void setRomshoulder(String romshoulder) {
        this.romshoulder = romshoulder;
    }

    public String getRomhipr() {
        return romhipr;
    }

    public void setRomhipr(String romhipr) {
        this.romhipr = romhipr;
    }

    public String getRomhipl() {
        return romhipl;
    }

    public void setRomhipl(String romhipl) {
        this.romhipl = romhipl;
    }

    public String getRomshoulderright() {
        return romshoulderright;
    }

    public void setRomshoulderright(String romshoulderright) {
        this.romshoulderright = romshoulderright;
    }

    public String getRomshoulderleft() {
        return romshoulderleft;
    }

    public void setRomshoulderleft(String romshoulderleft) {
        this.romshoulderleft = romshoulderleft;
    }

    public String getRomkeen() {
        return romkeen;
    }

    public void setRomkeen(String romkeen) {
        this.romkeen = romkeen;
    }

    public String getRomkneeright() {
        return romkneeright;
    }

    public void setRomkneeright(String romkneeright) {
        this.romkneeright = romkneeright;
    }

    public String getRomkneeleft() {
        return romkneeleft;
    }

    public void setRomkneeleft(String romkneeleft) {
        this.romkneeleft = romkneeleft;
    }

    public String getRomknee21r() {
        return romknee21r;
    }

    public void setRomknee21r(String romknee21r) {
        this.romknee21r = romknee21r;
    }

    public String getRomknee22r() {
        return romknee22r;
    }

    public void setRomknee22r(String romknee22r) {
        this.romknee22r = romknee22r;
    }

    public String getRomkneer() {
        return romkneer;
    }

    public void setRomkneer(String romkneer) {
        this.romkneer = romkneer;
    }

    public String getRomknee21l() {
        return romknee21l;
    }

    public void setRomknee21l(String romknee21l) {
        this.romknee21l = romknee21l;
    }

    public String getRomknee22l() {
        return romknee22l;
    }

    public void setRomknee22l(String romknee22l) {
        this.romknee22l = romknee22l;
    }

    public String getRomkneel() {
        return romkneel;
    }

    public void setRomkneel(String romkneel) {
        this.romkneel = romkneel;
    }

    public String getWristright() {
        return wristright;
    }

    public void setWristright(String wristright) {
        this.wristright = wristright;
    }

    public String getWristleft() {
        return wristleft;
    }

    public void setWristleft(String wristleft) {
        this.wristleft = wristleft;
    }

    public String getWrist() {
        return wrist;
    }

    public void setWrist(String wrist) {
        this.wrist = wrist;
    }

  
   
    public String getRowankler() {
        return rowankler;
    }

    public void setRowankler(String rowankler) {
        this.rowankler = rowankler;
    }

  
    public String getRowanklel() {
        return rowanklel;
    }

    public void setRowanklel(String rowanklel) {
        this.rowanklel = rowanklel;
    }

    public String getRomkneeflexion() {
        return romkneeflexion;
    }

    public void setRomkneeflexion(String romkneeflexion) {
        this.romkneeflexion = romkneeflexion;
    }

    public String getRomkneesupination() {
        return romkneesupination;
    }

    public void setRomkneesupination(String romkneesupination) {
        this.romkneesupination = romkneesupination;
    }

    public String getWristpalmar() {
        return wristpalmar;
    }

    public void setWristpalmar(String wristpalmar) {
        this.wristpalmar = wristpalmar;
    }

    public String getWristulnar() {
        return wristulnar;
    }

    public void setWristulnar(String wristulnar) {
        this.wristulnar = wristulnar;
    }

    public String getArom_wd_right() {
        return arom_wd_right;
    }

    public void setArom_wd_right(String arom_wd_right) {
        this.arom_wd_right = arom_wd_right;
    }

    public String getArom_wr_right() {
        return arom_wr_right;
    }

    public void setArom_wr_right(String arom_wr_right) {
        this.arom_wr_right = arom_wr_right;
    }

    public String getArom_wd_left() {
        return arom_wd_left;
    }

    public void setArom_wd_left(String arom_wd_left) {
        this.arom_wd_left = arom_wd_left;
    }

    public String getArom_wr_left() {
        return arom_wr_left;
    }

    public void setArom_wr_left(String arom_wr_left) {
        this.arom_wr_left = arom_wr_left;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
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

    public String getComplication() {
        return complication;
    }

    public void setComplication(String complication) {
        this.complication = complication;
    }

    public String getShortining() {
        return shortining;
    }

    public void setShortining(String shortining) {
        this.shortining = shortining;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public StringBuffer getTotalArmRight() {
        return totalArmRight;
    }

    public void setTotalArmRight(StringBuffer totalArmRight) {
        this.totalArmRight = totalArmRight;
    }

    public StringBuffer getTotalArmLeft() {
        return totalArmLeft;
    }

    public void setTotalArmLeft(StringBuffer totalArmLeft) {
        this.totalArmLeft = totalArmLeft;
    }

    public StringBuffer getTotalArm() {
        return totalArm;
    }

    public void setTotalArm(StringBuffer totalArm) {
        this.totalArm = totalArm;
    }

    public String getArmRight() {
        return ArmRight;
    }

    public void setArmRight(String ArmRight) {
        this.ArmRight = ArmRight;
    }

    public String getArmLeft() {
        return ArmLeft;
    }

    public void setArmLeft(String ArmLeft) {
        this.ArmLeft = ArmLeft;
    }

    public String getRightLeftArm() {
        return rightLeftArm;
    }

    public void setRightLeftArm(String rightLeftArm) {
        this.rightLeftArm = rightLeftArm;
    }

    public String getArm() {
        return arm;
    }

    public void setArm(String arm) {
        this.arm = arm;
    }

    public StringBuffer getArmRightLeft() {
        return armRightLeft;
    }

    public void setArmRightLeft(StringBuffer armRightLeft) {
        this.armRightLeft = armRightLeft;
    }

    public String getExtraCoomplication() {
        return extraCoomplication;
    }

    public void setExtraCoomplication(String extraCoomplication) {
        this.extraCoomplication = extraCoomplication;
    }

    public StringBuffer getUpper() {
        return upper;
    }

    public void setUpper(StringBuffer upper) {
        this.upper = upper;
    }

    public StringBuffer getUpperExtremity() {
        return upperExtremity;
    }

    public void setUpperExtremity(StringBuffer upperExtremity) {
        this.upperExtremity = upperExtremity;
    }

    public String getUpperExtremityRound() {
        return upperExtremityRound;
    }

    public void setUpperExtremityRound(String upperExtremityRound) {
        this.upperExtremityRound = upperExtremityRound;
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
