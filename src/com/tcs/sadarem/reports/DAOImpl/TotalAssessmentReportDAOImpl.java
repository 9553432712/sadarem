
package com.tcs.sadarem.reports.DAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.tcs.sadarem.reports.DAO.DirectorReportOutsideDAO;
import com.tcs.sadarem.reports.DAO.TotalAssessmentReportDAO;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.DataAccess;

public class TotalAssessmentReportDAOImpl implements TotalAssessmentReportDAO {
	static final private Logger log = Logger.getLogger(DirectorReportOutsideDAOImpl.class);
	Connection lcon;
	String lStrQuery;
	
	public ArrayList  getabstractassesseddata(String fromdate,String todate)
	{
		
                ArrayList resultList	= new ArrayList();
                ArrayList resultList1     = new ArrayList();
		try{
					
					ArrayList tempList	    = new ArrayList();
					
					
					lStrQuery = " {CALL PRO_SADAREM_R1_Total_Assessed(?,?)}";
					
					
					
					tempList.add(fromdate);// CommonUtility.getDateinFormat(fromdate, "dd/MM/yyyy", "MM/dd/yyyy"));
					tempList.add(todate); //CommonUtility.getDateinFormat(todate, "dd/MM/yyyy", "MM/dd/yyyy"));
					
			
					
					
					
					
				  	log.info("getabstractassesseddata lStrQuery : "+lStrQuery+"\n ParamList : "+tempList);
				  	
					resultList=(ArrayList)DataAccess.getStoredProcedureResultSet(lStrQuery,tempList,0,false,false);
				
	
					if(resultList.size()>0 )
					{
						resultList1 =(ArrayList)resultList.get(0);
					}
						
					//System.out.println("resultListttttt ============ "+lStrQuery);
					//System.out.println("resultListttttt ============ "+tempList);
					//System.out.println("resultListttttt ============ "+resultList1);
					
				}    
				 catch(SQLException sqle)
				 {    
					 log.info("SQLException in DirectorReportOutsideDAOImpl @ getabstractassesseddata :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
				 }
				 catch(Exception lexp)
				 {
					 log.info("Exception in DirectorReportOutsideDAOImpl @ getabstractassesseddata : "+lexp.getLocalizedMessage());
				 }
				
				return resultList1;	
		
	}

		
}
