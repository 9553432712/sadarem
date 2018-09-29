package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.MRBinetKamatDetailedTestDTO;
import org.bf.disability.dto.MentalRetardationDTO;
import org.bf.disability.dto.MentalRetardationTestsDTO;

/**
 * 
 * @author Sunima
 */
public interface MentalRetardationService {

    /**
     * This interface has abstract methods, whose implementation is to perform data
     * manipulation operations like insert, update, select and delete on Mental Retardation
     *as well as MR tools
     * @version 1.0
     */
    public int insertData(DataSource ds, MentalRetardationDTO modifydto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertDataAU(DataSource ds, MentalRetardationDTO modifydto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public MentalRetardationDTO getMentalDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public void updateDetails(DataSource ds, MentalRetardationDTO modifydto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public boolean checkPersoncode(String personcode, DataSource ds) throws SADAREMDBException, SQLException;

    public int MRSfbinsertData(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int MRSfbinsertDataAU(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int MRMalinsinsertData(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int MRMalinsinsertDataAU(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public MentalRetardationTestsDTO getMRseguintestDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public MentalRetardationTestsDTO getMRmalinstestDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public void updateMRSfbDetails(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public void updateMalinstestDetails(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertBinetKamaldata(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertBinetKamaldataAU(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto) throws SADAREMDBException, SQLException;

    public int insertVinelandData(DataSource ds, String personcode, String year, String month, double vinelandresult, String loginid, String systemip, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertVinelandDataAU(DataSource ds, String personcode, String year, String month, double vinelandresult, String loginid, String systemip, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertDevelopmentalData(DataSource ds, String personcode, String year, String month, double developmentalresult, String loginid, String systemip, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertDevelopmentalDataAU(DataSource ds, String personcode, String year, String month, double developmentalresult, String loginid, String systemip, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public MentalRetardationTestsDTO getDevelopmentalData(DataSource ds, String personcode) throws SADAREMDBException, SQLException;

    public MentalRetardationTestsDTO gettBinetKamaldata(DataSource ds, String personcode) throws SADAREMDBException, SQLException;

    public MentalRetardationTestsDTO getVinelandData(DataSource ds, String personcode) throws SADAREMDBException, SQLException;

    public int updateBinetKamaldata(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int updateVinelandData(DataSource ds, String personcode, String year, String month, double vinelandresult, String loginid, String systemip, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int updateDevelopmentalData(DataSource ds, String personcode, String year, String month, double developmentalresult, String loginid, String systemip, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public void insertMRAlexanderTestDetails(DataSource ds, String personcode, MentalRetardationTestsDTO mentalRetardationTestsDTO, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertMRAlexanderTestDetailsAU(DataSource ds, String personcode, MentalRetardationTestsDTO mentalRetardationTestsDTO, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public MentalRetardationTestsDTO getMRAlexanderTestDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException;

    public void updateMRAlexanderTestDetails(DataSource ds, String personcode, MentalRetardationTestsDTO mentalRetardationTestsDTO, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public boolean checkForPersonCode(DataSource ds, String personcode, String tableName) throws SADAREMDBException, SQLException;

    public MentalRetardationTestsDTO getMentalRetardationIQValues(DataSource ds, String personcode) throws SADAREMDBException, SQLException;

    public void insertBhatiaTestDetails(DataSource ds, String personcode, MentalRetardationTestsDTO mentalRetardationTestsDTO, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertBhatiaTestDetailsAU(DataSource ds, String personcode, MentalRetardationTestsDTO mentalRetardationTestsDTO, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public MentalRetardationTestsDTO getBhatiaTestDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException;

    public void updateBhatiaTestDetails(DataSource ds, String personcode, MentalRetardationTestsDTO mentalRetardationTestsDTO, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public MentalRetardationDTO getTestDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public MentalRetardationDTO getTestDetailsAU(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public void insertMRBinetKamatDetailedTestDetails(DataSource ds, MRBinetKamatDetailedTestDTO mRBinetKamatDetailedTestDTO, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertMRBinetKamatDetailedTestDetailsAU(DataSource ds, MRBinetKamatDetailedTestDTO mRBinetKamatDetailedTestDTO, HttpServletRequest request) throws SADAREMDBException, SQLException;

    MRBinetKamatDetailedTestDTO getMRBinetKamatDetailedTestDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException;

    public void updateMRBinetKamatDetailedTestDetails(DataSource ds, MRBinetKamatDetailedTestDTO mRBinetKamatDetailedTestDTO, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public ArrayList getMentalItems(DataSource datasource, String personCode) throws SADAREMDBException, SQLException;

    public ArrayList getMentalItemsVsms(DataSource datasource, String personCode) throws SADAREMDBException, SQLException;
}
