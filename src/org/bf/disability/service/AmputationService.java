/*
 * AmputationService.java
 *
 * Created on June 30, 2008, 4:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.AmputationDto;

/**
 * This interface contains abstract mehtods, whose implementation deals with 
 * the insertion, modification and deletion of amputation details
 * @version 1.0
 */
public interface AmputationService {

    public void insertAmputationDetails(DataSource ds, AmputationDto ampdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertAmputationDetailsAU(DataSource ds, AmputationDto ampdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public AmputationDto getAmputationDetails(String personcode, DataSource ds) throws SADAREMDBException, SQLException;

    public void updateAmputationDetails(DataSource ds, AmputationDto amputationdto, HttpServletRequest rquest) throws SADAREMDBException, SQLException;

    public boolean checkPersoncode(String personcode, DataSource ds) throws SADAREMDBException, SQLException;

    public void deleteAmputaionUpdateRecord(DataSource ds, String personcode) throws SADAREMDBException, SQLException;
}
