/*
 * VsmsScreeningTestService.java
 *
 * Created on October 16, 2008, 1:34 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.VsmsScreeningTestDTO;

/**
 *
 * @author Sunima.dila
 */
public interface VsmsScreeningTestService {

    /**
     * Creates a new instance of VsmsScreeningTestService
     */
    public int insertVsmsScreeningTest(DataSource ds, VsmsScreeningTestDTO vsmsscreeningtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertVsmsScreeningTestAU(DataSource ds, VsmsScreeningTestDTO vsmsscreeningtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public VsmsScreeningTestDTO getVsmsScreeningTest(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public void updateVsmsScreeningTest(DataSource ds, VsmsScreeningTestDTO vsmsscreeningtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public boolean checkPersonCode(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getMentalItems(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;
}

