
package com.tcs.sadare.issuetracksystem.DAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.tcs.sadarem.reports.DAOImpl.DirectorReportOutsideDAOImpl;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.DataAccess;

public class DistrictWiseGrievanceDashboardDAOImpl implements DistrictWiseGrievanceDashboardDAO 
{
	static final private Logger log = Logger.getLogger(DirectorReportOutsideDAOImpl.class);
	Connection lcon;
	String lStrQuery;
	public ArrayList getDashboardDetails(HashMap inputDtls)
	{
        ArrayList resultList	= new ArrayList();
        ArrayList resultList1     = new ArrayList();

		String fromdate = CommonUtility.checkNullObj(inputDtls.get("fromdate"));
		String todate   = CommonUtility.checkNullObj(inputDtls.get("todate"));

         try{
			
			ArrayList tempList	    = new ArrayList();
			
			
			lStrQuery = " {CALL PRO_SADAREM_ISSUE_TRACKING_DISTWISE_DASHBOARD(?,?)}";
			
			tempList.add(fromdate);
			tempList.add(todate); 
		  
			log.info("getabstractcertiuploaddata lStrQuery : "+lStrQuery+"\n ParamList : "+tempList);
		  	
			resultList=(ArrayList)DataAccess.getStoredProcedureResultSet(lStrQuery,tempList,0,false,false);
		

			if(resultList.size()>0 )
			{
				resultList1 =(ArrayList)resultList.get(0);
			}
			}
			catch(Exception e)
			{
				System.out.println("Exception raised in "+e);
			}
		return resultList1;
	}
	
	public ArrayList gettypeList()
	{
		ArrayList typeList =  new ArrayList();
		String qry="";
		try
		{
			qry = " select tkt_type_name from tkt_type_master where is_active='Y' order by tkt_type_id";
	     
			
			typeList = DataAccess.getData(qry);
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in getApproverRolesList"+e);
		}
	//	System.out.println("typeList"+typeList);
		return typeList;	
	}

	
	
}
