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
import org.bf.disability.dto.MeesevaSearchDTO;
import org.bf.disability.form.MeesvaSearchForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 693461
 */
public class MeesvaSearchDAO {

    public String getSadaremIdDetails(DataSource ds, MeesvaSearchForm meesvaSearchForm) throws SADAREMDBException, SQLException {
        ArrayList campList = new ArrayList();
        Connection con = null;
        StringBuffer query = new StringBuffer();
        PreparedStatement pstmt = null;
        String statusFlag = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            query.append("select  distinct view_edit_mode from tblperson_personal_Details  with (nolock) where status='Active'  ");
            if (meesvaSearchForm.getSadaremId() != null && !meesvaSearchForm.getSadaremId().equals("")) {
                query.append(" and person_code=?");
            }
              if (meesvaSearchForm.getAadharCardNo() != null && !meesvaSearchForm.getAadharCardNo().equals("")) {
                query.append(" and proof_id=?");
            }
              if (meesvaSearchForm.getRationCardNo() != null && !meesvaSearchForm.getRationCardNo().equals("")) {
                query.append(" and rationCard_number=?");
            }
             if (meesvaSearchForm.getPensionId() != null && !meesvaSearchForm.getPensionId().equals("")) {
                query.append(" and pensionCard_no=? ");
            }
             if (meesvaSearchForm.getName() != null && !meesvaSearchForm.getName().equals("")) {
                query.append(" and (surname like '%?%' " +
                        " or first_name like '%?%')");

            }
             if (meesvaSearchForm.getDistrict_id() != null
                    && !meesvaSearchForm.getDistrict_id().equals("0")) {

                query.append(" and substring(habcode,1,2)=?");

            }
             if ( meesvaSearchForm.getMandal_id() != null && !meesvaSearchForm.getMandal_id().equals("0")) {

                query.append("  and substring(habcode,6,2)=? ");
            }
             if (meesvaSearchForm.getPanchayat_id() != null && !meesvaSearchForm.getPanchayat_id().equals("0")) {

                query.append("  and substring(habcode,13,2)=?");
            }
             if (meesvaSearchForm.getVillage_id() != null && !meesvaSearchForm.getVillage_id().equals("0")
                    ) {
                query.append("  and substring(habcode,8,3)=?");
            }
             if ( meesvaSearchForm.getHabitation_id() != null && !meesvaSearchForm.getHabitation_id().equals("0")) {
                query.append("  and substring(habcode,11,2)=?");
            }
//            else if (meesvaSearchForm.getCampId() != null && !meesvaSearchForm.getCampId().equals("")) {
//                query.append("select distinct view_edit_mode from tblperson_personal_Details a "
//                        + " join certificateuploaddetails b on a.person_code=b.person_code and a.categoryid=b.categoryid"
//                        + " join tbldistrict_Details c on a.district_id=c.district_id"
//                        + " join tblmandal_Details d on a.district_id=d.district_id and a.mandal_id=d.mandal_id"
//                        + " join tblPerson_Disability_Details dis on a.person_code=dis.person_code"
//                        + " join tblcamp_details camp on dis.hospital_name=camp.name and dis.hospital_address=camp.address"
//                        + " and dis.venuename=camp.venuename"
//                        + " where a.district_id='" + meesvaSearchForm.getDistrict_id() + "' "
//                        + " and camp.camp_id='" + meesvaSearchForm.getCampId() + "'");
//            };
            int u=1;
            pstmt = con.prepareStatement(query.toString()); 
         
            if (meesvaSearchForm.getSadaremId() != null && !meesvaSearchForm.getSadaremId().equals("")) {      
            pstmt.setString(u, meesvaSearchForm.getSadaremId());  u++;  } 
            
            if (meesvaSearchForm.getAadharCardNo() != null && !meesvaSearchForm.getAadharCardNo().equals("")) {
            	pstmt.setString(u, meesvaSearchForm.getAadharCardNo());  u++; 
               
            }
              if (meesvaSearchForm.getRationCardNo() != null && !meesvaSearchForm.getRationCardNo().equals("")) {
            	  	pstmt.setString(u, meesvaSearchForm.getRationCardNo());  u++; 
              
            }
             if (meesvaSearchForm.getPensionId() != null && !meesvaSearchForm.getPensionId().equals("")) { 
            	 pstmt.setString(u, meesvaSearchForm.getPensionId());  u++; 
               
            }
             if (meesvaSearchForm.getName() != null && !meesvaSearchForm.getName().equals("")) {
            	 pstmt.setString(u, meesvaSearchForm.getName().trim());  u++; 
              	 pstmt.setString(u,  meesvaSearchForm.getName().trim());  u++;   
            }
             if (meesvaSearchForm.getDistrict_id() != null
                    && !meesvaSearchForm.getDistrict_id().equals("0")) {

            	 pstmt.setString(u, meesvaSearchForm.getDistrict_id());  u++; 
         

            }
             if ( meesvaSearchForm.getMandal_id() != null && !meesvaSearchForm.getMandal_id().equals("0")) {
            	 pstmt.setString(u,  meesvaSearchForm.getMandal_id());  u++; 

            }
             if (meesvaSearchForm.getPanchayat_id() != null && !meesvaSearchForm.getPanchayat_id().equals("0")) {
            	 pstmt.setString(u, meesvaSearchForm.getPanchayat_id());  u++; 

            }
             if (meesvaSearchForm.getVillage_id() != null && !meesvaSearchForm.getVillage_id().equals("0")
                    ) {
             	 pstmt.setString(u,  meesvaSearchForm.getVillage_id());  u++; 
              
            }
             if ( meesvaSearchForm.getHabitation_id() != null && !meesvaSearchForm.getHabitation_id().equals("0")) {
            	 pstmt.setString(u,  meesvaSearchForm.getHabitation_id());  u++; 
             
            }
   
            
            
            
            
            
            
            
            
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    statusFlag = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSadaremIdDetails", "MeesvaSearchDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MeesvaSearchDAO", "getSadaremIdDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSadaremIdDetails", "MeesvaSearchDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MeesvaSearchDAO", "getSadaremIdDetails");
        } finally {
            DBConnection.closeConnection(con);
        }

        return statusFlag;

    }

    public ArrayList getCertificateValidDetails(DataSource ds, MeesvaSearchForm meesvaSearchForm) throws SADAREMDBException, SQLException {
        ArrayList certificateValidDetails = new ArrayList();
        Connection con = null;
        StringBuffer query = new StringBuffer();
        PreparedStatement pstmt = null;
        PreparedStatement campPstmt = null;
        ResultSet rs = null;
        ResultSet campRs = null;
        Map map = null;
        String campQuery = null;
        String statusFlag = null;
        String tableName = null;
        String tableStatus = null;
        String q = "";
        String q1 = "";
        try {
            con = DBConnection.getConnection();
            
            if (meesvaSearchForm.getCampId() != null && !meesvaSearchForm.getCampId().equals("")) {
                query.append("select distinct a.person_code,a.surname+space(2)+a.first_name as Name ,"
                        + " a.age_years,a.relation_name, CASE WHEN a.GENDER = '1' THEN 'Male'"
                        + " WHEN a.GENDER = '2' THEN 'FEMALE' ELSE 'NE' END AS GENDER, "
                        + " c.district_name,d.mandal_name,a.district_id,a.mandal_id,a.village_id,"
                        + " a.habcode,c.district_name,d.mandal_name,v.village_name,coalesce(phone_no,'-')as phone,t.totaldisability totaldisability"
                        + " from tblperson_personal_Details a  with (nolock) "
                        + " join certificateuploaddetails b on a.person_code=b.person_code and a.categoryid=b.categoryid"
                        + " join tbldistrict_Details c on a.district_id=c.district_id"
                        + " join tblmandal_Details d on a.district_id=d.district_id and a.mandal_id=d.mandal_id"
                        + " join tblPerson_Disability_Details dis on a.person_code=dis.person_code"
                        + " join tblPerson_Disability_TotalValue_Details t on a.person_code=t.person_code"
                        + " join tblcamp_details camp on dis.hospital_name=camp.name and dis.hospital_address=camp.address"
                        + " and dis.venuename=camp.venuename "
                        + " join tblvillage_Details v on (a.district_id=v.district_id and a.mandal_id=v.mandal_id and a.village_id=v.village_id) "
                        + " where  b.status='Active'  ~replaceStr and camp.camp_id='" + meesvaSearchForm.getCampId() + "' "
                        + " union "
                        + " select distinct a.person_code,a.surname+space(2)+a.first_name as Name ,"
                        + " a.age_years,a.relation_name, CASE WHEN a.GENDER = '1' THEN 'Male'"
                        + " WHEN a.GENDER = '2' THEN 'FEMALE' ELSE 'NE' END AS GENDER,"
                        + " c.district_name,d.mandal_name,a.district_id,a.mandal_id,a.village_id,"
                        + " a.habcode,c.district_name,d.mandal_name,v.village_name,coalesce(phone_no,'-')as phone,'0' totaldisability"
                        + " from tblperson_personal_Details a with (nolock) "
                        + " join certificateuploaddetails b on a.person_code=b.person_code and a.categoryid=b.categoryid"
                        + " join tbldistrict_Details c on a.district_id=c.district_id"
                        + " join tblmandal_Details d on a.district_id=d.district_id and a.mandal_id=d.mandal_id"
                        + " join tblRejectPerson_Details dis on a.person_code=dis.person_code"
                        + " join tblcamp_details camp on dis.hospital_name=camp.name and dis.hospital_address=camp.address"
                        + " and dis.venuename=camp.venuename "
                        + " join tblvillage_Details v on (a.district_id=v.district_id and a.mandal_id=v.mandal_id and a.village_id=v.village_id) "
                        + " where  b.status='Active'  ~replaceStr and camp.camp_id='" + meesvaSearchForm.getCampId() + "'");
            } else {

                query.append(" select distinct a.person_code,a.surname+space(2)+a.first_name as Name ,a.age_years,"
                        + " a.relation_name, CASE WHEN a.GENDER = '1' THEN 'Male' WHEN a.GENDER = '2' THEN 'FEMALE' ELSE 'NE'"
                        + " END AS GENDER,c.district_name,d.mandal_name,a.district_id,a.mandal_id,a.village_id,a.habcode,"
                        + " c.district_name,d.mandal_name,v.village_name,coalesce(phone_no,'-')as phone,t.totaldisability totaldisability"
                        + " from tblperson_personal_Details a  with (nolock) join tblPerson_Disability_TotalValue_Details t on a.person_code=t.person_code"
                        + " join certificateuploaddetails b on a.person_code=b.person_code"
                        + " and a.categoryid=b.categoryid join tbldistrict_Details c on (a.district_id=c.district_id) join"
                        + " tblmandal_Details d on (a.district_id=d.district_id"
                        + " and a.mandal_id=d.mandal_id) "
                        + " join tblvillage_Details v on (a.district_id=v.district_id and"
                        + " a.mandal_id=v.mandal_id and a.village_id=v.village_id) where b.status='Active'  ~replaceqry"
                        + " union"
                        + " select distinct a.person_code,a.surname+space(2)+a.first_name as Name ,a.age_years,"
                        + " a.relation_name, CASE WHEN a.GENDER = '1' THEN 'Male' WHEN a.GENDER = '2' THEN 'FEMALE' ELSE 'NE'"
                        + " END AS GENDER, c.district_name,d.mandal_name,a.district_id,a.mandal_id,a.village_id,a.habcode,"
                        + " c.district_name,d.mandal_name,v.village_name,coalesce(phone_no,'-')as phone,t.totaldisability totaldisability"
                        + " from tblperson_personal_Details a  with (nolock) join tblPerson_Disability_TotalValue_Details t on a.person_code=t.person_code"
                        + " join certificateuploaddetails b on a.person_code=b.person_code"
                        + " and a.categoryid=b.categoryid join tbldistrict_Details c on (a.district_id=c.district_id) join"
                        + " tblmandal_Details d on (a.district_id=d.district_id"
                        + " and a.mandal_id=d.mandal_id) "
                        + " join tblvillage_Details v on (a.district_id=v.district_id and"
                        + " a.mandal_id=v.mandal_id and a.village_id=v.village_id) where b.status='Active'  ~replaceqry ");
            }
            if (meesvaSearchForm.getSadaremId() != null && !meesvaSearchForm.getSadaremId().equals("")) {

                q1 += " and a.person_code='" + meesvaSearchForm.getSadaremId() + "'  ";
                q += " and a.person_code='" + meesvaSearchForm.getSadaremId() + "'  ";

            }
            if (meesvaSearchForm.getAadharCardNo() != null && !meesvaSearchForm.getAadharCardNo().equals("")) {

                q1 += " and a.proof_id='" + meesvaSearchForm.getAadharCardNo() + "'  ";
                q += " and a.proof_id='" + meesvaSearchForm.getAadharCardNo() + "'   ";
            }
            if (meesvaSearchForm.getRationCardNo() != null && !meesvaSearchForm.getRationCardNo().equals("")) {

                q1 += " and a.rationCard_number='" + meesvaSearchForm.getRationCardNo() + "'  ";
                q += " and a.rationCard_number='" + meesvaSearchForm.getRationCardNo() + "'  ";
            }
            if (meesvaSearchForm.getPensionId() != null && !meesvaSearchForm.getPensionId().equals("")) {
                q1 += " and a.pensionCard_no='" + meesvaSearchForm.getPensionId() + "' and substring(a.habcode,1,2)='" + meesvaSearchForm.getDistrict_id() + "'  ";
                q += " and a.pensionCard_no='" + meesvaSearchForm.getPensionId() + "' and substring(a.habcode,1,2)='" + meesvaSearchForm.getDistrict_id() + "' ";
            }
            if (meesvaSearchForm.getName() != null && !meesvaSearchForm.getName().equals("")) {
                q1 += " and a.surname like '%" + meesvaSearchForm.getName().trim() + "%' or a.first_name like '%" + meesvaSearchForm.getName().trim() + "%'  ";
                q += " and a.surname like '%" + meesvaSearchForm.getName().trim() + "%' or a.first_name like '%" + meesvaSearchForm.getName().trim() + "%'   ";

            }
            if (meesvaSearchForm.getDistrict_id() != null
                    && !meesvaSearchForm.getDistrict_id().equalsIgnoreCase("0")) {

                q1 += " and substring(a.habcode,1,2)='" + meesvaSearchForm.getDistrict_id() + "' ";
                q += " and substring(a.habcode,1,2)='" + meesvaSearchForm.getDistrict_id() + "'   ";

            }
            if (meesvaSearchForm.getMandal_id() != null && !meesvaSearchForm.getMandal_id().equals("0")) {

                q1 += "  and substring(a.habcode,6,2)='" + meesvaSearchForm.getMandal_id() + "' ";
                q += " and substring(a.habcode,6,2)='" + meesvaSearchForm.getMandal_id() + "' ";
            }
            if (meesvaSearchForm.getPanchayat_id() != null && !meesvaSearchForm.getPanchayat_id().equals("0")) {

                q1 += " and substring(a.habcode,13,2)='" + meesvaSearchForm.getPanchayat_id() + "'";
                q += "  and substring(a.habcode,13,2)='" + meesvaSearchForm.getPanchayat_id() + "'  ";
            }
            if (meesvaSearchForm.getVillage_id() != null && !meesvaSearchForm.getVillage_id().equals("0")) {
                q1 += "  and substring(a.habcode,8,3)='" + meesvaSearchForm.getVillage_id() + "'";
                q += " and substring(a.habcode,8,3)='" + meesvaSearchForm.getVillage_id() + "'  ";
            }
            if (meesvaSearchForm.getHabitation_id() != null && !meesvaSearchForm.getHabitation_id().equals("0")) {
                q1 += " and substring(a.habcode,11,2)='" + meesvaSearchForm.getHabitation_id() + "' ";
                q += " and substring(a.habcode,11,2)='" + meesvaSearchForm.getHabitation_id() + "'  ";
            }
            String sql = query.toString().replaceAll("~replaceStr", q);
            sql = sql.replaceAll("~replaceqry", q1);

            
            pstmt = con.prepareStatement(sql); 
            
            
            
            
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("personCode", rs.getString(1));
                    map.put("name", rs.getString(2));
                    map.put("ageYears", rs.getString(3));
                    map.put("relationName", rs.getString(4));
                    map.put("gender", rs.getString(5));
                    //map.put("certificatetype", rs.getString(6));
                    map.put("districtName", rs.getString(6));
                    map.put("mandalName", rs.getString(7));
                    map.put("districtId", rs.getString(8));
                    map.put("mandalId", rs.getString(9));
                    map.put("villageId", rs.getString(10));
                    map.put("habCode", rs.getString(11));
                    map.put("districtName", rs.getString(12));
                    map.put("mandalName", rs.getString(13));
                    map.put("villageName", rs.getString(14));
                    map.put("phone", rs.getString(15));

                    map.put("totaldisability", rs.getString(16));
                    map.put("flagTableStatus", tableStatus);


                    certificateValidDetails.add(map);
                }

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCertificateValidDetails", "MeesvaSearchDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MeesvaSearchDAO", "getCertificateValidDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCertificateValidDetails", "MeesvaSearchDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MeesvaSearchDAO", "getCertificateValidDetails");
        } finally {
            DBConnection.closeConnection(con);
        }
        return certificateValidDetails;

    }

    public MeesevaSearchDTO getRequestDetails(DataSource ds, String applicationNo) throws SADAREMDBException, SQLException {

        Connection con = null;
        ResultSet rs = null;
        MeesevaSearchDTO meesvaSearchDTO = null;
        PreparedStatement pstmt = null;
        try {
            con = DBConnection.getConnection();
            if (applicationNo != null && !"".equals(applicationNo)) {
                pstmt = con.prepareStatement("select sur_name, first_name, age,coalesce( house_no,'-') house_no,D.District_Name,M.Mandal_Name,p.panchayat_name,V.Village_Name,H.Habitation_Name, convert(varchar,s.created_date,103) created_date,meeseva_transactionid"
                        + " from tblsadaremGreivances_Details s,tblDistrict_Details D,tblMandal_Details M,tblpanchayat_Details p,tblVillage_Details V,tblHabitation_Details H"
                        + " where s.flag='Active' and s.Village_ID = H.Village_ID and s.District_ID = H.District_ID and s.Mandal_ID = H.Mandal_ID and s.Habitation_ID = H.Habitation_ID"
                        + " and s.District_ID = D.District_ID and s.District_ID = M.District_ID and s.Mandal_ID = M.Mandal_ID and s.District_ID = p.District_ID "
                        + " and s.Mandal_ID = p.Mandal_ID and s.panchayat_ID = p.panchayat_ID and s.Village_ID = V.Village_ID and s.District_ID = V.District_ID"
                        + " and s.Mandal_ID = V.Mandal_ID and Beneficiary_Problem_ID=?");
                pstmt.setString(1, applicationNo);

                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        meesvaSearchDTO = new MeesevaSearchDTO();
                        meesvaSearchDTO.setSurname(rs.getString("sur_name"));
                        meesvaSearchDTO.setFirstname(rs.getString("first_name"));
                        meesvaSearchDTO.setAge(rs.getString("age"));
                        meesvaSearchDTO.setHouseno(rs.getString("house_no"));
                        meesvaSearchDTO.setDistrictName(rs.getString("District_Name"));
                        meesvaSearchDTO.setMandalName(rs.getString("Mandal_Name"));
                        meesvaSearchDTO.setPanchayatName(rs.getString("panchayat_name"));
                        meesvaSearchDTO.setVillageName(rs.getString("Village_Name"));
                        meesvaSearchDTO.setHabitationName(rs.getString("Habitation_Name"));
                        meesvaSearchDTO.setCreated_date(rs.getString("created_date"));
                        meesvaSearchDTO.setMeesevaId(rs.getString("meeseva_transactionid"));

                    }
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestDetails", "MeesvaSearchDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MeesvaSearchDAO", "getRequestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestDetails", "MeesvaSearchDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MeesvaSearchDAO", "getRequestDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return meesvaSearchDTO;
    }

    public MeesevaSearchDTO getSerachAcknowledgementDetails(DataSource ds, MeesvaSearchForm meesvaSearchForm) throws SADAREMDBException, SQLException {

        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        MeesevaSearchDTO meesevaSearchDTO = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            query = "select a.applicationid,a.operatorid,a.firstname,a.district_name,a.mandal_name,a.village_name," +
                    " convert(varchar,createddate,103)as createddate,a.habCode,a.transacrtionId from" +
                    " (select top 1 a.applicationid,a.operatorid,a.firstname,b.district_name,c.mandal_name,d.village_name," +
                    " createddate,e.habCode,a.transacrtionId from meesevasearchprintdetails a join tblperson_personal_Details e with (nolock)  on" +
                    " (a.personcode=e.person_code) join tbldistrict_Details b on (a.districtid=b.district_id)" +
                    " join tblmandal_Details c on (a.districtid=c.district_id and a.mandalid=c.mandal_id)" +
                    " join tblvillage_Details d on (a.districtid=d.district_id and a.mandalid=d.mandal_id" +
                    " and a.villageid=d.village_id)" +
                    " where applicationid =?  order by createddate desc)a";

            pstmt = con.prepareStatement(query); 
            
            pstmt.setString(1, meesvaSearchForm.getApplicationNo());
            
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    meesevaSearchDTO = new MeesevaSearchDTO();
                    meesevaSearchDTO.setApplicationNo(rs.getString(1));
                    meesevaSearchDTO.setChannelId(rs.getString(2));
                    meesevaSearchDTO.setFirstname(rs.getString(3));
                    meesevaSearchDTO.setDistrictName(rs.getString(4));
                    meesevaSearchDTO.setMandalName(rs.getString(5));
                    meesevaSearchDTO.setVillageName(rs.getString(6));
                    meesevaSearchDTO.setCreated_date(rs.getString(7));
                    meesevaSearchDTO.setHabCode(rs.getString(8));
                    meesevaSearchDTO.setMeesevaId(rs.getString(9));
                }
            }



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSerachAcknowledgementDetails", "MeesvaSearchDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MeesvaSearchDAO", "getSerachAcknowledgementDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSerachAcknowledgementDetails", "MeesvaSearchDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MeesvaSearchDAO", "getSerachAcknowledgementDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return meesevaSearchDTO;
    }

    public int insertSearchMeesevaSearchDetails(DataSource ds, MeesvaSearchForm meesvaSearchForm) throws SADAREMDBException, SQLException {

        int insertSearchMeesevaSearchDetails = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        PreparedStatement subPstmt = null;
        String query = null;
        String panchayatId = null;
        String habitationId = null;
        String subQuery = null;
        ResultSet subRs = null;
        try {
            con = DBConnection.getConnection();
            subQuery = "select distinct substring(habcode,13,2) as panchayat,substring(habcode,11,2)as habitation  from tblperson_personal_Details  with (nolock) where habcode=?";

            subPstmt = con.prepareStatement(subQuery); 
            
            subPstmt.setString(1, meesvaSearchForm.getHabCode());
            subRs = subPstmt.executeQuery();
            if (subRs != null) {
                while (subRs.next()) {
                    panchayatId = subRs.getString(1);
                    habitationId = subRs.getString(2);
                }
            }
            query = "INSERT INTO [meesevasearchprintdetails](personCode,Firstname,Mobile,DistrictId,MandalId,Panchayatid,VillageId,"
                    + " HabitationId,SystemIp,CreatedDate,updatedDate,flag,loginId,operatorId,ApplicationId,habcode,printCost,scapassword)"
                    + " VALUES(?,?,?,"
                    + " ?,?,?,"
                    + " ?,?,?,getDate(),getDate(),'Active'"
                    + " ,?,?,?,?,'25',?)";


            
            
            pstmt = con.prepareStatement(query);
            
            pstmt.setString(1,  meesvaSearchForm.getSadaremId());
            pstmt.setString(2,  meesvaSearchForm.getFirstname());
            pstmt.setString(3,	meesvaSearchForm.getMobile());
            pstmt.setString(4,  meesvaSearchForm.getDistrict_id());
            pstmt.setString(5,  meesvaSearchForm.getMandal_id());
            pstmt.setString(6,  panchayatId);
            pstmt.setString(7,  meesvaSearchForm.getVillage_id());
            pstmt.setString(8,  habitationId);
            pstmt.setString(9,  meesvaSearchForm.getSystemIp());
            pstmt.setString(10, meesvaSearchForm.getLoginId());
            pstmt.setString(11, meesvaSearchForm.getChannelId());
            pstmt.setString(12, meesvaSearchForm.getApplicationNo());
            pstmt.setString(13,	meesvaSearchForm.getHabCode());
            pstmt.setString(14, meesvaSearchForm.getScaPassword());
            
            
            insertSearchMeesevaSearchDetails = pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertSearchMeesevaSearchDetails", "MeesvaSearchDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MeesvaSearchDAO", "insertSearchMeesevaSearchDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertSearchMeesevaSearchDetails", "MeesvaSearchDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MeesvaSearchDAO", "insertSearchMeesevaSearchDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
        }
        return insertSearchMeesevaSearchDetails;


    }

    public int updateSearchMEESEVATransctionId(DataSource ds, String applicationId, String meesevaId) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        int updateSearch = 0;


        try {
            con = DBConnection.getConnection();
            if (applicationId != null && !applicationId.equalsIgnoreCase("")) {
                con = DBConnection.getConnection();
                query = "update tblsadaremGreivances_Details set meeseva_transactionid=? where Beneficiary_Problem_ID=?";
                pstmt = con.prepareStatement(query); 
                
                pstmt.setString(1, meesevaId);
                pstmt.setString(2, applicationId);
      
                
                updateSearch = pstmt.executeUpdate();

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateSearchMEESEVATransctionId", "MeesvaSearchDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MeesvaSearchDAO", "updateSearchMEESEVATransctionId");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateSearchMEESEVATransctionId", "MeesvaSearchDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MeesvaSearchDAO", "updateSearchMEESEVATransctionId");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
        }
        return updateSearch;
    }

    public ArrayList getSADAREMIDPercentageDetails(DataSource ds, MeesvaSearchForm meesvaSearchForm, String sadaremId) throws SADAREMDBException, SQLException {

        ArrayList totalDisability = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        Map map = null;
        try {
            con = DBConnection.getConnection();
            query = "select a.totalDisability,c.district_name,d.mandal_name,b.person_code,b.habcode,b.district_id,b.mandal_id,b.village_id,b.surname+space(2)+b.first_name as Name,coalesce(phone_no,'-')as phone_no  "
                    + " from tblPerson_Disability_TotalValue_Details a"
                    + " join tblperson_personal_Details b  with (nolock) on (a.person_code=b.person_code)"
                    + " join tbldistrict_Details c on (b.district_id=c.district_id)"
                    + " join tblmandal_Details d on (b.district_id=d.district_id and b.mandal_id= d.mandal_id)"
                    + " where b.person_code=?";

            
            pstmt = con.prepareStatement(query); 
            
            pstmt.setString(1, sadaremId);
            
            
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("totalDisability", rs.getString(1));
                    map.put("districtName", rs.getString(2));
                    map.put("mandalName", rs.getString(3));
                    map.put("personCode", rs.getString(4));
                    map.put("habCode", rs.getString(5));
                    map.put("districtId", rs.getString(6));
                    map.put("mandalId", rs.getString(7));
                    map.put("villageId", rs.getString(8));
                    map.put("name", rs.getString(9));
                    map.put("phoneNo", rs.getString(10));

                    totalDisability.add(map);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSADAREMIDPercentageDetails", "MeesvaSearchDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MeesvaSearchDAO", "getSADAREMIDPercentageDetails");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSADAREMIDPercentageDetails", "MeesvaSearchDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MeesvaSearchDAO", "getSADAREMIDPercentageDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
        }

        return totalDisability;

    }

    public int getprintMeesevaSearchDetails(DataSource ds, MeesvaSearchForm meesvaSearchForm) throws SADAREMDBException, SQLException {

        int insertSearchMeesevaSearchDetails = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        PreparedStatement subPstmt = null;
        String query = null;
        String panchayatId = null;
        String habitationId = null;
        String subQuery = null;
        ResultSet subRs = null;
        try {
            con = DBConnection.getConnection();
            subQuery = "select distinct substring(habcode,13,2) as panchayat,substring(habcode,11,2)as habitation  "
                    + " from tblperson_personal_Details  with (nolock) where habcode=?";
    
            
            subPstmt = con.prepareStatement(subQuery);  
            
            subPstmt.setString(1,meesvaSearchForm.getHabCode() );
            
            
            subRs = subPstmt.executeQuery();
            if (subRs != null) {
                while (subRs.next()) {
                    panchayatId = subRs.getString(1);
                    habitationId = subRs.getString(2);
                }
            }
            query = "INSERT INTO [meesevasearchprintdetails](personCode,Firstname,Mobile,DistrictId,MandalId,Panchayatid,VillageId,"
                    + " HabitationId,SystemIp,CreatedDate,updatedDate,flag,loginId,operatorId,ApplicationId,habcode,printCost,certificatetype,scapassword,meesevaFlag)"
                    + " VALUES(?,?,?,"
                    + " ?,?,?,"
                    + " ?,?,?,getDate(),getDate(),'Active'"
                    + " ,?,?,?,?,'25',?,?,?)";


            pstmt = con.prepareStatement(query); 
            
            
            pstmt.setString(1,  meesvaSearchForm.getSadaremId());
            pstmt.setString(2,  meesvaSearchForm.getFirstname());
            pstmt.setString(3,	meesvaSearchForm.getMobile());
            pstmt.setString(4,  meesvaSearchForm.getDistrict_id());
            pstmt.setString(5,  meesvaSearchForm.getMandal_id());
            pstmt.setString(6,  panchayatId);
            pstmt.setString(7,  meesvaSearchForm.getVillage_id());
            pstmt.setString(8,  habitationId);
            pstmt.setString(9,  meesvaSearchForm.getSystemIp());
            pstmt.setString(10, meesvaSearchForm.getLoginId());
            pstmt.setString(11, meesvaSearchForm.getChannelId());
            pstmt.setString(12, meesvaSearchForm.getApplicationNo());
            pstmt.setString(13,	meesvaSearchForm.getHabCode());
            pstmt.setString(14, meesvaSearchForm.getCertificateType() );    
            pstmt.setString(15, meesvaSearchForm.getScaPassword());
            pstmt.setString(16, meesvaSearchForm.getMeesevaFlag());
            
            
            insertSearchMeesevaSearchDetails = pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertSearchMeesevaSearchDetails", "MeesvaSearchDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MeesvaSearchDAO", "insertSearchMeesevaSearchDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertSearchMeesevaSearchDetails", "MeesvaSearchDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MeesvaSearchDAO", "insertSearchMeesevaSearchDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
        }
        
        return insertSearchMeesevaSearchDetails;


    }



    }
