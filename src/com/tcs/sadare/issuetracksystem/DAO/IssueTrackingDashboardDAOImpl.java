package com.tcs.sadare.issuetracksystem.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.DataAccess;

public class IssueTrackingDashboardDAOImpl implements IssueTrackingDashboardDAO 
{

	public ArrayList getDashboardDetails(HashMap inputDtls)
	{
		ArrayList details = new ArrayList();
		String qry="";
		String roleid   = CommonUtility.checkNullObj(inputDtls.get("role"));
		String fromdate = CommonUtility.checkNullObj(inputDtls.get("fromdate"));
		String todate   = CommonUtility.checkNullObj(inputDtls.get("todate"));
		String distId   = CommonUtility.checkNullObj(inputDtls.get("distId"));
		 
			try
			{
				qry=    "select ISNULL(s.IssueType,'ZGrand Total') issuetype,sum(total) total,\n"+
					    "sum(Pending) pen,case when sum(total)<>0 then (100*sum(Pending)/sum(total)) else 0 end pendper,\n"+
					    "sum(Approved) appr,case when sum(total)<>0 then (100*sum(Approved)/sum(total)) else 0 end apprper,\n"+
					    "sum(Rejected) rej,case when sum(total)<>0 then (100*sum(Rejected)/sum(total)) else 0 end rejper\n"+
						"from(\n"+
						"SELECT a.tkt_type_id,a.tkt_type_name 'IssueType',ISNULL(b.pending,0)+ISNULL(b.approved,0)+ISNULL(b.rejected,0) Total,\n"+
						"ISNULL(b.Pending,0) Pending,case when ISNULL(b.pending,0)+ISNULL(b.approved,0)+ISNULL(b.rejected,0) <>0 then\n"+ 
                        "(100 * ISNULL(b.Pending,0))/(ISNULL(b.pending,0)+ISNULL(b.approved,0)+ISNULL(b.rejected,0)) else 0 end pendingper,\n"+
						"ISNULL(b.Approved,0) Approved,\n"+
						"case when ISNULL(b.pending,0)+ISNULL(b.approved,0)+ISNULL(b.rejected,0) <>0 then\n"+ 
			            "(100 * ISNULL(b.approved,0))/(ISNULL(b.pending,0)+ISNULL(b.approved,0)+ISNULL(b.rejected,0)) else 0 end appper,\n"+
                        "ISNULL(b.Rejected,0) Rejected,\n"+
                        "case when ISNULL(b.pending,0)+ISNULL(b.approved,0)+ISNULL(b.rejected,0) <>0 then\n"+ 
			            "(100 * ISNULL(b.rejected,0))/(ISNULL(b.pending,0)+ISNULL(b.approved,0)+ISNULL(b.rejected,0)) else 0 end rejper\n"+
						"FROM \n"+
						"tkt_type_master a WITH(NOLOCK) LEFT JOIN\n"+
						"(\n";
						if(roleid.length() >0)
						{
							
							qry    +=	"  SELECT bas.tkt_type_id,\n"+
									"SUM(CASE WHEN (stat.request_type='A' and bas.status_flag=stat.flow_flag)    THEN 1 ELSE 0 END) Pending,\n"+
									"SUM(CASE WHEN (stat.request_type='A' AND bas.status_flag=stat.decision_flag)   THEN 1 \n"+
									"WHEN (stat.request_type='A' AND s.status_flag=stat.decision_flag)     THEN 1 \n"+
									"ELSE 0 END) Approved,\n"+
									"SUM(CASE WHEN stat.request_type='R' AND bas.status_flag=stat.decision_flag THEN 1 ELSE 0 END) Rejected\n";
						}
						else
						{
							qry    +=	"  SELECT bas.tkt_type_id,\n"+
									"SUM(CASE WHEN (stat.request_type='A' and bas.status_flag=stat.flow_flag) THEN 1 ELSE 0 END) Pending,\n"+
									"SUM(CASE WHEN (stat.request_type='A' AND bas.status_flag=stat.decision_flag AND stat.is_final='Y') THEN 1 \n"+
									"ELSE 0 END) Approved,\n"+
									"SUM(CASE WHEN stat.request_type='R' AND bas.status_flag=stat.decision_flag THEN 1 ELSE 0 END) Rejected\n";
									
						}
					
              qry    +=	" FROM \n"+
						"(\n"+
						"SELECT mst.tkt_req_id,mst.tkt_type_id,mst.status_flag\n"+
						"FROM \n"+
						"tkt_request_master mst with (nolock)\n"+
						" WHERE cast(mst.created_date as date) between ? and ?\n";
			
				if(!distId.equals("-1") && !distId.equals("") && distId.length()>0)
				{
					qry += "and mst.dist_id=?";
				}
				
				qry+=    ") \n"+
						"bas join (SELECT DISTINCT request_type,tkt_type_id,decision_flag,is_final,flow_flag FROM tkt_status_flow_mapping WITH(NOLOCK) where is_active='Y'"; 
				
				if(roleid.length() >0)
				{
					qry += "and role_id = ? ";
				}
				
				if(roleid.length() >0)
				{
					
					qry +=	" )stat\n"+ 
							" on  \n"+
							" bas.tkt_type_id = stat.tkt_type_id\n"+ 
							" left join (select distinct tkt_req_id,status_flag from tkt_request_status_history WITH(NOLOCK)) s\n"+
                             "on   (bas.tkt_req_id = s.tkt_req_id and stat.decision_flag=s.status_flag)\n"+   
							" GROUP BY bas.tkt_type_id\n"+
							" ) B ON (a.tkt_type_id=b.tkt_type_id )\n"+
							" WHERE a.is_active='Y'\n"+
							" )S group by rollup(s.IssueType)\n"+
							" ORDER BY issuetype";
				}
				else
				{
				qry += ")stat \n"+
						"On \n"+
						"bas.tkt_type_id = stat.tkt_type_id \n"+
						"GROUP BY bas.tkt_type_id\n"+
						") B ON (a.tkt_type_id=b.tkt_type_id )\n"+
						"WHERE a.is_active='Y'\n"+
						")S group by rollup(s.IssueType)\n"+
                        "ORDER BY issuetype";
				
				}
				 
			 //System.out.println("qryccc   "+qry);
			//	details = DataAccess.getData(qry);
				   
				ArrayList resultList = new ArrayList();
	    		ArrayList paramList = new ArrayList();
	    		ArrayList tempList	= new ArrayList();

	                	   tempList.add("S");
	   			    		tempList.add(fromdate);
	   			    		paramList.add(tempList);
	   			    		
	   			    		
	   			    		tempList = new ArrayList();
	   			    		tempList.add("S"); 
	   			    		tempList.add(todate);
	   			    		paramList.add(tempList);
	   			    	
	   			    		
	   			    		if(!distId.equals("-1") && !distId.equals("") && distId.length()>0)
	   						{	   							
	   			    		tempList = new ArrayList();
	   			    		tempList.add("S"); 
	   			    		tempList.add(distId);
	   			    		paramList.add(tempList);
	   						}
	   			    
	   			    		
	   			    		if(roleid.length() >0)
	   						{
	   			    		tempList = new ArrayList();
	   			    		tempList.add("S"); 
	   			    		tempList.add(roleid);
	   			    		paramList.add(tempList);
	   						}

	    		  	
	   			    		details=(ArrayList)DataAccess.pickDataByPrepareStmt(qry, paramList, false, false); 
	   			    
	   			    	
	   			    		 
	   			    		 
	   			    		//HttpServletRequest req= null;
				//System.out.println("-=-=-=>"+details);     
	   			    	//	System.out.println("-=-=>--->"+r.getAllPersonalData(ds,dt)); 
				
			}  
			catch(Exception e)
			{ 
				//e.printStackTrace();
				System.out.println("Exception raised in "+e);
			}
		return details;
	}
	
	
	public ArrayList getApproverRolesList()
	{
		ArrayList rolesList =  new ArrayList();
		String qry="";
		try
		{
			qry = " select role_id,role_name from roles where role_id in (\n"+
	              " select distinct role_id from tkt_status_flow_mapping where flow_flag is not null)";
			
			//rolesList = DataAccess.getData(qry);
			
			rolesList=	(ArrayList)DataAccess.pickData(qry, false, false);  
			
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in getApproverRolesList"+e);
		}
		return rolesList;
		
	}
	
	
}
