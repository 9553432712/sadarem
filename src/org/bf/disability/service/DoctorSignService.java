/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.service;


import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.dto.DoctorSignDTO;
import java.sql.SQLException;
import org.bf.disability.Exception.SADAREMDBException;
/**
 *
 * @author 484898
 */
public interface DoctorSignService {

    public ArrayList getPWDDetails(DataSource ds,DoctorSignDTO doctorSignDTO) throws SADAREMDBException,SQLException;

    public int signData(DataSource ds,DoctorSignDTO doctorSignDTO) throws SADAREMDBException,SQLException;

}
