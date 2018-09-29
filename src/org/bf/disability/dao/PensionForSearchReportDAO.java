/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.PensionForSearchReportDTO;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 484898
 */
public class PensionForSearchReportDAO {

    public ArrayList getMandals(DataSource ds, String districtid) throws SADAREMDBException, SQLException {

        ArrayList mandalList = new ArrayList();
        PensionForSearchReportDTO pensionForSearchReportDTO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;

        try {
            con = DBConnection.getConnection();

            query = "select mandal_id,mandal_name from tblMandal_Details where district_id=? order by mandal_name";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtid);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                pensionForSearchReportDTO = new PensionForSearchReportDTO();
                pensionForSearchReportDTO.setMandal_id(rs.getString("mandal_id"));
                pensionForSearchReportDTO.setMandal_name(rs.getString("mandal_name"));
                mandalList.add(pensionForSearchReportDTO);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandals", "PensionForSearchReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PensionForSearchReportDAO", "getMandals");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandals", "PensionForSearchReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PensionForSearchReportDAO", "getMandals");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return mandalList;
    }

    public ArrayList getVillageAll(DataSource ds, String districtid, String mandalid) throws SADAREMDBException, SQLException {

        ArrayList villagelist = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        Connection con = null;
        PensionForSearchReportDTO pensionForSearchReportDTO = null;

        try {

            con = DBConnection.getConnection();
            query = "select village_id,village_name from tblVillage_Details where district_id=? and  mandal_id=?   order by village_name";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtid);
            pstmt.setString(2, mandalid);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                pensionForSearchReportDTO = new PensionForSearchReportDTO();
                pensionForSearchReportDTO.setVillage_id(rs.getString("village_id"));
                pensionForSearchReportDTO.setVillage_name(rs.getString("village_name"));
                villagelist.add(pensionForSearchReportDTO);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillageAll", "PensionForSearchReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PensionForSearchReportDAO", "getVillageAll");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillageAll", "PensionForSearchReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PensionForSearchReportDAO", "getVillageAll");
        } finally {
            try {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeStatement(pstmt);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return villagelist;
    }

    public ArrayList getPhaseSearchDetails(DataSource ds, String pensionCode, String sadaremId, String districtId, String mandalId, String villageId) throws SADAREMDBException, SQLException {

        ArrayList searchList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmtPhaseI = null;
        PreparedStatement pstmtPhaseII = null;
        PreparedStatement pstmtPhaseIV = null;
        PreparedStatement pstmtTotalQuery = null;


        String phaseICountquery = null;
        String phaseIICountquery = null;
        String phaseIVCountquery = null;


        ResultSet phaseIRs = null;
        ResultSet phaseIIRs = null;
        ResultSet phaseIVRs = null;
        ResultSet totalReusltSet = null;
        String sadaremQuery = null;
        Map dataList = null;


        int phaseICount = 0;
        int phaseIICount = 0;
        int phaseIVCount = 0;

        String phaseVCountquery = null;
        String phaseVICountquery = null;
        String phaseVIICountquery = null;
        String totalSadaremQuery = null;

        int phaseVCount = 0;
        int phaseVICount = 0;
        int phaseVIICount = 0;


        ResultSet phaseVRs = null;
        ResultSet phaseVIRs = null;
        ResultSet phaseVIIRs = null;

        PreparedStatement pstmtPhaseV = null;
        PreparedStatement pstmtPhaseVI = null;
        PreparedStatement pstmtPhaseVII = null;
        PreparedStatement pstmtTotalPensionQuery = null;

        try {
            con = DBConnection.getConnection();
            if (sadaremId != null && !sadaremId.equals("")) {

                phaseICountquery = "select count(*) from phaseI where sadaremcode=?";

                phaseIICountquery = "select count(*) from phaseII where sadaremcode=?";

                phaseIVCountquery = "select count(*) from phaseIV where sadaremcode=?";


                pstmtPhaseI = con.prepareStatement(phaseICountquery);
                pstmtPhaseI.setString(1, sadaremId);
                pstmtPhaseII = con.prepareStatement(phaseIICountquery);
                pstmtPhaseII.setString(1, sadaremId);
                pstmtPhaseIV = con.prepareStatement(phaseIVCountquery);
                pstmtPhaseIV.setString(1, sadaremId);

                phaseIRs = pstmtPhaseI.executeQuery();
                phaseIIRs = pstmtPhaseII.executeQuery();
                phaseIVRs = pstmtPhaseIV.executeQuery();


                if (phaseIRs != null && phaseIRs.next()) {
                    phaseICount = phaseIRs.getInt(1);
                }

                if (phaseIIRs != null && phaseIIRs.next()) {
                    phaseIICount = phaseIIRs.getInt(1);
                }

                if (phaseIVRs != null && phaseIVRs.next()) {
                    phaseIVCount = phaseIVRs.getInt(1);
                }

                sadaremQuery = " select pensionid,sadaremcode,personname,gender,education,relationname,typeofDisability,"
                        + "totalDisability,remarks,reasonforpersonstatus";

                if (phaseICount > 0) {

                    sadaremQuery += " from dbo.PhaseI where sadaremcode='" + sadaremId + "'";

                } else if (phaseIICount > 0) {

                    sadaremQuery += " from dbo.PhaseII where sadaremcode='" + sadaremId + "'";

                } else if (phaseIVCount > 0) {

                    sadaremQuery += " from dbo.PhaseIV where sadaremcode='" + sadaremId + "'";

                }

                pstmtTotalQuery = con.prepareStatement(sadaremQuery);

                totalReusltSet = pstmtTotalQuery.executeQuery();
                while (totalReusltSet.next()) {
                    dataList = new HashMap();
                    dataList.put("pensionid", totalReusltSet.getString(1));
                    dataList.put("sadaremcode", totalReusltSet.getString(2));
                    dataList.put("personname", totalReusltSet.getString(3));
                    dataList.put("gender", totalReusltSet.getString(4));
                    dataList.put("education", totalReusltSet.getString(5));
                    dataList.put("relationname", totalReusltSet.getString(6));
                    dataList.put("typeofDisability", totalReusltSet.getString(7));
                    dataList.put("totalDisability", totalReusltSet.getString(8));
                    dataList.put("remarks", totalReusltSet.getString(9));
                    dataList.put("reasonforpersonstatus", totalReusltSet.getString(10));
                    searchList.add(dataList);

                }

            } else {

                phaseVCountquery = "select count(*) from phaseI where pensionid='" + pensionCode + "'  and substring(habcode,1,2)='" + districtId + "'";
                phaseVICountquery = "select count(*) from phaseII where pensionid='" + pensionCode + "'  and substring(habcode,1,2)='" + districtId + "'";
                phaseVIICountquery = "select count(*) from phaseIV where pensionid='" + pensionCode + "'  and substring(habcode,1,2)='" + districtId + "'";


                pstmtPhaseV = con.prepareStatement(phaseVCountquery);
                pstmtPhaseVI = con.prepareStatement(phaseVICountquery);
                pstmtPhaseVII = con.prepareStatement(phaseVIICountquery);

                phaseVRs = pstmtPhaseV.executeQuery();
                phaseVIRs = pstmtPhaseVI.executeQuery();
                phaseVIIRs = pstmtPhaseVII.executeQuery();


                if (phaseVRs != null && phaseVRs.next()) {
                    phaseVCount = phaseVRs.getInt(1);
                }
                if (phaseVIRs != null && phaseVIRs.next()) {
                    phaseVICount = phaseVIRs.getInt(1);
                }

                if (phaseVIIRs != null && phaseVIIRs.next()) {
                    phaseVIICount = phaseVIIRs.getInt(1);
                }

                totalSadaremQuery = " select pensionid,sadaremcode,personname,gender,education,relationname,typeofDisability,"
                        + "totalDisability,remarks,reasonforpersonstatus";

                if (phaseVCount > 0) {

                    totalSadaremQuery += " from dbo.PhaseI where pensionid='" + pensionCode + "'  and substring(habcode,1,2)='" + districtId + "'";

                } else if (phaseVICount > 0) {

                    totalSadaremQuery += " from dbo.PhaseII where pensionid='" + pensionCode + "'  and substring(habcode,1,2)='" + districtId + "'";

                } else if (phaseVIICount > 0) {

                    totalSadaremQuery += " from dbo.PhaseIV where pensionid='" + pensionCode + "'  and substring(habcode,1,2)='" + districtId + "'";

                }

                pstmtTotalPensionQuery = con.prepareStatement(totalSadaremQuery);

                totalReusltSet = pstmtTotalPensionQuery.executeQuery();
                while (totalReusltSet.next()) {

                    dataList = new HashMap();
                    dataList.put("pensionid", totalReusltSet.getString(1));
                    dataList.put("sadaremcode", totalReusltSet.getString(2));
                    dataList.put("personname", totalReusltSet.getString(3));
                    dataList.put("gender", totalReusltSet.getString(4));
                    dataList.put("education", totalReusltSet.getString(5));
                    dataList.put("relationname", totalReusltSet.getString(6));
                    dataList.put("typeofDisability", totalReusltSet.getString(7));
                    dataList.put("totalDisability", totalReusltSet.getString(8));
                    dataList.put("remarks", totalReusltSet.getString(9));
                    dataList.put("reasonforpersonstatus", totalReusltSet.getString(10));
                    searchList.add(dataList);
                }

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPhaseSearchDetails", "PensionForSearchReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PensionForSearchReportDAO", "getPhaseSearchDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPhaseSearchDetails", "PensionForSearchReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PensionForSearchReportDAO", "getPhaseSearchDetails");
        } finally {
            try {

                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(phaseIRs);
                DBConnection.closeResultSet(phaseIIRs);
                DBConnection.closeResultSet(phaseIVRs);
                DBConnection.closeResultSet(totalReusltSet);
                DBConnection.closeResultSet(phaseVRs);
                DBConnection.closeResultSet(phaseVIRs);
                DBConnection.closeResultSet(phaseVIIRs);
                DBConnection.closeResultSet(totalReusltSet);
                DBConnection.closeStatement(pstmtPhaseI);
                DBConnection.closeStatement(pstmtPhaseII);
                DBConnection.closeStatement(pstmtPhaseIV);
                DBConnection.closeStatement(pstmtTotalQuery);
                DBConnection.closeStatement(pstmtPhaseV);
                DBConnection.closeStatement(pstmtPhaseVI);
                DBConnection.closeStatement(pstmtPhaseVII);
                DBConnection.closeStatement(pstmtTotalPensionQuery);

            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPhaseSearchDetails", "PensionForSearchReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PensionForSearchReportDAO", "getPhaseSearchDetails");
            }
        }
        return searchList;

    }
}
