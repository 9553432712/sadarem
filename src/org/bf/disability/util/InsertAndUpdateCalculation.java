

package org.bf.disability.util;

  import org.bf.disability.dto.EvaluationDTO;
  import org.bf.disability.form.EvaluationActionForm;
/**
 * 
 * @author Sunima
 */
  public class InsertAndUpdateCalculation {

    /**
     * 
     * @ This Method is use for calculate the percentage of Evaluation of Physical Impairment.
     * @param form evaluationform
     */
    public EvaluationActionForm calculation(EvaluationActionForm evaluationform) 
        {

        // Local Variables.

     double value = 0;
     double value1 = 0;
     double value3 = 0;
     double value4 = 0;
     double value5 = 0;
     double value6 = 0;
     double value7 = 0;
     double value8 = 0;
     double value9 = 0;
     double value10 = 0;
   
    
     int dom =Integer.parseInt(evaluationform.getDominantupperextremity());
   
     int  upper= Integer.parseInt(evaluationform.getLossofsensationupper());

     int  lower= Integer.parseInt(evaluationform.getLossofsensationlower());
    
     int up=upper+lower  ;   
     int  neurolo = Integer.parseInt(evaluationform.getNeurologicalstatus());
 
   
     //compairing the value of sensation upper&lower with Neurologicalstatus
           if(up>neurolo){
            double a =up;
            double b=neurolo;
            value=cal(a,b);
        }else {
            double a= neurolo;
            double b= up;
            value = cal(a,b);
        }
    
    
  
      int intell =Integer.parseInt(evaluationform.getIntellectualimpairment());
   
      //result value compare with intell
      if(value>intell){
            double a =value;
            double b=intell;
            value1=cal(a,b);
        }else {
            double a= value;
            double b= intell;
          value1 = cal(a,b);
        }
     
           
   
     int speechde =Integer.parseInt(evaluationform.getSpeechdefect());
     
      
           
      if(value1>speechde){
            double a =value1;
            double b=speechde;
            value3=cal(a,b);
        }else {
            double a= speechde;
            double b= intell;
            value3 = cal(a,b);
        }
               
      int oculomotor =Integer.parseInt(evaluationform.getE6a());
           
      int  Trochear= Integer.parseInt(evaluationform.getE6b());
    
      int Abducens = Integer.parseInt(evaluationform.getE6c());
   
      int Facial = Integer.parseInt(evaluationform.getE6d());
    
      int Accessory = Integer.parseInt(evaluationform.getE6e());
    
      int Hypoglossa = Integer.parseInt(evaluationform.getE6f());

      int Trigeminal = Integer.parseInt(evaluationform.getE6g());

      int Vagus = Integer.parseInt(evaluationform.getE6h());
    
      int motornerve=oculomotor+Trochear +Abducens+Facial +Accessory+Hypoglossa+Trigeminal+Vagus;
         
      if(motornerve>100)
      {
         motornerve=100;
      }
    
      
      if(value3>motornerve){
            double a =value3;
            double b=motornerve;
            value4=cal(a,b);
        }else {
            double a= motornerve;
            double b= value3;
            value4 = cal(a,b);
        }
     
         
      
      int  Olfactory = Integer.parseInt(evaluationform.getSca());
      
      int Optic =Integer.parseInt(evaluationform.getScb());
      
      int  Vestibulocochlear  =Integer.parseInt(evaluationform.getScc());
      
      int Glossopharyngeal = Integer.parseInt(evaluationform.getScd());
      
      int sensorynerve=Olfactory +Optic +Vestibulocochlear +Glossopharyngeal;
    
      
       if(value4>sensorynerve){
            double a =value4;
            double b=sensorynerve;
            value5=cal(a,b);
        }else {
            double a= sensorynerve;
            double b= value4;
           value5 = cal(a,b);
        }
        
      
      int  motor = Integer.parseInt(evaluationform.getMotorsystem());
 
        if(value5>motor){
            double a =value5;
            double b=motor;
            value6=cal(a,b);
        }else {
            double a=motor;
            double b= value5;
           value6 = cal(a,b);
        }
      //just for database nort for calculation
       if(evaluationform.getSensorysystemh()!=null)
       {
       int s1=Integer.parseInt(evaluationform.getSensorysystemh());
     
       }
       if(evaluationform.getSensorysystemf()!=null)
       {
       int s2=Integer.parseInt(evaluationform.getSensorysystemf());
       }
       
       if(evaluationform.getSensorysystemt()!=null)
       {
       int s3=Integer.parseInt(evaluationform.getSensorysystemt());
       }
       
       int sensorysystem =Integer.parseInt(evaluationform.getSensorysystem());
     
       int sensorysystem1 =Integer.parseInt(evaluationform.getSensorysystem1());
     
       int sensorysystem2=Integer.parseInt(evaluationform.getSensorysystem2());
    
       int totalsensory=sensorysystem+sensorysystem1+sensorysystem2;
    
       
       if(value6>totalsensory){
            double a =value6;
            double b=totalsensory;
            value7=cal(a,b);
        }else {
            double a=totalsensory;
            double b= value6;
           value7 = cal(a,b);
        }
           
      int bladder = Integer.parseInt(evaluationform.getBladderinvolvement());
   
            
         if(value7>bladder){
            double a =value7;
            double b=bladder;
            value8=cal(a,b);
        }else {
            double a=bladder;
            double b= value7;
           value8 = cal(a,b);
        }
       int inju= Integer.parseInt(evaluationform.getInjury());
      
    
        
        if(value8>inju){
            double a =value8;
            double b=inju;
            value9=cal(a,b);
        }else {
            double a=inju;
            double b= value8;
           value9 = cal(a,b);
        }
      int ata = Integer.parseInt(evaluationform.getAtaxia());
      
    
        
       if(value9>ata){
            double a =value9;
            double b=ata;
            value10=cal(a,b);
        }else {
            double a=ata;
            double b= value9;
           value10 = cal(a,b);
        }
    
                         
      
              
        double totalpercent=(double)dom+value10;
       
        
         if(totalpercent>100)
         {
             totalpercent=100;
         }
        
        
       evaluationform.setTotalpercent(totalpercent); 
    
        
      return evaluationform;
     }
       public double cal(double a,double b)
      {
        double right= a+(((90-a)/90)*b);
   
        return right;
       }
     
  }
         
