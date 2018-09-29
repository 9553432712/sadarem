/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.IssueRaiseApprovalDTO;
import org.bf.disability.form.IssueRaiseApprovalForm;

import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 693461
 */
public class IssueRaiseApprovalDAO {

    public ArrayList getCampBasedOnLoginDetails(DataSource ds, String districtId, String personCode) throws SADAREMDBException, SQLException {

        ArrayList campList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        String query = null;
        IssueRaiseApprovalDTO issueRaiseApprovalDTO = null;
        Map map = null;
        String hospitalNameQuery = null;
        String venueName = null;

        try {
            con = DBConnection.getConnection();
            hospitalNameQuery = "select hospital_name,venueName from tblPerson_Disability_Details"
                    + " where person_code='" + personCode + "' and status='Active'";
            pstmt1 = con.prepareStatement(hospitalNameQuery);
            rs1 = pstmt1.executeQuery();
            if (rs1 != null) {
                while (rs1.next()) {
                    venueName = rs1.getString(2);
                }
            }
            query = "select distinct hospital_name,venueName from dbo.tblPerson_Disability_Details"
                    + " where substring(person_code,1,2) = '" + districtId + "' and status = 'Active' and venueName <>'" + venueName + "'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("campId", rs.getString(1) + ", " + rs.getString(2));
                    campList.add(map);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampBasedOnLoginDetails", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getCampBasedOnLoginDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampBasedOnLoginDetails", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getCampBasedOnLoginDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeResultSet(rs1);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(pstmt1);
        }
        return campList;
    }

    public String approvalVenueDetails(DataSource ds, String personCode, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        String venueName = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        Map map = null;
        String districtId = null;
        try {
            con = DBConnection.getConnection();
            districtId = personCode.substring(0, 2);
            query = "select top (1)  venuename from dbo.tblPerson_Disability_Details"
                    + " where substring(person_code,1,2)  = '" + districtId + "' and  "
                    + "hospital_name = '" + issueRaiseApprovalForm.getResolveCampName() + "'"
                    + " and hospital_Address='" + issueRaiseApprovalForm.getResolveMedicalAddress() + "' and status = 'Active'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    venueName = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampBasedOnLoginDetails", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getCampBasedOnLoginDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampBasedOnLoginDetails", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getCampBasedOnLoginDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }
        return venueName;
    }

    public ArrayList getCatgoryDetails(DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList catgoryList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        IssueRaiseApprovalDTO issueRaiseApprovalDTO = null;
        try {
            con = DBConnection.getConnection();
            query = "select category_id,category_name  from IssueCategories"
                    + " where status ='Active' order by category_name";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    issueRaiseApprovalDTO = new IssueRaiseApprovalDTO();
                    issueRaiseApprovalDTO.setCategoryId(rs.getString(1));
                    issueRaiseApprovalDTO.setCategoryname(rs.getString(2));
                    catgoryList.add(issueRaiseApprovalDTO);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCatgoryDetails", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getCatgoryDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCatgoryDetails", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getCatgoryDetails");
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return catgoryList;

    }

    public String getCampNameDetails(DataSource ds, String campId) throws SADAREMDBException, SQLException {

        String campName = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            query = "select name,address from tblcamp_Details where camp_id ='" + campId + "'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    campName = rs.getString(1) + ", " + rs.getString(2);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampNameDetails", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getCampNameDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampNameDetails", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getCampNameDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }
        return campName;
    }

    public int insertIssueRasingDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String campName, String systemIp, String loginId, String districtId, String rationCardNo) throws SADAREMDBException, SQLException {

        int insertDetails = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        CallableStatement cstmt = null;
        String query = null;
        String status = "Pending";
        String dateOfIssue = null;
        String rationCarNo = null;
        String d = "";
        String e = "";

        try {
            if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RATIONCARDADDRESSCHANGE)
                    || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RELATIONTYPECHANGE)
                    || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.SUBTYPEDISABILITYTYPE)
                    || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.SUB_SUB_TYPEDISABILITYTYPE)
                    || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.DIAGNOSUBDISABILITYTYPE)
                    || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RELATIONNAME)
                    || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.SURNAME)
                    || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEI)
                    || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEII)
                    || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEIV)
                    //|| issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.ADDRESSCHANGEPHASEIII) 
                    
            		) {
                status = "Resolved";
            }
            if (issueRaiseApprovalForm.getDateOfIssue() != null && !issueRaiseApprovalForm.getDateOfIssue().equals("")) {
                Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(issueRaiseApprovalForm.getDateOfIssue());
                dateOfIssue = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            }


            int i = 1;
            if (issueRaiseApprovalForm.getSubdisabilityId() != null) {
                for (String s : issueRaiseApprovalForm.getSubdisabilityId()) {

                    if (s != null) {
                        d += s + ",";

                    } else if (i == 1) {
                        d += s;

                    }
                    i++;
                }
                d = d.substring(0, d.length() - 1);
            }
            int j = 1;
            if (issueRaiseApprovalForm.getSubsubdisabilityId() != null) {
                for (String sub : issueRaiseApprovalForm.getSubsubdisabilityId()) {

                    if (sub != null) {
                        e += sub + ",";

                    } else if (j == 1) {
                        e += sub;

                    }
                    j++;
                }

                e = e.substring(0, e.length() - 1);
            }

            con = DBConnection.getConnection();
            query = "INSERT INTO IssueTrackerDetails(Camp_id,Camp_name,Category,"
                    + "Sadarem_id,Pension_id,RationCard,Description,"
                    + " Status,Created_date,Updated_date,LoginId,SystemIP,requeststatus,District_id,mobileno,newSadarem_id,username,SurName,Name,RelationName,RationCardSerialNo,memberName,medicalAddress,newPensionId,user_uploaded_document,DateOfIssue,newDistrict_id"
                    + ",ExitingRelationType,relationType,Typeof_disability,sub_disabilty,Sub_sub_disabilty,Diagnosis_of_Disability,New_SADAREMID,newmandal_id,newvillage_id ,newpanchyat_id,newhab_id,newhouse_no,newpin_code)"
                    + " VALUES(?,?,?,?,?,?,?,'Active',getDate(),getDate(),'" + loginId + "',"
                    + "'" + systemIp + "','" + status + "',?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, "");

            if (issueRaiseApprovalForm.getMedicalBoardId() != null && !issueRaiseApprovalForm.getMedicalBoardId().equals("")) {
                String[] a = issueRaiseApprovalForm.getMedicalBoardId().split(",");
                if (a != null) {
                    pstmt.setString(2, a[0]);
                }
            } else {
                pstmt.setString(2, "");
            }
            if (issueRaiseApprovalForm.getCategoryFormId() != null && !issueRaiseApprovalForm.getCategoryFormId().equals("0")) {
                pstmt.setString(3, issueRaiseApprovalForm.getCategoryFormId());
            } else {
                pstmt.setString(3, null);
            }
            if (issueRaiseApprovalForm.getCategoryFormId() != null && issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RATIONCARDSERIALNUMBERCHANGE)) {
                if (issueRaiseApprovalForm.getSadaremIdRationCardSlno() != null && !issueRaiseApprovalForm.getSadaremIdRationCardSlno().equals("")) {
                    pstmt.setString(4, issueRaiseApprovalForm.getSadaremIdRationCardSlno());
                } else {
                    pstmt.setString(4, null);
                }
            } else {
                if (issueRaiseApprovalForm.getSadaremId() != null && !issueRaiseApprovalForm.getSadaremId().equals("")) {
                    pstmt.setString(4, issueRaiseApprovalForm.getSadaremId());
                } else {
                    pstmt.setString(4, null);
                }
            }
            if (issueRaiseApprovalForm.getPensionId() != null && !issueRaiseApprovalForm.getPensionId().equals("")) {
                pstmt.setString(5, issueRaiseApprovalForm.getPensionId());
            } else {
                pstmt.setString(5, null);
            }

            if (issueRaiseApprovalForm.getRationCardNumber() != null && !issueRaiseApprovalForm.getRationCardNumber().equals("") && issueRaiseApprovalForm.getRationCardNumber().length() > 0) {
                pstmt.setString(6, issueRaiseApprovalForm.getRationCardNumber());
            } else {
                pstmt.setString(6, null);
            }
            if (issueRaiseApprovalForm.getDecription() != null && !issueRaiseApprovalForm.getDecription().equals("")) {
                pstmt.setString(7, issueRaiseApprovalForm.getDecription());
            } else {
                pstmt.setString(7, null);
            }
            pstmt.setString(8, districtId);
            pstmt.setString(9, issueRaiseApprovalForm.getMobile());
            if (issueRaiseApprovalForm.getNewSadaremId() != null && !issueRaiseApprovalForm.getNewSadaremId().equals("")) {
                pstmt.setString(10, issueRaiseApprovalForm.getNewSadaremId());

            } else {
                pstmt.setString(10, null);
            }
            if (issueRaiseApprovalForm.getForgetUserName() != null && !issueRaiseApprovalForm.getForgetUserName().equals("")) {
                pstmt.setString(11, issueRaiseApprovalForm.getForgetUserName());
            } else {
                pstmt.setString(11, null);
            }
            if (issueRaiseApprovalForm.getNewSurName() != null && !issueRaiseApprovalForm.getNewSurName().equals("")) {
                pstmt.setString(12, issueRaiseApprovalForm.getNewSurName());
            } else {
                pstmt.setString(12, null);
            }
            if (issueRaiseApprovalForm.getNewName() != null && !issueRaiseApprovalForm.getNewName().equals("")) {
                pstmt.setString(13, issueRaiseApprovalForm.getNewName());

            } else {
                pstmt.setString(13, null);
            }
            if (issueRaiseApprovalForm.getNewRelationName() != null && !issueRaiseApprovalForm.getNewRelationName().equals("")) {
                pstmt.setString(14, issueRaiseApprovalForm.getNewRelationName());

            } else {
                pstmt.setString(14, null);
            }
            if (issueRaiseApprovalForm.getNewSerialNo() != null && !issueRaiseApprovalForm.getNewSerialNo().equals("")) {
                pstmt.setString(15, issueRaiseApprovalForm.getNewSerialNo());
            } else {
                pstmt.setString(15, null);
            }
            if (issueRaiseApprovalForm.getMemberName() != null && !issueRaiseApprovalForm.getMemberName().equals("")) {
                pstmt.setString(16, issueRaiseApprovalForm.getMemberName());
            } else {
                pstmt.setString(16, null);
            }
            if (issueRaiseApprovalForm.getCampAddress() != null && !issueRaiseApprovalForm.getCampAddress().equals("")) {
                pstmt.setString(17, issueRaiseApprovalForm.getCampAddress());
            } else {
                pstmt.setString(17, null);
            }

            if (issueRaiseApprovalForm.getNewPensionId() != null && !issueRaiseApprovalForm.getNewPensionId().equals("")) {
                pstmt.setString(18, issueRaiseApprovalForm.getNewPensionId());

            } else {
                pstmt.setString(18, null);
            }
            if (issueRaiseApprovalForm.getIsseRaisefilePath() != null && !issueRaiseApprovalForm.getIsseRaisefilePath().equals("") && issueRaiseApprovalForm.getIsseRaisefilePath().length() > 0) {
                pstmt.setString(19, issueRaiseApprovalForm.getIsseRaisefilePath());

            } else {
                pstmt.setString(19, null);
            }
            if (dateOfIssue != null && !dateOfIssue.equals("")) {
                pstmt.setString(20, dateOfIssue);

            } else {
                pstmt.setString(20, null);
            }

            if (issueRaiseApprovalForm.getDistrict_id() != null && !issueRaiseApprovalForm.getDistrict_id().equals("")) {
                pstmt.setString(21, issueRaiseApprovalForm.getDistrict_id());

            } else {
                pstmt.setString(21, null);
            }
            if (issueRaiseApprovalForm.getRelationType() != null && !issueRaiseApprovalForm.getRelationType().equals("")) {
                if (issueRaiseApprovalForm.getRelationType().equals("Father")) {
                    pstmt.setString(22, "1");
                } else if (issueRaiseApprovalForm.getRelationType().equals("Mother")) {
                    pstmt.setString(22, "2");
                } else if (issueRaiseApprovalForm.getRelationType().equals("Husband")) {
                    pstmt.setString(22, "3");
                } else if (issueRaiseApprovalForm.getRelationType().equals("Wife")) {
                    pstmt.setString(22, "7");
                } else if (issueRaiseApprovalForm.getRelationType().equals("Brother")) {
                    pstmt.setString(22, "5");
                } else if (issueRaiseApprovalForm.getRelationType().equals("Sister")) {
                    pstmt.setString(22, "6");
                } else if (issueRaiseApprovalForm.getRelationType().equals("Guardian")) {
                    pstmt.setString(22, "4");
                } else {
                    pstmt.setString(22, null);
                }
            } else {
                pstmt.setString(22, null);
            }
            if (issueRaiseApprovalForm.getPopulaterelationType() != null && !issueRaiseApprovalForm.getPopulaterelationType().equals("")) {
                pstmt.setString(23, issueRaiseApprovalForm.getPopulaterelationType());

            } else {
                pstmt.setString(23, null);
            }

            if (issueRaiseApprovalForm.getTypeofdisabilityId() != null && !issueRaiseApprovalForm.getTypeofdisabilityId().equals("")) {
                pstmt.setString(24, issueRaiseApprovalForm.getTypeofdisabilityId());


            } else {
                pstmt.setString(24, null);
            }

            if (issueRaiseApprovalForm.getSubdisabilityId() != null && !issueRaiseApprovalForm.getSubdisabilityId().equals("")) {

                pstmt.setString(25, d);

            } else {
                pstmt.setString(25, null);
            }

            if (issueRaiseApprovalForm.getSubsubdisabilityId() != null && !issueRaiseApprovalForm.getSubsubdisabilityId().equals("")) {

                pstmt.setString(26, e);

            } else {
                pstmt.setString(26, null);
            }
            if (issueRaiseApprovalForm.getDiagnosisDisability() != null && !issueRaiseApprovalForm.getDiagnosisDisability().equals("")) {

                pstmt.setString(27, issueRaiseApprovalForm.getDiagnosisDisability());

            } else {
                pstmt.setString(27, null);
            }
            if (issueRaiseApprovalForm.getAddresschangeSADAREMID() != null && !issueRaiseApprovalForm.getAddresschangeSADAREMID().equals("")) 
            {

                pstmt.setString(28, issueRaiseApprovalForm.getAddresschangeSADAREMID());
            } else 
            {
                pstmt.setString(28, null);
            }
            
            if (issueRaiseApprovalForm.getHabitation_id() != null && !issueRaiseApprovalForm.getHabitation_id().equals("")) 
            {

                pstmt.setString(29, issueRaiseApprovalForm.getHabitation_id().substring(5, 7));
                pstmt.setString(30, issueRaiseApprovalForm.getHabitation_id().substring(7,10));
                pstmt.setString(31, issueRaiseApprovalForm.getHabitation_id().substring(10,12));
                pstmt.setString(32, issueRaiseApprovalForm.getHabitation_id());
                pstmt.setString(33, issueRaiseApprovalForm.getHouseNo());
                pstmt.setString(34, issueRaiseApprovalForm.getPin());
                
                
            } else 
            {
                pstmt.setString(29, null);
                pstmt.setString(30, null);
                pstmt.setString(31, null);
                pstmt.setString(32, null);
                pstmt.setString(33, null);
                pstmt.setString(34, null);
            }
            
            
            
            
            insertDetails = pstmt.executeUpdate();
            if (insertDetails == 1 && issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RATIONCARDADDRESSCHANGE)
                    && issueRaiseApprovalForm.getRationCardNumber() != null && !issueRaiseApprovalForm.getRationCardNumber().equalsIgnoreCase("") && !issueRaiseApprovalForm.getRationCardHidedndistrictId().equals("")) {
                String sql = "select * from RationcardNewAddressDetails where RationCard='" + issueRaiseApprovalForm.getRationCardNumber() + "' and Status='Active'";
                pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    sql = "update RationcardNewAddressDetails set Status='InActive' where RationCard='" + issueRaiseApprovalForm.getRationCardNumber() + "' and Status='Active'";
                    pstmt = con.prepareStatement(sql);
                    pstmt.executeUpdate();
                }
                query = "INSERT INTO RationcardNewAddressDetails(RationCard,ExisingDistrictId,NewDistrictId,Status,LoginId,SystemIP)"
                        + " VALUES(?,?,?,'Active','" + loginId + "','" + systemIp + "')";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, issueRaiseApprovalForm.getRationCardNumber());
                pstmt.setString(2, issueRaiseApprovalForm.getRationCardNumber().substring(3, 5));
                pstmt.setString(3, issueRaiseApprovalForm.getRationCardHidedndistrictId());
                insertDetails = pstmt.executeUpdate();
            }

            /*//balu Start
            if (insertDetails == 1 && issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.ADDRESSCHANGEPHASEIII)
            && issueRaiseApprovalForm.getAddresschangeSADAREMID() != null && !issueRaiseApprovalForm.getAddresschangeSADAREMID().equalsIgnoreCase("")
            && issueRaiseApprovalForm.getSadaremId() != null
            && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("")) {
            //System.out.println("balu code here...............DAO");
            cs = con.prepareCall("{Call [SP_DeleteDatainAllTables_for_PHASEIIIV1](?,?)}");
            cs.setString(1, issueRaiseApprovalForm.getSadaremId());
            cs.setString(2, issueRaiseApprovalForm.getAddresschangeSADAREMID());
            insertDetails = cs.executeUpdate();

            }
            //balu End*/


            if (insertDetails == 1 && issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RELATIONTYPECHANGE)
                    && issueRaiseApprovalForm.getSadaremId() != null
                    && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("")) {
                query = "update tblperson_personal_Details set relationship ='" + issueRaiseApprovalForm.getPopulaterelationType() + "' "
                        + " , updated_date =getDate()"
                        + " where person_code ='" + issueRaiseApprovalForm.getSadaremId() + "' and Status='Active' ";

                pstmt = con.prepareStatement(query);
                insertDetails = pstmt.executeUpdate();
            }

            if (insertDetails != 0 && issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.SUBTYPEDISABILITYTYPE)
                    && issueRaiseApprovalForm.getSadaremId() != null
                    && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("")) {
                query = "update dbo.tblPerson_Disability_Details set sub_disability_id='" + d + "',updated_date =getDate() "
                        + "where person_code='" + issueRaiseApprovalForm.getSadaremId() + "'and status='active'";
                pstmt = con.prepareStatement(query);
                insertDetails = pstmt.executeUpdate();

            }
            if (insertDetails == 1 && issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.SUB_SUB_TYPEDISABILITYTYPE)
                    && issueRaiseApprovalForm.getSadaremId() != null
                    && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("")) {
                query = "  update dbo.tblPerson_Disability_Details set Sub_Sub_Disability_ID='" + e + "',updated_date =getDate() "
                        + "where person_code='" + issueRaiseApprovalForm.getSadaremId() + "'and status='active'";
                pstmt = con.prepareStatement(query);
                insertDetails = pstmt.executeUpdate();
            }
            if (insertDetails == 1 && issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.DIAGNOSUBDISABILITYTYPE)
                    && issueRaiseApprovalForm.getSadaremId() != null
                    && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("")) {
                query = "  update dbo.tblPerson_Disability_Details set Diagnosis_of_Disability='" + issueRaiseApprovalForm.getDiagnosisDisability() + "',updated_date =getDate() "
                        + "where person_code='" + issueRaiseApprovalForm.getSadaremId() + "'and status='active'";
                pstmt = con.prepareStatement(query);
                insertDetails = pstmt.executeUpdate();
            }

            if (insertDetails == 1 && (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RELATIONNAME) || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.SURNAME))
                    && issueRaiseApprovalForm.getSadaremId() != null
                    && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("")) {
                cstmt = con.prepareCall("{Call [sp_name_relationship_surname_change](?,?,?,?)}");

                cstmt.setString(1, issueRaiseApprovalForm.getSadaremId());
                cstmt.setString(3, "SADAREMDevTeam");
                if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RELATIONNAME)) {
                    cstmt.setString(2, issueRaiseApprovalForm.getNewRelationName());
                    cstmt.setString(4, CommonConstants.RELATIONNAMECHANGE);
                } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.SURNAME)) {
                    cstmt.setString(2, issueRaiseApprovalForm.getNewSurName());
                    cstmt.setString(4, CommonConstants.SURNAMECHANGE);
                }
                insertDetails = cstmt.executeUpdate();

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertIssueRasingDetails", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "insertIssueRasingDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertIssueRasingDetails", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "insertIssueRasingDetails");
        } finally {


            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(cstmt);

        }

        return insertDetails;

    }

    public String getrequestMaxId(DataSource ds) throws SADAREMDBException, SQLException {

        String requestMaxId = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            query = "select  max(request_id)  from IssueTrackerDetails";
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    requestMaxId = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getrequestMaxId", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getrequestMaxId");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getrequestMaxId", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getrequestMaxId");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return requestMaxId;
    }

    public ArrayList getApprovalListDetails(DataSource ds, String district_Id, String category, String status, String issueId) throws SADAREMDBException, SQLException {

        ArrayList resolveList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuffer query = null;
        Map resolveData = null;
        String reqDate = null;
        String resolveDate = null;
        try {
            if (status == null) {
                status = "Pending";
            }
            con = DBConnection.getConnection();
            query = new StringBuffer();
            query.append("select a.request_id,b.category_name,a.camp_name,a.created_Date,"
                    + " a.status,a.requeststatus,a.description,a.category,a.updated_Date,Comments,Comments"
                    + " from IssueTrackerDetails a join IssueCategories b "
                    + " on(b.category_id=a.category) where  requeststatus ='" + status + "'  and a.status='Active'");
            if (district_Id != null && !district_Id.equals("") && !district_Id.equals("0")) {
                query.append("  and District_id='" + district_Id + "'");
            }
//            if (!medicalBoard_Id.equals("0")) {
//                query.append("  and camp_id='" + medicalBoard_Id + "' ");
//            }
            if (category != null && !district_Id.equals("") && !category.equals("0")) {
                query.append("  and category ='" + category + "' ");
            }
            if (issueId != null && !issueId.equalsIgnoreCase("") && !issueId.equalsIgnoreCase("null")) {
                query.append("  and request_id ='" + issueId + "' ");
            }
            query.append(" order by created_date ");
            pstmt = con.prepareStatement(query.toString());
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    resolveData = new HashMap();
                    resolveData.put("requestId", rs.getString(1));
                    resolveData.put("categoryName", rs.getString(2));
                    //resolveData.put("campName", rs.getString(3));
                    reqDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(rs.getString(4)));
                    resolveData.put("createdDate", reqDate);
                    resolveData.put("status", rs.getString(5));
                    resolveData.put("requeststatus", rs.getString(6));
                    if (rs.getString(7) != null && !rs.getString(7).trim().equals("")) {
                        resolveData.put("description", rs.getString(7));
                    } else {
                        resolveData.put("description", "-");
                    }
                    resolveData.put("categoryId", rs.getString(8));
                    resolveDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(rs.getString(9)));
                    if (rs.getString(6) != null && rs.getString(6).equalsIgnoreCase("Resolved")) {
                        resolveData.put("updatedDate", resolveDate);
                    } else {
                        resolveData.put("updatedDate", "-");
                    }
                    if (rs.getString(10) != null && !rs.getString(10).equals("")) {
                        resolveData.put("comments", rs.getString(10));
                    } else {
                        resolveData.put("comments", "-");
                    }
                    if (rs.getString(11) != null && !rs.getString(11).equals("")) {
                        resolveData.put("comments", rs.getString(11));
                    } else {
                        resolveData.put("comments", "-");
                    }


                    resolveList.add(resolveData);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getApprovalListDetails", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getApprovalListDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getApprovalListDetails", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getApprovalListDetails");
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return resolveList;


    }

    public ArrayList getRequestRaisedDetails(DataSource ds, String requestId, String categoryId) throws SADAREMDBException, SQLException {

        ArrayList requestList = new ArrayList();
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map requestRaisedId = null;
        Date fdate = null;
        String date = null;

        try {
            con = DBConnection.getConnection();

            query = "select a.camp_id,a.camp_name,b.category_id,b.category_name,"
                    + " a.Sadarem_id,a.pension_id,a.rationcard,a.description,a.request_id,"
                    + " a.user_uploaded_document,a.username,a.medicaladdress,a.surname,a.name,"
                    + " a.relationname,a.rationcardserialno,newsadarem_id,a.comments,a.comments,"
                    + " a.membername,a.mobileno,a.district_id,dateofissue,newDistrict_id";
            if (categoryId != null && !categoryId.equals("") && (categoryId.equals(CommonConstants.NAME)
                    || categoryId.equals(CommonConstants.RELATIONNAME) || categoryId.equals(CommonConstants.SURNAME)
                    || (categoryId.equals(CommonConstants.RATIONCARDSERIALNUMBERCHANGE)))) {
                query += ",c.surname,c.first_name,c.relation_name,c.rationcard_slno,"
                        + " c.pensionphase,case when c.pensioncard_no='PIII' then 'PIII' else coalesce (mid_name,'')+space(2) + coalesce (LASTNAME,'') END as disname,"
                        + " case when c.pensioncard_no='PIII' then 'PIII' else coalesce (firstname,'') END as dissurname,"
                        + " case when c.pensioncard_no='PIII' then 'PIII' else coalesce (fname,'') END as disrelationame,c.rationcard_number,coalesce (c.Surname,'')+space(2) + coalesce (c.First_Name,'') as Name";

            } else if (categoryId != null && !categoryId.equals("") && ((categoryId.equals(CommonConstants.VICEVERSA)) || (categoryId.equals(CommonConstants.RATIONCARDADDRESSCHANGE)))) {
                query += ",a.newpensionId";
            }
            else if(categoryId != null && !categoryId.equals("") && categoryId.equals(CommonConstants.ADDRESSCHANGEPHASEIII))
            {
            	query += ",m.mandal_name,v.village_name,h.habitation_name,m1.mandal_name,v1.village_name,h1.habitation_name,m1.mandal_id,v1.village_id,h1.habitation_code,h1.habitation_id,d.house_number,d.pin_code,a.newhouse_no,a.newpin_code"; 
            }
            query += " from IssueTrackerDetails a  join IssueCategories b on(b.category_id=a.category)";

            if (categoryId != null && !categoryId.equals("") && (categoryId.equals(CommonConstants.NAME) || categoryId.equals(CommonConstants.RELATIONNAME) || categoryId.equals(CommonConstants.SURNAME) || categoryId.equals(CommonConstants.RATIONCARDSERIALNUMBERCHANGE))) {
                query += " join tblperson_personal_Details c  with (nolock) on(c.person_code=a.Sadarem_id) "
                        + " left join Disabled_Pension d on (c.pensioncard_no=d.pensionid and a.district_id=d.distcode)";
            }
            if(categoryId != null && !categoryId.equals("") && categoryId.equals(CommonConstants.ADDRESSCHANGEPHASEIII))
            {
            	query +=" join tblperson_personal_Details d  with (nolock) on(d.person_code=a.Sadarem_id) \n"+
                        " join tblmandal_details m  with (nolock) on (m.district_id = d.district_id and m.mandal_id = d.mandal_id) \n"+
		            	" join  tblVillage_Details v with (nolock) on (v.district_id=d.district_id and v.mandal_id = d.mandal_id and v.village_id = d.village_id)\n"+ 
		            	" join tblhabitation_details h  with (nolock) on (h.habitation_code = d.habcode) \n"+
		            	"  join tblmandal_details m1  with (nolock) on (m1.district_id = a.newdistrict_id and m1.mandal_id = a.newmandal_id)\n"+ 
		            	" join  tblVillage_Details v1 with (nolock) on (v1.district_id=a.newdistrict_id and v1.mandal_id = a.newmandal_id and v1.village_id = a.newvillage_id)\n"+ 
		            	" join tblhabitation_details h1  with (nolock) on (h1.habitation_code = a.newhab_id) ";
            	
            	
            }
            if (categoryId != null && !categoryId.equals("") && (categoryId.equals(CommonConstants.RATIONCARDADDRESSCHANGE))) {
                query += " join tblperson_personal_Details c  with (nolock) on(c.rationCard_number=a.rationCard) ";

            }
            query += " where request_id  ='" + requestId + "' and a.category='" + categoryId + "' and a.status='Active'";

            pstmt = con.prepareStatement(query);
            System.out.println("qry"+query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    requestRaisedId = new HashMap();
                    requestRaisedId.put("campId", rs.getString(1));
                    requestRaisedId.put("campName", rs.getString(2));
                    requestRaisedId.put("categoryId", rs.getString(3));
                    requestRaisedId.put("categoryName", rs.getString(4));
                    if (rs.getString(5) != null && !rs.getString(5).equals("")) {
                        requestRaisedId.put("sadaremId", rs.getString(5));
                    } else {
                        requestRaisedId.put("sadaremId", "-");
                    }
                    if (rs.getString(6) != null && !rs.getString(6).equals("")) {
                        requestRaisedId.put("pensionId", rs.getString(6));
                    } else {
                        requestRaisedId.put("pensionId", "-");
                    }
                    if (rs.getString(7) != null && !rs.getString(7).equals("")) {
                        requestRaisedId.put("rationCard", rs.getString(7));
                    } else {
                        requestRaisedId.put("rationCard", "-");
                    }

                    requestRaisedId.put("description", rs.getString(8));
                    requestRaisedId.put("requestId", rs.getString(9));
                    if (rs.getString(10) != null) {
                        requestRaisedId.put("userDocumentPath", "<a href ='issueRaiseApproval.do?mode=getPhotoDetails&requestId=" + rs.getString(9) + "'>View</a>");
                    } else {
                        requestRaisedId.put("userDocumentPath", "-");
                    }
                    if (rs.getString(11) != null) {
                        requestRaisedId.put("username", rs.getString(11));
                    } else {
                        requestRaisedId.put("username", "-");
                    }
                    if (rs.getString(12) != null) {
                        requestRaisedId.put("medicalAddress", rs.getString(12));
                    } else {
                        requestRaisedId.put("medicalAddress", "-");
                    }
                    if (rs.getString(13) != null) {

                        requestRaisedId.put("surName", rs.getString(13));
                    } else {
                        requestRaisedId.put("surName", "-");
                    }
                    if (rs.getString(14) != null) {
                        requestRaisedId.put("name", rs.getString(14));
                    } else {
                        requestRaisedId.put("name", "-");
                    }
                    if (rs.getString(15) != null) {
                        requestRaisedId.put("relationName", rs.getString(15));
                    } else {
                        requestRaisedId.put("relationName", "-");
                    }
                    if (rs.getString(16) != null) {
                        requestRaisedId.put("rationCardserialNo", rs.getString(16));
                    } else {
                        requestRaisedId.put("rationCardserialNo", "-");
                    }
                    if (rs.getString(17) != null) {
                        requestRaisedId.put("newSadadremId", rs.getString(17));
                    } else {
                        requestRaisedId.put("newSadadremId", "-");
                    }
                    if (rs.getString(18) != null) {
                        requestRaisedId.put("comments", rs.getString(18));
                    } else {
                        requestRaisedId.put("comments", "-");
                    }
                    if (rs.getString(19) != null) {
                        requestRaisedId.put("comments", rs.getString(19));
                    } else {
                        requestRaisedId.put("comments", "-");
                    }
                    if (rs.getString(20) != null) {
                        requestRaisedId.put("memberName", rs.getString(20));
                    } else {
                        requestRaisedId.put("memberName", "-");
                    }
                    if (rs.getString(21) != null) {
                        requestRaisedId.put("mobileNo", rs.getString(21));
                    } else {
                        requestRaisedId.put("mobileNo", "-");
                    }
                    if (rs.getString(22) != null) {
                        requestRaisedId.put("districtId", rs.getString(22));
                    } else {
                        requestRaisedId.put("districtId", "-");
                    }
                    if (rs.getString(23) != null) {
                        fdate = new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString(23));
                        date = new SimpleDateFormat("dd/mm/yyyy").format(fdate);
                        requestRaisedId.put("date", date);
                        requestRaisedId.put("dateOfIssue", rs.getString(23));

                    } else {
                        requestRaisedId.put("dateOfIssue", "-");
                    }
                    if (rs.getString(24) != null) {
                        requestRaisedId.put("newDistrict_id", rs.getString(24));
                    } else {
                        requestRaisedId.put("newDistrict_id", "-");
                    }


                    if (categoryId != null && !categoryId.equals("") && (categoryId.equals(CommonConstants.NAME) || categoryId.equals(CommonConstants.RELATIONNAME) || categoryId.equals(CommonConstants.SURNAME) || (categoryId.equals(CommonConstants.RATIONCARDSERIALNUMBERCHANGE)))) {
                        if (rs.getString(25) != null) {
                            requestRaisedId.put("existingSurName", rs.getString(25));
                        } else {
                            requestRaisedId.put("existingSurName", "-");
                        }
                        if (rs.getString(26) != null) {
                            requestRaisedId.put("existingName", rs.getString(26));
                        } else {
                            requestRaisedId.put("existingName", "-");
                        }
                        if (rs.getString(27) != null) {
                            requestRaisedId.put("existingRelationName", rs.getString(27));
                        } else {
                            requestRaisedId.put("existingRelationName", "-");
                        }
                        if (rs.getString(28) != null) {
                            requestRaisedId.put("existingRationCardSerialNo", rs.getString(28));
                        } else {
                            requestRaisedId.put("existingRationCardSerialNo", "-");
                        }
                        if (rs.getString(29) != null) {
                            requestRaisedId.put("pensionPhase", rs.getString(29));
                        } else {
                            requestRaisedId.put("pensionPhase", "-");
                        }
                        if (rs.getString(30) != null) {
                            requestRaisedId.put("disName", rs.getString(30));
                        } else {
                            requestRaisedId.put("disName", "-");
                        }
                        if (rs.getString(31) != null) {
                            requestRaisedId.put("disSurName", rs.getString(31));
                        } else {
                            requestRaisedId.put("disSurName", "-");
                        }
                        if (rs.getString(32) != null) {
                            requestRaisedId.put("disRelationName", rs.getString(32));
                        } else {
                            requestRaisedId.put("disRelationName", "-");
                        }

                        if (rs.getString(33) != null) {
                            requestRaisedId.put("rationCardNoData", rs.getString(33));
                        } else {
                            requestRaisedId.put("rationCardNoData", "-");
                        }
                        if (rs.getString(34) != null) {
                            requestRaisedId.put("nameForRationCardSerialNoCat", rs.getString(34));
                        } else {
                            requestRaisedId.put("nameForRationCardSerialNoCat", "-");
                        }
                    }
                    if (categoryId != null && !categoryId.equals("")
                            && ((categoryId.equals(CommonConstants.VICEVERSA)) || (categoryId.equals(CommonConstants.RATIONCARDADDRESSCHANGE)))) {
                        if (rs.getString(25) != null) {
                            requestRaisedId.put("newpensionId", rs.getString(25));
                        } else {
                            requestRaisedId.put("newpensionId", "-");
                        }
                    }
                     if(categoryId != null && !categoryId.equals("") && categoryId.equals(CommonConstants.ADDRESSCHANGEPHASEIII))
                     {
                    	 if (rs.getString(25) != null) {
                             requestRaisedId.put("mandalname", rs.getString(25));
                         } else {
                             requestRaisedId.put("mandalname", "-");
                         }

                    	 if (rs.getString(26) != null) {
                             requestRaisedId.put("villagename", rs.getString(26));
                         } else {
                             requestRaisedId.put("villagename", "-");
                         }

                    	 if (rs.getString(27) != null) {
                             requestRaisedId.put("habname", rs.getString(27));
                         } else {
                             requestRaisedId.put("habname", "-");
                         }
                    	 if (rs.getString(28) != null) {
                             requestRaisedId.put("newmandalname", rs.getString(28));
                         } else {
                             requestRaisedId.put("newmandalname", "-");
                         }

                    	 if (rs.getString(29) != null) {
                             requestRaisedId.put("newvillagename", rs.getString(29));
                         } else {
                             requestRaisedId.put("newvillagename", "-");
                         }

                    	 if (rs.getString(30) != null) {
                             requestRaisedId.put("newhabname", rs.getString(30));
                         } else {
                             requestRaisedId.put("newhabname", "-");
                         }
                    	 
                    	 if (rs.getString(31) != null) {
                             requestRaisedId.put("mandalid", rs.getString(31));
                         } else {
                             requestRaisedId.put("mandalid", "-");
                         }
                    	 if (rs.getString(32) != null) {
                             requestRaisedId.put("villageid", rs.getString(32));
                         } else {
                             requestRaisedId.put("villageid", "-");
                         }
                    	 if (rs.getString(33) != null) {
                             requestRaisedId.put("habcode", rs.getString(33));
                         } else {
                             requestRaisedId.put("habcode", "-");
                         }
                    	 if (rs.getString(34) != null) {
                             requestRaisedId.put("habid", rs.getString(34));
                         } else {
                             requestRaisedId.put("habid", "-");
                         }
                    	 
                    	 if (rs.getString(35) != null) {
                             requestRaisedId.put("houseno", rs.getString(35));
                         } else {
                             requestRaisedId.put("houseno", "-");
                         }
                    	 if (rs.getString(36) != null) {
                             requestRaisedId.put("pincode", rs.getString(36));
                         } else {
                             requestRaisedId.put("pincode", "-");
                         }
                    	 
                    	 if (rs.getString(37) != null) {
                             requestRaisedId.put("newhouseno", rs.getString(37));
                         } else {
                             requestRaisedId.put("newhouseno", "-");
                         }
                    	 if (rs.getString(38) != null) {
                             requestRaisedId.put("newpincode", rs.getString(38));
                         } else {
                             requestRaisedId.put("newpincode", "-");
                         }
                     }
                    requestList.add(requestRaisedId);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestRaisedDetails", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getRequestRaisedDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestRaisedDetails", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getRequestRaisedDetails");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return requestList;
    }

    public ArrayList getPensionIdDetails(DataSource ds, String loginId, String pensionId, String category) throws SADAREMDBException, SQLException {
        ArrayList pensionDetails = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        try {

            con = DBConnection.getConnection();
            query = "select pensionid,reasonforpersonstatus from Disabled_pension a join login_Details b "
                    + "on (b.district_id =a.distcode) where b.username ='" + loginId + "'  and pensionid = '" + pensionId + "'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    pensionDetails.add(rs.getString(1));
                    pensionDetails.add(rs.getString(2));
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPensionIdDetails", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getPensionIdDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPensionIdDetails", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getPensionIdDetails");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return pensionDetails;

    }

    public ArrayList getMailForEmailIssueRaiseBody(DataSource ds, String loginId) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        String IssuereqId = null;
        String requestQuery = null;
        PreparedStatement pstmt1 = null;
        ResultSet issueRs = null;
        ArrayList issueDetails = new ArrayList();
        int sno = 1;
        Date fdate = null;
        String date = null;

        try {
            con = DBConnection.getConnection();
            query = "select max(request_id) from IssueTrackerDetails";
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    IssuereqId = rs.getString(1);
                }
            }
            requestQuery = "select a.request_id,b.category_name,"
                    + " a.Description,a.created_Date from IssueTrackerDetails a "
                    + " join IssueCategories b  on(a.category =b.category_id)"
                    + " join login_Details e on (e.district_id=a.district_id)"
                    + " where a.request_id ='" + IssuereqId + "'and e.username ='" + loginId + "' and a.status='Active'";
            pstmt1 = con.prepareStatement(requestQuery);
            issueRs = pstmt1.executeQuery();

            if (issueRs != null) {
                while (issueRs.next()) {
                    issueDetails.add(issueRs.getString(1));
                    issueDetails.add(issueRs.getString(2));
                    issueDetails.add(issueRs.getString(3));
                    fdate = new SimpleDateFormat("yyyy-mm-dd").parse(issueRs.getString(4));
                    date = new SimpleDateFormat("dd/mm/yyyy").format(fdate);
                    issueDetails.add(date);

                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMailForEmailIssueRaiseBody", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getMailForEmailIssueRaiseBody");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMailForEmailIssueRaiseBody", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getMailForEmailIssueRaiseBody");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(pstmt1);
            DBConnection.closeResultSet(issueRs);
			DBConnection.closeConnection(con);  
        }

        return issueDetails;

    }

    public ArrayList ToIssueRaiseList(DataSource ds) throws SADAREMDBException, SQLException {

        ArrayList issueRaiseList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            query = "select emailid from IssueTrackerEmailIdsDetails where To_id ='Y' and status='Active' and resolved='Y'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    issueRaiseList.add(rs.getString(1));
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "ToIssueRaiseList", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "ToIssueRaiseList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "ToIssueRaiseList", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "ToIssueRaiseList");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return issueRaiseList;
    }

    public IssueRaiseApprovalDTO getMailForEmailIssueResloveBody(DataSource ds, String requestId, String userName) throws SADAREMDBException, SQLException {

        IssueRaiseApprovalDTO issueRaiseApprovalDTO = new IssueRaiseApprovalDTO();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            query = "select comments,request_id from IssueTrackerDetails  where request_id ='" + requestId + "' and status ='Active'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    issueRaiseApprovalDTO.setResloveComments(rs.getString(1));
                    issueRaiseApprovalDTO.setResolverequestId(rs.getString(2));
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMailForEmailIssueResloveBody", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getMailForEmailIssueResloveBody");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMailForEmailIssueResloveBody", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getMailForEmailIssueResloveBody");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return issueRaiseApprovalDTO;

    }

    public ArrayList ToIssueResloveList(DataSource ds, String requestRaiseId) throws SADAREMDBException, SQLException {

        ArrayList issueResloveList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        String districtId = null;
        String query1 = null;
        PreparedStatement emailPstmt = null;
        ResultSet emailRs = null;
        try {
            con = DBConnection.getConnection();
            query = " select district_id from IssueTrackerDetails "
                    + " where request_id ='" + requestRaiseId + "'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    districtId = rs.getString(1);
                }
            }
            query1 = "select distinct emailid from IssueTrackerEmailIdsDetails  "
                    + " where Raised ='Y' and To_id='Y' and Status='Active' and districtid='" + districtId + "'";
            emailPstmt = con.prepareStatement(query1);
            emailRs = emailPstmt.executeQuery();
            if (emailRs != null) {
                while (emailRs.next()) {
                    issueResloveList.add(emailRs.getString(1));
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "ToIssueResloveList", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "ToIssueResloveList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "ToIssueResloveList", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "ToIssueResloveList");
        }
        finally
        {
            DBConnection.closeResultSet(rs);
       		DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(emailPstmt);
            DBConnection.closeResultSet(emailRs);
   			DBConnection.closeConnection(con); 

        }
        return issueResloveList;
    }

    public ArrayList CCIssueResloveList(DataSource ds, String requestRaiseId) throws SADAREMDBException, SQLException {

        ArrayList CCIssueResloveList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        String districtId = null;
        String query1 = null;
        PreparedStatement emailPstmt = null;
        ResultSet emailRs = null;
        try {
            con = DBConnection.getConnection();
            query = " select district_id from IssueTrackerDetails "
                    + " where request_id ='" + requestRaiseId + "' and status='Active'";
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    districtId = rs.getString(1);
                }
            }

            query1 = "select distinct emailid from IssueTrackerEmailIdsDetails  "
                    + " where Raised ='Y' and CC='Y' and Status='Active' and districtid='" + districtId + "'";

            emailPstmt = con.prepareStatement(query1);
            emailRs = emailPstmt.executeQuery();

            if (emailRs != null) {
                while (emailRs.next()) {

                    CCIssueResloveList.add(emailRs.getString(1));

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "CCIssueResloveList", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "CCIssueResloveList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "CCIssueResloveList", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "CCIssueResloveList");
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);
       		DBConnection.closeStatement(pstmt);

            DBConnection.closeStatement(emailPstmt);
            DBConnection.closeResultSet(emailRs);
   			DBConnection.closeConnection(con); 
        }
        return CCIssueResloveList;
    }

    public ArrayList getTaskTrackerDetails(DataSource ds, String requestId, IssueRaiseApprovalForm issueRaiseApprovalForm, String loginId) throws SADAREMDBException, SQLException {

        ArrayList taskTrackerList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        String districtId = null;
        PreparedStatement taskPstmt = null;
        ResultSet taskRs = null;
        Map requestRaisedId = null;
        StringBuffer taskerQuery = new StringBuffer();
        String status = null;
        String reqDate = null;
        String requestStatus = null;
        try {
            con = DBConnection.getConnection();
            query = "select a.district_id  From login_Details a "
                    + " where a.username ='" + loginId + "'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    districtId = rs.getString(1);
                }
            }
            taskerQuery.append("select a.camp_id,a.camp_name,b.category_id,b.category_name,"
                    + " a.Sadarem_id,a.pension_id,a.rationcard,a.description,a.request_id,"
                    + " a.user_uploaded_document,a.username,a.medicaladdress,a.surname,a.name,"
                    + " a.relationname,a.rationcardserialno,newsadarem_id,Comments,Comments,a.membername,a.mobileno,a.description,c.district_name,a.created_date,requeststatus,a.updated_Date,a.dateOfIssue,"
                    + " a.RelationType,a.sub_disabilty,a.Sub_sub_disabilty,Diagnosis_of_Disability "
                    + " from IssueTrackerDetails a  join IssueCategories b on(b.category_id=a.category)join tblDistrict_Details c "
                    + " on(c.district_id=a.district_id)"
                    + " where  a.status='Active' and a.district_id ='" + districtId + "'");
            if (requestId != null && !requestId.equalsIgnoreCase("") && requestId.length() > 0) {
                taskerQuery.append(" and a.request_id ='" + requestId + "'");
                if (issueRaiseApprovalForm.getStatus() != null && !issueRaiseApprovalForm.getStatus().equalsIgnoreCase("0")) {
                    taskerQuery.append(" and a.RequestStatus ='" + issueRaiseApprovalForm.getStatus() + "'");
                }
            } else {
                if (issueRaiseApprovalForm.getCategoryFormId() != null && !issueRaiseApprovalForm.getCategoryFormId().equals("0")) {
                    taskerQuery.append(" and a.category ='" + issueRaiseApprovalForm.getCategoryFormId() + "'");
                }
                if (issueRaiseApprovalForm.getStatus() != null && !issueRaiseApprovalForm.getStatus().equalsIgnoreCase("0")) {
                    taskerQuery.append(" and a.RequestStatus ='" + issueRaiseApprovalForm.getStatus() + "'");
                }
            }
            pstmt = con.prepareStatement(taskerQuery.toString());
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    requestRaisedId = new HashMap();
                    requestRaisedId.put("campId", rs.getString(1));
                    requestRaisedId.put("campName", rs.getString(2));
                    requestRaisedId.put("categoryId", rs.getString(3));
                    requestRaisedId.put("categoryName", rs.getString(4));
                    if (rs.getString(5) != null && !rs.getString(5).equals("")) {
                        requestRaisedId.put("sadaremId", rs.getString(5));
                    } else {
                        requestRaisedId.put("sadaremId", "-");
                    }
                    if (rs.getString(6) != null && !rs.getString(6).equals("")) {
                        requestRaisedId.put("pensionId", rs.getString(6));
                    } else {
                        requestRaisedId.put("pensionId", "-");
                    }
                    if (rs.getString(7) != null && !rs.getString(7).equals("")) {
                        requestRaisedId.put("rationCard", rs.getString(7));
                    } else {
                        requestRaisedId.put("rationCard", "-");
                    }
                    requestRaisedId.put("description", rs.getString(8));
                    requestRaisedId.put("requestId", rs.getString(9));
                    if (rs.getString(10) != null) {
                        requestRaisedId.put("userDocumentPath", "<a href ='issueRaiseApproval.do?mode=getPhotoDetails&requestId=" + rs.getString(9) + "'>View</a>");
                    } else {
                        requestRaisedId.put("userDocumentPath", "-");
                    }
                    if (rs.getString(11) != null) {
                        requestRaisedId.put("username", rs.getString(11));
                    } else {
                        requestRaisedId.put("username", "-");
                    }
                    if (rs.getString(12) != null) {
                        requestRaisedId.put("medicalAddress", rs.getString(12));
                    } else {
                        requestRaisedId.put("medicalAddress", "-");
                    }
                    if (rs.getString(13) != null) {
                        requestRaisedId.put("surName", rs.getString(13));
                    } else {
                        requestRaisedId.put("surName", "-");
                    }
                    if (rs.getString(14) != null) {
                        requestRaisedId.put("name", rs.getString(14));
                    } else {
                        requestRaisedId.put("name", "-");
                    }
                    if (rs.getString(15) != null) {
                        requestRaisedId.put("relationName", rs.getString(15));
                    } else {
                        requestRaisedId.put("relationName", "-");
                    }
                    if (rs.getString(16) != null) {
                        requestRaisedId.put("rationCardserialNo", rs.getString(16));
                    } else {
                        requestRaisedId.put("rationCardserialNo", "-");
                    }
                    if (rs.getString(17) != null) {
                        requestRaisedId.put("newSadadremId", rs.getString(17));
                    } else {
                        requestRaisedId.put("newSadadremId", "-");
                    }

                    if (rs.getString(18) != null) {
                        requestRaisedId.put("comments", rs.getString(18));
                    } else {
                        requestRaisedId.put("comments", "-");
                    }
                    if (rs.getString(20) != null) {
                        requestRaisedId.put("memberName", rs.getString(20));
                    } else {
                        requestRaisedId.put("memberName", "-");
                    }
                    if (rs.getString(21) != null) {
                        requestRaisedId.put("mobileNo", rs.getString(21));
                    } else {
                        requestRaisedId.put("mobileNo", "-");
                    }
                    if (rs.getString(22) != null) {
                        requestRaisedId.put("districtName", rs.getString(22));
                    } else {
                        requestRaisedId.put("districtName", "-");
                    }
                    if (rs.getString(24) != null) {
                        reqDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(rs.getString(24)));
                        requestRaisedId.put("raisedDate", reqDate);
                    } else {
                        requestRaisedId.put("raisedDate", "-");
                    }
                    if (rs.getString(25) != null) {
                        if (rs.getString(25).equalsIgnoreCase("Hold")) {
                            requestStatus = "InProgress";
                        } else {
                            requestStatus = rs.getString(25);
                        }
                        requestRaisedId.put("requeststatus", rs.getString(25));
                    } else {
                        requestRaisedId.put("requeststatus", "-");
                    }
                    if (rs.getString(25) != null && !rs.getString(25).equalsIgnoreCase("") && rs.getString(25).equalsIgnoreCase("Resolved") && rs.getString(26) != null) {
                        reqDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(rs.getString(26)));
                        requestRaisedId.put("solvedDate", reqDate);
                    } else {
                        requestRaisedId.put("solvedDate", "-");
                    }

                    if (rs.getString(26) != null && !rs.getString(26).equalsIgnoreCase("")) {
                        reqDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(rs.getString(26)));
                        requestRaisedId.put("dateOfIssue", reqDate);
                    } else {
                        requestRaisedId.put("dateOfIssue", "-");
                    }
                    taskTrackerList.add(requestRaisedId);
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTaskTrackerDetails", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getTaskTrackerDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTaskTrackerDetails", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getTaskTrackerDetails");
        } 
        finally 
        {
	        DBConnection.closeResultSet(taskRs);
        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeStatement(taskPstmt);
			DBConnection.closeConnection(con); 
        }


        return taskTrackerList;

    }

    public int updateResolveDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String requestId, String flag, String ip) throws SADAREMDBException, SQLException {
        int resolveDetails = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        String comments = "";
        String status = "";
        String queryForLogin = null;
        try {
            if (flag.equalsIgnoreCase("Approved")) {
                comments = issueRaiseApprovalForm.getRejectComment();
                status = "Resolved";
            } else {
                comments = issueRaiseApprovalForm.getRejectComment();
                if (flag.equalsIgnoreCase("Hold")) {
                    status = "Hold";
                } else {
                    status = "Rejected";
                }
            }
            con = DBConnection.getConnection();

            // UPDATE DEFAULT USERNAME AND PASSWORD IN LOGINDETIALS TABLE IN FORGOT PASSWORD CASE

            if (issueRaiseApprovalForm.getResolvecategoryName() != null && issueRaiseApprovalForm.getResolvecategoryName().length() > 0
                    && issueRaiseApprovalForm.getResolvecategoryName().equalsIgnoreCase("Forgot Password")) {

                queryForLogin = "update login_details set password='Sadarem@123',Encrypted_password='8530d2ba9b8720ee1cb8f0dd2676b5c0',password_flag='N'"
                        + " where username='" + issueRaiseApprovalForm.getResolveUserName() + "'";

                pstmt = con.prepareStatement(queryForLogin);
                pstmt.executeUpdate();
            }

            // END


            query = "update IssueTrackerDetails set requeststatus ='" + status + "',updated_Date=getDate(),Resolved_Hours='0.25',resolved_issues='1',"
                    + " Resolved_SystemIP='" + ip + "' ,"
                    + " Resolved_LoginId ='" + issueRaiseApprovalForm.getResloveLoginId() + "',comments='" + comments + "'";
            query += "    where request_id  ='" + requestId + "'";

            pstmt = con.prepareStatement(query);
            resolveDetails = pstmt.executeUpdate();
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateResolveDetails", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "updateResolveDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateResolveDetails", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "updateResolveDetails");
        } //end of catch block
        finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);

        }
        return resolveDetails;
    }

    /**
     * This method is used to check issue is raised or not that particular Category
     * @param ds
     * @param issueRaiseApprovalForm
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public int checkCategoryId(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        int checkCategoryId = 0;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            if (issueRaiseApprovalForm.getCategoryFormId() != null
                    && !issueRaiseApprovalForm.getCategoryFormId().equals("")
                    && issueRaiseApprovalForm.getCategoryFormId().length() > 0) {

                query = "select count(*) from IssueTrackerDetails"
                        + " where category ='" + issueRaiseApprovalForm.getCategoryFormId() + "' and status ='Active'";

                if (issueRaiseApprovalForm.getCategoryFormId() != null
                        && !issueRaiseApprovalForm.getCategoryFormId().equals("")
                        && issueRaiseApprovalForm.getCategoryFormId().length() > 0
                        && (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.TECHNICALDIFFICULTIES)
                        || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.INVALIDPERSONCODE)
                        || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.FORGOTPASSWORD)
                        || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.ADDRESSCHANGEPHASEIII))) {
                    query += "  and requeststatus not in('Rejected','Resolved')";
                } else {
                    query += "  and requeststatus not in('Rejected')";
                }
                if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RATIONCARDSERIALNUMBERCHANGE)
                        || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RATIONCARDADDRESSCHANGE)) {
                    query += " and  rationCard ='" + issueRaiseApprovalForm.getRationCardNumber() + "'";
                } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RATIONCARDNOTOPEN)) {
                    query += " and  rationCard ='" + issueRaiseApprovalForm.getRationCardNumber() + "' and rationCardserialno='" + issueRaiseApprovalForm.getNewSerialNo() + "'";
                } else if ((issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.BLANKPAGES)
                        && issueRaiseApprovalForm.getRadioPensionId().equals(CommonConstants.TECHNICALDIFFICULTIES))
                        || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.PENSIONIDNOTOPENING)) {
                    query += " and pension_id ='" + issueRaiseApprovalForm.getPensionId() + "'";
                } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.VICEVERSA) && issueRaiseApprovalForm.getVicversaFlag().equals("2")) {
                    query += " and newpensionid ='" + issueRaiseApprovalForm.getNewPensionId() + "'";
                } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.VICEVERSA) && issueRaiseApprovalForm.getVicversaFlag().equals("3")) {
                    query += " and newsadarem_id ='" + issueRaiseApprovalForm.getPopulateviceversaSadaremId() + "'";

                } else {
                    query += " and sadarem_id ='" + issueRaiseApprovalForm.getSadaremId() + "'";
                }
                pstmt = con.prepareStatement(query); 
                //System.out.println("query"+query);
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        checkCategoryId = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkCategoryId", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "checkCategoryId");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkCategoryId", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "checkCategoryId");
        } //end of catch block
        finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);

        }
        return checkCategoryId;

    }

    /**
     * This method is use to check Category of AddressChange(PhaseIII)
     * @param ds
     * @param issueRaiseApprovalForm
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public int checkAddressChangePhaseIII(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        int checkAddressChangePhaseIII = 0;
        Connection con = null;
        String query = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            con = DBConnection.getConnection();
            query = "select count(*) from dbo.tblPerson_Personal_Details  with (nolock) "
                    + " where person_code ='" + issueRaiseApprovalForm.getSadaremId() + "' and pensionphase = 'PhaseIII'"
                    + " and status = 'Active'";

            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    checkAddressChangePhaseIII = rs.getInt(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkAddressChangePhaseIII", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "checkAddressChangePhaseIII");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkAddressChangePhaseIII", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "checkAddressChangePhaseIII");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }

        return checkAddressChangePhaseIII;

    }

    /**
     * This method is uesd for Wheather SADAREM iD is Valid or not.
     * @param ds
     * @param issueRaiseApprovalForm
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public int validSadaremId(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        int validSadaremId = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        String sadaremId = null;
        try {

            con = DBConnection.getConnection();
            if (issueRaiseApprovalForm.getCategoryFormId() != null
                    && issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.VICEVERSA)
                    && issueRaiseApprovalForm.getCategoryFormId().length() > 0
                    && issueRaiseApprovalForm.getVicversaFlag() != null
                    && issueRaiseApprovalForm.getVicversaFlag().equals("3")) {
                sadaremId = issueRaiseApprovalForm.getPopulateviceversaSadaremId();
            } else {
                sadaremId = issueRaiseApprovalForm.getSadaremId();
            }
            query = "select count(*) from dbo.tblPerson_Personal_Details  with (nolock) "
                    + " where person_code ='" + sadaremId + "' and status = 'Active'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    validSadaremId = rs.getInt(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "validSadaremId", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "validSadaremId");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "validSadaremId", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "validSadaremId");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return validSadaremId;

    }

    /**
     * This method is used for Category technical Difficulty wheather SADAREM iD is Valid or not.
     * @param ds
     * @param issueRaiseApprovalForm
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public int checkTechnicalDifficultySADAREMID(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        int checkTechnicalDifficultySADAREMId = 0;
        String query = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            query = "select count(*) from tblperson_personal_Details  with (nolock) "
                    + " where person_code ='" + issueRaiseApprovalForm.getSadaremId() + "' and status ='Active'";

            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    checkTechnicalDifficultySADAREMId = rs.getInt(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkTechnicalDifficultySADAREMID", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "checkTechnicalDifficultySADAREMID");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkcheckTechnicalDifficultySADAREMIDHabCode", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "checkTechnicalDifficultySADAREMID");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return checkTechnicalDifficultySADAREMId;

    }

    /**
     * This method is uesd for to fetch Username Details based upon the districtId
     * @param ds
     * @param districtId
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public ArrayList getUserNameDetails(DataSource ds, String districtId) throws SADAREMDBException, SQLException {

        ArrayList userNameList = new ArrayList();
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map map = null;
        try {
            con = DBConnection.getConnection();
            query = "select username from login_details where  status in ('Active','Inactive') "
                    + " and district_id = '" + districtId + "' and role_id in ('1','2','3','8','9','19','20') ";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("username", rs.getString(1));
                    map.put("username", rs.getString(1));
                    userNameList.add(map);
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getUserNameDetails", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getUserNameDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getUserNameDetails", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getUserNameDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return userNameList;
    }

    /**
     * This method is used for to populate password Details that particular UserName
     * @param ds
     * @param userName
     * @param districtId
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public String getPasswordDetails(DataSource ds, String userName, String districtId) throws SADAREMDBException, SQLException {

        String passwordDetails = null;
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();

            query = "select Password from login_details"
                    + " where username = '" + userName + "' and status in ('Active','Inactive') and district_id = '" + districtId + "' and role_id in ('1','2','3','8','9','19','20')";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    passwordDetails = rs.getString(1);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPasswordDetails", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getPasswordDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPasswordDetails", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getPasswordDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return passwordDetails;
    }

    /**
     * This method is used for that Particular SADAREM ID is uesd to populate  SurName and as well as Name Details
     * @param ds
     * @param issueRaiseApprovalForm
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public ArrayList getSurNameDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        ArrayList surNameDetails = new ArrayList();
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map map = null;
        String sadaremId = null;
        try {
            con = DBConnection.getConnection();

            query = "select surname,first_name from tblperson_personal_details  with (nolock) "
                    + " where  person_code ='" + issueRaiseApprovalForm.getSadaremId() + "' and status ='Active'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    surNameDetails.add(rs.getString(1));
                    surNameDetails.add(rs.getString(2));
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSurNameDetails", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getSurNameDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSurNameDetails", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getSurNameDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return surNameDetails;
    }

    /**
     * This method is used to populate that particular SADAREM ID RelationName Details
     * @param ds
     * @param issueRaiseApprovalForm
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public String getRelationNameDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String relationNameDetails = null;
        try {
            con = DBConnection.getConnection();
            query = "select relation_name from tblperson_personal_details  with (nolock) "
                    + " where  person_code ='" + issueRaiseApprovalForm.getSadaremId() + "' and status ='Active'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    relationNameDetails = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRelationNameDetails", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getRelationNameDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRelationNameDetails", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getRelationNameDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return relationNameDetails;
    }

    /**
     * This method is used to Populate SADAREM ID based upon the RationCard No.
     * @param ds
     * @param issueRaiseApprovalForm
     * @param districtId
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public ArrayList getSADAREMIDDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String districtId) throws SADAREMDBException, SQLException {

        ArrayList sadaremList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        String rationCardNo = null;
        ResultSet rs = null;
        Map map = null;

        try {
            con = DBConnection.getConnection();
            query = "select person_code  from tblperson_personal_details  with (nolock) "
                    + " where  rationCard_number ='" + issueRaiseApprovalForm.getRationCardNumber() + "' and status ='Active' and district_id='" + districtId + "'";

            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("personCode", rs.getString(1));
                    sadaremList.add(map);
                }

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSADAREMIDDetails", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getSADAREMIDDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSADAREMIDDetails", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getSADAREMIDDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return sadaremList;

    }

    /**
     * This method is used for Camp Addres based upon that SADAREM ID.
     * @param ds
     * @param districtId
     * @param hospitalName
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public ArrayList getCampAddress(DataSource ds, String districtId, String hospitalName) throws SADAREMDBException, SQLException {
        ArrayList campAddress = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        Map map = null;
        try {
            con = DBConnection.getConnection();
            query = "select distinct hospital_address from dbo.tblPerson_Disability_Details"
                    + " where hospital_name = '" + hospitalName + "' and substring(person_code,1,2) = '" + districtId + "'"
                    + " and status = 'Active'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("hospitalAddress", rs.getString(1));
                    campAddress.add(map);

                }

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampAddress", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getCampAddress");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampAddress", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getCampAddress");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return campAddress;

    }

    /**
     * This method is used for SADAREM ID wheather its phaseIII valid or not.
     * @param ds
     * @param issueRaiseApprovalForm
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public int phasIIIvalidSADAREMID(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        int phasIIIvalidSADAREMID = 0;
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();

            query = "select count(*) from dbo.tblPerson_Personal_Details with (nolock)  "
                    + " where person_code ='" + issueRaiseApprovalForm.getSadaremId() + "'and  pensionPhase ='PhaseIII' and status = 'Active'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    phasIIIvalidSADAREMID = rs.getInt(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "phasIIIvalidSADAREMID", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "phasIIIvalidSADAREMID");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "phasIIIvalidSADAREMID", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "phasIIIvalidSADAREMID");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return phasIIIvalidSADAREMID;

    }

    /**
     * This method is uesd for wheather ther Category for that particular Id issue is raised or not.
     * @param ds
     * @param issueRaiseApprovalForm
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public ArrayList getValidCategoryMsg(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        ArrayList validCategoryMsg = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        String date = null;
        Date fdate = null;
        try {
            con = DBConnection.getConnection();
            query = " select b.category_name,request_id,created_Date,a.description from IssueTrackerDetails a"
                    + " join issuecategories b on (a.category=b.category_id)"
                    + " where sadarem_id ='" + issueRaiseApprovalForm.getSadaremId() + "' and a.category ='" + issueRaiseApprovalForm.getCategoryFormId() + "' and status ='Active'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    validCategoryMsg.add(rs.getString(1));
                    validCategoryMsg.add(rs.getString(2));
                    fdate = new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString(3));
                    date = new SimpleDateFormat("dd/mm/yyyy").format(fdate);
                    validCategoryMsg.add(date);
                    validCategoryMsg.add(rs.getString(4));

                }

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getValidCategoryMsg", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getValidCategoryMsg");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getValidCategoryMsg", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getValidCategoryMsg");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return validCategoryMsg;

    }

    /**
     * To Check weather category NoData Valid or not.
     * @param ds
     * @param issueRaiseApprovalForm
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public String noDataValidation(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        String noDataValidation = null;
        Connection con = null;
        CallableStatement cstmt = null;
        String query = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call [SP_PARTA_PARTB_TECHINICALDIFF_5.1](?)}");
            cstmt.setString(1, issueRaiseApprovalForm.getSadaremId());
            rs = cstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    noDataValidation = rs.getString(1);
                }
            }

        } catch (SQLException sqlEx) {

            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "noDataValidation", "IssueRaiseApprovalDAO", "DataBase");

            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "noDataValidation");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "noDataValidation", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "noDataValidation");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);
            DBConnection.closeResultSet(rs);
        }
        return noDataValidation;

    }

    /**
     * This method is uesd for wheather that pension Id is raised or not.
     * @param ds
     * @param issueRaiseApprovalForm
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public ArrayList getValidMsg(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        ArrayList pensionIdValidMsg = new ArrayList();
        Date fdate = null;
        String date = null;
        String validMsg = null;
        try {

            con = DBConnection.getConnection();
            query = " select b.category_name,request_id,created_Date,a.requeststatus,";
            if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.VICEVERSA) && issueRaiseApprovalForm.getVicversaFlag().equals("1")) {
                query += "sadarem_id,";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.VICEVERSA) && issueRaiseApprovalForm.getRadioPensionId().equals("1")) {
                query += "newsadarem_id,";
            } else {
                query += "sadarem_id,";
            }
            if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.VICEVERSA)) {
                query += "newpensionid,";
            } else {
                query += "pension_id,";
            }
            query += " rationCard from IssueTrackerDetails a"
                    + " join issuecategories b on (a.category=b.category_id)"
                    + " where a.category ='" + issueRaiseApprovalForm.getCategoryFormId() + "' and a.status ='Active'";
            if (issueRaiseApprovalForm.getCategoryFormId() != null
                    && !issueRaiseApprovalForm.getCategoryFormId().equals("")
                    && issueRaiseApprovalForm.getCategoryFormId().length() > 0) {
                if ((issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.BLANKPAGES)
                        && issueRaiseApprovalForm.getRadioPensionId().equals(CommonConstants.TECHNICALDIFFICULTIES))
                        || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.OTHERS)
                        || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.PENSIONIDNOTOPENING)) {
                    query += " and pension_id ='" + issueRaiseApprovalForm.getPensionId() + "' ";
                } else if ((issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEI)
                        || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEII)
                        || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEIV))
                        && issueRaiseApprovalForm.getSadaremId() != null && !issueRaiseApprovalForm.getSadaremId().equals("")) {
                    query += " and sadarem_id ='" + issueRaiseApprovalForm.getSadaremId() + "' ";
                } else if ((issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEI)
                        || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEII)
                        || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEIV))
                        && issueRaiseApprovalForm.getPensionId() != null && !issueRaiseApprovalForm.getPensionId().equals("")) {
                    query += " and pension_id ='" + issueRaiseApprovalForm.getPensionId() + "' ";
                } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RATIONCARDNOTOPEN)) {
                    query += " and rationCard ='" + issueRaiseApprovalForm.getRationCardNumber() + "' and rationCardserialno ='" + issueRaiseApprovalForm.getNewSerialNo() + "'  ";
                } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RATIONCARDADDRESSCHANGE)) {

                    query += " and rationCard ='" + issueRaiseApprovalForm.getRationCardNumber() + "'";
                } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RATIONCARDSERIALNUMBERCHANGE)) {
                    query += " and rationCard ='" + issueRaiseApprovalForm.getRationCardNumber() + "' and sadarem_id ='" + issueRaiseApprovalForm.getSadaremIdRationCardSlno() + "' ";
                } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.VICEVERSA) && (issueRaiseApprovalForm.getVicversaFlag().equals("1"))) {
                    query += " and sadarem_id ='" + issueRaiseApprovalForm.getSadaremId() + "' or newsadarem_id ='" + issueRaiseApprovalForm.getSadaremId() + "'";
                } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.VICEVERSA) && issueRaiseApprovalForm.getRadioPensionId().equals("2")) {
                    query += " and newpensionid ='" + issueRaiseApprovalForm.getNewPensionId() + "' ";
                } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.VICEVERSA) && issueRaiseApprovalForm.getVicversaFlag().equals("3")) {
                    query += " and (newsadarem_id ='" + issueRaiseApprovalForm.getPopulateviceversaSadaremId() + "' or sadarem_id ='" + issueRaiseApprovalForm.getPopulateviceversaSadaremId() + "')";
                } else {
                    query += " and sadarem_id ='" + issueRaiseApprovalForm.getSadaremId() + "' ";

                }
            }
            if (issueRaiseApprovalForm.getCategoryFormId() != null
                    && !issueRaiseApprovalForm.getCategoryFormId().equals("")
                    && issueRaiseApprovalForm.getCategoryFormId().length() > 0
                    && (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.TECHNICALDIFFICULTIES)
                    || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.INVALIDPERSONCODE)
                    || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.FORGOTPASSWORD))) {
                query += "  and requeststatus not in('Rejected','Resolved')";
            } else {
                query += "  and requeststatus not in('Rejected')";
            }
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    pensionIdValidMsg.add(rs.getString(1));
                    pensionIdValidMsg.add(rs.getString(2));
                    fdate = new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString(3));
                    date = new SimpleDateFormat("dd/mm/yyyy").format(fdate);
                    pensionIdValidMsg.add(date);
                    pensionIdValidMsg.add(rs.getString(4));

                    if (issueRaiseApprovalForm.getCategoryFormId() != null
                            && !issueRaiseApprovalForm.getCategoryFormId().equals("")
                            && issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.VICEVERSA)) {
                        if ((issueRaiseApprovalForm.getRadioPensionId() != null
                                && !issueRaiseApprovalForm.getRadioPensionId().equals("")
                                && issueRaiseApprovalForm.getRadioPensionId().equals("1"))
                                || (issueRaiseApprovalForm.getVicversaFlag() != null
                                && !issueRaiseApprovalForm.getVicversaFlag().equals("")
                                && issueRaiseApprovalForm.getVicversaFlag().equals("1"))) {
                            if (rs.getString(5) != null && !rs.getString(5).equalsIgnoreCase("")) {
                                validMsg = "SADAREM ID:" + rs.getString(5);
                                pensionIdValidMsg.add(validMsg);
                            }

                        } else if (issueRaiseApprovalForm.getRadioPensionId().equals("2")) {
                            if (rs.getString(6) != null && !rs.getString(6).equalsIgnoreCase("")) {
                                validMsg = "Pension ID:" + rs.getString(6);
                                pensionIdValidMsg.add(validMsg);
                            }
                        }
                    } else {
                        if (rs.getString(5) != null && !rs.getString(5).equalsIgnoreCase("")) {
                            validMsg = "SADAREM ID:" + rs.getString(5);
                            pensionIdValidMsg.add(validMsg);
                        } else if (rs.getString(6) != null && !rs.getString(6).equalsIgnoreCase("")) {
                            validMsg = "Pension ID:" + rs.getString(6);
                            pensionIdValidMsg.add(validMsg);
                        } else if (rs.getString(7) != null && !rs.getString(7).equalsIgnoreCase("")) {
                            validMsg = "RationCard No:" + rs.getString(7);
                            pensionIdValidMsg.add(validMsg);
                        }
                    }
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getValidMsg", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getValidMsg");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getValidMsg", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getValidMsg");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return pensionIdValidMsg;
    }

    /**
     * This method is populate the Details for that particular SADAREMID
     * @param ds
     * @param issueRaiseApprovalForm
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public ArrayList getbasedUponSADAREMIDDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        ArrayList basedUponSADAREMIDDetails = new ArrayList();
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;



        try {
            con = DBConnection.getConnection();
            query = " select surname+space(2)+first_name as name,relation_name,"
                    + " rationcard_slno from"
                    + " tblperson_personal_details  with (nolock) where person_code ='" + issueRaiseApprovalForm.getSadaremIdRationCardSlno() + "'"
                    + " and status ='Active'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();


            if (rs != null) {
                while (rs.next()) {
                    basedUponSADAREMIDDetails.add(rs.getString(1));
                    basedUponSADAREMIDDetails.add(rs.getString(2));


                    if (rs.getString(3) != null) {
                        basedUponSADAREMIDDetails.add(rs.getString(3));


                    } else {
                        basedUponSADAREMIDDetails.add("-");


                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getbasedUponSADAREMIDDetails", "IssueRaiseApprovalDAO", "DataBase");



            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getbasedUponSADAREMIDDetails");


        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getbasedUponSADAREMIDDetails", "IssueRaiseApprovalDAO", "Code");


            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getbasedUponSADAREMIDDetails");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);

        }
        return basedUponSADAREMIDDetails;


    }

    /**
     * This method is used for is valid for that particluar SADAREMID
     * @param ds
     * @param issueRaiseApprovalForm
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public String validMedicalBoardSADAREMIDDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        String query = null;
        String validMedicalBoardSADAREMIDDetails = null;
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call [SP_PARTA_PARTB_TECHINICALDIFF_5.1](?)}");
            cstmt.setString(1, issueRaiseApprovalForm.getSadaremId());
            rs = cstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    validMedicalBoardSADAREMIDDetails = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "validMedicalBoardSADAREMIDDetails", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "validMedicalBoardSADAREMIDDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "validMedicalBoardSADAREMIDDetails", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "validMedicalBoardSADAREMIDDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);
            DBConnection.closeResultSet(rs);
        }
        return validMedicalBoardSADAREMIDDetails;
    }

    /**
     * This mwethod is uesd for its Valid for Phase Conversion.
     * @param ds
     * @param issueRaiseApprovalForm
     * @param districtId
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public String validPhaseConversion(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String districtId) throws SADAREMDBException, SQLException {
        String validPhaseConversion = null;
        Connection con = null;
        String query = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;


        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call [sp_phaseconversion](?,?,?,?)}");
            cstmt.setString(1, issueRaiseApprovalForm.getSadaremId());
            cstmt.setString(2, issueRaiseApprovalForm.getPensionId());
            cstmt.setString(3, districtId);
            if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEI)) {
                cstmt.setString(4, "PhaseI");
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEII)) {
                cstmt.setString(4, "PhaseII");
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEIV)) {
                cstmt.setString(4, "PhaseIV");
            }
            rs = cstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    validPhaseConversion = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "validPhaseConversion", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "validPhaseConversion");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "validPhaseConversion", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "validPhaseConversion");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);
            DBConnection.closeResultSet(rs);
        }
        return validPhaseConversion;




    }

    /**
     * This method  is used for rationCardNo is valid or not.
     * @param ds
     * @param issueRaiseApprovalForm
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public int rationCardNoValid(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        int rationCardNoValid = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        String rationCard = null;
        ResultSet rs = null;



        try {
            con = DBConnection.getConnection();
            query = "select count(*) from dbo.tblPerson_Personal_Details  with (nolock)  where "
                    + " rationcard_number ='" + issueRaiseApprovalForm.getRationCardNumber() + "' and status = 'Active'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();


            if (rs != null) {
                while (rs.next()) {
                    rationCardNoValid = rs.getInt(1);


                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "rationCardNoValid", "IssueRaiseApprovalDAO", "DataBase");



            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "rationCardNoValid");


        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "rationCardNoValid", "IssueRaiseApprovalDAO", "Code");


            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "rationCardNoValid");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return rationCardNoValid;



    }

    /**
     * This method is uesd for its Apporoval for AddressChangePhaseIII.
     * @param ds
     * @param issueRaiseApprovalForm
     * @param districtId
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public int issueApprovalAddressPhaseIIIDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String districtId) throws SADAREMDBException, SQLException {
        int addressChasePhaseIII = 0;
        Connection con = null;
        CallableStatement cstmt = null;



        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call [SP_DeleteDatainAllTables_for_PHASEIII](?)}");
            cstmt.setString(1, issueRaiseApprovalForm.getResolvesadaremId());
            addressChasePhaseIII = cstmt.executeUpdate();


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "issueApprovalAddressPhaseIIIDetails", "IssueRaiseApprovalDAO", "DataBase");



            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "issueApprovalAddressPhaseIIIDetails");


        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "issueApprovalAddressPhaseIIIDetails", "IssueRaiseApprovalDAO", "Code");


            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "issueApprovalAddressPhaseIIIDetails");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);

        }
        return addressChasePhaseIII;



    }

    /**
     * This method is used for Approval MedicalBoard Details.
     * @param ds
     * @param issueRaiseApprovalForm
     * @param campIdAddress
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public int issueApprovalMedicalBoardDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String venueName) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cstmt = null;
        String query = null;
        int medicalBoardApprovalDetails = 0;
        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call [dbo].[SP_hospital_board_change_v2](?,?,?,?,?)}");
            cstmt.setString(1, issueRaiseApprovalForm.getResolvesadaremId());
            cstmt.setString(2, issueRaiseApprovalForm.getResolveCampName());
            cstmt.setString(3, issueRaiseApprovalForm.getResolveMedicalAddress());
            cstmt.setString(4, venueName);
            cstmt.setString(5, issueRaiseApprovalForm.getResolveUserName());
            medicalBoardApprovalDetails = cstmt.executeUpdate();
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "issueApprovalMedicalBoardDetails", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "issueApprovalMedicalBoardDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "issueApprovalMedicalBoardDetails", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "issueApprovalMedicalBoardDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);
        }
        return medicalBoardApprovalDetails;
    }

    /**
     * This method is uesd for Approval for name Change Category.
     * @param ds
     * @param issueRaiseApprovalForm
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public int issueApprovalNameChangeDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cstmt = null;


        int approvalNameChange = 0;



        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call [sp_name_relationship_surname_change](?,?,?,?)}");

            cstmt.setString(1, issueRaiseApprovalForm.getResolvesadaremId());
            cstmt.setString(3, issueRaiseApprovalForm.getResloveLoginId());
            if (issueRaiseApprovalForm.getStatusCategoryId().equals(CommonConstants.NAME)) {
                cstmt.setString(2, issueRaiseApprovalForm.getResolveName());
                cstmt.setString(4, CommonConstants.NAMECHANGE);
            } else if (issueRaiseApprovalForm.getStatusCategoryId().equals(CommonConstants.RELATIONNAME)) {
                cstmt.setString(2, issueRaiseApprovalForm.getResolverelationName());
                cstmt.setString(4, CommonConstants.RELATIONNAMECHANGE);
            } else if (issueRaiseApprovalForm.getStatusCategoryId().equals(CommonConstants.SURNAME)) {
                cstmt.setString(2, issueRaiseApprovalForm.getResolvesurName());
                cstmt.setString(4, CommonConstants.SURNAMECHANGE);
            }
            approvalNameChange = cstmt.executeUpdate();
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "issueApprovalNameChangeDetails", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "issueApprovalNameChangeDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "issueApprovalNameChangeDetails", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "issueApprovalNameChangeDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);
        }
        return approvalNameChange;
    }

    /**
     * This method is used for
     * @param ds
     * @param issueRaiseApprovalForm
     * @param districtId
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
//    public ArrayList medicalBoardUserNameDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String districtId) throws SADAREMDBException, SQLException {
//        ArrayList medicalBoardUserNameList = new ArrayList();
//        Connection con = null;
//        String query = null;
//        ResultSet rs = null;
//        PreparedStatement pstmt = null;
//        try {
//            con = DBConnection.getConnection();
//            query = "";
//            pstmt = con.prepareStatement(query);
//            rs = pstmt.executeQuery();
//            if (rs != null) {
//                while (rs.next()) {
//                }
//
//            }
//
//        } catch (SQLException sqlEx) {
//            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "medicalBoardUserNameDetails", "IssueRaiseApprovalDAO", "DataBase");
//
//            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "medicalBoardUserNameDetails");
//        } catch (Exception sqlEx) {
//            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "medicalBoardUserNameDetails", "IssueRaiseApprovalDAO", "Code");
//            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "medicalBoardUserNameDetails");
//        } finally {
//            DBConnection.closeConnection(con);
//        }
//        return medicalBoardUserNameList;
//
//    }
    /**
     * This method is uesd for approval for PhaseConversion
     * @param ds
     * @param issueRaiseApprovalForm
     * @param districtId
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public int approvalPhaseConversion(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String districtId) throws SADAREMDBException, SQLException {
        int approvalPhaseConversion = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        String phase = null;
        try {
            con = DBConnection.getConnection();
            if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEI)) {
                phase = "PhaseI";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEII)) {
                phase = "PhaseII";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEIV)) {
                phase = "PhaseIV";
            }
            query = "update dbo.tblPerson_Personal_Details "
                    + " set pensioncard_no = '" + issueRaiseApprovalForm.getResolvepensionId() + "' ,pensionphase = '" + phase + "'"
                    + " from dbo.tblPerson_Personal_Details d "
                    + " where d.person_code = '" + issueRaiseApprovalForm.getResolvesadaremId()+ "' and status = 'Active'";
            pstmt = con.prepareStatement(query);
            approvalPhaseConversion = pstmt.executeUpdate();
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "approvalPhaseConversion", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "approvalPhaseConversion");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "approvalPhaseConversion", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "approvalPhaseConversion");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
        }
        return approvalPhaseConversion;
    }

    /**
     * This method is used for Category ForgetPassword for mail Body.
     * @param ds
     * @param issueRaiseApprovalForm
     * @param distirctId
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public ArrayList getForgetPasswordDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String distirctId) throws SADAREMDBException, SQLException {
        Connection con = null;
        String query = null;
        ArrayList forgetPasswordDetails = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Date fdate = null;
        String date = null;


        try {
            con = DBConnection.getConnection();
            query = "select b.category_name,request_id,a.created_Date,a.description,c.password "
                    + " from IssueTrackerDetails a join issuecategories b on (a.category=b.category_id)"
                    + " join login_Details c on(a.district_id=c.district_id) where  a.category ='" + issueRaiseApprovalForm.getCategoryFormId() + "' and a.username ='" + issueRaiseApprovalForm.getForgetUserName() + "'and a.district_id ='" + distirctId + "'";

            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();


            if (rs != null) {
                while (rs.next()) {
                    forgetPasswordDetails.add(rs.getString(1));
                    forgetPasswordDetails.add(rs.getString(2));
                    fdate = new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString(3));
                    date = new SimpleDateFormat("dd/mm/yyyy").format(fdate);
                    forgetPasswordDetails.add(date);
                    forgetPasswordDetails.add(rs.getString(4));
                    forgetPasswordDetails.add(rs.getString(5));


                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getForgetPasswordDetails", "IssueRaiseApprovalDAO", "DataBase");



            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getForgetPasswordDetails");


        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getForgetPasswordDetails", "IssueRaiseApprovalDAO", "Code");


            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getForgetPasswordDetails");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);

        }
        return forgetPasswordDetails;


    }

    /**
     * This method is used for Category RationCardAddress Change for mail Body.
     * @param ds
     * @param issueRaiseApprovalForm
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public ArrayList addressChangeRationCardNumber(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        String rationCardNo = null;
        ArrayList addressChangeList = new ArrayList();
        Date fdate = null;
        String date = null;


        try {
            con = DBConnection.getConnection();
            rationCardNo = issueRaiseApprovalForm.getRationCardNumber();
            query = "select b.category_name,request_id,created_Date,a.description "
                    + " from IssueTrackerDetails a join issuecategories b on (a.category=b.category_id)"
                    + " where rationCard ='" + rationCardNo + "' and a.category ='" + issueRaiseApprovalForm.getCategoryFormId() + "' and a.status ='Active'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();


            if (rs != null) {
                while (rs.next()) {
                    addressChangeList.add(rs.getString(1));
                    addressChangeList.add(rs.getString(2));
                    fdate = new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString(3));
                    date = new SimpleDateFormat("dd/mm/yyyy").format(fdate);
                    addressChangeList.add(date);
                    addressChangeList.add(rs.getString(4));


                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "addressChangeRationCardNumber", "IssueRaiseApprovalDAO", "DataBase");


            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "addressChangeRationCardNumber");


        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "addressChangeRationCardNumber", "IssueRaiseApprovalDAO", "Code");


            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "addressChangeRationCardNumber");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return addressChangeList;


    }

    /**
     * This method is used for PensionId to mailbody.
     * @param ds
     * @param issueRaiseApprovalForm
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public ArrayList pensionIDForMailDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        ArrayList pensionIDForMailDetails = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;


        try {
            con = DBConnection.getConnection();
            query = "select pension_id,b.category_name,request_id,created_Date "
                    + " from IssueTrackerDetails a join issuecategories b on (a.category=b.category_id)"
                    + " where pension_id ='" + issueRaiseApprovalForm.getPensionId() + "' and a.category ='" + issueRaiseApprovalForm.getCategoryFormId() + "' and status='Active'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();


            if (rs != null) {
                while (rs.next()) {
                    pensionIDForMailDetails.add(rs.getString(1));
                    pensionIDForMailDetails.add(rs.getString(2));
                    pensionIDForMailDetails.add(rs.getString(3));
                    pensionIDForMailDetails.add(rs.getString(4));


                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "pensionIDForMailDetails", "IssueRaiseApprovalDAO", "DataBase");



            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "pensionIDForMailDetails");


        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "pensionIDForMailDetails", "IssueRaiseApprovalDAO", "Code");


            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "pensionIDForMailDetails");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return pensionIDForMailDetails;


    }

    public ArrayList toMailBody(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        ArrayList toMailBody = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;


        try {
            con = DBConnection.getConnection();
            query = "select b.category_name,request_id,created_Date ,a.description"
                    + " from IssueTrackerDetails a join issuecategories b on (a.category=b.category_id)"
                    + " where a.category ='" + issueRaiseApprovalForm.getCategoryFormId() + "'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();


            if (rs != null) {
                while (rs.next()) {
                    toMailBody.add(rs.getString(1));
                    toMailBody.add(rs.getString(2));
                    toMailBody.add(rs.getString(3));
                    toMailBody.add(rs.getString(4));


                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "toMailBody", "IssueRaiseApprovalDAO", "DataBase");



            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "toMailBody");


        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "toMailBody", "IssueRaiseApprovalDAO", "Code");


            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "toMailBody");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return toMailBody;



    }

    public ArrayList categoryApprovalList(DataSource ds) throws SADAREMDBException, SQLException {
        Connection con = null;
        ArrayList categoryApprovalList = new ArrayList();
        IssueRaiseApprovalDTO issueRaiseApprovalDTO = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        try {
            con = DBConnection.getConnection();
            query = "select category_id,category_name  from IssueCategories"
                    + " where status ='Active' and category_id not in('23') order by category_name";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();


            if (rs != null) {
                while (rs.next()) {
                    issueRaiseApprovalDTO = new IssueRaiseApprovalDTO();
                    issueRaiseApprovalDTO.setCategoryId(rs.getString(1));
                    issueRaiseApprovalDTO.setCategoryname(rs.getString(2));
                    categoryApprovalList.add(issueRaiseApprovalDTO);


                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "categoryApprovalList", "IssueRaiseApprovalDAO", "DataBase");


            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "categoryApprovalList");


        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "categoryApprovalList", "IssueRaiseApprovalDAO", "Code");


            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "categoryApprovalList");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);

        }
        return categoryApprovalList;


    }

    public IssueRaiseApprovalDTO pensionPhaseValidation(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String districtId) throws SADAREMDBException, SQLException {
        IssueRaiseApprovalDTO issueRaiseApprovalDTO = new IssueRaiseApprovalDTO();
        Connection con = null;
        String query = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        PreparedStatement pstmt = null;
        String pensionPhaseValidation = "";

        String qry = null;
        int flag = 0;
        String sql = null;
        int count = 0;
        String sadaremid = null;


        try {
            con = DBConnection.getConnection();
            qry = "select person_code from tblperson_personal_details with (nolock)  where pensioncard_no='" + issueRaiseApprovalForm.getPensionId() + "' and district_id='" + districtId + "'";
            pstmt = con.prepareStatement(qry);
            rs = pstmt.executeQuery();
            if (rs != null && rs.next()) {
                sadaremid = rs.getString(1);
            }
            if (sadaremid != null && !sadaremid.equalsIgnoreCase("")) {
                issueRaiseApprovalDTO.setPensionPhaseErrorMsg("Pension Id " + issueRaiseApprovalForm.getPensionId() + " has already been assessed for SADAREM ID " + sadaremid);
                issueRaiseApprovalDTO.setFlag(0);
            } else {
                sql = "select count(*) from Disabled_Pension where pensionid='" + issueRaiseApprovalForm.getPensionId() + "' and distcode='" + districtId + "'";
                pstmt = con.prepareStatement(sql);
                rs1 = pstmt.executeQuery();
                if (rs1 != null && rs1.next()) {
                    count = rs1.getInt(1);
                }

                if (count > 0) {
                    query = "select pensionphase from Disabled_Pension where pensionid ='" + issueRaiseApprovalForm.getPensionId() + "'"
                            + "  and reasonforpersonstatus not in ('Dead') and distcode = '" + districtId + "'";
                    pstmt = con.prepareStatement(query);
                    rs2 = pstmt.executeQuery();
                    if (rs2 != null) {
                        while (rs2.next()) {
                            pensionPhaseValidation = rs2.getString(1);
                        }
                    }
                    if (!pensionPhaseValidation.equalsIgnoreCase("")) {
                        if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEI)) {
                            if (pensionPhaseValidation.equalsIgnoreCase("PhaseI") || pensionPhaseValidation.equalsIgnoreCase("PhaseIII")) {
                                issueRaiseApprovalDTO.setPensionPhase(1);
                            }
                            issueRaiseApprovalDTO.setPensionPhaseErrorMsg("You Selected Request for Change Phase III to Phase I. But that Pension ID " + issueRaiseApprovalForm.getPensionId() + " doesnot not belongs to PhaseI ");
                            issueRaiseApprovalDTO.setFlag(0);
                        } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEII)) {
                            if (pensionPhaseValidation.equalsIgnoreCase("PhaseII") || pensionPhaseValidation.equalsIgnoreCase("PhaseIII")) {
                                issueRaiseApprovalDTO.setPensionPhase(1);
                            }
                            issueRaiseApprovalDTO.setPensionPhaseErrorMsg("You Selected Request for Change Phase III to Phase II. But that Pension ID " + issueRaiseApprovalForm.getPensionId() + " doesnot not belongs to PhaseII");
                            issueRaiseApprovalDTO.setFlag(0);
                        } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEIV)) {
                            if (pensionPhaseValidation.equalsIgnoreCase("PhaseIV") || pensionPhaseValidation.equalsIgnoreCase("PhaseIII")) {
                                issueRaiseApprovalDTO.setPensionPhase(1);
                            }
                            issueRaiseApprovalDTO.setPensionPhaseErrorMsg("You Selected Request for Change Phase III to Phase IV. But that Pension ID " + issueRaiseApprovalForm.getPensionId() + " doesnot not belongs to PhaseIV");
                            issueRaiseApprovalDTO.setFlag(0);
                        }
                    } else {
                        issueRaiseApprovalDTO.setPensionPhaseErrorMsg("Provided Pension ID " + issueRaiseApprovalForm.getPensionId() + " is Dead");
                        issueRaiseApprovalDTO.setFlag(0);
                    }
                } else {
                    issueRaiseApprovalDTO.setPensionPhaseErrorMsg("Pension Id " + issueRaiseApprovalForm.getPensionId() + " not avilable in Pension Data");
                    issueRaiseApprovalDTO.setFlag(0);
                }
            }



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "pensionPhaseValidation", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "pensionPhaseValidation");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "pensionPhaseValidation", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "pensionPhaseValidation");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs1);
            DBConnection.closeResultSet(rs2);
            DBConnection.closeResultSet(rs);
        }
        return issueRaiseApprovalDTO;
    }

    public int vicversaNewSADAREMID(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        int vicversaNewSADAREMID = 0;
        String query = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            if (issueRaiseApprovalForm.getVicversaFlag().equals("1")) {
                query = "select count(*) from issuetrackerDetails "
                        + " where (sadarem_id ='" + issueRaiseApprovalForm.getSadaremId() + "' "
                        + " or newsadarem_id ='" + issueRaiseApprovalForm.getSadaremId() + "') "
                        + " and category ='" + issueRaiseApprovalForm.getCategoryFormId() + "'";

                pstmt = con.prepareStatement(query);
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        vicversaNewSADAREMID = rs.getInt(1);
                    }
                }

            } else {
                query = "select count(*) from issuetrackerDetails "
                        + " where (sadarem_id ='" + issueRaiseApprovalForm.getPopulateviceversaSadaremId() + "' "
                        + " or newsadarem_id ='" + issueRaiseApprovalForm.getPopulateviceversaSadaremId() + "') "
                        + " and category ='" + issueRaiseApprovalForm.getCategoryFormId() + "'";
                pstmt = con.prepareStatement(query);
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        vicversaNewSADAREMID = rs.getInt(1);
                    }
                }


            }



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "vicversaNewSADAREMID", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "vicversaNewSADAREMID");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "vicversaNewSADAREMID", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "vicversaNewSADAREMID");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return vicversaNewSADAREMID;
    }

    public ArrayList validRationCardNotOpen(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String districtId) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList validRationCardNotOpen = new ArrayList();
        String query = null;
        ResultSet rs = null;
        Map map = null;
        try {
            con = DBConnection.getConnection();
            query = "select slno from civilsupply_database.dbo.tblrationcard_details_" + districtId + " "
                    + " where householdcardno='" + issueRaiseApprovalForm.getRationCardNumber() + "'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    validRationCardNotOpen.add(rs.getString(1));
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "validRationCardNotOpen", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "validRationCardNotOpen");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "validRationCardNotOpen", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "validRationCardNotOpen");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return validRationCardNotOpen;
    }

    public ArrayList getRationCardDetailsForNotOpen(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String rationCardNo) throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        ArrayList rationCardDetailsForNotOpen = new ArrayList();
        Map map = null;
        try {
            con = DBConnection.getConnection();
            if (issueRaiseApprovalForm.getVicversaFlag().trim().equals("1")) {
                query = "select householdcardno,slno,membername from civilsupply_database.dbo.tblrationcard_details_" + rationCardNo.substring(3, 5) + ""
                        + " where  householdcardno='" + rationCardNo + "'";

            } else if (issueRaiseApprovalForm.getVicversaFlag().trim().equals("2")) {
                query = "select person_code,rationCard_slno,reasonforpersonStatus from tblperson_personal_Details with (nolock) "
                        + " where status ='Active'  and rationCard_number='" + rationCardNo + "'";
            }
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    if (rs.getString(1) != null && !rs.getString(1).equals("")) {
                        map.put("householdcardno", rs.getString(1));
                    } else {
                        map.put("householdcardno", "-");
                    }
                    if (rs.getString(2) != null && !rs.getString(2).equals("")) {
                        map.put("slno", rs.getString(2));
                    } else {
                        map.put("slno", "-");
                    }
                    if (rs.getString(3) != null && !rs.getString(3).equals("")) {
                        map.put("membername", rs.getString(3));
                    } else {
                        map.put("membername", "-");
                    }
                    rationCardDetailsForNotOpen.add(map);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRationCardDetailsForNotOpen", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getRationCardDetailsForNotOpen");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRationCardDetailsForNotOpen", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getRationCardDetailsForNotOpen");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return rationCardDetailsForNotOpen;


    }

    public int approvalRationCardNotOpen(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String rationCardNo) throws SADAREMDBException, SQLException {

        int approvalRationCardNo = 0;
        Connection con = null;
        String query = null;
        CallableStatement cstmt = null;
        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call [dbo].[Sp_rationcard_insert](?,?,?)}");
            cstmt.setString(1, issueRaiseApprovalForm.getResolverationCard());
            cstmt.setString(2, issueRaiseApprovalForm.getResolveRationCardserialNo());
            cstmt.setString(3, issueRaiseApprovalForm.getResolveMemberName());
            approvalRationCardNo = cstmt.executeUpdate();
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "approvalRationCardNotOpen", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "approvalRationCardNotOpen");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "approvalRationCardNotOpen", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "approvalRationCardNotOpen");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);
        }
        return approvalRationCardNo;
    }

    public ArrayList getVicerversaPoulateDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String flag) throws SADAREMDBException, SQLException {
        ArrayList vicerversaPoulateDetails = new ArrayList();
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map map = null;
        try {
            con = DBConnection.getConnection();

            if (flag.equalsIgnoreCase("1")) {
                query = "select pensioncard_no,surname+space(2)+first_name as Name,relation_name from"
                        + " tblperson_personal_Details  with (nolock)  where person_code='" + issueRaiseApprovalForm.getSadaremId() + "' and status ='Active'";
            } else if (flag.equalsIgnoreCase("2")) {
                query = "select person_code  from tblperson_personal_Details  with (nolock)  where pensioncard_no='" + issueRaiseApprovalForm.getNewPensionId() + "'  and status ='Active'";
            } else if (flag.equalsIgnoreCase("3")) {
                query = "select pensioncard_no  from tblperson_personal_Details  with (nolock)  where person_code='" + issueRaiseApprovalForm.getPopulateviceversaSadaremId() + "'  and status ='Active'";
            }
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    if (flag.equals("1")) {
                        vicerversaPoulateDetails.add(rs.getString(1));
                        vicerversaPoulateDetails.add(rs.getString(2));
                        vicerversaPoulateDetails.add(rs.getString(3));
                    } else if (flag.equalsIgnoreCase("2")) {
                        vicerversaPoulateDetails.add(rs.getString(1));
                    } else if (flag.equalsIgnoreCase("3")) {
                        vicerversaPoulateDetails.add(rs.getString(1));
                    }
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVicerversaPoulateDetails", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getVicerversaPoulateDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVicerversaPoulateDetails", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getVicerversaPoulateDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return vicerversaPoulateDetails;
    }

    public int updateUserRaisedDocumentPath(DataSource ds, String requestId, String filePath) throws SADAREMDBException, SQLException {

        int raisedDocumentPath = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        try {
            con = DBConnection.getConnection();
            query = "update IssueTrackerDetails set user_uploaded_document='" + filePath + "' "
                    + " where request_id ='" + requestId + "'";

            pstmt = con.prepareStatement(query);

            raisedDocumentPath = pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateUserRaisedDocumentPath", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "updateUserRaisedDocumentPath");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateUserRaisedDocumentPath", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "updateUserRaisedDocumentPath");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
        }

        return raisedDocumentPath;

    }

    public ArrayList getPensionCardNotOpeningList(DataSource ds, String penisonId, String districtId) throws SADAREMDBException, SQLException {
        ArrayList pensionCardNotOpeningList = new ArrayList();
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map map = null;
        try {
            con = DBConnection.getConnection();
            query = "select rationCard_number,pensionCard_no,"
                    + " surname+space(2)+first_name as name,relation_name,"
                    + " case when view_edit_mode='Edit' then 'Part A Entered'"
                    + " when view_edit_mode='View' then 'Assessment Done' end Status"
                    + " from tblperson_personal_Details with (nolock)  where rationCard_number in"
                    + " (select rationCard_number from tblperson_personal_Details with (nolock) "
                    + " where pensionCard_no='" + penisonId + "' and district_id ='" + districtId + "')";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("rationCardNo", rs.getString(1));
                    map.put("pensionCardNo", rs.getString(2));
                    map.put("name", rs.getString(3));
                    map.put("relationName", rs.getString(4));
                    map.put("status", rs.getString(5));
                    pensionCardNotOpeningList.add(map);

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPensionCardNotOpeningList", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getPensionCardNotOpeningList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPensionCardNotOpeningList", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getPensionCardNotOpeningList");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return pensionCardNotOpeningList;

    }

    public ArrayList getMedicalBoardUserList(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        ArrayList medicalBoardUserList = new ArrayList();
        String query = null;
        PreparedStatement pstmt = null;
        Connection con = null;
        ResultSet rs = null;
        Map map = null;
        String medicalBoardId[] = null;
        String hospitalName = null;
        String venueName = null;
        try {
            con = DBConnection.getConnection();
            medicalBoardId = issueRaiseApprovalForm.getMedicalBoardId().split(",");
            hospitalName = medicalBoardId[0];
            venueName = medicalBoardId[1];

            query = "select distinct login_id from tblPerson_Disability_Details "
                    + " where hospital_name='" + hospitalName + "' and hospital_Address ='" + issueRaiseApprovalForm.getCampAddress() + "'"
                    + " and status ='Active'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("loginId", rs.getString(1));
                    medicalBoardUserList.add(map);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMedicalBoardUserList", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getMedicalBoardUserList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMedicalBoardUserList", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getMedicalBoardUserList");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return medicalBoardUserList;
    }

    public String getDistrictName(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        String districtName = null;

        try {
            con = DBConnection.getConnection();
            query = "select district_name from  tbldistrict_Details where district_id ='" + issueRaiseApprovalForm.getDistrict_id() + "'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    districtName = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistrictName", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getDistrictName");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistrictName", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getDistrictName");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return districtName;


    }

    public IssueRaiseApprovalDTO validblankPagesIds(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        IssueRaiseApprovalDTO issueRaiseApprovalDTO = null;
        String id = null;
        ResultSet rs = null;
        String validationIds = null;
        String issuetrackerQuery = null;
        try {
            con = DBConnection.getConnection();
            if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.BLANKPAGES)
                    && issueRaiseApprovalForm.getRadioPensionId().equals("1")) {
                query = "select pensionCard_no,person_code from tblperson_personal_Details  with (nolock) "
                        + " where person_code='" + issueRaiseApprovalForm.getSadaremId() + "'";

                pstmt = con.prepareStatement(query);
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        issueRaiseApprovalDTO = new IssueRaiseApprovalDTO();
                        issueRaiseApprovalDTO.setBlankPagesPensionId(rs.getString(1));
                        issueRaiseApprovalDTO.setBlankPagesSadaremId(rs.getString(2));
                        //validationIds = rs.getString(1);
                    }
                }
                issuetrackerQuery = "select count(*) from issuetrackerdetails"
                        + " where  pension_id ='" + issueRaiseApprovalDTO.getBlankPagesPensionId() + "' and  category='25' ";
                pstmt = con.prepareStatement(issuetrackerQuery);
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        issueRaiseApprovalDTO.setBlankCount(rs.getInt(1));
                    }
                }

            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.BLANKPAGES)
                    && issueRaiseApprovalForm.getRadioPensionId().equals("2")) {
                query = "select person_code,pensionCard_no from tblperson_personal_Details  with (nolock) "
                        + " where pensionCard_no ='" + issueRaiseApprovalForm.getPensionId() + "'";

                pstmt = con.prepareStatement(query);
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        issueRaiseApprovalDTO = new IssueRaiseApprovalDTO();
                        issueRaiseApprovalDTO.setBlankPagesSadaremId(rs.getString(1));
                        issueRaiseApprovalDTO.setBlankPagesPensionId(rs.getString(2));
                    }
                }
                issuetrackerQuery = "select count(*) from issuetrackerdetails"
                        + " where  sadarem_id ='" + issueRaiseApprovalDTO.getBlankPagesSadaremId() + "'and  category='25' ";
                pstmt = con.prepareStatement(issuetrackerQuery);
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        issueRaiseApprovalDTO.setBlankCount(rs.getInt(1));
                    }

                }

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "validblankPagesIds", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "validblankPagesIds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "validblankPagesIds", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "validblankPagesIds");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return issueRaiseApprovalDTO;

    }

    public int approvalRationCardSerialNo(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        int approvalRationCardSerialNo = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        try {
            con = DBConnection.getConnection();
            query = "update tblperson_personal_Details set rationCard_slno='" + issueRaiseApprovalForm.getResolveRationCardserialNo() + "'"
                    + " where person_code ='" + issueRaiseApprovalForm.getResolvesadaremId() + "'";
            pstmt = con.prepareStatement(query);
            approvalRationCardSerialNo = pstmt.executeUpdate();


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "approvalRationCardSerialNo", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "approvalRationCardSerialNo");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "approvalRationCardSerialNo", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "approvalRationCardSerialNo");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
        }
        return approvalRationCardSerialNo;

    }

    public IssueRaiseApprovalDTO getDateOfIssue(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        int dateOfIssue = 0;
        String query = null;
        ResultSet rs = null;
        IssueRaiseApprovalDTO issueRaiseApprovalDTO = null;

        try {
            con = DBConnection.getConnection();
            query = "select count(*) from "
                    + " tblPerson_Disability_Details where person_code ='" + issueRaiseApprovalForm.getSadaremId() + "'and status='Active'";

            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs != null) {
                if (rs.next()) {
                    issueRaiseApprovalDTO = new IssueRaiseApprovalDTO();
                    dateOfIssue = rs.getInt(1);
                    if (dateOfIssue == 0) {
                        query = "select reasonforpersonstatus from tblPerson_personal_Details with (nolock) "
                                + " where person_code ='" + issueRaiseApprovalForm.getSadaremId() + "' and status='Inactive'";

                        pstmt = con.prepareStatement(query);
                        rs = pstmt.executeQuery();
                        if (rs != null && rs.next()) {
                            issueRaiseApprovalDTO = new IssueRaiseApprovalDTO();
                            issueRaiseApprovalDTO.setPersonStatus(rs.getString(1));

                        } else {
                            issueRaiseApprovalDTO.setPersonStatus("1");
                        }
                    } else {
                        issueRaiseApprovalDTO.setPersonStatus("1");
                    }
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDateOfIssue", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getDateOfIssue");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDateOfIssue", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getDateOfIssue");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return issueRaiseApprovalDTO;


    }

    public int getAppleateCountDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        int appleateCountDetails = 0;
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();

            query = "select count(*) from dbo.tblPerson_Personal_Details with (nolock)  where person_code ='" + issueRaiseApprovalForm.getSadaremId() + "' "
                    + " and  pensionphase = 'PhaseIII' and categoryid  not in('2') and status ='Active' and pensioncard_no in ('PIII','RDPIII')";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    appleateCountDetails = rs.getInt(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAppleateCountDetails", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getAppleateCountDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAppleateCountDetails", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getAppleateCountDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return appleateCountDetails;
    }

    public IssueRaiseApprovalDTO validDateOfIssue(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        IssueRaiseApprovalDTO issueRaiseApprovalDTO = null;
        String query = null;
        ResultSet rs = null;
        String hospitalName = null;
        String hospitalAddress = null;
        String hospitalQuery = null;
        try {
            con = DBConnection.getConnection();
            query = "select distinct hospital_name,hospital_address from dbo.tblPerson_Disability_Details "
                    + " where person_code = '" + issueRaiseApprovalForm.getSadaremId() + "'";

            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null && !rs.equals("")) {
                while (rs.next()) {
                    hospitalName = rs.getString(1);
                    hospitalAddress = rs.getString(2);

                }
                hospitalQuery = "select convert(varchar,min(created_date),103) mindate ,"
                        + " convert(varchar,max(created_date),103) maxdate"
                        + " from dbo.tblPerson_Disability_Details "
                        + " where hospital_name = '" + hospitalName + "' and hospital_address = '" + hospitalAddress + "'";


                pstmt = con.prepareStatement(hospitalQuery);
                rs = pstmt.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        issueRaiseApprovalDTO = new IssueRaiseApprovalDTO();
                        issueRaiseApprovalDTO.setFromDate(rs.getString(1));
                        issueRaiseApprovalDTO.setToDate(rs.getString(2));
                    }
                }

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "validDateOfIssue", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "validDateOfIssue");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "validDateOfIssue", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "validDateOfIssue");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return issueRaiseApprovalDTO;

    }

    public int approvalForDateOfIssue(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        int approvalForDateOfIssue = 0;
        Connection con = null;
        CallableStatement cstmt = null;

        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call [dbo].[SP_date_change](?,?)}");
            cstmt.setString(1, issueRaiseApprovalForm.getResolvesadaremId());
            cstmt.setString(2, issueRaiseApprovalForm.getResolveDateOfIssue());
            approvalForDateOfIssue = cstmt.executeUpdate();
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "approvalForDateOfIssue", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "approvalForDateOfIssue");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "approvalForDateOfIssue", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "approvalForDateOfIssue");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);
        }
        return approvalForDateOfIssue;
    }

    public String getRelationTypeChange(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        String relationTypeDetails = null;
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map map = null;
        String sadaremId = null;


        try {
            con = DBConnection.getConnection();

            query = "select Relationship, CASE  "
                    + " WHEN Relationship = 1 THEN 'Father' "
                    + " WHEN Relationship = 2 THEN 'Mother' "
                    + " WHEN Relationship = 3 THEN 'Husband' "
                    + " WHEN Relationship = 4 THEN 'Guardian' "
                    + " WHEN Relationship = 5 THEN 'Brother' "
                    + " WHEN Relationship = 6 THEN 'Sister' "
                    + " WHEN Relationship = 7 THEN 'Wife' "
                    + " ELSE '-' "
                    + " END  from tblPerson_Personal_Details  with (nolock) "
                    + "where Person_code='" + issueRaiseApprovalForm.getSadaremId() + "' and status='active'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    relationTypeDetails = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRelationTypeChange", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getRelationTypeChange");


        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRelationTypeChange", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getRelationTypeChange");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);

        }
        return relationTypeDetails;


    }

    public ArrayList getRelationTypeChangeCompare(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        ArrayList relationTypeList = new ArrayList();

        String query = null;
        PreparedStatement pstmt = null;
        Connection con = null;
        ResultSet rs = null;
        Map map = null;
        try {
            con = DBConnection.getConnection();
            query = "select Relationship, CASE  WHEN Relationship = 1 THEN 'Father' WHEN Relationship = 2 THEN 'Mother'"
                    + " WHEN Relationship = 3 THEN 'Husband'  WHEN Relationship = 4 THEN 'Guardian'"
                    + "  WHEN Relationship = 5 THEN 'Brother'"
                    + "  WHEN Relationship = 6 THEN 'Sister'"
                    + "  WHEN Relationship = 7 THEN 'Wife'"
                    + "  ELSE '-'"
                    + " END  from tblPerson_Personal_Details with (nolock) "
                    + "  where Person_code='" + issueRaiseApprovalForm.getSadaremId() + "' and status='active'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    relationTypeList.add(rs.getString(1));
                    relationTypeList.add(rs.getString(2));
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRelationTypeChangeCompare", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getMedicalBoardUserList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRelationTypeChangeCompare", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getMedicalBoardUserList");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return relationTypeList;
    }

    public ArrayList getTypeofDisablity(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        ArrayList getTypeofDisability = new ArrayList();
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map map = null;
        String sadaremId = null;
        try {
            con = DBConnection.getConnection();
            query = "select typeof_disability, CASE  WHEN typeof_disability = 1 THEN 'A.Locomotor/OH' "
                    + "WHEN typeof_disability = 2 THEN 'B.Visual Impairment'"
                    + "  WHEN typeof_disability = 3 THEN 'C.Hearing Impairment'  "
                    + "  WHEN typeof_disability = 4 THEN 'D.Mental Retardation'"
                    + "   WHEN typeof_disability = 5 THEN 'E.Mental Illness'"
                    + "   WHEN typeof_disability = 6 THEN 'F.Multiple Disability'"
                    + "       ELSE '-'"
                    + "       END  from tblPerson_Personal_Details with (nolock) "
                    + "      where Person_code='" + issueRaiseApprovalForm.getSadaremId() + "' and status='active'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();


            if (rs != null) {
                while (rs.next()) {
                    getTypeofDisability.add(rs.getString(1));
                    getTypeofDisability.add(rs.getString(2));
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTypeofDisablity", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getTypeofDisablity");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTypeofDisablity", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getTypeofDisablity");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return getTypeofDisability;


    }

    public ArrayList getSubDisabilityList(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        ArrayList subDisabilityList = new ArrayList();
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map map = null;
        String sadaremId = null;


        try {
            con = DBConnection.getConnection();

            query = "select distinct(s.Sub_Disability_ID),s.Sub_Disability_Name from dbo.tblSubDisability_Details s,"
                    + " tblPerson_Personal_Details p  with (nolock) ,"
                    + " dbo.tblPerson_Disability_Details d"
                    + " where p.person_code='" + issueRaiseApprovalForm.getSadaremId() + "' and p.person_code=d.person_code"
                    + " and p.status='Active' and d.disability_id=s.disability_id  and s.status='Active' "
                    + "and s.sub_disability_id not in(select * from"
                    + " fnSplit((select distinct(sub_disability_id)"
                    + " from tblPerson_Disability_Details  where status='active' and person_code='" + issueRaiseApprovalForm.getSadaremId() + "'),',')as split)";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("subdisabilityId", rs.getString(1));
                    map.put("subdisabilityName", rs.getString(2));
                    subDisabilityList.add(map);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSubDisabilityList", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getSubDisabilityList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSubDisabilityList", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getSubDisabilityList");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return subDisabilityList;


    }

    public ArrayList getSub_Sub_DisabilityList(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        ArrayList sub_sub_DisabilityList = new ArrayList();
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map map = null;
        try {
            con = DBConnection.getConnection();
            query = "select s.Sub_sub_Disability_ID,s.Sub_sub_Disability_Name from dbo.tblSub_Sub_Disabilities s,"
                    + " tblPerson_Personal_Details p with (nolock) ,"
                    + " dbo.tblPerson_Disability_Details d"
                    + "   where p.person_code='" + issueRaiseApprovalForm.getSadaremId() + "' and p.person_code=d.person_code"
                    + " and p.status='Active' and d.disability_id=s.disability_id  "
                    + " and s.Sub_sub_Disability_ID not in (select * from "
                    + " fnSplit((select distinct(Sub_sub_Disability_ID) from tblPerson_Disability_Details "
                    + " where status='active' and person_code='" + issueRaiseApprovalForm.getSadaremId() + "'),',')as split)";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("subsubdisabilityId", rs.getString(1));
                    map.put("subsubdisabilityName", rs.getString(2));
                    sub_sub_DisabilityList.add(map);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSub_Sub_DisabilityList", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getSub_Sub_DisabilityList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSub_Sub_DisabilityList", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getSub_Sub_DisabilityList");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return sub_sub_DisabilityList;
    }

    public String getDiagnosisofDisability(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        String diagnosisofDisability = null;
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            query = "select Diagnosis_of_Disability from tblPerson_Disability_Details"
                    + " where person_code='" + issueRaiseApprovalForm.getSadaremId() + "' and status='active'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    diagnosisofDisability = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDiagnosisofDisability", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getDiagnosisofDisability");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDiagnosisofDisability", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getDiagnosisofDisability");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return diagnosisofDisability;
    }

    public ArrayList activeSadaremId(DataSource ds, String sadaremId) throws SADAREMDBException, SQLException {
        ArrayList act = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        try {
            con = DBConnection.getConnection();
            query = "select status,reasonforpersonstatus from tblPerson_Personal_Details  with (nolock)  where person_code='" + sadaremId + "'";

            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    act.add(rs.getString("status"));
                    act.add(rs.getString("reasonforpersonstatus"));
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "activeSadaremId", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "activeSadaremId");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "activeSadaremId", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "activeSadaremId");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return act;
    }

    //balu start
    public String getAddressChangeValidation(DataSource ds, String sadaremId) throws SADAREMDBException, SQLException {
        String pensioncard_no = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        try {
            con = DBConnection.getConnection();
            query = "select pensioncard_no from tblPerson_Personal_Details  with (nolock) where"
                    + " status='Active' and person_Code='" + sadaremId + "' ";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null && rs.next()) {
                pensioncard_no = rs.getString(1);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "pensioncard_no", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "pensioncard_no");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "pensioncard_no", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "pensioncard_no");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return pensioncard_no;
    }

    public ArrayList getPhaseConversionList(DataSource ds, String pensionId, String distId) throws SADAREMDBException, SQLException {
        ArrayList personalDetails = new ArrayList();
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map map = null;
        try {
            con = DBConnection.getConnection();
            query = "select person_code,surname+first_name Name,relation_name,age_years,district_name,Mandal_name,village_name,habitation_name,Rationcard_number "
                    + " from dbo.tblPerson_Personal_Details a with (nolock) ,"
                    + " tbldistrict_details b,tblmandal_details c,"
                    + " tblvillage_details d,tblhabitation_details e"
                    + " where Rationcard_number in (select rationcardno from disabled_pension"
                    + " where  pensionid = '" + pensionId + "' and distcode = '" + distId + "') and pensionphase = 'PhaseIII' "
                    + " and pensioncard_no in ('PIII','RDPIII','MEESEVAPIII','ADPIII') "
                    + " and a.status = 'Active' and"
                    + " a.districtid = b.district_id and"
                    + " a.districtid = c.district_id and"
                    + " a.mandalid = c.mandal_id and"
                    + " a.districtid = d.district_id and"
                    + " a.mandalid = d.mandal_id and"
                    + " a.villageid = d.village_id and"
                    + " a.habcode = e.habitation_code ";

            //pensionid = '"+pensionId+"' and
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("sadaremid", rs.getString(1));
                    map.put("name", rs.getString(2));
                    map.put("relationname", rs.getString(3));
                    map.put("age", rs.getString(4));
                    map.put("district", rs.getString(5));
                    map.put("mandal", rs.getString(6));
                    map.put("village", rs.getString(7));
                    map.put("habitation", rs.getString(8));
                    map.put("rationcard", rs.getString(9));
                    personalDetails.add(map);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPhaseConversionList", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getPhaseConversionList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPhaseConversionList", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getPhaseConversionList");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return personalDetails;
    }

    public ArrayList getPensionPersonalDetails(DataSource ds, String pensionId, String distId) throws SADAREMDBException, SQLException {
        ArrayList pensionPersonalDetails = new ArrayList();
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map map = null;
        try {
            con = DBConnection.getConnection();
            query = "SELECT PENSIONID,FIRSTNAME+SPACE(1)+MID_NAME+SPACE(1)+COALESCE(LASTNAME,'') AS NAME,"
                    + "AGE,RATIONCARDNO,PENSIONPHASE  "
                    + "FROM DISABLED_PENSION WHERE PENSIONID='" + pensionId + "' AND DISTCODE='" + distId + "' ";
            // System.out.println("query=> "+query);
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("pensionid", rs.getString(1));
                    map.put("name", rs.getString(2));
                    map.put("age", rs.getString(3));
                    map.put("rationcardno", rs.getString(4));
                    map.put("phase", rs.getString(5));
                    pensionPersonalDetails.add(map);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPhaseConversionList", "IssueRaiseApprovalDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getPhaseConversionList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPhaseConversionList", "IssueRaiseApprovalDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getPhaseConversionList");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return pensionPersonalDetails;
    }
    //balu end

    //Sravani Start 24/02/2015

     /**
     * This method is uesd for its Apporoval for AddressChangePhaseIII.
     * @param ds
     * @param issueRaiseApprovalForm
     * @param districtId
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public int issueAddressPhaseIIIResolve(DataSource ds, String oldSADAREMID, String newSADAREMID) throws SADAREMDBException, SQLException {
        int addressChasePhaseIII = 0;
        Connection con = null;
        CallableStatement cstmt = null;



        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call [SP_DeleteDatainAllTables_for_PHASEIIIV1](?,?)}");
            cstmt.setString(1, oldSADAREMID);
            cstmt.setString(2, newSADAREMID);
            addressChasePhaseIII = cstmt.executeUpdate();


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "issueAddressPhaseIIIResolve", "IssueRaiseApprovalDAO", "DataBase");



            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "issueAddressPhaseIIIResolve");


        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "issueAddressPhaseIIIResolve", "IssueRaiseApprovalDAO", "Code");


            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "issueAddressPhaseIIIResolve");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);

        }
        return addressChasePhaseIII;



    }//sravani end
    
    
    
    public int issueNewAddressPhaseIIIResolve(DataSource ds, String oldSADAREMID, String habitationId,String newhouseNo,String newPin) throws SADAREMDBException, SQLException {
        int addressChasePhaseIII = 0;
        Connection con = null;
        CallableStatement cstmt = null;



        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call [SP_UPDATEDatainAllTables_for_ADDRESSCHANGE](?,?,?,?)}");
            cstmt.setString(1, oldSADAREMID);
            cstmt.setString(2, habitationId);
            cstmt.setString(3, newhouseNo);
            cstmt.setString(4, newPin);
            
            addressChasePhaseIII = cstmt.executeUpdate();


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "issueAddressPhaseIIIResolve", "IssueRaiseApprovalDAO", "DataBase");



            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "issueAddressPhaseIIIResolve");


        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "issueAddressPhaseIIIResolve", "IssueRaiseApprovalDAO", "Code");


            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "issueAddressPhaseIIIResolve");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);

        }
        return addressChasePhaseIII;



    } 
    
     public ArrayList getAddressDetails(String sadaremid)
     {
    	 String qry="";
    	 ArrayList addressList = new ArrayList();
    	 qry="select isnull(house_number,'-'),isnull(pin_code,'-') from tblperson_personal_details where person_code='"+sadaremid+"'";
    	try
    	{
    	 addressList = DataAccess.getData(qry);
    	}
    	catch(Exception e)
    	{
    		System.out.println("Exception raised in getAddressDetails"+e);
    	}
    	return addressList;
    	 
     }
    
    
}
