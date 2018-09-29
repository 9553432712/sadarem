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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CryptoUtil;
import org.bf.disability.dto.DoctorSignDTO;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 484898
 */
public class DoctorSignDAO {

    /**
     * This method is for getting the all the details of PWD's when page loads for signing
     * @param ds
     * @param doctorSignDTO
     * @return ArrayList
     * @throws SADAREMDBException
     */
    public ArrayList getPWDDetails(DataSource ds, DoctorSignDTO doctorSignDTO) throws SADAREMDBException, SQLException {
        ArrayList pwdDetails = new ArrayList();
        Map<String, String> details = null;
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();

            cstmt = con.prepareCall("{Call Sp_Doctorlogin_display(?,?)}");
            cstmt.setString(1, doctorSignDTO.getDistrictCode());

            cstmt.setString(2, doctorSignDTO.getDoctorRegNumber());
            //cstmt.setString(2, "44699");
            rs = cstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    details = new HashMap<String, String>();
                    details.put("sadaremId", rs.getString(1));
                    details.put("personName", rs.getString(2));
                    details.put("disability", rs.getString(3));
                    details.put("percentage", rs.getString(4).substring(0, rs.getString(4).toString().length() - 2));
                    details.put("assessedDate", rs.getString(5));
                    details.put("doctorRegNo", rs.getString(6));
                    details.put("encString", rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getString(4).substring(0, rs.getString(4).toString().length() - 2) + "," + rs.getString(5) + "," + rs.getString(6));
                    details.put("certificate", "View");
                    details.put("idCard", rs.getString(7));
                    details.put("railwayCertificate",rs.getString(8));
                    //details.put("railwayCertificate", "View");
                    details.put("rtcCertificate", checkCertificate(ds, rs.getString(1), "rtcCertificate"));
                    details.put("percentageCalculation", "View");
                    details.put("doctorDetails", "0");
                    pwdDetails.add(details);
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            try {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPWDDetails", "DoctorSignDAO", "DataBase");
               throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorSignDAO", "getPWDDetails");
            } catch (org.bf.disability.Exception.SADAREMDBException ex) {
                Logger.getLogger(DoctorSignDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception sqlEx) {
             sqlEx.printStackTrace();
            try {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPWDDetails", "DoctorSignDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorSignDAO", "getPWDDetails");
            } catch (org.bf.disability.Exception.SADAREMDBException ex) {
                Logger.getLogger(DoctorSignDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } finally {
            try {
                DBConnection.closeResultSet(rs);
                DBConnection.closeStatement(cstmt);
                DBConnection.closeConnection(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pwdDetails;
    }

    /**
     * This method is for signing the selected Records
     * @param ds
     * @param doctorSignDTO
     * @return int
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public int signData(DataSource ds, DoctorSignDTO doctorSignDTO) throws SADAREMDBException, SQLException {
        int status = 0;
        Connection con = null;
      //  Statement st = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        String query = null;
        CryptoUtil cryptoUtil = null;
        try {
            con = DBConnection.getConnection();
            //st = con.createStatement();

            
            
            
            
            if (doctorSignDTO.getDoSign() != null && doctorSignDTO.getDoSign().length > 0) {
                cryptoUtil = new CryptoUtil();
                
                query = "INSERT INTO [dbo].[Doctor_Signed_Data] ([SadaremId],[PwdName],[Disability_Type],[Percentage],"
                        + "[AssessedDate],[DoctorNo],[EncryptedString],[LoginId],[CreatedDate],[SystemIp])"
                        + " VALUES(?,?,?,?,convert(datetime, ?,20)"
                        + ",?,?,?,getDate() ,?)";
                
                pstmt = con.prepareStatement(query);
                
                for (String str : doctorSignDTO.getDoSign()) {
                	
                	pstmt.setString(1, str.split(",")[0]);
                	pstmt.setString(2, str.split(",")[1]);
                	pstmt.setString(3, str.split(",")[2]);
                	pstmt.setString(4, str.split(",")[3]);
                	pstmt.setString(5,  str.split(",")[4] );
                	pstmt.setString(6, str.split(",")[5]);
                	pstmt.setString(7, cryptoUtil.encrypt(str.split(",")[5], str));
                	pstmt.setString(8,  doctorSignDTO.getDoctorRegNumber());
                	pstmt.setString(9, doctorSignDTO.getSystemIp());
                     
                    pstmt.addBatch();
                }
                int[] a = pstmt.executeBatch();

                if (a.length > 0) {
                    status = 1;
                }

            }

        } catch (SQLException sqlEx) {
            try {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "signData", "DoctorSignDAO", "DataBase");
               con.rollback();
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorSignDAO", "signData");
            } catch (org.bf.disability.Exception.SADAREMDBException ex) {
                Logger.getLogger(DoctorSignDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception sqlEx) {
            try {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "signData", "DoctorSignDAO", "Code");
               con.rollback();
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorSignDAO", "signData");
            } catch (org.bf.disability.Exception.SADAREMDBException ex) {
                Logger.getLogger(DoctorSignDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } finally {
            try {
                DBConnection.closeResultSet(rs);
                //DBConnection.closeStatement(st); 
                DBConnection.closeConnection(con); 
                DBConnection.closeStatement(pstmt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    /**
     *
     * @param ds DataSource
     * @param PersonCode
     * @return PersonCode valid personcode or not
     */
    public String checkCertificate(DataSource ds, String PersonCode, String flag) throws SADAREMDBException, SQLException {
        Connection con = null;
      //  Statement stmt = null; 
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        String query;
        int exist = 0;
        String certificate = "-";
        int totalDisability = 0;
        String disabilityId = "";
        String personStatus = null;

        try {
            con = DBConnection.getConnection();
        //    stmt = con.createStatement();
            if (flag.equalsIgnoreCase("certificate")) {  
            	query = "select Person_Status from tblPerson_Personal_Details  with (nolock) where Person_Code=? and Status='Active';";
            	pstmt = con.prepareStatement(query);
            	pstmt.setString(1, PersonCode); 
            	
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    personStatus = rs.getString(1);
                }
                if (personStatus != null) {
                    if (personStatus.equalsIgnoreCase("Eligible")) {
                        query = "select count(Person_Code) from tblPerson_Disability_TotalValue_Details where Person_Code=? and Status='Active';";
                        pstmt = con.prepareStatement(query);
                    	pstmt.setString(1, PersonCode); 
                        rs = pstmt.executeQuery();
                        while (rs.next()) {
                            exist = rs.getInt(1);
                        }
                    } else {
                        query = "select count(Person_Code) from tblRejectPerson_Details where Person_Code=? and Status='Active';";
                        pstmt = con.prepareStatement(query);
                    	pstmt.setString(1, PersonCode); 
                        rs = pstmt.executeQuery();
                        while (rs.next()) {
                            exist = rs.getInt(1);
                        }

                    }
                }
                if (exist > 0) {
                    certificate = "View";
                }
            } else if (flag.equalsIgnoreCase("idcard")) {
                query = "select TotalDisability from tblPerson_Disability_TotalValue_Details where Person_Code=? and Status='Active';";
                
                pstmt = con.prepareStatement(query);
            	pstmt.setString(1, PersonCode); 
                rs = pstmt.executeQuery();
                
              
                while (rs.next()) {
                    totalDisability = rs.getInt(1);
                }
                if (totalDisability > 40) {
                    certificate = "View";
                }
            } else if (flag.equalsIgnoreCase("railwaycertificate")) {
            	query = "select count(*) from RailwaypassDoctor_Details where person_code=? and status='Active';";
              
                
                pstmt = con.prepareStatement(query);
            	pstmt.setString(1, PersonCode); 
                rs = pstmt.executeQuery();
                
                
                while (rs.next()) {
                    if (rs.getInt(1) > 0) {
                        query = "select t.TotalDisability,d.disability_id from tblPerson_Disability_TotalValue_Details t,tblPerson_Disability_Details d where t.Person_Code=? and t.Status='Active' and"
                                + "  t.Person_Code=d.Person_Code and d.status='Active';";
                        
                        
                        pstmt = con.prepareStatement(query);
                    	pstmt.setString(1, PersonCode); 
                        rs = pstmt.executeQuery();
                        
                      //  rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            totalDisability = rs.getInt(1);
                            disabilityId = rs.getString(2);
                        }
                        if ((disabilityId.equals("2") && totalDisability == 100) || (disabilityId.equals("1") && totalDisability >= 40)
                                || (disabilityId.equals("3") && totalDisability >= 70)
                                || (disabilityId.equals("4") && totalDisability >= 50) || (disabilityId.equals("6") && totalDisability >= 40)) {
                            certificate = "View";
                        }
                    }
                }
            } else if (flag.equalsIgnoreCase("rtcCertificate")) {
                query = "select count(*) from Apsrtccertificatedetails where personcode=?  ;";
                
                
                pstmt = con.prepareStatement(query);
            	pstmt.setString(1, PersonCode); 
                rs = pstmt.executeQuery();
                
                
               // rs = stmt.executeQuery(query);
                while (rs.next()) {
                    exist = rs.getInt(1);
                }
                if (exist > 0) {
                    certificate = "View";
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            try {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkCertificate", "DoctorSignDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorSignDAO", "checkCertificate");
            } catch (org.bf.disability.Exception.SADAREMDBException ex) {
                Logger.getLogger(DoctorSignDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            try {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkCertificate", "DoctorSignDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorSignDAO", "checkCertificate");
            } catch (org.bf.disability.Exception.SADAREMDBException ex) {
                Logger.getLogger(DoctorSignDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
//                DBConnection.closeStatement(stmt); 
                DBConnection.closeStatement(pstmt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return certificate;
    }
}
