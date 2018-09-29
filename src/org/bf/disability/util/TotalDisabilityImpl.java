/*
 * TotalDisabilityImpl.java
 *
 * Created on January 20, 2009, 10:29 AM
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
import org.bf.disability.bean.TotalDisabilityBean;
import org.bf.disability.serviceimpl.ShowCalcualationsServiceImpl;
import org.bf.disability.dto.TotalPercentageDTO;
import org.bf.disability.dao.TotalDisabilityDAO;

/**
 *
 * @author Bapinaidu.t
 */
public class TotalDisabilityImpl extends ShowCalcualationsServiceImpl {

    TotalDisabilityBean totalDisabilityBean = new TotalDisabilityBean();

    public void populateTotalDisabilityCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws Exception {

        TotalPercentageDTO totalPercentagedto = new TotalPercentageDTO();
        TotalDisabilityDAO dao = new TotalDisabilityDAO();
        totalPercentagedto = dao.getTotalpercentage(dataSource, personcode);
        
        double hearingimpairment = totalPercentagedto.getHearingimpairment();
        double visualimpairment = totalPercentagedto.getVisualimpairment();
        double mentalretardation = totalPercentagedto.getMentalretardation();
        double mentalillness = totalPercentagedto.getMentalillness();
        double totalphysical = totalPercentagedto.getTotalphysical();




        double totalphysicalimpairment = Math.round(totalphysical);
        double hearingimpairmenttotal = Math.round(hearingimpairment);
        double visualimpairmenttotal = Math.round(visualimpairment);
        double mentalretardationtotal = Math.round(mentalretardation);
        double mentalillnesstotal = Math.round(mentalillness);
        double totaldisability = 0;

        totalDisabilityBean.setTotalphysical(totalphysicalimpairment);
        totalDisabilityBean.setHearingimpairment(hearingimpairmenttotal);
        totalDisabilityBean.setVisualimpairment(visualimpairmenttotal);
        totalDisabilityBean.setMentalretardation(mentalretardationtotal);
        totalDisabilityBean.setMentalillness(mentalillnesstotal);

        ArrayList totallist = new ArrayList();

        totallist.add(totalphysical);
        totallist.add(hearingimpairment);
        totallist.add(visualimpairment);
        totallist.add(mentalretardation);
        totallist.add(mentalillness);

        totalDisabilityBean = populateTotalDisabilityCalculations(totalDisabilityBean, totallist);

        request.setAttribute("totalDisabilityBean", totalDisabilityBean);
    }

    public TotalDisabilityBean populateTotalDisabilityCalculations(TotalDisabilityBean totalDisabilityBean, ArrayList totallist) {

        double finalresult = 0, final1 = 0, final2 = 0;
        int max = 0;
        final1 = needCalculation(totallist);
        if (final1 > 100) {
            final1 = 100.00;
        }
        totalDisabilityBean.setOhfinalvalue(formatResult(final1));
        return totalDisabilityBean;
    }

    public double needCalculation(ArrayList totallist) {

        double final1;
        StringBuffer temp = new StringBuffer();
        String commaSeparatedFormat = new String();
        // Findout the Highest value in the arraylist.
        double max1 = ((Double) Collections.max(totallist)).doubleValue();
        //sorting the arraylist into ascending order.
        Collections.sort(totallist);
        Collections.reverse(totallist);
        for (int i = 0; i < totallist.size(); i++) {
            if (((Double) totallist.get(i)).doubleValue() == 0) {
                break;
            } else {
                commaSeparatedFormat = commaSeparatedFormat + ((Double) totallist.get(i)).doubleValue();
                if (i < totallist.size() - 1) {
                    if (((Double) totallist.get(i + 1)).doubleValue() != 0) {
                        commaSeparatedFormat = commaSeparatedFormat + ", ";
                    }
                }
            }

        }


        //if highest value is 0 means no elements are there in the array.
        if (max1 == 0) {
            final1 = max1;
            temp.append("\n");
            totalDisabilityBean.setTolalformula(temp);
        } //if atleast one value is 90 or >90 then directly take that value as
        //final1.
        else if (max1 >= 100) {
            final1 = max1;
            temp.append("so 100 will be directly taken as maximum value.\n");
            temp.append("In this case no calculation is performed. Maximum value will be directly taken as 100.\n");
            totalDisabilityBean.setTolalformula(temp);
        } else {
            temp.append("The selected values are: ");
            temp.append(commaSeparatedFormat);
            temp.append("\n\n");
            final1 = calcResult(totallist);
        }
        return final1;
    }

    public double calcResult(ArrayList obj) {
        double max1 = 0;
        Object o[] = obj.toArray();
        double a[] = new double[o.length];
        for (int i = 0; i < o.length; i++) {
            a[i] = ((Double) o[i]).doubleValue();
        }
        if (a[1] == 0) {
            max1 = a[0];
            StringBuffer temp = new StringBuffer();
            temp.append("As it found only one value has selected. So no calculation is required,\n The selected value will be taken as total Disability . That is, " + max1 + "");
            totalDisabilityBean.setTolalformula(temp);
        } else if (a.length == 2) {
            max1 = internalCalculation(a[0], a[1]);
        } else {
            max1 = internalCalculation(a[0], a[1]);
            for (int i = 2; i < a.length; i++) {
                if (a[i] == 0) {
                    break;
                }
                max1 = internalCalculation(max1, a[i]);
            }
        }

        return max1;
    }

    public double internalCalculation(double a, double b) {
        //StringBuffer temp=getCalculationSideBuffer(side);
        StringBuffer temp = new StringBuffer();
        if (totalDisabilityBean.getTolalformula() != null) {
            temp.append(totalDisabilityBean.getTolalformula());
        }
        temp.append("a+((b*(90-a))/90) Greater value is 'a' (" + formatResult(a) + "," + formatResult(b) + ") \n = ");
        temp.append(formatResult(a) + " + ( ( " + formatResult(b) + " x ( 90 - " + formatResult(a) + " ) )/90 )");
        a = a + ((b * (90 - a)) / 90);
        temp.append("\n = " + formatResult(a) + "\n\n");
        totalDisabilityBean.setTolalformula(temp);
        return a;
    }

    public double formatResult(double val) {
        BigDecimal temp1 = new BigDecimal(val).setScale(2, BigDecimal.ROUND_HALF_UP);
        double temp2 = (new Double(temp1.doubleValue())).doubleValue();
        return temp2;
    }
}
