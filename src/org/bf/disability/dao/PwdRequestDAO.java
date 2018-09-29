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

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.AppealPersonalDataDTO;
import org.bf.disability.dto.PwdRequestDTO;
import org.bf.disability.form.PwdRequestForm;

import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 693461
 */
public class PwdRequestDAO {

    public ArrayList getPwdRequestList(DataSource ds, String requestTypeId) throws SADAREMDBException, SQLException {
        ArrayList requestList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        PwdRequestDTO pwdRequestDTO = null;
        HashMap details = null;

        try {
            con = DBConnection.getConnection();
            query = "select requestTypeId,RequestTypeName from tblRequestTypeMaster where requestgroupid =? and status = 'Active'";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, requestTypeId);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    pwdRequestDTO = new PwdRequestDTO();
                    pwdRequestDTO.setRequestTypeId(rs.getString(1));
                    pwdRequestDTO.setRequestTypeName(rs.getString(2));
                    requestList.add(pwdRequestDTO);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPwdRequestList", "PwdRequestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "getPwdRequestList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPwdRequestList", "PwdRequestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "getPwdRequestList");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return requestList;
    }

    public PwdRequestDTO checkSadarermIDNo(DataSource ds, String personCode) throws SADAREMDBException, SQLException {

        int checkSadarermIDNo = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        PwdRequestDTO pwdRequestDTO = new PwdRequestDTO();
        int c = 0;
        String queryData = null;

        try {
            con = DBConnection.getConnection();
            query = "select count (a.person_code) from tblperson_personal_Details a   with (nolock) ,dbo.tblPerson_Disability_Details b , dbo.tblPerson_Disability_TotalValue_Details c   where a.person_code=b.person_code and a.person_code = c.person_code "
                    + " and a.status ='Active' and b.status = 'Active' and c.status = 'Active' "
                    + " and a.person_code = ?";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personCode);
            rs = pstmt.executeQuery();


            if (rs.next()) {
                checkSadarermIDNo = rs.getInt(1);


                pwdRequestDTO.setSadaremValidMsg(checkSadarermIDNo);
                c = checkSadarermIDNo;
                if (c == 0) {System.out.println("1");

                    query = "select  a.reasonforpersonstatus from tblperson_personal_Details a   with (nolock) ,dbo.tblPerson_Disability_Details b ,"
                            + " dbo.tblPerson_Disability_TotalValue_Details c   where a.person_code=b.person_code "
                            + " and a.person_code = c.person_code and a.status <>'Active'"
                            + " and b.status <> 'Active' and c.status <> 'Active'"
                            + " and a.person_code = ?";


                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, personCode);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        queryData = rs.getString(1);

                        pwdRequestDTO.setSadaremIdErrorMsg(queryData);

                    } else {

                        pwdRequestDTO.setSadaremIdErrorMsg("Invalid SADAREMID");
                    }
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkSadarermIDNo", "PwdRequestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "checkSadarermIDNo");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkSadarermIDNo", "PwdRequestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "checkSadarermIDNo");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return pwdRequestDTO;
    }

    public PwdRequestDTO checkSadarermIDNoPersoncode(DataSource ds, String personCode) throws SADAREMDBException, SQLException {

        int checkSadarermIDNo = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        String query = null;
        String queryData = null;
        int c = 0;
        PwdRequestDTO pwdRequestDTO = new PwdRequestDTO();

        try {
            con = DBConnection.getConnection();
            query = "select count (a.person_code) from tblperson_personal_Details a  with (nolock) ,dbo.tblPerson_Disability_Details b , dbo.tblPerson_Disability_TotalValue_Details c   where a.person_code=b.person_code and a.person_code = c.person_code "
                    + " and a.status ='Active' and b.status = 'Active' and c.status = 'Active' "
                    + " and a.person_code = ?";


            pstmt = con.prepareStatement(query);
            pstmt.setString(1,personCode);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                checkSadarermIDNo = rs.getInt(1);

                pwdRequestDTO.setValidMsg(checkSadarermIDNo);
                c = checkSadarermIDNo;
                if (c == 0) {

                    query = "select  a.reasonforpersonstatus from tblperson_personal_Details a   with (nolock) ,dbo.tblPerson_Disability_Details b ,"
                            + " dbo.tblPerson_Disability_TotalValue_Details c   where a.person_code=b.person_code "
                            + " and a.person_code = c.person_code and a.status <>'Active'"
                            + " and b.status <> 'Active' and c.status <> 'Active'"
                            + " and a.person_code = ?";


                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, personCode);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        queryData = rs.getString(1);

                        pwdRequestDTO.setInvalidErrorMsg(queryData);



                    } else {

                        pwdRequestDTO.setInvalidErrorMsg("Invalid SADAREMID");
                    }
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkSadarermIDNoPersoncode", "PwdRequestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "checkSadarermIDNoPersoncode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkSadarermIDNoPersoncode", "PwdRequestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "checkSadarermIDNoPersoncode");
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);
            DBConnection.closeResultSet(rs1);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeStatement(pstmt1);
			DBConnection.closeConnection(con); 
        } 
        return pwdRequestDTO;
    }

    public AppealPersonalDataDTO getPersonalDetailsForAppealAuthority(DataSource ds, String pid) throws SADAREMDBException, SQLException {


        CallableStatement calstmt = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        PreparedStatement stmt = null;
        String reason = null;
        String view = null;
        String personstatus = null;
        ResultSet rs1 = null;
        //   int count = 0;
        PreparedStatement stmt1 = null;
        ResultSet rr = null;

        // String Status = partadto.getPersonstatus();
        // PreparedStatement ps = null;
        ResultSet rs = null;
        // HashMap h = new HashMap();
        AppealPersonalDataDTO dto = new AppealPersonalDataDTO();
        String errormsg = "NO";
        boolean dataexists = false;
        boolean allowtemporary = false;
        String name = null;
        boolean exi = false;
        boolean ex = false;
        String query = "";
        String applieddate = null;
        String ass_date = null;
        //  PreparedStatement ps1=null;
        // ResultSet rs2=null;
        try {
            dto.setError_msg(errormsg);
            con = DBConnection.getConnection();
            int maxcount = 0;
            stmt1 = con.prepareStatement("select * from AppellateAuthorityandTemporary_RegistrationDetails where Person_Code=? and categoryid=3");
            stmt1.setString(1, pid);
            rs1 = stmt1.executeQuery();
            if (rs1.next()) {
                allowtemporary = true;
            }
            stmt1 = con.prepareStatement("select max(CategoryCount) from AppellateAuthorityandTemporary_RegistrationDetails where Person_Code=? and categoryid=2");
            stmt1.setString(1, pid);
            rs1 = stmt1.executeQuery();
            if (rs1.next()) {
                maxcount = rs1.getInt(1);
            }
            if (maxcount != 0) {
                query = " and CategoryCount=" + maxcount;
            }
            stmt1 = con.prepareStatement("select * from AppellateAuthorityandTemporary_RegistrationDetails where Person_Code=? and ViewEditMode='Edit' and categoryid=2 " + query);
            stmt1.setString(1, pid);
            rr = stmt1.executeQuery();
            if (rr.next()) {
                ass_date = rr.getString("updateddate");
                exi = true;
            } else {
                stmt1 = con.prepareStatement("select * from AppellateAuthorityandTemporary_RegistrationDetails "
                        + "where Person_Code=? and ViewEditMode='View' and DATEDIFF(YEAR,UPDATEDDATE, GETDATE())<1  and categoryid=2" + query);      // DATEDIFF(YEAR, GETDATE(),UPDATEDDATE)<= 1");
                stmt1.setString(1, pid);
                rr = stmt1.executeQuery();
                if (rr.next()) {
                    ex = true;
                }
            }
            
            CommonDAOImpl comObj = new CommonDAOImpl();
            HashMap GEODtls = new HashMap();
  			GEODtls=comObj.getGEODetailsbySADAREMID(pid);
  			String distIdFromSadaremId	=	GEODtls.get("districtid").toString();
  			String mandIdFromSadaremId	=	GEODtls.get("mandalid").toString();
  			String vilIdFromSadaremId	=	GEODtls.get("villageid").toString();
  			String habIdFromSadaremId	=	GEODtls.get("habtationid").toString();
            
            if (exi || ex) {
                if (ex) {
                    stmt1 = con.prepareStatement("select updateddate + 365 from AppellateAuthorityandTemporary_RegistrationDetails "
                            + "where person_code=? and categoryid=2" + query);
                    stmt1.setString(1, pid);
                    rr = stmt1.executeQuery();
                    if (rr.next()) {
                        applieddate = rr.getString(1);
                    }
                    String formdate = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd ").parse(applieddate));
                    //errormsg="Already registered for Appellate Authority and you can apply again after one year as per your first registration. you may able to apply again on or after "+formdate ;
                    //Already appellate authority assessment completed and you can apply again after one year from your previous appellate assessment date. You may able to apply again on or after
                    errormsg = "Already appellate authority assessment completed and you can apply again after one year from your previous appellate assessment date. You may able to apply again on or after " + formdate;
                    dto.setError_msg(errormsg);

                } else if (exi) {

                    stmt1 = con.prepareStatement("select schedule_date from Appeal_Authority_Schedule where  "
                            + "district_id =? and mandal_id=?"
                            + "and village_id=? and schedule_date>=GETDATE()");
                    
                    stmt1.setString(1, distIdFromSadaremId);
                    stmt1.setString(2, mandIdFromSadaremId);
                    stmt1.setString(3, vilIdFromSadaremId);
                    
                    rr = stmt1.executeQuery();
                    if (rr.next()) {
                        applieddate = rr.getString(1);

                        String formdate = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd ").parse(applieddate));
                        errormsg = "Already Registered for Appealet Authority and your assessment date will be on " + formdate;
                        dto.setError_msg(errormsg);
                    } else {
                        stmt1 = con.prepareStatement("select schedule_date from Appeal_Authority_Schedule where  "
                                + "district_id =? and mandal_id=? "
                                + "and village_id=? and schedule_date<GETDATE()");
                        
                        stmt1.setString(1, distIdFromSadaremId);
                        stmt1.setString(2, mandIdFromSadaremId);
                        stmt1.setString(3, vilIdFromSadaremId);
                        
                        rr = stmt1.executeQuery();
                        if (rr.next()) {
                            applieddate = rr.getString(1);
                            String formdate = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(applieddate));
                            errormsg = "Already Registered and assessment not completed on the schedule date " + formdate + ", Reschedule date to be given for appellate authority Assessment";
                            dto.setError_msg(errormsg);
                        } else {
                            String formdate = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(ass_date));
                            errormsg = "Already Registered for Appealet Authority on " + formdate + " and your assessment date will be informed soon ";
                            dto.setError_msg(errormsg);
                        }
                    }
                }
            } else {
                pstmt = con.prepareStatement("select REASONFORPERSONSTATUS, VIEW_EDIT_MODE, PERSON_STATUS , first_name, surname from TBLPERSON_PERSONAL_DETAILS   with (nolock) "
                        + "where person_code=? ");
                pstmt.setString(1, pid);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    reason = rs.getString(1);
                    view = rs.getString(2);
                    personstatus = rs.getString(3);
                    name = rs.getString(5) + " " + rs.getString(4);
                    if (reason != null && (reason.equalsIgnoreCase("Dead") || reason.equalsIgnoreCase("Permanent Migration") || reason.equalsIgnoreCase("Duplicate"))) {
                        errormsg = name + " is " + reason + " and not Eligible for Appellate Authority";
                        //"Not eligible Due to "+reason;
                    } else if (personstatus != null && personstatus.equalsIgnoreCase("Rejected")) {
                        if (view != null && view.equalsIgnoreCase("Edit")) {
                            errormsg = name + " is " + reason + " and not Eligible for Appellate Authority";
                            //"Assesment not completed "+reason;
                        } else if (view != null && view.equalsIgnoreCase("View")) {
                            dataexists = true;
                        }
                    } else if (personstatus != null && personstatus.equalsIgnoreCase("Eligible")) {
                        stmt = con.prepareStatement("select totaldisability from dbo.tblPerson_Disability_TotalValue_Details "
                                + "where person_code=? and status='Active'");
                        stmt.setString(1, pid);
                        String dis = null;
                        float diss = 0;

                        rs1 = stmt.executeQuery();
                        if (rs1.next()) {
                            dis = rs1.getString(1);
                            if (dis != null) {
                                diss = Float.parseFloat(dis);
                            }
                            if (diss >= 40) {

                                errormsg = name + " is " + " not Eligible for  Appealet Authority due to above 40 percentage";

                            } else if (diss < 40) {
                                dataexists = true;
                            }
                        } else {
                            errormsg = " Assesment  not Completed ";
                        }


                    }
                    //  if(errormsg!=null)
                    dto.setError_msg(errormsg);
                    if (dataexists) {
                        String sql = null;
                        if (personstatus != null && personstatus.equalsIgnoreCase("Eligible")) {

                            sql = "SELECT P.PERSON_CODE,P.SURNAME + SPACE(2) + P.FIRST_NAME AS PERSONNAME, P.RELATION_NAME, "
                                    + "P.AGE_YEARS, CASE WHEN P.GENDER = '1' THEN 'Male' WHEN P.GENDER = '2' THEN 'FEMALE' ELSE 'NE' END AS GENDER, "
                                    + "P.PERSON_STATUS, P.VIEW_EDIT_MODE, PD.DISABILITY_ID, CASE WHEN PD.DISABILITY_ID = '1' THEN 'O.H' WHEN PD.DISABILITY_ID = '2' THEN 'V.I' "
                                    + "WHEN PD.DISABILITY_ID = '3' THEN 'H.I' WHEN PD.DISABILITY_ID = '4' THEN 'M.R' WHEN PD.DISABILITY_ID = '5' THEN 'M.I' "
                                    + "WHEN PD.DISABILITY_ID = '6' THEN 'MULTIPLE' ELSE 'NE' END AS DISABILITYNAME, PT.TOTALDISABILITY, M.MANDAL_NAME, V.VILLAGE_NAME, "
                                    + "P.RATIONCARD_NUMBER "
                                    + "FROM  TBLPERSON_PERSONAL_DETAILS P   with (nolock) ,"
                                    + "TBLPERSON_DISABILITY_DETAILS PD ,TBLPERSON_DISABILITY_TOTALVALUE_DETAILS PT ,TBLMANDAL_DETAILS M,TBLVILLAGE_DETAILS V "
                                    + "WHERE P.PERSON_CODE  = ?   AND P.PERSON_CODE  = PD.PERSON_CODE   AND P.PERSON_CODE  = PT.PERSON_CODE   AND PD.PERSON_CODE = PT.PERSON_CODE    AND "
                                    + "M.DISTRICT_ID  = P.District_ID AND M.MANDAL_ID    = P.Mandal_ID AND V.DISTRICT_ID  = P.District_ID AND V.MANDAL_ID    = P.Mandal_ID AND "
                                    + "V.VILLAGE_ID   = P.Village_ID AND P.STATUS = 'Active' AND PD.STATUS = 'Active'  AND PT.STATUS = 'Active' AND ";

                            sql = sql + "PT.TOTALDISABILITY <40";

                            pstmt = con.prepareStatement(sql);
                            pstmt.setString(1, pid);
                            rs = pstmt.executeQuery();

                            if (rs.next()) {
                                if (allowtemporary) {
                                    stmt = con.prepareStatement("select * from TBLPERSON_DISABILITY_DETAILS where  person_code=? and status='Active'");
                                } else //stmt = con.prepareStatement("select * from TBLPERSON_DISABILITY_DETAILS where Created_Date<='2011-07-21' and person_code=? and status='Active'");
                                {
                                    stmt = con.prepareStatement("select * from TBLPERSON_DISABILITY_DETAILS where person_code=? and status='Active'");
                                }

                                stmt.setString(1, pid);
                                rr = stmt.executeQuery();
                                if (rr.next()) {
                                    dto.setPersoncode(rs.getString(1));
                                    dto.setName(rs.getString(2));
                                    dto.setViewedit(rs.getString(7));
                                    dto.setPersonstatus(rs.getString(6));
                                    dto.setAge(rs.getString(4));
                                    dto.setRelationname(rs.getString(3));
                                    dto.setStatus(rs.getString(6));
                                    dto.setGender(rs.getString(5));
                                    //  dto.setPercentage(rs.getString(1));
                                    dto.setVillage(rs.getString(12));
                                    dto.setMandal(rs.getString(11));
                                    dto.setDisability(rs.getString(8));
                                    dto.setRationcard(rs.getString(13));

                                    dto.setPercentage(rs.getString(10));
                                } else {
                                    //  errormsg = "Appellate registration provision will be considered  for only from the appeals received among the suspended pensioners in the 1st August, 2011  "
                                    // + " acquittance, who were assessed before 21-07-2011.";
                                    //dto.setError_msg(errormsg);
                                }

                            } else {
                                errormsg = " Assesment  not Completed ";
                                dto.setError_msg(errormsg);

                            }


                        } else if (personstatus != null && personstatus.equalsIgnoreCase("Rejected")) {


                            sql = "SELECT P.PERSON_CODE,P.SURNAME + SPACE(2) + P.FIRST_NAME AS PERSONNAME, P.RELATION_NAME, "
                                    + "P.AGE_YEARS, CASE WHEN P.GENDER = '1' THEN 'Male' WHEN P.GENDER = '2' THEN 'FEMALE' ELSE 'NE'END AS GENDER,"
                                    + " P.PERSON_STATUS, P.VIEW_EDIT_MODE, "
                                    + " PR.DISABILITY_ID, CASE WHEN PR.DISABILITY_ID = '1' THEN 'O.H' WHEN PR.DISABILITY_ID = '2' THEN 'V.I' WHEN PR.DISABILITY_ID = '3' THEN 'H.I' "
                                    + "WHEN PR.DISABILITY_ID = '4' THEN 'M.R' WHEN PR.DISABILITY_ID = '5' THEN 'M.I' WHEN PR.DISABILITY_ID = '6' THEN 'MULTIPLE' "
                                    + "ELSE 'NE' END AS DISABILITYNAME, M.MANDAL_NAME, V.VILLAGE_NAME, P.RATIONCARD_NUMBER  FROM TBLPERSON_PERSONAL_DETAILS P   with (nolock) , "
                                    + "TBLREJECTPERSON_DETAILS    PR, TBLMANDAL_DETAILS M, TBLVILLAGE_DETAILS V WHERE "
                                    + "P.PERSON_CODE  = ?  AND "
                                    + " P.PERSON_CODE  = PR.PERSON_CODE           AND "
                                    + " M.DISTRICT_ID  = P.District_ID AND "
                                    + " M.MANDAL_ID    = P.Mandal_ID AND "
                                    + " V.DISTRICT_ID  = P.District_ID AND AND "
                                    + " V.MANDAL_ID    = P.Mandal_ID AND AND "
                                    + " V.VILLAGE_ID   = P.Village_ID AND "
                                    + " P.STATUS       = 'Active'                 AND "
                                    + " PR.STATUS      = 'Active'   ";


                            pstmt = con.prepareStatement(sql);
                            pstmt.setString(1, pid);
                            rs = pstmt.executeQuery();
                            if (rs.next()) {
                                if (allowtemporary) {
                                    stmt = con.prepareStatement("select * from TBLREJECTPERSON_DETAILS where  person_code=? and status='Active'");
                                } else //  stmt = con.prepareStatement("select * from TBLREJECTPERSON_DETAILS where Created_Date<='2011-07-21' and person_code=? and status='Active'");
                                {
                                    stmt = con.prepareStatement("select * from TBLREJECTPERSON_DETAILS where person_code=? and status='Active'");
                                }
                                stmt.setString(1, pid);
                                rr = stmt.executeQuery();
                                if (rr.next()) {

                                    dto.setPersoncode(rs.getString(1));
                                    dto.setName(rs.getString(2));
                                    dto.setViewedit(rs.getString(7));
                                    dto.setPersonstatus(rs.getString(6));
                                    dto.setAge(rs.getString(4));
                                    dto.setRelationname(rs.getString(3));
                                    // dto.setStatus(rs.getString(7));
                                    dto.setGender(rs.getString(5));
                                    dto.setStatus(rs.getString(6));
                                    //  dto.setPercentage(rs.getString(1));
                                    dto.setVillage(rs.getString(11));
                                    dto.setMandal(rs.getString(10));
                                    dto.setDisability(rs.getString(8));
                                    dto.setRationcard(rs.getString(12));
                                    dto.setPercentage("0");

                                } else {
                                    //errormsg = "Appellate registration provision will be considered  for only from the appeals received among the suspended pensioners in the 1st August, 2011  "
                                    //      + " acquittance, who were assessed before 21-07-2011.";
                                    //dto.setError_msg(errormsg);
                                }

                            } else {
                                errormsg = " Assesment  not Completed ";
                                dto.setError_msg(errormsg);

                            }

                        }

                    }

                } else {
                    errormsg = " Assesment  not Completed ";
                    dto.setError_msg(errormsg);
                }

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDetailsForAppealAuthority", "PwdRequestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "getPersonalDetailsForAppealAuthority");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDetailsForAppealAuthority", "PwdRequestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "getPersonalDetailsForAppealAuthority");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeResultSet(rr);
            DBConnection.closeResultSet(rs1);
            DBConnection.closeStatement(calstmt);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(stmt1);



        }
        return dto;
    }

    public PwdRequestDTO tempararyDetails(DataSource ds, String personCode) throws SADAREMDBException, SQLException {

        PwdRequestDTO tempararyDetails = new PwdRequestDTO();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        String category = null;
        String status = null;
        int totaldisability = 0;
        int alreadyExist = 0;
        String personstatus = null;
        ResultSet rs = null;
        String errormsg = "NO";
        AppealPersonalDataDTO dto = new AppealPersonalDataDTO();

        try {
            dto.setError_msg(errormsg);
            con = DBConnection.getConnection();

            pstmt = con.prepareStatement("select categoryID,status,ReasonforPersonStatus from tblperson_personal_Details  with (nolock) where person_code=?");

            pstmt.setString(1,personCode);
            rs = pstmt.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    category = rs.getString("categoryID");

                    status = rs.getString("status");
                    personstatus = rs.getString("ReasonforPersonStatus");

                }
            }

            // pstmt = con.prepareStatement("select totaldisability from dbo.tblPerson_Disability_TotalValue_Details where person_code='" + personCode + "'");

            String query1 = "select totaldisability from dbo.tblPerson_Disability_TotalValue_Details where person_code=? and status ='Active'";

            pstmt = con.prepareStatement(query1);
            pstmt.setString(1, personCode);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    totaldisability = rs.getInt("totaldisability");
                    ;
                }
            }

            pstmt = con.prepareStatement("select count(*) from dbo.AppellateAuthorityandTemporary_RegistrationDetails where categoryid ='3' and person_code=? and status='Active'");

            pstmt.setString(1, personCode);

            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    alreadyExist = rs.getInt(1);

                }
            }
            if (!category.equalsIgnoreCase("3")) {
                errormsg = "Not valid for Renewal";
                //dto.setError_msg(errormsg);
                tempararyDetails.setTemparyPersonCodeId(errormsg);
            } else {
                if (status.equalsIgnoreCase("Inactive") || personstatus.equalsIgnoreCase("Dead") || personstatus.equalsIgnoreCase("Permanent Migration")) {
                    errormsg = "Not eligible for Renewal as you are " + personstatus;


                    // dto.setError_msg(errormsg);
                    //tempararyDetails.setTemparyPersonCodeId(errormsg);
                    tempararyDetails.setTemparyerrormsg(errormsg);
                } else if (totaldisability > 40) {
                    errormsg = "Not eligible for Renewal as you are permanent";

                    // dto.setError_msg(errormsg);
                    // tempararyDetails.setTemparyPersonCodeId(errormsg);
                    tempararyDetails.setTemparyerrormsg(errormsg);
                } else if (alreadyExist > 0) {
                    errormsg = "Not eligible for Renewal as you are already registered";

                    //dto.setError_msg(errormsg);
                    //tempararyDetails.setTemparyPersonCodeId(errormsg);
                    tempararyDetails.setTemparyerrormsg(errormsg);
                } else {
                    query = "select e.person_code  from  tblperson_personal_Details E  with (nolock) "
                            + " join tblPerson_Causes_of_Disability_Details b on E.person_code=b.person_code "
                            + " where view_edit_mode='View' and b.categoryid ='3' and b.status='Active' "
                            + " and b.person_code=? and years_for_temparary<>'0'"
                            + " and (datediff(dd, b.created_date,getdate())/365)=(select years_for_temparary from "
                            + " tblPerson_Causes_of_Disability_Details bc where b.person_code=bc.person_code "
                            + " and b.categoryid ='3' and bc.status='Active')";


                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, personCode);
                    rs = pstmt.executeQuery();
                    if (rs != null) {
                        while (rs.next()) {
                            tempararyDetails.setTemparyPersonCodeId(rs.getString(1));
                        }
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "tempararyDetails", "PwdRequestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "tempararyDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "tempararyDetails", "PwdRequestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "tempararyDetails");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return tempararyDetails;
    }

    public PwdRequestDTO duplicateCertificateDetails(DataSource ds, String personCode) throws SADAREMDBException, SQLException {

        String duplicateCertificate = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        String errormsg = "NO";
        AppealPersonalDataDTO dto = new AppealPersonalDataDTO();
        PwdRequestDTO pwdRequestDTO = new PwdRequestDTO();

        try {

            con = DBConnection.getConnection();
            query = "select  distinct c.person_code from tblPerson_Causes_of_Disability_Details b,"
                    + " dbo.AppellateAuthorityandTemporary_RegistrationDetails c,tblRejectPerson_Details d "
                    + " where (datediff(dd, b.created_date,getdate())/365)= b.years_for_temparary "
                    + " and b.status='Active'and b.categoryid ='3' "
                    + " and b.person_code = c.person_code "
                    + " and c.ViewEditMode ='View' and   c.person_code =? "
                    + " union select b.person_code from tblRejectPerson_Details d,tblPerson_Causes_of_Disability_Details b,"
                    + " AppellateAuthorityandTemporary_RegistrationDetails c "
                    + " where  b.person_code =d.person_code and b.categoryid ='3' and c.ViewEditMode ='View' and c.ViewEditMode ='View' "
                    + " and (datediff(dd, b.created_date,getdate())/365)= b.years_for_temparary and b.person_code = c.person_code and c.person_code ='" + personCode + "'";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personCode);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                duplicateCertificate = rs.getString(1);

            } else {
                errormsg = "Not eligible for Duplicated as your temporary certificate expired";
                pwdRequestDTO.setError_msg(errormsg);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "duplicateCertificateDetails", "PwdRequestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "duplicateCertificateDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "duplicateCertificateDetails", "PwdRequestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "duplicateCertificateDetails");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return pwdRequestDTO;

    }

    public boolean physicalImpairment(DataSource ds, String personCode) throws SADAREMDBException, SQLException {

        boolean physicalImpairment = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        String errormsg = "NO";
        AppealPersonalDataDTO dto = new AppealPersonalDataDTO();

        try {
            dto.setError_msg(errormsg);
            con = DBConnection.getConnection();
            query = "select a.person_code from tbl_Person_Disability_Cando_Details a,tblPerson_Disability_Details b ,tblPerson_Disability_TotalValue_Details c,"
                    + " tblperson_personal_details d  with (nolock) where a.person_code = b.person_code and a.person_code = c.person_code"
                    + " and a.person_code = d.person_code and a.s_can is null and a.s_can is null and a.f_can is null "
                    + " and a.pp_can is null and a.l_can is null and a.kc_can is null and a.b_can is null "
                    + " and a.st_can is null and a.w_can is null and a.rw_can is null and  a.h_can is null "
                    + " and a.se_can is null  and b.status='Active' and c.status='Active' and d.status='Active'"
                    + " and d.view_edit_mode='View' and a.person_code = ?";


            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personCode);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    physicalImpairment = true;
                }
            } else {
                errormsg = "Not eligible for physical Impairment";
                dto.setError_msg(errormsg);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "physicalImpairment", "PwdRequestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "physicalImpairment");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "physicalImpairment", "PwdRequestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "physicalImpairment");
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return physicalImpairment;
    }

    public String getrequestMaxId(DataSource ds) throws SADAREMDBException, SQLException {
        String requestMaxId = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            query = "select max(requestid) from requestdetails";

            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    requestMaxId = rs.getString(1);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getrequestMaxId", "PwdRequestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "getrequestMaxId");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getrequestMaxId", "PwdRequestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "getrequestMaxId");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return requestMaxId;
    }

    public int insertRequestInformationDetails(DataSource ds, PwdRequestForm pwdRequestForm, String systemIp) throws SADAREMDBException, SQLException {

        int requestInformationDetails = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        String maxQuery = null;
        ResultSet rs = null;
        String requestMaxId = null;
        String DateOfBirth = null;

        PwdRequestDTO pwdRequestDTO = new PwdRequestDTO();

        try {
            con = DBConnection.getConnection();

            query = "insert into  requestDetails (Person_Code,SurName,Name,RelationName,DistrictId,MandalId,VillageId,HabitationId,"
                    + " HouseNo,Mobile,Email,SystemIp,Created_Date,Updated_Date,Status) "
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?,getDate(),getDate(),'Active')";


            pstmt = con.prepareStatement(query);

            if (pwdRequestForm.getSadaremID() != null && !pwdRequestForm.getSadaremID().equals("")) {
                pstmt.setString(1, pwdRequestForm.getSadaremID());
            } else if (pwdRequestForm.getRequestDetailsId() != null && pwdRequestForm.getRequestDetailsId().equals("6")) {

                pstmt.setString(1, null);
            }
            if (pwdRequestForm.getSadaremIDDetails() != null && !pwdRequestForm.getSadaremIDDetails().equals("")) {
                pstmt.setString(1, pwdRequestForm.getSadaremIDDetails());
            } else if (pwdRequestForm.getRequestDetailsId() != null && pwdRequestForm.getRequestDetailsId().equals("6")) {
                //pstmt.setString(1,"NULL");
            }


            if (pwdRequestForm.getSurName1() != null && !pwdRequestForm.getSurName1().equals("")) {
                pstmt.setString(2, pwdRequestForm.getSurName1());

            } else if (pwdRequestForm.getHouseNo3() != null && pwdRequestForm.getHouseNo3().equals("")) {
                pstmt.setString(2, pwdRequestForm.getHouseNo3());


            } else if (pwdRequestForm.getRequestsurName() != null && !pwdRequestForm.getRequestsurName().equals("")) {
                pstmt.setString(2, pwdRequestForm.getRequestsurName());

            }

            if (pwdRequestForm.getName1() != null && !pwdRequestForm.getName1().equals("")) {
                pstmt.setString(3, pwdRequestForm.getName1());
            } else if (pwdRequestForm.getHouseNo2() != null && !pwdRequestForm.getHouseNo2().equals("")) {
                pstmt.setString(3, pwdRequestForm.getHouseNo2());
            } else if (pwdRequestForm.getRequestName() != null && !pwdRequestForm.getRequestName().equals("")) {
                pstmt.setString(3, pwdRequestForm.getRequestName());

            }

            if (pwdRequestForm.getRelationName1() != null && !pwdRequestForm.getRelationName1().equals("")) {
                pstmt.setString(4, pwdRequestForm.getRelationName1());
            } else if (pwdRequestForm.getHouseNo1() != null && !pwdRequestForm.getHouseNo1().equals("")) {
                pstmt.setString(4, pwdRequestForm.getHouseNo1());
            } else if (pwdRequestForm.getRequestRelationName() != null && !pwdRequestForm.getRequestRelationName().equals("")) {
                pstmt.setString(4, pwdRequestForm.getRequestRelationName());

            }


            pstmt.setString(5, pwdRequestForm.getDistrict_id());
            pstmt.setString(6, pwdRequestForm.getMandal_id());
            pstmt.setString(7, pwdRequestForm.getVillage_id());
            pstmt.setString(8, pwdRequestForm.getHabitation_id());
            pstmt.setString(9, pwdRequestForm.getHouseNo());

            pstmt.setString(10, pwdRequestForm.getContactNo());
            pstmt.setString(11, pwdRequestForm.getEmail());
            pstmt.setString(12, systemIp);

            requestInformationDetails = pstmt.executeUpdate();

            if (requestInformationDetails > 0) {

                maxQuery = "select max(requestid) from requestdetails";
                pstmt = con.prepareStatement(maxQuery);
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        requestMaxId = rs.getString(1);
                    }
                }

                if (!pwdRequestForm.getDateOfBirth().equals("")) {

                    Date DOB = new SimpleDateFormat("dd/mm/yyyy").parse(pwdRequestForm.getDateOfBirth());
                    DateOfBirth = new SimpleDateFormat("mm/dd/yyyy").format(DOB);
                }

                if (pwdRequestForm.getRequestId() != null && pwdRequestForm.getPersonal().equals("1")) {
                    query = "insert into  RequestTypeMapping "
                            + "(RequestId, RequestTypeId,SurName,Name,RelationName,MolesOne,MolesTwo,DateOfBirth,RequestDistrictId,RequestMandalId,RequestVillageId,RequestHabitationId,RequestHouseNo,RequestPinCode,RequestStatus)"
                            + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,'Pending')";

                    pstmt = con.prepareStatement(query);

                    pstmt.setString(1, requestMaxId);

                    pwdRequestDTO.setRequestmaxId(requestMaxId);


                    for (int i = 0; i < pwdRequestForm.getRequestId().length; i++) {

                        pstmt.setString(2, pwdRequestForm.getRequestId()[i]);
                        pstmt.setString(3, pwdRequestForm.getRequestsurName());
                        pstmt.setString(4, pwdRequestForm.getRequestName());
                        pstmt.setString(5, pwdRequestForm.getRequestRelationName());
                        pstmt.setString(6, pwdRequestForm.getRequestMoles());
                        pstmt.setString(7, pwdRequestForm.getRequestMolesTwo());
                        pstmt.setString(8, DateOfBirth);
                        pstmt.setString(9, pwdRequestForm.getRequestDistrict_id());
                        pstmt.setString(10, pwdRequestForm.getRequestMandal_id());
                        pstmt.setString(11, pwdRequestForm.getRequestVillage_id());
                        pstmt.setString(12, pwdRequestForm.getRequestHabitation_id());
                        pstmt.setString(13, pwdRequestForm.getRequestHouseNo());
                        pstmt.setString(14, pwdRequestForm.getRequestPinCode());
                        requestInformationDetails += pstmt.executeUpdate();
                    }
                } else if (pwdRequestForm.getRequestDetailsId() != null && pwdRequestForm.getPersonal().equals("2")) {
                    query = "insert into  RequestTypeMapping (RequestId, RequestTypeId,RequestStatus) VALUES(?,?,'Pending')";



                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, requestMaxId);
                    pstmt.setString(2, pwdRequestForm.getRequestDetailsId());
                    requestInformationDetails += pstmt.executeUpdate();
                }
            } else {
                requestInformationDetails = 0;
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertRequestInformationDetails", "PwdRequestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "insertRequestInformationDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertRequestInformationDetails", "PwdRequestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "insertRequestInformationDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return requestInformationDetails;
    }

    public ArrayList getPanchayatDetails(DataSource ds, String districtId, String mandalId) throws SADAREMDBException, SQLException {

        ArrayList panchayatList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        PwdRequestDTO pwdRequestDTO = null;
        try {
            con = DBConnection.getConnection();
            query = "select Panchayat_ID ,Panchayat_Name from tblPanchayat_Details where District_ID=? "
                    + "and Mandal_ID=? order by Panchayat_Name";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtId);
            pstmt.setString(2, mandalId);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    pwdRequestDTO = new PwdRequestDTO();
                    pwdRequestDTO.setPanchayat_id(rs.getString(1));
                    pwdRequestDTO.setPanchaith(rs.getString(2));
                    panchayatList.add(pwdRequestDTO);

                }

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPanchayatDetails", "PwdRequestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "getPanchayatDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPanchayatDetails", "PwdRequestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "getPanchayatDetails");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return panchayatList;
    }

    public int insertRessessmentRequestInformationDetails(DataSource ds, PwdRequestForm pwdRequestForm, String systemIp) throws SADAREMDBException, SQLException {

        int resssementDetails = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        String maxQuery = null;
        ResultSet rs = null;
        String requestMaxId = null;
        String DateOfBirth = null;
        PwdRequestDTO pwdRequestDTO = new PwdRequestDTO();

        try {
            con = DBConnection.getConnection();


            query = "insert into  requestDetails (Person_Code,SurName,Name,RelationName,DistrictId,MandalId,VillageId,HabitationId,"
                    + " HouseNo,Mobile,Email,SystemIp,Created_Date,Updated_Date,Status) "
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?,getDate(),getDate(),'Active')";


            pstmt = con.prepareStatement(query);

            if (pwdRequestForm.getSadaremID() != null && !pwdRequestForm.getSadaremID().equals("")) {
                pstmt.setString(1, pwdRequestForm.getSadaremID());
            } else if (pwdRequestForm.getRequestDetailsId() != null && pwdRequestForm.getRequestDetailsId().equals("6")) {

                pstmt.setString(1, null);
            }
            if (pwdRequestForm.getSadaremIDDetails() != null && !pwdRequestForm.getSadaremIDDetails().equals("")) {
                pstmt.setString(1, pwdRequestForm.getSadaremIDDetails());
            } else if (pwdRequestForm.getRequestDetailsId() != null && pwdRequestForm.getRequestDetailsId().equals("6")) {
                //pstmt.setString(1,"NULL");
            }


            //pstmt.setString(2, pwdRequestForm.getSurName1());
            //pstmt.setString(3, pwdRequestForm.getName1());

            //pstmt.setString(4, pwdRequestForm.getRelationName1());

            pstmt.setString(2, pwdRequestForm.getHouseNo3());

            pstmt.setString(3, pwdRequestForm.getHouseNo2());

            pstmt.setString(4, pwdRequestForm.getHouseNo1());


            pstmt.setString(5, pwdRequestForm.getDistrict_id());
            pstmt.setString(6, pwdRequestForm.getMandal_id());
            pstmt.setString(7, pwdRequestForm.getVillage_id());
            pstmt.setString(8, pwdRequestForm.getHabitation_id());
            pstmt.setString(9, pwdRequestForm.getHouseNo());

            pstmt.setString(10, pwdRequestForm.getContactNo());
            pstmt.setString(11, pwdRequestForm.getEmail());
            pstmt.setString(12, systemIp);

            resssementDetails = pstmt.executeUpdate();

            if (resssementDetails > 0) {


                maxQuery = "select max(requestid) from requestdetails";
                pstmt = con.prepareStatement(maxQuery);
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        requestMaxId = rs.getString(1);
                    }
                }

                if (pwdRequestForm.getDateOfBirth() != null && !pwdRequestForm.getDateOfBirth().equals("")) {

                    Date DOB = new SimpleDateFormat("dd/mm/yyyy").parse(pwdRequestForm.getDateOfBirth());
                    DateOfBirth = new SimpleDateFormat("mm/dd/yyyy").format(DOB);
                }

                if (pwdRequestForm.getRequestId() != null && pwdRequestForm.getPersonal().equals("1")) {
                    query = "insert into  RequestTypeMapping "
                            + "(RequestId, RequestTypeId,SurName,Name,RelationName,MolesOne,MolesTwo,DateOfBirth,RequestDistrictId,RequestMandalId,RequestVillageId,RequestHabitationId,RequestHouseNo,RequestPinCode,RequestStatus)"
                            + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,'Pending')";

                    pstmt = con.prepareStatement(query);

                    pstmt.setString(1, requestMaxId);

                    pwdRequestDTO.setRequestmaxId(requestMaxId);



                    for (int i = 0; i < pwdRequestForm.getRequestId().length; i++) {

                        pstmt.setString(2, pwdRequestForm.getRequestId()[i]);
                        pstmt.setString(3, pwdRequestForm.getRequestsurName());

                        pstmt.setString(4, pwdRequestForm.getRequestName());

                        pstmt.setString(5, pwdRequestForm.getRequestRelationName());

                        pstmt.setString(6, pwdRequestForm.getRequestMoles());
                        pstmt.setString(7, pwdRequestForm.getRequestMolesTwo());
                        pstmt.setString(8, DateOfBirth);
                        pstmt.setString(9, pwdRequestForm.getRequestDistrict_id());
                        pstmt.setString(10, pwdRequestForm.getRequestMandal_id());
                        pstmt.setString(11, pwdRequestForm.getRequestVillage_id());
                        pstmt.setString(12, pwdRequestForm.getRequestHabitation_id());
                        pstmt.setString(13, pwdRequestForm.getRequestHouseNo());
                        pstmt.setString(14, pwdRequestForm.getRequestPinCode());
                        resssementDetails += pstmt.executeUpdate();
                    }
                } else if (pwdRequestForm.getRenualId() != null && !pwdRequestForm.getRenualId().equals("")) {
                    query = "insert into  RequestTypeMapping (RequestId, RequestTypeId,RequestStatus) VALUES(?,?,'Pending')";

                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, requestMaxId);
                    pstmt.setString(2, pwdRequestForm.getRenualId());
                    resssementDetails += pstmt.executeUpdate();
                } else if (pwdRequestForm.getReAssessmentId() != null && !pwdRequestForm.getReAssessmentId().equals("")) {
                    query = "insert into  RequestTypeMapping (RequestId, RequestTypeId,RequestStatus) VALUES(?,?,'Pending')";

                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, requestMaxId);
                    pstmt.setString(2, pwdRequestForm.getReAssessmentId());
                    resssementDetails += pstmt.executeUpdate();
                } else if (pwdRequestForm.getRequestDetailsId() != null && !pwdRequestForm.getPersonal().equals("")) {
                    query = "insert into  RequestTypeMapping (RequestId, RequestTypeId,RequestStatus) VALUES(?,?,'Pending')";

                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, requestMaxId);
                    pstmt.setString(2, pwdRequestForm.getRequestDetailsId());
                    resssementDetails += pstmt.executeUpdate();
                }


            } else {
                resssementDetails = 0;
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertRessessmentRequestInformationDetails", "PwdRequestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "insertRessessmentRequestInformationDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertRessessmentRequestInformationDetails", "PwdRequestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "insertRessessmentRequestInformationDetails");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return resssementDetails;

    }

    public PwdRequestDTO getReciptDetils(DataSource ds, String requestId) throws SADAREMDBException, SQLException {

        PwdRequestDTO pwdRequestDTO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        String requestTypeName = "";
        try {
            con = DBConnection.getConnection();
            query = "select a.requestid,coalesce(a.person_code,'---'),a.surname,a.name,a.relationname,a.houseno,c.District_Name,d.mandal_name,"
                    + " e.village_name,f.habitation_name,a.created_date,g.requesttypename from requestDetails a "
                    + " join  RequestTypeMapping b on(a.requestid = b.requestid)"
                    + " join tblDistrict_Details c on(c.District_ID =a.DistrictID)"
                    + " join tblMandal_Details d on(d.District_ID =a.DistrictID)"
                    + " and (d.mandal_id=a.mandalid) join tblvillage_Details e on(e.District_ID=a.Districtid) "
                    + " and (e.mandal_id=a.mandalid) and (e.village_id=a.villageid)"
                    + " join tblHabitation_Details f on(f.District_ID=a.DistrictID)"
                    + " and  (f.mandal_id=a.mandalid) and (f.village_id =a.villageid)"
                    + " and (f.habitation_id =a.habitationid)"
                    + " join tblrequesttypemaster g on (g.requesttypeid =b.requesttypeid)"
                    + " where a.requestid = ?";



            pstmt = con.prepareStatement(query);
            pstmt.setString(1, requestId);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    pwdRequestDTO = new PwdRequestDTO();
                    pwdRequestDTO.setReciptRequestId(rs.getString(1));
                    pwdRequestDTO.setReciptPersonCode(rs.getString(2));
                    pwdRequestDTO.setReciptSurName(rs.getString(3));
                    pwdRequestDTO.setReciptName(rs.getString(4));
                    pwdRequestDTO.setReciptRelationName(rs.getString(5));
                    pwdRequestDTO.setReciptHouseno(rs.getString(6));
                    pwdRequestDTO.setReciptDistrictName(rs.getString(7));
                    pwdRequestDTO.setReciptMandalName(rs.getString(8));
                    pwdRequestDTO.setReciptVillageName(rs.getString(9));
                    pwdRequestDTO.setReciptHabitationName(rs.getString(10));
                    String reqDate = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(11)));
                    pwdRequestDTO.setReciptRegisteratedDate(reqDate);
                    requestTypeName += rs.getString(12) + ",";
                    pwdRequestDTO.setReciptRequestTypeName(requestTypeName.substring(0, requestTypeName.length() - 1));
                }
            }




        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getReciptDetils", "PwdRequestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "getReciptDetils");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getReciptDetils", "PwdRequestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "getReciptDetils");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return pwdRequestDTO;
    }

    public int newCertificateDetails(DataSource ds, PwdRequestForm pwdRequestForm, String systemIp) throws SADAREMDBException, SQLException {

        int newCertificateDetails = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        String maxQuery = null;
        ResultSet rs = null;
        String requestMaxId = null;
        int requestInformationDetails = 0;
        String insertQuery = null;


        try {
            con = DBConnection.getConnection();

            insertQuery = "insert into  requestDetails (SurName,Name,RelationName,DistrictId,MandalId,VillageId,HabitationId,"
                    + " HouseNo,Mobile,Email,SystemIp,Created_Date,Updated_Date,Status) "
                    + " values(?,?,?,?,?,?,?,?,?,?,?,getDate(),getDate(),'Active')";


            pstmt = con.prepareStatement(insertQuery);

            pstmt.setString(1, pwdRequestForm.getSurname());
            pstmt.setString(2, pwdRequestForm.getFirstname());
            pstmt.setString(3, pwdRequestForm.getGsurname());
            pstmt.setString(4, pwdRequestForm.getDistricts_id());
            pstmt.setString(5, pwdRequestForm.getMandal_id());
            pstmt.setString(6, pwdRequestForm.getVillage_id());
            pstmt.setString(7, pwdRequestForm.getHabitation_id());
            pstmt.setString(8, pwdRequestForm.getHouseno());
            pstmt.setString(9, pwdRequestForm.getPhoneno());
            pstmt.setString(10, pwdRequestForm.getEmailId());
            pstmt.setString(11, systemIp);


            requestInformationDetails = pstmt.executeUpdate();

            if (requestInformationDetails > 0) {

                maxQuery = "select max(requestid) from requestdetails";
                pstmt = con.prepareStatement(maxQuery);
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        requestMaxId = rs.getString(1);
                    }
                }

                query = "insert into RequestTypeMapping (RequestId, RequestTypeId,RequestStatus) VALUES(?,?,'Pending')";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, requestMaxId);
                pstmt.setString(2, pwdRequestForm.getRequestFormId());
                requestInformationDetails = pstmt.executeUpdate();


            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "newCertificateDetails", "PwdRequestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "newCertificateDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "newCertificateDetails", "PwdRequestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "newCertificateDetails");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return requestInformationDetails;
    }

    public synchronized int partAInsertDetails(DataSource ds, PwdRequestForm pwdRequestForm, String documentPath, String addressPath, String loginId, String systemIp) throws SADAREMDBException, SQLException {

        int partAInsertDetails = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        String years = null;
        String month = null;
        String day = null;
        String DateOfBirth = null;
        String requestQuery = null;
        PreparedStatement requestPstmt = null;
        ResultSet requestRs = null;
        String requestmaxId = null;
        String insertQuery = null;
        int requestInformationDetails = 0;
        //Statement stmt = null;


        try {
            con = DBConnection.getConnection();

            Date campdate = new SimpleDateFormat("dd/mm/yyyy").parse(pwdRequestForm.getFromdate());
            String dateReg = new SimpleDateFormat("mm/dd/yyyy").format(campdate);

            years = pwdRequestForm.getYear();
            month = pwdRequestForm.getMonth();
            day = pwdRequestForm.getDay();
            DateOfBirth = years + "-" + month + "-" + day;

            requestQuery = "select max(requestid) from requestDetails";
            requestPstmt = con.prepareStatement(requestQuery);
            requestRs = requestPstmt.executeQuery();

            if (requestRs != null) {
                while (requestRs.next()) {
                    requestmaxId = requestRs.getString(1);
                }
            }

            

            query = "INSERT INTO tblperson_Request_Details "
                    + " (requestid,requesttypeid,Reference_Form_Number,Form_Fill_Date,"
                    + " Surname,First_Name,Gender,Date_of_Birth,Age_Years,Religion,"
                    + " Caste,Marital_Status,Education,Employment,Relationship,Relation_Name,"
                    + " RationCard_Type,RationCard_Number,rationcard_slno,EPIC_Card,EPICCard_No,"
                    + " Pension_Card,Pension_Type,PensionCard_No,Mole_One,Mole_Two,House_Number,"
                    + " Phone_No,Email,District_ID,Mandal_ID,Village_ID,Habitation_ID,Panchayat_ID,Pin_Code,"
                    + " Personname_Telugu,Fathername_Telugu,Person_Status,Parents_Marriage,Typeof_Disability,"
                    + " Existing_Percentage,Documents,AddressDocuments,Login_ID,System_IP_Address,Created_Date,Updated_Date,"
                    + " Status,UpdateStatus)"
                    + " VALUES(?,?,?,?,"
                    + " ?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,"
                    + "?,?,?,?,"
                    + "?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,"
                    + "?,?,'Eligible',?,?,?,?,?,?,?,"
                    + " getDate(),getDate(),'Active','Pending')";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, requestmaxId);
            pstmt.setString(2, pwdRequestForm.getRequestFormId());
            pstmt.setString(3, pwdRequestForm.getFormno());
            pstmt.setString(4, dateReg);
            pstmt.setString(5, pwdRequestForm.getSurname());
            pstmt.setString(6, pwdRequestForm.getFirstname());
            pstmt.setInt(7, pwdRequestForm.getGender());
            pstmt.setString(8, DateOfBirth);
            pstmt.setString(9, pwdRequestForm.getNoOfYears());
            pstmt.setInt(10, pwdRequestForm.getReligion());
            pstmt.setInt(11,  pwdRequestForm.getCaste());
            pstmt.setInt(12, pwdRequestForm.getMarital());
            pstmt.setInt(13, pwdRequestForm.getEducation());
            pstmt.setInt(14, pwdRequestForm.getEmployment());
            pstmt.setInt(15, pwdRequestForm.getGrelation());
            pstmt.setString(16, pwdRequestForm.getGsurname());
            pstmt.setInt(17, pwdRequestForm.getRtype());
            pstmt.setString(18, pwdRequestForm.getCard());
            pstmt.setInt(19, pwdRequestForm.getRationCardSlno());
            pstmt.setBoolean(20, pwdRequestForm.isEpiccard());
            pstmt.setString(21, pwdRequestForm.getEpiccardno());
            pstmt.setBoolean(22,  pwdRequestForm.isPensioncard());
            pstmt.setString(23, pwdRequestForm.getPension_type());
            pstmt.setString(24, pwdRequestForm.getPensioncardno());
            pstmt.setString(25, pwdRequestForm.getMole1());
            pstmt.setString(26, pwdRequestForm.getMole2());
            pstmt.setString(27, pwdRequestForm.getHouseno());
            pstmt.setString(28, pwdRequestForm.getPhoneno());
            pstmt.setString(29, pwdRequestForm.getEmailId());
            pstmt.setString(30, pwdRequestForm.getDistricts_id());
            pstmt.setString(31, pwdRequestForm.getMandal_id());
            pstmt.setString(32, pwdRequestForm.getVillage_id());
            pstmt.setString(33, pwdRequestForm.getHabitation_id());
            pstmt.setString(34, pwdRequestForm.getPanchayat_id());
            pstmt.setString(35, pwdRequestForm.getPin());
            pstmt.setString(36, pwdRequestForm.getFirstnametelugu());
            pstmt.setString(37,  pwdRequestForm.getTelugufathername());
            pstmt.setBoolean(38, pwdRequestForm.isParents_marriage());
            pstmt.setInt(39, pwdRequestForm.getType_disability());
            pstmt.setInt(40, pwdRequestForm.getExistingpercentage());
            pstmt.setString(41, documentPath);
            pstmt.setString(42, addressPath);
            pstmt.setString(43, loginId);
            pstmt.setString(44, systemIp);
            
           

            partAInsertDetails = pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "partAInsertDetails", "PwdRequestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "partAInsertDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "partAInsertDetails", "PwdRequestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "partAInsertDetails");
        } finally {
        	 DBConnection.closeResultSet(requestRs);
             DBConnection.closeStatement(requestPstmt);
 			DBConnection.closeStatement(pstmt);
 			DBConnection.closeConnection(con); 
        }

        return partAInsertDetails;
    }

    public ArrayList getTORequestNameDetails(DataSource ds) throws SADAREMDBException, SQLException {

        ArrayList toList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;


        try {
            con = DBConnection.getConnection();

            query = "select emailId from DistrictEmailIdsDetails where flag = 'NR' and role ='TO'";


            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    toList.add(rs.getString(1));
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTORequestNameDetails", "PwdRequestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "getTORequestNameDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTORequestNameDetails", "PwdRequestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "getTORequestNameDetails");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return toList;

    }

    public ArrayList getCCRequestNameDetails(DataSource ds, String districtId) throws SADAREMDBException, SQLException {

        ArrayList ccList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        String personCode = null;
        // String districtId = null;

        try {
            con = DBConnection.getConnection();

            query = "select emailid from DistrictEmailIdsDetails where flag = 'NR' and role ='CC' and districtid =?";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtId);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    ccList.add(rs.getString(1));
                }

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCCRequestNameDetails", "PwdRequestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "getCCRequestNameDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCCRequestNameDetails", "PwdRequestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "getCCRequestNameDetails");
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return ccList;
    }

    public ArrayList getPwdBodyDetails(DataSource ds, String personCode) throws SADAREMDBException, SQLException {

        ArrayList bodyDetailsList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        ArrayList pwdBodyList = null;

        try {
            con = DBConnection.getConnection();
            query = "select  distinct a.person_code,b.pensionCard_no,a.name,a.relationname,a.houseno,c.district_name,"
                    + " d.mandal_name,e.village_name,f.habitation_name  from requestDetails a "
                    + " join tblPerson_Personal_Details b  with (nolock) on(a.person_code =b.person_code) "
                    + " join tblDistrict_Details c on(c.district_id =a.districtid)"
                    + " join tblmandal_Details d on(d.district_id=a.districtid and d.mandal_id =a.mandalid)"
                    + " join tblvillage_Details e on (a.districtid=e.district_id"
                    + " and a.mandalid =e.mandal_id and a.villageid=e.village_id)"
                    + " join tblhabitation_Details f on (a.districtid =f.district_id"
                    + " and a.mandalid =f.mandal_id and a.villageid =f.village_id"
                    + " and a.habitationid =f.habitation_id)"
                    + " where a.person_code  =? ";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personCode);
            rs = pstmt.executeQuery();

            if (rs != null) {

                while (rs.next()) {
                    pwdBodyList = new ArrayList();
                    pwdBodyList.add(rs.getString(1));
                    pwdBodyList.add(rs.getString(2));
                    pwdBodyList.add(rs.getString(3));
                    pwdBodyList.add(rs.getString(4));
                    pwdBodyList.add(rs.getString(5));
                    pwdBodyList.add(rs.getString(6));
                    pwdBodyList.add(rs.getString(7));
                    pwdBodyList.add(rs.getString(8));
                    pwdBodyList.add(rs.getString(9));
                    bodyDetailsList.add(pwdBodyList);

                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPwdBodyDetails", "PwdRequestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "getPwdBodyDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPwdBodyDetails", "PwdRequestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "getPwdBodyDetails");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return bodyDetailsList;
    }

    public PwdRequestDTO validRequestSADAREMID(DataSource ds, String personCode, String requesttypeId) throws SADAREMDBException, SQLException {

        int checkSadarermIDNo = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        PwdRequestDTO pwdRequestDTO = new PwdRequestDTO();
        int c = 0;
        String queryData = null;
        String countId = "1";

        try {
            con = DBConnection.getConnection();
            query = "select  c.requesttypename,a.person_code from  requestDetails a, "
                    + " requesttypemapping b,tblrequesttypemaster c "
                    + " where a.requestid =b.requestid and b.requesttypeid =c.requesttypeid and "
                    + " a.person_code =? and b.requesttypeid =? "
                    + " and b.updatedpersonStatus is null";



            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personCode);
            pstmt.setString(2, requesttypeId);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    pwdRequestDTO.setRequestCountName(rs.getString(1));
                    pwdRequestDTO.setRequestCountId(countId);

                }

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "validRequestSADAREMID", "PwdRequestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "validRequestSADAREMID");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "validRequestSADAREMID", "PwdRequestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PwdRequestDAO", "validRequestSADAREMID");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return pwdRequestDTO;

    }
}
