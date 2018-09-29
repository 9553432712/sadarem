/*
 * TrunkService.java
 *
 * Created on July 9, 2008, 12:40 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.TrunkDTO;

/**
 * This interface has abstract methods, whose implementation is to perform data
 * manipulation operations like insert, update, select and delete on Trunk
 * @version 1.0
 * @author Bapinaidu
 */
public interface TrunkService {

    /** Creates a new instance of TrunkService */
    public int insertTrunkDetails(DataSource datasource, TrunkDTO trunkdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertTrunkDetailsAU(DataSource datasource, TrunkDTO trunkdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public TrunkDTO getTrunkDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public int updateTrunkDetails(DataSource datasource, TrunkDTO trunkdto, HttpServletRequest request) throws SADAREMDBException, SQLException;
}
