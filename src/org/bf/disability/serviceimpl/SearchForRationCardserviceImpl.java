/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.SearchForRationCardDAO;
import org.bf.disability.form.SearchForRationCardForm;
import org.bf.disability.service.SearchForRationCardservice;

/**
 *
 * @author 525483
 */
public class SearchForRationCardserviceImpl implements SearchForRationCardservice {

    public ArrayList getRationCardDetails(SearchForRationCardForm searchForRationCardForm, DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList getDetails = new ArrayList();
        SearchForRationCardDAO searchRationCardDao = new SearchForRationCardDAO();
        try {
            getDetails = searchRationCardDao.getRationCardDetails(searchForRationCardForm, ds);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getRationCardDetails", "SearchForRationCardserviceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "SearchForRationCardserviceImpl", "getRationCardDetails");
        }

        return getDetails;

    }
}
