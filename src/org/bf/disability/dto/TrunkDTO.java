/*
 * TrunkDTO.java
 *
 * Created on July 9, 2008, 12:37 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.dto;

import java.io.Serializable;

/**
 * This class contains the fields, required to compute Trunk
 * @version 1.0
 */
public class TrunkDTO implements Serializable {
      private String systemip=null;
    private String compression ;
    private String posterior_fusion;
    private String posterior_persistent;
    private String severe_fire;
    private String severe_inadequate;
    private String cervical_disc;
    private String cervical_pain;
    private String thoracic_less;
    private String thoracic_more;
    private String thoracic_fusion;
    private String thoracic_radiologically;
    private String fracture_less;
    private String fracture_more;
    private String fracture_radiologically;
    private String disc_persistent;
    private String disc_pain;
    private String disc_diseases;
    private String disc_stifness;
    private String scoliosis_measure;
    private String scoliosis_torso;
    private String kyphosis_measure;
    private String kyphosis_torso;
    private String head;
    private String cardio_chest;
    private String cardio_counting;
    private String cardio_associatepain;
    private String cardio_associatecosmetic;
    private String cardio_associateleg; 
private boolean scoliosis;
private boolean kyphosis;
        public double trunk;
        public int traumatic;
        public int totalscoliosis;
        public int totalkyphosis;
        public double nontraumatic;
         public String personcode;
         public String loginid;
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

    public String getCervical_pain() {
        return cervical_pain;
    }

    public void setCervical_pain(String cervical_pain) {
        this.cervical_pain = cervical_pain;
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

    public boolean isKyphosis() {
        return kyphosis;
    }

    public void setKyphosis(boolean kyphosis) {
        this.kyphosis = kyphosis;
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
   
}
