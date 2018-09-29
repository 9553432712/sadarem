/*
 * CommonReportServiceImpl.java
 *
 * Created on December 8, 2008, 10:24 AM
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
import org.bf.disability.dao.CommonReportDAO;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dto.CommonReportDTO;
import org.bf.disability.service.CommonReportService;

/**
 *
 * @author svsganesh
 */
public class CommonReportServiceImpl implements CommonReportService {
    
    public ArrayList getDistrictReport(DataSource ds,
            CommonReportDTO surgeryreportdto, String Surgerytype)
            throws SADAREMDBException,SQLException{
        ArrayList physioreportlist=new ArrayList();
        CommonReportDAO surgeryreportdao=new CommonReportDAO();
        try{
        physioreportlist=surgeryreportdao.getDistrictReport
                (ds,surgeryreportdto,Surgerytype);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDistrictReport", "CommonReportServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportServiceImpl", "getDistrictReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDistrictReport", "CommonReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportServiceImpl", "getDistrictReport");
        }
        return physioreportlist;
        
    }
    public ArrayList getMandalReport
            (DataSource datasource,CommonReportDTO surgeryreportdto,
            String Surgerytype) throws SADAREMDBException,SQLException{
        ArrayList physioreportlist=new ArrayList();
        CommonReportDAO surgeryreportdao=new CommonReportDAO();
        try{
        physioreportlist=surgeryreportdao.getMandalReport
                (datasource,surgeryreportdto,Surgerytype);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMandalReport", "CommonReportServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportServiceImpl", "getMandalReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMandalReport", "CommonReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportServiceImpl", "getMandalReport");
        }
        return physioreportlist;
        
    }
    
    public ArrayList getVillageReport(DataSource datasource,
            CommonReportDTO commonreportdto,String surgerytype)
            throws SADAREMDBException,SQLException {
        ArrayList villagelist=new ArrayList();
        CommonReportDAO surgeryreportdao=new CommonReportDAO();
        try{
        villagelist=surgeryreportdao.getVillageReport
                (datasource,commonreportdto,surgerytype);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVillageReport", "CommonReportServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportServiceImpl", "getVillageReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVillageReport", "CommonReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportServiceImpl", "getVillageReport");
        }
        return villagelist;
        
    }
    public ArrayList getHabitationReport(DataSource datasource,
            CommonReportDTO  commonreportdto,String surgerytype)
            throws SADAREMDBException,SQLException {
        ArrayList habitationlist=new ArrayList();
        CommonReportDAO surgeryreportdao=new CommonReportDAO();
        try{
        habitationlist=surgeryreportdao.getHabitationReport
                (datasource,commonreportdto,surgerytype);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHabitationReport", "CommonReportServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportServiceImpl", "getHabitationReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHabitationReport", "CommonReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportServiceImpl", "getHabitationReport");
        }
        return habitationlist;
    }

    public ArrayList getNiramayaDistrictReport(DataSource datasource)
            throws SADAREMDBException,SQLException {
        ArrayList districtlist=new ArrayList();
        CommonReportDAO surgeryreportdao=new CommonReportDAO();
        try{
        districtlist=surgeryreportdao.getNiramayaDistrictReport
                (datasource);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getNiramayaDistrictReport", "CommonReportServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportServiceImpl", "getNiramayaDistrictReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getNiramayaDistrictReport", "CommonReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportServiceImpl", "getNiramayaDistrictReport");
        }
        return districtlist;
    }

    public ArrayList getNiramayaMandalReport(DataSource datasource, String District_Id)
            throws SADAREMDBException,SQLException {
        ArrayList districtlist=new ArrayList();
        CommonReportDAO surgeryreportdao=new CommonReportDAO();
        try{
        districtlist=surgeryreportdao.getNiramayaMandalReport
                (datasource,District_Id);
        }catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getNiramayaMandalReport", "CommonReportServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportServiceImpl", "getNiramayaMandalReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getNiramayaMandalReport", "CommonReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportServiceImpl", "getNiramayaMandalReport");
        }
        return districtlist;
    }

    public ArrayList getNiramayaVillageReport(DataSource datasource, String District_Id, String mandalId)
            throws SADAREMDBException,SQLException {
        ArrayList districtlist=new ArrayList();
        CommonReportDAO surgeryreportdao=new CommonReportDAO();
        try{
        districtlist=surgeryreportdao.getNiramayaVillageReport
                (datasource,District_Id,mandalId);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getNiramayaVillageReport", "CommonReportServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportServiceImpl", "getNiramayaVillageReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getNiramayaVillageReport", "CommonReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportServiceImpl", "getNiramayaVillageReport");
        }
        return districtlist;
    }

    public ArrayList getNiramayaHabitationReport(DataSource datasource, String District_Id, String mandalId, String villageId)
            throws SADAREMDBException,SQLException {
        ArrayList districtlist=new ArrayList();
        CommonReportDAO surgeryreportdao=new CommonReportDAO();
        try{
        districtlist=surgeryreportdao.getNiramayaHabitationReport
                (datasource,District_Id,mandalId,villageId);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getNiramayaHabitationReport", "CommonReportServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportServiceImpl", "getNiramayaHabitationReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getNiramayaHabitationReport", "CommonReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportServiceImpl", "getNiramayaHabitationReport");
        }
        return districtlist;
    }

}
