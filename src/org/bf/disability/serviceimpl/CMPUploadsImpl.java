/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.CMPUploadDAO;
import org.bf.disability.dto.CMPUploadDTO;
import org.bf.disability.service.CMPUploadsService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;


/**
 *
 * @author 484898
 */
public class CMPUploadsImpl implements CMPUploadsService {

    /**
     * This method is for save the Circulars/Memos/Proceedings.
     * @param cmpUploadDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public int saveDetails(CMPUploadDTO cmpUploadDTO, HttpServletRequest request,DataSource ds) throws SADAREMDBException, SQLException {
        int insertStatus = 0;
        CMPUploadDAO cmpUplaodDao = new CMPUploadDAO();
        insertStatus = cmpUplaodDao.saveDetails(cmpUploadDTO, request,ds);
        return insertStatus;
    }

    /**
     * This method is for Update the Circulars/Memos/Proceedings.
     * @param cmpUploadDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public int updateDetails(CMPUploadDTO cmpUploadDTO, HttpServletRequest request,DataSource ds) throws SADAREMDBException, SQLException {
        int insertStatus = 0;
        CMPUploadDAO cmpUplaodDao = new CMPUploadDAO();
        insertStatus = cmpUplaodDao.updateDetails(cmpUploadDTO, request,ds);
        return insertStatus;
    }

    /**
     * This method is for Inactive the Circulars/Memos/Proceedings.
     * @param cmpUploadDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public int inactiveRecord(CMPUploadDTO cmpUploadDTO,DataSource ds) throws SADAREMDBException, SQLException {
        int insertStatus = 0;
        CMPUploadDAO cmpUplaodDao = new CMPUploadDAO();
        insertStatus = cmpUplaodDao.inactiveRecord(cmpUploadDTO,ds);
        return insertStatus;
    }

    /**
     * This methos is for getting teh CMP Uploaded Details
     * @param cmpUploadDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public ArrayList getCMPUploadsDetails(CMPUploadDTO cmpUploadDTO,DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList CMPDetails = new ArrayList();
        CMPUploadDAO cmpUplaodDao = new CMPUploadDAO();
        CMPDetails = cmpUplaodDao.getCMPUploadsDetails(cmpUploadDTO,ds);
        return CMPDetails;
    }

    /**
     * This methos is for getting teh CMP Uploaded Details for edit
     * @param cmpUploadDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public ArrayList getCMPDetailsForEdit(CMPUploadDTO cmpUploadDTO,DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList CMPDetails = new ArrayList();
        CMPUploadDAO cmpUplaodDao = new CMPUploadDAO();
        CMPDetails = cmpUplaodDao.getValuesWhenClickOnEdit(cmpUploadDTO,ds);
        return CMPDetails;
    }

    /**
     * This method is for generating the circular number
     * @param cmpUploadDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public String generateCircularNumber(CMPUploadDTO cmpUploadDTO,DataSource ds) throws SADAREMDBException, SQLException {
        String circularNumber = null;
        CMPUploadDAO cmpUplaodDao = new CMPUploadDAO();
        circularNumber = cmpUplaodDao.generateCircularNumber(cmpUploadDTO,ds);
        return circularNumber;
    }
}
