package org.bf.disability.service;

import java.sql.SQLException;
import javax.sql.*;
import javax.servlet.http.*;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.PartCUpdateDTO;

public interface PartCService {

    public int insertPartCService(DataSource datasource, PartCUpdateDTO partcdto, String personcode, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertPartCServiceAU(DataSource datasource, PartCUpdateDTO partcdto, String personcode, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertReassesment(DataSource datasource, PartCUpdateDTO partcdto, String personcode, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public PartCUpdateDTO getLocomotorneeds(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public String eligibiltyForRailwayCertificate(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public int updatePartc(DataSource datasource, PartCUpdateDTO partcdto, HttpServletRequest request) throws SADAREMDBException, SQLException;
}
