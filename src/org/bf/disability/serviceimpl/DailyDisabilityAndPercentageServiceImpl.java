/*
 * DailyDisabilityAndPercentageServiceImpl.java
 *
 * Created on November 25, 2008, 3:14 PM
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
import org.bf.disability.dao.DailyDisabilityAndPercentageDAO;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dto.AssessedPWDDetailsDTO;
import org.bf.disability.dto.DailyDisabilityAndPercentageDTO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.service.DailyDisabilityAndPercentageService;

/**
 * This class has the implementation functionality for
 * DailyDisabilityAdnPercentageService interface.
 * @author SVS Ganesh
 * @version 1.0
 */
public class DailyDisabilityAndPercentageServiceImpl implements DailyDisabilityAndPercentageService {

    /** Creates a new instance of DailyDisabilityAndPercentageServiceImpl */
    public DailyDisabilityAndPercentageServiceImpl() {
    }

    public ArrayList getDisabilityWise(DataSource ds,
            DailyDisabilityAndPercentageDTO dailyreportdto)
            throws SADAREMDBException, SQLException {
        ArrayList disabilitylist = new ArrayList();
        DailyDisabilityAndPercentageDAO dailydisabilityandpercentagedao =
                new DailyDisabilityAndPercentageDAO();
        try {
            disabilitylist = dailydisabilityandpercentagedao.getDisabilityWise(ds, dailyreportdto);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDisabilityWise", "DailyDisabilityAndPercentageServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "getDisabilityWise");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDisabilityWise", "DailyDisabilityAndPercentageServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "getDisabilityWise");
        }
        return disabilitylist;
    }

    public ArrayList getDisabilityPercentage(DataSource ds,
            DailyDisabilityAndPercentageDTO dailyreportdto)
            throws SADAREMDBException, SQLException {
        ArrayList percentagelist = new ArrayList();
        DailyDisabilityAndPercentageDAO dailydisabilityandpercentagedao =
                new DailyDisabilityAndPercentageDAO();
        try {
            percentagelist = dailydisabilityandpercentagedao.getDisabilityPercentage(ds, dailyreportdto);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDisabilityPercentage", "DailyDisabilityAndPercentageServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "getDisabilityPercentage");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDisabilityPercentage", "DailyDisabilityAndPercentageServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "getDisabilityPercentage");
        }
        return percentagelist;

    }

    public ArrayList getPersonPersonalDetailswise(DataSource ds,
            PartADTO partADTO, String districtid)
            throws SADAREMDBException, SQLException {
        ArrayList personallist = new ArrayList();
        DailyDisabilityAndPercentageDAO dailydisabilityandpercentagedao =
                new DailyDisabilityAndPercentageDAO();
        try {
            personallist = dailydisabilityandpercentagedao.getPersonPersonalDetailswise(ds, partADTO, districtid);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPersonPersonalDetailswise", "DailyDisabilityAndPercentageServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "getPersonPersonalDetailswise");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPersonPersonalDetailswise", "DailyDisabilityAndPercentageServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "getPersonPersonalDetailswise");
        }
        return personallist;

    }

    public ArrayList getReportForLoginWiseCount(DataSource ds,
            PartADTO partADTO, String districtid)
            throws SADAREMDBException, SQLException {
        ArrayList loginwisecountlist = new ArrayList();
        DailyDisabilityAndPercentageDAO dailydisabilityandpercentagedao =
                new DailyDisabilityAndPercentageDAO();
        try {
            loginwisecountlist = dailydisabilityandpercentagedao.getReportForLoginWiseCount(ds, partADTO, districtid);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getReportForLoginWiseCount", "DailyDisabilityAndPercentageServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "getReportForLoginWiseCount");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getReportForLoginWiseCount", "DailyDisabilityAndPercentageServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "getReportForLoginWiseCount");
        }
        return loginwisecountlist;

    }

    public ArrayList getStatusCountForMandal(DataSource ds,
            PartADTO partADTO, String districtid)
            throws SADAREMDBException, SQLException {
        ArrayList statuscountformandallist = new ArrayList();
        DailyDisabilityAndPercentageDAO dailydisabilityandpercentagedao =
                new DailyDisabilityAndPercentageDAO();
        try {
            statuscountformandallist = dailydisabilityandpercentagedao.getStatusCountForMandal(ds, partADTO, districtid);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getStatusCountForMandal", "DailyDisabilityAndPercentageServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "getStatusCountForMandal");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getStatusCountForMandal", "DailyDisabilityAndPercentageServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "getStatusCountForMandal");
        }
        return statuscountformandallist;

    }

    public ArrayList getStatusCountForVillage(DataSource ds,
            PartADTO partADTO, String districtid, String mandalid)
            throws SADAREMDBException, SQLException {
        ArrayList statuscountforvillagelist = new ArrayList();
        DailyDisabilityAndPercentageDAO dailydisabilityandpercentagedao =
                new DailyDisabilityAndPercentageDAO();
        try {
            statuscountforvillagelist = dailydisabilityandpercentagedao.getStatusCountForVillage(ds, partADTO, districtid, mandalid);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getStatusCountForVillage", "DailyDisabilityAndPercentageServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "getStatusCountForVillage");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getStatusCountForVillage", "DailyDisabilityAndPercentageServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "getStatusCountForVillage");
        }
        return statuscountforvillagelist;

    }

    public ArrayList getStatusCountForPartA(DataSource ds,
            PartADTO partADTO, String districtid)
            throws SADAREMDBException, SQLException {
        ArrayList statuscountforpartalist = new ArrayList();
        DailyDisabilityAndPercentageDAO dailydisabilityandpercentagedao =
                new DailyDisabilityAndPercentageDAO();
        try {
            statuscountforpartalist = dailydisabilityandpercentagedao.getStatusCountForPartA(ds, partADTO, districtid);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getStatusCountForPartA", "DailyDisabilityAndPercentageServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "getStatusCountForPartA");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getStatusCountForPartA", "DailyDisabilityAndPercentageServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "getStatusCountForPartA");
        }
        return statuscountforpartalist;

    }

    public ArrayList distWisestatusreportforPartA(DataSource ds,
            PartADTO partADTO, String districtid)
            throws SADAREMDBException, SQLException {
        ArrayList statuscountforpartalist = new ArrayList();
        DailyDisabilityAndPercentageDAO dailydisabilityandpercentagedao =
                new DailyDisabilityAndPercentageDAO();
        try {
            statuscountforpartalist = dailydisabilityandpercentagedao.distWisestatusreportforPartA(ds, partADTO, districtid);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "distWisestatusreportforPartA", "DailyDisabilityAndPercentageServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "distWisestatusreportforPartA");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "distWisestatusreportforPartA", "DailyDisabilityAndPercentageServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "distWisestatusreportforPartA");
        }
        return statuscountforpartalist;

    }

    public ArrayList distWisestatusreportforPartB(DataSource ds,
            PartADTO partADTO, String districtid)
            throws SADAREMDBException, SQLException {
        ArrayList statuscountforpartalist = new ArrayList();
        DailyDisabilityAndPercentageDAO dailydisabilityandpercentagedao =
                new DailyDisabilityAndPercentageDAO();
        try {
            statuscountforpartalist = dailydisabilityandpercentagedao.distWisestatusreportforPartB(ds, partADTO, districtid);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "distWisestatusreportforPartB", "DailyDisabilityAndPercentageServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "distWisestatusreportforPartB");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "distWisestatusreportforPartB", "DailyDisabilityAndPercentageServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "distWisestatusreportforPartB");
        }
        return statuscountforpartalist;

    }

    public ArrayList mandalWisestatusreportforPartB(DataSource ds,
            PartADTO partADTO, String districtid)
            throws SADAREMDBException, SQLException {
        ArrayList statuscountforpartblist = new ArrayList();
        DailyDisabilityAndPercentageDAO dailydisabilityandpercentagedao =
                new DailyDisabilityAndPercentageDAO();
        try {
            statuscountforpartblist = dailydisabilityandpercentagedao.mandalWisestatusreportforPartB(ds, partADTO, districtid);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "mandalWisestatusreportforPartB", "DailyDisabilityAndPercentageServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "mandalWisestatusreportforPartB");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "mandalWisestatusreportforPartB", "DailyDisabilityAndPercentageServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "mandalWisestatusreportforPartB");
        }
        return statuscountforpartblist;

    }

    public ArrayList villageWiseStatusReportForPartB(DataSource ds,
            PartADTO partADTO, String districtid, String mandalid)
            throws SADAREMDBException, SQLException {
        ArrayList villagewisereportforpartB = new ArrayList();
        DailyDisabilityAndPercentageDAO dailydisabilityandpercentagedao =
                new DailyDisabilityAndPercentageDAO();
        try {
            villagewisereportforpartB = dailydisabilityandpercentagedao.villageWiseStatusReportForPartB(ds, partADTO, districtid, mandalid);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "villageWiseStatusReportForPartB", "DailyDisabilityAndPercentageServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "villageWiseStatusReportForPartB");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "villageWiseStatusReportForPartB", "DailyDisabilityAndPercentageServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "villageWiseStatusReportForPartB");
        }
        return villagewisereportforpartB;
    }

    public ArrayList statusreportforPartB(DataSource ds, PartADTO partBDTO)
            throws SADAREMDBException, SQLException {
        ArrayList<PartADTO> partBList = null;
        partBList = new ArrayList<PartADTO>();
        DailyDisabilityAndPercentageDAO partBDAO =
                new DailyDisabilityAndPercentageDAO();
        try {
            partBList =
                    partBDAO.statusreportforPartB(ds, partBDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "statusreportforPartB", "DailyDisabilityAndPercentageServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "statusreportforPartB");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "statusreportforPartB", "DailyDisabilityAndPercentageServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "statusreportforPartB");
        }
        return partBList;
    }

//Added by mohan on 05/07/2011
    public ArrayList statusReportforPartBAssessed(DataSource ds, String district_id, String mandal_id, String village_id, String phase, String header)
            throws SADAREMDBException, SQLException {
        ArrayList<PartADTO> partBList = null;
        partBList = new ArrayList<PartADTO>();
        DailyDisabilityAndPercentageDAO partBDAO =
                new DailyDisabilityAndPercentageDAO();
        try {
            partBList =
                    partBDAO.statusReportforPartBAssessed(ds, district_id, mandal_id, village_id, phase, header);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "statusReportforPartBAssessed", "DailyDisabilityAndPercentageServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "statusReportforPartBAssessed");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "statusReportforPartBAssessed", "DailyDisabilityAndPercentageServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "statusReportforPartBAssessed");
        }
        return partBList;
    }
//kavya

    public ArrayList statusreportforPartBModified(DataSource ds, AssessedPWDDetailsDTO assessedPWDDetailsDTO) throws SADAREMDBException, SQLException{
        ArrayList partBList = null;
        try {
            partBList = new ArrayList();
            DailyDisabilityAndPercentageDAO partBDAO = new DailyDisabilityAndPercentageDAO();
            partBList = partBDAO.statusreportforPartBModified(ds, assessedPWDDetailsDTO);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "statusreportforPartBModified", "DailyDisabilityAndPercentageServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "statusreportforPartBModified");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "statusreportforPartBModified", "DailyDisabilityAndPercentageServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "statusreportforPartBModified");
        }
        return partBList;
    }

    public ArrayList setDistrictList(DataSource ds, AssessedPWDDetailsDTO assessedPWDDetailsDTO) throws SADAREMDBException, SQLException {
        ArrayList distList = new ArrayList();
        try {

            DailyDisabilityAndPercentageDAO partBDAO = new DailyDisabilityAndPercentageDAO();
            distList = partBDAO.setDistrictList(ds, assessedPWDDetailsDTO);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "setDistrictList", "DailyDisabilityAndPercentageServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "setDistrictList");
        }
        return distList;
    }

    public ArrayList setMandalList(DataSource ds, AssessedPWDDetailsDTO assessedPWDDetailsDTO) throws SADAREMDBException, SQLException{
        ArrayList mandalList = new ArrayList();
        try {

            DailyDisabilityAndPercentageDAO partBDAO = new DailyDisabilityAndPercentageDAO();
            mandalList = partBDAO.setMandalList(ds, assessedPWDDetailsDTO);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "setMandalList", "DailyDisabilityAndPercentageServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "setMandalList");
        }
        return mandalList;
    }

    public ArrayList setPanchayatList(DataSource ds, AssessedPWDDetailsDTO assessedPWDDetailsDTO) throws SADAREMDBException, SQLException{
        ArrayList panchayatList = new ArrayList();
        try {

            DailyDisabilityAndPercentageDAO partBDAO = new DailyDisabilityAndPercentageDAO();
            panchayatList = partBDAO.setPanchayatList(ds, assessedPWDDetailsDTO);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "setPanchayatList", "DailyDisabilityAndPercentageServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "setPanchayatList");
        }
        return panchayatList;
    }

    public ArrayList setVillageList(DataSource ds, AssessedPWDDetailsDTO assessedPWDDetailsDTO) throws SADAREMDBException, SQLException{
        ArrayList villList = new ArrayList();
        try {

            DailyDisabilityAndPercentageDAO partBDAO = new DailyDisabilityAndPercentageDAO();
            villList = partBDAO.setVillageList(ds, assessedPWDDetailsDTO);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "setVillageList", "DailyDisabilityAndPercentageServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "setVillageList");
        }
        return villList;
    }

    public ArrayList getAllDataByMandal(DataSource ds,String districtId, String mandalId) throws SADAREMDBException, SQLException{
        ArrayList allData = new ArrayList();
        try {

            DailyDisabilityAndPercentageDAO partBDAO = new DailyDisabilityAndPercentageDAO();
            allData = partBDAO.getAllDataByMandal(ds,districtId, mandalId);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAllDataByMandal", "DailyDisabilityAndPercentageServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageServiceImpl", "getAllDataByMandal");
        }
        return allData;
    }
//end of kavya
}
