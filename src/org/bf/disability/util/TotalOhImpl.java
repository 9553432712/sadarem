/*
 * TotalOhImpl.java
 *
 * Created on January 15, 2009, 12:01 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.bean.TotalOhBean;
import org.bf.disability.serviceimpl.ShowCalcualationsServiceImpl;
import org.bf.disability.dto.TotalPercentageDTO;
import org.bf.disability.dao.TotalDisabilityDAO;

/**
 *
 * @author bapinaidu.t
 */
public class TotalOhImpl extends ShowCalcualationsServiceImpl{
    TotalOhBean totalOhBean=new TotalOhBean();
    
    public void populateTotalOhCalculations(DataSource datasource,HttpServletRequest request,String personcode)throws Exception {
        TotalPercentageDTO totalPercentagedto = new TotalPercentageDTO();
        TotalDisabilityDAO dao = new TotalDisabilityDAO();
        totalPercentagedto = dao.getTotalpercentage(datasource, personcode);
        double upperextrimity = totalPercentagedto.getUpperExtrimity();
        double lowerextremity = totalPercentagedto.getLowerExtrimity();
        double amputation = totalPercentagedto.getAmputation();
        double transverse = totalPercentagedto.getTransverse();
        double trunk = totalPercentagedto.getTrunk();
        double Evaluation = totalPercentagedto.getEvaluation();
        double cardiopulmonary = totalPercentagedto.getCardiopulmonary();
        double dwarfism = totalPercentagedto.getDwarfism();
        
        
       
        double upperextrimitytotal=Math.round(upperextrimity);
        double lowererextrimitytotal=Math.round(lowerextremity);
        double amputationtotal=Math.round(amputation);
        double transversetotal=Math.round(transverse);
        double trunktotal=Math.round( trunk);
        double Evaluationtotal=Math.round(Evaluation);
        double cardiopulmonarytotal=Math.round(cardiopulmonary);
        double dwarfismtotal=Math.round(dwarfism);
        double totaldisability=0;
        
        totalOhBean.setUpperextrimity(upperextrimitytotal);
        totalOhBean.setLowererextrimity(lowererextrimitytotal);
        totalOhBean.setAmputation(amputationtotal);
        totalOhBean.setTrunk(trunktotal);
        totalOhBean.setTransverse(transversetotal);
        totalOhBean.setCardiopulmonary(cardiopulmonarytotal);
        totalOhBean.setEvaluation(Evaluationtotal);
        totalOhBean.setDwarfism(dwarfismtotal);
        
        ArrayList ohlist=new ArrayList();
        
        ohlist.add(upperextrimity);
        ohlist.add(lowerextremity);
        ohlist.add(amputation);
        ohlist.add(transverse);
        ohlist.add(trunk);
        ohlist.add(Evaluation);
        ohlist.add(cardiopulmonary);
        ohlist.add(dwarfism);
        totalOhBean=populateTotalOhCalculations(totalOhBean,ohlist);
        
        request.setAttribute("totalOhBean",totalOhBean);
    }
    
    public TotalOhBean populateTotalOhCalculations(TotalOhBean totalOhBean, ArrayList ohlist) {
        
        double finalresult = 0,final1=0,final2=0;
        int max=0;
        final1=needCalculation(ohlist);
        if(final1>100)
        final1=100.00;
        totalOhBean.setOhfinalvalue(formatResult(final1));
        return totalOhBean;
    }
    
    public double needCalculation(ArrayList ohlist) {
        
        double final1;
        StringBuffer temp=new StringBuffer();
        String commaSeparatedFormat=new String();
        // Findout the Highest value in the arraylist.
        double max1=((Double)Collections.max(ohlist)).doubleValue();
        //sorting the arraylist into ascending order.
        Collections.sort(ohlist);
        Collections.reverse(ohlist);
        for(int i=0;i<ohlist.size();i++) {
            if(((Double)ohlist.get(i)).doubleValue()==0)
                break;
            else {
                commaSeparatedFormat=commaSeparatedFormat+((Double)ohlist.get(i)).doubleValue();
                if(i<ohlist.size()-1) {
                    if(((Double)ohlist.get(i+1)).doubleValue()!=0) {
                        commaSeparatedFormat=commaSeparatedFormat+", ";
                    }
                }
            }
            
        }

        
        //if highest value is 0 means no elements are there in the array.
        if(max1==0) {
            final1=max1;
            temp.append("\n");
            totalOhBean.setTolalformula(temp);
        }
        
        //if atleast one value is 90 or >90 then directly take that value as
        //final1.
        else if(max1>=100) {
            final1=max1;
            temp.append("so 100 will be directly taken as maximum value.\n");
            temp.append("In this case no calculation is performed. Maximum value will be directly taken as 100.\n");
            totalOhBean.setTolalformula(temp);
        }
        else {
            temp.append("The selected values are: ");
            temp.append(commaSeparatedFormat);
            temp.append("\n\n");
            final1=calcResult(ohlist);
        }
        return final1;
    }
    public double calcResult(ArrayList obj) {
        double max1=0;
        Object o[]=obj.toArray();
        double a[]=new double[o.length];
        for(int i=0;i<o.length;i++)
            a[i]=((Double)o[i]).doubleValue();
        if(a[1]==0) {
            max1=a[0];
            StringBuffer temp=new StringBuffer();
            temp.append("As it found only one value has selected. So no calculation is required,\n The selected value will be taken as total value for Locomotor . That is, " + max1+"");
            totalOhBean.setTolalformula(temp);
        }
        
        else if(a.length==2)
            max1=internalCalculation(a[0],a[1]);
        
        else {
            max1=internalCalculation(a[0],a[1]);
            for(int i=2;i<a.length;i++) {
                if(a[i]==0)
                    break;
                max1=internalCalculation(max1,a[i]);
            }
        }
        
        return max1;
    }
    
    public double internalCalculation(double a,double b) {
        //StringBuffer temp=getCalculationSideBuffer(side);
        StringBuffer temp=new StringBuffer();
        if(totalOhBean.getTolalformula()!=null){
            temp.append(totalOhBean.getTolalformula());
        }
        temp.append("a+((b*(90-a))/90) Greater value is 'a' ("+formatResult(a)+","+formatResult(b)+") \n = ");
        temp.append(formatResult(a)+" + ( ( "+formatResult(b)+" x ( 90 - " + formatResult(a)+" ) )/90 )");
        a=a+((b*(90-a))/90);
        temp.append("\n = " + formatResult(a) + "\n\n");
        totalOhBean.setTolalformula(temp);
        return a;
    }
    
    public double formatResult(double val) {
        BigDecimal temp1 = new BigDecimal(val).setScale(2,BigDecimal.ROUND_HALF_UP);
        double temp2= (new Double(temp1.doubleValue())).doubleValue();
        return temp2;
    }
    
    
}
