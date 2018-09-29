/*
 * UpperExtrimityService.java
 *
 * Created on June 11, 2008, 10:05 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;

import org.bf.disability.dto.UpperExtrimityDto;

/**
 * This interface has abstract methods, whose implementation is to perform data
 * manipulation operations like insert, update, select and delete on Upper Extremity
 * @version 1.0
 * 
 */
public interface UpperExtrimityService {

    public boolean checkPersoncode(String personcode,
            DataSource ds)
            throws SADAREMDBException, SQLException;

    public int inserUpperExtremityData(DataSource ds,
            UpperExtrimityDto upperextrimitydto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int updateRomData(DataSource ds,
            UpperExtrimityDto upperextrimitydto, HttpServletRequest request)
            throws SADAREMDBException, SQLException;

    public UpperExtrimityDto selectUpperExterimityData(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException;

    public void deleteUpperExtremityUpdateRecord(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException;

    public int inserUpperExtremityDataAU(DataSource ds,
            UpperExtrimityDto upperextrimitydto, HttpServletRequest request) throws SADAREMDBException, SQLException;
}
