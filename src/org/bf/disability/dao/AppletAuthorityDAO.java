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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.AssessedPWDDetailsDTO;
import org.bf.disability.form.AppletAuthorityForPartBForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 484898
 */
public class AppletAuthorityDAO {

    /**
     * This method is for check the person status for given 17 digit number
     * @param DataSource
     * @param sadaremCode
     **/
    public synchronized int getPersonCodeForAppletAuthority(DataSource ds, String sadaremCode) throws SADAREMDBException, SQLException {

        int sadaremId = 0;
        String sql = null;
        Connection con = null;
       // Statement st = null; 
        PreparedStatement pstmt = null ;
        try {
            con = DBConnection.getConnection();
           
            sql = "update tblPerson_personal_details set view_Edit_mode='View' where person_code=?"; 
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1,sadaremCode );
            
            sadaremId = pstmt.executeUpdate();


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonCodeForAppletAuthority", "AppletAuthorityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getPersonCodeForAppletAuthority");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonCodeForAppletAuthority", "AppletAuthorityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getPersonCodeForAppletAuthority");
        } finally {
            try {
                if (con != null) {
                    DBConnection.closeConnection(con);
                    DBConnection.closeStatement(pstmt);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sadaremId;
    }

    /**
     * This method is for check the person status for given 17 digit number
     * @param DataSource
     * @param sadaremCode
     **/
    public int checkPersonStatusForAppealAuthority(DataSource ds, String sadaremCode, String personStatus) throws SADAREMDBException, SQLException {

        int sadaremId = 0;
        String sql = null;
        Connection con = null;
      
        PreparedStatement pstmt = null ;
        try {
            con = DBConnection.getConnection();
           
            sql = "update AppellateAuthorityandTemporary_RegistrationDetails set ViewEditMode='View',status='Inactive',updateddate=getDate(),disabilitystatus=? where person_code=?";
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1,personStatus );
            pstmt.setString(2,sadaremCode );
            
            sadaremId =pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersonStatusForAppealAuthority", "AppletAuthorityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "checkPersonStatusForAppealAuthority");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersonStatusForAppealAuthority", "AppletAuthorityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "checkPersonStatusForAppealAuthority");
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
        return sadaremId;
    }

    /**
     * This method is for check the person status for given 17 digit number
     * @param DataSource
     * @param sadaremCode
     **/
    public int updateRationCardDetails(DataSource ds, String sadaremCode, String rationCardNo, String rationCardSlno, HttpServletRequest request) throws SADAREMDBException, SQLException {

        int sadaremId = 0;
        String sql = null;
        Connection con = null;
        PreparedStatement pstmt = null ;
 
        HttpSession session = request.getSession();
        try {
            con = DBConnection.getConnection();
      
            sql = "update tblPerson_personal_details set rationcard_number=?,rationcard_slno=?,RCamp_ID=?,RLogin=?,RDate=getDate() where person_code=?";
            
            	pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1,rationCardNo );
            pstmt.setString(2,rationCardSlno );
            pstmt.setString(3,session.getAttribute("campId")+"" );
            pstmt.setString(4, session.getAttribute("loginid")+"" );
            pstmt.setString(5,sadaremCode );
            
            sadaremId = pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateRationCardDetails", "AppletAuthorityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "updateRationCardDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateRationCardDetails", "AppletAuthorityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "updateRationCardDetails");
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
        return sadaremId;
    }

    /**
     * This method is for get the personal details for given 17 digit number
     * @param DataSource
     * @param sadaremCode
     **/
    public ArrayList getAppletAuthorityDetails(DataSource ds, String sadaremCode, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ArrayList<HashMap> personalDetails = new ArrayList<HashMap>();
        String sql = null;
        ResultSet rs = null;
        Connection con = null;
       // Statement st = null; 
        PreparedStatement pstmt = null;
       HttpSession session = request.getSession();
        String categoryid = "";
        String categorycount = "";
        String selectflow = null;
        try {




            /*   sql = "select coalesce(ppd.pensioncard_no,'-'),ppd.surname+ space(2) + ppd.first_name,ppd.relation_name," +
            "case when ppd.gender=1 then 'Male' else 'Female' end as gender,ppd.age_years," +
            "case when ppd.religion='1' then 'Hindu' when ppd.religion='2' then 'Muslim' when ppd.religion='3' then 'Christian' " +
            "when ppd.religion='4' then 'Sikh' when ppd.religion='5' then 'Jain' when ppd.religion='6' then 'Budhist' else 'Others' end as religion," +
            "case when ppd.caste='1' then 'ST' when ppd.caste='2' then 'SC' when ppd.caste='3' then 'BC' when ppd.caste='4' then 'OC' " +
            "when ppd.caste='5' then 'Minority' else 'NA' end as caste,coalesce(ppd.rationcard_number,'')," +
            "case when ppd.typeof_disability='1' then 'Locomotor/OH' when ppd.typeof_disability='2' then 'Visual Impairment' " +
            "when ppd.typeof_disability='3' then 'Hearing Impairment'when ppd.typeof_disability='4' then 'Mental Retardation' " +
            "when ppd.typeof_disability='5' then 'Mental Illness' else 'Multiple Disabilities' end as disability,ppd.mole_one," +
            "ppd.mole_two,ppd.person_code,CONVERT(VARCHAR(10), ppd.date_of_birth, 103) as date_of_birth," +
            "case when ppd.person_status='Eligible' then 'checked' else 'unchecked' end as person_statusEli," +
            "case when ppd.person_status='Rejected' then 'checked' else 'unchecked' end as person_statusRej," +
            "dd.district_name,md.mandal_name,vd.village_name,hd.habitation_name,ppd.rationcard_slno from tblPerson_personal_details PPD " +
            //"join dbo.AppellateAuthorityandTemporary_RegistrationDetails ATR on(ppd.person_code=ATR.person_code) "+
            "join tblDistrict_details DD on(ppd.district_id=dd.district_id) " +
            "join tblMandal_details MD on(ppd.district_id=dd.district_id and ppd.mandal_id=md.mandal_id and dd.district_id=md.district_id) " +
            "join tblVillage_details VD on(vd.village_id=ppd.village_id and ppd.district_id=vd.district_id and ppd.mandal_id=vd.mandal_id) " +
            "join tblHabitation_details HD on(hd.district_id=ppd.district_id and hd.mandal_id=ppd.mandal_id and hd.village_id=ppd.village_id and hd.habitation_id=ppd.habitation_id) " +
            "where ppd.person_code='"+sadaremCode+"' and ppd.status='Active' and ppd.view_edit_mode='View' and ppd.district_id='"+session.getAttribute("districtId")+"'";
             */
            selectflow = request.getParameter("selectFlow");
            if (selectflow != null && selectflow.equalsIgnoreCase("appellate")) {
                categoryid = "2";
            } else if (selectflow != null && selectflow.equalsIgnoreCase("temporay")) {
                categoryid = "3";
            }
            categorycount = getCategoryCount(ds, sadaremCode, categoryid);
            con = DBConnection.getConnection();
            sql = "select coalesce(ppd.pensioncard_no,'-'),ppd.surname+ space(2) + ppd.first_name,ppd.relation_name,"
                    + "case when ppd.gender=1 then 'Male' else 'Female' end as gender,ppd.age_years,"
                    + "case when ppd.religion='1' then 'Hindu' when ppd.religion='2' then 'Muslim' when ppd.religion='3' then 'Christian' "
                    + "when ppd.religion='4' then 'Sikh' when ppd.religion='5' then 'Jain' when ppd.religion='6' then 'Budhist' else 'Others' end as religion,"
                    + "case when ppd.caste='1' then 'ST' when ppd.caste='2' then 'SC' when ppd.caste='3' then 'BC' when ppd.caste='4' then 'OC' "
                    + "when ppd.caste='5' then 'Minority' else 'NA' end as caste,coalesce(ppd.rationcard_number,''),"
                    + "case when ppd.typeof_disability='1' then 'Locomotor/OH' when ppd.typeof_disability='2' then 'Visual Impairment' "
                    + "when ppd.typeof_disability='3' then 'Hearing Impairment'when ppd.typeof_disability='4' then 'Mental Retardation' "
                    + "when ppd.typeof_disability='5' then 'Mental Illness' else 'Multiple Disabilities' end as disability,ppd.mole_one,"
                    + "ppd.mole_two,ppd.person_code,CONVERT(VARCHAR(10), ppd.date_of_birth, 103) as date_of_birth,"
                    + "case when ppd.person_status='Eligible' then 'checked' else 'unchecked' end as person_statusEli,"
                    + "case when ppd.person_status='Rejected' then 'checked' else 'unchecked' end as person_statusRej,"
                    + "dd.district_name,md.mandal_name,vd.village_name,hd.habitation_name,ppd.rationcard_slno,ppd.person_status,ppd.personname_telugu,ppd.partA_loginid,ppd.PensionCard_No \n"
                    + " from tblPerson_personal_details PPD   with (nolock) "
                    + "join dbo.AppellateAuthorityandTemporary_RegistrationDetails ATR on(ppd.person_code=ATR.person_code) "
                    + "join tblDistrict_details DD on(ppd.district_id=dd.district_id) "
                    + "join tblMandal_details MD on( ppd.mandal_id=md.mandal_id and ppd.district_id=md.district_id) "
                    + "join tblVillage_details VD on(ppd.village_id=vd.village_id and ppd.mandal_id=vd.mandal_id and ppd.district_id=vd.district_id ) "
                    + "join tblHabitation_details HD on(hd.habitation_id=ppd.habitation_id and hd.village_id=ppd.village_id   and hd.mandal_id=ppd.mandal_id and hd.district_id=ppd.district_id ) "
                    + "where ppd.person_code=? and ppd.status='Active' and ";
            
            
            if (Integer.parseInt(session.getAttribute("roleId").toString()) == 5) {
                sql += "ppd.district_id=?";
            } else {
                // categoryid=getCategoryId(ds, sadaremCode); session.getAttribute("roleId").toString()

                if (con == null) {
                    con = DBConnection.getConnection();
                }
                sql += " ATR.ViewEditMode='Edit' and ATR.Status='Active' "
                        + "and ATR.categoryId=? and ATR.categoryCount=? and ppd.district_id=?";
            }

            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, sadaremCode);
            // session.getAttribute("districtId")
            if (Integer.parseInt(session.getAttribute("roleId").toString()) == 5) {
            	 pstmt.setString(2, session.getAttribute("districtId")+"");	
            }else
            {
            	 pstmt.setString(2, categoryid);	
            	 pstmt.setString(3, categorycount);	
            	 pstmt.setString(4, session.getAttribute("districtId")+"");	
            }
            
            
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    HashMap data = new HashMap();
                    data.put("pension_id", rs.getString(1));
                    data.put("name", rs.getString(2));
                    data.put("relation_name", rs.getString(3));
                    data.put("gender", rs.getString(4));
                    data.put("age", rs.getString(5));
                    data.put("religion", rs.getString(6));
                    data.put("caste", rs.getString(7));
                    data.put("rationcard", rs.getString(8));
                    data.put("disability", rs.getString(9));
                    data.put("moleOne", rs.getString(10));
                    data.put("moleTwo", rs.getString(11));
                    data.put("sadaremCode", rs.getString(12));
                    data.put("dob", rs.getString(13));
                    data.put("person_statusEli", rs.getString(14));
                    data.put("person_statusRej", rs.getString(15));
                    data.put("district_name", rs.getString(16));
                    data.put("mandal_name", rs.getString(17));
                    data.put("village_name", rs.getString(18));
                    data.put("habitation_name", rs.getString(19));
                    data.put("rationcard_slno", rs.getString(20));
                    data.put("personstatusPrint", rs.getString(21));
                    data.put("person_nameTelugu", rs.getString(22));
                    data.put("RDFLAG", rs.getString(23));
                    data.put("PHENSIONPHASE", rs.getString(24));
                 //   session.setAttribute("teluguNameAu", rs.getString(22));

                    personalDetails.add(data);
                }
            }

        } catch (SQLException sqlEx) { 
        	sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAppletAuthorityDetails", "AppletAuthorityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getAppletAuthorityDetails");
        } catch (Exception sqlEx) {
        	sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAppletAuthorityDetails", "AppletAuthorityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getAppletAuthorityDetails");
        } finally {
            try {
                if (con != null) {

                    DBConnection.closeStatement(pstmt);
                    DBConnection.closeResultSet(rs);
                    DBConnection.closeConnection(con);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return personalDetails;
    }

    /**
     * This method is for get the personal details for given 17 digit number
     * @param DataSource
     * @param sadaremCode
     **/
    public ArrayList getPhysicalReqDetailsPrints(DataSource ds, String sadaremCode, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ArrayList<HashMap> personalDetails = new ArrayList<HashMap>();
        String sql = null;
        ResultSet rs = null;
        Connection con = null;
      //  Statement st = null; 
        
        PreparedStatement pstmt = null ;

        HttpSession session = request.getSession();
        try {
            con = DBConnection.getConnection();
      
            sql = "select coalesce(ppd.pensioncard_no,'-'),ppd.surname+ space(2) + ppd.first_name,ppd.relation_name,"
                    + "case when ppd.gender=1 then 'Male' else 'Female' end as gender,ppd.age_years,"
                    + "case when ppd.religion='1' then 'Hindu' when ppd.religion='2' then 'Muslim' when ppd.religion='3' then 'Christian' "
                    + "when ppd.religion='4' then 'Sikh' when ppd.religion='5' then 'Jain' when ppd.religion='6' then 'Budhist' else 'Others' end as religion,"
                    + "case when ppd.caste='1' then 'ST' when ppd.caste='2' then 'SC' when ppd.caste='3' then 'BC' when ppd.caste='4' then 'OC' "
                    + "when ppd.caste='5' then 'Minority' else 'NA' end as caste,coalesce(ppd.rationcard_number,''),"
                    + "case when pd.disability_id='1' then 'Locomotor/OH' when pd.disability_id='2' then 'Visual Impairment' "
                    + "when pd.disability_id='3' then 'Hearing Impairment'when pd.disability_id='4' then 'Mental Retardation' "
                    + "when pd.disability_id='5' then 'Mental Illness' else 'Multiple Disabilities' end as disability,ppd.mole_one,"
                    + "ppd.mole_two,ppd.person_code,CONVERT(VARCHAR(10), ppd.date_of_birth, 103) as date_of_birth,"
                    + "case when ppd.person_status='Eligible' then 'checked' else 'unchecked' end as person_statusEli,"
                    + "case when ppd.person_status='Rejected' then 'checked' else 'unchecked' end as person_statusRej,"
                    + "dd.district_name,md.mandal_name,vd.village_name,hd.habitation_name,"
                    + " ppd.rationcard_slno,ppd.person_status, pd.disability_id, pd.hospital_name, pd.hospital_address,"
                    + " pd.first_doctor_name, pd.first_doctor_regnumber,pd.first_doctor_designation,t.totaldisability, case "
                    + " when c.years_for_temparary=0 then 'Permanent' else 'Temporary' end as type from tblPerson_personal_details PPD   with (nolock) "
                    // + " join Certificate_With_PhysicalRequirements_RegistrationDetails ATR on(ppd.person_code=ATR.person_code) "
                    + " join tblPerson_Disability_Details pd  on(ppd.person_code=pd.person_code) "
                    + " join tblPerson_Disability_TotalValue_Details t on(ppd.person_code=t.person_code) "
                    + " join tblPerson_Causes_of_Disability_Details c on(ppd.person_code=c.person_code) "
                    + "join tblDistrict_details DD on(ppd.district_id=dd.district_id) "
                    + "join tblMandal_details MD on(ppd.district_id=dd.district_id and ppd.mandal_id=md.mandal_id and dd.district_id=md.district_id) "
                    + "join tblVillage_details VD on(vd.village_id=ppd.village_id and ppd.district_id=vd.district_id and ppd.mandal_id=vd.mandal_id) "
                    + "join tblHabitation_details HD on(hd.district_id=ppd.district_id and hd.mandal_id=ppd.mandal_id and hd.village_id=ppd.village_id and hd.habitation_id=ppd.habitation_id) "
                    + "where ppd.person_code=? and ppd.district_id=? and pd.status='active' and t.status='active' and c.status='active'";

            
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, sadaremCode);
            pstmt.setString(2, session.getAttribute("districtId")+"");
            
            rs = pstmt.executeQuery(); 
           
            
            if (rs != null) {
                if (rs.next()) {
                    HashMap data = new HashMap();
                    data.put("pension_id", rs.getString(1));
                    data.put("name", rs.getString(2));
                    data.put("relation_name", rs.getString(3));
                    data.put("gender", rs.getString(4));
                    data.put("age", rs.getString(5));
                    data.put("religion", rs.getString(6));
                    data.put("caste", rs.getString(7));
                    data.put("rationcard", rs.getString(8));
                    data.put("disability", rs.getString(9));
                    data.put("moleOne", rs.getString(10));
                    data.put("moleTwo", rs.getString(11));
                    data.put("sadaremCode", rs.getString(12));
                    data.put("dob", rs.getString(13));
                    data.put("person_statusEli", rs.getString(14));
                    data.put("person_statusRej", rs.getString(15));
                    data.put("district_name", rs.getString(16));
                    data.put("mandal_name", rs.getString(17));
                    data.put("village_name", rs.getString(18));
                    data.put("habitation_name", rs.getString(19));
                    data.put("rationcard_slno", rs.getString(20));
                    data.put("personstatusPrint", rs.getString(21));
                    data.put("hname", rs.getString(23));
                    data.put("hadd", rs.getString(24));
                    data.put("fname", rs.getString(25));
                    data.put("freg", rs.getString(26));
                    data.put("fdes", rs.getString(27));
                    data.put("per", rs.getString(28));
                    data.put("type", rs.getString(29));
                    session.setAttribute("dis", rs.getString(22));

                    /* pstmt = con.prepareStatement("select * from tbl_Person_Disability_Cando_Details" +
                    " WHERE Person_Code=? and status='active'");

                    pstmt.setString(1, sadaremCode);
                    4rfv55
                    rs1 = pstmt.executeQuery();

                    if (rs1.next()) {
                    data.put("scan",rs.getString("s_can"));
                    data.put("fcan",rs.getString("f_can"));
                    data.put("ppcan",rs.getString("pp_can"));
                    data.put("lcan",rs.getString("l_can"));
                    data.put("kccan",rs.getString("kc_can"));
                    data.put("bcan",rs.getString("b_can"));
                    data.put("stcan",rs.getString("st_can"));
                    data.put("wcan",rs.getString("w_can"));
                    data.put("rwcan",rs.getString("rw_can"));
                    data.put("hcan",rs.getString("h_can"));
                    data.put("secan",rs.getString("se_can"));

                    }*/





                    personalDetails.add(data);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {

                    DBConnection.closeStatement(pstmt);
                    DBConnection.closeResultSet(rs);
                    DBConnection.closeConnection(con);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return personalDetails;
    }


    /*
     * This mentod is for updating the person status weather he is eligible or not
     * @param Datasource
     * @param personStatus
     * @return int
     * @throws SADAREMDBException, SQLException
     **/
    public int updatePersonStatus(DataSource ds, String personStatus, String sadaremCode, String categoryId, String categoryCount, String rationCardNo, String rationCardSlno, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int result = 0;
        String sql = null;
        Connection con = null;
      //  Statement st = null;
        PreparedStatement pstmt = null ;
        HttpSession session = request.getSession();
        try {
            con = DBConnection.getConnection();
             
            sql = "update tblPerson_personal_details set person_status=?,categoryid=?,categorycount=?,rationcard_number=?,rationcard_slno=?,RCamp_ID=?,RLogin=?,RDate=getDate()  where person_code=?";
            
            
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, personStatus);
            pstmt.setString(2, categoryId);
            pstmt.setString(3, categoryCount);
            pstmt.setString(4, rationCardNo);
            pstmt.setString(5, rationCardSlno);
            pstmt.setString(6, session.getAttribute("campId")+"" );
            pstmt.setString(7, session.getAttribute("loginid")+""); 
            pstmt.setString(8,sadaremCode);
            
            result = pstmt.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    DBConnection.closeConnection(con);
                    DBConnection.closeStatement(pstmt);
                    //DBConnection.closeResultSet(rs);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /*
     * This mentod is for updating the person status weather he is eligible or not
     * @param Datasource
     * @param personStatus
     * @return int
     * @throws SADAREMDBException, SQLException
     **/
    public int updatePersonStatusAppeal(DataSource ds, String personStatus, String sadaremCode) throws SADAREMDBException, SQLException {
        int result = 0;
        String sql = null;
        Connection con = null;
        //Statement st = null;
        PreparedStatement pstmt = null ;
        try {
            con = DBConnection.getConnection();
          
            sql = "update AppellateAuthorityandTemporary_RegistrationDetails set disabilitystatus=?  where person_code=?"; 
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, personStatus);
            pstmt.setString(2, sadaremCode);
            
            
            result = pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatePersonStatusAppeal", "AppletAuthorityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "updatePersonStatusAppeal");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatePersonStatusAppeal", "AppletAuthorityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "updatePersonStatusAppeal");
        } finally {
            try {
                if (con != null) {
                    DBConnection.closeConnection(con);
                    DBConnection.closeStatement(pstmt);
                    //DBConnection.closeResultSet(rs);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /*
     * This mentod is for updating the person status weather he is eligible or not
     * @param Datasource
     * @param personStatus
     * @return int
     * @throws SADAREMDBException, SQLException
     **/
    public String checkPersonForEligibility(DataSource ds, String sadaremCode, String categoryid) throws SADAREMDBException, SQLException {
        String result = null;
        String sql = null;
        Connection con = null;
        //Statement st = null;
        PreparedStatement pstmt = null ;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
        
            sql = "select ViewEditMode from AppellateAuthorityandTemporary_RegistrationDetails  WITH(NOLOCK) where person_code=?"
                    + "and categoryid=? ";
            // sql = "select View_Edit_Mode from tblPerson_personal_details where person_code='" + sadaremCode + "'";
            
            
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, sadaremCode);
            pstmt.setString(2, categoryid);
      
            
            rs = pstmt.executeQuery();
            int forAppellate=0;
            int forTemparary=0;
            if (rs != null) {
                while (rs.next()) {
                    result = rs.getString(1);
                    if(result.equals("APA"))
                    {
                    	forAppellate++;                    	
                    }
                    if(result.equals("Edit"))
                    {
                    	forTemparary++;                    	
                    }
                }
            }
            if(forAppellate==0 && forTemparary>0)
            {
            	result = "Edit";
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersonForEligibility", "AppletAuthorityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "checkPersonForEligibility");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersonForEligibility", "AppletAuthorityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "checkPersonForEligibility");
        } finally {
            try {
                if (con != null) {

                    DBConnection.closeStatement(pstmt);
                    DBConnection.closeResultSet(rs);
                    DBConnection.closeConnection(con);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /*
     * This mentod is for updating the person status weather he is eligible or not
     * @param Datasource
     * @param personStatus
     * @return int
     * @throws SADAREMDBException, SQLException
     **/
    public String getCategoryId(DataSource ds, String sadaremCode) throws SADAREMDBException, SQLException {
        String result = null;
        String sql = null;
        Connection con = null;
        PreparedStatement pstmt = null ;
        //Statement st = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
           // st = con.createStatement();
            sql = "select categoryid from AppellateAuthorityandTemporary_RegistrationDetails where person_code=?";
            
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, sadaremCode);
            
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    result = rs.getString(1);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCategoryId", "AppletAuthorityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getCategoryId");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCategoryId", "AppletAuthorityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getCategoryId");
        } finally {
            try {
                if (con != null) {
                    DBConnection.closeConnection(con);
                    DBConnection.closeStatement(pstmt);
                    DBConnection.closeResultSet(rs);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /*
     * This mentod is for updating the person status weather he is eligible or not
     * @param Datasource
     * @param personStatus
     * @return int
     * @throws SADAREMDBException, SQLException
     **/
    public String getCategoryCount(DataSource ds, String sadaremCode, String categoryid) throws SADAREMDBException, SQLException {
        String result = null;
        String sql = null;
        Connection con = null;
        PreparedStatement pstmt = null ;
        //Statement st = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
          
            sql = "select max(categoryCount) from AppellateAuthorityandTemporary_RegistrationDetails where person_code=?"
                    + "and categoryid=?";
            
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, sadaremCode);
            pstmt.setString(2, categoryid);
            
            
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    result = rs.getString(1);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCategoryCount", "AppletAuthorityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getCategoryCount");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCategoryCount", "AppletAuthorityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getCategoryCount");
        } finally {
            try {
                if (con != null) {
                    DBConnection.closeConnection(con);
                    DBConnection.closeStatement(pstmt);
                    DBConnection.closeResultSet(rs);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String getCategoryCount(DataSource ds, String sadaremCode) throws SADAREMDBException, SQLException {
        String result = null;
        String sql = null;
        Connection con = null;
        //Statement st = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null ;
        try {
            con = DBConnection.getConnection();
         
            sql = "select categoryCount from AppellateAuthorityandTemporary_RegistrationDetails where person_code=?";
            
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, sadaremCode);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    result = rs.getString(1);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCategoryCount", "AppletAuthorityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getCategoryCount");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCategoryCount", "AppletAuthorityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getCategoryCount");
        } finally {
            try {
                if (con != null) {
                    DBConnection.closeConnection(con);
                    DBConnection.closeStatement(pstmt);
                    DBConnection.closeResultSet(rs);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /*
     * This mentod is for updating the person status weather he is eligible or not
     * @param Datasource
     * @param personStatus
     * @return int
     * @throws SADAREMDBException, SQLException
     **/
    public String[] getCategoryDetails(DataSource ds, String sadaremCode) throws SADAREMDBException, SQLException {
        String sql = null;
        Connection con = null;
       // Statement st = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null ;
        String[] details = new String[2];
        try {
            con = DBConnection.getConnection();
           // st = con.createStatement();
            sql = "select ISNULL(categoryId,1) categoryid,ISNULL(categoryCount,1) categoryCount from tblPerson_personal_details   with (nolock) where person_code=?";
            
            
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, sadaremCode);
        
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    details[0] = rs.getString(1);
                    details[1] = rs.getString(2);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCategoryDetails", "AppletAuthorityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getCategoryDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCategoryDetails", "AppletAuthorityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getCategoryDetails");
        } finally {
            try {
                if (con != null) {
                    DBConnection.closeConnection(con);
                    DBConnection.closeStatement(pstmt);
                    DBConnection.closeResultSet(rs);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return details;
    }

    /**
     *
     * @param ds
     * @param personcode
     * @throws org.bf.disability.Exception.SADAREMException
     */
    public void deletePreviousDisabilityDetailsForUpdateDisability(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        CallableStatement calstmt = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            calstmt = con.prepareCall("{Call SP_DeleteDatainAllTables_for_RejectPerson(?)}");
            calstmt.setString(1, personcode);
            calstmt.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousDisabilityDetailsForUpdateDisability", "AppletAuthorityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "deletePreviousDisabilityDetailsForUpdateDisability");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousDisabilityDetailsForUpdateDisability", "AppletAuthorityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "deletePreviousDisabilityDetailsForUpdateDisability");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(calstmt);

        }
    }

    /**
     *
     * @param ds
     * @param personcode
     * @throws org.bf.disability.Exception.SADAREMException
     */
    public void getAge(DataSource ds, String personcode, AppletAuthorityForPartBForm partAForm) throws SADAREMDBException, SQLException {

        Connection con = null;
        String sql = null;
        //Statement st = null;
        PreparedStatement pstmt = null ;
        
        ResultSet rs = null;
        try {

            con = DBConnection.getConnection();
          //  st = con.createStatement();
            sql = "select SUBSTRING(CONVERT(varchar(24), Date_of_birth, 121), 1, 4) as year,"
                    + "SUBSTRING(CONVERT(varchar(24), Date_of_birth, 121), 6, 2) as month,"
                    + "SUBSTRING(CONVERT(varchar(24), Date_of_birth, 121), 9, 2) as day from "
                    + "tblperson_personal_details   with (nolock) where person_code=?";
            
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, personcode);
            
            
            
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    partAForm.setYear(rs.getString(1));
                    partAForm.setMonth(rs.getString(2));
                    partAForm.setDay(rs.getString(3));
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAge", "AppletAuthorityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getAge");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAge", "AppletAuthorityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getAge");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
    }

    /**
     * This method is for get the schedule Details
     * @param DataSource
     * @param sadaremCode
     **/
    public ArrayList getScheduleData(DataSource ds, String district_id, String mandal_id) throws SADAREMDBException, SQLException {

        String sql = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null ;
      //  Statement st = null;
        ArrayList scheduleData = new ArrayList();
        try {
            con = DBConnection.getConnection();
           // st = con.createStatement();
            sql = "select V.village_name,CONVERT(VARCHAR(10), AAS.schedule_date, 103) as scheduleDate from Appeal_Authority_Schedule AAS "
                    + "join tblVillage_details V on(AAS.village_id=V.village_id and AAS.district_id=V.district_id and AAS.mandal_id=V.mandal_id "
                    + "and AAS.district_id=? and AAS.mandal_id=?) order by V.village_name,AAS.schedule_date";
            
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, district_id);
            
            pstmt.setString(2, mandal_id);
            
            

            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                HashMap<String, Object> data = new HashMap<String, Object>();
                data.put("village_name", rs.getString(1));
                data.put("scheduleDate", rs.getString(2));
                scheduleData.add(data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    DBConnection.closeConnection(con);
                    DBConnection.closeStatement(pstmt);
                    DBConnection.closeResultSet(rs);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return scheduleData;
    }

    /*
     * This mentod is for updating the person status weather he is eligible or not
     * @param Datasource
     * @param personStatus
     * @return int
     * @throws SADAREMDBException, SQLException
     **/
    public String getAge(DataSource ds, String sadaremCode) throws SADAREMDBException, SQLException {
        String result = null;
        String sql = null;
        Connection con = null;
       // Statement st = null;
        PreparedStatement pstmt = null ;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            
            sql = "select age_years from tblPerson_personal_details   with (nolock) where person_code=?";
            
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, sadaremCode);
            
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    result = rs.getString(1);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAge", "AppletAuthorityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getAge");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAge", "AppletAuthorityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getAge");
        } finally {
            try {
                if (con != null) {
                    DBConnection.closeConnection(con);
                    DBConnection.closeStatement(pstmt);
                    DBConnection.closeResultSet(rs);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /*
     * This mentod is for updating the person status weather he is eligible or not
     * @param Datasource
     * @param personStatus
     * @return int
     * @throws SADAREMDBException, SQLException
     **/
    public int getCheckStatusForAppealCheck(DataSource ds, String sadaremCode, String categoryid) throws SADAREMDBException, SQLException {

        String sql = null;
        Connection con = null;
        //Statement st = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null ;
        int i = 0;
        try {
            con = DBConnection.getConnection();
            //st = con.createStatement();
            sql = "select * from dbo.AppellateAuthorityandTemporary_RegistrationDetails where person_code=?"
                    + " and categoryid =?";
            
            
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, sadaremCode);
            
            pstmt.setString(2, categoryid);
        
            
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    i = 1;
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCheckStatusForAppealCheck", "AppletAuthorityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getCheckStatusForAppealCheck");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCheckStatusForAppealCheck", "AppletAuthorityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getCheckStatusForAppealCheck");
        } finally {
            try {
                if (con != null) {
                    DBConnection.closeConnection(con);
                    DBConnection.closeStatement(pstmt);

                    DBConnection.closeResultSet(rs);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    /*
     * This mentod is for updating the person status weather he is eligible or not
     * @param Datasource
     * @param personStatus
     * @return int
     * @throws SADAREMDBException, SQLException
     **/
    public String getPersonStatus(DataSource ds, String sadaremCode) throws SADAREMDBException, SQLException {

        String sql = null;
        Connection con = null;
        //Statement st = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null ;
        String code = null;
        try {
            con = DBConnection.getConnection();
           // st = con.createStatement();
            sql = "select person_status from tblPerson_personal_details   with (nolock) where person_code=?";
            

            
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, sadaremCode); 
            
            rs = pstmt.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    code = rs.getString(1);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonStatus", "AppletAuthorityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getPersonStatus");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonStatus", "AppletAuthorityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getPersonStatus");
        } finally {
            try {
                if (con != null) {
                    DBConnection.closeConnection(con);
                    DBConnection.closeStatement(pstmt);

                    DBConnection.closeResultSet(rs);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return code;
    }

    /*
     * This method is for getting the PWD status based on sadarem code
     * @param Datasource
     * @param transactionalStatus
     * @param person_code
     * @param request
     * @return int
     * @Exception
     **/
    public int insertTransactionDetails(DataSource ds, String transactionalStatus, String person_code, HttpServletRequest request) throws SADAREMDBException, SQLException {

        String sql = null;
        Connection con = null;
        //Statement st = null;
        PreparedStatement pstmt = null ;
        HttpSession session = request.getSession();
        String pensionphase = "";
        int insertStatus = 0;
        try {
            pensionphase = getPensionPhase(ds, person_code);
            con = DBConnection.getConnection();
     

            sql = "insert into tblTransactional_Status(Person_code,Transaction_status,campid,Login_id,"
                    + "Created_Date,Updated_Date,pensionphase) values(?,?,?,?,getDate(),getDate(),?)";

            

            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, person_code); 
            pstmt.setString(2, transactionalStatus); 
            pstmt.setString(3, session.getAttribute("campId")+""); 
            pstmt.setString(4, session.getAttribute("loginid")+""); 
            pstmt.setString(5, pensionphase); 
            
            insertStatus = pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertTransactionDetails", "AppletAuthorityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "insertTransactionDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertTransactionDetails", "AppletAuthorityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "insertTransactionDetails");
        } finally {
            try {
                if (con != null) {
                    DBConnection.closeConnection(con);
                    DBConnection.closeStatement(pstmt);


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return insertStatus;
    }

    public String getPensionPhase(DataSource ds, String person_code) throws SADAREMDBException, SQLException {
        String pensionphase = "";
        String sql = null;
        //Statement st = null;
        Connection con = null;
        PreparedStatement pstmt = null ;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
          //  st = con.createStatement();

            sql = "select pensionphase from tblperson_personal_details   with (nolock) where person_code=?"; 
                        
            pstmt = con.prepareStatement(sql);            
            pstmt.setString(1, person_code);         
            
            rs = pstmt.executeQuery();
            if (rs.next()) {
                pensionphase = rs.getString(1);
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPensionPhase", "AppletAuthorityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getPensionPhase");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPensionPhase", "AppletAuthorityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getPensionPhase");
        } finally {
            try {
                DBConnection.closeConnection(con);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeResultSet(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pensionphase;
    }

    /**
     * This method is for Release the powerCut ids
     * @param DataSource
     * @param rquest
     **/
    public String getPowerCutDetails(DataSource ds, String transactionStatus, String sadaremCode, HttpServletRequest request) {

        String sql = null;
        ResultSet rs = null;
        Connection con = null;
       // Statement st = null;
        PreparedStatement pstmt = null ;
        String powerCutIds = null;
        HttpSession session = request.getSession();
        try {
            con = DBConnection.getConnection();
            //st = con.createStatement();
            
            sql = "select person_code from dbo.tblTransactional_Status A  where "
                    + "A.transaction_status!=? and A.person_code=?  ";
                   /* + "and DPC.DISTRICT_ID=? "
                    + "and campid=?";
  */
            
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, transactionStatus); 
            pstmt.setString(2, sadaremCode); /*
            pstmt.setString(3, session.getAttribute("districtId")+""); 
            pstmt.setString(4, session.getAttribute("campId")+""); */
           
            
            
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    powerCutIds = rs.getString(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    DBConnection.closeConnection(con);
                    DBConnection.closeStatement(pstmt);
                    DBConnection.closeResultSet(rs);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return powerCutIds;
    }

    /**
     * This method is to check disability details check
     * @param DataSource
     * @param rquest
     **/
    public String chkDisabilityDetails(DataSource ds, String sadaremCode, HttpServletRequest request)
    {

        String sql = null;
        ResultSet rs = null;
        Connection con = null;
       // Statement st = null;
        PreparedStatement pstmt = null ;
        String PartANormalFlow = null;
        HttpSession session = request.getSession();
        try 
        {
            con = DBConnection.getConnection();
            //
            
            sql = "select count(Person_code) from tblperson_personal_details  WITH(NOLOCK) where View_Edit_Mode='Edit' and status='Active' and ReasonforPersonStatus='Live' --and CategoryID='1' \n "
            		+ "and person_code=? \n";
            		
 
            
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, sadaremCode);    
           
            
            
            rs = pstmt.executeQuery();
            if (rs != null)
            {
                while (rs.next())
                {
                	PartANormalFlow = rs.getString(1);
                }
            }
            
            if(PartANormalFlow!=null && PartANormalFlow.equals("1"))
            {
            	sql =   "select count(*) COUNT From ( \n "
            			+ "select person_code from AppellateAuthorityandTemporary_RegistrationDetails  where person_code=? \n"
                		+ " )T\n"
                		+ "";
     
                
                pstmt = con.prepareStatement(sql);
                
                pstmt.setString(1, sadaremCode);
                
                rs = pstmt.executeQuery();
                if (rs != null)
                {
                    while (rs.next())
                    {
                    	PartANormalFlow = rs.getString(1);
                    }
                }
                if(PartANormalFlow.equals("0"))
                {
                	PartANormalFlow="1";
                }
                else
                {
                	PartANormalFlow="2";
                }
            }
            else
            {
            	PartANormalFlow="2";
            }

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        finally
        {
            try
            {
                if (con != null)
                {
                    DBConnection.closeConnection(con);
                    DBConnection.closeStatement(pstmt);
                    DBConnection.closeResultSet(rs);

                }
            } 
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return PartANormalFlow;
    }
    

    /*
     * This method is for deleting the powerCut ids
     * @param Datasource
     * @param personCode
     * @param request
     * @return int
     * @Exception
     **/
    public int powerCutIds(DataSource ds, String personCode, HttpServletRequest request) throws SADAREMDBException, SQLException {

        String sql = null;
        Connection con = null;
       // Statement st = null;
        PreparedStatement pstmt = null ;
        HttpSession session = request.getSession(); 
        CallableStatement calSt = null;
        int calResult = 0;
        int insertStatus = 0;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        try {/* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, personCode);

            con = DBConnection.getConnection();
         //   st = con.createStatement();


            /** Delete date from all DataBase Tables */
            calSt = con.prepareCall("{Call SP_DeleteDatainAllTables_for_UpdateDisability_Transaction(?)}");
            calSt.setString(1, personCode);
            calResult = calSt.executeUpdate();

            if (Integer.parseInt(details[0].toString()) == 1) {
                sql = "update tblPerson_personal_details set View_Edit_Mode='Edit' where person_code=? and District_id=? and camp_id=?";
                

                pstmt = con.prepareStatement(sql);                
                pstmt.setString(1, personCode); 
                pstmt.setString(2, session.getAttribute("districtId")+""); 
                pstmt.setString(3, session.getAttribute("campId")+""); 
                
                insertStatus = pstmt.executeUpdate();
                
            } else if (Integer.parseInt(details[0].toString()) == 2 || Integer.parseInt(details[0].toString()) == 3) 
            {
            	
                sql = "update AppellateAuthorityandTemporary_RegistrationDetails set ViewEditMode='Edit',status='Active' FROM AppellateAuthorityandTemporary_RegistrationDetails a CROSS APPLY DATA_PERSON_CODE(A.PERSON_CODE) DPC where person_code=? and DPC.DISTRICT_ID=? and categoryid=? and categorycount=?";
                
                
                pstmt = con.prepareStatement(sql);
                
                pstmt.setString(1, personCode); 
                pstmt.setString(2, session.getAttribute("districtId")+""); 
                pstmt.setString(3, details[0].toString()); 
                pstmt.setString(4, details[1].toString()); 
            
                
                
                
                insertStatus = pstmt.executeUpdate();
            }

        } catch (SQLException sqlEx) 
        {
        	sqlEx.printStackTrace();
        	
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "powerCutIds", "AppletAuthorityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "powerCutIds");
        } 
        catch (Exception sqlEx) 
        {
        	sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "powerCutIds", "AppletAuthorityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "powerCutIds");
        } 
        finally 
        {
            try {
                if (con != null) {
                    DBConnection.closeConnection(con);
                    DBConnection.closeStatement(calSt);
                    DBConnection.closeStatement(pstmt);


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return calResult;
    }

    public boolean isEligibleforPhysicalRequirements(DataSource ds, String person_code, HttpServletRequest request) throws SADAREMDBException, SQLException {


        String sql = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        HttpSession session = request.getSession();
        boolean exi = false;
        ResultSet rs = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        try {
            con = DBConnection.getConnection();
            pstmt = con.prepareStatement("select * from tblperson_personal_details p   with (nolock) join  tblPerson_Disability_Details d on(p.person_code=d.person_code) "
                    + " join tblPerson_Disability_TotalValue_Details t on(p.person_code=t.person_code)"
                    + //  " join tbl_Person_Disability_Cando_Details c on(p.person_code=c.person_code)" +
                    " where p.person_code=?  and p.view_edit_mode='View'  "
                    + //  " and c.s_can is null and c.s_can is null and c.f_can is null and c.pp_can is null and c.l_can is null " +
                    //   "and c.kc_can is null and c.b_can is null and c.st_can is " +
                    //  "null and c.w_can is null and c.rw_can is null and  c.h_can is null and c.se_can is null " +
                    " and p.status='Active' and d.status='Active' and t.status='Active'  and t.totaldisability>=40"
                    + " and d.disability_id!=5");//and c.status='Active'
            pstmt.setString(1, person_code);
            rs = pstmt.executeQuery();
            if (rs.next()) {

                pstmt = con.prepareStatement("select * from tbl_Person_Disability_Cando_Details where s_can is null "
                        + "and s_can is null and f_can is null and pp_can is null and l_can is null and kc_can is null and "
                        + "b_can is null and st_can is null and w_can is null and rw_can is null and  h_can is null and se_can"
                        + " is null and person_code=?");
                pstmt.setString(1, person_code);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    exi = true;
                } else {
                    request.setAttribute("msg", "Person has physical Requirements!!!");
                }

            } else {
                request.setAttribute("msg", "Personcode is not eligible for physical Requirements!!!");
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "isEligibleforPhysicalRequirements", "AppletAuthorityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "isEligibleforPhysicalRequirements");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "isEligibleforPhysicalRequirements", "AppletAuthorityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "isEligibleforPhysicalRequirements");
        } finally {
            try {
                if (con != null) {
                    DBConnection.closeConnection(con);
                    DBConnection.closeStatement(pstmt);

                    DBConnection.closeResultSet(rs);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return exi;

    }

    public void insertPhysicalRequirements(DataSource ds, String person_code, HttpServletRequest request) throws SADAREMDBException, SQLException {


        String sql = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        HttpSession session = request.getSession();
        boolean exi = false;
        ResultSet rs = null;
        ResultSet rs1 = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        try {
            con = DBConnection.getConnection();
            pstmt = con.prepareStatement("select d.disability_id, t.totaldisability from tblperson_personal_details p   with (nolock) join  tblPerson_Disability_Details d on(p.person_code=d.person_code) "
                    + " join tblPerson_Disability_TotalValue_Details t on(p.person_code=t.person_code)"
                    + //   " join tbl_Person_Disability_Cando_Details c on(p.person_code=c.person_code)" +
                    " where p.person_code=?  and p.view_edit_mode='View'  "
                    + //    "  and c.s_can is null and c.s_can is null and c.f_can is null and c.pp_can is null and c.l_can is null and c.kc_can is null and c.b_can is null and c.st_can is null and c.w_can is null and c.rw_can is null and  c.h_can is null and c.se_can is null " +
                    " and p.status='Active' and d.status='Active' and t.status='Active' ");//and c.status='Active' ");

            pstmt.setString(1, person_code);
            rs = pstmt.executeQuery();
            if (rs.next()) {

                pstmt1 = con.prepareStatement("select * from  dbo.Certificate_With_PhysicalRequirements_RegistrationDetails"
                        + " where person_code=? ");
                pstmt1.setString(1, person_code);
                rs1 = pstmt1.executeQuery();
                if (rs1.next()) {
                    request.setAttribute("msg", "Already Registered for Physical Requirements!!!");

                } else {


                    pstmt = con.prepareStatement("insert into Certificate_With_PhysicalRequirements_RegistrationDetails(person_code,disabilityid,percentage,createddate,"
                            + " updateddate,status,loginid) values(?,?,?,GETDATE(),GETDATE(),'Active',?)");
                    pstmt.setString(1, person_code);
                    pstmt.setString(2, rs.getString(1));
                    pstmt.setString(3, rs.getString(2));
                    pstmt.setString(4, session.getAttribute("loginid").toString());
                    if (pstmt.executeUpdate() > 0) {
                        request.setAttribute("msg", "Registered Succesfully!!!");
                    } else {
                        request.setAttribute("msg", "Not Registered Succesfully!!!");
                    }

                }


            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPhysicalRequirements", "AppletAuthorityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "insertPhysicalRequirements");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPhysicalRequirements", "AppletAuthorityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "insertPhysicalRequirements");
        } finally {
            try {
                if (con != null) {
                    DBConnection.closeConnection(con);
                    DBConnection.closeStatement(pstmt);
                    DBConnection.closeStatement(pstmt1);

                    DBConnection.closeResultSet(rs);
                    DBConnection.closeResultSet(rs1);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList getAppletAuthorityDetailsPrints(DataSource ds, String sadaremCode, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ArrayList<HashMap> personalDetails = new ArrayList<HashMap>();
        String sql = null;
        ResultSet rs = null;
        Connection con = null;
       // Statement st = null;
        PreparedStatement pstmt = null ;
        
        HttpSession session = request.getSession();
        try {
            con = DBConnection.getConnection();
           // st = con.createStatement();
            sql = "select coalesce(ppd.pensioncard_no,'-'),ppd.surname+ space(2) + ppd.first_name,ppd.relation_name,"
                    + "case when ppd.gender=1 then 'Male' else 'Female' end as gender,ppd.age_years,"
                    + "case when ppd.religion='1' then 'Hindu' when ppd.religion='2' then 'Muslim' when ppd.religion='3' then 'Christian' "
                    + "when ppd.religion='4' then 'Sikh' when ppd.religion='5' then 'Jain' when ppd.religion='6' then 'Budhist' else 'Others' end as religion,"
                    + "case when ppd.caste='1' then 'ST' when ppd.caste='2' then 'SC' when ppd.caste='3' then 'BC' when ppd.caste='4' then 'OC' "
                    + "when ppd.caste='5' then 'Minority' else 'NA' end as caste,coalesce(ppd.rationcard_number,''),"
                    + "case when pd.disability_id='1' then 'Locomotor/OH' when pd.disability_id='2' then 'Visual Impairment' "
                    + "when pd.disability_id='3' then 'Hearing Impairment'when pd.disability_id='4' then 'Mental Retardation' "
                    + "when pd.disability_id='5' then 'Mental Illness' else 'Multiple Disabilities' end as disability,ppd.mole_one,"
                    + "ppd.mole_two,ppd.person_code,CONVERT(VARCHAR(10), ppd.date_of_birth, 103) as date_of_birth,"
                    + "case when ppd.person_status='Eligible' then 'checked' else 'unchecked' end as person_statusEli,"
                    + "case when ppd.person_status='Rejected' then 'checked' else 'unchecked' end as person_statusRej,"
                    + "dd.district_name,md.mandal_name,vd.village_name,hd.habitation_name,ppd.rationcard_slno,ppd.person_status from tblPerson_personal_details PPD "
                    + " join dbo.AppellateAuthorityandTemporary_RegistrationDetails ATR on(ppd.person_code=ATR.person_code) "
                    + " join tblPerson_Disability_Details pd  on(ppd.person_code=pd.person_code) "
                    + " join tblDistrict_details DD on(ppd.district_id=dd.district_id) "
                    + " join tblMandal_details MD on(ppd.district_id=dd.district_id and ppd.mandal_id=md.mandal_id and dd.district_id=md.district_id) "
                    + " join tblVillage_details VD on(vd.village_id=ppd.village_id and ppd.district_id=vd.district_id and ppd.mandal_id=vd.mandal_id) "
                    + " join tblHabitation_details HD on(hd.district_id=ppd.district_id and hd.mandal_id=ppd.mandal_id and hd.village_id=ppd.village_id and hd.habitation_id=ppd.habitation_id) "
                    + "where ppd.person_code=? and ppd.district_id=?"
                    + " and  ATR.categoryid=? and pd.status='Active' and ppd.status='Active'";   
            
            
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, sadaremCode);
            pstmt.setString(2, session.getAttribute("districtId")+"");
            pstmt.setString(3, session.getAttribute("catid").toString());
            

            rs = pstmt.executeQuery(); 
            
            if (rs != null) {
                while (rs.next()) {
                    HashMap data = new HashMap();
                    data.put("pension_id", rs.getString(1));
                    data.put("name", rs.getString(2));
                    data.put("relation_name", rs.getString(3));
                    data.put("gender", rs.getString(4));
                    data.put("age", rs.getString(5));
                    data.put("religion", rs.getString(6));
                    data.put("caste", rs.getString(7));
                    data.put("rationcard", rs.getString(8));
                    data.put("disability", rs.getString(9));
                    data.put("moleOne", rs.getString(10));
                    data.put("moleTwo", rs.getString(11));
                    data.put("sadaremCode", rs.getString(12));
                    data.put("dob", rs.getString(13));
                    data.put("person_statusEli", rs.getString(14));
                    data.put("person_statusRej", rs.getString(15));
                    data.put("district_name", rs.getString(16));
                    data.put("mandal_name", rs.getString(17));
                    data.put("village_name", rs.getString(18));
                    data.put("habitation_name", rs.getString(19));
                    data.put("rationcard_slno", rs.getString(20));
                    data.put("personstatusPrint", rs.getString(21));
                    personalDetails.add(data);
                }
            }
            session.removeAttribute("catid");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    DBConnection.closeConnection(con);
                    DBConnection.closeStatement(pstmt);
                    DBConnection.closeResultSet(rs);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return personalDetails;
    }

    public ArrayList getAllDataReport(DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ArrayList details = new ArrayList();
        ResultSet rs = null;
        Connection con = null;
        CallableStatement callableStatement = null;
        HttpSession session = request.getSession();
        try {
            con = DBConnection.getConnection();
            callableStatement = con.prepareCall("{Call TOTALPERSONALDATA(?)}");
            callableStatement.setString(1, session.getAttribute("districtId").toString());
            rs = callableStatement.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    HashMap data = new HashMap();
                    data.put("SADAREM ID", rs.getString(1));
                    data.put("Pension ID", rs.getString(2));
                    data.put("Name", rs.getString(3));
                    data.put("Gender", rs.getString(4));
                    data.put("Caste", rs.getString(5));
                    data.put("Age", rs.getString(6));
                    data.put("Marital Status", rs.getString(7));
                    data.put("Relation Name", rs.getString(8));
                    data.put("Education", rs.getString(9));
                    data.put("Rationcard", rs.getString(10));
                    data.put("Phase", rs.getString(11));

                    data.put("Mandal", rs.getString(13));
                    data.put("Village", rs.getString(14));
                    data.put("PWD Status", rs.getString(17));
                    data.put("Assessement Status", rs.getString(18));
                    data.put("Disability", rs.getString(20));
                    data.put("Disability %", rs.getString(28));
                    data.put("Medical Board", rs.getString(22));
                    data.put("Medical Board Address", rs.getString(23));
                    data.put("Assessed Doctor", rs.getString(24));
                    data.put("Doctor RegNo", rs.getString(25));
                    data.put("Doctor Designation", rs.getString(26));
                    data.put("Assessed Date", rs.getString(27));

                    details.add(data);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    DBConnection.closeConnection(con);
                    DBConnection.closeStatement(callableStatement);
                    DBConnection.closeResultSet(rs);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return details;
    }

    public ArrayList getAllPersonalData(DataSource ds, AssessedPWDDetailsDTO assessedPWDDetailsDTO) throws SADAREMDBException, SQLException {
        ArrayList details = new ArrayList();
        ResultSet rs = null;
        Connection con = null;
        StringBuffer sb = new StringBuffer();
        Statement st = null; 
        PreparedStatement pstmt = null;
        Date campdate;
        String startDate = "";
        String endDate = "";
        try {

            con = DBConnection.getConnection();
         //   st = con.createStatement();
            campdate = new SimpleDateFormat("dd/mm/yyyy").parse(assessedPWDDetailsDTO.getFromdate());
            startDate = new SimpleDateFormat("mm/dd/yyyy").format(campdate);
            campdate = new SimpleDateFormat("dd/mm/yyyy").parse(assessedPWDDetailsDTO.getTodate());
            endDate = new SimpleDateFormat("mm/dd/yyyy").format(campdate);
            sb.append("select coalesce(nullif(s.PERSONCODE,''),'-'),coalesce(nullif(s.PENSIONID,''),'-'),coalesce(nullif(s.PERSONNAME,''),'-'),coalesce(nullif(s.GENDER,''),'-'),coalesce(nullif(s.CASTE,''),'-'),"
                    + "coalesce(nullif(s.AGE,''),'-'),coalesce(nullif(s.MARITALSTATUS,''),'-'),coalesce(nullif(s.RELATIONNAME,''),'-'),coalesce(nullif(s.EDUCATION,''),'-'),coalesce(nullif(s.RATIONCARD,''),'-'),"
                    + "coalesce(nullif(s.DISABILITY,''),'-'),s.PERCENTAGE,coalesce(nullif(s.PENSIONPHASE,''),'-'),coalesce(nullif(d.District_Name,''),'-'),coalesce(nullif(b.Mandal_Name,''),'-'),"
                    + "coalesce(nullif(e.Village_Name,''),'-')"
                    + ",coalesce(nullif(s.Habitationid,''),'-'),coalesce(nullif(s.MEDICALBOARD,''),'-'),coalesce(nullif(s.MEDICALBOARDADDRESS,''),'-'),coalesce(nullif(s.DOCTOR,''),'-'),coalesce(nullif(s.REGNUMBER,''),'-'),"
                    + "coalesce(nullif(s.DESIGNATION,''),'-'),coalesce(nullif(s.ASSESSEDDATE,''),'-'),coalesce(nullif(s.REASONFORPERSONSTATUS,''),'-'),coalesce(nullif(s.CATEGORYID,''),'-'),"
                    + "coalesce(nullif(s.Login_ID,''),'-') from dbo.Totalassesseddata s "
                    + "join tblDistrict_Details d on(s.districtid =d.district_id) "
                    + "join tblmandal_Details b on (s.districtid =b.district_id) and (s.MANDALID =b.mandal_id) "
                    + "join tblVillage_Details e on (s.districtid =e.district_id) and (s.MANDALID =e.mandal_id) and (s.VILLAGEID =e.village_id) "
                    + "where DATEADD(DD,0,DATEDIFF(DD,0,ASSESSEDDATE)) BETWEEN ? AND ? ");

            if (assessedPWDDetailsDTO.getDistrict_id() != null) {
                sb.append("  and s.districtid = ?");
            }
            if (assessedPWDDetailsDTO.getMandal_id() != null && !assessedPWDDetailsDTO.getMandal_id().equals("0")) {
                sb.append("  and s.MANDALID = ?");
            }
            if (assessedPWDDetailsDTO.getVillage_id() != null && !assessedPWDDetailsDTO.getVillage_id().equals("0")) {
                sb.append("  and s.VILLAGEID = ?");
            }

            if (assessedPWDDetailsDTO.getPensionPhase() != null && !assessedPWDDetailsDTO.getPensionPhase().equals("0")) {
                sb.append("  and s.PENSIONPHASE = ?");
            } 

            pstmt=con.prepareStatement(sb.toString());
            
           // System.out.println("query==>"+sb.toString());
            
            int y=1;     
            
            pstmt.setString(y, startDate); y++;
            pstmt.setString(y, endDate);  y++;
            

            if (assessedPWDDetailsDTO.getDistrict_id() != null) 
               {            	
              
            			pstmt.setString(y, assessedPWDDetailsDTO.getDistrict_id()); y++;
            
               }
            if (assessedPWDDetailsDTO.getMandal_id() != null && !assessedPWDDetailsDTO.getMandal_id().equals("0")) 
                {
                	
                	 	pstmt.setString(y,  assessedPWDDetailsDTO.getMandal_id()); 	y++;
            		
            	}
            if (assessedPWDDetailsDTO.getVillage_id() != null && !assessedPWDDetailsDTO.getVillage_id().equals("0")) 
            	{             
            	
            	 		pstmt.setString(y, assessedPWDDetailsDTO.getVillage_id()); 	y++;
            	 
            	}
            if (assessedPWDDetailsDTO.getPensionPhase() != null && !assessedPWDDetailsDTO.getPensionPhase().equals("0")) 
            	{
                
            	 		pstmt.setString(y, assessedPWDDetailsDTO.getPensionPhase()); 	y++;
            	 
            	}
             
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    HashMap data = new HashMap();
                    data.put("PERSONCODE", rs.getString(1));
                    data.put("PENSIONID", rs.getString(2));
                    data.put("PERSONNAME", rs.getString(3));
                    data.put("GENDER", rs.getString(4));
                    data.put("CASTE", rs.getString(5));
                    data.put("AGE", rs.getString(6));
                    data.put("MARITALSTATUS", rs.getString(7));
                    data.put("RELATIONNAME", rs.getString(8));
                    data.put("EDUCATION", rs.getString(9));
                    data.put("RATIONCARD", rs.getString(10));
                    data.put("DISABILITY", rs.getString(11));
                    if (rs.getString(12) == null) {
                        data.put("PERCENTAGE", 0);
                    } else {
                        data.put("PERCENTAGE", rs.getString(12));
                    }
                    data.put("PENSIONPHASE", rs.getString(13));
                    data.put("district", rs.getString(14));
                    data.put("MANDAL", rs.getString(15));
                    data.put("VILLAGE", rs.getString(16));
                    data.put("Habitation", rs.getString(17));
                    data.put("MEDICALBOARD", rs.getString(18));
                    data.put("MEDICALBOARDADDRESS", rs.getString(19));
                    data.put("DOCTOR", rs.getString(20));
                    data.put("REGNUMBER", rs.getString(21));
                    data.put("DESIGNATION", rs.getString(22));
                    data.put("ASSESSEDDATE", rs.getString(23));
                    data.put("REASONFORPERSONSTATUS", rs.getString(24));
                    data.put("CATEGORY", rs.getString(25));
                    data.put("Login_ID", rs.getString(26));

                    details.add(data); 
                  //  System.out.print("details-=-=>"+details);
                }
            }

        } catch (SQLException sqlEx) {
        	sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAllPersonalData", "AppletAuthorityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getAllPersonalData");
        } catch (Exception sqlEx) {
        	sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAllPersonalData", "AppletAuthorityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AppletAuthorityDAO", "getAllPersonalData");
        } finally {
            try {
                if (con != null) {
                    DBConnection.closeConnection(con);
                   DBConnection.closeStatement(pstmt);
                    DBConnection.closeResultSet(rs);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return details;
    }
}
