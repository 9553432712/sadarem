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
import org.bf.disability.dao.SearchRequestStatusDAO;
import org.bf.disability.dto.SearchRequestStatusDTO;
import org.bf.disability.service.SearchRequestStatusService;

/**
 *
 * @author 310926
 */
public class SearchRequestStatusServiceImpl implements SearchRequestStatusService {

    public ArrayList getDetails(DataSource ds, SearchRequestStatusDTO searchRequestStatusDTO)
            throws SADAREMDBException, SQLException {
        ArrayList getdetails = new ArrayList();
        SearchRequestStatusDAO searchRequestStatusDAO = new SearchRequestStatusDAO();
        try {
            getdetails = searchRequestStatusDAO.getDetails(ds, searchRequestStatusDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDetails", "SearchRequestStatusServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "SearchRequestStatusServiceImpl", "getDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDetails", "SearchRequestStatusServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "SearchRequestStatusServiceImpl", "getDetails");
        }
        return getdetails;

    }
}
