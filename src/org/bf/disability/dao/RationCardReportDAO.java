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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.RationCardReportDTO;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author SADAREM
 */
public class RationCardReportDAO {

    public ArrayList getMandals(DataSource ds, String districtid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        ArrayList mandal_list = new ArrayList();



        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "select mandal_id,mandal_name from tblMandal_Details where district_id=? order by mandal_name";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtid);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                RationCardReportDTO rationCardReportDTO = new RationCardReportDTO();
                rationCardReportDTO.setMandal_id(rs.getString("mandal_id"));
                rationCardReportDTO.setMandal_name(rs.getString("mandal_name"));
                mandal_list.add(rationCardReportDTO);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandals", "RationCardReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RationCardReportDAO", "getMandals");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandals", "RationCardReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RationCardReportDAO", "getMandals");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }

        return mandal_list;
    }

    public ArrayList getVillageAll(DataSource ds, String districtid, String mandalid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList villagelist = new ArrayList();
        ResultSet rs = null;
        String query = null;
        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            query = "select village_id,village_name from tblVillage_Details where district_id=? and  mandal_id=?   order by village_name";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtid);
            pstmt.setString(2, mandalid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                RationCardReportDTO rationCardReportDTO = new RationCardReportDTO();
                rationCardReportDTO.setVillage_id(rs.getString("village_id"));
                rationCardReportDTO.setVillage_name(rs.getString("village_name"));
                villagelist.add(rationCardReportDTO);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillageAll", "RationCardReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RationCardReportDAO", "getVillageAll");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillageAll", "RationCardReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RationCardReportDAO", "getVillageAll");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return villagelist;
    }

    public ArrayList getHabitation(DataSource ds, String districtid, String mandalid, String villageid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList habitationList = new ArrayList();
        ResultSet rs = null;
        String query = null;
        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            query = " select habitation_id,habitation_name from tblHabitation_Details where district_id=? and mandal_id=? and village_id=? and status='Active' order by habitation_name";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtid);
            pstmt.setString(2, mandalid);
            pstmt.setString(3, villageid);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                RationCardReportDTO rationCardReportDTO = new RationCardReportDTO();
                rationCardReportDTO.setHabitation_id(rs.getString("habitation_id"));
                rationCardReportDTO.setHabitation_name(rs.getString("habitation_name"));
                habitationList.add(rationCardReportDTO);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHabitation", "RationCardReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RationCardReportDAO", "getHabitation");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHabitation", "RationCardReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RationCardReportDAO", "getHabitation");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return habitationList;
    }

    public ArrayList getRationPersonalReportDetails(DataSource ds, String district_id, String mandal_id, String village_id, String habitation_id, String personStatus) throws SADAREMDBException, SQLException {
        ArrayList<Object> personalDetails = new ArrayList<Object>();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        StringBuffer sql = new StringBuffer();
        Map<String, Object> rationPersonalData = null;
        int i = 1;
        try {

            con = DBConnection.getConnection();
            

            if (district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004") && habitation_id.equals("01")) {

                 if (personStatus.equals("1")) { // ALL
                    sql.append("select distinct a.pensioncard_no,a.person_code,a.surname + SPACE(2)+ a.first_name as name,a.relation_name,a.rationcard_number,a.rationcard_type from tblperson_personal_details a   with (nolock) where a.District_ID=? and a.Mandal_ID=? and a.Village_ID=? and a.Habitation_ID in ('00',?) and a.rationcard_slno is null and a.status='Active' ");
                    pstmt = con.prepareStatement(sql.toString());
                    pstmt.setString(1, district_id);
                    pstmt.setString(2, mandal_id);
                    pstmt.setString(3, village_id);
                    pstmt.setString(4, habitation_id);
                } else if (personStatus.equals("2")) { // Eligible
                    sql.append("select distinct a.pensioncard_no,a.person_code,a.surname + SPACE(2)+ a.first_name as name,a.relation_name,a.rationcard_number,a.rationcard_type from tblperson_personal_details a   with (nolock) join tblPerson_Disability_TotalValue_Details b on(a.person_code=b.person_code and b.totaldisability >=40) where a.District_ID=? and a.Mandal_ID=? and a.Village_ID=? and a.Habitation_ID in ('00',?) and a.rationcard_slno is null and a.status='Active' ");
                    pstmt = con.prepareStatement(sql.toString());
                    pstmt.setString(1, district_id);
                    pstmt.setString(2, mandal_id);
                    pstmt.setString(3, village_id);
                    pstmt.setString(4, habitation_id);
                } else if (personStatus.equals("3")) { // AR
                    sql.append("select distinct a.pensioncard_no,a.person_code,a.surname + SPACE(2)+ a.first_name as name,a.relation_name,a.rationcard_number,a.rationcard_type from tblperson_personal_details a   with (nolock) join tblPerson_Disability_TotalValue_Details b on(a.person_code=b.person_code and b.totaldisability <=39) where a.District_ID=? and a.Mandal_ID=? and a.Village_ID=? and a.Habitation_ID in ('00',?) and a.rationcard_slno is null and a.status='Active' ");
                    pstmt = con.prepareStatement(sql.toString());
                    pstmt.setString(1, district_id);
                    pstmt.setString(2, mandal_id);
                    pstmt.setString(3, village_id);
                    pstmt.setString(4, habitation_id);
                } else if (personStatus.equals("4")) { // DR
                    sql.append("select distinct a.pensioncard_no,a.person_code,a.surname + SPACE(2)+ a.first_name as name,a.relation_name,a.rationcard_number,a.rationcard_type from tblperson_personal_details a   with (nolock) join tblRejectPerson_Details b on(a.person_code=b.person_code) where a.District_ID=? and a.Mandal_ID=? and a.Village_ID=? and a.Habitation_ID in ('00',?) and a.rationcard_slno is null and a.status='Active' ");
                    pstmt = con.prepareStatement(sql.toString());
                    pstmt.setString(1, district_id);
                    pstmt.setString(2, mandal_id);
                    pstmt.setString(3, village_id);
                    pstmt.setString(4, habitation_id);
                }
            } else {
                if (personStatus.equals("1")) { // ALL
                    sql.append("select distinct a.pensioncard_no,a.person_code,a.surname + SPACE(2)+ a.first_name as name,a.relation_name,a.rationcard_number,a.rationcard_type from tblperson_personal_details a   with (nolock) where a.District_ID=? and a.Mandal_ID=? and a.Village_ID=? and a.Habitation_ID=? and a.rationcard_slno is null and a.status='Active' ");
                    pstmt = con.prepareStatement(sql.toString());
                    pstmt.setString(1, district_id);
                    pstmt.setString(2, mandal_id);
                    pstmt.setString(3, village_id);
                    pstmt.setString(4, habitation_id);
                } else if (personStatus.equals("2")) { // Eligible
                    sql.append("select distinct a.pensioncard_no,a.person_code,a.surname + SPACE(2)+ a.first_name as name,a.relation_name,a.rationcard_number,a.rationcard_type from tblperson_personal_details a   with (nolock) join tblPerson_Disability_TotalValue_Details b on(a.person_code=b.person_code and b.totaldisability >=40) where a.District_ID=? and a.Mandal_ID=? and a.Village_ID=? and a.Habitation_ID=? and a.rationcard_slno is null and a.status='Active' ");
                    pstmt = con.prepareStatement(sql.toString());
                    pstmt.setString(1, district_id);
                    pstmt.setString(2, mandal_id);
                    pstmt.setString(3, village_id);
                    pstmt.setString(4, habitation_id);
                } else if (personStatus.equals("3")) { // AR
                    sql.append("select distinct a.pensioncard_no,a.person_code,a.surname + SPACE(2)+ a.first_name as name,a.relation_name,a.rationcard_number,a.rationcard_type from tblperson_personal_details a   with (nolock) join tblPerson_Disability_TotalValue_Details b on(a.person_code=b.person_code and b.totaldisability <=39) where a.District_ID=? and a.Mandal_ID=? and a.Village_ID=? and a.Habitation_ID=? and a.rationcard_slno is null and a.status='Active' ");
                    pstmt = con.prepareStatement(sql.toString());
                    pstmt.setString(1, district_id);
                    pstmt.setString(2, mandal_id);
                    pstmt.setString(3, village_id);
                    pstmt.setString(4, habitation_id);
                } else if (personStatus.equals("4")) { // DR
                    sql.append("select distinct a.pensioncard_no,a.person_code,a.surname + SPACE(2)+ a.first_name as name,a.relation_name,a.rationcard_number,a.rationcard_type from tblperson_personal_details a   with (nolock) join tblRejectPerson_Details b on(a.person_code=b.person_code) where a.District_ID=? and a.Mandal_ID=? and a.Village_ID=? and a.Habitation_ID=? and a.rationcard_slno is null and a.status='Active' ");
                    pstmt = con.prepareStatement(sql.toString());
                    pstmt.setString(1, district_id);
                    pstmt.setString(2, mandal_id);
                    pstmt.setString(3, village_id);
                    pstmt.setString(4, habitation_id);
                }
            }
            
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {

                    rationPersonalData = new HashMap<String, Object>();
                    rationPersonalData.put("sno", i);
                    rationPersonalData.put("pensioncard_no", rs.getString(1));
                    rationPersonalData.put("person_code", rs.getString(2));
                    rationPersonalData.put("name", rs.getString(3));
                    rationPersonalData.put("relation", rs.getString(4));
                    rationPersonalData.put("rationCard", rs.getString(5));
                    rationPersonalData.put("rationCardType", rs.getString(6));
                    personalDetails.add(rationPersonalData);
                    i++;
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRationPersonalReportDetails", "RationCardReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RationCardReportDAO", "getRationPersonalReportDetails");
        } catch (Exception sqlEx) {System.out.println(sqlEx);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRationPersonalReportDetails", "RationCardReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RationCardReportDAO", "getRationPersonalReportDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }


        return personalDetails;
    }

    /**
     * This Method is Used for Getting the RationCardMembersDetails getting
     * RationCardNumber
     */
    public ArrayList getRationCardMembersDetails(DataSource ds,DataSource civilDs, String rationCardNumber, HttpServletRequest request) throws SADAREMDBException, SQLException {
        ArrayList<Object> rationCardMembersDetails = null;
        PreparedStatement pstmt = null;
        Connection con = null;
        String sql = null;
        ResultSet rs = null;
        HashMap<String, Object> details = null;
        HttpSession session = request.getSession(true);
        // String tablename=" CivilSupply_Database.dbo.CivilFinal a ";
        String tablename = "  ";
        String districtid = (String) session.getAttribute("districtId");


        try {
            if (districtid != null) {
                tablename = " CivilSupply_Database.dbo.tblRationcard_details_" + districtid + " a ";

            }

            con = DBConnection.getConnection();

            sql = "select a.slno,coalesce(a.membername,'-'),coalesce(a.relation,'-'),"
                    + "case when a.gender='M' then 'Male' else 'Female' end as gender,"
                    + "coalesce(a.age,'-'),substring(a.householdcardno,4,2) as district from "
                    + tablename
                    + "  with (nolock) where a.householdcardno=? order by a.slno";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, rationCardNumber);
            rs = pstmt.executeQuery();
            if (rs != null) {
                rationCardMembersDetails = new ArrayList<Object>();
                while (rs.next()) {
                    details = new HashMap<String, Object>();
                    details.put("slNo", rs.getString(1));
                    details.put("memberName", rs.getString(2));
                    details.put("relation", rs.getString(3));
                    details.put("gender", rs.getString(4));
                    details.put("age", rs.getString(5));
                    details.put("district", rs.getString(6));
                    rationCardMembersDetails.add(details);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRationCardMembersDetails", "RationCardReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RationCardReportDAO", "getRationCardMembersDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRationCardMembersDetails", "RationCardReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RationCardReportDAO", "getRationCardMembersDetails");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return rationCardMembersDetails;
    }
}
