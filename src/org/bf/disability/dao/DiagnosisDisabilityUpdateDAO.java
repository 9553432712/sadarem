/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 693461
 */
public class DiagnosisDisabilityUpdateDAO {

    public String getDiagnosisDisabilityDetails(DataSource ds, String personCode) throws SADAREMDBException,SQLException {

        String diagnosisDisabilityDetails = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;


        try {
            con = DBConnection.getConnection();
            query = "select p.person_code,t.totaldisability from dbo.tblPerson_Personal_Details p  with (nolock) join dbo.tblPerson_Disability_Details d"
                    + " on(p.person_code=d.person_code)join dbo.tblPerson_Disability_TotalValue_Details t"
                    + " on(p.person_code=t.person_code) where p.status='Active' and d.status='Active'"
                    + " and t.status='Active'  and t.totaldisability>=40 and p.person_code=?";
            pstmt = con.prepareStatement(query); 
            
            pstmt.setString(1, personCode);
            
            rs = pstmt.executeQuery();

            if (rs != null) {
                if (rs.next()) {
                    //   if(rs.getString(2)!=null && rs.getString(2)!="" && Integer.parseInt(rs.getString(2))>=40)

                    diagnosisDisabilityDetails = rs.getString(1);
                }//else{

                //}
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDiagnosisDisabilityDetails", "DiagnosisDisabilityUpdateDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DiagnosisDisabilityUpdateDAO", "getDiagnosisDisabilityDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDiagnosisDisabilityDetails", "DiagnosisDisabilityUpdateDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DiagnosisDisabilityUpdateDAO", "getDiagnosisDisabilityDetails");
        } 
        finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return diagnosisDisabilityDetails;
    }

    public int updateDiagnosisDisabilityDetails(DataSource ds, String diagnosisDetails, String personcode) throws SADAREMDBException,SQLException {

        int updateDiagnosisDisabilityDetails = 0;
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;


        try {
            con = DBConnection.getConnection();
            query = "update tblPerson_Disability_Details set  diagnosis_of_Disability =?"
                    + " where person_code =?";
            
            pstmt = con.prepareStatement(query); 
            
            pstmt.setString(1, diagnosisDetails);
            pstmt.setString(2, personcode);
            
            
            updateDiagnosisDisabilityDetails = pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateDiagnosisDisabilityDetails", "DiagnosisDisabilityUpdateDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DiagnosisDisabilityUpdateDAO", "updateDiagnosisDisabilityDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateDiagnosisDisabilityDetails", "DiagnosisDisabilityUpdateDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DiagnosisDisabilityUpdateDAO", "updateDiagnosisDisabilityDetails");
        }  finally {
           DBConnection.closeStatement(pstmt);
           DBConnection.closeConnection(con);
        }

        return updateDiagnosisDisabilityDetails;

    }
}
