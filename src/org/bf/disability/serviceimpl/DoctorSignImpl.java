/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.DoctorSignDAO;
import org.bf.disability.dto.DoctorSignDTO;
import org.bf.disability.service.DoctorSignService;

/**
 *
 * @author 484898
 */
public class DoctorSignImpl implements DoctorSignService {

    /**
     * This method is for getting the all the details of PWD's when page loads for signing
     * @param ds
     * @param doctorSignDTO
     * @return ArrayList
     * @throws SADAREMDBException
     */
    public ArrayList getPWDDetails(DataSource ds, DoctorSignDTO doctorSignDTO) throws SADAREMDBException, SQLException {
        ArrayList pwdDetails = new ArrayList();
        DoctorSignDAO doctorSignDAO = new DoctorSignDAO();
        pwdDetails = doctorSignDAO.getPWDDetails(ds, doctorSignDTO);
        return pwdDetails;
    }

    /**
     * This method is for sign the selected data
     * @param ds
     * @param doctorSignDTO
     * @return int
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public int signData(DataSource ds, DoctorSignDTO doctorSignDTO) throws SADAREMDBException, SQLException {
        int status = 0;
        DoctorSignDAO doctorSignDAO = new DoctorSignDAO();
        status = doctorSignDAO.signData(ds, doctorSignDTO);
        return status;
    }
}
