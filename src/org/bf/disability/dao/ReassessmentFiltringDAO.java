/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.TerritoryDTO;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 484898
 */
public class ReassessmentFiltringDAO {

    /* Method for getting reassessment details  **/
    public ArrayList getReassessmentDetails(DataSource ds, String districtId, String mandalId, String villageId, String habitationId) throws SADAREMDBException, SQLException {
        ArrayList<Object> reassessmentDetails = new ArrayList<Object>();
        HashMap<String, Object> reassDetails = null;
        TerritoryDTO dto = null;
        Statement st = null;
        Connection con = null;
        String sql = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            sql = "select b.surname+space(3)+b.first_name,b.relation_name,a.percentage, a.disabilityStatus,a.person_code from "
                    + "dbo.AppellateAuthorityandTemporary_RegistrationDetails a join tblperson_personal_details b  with (nolock)  "
                    + "on(a.person_code = b.person_code) where district_id='" + districtId + "'";
            //  if (districtId != null || mandalId != null || villageId != null || habitationId != null) {
            if (!districtId.equals("0") && !mandalId.equals("0") && villageId.equals("0") && habitationId.equals("0")) {
                sql += " and mandal_id='" + mandalId + "'";
            }
            if (!districtId.equals("0") && !mandalId.equals("0") && !villageId.equals("0") && habitationId.equals("0")) {
                sql += " and mandal_id='" + mandalId + "' and village_id='" + villageId + "'";
            }
            if (!districtId.equals("0") && !mandalId.equals("0") && !villageId.equals("0") && !habitationId.equals("0")) {
                 if(districtId.equals("16")&& mandalId.equals("84") && villageId.equals("004")&& habitationId.equals("01") ){
                       sql += " and mandal_id='" + mandalId + "' and village_id='" + villageId + "' and habitation_id in('00','" + habitationId + "')";
                 }else{
                sql += " and mandal_id='" + mandalId + "' and village_id='" + villageId + "' and habitation_id='" + habitationId + "'";
                 }
            }
            //  }
            sql += " and deleteFlag='Active' and vieweditmode='Edit'";

            rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    dto = new TerritoryDTO();
                    reassDetails = new HashMap<String, Object>();
                    reassDetails.put("name", rs.getString(1));
                    reassDetails.put("relation_name", rs.getString(2));
                    reassDetails.put("percentage", rs.getString(3));
                    reassDetails.put("disabilityStatus", rs.getString(4));
                    reassDetails.put("person_code", rs.getString(5));
                    reassessmentDetails.add(reassDetails);
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getReassessmentDetails", "ReassessmentFiltringDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReassessmentFiltringDAO", "getReassessmentDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getReassessmentDetails", "ReassessmentFiltringDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReassessmentFiltringDAO", "getReassessmentDetails");
        } finally {
            try {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeStatement(st);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return reassessmentDetails;
    }

    public int updateFlagForPersons(DataSource ds, String personIds) throws SADAREMDBException, SQLException {
        int deleteRecords = 0;
        PreparedStatement pstmt = null;
        Connection con = null;
        String sql = null;
        try {
            con = DBConnection.getConnection();
            
            sql = "update AppellateAuthorityandTemporary_RegistrationDetails set deleteFlag='Deleted' where person_code=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, personIds);
            deleteRecords = pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateFlagForPersons", "ReassessmentFiltringDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReassessmentFiltringDAO", "updateFlagForPersons");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateFlagForPersons", "ReassessmentFiltringDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReassessmentFiltringDAO", "updateFlagForPersons");
        } finally {
            try {
                DBConnection.closeConnection(con);
                DBConnection.closeStatement(pstmt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return deleteRecords;
    }

    public ArrayList getHabitations(DataSource ds, String districtId, String mandalId, String villageId) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList habitations = new ArrayList();
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "select habitation_id,habitation_name from tblHabitation_Details where district_id=? and  mandal_id=?  and village_id=? and status='Active' order by habitation_name";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtId);
            pstmt.setString(2, mandalId);
            pstmt.setString(3, villageId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                TerritoryDTO territory = new TerritoryDTO();
                territory.setHabitation_id(rs.getString("habitation_id"));
                territory.setHabitation_name(rs.getString("habitation_name"));
                habitations.add(territory);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHabitations", "ReassessmentFiltringDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReassessmentFiltringDAO", "getHabitations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHabitations", "ReassessmentFiltringDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReassessmentFiltringDAO", "getHabitations");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }

        return habitations;
    }
}
