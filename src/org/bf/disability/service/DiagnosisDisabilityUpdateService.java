/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.service;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;

/**
 *
 * @author 693461
 */
public interface  DiagnosisDisabilityUpdateService {

    public String getDiagnosisDisabilityDetails(DataSource ds,String personCode) throws SADAREMDBException,SQLException;

    public int updateDiagnosisDisabilityDetails(DataSource ds,String diagnosisDetails,String personcode) throws SADAREMDBException,SQLException;
}
