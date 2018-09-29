package com.tcs.sadarem.openreports.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.DataAccess;

public class ExpiredSadaremCertificatesImpl  implements ExpiredSadaremCertificatesDAO
{
	

	String lStrQuery="";
	
	public ArrayList getExpireddata(String fromdate, String todate,String distId,String mandalId,String villageId) 
	
	{
		 
		ArrayList Datalist = new ArrayList();
		ArrayList ReportDatalist = new ArrayList(); 
		try
			{
		
			      /*lStrQuery = " {CALL myproc(?,?,?,?)}";*/
					lStrQuery = " {CALL USP_SADAREM_EXPIRED(?,?,?,?,?)}";
						
					  ArrayList paramlist = new ArrayList();
					  
					  paramlist.add(CommonUtility.getDateinFormat(fromdate, "dd/MM/yyyy", "MM/dd/yyyy"));
					  paramlist.add(CommonUtility.getDateinFormat(todate,"dd/MM/yyyy","MM/dd/yyyy"));
					  paramlist.add(distId);
					  paramlist.add(mandalId);
					 paramlist.add(villageId);
					// paramlist.add(HabId);
					  
					//  System.out.println("paramlist : "+paramlist);
					 
					 Datalist = DataAccess.getStoredProcedureResultSet(lStrQuery,paramlist,0,false,false);
				
				if(Datalist.size()>0 )
				{
					ReportDatalist =(ArrayList)Datalist.get(0);
				}
					  
						  
			}
			catch(SQLException sqle)
			{
				System.out.println(sqle.getErrorCode());
				sqle.printStackTrace();
			}
			catch(Exception e)
			{
				System.out.println(e.getLocalizedMessage());
				e.printStackTrace();
				
			}
	return ReportDatalist;	
	}
public ArrayList getExpireddistrictdata(String fromdate, String todate,String distId,String mandalId,String villageId) 
	
	{
		 
		ArrayList Datalist = new ArrayList();
		ArrayList ReportDatalist = new ArrayList(); 
		try
			{
		
			      /*lStrQuery = " {CALL myproc(?,?,?,?)}";*/
					lStrQuery = " {CALL USP_SADAREM_EXPIRED_log(?,?,?,?,?)}";
						
					  ArrayList paramlist = new ArrayList();
					  
					  paramlist.add(CommonUtility.getDateinFormat(fromdate, "dd/MM/yyyy", "MM/dd/yyyy"));
					  paramlist.add(CommonUtility.getDateinFormat(todate,"dd/MM/yyyy","MM/dd/yyyy"));
					  paramlist.add(distId);
					  paramlist.add(mandalId);
					 paramlist.add(villageId);
					// paramlist.add(HabId);
					  
					//  System.out.println("paramlist : "+paramlist);
					 
					 Datalist = DataAccess.getStoredProcedureResultSet(lStrQuery,paramlist,0,false,false);
				
				if(Datalist.size()>0 )
				{
					ReportDatalist =(ArrayList)Datalist.get(0);
				}
					  
						  
			}
			catch(SQLException sqle)
			{
				System.out.println(sqle.getErrorCode());
				sqle.printStackTrace();
			}
			catch(Exception e)
			{
				System.out.println(e.getLocalizedMessage());
				e.printStackTrace();
				
			}
	return ReportDatalist;	
	}
	
	}
	
	


