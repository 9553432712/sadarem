package com.tcs.sadarem.internalscreens.actions;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAOImpl;
import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

public class PartBDeletionDAOImpl implements PartBDeletionDAO{
	
	
	public int updatedeleterecord(String sadaremid,String aadharflag,String remarks) throws SQLException
	{
		
		int result=0;  int i = 0; 
		Connection con = null;
		try
		{
		
		 con = DBConnection.getConnection();
	     con.setAutoCommit(false);

			  
			   PreparedStatement lPstmt = null;
				CallableStatement calstmt = null;
		        ResultSet rs = null;
		       
		        try {
		        	
		            calstmt = con.prepareCall("{Call PRO_SADAREM_DELETION_OF_CERTI(?,?,?)}");
		            calstmt.setString(1,sadaremid);
		            calstmt.setString(2,aadharflag);
		            calstmt.setString(3,remarks);
		          
		            result = calstmt.executeUpdate();
		            calstmt.close();
		            System.out.println("i value-->"+i);
		           
		            
		        } catch (SQLException sqlEx) {
		        	result=0;
		         sqlEx.printStackTrace();		       
		        
		        }  
		        if(result>0)
			     {
	    		    con.commit();
	    		    
			     }
		        else
		        {
		        	con.rollback();
		        }
			
		        DBConnection.closeConnection(con);
		   
		}
		catch(Exception e)
		{
			DBConnection.closeConnection(con);
			result=0;
			e.printStackTrace();
		}
		 
		
		
		
		return result;
	
	}
	

}
