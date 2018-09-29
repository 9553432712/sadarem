/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.CampDetailsDTO;
import org.bf.disability.form.CampDailyReportForm;
import org.bf.disability.form.CampDetailsForm;

/**
 *
 * @author SADAREM
 */
public interface CampDailyReportService {

    public ArrayList getCampBasedOnLoginDetails(DataSource ds, String districtId) throws SADAREMDBException, SQLException;

    public ArrayList getCampDailyReportDetails(DataSource ds, String district_id) throws SADAREMDBException, SQLException;

    public ArrayList getDisabilityDetails(DataSource ds) throws SADAREMDBException, SQLException;

    public int insertPwdAssessementDetails(DataSource ds, CampDailyReportForm campDailyReportForm, String districtid, String loginID, String systemIP) throws SADAREMDBException, SQLException;

    public int getCampDetailsDateWise(DataSource ds, CampDetailsForm sueRaiseApprovalForm, String districtid, String loginID, String systemIP) throws SADAREMDBException, SQLException;

    //kavya
    public int insertCampDetailsDateWise(DataSource ds, CampDetailsForm sueRaiseApprovalForm, String districtid, String loginID, String systemIP) throws SADAREMDBException, SQLException;

    public ArrayList getCampReportBasedOnMonthYearDistrict(DataSource ds, CampDetailsDTO campDetailsDTO, String flag) throws SADAREMDBException, SQLException;

    public ArrayList getCampDisabilityWiseReport(DataSource ds, CampDetailsDTO campDetailsDTO) throws SADAREMDBException, SQLException;

    public String getCampName(DataSource ds, String campId) throws SADAREMDBException, SQLException;
    public ArrayList getDisabilityList(DataSource ds) throws SADAREMDBException, SQLException;

    public String checkCampAssessmentStatus(DataSource ds,CampDetailsForm sueRaiseApprovalForm) throws SADAREMDBException, SQLException;
}
