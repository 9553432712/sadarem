package com.tcs.sadarem.reports.DAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.reports.DAO.CommonReportsDAO;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.DataAccess;

public class CommonReportsDAOImpl implements CommonReportsDAO{
	static final private Logger log = Logger.getLogger(CommonReportsDAOImpl.class);
	Connection lcon;
	String lStrQuery;
	
	public ArrayList getDistrictList()
	{
		ArrayList resultList = new ArrayList();
    	
    	try
    	{
    		//System.out.println("inside method");
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    			lStrQuery	=  "SELECT  district_id ,district_name FROM sadarem_district_master WITH (NOLOCK) ORDER BY district_name";
    		
    	
		  	log.info("getDistrictList lStrQuery : "+lStrQuery);
		  	
    		resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
    		//System.out.println("result"+resultList);
    		paramList = null;
    		tempList  = null;
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl @ getDistrictList :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl @ getDistrictList : "+lexp.getLocalizedMessage());
		 }
    	
    	return resultList;		
	}
	
	public ArrayList getNewDistrictList()
	{
		ArrayList resultList = new ArrayList();
    	
    	try
    	{
    		//System.out.println("inside method");
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    			lStrQuery	=  "SELECT DISTINCT NEW_DISTRICT_ID,NEW_DISTRICT_NAME FROM NEW_SADAREM_DISTRICT_MASTER  ORDER BY NEW_DISTRICT_NAME";
    		
    	
		  	log.info("getNewDistrictList lStrQuery : "+lStrQuery);
		  	
    		resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
    		//System.out.println("result"+resultList);
    		paramList = null;
    		tempList  = null;
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl @ getNewDistrictList :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl @ getNewDistrictList : "+lexp.getLocalizedMessage());
		 }
    	
    	return resultList;		
	}

	public ArrayList getDisabilityList()
	{
		ArrayList resultList = new ArrayList();
		try
		{
			lStrQuery=

					"select  disability_id,disability_name from dbo.tbldisability_details(nolock)";



				log.info("getDisabilityList lStrQuery : "+lStrQuery);
  	
					resultList=(ArrayList)DataAccess.pickData(lStrQuery, false, false);
					
		}   
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl @ getDisabilityList :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl @ getDisabilityList : "+lexp.getLocalizedMessage());
		 }
		
		return resultList;
	} 
	public ArrayList getEducationList()
	{
		ArrayList resultList = new ArrayList();
		try
		{
			lStrQuery=

					"select  edu_id,edu_desc from sadarem_education_master(nolock) order by edu_id";



				log.info("getEducationList lStrQuery : "+lStrQuery);
  	
					resultList=(ArrayList)DataAccess.pickData(lStrQuery, false, false);
					
		}   
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl @ getEducationList :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl @ getEducationList : "+lexp.getLocalizedMessage());
		 }
		
		return resultList;
	}
	public ArrayList getCampListByDistrictID(String distID)
	{
		ArrayList resultList = new ArrayList();
    	
    	try
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		lStrQuery=  "select  distinct a.parta_campid,b.VenueName+', '+b.address CampName \n"+ 
    					" from sadarem_complete_details_sharing_reports(nolock) a \n"+
    					" ,tblcamp_details b where b.Camp_ID=a.parta_campid and  a.districtid=? and b.status='Active' \n"+
    					" order by  CampName";
    		           
    		    		tempList.add("S");
			    		tempList.add(distID);
			    		paramList.add(tempList);
    		
		  	log.info("getCampListByDistrictID lStrQuery : "+lStrQuery+"\n ParamList : "+paramList);
		  	//System.out.println("paramList"+paramList);
    		resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
    		
    		paramList = null; 
    		tempList  = null;
    	}     
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl @ getCampListByDistrictID :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl @ getCampListByDistrictID : "+lexp.getLocalizedMessage());
		 }
    	
    	return resultList;		
	}
	public ArrayList getDistrictListbyAreatype(String areatype)
	{
		ArrayList resultList = new ArrayList();
    	
    	try
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		if(areatype.equalsIgnoreCase("-1") || areatype.equalsIgnoreCase("R"))
    		{
    		lStrQuery=  "select district_id,district_name from sadarem_district_master (nolock) order by district_name";
    		}
    		else if(areatype.equalsIgnoreCase("U"))
    		{
    			lStrQuery=  "select district_id,district_name from sadarem_district_master(nolock) where is_urban=? order by district_name";
    			tempList.add("S");
	    		tempList.add(areatype);
	    		paramList.add(tempList);
    		}
    		    		
    		
		  	log.info("getCampListByDistrictID lStrQuery : "+lStrQuery+"\n ParamList : "+paramList);
		  	//System.out.println("paramList"+paramList);
    		resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
    		
    		paramList = null; 
    		tempList  = null;
    	}     
		 catch(SQLException sqle)
		 {   
			 sqle.printStackTrace();
			 log.info("SQLException in CommonReportsDAOImpl @ getCampListByDistrictID :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 lexp.printStackTrace();
			 log.info("Exception in CommonReportsDAOImpl @ getCampListByDistrictID : "+lexp.getLocalizedMessage());
		 }
    	
    	return resultList;		
	}
	public ArrayList getMandalListbyDistrictId(String distID)
	{
		ArrayList resultList = new ArrayList();
    	
    	try
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		lStrQuery=  "select  mandal_id,mandal_name from \n"+
    				    "dbo.sadarem_mandal_master(nolock)"+
    				    "where district_id=? ";
    		           
			    		tempList.add("S");
			    		tempList.add(distID);
			    		paramList.add(tempList);
    		
		  	log.info("getMandalListbyDistrictId lStrQuery : "+lStrQuery+"\n ParamList : "+paramList);
	
    		resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
    		
    		paramList = null;
    		tempList  = null;
    	}     
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl @ getMandalListbyDistrictId :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl @ getMandalListbyDistrictId : "+lexp.getLocalizedMessage());
		 }
    	
    	return resultList;		
	}
	public ArrayList getMandalListbyDistrictIdAreatype(String distID,String areatype)
	{
		ArrayList resultList = new ArrayList();
    	
    	try
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		
    		if(areatype.equalsIgnoreCase("-1"))
    		{
    		lStrQuery=  "select  mandal_id,mandal_name from \n"+
    				    "dbo.sadarem_mandal_master(nolock)"+
    				    "where district_id=? order by mandal_name";
    		           
			    		 tempList.add("S");
			    		 tempList.add(distID);
			    		 paramList.add(tempList);
    		}
    		else
    		{
    			lStrQuery=  "select  mandal_id,mandal_name from \n"+
    				    "dbo.sadarem_mandal_master(nolock)"+
    				    "where district_id=? and is_urban=? order by mandal_name";
    		           
			    		 tempList.add("S");
			    		 tempList.add(distID);
			    		 paramList.add(tempList);	
			    		 tempList	= new ArrayList();
			    		 tempList.add("S");
			    		 tempList.add(areatype);
			    		 paramList.add(tempList);	
    		}
		  	log.info("getMandalListbyDistrictId lStrQuery : "+lStrQuery+"\n ParamList : "+paramList);
	
    		resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
    		
    		
    	}     
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl @ getMandalListbyDistrictId :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl @ getMandalListbyDistrictId : "+lexp.getLocalizedMessage());
		 }
    	
    	return resultList;		
	}
	public ArrayList getDistrictIDsList()
	{
		ArrayList resultList = new ArrayList();
		try
		{
			lStrQuery=

					"select  district_id from dbo.sadarem_district_master(nolock)";



				log.info("getDistrictIDsList lStrQuery : "+lStrQuery);
  	
					resultList=(ArrayList)DataAccess.pickData(lStrQuery, false, false);
					
		}   
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl @ getDistrictIDsList :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl @ getDistrictIDsList : "+lexp.getLocalizedMessage());
		 }
		
		return resultList;
	}
	public ArrayList getVillageListByDistrictIDMandalID(String distID,String mandID)
	{
		ArrayList resultList = new ArrayList();
    	
    	try
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		lStrQuery=  " SELECT  village_id,village_name \n"+
    					" FROM sadarem_village_master (nolock)\n"+
    					" WHERE district_id=? AND \n"+
    					" mandal_id=? \n"+
    					" ORDER BY village_name";
    		
    		
    		tempList.add("S");
    		tempList.add(distID);
    		paramList.add(tempList);
    		
    		tempList = new ArrayList();
    		tempList.add("S");
    		tempList.add(mandID);
    		paramList.add(tempList);
    		
		  	log.info("getVillageListByDistrictIDMandalID lStrQuery : "+lStrQuery+"\n ParamList : "+paramList);
		  	
    		resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
    		
    		paramList = null;
    		tempList  = null;
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl @ getVillageListByDistrictIDMandalID :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl @ getVillageListByDistrictIDMandalID : "+lexp.getLocalizedMessage());
		 }
    	
    	return resultList;		
	}
	public ArrayList getVillageListByDistrictIDMandalIDAreaWise(String distID,String mandID,String areatype)
	{
		ArrayList resultList = new ArrayList();
    	
    	try
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		if(areatype.equalsIgnoreCase("-1"))
    		{
    		lStrQuery=  " SELECT  village_id,village_name \n"+
    					" FROM sadarem_village_master (nolock)\n"+
    					" WHERE district_id=? AND \n"+
    					" mandal_id=? \n"+
    					" ORDER BY village_name";
    		
    		
    		tempList.add("S");
    		tempList.add(distID);
    		paramList.add(tempList);
    		
    		tempList = new ArrayList();
    		tempList.add("S");
    		tempList.add(mandID);
    		paramList.add(tempList);
    		}
    		else
    			
    		{
    			lStrQuery=  " SELECT  village_id,village_name \n"+
    					" FROM sadarem_village_master (nolock)\n"+
    					" WHERE district_id=? AND \n"+
    					" mandal_id=? and is_urban=? \n"+
    					" ORDER BY village_name";
    		
    		
    		tempList.add("S");
    		tempList.add(distID);
    		paramList.add(tempList);
    		
    		tempList = new ArrayList();
    		tempList.add("S");
    		tempList.add(mandID);
    		paramList.add(tempList);	
    		
    		tempList = new ArrayList();
    		tempList.add("S");
    		tempList.add(areatype);
    		paramList.add(tempList);	
    		}
		  	log.info("getVillageListByDistrictIDMandalID lStrQuery : "+lStrQuery+"\n ParamList : "+paramList);
		  	
    		resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
    		
    		paramList = null;
    		tempList  = null;
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl @ getVillageListByDistrictIDMandalID :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl @ getVillageListByDistrictIDMandalID : "+lexp.getLocalizedMessage());
		 }
    	
    	return resultList;		
	}
	public String getCampName(int campID)
	{
		String distname="";
    	try
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		lStrQuery=  "SELECT venuename+', '+address Camp_name  from dbo.tblCamp_Details WHERE camp_id=?";
    		
    		tempList.add("I");
    		tempList.add(campID);
    		paramList.add(tempList);
    		
		  	log.info("get lStrQuery : "+lStrQuery+"\n ParamList : ");
		  	
		  	distname=DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
    		
    	
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl : "+lexp.getLocalizedMessage());
		 }
    	
    	return distname;	
	}
	public String getCampName(String campID)
	{
		String distname="";
    	try
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		lStrQuery=  "SELECT venuename+', '+address Camp_name  from dbo.tblCamp_Details WHERE camp_id=?";
    		
    		tempList.add("I");
    		tempList.add(campID);
    		paramList.add(tempList);
    		
		  	log.info("get lStrQuery : "+lStrQuery+"\n ParamList : ");
		  	
		  	distname=DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
    		
    	
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl : "+lexp.getLocalizedMessage());
		 }
    	
    	return distname;	
	}
	public String getDistrictNameBySadaremID(String sadaremID)
	{
		String distname="";
    	try
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		lStrQuery=  "select distinct District_Name  from dbo.sadarem_complete_details_sharing_reports(nolock) where sadarem_id=?";
    		
    		tempList.add("S");
    		tempList.add(sadaremID);
    		paramList.add(tempList);
    		
		  	log.info("get lStrQuery : "+lStrQuery+"\n ParamList : ");
		  	
		  	distname=DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
    		
    	
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl : "+lexp.getLocalizedMessage());
		 }
    	
    	return distname;	
	} 
	public String getNewDistrictName(String distID)
	{
		String distname="";
    	try
    	{
    		
    			ArrayList paramList = new ArrayList();
	    		ArrayList tempList	= new ArrayList();
	    		
	    		lStrQuery=  "select  NEW_DISTRICT_NAME from NEW_SADAREM_DISTRICT_MASTER (nolock)  where new_District_ID=?";
	    		
	    		tempList.add("S");
	    		tempList.add(distID);
	    		paramList.add(tempList);
	    		
			  	log.info("get lStrQuery : "+lStrQuery+"\n ParamList : ");
			  	
			  	distname=DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
    		
    	
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl : "+lexp.getLocalizedMessage());
		 }
    	
    	return distname;	
	}
	public String getDistrictName(String distID)
	{
		String distname="";
    	try
    	{
    		
    			ArrayList paramList = new ArrayList();
	    		ArrayList tempList	= new ArrayList();
	    		
	    		lStrQuery=  "select  District_Name from sadarem_district_master(nolock)  where District_ID=?";
	    		
	    		tempList.add("S");
	    		tempList.add(distID);
	    		paramList.add(tempList);
	    		
			  	log.info("get lStrQuery : "+lStrQuery+"\n ParamList : ");
			  	
			  	distname=DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
    		
    	
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl : "+lexp.getLocalizedMessage());
		 }
    	
    	return distname;	
	}
	public String getMandalName(String distID, String Mandalid) 
	{
		String mandalname="";
    	try
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		
    		lStrQuery=  "select  mandal_name from sadarem_mandal_master(nolock) where District_ID=? and Mandal_ID=?";
   
    		tempList.add("S");
    		tempList.add(distID);
    		paramList.add(tempList);
    		
    		tempList = new ArrayList();
    		tempList.add("S");
    		tempList.add(Mandalid);
    		paramList.add(tempList);
    		
    		log.info("get lStrQuery : "+lStrQuery+"\n ParamList : ");
		  	
		  	mandalname=DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
    	
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl : "+lexp.getLocalizedMessage());
		 }
    	
    	return mandalname;
	} 
	public String getEducationName(String eduId)
	{
		String distname="";
    	try
    	{
    		
    			ArrayList paramList = new ArrayList();
	    		ArrayList tempList	= new ArrayList();
	    		
	    		lStrQuery=  "select  edu_desc from sadarem_education_master(nolock)  where edu_id=?";
	    		
	    		tempList.add("S");
	    		tempList.add(eduId);
	    		paramList.add(tempList);
	    		
			  	log.info("get lStrQuery : "+lStrQuery+"\n ParamList : ");
			  	
			  	distname=DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
    		
    	
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl : "+lexp.getLocalizedMessage());
		 }
    	
    	return distname;	
	} 
	public String getEmploymentName(String emp_id)
	{
		String distname="";
    	try
    	{
    		
    			ArrayList paramList = new ArrayList();
	    		ArrayList tempList	= new ArrayList();
	    		
	    		lStrQuery=  "select employement_type_desc from sadarem_employement_type_master(nolock) where employement_type_id=?";
	    				
	    		
	    		tempList.add("S");
	    		tempList.add(emp_id);
	    		paramList.add(tempList);
	    		
			  	log.info("get lStrQuery : "+lStrQuery+"\n ParamList : ");
			  	
			  	distname=DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
    		
    	
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl : "+lexp.getLocalizedMessage());
		 }
    	
    	return distname;	
	}
	public String getVillageName(String distID, String Mandalid,String villageID) 
	{
		String mandalname="";
    	try
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		
    		lStrQuery=  "select   village_name from sadarem_village_master(nolock) where District_ID=? and Mandal_ID=? and village_id=?";
    	
		  
    		tempList.add("S");
    		tempList.add(distID);
    		paramList.add(tempList);
    		
    		tempList = new ArrayList();
    		tempList.add("S");
    		tempList.add(Mandalid);
    		paramList.add(tempList);
    		
    		tempList = new ArrayList();
    		tempList.add("S");
    		tempList.add(villageID);
    		paramList.add(tempList);
    		
    		
    		log.info("get lStrQuery : "+lStrQuery+"\n ParamList : ");
		  	
		  	mandalname=DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
    	
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl : "+lexp.getLocalizedMessage());
		 }
    	
    	return mandalname;
	}
	public ArrayList getHabitationListByDistrictIDMandalIDvillageID(String distID, String mandID, String villageID) 
	{
		ArrayList resultList	= new ArrayList();
		
		try{
					ArrayList paramList     = new ArrayList();
					ArrayList tempList	    = new ArrayList();
					
					
					lStrQuery=  "select   distinct habtationid,habitation_name from sadarem_complete_details_sharing_reports(nolock) \n"+
								"where districtid=? and mandalid=? and villageid=? \n"+
								" ORDER BY habitation_name";
					
					
					tempList.add("S");
					tempList.add(distID);
					paramList.add(tempList);
					
					tempList = new ArrayList();
					tempList.add("S");
					tempList.add(mandID);
					paramList.add(tempList);
					
					tempList = new ArrayList();
					tempList.add("S");
					tempList.add(villageID);
					paramList.add(tempList);
					
				  	log.info("getHabitationListByDistrictIDMandalIDvillageID lStrQuery : "+lStrQuery+"\n ParamList : "+paramList);
				  	
					resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
					
					paramList = null;
					tempList  = null;
				}    
				 catch(SQLException sqle)
				 {    
					 log.info("SQLException in CommonReportsDAOImpl @ getVillageListByDistrictIDMandalID :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
				 }
				 catch(Exception lexp)
				 {
					 log.info("Exception in CommonReportsDAOImpl @ getVillageListByDistrictIDMandalID : "+lexp.getLocalizedMessage());
				 }
				
				return resultList;		
	}
	public ArrayList getEducationQualificationList()
	{
		ArrayList resultList = new ArrayList();
		try
		{
			lStrQuery=

					"select distinct edu_id,education_desc from dbo.sadarem_complete_details_sharing_reports(nolock)";



				log.info("getDisabilityList lStrQuery : "+lStrQuery);
  	
					resultList=(ArrayList)DataAccess.pickData(lStrQuery, false, false);
					
		}   
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl @ getEducationQualificationList :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl @ getEducationQualificationList : "+lexp.getLocalizedMessage());
		 }
		
		return resultList;
	}
	public ArrayList getCasteList()
	{
		ArrayList resultList = new ArrayList();
    	
    	try
    	{
    		//System.out.println("inside method");
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    			lStrQuery	=  "SELECT  CastCode ,CastDesc FROM Caste WITH (NOLOCK) ORDER BY CastCode";
    		
    	
		  	log.info("getCasteList lStrQuery : "+lStrQuery);
		  	
    		resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
    		//System.out.println("result"+resultList);
    		paramList = null;
    		tempList  = null;
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl @ getCasteList :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl @ getCasteList : "+lexp.getLocalizedMessage());
		 }
    	
    	return resultList;		
	}
	public ArrayList getEmploymentList()
	{
		ArrayList resultList = new ArrayList();
    	
    	try
    	{
    		//System.out.println("inside method");
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    			lStrQuery	=  "SELECT  employement_type_id ,employement_type_desc FROM sadarem_employement_type_master WITH (NOLOCK) ORDER BY employement_type_id";
    		
    	
		  	log.info("getEmploymentList lStrQuery : "+lStrQuery);
		  	
    		resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
    		//System.out.println("result"+resultList);
    		paramList = null;
    		tempList  = null;
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl @ getEmploymentList :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl @ getEmploymentList : "+lexp.getLocalizedMessage());
		 }
    	
    	return resultList;		
	}
	public ArrayList PartAWithoutProofPDReportList(String LoginID)
	{
		ArrayList resultList = new ArrayList();
    	
    	try
    	{
    		//System.out.println("inside method");
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    			lStrQuery	=  "SELECT  Person_Code ,Surname , First_Name,CONVERT(VARCHAR(12),Date_of_Birth, 103) ,Relation_Name,Created_Date FROM tblperson_personal_details WITH (NOLOCK) where Login_ID=? ORDER BY Created_Date desc";
    			
    			tempList.add("S");
    			tempList.add(LoginID);
    			paramList.add(tempList);
    		
    	
		  	log.info("getEmploymentList lStrQuery : "+lStrQuery);
		  	
    		resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
    		//System.out.println("result"+resultList);
    		paramList = null;
    		tempList  = null;
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonReportsDAOImpl @ getEmploymentList :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonReportsDAOImpl @ getEmploymentList : "+lexp.getLocalizedMessage());
		 }
    	
    	return resultList;		
	}
}
