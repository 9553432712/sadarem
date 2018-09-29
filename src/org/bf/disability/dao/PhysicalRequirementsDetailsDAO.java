/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.PhysicalRequirementsDetailsDTO;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 693461
 */
public class PhysicalRequirementsDetailsDAO {

    public ArrayList getphysicalRequirementDetails(DataSource ds, String districtId, String mandalId, String villageId) throws SADAREMDBException, SQLException {

        ArrayList physicalRequiremenList = new ArrayList();
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        HashMap physicalRequirementData = null;

        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call Person_CandoDetailsCountbySelection(?,?,?)}");
            cstmt.setString(1, districtId);
            cstmt.setString(2, mandalId);
            cstmt.setString(3, villageId);

            rs = cstmt.executeQuery();
            if (districtId.equals("00")) {
                if (rs != null) {
                    while (rs.next()) {
                        physicalRequirementData = new HashMap();
                        physicalRequirementData.put("districtId", rs.getString(1));
                        physicalRequirementData.put("name", rs.getString(2));
                        physicalRequirementData.put("personCount", rs.getString(3));
                        physicalRequiremenList.add(physicalRequirementData);

                    }
                }
            } else if (!districtId.equals("00") && mandalId.equals("00")) {
                if (rs != null) {
                    while (rs.next()) {
                        physicalRequirementData = new HashMap();
                        physicalRequirementData.put("districtId", districtId);
                         physicalRequirementData.put("mandalId", rs.getString(1));
                        physicalRequirementData.put("name", rs.getString(2));
                        physicalRequirementData.put("personCount", rs.getString(3));
                        physicalRequiremenList.add(physicalRequirementData);
                    }
                }
            } else if (!districtId.equals("00") && !mandalId.equals("00")) {
                if (rs != null) {
                    while (rs.next()) {
                        physicalRequirementData = new HashMap();
                        physicalRequirementData.put("districtId", districtId);
                        physicalRequirementData.put("mandalId", mandalId);
                         physicalRequirementData.put("villageId", rs.getString(1));
                        physicalRequirementData.put("name", rs.getString(2));
                        physicalRequirementData.put("personCount", rs.getString(3));
                        physicalRequiremenList.add(physicalRequirementData);
                    }
                }
            } else if (!districtId.equals("00") && !mandalId.equals("00") && !villageId.equals("00")) {
                if (rs != null) {
                    while (rs.next()) {
                        physicalRequirementData = new HashMap();
                        physicalRequirementData.put("districtId", districtId);
                        physicalRequirementData.put("mandalId", mandalId);
                        physicalRequirementData.put("villageId", rs.getString(1));
                        physicalRequirementData.put("name", rs.getString(2));
                        physicalRequirementData.put("personCount", rs.getString(3));
                        physicalRequiremenList.add(physicalRequirementData);
                    }
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getphysicalRequirementDetails", "PhysicalRequirementsDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PhysicalRequirementsDetailsDAO", "getphysicalRequirementDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getphysicalRequirementDetails", "PhysicalRequirementsDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PhysicalRequirementsDetailsDAO", "getphysicalRequirementDetails");
        } 
        finally 
        { 
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);
            DBConnection.closeConnection(con);
        }
        return physicalRequiremenList;
    }

    public ArrayList getphysicalRequirementPersonalDetails(DataSource ds, String districtId, String mandalId, String villageId) throws SADAREMDBException, SQLException {

        ArrayList personalRequirementList = new ArrayList();
        Connection con = null;
        CallableStatement cstmt = null;
        String query = null;
        ResultSet rs = null;
        HashMap personalRequirement = null;
        String address = "";
        PhysicalRequirementsDetailsDTO physicalRequirementsDetailsDTO = null;


        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call PERSON_CANDODETAILSPERSONALBYSELECTION(?,?,?)}");
            cstmt.setString(1, districtId);
            cstmt.setString(2, mandalId);
            cstmt.setString(3, villageId);
            rs = cstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    physicalRequirementsDetailsDTO = new PhysicalRequirementsDetailsDTO();
                    physicalRequirementsDetailsDTO.setPersonCode(rs.getString(1));
                    physicalRequirementsDetailsDTO.setPersonName(rs.getString(2));
                    physicalRequirementsDetailsDTO.setPersonRelationName(rs.getString(3));
                    physicalRequirementsDetailsDTO.setPersonalhouseNo(rs.getString(4));
                    physicalRequirementsDetailsDTO.setPersonalDistrictName(rs.getString(5));
                    physicalRequirementsDetailsDTO.setPersonalMandalName(rs.getString(6));
                    physicalRequirementsDetailsDTO.setPersonalVillageName(rs.getString(7));
                    physicalRequirementsDetailsDTO.setPersonalHabitationName(rs.getString(8));
                    personalRequirementList.add(physicalRequirementsDetailsDTO);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getphysicalRequirementPersonalDetails", "PhysicalRequirementsDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PhysicalRequirementsDetailsDAO", "getphysicalRequirementPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getphysicalRequirementPersonalDetails", "PhysicalRequirementsDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PhysicalRequirementsDetailsDAO", "getphysicalRequirementPersonalDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);
            DBConnection.closeResultSet(rs);
        }

        return personalRequirementList;

    }
}
