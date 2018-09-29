/*
 * LowerExtremityCalculation.java
 *
 * Created on July 7, 2008, 2:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.util;

import javax.servlet.http.HttpServletRequest;
import org.bf.disability.form.LowerExtremityForm;

/**
 *
 * @author Bapinaidu.t
 */
public class LowerExtremityCalculation {
    
    /**
     * Creates a new instance of LowerExtremityCalculation
     */
    public LowerExtremityCalculation() {
    }
    public LowerExtremityForm calculation(LowerExtremityForm lef,HttpServletRequest request) {
        
        // Local Variables
        double extradata = 0;
        double shortininginches = 0;
        double mobilitycomponentright = 0;
        double mobilitycomponentleft = 0;
        double mobilitycomponent = 0;
        double lowerwithoutextra = 0;
        double lowerextremity = 0;
        double lowerextremitytotal = 0;

        if("".equals(lef.getRomhipflexionextensionright())){lef.setRomhipflexionextensionright("140");}
        if("".equals(lef.getRomhiprotationright())){lef.setRomhiprotationright("90");}
        if("".equals(lef.getRomhipabductionadductionright())){lef.setRomhipabductionadductionright("90");}
        if("".equals(lef.getRomhipflexionextensionleft())){lef.setRomhipflexionextensionleft("140");}
        if("".equals(lef.getRomhiprotationleft())){lef.setRomhiprotationleft("90");}
	if("".equals(lef.getRomhipabductionadductionleft())){lef.setRomhipabductionadductionleft("90");}
        if("".equals(lef.getRomkneeflexionextensionright())){lef.setRomkneeflexionextensionright("125");}
        if("".equals(lef.getRomkneeflexionextensionleft())){lef.setRomkneeflexionextensionleft("125");}
        if("".equals(lef.getRomankledorsiflexionpalmarflexionright())){lef.setRomankledorsiflexionpalmarflexionright("70");}
        if("".equals(lef.getRomankledorsiflexionpalmarflexionleft())){lef.setRomankledorsiflexionpalmarflexionleft("70");}
        if("".equals(lef.getRomankleinversioneversionright())){lef.setRomankleinversioneversionright("60");}
        if("".equals(lef.getRomankleinversioneversionleft())){lef.setRomankleinversioneversionleft("60");}
        if("".equals(lef.getMshipflexormusclesright())){lef.setMshipflexormusclesright("5");}
        if("".equals(lef.getMshipextensormusclesright())){lef.setMshipextensormusclesright("5");}
        if("".equals(lef.getMshiprotatormusclesright())){lef.setMshiprotatormusclesright("5");}
        if("".equals(lef.getMshipabductormusclesright())){lef.setMshipabductormusclesright("5");}
        if("".equals(lef.getMshipadductormusclesright())){lef.setMshipadductormusclesright("5");}
        if("".equals(lef.getMshipflexormusclesleft())){lef.setMshipflexormusclesleft("5");}
        if("".equals(lef.getMshipextensormusclesleft())){lef.setMshipextensormusclesleft("5");}
        if("".equals(lef.getMshiprotatormusclesleft())){lef.setMshiprotatormusclesleft("5");}
        if("".equals(lef.getMshipabductormusclesleft())){lef.setMshipabductormusclesleft("5");}
        if("".equals(lef.getMshipadductormusclesleft())){lef.setMshipadductormusclesleft("5");}
        if("".equals(lef.getMskneeflexormusclesright())){lef.setMskneeflexormusclesright("5");}
	if("".equals(lef.getMskneeextensormusclesright())){lef.setMskneeextensormusclesright("5");}
        if("".equals(lef.getMskneeflexormusclesleft())){lef.setMskneeflexormusclesleft("5");}
	if("".equals(lef.getMskneeextensormusclesleft())){lef.setMskneeextensormusclesleft("5");}
        if("".equals(lef.getMsankleplanterflexormusclesright())){lef.setMsankleplanterflexormusclesright("5");}
        if("".equals(lef.getMsankledorsiflexormusclesright())){lef.setMsankledorsiflexormusclesright("5");}
        if("".equals(lef.getMsankleinvertormusclesright())){lef.setMsankleinvertormusclesright("5");}
        if("".equals(lef.getMsankleevertormusclesright())){lef.setMsankleevertormusclesright("5");}
        if("".equals(lef.getMsankleplanterflexormusclesleft())){lef.setMsankleplanterflexormusclesleft("5");}
        if("".equals(lef.getMsankledorsiflexormusclesleft())){lef.setMsankledorsiflexormusclesleft("5");}
        if("".equals(lef.getMsankleinvertormusclesleft())){lef.setMsankleinvertormusclesleft("5");}
        if("".equals(lef.getMsankleevertormusclesleft())){lef.setMsankleevertormusclesleft("5");}
        if("".equals(lef.getShortening())){lef.setShortening("0");}
        
        int romhipflexionextensionright= Integer.parseInt(lef.getRomhipflexionextensionright());
      
        int romhiprotationright= Integer.parseInt(lef.getRomhiprotationright());
        int romhipabductionadductionright= Integer.parseInt(lef.getRomhipabductionadductionright());
        int romhipflexionextensionleft=Integer.parseInt(lef.getRomhipflexionextensionleft());
        int romhiprotationleft =Integer.parseInt(lef.getRomhiprotationleft());
        int romhipabductionadductionleft =Integer.parseInt(lef.getRomhipabductionadductionleft());
        int romkneeflexionextensionright =Integer.parseInt(lef.getRomkneeflexionextensionright());
        int romkneeflexionextensionleft = Integer.parseInt(lef.getRomkneeflexionextensionleft());
        int romankledorsiflexionpalmarflexionright = Integer.parseInt(lef.getRomankledorsiflexionpalmarflexionright());
        int romankleinversioneversionright = Integer.parseInt(lef.getRomankleinversioneversionright());
        int romankledorsiflexionpalmarflexionleft = Integer.parseInt(lef.getRomankledorsiflexionpalmarflexionleft());
        int romankleinversioneversionleft = Integer.parseInt(lef.getRomankleinversioneversionleft());        
        int mshipflexormusclesright = Integer.parseInt(lef.getMshipflexormusclesright());
        int mshipextensormusclesright = Integer.parseInt(lef.getMshipextensormusclesright());
        int mshiprotatormusclesright = Integer.parseInt(lef.getMshiprotatormusclesright());
        int mshipabductormusclesright = Integer.parseInt(lef.getMshipabductormusclesright());
        int mshipadductormusclesright = Integer.parseInt(lef.getMshipadductormusclesright());        
        int mshipflexormusclesleft = Integer.parseInt(lef.getMshipflexormusclesleft());
        int mshipextensormusclesleft = Integer.parseInt(lef.getMshipextensormusclesleft());
        int mshiprotatormusclesleft = Integer.parseInt(lef.getMshiprotatormusclesleft());
        int mshipabductormusclesleft = Integer.parseInt(lef.getMshipabductormusclesleft());
        int mshipadductormusclesleft = Integer.parseInt(lef.getMshipadductormusclesleft());        
        int mskneeflexormusclesright = Integer.parseInt(lef.getMskneeflexormusclesright());
        int mskneeextensormusclesright = Integer.parseInt(lef.getMskneeextensormusclesright());
        int mskneeflexormusclesleft = Integer.parseInt(lef.getMskneeflexormusclesleft());
        int mskneeextensormusclesleft = Integer.parseInt(lef.getMskneeextensormusclesleft());        
        int msankleplanterflexormusclesright = Integer.parseInt(lef.getMsankleplanterflexormusclesright());
        int msankledorsiflexormusclesright = Integer.parseInt(lef.getMsankledorsiflexormusclesright());
        int msankleinvertormusclesright = Integer.parseInt(lef.getMsankleinvertormusclesright());
        int msankleevertormusclesright = Integer.parseInt(lef.getMsankleevertormusclesright());
        int msankleplanterflexormusclesleft = Integer.parseInt(lef.getMsankleplanterflexormusclesleft());
        int msankledorsiflexormusclesleft = Integer.parseInt(lef.getMsankledorsiflexormusclesleft());
        int msankleinvertormusclesleft = Integer.parseInt(lef.getMsankleinvertormusclesleft());
        int msankleevertormusclesleft = Integer.parseInt(lef.getMsankleevertormusclesleft());
        double shortening = Double.parseDouble(lef.getShortening());
        
        int Deformity = Integer.parseInt(lef.getDeformity());
        int Pain= Integer.parseInt(lef.getPain());
        int Loss_of_Function=Integer.parseInt(lef.getLoss_of_Function());
        int Complications=Integer.parseInt(lef.getComplications());
        int working_on_plane=Integer.parseInt(lef.getWorking_on_plane());
        int working_on_slope=Integer.parseInt(lef.getWorking_on_slope());
        int working_on_stairs=Integer.parseInt(lef.getWorking_on_stairs());
        int Standing=Integer.parseInt(lef.getStanding_on_both_legs());
        int Standing_on_effected=Integer.parseInt(lef.getStanding_on_effected());
        int Kneeling=Integer.parseInt(lef.getKneeling());
        int Squatting=Integer.parseInt(lef.getSquatting_on_floor());
        int Sitting=Integer.parseInt(lef.getSitting_cross_leg());
        int Taking=Integer.parseInt(lef.getTaking_turns());
        
         /*
          * calculating the Range of motion at rightside and leftside.
          *
          */
        float rhflexionextensionright = (((140-romhipflexionextensionright)*100)/140);
       
        float rhrotationright = (((90-romhiprotationright)*100)/90);
        float rhabductionadductionright = (((90-romhipabductionadductionright)*100)/90);
        double romhipright= ((rhflexionextensionright+rhrotationright+rhabductionadductionright)/3)*0.3;
        float rhflexionextensionleft = (((140-romhipflexionextensionleft)*100)/140);
        float rhrotationleft = (((90-romhiprotationleft)*100)/90);
        float rhabductionadductionleft = (((90-romhipabductionadductionleft)*100)/90);
        double romhipleft= ((rhflexionextensionleft+rhrotationleft+rhabductionadductionleft)/3)*0.3;        
        double rkflexionextensionright = (((125-romkneeflexionextensionright)*100)/125)*0.3;
        double rkflexionextensionleft = (((125-romkneeflexionextensionleft)*100)/125)*0.3;        
        float radorsiflexionpalmarflexionright = (((70-romankledorsiflexionpalmarflexionright)*100)/70);
        float rainversioneversionright = (((60-romankleinversioneversionright)*100)/60);
        double romankleright= ((radorsiflexionpalmarflexionright+rainversioneversionright)/2)*0.3;
        float radorsiflexionpalmarflexionleft = (((70-romankledorsiflexionpalmarflexionleft)*100)/70);
        float rainversioneversionleft = (((60-romankleinversioneversionleft)*100)/60);
        double romankleleft= ((radorsiflexionpalmarflexionleft+rainversioneversionleft)/2)*0.3;    
       double romright =(romhipright+rkflexionextensionright+romankleright);
        double romleft =(romhipleft+rkflexionextensionleft+romankleleft);
        
         /*
         *calculating the presence of complications means extra value.
         *
         */
       
        int extra =Deformity+Pain+Loss_of_Function+Complications;
     
        
         if(shortening<=0.5){
            shortininginches=0;
        }else{
          shortininginches=(shortening-0.5)*8;   
        }  
       
      
        
        
        /*
         *calculating the Stability component value.
         */     
 
      
        int stability=working_on_plane+working_on_slope+working_on_stairs+Standing+Standing_on_effected+Kneeling+Squatting+Sitting+Taking;
    
        
      
          
        /*
         *fetch the muscle strength values from LowerExtremityForm.
         *and calculating the muscle strength right and left.
         *
         */
        int mshflexormusclesright= msMethod(mshipflexormusclesright);
        int mshextensormusclesright= msMethod(mshipextensormusclesright);
        int mshrotatormusclesright= msMethod(mshiprotatormusclesright);
        int mshabductormusclesright= msMethod(mshipabductormusclesright);
        int mshadductormusclesright= msMethod(mshipadductormusclesright);        
        int mshflexormusclesleft= msMethod(mshipflexormusclesleft);
        int mshextensormusclesleft= msMethod(mshipextensormusclesleft);
        int mshrotatormusclesleft= msMethod(mshiprotatormusclesleft);
        int mshabductormusclesleft= msMethod(mshipabductormusclesleft);
        int mshadductormusclesleft= msMethod(mshipadductormusclesleft);   
        int mskflexormusclesright= msMethod(mskneeflexormusclesright);
        int mskextensormusclesright= msMethod(mskneeextensormusclesright);
        int mskflexormusclesleft= msMethod(mskneeflexormusclesleft);
        int mskextensormusclesleft= msMethod(mskneeextensormusclesleft);        
        int msaplanterflexormusclesright= msMethod(msankleplanterflexormusclesright);
        int msadorsiflexormusclesright= msMethod(msankledorsiflexormusclesright);
        int msainvertormusclesright= msMethod(msankleinvertormusclesright);
        int msaevertormusclesright= msMethod(msankleevertormusclesright);       
        int msaplanterflexormusclesleft= msMethod(msankleplanterflexormusclesleft);
        int msadorsiflexormusclesleft= msMethod(msankledorsiflexormusclesleft);
        int msainvertormusclesleft= msMethod(msankleinvertormusclesleft);
        int msaevertormusclesleft= msMethod(msankleevertormusclesleft);        
        double hipright = (((mshflexormusclesright+ mshextensormusclesright+mshrotatormusclesright+mshabductormusclesright+mshadductormusclesright)/5)*0.3);        
        double kneeright=(((mskflexormusclesright+mskextensormusclesright)/2)*0.3);
        double ankleright=(((msaplanterflexormusclesright+msadorsiflexormusclesright+msainvertormusclesright+msaevertormusclesright)/4)*0.3);
        double ms_right = hipright+kneeright+ankleright;        
        double hipleft= (((mshflexormusclesleft+ mshextensormusclesleft+mshrotatormusclesleft+mshabductormusclesleft+mshadductormusclesleft)/5)*0.3);
        double kneeleft=(((mskflexormusclesleft+mskextensormusclesleft)/2)*0.3);
        double ankleleft=(((msaplanterflexormusclesleft+msadorsiflexormusclesleft+msainvertormusclesleft+msaevertormusclesleft)/4)*0.3);
        double ms_left = hipleft+kneeleft+ankleleft;
     
        
        
        /*
         *By caling the method "add" we are applying the formula a+(((90-a)/90)*b)for ROM and MS.
         *and finding the total mobility component.
         */
        mobilitycomponentright=add(romright,ms_right);
        mobilitycomponentleft=add(romleft,ms_left);      
     
        mobilitycomponent=add(mobilitycomponentright,mobilitycomponentleft);
      
        
         /*
          *By caling the method "add" we are applying the formula a+(((90-a)/90)*b)for mobilitycomponent and stability.
          *and finding the lowerextrimity with out adding extra percentage.
          *
          */
        lowerwithoutextra=add(mobilitycomponent,stability);        
    
         /*
          *By caling the method "add" we are applying the formula a+(((90-a)/90)*b)for lowerwithoutextra and extradata.
          *finding the total LowerExtremity percentage .
          */
       if(extra>10) {
        
           extradata= 10+shortininginches;
           lowerextremity=add(lowerwithoutextra,extradata);
           }
           else if(extra<=10){
           extradata=extra+shortininginches;
           lowerextremity=add(lowerwithoutextra,extradata);
           }
     
       // request.setAttribute("lowerextremity",new Double(lowerextremity));
        
        if(lowerextremity>100){
            lowerextremitytotal=100;
        }else if(lowerextremity<=100){
            lowerextremitytotal=lowerextremity;
        }
        
        
//        HttpSession session=request.getSession();
//        session.setAttribute("lowerextremity",new Double(lowerextremitytotal));
        
        lef.setRomright(romright);
        lef.setRomleft(romleft);       
        lef.setExtra(extra);
        lef.setStability(stability);
        lef.setMsright(ms_right);
        lef.setMsleft(ms_left); 
        lef.setLowerextremity(lowerextremitytotal);
       
        return lef;
            }
            
    /**
     * 
     * @param a 
     * @param b 
     * @return 
     */
    public double cal(double a, double b) {
        double right = a + (((90 - a) / 90) * b);
        return right;
    }
    /**
     * 
     * @param input 
     * @return 
     */
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
    /**
     * 
     * @param a 
     * @param b 
     * @return 
     */
     public double add(double a, double b) {
        double add = 0;
        if (a > b) {
            add = cal(a, b);
        } else {
            add = cal(b, a);
        }
        return add;
    }
    }
    

