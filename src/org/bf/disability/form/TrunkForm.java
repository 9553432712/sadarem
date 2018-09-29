/*
 * TrunkForm.java
 *
 * Created on July 9, 2008, 12:36 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


/**
 * This class contains the fields, required to compute Trunk
 * @version 1.0
 */
public class TrunkForm extends ActionForm{
      private String systemip=null;
    /** Creates a new instance of TrunkForm */
    public TrunkForm() {
    }
    private String compression=null;
    private String posterior_fusion=null;
    private String posterior_persistent=null;
    private String severe_fire=null;
    private String severe_inadequate=null;
    private String cervical_disc=null;
    private String cervical_pain=null;
    private String thoracic_less=null;
    private String thoracic_more=null;
    private String thoracic_fusion=null;
    private String thoracic_radiologically=null;
    private String fracture_less=null;
    private String fracture_more=null;
    private String fracture_radiologically=null;
    private String disc_persistent=null;
    private String disc_pain=null;
    private String disc_diseases=null;
    private String disc_stifness=null;
    private String scoliosis_measure=null;
    private String scoliosis_torso=null;
    private String kyphosis_measure=null;
    private String kyphosis_torso=null;
    private String head=null;
    private String cardio_chest=null;
    private String cardio_counting=null;
    private String cardio_associatepain=null;
    private String cardio_associatecosmetic=null;
    private String cardio_associateleg=null;
    private boolean  scoliosis;
    public boolean kyphosis;
      public double trunk;
      public int traumatic;
       public int totalscoliosis;
      public int totalkyphosis;
      public double nontraumatic;
      public String loginid;
      
      
      
      
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.setCardio_associatecosmetic("0");      
        this.setCardio_associatepain("0");
        this.setCardio_chest("0");
        this.setCardio_counting("0");
        this.setCervical_disc("0");
        this.setCervical_pain("0");
        this.setCompression("0");
        this.setDisc_diseases("0");
        this.setDisc_pain("0");
        this.setDisc_persistent("0");
        this.setDisc_stifness("0");
        this.setFracture_less("0");
        this.setFracture_more("0");
        this.setFracture_radiologically("0");
        this.setHead("0");
        this.setKyphosis_measure("0");
        this.setKyphosis_torso("0");
      
        this.setPosterior_fusion("0");
        this.setPosterior_persistent("0");
     
        this.setScoliosis_measure("0");
        this.setScoliosis_torso("0");
        this.setSevere_fire("0");
        this.setSevere_inadequate("0");
        this.setThoracic_fusion("0");
        this.setThoracic_less("0");
        this.setThoracic_more("0");
        this.setThoracic_radiologically("0");
        
        this.setNontraumatic(0);
        this.setTraumatic(0);
        this.setTrunk(0);
        
        
    }
    public String getCompression() {
        return compression;
    }
    
    public void setCompression(String compression) {
        this.compression = compression;
    }
    
    public String getPosterior_fusion() {
        return posterior_fusion;
    }
    
    public void setPosterior_fusion(String posterior_fusion) {
        this.posterior_fusion = posterior_fusion;
    }
    
    public String getPosterior_persistent() {
        return posterior_persistent;
    }
    
    public void setPosterior_persistent(String posterior_persistent) {
        this.posterior_persistent = posterior_persistent;
    }
    
    public String getSevere_fire() {
        return severe_fire;
    }
    
    public void setSevere_fire(String severe_fire) {
        this.severe_fire = severe_fire;
    }
    
    public String getSevere_inadequate() {
        return severe_inadequate;
    }
    
    public void setSevere_inadequate(String severe_inadequate) {
        this.severe_inadequate = severe_inadequate;
    }
    
    public String getCervical_disc() {
        return cervical_disc;
    }
    
    public void setCervical_disc(String cervical_disc) {
        this.cervical_disc = cervical_disc;
    }
    
    public String getCervical_pain() {
        return cervical_pain;
    }
    
    public void setCervical_pain(String cervical_pain) {
        this.cervical_pain = cervical_pain;
    }
    
    public String getThoracic_less() {
        return thoracic_less;
    }
    
    public void setThoracic_less(String thoracic_less) {
        this.thoracic_less = thoracic_less;
    }
    
    public String getThoracic_more() {
        return thoracic_more;
    }
    
    public void setThoracic_more(String thoracic_more) {
        this.thoracic_more = thoracic_more;
    }
    
    public String getThoracic_fusion() {
        return thoracic_fusion;
    }
    
    public void setThoracic_fusion(String thoracic_fusion) {
        this.thoracic_fusion = thoracic_fusion;
    }
    
    public String getThoracic_radiologically() {
        return thoracic_radiologically;
    }
    
    public void setThoracic_radiologically(String thoracic_radiologically) {
        this.thoracic_radiologically = thoracic_radiologically;
    }
    
    public String getFracture_less() {
        return fracture_less;
    }
    
    public void setFracture_less(String fracture_less) {
        this.fracture_less = fracture_less;
    }
    
    public String getFracture_more() {
        return fracture_more;
    }
    
    public void setFracture_more(String fracture_more) {
        this.fracture_more = fracture_more;
    }
    
    public String getFracture_radiologically() {
        return fracture_radiologically;
    }
    
    public void setFracture_radiologically(String fracture_radiologically) {
        this.fracture_radiologically = fracture_radiologically;
    }
    
    public String getDisc_persistent() {
        return disc_persistent;
    }
    
    public void setDisc_persistent(String disc_persistent) {
        this.disc_persistent = disc_persistent;
    }
    
    public String getDisc_pain() {
        return disc_pain;
    }
    
    public void setDisc_pain(String disc_pain) {
        this.disc_pain = disc_pain;
    }
    
    public String getDisc_diseases() {
        return disc_diseases;
    }
    
    public void setDisc_diseases(String disc_diseases) {
        this.disc_diseases = disc_diseases;
    }
    
    public String getDisc_stifness() {
        return disc_stifness;
    }
    
    public void setDisc_stifness(String disc_stifness) {
        this.disc_stifness = disc_stifness;
    }
    
    public String getScoliosis_measure() {
        return scoliosis_measure;
    }
    
    public void setScoliosis_measure(String scoliosis_measure) {
        this.scoliosis_measure = scoliosis_measure;
    }
    
    public String getScoliosis_torso() {
        return scoliosis_torso;
    }
    
    public void setScoliosis_torso(String scoliosis_torso) {
        this.scoliosis_torso = scoliosis_torso;
    }
    
    public String getKyphosis_measure() {
        return kyphosis_measure;
    }
    
    public void setKyphosis_measure(String kyphosis_measure) {
        this.kyphosis_measure = kyphosis_measure;
    }
    
    public String getKyphosis_torso() {
        return kyphosis_torso;
    }
    
    public void setKyphosis_torso(String kyphosis_torso) {
        this.kyphosis_torso = kyphosis_torso;
    }
    
    public String getHead() {
        return head;
    }
    
    public void setHead(String head) {
        this.head = head;
    }
    
    public String getCardio_chest() {
        return cardio_chest;
    }
    
    public void setCardio_chest(String cardio_chest) {
        this.cardio_chest = cardio_chest;
    }
    
    public String getCardio_counting() {
        return cardio_counting;
    }
    
    public void setCardio_counting(String cardio_counting) {
        this.cardio_counting = cardio_counting;
    }
    
    public String getCardio_associatepain() {
        return cardio_associatepain;
    }
    
    public void setCardio_associatepain(String cardio_associatepain) {
        this.cardio_associatepain = cardio_associatepain;
    }
    
    public String getCardio_associatecosmetic() {
        return cardio_associatecosmetic;
    }
    
    public void setCardio_associatecosmetic(String cardio_associatecosmetic) {
        this.cardio_associatecosmetic = cardio_associatecosmetic;
    }
    
    public String getCardio_associateleg() {
        return cardio_associateleg;
    }
    
    public void setCardio_associateleg(String cardio_associateleg) {
        this.cardio_associateleg = cardio_associateleg;
    }
    
 

    public String getSystemip() {
        return systemip;
    }

    public void setSystemip(String systemip) {
        this.systemip = systemip;
    }

   

    public boolean isScoliosis() {
        return scoliosis;
    }

    public void setScoliosis(boolean scoliosis) {
        this.scoliosis = scoliosis;
    }

    public double getTrunk() {
        return trunk;
    }

    public void setTrunk(double trunk) {
        this.trunk = trunk;
    }

    public int getTraumatic() {
        return traumatic;
    }

    public void setTraumatic(int traumatic) {
        this.traumatic = traumatic;
    }

    public int getTotalscoliosis() {
        return totalscoliosis;
    }

    public void setTotalscoliosis(int totalscoliosis) {
        this.totalscoliosis = totalscoliosis;
    }

    public int getTotalkyphosis() {
        return totalkyphosis;
    }

    public void setTotalkyphosis(int totalkyphosis) {
        this.totalkyphosis = totalkyphosis;
    }

    public double getNontraumatic() {
        return nontraumatic;
    }

    public void setNontraumatic(double nontraumatic) {
        this.nontraumatic = nontraumatic;
    }

    public boolean isKyphosis() {
        return kyphosis;
    }

    public void setKyphosis(boolean kyphosis) {
        this.kyphosis = kyphosis;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    
    
    
}
