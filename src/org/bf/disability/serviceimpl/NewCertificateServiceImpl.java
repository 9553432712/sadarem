/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.NewCertificateDAO;
import org.bf.disability.dto.NewCertificateDTO;

/**
 *
 * @author 310926
 */
public class NewCertificateServiceImpl {

    NewCertificateDAO newCertificateDAO = new NewCertificateDAO();

    public String insertPersonalDetailsForRationCard(NewCertificateDTO NewCertificateDTO, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException {
        String insertpersonaldetails = null;
        try {
            insertpersonaldetails = newCertificateDAO.insertPersonalDetailsForRationCard(NewCertificateDTO, ds, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertPersonalDetailsForRationCard", "NewCertificateServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewCertificateServiceImpl", "insertPersonalDetailsForRationCard");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertPersonalDetailsForRationCard", "NewCertificateServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewCertificateServiceImpl", "insertPersonalDetailsForRationCard");
        }
        return insertpersonaldetails;
    }

    public String getPartACheckForDuplicate(DataSource ds, NewCertificateDTO NewCertificateDTO) throws SADAREMDBException, SQLException {
        String getPartACheckForDuplicate = null;
        try {
            getPartACheckForDuplicate = newCertificateDAO.getPartACheckForDuplicate(ds, NewCertificateDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPartACheckForDuplicate", "NewCertificateServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewCertificateServiceImpl", "getPartACheckForDuplicate");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPartACheckForDuplicate", "NewCertificateServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewCertificateServiceImpl", "getPartACheckForDuplicate");
        }
        return getPartACheckForDuplicate;
    }
}
