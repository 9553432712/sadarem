/*
 * DoctorsInformationServiceImpl.java
 *
 * Created on September 12, 2008, 2:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.DoctorsInformationDAO;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dto.DoctorsInformationDTO;
import org.bf.disability.service.DoctorsInformationService;

/**
 * This class has the implementation functionality for DoctorsInformationService
 * interface.
 * @author Pramod Kumar G
 * @version 1.0
 */
public class DoctorsInformationServiceImpl implements DoctorsInformationService {

    DoctorsInformationDAO doctorsinfodao = new DoctorsInformationDAO();
    DoctorsInformationDTO doctorsinfodto = new DoctorsInformationDTO();

    public int insertDoctorsDetails(DataSource datasource, DoctorsInformationDTO doctorsinfodto, String localaddr, String districtid_id, String loginid_id, int campid_id) throws SADAREMDBException, SQLException {
        int i=0;
        try {
           i= doctorsinfodao.insertDoctorDetails(datasource, doctorsinfodto, localaddr, districtid_id, loginid_id, campid_id);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertDoctorsDetails", "DoctorsInformationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationServiceImpl", "insertDoctorsDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertDoctorsDetails", "DoctorsInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationServiceImpl", "insertDoctorsDetails");
        }
        return i;
    }

    public DoctorsInformationDTO selectDoctorDetails(DataSource datasource, String disabilitytype, int campid_id) throws SADAREMDBException, SQLException {
        try {
            doctorsinfodto = doctorsinfodao.selectDoctorDetails(datasource, disabilitytype, campid_id);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "selectDoctorDetails", "DoctorsInformationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationServiceImpl", "selectDoctorDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "selectDoctorDetails", "DoctorsInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationServiceImpl", "selectDoctorDetails");
        }
        return doctorsinfodto;
    }

    public DoctorsInformationDTO selectDoctors(DataSource datasource, String personCode) throws SADAREMDBException, SQLException {
        try {
            doctorsinfodto = doctorsinfodao.selectDoctors(datasource, personCode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "selectDoctors", "DoctorsInformationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationServiceImpl", "selectDoctors");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "selectDoctors", "DoctorsInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationServiceImpl", "selectDoctors");
        }
        return doctorsinfodto;
    }

    public int updateDoctors(DataSource datasource, DoctorsInformationDTO doctorsinfodto, String personcode,int campid_id,String role_id) throws SADAREMDBException, SQLException {
        try {
            doctorsinfodao.updateDoctors(datasource, doctorsinfodto, personcode, campid_id,role_id);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateDoctors", "DoctorsInformationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationServiceImpl", "updateDoctors");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateDoctors", "DoctorsInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationServiceImpl", "updateDoctors");
        }
        return 0;
    }

    public int updateDoctorDetails(DataSource datasource, DoctorsInformationDTO doctorsinfodto, int campid_id) throws SADAREMDBException, SQLException {
        int i=0;
        try {
            i=doctorsinfodao.updateDoctorDetails(datasource, doctorsinfodto, campid_id);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateDoctorDetails", "DoctorsInformationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationServiceImpl", "updateDoctorDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateDoctorDetails", "DoctorsInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationServiceImpl", "updateDoctorDetails");
        }
        return i;
    }

    public ArrayList selectDisabilityTypes(DataSource datasource) throws SADAREMDBException, SQLException {
        ArrayList disabilitytype = new ArrayList();
        try {
            disabilitytype = doctorsinfodao.selectDisabilityTypes(datasource);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "selectDisabilityTypes", "DoctorsInformationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationServiceImpl", "selectDisabilityTypes");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "selectDisabilityTypes", "DoctorsInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationServiceImpl", "selectDisabilityTypes");
        } 
        return disabilitytype;
    }

     public  int addUser(DataSource ds, DoctorsInformationDTO doctorsinfodto, String districtid_id, String loginid_id, int campid_id, String localaddr, String flag) throws SADAREMDBException, SQLException {


        try {
            return doctorsinfodao.addUser(ds, doctorsinfodto, districtid_id, loginid_id, campid_id, localaddr, flag);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "addUser", "DoctorsInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationServiceImpl", "addUser");
        }
    }
}
