/*
 * MentalRetardationImpl.java
 *
 * Created on January 2, 2009, 5:32 PM
 *
 */
package org.bf.disability.util;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.bean.MentalRetardationBean;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dto.MentalRetardationDTO;
import org.bf.disability.service.MentalRetardationService;
import org.bf.disability.servicefactory.MentalRetardationServiceFactory;
import org.bf.disability.serviceimpl.ShowCalcualationsServiceImpl;

/**
 *
 * @author Deviprasad_t
 */
public class MentalRetardationImpl extends ShowCalcualationsServiceImpl {

    MentalRetardationBean mentalRetardationBean = new MentalRetardationBean();

    public void populateMRCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException {
        try {
            MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
            MentalRetardationDTO modifydto = new MentalRetardationDTO();
            modifydto = mentalretarservice.getMentalDetails(dataSource, personcode);
            BeanUtils.copyProperties(mentalRetardationBean, modifydto);
            mentalRetardationBean = mrCalculationsLogic(mentalRetardationBean);
            request.setAttribute("mentalRetardationBean", mentalRetardationBean);
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource, sqlEx.toString(), "populateMRCalculations", "MentalRetardationImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationImpl", "populateMRCalculations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource, sqlEx.toString(), "populateMRCalculations", "MentalRetardationImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationImpl", "populateMRCalculations");
        }//end of catch block
    }

    public MentalRetardationBean mrCalculationsLogic(MentalRetardationBean mentalRetardationBean) throws SADAREMDBException, SQLException {
        try {
            double percent;

            double range = Double.parseDouble(mentalRetardationBean.getIq());

            String test = mentalRetardationBean.getChronologicalage().trim();
            if (test.equalsIgnoreCase("D.S.T")) {
                test = "D.S.T";
            }
            if (test.equalsIgnoreCase("V.S.M.S")) {
                test = "V.S.M.S";
            }
            if (test.equalsIgnoreCase("B.K.T")) {
                test = "B.K.T";
            }
            if (test.equalsIgnoreCase("S.F.B")) {
                test = "S.F.B";
            }
            if (test.equalsIgnoreCase("P.A.T")) {
                test = "P.A.T";
            }
            if (test.equalsIgnoreCase("B.B.I.T")) {
                test = "B.B.I.T";
            }
            if (test.equalsIgnoreCase("M.I.S.I.C")) {
                test = "M.I.S.I.C";
            }
            mentalRetardationBean.setTest(test);

            if (range >= 70 && range < 80) {
                percent = 25.0;
            } else if (range >= 50 && range < 70) {
                percent = 50.0;
            } else if (range >= 35 && range < 50) {
                percent = 75.0;
            } else if (range >= 20 && range < 35) {
                percent = 90.0;
            } else if (range < 20 && range != 0) {
                percent = 100.0;
            } else {
                percent = 0.0;
            }
            mentalRetardationBean.setTotalpercent(percent);
        } //end of try block
        catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }//end of catch block
        return mentalRetardationBean;

    }//end of cal logic
}/* end of class  */
