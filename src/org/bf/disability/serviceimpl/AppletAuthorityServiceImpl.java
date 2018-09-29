/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.AppletAuthorityDAO;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dto.AssessedPWDDetailsDTO;
import org.bf.disability.form.AppletAuthorityForPartBForm;
import org.bf.disability.service.AppletAuthorityService;

/**
 *
 * @author 484898
 */
public class AppletAuthorityServiceImpl implements AppletAuthorityService {

    /*
     * This method is for getting the PWD status based on sadarem code
     * @param Datasource
     * @param sadaremCode
     * @return String
     * @Exception
     **/
    public int checkPersonCode(DataSource ds, String sadaremCode) throws SADAREMDBException, SQLException {

        int sadaremId = 0;
        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            sadaremId = appletAuthorityDao.getPersonCodeForAppletAuthority(ds, sadaremCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sadaremId;
    }

    /*
     * This method is for getting the PWD personal details based on sadarem code
     * @param Datasource
     * @param sadaremCode
     * @return String
     * @Exception
     **/
    public ArrayList getAppletAuthorityDetails(DataSource ds, String sadaremCode, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ArrayList personalDetails = new ArrayList();
        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            personalDetails = appletAuthorityDao.getAppletAuthorityDetails(ds, sadaremCode, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personalDetails;
    }

    /*
     * This method is for getting the PWD personal details based on sadarem code
     * @param Datasource
     * @param sadaremCode
     * @return String
     * @Exception
     **/
    public ArrayList getAppletAuthorityDetailsPrints(DataSource ds, String sadaremCode, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ArrayList personalDetails = new ArrayList();
        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            personalDetails = appletAuthorityDao.getAppletAuthorityDetailsPrints(ds, sadaremCode, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personalDetails;
    }

    /*
     * This mentod is for updating the person status weather PWD is eligible or not
     * @param DataSource
     * @param personStatus
     * @return int
     * @throws SADAREMDBException, SQLException
     **/
    public int updatePersonStatus(DataSource ds, String personStatus, String sadaremCode, String categoryId, String categoryCount, String rationCard, String rationCardSlno, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int result = 0;
        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            result = appletAuthorityDao.updatePersonStatus(ds, personStatus, sadaremCode, categoryId, categoryCount, rationCard, rationCardSlno, request);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

    /*
     * This mentod is for updating the person status weather PWD is eligible or not
     * @param DataSource
     * @param personStatus
     * @return int
     * @throws SADAREMDBException, SQLException
     **/
    public int updatePersonStatusAppeal(DataSource ds, String personStatus, String sadaremCode) throws SADAREMDBException, SQLException {
        int result = 0;
        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            result = appletAuthorityDao.updatePersonStatusAppeal(ds, personStatus, sadaremCode);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

    /*
     * This mentod is for updating the person status weather PWD is eligible or not
     * @param DataSource
     * @param personStatus
     * @return int
     * @throws SADAREMDBException, SQLException
     **/
    public String checkPersonForEligibility(DataSource ds, String sadaremCode, String categoryid) throws SADAREMDBException, SQLException {
        String result = null;
        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            result = appletAuthorityDao.checkPersonForEligibility(ds, sadaremCode, categoryid);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

    /*
     * This mentod is for updating the person status weather PWD is eligible or not
     * @param DataSource
     * @param personStatus
     * @return int
     * @throws SADAREMDBException, SQLException
     **/
    public String getPersonStatus(DataSource ds, String sadaremCode) throws SADAREMDBException, SQLException {
        String result = null;
        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            result = appletAuthorityDao.getPersonStatus(ds, sadaremCode);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

    /*
     * This mentod is for updating the person status weather PWD is eligible or not
     * @param DataSource
     * @param personStatus
     * @return int
     * @throws SADAREMDBException, SQLException
     **/
    public void getAge(DataSource ds, String sadaremCode, AppletAuthorityForPartBForm partAForm) throws SADAREMDBException, SQLException {

        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            appletAuthorityDao.getAge(ds, sadaremCode, partAForm);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
     * This method is for getting the PWD status based on sadarem code
     * @param Datasource
     * @param sadaremCode
     * @return String
     * @Exception
     **/
    public int checkPersonStatusForAppealAuthority(DataSource ds, String sadaremCode, String personStatus) throws SADAREMDBException, SQLException {

        int sadaremId = 0;
        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            sadaremId = appletAuthorityDao.checkPersonStatusForAppealAuthority(ds, sadaremCode, personStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sadaremId;
    }

    /*
     * This method is for getting the PWD status based on sadarem code
     * @param Datasource
     * @param sadaremCode
     * @return String
     * @Exception
     **/
    public int updateRationCardDetails(DataSource ds, String sadaremCode, String rationCard, String rationCardSlno, HttpServletRequest request) throws SADAREMDBException, SQLException {

        int sadaremId = 0;
        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            sadaremId = appletAuthorityDao.updateRationCardDetails(ds, sadaremCode, rationCard, rationCardSlno, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sadaremId;
    }

    /*
     * This method is for getting the Schedule Details
     * @param Datasource
     * @param sadaremCode
     * @return String
     * @Exception
     **/
    public ArrayList getScheduleData(DataSource ds, String district_id, String mandal_id) throws SADAREMDBException, SQLException {

        ArrayList personalDetails = new ArrayList();
        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            personalDetails = appletAuthorityDao.getScheduleData(ds, district_id, mandal_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personalDetails;
    }

    /*
     * This mentod is for updating the person status weather PWD is eligible or not
     * @param DataSource
     * @param personStatus
     * @return int
     * @throws SADAREMDBException, SQLException
     **/
    public String getCategoryId(DataSource ds, String sadaremCode) throws SADAREMDBException, SQLException {
        String result = null;
        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            result = appletAuthorityDao.getCategoryId(ds, sadaremCode);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

    /*
     * This mentod is for updating the person status weather PWD is eligible or not
     * @param DataSource
     * @param personStatus
     * @return int
     * @throws SADAREMDBException, SQLException
     **/
    public String getCategoryCount(DataSource ds, String sadaremCode, String categoryid) throws SADAREMDBException, SQLException {
        String result = null;
        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            result = appletAuthorityDao.getCategoryCount(ds, sadaremCode, categoryid);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

    /*
     * This mentod is for updating the person status weather PWD is eligible or not
     * @param DataSource
     * @param personStatus
     * @return int
     * @throws SADAREMDBException, SQLException
     **/
    public String getAge(DataSource ds, String sadaremCode) throws SADAREMDBException, SQLException {
        String result = null;
        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            result = appletAuthorityDao.getAge(ds, sadaremCode);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

    /*
     * This method is for getting the PWD status based on sadarem code
     * @param Datasource
     * @param sadaremCode
     * @return String
     * @Exception
     **/
    public int checkStatusForAppealCheck(DataSource ds, String sadaremCode, String categoryid) throws SADAREMDBException, SQLException
    {

        int sadaremId = 0;
        try 
        {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            sadaremId = appletAuthorityDao.getCheckStatusForAppealCheck(ds, sadaremCode, categoryid);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return sadaremId;
    }

    /*
     * This method is for getting the PWD status based on sadarem code
     * @param Datasource
     * @param transactionalStatus
     * @param person_code
     * @param request
     * @return int
     * @Exception
     **/
    public int insertTransactionDetails(DataSource ds, String transactionalStatus, String person_code, HttpServletRequest request) throws SADAREMDBException, SQLException {

        int status = 0;
        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            status = appletAuthorityDao.insertTransactionDetails(ds, transactionalStatus, person_code, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    /*
     * This method is for Releasing the powercut IDS
     * @param Datasource
     * @param request
     * @return ArrayList
     * @Exception
     **/
    public String getPowerCutIds(DataSource ds, String transactionStatus, String sadaremCode, HttpServletRequest request) throws SADAREMDBException, SQLException {

        String powerCutIds = null;
        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            powerCutIds = appletAuthorityDao.getPowerCutDetails(ds, transactionStatus, sadaremCode, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return powerCutIds;
    }
    
    
    /*
     * This method is to check disability details check
     * @param Datasource
     * @param request
     * @return ArrayList
     * @Exception
     **/
    public String CheckDisabilityDetails(DataSource ds, String sadaremCode, HttpServletRequest request)
    {

        String DisabilityExist = null;
        try
        {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            DisabilityExist = appletAuthorityDao.chkDisabilityDetails(ds, sadaremCode, request);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return DisabilityExist;
    }
    

    /*
     * This method is for Deleting powerCut ids
     * @param Datasource
     * @param personCode
     * @return int
     * @Exception
     **/
    public int powerCutIds(DataSource ds, String personCode, HttpServletRequest request) throws SADAREMDBException, SQLException {

        int sadaremId = 0;
        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            sadaremId = appletAuthorityDao.powerCutIds(ds, personCode, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sadaremId;
    }

    public boolean isEligibleforPhysicalRequirements(DataSource ds, String person_code, HttpServletRequest request) throws SADAREMDBException, SQLException {
        boolean exist = false;
        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            exist = appletAuthorityDao.isEligibleforPhysicalRequirements(ds, person_code, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exist;

    }

    public void insertPhysicalRequirements(DataSource ds, String person_code, HttpServletRequest request) throws SADAREMDBException, SQLException {
        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            appletAuthorityDao.insertPhysicalRequirements(ds, person_code, request);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList getPhysicalReqDetailsPrints(DataSource ds, String sadaremCode, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ArrayList personalDetails = new ArrayList();
        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            personalDetails = appletAuthorityDao.getPhysicalReqDetailsPrints(ds, sadaremCode, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personalDetails;
    }

    public ArrayList getAllDataReport(DataSource ds, HttpServletRequest request) {
        ArrayList dataList = new ArrayList();
        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            dataList = appletAuthorityDao.getAllDataReport(ds, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

     public ArrayList getAllPersonalData(DataSource ds, AssessedPWDDetailsDTO assessedPWDDetailsForm)throws SADAREMDBException, SQLException {
         ArrayList dataList = new ArrayList();
        try {
            AppletAuthorityDAO appletAuthorityDao = new AppletAuthorityDAO();
            dataList = appletAuthorityDao.getAllPersonalData(ds, assessedPWDDetailsForm);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAllPersonalData", "AppletAuthorityServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityServiceImpl", "getAllPersonalData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAllPersonalData", "AppletAuthorityServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityServiceImpl", "getAllPersonalData");
        }
        return dataList;
    }
}
