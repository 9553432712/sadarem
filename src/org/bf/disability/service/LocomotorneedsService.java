/*
 * LocomotorneedsService.java
 *

 */
package org.bf.disability.service;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.LocomotorneedsDTO;

/**
 * This interface has abstract methods, whose implementation is to perform data
 * manipulation operations like insert, update, select and delete on Locomotor Needs
 * @version 1.0
 */
public interface LocomotorneedsService {

    /** Creates a new instance of LocomotorneedsService */
    public int insertLocomotorneeds(DataSource datasource, LocomotorneedsDTO locomotorneedsdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public LocomotorneedsDTO getLocomotorneeds(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public int updateLocomotorneeds(DataSource datasource, LocomotorneedsDTO locomotorneedsdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertLocomotorneedsAU(DataSource datasource, LocomotorneedsDTO locomotorneedsdto, HttpServletRequest request) throws SADAREMDBException, SQLException;
}
