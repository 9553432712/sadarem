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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.AadharCardMappingDTO;
import org.bf.disability.form.AadharCardMappingForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 693461
 */
public class AadharCardMappingDAO {

    public int getValidSADAREMIDDetails(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException, SQLException {
        int validSADAREMID = 0;
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            query = "select count(*) from tblperson_personal_Details  with (nolock) where status ='Active' and person_code =?";
            pstmt = con.prepareStatement(query); 
            
            pstmt.setString(1,aadharCardMappingForm.getSadaremId() );
            
            //System.out.println(query+"--"+rs.getInt(1));
            
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    validSADAREMID = rs.getInt(1);
                }
            }

        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getValidSADAREMIDDetails", "AadharCardMappingDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AadharCardMappingDAO", "getValidSADAREMIDDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getValidSADAREMIDDetails", "AadharCardMappingDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AadharCardMappingDAO", "getValidSADAREMIDDetails");
        } finally {
            DBConnection.closeConnection(con);



        }
        return validSADAREMID;


    }

    public String invalidSADAREMIDMsg(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException, SQLException {

        String invalidSADAREMIDMsg = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            query = "select reasonforpersonstatus from tblperson_personal_Details  with (nolock) "
                    + " where status ='Inactive' and person_code =?";
            pstmt = con.prepareStatement(query);
            
            pstmt.setString(1, aadharCardMappingForm.getSadaremId());
            
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    invalidSADAREMIDMsg = rs.getString(1);
                }
            }

        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "invalidSADAREMIDMsg", "AadharCardMappingDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AadharCardMappingDAO", "invalidSADAREMIDMsg");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "invalidSADAREMIDMsg", "AadharCardMappingDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AadharCardMappingDAO", "invalidSADAREMIDMsg");
        } finally {
            DBConnection.closeConnection(con);
        }
        return invalidSADAREMIDMsg;
    }

    public ArrayList getSADAREMIDValidDetails(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException, SQLException {

        ArrayList SADAREMIDValidDetails = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        Map map = null;


        try {
            con = DBConnection.getConnection();
//            query = "select surname+space(2)+first_name as name,relation_name,age_years,"
//                    + " case when gender='1' then 'Male' "
//                    + " when gender='2' then 'Female'"
//                    + " else 'Not Entered' end as Gender,RationCard_number,pensioncard_no,person_code"
//                    + " from tblperson_personal_Details where  person_code ='" + aadharCardMappingForm.getSadaremId() + "' and status ='Active'";
//            System.out.println("");

            query = "SELECT DISTINCT P.PENSIONCARD_NO AS PENSIONID, P.PERSON_CODE AS SADAREMCODE,P.SURNAME + SPACE(2) + P.FIRST_NAME AS PERSONNAME ,"
                    + "'GENDER' =  CASE WHEN Gender = 1 THEN 'Male' WHEN Gender = 2 THEN 'Female'  ELSE 'Not Entered'  END, "
                    + " p.age_years age,'EDUCATION' = CASE WHEN P.EDUCATION = 1 THEN 'Illiterate' WHEN P.EDUCATION = 2 THEN 'Below 10th' "
                    + " WHEN P.EDUCATION = 3 THEN '10th Class' WHEN P.EDUCATION = 4 THEN 'Intermediate' WHEN P.EDUCATION = 5 THEN 'Diploma/I.T.I'  "
                    + " WHEN P.EDUCATION = 6 THEN 'Graduate' WHEN P.EDUCATION = 7 THEN 'Postgraduate'   ELSE 'Not Entered' END,"
                    + " 'CASTE' = CASE WHEN P.CASTE = 1 THEN 'ST' WHEN P.CASTE = 2 THEN 'SC' WHEN P.CASTE = 3 THEN 'BC' WHEN P.CASTE = 4 THEN 'OC'  "
                    + " WHEN P.CASTE = 5 THEN 'Minority'   WHEN P.CASTE = 6 THEN 'NA' ELSE 'Not Entered' END,P.RELATION_NAME AS RELATIONNAME ,P.RATIONCARD_NUMBER,"
                    + " 'ASSESSEMENTSTATUS' = CASE WHEN p.CategoryID = 1 THEN 'Regular' WHEN p.CategoryID = 2 THEN 'Reassessed' WHEN p.CategoryID = 3 THEN 'Temporary'        "
                    + " ELSE 'Not Entered' END,house_number+','+habitation_name+','+e.village_name+', '+d.mandal_name+','+s.district_name  Address,"
                    + " phone_no  FROM DBO.TBLPERSON_PERSONAL_DETAILS P  with (nolock) ,tbldistrict_details s,tblmandal_details d,tblvillage_details e ,tblhabitation_details h "
                    + " WHERE P.STATUS= 'ACTIVE' AND p.districtid = s.district_id and p.districtid = d.district_id and  p.mandalid = d.mandal_id and "
                    + "  p.districtid = e.district_id and p.mandalid = e.mandal_id and p.villageid = e.village_id and p.habcode = h.habitation_code   and"
                    + "   p.person_code=?";
            
            pstmt = con.prepareStatement(query);
            
            pstmt.setString(1, aadharCardMappingForm.getSadaremId() );
            
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("pensionCardNo", rs.getString(1));
                    map.put("personCode", rs.getString(2));
                    map.put("name", rs.getString(3));
                    map.put("gender", rs.getString(4));
                    map.put("age", rs.getString(5));
                    map.put("education", rs.getString(6));
                    map.put("caste", rs.getString(7));
                    map.put("relationName", rs.getString(8));
                    map.put("rationCardNo", rs.getString(9));
                    map.put("assessmentstatus", rs.getString(10));
                    map.put("address", rs.getString(11));
                    map.put("phoneno", rs.getString(12));
                    SADAREMIDValidDetails.add(map);
                }
            }

        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSADAREMIDValidDetails", "AadharCardMappingDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AadharCardMappingDAO", "getSADAREMIDValidDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSADAREMIDValidDetails", "AadharCardMappingDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AadharCardMappingDAO", "getSADAREMIDValidDetails");
        } finally {
            DBConnection.closeConnection(con);
        }
        return SADAREMIDValidDetails;
    }

    public ArrayList getCivilSuppliesData(DataSource ds, String rationcard, String districtid) throws SADAREMDBException, SQLException {

        ArrayList SADAREMIDValidDetails = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        Map map = null;


        try {
            con = DBConnection.getConnection();
//            query = "select surname+space(2)+first_name as name,relation_name,age_years,"
//                    + " case when gender='1' then 'Male' "
//                    + " when gender='2' then 'Female'"
//                    + " else 'Not Entered' end as Gender,RationCard_number,pensioncard_no,person_code"
//                    + " from tblperson_personal_Details where  person_code ='" + aadharCardMappingForm.getSadaremId() + "' and status ='Active'";
//            System.out.println("");

            query = "select slno,membername,coalesce(relation,'-'),coalesce(age,'-'),aadharno from CivilSupply_Database..tblrationcard_details_" + districtid + " where HOUSEHOLDCARDNO=?";
            pstmt = con.prepareStatement(query);
       
            pstmt.setString(1, rationcard);
            
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("slNo", rs.getString(1));
                    map.put("memberName", rs.getString(2));

                    map.put("relationName", "-");
                    map.put("age", rs.getString(4));
                    map.put("aadharcardNo", rs.getString(5));
                    map.put("district", districtid);
                     map.put("dob", "");
                    SADAREMIDValidDetails.add(map);
                }
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSADAREMIDValidDetails", "AadharCardMappingDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AadharCardMappingDAO", "getSADAREMIDValidDetails");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSADAREMIDValidDetails", "AadharCardMappingDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AadharCardMappingDAO", "getSADAREMIDValidDetails");
        } finally {
            DBConnection.closeConnection(con);
        }
        return SADAREMIDValidDetails;
    }

    public AadharCardMappingDTO getAadharCardCount(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException, SQLException {

        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        AadharCardMappingDTO aadharCardCount = new AadharCardMappingDTO();

        try {
            con = DBConnection.getConnection();
            query = "select REPLACE(proof_id,'NA','') proof_id,proofdoc_type from tblperson_personal_Details  with (nolock)  where  "
                    + " person_code=?"
                    + " and status ='Active' and proof_id not in ('','null','NA')";
            pstmt = con.prepareStatement(query);
            
            pstmt.setString(1, aadharCardMappingForm.getSadaremId());
            
            rs = pstmt.executeQuery();

            if (rs != null && rs.next()) {
                aadharCardCount.setValidMsg(rs.getString(1));
                aadharCardCount.setPrrofDocType(rs.getString(2));
            }

        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSADAREMIDValidDetails", "AadharCardMappingDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AadharCardMappingDAO", "getSADAREMIDValidDetails");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSADAREMIDValidDetails", "AadharCardMappingDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AadharCardMappingDAO", "getSADAREMIDValidDetails");
        } finally {
            DBConnection.closeConnection(con);
        }

        return aadharCardCount;
    }

    public int updateAadharCardCount(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        int updateAadharCardCount = 0,updateAadharCardCount1 = 0,updateAadharCardCount2 = 0,updateAadharCardCount3 = 0;
        String query = null;
        PreparedStatement pstmt = null; 
        try 
        {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            query   = "update tblperson_personal_Details"
                    + " set proof_id =?,proofdoc_type='Adhaar Card' where person_code=?";

            pstmt = con.prepareStatement(query);
            
            pstmt.setString(1,aadharCardMappingForm.getAadharCardId() );
            pstmt.setString(2,aadharCardMappingForm.getAadharPersonCode() );
            
            updateAadharCardCount = pstmt.executeUpdate();
            
            if (updateAadharCardCount > 0)
            {
            	query   = "update tblperson_personal_Details_new"
                        + " set proof_id =?,proofdoc_type='Adhaar Card' where person_code=?";

                pstmt = con.prepareStatement(query);                
                pstmt.setString(1,aadharCardMappingForm.getAadharCardId() );
                pstmt.setString(2,aadharCardMappingForm.getAadharPersonCode() );                
                updateAadharCardCount1 = pstmt.executeUpdate();
                
                if (updateAadharCardCount1 > 0)
                {
                	query   = "update sadarem_without_proof_request_master"
                            + " set proof_id =?,proofdoc_type='Adhaar Card',updated_date=getDate() where sadarem_id=?";

                    pstmt = con.prepareStatement(query);                
                    pstmt.setString(1,aadharCardMappingForm.getAadharCardId() );
                    pstmt.setString(2,aadharCardMappingForm.getAadharPersonCode() );                
                    updateAadharCardCount2 = pstmt.executeUpdate();
                    
                    if (updateAadharCardCount2 >= 0)
                    {
                    	query = "insert into AadharUpdateDetails (person_code,createddate,loginid,systemip,status) values(?,getDate(),"
                                + "?,?,'Active')";
                        pstmt = con.prepareStatement(query);
                        
                        
                        pstmt.setString(1,  aadharCardMappingForm.getAadharPersonCode());
                        pstmt.setString(2,  aadharCardMappingForm.getLoginId());
                        pstmt.setString(3, InetAddress.getLocalHost().getHostAddress());                        
                        updateAadharCardCount3 = pstmt.executeUpdate();
                        if(updateAadharCardCount3>0)
                        {
                        	con.commit();
                        }
                        else
                        {
                        	con.rollback();
                        }
                    }
                    else
                    {
                    	con.rollback();
                    }
                }
                else
                {
                	con.rollback();
                }               
            }
            else
            {
            	con.rollback();
            }


        } 
        catch (SQLException sqlEx)
        {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateAadharCardCount", "AadharCardMappingDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AadharCardMappingDAO", "updateAadharCardCount");
        } 
        catch (Exception sqlEx) 
        {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateAadharCardCount", "AadharCardMappingDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AadharCardMappingDAO", "updateAadharCardCount");
        } 
        finally 
        {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            
        }
        return updateAadharCardCount;


    }

    public AadharCardMappingDTO particularAaadhartagedSADAREMIDDetails(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        int particularAaadhartagedSADAREMIDDetails = 0;
        String query = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        AadharCardMappingDTO aadharCardMappingDTO = null;

        try {
            con = DBConnection.getConnection();
            query = "select count(*),person_code from tblperson_personal_Details  with (nolock) where   proof_id =? group by person_code";
            pstmt = con.prepareStatement(query);
            
            
            pstmt.setString(1, aadharCardMappingForm.getAadharCardId());
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    aadharCardMappingDTO = new AadharCardMappingDTO();
                    aadharCardMappingDTO.setPersonCodeCount(rs.getInt(1));
                    aadharCardMappingDTO.setValidSADAREMIDMsg(rs.getString(2));
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "particularAaadhartagedSADAREMIDDetails", "AadharCardMappingDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AadharCardMappingDAO", "particularAaadhartagedSADAREMIDDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "particularAaadhartagedSADAREMIDDetails", "AadharCardMappingDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AadharCardMappingDAO", "particularAaadhartagedSADAREMIDDetails");
        } finally {
            DBConnection.closeConnection(con);
        }
        return aadharCardMappingDTO;
    }

    public String getRationCardNumber(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        String query = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sadaremId = null;

        try {
            con = DBConnection.getConnection();
            query = "select rationcard_number from dbo.tblPerson_Personal_Details  with (nolock)  where person_code=?";
            
            pstmt = con.prepareStatement(query);
            
            pstmt.setString(1,  aadharCardMappingForm.getSadaremId() );
            
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    sadaremId = rs.getString(1);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRationCardNumber", "AadharCardMappingDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AadharCardMappingDAO", "getRationCardNumber");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRationCardNumber", "AadharCardMappingDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AadharCardMappingDAO", "getRationCardNumber");
        } 
        finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return sadaremId;
    }

    public String AadharCardExist(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        String query = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sadaremId = null;

        try {
            con = DBConnection.getConnection();
            query = "select person_code from dbo.tblPerson_Personal_Details  with (nolock) where proof_id=? and proofdoc_type in ('Adhaar Card','Aadhaar Card','Aadhaar ID','Aadhar Card')"
                    + " and proof_id not in ('','null')";
            pstmt = con.prepareStatement(query);
            
            pstmt.setString(1,  aadharCardMappingForm.getAadharCardId());
            
            rs = pstmt.executeQuery();
            
         //   System.out.println(query+"---");
            
            if (rs != null) {
                while (rs.next()) {
                    sadaremId = rs.getString(1);
                    //System.out.println("---> "+sadaremId);
                }
            }

        }
        catch (SQLException sqlEx) 
        { 
        	sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "AadharCardExist", "AadharCardMappingDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AadharCardMappingDAO", "AadharCardExist");
        }
        catch (Exception sqlEx)
        {
        	sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "AadharCardExist", "AadharCardMappingDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AadharCardMappingDAO", "AadharCardExist");
        }
        finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return sadaremId;
    }
}
