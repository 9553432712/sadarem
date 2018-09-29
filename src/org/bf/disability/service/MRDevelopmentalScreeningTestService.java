/*
 * MRDevelopmentalScreeningTestService.java
 *
 * Created on October 16, 2008, 4:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.MRDevelopmentalScreeningTestDAO;
import org.bf.disability.dto.MRDevelopmentalScreeningTestDTO;

/**
 * This interface has abstract methods, whose implementation is to perform data
 * manipulation operations like insert, update, select and delete on MR 
 * Developmental Screening Test
 * @version 1.0
 */
public class MRDevelopmentalScreeningTestService {

    MRDevelopmentalScreeningTestDAO mrDevelopmentalScreeningTestDAO = new MRDevelopmentalScreeningTestDAO();
    MRDevelopmentalScreeningTestDTO mrDevelopmentalScreeningTestDTO = new MRDevelopmentalScreeningTestDTO();

    /** Creates a new instance of MRDevelopmentalScreeningTestService */
    public void insertMRDevelopmentalScreeningTestDetails(DataSource datasource, MRDevelopmentalScreeningTestDTO mrDevelopmentalScreeningTestDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {
        mrDevelopmentalScreeningTestDAO.insertMRDevelopmentalScreeningTestDetails(datasource, mrDevelopmentalScreeningTestDTO, request);

    }

    public int insertMRDevelopmentalScreeningTestDetailsAU(DataSource datasource, MRDevelopmentalScreeningTestDTO mrDevelopmentalScreeningTestDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i = mrDevelopmentalScreeningTestDAO.insertMRDevelopmentalScreeningTestDetailsAU(datasource, mrDevelopmentalScreeningTestDTO, request);
        return i;
    }

    public MRDevelopmentalScreeningTestDTO selectMRDevelopmentalScreeningTestDetails(DataSource datasource, MRDevelopmentalScreeningTestDTO mrDevelopmentalScreeningTestDTO) throws SADAREMDBException, SQLException {
        mrDevelopmentalScreeningTestDTO = mrDevelopmentalScreeningTestDAO.selectMRDevelopmentalScreeningTestDetails(datasource, mrDevelopmentalScreeningTestDTO);
        return mrDevelopmentalScreeningTestDTO;
    }

    public void updateMRDevelopmentalScreeningTestDetails(DataSource datasource, MRDevelopmentalScreeningTestDTO mrDevelopmentalScreeningTestDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {
        mrDevelopmentalScreeningTestDAO.updateMRDevelopmentalScreeningTestDetails(datasource, mrDevelopmentalScreeningTestDTO, request);
    }

    public boolean checkForUser(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        boolean result = mrDevelopmentalScreeningTestDAO.checkForUser(datasource, personcode);
        return result;
    }

    public void deleteMRDevelopmentalScreeningTestDetails(DataSource datasource, MRDevelopmentalScreeningTestDTO mrDevelopmentalScreeningTestDTO) throws SADAREMDBException, SQLException {
        mrDevelopmentalScreeningTestDAO.deleteMRDevelopmentalScreeningTestDetails(datasource, mrDevelopmentalScreeningTestDTO);
    }
}
