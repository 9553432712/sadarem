/*
 * TrunkCalculation.java
 *
 * Created on July 9, 2008, 12:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.util;

import javax.servlet.http.HttpServletRequest;
import org.bf.disability.form.TrunkForm;

/**
 *
 * @author Bapinaidu.t
 */
public class TrunkCalculation {
      
       
    /** Creates a new instance of TrunkCalculation */
    public TrunkCalculation() {
    }
    
    public TrunkForm calculation(TrunkForm trunkform,HttpServletRequest request){

       double scoliosisextra = 0;
       double kyphosisextra = 0;
       double trunk = 0;
       double trunktotal = 0;
       double cardio_leg = 0;
     /*
          modified by ganesh 
        */
         if("".equals(trunkform.getCardio_associateleg())){trunkform.setCardio_associateleg("0");}
         if("".equals(trunkform.getScoliosis_measure())){trunkform.setScoliosis_measure("0");}
         if("".equals(trunkform.getKyphosis_measure())){trunkform.setKyphosis_measure("0");}
         if("".equals(trunkform.getCardio_chest())){trunkform.setCardio_chest("0");}
         if("".equals(trunkform.getCardio_counting())){trunkform.setCardio_counting("0");}
         if("".equals(trunkform.getCardio_associatecosmetic())){trunkform.setCardio_associatecosmetic("0");}
     /*
      modified by ganesh
      */
     
        int compression= Integer.parseInt(trunkform.getCompression());
        int posterior_fusion= Integer.parseInt(trunkform.getPosterior_fusion());
        int posterior_persistent= Integer.parseInt(trunkform.getPosterior_persistent());
        int severe_fire= Integer.parseInt(trunkform.getSevere_fire());
        int severe_inadequate= Integer.parseInt(trunkform.getSevere_inadequate());
        int cervical_disc= Integer.parseInt(trunkform.getCervical_disc());
        int cervical_pain= Integer.parseInt(trunkform.getCervical_pain());
        int thoracic_less= Integer.parseInt(trunkform.getThoracic_less());
        int thoracic_more= Integer.parseInt(trunkform.getThoracic_more());
        int thoracic_fusion= Integer.parseInt(trunkform.getThoracic_fusion());
        int thoracic_radiologically= Integer.parseInt(trunkform.getThoracic_radiologically());
        int fracture_less= Integer.parseInt(trunkform.getFracture_less());
        int fracture_more= Integer.parseInt(trunkform.getFracture_more());
        int fracture_radiologically= Integer.parseInt(trunkform.getFracture_radiologically());
        int disc_persistent= Integer.parseInt(trunkform.getDisc_persistent());
        int disc_pain= Integer.parseInt(trunkform.getDisc_pain());
        int disc_diseases= Integer.parseInt(trunkform.getDisc_diseases());
        int disc_stifness= Integer.parseInt(trunkform.getDisc_stifness());
        int scoliosis_measure= Integer.parseInt(trunkform.getScoliosis_measure());
        int scoliosis_torso= Integer.parseInt(trunkform.getScoliosis_torso());
        int kyphosis_measure= Integer.parseInt(trunkform.getKyphosis_measure());
        int kyphosis_torso= Integer.parseInt(trunkform.getKyphosis_torso());
     boolean  scoliosis=(trunkform.isScoliosis());
  
    boolean  kyphosis=(trunkform.isKyphosis());

     
        int head= Integer.parseInt(trunkform.getHead());
        int cardio_chest= Integer.parseInt(trunkform.getCardio_chest());
        int cardio_counting= Integer.parseInt(trunkform.getCardio_counting());
        int cardio_associatepain= Integer.parseInt(trunkform.getCardio_associatepain());
        int cardio_associatecosmetic= Integer.parseInt(trunkform.getCardio_associatecosmetic());
        double cardio_inches= Double.parseDouble(trunkform.getCardio_associateleg());
        int traumatic=compression+posterior_fusion+posterior_persistent+
                severe_fire+severe_inadequate+cervical_disc+cervical_pain+
                thoracic_less+thoracic_more+thoracic_fusion+
                thoracic_radiologically+fracture_less+fracture_more+
                fracture_radiologically+disc_persistent+disc_pain+
                disc_diseases+disc_stifness;
        
        int totalscoliosis=scoliosis_measure+scoliosis_torso;
      
        int  totalkyphosis=kyphosis_measure+kyphosis_torso;
     
        if(cardio_inches<=0.5){
            cardio_leg=0;
        }else{
            cardio_leg=(cardio_inches-0.5)*8;
        }
        double extra=head+cardio_chest+cardio_counting+cardio_associatepain+
                cardio_associatecosmetic+cardio_leg;
        int i=0;
        if((scoliosis==true) &&(kyphosis==true)){
            scoliosisextra=add(totalscoliosis,extra);
            kyphosisextra=add(totalkyphosis,extra); 
            i++;
        } else if(scoliosis==true){
            scoliosisextra=add(totalscoliosis,extra);
             i++;
        } else if(kyphosis==true){
            kyphosisextra=add(totalkyphosis,extra);
             i++;
        }
        if(i==0){
            scoliosisextra=0;
            kyphosisextra=0;
        }
        double nontraumatic =add(scoliosisextra,kyphosisextra);
        
        
         if(traumatic>100) {
        
           traumatic= 100;
           trunk=add(traumatic,nontraumatic);
           }
           else if(traumatic<=100){
           traumatic = traumatic;
           trunk=add(traumatic,nontraumatic);
           }
        
        
         if(trunk>100){
            trunktotal=100;
        }else if(trunk<=100){
            trunktotal=trunk;
        }
   
        trunkform.setTraumatic(traumatic);
        trunkform.setTotalscoliosis(totalscoliosis);
        trunkform.setTotalkyphosis(totalkyphosis);
        trunkform.setNontraumatic(nontraumatic);    
        trunkform.setTrunk(trunktotal);
//         HttpSession session=request.getSession(true);
//         session.setAttribute("trunk",new Double(trunktotal));
        
        
        return trunkform;
        
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
    public double cal(double a,double b) {
       double right= a+(((90-a)/90)*b);
        return right;
    }
    
}
