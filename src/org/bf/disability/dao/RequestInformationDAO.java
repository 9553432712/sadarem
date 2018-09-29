/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.RequestInformationDTO;
import org.bf.disability.form.RequestInformationForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 693461
 */
public class RequestInformationDAO {

    public ArrayList getRequestTypeDetails(DataSource ds) throws SADAREMDBException, SQLException {

        ArrayList requestTypeList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        RequestInformationDTO requestInformationDTO = new RequestInformationDTO();

        try {
            con = DBConnection.getConnection();
            query = "select requestTypeid,requesttypename from tblrequesttypemaster where status ='Active'";
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    requestInformationDTO = new RequestInformationDTO();
                    requestInformationDTO.setRequestTypeId(rs.getString(1));
                    requestInformationDTO.setRequestTypeName(rs.getString(2));
                    requestTypeList.add(requestInformationDTO);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestTypeDetails", "RequestInformationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "getRequestTypeDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestTypeDetails", "RequestInformationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "getRequestTypeDetails");
        } finally {
        	DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con);
        }

        return requestTypeList;
    }

    public ArrayList getRequestDetails(DataSource ds) throws SADAREMDBException, SQLException {

        ArrayList requestTypeList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        RequestInformationDTO requestInformationDTO = new RequestInformationDTO();

        try {
            con = DBConnection.getConnection();
            query = "select requestTypeid,requesttypename from tblrequesttypemaster where requeststatus ='Y'";
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    requestInformationDTO = new RequestInformationDTO();
                    requestInformationDTO.setRequestTypeId(rs.getString(1));
                    requestInformationDTO.setRequestTypeName(rs.getString(2));
                    requestTypeList.add(requestInformationDTO);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestDetails", "RequestInformationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "getRequestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestDetails", "RequestInformationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "getRequestDetails");
        } finally {
        	DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con);
        }

        return requestTypeList;

    }

    public ArrayList getRequestInformationDetails(DataSource ds, String districtId, String requestType, String status) throws SADAREMDBException, SQLException {

        ArrayList requestInformationList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        RequestInformationDTO requestInformationDTO = null;
        String personCodeQuery = null;
        PreparedStatement personCodeStmt = null;
        ResultSet personCodeRs = null;
        ArrayList personCode = new ArrayList();
        String regDate = null;

        int a = 0;


        try {
            con = DBConnection.getConnection();
            personCodeQuery = "select distinct person_code from requestDetails";
            personCodeStmt = con.prepareStatement(personCodeQuery);
            personCodeRs = personCodeStmt.executeQuery();

            if (personCodeRs != null) {
                while (personCodeRs.next()) {
                    personCode.add(personCodeRs.getString(1));
                }
            }
            for (int i = 0; i < personCode.size(); i++) {
                query = "with a as (select person_code, districtid, mandalid, villageid, habcode "
                        + " from dbo.tblPerson_Personal_Details  with (nolock) where person_code=?)"
                        + " select r.requestid,c.requesttypeid,r.name,r.relationname,r.houseno, "
                        + " b.mandal_name,d.district_name,e.village_name,f.habitation_name,r.person_code,c.requesttypeid,r.created_Date  from RequestDetails r "
                        + " join RequestTypeMapping c on (c.requestid =r.requestid) join a "
                        + " on(R.person_code=a.person_code) join tblDistrict_Details d "
                        + " on(a.districtid =d.district_id) join tblmandal_Details b "
                        + " on (a.districtid =b.district_id) and (a.mandalid =b.mandal_id) join tblVillage_Details e "
                        + " on (a.districtid =e.district_id) and (a.mandalid =e.mandal_id) "
                        + " and (a.villageid =e.village_id) join tblHabitation_Details f "
                        + " on (a.habcode =f.Habitation_Code) where a.mandalid <80 and a.districtid =? "
                        + " and c.requesttypeid =? and c.requeststatus =?";



                pstmt = con.prepareStatement(query);
                pstmt.setString(1, personCode.get(i).toString() );
                pstmt.setString(2, districtId );
                pstmt.setString(3, requestType );
                pstmt.setString(4, status );
                rs = pstmt.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        requestInformationDTO = new RequestInformationDTO();
                        requestInformationDTO.setRequestId(rs.getString(1));
                        requestInformationDTO.setRequestTypeDetailsId(rs.getString(2));

                        if (rs.getString(3) != null && rs.getString(3).equals("")) {
                            requestInformationDTO.setName("---");
                        } else {
                            requestInformationDTO.setName(rs.getString(3));
                        }

                        if (rs.getString(4) != null && rs.getString(4).equals("")) {
                            requestInformationDTO.setRelationName("---");
                        } else {
                            requestInformationDTO.setRelationName(rs.getString(4));
                        }
                        requestInformationDTO.setHouseNO(rs.getString(5));
                        requestInformationDTO.setMandalName(rs.getString(6));
                        requestInformationDTO.setDistrictName(rs.getString(7));
                        requestInformationDTO.setVillageName(rs.getString(8));
                        requestInformationDTO.setHabitationName(rs.getString(9));
                        requestInformationDTO.setPersonCode(rs.getString(10));
                        requestInformationDTO.setRequestTypeIdDataFormat(rs.getString(11));

                        regDate = new SimpleDateFormat("dd/mm/yyyy").format(new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString(12)));
                        requestInformationDTO.setRegDate(regDate);
                        requestInformationList.add(requestInformationDTO);

                    }
                }
            }
            if (requestType.equals("6")) {


                query = "select r.requestid,c.requesttypeid,r.name,r.relationname,r.houseno,"
                        + " b.mandal_name,d.district_name,e.village_name,f.habitation_name,coalesce(r.person_code,'---'),c.requesttypeid,r.created_Date  from "
                        + " RequestDetails r  join RequestTypeMapping c on (c.requestid =r.requestid)"
                        + " join tblDistrict_Details d on(r.districtid =d.district_id) join tblmandal_Details b"
                        + " on (r.districtid =b.district_id and r.mandalid =b.mandal_id) "
                        + " join tblVillage_Details e  on (r.districtid =e.district_id "
                        + " and r.mandalid =e.mandal_id  and r.villageid =e.village_id)"
                        + " join tblHabitation_Details f  on ( r.districtid=f.district_id "
                        + " and r.mandalid=f.mandal_id "
                        + " and r.villageid=f.village_id  and r.habitationid=f.habitation_id) "
                        + " where r.mandalid <80 and r.districtid =? and c.requesttypeid =? "
                        + " and c.requeststatus =?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, districtId);
                pstmt.setString(2, requestType);
                pstmt.setString(3, status);
                rs = pstmt.executeQuery();


                if (rs != null) {
                    while (rs.next()) {
                        requestInformationDTO = new RequestInformationDTO();
                        requestInformationDTO.setRequestId(rs.getString(1));
                        requestInformationDTO.setRequestTypeDetailsId(rs.getString(2));
                        requestInformationDTO.setName(rs.getString(3));
                        requestInformationDTO.setRelationName(rs.getString(4));
                        requestInformationDTO.setHouseNO(rs.getString(5));
                        requestInformationDTO.setMandalName(rs.getString(6));
                        requestInformationDTO.setDistrictName(rs.getString(7));
                        requestInformationDTO.setVillageName(rs.getString(8));
                        requestInformationDTO.setHabitationName(rs.getString(9));
                        requestInformationDTO.setPersonCode(rs.getString(10));
                        requestInformationDTO.setRequestTypeIdDataFormat(rs.getString(11));

                        regDate = new SimpleDateFormat("dd/mm/yyyy").format(new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString(12)));
                        requestInformationDTO.setRegDate(regDate);
                        requestInformationList.add(requestInformationDTO);
                    }
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestInformationDetails", "RequestInformationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "getRequestInformationDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestInformationDetails", "RequestInformationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "getRequestInformationDetails");
        } finally {
        	DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con);
        }
        return requestInformationList;
    }

    public ArrayList getRequestUrbanIformationDetails(DataSource ds, String districtId, String requestType, String status) throws SADAREMDBException, SQLException {

        ArrayList requestInformationList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        RequestInformationDTO requestInformationDTO = null;
        String personCodeQuery = null;
        PreparedStatement personCodeStmt = null;
        ResultSet personCodeRs = null;
        ArrayList personCode = new ArrayList();

        int a = 0;


        try {
            con = DBConnection.getConnection();
            personCodeQuery = "select distinct person_code from requestDetails";

            personCodeStmt = con.prepareStatement(personCodeQuery);
            personCodeRs = personCodeStmt.executeQuery();

            if (personCodeRs != null) {
                while (personCodeRs.next()) {
                    personCode.add(personCodeRs.getString(1));
                }
            }
            for (int i = 0; i < personCode.size(); i++) {
                query = "with a as (select person_code, districtid, mandalid, villageid, habcode "
                        + " from dbo.tblPerson_Personal_Details  with (nolock) where person_code=?)"
                        + " select r.requestid,c.requesttypeid,r.name,r.relationname,r.houseno, "
                        + " b.mandal_name,d.district_name,e.village_name,f.habitation_name,r.person_code,c.requesttypeid from RequestDetails r "
                        + " join RequestTypeMapping c on (c.requestid =r.requestid) join a "
                        + " on(R.person_code=a.person_code) join tblDistrict_Details d "
                        + " on(a.districtid =d.district_id) join tblmandal_Details b "
                        + " on (a.districtid =b.district_id) and (a.mandalid =b.mandal_id) join tblVillage_Details e "
                        + " on (a.districtid =e.district_id) and (a.mandalid =e.mandal_id) "
                        + " and (a.villageid =e.village_id) join tblHabitation_Details f "
                        + " on (a.habcode =f.Habitation_Code) where a.mandalid >80 and a.districtid =? "
                        + " and c.requesttypeid =? and c.requeststatus =?";


                pstmt = con.prepareStatement(query);
                pstmt.setString(1, personCode.get(i).toString() );
                pstmt.setString(2, districtId );
                pstmt.setString(3, requestType );
                pstmt.setString(4, status );
                rs = pstmt.executeQuery();


                if (rs != null) {
                    while (rs.next()) {
                        requestInformationDTO = new RequestInformationDTO();
                        requestInformationDTO.setRequestId(rs.getString(1));
                        requestInformationDTO.setRequestTypeDetailsId(rs.getString(2));

                        if (rs.getString(3) != null && rs.getString(3).equals("")) {
                            requestInformationDTO.setName("---");
                        } else {
                            requestInformationDTO.setName(rs.getString(3));
                        }

                        if (rs.getString(4) != null && rs.getString(4).equals("")) {
                            requestInformationDTO.setRelationName("---");
                        } else {
                            requestInformationDTO.setRelationName(rs.getString(4));
                        }
                        requestInformationDTO.setRelationName(rs.getString(4));
                        requestInformationDTO.setHouseNO(rs.getString(5));
                        requestInformationDTO.setMandalName(rs.getString(6));
                        requestInformationDTO.setDistrictName(rs.getString(7));
                        requestInformationDTO.setVillageName(rs.getString(8));
                        requestInformationDTO.setHabitationName(rs.getString(9));
                        requestInformationDTO.setPersonCode(rs.getString(10));
                        requestInformationDTO.setRequestTypeIdDataFormat(rs.getString(11));
                        requestInformationList.add(requestInformationDTO);

                    }
                }
            }
            if (requestType.equals("6")) {
                query = "select r.requestid,c.requesttypeid,r.name,r.relationname,r.houseno,"
                        + " b.mandal_name,d.district_name,e.village_name,f.habitation_name,coalesce(r.person_code,'---'),c.requesttypeid from "
                        + " RequestDetails r  join RequestTypeMapping c on (c.requestid =r.requestid)"
                        + " join tblDistrict_Details d on(r.districtid =d.district_id) join tblmandal_Details b"
                        + " on (r.districtid =b.district_id and r.mandalid =b.mandal_id) "
                        + " join tblVillage_Details e  on (r.districtid =e.district_id "
                        + " and r.mandalid =e.mandal_id  and r.villageid =e.village_id)"
                        + " join tblHabitation_Details f  on ( r.districtid=f.district_id "
                        + " and r.mandalid=f.mandal_id "
                        + " and r.villageid=f.village_id  and r.habitationid=f.habitation_id) "
                        + " where r.mandalid >80 and r.districtid =? and c.requesttypeid =? "
                        + " and c.requeststatus =?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, districtId);
                pstmt.setString(2, requestType);
                pstmt.setString(3, status);
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        requestInformationDTO = new RequestInformationDTO();
                        requestInformationDTO.setRequestId(rs.getString(1));
                        requestInformationDTO.setRequestTypeDetailsId(rs.getString(2));
                        if (rs.getString(3) != null && rs.getString(3).equals("")) {
                            requestInformationDTO.setName("---");
                        } else {
                            requestInformationDTO.setName(rs.getString(3));
                        }

                        if (rs.getString(4) != null && rs.getString(4).equals("")) {
                            requestInformationDTO.setRelationName("---");
                        } else {
                            requestInformationDTO.setRelationName(rs.getString(4));
                        }

                        requestInformationDTO.setHouseNO(rs.getString(5));
                        requestInformationDTO.setMandalName(rs.getString(6));
                        requestInformationDTO.setDistrictName(rs.getString(7));
                        requestInformationDTO.setVillageName(rs.getString(8));
                        requestInformationDTO.setHabitationName(rs.getString(9));
                        requestInformationDTO.setPersonCode(rs.getString(10));
                        requestInformationDTO.setRequestTypeIdDataFormat(rs.getString(11));
                        requestInformationList.add(requestInformationDTO);
                    }
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestUrbanIformationDetails", "RequestInformationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "getRequestUrbanIformationDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestUrbanIformationDetails", "RequestInformationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "getRequestUrbanIformationDetails");
        } finally {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con);
            DBConnection.closeStatement(personCodeStmt);
            DBConnection.closeResultSet(personCodeRs);

        }
        return requestInformationList;
    }

    public int updateRequestDetails(DataSource ds, String status, String requestId, String requestTypeId) throws SADAREMDBException, SQLException {

        int updateRequestDetails = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;

        try {
            con = DBConnection.getConnection();

            if (requestTypeId.equals("7") || requestTypeId.equals("8") || requestTypeId.equals("9")) {

                query = "update RequestTypeMapping set requeststatus =?,updatedpersonstatus ='PersonUpdate' "
                        + " where requestid =?";

                pstmt = con.prepareStatement(query);
                pstmt.setString(1, status);
                pstmt.setString(2, requestId);
                updateRequestDetails = pstmt.executeUpdate();
            } else {
                query = "update RequestTypeMapping set requeststatus =? where requestid =?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, status);
                pstmt.setString(2, requestId);
                updateRequestDetails = pstmt.executeUpdate();
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateRequestDetails", "RequestInformationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "updateRequestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateRequestDetails", "RequestInformationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "updateRequestDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);

        }
        return updateRequestDetails;

    }

    public RequestInformationDTO checkRessessmentDetails(DataSource ds, String requestTypeId, String personCode, String loginId) throws SADAREMDBException, SQLException {

        RequestInformationDTO requestInformationDTO = new RequestInformationDTO();

        Connection con = null;
        PreparedStatement editPstmt = null;
        PreparedStatement viewPstmt = null;
        ResultSet editRs = null;
        ResultSet viewRs = null;
        String editQuery = null;
        String viewQuery = null;
        boolean edit = false;
        boolean view = false;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String insertQuery = null;
        int insertDeatils = 0;
        String serialNumber = null;
        Statement stmt = null;
        String name = null;
        String name1 = null;
        String name2 = null;
        String name3 = null;
        int name4 = 0;
        String name5 = null;
        String name6 = null;


        try {
            con = DBConnection.getConnection();

            if (requestTypeId.equals("7")) {

                editQuery = "select person_code from AppellateAuthorityandTemporary_RegistrationDetails"
                        + " where person_code =? and categoryid ='2' and vieweditmode ='Edit'"
                        + " and categorycount='1' and percentage <40";

                editPstmt = con.prepareStatement(editQuery);
                editPstmt.setString(1, personCode);
                editRs = editPstmt.executeQuery();

                if (editRs != null) {
                    while (editRs.next()) {
                        editRs.getString(1);
                        edit = true;
                        requestInformationDTO.setEditErrormsg("Ressessment is Completed!");
                    }
                }


                if (edit == false) {
                    viewQuery = "select person_code from AppellateAuthorityandTemporary_RegistrationDetails "
                            + " where person_code =? and categoryid ='2' and vieweditmode ='View' "
                            + " and categorycount='1' and percentage <40";


                    viewPstmt = con.prepareStatement(viewQuery);
                    viewPstmt.setString(1, personCode);
                    viewRs = viewPstmt.executeQuery();

                    if (viewRs != null) {
                        while (viewRs.next()) {
                            viewRs.getString(1);
                            view = true;
                            requestInformationDTO.setViewErrormsg("Already Resessment Registrated go head !");
                        }
                    }

                }
                if (edit == false && view == false) {
                    query = "select a.person_code,a.disability_id,totalDisability,b.rationcard_number,b.rationcard_slno,b.person_status"
                            + " from tblPerson_Disability_Details a join tblperson_personal_Details b  with (nolock) "
                            + " on (a.person_code=b.person_code) join tblPerson_Disability_totalValue_Details c"
                            + " on (a.person_code =c.person_code)where a.person_code =? and"
                            + " a.status='Active' and b.status ='Active'";

                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, personCode);
                    rs = pstmt.executeQuery();


                    if (rs != null) {
                        while (rs.next()) {

                            name = rs.getString(1);
                            name1 = rs.getString(2);
                            name2 = rs.getString(3);
                            name3 = rs.getString(4);
                            name4 = rs.getInt(5);
                            name5 = rs.getString(6);
                        }

                        if (name4 == 255) {
                            insertQuery = "insert into AppellateAuthorityandTemporary_RegistrationDetails "
                                    + " (person_code,disabilityid,percentage,categoryid,categorycount,rationCardnumber,coalesce (b.rationcard_slno,'255')"
                                    + " disabilityStatus,vieweditmode, CreatedDate,UpdatedDate,Status,loginID,deleteFlag)"
                                    + " values(?,?,?,'2','1',?,?,?,'Edit',"
                                    + " getDate(),getDate(),'Inactive',?,'Active')";


                            pstmt = con.prepareStatement(insertQuery);
                            pstmt.setString(1, name);
                            pstmt.setString(2, name1);
                            pstmt.setString(3, name2);
                            pstmt.setString(4, name3);
                            pstmt.setInt(5, name4);
                            pstmt.setString(6, name5);
                            pstmt.setString(7, loginId);
                            insertDeatils = pstmt.executeUpdate();
                        } else {

                            insertQuery = "insert into AppellateAuthorityandTemporary_RegistrationDetails "
                                    + " (person_code,disabilityid,percentage,categoryid,categorycount,rationCardnumber,serialNumber,"
                                    + " disabilityStatus,vieweditmode, CreatedDate,UpdatedDate,Status,loginID,deleteFlag)"
                                    + " values(?,?,?,'2','1',?,?,?,'Edit',"
                                    + " getDate(),getDate(),'Inactive',?,'Active')";

                            pstmt = con.prepareStatement(insertQuery);
                            pstmt.setString(1, name);
                            pstmt.setString(2, name1);
                            pstmt.setString(3, name2);
                            pstmt.setString(4, name3);
                            pstmt.setInt(5, name4);
                            pstmt.setString(6, name5);
                            pstmt.setString(7, loginId);
                            insertDeatils = pstmt.executeUpdate();
                        }
                    }
                    requestInformationDTO.setInsertDetails(insertDeatils);
                }


            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkRessessmentDetails", "RequestInformationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "checkRessessmentDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkRessessmentDetails", "RequestInformationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "checkRessessmentDetails");
        } finally {
            DBConnection.closeResultSet(viewRs);
            DBConnection.closeResultSet(editRs);
            DBConnection.closeStatement(viewPstmt);
            DBConnection.closeStatement(editPstmt);
			DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }

        return requestInformationDTO;
    }

    public RequestInformationDTO checkRenualDetails(DataSource ds, String requestTypeId, String personCode, String loginId) throws SADAREMDBException, SQLException {

        RequestInformationDTO requestInformationDTO = new RequestInformationDTO();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        String insertQuery = null;
        int insertDeatils = 0;
        boolean renualFlag = false;
        String name = null;
        String name1 = null;
        String name2 = null;
        String name3 = null;
        int name4 = 0;
        String name5 = null;
        String name6 = null;

        try {
            con = DBConnection.getConnection();

            if (requestTypeId.equals("9")) {
                query = "select * from tblPerson_Causes_of_Disability_Details b "
                        + " where datediff(dd, b.created_date,getdate())>(select  case "
                        + " when years_for_temparary =1 then 365 when years_for_temparary=2 then 730"
                        + " when years_for_temparary=3 then 1095 when years_for_temparary=4 then 1460 "
                        + " when years_for_temparary=5 then 1825  end years_for_temparary  from "
                        + " tblPerson_Causes_of_Disability_Details bc where b.person_code=bc.person_code "
                        + " and bc.categoryid ='3'and bc.status='Active') and b.person_Code =?";

                pstmt = con.prepareStatement(query);
                pstmt.setString(1, personCode);
                rs = pstmt.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        requestInformationDTO.setRenualstatus(rs.getString(1));
                        renualFlag = true;
                    }
                }
                if (renualFlag == false) {
                    query = "select a.person_code,a.disability_id,totalDisability,b.rationcard_number,b.rationcard_slno,b.person_status "
                            + "from tblPerson_Disability_Details a join tblperson_personal_Details b  with (nolock) "
                            + " on (a.person_code=b.person_code) join tblPerson_Disability_totalValue_Details c"
                            + " on (a.person_code =c.person_code)where a.person_code =? and"
                            + " a.status='Active' and b.status ='Active'";

                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, personCode);
                    rs = pstmt.executeQuery();

                    if (rs != null) {
                        while (rs.next()) {

                            name = rs.getString(1);
                            name1 = rs.getString(2);
                            name2 = rs.getString(3);
                            name3 = rs.getString(4);
                            name4 = rs.getInt(5);
                            name5 = rs.getString(6);
                        }
                        if (name4 == 255) {
                            insertQuery = "insert into AppellateAuthorityandTemporary_RegistrationDetails "
                                    + " (person_code,disabilityid,percentage,categoryid,categorycount,rationCardnumber,coalesce (b.rationcard_slno,'255')"
                                    + " disabilityStatus,vieweditmode, CreatedDate,UpdatedDate,Status,loginID,deleteFlag)"
                                    + " values(?,?,?,'3','1',?,?,?,'Edit',"
                                    + " getDate(),getDate(),'Active',?,'Active')";


                            pstmt = con.prepareStatement(insertQuery);
                            pstmt.setString(1, name);
                            pstmt.setString(2, name1);
                            pstmt.setString(3, name2);
                            pstmt.setString(4, name3);
                            pstmt.setInt(5, name4);
                            pstmt.setString(6, name5);
                            pstmt.setString(7, loginId);
                            insertDeatils = pstmt.executeUpdate();
                        } else {

                            insertQuery = "insert into AppellateAuthorityandTemporary_RegistrationDetails "
                                    + " (person_code,disabilityid,percentage,categoryid,categorycount,rationCardnumber,serialNumber,"
                                    + " disabilityStatus,vieweditmode, CreatedDate,UpdatedDate,Status,loginID,deleteFlag)"
                                    + " values(?,?,?,'3','1',?,?,?,'Edit',"
                                    + " getDate(),getDate(),'Inactive',?,'Active')";



                            pstmt = con.prepareStatement(insertQuery);
                            pstmt.setString(1, name);
                            pstmt.setString(2, name1);
                            pstmt.setString(3, name2);
                            pstmt.setString(4, name3);
                            pstmt.setInt(5, name4);
                            pstmt.setString(6, name5);
                            pstmt.setString(7, loginId);
                            insertDeatils = pstmt.executeUpdate();
                        }
                    }
                    requestInformationDTO.setRenualInsertDetails(insertDeatils);

                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkRenualDetails", "RequestInformationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "checkRenualDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkRenualDetails", "RequestInformationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "checkRenualDetails");
        } finally {
        	DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con);
        }
        return requestInformationDTO;
    }

    public int duplicateDetails(DataSource ds, String requestTypeId, String personCode, String loginId) throws SADAREMDBException, SQLException {

        int duplicateDetails = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        String insertQuery = null;

        String name = null;
        String name1 = null;
        String name2 = null;
        String name3 = null;
        int name4 = 0;
        String name5 = null;
        String name6 = null;

        RequestInformationDTO requestInformationDTO = new RequestInformationDTO();

        try {

            con = DBConnection.getConnection();

            if (requestTypeId.equals("8")) {
                query = "select a.person_code,a.disability_id,totalDisability,b.rationcard_number,b.rationcard_slno,b.person_status "
                        + "from tblPerson_Disability_Details a join tblperson_personal_Details b  with (nolock) "
                        + " on (a.person_code=b.person_code) join tblPerson_Disability_totalValue_Details c"
                        + " on (a.person_code =c.person_code)where a.person_code =? and"
                        + " a.status='Active' and b.status ='Active'";

                pstmt = con.prepareStatement(query);
                pstmt.setString(1, personCode);
                rs = pstmt.executeQuery();


                if (rs != null) {
                    while (rs.next()) {
                        name = rs.getString(1);
                        name1 = rs.getString(2);
                        name2 = rs.getString(3);
                        name3 = rs.getString(4);
                        name4 = rs.getInt(5);
                        name5 = rs.getString(6);

                    }
                    if (name4 == 255) {
                        insertQuery = "insert into AppellateAuthorityandTemporary_RegistrationDetails "
                                + " (person_code,disabilityid,percentage,categoryid,categorycount,rationCardnumber,coalesce (b.rationcard_slno,'255')"
                                + " disabilityStatus,vieweditmode, CreatedDate,UpdatedDate,Status,loginID,deleteFlag)"
                                + " values(?,?,?,'4','1',?,?,?,'Edit',"
                                + " getDate(),getDate(),'Active',?,'Active')";


                        pstmt = con.prepareStatement(insertQuery);
                        pstmt.setString(1, name);
                        pstmt.setString(2, name1);
                        pstmt.setString(3, name2);
                        pstmt.setString(4, name3);
                        pstmt.setInt(5, name4);
                        pstmt.setString(6, name5);
                        pstmt.setString(7, loginId);
                        duplicateDetails = pstmt.executeUpdate();
                    } else {

                        insertQuery = "insert into AppellateAuthorityandTemporary_RegistrationDetails "
                                + " (person_code,disabilityid,percentage,categoryid,categorycount,rationCardnumber,serialNumber,"
                                + " disabilityStatus,vieweditmode, CreatedDate,UpdatedDate,Status,loginID,deleteFlag)"
                                + " values(?,?,?,'4','1',?,?,?,'Edit',"
                                + " getDate(),getDate(),'Inactive',?,'Active')";

                        pstmt = con.prepareStatement(insertQuery);
                        pstmt.setString(1, name);
                        pstmt.setString(2, name1);
                        pstmt.setString(3, name2);
                        pstmt.setString(4, name3);
                        pstmt.setInt(5, name4);
                        pstmt.setString(6, name5);
                        pstmt.setString(7, loginId);
                        duplicateDetails = pstmt.executeUpdate();
                    }

                }


            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "duplicateDetails", "RequestInformationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "duplicateDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "duplicateDetails", "RequestInformationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "duplicateDetails");
        } finally {
        	DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con);

        }

        return duplicateDetails;

    }

    public int physicalImpairment(DataSource ds, String requestTypeId, String personCode, String loginId) throws SADAREMDBException, SQLException {

        int physicalImpairment = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        String insertQuery = null;
        String name = null;
        String name1 = null;
        String name2 = null;
        String name3 = null;
        int name4 = 0;
        String name5 = null;
        String name6 = null;

        try {
            con = DBConnection.getConnection();

            if (requestTypeId.equals("10")) {
                query = "select a.person_code,a.disability_id,totalDisability,b.rationcard_number,b.rationcard_slno,b.person_status "
                        + "from tblPerson_Disability_Details a join tblperson_personal_Details b  with (nolock) "
                        + " on (a.person_code=b.person_code) join tblPerson_Disability_totalValue_Details c"
                        + " on (a.person_code =c.person_code)where a.person_code =? and"
                        + " a.status='Active' and b.status ='Active'";


                pstmt = con.prepareStatement(query);
                pstmt.setString(1, personCode);
                rs = pstmt.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        name = rs.getString(1);
                        name1 = rs.getString(2);
                        name2 = rs.getString(3);
                        name3 = rs.getString(4);
                        name4 = rs.getInt(5);
                        name5 = rs.getString(6);

                    }
                    if (name4 == 255) {
                        insertQuery = "insert into AppellateAuthorityandTemporary_RegistrationDetails "
                                + " (person_code,disabilityid,percentage,categoryid,categorycount,rationCardnumber,coalesce (b.rationcard_slno,'255')"
                                + " disabilityStatus,vieweditmode, CreatedDate,UpdatedDate,Status,loginID,deleteFlag)"
                                + " values(?,?,?,'5','1',?,?,?,'Edit',"
                                + " getDate(),getDate(),'Active',?,'Active')";


                        pstmt = con.prepareStatement(insertQuery);
                        pstmt.setString(1, name);
                        pstmt.setString(2, name1);
                        pstmt.setString(3, name2);
                        pstmt.setString(4, name3);
                        pstmt.setInt(5, name4);
                        pstmt.setString(6, name5);
                        pstmt.setString(7, loginId);
                        physicalImpairment = pstmt.executeUpdate();
                    } else {

                        insertQuery = "insert into AppellateAuthorityandTemporary_RegistrationDetails "
                                + " (person_code,disabilityid,percentage,categoryid,categorycount,rationCardnumber,serialNumber,"
                                + " disabilityStatus,vieweditmode, CreatedDate,UpdatedDate,Status,loginID,deleteFlag)"
                                + " values(?,?,?,'5','1',?,?,?,'Edit',"
                                + " getDate(),getDate(),'Inactive',?,'Active')";


                        pstmt = con.prepareStatement(insertQuery);
                        pstmt.setString(1, name);
                        pstmt.setString(2, name1);
                        pstmt.setString(3, name2);
                        pstmt.setString(4, name3);
                        pstmt.setInt(5, name4);
                        pstmt.setString(6, name5);
                        pstmt.setString(7, loginId);
                        physicalImpairment = pstmt.executeUpdate();
                    }
                }


            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "physicalImpairment", "RequestInformationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "physicalImpairment");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "physicalImpairment", "RequestInformationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "physicalImpairment");
        } finally {
        	DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con);

        }



        return physicalImpairment;

    }

    public ArrayList getNewCertificateDetails(DataSource ds, String requestid, String requesttypeid) throws SADAREMDBException, SQLException {

        ArrayList newCertificateList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        Map mapList = null;

        try {
            con = DBConnection.getConnection();
            query = "select a.PensionCard_No,a.RationCard_Number,a.surname,a.first_name,case "
                    + " when gender =1 then 'Male'when gender=2 then 'Female' "
                    + " end gender,a.requestid,a.requesttypeid,d.district_name,b.mandal_name,e.village_name,habitation_name"
                    + " from tblperson_request_Details a join tblDistrict_Details d "
                    + " on(a.district_id =d.district_id) join tblmandal_Details b  "
                    + " on (a.district_id =b.district_id) and (a.mandal_id =b.mandal_id)"
                    + " join tblVillage_Details e  on (a.district_id =e.district_id)"
                    + " and (a.mandal_id =e.mandal_id)  and (a.village_id =e.village_id)"
                    + " join  tblHabitation_Details f on (a.district_id =f.district_id)"
                    + " and (a.mandal_id =f.mandal_id)  and (a.village_id =f.village_id)"
                    + " and (a.habitation_id= f.habitation_id)  where a.requestid =?"
                    + " and a.requesttypeid =?";




            pstmt = con.prepareStatement(query);
            pstmt.setString(1, requestid);
            pstmt.setString(2, requesttypeid);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    mapList = new HashMap();
                    mapList.put("pensionCardNO", rs.getString(1));
                    mapList.put("rationCardNumber", rs.getString(2));
                    mapList.put("surName", rs.getString(3));
                    mapList.put("firstName", rs.getString(4));
                    mapList.put("gender", rs.getString(5));
                    mapList.put("requestid", rs.getString(6));
                    mapList.put("requesttypeid", rs.getString(7));
                    mapList.put("districtName", rs.getString(8));
                    mapList.put("mandalName", rs.getString(9));
                    mapList.put("villageName", rs.getString(10));
                    mapList.put("habitationName", rs.getString(11));
                    newCertificateList.add(mapList);
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getNewCertificateDetails", "RequestInformationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "getNewCertificateDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getNewCertificateDetails", "RequestInformationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "getNewCertificateDetails");
        } finally {
        	DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con);
        }
        return newCertificateList;

    }

    public int insertNewCertificate(DataSource ds, String requestId, String requestTypeId, String loginId, String systemIp, String statusId, HttpServletRequest request) throws SADAREMDBException, SQLException {

        int insertNewCertificate = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        String insertQuery = null;
        PreparedStatement ps = null;
        int insertDataQuery = 0;
        String updateQuery = null;
        PreparedStatement updatePstmt = null;
        String requestUpdate = null;
        PreparedStatement requestPstmt = null;
        String habitationUpdatedCode = null;
        String habitationCode = null;
        String habitationMaxCode = null;
        //Statement stmt1 = null;
        //Statement stmt2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        String getMaxHabitationCodeQuery = null;

        try {
            con = DBConnection.getConnection();
            query = " select  reference_form_number,form_fill_Date,surname,first_name,"
                    + " gender, Date_of_Birth,age_years,religion,caste,marital_status,"
                    + " education,employment,relationship, relation_name,rationcard_type,"
                    + " RationCard_Number,epic_card, epiccard_no,pension_card,"
                    + " Pension_type, PensionCard_No,mole_one,mole_two,house_number,"
                    + " phone_no,email,District_id,mandal_id,village_id,habitation_id,"
                    + " pin_code,personname_telugu,fathername_telugu,person_Status,"
                    + " Parents_Marriage,Typeof_Disability,Existing_Percentage,"
                    + " rationcard_slno,Panchayat_ID"
                    + " from tblperson_request_Details"
                    + " where requestid =? and requesttypeid =?";




            pstmt = con.prepareStatement(query);
            pstmt.setString(1, requestId);
            pstmt.setString(2, requestTypeId);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {


                    String getHabitationCodeQuery = "select habitation_code from tblHabitation_Details where district_id=? and mandal_id=?  and village_id=? and habitation_id=? and Panchayat_ID=?";


                    pstmt = con. prepareStatement(getHabitationCodeQuery);
                    pstmt.setString(1, rs.getString(27));
                    pstmt.setString(2, rs.getString(28));
                    pstmt.setString(3, rs.getString(29));
                    pstmt.setString(4, rs.getString(30));
                    pstmt.setString(5, rs.getString(39));

                    rs1 = pstmt.executeQuery();

                    if (rs1 != null) {
                        while (rs1.next()) {
                            habitationCode = rs1.getString(1);
                        }
                    }
                    if (habitationCode != null) {

                        getMaxHabitationCodeQuery = "select max(Person_Code) from tblPerson_Personal_Details_new  with (nolock) where HabCode = ? ";



                        pstmt = con.prepareStatement(getHabitationCodeQuery);
                        pstmt.setString(1, habitationCode);
                        rs2 = pstmt.executeQuery();
                        while (rs2.next()) {
                            habitationMaxCode = rs2.getString(1);
                        }
                        if (habitationMaxCode != null) {

                            long longCode = Long.parseLong(habitationMaxCode);

                            if (habitationMaxCode.length() == 18 && habitationMaxCode.substring((habitationMaxCode.length() - 3), (habitationMaxCode.length())).equals("999")) {
                                //do something that prevent user from entering further more records
                            } else {
                                if (habitationMaxCode.startsWith("0")) {
                                    String last3Digits = habitationMaxCode.substring((habitationMaxCode.length() - 3), (habitationMaxCode.length()));
                                    if (last3Digits.equals("999")) {
                                        StringBuffer strbuf = new StringBuffer(habitationCode);
                                        habitationUpdatedCode = strbuf.append("1000").toString();



                                    } else {
                                        longCode++;
                                        String withZero = String.valueOf(longCode);
                                        habitationUpdatedCode = paddingString(withZero, withZero.length() + 1, '0');


                                    }
                                } else {
                                    String last3Digits = (String.valueOf(longCode)).substring(String.valueOf(longCode).length() - 3, (String.valueOf(longCode).length()));
                                    if (last3Digits.equals("999")) {
                                        StringBuffer strbuf = new StringBuffer(habitationCode);
                                        habitationUpdatedCode = strbuf.append("1000").toString();
                                    } else {
                                        longCode++;
                                        habitationUpdatedCode = String.valueOf(longCode);
                                    }
                                }

                            }
                        } else {
                            StringBuffer strbuf = new StringBuffer(habitationCode);
                            habitationUpdatedCode = strbuf.append("001").toString();

                        }
                        request.setAttribute("generatedSadaremID", habitationUpdatedCode);

                        insertQuery = "INSERT INTO tblPerson_Personal_Details"
                                + " (Person_Code,Reference_Form_Number,Form_Fill_Date,"
                                + " Surname,First_Name,Gender,Date_of_Birth,Age_Years,Religion,"
                                + " Caste,Marital_Status,Education,Employment,Relationship,"
                                + " Relation_Name, RationCard_Type,RationCard_Number,EPIC_Card,"
                                + " EPICCard_No,Pension_Card,Pension_Type,PensionCard_No,"
                                + " Mole_One, Mole_Two,House_Number,Phone_No,Email,District_ID,"
                                + " Mandal_ID, Village_ID,Habitation_ID,"
                                + " Pin_Code,Personname_Telugu,Fathername_Telugu,"
                                + " Person_Status,Parents_Marriage,Typeof_Disability,"
                                + " Existing_Percentage, Login_ID,System_IP_Address,Created_Date,"
                                + " Updated_Date,Status, View_Edit_Mode,PensionPhase,"
                                + " ReasonforPersonStatus,HabCode,CategoryID,"
                                + " CategoryCount,rationcard_slno,"
                                + " DistrictID,MandalID,VillageID,HabtationID)"
                                + " VALUES (?,?,?,?,?,"
                                + "?,?,?,?,?,"
                                + "?,?,?,?,?,"
                                + "?,?,?,?,?,"
                                + "?,?,?,?,?,"
                                + "?,?,?,?,?,"
                                + "?,?,?,?,?,"
                                + "?,?,?,?,?,"
                                + "getDate(),getDate(),"
                                + "?,?,?,"
                                + "?,?,?,?,?,"
                                + "?,?,?,?)";  // Total --43



                        ps = con.prepareStatement(insertQuery);
                        ps.setString(1, habitationUpdatedCode);
                        ps.setString(2, rs.getString(1));
                        ps.setString(3, rs.getString(2));
                        ps.setString(4, rs.getString(3));
                        ps.setString(5, rs.getString(4));
                        ps.setInt(6, rs.getInt(5));
                        ps.setString(7, rs.getString(6));
                        ps.setInt(8, rs.getInt(7));
                        ps.setInt(9, rs.getInt(8));
                        ps.setInt(10, rs.getInt(9));
                        ps.setInt(11, rs.getInt(10));
                        ps.setInt(12, rs.getInt(11));
                        ps.setInt(13, rs.getInt(12));
                        ps.setInt(14, rs.getInt(13));
                        ps.setString(15, rs.getString(14));
                        ps.setInt(16, rs.getInt(15));
                        ps.setString(17, rs.getString(16));
                        ps.setBoolean(18, rs.getBoolean(17));
                        ps.setString(19, rs.getString(18));
                        ps.setBoolean(20, rs.getBoolean(19));
                        ps.setString(21, rs.getString(20));
                        ps.setString(22, rs.getString(21));
                        ps.setString(23, rs.getString(22));
                        ps.setString(24, rs.getString(23));
                        ps.setString(25, rs.getString(24));
                        ps.setString(26, rs.getString(25));
                        ps.setString(27, rs.getString(26));
                        ps.setString(28, rs.getString(27));
                        ps.setString(29, rs.getString(28));
                        ps.setString(30, rs.getString(29));
                        ps.setString(31, rs.getString(30));
                        ps.setString(32, rs.getString(31));
                        ps.setString(33, rs.getString(32));
                        ps.setString(34, rs.getString(33));
                        ps.setString(35, rs.getString(34));
                        ps.setBoolean(36, rs.getBoolean(35));
                        ps.setInt(37, rs.getInt(36));
                        ps.setInt(38, rs.getInt(37));
                        //ps.setInt(39, rs.getInt(38));
                        ps.setString(39, loginId);
                        ps.setString(40, systemIp);
                        ps.setString(41, "Active");
                        ps.setString(42, "Edit");
                        ps.setString(43, "PIII");
                        ps.setString(44, "Live");
                        ps.setString(45, habitationCode);
                        ps.setInt(46, 1);
                        ps.setInt(47, 1);
                        ps.setInt(48, rs.getInt(38));
                        ps.setString(49, rs.getString(27));
                        ps.setString(50, rs.getString(28));
                        ps.setString(51, rs.getString(29));
                        ps.setString(52, rs.getString(30));

                        insertDataQuery += ps.executeUpdate();


                        if (insertDataQuery > 0) {
                            updateQuery = "update requesttypemapping set requeststatus ='Approval'"
                                    + " where requestid =? and requesttypeid =?";


                            updatePstmt = con.prepareStatement(updateQuery);
                            updatePstmt.setString(1, requestId);
                            updatePstmt.setString(2, requestTypeId);
                            insertNewCertificate = updatePstmt.executeUpdate();

                            requestUpdate = "update tblperson_request_Details  set updatestatus ='Approval'"
                                    + " where requestid =?";

                            requestPstmt = con.prepareStatement(requestUpdate);
                            requestPstmt.setString(1, requestId);
                            insertNewCertificate = requestPstmt.executeUpdate();
                        }
                    } else {
                        request.setAttribute("selecthabCode", "Habitation Code NotMatch Please Select Proper Territory Details");

                    }
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertNewCertificate", "RequestInformationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "insertNewCertificate");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertNewCertificate", "RequestInformationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "insertNewCertificate");
        } finally {
        	DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con);
            DBConnection.closeStatement(ps);
            DBConnection.closeStatement(updatePstmt);
            DBConnection.closeStatement(requestPstmt);
            DBConnection.closeResultSet(rs);
            DBConnection.closeResultSet(rs1);
            DBConnection.closeResultSet(rs2);
        }

        return insertNewCertificate;

    }

    public int rejectNewCertificate(DataSource ds, String requestId, String requestTypeId) throws SADAREMDBException, SQLException {

        int rejectNewCertificate = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        String rejectStatus = null;
        PreparedStatement rejectPstmt = null;
        int reject = 0;

        try {
            con = DBConnection.getConnection();
            query = "update requesttypemapping set requeststatus ='Reject' where requestid =? "
                    + "and requesttypeid =?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, requestId);
            pstmt.setString(2, requestTypeId);
            rejectNewCertificate = pstmt.executeUpdate();
            rejectStatus = " update tblperson_request_Details  set updatestatus ='Reject'"
                    + " where requestid =?";

            rejectPstmt = con.prepareStatement(rejectStatus);
            rejectPstmt.setString(1,requestId);
            reject = rejectPstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "rejectNewCertificate", "RequestInformationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "rejectNewCertificate");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "rejectNewCertificate", "RequestInformationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "rejectNewCertificate");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(rejectPstmt);

        }
        return rejectNewCertificate;
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

    public ArrayList getMultipleDetails(DataSource ds, String districtId, RequestInformationForm requestInformationForm) throws SADAREMDBException, SQLException {

        ArrayList multipleList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        ResultSet requestNameRs = null;
        String query = null;
        RequestInformationDTO requestInformationDTO = null;
        String regDate = null;
        String requestNameQuery = null;
        String conCatname = "";
        String personCodeQuery = null;
        PreparedStatement personCodeStmt = null;
        ResultSet personCodeRs = null;
        ArrayList personCode = new ArrayList();


        try {
            con = DBConnection.getConnection();

            personCodeQuery = "select distinct person_code from requestDetails";

            personCodeStmt = con.prepareStatement(personCodeQuery);
            personCodeRs = personCodeStmt.executeQuery();

            if (personCodeRs != null) {
                while (personCodeRs.next()) {
                    personCode.add(personCodeRs.getString(1));

                }
            }
            for (int i = 0; i < personCode.size(); i++) {

                query = "with a as (select person_code, districtid, mandalid, villageid, habcode "
                        + " from dbo.tblPerson_Personal_Details  with (nolock) where person_code=?) "
                        + " select  r.requestid,r.name,r.relationname,r.houseno, "
                        + " b.mandal_name,d.district_name,e.village_name,f.habitation_name,"
                        + " r.person_code,r.created_Date  from RequestDetails r  "
                        + " join RequestTypeMapping c on (c.requestid =r.requestid) join "
                        + " a on(R.person_code=a.person_code) join tblDistrict_Details d "
                        + " on(a.districtid =d.district_id) join tblmandal_Details b "
                        + " on (a.districtid =b.district_id) and (a.mandalid =b.mandal_id)"
                        + " join tblVillage_Details e on (a.districtid =e.district_id) "
                        + " and (a.mandalid =e.mandal_id) and (a.villageid =e.village_id) "
                        + " join tblHabitation_Details f on (a.habcode =f.Habitation_Code)"
                        + " where a.mandalid <80 and a.districtid =? "
                        + " and (c.requesttypeid in ('1') OR c.requesttypeid in ('2') OR c.requesttypeid in ('3')OR c.requesttypeid in ('5'))"
                        + " and c.requeststatus =? "
                        + " group by r.requestid,r.name,r.relationname,r.houseno,"
                        + " b.mandal_name,d.district_name,e.village_name,f.habitation_name,"
                        + " r.person_code,r.created_Date";


                pstmt = con.prepareStatement(query);
                pstmt.setString(1,personCode.get(i).toString());
                pstmt.setString(2,districtId);
                pstmt.setString(3,requestInformationForm.getStatus());

//

                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        requestInformationDTO = new RequestInformationDTO();
//                        requestId.add(rs.getString(1));
                        requestInformationDTO.setRequestId(rs.getString(1));
                        requestInformationDTO.setName(rs.getString(2));
                        requestInformationDTO.setRelationName(rs.getString(3));
                        requestInformationDTO.setHouseNO(rs.getString(4));
                        requestInformationDTO.setMandalName(rs.getString(5));
                        requestInformationDTO.setDistrictName(rs.getString(6));
                        requestInformationDTO.setVillageName(rs.getString(7));
                        requestInformationDTO.setHabitationName(rs.getString(8));
                        requestInformationDTO.setPersonCode(rs.getString(9));
                        regDate = new SimpleDateFormat("dd/mm/yyyy").format(new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString(10)));
                        requestInformationDTO.setRegDate(regDate);


//                        for (int k = 0; k < requestId.size(); k++) {
                        requestNameQuery = "select requesttypename from requesttypemapping a , tblrequesttypemaster b "
                                + " where a.requesttypeid = b.requesttypeid and requestid =?";


                        pstmt1 = con.prepareStatement(requestNameQuery);
                        pstmt1.setString(1, rs.getString(1));
                        requestNameRs = pstmt1.executeQuery();

                        if (requestNameRs != null) {
                            conCatname = "";
                            while (requestNameRs.next()) {
                                conCatname += requestNameRs.getString(1) + ",";
                                requestInformationDTO.setMultipleReqName(conCatname.substring(0, conCatname.length() - 1));
                                //  multipleList.add(requestInformationDTO);
                            }

                        }
//                        }
                        multipleList.add(requestInformationDTO);
                        // multipleList.add(requestInformationDTO);
                    }
                }

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleDetails", "RequestInformationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "getMultipleDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleDetails", "RequestInformationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "getMultipleDetails");
        } finally {
        	DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt1);
            DBConnection.closeStatement(personCodeStmt);
            DBConnection.closeResultSet(personCodeRs);
            DBConnection.closeResultSet(requestNameRs);
        }
        return multipleList;
    }

    public ArrayList getSingleDetails(DataSource ds, String districtId, RequestInformationForm requestInformationForm) throws SADAREMDBException, SQLException {

        ArrayList singleList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        RequestInformationDTO requestInformationDTO = null;
        String regDate = null;
        String personCodeQuery = null;
        ArrayList requestId = new ArrayList();
        PreparedStatement personCodeStmt = null;
        ResultSet personCodeRs = null;
        ArrayList personCode = new ArrayList();
        String requestNameQuery = null;
        PreparedStatement pstmt1 = null;
        ResultSet requestNameRs = null;
        String conCatname = "";


        try {
            con = DBConnection.getConnection();

            personCodeQuery = "select distinct person_code from requestDetails";

            personCodeStmt = con.prepareStatement(personCodeQuery);
            personCodeRs = personCodeStmt.executeQuery();

            if (personCodeRs != null) {
                while (personCodeRs.next()) {
                    personCode.add(personCodeRs.getString(1));

                }
            }
            for (int i = 0; i < personCode.size(); i++) {

                query = "with a as (select person_code, districtid, mandalid, villageid, habcode "
                        + " from dbo.tblPerson_Personal_Details  with (nolock)  where person_code=?)"
                        + " select  r.requestid,r.name,r.relationname,r.houseno,"
                        + " b.mandal_name,d.district_name,e.village_name,f.habitation_name,"
                        + " r.person_code,r.created_Date  from RequestDetails r"
                        + " join RequestTypeMapping c on (c.requestid =r.requestid) join a"
                        + " on(R.person_code=a.person_code) join tblDistrict_Details d "
                        + " on(a.districtid =d.district_id) join tblmandal_Details b "
                        + " on (a.districtid =b.district_id) and (a.mandalid =b.mandal_id)"
                        + " join tblVillage_Details e on (a.districtid =e.district_id) "
                        + " and (a.mandalid =e.mandal_id) "
                        + " and (a.villageid =e.village_id) join tblHabitation_Details "
                        + " f on (a.habcode =f.Habitation_Code) "
                        + " where a.mandalid <80 and a.districtid =? "
                        + " and (c.requesttypeid in (?)) and c.requeststatus =?"
                        + " group by r.requestid,r.name,r.relationname,r.houseno,"
                        + " b.mandal_name,d.district_name,e.village_name,f.habitation_name,"
                        + " r.person_code,r.created_Date";

                pstmt = con.prepareStatement(query);
                pstmt.setString(1,  personCode.get(i).toString());
                pstmt.setString(2,  districtId);
                pstmt.setString(3,  requestInformationForm.getRequestTypeDetails());
                pstmt.setString(4,  requestInformationForm.getStatus());
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        requestInformationDTO = new RequestInformationDTO();
                        requestId.add(rs.getString(1));
                        requestInformationDTO.setRequestId(rs.getString(1));
                        requestInformationDTO.setName(rs.getString(2));
                        requestInformationDTO.setRelationName(rs.getString(3));
                        requestInformationDTO.setHouseNO(rs.getString(4));
                        requestInformationDTO.setMandalName(rs.getString(5));
                        requestInformationDTO.setDistrictName(rs.getString(6));
                        requestInformationDTO.setVillageName(rs.getString(7));
                        requestInformationDTO.setHabitationName(rs.getString(8));
                        requestInformationDTO.setPersonCode(rs.getString(9));
                        regDate = new SimpleDateFormat("dd/mm/yyyy").format(new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString(10)));
                        requestInformationDTO.setRegDate(regDate);


//                        for (int k = 0; k < requestId.size(); k++) {
                        requestNameQuery = "select requesttypename from requesttypemapping a , tblrequesttypemaster b "
                                + " where a.requesttypeid = b.requesttypeid and requestid =?";

                        pstmt1 = con.prepareStatement(requestNameQuery);
                        pstmt1.setString(1, rs.getString(1));
                        requestNameRs = pstmt1.executeQuery();

                        if (requestNameRs != null) {
                            conCatname = "";
                            while (requestNameRs.next()) {
                                conCatname += requestNameRs.getString(1) + ",";
                                requestInformationDTO.setMultipleReqName(conCatname.substring(0, conCatname.length() - 1));
                                //  multipleList.add(requestInformationDTO);
                            }

                        }
//                        }
                        singleList.add(requestInformationDTO);
                        // multipleList.add(requestInformationDTO);
                    }
                }

            }

            if (requestInformationForm.getRequestTypeDetails().equals("6")) {
                query = "select r.requestid,c.requesttypeid,r.name,r.relationname,r.houseno,"
                        + " b.mandal_name,d.district_name,e.village_name,f.habitation_name,coalesce(r.person_code,'---'),c.requesttypeid,r.created_Date  from "
                        + " RequestDetails r  join RequestTypeMapping c on (c.requestid =r.requestid)"
                        + " join tblDistrict_Details d on(r.districtid =d.district_id) join tblmandal_Details b"
                        + " on (r.districtid =b.district_id and r.mandalid =b.mandal_id) "
                        + " join tblVillage_Details e  on (r.districtid =e.district_id "
                        + " and r.mandalid =e.mandal_id  and r.villageid =e.village_id)"
                        + " join tblHabitation_Details f  on ( r.districtid=f.district_id "
                        + " and r.mandalid=f.mandal_id "
                        + " and r.villageid=f.village_id  and r.habitationid=f.habitation_id) "
                        + " where r.mandalid <80 and r.districtid =? and c.requesttypeid =? "
                        + " and c.requeststatus =?";

                pstmt = con.prepareStatement(query);
                pstmt.setString(1, districtId);
                pstmt.setString(2, requestInformationForm.getRequestTypeDetails());
                pstmt.setString(3, requestInformationForm.getStatus());
                rs = pstmt.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        requestInformationDTO = new RequestInformationDTO();
                        requestInformationDTO.setRequestId(rs.getString(1));
                        requestInformationDTO.setRequestTypeDetailsId(rs.getString(2));
                        requestInformationDTO.setName(rs.getString(3));
                        requestInformationDTO.setRelationName(rs.getString(4));
                        requestInformationDTO.setHouseNO(rs.getString(5));
                        requestInformationDTO.setMandalName(rs.getString(6));
                        requestInformationDTO.setDistrictName(rs.getString(7));
                        requestInformationDTO.setVillageName(rs.getString(8));
                        requestInformationDTO.setHabitationName(rs.getString(9));
                        requestInformationDTO.setPersonCode(rs.getString(10));
                        requestInformationDTO.setRequestTypeIdDataFormat(rs.getString(11));
                        regDate = new SimpleDateFormat("dd/mm/yyyy").format(new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString(12)));
                        requestInformationDTO.setRegDate(regDate);


                        requestNameQuery = "select requesttypename from requesttypemapping a , tblrequesttypemaster b "
                                + " where a.requesttypeid = b.requesttypeid and requestid =?";


                        pstmt1 = con.prepareStatement(requestNameQuery);
                        pstmt1.setString(1, rs.getString(1));

                        requestNameRs = pstmt1.executeQuery();

                        if (requestNameRs != null) {
                            conCatname = "";
                            while (requestNameRs.next()) {
                                conCatname += requestNameRs.getString(1) + ",";

                                requestInformationDTO.setMultipleReqName(conCatname.substring(0, conCatname.length() - 1));
                                //  multipleList.add(requestInformationDTO);
                            }

                        }
                        singleList.add(requestInformationDTO);
                    }

                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSingleDetails", "RequestInformationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "getSingleDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSingleDetails", "RequestInformationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "getSingleDetails");
        } finally {
        	DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt1);
            DBConnection.closeStatement(personCodeStmt);
            DBConnection.closeResultSet(personCodeRs);
            DBConnection.closeResultSet(requestNameRs);
        }

        return singleList;
    }

    public ArrayList getMultipleUrbanDetails(DataSource ds, String districtId, RequestInformationForm requestInformationForm) throws SADAREMDBException, SQLException {

        ArrayList multipleUrbanList = new ArrayList();

        Connection con = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        ResultSet requestNameRs = null;
        String query = null;
        ArrayList requestId = new ArrayList();

        RequestInformationDTO requestInformationDTO = null;
        String regDate = null;
        String requestNameQuery = null;
        String conCatname = "";
        String personCodeQuery = null;
        PreparedStatement personCodeStmt = null;
        ResultSet personCodeRs = null;
        ArrayList personCode = new ArrayList();


        try {
            con = DBConnection.getConnection();

            personCodeQuery = "select distinct person_code from requestDetails";


            personCodeStmt = con.prepareStatement(personCodeQuery);
            personCodeRs = personCodeStmt.executeQuery();

            if (personCodeRs != null) {
                while (personCodeRs.next()) {
                    personCode.add(personCodeRs.getString(1));

                }
            }
            for (int i = 0; i < personCode.size(); i++) {

                query = "with a as (select person_code, districtid, mandalid, villageid, habcode "
                        + " from dbo.tblPerson_Personal_Details  with (nolock) where person_code=?) "
                        + " select  r.requestid,r.name,r.relationname,r.houseno, "
                        + " b.mandal_name,d.district_name,e.village_name,f.habitation_name,"
                        + " r.person_code,r.created_Date  from RequestDetails r  "
                        + " join RequestTypeMapping c on (c.requestid =r.requestid) join "
                        + " a on(R.person_code=a.person_code) join tblDistrict_Details d "
                        + " on(a.districtid =d.district_id) join tblmandal_Details b "
                        + " on (a.districtid =b.district_id) and (a.mandalid =b.mandal_id)"
                        + " join tblVillage_Details e on (a.districtid =e.district_id) "
                        + " and (a.mandalid =e.mandal_id) and (a.villageid =e.village_id) "
                        + " join tblHabitation_Details f on (a.habcode =f.Habitation_Code)"
                        + " where a.mandalid >80 and a.districtid =? "
                        + " and (c.requesttypeid in ('1') OR c.requesttypeid in ('2') OR c.requesttypeid in ('3')OR c.requesttypeid in ('5'))"
                        + " and c.requeststatus =? "
                        + " group by r.requestid,r.name,r.relationname,r.houseno,"
                        + " b.mandal_name,d.district_name,e.village_name,f.habitation_name,"
                        + " r.person_code,r.created_Date";


                pstmt = con.prepareStatement(query);
                pstmt.setString(1, personCode.get(i).toString());
                pstmt.setString(2, districtId);
                pstmt.setString(3, requestInformationForm.getStatus());

                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        requestInformationDTO = new RequestInformationDTO();
//                        requestId.add(rs.getString(1));
                        requestInformationDTO.setRequestId(rs.getString(1));
                        requestInformationDTO.setName(rs.getString(2));
                        requestInformationDTO.setRelationName(rs.getString(3));
                        requestInformationDTO.setHouseNO(rs.getString(4));
                        requestInformationDTO.setMandalName(rs.getString(5));
                        requestInformationDTO.setDistrictName(rs.getString(6));
                        requestInformationDTO.setVillageName(rs.getString(7));
                        requestInformationDTO.setHabitationName(rs.getString(8));
                        requestInformationDTO.setPersonCode(rs.getString(9));
                        regDate = new SimpleDateFormat("dd/mm/yyyy").format(new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString(10)));
                        requestInformationDTO.setRegDate(regDate);


//                        for (int k = 0; k < requestId.size(); k++) {
                        requestNameQuery = "select requesttypename from requesttypemapping a , tblrequesttypemaster b "
                                + " where a.requesttypeid = b.requesttypeid and requestid =?";


                        pstmt1 = con.prepareStatement(requestNameQuery);
                        pstmt1.setString(1, rs.getString(1));
                        requestNameRs = pstmt1.executeQuery();

                        if (requestNameRs != null) {
                            conCatname = "";
                            while (requestNameRs.next()) {
                                conCatname += requestNameRs.getString(1) + ",";

                                requestInformationDTO.setMultipleReqName(conCatname.substring(0, conCatname.length() - 1));
                                //  multipleList.add(requestInformationDTO);
                            }

                        }
//                        }
                        multipleUrbanList.add(requestInformationDTO);
                        // multipleList.add(requestInformationDTO);
                    }
                }

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleUrbanDetails", "RequestInformationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "getMultipleUrbanDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleUrbanDetails", "RequestInformationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "getMultipleUrbanDetails");
        } finally {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt1);
            DBConnection.closeResultSet(requestNameRs);

        }

        return multipleUrbanList;

    }

    public ArrayList getSingleUrbanDetails(DataSource ds, String districtId, RequestInformationForm requestInformationForm) throws SADAREMDBException, SQLException {

        ArrayList singleList = new ArrayList();
        ArrayList singleUrbanList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        RequestInformationDTO requestInformationDTO = null;
        String regDate = null;
        String personCodeQuery = null;
        ArrayList requestId = new ArrayList();
        PreparedStatement personCodeStmt = null;
        ResultSet personCodeRs = null;
        ArrayList personCode = new ArrayList();
        String requestNameQuery = null;
        PreparedStatement pstmt1 = null;
        ResultSet requestNameRs = null;
        String conCatname = "";
        try {
            con = DBConnection.getConnection();

            personCodeQuery = "select distinct person_code from requestDetails";

            personCodeStmt = con.prepareStatement(personCodeQuery);
            personCodeRs = personCodeStmt.executeQuery();

            if (personCodeRs != null) {
                while (personCodeRs.next()) {
                    personCode.add(personCodeRs.getString(1));

                }
            }
            for (int i = 0; i < personCode.size(); i++) {

                query = "with a as (select person_code, districtid, mandalid, villageid, habcode "
                        + " from dbo.tblPerson_Personal_Details  with (nolock) where person_code=?)"
                        + " select  r.requestid,r.name,r.relationname,r.houseno,"
                        + " b.mandal_name,d.district_name,e.village_name,f.habitation_name,"
                        + " r.person_code,r.created_Date  from RequestDetails r"
                        + " join RequestTypeMapping c on (c.requestid =r.requestid) join a"
                        + " on(R.person_code=a.person_code) join tblDistrict_Details d "
                        + " on(a.districtid =d.district_id) join tblmandal_Details b "
                        + " on (a.districtid =b.district_id) and (a.mandalid =b.mandal_id)"
                        + " join tblVillage_Details e on (a.districtid =e.district_id) "
                        + " and (a.mandalid =e.mandal_id) "
                        + " and (a.villageid =e.village_id) join tblHabitation_Details "
                        + " f on (a.habcode =f.Habitation_Code) "
                        + " where a.mandalid >80 and a.districtid =? "
                        + " and (c.requesttypeid in (?)) and c.requeststatus =?"
                        + " group by r.requestid,r.name,r.relationname,r.houseno,"
                        + " b.mandal_name,d.district_name,e.village_name,f.habitation_name,"
                        + " r.person_code,r.created_Date";


                pstmt = con.prepareStatement(query);
                pstmt.setString(1, personCode.get(i).toString());
                pstmt.setString(2, districtId);
                pstmt.setString(3, requestInformationForm.getRequestTypeDetails());
                pstmt.setString(4, requestInformationForm.getStatus());;
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        requestInformationDTO = new RequestInformationDTO();
                        requestId.add(rs.getString(1));
                        requestInformationDTO.setRequestId(rs.getString(1));
                        requestInformationDTO.setName(rs.getString(2));
                        requestInformationDTO.setRelationName(rs.getString(3));
                        requestInformationDTO.setHouseNO(rs.getString(4));
                        requestInformationDTO.setMandalName(rs.getString(5));
                        requestInformationDTO.setDistrictName(rs.getString(6));
                        requestInformationDTO.setVillageName(rs.getString(7));
                        requestInformationDTO.setHabitationName(rs.getString(8));
                        requestInformationDTO.setPersonCode(rs.getString(9));
                        regDate = new SimpleDateFormat("dd/mm/yyyy").format(new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString(10)));
                        requestInformationDTO.setRegDate(regDate);


//                        for (int k = 0; k < requestId.size(); k++) {
                        requestNameQuery = "select requesttypename from requesttypemapping a , tblrequesttypemaster b "
                                + " where a.requesttypeid = b.requesttypeid and requestid =?";


                        pstmt1 = con.prepareStatement(requestNameQuery);
                        pstmt1.setString(1, rs.getString(1));
                        requestNameRs = pstmt1.executeQuery();

                        if (requestNameRs != null) {
                            conCatname = "";
                            while (requestNameRs.next()) {
                                conCatname += requestNameRs.getString(1) + ",";

                                requestInformationDTO.setMultipleReqName(conCatname.substring(0, conCatname.length() - 1));
                                //  multipleList.add(requestInformationDTO);
                            }

                        }
//                        }
                        singleUrbanList.add(requestInformationDTO);
                        // multipleList.add(requestInformationDTO);
                    }
                }

            }

            if (requestInformationForm.getRequestTypeDetails().equals("6")) {


                query = "select r.requestid,c.requesttypeid,r.name,r.relationname,r.houseno,"
                        + " b.mandal_name,d.district_name,e.village_name,f.habitation_name,coalesce(r.person_code,'---'),c.requesttypeid,r.created_Date  from "
                        + " RequestDetails r  join RequestTypeMapping c on (c.requestid =r.requestid)"
                        + " join tblDistrict_Details d on(r.districtid =d.district_id) join tblmandal_Details b"
                        + " on (r.districtid =b.district_id and r.mandalid =b.mandal_id) "
                        + " join tblVillage_Details e  on (r.districtid =e.district_id "
                        + " and r.mandalid =e.mandal_id  and r.villageid =e.village_id)"
                        + " join tblHabitation_Details f  on ( r.districtid=f.district_id "
                        + " and r.mandalid=f.mandal_id "
                        + " and r.villageid=f.village_id  and r.habitationid=f.habitation_id) "
                        + " where r.mandalid >80 and r.districtid =? and c.requesttypeid =? "
                        + " and c.requeststatus =?";


                pstmt = con.prepareStatement(query);
                pstmt.setString(1, districtId);
                pstmt.setString(2, requestInformationForm.getRequestTypeDetails());
                pstmt.setString(3,  requestInformationForm.getStatus());
                rs = pstmt.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        requestInformationDTO = new RequestInformationDTO();
                        requestInformationDTO.setRequestId(rs.getString(1));
                        requestInformationDTO.setRequestTypeDetailsId(rs.getString(2));
                        requestInformationDTO.setName(rs.getString(3));
                        requestInformationDTO.setRelationName(rs.getString(4));
                        requestInformationDTO.setHouseNO(rs.getString(5));
                        requestInformationDTO.setMandalName(rs.getString(6));
                        requestInformationDTO.setDistrictName(rs.getString(7));
                        requestInformationDTO.setVillageName(rs.getString(8));
                        requestInformationDTO.setHabitationName(rs.getString(9));
                        requestInformationDTO.setPersonCode(rs.getString(10));
                        requestInformationDTO.setRequestTypeIdDataFormat(rs.getString(11));
                        regDate = new SimpleDateFormat("dd/mm/yyyy").format(new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString(12)));
                        requestInformationDTO.setRegDate(regDate);


                        requestNameQuery = "select requesttypename from requesttypemapping a , tblrequesttypemaster b "
                                + " where a.requesttypeid = b.requesttypeid and requestid =?";


                        pstmt1 = con.prepareStatement(requestNameQuery);
                        pstmt1.setString(1, rs.getString(1));
                        requestNameRs = pstmt1.executeQuery();

                        if (requestNameRs != null) {
                            conCatname = "";
                            while (requestNameRs.next()) {
                                conCatname += requestNameRs.getString(1) + ",";

                                requestInformationDTO.setMultipleReqName(conCatname.substring(0, conCatname.length() - 1));
                                //  multipleList.add(requestInformationDTO);
                            }

                        }
                        singleUrbanList.add(requestInformationDTO);
                    }

                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSingleUrbanDetails", "RequestInformationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "getSingleUrbanDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSingleUrbanDetails", "RequestInformationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationDAO", "getSingleUrbanDetails");
        } finally {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt1);
            DBConnection.closeStatement(personCodeStmt);
            DBConnection.closeResultSet(personCodeRs);
            DBConnection.closeResultSet(requestNameRs);
        }
        return singleUrbanList;

    }
}
