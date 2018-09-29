package com.tcs.sadarem.openreports.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.DataAccess;

public class OpenReportsDAOImpl implements OpenReportsDAO
{

	String lStrQuery="";
	
	public ArrayList getpwdvalAbstReport(String fromdate, String todate,String distId,String mandalId,String villageId) 
	{
		 
		ArrayList Datalist = new ArrayList();
		ArrayList ReportDatalist = new ArrayList();
		try
			{
		
			      /*lStrQuery = " {CALL myproc(?,?,?,?)}";*/
					lStrQuery = " {CALL PwDValidationAbstractRuralReport(?,?,?,?)}";
						
					  ArrayList paramlist = new ArrayList();
					  
					  paramlist.add(CommonUtility.getDateinFormat(fromdate, "dd/MM/yyyy", "MM/dd/yyyy"));
					  paramlist.add(CommonUtility.getDateinFormat(todate,"dd/MM/yyyy","MM/dd/yyyy"));
					  paramlist.add(distId);
					  paramlist.add(mandalId);
					//  paramlist.add(villageId);
					  
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
