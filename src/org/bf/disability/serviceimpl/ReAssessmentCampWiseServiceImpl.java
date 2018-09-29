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

import org.bf.disability.dao.ReAssessmentCampWiseReportDAO;

/**
 *
 * @author SADAREM
 */
public class ReAssessmentCampWiseServiceImpl {

    public ArrayList getDistrictDetials(DataSource datasource) throws SADAREMDBException, SQLException {

        ArrayList districtList = new ArrayList();

        ReAssessmentCampWiseReportDAO reAssessmentCampWiseDAO = new ReAssessmentCampWiseReportDAO();

        try {

            districtList = reAssessmentCampWiseDAO.getDistrictDetials(datasource);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDistrictDetials", "ReAssessmentCampWiseServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReAssessmentCampWiseServiceImpl", "getDistrictDetials");
        }

        return districtList;
    }

    public ArrayList getCampDetails(DataSource datasource, String district_id) throws SADAREMDBException, SQLException {

        ArrayList campList = new ArrayList();

        ReAssessmentCampWiseReportDAO reAssessmentCampWiseDAO = new ReAssessmentCampWiseReportDAO();

        try {
            campList = reAssessmentCampWiseDAO.getCampDetails(datasource, district_id);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getCampDetails", "ReAssessmentCampWiseServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReAssessmentCampWiseServiceImpl", "getCampDetails");
        }

        return campList;

    }

    public ArrayList getReAssessmentDetails(DataSource dataSource, String district_id, String camp_id) throws SADAREMDBException, SQLException {

        ArrayList assesmentList = new ArrayList();

        ReAssessmentCampWiseReportDAO reAssessmentCampWiseDAO = new ReAssessmentCampWiseReportDAO();
        try {

            assesmentList = reAssessmentCampWiseDAO.getReAssessmentDetails(dataSource, district_id, camp_id);


        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource,sqlEx.toString(), "getReAssessmentDetails", "ReAssessmentCampWiseServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReAssessmentCampWiseServiceImpl", "getReAssessmentDetails");
        }



        return assesmentList;

    }
}
