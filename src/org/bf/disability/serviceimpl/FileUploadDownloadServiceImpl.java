/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.FileUploadDownloadDAO;
import org.bf.disability.service.FileUploadDownloadService;

/**
 *
 * @author 693461
 */
public class FileUploadDownloadServiceImpl implements FileUploadDownloadService {

    public String getDistrictLoginNameDetails(DataSource ds, String distid) throws SADAREMDBException,SQLException {

        String districtLoginNameDetails = null;

        FileUploadDownloadDAO fileUploadDownloadDAO = new FileUploadDownloadDAO();

        try {

            districtLoginNameDetails = fileUploadDownloadDAO.getDistrictLoginNameDetails(ds, distid);


        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDistrictLoginNameDetails", "FileUploadDownloadServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FileUploadDownloadServiceImpl", "getDistrictLoginNameDetails");
        }


        return districtLoginNameDetails;
    }

    public int insertFileUpdateDetails(DataSource ds, String districtId, String fileName) throws SADAREMDBException,SQLException {

        int insertFileUpdateDetails = 0;
        FileUploadDownloadDAO fileUploadDownloadDAO = new FileUploadDownloadDAO();
        try {
            insertFileUpdateDetails = fileUploadDownloadDAO.insertFileUpdateDetails(ds, districtId, fileName);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertFileUpdateDetails", "FileUploadDownloadServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FileUploadDownloadServiceImpl", "insertFileUpdateDetails");
        }

        return insertFileUpdateDetails;
    }

    public ArrayList getDistEmailIds(DataSource ds, String districtId) throws SADAREMDBException,SQLException {

        ArrayList emails = null;
        FileUploadDownloadDAO fileUploadDownloadDAO = new FileUploadDownloadDAO();
        try {
            emails = fileUploadDownloadDAO.getDistEmailIds(ds, districtId);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDistEmailIds", "FileUploadDownloadServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FileUploadDownloadServiceImpl", "getDistEmailIds");
        }
        return emails;
    }

    public String basedOnSessionDetails(DataSource ds, String userid) throws SADAREMDBException,SQLException {

        String userList = null;
        FileUploadDownloadDAO fileUploadDownloadDAO = new FileUploadDownloadDAO();

        try {
            userList = fileUploadDownloadDAO.basedOnSessionDetails(ds, userid);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "basedOnSessionDetails", "FileUploadDownloadServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FileUploadDownloadServiceImpl", "basedOnSessionDetails");
        }
        return userList;

    }

    public ArrayList getUserDetailsForMail(DataSource ds, String distId) throws SADAREMDBException,SQLException {
        ArrayList userList = null;
        FileUploadDownloadDAO fileUploadDownloadDAO = new FileUploadDownloadDAO();

        try {
            userList = fileUploadDownloadDAO.getUserDetailsForMail(ds, distId);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getUserDetailsForMail", "FileUploadDownloadServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FileUploadDownloadServiceImpl", "getUserDetailsForMail");
        }
        return userList;
    }

    public String getFileDeleteDetials(DataSource ds) throws SADAREMDBException,SQLException {

        String fileDeleteDetails = null;
        FileUploadDownloadDAO fileUploadDownloadDAO = new FileUploadDownloadDAO();
        try {
            fileDeleteDetails = fileUploadDownloadDAO.getFileDeleteDetials(ds);

        }catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getFileDeleteDetials", "FileUploadDownloadServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FileUploadDownloadServiceImpl", "getFileDeleteDetials");
        }
        return fileDeleteDetails;
    }

    public ArrayList getDistrictCCMails(DataSource ds, String district) throws SADAREMDBException,SQLException {

        ArrayList districtCCMails = new ArrayList();
        FileUploadDownloadDAO fileUploadDownloadDAO = new FileUploadDownloadDAO();
        try {
            districtCCMails = fileUploadDownloadDAO.getDistrictCCMails(ds, district);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDistrictCCMails", "FileUploadDownloadServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FileUploadDownloadServiceImpl", "getDistrictCCMails");
        }


        return districtCCMails;
    }
}
