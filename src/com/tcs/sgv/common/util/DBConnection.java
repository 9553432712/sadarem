/** ==============================================================
* Package			  : com.tcs.sgv.common.util;
* Filename			: DBConnection.java
* Purpose			  : provides methods realted to Connection classes
*==============================================================
* Date              Author              Version
*==============================================================
* 13/08/2003	   	Harjeet				        1.0
*==============================================================
*
* Modification History
*==============================================================
* Date              Author              Changes
*==============================================================
*
*
*==============================================================
*/

package com.tcs.sgv.common.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.DriverManager;
import java.sql.Driver;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/*
 * This bean connects to a datasource , gets connection and
 * excutes the queries and returns the resultset in a hashmap.
 */
public class DBConnection
{
  	private static Logger oslogger= Logger.getLogger(DBConnection.class);
   	private static Connection con = null;
   	private static Connection conml = null;   
   

  /*************************************************************************************
	 Method      :getConnection
	 Parameter   :
	 Return      :Connection object
	 Description :returns connection object
	***************************************************************************************/		
	
	public static Connection getConnection()
	{	
		String sJdbcDS = "";
		try	
			{
        ResourceBundle bundle = ResourceBundle.getBundle("SGVConstants");
        sJdbcDS = bundle.getString("DATASOURCE");        
        oslogger.info("Data source "+ sJdbcDS);
				Context ctx = new InitialContext();
				Context envContext = (Context)ctx.lookup("java:comp/env");
				DataSource dataSource = (DataSource)envContext.lookup(sJdbcDS);
				con = dataSource.getConnection();
        oslogger.info("Entering log details in log file==== "+ sJdbcDS);
			}
		
			catch(NamingException e)
			{
				oslogger.error("Error in Naming DBConnection.getConnection method " +e);
				e.printStackTrace();
			}
			catch(SQLException e)
			{
				oslogger.error("SQL Exception in DBConnection.getConnection method "+ e);
				e.printStackTrace();
			}
			
		if (con == null)
		{
			 con = getDriverManagerConnection();
		}
		return con;
	}
	/********************************************************************************
	 Method      :getDriverManagerConnection
	 Parameter   :
	 Return      :
	 Description :Reads Database Configuration information from configuration file
				  like the Driver name,user name,password and returns the Database Connection.
	***********************************************************************************/
	public static Connection getDriverManagerConnection()
	{
      String sDriver = "";
      String sJdbcUrl = "";
      String sUserName = "";
      String sPassword = "";
      try
		{
        ResourceBundle bundle = ResourceBundle.getBundle("SGVConstants");
        sDriver="oracle.jdbc.driver.OracleDriver";
        oslogger.info("Getting connection from driver manager");
        sJdbcUrl = bundle.getString("JDBC_URL");
        sUserName = bundle.getString("USER");
        sPassword = bundle.getString("PASSWORD");
        Class cClass = Class.forName(sDriver); // load driver
        oslogger.debug(" User name : " +sUserName + "  Password : "+sPassword +" Url : " +sJdbcUrl);
        if (!sDriver.equals((String)bundle.getString("DRIVER")))                  
        {
          DriverManager.registerDriver((Driver)cClass.newInstance());
        }
        conml  = DriverManager.getConnection(sJdbcUrl,sUserName ,sPassword);
      }
      catch(IllegalAccessException exception)
      {
        oslogger.error("Error in JDBC connection:"+exception.getMessage());
      }
      catch(ClassNotFoundException exception)
      {
        oslogger.error("Error in JDBC connection:"+exception.getMessage());
      }
      catch(InstantiationException exception)
      {
        oslogger.error("Error in JDBC connection:"+exception.getMessage());
      }
      catch(SQLException exception)
      {
        oslogger.error("Error in JDBC connection:"+exception.getMessage());
      }
      catch(Exception exception)
      {
        oslogger.error("Error in JDBC connection:"+exception.getMessage());
      }	  		
        return conml;
		}

		public static void closeConnection()
    {
      try {
        if (!con.isClosed())
        {
          con.close();          
        }
      } 
      catch (SQLException sqlex)
        {
         oslogger.error ("Not able to close connection  "+ sqlex); 
        }  
      }

      
    public static void closeConnection(Connection con)
    {
        try
        {
            if(con != null && !con.isClosed())
            {
                con.close();
                con = null;
            }
        }
        catch(SQLException sqlex)
        {
            oslogger.debug("Sql exception While Closing the connection  ", sqlex);
        }
        catch(Exception genExp)
        {
            oslogger.debug("General exception Not able to close connection  ", genExp);
        }
    }

    public static void closeStatement(Statement stmt)
    {
        try
        {
            if(stmt != null)
            {
                stmt.close();
                stmt = null;
            }
        }
        catch(SQLException sqlex)
        {
            oslogger.debug("Sql exception While Statement the Statement  ", sqlex);
        }
        catch(Exception genExp)
        {
            oslogger.debug("General exception Not able to close Statement  ", genExp);
        }
    }

    public static void closeResultSet(ResultSet rs)
    {
        try
        {
            if(rs != null)
            {
                rs.close();
                rs = null;
            }
        }
        catch(SQLException sqlex)
        {
            oslogger.debug("Sql exception While Closing the resultset  ", sqlex);
        }
        catch(Exception genExp)
        {
            oslogger.debug("General exception Not able to close resultset  ", genExp);
        }
    }

	}

	