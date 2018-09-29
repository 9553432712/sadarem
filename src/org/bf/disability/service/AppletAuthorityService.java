/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.AssessedPWDDetailsDTO;
import org.bf.disability.form.AppletAuthorityForPartBForm;

/**
 * This service acts as an interface between AppletAuthorityAction and AppletAuthorityDao
 * @author 484898
 */
public interface AppletAuthorityService {

    public int checkPersonCode(DataSource ds, String sadaremCode) throws SADAREMDBException, SQLException;

    public ArrayList getAppletAuthorityDetails(DataSource ds, String sadaremCode, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public ArrayList getAppletAuthorityDetailsPrints(DataSource ds, String sadaremCode, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int updatePersonStatus(DataSource ds, String perosnStatus, String sadaremCode, String categoryId, String categoryCount, String rationCard, String rationCardSlno, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public String checkPersonForEligibility(DataSource ds, String sadaremCode, String categoryid) throws SADAREMDBException, SQLException;

    public String getPersonStatus(DataSource ds, String sadaremCode) throws SADAREMDBException, SQLException;

    public void getAge(DataSource ds, String sadaremCode, AppletAuthorityForPartBForm partAForm) throws SADAREMDBException, SQLException;

    public int checkPersonStatusForAppealAuthority(DataSource ds, String sadaremCode, String perosnStatus) throws SADAREMDBException, SQLException;

    public int updateRationCardDetails(DataSource ds, String sadaremCode, String rationCard, String rationCardSlno, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int updatePersonStatusAppeal(DataSource ds, String perosnStatus, String sadaremCode) throws SADAREMDBException, SQLException;

    public String getCategoryId(DataSource ds, String sadaremCode) throws SADAREMDBException, SQLException;

    public String getCategoryCount(DataSource ds, String sadaremCode, String categoryid) throws SADAREMDBException, SQLException;
    //Schedule

    public ArrayList getScheduleData(DataSource ds, String district_id, String mandal_id) throws SADAREMDBException, SQLException;

    public String getAge(DataSource ds, String sadaremCode) throws SADAREMDBException, SQLException;

    public int checkStatusForAppealCheck(DataSource ds, String sadaremCode, String categoryid) throws SADAREMDBException, SQLException;

    public int insertTransactionDetails(DataSource ds, String transactionalStatus, String person_code, HttpServletRequest request) throws SADAREMDBException, SQLException;

    // powerCut ids.
    public String getPowerCutIds(DataSource ds, String transactionStatus, String sadaremCode, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int powerCutIds(DataSource ds, String person_code, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public boolean isEligibleforPhysicalRequirements(DataSource ds, String person_code, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public void insertPhysicalRequirements(DataSource ds, String person_code, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public ArrayList getPhysicalReqDetailsPrints(DataSource ds, String sadaremCode, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public ArrayList getAllDataReport(DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public ArrayList getAllPersonalData(DataSource ds, AssessedPWDDetailsDTO assessedPWDDetailsForm) throws SADAREMDBException, SQLException;

	public String CheckDisabilityDetails(DataSource ds, String personcode,HttpServletRequest request);
}
