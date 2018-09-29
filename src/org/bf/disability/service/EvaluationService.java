package org.bf.disability.service;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.bf.disability.dto.EvaluationDTO;
import javax.servlet.http.HttpServletRequest;
import org.bf.disability.Exception.SADAREMDBException;

/**
 * 
 * @author Sunima
 */
public interface EvaluationService {

    /**
     * Creates a new instance of EvaluationService
     */
    public int insertData(DataSource ds, EvaluationDTO evaluationdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertDataAU(DataSource ds, EvaluationDTO evaluationdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public EvaluationDTO getEvaluationDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public void updateDetails(DataSource datasource, EvaluationDTO evaluationdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public boolean checkPersoncode(String personcode, DataSource ds) throws SADAREMDBException, SQLException;
}
