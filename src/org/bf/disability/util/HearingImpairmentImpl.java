/*
 * HearingImpairmentImpl.java
 *
 * Created on January 2, 2009, 5:29 PM
 *
 */
package org.bf.disability.util;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.bean.HearingImpairmentBean;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.HearingImpairmentDao;
import org.bf.disability.dto.HearingImpairmentDto;
import org.bf.disability.serviceimpl.ShowCalcualationsServiceImpl;

/**
 *
 * @author Deviprasad_t
 */
public class HearingImpairmentImpl extends ShowCalcualationsServiceImpl {

    private double percent;
    private int betterear;
    private double betterearmonauralvalue;
    private int poorerear;
    private double poorerearmonauralvalue;
    private double percenttotal;
    private double monauralFormula;
    String dynamicformula;
    StringBuffer monauralvalue = new StringBuffer();
    StringBuffer binauralvalue = new StringBuffer();
    StringBuffer lessthen26 = new StringBuffer();
    StringBuffer dblevels = new StringBuffer();

    public void populateHICalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException {
        try {

            HearingImpairmentBean hearingImpairmentBean = new HearingImpairmentBean();
            HearingImpairmentDao hearingimpairmentdao = new HearingImpairmentDao();
            HearingImpairmentDto hearingdto = hearingimpairmentdao.getHearingDetails(dataSource, personcode);
            BeanUtils.copyProperties(hearingImpairmentBean, hearingdto);

            hearingImpairmentBean = HICalculationLogic(hearingImpairmentBean);

            request.setAttribute("hearingImpairmentBean", hearingImpairmentBean);
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource,sqlEx.toString(), "populateHICalculations", "HearingImpairmentImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentImpl", "populateHICalculations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource,sqlEx.toString(), "populateHICalculations", "HearingImpairmentImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentImpl", "populateHICalculations");
        } //end of catch block
    }

    public HearingImpairmentBean HICalculationLogic(HearingImpairmentBean hearingImpairmentBean)  {
        try {
            if (hearingImpairmentBean.getRighteardblevel() != "" && hearingImpairmentBean.getLefteardblevel() != "") {
                if ((Integer.parseInt(hearingImpairmentBean.getRighteardblevel())) < 26 || (Integer.parseInt(hearingImpairmentBean.getLefteardblevel()) < 26)) {
                    percent = 0.0;
                    lessthen26.append("Better Ear DBLevel is below 26 so the  HearingDisability is Zero");
                    lessthen26.append(" = 0");
                    hearingImpairmentBean.setBinauralvaluepoorerear(lessthen26);


                } else {
                    if ((Integer.parseInt(hearingImpairmentBean.getRighteardblevel())) < (Integer.parseInt(hearingImpairmentBean.getLefteardblevel()))) {


                        betterear = Integer.parseInt(hearingImpairmentBean.getRighteardblevel());
                        betterearmonauralvalue =
                                monauralFormula(betterear);
                        poorerear =
                                Integer.parseInt(hearingImpairmentBean.getLefteardblevel());
                        poorerearmonauralvalue =
                                monauralFormula(poorerear);
                        dynamicformula =
                                "(" + hearingImpairmentBean.getRighteardblevel() + "<" + hearingImpairmentBean.getLefteardblevel() + ")";

                        monauralvalue.append("( Lesser DbLevel is Better Ear )= ");
                        monauralvalue.append(dynamicformula);


                    } else {
                        betterear = Integer.parseInt(hearingImpairmentBean.getLefteardblevel());
                        betterearmonauralvalue =
                                monauralFormula(betterear);
                        poorerear =
                                Integer.parseInt(hearingImpairmentBean.getRighteardblevel());
                        poorerearmonauralvalue =
                                monauralFormula(poorerear);
                        dynamicformula =
                                "(" + hearingImpairmentBean.getRighteardblevel() + ">" + hearingImpairmentBean.getLefteardblevel() + ")";
                        monauralvalue.append("RightEar DBLevel,LeftEar DBLevel");
                        monauralvalue.append("( Lesser DbLevel is Better Ear )= ");
                        monauralvalue.append(dynamicformula);


                    }
                    if (betterear != 0) {
                        String staticformula = "BetterEar MonauralValue \n = ";
                        monauralvalue =
                                formulaforMonaural(betterear, betterearmonauralvalue, dynamicformula, staticformula);
                        hearingImpairmentBean.setMonauralvaluebetterear(monauralvalue);


                    }
                    if (poorerear != 0) {
                        String staticformula = "PoorerEar MonauralValue \n = ";
                        monauralvalue =
                                formulaforMonaural(poorerear, poorerearmonauralvalue, dynamicformula, staticformula);
                        hearingImpairmentBean.setMonauralvaluepoorerear(monauralvalue);


                    }
                    percent = ((betterearmonauralvalue * 5) + poorerearmonauralvalue) / 6;


                    if (percent != 0) {
                        binauralvalue.append("((betterearmonauralvalue*5)+poorerearmonauralvalue)/6 = ");
                        binauralvalue.append("((" + betterearmonauralvalue + "*5)+" + poorerearmonauralvalue + ")/6 = ");
                        binauralvalue.append(percent);
                        hearingImpairmentBean.setBinauralvaluepoorerear(binauralvalue);


                    }

                }
            } else {

                hearingImpairmentBean.setRighteardblevel("0");
                hearingImpairmentBean.setLefteardblevel("0");


            }
            if (percent > 100) {
                percenttotal = 100;


            } else if (percent <= 100) {
                percenttotal = percent;


            }
            hearingImpairmentBean.setTotalpercent(percenttotal);


        } //end of try block
        catch (Exception sqlEx) {
            sqlEx.printStackTrace();

        } //end of catch block
        return hearingImpairmentBean;


    }

    public double monauralFormula(int a) {
        monauralFormula = (a - 25) * 1.5;


        return monauralFormula;


    }

    public StringBuffer formulaforMonaural(double a, double b, String dynamicformula, String staticformula) {
        monauralvalue = new StringBuffer();
        monauralvalue.append("Lesser DBLevel is BetterEar ");
        monauralvalue.append(dynamicformula);
        monauralvalue.append("\n = ");
        monauralvalue.append(staticformula);
        monauralvalue.append("(" + a + "-25)*1.5 = ");
        monauralvalue.append(b);


        return monauralvalue;

    }
}
