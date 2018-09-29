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
import org.bf.disability.dto.FilterCandidateDTO;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author vijay
 */
public class FilterCandidateDAO {

    public ArrayList getMandalDetails(DataSource ds, String district_id) throws SADAREMDBException,SQLException {

        ArrayList mandalList = new ArrayList();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String query = null;
        FilterCandidateDTO filterCandidateDTO = null;
        try {
           con = DBConnection.getConnection();
            query = "select mandal_id,mandal_name  from dbo.tblMandal_Details where district_id =? order by  mandal_name";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, district_id);

            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    filterCandidateDTO = new FilterCandidateDTO();
                    filterCandidateDTO.setMandal_id(rs.getString(1));
                    filterCandidateDTO.setMandal_name(rs.getString(2));
                    mandalList.add(filterCandidateDTO);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMandalDetails", "FilterCandidateDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FilterCandidateDAO", "getMandalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMandalDetails", "FilterCandidateDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FilterCandidateDAO", "getMandalDetails");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(pstmt);
           DBConnection.closeConnection(con);
        }
        return mandalList;
    }

    public ArrayList getPensionDetails(DataSource ds, String districtId, String mandal_id, String village_id) throws SADAREMDBException,SQLException {

        ArrayList villageList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String villageQuery = null;
        FilterCandidateDTO filterCandidateDTO = null;
        ArrayList duplicateRationCardDetails = new ArrayList();
        Map duplicateData = null;

        try {
           con = DBConnection.getConnection();
            villageQuery = "select p.pensioncard_no as PENSIIONID, p.habcode as HABCODE,"
                    + " p.person_code  as SADAREMCODE,p.surname +space(2)+p.first_name as PERSONNAME "
                    + ",p.age_years,p.rationcard_number, 'GENDER'=CASE WHEN p.gender=1 THEN 'Male' WHEN p.gender=2 "
                    + "THEN 'Female' END,'EDUCATION' = CASE WHEN P.EDUCATION = 1 THEN 'Illiterate' WHEN P.EDUCATION = 2 THEN 'Below 10th' WHEN P.EDUCATION = 3 THEN '10th Class' WHEN P.EDUCATION = 4 THEN "
                    + "'Intermediate'WHEN P.EDUCATION = 5 THEN 'Diploma/I.T.I' WHEN P.EDUCATION = 6 THEN 'Graduate' WHEN P.EDUCATION = 7 "
                    + "THEN 'Postgraduate'ELSE 'NOT ENTERED'END,p.relation_name as RELATIONNAME,'TYPEOFDISABILITY' = CASE WHEN d.DISABILITY_ID = 1"
                    + " THEN 'Locomotor/OH' WHEN d.DISABILITY_ID = 2 THEN 'Visual Impairment'WHEN d.DISABILITY_ID = 3 THEN "
                    + "'Hearing Impairment'WHEN d.DISABILITY_ID = 4 THEN 'Mental Retardation'WHEN d.DISABILITY_ID = 5 THEN 'Mental Illness'ELSE "
                    + "'Multiple Disability'END, t.TotalDisability as TOTALDISABILITY , p.reasonforpersonstatus, p.pensionphase"
                    + " from tblperson_personal_details p  with (nolock) join tblPerson_Disability_Details d on(p.person_code=d.person_code) join dbo.tblPerson_Disability_TotalValue_Details t on(p.person_code=t.person_code ) and p.District_ID=? where    p.status='Active' and d.status='Active' and t.status='Active' and p.District_ID='10' and  p.Mandal_ID =? and  p.Village_ID=?";
            
            pstmt = con.prepareStatement(villageQuery);
            
            pstmt.setString(1,districtId);
            pstmt.setString(2,mandal_id);
            pstmt.setString(3,village_id);
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                duplicateData = new HashMap();
                duplicateData.put("rationCardNumber", rs.getString(6));
                duplicateData.put("pensionCardNo", rs.getString(1));
                duplicateData.put("pensionCode", rs.getString(3));
                duplicateData.put("name", rs.getString(4));
                duplicateData.put("ralationName", rs.getString(9));
                duplicateData.put("education", rs.getString(8));
                duplicateData.put("reasonForStatus", rs.getString(12));
                duplicateData.put("pensionPhase", rs.getString(13));
                duplicateData.put("ageYears", rs.getString(5));
                duplicateData.put("gender", rs.getString(7));
                duplicateData.put("disability", rs.getString(10));
                duplicateRationCardDetails.add(duplicateData);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPensionDetails", "FilterCandidateDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FilterCandidateDAO", "getPensionDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPensionDetails", "FilterCandidateDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FilterCandidateDAO", "getPensionDetails");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeStatement(pstmt);
           DBConnection.closeResultSet(rs);

        }
        return duplicateRationCardDetails;
    }

    public ArrayList getVillageDetails(DataSource ds, String district_id, String mandal_id) throws SADAREMDBException,SQLException {

        ArrayList villageList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String villageQuery = null;
        FilterCandidateDTO filterCandidateDTO = null;


        try {
           con = DBConnection.getConnection();
            villageQuery = "select village_id,village_name from tblVillage_Details where District_ID=? and  mandal_id =? ";

            pstmt = con.prepareStatement(villageQuery);
            pstmt.setString(1, district_id);
            pstmt.setString(2, mandal_id);

            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    filterCandidateDTO = new FilterCandidateDTO();
                    filterCandidateDTO.setVillage_id(rs.getString(1));
                    filterCandidateDTO.setVillage_name(rs.getString(2));
                    villageList.add(filterCandidateDTO);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getVillageDetails", "FilterCandidateDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FilterCandidateDAO", "getVillageDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getVillageDetails", "FilterCandidateDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FilterCandidateDAO", "getVillageDetails");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeStatement(pstmt);
           DBConnection.closeResultSet(rs);

        }
        return villageList;
    }

    public ArrayList getReportRationCardDuplicateDetails(DataSource ds, String district_id, String mandal_id, String village_id) throws SADAREMDBException,SQLException {

        ArrayList duplicateRationCardDetails = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        Map duplicateData = null;

        try {
           con = DBConnection.getConnection();

            /* Populate All Mandal Details*/
            if (!district_id.equals("0") && mandal_id.equals("0")) {
                query = "SELECT  upper(a.rationcard_number) as RationCard,a.pensioncard_no,a.person_code,a.surname+space(2)+a.first_name as Name,"
                        + " a.relation_name,c.mandal_name,a.reasonforpersonstatus,a.pensionphase,"
                        + "a.age_years,CASE WHEN a.gender = 1 THEN 'Male' WHEN a.gender = 2 THEN 'Female' ELSE 'Not Entered' END as gender, "
                        + " CASE WHEN a.view_edit_mode = 'View'"
                        + "THEN 'Completed' WHEN a.view_edit_mode ='Edit' THEN 'Not Completed' ELSE 'Not Entered' END as "
                        + "viewEditMode  from dbo.tblPerson_Personal_Details a  with (nolock) left join "
                        + "Disabled_Pension b on"
                        + "(a.district_id=b.distcode and a.mandal_id=mndcode and a.district_id =? and  "
                        + " a.pensioncard_no = b.pensionid and b.rationcardno = a.rationcard_number)"
                        + "join tblMandal_Details  c on(a.district_id =c.district_id and a.mandal_id =c.mandal_id )"
                        + "WHERE a.district_id =? and substring(a.reasonforpersonstatus,1,3)!='You' and "
                        + " a.rationcard_number IN (SELECT a.rationcard_number FROM tblperson_personal_details a  with (nolock)  where a.district_id =?  and substring(a.reasonforpersonstatus,1,3)!='You'"
                        + "GROUP BY a.rationcard_number HAVING COUNT(*) > 1 ) ORDER BY a.rationcard_number";

                pstmt = con.prepareStatement(query);

                pstmt.setString(1, district_id);
                pstmt.setString(2, district_id);
                pstmt.setString(3, district_id);
                rs = pstmt.executeQuery();

                if (rs != null) {
                    while (rs.next()) {

                        duplicateData = new HashMap();
                        duplicateData.put("rationCardNumber", rs.getString(1));
                        duplicateData.put("pensionCardNo", rs.getString(2));
                        duplicateData.put("pensionCode", rs.getString(3));
                        duplicateData.put("name", rs.getString(4));
                        duplicateData.put("ralationName", rs.getString(5));
                        duplicateData.put("territaoryDetails", rs.getString(6));
                        duplicateData.put("reasonForStatus", rs.getString(7));
                        duplicateData.put("pensionPhase", rs.getString(8));
                        duplicateData.put("ageYears", rs.getString(9));
                        duplicateData.put("gender", rs.getString(10));
                        duplicateData.put("assessmentStatus", rs.getString(11));
                        duplicateRationCardDetails.add(duplicateData);
                    }
                }
            } /* Populate with Selecting Mandal Details and Village All Details*/ else if (!district_id.equals("0") && !mandal_id.equals("0") && village_id.equals("0")) {


                query = "SELECT  upper(a.rationcard_number) as RationCard,a.pensioncard_no,a.person_code,a.surname+space(2)+a.first_name as Name,"
                        + " a.relation_name,c.mandal_name,"
                        + "d.village_name,"
                        + "a.reasonforpersonstatus,a.pensionphase,"
                        + "a.age_years,CASE WHEN a.gender = 1 THEN 'Male' WHEN a.gender = 2 THEN 'Female' ELSE 'Not Entered' END as gender, "
                        + " CASE WHEN a.view_edit_mode = 'View'"
                        + "THEN 'Completed' WHEN a.view_edit_mode ='Edit' THEN 'Not Completed' ELSE 'Not Entered' END as viewEditMode  from dbo.tblPerson_Personal_Details a  with (nolock) left join "
                        + "Disabled_Pension b on"
                        + "(a.pensioncard_no = b.pensionid and b.rationcardno = a.rationcard_number)"
                        + " and (a.district_id=b.distcode and a.mandal_id=b.mndcode and a.district_id =? and  a.mandal_id =?)"
                        + " join tblMandal_Details  c on(a.district_id =c.district_id and a.mandal_id =c.mandal_id)"
                        + " join tblVillage_Details d on(a.district_id =d.district_id and a.mandal_id =d.mandal_id "
                        + "and a.village_id =d.village_id) "
                        + " WHERE a.district_id =? and  a.mandal_id =? and substring(a.reasonforpersonstatus,1,3)!='You'"
                        + " and a.rationcard_number IN (SELECT a.rationcard_number FROM tblperson_personal_details a  with (nolock)  WHERE a.district_id =? and  a.mandal_id =? and substring(a.reasonforpersonstatus,1,3)!='You'"
                        + "GROUP BY a.rationcard_number HAVING COUNT(*) > 1 ) ORDER BY a.rationcard_number";


                pstmt = con.prepareStatement(query);
                pstmt.setString(1, district_id);
                pstmt.setString(2, mandal_id);
                pstmt.setString(3, district_id);
                pstmt.setString(4, mandal_id);
                pstmt.setString(5, district_id);
                pstmt.setString(6, mandal_id);

                rs = pstmt.executeQuery();

                if (rs != null) {
                    while (rs.next()) {

                        duplicateData = new HashMap();
                        duplicateData.put("rationCardNumber", rs.getString(1));
                        duplicateData.put("rationCardCountDetails", getRationCardCountDetails(ds, rs.getString(1)));
                        duplicateData.put("pensionCardNo", rs.getString(2));
                        duplicateData.put("pensionCode", rs.getString(3));
                        duplicateData.put("name", rs.getString(4));
                        duplicateData.put("ralationName", rs.getString(5));
                        duplicateData.put("mandalName", rs.getString(6));
                        duplicateData.put("territaoryDetails", rs.getString(7));
                        duplicateData.put("reasonForStatus", rs.getString(8));
                        duplicateData.put("pensionPhase", rs.getString(9));
                        duplicateData.put("ageYears", rs.getString(10));
                        duplicateData.put("gender", rs.getString(11));
                        duplicateData.put("assessmentStatus", rs.getString(12));
                        duplicateRationCardDetails.add(duplicateData);

                    }
                }
            } /* Selecting with mandal and Village Details and Generating  Report */ else if (!district_id.equals("0") && !mandal_id.equals("0") && !village_id.equals("0")) {
                query = "SELECT  upper(a.rationcard_number) as RationCard,a.pensioncard_no,a.person_code,a.surname+space(2)+a.first_name as Name,"
                        + " a.relation_name,c.mandal_name,d.village_name,"
                        + "e.Habitation_name,"
                        + "a.reasonforpersonstatus,a.pensionphase,"
                        + "a.age_years,CASE WHEN a.gender = 1 THEN 'Male' WHEN a.gender = 2 THEN 'Female' ELSE 'Not Entered' END as gender, "
                        + " CASE WHEN a.view_edit_mode = 'View'"
                        + "THEN 'Completed' WHEN a.view_edit_mode ='Edit' THEN 'Not Completed' ELSE 'Not Entered' END as viewEditMode  from dbo.tblPerson_Personal_Details a  with (nolock) left join "
                        + "Disabled_Pension b on"
                        + "(a.pensioncard_no = b.pensionid and b.rationcardno = a.rationcard_number and "
                        + " a.district_id =? and  a.mandal_id =? and a.village_id=?)"
                        + " join tblMandal_Details  c on(a.district_id =c.district_id and a.mandal_id =c.mandal_id )"
                        + " join tblVillage_Details d on(a.district_id =d.district_id and a.mandal_id =d.mandal_id "
                        + " and a.village_id =d.village_id)"
                        + " join tblHabitation_Details e on(a.district_id =e.district_id "
                        + " and a.mandal_id =e.mandal_id and a.village_id =e.village_id and a.Habitation_ID=e.Habitation_ID) "
                        + " WHERE a.district_id =? and  a.mandal_id =? and a.village_id=? and substring(a.reasonforpersonstatus,1,3)!='You'"
                        + " and a.rationcard_number IN (SELECT a.rationcard_number FROM tblperson_personal_details a  with (nolock) WHERE a.district_id =? and  a.mandal_id =? and a.village_id=? and substring(a.reasonforpersonstatus,1,3)!='You'"
                        + " GROUP BY a.rationcard_number HAVING COUNT(*) > 1 ) ORDER BY a.rationcard_number";

                pstmt = con.prepareStatement(query);
                pstmt.setString(1, district_id);
                pstmt.setString(2, mandal_id);
                pstmt.setString(3, village_id);
                pstmt.setString(4, mandal_id);
                pstmt.setString(5, district_id);
                pstmt.setString(6, village_id);
                pstmt.setString(7, mandal_id);
                pstmt.setString(8, district_id);
                pstmt.setString(9, village_id);

                rs = pstmt.executeQuery();

                if (rs != null) {
                    while (rs.next()) {

                        duplicateData = new HashMap();
                        duplicateData.put("rationCardNumber", rs.getString(1));
                        duplicateData.put("pensionCardNo", rs.getString(2));
                        duplicateData.put("pensionCode", rs.getString(3));
                        duplicateData.put("name", rs.getString(4));
                        duplicateData.put("ralationName", rs.getString(5));
                        duplicateData.put("mandalName", rs.getString(6));
                        duplicateData.put("villageName", rs.getString(7));
                        duplicateData.put("territaoryDetails", rs.getString(8));
                        duplicateData.put("reasonForStatus", rs.getString(9));
                        duplicateData.put("pensionPhase", rs.getString(10));
                        duplicateData.put("ageYears", rs.getString(11));
                        duplicateData.put("gender", rs.getString(12));
                        duplicateData.put("assessmentStatus", rs.getString(13));
                        duplicateRationCardDetails.add(duplicateData);
                    }
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getReportRationCardDuplicateDetails", "FilterCandidateDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FilterCandidateDAO", "getReportRationCardDuplicateDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getReportRationCardDuplicateDetails", "FilterCandidateDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FilterCandidateDAO", "getReportRationCardDuplicateDetails");
        }finally {
           DBConnection.closeConnection(con);
           DBConnection.closeStatement(pstmt);
           DBConnection.closeResultSet(rs);

        }
        return duplicateRationCardDetails;
    }

    public int getUpdateDuplicateDetails(DataSource ds, String pensionCard, String duplicatePensioncode, String district) throws SADAREMDBException,SQLException {

        int updateDuplicateDetails = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;
        String query = null;

        try {
           con = DBConnection.getConnection();
            
            sql = "update dbo.tblPerson_Personal_Details set ReasonforpersonStatus = ?,status ='Inactive' where pensioncard_no=? and district_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,"You are Duplicate for this pension number "+ pensionCard);
            pstmt.setString(2, duplicatePensioncode.trim());
            pstmt.setString(3, district);
            updateDuplicateDetails = pstmt.executeUpdate();
            query = "update Disabled_pension  set reasonforpersonstatus=? where pensionid=? and distcode=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, "You are Duplicate for this pension number "+ pensionCard);
            pstmt.setString(2, duplicatePensioncode.trim());
            pstmt.setString(3, district);
            updateDuplicateDetails = pstmt.executeUpdate();


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getUpdateDuplicateDetails", "FilterCandidateDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FilterCandidateDAO", "getUpdateDuplicateDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getUpdateDuplicateDetails", "FilterCandidateDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FilterCandidateDAO", "getUpdateDuplicateDetails");
        }finally {
           DBConnection.closeConnection(con);
           DBConnection.closeStatement(pstmt);
        }
        return updateDuplicateDetails;

    }

    public String getDistrictNameDetails(DataSource ds, String district_id) throws SADAREMDBException,SQLException {

        String districtName = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        try {

           con = DBConnection.getConnection();
            query = "select district_name from tblDistrict_Details where district_id=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, district_id);

            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    districtName = rs.getString(1);
                }
            }
        }catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDistrictNameDetails", "FilterCandidateDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FilterCandidateDAO", "getDistrictNameDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDistrictNameDetails", "FilterCandidateDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FilterCandidateDAO", "getDistrictNameDetails");
        }finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeConnection(con);
           DBConnection.closeStatement(pstmt);
        }
        return districtName;


    }

    public String getRationCardCountDetails(DataSource ds, String rationCardId) throws SADAREMDBException,SQLException {

        String rationCardCountDetails = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;

        try {
           con = DBConnection.getConnection();

            query = "select count(rationCard_number) from tblperson_personal_Details  with (nolock) where rationCard_number =?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, rationCardId);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    rationCardCountDetails = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getRationCardCountDetails", "FilterCandidateDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FilterCandidateDAO", "getRationCardCountDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getRationCardCountDetails", "FilterCandidateDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FilterCandidateDAO", "getRationCardCountDetails");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeConnection(con);
           DBConnection.closeStatement(pstmt);
        }
        return rationCardCountDetails;
    }
}
