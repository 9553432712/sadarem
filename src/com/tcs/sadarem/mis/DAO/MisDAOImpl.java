package com.tcs.sadarem.mis.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

public class MisDAOImpl implements MisDAO{
	String lStrQuery="";

	static final private Logger log = Logger.getLogger(MisDAOImpl.class);
	public ArrayList getActiveActivityList()
	
	{
		ArrayList activityList = new ArrayList(); 
		try{
			
			lStrQuery="select mis_activity_id,mis_activity_name from sadarem_mis_activity_master where is_active='Y' order by mis_activity_id"	;

			activityList = DataAccess.pickData(lStrQuery, false, false);
		}
		catch(Exception e)
		{

			e.printStackTrace();
		}
		return activityList;
		 
		
	}
public ArrayList getActiveSubActivityList(String actid)
	
	{
		ArrayList subactivityList = new ArrayList(); 
		ArrayList paramList = new ArrayList();
		ArrayList tempList	= new ArrayList();
		try{
			
			lStrQuery="select a.mis_subactivity_id,d.mis_subactivity_name\n"
					+ "from SADAREMTG.dbo.sadarem_mis_activity_subactivity_mapping as a inner join \n"
					+ "SADAREMTG.dbo.sadarem_mis_activity_master as b on a.mis_activity_id = b.mis_activity_id\n"
					+ "inner join  SADAREMTG.dbo.sadarem_mis_subactivity_master as d on a.mis_subactivity_id = d.mis_subactivity_id\n"
					+ "where a.mis_activity_id=? and  a.is_active='Y' order by mis_subactivity_id"	;

			 tempList.add("S");
	    		tempList.add(actid);
	    		paramList.add(tempList);
			
			subactivityList = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return subactivityList;
		 
		
	}

public ArrayList getActSubactList()
	
	{
		ArrayList totalactsubactList = new ArrayList(); 
		try{
			
			lStrQuery="select a.mis_target_id,a.mis_activity_id,a.mis_subactivity_id,b.mis_activity_name,d.mis_subactivity_name\n"
					+ "from SADAREMTG.dbo.sadarem_mis_activity_subactivity_mapping as a inner join \n"
					+ "SADAREMTG.dbo.sadarem_mis_activity_master as b on a.mis_activity_id = b.mis_activity_id\n"
					+ "inner join  SADAREMTG.dbo.sadarem_mis_subactivity_master as d on a.mis_subactivity_id = d.mis_subactivity_id\n"
					+ "where a.is_active='Y' order by mis_subactivity_id"	;

			totalactsubactList = DataAccess.pickData(lStrQuery, false, false);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return totalactsubactList;
		
		
	}



public ArrayList getSPMUleveltargets(String distid,String actid,String subactid)
{
	
	  ArrayList resultList	= new ArrayList();
      ArrayList resultList1     = new ArrayList();
try{
			
			ArrayList tempList	    = new ArrayList();
			if(distid.equals(""))
			{
				distid="-1";
			}
		
			lStrQuery = " {CALL PRO_SADAREM_MIS_SPMU_AUTOPOPULATE_TARGETS(?,?,?)}";
			
			
			
			tempList.add(distid);
			tempList.add(actid);
			tempList.add(subactid);
			
		  	log.info("getSPMUleveltargets lStrQuery : "+lStrQuery+"\n ParamList : "+tempList);
		  	
			resultList=(ArrayList)DataAccess.getStoredProcedureResultSet(lStrQuery,tempList,0,false,false);
		

	if(resultList.size()>0 )
			{
				resultList1 =(ArrayList)resultList.get(0);
			}


		}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in MisDAOImpl @ getSPMUleveltargets :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in MisDAOImpl @ getSPMUleveltargets : "+lexp.getLocalizedMessage());
		 }
		
		return resultList1;	
	
}
public ArrayList getSPMUleveldistricttargets(String year)
{
	
	  ArrayList resultList	= new ArrayList();
      ArrayList resultList1     = new ArrayList();
try{
			
			ArrayList tempList	    = new ArrayList();
	
		
			lStrQuery = " {CALL PRO_SADAREM_MIS_SPMU_DISTRICT_AFTERSAVE_TARGETS(?)}";
			
			
			
			tempList.add(year);
			
			
		  	log.info("getSPMUleveldistricttargets lStrQuery : "+lStrQuery+"\n ParamList : "+tempList);
		  	
			resultList=(ArrayList)DataAccess.getStoredProcedureResultSet(lStrQuery,tempList,0,false,false);
		

	if(resultList.size()>0 )
			{
				resultList1 =(ArrayList)resultList.get(0);
			}

//System.out.println(resultList+"\n"+resultList1);
		}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in MisDAOImpl @ getSPMUleveldistricttargets :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in MisDAOImpl @ getSPMUleveldistricttargets : "+lexp.getLocalizedMessage());
		 }
		
		return resultList1;	
	
}
/// 

public ArrayList getSPMUDistWiseRanks(String year,String month)  throws Exception
{
	
	  ArrayList resultList	= new ArrayList();
      ArrayList resultList1     = new ArrayList();
      String finyear=year;
      
      ResultSet rs = null;
	  Connection con =null;
	  CallableStatement st = null;
try{
			
	
			ArrayList tempList	= new ArrayList();
				
			  if(Integer.parseInt(month)<4)
			     {
				  year=(Integer.parseInt(finyear)+1)+"";
			     }
			  lStrQuery = " {CALL PRO_SADAREM_MIS_REPORT(?,?)}";
			  
				con=DBConnection.getConnection();
				st = con.prepareCall(lStrQuery);
				st.setString(1, finyear);
				st.setString(2, year+month);
				
				rs=st.executeQuery();
				st.getResultSet();
				
			
				 ResultSetMetaData rsMetaData=rs.getMetaData();
	       		int columnCount=rsMetaData.getColumnCount();
	       		while (rs.next()) 
	       			{
			  	  		ArrayList data=new ArrayList();
			  	  		for(int i=1;i<=columnCount;i++)
						   	       {
				      		  	data.add(rs.getString(i));
						   	       }
				  	  				resultList.add(data);
				  	 		
	    		     }
				 

			
			
		  	log.info("getSPMUleveldistricttargets lStrQuery : "+lStrQuery+"\n ParamList : "+tempList);
		  	

		}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in MisDAOImpl @ getSPMUleveldistricttargets :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in MisDAOImpl @ getSPMUleveldistricttargets : "+lexp.getLocalizedMessage());
		 }
			finally
			{
				if(rs!=null){rs.close();}
				if(st!=null){st.close();}
			    if(con!=null){ con.close();}
			    
			    try { rs.close(); } catch(Exception e) {}
			     try { st.close(); } catch(Exception e) {}
			     try { con.close(); } catch(Exception e) {}
			}
		return resultList;	
	
}
public ArrayList getRanks(String year,String month) throws Exception  
{
	
	  ArrayList resultList	= new ArrayList();
      ArrayList resultList1     = new ArrayList();
      String finyear=year;
      
      ResultSet rs = null;
	  Connection con =null;
	  CallableStatement st = null;
try{
			
			ArrayList tempList	= new ArrayList();
	
		
			lStrQuery = " {CALL PRO_SADAREM_MIS_REPORT_MASTER_RANK(?,?)}";
			 if(Integer.parseInt(month)<4)
		     {
			  year=(Integer.parseInt(finyear)+1)+"";
		     }
			 
			con=DBConnection.getConnection();
			st = con.prepareCall(lStrQuery);
			st.setString(1, finyear);
			st.setString(2, year+month);
			
			rs=st.executeQuery();
			st.getResultSet();
			
		
			 ResultSetMetaData rsMetaData=rs.getMetaData();
       		int columnCount=rsMetaData.getColumnCount();
       		while (rs.next()) 
       			{
		  	  		ArrayList data=new ArrayList();
		  	  		for(int i=1;i<=columnCount;i++)
					   	       {
			      		  	data.add(rs.getString(i));
					   	       }
			  	  				resultList.add(data);
			  	 		
    		     }
				
			
		  	log.info("getSPMUleveldistricttargets lStrQuery : "+lStrQuery+"\n ParamList : "+tempList);

		}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in MisDAOImpl @ getSPMUleveldistricttargets :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in MisDAOImpl @ getSPMUleveldistricttargets : "+lexp.getLocalizedMessage());
		 }
			finally
			{
				if(rs!=null){rs.close();}
				if(st!=null){st.close();}
			    if(con!=null){ con.close();}
			    
			    try { rs.close(); } catch(Exception e) {}
			     try { st.close(); } catch(Exception e) {}
			     try { con.close(); } catch(Exception e) {}
			}
		return resultList;	
	
}  

public ArrayList getMasterRanksActivityWise(String year,String month) throws SQLException  
{
	
	  ArrayList resultList	= new ArrayList();
      ArrayList resultList1     = new ArrayList();
      String finyear=year;
      

      ResultSet rs = null;
	  Connection con =null;
	  CallableStatement st = null;
try{
			
			ArrayList tempList	= new ArrayList();
	
		
			lStrQuery = " {CALL PRO_SADAREM_MIS_REPORT_MASTER_RANK_ACTIVITYWISE(?,?)}";
			 if(Integer.parseInt(month)<4)
		     {
			  year=(Integer.parseInt(finyear)+1)+"";
		     }
			 
			con=DBConnection.getConnection();
			st = con.prepareCall(lStrQuery);
			st.setString(1, finyear);
			st.setString(2, year+month);
			
			rs=st.executeQuery();
			st.getResultSet();
			
		
			 ResultSetMetaData rsMetaData=rs.getMetaData();
       		int columnCount=rsMetaData.getColumnCount();
       		while (rs.next()) 
       			{
		  	  		ArrayList data=new ArrayList();
		  	  		for(int i=1;i<=columnCount;i++)
					   	       {
			      		  	data.add(rs.getString(i));
					   	       }
			  	  				resultList.add(data);
			  	 		
    		     }
				
			
		  	log.info("getSPMUleveldistricttargets lStrQuery : "+lStrQuery+"\n ParamList : "+tempList);

		}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in MisDAOImpl @ getSPMUleveldistricttargets :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in MisDAOImpl @ getSPMUleveldistricttargets : "+lexp.getLocalizedMessage());
		 }
			finally
			{
				if(rs!=null){rs.close();}
				if(st!=null){st.close();}
			    if(con!=null){ con.close();}
			    
			    try { rs.close(); } catch(Exception e) {}
			     try { st.close(); } catch(Exception e) {}
			     try { con.close(); } catch(Exception e) {}
			}
		
		return resultList;	
	
}


public ArrayList getSPMUleveltargetsaftersaving(String distid,String year,String actid,String subactid)
{
	
	  ArrayList resultList	= new ArrayList();
      ArrayList resultList1     = new ArrayList();
try{
			
			ArrayList tempList	    = new ArrayList();
			if(distid.equals(""))
			{
				distid="-1";
			}
		
			lStrQuery = " {CALL PRO_SADAREM_MIS_SPMU_AFTERSAVE_TARGETS(?,?,?,?)}";
			
			tempList.add(distid);
			tempList.add(year);
			tempList.add(actid);
			tempList.add(subactid);
			
		  	log.info("getSPMUleveltargetsaftersaving lStrQuery : "+lStrQuery+"\n ParamList : "+tempList);
		  	
			resultList=(ArrayList)DataAccess.getStoredProcedureResultSet(lStrQuery,tempList,0,false,false);
		

	if(resultList.size()>0 )
			{
				resultList1 =(ArrayList)resultList.get(0);
			}

		}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CertiUploadReportDAOImpl @ getabstractcertiuploaddata :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CertiUploadReportDAOImpl @ getabstractcertiuploaddata : "+lexp.getLocalizedMessage());
		 }
		
		return resultList1;	
	
}
public String getMisTargetIDcheckMethod(String year,String districtid,String actid,String subactid)
{
	String status="";
	
	ArrayList paramList = new ArrayList();
	ArrayList tempList = new ArrayList();
	try
	{
		lStrQuery =  "select distinct s.mis_target_id from sadarem_mis_monthly_target_achiv_dtls s,sadarem_mis_activity_subactivity_mapping p\n"
				+ "where p.mis_target_id=s.mis_target_id and fin_year=? and district_id=? and p.mis_activity_id=? and\n"
				+ "p.mis_subactivity_id=?";
	
		tempList.add("S");
		tempList.add(year);
		paramList.add(tempList);	
		
		tempList = new ArrayList();		
		tempList.add("S");
		tempList.add(districtid);
		paramList.add(tempList);
			
		tempList = new ArrayList();			 
		tempList.add("S");
		tempList.add(actid);
		paramList.add(tempList);	
				
		tempList = new ArrayList();				 
		tempList.add("S");
	    tempList.add(subactid);
		paramList.add(tempList);						
		
		
		

		
		
		status = DataAccess.getReturnResultByPstmt(lStrQuery, paramList);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		log.info("Exception raised in MisDAOImpl @ getMisTargetIDcheckMethod of "+e);
	}
	
	return status;	
}
public String getSubActName(String subactid)
{
	String status="";
	
	ArrayList paramList = new ArrayList();
	ArrayList tempList = new ArrayList();
	try
	{
		lStrQuery =  "select mis_subactivity_name from sadarem_mis_subactivity_master where mis_subactivity_id=?";
				
		tempList.add("S");
		tempList.add(subactid);
		paramList.add(tempList);
		
		status = DataAccess.getReturnResultByPstmt(lStrQuery, paramList);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		log.info("Exception raised in MisDAOImpl @ getSubActName of "+e);
	}
	
	return status;	
}


public ArrayList getMandalListByDistrictIDPopulation(String distID)
{
	ArrayList resultlist = new ArrayList(); 
	ArrayList paramList = new ArrayList();
	ArrayList tempList	= new ArrayList();
	
	try{
		
		lStrQuery="select A.mandal_id,mandal_name,ISNULL(B.total_population,0),B.is_confirm from sadarem_mandal_master A \n"
					+" left join sadarem_mis_pop_master B on (A.district_id = B.district_id and A.mandal_id = B.mandal_id) where A.district_id = ? order by mandal_name\n";
	
		tempList.add("S");
		tempList.add(distID);
		
		paramList.add(tempList);
		
	  	log.info("getDistrictWisePopulation lStrQuery : "+lStrQuery+"\n ParamList : "+paramList);
	  	
	  	resultlist=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		 
		paramList = null;
		tempList  = null;
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return resultlist;
	
}
public ArrayList getDistrictWisePopulation()
{
	ArrayList resultlist = new ArrayList(); 
	ArrayList paramList = new ArrayList();
	ArrayList tempList	= new ArrayList();
	
	try{
		
		lStrQuery="select A.district_id,A.district_name,ISNULL(SUM(CONVERT(BIGINT,B.total_population)),0) as total_population,b.is_confirm from sadarem_district_master A \n"
				+"left join sadarem_mis_pop_master B on (A.district_id = B.district_id) \n" 
				+"group by A.district_id,A.district_name,b.is_confirm order by district_name";
	
		
		
	  	log.info("getDistrictWisePopulation lStrQuery : "+lStrQuery+"\n ParamList : "+paramList);
	  	
	  	resultlist=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		 
		paramList = null;
		tempList  = null;
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return resultlist;
}

public String  getParentDistid(String  distid)
{
	ArrayList resultlist = new ArrayList(); 
	ArrayList paramList = new ArrayList();
	ArrayList tempList	= new ArrayList();
	
	String parentDist="";
	
	
	Connection con=null;
	PreparedStatement pst =null;		
	try{
		con = DBConnection.getConnection();
		 
				
		lStrQuery="select parent_distid from sadarem_district_master where district_id=?";
		
		tempList.add("S");
		tempList.add(distid);
		
		paramList.add(tempList);
		
		
		
	  	log.info("getParentDistid lStrQuery : \n ParamList : "+paramList);
	  	
	  	resultlist=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
	  	
	  	
		paramList = null;
		tempList  = null;
		
		parentDist = (String) resultlist.get(0);
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	
	return	parentDist;		
}
public String getDistrictName(String distID)
{
	String distname="";
	try
	{
		if(distID.equals("00"))
		{
    		distname = "SERP Hyderabad";
		}
		else
		{
			ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		lStrQuery=  "select a.District_Name from sadarem_district_master a where a.District_ID=?";
    		
    		tempList.add("S");
    		tempList.add(distID);
    		paramList.add(tempList);
    		
		  	log.info("getDistrictName lStrQuery : "+lStrQuery+"\n ParamList : ");
		  	
		  	distname=DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
		}
	
	}    
	 catch(SQLException sqle)
	 {    
		 log.info("SQLException in MisDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
	 }
	 catch(Exception lexp)
	 {
		 log.info("Exception in MisDAOImpl : "+lexp.getLocalizedMessage());
	 }
	
	return distname;	
}
public String checkRecordExit(String Year,String distID)
{
	ArrayList resultlist = new ArrayList(); 
	ArrayList paramList = new ArrayList();
	ArrayList tempList	= new ArrayList();
	
	try{
		
		lStrQuery="select * from sadarem_mis_pop_master where fin_year=? and district_id = ? ";
	
		tempList.add("I");
		tempList.add(Year);
		paramList.add(tempList);
		
		tempList = new ArrayList();
		tempList.add("S");
		tempList.add(distID);
		
		paramList.add(tempList);
		
		
		
	  	log.info("checkRecordExit lStrQuery : \n ParamList : "+paramList);
	  	
	  	resultlist=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
	  	if(resultlist.size()>0)
	  	{
	  		return "Success";
	  	}
	  	
		paramList = null;
		tempList  = null;
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	
	return "";
}
public String checkRecordExistfortargets(String Year,String distID,String mistargetid)
{
	ArrayList resultlist = new ArrayList(); 
	ArrayList paramList = new ArrayList();
	ArrayList tempList	= new ArrayList();
	
	
	
	try{
		
		lStrQuery="select * from sadarem_mis_monthly_target_achiv_dtls where fin_year=? and district_id = ? and mis_target_id=?";
	
		tempList.add("I");
		tempList.add(Year);
		paramList.add(tempList);
		
		tempList = new ArrayList();
		tempList.add("S");
		tempList.add(distID);
		paramList.add(tempList);
		
		tempList = new ArrayList();
		tempList.add("S");
		tempList.add(mistargetid);
		
		paramList.add(tempList);
		
//		System.out.println("in check record"+paramList);
		
	  	log.info("checkRecordExistfortargets lStrQuery : \n ParamList : "+paramList);
	  	
	  	resultlist=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
	  	if(resultlist.size()>0)
	  	{
	  		return "Success";
	  	}
	  	
		paramList = null;
		tempList  = null;
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	
	return "";
}
public String  instpopulatio(HashMap insertdtls)
{
	Connection con = null;
    PreparedStatement prlStr = null;
    String result="";
    int rs=0;
    try
    {        	 
        con = DBConnection.getConnection();
        con.setAutoCommit(false);
        
	ArrayList mandalIdList= new ArrayList();
	ArrayList populationinputList= new ArrayList();
	
	mandalIdList=(ArrayList) insertdtls.get("mandalIdList");
	populationinputList=(ArrayList) insertdtls.get("populationinputList");
	String distID= (String) insertdtls.get("distID");
	String Year= (String) insertdtls.get("Year");
	String createdBy= (String) insertdtls.get("createdBy");
	String parentDistId=getParentDistid(distID);
	String recordExit=checkRecordExit(Year,distID);
	int r=1;
	
	if(recordExit.trim().length()==0)
	{
		String query = " INSERT INTO sadarem_mis_pop_master \n"
				+"(Fin_year,parent_dist_id,district_id,mandal_id,total_population,is_confirm,created_by,created_date) \n"
				+"VALUES ";
		for (int i=0;i<mandalIdList.size();i++)
		{
		    query += "( ?,?,?,?,?,'" + "N" + "',?," + "getdate() ),";
		    
		}
		  query=query.substring(0,query.length()-1);
		  prlStr=con.prepareStatement(query);	
		    
		for(int j=0;j<mandalIdList.size();j++)
		{
			   prlStr.setString(r,Year );  
			    r++;
			    prlStr.setString(r,parentDistId ); 
			    r++;
			    prlStr.setString(r,distID ); 
			    r++;
			    prlStr.setString(r,(String)mandalIdList.get(j) ); 
			    r++;
			    prlStr.setString(r,(String)populationinputList.get(j) ); 
			    r++;
			    prlStr.setString(r,createdBy ); 
			    r++;
		}
		
		//System.out.println("-=-=-=-=>"+query);
		  rs = prlStr.executeUpdate();
	}
	else
	{
		
		for (int i=0;i<mandalIdList.size();i++)
		{
			String query = "update sadarem_mis_pop_master set\n"
					+"total_population=? ,updated_by=? , updated_date=getdate() where Fin_year=? and district_id=? and mandal_id=? \n";
			prlStr=con.prepareStatement(query);
			prlStr.setString(1, (String) populationinputList.get(i));
			prlStr.setString(2, createdBy);
			prlStr.setString(3, Year);
			prlStr.setString(4, distID);
			prlStr.setString(5, (String) mandalIdList.get(i));		
			
		    rs = prlStr.executeUpdate();
		}		
		 
	}
     
     if(rs>0)
     {
  	   result="<font color='green'><b>Details Updated Successfully</b></font>";
  	   con.commit();
     }
     else
     {
  	   result="<font color='red'><b>01) Failed to update the details.Please try again.!</b></font>";
  	   con.rollback();
     }
     
     con.close();
  }
  catch (SQLException sqlEx)
  {
      	result="<font color='red'><b>02) Failed to Insert the details.Please try again.!</b></font>";
      	sqlEx.printStackTrace();
  } 
  catch (Exception sqlEx)
  {
      	result="<font color='red'><b>03) Failed to Insert the details.Please try again.!</b></font>";
      	sqlEx.printStackTrace();	            
  }
  finally 
  {
     DBConnection.closeConnection(con);
     DBConnection.closeStatement(prlStr);
  }		
    
	return result;		
}
public String confirmDtls(HashMap insertdtls,String flag)
{
	Connection con = null;
    PreparedStatement prlStr = null;
    String result="";
    int rs=0;
    
    try
    {
     	 
        con = DBConnection.getConnection();
        con.setAutoCommit(false);
        
	ArrayList mandalIdList= new ArrayList();
	ArrayList populationinputList= new ArrayList();
	
	mandalIdList=(ArrayList) insertdtls.get("mandalIdList");
	populationinputList=(ArrayList) insertdtls.get("populationinputList");
	String distID= (String) insertdtls.get("distID");
	String Year= (String) insertdtls.get("Year");
	String createdBy= (String) insertdtls.get("createdBy");
	String parentDistId=getParentDistid(distID);
	String recordExit=checkRecordExit(Year,distID);
	
	
	for (int i=0;i<mandalIdList.size();i++)
	{
		String query = "update sadarem_mis_pop_master set total_population=?,\n"
				+"is_confirm=? ,confim_by=?,confirm_date=getdate(),updated_by=?,updated_date=getdate() where Fin_year=? and district_id=? and mandal_id=? \n";
		
		prlStr = con.prepareStatement(query);
		prlStr.setString(1, (String) populationinputList.get(i));
		prlStr.setString(2, flag);
		prlStr.setString(3, createdBy);
		prlStr.setString(4, createdBy);
		prlStr.setString(5, Year);
		prlStr.setString(6, distID);
		prlStr.setString(7, (String) mandalIdList.get(i));
		
	    rs = prlStr.executeUpdate();
		
	}
	if(rs>0)
    {
 	   result="<font color='green'><b>Details Updated Successfully</b></font>";
 	   con.commit();
    }
    else
    {
 	   result="<font color='red'><b>01) Failed to update the details.Please try again.!</b></font>";
 	   con.rollback();
    }
	
		
		con.close();
    }
    catch(Exception e)
    {
    	result="<font color='red'><b>02) Failed to Update the details.Please try again.!</b></font>";
      	
    	e.printStackTrace();
    }
    finally 
    {
    	
       DBConnection.closeConnection(con);
       DBConnection.closeStatement(prlStr);
    }
	return result;
}

public ArrayList getNewDistrictList()
{
	ArrayList resultList = new ArrayList();
	
	try
	{
		ArrayList paramList = new ArrayList();
		ArrayList tempList	= new ArrayList();
		
			lStrQuery	=  "SELECT district_id ,district_name FROM sadarem_district_master WITH (NOLOCK) where district_id!='16' ORDER BY district_name";
		
	
	  	log.info("getNewDistrictList lStrQuery : "+lStrQuery);
	  	
		resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		
		paramList = null;
		tempList  = null;
	}    
	 catch(SQLException sqle)
	 {    
		 log.info("SQLException in MisDAOImpl @ getNewDistrictList :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
	 }
	 catch(Exception lexp)
	 {
		 log.info("Exception in MisDAOImpl @ getNewDistrictList : "+lexp.getLocalizedMessage());
	 }
	
	return resultList;		
}

public String  inserttargetsatspmulevel(HashMap insertdtls)
{
	Connection con = null;
    PreparedStatement prlStr = null;
    String result="";
    int rs=0;
    try
    {        	 
        con = DBConnection.getConnection();
        con.setAutoCommit(false);
        
	ArrayList mandaltargetList= new ArrayList();
	ArrayList mistargetidlist= new ArrayList();
	ArrayList mandalidlist= new ArrayList();
	  DecimalFormat format = new DecimalFormat();
      format.setDecimalSeparatorAlwaysShown(false);
      
	mandaltargetList=(ArrayList) insertdtls.get("mandaltargetList");
	String mistargetid=(String) insertdtls.get("mistargetid");
	mandalidlist=(ArrayList) insertdtls.get("mandalidlist");
	String distID= (String) insertdtls.get("distID");
	String Year= (String) insertdtls.get("Year");
	String createdBy= (String) insertdtls.get("createdBy");
	String parentDistId=getParentDistid(distID);
	
	String recordExit=checkRecordExistfortargets(Year,distID,mistargetid);
	int r=1;
	
	if(recordExit.trim().length()==0)
	{
		String query = " INSERT INTO sadarem_mis_monthly_target_achiv_dtls \n"
				+"([Fin_year],[year_month],[district_id],[mandal_id],[mis_target_id],[total_fin_target],[actual_target],[mis_achivement],[mis_target_balance],[is_mand_confirm]\n"
				+ ",[is_dist_confirm],[is_active],[created_by],[created_date],[updated_by],[updated_date]) \n"
				+"VALUES ";
		
		if(mandalidlist!=null)
		for (int i=0;i<mandalidlist.size();i++)
		{
	               
			
		    query += "( ?, ?,?,?,?,?,?,0,0,'" + "N" + "','" + "N"+ "','" + "Y"+ "',?," + "getdate()"+",?,"+"getdate() ),";
		    
		}
		query=query.substring(0,query.length()-1);
		
		prlStr=con.prepareStatement(query);
		for (int i=0;i<mandalidlist.size();i++)
		{
			
			prlStr.setString(r,Year); r++;
			prlStr.setString(r,Year+"04"); r++;
			prlStr.setString(r,distID); r++;
			prlStr.setString(r,(String)mandalidlist.get(i)); r++;
			prlStr.setString(r,mistargetid); r++;
			prlStr.setInt(r,(Integer)mandaltargetList.get(i)); r++; 
			prlStr.setInt(r,(Integer)(mandaltargetList.get(i))/12); r++;
			prlStr.setString(r,createdBy); r++;
			prlStr.setString(r,createdBy); r++;
		}
		
		//System.out.println("=>=>=>"+query);
		
		  rs = prlStr.executeUpdate();
	}
	else
	{
		
		for (int i=0;i<mandalidlist.size();i++)
		{
			String query = "update sadarem_mis_monthly_target_achiv_dtls set\n"
					+"total_fin_target=? ,actual_target=?,updated_by=? , updated_date=getdate() where Fin_year=? and district_id=? and mandal_id=? and mis_target_id=? \n";
	
			
			prlStr=con.prepareStatement(query);
			prlStr.setInt(1, (Integer) mandaltargetList.get(i)); 
			prlStr.setInt(2, (Integer)(mandaltargetList.get(i))/12);
			prlStr.setString(3, createdBy);
			prlStr.setString(4, Year);
			prlStr.setString(5, distID);
			prlStr.setString(6, (String) mandalidlist.get(i));	
			prlStr.setString(7, mistargetid);
			
		    rs = prlStr.executeUpdate();
		}		
		 
	}
     
     if(rs>0)
     {
  	   result="<font color='green'><b>Details Updated Successfully</b></font>";
  	   con.commit();
     }
     else
     {
  	   result="<font color='red'><b>01) Failed to update the details.Please try again.!</b></font>";
  	   con.rollback();
     }
     
     con.close();
  }
  catch (SQLException sqlEx)
  {
      	result="<font color='red'><b>02) Failed to Insert the details.Please try again.!</b></font>";
      	sqlEx.printStackTrace();
  } 
  catch (Exception sqlEx)
  {
      	result="<font color='red'><b>03) Failed to Insert the details.Please try again.!</b></font>";
      	sqlEx.printStackTrace();	            
  }
  finally 
  {
     DBConnection.closeConnection(con);
     DBConnection.closeStatement(prlStr);
  }		
    
	return result;		
}
public String  confirmtargetsatspmulevel(HashMap insertdtls)
{
	Connection con = null;
    PreparedStatement prlStr = null;
    String result="";
    int rs=0;
    try
    {        	 
        con = DBConnection.getConnection();
        con.setAutoCommit(false);
        
	ArrayList mandaltargetList= new ArrayList();
	ArrayList mistargetidlist= new ArrayList();
	ArrayList mandalidlist= new ArrayList();
	
	mandaltargetList=(ArrayList) insertdtls.get("mandaltargetList");
	String mistargetid=(String) insertdtls.get("mistargetid");
	mandalidlist=(ArrayList) insertdtls.get("mandalidlist");
	String distID= (String) insertdtls.get("distID");
	String Year= (String) insertdtls.get("Year");
	String createdBy= (String) insertdtls.get("createdBy");
	String parentDistId=getParentDistid(distID);
	
	String recordExit=checkRecordExistfortargets(Year,distID,mistargetid);
	int r=1;
	
	if(recordExit.trim().length()==0)
	{
		String query = " INSERT INTO sadarem_mis_monthly_target_achiv_dtls \n"
				+"([Fin_year],[year_month],[district_id],[mandal_id],[mis_target_id],[total_fin_target],[actual_target],[mis_achivement],[mis_target_balance],[is_mand_confirm]\n"
				+ ",[is_dist_confirm],[is_active],[created_by],[created_date],[updated_by],[updated_date]) \n"
				+"VALUES ";
		
		if(mandalidlist!=null)
		for (int i=0;i<mandalidlist.size();i++)
		{
			
			
		    query += "( ?, ?,?,?,?,?,?,0,0,'" + "S" + "','" + "S"+ "','" + "Y"+ "',?,getdate(),?,getdate() ),";
		    
		}
		query=query.substring(0,query.length()-1);
		
		prlStr=con.prepareStatement(query);
		
		for (int i=0;i<mandalidlist.size();i++)
		{
			
			prlStr.setString(r,Year); r++;
			prlStr.setString(r,Year+"04"); r++;
			prlStr.setString(r,distID); r++;
			prlStr.setString(r,(String)mandalidlist.get(i)); r++;
			prlStr.setString(r,mistargetid); r++;
			prlStr.setInt(r,(Integer)mandaltargetList.get(i)); r++; 
			prlStr.setInt(r,(Integer)(mandaltargetList.get(i))/12); r++;
			prlStr.setString(r,createdBy); r++;
			prlStr.setString(r,createdBy); r++;
		}
	
	//	System.out.println("-->"+query);
		  rs = prlStr.executeUpdate();
		  
		  
	}
	else
	{
		
		for (int i=0;i<mandalidlist.size();i++)
		{
			String query = "update sadarem_mis_monthly_target_achiv_dtls set\n"
					+"total_fin_target=? , actual_target=?,updated_by=? , updated_date=getdate(),is_dist_confirm='S',is_mand_confirm='S' where Fin_year=? and district_id=? and mandal_id=? and mis_target_id=? ";
			
			prlStr=con.prepareStatement(query);
			prlStr.setInt(1, (Integer) mandaltargetList.get(i));
			prlStr.setInt(2,  (Integer)mandaltargetList.get(i)/12);
			prlStr.setString(3, createdBy);
			prlStr.setString(4, Year);
			prlStr.setString(5, distID);
			prlStr.setString(6, (String) mandalidlist.get(i));	
			prlStr.setString(7, mistargetid);
			
		    rs = prlStr.executeUpdate();
		}		
		 
	}
     
     if(rs>0)
     {
  	   result="<font color='green'><b>Details Updated Successfully.</b></font>";
  	   con.commit();
     }
     else
     {
  	   result="<font color='red'><b>01) Failed to update the details.Please try again.!</b></font>";
  	   con.rollback();
     }
     
     con.close();
  }
  catch (SQLException sqlEx)
  {
      	result="<font color='red'><b>02) Failed to Insert the details.Please try again.!</b></font>";
      	sqlEx.printStackTrace();
  } 
  catch (Exception sqlEx)
  {
      	result="<font color='red'><b>03) Failed to Insert the details.Please try again.!</b></font>";
      	sqlEx.printStackTrace();	            
  }
  finally 
  {
     DBConnection.closeConnection(con);
     DBConnection.closeStatement(prlStr);
  }		
    
	return result;		
}
public ArrayList getMonths(int year)
{
	
    Calendar cal = Calendar.getInstance();
  
    int currentYear = cal.get(Calendar.YEAR);
    int currentMonth = cal.get(Calendar.MONTH);
    ArrayList Month = new ArrayList();
    ArrayList totalMonth = new ArrayList();
    ArrayList totalMnthList = null;
    totalMnthList = new ArrayList();	        

   //System.out.println(cal.get(Calendar.YEAR));
    	//year=2016;
    	//currentYear=2017;
    	//currentMonth=2;




    //if (year == currentYear)
    {
    	totalMnthList = new ArrayList();	       
        totalMnthList.add("04");
        totalMnthList.add("APR "+year);
        totalMonth.add(totalMnthList);

        totalMnthList = new ArrayList();
        totalMnthList.add("05");
        totalMnthList.add("MAY "+year);	        
        totalMonth.add(totalMnthList);

        totalMnthList = new ArrayList();
        totalMnthList.add("06");
        totalMnthList.add("JUN "+year);	        
        totalMonth.add(totalMnthList);

        totalMnthList = new ArrayList();
        totalMnthList.add("07");
        totalMnthList.add("JUL "+year);	        
        totalMonth.add(totalMnthList);

        totalMnthList = new ArrayList();
        totalMnthList.add("08");
        totalMnthList.add("AUG "+year);	        
        totalMonth.add(totalMnthList);

        totalMnthList = new ArrayList();
        totalMnthList.add("09");
        totalMnthList.add("SEP "+year);	        
        totalMonth.add(totalMnthList);

        totalMnthList = new ArrayList();
        totalMnthList.add("10");
        totalMnthList.add("OCT "+year);	        
        totalMonth.add(totalMnthList);

        totalMnthList = new ArrayList();
        totalMnthList.add("11");
        totalMnthList.add("NOV "+year);	        
        totalMonth.add(totalMnthList);

        totalMnthList = new ArrayList();
        totalMnthList.add("12");
        totalMnthList.add("DEC "+year);	        
        totalMonth.add(totalMnthList);
    }
    //else
    {
    	totalMnthList = new ArrayList();  
    	totalMnthList.add("01");
        totalMnthList.add("JAN "+currentYear);	        
        totalMonth.add(totalMnthList);

        totalMnthList = new ArrayList();        
        totalMnthList.add("02");
        totalMnthList.add("FEB "+currentYear);	        
        totalMonth.add(totalMnthList);

        totalMnthList = new ArrayList();	        
        totalMnthList.add("03");
        totalMnthList.add("MAR "+currentYear);	        
        totalMonth.add(totalMnthList);
    }


     ArrayList mthList = null;
    if (year == currentYear)
    {
        for (int i = 0; i <= currentMonth-3; i++)
        {
            ArrayList m = new ArrayList();
            mthList = new ArrayList();              

            Month.add(totalMonth.get(i));
           // System.out.println("in if--------"+Month);
        }
    }
    else
    {
        for (int i = 0; i <=9+currentMonth; i++)
        {
        	  ArrayList m = new ArrayList();
                mthList = new ArrayList();                
                  	Month.add(totalMonth.get(i));
                  //	System.out.println("in else"+Month);
        }
    }
    
    return Month;
} 

public String getNewdistrictID(String distID, String Mandalid) 
{
	String distid="";
	try
	{
		ArrayList paramList = new ArrayList();
		ArrayList tempList	= new ArrayList();
		
		
		lStrQuery=  "select a.district_id from  sadarem_mandal_master a where a.parent_distid=? and a.mandal_id=?";
	
	  
		tempList.add("S");
		tempList.add(distID);
		paramList.add(tempList);
		
		tempList = new ArrayList();
		tempList.add("S");
		tempList.add(Mandalid);
		paramList.add(tempList);
		
		log.info("get lStrQuery : "+lStrQuery+"\n ParamList : ");
	  	
	  	distid=DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
	
	}    
	 catch(SQLException sqle)
	 {    
		 log.info("SQLException in MisDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
	 }
	 catch(Exception lexp)
	 {
		 log.info("Exception in MisDAOImpl : "+lexp.getLocalizedMessage());
	 }
	
	return distid;
} 
public ArrayList getNewMandalList(String distid)

{
	ArrayList mandallistcombo = new ArrayList(); 
	ArrayList paramList = new ArrayList();
	ArrayList tempList	= new ArrayList();
	try{
		
		lStrQuery="select mandal_id,mandal_name from sadarem_mandal_master where parent_distid=? and is_urban='R' order by mandal_name";

		 tempList.add("S");
    		tempList.add(distid);
    		paramList.add(tempList);
		
    		mandallistcombo = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return mandallistcombo;
	 
	
}
public ArrayList getDPMlevelMandalTarAchBalValues(String distId,String mandalId,String finyear,String month)
{

	 ArrayList resultList	= new ArrayList();
     ArrayList resultList1     = new ArrayList();
     String Query="";
     try
     {
			
			ArrayList tempList	    = new ArrayList();
		
		
				Query = " {CALL PRO_SADAREM_MIS_DPM_AUTOPOPULATE_TARGETS(?,?,?,?)}";
			
						
			tempList.add(distId);
			tempList.add(mandalId);
			tempList.add(finyear);
			tempList.add(month);
			
		  	log.info("getDPMlevelMandalTarAchBalValues lStrQuery : "+Query+"\n ParamList : "+tempList);
		  	
			resultList=(ArrayList)DataAccess.getStoredProcedureResultSet(Query,tempList,0,false,false);
		
			//System.out.println(">>>>>>>>>>"+resultList);
			
			if(resultList.size()>0 )
			{
				resultList1 =(ArrayList)resultList.get(0);
			}

		}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in MisDAOImpl @ getDPMlevelMandalTarAchBalValues :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in MisDAOImpl @ getDPMlevelMandalTarAchBalValues : "+lexp.getLocalizedMessage());
		 }
		
		return resultList1;	
}
public String  saveupdateAchvssatspmulevel(HashMap insertdtls)
{
	int result =0;
	String qry="";
	Connection con=null;
	PreparedStatement lPstmt = null;
	String resultMSG="";
	
	
	ArrayList targetList = new ArrayList();
	ArrayList achiveList = new ArrayList();
	ArrayList misTarIdList = new ArrayList();
	ArrayList misBalList = new ArrayList();
	String Mandalid="";
	String distId="";
	String loginid="";
	String monthyear="";
	String yrmonth="";
	int Year;
	
	targetList=(ArrayList) insertdtls.get("targetList");
	achiveList=(ArrayList) insertdtls.get("achiveList");
	misBalList=(ArrayList) insertdtls.get("misBalList");
	misTarIdList=(ArrayList) insertdtls.get("misTarIdList");
	Mandalid=(String) insertdtls.get("Mandalid");
	distId=(String) insertdtls.get("distId");
	loginid=(String) insertdtls.get("loginid");
	Year= Integer.parseInt((String) insertdtls.get("Year"));

	 monthyear=(String) insertdtls.get("monthyear");

	 
	 if(Integer.parseInt((String) insertdtls.get("monthyear"))<4)
			 {
		  yrmonth = (Year+1)+monthyear;
			 }
	         else
	          {
	      yrmonth = Year+monthyear;
	          }
	 

	try
	{ 	  
		con=DBConnection.getConnection();
		con.setAutoCommit(false);
		for(int i=0;i<targetList.size();i++)
		{
			qry = "update sadarem_mis_monthly_target_achiv_dtls set  \n"+
					"mis_achivement=?,mis_target_balance=?,is_dist_confirm=?,updated_by=?,updated_date=current_timestamp \n"
					+ "where mis_target_id=? and district_id=? and mandal_id=? and is_mand_confirm=? and fin_year=? and year_month=?";					
			 lPstmt = con.prepareStatement(qry);				 
		     lPstmt.setFloat(1, (Float) achiveList.get(i));
		     lPstmt.setFloat(2, (Float)misBalList.get(i));
		     lPstmt.setString(3,"N" );
		     lPstmt.setString(4, loginid);
		     lPstmt.setString(5,(String) misTarIdList.get(i));
		     lPstmt.setString(6,distId );
		     lPstmt.setString(7,Mandalid);
		     lPstmt.setString(8,"Y" );		    
		     lPstmt.setInt(9,Year );
		     lPstmt.setString(10,yrmonth );
		     result =   lPstmt.executeUpdate();
		}		

     if(result>0)
     {
    	 resultMSG="<font color='green'><b>Details Updated Successfully</b></font>";
    	 con.commit();
     }
     else
     {
    	 resultMSG="<font color='red'><b>01) Details not updated.Please try again.!</b></font>";
  	   		con.rollback();
     }
     
     con.close();
	}
	catch(Exception e)
	{
		try {
			con.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	log.info("Exception raised in MisDAOImpl @ saveupdateAchvssatspmulevel"+e);
    	 e.printStackTrace();
	}
  finally 
  {
     DBConnection.closeConnection(con);
     DBConnection.closeStatement(lPstmt);
  }		
    
	return resultMSG;		
}
public String  confirmupdateAchvssatspmulevel(HashMap insertdtls)
{
	int result =0;
	String qry="";
	Connection con=null;
	PreparedStatement lPstmt = null;
	String resultMSG="";
	
	
	ArrayList targetList = new ArrayList();
	ArrayList achiveList = new ArrayList();
	ArrayList misTarIdList = new ArrayList();
	ArrayList misBalList = new ArrayList();
	String Mandalid="";
	String distId="";
	String loginid="";
	String monthyear="";
	String yrmonth="";
	int Year;
	
	targetList=(ArrayList) insertdtls.get("targetList");
	achiveList=(ArrayList) insertdtls.get("achiveList");
	misBalList=(ArrayList) insertdtls.get("misBalList");
	misTarIdList=(ArrayList) insertdtls.get("misTarIdList");
	Mandalid=(String) insertdtls.get("Mandalid");
	distId=(String) insertdtls.get("distId");
	loginid=(String) insertdtls.get("loginid");
	Year= Integer.parseInt((String) insertdtls.get("Year"));
	 monthyear=(String) insertdtls.get("monthyear");
	 
	 
	 if(Integer.parseInt((String) insertdtls.get("monthyear"))<4)
			 {
		  yrmonth = (Year+1)+monthyear;
			 }
	         else
	          {
	      yrmonth = Year+monthyear;
	          }
	try
	{ 	  
		con=DBConnection.getConnection();
		con.setAutoCommit(false);
		for(int i=0;i<targetList.size();i++)
		{
			qry = "update sadarem_mis_monthly_target_achiv_dtls set  \n"+
					"mis_achivement=?,mis_target_balance=?,is_dist_confirm=?,dist_confirm_by=?,dist_confirm_date=getdate(),updated_by=?,updated_date=current_timestamp \n"
					+ "where mis_target_id=? and district_id=? and mandal_id=? and is_mand_confirm=? and fin_year=? and year_month=?";					
			 lPstmt = con.prepareStatement(qry);				 
		     lPstmt.setFloat(1, (Float) achiveList.get(i));
		     lPstmt.setFloat(2, (Float)misBalList.get(i));
		     lPstmt.setString(3,"Y" );
		     lPstmt.setString(4, loginid);
		     lPstmt.setString(5, loginid);
		     lPstmt.setString(6,(String) misTarIdList.get(i));
		     lPstmt.setString(7,distId );
		     lPstmt.setString(8,Mandalid);
		     lPstmt.setString(9,"Y" );		    
		     lPstmt.setInt(10,Year );
		     lPstmt.setString(11,yrmonth );
		     result =   lPstmt.executeUpdate();
		}		

     if(result>0)
     {
    		CallableStatement calstmt = null;	      
	       
	        try {
	        	
	            calstmt = con.prepareCall("{Call PRO_SADAREM_MIS_INSERTION_TARGET(?,?,?,?,?)}");
	            calstmt.setString(1,distId);
	            calstmt.setString(2,Mandalid);
	            calstmt.setInt(3,Year);
	            calstmt.setInt(4,Integer.parseInt((String)monthyear));
	            calstmt.setString(5,loginid);
	            
	            result = calstmt.executeUpdate();
	            calstmt.close();
	           
	           
	            
	     } catch (SQLException sqlEx) {
	        	result=0;
	         sqlEx.printStackTrace();		       
	        
	        }  
	        if(result>0)
		     {
	        	 resultMSG="<font color='green'><b>Details Updated Successfully</b></font>";
	        	 con.commit();
    		    
		     }
	        else
	        {
	        	resultMSG="<font color='red'><b>01) Details not updated.Please try again.!</b></font>";
	  	   		con.rollback();
	        }
    	 
    	 
    	
     }
     else
     {
    	 resultMSG="<font color='red'><b>02) Details not updated.Please try again.!</b></font>";
  	   		con.rollback();
     }
     
     con.close();
	}
	catch(Exception e)
	{
		try {
			con.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	log.info("Exception raised in MisDAOImpl @ confirmupdateAchvssatspmulevel"+e);
    	 e.printStackTrace();
	}
  finally 
  {
     DBConnection.closeConnection(con);
     DBConnection.closeStatement(lPstmt);
  }		
    
	return resultMSG;		
}
public String getMonthForInt(int num)
{
	num--;
	
    String month = "wrong";
    DateFormatSymbols dfs = new DateFormatSymbols();
    String[] months = dfs.getMonths();
    
    if (num >= 0 && num <= 11 )
    {
        month = months[num];
    }
    return month;
}
public ArrayList getMandalTargetAchieveBal(String finyear,String distId,String mandalId,String yearmonth)
{
	//System.out.println("Inputs for proc  finyear, distId, mandalId, month"+finyear+ distId+ mandalId+ yearmonth);
	 ArrayList resultList	= new ArrayList();
     ArrayList resultList1     = new ArrayList();
     int checkacheverecord=0;
     try
     {
			
			ArrayList tempList	    = new ArrayList();
			checkacheverecord=achievementRecordExit( finyear, distId, mandalId, yearmonth);
		//	System.out.println("checkacheverecord"+checkacheverecord);
			if(checkacheverecord==0)
			{

				lStrQuery = " {CALL PRO_SADAREM_MIS_MANDAL_AUTOPOPULATE_TARGETS(?,?,?,?)}";	
				
			}
			else
			{
				lStrQuery = " {CALL PRO_SADAREM_MIS_MANDAL_AFTERSAVE_TARGETS(?,?,?,?)}";
			}						
			tempList.add(distId);
			tempList.add(mandalId);
			tempList.add(finyear);
			if(checkacheverecord==0)
			{
				tempList.add(yearmonth.substring(4, yearmonth.length())); 
 			}
			else
			{
				tempList.add(yearmonth);
				
			}
			
			
		  	log.info("getabstractcertiuploaddata lStrQuery : "+lStrQuery+"\n ParamList : "+tempList);
		  	
			resultList=(ArrayList)DataAccess.getStoredProcedureResultSet(lStrQuery,tempList,0,false,false);
		
			//System.out.println(resultList);
			if(resultList.size()>0 )
			{
				resultList1 =(ArrayList)resultList.get(0);
			}

			//System.out.println(resultList+"\n"+resultList1);
		}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CertiUploadReportDAOImpl @ getabstractcertiuploaddata :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CertiUploadReportDAOImpl @ getabstractcertiuploaddata : "+lexp.getLocalizedMessage());
		 }
		
		return resultList1;	
}
public String updateMandalTarAchieve(HashMap inputMapList)
{
		int result =0;
		String qry="";
		Connection con=null;
		PreparedStatement lPstmt = null;
		String resultMSG="";
		
		
		ArrayList targetList = new ArrayList();
		ArrayList achiveList = new ArrayList();
		ArrayList misTarIdList = new ArrayList();
		ArrayList misBalList = new ArrayList();
		String Mandalid="";
		String distId="";
		String loginid="";
		String yearMonth="";
		int FINYear;
		
		targetList=(ArrayList) inputMapList.get("targetList");
		achiveList=(ArrayList) inputMapList.get("achiveList");
		misBalList=(ArrayList) inputMapList.get("misBalList");
		misTarIdList=(ArrayList) inputMapList.get("misTarIdList");
		Mandalid=(String) inputMapList.get("Mandalid");
		distId=(String) inputMapList.get("distId");
		loginid=(String) inputMapList.get("loginid");
		
		FINYear= (Integer) ( inputMapList.get("finYear"));
		
		yearMonth=(String) inputMapList.get("yearMonth");
		 	
		try
		{ 	  
			con=DBConnection.getConnection();
			con.setAutoCommit(false);
			for(int i=0;i<targetList.size();i++)
			{
				qry = "update sadarem_mis_monthly_target_achiv_dtls set  \n"+
						"mis_achivement=?,mis_target_balance=?,is_mand_confirm=?,updated_by=?,updated_date=current_timestamp \n"
						+ "where mis_target_id=? and district_id=? and mandal_id=? and is_dist_confirm=? and fin_year=? and year_month=?";					
				 lPstmt = con.prepareStatement(qry);				 
			     lPstmt.setFloat(1, (Float) achiveList.get(i));
			     lPstmt.setFloat(2, (Float)misBalList.get(i));
			     lPstmt.setString(3,"N" );
			     lPstmt.setString(4, loginid);
			 
			     lPstmt.setString(5,(String) misTarIdList.get(i));
			     lPstmt.setString(6,distId );
			     lPstmt.setString(7,Mandalid);
			     lPstmt.setString(8,"S" );		    
			     lPstmt.setInt(9,FINYear );
			     lPstmt.setString(10,yearMonth);
			     result =   lPstmt.executeUpdate();
			  }		

	     if(result>0)
	     {
	    	 resultMSG="<font color='green'><b>Details Updated Successfully</b></font>";
	    	 con.commit();
	     }
	     else
	     {
	    	 resultMSG="<font color='red'><b>01) Deatails not updated.Please try again.!</b></font>";
	  	   		con.rollback();
	     }
	     
	     con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	log.info("Exception raised in MisDAOImpl @ insertmandalleveltargets"+e);
	    	 e.printStackTrace();
		}
		 finally 
		  {
		     DBConnection.closeConnection(con);
		     DBConnection.closeStatement(lPstmt);
		  }		

	return resultMSG;	
}
public String confirmMandalTarAchieve(HashMap inputMapList)
{  //System.out.println("confirmMandalTarAchieve1");
	int result =0;
	String qry="";
	Connection con=null;
	PreparedStatement lPstmt = null;
	String resultMSG="";
	
	
	ArrayList targetList = new ArrayList();
	ArrayList achiveList = new ArrayList();
	ArrayList misTarIdList = new ArrayList();
	ArrayList misBalList = new ArrayList();
	String Mandalid="";
	String distId="";
	String loginid="";
	String monthyear="";
	int Year;
	
	targetList=(ArrayList) inputMapList.get("targetList");
	achiveList=(ArrayList) inputMapList.get("achiveList");
	misBalList=(ArrayList) inputMapList.get("misBalList");
	misTarIdList=(ArrayList) inputMapList.get("misTarIdList");
	Mandalid=(String) inputMapList.get("Mandalid");
	distId=(String) inputMapList.get("distId");
	loginid=(String) inputMapList.get("loginid");
	Year= (Integer) inputMapList.get("finYear");
	 monthyear=(String) inputMapList.get("monthyear");
	// System.out.println("confirmMandalTarAchieve2");
	try
	{ 	  
		con=DBConnection.getConnection();
		con.setAutoCommit(false);
		for(int i=0;i<targetList.size();i++)
		{
			
			qry = "update sadarem_mis_monthly_target_achiv_dtls set  \n"+
					"mand_confirm_date=current_timestamp,mand_confim_by=?,is_mand_confirm=?,updated_by=?,updated_date=current_timestamp, \n "
					+ "mis_achivement=?,mis_target_balance=? \n"
					+ "where mis_target_id=? and district_id=? and mandal_id=? and is_dist_confirm=? and fin_year=? and year_month=?";					
			 lPstmt = con.prepareStatement(qry);

		     lPstmt.setString(1, loginid);
		     lPstmt.setString(2,"Y" );
		     lPstmt.setString(3, loginid);

		     lPstmt.setFloat(4, (Float) achiveList.get(i));
		     lPstmt.setFloat(5, (Float)misBalList.get(i));
		     lPstmt.setString(6,(String) misTarIdList.get(i));
		     lPstmt.setString(7,distId );
		     lPstmt.setString(8,Mandalid);
		     lPstmt.setString(9,"S" );		    
		     lPstmt.setInt(10,Year );
		     lPstmt.setString(11,monthyear );
		     result =   lPstmt.executeUpdate();
		     
		}		

     if(result>0)
     {
    	 resultMSG="<font color='green'><b>Details Updated Successfully</b></font>";
    	 con.commit();
     }
     else
     {
    	 resultMSG="<font color='red'><b>01) Failed to update the details.Please try again.!</b></font>";
  	   		con.rollback();
     }
     
     con.close();
	}
	catch(Exception e)
	{
		try {
			con.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	log.info("Exception raised in MisDAOImpl @ insertmandalleveltargets"+e);
    	 e.printStackTrace();
	}
	 finally 
	  {
	     DBConnection.closeConnection(con);
	     DBConnection.closeStatement(lPstmt);
	  }		

return resultMSG;
}
public int achievementRecordExit(String finyear,String distId,String mandalId,String Yearmonth)
{ //System.out.println("Inputs for achievementRecordExit  finyear, distId, mandalId, month"+finyear+ distId+ mandalId+ Yearmonth);
	int total=0;
	try
	{
		ArrayList paramList = new ArrayList();
		ArrayList tempList	= new ArrayList();

		
		lStrQuery=  "select sum(mis_achivement) from  sadarem_mis_monthly_target_achiv_dtls "
				+ " where   district_id=? and mandal_id=?  and fin_year=? and year_month=?";


		tempList.add("S");
		tempList.add(distId);
		paramList.add(tempList);

		tempList = new ArrayList();
		tempList.add("S");
		tempList.add(mandalId);
		paramList.add(tempList);
		
		tempList = new ArrayList();
		tempList.add("I");
		tempList.add(finyear);
		paramList.add(tempList);
		
		tempList = new ArrayList();
		tempList.add("S");
		tempList.add(Yearmonth);
		paramList.add(tempList);

		log.info("get lStrQuery : "+lStrQuery+"\n ParamList : ");
		
		
		total=(int)Float.parseFloat(testnully(DataAccess.getReturnResultByPstmt(lStrQuery,paramList)));
	  	
	  	
	  	

	}    
	 catch(SQLException sqle)
	 {    
		 log.info("SQLException in MisDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
	 }
	 catch(Exception lexp)
	 {
		 log.info("Exception in MisDAOImpl : "+lexp.getLocalizedMessage());
	 }

	return total;
}
public String getMonthName(String month) {
    switch (Integer.parseInt(month)) {
        case 1:
            return "January";
        case 2:
            return "February";
        case 3:
            return "March";
        case 4:
            return "April";
        case 5:
            return "May";
        case 6:
            return "June";
        case 7:
            return "July";
        case 8:
            return "August";
        case 9:
            return "September";
        case 10:
            return "October";
        case 11:
            return "November";
        case 12:
            return "December";
    }
    return "";
}
public String testnully(String x)
{
	if(x==null)
	{
		return "0";
	}
	else
	{
		return x;
	}
}
}
