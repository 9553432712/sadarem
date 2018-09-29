package com.tcs.sadarem.issuetracksystem.actions;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

public class IssueRequestRaiseReportImpl implements IssueRequestRaiseReportDAO
{	
	public ArrayList ExtractRoleNmaes()
	{
		int result;		 
		ArrayList RoleNames = new ArrayList();		
		try{			
				Connection con = null;
				con = DBConnection.getConnection();
				//con.setAutoCommit(false);			  
				PreparedStatement lPstmt = null;
				CallableStatement calstmt = null;
		        ResultSet rs = null; 
		        String qry="SELECT tkt_type_name 'Grievence Type', CASE WHEN [Beneficiary] IS NULL THEN 'False' ELSE 'True' END 'Beneficiary' ,\n"+
							" CASE WHEN [Camp Admin] IS NULL THEN 'False' ELSE 'True' END 'Camp Incharge' , \n"+
							" CASE WHEN [APM] IS NULL THEN 'False' ELSE 'True' END 'APM' , \n"+
							" CASE WHEN [DPM-SERP] IS NULL THEN 'False' ELSE 'True' END 'DPM' , \n"+
							" CASE WHEN [PD-SERP] IS NULL THEN 'False' ELSE 'True' END 'DRDO' , \n"
							+ "CASE WHEN [Admin Assistant] IS NULL THEN 'False' ELSE 'True' END 'Admin Assistant' , \n"+
							" CASE WHEN [Appellate Authority] IS NULL THEN 'False' ELSE 'True' END 'Appellate Authority' , \n"+
							" CASE WHEN [SPMU-SERP] IS NULL THEN 'False' ELSE 'True' END 'SPMU', \n"+
							" CASE WHEN [SPM] IS NULL THEN 'False' ELSE 'True' END 'SPM', \n"+
							" CASE WHEN [Director] IS NULL THEN 'False' ELSE 'True' END 'Director' FROM  \n"+
							" (select distinct tkt_type_name,  k.role_id, \n"+
							" CASE WHEN k.role_id='99' THEN 'APM' ELSE k.role_name END role_name  	from   	\n"+
							" tkt_type_master  t(nolock),  	tkt_role_type_mapping  r(nolock),  	roles k(nolock)	 \n"+
							" 	where t.tkt_type_id=r.tkt_type_id  and  k.role_id=r.role_id and k.role_id<>'32' and t.is_active='Y') as temp  PIVOt\n"+
							" ( sum(temp.role_id) for  temp.role_name in ([Beneficiary],[APM],[Camp Admin],[DPM-SERP],[Director],[PD-SERP],[SPMU-SERP],[SPM],[Appellate Authority],[Admin Assistant])) As p ";		       
		       // System.out.println("-----------");
		        RoleNames= columnData(qry);		       
		}catch(Exception e)
		{
			//System.out.println(" ---------------ExtractRoleNmaes Exception----------------------");
			e.printStackTrace();			
		}		
		return RoleNames;
	}
	

	public ArrayList ExtractRolesServicesMap() 
	{
		ArrayList RolesServicesMap = new ArrayList();	
		try
		{		
				Connection con = null;
				con = DBConnection.getConnection();						  
				Statement lPstmt = con.createStatement();
				//System.out.println("RolesToPivote------------"+RolesToPivote);
				String qry= "";
				qry = "SELECT tkt_type_name 'Grievence Type', CASE WHEN [Beneficiary] IS NULL THEN 'False' ELSE 'True' END 'Beneficiary' ,\n"+
						" CASE WHEN [Camp Admin] IS NULL THEN 'False' ELSE 'True' END 'Camp Incharge' , \n"+
						" CASE WHEN [APM] IS NULL THEN 'False' ELSE 'True' END 'APM' , \n"+
						" CASE WHEN [DPM-SERP] IS NULL THEN 'False' ELSE 'True' END 'DPM' , \n"+
						" CASE WHEN [PD-SERP] IS NULL THEN 'False' ELSE 'True' END 'DRDO' , \n"
						+ "CASE WHEN [Admin Assistant] IS NULL THEN 'False' ELSE 'True' END 'Admin Assistant' , \n"+
						" CASE WHEN [Appellate Authority] IS NULL THEN 'False' ELSE 'True' END 'Appellate Authority' , \n"+
						" CASE WHEN [SPMU-SERP] IS NULL THEN 'False' ELSE 'True' END 'SPMU', \n"+
						" CASE WHEN [SPM] IS NULL THEN 'False' ELSE 'True' END 'SPM', \n"+
						" CASE WHEN [Director] IS NULL THEN 'False' ELSE 'True' END 'Director' FROM  \n"+
						" (select distinct tkt_type_name,  k.role_id, \n"+
						" CASE WHEN k.role_id='99' THEN 'APM' ELSE k.role_name END role_name  	from   	\n"+
						" tkt_type_master  t(nolock),  	tkt_role_type_mapping  r(nolock),  	roles k(nolock)	 \n"+
						" 	where t.tkt_type_id=r.tkt_type_id  and  k.role_id=r.role_id and k.role_id<>'32' and t.is_active='Y') as temp  PIVOt\n"+
						" ( sum(temp.role_id) for  temp.role_name in ([Beneficiary],[APM],[Camp Admin],[DPM-SERP],[Director],[PD-SERP],[SPMU-SERP],[SPM],[Appellate Authority],[Admin Assistant])) As p ";
											
				RolesServicesMap=getaccData(qry);		
				//System.out.println("RolesServicesMap"+RolesServicesMap);
		 
		}
		catch(Exception e) 
			{
				e.printStackTrace();
			}	
		return RolesServicesMap;	
	}
		
	
	public ArrayList columnData(String qry) throws Exception
	{
			
			Connection lcon=null;
			Statement lstmt=null;
		    ResultSet lrs=null;
		    ArrayList DataList=new ArrayList();
		    try
		    {
		      lcon = DBConnection.getConnection();
		      lstmt = lcon.createStatement(); 
		      
		      lrs = lstmt.executeQuery(qry);    
		      ResultSetMetaData rsMetaData=lrs.getMetaData();
		      int columnCount=rsMetaData.getColumnCount();		     
		      for (int i = 1; i <= columnCount; i++)
		      {		         
		         // System.out.println("column number " + i);	
		          DataList.add(rsMetaData.getColumnName(i));
		         // System.out.println(DataList.get(i));		      
		      }		 
		    } catch(SQLException sqle)
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
	public  ArrayList getaccData(String query) throws Exception
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
		                 //System.out.println("In data access"+alist.get(loopCount-1)+"hai");
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
