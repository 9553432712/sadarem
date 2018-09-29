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
import java.sql.SQLException;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is for deleting services roles from database
 * @author Devi Prasad .T
 * @version 1.0
 */
public class DeleteServicestoRoleDAO {

    /**
     * 
     * @param ds 
     * @param roleid 
     * @param service_id 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public int deleteServicestoRole(DataSource ds, String roleid, String service_id) throws SADAREMDBException, SQLException {
        Connection con = null;
       // Statement stmt = null; 
        PreparedStatement pstmt = null ;
        String sql; 
        int i = 0;
        try {
            con = DBConnection.getConnection();
          // stmt = con.createStatement();
            sql = "delete from roles_services where role_id=? and service_id=?";

            pstmt =con.prepareStatement(sql);
            
            pstmt.setString(1, roleid);
            pstmt.setString(2, service_id);
            
            i = pstmt.executeUpdate();

        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "deleteServicestoRole", "DeleteServicestoRoleDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DeleteServicestoRoleDAO", "deleteServicestoRole");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "deleteServicestoRole", "DeleteServicestoRoleDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DeleteServicestoRoleDAO", "deleteServicestoRole");
        } finally {

           //DBConnection.closeStatement(stmt);
           DBConnection.closeStatement(pstmt);
           DBConnection.closeConnection(con);

        }
        return i;
    }
}


