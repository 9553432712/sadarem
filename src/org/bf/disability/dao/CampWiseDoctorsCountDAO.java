/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.net.InetAddress;
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
import org.bf.disability.form.CampWiseDoctorsCountForm;

import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 693461
 */
public class CampWiseDoctorsCountDAO {

    private String systemIp;
    private String queryStr=null; 

    public ArrayList getCampBasedDisabilityDetails(DataSource ds, int campId, String date, String districtId) throws SADAREMDBException, SQLException {

        ArrayList campBasedDisabilityDetails = new ArrayList();
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map disabilityType = null;
        int i = 1;
        try {
            con =DBConnection.getConnection();

            query = "with wap as ( (select e.disability_id disability_id,e.first_doctor_RegNumber doctorregno from tblPerson_Disability_Details e "
                    + " join tblPerson_Disability_TotalValue_Details b on (e.Person_Code=b.Person_Code) \n"
                    + " join tblPerson_Personal_Details c  with (nolock) on (e.Person_Code=c.Person_Code) "
                    + " join tblDisability_Details d on (e.Disability_ID=d.Disability_ID) where c.camp_id=?  and "
                    + " (DATEADD(DD,0,DATEDIFF(DD,0,e.CREATED_DATE)))=? \n"
                    + " and c.district_id=? and \n"
                    + " e.Status='Active' and b.Status='Active' and c.Status='Active' "
                    + " group by e.Disability_Id,e.first_doctor_name,d.Disability_Name,e.first_doctor_Designation,e.first_doctor_RegNumber ) "
                    + " except (select disability_id,doctorregno from tblPwdAssessedDoctorsCount_Details where campid=? \n"
                    + " and district_id=? and \n"
                    + " (DATEADD(DD,0,DATEDIFF(DD,0,assessmentdate)))=? and doctorregno is not null)) "
                    + " select e.disability_id disabilityId,substring(disability_name,3,50) disabilityName, count(e.Person_Code) as doctorCount, first_doctor_name doctorName,\n"
                    + " e.first_doctor_RegNumber doctorRegNumber,e.first_doctor_Designation doctorDesignation "
                    + " from tblPerson_Disability_Details e join tblPerson_Disability_TotalValue_Details b on (e.Person_Code=b.Person_Code) \n"
                    + " join tblPerson_Personal_Details c  with (nolock) on (e.Person_Code=c.Person_Code) "
                    + " join tblDisability_Details d on (e.Disability_ID=d.Disability_ID) \n"
                    + " join wap w  on ( e.first_doctor_RegNumber = w.doctorregno and e.Disability_ID = w.disability_id ) where c.camp_id=? and "
                    + " (DATEADD(DD,0,DATEDIFF(DD,0,e.CREATED_DATE)))=? and\n"
                    + " c.district_id=? and \n"
                    + " e.Status='Active' and b.Status='Active' and c.Status='Active' "
                    + " group by e.Disability_Id,e.first_doctor_name,d.Disability_Name,\n"
                    + " e.first_doctor_Designation,e.first_doctor_RegNumber order by e.Disability_Id,e.first_doctor_name,e.first_doctor_Designation";

            pstmt = con.prepareStatement(query);
            
            pstmt.setString(1,""+campId);
            pstmt.setString(2,date);
            pstmt.setString(3,districtId);
            pstmt.setString(4,""+campId);
            pstmt.setString(5,districtId);
            pstmt.setString(6,date);
            pstmt.setString(7,""+campId);
            pstmt.setString(8,date);
            pstmt.setString(9,districtId); 
            
            
            
            
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    disabilityType = new HashMap();
                    disabilityType.put("disabilityId", rs.getInt(1));
                    disabilityType.put("disabilityName", rs.getString(2));
                    disabilityType.put("doctorCount", rs.getString(3));
                    disabilityType.put("doctorName", rs.getString(4));
                    disabilityType.put("doctorRegNumber", rs.getString(5));
                    disabilityType.put("doctorDesignation", rs.getString(6));
                    disabilityType.put("sno", i);
                    campBasedDisabilityDetails.add(disabilityType);
                    i++;

                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCampBasedDisabilityDetails", "CampWiseDoctorsCountDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "getCampBasedDisabilityDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCampBasedDisabilityDetails", "CampWiseDoctorsCountDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "getCampBasedDisabilityDetails");
        }
        finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return campBasedDisabilityDetails;
    }

    public int insertPwdDoctorCountDetails(DataSource ds, String campDate, int campId, String districtId, String disabilityId, String assessedPwdCount, String docRegNo, String loginId, String SystemIp) throws SADAREMDBException, SQLException {

        int pwdDoctorCountDetails = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;


        try {

            con = DBConnection.getConnection();

            query = "INSERT INTO tblPwdAssessedDoctorsCount_Details(CampId,"
                    + " AssessmentDate,Disability_id,AssessedPwdCount,login_id,System_Ip_Address,"
                    + " Created_date,Updated_date,Status,District_id,DoctorRegNo) VALUES"
                    + "(?,?,?,?,?,?,getDate(),getDate(),'Active',?,?)";

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, campId);
            pstmt.setString(2, campDate);
            pstmt.setString(3, disabilityId);
            pstmt.setString(4, assessedPwdCount);
            pstmt.setString(5, loginId);
            pstmt.setString(6, SystemIp);
            pstmt.setString(7, districtId);
            pstmt.setString(8, docRegNo);


            pwdDoctorCountDetails = pstmt.executeUpdate();




        }catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertPwdDoctorCountDetails", "CampWiseDoctorsCountDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "insertPwdDoctorCountDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertPwdDoctorCountDetails", "CampWiseDoctorsCountDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "insertPwdDoctorCountDetails");
        } finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return pwdDoctorCountDetails;
    }

    public String getCampName(DataSource ds, int campId, String districtId) throws SADAREMDBException, SQLException {

        String campName = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;


        try {
            con = DBConnection.getConnection();
            query = "select name from tblCamp_Details where camp_id ='" + campId + "' and district_id='" + districtId + "' and status='Active'";
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    campName = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCampName", "CampWiseDoctorsCountDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "getCampName");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCampName", "CampWiseDoctorsCountDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "getCampName");
        }finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return campName;
    }

    public String getCertificateCountDetails(DataSource ds, int campId, String district, String date) throws SADAREMDBException, SQLException {

        String certificateCountDetails = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;


        try {
            con = DBConnection.getConnection();
            query = "select count(b.view_edit_mode) from tblPerson_Disability_Details a "
                    + " join tblPerson_Personal_Details b  with (nolock) "
                    + " on(a.person_code=b.person_code) where a.Hospital_Name=(select name from dbo.tblCamp_Details"
                    + " where district_id=? and camp_id=?) "
                    + " and b.view_edit_mode ='View' and "
                    + " dateadd(dd,0,datediff(dd,0, a.created_date))=?";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, district);
            pstmt.setString(2, ""+campId);
            pstmt.setString(3, date);
            
            rs = pstmt.executeQuery();

            if (rs != null) 
            {
                while (rs.next()) 
                {
                    certificateCountDetails = rs.getString(1);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCertificateCountDetails", "CampWiseDoctorsCountDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "getCertificateCountDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCertificateCountDetails", "CampWiseDoctorsCountDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "getCertificateCountDetails");
        } finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return certificateCountDetails;
    }

    public String getPartAEnteredCountDetails(DataSource ds, int campId, String district, String date) throws SADAREMDBException, SQLException {

        String partAEnteredCountDetails = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        try {

            con = DBConnection.getConnection();
            query = "select count(b.view_edit_mode)  from tblPerson_Disability_Details a "
                    + " join tblPerson_Personal_Details b  with (nolock) on(a.person_code=b.person_code) "
                    + " where a.Hospital_Name=(select name from dbo.tblCamp_Details"
                    + " where district_id=? and camp_id=?)  and b.view_edit_mode ='Edit' "
                    + " and dateadd(dd,0,datediff(dd,0, a.created_date))=?";

            pstmt = con.prepareStatement(query);
            
            pstmt.setString(1,district);
            pstmt.setString(2, ""+campId);
            pstmt.setString(3,date);
            
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    partAEnteredCountDetails = rs.getString(1);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPartAEnteredCountDetails", "CampWiseDoctorsCountDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "getPartAEnteredCountDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPartAEnteredCountDetails", "CampWiseDoctorsCountDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "getPartAEnteredCountDetails");
        } finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return partAEnteredCountDetails;

    }

    public ArrayList getCampBasedDisabilityDetailsForMail(DataSource ds, int campId, String district, String date) throws SADAREMDBException, SQLException {

        ArrayList campBasedDisabilityDetails = new ArrayList();
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map disabilityType = null;
        ArrayList tempList = null;
        int i = 1;
        try {
            con = DBConnection.getConnection();
 
            query = "select disability_name,first_doctor_RegNumber,first_doctor_name,first_doctor_Designation, "
                    + " count(a.Person_Code) as Count"
                    + " from tblPerson_Disability_Details a  "
                    + " join tblPerson_Disability_TotalValue_Details b on (a.Person_Code=b.Person_Code) "
                    + " join tblPerson_Personal_Details c  with (nolock) on (a.Person_Code=c.Person_Code) "
                    + " join tblDisability_Details d on (a.Disability_ID=d.Disability_ID)  "
                    + " where Hospital_Name=(select name from dbo.tblCamp_Details"
                    + " where district_id=? and camp_id=?)  and "
                    + " (DATEADD(DD,0,DATEDIFF(DD,0,a.CREATED_DATE)))=? "
                    + " and a.Status='Active' and b.Status='Active' and c.Status='Active' "
                    + " group by a.Disability_Id,a.first_doctor_name,d.Disability_Name, "
                    + " first_doctor_RegNumber,first_doctor_Designation "
                    + " having (case when  a.disability_id=1 then count(a.Person_Code) end)>80 "
                    + " or (case when  a.disability_id=2 then count(a.Person_Code) end)>40"
                    + " or (case when  a.disability_id=3 then count(a.Person_Code) end) >20"
                    + " or (case when  a.disability_id=4 then count(a.Person_Code) end)>20"
                    + " or (case  when a.disability_id=5 then count(a.Person_Code)end)>20"
                    + " order by a.Disability_Id";


            pstmt = con.prepareStatement(query);
            
            pstmt.setString(1,district);
            pstmt.setString(2, ""+campId);
            pstmt.setString(3,date);
            
            
            rs = pstmt.executeQuery();
            int sno = 1;
            if (rs != null)
            {
                while (rs.next()) 
                {
                    tempList = new ArrayList();
                    tempList.add(sno);
                    tempList.add(rs.getString(1));
                    tempList.add(rs.getString(2));
                    tempList.add(rs.getString(3));
                    tempList.add(rs.getString(4));
                    tempList.add(rs.getString(5));
                    //tempList.add(rs.getString(7));
                    campBasedDisabilityDetails.add(tempList);
                    sno++;
                }
            }

        } 
        catch (SQLException sqlEx)
        {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCampBasedDisabilityDetailsForMail", "CampWiseDoctorsCountDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "getCampBasedDisabilityDetailsForMail");
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCampBasedDisabilityDetailsForMail", "CampWiseDoctorsCountDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "getCampBasedDisabilityDetailsForMail");
        } 
        finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return campBasedDisabilityDetails;
    }

    public ArrayList getTypeofDisability(DataSource ds) throws SADAREMDBException,SQLException 
    {
        ArrayList getTypeofDisability = new ArrayList();

        Map tempList = null;
        Connection con = null;
        String query = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();

            query = "select Disability_ID,Disability_Name from tblDisability_Details";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    tempList = new HashMap();
                    tempList.put("disid", rs.getString(1));
                    tempList.put("disName", rs.getString(2));
                    getTypeofDisability.add(tempList);
                }
            }

        } 
        catch (SQLException sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getTypeofDisability", "CampWiseDoctorsCountDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "getTypeofDisability");
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getTypeofDisability", "CampWiseDoctorsCountDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "getTypeofDisability");
        }
        finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(ps);
			DBConnection.closeConnection(con); 
        }
        return getTypeofDisability;
    }

    public ArrayList getDocDetails(DataSource ds, int campId, String disabilityid, String docReg) throws SADAREMDBException, SQLException
    {
        ArrayList doclist = new ArrayList();

        Map tempList = null;
        Connection con = null;
        String query = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        boolean flag = true;
        try {
            con = DBConnection.getConnection();

            query = "SELECT  TOP 1 First_Doctor_Name,First_Doctor_Designation,First_Doctor_RegNumber "
                    + " FROM tblPerson_Disability_Details a join tblCamp_Details b on(a.Hospital_Name=b.Name and a.Hospital_Address=b.Address and a.VenueName=b.VenueName) "
                    + " WHERE First_Doctor_RegNumber =? and b.Camp_id=? and a.Disability_ID=? ORDER BY a.created_date DESC";


            ps = con.prepareStatement(query);
            ps.setString(1, docReg);
            ps.setString(2, ""+campId);
            ps.setString(3, disabilityid);
            
            rs = ps.executeQuery();          

            if (rs != null) 
            {

                while (rs.next())
                {
                    flag = false;
                    tempList = new HashMap();
                    tempList.put("doctorName", rs.getString(1));
                    tempList.put("docDesig", rs.getString(2));
                    doclist.add(tempList);
                }

            } 
            
            if(flag) 
            {
                query = "select b.DoctorName,b.DoctorDesignation,b.DoctorRegNo "
                        + " from tblCampDoctorDetails b "
                        + " where b.CampId=? and b.DisabilityId=? and b.DoctorRegNo=?";

                ps = con.prepareStatement(query);
                ps.setString(1, ""+campId);
                ps.setString(2,  disabilityid);
                ps.setString(3, docReg);
                
                
                rs2 = ps.executeQuery();
                if (rs2 != null)
                {

                    while (rs2.next()) 
                    { 
                        tempList = new HashMap();
                        tempList.put("doctorName", rs2.getString(1));
                        tempList.put("docDesig", rs2.getString(2));
                        doclist.add(tempList);
                    }

                }
            }



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDocDetails", "CampWiseDoctorsCountDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "getDocDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDocDetails", "CampWiseDoctorsCountDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "getDocDetails");
        } finally {
            try {
                DBConnection.closeConnection(con);
                DBConnection.closeStatement(ps);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return doclist;

    }

    public int insertDoctor(DataSource ds, String loginid, int campid, String disabilityid, CampWiseDoctorsCountForm formBean) throws SADAREMDBException, SQLException {
        int success = 0;

        Connection con = null;
        String query = null;
        PreparedStatement ps = null;

        try {
            con = DBConnection.getConnection();
            InetAddress ownIP = InetAddress.getLocalHost();
            systemIp = ownIP.getHostAddress();
            Date AD = new SimpleDateFormat("dd/MM/yyyy").parse(formBean.getCampDate());
            String campdate = new SimpleDateFormat("MM/dd/yyyy").format(AD);

            query = "INSERT INTO tblCampDoctorDetails"
                    + " (CampId,CampDate,DisabilityId,DoctorName,DoctorRegNo,"
                    + " DoctorDesignation,CreatedDate,UpdatedDate,SystemIP,"
                    + " LoginId) VALUES(?,?,?,?,?,?,getDate(),getDate(),?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, campid);
            ps.setString(2, campdate);
            ps.setString(3, formBean.getDisabilityid());
            ps.setString(4, formBean.getDocname());
            ps.setString(5, formBean.getDocreg());
            ps.setString(6, formBean.getDocdesig());
            ps.setString(7, systemIp);
            ps.setString(8, loginid);
            success = ps.executeUpdate();


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertDoctor", "CampWiseDoctorsCountDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "insertDoctor");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertDoctor", "CampWiseDoctorsCountDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "insertDoctor");
        } finally {
            try {
                DBConnection.closeConnection(con);
                DBConnection.closeStatement(ps);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return success;

    }

    public int insertDocotorCount(DataSource ds, String loginid, int campid, String districtid, CampWiseDoctorsCountForm formBean) throws SADAREMDBException, SQLException {
        int success = 0;

        Connection con = null;
        String query = null;
        PreparedStatement ps = null;

        try {
            con = DBConnection.getConnection();
            InetAddress ownIP = InetAddress.getLocalHost();
            systemIp = ownIP.getHostAddress();
            Date AD = null;
            String campdate = null;

            query = "INSERT INTO tblPwdAssessedDoctorsCount_Details(CampId,"
                    + " AssessmentDate,Disability_id,AssessedPwdCount,login_id,System_Ip_Address,"
                    + " Created_date,Updated_date,Status,District_id,DoctorRegNo) VALUES"
                    + "(?,?,?,?,?,?,getDate(),getDate(),'Active',?,?)";

            ps = con.prepareStatement(query);

            if (formBean.getTotalCount() != null && formBean.getTotalCount().length > 0) {
            	
                for (int i = 0; i < formBean.getTotalCount().length; i++) 
                {
                    AD = new SimpleDateFormat("dd/MM/yyyy").parse(formBean.getCampDate());
                    campdate = new SimpleDateFormat("MM/dd/yyyy").format(AD);
                    ps.setInt(1, campid);
                    ps.setString(2, campdate);
                    ps.setString(3, formBean.getDisabilityId()[i]);
                    ps.setString(4, formBean.getTotalCount()[i]);
                    ps.setString(5, loginid);
                    ps.setString(6, systemIp);
                    ps.setString(7, districtid);
                    ps.setString(8, formBean.getDoctorReg()[i]);
                    ps.addBatch();
                }
            }
            int x[] = ps.executeBatch();
            success = x.length;

        } catch (SQLException sqlEx) {
          
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertDocotorCount", "CampWiseDoctorsCountDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "insertDocotorCount");
        } catch (Exception sqlEx) {
            
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertDocotorCount", "CampWiseDoctorsCountDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "insertDocotorCount");
        } finally {
            try {
                DBConnection.closeConnection(con);
                DBConnection.closeStatement(ps);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return success;

    }

    public ArrayList getCampWiseDoctorsAssCountDetails(DataSource ds, int campId, String date, String disabilityid) throws SADAREMDBException, SQLException {
        ArrayList doclist = new ArrayList();

        Map tempList = null;
        Connection con = null;
        String query = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();

            Date AD = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            String rdate = new SimpleDateFormat("MM/dd/yyyy").format(AD);

            query = "select First_Doctor_Name,First_Doctor_RegNumber,First_Doctor_Designation,count(Person_Code) as count,c.Disability_Name"
                    + "  from tblPerson_Disability_Details a"
                    + "  join tblDisability_Details c on (a.Disability_Id=c.Disability_ID) "
                    + "  join tblCamp_Details b on(a.Hospital_Name=b.Name and a.Hospital_Address=b.Address and a.VenueName=b.VenueName)"
                    + "  where b.Camp_id=? "
                    + "  and (DATEADD(DD,0,DATEDIFF(DD,0,a.Created_date)))=?"
                    + "  group by c.Disability_Name,First_Doctor_Name,First_Doctor_RegNumber,First_Doctor_Designation";


            ps = con.prepareStatement(query);
            ps.setString(1, ""+campId);
            ps.setString(1, rdate);
            
            rs = ps.executeQuery();
            int sno = 1;
            if (rs != null && !(rs.next())) 
            {
                query = "select a.DoctorName,a.DoctorRegNo,a.DoctorDesignation,b.assessedPwdCount,"
                        + "c.Disability_Name from tblCampDoctorDetails a join tblPwdAssessedDoctorsCount_Details b "
                        + " on(a.campId=b.campId and a.DisabilityId=b.Disability_Id) "
                        + " join tblDisability_Details c on (a.DisabilityId=c.Disability_ID) where b.Campid=? and \n"
                        + " (DATEADD(DD,0,DATEDIFF(DD,0,b.assessmentDate)))=?\n"
                        + " GROUP BY a.DoctorName,a.DoctorRegNo,a.DoctorDesignation,b.assessedPwdCount,c.Disability_Name ";
                
                ps = con.prepareStatement(query);
                ps.setString(1,""+campId);
                ps.setString(2,rdate);
                
                rs = ps.executeQuery();
            }

            if (rs != null) 
            {
                while (rs.next()) 
                {
                    tempList = new HashMap();
                    tempList.put("sno", sno);
                    tempList.put("doctorName", rs.getString(1));
                    tempList.put("doctorRegNumber", rs.getString(2));
                    tempList.put("desig", rs.getString(3));
                    tempList.put("count", rs.getString(4));
                    tempList.put("disability", rs.getString(5));
                    doclist.add(tempList);
                    sno++;
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampWiseDoctorsAssCountDetails", "CampWiseDoctorsCountDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "getCampWiseDoctorsAssCountDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampWiseDoctorsAssCountDetails", "CampWiseDoctorsCountDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "getCampWiseDoctorsAssCountDetails");
        } finally {
            try {
                DBConnection.closeConnection(con);
                DBConnection.closeStatement(ps);
                DBConnection.closeResultSet(rs);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return doclist;
    }

    public int checkDoctorCount(DataSource ds, int campId, String date, String disabilityid) throws SADAREMDBException, SQLException {
        int count = 0;

        Connection con = null;
        String query = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();

            Date AD = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            String rdate = new SimpleDateFormat("MM/dd/yyyy").format(AD);

            query = "select count(*) as count from tblPwdAssessedDoctorsCount_Details"
                    + " where CampId=? and (DATEADD(DD,0,DATEDIFF(DD,0,AssessmentDate)))=?";


            ps = con.prepareStatement(query);
            ps.setString(1, ""+campId);
            ps.setString(2, rdate);
            
            rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkDoctorCount", "CampWiseDoctorsCountDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "checkDoctorCount");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkDoctorCount", "CampWiseDoctorsCountDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "checkDoctorCount");
        } finally {
            try {
                DBConnection.closeConnection(con);
                DBConnection.closeStatement(ps);
                DBConnection.closeResultSet(rs);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public int checkDoctorWithRegistrationNo(DataSource ds, int campId, String regNo, String disabilityid) throws SADAREMDBException, SQLException {
        int count = 0;
 

        try { 
        	ArrayList paramList = new ArrayList();
        	ArrayList tempList = new ArrayList();
        	
        	
        	queryStr = " select COUNT(*) from tblCampDoctorDetails where campid=? and disabilityId=? and doctorRegNo=?";

  
            
            tempList = new ArrayList();
            tempList.add("S");
            tempList.add(campId);
            paramList.add(tempList);
            
            tempList = new ArrayList();
            tempList.add("S");
            tempList.add(disabilityid);
            paramList.add(tempList);
            
            tempList = new ArrayList();
            tempList.add("S");
            tempList.add(regNo);
            paramList.add(tempList);
            
            
            count =Integer.parseInt(DataAccess.getReturnResultByPstmt(queryStr, paramList));
            
            
        } catch (SQLException sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkDoctorWithRegistrationNo", "CampWiseDoctorsCountDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "checkDoctorWithRegistrationNo");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkDoctorWithRegistrationNo", "CampWiseDoctorsCountDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "checkDoctorWithRegistrationNo");
        } 
        return count;
    }

    public String getDisabilityName(DataSource ds, String disabilityid) throws SADAREMDBException, SQLException 
    {
        String disabilityName = null;
 
        try {
        	ArrayList paramList = new ArrayList();
        	ArrayList tempList = new ArrayList();
        	
        	
           String queryStr = "select Disability_Name from tblDisability_Details where Disability_id=?";
           
           tempList = new ArrayList();
           tempList.add("S");
           tempList.add(disabilityid);
           paramList.add(tempList);
           
           disabilityName = DataAccess.getReturnResultByPstmt(queryStr, paramList);
        } 
        catch (SQLException sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityName", "CampWiseDoctorsCountDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "getDisabilityName");
        }
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityName", "CampWiseDoctorsCountDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampWiseDoctorsCountDAO", "getDisabilityName");
        } 
        return disabilityName;
    }
    /* public boolean EmailBasedOnDisabilityDoctorsCount(DataSource ds,ArrayList doctorCount) throws SADAREMDBException, SQLException {

    boolean msg = false;
    //ArrayList<InternetAddress> RecMail = new ArrayList<InternetAddress>();
    ArrayList<InternetAddress> CCMailsList = new ArrayList<InternetAddress>();
    ArrayList<InternetAddress> TOMailsList = new ArrayList<InternetAddress>();
    ArrayList<InternetAddress> BCCMailsList = new ArrayList<InternetAddress>();
    String subject  = "PWD's Assessed the Doctor's Information";
    String body = "In that Particaular Camp the Doctor Assessed Disabilities";
    String from = "abhirams.g@tcs.com";
    Connection con = null;

    EmailUtility emailUtility = new EmailUtility();
    //CCMailsList, ArrayList<InternetAddress> BCCMailsList, String subject, String message, String from

    try {
    con = DBConnection.getConnection();

    ArrayList<InternetAddress> RecMail = new ArrayList<InternetAddress>();
    InternetAddress emailId = null;

    emailId = new InternetAddress("abhiram143@yahoo.com");
    RecMail.add(emailId);

    msg = EmailUtility.SendEmail(RecMail, RecMail, RecMail, subject, body, "abhirams.g@tcs.com");

    } catch (Exception e) {
    e.printStackTrace();
    }

    return  msg;

    }*/
}
