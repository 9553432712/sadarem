/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.NameRelationChangeDTO;
import org.bf.disability.form.NameRelationChangeForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 693461
 */
public class NameRelationChangeDAO {

    public ArrayList getnameRelationChangeDetails(DataSource ds, int campId, String districtId) throws SADAREMDBException, SQLException {


        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map campData = null;
        ArrayList campList = new ArrayList();

        try {
            con = DBConnection.getConnection();
            query = "select  distinct a.person_code,a.requestid,a.name,a.relationname,c.district_name, "
                    + " d.mandal_name,e.village_name,f.habitation_name,g.requesttypeid, m.requesttypename " +
                    " from requestDetails a  "
                    + " join tblPerson_Personal_Details b  with (nolock) on(a.person_code =b.person_code)  "
                    + " join tblDistrict_Details c on(c.district_id =a.districtid) "
                    + " join tblmandal_Details d on(d.district_id=a.districtid and d.mandal_id =a.mandalid)"
                    + " join tblvillage_Details e on (a.districtid=e.district_id "
                    + " and a.mandalid =e.mandal_id and a.villageid=e.village_id)"
                    + " join tblhabitation_Details f on (a.districtid =f.district_id "
                    + " and a.mandalid =f.mandal_id and a.villageid =f.village_id "
                    + " and a.habitationid =f.habitation_id) join requesttypemapping g "
                    + " on(a.requestid =g.requestid) join tblrequesttypemaster m on (g.requesttypeid=m.requesttypeid) where g.updatedpersonstatus is null  and g.requeststatus ='Approval'"
                    + " and b.district_id =? and g.requesttypeid in('1','2','3','4','5') and a.status='Active'";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtId);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    campData = new HashMap();
                    campData.put("personCode", rs.getString(1));
                    campData.put("requestId", rs.getString(2));
                    if (rs.getString(3) != null && rs.getString(3).equals("")) {

                        campData.put("name", "--");

                    } else {
                        campData.put("name", rs.getString(3));
                    }
                    if (rs.getString(4) != null && rs.getString(4).equals("")) {

                        campData.put("relationName", "--");

                    } else {
                        campData.put("relationName", rs.getString(4));
                    }

                    campData.put("districtName", rs.getString(5));
                    campData.put("mandalName", rs.getString(6));
                    campData.put("villageName", rs.getString(7));
                    campData.put("habitationName", rs.getString(8));
                    campData.put("requesttypeid", rs.getString(9));
                    campData.put("requesttypeName", rs.getString(10));
                    campList.add(campData);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getnameRelationChangeDetails", "NameRelationChangeDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "getnameRelationChangeDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getnameRelationChangeDetails", "NameRelationChangeDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "getnameRelationChangeDetails");
        }
        finally 
        {
            DBConnection.closeResultSet(rs); 
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return campList;
    }

    public ArrayList individualNameRelationNameChangeDetails(DataSource ds, String requestId) throws SADAREMDBException, SQLException {

        ArrayList individualNameRelationList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        Map individualName = null;
        String requestTypeId = null;

        ResultSet rs1 = null;
        NameRelationChangeDTO nameRelationChangeDTO = new NameRelationChangeDTO();

        try {
            con = DBConnection.getConnection();
            query = "select  a.requestid,b.requesttypeid,c.requesttypename,a.person_code"
                    + " from requestDetails a  join requesttypemapping b on(a.requestid =b.requestid)"
                    + " join tblrequesttypemaster c on(c.requesttypeid =b.requesttypeid) where a.requestid =?"
                    + " and b.requeststatus ='Approval' and b.updatedpersonstatus is null";


            pstmt = con.prepareStatement(query);
            pstmt.setString(1, requestId);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    requestId = rs.getString(1);
                    requestTypeId = rs.getString(2);
                    individualNameRelationList.add(requestTypeId);
//                   
//
//                 
//                    if (requestId!=null && requestTypeId.equalsIgnoreCase("1")) {
//

//                query = "select  c.surname,c.first_name,b.surname,b.name NewNamess, "
//                        + " a.person_code,b.requestid,b.requesttypeid from requestDetails a"
//                        + " join  requesttypemapping b on(a.requestid =b.requestid)"
//                        + " join  tblPerson_Personal_Details c on(c.person_code =a.person_code)"
//                        + " where"
//                        + "  b.requestid ='" + requestId + "'";
//

//
//                pstmt = con.prepareStatement(query);
//
//                rs = pstmt.executeQuery();
//
//                if (rs != null) {
//                    while (rs.next()) {
//                        nameRelationChangeDTO = new NameRelationChangeDTO();
//                        nameRelationChangeDTO.setOldsurName(rs.getString(1));
//                        nameRelationChangeDTO.setOldName(rs.getString(2));
//                        nameRelationChangeDTO.setNewsurName(rs.getString(3));
//                        nameRelationChangeDTO.setNewName(rs.getString(4));
//                        nameRelationChangeDTO.setRequestpersonCode(rs.getString(5));
//                        nameRelationChangeDTO.setRequestChangeId(rs.getString(6));
//                        nameRelationChangeDTO.setRequestIndividualTyepId(rs.getString(7));
//                        individualNameRelationList.add(nameRelationChangeDTO);
//                    }
//                }
//              }
//                }

                    /*if (requestTypeId.equals("2")) {
                    query = "select c.relation_name, b.relationname, a.person_code,b.requestid,b.requesttypeid"
                    + " from requestDetails a join requesttypemapping b on(a.requestid =b.requestid)"
                    + " join tblPerson_Personal_Details c on(c.person_code =a.person_code) where "
                    + " b.requestid ='" + requestId + "'";


                    pstmt = con.prepareStatement(query);

                    rs1 = pstmt.executeQuery();

                    if (rs1 != null) {
                    while (rs1.next()) {
                    nameRelationChangeDTO = new NameRelationChangeDTO();
                    nameRelationChangeDTO.setOldRelationName(rs1.getString(1));
                    nameRelationChangeDTO.setNewRelationName(rs1.getString(2));
                    nameRelationChangeDTO.setRequestpersonCode(rs1.getString(3));
                    nameRelationChangeDTO.setRequestChangeId(rs1.getString(4));
                    nameRelationChangeDTO.setRequestIndividualTyepId(rs1.getString(5));
                    individualNameRelationList.add(nameRelationChangeDTO);
                    }
                    }
                    }*/


                }

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "individualNameRelationNameChangeDetails", "NameRelationChangeDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "individualNameRelationNameChangeDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "individualNameRelationNameChangeDetails", "NameRelationChangeDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "individualNameRelationNameChangeDetails");
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);  
            DBConnection.closeResultSet(rs1);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return individualNameRelationList;

    }

    /* public ArrayList oldParticularDertails(DataSource ds, String personCode, String requestId, String requestTypeId) throws SADAREMDBException,SQLException {

    ArrayList oldParticularList = new ArrayList();
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    String query = null;
    NameRelationChangeDTO nameRelationChangeDTO = null;


    try {
    con = DBConnection.getConnection();

    if (requestTypeId.equals("1")) {
    query = "select  c.surname,c.first_name,b.surname,b.name NewNamess, "
    + " a.person_code,b.requestid,b.requesttypeid from requestDetails a"
    + " join  requesttypemapping b on(a.requestid =b.requestid)"
    + " join  tblPerson_Personal_Details c on(c.person_code =a.person_code)"
    + " where a.person_code ='" + personCode + "' and b.requesttypeid ='" + requestTypeId + "'"
    + " and b.requestid ='" + requestId + "'";

    pstmt = con.prepareStatement(query);

    rs = pstmt.executeQuery();

    if (rs != null) {
    while (rs.next()) {
    nameRelationChangeDTO = new NameRelationChangeDTO();
    nameRelationChangeDTO.setOldsurName(rs.getString(1));
    nameRelationChangeDTO.setOldName(rs.getString(2));
    nameRelationChangeDTO.setNewsurName(rs.getString(3));
    nameRelationChangeDTO.setNewName(rs.getString(4));
    nameRelationChangeDTO.setRequestpersonCode(rs.getString(5));
    nameRelationChangeDTO.setRequestChangeId(rs.getString(6));
    nameRelationChangeDTO.setRequestIndividualTyepId(rs.getString(7));
    oldParticularList.add(nameRelationChangeDTO);
    }
    }
    }
    else if (requestTypeId.equals("2")) {
    query = "select c.relation_name, b.relationname, a.person_code,b.requestid,b.requesttypeid"
    + " from requestDetails a join requesttypemapping b on(a.requestid =b.requestid)"
    + " join tblPerson_Personal_Details c on(c.person_code =a.person_code) where "
    + " a.person_code ='" + personCode + "' and b.requesttypeid ='" + requestTypeId + "' and b.requestid ='" + requestId + "'";


    pstmt = con.prepareStatement(query);

    rs1 = pstmt.executeQuery();

    if (rs1 != null) {
    while (rs1.next()) {
    nameRelationChangeDTO = new NameRelationChangeDTO();
    nameRelationChangeDTO.setOldRelationName(rs1.getString(1));
    nameRelationChangeDTO.setNewRelationName(rs1.getString(2));
    nameRelationChangeDTO.setRequestpersonCode(rs1.getString(3));
    nameRelationChangeDTO.setRequestChangeId(rs1.getString(4));
    nameRelationChangeDTO.setRequestIndividualTyepId(rs1.getString(5));
    oldParticularList.add(nameRelationChangeDTO);
    }
    }
    }else if(requestTypeId.equals("3")){

    query =" select c.mole_one as OldMoleOne, c.mole_two as OldMoleTwo," +
    " b.molesOne as NewMoleOne,b.molesTwo as NewMoleTwo,a.person_code," +
    " b.requestid,b.requesttypeid from requestDetails a join requesttypemapping b" +
    " on(a.requestid =b.requestid) join tblPerson_Personal_Details c " +
    " on(c.person_code =a.person_code) where a.person_code ='"+personCode+"'" +
    " and b.requesttypeid ='"+requestTypeId+"' and b.requestid ='"+requestId+"'";



    pstmt =con.prepareStatement(query);
    
    rs = pstmt.executeQuery();

    if(rs!=null){
    while (rs.next()) {
    nameRelationChangeDTO = new NameRelationChangeDTO();
    nameRelationChangeDTO.setOldmolesOne(rs.getString(1));
    nameRelationChangeDTO.setOldmolesTwo(rs.getString(2));
    nameRelationChangeDTO.setNewmoleOne(rs.getString(3));
    nameRelationChangeDTO.setNewmolesTwo(rs.getString(4));
    nameRelationChangeDTO.setRequestpersonCode(rs.getString(5));
    nameRelationChangeDTO.setRequestChangeId(rs.getString(6));
    nameRelationChangeDTO.setRequestIndividualTyepId(rs.getString(7));
    oldParticularList.add(nameRelationChangeDTO);

    }

    }

    }

    } catch (Exception e) {
    e.printStackTrace();
    }finally {
    DBConnection.closeAll(con, pstmt, pstmt, rs);
    DBConnection.closeResultSet(rs1);
    }

    return oldParticularList;
    }*/
    public int updateParticularDetails(DataSource ds, NameRelationChangeForm nameRelationChangeForm, String requestId) throws SADAREMDBException, SQLException {

        int updateParticularDetails = 0;

        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        String updateQuery = null;
        String errormsg = "NO";
        NameRelationChangeDTO dto = new NameRelationChangeDTO();


        try {
            dto.setError_msg(errormsg);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            if (requestId.equals("1"))
            {
            	
                query = "UPDATE a SET a.first_name = ?,a.personname_telugu =?,a.updated_Date=getDate()"
                        + " FROM tblPerson_Personal_Details a  INNER JOIN RequestDetails b "
                        + " ON (b.person_code = a.person_code) Inner Join requesttypemapping c "
                        + " on(c.requestid =b.requestid) where c.requesttypeid =?"
                        + " and b.requestid =?";


                pstmt = con.prepareStatement(query);
                pstmt.setString(1, nameRelationChangeForm.getPopulatenewName());
                pstmt.setString(2, nameRelationChangeForm.getTelugupersonname());
                pstmt.setString(3, requestId);
                pstmt.setString(4, nameRelationChangeForm.getParticularRequestIdDetails());

                if(pstmt.executeUpdate()>0)
                {
                	query = "update  requesttypemapping set updatedpersonstatus ='PersonUpdate',updated_date=getDate(),loginid= ?"
                            + " where requestid =?  and requesttypeid =?";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, nameRelationChangeForm.getLoginId());
                    pstmt.setString(2, nameRelationChangeForm.getParticularRequestIdDetails());
                    pstmt.setString(3, requestId);
                    if(pstmt.executeUpdate()>0)
                    {
                    	query = "update  RequestDetails set updated_Date=getDate() "
                                + " where requestid =?  and requesttypeid =?";
                        pstmt = con.prepareStatement(query);
                        pstmt.setInt(1, Integer.parseInt(nameRelationChangeForm.getParticularRequestIdDetails()));
                        pstmt.setString(2, requestId);
                        if(pstmt.executeUpdate()>0)
                        {
                        	updateParticularDetails = 1;
                        	con.commit();
                        }
                        else
                        {
                            errormsg = "Error in Updating";
                            dto.setError_msg(errormsg);
                        }
                    }
                    else
                    {
                        errormsg = "Error in Updating";
                        dto.setError_msg(errormsg);
                    }
                    
                }
                else
                {
                    errormsg = "Error in Updating";
                    dto.setError_msg(errormsg);
                }
            }

            if (requestId.equals("2"))
            {

                query = "UPDATE a SET a.relation_name = ?,a.fathername_telugu =?,"
                        + " a.updated_Date=getDate(),a.relationship =? FROM tblPerson_Personal_Details a INNER JOIN RequestDetails b ON "
                        + " (b.person_code = a.person_code) Inner Join requesttypemapping c "
                        + " on(c.requestid =b.requestid) where c.requesttypeid =? and b.requestid =?";

                pstmt = con.prepareStatement(query);
                pstmt.setString(1, nameRelationChangeForm.getPopulatenewRelationName());
                pstmt.setString(2, nameRelationChangeForm.getTelugufathername());
                pstmt.setString(3, nameRelationChangeForm.getGrelation());
                pstmt.setString(4, requestId);
                pstmt.setString(5, nameRelationChangeForm.getParticularRequestIdDetails());
                if(pstmt.executeUpdate()>0)
                {
                	query = "update  requesttypemapping set updatedpersonstatus ='PersonUpdate',updated_date=getDate() ,loginid= ?"
                            + " where requestid =?  and requesttypeid =?";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, nameRelationChangeForm.getLoginId());
                    pstmt.setInt(2, Integer.parseInt(nameRelationChangeForm.getParticularRequestIdDetails()));
                    pstmt.setString(3, requestId);
                    if(pstmt.executeUpdate()>0)
                    {
                    	 query = "update  RequestDetails set updated_Date=getDate() "
                                 + " where requestid =?  and requesttypeid =?";
                         
                         pstmt = con.prepareStatement(query);
                         pstmt.setInt(1, Integer.parseInt(nameRelationChangeForm.getParticularRequestIdDetails()));
                         pstmt.setString(2, requestId);
                         if(pstmt.executeUpdate()>0)
                         {
                        	 con.commit();
                        	 updateParticularDetails = 1;
                         }
                         else
                         {
                             errormsg = "Error in Updating";
                             dto.setError_msg(errormsg);
                         }                         
                    }
                    else
                    {
                        errormsg = "Error in Updating";
                        dto.setError_msg(errormsg);
                    }
                }
                else
                {
                    errormsg = "Error in Updating";
                    dto.setError_msg(errormsg);
                }
            }

            if (requestId.equals("3"))
            {

                query = "UPDATE a SET a.mole_one = ?,a.mole_two =?,a.updated_Date=getDate() FROM tblPerson_Personal_Details a "
                        + " INNER JOIN RequestDetails b ON (b.person_code = a.person_code) "
                        + " Inner Join requesttypemapping c on(c.requestid =b.requestid) "
                        + " where c.requesttypeid =? and b.requestid =?";

                pstmt = con.prepareStatement(query);
                pstmt.setString(1, nameRelationChangeForm.getPopulatenewMolesOne());
                pstmt.setString(2, nameRelationChangeForm.getPopulatenewMolesTwo());
                pstmt.setString(3, requestId);
                pstmt.setString(4, nameRelationChangeForm.getParticularRequestIdDetails());
                if(pstmt.executeUpdate()>0)
                {
                	query = "update  requesttypemapping set updatedpersonstatus ='PersonUpdate' ,updated_date=getDate(),loginid= ?"
                            + " where requestid =?  and requesttypeid =?";

                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, nameRelationChangeForm.getLoginId());
                    pstmt.setString(2, nameRelationChangeForm.getParticularRequestIdDetails());
                    pstmt.setString(3, requestId);
                    if(pstmt.executeUpdate()>0)
                    {
                    	con.commit();
                    	updateParticularDetails = 1;
                    }
                    else {
                        errormsg = "Error in Updating";
                        dto.setError_msg(errormsg);
                    }
                }
                else {
                    errormsg = "Error in Updating";
                    dto.setError_msg(errormsg);
                }
                
            }
            if (requestId.equals("4")) {


                Date DOR = new SimpleDateFormat("dd/mm/yyyy").parse(nameRelationChangeForm.getPopulateNewDOB());


                String DateOfBirth = new SimpleDateFormat("yyyy-mm-dd").format(DOR);


                query = "UPDATE a SET a.Date_of_Birth = ?,a.updated_Date=getDate() FROM tblPerson_Personal_Details a  "
                        + " INNER JOIN RequestDetails b ON (b.person_code = a.person_code) "
                        + " Inner Join requesttypemapping c on(c.requestid =b.requestid) "
                        + " where c.requesttypeid =? and b.requestid =?";

                pstmt = con.prepareStatement(query);
                pstmt.setString(1, DateOfBirth);
                pstmt.setString(2, requestId);
                pstmt.setString(3, nameRelationChangeForm.getParticularRequestIdDetails());
                
                if(pstmt.executeUpdate()>0)
                {
                	 query = "update  requesttypemapping set updatedpersonstatus ='PersonUpdate' ,updated_date=getDate(),loginid= ?"
                             + " where requestid =?  and requesttypeid =?";


                     pstmt = con.prepareStatement(query);
                     pstmt.setString(1, nameRelationChangeForm.getLoginId());
                     pstmt.setString(2, nameRelationChangeForm.getParticularRequestIdDetails());
                     pstmt.setString(3, requestId);

                     if(pstmt.executeUpdate()>0)
                     {
                    	 con.commit();
                    	 updateParticularDetails = 1;
                     }
                     else {
                         errormsg = "Error in Updating";
                         dto.setError_msg(errormsg);
                     }
                }
                else {
                    errormsg = "Error in Updating";
                    dto.setError_msg(errormsg);
                }
               
                
            }

           if (requestId.equals("5"))
           {
                query = "UPDATE a SET a.surname =?,a.personname_telugu =?,a.updated_Date=getDate()"
                        + " FROM tblPerson_Personal_Details a  INNER JOIN RequestDetails b "
                        + " ON (b.person_code = a.person_code) Inner Join requesttypemapping c "
                        + " on(c.requestid =b.requestid) where c.requesttypeid =?"
                        + " and b.requestid =?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, nameRelationChangeForm.getPopulateOldNewName());
                pstmt.setString(2, nameRelationChangeForm.getTelugupersonname());
                pstmt.setString(3, requestId);
                pstmt.setString(4, nameRelationChangeForm.getParticularRequestIdDetails());
                
                if(pstmt.executeUpdate()>0)
                {
                	query = "update  requesttypemapping set updatedpersonstatus ='PersonUpdate' ,updated_date=getDate(),loginid= ?"
                            + " where requestid =?  and requesttypeid =?";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, nameRelationChangeForm.getLoginId());
                    pstmt.setString(2, nameRelationChangeForm.getParticularRequestIdDetails());
                    pstmt.setString(3, requestId);
                    if(pstmt.executeUpdate()>0)
                    {
                    	query = "update  RequestDetails set updated_Date=getDate() "
                                + " where requestid =?  and requesttypeid =?";

                        pstmt = con.prepareStatement(query);
                        pstmt.setInt(1, Integer.parseInt(nameRelationChangeForm.getParticularRequestIdDetails()));
                        pstmt.setString(2, requestId);
                        if(pstmt.executeUpdate()>0)
                        {
                        	con.commit();
                        	updateParticularDetails = 1;
                        }
                        else
                        {
                            errormsg = "Error in Updating";
                            dto.setError_msg(errormsg);
                        }
                    }
                    else
                    {
                        errormsg = "Error in Updating";
                        dto.setError_msg(errormsg);
                    }
                }
                else
                {
                    errormsg = "Error in Updating";
                    dto.setError_msg(errormsg);
                }               
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateParticularDetails", "NameRelationChangeDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "updateParticularDetails");
        } catch (Exception sqlEx) {System.out.println(sqlEx);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateParticularDetails", "NameRelationChangeDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "updateParticularDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);

        }

        return updateParticularDetails;
    }

    public ArrayList NameChangeDetails(DataSource ds, String requestId, String requestType) throws SADAREMDBException, SQLException {

        ArrayList individualNameRelationList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;

        String requestTypeId = null;
        ResultSet rs1 = null;
        int c = 0;
        NameRelationChangeDTO nameRelationChangeDTO = new NameRelationChangeDTO();

        try {
            con = DBConnection.getConnection();

            query = "select c.first_name as OldName,b.name as NewName, "
                    + " a.person_code,b.requestid,b.requesttypeid,c.personname_telugu  from requestDetails a"
                    + " join  requesttypemapping b on(a.requestid =b.requestid)"
                    + " join  tblPerson_Personal_Details c  with (nolock) on(c.person_code =a.person_code)"
                    + " where"
                    + "  b.requestid =? and b.requesttypeid = ? ";
          
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, requestId);
            pstmt.setString(2, requestType);

            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    nameRelationChangeDTO = new NameRelationChangeDTO();
                    nameRelationChangeDTO.setOldName(rs.getString(1));
                    nameRelationChangeDTO.setNewName(rs.getString(2));
                    nameRelationChangeDTO.setRequestpersonCode(rs.getString(3));
                    nameRelationChangeDTO.setRequestChangeId(rs.getString(4));
                    nameRelationChangeDTO.setRequestIndividualTyepId(rs.getString(5));
                        nameRelationChangeDTO.setOldTeluguName(rs.getString("personname_telugu"));
                    individualNameRelationList.add(nameRelationChangeDTO);

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "NameChangeDetails", "NameRelationChangeDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "NameChangeDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "NameChangeDetails", "NameRelationChangeDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "NameChangeDetails");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);  
            DBConnection.closeResultSet(rs1);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return individualNameRelationList;

    }

    public ArrayList RelationChangeDetails(DataSource ds, String requestId, String requestType) throws SADAREMDBException, SQLException {

        ArrayList individualNameRelationList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;

        String requestTypeId = null;
        ResultSet rs1 = null;
        NameRelationChangeDTO nameRelationChangeDTO = new NameRelationChangeDTO();

        try {
            con = DBConnection.getConnection();
            query = "select c.relation_name, b.relationname, a.person_code,b.requestid,b.requesttypeid"
                    + " from requestDetails a join requesttypemapping b on(a.requestid =b.requestid)"
                    + " join tblPerson_Personal_Details c  with (nolock) on(c.person_code =a.person_code) where "
                    + " b.requestid =? and b.requesttypeid = ?";


            pstmt = con.prepareStatement(query);
            pstmt.setString(1, requestId);
            pstmt.setString(2, requestType);
            rs1 = pstmt.executeQuery();

            if (rs1 != null) {
                while (rs1.next()) {
                    nameRelationChangeDTO = new NameRelationChangeDTO();
                    nameRelationChangeDTO.setOldRelationName(rs1.getString(1));
                    nameRelationChangeDTO.setNewRelationName(rs1.getString(2));
                    nameRelationChangeDTO.setRequestpersonCode(rs1.getString(3));
                    nameRelationChangeDTO.setRequestChangeId(rs1.getString(4));
                    nameRelationChangeDTO.setRequestIndividualTyepId(rs1.getString(5));
                    individualNameRelationList.add(nameRelationChangeDTO);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "RelationChangeDetails", "NameRelationChangeDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "RelationChangeDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "RelationChangeDetails", "NameRelationChangeDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "RelationChangeDetails");
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);  
            DBConnection.closeResultSet(rs1);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return individualNameRelationList;

    }

    public ArrayList MoleChangeDetails(DataSource ds, String requestId, String requestType) throws SADAREMDBException, SQLException {

        ArrayList individualNameRelationList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;

        String requestTypeId = null;
        ResultSet rs1 = null;
        NameRelationChangeDTO nameRelationChangeDTO = new NameRelationChangeDTO();

        try {
            con = DBConnection.getConnection();

            query = "select  c.Mole_One,c.Mole_Two,b.MolesOne,b.MolesTwo ,  "
                    + " a.person_code,b.requestid,b.requesttypeid from requestDetails a"
                    + " join  requesttypemapping b on(a.requestid =b.requestid)"
                    + " join  tblPerson_Personal_Details c  with (nolock) on(c.person_code =a.person_code)"
                    + " where"
                    + "  b.requestid =? and b.requesttypeid = ?";



            pstmt = con.prepareStatement(query);
            pstmt.setString(1, requestId);
            pstmt.setString(2, requestType);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    nameRelationChangeDTO = new NameRelationChangeDTO();
                    nameRelationChangeDTO.setOldmolesOne(rs.getString(1));
                    nameRelationChangeDTO.setOldmolesTwo(rs.getString(2));
                    nameRelationChangeDTO.setNewmoleOne(rs.getString(3));
                    nameRelationChangeDTO.setNewmolesTwo(rs.getString(4));
                    nameRelationChangeDTO.setRequestpersonCode(rs.getString(5));
                    nameRelationChangeDTO.setRequestChangeId(rs.getString(6));
                    nameRelationChangeDTO.setRequestIndividualTyepId(rs.getString(7));
                    individualNameRelationList.add(nameRelationChangeDTO);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "MoleChangeDetails", "NameRelationChangeDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "MoleChangeDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "MoleChangeDetails", "NameRelationChangeDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "MoleChangeDetails");
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);  
            DBConnection.closeResultSet(rs1);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return individualNameRelationList;

    }

    public ArrayList DOBChangeDetails(DataSource ds, String requestId, String requestType) throws SADAREMDBException, SQLException {

        ArrayList individualNameRelationList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;

        String requestTypeId = null;
        ResultSet rs1 = null;
        NameRelationChangeDTO nameRelationChangeDTO = new NameRelationChangeDTO();

        try {
            con = DBConnection.getConnection();

            query = "select  c.Date_of_Birth,b.DateOfBirth , "
                    + " a.person_code,b.requestid,b.requesttypeid from requestDetails a"
                    + " join  requesttypemapping b on(a.requestid =b.requestid)"
                    + " join  tblPerson_Personal_Details c  with (nolock) on(c.person_code =a.person_code)"
                    + " where"
                    + "  b.requestid =? and b.requesttypeid = ?";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, requestId);
            pstmt.setString(2, requestType);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    nameRelationChangeDTO = new NameRelationChangeDTO();
                    String reqDate = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(1)));
                    nameRelationChangeDTO.setOldDOB(reqDate);
                    String reqDate1 = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(2)));
                    nameRelationChangeDTO.setNewDOB(reqDate1);
                    nameRelationChangeDTO.setRequestpersonCode(rs.getString(3));
                    nameRelationChangeDTO.setRequestChangeId(rs.getString(4));
                    nameRelationChangeDTO.setRequestIndividualTyepId(rs.getString(5));
                    individualNameRelationList.add(nameRelationChangeDTO);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "DOBChangeDetails", "NameRelationChangeDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "DOBChangeDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "DOBChangeDetails", "NameRelationChangeDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "DOBChangeDetails");
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);  
            DBConnection.closeResultSet(rs1);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return individualNameRelationList;

    }

    public String getPersonCodeDetails(DataSource ds, String requestId) throws SADAREMDBException, SQLException {

        //ArrayList individualNameRelationList = new ArrayList();

        String individualNameRelationList = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        ResultSet rs1 = null;
        NameRelationChangeDTO nameRelationChangeDTO = new NameRelationChangeDTO();

        try {
            con = DBConnection.getConnection();
            query = "select a.person_code,b.requesttypeid,a.requestid,c.requesttypename"
                    + " from requestDetails a  join requesttypemapping b on(a.requestid =b.requestid)"
                    + " join tblrequesttypemaster c on(c.requesttypeid =b.requesttypeid) where a.requestid =?"
                    + " and b.requeststatus ='Approval' and b.updatedpersonstatus is null";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, requestId);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    //peronCode = rs.getString(1);
                    individualNameRelationList = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonCodeDetails", "NameRelationChangeDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "getPersonCodeDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonCodeDetails", "NameRelationChangeDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "getPersonCodeDetails");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);  
            DBConnection.closeResultSet(rs1);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return individualNameRelationList;
    }

    public ArrayList getEmailBodyDetails(DataSource ds, String personCode, String requestId) throws SADAREMDBException, SQLException {

        ArrayList emailList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;

        NameRelationChangeDTO nameRelationChangeDTO = null;

        try {

            con = DBConnection.getConnection();


            query = "select distinct a.surname + space (1) + a.first_name Oldname,"
                    + " h.surname + space (1) + h.name Newname,a.relation_name oldrelationname,h.relationname newrelationname,h.requesttypeid,"
                    + " a.pensioncard_no,  a.person_code, b.houseno,c.district_name,d.mandal_name,e.village_name,"
                    + " f.habitation_name, g.name,g.address,b.surname + space (1) + b.name as Personalname,"
                    + " b.relationname as personalRelationname,i.requesttypename,b.created_Date,i.requesttypename from dbo.tblPerson_Personal_Details a  with (nolock) ,"
                    + " dbo.RequestDetails b,dbo.tblDistrict_Details c,dbo.tblMandal_Details d,dbo.tblVillage_Details e,"
                    + " dbo.tblHabitation_Details f,dbo.tblCamp_Details g,Requesttypemapping h,tblrequesttypemaster i"
                    + " where a.person_code = b.person_code and b.districtid = c.district_id and"
                    + " b.districtid = d.district_id and b.mandalid =  d.mandal_id and "
                    + " b.districtid = e.district_id and b.mandalid =  e.mandal_id and  "
                    + " b.villageid = e.village_id and b.districtid = f.district_id and "
                    + " b.mandalid =  f.mandal_id and b.villageid = f.village_id and "
                    + " b.habitationid = f.habitation_id and a.district_id = g.district_id and"
                    + " a.camp_id = g.camp_id and b.requestid =h.requestid and  h.requesttypeid  = i.requesttypeid  and"
                    + " a.person_code = ? and b.requestid =?";


            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personCode);
            pstmt.setString(2, requestId);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    emailList.add(rs.getString(1));
                    emailList.add(rs.getString(2));
                    emailList.add(rs.getString(3));
                    emailList.add(rs.getString(4));
                    emailList.add(rs.getString(5));
                    emailList.add(rs.getString(6));
                    emailList.add(rs.getString(7));
                    emailList.add(rs.getString(8));
                    emailList.add(rs.getString(9));
                    emailList.add(rs.getString(10));
                    emailList.add(rs.getString(11));
                    emailList.add(rs.getString(12));
                    emailList.add(rs.getString(13));
                    emailList.add(rs.getString(14));
                    emailList.add(rs.getString(15));
                    emailList.add(rs.getString(16));
                    emailList.add(rs.getString(17));
                    emailList.add(rs.getString(18));
                    emailList.add(rs.getString(19));

                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getEmailBodyDetails", "NameRelationChangeDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "getEmailBodyDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getEmailBodyDetails", "NameRelationChangeDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "getEmailBodyDetails");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);  
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }


        return emailList;
    }

    public String emailpersonDetails(DataSource ds, String username, String districtId) throws SADAREMDBException, SQLException {

        String emailPersonName = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            query = "select personname from login_Details where username =? and district_id =?";
            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, districtId);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    emailPersonName = rs.getString(1);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "emailpersonDetails", "NameRelationChangeDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "emailpersonDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "emailpersonDetails", "NameRelationChangeDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "emailpersonDetails");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);  
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return emailPersonName;
    }

    public ArrayList getEmailOldNameRelationDetails(DataSource ds, String personCode) throws SADAREMDBException, SQLException {

        ArrayList emailOldRelationName = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            query = "select surname+space(2)+first_name as oldname,relation_name as oldrelationname "
                    + " from tblperson_personal_Details   with (nolock) where person_code =?";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personCode);

            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    emailOldRelationName.add(rs.getString(1));
                    emailOldRelationName.add(rs.getString(2));
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getEmailOldNameRelationDetails", "NameRelationChangeDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "getEmailOldNameRelationDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getEmailOldNameRelationDetails", "NameRelationChangeDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "getEmailOldNameRelationDetails");
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);  
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return emailOldRelationName;

    }

    public String dOBDetails(DataSource ds, String personCode) throws SADAREMDBException, SQLException {

        String dOBDetails = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            query = "select Disability_id  from tblperson_Disability_Details "
                    + " where person_code =? and status ='Active'";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personCode);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    dOBDetails = rs.getString(1);
                }

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "dOBDetails", "NameRelationChangeDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "dOBDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "dOBDetails", "NameRelationChangeDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "dOBDetails");
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);  
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return dOBDetails;
    }

    public ArrayList getData(DataSource ds, String district_id, String status) throws SADAREMDBException, SQLException {
        ArrayList requestDataList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        Map requestData = null;
        try {

            con = DBConnection.getConnection();
            query = "select  distinct a.person_code,a.requestid,a.name,a.relationname,c.district_name, "
                    + " d.mandal_name,e.village_name,f.habitation_name,convert(varchar,a.updated_date,103) updated_date,convert(varchar,a.created_date,103) created_date  from requestDetails a  "
                    + " join tblPerson_Personal_Details b  with (nolock) on(a.person_code =b.person_code)  "
                    + " join tblDistrict_Details c on(c.district_id =a.districtid) "
                    + " join tblmandal_Details d on(d.district_id=a.districtid and d.mandal_id =a.mandalid)"
                    + " join tblvillage_Details e on (a.districtid=e.district_id "
                    + " and a.mandalid =e.mandal_id and a.villageid=e.village_id)"
                    + " join tblhabitation_Details f on (a.districtid =f.district_id "
                    + " and a.mandalid =f.mandal_id and a.villageid =f.village_id "
                    + " and a.habitationid =f.habitation_id) join requesttypemapping g "
                    + " on(a.requestid =g.requestid) where g.requeststatus ='Approval'"
                    + "  and g.requesttypeid in('1','2','3','4') and a.status='Active' ";

            if (district_id != null && !district_id.equalsIgnoreCase("") && !district_id.equals("0")) {
                query = query + " and c.district_id=?";
            }
            if (status != null && !status.equalsIgnoreCase("")) {
                if (status.equals("1")) {
                    query = query + "and g.updatedpersonstatus is null";
                } else if (status.equals("2")) {
                    query = query + "and g.updatedpersonstatus is not null";
                }
            }
            pstmt = con.prepareStatement(query);
            int count = 0;
            if (district_id != null && !district_id.equalsIgnoreCase("") && !district_id.equals("0"))
            {                
                pstmt.setString(1, district_id);
            }
           
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    requestData = new HashMap();
                    requestData.put("personCode", rs.getString(1));
                    requestData.put("requestId", rs.getString(2));
                    if (rs.getString(3) != null && !rs.getString(3).equals("")) {
                        requestData.put("name", rs.getString(3));
                    } else {
                        requestData.put("name", "-");
                    }
                    if (rs.getString(4) != null && !rs.getString(4).equals("")) {
                        requestData.put("relationName", rs.getString(4));
                    } else {
                        requestData.put("relationName", "-");
                    }
                    requestData.put("districtName", rs.getString(5));
                    requestData.put("mandalName", rs.getString(6));
                    requestData.put("villageName", rs.getString(7));
                    requestData.put("habitationName", rs.getString(8));
                    requestData.put("updated_date", rs.getString(9));
                    requestData.put("created_date", rs.getString(10));
                    requestDataList.add(requestData);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getData", "NameRelationChangeDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "getData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getData", "NameRelationChangeDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "getData");
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);  
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return requestDataList;

    }

    public ArrayList surNameChangeDetails(DataSource ds, String requestId, String requestType) throws SADAREMDBException, SQLException {

        ArrayList individualNameRelationList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        String requestTypeId = null;
        ResultSet rs1 = null;
        int c = 0;
        NameRelationChangeDTO nameRelationChangeDTO = new NameRelationChangeDTO();

        try {
            con = DBConnection.getConnection();

            query = "select  c.surname,b.surname as Surname,"
                    + " a.person_code,b.requestid,b.requesttypeid,c.personname_telugu from requestDetails a"
                    + " join  requesttypemapping b on(a.requestid =b.requestid)"
                    + " join  tblPerson_Personal_Details c  with (nolock) on(c.person_code =a.person_code)"
                    + " where b.requestid =? and b.requesttypeid = ? ";

            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, requestId);
            pstmt.setString(2, requestType);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    nameRelationChangeDTO = new NameRelationChangeDTO();
                    nameRelationChangeDTO.setOldsurName(rs.getString(1));
                    nameRelationChangeDTO.setNewsurName(rs.getString(2));
                    nameRelationChangeDTO.setRequestpersonCode(rs.getString(3));
                    nameRelationChangeDTO.setRequestChangeId(rs.getString(4));
                    nameRelationChangeDTO.setRequestIndividualTyepId(rs.getString(5));
                    nameRelationChangeDTO.setOldTeluguName(rs.getString("personname_telugu"));
                    individualNameRelationList.add(nameRelationChangeDTO);

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "surNameChangeDetails", "NameRelationChangeDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "surNameChangeDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "surNameChangeDetails", "NameRelationChangeDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NameRelationChangeDAO", "surNameChangeDetails");
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);  
            DBConnection.closeResultSet(rs1);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return individualNameRelationList;

    }
}
