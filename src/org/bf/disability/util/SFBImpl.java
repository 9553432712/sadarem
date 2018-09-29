/*
 * SFBImpl.java
 *
 * Created on January 5, 2009, 11:57 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.util;

import java.math.BigDecimal;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.bean.SFBBean;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.MentalRetardationDAO;
import org.bf.disability.dao.ShowCalculationsDAO;
import org.bf.disability.dto.MentalRetardationTestsDTO;
import org.bf.disability.serviceimpl.ShowCalcualationsServiceImpl;

/**
 *
 * @author kiran_h
 */
public class SFBImpl extends ShowCalcualationsServiceImpl {

    SFBBean sFBBean = new SFBBean();
    ChronologicalAge cageobj = new ChronologicalAge();
    ShowCalculationsDAO showCalculationsDAO = new ShowCalculationsDAO();

    public void populateSFBCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException {
        try {
            MentalRetardationTestsDTO mentalRetardationTestsDTO = new MentalRetardationTestsDTO();
            MentalRetardationDAO mentalRetardationDAO = new MentalRetardationDAO();
            HttpSession session = request.getSession();
//     double ca=((Double)session.getAttribute("chronologicalage")).doubleValue();
            cageobj = showCalculationsDAO.getChronologicalAge(dataSource, personcode);



            mentalRetardationTestsDTO = mentalRetardationDAO.getMRseguintestDetails(dataSource, personcode);

            BeanUtils.copyProperties(sFBBean, mentalRetardationTestsDTO);
            sFBBean.setCa(Double.parseDouble(cageobj.getChronologicalage()));
            sFBBean = sfbCalculationsLogic(sFBBean);
            sFBBean.setDateofbirth(cageobj.getDateofbirth());
            sFBBean.setTodaysdate(cageobj.getTodaysdate());

            request.setAttribute("sFBBean", sFBBean);
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource, sqlEx.toString(), "populateSFBCalculations", "SFBImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "SFBImpl", "populateSFBCalculations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource, sqlEx.toString(), "populateSFBCalculations", "SFBImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "SFBImpl", "populateSFBCalculations");
        }//end of catch block

    }

    public SFBBean sfbCalculationsLogic(SFBBean sFBBean) throws SADAREMDBException, SQLException {
        try {
            String ageyears = sFBBean.getSfbyear();
            String agemonths = sFBBean.getSfbmonth();

            double sfbiq = 0.0;
            double iqrange = 0;
            double caage = 0.0;


//      double ageyears= Double.parseDouble(sFBBean.getAgeyears());
//        double agemonths= Double.parseDouble(sFBBean.getAgemonths());

            if (sFBBean.getSfbtrialone() != null || sFBBean.getSfbtrialtwo() != null || sFBBean.getSfbtrialthree() != null) {

                sFBBean.setFlag("true");
            }

            double mentalage = convertToYears(ageyears, agemonths);
            caage = sFBBean.getCa();

            if (caage < 15) {
                iqrange = calculateIQ(mentalage, caage);
            } else {
                sFBBean.setChronologicalageflag(true);
                caage = 15;
                iqrange = calculateIQ(mentalage, caage);
            }
            iqrange = calculateIQ(mentalage, caage);



        } //end of try block
        catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }//end of catch block
        return sFBBean;
    }

    public double convertToYears(String ageinyears, String ageinmonths) {
        StringBuffer mentalagebuffer = new StringBuffer();
        mentalagebuffer.append(Double.parseDouble(ageinyears) + "+" + (Double.parseDouble(ageinmonths)) + "/" + 12);
        double years = Double.parseDouble(ageinyears) + (Double.parseDouble(ageinmonths) / 12);
        mentalagebuffer.append("=" + formatResult(years));
        sFBBean.setMentalagebuffer(mentalagebuffer);
        return years;
    }

    public double calculateIQ(double ma, double ca) {
        StringBuffer iqbuffer = new StringBuffer();
        iqbuffer.append(" =  (" + formatResult(ma) + "/" + ca + ")X100");
        double iq = (ma / ca) * 100;
        iqbuffer.append("=" + formatResult(iq));
        sFBBean.setIqbuffer(iqbuffer);
        return iq;
    }

    public double formatResult(double val) {
        BigDecimal temp1 = new BigDecimal(val).setScale(2, BigDecimal.ROUND_HALF_UP);
        double temp2 = (new Double(temp1.doubleValue())).doubleValue();
        return temp2;
    }
}
