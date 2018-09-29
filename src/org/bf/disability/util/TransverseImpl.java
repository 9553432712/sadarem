/*
 * TransverseImpl.java
 *
 * Created on January 2, 2009, 5:10 PM
 *
 */
package org.bf.disability.util;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.bean.TransverseBean;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dto.TransverseDTO;
import org.bf.disability.service.TransverseService;
import org.bf.disability.servicefactory.TransverseServiceFactory;
import org.bf.disability.serviceimpl.ShowCalcualationsServiceImpl;

/**
 *
 * @author Deviprasad_t
 */
public class TransverseImpl extends ShowCalcualationsServiceImpl {

    StringBuffer leftsidecalculation = new StringBuffer();
    StringBuffer righttsidecalculation = new StringBuffer();
    TransverseBean transverseBean = new TransverseBean();

    public void populateTransverseCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException {
        try {
            TransverseDTO transverseDTO = new TransverseDTO();
            TransverseService transverseService = TransverseServiceFactory.getTransverseServiceImpl();
            //TransverseBean transverseBean=new TransverseBean();

            transverseDTO = transverseService.getTransverseDetails(dataSource, personcode);

            BeanUtils.copyProperties(transverseBean, transverseDTO);

            transverseBean = transverseCalculationsLogic(transverseBean);

            request.setAttribute("transversebean", transverseBean);
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource,sqlEx.toString(), "populateTransverseCalculations", "TransverseImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransverseImpl", "populateTransverseCalculations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource,sqlEx.toString(), "populateTransverseCalculations", "TransverseImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransverseImpl", "populateTransverseCalculations");
        }//end of catch block
    }

    public TransverseBean transverseCalculationsLogic(TransverseBean transverseBean) {
        transverseBean = insertCalculationPart(transverseBean);
        return transverseBean;
    }

    public TransverseBean insertCalculationPart(TransverseBean transverseBean) {
        double finalresult, final1 = 0, final2 = 0;
        int max = 0;

        ArrayList a1 = new ArrayList();
        ArrayList a2 = new ArrayList();
        transverseBean.setLeftsidecalculation(new StringBuffer("\n"));
        transverseBean.setRightsidecalculation(new StringBuffer("\n"));
        a1.add(new Integer(transverseBean.getShoulderdisarticulationrightside()));
        a1.add(new Integer(transverseBean.getHipdisarticulationrightside()));
        a1.add(new Integer(transverseBean.getAboveelbowamputeerightside()));
        a1.add(new Integer(transverseBean.getKneeamputeerightside()));
        a1.add(new Integer(transverseBean.getElbowdisarticulationrightside()));
        a1.add(new Integer(transverseBean.getBelowelbowamputeerightside()));
        a1.add(new Integer(transverseBean.getWristdisarticulationrightside()));
        a1.add(new Integer(transverseBean.getCarpalbonesrightside()));



        a2.add(new Integer(transverseBean.getShoulderdisarticulationleftside()));
        a2.add(new Integer(transverseBean.getHipdisarticulationleftside()));
        a2.add(new Integer(transverseBean.getAboveelbowamputeeleftside()));
        a2.add(new Integer(transverseBean.getKneeamputeeleftside()));
        a2.add(new Integer(transverseBean.getElbowdisarticulationleftside()));
        a2.add(new Integer(transverseBean.getBelowelbowamputeeleftside()));
        a2.add(new Integer(transverseBean.getWristdisarticulationleftside()));
        a2.add(new Integer(transverseBean.getCarpalbonesleftside()));


        final1 = needCalculation(a1, "Right");

        final2 = needCalculation(a2, "Left");


        if (final1 >= final2) {
            finalresult = internalCalculationforFinal(final1, final2, "Right", "Left");
        } else {
            finalresult = internalCalculationforFinal(final2, final1, "Left", "Right");
        }

        if (finalresult > 100) {
            finalresult = 100.00;
        }

        transverseBean.setFinalres(finalresult);
        return transverseBean;
    }

    public double needCalculation(ArrayList a, String side) {
        double final1;
        StringBuffer temp = new StringBuffer();
        String commaSeparatedFormat = new String();

        // Findout the Highest value in the arraylist.
        int max1 = ((Integer) Collections.max(a)).intValue();


        //sorting the arraylist into ascending order.
        Collections.sort(a);
        Collections.reverse(a);
        for (int i = 0; i < a.size(); i++) {
            if (((Integer) a.get(i)).intValue() == 0) {
                break;
            } else {
                commaSeparatedFormat = commaSeparatedFormat + ((Integer) a.get(i)).intValue();
                if (i < a.size() - 1) {
                    if (((Integer) a.get(i + 1)).intValue() != 0) {
                        commaSeparatedFormat = commaSeparatedFormat + ", ";
                    }
                }
            }

        }
        temp = getCalculationSideBuffer(side);

        //if highest value is 0 means no elements are there in the array.
        if (max1 == 0) {
            final1 = max1;
            temp.append(side + " side no data found. That means maximum value for " + side + " side is 0");
            // temp.append("\n");
            temp.append("\n");
            decideSideandAppendValues(temp, side);
        } //if atleast one value is 90 or >90 then directly take that value as
        //final1.
        else if (max1 >= 90) {
            final1 = max1;
            temp.append("The selected values are: ");
            temp.append(new String(commaSeparatedFormat));
            temp.append("\n\n");
            temp.append("As it found one/more selected value/s in the " + side + " side has/have value/s  \ngreater than or equal to 90,\n");
            temp.append("so 90 will be directly taken as maximum value.\n");
            temp.append("In this case no calculation is performed. Maximum value will be directly taken as 90.\n");
            decideSideandAppendValues(temp, side);
        } else {
            temp.append("The selected values are: ");
            temp.append(commaSeparatedFormat);
            temp.append("\n\n");
            decideSideandAppendValues(temp, side);
            final1 = calcResult(a, side);
        }

        return final1;

    }

    public double calcResult(ArrayList obj, String side) {
        double max1 = 0;



        Object o[] = obj.toArray();
        int a[] = new int[o.length];

        for (int i = 0; i < o.length; i++) {
            a[i] = ((Integer) o[i]).intValue();
        }

        if (a[1] == 0) {
            max1 = a[0];
            StringBuffer temp = getCalculationSideBuffer(side);
            temp.append("As it found only one value has selected. So no calculation is required on the " + side + " side.\n");
            temp.append("The selected value will be taken as total value for " + side + " side. That is, " + max1);
            decideSideandAppendValues(temp, side);
        } else if (a.length == 2) {
            max1 = internalCalculation(a[0], a[1], side);
        } else {
            max1 = internalCalculation(a[0], a[1], side);
            for (int i = 2; i < a.length; i++) {
                if (a[i] == 0) {
                    break;
                }
                max1 = internalCalculation(max1, a[i], side);
            }
        }

        return max1;
    }

    public double internalCalculation(double a, double b, String side) {
        StringBuffer temp = getCalculationSideBuffer(side);
        temp.append(formatResult(a) + " + ( ( " + formatResult(b) + " x ( 90 - " + formatResult(a) + " ) )/90 )");
        a = a + ((b * (90 - a)) / 90);

        temp.append(" = " + formatResult(a) + "\n\n");
        decideSideandAppendValues(temp, side);
        return a;
    }

    public double internalCalculationforFinal(double a, double b, String res1, String res2) {
        StringBuffer temp = new StringBuffer();
        temp.append("The final " + res1 + " side value is: " + formatResult(a) + "\n");
        temp.append("The final " + res2 + " side value is: " + formatResult(b) + "\n\n");
        temp.append(formatResult(a) + " + ( ( " + formatResult(b) + " x ( 90 - " + formatResult(a) + " ) )/90 )");
        a = a + ((b * (90 - a)) / 90);
        temp.append(" = " + formatResult(a) + "\n\n");
        temp.append("The final percentage of Transverse Deficiency is:  " + formatResult(a) + "\n");
        transverseBean.setFinalpercentageofdisability(temp);
        return a;
    }

    public StringBuffer prepareJsp(ArrayList right, ArrayList left, TransverseBean transverseBean) {

//        StringUtils s=new StringUtils();     


        return null;
    }

    public void decideSideandAppendValues(StringBuffer str, String side) {
        if (side.equalsIgnoreCase("Left")) {
            transverseBean.setLeftsidecalculation(str);
        } else {
            transverseBean.setRightsidecalculation(str);
        }

    }

    public StringBuffer getCalculationSideBuffer(String side) {
        StringBuffer temp = new StringBuffer();
        if (side.equalsIgnoreCase("Left")) {
            temp = transverseBean.getLeftsidecalculation();
        } else {
            temp = transverseBean.getRightsidecalculation();
        }
        return temp;
    }

    public double formatResult(double val) {
        BigDecimal temp1 = new BigDecimal(val).setScale(2, BigDecimal.ROUND_HALF_UP);
        double temp2 = (new Double(temp1.doubleValue())).doubleValue();
        return temp2;
    }
}
