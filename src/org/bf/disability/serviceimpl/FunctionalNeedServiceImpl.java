/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 509865
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.FunctionalNeedReportDAO;
import org.bf.disability.dao.FunctionalReportDAO;
import org.bf.disability.dto.FunctionalNeedReportDTO;
import org.bf.disability.form.CampDailyReportForm;
import org.bf.disability.service.FunctionalNeedReportService;

public class FunctionalNeedServiceImpl implements FunctionalNeedReportService {

    public ArrayList getMedicalIntervention(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> medicalInterventionList = null;
        medicalInterventionList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            medicalInterventionList =
                    distictFunctionalNeedDAO.getMedicalIntervention(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMedicalIntervention", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMedicalIntervention");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMedicalIntervention", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMedicalIntervention");
        }
        return medicalInterventionList;
    }

    public ArrayList getEmpWiseDetails(DataSource ds, String district_id, String mandal_id, String village_id, FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> empWiseList = null;
        empWiseList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalReportDAO empWiseDAO =
                new FunctionalReportDAO();
        try {
            empWiseList =
                    empWiseDAO.getEmpWiseDetails(ds, district_id, mandal_id, village_id, functionalNeedReportDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getEmpWiseDetails", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getEmpWiseDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getEmpWiseDetails", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getEmpWiseDetails");
        }
        return empWiseList;
    }

    public ArrayList getPersonalempWiseDetails(DataSource ds, String district_id, String mandal_id, String village_id, String habCode, String caste,String urbanId,String fromDate, String todate)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> casteWiseList = null;
        casteWiseList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO casteWiseDAO =
                new FunctionalNeedReportDAO();
        try {
            casteWiseList =
                    casteWiseDAO.getPersonalempWiseDetails(ds, district_id, mandal_id, village_id, habCode, caste,urbanId,fromDate,todate);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPersonalempWiseDetails", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getPersonalempWiseDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPersonalempWiseDetails", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getPersonalempWiseDetails");
        }
        return casteWiseList;
    }

    public ArrayList getAssistiveDevicesProthosis(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> assistiveDevicesProthosisList = null;
        assistiveDevicesProthosisList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            assistiveDevicesProthosisList =
                    distictFunctionalNeedDAO.getAssistiveDevicesProthosis(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAssistiveDevicesProthosis", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAssistiveDevicesProthosis");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAssistiveDevicesProthosis", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAssistiveDevicesProthosis");
        }
        return assistiveDevicesProthosisList;
    }

    public ArrayList getAssistiveDevicesOrthosis(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> assistiveDevicesOrthosisList = null;
        assistiveDevicesOrthosisList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            assistiveDevicesOrthosisList =
                    distictFunctionalNeedDAO.getAssistiveDevicesOrthosis(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAssistiveDevicesOrthosis", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAssistiveDevicesOrthosis");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAssistiveDevicesOrthosis", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAssistiveDevicesOrthosis");
        }
        return assistiveDevicesOrthosisList;
    }

    public ArrayList getAssistiveDevicesWalkingAids(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> assistiveDevicesWalkingAidsList = null;
        assistiveDevicesWalkingAidsList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            assistiveDevicesWalkingAidsList =
                    distictFunctionalNeedDAO.getAssistiveDevicesWalkingAids(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAssistiveDevicesWalkingAids", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAssistiveDevicesWalkingAids");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAssistiveDevicesWalkingAids", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAssistiveDevicesWalkingAids");
        }
        return assistiveDevicesWalkingAidsList;
    }

    public ArrayList getAssistiveDevicesMobility(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> assistiveDevicesMobilityList = null;
        assistiveDevicesMobilityList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            assistiveDevicesMobilityList =
                    distictFunctionalNeedDAO.getAssistiveDevicesMobility(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAssistiveDevicesMobility", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAssistiveDevicesMobility");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAssistiveDevicesMobility", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAssistiveDevicesMobility");
        }
        return assistiveDevicesMobilityList;
    }

    public ArrayList getAssistiveDevicesADLEquipments(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> assistiveDevicesADLEquipmentsList = null;
        assistiveDevicesADLEquipmentsList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            assistiveDevicesADLEquipmentsList =
                    distictFunctionalNeedDAO.getAssistiveDevicesADLEquipments(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAssistiveDevicesADLEquipments", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAssistiveDevicesADLEquipments");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAssistiveDevicesADLEquipments", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAssistiveDevicesADLEquipments");
        }
        return assistiveDevicesADLEquipmentsList;
    }

    public ArrayList getOtherNeeds(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> otherNeedsList = null;
        otherNeedsList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            otherNeedsList =
                    distictFunctionalNeedDAO.getOtherNeeds(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getOtherNeeds", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getOtherNeeds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getOtherNeeds", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getOtherNeeds");
        }
        return otherNeedsList;
    }

    public ArrayList getMedicalInterventionVI(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> medicalInterventionList = null;
        medicalInterventionList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            medicalInterventionList =
                    distictFunctionalNeedDAO.getMedicalInterventionVI(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMedicalInterventionVI", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMedicalInterventionVI");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMedicalInterventionVI", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMedicalInterventionVI");
        }
        return medicalInterventionList;
    }

    public ArrayList getAssistiveAugmentativeDevicesVI(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> assistiveDevicesProthosisList = null;
        assistiveDevicesProthosisList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            assistiveDevicesProthosisList =
                    distictFunctionalNeedDAO.getAssistiveAugmentativeDevicesVI(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAssistiveAugmentativeDevicesVI", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAssistiveAugmentativeDevicesVI");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAssistiveAugmentativeDevicesVI", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAssistiveAugmentativeDevicesVI");
        }
        return assistiveDevicesProthosisList;
    }

    public ArrayList getOtherNeedsVI(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> otherNeedsList = null;
        otherNeedsList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            otherNeedsList =
                    distictFunctionalNeedDAO.getOtherNeedsVI(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getOtherNeedsVI", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getOtherNeedsVI");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getOtherNeedsVI", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getOtherNeedsVI");
        }
        return otherNeedsList;
    }

    public ArrayList getMedicalInterventionHI(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> medicalInterventionList = null;
        medicalInterventionList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            medicalInterventionList =
                    distictFunctionalNeedDAO.getMedicalInterventionHI(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMedicalInterventionHI", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMedicalInterventionHI");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMedicalInterventionHI", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMedicalInterventionHI");
        }
        return medicalInterventionList;
    }

    public ArrayList getAssistiveAugmentativeDevicesHI(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> assistiveDevicesProthosisList = null;
        assistiveDevicesProthosisList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            assistiveDevicesProthosisList =
                    distictFunctionalNeedDAO.getAssistiveAugmentativeDevicesHI(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAssistiveAugmentativeDevicesHI", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAssistiveAugmentativeDevicesHI");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAssistiveAugmentativeDevicesHI", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAssistiveAugmentativeDevicesHI");
        }
        return assistiveDevicesProthosisList;
    }

    public ArrayList getOtherNeedsHI(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> otherNeedsList = null;
        otherNeedsList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            otherNeedsList =
                    distictFunctionalNeedDAO.getOtherNeedsHI(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getOtherNeedsHI", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getOtherNeedsHI");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getOtherNeedsHI", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getOtherNeedsHI");
        }
        return otherNeedsList;
    }

    public ArrayList getMedicalInterventionMR(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> medicalInterventionList = null;
        medicalInterventionList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            medicalInterventionList =
                    distictFunctionalNeedDAO.getMedicalInterventionMR(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMedicalInterventionMR", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMedicalInterventionMR");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMedicalInterventionMR", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMedicalInterventionMR");
        }
        return medicalInterventionList;
    }

    public ArrayList getAssistiveAugmentativeDevicesMR(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> assistiveDevicesProthosisList = null;
        assistiveDevicesProthosisList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            assistiveDevicesProthosisList =
                    distictFunctionalNeedDAO.getAssistiveAugmentativeDevicesMR(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAssistiveAugmentativeDevicesMR", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAssistiveAugmentativeDevicesMR");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAssistiveAugmentativeDevicesMR", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAssistiveAugmentativeDevicesMR");
        }
        return assistiveDevicesProthosisList;
    }

    public ArrayList getOtherNeedsMR(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> otherNeedsList = null;
        otherNeedsList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            otherNeedsList =
                    distictFunctionalNeedDAO.getOtherNeedsMR(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getOtherNeedsMR", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getOtherNeedsMR");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getOtherNeedsMR", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getOtherNeedsMR");
        }
        return otherNeedsList;
    }

    public ArrayList getMedicalInterventionMI(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> medicalInterventionList = null;
        medicalInterventionList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            medicalInterventionList =
                    distictFunctionalNeedDAO.getMedicalInterventionMI(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMedicalInterventionMI", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMedicalInterventionMI");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMedicalInterventionMI", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMedicalInterventionMI");
        }
        return medicalInterventionList;
    }

    public ArrayList getOtherNeedsMI(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> otherNeedsList = null;
        otherNeedsList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            otherNeedsList =
                    distictFunctionalNeedDAO.getOtherNeedsMI(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getOtherNeedsMI", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getOtherNeedsMI");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getOtherNeedsMI", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getOtherNeedsMI");
        }
        return otherNeedsList;
    }

    public ArrayList getAllFunctionalNeeds(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> allFunctionalNeedList = null;
        allFunctionalNeedList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            allFunctionalNeedList =
                    distictFunctionalNeedDAO.getMedicalIntervention(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAllFunctionalNeeds", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAllFunctionalNeeds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAllFunctionalNeeds", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAllFunctionalNeeds");
        }
        return allFunctionalNeedList;
    }

    public ArrayList getCommonGeneralNeeds(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> commonGeneralNeedsList = null;
        commonGeneralNeedsList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            commonGeneralNeedsList =
                    distictFunctionalNeedDAO.getCommonGeneralNeeds(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCommonGeneralNeeds", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getCommonGeneralNeeds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCommonGeneralNeeds", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getCommonGeneralNeeds");
        }
        return commonGeneralNeedsList;
    }

    public ArrayList getMRGeneralNeeds(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> mrGeneralNeedsList = null;
        mrGeneralNeedsList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            mrGeneralNeedsList =
                    distictFunctionalNeedDAO.getMRGeneralNeeds(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMRGeneralNeeds", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMRGeneralNeeds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMRGeneralNeeds", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMRGeneralNeeds");
        }
        return mrGeneralNeedsList;
    }

    public ArrayList getMIGeneralNeeds(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> miGeneralNeedsList = null;
        miGeneralNeedsList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            miGeneralNeedsList =
                    distictFunctionalNeedDAO.getMIGeneralNeeds(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMIGeneralNeeds", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMIGeneralNeeds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMIGeneralNeeds", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMIGeneralNeeds");
        }
        return miGeneralNeedsList;
    }

    public ArrayList getMultipleGeneralNeeds(DataSource ds, FunctionalNeedReportDTO districtFunctionalDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> multipleGeneralNeedsList = null;
        multipleGeneralNeedsList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO multipleGeneralNeedsDAO =
                new FunctionalNeedReportDAO();
        try {
            multipleGeneralNeedsList =
                    multipleGeneralNeedsDAO.getMultipleGeneralNeeds(ds, districtFunctionalDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMultipleGeneralNeeds", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMultipleGeneralNeeds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMultipleGeneralNeeds", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMultipleGeneralNeeds");
        }
        return multipleGeneralNeedsList;
    }

    public ArrayList getPersonalDetailsAnalysis(DataSource datasource, FunctionalNeedReportDTO functionalNeedDTO,
            int start, int endrange, String columnName, String columnValue, String tableName) throws SADAREMDBException, SQLException {

        ArrayList<FunctionalNeedReportDTO> functionalNeedPersonalList = null;
        functionalNeedPersonalList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            functionalNeedPersonalList = distictFunctionalNeedDAO.getPersonalDetailsAnalysis(datasource, functionalNeedDTO,
                    start, endrange, columnName, columnValue, tableName);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonalDetailsAnalysis", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getPersonalDetailsAnalysis");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonalDetailsAnalysis", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getPersonalDetailsAnalysis");
        }
        return functionalNeedPersonalList;
    }

    public ArrayList getPersonalDetailsTwoColumns(DataSource datasource, FunctionalNeedReportDTO functionalNeedDTO,
            int start, int endrange, String columnNameOne, String columnValueOne,
            String columnNameTwo, String columnValueTwo, String tableName) throws SADAREMDBException, SQLException {

        ArrayList<FunctionalNeedReportDTO> functionalNeedPersonalList = null;
        functionalNeedPersonalList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            functionalNeedPersonalList = distictFunctionalNeedDAO.getPersonalDetailsTwoColumns(datasource, functionalNeedDTO,
                    start, endrange, columnNameOne, columnValueOne, columnNameTwo, columnValueTwo, tableName);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonalDetailsTwoColumns", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getPersonalDetailsTwoColumns");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonalDetailsTwoColumns", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getPersonalDetailsTwoColumns");
        }
        return functionalNeedPersonalList;
    }

    public ArrayList getNotComeToSadaremCamp(DataSource ds, String districtID, String phase)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> notCometoCampList = null;
        notCometoCampList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO notCometoCampDAO =
                new FunctionalNeedReportDAO();
        try {
            notCometoCampList =
                    notCometoCampDAO.getNotComeToSadaremCamp(ds, districtID, phase);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getNotComeToSadaremCamp", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getNotComeToSadaremCamp");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getNotComeToSadaremCamp", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getNotComeToSadaremCamp");
        }
        return notCometoCampList;
    }

    public ArrayList getNotComeToCampPersonalDetails(DataSource ds, String districtID, String mandalID, String phase, String reportType)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> notCometoCampPersonalList = null;
        notCometoCampPersonalList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO notCometoCampDAO =
                new FunctionalNeedReportDAO();
        try {
            notCometoCampPersonalList =
                    notCometoCampDAO.getNotComeToCampPersonalDetails(ds, districtID, mandalID, phase, reportType);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getNotComeToCampPersonalDetails", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getNotComeToCampPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getNotComeToCampPersonalDetails", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getNotComeToCampPersonalDetails");
        }
        return notCometoCampPersonalList;
    }

    public ArrayList getPersonalDetailsSurgery(DataSource datasource, FunctionalNeedReportDTO functionalNeedDTO,
            String columnName, String tableName) throws SADAREMDBException, SQLException {

        ArrayList<FunctionalNeedReportDTO> functionalNeedPersonalList = null;
        functionalNeedPersonalList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            functionalNeedPersonalList = distictFunctionalNeedDAO.getPersonalDetailsSurgery(datasource, functionalNeedDTO,
                    columnName, tableName);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonalDetailsSurgery", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getPersonalDetailsSurgery");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonalDetailsSurgery", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getPersonalDetailsSurgery");
        }
        return functionalNeedPersonalList;
    }

    public ArrayList getpersonalGeneralNeeds(DataSource datasource, FunctionalNeedReportDTO functionalNeedDTO,
            int start, int endrange, String columnName, String columnValue, String tableName) throws SADAREMDBException, SQLException {

        ArrayList<FunctionalNeedReportDTO> generalNeedPersonalList = null;
        generalNeedPersonalList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            generalNeedPersonalList = distictFunctionalNeedDAO.getpersonalGeneralNeeds(datasource, functionalNeedDTO,
                    start, endrange, columnName, columnValue, tableName);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getpersonalGeneralNeeds", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getpersonalGeneralNeeds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getpersonalGeneralNeeds", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getpersonalGeneralNeeds");
        }
        return generalNeedPersonalList;
    }

    public ArrayList getpersonalGeneralNeedsTotal(DataSource datasource, FunctionalNeedReportDTO functionalNeedDTO,
            int start, int endrange, String columnName, String columnValue, String tableName) throws SADAREMDBException, SQLException {

        ArrayList<FunctionalNeedReportDTO> generalNeedPersonalList = null;
        generalNeedPersonalList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            generalNeedPersonalList = distictFunctionalNeedDAO.getpersonalGeneralNeeds(datasource, functionalNeedDTO,
                    start, endrange, columnName, columnValue, tableName);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getpersonalGeneralNeedsTotal", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getpersonalGeneralNeedsTotal");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getpersonalGeneralNeedsTotal", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getpersonalGeneralNeedsTotal");
        }
        return generalNeedPersonalList;
    }

    public ArrayList getEducationWiseReport(DataSource ds, FunctionalNeedReportDTO educationwiseDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> educationwiseList = null;
        educationwiseList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalReportDAO educationwiseDAO =
                new FunctionalReportDAO();
        try {
            educationwiseList =
                    educationwiseDAO.getEducationWiseReport(ds, educationwiseDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getEducationWiseReport", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getEducationWiseReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getEducationWiseReport", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getEducationWiseReport");
        }
        return educationwiseList;
    }

    public ArrayList getPersonalEducationWiseReport(DataSource ds, String district_id, String mandal_id, String village_id, String hab_id, String education,String urbanId)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> educationwiseList = null;
        educationwiseList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO educationwiseDAO =
                new FunctionalNeedReportDAO();
        try {
            educationwiseList =
                    educationwiseDAO.getPersonEducationWiseReport(ds, district_id, mandal_id, village_id, hab_id, education,urbanId);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPersonalEducationWiseReport", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getPersonalEducationWiseReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPersonalEducationWiseReport", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getPersonalEducationWiseReport");
        }
        return educationwiseList;
    }

    public ArrayList getAreaDetails(DataSource ds, String district_id, String mandal_id, String village_id, String hab_id)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> educationwiseList = null;
        educationwiseList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO educationwiseDAO =
                new FunctionalNeedReportDAO();
        try {
            educationwiseList =
                    educationwiseDAO.getAreaDetails(ds, district_id, mandal_id, village_id, hab_id);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAreaDetails", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAreaDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAreaDetails", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAreaDetails");
        }
        return educationwiseList;
    }

    public ArrayList getCasteWiseDetails(DataSource ds, String district_id, String mandal_id, String village_id, String caste, FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> casteWiseList = null;
        casteWiseList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalReportDAO casteWiseDAO =
                new FunctionalReportDAO();
        try {
            casteWiseList =
                    casteWiseDAO.getCasteWiseDetails(ds, district_id, mandal_id, village_id, caste, functionalNeedReportDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCasteWiseDetails", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getCasteWiseDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCasteWiseDetails", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getCasteWiseDetails");
        }

        return casteWiseList;
    }

    public ArrayList getPersonalCasteWiseDetails(DataSource ds, String district_id, String mandal_id, String village_id, String habCode, String caste,String urbanId,String fromDate,String todate)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> casteWiseList = null;
        casteWiseList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO casteWiseDAO =
                new FunctionalNeedReportDAO();
        try {
            casteWiseList =
                    casteWiseDAO.getPersonalCasteWiseDetails(ds, district_id, mandal_id, village_id, habCode, caste,urbanId,fromDate,todate);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPersonalCasteWiseDetails", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getPersonalCasteWiseDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPersonalCasteWiseDetails", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getPersonalCasteWiseDetails");
        }
        

       
        return casteWiseList;
    }

    public ArrayList getAgeDetails(DataSource ds, String district_id, String mandal_id, String village, String fromAge, String toAge, FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> casteWiseList = null;
        casteWiseList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalReportDAO casteWiseDAO =
                new FunctionalReportDAO();
        try {
            casteWiseList =
                    casteWiseDAO.getAgeDetails(ds, district_id, mandal_id, village, fromAge, toAge, functionalNeedReportDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAgeDetails", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAgeDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAgeDetails", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAgeDetails");
        }



        return casteWiseList;
    }

    public ArrayList getPersonalAgeWiseDetails(DataSource ds, String district_id, String mandal_id, String village, String habCode, String gender, String fromAge, String toAge,String urbanId,String fromDate, String todate)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> casteWiseList = null;
        casteWiseList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO casteWiseDAO =
                new FunctionalNeedReportDAO();
        try {
            casteWiseList =
                    casteWiseDAO.getPersonalAgeWiseDetails(ds, district_id, mandal_id, village, habCode, gender, fromAge, toAge,urbanId,fromDate,todate);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPersonalAgeWiseDetails", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getPersonalAgeWiseDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPersonalAgeWiseDetails", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getPersonalAgeWiseDetails");
        }

       
        return casteWiseList;
    }

    public ArrayList getMandals(DataSource datasource, String districtid, String urbanId) throws SADAREMDBException, SQLException {
        ArrayList districtlist = new ArrayList();
        FunctionalNeedReportDAO casteWiseDAO =
                new FunctionalNeedReportDAO();
        try {
            districtlist = casteWiseDAO.getMandals(datasource, districtid, urbanId);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMandals", "FunctionalNeedServiceImpl", "DataBase");
          throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMandals");
        } catch (Exception sqlEx) {
          int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMandals", "FunctionalNeedServiceImpl", "Code");
           throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMandals");
        }
        return districtlist;
    }

    public ArrayList getMandalsScheduleMandals(DataSource datasource, String districtid) throws SADAREMDBException, SQLException {
        ArrayList districtlist = new ArrayList();
        FunctionalNeedReportDAO casteWiseDAO =
                new FunctionalNeedReportDAO();
        try {
            districtlist = casteWiseDAO.getMandalsScheduleMandals(datasource, districtid);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMandalsScheduleMandals", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMandalsScheduleMandals");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMandalsScheduleMandals", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMandalsScheduleMandals");
        }
        return districtlist;
    }

    public ArrayList getVillageAll(DataSource datasource, String districeId, String mandalId) throws SADAREMDBException, SQLException {
        ArrayList personStatusList = new ArrayList();
        FunctionalNeedReportDAO casteWiseDAO =
                new FunctionalNeedReportDAO();
        try {
            personStatusList = casteWiseDAO.getVillageAll(datasource, districeId, mandalId);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVillageAll", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getVillageAll");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVillageAll", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getVillageAll");
        }
        return personStatusList;
    }

    public ArrayList getScheduleVillages(DataSource datasource, String districeId, String mandalId) throws SADAREMDBException, SQLException {
        ArrayList personStatusList = new ArrayList();
        FunctionalNeedReportDAO casteWiseDAO =
                new FunctionalNeedReportDAO();
        try {
            personStatusList = casteWiseDAO.getScheduleVillages(datasource, districeId, mandalId);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getScheduleVillages", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getScheduleVillages");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getScheduleVillages", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getScheduleVillages");
        }
        return personStatusList;
    }

    /*  public ArrayList getAppealVillageAll(DataSource datasource, String districeId, String mandalId) throws SADAREMDBException,SQLException {
    ArrayList personStatusList = new ArrayList();
    FunctionalNeedReportDAO casteWiseDAO =
    new FunctionalNeedReportDAO();
    try {
    personStatusList = casteWiseDAO.getAppealVillageAll(datasource, districeId, mandalId);
    } catch (SADAREMException sADAREMException) {
    sADAREMException.printStackTrace();
    throw new SADAREMException();
    } catch (Exception exception) {
    exception.printStackTrace();
    throw new SADAREMException();
    }
    return personStatusList;
    }*/
    public ArrayList alreadyEnteredVillagesAll(DataSource datasource, ArrayList villages, String districtid, String mandalid, String fromDate) throws SADAREMDBException, SQLException {
        ArrayList personStatusList = new ArrayList();
        FunctionalNeedReportDAO casteWiseDAO =
                new FunctionalNeedReportDAO();
        try {
            personStatusList = casteWiseDAO.alreadyEnteredVillagesAll(datasource, villages, districtid, mandalid, fromDate);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "alreadyEnteredVillagesAll", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "alreadyEnteredVillagesAll");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "alreadyEnteredVillagesAll", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "alreadyEnteredVillagesAll");
        }
        return personStatusList;
    }

    /*  public ArrayList getCasteWiseSingleDetails(DataSource ds, String district_id, String mandal_id, String village_id, String caste)
    throws SADAREMDBException,SQLException {
    ArrayList<FunctionalNeedReportDTO> casteWiseList = null;
    casteWiseList = new ArrayList<FunctionalNeedReportDTO>();
    FunctionalNeedReportDAO casteWiseDAO =
    new FunctionalNeedReportDAO();
    try {
    casteWiseList =
    casteWiseDAO.getCasteWiseSingleDetails(ds, district_id, mandal_id, village_id, caste);
    } catch (SADAREMException sADAREMException) {
    sADAREMException.printStackTrace();
    throw new SADAREMException();
    } catch (Exception exception) {
    exception.printStackTrace();
    throw new SADAREMException();
    }
    return casteWiseList;
    }*/
    public ArrayList getMaritalStatusDetails(DataSource ds, String district_id, String mandal_id, String village_id, String habCode, FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> casteWiseList = null;
        casteWiseList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalReportDAO casteWiseDAO =
                new FunctionalReportDAO();
        try {
            casteWiseList =
                    casteWiseDAO.getMaritalStatusDetails(ds, district_id, mandal_id, village_id, habCode, functionalNeedReportDTO);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
          //  int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMaritalStatusDetails", "FunctionalNeedServiceImpl", "DataBase");
           // throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMaritalStatusDetails");
        } catch (Exception sqlEx) {
           // int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMaritalStatusDetails", "FunctionalNeedServiceImpl", "Code");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMaritalStatusDetails");
        }
        return casteWiseList;
    }

    public ArrayList getMaritalStatusPersonalDetails(DataSource ds, String district_id, String mandal_id, String village_id, String habCode, String mstatus,String urbanId,String fromDate, String todate)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> casteWiseList = null;
        casteWiseList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO casteWiseDAO =
                new FunctionalNeedReportDAO();
        try {
            casteWiseList =
                    casteWiseDAO.getMaritalStatusPersonalDetails(ds, district_id, mandal_id, village_id, habCode, mstatus,urbanId,fromDate,todate);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMaritalStatusPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getMaritalStatusPersonalDetails");
        }
        return casteWiseList;
    }

    public ArrayList getParentsMaritalStatusDetails(DataSource ds, String district_id, String mandal_id, String village_id, String habCode, FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> casteWiseList = null;
        casteWiseList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO casteWiseDAO =
                new FunctionalNeedReportDAO();
        try {
            casteWiseList =
                    casteWiseDAO.getParentsMaritalStatusDetails(ds, district_id, mandal_id, village_id, habCode, functionalNeedReportDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getParentsMaritalStatusDetails", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getParentsMaritalStatusDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getParentsMaritalStatusDetails", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getParentsMaritalStatusDetails");
        }
        return casteWiseList;
    }

    public ArrayList getParentsMaritalIndivdualDetails(DataSource ds, String district_id, String mandal_id, String village_id, String habCode, String parentStatus,String fromDate,String todate)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> casteWiseList = null;
        casteWiseList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO casteWiseDAO =
                new FunctionalNeedReportDAO();
        try {
            casteWiseList =
                    casteWiseDAO.getParentsMaritalIndivdualDetails(ds, district_id, mandal_id, village_id, habCode, parentStatus,fromDate,todate);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getParentsMaritalIndivdualDetails", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getParentsMaritalIndivdualDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getParentsMaritalIndivdualDetails", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getParentsMaritalIndivdualDetails");
        }
        return casteWiseList;
    }

    /**Added by mohan on 18/08/2011*/
    public int insertVillages(DataSource ds, ArrayList villages, String district_id, String mandal_id, String fromDate, String loginID, String systemIP)
            throws SADAREMDBException, SQLException {
        int casteWiseList = 0;
        //casteWiseList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            casteWiseList = distictFunctionalNeedDAO.insertVillages(ds, villages, district_id, mandal_id, fromDate, loginID, systemIP);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertVillages", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "insertVillages");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertVillages", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "insertVillages");
        }
        return casteWiseList;
    }

    public int insertVillage(DataSource ds, String district_id, String mandal_id, String village_id, String fromDate, String loginID, String systemIP)
            throws SADAREMDBException, SQLException {
        int casteWiseList = 0;
        //casteWiseList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            casteWiseList = distictFunctionalNeedDAO.insertVillage(ds, district_id, mandal_id, village_id, fromDate, loginID, systemIP);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertVillage", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "insertVillage");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertVillage", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "insertVillage");
        }
        return casteWiseList;
    }

    public ArrayList alreadyEnteredVillages(DataSource ds, String district_id, String mandal_id, String village_id, String date)
            throws SADAREMDBException, SQLException {
        ArrayList<FunctionalNeedReportDTO> medicalInterventionList = null;
        medicalInterventionList = new ArrayList<FunctionalNeedReportDTO>();
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            medicalInterventionList =
                    distictFunctionalNeedDAO.alreadyEnteredVillages(ds, district_id, mandal_id, village_id, date);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "alreadyEnteredVillages", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "alreadyEnteredVillages");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "alreadyEnteredVillages", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "alreadyEnteredVillages");
        }
        return medicalInterventionList;
    }

    public String checkConditions(DataSource ds, String district_id, String mandal_id, String village_id, String date)
            throws SADAREMDBException, SQLException {
        String medicalInterventionList = null;
        FunctionalNeedReportDAO distictFunctionalNeedDAO =
                new FunctionalNeedReportDAO();
        try {
            medicalInterventionList =
                    distictFunctionalNeedDAO.checkConditions(ds, district_id, mandal_id, village_id, date);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkConditions", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "checkConditions");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkConditions", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "checkConditions");
        }
        return medicalInterventionList;
    }

    public ArrayList getRachaBandaData(DataSource ds, String district_id) throws SADAREMDBException, SQLException {

        ArrayList rachaBandaData = new ArrayList();
        FunctionalNeedReportDAO getRachabandaData = new FunctionalNeedReportDAO();
        try {
            rachaBandaData = getRachabandaData.rachaBandaData(ds, district_id);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getRachaBandaData", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getRachaBandaData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getRachaBandaData", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getRachaBandaData");
        }

        return rachaBandaData;
    }

    public ArrayList getRachaBandaPersonalDetails(DataSource ds, String district_id, String mandal_id, String modeStatus) throws SADAREMDBException, SQLException {

        ArrayList rachaBandaDataPersonal = new ArrayList();
        FunctionalNeedReportDAO getRachabandaData = new FunctionalNeedReportDAO();
        try {
            rachaBandaDataPersonal = getRachabandaData.getRachaBandaPersonalDetails(ds, district_id, mandal_id, modeStatus);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getRachaBandaPersonalDetails", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getRachaBandaPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getRachaBandaPersonalDetails", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getRachaBandaPersonalDetails");
        }
        return rachaBandaDataPersonal;
    }

    public ArrayList getAppealPersonalDetails(DataSource ds, String district_id, String mandal_id, String village_id, String fromDate, String toDate, String disabilityStatus, String disability, String baseDistrict, String baseMandal, String baseVillage, String basehab, String pensionPhase) throws SADAREMDBException, SQLException {

        ArrayList rachaBandaDataPersonal = new ArrayList();
        FunctionalReportDAO getRachabandaData = new FunctionalReportDAO();
        try {
            rachaBandaDataPersonal = getRachabandaData.getAppealPersonalDetails(ds, district_id, mandal_id, village_id, fromDate, toDate, disabilityStatus, disability, baseDistrict, baseMandal, baseVillage, basehab, pensionPhase);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAppealPersonalDetails", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAppealPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAppealPersonalDetails", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getAppealPersonalDetails");
        }

        return rachaBandaDataPersonal;
    }

    public ArrayList getRationPersonalReportDetails(DataSource ds, String district_id, String mandal_id, String village_id) throws SADAREMDBException, SQLException {

        ArrayList rationCardPersonalDetails = new ArrayList();
        FunctionalReportDAO getPersonalData = new FunctionalReportDAO();
        try {
            rationCardPersonalDetails = getPersonalData.getRationPersonalReportDetails(ds, district_id, mandal_id, village_id);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getRationPersonalReportDetails", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getRationPersonalReportDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getRationPersonalReportDetails", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getRationPersonalReportDetails");
        }

        return rationCardPersonalDetails;
    }

    public ArrayList campDetails(DataSource ds, String district_id) throws SADAREMDBException, SQLException {

        ArrayList rationCardPersonalDetails = new ArrayList();
        FunctionalReportDAO campDetails = new FunctionalReportDAO();
        try {
            rationCardPersonalDetails = campDetails.getCampDetails(ds, district_id);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "campDetails", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "campDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "campDetails", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "campDetails");
        }

        return rationCardPersonalDetails;
    }

    public ArrayList getCampDailyReportDetails(DataSource ds, String district_id) throws SADAREMDBException, SQLException {


        ArrayList campList = new ArrayList();

        FunctionalReportDAO functionalNeedReportDAO = new FunctionalReportDAO();
        try {

            campList = functionalNeedReportDAO.getCampDailyReportDetails(ds, district_id);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCampDailyReportDetails", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getCampDailyReportDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCampDailyReportDetails", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getCampDailyReportDetails");
        }
        return campList;

    }

    public ArrayList getDisabilityDetails(DataSource ds) throws SADAREMDBException, SQLException {

        ArrayList DisabilityList = new ArrayList();
        FunctionalReportDAO functionalNeedReportDAO = new FunctionalReportDAO();

        try {
            DisabilityList = functionalNeedReportDAO.getDisabilityDetails(ds);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDisabilityDetails", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getDisabilityDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDisabilityDetails", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getDisabilityDetails");
        }
        return DisabilityList;
    }

    public int insertPwdAssessementDetails(DataSource ds, CampDailyReportForm campDailyReportForm, String districtid, String loginID, String systemIP) throws SADAREMDBException, SQLException {

        int i = 0;
        FunctionalReportDAO functionalNeedReportDAO = new FunctionalReportDAO();

        try {

            i = functionalNeedReportDAO.insertPwdAssessementDetails(ds, campDailyReportForm, districtid, loginID, systemIP);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertPwdAssessementDetails", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "insertPwdAssessementDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertPwdAssessementDetails", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "insertPwdAssessementDetails");
        }
        return i;
    }

    public ArrayList getNotComeToCampPersonalDetailsTotal(DataSource ds, String districtID, String phaseName, String reportType) throws SADAREMDBException, SQLException {

        ArrayList DisabilityList = new ArrayList();
        FunctionalReportDAO functionalNeedReportDAO = new FunctionalReportDAO();

        try {
            DisabilityList = functionalNeedReportDAO.getNotComeToCampPersonalDetailsTotal(ds, districtID, phaseName, reportType);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getNotComeToCampPersonalDetailsTotal", "FunctionalNeedServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getNotComeToCampPersonalDetailsTotal");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getNotComeToCampPersonalDetailsTotal", "FunctionalNeedServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedServiceImpl", "getNotComeToCampPersonalDetailsTotal");
        }
        return DisabilityList;
    }

    public String getAreaName(DataSource ds,String districtid, String mandalid, String villageid) {

        String areaname=null;
        try {
            FunctionalReportDAO dao = new FunctionalReportDAO();
            areaname=dao.getAreaName(ds,districtid,mandalid,villageid);
        } catch (Exception e) {
        }

        return areaname;
    }

    
}
