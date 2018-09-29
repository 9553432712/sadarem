/*
 * TrunkBean.java
 *
 * Created on January 2, 2009, 2:46 PM
 *
 */

package org.bf.disability.bean;

/**
 *
 * @author Deviprasad_t
 */
public class TrunkBean {
    
    /** Creates a new instance of TrunkBean */
    public TrunkBean() {
    }
    
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
    private double trunk;
    private int traumatic;
    private int totalscoliosis;
    private int totalkyphosis;
    private double nontraumatic;
    private String personcode;
    private String loginid;
    private double extra; 
    private double lesser;
    private double greater;
     private double scoliosisextraformat;
       private double kyphosisextraformat;
         private double nontraumaticformat;
            private double traumaticformat;
     private double scoliosisextra;
       private double  kyphosisextra;
    private double cardio_leg;
    private StringBuffer scoliosis_extra;
    private StringBuffer kyphosis_extra;
    private StringBuffer non_traumatic;
    private StringBuffer totaltrunk;
     private boolean traumaticflag1;
    
      private String motortraumatic;
    /* these properties are used for display purpose */
     private boolean severe;
     private boolean posterior;
     private boolean cervical;
     private boolean thoracic;
      private boolean fracture;
    private boolean disc;
     private boolean traumaticflag;
     private boolean cervicalspine ;
     
     
     
     
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

    public double getExtra() {
        return extra;
    }

    public void setExtra(double extra) {
        this.extra = extra;
    }

   
    public double getLesser() {
        return lesser;
    }

    public void setLesser(double lesser) {
        this.lesser = lesser;
    }

    public double getGreater() {
        return greater;
    }

    public void setGreater(double greater) {
        this.greater = greater;
    }

    public boolean isPosterior() {
        if(this.getPosterior_fusion().equals("10")|| this.getPosterior_persistent().equals("25"))
        {
            this.posterior=true;
        }
            
        return posterior;
    }

    public void setPosterior(boolean posterior) {
        this.posterior = posterior;
    }

    public boolean isSevere() {
        
         if(this.getSevere_fire().equals("10")|| this.getSevere_inadequate().equals("15"))
        {
            this.severe=true;
        }
                  
        return severe;
    }

    public void setSevere(boolean severe) {
        this.severe = severe;
    }

    public boolean isCervical() {
        if(this.getCompression().equals("20")|| this.getCervical_pain().equals("15"))
        {
            this.cervical=true;
        }
                  
        return cervical;
    }

    public void setCervical(boolean cervical) {
        this.cervical = cervical;
    }

    public boolean isThoracic() {
       if(this.getThoracic_less().equals("10")||isPosterior()||this.getThoracic_fusion().equals("20")||this.getThoracic_radiologically().equals("30"))
        {
            this.thoracic=true;
        } 
        
        return thoracic;
    }

    public void setThoracic(boolean thoracic) {
        this.thoracic = thoracic;
    }

    public boolean isFracture() {
        if(this.getFracture_less().equals("35")|| this.getFracture_more().equals("30")||this.getFracture_radiologically().equals("35"))
        {
            this.fracture=true;
        } 
        return fracture;
    }

    public void setFracture(boolean  fracture) {
        this.fracture = fracture;
    }

    public boolean isDisc() {
          if(this.getDisc_persistent().equals("15")|| this.getDisc_pain().equals("20")||this.getDisc_diseases().equals("25")||this.getDisc_stifness().equals("30"))
        {
            this.disc=true;
        } 
        return disc;
    }

    public void setDisc(boolean disc) {
        this.disc = disc;
    }

    public StringBuffer getScoliosis_extra() {
        return scoliosis_extra;
    }

    public void setScoliosis_extra(StringBuffer scoliosis_extra) {
        this.scoliosis_extra = scoliosis_extra;
    }

    public StringBuffer getKyphosis_extra() {
        return kyphosis_extra;
    }

    public void setKyphosis_extra(StringBuffer kyphosis_extra) {
        this.kyphosis_extra = kyphosis_extra;
    }

    public StringBuffer getNon_traumatic() {
        return non_traumatic;
    }

    public void setNon_traumatic(StringBuffer non_traumatic) {
        this.non_traumatic = non_traumatic;
    }

    public StringBuffer getTotaltrunk() {
        return totaltrunk;
    }

    public void setTotaltrunk(StringBuffer totaltrunk) {
        this.totaltrunk = totaltrunk;
    }

    public boolean isTraumaticflag() {
        return traumaticflag;
    }

    public void setTraumaticflag(boolean traumaticflag) {
        this.traumaticflag = traumaticflag;
    }

    public double getCardio_leg() {
        return cardio_leg;
    }

    public void setCardio_leg(double cardio_leg) {
        this.cardio_leg = cardio_leg;
    }

    public double getKyphosisextra() {
        return kyphosisextra;
    }

    public void setKyphosisextra(double kyphosisextra) {
        this.kyphosisextra = kyphosisextra;
    }

    public double getScoliosisextra() {
        return scoliosisextra;
    }

    public void setScoliosisextra(double scoliosisextra) {
        this.scoliosisextra = scoliosisextra;
    }

    public double getScoliosisextraformat() {
        return scoliosisextraformat;
    }

    public void setScoliosisextraformat(double scoliosisextraformat) {
        this.scoliosisextraformat = scoliosisextraformat;
    }

    public double getKyphosisextraformat() {
        return kyphosisextraformat;
    }

    public void setKyphosisextraformat(double kyphosisextraformat) {
        this.kyphosisextraformat = kyphosisextraformat;
    }

    public double getNontraumaticformat() {
        return nontraumaticformat;
    }

    public void setNontraumaticformat(double nontraumaticformat) {
        this.nontraumaticformat = nontraumaticformat;
    }

    public double getTraumaticformat() {
        return traumaticformat;
    }

    public void setTraumaticformat(double traumaticformat) {
        this.traumaticformat = traumaticformat;
    }

    public boolean isCervicalspine() {
          if(this.compression.equals("20")|| this.isPosterior() ||this. isSevere())
        {
            this.cervicalspine=true;
        } 
        
        return cervicalspine;
    }

    public void setCervicalspine(boolean cervicalspine) {
        this.cervicalspine = cervicalspine;
    }

  
    public String getMotortraumatic() {
        return motortraumatic;
    }

    public void setMotortraumatic(String motortraumatic) {
        this.motortraumatic = motortraumatic;
    }

    public boolean isTraumaticflag1() {
        return traumaticflag1;
    }

    public void setTraumaticflag1(boolean traumaticflag1) {
        this.traumaticflag1 = traumaticflag1;
    }

  
    
}

