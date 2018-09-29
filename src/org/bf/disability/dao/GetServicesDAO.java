/*
 *
 *
 *
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

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.ServiceBean;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is for getting services from database
 * @author Devi Prasad .T
 * @version 1.0
 */
public class GetServicesDAO {

    /** Creates a new instance of DailyEntryDisplayDAO */
    public GetServicesDAO() {
    }

    /**
     * 
     * @param ds 
     * @param roleid 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getService(DataSource ds, String roleid) throws SADAREMDBException, SQLException {

        ArrayList servicelist = new ArrayList();
        ArrayList servicelist1 = new ArrayList();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
       // Statement stmt1 = null;   
        PreparedStatement pstmt =null ;
        ResultSet rs1 = null;

        ServiceBean servicebean = null;
        String service_id;

        StringBuffer sql = new StringBuffer();
        StringBuffer sql1 = new StringBuffer();

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
          //  stmt1 = con.createStatement();
            sql1.append("select * from roles_services where role_id=? order by service_id");
            
            pstmt = con.prepareStatement(sql1.toString()); 
            
            pstmt.setString(1, roleid);
            
            rs1 = pstmt.executeQuery(); 
            
            
            while (rs1.next()) {
                service_id = rs1.getString("service_id"); 
                int i = 0;
                for (i = 0; i < servicelist.size(); i++) {
                    servicebean = (ServiceBean) servicelist.get(i);
                    if (service_id.equals(servicebean.getService_id())) {
                        ((ServiceBean) servicelist.get(i)).setSelect("on");
                        //servicelist1.add(servicebean);
                        break;
                    }
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getService", "GetServicesDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GetServicesDAO", "getService");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getService", "GetServicesDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GetServicesDAO", "getService");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeResultSet(rs1);
           DBConnection.closeStatement(stmt);
           //DBConnection.closeStatement(stmt1); 
           DBConnection.closeStatement(pstmt);
           DBConnection.closeConnection(con);
        }
        return servicelist;
    }
}
