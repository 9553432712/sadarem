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

import org.apache.log4j.Logger;
import org.bf.disability.Constants.CertificateConstants;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonFiledsChecking;
import org.bf.disability.dto.AppealPersonalDataDTO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.form.PartAForm;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.service.TransactionService;
import org.bf.disability.servicefactory.TerritoryServiceFactory;
import org.bf.disability.servicefactory.TransactionFactory;

import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author demo
 */
/** Creates a new instance of PartADAO */
public class PartADAO {

    /**
     *
     * @param datasource
     * @return
     * @throws SADAREMDBException,SQLException
     */
	static final private Logger log = Logger.getLogger(PartADAO.class);
	Connection lcon;
	String lStrQuery;
	
	
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

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityList", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getDisabilityList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityList", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getDisabilityList");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);



        }
        return disabilityList;
    }

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



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTotalDisExists", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getTotalDisExists");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTotalDisExists", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getTotalDisExists");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

            DBConnection.closeResultSet(rs1);
            DBConnection.closeStatement(stmt1);
        }
        return exi;
    }

    public PartADTO getRailwayDoctorDetails(DataSource ds, String pension_id) throws SADAREMDBException, SQLException {

        ArrayList disabilityList = new ArrayList();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        PartADTO partADTO = null;
        PreparedStatement stmt1 = null;
        ResultSet rs1 = null;


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
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRailwayDoctorDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getRailwayDoctorDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRailwayDoctorDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getRailwayDoctorDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

            DBConnection.closeResultSet(rs1);
            DBConnection.closeStatement(stmt1);
        }
        return partADTO;
    }

    public String getDisabilityIdforCertificate(DataSource ds, String personcode) throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String disabiltyid = null;
        try {
            con = DBConnection.getConnection();

            /* pstmt=con.prepareStatement("select d.disability_id from tblperson_personal_details p join  tblPerson_Disability_Details d on(p.person_code=d.person_code) " +
            " join tblPerson_Disability_TotalValue_Details t on(p.person_code=t.person_code) " +
            " where p.person_code=?  and p.view_edit_mode='View' " +
            " and p.status='Active' and d.status='Active' and t.status='Active' ");*/

            pstmt = con.prepareStatement("select * from dbo.Certificate_With_PhysicalRequirements_RegistrationDetails where person_code=?");

            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                disabiltyid = rs.getString("disabilityid");
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityIdforCertificate", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getDisabilityIdforCertificate");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityIdforCertificate", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getDisabilityIdforCertificate");
        } finally {
            try {
                DBConnection.closeConnection(con);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeResultSet(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return disabiltyid;

    }

    //appeal authority
    public synchronized int insertPersonalDetailsForAppeal(DataSource ds, AppealPersonalDataDTO dto, String loginid) throws SADAREMDBException, SQLException {
        ArrayList disabilityList = new ArrayList();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        int i = 0; //byte category=0;
        byte dis = 0;
        String errormsg = "";
        int count = 1;
        byte categoryCount = 1;
        byte category = 2;
//,LoginID
        try {
            if (dto.getDisability() != null && dto.getDisability() != "") {
                dis = Byte.parseByte(dto.getDisability());
            }
            con = DBConnection.getConnection();//AppellateAuthorityandTemporary_RegistrationDetails

            stmt = con.prepareStatement("select count(*) from AppellateAuthorityandTemporary_RegistrationDetails where Person_Code=?  and categoryid=2 ");
            stmt.setString(1, dto.getPersoncode());
            //stmt.setByte(2, dis);
            // stmt.setString(3, dto.getPersoncode());

            rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getByte(1);

            }
            if (count == 0) {
                stmt = con.prepareStatement("insert into AppellateAuthorityandTemporary_RegistrationDetails (Person_Code,DisabilityID,Percentage,CategoryID,CategoryCount,DisabilityStatus,ViewEditMode,CreatedDate,UpdatedDate,Status, Loginid, RationCardNumber, SerialNumber) values(?,?,?,?,?,?,?,GETDATE(),GETDATE(),?,?,?,?)");
                //   stmt = con.prepareStatement("insert into AppellateAuthorityandTemporary_RegistrationDetails (Person_Code,DisabilityID,Percentage,DisabilityStatus,ViewEditMode,CreatedDate,UpdatedDate,Status) values(?,?,?,?,?,GETDATE(),GETDATE(),?)");
                stmt.setString(1, dto.getPersoncode());
                stmt.setByte(2, dis);
                stmt.setString(3, dto.getPercentage());
                //  if(dto.getCategoryid()!=null)
                // category=Byte.parseByte(dto.getCategoryid());
                stmt.setByte(4, category);
                stmt.setByte(5, categoryCount);
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
            } else {
                errormsg = "Already Registered and assessment not completed. ";
                dto.setError_msg(errormsg);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPersonalDetailsForAppeal", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertPersonalDetailsForAppeal");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPersonalDetailsForAppeal", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertPersonalDetailsForAppeal");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

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
        String mode = null;
//        boolean exi = false;
//        boolean ex = false;
        boolean beforeoneyear = false;
        String query = "";
        String applieddate = null;
        String ass_date = null;
        //  PreparedStatement ps1=null;
        // ResultSet rs2=null;
        try {
            dto.setError_msg(errormsg);
            con = DBConnection.getConnection();
            int maxcount = 0;
//            stmt1=con.prepareStatement("select * from AppellateAuthorityandTemporary_RegistrationDetails where Person_Code=? and categoryid=3");
//             stmt1.setString(1, pid);
//             rs1=stmt1.executeQuery();
//             if(rs1.next()){
//                 allowtemporary=true;
//             }
            stmt1 = con.prepareStatement("select max(CategoryCount) from AppellateAuthorityandTemporary_RegistrationDetails where Person_Code=? and categoryid=2");
            stmt1.setString(1, pid);
            rs1 = stmt1.executeQuery();
            if (rs1.next()) {
                maxcount = rs1.getInt(1);
            }
            if (maxcount != 0) {
                query = " and CategoryCount=" + maxcount;
            }

            stmt1 = con.prepareStatement("select ViewEditMode from AppellateAuthorityandTemporary_RegistrationDetails where Person_Code=? and categoryid=2" + query);
            stmt1.setString(1, pid);
            rs1 = stmt1.executeQuery();
            if (rs1.next()) {
                mode = rs1.getString("ViewEditMode");
            }

            stmt1 = con.prepareStatement("select * from AppellateAuthorityandTemporary_RegistrationDetails "
                    + "where Person_Code=? and DATEDIFF(YEAR,UPDATEDDATE, GETDATE())<1  and categoryid=2" + query);      // DATEDIFF(YEAR, GETDATE(),UPDATEDDATE)<= 1");
            stmt1.setString(1, pid);
            rr = stmt1.executeQuery();
            if (rr.next()) {
                beforeoneyear = true;
            }


            CommonDAOImpl comObj = new CommonDAOImpl();
            HashMap GEODtls = new HashMap();
  			GEODtls=comObj.getGEODetailsbySADAREMID(pid);
  			String distIdFromSadaremId	=	GEODtls.get("districtid").toString();
  			String mandIdFromSadaremId	=	GEODtls.get("mandalid").toString();
  			String vilIdFromSadaremId	=	GEODtls.get("villageid").toString();
            

            if (mode != null && !mode.equalsIgnoreCase("") && !mode.equalsIgnoreCase("null") && mode.equalsIgnoreCase("Edit")) {
                stmt1 = con.prepareStatement("select schedule_date from Appeal_Authority_Schedule where  "
                        + "district_id =? and mandal_id=? "
                        + "and village_id=? and schedule_date>GETDATE()");

                stmt1.setString(1, distIdFromSadaremId);
                stmt1.setString(2, mandIdFromSadaremId);
                stmt1.setString(3, vilIdFromSadaremId);
                
                rr = stmt1.executeQuery();
                if (rr.next()) 
                {
                    applieddate = rr.getString("schedule_date");

                    String formdate = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd ").parse(applieddate));
                    errormsg = "Already Registered for Appealet Authority and your assessment date will be on " + formdate;
                    dto.setError_msg(errormsg);
                }
                else 
                {
                    stmt1 = con.prepareStatement("select schedule_date from Appeal_Authority_Schedule where  "
                            + "district_id =? and mandal_id=? "
                            + "and village_id=? and schedule_date<GETDATE()");
                    
                    stmt1.setString(1, distIdFromSadaremId);
                    stmt1.setString(2, mandIdFromSadaremId);
                    stmt1.setString(3, vilIdFromSadaremId);
                    
                    rr = stmt1.executeQuery();
                    if (rr.next()) 
                    {
                        applieddate = rr.getString(1);
                        String formdate = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(applieddate));
                        errormsg = "Already Registered and assessment not completed on the schedule date " + formdate + ", Reschedule date to be given for Second Time Re-Assessment";
                        dto.setError_msg(errormsg);
                    } else {
                        //String formdate = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(ass_date));
                        //errormsg = "Already Registered for Appealet Authority on " + formdate + " and your assessment date will be informed soon ";
                        errormsg = "Already Registered for Second Time Re-Assessment and your assessment date will be informed soon ";
                        dto.setError_msg(errormsg);
                    }
                }
            } else if (mode != null && !mode.equalsIgnoreCase("") && !mode.equalsIgnoreCase("null") && mode.equalsIgnoreCase("View"))//&&beforeoneyear)
            {

//                    if(beforeoneyear){
//                        stmt1 = con.prepareStatement("select updateddate + 365 from AppellateAuthorityandTemporary_RegistrationDetails "
//                                + "where person_code=? and categoryid=2"+query);
//                        stmt1.setString(1, pid);
//                        rr = stmt1.executeQuery();
//                        if (rr.next()) {
//                            applieddate = rr.getString(1);
//                        }
//                        String formdate = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd ").parse(applieddate));
                errormsg = "Already Second Time Re-Assessment assessment completed";
                dto.setError_msg(errormsg);
//                    }
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
                        stmt = con.prepareStatement("select totaldisability,d.Disability_ID from dbo.tblPerson_Disability_TotalValue_Details t,tblPerson_Disability_Details d "
                                + "where t.person_code=d.person_code and t.person_code=? and t.status='Active'");
                        stmt.setString(1, pid);
                        String dis = null;
                        String disbId=null;
                        float diss = 0,disId=0;

                        rs1 = stmt.executeQuery();
                        if (rs1.next()) {
                            dis = rs1.getString(1);
                            disbId = rs1.getString(2);
                            if (dis != null) {
                                diss = Float.parseFloat(dis);
                            }
                            if (disbId != null) {
                            	disId = Float.parseFloat(disbId);
                            }
                            if (!(disId==3) && diss >= 40) {

                                errormsg = name + " is " + " not Eligible for  Second Time Re-Assessment due to above 40 percentage";

                            } 
                            else if ((disId==3) && diss >= 51) {

                                errormsg = name + " is " + " not Eligible for  Second Time Re-Assessment due to above 51 percentage";

                            } else if (!(disId==3) &&  diss < 40) {
                                dataexists = true;
                            }
                            else if ((disId==3) &&  diss < 51) {
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
                                    + "FROM  TBLPERSON_PERSONAL_DETAILS P  with (nolock) ,"
                                    + "TBLPERSON_DISABILITY_DETAILS PD ,TBLPERSON_DISABILITY_TOTALVALUE_DETAILS PT ,TBLMANDAL_DETAILS M,TBLVILLAGE_DETAILS V "
                                    + "WHERE P.PERSON_CODE  = ?   AND P.PERSON_CODE  = PD.PERSON_CODE   AND P.PERSON_CODE  = PT.PERSON_CODE   AND PD.PERSON_CODE = PT.PERSON_CODE    AND "
                                    + "M.DISTRICT_ID  = P.DISTRICT_ID AND M.MANDAL_ID    = P.MANDAL_ID AND V.DISTRICT_ID  =P.DISTRICT_ID  AND V.MANDAL_ID    = P.MANDAL_ID AND "
                                    + "V.VILLAGE_ID   = P.VILLAGE_ID AND P.STATUS = 'Active' AND PD.STATUS = 'Active'  AND PT.STATUS = 'Active' AND ";
                      
                            sql = sql + "  ((PD.Disability_id='3' AND  PT.TOTALDISABILITY<51) or  (PD.Disability_id<>'3' AND  PT.TOTALDISABILITY<40)) ";

                            pstmt = con.prepareStatement(sql);
                            pstmt.setString(1, pid);
                            rs = pstmt.executeQuery();

                            if (rs.next()) {
                                //                                if(allowtemporary)
                                //                                  stmt = con.prepareStatement("select * from TBLPERSON_DISABILITY_DETAILS where  person_code=? and status='Active'");
                                //                                else
                                stmt = con.prepareStatement("select * from TBLPERSON_DISABILITY_DETAILS where  person_code=? and status='Active'");
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
                                    errormsg = " Assesment  not Completed ";
                                    dto.setError_msg(errormsg);
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
                                    + "ELSE 'NE' END AS DISABILITYNAME, M.MANDAL_NAME, V.VILLAGE_NAME, P.RATIONCARD_NUMBER  FROM TBLPERSON_PERSONAL_DETAILS P  with (nolock) , "
                                    + "TBLREJECTPERSON_DETAILS    PR, TBLMANDAL_DETAILS M, TBLVILLAGE_DETAILS V WHERE "
                                    + "P.PERSON_CODE  = ?  AND "
                                    + " P.PERSON_CODE  = PR.PERSON_CODE           AND "
                                    + " M.DISTRICT_ID  = P.District_ID AND "
                                    + " M.MANDAL_ID    = P.Mandal_ID AND "
                                    + " V.DISTRICT_ID  = P.District_ID AND "
                                    + " V.MANDAL_ID    = P.MANDAL_ID AND "
                                    + " V.VILLAGE_ID   = P.VILLAGE_ID AND "
                                    + " P.STATUS       = 'Active'                 AND "
                                    + " PR.STATUS      = 'Active'   ";


                            pstmt = con.prepareStatement(sql);
                            pstmt.setString(1, pid);
                            rs = pstmt.executeQuery();
                            if (rs.next()) {
                                //                                if(allowtemporary)
                                //                                    stmt = con.prepareStatement("select * from TBLREJECTPERSON_DETAILS where  person_code=? and status='Active'");
                                //                                else
                                stmt = con.prepareStatement("select * from TBLREJECTPERSON_DETAILS where  person_code=? and status='Active'");
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
                                    errormsg = " Assesment  not Completed ";
                                    dto.setError_msg(errormsg);
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

            // }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDetailsForAppealAuthority", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getPersonalDetailsForAppealAuthority");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDetailsForAppealAuthority", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getPersonalDetailsForAppealAuthority");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeResultSet(rr);
            DBConnection.closeResultSet(rs1);
            DBConnection.closeStatement(calstmt);


            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(stmt1);
            DBConnection.closeStatement(pstmt);

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
        PreparedStatement pstmt = null;
        Connection con = null;
        PartADTO partADTO = null;
        String query = "";


        try {
            con = DBConnection.getConnection();
            
            query = "select Name,Address,VenueName from tblCamp_Details where Camp_ID=? and status='Active' ";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, campId);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                partADTO = new PartADTO();
                partADTO.setHospitalname(rs.getString("Name"));
                partADTO.setHospitaladdress(rs.getString("Address"));
                partADTO.setCamp_venue(rs.getString("VenueName"));
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getCampDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getCampDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);



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
        PreparedStatement pstmt = null;
        Connection con = null;
        String query="";


        try {
            con = DBConnection.getConnection();

            query = "select Sub_Disability_ID,Sub_Disability_Name from tblSubDisability_Details where Disability_ID = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, disabilityId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                PartADTO partADTO = new PartADTO();
                partADTO.setDisabilityLocoSubId(rs.getInt("Sub_Disability_ID"));
                partADTO.setDisabilityLocoSubName(rs.getString("Sub_Disability_Name"));
                disabilityLocoSubList.add(partADTO);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityLocoSubList", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getDisabilityLocoSubList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityLocoSubList", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getDisabilityLocoSubList");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);



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
        PreparedStatement pstmt = null;
        Connection con = null;
        String query = "";


        try {
            con = DBConnection.getConnection();
            query = "select Sub_Sub_Disability_ID,Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Disability_ID = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, disabilityId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                PartADTO partADTO = new PartADTO();
                partADTO.setDisabilityLocoSubSubId(rs.getInt("Sub_Sub_Disability_ID"));
                partADTO.setDisabilityLocoSubSubName(rs.getString("Sub_Sub_Disability_Name"));
                disabilityLocoSubSubList.add(partADTO);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityLocoSubSubList", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getDisabilityLocoSubSubList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityLocoSubSubList", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getDisabilityLocoSubSubList");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);



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
    public synchronized void insertDisabilityDetails(String personcode, PartADTO partadto, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        Connection con = null;

        String Status = partadto.getPersonstatus();
        String sql = null;
        ResultSet rs = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        PreparedStatement pstmt = null;
        String personStatus = null;
        int result = 0;
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();

        try {


            details = dao.getCategoryDetails(ds, personcode);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            /* Getting the categeoryIdncategoryCount */


            /* To check the person status weather he is Eligible / Rejected start here **/
            
            if (personcode != null) {
                sql = "select person_status from tblperson_personal_details  with (nolock) where person_code=?";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, personcode);
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        personStatus = rs.getString(1);
                    }
                }
            }
            /* End */

            if (Status != null && "Eligible".equals(Status) && personStatus != null && "Eligible".equals(personStatus)) {
                calstmt = con.prepareCall("{Call SP_tblPerson_Disability_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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

                calstmt.setInt(21, Integer.parseInt(details[0].toString()));
                calstmt.setInt(22, Integer.parseInt(details[1].toString()));


                calstmt.executeUpdate();
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
                calstmt.setString(27, partadto.getLoginid());
                calstmt.setString(28, partadto.getSystemip());

                calstmt.setInt(29, Integer.parseInt(details[0].toString()));
                calstmt.setInt(30, Integer.parseInt(details[1].toString()));


                calstmt.executeUpdate();
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
                calstmt.setInt(15, Integer.parseInt(details[0].toString()));
                calstmt.setInt(16, Integer.parseInt(details[1].toString()));

                result = calstmt.executeUpdate();

            } else if (Status != null && personStatus != null && "Rejected".equals(Status) && "Rejected".equals(personStatus)) {

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
                calstmt.setInt(28, Integer.parseInt(details[0].toString()));
                calstmt.setInt(29, Integer.parseInt(details[1].toString()));

                result = calstmt.executeUpdate();
                if (result != 0) {
                    transactionService.updateTransactionStatus(ds, "Completed", personcode);
                }
            }

            calstmt.close();
            con.commit();
            con.setAutoCommit(true);



        } catch (SQLException sqlEx) {
            transactionService.updateTransactionDetails(ds, "Error Disability Details", personcode);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertDisabilityDetails", "PartADAO", "DataBase");
            con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertDisabilityDetails");
        } catch (Exception sqlEx) {
            transactionService.updateTransactionDetails(ds, "Error Disability Details", personcode);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertDisabilityDetails", "PartADAO", "Code");
            con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertDisabilityDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(calstmt);

            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

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


        ResultSet rs = null;
        CallableStatement calstmt = null;
        Connection con = null;
        String personcodemax = null;
        String sql = null;
        //Statement st = null;
        //Statement st1 = null;
        PreparedStatement ps = null;
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        String districtid = partadto.getDistrict();
        String mandalid = partadto.getMandal();
        String villageid = partadto.getTownVillage();
        String habitationid = partadto.getHabitation();
        String panchayatid = partadto.getPanchayatiid();
        String assemblyid = partadto.getAssemblyid();
        HttpSession session = request.getSession();
        String reasonForStatus = null;
        String formdate = null;
        String dobdate = null;
        String campdate = null;
        int partAEnteredStatus = 0;
        boolean success = true;
        
        String habcode="";
        try 
        {
        	CommonDAO comObj = new CommonDAOImpl();
            con = DBConnection.getConnection();
            //st = con.createStatement();

            con.setAutoCommit(false);
            
            if (partadto.getFromdate() != null && !partadto.getFromdate().equalsIgnoreCase("")) 
            {
                Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getFromdate());
                formdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            }
            
            if (partadto.getDobday() != null && !partadto.getDobday().equalsIgnoreCase("")) 
            {
                Date ddate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getDobday());
                dobdate = new SimpleDateFormat("mm/dd/yyyy").format(ddate);
            }
            
            if (session.getAttribute("categoryIdAu") != null && session.getAttribute("categoryCountAu") != null) 
            {
                session.removeAttribute("categoryIdAu");
                session.removeAttribute("categoryCountAu");
            }

            int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));


            if (partadto.getReasonforstatus() != null && !partadto.getReasonforstatus().equals(""))
            {
                reasonForStatus = partadto.getReasonforstatus();


            }
            else 
            {
                if (session.getAttribute("personstatus") != null) 
                {
                    reasonForStatus = session.getAttribute("personstatus").toString();

                } 
                else
                {
                    if (session.getAttribute("personStatusId") != null)
                    {
                        reasonForStatus = session.getAttribute("personStatusId").toString();
                    }
                }
            }
            personcodemax = territoryservice.getHabitationCode(districtid, mandalid, panchayatid, assemblyid, villageid, habitationid, ds);

            habcode = comObj.getHabCode(districtid, mandalid, panchayatid, villageid, habitationid);
            
            

            if (partadto.getPersonstatus() != null) 
            {
                calstmt = con.prepareCall("{Call [SP_tblPerson_Personal_Details_Insert_PDPartA](?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

                calstmt.setString(1, personcodemax);
                calstmt.setString(2, partadto.getFormno());
                if (formdate != null) {
                    calstmt.setString(3, formdate);
                } else {
                    calstmt.setString(3, null);
                }
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
                if (partadto.isPensioncard() != false)
                {
                    calstmt.setBoolean(20, partadto.isPensioncard());
                } 
                else 
                {
                    calstmt.setBoolean(20, partadto.isPensioncard());
                }
                
                if (partadto.getPension_type() != null
                        && !partadto.getPension_type().equals(null)
                        && !partadto.getPension_type().equals("null")
                        && partadto.getPension_type().toString().length() > 0) 
                {
                    calstmt.setString(21, partadto.getPension_type());
                } 
                else 
                {
                    calstmt.setString(21, partadto.getPension_type());
                }
                
                calstmt.setString(22, "WPIII");
//                if (session.getAttribute("pensionIdDis") != null
//                        && !session.getAttribute("pensionIdDis").equals(null)
//                        && session.getAttribute("pensionIdDis").toString().length() > 0) {
//                    calstmt.setString(22, (String) session.getAttribute("pensionIdDis"));
//                } else {
//                    calstmt.setString(22, (String) session.getAttribute("pensionIdDis"));
//                }
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
                calstmt.setString(42, "PhaseIII");
                calstmt.setString(43, "Live");



                // Commented by mohan on 28/06/2011
                //7396610556 --
                // 8019627797-- medak

                //  if (partadto.getHabCode() != null) {
                //     calstmt.setString(44, partadto.getHabCode());
                //  } else {
                calstmt.setString(44, habcode);
                calstmt.setInt(45, 1);
                calstmt.setInt(46, 1);
                //rationCardSlno
                if (partadto.getRationCardSlno() != null) {
                    calstmt.setString(47, partadto.getRationCardSlno());
                } else {
                    calstmt.setString(47, null);


                }
                calstmt.setString(48, null);


                //  }
//                if (partadto.getAadharCardNo() != null && !partadto.getAadharCardNo().equals("")) {
//                    calstmt.setString(48, partadto.getAadharCardNo());
//                } else {
//                    calstmt.setString(48, null);
//                }

                //calstmt.setString(48, partadto.getAadharCardNo());

                partAEnteredStatus = calstmt.executeUpdate();


                int statusCount = 0;
                if (partAEnteredStatus != 0) {
                    transactionService.insertTransactionalDetails(ds, "PartA Details Entered", personcodemax, request);

                    int count = 0;
                    
                    String applicationNumber = "";
                    String query = "select count(*) from Disabilitycampdatedetails where districtid=?";
                    ps = con.prepareStatement(query);
                    ps.setString(1,partadto.getDistrict_id());
                    rs = ps.executeQuery();
                    if (rs != null && rs.next()) {
                        count = rs.getInt(1);
                    }
                    if (count > 0) {
//partadto.setDistrict_id("07");
                        String query1 = "select max(ackno) from Disabilitycampdatedetails where districtid = ?";
                        ps = con.prepareStatement(query1);
                        ps.setString(1, partadto.getDistrict_id());
                        rs = ps.executeQuery();
                        if (rs != null && rs.next()) {
                            rs.getString(1);
                            if (rs.getString(1) != null) {
                                long longCode = Long.parseLong(rs.getString(1));
                                if (rs.getString(1).startsWith("0")) {
                                    longCode++;

                                    String withZero = String.valueOf(longCode);

                                    applicationNumber = paddingString(withZero, withZero.length() + 1, '0');

                                } else {
                                    longCode++;
                                    applicationNumber = String.valueOf(longCode);
                                }

                            } else {
                                applicationNumber = partadto.getDistrict_id() + "00001";
                            }
                        }
                    } else {
                        applicationNumber = partadto.getDistrict_id() + "00001";
                    }
                    if (partadto.getPartaCampId() != null && !partadto.getPartaCampId().equals("0")) {
                        if (partadto.getPartaCampDate() != null && !partadto.getPartaCampDate().equalsIgnoreCase("0")) {
                            Date partcampdate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getPartaCampDate());
                            campdate = new SimpleDateFormat("mm/dd/yyyy").format(partcampdate);
                        }
                        String querys = "insert into Disabilitycampdatedetails (Personcode,Districtid,Disabilityid,CampId,"
                                + "Campdate,ackno,Createddate,Updateddate,Loginid,Sysytemip)"
                                + "values(?,?,?,?,"
                                + "?,?,getDate(),getDate(),?,?)";
                        ps = con.prepareStatement(querys);
                        ps.setString(1, personcodemax);
                        ps.setString(2, partadto.getDistrict_id());
                        ps.setString(3, partadto.getType_disability());
                        ps.setString(4, partadto.getPartaCampId());
                        ps.setString(5, campdate);
                        ps.setString(6, applicationNumber);
                        ps.setString(7, partadto.getLoginid());
                        ps.setString(8, partadto.getSystemip());
                        int i = ps.executeUpdate();
                        if (i > 0) {
                            String formdates = new SimpleDateFormat("MM/dd/yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(partadto.getPartaCampDate()));
                            calstmt = con.prepareCall("{Call Sp_Campcount_verification(?,?,?,?)}");
                            calstmt.setString(1, partadto.getDistrict_id());
                            calstmt.setString(2, partadto.getType_disability());
                            calstmt.setString(3, partadto.getPartaCampId());
                            calstmt.setString(4, formdates);
                            rs = calstmt.executeQuery();
                            if (rs != null && rs.next()) {
                                statusCount = rs.getInt(1);
                            }
                        }

                        if (statusCount == 1) {
                            query = "delete from dbo.tblPerson_Personal_Details where person_code=? ";
                            ps = con.prepareStatement(query);
                            ps.setString(1, personcodemax);
                            ps.executeUpdate();
                            query = "delete from Disabilitycampdatedetails where personcode=? ";
                            ps = con.prepareStatement(query);
                            ps.setString(1, personcodemax);
                            ps.executeUpdate();
                            request.setAttribute("dvalues", "Select Disabilities Exceeded To Selete Camp");

                        }
                    }
                    else 
                    {
                        int status = 0;
                        if (partadto.getNoofrowvalue() > 0)
                        {
                            for (int i = 0; i < partadto.getNoofrowvalue(); i++)
                            {
                                boolean flag = false;

                                if ((partadto.getDynaProperty(i + "#" + (1)) != null
                                        && !partadto.getDynaProperty(i + "#" + (1)).toString().equals("")
                                        && partadto.getDynaProperty(i + "#" + (1)).toString().length() > 0)
                                        && (partadto.getDynaProperty(i + "#" + (2)) != null
                                        && !partadto.getDynaProperty(i + "#" + (2)).toString().equals("")
                                        && partadto.getDynaProperty(i + "#" + (2)).toString().length() > 0)
                                        && (partadto.getDynaProperty(i + "#" + (3)) != null
                                        && !partadto.getDynaProperty(i + "#" + (3)).toString().equals("")
                                        && partadto.getDynaProperty(i + "#" + (3)).toString().length() > 0)) {
                                    flag = true;
                                }
                                if (flag) 
                                {
                                    String campdates = new SimpleDateFormat("MM/dd/yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(partadto.getDynaProperty(i + "#3").toString()));

                                    String querys = "insert into Disabilitycampdatedetails (Personcode,Districtid,Disabilityid,CampId,"
                                            + "Campdate,ackno,Createddate,Updateddate,Loginid,Sysytemip)"
                                            + "values(?,?,?,?,?,?,getDate(),getDate(),?,?)";
                                    ps = con.prepareStatement(querys);
                                    ps.setString(1, personcodemax);
                                    ps.setString(2, partadto.getDistrict_id());
                                    if (partadto.getDynaProperty(i + "#2").toString() != null) {
                                        ps.setString(3, partadto.getDynaProperty(i + "#2").toString());
                                    } else {
                                        ps.setString(3, null);
                                    }
                                    if (partadto.getDynaProperty(i + "#1").toString() != null) {
                                        ps.setString(4, partadto.getDynaProperty(i + "#1").toString());
                                    } else {
                                        ps.setString(4, null);
                                    }
                                    if (partadto.getDynaProperty(i + "#3").toString() != null) {
                                        ps.setString(5, campdates);
                                    } else {
                                        ps.setString(5, null);
                                    }
                                    ps.setString(6, applicationNumber);
                                    ps.setString(7, partadto.getLoginid());
                                    ps.setString(8, partadto.getSystemip());
                                    status = ps.executeUpdate();

                                }


                                if (status > 0) 
                                {
                                    Date partcampdate1 = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getDynaProperty(i + "#3").toString());
                                    String campdate1 = new SimpleDateFormat("mm/dd/yyyy").format(partcampdate1);
                                    calstmt = con.prepareCall("{Call Sp_Campcount_verification(?,?,?,?)}");
                                    calstmt.setString(1, partadto.getDistrict_id());
                                    calstmt.setString(2, partadto.getDynaProperty(i + "#2").toString());
                                    calstmt.setString(3, partadto.getDynaProperty(i + "#1").toString());
                                    calstmt.setString(4, campdate1);
                                    rs = calstmt.executeQuery();
                                    if (rs != null && rs.next())
                                    {
                                        statusCount = rs.getInt(1);
                                    }

                                    if (statusCount == 1) 
                                    {
                                        success = false;
                                    }
                                }
                            }
                            if (success == false)
                            {
                                query = "delete from dbo.tblPerson_Personal_Details where person_code=? ";
                                ps = con.prepareStatement(query);
                                ps.setString(1, personcodemax);
                                ps.executeUpdate();
                                for (int i = 0; i < partadto.getNoofrowvalue(); i++)
                                {
                                    Date partcampdate1 = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getDynaProperty(i + "#3").toString());
                                    String campdate1 = new SimpleDateFormat("mm/dd/yyyy").format(partcampdate1);

                                    query = "delete from Disabilitycampdatedetails where personcode=?"
                                            + " and disabilityid=? and campdate=?";
                                    ps = con.prepareStatement(query);
                                    ps.setString(1, personcodemax);
                                    ps.setString(2, partadto.getDynaProperty(i + "#2").toString());
                                    ps.setString(3, campdate1);
                                    ps.executeUpdate();

                                }
                                request.setAttribute("dvalues", "Select Disabilities Exceeded To Selete Camp");
                            }
                        }
                    }

                    con.commit();
                    con.setAutoCommit(true);

                    session.removeAttribute("personStatusId");
                    session.removeAttribute("pensionIdDis");
                }
            }
        }
        catch (SQLException sqlEx) 
        {
            transactionService.updateTransactionDetails(ds, "Error PartA Details", personcodemax);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPersonalDetails", "PartADAO", "DataBase");
            con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertPersonalDetails");
        } catch (Exception sqlEx) {
            transactionService.updateTransactionDetails(ds, "Error PartA Details", personcodemax);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPersonalDetails", "PartADAO", "Code");
            con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertPersonalDetails");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (calstmt != null) {
                    calstmt.close();
                }
                
                
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return personcodemax;
    }

    public int removeSadaremids(DataSource ds, String personcodemax) throws SQLException {
        int i = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        try {
            con = DBConnection.getConnection();
            if (personcodemax != null) {
                
                query = "delete from Disabilitycampdatedetails where personcode=?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, personcodemax);
                i=pstmt.executeUpdate();
                System.out.println(i);
                
                query = "delete from dbo.tblPerson_Personal_Details where person_code=? ";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, personcodemax);
                i=pstmt.executeUpdate();
                System.out.println(i);
            }


        } catch (Exception e) {
        } finally {
            if (con != null) {
                con.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return i;
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
        PreparedStatement pstmt = null;

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
                partADTO.setRationCardSlno(rs.getString("RationCard_Slno"));
                ///partADTO.setHabCode(rs.getString("HabCode"));
                partADTO.setAadharCardNo(rs.getString("proof_id"));
            }
            rs.close();
            calstmt.close();


        } catch (ParseException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getPersonalDetails");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getPersonalDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(calstmt);
            DBConnection.closeStatement(stmt);

            DBConnection.closeStatement(pstmt);
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
        PreparedStatement pstmt = null;

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
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDesabilityDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getDesabilityDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDesabilityDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getDesabilityDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(calstmt);
            DBConnection.closeStatement(stmt);

            DBConnection.closeStatement(pstmt);
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
    public void updatePersonalDetails(String personcode, PartADTO partadto, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException 
    {
        CallableStatement calstmt = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        String Status = partadto.getPersonstatus();
        
        try 
        {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getFromdate());
            String formdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date ddate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getDobday());
            String dobdate = new SimpleDateFormat("mm/dd/yyyy").format(ddate);
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, personcode);

            con = DBConnection.getConnection();



            if (Status != null && Status.equalsIgnoreCase("Rejected")) 
            {
                calstmt = con.prepareCall("{Call SP_DeleteDatainAllTables_for_RejectPerson(?)}");
                calstmt.setString(1, personcode);
                calstmt.executeUpdate();
                calstmt.close();
            }// Modified by ganesh
            else
            {
                String queryDeleteRejectedDate = "delete from  dbo.tblRejectPerson_Details where Person_Code=? and status='Active'";
                pstmt = con.prepareStatement(queryDeleteRejectedDate);
                pstmt.setString(1, personcode);
                pstmt.execute();
                pstmt.close();
                pstmt =null;
            }
            // end of ganesh modification

          //  System.out.println("partadto.getPersonstatus() : "+partadto.getPersonstatus());
            
            if (partadto.getPersonstatus() != null)
            {
            	
            	lStrQuery = " UPDATE tblperson_personal_details SET Person_Status =? ,\n"+
            				" typeof_Disability = ?,Existing_Percentage = ?,Login_ID= ?,System_IP_Address = ?,Updated_Date= CURRENT_TIMESTAMP,\n"+
            				" Status= 'Active',PartB_CampID =?,CategoryId =?,CategoryCount  = ? \n"+
            				" WHERE Person_Code =? And Status = 'Active'";
            	
            	
            	int param = 1;
            	pstmt = con.prepareStatement(lStrQuery);
            	pstmt.setString(param++, partadto.getPersonstatus());
            	pstmt.setString(param++, partadto.getType_disability());
            	pstmt.setString(param++, partadto.getExistingpercentage());
            	pstmt.setString(param++, partadto.getLoginid()); 
            	pstmt.setString(param++, partadto.getSystemip());
            	pstmt.setInt(param++, partadto.getCampid());
            	if (details != null) 
            	{
            		pstmt.setInt(param++, Integer.parseInt(details[0].toString()));
            		pstmt.setInt(param++, Integer.parseInt(details[1].toString()));
                }
            	else 
            	{
            		pstmt.setInt(param++, 1);
            		pstmt.setInt(param++, 1);
                }
            	pstmt.setString(param++,personcode);

            	  pstmt.executeUpdate();
            	
            	//System.out.println("status : "+status);
            	
            	pstmt.close();
            	con.close();
            	/*
                con.setAutoCommit(false);
                calstmt = con.prepareCall("{Call SP_tblPerson_Personal_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");


                calstmt.setString(1, personcode);
                calstmt.setString(2, partadto.getFormno());
                calstmt.setString(3, formdate);
                calstmt.setString(4, convertFirstLetterToUpperCase(partadto.getSurname()));
                calstmt.setString(5, CommonFiledsChecking.firstLetterAsCapital(partadto.getFirstname()));
                calstmt.setString(6, partadto.getGender());
                calstmt.setString(7, dobdate);
                calstmt.setString(8, partadto.getNoOfYears());
                calstmt.setString(9, partadto.getReligion());
                calstmt.setString(10, partadto.getCaste());
                calstmt.setString(11, partadto.getMarital());
                calstmt.setString(12, partadto.getEducation());
                calstmt.setString(13, partadto.getEmployment());
                calstmt.setString(14, partadto.getGrelation());
                calstmt.setString(15, CommonFiledsChecking.firstLetterAsCapital(partadto.getGsurname()));
                calstmt.setString(16, partadto.getRtype());
                calstmt.setString(17, partadto.getCard());
                calstmt.setBoolean(18, partadto.isEpiccard());
                calstmt.setString(19, partadto.getEpiccardno());

                if (partadto.isPensioncard() != false) {
                    calstmt.setBoolean(20, partadto.isPensioncard());
                } else {
                    calstmt.setBoolean(20, false);
                }

                if (partadto.getPension_type() != null
                        && !partadto.getPension_type().equals(null)
                        && !partadto.getPension_type().equals("null")
                        && partadto.getPension_type().toString().length() > 0) {
                    calstmt.setString(21, partadto.getPension_type());
                } else {
                    calstmt.setString(21, partadto.getPension_type());
                }

                if (partadto.getPensioncardno() != null
                        && !partadto.getPensioncardno().equals(null)
                        && partadto.getPensioncardno().length() > 0) {
                    calstmt.setString(22, partadto.getPensioncardno());
                } else {
                    calstmt.setString(22, partadto.getPensioncardno());
                }

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

                if (details != null) {
                    calstmt.setInt(42, Integer.parseInt(details[0].toString()));
                    calstmt.setInt(43, Integer.parseInt(details[1].toString()));
                } else {
                    calstmt.setInt(42, 1);
                    calstmt.setInt(43, 1);
                }

                calstmt.setString(44, partadto.getRationCardSlno());
                calstmt.setString(45, partadto.getAadharCardNo());

                calstmt.executeUpdate();
                con.commit();
                con.setAutoCommit(true);*/
            }
        } 
        catch (ParseException sqlEx)
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatePersonalDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "updatePersonalDetails");
        }
        catch (SQLException sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatePersonalDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "updatePersonalDetails");
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatePersonalDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "updatePersonalDetails");
        } 
        finally 
        {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(calstmt);

        }
    }

    
    public int updatePartADetails(PartADTO partadto,String personcode)
    {
    	String qry="";
    	Connection con=DBConnection.getConnection();
    	int i=0;
      try
      {
    	PreparedStatement ps=null;
    	qry = "update tblperson_personal_details set mole_one=?,mole_two=?,updated_date=current_timestamp where person_code=? and status='Active'";
    	ps = con.prepareStatement(qry);
    	ps.setString(1,convertFirstLetterToUpperCase(partadto.getMole1()) );
    	ps.setString(2,convertFirstLetterToUpperCase(partadto.getMole2()) );
    	ps.setString(3,personcode);
    	i=ps.executeUpdate();
      }
      catch(Exception e)
      {
    	e.printStackTrace();  
      }
    	
    	return i;
    }
    
    
    
    /**
     *
     * @param personcode
     * @param partadto
     * @param ds
     * @throws SADAREMDBException,SQLException
     */
    public synchronized void updateDisabilityDetails(String personcode, PartADTO partadto, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String[] details = null;
        String sql = null;
        Statement st = null;
        ResultSet rss = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        String personstatus = partadto.getPersonstatus();
        String personCodeStatus = null;

        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();

        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, personcode);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            

            /* To check the person status weather he is Eligible / Rejected start here **/
            sql = "select person_status from tblperson_personal_details  with (nolock) where person_code=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, personcode);
            rss = pstmt.executeQuery();
            if (rss != null) {
                while (rss.next()) {
                    personCodeStatus = rss.getString(1);
                }
            }


            if ("Eligible".equals(personstatus) && "Eligible".equals(personCodeStatus)) {
            	sql="select Person_Code from tblPerson_Disability_Details where Person_Code=? and status='Active'";
            	pstmt = con.prepareStatement(sql);
            	pstmt.setString(1, personcode);
                rs = pstmt.executeQuery();
                if (rs.next()) {

                    calstmt = con.prepareCall("{Call SP_tblPerson_Disability_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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

                    calstmt.setInt(21, Integer.parseInt(details[0].toString()));
                    calstmt.setInt(22, Integer.parseInt(details[1].toString()));

                    calstmt.executeUpdate();
                    calstmt.close();
                    calstmt = con.prepareCall("{Call SP_tblPerson_Causes_of_Disability_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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

                    calstmt.setInt(29, Integer.parseInt(details[0].toString()));
                    calstmt.setInt(30, Integer.parseInt(details[1].toString()));

                    calstmt.executeUpdate();
                    calstmt.close();
                    calstmt = con.prepareCall("{Call SP_tbl_Person_Disability_Cando_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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

                    calstmt.setInt(15, Integer.parseInt(details[0].toString()));
                    calstmt.setInt(16, Integer.parseInt(details[1].toString()));

                    calstmt.executeUpdate();


                } else {

                    insertDisabilityDetails(personcode, partadto, ds, request);

                }
            } else if ("Rejected".equals(personstatus) && "Rejected".equals(personCodeStatus)) {
            	sql = "select Person_Code from tblRejectPerson_Details where Person_Code=? and status='Active'";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, personcode);
            	rs = pstmt.executeQuery();
                if (rs.next()) {
                    calstmt = con.prepareCall("{Call SP_tblRejectPerson_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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

                    calstmt.setInt(28, Integer.parseInt(details[0].toString()));
                    calstmt.setInt(29, Integer.parseInt(details[1].toString()));

                    calstmt.executeUpdate();
                } else {

                    insertDisabilityDetails(personcode, partadto, ds, request);
                }
            }
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception exception) {

            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in Disability Details", personcode);
            // End
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "updateDisabilityDetails", "PartADAO", "DataBase");
            con.rollback();

            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "updateDisabilityDetails");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rss);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(calstmt);

        }

    }

    public synchronized PartADTO setPreviousPhysicalRequirements(DataSource ds, PartADTO dto) throws SADAREMDBException, SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        // String partACheckForDuplicatePersonCode = null;
        try {

            con = DBConnection.getConnection();
            pstmt = con.prepareStatement("select * from tbl_Person_Disability_Cando_Details"
                    + " WHERE Person_Code=? and status='active'");

            pstmt.setString(1, dto.getPersoncode());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                dto.setS_can(rs.getString("s_can"));
                dto.setF_can(rs.getString("f_can"));
                dto.setPp_can(rs.getString("pp_can"));
                dto.setL_can(rs.getString("l_can"));
                dto.setKc_can(rs.getString("kc_can"));
                dto.setB_can(rs.getString("b_can"));
                dto.setSt_can(rs.getString("st_can"));
                dto.setW_can(rs.getString("w_can"));
                dto.setRw_can(rs.getString("rw_can"));
                dto.setH_can(rs.getString("h_can"));
                dto.setSe_can(rs.getString("se_can"));

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "setPreviousPhysicalRequirements", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "setPreviousPhysicalRequirements");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "setPreviousPhysicalRequirements", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "setPreviousPhysicalRequirements");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }

        return dto;


    }

    public synchronized int updatePhysicalRequirements(DataSource ds, PartADTO partadto) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        Connection con = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        int i = 0;
        try {

            details = dao.getCategoryDetails(ds, partadto.getPersoncode());
            con = DBConnection.getConnection();
            calstmt = con.prepareCall("{Call SP_tbl_Person_Disability_Cando_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            calstmt.setString(1, partadto.getPersoncode());
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

            calstmt.setInt(15, Integer.parseInt(details[0].toString()));
            calstmt.setInt(16, Integer.parseInt(details[1].toString()));

            i = calstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatePhysicalRequirements", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "updatePhysicalRequirements");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatePhysicalRequirements", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "updatePhysicalRequirements");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(calstmt);

        }


        return 2;

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
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousDisabilityDetailsForUpdateDisability", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "deletePreviousDisabilityDetailsForUpdateDisability");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousDisabilityDetailsForUpdateDisability", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "deletePreviousDisabilityDetailsForUpdateDisability");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(calstmt);

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
            pstmt = con.prepareStatement("select Person_Code from tblPerson_Personal_Details  with (nolock) where "
                    + " District_Id=? and Mandal_Id =? and Village_Id = ? and  Habitation_ID= ? "
                    + "and UPPER(Surname) =?  and UPPER(First_Name)=?    and  Age_Years=? and  Gender=? "
                    + "and Relation_Name =? and UPPER(House_Number) =?");

            pstmt.setString(1, district_id);
            pstmt.setString(2, mandal_id);
            pstmt.setString(3, village_id);
            pstmt.setString(4, habitation_id);
            if (surName != null && !surName.equalsIgnoreCase("null") && !surName.equalsIgnoreCase("")) {
                pstmt.setString(5, surName.toUpperCase());
            } else {
                pstmt.setString(5, surName);
            }
            if (firstName != null && !firstName.equalsIgnoreCase("null") && !firstName.equalsIgnoreCase("")) {
                pstmt.setString(6, firstName.toUpperCase());
            } else {
                pstmt.setString(6, firstName);
            }
            //pstmt.setString(7,"null");
            pstmt.setString(7, age);
            pstmt.setString(8, gender);
            pstmt.setString(9, relationName);
            if (houseNumber != null && !houseNumber.equalsIgnoreCase("null") && !houseNumber.equalsIgnoreCase("")) {
                pstmt.setString(10, houseNumber.toUpperCase());
            } else {
                pstmt.setString(10, houseNumber);
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                partACheckForDuplicatePersonCode = rs.getString(1);

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPartACheckForDuplicate", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getPartACheckForDuplicate");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPartACheckForDuplicate", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getPartACheckForDuplicate");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

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
                                partADTO.setSurname(CommonFiledsChecking.firstLetterAsCapital(rs2.getString("Surname")));
                                partADTO.setFirstname(CommonFiledsChecking.firstLetterAsCapital(rs2.getString("FirstName")));
                                partADTO.setGsurname(CommonFiledsChecking.firstLetterAsCapital(rs2.getString("FatherName")));

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
                                partADTO.setHouseno(rs2.getString("hno"));
                                partADTO.setReasonforstatus(rs.getString("ReasonforPersonStatus"));


                            }
                        }
                    }
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getExistingPensionDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getExistingPensionDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getExistingPensionDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getExistingPensionDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeResultSet(rs1);
            DBConnection.closeResultSet(rs2);
            DBConnection.closeStatement(calstmt);
            DBConnection.closeStatement(pstmt);

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
        PreparedStatement pstmt = null;

        ArrayList mandallist = new ArrayList();



        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "select mandal_id,mandal_name from tblMandal_Details where district_id=? order by mandal_name";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtid);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                PartADTO partADTO = new PartADTO();
                partADTO.setMandal(rs.getString("mandal_id"));
                partADTO.setMandal_name(rs.getString("mandal_name"));
                mandallist.add(partADTO);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandalsList", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getMandalsList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandalsList", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getMandalsList");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


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
        PreparedStatement pstmt = null;
        ArrayList villagelist = new ArrayList();
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "select village_id,village_name from tblVillage_Details where district_id=? and  mandal_id=? order by village_name";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtid);
            pstmt.setString(2, mandalid);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                PartADTO partADTO = new PartADTO();
                partADTO.setTownVillage(rs.getString("village_id"));
                partADTO.setVillage_name(rs.getString("village_name"));
                villagelist.add(partADTO);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillagesList", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getVillagesList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillagesList", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getVillagesList");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


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
        PreparedStatement pstmt = null;
        ArrayList habitationlist = new ArrayList();
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "select habitation_id,habitation_name from tblHabitation_Details where district_id=? and  mandal_id=?  and village_id=? and status='Active' order by habitation_name";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtid);
            pstmt.setString(2, mandalid);
            pstmt.setString(3, villageid);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                PartADTO partADTO = new PartADTO();
                partADTO.setHabitation(rs.getString("habitation_id"));
                partADTO.setHabitation_name(rs.getString("habitation_name"));
                habitationlist.add(partADTO);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHabitationsList", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getHabitationsList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHabitationsList", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getHabitationsList");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


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
        ResultSet rs = null;
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
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatePersonaCode", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "updatePersonaCode");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatePersonaCode", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "updatePersonaCode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatePersonaCode", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "updatePersonaCode");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(calstmt);

            DBConnection.closeResultSet(rs);
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
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPersonalDetailsForUpdatePersonCode", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertPersonalDetailsForUpdatePersonCode");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPersonalDetailsForUpdatePersonCode", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertPersonalDetailsForUpdatePersonCode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPersonalDetailsForUpdatePersonCode", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertPersonalDetailsForUpdatePersonCode");
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
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePersonDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "deletePersonDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePersonDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "deletePersonDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(calstmt);

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
        PreparedStatement pstmt = null;
        try {
            con = DBConnection.getConnection();
            
            String SQL = "select role_id from Login_Details "
                    + "where UserName = ? and Status='Active';";
            pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                partADTO = new PartADTO();
                partADTO.setRoleid(rs.getString("role_id"));
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getLoginDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getLoginDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getLoginDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getLoginDetails");
        } finally {//added by rekha
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            //DBConnection.closeStatement(cs);

            DBConnection.closeStatement(pstmt);
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

        CallableStatement calstmt = null;
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
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getEmployeeDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getEmployeeDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getEmployeeDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getEmployeeDetails");
        } finally {//added by rekha
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(calstmt);

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
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getEmployeeStatus", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getEmployeeStatus");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getEmployeeStatus", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getEmployeeStatus");
        } finally {//added by rekha
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);//DBConnection.closeStatement(cs);
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
    public void insertMultipleDoctors(DataSource ds, String personcode, String multipleDisabilityID[], Map multipleDisabilityMap, String loginid, String Systemip, HttpServletRequest request) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        Connection con = null;
        Statement stmt = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        try {

            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, personcode);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            stmt = con.createStatement();



            for (int i = 0; i < multipleDisabilityID.length; i++) {
                PartADTO partADTO = (PartADTO) multipleDisabilityMap.get(multipleDisabilityID[i]);
                int disabilityId = Integer.parseInt(multipleDisabilityID[i]);
                calstmt = con.prepareCall("{Call SP_tblPerson_MultipleDisability_Doctors_Details_INSERT(?,?,?,?,?,?,?,?,?)}");
                calstmt.setString(1, personcode);
                calstmt.setInt(2, disabilityId);
                calstmt.setString(3, partADTO.getDoctorname());
                calstmt.setString(4, partADTO.getDoctorregnumber());
                calstmt.setString(5, partADTO.getDoctordesignation());
                calstmt.setString(6, loginid);
                calstmt.setString(7, Systemip);

                calstmt.setInt(8, Integer.parseInt(details[0].toString()));
                calstmt.setInt(9, Integer.parseInt(details[1].toString()));

                calstmt.executeUpdate();
                calstmt.close();

            }
            con.commit();
            con.setAutoCommit(true);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertMultipleDoctors", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertMultipleDoctors");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertMultipleDoctors", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertMultipleDoctors");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(calstmt);

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
    public void updateMultipleDoctors(DataSource ds, String personcode, String multipleDisabilityID[], Map multipleDisabilityMap, String loginid, String Systemip, HttpServletRequest request) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, personcode);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            

            String inactive = "UPDATE dbo.tblPerson_MultipleDisability_Doctors_Details SET Status = 'Inactive' where Person_Code=? and status='Active'";
            pstmt = con.prepareStatement(inactive);
            pstmt.setString(1, personcode);
            pstmt.executeUpdate();

            for (int i = 0; i < multipleDisabilityID.length; i++) {
                PartADTO partADTO = (PartADTO) multipleDisabilityMap.get(multipleDisabilityID[i]);
                int disabilityId = Integer.parseInt(multipleDisabilityID[i]);
                String checkquery = "select Person_Code from tblPerson_MultipleDisability_Doctors_Details where Person_Code=? and Disability_ID=? and status='Active'";
                pstmt = con.prepareStatement(checkquery);
                pstmt.setString(1, personcode);
                pstmt.setString(2, multipleDisabilityID[i]);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    calstmt = con.prepareCall("{Call SP_tblPerson_MultipleDisability_Doctors_Details_UPDATE(?,?,?,?,?,?,?)}");
                    calstmt.setString(1, personcode);
                    calstmt.setInt(2, disabilityId);
                    calstmt.setString(3, partADTO.getDoctorname());
                    calstmt.setString(4, partADTO.getDoctorregnumber());
                    calstmt.setString(5, partADTO.getDoctordesignation());

                    calstmt.setInt(6, Integer.parseInt(details[0].toString()));
                    calstmt.setInt(7, Integer.parseInt(details[1].toString()));

                    /*   if (session.getAttribute("categoryIdUpdateAu") != null && session.getAttribute("categoryIdUpdateAu") != null) {
                    calstmt.setInt(6, Integer.parseInt((String) session.getAttribute("categoryIdAu")));
                    calstmt.setInt(7, Integer.parseInt((String) session.getAttribute("categoryCountUpdateAu")));
                    } else {
                    calstmt.setInt(6, 1);
                    calstmt.setInt(7, 1);
                    }*/
                    calstmt.executeUpdate();
                    calstmt.close();

                } else {
                    calstmt = con.prepareCall("{Call SP_tblPerson_MultipleDisability_Doctors_Details_INSERT(?,?,?,?,?,?,?,?,?)}");
                    calstmt.setString(1, personcode);
                    calstmt.setInt(2, disabilityId);
                    calstmt.setString(3, partADTO.getDoctorname());
                    calstmt.setString(4, partADTO.getDoctorregnumber());
                    calstmt.setString(5, partADTO.getDoctordesignation());
                    calstmt.setString(6, loginid);
                    calstmt.setString(7, Systemip);

                    calstmt.setInt(8, Integer.parseInt(details[0].toString()));
                    calstmt.setInt(9, Integer.parseInt(details[1].toString()));

                    /*if (session.getAttribute("categoryIdUpdateAu") != null && session.getAttribute("categoryCountUpdateAu") != null) {
                    calstmt.setInt(8, Integer.parseInt((String) session.getAttribute("categoryIdUpdateAu")));
                    calstmt.setInt(9, Integer.parseInt((String) session.getAttribute("categoryCountUpdateAu")));
                    } else {
                    calstmt.setInt(8, 1);
                    calstmt.setInt(9, 1);
                    }*/
                    calstmt.executeUpdate();
                    calstmt.close();
                }


            }
            con.commit();
            con.setAutoCommit(true);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateMultipleDoctors", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "updateMultipleDoctors");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateMultipleDoctors", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "updateMultipleDoctors");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

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
        String prefix = null;
        PreparedStatement pstmt = null;
        PartADTO partADTO = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();

            pstmt = con.prepareStatement("select Railway_Certificate from tblAllDisabilityPerson_Common_Needs where person_code=? and status='Active'");
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                eli = rs.getString(1);

            }




        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRail_eli_status", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getRail_eli_status");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRail_eli_status", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getRail_eli_status");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }


        return eli;

    }

    public int insertRailwayDoctordetails(DataSource ds, String pid, String dn, String rn, String de, String status, String rail, String ip, String role) throws SADAREMDBException, SQLException {

        PreparedStatement pstmt = null;
        PreparedStatement ps = null;
        // ResultSet rs=null;
        PartADTO partADTO = null;
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
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertRailwayDoctordetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertRailwayDoctordetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertRailwayDoctordetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertRailwayDoctordetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(ps);

        }
    }

    public String getDoctorprefix(DataSource ds, String campid, String distid) throws SADAREMDBException, SQLException {

        String prefix = null;
        PreparedStatement pstmt = null;
        PartADTO partADTO = null;
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
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDoctorprefix", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getDoctorprefix");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDoctorprefix", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getDoctorprefix");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }

        return prefix;
    }

    public boolean checkVisual(DataSource ds, String personcode) throws SADAREMDBException, SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        ResultSet rs = null;
        String sql = null;
        boolean viexists = false;

        try {
            con = DBConnection.getConnection();

            sql = "SELECT * FROM tblVisualImpairment_Details WHERE Person_Code = ? and Status = 'Active'";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                viexists = true;
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkVisual", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkVisual");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkVisual", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkVisual");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return viexists;
    }

    public PartADTO getMaxMultipleDoctorsDetails(DataSource ds, String personcode, String maxId) throws SADAREMDBException, SQLException {

        PreparedStatement pstmt = null;
        PartADTO partADTO = null;
        Connection con = null;
        ResultSet rs = null;
        String sql = null;
        try {
            con = DBConnection.getConnection();
            // con.setAutoCommit(false);
            sql = "SELECT Doctor_Name,Doctor_RegNumber,Doctor_Designation FROM dbo.tblPerson_MultipleDisability_Doctors_Details WHERE Person_Code = ? and Disability_ID=? and Status = 'Active'";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, personcode);
            pstmt.setString(2, maxId);
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
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaxMultipleDoctorsDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getMaxMultipleDoctorsDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaxMultipleDoctorsDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getMaxMultipleDoctorsDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

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
        PreparedStatement pstmt = null;
        String doctorname = null;
        String doctorregNumber = null;
        String doctorDesignation = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            
            doctorname = partADTO.getDoctor1name();
            doctorregNumber = partADTO.getDoctor1regnumber();
            doctorDesignation = partADTO.getDoctor1designation();
            String query = "UPDATE tblPerson_Disability_Details SET First_Doctor_Name=?, First_Doctor_RegNumber=? , First_Doctor_Designation=?  WHERE Person_Code=? and status='Active'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, doctorname);
            pstmt.setString(2, doctorregNumber);
            pstmt.setString(3, doctorDesignation);
            pstmt.setString(4, personcode);
            pstmt.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateMaxMultipleDoctorsDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "updateMaxMultipleDoctorsDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateMaxMultipleDoctorsDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "updateMaxMultipleDoctorsDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(calstmt);

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
            pstmt = con.prepareStatement("SELECT Disability_ID FROM dbo.tblPerson_MultipleDisability_Doctors_Details WHERE Person_Code = ? and status='Active'");
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {

                bdMultipleDisableId = Integer.toString(rs.getInt("Disability_ID"));
                multipleDisabilityIdList.add(bdMultipleDisableId);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleDisabilityDisabilityIds", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getMultipleDisabilityDisabilityIds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleDisabilityDisabilityIds", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getMultipleDisabilityDisabilityIds");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

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
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousLocomotorDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "deletePreviousLocomotorDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousLocomotorDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "deletePreviousLocomotorDetails");
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
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousVisualImpaimentDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "deletePreviousVisualImpaimentDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousVisualImpaimentDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "deletePreviousVisualImpaimentDetails");
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
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousHearingImpairmentDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "deletePreviousHearingImpairmentDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousHearingImpairmentDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "deletePreviousHearingImpairmentDetails");
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
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousMentalRetardationDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "deletePreviousMentalRetardationDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousMentalRetardationDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "deletePreviousMentalRetardationDetails");
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
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousMentalIllnessDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "deletePreviousMentalIllnessDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletePreviousMentalIllnessDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "deletePreviousMentalIllnessDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(calstmt);

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
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "rollBackEmployeeStatus", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "rollBackEmployeeStatus");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "rollBackEmployeeStatus", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "rollBackEmployeeStatus");
        } finally {//added by rekha
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            //DBConnection.closeStatement(cs);

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
    public synchronized int insertDisabilityDetailsAU(String personcode, PartADTO partadto, String personStatus, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        Connection con = null;
        int i = 0;
        String Status = personStatus;
        String sql = null;
        String categoryID = null;
        String categoryCount = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        String personCode = null;
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, personcode);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);


            /* To check the person status weather he is Eligible / Rejected start here **/
            
            sql = "select person_status from tblperson_personal_details  with (nolock) where person_code=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {System.out.println();
                    personCode = rs.getString(1);
                }
            }

            /* End */


            if ("Eligible".equals(Status) && "Eligible".equals(personCode)) {
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

                calstmt.setInt(21, Integer.parseInt(details[0].toString()));
                calstmt.setInt(22, Integer.parseInt(details[1].toString()));

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
                calstmt.setString(27, partadto.getLoginid());
                calstmt.setString(28, partadto.getSystemip());
                calstmt.setInt(29, Integer.parseInt(details[0].toString()));
                calstmt.setInt(30, Integer.parseInt(details[1].toString()));

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
                calstmt.setInt(15, Integer.parseInt(details[0].toString()));
                calstmt.setInt(16, Integer.parseInt(details[1].toString()));
                calstmt.executeUpdate();

            } else if ("Rejected".equals(Status) && "Rejected".equals(personCode)) {

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
                calstmt.setInt(28, Integer.parseInt(details[0].toString()));
                calstmt.setInt(29, Integer.parseInt(details[1].toString()));
                i = calstmt.executeUpdate();
                if (i > 0) {
                    transactionService.updateTransactionStatus(ds, "Completed", personcode);
                }
            }
            calstmt.close();
            con.commit();
            con.setAutoCommit(true);



        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error Disability Details", personcode);
            // End
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "insertDisabilityDetailsAU", "PartADAO", "DataBase");
            con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertDisabilityDetailsAU");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(calstmt);

            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }
        return i;
    }

    /** Ration Card Details from web services **/
    public String CheckDuplicateration(DataSource ds, String sid, String id) throws SADAREMDBException, SQLException {

        ArrayList disabilityList = new ArrayList();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        String exi = null;

        try {
            con = DBConnection.getConnection();
            // stmt = con.createStatement();
            stmt = con.prepareStatement("select RationCard_Number  from tblPerson_Personal_Details  with (nolock) where sno=? and RationCard_number=?");
            stmt.setString(1, sid);
            stmt.setString(2, id);
            rs = stmt.executeQuery();


            while (rs.next()) {
                exi = rs.getString(1);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "CheckDuplicateration", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "CheckDuplicateration");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "CheckDuplicateration", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "CheckDuplicateration");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);



        }
        return exi;
    }

    public String getDistrictOrMandalname(DataSource ds, String districtid, String mandalid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        String name = null;
        ResultSet rs = null;
        String query = null;
        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            if (!mandalid.equalsIgnoreCase("0")) {
                query = "select mandal_name from tblMandal_details where district_id=? and mandal_id=?";
                stmt = con.prepareStatement(query);
                stmt.setString(1, districtid);
                stmt.setString(2, mandalid);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    name = rs.getString(1);
                }
            } else if (mandalid.equalsIgnoreCase("0")) {
                query = "select district_name from tblDistrict_details where district_id=?";
                stmt = con.prepareStatement(query);
                stmt.setString(1,districtid);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    name = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistrictOrMandalname", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getDistrictOrMandalname");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistrictOrMandalname", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getDistrictOrMandalname");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

        }
        return name;
    }

    public String getVillageName(DataSource ds, String districtid, String mandalid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        String name = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            //  if(districtid!=null && mandalid!=null){

            String query = "select village_id from tblVillage_details where district_id=1 and mandal_id=1";
            stmt = con.prepareStatement(query);
            //  stmt.setString(1, districtid);
            //  stmt.setString(2, mandalid);
            rs = stmt.executeQuery();

            while (rs.next()) {
                name = rs.getString(1);
            }



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillageName", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getVillageName");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillageName", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getVillageName");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);


        }
        return name;
    }

    /** End **/
    /**
     *
     * @param partadto
     * @param ds
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public synchronized String insertPersonalDetailsForRationCard(PartADTO partadto, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String personcodemax = null;
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        String districtid = partadto.getDistrict();
        String mandalid = partadto.getMandal();
        String villageid = partadto.getTownVillage();
        String habitationid = partadto.getHabitation();
        String panchayatid = partadto.getPanchayatiid();
        String assemblyid = partadto.getAssemblyid();
        HttpSession session = request.getSession();
        String reasonForStatus = null;
        int partAEnteredStatus = 0;
        String habCode = null;
        String campdate = null;
        PreparedStatement ps = null;
        boolean success = true;
        try {


            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getFromdate());
            String formdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date ddate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getDobday());
            String dobdate = new SimpleDateFormat("mm/dd/yyyy").format(ddate);

            session.removeAttribute("categoryIdAu");
            session.removeAttribute("categoryCountAu");
            if (session.getAttribute("campId") != null) {
                int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));

                /** Check Habcode base on selecting territorydetails */
                habCode = this.checkHabitation(ds, districtid, mandalid, panchayatid, assemblyid, villageid, habitationid);

                if (habCode != null) {
                    personcodemax = territoryservice.getHabitationCodeRationCard(districtid, mandalid, panchayatid, assemblyid, villageid, habitationid, ds);
                    con = DBConnection.getConnection();
                    con.setAutoCommit(false);
                    
                    CommonDAOImpl comObj = new CommonDAOImpl();
                    HashMap GEODtls = new HashMap();
          			GEODtls=comObj.getGEODetailsbySADAREMID(personcodemax);
          			String habcodeFromSadaremId	=	GEODtls.get("HabCode").toString();
                    
                    if (habCode.equals(habcodeFromSadaremId)) {

                        calstmt = con.prepareCall("{Call SP_tblPerson_Personal_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

                        calstmt.setString(1, personcodemax);
                        calstmt.setString(2, partadto.getFormno());
                        calstmt.setString(3, formdate);
                        calstmt.setString(4, convertFirstLetterToUpperCase(partadto.getSurname()));
                        calstmt.setString(5, partadto.getFirstname());
                        calstmt.setString(6, partadto.getGender());
                        calstmt.setString(7, dobdate);
                        if (partadto.getNoOfYears() != null && partadto.getNoOfYears().length() > 0
                                && !partadto.getNoOfYears().equals(null)) {
                            calstmt.setString(8, partadto.getNoOfYears());
                        }
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
                        calstmt.setBoolean(20, true);
                        calstmt.setString(21, "Disabled");
                        calstmt.setString(22, "PIII");
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
                        calstmt.setString(42, "PhaseIII");
                        calstmt.setString(43, "Live");
                        calstmt.setString(44, habCode);
                        calstmt.setInt(45, 1);
                        calstmt.setInt(46, 1);
                        calstmt.setString(47, partadto.getRationCardSlno());
                        calstmt.setString(48, partadto.getAadharCardNo());

                        partAEnteredStatus = calstmt.executeUpdate();


                        int statusCount = 0;
                        if (partAEnteredStatus != 0) {
                            transactionService.insertTransactionalDetails(ds, "PartA Details Entered", personcodemax, request);

                            int count = 0;
                            String applicationNumber = "";
                            String query = "select count(*) from Disabilitycampdatedetails where districtid=?";
                            ps = con.prepareStatement(query);
                            ps.setString(1, partadto.getDistrict_id());
                            rs = ps.executeQuery();
                            if (rs != null && rs.next()) {
                                count = rs.getInt(1);
                            }
                            if (count > 0) {
//partadto.setDistrict_id("07");
                                String query1 = "select max(ackno) from Disabilitycampdatedetails where districtid = ?";
                                ps = con.prepareStatement(query1);
                                ps.setString(1, partadto.getDistrict_id());
                                rs = ps.executeQuery();
                                if (rs != null && rs.next()) {
                                    rs.getString(1);
                                    if (rs.getString(1) != null) {
                                        long longCode = Long.parseLong(rs.getString(1));
                                        if (rs.getString(1).startsWith("0")) {
                                            longCode++;

                                            String withZero = String.valueOf(longCode);

                                            applicationNumber = paddingString(withZero, withZero.length() + 1, '0');

                                        } else {
                                            longCode++;
                                            applicationNumber = String.valueOf(longCode);
                                        }

                                    } else {
                                        applicationNumber = partadto.getDistrict_id() + "00001";
                                    }
                                }
                            } else {
                                applicationNumber = partadto.getDistrict_id() + "00001";
                            }

                            if (partadto.getPartaCampId() != null && !partadto.getPartaCampId().equals("0")) {
                                if (partadto.getPartaCampDate() != null && !partadto.getPartaCampDate().equalsIgnoreCase("0")) {
                                    Date partcampdate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getPartaCampDate());
                                    campdate = new SimpleDateFormat("mm/dd/yyyy").format(partcampdate);
                                }
                                String querys = "insert into Disabilitycampdatedetails (Personcode,Districtid,Disabilityid,CampId,"
                                        + "Campdate,ackno,Createddate,Updateddate,Loginid,Sysytemip)"
                                        + "values(?,?,?,?,"
                                        + "?,?,getDate(),getDate(),?,?)";
                                ps = con.prepareStatement(querys);
                                ps.setString(1, personcodemax);
                                ps.setString(2, partadto.getDistrict_id());
                                ps.setString(3, partadto.getType_disability());
                                ps.setString(4, partadto.getPartaCampId());
                                ps.setString(5, campdate);
                                ps.setString(6, applicationNumber);
                                ps.setString(7, partadto.getLoginid());
                                ps.setString(8, partadto.getSystemip() );
                                int i = ps.executeUpdate();
                                if (i > 0) {
                                    String formdates = new SimpleDateFormat("MM/dd/yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(partadto.getPartaCampDate()));
                                    calstmt = con.prepareCall("{Call Sp_Campcount_verification(?,?,?,?)}");
                                    calstmt.setString(1, partadto.getDistrict_id());
                                    calstmt.setString(2, partadto.getType_disability());
                                    calstmt.setString(3, partadto.getPartaCampId());
                                    calstmt.setString(4, formdates);
                                    rs = calstmt.executeQuery();
                                    if (rs != null && rs.next()) {
                                        statusCount = rs.getInt(1);
                                    }
                                }

                                if (statusCount == 1) {
                                    query = "delete from dbo.tblPerson_Personal_Details where person_code=? ";
                                    ps = con.prepareStatement(query);
                                    ps.setString(1, personcodemax);
                                    ps.executeUpdate();
                                    query = "delete from Disabilitycampdatedetails where personcode=? ";
                                    ps = con.prepareStatement(query);
                                    ps.setString(1, personcodemax);
                                    ps.executeUpdate();
                                    request.setAttribute("dvalues", "Select Disabilities Exceeded To Selete Camp");

                                }
                            } else {
                                int status = 0;
                                if (partadto.getNoofrowvalue() > 0) {
                                    for (int i = 0; i < partadto.getNoofrowvalue(); i++) {
                                        boolean flag = false;

                                        if ((partadto.getDynaProperty(i + "#" + (1)) != null
                                                && !partadto.getDynaProperty(i + "#" + (1)).toString().equals("")
                                                && partadto.getDynaProperty(i + "#" + (1)).toString().length() > 0)
                                                && (partadto.getDynaProperty(i + "#" + (2)) != null
                                                && !partadto.getDynaProperty(i + "#" + (2)).toString().equals("")
                                                && partadto.getDynaProperty(i + "#" + (2)).toString().length() > 0)
                                                && (partadto.getDynaProperty(i + "#" + (3)) != null
                                                && !partadto.getDynaProperty(i + "#" + (3)).toString().equals("")
                                                && partadto.getDynaProperty(i + "#" + (3)).toString().length() > 0)) {
                                            flag = true;
                                        }
                                        if (flag) {
                                            String campdates = new SimpleDateFormat("MM/dd/yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(partadto.getDynaProperty(i + "#3").toString()));

                                            String querys = "insert into Disabilitycampdatedetails (Personcode,Districtid,Disabilityid,CampId,"
                                                    + "Campdate,ackno,Createddate,Updateddate,Loginid,Sysytemip)"
                                                    + "values(?,?,?,?,?,?,getDate(),getDate(),?,?)";
                                            ps = con.prepareStatement(querys);
                                            ps.setString(1, personcodemax);
                                            ps.setString(2, partadto.getDistrict_id());
                                            if (partadto.getDynaProperty(i + "#2").toString() != null) {
                                                ps.setString(3, partadto.getDynaProperty(i + "#2").toString());
                                            } else {
                                                ps.setString(3, null);
                                            }
                                            if (partadto.getDynaProperty(i + "#1").toString() != null) {
                                                ps.setString(4, partadto.getDynaProperty(i + "#1").toString());
                                            } else {
                                                ps.setString(4, null);
                                            }
                                            if (partadto.getDynaProperty(i + "#3").toString() != null) {
                                                ps.setString(5, campdates);
                                            } else {
                                                ps.setString(5, null);
                                            }
                                            ps.setString(6, applicationNumber);
                                            ps.setString(7, partadto.getLoginid());
                                            ps.setString(8, partadto.getSystemip());
                                            status = ps.executeUpdate();

                                        }


                                        if (status > 0) {
                                            Date partcampdate1 = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getDynaProperty(i + "#3").toString());
                                            String campdate1 = new SimpleDateFormat("mm/dd/yyyy").format(partcampdate1);
                                            calstmt = con.prepareCall("{Call Sp_Campcount_verification(?,?,?,?)}");
                                            calstmt.setString(1, partadto.getDistrict_id());
                                            calstmt.setString(2, partadto.getDynaProperty(i + "#2").toString());
                                            calstmt.setString(3, partadto.getDynaProperty(i + "#1").toString());
                                            calstmt.setString(4, campdate1);
                                            rs = calstmt.executeQuery();
                                            if (rs != null && rs.next()) {
                                                statusCount = rs.getInt(1);
                                            }

                                            if (statusCount == 1) {
                                                success = false;
                                            }
                                        }
                                    }
                                    if (success == false) {
                                        query = "delete from dbo.tblPerson_Personal_Details where person_code=? ";
                                        ps = con.prepareStatement(query);
                                        ps.setString(1, personcodemax);
                                        ps.executeUpdate();
                                        for (int i = 0; i < partadto.getNoofrowvalue(); i++) {
                                            Date partcampdate1 = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getDynaProperty(i + "#3").toString());
                                            String campdate1 = new SimpleDateFormat("mm/dd/yyyy").format(partcampdate1);

                                            query = "delete from Disabilitycampdatedetails where personcode=?"
                                                    + " and disabilityid=? and campdate=?";
                                            ps = con.prepareStatement(query);
                                            ps.setString(1, personcodemax);
                                            ps.setString(2, partadto.getDynaProperty(i + "#2").toString());
                                            ps.setString(3, campdate1);
                                            ps.executeUpdate();

                                        }
                                        request.setAttribute("dvalues", "Select Disabilities Exceeded To Selete Camp");
                                    }
                                }
                            }

                        }

                        con.commit();
                        con.setAutoCommit(true);


                    } else {
                        personcodemax = "Not Match";
                    }
                } else {
                    personcodemax = "Not Match";
                }
            }
        } catch (Exception exception) {
            // exception.printStackTrace();
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error PartA Details", personcodemax);
            // End
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "insertPersonalDetailsForRationCard", "PartADAO", "DataBase");
            con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertPersonalDetailsForRationCard");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(ps);
            DBConnection.closeStatement(calstmt);


        }
        return personcodemax;
    }

    /**
     *
     * @param partadto
     * @param ds
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public synchronized int checkPensionCardNumber(DataSource ds, String pensionId, String districtId) throws SADAREMDBException, SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        String sql = null;
        int personcodemax = 0;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            
            

            sql = "select count(*) from tblperson_personal_details  with (nolock) where pensioncard_no=? and district_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pensionId);
            pstmt.setString(2, districtId);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    personcodemax = rs.getInt(1);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPensionCardNumber", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkPensionCardNumber");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPensionCardNumber", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkPensionCardNumber");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);

            DBConnection.closeResultSet(rs);
        }
        return personcodemax;
    }

    /**
     *
     * @param partadto
     * @param ds
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public synchronized String checkaadharcardNumber(DataSource ds, String aadharCardNo, String districtId) throws SADAREMDBException, SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        String sql = null;
        String personcodemax = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            

            sql = "select person_code from tblperson_personal_details  with (nolock) where  proofdoc_type in ('Aadhaar Card','Aadhaar ID','Aadhar Card','Adhaar Card') and   proof_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, aadharCardNo);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    personcodemax = rs.getString(1);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkaadharcardNumber", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkaadharcardNumber");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkaadharcardNumber", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkaadharcardNumber");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);

            DBConnection.closeResultSet(rs);
        }
        return personcodemax;
    }

    public synchronized boolean isDuplicateSlno(PartADTO partADTO, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        String sql = null;
        boolean exists_slno = false;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            
            con.setAutoCommit(false);
            sql = "select * from tblperson_personal_details  with (nolock) where rationcard_number=? and RationCard_Slno=? and  ReasonforPersonStatus not in (?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, partADTO.getCard());
            pstmt.setString(2, partADTO.getRationCardSlno());
            pstmt.setString(3, CommonConstants.ADDRESSCHANGE);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    exists_slno = true;
                }
            }

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "isDuplicateSlno", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "isDuplicateSlno");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "isDuplicateSlno", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "isDuplicateSlno");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);

            DBConnection.closeResultSet(rs);
        }
        return exists_slno;
    }

    /**
     *
     * @param partadto
     * @param ds
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public synchronized String getPersonCode(DataSource ds, String pensionId) throws SADAREMDBException, SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        String sql = null;
        String personcodemax = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            

            sql = "select person_code from tblperson_personal_details  with (nolock) where pensioncard_no=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pensionId);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    personcodemax = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {System.out.println(sqlEx);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonCode", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getPersonCode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonCode", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getPersonCode");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);

            DBConnection.closeResultSet(rs);
        }
        return personcodemax;
    }

    /**
     *
     * @param partadto
     * @param ds
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public synchronized String insertSerpEmployeesPersonalDetails(PartADTO partadto, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        Connection con = null;
        String personcodemax = null;
        String sql = null;
        Statement st = null;
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        String districtid = partadto.getDistrict();
        String mandalid = partadto.getMandal();
        String villageid = partadto.getTownVillage();
        String habitationid = partadto.getHabitation();
        String panchayatid = partadto.getPanchayatiid();
        String assemblyid = partadto.getAssemblyid();
        HttpSession session = request.getSession();
        String reasonForStatus = null;
        int partAEnteredStatus = 0;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            con.setAutoCommit(false);
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getFromdate());
            String formdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date ddate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getDobday());
            String dobdate = new SimpleDateFormat("mm/dd/yyyy").format(ddate);

            if (session.getAttribute("categoryIdAu") != null
                    && session.getAttribute("categoryCountAu") != null) {
                session.removeAttribute("categoryIdAu");
                session.removeAttribute("categoryCountAu");
            }

            int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));

            if (partadto.getReasonforstatus() != null && !partadto.getReasonforstatus().equals("")) {
                reasonForStatus = partadto.getReasonforstatus();
            } else {
                reasonForStatus = session.getAttribute("personStatusId").toString();
            }

            personcodemax = territoryservice.getHabitationCode(districtid, mandalid, panchayatid, assemblyid, villageid, habitationid, ds);
            if (partadto.getPersonstatus() != null) {
                calstmt = con.prepareCall("{Call SP_tblPerson_Personal_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

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


                // calstmt.setString(22, partadto.getPensioncardno());


                calstmt.setString(22, (String) session.getAttribute("pensionIdDis"));


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
                
                CommonDAOImpl comObj = new CommonDAOImpl();
                HashMap GEODtls = new HashMap();
      			GEODtls=comObj.getGEODetailsbySADAREMID(personcodemax);
      			String habcodeFromSadaremId	=	GEODtls.get("HabCode").toString();
                
                calstmt.setString(44, habcodeFromSadaremId);
                calstmt.setInt(45, 1);
                calstmt.setInt(46, 1);

                calstmt.setString(47, partadto.getRationCardSlno());
                calstmt.setString(48, partadto.getAadharCardNo());
                //  }
                partAEnteredStatus = calstmt.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                if (partAEnteredStatus != 0) {
                    transactionService.insertTransactionalDetails(ds, "PartA Details Entered", personcodemax, request);

                }

                session.removeAttribute("personStatusId");
                session.removeAttribute("pensionIdDis");
            }
        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error PartA Details", personcodemax);
            // End
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "insertSerpEmployeesPersonalDetails", "PartADAO", "DataBase");
            con.rollback();

            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertSerpEmployeesPersonalDetails");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(calstmt);

            DBConnection.closeStatement(st);

        }
        return personcodemax;
    }

    public String checkHabitation(DataSource ds, String districtId, String mandalId, String panchayatId, String assemblyId, String villageId, String habitationId) throws SADAREMDBException, SQLException {
        String habitationIdGen = null;
        String assemblyIdGen = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet habcodeRS = null;
        ResultSet assemblyRS = null;
        String habcodeSQL = null;
        String assemblySQL = null;
        try {
            con = DBConnection.getConnection();
            

            assemblySQL = "select assembly_id from tblHabitation_Details where district_id=? and "
                    + "mandal_id=? and panchayat_id=? and "
                    + "village_id=? and habitation_id=?";
            pstmt = con.prepareStatement(assemblySQL);
            pstmt.setString(1, districtId);
            pstmt.setString(2, mandalId);
            pstmt.setString(3, panchayatId);
            pstmt.setString(4, villageId);
            pstmt.setString(5, habitationId);
            assemblyRS = pstmt.executeQuery();
            if (assemblyRS != null) {
                while (assemblyRS.next()) {
                    assemblyIdGen = assemblyRS.getString(1);
                }
            }

            habcodeSQL = "select habitation_code from tblHabitation_Details where district_id=? and assembly_id=? and "
                    + "mandal_id=? and panchayat_id=? and "
                    + "village_id=? and habitation_id=?";
            pstmt = con.prepareStatement(habcodeSQL);
            pstmt.setString(1, districtId);
            pstmt.setString(2, assemblyIdGen);
            pstmt.setString(3, mandalId);
            pstmt.setString(4, panchayatId);
            pstmt.setString(5, villageId);
            pstmt.setString(6, habitationId);
            habcodeRS = pstmt.executeQuery();

            if (habcodeRS != null) {
                while (habcodeRS.next()) {
                    habitationIdGen = habcodeRS.getString(1);
                }
            }

        } catch (Exception e) {
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "checkHabitation", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkHabitation");

        } finally {
            try {
                DBConnection.closeConnection(con);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeResultSet(assemblyRS);
                DBConnection.closeResultSet(habcodeRS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return habitationIdGen;
    }

    /*  public ArrayList getAllDataBasedonRationCard1( DataSource ds, String pensionid,String distid) throws SADAREMDBException,SQLException {

    Connection con = null;
    ResultSet rs = null;ResultSet rs1 = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt1 = null;
    // String PersonCode = null;
    //int i = 0;
    HashMap pensiondata_each=null;
    ArrayList data=null;//new ArrayList();
    String name=""; String rationcardno="";
    boolean exists=false;
    try {
    con = DBConnection.getConnection();
    pstmt=con.prepareStatement("select rationcardno  from dbo.Disabled_Pension where pensionid=? and distcode=? and rationcardno is not null");
    pstmt.setString(1, pensionid);
    pstmt.setString(2, distid);
    rs=pstmt.executeQuery();
    if(rs.next()){
    exists=true;
    rationcardno=rs.getString(1);
    data=new ArrayList();
    pstmt=con.prepareStatement("select pensionid,firstname,mid_name,age,fname, rationcardno,  case when sex='2' then 'Female'  when sex='1' then 'Male' end " +
    "as gender,reasonforpersonstatus,pensionphase from dbo.Disabled_Pension where rationcardno=? ");//and pensionid!=?");
    pstmt.setString(1, rs.getString(1));
    // pstmt.setString(2, pensionid);
    rs1=pstmt.executeQuery();
    while(rs1.next())
    {
    pensiondata_each=new HashMap();

    pensiondata_each.put("pensionid",rs1.getString(1));
    name="";
    if(rs1.getString(2)!=null)
    name=rs1.getString(2);
    if(rs1.getString(3)!=null)
    name=name+" "+rs1.getString(3);
    pensiondata_each.put("name",name);
    pensiondata_each.put("age", rs1.getString(4));
    pensiondata_each.put("rno", rs1.getString(6));
    pensiondata_each.put("rname", rs1.getString(5));
    pensiondata_each.put("gender",rs1.getString(7));
    pstmt1=con.prepareStatement("select * from tblperson_personal_details where pensioncard_no=? " +
    "and district_id=? and view_edit_mode='Edit' and status='Active' and rationcard_number=?");
    pstmt1.setString(1, rs1.getString(1)); pstmt1.setString(2, distid);
    pstmt1.setString(3, rationcardno);
    rs=pstmt1.executeQuery();
    if(rs.next()){

    pensiondata_each.put("Ass","PartA Entered");
    pensiondata_each.put("rname", rs.getString("relation_name"));
    pensiondata_each.put("sadarem", rs.getString("person_code"));
    pensiondata_each.put("reason",rs.getString("reasonforpersonstatus"));
    pensiondata_each.put("phase", rs.getString("pensionphase") );
    pensiondata_each.put("url","Done");
    }else{
    pstmt1=con.prepareStatement("select * from tblperson_personal_details p join " +
    "tblPerson_Disability_Details d on(p.person_code=d.person_code) " +
    "join tblPerson_Disability_TotalValue_Details t on(p.person_code=t.person_code)" +
    " where p.pensioncard_no=? and p.district_id=? and p.view_edit_mode='View'" +
    " and p.status='Active' and d.status='Active' and t.status='Active' and p.rationcard_number=?");
    pstmt1.setString(1, rs1.getString(1)); pstmt1.setString(2, distid);
    pstmt1.setString(3, rationcardno);
    rs=pstmt1.executeQuery();
    if(rs.next()){
    pensiondata_each.put("rname", rs.getString("relation_name"));
    pensiondata_each.put("reason",rs.getString("reasonforpersonstatus"));
    pensiondata_each.put("Ass","Assesment Completed");
    pensiondata_each.put("sadarem", rs.getString("person_code"));
    pensiondata_each.put("url","Done");
    pensiondata_each.put("phase", rs.getString("pensionphase") );
    }else{


    pstmt1=con.prepareStatement("select * from tblperson_personal_details where " +
    "pensioncard_no=? and district_id=? and view_edit_mode='View' and status='Active'and rationcard_number=? ");
    pstmt1.setString(1, rs1.getString(1)); pstmt1.setString(2, distid);
    pstmt1.setString(3, rationcardno);
    rs=pstmt1.executeQuery();
    if(rs.next()){

    pensiondata_each.put("Ass","Assesment not completed");
    pensiondata_each.put("rname", rs.getString("relation_name"));
    pensiondata_each.put("sadarem", rs.getString("person_code"));
    pensiondata_each.put("reason",rs.getString("reasonforpersonstatus"));
    pensiondata_each.put("phase", rs.getString("pensionphase") );
    pensiondata_each.put("url","Done");
    }else{
    pensiondata_each.put("reason",rs1.getString("reasonforpersonstatus"));
    pensiondata_each.put("Ass","Assesment Not  Completed");
    pensiondata_each.put("sadarem", "Not Exists");
    pensiondata_each.put("phase", rs1.getString("pensionphase") );
    if(pensionid.equalsIgnoreCase(rs1.getString("pensionid")) &&
    rs1.getString("person_status")!=null &&
    rs1.getString("person_status").equalsIgnoreCase("D")){

    pensiondata_each.put("url", "<a href=\"./getExistingData.do?parameter=getExistingData&pensioncode="+pensionid+"\"><font color=red><b>Go</b></font></a>");

    }else{
    // pensiondata_each.put("Ass","-");
    pensiondata_each.put("url","Done");
    }

    }


    }}
    data.add(pensiondata_each);
    }

    }else{

    pstmt=con.prepareStatement("select *  from dbo.Disabled_Pension" +
    " where pensionid=?  and distcode=? and pensionphase!='PhaseIV'");
    pstmt.setString(1, pensionid);
    pstmt.setString(2, distid);
    rs=pstmt.executeQuery();
    if(rs.next()){
    //  rationcardno=rs.getString("rationcardno");

    pstmt=con.prepareStatement("select * from tblperson_personal_details where "+// pensioncard_no=? and " +
    " district_id=? and status='Active' and rationcard_number is not null  and rationcard_number in(select rationcard_number from tblperson_personal_details " +
    "where pensioncard_no=? and district_id=?)  ");//and pensionid!=?");
    // pstmt.setString(1, pensionid);
    pstmt.setString(1, distid);
    pstmt.setString(2, pensionid);
    pstmt.setString(3, distid);
    rs1=pstmt.executeQuery();
    if(rs1.next()){
    data=new ArrayList();
    rs1=pstmt.executeQuery();
    while(rs1.next())
    {
    pensiondata_each=new HashMap();

    pensiondata_each.put("pensionid",rs1.getString("pensioncard_no"));
    name="";
    if(rs1.getString("surname")!=null)
    name=rs1.getString("surname");
    if(rs1.getString("first_name")!=null)
    name=name+" "+rs1.getString("first_name");
    pensiondata_each.put("name",name);
    pensiondata_each.put("age", rs1.getString("age_years"));
    pensiondata_each.put("rno", rs1.getString("rationcard_number"));
    pensiondata_each.put("rname", rs1.getString("relation_name"));
    pensiondata_each.put("gender",rs1.getString("gender"));
    pstmt1=con.prepareStatement("select * from tblperson_personal_details where pensioncard_no=? and district_id=? and view_edit_mode='Edit' and status='Active'");
    pstmt1.setString(1, rs1.getString("pensioncard_no")); pstmt1.setString(2, distid);
    rs=pstmt1.executeQuery();
    if(rs.next()){
    pensiondata_each.put("Ass","PartA Entered");
    pensiondata_each.put("sadarem", rs1.getString("person_code"));
    pensiondata_each.put("reason",rs1.getString("reasonforpersonstatus"));
    pensiondata_each.put("phase", rs1.getString("pensionphase") );
    pensiondata_each.put("url","Done");
    }else{

    pstmt1=con.prepareStatement("select * from tblperson_personal_details p join " +
    "tblPerson_Disability_Details d on(p.person_code=d.person_code) " +
    "join tblPerson_Disability_TotalValue_Details t on(p.person_code=t.person_code)" +
    " where p.pensioncard_no=? and p.district_id=? and p.view_edit_mode='View'" +
    " and p.status='Active' and d.status='Active' and t.status='Active'");
    pstmt1.setString(1, rs1.getString("pensioncard_no")); pstmt1.setString(2, distid);
    rs=pstmt1.executeQuery();
    if(rs.next()){
    pensiondata_each.put("reason",rs1.getString("reasonforpersonstatus"));
    pensiondata_each.put("Ass","Assesment Completed");
    pensiondata_each.put("sadarem", rs1.getString("person_code"));
    pensiondata_each.put("url","Done");
    pensiondata_each.put("phase", rs1.getString("pensionphase") );
    }else{

    pstmt1=con.prepareStatement("select * from tblperson_personal_details where pensioncard_no=? " +
    "and district_id=? and view_edit_mode='view' and status='Active'");
    pstmt1.setString(1, rs1.getString("pensioncard_no")); pstmt1.setString(2, distid);
    rs=pstmt1.executeQuery();
    if(rs.next()){
    pensiondata_each.put("Ass","Assessment not Completed");
    pensiondata_each.put("sadarem", rs1.getString("person_code"));
    pensiondata_each.put("reason",rs1.getString("reasonforpersonstatus"));
    pensiondata_each.put("phase", rs1.getString("pensionphase") );
    pensiondata_each.put("url","Done");
    }else{
    pensiondata_each.put("reason",rs1.getString("reasonforpersonstatus"));
    pensiondata_each.put("Ass","Assesment Not  Completed");
    pensiondata_each.put("sadarem", "Not Exists");
    pensiondata_each.put("phase", rs1.getString("pensionphase") );

    pensiondata_each.put("url","Done");
    }


    }
    }

    data.add(pensiondata_each);

    }}else{
    data=new ArrayList();
    pstmt=con.prepareStatement("select pensionid,firstname,mid_name,age,fname, rationcardno,  case when sex='2' then 'Female'" +
    "  when sex='1' then 'Male' end as gender,reasonforpersonstatus,pensionphase from dbo.Disabled_Pension " +
    "where pensionid=?  and distcode=?");//and pensionid!=?");
    // pstmt.setString(1, rs.getString(1));
    pstmt.setString(1, pensionid);
    pstmt.setString(2, distid);
    rs1=pstmt.executeQuery();
    if(rs1.next())
    {
    pensiondata_each=new HashMap();

    pensiondata_each.put("pensionid",rs1.getString(1));
    name="";
    if(rs1.getString(2)!=null)
    name=rs1.getString(2);
    if(rs1.getString(3)!=null)
    name=name+" "+rs1.getString(3);
    pensiondata_each.put("name",name);
    pensiondata_each.put("age", rs1.getString(4));
    pensiondata_each.put("rno", rs1.getString(6));
    pensiondata_each.put("rname", rs1.getString(5));
    pensiondata_each.put("gender",rs1.getString(7));
    pensiondata_each.put("reason",rs1.getString("reasonforpersonstatus"));
    pensiondata_each.put("Ass","Assesment Not  Completed");
    pensiondata_each.put("sadarem", "Not Exists");
    pensiondata_each.put("phase", rs1.getString("pensionphase") );
    if(rs1.getString("person_status")!=null &&
    rs1.getString("person_status").equalsIgnoreCase("D")){

    pensiondata_each.put("url", "<a href=\"./getExistingData.do?parameter=getExistingData&pensioncode="+pensionid+"\"><font color=red><b>Go</b></font></a>");

    }else{
    // pensiondata_each.put("Ass","-");
    pensiondata_each.put("url","Done");
    }
    //pensiondata_each.put("url", "<a href=\"./getExistingData.do?parameter=getExistingData&pensioncode="+pensionid+"\"><font color=red><b>Go</b></font></a>");


    data.add(pensiondata_each);
    }


    }

    }else{

    data=null;
    }

    }

    if(exists){
    pstmt=con.prepareStatement("select * from tblperson_personal_details where " +
    " rationcard_number=? and  status='Active' and district_id=? and  " +
    "pensioncard_no not in(select pensionid from Disabled_Pension where" +
    " rationcardno=? and distcode=?)");

    pstmt.setString(1, rationcardno);
    pstmt.setString(2, distid);
    pstmt.setString(3, rationcardno);
    pstmt.setString(4, distid);
    rs=pstmt.executeQuery();
    while(rs.next()){
    pensiondata_each=new HashMap();
    name="";
    pensiondata_each.put("pensionid",rs.getString("pensioncard_no"));
    if(rs.getString("surname")!=null)
    name=rs.getString("surname");
    if(rs.getString("first_name")!=null)
    name=name+" "+rs.getString("first_name");
    pensiondata_each.put("name",name);
    pensiondata_each.put("age", rs.getString("age_years"));
    pensiondata_each.put("rno", rs.getString("rationcard_number"));
    pensiondata_each.put("rname", rs.getString("relation_name"));
    pensiondata_each.put("gender",rs.getString("gender"));
    pstmt1=con.prepareStatement("select * from tblperson_personal_details where pensioncard_no=? and district_id=? and view_edit_mode='Edit' and status='Active'");
    pstmt1.setString(1, rs.getString("pensioncard_no")); pstmt1.setString(2, distid);
    rs1=pstmt1.executeQuery();
    if(rs1.next()){
    pensiondata_each.put("Ass","PartA Entered");
    pensiondata_each.put("sadarem", rs.getString("person_code"));
    pensiondata_each.put("reason",rs.getString("reasonforpersonstatus"));
    pensiondata_each.put("phase", rs.getString("pensionphase") );
    pensiondata_each.put("url","Done");
    }else{

    pstmt1=con.prepareStatement("select * from tblperson_personal_details p join " +
    "tblPerson_Disability_Details d on(p.person_code=d.person_code) " +
    "join tblPerson_Disability_TotalValue_Details t on(p.person_code=t.person_code)" +
    " where p.pensioncard_no=? and p.district_id=? and p.view_edit_mode='View'" +
    " and p.status='Active' and d.status='Active' and t.status='Active'");
    pstmt1.setString(1, rs.getString("pensioncard_no")); pstmt1.setString(2, distid);
    rs1=pstmt1.executeQuery();
    if(rs1.next()){
    pensiondata_each.put("reason",rs.getString("reasonforpersonstatus"));
    pensiondata_each.put("Ass","Assesment Completed");
    pensiondata_each.put("sadarem", rs.getString("person_code"));
    pensiondata_each.put("url","Done");
    pensiondata_each.put("phase", rs.getString("pensionphase") );
    }else{


    pstmt1=con.prepareStatement("select * from tblperson_personal_details where pensioncard_no=? " +
    "and district_id=? and view_edit_mode='View' and status='Active'");
    pstmt1.setString(1, rs.getString("pensioncard_no")); pstmt1.setString(2, distid);
    rs1=pstmt1.executeQuery();
    if(rs1.next()){
    pensiondata_each.put("Ass","Assessment not Completed");
    pensiondata_each.put("sadarem", rs.getString("person_code"));
    pensiondata_each.put("reason",rs.getString("reasonforpersonstatus"));
    pensiondata_each.put("phase", rs.getString("pensionphase") );
    pensiondata_each.put("url","Done");
    }

    }
    }
    data.add(pensiondata_each);


    }

    }    } catch (SQLException sqlException) {
    sqlException.printStackTrace();
    throw new SADAREMException();
    } catch (Exception exception) {
    exception.printStackTrace();
    throw new SADAREMException();

    } finally {//added by rekha


    DBConnection.closeStatement(pstmt);
    DBConnection.closeStatement(pstmt1);
    DBConnection.closeResultSet(rs1);
    DBConnection.closeResultSet(rs);
    DBConnection.closeConnection(con);
    }

    return data;

    }*/
    //Added by Rekha
    public ArrayList getAllDataBasedonRationCard(DataSource ds, String pensionCode, String distid, HttpServletRequest request) throws SADAREMDBException, SQLException {

        Connection con = null;
        ResultSet rs = null;
        ResultSet rs1 = null, rs2 = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        HashMap pensiondata_each = null;
        ArrayList data = null;//new ArrayList();
        String name = "";
        String rationcardno = null;
        //added by kavya
        if (request.getAttribute("rationcard") != null) {
            rationcardno = request.getAttribute("rationcard").toString();
        }
        boolean exists = true;
        String deathcase = null;
        try {
            con = DBConnection.getConnection();
            if ((pensionCode != null && !"".equals(pensionCode)) && (distid != null && !"".equals(distid))) {

                pstmt = con.prepareStatement("select Person_Status,rationcardno from Disabled_pension where pensionid=? and distcode=?");
                pstmt.setString(1, pensionCode);
                pstmt.setString(2, distid);
                rs = pstmt.executeQuery();
                if (rs.next()) {

                    deathcase = rs.getString("Person_Status");
                    rationcardno = rs.getString("rationcardno");
                    if (!CommonConstants.DEATH_CASE.equals(deathcase)) {

                        pstmt = con.prepareStatement("select rationcard_number from tblPerson_Personal_Details_new  with (nolock) where PensionCard_No=? and District_Id=?");
                        pstmt.setString(1, pensionCode);
                        pstmt.setString(2, distid);
                        rs = pstmt.executeQuery();
                        if (rs.next()) {
                            if (rs.getString("rationcard_number") != null) {
                                rationcardno = rs.getString("rationcard_number");
                            }
                        }

                    }
                }
            }
            if (rationcardno != null) {
                data = new ArrayList();
                //COmmon Data
//                pstmt = con.prepareStatement("select p.pensioncard_no,p.surname,p.first_name,p.age_years,p.rationcard_number,p.relation_name,"
//                        + "p.gender,p.person_code,p.reasonforpersonstatus,p.pensionphase,p.view_edit_mode,p.rationcard_slno "
//                        + " from tblperson_personal_details p  with (nolock) join  Disabled_Pension d "
//                        + " on(p.pensioncard_no=d.pensionid and d.distcode=? and d.rationcardno=?) where "
//                        + " district_id=? and rationcard_number=?  and p.ReasonforPersonStatus not in ('" + CommonConstants.ADDRESSCHANGE + "')");



//                String sql = " select p.pensioncard_no,p.surname,p.first_name,p.age_years,p.rationcard_number,p.relation_name,Proof_Id,"
//                        + " p.gender,p.person_code,p.reasonforpersonstatus,p.pensionphase,p.view_edit_mode,p.rationcard_slno "
//                        + " from tblperson_personal_details p  with (nolock) join  Disabled_Pension d "
//                        + " on(p.pensioncard_no=d.pensionid and   d.distcode=? and d.rationcardno=?) where "
//                        + " district_id=? and rationcard_number=?  and p.ReasonforPersonStatus not in ('" + CommonConstants.ADDRESSCHANGE + "')";
//                pstmt = con.prepareStatement(sql);
//                pstmt.setString(1, distid);
//                pstmt.setString(2, rationcardno);
//                pstmt.setString(3, distid);
//                pstmt.setString(4, rationcardno);
//                rs = pstmt.executeQuery();
//                while (rs.next()) {
//                    pensiondata_each = new HashMap();
//                    name = "";
//
//                    if (rs.getString("rationcard_slno") != null) {
//                        pensiondata_each.put("rationcard_slno", rs.getString("rationcard_slno"));
//
//                    } else {
//                        pensiondata_each.put("rationcard_slno", "-");
//                    }
//                    pensiondata_each.put("pensionid", rs.getString("pensioncard_no"));
//                    if (rs.getString("surname") != null) {
//                        name = rs.getString("surname");
//                    }
//                    if (rs.getString("first_name") != null) {
//                        name = name + " " + rs.getString("first_name");
//                    }
//                    pensiondata_each.put("name", name);
//                    pensiondata_each.put("age", rs.getString("age_years"));
//                    pensiondata_each.put("rno", rs.getString("rationcard_number"));
//                    pensiondata_each.put("rname", rs.getString("relation_name"));
//                    if (rs.getString("gender") != null && rs.getString("gender").equalsIgnoreCase("1")) {
//                        pensiondata_each.put("gender", "Male");
//                    } else if (rs.getString("gender") != null && rs.getString("gender").equalsIgnoreCase("2")) {
//                        pensiondata_each.put("gender", "Female");
//                    } else {
//                        pensiondata_each.put("gender", " ");
//                    }
//                    pensiondata_each.put("sadarem", rs.getString("person_code"));
//                    pensiondata_each.put("reason", rs.getString("reasonforpersonstatus"));
//                    pensiondata_each.put("phase", rs.getString("pensionphase"));
//                    pensiondata_each.put("url", "Done");
//                    pstmt1 = con.prepareStatement("select view_edit_mode from tblperson_personal_details  with (nolock) where pensioncard_no=? and district_id=?  ");
//                    pstmt1.setString(1, rs.getString("pensioncard_no"));
//                    pstmt1.setString(2, distid);
//                    rs1 = pstmt1.executeQuery();
//                    if (rs1.next()) {
//                        if (rs.getString("view_edit_mode") != null && rs.getString("view_edit_mode").equalsIgnoreCase("Edit")) {
//                            pensiondata_each.put("Ass", "PartA Entered");
//                        } else {
//                            pstmt1 = con.prepareStatement("select pensioncard_no from tblperson_personal_details p  with (nolock) join "
//                                    + "tblPerson_Disability_Details d on(p.person_code=d.person_code) "
//                                    + "join tblPerson_Disability_TotalValue_Details t on(p.person_code=t.person_code)"
//                                    + " where p.pensioncard_no=? and p.district_id=? and p.view_edit_mode='View'"
//                                    + " and p.status='Active' and d.status='Active' and t.status='Active'");
//                            pstmt1.setString(1, rs.getString("pensioncard_no"));
//                            pstmt1.setString(2, distid);
//                            rs1 = pstmt1.executeQuery();
//                            if (rs1.next()) {
//                                pensiondata_each.put("Ass", "Assessment Completed");
//                            } else {
//                                if (rs.getString("view_edit_mode") != null && rs.getString("view_edit_mode").equalsIgnoreCase("View")) {
//                                    pensiondata_each.put("Ass", "Assessment not Completed");
//
//                                }
//                            }
//                        }
//                    }
//                    data.add(pensiondata_each);
//                }

                // Only tblperson_personal_details data and not in Disabled_Pension

                pstmt = con.prepareStatement("select p.pensioncard_no,p.surname,p.first_name,p.age_years,p.rationcard_number,p.relation_name,Proof_Id,"
                        + " p.gender,p.person_code,p.reasonforpersonstatus,p.pensionphase,p.view_edit_mode,p.rationcard_slno "
                        + " from tblperson_personal_details_new p  with (nolock) join  Disabled_Pension d "
                        + " on(p.pensioncard_no=d.pensionid and   d.distcode=? and d.rationcardno=?) where "
                        + " district_id=? and rationcard_number=?  and p.ReasonforPersonStatus not in ('" + CommonConstants.ADDRESSCHANGE + "')"
                        + " union"
                        + " select pensioncard_no,surname,first_name,age_years,rationcard_number,relation_name,Proof_Id,"
                        + " gender,person_code,reasonforpersonstatus,pensionphase,view_edit_mode,rationcard_slno "
                        + " from tblperson_personal_details_new  with (nolock) where pensioncard_no "
                        + " not in(select pensionid from  Disabled_Pension where distcode=? and rationcardno=?)"
                        + " and district_id=? and rationcard_number=? and ReasonforPersonStatus not in ('" + CommonConstants.ADDRESSCHANGE + "')"
                        + " union"
                        + " select p.pensioncard_no,p.surname,p.first_name,p.age_years,p.rationcard_number,p.relation_name,Proof_Id,"
                        + " p.gender,p.person_code,p.reasonforpersonstatus,p.pensionphase,p.view_edit_mode,p.rationcard_slno "
                        + " from tblperson_personal_details_new p  with (nolock) where proof_id in (select proof_id from tblperson_personal_details where "
                        + " rationcard_number = ? and proof_id is not null and proof_id<>'')  and proofdoc_type in ('Aadhaar Card','Aadhaar ID','Aadhar Card','Adhaar Card')  and p.ReasonforPersonStatus not in ('" + CommonConstants.ADDRESSCHANGE + "')");



                pstmt.setString(1, distid);
                pstmt.setString(2, rationcardno);
                pstmt.setString(3, distid);
                pstmt.setString(4, rationcardno);
                pstmt.setString(5, distid);
                pstmt.setString(6, rationcardno);
                pstmt.setString(7, distid);
                pstmt.setString(8, rationcardno);
                pstmt.setString(9, rationcardno);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    pensiondata_each = new HashMap();
                    name = "";
                    if (rs.getString("rationcard_slno") != null) {
                        pensiondata_each.put("rationcard_slno", rs.getString("rationcard_slno"));

                    } else {
                        pensiondata_each.put("rationcard_slno", "-");
                    }
                    pensiondata_each.put("pensionid", rs.getString("pensioncard_no"));
                    if (rs.getString("surname") != null) {
                        name = rs.getString("surname");
                    }
                    if (rs.getString("first_name") != null) {
                        name = name + " " + rs.getString("first_name");
                    }
                    pensiondata_each.put("name", name);
                    pensiondata_each.put("age", rs.getString("age_years"));
                    pensiondata_each.put("rno", rs.getString("rationcard_number"));
                    pensiondata_each.put("rname", rs.getString("relation_name"));

                    if (rs.getString("gender") != null && rs.getString("gender").equalsIgnoreCase("1")) {
                        pensiondata_each.put("gender", "Male");
                    } else if (rs.getString("gender") != null && rs.getString("gender").equalsIgnoreCase("2")) {
                        pensiondata_each.put("gender", "Female");
                    } else {
                        pensiondata_each.put("gender", " ");
                    }

                    pensiondata_each.put("sadarem", rs.getString("person_code"));
                    pensiondata_each.put("reason", rs.getString("reasonforpersonstatus"));
                    pensiondata_each.put("phase", rs.getString("pensionphase"));
                    pensiondata_each.put("url", "Done");
                    if (rs.getString("view_edit_mode") != null && rs.getString("view_edit_mode").equalsIgnoreCase("Edit")) {
                        pensiondata_each.put("Ass", "PartA Entered");
                    } else {
                        pstmt1 = con.prepareStatement("select pensioncard_no from tblperson_personal_details p  with (nolock) join "
                                + "tblPerson_Disability_Details d on(p.person_code=d.person_code) "
                                + "join tblPerson_Disability_TotalValue_Details t on(p.person_code=t.person_code)"
                                + " where p.pensioncard_no=? and p.district_id=? and p.view_edit_mode='View'"
                                + " and p.status='Active' and d.status='Active' and t.status='Active' and p.ReasonforPersonStatus not in ('" + CommonConstants.ADDRESSCHANGE + "')");
                        pstmt1.setString(1, rs.getString("pensioncard_no"));
                        pstmt1.setString(2, distid);
                        rs1 = pstmt1.executeQuery();
                        if (rs1.next()) {
                            pensiondata_each.put("Ass", "Assessment Completed");
                        } else {
                            if (rs.getString("view_edit_mode") != null && rs.getString("view_edit_mode").equalsIgnoreCase("View")) {
                                pensiondata_each.put("Ass", "Assessment not Completed");
                            }

                        }
                    }
                    data.add(pensiondata_each);


                }

// Only Disabled_Pension  data and not in tblperson_personal_details

                pstmt = con.prepareStatement("select pensionid,firstname, mid_name , age, "
                        + " rationcardno,fname, case when sex='2' then 'Female'  when sex='1' then 'Male' end as sex,reasonforpersonstatus,pensionphase from  Disabled_Pension where  pensionid "
                        + " not in(select pensioncard_no from tblperson_personal_details_new with (nolock) where district_id=?  "
                        + " and rationcard_number=? ) and distcode=? and rationcardno=?");

                pstmt.setString(1, distid);
                pstmt.setString(2, rationcardno);
                pstmt.setString(3, distid);
                pstmt.setString(4, rationcardno);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    pensiondata_each = new HashMap();
                    name = "";



                    pensiondata_each.put("pensionid", rs.getString("pensionid"));
                    if (rs.getString("firstname") != null) {
                        name = rs.getString("firstname");
                    }
                    if (rs.getString("mid_name") != null) {
                        name = name + " " + rs.getString("mid_name");
                    }
                    pensiondata_each.put("name", name);
                    pensiondata_each.put("age", rs.getString("age"));
                    pensiondata_each.put("rno", rs.getString("rationcardno"));
                    pensiondata_each.put("rname", rs.getString("fname"));
                    pensiondata_each.put("gender", rs.getString("sex"));

                    pensiondata_each.put("Ass", "Assessment not Completed");
                    pensiondata_each.put("sadarem", "Not Exists");
                    pensiondata_each.put("reason", rs.getString("reasonforpersonstatus"));
                    pensiondata_each.put("phase", rs.getString("pensionphase"));
                    pensiondata_each.put("url", "Done");
                    pensiondata_each.put("rationcard_slno", "-");
                    if (request.getAttribute("rationcard") != null) {
                        pensionCode = rs.getString("pensionid");
                        exists = isExistInPersonCode(con, ds, pensionCode, distid);
                        if (exists) {
                            pensiondata_each.put("url", "<a href=\"./getExistingData.do?parameter=getExistingData&pensioncode=" + pensionCode + "\"><font color=red><b>Go</b></font></a>");
                        }
                    } else if (pensionCode != null && pensionCode.equalsIgnoreCase(rs.getString("pensionid")) && exists
                            && !CommonConstants.DEATH_CASE.equals(deathcase)) {
                        pensiondata_each.put("url", "<a href=\"./getExistingData.do?parameter=getExistingData&pensioncode=" + pensionCode + "\"><font color=red><b>Go</b></font></a>");
                    }
                    data.add(pensiondata_each);
                }
            } else if (rationcardno == null) {
                exists = false;
                data = new ArrayList();
                pstmt = con.prepareStatement("select pensionid,firstname, mid_name , age, "
                        + " rationcardno,fname, case when sex='2' then 'Female'  when sex='1' then 'Male' end as sex,reasonforpersonstatus,pensionphase,Person_Status from  Disabled_Pension where  "
                        + "distcode=? and pensionid =? and pensionphase!='PhaseIV'");

                pstmt.setString(1, distid);
                pstmt.setString(2, pensionCode);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    pensiondata_each = new HashMap();
                    name = "";
                    pensiondata_each.put("pensionid", rs.getString("pensionid"));
                    if (rs.getString("firstname") != null) {
                        name = rs.getString("firstname");
                    }
                    if (rs.getString("mid_name") != null) {
                        name = name + " " + rs.getString("mid_name");
                    }
                    pensiondata_each.put("name", name);
                    pensiondata_each.put("age", rs.getString("age"));
                    pensiondata_each.put("rno", "Does not exists");
                    pensiondata_each.put("rname", rs.getString("fname"));
                    pensiondata_each.put("gender", rs.getString("sex"));
                    pensiondata_each.put("rationcard_slno", "-");
                    pensiondata_each.put("Ass", "Assessment not Completed");
                    pensiondata_each.put("sadarem", "Not Exists");
                    pensiondata_each.put("reason", rs.getString("reasonforpersonstatus"));
                    pensiondata_each.put("phase", rs.getString("pensionphase"));

                    if (pensionCode != null && pensionCode.equalsIgnoreCase(rs.getString("pensionid")) && !exists
                            && !CommonConstants.DEATH_CASE.equals(rs.getString("Person_Status"))) {
                        pensiondata_each.put("url", "<a href=\"./getExistingData.do?parameter=getExistingData&pensioncode=" + pensionCode + "\"><font color=red><b>Go</b></font></a>");

                    } else {
                        pensiondata_each.put("url", "Done");
                    }
                    data.add(pensiondata_each);
                } else {
                    // request.setAttribute("msg1", "Without pensioncard PhaseIV pensioners are not allowed");
                    data = new ArrayList();
                    data = null;
                }

            } else {
                data = null;
            }



        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAllDataBasedonRationCard", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getAllDataBasedonRationCard");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAllDataBasedonRationCard", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getAllDataBasedonRationCard");
        } finally {//added by rekha

            //DBConnection.closeStatement(cs);
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(pstmt1);
            DBConnection.closeResultSet(rs1);
            DBConnection.closeResultSet(rs);

            DBConnection.closeStatement(pstmt2);
            DBConnection.closeResultSet(rs2);
        }

        return data;
    }
//added by kavya

    public boolean isExistInPersonCode(Connection con, DataSource ds, String pensionCode, String distid) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        boolean flag = true;
        try {
            pstmt = con.prepareStatement("select Person_Status,rationcardno from Disabled_pension where pensionid=? and distcode=?");
            pstmt.setString(1, pensionCode);
            pstmt.setString(2, distid);
            rs = pstmt.executeQuery();
            if (rs.next()) {

                String deathcase = rs.getString("Person_Status");
                if (!CommonConstants.DEATH_CASE.equals(deathcase)) {
                    pstmt = con.prepareStatement("select PensionCard_No from tblPerson_Personal_Details  with (nolock) where PensionCard_No=? and District_Id=? and ReasonforPersonStatus not in (?)");
                    pstmt.setString(1, pensionCode);
                    pstmt.setString(2, distid);
                    pstmt.setString(3, CommonConstants.ADDRESSCHANGE );
                    rs = pstmt.executeQuery();
                    if (rs != null && rs.next()) {
                        flag = false;
                    }
                } else {
                    flag = false;
                }
            }
        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "isExistInPersonCode", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "isExistInPersonCode");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "isExistInPersonCode", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "isExistInPersonCode");
        } finally {
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);

        }

        return flag;
    }
//added by kavya

    public String getAssesmentStatusRationcardAndSlnoStatus(DataSource ds, String rationcard, String slno, String personCode) throws SADAREMDBException, SQLException {
        String message = CommonConstants.ERROR;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            con = DBConnection.getConnection();
            String query = "select person_code from  tblPerson_Personal_Details where rationcard_number=? and rationcard_slno=? and status='Active'";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, rationcard);
            pstmt.setString(2, slno);
            rs = pstmt.executeQuery();

            if (rs != null && rs.next()) {
                if (personCode != null && personCode.equals(rs.getString("person_code"))) {
                    message = CommonConstants.SUCCESS;
                } else {
                    message = "Already Assessment done with " + rationcard + " with serial number " + slno + ".SADAREM ID is :" + rs.getString("person_code");
                }

            } else {
                query = "select person_code from  tblPerson_Personal_Details_new where rationcard_number=? and rationcard_slno=? and status='Active'";

                pstmt = con.prepareStatement(query);
                pstmt.setString(1, rationcard);
                pstmt.setString(2, slno);
                rs = pstmt.executeQuery();

                if (rs != null && rs.next()) {
                    if (personCode != null && personCode.equals(rs.getString("person_code"))) {
                        message = CommonConstants.SUCCESS;
                    } else {
                        message = "Already Assessment done with " + rationcard + " with serial number " + slno + ".SADAREM ID is :" + rs.getString("person_code");
                    }

                } else {
                    message = CommonConstants.SUCCESS;
                }
            }
        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAssesmentStatusRationcardAndSlno", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getAssesmentStatusRationcardAndSlno");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAssesmentStatusRationcardAndSlno", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getAssesmentStatusRationcardAndSlno");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);

        }

        return message;
    }

    public String getRAPTAPRationCardSerialNumberStatus(DataSource ds, String rationcard, String slno, String districtId) throws SADAREMDBException, SQLException {
        String message = CommonConstants.ERROR;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String tablename = null;
        ResultSet rs1 = null;
        try {
            con = DBConnection.getConnection();
            if (districtId != null) {
                tablename = " CivilSupply_Database.dbo.tblRationcard_details_" + districtId;

            }

            pstmt = con.prepareStatement("select membername from " + tablename + " where HOUSEHOLDCARDNO=? and slno=?" );
            pstmt.setString(1, rationcard);
            pstmt.setString(2, slno);

            rs = pstmt.executeQuery();

            if (rs != null && rs.next()) {
                message = CommonConstants.SUCCESS;
            } else {

                pstmt = con.prepareStatement("select max(slno) from " + tablename + " where HOUSEHOLDCARDNO=?");
                pstmt.setString(1, rationcard);
                rs1 = pstmt.executeQuery();

                if (rs1 != null && rs1.next()) {
                    message = "You have entered in valid Ration card serial number." + rs1.getInt(1) + " is the max serial number";
                } else {
                    message = "Ration card Details not Found in SADAREM DB.Please Raise request in Issue Raise Under category Ration card Not Open for RAP and TAP Rationcard No.";
                }

            }
        } catch (SQLException sqlEx) {System.out.println(sqlEx);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRAPTAPRationCardSerialNumberStatus", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getRAPTAPRationCardSerialNumberStatus");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRAPTAPRationCardSerialNumberStatus", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getRAPTAPRationCardSerialNumberStatus");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
            DBConnection.closeResultSet(rs1);

        }

        return message;
    }

    public static ArrayList getCampBasedOnLoginDetailsForPartA(DataSource ds, String districtId, String disabilityId) throws Exception {
        Connection con = null;
        ResultSet rs = null;
        ArrayList territory_info = new ArrayList();
        ArrayList territory_id_list = new ArrayList();
        ArrayList territory_name_list = new ArrayList();
        CallableStatement cs = null;
        try {
            con = DBConnection.getConnection();

            if (districtId != null && disabilityId != null && !disabilityId.equals("6")) {
                cs = con.prepareCall("{call Sp_Camplist_selection(?,?)}");
                cs.setString(1, districtId);
                cs.setString(2, disabilityId);

                rs = cs.executeQuery();
                while (rs.next()) {
                    territory_id_list.add(rs.getString(1));
                    territory_name_list.add(rs.getString(2));
                }
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampBasedOnLoginDetailsForPartA", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getCampBasedOnLoginDetailsForPartA");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampBasedOnLoginDetailsForPartA", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getCampBasedOnLoginDetailsForPartA");
        } finally {
            territory_info.add(territory_name_list);
            territory_info.add(territory_id_list);
            DBConnection.closeResultSet(rs);
            // DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cs);
        }
        return territory_info;
    }
    public static ArrayList getNewCampBasedOnLoginCampDetailsForPartA(DataSource ds, String districtId, String disabilityId,String campId) throws Exception {
        Connection con = null;
        ResultSet rs = null;
        ArrayList territory_info = new ArrayList();
        ArrayList territory_id_list = new ArrayList();
        ArrayList territory_name_list = new ArrayList();
        CallableStatement cs = null;
        try {
            con = DBConnection.getConnection();

            if (districtId != null && disabilityId != null && !disabilityId.equals("6")) {
                cs = con.prepareCall("{call Sp_NewCamplist_selection(?,?)}");
                cs.setString(1, districtId);
                cs.setString(2, campId);

                rs = cs.executeQuery();
                while (rs.next()) {
                    territory_id_list.add(rs.getString(1));
                    territory_name_list.add(rs.getString(2));
                }
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampBasedOnLoginDetailsForPartA", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getCampBasedOnLoginDetailsForPartA");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampBasedOnLoginDetailsForPartA", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getCampBasedOnLoginDetailsForPartA");
        } finally {
            territory_info.add(territory_name_list);
            territory_info.add(territory_id_list);
            DBConnection.closeResultSet(rs);
            // DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cs);
        }
        return territory_info;
    }
    public ArrayList getcampdropdownList(DataSource ds, String districtId, String disabilityId) throws Exception {
        Connection con = null;
        ResultSet rs = null;
        CallableStatement cs = null;
        ArrayList list = new ArrayList();
        ArrayList territory_info = new ArrayList();
        ArrayList territory_id_list = new ArrayList();
        ArrayList territory_name_list = new ArrayList();
        Map m = null;
        try {
            con = DBConnection.getConnection();
            if (districtId != null && disabilityId != null && !disabilityId.equals("6")) {
                cs = con.prepareCall("{call Sp_Camplist_selection(?,?)}");
                cs.setString(1, districtId);
                cs.setString(2, disabilityId);
                rs = cs.executeQuery();
                while (rs.next()) {
                    m = new HashMap();
                    territory_id_list.add(rs.getString(1));
                    territory_name_list.add(rs.getString(2));
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getcampdropdownList", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getcampdropdownList");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getcampdropdownList", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getcampdropdownList");
        } finally {
            territory_info.add(territory_name_list);
            territory_info.add(territory_id_list);
            DBConnection.closeResultSet(rs);
            // DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cs);
        }
        return territory_info;
    }

    public String getcampdropdownList1(DataSource ds, String districtId, String disabilityId) throws Exception {
        Connection con = null;
        ResultSet rs = null;
        CallableStatement cs = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            con = DBConnection.getConnection();
            if (districtId != null && disabilityId != null && !disabilityId.equals("6")) {
                cs = con.prepareCall("{call Sp_Camplist_selection(?,?)}");
                cs.setString(1, districtId);
                cs.setString(2, disabilityId);
                rs = cs.executeQuery();
                if (rs != null) {
                    stringBuffer.append("<select property='partaCampId' onchange='getNonpensionerCampdateDetails(this.value),setpartacampid(this.value);'  style='width:400px' >");
                    stringBuffer.append("<option value='0' >--SELECT--</option>\n");
                    while (rs.next()) {
                        stringBuffer.append("<option value=" + rs.getString(1) + ">" + rs.getString(2) + "</option>");
                    }
                    stringBuffer.append("</select>");
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getcampdropdownList1", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getcampdropdownList1");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getcampdropdownList1", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getcampdropdownList1");
        } finally {
            DBConnection.closeResultSet(rs);
            // DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cs);
        }
        return stringBuffer.toString();
    }

    public String getcampdatedropdownList(DataSource ds, String districtId, String disabilityId) throws Exception {
        Connection con = null;
        ResultSet rs = null;
        CallableStatement cs = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            con = DBConnection.getConnection();

            cs = con.prepareCall("{call Sp_Campdate_selection(?,?)}");
            cs.setString(1, districtId);
            cs.setString(2, disabilityId);
            rs = cs.executeQuery();
            if (rs != null) {
                stringBuffer.append("<select property='partaCampDate'   style='width:200px' >");
                stringBuffer.append("<option value='0'  >--SELECT--</option>\n");
                while (rs.next()) {
                    stringBuffer.append("<option value=" + rs.getString(1) + ">" + rs.getString(1) + "</option>");
                }
                stringBuffer.append("</select>");
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getcampdatedropdownList", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getcampdatedropdownList");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getcampdatedropdownList", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getcampdatedropdownList");
        } finally {
            DBConnection.closeResultSet(rs);
            // DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cs);
        }
        return stringBuffer.toString();
    }

    public static ArrayList getCampDatesBasedOnLoginDetailsForPartA(DataSource ds, String districtId, String disabilityId) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        ArrayList territory_info = new ArrayList();
        ArrayList territory_id_list = new ArrayList();
        try {
            con = DBConnection.getConnection();
            cs = con.prepareCall("{call Sp_Campdate_selection(?,?)}");
            cs.setString(1, districtId);
            cs.setString(2, disabilityId);

            rs = cs.executeQuery();
            while (rs.next()) {
                territory_id_list.add(rs.getString(1));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDatesBasedOnLoginDetailsForPartA", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getCampDatesBasedOnLoginDetailsForPartA");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDatesBasedOnLoginDetailsForPartA", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getCampDatesBasedOnLoginDetailsForPartA");
        } finally {
            territory_info.add(territory_id_list);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cs);
            DBConnection.closeConnection(con);
        }

        return territory_info;
    }

    public ArrayList getMultipleCampDetails(DataSource ds, String districtId) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList territory_info = new ArrayList();
        Map m = null;
        try {
            con = DBConnection.getConnection();
            String query = "select distinct camp_id,name,Address,venuename from dbo.tblCamp_Details a join CampDetailsDateWise b on(a.camp_id=b.campid) "
                    + " where districtid= ? and  a.district_id = b.districtid and (DATEADD(DD,0,DATEDIFF(DD,0,campdate))) >=  (DATEADD(DD,0,DATEDIFF(DD,0,getdate()))) "
                    + " order by name";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                m = new HashMap();
                m.put("campid", rs.getString(1));
                m.put("campname", rs.getString(2) + " (" + rs.getString(4) + "), " + rs.getString(3));
                territory_info.add(m);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleCampDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getMultipleCampDetails");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleCampDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getMultipleCampDetails");
        } finally {
            try {
                DBConnection.closeResultSet(rs);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeConnection(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return territory_info;
    }

    public String getMultipleDisabilitiesDetails(DataSource ds, String campid, String districtId, String rowId, String flag, String date) throws Exception {
        StringBuffer mateDetails = new StringBuffer();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String campdate = null;
        try {
            Date partcampdate = new SimpleDateFormat("dd/mm/yyyy").parse(date);
            campdate = new SimpleDateFormat("mm/dd/yyyy").format(partcampdate);
            con = DBConnection.getConnection();
            

            String query = "select * from (select 'dis'= case  when ohcountpending <> 0  then '1.Locomotor/OH'"
                    + "  end from CampDetailsDateWise   where campid = ? and districtid = ? and campdate=? "
                    + "      union "
                    + "     select 'dis'= case     when vicountpending <> 0  then  '2.Visual Impairment' "
                    + "   end from CampDetailsDateWise    where campid = ? and districtid = ? and campdate=? "
                    + "      union      select 'dis'= case    when hicountpending <> 0  then '3.Hearing Impairment' end "
                    + " from CampDetailsDateWise   where campid = ? and districtid = ? and campdate=? "
                    + "   union     select 'dis'= case    when mrcountpending <> 0  then '4.Mental Retardation' "
                    + "   end from CampDetailsDateWise   where campid = ? and districtid = ? and campdate=? "
                    + "   union     select 'dis'= case    when micountpending <> 0  then '5.Mental Illness' end from CampDetailsDateWise"
                    + "   where campid = ? and districtid = ? and campdate=?)a   where dis is not null";
            pstmt=con.prepareStatement(query);
            pstmt.setString(1, campid);
            pstmt.setString(2, districtId);
            pstmt.setString(3, campdate);
            pstmt.setString(4, campid);
            pstmt.setString(5, districtId);
            pstmt.setString(6, campdate);
            pstmt.setString(7, campid);
            pstmt.setString(8, districtId);
            pstmt.setString(9, campdate);
            pstmt.setString(10, campid);
            pstmt.setString(11, districtId);
            pstmt.setString(12, campdate);
            pstmt.setString(13, campid);
            pstmt.setString(14, districtId);
            pstmt.setString(15, campdate);
            rs = pstmt.executeQuery();
            if (rs != null) {
                String id = rowId + "#2";
                mateDetails.append("<select name='dynaProperty(" + id + ")' id='" + id + "' onchange=\"checkOtherOptions(" + rowId + ",this.value,2);\" >");
                mateDetails.append("<option value='0'>--SELECT--</option>\n");
                while (rs.next()) {
                    mateDetails.append("<option value='" + rs.getString(1).substring(0, 1) + "'>" + rs.getString(1).substring(2, rs.getString(1).length()) + "</option>");
                }
                mateDetails.append("</select>");
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleDisabilitiesDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getMultipleDisabilitiesDetails");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleDisabilitiesDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getMultipleDisabilitiesDetails");
        } finally {
            try {
                DBConnection.closeResultSet(rs);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeConnection(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mateDetails.toString();
    }

    public String getMultipleCampDatesDetails(DataSource ds, String districtId, String rowId, String flag, String campid) throws Exception {
        StringBuffer mateDetails = new StringBuffer();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            
            String query = "select convert(varchar,campdate,103)campdate from CampDetailsDateWise where districtid=? and campid=? and  (DATEADD(DD,0,DATEDIFF(DD,0,campdate))) >=  (DATEADD(DD,0,DATEDIFF(DD,0,getdate()))) ";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtId);
            pstmt.setString(2, campid);

            rs = pstmt.executeQuery();
            if (rs != null) {//
                String id = rowId + "#3";
                mateDetails.append("<select style='width:100px' name='dynaProperty(" + id + ")' id='" + id + "' onchange=\"getMultipleDisabilitiesDetails(" + campid + "," + rowId + "," + flag + ",this.value);\" >");
                mateDetails.append("<option value='0'  >--SELECT--</option>\n");
                while (rs.next()) {
                    mateDetails.append("<option value=" + rs.getString(1) + ">" + rs.getString(1) + "</option>");
                }
                mateDetails.append("</select>");
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleCampDatesDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getMultipleCampDatesDetails");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleCampDatesDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getMultipleCampDatesDetails");
        } finally {
            try {
                DBConnection.closeResultSet(rs);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeConnection(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mateDetails.toString();
    }

    public String getCampDatesDetails(DataSource ds, String districtId, String campid, String disabilityId) throws Exception {
        StringBuffer mateDetails = new StringBuffer();
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            cs = con.prepareCall("{call Sp_Campdate_selection(?,?)}");
            cs.setString(1, districtId);
            cs.setString(2, campid);

            rs = cs.executeQuery();
            if (rs != null) {//
                mateDetails.append("<select style='width:100px' name='partaCampDate'   >");
                mateDetails.append("<option value='0'  >--SELECT--</option>\n");
                while (rs.next()) {
                    mateDetails.append("<option value=" + rs.getString(1) + ">" + rs.getString(1) + "</option>");
                }
                mateDetails.append("</select>");
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDatesDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getCampDatesDetails");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDatesDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getCampDatesDetails");
        } finally {
            try {
                DBConnection.closeResultSet(rs);
                DBConnection.closeStatement(cs);
                DBConnection.closeConnection(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mateDetails.toString();
    }

    public ArrayList getmultipleDisabilityDetails(DataSource ds, String SadaremId, PartADTO partADTO) throws SADAREMDBException, SQLException {
        ArrayList getPartaacknowledgementDetails = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;
        Map templist = null;
        Statement st=null;
        try {
            con = DBConnection.getConnection();
            
            sql = "  select b.name+','+b.address,convert(varchar,a.campdate,103)campdate,c.disability_name   from  Disabilitycampdatedetails a,tblCamp_Details b,dbo.tblDisability_Details c"
                    + " where personcode=? AND  a.campid = b.camp_id ANd b.camp_id = a.campid  and c.disability_id=a.disabilityid  and (DATEADD(DD,0,DATEDIFF(DD,0,campdate))) >=  (DATEADD(DD,0,DATEDIFF(DD,0,getdate()))) ";
            ps = con.prepareStatement(sql);
            ps.setString(1, SadaremId);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    templist = new HashMap();
                    templist.put("campname", rs.getString(1).trim());
                    templist.put("campdate", rs.getString(2));
                    templist.put("disability", rs.getString(3).substring(2, rs.getString(3).length()));
                    getPartaacknowledgementDetails.add(templist);
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getmultipleDisabilityDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getmultipleDisabilityDetails");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getmultipleDisabilityDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getmultipleDisabilityDetails");
        } finally 
        { 
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(ps);
            DBConnection.closeConnection(con);
        }
        return getPartaacknowledgementDetails;
    }

    public String getFirstNameForsms(DataSource ds, String SadaremId, PartADTO partADTO) throws SADAREMDBException, SQLException {
        String firstName = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;
        Statement st = null;

        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            sql = "select first_name from tblPerson_Personal_Details where person_code=?";
            ps = con.prepareStatement(sql);
            ps.setString(1,SadaremId);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    firstName = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getFirstNameForsms", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getFirstNameForsms");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getFirstNameForsms", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getFirstNameForsms");
        } finally {
        	DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(ps);
            DBConnection.closeConnection(con);
        }
        return firstName;
    }

    public ArrayList getsmsCampDetials(DataSource ds, String SadaremId, PartADTO partADTO) throws SADAREMDBException, SQLException {
        ArrayList getPartaacknowledgementDetails = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;
        Map templist = null;
        Statement st = null;

        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            sql = "  select b.name+','+b.address,convert(varchar,a.campdate,3)campdate,c.disability_name   from  Disabilitycampdatedetails a,tblCamp_Details b,dbo.tblDisability_Details c"
                    + " where personcode=? AND  a.campid = b.camp_id ANd b.camp_id = a.campid  and c.disability_id=a.disabilityid ";
            ps = con.prepareStatement(sql);
            ps.setString(1,SadaremId);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    templist = new HashMap();
                    getPartaacknowledgementDetails.add(rs.getString(1).trim());
                    getPartaacknowledgementDetails.add(rs.getString(2));
                    getPartaacknowledgementDetails.add(rs.getString(3));
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getsmsCampDetials", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getsmsCampDetials");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getsmsCampDetials", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getsmsCampDetials");
        } finally {
        	DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(ps);
            DBConnection.closeConnection(con);
        }
        return getPartaacknowledgementDetails;
    }

    public ArrayList getPartaacknowledgementDetails(DataSource ds, String SadaremId, PartADTO partADTO) throws SADAREMDBException, SQLException {
        ArrayList getPartaacknowledgementDetails = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;
        Map templist = null;
        Statement st = null;
        String campname = "";
        String campdate = "";
        String disabilityId = null;
        String campId = null;
        String districtId = null;
        String tokencampdate = null;
        String tokenNo = null;
        String dpmname = null;
        String contactno = null;
        ArrayList list = new ArrayList();
        String disabilitytype = "";
        try {
            con = DBConnection.getConnection();
            String query = "  select b.name+','+b.address,convert(varchar,a.campdate,103)campdate  from  Disabilitycampdatedetails a,tblCamp_Details b"
                    + "  where personcode=? AND  a.campid = b.camp_id ANd b.camp_id = a.campid AND   (DATEADD(DD,0,DATEDIFF(DD,0,campdate))) >=  (DATEADD(DD,0,DATEDIFF(DD,0,getdate())))";

            ps = con.prepareStatement(query);
            ps.setString(1, SadaremId);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    campname = rs.getString(1);
                    campdate = rs.getString(2);
                }
            }
            sql = "select disabilityid,campid,districtid,convert(varchar,campdate,101)campdate"
                    + " from  dbo.Disabilitycampdatedetails where personcode=? order by rowid desc";
            ps = con.prepareStatement(sql);
            ps.setString(1, SadaremId);
            rs = ps.executeQuery();
            int i = 1;
            if (rs != null) {
                while (rs.next()) {
                    list.add(campId = rs.getString(2));
                    if (i == 1) {
                        disabilityId = rs.getString(1);
                        campId = rs.getString(2);
                        i++;
                    }
                    if (list.size() == 2) {
                        campId = rs.getString(2);
                        districtId = rs.getString(3);
                        tokencampdate = rs.getString(4);
                        disabilitytype = "For Multiple Disability";
                    } else {

                        campId = rs.getString(2);
                        disabilityId = rs.getString(1);
                        districtId = rs.getString(3);
                        tokencampdate = rs.getString(4);
                        disabilitytype = "";
                    }

                }
            }
            if (disabilityId != null) {
                if (disabilityId.equals("1")) {
                    sql = "select ohcount,ohcountpending from dbo.CampDetailsDateWise where campid=? and districtid=? and campdate=?";
                } else if (disabilityId.equals("2")) {
                    sql = "select vicount,vicountpending from dbo.CampDetailsDateWise where campid=? and districtid=? and campdate=?";
                } else if (disabilityId.equals("3")) {
                    sql = "select hicount,hicountpending from dbo.CampDetailsDateWise where campid=? and districtid=? and campdate=?";
                } else if (disabilityId.equals("4")) {
                    sql = "select mrcount,mrcountpending from dbo.CampDetailsDateWise where campid=? and districtid=? and campdate=?";
                } else if (disabilityId.equals("5")) {
                    sql = "select micount,micountpending from dbo.CampDetailsDateWise where campid=? and districtid=? and campdate=?";
                }
            }
            ps = con.prepareStatement(sql);
            ps.setString(1, campId);
            ps.setString(2, districtId);
            ps.setString(3, campdate);
            
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    if (rs.getString(1) != null && rs.getString(2) != null) {
                        tokenNo = Integer.parseInt(rs.getString(1)) - Integer.parseInt(rs.getString(2)) + " / " + rs.getString(1);
                    } else {
                        tokenNo = "Not Generated";
                    }
                }
            }

            sql = "select b.name,b.contactno,convert(varchar,getDate(),103)currentdate from tblDistrict_Details a "
                    + " join contactus b on(a.district_name=b.district)"
                    + " where a.district_id=? and b.type='Campincharge' and b.campid=?";
            ps =con.prepareStatement(sql);
            ps.setString(1, districtId);
            ps.setString(2, campId);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    dpmname = rs.getString(1);
                    contactno = rs.getString(2);
                }
            }
            if (dpmname == null) {

                dpmname = "NA";
                contactno = "";
            }


            sql = " select distinct person_code,surname +space(2)+first_name fullname,relation_name,P.Relationship,p.Marital_Status,P.Gender,p.house_number,x.habitation_name,v.village_name,m.mandal_name,n.district_name,convert(varchar,p.created_date,103) created_date"
                    + ",y.ackno,convert(varchar,getDate(),103)currentdate  from dbo.tblperson_personal_details_new P ,tbldistrict_details n,TBLMANDAL_DETAILS M,TBLVILLAGE_DETAILS V,dbo.tblHabitation_Details x ,Disabilitycampdatedetails y"
                    + " where p.status='Active'  and Person_code=? and p.districtid = n.district_id and P.DISTRICTID = M.DISTRICT_ID AND P.MANDALID = M.MANDAL_ID ANd P.DISTRICTID = V.DISTRICT_ID AND P.MANDALID = V.MANDAL_ID AND P.VILLAGEID = V.VILLAGE_ID and p.habcode = x.habitation_code"
                    + "  and y.personcode=p.person_code";
            ps = con.prepareStatement(sql);
            ps.setString(1, SadaremId);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    templist = new HashMap();
                    templist.put("person_code", rs.getString("person_code"));
                    templist.put("fullname", rs.getString("fullname"));
                    templist.put("relation_name", rs.getString("relation_name"));


                    if (rs.getString("Gender").equals("1")) { 


                        Integer maritalstatus = Integer.parseInt(rs.getString("Marital_Status"));

                        if (maritalstatus.equals(1)) {
                            templist.put("Marital_Status", "Shri");
                        }
                        int relationship = Integer.parseInt(rs.getString("Relationship"));
                        switch (relationship) {
                            case 0: templist.put("Relationship", CertificateConstants.CARE_OF);
                                   break;
                            case 1:
                                templist.put("Relationship", CertificateConstants.SON_OF);
                                break;
                            case 2:
                                templist.put("Relationship", CertificateConstants.GAURDIAN_OF);
                                break;
                            
                            case 3:
                                templist.put("Relationship", CertificateConstants.HUSBAND_OF);
                                break;
                            default:
                                break;
                        }

                    } else {
                        Integer maritalstatus = Integer.parseInt(rs.getString("Marital_Status"));

                        if (maritalstatus.equals(1)) {
                            templist.put("Marital_Status", "Smt");
                        } else if (maritalstatus.equals(2)) {
                            templist.put("Marital_Status", "Kumari");
                        } else if (rs.getString("Marital_Status").equals("3")
                                || rs.getString("Marital_Status").equals("4")
                                || rs.getString("Marital_Status").equals("5")) {
                            templist.put("Marital_Status", "Ms");
                        }

                        int relationship = Integer.parseInt(rs.getString("Relationship"));

                        switch (relationship) {
	                        case 0: templist.put("Relationship", CertificateConstants.CARE_OF);
	                               break;
                            case 1:
                                templist.put("Relationship", CertificateConstants.DAUGHTER_OF);
                                break;
                            case 2:
                                templist.put("Relationship", CertificateConstants.GAURDIAN_OF);
                                break;
                            case 3:
                                templist.put("Relationship", CertificateConstants.WIFE_OF);
                                break;
                             default:
                                break;
                        }
                    }


                    if (rs.getString("house_number") != null && rs.getString("house_number").length() > 0) {
                        templist.put("house_number", rs.getString("house_number") + ",");
                    } else {
                        templist.put("house_number", "");
                    }
                    templist.put("habitation_name", rs.getString("habitation_name"));
                    templist.put("village_name", rs.getString("village_name"));
                    templist.put("mandal_name", rs.getString("mandal_name"));
                    templist.put("district_name", rs.getString("district_name"));
                    templist.put("Created_Date", rs.getString("Created_Date"));
                    templist.put("campdate", campdate);
                    templist.put("campname", campname.trim());
                    templist.put("ackno", rs.getString("ackno"));
                    templist.put("tokenNo", tokenNo);
                    templist.put("currentdate", rs.getString("currentdate"));
                    templist.put("dpmname", dpmname);
                    templist.put("contactno", contactno);
                    templist.put("disabilitytype", disabilitytype);

                    getPartaacknowledgementDetails.add(templist);
                }
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPartaacknowledgementDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getPartaacknowledgementDetails");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPartaacknowledgementDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getPartaacknowledgementDetails");
        } finally {
        	DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(ps);
            DBConnection.closeConnection(con);
        }
        return getPartaacknowledgementDetails;
    }

    public String checkEligibility(DataSource ds, String SadaremId) throws SADAREMDBException, SQLException {
        String result = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;
        Statement st = null;
        try {
            con = DBConnection.getConnection();
            String query = "  select *  from  tblperson_personal_details a"
                    + "  where person_code=? AND  status='Active' and view_edit_mode='Edit' ";

            ps = con.prepareStatement(query);
            ps.setString(1, SadaremId);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    result = "1";
                }
            }
            sql = " select *  from  dbo.AppellateAuthorityandTemporary_RegistrationDetails a where person_code=? AND "
                    + " vieweditmode='Edit' and categoryid=2";
            ps = con.prepareStatement(sql);
            ps.setString(1, SadaremId);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    result = "2";
                }
            }
            sql = " select *  from  dbo.AppellateAuthorityandTemporary_RegistrationDetails a where person_code=? AND "
                    + " vieweditmode='Edit' and categoryid=3";
            ps = con.prepareStatement(sql);
            ps.setString(1, SadaremId);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    result = "3";
                }
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkEligibility", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkEligibility");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkEligibility", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkEligibility");
        } finally {
        	DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(ps);
            DBConnection.closeConnection(con);
        }
        return result;
    }

    public ArrayList checkAlreadyRegistered(DataSource ds, String SadaremId) throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;
        Statement st = null;
        Map templist = null;
        ArrayList getCampDetails = new ArrayList();
        try {
            con = DBConnection.getConnection();
            String query = " select convert(varchar,campdate,103),c.name,c.address from  Disabilitycampdatedetails a,tblcamp_Details c where personcode=? and a.campid=c.camp_id"
                    + " and (DATEADD(DD,0,DATEDIFF(DD,0,campdate))) >=  (DATEADD(DD,0,DATEDIFF(DD,0,getdate())))";
            
            ps = con.prepareStatement(query);
            ps.setString(1, SadaremId);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    templist = new HashMap();
                    templist.put("campname", rs.getString(1));
                    templist.put("campdate", rs.getString(2));
                    templist.put("campaddress", rs.getString(3));
                    getCampDetails.add(templist);
                }
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkAlreadyRegistered", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkAlreadyRegistered");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkAlreadyRegistered", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkAlreadyRegistered");
        }
        finally 
        {
        	DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(ps);
            DBConnection.closeConnection(con);       
        }
        return getCampDetails;
    }

    /**
     *
     * @param partadto
     * @param ds
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public synchronized int insertCampScheduleDetails(PartADTO partadto, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        CallableStatement calstmt = null;
        Connection con = null;
        ;
        String sql = null;
        Statement st = null;
        Statement st1 = null;
        PreparedStatement ps = null;
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        // Created by mohan on 07/02/2012
        HttpSession session = request.getSession();
//        String reasonForStatus = null;
//        String formdate = null;
//        String dobdate = null;
        String campdate = null;
//        int partAEnteredStatus = 0;
        int statusCount = 0;
        int success = 0;
        try {

            con = DBConnection.getConnection();
            
            String personcodemax = partadto.getPersoncode();
            con.setAutoCommit(false);
            int count = 0;
            
            Integer applicationNumber = 0;
            String query = "select count(*) from Disabilitycampdatedetails where districtid=?";
            ps = con.prepareStatement(query);
            ps.setString(1, partadto.getDistrict_id());
            rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                count = rs.getInt(1);
            }
            if (count > 0) {
                String query1 = "select max(ackno) from Disabilitycampdatedetails where districtid = ?";
                ps = con.prepareStatement(query1);
                ps.setString(1, partadto.getDistrict_id());
                rs = ps.executeQuery(); 
                if (rs != null && rs.next()) {
                    rs.getString(1);
                    if (rs.getString(1) != null) {
                        applicationNumber = Integer.parseInt(rs.getString(1)) + 1;
                    } else {
                        applicationNumber = Integer.parseInt(partadto.getDistrict_id() + "00001");
                    }
                }
            } else {
                applicationNumber = Integer.parseInt(partadto.getDistrict_id() + "00001");
            }
            if (partadto.getPartaCampId() != null && !partadto.getPartaCampId().equals("0")) {
                if (partadto.getPartaCampDate() != null && !partadto.getPartaCampDate().equalsIgnoreCase("0")) {
                    Date partcampdate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getPartaCampDate());
                    campdate = new SimpleDateFormat("mm/dd/yyyy").format(partcampdate);
                }
                String querys = "insert into Disabilitycampdatedetails (Personcode,Districtid,Disabilityid,CampId,"
                        + "Campdate,ackno,Createddate,Updateddate,Loginid,Sysytemip)"
                        + "values(?,?,?,?,"
                        + "?,?,getDate(),getDate(),?,?)";
                ps = con.prepareStatement(querys);
                ps.setString(1, personcodemax);
                ps.setString(2, partadto.getDistrict_id());
                ps.setString(3, partadto.getType_disability());
                ps.setString(4, partadto.getPartaCampId());
                ps.setString(5, campdate);
                ps.setInt(6, applicationNumber);
                ps.setString(7, partadto.getLoginid());
                ps.setString(8, partadto.getSystemip());
                int i = ps.executeUpdate();
                if (i > 0) {
                    success = 1;
                    String formdates = new SimpleDateFormat("MM/dd/yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(partadto.getPartaCampDate()));
                    calstmt = con.prepareCall("{Call Sp_Campcount_verification(?,?,?,?)}");
                    calstmt.setString(1, partadto.getDistrict_id());
                    calstmt.setString(2, partadto.getType_disability());
                    calstmt.setString(3, partadto.getPartaCampId());
                    calstmt.setString(4, formdates);
                    rs = calstmt.executeQuery();
                    if (rs != null && rs.next()) {
                        statusCount = rs.getInt(1);

                    }
                }

                if (statusCount == 1) {
                    success = 0;
                    query = "delete from Disabilitycampdatedetails where personcode=? ";
                    ps = con.prepareStatement(query);
                    ps.setString(1, personcodemax);
                    ps.executeUpdate();
                    request.setAttribute("dvalues", "Select Disabilities Exceeded for Selete Camp");

                }
            } else {
                int status = 0;
                if (partadto.getNoofrowvalue() > 0) {
                    for (int i = 0; i < partadto.getNoofrowvalue(); i++) {
                        boolean flag = false;
                        for (int j = 1; j < partadto.getNoofrowvalue(); j++) {
                            if (partadto.getDynaProperty(i + "#" + (j)) != null
                                    && !partadto.getDynaProperty(i + "#" + (j)).toString().equals("")
                                    && !partadto.getDynaProperty(i + "#" + (j)).toString().equals("00")
                                    && partadto.getDynaProperty(i + "#" + (j)).toString().length() > 0
                                    && !partadto.getDynaProperty(i + "#" + (j)).toString().equalsIgnoreCase("0")) {
                                flag = true;
                            }
                        }
                        if (flag) {
                            String campdates = new SimpleDateFormat("MM/dd/yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(partadto.getDynaProperty(i + "#3").toString()));

                            String querys = "insert into Disabilitycampdatedetails (Personcode,Districtid,Disabilityid,CampId,"
                                    + "Campdate,ackno,Createddate,Updateddate,Loginid,Sysytemip)"
                                    + "values(?,?,?,?,?,?,getDate(),getDate(),?,?)";
                            ps = con.prepareStatement(querys);
                            ps.setString(1, personcodemax);
                            ps.setString(2, partadto.getDistrict_id());
                            if (partadto.getDynaProperty(i + "#2").toString() != null) {
                                ps.setString(3, partadto.getDynaProperty(i + "#2").toString());
                            } else {
                                ps.setString(3, null);
                            }
                            if (partadto.getDynaProperty(i + "#1").toString() != null) {
                                ps.setString(4, partadto.getDynaProperty(i + "#1").toString());
                            } else {
                                ps.setString(4, null);
                            }
                            if (partadto.getDynaProperty(i + "#3").toString() != null) {
                                ps.setString(5, campdates);
                            } else {
                                ps.setString(5, null);
                            }
                            ps.setInt(6, applicationNumber);
                            ps.setString(7, partadto.getLoginid());
                            ps.setString(8, partadto.getSystemip());
                            status = ps.executeUpdate();

                        }

                        if (status > 0) {
                            success = 1;
                            Date partcampdate1 = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getDynaProperty(i + "#3").toString());
                            String campdate1 = new SimpleDateFormat("mm/dd/yyyy").format(partcampdate1);
                            calstmt = con.prepareCall("{Call Sp_Campcount_verification(?,?,?,?)}");
                            calstmt.setString(1, partadto.getDistrict_id());
                            calstmt.setString(2, partadto.getDynaProperty(i + "#2").toString());
                            calstmt.setString(3, partadto.getDynaProperty(i + "#1").toString());
                            calstmt.setString(4, campdate1);
                            rs = calstmt.executeQuery();
                            if (rs != null && rs.next()) {
                                statusCount = rs.getInt(1);
                            }
                            if (statusCount == 1) {
                                success = 0;
                                query = "delete from Disabilitycampdatedetails where personcode=?";
                                ps  = con.prepareStatement(query);
                                ps.setString(1, personcodemax);
                                		
                                
                                ps.executeUpdate();
                                request.setAttribute("dvalues", "Select Disabilities Exceeded To Selete Camp");
                            }
                        }
                    }
                }
            }

            con.commit();
            con.setAutoCommit(true);


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            //transactionService.updateTransactionDetails(ds, "Error PartA Details", personcodemax);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertCampScheduleDetails", "PartADAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertCampScheduleDetails");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            //transactionService.updateTransactionDetails(ds, "Error PartA Details", personcodemax);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertCampScheduleDetails", "PartADAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertPersonalinsertCampScheduleDetailsDetails");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (calstmt != null) {
                    calstmt.close();
                }
                if (st != null) {
                    st.close();
                }
                if (st1 != null) {
                    st1.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public String paddingString(String s, int n, char c) {
        StringBuffer str = new StringBuffer(s);
        int strLength = str.length();
        if (n > 0 && n > strLength) {
            for (int i = 0; i <= n; i++) {

                if (i < n - strLength) {
                    str.insert(0, c);
                }

            }
        }
        return str.toString();
    }

    public ArrayList getDpmuDetails(DataSource ds, String districtId, String campId, String SadaremId) throws SADAREMDBException, SQLException {
        ArrayList getPartaacknowledgementDetails = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        String sql = null;
        Statement st = null;
        try {
            con = DBConnection.getConnection();
            
            if (campId == null) {
                sql = "select disabilityid,campid,districtid,convert(varchar,campdate,101)campdate"
                        + " from  dbo.Disabilitycampdatedetails where personcode=?  order by rowid desc";
                ps = con.prepareStatement(sql);
                ps.setString(1, SadaremId);
                rs = ps.executeQuery();
                if (rs != null) {
                    while (rs.next()) {

                        campId = rs.getString(2);
                    }
                }
            }

            sql = "select b.name,b.contactno,convert(varchar,getDate(),103)currentdate from tblDistrict_Details a "
                    + " join contactus b on(a.district_name=b.district)"
                    + " where a.district_id=? and b.type='Campincharge'  and b.campid=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, districtId);
            ps.setString(2, campId);
            rs1 = ps.executeQuery();
            if (rs1 != null) {
                while (rs1.next()) {
                    getPartaacknowledgementDetails.add(rs1.getString(1));
                    getPartaacknowledgementDetails.add(rs1.getString(2));
                    getPartaacknowledgementDetails.add(rs1.getString(3));
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDpmuDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getDpmuDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDpmuDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getDpmuDetails");
        } 
        finally 
        {
        	DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(ps);
            DBConnection.closeConnection(con);       
        }
        return getPartaacknowledgementDetails;
    }

    public void insertSmsLogDetails(DataSource ds, PartADTO partADTO, String message, String smsStatus) throws SADAREMDBException, Exception {
        Connection con = null;
        Statement st = null;
        String query = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            
            query = "insert into SmsLogs(MessageDescription,Messageto,LoginId,Systemip,createddate,updateddate,status) values(?,"
                    + "?,?,?,getDate(),getDate(),"
                    + "?) ";
            ps = con.prepareStatement(query);
            ps.setString(1, message);
            ps.setString(2, partADTO.getPhoneno());
            ps.setString(3, partADTO.getLoginid());
            ps.setString(4, partADTO.getSystemip());
            ps.setString(5, smsStatus);
            ps.executeUpdate();
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertSmsLogDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertSmsLogDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertSmsLogDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertSmsLogDetails");
        } 
        finally 
        {
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(ps);
            DBConnection.closeConnection(con);       
        }
    }

    public ArrayList checkSADAREMAssessmentData(DataSource ds, String aadharCardNo) throws SADAREMDBException, Exception {
        Connection con = null;
        Statement st = null;
        String query = null;
        PreparedStatement ps = null;
        ArrayList SADAREMID = new ArrayList();
        ResultSet rs = null;
        Map templist = null;
        int i = 0;
        try {
            con = DBConnection.getConnection();
            
            query = "select person_code,district_name from tblperson_personal_details p  with (nolock) ,tbldistrict_details d"
                    + "  where  proofdoc_type in ('Adhaar Card','Aadhaar Card','Aadhaar ID','Aadhar Card')  and  proof_id=? and p.district_id=d.district_id "
                    + " and ReasonforPersonStatus not in (?)";
            ps = con.prepareStatement(query);
            ps.setString(1,aadharCardNo);
            ps.setString(2,CommonConstants.ADDRESSCHANGE );
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    i = 1;
                    templist = new HashMap();
                    templist.put("sadaremid", rs.getString(1));
                    templist.put("districtname", rs.getString(2));
                    SADAREMID.add(templist);
                }
            }
            if (i == 0) {
                query = "select person_code,district_name from tblperson_personal_details_new p  with (nolock) ,tbldistrict_details d"
                        + "  where  proofdoc_type in ('Aadhaar Card','Aadhaar ID','Aadhar Card','Adhaar Card') and  proof_id=? and p.district_id=d.district_id";
                ps = con.prepareStatement(query);
                ps.setString(1,aadharCardNo);
                rs = ps.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        templist = new HashMap();
                        templist.put("sadaremid", rs.getString(1));
                        templist.put("districtname", rs.getString(2));
                        SADAREMID.add(templist);
                    }
                }
            }
        } catch (SQLException sqlEx) {System.out.println(sqlEx);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkSADAREMAssessmentData", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkSADAREMAssessmentData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkSADAREMAssessmentData", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkSADAREMAssessmentData");
        } 
        finally 
        {
        	DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(ps);
            DBConnection.closeConnection(con);       
        }
        return SADAREMID;
    }

    public ArrayList checkPensionData(DataSource ds, String aadharCardNo) throws SADAREMDBException, Exception {
        Connection con = null;
        Statement st = null;
        String query = null;
        PreparedStatement ps = null;
        ArrayList pensionId = new ArrayList();
        ResultSet rs = null;
        Map templist = null;
        try {
            con = DBConnection.getConnection();
            
            query = "select pensionid,district_name from Disabled_Pension p,tbldistrict_details d where Aadharcardno=? and p.distcode=d.district_id";
            ps = con.prepareStatement(query);
            ps.setString(1, aadharCardNo);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    templist = new HashMap();
                    templist.put("pensionid", rs.getString(1));
                    templist.put("districtname", rs.getString(2));
                    pensionId.add(templist);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPensionData", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkPensionData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPensionData", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkPensionData");
        } 
        finally 
        {
        	DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(ps);
            DBConnection.closeConnection(con);       
        }
        return pensionId;
    }

    public ArrayList getSADAREMAssessedData(DataSource ds, PartADTO partADTO) throws SQLException, SADAREMDBException {
        ArrayList proofsList = new ArrayList();
        ResultSet rs = null;
        HashMap map = null;
        CallableStatement cs = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();


            cs = con.prepareCall("{Call SP_SADAREMDATA_AADHARCARDNO(?,?,?,?,?)}");
            cs.setString(1, partADTO.getDistrict_id());
            cs.setString(2, partADTO.getMandal_id());
            cs.setString(3, partADTO.getVillage_id());
            cs.setString(4, partADTO.getHabitation_id());
            cs.setString(5, partADTO.getSurname() + " " + partADTO.getFirstname());

            rs = cs.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();

                    map.put("PensionID", rs.getString(9));
                    map.put("SADAREMID", rs.getString(8));

                    if (rs.getString(1) != null) {
                        map.put("Name", rs.getString(1));
                    } else {
                        map.put("Name", "-");
                    }
                    map.put("RelationName", rs.getString(7));
                    map.put("Age", rs.getString(2));
                    map.put("Qualification", rs.getString(3));
                    map.put("DisabiltyType", rs.getString(4));
                    map.put("DisabilityPercentage", rs.getString(5));
                    map.put("ContactNumber", rs.getString(6));
                    map.put("PWDStatus", rs.getString(10));
                    map.put("AssessmentStatus", rs.getString(11));
                    map.put("PensionPhase", rs.getString(12));


                    proofsList.add(map);

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSADAREMAssessedData", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getSADAREMAssessedData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSADAREMAssessedData", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getSADAREMAssessedData");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cs);

        }
        return proofsList;
    }

    public synchronized int taggedAadharCardNo(DataSource ds, PartAForm partAForm) throws SADAREMDBException, SQLException {
        int insert = 0;
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBConnection.getConnection();

            if (partAForm.getTagSadarem() != null && partAForm.getTagSadarem().length > 0) {
                String[] sArray = partAForm.getTagSadarem();
                String[] sar = sArray[0].split("!");
                String sadaremId = sar[0];
                String aadharCardNo = sar[1];
                pstmt = con.prepareStatement("Update tblperson_personal_details set  proofdoc_type='Adhaar Card' ,  proof_id=? where person_code=?");
                pstmt.setString(1, aadharCardNo);
                pstmt.setString(2, sadaremId);
                insert = pstmt.executeUpdate();

            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "taggedAadharCardNo", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "taggedAadharCardNo");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "taggedAadharCardNo", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "taggedAadharCardNo");
        } 
        finally 
        {
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);       
        }
        return insert;
    }

    /**
     *
     * @param partadto
     * @param ds
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public synchronized String insertPersonalDetailsForAadharNumber(PartADTO partadto, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        Statement st = null;
        ResultSet rs = null;
        Connection con = null;
        String personcodemax = null;
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        String districtid = partadto.getDistrict();
        String mandalid = partadto.getMandal();
        String villageid = partadto.getTownVillage();
        String habitationid = partadto.getHabitation();
        String panchayatid = partadto.getPanchayatiid();
        String assemblyid = partadto.getAssemblyid();
        HttpSession session = request.getSession();
        String reasonForStatus = null;
        int partAEnteredStatus = 0;
        String habCode = null;
        String campdate = null;
        PreparedStatement ps = null;
        boolean success = true;
        Statement st1 = null;
        try {


            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getFromdate());
            String formdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date ddate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getDobday());
            String dobdate = new SimpleDateFormat("mm/dd/yyyy").format(ddate);

            session.removeAttribute("categoryIdAu");
            session.removeAttribute("categoryCountAu");
            if (session.getAttribute("campId") != null) 
            {
                int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));

                /** Check Habcode base on selecting territorydetails */
                habCode = this.checkHabitation(ds, districtid, mandalid, panchayatid, assemblyid, villageid, habitationid);

                if (habCode != null) 
                {
                    personcodemax = territoryservice.getHabitationCodeRationCard(districtid, mandalid, panchayatid, assemblyid, villageid, habitationid, ds);
                    con = DBConnection.getConnection();
                    con.setAutoCommit(false);
                    
                    /*CommonDAOImpl comObj = new CommonDAOImpl();
                    HashMap GEODtls = new HashMap();
          			GEODtls=comObj.getGEODetailsbySADAREMID(personcodemax);
          			String distIdFromSadaremId	=	GEODtls.get("districtid").toString();
          			String mandIdFromSadaremId	=	GEODtls.get("mandalid").toString();
          			String vilIdFromSadaremId	=	GEODtls.get("villageid").toString();
          			String habcodeFromSadaremId	=	GEODtls.get("HabCode").toString();*/
                    
                    if (habCode.equals(personcodemax.substring(0, 14)) || (districtid+mandalid+villageid).equals(personcodemax.substring(0,7)))
                    {

                        calstmt = con.prepareCall("{Call SP_tblPerson_Personal_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

                        calstmt.setString(1, personcodemax);
                        calstmt.setString(2, partadto.getFormno());
                        calstmt.setString(3, formdate);
                        calstmt.setString(4, convertFirstLetterToUpperCase(partadto.getSurname()));
                        calstmt.setString(5, partadto.getFirstname());
                        calstmt.setString(6, partadto.getGender());
                        calstmt.setString(7, dobdate);
                        if (partadto.getNoOfYears() != null && partadto.getNoOfYears().length() > 0
                                && !partadto.getNoOfYears().equals(null))
                        {
                            calstmt.setString(8, partadto.getNoOfYears());
                        }
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
                        calstmt.setBoolean(20, true);
                        calstmt.setString(21, "Disabled");
                        calstmt.setString(22, "ADPIII");
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
                        calstmt.setString(42, "PhaseIII");
                        calstmt.setString(43, "Live");
                        calstmt.setString(44, habCode);
                        calstmt.setInt(45, 1);
                        calstmt.setInt(46, 1);
                        calstmt.setString(47, partadto.getRationCardSlno());
                        calstmt.setString(48, partadto.getAadharCardNo());

                        partAEnteredStatus = calstmt.executeUpdate();
                        con.commit();
                        con.setAutoCommit(true);
                        if (partAEnteredStatus != 0) 
                        {
                            transactionService.insertTransactionalDetails(ds, "PartA Details Entered", personcodemax, request);

                        }

                        session.removeAttribute("personStatusId");
                        session.removeAttribute("pensionIdDis");

                    } 
                    else 
                    {
                        personcodemax = "Not Match";
                    }
                } 
                else
                {
                    personcodemax = "Not Match";
                }
            }
        } 
        catch (Exception exception) 
        {
            // exception.printStackTrace();
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error PartA Details", personcodemax);
            // End
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "insertPersonalDetailsForAadharNumber", "PartADAO", "DataBase");
           con.rollback();

            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertPersonalDetailsForAadharNumber");

        }
        finally
        {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(st1);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(ps);
            DBConnection.closeStatement(calstmt);


        }
        return personcodemax;
    }

    public int checkPersonalDetailsForAadharNumber(PartADTO partadto, DataSource ds, HttpServletRequest request, String personcodemax) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        //Statement st = null;
        ResultSet rs = null;
        Connection con = null;
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();

        String campdate = null;
        PreparedStatement ps = null;
        boolean success = true;
        //Statement st1 = null;
        int result = 1;
        try {
            int statusCount = 0;
            int count = 0;
            con = DBConnection.getConnection();
            
            String applicationNumber = "";
            String query = "select count(*) from Disabilitycampdatedetails where districtid=?";
            ps = con.prepareStatement(query);
            ps.setString(1, partadto.getDistrict_id());
            rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                count = rs.getInt(1);
            }
            if (count > 0) {
//partadto.setDistrict_id("07");
                String query1 = "select max(ackno) from Disabilitycampdatedetails where districtid = ?";
                ps = con.prepareStatement(query1);
                ps.setString(1, partadto.getDistrict_id());
                rs = ps.executeQuery();
                if (rs != null && rs.next()) {
                    rs.getString(1);
                    if (rs.getString(1) != null) {
                        long longCode = Long.parseLong(rs.getString(1));
                        if (rs.getString(1).startsWith("0")) {
                            longCode++;

                            String withZero = String.valueOf(longCode);

                            applicationNumber = paddingString(withZero, withZero.length() + 1, '0');

                        } else {
                            longCode++;
                            applicationNumber = String.valueOf(longCode);
                        }

                    } else {
                        applicationNumber = partadto.getDistrict_id() + "00001";
                    }
                }
            } else {
                applicationNumber = partadto.getDistrict_id() + "00001";
            }

            if (partadto.getPartaCampId() != null && !partadto.getPartaCampId().equals("0")) {
                if (partadto.getPartaCampDate() != null && !partadto.getPartaCampDate().equalsIgnoreCase("0")) {
                    Date partcampdate = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getPartaCampDate());
                    campdate = new SimpleDateFormat("mm/dd/yyyy").format(partcampdate);
                }
                String querys = "insert into Disabilitycampdatedetails (Personcode,Districtid,Disabilityid,CampId,"
                        + "Campdate,ackno,Createddate,Updateddate,Loginid,Sysytemip)"
                        + "values(?,?,?,?,"
                        + "?,?,getDate(),getDate(),?,?)";
                ps = con.prepareStatement(querys);
                ps.setString(1, personcodemax);
                ps.setString(2, partadto.getDistrict_id());
                ps.setString(3, partadto.getType_disability());
                ps.setString(4, partadto.getPartaCampId());
                ps.setString(5,campdate);
                ps.setString(6, applicationNumber);
                ps.setString(7, partadto.getLoginid());
                ps.setString(8, partadto.getSystemip());
                int i = ps.executeUpdate();
                if (i > 0) {
                    String formdates = new SimpleDateFormat("MM/dd/yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(partadto.getPartaCampDate()));
                    calstmt = con.prepareCall("{Call Sp_Campcount_verification(?,?,?,?)}");
                    calstmt.setString(1, partadto.getDistrict_id());
                    calstmt.setString(2, partadto.getType_disability());
                    calstmt.setString(3, partadto.getPartaCampId());
                    calstmt.setString(4, formdates);
                    rs = calstmt.executeQuery();
                    if (rs != null && rs.next()) {
                        statusCount = rs.getInt(1);
                    }
                }
                if (statusCount == 1) {
                    query = "delete from dbo.tblPerson_Personal_Details_new where person_code=? ";
                    ps = con.prepareStatement(query);
                    ps.setString(1, personcodemax);                    
                    ps.executeUpdate();
                    query = "delete from Disabilitycampdatedetails where personcode=? ";
                    ps = con.prepareStatement(query);
                    ps.setString(1, personcodemax);                    
                    ps.executeUpdate();
                    request.setAttribute("dvalues", "Select Disabilities Exceeded To Selete Camp");

                }
            } else {
                int status = 0;
                if (partadto.getNoofrowvalue() > 0) {
                    for (int i = 0; i < partadto.getNoofrowvalue(); i++) {

                        if (true) {
                            String campdates = new SimpleDateFormat("MM/dd/yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(partadto.getDynaProperty(i + "#3").toString()));

                            String querys = "insert into Disabilitycampdatedetails (Personcode,Districtid,Disabilityid,CampId,"
                                    + "Campdate,ackno,Createddate,Updateddate,Loginid,Sysytemip)"
                                    + "values(?,?,?,?,?,?,getDate(),getDate(),?,?)";
                            ps = con.prepareStatement(querys);
                            ps.setString(1, personcodemax);
                            ps.setString(2, partadto.getDistrict_id());
                            if (partadto.getDynaProperty(i + "#2").toString() != null) {
                                ps.setString(3, partadto.getDynaProperty(i + "#2").toString());
                            } else {
                                ps.setString(3, null);
                            }
                            if (partadto.getDynaProperty(i + "#1").toString() != null) {
                                ps.setString(4, partadto.getDynaProperty(i + "#1").toString());
                            } else {
                                ps.setString(4, null);
                            }
                            if (partadto.getDynaProperty(i + "#3").toString() != null) {
                                ps.setString(5, campdates);
                            } else {
                                ps.setString(5, null);
                            }
                            ps.setString(6, applicationNumber);
                            ps.setString(7, partadto.getLoginid());
                            ps.setString(8, partadto.getSystemip());
                            status = ps.executeUpdate();

                        }


                        if (status > 0) {
                            Date partcampdate1 = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getDynaProperty(i + "#3").toString());
                            String campdate1 = new SimpleDateFormat("mm/dd/yyyy").format(partcampdate1);
                            calstmt = con.prepareCall("{Call Sp_Campcount_verification(?,?,?,?)}");
                            calstmt.setString(1, partadto.getDistrict_id());
                            calstmt.setString(2, partadto.getDynaProperty(i + "#2").toString());
                            calstmt.setString(3, partadto.getDynaProperty(i + "#1").toString());
                            calstmt.setString(4, campdate1);
                            rs = calstmt.executeQuery();
                            if (rs != null && rs.next()) {
                                statusCount = rs.getInt(1);
                            }

                            if (statusCount == 1) {
                                success = false;
                            }
                        }
                    }
                    if (success == false) {
                        query = "delete from dbo.tblPerson_Personal_Details_new where person_code=? ";
                        ps = con.prepareStatement(query);
                        ps.setString(1, personcodemax);
                        ps.executeUpdate();
                        for (int i = 0; i < partadto.getNoofrowvalue(); i++) {
                            Date partcampdate1 = new SimpleDateFormat("dd/mm/yyyy").parse(partadto.getDynaProperty(i + "#3").toString());
                            String campdate1 = new SimpleDateFormat("mm/dd/yyyy").format(partcampdate1);

                            query = "delete from Disabilitycampdatedetails where personcode=?"
                                    + " and disabilityid=? and campdate=?";
                            ps = con.prepareStatement(query); 
                            ps.setString(1, personcodemax);
                            ps.setString(2, partadto.getDynaProperty(i + "#2").toString());
                            ps.setString(3, campdate1);
                            ps.executeUpdate();

                        }
                        request.setAttribute("dvalues", "Select Disabilities Exceeded To Selete Camp");
                    }
                }
            }

        } catch (Exception exception) {
            // exception.printStackTrace();
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error PartA Details", personcodemax);
            // End
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "checkPersonalDetailsForAadharNumber", "PartADAO", "DataBase");
            //DBConnection.rollbackConnection(con);

            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkPersonalDetailsForAadharNumber");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(ps);
            DBConnection.closeStatement(calstmt);


        }
        return result;
    }

    public static String checkAdharCardNumberWithPensionNo(DataSource ds, String pensionCode, String type, String district) throws SADAREMDBException, SQLException {
        Connection con = null;
        String query = null;
        String aadharNumber = "Invalid";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int i = 0;
        try {
            con = DBConnection.getConnection();
            if (type.equals("PensionCode")) {
                query = "select  distinct proof_id from dbo.tblPerson_Personal_Details a with (nolock) "
                        + " join Disabled_Pension b on(a.proof_id=b.aadharcardno) where b.pensionid=? and b.distcode=?";
            } else if (type.equals("RationCard")) {
                query = "select  distinct proof_id from dbo.tblPerson_Personal_Details a with (nolock) "
                        + " join Disabled_Pension b on(a.proof_id=b.aadharcardno) where b.rationcardno=?";
            } else if (type.equals("AdharCard")) {
                query = "select distinct proof_id from dbo.tblPerson_Personal_Details  with (nolock)  where proof_id=? and  proofdoc_type in ('Aadhaar Card','Aadhaar ID','Aadhar Card','Adhaar Card')";
            } else if (type.equals("AdharCardNumber")) {
                query = "select  person_code from dbo.tblPerson_Personal_Details  with (nolock)  where proof_id=?";
            }
            ps = con.prepareStatement(query);
            if (type.equals("PensionCode"))
            {
            	ps.setString(1, pensionCode);
            	ps.setString(2, district);
            } 
            else if (type.equals("RationCard")) 
            {
            	ps.setString(1, pensionCode);
            }
            else if (type.equals("AdharCard"))
            {
            	ps.setString(1, pensionCode);
            } 
            else if (type.equals("AdharCardNumber")) 
            {
            	ps.setString(1, pensionCode);
            }
            rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                i = 1;
                aadharNumber = rs.getString(1);
            }

            if (i == 0) {

                if (type.equals("PensionCode")) {
                    query = "select  distinct proof_id from dbo.tblPerson_Personal_Details_new a"
                            + " join Disabled_Pension b on(a.proof_id=b.aadharcardno) where b.pensionid=? and b.distcode=?";
                } else if (type.equals("RationCard")) {
                    query = "select  distinct proof_id from dbo.tblPerson_Personal_Details_new a"
                            + " join Disabled_Pension b on(a.proof_id=b.aadharcardno) where b.rationcardno=?";
                } else if (type.equals("AdharCard")) {
                    query = "select distinct proof_id from dbo.tblPerson_Personal_Details_new  where proof_id=? and  proofdoc_type in ('Aadhaar Card','Aadhaar ID','Aadhar Card','Adhaar Card')";
                } else if (type.equals("AdharCardNumber")) {
                    query = "select  person_code from dbo.tblPerson_Personal_Details_new  where proof_id=?";
                }
                ps = con.prepareStatement(query);
                if (type.equals("PensionCode"))
                {
                	ps.setString(1, pensionCode);
                	ps.setString(2, district);
                } 
                else if (type.equals("RationCard")) 
                {
                	ps.setString(1, pensionCode);
                }
                else if (type.equals("AdharCard"))
                {
                	ps.setString(1, pensionCode);
                } 
                else if (type.equals("AdharCardNumber")) 
                {
                	ps.setString(1, pensionCode);
                }
                rs = ps.executeQuery();
                if (rs != null && rs.next()) {
                    i = 1;
                    aadharNumber = rs.getString(1);
                } else {
                    aadharNumber = "Invalid";
                }
            }
        } catch (SQLException sqlEx) {System.out.println(sqlEx);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkAdharCardNumberWithPensionNo", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkAdharCardNumberWithPensionNo");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkAdharCardNumberWithPensionNo", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkAdharCardNumberWithPensionNo");
        } 
        finally 
        {
        	DBConnection.closeResultSet(rs); 
            DBConnection.closeStatement(ps);
            DBConnection.closeConnection(con);       
        }
        return aadharNumber;
    }

    public ArrayList getAcknowledgementDetails(DataSource ds, String SadaremId) throws SADAREMDBException, SQLException {
        ArrayList getPartaacknowledgementDetails = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;
        Map templist = null;
        Statement st = null;
        String campname = "";
        String campdate = "";
        String disabilityId = null;
        String campId = null;
        String districtId = null;
        String tokencampdate = null;
        String tokenNo = null;
        String dpmname = null;
        String contactno = null;
        ArrayList list = new ArrayList();
        String disabilitytype = "";
        try {
            con = DBConnection.getConnection();
            //            String query = "  select b.name+','+b.address,convert(varchar,a.campdate,103)campdate  from  Disabilitycampdatedetails a,tblCamp_Details b"
//                    + "  where personcode='" + SadaremId + "' AND  a.campid = b.camp_id ANd b.camp_id = a.campid AND   (DATEADD(DD,0,DATEDIFF(DD,0,campdate))) >=  (DATEADD(DD,0,DATEDIFF(DD,0,getdate())))";
//
            
//            rs = st.executeQuery(query);
//            if (rs != null) {
//                while (rs.next()) {
//                    campname = rs.getString(1);
//                    campdate = rs.getString(2);
//                }
//            }
            sql = "select a.disabilityid,a.campid,a.districtid,convert(varchar,campdate,101)campdate,b.name+','+b.address "
                    + " from  dbo.Disabilitycampdatedetails a,tblCamp_Details b where  a.campid = b.camp_id ANd "
                    + "a.personcode=? order by rowid desc";
            ps = con.prepareStatement(sql);
            ps.setString(1, SadaremId);
            
            rs = ps.executeQuery();
            int i = 1;
            if (rs != null) {
                while (rs.next()) {
                    campname = rs.getString(5);
                    campdate = rs.getString(4);
                    list.add(campId = rs.getString(2));
                    if (i == 1) {
                        disabilityId = rs.getString(1);
                        campId = rs.getString(2);
                        i++;
                    }
                    if (list.size() == 2) {
                        campId = rs.getString(2);
                        districtId = rs.getString(3);
                        tokencampdate = rs.getString(4);
                        disabilitytype = "For Multiple Disability";
                    } else {

                        campId = rs.getString(2);
                        disabilityId = rs.getString(1);
                        districtId = rs.getString(3);
                        tokencampdate = rs.getString(4);
                        disabilitytype = "";
                    }

                }
            }
            if (disabilityId != null) {
                if (disabilityId.equals("1")) {
                    sql = "select ohcount,ohcountpending from dbo.CampDetailsDateWise where campid=? and districtid=? and campdate=?";
                } else if (disabilityId.equals("2")) {
                    sql = "select vicount,vicountpending from dbo.CampDetailsDateWise where campid=? and districtid=? and campdate=?";
                } else if (disabilityId.equals("3")) {
                    sql = "select hicount,hicountpending from dbo.CampDetailsDateWise where campid=? and districtid=? and campdate=?";
                } else if (disabilityId.equals("4")) {
                    sql = "select mrcount,mrcountpending from dbo.CampDetailsDateWise where campid=? and districtid=? and campdate=?";
                } else if (disabilityId.equals("5")) {
                    sql = "select micount,micountpending from dbo.CampDetailsDateWise where campid=? and districtid=? and campdate=?";
                }
            }
            ps = con.prepareStatement(sql);
            ps.setString(1, campId);
            ps.setString(2, districtId);
            ps.setString(3, tokencampdate);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    if (rs.getString(1) != null && rs.getString(2) != null) {
                        tokenNo = Integer.parseInt(rs.getString(1)) - Integer.parseInt(rs.getString(2)) + " / " + rs.getString(1);
                    } else {
                        tokenNo = "Not Generated";
                    }
                }
            }

            sql = "select b.name,b.contactno,convert(varchar,getDate(),103)currentdate from tblDistrict_Details a "
                    + " join contactus b on(a.district_name=b.district)"
                    + " where a.district_id=? and b.type='Campincharge' and b.campid=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, districtId);
            ps.setString(2, campId);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    dpmname = rs.getString(1);
                    contactno = rs.getString(2);
                }
            }
            if (dpmname == null) {

                dpmname = "NA";
                contactno = "";
            }


            sql = " select distinct person_code,surname +space(2)+first_name fullname,relation_name,P.Relationship,p.Marital_Status,P.Gender,p.house_number,x.habitation_name,v.village_name,m.mandal_name,n.district_name,convert(varchar,p.created_date,103) created_date"
                    + ",y.ackno,convert(varchar,getDate(),103)currentdate  from dbo.tblperson_personal_details_new P  with (nolock) ,tbldistrict_details n,TBLMANDAL_DETAILS M,TBLVILLAGE_DETAILS V,dbo.tblHabitation_Details x ,Disabilitycampdatedetails y"
                    + " where p.status='Active'  and Person_code=? and p.districtid = n.district_id and P.DISTRICTID = M.DISTRICT_ID AND P.MANDALID = M.MANDAL_ID ANd P.DISTRICTID = V.DISTRICT_ID AND P.MANDALID = V.MANDAL_ID AND P.VILLAGEID = V.VILLAGE_ID and p.habcode = x.habitation_code"
                    + "  and y.personcode=p.person_code";
            ps = con.prepareStatement(sql);
            ps.setString(1,SadaremId);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    templist = new HashMap();
                    templist.put("person_code", rs.getString("person_code"));
                    templist.put("fullname", rs.getString("fullname"));
                    templist.put("relation_name", rs.getString("relation_name"));


                    if (rs.getString("Gender").equals("1")) {


                        Integer maritalstatus = Integer.parseInt(rs.getString("Marital_Status"));

                        if (maritalstatus.equals(1)) {
                            templist.put("Marital_Status", "Shri");
                        }
                        int relationship = Integer.parseInt(rs.getString("Relationship"));
                        switch (relationship) {
                            case 1:
                            case 2:
                                templist.put("Relationship", CertificateConstants.SON_OF);
                                break;
                            case 3:
                                templist.put("Relationship", CertificateConstants.WIFE_OF);
                                break;
                            case 4:
                            case 5:
                            case 6:
                                templist.put("Relationship", CertificateConstants.GAURDIAN_OF);
                                break;
                            case 7:
                                templist.put("Relationship", CertificateConstants.HUSBAND_OF);
                                break;
                            default:
                                break;
                        }

                    } else {
                        Integer maritalstatus = Integer.parseInt(rs.getString("Marital_Status"));

                        if (maritalstatus.equals(1)) {
                            templist.put("Marital_Status", "Smt");
                        } else if (maritalstatus.equals(2)) {
                            templist.put("Marital_Status", "Kumari");
                        } else if (rs.getString("Marital_Status").equals("3")
                                || rs.getString("Marital_Status").equals("4")
                                || rs.getString("Marital_Status").equals("5")) {
                            templist.put("Marital_Status", "Ms");
                        }

                        int relationship = Integer.parseInt(rs.getString("Relationship"));

                        switch (relationship) {
                            case 1:
                            case 2:
                                templist.put("Relationship", CertificateConstants.DAUGHTER_OF);
                                break;
                            case 3:
                                templist.put("Relationship", CertificateConstants.WIFE_OF);
                                break;
                            case 4:
                            case 5:
                            case 6:
                                templist.put("Relationship", CertificateConstants.GAURDIAN_OF);
                                break;
                            case 7:
                                templist.put("Relationship", CertificateConstants.HUSBAND_OF);
                                break;
                            default:
                                break;
                        }
                    }


                    if (rs.getString("house_number") != null && rs.getString("house_number").length() > 0) {
                        templist.put("house_number", rs.getString("house_number") + ",");
                    } else {
                        templist.put("house_number", "");
                    }
                    templist.put("habitation_name", rs.getString("habitation_name"));
                    templist.put("village_name", rs.getString("village_name"));
                    templist.put("mandal_name", rs.getString("mandal_name"));
                    templist.put("district_name", rs.getString("district_name"));
                    templist.put("Created_Date", rs.getString("Created_Date"));
                    templist.put("campdate", campdate);
                    templist.put("campname", campname.trim());
                    templist.put("ackno", rs.getString("ackno"));
                    templist.put("tokenNo", tokenNo);
                    templist.put("currentdate", rs.getString("Created_Date"));
                    templist.put("dpmname", dpmname);
                    templist.put("contactno", contactno);
                    templist.put("disabilitytype", disabilitytype);

                    getPartaacknowledgementDetails.add(templist);
                }
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAcknowledgementDetails", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getAcknowledgementDetails");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAcknowledgementDetails", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "getAcknowledgementDetails");
        } 
        finally 
        {
        	DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(ps);
            DBConnection.closeConnection(con);       
        }
        return getPartaacknowledgementDetails;
    }

    public String checkSADARAMID(DataSource ds, String SadaremId, HttpServletRequest request) throws SADAREMDBException, SQLException {
        String result = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;
        HttpSession session = request.getSession(true);
        String districtid = (String) session.getAttribute("districtId");
        Statement st = null;
        try {
            con = DBConnection.getConnection();
            if (SadaremId != null && SadaremId != "") {
                String query = "  select count(*) from dbo.tblPerson_Personal_Details  with (nolock) where person_Code=? and status='Active' ";
                ps = con.prepareStatement(query);
                ps.setString(1, SadaremId);
                rs = ps.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        if (rs.getInt(1) > 0) {
                            result = "valid";
                        } else {
                            request.setAttribute("msg", "Invalid SADAREM ID!!!");
                        }
                    }
                }
//                } else {
//                    request.setAttribute("msg", "Invalid SADAREM ID!!!"); result = "valid";
//                }
            }
            if (result != null && result != "" && result.equalsIgnoreCase("valid")) {
                String query = "  select reasonforpersonstatus from dbo.tblPerson_Personal_Details  with (nolock) where person_Code=? and status='Active' ";
                ps = con.prepareStatement(query);
                ps.setString(1, SadaremId);
                rs = ps.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        result = rs.getString(1);
                        if (result.equalsIgnoreCase("Dead")) {
                            request.setAttribute("msg", "SADAREM ID is in Dead Status!!!");
                        }
                    }
                }
            }
            if (result != null && result != "" && !result.equalsIgnoreCase("Dead")) {
                String query = "  select * from Disabilitycampdatedetails where personcode=? ";
                ps = con.prepareStatement(query);
                ps.setString(1, SadaremId);
                
                rs = ps.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        result = "true";
                    }
                } else {
                    request.setAttribute("msg", "Given SADAREM ID has not registered for any Camp!!!");
                }
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkSADARAMID", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkEligibility");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkEligibility", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkEligibility");
        } 
        finally 
        {
        	DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(ps);
            DBConnection.closeConnection(con);       
        }
        return result;
    }

    public static int checkAdharCardNumberMapping(DataSource ds, String aadharNo, String personCode) throws SADAREMDBException, SQLException {
        Connection con = null;
        String query = null;
        String aadharNumber = "Invalid";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBConnection.getConnection();
            query = "select COUNT(*) from tblPerson_Personal_Details  with (nolock) where Proofdoc_Type in ('Aadhaar Card','Aadhaar ID','Aadhar Card','Adhaar Card') and proof_id=?";
            if (personCode != null && !personCode.equalsIgnoreCase("") && !personCode.equalsIgnoreCase("null")) {
                query = query + " and person_code not in (?)";
            }

            ps = con.prepareStatement(query);
            ps.setString(1, aadharNo);
            if (personCode != null && !personCode.equalsIgnoreCase("") && !personCode.equalsIgnoreCase("null"))
            {
            	ps.setString(2, personCode);
            }
            
            rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                count = rs.getInt(1);
            }

            if (count == 0) {
                query = "select COUNT(*) from tblPerson_Personal_Details_new  with (nolock) where Proofdoc_Type in ('Aadhaar Card','Aadhaar ID','Aadhar Card','Adhaar Card') and proof_id=?";
                if (personCode != null && !personCode.equalsIgnoreCase("") && !personCode.equalsIgnoreCase("null")) {
                    query = query + " and person_code not in (?)";
                }

                ps = con.prepareStatement(query);
                ps.setString(1, aadharNo);
                if (personCode != null && !personCode.equalsIgnoreCase("") && !personCode.equalsIgnoreCase("null"))
                {
                	ps.setString(2, personCode);
                }
                rs = ps.executeQuery();
                if (rs != null && rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkAdharCardNumberMapping", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkAdharCardNumberMapping");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkAdharCardNumberMapping", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkAdharCardNumberMapping");
        } 
        finally 
        {
        	DBConnection.closeResultSet(rs); 
            DBConnection.closeStatement(ps);
            DBConnection.closeConnection(con);       
        }
        return count;
    }

    public String checkPensionAssessedData(DataSource ds, String pensionNo, String districtId) throws SADAREMDBException, Exception {
        Connection con = null;
        Statement st = null;
        String query = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String person_code = null;
        int i = 0;
        try {
            con = DBConnection.getConnection();
            
            query = "select person_code from tblperson_personal_details p  with (nolock) ,tbldistrict_details d"
                    + "  where   pensioncard_no=? and p.district_id=? and p.district_id=d.district_id";
            ps = con.prepareStatement(query);
            ps.setString(1, pensionNo);
            ps.setString(2, districtId);

            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    i = 1;
                    person_code = rs.getString(1);
                }
            }
            if (i == 0) {
                query = "select person_code from tblperson_personal_details_new p  with (nolock) ,tbldistrict_details d"
                        + "  where   pensioncard_no=? and p.district_id=? and p.district_id=d.district_id";
                ps = con.prepareStatement(query);
                ps.setString(1,pensionNo);
                ps.setString(2,districtId);
                rs = ps.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        person_code = rs.getString(1);

                    }
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPensionAssessedData", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkPensionAssessedData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPensionAssessedData", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkPensionAssessedData");
        } 
        finally 
        {
        	DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(ps);
            DBConnection.closeConnection(con);       
        }
        return person_code;
    }

    public String checkPersoncodeData(DataSource ds, String personcode) throws SADAREMDBException, Exception {
        Connection con = null;
        Statement st = null;
        String query = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String msg = null;
        int i = 0;
        try {
            con = DBConnection.getConnection();
            
            query = "select person_code from tblperson_personal_details p  with (nolock)"
                    + "  where   person_code=?  ";
            
            ps = con.prepareStatement(query);
            ps.setString(1, personcode);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    i = 1;
                    msg = "Success";
                }
            }
            if (i == 0) {
                query = "select person_code from tblperson_personal_details_new p  with (nolock) "
                        + "  where   person_code=? ";

                ps = con.prepareStatement(query);
                ps.setString(1, personcode);
                rs = ps.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        msg = "New";
                    }
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersoncodeData", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkPersoncodeData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersoncodeData", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkPersoncodeData");
        }
        finally 
        {
        	DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(ps);
            DBConnection.closeConnection(con);       
        }
        return msg;
    }

    public String checkPensioncardno(DataSource ds, String personcode) throws SADAREMDBException, Exception {
        Connection con = null;
        Statement st = null;
        String query = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String checkPensioncardno = null;
        int i = 0;
        try {
            con = DBConnection.getConnection();
            
            query = "select Pensioncard_no from TBLPERSON_PERSONAL_DETAILS_new(nolock) "
                    + " where Person_code=? and  status='Active' and"
                    + " view_edit_mode='Edit'";

            ps = con.prepareStatement(query);
            ps.setString(1, personcode);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    i = 1;
                    checkPensioncardno = rs.getString(1);


                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPensioncardno", "PartADAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkPensioncardno");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPensioncardno", "PartADAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "checkPensioncardno");
        } 
        finally 
        {
        	DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(ps);
            DBConnection.closeConnection(con);       
        }
        return checkPensioncardno;
    }
}
