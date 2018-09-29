/*
 * PIDuetoNeurologicalConditionsImpl.java
 *
 * Created on January 2, 2009, 5:13 PM
 *
 */
package org.bf.disability.util;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.bean.PIDuetoNeurologicalConditionsBean;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dto.EvaluationDTO;
import org.bf.disability.service.EvaluationService;
import org.bf.disability.servicefactory.EvaluationServiceFactory;
import org.bf.disability.serviceimpl.ShowCalcualationsServiceImpl;

/**
 *
 * @author Syam
 */
public class PIDuetoNeurologicalConditionsImpl extends ShowCalcualationsServiceImpl {

    private double value;
    private double value1;
    private double value2;
    private double value3;
    private double value4;
    private double value5;
    private double value6;
    private double value7;
    private double value8;
    private double value9;
    private double value10;
    private double right;
    PIDuetoNeurologicalConditionsBean pIDuetoNeurologicalConditionsBean = new PIDuetoNeurologicalConditionsBean();

    public void populatePIDuetoNeurologicalConditionsCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException {
        try {
            EvaluationService evaluationservice = EvaluationServiceFactory.getEvaluationServiceImpl();
            EvaluationDTO evaluationdto = new EvaluationDTO();
            evaluationdto = evaluationservice.getEvaluationDetails(dataSource, personcode);
            try {
                BeanUtils.copyProperties(pIDuetoNeurologicalConditionsBean, evaluationdto);
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            }
            pIDuetoNeurologicalConditionsBean = PIDuetoNeurologicalConditionsCalcualtionsLogic(pIDuetoNeurologicalConditionsBean);
            request.setAttribute("pIDuetoNeurologicalConditionsBean", pIDuetoNeurologicalConditionsBean);
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource,sqlEx.toString(), "populatePIDuetoNeurologicalConditionsCalculations", "PIDuetoNeurologicalConditionsImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PIDuetoNeurologicalConditionsImpl", "populatePIDuetoNeurologicalConditionsCalculations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource,sqlEx.toString(), "populatePIDuetoNeurologicalConditionsCalculations", "PIDuetoNeurologicalConditionsImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PIDuetoNeurologicalConditionsImpl", "populatePIDuetoNeurologicalConditionsCalculations");
        }//end of catch block
    }

    public PIDuetoNeurologicalConditionsBean PIDuetoNeurologicalConditionsCalcualtionsLogic(PIDuetoNeurologicalConditionsBean pIDuetoNeurologicalConditionsBean) {

        int dom = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getDominantupperextremity());

        int upper = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getLossofsensationupper());
        int lower = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getLossofsensationlower());
       if (upper != 0 && lower != 0) {
            pIDuetoNeurologicalConditionsBean.setLossofsensationflag(true);
        }
        int up = upper + lower;
        pIDuetoNeurologicalConditionsBean.setSumoflossofsensation(String.valueOf(up));
        int neurolo = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getNeurologicalstatus());

        value = checkzerosforCal(up, neurolo, 1);
        //compairing the value of sensation upper&lower with Neurologicalstatus
//           if(up>neurolo){
//            double a =up;
//            double b=neurolo;
//            value=internalcal(a,b,1);
//        }else {
//            double a= neurolo;
//            double b= up;
//            value = internalcal(a,b,1);
//        }



        int intell = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getIntellectualimpairment());
        value1 = checkzerosforCal(value, intell, 2);
        //result value compare with intell
//      if(value>intell){
//            double a =value;
//            double b=intell;
//            value1=cal(a,b);
//        }else {
//            double a= value;
//            double b= intell;
//          value1 = cal(a,b);
//        }
        int speechde = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getSpeechdefect());

        value2 = checkzerosforCal(value1, speechde, 3);


//      if(value1>speechde){
//            double a =value1;
//            double b=speechde;
//            value3=cal(a,b);
//        }else {
//            double a= speechde;
//            double b= intell;
//            value3 = cal(a,b);
//        }

        int oculomotor = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getE6a());

        int Trochear = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getE6b());

        int Abducens = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getE6c());

        int Facial = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getE6d());

        int Accessory = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getE6e());

        int Hypoglossa = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getE6f());
        int Trigeminal = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getE6g());
        int Vagus = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getE6h());

        int motornerve = oculomotor + Trochear + Abducens + Facial + Accessory + Hypoglossa + Trigeminal + Vagus;
        StringBuffer motorbuffer = new StringBuffer();
        String motorstring = null;
        int count = 0;
        if (motornerve != 0) {
            pIDuetoNeurologicalConditionsBean.setMotorcranialnerveflag(true);


            if (oculomotor != 0) {
                motorbuffer.append(oculomotor + "+");
                count++;
            }
            if (Trochear != 0) {
                motorbuffer.append(Trochear + "+");
                count++;
            }
            if (Abducens != 0) {
                motorbuffer.append(Abducens + "+");
                count++;
            }
            if (Facial != 0) {
                motorbuffer.append(Facial + "+");
                count++;
            }
            if (Accessory != 0) {
                motorbuffer.append(Accessory + "+");
                count++;
            }
            if (Hypoglossa != 0) {
                motorbuffer.append(Hypoglossa + "+");
                count++;
            }
            if (Trigeminal != 0) {
                motorbuffer.append(Trigeminal + "+");
                count++;
            }
            if (Vagus != 0) {
                motorbuffer.append(Vagus + "+");
                count++;
            }
            if (count > 1) {
                motorstring = motorbuffer.substring(0, motorbuffer.length() - 1);
                motorstring = motorstring + "=" + motornerve;
                pIDuetoNeurologicalConditionsBean.setMotornervetotalflag(true);
                pIDuetoNeurologicalConditionsBean.setMotorstring(motorstring);
            }
        }
        if (motornerve > 100) {
            motornerve = 100;
            pIDuetoNeurologicalConditionsBean.setMotornerveflagforvalue(true);
        }
        value3 = checkzerosforCal(value2, motornerve, 4);

//      if(value3>motornerve){
//            double a =value3;
//            double b=motornerve;
//            value4=cal(a,b);
//        }else {
//            double a= motornerve;
//            double b= value3;
//            value4 = cal(a,b);
//        }



        int Olfactory = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getSca());

        int Optic = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getScb());

        int Vestibulocochlear = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getScc());

        int Glossopharyngeal = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getScd());

        int sensorynerve = Olfactory + Optic + Vestibulocochlear + Glossopharyngeal;
        StringBuffer sensorybuffer = new StringBuffer();
        String sensorystring = null;
        int count1 = 0;
        if (sensorynerve != 0) {
            pIDuetoNeurologicalConditionsBean.setSensorycranialnerveflag(true);
            if (Olfactory != 0) {
                sensorybuffer.append(Olfactory + "+");
                count1++;
            }
            if (Optic != 0) {
                sensorybuffer.append(Optic + "+");
                count1++;
            }
            if (Vestibulocochlear != 0) {
                sensorybuffer.append(Vestibulocochlear + "+");
                count1++;
            }
            if (Glossopharyngeal != 0) {
                sensorybuffer.append(Glossopharyngeal + "+");
                count1++;
            }
            if (count1 > 1) {
                sensorystring = sensorybuffer.substring(0, sensorybuffer.length() - 1);
                sensorystring = sensorystring + "=" + sensorynerve;
                pIDuetoNeurologicalConditionsBean.setSensorynervetotalflag(true);
                pIDuetoNeurologicalConditionsBean.setSensorystring(sensorystring);
            }
        }
        value4 = checkzerosforCal(value3, sensorynerve, 5);
//       if(value4>sensorynerve){
//            double a =value4;
//            double b=sensorynerve;
//            value5=cal(a,b);
//        }else {
//            double a= sensorynerve;
//            double b= value4;
//           value5 = cal(a,b);
//        }
//        

        int motor = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getMotorsystem());
        value5 = checkzerosforCal(value4, motor, 6);
//        if(value5>motor){
//            double a =value5;
//            double b=motor;
//            value6=cal(a,b);
//        }else {
//            double a=motor;
//            double b= value5;
//           value6 = cal(a,b);
//        }
        //just for database nort for calculation
//       if(pIDuetoNeurologicalConditionsBean.getSensorysystemh()!=null)
//       {
//       int s1=Integer.parseInt(pIDuetoNeurologicalConditionsBean.getSensorysystemh());
//     
//       }
//       if(pIDuetoNeurologicalConditionsBean.getSensorysystemf()!=null)
//       {
//       int s2=Integer.parseInt(pIDuetoNeurologicalConditionsBean.getSensorysystemf());
//       }
//       
//       if(pIDuetoNeurologicalConditionsBean.getSensorysystemt()!=null)
//       {
//       int s3=Integer.parseInt(pIDuetoNeurologicalConditionsBean.getSensorysystemt());
//       }

        int sensorysystem = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getSensorysystem());

        int sensorysystem1 = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getSensorysystem1());

        int sensorysystem2 = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getSensorysystem2());

        int totalsensory = sensorysystem + sensorysystem1 + sensorysystem2;
        StringBuffer sensorysystembuffer = new StringBuffer();
        String sensorysystemstring = null;
        int count2 = 0;
        if (totalsensory != 0) {
            pIDuetoNeurologicalConditionsBean.setSensorysystemflag(true);
            if (sensorysystem != 0) {
                sensorysystembuffer.append(sensorysystem + "+");
                count2++;
            }
            if (sensorysystem1 != 0) {
                sensorysystembuffer.append(sensorysystem1 + "+");
                count2++;
            }
            if (sensorysystem2 != 0) {
                sensorysystembuffer.append(sensorysystem2 + "+");
                count2++;
            }
            if (count2 > 1) {
                sensorysystemstring = sensorysystembuffer.substring(0, sensorysystembuffer.length() - 1);
                sensorysystemstring = sensorysystemstring + "=" + totalsensory;
                pIDuetoNeurologicalConditionsBean.setSensorysystemtotalflag(true);
                pIDuetoNeurologicalConditionsBean.setSensorysystemstring(sensorysystemstring);
            }

        }
        value6 = checkzerosforCal(value5, totalsensory, 7);
//       if(value6>totalsensory){
//            double a =value6;
//            double b=totalsensory;
//            value7=cal(a,b);
//        }else {
//            double a=totalsensory;
//            double b= value6;
//           value7 = cal(a,b);
//        }

        int bladder = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getBladderinvolvement());

        value7 = checkzerosforCal(value6, bladder, 8);
        
//         if(value7>bladder){
//            double a =value7;
//            double b=bladder;
//            value8=cal(a,b);
//        }else {
//            double a=bladder;
//            double b= value7;
//           value8 = cal(a,b);
//        }
        int inju = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getInjury());

        value8 = checkzerosforCal(value7, inju, 9);
//        if(value8>inju){
//            double a =value8;
//            double b=inju;
//            value9=cal(a,b);
//        }else {
//            double a=inju;
//            double b= value8;
//           value9 = cal(a,b);
//        }
        int ata = Integer.parseInt(pIDuetoNeurologicalConditionsBean.getAtaxia());

        value9 = checkzerosforCal(value8, ata, 10);

//       if(value9>ata){
//            double a =value9;
//            double b=ata;
//            value10=cal(a,b);
//        }else {
//            double a=ata;
//            double b= value9;
//           value10 = cal(a,b);
        // }




        double totalpercent = (double) dom + value9;
        StringBuffer totalbuffer = new StringBuffer();
        totalbuffer.append("Total Percentage=");
        if (dom != 0) {
            totalbuffer.append(dom + "+" + formatResult(value9) + "=" + formatResult(totalpercent));
        } else {
            totalbuffer.append(formatResult(totalpercent));
        }

        pIDuetoNeurologicalConditionsBean.setTotalpercentbuffer(totalbuffer);
        if (totalpercent > 100) {
            pIDuetoNeurologicalConditionsBean.setTotalpercentflag(true);
            totalpercent = 100;
        }


        pIDuetoNeurologicalConditionsBean.setTotalpercent(totalpercent);


        return pIDuetoNeurologicalConditionsBean;
    }

    public double cal(double a, double b) {
        right = a + (((90 - a) / 90) * b);
        return right;
    }

    public double internalcal(double a, double b, int i) {
        StringBuffer temp = new StringBuffer();
        temp.append(formatResult(a) + " + ( ( ( 90 - " + formatResult(a) + " ) /90) x" + formatResult(b) + ")");
        right = a + (((90 - a) / 90) * b);
        temp.append(" = " + formatResult(right) + "\n\n");
        decideandSet(i, temp);
        return right;
    }

    public double checkzerosforCal(double a, double b, int i) {
        if (a == 0 && b == 0) {
            value = 0;
        } else if (a != 0 && b == 0) {
            value = a;
        } else if (a == 0 && b != 0) {
            value = b;
        } else {
            if (a > b) {
                value = internalcal(a, b, i);
            } else {
                value = internalcal(b, a, i);
            }
        }
        return value;
    }

    public void decideandSet(int i, StringBuffer str) {
        switch (i) {
            case 1:
                pIDuetoNeurologicalConditionsBean.setValue1flag(true);
                pIDuetoNeurologicalConditionsBean.setValue1(str);
                break;
            case 2:
                pIDuetoNeurologicalConditionsBean.setValue2flag(true);
                pIDuetoNeurologicalConditionsBean.setValue2(str);
                break;
            case 3:
                pIDuetoNeurologicalConditionsBean.setValue3flag(true);
                pIDuetoNeurologicalConditionsBean.setValue3(str);
                break;
            case 4:
                pIDuetoNeurologicalConditionsBean.setValue4flag(true);
                pIDuetoNeurologicalConditionsBean.setValue4(str);
                break;
            case 5:
                pIDuetoNeurologicalConditionsBean.setValue5flag(true);
                pIDuetoNeurologicalConditionsBean.setValue5(str);
                break;
            case 6:
                pIDuetoNeurologicalConditionsBean.setValue6flag(true);
                pIDuetoNeurologicalConditionsBean.setValue6(str);
                break;
            case 7:
                pIDuetoNeurologicalConditionsBean.setValue7flag(true);
                pIDuetoNeurologicalConditionsBean.setValue7(str);
                break;
            case 8:
                pIDuetoNeurologicalConditionsBean.setValue8flag(true);
                pIDuetoNeurologicalConditionsBean.setValue8(str);
                break;
            case 9:
                pIDuetoNeurologicalConditionsBean.setValue9flag(true);
                pIDuetoNeurologicalConditionsBean.setValue9(str);
                break;
            case 10:
                pIDuetoNeurologicalConditionsBean.setValue10flag(true);
                pIDuetoNeurologicalConditionsBean.setValue10(str);
                break;
        }
    }

    public double formatResult(double val) {
        BigDecimal temp1 = new BigDecimal(val).setScale(2, BigDecimal.ROUND_HALF_UP);
        double temp2 = (new Double(temp1.doubleValue())).doubleValue();
        return temp2;
    }
}
     
    

