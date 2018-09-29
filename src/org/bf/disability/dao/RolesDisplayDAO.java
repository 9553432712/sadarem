/*
 * SocialAuditDAO.java
 *
 * Created on February 5, 2007, 4:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.Roles;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is for getting roles from database
 * @author Devi Prasad .T
 * @version 1.0
 */
public class RolesDisplayDAO {

    /** Creates a new instance of DailyEntryDisplayDAO */
    public RolesDisplayDAO() {
    }

    /**
     * 
     * @param ds 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getRoles(DataSource ds) throws SADAREMDBException, SQLException {

        ArrayList roleslist = new ArrayList();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Roles roles;
        StringBuffer sql = new StringBuffer();

        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            sql.append("select * from roles order by role_name");
            rs = stmt.executeQuery(sql.toString());
            while (rs.next()) {
                roles = new Roles();
                roles.setRoleid(rs.getString("role_id"));
                roles.setRolename(rs.getString("role_name"));

                roleslist.add(roles);
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRoles", "RolesDisplayDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RolesDisplayDAO", "getRoles");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRoles", "RolesDisplayDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RolesDisplayDAO", "getRoles");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

        }
        return roleslist;
    }
}
