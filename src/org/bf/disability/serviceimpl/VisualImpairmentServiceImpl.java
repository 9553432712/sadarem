/*
 * VisualImpairmentServiceImpl.java
 *
 * Created on September 12, 2008, 1:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.VisualImapairmentDAO;
import org.bf.disability.dto.CardioPulmonaryDTO;
import org.bf.disability.dto.DwarfismDTO;
import org.bf.disability.dto.MentalIllnessDTO;
import org.bf.disability.service.VisualImpairmentService;

/**
 * This class has the implementation functionality for VisualImpairmentService
 * interface.
 * @author Krishnaprasad K
 * @version 1.0
 */
public class VisualImpairmentServiceImpl implements VisualImpairmentService {

    VisualImapairmentDAO dao = new VisualImapairmentDAO();

    //*************Cardio Pulmonary methods ***************
    public int insertCardioPulmonary(DataSource ds, String personcode, float cardiopulmonaryvalue, String Systemip, String loginid, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i = 0;
        try {
            i = dao.insertCardioPulmonary(ds, personcode, cardiopulmonaryvalue, Systemip, loginid, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertCardioPulmonary", "VisualImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "insertCardioPulmonary");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertCardioPulmonary", "VisualImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "insertCardioPulmonary");
        }
        return i;

    }

    public float getCardioPulmonaryDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        float f;
        try {
            f = dao.getCardioPulmonaryDetails(ds, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCardioPulmonaryDetails", "VisualImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "getCardioPulmonaryDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCardioPulmonaryDetails", "VisualImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "getCardioPulmonaryDetails");
        }
        return f;
    }

    public int insertDwarfismAU(DataSource ds, DwarfismDTO dto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i;
        try {
            i = dao.insertDwarfismAU(ds, dto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertDwarfismAU", "VisualImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "insertDwarfismAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertDwarfismAU", "VisualImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "insertDwarfismAU");
        }
        return i;
    }

    public int updateCardioPolumonary(DataSource ds, String personcode, float cardiopulmonary, String systemip, String loginid, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i;
        try {
            i = dao.updateCardioPolumonary(ds, personcode, cardiopulmonary, systemip, loginid, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateCardioPolumonary", "VisualImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "updateCardioPolumonary");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateCardioPolumonary", "VisualImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "updateCardioPolumonary");
        }
        return i;
    }

    public int insertMentalIllnessAU(DataSource ds, MentalIllnessDTO dto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i;
        try {
            i = dao.insertMentalIllnessAU(ds, dto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertMentalIllnessAU", "VisualImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "insertMentalIllnessAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertMentalIllnessAU", "VisualImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "insertMentalIllnessAU");
        }
        return i;
    }

    public int insertCardioPulmonaryAU(DataSource ds, String personcode, float cardiopulmonaryvalue, String Systemip, String loginid, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i = 0;
        try {
            i = dao.insertCardioPulmonaryAU(ds, personcode, cardiopulmonaryvalue, Systemip, loginid, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertCardioPulmonaryAU", "VisualImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "insertCardioPulmonaryAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertCardioPulmonaryAU", "VisualImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "insertCardioPulmonaryAU");
        }
        return i;

    }

    public int insertVisualImparmentAU(DataSource ds, String personcode, float visualimparment, String systemip, String loginid, CardioPulmonaryDTO cardioPulmonarydto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i;
        try {
            i = dao.insertVisualImparmentAU(ds, personcode, visualimparment, systemip, loginid, cardioPulmonarydto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertVisualImparmentAU", "VisualImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "insertVisualImparmentAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertVisualImparmentAU", "VisualImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "insertVisualImparmentAU");
        }
        
        return i;
    }

    //*************Dwarfism methods ***************
    public int insertDwarfism(DataSource ds, DwarfismDTO dto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i;
        try {
            i = dao.insertDwarfism(ds, dto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertDwarfism", "VisualImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "insertDwarfism");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertDwarfism", "VisualImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "insertDwarfism");
        }
        return i;
    }

    public String getGender(DataSource ds, String personid) throws SADAREMDBException, SQLException {
        String gender = null;
        try {
            gender = dao.getGender(ds, personid);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getGender", "VisualImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "getGender");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getGender", "VisualImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "getGender");
        }
        return gender;
    }

    public DwarfismDTO getDwarfismDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        DwarfismDTO dwarfismdto;
        try {
            dwarfismdto = dao.getDwarfismDetails(ds, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDwarfismDetails", "VisualImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "getDwarfismDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDwarfismDetails", "VisualImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "getDwarfismDetails");
        }
        return dwarfismdto;
    }

    public int updateDwarfism(DataSource ds, DwarfismDTO dwarfismdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i;
        try {
            i = dao.updateDwarfism(ds, dwarfismdto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateDwarfism", "VisualImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "updateDwarfism");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateDwarfism", "VisualImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "updateDwarfism");
        }
        return i;
    }

    public int insertMentalIllness(DataSource ds, MentalIllnessDTO dto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i;
        try {
            i = dao.insertMentalIllness(ds, dto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertMentalIllness", "VisualImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "insertMentalIllness");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertMentalIllness", "VisualImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "insertMentalIllness");
        }
        return i;
    }

    public MentalIllnessDTO getMentalIllnessDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        MentalIllnessDTO mentalillnessdto;
        try {
            mentalillnessdto = dao.getMentalIllnessDetails(ds, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMentalIllnessDetails", "VisualImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "getMentalIllnessDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMentalIllnessDetails", "VisualImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "getMentalIllnessDetails");
        }
        return mentalillnessdto;
    }

    public int updateMentalIllnesDetails(DataSource ds, MentalIllnessDTO millnessdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i;
        try {
            i = dao.updateMentalIllnesDetails(ds, millnessdto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateMentalIllnesDetails", "VisualImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "updateMentalIllnesDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateMentalIllnesDetails", "VisualImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "updateMentalIllnesDetails");
        }
        return i;
    }

    public int insertVisualImparment(DataSource ds, String personcode, float visualimparment, String systemip, String loginid, CardioPulmonaryDTO cardioPulmonarydto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i;
        try {
            i = dao.insertVisualImparment(ds, personcode, visualimparment, systemip, loginid, cardioPulmonarydto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertVisualImparment", "VisualImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "insertVisualImparment");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertVisualImparment", "VisualImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "insertVisualImparment");
        }
        return i;
    }

    public CardioPulmonaryDTO getVisualImpairment(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        CardioPulmonaryDTO cardiopolumonarydto;
        try {
            cardiopolumonarydto = dao.getVisualImpairment(ds, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getVisualImpairment", "VisualImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "getVisualImpairment");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getVisualImpairment", "VisualImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "getVisualImpairment");
        }
        return cardiopolumonarydto;
    }

    public int updateVisualImparment(DataSource ds, String personcode, double visualimparment, String systemip, String loginid, CardioPulmonaryDTO cardioPulmonarydto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i;
        try {
            i = dao.updateVisualImpairment(ds, personcode, visualimparment, systemip, loginid, cardioPulmonarydto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateVisualImparment", "VisualImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "updateVisualImparment");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateVisualImparment", "VisualImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentServiceImpl", "updateVisualImparment");
        }

        return i;
    }
}   
