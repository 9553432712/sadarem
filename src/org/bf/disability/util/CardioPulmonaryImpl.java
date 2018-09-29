/*
 * CardioPulmonaryImpl.java
 *
 * Created on January 2, 2009, 5:15 PM
 *
 */
package org.bf.disability.util;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.bean.CardioPulmonaryBean;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.VisualImapairmentDAO;
import org.bf.disability.serviceimpl.ShowCalcualationsServiceImpl;

/**
 *
 * @author Deviprasad_t
 */
public class CardioPulmonaryImpl extends ShowCalcualationsServiceImpl {

    public void populateCardioPulmonaryCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException {
        CardioPulmonaryBean cardioPulmonaryBean = new CardioPulmonaryBean();
        try {

            //CardioPulmonaryDTO cardioPulmonaryDTO =new CardioPulmonaryDTO ();
            VisualImapairmentDAO dao = new VisualImapairmentDAO();
            float cardiopulmonary = dao.getCardioPulmonaryDetails(dataSource, personcode);

            cardioPulmonaryBean.setCardiopulmonary(String.valueOf(cardiopulmonary));
            // visualImpairmentBean=VICalculationLogic(visualImpairmentBean);
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource,sqlEx.toString(), "populateCardioPulmonaryCalculations", "CardioPulmonaryImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CardioPulmonaryImpl", "populateCardioPulmonaryCalculations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource,sqlEx.toString(), "populateCardioPulmonaryCalculations", "CardioPulmonaryImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CardioPulmonaryImpl", "populateCardioPulmonaryCalculations");
        } //end of catch block
        request.setAttribute("cardioPulmonaryBean", cardioPulmonaryBean);
    }

    public CardioPulmonaryBean cardioPulmonaryCalculationsLogic(CardioPulmonaryBean cardioPulmonaryBean) {
        return cardioPulmonaryBean;
    }
}
