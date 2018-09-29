/*
 * CommonServiceImpl.java
 *
 * Created on September 13, 2008, 12:06 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.service.CommonService;
import org.bf.disability.dto.WebsiteCommonDTO;

/**
 * This class has the implementation functionality for CommonService interface.
 * @author SVS Ganesh
 * @version 1.0
 */
public class CommonServiceImpl implements CommonService {

    CommonDAO commondao = null;
    
    int i = 0;

    public String selectStatus(DataSource datasource, String personCode) throws SADAREMDBException,SQLException {
        String status = null;
        try {
            commondao = new CommonDAO();
            status = commondao.selectStatus(datasource, personCode);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "selectStatus", "CommonServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonServiceImpl", "selectStatus");
        }
        return status;
    }

    public int updateStatus(DataSource datasource, String personCode, String status) throws SADAREMDBException,SQLException {
        try {
            commondao = new CommonDAO();
            i = commondao.updateStatus(datasource, personCode, status);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateStatus", "CommonServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonServiceImpl", "updateStatus");
        }
        return i;
    }

    public String checkPersonCodeForSearch(DataSource datasource, String personCode) throws SADAREMDBException,SQLException {
        String status = null;
        try {
            commondao = new CommonDAO();
            status = commondao.checkPersonCodeForSearch(datasource, personCode);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "checkPersonCodeForSearch", "CommonServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonServiceImpl", "checkPersonCodeForSearch");
        }
        return status;
    }

    public int getTotalPercentage(DataSource datasource, String personCode) throws SADAREMDBException,SQLException {
        try {
            commondao = new CommonDAO();
            i = commondao.getTotalPercentage(datasource, personCode);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getTotalPercentage", "CommonServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonServiceImpl", "getTotalPercentage");
        }
        return i;
    }

    public String getPersonStatus(DataSource datasource, String personCode) throws SADAREMDBException,SQLException {
        String status = null;
        try {
            commondao = new CommonDAO();
            status = commondao.getPersonStatus(datasource, personCode);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonStatus", "CommonServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonServiceImpl", "getPersonStatus");
        }
        return status;
    }

    public String getDisabilityId(DataSource datasource, String personCode) throws SADAREMDBException,SQLException {
        String status = null;
        try {
            commondao = new CommonDAO();
            status = commondao.getDisabilityId(datasource, personCode);
        }catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDisabilityId", "CommonServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonServiceImpl", "getDisabilityId");
        }
        return status;
    }

    public int insertFeedbackDetails(DataSource datasource, WebsiteCommonDTO feedbackdto, String localaddr) throws SADAREMDBException,SQLException 
    {
        try {
            commondao = new CommonDAO();
            i = commondao.insertFeedbackDetails(datasource, feedbackdto, localaddr);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertFeedbackDetails", "CommonServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonServiceImpl", "insertFeedbackDetails");
        }
        return i;
    }
    
    public List getFeedbackDetails(DataSource ds) throws SADAREMDBException,SQLException {
        
        List feedbackList = null;
        try{
           commondao = new CommonDAO();
           feedbackList = commondao.getFeedbackDetails(ds);
        }catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getFeedbackDetails", "CommonServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonServiceImpl", "getFeedbackDetails");
        }
        return feedbackList;
    }

    public int updateFeedbackDetails(DataSource ds,  String FeedbackIdsStatus, String selectedRowId) throws SADAREMDBException,SQLException {

        int i = 0;
        try{
           commondao = new CommonDAO();
           i = commondao.updateFeedbackDetails(ds, FeedbackIdsStatus,selectedRowId);
        }catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateFeedbackDetails", "CommonServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonServiceImpl", "updateFeedbackDetails");
        }
        return i;
    }

    public WebsiteCommonDTO selectFeedbackDetails(DataSource ds, String rowId) throws SADAREMDBException,SQLException
    {
        WebsiteCommonDTO feedbackDTO = null;
        try {
            commondao = new CommonDAO();
            feedbackDTO = new WebsiteCommonDTO();
            feedbackDTO = commondao.selectFeedbackDetails(ds, rowId);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "selectFeedbackDetails", "CommonServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonServiceImpl", "selectFeedbackDetails");
        }
        return feedbackDTO;
    }

    public List getFeedback(DataSource ds) throws SADAREMDBException,SQLException {

        List feedbackList = null;
        try{
           commondao = new CommonDAO();
           feedbackList = commondao.getFeedback(ds);
        }catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getFeedback", "CommonServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonServiceImpl", "getFeedback");
        }
        return feedbackList;
    }

    public boolean checkUploadPhoto(DataSource ds, String PersonCode) throws SADAREMDBException,SQLException {

        CommonDAO commondao = null;
        boolean uploadPhotoFlag = false;
        try {
            commondao = new CommonDAO();
            uploadPhotoFlag = commondao.checkUploadPhoto(ds, PersonCode);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkUploadPhoto", "CommonServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonServiceImpl", "checkUploadPhoto");
        }
        return uploadPhotoFlag;
    }

    public TerritoryDTO getDisabilityPercentages(DataSource datasource, String personCode, int disabilityId) throws SADAREMDBException,SQLException {
       CommonDAO commondao = null;
       TerritoryDTO territoryDTO = null;
        try {
            commondao = new CommonDAO();
            territoryDTO = new TerritoryDTO();
            territoryDTO = commondao.getDisabilityPercentages(datasource, personCode, disabilityId);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDisabilityPercentages", "CommonServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonServiceImpl", "getDisabilityPercentages");
        }
        return territoryDTO;
    }

    public TerritoryDTO getDisabilityPercentagesAU(DataSource datasource, String personCode, int disabilityId) throws SADAREMDBException,SQLException {
       CommonDAO commondao = null;
       TerritoryDTO territoryDTO = null;
        try {
            commondao = new CommonDAO();
            territoryDTO = new TerritoryDTO();
            territoryDTO = commondao.getDisabilityPercentagesAU(datasource, personCode, disabilityId);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDisabilityPercentagesAU", "CommonServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonServiceImpl", "getDisabilityPercentagesAU");
        }
        return territoryDTO;
    }


    public ArrayList getcumulativeReport(DataSource ds) throws SADAREMDBException,SQLException {
        ArrayList cumulativeList = null;
        try{
           commondao = new CommonDAO();
           cumulativeList = commondao.getcumulativeReport(ds);
        }catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getcumulativeReport", "CommonServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonServiceImpl", "getcumulativeReport");
        }
        return cumulativeList;
    }
}
