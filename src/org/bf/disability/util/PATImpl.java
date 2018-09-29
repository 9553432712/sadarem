/*
 * PATImpl.java
 *
 * Created on January 5, 2009, 11:58 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.util;

import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.bean.PATBean;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.ShowCalculationsDAO;
import org.bf.disability.dto.MentalRetardationTestsDTO;
import org.bf.disability.service.MentalRetardationService;
import org.bf.disability.servicefactory.MentalRetardationServiceFactory;
import org.bf.disability.serviceimpl.ShowCalcualationsServiceImpl;

/**
 *
 * @author kiran_h
 */
public class PATImpl extends ShowCalcualationsServiceImpl {

    PATBean pATBean = new PATBean();
    HttpSession session = null;
    double ca = 0.0;
    ChronologicalAge cageobj = new ChronologicalAge();

    public void populatePATCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException {
        try {
            ShowCalculationsDAO showCalculationsDAO = new ShowCalculationsDAO();
            cageobj = showCalculationsDAO.getChronologicalAge(dataSource, personcode);


//  session=request.getSession(true);   
//  ca=((Double)session.getAttribute("chronologicalage")).doubleValue();
            MentalRetardationService mentalRetardationService = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
            MentalRetardationTestsDTO mentalRetardationTestsDTO = mentalRetardationService.getMRAlexanderTestDetails(dataSource, personcode);
            BeanUtils.copyProperties(pATBean, mentalRetardationTestsDTO);
            pATBean.setChronologicalage(Double.parseDouble(cageobj.getChronologicalage()));
            pATBean.setDobstring(cageobj.getDateofbirth());
            pATBean.setTodaystring(cageobj.getTodaysdate());

            pATBean = patCalculationsLogic(pATBean);
            request.setAttribute("pATBean", pATBean);
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource, sqlEx.toString(), "populatePATCalculations", "PATImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PATImpl", "populatePATCalculations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource, sqlEx.toString(), "populatePATCalculations", "PATImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PATImpl", "populatePATCalculations");
        }//end of catch block
    }

    public PATBean patCalculationsLogic(PATBean pATBean) throws SADAREMDBException, SQLException {
        try {
            double patIQ = 0;
            int pat_Second_One = Integer.parseInt(pATBean.getPat_Second_One());
            int pat_Second_Two = Integer.parseInt(pATBean.getPat_Second_Two());
            int pat_Second_Three = Integer.parseInt(pATBean.getPat_Second_Three());
            int pat_Second_Four = Integer.parseInt(pATBean.getPat_Second_Four());
            int pat_Second_Five = Integer.parseInt(pATBean.getPat_Second_Five());
            int pat_Second_Six = Integer.parseInt(pATBean.getPat_Second_Six());
            int pat_Second_Seven = Integer.parseInt(pATBean.getPat_Second_Seven());
            int pat_Second_Eight = Integer.parseInt(pATBean.getPat_Second_Eight());
            int pat_Second_Nine = Integer.parseInt(pATBean.getPat_Second_Nine());
            int pat_SA_One = Integer.parseInt(pATBean.getPat_SA_One());
            int pat_SA_Two = Integer.parseInt(pATBean.getPat_SA_Two());
            int pat_SA_Three = Integer.parseInt(pATBean.getPat_SA_Three());
            int pat_SA_Four = Integer.parseInt(pATBean.getPat_SA_Four());
            int pat_SA_Five = Integer.parseInt(pATBean.getPat_SA_Five());
            int pat_SA_Six = Integer.parseInt(pATBean.getPat_SA_Six());
            int pat_SA_Seven = Integer.parseInt(pATBean.getPat_SA_Seven());
            int pat_SA_Eight = Integer.parseInt(pATBean.getPat_SA_Eight());
            int pat_SA_Nine = Integer.parseInt(pATBean.getPat_SA_Nine());
            String pat_Remarks_One = (pATBean.getPat_Remarks_One());
            String pat_Remarks_Two = (pATBean.getPat_Remarks_Two());
            String pat_Remarks_Three = (pATBean.getPat_Remarks_Three());
            String pat_Remarks_Four = (pATBean.getPat_Remarks_Four());
            String pat_Remarks_Five = (pATBean.getPat_Remarks_Five());
            String pat_Remarks_Six = (pATBean.getPat_Remarks_Six());
            String pat_Remarks_Seven = (pATBean.getPat_Remarks_Seven());
            String pat_Remarks_Eight = (pATBean.getPat_Remarks_Eight());
            String pat_Remarks_Nine = (pATBean.getPat_Remarks_Nine());
            checkIfExist(pat_Second_One, pat_SA_One, pat_Remarks_One, 1);
            checkIfExist(pat_Second_Two, pat_SA_Two, pat_Remarks_Two, 2);
            checkIfExist(pat_Second_Three, pat_SA_Three, pat_Remarks_Three, 3);
            checkIfExist(pat_Second_Four, pat_SA_Four, pat_Remarks_Four, 4);
            checkIfExist(pat_Second_Five, pat_SA_Five, pat_Remarks_Five, 5);
            checkIfExist(pat_Second_Six, pat_SA_Six, pat_Remarks_Six, 6);
            checkIfExist(pat_Second_Seven, pat_SA_Seven, pat_Remarks_Seven, 7);
            checkIfExist(pat_Second_Eight, pat_SA_Eight, pat_Remarks_Eight, 8);
            checkIfExist(pat_Second_Nine, pat_SA_Nine, pat_Remarks_Nine, 9);
            ca = pATBean.getChronologicalage();
            if (ca < 16) {
                patIQ = calculateIQ(convertToYears(pATBean.getPat_Year(), pATBean.getPat_Month()), ca);
            } else {
                ca = 16;
                pATBean.setChronologicalageflag(true);
                patIQ = calculateIQ(convertToYears(pATBean.getPat_Year(), pATBean.getPat_Month()), ca);
            }
            pATBean.setPat_IQ(patIQ);

        } //end of try block
        catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }//end of catch block
        return pATBean;
    }

    public double convertToYears(String ageinyears, String ageinmonths) {
        StringBuffer mentalagebuffer = new StringBuffer();
        mentalagebuffer.append(Double.parseDouble(ageinyears) + "+" + "(" + Double.parseDouble(ageinmonths) + "/12)");
        double years = Double.parseDouble(ageinyears) + (Double.parseDouble(ageinmonths) / 12);
        mentalagebuffer.append("=" + years);
        pATBean.setMentalagebuffer(mentalagebuffer);
        return years;
    }

    public double calculateIQ(double ma, double ca) {
        StringBuffer iqbuffer = new StringBuffer();
        iqbuffer.append("(" + ma + "/" + ca + ")X100");
        double iq = (ma / ca) * 100;
        iqbuffer.append("=" + iq);
        pATBean.setIqbuffer(iqbuffer);
        return iq;
    }

    public void checkIfExist(int a, int b, String c, int i) {
        if (a != 0 || b != 0 || !(c.equals("0"))) {
            pATBean.setPat_total_flag(true);
            switch (i) {
                case 1:
                    pATBean.setPat_one_flag(true);

                    break;
                case 2:

                    pATBean.setPat_two_flag(true);
                    break;
                case 3:

                    pATBean.setPat_three_flag(true);
                    break;
                case 4:

                    pATBean.setPat_four_flag(true);
                    break;
                case 5:

                    pATBean.setPat_five_flag(true);
                    break;
                case 6:

                    pATBean.setPat_six_flag(true);
                    break;
                case 7:


                    pATBean.setPat_seven_flag(true);
                    break;
                case 8:

                    pATBean.setPat_eight_flag(true);
                    break;
                case 9:

                    pATBean.setPat_nine_flag(true);
                    break;
            }
        }
    }
}
