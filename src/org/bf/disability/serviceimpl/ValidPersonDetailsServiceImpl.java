/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.ValidPersonDetailsDAO;
import org.bf.disability.form.ValidPersonDetailsForm;
import org.bf.disability.service.ValidPersonDetailsService;

/**
 *
 * @author 728056
 */
public class ValidPersonDetailsServiceImpl implements ValidPersonDetailsService {

    public boolean checkDetails(DataSource ds, ValidPersonDetailsForm detailsForm) throws SADAREMDBException, SQLException {
        boolean person = false;
        ValidPersonDetailsDAO detailsDAO = new ValidPersonDetailsDAO();
        try {
            person = detailsDAO.checkDetails(ds, detailsForm);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkDetails", "ValidPersonDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ValidPersonDetailsServiceImpl", "checkDetails");
        }
        return person;
    }

    public int updateDetails(DataSource ds, ValidPersonDetailsForm detailsForm) throws SADAREMDBException, SQLException {

        int update = 0;
        ValidPersonDetailsDAO detailsDAO = new ValidPersonDetailsDAO();
        try {
            update = detailsDAO.updateDetails(ds, detailsForm);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateDetails", "ValidPersonDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ValidPersonDetailsServiceImpl", "updateDetails");
        }
        return update;
    }
}
