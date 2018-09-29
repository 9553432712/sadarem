/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.common.DataBase;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;

/**
 *
 * @author t_bapinaidu
 */
public class JNDIDataSource {

public static DataSource getConnection() throws SADAREMDBException{
    DataSource ds=null;
     InitialContext ic;

        try{
//            SQLServerDataSource dssql = new SQLServerDataSource();
//            dssql.setUser("sa");
//            dssql.setPassword("CRT_hyd123");
//            dssql.setServerName("192.168.101.247\\SQLEXPRESS");
//            dssql.setPortNumber(1433);
//            dssql.setDatabaseName("Pension_for_Disabilities");
//            con = dssql.getConnection();

//                  InitialContext initCtx = new InitialContext();
//                  ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/AsistMedWebDataSource");

	     ic = new InitialContext();
           //  ds = (DataSource)ic.lookup("java:comp/env/jdbc/SADAREMTG");
	    //Context envContext = (Context)initContext.lookup("java:/comp/env");
           // ds = (DataSource)envContext.lookup("java:/SADAREMTG");
           // con = ds.getConnection();

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return ds;
    }

/*public static DataSource getCivilSuppliesConnection() throws SADAREMDBException{
    DataSource ds=null;
     InitialContext ic;

        try{
//            SQLServerDataSource dssql = new SQLServerDataSource();
//            dssql.setUser("sa");
//            dssql.setPassword("CRT_hyd123");
//            dssql.setServerName("192.168.101.247\\SQLEXPRESS");
//            dssql.setPortNumber(1433);
//            dssql.setDatabaseName("Pension_for_Disabilities");
//            con = dssql.getConnection();

//                  InitialContext initCtx = new InitialContext();
//                  ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/AsistMedWebDataSource");

	     ic = new InitialContext();
             ds = (DataSource)ic.lookup("java:comp/env/jdbc/civilSuppliesDS");
	    //Context envContext = (Context)initContext.lookup("java:/comp/env");
           // ds = (DataSource)envContext.lookup("java:/SADAREMTG");
           // con = ds.getConnection();

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return ds;
    } */
}
