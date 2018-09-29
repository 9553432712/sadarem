/*
 * PartADAO.java
 *
 * Created on June 16, 2008, 2:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.AppealPersonalDataDTO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sgv.common.util.DBConnection;

/*
 * PartADAO.java
 *
 * Created on June 16, 2008, 2:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
/**
 *
 * @author demo
 */
/** Creates a new instance of PartADAO */
public class PartADAO1 {

    /**
     *
     * @param datasource
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public ArrayList getDisabilityList(DataSource ds) throws SADAREMDBException, SQLException {

        ArrayList disabilityList = new ArrayList();
        ResultSet rs = null;
        Statement stmt = null;
        Connection con = null;


        try {
            con = DBConnection.getConnection();

            stmt = con.createStatement();

            rs = stmt.executeQuery("select Disability_ID,Disability_Name from tblDisability_Details");

            while (rs.next()) {
                PartADTO partADTO = new PartADTO();
                partADTO.setDisabilityId(rs.getInt("Disability_ID"));
                partADTO.setDisabilityName(rs.getString("Disability_Name"));
                disabilityList.add(partADTO);
            }

        } catch (SQLException sqlException) {
            int exResult = ExceptionDAO.saveException(ds, sqlException.toString(), "getDisabilityList", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getDisabilityList");

        } catch (Exception e) {
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "getDisabilityList", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getDisabilityList");

        } finally {

            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);


        }
        return disabilityList;
    }
//railway

    public String getTotalDisExists(DataSource ds, String pension_id) throws SADAREMDBException, SQLException {

        ArrayList disabilityList = new ArrayList();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        PartADTO partADTO = null;
        PreparedStatement stmt1 = null;
        ResultSet rs1 = null;
        String exi = null;


        try {
            con = DBConnection.getConnection();

            stmt = con.prepareStatement("select * from dbo.tblPerson_Disability_TotalValue_Details where person_code=? and status='Active'");
            stmt.setString(1, pension_id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                exi = "active";
            } else {
                stmt = con.prepareStatement("select * from dbo.tblPerson_Disability_TotalValue_Details where person_code=? and status='Inactive'");
                stmt.setString(1, pension_id);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    exi = "inactive";
                }
            }



        } catch (SQLException sqlException) {
            int exResult = ExceptionDAO.saveException(ds, sqlException.toString(), "getTotalDisExists", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getTotalDisExists");

        } catch (Exception e) {
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "getTotalDisExists", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getTotalDisExists");

        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeResultSet(rs1);
            DBConnection.closeStatement(stmt1);
            DBConnection.closeConnection(con);
        }
        return exi;
    }

    public PartADTO getRailwayDoctorDetails(DataSource ds, String pension_id) throws SADAREMDBException, SQLException {

        ArrayList disabilityList = new ArrayList();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        PartADTO partADTO = null;


        try {
            con = DBConnection.getConnection();

            stmt = con.prepareStatement("select * from dbo.RailwaypassDoctor_Details where Person_Code=? and Status='Active'");
            stmt.setString(1, pension_id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String name = null;
                name = rs.getString("DoctorName");


                //  if(name.startsWith("Dr.")||name.startsWith("dr.")||name.contains("Dr.")||name.contains("dr.")){
                //   name=rs.getString("DoctorName").replace("Dr.", " ");}

                partADTO = new PartADTO();
                partADTO.setDoctor1name(name);
                partADTO.setDoctor1regnumber(rs.getString("RegistrationNO"));
                partADTO.setDoctor1designation(rs.getString("Designation"));
                partADTO.setRail(rs.getString("Railway_Certificate"));
                String fdate = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("created_date")));

                partADTO.setDateofissue(fdate);


                // partADTO.setPosition(rs.getString("position"));
            }
            if (partADTO == null) {
            }


            /*  stmt1 = con.prepareStatement("select Railway_Certificate from dbo.RailwaypassDoctor_Details where Person_Code=?");
            stmt1.setString(1, pension_id);
            rs1=stmt1.executeQuery();
            rs = stmt.executeQuery();
            if(rs.next()){
            while(rs1.next()){
            partADTO.setRail(rs1.getString(1));

            }
            }*/
        } catch (SQLException sqlException) {
            int exResult = ExceptionDAO.saveException(ds, sqlException.toString(), "getRailwayDoctorDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getRailwayDoctorDetails");

        } catch (Exception e) {
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "getRailwayDoctorDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getRailwayDoctorDetails");

        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);
        }
        return partADTO;
    }
//appeal authority

    public int insertPersonalDetailsForAppeal(DataSource ds, AppealPersonalDataDTO dto, String loginid) throws SADAREMDBException, SQLException {
        ArrayList disabilityList = new ArrayList();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        int i = 0; //byte category=0;
        byte dis = 0;
        byte count = 0;
        byte category = 2;
//,LoginID
        try {
            if (dto.getDisability() != null && dto.getDisability() != "") {
                dis = Byte.parseByte(dto.getDisability());
            }
            con = DBConnection.getConnection();//AppellateAuthorityandTemporary_RegistrationDetails

            stmt = con.prepareStatement("select max(CategoryCount) from AppellateAuthorityandTemporary_RegistrationDetails where Person_Code=?  and categoryid=2 ");
            stmt.setString(1, dto.getPersoncode());
            //stmt.setByte(2, dis);
            // stmt.setString(3, dto.getPersoncode());

            rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getByte(1);
                count++;
            } else {
                count = 1;
            }
            stmt = con.prepareStatement("insert into AppellateAuthorityandTemporary_RegistrationDetails (Person_Code,DisabilityID,Percentage,CategoryID,CategoryCount,DisabilityStatus,ViewEditMode,CreatedDate,UpdatedDate,Status, Loginid, RationCardNumber, SerialNumber) values(?,?,?,?,?,?,?,GETDATE(),GETDATE(),?,?,?,?)");
            //   stmt = con.prepareStatement("insert into AppellateAuthorityandTemporary_RegistrationDetails (Person_Code,DisabilityID,Percentage,DisabilityStatus,ViewEditMode,CreatedDate,UpdatedDate,Status) values(?,?,?,?,?,GETDATE(),GETDATE(),?)");
            stmt.setString(1, dto.getPersoncode());
            stmt.setByte(2, dis);
            stmt.setString(3, dto.getPercentage());
            //  if(dto.getCategoryid()!=null)
            // category=Byte.parseByte(dto.getCategoryid());
            stmt.setByte(4, category);
            stmt.setByte(5, count);
            stmt.setString(6, dto.getStatus());
            stmt.setString(7, "Edit");
            stmt.setString(8, "Inactive");
            stmt.setString(9, loginid);
            stmt.setString(10, dto.getRationcard());
            stmt.setString(11, dto.getSid());
            i = stmt.executeUpdate();
            if (i > 0) {
                i = 1;
            }
        } catch (SQLException sqlException) {
            int exResult = ExceptionDAO.saveException(ds, sqlException.toString(), "insertPersonalDetailsForAppeal", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "insertPersonalDetailsForAppeal");

        } catch (Exception e) {
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "insertPersonalDetailsForAppeal", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "insertPersonalDetailsForAppeal");

        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);
        }
        return i;
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
        int count = 0;
        PreparedStatement stmt1 = null;
        ResultSet rr = null;

        // String Status = partadto.getPersonstatus();

        ResultSet rs = null;
        HashMap h = new HashMap();
        AppealPersonalDataDTO dto = new AppealPersonalDataDTO();
        String errormsg = "NO";
        boolean dataexists = false;
        String name = null;
        boolean exi = false;
        boolean ex = false;
        String applieddate = null;
        String ass_date = null;
        try {
            dto.setError_msg(errormsg);
            con = DBConnection.getConnection();
            //and DATEDIFF(YEAR, GETDATE(),UPDATEDDATE)<=1


            // stmt1=con.prepareStatement("select max(CategoryCount) from AppellateAuthorityandTemporary_RegistrationDetails where Person_Code=?");
            stmt1 = con.prepareStatement("select * from AppellateAuthorityandTemporary_RegistrationDetails where Person_Code=? and ViewEditMode='Edit' and categoryid=2");
            stmt1.setString(1, pid);
            rr = stmt1.executeQuery();
            if (rr.next()) {


                ass_date = rr.getString("updateddate");

                exi = true;
            } else {
                stmt1 = con.prepareStatement("select * from AppellateAuthorityandTemporary_RegistrationDetails "
                        + "where Person_Code=? and ViewEditMode='View' and DATEDIFF(YEAR,UPDATEDDATE, GETDATE())<1  and categoryid=2");      // DATEDIFF(YEAR, GETDATE(),UPDATEDDATE)<= 1");
                stmt1.setString(1, pid);
                rr = stmt1.executeQuery();
                if (rr.next()) {
                    ex = true;


                }
            }


            if (exi || ex) {
                if (ex) {
                    stmt1 = con.prepareStatement("select updateddate + 365 from AppellateAuthorityandTemporary_RegistrationDetails "
                            + "where person_id=? and categoryid=2");
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
                    /*          stmt1=con.prepareStatement("select schedule_date from Appeal_Authority_Schedule where district_code in(select substring(person_code,1,2) from "+
                    "AppellateAuthorityandTemporary_RegistrationDetails where person_code=?) and mandal_code in(select substring(person_code,6,2) from "+
                    "AppellateAuthorityandTemporary_RegistrationDetails where person_code=?) and village_code in(select substring(person_code,8,3) from "+
                    "AppellateAuthorityandTemporary_RegistrationDetails where person_code=?)");
                     * 8/
                     */
                    stmt1 = con.prepareStatement("select schedule_date from Appeal_Authority_Schedule where  "
                            + "district_id =substring('" + pid + "',1,2) and mandal_id=substring('" + pid + "',6,2) "
                            + "and village_id=substring('" + pid + "',8,3) and schedule_date>=GETDATE()");



                    //     select schedule_date from Appeal_Authority_Schedule where
//district_code =substring('16213840040000082',1,2) and mandal_code=substring('16213840040000082',6,2)
//and village_code=substring('16213840040000082',8,3)



                    //  stmt1.setString(1, pid); stmt1.setString(2, pid); stmt1.setString(3, pid);

                    rr = stmt1.executeQuery();
                    if (rr.next()) {
                        applieddate = rr.getString(1);

                        String formdate = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd ").parse(applieddate));
                        errormsg = "Already Registered for Appealet Authority and your assessment date will be on " + formdate;
                        dto.setError_msg(errormsg);
                    } else {
                        stmt1 = con.prepareStatement("select schedule_date from Appeal_Authority_Schedule where  "
                                + "district_id =substring('" + pid + "',1,2) and mandal_id=substring('" + pid + "',6,2) "
                                + "and village_id=substring('" + pid + "',8,3) and schedule_date<GETDATE()");
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
                pstmt = con.prepareStatement("select REASONFORPERSONSTATUS, VIEW_EDIT_MODE, PERSON_STATUS , first_name, surname from TBLPERSON_PERSONAL_DETAILS  with (nolock) "
                        + "where person_code=? and updated_date<='2011-07-21' ");
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
                                + "where person_code=?");
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
                                errormsg =//name +"is " +reason +" and not Eligible for Appellate Authority";
                                        name + " is " + " not Eligible for  Appealet Authority due to above 40 percentage";

                            } else if (diss < 40) {
                                dataexists = true;
                            }
                        } else {
                            errormsg =//name +"is " +reason +" and not Eligible for Appellate Authority";
                                    " Assesment  not Completed ";
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
                                    + "FROM  TBLPERSON_PERSONAL_DETAILS P  with (nolock) ,"
                                    + "TBLPERSON_DISABILITY_DETAILS PD ,TBLPERSON_DISABILITY_TOTALVALUE_DETAILS PT ,TBLMANDAL_DETAILS M,TBLVILLAGE_DETAILS V "
                                    + "WHERE P.PERSON_CODE  = ?   AND P.PERSON_CODE  = PD.PERSON_CODE   AND P.PERSON_CODE  = PT.PERSON_CODE   AND PD.PERSON_CODE = PT.PERSON_CODE    AND "
                                    + "M.DISTRICT_ID  = SUBSTRING(P.HABCODE,1,2) AND M.MANDAL_ID    = SUBSTRING(P.HABCODE,6,2) AND V.DISTRICT_ID  = SUBSTRING(P.HABCODE,1,2) AND V.MANDAL_ID    = SUBSTRING(P.HABCODE,6,2) AND "
                                    + "V.VILLAGE_ID   = SUBSTRING(P.HABCODE,8,3) AND P.STATUS = 'Active' AND PD.STATUS = 'Active'  AND PT.STATUS = 'Active' AND "
                                    + "PT.TOTALDISABILITY <40";

                            pstmt = con.prepareStatement(sql);
                            pstmt.setString(1, pid);
                            rs = pstmt.executeQuery();

                            if (rs.next()) {
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
                                errormsg = " Assesment  not Completed ";
                                dto.setError_msg(errormsg);

                            }


                        } else if (personstatus != null && personstatus.equalsIgnoreCase("Rejected")) {


                            sql = "SELECT P.PERSON_CODE,P.SURNAME + SPACE(2) + P.FIRST_NAME AS PERSONNAME, P.RELATION_NAME, "
                                    + "P.AGE_YEARS, CASE WHEN P.GENDER = '1' THEN 'Male' WHEN P.GENDER = '2' THEN 'FEMALE' ELSE 'NE'END AS GENDER,"
                                    + " P.PERSON_STATUS, P.VIEW_EDIT_MODE, "
                                    + " PR.DISABILITY_ID, CASE WHEN PR.DISABILITY_ID = '1' THEN 'O.H' WHEN PR.DISABILITY_ID = '2' THEN 'V.I' WHEN PR.DISABILITY_ID = '3' THEN 'H.I' "
                                    + "WHEN PR.DISABILITY_ID = '4' THEN 'M.R' WHEN PR.DISABILITY_ID = '5' THEN 'M.I' WHEN PR.DISABILITY_ID = '6' THEN 'MULTIPLE' "
                                    + "ELSE 'NE' END AS DISABILITYNAME, M.MANDAL_NAME, V.VILLAGE_NAME, P.RATIONCARD_NUMBER  FROM TBLPERSON_PERSONAL_DETAILS P  with (nolock) , "
                                    + "TBLREJECTPERSON_DETAILS    PR, TBLMANDAL_DETAILS M, TBLVILLAGE_DETAILS V WHERE "
                                    + "P.PERSON_CODE  = ?  AND "
                                    + " P.PERSON_CODE  = PR.PERSON_CODE           AND "
                                    + " M.DISTRICT_ID  = SUBSTRING(P.HABCODE,1,2) AND "
                                    + " M.MANDAL_ID    = SUBSTRING(P.HABCODE,6,2) AND "
                                    + " V.DISTRICT_ID  = SUBSTRING(P.HABCODE,1,2) AND "
                                    + " V.MANDAL_ID    = SUBSTRING(P.HABCODE,6,2) AND "
                                    + " V.VILLAGE_ID   = SUBSTRING(P.HABCODE,8,3) AND "
                                    + " P.STATUS       = 'Active'                 AND "
                                    + " PR.STATUS      = 'Active'   ";


                            pstmt = con.prepareStatement(sql);
                            pstmt.setString(1, pid);
                            rs = pstmt.executeQuery();
                            if (rs.next()) {

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
                                errormsg = " Assesment  not Completed ";
                                dto.setError_msg(errormsg);

                            }





                        }

                        /* 
                        String gender=null; String disabilityid=null; String type=null; String mandal=null; String district=null; String village=null;

                        pstmt=con.prepareStatement("select * from TBLPERSON_PERSONAL_DETAILS "+
                        "where person_code=?  and status='active'");// and DATEDIFF(YEAR, GETDATE(),UPDATED_DATE)>=1
                        pstmt.setString(1, pid);rs=pstmt.executeQuery();
                        if(rs.next()){

                        dto.setError_msg("NO");
                        // disabilityid=rs.getString("typeof_disability");
                        gender=rs.getString("gender");
                        if(gender!=null && gender.equalsIgnoreCase("1"))
                        gender="Male";
                        else if(gender!=null && gender.equalsIgnoreCase("2"))
                        gender="Female";
                        else
                        gender="NE";


                        dto.setPersoncode(rs.getString("person_code"));
                        dto.setName((rs.getString("surname")+" "+rs.getString("first_name")));
                        dto.setViewedit(rs.getString("VIEW_EDIT_MODE"));
                        dto.setPersonstatus(rs.getString("PERSON_STATUS"));
                        dto.setAge(rs.getString("AGE_YEARS"));
                        dto.setRelationname(rs.getString("RELATION_NAME"));
                        // dto.setDisability(rs.getString("Typeof_Disability"));
                        dto.setStatus(rs.getString("PERSON_STATUS"));
                        dto.setGender(gender);
                        dto.setCategoryid(rs.getString("categoryid"));
                        district=rs.getString("district_id");
                        mandal=rs.getString("mandal_id");
                        village=rs.getString("village_id");
                        stmt=con.prepareStatement("select mandal_name from tblmandal_details where mandal_id=? and district_id=?");
                        stmt.setString(1, mandal);
                        stmt.setString(2, district);
                        rs1=stmt.executeQuery();
                        if(rs1.next())
                        dto.setMandal(rs1.getString(1));
                        stmt=con.prepareStatement("select village_name from tblvillage_details where village_id=? and mandal_id=? and district_id=?");
                        stmt.setString(1, village);
                        stmt.setString(2, mandal);
                        stmt.setString(3, district);
                        rs1=stmt.executeQuery();
                        if(rs1.next())
                        dto.setVillage(rs1.getString(1));
                        stmt=con.prepareStatement("select totaldisability from dbo.tblPerson_Disability_TotalValue_Details "+
                        "where person_code=?");
                        stmt.setString(1, pid);
                        rs1=stmt.executeQuery();
                        if(rs1.next())
                        dto.setPercentage(rs1.getString(1));

                        if(personstatus!=null && personstatus.equalsIgnoreCase("Eligible")){
                        stmt=con.prepareStatement("select * from dbo.tblPerson_Disability_Details "+
                        "where person_code=?  and status='active'");

                        stmt.setString(1, pid);
                        rs1=stmt.executeQuery();//Eligible
                        if(rs1.next()){
                        disabilityid=rs1.getString("disability_id");
                        dto.setDisability(disabilityid);
                        }else{
                        disabilityid="NULL";
                        dto.setDisability(disabilityid);
                        }


                        }else if(personstatus!=null && personstatus.equalsIgnoreCase("Rejected")){
                        // select * from tblRejectPerson_Details where person_code='14200010070107024' and status='active'
                        stmt=con.prepareStatement("select * from tblRejectPerson_Details where person_code=? and status='active'");

                        stmt.setString(1, pid);
                        rs1=stmt.executeQuery();//Eligible
                        if(rs1.next()){
                        disabilityid=rs1.getString("disability_id");
                        dto.setDisability(disabilityid);
                        }else{
                        disabilityid="NULL";
                        dto.setDisability(disabilityid);
                        errormsg=" Assesment  not Completed ";
                        dto.setError_msg(errormsg);
                        }
                        // else
                        //  dto.setDisability(rs.getString("Typeof_Disability"));

                        
                        }}else{
                        errormsg=" Assesment  not Completed ";
                        dto.setError_msg(errormsg);
                        // errormsg= name +" is "+"Not eligible.";

                        }

                         */

                    }

                } else {

                    /*   pstmt=con.prepareStatement("select REASONFORPERSONSTATUS, VIEW_EDIT_MODE, PERSON_STATUS , first_name, surname from TBLPERSON_PERSONAL_DETAILS "+
                    "where person_code=? ");
                    pstmt.setString(1, pid);
                    rs=pstmt.executeQuery();
                    if(rs.next()){

                    }*/

                    //  errormsg="Appellate authority registration will be considered on or before 21, July, 2011 assessment completed persons . <BR> Your SADAREM "
                    //   +" regular assessment completed after 21, July, 2011 and   your assessment date will be informed soon ";
                    errormsg = "Appellate registration provision will be considered  for only from the appeals received among the suspended pensioners in the 1st August, 2011  "
                            + " acquittance, who were assessed before 21-07-2011.";
                    dto.setError_msg(errormsg);
                }

            }

        } catch (SQLException sqlException) {
            int exResult = ExceptionDAO.saveException(ds, sqlException.toString(), "getPersonalDetailsForAppealAuthority", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getPersonalDetailsForAppealAuthority");

        } catch (Exception e) {
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "getPersonalDetailsForAppealAuthority", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getPersonalDetailsForAppealAuthority");

        } finally {

            DBConnection.closeResultSet(rs);
            DBConnection.closeResultSet(rs1);
            DBConnection.closeResultSet(rr);
            DBConnection.closeStatement(calstmt);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(stmt1);
            DBConnection.closeConnection(con);


        }
        return dto;
    }
    //  added method for geting hospital name and address

    /**
     *
     * @param datasource
     * @param campId
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public PartADTO getCampDetails(DataSource ds, int campId) throws SADAREMDBException, SQLException {

        ArrayList campList = new ArrayList();
        ResultSet rs = null;
        Statement stmt = null;
        Connection con = null;
        PartADTO partADTO = null;


        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select Name,Address,VenueName from tblCamp_Details where Camp_ID='" + campId + "'");
            while (rs.next()) {
                partADTO = new PartADTO();
                partADTO.setHospitalname(rs.getString("Name"));
                partADTO.setHospitaladdress(rs.getString("Address"));
                partADTO.setCamp_venue(rs.getString("VenueName"));
            }
        } catch (SQLException sqlException) {
            int exResult = ExceptionDAO.saveException(ds, sqlException.toString(), "getCampDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getCampDetails");

        } catch (Exception e) {
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "getCampDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getCampDetails");

        } finally {

            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);


        }
        return partADTO;
    }

    /**
     *
     * @param disabilityId
     * @param datasource
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public ArrayList getDisabilityLocoSubList(int disabilityId, DataSource ds) throws SADAREMDBException, SQLException {

        ArrayList disabilityLocoSubList = new ArrayList();

        ResultSet rs = null;
        Statement stmt = null;
        Connection con = null;


        try {
            con = DBConnection.getConnection();

            stmt = con.createStatement();
            rs = stmt.executeQuery("select Sub_Disability_ID,Sub_Disability_Name from tblSubDisability_Details where Disability_ID = '" + disabilityId + "'");
            while (rs.next()) {
                PartADTO partADTO = new PartADTO();
                partADTO.setDisabilityLocoSubId(rs.getInt("Sub_Disability_ID"));
                partADTO.setDisabilityLocoSubName(rs.getString("Sub_Disability_Name"));


                disabilityLocoSubList.add(partADTO);
            }

        } catch (SQLException sqlException) {
            int exResult = ExceptionDAO.saveException(ds, sqlException.toString(), "getDisabilityLocoSubList", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getDisabilityLocoSubList");

        } catch (Exception e) {
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "getDisabilityLocoSubList", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getDisabilityLocoSubList");

        } finally {

            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);


        }
        return disabilityLocoSubList;
    }

    /**
     *
     * @param disabilityId
     * @param datasource
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public ArrayList getDisabilityLocoSubSubList(int disabilityId, DataSource ds) throws SADAREMDBException, SQLException {

        ArrayList disabilityLocoSubSubList = new ArrayList();

        ResultSet rs = null;
        Statement stmt = null;
        Connection con = null;


        try {
            con = DBConnection.getConnection();

            stmt = con.createStatement();
            rs = stmt.executeQuery("select Sub_Sub_Disability_ID,Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Disability_ID = '" + disabilityId + "'");
            while (rs.next()) {
                PartADTO partADTO = new PartADTO();
                partADTO.setDisabilityLocoSubSubId(rs.getInt("Sub_Sub_Disability_ID"));
                partADTO.setDisabilityLocoSubSubName(rs.getString("Sub_Sub_Disability_Name"));
                disabilityLocoSubSubList.add(partADTO);
            }

        } catch (SQLException sqlException) {
            int exResult = ExceptionDAO.saveException(ds, sqlException.toString(), "getDisabilityLocoSubSubList", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getDisabilityLocoSubSubList");

        } catch (Exception e) {
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "getDisabilityLocoSubSubList", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getDisabilityLocoSubSubList");

        } finally {

            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);


        }
        return disabilityLocoSubSubList;
    }

    /**
     *
     * @param personcode
     * @param partadto
     * @param ds
     * @throws SADAREMDBException,SQLException
     */
    public void insertDisabilityDetails(String personcode, PartADTO partadto, DataSource ds) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        Connection con = null;

        String Status = partadto.getPersonstatus();


        Statement st = null;
        try {
            con = DBConnection.getConnection();
            //   con.setAutoCommit(false);
            st = con.createStatement();



            if (Status.equals("Eligible")) {
                calstmt = con.prepareCall("{Call SP_tblPerson_Disability_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                calstmt.setString(1, personcode);
                calstmt.setInt(2, partadto.getDisabilityId());
                calstmt.setString(3, partadto.getDisabilitySubIds());
                calstmt.setString(4, partadto.getDisabilitySubSubIds());
                calstmt.setString(5, partadto.getOthertypeofdisability());
                calstmt.setString(6, partadto.getDiagnosisofdisability());
                calstmt.setString(7, partadto.getHospitalname());
                calstmt.setString(8, partadto.getHospitaladdress());
                calstmt.setString(9, partadto.getDoctor1name());
                calstmt.setString(10, partadto.getDoctor1regnumber());
                calstmt.setString(11, partadto.getDoctor1designation());
                calstmt.setString(12, partadto.getDoctor2name());
                calstmt.setString(13, partadto.getDoctor2regnumber());
                calstmt.setString(14, partadto.getDoctor2designation());
                calstmt.setString(15, partadto.getDoctor3name());
                calstmt.setString(16, partadto.getDoctor3regnumber());
                calstmt.setString(17, partadto.getDoctor3designation());
                calstmt.setString(18, partadto.getCamp_venue());
                calstmt.setString(19, partadto.getLoginid());
                calstmt.setString(20, partadto.getSystemip());



                calstmt.executeUpdate();
                calstmt.close();

                calstmt = con.prepareCall("{Call SP_tblPerson_Causes_of_Disability_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                calstmt.setString(1, personcode);
                calstmt.setString(2, partadto.getConditiondisabilityIds());
                calstmt.setString(3, partadto.getConditionindisability());

                calstmt.setInt(4, Integer.parseInt(partadto.getYearsfortemporary()));
                calstmt.setBoolean(5, partadto.isCongenital());
                calstmt.setBoolean(6, partadto.isCongenitalbettereye());
                calstmt.setBoolean(7, partadto.isCongenitalworseeye());
                calstmt.setBoolean(8, partadto.isHereditary());
                calstmt.setBoolean(9, partadto.isHereditarybettereye());
                calstmt.setBoolean(10, partadto.isHereditaryworseeye());
                calstmt.setBoolean(11, partadto.isBirthinjury());
                calstmt.setBoolean(12, partadto.isBirthinjurybettereye());
                calstmt.setBoolean(13, partadto.isBirthinjuryworseeye());
                calstmt.setBoolean(14, partadto.isDiseaseInfection());
                calstmt.setBoolean(15, partadto.isDiseaseInfectionbettereye());
                calstmt.setBoolean(16, partadto.isDiseaseInfectionworseeye());
                calstmt.setBoolean(17, partadto.isMalnutrition());
                calstmt.setBoolean(18, partadto.isMalnutritionbettereye());
                calstmt.setBoolean(19, partadto.isMalnutritionworseeye());
                calstmt.setBoolean(20, partadto.isAccident());
                calstmt.setBoolean(21, partadto.isAccidentbettereye());
                calstmt.setBoolean(22, partadto.isAccidentworseeye());
                calstmt.setString(23, partadto.getOtherconditionofdisability());
                calstmt.setBoolean(24, partadto.isHighfever());
                calstmt.setBoolean(25, partadto.isEpilepsy());
                calstmt.setBoolean(26, partadto.isBirthasphyxia());
//                calstmt.setBoolean(24, partadto.isCongenitalvisual());
//                calstmt.setBoolean(25, partadto.isHereditaryvisual());
//                calstmt.setBoolean(26, partadto.isBirthinjuryvisual());
//                calstmt.setBoolean(27, partadto.isDiseaseInfectionvisual());
//                calstmt.setBoolean(28, partadto.isMalnutritionvisual());
//                calstmt.setBoolean(29, partadto.isAccidentvisual());
                //calstmt.setBoolean(24, partadto.isHighfever());
                //calstmt.setBoolean(25, partadto.isEpilepsy());
                calstmt.setString(27, partadto.getLoginid());
                calstmt.setString(28, partadto.getSystemip());



                calstmt.executeUpdate();
                calstmt.close();
                calstmt = con.prepareCall("{Call SP_tbl_Person_Disability_Cando_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                calstmt.setString(1, personcode);
                calstmt.setString(2, partadto.getF_can());
                calstmt.setString(3, partadto.getPp_can());
                calstmt.setString(4, partadto.getL_can());
                calstmt.setString(5, partadto.getKc_can());
                calstmt.setString(6, partadto.getB_can());
                calstmt.setString(7, partadto.getS_can());
                calstmt.setString(8, partadto.getSt_can());
                calstmt.setString(9, partadto.getW_can());
                calstmt.setString(10, partadto.getRw_can());
                calstmt.setString(11, partadto.getSe_can());
                calstmt.setString(12, partadto.getH_can());
                calstmt.setString(13, partadto.getLoginid());
                calstmt.setString(14, partadto.getSystemip());


                calstmt.executeUpdate();

                /*    ps=con.prepareStatement("insert into RailwaypassDoctor_Details(Person_Code, DoctorName, RegistrationNo, Designation) values(?,?,?,?)");
                ps.setString(1, personcode);
                ps.setString(2, partadto.getDoctor1name());
                ps.setString(3, partadto.getDoctor1regnumber());
                ps.setString(4, partadto.getDoctor1designation());

                if(ps.executeUpdate()>0){

                }*/


            } else {

                calstmt = con.prepareCall("{Call SP_tblRejectPerson_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                calstmt.setString(1, personcode);
                calstmt.setInt(2, partadto.getDisabilityId());
                calstmt.setString(3, partadto.getOthertypeofdisability());
                calstmt.setString(4, partadto.getReferredto());
                calstmt.setString(5, partadto.getSurgery());
                calstmt.setString(6, partadto.getCouncellingandguidance());
                //calstmt.setBoolean(6,partadto.isSpeechtherapy());
                calstmt.setString(7, partadto.getHearingaid());
                calstmt.setString(8, partadto.getBehaviourmodification());
                calstmt.setString(9, partadto.getPhychotherapy());
                calstmt.setString(10, partadto.getAdmissioninpsychiatrichospital());
                calstmt.setString(11, partadto.getPhysiotherapy());
                calstmt.setString(12, partadto.getLowvisionaid());
                calstmt.setString(13, partadto.getAnyotherneed());
                calstmt.setString(14, partadto.getHospitalname());
                calstmt.setString(15, partadto.getHospitaladdress());
                calstmt.setString(16, partadto.getDoctor1name());
                calstmt.setString(17, partadto.getDoctor1regnumber());
                calstmt.setString(18, partadto.getDoctor1designation());
                calstmt.setString(19, partadto.getDoctor2name());
                calstmt.setString(20, partadto.getDoctor2regnumber());
                calstmt.setString(21, partadto.getDoctor2designation());
                calstmt.setString(22, partadto.getDoctor3name());
                calstmt.setString(23, partadto.getDoctor3regnumber());
                calstmt.setString(24, partadto.getDoctor3designation());
                calstmt.setString(25, partadto.getCamp_venue());
                calstmt.setString(26, partadto.getLoginid());
                calstmt.setString(27, partadto.getSystemip());



                calstmt.executeUpdate();
            }
            calstmt.close();
            //  con.commit();
            // con.setAutoCommit(true);



        } catch (SQLException sqlException) {
           con.rollback();
            int exResult = ExceptionDAO.saveException(ds, sqlException.toString(), "insertDisabilityDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "insertDisabilityDetails");

        } catch (Exception e) {
           con.rollback();
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "insertDisabilityDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "insertDisabilityDetails");

        } finally {
            DBConnection.closeStatement(calstmt);
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(st);
        }
    }

    /**
     *
     * @param partadto
     * @param ds
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public synchronized String insertPersonalDetails(PartADTO partadto, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        Connection con = null;
        String personcodemax = null;
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        String districtid = partadto.getDistrict();
        String mandalid = partadto.getMandal();
        String villageid = partadto.getTownVillage();
        String habitationid = partadto.getHabitation();
        String panchayatid = partadto.getPanchayatiid();
        String assemblyid = partadto.getAssemblyid();
        HttpSession session = request.getSession();
        String reasonForStatus = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getFromdate());
            String formdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date ddate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getDobday());
            String dobdate = new SimpleDateFormat("mm/dd/yyyy").format(ddate);

            int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));

            if (partadto.getReasonforstatus() != null && !partadto.getReasonforstatus().equals("")) {
                reasonForStatus = partadto.getReasonforstatus();
            } else {
                reasonForStatus = session.getAttribute("personStatusId").toString();
            }

            personcodemax = territoryservice.getHabitationCode(districtid, mandalid, panchayatid, assemblyid, villageid, habitationid, ds);
            calstmt = con.prepareCall("{Call SP_tblPerson_Personal_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            calstmt.setString(1, personcodemax);
            calstmt.setString(2, partadto.getFormno());
            calstmt.setString(3, formdate);
            calstmt.setString(4, convertFirstLetterToUpperCase(partadto.getSurname()));
            calstmt.setString(5, partadto.getFirstname());
            calstmt.setString(6, partadto.getGender());
            calstmt.setString(7, dobdate);
            calstmt.setString(8, partadto.getNoOfYears());
            calstmt.setString(9, partadto.getReligion());
            calstmt.setString(10, partadto.getCaste());
            calstmt.setString(11, partadto.getMarital());
            calstmt.setString(12, partadto.getEducation());
            calstmt.setString(13, partadto.getEmployment());
            calstmt.setString(14, partadto.getGrelation());
            calstmt.setString(15, partadto.getGsurname());
            calstmt.setString(16, partadto.getRtype());
            calstmt.setString(17, partadto.getCard());
            calstmt.setBoolean(18, partadto.isEpiccard());
            calstmt.setString(19, partadto.getEpiccardno());
            calstmt.setBoolean(20, partadto.isPensioncard());
            calstmt.setString(21, partadto.getPension_type());
            calstmt.setString(22, partadto.getPensioncardno());
            calstmt.setString(23, convertFirstLetterToUpperCase(partadto.getMole1()));
            calstmt.setString(24, convertFirstLetterToUpperCase(partadto.getMole2()));
            calstmt.setString(25, partadto.getHouseno());
            calstmt.setString(26, partadto.getPhoneno());
            calstmt.setString(27, partadto.getEmail());
            calstmt.setString(28, partadto.getDistrict());
            calstmt.setString(29, partadto.getMandal());
            calstmt.setString(30, partadto.getTownVillage());
            calstmt.setString(31, partadto.getHabitation());
            calstmt.setString(32, partadto.getPin());
            calstmt.setString(33, partadto.getTelugupersonname());
            calstmt.setString(34, partadto.getTelugufathername());
            calstmt.setString(35, partadto.getPersonstatus());
            calstmt.setString(36, partadto.getParents_marriage());
            calstmt.setString(37, partadto.getType_disability());
            calstmt.setString(38, partadto.getExistingpercentage());
            calstmt.setString(39, partadto.getLoginid());
            calstmt.setString(40, partadto.getSystemip());
            calstmt.setInt(41, campId);
            calstmt.setString(42, partadto.getPensionPhase());
            calstmt.setString(43, reasonForStatus);

            // Commented by mohan on 28/06/2011

            //  if (partadto.getHabCode() != null) {
            //     calstmt.setString(44, partadto.getHabCode());
            //  } else {
            calstmt.setString(44, personcodemax.substring(0, 14));
            //  }
            calstmt.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            session.removeAttribute("personStatusId");
        } catch (ParseException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPersonalDetails", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "insertPersonalDetails");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPersonalDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "insertPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPersonalDetails", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "insertPersonalDetails");
        } finally {
            DBConnection.closeStatement(calstmt);
            DBConnection.closeConnection(con);

        }
        return personcodemax;
    }

    /**
     *
     * @param personcode
     * @param personstatus
     * @param datasource
     * @param campId
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public PartADTO getPersonalDetails(String personcode, String personstatus, DataSource ds, int campId) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        Connection con = null;
        ResultSet rs = null;
        PartADTO partADTO = null;
        int disability_ID = 0;
        int[] Sub_Disability_IDs = null;
        Statement stmt = null;

        try {
            con = DBConnection.getConnection();
            partADTO = getCampDetails(ds, campId);
            if (partADTO == null) {
                partADTO = new PartADTO();
            }

            calstmt = con.prepareCall("{Call SP_tblPerson_Personal_Details_Select(?)}");
            calstmt.setString(1, personcode);
            rs = calstmt.executeQuery();
            while (rs.next()) {
                partADTO.setFormno(rs.getString("Reference_Form_Number"));
                String formdate = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd ").parse(rs.getString(2)));
                partADTO.setFromdate(formdate);

                partADTO.setSurname(rs.getString("Surname"));
                partADTO.setFirstname(rs.getString("First_Name"));

                partADTO.setGender(rs.getString("Gender"));
                String dobdate = new SimpleDateFormat("d/M/yyyy").format(new SimpleDateFormat("yyyy-MM-dd ").parse(rs.getString("Date_of_Birth")));
                partADTO.setDobday(dobdate);
                partADTO.setNoOfYears(String.valueOf(rs.getInt("Age_Years")));
                partADTO.setReligion(rs.getString("Religion"));
                partADTO.setCaste(rs.getString("Caste"));
                partADTO.setMarital(rs.getString("Marital_Status"));
                partADTO.setEducation(rs.getString("Education"));
                //    partADTO.setEdustatus(rs.getString("Education_Status"));
                partADTO.setEmployment(rs.getString("Employment"));
                //   partADTO.setAnualIncome(rs.getString("Annual_Income"));
                partADTO.setGrelation(rs.getString("Relationship"));
                partADTO.setGsurname(rs.getString("Relation_Name"));

                // partADTO.setGageYears(rs.getString("Relation_Age"));

                //   partADTO.setGeducation(rs.getString("Relation_Education"));
                // partADTO.setGemployement(rs.getString("Relation_Ocupation"));
                //     partADTO.setGincome(rs.getString("Family_Income"));
                //  partADTO.setYesanyDisableperson(rs.getBoolean("Infamily_Other_Disability"));
                //  partADTO.setNoOfDisableperson(String.valueOf(rs.getInt("Numberof_Persons_Infamily")));
                //  partADTO.setRelationStr(rs.getString("Relation"));
                partADTO.setRtype(rs.getString("RationCard_Type"));
                partADTO.setCard(rs.getString("RationCard_Number"));
                //partADTO.setHousingtype(rs.getString("Housing_Type"));
                //  partADTO.setBloodgroup(rs.getString("Blood_Group"));
                //  partADTO.setHallticket(rs.getString("HallTicket_Number"));

                //partADTO.setShgno(rs.getString("SHG_Member"));
                // partADTO.setPwdno(rs.getString("PWD_Member"));
                //   partADTO.setNregcard(rs.getBoolean("NREG_Card"));
                //   partADTO.setNregcardno(rs.getString("NREGCard_No"));
                //  partADTO.setArogyasricard(rs.getBoolean("ArogyaSri_Card"));
                //   partADTO.setArogyasricardno(rs.getString("ArogyaSriCard_No"));
                partADTO.setEpiccard(rs.getBoolean("EPIC_Card"));
                partADTO.setEpiccardno(rs.getString("EPICCard_No"));
                // partADTO.setIsl(rs.getString("ISL"));
                partADTO.setPensioncard(rs.getBoolean("Pension_Card"));
                partADTO.setPension_type(rs.getString("Pension_Type"));
                partADTO.setPensioncardno(rs.getString("PensionCard_No"));
                partADTO.setMole1(rs.getString("Mole_One"));
                partADTO.setMole2(rs.getString("Mole_Two"));
                partADTO.setHouseno(rs.getString("House_Number"));
                partADTO.setPhoneno(rs.getString("Phone_No"));
                partADTO.setEmail(rs.getString("Email"));
                partADTO.setDistrict(rs.getString("District_ID"));
                partADTO.setMandal(rs.getString("Mandal_ID"));
                //   partADTO.setPanchaith(rs.getString("Panchayat_ID"));
                partADTO.setTownVillage(rs.getString("Village_ID"));
                partADTO.setHabitation(rs.getString("Habitation_ID"));
                partADTO.setPersonstatus(rs.getString("Person_Status"));
                partADTO.setPin(rs.getString("Pin_Code"));
                partADTO.setParents_marriage(rs.getString("Parents_Marriage"));
                partADTO.setType_disability(rs.getString("Typeof_Disability"));
                partADTO.setExistingpercentage(rs.getString("Existing_Percentage"));

                //  partADTO.setLeprosy_cured(rs.getString("Leprosy_Cured"));
                partADTO.setTelugupersonname(rs.getString("Personname_Telugu"));
                partADTO.setTelugufathername(rs.getString("Fathername_Telugu"));
                partADTO.setLoginid(rs.getString("Login_ID"));
                partADTO.setOperatorStatus(rs.getString("View_Edit_Mode"));

                ///partADTO.setHabCode(rs.getString("HabCode"));
            }
            rs.close();
            calstmt.close();


        } catch (ParseException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDetails", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getPersonalDetails");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDetails", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getPersonalDetails");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(calstmt);
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);
        }
        return partADTO;
    }

    /**
     *
     * @param personcode
     * @param personstatus
     * @param datasource
     * @param campId
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public PartADTO getDesabilityDetails(String personcode, String personstatus, DataSource ds, int campId) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        Connection con = null;
        ResultSet rs = null;
        PartADTO partADTO = null;
        int disability_ID = 0;
        int[] Sub_Disability_IDs = null;
        Statement stmt = null;

        try {
            con = DBConnection.getConnection();
            partADTO = getCampDetails(ds, campId);
            if (partADTO == null) {
                partADTO = new PartADTO();
            }
            if (personstatus.equals("Eligible")) {
                calstmt = con.prepareCall("{Call SP_tblPerson_Disability_Details_Select(?)}");
                calstmt.setString(1, personcode);
                rs = calstmt.executeQuery();
                while (rs.next()) {
                    partADTO.setDisabilityId(rs.getInt("Disability_ID"));
                    partADTO.setDisabilitySubIds(rs.getString("Sub_Disability_ID"));
                    partADTO.setOthertypeofdisability(rs.getString("Any_Other"));
                    partADTO.setDisabilitySubSubIds(rs.getString("Sub_Sub_Disability_ID"));
                    partADTO.setDiagnosisofdisability(rs.getString("Diagnosis_of_Disability"));


                    partADTO.setHospitalname(rs.getString("Hospital_Name"));
                    partADTO.setHospitaladdress(rs.getString("Hospital_Address"));
                    partADTO.setDoctor1name(rs.getString("First_Doctor_Name"));

                    partADTO.setDoctor1regnumber(rs.getString("First_Doctor_RegNumber"));
                    partADTO.setDoctor1designation(rs.getString("First_Doctor_Designation"));

                    partADTO.setDoctor2name(rs.getString("Second_Doctor_Name"));
                    partADTO.setDoctor2regnumber(rs.getString("Second_Doctor_RegNumber"));
                    partADTO.setDoctor2designation(rs.getString("Second_Doctor_Designation"));

                    partADTO.setDoctor3name(rs.getString("Third_Doctor_Name"));
                    partADTO.setDoctor3regnumber(rs.getString("Third_Doctor_RegNumber"));
                    partADTO.setDoctor3designation(rs.getString("Third_Doctor_Designation"));
                    partADTO.setCamp_venue(rs.getString("VenueName"));
                    int disabilityId = rs.getInt("Disability_ID");
                    if (disabilityId == 6) {
                        calstmt = con.prepareCall("{Call SP_tblPerson_MultipleDisability_Doctors_Details_SELECT(?)}");
                        calstmt.setString(1, personcode);
                        rs = calstmt.executeQuery();

                        while (rs.next()) {
                            if (rs.getInt("Disability_ID") == 1) {
                                partADTO.setOhdoctor(String.valueOf(rs.getInt("Disability_ID")));
                                partADTO.setDoctornameOH(rs.getString("Doctor_Name"));
                                partADTO.setDoctorregnumberOH(rs.getString("Doctor_RegNumber"));
                                partADTO.setDoctordesignationOH(rs.getString("Doctor_Designation"));
                            }
                            if (rs.getInt("Disability_ID") == 2) {
                                partADTO.setVidoctor(String.valueOf(rs.getInt("Disability_ID")));
                                partADTO.setDoctornameVI(rs.getString("Doctor_Name"));
                                partADTO.setDoctorregnumberVI(rs.getString("Doctor_RegNumber"));
                                partADTO.setDoctordesignationVI(rs.getString("Doctor_Designation"));
                            }
                            if (rs.getInt("Disability_ID") == 3) {
                                partADTO.setHidoctor(String.valueOf(rs.getInt("Disability_ID")));
                                partADTO.setDoctornameHI(rs.getString("Doctor_Name"));
                                partADTO.setDoctorregnumberHI(rs.getString("Doctor_RegNumber"));
                                partADTO.setDoctordesignationHI(rs.getString("Doctor_Designation"));
                            }
                            if (rs.getInt("Disability_ID") == 4) {
                                partADTO.setMrdoctor(String.valueOf(rs.getInt("Disability_ID")));
                                partADTO.setDoctornameMR(rs.getString("Doctor_Name"));
                                partADTO.setDoctorregnumberMR(rs.getString("Doctor_RegNumber"));
                                partADTO.setDoctordesignationMR(rs.getString("Doctor_Designation"));
                            }
                            if (rs.getInt("Disability_ID") == 5) {
                                partADTO.setMidoctor(String.valueOf(rs.getInt("Disability_ID")));
                                partADTO.setDoctornameMI(rs.getString("Doctor_Name"));
                                partADTO.setDoctorregnumberMI(rs.getString("Doctor_RegNumber"));
                                partADTO.setDoctordesignationMI(rs.getString("Doctor_Designation"));
                            }
                        }

                    }


                }
                rs.close();
                calstmt.close();

                calstmt = con.prepareCall("{Call SP_tbl_Person_Disability_Cando_Details_Select(?)}");
                calstmt.setString(1, personcode);
                rs = calstmt.executeQuery();
                while (rs.next()) {
                    partADTO.setF_can(rs.getString("F_Can"));
                    partADTO.setPp_can(rs.getString("PP_Can"));
                    partADTO.setL_can(rs.getString("L_Can"));
                    partADTO.setKc_can(rs.getString("KC_Can"));
                    partADTO.setB_can(rs.getString("B_Can"));
                    partADTO.setS_can(rs.getString("S_Can"));
                    partADTO.setSt_can(rs.getString("ST_Can"));
                    partADTO.setW_can(rs.getString("W_Can"));
                    partADTO.setRw_can(rs.getString("RW_Can"));
                    partADTO.setSe_can(rs.getString("SE_Can"));
                    partADTO.setH_can(rs.getString("H_Can"));
                }
            } else {
                calstmt = con.prepareCall("{Call SP_tblRejectPerson_Details_Select(?)}");
                calstmt.setString(1, personcode);
                rs = calstmt.executeQuery();
                while (rs.next()) {
                    partADTO.setDisabilityId(rs.getInt("Disability_ID"));
                    partADTO.setOthertypeofdisability(rs.getString("Other_Disability"));
                    partADTO.setReferredto(rs.getString("Reffered_To"));
                    partADTO.setSurgery(rs.getString("Surgery"));

                    partADTO.setCouncellingandguidance(rs.getString("Counciling_Guidence"));
                    // partADTO.setSpeechtherapy(rs.getBoolean(""));
                    partADTO.setHearingaid(rs.getString("Hearing_Aid"));
                    partADTO.setBehaviourmodification(rs.getString("Behaviour"));
                    partADTO.setPhychotherapy(rs.getString("Phychotherapy"));
                    partADTO.setAdmissioninpsychiatrichospital(rs.getString("Addmission"));
                    partADTO.setPhysiotherapy(rs.getString("Physiotherapy"));
                    partADTO.setLowvisionaid(rs.getString("LowVision"));
                    partADTO.setAnyotherneed(rs.getString("AnyOther"));
                    partADTO.setHospitalname(rs.getString("Hospital_Name"));
                    partADTO.setHospitaladdress(rs.getString("Hospital_Address"));

                    partADTO.setDoctor1name(rs.getString("First_Doctor_Name"));
                    partADTO.setDoctor1regnumber(rs.getString("First_Doctor_RegNumber"));
                    partADTO.setDoctor1designation(rs.getString("First_Doctor_Designation"));

                    partADTO.setDoctor2name(rs.getString("Second_Doctor_Name"));
                    partADTO.setDoctor2regnumber(rs.getString("Second_Doctor_RegNumber"));
                    partADTO.setDoctor2designation(rs.getString("Second_Doctor_Designation"));

                    partADTO.setDoctor3name(rs.getString("Third_Doctor_Name"));
                    partADTO.setDoctor3regnumber(rs.getString("Third_Doctor_RegNumber"));
                    partADTO.setDoctor3designation(rs.getString("Third_Doctor_Designation"));
                }
            }
            rs.close();
            calstmt.close();



            calstmt = con.prepareCall("{Call SP_tblPerson_Causes_of_Disability_Details_Select(?)}");
            calstmt.setString(1, personcode);
            rs = calstmt.executeQuery();
            while (rs.next()) {
                partADTO.setConditiondisabilityIds(rs.getString(1));
                partADTO.setConditionindisability(rs.getString(2));

                partADTO.setYearsfortemporary(String.valueOf(rs.getInt(3)));
                partADTO.setCongenital(rs.getBoolean(4));
                partADTO.setCongenitalbettereye(rs.getBoolean(5));
                partADTO.setCongenitalworseeye(rs.getBoolean(6));
                partADTO.setHereditary(rs.getBoolean(7));
                partADTO.setHereditarybettereye(rs.getBoolean(8));
                partADTO.setHereditaryworseeye(rs.getBoolean(9));
                partADTO.setBirthinjury(rs.getBoolean(10));
                partADTO.setBirthinjurybettereye(rs.getBoolean(11));
                partADTO.setBirthinjuryworseeye(rs.getBoolean(12));
                partADTO.setDiseaseInfection(rs.getBoolean(13));
                partADTO.setDiseaseInfectionbettereye(rs.getBoolean(14));
                partADTO.setDiseaseInfectionworseeye(rs.getBoolean(15));
                partADTO.setMalnutrition(rs.getBoolean(16));
                partADTO.setMalnutritionbettereye(rs.getBoolean(17));
                partADTO.setMalnutritionworseeye(rs.getBoolean(18));
                partADTO.setAccident(rs.getBoolean(19));
                partADTO.setAccidentbettereye(rs.getBoolean(20));
                partADTO.setAccidentworseeye(rs.getBoolean(21));
                partADTO.setOtherconditionofdisability(rs.getString(22));
                partADTO.setHighfever(rs.getBoolean(23));
                partADTO.setEpilepsy(rs.getBoolean(24));
                partADTO.setBirthasphyxia(rs.getBoolean(25));
//                partADTO.setCongenitalvisual(rs.getBoolean(23));
//                partADTO.setHereditaryvisual(rs.getBoolean(24));
//                partADTO.setBirthinjuryvisual(rs.getBoolean(25));
//                partADTO.setDiseaseInfectionvisual(rs.getBoolean(26));
//                partADTO.setMalnutritionvisual(rs.getBoolean(27));
//                partADTO.setAccidentvisual(rs.getBoolean(28));

            }
            rs.close();
            calstmt.close();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDesabilityDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getDesabilityDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDesabilityDetails", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getDesabilityDetails");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(calstmt);
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);
        }
        return partADTO;
    }

    /**
     *
     * @param personcode
     * @param partadto
     * @param datasource
     * @throws SADAREMDBException,SQLException
     */
    public void updatePersonalDetails(String personcode, PartADTO partadto, DataSource ds) throws SADAREMDBException, SQLException {
        CallableStatement calstmt = null;
        Connection con = null;
        Statement stmt = null;
        String Status = partadto.getPersonstatus();
        try {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getFromdate());
            String formdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date ddate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getDobday());
            String dobdate = new SimpleDateFormat("mm/dd/yyyy").format(ddate);
            con = DBConnection.getConnection();

            if (Status.equals("Rejected")) {
                calstmt = con.prepareCall("{Call SP_DeleteDatainAllTables_for_RejectPerson(?)}");
                calstmt.setString(1, personcode);
                calstmt.executeUpdate();
            }// Modified by ganesh
            else {
                String queryDeleteRejectedDate = "delete from  dbo.tblRejectPerson_Details where Person_Code='" + personcode + "'";
                // con = DBConnection.getConnection();
                stmt = con.createStatement();
                stmt.execute(queryDeleteRejectedDate);
            }
            // end of ganesh modification


            if (partadto.getPersonstatus() != null) {
                con.setAutoCommit(false);
                calstmt = con.prepareCall("{Call SP_tblPerson_Personal_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");


                calstmt.setString(1, personcode);
                calstmt.setString(2, partadto.getFormno());
                calstmt.setString(3, formdate);
                calstmt.setString(4, convertFirstLetterToUpperCase(partadto.getSurname()));
                calstmt.setString(5, partadto.getFirstname());
                calstmt.setString(6, partadto.getGender());
                calstmt.setString(7, dobdate);
                calstmt.setString(8, partadto.getNoOfYears());
                calstmt.setString(9, partadto.getReligion());
                calstmt.setString(10, partadto.getCaste());
                calstmt.setString(11, partadto.getMarital());
                calstmt.setString(12, partadto.getEducation());              
                calstmt.setString(13, partadto.getEmployment());               
                calstmt.setString(14, partadto.getGrelation());
                calstmt.setString(15, partadto.getGsurname());               
                calstmt.setString(16, partadto.getRtype());
                calstmt.setString(17, partadto.getCard());               
                calstmt.setBoolean(18, partadto.isEpiccard());
                calstmt.setString(19, partadto.getEpiccardno());               
                calstmt.setBoolean(20, partadto.isPensioncard());
                calstmt.setString(21, partadto.getPension_type());
                calstmt.setString(22, partadto.getPensioncardno());
                calstmt.setString(23, convertFirstLetterToUpperCase(partadto.getMole1()));
                calstmt.setString(24, convertFirstLetterToUpperCase(partadto.getMole2()));
                calstmt.setString(25, partadto.getHouseno());
                calstmt.setString(26, partadto.getPhoneno());
                calstmt.setString(27, partadto.getEmail());
                calstmt.setString(28, partadto.getDistrict());
                calstmt.setString(29, partadto.getMandal());              
                calstmt.setString(30, partadto.getTownVillage());
                calstmt.setString(31, partadto.getHabitation());
                calstmt.setString(32, partadto.getPin());
                calstmt.setString(33, partadto.getTelugupersonname());
                calstmt.setString(34, partadto.getTelugufathername());
                calstmt.setString(35, partadto.getPersonstatus());
                calstmt.setString(36, partadto.getParents_marriage());
                calstmt.setString(37, partadto.getType_disability());
                calstmt.setString(38, partadto.getExistingpercentage());
                calstmt.setString(39, partadto.getLoginid());
                calstmt.setString(40, partadto.getSystemip());
                calstmt.setInt(41, partadto.getCampid());

                calstmt.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            }
        } catch (ParseException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatePersonalDetails", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "updatePersonalDetails");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatePersonalDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "updatePersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatePersonalDetails", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "updatePersonalDetails");
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(calstmt);
            DBConnection.closeConnection(con);
        }
    }

    /**
     *
     * @param personcode
     * @param partadto
     * @param ds
     * @throws SADAREMDBException,SQLException
     */
    public void updateDisabilityDetails(String personcode, PartADTO partadto, DataSource ds) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        String personstatus = partadto.getPersonstatus();
        try {


            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            stmt = con.createStatement();

            if (personstatus.equals("Eligible")) {
                rs = stmt.executeQuery("select Person_Code from tblPerson_Disability_Details where Person_Code='" + personcode + "'");
                if (rs.next()) {
                    calstmt = con.prepareCall("{Call SP_tblPerson_Disability_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    calstmt.setString(1, personcode);
                    calstmt.setInt(2, partadto.getDisabilityId());
                    calstmt.setString(3, partadto.getDisabilitySubIds());
                    calstmt.setString(4, partadto.getDisabilitySubSubIds());
                    calstmt.setString(5, partadto.getOthertypeofdisability());
                    calstmt.setString(6, partadto.getDiagnosisofdisability());
                    calstmt.setString(7, partadto.getHospitalname());
                    calstmt.setString(8, partadto.getHospitaladdress());
                    calstmt.setString(9, partadto.getDoctor1name());
                    calstmt.setString(10, partadto.getDoctor1regnumber());
                    calstmt.setString(11, partadto.getDoctor1designation());
                    calstmt.setString(12, partadto.getDoctor2name());
                    calstmt.setString(13, partadto.getDoctor2regnumber());
                    calstmt.setString(14, partadto.getDoctor2designation());
                    calstmt.setString(15, partadto.getDoctor3name());
                    calstmt.setString(16, partadto.getDoctor3regnumber());
                    calstmt.setString(17, partadto.getDoctor3designation());
                    calstmt.setString(18, partadto.getCamp_venue());
                    calstmt.setString(19, partadto.getLoginid());
                    calstmt.setString(20, partadto.getSystemip());
                    calstmt.executeUpdate();
                    calstmt.close();
                    calstmt = con.prepareCall("{Call SP_tblPerson_Causes_of_Disability_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    calstmt.setString(1, personcode);
                    calstmt.setString(2, partadto.getConditiondisabilityIds());
                    calstmt.setString(3, partadto.getConditionindisability());

                    calstmt.setInt(4, Integer.parseInt(partadto.getYearsfortemporary()));
                    calstmt.setBoolean(5, partadto.isCongenital());
                    calstmt.setBoolean(6, partadto.isCongenitalbettereye());
                    calstmt.setBoolean(7, partadto.isCongenitalworseeye());
                    calstmt.setBoolean(8, partadto.isHereditary());
                    calstmt.setBoolean(9, partadto.isHereditarybettereye());
                    calstmt.setBoolean(10, partadto.isHereditaryworseeye());
                    calstmt.setBoolean(11, partadto.isBirthinjury());
                    calstmt.setBoolean(12, partadto.isBirthinjurybettereye());
                    calstmt.setBoolean(13, partadto.isBirthinjuryworseeye());
                    calstmt.setBoolean(14, partadto.isDiseaseInfection());
                    calstmt.setBoolean(15, partadto.isDiseaseInfectionbettereye());
                    calstmt.setBoolean(16, partadto.isDiseaseInfectionworseeye());
                    calstmt.setBoolean(17, partadto.isMalnutrition());
                    calstmt.setBoolean(18, partadto.isMalnutritionbettereye());
                    calstmt.setBoolean(19, partadto.isMalnutritionworseeye());
                    calstmt.setBoolean(20, partadto.isAccident());
                    calstmt.setBoolean(21, partadto.isAccidentbettereye());
                    calstmt.setBoolean(22, partadto.isAccidentworseeye());
                    calstmt.setString(23, partadto.getOtherconditionofdisability());
                    calstmt.setBoolean(24, partadto.isHighfever());
                    calstmt.setBoolean(25, partadto.isEpilepsy());
                    calstmt.setBoolean(26, partadto.isBirthasphyxia());
//                    calstmt.setBoolean(24, partadto.isCongenitalvisual());
//                    calstmt.setBoolean(25, partadto.isHereditaryvisual());
//                    calstmt.setBoolean(26, partadto.isBirthinjuryvisual());
//                    calstmt.setBoolean(27, partadto.isDiseaseInfectionvisual());
//                    calstmt.setBoolean(28, partadto.isMalnutritionvisual());
//                    calstmt.setBoolean(29, partadto.isAccidentvisual());
                    //calstmt.setBoolean(24, partadto.isHighfever());
                    //calstmt.setBoolean(25, partadto.isEpilepsy());
                    calstmt.setString(27, partadto.getLoginid());
                    calstmt.setString(28, partadto.getSystemip());

                    calstmt.executeUpdate();
                    calstmt.close();
                    calstmt = con.prepareCall("{Call SP_tbl_Person_Disability_Cando_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    calstmt.setString(1, personcode);
                    calstmt.setString(2, partadto.getF_can());
                    calstmt.setString(3, partadto.getPp_can());
                    calstmt.setString(4, partadto.getL_can());
                    calstmt.setString(5, partadto.getKc_can());
                    calstmt.setString(6, partadto.getB_can());
                    calstmt.setString(7, partadto.getS_can());
                    calstmt.setString(8, partadto.getSt_can());
                    calstmt.setString(9, partadto.getW_can());
                    calstmt.setString(10, partadto.getRw_can());
                    calstmt.setString(11, partadto.getSe_can());
                    calstmt.setString(12, partadto.getH_can());
                    calstmt.setString(13, partadto.getLoginid());
                    calstmt.setString(14, partadto.getSystemip());
                    calstmt.executeUpdate();


                } else {
                    insertDisabilityDetails(personcode, partadto, ds);

                }
            } else {
                rs = stmt.executeQuery("select Person_Code from tblRejectPerson_Details where Person_Code='" + personcode + "'");
                if (rs.next()) {

                    calstmt = con.prepareCall("{Call SP_tblRejectPerson_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    calstmt.setString(1, personcode);
                    calstmt.setInt(2, partadto.getDisabilityId());
                    calstmt.setString(3, partadto.getOthertypeofdisability());
                    calstmt.setString(4, partadto.getReferredto());
                    calstmt.setString(5, partadto.getSurgery());
                    calstmt.setString(6, partadto.getCouncellingandguidance());
                    //calstmt.setBoolean(6,partadto.isSpeechtherapy());
                    calstmt.setString(7, partadto.getHearingaid());
                    calstmt.setString(8, partadto.getBehaviourmodification());
                    calstmt.setString(9, partadto.getPhychotherapy());
                    calstmt.setString(10, partadto.getAdmissioninpsychiatrichospital());
                    calstmt.setString(11, partadto.getPhysiotherapy());
                    calstmt.setString(12, partadto.getLowvisionaid());
                    calstmt.setString(13, partadto.getAnyotherneed());
                    calstmt.setString(14, partadto.getHospitalname());
                    calstmt.setString(15, partadto.getHospitaladdress());
                    calstmt.setString(16, partadto.getDoctor1name());
                    calstmt.setString(17, partadto.getDoctor1regnumber());
                    calstmt.setString(18, partadto.getDoctor1designation());
                    calstmt.setString(19, partadto.getDoctor2name());
                    calstmt.setString(20, partadto.getDoctor2regnumber());
                    calstmt.setString(21, partadto.getDoctor2designation());
                    calstmt.setString(22, partadto.getDoctor3name());
                    calstmt.setString(23, partadto.getDoctor3regnumber());
                    calstmt.setString(24, partadto.getDoctor3designation());
                    calstmt.setString(25, partadto.getCamp_venue());
                    calstmt.setString(26, partadto.getLoginid());
                    calstmt.setString(27, partadto.getSystemip());
                    calstmt.executeUpdate();
                } else {

                    insertDisabilityDetails(personcode, partadto, ds);
                }
            }
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateDisabilityDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "updateDisabilityDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateDisabilityDetails", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "updateDisabilityDetails");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(calstmt);
            DBConnection.closeConnection(con);
        }

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
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousDisabilityDetailsForUpdateDisability", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "deletePreviousDisabilityDetailsForUpdateDisability");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousDisabilityDetailsForUpdateDisability", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "deletePreviousDisabilityDetailsForUpdateDisability");
        } finally {
            DBConnection.closeStatement(calstmt);
            DBConnection.closeConnection(con);
        }
    }

    /**
     *
     * @param ds
     * @param district_id
     * @param mandal_id
     * @param village_id
     * @param habitation_id
     * @param surName
     * @param firstName
     * @param lastName
     * @param age
     * @param gender
     * @param relationName
     * @param houseNumber
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public String getPartACheckForDuplicate(DataSource ds, String district_id, String mandal_id, String village_id, String habitation_id, String surName, String firstName, String lastName, String age, String gender, String relationName, String houseNumber) throws SADAREMDBException, SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String partACheckForDuplicatePersonCode = null;
        try {

            con = DBConnection.getConnection();
            pstmt = con.prepareStatement("select Person_Code from tblPerson_Personal_Details with (nolock)  where  District_Id=? and Mandal_Id =? and Village_Id = ? and  Habitation_ID= ? and UPPER(Surname) =?  and UPPER(First_Name)=?    and  Age_Years=? and  Gender=? and Relation_Name =? and UPPER(House_Number) =?");


            pstmt.setString(1, district_id);
            pstmt.setString(2, mandal_id);
            pstmt.setString(3, village_id);
            pstmt.setString(4, habitation_id);
            pstmt.setString(5, surName.toUpperCase());
            pstmt.setString(6, firstName.toUpperCase());
            //pstmt.setString(7,"null");
            pstmt.setString(7, age);
            pstmt.setString(8, gender);
            pstmt.setString(9, relationName);
            pstmt.setString(10, houseNumber.toUpperCase());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                partACheckForDuplicatePersonCode = rs.getString(1);

            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPartACheckForDuplicate", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getPartACheckForDuplicate");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPartACheckForDuplicate", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getPartACheckForDuplicate");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }


        return partACheckForDuplicatePersonCode;
    }

    // Added by BapiNaidu for retriving existing data based on pension number.
    /**
     *
     * @param pensionCode
     * @param districtId
     * @param datasource
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public PartADTO getExistingPensionDetails(String pensionCode, String districtId, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        Connection con = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        PartADTO partADTO = null;
        PreparedStatement pstmt = null;
        int pensionCodeTemp;
        String deathcase = null;
        HttpSession session = request.getSession();
        try {
            con = DBConnection.getConnection();
            if ((pensionCode != null && !"".equals(pensionCode)) && (districtId != null && !"".equals(districtId))) {
                pstmt = con.prepareStatement("select Person_Status,ReasonforPersonStatus from Disabled_pension where pensionid=? and distcode=?");
                pstmt.setString(1, pensionCode);
                pstmt.setString(2, districtId);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    partADTO = new PartADTO();
                    deathcase = rs.getString("Person_Status");
                    partADTO.setDeathcase(deathcase);
                    partADTO.setReasonforstatus(rs.getString("ReasonforPersonStatus"));
                    session.setAttribute("personStatusId", rs.getString("ReasonforPersonStatus"));
                    if (!CommonConstants.DEATH_CASE.equals(deathcase)) {
                        //  pensionCodeTemp = Integer.parseInt(pensionCode.toString());
                        pstmt = con.prepareStatement("select Person_Code from tblPerson_Personal_Details  with (nolock) where PensionCard_No=? and District_Id=?");
                        pstmt.setString(1, pensionCode);
                        pstmt.setString(2, districtId);
                        rs1 = pstmt.executeQuery();
                        if (rs1.next()) {
                            partADTO.setPersoncode(rs1.getString("Person_Code"));
                        } else {
                            calstmt = con.prepareCall("{Call SP_GETTINGEXISTINGPENSIONERDATA(?,?)}");
                            calstmt.setString(1, districtId);
                            calstmt.setString(2, pensionCode);
                            rs2 = calstmt.executeQuery();
                            while (rs2.next()) {
                                partADTO = new PartADTO();
                                partADTO.setPensioncardno(rs2.getString("pensionid"));
                                partADTO.setPension_type(rs2.getString("PensionType"));
                                if ((rs2.getString("pensionid") != null && !"".equals(rs2.getString("pensionid")))
                                        || (rs2.getString("PensionType") != null && !"".equals(rs2.getString("PensionType")))) {
                                    partADTO.setPensioncard(true);
                                }
                                partADTO.setSurname(rs2.getString("Surname"));
                                partADTO.setFirstname(rs2.getString("FirstName"));
                                partADTO.setGsurname(rs2.getString("FatherName"));
                                partADTO.setNoOfYears(rs2.getString("Age"));
                                partADTO.setGender(rs2.getString("Gender"));
                                partADTO.setCaste(rs2.getString("caste"));
                                partADTO.setCard(rs2.getString("rationcardno"));
                                partADTO.setDistrict(rs2.getString("District_Name"));
                                partADTO.setMandal(rs2.getString("Mandal_Name"));
                                partADTO.setTownVillage(rs2.getString("Village_Name"));
                                partADTO.setHabitation(rs2.getString("Habitation_Name"));
                                partADTO.setDistrtictid(rs2.getString("District_ID"));
                                partADTO.setMandalid(rs2.getString("Mandal_ID"));
                                partADTO.setVillageid(rs2.getString("Village_ID"));
                                partADTO.setHabitationid(rs2.getString("Habitation_ID"));
                                partADTO.setPensionPhase(rs2.getString("PensionPhase"));
                                partADTO.setPanchayatiid(rs2.getString("panchayatiid"));
                                partADTO.setAssemblyid(rs2.getString("assemblyid"));
                                partADTO.setHabCode(rs2.getString("HabCode"));
                                partADTO.setReasonforstatus(rs.getString("ReasonforPersonStatus"));

                            }
                        }
                    }
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getExistingPensionDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getExistingPensionDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getExistingPensionDetails", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getExistingPensionDetails");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeResultSet(rs1);
            DBConnection.closeResultSet(rs2);
            DBConnection.closeStatement(calstmt);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }
        return partADTO;
    }

    /**
     *
     * @param datasource
     * @param districtid
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public ArrayList getMandalsList(DataSource ds, String districtid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;

        ArrayList mandallist = new ArrayList();



        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "select mandal_id,mandal_name from tblMandal_Details where district_id='" + districtid + "' order by mandal_name";
            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                PartADTO partADTO = new PartADTO();
                partADTO.setMandal(rs.getString("mandal_id"));
                partADTO.setMandal_name(rs.getString("mandal_name"));
                mandallist.add(partADTO);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandalsList", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getMandalsList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandalsList", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getMandalsList");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);

        }

        return mandallist;
    }

    /**
     *
     * @param datasource
     * @param districtid
     * @param mandalid
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getVillagesList(DataSource ds, String districtid, String mandalid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList villagelist = new ArrayList();
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "select village_id,village_name from tblVillage_Details where district_id='" + districtid + "' and  mandal_id='" + mandalid + "' order by village_name";
            stmt = con.createStatement();

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                PartADTO partADTO = new PartADTO();
                partADTO.setTownVillage(rs.getString("village_id"));
                partADTO.setVillage_name(rs.getString("village_name"));
                villagelist.add(partADTO);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillagesList", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getVillagesList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillagesList", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getVillagesList");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);

        }
        return villagelist;
    }

    /**
     *
     * @param datasource
     * @param districtid
     * @param mandalid
     * @param villageid
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getHabitationsList(DataSource ds, String districtid, String mandalid, String villageid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList habitationlist = new ArrayList();
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "select habitation_id,habitation_name from tblHabitation_Details where district_id='" + districtid + "' and  mandal_id='" + mandalid + "'  and village_id='" + villageid + "' and status='Active' order by habitation_name";

            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                PartADTO partADTO = new PartADTO();
                partADTO.setHabitation(rs.getString("habitation_id"));
                partADTO.setHabitation_name(rs.getString("habitation_name"));
                habitationlist.add(partADTO);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHabitationsList", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getHabitationsList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHabitationsList", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getHabitationsList");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);

        }
        return habitationlist;
    }

    /**
     *
     * @param datasource
     * @param personcode
     * @param partadto
     * @param existingloginid
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public String updatePersonaCode(DataSource ds, String personcode, PartADTO partadto, String existingloginid) throws SADAREMDBException, SQLException {
        CallableStatement calstmt = null;
        Connection con = null;
        Statement stmt = null;
        String Status = partadto.getPersonstatus();
        String personcodemax = null;
        String districtid = partadto.getDistrict();
        String mandalid = partadto.getMandal();
        String villageid = partadto.getTownVillage();
        String habitationid = partadto.getHabitation();
        String panchayatid = partadto.getPanchayatiid();
        String assemblyid = partadto.getAssemblyid();
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        try {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getFromdate());
            String formdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date ddate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getDobday());
            String dobdate = new SimpleDateFormat("mm/dd/yyyy").format(ddate);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            personcodemax = territoryservice.getHabitationCode(districtid, mandalid, panchayatid, assemblyid, villageid, habitationid, ds);
            //updatePersonalDetails(personcode,partadto,datasource);
            insertPersonalDetailsForUpdatePersonCode(personcodemax, partadto, ds, con);

            calstmt = con.prepareCall("{Call SP_UPDATEPERSONCODE(?,?)}");
            calstmt.setString(1, personcode);
            calstmt.setString(2, personcodemax);
            calstmt.executeUpdate();
            calstmt.close();

            calstmt = con.prepareCall("{Call SP_tblModifyed_PersonCode_Details_INSERT(?,?,?,?)}");
            calstmt.setString(1, personcode);
            calstmt.setString(2, personcodemax);
            calstmt.setString(3, existingloginid);
            calstmt.setString(4, partadto.getLoginid());
            calstmt.executeUpdate();
            con.commit();
            con.setAutoCommit(true);

        } catch (ParseException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatePersonaCode", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "updatePersonaCode");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatePersonaCode", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "updatePersonaCode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatePersonaCode", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "updatePersonaCode");
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(calstmt);
            DBConnection.closeConnection(con);
        }

        return personcodemax;
    }

    /**
     *
     * @param personCode
     * @param partadto
     * @param ds
     * @param con
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public synchronized String insertPersonalDetailsForUpdatePersonCode(String personCode, PartADTO partadto, DataSource ds, Connection con) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        // Connection con = null;

        try {
            //con = DBConnection.getConnection();
            //con.setAutoCommit(false);
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getFromdate());
            String formdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date ddate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getDobday());
            String dobdate = new SimpleDateFormat("mm/dd/yyyy").format(ddate);


            calstmt = con.prepareCall("{Call SP_tblPerson_Personal_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            calstmt.setString(1, personCode);
            calstmt.setString(2, partadto.getFormno());
            calstmt.setString(3, formdate);
            calstmt.setString(4, partadto.getSurname());
            calstmt.setString(5, partadto.getFirstname());
            calstmt.setString(6, partadto.getGender());
            calstmt.setString(7, dobdate);
            calstmt.setString(8, partadto.getNoOfYears());
            calstmt.setString(9, partadto.getReligion());
            calstmt.setString(10, partadto.getCaste());
            calstmt.setString(11, partadto.getMarital());
            calstmt.setString(12, partadto.getEducation());

            calstmt.setString(13, partadto.getEmployment());

            calstmt.setString(14, partadto.getGrelation());
            calstmt.setString(15, partadto.getGsurname());
            //calstmt.setString(16, partadto.getGageYears());

            calstmt.setString(16, partadto.getRtype());
            calstmt.setString(17, partadto.getCard());

            calstmt.setBoolean(18, partadto.isEpiccard());
            calstmt.setString(19, partadto.getEpiccardno());

            calstmt.setBoolean(20, partadto.isPensioncard());
            calstmt.setString(21, partadto.getPension_type());
            calstmt.setString(22, partadto.getPensioncardno());
            calstmt.setString(23, partadto.getMole1());
            calstmt.setString(24, partadto.getMole2());
            calstmt.setString(25, partadto.getHouseno());
            calstmt.setString(26, partadto.getPhoneno());
            calstmt.setString(27, partadto.getEmail());
            calstmt.setString(28, partadto.getDistrict());
            calstmt.setString(29, partadto.getMandal());

            calstmt.setString(30, partadto.getTownVillage());
            calstmt.setString(31, partadto.getHabitation());
            calstmt.setString(32, partadto.getPin());
            calstmt.setString(33, partadto.getTelugupersonname());
            calstmt.setString(34, partadto.getTelugufathername());
            calstmt.setString(35, partadto.getPersonstatus());
            calstmt.setString(36, partadto.getParents_marriage());
            calstmt.setString(37, partadto.getType_disability());
            calstmt.setString(38, partadto.getExistingpercentage());
            calstmt.setString(39, partadto.getLoginid());
            calstmt.setString(40, partadto.getSystemip());


            calstmt.executeUpdate();

            // con.commit();
            // con.setAutoCommit(true);

        } catch (ParseException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPersonalDetailsForUpdatePersonCode", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "insertPersonalDetailsForUpdatePersonCode");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPersonalDetailsForUpdatePersonCode", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "insertPersonalDetailsForUpdatePersonCode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPersonalDetailsForUpdatePersonCode", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "insertPersonalDetailsForUpdatePersonCode");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(calstmt);

        }
        return personCode;
    }

//new added== delete data in alltables based on personcode
    /**
     *
     * @param ds
     * @param personcode
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public int deletePersonDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        Connection con = null;
        int i = 0;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            calstmt = con.prepareCall("{Call SP_DeleteDatainAllTables_for_UpdateDisability(?)}");
            calstmt.setString(1, personcode);
            calstmt.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePersonDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "deletePersonDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePersonDetails", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "deletePersonDetails");
        } finally {
            DBConnection.closeStatement(calstmt);
            DBConnection.closeConnection(con);
        }
        return i;

    }
//for First Letter CapitalPurpose

    /**
     *
     * @param inputString
     * @return
     */
    public String convertFirstLetterToUpperCase(String inputString) {
        if (inputString.equals("")) {
            return inputString;
        } else {
            StringBuffer inputStrBuffer = new StringBuffer(inputString);
            String firstLetterUpperCase = inputStrBuffer.substring(0, 1).toUpperCase();
            inputStrBuffer.replace(0, 1, firstLetterUpperCase);
            return inputStrBuffer.toString();
        }

    }

    /**
     *
     * @param ds
     * @param userid
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public PartADTO getLoginDetails(DataSource ds, String userid) throws SADAREMDBException, SQLException {

        Connection con = null;
        ResultSet rs = null;
        PartADTO partADTO = null;
        Statement stmt = null;
        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            String SQL = "select role_id from Login_Details "
                    + "where UserName = '" + userid + "'and Status='Active';";
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                partADTO = new PartADTO();
                partADTO.setRoleid(rs.getString("role_id"));
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getLoginDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getLoginDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getLoginDetails", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getLoginDetails");
        } finally {//added by rekha
            DBConnection.closeResultSet(rs);
            //DBConnection.closeStatement(cs);
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
        }
        return partADTO;
    }

    /**
     *
     * @param employeeId
     * @param districtId
     * @param datasource
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public PartADTO getEmployeeDetails(String employeeId, String districtId, DataSource ds) throws SADAREMDBException, SQLException {


        Connection con = null;
        ResultSet rs = null;
        PartADTO partADTO = null;
        PreparedStatement pstmt = null;
        try {
            con = DBConnection.getConnection();
            if (employeeId != null && !"".equals(employeeId)) {
                pstmt = con.prepareStatement("select Surname,Name,PensionPhase,ReasonforPersonStatus,Status from SERP_EmployeesDetails where EmployeeID = ?");
                pstmt.setString(1, employeeId);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    partADTO = new PartADTO();
                    partADTO.setSurname(rs.getString("Surname"));
                    partADTO.setFirstname(rs.getString("Name"));
                    partADTO.setPensionPhase(rs.getString("PensionPhase"));
                    partADTO.setReasonforstatus(rs.getString("ReasonforPersonStatus"));
                    partADTO.setStatus(rs.getString("Status"));
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getEmployeeDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getEmployeeDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getEmployeeDetails", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getEmployeeDetails");
        } finally {//added by rekha
            DBConnection.closeResultSet(rs);
            //DBConnection.closeStatement(cs);
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
        }

        return partADTO;
    }

    /**
     *
     * @param employeeId
     * @param partadto
     * @param datasource
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public String getEmployeeStatus(String employeeId, PartADTO partadto, DataSource ds) throws SADAREMDBException, SQLException {

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        String PersonCode = null;
        int i = 0;
        try {
            con = DBConnection.getConnection();
            if (employeeId != null && !"".equals(employeeId)) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                String todaydate = dateFormat.format(calendar.getTime());
                pstmt = con.prepareStatement("update dbo.SERP_EmployeesDetails set Status = 'B',District_ID =?,PensionID =?, Updated_Date =?,ReasonforPersonStatus=? where EmployeeID = ?");
                pstmt.setString(1, partadto.getDistrict());
                pstmt.setString(2, partadto.getPensioncardno());
                pstmt.setString(3, todaydate);
                pstmt.setString(4, "Assessment Completed");
                pstmt.setString(5, employeeId);
                i = pstmt.executeUpdate();
                if (i == 1) {
                    if (partadto.getPensioncardno() != null && !"".equals(partadto.getPensioncardno())) {
                        pstmt1 = con.prepareStatement("select Person_Code from tblPerson_Personal_Details  with (nolock) where PensionCard_No = ? and District_ID = ? and Mandal_ID = ? and Village_ID = ? and Habitation_ID = ?");
                        pstmt1.setString(1, partadto.getPensioncardno());
                        pstmt1.setString(2, partadto.getDistrict());
                        pstmt1.setString(3, partadto.getMandal());
                        pstmt1.setString(4, partadto.getTownVillage());
                        pstmt1.setString(5, partadto.getHabitation());
                        rs = pstmt1.executeQuery();
                        if (rs.next()) {
                            PersonCode = rs.getString("Person_Code");
                        } else {
                            PersonCode = "true";
                        }
                    } else {
                        PersonCode = "true";
                    }
                }

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getEmployeeStatus", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getEmployeeStatus");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getEmployeeStatus", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getEmployeeStatus");
        } finally {//added by rekha
            DBConnection.closeResultSet(rs);
            //DBConnection.closeStatement(cs);
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(pstmt1);
        }

        return PersonCode;

    }
    // Insert Multiple Doctors

    /**
     *
     * @param ds
     * @param personcode
     * @param multipleDisabilityID
     * @param multipleDisabilityMap
     * @param loginid
     * @param Systemip
     * @throws SADAREMDBException,SQLException
     */
    public void insertMultipleDoctors(DataSource ds, String personcode, String multipleDisabilityID[], Map multipleDisabilityMap, String loginid, String Systemip) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        Connection con = null;
        Statement stmt = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            for (int i = 0; i < multipleDisabilityID.length; i++) {
                PartADTO partADTO = (PartADTO) multipleDisabilityMap.get(multipleDisabilityID[i]);
                int disabilityId = Integer.parseInt(multipleDisabilityID[i]);
                calstmt = con.prepareCall("{Call SP_tblPerson_MultipleDisability_Doctors_Details_INSERT(?,?,?,?,?,?,?)}");
                calstmt.setString(1, personcode);
                calstmt.setInt(2, disabilityId);
                calstmt.setString(3, partADTO.getDoctorname());
                calstmt.setString(4, partADTO.getDoctorregnumber());
                calstmt.setString(5, partADTO.getDoctordesignation());
                calstmt.setString(6, loginid);
                calstmt.setString(7, Systemip);
                calstmt.executeUpdate();
                calstmt.close();

            }
            con.commit();
            con.setAutoCommit(true);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertMultipleDoctors", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "insertMultipleDoctors");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertMultipleDoctors", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "insertMultipleDoctors");
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(calstmt);
            DBConnection.closeConnection(con);
        }

    }

    // Update Multiple Doctors
    /**
     *
     * @param ds
     * @param personcode
     * @param multipleDisabilityID
     * @param multipleDisabilityMap
     * @param loginid
     * @param Systemip
     * @throws SADAREMDBException,SQLException
     */
    public void updateMultipleDoctors(DataSource ds, String personcode, String multipleDisabilityID[], Map multipleDisabilityMap, String loginid, String Systemip) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            String inactive = "UPDATE dbo.tblPerson_MultipleDisability_Doctors_Details SET Status = 'Inactive' where Person_Code='" + personcode + "'";
            stmt.executeUpdate(inactive);

            for (int i = 0; i < multipleDisabilityID.length; i++) {
                PartADTO partADTO = (PartADTO) multipleDisabilityMap.get(multipleDisabilityID[i]);
                int disabilityId = Integer.parseInt(multipleDisabilityID[i]);
                String checkquery = "select Person_Code from tblPerson_MultipleDisability_Doctors_Details where Person_Code='" + personcode + "' and Disability_ID='" + multipleDisabilityID[i] + "'";
                rs = stmt.executeQuery(checkquery);
                if (rs.next()) {
                    calstmt = con.prepareCall("{Call SP_tblPerson_MultipleDisability_Doctors_Details_UPDATE(?,?,?,?,?)}");
                    calstmt.setString(1, personcode);
                    calstmt.setInt(2, disabilityId);
                    calstmt.setString(3, partADTO.getDoctorname());
                    calstmt.setString(4, partADTO.getDoctorregnumber());
                    calstmt.setString(5, partADTO.getDoctordesignation());
                    calstmt.executeUpdate();
                    calstmt.close();

                } else {
                    calstmt = con.prepareCall("{Call SP_tblPerson_MultipleDisability_Doctors_Details_INSERT(?,?,?,?,?,?,?)}");
                    calstmt.setString(1, personcode);
                    calstmt.setInt(2, disabilityId);
                    calstmt.setString(3, partADTO.getDoctorname());
                    calstmt.setString(4, partADTO.getDoctorregnumber());
                    calstmt.setString(5, partADTO.getDoctordesignation());
                    calstmt.setString(6, loginid);
                    calstmt.setString(7, Systemip);
                    calstmt.executeUpdate();
                    calstmt.close();
                }


            }
            con.commit();
            con.setAutoCommit(true);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateMultipleDoctors", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "updateMultipleDoctors");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateMultipleDoctors", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "updateMultipleDoctors");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(calstmt);
        }

    }

    // Get Max Percentage Multiple Doctors
    /**
     *
     * @param ds
     * @param personcode
     * @param maxId
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public String getRail_eli_status(DataSource ds, String personcode) throws SADAREMDBException, SQLException {

        String eli = null;
        PreparedStatement pstmt = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();

            pstmt = con.prepareStatement("select Railway_Certificate from tblAllDisabilityPerson_Common_Needs where person_code=?");
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                eli = rs.getString(1);

            }




        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRail_eli_status", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getRail_eli_status");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRail_eli_status", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getRail_eli_status");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }


        return eli;

    }

    public int insertRailwayDoctordetails(DataSource ds, String pid, String dn, String rn, String de, String status, String rail, String ip, String role) throws SADAREMDBException, SQLException {

        PreparedStatement pstmt = null;
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        int i = 0;
        try {
            con = DBConnection.getConnection();
            /* ps=con.prepareStatement("select * from RailwaypassDoctor_Details where person_code=?");
            ps.setString(1, pid);
            rs=ps.executeQuery();
            if(rs.next()){
            pstmt=con.prepareStatement("update RailwaypassDoctor_Details set Status=? where person_code=?");
            pstmt.setString(1, "Inactive");
            pstmt.setString(2, pid);
            pstmt.executeUpdate();
            }

            pstmt = con.prepareStatement("insert into RailwaypassDoctor_Details values(?,?,?,?,?,?,GETDATE(),GETDATE(),?,?)");
            pstmt.setString(1, pid);
            pstmt.setString(2, dn);
            pstmt.setString(3, rn);
            pstmt.setString(4, de);
            pstmt.setString(5, rail);
            pstmt.setString(6, status);
            pstmt.setString(7, ip);
            pstmt.setString(8, role);



            if(pstmt.executeUpdate()>0){

            i=1;

            }*/

            ps = con.prepareStatement("select * from RailwaypassDoctor_Details where person_code=? and status='Active'");
            ps.setString(1, pid); //pstmt.setString(2, rail);
            rs = ps.executeQuery();
            if (rs.next()) {
                // String rai=rs.getString("Railway_Certificate");
                if (rail != null && rail.equalsIgnoreCase("1")) {
                    i = 1;
                    pstmt = con.prepareStatement("update RailwaypassDoctor_Details set Updated_Date=GETDATE() , Railway_Certificate=? where person_code=? and status='Active'");
                    pstmt.setString(1, rail);
                    pstmt.setString(2, pid);
                    pstmt.executeUpdate();
                } else if (rail != null && rail.equalsIgnoreCase("0")) {
                    i = 1;
                    pstmt = con.prepareStatement("update RailwaypassDoctor_Details set Updated_Date=GETDATE() ,Railway_Certificate=? where person_code=? and status='Active'");
                    pstmt.setString(1, rail);
                    pstmt.setString(2, pid);
                    pstmt.executeUpdate();
                } else {
                    i = 1;
                    pstmt = con.prepareStatement("update RailwaypassDoctor_Details set Updated_Date=GETDATE(),Status=?,Railway_Certificate=? where person_code=? ");
                    pstmt.setString(1, "Inactive");
                    pstmt.setString(2, rail);
                    pstmt.setString(3, pid);
                    pstmt.executeUpdate();
                }

                // pstmt=con.prepareStatement("update RailwaypassDoctor_Details set Status=? where person_code=?");
                // pstmt.setString(1, "Inactive");
                //  pstmt.setString(1, pid);
                // pstmt.executeUpdate();
            } else {
                if (dn != null && rn != null) {
                    pstmt = con.prepareStatement("insert into RailwaypassDoctor_Details values(?,?,?,?,?,?,GETDATE(),GETDATE(),?,?)");
                    pstmt.setString(1, pid);
                    pstmt.setString(2, dn);
                    pstmt.setString(3, rn);
                    pstmt.setString(4, de);
                    pstmt.setString(5, rail);
                    pstmt.setString(6, status);
                    pstmt.setString(7, ip);
                    pstmt.setString(8, role);



                    if (pstmt.executeUpdate() > 0) {

                        i = 1;

                    }
                }
            }
            return i;


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertRailwayDoctordetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "insertRailwayDoctordetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertRailwayDoctordetails", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "insertRailwayDoctordetails");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(ps);
            DBConnection.closeConnection(con);
        }
    }

    public String getDoctorprefix(DataSource ds, String campid, String distid) throws SADAREMDBException, SQLException {

        String prefix = null;
        PreparedStatement pstmt = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            // con.setAutoCommit(false);
            pstmt = con.prepareStatement("select prefexofdoctorone from dbo.tblDoctorsInformation_Details "
                    + " where type_of_disability = 'D.Mental Retardation' and district_id=? and camp_id=?");
            pstmt.setString(1, distid.trim());
            pstmt.setString(2, campid.trim());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                prefix = rs.getString(1);
            }


            /* pstmt = con.prepareStatement("select Railway_Certificate from tblAllDisabilityPerson_Common_Needs where person_code=?");
            pstmt.setString(1, personcode);
            rs=pstmt.executeQuery();
            while(rs.next()){
            partADTO.setRail(rs.getString(1));

            }*/
            //  con.commit();
            // con.setAutoCommit(true);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDoctorprefix", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getDoctorprefix");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDoctorprefix", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getDoctorprefix");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }

        return prefix;
    }

    public PartADTO getMaxMultipleDoctorsDetails(DataSource ds, String personcode, String maxId) throws SADAREMDBException, SQLException {

        PreparedStatement pstmt = null;
        PartADTO partADTO = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            // con.setAutoCommit(false);
            pstmt = con.prepareStatement("SELECT Doctor_Name,Doctor_RegNumber,Doctor_Designation FROM dbo.tblPerson_MultipleDisability_Doctors_Details WHERE Person_Code = '" + personcode + "' and Disability_ID='" + maxId + "' and Status = 'Active'");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                partADTO = new PartADTO();
                partADTO.setDoctor1name(rs.getString("Doctor_Name"));
                partADTO.setDoctor1regnumber(rs.getString("Doctor_RegNumber"));
                partADTO.setDoctor1designation(rs.getString("Doctor_Designation"));
            }


            /* pstmt = con.prepareStatement("select Railway_Certificate from tblAllDisabilityPerson_Common_Needs where person_code=?");
            pstmt.setString(1, personcode);
            rs=pstmt.executeQuery();
            while(rs.next()){
            partADTO.setRail(rs.getString(1));

            }*/
            //  con.commit();
            // con.setAutoCommit(true);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaxMultipleDoctorsDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getMaxMultipleDoctorsDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaxMultipleDoctorsDetails", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getMaxMultipleDoctorsDetails");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }
        return partADTO;
    }

    // Update Maximum percentage Multiple Doctors
    /**
     *
     * @param ds
     * @param personcode
     * @param maxId
     * @param partADTO
     * @throws SADAREMDBException,SQLException
     */
    public void updateMaxMultipleDoctorsDetails(DataSource ds, String personcode, String maxId, PartADTO partADTO) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        Connection con = null;
        Statement stmt = null;
        String doctorname = null;
        String doctorregNumber = null;
        String doctorDesignation = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            doctorname = partADTO.getDoctor1name();
            doctorregNumber = partADTO.getDoctor1regnumber();
            doctorDesignation = partADTO.getDoctor1designation();
            String query = "UPDATE tblPerson_Disability_Details SET First_Doctor_Name='" + doctorname + "', First_Doctor_RegNumber='" + doctorregNumber + "' , First_Doctor_Designation='" + doctorDesignation + "'  WHERE Person_Code='" + personcode + "'";
            stmt.executeUpdate(query);
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateMaxMultipleDoctorsDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "updateMaxMultipleDoctorsDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateMaxMultipleDoctorsDetails", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "updateMaxMultipleDoctorsDetails");
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(calstmt);
            DBConnection.closeConnection(con);
        }

    }

    /**
     *
     * @param ds
     * @param personcode
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public ArrayList getMultipleDisabilityDisabilityIds(DataSource ds, String personcode) throws SADAREMDBException, SQLException {

        PreparedStatement pstmt = null;
        String bdMultipleDisableId = null;
        Connection con = null;
        ResultSet rs = null;
        ArrayList multipleDisabilityIdList = null;

        try {
            multipleDisabilityIdList = new ArrayList();
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("SELECT Disability_ID FROM dbo.tblPerson_MultipleDisability_Doctors_Details WHERE Person_Code = '" + personcode + "'");
            rs = pstmt.executeQuery();
            while (rs.next()) {

                bdMultipleDisableId = Integer.toString(rs.getInt("Disability_ID"));
                multipleDisabilityIdList.add(bdMultipleDisableId);
            }
            con.commit();
            con.setAutoCommit(true);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleDisabilityDisabilityIds", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getMultipleDisabilityDisabilityIds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleDisabilityDisabilityIds", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getMultipleDisabilityDisabilityIds");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }
        return multipleDisabilityIdList;
    }

    /**
     *
     * @param ds
     * @param personcode
     * @throws org.bf.disability.Exception.SADAREMException
     */
    public void deletePreviousLocomotorDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        CallableStatement calstmt = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            calstmt = con.prepareCall("{Call SP_DATAINACTIVEFORLOCOMOTORTABLES(?)}");
            calstmt.setString(1, personcode);
            calstmt.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousLocomotorDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "deletePreviousLocomotorDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousLocomotorDetails", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "deletePreviousLocomotorDetails");
        } finally {
            DBConnection.closeStatement(calstmt);
            DBConnection.closeConnection(con);
        }
    }

    /**
     *
     * @param ds
     * @param personcode
     * @throws org.bf.disability.Exception.SADAREMException
     */
    public void deletePreviousVisualImpaimentDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        CallableStatement calstmt = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            calstmt = con.prepareCall("{Call SP_DATAINACTIVEFORVISUALIMPAIRMENTTABLES(?)}");
            calstmt.setString(1, personcode);
            calstmt.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousVisualImpaimentDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "deletePreviousVisualImpaimentDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousVisualImpaimentDetails", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "deletePreviousVisualImpaimentDetails");
        } finally {
            DBConnection.closeStatement(calstmt);
            DBConnection.closeConnection(con);
        }
    }

    /**
     *
     * @param ds
     * @param personcode
     * @throws org.bf.disability.Exception.SADAREMException
     */
    public void deletePreviousHearingImpairmentDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        CallableStatement calstmt = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            calstmt = con.prepareCall("{Call SP_DATAINACTIVEFORHEARINGIMPAIRMENTTABLES(?)}");
            calstmt.setString(1, personcode);
            calstmt.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousHearingImpairmentDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "deletePreviousHearingImpairmentDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousHearingImpairmentDetails", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "deletePreviousHearingImpairmentDetails");
        } finally {
            DBConnection.closeStatement(calstmt);
            DBConnection.closeConnection(con);
        }
    }

    /**
     *
     * @param ds
     * @param personcode
     * @throws org.bf.disability.Exception.SADAREMException
     */
    public void deletePreviousMentalRetardationDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        CallableStatement calstmt = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            calstmt = con.prepareCall("{Call SP_DATAINACTIVEFORMRTABLES(?)}");
            calstmt.setString(1, personcode);
            calstmt.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousMentalRetardationDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "deletePreviousMentalRetardationDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousMentalRetardationDetails", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "deletePreviousMentalRetardationDetails");
        } finally {
            DBConnection.closeStatement(calstmt);
            DBConnection.closeConnection(con);
        }
    }

    /**
     *
     * @param ds
     * @param personcode
     * @throws org.bf.disability.Exception.SADAREMException
     */
    public void deletePreviousMentalIllnessDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        CallableStatement calstmt = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            calstmt = con.prepareCall("{Call SP_DATAINACTIVEFORMITABLES(?)}");
            calstmt.setString(1, personcode);
            calstmt.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousMentalIllnessDetails", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "deletePreviousMentalIllnessDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousMentalIllnessDetails", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "deletePreviousMentalIllnessDetails");
        } finally {
            DBConnection.closeStatement(calstmt);
            DBConnection.closeConnection(con);
        }
    }

    public String rollBackEmployeeStatus(String employeeId, PartADTO partadto, DataSource ds) throws SADAREMDBException, SQLException {

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String PersonCode = null;
        int i = 0;
        try {
            con = DBConnection.getConnection();
            if (employeeId != null && !"".equals(employeeId)) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                String todaydate = dateFormat.format(calendar.getTime());
                pstmt = con.prepareStatement("update dbo.SERP_EmployeesDetails set Status = 'L',District_ID =?,PensionID =?, Updated_Date =?,ReasonforPersonStatus=? where EmployeeID = ?");
                pstmt.setString(1, null);
                pstmt.setString(2, null);
                pstmt.setString(3, todaydate);
                pstmt.setString(4, "Live");
                pstmt.setString(5, employeeId);
                i = pstmt.executeUpdate();
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "rollBackEmployeeStatus", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "rollBackEmployeeStatus");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "rollBackEmployeeStatus", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "rollBackEmployeeStatus");
        } finally {//added by rekha
            DBConnection.closeResultSet(rs);
            //DBConnection.closeStatement(cs);
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
        }

        return PersonCode;

    }

    /**
     *
     * @param personcode
     * @param partadto
     * @param ds
     * @throws SADAREMDBException,SQLException
     */
    public int insertDisabilityDetailsAU(String personcode, PartADTO partadto, String personStatus, DataSource ds) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        Connection con = null;
        int i = 0;
        String Status = personStatus;
        String sql = null;
        String categoryID = null;
        String categoryCount = null;
        Statement st = null;
        ResultSet rs = null;



        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            sql = "select categoryId,categoryCount from AppellateAuthorityandTemporary_RegistrationDetails where vieweditmode='View' and status='Active' and  person_code='" + personcode + "'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                categoryID = rs.getString(1);
                categoryCount = rs.getString(2);
            }

            if (Status.equals("Eligible")) {
                calstmt = con.prepareCall("{Call SP_tblPerson_Disability_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                calstmt.setString(1, personcode);
                calstmt.setInt(2, partadto.getDisabilityId());
                calstmt.setString(3, partadto.getDisabilitySubIds());
                calstmt.setString(4, partadto.getDisabilitySubSubIds());
                calstmt.setString(5, partadto.getOthertypeofdisability());
                calstmt.setString(6, partadto.getDiagnosisofdisability());
                calstmt.setString(7, partadto.getHospitalname());
                calstmt.setString(8, partadto.getHospitaladdress());
                calstmt.setString(9, partadto.getDoctor1name());
                calstmt.setString(10, partadto.getDoctor1regnumber());
                calstmt.setString(11, partadto.getDoctor1designation());
                calstmt.setString(12, partadto.getDoctor2name());
                calstmt.setString(13, partadto.getDoctor2regnumber());
                calstmt.setString(14, partadto.getDoctor2designation());
                calstmt.setString(15, partadto.getDoctor3name());
                calstmt.setString(16, partadto.getDoctor3regnumber());
                calstmt.setString(17, partadto.getDoctor3designation());
                calstmt.setString(18, partadto.getCamp_venue());
                calstmt.setString(19, partadto.getLoginid());
                calstmt.setString(20, partadto.getSystemip());

                if (categoryID != null && categoryCount != null) {
                    calstmt.setInt(21, Integer.parseInt(categoryID));
                    calstmt.setInt(22, Integer.parseInt(categoryCount));
                } else {
                    calstmt.setInt(21, 1);
                    calstmt.setInt(22, 1);
                }

                i = calstmt.executeUpdate();
                calstmt.close();

                calstmt = con.prepareCall("{Call SP_tblPerson_Causes_of_Disability_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                calstmt.setString(1, personcode);
                calstmt.setString(2, partadto.getConditiondisabilityIds());
                calstmt.setString(3, partadto.getConditionindisability());

                calstmt.setInt(4, Integer.parseInt(partadto.getYearsfortemporary()));
                calstmt.setBoolean(5, partadto.isCongenital());
                calstmt.setBoolean(6, partadto.isCongenitalbettereye());
                calstmt.setBoolean(7, partadto.isCongenitalworseeye());
                calstmt.setBoolean(8, partadto.isHereditary());
                calstmt.setBoolean(9, partadto.isHereditarybettereye());
                calstmt.setBoolean(10, partadto.isHereditaryworseeye());
                calstmt.setBoolean(11, partadto.isBirthinjury());
                calstmt.setBoolean(12, partadto.isBirthinjurybettereye());
                calstmt.setBoolean(13, partadto.isBirthinjuryworseeye());
                calstmt.setBoolean(14, partadto.isDiseaseInfection());
                calstmt.setBoolean(15, partadto.isDiseaseInfectionbettereye());
                calstmt.setBoolean(16, partadto.isDiseaseInfectionworseeye());
                calstmt.setBoolean(17, partadto.isMalnutrition());
                calstmt.setBoolean(18, partadto.isMalnutritionbettereye());
                calstmt.setBoolean(19, partadto.isMalnutritionworseeye());
                calstmt.setBoolean(20, partadto.isAccident());
                calstmt.setBoolean(21, partadto.isAccidentbettereye());
                calstmt.setBoolean(22, partadto.isAccidentworseeye());
                calstmt.setString(23, partadto.getOtherconditionofdisability());
                calstmt.setBoolean(24, partadto.isHighfever());
                calstmt.setBoolean(25, partadto.isEpilepsy());
                calstmt.setBoolean(26, partadto.isBirthasphyxia());
//                calstmt.setBoolean(24, partadto.isCongenitalvisual());
//                calstmt.setBoolean(25, partadto.isHereditaryvisual());
//                calstmt.setBoolean(26, partadto.isBirthinjuryvisual());
//                calstmt.setBoolean(27, partadto.isDiseaseInfectionvisual());
//                calstmt.setBoolean(28, partadto.isMalnutritionvisual());
//                calstmt.setBoolean(29, partadto.isAccidentvisual());
                //calstmt.setBoolean(24, partadto.isHighfever());
                //calstmt.setBoolean(25, partadto.isEpilepsy());
                calstmt.setString(27, partadto.getLoginid());
                calstmt.setString(28, partadto.getSystemip());

                if (categoryID != null && categoryCount != null) {
                    calstmt.setInt(29, Integer.parseInt(categoryID));
                    calstmt.setInt(30, Integer.parseInt(categoryCount));
                } else {
                    calstmt.setInt(29, 1);
                    calstmt.setInt(30, 1);
                }
                i = calstmt.executeUpdate();
                calstmt.close();
                calstmt = con.prepareCall("{Call SP_tbl_Person_Disability_Cando_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                calstmt.setString(1, personcode);
                calstmt.setString(2, partadto.getF_can());
                calstmt.setString(3, partadto.getPp_can());
                calstmt.setString(4, partadto.getL_can());
                calstmt.setString(5, partadto.getKc_can());
                calstmt.setString(6, partadto.getB_can());
                calstmt.setString(7, partadto.getS_can());
                calstmt.setString(8, partadto.getSt_can());
                calstmt.setString(9, partadto.getW_can());
                calstmt.setString(10, partadto.getRw_can());
                calstmt.setString(11, partadto.getSe_can());
                calstmt.setString(12, partadto.getH_can());
                calstmt.setString(13, partadto.getLoginid());
                calstmt.setString(14, partadto.getSystemip());

                if (categoryID != null && categoryCount != null) {
                    calstmt.setInt(15, Integer.parseInt(categoryID));
                    calstmt.setInt(16, Integer.parseInt(categoryCount));
                } else {
                    calstmt.setInt(15, 1);
                    calstmt.setInt(16, 1);
                }

                calstmt.executeUpdate();
            } else {

                calstmt = con.prepareCall("{Call SP_tblRejectPerson_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                calstmt.setString(1, personcode);
                calstmt.setInt(2, partadto.getDisabilityId());
                calstmt.setString(3, partadto.getOthertypeofdisability());
                calstmt.setString(4, partadto.getReferredto());
                calstmt.setString(5, partadto.getSurgery());
                calstmt.setString(6, partadto.getCouncellingandguidance());
                //calstmt.setBoolean(6,partadto.isSpeechtherapy());
                calstmt.setString(7, partadto.getHearingaid());
                calstmt.setString(8, partadto.getBehaviourmodification());
                calstmt.setString(9, partadto.getPhychotherapy());
                calstmt.setString(10, partadto.getAdmissioninpsychiatrichospital());
                calstmt.setString(11, partadto.getPhysiotherapy());
                calstmt.setString(12, partadto.getLowvisionaid());
                calstmt.setString(13, partadto.getAnyotherneed());
                calstmt.setString(14, partadto.getHospitalname());
                calstmt.setString(15, partadto.getHospitaladdress());
                calstmt.setString(16, partadto.getDoctor1name());
                calstmt.setString(17, partadto.getDoctor1regnumber());
                calstmt.setString(18, partadto.getDoctor1designation());
                calstmt.setString(19, partadto.getDoctor2name());
                calstmt.setString(20, partadto.getDoctor2regnumber());
                calstmt.setString(21, partadto.getDoctor2designation());
                calstmt.setString(22, partadto.getDoctor3name());
                calstmt.setString(23, partadto.getDoctor3regnumber());
                calstmt.setString(24, partadto.getDoctor3designation());
                calstmt.setString(25, partadto.getCamp_venue());
                calstmt.setString(26, partadto.getLoginid());
                calstmt.setString(27, partadto.getSystemip());

                if (categoryID != null && categoryCount != null) {
                    calstmt.setInt(28, Integer.parseInt(categoryID));
                    calstmt.setInt(29, Integer.parseInt(categoryCount));
                } else {
                    calstmt.setInt(28, 1);
                    calstmt.setInt(29, 1);
                }

                i = calstmt.executeUpdate();
            }
            calstmt.close();
            con.commit();
            con.setAutoCommit(true);



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertDisabilityDetailsAU", "PartADAO1", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "insertDisabilityDetailsAU");
        } catch (Exception sqlEx) {
           con.rollback();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertDisabilityDetailsAU", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "insertDisabilityDetailsAU");
        } finally {
            DBConnection.closeStatement(calstmt);
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);

        }
        return i;
    }
}












