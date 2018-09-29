/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.DiagnosisDisabilityUpdateDAO;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.service.DiagnosisDisabilityUpdateService;

/**
 *
 * @author 693461
 */
public class DiagnosisDisabilityUpdateServiceImpl implements DiagnosisDisabilityUpdateService {

    public String getDiagnosisDisabilityDetails(DataSource ds, String personCode) throws SADAREMDBException, SQLException {

        String diagnosisDisabilityDetails = null;
        DiagnosisDisabilityUpdateDAO diagnosisDisabilityUpdateDAO = new DiagnosisDisabilityUpdateDAO();

        try {
            diagnosisDisabilityDetails = diagnosisDisabilityUpdateDAO.getDiagnosisDisabilityDetails(ds, personCode);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDiagnosisDisabilityDetails", "DiagnosisDisabilityUpdateServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DiagnosisDisabilityUpdateServiceImpl", "getDiagnosisDisabilityDetails");
        }

        return diagnosisDisabilityDetails;

    }

    public int updateDiagnosisDisabilityDetails(DataSource ds, String diagnosisDetails, String personcode) throws SADAREMDBException, SQLException {

        int updateDiagnosisDisabilityDetails = 0;
        DiagnosisDisabilityUpdateDAO diagnosisDisabilityUpdateDAO = new DiagnosisDisabilityUpdateDAO();


        try {
            updateDiagnosisDisabilityDetails = diagnosisDisabilityUpdateDAO.updateDiagnosisDisabilityDetails(ds, diagnosisDetails, personcode);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateDiagnosisDisabilityDetails", "DiagnosisDisabilityUpdateServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DiagnosisDisabilityUpdateServiceImpl", "updateDiagnosisDisabilityDetails");
        }

        return updateDiagnosisDisabilityDetails;


    }
}
