/*
 * PersonCodeCheckDAO.java
 *
 * Created on July 7, 2008, 12:36 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is for checking person code in database
 *
 * @author Devi Prasad .T
 * @version 1.0
 */
public class PersonCodeCheckDAO {

    /**
     *
     * @param personcode
     * @param ds
     * @throws org.bf.disability.Exception.SADAREMException
     * @return int
     */
    public boolean checkPersonCode(String personcode, DataSource ds) throws SADAREMDBException, SQLException {
        boolean flag = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            String query = "select Person_Code from Pension_for_Disabilities.dbo.tblPerson_Personal_Details   with (nolock)  where Person_Code=?";
            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();


            if (rs.next() == false) {
                flag = false;

            } else {
                flag = true;



            }
        } catch (SQLException sqlEx) {System.out.println(sqlEx);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersonCode", "PersonCodeCheckDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "checkPersonCode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersonCode", "PersonCodeCheckDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "checkPersonCode");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return flag;
    }

    /**
     *
     * @param presoncode
     * @param ds
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getCalculatedValues(String presoncode, DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList calculatedValueslist = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            String query = "";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCalculatedValues", "PersonCodeCheckDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "getCalculatedValues");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCalculatedValues", "PersonCodeCheckDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "getCalculatedValues");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);


        }
        return calculatedValueslist;
    }

    /**
     * RationCard Details from Web Services *
     */
    public ArrayList ExistingRationCardPerson(String rationid, String district_id, DataSource ds) throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        ArrayList list = new ArrayList();

        try {
            con = DBConnection.getConnection();
            
            query = "select person_code,surname + space(1) + first_name,Relation_name,case when gender='1' then 'Male' else 'Female' end as gender,"
                    + "CONVERT(VARCHAR(10), Date_of_birth, 103) AS date_of_birth,age_years from tblPerson_Personal_Details with (nolock) where lower(rationcard_number)=lower('" + rationid + "') and (rationcard_slno is null or rationcard_slno=0) "
                    + "and district_id=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, district_id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                HashMap<String, Object> members = new HashMap<String, Object>();
                members.put("person_code", rs.getString(1));
                members.put("name", rs.getString(2));
                members.put("Relation_name", rs.getString(3));
                members.put("gender", rs.getString(4));
                members.put("date_of_birth", rs.getString(5));
                members.put("age_years", rs.getString(6));
                list.add(members);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "ExistingRationCardPerson", "PersonCodeCheckDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "ExistingRationCardPerson");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "ExistingRationCardPerson", "PersonCodeCheckDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "ExistingRationCardPerson");
        } finally {
            
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);

        }
        return list;
    }

    public String getRationCardSerialNoStatus(DataSource ds, String rationCardId, String district_id) throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        String rationCardDetails = null;

        try {
            con = DBConnection.getConnection();
            
            query = "select rationcard_slno from tblPerson_Personal_Details   with (nolock) where rationcard_number=?"
                    + "and district_id=?";//and sno is not null
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, rationCardId);
            pstmt.setString(2, district_id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                rationCardDetails = rs.getString(1);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRationCardSerialNoStatus", "PersonCodeCheckDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "getRationCardSerialNoStatus");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRationCardSerialNoStatus", "PersonCodeCheckDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "getRationCardSerialNoStatus");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return rationCardDetails;
    }

    public ArrayList rationCardCheckWithWebService(DataSource ds, String rationCardId, String slNo, String district_id) throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        ArrayList rationCardDetails = new ArrayList();

        try {
            con = DBConnection.getConnection();
            
            query = "select person_code,first_name from tblperson_personal_details   with (nolock) where rationcard_number=? and "
                    + "rationcard_slno=? and district_id=?  and ReasonforPersonStatus not in (?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, rationCardId);
            pstmt.setString(2, slNo);
            pstmt.setString(3, district_id);
            pstmt.setString(4, CommonConstants.ADDRESSCHANGE);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                rationCardDetails.add(rs.getString(1));
                rationCardDetails.add(rs.getString(2));
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "rationCardCheckWithWebService", "PersonCodeCheckDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "rationCardCheckWithWebService");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "rationCardCheckWithWebService", "PersonCodeCheckDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "rationCardCheckWithWebService");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);



        }
        return rationCardDetails;
    }

    public int getMaximumSerialNumber(DataSource ds, String rationCardNumber, HttpServletRequest request) throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        int maxno = -1;
        String tablename = "  ";
        HttpSession session = request.getSession(true);

        String districtid = (String) session.getAttribute("districtId");
        List<String> dist = getIssueRaisingRationCard(ds, rationCardNumber);
        if (dist.size() > 0 && !dist.get(1).equals("")) {
            districtid = dist.get(1);
        }

        try {
            con = DBConnection.getConnection();

            if (districtid != null) {
                tablename = " CivilSupply_Database.dbo.tblRationcard_details_" + districtid;

            }

            query = "select max(slno) from  " + tablename + " where  householdcardno=? ";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, rationCardNumber);
            rs = pstmt.executeQuery();
            if (rs.next()) {

                if (rs.getInt(1) == 0) {
                    if (dist.size() > 0 && !dist.get(0).equals("")) {
                        districtid = dist.get(0);
                    } else {
                        districtid = rationCardNumber.substring(3, 5);
                    }


                    query = "select max(slno) from  " + " CivilSupply_Database.dbo.tblRationcard_details_" + districtid + " where  householdcardno=? ";

                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, rationCardNumber);
                    ResultSet rs1 = pstmt.executeQuery();
                    if (rs1.next()) {
                        maxno = rs1.getInt(1);
                    }
                } else {
                    maxno = rs.getInt(1);
                }

            }

        } catch (SQLException sqlEx) {System.out.println(sqlEx);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaximumSerialNumber", "PersonCodeCheckDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "getMaximumSerialNumber");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaximumSerialNumber", "PersonCodeCheckDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "getMaximumSerialNumber");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);

            DBConnection.closeResultSet(rs);
        }
        return maxno;
    }

    public synchronized  int UpdateRationCardSerialnumber(String ration, String personCode, String slNo, DataSource ds) throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        int a = 0;

        try {
            con = DBConnection.getConnection();
            query = "select *from  tblPerson_Personal_Details  with (nolock) where rationcard_number=? and rationcard_slno=?" ;
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, ration);
            pstmt.setString(2, slNo);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                query = "update tblPerson_Personal_Details set rationcard_slno=? where person_code=?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, slNo);
                pstmt.setString(2, personCode);

                a = pstmt.executeUpdate();
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "UpdateRationCardSerialnumber", "PersonCodeCheckDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "UpdateRationCardSerialnumber");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "UpdateRationCardSerialnumber", "PersonCodeCheckDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "UpdateRationCardSerialnumber");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);


        }
        return a;
    }

    /**
     * End *
     */
    public ArrayList getDataFromCivilSuppliesDatabase(DataSource ds, String rationCardNumber, HttpServletRequest request) throws SADAREMDBException, SQLException {
        ArrayList<Object> rationCardDetails = null;

        String sql = null;

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        HashMap<String, Object> rationDetails = null;
        HttpSession session = request.getSession(true);
        rationCardDetails = new ArrayList<Object>();
        Connection connection = null;
        // String tablename=" CivilSupply_Database.civil.CivilFinal a ";
        String tablename = "  ";
        int personCount = 0;
        String districtid = null;
        if (session.getAttribute("districtId") != null) {
            districtid = (String) session.getAttribute("districtId");
        } else {
            districtid = rationCardNumber.substring(3, 5);
        }
        try {
            // conn =DBConnection.getConnection();


            if (districtid != null) {
                tablename = " CivilSupply_Database.dbo.tblRationcard_details_" + districtid + " a ";
            }

            personCount = getPersonCount(ds, rationCardNumber); // CHECK PERSON'S COUNT

            sql = "select  a.householdcardno,a.slno,a.membername,coalesce(a.relation,'-'),coalesce(a.gender,'-'),coalesce(a.age,'0'),"
                    + " substring(a.householdcardno,4,2) as district,b.rationcard_slno,"
                    + " case when b.view_edit_mode='View' and b.ReasonforPersonStatus not in (?) then 'Completed' else 'Not Completed' end as assStatus, b.pensioncard_no, "
                    + " b.person_code,b.ReasonforPersonStatus,a.aadharNo from   dbo.tblperson_personal_details b with (nolock) "
                    + " right join " + tablename + " with (nolock)  "
                    + " on(a.slno=b.rationcard_slno and lower(b.rationcard_number)=lower(?)) "
                    + " where lower(a.householdcardno)=lower(?) and a.membername is not null and a.slno is not null";


            // Civil Supplies
            connection = DBConnection.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, CommonConstants.ADDRESSCHANGE);
            pstmt.setString(2, rationCardNumber);
            pstmt.setString(3, rationCardNumber);
            rs = pstmt.executeQuery();
            boolean head0or1 = false;
            if (rs != null) {
                while (rs.next()) {
                    rationDetails = new HashMap<String, Object>();
                    rationDetails.put("rationCardNo", rs.getString(1));

                    if ((rs.getString(2) != null && (rs.getString(2).equalsIgnoreCase("0") || rs.getString(2).equalsIgnoreCase("1")))) {

                        if (personCount > 0) { // Check the person Count

                            head0or1 = true;
                            rationDetails.put("imgs", "<img src=\"./DisabilityUITG/images/right.JPG\" height=\"18\">");
                            rationDetails.put("go", "<font color=green><b>Done</b></font></a>");
                            if (rs.getString(10) != null) {
                                rationDetails.put("pen", rs.getString(10));
                            } else {
                                rationDetails.put("pen", "-");
                            }
                            if (rs.getString(11) != null) {
                                rationDetails.put("sad", rs.getString(11));
                            } else {
                                rationDetails.put("sad", "-");
                            }
                        } else {
                            rationDetails.put("pen", "-");
                            rationDetails.put("sad", "-");
                            rationDetails.put("imgs", "<img src=\"./DisabilityUITG/images/cross.JPG\" height=\"18\">");
                            rationDetails.put("go", "<a href=\"./MemberDetails.do?mode=getWebDetails&reset=reset&rCardSlno=" + rs.getString(2) + "&name=" + rs.getString(3) + "&age=" + rs.getString(6) + "&gender=" + rs.getString(5) + "&district=" + rs.getString(7) + "&rationCardNo=" + rs.getString(1) + "&relationName=" + rs.getString(4) + "\"><font color=red><b>Go</b></font></a>");
                        }

                    } else {

                        if (rs.getString(2) != null && rs.getString(2).equals(rs.getString(8))) {
                            
                            if (rs.getString(10) != null) {
                                rationDetails.put("pen", rs.getString(10));
                            } else {
                                rationDetails.put("pen", "-");
                            }
                            if (rs.getString(11) != null) {
                                rationDetails.put("sad", rs.getString(11));
                            } else {
                                rationDetails.put("sad", "-");
                            }
if (rs.getString(12) != null && rs.getString(12).equalsIgnoreCase(CommonConstants.ADDRESSCHANGE)) {
                                rationDetails.put("imgs", "<img src=\"./DisabilityUITG/images/cross.JPG\" height=\"18\">");
                                rationDetails.put("go", "<a href=\"./MemberDetails.do?mode=getWebDetails&reset=reset&rCardSlno=" + rs.getString(2) + "&name=" + rs.getString(3) + "&age=" + rs.getString(6) + "&gender=" + rs.getString(5) + "&district=" + rs.getString(7) + "&rationCardNo=" + rs.getString(1) + "&relationName=" + rs.getString(4) + "\"><font color=red><b>Go</b></font></a>");
rationDetails.put("sad", "-");
                            } else {
                                rationDetails.put("imgs", "<img src=\"./DisabilityUITG/images/right.JPG\" height=\"18\">");
                                rationDetails.put("go", "<font color=green><b>Done</b></font></a>");
                            }
                        } else if (rs.getString(2) != null) {
                            rationDetails.put("pen", "-");
                            rationDetails.put("sad", "-");
                            rationDetails.put("imgs", "<img src=\"./DisabilityUITG/images/cross.JPG\" height=\"18\">");
                            rationDetails.put("go", "<a href=\"./MemberDetails.do?mode=getWebDetails&reset=reset&rCardSlno=" + rs.getString(2) + "&name=" + rs.getString(3) + "&age=" + rs.getString(6) + "&gender=" + rs.getString(5) + "&district=" + rs.getString(7) + "&rationCardNo=" + rs.getString(1) + "&relationName=" + rs.getString(4) + "\"><font color=red><b>Go</b></font></a>");
                        } else {
                            rationDetails.put("imgs", "<img src=\"./DisabilityUITG/images/right.JPG\" height=\"18\">");
                            rationDetails.put("go", "<font color=green><b>Done</b></font></a>");
                        }
                    }

                    rationDetails.put("relationSlno", rs.getString(2));
                    rationDetails.put("name", rs.getString(3));
                    rationDetails.put("relation", rs.getString(4));
                    rationDetails.put("gender", rs.getString(5));
                    rationDetails.put("Age", rs.getString(6));
                    rationDetails.put("district", rs.getString(7));
                    rationDetails.put("assStatus", rs.getString(9));
                    rationDetails.put("aadharCardNo", rs.getString(13));
                    rationCardDetails.add(rationDetails);
                }
            }

        } catch (SQLException sqlEx) {System.out.println(sqlEx);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDataFromCivilSuppliesDatabase", "PersonCodeCheckDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "getDataFromCivilSuppliesDatabase");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDataFromCivilSuppliesDatabase", "PersonCodeCheckDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "getDataFromCivilSuppliesDatabase");
        } finally {
            try {

                if (connection != null) {
                    connection.close();
                }

                if (pstmt != null) {
                	pstmt.close();
                }

                if (rs != null) {
                    rs.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return rationCardDetails;
    }

    public int getDataFromCivil(DataSource ds, String rationCardNumber, String slno, HttpServletRequest request) throws SADAREMDBException, SQLException {

        String ratonCivil = null;
        String sql = null;

        //Statement st = null;
        ResultSet rs = null;
        HashMap<String, Object> rationDetails = null;
        HttpSession session = request.getSession(true);
        ArrayList rationCardDetails = new ArrayList<Object>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        int personCount = 0;
        ResultSet rs2 = null;
        String districtid = (String) session.getAttribute("districtId");

        try {
            // conn = DataBasePlugin.getConnection();
            conn = DBConnection.getConnection();
            sql = "select count(person_code) from tblperson_personal_details with(nolock) where rationcard_number=? and rationcard_slno =?  and ReasonforPersonStatus not in (?)";


            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rationCardNumber);
            pstmt.setString(2, slno);
            pstmt.setString(3, CommonConstants.ADDRESSCHANGE);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    personCount = rs.getInt(1);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDataFromCivil", "PersonCodeCheckDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "getDataFromCivil");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDataFromCivil", "PersonCodeCheckDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "getDataFromCivil");
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }

                if (pstmt != null) {
                	pstmt.close();
                }

                if (rs != null) {
                    rs.close();
                }
                if (rs2 != null) {
                    rs2.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return personCount;
    }

    public ArrayList checkPersonDetails(DataSource ds, String rationCardNumber, String slno) throws SADAREMDBException, SQLException {


        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        ArrayList personDetails = new ArrayList();

        int i = 0;
        try {
            // conn = DataBasePlugin.getConnection();
            conn = DBConnection.getConnection();



            sql = "select pensioncard_no,person_code,"
                    + " case when view_edit_mode='View' then 'Completed' else 'Not Completed' end as assStatus,Surname,First_Name"
                    + " from tblperson_personal_details "
                    + " where lower(rationCard_number) =lower(?) and rationcard_slno =?";


            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rationCardNumber);
            pstmt.setString(2, slno);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    i = 1;
                    personDetails.add(rs.getString(1));
                    personDetails.add(rs.getString(2));
                    personDetails.add(rs.getString(3));
                    String name = "";
                    if (rs.getString(4) != null) {
                        name = rs.getString(4) + " ";
                    }

                    if (rs.getString(5) != null) {
                        name = name + rs.getString(5);
                    } else {
                        name = "-";
                    }
                    personDetails.add(name);
                }
            }

            if (i == 0) {
                sql = "select pensioncard_no,person_code,"
                        + " case when view_edit_mode='View' then 'Completed' else 'Not Completed' end as assStatus,Surname,First_Name"
                        + " from tblperson_personal_details_new "
                        + " where lower(rationCard_number) =lower(?) and rationcard_slno =?";


                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, rationCardNumber);
                pstmt.setString(2, slno);
                rs = pstmt.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        i = 1;
                        personDetails.add(rs.getString(1));
                        personDetails.add(rs.getString(2));
                        personDetails.add(rs.getString(3));
                        String name = "";
                        if (rs.getString(4) != null) {
                            name = rs.getString(4) + " ";
                        }

                        if (rs.getString(5) != null) {
                            name = name + rs.getString(5);
                        } else {
                            name = "-";
                        }
                        personDetails.add(name);
                    }
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersonDetails", "PersonCodeCheckDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "checkPersonDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersonDetails", "PersonCodeCheckDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "checkPersonDetails");
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }

                if (rs != null) {
                    rs.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return personDetails;
    }

    public ArrayList getDataFromCivilSuppliesDatabaseNew(DataSource ds, DataSource civilDs, String rationCardNumber, HttpServletRequest request) throws SADAREMDBException, SQLException {
        ArrayList<Object> rationCardDetails = null;

        String sql = null;
        Connection conn = null;

        PreparedStatement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ResultSet rss = null;
        HashMap<String, Object> rationDetails = null;
        HttpSession session = request.getSession(true);
        String query = null;
        rationCardDetails = new ArrayList<Object>();
        // String tablename=" CivilSupply_Database.civil.CivilFinal a ";
        String tablename = "  ";

        String districtid = rationCardNumber.substring(3, 5);

        try {
            conn = DBConnection.getConnection();



            if (districtid != null) {
                tablename = " CivilSupply_Database.dbo.tblRationcard_details_" + districtid + " a ";
            }


            sql = "select  a.householdcardno,a.slno,a.membername,coalesce(a.relation,'-'),coalesce(a.gender,'-'),coalesce(a.age,'0'),"
                    + "substring(a.householdcardno,4,2) as district,b.rationcard_slno,"
                    + "case when b.view_edit_mode='View' then 'Completed' else 'Not Completed' end as assStatus, b.pensioncard_no, "
                    + " b.person_code from   dbo.tblperson_personal_details b             with(nolock) "
                    + " right join " + tablename + "    with(nolock)  "
                    + "on(a.slno=b.rationcard_slno and b.rationcard_number=?) "
                    + "where a.householdcardno=? and a.membername is not null and a.slno is not null;";



            // Civil Supplies
            st = conn.prepareStatement(sql);
            st.setString(1, rationCardNumber);
            st.setString(2, rationCardNumber);
            rs = st.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    rationDetails = new HashMap<String, Object>();
                    rationDetails.put("rationCardNo", rs.getString(1));
                    String pension = rs.getString(10);
                    String saderm = rs.getString(11);
                    rationDetails.put("pen", "-");
                    rationDetails.put("sad", "-");
                    rationDetails.put("relationSlno", "-");
                    rationDetails.put("name", "-");
                    rationDetails.put("relation", "-");
                    rationDetails.put("gender", "-");
                    rationDetails.put("Age", "-");

                    if ((rs.getString(2) != null && (rs.getString(2).equalsIgnoreCase("0") || rs.getString(2).equalsIgnoreCase("1")))) {
                        pstmt = conn.prepareStatement("select count(person_code) from dbo.tblperson_personal_details with(nolock) where rationcard_number=? and rationcard_slno in('1','0') ");
                        pstmt.setString(1, rationCardNumber);
                        rss = pstmt.executeQuery();
                        if (rss.next()) {


                            if (pension != null && !pension.equalsIgnoreCase("") && !pension.equalsIgnoreCase("null")) {
                                rationDetails.put("pen", pension);
                            } else {
                                rationDetails.put("pen", "-");
                            }
                            if (saderm != null) {
                                rationDetails.put("sad", saderm);
                            } else {
                                rationDetails.put("sad", "-");
                            }
                        }

                    } else {

                        if (rs.getString(2) != null && rs.getString(2).length() < 2 && rs.getString(2).equals(rs.getString(8))) {

                            if (pension != null && !pension.equalsIgnoreCase("") && !pension.equalsIgnoreCase("null")) {
                                rationDetails.put("pen", pension);
                            } else {
                                rationDetails.put("pen", "-");

                            }
                            if (saderm != null) {
                                rationDetails.put("sad", saderm);
                            } else {
                                rationDetails.put("sad", "-");
                            }


                        }

                    }

                    if (rs.getString(2) != null && !rs.getString(2).equalsIgnoreCase("") && !rs.getString(2).equalsIgnoreCase("null")) {

                        rationDetails.put("relationSlno", rs.getString(2));
                    }

                    if (rs.getString(3) != null && !rs.getString(3).equalsIgnoreCase("") && !rs.getString(3).equalsIgnoreCase("null")) {
                        rationDetails.put("name", rs.getString(3));
                    }
                    if (rs.getString(4) != null && !rs.getString(4).equalsIgnoreCase("") && !rs.getString(4).equalsIgnoreCase("null")) {

                        rationDetails.put("relation", rs.getString(4));
                    }
                    if (rs.getString(5) != null && !rs.getString(5).equalsIgnoreCase("") && !rs.getString(5).equalsIgnoreCase("null")) {

                        rationDetails.put("gender", rs.getString(5));
                    }
                    if (rs.getString(6) != null && !rs.getString(6).equalsIgnoreCase("") && !rs.getString(6).equalsIgnoreCase("null")) {

                        rationDetails.put("Age", rs.getString(6));
                    }
                    rationDetails.put("district", rs.getString(7));
                    rationCardDetails.add(rationDetails);
                }
            }

        } catch (SQLException sqlEx) {System.out.println(sqlEx);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDataFromCivilSuppliesDatabaseNew", "PersonCodeCheckDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "getDataFromCivilSuppliesDatabaseNew");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDataFromCivilSuppliesDatabaseNew", "PersonCodeCheckDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "getDataFromCivilSuppliesDatabaseNew");
        } finally {
            try {

                DBConnection.closeConnection(conn);
                DBConnection.closeStatement(st);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rss);
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDataFromCivilSuppliesDatabaseNew", "PersonCodeCheckDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "getDataFromCivilSuppliesDatabaseNew");
            }
        }

        return rationCardDetails;
    }

    // Added by *******  Mohan Reddy on 18/02/2013 ********
    /**
     * This method is for checking the persons count from
     * tbl_person_personalDetails
     *
     * @param rationCardNumber
     * @param con
     * @return count
     * @throws SADAREMDBException,SQLException
     */
    public int getPersonCount(DataSource ds, String rationCardNumber) throws SADAREMDBException, SQLException {
        int count = 0;
        PreparedStatement pstmt = null;
        ResultSet rss = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            pstmt = con.prepareStatement("select count(person_code) from dbo.tblperson_personal_details with(nolock) where rationcard_number=? and rationcard_slno in('1','0')  and ReasonforPersonStatus not in (?)");
            pstmt.setString(1, rationCardNumber);
            pstmt.setString(2, CommonConstants.ADDRESSCHANGE);
            rss = pstmt.executeQuery();
            if (rss != null && rss.next()) {
                count = rss.getInt(1);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonCount", "PersonCodeCheckDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "getPersonCount");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonCount", "PersonCodeCheckDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "getPersonCount");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

                if (rss != null) {
                    rss.close();
                }

                if (pstmt != null) {
                	pstmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public int insertRationCardStatus(DataSource ds, String rationCardNumber, String url, String dataServerDescription, String loginId, String systemIp, String expectionDescription) throws SADAREMDBException, SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = null;
        int rationCardStatus = 0;

        try {
            conn = DBConnection.getConnection();
            query = "INSERT INTO sp_rationCard_details (RationCard,DataServer,DataServerDescription,Created_date,LoginId,SystemIP,expectionDescription)"
                    + " VALUES (?,?,?,getDate(),?,?,?)";


            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, rationCardNumber);
            pstmt.setString(2, url);
            pstmt.setString(3, dataServerDescription);
            pstmt.setString(4, loginId);
            pstmt.setString(5, systemIp);
            pstmt.setString(6, expectionDescription);
            
            //  pstmt.setString(1,rationCardNumber);
            //  pstmt.setString(2,url);
            // pstmt.setString(3,loginId);
            // pstmt.setString(4,systemIp);
            rationCardStatus = pstmt.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rationCardStatus;
    }

    public List<String> getIssueRaisingRationCard(DataSource ds, String rationCardNumber) throws SADAREMDBException, SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = null;
        String newDistrict = "";
        String oldDistrict = "";
        List<String> testList = new ArrayList<String>();
        try {
            conn = DBConnection.getConnection();
            query = "select ExisingDistrictId,NewDistrictId from dbo.RationcardNewAddressDetails "
                    + "where RationCard=? and Status='Active'";

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, rationCardNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    oldDistrict = rs.getString(1);
                    newDistrict = rs.getString(2);

                }
            }

            testList.add(oldDistrict);
            testList.add(newDistrict);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return testList;
    }

    public ArrayList getDataFromCivilDatabaseWithOld(DataSource ds, String rationCardNumber, String districtid) throws SADAREMDBException, SQLException {
        ArrayList<Object> rationCardDetails = null;

        String sql = null;

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        HashMap<String, Object> rationDetails = null;
        rationCardDetails = new ArrayList<Object>();
        Connection connection = null;
        // String tablename=" CivilSupply_Database.civil.CivilFinal a ";
        String tablename = "  ";
        int personCount = 0;


        try {
            // conn =DBConnection.getConnection();


            if (districtid != null) {
                tablename = " CivilSupply_Database.dbo.tblRationcard_details_" + districtid + " a ";
            }

            personCount = getPersonCount(ds, rationCardNumber); // CHECK PERSON'S COUNT

            sql = "select  a.householdcardno,a.slno,a.membername,coalesce(a.relation,'-'),coalesce(a.gender,'-'),coalesce(a.age,'-'),"
                    + "substring(a.householdcardno,4,2) as district,b.rationcard_slno,"
                    + "case when b.view_edit_mode='View' and b.ReasonforPersonStatus not in (?) then 'Completed' else 'Not Completed' end as assStatus, b.pensioncard_no, "
                    + " b.person_code,a.aadharNo from   dbo.tblperson_personal_details b             with(nolock) "
                    + " right join " + tablename + "     with(nolock)  "
                    + "on(a.slno=b.rationcard_slno and lower(b.rationcard_number)=lower(?)) "
                    + "where lower(a.householdcardno)=lower(?) and a.membername is not null and a.slno is not null and (b.ReasonforPersonStatus not in (?)"
                    + " or b.ReasonforPersonStatus  is null)";

            connection = DBConnection.getConnection();
            // Civil Supplies
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,  CommonConstants.ADDRESSCHANGE);
            pstmt.setString(2,  rationCardNumber);
            pstmt.setString(3,  rationCardNumber);
            pstmt.setString(4,  CommonConstants.ADDRESSCHANGE);
            rs = pstmt.executeQuery();
            boolean head0or1 = false;

            if (rs != null) {
                while (rs.next()) {
                    rationDetails = new HashMap<String, Object>();
                    rationDetails.put("rationCardNo", rs.getString(1));

                    if ((rs.getString(2) != null && (rs.getString(2).equalsIgnoreCase("0") || rs.getString(2).equalsIgnoreCase("1")))) {

                        if (personCount > 0) { // Check the person Count

                            head0or1 = true;
                            rationDetails.put("imgs", "<img src=\"./DisabilityUITG/images/right.JPG\" height=\"18\">");
                            rationDetails.put("go", "<font color=green><b>Done</b></font></a>");
                            if (rs.getString(10) != null) {
                                rationDetails.put("pen", rs.getString(10));
                            } else {
                                rationDetails.put("pen", "-");
                            }
                            if (rs.getString(11) != null) {
                                rationDetails.put("sad", rs.getString(11));
                            } else {
                                rationDetails.put("sad", "-");
                            }
                        } else {

                            rationDetails.put("imgs", "<img src=\"./DisabilityUITG/images/cross.JPG\" height=\"18\">");
                            rationDetails.put("go", "<a href=\"./MemberDetails.do?mode=getWebDetails&reset=reset&rCardSlno=" + rs.getString(2) + "&name=" + rs.getString(3) + "&age=" + rs.getString(6) + "&gender=" + rs.getString(5) + "&district=" + rs.getString(7) + "&rationCardNo=" + rs.getString(1) + "&relationName=" + rs.getString(4) + "\"><font color=red><b>Go</b></font></a>");
                        }

                    } else {

                        if (rs.getString(2) != null && rs.getString(2).equals(rs.getString(8))) {
                            rationDetails.put("imgs", "<img src=\"./DisabilityUITG/images/right.JPG\" height=\"18\">");
                            rationDetails.put("go", "<font color=green><b>Done</b></font></a>");
                            if (rs.getString(10) != null) {
                                rationDetails.put("pen", rs.getString(10));
                            } else {
                                rationDetails.put("pen", "-");
                            }
                            if (rs.getString(11) != null) {
                                rationDetails.put("sad", rs.getString(11));
                            } else {
                                rationDetails.put("sad", "-");
                            }

                        } else if (rs.getString(2) != null) {
                            rationDetails.put("imgs", "<img src=\"./DisabilityUITG/images/cross.JPG\" height=\"18\">");
                            rationDetails.put("go", "<a href=\"./MemberDetails.do?mode=getWebDetails&reset=reset&rCardSlno=" + rs.getString(2) + "&name=" + rs.getString(3) + "&age=" + rs.getString(6) + "&gender=" + rs.getString(5) + "&district=" + rs.getString(7) + "&rationCardNo=" + rs.getString(1) + "&relationName=" + rs.getString(4) + "\"><font color=red><b>Go</b></font></a>");
                        } else {
                            rationDetails.put("imgs", "<img src=\"./DisabilityUITG/images/right.JPG\" height=\"18\">");
                            rationDetails.put("go", "<font color=green><b>Done</b></font></a>");
                        }
                    }

                    rationDetails.put("relationSlno", rs.getString(2));
                    rationDetails.put("name", rs.getString(3));
                    rationDetails.put("relation", rs.getString(4));
                    rationDetails.put("gender", rs.getString(5));
                    rationDetails.put("Age", rs.getString(6));
                    rationDetails.put("district", rs.getString(7));
                    rationDetails.put("assStatus", rs.getString(9));
                    rationDetails.put("aadharCardNo", rs.getString(12));
                    rationCardDetails.add(rationDetails);
                }
            }

        } catch (SQLException sqlEx) {System.out.println(sqlEx);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDataFromCivilDatabaseWithOld", "PersonCodeCheckDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "getDataFromCivilDatabaseWithOld");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDataFromCivilDatabaseWithOld", "PersonCodeCheckDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "getDataFromCivilDatabaseWithOld");
        } finally {
            try {

                if (connection != null) {
                    connection.close();
                }

                if (pstmt != null) {
                	pstmt.close();
                }

                if (rs != null) {
                    rs.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return rationCardDetails;
    }

    public int getRactionCardCount(String districtId,DataSource ds)throws SADAREMDBException, SQLException {
        int ractionCardCount =0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
      
        try {
            con = DBConnection.getConnection();
            query=" select count(*) from  tbldistrict_DEtails where district_id =?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtId);
            rs =pstmt.executeQuery();
            if(rs!=null){
                while (rs.next()) {
                    ractionCardCount= rs.getInt(1);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRactionCardCount", "PersonCodeCheckDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "getRactionCardCount");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRactionCardCount", "PersonCodeCheckDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckDAO", "getRactionCardCount");
        } finally {
            try {

                if (con != null) {
                    con.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }

                if (rs != null) {
                    rs.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ractionCardCount;


    }
}
