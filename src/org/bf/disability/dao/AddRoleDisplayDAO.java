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
import org.bf.disability.form.ServiceBean;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is used for getting roles form database
 * @author Devi Prasad .T
 * @version 1.0
 */
public class AddRoleDisplayDAO {

    /**
     * 
     * @param ds 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getService(DataSource ds) throws SADAREMDBException, SQLException {

        ArrayList servicelist = new ArrayList();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ServiceBean servicebean;
        StringBuffer sql = new StringBuffer();

        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            sql.append("select * from services order by parent_id,priority");
            rs = stmt.executeQuery(sql.toString());
            while (rs.next()) {
                servicebean = new ServiceBean();
                servicebean.setService_id(rs.getString("service_id"));
                servicebean.setService_name(rs.getString("service_name"));
                servicebean.setParent_id(rs.getString("parent_id"));
                servicebean.setPriority(rs.getString("priority"));
                servicebean.setTarget_url(rs.getString("target_url"));
                servicebean.setAddflag("false");
                servicelist.add(servicebean);
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getService", "AddRoleDisplayDAO","DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddRoleDisplayDAO", "getService");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getService", "AddRoleDisplayDAO","Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddRoleDisplayDAO", "getService");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(stmt);
            
        }
        return servicelist;
    }
}
