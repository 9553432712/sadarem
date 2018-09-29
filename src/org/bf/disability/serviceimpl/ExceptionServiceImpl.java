package org.bf.disability.serviceimpl;

import org.bf.disability.service.ExpectionService;
import java.sql.SQLException;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import javax.sql.DataSource;
public class ExceptionServiceImpl implements ExpectionService {

    ExceptionDAO exceptionDAO = new ExceptionDAO();

    public int saveException(DataSource ds,String exp, String method, String DAO,String type) throws SADAREMDBException,SQLException {
        int exception = 0;
        try {

            exception = exceptionDAO.saveException(ds,exp, method, DAO,type);
        }catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "saveException", "ExceptionServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ExceptionServiceImpl", "saveException");
        }
        return exception;
    }
}
