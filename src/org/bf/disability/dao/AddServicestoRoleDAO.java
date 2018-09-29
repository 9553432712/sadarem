package org.bf.disability.dao;
/*
 * DistrictDAO.java
 *
 * Created on December 7, 2006, 11:17 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is used for adding services for the roles into database
 * @author Devi Prasad .G
 * @version 1.0
 */
public class AddServicestoRoleDAO {

    /**
     * 
     * @param ds 
     * @param roleid 
     * @param serviceid 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public static int addServicestoRole(DataSource ds, String roleid, String serviceid) throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null; 
        PreparedStatement pstmt = null ;
        int i = 0;
        try {
            con = DBConnection.getConnection();
          
            String query = "insert into roles_services (role_id,service_id) values (?,?)";
    
            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, roleid);
            pstmt.setString(2, serviceid);
            
            i = pstmt.executeUpdate();

        } catch (SQLException sqlEx) { 
        	sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "addServicestoRole", "AddServicestoRoleDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddServicestoRoleDAO", "addServicestoRole");
        } catch (Exception sqlEx) {
        	sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "addServicestoRole", "AddServicestoRoleDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddServicestoRoleDAO", "addServicestoRole");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeStatement(pstmt);
        }
        return i;
    }
}


