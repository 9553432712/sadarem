package com.tcs.sadarem.util;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import com.tcs.sgv.common.util.DBConnection;
public class DataAccess 
{
 
	public static ArrayList pickDataByPrepareStmtArrayListHashMap(String query,ArrayList paramList) throws Exception
	 {
		  ArrayList resultDataList	= new ArrayList();
		  ArrayList headerList 		= new ArrayList();
		  ArrayList tempList 		= new ArrayList();
		  
		  HashMap columnHashData = new HashMap();
		  		  
		  Connection lcon			= null; 
		  PreparedStatement prStmt  = null;
		  ResultSet lrs				= null;
		  
		  try
		    {
		      lcon = DBConnection.getConnection();
		      prStmt = lcon.prepareStatement(query); 
		      //System.out.println("paramList in DagaAccess : "+paramList);
		      
		      for(int looper=0;looper<paramList.size();looper++)
		      {
		    	    tempList = new ArrayList();
			    	tempList = (ArrayList)paramList.get(looper);
			    	
			    	if(tempList.get(0).toString().equalsIgnoreCase("S"))
			    	{
			  	  	  prStmt.setString(looper+1, tempList.get(1).toString().trim());
			    	}
			    	else if(tempList.get(0).toString().equalsIgnoreCase("I"))
			    	{
				  	  	  prStmt.setInt(looper+1, Integer.parseInt(tempList.get(1).toString().trim()));
				    }
			    	
		      }
		      //System.out.println("Query:\n"+query);
			  lrs = prStmt.executeQuery();     
			  
		      ResultSetMetaData rsMetaData=lrs.getMetaData();
		      int columnCount=rsMetaData.getColumnCount();
	  
		    	 if(columnCount>1)
		    	 { 
				      for(int i=1;i<=columnCount;i++)
				      {
				    	  headerList.add(rsMetaData.getColumnName(i));
				      } 
		    	 }
		    	 else
		    	 {
		    		 headerList.add(rsMetaData.getColumnName(1));
		    	 } 
		   	   
				      while(lrs.next())
				          {
				    	  	columnHashData = new HashMap();
				    	  	
				    	  	if(columnCount>1)
					    	 { 
						    
				              for(int loopCount=1;loopCount<=columnCount;loopCount++)
				              {
				            	// System.out.println("ColumnValue:"+  lrs.getString(loopCount)); 
				                if(lrs.getString(loopCount)!=null)                
				                {
				                	//System.out.println("Data in Main :"+lrs.getString(loopCount));
				                	 columnHashData.put(headerList.get(loopCount-1).toString().trim(), lrs.getString(loopCount));  
				                }
				               else
				                {
				            	   columnHashData.put(headerList.get(loopCount-1).toString().trim(), "");
				                  //System.out.println("In Varcharcommma");
				                }
				               
				              
				              }      
				              resultDataList.add(columnHashData); 
				           }
			    	     else
				    	  {  
			    	    	 
			    	    	 if(lrs.getString(1)!=null)                
				                {
				                	//System.out.println("Data in Main :"+lrs.getString(loopCount));
			                	 	columnHashData.put(headerList.get(0).toString().trim(), lrs.getString(1));
				                }
				               else
				                {
				                	 columnHashData.put(headerList.get(0).toString().trim(), ""); 
				                  //System.out.println("In Varcharcommma");
				                }
			    	    	  
			    	    	 resultDataList.add(columnHashData);
				    	  }
				        }
				   	  
		    
		   	  
		           if(lrs!=null)
		           {
		              DBConnection.closeResultSet(lrs);
		              lrs=null;
		           }
		          
		           if(lcon!=null)
		           {
		                DBConnection.closeConnection(lcon);
		                lcon=null;
		           }
		           
		      }    
		      catch(SQLException sqle)
		     {
		         throw sqle;
		      }
		     catch(Exception lexp)
		     {
		          throw lexp;
		     }
		     finally
		      {
		            DBConnection.closeResultSet(lrs);    
		            DBConnection.closeConnection(lcon);  
		       }
		                  
		        return resultDataList;
		   
		 }
	
	
	
	public static ArrayList pickDataByPrepareStmt(String query,ArrayList paramList,boolean ColumnNameStatus,boolean ColumnDataType) throws Exception
	 {
		  Connection lcon=null; 
		  PreparedStatement prStmt = null;
		    ResultSet lrs=null;
		  ArrayList DataList=new ArrayList();
		  ArrayList tempList = new ArrayList();
		  try
		    {
		      lcon = DBConnection.getConnection();
		      prStmt = lcon.prepareStatement(query); 
		      //System.out.println("paramList in DagaAccess : "+paramList);
		      for(int looper=0;looper<paramList.size();looper++)
		      {
		    	    tempList = new ArrayList();
			    	tempList = (ArrayList)paramList.get(looper);
			    	if(tempList.get(0).toString().equalsIgnoreCase("S"))
			    	{
			  	  	  prStmt.setString(looper+1, tempList.get(1).toString().trim());
			    	}
			    	else if(tempList.get(0).toString().equalsIgnoreCase("I"))
			    	{
				  	  	  prStmt.setInt(looper+1, Integer.parseInt(tempList.get(1).toString().trim()));
				    }
		      }
		      //System.out.println("Query:\n"+query);
			  lrs = prStmt.executeQuery();     
			  
		      ResultSetMetaData rsMetaData=lrs.getMetaData();
		      int columnCount=rsMetaData.getColumnCount();
		      if(ColumnDataType)
		      {
			      if(columnCount>1)
			      {
				      ArrayList colList=new ArrayList();
				      for(int i=1;i<=columnCount;i++)
				      {
				        colList.add(rsMetaData.getColumnTypeName(i));
				      }

				      DataList.add(colList);
			      }
			      else
			      {
			    	  DataList.add(rsMetaData.getColumnTypeName(1));
			      }
		      }
		      if(ColumnNameStatus)
		      {
		    	 if(columnCount>1)
		    	 {
				      ArrayList colList=new ArrayList();
				      for(int i=1;i<=columnCount;i++)
				      {
				        colList.add(rsMetaData.getColumnName(i));
				      }
				      DataList.add(colList);
		    	 }
		    	 else
		    	 {
		    		 DataList.add(rsMetaData.getColumnName(1));
		    	 }
		      }
		      while(lrs.next())
		          {
		    	  
				    	  if(columnCount>1)
					    	 {
					              ArrayList alist=new ArrayList();
					              for(int loopCount=1;loopCount<=columnCount;loopCount++)
					              {
					            	//System.out.println("ColumnValue:"+  lrs.getString(loopCount));
					            	
					                if(lrs.getString(loopCount)!=null)                
					                {
					                	//System.out.println("Data in Main :"+lrs.getString(loopCount));
					                
					                         alist.add(lrs.getString(loopCount));
					                      
					                }
					               else
					                {
					                  alist.add("");
					                  //System.out.println("In Varcharcommma");
					                }
					               
					              
					              }      
					              DataList.add(alist);
					    	 }
				    	     else
					    	  {
				    	    	 if(lrs.getString(1)!=null)                
					                {
					                	//System.out.println("Data in Main :"+lrs.getString(loopCount));
					                
				    	    		 DataList.add(lrs.getString(1));
					                      
					                }
					               else
					                {
					            	   DataList.add("");
					                  //System.out.println("In Varcharcommma");
					                }
					    	  }
		           }
		      
		      
		           if(lrs!=null)
		           {
		              DBConnection.closeResultSet(lrs);
		              lrs=null;
		           }
		           
		           if(prStmt!=null)
		           {
		        	   DBConnection.closeStatement(prStmt);
		           }
		           
		           if(lcon!=null)
		           {
		                DBConnection.closeConnection(lcon);
		                lcon=null;
		           }
		           
		      }    
		      catch(SQLException sqle)
		     {
		         throw sqle;
		      }
		     catch(Exception lexp)
		     {
		          throw lexp;
		     }
		     finally
		      {
		         if(lrs!=null)
		         {
		            DBConnection.closeResultSet(lrs);
		            lrs=null;
		         }
		         if(prStmt!=null)
		           {
		        	   DBConnection.closeStatement(prStmt);
		           }
		        
		         if(lcon!=null)
		         {
		             DBConnection.closeConnection(lcon);
		             lcon=null;         
		         }

		         try { lrs.close(); } catch(Exception e) {}
		         try { prStmt.close(); } catch(Exception e) {}		         
		         try { lcon.close(); } catch(Exception e) {}
		       }
		                  
		        return DataList;
		   
		 }
	
	
	public static ArrayList pickData(String query,boolean ColumnNameStatus,boolean ColumnDataType) throws Exception
	 {
		  Connection lcon=null;
		  Statement lstmt=null;
		    ResultSet lrs=null;
		  ArrayList DataList=new ArrayList();
		  try
		    {
		      lcon = DBConnection.getConnection();
		      lstmt = lcon.createStatement(); 
		      //System.out.println("Query:\n"+query);
		      lrs = lstmt.executeQuery(query);    
		      ResultSetMetaData rsMetaData=lrs.getMetaData();
		      int columnCount=rsMetaData.getColumnCount();
		      if(ColumnDataType)
		      {
			      ArrayList colList=new ArrayList();
			      for(int i=1;i<=columnCount;i++)
			      {
			        colList.add(rsMetaData.getColumnTypeName(i));
			      }
			      DataList.add(colList);
		      }
		      if(ColumnNameStatus)
		      {
			      ArrayList colList=new ArrayList();
			      for(int i=1;i<=columnCount;i++)
			      {
			        colList.add(rsMetaData.getColumnName(i));
			      }
			      DataList.add(colList);
		      }
		      while(lrs.next())
		          {
		              ArrayList alist=new ArrayList();
		              for(int loopCount=1;loopCount<=columnCount;loopCount++)
		              {
		            	//System.out.println("ColumnValue:"+  lrs.getString(loopCount));
		            	
		                if(lrs.getString(loopCount)!=null)                
		                {
		                	//System.out.println("Data in Main :"+lrs.getString(loopCount));
		                
		                         alist.add(lrs.getString(loopCount));
		                      
		                }
		               else
		                {
		                  alist.add("");
		                  //System.out.println("In Varcharcommma");
		                }
		               
		              
		              }      
		              DataList.add(alist);
		           }
		           if(lrs!=null)
		           {
		              DBConnection.closeResultSet(lrs);
		              lrs=null;
		           }
		           if(lstmt!=null)
		           {
		               DBConnection.closeStatement(lstmt);
		               lstmt=null;
		           }
		           if(lcon!=null)
		           {
		                DBConnection.closeConnection(lcon);
		                lcon=null;
		           }
		           
		      }    
		      catch(SQLException sqle)
		     {
		         throw sqle;
		      }
		     catch(Exception lexp)
		     {
		          throw lexp;
		     }
		  finally
	      {
	         if(lrs!=null)
	         {
	            DBConnection.closeResultSet(lrs);
	            lrs=null;
	         }
	         if(lstmt!=null)
	           {
	        	   DBConnection.closeStatement(lstmt);
	           }
	        
	         if(lcon!=null)
	         {
	             DBConnection.closeConnection(lcon);
	             lcon=null;         
	         }

	         try { lrs.close(); } catch(Exception e) {}
	         try { lstmt.close(); } catch(Exception e) {}		         
	         try { lcon.close(); } catch(Exception e) {}
	       }
		                  
		        return DataList;
		   
		 }
	
 public static ArrayList getData(String query) throws Exception
 {
  Connection lcon=null;
  Statement lstmt=null;
    ResultSet lrs=null;
  ArrayList DataList=new ArrayList();
  try
    {
      lcon = DBConnection.getConnection();
      
      lstmt = lcon.createStatement(); 
     // System.out.println("Query : \n"+query);
      lrs = lstmt.executeQuery(query);    
      ResultSetMetaData rsMetaData=lrs.getMetaData();
      int columnCount=rsMetaData.getColumnCount();
      DecimalFormat fmt = new DecimalFormat("#,##,##,#,#,##,##0.00");
      DecimalFormat fmt1 = new DecimalFormat("#,##,##,##,##,##,###");

      
      while(lrs.next())
          {
              ArrayList alist=new ArrayList();
              for(int loopCount=1;loopCount<=columnCount;loopCount++)
              {
            	////System.out.println("ColumnValue:"+  lrs.getString(loopCount));
            	
                if(lrs.getString(loopCount)!=null)                
                {
                	////System.out.println("ColumnType:"+rsMetaData.getColumnTypeName(loopCount));
                      if(rsMetaData.getColumnTypeName(loopCount).equalsIgnoreCase("DOUBLE") 
                    		  || rsMetaData.getColumnTypeName(loopCount).equalsIgnoreCase("Decimal")
                    		  || rsMetaData.getColumnTypeName(loopCount).equalsIgnoreCase("DECFLOAT")
                    		  || rsMetaData.getColumnTypeName(loopCount).equalsIgnoreCase("Float"))
                      {
                    	 ////System.out.println("In .00");
                      alist.add(fmt.format(lrs.getFloat(loopCount)));                      
                      }
                      else if( rsMetaData.getColumnTypeName(loopCount).equalsIgnoreCase("Integer")
                    		  || rsMetaData.getColumnTypeName(loopCount).equalsIgnoreCase("BIGINT"))
                      {
                      alist.add(fmt1.format(lrs.getFloat(loopCount)));  
                    ////System.out.println("In no point");
                      }
                      else
                      {
                         alist.add(lrs.getString(loopCount));
                        ////System.out.println("In default");
                      }
                }
               else if(rsMetaData.getColumnTypeName(loopCount).equals("Varchar") || rsMetaData.getColumnTypeName(loopCount).equals("char"))
                {
                  alist.add("");
                  ////System.out.println("In Varcharcommma");
                }
                else
                {
                  alist.add("0");
                  ////System.out.println("In Varchar0");
                }
              
              }      
              DataList.add(alist);
           }
           if(lrs!=null)
           {
              DBConnection.closeResultSet(lrs);
              lrs=null;
           }
           if(lstmt!=null)
           {
               DBConnection.closeStatement(lstmt);
               lstmt=null;
           }
           if(lcon!=null)
           {
                DBConnection.closeConnection(lcon);
                lcon=null;
           }
           
      }    
      catch(SQLException sqle)
     {
         System.out.print("SQLException in DataAccess class getData method : \n"+sqle);
         throw sqle;
      }
     catch(Exception lexp)
     {
    	 System.out.print("Exception in DataAccess class getData method : \n"+lexp);
    	 throw lexp;
     }
     finally
      {
         if(lrs!=null)
         {
            DBConnection.closeResultSet(lrs);
            lrs=null;
         }
         if(lstmt!=null)
         {
             DBConnection.closeStatement(lstmt);
             lstmt=null;
         }
         if(lcon!=null)
         {
             DBConnection.closeConnection(lcon);
             lcon=null;         
         }

         try { lrs.close(); } catch(Exception e) {}
         try { lstmt.close(); } catch(Exception e) {}
         try { lcon.close(); } catch(Exception e) {}
       }
                  
        return DataList;
   
 }
 public static ArrayList getaccData(String query) throws Exception
 {
	  Connection lcon=null;
	  Statement lstmt=null;
	    ResultSet lrs=null;
	  ArrayList DataList=new ArrayList();
	  try
	    {
	      lcon = DBConnection.getConnection();
	      lstmt = lcon.createStatement(); 
	   // System.out.println("Query:"+query);
	      lrs = lstmt.executeQuery(query);    
	      ResultSetMetaData rsMetaData=lrs.getMetaData();
	      int columnCount=rsMetaData.getColumnCount();
	      DecimalFormat fmt = new DecimalFormat("###########0.00");
	      DecimalFormat fmt1 = new DecimalFormat("##############");

	      while(lrs.next())
	          {
	              ArrayList alist=new ArrayList();
	              for(int loopCount=1;loopCount<=columnCount;loopCount++)
	              {
	            	   if(lrs.getString(loopCount)!=null)                
	                   {
	                   	////System.out.println("ColumnType:"+rsMetaData.getColumnTypeName(loopCount));
	                         if(rsMetaData.getColumnTypeName(loopCount).equalsIgnoreCase("DOUBLE") 
	                       		  || rsMetaData.getColumnTypeName(loopCount).equalsIgnoreCase("Decimal")
	                       		  || rsMetaData.getColumnTypeName(loopCount).equalsIgnoreCase("DECFLOAT")
	                       		  || rsMetaData.getColumnTypeName(loopCount).equalsIgnoreCase("Float"))
	                         {
	                       	 ////System.out.println("In .00");
	                         alist.add(fmt.format(lrs.getFloat(loopCount)));                      
	                         }
	                         else if( rsMetaData.getColumnTypeName(loopCount).equalsIgnoreCase("Integer")
	                       		  || rsMetaData.getColumnTypeName(loopCount).equalsIgnoreCase("BIGINT"))
	                         {
	                         alist.add(fmt1.format(lrs.getFloat(loopCount)));  
	                       ////System.out.println("In no point");
	                         }
	                         else
	                         {
	                            alist.add(lrs.getString(loopCount));
	                           ////System.out.println("In default");
	                         }
	                   }
	                  else if(rsMetaData.getColumnTypeName(loopCount).equals("Varchar") || rsMetaData.getColumnTypeName(loopCount).equals("char"))
	                   {
	                     alist.add("");
	                     ////System.out.println("In Varcharcommma");
	                   }
	                   else
	                   {
	                     alist.add("0");
	                     ////System.out.println("In Varchar0");
	                   }
	                 
	                 }      
	                 DataList.add(alist);
	              
	           }
	           if(lrs!=null)
	           {
	              DBConnection.closeResultSet(lrs);
	              lrs=null;
	           }
	           if(lstmt!=null)
	           {
	               DBConnection.closeStatement(lstmt);
	               lstmt=null;
	           }
	           if(lcon!=null)
	           {
	                DBConnection.closeConnection(lcon);
	                lcon=null;
	           }
	           
	      }    
	      catch(SQLException sqle)
	     {
	         throw sqle;
	      }
	     catch(Exception lexp)
	     {
	          throw lexp;
	     }
	     finally
	      {
	         if(lrs!=null)
	         {
	            DBConnection.closeResultSet(lrs);
	            lrs=null;
	         }
	         if(lstmt!=null)
	         {
	             DBConnection.closeStatement(lstmt);
	             lstmt=null;
	         }
	         if(lcon!=null)
	         {
	             DBConnection.closeConnection(lcon);
	             lcon=null;         
	         }

	         try { lrs.close(); } catch(Exception e) {}
	         try { lstmt.close(); } catch(Exception e) {}
	         try { lcon.close(); } catch(Exception e) {}
	       }
	                  
	        return DataList;
	   
	 }
 public static ArrayList getUploadData(String query) throws Exception
 {
  Connection lcon=null;
  Statement lstmt=null;
    ResultSet lrs=null;
  ArrayList DataList=new ArrayList();
  try
    {
      lcon = DBConnection.getConnection();
      lstmt = lcon.createStatement(); 
    //System.out.println("Query:"+query);
      lrs = lstmt.executeQuery(query);    
      ResultSetMetaData rsMetaData=lrs.getMetaData();
      int columnCount=rsMetaData.getColumnCount();
      while(lrs.next())
          {
              ArrayList alist=new ArrayList();
              for(int loopCount=1;loopCount<=columnCount;loopCount++)
              {
                if(lrs.getString(loopCount)!=null)                
                {
                	////System.out.println("Notnull");
                  alist.add(lrs.getString(loopCount));
                }

                else if(rsMetaData.getColumnTypeName(loopCount).equals("Varchar"))
                {
                	////System.out.println("Varchar");
                  alist.add("");
                }
                else
                {
                  alist.add("0");
                }
              
              }      
              DataList.add(alist);
           }
           if(lrs!=null)
           {
              DBConnection.closeResultSet(lrs);
              lrs=null;
           }
           if(lstmt!=null)
           {
               DBConnection.closeStatement(lstmt);
               lstmt=null;
           }
           if(lcon!=null)
           {
                DBConnection.closeConnection(lcon);
                lcon=null;
           }
           
      }    
      catch(SQLException sqle)
     {
         throw sqle;
      }
     catch(Exception lexp)
     {
          throw lexp;
     }
     finally
      {
         if(lrs!=null)
         {
            DBConnection.closeResultSet(lrs);
            lrs=null;
         }
         if(lstmt!=null)
         {
             DBConnection.closeStatement(lstmt);
             lstmt=null;
         }
         if(lcon!=null)
         {
             DBConnection.closeConnection(lcon);
             lcon=null;         
         }

         try { lrs.close(); } catch(Exception e) {}
         try { lstmt.close(); } catch(Exception e) {}
         try { lcon.close(); } catch(Exception e) {}
       }
                  
        return DataList;
   
 }



public static String executeUpdate(String strQuery) throws Exception
  {
  Connection lcon=null;
  PreparedStatement lPstmt = null;
  String msg = "";
  try
  {
    lcon = DBConnection.getConnection();
    lPstmt = lcon.prepareStatement(strQuery);
    int iCnt = lPstmt.executeUpdate();
    if(iCnt == 0)  { msg = "Failed";  }
          else { msg = "Details Saved Successfully"; }
    }
     catch(SQLException sqle)
      {
          System.out.println(sqle);
          msg="Failed";
          sqle.printStackTrace();
      }
      catch(Exception lexp)
      {
    	  msg="Failed";
    	  System.out.println(lexp);
    	  lexp.printStackTrace();
      }
      finally
      {
          
          if(lPstmt!=null)
          {
              DBConnection.closeStatement(lPstmt);
              lPstmt=null;
          }
          if(lcon!=null)
          {
              DBConnection.closeConnection(lcon);
              lcon=null;         
          }
          

	         try { lPstmt.close(); } catch(Exception e) {}
	         try { lcon.close(); } catch(Exception e) {}
      }
      return msg;
    }


public static int updateStatementExecute(String strQuery) 
{
Connection lcon=null;
PreparedStatement lPstmt = null;
int msg = -1;
try
{
  lcon = DBConnection.getConnection();
  lPstmt = lcon.prepareStatement(strQuery);
  msg = lPstmt.executeUpdate();


  }
   catch(SQLException sqle)
    {
        System.out.println(sqle);
        msg=-1;
        sqle.printStackTrace();
    }
    catch(Exception lexp)
    {
        msg=-1;
  	  System.out.println(lexp);
  	  lexp.printStackTrace();
    }
    finally
    {
        
        if(lPstmt!=null)
        {
            DBConnection.closeStatement(lPstmt);
            lPstmt=null;
        }
        if(lcon!=null)
        {
            DBConnection.closeConnection(lcon);
            lcon=null;         
        }
        

	         try { lPstmt.close(); } catch(Exception e) {}
	         try { lcon.close(); } catch(Exception e) {}
    }
    return msg;
  }


public static String getReturnResult(String queryBody) throws Exception 
{
  Connection con=null;
  Statement stmt=null;
  ResultSet rs=null;  
  String returnResultString="";
  try{
    con = DBConnection.getConnection();
    stmt = con.createStatement();
    rs = stmt.executeQuery(queryBody);
    while(rs.next())
    {
      returnResultString = rs.getString(1);
    }
  }
  catch(SQLException sqle) {
         throw sqle;
  }
  catch(Exception e) {
      throw e;
  }
  finally 
  {
     if(rs!=null) {
        DBConnection.closeResultSet(rs);
        rs=null;
     }
     if(stmt!=null) {
         DBConnection.closeStatement(stmt);
         stmt=null;
     }
     if(con!=null) 
     {
         DBConnection.closeConnection(con);
         con=null;
     }
     
     try { rs.close(); } catch(Exception e) {}
     try { stmt.close(); } catch(Exception e) {}
     try { con.close(); } catch(Exception e) {}
  }
  return returnResultString;
}

public static String getReturnResultByPstmt(String queryBody,ArrayList paramList) throws Exception 
{
  Connection lcon=null;
  PreparedStatement prStmt=null;
  ResultSet rs=null;  
  String returnResultString="";
  ArrayList tempList = new ArrayList();
  
  try{
		  lcon = DBConnection.getConnection();
		  prStmt = lcon.prepareStatement(queryBody); 
	    for(int looper=0;looper<paramList.size();looper++)
	    {
	    	tempList = new ArrayList();
	    	tempList = (ArrayList)paramList.get(looper);
	    	if(tempList.get(0).toString().equalsIgnoreCase("S"))
	    	{
	  	  	  prStmt.setString(looper+1, tempList.get(1).toString().trim());
	    	}
	    	else if(tempList.get(0).toString().equalsIgnoreCase("I"))
	    	{
		  	  	  prStmt.setInt(looper+1, Integer.parseInt(tempList.get(1).toString().trim()));
		    }
	    }
	    //System.out.println("Query:\n"+query);
		rs = prStmt.executeQuery();
	    while(rs.next())
	    {
	      returnResultString = rs.getString(1);
	    }
	  }
  catch(SQLException sqle) 
  {
         throw sqle;
  }
  catch(Exception e) {
      throw e;
  }
  finally {
     if(rs!=null) {
        DBConnection.closeResultSet(rs);
        rs=null;
     }
     if(prStmt!=null) {
         DBConnection.closeStatement(prStmt);
         prStmt=null;
     }
     if(lcon!=null) {
         DBConnection.closeConnection(lcon);
         lcon=null;
     }
     
     try { rs.close(); } catch(Exception e) {}
     try { prStmt.close(); } catch(Exception e) {}
     try { lcon.close(); } catch(Exception e) {}
  }
  return returnResultString;
}
public static String executeUpdateMany(ArrayList qryList) throws Exception
{
Connection lcon=null;
PreparedStatement lPstmt = null;
String msg1 = "";
String msg2="";
String Query1="";
String Query2="";
Query1=(String)qryList.get(0);

try
{
  lcon = DBConnection.getConnection();
  lcon.setAutoCommit(false);
  lPstmt = lcon.prepareStatement(Query1);
  int iCnt = lPstmt.executeUpdate();
   System.out.println("Query1--->"+Query1);
  if(iCnt == 0) 
   { msg1 = "Failed";}
  else
   {
	  msg1 = "Details Saved Successfully";
	  for(int i=1;i<qryList.size();i++)
	  {     
		   Query2=(String)qryList.get(i);
		   //System.out.println("Query2--->"+Query2);
		   lPstmt = lcon.prepareStatement(Query2);
		   iCnt = lPstmt.executeUpdate();

		   if(iCnt == 0) 
		   { 
			   msg2 = "Failed";
			   break;
		   }
		  else
		   {
			  msg2 = "Details Saved Successfully";
		   }
	  }
    }
   if(msg1.equals("Details Saved Successfully")&& msg2.equals("Details Saved Successfully"))
   {
	   lcon.commit();
   }
   else
   {
	   lcon.rollback();
   }
   
  }
   catch(SQLException sqle)
    {
        throw sqle;
    }
    catch(Exception lexp)
    {
        throw lexp;
    }
    finally
    {
        
        if(lPstmt!=null)
        {
            DBConnection.closeStatement(lPstmt);
            lPstmt=null;
        }
        if(lcon!=null)
        {
            DBConnection.closeConnection(lcon);
            lcon=null;         
        }
        
        try { lPstmt.close(); } catch(Exception e) {}
        try { lcon.close(); } catch(Exception e) {}
    }
    return msg2;
  }
	
	public static ArrayList getStoredProcedureResultSet(String query ,ArrayList inparamlist,int outsize,boolean ColumnNameStatus,boolean ColumnDataType) throws Exception
	{
	  ArrayList resultlist=new ArrayList();
	  ResultSet rs = null;
	  Connection conn =null;
	  CallableStatement stmt = null;
	    try 
	    {

	    	conn=DBConnection.getConnection();
	      	stmt = conn.prepareCall(query); 
	   
    		if(inparamlist.size()>0)
    		{
		  		  for(int k=0;k<inparamlist.size();k++)
		  	  		{
		  			  stmt.setString((k+1), (String)inparamlist.get(k)); // DEPTNO
		  	  		}
    		}
    		
    		 for(int k=0;k<outsize;k++)
	   	  		{
    			 System.out.println("outErrorCode : "+k+" ==>"+inparamlist.size()+(k+1));
 	      		
		      		stmt.registerOutParameter(inparamlist.size()+(k+1), Types.CHAR); //Out Parameter Registration
	   	  		}
    		 
	      	stmt.execute();
	      	
	      	ArrayList outparams=new ArrayList();
	      	for(int m=0;m<outsize;m++)
	      	{
	      		String outval="";
	      		
	      		try
	      		{
	      		 System.out.println("M ("+m+")==>Name ()==>Value("+stmt.getString(inparamlist.size()+m+1)+")");
	      		
		      		//outvalName=stmt.getMetaData().getColumnName(inparamlist.size()+m+1);
		      		outval=stmt.getString(inparamlist.size()+m+1);
	      		}catch(Exception e)
	      		{
	      			System.out.println("Out Param Exception : "+e.getLocalizedMessage());
	      		}
	      		
		      	if(outval!=null)
		      	{
		      		outparams.add(outval);
		      	}
	      		
	      	}
	      	
	      	if(outparams.size()>0)
	      	{
	      		resultlist.add(outparams);
	      	}
	      	
    		/*if (stmt.getFetchSize()>0) 
    		{*/
      		//System.out.println("Procedure completed successfully");
	      	try
	      	{
	      		if(stmt.getResultSet()!=null)
	      		{
	       		 do
	        		{
       			
      	 		 ArrayList datalist=new ArrayList();
	          		 rs = stmt.getResultSet(); 
	          		 
	         		 ResultSetMetaData rsMetaData=rs.getMetaData();
	          		int columnCount=rsMetaData.getColumnCount();
	          
	          	  if(ColumnDataType)
	    	      {
	    		      ArrayList colList=new ArrayList();
	    		      for(int i=1;i<=columnCount;i++)
	    		      {
	    		        colList.add(rsMetaData.getColumnTypeName(i));
	    		      }
	    		      datalist.add(colList);
	    	      }
	          	  
	          		if(ColumnNameStatus)
		  		      {
		  			      ArrayList colList=new ArrayList();
		  			      for(int i=1;i<=columnCount;i++)
		  			      {
		  			        colList.add(rsMetaData.getColumnName(i));
		  			      }
		  			    datalist.add(colList);
		  		      }
	          		
	          		while (rs.next()) 
	        		  {
	        	  		ArrayList data=new ArrayList();
	        	  		for(int i=1;i<=columnCount;i++)
				   	       {
		        		  	data.add(rs.getString(i));
				   	       }
	        	 		 datalist.add(data);
	          		  }
		          		if(datalist.size()>0)
		          		{
		          			resultlist.add(datalist);
		          		}
		          		else
		          		{
		          			resultlist.add(new ArrayList());
		          		}
	        		}
	       		 while(stmt.getMoreResults());
	      		}
	      	}
	      	catch(Exception e)
	      	{
	      		System.out.println("Exception at Result Set : "+e.getLocalizedMessage());
	      	}
        // close ResultSet
    		/*}
	       else  
			{ // stored procedure failed
				System.out.println("No ResultSets : "+ stmt.getFetchSize());
		   	}*/
	    }
	    catch (SQLException e) 
	    {
	      System.out.println("Procedure Exception : "+e.getLocalizedMessage());
	    }
	    finally
	    {
	    	if(rs!=null){rs.close();}
	    	if(stmt!=null){stmt.close();}
	        if(conn!=null){ conn.close();}
	        
	        try { rs.close(); } catch(Exception e) {}
	         try { stmt.close(); } catch(Exception e) {}
	         try { conn.close(); } catch(Exception e) {}
	    }
	    //System.out.println("Resultlist : "+resultlist);
	    return resultlist;
	}
	
	
	public static ArrayList getnocommas(String query) throws Exception
	 {
		  Connection lcon=null;
		  Statement lstmt=null;
		    ResultSet lrs=null;
		  ArrayList DataList=new ArrayList();
		  try
		    {
		      lcon = DBConnection.getConnection();
		      lstmt = lcon.createStatement(); 
		   // System.out.println("Query:"+query);
		      lrs = lstmt.executeQuery(query);    
		      ResultSetMetaData rsMetaData=lrs.getMetaData();
		      int columnCount=rsMetaData.getColumnCount();
		      while(lrs.next())
		          {
		              ArrayList alist=new ArrayList();
		              for(int loopCount=1;loopCount<=columnCount;loopCount++)
		              {
		                if(lrs.getString(loopCount)!=null)                
		                {
		                  alist.add(lrs.getString(loopCount));
		                }

		                else if(rsMetaData.getColumnTypeName(loopCount).equals("Varchar"))
		                {
		                  alist.add("");
		                }
		                else
		                {
		                  alist.add("0");
		                }
		              
		              }      
		              DataList.add(alist);
		           }
		           if(lrs!=null)
		           {
		              DBConnection.closeResultSet(lrs);
		              lrs=null;
		           }
		           if(lstmt!=null)
		           {
		               DBConnection.closeStatement(lstmt);
		               lstmt=null;
		           }
		           if(lcon!=null)
		           {
		                DBConnection.closeConnection(lcon);
		                lcon=null;
		           }
		           
		      }    
		      catch(SQLException sqle)
		     {
		         throw sqle;
		      }
		     catch(Exception lexp)
		     {
		          throw lexp;
		     }
		     finally
		      {
		         if(lrs!=null)
		         {
		            DBConnection.closeResultSet(lrs);
		            lrs=null;
		         }
		         if(lstmt!=null)
		         {
		             DBConnection.closeStatement(lstmt);
		             lstmt=null;
		         }
		         if(lcon!=null)
		         {
		             DBConnection.closeConnection(lcon);
		             lcon=null;         
		         }
		         
		         try { lrs.close(); } catch(Exception e) {}
		         try { lstmt.close(); } catch(Exception e) {}
		         try { lcon.close(); } catch(Exception e) {}
		       }
		                  
		        return DataList;
		   
		 }
}