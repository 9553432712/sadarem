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
import org.bf.disability.dao.NameRelationChangeDAO;
import org.bf.disability.dto.NameRelationChangeDTO;
import org.bf.disability.form.NameRelationChangeForm;
import org.bf.disability.service.NameRelationChangeService;

/**
 *
 * @author 693461
 */
public class NameRelationChangeServiceImpl implements NameRelationChangeService {

    public ArrayList getnameRelationChangeDetails(DataSource ds, int campId, String districtId) throws SADAREMDBException, SQLException {

        ArrayList nameRelationList = new ArrayList();
        NameRelationChangeDAO nameRelationChangeDAO = new NameRelationChangeDAO();

        try {
            nameRelationList = nameRelationChangeDAO.getnameRelationChangeDetails(ds, campId, districtId);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getnameRelationChangeDetails", "NameRelationChangeServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeServiceImpl", "getnameRelationChangeDetails");
        }
        return nameRelationList;
    }

    public ArrayList individualNameRelationNameChangeDetails(DataSource ds, String requestId) throws SADAREMDBException, SQLException {
        ArrayList nameRelationList = new ArrayList();
        NameRelationChangeDAO nameRelationChangeDAO = new NameRelationChangeDAO();

        try {
            nameRelationList = nameRelationChangeDAO.individualNameRelationNameChangeDetails(ds, requestId);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "individualNameRelationNameChangeDetails", "NameRelationChangeServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeServiceImpl", "individualNameRelationNameChangeDetails");
        }

        return nameRelationList;
    }

    public ArrayList oldParticularDertails(DataSource ds, String personCode, String requestId, String requestTypeId) throws SADAREMDBException, SQLException {
        ArrayList oldParticularList = new ArrayList();
        NameRelationChangeDAO nameRelationChangeDAO = new NameRelationChangeDAO();

        try {
            //  oldParticularList = nameRelationChangeDAO.oldParticularDertails(ds, personCode, requestId, requestTypeId);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "oldParticularDertails", "NameRelationChangeServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeServiceImpl", "oldParticularDertails");
        }
        return oldParticularList;

    }

    public int updateParticularDetails(DataSource ds, NameRelationChangeForm nameRelationChangeForm, String requestId) throws SADAREMDBException, SQLException {

        int updateParticularDetails = 0;
        NameRelationChangeDAO nameRelationChangeDAO = new NameRelationChangeDAO();

        try {
            updateParticularDetails = nameRelationChangeDAO.updateParticularDetails(ds, nameRelationChangeForm, requestId);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateParticularDetails", "NameRelationChangeServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeServiceImpl", "updateParticularDetails");
        }


        return updateParticularDetails;

    }

//public ArrayList RelationChangeDetails(DataSource ds, String requestId, String requestType) throws SADAREMDBException,SQLException ;
//public ArrayList NameChangeDetails(DataSource ds, String requestId, String requestType) throws SADAREMDBException,SQLException ;
// ArrayList MoleChangeDetails(DataSource ds, String requestId, String requestType) throws SADAREMDBException,SQLException ;
//public ArrayList DOBChangeDetails(DataSource ds, String requestId, String requestType) throws SADAREMDBException,SQLException ;
    public ArrayList NameChangeDetails(DataSource ds, String requestId, String requestType) throws SADAREMDBException, SQLException {

        ArrayList individualNameRelationList = new ArrayList();
        NameRelationChangeDAO nameRelationChangeDAO = new NameRelationChangeDAO();

        try {
            individualNameRelationList = nameRelationChangeDAO.NameChangeDetails(ds, requestId, requestType);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "NameChangeDetails", "NameRelationChangeServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeServiceImpl", "NameChangeDetails");
        }


        return individualNameRelationList;

    }

    public ArrayList RelationChangeDetails(DataSource ds, String requestId, String requestType) throws SADAREMDBException, SQLException {

        ArrayList individualNameRelationList = new ArrayList();
        NameRelationChangeDAO nameRelationChangeDAO = new NameRelationChangeDAO();

        try {
            individualNameRelationList = nameRelationChangeDAO.RelationChangeDetails(ds, requestId, requestType);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "RelationChangeDetails", "NameRelationChangeServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeServiceImpl", "RelationChangeDetails");
        }


        return individualNameRelationList;

    }

    public ArrayList MoleChangeDetails(DataSource ds, String requestId, String requestType) throws SADAREMDBException, SQLException {

        ArrayList individualNameRelationList = new ArrayList();
        NameRelationChangeDAO nameRelationChangeDAO = new NameRelationChangeDAO();

        try {
            individualNameRelationList = nameRelationChangeDAO.MoleChangeDetails(ds, requestId, requestType);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "MoleChangeDetails", "NameRelationChangeServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeServiceImpl", "MoleChangeDetails");
        }


        return individualNameRelationList;

    }

    public ArrayList DOBChangeDetails(DataSource ds, String requestId, String requestType) throws SADAREMDBException, SQLException {

        ArrayList individualNameRelationList = new ArrayList();
        NameRelationChangeDAO nameRelationChangeDAO = new NameRelationChangeDAO();

        try {
            individualNameRelationList = nameRelationChangeDAO.DOBChangeDetails(ds, requestId, requestType);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "DOBChangeDetails", "NameRelationChangeServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeServiceImpl", "DOBChangeDetails");
        }


        return individualNameRelationList;

    }

    public String getPersonCodeDetails(DataSource ds, String requestId) throws SADAREMDBException, SQLException {

        String nameRelationList = null;
        NameRelationChangeDAO nameRelationChangeDAO = new NameRelationChangeDAO();

        try {
            nameRelationList = nameRelationChangeDAO.getPersonCodeDetails(ds, requestId);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPersonCodeDetails", "NameRelationChangeServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeServiceImpl", "getPersonCodeDetails");
        }

        return nameRelationList;
    }

    public ArrayList getEmailBodyDetails(DataSource ds, String personCode, String requestId) throws SADAREMDBException, SQLException {

        ArrayList emailList = new ArrayList();
        NameRelationChangeDAO nameRelationChangeDAO = new NameRelationChangeDAO();
        NameRelationChangeDTO nameRelationChangeDTO = new NameRelationChangeDTO();

        try {
            emailList = nameRelationChangeDAO.getEmailBodyDetails(ds, personCode, requestId);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getEmailBodyDetails", "NameRelationChangeServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeServiceImpl", "getEmailBodyDetails");
        }

        return emailList;
    }

    public String emailpersonDetails(DataSource ds, String username, String districtId) throws SADAREMDBException, SQLException {

        String emailPersonName = null;
        NameRelationChangeDAO nameRelationChangeDAO = new NameRelationChangeDAO();

        try {
            emailPersonName = nameRelationChangeDAO.emailpersonDetails(ds, username, districtId);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "emailpersonDetails", "NameRelationChangeServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeServiceImpl", "emailpersonDetails");
        }

        return emailPersonName;


    }

    public ArrayList getEmailOldNameRelationDetails(DataSource ds, String personCode) throws SADAREMDBException, SQLException {

        ArrayList oldNameRelationName = new ArrayList();
        NameRelationChangeDAO nameRelationChangeDAO = new NameRelationChangeDAO();

        try {

            oldNameRelationName = nameRelationChangeDAO.getEmailOldNameRelationDetails(ds, personCode);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getEmailOldNameRelationDetails", "NameRelationChangeServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeServiceImpl", "getEmailOldNameRelationDetails");
        }
        return oldNameRelationName;
    }

    public String dOBDetails(DataSource ds, String personCode) throws SADAREMDBException, SQLException {

        String dobDetails = null;
        NameRelationChangeDAO nameRelationChangeDAO = new NameRelationChangeDAO();

        try {
            dobDetails = nameRelationChangeDAO.dOBDetails(ds, personCode);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "dOBDetails", "NameRelationChangeServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeServiceImpl", "dOBDetails");
        }

        return dobDetails;


    }

    public ArrayList getData(DataSource ds, String district_id, String status)  throws SADAREMDBException, SQLException {

        ArrayList resolvList = new ArrayList();
        NameRelationChangeDAO NameRelationChangeDAO = new NameRelationChangeDAO();
        try {
            resolvList = NameRelationChangeDAO.getData(ds, district_id, status);
        } catch (Exception e) {
           int exResult = ExceptionDAO.saveException(ds, e.toString(), "getData", "NameRelationChangeServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeServiceImpl", "getData");

        }
        return resolvList;

    }

    public ArrayList surNameChangeDetails(DataSource ds, String requestId, String requestType) throws SADAREMDBException, SQLException {
      
        ArrayList surNameList = new ArrayList();
        NameRelationChangeDAO NameRelationChangeDAO = new NameRelationChangeDAO();
        try {
            surNameList = NameRelationChangeDAO.surNameChangeDetails(ds, requestId, requestType);
        } catch (Exception e) {
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "surNameChangeDetails", "NameRelationChangeServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeServiceImpl", "surNameChangeDetails");
        }
        return  surNameList;
    }
}
