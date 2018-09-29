/*
 * LowerExtremityForm.java
 */

package org.bf.disability.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;


/**
 * This class contains the fields, required to compute Lower Extremity disability type
 * @author Bapi naidu
 * @version 1.0
 */
public class LowerExtremityForm extends ValidatorForm{
    
    /**
     * Creates a new instance of LowerExtremityForm
     */
    public LowerExtremityForm() {
    }
    public String romhipflexionextensionright;
        public String romhiprotationright;
        public String romhipabductionadductionright; 
        
        public String romhipflexionextensionleft;
        public String romhiprotationleft;
        public String romhipabductionadductionleft;
        public String romkneeflexionextensionright;
        public String romkneeflexionextensionleft;
        public String romankledorsiflexionpalmarflexionright;
        public String romankledorsiflexionpalmarflexionleft;
        public String romankleinversioneversionright;
        public String romankleinversioneversionleft;        
        public String mshipflexormusclesright; 
        public String mshipextensormusclesright; 
        public String mshiprotatormusclesright; 
        public String mshipabductormusclesright; 
        public String mshipadductormusclesright; 
        public String mshipflexormusclesleft; 
        public String mshipextensormusclesleft; 
        public String mshiprotatormusclesleft; 
        public String mshipabductormusclesleft; 
        public String mshipadductormusclesleft; 
        public String mskneeflexormusclesright; 
        public String mskneeextensormusclesright; 
        public String mskneeflexormusclesleft; 
        public String mskneeextensormusclesleft; 
        public String msankleplanterflexormusclesright; 
        public String msankledorsiflexormusclesright; 
        public String msankleinvertormusclesright; 
        public String msankleevertormusclesright; 
        public String msankleplanterflexormusclesleft; 
        public String msankledorsiflexormusclesleft; 
        public String msankleinvertormusclesleft; 
        public String msankleevertormusclesleft; 
        public String shortening;
        public double romright;
         public double romleft;
          public double msright;
         public double msleft;
          public double shortininginches;
          public double extra;
          public double stability;
       public String systemip; 
       public String defor;
       public String lof;
       public String painpc;
       public String com;
      public double lowerextremity;
      public String loginid;
       
       public int wop;
       public int wos;
       public int wost;
       public int std;
       public int soe;
            public int sqa;
             public int sit;
              public int tak;
               public int kee;
         public String personcode;       
               
       
        
        
        
        public String deformity=null;
       public String pain=null;
        public String loss_of_Function=null;
        public String complications=null;
        public String working_on_plane=null;
        public String working_on_slope=null;
        public String working_on_stairs=null;
        public String standing_on_effected=null;
        public String standing_on_both_legs=null;
        public String squatting_on_floor=null;
        public String sitting_cross_leg=null;
        public String kneeling=null;
        public String taking_turns=null;
  
    public void reset(ActionMapping mapping, HttpServletRequest request) {

        
        this.setDeformity("0");
        this.setPain("0");
        this.setLoss_of_Function("0");
        this.setComplications("0");
        this.setWorking_on_plane("0");
        this.setWorking_on_slope("0");
        this.setWorking_on_stairs("0");
        this.setStanding_on_effected("0");
        this.setStanding_on_both_legs("0");
        this.setSquatting_on_floor("0");
        this.setSitting_cross_leg("0");
        this.setKneeling("0");
        this.setTaking_turns("0");
       this.setLowerextremity(0);
        
        
    }
    
   
    

 

    public String getDeformity() {
        return deformity;
    }

    public void setDeformity(String Deformity) {
        this.deformity = Deformity;
    }

   
    public String getWorking_on_plane() {
        return working_on_plane;
    }

    public void setWorking_on_plane(String working_on_plane) {
        this.working_on_plane = working_on_plane;
    }

    public String getWorking_on_slope() {
        return working_on_slope;
    }

    public void setWorking_on_slope(String working_on_slope) {
        this.working_on_slope = working_on_slope;
    }

    public String getWorking_on_stairs() {
        return working_on_stairs;
    }

    public void setWorking_on_stairs(String working_on_stairs) {
        this.working_on_stairs = working_on_stairs;
    }

   

   
    public String getComplications() {
        return complications;
    }

    public void setComplications(String complications) {
        this.complications = complications;
    }

    
    public String getStanding_on_effected() {
        return standing_on_effected;
    }

    public void setStanding_on_effected(String standing_on_effected) {
        this.standing_on_effected = standing_on_effected;
    }

    public String getKneeling() {
        return kneeling;
    }

    public void setKneeling(String kneeling) {
        this.kneeling = kneeling;
    }

    

   
    

   

    public String getStanding_on_both_legs() {
        return standing_on_both_legs;
    }

    public void setStanding_on_both_legs(String standing_on_both_legs) {
        this.standing_on_both_legs = standing_on_both_legs;
    }

    public String getSquatting_on_floor() {
        return squatting_on_floor;
    }

    public void setSquatting_on_floor(String squatting_on_floor) {
        this.squatting_on_floor = squatting_on_floor;
    }

    public String getSitting_cross_leg() {
        return sitting_cross_leg;
    }

    public void setSitting_cross_leg(String sitting_cross_leg) {
        this.sitting_cross_leg = sitting_cross_leg;
    }

    public String getTaking_turns() {
        return taking_turns;
    }

    public void setTaking_turns(String taking_turns) {
        this.taking_turns = taking_turns;
    }

    public String getLoss_of_Function() {
        return loss_of_Function;
    }

    public void setLoss_of_Function(String loss_of_Function) {
        this.loss_of_Function = loss_of_Function;
    }

    public String getRomhipflexionextensionright() {
        return romhipflexionextensionright;
    }

    public void setRomhipflexionextensionright(String romhipflexionextensionright) {
        this.romhipflexionextensionright = romhipflexionextensionright;
    }

    public String getRomhiprotationright() {
        return romhiprotationright;
    }

    public void setRomhiprotationright(String romhiprotationright) {
        this.romhiprotationright = romhiprotationright;
    }

    public String getRomhipabductionadductionright() {
        return romhipabductionadductionright;
    }

    public void setRomhipabductionadductionright(String romhipabductionadductionright) {
        this.romhipabductionadductionright = romhipabductionadductionright;
    }

    public String getRomhipflexionextensionleft() {
        return romhipflexionextensionleft;
    }

    public void setRomhipflexionextensionleft(String romhipflexionextensionleft) {
        this.romhipflexionextensionleft = romhipflexionextensionleft;
    }

    public String getRomhiprotationleft() {
        return romhiprotationleft;
    }

    public void setRomhiprotationleft(String romhiprotationleft) {
        this.romhiprotationleft = romhiprotationleft;
    }

    public String getRomhipabductionadductionleft() {
        return romhipabductionadductionleft;
    }

    public void setRomhipabductionadductionleft(String romhipabductionadductionleft) {
        this.romhipabductionadductionleft = romhipabductionadductionleft;
    }

    public String getRomkneeflexionextensionright() {
        return romkneeflexionextensionright;
    }

    public void setRomkneeflexionextensionright(String romkneeflexionextensionright) {
        this.romkneeflexionextensionright = romkneeflexionextensionright;
    }

    public String getRomkneeflexionextensionleft() {
        return romkneeflexionextensionleft;
    }

    public void setRomkneeflexionextensionleft(String romkneeflexionextensionleft) {
        this.romkneeflexionextensionleft = romkneeflexionextensionleft;
    }

    public String getRomankledorsiflexionpalmarflexionright() {
        return romankledorsiflexionpalmarflexionright;
    }

    public void setRomankledorsiflexionpalmarflexionright(String romankledorsiflexionpalmarflexionright) {
        this.romankledorsiflexionpalmarflexionright = romankledorsiflexionpalmarflexionright;
    }

    public String getRomankledorsiflexionpalmarflexionleft() {
        return romankledorsiflexionpalmarflexionleft;
    }

    public void setRomankledorsiflexionpalmarflexionleft(String romankledorsiflexionpalmarflexionleft) {
        this.romankledorsiflexionpalmarflexionleft = romankledorsiflexionpalmarflexionleft;
    }

    public String getRomankleinversioneversionright() {
        return romankleinversioneversionright;
    }

    public void setRomankleinversioneversionright(String romankleinversioneversionright) {
        this.romankleinversioneversionright = romankleinversioneversionright;
    }

    public String getRomankleinversioneversionleft() {
        return romankleinversioneversionleft;
    }

    public void setRomankleinversioneversionleft(String romankleinversioneversionleft) {
        this.romankleinversioneversionleft = romankleinversioneversionleft;
    }

    public String getMshipflexormusclesright() {
        return mshipflexormusclesright;
    }

    public void setMshipflexormusclesright(String mshipflexormusclesright) {
        this.mshipflexormusclesright = mshipflexormusclesright;
    }

    public String getMshipextensormusclesright() {
        return mshipextensormusclesright;
    }

    public void setMshipextensormusclesright(String mshipextensormusclesright) {
        this.mshipextensormusclesright = mshipextensormusclesright;
    }

    public String getMshiprotatormusclesright() {
        return mshiprotatormusclesright;
    }

    public void setMshiprotatormusclesright(String mshiprotatormusclesright) {
        this.mshiprotatormusclesright = mshiprotatormusclesright;
    }

    public String getMshipabductormusclesright() {
        return mshipabductormusclesright;
    }

    public void setMshipabductormusclesright(String mshipabductormusclesright) {
        this.mshipabductormusclesright = mshipabductormusclesright;
    }

    public String getMshipadductormusclesright() {
        return mshipadductormusclesright;
    }

    public void setMshipadductormusclesright(String mshipadductormusclesright) {
        this.mshipadductormusclesright = mshipadductormusclesright;
    }

    public String getMshipflexormusclesleft() {
        return mshipflexormusclesleft;
    }

    public void setMshipflexormusclesleft(String mshipflexormusclesleft) {
        this.mshipflexormusclesleft = mshipflexormusclesleft;
    }

    public String getMshipextensormusclesleft() {
        return mshipextensormusclesleft;
    }

    public void setMshipextensormusclesleft(String mshipextensormusclesleft) {
        this.mshipextensormusclesleft = mshipextensormusclesleft;
    }

    public String getMshiprotatormusclesleft() {
        return mshiprotatormusclesleft;
    }

    public void setMshiprotatormusclesleft(String mshiprotatormusclesleft) {
        this.mshiprotatormusclesleft = mshiprotatormusclesleft;
    }

    public String getMshipabductormusclesleft() {
        return mshipabductormusclesleft;
    }

    public void setMshipabductormusclesleft(String mshipabductormusclesleft) {
        this.mshipabductormusclesleft = mshipabductormusclesleft;
    }

    public String getMshipadductormusclesleft() {
        return mshipadductormusclesleft;
    }

    public void setMshipadductormusclesleft(String mshipadductormusclesleft) {
        this.mshipadductormusclesleft = mshipadductormusclesleft;
    }

    public String getMskneeflexormusclesright() {
        return mskneeflexormusclesright;
    }

    public void setMskneeflexormusclesright(String mskneeflexormusclesright) {
        this.mskneeflexormusclesright = mskneeflexormusclesright;
    }

    public String getMskneeextensormusclesright() {
        return mskneeextensormusclesright;
    }

    public void setMskneeextensormusclesright(String mskneeextensormusclesright) {
        this.mskneeextensormusclesright = mskneeextensormusclesright;
    }

    public String getMskneeflexormusclesleft() {
        return mskneeflexormusclesleft;
    }

    public void setMskneeflexormusclesleft(String mskneeflexormusclesleft) {
        this.mskneeflexormusclesleft = mskneeflexormusclesleft;
    }

    public String getMskneeextensormusclesleft() {
        return mskneeextensormusclesleft;
    }

    public void setMskneeextensormusclesleft(String mskneeextensormusclesleft) {
        this.mskneeextensormusclesleft = mskneeextensormusclesleft;
    }

    public String getMsankleplanterflexormusclesright() {
        return msankleplanterflexormusclesright;
    }

    public void setMsankleplanterflexormusclesright(String msankleplanterflexormusclesright) {
        this.msankleplanterflexormusclesright = msankleplanterflexormusclesright;
    }

    public String getMsankledorsiflexormusclesright() {
        return msankledorsiflexormusclesright;
    }

    public void setMsankledorsiflexormusclesright(String msankledorsiflexormusclesright) {
        this.msankledorsiflexormusclesright = msankledorsiflexormusclesright;
    }

    public String getMsankleinvertormusclesright() {
        return msankleinvertormusclesright;
    }

    public void setMsankleinvertormusclesright(String msankleinvertormusclesright) {
        this.msankleinvertormusclesright = msankleinvertormusclesright;
    }

    public String getMsankleevertormusclesright() {
        return msankleevertormusclesright;
    }

    public void setMsankleevertormusclesright(String msankleevertormusclesright) {
        this.msankleevertormusclesright = msankleevertormusclesright;
    }

    public String getMsankleplanterflexormusclesleft() {
        return msankleplanterflexormusclesleft;
    }

    public void setMsankleplanterflexormusclesleft(String msankleplanterflexormusclesleft) {
        this.msankleplanterflexormusclesleft = msankleplanterflexormusclesleft;
    }

    public String getMsankledorsiflexormusclesleft() {
        return msankledorsiflexormusclesleft;
    }

    public void setMsankledorsiflexormusclesleft(String msankledorsiflexormusclesleft) {
        this.msankledorsiflexormusclesleft = msankledorsiflexormusclesleft;
    }

    public String getMsankleinvertormusclesleft() {
        return msankleinvertormusclesleft;
    }

    public void setMsankleinvertormusclesleft(String msankleinvertormusclesleft) {
        this.msankleinvertormusclesleft = msankleinvertormusclesleft;
    }

    public String getMsankleevertormusclesleft() {
        return msankleevertormusclesleft;
    }

    public void setMsankleevertormusclesleft(String msankleevertormusclesleft) {
        this.msankleevertormusclesleft = msankleevertormusclesleft;
    }

    public String getShortening() {
        return shortening;
    }

    public void setShortening(String shortening) {
        this.shortening = shortening;
    }

    public double getRomright() {
        return romright;
    }

    public void setRomright(double romright) {
        this.romright = romright;
    }

    public double getRomleft() {
        return romleft;
    }

    public void setRomleft(double romleft) {
        this.romleft = romleft;
    }

    public String getSystemip() {
        return systemip;
    }

    public void setSystemip(String systemip) {
        this.systemip = systemip;
    }

    public String getPain() {
        return pain;
    }

    public void setPain(String pain) {
        this.pain = pain;
    }

    public String getDefor() {
        return defor;
    }

    public void setDefor(String defor) {
        this.defor = defor;
    }

    public String getLof() {
        return lof;
    }

    public void setLof(String lof) {
        this.lof = lof;
    }

    public String getPainpc() {
        return painpc;
    }

    public void setPainpc(String painpc) {
        this.painpc = painpc;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public int getWop() {
        return wop;
    }

    public void setWop(int wop) {
        this.wop = wop;
    }

    public int getWos() {
        return wos;
    }

    public void setWos(int wos) {
        this.wos = wos;
    }

    public int getWost() {
        return wost;
    }

    public void setWost(int wost) {
        this.wost = wost;
    }

    public int getStd() {
        return std;
    }

    public void setStd(int std) {
        this.std = std;
    }

    public int getSoe() {
        return soe;
    }

    public void setSoe(int soe) {
        this.soe = soe;
    }

    public int getSqa() {
        return sqa;
    }

    public void setSqa(int sqa) {
        this.sqa = sqa;
    }

    public int getSit() {
        return sit;
    }

    public void setSit(int sit) {
        this.sit = sit;
    }

    public int getTak() {
        return tak;
    }

    public void setTak(int tak) {
        this.tak = tak;
    }

    public int getKee() {
        return kee;
    }

    public void setKee(int kee) {
        this.kee = kee;
    }

    public double getMsright() {
        return msright;
    }

    public void setMsright(double msright) {
        this.msright = msright;
    }

    public double getMsleft() {
        return msleft;
    }

    public void setMsleft(double msleft) {
        this.msleft = msleft;
    }

   

    public double getExtra() {
        return extra;
    }

    public void setExtra(double extra) {
        this.extra = extra;
    }

    public double getStability() {
        return stability;
    }

    public void setStability(double stability) {
        this.stability = stability;
    }

    public double getShortininginches() {
        return shortininginches;
    }

    public void setShortininginches(double shortininginches) {
        this.shortininginches = shortininginches;
    }

    public double getLowerextremity() {
        return lowerextremity;
    }

    public void setLowerextremity(double lowerextremity) {
        this.lowerextremity = lowerextremity;
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
