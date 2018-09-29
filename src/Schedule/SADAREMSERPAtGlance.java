package Schedule;


 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Properties;

public class SADAREMSERPAtGlance 
{
   
    static Properties props;
   
    public static void main(String[] args)
    { 
    	insertRecordsfrmSadaremtoGlance();  
    }
   
    private static void insertRecordsfrmSadaremtoGlance()
    {

        // Declare the JDBC objects.
        Connection SqlConn = null;
        PreparedStatement SqlStmt = null;
        ResultSet SqlRs = null;
        Connection Db2Conn = null;
        PreparedStatement Db2Stmt = null;
        PreparedStatement updateStrmt = null;

        try 
        {
  
        	SqlConn = getSQLConnection();
        	Db2Conn = getDB2Connection();
            // Create and execute an SQL statement that returns data from SQL Server
            String sql = 
            				"SELECT ISNULL(district_id,'00') district_id,ISNULL(mandal_id,'00') mandal_id,ISNULL(fin_year,'All') fin_year,\n" +
            				"SUM(ISNULL(\"Locomotor/OH\",0)) TOTAL_DISABLEABLE_MEMEBER_LOCOMOTIVE_OH,\n" + 
            				"SUM(ISNULL(\"Hearing Impairment\",0)) TOTAL_DISABLE_MEMEBER_HEARING_IMPAIRMENT,\n" + 
            				"SUM(ISNULL(\"Mental Illness\",0)) TOTAL_DISABLE_MEMEBER_MENTAL_ILLNESS,\n" + 
            				"SUM(ISNULL(\"Mental Retardation\",0)) TOTAL_DISABLE_MEMEBER_MENTAL_RETARDATION,\n" + 
            				"SUM(ISNULL(\"Visual Impairment\",0)) TOTAL_DISABLE_MEMEBER_VISUAL_IMPAIRMENT,\n" + 
            				"SUM(ISNULL(\"Multiple Disability\",0)) TOTAL_DISABLE_MEMEBER_MULTIPLE,\n" + 
            				"SUM(ISNULL(\"Locomotor/OH\",0))+SUM(ISNULL(\"Hearing Impairment\",0))+SUM(ISNULL(\"Mental Illness\",0))+\n" + 
            				"SUM(ISNULL(\"Mental Retardation\",0))+SUM(ISNULL(\"Visual Impairment\",0))+SUM(ISNULL(\"Multiple Disability\",0)) TOTAL_DISABLE_MEMEBERS,\n" + 
            				"'Y' IS_ACTIVE,'SADAREM_ADMIN' CREATED_BY\n" + 
            				" FROM (\n" + 
            				"SELECT\n" + 
            				"ISNULL(districtid,'00') district_id,ISNULL(mandalid,'00') mandal_id,\n" + 
            				"ISNULL(fin_year,'All') fin_year,\n" + 
            				"disb_name,SUM(ISNULL(mycount,0)) total_count\n" + 
            				"FROM SADAREM_VIEW_AT_GLANCE\n" + 
            				"GROUP BY GROUPING SETS((districtid,mandalid,fin_year,disb_name))\n" + 
            				")a\n" + 
            				"pivot (max (total_count) for disb_name in\n" + 
            				"([Hearing Impairment],[Locomotor/OH],[Mental Illness],[Mental Retardation],[Multiple Disability],[Visual Impairment])) as pvt\n" + 
            				"GROUP BY CUBE((district_id,mandal_id),district_id,fin_year)\n" + 
            				"ORDER BY district_id,mandal_id,fin_year";

            SqlStmt = SqlConn.prepareStatement(sql);
            SqlRs = SqlStmt.executeQuery();

            
            

            updateStrmt =Db2Conn.prepareStatement("UPDATE SADAREM_AT_A_GLANCE SET is_active='N' --WHERE TRUNC(created_date)<>TRUNC(currernt_timestamp)");
            int updateStatus = updateStrmt.executeUpdate();
            System.out.println("Update Status = "+updateStatus);
            
            // Db2 SQL statement to insert data 
            sql =
            	 "INSERT INTO SADAREM_AT_A_GLANCE \n"+
            	 "( DISTRICT_ID,MANDAL_ID,FIN_YEAR,TOTAL_DISABLEABLE_MEMEBER_LOCOMOTIVE_OH,TOTAL_DISABLE_MEMEBER_HEARING_IMPAIRMENT,	\n"+
            	 " TOTAL_DISABLE_MEMEBER_MENTAL_ILLNESS,TOTAL_DISABLE_MEMEBER_MENTAL_RETARDATION,TOTAL_DISABLE_MEMEBER_VISUAL_IMPAIRMENT,\n"+
            	 " TOTAL_DISABLE_MEMEBER_MULTIPLE,TOTAL_DISABLE_MEMEBERS,IS_ACTIVE,CREATED_BY,CREATED_DATE)\n"+
            	 " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP)";
            Db2Stmt = Db2Conn.prepareStatement(sql);

            // Get MetaData for SQL table
            ResultSetMetaData metaData = SqlRs.getMetaData();
            int colCount = metaData.getColumnCount();

            int rowCount = 0;
            int successRows = 0;
            
            // Iterate through the SQL server data in the result set and update Db2 table
            while (SqlRs.next()) 
            {
              
              /*  for (int i = 0; i < colCount; i++) 
                {
                    System.out.println(metaData.getColumnName(i + 1) + ": " + SqlRs.getString(i+1));
                    System.out.println(metaData.getColumnTypeName(i + 1));
                }
*/
                Db2Stmt.setString(1,SqlRs.getString("district_id"));
                Db2Stmt.setString(2,SqlRs.getString("mandal_id"));
                Db2Stmt.setString(3,SqlRs.getString("fin_year"));
                Db2Stmt.setString(4,SqlRs.getString("TOTAL_DISABLEABLE_MEMEBER_LOCOMOTIVE_OH"));
                Db2Stmt.setString(5,SqlRs.getString("TOTAL_DISABLE_MEMEBER_HEARING_IMPAIRMENT"));
                Db2Stmt.setString(6,SqlRs.getString("TOTAL_DISABLE_MEMEBER_MENTAL_ILLNESS"));
                Db2Stmt.setString(7,SqlRs.getString("TOTAL_DISABLE_MEMEBER_MENTAL_RETARDATION"));
                Db2Stmt.setString(8,SqlRs.getString("TOTAL_DISABLE_MEMEBER_VISUAL_IMPAIRMENT"));
                Db2Stmt.setString(9,SqlRs.getString("TOTAL_DISABLE_MEMEBER_MULTIPLE"));
                Db2Stmt.setString(10,SqlRs.getString("TOTAL_DISABLE_MEMEBERS"));
                Db2Stmt.setString(11,SqlRs.getString("IS_ACTIVE"));
                Db2Stmt.setString(12,SqlRs.getString("CREATED_BY"));
                
                rowCount=rowCount+1;
                // Map all your database columns here
                
                if(Db2Stmt.executeUpdate()>0)
                {
                	successRows=successRows+1;
                    System.out.println(rowCount+" Row Status ------>Success");
                }
                else
                {
                    System.out.println(rowCount+" Row Status ------>Failed");
                }
            }
            
            
            System.out.println("Total Rows : "+rowCount+"======>Total Successfull Rows : "+successRows);
        }

        // Handle any errors that may have occurred.
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally 
        {
            if (SqlRs != null) try { SqlRs.close(); } catch(Exception e) {}
            if (SqlStmt != null) try { SqlStmt.close(); } catch(Exception e) {}
            if (SqlConn != null) try { SqlConn.close(); } catch(Exception e) {}
            if (Db2Stmt != null) try { Db2Stmt.close(); } catch(Exception e) {}
            if (Db2Conn != null) try { Db2Conn.close(); } catch(Exception e) {}
        } 

   
    }
    
    private static Connection getDB2Connection()
	{ 
		/*String url = null; 
		String nodeNumber = "0"; 
       	String userId = "shgadmin"; 
		String password = "Password123"; 
      	 String alias = "SMSDB"; 
       	String server = "172.25.152.191";
       	String portNumber = "50000";
		
		*/
		
		String url 			= null; 
		String nodeNumber 	= "0"; 
        String userId 		= "serp\\PMSCTG"; 
		String password 	= "msctgs@08012017"; 
    	String alias 		= "SERPTG"; 
   		String server 		= "10.10.18.98";
    	String portNumber 	= "3700"; 

		Connection lcon = null;
		
       try
			{

			Properties props = new Properties(); 

				if ( Integer.parseInt(portNumber) < 0 ) 
				{ 
					url = "jdbc:db2:" + alias; 
					//System.out.println("  Connect to '" + alias + "' database using JDBC Universal type 2 driver." ); 
					Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance(); 
				} 
				else 
				{ 
					url = "jdbc:db2://" + server + ":" + portNumber + "/" + alias; 
					//System.out.println("  Connect to DB2'" + alias + "' database using JDBC Universal type 4 driver." ); 
					Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance(); 
				} 

				if( null != userId ) 
				{ 
					props.setProperty("user", userId); 
					props.setProperty("password", password); 
				} 

			props.setProperty("CONNECTNODE", nodeNumber); 
			lcon = DriverManager.getConnection( url, props ); 
			//System.out.println("Connection Object:" +lcon);
			//lcon.close();
			System.out.println("DB2 Connected..");
			}
			catch(Exception e)
				{
				System.out.println("Error message..."+e.toString());
				e.printStackTrace();
				}

		
		return lcon; 
		
	}
	


	 private static Connection getSQLConnection() throws Exception
	 {
		
		/*String url = null; 
		String nodeNumber = "0"; 
      	String userId = "shgadmin"; 
		String password = "Password123"; 
     	 String alias = "SMSDB"; 
      	String server = "172.25.152.191";
      	String portNumber = "50000";
		*/
		
		String url 			= null; 
		String nodeNumber 	= "0"; 
       	String userId 		= "SADAREM_QUERYTOOL"; 
		String password 	= "Password@#123$$"; 
       	String alias 		= "SADAREMTG"; 
      	String server 		= "10.10.102.12";
       	String portNumber 	= "1433";
		 
		Connection lcon = null;
		
      try
			{

			Properties props = new Properties(); 

				if ( Integer.parseInt(portNumber) < 0 ) 
				{ 
					url = "jdbc:jtds:sqlserver:" + alias; 
					//System.out.println("  Connect to '" + alias + "' database using JDBC Universal type 2 driver." ); 
					Class.forName("net.sourceforge.jtds.jdbcx.JtdsDataSource").newInstance(); 
				} 
				else 
				{ 
					url = "jdbc:jtds:sqlserver://" + server + ":" + portNumber + "/" + alias; 
					//System.out.println("  Connect to DB2'" + alias + "' database using JDBC Universal type 4 driver." ); 
					Class.forName("net.sourceforge.jtds.jdbcx.JtdsDataSource").newInstance(); 
				} 

				if( null != userId ) 
				{ 
					props.setProperty("user", userId); 
					props.setProperty("password", password); 
				} 

			props.setProperty("CONNECTNODE", nodeNumber); 
			lcon = DriverManager.getConnection( url, props ); 
			//System.out.println("Connection Object:" +lcon);
			//lcon.close();
			System.out.println("SQL Connected..");
			}
			catch(Exception e)
				{
				System.out.println("Error message..."+e.toString());
				e.printStackTrace();
				}

		
		return lcon; 
	 }
 
	
   
}
