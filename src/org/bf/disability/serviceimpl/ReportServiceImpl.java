/*
 * ReportServiceImpl.java
 *
 * Created on September 12, 2008, 4:49 PM
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
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.ReportDAO;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.form.TerritoryForm;
import org.bf.disability.service.ReportService;

/**
 * This class has the implementation functionality for ReportService
 * interface.
 * @author SVS Ganesh
 * @version 1.0
 */
public class ReportServiceImpl implements ReportService 
{ 

    public ArrayList getDetailsForCertificate(DataSource datasource, TerritoryForm territoryForm, String personcode)  throws SADAREMDBException, SQLException
    {
        ArrayList reportlist = new ArrayList();
        try 
        {
        	ReportDAO reportdao = new ReportDAO();
            reportlist = reportdao.getDetailsForCertificate(datasource, territoryForm, personcode);
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDetailsForCertificate", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getDetailsForCertificate");
        }
        return reportlist;

    }

    public TerritoryDTO getTeluguName(DataSource datasource, String personcode)  throws SADAREMDBException, SQLException 
    {

    	ReportDAO reportdao = new ReportDAO();
        TerritoryDTO territoryDTO = new TerritoryDTO();
        try 
        {
            territoryDTO = reportdao.getTeluguName(datasource, personcode);
        } 
        catch (Exception sqlEx)
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getTeluguName", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getTeluguName");
        }
        return territoryDTO;
    }

    public ArrayList getDetaiilsForIdCard(DataSource datasource, TerritoryForm territoryForm, String personcode)  throws SADAREMDBException, SQLException 
    {
        ArrayList reportlist = new ArrayList();
 
        try 
        {
        	ReportDAO reportdao = new ReportDAO();
            reportlist = reportdao.getDetaiilsForIdCard(datasource, territoryForm, personcode);
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDetaiilsForIdCard", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getDetaiilsForIdCard");
        }
        
        return reportlist;

    }

    public ArrayList getRejectedData(DataSource datasource, String personcode)  throws SADAREMDBException, SQLException 
    {
        ArrayList rejectedlist = new ArrayList();
        try 
        {

        	ReportDAO reportdao = new ReportDAO();
            rejectedlist = reportdao.getRejectedData(datasource, personcode);
        }
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getRejectedData", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getRejectedData");
        }
        return rejectedlist;

    }

    public ArrayList getStatus(DataSource ds, String personcode) throws SADAREMDBException, SQLException
    {
        ArrayList personlist = new ArrayList();
        try 
        {
        	ReportDAO reportdao = new ReportDAO();
            personlist = reportdao.getStatus(ds, personcode);
        } 
        catch (Exception sqlEx)
        {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getStatus", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getStatus");
        }
        return personlist;
    }

    public ArrayList getLocomotorData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList locomotorlist = new ArrayList();
        try 
        {
        	ReportDAO reportdao = new ReportDAO();
            locomotorlist = reportdao.getLocomotorData(datasource, personcode);
        }
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getLocomotorData", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getLocomotorData");
        }
        return locomotorlist;

    }

    public ArrayList getHearingData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList hearinglist = new ArrayList();
        try 
        {
        	ReportDAO reportdao = new ReportDAO();
            hearinglist = reportdao.getHearingData(datasource, personcode);
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHearingData", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getHearingData");
        }
        return hearinglist;

    }

    public ArrayList getVisualData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList visuallist = new ArrayList();
        try
        {
        	ReportDAO reportdao = new ReportDAO();
            visuallist = reportdao.getVisualData(datasource, personcode);
        }
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVisualData", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getVisualData");
        }
        return visuallist;

    }

    public ArrayList getMetalillnessData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList illinesslist = new ArrayList();
        try
        {
        	ReportDAO reportdao = new ReportDAO();
            illinesslist = reportdao.getMetalillnessData(datasource, personcode);
        } 
        catch (Exception sqlEx)
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMetalillnessData", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getMetalillnessData");
        }
        return illinesslist;

    }

    public ArrayList getMentalRetardationData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList retardationlist = new ArrayList();
        try 
        {
        	ReportDAO reportdao = new ReportDAO();
            retardationlist = reportdao.getMentalRetardationData(datasource, personcode);
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMentalRetardationData", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getMentalRetardationData");
        }
        return retardationlist;
    }

    public ArrayList getMultipleData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList multiplelist = new ArrayList();
        try 
        {
        	ReportDAO reportdao = new ReportDAO();
            multiplelist = reportdao.getMultipleData(datasource, personcode);
        } 
        catch (Exception sqlEx)
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMultipleData", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getMultipleData");
        }
        return multiplelist;
    }

    public ArrayList getRejectedLocomotorData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList locomotorlist = new ArrayList();
        try 
        {
        	ReportDAO reportdao = new ReportDAO();
            locomotorlist = reportdao.getRejectedLocomotorData(datasource, personcode);
        } 
        catch (Exception sqlEx)
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getRejectedLocomotorData", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getRejectedLocomotorData");
        }
        return locomotorlist;

    }

    public ArrayList getRejectedHearingData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList hearinglist = new ArrayList();
        try 
        {
        	ReportDAO reportdao = new ReportDAO();
            hearinglist = reportdao.getRejectedHearingData(datasource, personcode);
        } 
        catch (Exception sqlEx)
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getRejectedHearingData", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getRejectedHearingData");
        }
        return hearinglist;

    }

    public ArrayList getRejectedVisualData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList visuallist = new ArrayList();
        try 
        {
        	ReportDAO reportdao = new ReportDAO();
            visuallist = reportdao.getRejectedVisualData(datasource, personcode);
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getRejectedVisualData", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getRejectedVisualData");
        }
        return visuallist;

    }

    public ArrayList getRejectedMetalillnessData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList illinesslist = new ArrayList();
        try
        {
        	ReportDAO reportdao = new ReportDAO();
            illinesslist = reportdao.getRejectedMetalillnessData(datasource, personcode);
        }
        catch (Exception sqlEx)
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getRejectedMetalillnessData", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getRejectedMetalillnessData");
        }
        
        return illinesslist;

    }

    public ArrayList getRejectedMentalRetardationData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList retardationlist = new ArrayList();
        try
        {
        	ReportDAO reportdao = new ReportDAO();
            retardationlist = reportdao.getRejectedMentalRetardationData(datasource, personcode);
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getRejectedMentalRetardationData", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getRejectedMentalRetardationData");
        }
        
        return retardationlist;

    }

    public ArrayList getLocomotorSubDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList locomotorsubdetailslist = new ArrayList();
        try 
        {
        	ReportDAO reportdao = new ReportDAO();
            locomotorsubdetailslist = reportdao.getLocomotorSubDetails(datasource, personcode);
        } 
        catch (Exception sqlEx)
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getLocomotorSubDetails", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getLocomotorSubDetails");
        }
        return locomotorsubdetailslist;
    }

    public ArrayList getVisualSubDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList locomotorsubdetailslist = new ArrayList();
        try
        {
        	ReportDAO reportdao = new ReportDAO();
            locomotorsubdetailslist = reportdao.getVisualSubDetails(datasource, personcode);
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVisualSubDetails", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getVisualSubDetails");
        }
        return locomotorsubdetailslist;
    }

    public ArrayList getHearingSubDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList locomotorsubdetailslist = new ArrayList();
        try
        {
        	ReportDAO reportdao = new ReportDAO();
            locomotorsubdetailslist = reportdao.getHearingSubDetails(datasource, personcode);
        }
        catch (Exception sqlEx)
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHearingSubDetails", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getHearingSubDetails");
        }
        return locomotorsubdetailslist;
    }

    public ArrayList getMRSubDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList locomotorsubdetailslist = new ArrayList();
        try
        {
        	ReportDAO reportdao = new ReportDAO();
            locomotorsubdetailslist = reportdao.getMRSubDetails(datasource, personcode);
        } 
        catch (Exception sqlEx)
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMRSubDetails", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getMRSubDetails");
        }
        return locomotorsubdetailslist;
    }

    public ArrayList getMISubDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList locomotorsubdetailslist = new ArrayList();
        try 
        {
        	ReportDAO reportdao = new ReportDAO();
            locomotorsubdetailslist = reportdao.getMISubDetails(datasource, personcode);
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMISubDetails", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getMISubDetails");
        }
        return locomotorsubdetailslist;
    }

    public ArrayList getMultipleSubDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList locomotorsubdetailslist = new ArrayList();
        try 
        {
        	ReportDAO reportdao = new ReportDAO();
            locomotorsubdetailslist = reportdao.getMultipleSubDetails(datasource, personcode);
        }
        catch (Exception sqlEx)
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMultipleSubDetails", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getMultipleSubDetails");
        }
        return locomotorsubdetailslist;
    }

    public ArrayList getLocomotorConditionDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList locomotorsubdetailslist = new ArrayList();
        try
        {
        	ReportDAO reportdao = new ReportDAO();
            locomotorsubdetailslist = reportdao.getLocomotorConditionDetails(datasource, personcode);
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getLocomotorConditionDetails", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getLocomotorConditionDetails");
        }
        return locomotorsubdetailslist;
    }

    public ArrayList getVisualConditionDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList locomotorsubdetailslist = new ArrayList();
        try
        {
        	ReportDAO reportdao = new ReportDAO();
            locomotorsubdetailslist = reportdao.getVisualConditionDetails(datasource, personcode);
        }
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVisualConditionDetails", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getVisualConditionDetails");
        }
        return locomotorsubdetailslist;
    }

    public ArrayList getHearingConditionDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList locomotorsubdetailslist = new ArrayList();
        try 
        {
        	ReportDAO reportdao = new ReportDAO();
            locomotorsubdetailslist = reportdao.getHearingConditionDetails(datasource, personcode);
        }
        catch (Exception sqlEx)
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHearingConditionDetails", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getHearingConditionDetails");
        }
        return locomotorsubdetailslist;
    }

    public ArrayList getMRConditionDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList locomotorsubdetailslist = new ArrayList();
        try 
        {
        	ReportDAO reportdao = new ReportDAO();
            locomotorsubdetailslist = reportdao.getMRConditionDetails(datasource, personcode);
        }
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMRConditionDetails", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getMRConditionDetails");
        }
        return locomotorsubdetailslist;
    }

    public ArrayList getMultipleConditionDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException 
    {
        ArrayList locomotorsubdetailslist = new ArrayList();
        try
        {
        	ReportDAO reportdao = new ReportDAO();
            locomotorsubdetailslist = reportdao.getMultipleConditionDetails(datasource, personcode);
        }
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMultipleConditionDetails", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getMultipleConditionDetails");
        }
        return locomotorsubdetailslist;
    } 
    
    public String getReasonForPersonStatus(DataSource ds, String personcode) throws SADAREMDBException, SQLException 
    {
    	String personlist = "";
        try 
        {
        	ReportDAO reportdao = new ReportDAO();
            personlist = reportdao.getReasonForPersonStatus(ds, personcode);
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getStatus", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getStatus");
        }
        return personlist;
    }
    
    public ArrayList getCatIdOfPerson(DataSource ds, String personcode) throws SADAREMDBException, SQLException
    {
    	ArrayList personlist;
        try 
        {
        	ReportDAO reportdao = new ReportDAO();
            personlist = reportdao.getCatIdOfPerson(ds, personcode);
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getStatus", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getStatus");
        }
        return personlist;
    }
    public String getStatusOfPerson(DataSource ds, String personcode) throws SADAREMDBException, SQLException 
    {
    	String personlist="";
        try 
        {
        	ReportDAO reportdao = new ReportDAO();
            personlist = reportdao.getStatusOfPerson(ds, personcode);
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getStatus", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getStatus");
        }
        return personlist;
    }
    public String getViewmodeOfPerson(DataSource ds, String personcode) throws SADAREMDBException, SQLException 
    {
    	String personlist="";
        try 
        {
        	ReportDAO reportdao = new ReportDAO();
            personlist = reportdao.getViewmodeOfPerson(ds, personcode);
        }
        catch (Exception sqlEx)
        {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getStatus", "ReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getStatus");
        }
        return personlist;
    }

    public ArrayList getOnlyPartADetails(String personcode) 
    {
    	ArrayList personlist =  new ArrayList();
        try 
        {
        	ReportDAO reportdao = new ReportDAO();
            personlist = reportdao.getOnlyPartADetails( personcode);
        } 
        catch (Exception sqlEx) 
        {
           // int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getStatus", "ReportServiceImpl", "Code");
          //  throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getStatus");
        }
        return personlist;
    }
   
    public ArrayList getCampDistIds(String personcode)  
    {
    	ArrayList personlist =  new ArrayList();
        try 
        {
        	ReportDAO reportdao = new ReportDAO();
            personlist = reportdao.getCampDistIds( personcode);
        }
        catch (Exception sqlEx) 
        {
           // int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getStatus", "ReportServiceImpl", "Code");
          //  throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportServiceImpl", "getStatus");
        }
        return personlist;
    }

 
}
