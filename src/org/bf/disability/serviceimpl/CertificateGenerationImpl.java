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
import org.bf.disability.dao.CertificateGenerationDAO;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.form.CertificateGenerationForm;
import org.bf.disability.service.CertificateGenerationService;

/**
 *
 * @author 728056
 */
public class CertificateGenerationImpl implements CertificateGenerationService {

    public ArrayList getMandalList(DataSource ds, String districtid) throws SADAREMDBException, SQLException {
        ArrayList MandalList = new ArrayList();
        CertificateGenerationDAO cgdao = new CertificateGenerationDAO();
        try {
            MandalList = cgdao.getMandalList(ds, districtid);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMandalList", "CertificateGenerationImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificateGenerationImpl", "getMandalList");
        }
        return MandalList;
    }

    public ArrayList getviiageNames(DataSource ds, CertificateGenerationForm cgform, String districtid) throws SADAREMDBException, SQLException {
        ArrayList VillageNames = new ArrayList();
        CertificateGenerationDAO cgdao = new CertificateGenerationDAO();
        try {
            VillageNames = cgdao.getviiageNames(ds, cgform, districtid);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getviiageNames", "CertificateGenerationImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificateGenerationImpl", "getviiageNames");
        }
        return VillageNames;
    }

    public ArrayList getHabitationNames(DataSource ds, CertificateGenerationForm cgform,String districtid) throws SADAREMDBException, SQLException {
        ArrayList HabitationNames = new ArrayList();
        CertificateGenerationDAO cgdao = new CertificateGenerationDAO();
        try {
            HabitationNames = cgdao.getHabitationNames(ds, cgform,districtid);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getHabitationNames", "CertificateGenerationImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificateGenerationImpl", "getHabitationNames");
        }

        return HabitationNames;
    }

    public ArrayList getPersonDetails(DataSource ds, CertificateGenerationForm cgform, String districtid) throws SADAREMDBException, SQLException {
        ArrayList data = new ArrayList();
        CertificateGenerationDAO cgdao = new CertificateGenerationDAO();
        try {
            data = cgdao.getPersonDetails(ds, cgform, districtid);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPersonDetails", "CertificateGenerationImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificateGenerationImpl", "getPersonDetails");
        }

        return data;
    }

    public boolean insertDetails(DataSource ds, CertificateGenerationForm cgform) throws SADAREMDBException, SQLException {
        boolean insert = false;
        CertificateGenerationDAO cgdao = new CertificateGenerationDAO();
        try {
            insert = cgdao.insertDetails(ds, cgform);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertDetails", "CertificateGenerationImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificateGenerationImpl", "insertDetails");
        }
        return insert;
    }
}
