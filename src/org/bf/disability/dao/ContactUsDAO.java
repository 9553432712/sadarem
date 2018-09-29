package org.bf.disability.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.ContactUsDTO;
import org.bf.disability.form.ContactUsForm;

import com.tcs.sgv.common.util.DBConnection;

public class ContactUsDAO {

    public synchronized int insertContactUsDetails(DataSource ds, ContactUsForm contactUsForm) throws Exception {
        int contactDetails = 0;
        String query = null;
        Connection con = null;
        //Statement st = null;
        
       
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            //st = con.createStatement();
            query = "insert into Contactus(DistrictId,campId,District,Name,Designation,Contactno,LandLine,Email,type,Status,login,ip,createddate,updateddate)"
                    + "values(?,?,?,?,"
                    + "?, ?,'-',?,"
                    + " ?,'Active',?,?,"
                    + "getDate(),getDate())";

           pstmt = con.prepareStatement(query);
           pstmt.setString(1, contactUsForm.getDistrictId());
           pstmt.setString(2, contactUsForm.getCampId());
           pstmt.setString(3, contactUsForm.getDistrictName());
           pstmt.setString(4, contactUsForm.getEmployeeName());
           pstmt.setString(5, contactUsForm.getDesignation());
           pstmt.setString(6, contactUsForm.getEmpContact());
           pstmt.setString(7, contactUsForm.getEmail());           
           pstmt.setString(8, contactUsForm.getContactUsType() );
           pstmt.setString(9, contactUsForm.getLoginId());
           pstmt.setString(10, contactUsForm.getSystemIp());


            contactDetails = pstmt.executeUpdate();
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertContactUsDetails", "ContactUsDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ContactUsDAO", "insertContactUsDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertContactUsDetails", "ContactUsDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ContactUsDAO", "insertContactUsDetails");
        } finally {
            DBConnection.closeConnection(con);
           // DBConnection.closeStatement(st);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }

        return contactDetails;
    }

    public ContactUsDTO editDetails(DataSource ds, String id) throws Exception {
        String query = null;
        Connection con = null;
        //Statement st = null;
        PreparedStatement pstmt =null ;
        
        ResultSet rs = null;
        Integer count = 0;
        ContactUsDTO contactUsDto = new ContactUsDTO();
        try {
            con = DBConnection.getConnection();
           // st = con.createStatement();
            query = "select rowid,Name,Contactno,Email,type from contactus where  rowid=? and  status='Active'";
            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
             
            while (rs.next()) {
                contactUsDto.setRowId(rs.getString(1));
                contactUsDto.setEmployeeName(rs.getString(2));
                StringTokenizer stringTokenizer = new StringTokenizer(rs.getString(3), "-");
                contactUsDto.setContactCode(stringTokenizer.nextToken());
                contactUsDto.setEmpContact(stringTokenizer.nextToken());
                contactUsDto.setEmail(rs.getString(4));
                contactUsDto.setContactUsType(rs.getString(5));
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "editDetails", "ContactUsDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ContactUsDAO", "editDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "editDetails", "ContactUsDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ContactUsDAO", "editDetails");
        } finally {
            DBConnection.closeConnection(con);
          //  DBConnection.closeStatement(st);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return contactUsDto;
    }

    public int updateDetails(DataSource ds, ContactUsForm contactUsForm) throws Exception {
        int contactDetails = 0;
        String query = null;
        Connection con = null;
       // Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
         //   st = con.createStatement();
            
            query = "update contactus set Name=?,"
                    + "Contactno=?,type=?,"
                    + "updateddate=getDate(),Email=?,Designation=? where rowid=?";

            pstmt=con.prepareStatement(query);
            pstmt.setString(1,contactUsForm.getEmployeeName() );
            pstmt.setString(2, contactUsForm.getEmpContact());
            pstmt.setString(3,  contactUsForm.getContactUsType());
            pstmt.setString(4, contactUsForm.getEmail());
            pstmt.setString(5,  contactUsForm.getDesignation());
            pstmt.setString(6,  contactUsForm.getRowId()+"" );
            
            
            contactDetails = pstmt.executeUpdate();
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateDetails", "ContactUsDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ContactUsDAO", "updateDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateDetails", "ContactUsDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ContactUsDAO", "updateDetails");
        } finally {
            DBConnection.closeConnection(con);
         //   DBConnection.closeStatement(st);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }

        return contactDetails;
    }

    public int InActiveStatus(DataSource ds, String contactID) throws Exception {
        int contactDetails = 0;
        String query = null;
        Connection con = null;
    //    Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
           // st = con.createStatement();
            query = " update contactus set status='inActive' where rowid=?";
            
            pstmt=con.prepareStatement(query);
            pstmt.setString(1, contactID);
            
            contactDetails = pstmt.executeUpdate();
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "InActiveStatus", "ContactUsDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ContactUsDAO", "InActiveStatus");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "InActiveStatus", "ContactUsDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ContactUsDAO", "InActiveStatus");
        } finally {
            DBConnection.closeConnection(con);
            //DBConnection.closeStatement(st);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return contactDetails;

    }

    public ArrayList getContactUsDetails(DataSource ds, String type) throws Exception {
        ArrayList contactDetails = new ArrayList();
        String query = null;
        Connection con = null;
      //  Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        HashMap contactMap = null;
        int sno = 0;
        try {
            con = DBConnection.getConnection();
          //  st = con.createStatement();
           //System.out.println("Type : "+type);
            query = " select rowid,District,Name,Designation,Contactno,LandLine,Email,type,DistrictId,campId from ContactUs where status='Active' and type=? order by district";
            
            pstmt=con.prepareStatement(query);
            pstmt.setString(1,type+"");
            
            rs = pstmt.executeQuery();
            if (rs != null) 
            {
                sno = 1;
                while (rs.next()) 
                {

                    contactMap = new HashMap();
                    contactMap.put("rowid", sno);
                    contactMap.put("District", rs.getString(2));
                    contactMap.put("Name", rs.getString(3));
                    contactMap.put("Designation", rs.getString(4));
                    contactMap.put("Contactno", rs.getString(5));
                    contactMap.put("LandLine", rs.getString(6));
                    contactMap.put("Email", rs.getString(7));
                    contactMap.put("type", rs.getString(8));
                    contactMap.put("campName", getCampDetails(con, rs.getString(9), rs.getString(10)));
                    contactDetails.add(contactMap);
                    sno++;
                }
            }



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getContactUsDetails", "ContactUsDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ContactUsDAO", "getContactUsDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getContactUsDetails", "ContactUsDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ContactUsDAO", "getContactUsDetails");
        } finally {
            DBConnection.closeConnection(con);
           // DBConnection.closeStatement(st);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return contactDetails;
    }

    public String checkExistingContact(DataSource ds, String contactUsType, String districtId, String campId) throws SADAREMDBException, SQLException {
        String contactDetails = "";
        StringBuilder query = new StringBuilder();
        Connection con = null;
       // Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
          //  st = con.createStatement();
            if (contactUsType.equalsIgnoreCase("Campincharge")) {
                query.append("select name from Contactus where status='Active' and  Type=? and DistrictId=? and campId=?");

            } else {
                query.append("select name from Contactus where status='Active' and  Type=? and DistrictId=?");
            }

            pstmt=con.prepareStatement(query.toString());
   
            if (contactUsType.equalsIgnoreCase("Campincharge")) {
            	pstmt.setString(1,contactUsType+"" );
            	pstmt.setString(2,districtId );
            	pstmt.setString(3,campId );
            }else
            {
            	pstmt.setString(1,contactUsType );
            	pstmt.setString(2,districtId );
            }
            
            rs = pstmt.executeQuery();
            
            if (rs != null) {

                while (rs.next()) {
                    if (contactUsType.equalsIgnoreCase("Campincharge")) {
                        contactDetails = "Already Camp Incharge Contact Existing in this Camp";
                    } else {
                        contactDetails = "Already DPM Contact Existing in this District";
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkExistingContact", "ContactUsDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ContactUsDAO", "checkExistingContact");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkExistingContact", "ContactUsDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ContactUsDAO", "checkExistingContact");
        } finally {
            DBConnection.closeConnection(con);
//        /    DBConnection.closeStatement(st);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return contactDetails;
    }

    public ContactUsDTO existingContactDetails(DataSource ds, String contactUsType, String districtId, String campId) throws SADAREMDBException, SQLException {
        ContactUsDTO contactUsDTO = new ContactUsDTO();

        StringBuilder query = new StringBuilder();
        Connection con = null;
      //  Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
        //    st = con.createStatement();
            if (contactUsType.equalsIgnoreCase("Campincharge")) {

                if (!campId.equals("0")) {
                    query.append("SELECT rowid,DistrictId,campId,District,Name,Designation,Contactno,Email,Type from Contactus where status='Active' and  Type=? and DistrictId=? and campId=?");
                }
            } else {

                query.append("select rowid,DistrictId,campId,District,Name,Designation,Contactno,Email,Type from Contactus where status='Active' and  Type=? and DistrictId=?");
            }
            if (query != null && query.length() > 0) {
                contactUsDTO.setStatus("true");

                pstmt=con.prepareStatement(query.toString());
                
                if (contactUsType.equalsIgnoreCase("Campincharge")) {
                	   if (!campId.equals("0")) {
                	pstmt.setString(1,contactUsType+"" );
                	pstmt.setString(2,districtId );
                	pstmt.setString(3,campId );
                	   }
                }else
                {
                	pstmt.setString(1,contactUsType );
                	pstmt.setString(2,districtId );
                }
                
                rs = pstmt.executeQuery();
            }

            if (rs != null) {

                while (rs.next()) {
                    contactUsDTO.setRowId(rs.getString(1));
                    contactUsDTO.setDistrictId(rs.getString(2));
                    contactUsDTO.setCampId(rs.getString(3));
                    contactUsDTO.setDistrictName(rs.getString(4));
                    contactUsDTO.setEmployeeName(rs.getString(5));
                    contactUsDTO.setDesignation(rs.getString(6));
                    contactUsDTO.setEmpContact(rs.getString(7));
                    contactUsDTO.setEmail(rs.getString(8));
                    contactUsDTO.setContactUsType(rs.getString(9));

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "existingContactDetails", "ContactUsDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ContactUsDAO", "existingContactDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "existingContactDetails", "ContactUsDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ContactUsDAO", "existingContactDetails");
        } finally {
            DBConnection.closeConnection(con);
           // DBConnection.closeStatement(st);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return contactUsDTO;
    }

    public String getCampDetails(Connection con, String district_id, String camp_id) throws SADAREMDBException, SQLException {
        String campDetails = "-";
        ResultSet rs1 = null;
    //    Statement st = null;
        PreparedStatement pstmt =null ;
        String sql = null;

        try {
        		//System.out.println("District : "+district_id+" camp_id : "+camp_id);
         //   st = con.createStatement();
            sql = "select camp_id,name,address,venueName from dbo.tblCamp_Details where district_id=? and camp_id=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, district_id);
            pstmt.setString(2, camp_id);

            rs1 = pstmt.executeQuery();
            if (rs1 != null) {
                while (rs1.next()) 
                {
                    campDetails = rs1.getString(2) + "(" + rs1.getString(3) + "," + rs1.getString(4) + ")";

                }
            }


        }
        catch (Exception sqlEx) 
        {
            sqlEx.printStackTrace();
        }
        finally
        {
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs1);
        }
        return campDetails;
    }
}
