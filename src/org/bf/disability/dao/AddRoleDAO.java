/*
 * DistrictDAO.java
 *
 * Created on December 7, 2006, 11:17 AM
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

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is used for adding new role inforamtion to database
 * @author Devi Prasad .T
 * @version 1.0
 */
public class AddRoleDAO {

    /**
     * 
     * @param ds 
     * @param rolename 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public static int addRole(DataSource ds, String rolename) throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        Statement stmt1 = null; 
        PreparedStatement pstmt = null ; 
        
        ResultSet rs = null;
        int role_id = 0;
        int i = 0;
        try {
            con = DBConnection.getConnection();
          //  stmt = con.createStatement();
            stmt1 = con.createStatement();
            rs = stmt1.executeQuery("select max(role_id) from roles ");
            while (rs.next()) {
                role_id = (int) rs.getInt(1);
            }
            role_id++;
            String query = "insert into roles (role_id,role_name) values (?,?)"; 
            
            pstmt = con.prepareStatement(query);  
            
            pstmt.setString(1,role_id+"" );
            pstmt.setString(2,rolename );
            
            
            i = pstmt.executeUpdate();
            if (i != 0) {
                i = role_id;
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "addRole", "AddRoleDAO","DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddRoleDAO", "addRole");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "addRole", "AddRoleDAO","Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddRoleDAO", "addRole");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(pstmt);
           DBConnection.closeStatement(stmt1);


        }
        return i;
    }
}


