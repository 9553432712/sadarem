 /*
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.dto.WebsiteCommonDTO;
import org.bf.disability.form.PartAForm;
import org.bf.disability.form.Roles;
import org.bf.disability.form.Users;

import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author sandeep & ganesh
 */
public class CommonDAO {

    /**
     *
     * @param ds DataSource
     * @param districtid Distric id which is selected
     * @return District Name
     */
    public String getUsername(DataSource ds, String userid) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query;
        String username = null;

        try {
            con = DBConnection.getConnection();
            
            query = "select username from users with(nolock) where userid=?";
            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                username = rs.getString(1);
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getUsername", "CommonDAO", "DataBase");
            System.out.println(sqlEx);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getUsername");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getUsername", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getUsername");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return username;
    }

    public ArrayList getRoles(DataSource ds) throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        String query = null;
        Roles roles;

        ArrayList roleslist = new ArrayList();

        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            query = "SELECT role_id,role_name  from roles with(nolock) order by role_name  ";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                roles = new Roles();
                roles.setRoleid(rs.getString("role_id"));
                roles.setRolename(rs.getString("role_name"));

                roleslist.add(roles);
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRoles", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getRoles");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRoles", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getRoles");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);


        }
        return roleslist;
    }

    public ArrayList getUsers(DataSource ds) throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        String query = null;
        Users users;

        ArrayList userlist = new ArrayList();

        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            query = "select a.userid from users a with(nolock),user_roles b with(nolock) where a.userid=b.userid and b.role_id!=10 order by a.userid  ";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                users = new Users();
                users.setUserid(rs.getString(1));
                userlist.add(users);
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getUsers", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getUsers");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getUsers", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getUsers");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);


        }
        return userlist;
    }

    public static void changeStatusToInactiveForResetInUpdate(DataSource ds, String personCode, String tableName) throws SADAREMDBException, SQLException {

        Connection con = null;

        PreparedStatement pstmt = null;
        String query = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            
            query = " update " + tableName + " set Status = 'Inactive' where Person_Code = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personCode);
            pstmt.executeUpdate();

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "changeStatusToInactiveForResetInUpdate", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "changeStatusToInactiveForResetInUpdate");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "changeStatusToInactiveForResetInUpdate", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "changeStatusToInactiveForResetInUpdate");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);


        }

    }

    public ArrayList populateStartEnd(HttpServletRequest request, int totalResults) {
        HttpSession session = request.getSession();

        int start = 1;
        int range = 9;
        int endrange = 0;
        int totalresults = totalResults;

        int restrictarr2 = (totalresults / 10);
        if (totalresults % 10 != 0 && totalresults % 10 <= 10) {
            restrictarr2 = restrictarr2 + 1;
        }

        String removeprevious = "no";
        String removenext = "no";
        String removeback = "yes";

        if (request.getParameter("start") != null) {
            start = Integer.parseInt(request.getParameter("start"));
        }
        session.setAttribute("start", new Integer(start));

        if (start >= restrictarr2) {
            if (start > restrictarr2 || start > 10) {
                removeback = "no";
            }

            start = restrictarr2;
            removenext = "yes";


        } else if (start > 10) {
            removeback = "no";
        }



        if (start % 10 == 0) {
            removenext = "yes";
        }

        if (start % 10 == 1) {
            removeprevious = "yes";
        }
        if (start != 1) {
            start = (start - 1) * 10 + 1;
        } else {
            range = 9;
        }



        endrange = start + range;
        if (endrange > totalresults) {
            endrange = totalresults;

        }


        int arr1 = 1;
        int arr2 = 10;


        if (request.getParameter("nexxt").equals("true")) {
            arr1 = ((Integer) session.getAttribute("arr2")).intValue() + 1;
            arr2 = ((Integer) session.getAttribute("arr2")).intValue() + 10;
            start = arr1;

            if (start >= restrictarr2) {
                removenext = "yes";
            }

            session.setAttribute("start", new Integer(start));
            start = (start - 1) * 10 + 1;
            endrange = start + 9;


            if (endrange > totalresults) {
                endrange = totalresults;
            }

            removeback = "no";

        }

        if (request.getParameter("nexxt").equals("false")) {
            arr1 = ((Integer) session.getAttribute("arr1")).intValue();
            arr2 = ((Integer) session.getAttribute("arr2")).intValue();
        }

        if (request.getParameter("back").equals("true")) {
            arr1 = ((Integer) session.getAttribute("arr1")).intValue() - 10;
            arr2 = ((Integer) session.getAttribute("arr1")).intValue() - 1;
            start = arr1;

            if (start > 10) {
                removeback = "no";
            }
            if (start >= restrictarr2) {
                removenext = "yes";
            }
            session.setAttribute("start", new Integer(start));

            if (start == 1) {
                start = (start - 1) * 10 + 1;
                endrange = start + 9;
            } else {
                start = (start - 1) * 10 + 1;
                endrange = start + 10;
            }

            ArrayList result1 = new ArrayList();


            if (endrange > totalresults) {
                endrange = totalresults;
            }

        }

        session.setAttribute("arr2", new Integer(arr2));
        session.setAttribute("arr1", new Integer(arr1));

        ArrayList numbers = new ArrayList();



        if (arr2 >= restrictarr2) {
            arr2 = restrictarr2;
            request.setAttribute("removenexxt", "yes");
        } else {
            request.setAttribute("removenexxt", "no");
        }


        session.setAttribute("color", new Integer(arr1));
        for (int i = arr1; i <= arr2; i++) {
            numbers.add(new Integer(i));
        }
        request.setAttribute("numbers", numbers);



        request.setAttribute("removeprevious", removeprevious);
        request.setAttribute("removenext", removenext);
        request.setAttribute("removeback", removeback);


        ArrayList startEndRange = new ArrayList();
        startEndRange.add(new Integer(start));
        startEndRange.add(new Integer(endrange));
        return startEndRange;
    }

    public boolean checkUploadPhoto(DataSource ds, String PersonCode) throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        boolean uploadPhotoFlag = false;
        try {
            con = DBConnection.getConnection();
            
            query = "select Person_Code from tblPerson_Photo_Details with(nolock) where Person_Code = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, PersonCode);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                uploadPhotoFlag = true;
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkUploadPhoto", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "checkUploadPhoto");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkUploadPhoto", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "checkUploadPhoto");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return uploadPhotoFlag;
    }

    public int insertUploadPhoto(DataSource ds, String PersonCode, String loginid) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement calstmt = null;
        ResultSet rs = null;
        int i = 0;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            calstmt = con.prepareCall("{Call SP_tblPerson_Photo_Details_Insert_or_Update(?,?)}");
            calstmt.setString(1, PersonCode);
            calstmt.setString(2, loginid);
            i = calstmt.executeUpdate();
            calstmt.close();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertUploadPhoto", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "insertUploadPhoto");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertUploadPhoto", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "insertUploadPhoto");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(calstmt);


        }


        return i;

    }

    /**
     *
     * @param ds DataSource
     * @param PersonCode
     * @return PersonCode Status View or Edite mode
     */
    public String selectStatus(DataSource ds, String PersonCode) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = null;
        String status = null;

        try {
            con = DBConnection.getConnection();
            
            query = "select View_Edit_Mode from tblPerson_Personal_Details  with (nolock) where Person_Code=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, PersonCode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                status = rs.getString(1);
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "selectStatus", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "selectStatus");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "selectStatus", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "selectStatus");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return status;
    }

    /**
     *
     * @param ds DataSource
     * @param PersonCode
     * @param status
     * @return 0 or 1
     */
    public synchronized  int updateStatus(DataSource ds, String PersonCode, String status) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = null;
        int i;


        try {
            con = DBConnection.getConnection();
            
            query = "update tblPerson_Personal_Details set View_Edit_Mode=? where Person_Code=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, status);
            pstmt.setString(2, PersonCode);
            i = pstmt.executeUpdate();



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateStatus", "CommonDAO", "DataBase");
            System.out.println(sqlEx);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "updateStatus");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateStatus", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "updateStatus");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return i;
    }

    /**
     *
     * @param ds DataSource
     * @param PersonCode
     * @return PersonCode valid personcode or not
     */
    public String checkPersonCodeForSearch(DataSource ds, String PersonCode) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query;
        String status = null;
        String personStatus = null;

        try {
            con = DBConnection.getConnection();
            
            query = "select Person_Status from tblPerson_Personal_Details  with (nolock) where Person_Code=? and Status='Active';";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, PersonCode);
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                personStatus = rs.getString(1);
            }
            if (personStatus != null) {
                if (personStatus.equalsIgnoreCase("Eligible")) {
                    query = "select Person_Code from tblPerson_Disability_TotalValue_Details where Person_Code=? and Status='Active';";
                    
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, PersonCode);
                    rs = pstmt.executeQuery();
                    while (rs.next()) {
                        status = rs.getString(1);
                    }
                } else {
                    query = "select Person_Code from tblRejectPerson_Details where Person_Code=? and Status='Active';";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, PersonCode);
                    rs = pstmt.executeQuery();
                    while (rs.next()) {
                        status = rs.getString(1);
                    }

                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersonCodeForSearch", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "checkPersonCodeForSearch");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersonCodeForSearch", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "checkPersonCodeForSearch");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return status;
    }

    /**
     *
     * @param ds DataSource
     * @param PersonCode
     * @return PersonCode valid personcode or not
     */
    public String checkPersonCodeForSearchRep(DataSource ds, String PersonCode, String pensionNo, String district_id) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String personStatus = null;
        String sql = null;

        try {
            con = DBConnection.getConnection();
            
            sql = "select Person_Status from tblPerson_Personal_Details  with (nolock) where district_id=? ";
            if (PersonCode != null && !PersonCode.equalsIgnoreCase("") && !PersonCode.equalsIgnoreCase("null")) {
                sql += "or Person_Code=?";
            }
            if (pensionNo != null && !pensionNo.equalsIgnoreCase("") && !pensionNo.equalsIgnoreCase("null")) {
                sql += "or pensioncard_no=?";
            }

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, district_id);
            if (PersonCode != null && !PersonCode.equalsIgnoreCase("") && !PersonCode.equalsIgnoreCase("null"))
            {
            	pstmt.setString(2, PersonCode);
            }
            if ((pensionNo != null && !pensionNo.equalsIgnoreCase("") && !pensionNo.equalsIgnoreCase("null"))&& !(PersonCode != null && !PersonCode.equalsIgnoreCase("") && !PersonCode.equalsIgnoreCase("null")))
            {
            	pstmt.setString(2, pensionNo);
            }
            if ((pensionNo != null && !pensionNo.equalsIgnoreCase("") && !pensionNo.equalsIgnoreCase("null"))&& (PersonCode != null && !PersonCode.equalsIgnoreCase("") && !PersonCode.equalsIgnoreCase("null")))
            {
            	pstmt.setString(3, pensionNo);
            }
            
            System.out.println(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                personStatus = rs.getString(1);
            }



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersonCodeForSearchRep", "CommonDAO", "DataBase");
            System.out.println(sqlEx);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "checkPersonCodeForSearchRep");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersonCodeForSearchRep", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "checkPersonCodeForSearchRep");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return personStatus;
    }

    /**
     *
     * @param ds DataSource
     * @param PersonCode
     * @return get total percentage of disability with person
     */
    public int getTotalPercentage(DataSource ds, String PersonCode) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query;
        int totalDisability = 0;

        try {
            con = DBConnection.getConnection();
            
            query = "select TotalDisability from tblPerson_Disability_TotalValue_Details with(nolock) where Person_Code=? and Status='Active';";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, PersonCode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                totalDisability = rs.getInt(1);
            }



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTotalPercentage", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getTotalPercentage");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTotalPercentage", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getTotalPercentage");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return totalDisability;
    }

    /**
     *
     * @param ds DataSource
     * @param PersonCode
     * @return PersonCode Status Elgible or Reject
     */
    public String getPersonStatus(DataSource ds, String PersonCode) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query;
        String status = null;

        try {
            con = DBConnection.getConnection();
            
            query = "select Person_Status from tblPerson_Personal_Details  with (nolock) where Person_Code=? and Status='Active';";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, PersonCode);
            rs = pstmt.executeQuery();
            if (rs != null) {
            while (rs.next()) {
                status = rs.getString(1);
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonStatus", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getPersonStatus");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonStatus", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getPersonStatus");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return status;
    }

    /**
     *
     * @param ds DataSource
     * @param PersonCode
     * @return Disability Id
     */
    public String getDisabilityId(DataSource ds, String PersonCode) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query;
        String status = null;

        try {
            con = DBConnection.getConnection();
            
            query = "select Disability_ID from tblPerson_Disability_Details with(nolock) where Person_Code=? and Status='Active';";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, PersonCode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                status = rs.getString(1);
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityId", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getDisabilityId");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityId", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getDisabilityId");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return status;
    }

    /**
     *
     * @param ds DataSource
     * @param PersonCode
     * @param status
     * @return 0 or 1
     */
    public int insertFeedbackDetails(DataSource ds, WebsiteCommonDTO feedbackdto, String localaddr) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement cs = null;
        int i = 0;


        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            cs = con.prepareCall("{Call SP_Feedback_Details_INSERT(?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, feedbackdto.getName());
            cs.setString(2, feedbackdto.getState());
            cs.setString(3, feedbackdto.getDistrict());
            cs.setString(4, feedbackdto.getMandal());
            cs.setString(5, feedbackdto.getLocation());
            cs.setString(6, feedbackdto.getPhone());
            cs.setString(7, feedbackdto.getEmail());
            cs.setString(8, feedbackdto.getFeedback());
            cs.setString(9, localaddr);

            i = cs.executeUpdate();

            con.commit();
            con.setAutoCommit(true);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertFeedbackDetails", "CommonDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "insertFeedbackDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertFeedbackDetails", "CommonDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "insertFeedbackDetails");
        } finally {

            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cs);

        }
        return i;
    }

    /**
     *
     * @param ds DataSource
     *
     * @return feedback list
     */
    public List getFeedbackDetails(DataSource datasource) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        Connection con = null;
        Statement stmt = null;
        WebsiteCommonDTO feedbackDTO = null;
        List feedbackDetailsList = null;
        String createdDate = null;
        String personName = null;
        String district = null;
        String mandal = null;
        String location = null;
        int endIndex;
        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            feedbackDetailsList = new ArrayList();
            rs = stmt.executeQuery("select RowID, PersonName, District, Mandal, Location, Status, Created_Date from Feedback_Details with(nolock) ");
            while (rs.next()) {
                feedbackDTO = new WebsiteCommonDTO();
                feedbackDTO.setRowId(rs.getString("RowID"));
                personName = rs.getString("PersonName");
                endIndex = (personName.length()) / 2;
                feedbackDTO.setName(personName.substring(0, endIndex));
                district = rs.getString("District");
                endIndex = (district.length()) / 2;
                feedbackDTO.setDistrict(district.substring(0, endIndex));
                mandal = rs.getString("Mandal");
                endIndex = (mandal.length()) / 2;
                feedbackDTO.setMandal(mandal.substring(0, endIndex));
                location = rs.getString("Location");
                endIndex = (location.length()) / 2;
                feedbackDTO.setLocation(location.substring(0, endIndex));
                feedbackDTO.setStatus(rs.getString("Status"));
                Date formateDate = new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString("Created_Date"));
                createdDate = new SimpleDateFormat("dd/mm/yyyy").format(formateDate);
                feedbackDTO.setCreatedDate(createdDate);
                feedbackDetailsList.add(feedbackDTO);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getFeedbackDetails", "CommonDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getFeedbackDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getFeedbackDetails", "CommonDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getFeedbackDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

        }
        return feedbackDetailsList;
    }

    /**
     * @param  db row id
     * @param ds DataSource
     * @param feedback status active or inactive or delete
     * @return 0 or 1
     */
    public int updateFeedbackDetails(DataSource ds, String FeedbackIdsStatus, String selectedRowId) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql="";
        int i = 0;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            if ("ActiveFeedback".equals(FeedbackIdsStatus)) {
            	sql = "update Feedback_Details set Status='Active' where RowID=?";
            	pstmt = con.prepareStatement(sql);
            	pstmt.setString(1, selectedRowId);            	
                i = pstmt.executeUpdate();
                con.commit();
            } else if ("Inactive".equals(FeedbackIdsStatus)) {
            	sql = "update Feedback_Details set Status='Inactive' where RowID=?";
            	pstmt = con.prepareStatement(sql);
            	pstmt.setString(1, selectedRowId);            	
                i = pstmt.executeUpdate();
                con.commit();
            } else if ("Delete".equals(FeedbackIdsStatus)) {
            	sql = "delete Feedback_Details where RowID=?";
            	pstmt = con.prepareStatement(sql);
            	pstmt.setString(1, selectedRowId);            	
                i = pstmt.executeUpdate();
                con.commit();
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateFeedbackDetails", "CommonDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "updateFeedbackDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateFeedbackDetails", "CommonDAO", "Code");
           con.rollback();
            System.out.println(sqlEx);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "updateFeedbackDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return i;


    }

    public WebsiteCommonDTO selectFeedbackDetails(DataSource ds, String rowId) throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query;
        WebsiteCommonDTO feedbackDTO = null;

        try {
            con = DBConnection.getConnection();
            
            feedbackDTO = new WebsiteCommonDTO();
            query = "select PersonName, State, District, Mandal, Location, Phone, Email, Feedback from Feedback_Details with(nolock) where RowID=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, rowId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                feedbackDTO.setName(rs.getString(1));
                feedbackDTO.setState(rs.getString(2));
                feedbackDTO.setDistrict(rs.getString(3));
                feedbackDTO.setMandal(rs.getString(4));
                feedbackDTO.setLocation(rs.getString(5));
                feedbackDTO.setPhone(rs.getString(6));
                feedbackDTO.setEmail(rs.getString(7));
                feedbackDTO.setFeedback(rs.getString(8));
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "selectFeedbackDetails", "CommonDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "selectFeedbackDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "selectFeedbackDetails", "CommonDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "selectFeedbackDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return feedbackDTO;
    }

    /**
     *
     * @param ds DataSource
     *
     * @return feedback list
     */
    public List getFeedback(DataSource datasource) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        Connection con = null;
        Statement stmt = null;
        WebsiteCommonDTO feedbackDTO = null;
        List feedbackDetailsList = null;

        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            feedbackDetailsList = new ArrayList();
            rs = stmt.executeQuery("select PersonName, Location, Feedback   from Feedback_Details with(nolock) where Status='Active'");
            while (rs.next()) {
                feedbackDTO = new WebsiteCommonDTO();
                feedbackDTO.setName(rs.getString("PersonName"));
                feedbackDTO.setLocation(rs.getString("Location"));
                feedbackDTO.setFeedback(rs.getString("Feedback"));
                feedbackDetailsList.add(feedbackDTO);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getFeedback", "CommonDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getFeedback");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getFeedback", "CommonDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getFeedback");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

        }
        return feedbackDetailsList;
    }

//   public ArrayList<WebsiteCommonDTO> getDashBoardDetails(DataSource ds)throws SADAREMDBException, SQLException{
//        Connection con = null;
//        Statement stmt = null;
//        Statement stmt2 = null;
//        ResultSet rs = null;
//        ResultSet rs2 = null;
//        String query1 =  null;
//        String query2 =  null;
//         WebsiteCommonDTO websiteDTO  = null;
//         WebsiteCommonDTO websiteDTONew = null;
//        ArrayList<WebsiteCommonDTO> dashBoardList = null;
//
//    try{
//         con = DBConnection.getConnection();
//        stmt = con.createStatement();
//        stmt2 = con.createStatement();
//        dashBoardList = new ArrayList<WebsiteCommonDTO>();
//
//        query1 = "SELECT PENSIONPHASE,COUNT(PENSIONID) AS EXISTINGPERSONS FROM DISABLED_PENSION " +
//                "WHERE PERSON_STATUS NOT IN ('D')GROUP BY PENSIONPHASE";
//
//        rs = stmt.executeQuery(query1);
//            while (rs.next()) {
//            websiteDTO = new WebsiteCommonDTO();
//            websiteDTO.setPhaseName(rs.getString("PENSIONPHASE"));
//            websiteDTO.setNoofpersons(rs.getString("EXISTINGPERSONS"));
//            query2 = "SELECT PENSIONPHASE, COUNT(PERSON_CODE) AS ASSESSEDPERSONS FROM"
//                    + " TBLPERSON_PERSONAL_DETAILS WHERE STATUS = 'Active' "
//                    + "AND PENSIONPHASE NOT IN ('') AND PENSIONPHASE = '" + rs.getString("PENSIONPHASE") + "' AND PERSON_CODE IN "
//                    + "(SELECT PERSON_CODE FROM TBLPERSON_DISABILITY_TOTALVALUE_DETAILS"
//                    + " WHERE STATUS = 'Active')GROUP BY PENSIONPHASE";
//            rs2 = stmt2.executeQuery(query2);
//            while (rs2.next()) {
//                websiteDTO.setNoofassessed(rs2.getString("ASSESSEDPERSONS"));
//                int percentage = (Integer.parseInt(websiteDTO.getNoofpersons()) / Integer.parseInt(websiteDTO.getNoofassessed())) * 100;
//                websiteDTO.setCompletedpercent(String.valueOf(percentage));
//                dashBoardList.add(websiteDTO);
//            }
//        }
//
//
//
//    } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//
//            throw new SADAREMException();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SADAREMException();
//        } finally {
//            DBConnection.closeResultSet(rs);
//            DBConnection.closeStatement(stmt);
//            DBConnection.closeConnection(con);
//
//        }
//    return dashBoardList;
//}
    public ArrayList<WebsiteCommonDTO> getDashBoardDetails(DataSource ds) throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        Statement stmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        String query1 = null;
        String query2 = null;
        WebsiteCommonDTO websiteDTO = null;
        WebsiteCommonDTO websiteDTONew = null;
        WebsiteCommonDTO websiteDTOTemp = null;
        WebsiteCommonDTO websiteDTODupli = null;
        ArrayList<WebsiteCommonDTO> phaseIIIList = null;
        ArrayList<WebsiteCommonDTO> dashBoardList = null;
        ArrayList emptyRecordList = null;
        ArrayList<WebsiteCommonDTO> dashBoardListNew = null;
        ArrayList<WebsiteCommonDTO> dashBoardListTemp = null;

        try {
            //  con = DBConnection.getConnection();
            con = DBConnection.getConnection();

            stmt = con.createStatement();
            stmt2 = con.createStatement();
            dashBoardList = new ArrayList<WebsiteCommonDTO>();
            emptyRecordList = new ArrayList<WebsiteCommonDTO>();
            dashBoardListNew = new ArrayList<WebsiteCommonDTO>();
            dashBoardListTemp = new ArrayList<WebsiteCommonDTO>();

            /*   query1 = "SELECT PENSIONPHASE,COUNT(PENSIONID) AS EXISTINGPERSONS FROM DISABLED_PENSION "
            + "WHERE PERSON_STATUS NOT IN ('D')GROUP BY PENSIONPHASE";*/

            query1 = "SELECT PENSIONPHASE,case when COUNT(PENSIONID)!=0 then COUNT(PENSIONID) else (select count(person_code) from "
                    + "tblperson_personal_details with (nolock) where PENSIONPHASE='PhaseIII') end AS EXISTINGPERSONS FROM DISABLED_PENSION with (nolock) "
                    + "WHERE PERSON_STATUS NOT IN ('D')GROUP BY PENSIONPHASE order by PENSIONPHASE";

            rs = stmt.executeQuery(query1);
            while (rs.next()) {
                websiteDTO = new WebsiteCommonDTO();
                websiteDTO.setPhaseName(rs.getString("PENSIONPHASE"));
                websiteDTO.setNoofpersons(rs.getString("EXISTINGPERSONS"));
                dashBoardListNew.add(websiteDTO);
            }

//         query2 = "SELECT PENSIONPHASE, COUNT(PERSON_CODE) AS ASSESSEDPERSONS FROM"
//                    + " TBLPERSON_PERSONAL_DETAILS WHERE STATUS = 'Active' "
//                    + "AND PENSIONPHASE NOT IN ('') AND PERSON_CODE IN "
//                    + "(SELECT PERSON_CODE FROM TBLPERSON_DISABILITY_TOTALVALUE_DETAILS"
//                    + " WHERE STATUS = 'Active')GROUP BY PENSIONPHASE";


            query2 = "SELECT PENSIONPHASE, COUNT(P.PERSON_CODE) AS ASSESSEDPERSONS FROM "
                    + " TBLPERSON_PERSONAL_DETAILS P with (nolock) ,TBLPERSON_DISABILITY_TOTALVALUE_DETAILS T with(nolock) "
                    + " WHERE P.STATUS = 'Active' AND T.STATUS='Active' and  P.PENSIONPHASE NOT IN ('') AND P.PERSON_CODE=T.PERSON_CODE "
                    + "GROUP BY PENSIONPHASE order by PENSIONPHASE";


            rs2 = stmt2.executeQuery(query2);
            while (rs2.next()) {
                websiteDTO = new WebsiteCommonDTO();
                websiteDTO.setPhaseName(rs2.getString("PENSIONPHASE"));
                websiteDTO.setNoofassessed(rs2.getString("ASSESSEDPERSONS"));
                dashBoardListTemp.add(websiteDTO);
            }



            for (int i = 0; i < dashBoardListNew.size(); i++) {
                websiteDTONew = (WebsiteCommonDTO) dashBoardListNew.get(i);
                websiteDTOTemp = (WebsiteCommonDTO) dashBoardListTemp.get(i);


                if (websiteDTONew.getPhaseName().equals(websiteDTOTemp.getPhaseName())) {

                    DecimalFormat decimalFormat = new DecimalFormat("#.##");

                    if (!websiteDTOTemp.getNoofassessed().equalsIgnoreCase("0")) {
                        double percentage = (Double.parseDouble(websiteDTOTemp.getNoofassessed()) * 100 / Double.parseDouble(websiteDTONew.getNoofpersons()));
                        //decimalFormat.format(percentage);
                        websiteDTONew.setNoofassessed(websiteDTOTemp.getNoofassessed());
                        websiteDTONew.setCompletedpercent(decimalFormat.format(percentage));

                    } else {
                        websiteDTONew.setNoofassessed(websiteDTOTemp.getNoofassessed());
                        websiteDTONew.setCompletedpercent(decimalFormat.format(0));
                    }
                    //    websiteDTONew.setCompletedpercent(decimalFormat.format(percentage));

                    dashBoardList.add(websiteDTONew);
                    emptyRecordList.add(websiteDTOTemp.getPhaseName());

                }
            }



            String[] pahseList = {"PhaseI", "PhaseII", "PhaseIII", "PhaseIV"};
            ArrayList newList = new ArrayList(Arrays.asList(pahseList));

            newList.removeAll(emptyRecordList);

            for (int k = 0; k < newList.size(); k++) {
                websiteDTODupli = new WebsiteCommonDTO();
                websiteDTODupli.setPhaseName(newList.get(k).toString());
                websiteDTODupli.setNoofpersons("-");
                websiteDTODupli.setNoofassessed("-");
                websiteDTODupli.setCompletedpercent("-");
                dashBoardList.add(websiteDTODupli);
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDashBoardDetails", "CommonDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getDashBoardDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDashBoardDetails", "CommonDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getDashBoardDetails");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeResultSet(rs3);
                DBConnection.closeStatement(stmt);
                DBConnection.closeStatement(stmt2);
                //DBConnection.closeConnection(con);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return dashBoardList;
    }

    public TerritoryDTO getDisabilityPercentages(DataSource datasource, String personCode, int disabilityId) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        TerritoryDTO territoryDTO = new TerritoryDTO();
        try {
            con = DBConnection.getConnection();
            
            StringBuffer strbuff = new StringBuffer();
            //TerritoryDTO territoryDTO = new TerritoryDTO();
            switch (disabilityId) {

                case 1:
                    strbuff.append("select L.LowerExtremity_Total ,U.UpperExtremity_Total,A.Amputation_Total,T.Total_Value AS Transverse_Total,TR.Trunk_Total,E.Total_Value AS Physical_Impairments_Total,C.Pulmonary_Condition,D.Dwarfism_Total ");
                    strbuff.append(" from tblPerson_Personal_Details P   with (nolock) ");
                    strbuff.append("LEFT OUTER JOIN tblLower_Extremity_StabilityComponent_ClinicalMethod_Details L with(nolock) ON L.Person_Code = P.Person_Code AND L.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblUpper_Extremity_HandComponent_Strength_Details U  with(nolock)  ON U.Person_Code = P.Person_Code AND U.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblAmputation_UpperLimbAmputation_Details A with(nolock)  ON A.Person_Code = P.Person_Code AND A.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblCongenitalDeficienciesLimb_Transverse_Details T  with(nolock)  ON T.Person_Code = P.Person_Code AND T.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblTrunk_NonTraumaticLesions_Details TR with(nolock) ON TR.Person_Code = P.Person_Code AND TR.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblPhysical_Impairments_in_Neurological_Details E with(nolock) ON E.Person_Code = P.Person_Code AND E.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblCardio_Pulmonary_Conditions_Details C with(nolock) ON C.Person_Code = P.Person_Code AND C.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblDwarfism_Details D with(nolock) ON D.Person_Code = P.Person_Code AND D.Status = 'Active' ");
                    strbuff.append("where P.Person_code = '" + personCode + "' and P.Status = 'Active'");
                    pstmt = con.prepareStatement(strbuff.toString());
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        if (rs.getString("UpperExtremity_Total") != null && !"".equals(rs.getString("UpperExtremity_Total"))) {
                            territoryDTO.setUpperExtremity_Total(rs.getString("UpperExtremity_Total"));
                        } else {
                            territoryDTO.setUpperExtremity_Total("0");
                        }
                        if (rs.getString("LowerExtremity_Total") != null && !"".equals(rs.getString("LowerExtremity_Total"))) {
                            territoryDTO.setLowerExtremity_Total(rs.getString("LowerExtremity_Total"));
                        } else {
                            territoryDTO.setLowerExtremity_Total("0");
                        }
                        if (rs.getString("Amputation_Total") != null && !"".equals(rs.getString("Amputation_Total"))) {
                            territoryDTO.setAmputation_Total(rs.getString("Amputation_Total"));
                        } else {
                            territoryDTO.setAmputation_Total("0");
                        }
                        if (rs.getString("Transverse_Total") != null && !"".equals(rs.getString("Transverse_Total"))) {
                            territoryDTO.setTransverse_Total(rs.getString("Transverse_Total"));
                        } else {
                            territoryDTO.setTransverse_Total("0");
                        }
                        if (rs.getString("Trunk_Total") != null && !"".equals(rs.getString("Trunk_Total"))) {
                            territoryDTO.setTrunk_Total(rs.getString("Trunk_Total"));
                        } else {
                            territoryDTO.setTrunk_Total("0");
                        }
                        if (rs.getString("Physical_Impairments_Total") != null && !"".equals(rs.getString("Physical_Impairments_Total"))) {
                            territoryDTO.setPhysical_Impairments_Total(rs.getString("Physical_Impairments_Total"));
                        } else {
                            territoryDTO.setPhysical_Impairments_Total("0");
                        }
                        if (rs.getString("Pulmonary_Condition") != null && !"".equals(rs.getString("Pulmonary_Condition"))) {
                            territoryDTO.setPulmonary_Condition(rs.getString("Pulmonary_Condition"));
                        } else {
                            territoryDTO.setPulmonary_Condition("0");
                        }
                        if (rs.getString("Dwarfism_Total") != null && !"".equals(rs.getString("Dwarfism_Total"))) {
                            territoryDTO.setDwarfism_Total(rs.getString("Dwarfism_Total"));
                        } else {
                            territoryDTO.setDwarfism_Total("0");
                        }
                    } else {
                        territoryDTO.setUpperExtremity_Total("0");
                        territoryDTO.setLowerExtremity_Total("0");
                        territoryDTO.setAmputation_Total("0");
                        territoryDTO.setTransverse_Total("0");
                        territoryDTO.setTrunk_Total("0");
                        territoryDTO.setPhysical_Impairments_Total("0");
                        territoryDTO.setPulmonary_Condition("0");
                        territoryDTO.setDwarfism_Total("0");
                    }
                    break;
                case 2:
                    strbuff.append("select Visual_Impairment from tblVisualImpairment_Details with(nolock) where Person_code = ? and Status = 'Active'");
                    pstmt = con.prepareStatement(strbuff.toString());
                    pstmt.setString(1, personCode);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        if (rs.getString("Visual_Impairment") != null && !"".equals(rs.getString("Visual_Impairment"))) {
                            if (rs.getString("Visual_Impairment").equals("101.0")) {
                                territoryDTO.setVisual_Impairment("100.0");
                            } else {
                                territoryDTO.setVisual_Impairment(rs.getString("Visual_Impairment"));
                            }
                        } else {
                            territoryDTO.setVisual_Impairment("0");
                        }
                    } else {
                        territoryDTO.setVisual_Impairment("0");
                    }
                    break;
                case 3:
                    strbuff.append("select Person_Hearing_Percentage from tblHearing_Impairment_Test_Details_for_Persons with(nolock) where Person_code = ? and Status = 'Active'");
                    pstmt = con.prepareStatement(strbuff.toString());
                    pstmt.setString(1, personCode);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        if (rs.getString("Person_Hearing_Percentage") != null && !"".equals(rs.getString("Person_Hearing_Percentage"))) {
                            territoryDTO.setHearing_Percentage(rs.getString("Person_Hearing_Percentage"));
                        } else {
                            territoryDTO.setHearing_Percentage("0");
                        }
                    } else {
                        territoryDTO.setHearing_Percentage("0");
                    }
                    break;
                case 4:
                    strbuff.append("select Total_Value from tblMental_Retardation_Details with(nolock) where Person_code = ? and Status = 'Active'");
                    pstmt = con.prepareStatement(strbuff.toString());
                    pstmt.setString(1, personCode);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        if (rs.getString("Total_Value") != null && !"".equals(rs.getString("Total_Value"))) {
                            territoryDTO.setMental_Retardation_Total(rs.getString("Total_Value"));
                        } else {
                            territoryDTO.setMental_Retardation_Total("0");
                        }
                    } else {
                        territoryDTO.setMental_Retardation_Total("0");
                    }
                    break;
                case 5:
                    strbuff.append("select Total_Value from tblMental_Disability_Illness_Details with(nolock) where Person_code = ? and Status = 'Active'");
                    pstmt = con.prepareStatement(strbuff.toString());
                    pstmt.setString(1, personCode);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        if (rs.getString("Total_Value") != null && !"".equals(rs.getString("Total_Value"))) {
                            territoryDTO.setMental_Disability_Total(rs.getString("Total_Value"));
                        } else {
                            territoryDTO.setMental_Disability_Total("0");
                        }
                    } else {
                        territoryDTO.setMental_Disability_Total("0");
                    }
                    break;

                case 6:
                    strbuff.append("select L.LowerExtremity_Total ,U.UpperExtremity_Total,A.Amputation_Total,T.Total_Value AS Transverse_Total,TR.Trunk_Total,E.Total_Value AS Physical_Impairments_Total,C.Pulmonary_Condition,D.Dwarfism_Total, ");
                    strbuff.append(" V.Visual_Impairment,H.Person_Hearing_Percentage,MR.Total_Value AS MentalRetardation,MI.Total_Value AS MentalIllness from tblPerson_Personal_Details P   with (nolock) ");
                    strbuff.append("LEFT OUTER JOIN tblLower_Extremity_StabilityComponent_ClinicalMethod_Details L with(nolock)  ON L.Person_Code = P.Person_Code AND L.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblUpper_Extremity_HandComponent_Strength_Details U with(nolock) ON U.Person_Code = P.Person_Code AND U.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblAmputation_UpperLimbAmputation_Details A with(nolock) ON A.Person_Code = P.Person_Code AND A.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblCongenitalDeficienciesLimb_Transverse_Details T with(nolock)  ON T.Person_Code = P.Person_Code AND T.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblTrunk_NonTraumaticLesions_Details TR with(nolock) ON TR.Person_Code = P.Person_Code AND TR.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblPhysical_Impairments_in_Neurological_Details E with(nolock) ON E.Person_Code = P.Person_Code AND E.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblCardio_Pulmonary_Conditions_Details C with(nolock) ON C.Person_Code = P.Person_Code AND C.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblDwarfism_Details D with(nolock) ON D.Person_Code = P.Person_Code AND D.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN tblVisualImpairment_Details V with(nolock) ON V.Person_Code = P.Person_Code AND V.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN tblHearing_Impairment_Test_Details_for_Persons H with(nolock) ON H.Person_Code = P.Person_Code AND H.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN tblMental_Retardation_Details MR with(nolock) ON MR.Person_Code = P.Person_Code AND MR.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN tblMental_Disability_Illness_Details MI with(nolock)  ON MI.Person_Code = P.Person_Code AND MI.Status = 'Active' ");
                    strbuff.append("where P.Person_code = ? and P.Status = 'Active'");
                    pstmt = con.prepareStatement(strbuff.toString());
                    pstmt.setString(1, personCode);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        if (rs.getString("UpperExtremity_Total") != null && !"".equals(rs.getString("UpperExtremity_Total"))) {
                            territoryDTO.setUpperExtremity_Total(rs.getString("UpperExtremity_Total"));
                        } else {
                            territoryDTO.setUpperExtremity_Total("0");
                        }
                        if (rs.getString("LowerExtremity_Total") != null && !"".equals(rs.getString("LowerExtremity_Total"))) {
                            territoryDTO.setLowerExtremity_Total(rs.getString("LowerExtremity_Total"));
                        } else {
                            territoryDTO.setLowerExtremity_Total("0");
                        }
                        if (rs.getString("Amputation_Total") != null && !"".equals(rs.getString("Amputation_Total"))) {
                            territoryDTO.setAmputation_Total(rs.getString("Amputation_Total"));
                        } else {
                            territoryDTO.setAmputation_Total("0");
                        }
                        if (rs.getString("Transverse_Total") != null && !"".equals(rs.getString("Transverse_Total"))) {
                            territoryDTO.setTransverse_Total(rs.getString("Transverse_Total"));
                        } else {
                            territoryDTO.setTransverse_Total("0");
                        }
                        if (rs.getString("Trunk_Total") != null && !"".equals(rs.getString("Trunk_Total"))) {
                            territoryDTO.setTrunk_Total(rs.getString("Trunk_Total"));
                        } else {
                            territoryDTO.setTrunk_Total("0");
                        }
                        if (rs.getString("Physical_Impairments_Total") != null && !"".equals(rs.getString("Physical_Impairments_Total"))) {
                            territoryDTO.setPhysical_Impairments_Total(rs.getString("Physical_Impairments_Total"));
                        } else {
                            territoryDTO.setPhysical_Impairments_Total("0");
                        }
                        if (rs.getString("Pulmonary_Condition") != null && !"".equals(rs.getString("Pulmonary_Condition"))) {
                            territoryDTO.setPulmonary_Condition(rs.getString("Pulmonary_Condition"));
                        } else {
                            territoryDTO.setPulmonary_Condition("0");
                        }
                        if (rs.getString("Dwarfism_Total") != null && !"".equals(rs.getString("Dwarfism_Total"))) {
                            territoryDTO.setDwarfism_Total(rs.getString("Dwarfism_Total"));
                        } else {
                            territoryDTO.setDwarfism_Total("0");
                        }
                        if (rs.getString("Visual_Impairment") != null && !"".equals(rs.getString("Visual_Impairment"))) {
                            if (rs.getString("Visual_Impairment").equals("101.0")) {
                                territoryDTO.setVisual_Impairment("100.0");
                            } else {
                                territoryDTO.setVisual_Impairment(rs.getString("Visual_Impairment"));
                            }
                        } else {
                            territoryDTO.setVisual_Impairment("0");
                        }
                        if (rs.getString("Person_Hearing_Percentage") != null && !"".equals(rs.getString("Person_Hearing_Percentage"))) {
                            territoryDTO.setHearing_Percentage(rs.getString("Person_Hearing_Percentage"));
                        } else {
                            territoryDTO.setHearing_Percentage("0");
                        }
                        if (rs.getString("MentalRetardation") != null && !"".equals(rs.getString("MentalRetardation"))) {
                            territoryDTO.setMental_Retardation_Total(rs.getString("MentalRetardation"));
                        } else {
                            territoryDTO.setMental_Retardation_Total("0");
                        }
                        if (rs.getString("MentalIllness") != null && !"".equals(rs.getString("MentalIllness"))) {
                            territoryDTO.setMental_Disability_Total(rs.getString("MentalIllness"));
                        } else {
                            territoryDTO.setMental_Disability_Total("0");
                        }

                    } else {
                        territoryDTO.setUpperExtremity_Total("0");
                        territoryDTO.setLowerExtremity_Total("0");
                        territoryDTO.setAmputation_Total("0");
                        territoryDTO.setTransverse_Total("0");
                        territoryDTO.setTrunk_Total("0");
                        territoryDTO.setPhysical_Impairments_Total("0");
                        territoryDTO.setPulmonary_Condition("0");
                        territoryDTO.setDwarfism_Total("0");
                        territoryDTO.setVisual_Impairment("0");
                        territoryDTO.setHearing_Percentage("0");
                        territoryDTO.setMental_Retardation_Total("0");
                        territoryDTO.setMental_Disability_Total("0");
                    }
                    break;

            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getDisabilityPercentages", "CommonDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getDisabilityPercentages");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getDisabilityPercentages", "CommonDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getDisabilityPercentages");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return territoryDTO;
    }

    public TerritoryDTO getDisabilityPercentagesAU(DataSource datasource, String personCode, int disabilityId) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        TerritoryDTO territoryDTO = new TerritoryDTO();
        try {
            con = DBConnection.getConnection();
            
            StringBuffer strbuff = new StringBuffer();
            //TerritoryDTO territoryDTO = new TerritoryDTO();
            switch (disabilityId) {

                case 1:
                    strbuff.append("select L.LowerExtremity_Total ,U.UpperExtremity_Total,A.Amputation_Total,T.Total_Value AS Transverse_Total,TR.Trunk_Total,E.Total_Value AS Physical_Impairments_Total,C.Pulmonary_Condition,D.Dwarfism_Total ");
                    strbuff.append(" from tblPerson_Personal_Details P   with (nolock) ");
                    strbuff.append("LEFT OUTER JOIN tblLower_Extremity_StabilityComponent_ClinicalMethod_Details L with(nolock) ON L.Person_Code = P.Person_Code AND L.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblUpper_Extremity_HandComponent_Strength_Details U with(nolock) ON U.Person_Code = P.Person_Code AND U.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblAmputation_UpperLimbAmputation_Details A with(nolock) ON A.Person_Code = P.Person_Code AND A.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblCongenitalDeficienciesLimb_Transverse_Details T with(nolock)  ON T.Person_Code = P.Person_Code AND T.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblTrunk_NonTraumaticLesions_Details TR with(nolock) ON TR.Person_Code = P.Person_Code AND TR.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblPhysical_Impairments_in_Neurological_Details E with(nolock) ON E.Person_Code = P.Person_Code AND E.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblCardio_Pulmonary_Conditions_Details C with(nolock) ON C.Person_Code = P.Person_Code AND C.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblDwarfism_Details D with(nolock) ON D.Person_Code = P.Person_Code AND D.Status = 'Active' ");
                    strbuff.append("where P.Person_code = ? and P.Status = 'Active'");
                    pstmt = con.prepareStatement(strbuff.toString());
                    pstmt.setString(1, personCode);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        if (rs.getString("UpperExtremity_Total") != null && !"".equals(rs.getString("UpperExtremity_Total"))) {
                            territoryDTO.setUpperExtremity_Total(rs.getString("UpperExtremity_Total"));
                        } else {
                            territoryDTO.setUpperExtremity_Total("0");
                        }
                        if (rs.getString("LowerExtremity_Total") != null && !"".equals(rs.getString("LowerExtremity_Total"))) {
                            territoryDTO.setLowerExtremity_Total(rs.getString("LowerExtremity_Total"));
                        } else {
                            territoryDTO.setLowerExtremity_Total("0");
                        }
                        if (rs.getString("Amputation_Total") != null && !"".equals(rs.getString("Amputation_Total"))) {
                            territoryDTO.setAmputation_Total(rs.getString("Amputation_Total"));
                        } else {
                            territoryDTO.setAmputation_Total("0");
                        }
                        if (rs.getString("Transverse_Total") != null && !"".equals(rs.getString("Transverse_Total"))) {
                            territoryDTO.setTransverse_Total(rs.getString("Transverse_Total"));
                        } else {
                            territoryDTO.setTransverse_Total("0");
                        }
                        if (rs.getString("Trunk_Total") != null && !"".equals(rs.getString("Trunk_Total"))) {
                            territoryDTO.setTrunk_Total(rs.getString("Trunk_Total"));
                        } else {
                            territoryDTO.setTrunk_Total("0");
                        }
                        if (rs.getString("Physical_Impairments_Total") != null && !"".equals(rs.getString("Physical_Impairments_Total"))) {
                            territoryDTO.setPhysical_Impairments_Total(rs.getString("Physical_Impairments_Total"));
                        } else {
                            territoryDTO.setPhysical_Impairments_Total("0");
                        }
                        if (rs.getString("Pulmonary_Condition") != null && !"".equals(rs.getString("Pulmonary_Condition"))) {
                            territoryDTO.setPulmonary_Condition(rs.getString("Pulmonary_Condition"));
                        } else {
                            territoryDTO.setPulmonary_Condition("0");
                        }
                        if (rs.getString("Dwarfism_Total") != null && !"".equals(rs.getString("Dwarfism_Total"))) {
                            territoryDTO.setDwarfism_Total(rs.getString("Dwarfism_Total"));
                        } else {
                            territoryDTO.setDwarfism_Total("0");
                        }
                    } else {
                        territoryDTO.setUpperExtremity_Total("0");
                        territoryDTO.setLowerExtremity_Total("0");
                        territoryDTO.setAmputation_Total("0");
                        territoryDTO.setTransverse_Total("0");
                        territoryDTO.setTrunk_Total("0");
                        territoryDTO.setPhysical_Impairments_Total("0");
                        territoryDTO.setPulmonary_Condition("0");
                        territoryDTO.setDwarfism_Total("0");
                    }
                    break;
                case 2:
                    strbuff.append("select Visual_Impairment from tblVisualImpairment_Details with(nolock) where Person_code = ? and Status = 'Active'");
                    pstmt = con.prepareStatement(strbuff.toString());
                    pstmt.setString(1, personCode);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        if (rs.getString("Visual_Impairment") != null && !"".equals(rs.getString("Visual_Impairment"))) {
                            if (rs.getString("Visual_Impairment").equals("101.0")) {
                                territoryDTO.setVisual_Impairment("100.0");
                            } else {
                                territoryDTO.setVisual_Impairment(rs.getString("Visual_Impairment"));
                            }
                        } else {
                            territoryDTO.setVisual_Impairment("0");
                        }
                    } else {
                        territoryDTO.setVisual_Impairment("0");
                    }
                    break;
                case 3:
                    strbuff.append("select Person_Hearing_Percentage from tblHearing_Impairment_Test_Details_for_Persons with(nolock)  where Person_code = ? and Status = 'Active'");
                    pstmt = con.prepareStatement(strbuff.toString());
                    pstmt.setString(1, personCode);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        if (rs.getString("Person_Hearing_Percentage") != null && !"".equals(rs.getString("Person_Hearing_Percentage"))) {
                            territoryDTO.setHearing_Percentage(rs.getString("Person_Hearing_Percentage"));
                        } else {
                            territoryDTO.setHearing_Percentage("0");
                        }
                    } else {
                        territoryDTO.setHearing_Percentage("0");
                    }
                    break;
                case 4:
                    strbuff.append("select Total_Value from tblMental_Retardation_Details with(nolock) where Person_code = ? and Status = 'Active'");
                    pstmt = con.prepareStatement(strbuff.toString());
                    pstmt.setString(1, personCode);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        if (rs.getString("Total_Value") != null && !"".equals(rs.getString("Total_Value"))) {
                            territoryDTO.setMental_Retardation_Total(rs.getString("Total_Value"));
                        } else {
                            territoryDTO.setMental_Retardation_Total("0");
                        }
                    } else {
                        territoryDTO.setMental_Retardation_Total("0");
                    }
                    break;
                case 5:
                    strbuff.append("select Total_Value from tblMental_Disability_Illness_Details with(nolock) where Person_code = ? and Status = 'Active'");
                    pstmt = con.prepareStatement(strbuff.toString());
                    pstmt.setString(1, personCode);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        if (rs.getString("Total_Value") != null && !"".equals(rs.getString("Total_Value"))) {
                            territoryDTO.setMental_Disability_Total(rs.getString("Total_Value"));
                        } else {
                            territoryDTO.setMental_Disability_Total("0");
                        }
                    } else {
                        territoryDTO.setMental_Disability_Total("0");
                    }
                    break;

                case 6:
                    strbuff.append("select L.LowerExtremity_Total ,U.UpperExtremity_Total,A.Amputation_Total,T.Total_Value AS Transverse_Total,TR.Trunk_Total,E.Total_Value AS Physical_Impairments_Total,C.Pulmonary_Condition,D.Dwarfism_Total, ");
                    strbuff.append(" V.Visual_Impairment,H.Person_Hearing_Percentage,MR.Total_Value AS MentalRetardation,MI.Total_Value AS MentalIllness from tblPerson_Personal_Details P   with (nolock) ");
                    strbuff.append("LEFT OUTER JOIN tblLower_Extremity_StabilityComponent_ClinicalMethod_Details L with(nolock)  ON L.Person_Code = P.Person_Code AND L.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblUpper_Extremity_HandComponent_Strength_Details U with(nolock) ON U.Person_Code = P.Person_Code AND U.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblAmputation_UpperLimbAmputation_Details A with(nolock) ON A.Person_Code = P.Person_Code AND A.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblCongenitalDeficienciesLimb_Transverse_Details T with(nolock) ON T.Person_Code = P.Person_Code AND T.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblTrunk_NonTraumaticLesions_Details TR with(nolock) ON TR.Person_Code = P.Person_Code AND TR.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblPhysical_Impairments_in_Neurological_Details E with(nolock) ON E.Person_Code = P.Person_Code AND E.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblCardio_Pulmonary_Conditions_Details C with(nolock) ON C.Person_Code = P.Person_Code AND C.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN  tblDwarfism_Details D with(nolock) ON D.Person_Code = P.Person_Code AND D.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN tblVisualImpairment_Details V with(nolock) ON V.Person_Code = P.Person_Code AND V.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN tblHearing_Impairment_Test_Details_for_Persons H with(nolock) ON H.Person_Code = P.Person_Code AND H.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN tblMental_Retardation_Details MR with(nolock) ON MR.Person_Code = P.Person_Code AND MR.Status = 'Active' ");
                    strbuff.append("LEFT OUTER JOIN tblMental_Disability_Illness_Details MI with(nolock) ON MI.Person_Code = P.Person_Code AND MI.Status = 'Active' ");
                    strbuff.append("where P.Person_code = ? and P.Status = 'Active'");
                    pstmt = con.prepareStatement(strbuff.toString());
                    pstmt.setString(1, personCode);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        if (rs.getString("UpperExtremity_Total") != null && !"".equals(rs.getString("UpperExtremity_Total"))) {
                            territoryDTO.setUpperExtremity_Total(rs.getString("UpperExtremity_Total"));
                        } else {
                            territoryDTO.setUpperExtremity_Total("0");
                        }
                        if (rs.getString("LowerExtremity_Total") != null && !"".equals(rs.getString("LowerExtremity_Total"))) {
                            territoryDTO.setLowerExtremity_Total(rs.getString("LowerExtremity_Total"));
                        } else {
                            territoryDTO.setLowerExtremity_Total("0");
                        }
                        if (rs.getString("Amputation_Total") != null && !"".equals(rs.getString("Amputation_Total"))) {
                            territoryDTO.setAmputation_Total(rs.getString("Amputation_Total"));
                        } else {
                            territoryDTO.setAmputation_Total("0");
                        }
                        if (rs.getString("Transverse_Total") != null && !"".equals(rs.getString("Transverse_Total"))) {
                            territoryDTO.setTransverse_Total(rs.getString("Transverse_Total"));
                        } else {
                            territoryDTO.setTransverse_Total("0");
                        }
                        if (rs.getString("Trunk_Total") != null && !"".equals(rs.getString("Trunk_Total"))) {
                            territoryDTO.setTrunk_Total(rs.getString("Trunk_Total"));
                        } else {
                            territoryDTO.setTrunk_Total("0");
                        }
                        if (rs.getString("Physical_Impairments_Total") != null && !"".equals(rs.getString("Physical_Impairments_Total"))) {
                            territoryDTO.setPhysical_Impairments_Total(rs.getString("Physical_Impairments_Total"));
                        } else {
                            territoryDTO.setPhysical_Impairments_Total("0");
                        }
                        if (rs.getString("Pulmonary_Condition") != null && !"".equals(rs.getString("Pulmonary_Condition"))) {
                            territoryDTO.setPulmonary_Condition(rs.getString("Pulmonary_Condition"));
                        } else {
                            territoryDTO.setPulmonary_Condition("0");
                        }
                        if (rs.getString("Dwarfism_Total") != null && !"".equals(rs.getString("Dwarfism_Total"))) {
                            territoryDTO.setDwarfism_Total(rs.getString("Dwarfism_Total"));
                        } else {
                            territoryDTO.setDwarfism_Total("0");
                        }
                        if (rs.getString("Visual_Impairment") != null && !"".equals(rs.getString("Visual_Impairment"))) {
                            if (rs.getString("Visual_Impairment").equals("101.0")) {
                                territoryDTO.setVisual_Impairment("100.0");
                            } else {
                                territoryDTO.setVisual_Impairment(rs.getString("Visual_Impairment"));
                            }
                        } else {
                            territoryDTO.setVisual_Impairment("0");
                        }
                        if (rs.getString("Person_Hearing_Percentage") != null && !"".equals(rs.getString("Person_Hearing_Percentage"))) {
                            territoryDTO.setHearing_Percentage(rs.getString("Person_Hearing_Percentage"));
                        } else {
                            territoryDTO.setHearing_Percentage("0");
                        }
                        if (rs.getString("MentalRetardation") != null && !"".equals(rs.getString("MentalRetardation"))) {
                            territoryDTO.setMental_Retardation_Total(rs.getString("MentalRetardation"));
                        } else {
                            territoryDTO.setMental_Retardation_Total("0");
                        }
                        if (rs.getString("MentalIllness") != null && !"".equals(rs.getString("MentalIllness"))) {
                            territoryDTO.setMental_Disability_Total(rs.getString("MentalIllness"));
                        } else {
                            territoryDTO.setMental_Disability_Total("0");
                        }

                    } else {
                        territoryDTO.setUpperExtremity_Total("0");
                        territoryDTO.setLowerExtremity_Total("0");
                        territoryDTO.setAmputation_Total("0");
                        territoryDTO.setTransverse_Total("0");
                        territoryDTO.setTrunk_Total("0");
                        territoryDTO.setPhysical_Impairments_Total("0");
                        territoryDTO.setPulmonary_Condition("0");
                        territoryDTO.setDwarfism_Total("0");
                        territoryDTO.setVisual_Impairment("0");
                        territoryDTO.setHearing_Percentage("0");
                        territoryDTO.setMental_Retardation_Total("0");
                        territoryDTO.setMental_Disability_Total("0");
                    }
                    break;

            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getDisabilityPercentagesAU", "CommonDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getDisabilityPercentagesAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getDisabilityPercentagesAU", "CommonDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getDisabilityPercentagesAU");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return territoryDTO;
    }

    /**
     *
     * @param ds DataSource
     *
     * @return feedback list
     */
    public ArrayList getcumulativeReport(DataSource datasource) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        Connection con = null;
        Statement stmt = null;
        PartADTO cumulativeDTO = null;
        ArrayList cumulativeDetailsList = null;
        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            cumulativeDetailsList = new ArrayList();
            rs = stmt.executeQuery("SELECT DISTRICTID,DISTRICTNAME,PHASEI,PHASEI_ASSESSED,PHASEI_DR,PHASEI_AR,"
                    + "PHASEI_ELIGIBLE,PHASEII,PHASEII_ASSESSED,PHASEII_DR,PHASEII_AR,PHASEII_ELIGIBLE,PHASEIII,"
                    + "PHASEIII_ASSESSED,PHASEIII_DR,PHASEIII_AR,PHASEIII_ELIGIBLE,PHASEIV,PHASEIV_ASSESSED,"
                    + "PHASEIV_DR,PHASEIV_AR,PHASEIV_ELIGIBLE,"
                    + " PHASEI + PHASEII + PHASEIII + PHASEIV AS TOTAL,"
                    + " PHASEI_ASSESSED + PHASEII_ASSESSED + PHASEIII_ASSESSED + PHASEIV_ASSESSED AS TOTALASSESSED,"
                    + " PHASEI_DR + PHASEII_DR + PHASEIII_DR + PHASEIV_DR AS TOTALDR,"
                    + " PHASEI_AR + PHASEII_AR + PHASEIII_AR + PHASEIV_AR AS TOTALAR,"
                    + " PHASEI_ELIGIBLE + PHASEII_ELIGIBLE + PHASEIII_ELIGIBLE + PHASEIV_ELIGIBLE AS TOTALELIGIBLE"
                    + " FROM STATEWISE_EVERYDAY_CUMULATIVEREPORT with(nolock) order by DISTRICTNAME asc");
            while (rs.next()) {
                cumulativeDTO = new PartADTO();
                cumulativeDTO.setDistrict(rs.getString("DISTRICTNAME"));
                cumulativeDTO.setPhaseOne(rs.getString("PHASEI"));
                cumulativeDTO.setPhaseIAssessed(rs.getString("PHASEI_ASSESSED"));
                cumulativeDTO.setPhaseIDR(rs.getString("PHASEI_DR"));
                cumulativeDTO.setPhaseIAR(rs.getString("PHASEI_AR"));
                cumulativeDTO.setPhaseIEligible(rs.getString("PHASEI_ELIGIBLE"));
                cumulativeDTO.setPhaseTwo(rs.getString("PHASEII"));
                cumulativeDTO.setPhaseIIAssessed(rs.getString("PHASEII_ASSESSED"));
                cumulativeDTO.setPhaseIIDR(rs.getString("PHASEII_DR"));
                cumulativeDTO.setPhaseIIAR(rs.getString("PHASEII_AR"));
                cumulativeDTO.setPhaseIIEligible(rs.getString("PHASEII_ELIGIBLE"));
                cumulativeDTO.setPhaseIII(rs.getString("PHASEIII"));
                cumulativeDTO.setPhaseIIIAssessed(rs.getString("PHASEIII_ASSESSED"));
                cumulativeDTO.setPhaseIIIDR(rs.getString("PHASEIII_DR"));
                cumulativeDTO.setPhaseIIIAR(rs.getString("PHASEIII_AR"));
                cumulativeDTO.setPhaseIIIEligible(rs.getString("PHASEIII_ELIGIBLE"));
                cumulativeDTO.setPhaseFour(rs.getString("PHASEIV"));
                cumulativeDTO.setPhaseIVAssessed(rs.getString("PHASEIV_ASSESSED"));
                cumulativeDTO.setPhaseIVDR(rs.getString("PHASEIV_DR"));
                cumulativeDTO.setPhaseIVAR(rs.getString("PHASEIV_AR"));
                cumulativeDTO.setPhaseIVEligible(rs.getString("PHASEIV_ELIGIBLE"));
                cumulativeDTO.setTotalPhases(rs.getString("TOTAL"));
                cumulativeDTO.setToatlAssessed(rs.getString("TOTALASSESSED"));
                cumulativeDTO.setTotalDR(rs.getString("TOTALDR"));
                cumulativeDTO.setTotalAR(rs.getString("TOTALAR"));
                cumulativeDTO.setTotalEligible(rs.getString("TOTALELIGIBLE"));
                cumulativeDetailsList.add(cumulativeDTO);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getcumulativeReport", "CommonDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getcumulativeReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getcumulativeReport", "CommonDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getcumulativeReport");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

        }
        return cumulativeDetailsList;
    }

    public String[] getPersionCode(DataSource ds, String pensionCardno, String districtId, String sadaremId,String aadhar) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String status = null;
        String[] personCode = new String[2];
        String sql = null;
        try {
            con = DBConnection.getConnection();
            
            if (!sadaremId.equals("")) {
                sql = "select Person_Code,View_Edit_Mode from tblPerson_Personal_Details  with (nolock) where person_code=? and Status='Active'";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, sadaremId);
               
            }else if (!aadhar.equals("")) {
                sql = "select Person_Code,View_Edit_Mode from tblPerson_Personal_Details  with (nolock) where proof_id=? and proofdoc_Type in ('Adhaar Card','Aadhaar Card','Aadhaar ID','Aadhar Card') and Status='Active'";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, aadhar);


              
            }
            else {
                sql = "select Person_Code,View_Edit_Mode from tblPerson_Personal_Details  with (nolock) where PensionCard_No=? and District_ID=? and Status='Active'";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, pensionCardno);
                pstmt.setString(2, districtId);
            }

            rs = pstmt.executeQuery();
            while (rs.next()) {
                    personCode[0] = rs.getString(1);
                    personCode[1] = rs.getString(2);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersionCode", "CommonDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getPersionCode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersionCode", "CommonDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getPersionCode");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return personCode;
    }

   

    public String[] getPersionCodes(DataSource ds, String pensionCardno, String districtId) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String status = null;
        String[] personCode = new String[2];
        String sql = null;
        try {
            con = DBConnection.getConnection();
            


            sql = "select Person_Code,View_Edit_Mode from tblPerson_Personal_Details  with (nolock) where PensionCard_No=? and District_ID=? and Status='Active'";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pensionCardno);
            pstmt.setString(2, districtId);


            rs = pstmt.executeQuery();
            while (rs.next()) {
                personCode[0] = rs.getString(1);
                personCode[1] = rs.getString(2);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersionCodes", "CommonDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getPersionCodes");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersionCodes", "CommonDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getPersionCodes");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return personCode;
    }

    public String getPersonStatusForDuplicate(DataSource ds, String sadaremCode) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String personCode = null;
        String sql = null;
        try {
            con = DBConnection.getConnection();
            


            sql = "select reasonforpersonstatus from tblPerson_Personal_Details  with (nolock) where person_code=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, sadaremCode);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                personCode = rs.getString(1);

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonStatusForDuplicate", "CommonDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getPersonStatusForDuplicate");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonStatusForDuplicate", "CommonDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getPersonStatusForDuplicate");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return personCode;
    }

    public String getPersonStatusBasedOnPensionId(DataSource ds, String district_id, String pensionCode) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String personCode = null;
        String sql = null;
        try {
            con = DBConnection.getConnection();
            


            sql = "select reasonforpersonstatus from tblPerson_Personal_Details  with (nolock) where pensioncard_no=? and district_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pensionCode);
            pstmt.setString(2, district_id);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                personCode = rs.getString(1);

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonStatusBasedOnPensionId", "CommonDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getPersonStatusBasedOnPensionId");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonStatusBasedOnPensionId", "CommonDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getPersonStatusBasedOnPensionId");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return personCode;
    }
    // Added by Mohan on 31/05/2011
 /*   public ArrayList getAreaWise(DataSource ds, String districtId, String mandalId, String habId, String name, String parentsName, String pensionCardNo, String sadaremId) throws SADAREMDBException, SQLException {
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    String status = null;
    String sql = null;
    String sqlRej = null;
    String personStatus = null;
    int stat = 0;
    ArrayList areaWise = new ArrayList();
    int a = 0;
    try {
    con = DBConnection.getConnection();
    stmt = con.createStatement();

    if (!sadaremId.equals("")) {
    sqlRej = " select person_status from tblPerson_personal_details where person_code='" + sadaremId + "'";
    rs = stmt.executeQuery(sqlRej);
    while (rs.next()) {
    personStatus = rs.getString(1);

    }
    }
    if (!pensionCardNo.equals("")) {
    sqlRej = " select person_status from tblPerson_personal_details where  pensioncard_no='" + pensionCardNo + "' and district_Id='" + districtId + "'";
    rs = stmt.executeQuery(sqlRej);
    while (rs.next()) {
    personStatus = rs.getString(1);

    }
    }

    if (personStatus == null) {
    personStatus = "A";
    }

    if (personStatus.equalsIgnoreCase("Eligible") || personStatus.equals("A")) {

    sql = "select a.Surname +' '+ a.First_Name as name,a.Age_Years as age,"
    + "case when a.Education=1 then 'Illiterate' when a.Education=2 then 'Below 10th' when a.Education=3 then '10th Class' "
    + "when a.Education=4 then 'Intermediate' when a.Education=5 then 'Diploma/ITI' when a.Education=6 then 'Graduate' "
    + "when a.Education=7 then 'Post Graduate' else 'Illiterate' end as qly,case when c.disability_id='1' "
    + "then 'Locomotor/OH' when c.disability_id='2' then 'Visual Impairment' when c.disability_id='3' "
    + "then 'Hearing Impairment' when c.disability_id='4' then 'Mental Retardation' when c.disability_id='5' "
    + "then 'Mental Illness' when c.disability_id='6' then 'Multiple Disability' end as disability,d.totaldisability,"
    + "coalesce(a.Phone_No,'0') as mobile,a.Person_Code,"
    + "a.PensionCard_No,a.relation_name,a.person_code,a.pensioncard_no,a.reasonforpersonstatus from "
    + "dbo.tblPerson_Personal_Details a  "
    + "left join tblPerson_disability_details c on(a.person_code=c.person_code)"
    + "left join dbo.tblPerson_Disability_TotalValue_Details d on(a.person_code=d.person_code and d.person_code=c.person_code) where ";


    if (!districtId.equals("") && mandalId.equals("") && habId.equals("") && pensionCardNo.equals("")) {
    sql += "a.district_Id='" + districtId + "'";
    a = 1;
    }

    if (!districtId.equals("") && !mandalId.equals("") && habId.equals("")) {
    sql += "a.district_Id='" + districtId + "' and a.mandal_Id='" + mandalId + "'";
    a = 5;
    }

    if (!districtId.equals("") && !mandalId.equals("") && !habId.equals("")) {
    if (a == 1) {
    sql += "and";
    }
    sql += "a.district_Id='" + districtId + "' and a.mandal_id='" + mandalId + "' and a.village_id='" + habId + "'";
    a = 2;
    }
    if (!name.equals("")) {
    if (a == 2 || a == 1 || a == 5) {
    sql += "and";
    }
    sql += " a.first_name like '%" + name.toString().trim() + "%'";
    a = 3;
    }
    if (!parentsName.equals("")) {
    if (a == 3 || a == 2 || a == 1 || a == 5) {
    sql += "and";
    }
    sql += " a.relation_name like '%" + parentsName.toString().trim() + "%'";
    a = 4;
    }
    if (!pensionCardNo.equals("") && !districtId.equals("")) {
    if (a == 3 || a == 2 || a == 1 || a == 5 || a == 4) {
    sql += "and";
    }
    sql += " a.pensioncard_no='" + pensionCardNo + "' and a.district_Id='" + districtId + "'";
    a = 6;
    }
    if (!sadaremId.equals("")) {
    if (a == 3 || a == 2 || a == 1 || a == 5 || a == 4 || a == 6) {
    sql += "and ";
    }
    sql += " a.person_code ='" + sadaremId + "'";
    a = 7;
    }
    if (a == 1 || a == 2 || a == 3 || a == 4 || a == 5 || a == 6 || a == 7) {
    sql += " and ";
    }
    sql += " a.Status='Active' order by a.Age_Years,a.Education,a.PensionCard_No,a.Person_Code";
    }

    if (personStatus.equalsIgnoreCase("Rejected")) {
    sql = "select a.Surname +' '+ a.First_Name as name,a.Age_Years as age,"
    + "case when a.Education=1 then 'Illiterate' when a.Education=2 then 'Below 10th' "
    + "when a.Education=3 then '10th Class' when a.Education=4 then 'Intermediate' "
    + "when a.Education=5 then 'Diploma/ITI' when a.Education=6 then 'Graduate' when a.Education=7 "
    + "then 'Post Graduate' else 'Illiterate' end as qly,case when c.disability_id='1' then 'Locomotor/OH' "
    + "when c.disability_id='2' then 'Visual Impairment' when c.disability_id='3' then 'Hearing Impairment' "
    + "when c.disability_id='4' then 'Mental Retardation' when c.disability_id='5' then 'Mental Illness' "
    + "when c.disability_id='6' then 'Multiple Disability' end as disability,totalValue.totaldisability,"
    + "coalesce(a.Phone_No,'0') as mobile,a.Person_Code,a.PensionCard_No,a.relation_name,a.person_code,"
    + "a.pensioncard_no,a.reasonforpersonstatus from dbo.tblPerson_Personal_Details a " +
    "join tblRejectPerson_Details b on(a.person_code=b.person_code) "
    +"left join tblPerson_disability_details c on(a.person_code=c.person_code) "
    + "left join dbo.tblPerson_Disability_TotalValue_Details totalValue on(a.person_code=totalValue.person_code and c.person_code=totalValue.person_code) "
    + "where ";

    if (!districtId.equals("") && mandalId.equals("") && habId.equals("") && pensionCardNo.equals("")) {
    sql += "a.district_Id='" + districtId + "'";
    a = 1;
    }

    if (!districtId.equals("") && !mandalId.equals("") && habId.equals("")) {
    sql += "a.district_Id='" + districtId + "' and a.mandal_Id='" + mandalId + "'";
    a = 5;
    }

    if (!districtId.equals("") && !mandalId.equals("") && !habId.equals("")) {
    if (a == 1) {
    sql += "and";
    }
    sql += "a.district_Id='" + districtId + "' and a.mandal_id='" + mandalId + "' and a.village_id='" + habId + "'";
    a = 2;
    }
    if (!name.equals("")) {
    if (a == 2 || a == 1 || a == 5) {
    sql += "and";
    }
    sql += " a.first_name like '%" + name.toString().trim() + "%'";
    a = 3;
    }
    if (!parentsName.equals("")) {
    if (a == 3 || a == 2 || a == 1 || a == 5) {
    sql += "and";
    }
    sql += " a.relation_name like '%" + parentsName.toString().trim() + "%'";
    a = 4;
    }
    if (!pensionCardNo.equals("") && !districtId.equals("")) {
    if (a == 3 || a == 2 || a == 1 || a == 5 || a == 4) {
    sql += "and";
    }
    sql += " a.pensioncard_no='" + pensionCardNo + "' and a.district_Id='" + districtId + "'";
    a = 6;
    }
    if (!sadaremId.equals("")) {
    if (a == 3 || a == 2 || a == 1 || a == 5 || a == 4 || a == 6) {
    sql += "and ";
    }
    sql += " a.person_code ='" + sadaremId + "'";
    a = 7;
    }
    if (a == 1 || a == 2 || a == 3 || a == 4 || a == 5 || a == 6 || a == 7) {
    sql += " and ";
    }

    sql += " a.Status='Active' order by a.Age_Years,a.Education,a.PensionCard_No,a.Person_Code";

    }

    rs = stmt.executeQuery(sql);
    while (rs.next()) {
    HashMap<String, Object> areaData = new HashMap<String, Object>();
    areaData.put("name", rs.getString(1));
    areaData.put("age", rs.getString(2));
    areaData.put("qly", rs.getString(3));
    areaData.put("disability", rs.getString(4));
    areaData.put("percentage", rs.getInt(5));
    areaData.put("mobile", rs.getString(6));
    areaData.put("Person_Code", rs.getString(7));
    areaData.put("PensionCard_No", rs.getString(8));
    areaData.put("relation_name", rs.getString(9));
    areaData.put("person_code", rs.getString(10));
    areaData.put("pensioncard_no", rs.getString(11));
    areaData.put("personStatus", rs.getString(12));
    areaWise.add(areaData);
    }
    //  }

    } catch (SQLException sqlException) {
    sqlException.printStackTrace();

    throw new SADAREMException();
    } catch (Exception e) {
    e.printStackTrace();
    throw new SADAREMException();
    } finally {
    DBConnection.closeResultSet(rs);
    DBConnection.closeStatement(stmt);
    DBConnection.closeConnection(con);

    }
    return areaWise;
    }*/

    public ArrayList getYears() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        ArrayList yearsList = new ArrayList();
        Map yrList = null;
        for (int i = 2010; i <= year; i++) {
            yrList = new HashMap();
            yrList.put("year", i);
            yearsList.add(yrList);
        }
        return yearsList;
    }

    public ArrayList getMonths(int year) {
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        ArrayList Month = new ArrayList();
        ArrayList totalMonth = new ArrayList();
        Map totalMnthList = null;
        totalMnthList = new HashMap();
        totalMnthList.put("id", "01");
        totalMnthList.put("month", "JAN");
        totalMonth.add(totalMnthList);
        totalMnthList = new HashMap();
        totalMnthList.put("id", "02");
        totalMnthList.put("month", "FEB");
        totalMonth.add(totalMnthList);
        totalMnthList = new HashMap();
        totalMnthList.put("id", "03");
        totalMnthList.put("month", "MAR");
        totalMonth.add(totalMnthList);
        totalMnthList = new HashMap();
        totalMnthList.put("id", "04");
        totalMnthList.put("month", "APR");
        totalMonth.add(totalMnthList);
        totalMnthList = new HashMap();
        totalMnthList.put("id", "05");
        totalMnthList.put("month", "MAY");
        totalMonth.add(totalMnthList);
        totalMnthList = new HashMap();
        totalMnthList.put("id", "06");
        totalMnthList.put("month", "JUN");
        totalMonth.add(totalMnthList);
        totalMnthList = new HashMap();
        totalMnthList.put("id", "07");
        totalMnthList.put("month", "JUL");
        totalMonth.add(totalMnthList);
        totalMnthList = new HashMap();
        totalMnthList.put("id", "08");
        totalMnthList.put("month", "AUG");
        totalMonth.add(totalMnthList);
        totalMnthList = new HashMap();
        totalMnthList.put("id", "09");
        totalMnthList.put("month", "SEP");
        totalMonth.add(totalMnthList);
        totalMnthList = new HashMap();
        totalMnthList.put("id", "10");
        totalMnthList.put("month", "OCT");
        totalMonth.add(totalMnthList);
        totalMnthList = new HashMap();
        totalMnthList.put("id", "11");
        totalMnthList.put("month", "NOV");
        totalMonth.add(totalMnthList);
        totalMnthList = new HashMap();
        totalMnthList.put("id", "12");
        totalMnthList.put("month", "DEC");
        totalMonth.add(totalMnthList);

        Map mthList = null;
        if (year == currentYear) {
            for (int i = 0; i <= currentMonth; i++) {
                HashMap m = new HashMap();
                mthList = new HashMap();

                m = (HashMap) totalMonth.get(i);
                mthList.put("id", m.get("id").toString());
                mthList.put("month", m.get("month").toString());

                Month.add(mthList);
            }
        } else {
            for (int i = 0; i <= 11; i++) {
                HashMap m = new HashMap();
                mthList = new HashMap();

                m = (HashMap) totalMonth.get(i);
                mthList.put("id", m.get("id").toString());
                mthList.put("month", m.get("month").toString());

                Month.add(mthList);
            }
        }
        return Month;
    }

    public String getMonth(String month) {
        switch (Integer.parseInt(month)) {
            case 1:
                return "JAN";
            case 2:
                return "FEB";
            case 3:
                return "MAR";
            case 4:
                return "APR";
            case 5:
                return "MAY";
            case 6:
                return "JUN";
            case 7:
                return "JUL";
            case 8:
                return "AUG";
            case 9:
                return "SEP";
            case 10:
                return "OCT";
            case 11:
                return "NOV";
            case 12:
                return "DEC";
        }
        return "";
    }

    public static void exportExcel(ArrayList dataList, ArrayList headerList, ArrayList keyList, String reportHeading, String reportName, HttpServletRequest request, HttpServletResponse response) {
        OutputStream out = null;
        try {
            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=" + reportName + ".xls");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());


            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);

            if (dataList.size() == 0) {
                s.addCell(new Label(0, 0, "No Data Found................"));
            } else {
                s.addCell(new Label(0, 0, reportHeading));
                s.mergeCells(0, 0, 6, 0);
                addHeaders(s, headerList);
            }

            int x = 2;
            for (int i = 0; i < dataList.size(); i++, x++) {
                int j = 0;
                Map map = (Map) dataList.get(i);

                s.addCell(new Label(j++, x, i + 1 + ""));
                for (int u = 0; u < keyList.size(); u++) {
                    s.addCell(new Label(j++, x, map.get(keyList.get(u)) + ""));
                }

                if (i == (50000) * page) {
                    int sheet = ++page;
                    s = w.createSheet("Sheet" + sheet, sheet - 1);
                    j = 0;
                    x = 2;
                    addHeaders(s, headerList);
                }



            }

            w.write();

            w.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void setWidth(WritableSheet s, int col, int widthInChars) {

        s.setColumnView(col, widthInChars);
    }

    public static void addHeaders(WritableSheet s, ArrayList list) {

        for (int x = 0; x < list.size(); x++) {
            try {
                s.addCell(new Label(x, 1, list.get(x).toString()));
            } catch (WriteException ex) {
                ex.printStackTrace();
            }
            setWidth(s, x, 20);
        }
    }

    /**
     * This method is for moving the file form one place to another
     * @param sourcePath
     * @param destinationPath
     * @return void
     * @throws Exception
     */
    public void moveFile(String sourcePath, String destinationPath, String file) throws Exception {

        try {

            File directory = new File(destinationPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File source = new File(sourcePath + "\\" + file);
            File destination = new File(destinationPath, file.trim());

            FileInputStream streamIn = new FileInputStream(source);
            FileOutputStream streamOut = new FileOutputStream(destination);

            int c;
            while ((c = streamIn.read()) != -1) {
                streamOut.write(c);
            }
            streamIn.close();
            streamOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList getFinancialYears() {
        Calendar cal = Calendar.getInstance();
        ArrayList yearsList = new ArrayList();
        Map yrList = null;
        int finalYear = 0;

        int year = cal.get(Calendar.YEAR);
        int mnt = cal.get(Calendar.MONTH);

        if (mnt == 1 || mnt == 2 || mnt == 3) {
            finalYear = year;
        } else {
            finalYear = year + 1;
        }

        for (int i = 2010; i <= finalYear; i++) {
            yrList = new HashMap();
            yrList.put("finyear", String.valueOf(i - 1) + "-" + String.valueOf(i));
            yearsList.add(yrList);
        }
        return yearsList;
    }

    public String getMonthStartDate(DataSource ds, String month, String year) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String date = "";
        try {
            con = DBConnection.getConnection();
            
            String sql = "select convert(varchar,DATEADD(month,?-1,DATEADD(year,?-1900,0)),103)  as date";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, month);
            pstmt.setString(2, year);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                date = rs.getString("date");
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMonthStartDate", "CommonDAO", "DataBase");

            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getMonthStartDate");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMonthStartDate", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getMonthStartDate");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return date;
    }

    public String getMonthEndtDate(DataSource ds, String month, String year) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String date = "";
        try {
            con = DBConnection.getConnection();
            
            String sql = "select convert(varchar,DATEADD(day,-1,DATEADD(month,?,DATEADD(year,?-1900,0))),103)  as date";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,Integer.parseInt( month));
            pstmt.setInt(2, Integer.parseInt(year));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                date = rs.getString("date");
            }
        } catch (SQLException sqlEx) {System.out.println(sqlEx);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMonthEndtDate", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getMonthEndtDate");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMonthEndtDate", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getMonthEndtDate");
        }
        finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return date;
    }

    public String getCurrentStartDate(String month, String year) throws Exception 
    {
       
        String result = "";
        try {
        
            String sql = "select convert(varchar,DATEADD(month," + month + ",DATEADD(year," + year + ",0)),103)  as date";

            result = DataAccess.getReturnResult(sql);
        }
        catch (Exception sqlEx)
        {
            sqlEx.printStackTrace();

        }
        finally
        {
           
        }

        return result;
    }

    public String getCurrentEndtDate(String month, String year) throws Exception {


        String result = "";
        try 
        {
            String sql = "select convert(varchar,getDate(),103)   as date";

            result = DataAccess.getReturnResult(sql);
        } catch (Exception e) 
        {
        	e.printStackTrace();
        } 
        
 
        return result;
    }

    public ArrayList getCampDetails(DataSource ds, String district_id) throws SADAREMDBException, SQLException {
        ArrayList campDetailsList = new ArrayList();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sql = null;
        Map campDetails = null;
        int i = 1;
        try {
            con = DBConnection.getConnection();
            
            sql = "select camp_id,name,address,venueName from dbo.tblCamp_Details with(nolock) where district_id=? and status='Active' order by name";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, district_id);

            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    campDetails = new HashMap();
                    campDetails.put("camp_id", rs.getString(1));
                    campDetails.put("camp_name", rs.getString(2) + "(" + rs.getString(3) + "," + rs.getString(4) + ")");
                    campDetailsList.add(campDetails);
                    i++;
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDetails", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getCampDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDetails", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getCampDetails");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }
        return campDetailsList;
    }
    public ArrayList getCampList(DataSource ds, String district_id) throws SADAREMDBException, SQLException {
        ArrayList campDetailsList = new ArrayList();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sql = null;
        Map campList = null;
        int i = 1;
        try {
            con = DBConnection.getConnection();
            
            sql = "select camp_id,venueName+address camp_name from dbo.tblCamp_Details with(nolock) where district_id='" + district_id + "' and status='Active' order by name";
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                	campList = new HashMap();
                	campList.put("camp_id", rs.getString(1));
                	campList.put("camp_name", rs.getString(2));
                    campDetailsList.add(campList);
                    i++;
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampList", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getCampDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampList", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getCampDetails");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }
        return campDetailsList;
    }
    public String getDistrictNameById(DataSource ds, String district_id) throws SADAREMDBException, SQLException {

        String districtName = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        try {

            con = DBConnection.getConnection();
            query = "select district_name from tblDistrict_Details with(nolock) where district_id=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, district_id);

            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    districtName = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistrictNameById", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getDistrictNameById");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistrictNameById", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getDistrictNameById");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
        }
        return districtName;
    }

    public String getHabcodeBySadaremId(DataSource ds, String sadaremid) throws SADAREMDBException, SQLException {
        String habitationcode = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        try {

            con = DBConnection.getConnection();
            query = "select habcode from tblPerson_Personal_Details with(nolock) where person_code=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, sadaremid);

            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    habitationcode = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHabcodeBySadaremId", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getHabcodeBySadaremId");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHabcodeBySadaremId", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getHabcodeBySadaremId");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
        }
        return habitationcode;
    }

    public String getPanchayatName(DataSource ds, String districtid, String mandalid, String panchayatid) throws SADAREMDBException, SQLException {
        String name = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        try {

            con = DBConnection.getConnection();
            query = "select Panchayat_Name from tblPanchayat_Details with(nolock) where district_id=? and mandal_id=? and panchayat_id=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtid);
            pstmt.setString(2, mandalid);
            pstmt.setString(3, panchayatid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                name = rs.getString(1);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillageName", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getVillageName");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillageName", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getVillageName");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
        }
        return name;
    }

    public String getVillageName(DataSource ds, String districtid, String mandalid, String villageid) throws SADAREMDBException, SQLException {
        String name = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        try {

            con = DBConnection.getConnection();
            query = "select village_name from tblvillage_details with(nolock) where district_id=? and mandal_id=? and village_id=?";
            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtid);
            pstmt.setString(2, mandalid);
            pstmt.setString(3, villageid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                name = rs.getString(1);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillageName", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getVillageName");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillageName", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getVillageName");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
        }
        return name;
    }

    public String getHabitationName(DataSource ds, String habcode) throws SADAREMDBException, SQLException {
        String name = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        try {

            con = DBConnection.getConnection();
            // Mandal Name
                query = "select habitation_name from tblHABITATION_Details with(nolock) where habitation_code=? ";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, habcode);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    name = rs.getString(1);
                }
           
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "TerritoryNames", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "TerritoryNames");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "TerritoryNames", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "TerritoryNames");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
        }
        return name;
    }

    public ArrayList getMandalsList(DataSource ds, String districtid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        ArrayList mandallist = new ArrayList();
        Map tlist = null;


        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "select mandal_id,mandal_name from tblMandal_Details with(nolock) where district_id=? order by mandal_name";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtid);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                tlist = new HashMap();
                tlist.put("mandal_id", rs.getString("mandal_id"));
                tlist.put("mandal_name", rs.getString("mandal_name"));
                mandallist.add(tlist);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandalsList", "PartADAO1", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getMandalsList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandalsList", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getMandalsList");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);

        }

        return mandallist;
    }

    public ArrayList getCampInchargeRoles(DataSource ds)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;

        ArrayList roles = new ArrayList();
        Map tlist = null;


        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "SELECT role_id,role_name  from roles with(nolock) where role_id in("+CommonConstants.CAMPINCHARGE_ROLES+") AND is_active='Y' order by role_name";
            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                tlist = new HashMap();
                tlist.put("id", rs.getString(1));
                tlist.put("name", rs.getString(2));
                roles.add(tlist);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampInchargeRoles", "PartADAO1", "DataBase");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getCampInchargeRoles");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampInchargeRoles", "PartADAO1", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO1", "getCampInchargeRoles");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);

        }

        return roles;
    }
    public ArrayList getrationCardDetails(DataSource ds, String personcode, String type, String rationcard, String aadhracard, String Pensionid, String districtid, PartAForm partaForm, HttpServletRequest request) throws SADAREMDBException, SQLException {
        ArrayList reportDetails = new ArrayList();
        StringBuffer sb = new StringBuffer();
        CallableStatement cstmt = null;
        Date campdate;
        Connection con = null;
        ResultSet rs = null;
        HashMap map = null;
        try {

            con = DBConnection.getConnection();
            if (type.equals("2") || type.equals("4")) {
                cstmt = con.prepareCall("{Call SP_SELECTDATAFORCERTIFICATES_SEARCH_NEW(?,?)}");
                if (type.equals("2")) {
                    cstmt.setString(1, rationcard);
                    cstmt.setString(2, "1");
                } else {
                    cstmt.setString(1, aadhracard);
                    cstmt.setString(2, "2");
                }
            } else {
                cstmt = con.prepareCall("{Call SP_SELECTDATAFORCERTIFICATES_NEW_Pension(?,?)}");
                cstmt.setString(1, Pensionid);
                cstmt.setString(2, districtid);
            }
            rs = cstmt.executeQuery();
            int i = 1;
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("Person_Code", rs.getString("Person_Code"));
                    map.put("First_Name", rs.getString("First_Name"));
                    map.put("TotalDisability", rs.getString("TotalDisability"));
                    map.put("Surname", rs.getString("Surname"));
                    map.put("First_Name", rs.getString("First_Name"));
                    map.put("Relation_Name", rs.getString("Relation_Name"));
                    request.setAttribute("percentage" + i, rs.getInt("TotalDisability"));
                    request.setAttribute("Surname", rationcard);
                    request.setAttribute("Pensionid", Pensionid);
                    request.setAttribute("aadhracard", aadhracard);
                    reportDetails.add(map);
                    i++;
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getrationCardDetails", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getrationCardDetails");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getrationCardDetails", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getrationCardDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return reportDetails;
    }

    public ArrayList getDistrictList(DataSource ds) throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList distList = new ArrayList();
        Map m = null;
        try {
            con = DBConnection.getConnection();

            stmt = con.createStatement();
            String query = "select District_ID ,District_Name from tblDistrict_Details with(nolock) order by District_Name";

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                m = new HashMap();
                m.put("district_id", rs.getString("District_ID"));
                m.put("district_name", rs.getString("District_Name"));

                distList.add(m);
            }
        } catch (SQLException sqlEx) {

            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistrictList", "CommonDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getDistrictList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistrictList", "CommonDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getDistrictList");
        } finally {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(stmt);
			DBConnection.closeConnection(con);

        }
        return distList;
    }

     public String getDisabilityName(DataSource ds,String disability_id) throws SADAREMDBException, SQLException {
        ArrayList disabilityList = new ArrayList();
        String disabilityName=null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sql = null;
        Map list = null;
        int i = 1;
        try {
            con = DBConnection.getConnection();
            
            sql = "select disability_name from dbo.tblDisability_Details with(nolock) where  disability_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, disability_id);

            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                     disabilityName = rs.getString(1);
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityName", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getDisabilitys");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityName", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getDisabilitys");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }
        return disabilityName;
    }
 public ArrayList getDisabilitys(DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList disabilityList = new ArrayList();
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        String sql = null;
        Map list = null;
        int i = 1;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            sql = "select disability_id,disability_name from dbo.tblDisability_Details with(nolock) where  disability_id<'6' ";

            rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    list = new HashMap();
                    list.put("disabilityid", rs.getString(1));
                    list.put("disabilityName", rs.getString(2) );
                    disabilityList.add(list);
                    i++;
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilitys", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getDisabilitys");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilitys", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getDisabilitys");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            DBConnection.closeConnection(con);
        }
        return disabilityList;
    }


// public String getDisabilityName(DataSource ds,String disability_id) throws SADAREMDBException, SQLException {
//        ArrayList disabilityList = new ArrayList();
//        String disabilityName=null;
//        Connection con = null;
//        ResultSet rs = null;
//        Statement st = null;
//        String sql = null;
//        Map list = null;
//        int i = 1;
//        try {
//            con = DBConnection.getConnection();
//            st = con.createStatement();
//            sql = "select disability_name from dbo.tblDisability_Details where  disability_id='" + disability_id + "'";
//
//            rs = st.executeQuery(sql);
//            if (rs != null) {
//                while (rs.next()) {
//                     disabilityName = rs.getString(1);
//                }
//            }
//
//
//        } catch (SQLException sqlEx) {
//            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityName", "CommonDAO", "DataBase");
//            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getDisabilitys");
//        } catch (Exception sqlEx) {
//            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityName", "CommonDAO", "Code");
//            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getDisabilitys");
//        } finally {
//            DBConnection.closeResultSet(rs);
//            DBConnection.closeStatement(st);
//            DBConnection.closeConnection(con);
//        }
//        return disabilityName;
//    }

 public String getAadharCardno(DataSource ds, String person_code) throws SADAREMDBException, Exception {
        Connection con = null;
        String query = null;
        PreparedStatement ps = null;
        String aadharcardno =null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            
            query = "select proof_id, person_code from tblperson_personal_details p  with (nolock)"
                    + "  where  proofdoc_type in ('Adhaar Card','Aadhaar Card','Aadhaar ID','Aadhar Card') and  person_code=? and proof_id not in ('-')";
            ps = con.prepareStatement(query);
            ps.setString(1, person_code);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    
                    aadharcardno= rs.getString(1);
                    
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAadharCardno", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getAadharCardno");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAadharCardno", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getAadharCardno");
        } finally {
            DBConnection.closeStatement(ps);
        }
        return aadharcardno;
    }

 public int getPhotoUploadDetails(DataSource ds, String person_code) throws SADAREMDBException, Exception {
        Connection con = null;
        String query = null;
        PreparedStatement ps = null;
        int count =0;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            
            query = "select count(*) from TBLPERSON_PHOTO_DETAILS   with (nolock)"
                    + "  where  person_code=?";
            ps = con.prepareStatement(query);
            ps.setString(1, person_code);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {

                    count= rs.getInt(1);

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPhotoUploadDetails", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getPhotoUploadDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPhotoUploadDetails", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getPhotoUploadDetails");
        } finally {
            DBConnection.closeStatement(ps);
        }
        return count;
    }
 public double getDisabilityPercentage(DataSource ds,String disability_id) throws SADAREMDBException, SQLException {
        double percentage=0 ;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sql = null;

        int i = 1;
        try {
            con = DBConnection.getConnection();
            
            sql = "select Eligiblepercentage from dbo.tblDisability_EligiblePercentages with(nolock) where  disability_id=? and status='Active'";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, disability_id);

            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                     percentage =Double.parseDouble(rs.getString(1));
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityPercentage", "CommonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getDisabilityPercentage");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityPercentage", "CommonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonDAO", "getDisabilityPercentage");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }
        return percentage;
    }



}
