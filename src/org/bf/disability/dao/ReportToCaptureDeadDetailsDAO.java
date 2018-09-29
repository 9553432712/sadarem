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
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.ReportToCaptureDeadDetailsForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 310926
 */
public class ReportToCaptureDeadDetailsDAO {

    public ArrayList validateSADAREMID(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException {
        ArrayList SADAREMIDValidDetails = new ArrayList();
        String personcode = null;
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map map = null;
        try {
            con = DBConnection.getConnection();
            query = " select P.PERSON_CODE AS SADAREMCODE,P.SURNAME + SPACE(2) + P.FIRST_NAME AS PERSONNAME ,'GENDER' =   "
                    + " CASE WHEN Gender = 1 THEN 'Male'  WHEN Gender = 2 THEN 'Female' ELSE 'Not Entered'   END,   "
                    + " coalesce(relation_name,'-') relation,coalesce(phone_no,'-') phone_no,habitation_name,e.village_name,d.mandal_name,p.proof_id, p.proofdoc_type "
                    + " from dbo.tblPerson_Personal_Details p with (nolock) ,tblmandal_details d,  tblvillage_details e , tblhabitation_details h  where "
                    + " p.districtid = d.district_id and  p.mandalid = d.mandal_id and  p.districtid = e.district_id and  p.mandalid = e.mandal_id and  "
                    + " p.villageid = e.village_id and   p.habcode = h.habitation_code and p.person_code=? and  p.status='Active' ";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, reportForm.getPersoncode());
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();

                    map.put("personCode", rs.getString(1));
                    map.put("name", rs.getString(2));
                    map.put("gender", rs.getString(3));
                    map.put("relationName", rs.getString(4));
                    map.put("phoneno", rs.getString(5));
                    map.put("habitation", rs.getString(6));
                    map.put("village", rs.getString(7));
                    map.put("mandal", rs.getString(8)); 
                    if (rs.getString(9) != null && (rs.getString(10).equalsIgnoreCase("Adhaar Card") || rs.getString(10).equalsIgnoreCase("Aadhaar Card") || rs.getString(10).equalsIgnoreCase("Aadhaar ID") || rs.getString(10).equalsIgnoreCase("Aadhar Card"))) {
                        map.put("proof_id", rs.getString(9));


                    }
                    SADAREMIDValidDetails.add(map);
                }
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "validateSADAREMID", "ReportToCaptureDeadDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsDAO", "validateSADAREMID");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "validateSADAREMID", "ReportToCaptureDeadDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsDAO", "validateSADAREMID");
        } finally {
            DBConnection.closeConnection(con);
        }
        return SADAREMIDValidDetails;
    }
    public String validateAAdhracardEligible(DataSource ds, String personCode, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException {



        String validateAAdhracardEligible = null;
        Connection con = null;
        CallableStatement cstmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        Map map = null;
        String sadaremId = null;


        try {
            con = DBConnection.getConnection();

            stmt = con.createStatement();
            cstmt = con.prepareCall("{Call [SadaremidEligibleOrRejected](?)}");
            cstmt.setString(1, personCode);
            rs = cstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {

                    validateAAdhracardEligible = rs.getString(2);

               

                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(stmt);
            DBConnection.closeResultSet(rs);

        }
        return validateAAdhracardEligible;


    }

    public String validateInActive(DataSource ds, String person_code, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException {

        String validateInActive = null;
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map map = null;
        String sadaremId = null;


        try {
            con = DBConnection.getConnection();

            query = "select person_code from tblPerson_Personal_Details where status='inActive' and person_code=?";


            pstmt = con.prepareStatement(query);
            pstmt.setString(1, reportForm.getPersoncode());
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    validateInActive = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);

        }
        return validateInActive;


    }

    public String EditPwdValidation(DataSource ds, String person_code, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException {


     
        String EditPwdValidation = null;
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map map = null;
        String sadaremId = null;


        try {
            con = DBConnection.getConnection();

            // query = "select person_code from tblPerson_Personal_Details where status='inActive' and person_code='" + reportForm.getPersoncode() + "'";

            query = "select View_Edit_mode from dbo.tblPerson_Personal_Details where Person_code=?  " +
                    " and status='Active'";

        
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, reportForm.getPersoncode());
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    EditPwdValidation = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);

        }
        return EditPwdValidation;


    }

    public String personstatus(DataSource ds, String person_code, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException {

        String ReasonforPersonStatus = null;
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map map = null;
        String sadaremId = null;


        try {
            con = DBConnection.getConnection();

            query = "select ReasonforPersonStatus from tblPerson_Personal_Details where status='inActive' and person_code=?";

      
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, reportForm.getPersoncode());
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ReasonforPersonStatus = rs.getString(1);

                
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);

        }
        return ReasonforPersonStatus;


    }

    public int alreadyExist(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException {

        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int i=0;
        try {
            con = DBConnection.getConnection();
            query = " select count(*) from dataValidationForPwD where person_code=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, reportForm.getPersoncode());
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                  i=rs.getInt(1);
                }
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "alreadyExist", "ReportToCaptureDeadDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsDAO", "alreadyExist");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "alreadyExist", "ReportToCaptureDeadDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsDAO", "alreadyExist");
        } finally {
            DBConnection.closeConnection(con);
        }
        return i;
    }

    public int insertDetails(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException {

        int status = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        try {
            String deathdate = null;

            if (reportForm.getDeathdate() != null && !reportForm.getDeathdate().equalsIgnoreCase("")) {
                Date ddate = new SimpleDateFormat("dd/mm/yyyy").parse(reportForm.getDeathdate());
                deathdate = new SimpleDateFormat("mm/dd/yyyy").format(ddate);
            }
            String shgdate = null;
            if (reportForm.getDeathdate() != null  && !reportForm.getShgdate().equalsIgnoreCase("")) {
                Date sdate = new SimpleDateFormat("dd/mm/yyyy").parse(reportForm.getShgdate());
                shgdate = new SimpleDateFormat("mm/dd/yyyy").format(sdate);
            }
            String appliancedate = null;
            if (reportForm.getDeathdate() != null  && !reportForm.getAppdate().equalsIgnoreCase("")) {
                Date appdate = new SimpleDateFormat("dd/mm/yyyy").parse(reportForm.getAppdate());
                appliancedate = new SimpleDateFormat("mm/dd/yyyy").format(appdate);
            }
            String surgicaldate = null;
            if (reportForm.getDeathdate() != null  && !reportForm.getSurdate().equalsIgnoreCase("")) {
                Date sudate = new SimpleDateFormat("dd/mm/yyyy").parse(reportForm.getSurdate());
                surgicaldate = new SimpleDateFormat("mm/dd/yyyy").format(sudate);
            }

            String recivedDate = null;
            if (reportForm.getRecivedDate() != null && !reportForm.getRecivedDate().equalsIgnoreCase("")) {
                Date date = new SimpleDateFormat("dd/mm/yyyy").parse(reportForm.getRecivedDate());

                recivedDate = new SimpleDateFormat("mm/dd/yyyy").format(date);
            }

            String dataOperatorDate = null;
            if (reportForm.getDataOperatordate() != null && !reportForm.getDataOperatordate().equalsIgnoreCase("")) {
                Date date = new SimpleDateFormat("dd/mm/yyyy").parse(reportForm.getDataOperatordate());
                dataOperatorDate = new SimpleDateFormat("mm/dd/yyyy").format(date);
            }
            con = DBConnection.getConnection();

            query = "INSERT INTO [dbo].[dataValidationForPwD]([Person_code],[AADHAR_Exist],[aadharCardNo],[aadharRemarks],[dead_or_alive],[deathdate]"
                    + "  ,[Member_of_SHG],[shgReason],[shgname],[shgid],[shgdate],[AIDS_and_Appliances],[appliancesReason],[appliancestype]"
                    + " ,[appliancesOrganization],[appliancesdate],[Surgical_correction],[SurgicalReason],[SurgicaltypeOfApp],[SurgicalOrganization]"
                    + " ,[Surgicaldate],[PMJDY_details],[PMJDYReason],[PMJDYAccNo],[PMJDYBank],[PMJDYBranch],[AASARA_ID],[AASARARemarks]"
                    + ",[aasaraPensionId],[Created_date],[updated_date],[Login_id],[System_ip],[Status],PMJDYIFSCCode,servveid,serveDoneBy,designation,recivedDate)VALUES"
                    + " (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,getdate(),getDate(),?, ?,'Active',?,?,?,?,?)";

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, reportForm.getPersoncode());
            pstmt.setString(2, reportForm.getAadharRadio());
            pstmt.setString(3, reportForm.getAadharCardNo());
            pstmt.setString(4, reportForm.getAadharRemarks());
            pstmt.setString(5, reportForm.getDeadRadio());
            pstmt.setString(6, deathdate);
            pstmt.setString(7, reportForm.getSHGRadio());
            pstmt.setString(8, reportForm.getShgReason());
            pstmt.setString(9, reportForm.getShgname());
            pstmt.setString(10, reportForm.getShgid());
            pstmt.setString(11, shgdate);
            pstmt.setString(12, reportForm.getAppRadio());
            pstmt.setString(13, reportForm.getAppReason());
            pstmt.setString(14, reportForm.getTypeOfApp());
            pstmt.setString(15, reportForm.getAppOrganization());
            pstmt.setString(16, appliancedate);
            pstmt.setString(17, reportForm.getSurRadio());
            pstmt.setString(18, reportForm.getSurReason());
            pstmt.setString(19, reportForm.getSurtypeOfApp());
            pstmt.setString(20, reportForm.getSurOrganization());
            pstmt.setString(21, surgicaldate);
            pstmt.setString(22, reportForm.getpRadio());
            pstmt.setString(23, reportForm.getpReason());
            pstmt.setString(24, reportForm.getpAccNo());
            pstmt.setString(25, reportForm.getpBank());
            pstmt.setString(26, reportForm.getpBranch());
            pstmt.setString(27, reportForm.getAasaraRadio());
            pstmt.setString(28, reportForm.getaRemarks());
            pstmt.setString(29, reportForm.getAasaraPensionId());
            pstmt.setString(30, reportForm.getLoginId());
            pstmt.setString(31, reportForm.getSystemIp());
            pstmt.setString(32, reportForm.getpIFSCCode());
            pstmt.setString(33, reportForm.getServeid());
            pstmt.setString(34, reportForm.getServeDoneBy());
            pstmt.setString(35, reportForm.getDesignation());
            pstmt.setString(36, recivedDate);

            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
        			DBConnection.closeStatement(pstmt);
        			DBConnection.closeConnection(con);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    public ArrayList getVoList(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException {
        ArrayList voList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        Map map = null;
        String districtId = null;
        String mandalId = null;
        String panchayatId = null;
        String shgQuery = null;
        PreparedStatement shgPstmt = null;
        ResultSet maxRs = null;
        try {
            con = DBConnection.getConnection();
            query = "select substring(person_code,1,2),substring(person_code,6,2),substring(person_code,13,2)"
                    + " from tblperson_personal_Details where status ='Active' and  person_code=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, reportForm.getPersoncode());
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    districtId = rs.getString(1);
                    mandalId = rs.getString(2);
                    panchayatId = rs.getString(3);
                }
            }
            shgQuery = "SELECT VO_ID,VO_NAME FROM SHGMASTER..VOMASTER WHERE DISTRICT_ID=?"
                    + " AND MANDAL_ID=? AND panchayath_ID=? ORDER BY VO_NAME";

            shgPstmt = con.prepareStatement(shgQuery);
            shgPstmt.setString(1, districtId);
            shgPstmt.setString(2, mandalId);
            shgPstmt.setString(3, panchayatId);
            maxRs = shgPstmt.executeQuery();
            if (maxRs != null) {
                while (maxRs.next()) {
                    map = new HashMap();
                    map.put("voId", maxRs.getString(1));
                    map.put("voName", maxRs.getString(2));
                    voList.add(map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                	DBConnection.closeResultSet(rs);
        			DBConnection.closeStatement(pstmt);
        			DBConnection.closeConnection(con);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return voList;

    }

    public ArrayList voNameshgMappingDetails(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ArrayList voName = new ArrayList();
        ResultSet rs = null;
        String shgid = null;
        String voId = null;
        String voidQuery = null;
        PreparedStatement voPstmt = null;
        ResultSet voRs = null;
        String voidNameQuery = null;
        PreparedStatement voNamePstmt = null;
        ResultSet vonameRs = null;


        try {
            con = DBConnection.getConnection();

            query = "select shgid from TASKTRACKINGTG..shgmappingDetails where sadaremid =?";
            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, reportForm.getPersoncode());
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    shgid = rs.getString(1);
                }
            }
            voidQuery = "select vo_id from SHGMASTER..Shg_master" + reportForm.getDistrict_id() + " where shg_id=?";
            
            voPstmt = con.prepareStatement(voidQuery);
            voPstmt.setString(1, shgid);
            voRs = voPstmt.executeQuery();
            if (voRs != null) {
                while (voRs.next()) {
                    voId = voRs.getString(1);
                }
            }
            voidNameQuery = "SELECT vo_id,vo_name  FROM SHGMASTER..VOMASTER where vo_id=?";
            
            voNamePstmt = con.prepareStatement(voidNameQuery);
            voNamePstmt.setString(1, voId);
            vonameRs = voNamePstmt.executeQuery();
            if (vonameRs != null) {
                while (vonameRs.next()) {
                    voName.add(vonameRs.getString(1));
                    voName.add(vonameRs.getString(2));
                }
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "voNameshgMappingDetails", "ReportToCaptureDeadDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsDAO", "voNameshgMappingDetails");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "voNameshgMappingDetails", "ReportToCaptureDeadDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsDAO", "voNameshgMappingDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return voName;

    }

    public ArrayList shgMappingName(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException {
        ArrayList shgMappingName = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        String sqlQuery = null;
        PreparedStatement sqlPstmt = null;
        ResultSet sqlRs = null;
        String shgId = null;
        try {
            con = DBConnection.getConnection();

            sqlQuery = "select shgid from TASKTRACKINGTG..shgmappingDetails where  sadaremid =?";
            sqlPstmt = con.prepareStatement(sqlQuery);
            sqlPstmt.setString(1, reportForm.getPersoncode());
            sqlRs = sqlPstmt.executeQuery();
            if (sqlRs != null) {
                while (sqlRs.next()) {
                    shgId = sqlRs.getString(1);
                }
            }

            query = "select vo_id,shg_id,shg_name from SHGMASTER..Shg_master" + reportForm.getDistrict_id() + " where shg_id =?";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, shgId);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    shgMappingName.add(rs.getString(1));
                    shgMappingName.add(rs.getString(2));
                    shgMappingName.add(rs.getString(3));
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "shgMappingName", "ReportToCaptureDeadDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsDAO", "shgMappingName");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "shgMappingName", "ReportToCaptureDeadDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsDAO", "shgMappingName");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return shgMappingName;
    }

    public int getMappingCount(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        int mappingCount = 0;
        try {
            con = DBConnection.getConnection();
            query = "select count(*) from TASKTRACKINGTG..shgmappingDetails where  sadaremid =?";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, reportForm.getPersoncode());
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    mappingCount = rs.getInt(1);
                }

            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMappingCount", "ReportToCaptureDeadDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsDAO", "getMappingCount");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMappingCount", "ReportToCaptureDeadDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsDAO", "getMappingCount");
        } finally {

            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }

        return mappingCount;
    }

    public ArrayList getShgDropdownDetails(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException {
        ArrayList shgDropdownDetails = new ArrayList();
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map map = null;
        try {
            con = DBConnection.getConnection();
            System.out.println(reportForm.getVoId());
            query = "select shgid,nameofpwdshg,void from TASKTRACKINGTG..SHGInformation  where void =?"
                    + " union select shg_id collate Latin1_General_CI_AI,shg_name collate Latin1_General_CI_AI,vo_id collate Latin1_General_CI_AI from SHGMASTER..Shg_master" + reportForm.getDistrict_id() + "" +
                    " where vo_id =?";

            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, reportForm.getVoId());
            pstmt.setString(2, reportForm.getVoId());
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("shgId", rs.getString(1));
                    map.put("shgname", rs.getString(2));
                    map.put("void", rs.getString(3));
                    shgDropdownDetails.add(map);
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getShgDropdownDetails", "ReportToCaptureDeadDetailsDAO", "DataBase");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsDAO", "getShgDropdownDetails");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getShgDropdownDetails", "ReportToCaptureDeadDetailsDAO", "Code");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsDAO", "getShgDropdownDetails");
        } finally {

            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }
        return shgDropdownDetails;

    }

    public String getShgIdDetails(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        String shgid = null;

        try {
            con = DBConnection.getConnection();
            query = "select shgid from (select shgid,nameofpwdshg from TASKTRACKINGTG..SHGInformation "
                    + " where void =? union select shg_id collate Latin1_General_CI_AI,shg_name collate Latin1_General_CI_AI "
                    + " from SHGMASTER..Shg_master" + reportForm.getDistrict_id() + ""
                    + " where vo_id =? ) a where a.nameofpwdshg = ?";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, reportForm.getVoId());
            pstmt.setString(2, reportForm.getVoId());
            pstmt.setString(3, reportForm.getShgname());
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    shgid = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getShgIdDetails", "ReportToCaptureDeadDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsDAO", "getShgIdDetails");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getShgIdDetails", "ReportToCaptureDeadDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsDAO", "getShgIdDetails");
        } finally {

            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }
        return shgid;
    }
}
