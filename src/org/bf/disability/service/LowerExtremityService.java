/*
 * LowerExtremityService.java
 *
 * Created on June 25, 2008, 12:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.LowerExtremityDTO;

/**
 * This interface has abstract methods, whose implementation is to perform data
 * manipulation operations like insert, update, select and delete on Lower Extremity
 * @version 1.0
 * @author Bapinaidu T
 */
public interface LowerExtremityService {

    /**
     * Creates a new instance of LowerExtremityService
     */
    public int insertLowerExtremityDetails(DataSource datasource, LowerExtremityDTO lowerextremitydto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public LowerExtremityDTO getLowerExtremityDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public int updateLowerExtremityDetails(DataSource datasource, LowerExtremityDTO lowerextremitydto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertLowerExtremityDetailsAU(DataSource datasource, LowerExtremityDTO lowerextremitydto, HttpServletRequest request) throws SADAREMDBException, SQLException;
}
