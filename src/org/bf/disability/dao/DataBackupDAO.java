/*
 * DataBackupDAO.java
 *
 * Created on September 22, 2008, 2:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this method gets database back up from database
 * @author pramod
 * @version 1.0
 */
public class DataBackupDAO {

    /**
     * 
     * @param datasource 
     * @throws org.bf.disability.Exception.SADAREMException 
     */
    public void takeDataBackup(DataSource datasource) throws SADAREMDBException,SQLException {
        Connection con = null;
        CallableStatement cs = null;

//     TransverseDTO transverseupdatedto=new TransverseDTO();
        try {

//       con.setAutoCommit(false);

            con = DBConnection.getConnection();

            cs = con.prepareCall("{Call SP_TAKEBACKUP()}");
            cs.execute();
//       con.commit();
//       con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "takeDataBackup", "DataBackupDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DataBackupDAO", "takeDataBackup");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "takeDataBackup", "DataBackupDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DataBackupDAO", "takeDataBackup");
        }finally {
           DBConnection.closeStatement(cs);
           DBConnection.closeConnection(con);
        }
    }
}
