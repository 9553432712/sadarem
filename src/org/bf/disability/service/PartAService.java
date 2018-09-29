/*
 * addPartAService.java
 *
 * Created on June 16, 2008, 2:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.form.PartAForm;

/**
 * This interface has abstract methods, whose implementation is to perform data
 * manipulation operations like insert, update, select and delete for Part A
 * @version 1.0
 */
public interface PartAService {

    public String insertPersonalDetails(PartADTO partadto, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public PartADTO getPersonalDetails(String personcode, String personstatus, DataSource ds, int campId) throws SADAREMDBException, SQLException;

    public PartADTO getDesabilityDetails(String personcode, String personstatus, DataSource ds, int campId) throws SADAREMDBException, SQLException;

    public void updatePersonalDetails(String personcode, PartADTO partadto, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public void insertDisabilityDetails(String personcode, PartADTO partADTO, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertDisabilityDetailsAU(String personcode, PartADTO partADTO, String personStatus, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public PartADTO getExistingPensionDetails(String pensionCode, String districtId, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public ArrayList getMandalsList(DataSource datasource, String districtid) throws SADAREMDBException, SQLException;

    public ArrayList getVillagesList(DataSource datasource, String districtid, String mandalid) throws SADAREMDBException, SQLException;

    public ArrayList getHabitationsList(DataSource datasource, String districtid, String mandalid, String villageid) throws SADAREMDBException, SQLException;

    public PartADTO getEmployeeDetails(String employeeId, String districtId, DataSource ds) throws SADAREMDBException, SQLException;

    public String insertPersonalDetailsForRationCard(PartADTO partadto, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int checkPensionCardNumber(DataSource ds, String pensionId, String districtId) throws SADAREMDBException, SQLException;

    public String getPersonCode(DataSource ds, String pensionId) throws SADAREMDBException, SQLException;

    public String insertSerpEmployeesPersonalDetails(PartADTO partadto, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public ArrayList getAllDataBasedonRationCard(DataSource ds, String pensionId, String distid, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public ArrayList checkSADAREMAssessmentData(DataSource ds, String aadharCardNo) throws SADAREMDBException, Exception;

    public ArrayList checkPensionData(DataSource ds, String aadharCardNo) throws SADAREMDBException, Exception;

    public ArrayList getSADAREMAssessedData(DataSource ds, PartADTO partADTO) throws SQLException, SADAREMDBException;

    public int taggedAadharCardNo(DataSource ds, PartAForm partAForm) throws SADAREMDBException, SQLException;

    public String insertPersonalDetailsForAadharNumber(PartADTO partadto, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException;
}

