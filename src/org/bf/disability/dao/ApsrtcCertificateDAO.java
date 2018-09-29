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
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.sql.DataSource;

import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.ApsrtccertificateDTO;

import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 747577
 */
public class ApsrtcCertificateDAO
{

    public int storeCertificateDetails(DataSource ds, ApsrtccertificateDTO apsrtccertificateDTO) throws SADAREMDBException, SQLException {
        int status = 0;
        String sql = null;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Integer applicationNumber = Integer.parseInt(apsrtccertificateDTO.getDistrictId()+ "00001") ;
        int count = 0;
        ArrayList applicationNosList = new ArrayList();
        

		  PreparedStatement prStmt = null;
        
        try 
        {
        	ArrayList paramList = new ArrayList();
        	ArrayList tempList 	= new ArrayList();
        	
        	
        	
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(apsrtccertificateDTO.getReceiptDate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            
            con = DBConnection.getConnection();
            st = con.createStatement();
         
            /*Get Count Started*/
            paramList = new ArrayList();            
        	tempList 	= new ArrayList();            
        	tempList.add("S");
        	tempList.add(apsrtccertificateDTO.getSadaremId()); 
        	paramList.add(tempList);
        	
        	count = Integer.parseInt(DataAccess.getReturnResultByPstmt("select count(*) from Apsrtccertificatedetails where personcode=?", paramList));
        	
        	/*Get Count end*/

 
            String query1 = "select applicationno from Apsrtccertificatedetails where personcode=?";
             
  		  prStmt = con.prepareStatement(query1); 
  		  prStmt.setString(1, apsrtccertificateDTO.getDistrictId().trim());  
            rs = prStmt.executeQuery(query1);
            if (rs != null && rs.next()) 
            {
                applicationNosList.add(rs.getString(1));
            }
            
            if (applicationNosList.size() > 0) 
            {
                Collections.addAll(applicationNosList);
                applicationNumber = Integer.parseInt(Collections.max(applicationNosList).toString()) + 1;
            }
            
            
            
            if (count > 0) 
            {
                sql = "update Apsrtccertificatedetails set Idno='" + apsrtccertificateDTO.getIdNo() + "',"
                        + "Typeofpass='" + apsrtccertificateDTO.getTypeOfPass() + "',Proofenclosed='" + apsrtccertificateDTO.getProofEnclosed() + "',Passtypeeligibility='" + apsrtccertificateDTO.getPassTypeEligibility() + "',"
                        + "Escortforphc='" + apsrtccertificateDTO.getEscortForPhc() + "',Mrno='" + apsrtccertificateDTO.getAckReceiptNo() + "',ReceiptDate='" + fromdate + "',Busspassticketno='" + apsrtccertificateDTO.getBusPassTicketNo() + "',Passammount='" + apsrtccertificateDTO.getPassAmmountRs() + "',"
                        + "Paassissueof='" + apsrtccertificateDTO.getPassIssuedOf() + "',Loginid='" + apsrtccertificateDTO.getLoginId() + "',Createddate=getDate(),Systemip='" + InetAddress.getLocalHost().getHostAddress() + "'";
            } 
            else
            {

                sql = "INSERT INTO Apsrtccertificatedetails(Personcode,Applicationno,Idno,Typeofpass,Proofenclosed"
                        + ",Passtypeeligibility,Escortforphc,Mrno,ReceiptDate,Busspassticketno,Passammount,Paassissueof,Loginid,Createddate,Systemip)"
                        + "values('" + apsrtccertificateDTO.getSadaremId() + "','" + applicationNumber + "','" + apsrtccertificateDTO.getIdNo() + "',"
                        + "'" + apsrtccertificateDTO.getTypeOfPass() + "','" + apsrtccertificateDTO.getProofEnclosed() + "','" + apsrtccertificateDTO.getPassTypeEligibility() + "',"
                        + "'" + apsrtccertificateDTO.getEscortForPhc() + "',"
                        + "'" + apsrtccertificateDTO.getAckReceiptNo() + "','" + fromdate + "','" + apsrtccertificateDTO.getBusPassTicketNo() + "','" + apsrtccertificateDTO.getPassAmmountRs() + "',"
                        + "'" + apsrtccertificateDTO.getPassIssuedOf() + "',"
                        + "'" + apsrtccertificateDTO.getLoginId() + "',getDate(),'" + InetAddress.getLocalHost().getHostAddress() + "')";
            }
            status = st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(st);
            DBConnection.closeResultSet(rs);
        }
        return status;
    }

    public int updateCertificateDetails(DataSource ds, ApsrtccertificateDTO apsrtccertificateDTO) throws SADAREMDBException, SQLException {
        int status = 0;
        String sql = null;
        Connection con = null;
        Statement st = null;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            sql = "update Apsrtccertificatedetails set CertificateGeneratedStatus='Yes' where personcode='" + apsrtccertificateDTO.getSadaremId() + "'";
            status = st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(st);
        }
        return status;
    }

    public ApsrtccertificateDTO getpersonDisabilityDetails(DataSource ds, ApsrtccertificateDTO apsrtccertificateDTO) throws SADAREMDBException, SQLException
    {
        String sql = null;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try
        {
            con = DBConnection.getConnection();
            st = con.createStatement();
            sql = "SELECT    'TYPEOFDISABILITY' =   "
                    + " CASE  WHEN PD.DISABILITY_ID = 1 THEN 'Locomotor/OH'  "
                    + " WHEN PD.DISABILITY_ID = 2 THEN 'Visual Impairment'  "
                    + " WHEN PD.DISABILITY_ID = 3 THEN 'Hearing Impairment'  "
                    + " WHEN PD.DISABILITY_ID = 4 THEN 'Mental Retardation'  "
                    + " WHEN PD.DISABILITY_ID = 5 THEN 'Mental Illness'  "
                    + " WHEN PD.DISABILITY_ID = 6 THEN 'Multiple Disability'  "
                    + " ELSE 'Not Entered'  "
                    + " END FROM DBO.TBLPERSON_DISABILITY_DETAILS PD where  person_code = '" + apsrtccertificateDTO.getSadaremId() + "'";
            
            
            
            rs = st.executeQuery(sql);
            if (rs != null) 
            {
                while (rs.next()) 
                {
                    apsrtccertificateDTO.setTypeOfDisability(rs.getString(1));
                }
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(st);
            DBConnection.closeResultSet(rs);
        }
        
        return apsrtccertificateDTO;
    }

    public ApsrtccertificateDTO getDisabilitypercentage(DataSource ds, ApsrtccertificateDTO apsrtccertificateDTO) throws SADAREMDBException, SQLException {
        String sql = null;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            sql = "select totaldisability from DBO.TBLPERSON_DISABILITY_TOTALVALUE_DETAILS where person_code='" + apsrtccertificateDTO.getSadaremId() + "'";
            rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    apsrtccertificateDTO.setDegreeOfDisability(rs.getDouble(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(st);
            DBConnection.closeResultSet(rs);
        }
        return apsrtccertificateDTO;
    }

    public ApsrtccertificateDTO getApplicantDetails(DataSource ds, ApsrtccertificateDTO apsrtccertificateDTO) throws SADAREMDBException, SQLException {
        String sql = null;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            sql = "select distinct Idno,Proofenclosed,Passtypeeligibility"
                    + ",Escortforphc,typeofpass,Mrno,convert(varchar,ReceiptDate,103) as date,Busspassticketno,convert(varchar(30),Passammount,1),Paassissueof from Apsrtccertificatedetails "
                    + "where personcode='" + apsrtccertificateDTO.getSadaremId() + "'";
            rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    apsrtccertificateDTO.setIdNo(rs.getString(1));
                    apsrtccertificateDTO.setProofEnclosed(rs.getString(2));
                    apsrtccertificateDTO.setPassTypeEligibility(rs.getString(3));
                    if (rs.getString(4).equals("null")) {
                        apsrtccertificateDTO.setEscortForPhc("N/A");
                    } else {
                        apsrtccertificateDTO.setEscortForPhc(rs.getString(4));
                    }
                    apsrtccertificateDTO.setTypeOfPass(rs.getString(5));
                    apsrtccertificateDTO.setAckReceiptNo(rs.getString(6));
                    apsrtccertificateDTO.setReceiptDate(rs.getString(7));
                    apsrtccertificateDTO.setBusPassTicketNo(rs.getString(8));
                    apsrtccertificateDTO.setPassAmmountRs(rs.getString(9));
                    apsrtccertificateDTO.setPassIssuedOf(rs.getString(10));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(st);
            DBConnection.closeResultSet(rs);
        }
        return apsrtccertificateDTO;
    }

    public ApsrtccertificateDTO getCertificateDetails(DataSource ds, ApsrtccertificateDTO apsrtccertificateDTO) throws SADAREMDBException, SQLException {
        String sql = null;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            sql = "SELECT  distinct aps.applicationno,aps.idno,aps.Proofenclosed,aps.Passtypeeligibility,aps.Escortforphc,"
                    + "aps.Mrno,convert(varchar,aps.ReceiptDate,103) as date,aps.Busspassticketno,convert(varchar(30),aps.Passammount,1),aps.Paassissueof,aps.typeofpass,  \n"
                    + "P.SURNAME + SPACE(2) + P.FIRST_NAME AS PERSONNAME ,   \n"
                    + "P.RELATION_NAME AS RELATIONNAME ,\n"
                    + "convert(varchar,date_of_birth,103) as date, \n"
                    + "'GENDER' =   \n"
                    + "CASE  \n"
                    + "WHEN Gender = 1 THEN 'Male'  \n"
                    + "WHEN Gender = 2 THEN 'Female'  \n"
                    + "ELSE 'Not Entered'  \n"
                    + "END,   \n"
                    + "house_number +','+habitation_name+','+e.village_name+','+d.mandal_name+','+s.district_name,\n"
                    + "'PHC' ,\n"
                    + "\n"
                    + "\n"
                    + "'TYPEOFDISABILITY' =   \n"
                    + "CASE  \n"
                    + "WHEN PD.DISABILITY_ID = 1 THEN 'Locomotor/OH'  \n"
                    + "WHEN PD.DISABILITY_ID = 2 THEN 'Visual Impairment'  \n"
                    + "WHEN PD.DISABILITY_ID = 3 THEN 'Hearing Impairment'  \n"
                    + "WHEN PD.DISABILITY_ID = 4 THEN 'Mental Retardation'  \n"
                    + "WHEN PD.DISABILITY_ID = 5 THEN 'Mental Illness'  \n"
                    + "WHEN PD.DISABILITY_ID = 6 THEN 'Multiple Disability'  \n"
                    + "ELSE 'Not Entered'  \n"
                    + "END,    \n"
                    + "PT.totaldisability percentage\n"
                    + "  \n"
                    + "FROM   \n"
                    + "DBO.TBLPERSON_PERSONAL_DETAILS P  with (nolock) ,   \n"
                    + "DBO.TBLPERSON_DISABILITY_DETAILS PD ,   \n"
                    + "DBO.TBLPERSON_DISABILITY_TOTALVALUE_DETAILS PT ,"
                    + "DBO.APSRTCCERTIFICATEDETAILS  APS,  \n"
                    + "tbldistrict_details s,  \n"
                    + "tblmandal_details d,  \n"
                    + "tblvillage_details e , tblhabitation_details h  \n"
                    + "WHERE   \n"
                    + "PD.PERSON_CODE      = P.PERSON_CODE  AND   \n"
                    + "PT.PERSON_CODE      = P.PERSON_CODE  AND  \n"
                    + "PD.PERSON_CODE      = PT.PERSON_CODE AND "
                    + "APS.PERSONCODE	   =P.PERSON_CODE  AND    \n"
                    + "P.STATUS= 'ACTIVE'               AND  \n"
                    + "PD.STATUS= 'ACTIVE'               AND  \n"
                    + "PT.STATUS= 'ACTIVE'               AND   \n"
                    + "VIEW_EDIT_MODE = 'View'          AND  \n"
                    + "p.districtid = s.district_id and  \n"
                    + "p.districtid = d.district_id and  \n"
                    + "p.mandalid = d.mandal_id and  \n"
                    + "p.districtid = e.district_id and  \n"
                    + "p.mandalid = e.mandal_id and  \n"
                    + "p.villageid = e.village_id and   \n"
                    + " p.habcode = h.habitation_code   and   \n"
                    + " p.person_code = '" + apsrtccertificateDTO.getSadaremId() + "'";
            rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    apsrtccertificateDTO.setApplicationNo(rs.getString(1));
                    apsrtccertificateDTO.setIdNo(rs.getString(2));
                    apsrtccertificateDTO.setProofEnclosed(rs.getString(3));
                    apsrtccertificateDTO.setPassTypeEligibility(rs.getString(4));
                    if (rs.getString(5).equals("null")) {
                        apsrtccertificateDTO.setEscortForPhc("N/A");
                    } else {
                        apsrtccertificateDTO.setEscortForPhc(rs.getString(5));
                    }
                    apsrtccertificateDTO.setAckReceiptNo(rs.getString(6));
                    apsrtccertificateDTO.setReceiptDate(rs.getString(7));
                    apsrtccertificateDTO.setBusPassTicketNo(rs.getString(8));
                    apsrtccertificateDTO.setPassAmmountRs(rs.getString(9));
                    apsrtccertificateDTO.setPassIssuedOf(rs.getString(10));
                    apsrtccertificateDTO.setTypeOfPass(rs.getString(11));
                    apsrtccertificateDTO.setName(rs.getString(12));
                    apsrtccertificateDTO.setFatherName(rs.getString(13));
                    apsrtccertificateDTO.setDob(rs.getString(14));
                    apsrtccertificateDTO.setGender(rs.getString(15));
                    apsrtccertificateDTO.setAddress(rs.getString(16));
                    apsrtccertificateDTO.setPhcType(rs.getString(17));
                    apsrtccertificateDTO.setTypeOfDisability(rs.getString(18));
                    apsrtccertificateDTO.setDegreeOfDisability(Double.parseDouble(rs.getString(19)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(st);
            DBConnection.closeResultSet(rs);
        }
        return apsrtccertificateDTO;
    }

    public String getDistrictName(DataSource ds, String distId) throws SADAREMDBException, SQLException {
        String sql = null;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String disName = null;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            sql = "select district_name from tblDistrict_Details where district_id='" + distId + "'";
            rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    disName = rs.getString(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(st);
            DBConnection.closeResultSet(rs);
        }
        return disName;
    }
}
