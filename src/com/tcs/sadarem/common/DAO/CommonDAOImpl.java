package com.tcs.sadarem.common.DAO;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.bf.disability.Constants.CommonConstants;

import com.ecentric.servicemodels.AadhaarProfile;
import com.tcs.sadarem.issuetracksystem.actions.issueTrackingCommonUtil;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.DataAccess;
import com.tcs.sadarem.util.SMSUtility;
import com.tcs.sgv.common.util.DBConnection;

public class CommonDAOImpl implements CommonDAO 
{
	static final private Logger log = Logger.getLogger(CommonDAOImpl.class);
	Connection lcon;
	String lStrQuery;
	
	
	public AadhaarProfile get101AadhaarProfile(String aadhaarId)
	{
		 AadhaarProfile myaadhaarProfile = new AadhaarProfile();
		 try
		 { 
    	 	myaadhaarProfile.setBase64file(CommonUtility.checkNullObj("101"));
			myaadhaarProfile.setBuildingName(CommonUtility.checkNullObj("101"));
			myaadhaarProfile.setCareof(CommonUtility.checkNullObj("101"));
			myaadhaarProfile.setDistrict(CommonUtility.checkNullObj("101"));
			myaadhaarProfile.setDistrict_name(CommonUtility.checkNullObj("101"));
			myaadhaarProfile.setDob(CommonUtility.checkNullObj("101"));
			myaadhaarProfile.setEid(CommonUtility.checkNullObj("101"));
			myaadhaarProfile.setGender(CommonUtility.checkNullObj("101"));
			myaadhaarProfile.setMandal(CommonUtility.checkNullObj("101"));
			myaadhaarProfile.setMandal_name(CommonUtility.checkNullObj("101"));
			myaadhaarProfile.setName(CommonUtility.checkNullObj("101"));
			myaadhaarProfile.setPhoneNo(CommonUtility.checkNullObj("101"));
			myaadhaarProfile.setPincode(CommonUtility.checkNullObj("101"));
			myaadhaarProfile.setStatecode(CommonUtility.checkNullObj("1"));
			myaadhaarProfile.setStatus(CommonUtility.checkNullObj("100"));
			myaadhaarProfile.setStreet(CommonUtility.checkNullObj("101"));
			myaadhaarProfile.setUid(aadhaarId);
			myaadhaarProfile.setVillage(CommonUtility.checkNullObj("101"));
			myaadhaarProfile.setVillage_name(CommonUtility.checkNullObj("101"));
			myaadhaarProfile.setSrdhwstxn(CommonUtility.checkNullObj("101")); 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 
		 return myaadhaarProfile;
	}
	
	public ArrayList getPentionTypeList()
	{
		ArrayList resultList = new ArrayList();
		try
		{
			ArrayList tempList = new ArrayList();
			tempList.add("Disabled");
			tempList.add("Disabled");
			resultList.add(tempList);
			

			tempList = new ArrayList();
			tempList.add("Old Age");
			tempList.add("Old Age");
			
			resultList.add(tempList);
			tempList = new ArrayList();
			tempList.add("Widow");
			tempList.add("Widow");
			
			resultList.add(tempList);
			tempList = new ArrayList();
			tempList.add("Weavers");
			tempList.add("Weavers");
			resultList.add(tempList);
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	public ArrayList getDisabilityCountDetails()
	{
		ArrayList resultList = new ArrayList();
		try
		{
			lStrQuery=
					
					"SELECT ISNULL(rpt.Disability_Name,'ZZ_Total') disb_name,COUNT(*) mycount\n" +
					"FROM sadarem_complete_details_sharing_reports rpt WITH(NOLOCK)\n" + 
					"WHERE\n" + 
					"rpt.dis_form_status='View' AND\n" + 
					"rpt.person_disp_percent>=rpt.disp_min_per\n" + 
					"GROUP BY GROUPING SETS ((rpt.Disability_Name),())\n" + 
					"ORDER BY 1,2";



				log.info("getDisabilityCountDetails lStrQuery : "+lStrQuery);
  	
					resultList=(ArrayList)DataAccess.pickData(lStrQuery, false, false);
					
		}   
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl @ getDisabilityCountDetails :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl @ getDisabilityCountDetails : "+lexp.getLocalizedMessage());
		 }
		
		return resultList;
	}
	
	public ArrayList getDisabilityList()
	{
		ArrayList resultList = new ArrayList();
		try
		{
			lStrQuery= "select disability_id,disability_name from tblDisability_Details  WITH(NOLOCK)";  
			 
			resultList=(ArrayList)DataAccess.pickData(lStrQuery, false, false);
					
		}   
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl @ getDisabilityList :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl @ getDisabilityList : "+lexp.getLocalizedMessage());
		 }
		
		return resultList;
	}

	
	public ArrayList getRationCardTypeList()
	{
		ArrayList resultList = new ArrayList();
		try
		{
			lStrQuery= "select rationcard_type_id,rationcard_type_desc from sadarem_rationcard_type_master  WITH(NOLOCK)";  
			 
			resultList=(ArrayList)DataAccess.pickData(lStrQuery, false, false);
					
		}   
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl @ getRationCardTypeList :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl @ getDisabilityList : "+lexp.getLocalizedMessage());
		 }
		
		return resultList;
	}
	
	public ArrayList getGenderList()
	{
		ArrayList resultList = new ArrayList();
		try
		{
			lStrQuery= "SELECT DISTINCT gender_id,gender_name \n"+
					 " FROM sadarem_gender_relation_type_mapping WITH(NOLOCK) \n"+
					" WHERE \n"+
					" is_active='Y' \n"+
					" ORDER BY gender_name";  
			 
			resultList=(ArrayList)DataAccess.pickData(lStrQuery, false, false);
					
		}   
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl @ getGenderList :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl @ getGenderList : "+lexp.getLocalizedMessage());
		 }
		
		return resultList;
	}
	
	public ArrayList getReligionList()
	{
		ArrayList resultList = new ArrayList();
		try
		{
			lStrQuery= " SELECT religion_id,religion_desc \n"+
					" FROM sadarem_religion_master WITH(NOLOCK) \n"+
					" WHERE is_active='Y' \n"+
					" ORDER BY religion_id";  
			 
			resultList=(ArrayList)DataAccess.pickData(lStrQuery, false, false);
					
		}   
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl @ getReligionList :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl @ getReligionList : "+lexp.getLocalizedMessage());
		 }
		
		return resultList;
	}
	
	public ArrayList getContactDetails()
	{
		ArrayList resultList = new ArrayList();
    	
    	try
    	{
    		lStrQuery=  
    						/*" SELECT cont.rowid,CASE WHEN cont.type<>'Software' THEN cont.District ELSE '-' END,cont.Name,cont.Designation,cont.Contactno,cont.LandLine,cont.Email,\n" +
    						" CASE WHEN campId IS NOT NULL AND camp.name IS NOT NULL THEN camp.name+' ( '+camp.address+','+camp.venueName+' )' ELSE '-' END address,\n"+
    						" CASE WHEN cont.type='District' THEN 'District Wise DPMs Details' \n" +
    						" WHEN  cont.type='Campincharge' THEN 'District Wise Camp Incharges Details' \n"+
    						" WHEN  cont.type='Software' THEN 'Software Related' \n"+
    						" WHEN cont.Type='RD Call Center' THEN 'RD Call Center'  END mytpe,\n"+
    						" CASE WHEN cont.type='Software' THEN 98\n" + 
    						" WHEN cont.type='District' THEN 0\n" + 
    						" WHEN cont.type='Campincharge' THEN 1\n" + 
    						" ELSE 99 END myorder\n" + 
    						" FROM ContactUs cont WITH(NOLOCK) LEFT OUTER JOIN tblCamp_Details camp  WITH(NOLOCK)\n" + 
    						" ON cont.campid=camp.camp_id and cont.districtid=camp.district_id\n" + 
    						" WHERE cont.status='Active'   and cont.type<>'Software'\n" + 
    						" ORDER BY myorder,cont.District";*/ 
 


							" SELECT ROW_NUMBER() OVER(PARTITION BY district.is_active ORDER BY district.district_name) as rowid,\n" +
							" district.district_name as district,login.PersonName Name,\n" + 
							" (SELECT ISNULL(role_name,'-') FROM roles r with(nolock) WHERE r.role_id=login.role_id)  Designation,\n" + 
							" login.contactno Contactno,'-' as 'Land Line',login.email Email,'-' as address,\n" + 
							" 'District Wise DPMs Details' mytpe,0 myorder\n" + 
							" FROM\n" + 
							" tbldistrict_details district with(nolock)\n" + 
							" LEFT JOIN LOGIN_DETAILS login with(nolock)\n" + 
							" ON(district.district_id = login.district_id AND login.role_id in ('34') AND\n" + 
							" login.contact_view='Y' AND login.is_active='Y' AND  login.status='Active')\n" + 
							" ORDER BY district.district_name";




    		
    		//System.out.println("lStrQuery : "+lStrQuery);
    		
		  	log.info("getContactDetails lStrQuery : "+lStrQuery);
		  	
    		resultList=(ArrayList)DataAccess.pickData(lStrQuery, false, false);
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl @ getContactDetails :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl @ getContactDetails : "+lexp.getLocalizedMessage());
		 }
    	
    	return resultList;
		
		
	}
	
	public ArrayList getDistrictIDSList()
	{
		ArrayList resultList = new ArrayList();
    	
    	try
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    			lStrQuery	=  "SELECT district_id FROM tblDistrict_Details WITH (NOLOCK) ORDER BY district_id";
    		
    	
		  	log.info("getDistrictIDSList lStrQuery : "+lStrQuery);
		  	
    		resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
    		
    		paramList = null;
    		tempList  = null;
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl @ getDistrictIDSList :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl @ getDistrictIDSList : "+lexp.getLocalizedMessage());
		 }
    	
    	return resultList;		
	}
	
	public ArrayList getDistrictList()
	{
		ArrayList resultList = new ArrayList();
    	
    	try
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    			lStrQuery	=  "SELECT district_id ,district_name FROM tblDistrict_Details WITH (NOLOCK) ORDER BY district_name";
    		
    	
		  	log.info("getDistrictList lStrQuery : "+lStrQuery);
		  	
    		resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
    		
    		paramList = null;
    		tempList  = null;
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl @ getDistrictList :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl @ getDistrictList : "+lexp.getLocalizedMessage());
		 }
    	
    	return resultList;		
	}
	
	public ArrayList getMandalListByDistrictID(String distID)
	{
		ArrayList resultList = new ArrayList();
    	
    	try
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		lStrQuery=  "SELECT mandal_id,mandal_name \n"+
    					" FROM tblMandal_Details WITH (NOLOCK) \n"+
    					" WHERE district_id=? \n"+
    					" ORDER BY mandal_name";
    		
    		            tempList.add("S");
			    		tempList.add(distID);
			    		paramList.add(tempList);
    		
		  	log.info("getMandalListByDistrictID lStrQuery : "+lStrQuery+"\n ParamList : "+paramList);
		  	
    		resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
    		// System.out.println("------"+resultList);
    		paramList = null;
    		tempList  = null;
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl @ getMandalListByDistrictID :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl @ getMandalListByDistrictID : "+lexp.getLocalizedMessage());
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
    		
    		lStrQuery=  "SELECT Camp_ID,name+', '+address Name \n"+ 
    					" FROM tblcamp_details WITH (NOLOCK) \n"+
    					" WHERE district_id=? and Status='Active'\n"+
    					" ORDER BY Name";
    		           
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
			 log.info("SQLException in CommonDAOImpl @ getMandalListByDistrictID :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl @ getMandalListByDistrictID : "+lexp.getLocalizedMessage());
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
    		
    		lStrQuery=  " SELECT village_id,village_name \n"+
    					" FROM tblvillage_details with(nolock) \n"+
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
			 log.info("SQLException in CommonDAOImpl @ getVillageListByDistrictIDMandalID :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl @ getVillageListByDistrictIDMandalID : "+lexp.getLocalizedMessage());
		 }
    	
    	return resultList;		
	}
	public ArrayList getVillageListByDistrictIDMandalIDPanchayatId(String distID,String mandID, String PanchayaId)
	{
		ArrayList resultList = new ArrayList();
    	
    	try
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		lStrQuery=  " select v.Village_ID,v.Village_Name from TBLVILLAGE_DETAILS v  with(nolock),TBLHABITATION_DETAILS h  with(nolock) \n "
    					+ "	where v.District_ID=h.District_ID and v.Mandal_ID=h.Mandal_ID and v.Village_ID=h.Village_ID  \n"+
    					" and v.district_id=? AND \n"+
    					" v.mandal_id=? and h.Panchayat_ID=? \n"+
    					" ORDER BY v.village_name";
    		
    		
    		tempList.add("S");
    		tempList.add(distID);
    		paramList.add(tempList);
    		
    		tempList = new ArrayList();
    		tempList.add("S");
    		tempList.add(mandID);
    		paramList.add(tempList);
    		
    		tempList = new ArrayList();
    		tempList.add("S");
    		tempList.add(PanchayaId);
    		paramList.add(tempList);
    		
		  	log.info("getVillageListByDistrictIDMandalID lStrQuery : "+lStrQuery+"\n ParamList : "+paramList);
		  //	System.out.println(lStrQuery+"\n"+paramList);
		  	
    		resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
    		
    		paramList = null;
    		tempList  = null;
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl @ getVillageListByDistrictIDMandalID :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl @ getVillageListByDistrictIDMandalID : "+lexp.getLocalizedMessage());
		 }
    	
    	return resultList;		
	}


	public ArrayList getpanchayatListByDistrictIDMandalIDVillageID(String distID,String mandID)
		{
			ArrayList resultList= new ArrayList();
	    	
	    	try
	    	{
	    		ArrayList paramList = new ArrayList();
	    		ArrayList tempList	= new ArrayList();
	    		
	    		lStrQuery=  " SELECT Panchayat_ID ,Panchayat_Name \n"+
	    					" FROM tblPanchayat_Details with(nolock) \n"+
	    					" WHERE district_id=? AND \n"+
	    					" mandal_id=? \n"+
	    					" ORDER BY Panchayat_Name";
	    		
	    		
	    		tempList.add("S");
	    		tempList.add(distID);
	    		paramList.add(tempList);
	    		
	    		tempList = new ArrayList();
	    		tempList.add("S");
	    		tempList.add(mandID);
	    		paramList.add(tempList);
	    		
			  	log.info("getpanchayatListByDistrictIDMandalIDVillageID lStrQuery : "+lStrQuery+"\n ParamList : "+paramList);
			  	
	    		resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
	    		
	    		paramList = null;
	    		tempList  = null;
	    	}    
			 catch(SQLException sqle)
			 {    
				 log.info("SQLException in CommonDAOImpl @ getpanchayatListByDistrictIDMandalIDVillageID :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
			 }
			 catch(Exception lexp)
			 {
				 log.info("Exception in CommonDAOImpl @ getpanchayatListByDistrictIDMandalIDVillageID : "+lexp.getLocalizedMessage());
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
    		
    		lStrQuery=  "SELECT name+'<br/> ('+venuename+')' from dbo.tblCamp_Details with(nolock) WHERE camp_id=?";
    		
    		tempList.add("I");
    		tempList.add(campID);
    		paramList.add(tempList);
    		
		  	log.info("get lStrQuery : "+lStrQuery+"\n ParamList : ");
		  	
		  	distname=DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
    		
    	
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl : "+lexp.getLocalizedMessage());
		 }
    	
    	return distname;	
	}
	

	
	public String getRoleName(int roleID)
	{
		String distname="";
    	try
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		lStrQuery=  "SELECT role_name FROM roles with(nolock) where role_id=?";
    		
    		tempList.add("I");
    		tempList.add(roleID);
    		paramList.add(tempList);
    		
		  	log.info("get lStrQuery : "+lStrQuery+"\n ParamList : ");
		  	
		  	distname=DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
    		
    	
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl : "+lexp.getLocalizedMessage());
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
    		
    		lStrQuery=  "select a.District_Name from tblDistrict_Details a with(nolock) where a.District_ID IN ( select DISTINCT DistrictID from dbo.tblPerson_Personal_Details  WITH(NOLOCK) where Person_Code=?)";
    		
    		tempList.add("S");
    		tempList.add(sadaremID);
    		paramList.add(tempList);
    		
		  	log.info("get lStrQuery : "+lStrQuery+"\n ParamList : ");
		  	
		  	distname=DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
    		
    	
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl : "+lexp.getLocalizedMessage());
		 }
    	
    	return distname;	
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
	    		
	    		lStrQuery=  "select a.District_Name from tblDistrict_Details a with(nolock) where a.District_ID=?";
	    		
	    		tempList.add("S");
	    		tempList.add(distID);
	    		paramList.add(tempList);
	    		
			  	log.info("get lStrQuery : "+lStrQuery+"\n ParamList : ");
			  	
			  	distname=DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
    		}
    	
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl : "+lexp.getLocalizedMessage());
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
    		
    		
    		lStrQuery=  "select a.Mandal_Name from  tblmandal_details a with(nolock) where a.District_ID=? and a.Mandal_ID=?";
    	
		  
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
			 log.info("SQLException in CommonDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl : "+lexp.getLocalizedMessage());
		 }
    	
    	return mandalname;
	} 
	
	public String getVillageName(String distID, String Mandalid,String villageID) 
	{
		String mandalname="";
    	try
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		
    		lStrQuery=  "select a.village_name from  tblvillage_details a with(nolock) where a.District_ID=? and a.Mandal_ID=? and a.village_id=?";
    	
		  
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
			 log.info("SQLException in CommonDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl : "+lexp.getLocalizedMessage());
		 }
    	
    	return mandalname;
	}
	
	public ArrayList getMenuList(int roleID)
	{
		ArrayList menuList = new ArrayList();
		try
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		lStrQuery=  " select  service_id,parent_id,service_name,target_url \n"+
			    		" from services   WITH(NOLOCK)\n"+
			    		" where service_id in (select service_id from roles_services s  WITH(NOLOCK),roles r  WITH(NOLOCK)\n "
			    		+ " WHERE ( s.role_id = ? OR s.role_id=0) AND \n"
			    		+ " r.role_id=s.role_id  AND \n"
			    		+ " s.is_active='Y' AND \n"
			    		+ " r.is_active='Y' \n"+
			    		"  )  AND is_active='Y'\n"+
			    		" order by parent_id,priority";
			    		
    		
    		tempList.add("I");
    		tempList.add(roleID);
    		paramList.add(tempList);
    		
    		
		  	
		  	menuList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
    		
    		paramList = null;
    		tempList  = null;
			
		}
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl (getMenuList)  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl (getMenuList) : "+lexp.getLocalizedMessage());
		 }
		return menuList;
	}

	public ArrayList getHabitationListByDistrictIDMandalIDvillageID(String distID, String mandID, String villageID) 
	{
		ArrayList resultList	= new ArrayList();
		
		try{
					ArrayList paramList     = new ArrayList();
					ArrayList tempList	    = new ArrayList();
					
					
					lStrQuery=  "select habitation_id,habitation_name from tblHabitation_Details with(nolock)  \n"+
								"where district_id=? and mandal_id=? and village_id=? \n"+
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
					 log.info("SQLException in CommonDAOImpl @ getVillageListByDistrictIDMandalID :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
				 }
				 catch(Exception lexp)
				 {
					 log.info("Exception in CommonDAOImpl @ getVillageListByDistrictIDMandalID : "+lexp.getLocalizedMessage());
				 }
				
				return resultList;		
	}
	
	
	public String getNewSADAREMID(String districtID,String mandalID,String VillageID)
	{
		
		String newSADAREMID="";
    	try
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		lStrQuery=  
    				"SELECT ?+(CONVERT(VARCHAR(10), getdate() ,12))+RIGHT(REPLICATE('0', 4) + CAST(( COUNT(*)+1) AS VARCHAR(4)), 4)\n" +
    						" FROM tblPerson_Personal_Details_new with(nolock)\n" + 
    						" WHERE\n" + 
    						" (CONVERT(VARCHAR(10), created_date ,12))=(CONVERT(VARCHAR(10), getdate() ,12)) AND\n" + 
    						"  districtid=? AND\n" + 
    						"  mandalid=? AND\n" + 
    						"  villageid=?";
    		
        
    		
    		tempList	= new ArrayList();
    		tempList.add("S");
    		tempList.add(districtID+mandalID+VillageID);
    		paramList.add(tempList);
    		

    		tempList	= new ArrayList();
    		tempList.add("S");
    		tempList.add(districtID);
    		paramList.add(tempList);
    		
    		tempList	= new ArrayList();
    		tempList.add("S");
    		tempList.add(mandalID);
    		paramList.add(tempList);
    		
    		tempList	= new ArrayList();
    		tempList.add("S");
    		tempList.add(VillageID);
    		paramList.add(tempList);
    		//System.out.println("**");
    		
		  	log.info("get lStrQuery : "+lStrQuery+"\n ParamList : "+paramList);
		  	
		  	newSADAREMID=DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
    		
    	
    	
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl : "+lexp.getLocalizedMessage());
		 }
    	
    	return newSADAREMID;	
	}
	
	
	public String getHabCode(String districtID,String mandalID,String panchayatID,String villageID,String habitationID)
	{
		String habcode="";
    	try
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		lStrQuery=  
    				"select habitation_code from tblHabitation_Details with(nolock) where district_id=? " +
    	                     " and mandal_id=? and Panchayat_ID=? " +
    	                     "  and village_id=? and habitation_id=?";

    		tempList	= new ArrayList();
    		tempList.add("S");
    		tempList.add(districtID);
    		paramList.add(tempList);
    		
    		tempList	= new ArrayList();
    		tempList.add("S");
    		tempList.add(mandalID);
    		paramList.add(tempList);
    		
    		tempList	= new ArrayList();
    		tempList.add("S");
    		tempList.add(panchayatID);
    		paramList.add(tempList);
    		
    		tempList	= new ArrayList();
    		tempList.add("S");
    		tempList.add(villageID);
    		paramList.add(tempList);
    		
    		tempList	= new ArrayList();
    		tempList.add("S");
    		tempList.add(habitationID);
    		paramList.add(tempList);
    		
		  	log.info("get lStrQuery : "+lStrQuery+"\n ParamList : "+paramList);
		  	
		  	habcode=DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
    		
    	
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl : "+lexp.getLocalizedMessage());
		 }
    	return habcode;
	}
	public String getHabCodewithoutpanchayat(String districtID,String mandalID,String villageID,String habitationID)
	{
		String habcode="";
    	try
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		lStrQuery=  
    				"select habitation_code from tblHabitation_Details with(nolock) where district_id=? " +
    	                     " and mandal_id=? " +
    	                     "  and village_id=? and habitation_id=?";

    		tempList	= new ArrayList();
    		tempList.add("S");
    		tempList.add(districtID);
    		paramList.add(tempList);
    		
    		tempList	= new ArrayList();
    		tempList.add("S");
    		tempList.add(mandalID);
    		paramList.add(tempList);
    		
    		
    		
    		tempList	= new ArrayList();
    		tempList.add("S");
    		tempList.add(villageID);
    		paramList.add(tempList);
    		
    		tempList	= new ArrayList();
    		tempList.add("S");
    		tempList.add(habitationID);
    		paramList.add(tempList);
    		
		  	log.info("get lStrQuery : "+lStrQuery+"\n ParamList : "+paramList);
		  	
		  	habcode=DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
    		
    	
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl : "+lexp.getLocalizedMessage());
		 }
    	return habcode;
	}
	
	public ArrayList searchForSadaremDetails(String sadaremID,String aadhaarID,String distID,String mandalID,String villID,String personName,String relationName)
	{
		ArrayList resultList = new ArrayList();
    	try
    	{
    		
    		if(((sadaremID!=null && sadaremID.length()!=0 )|| (aadhaarID!=null && aadhaarID.length()!=0)) || (distID!=null && distID.length()!=0 && mandalID!=null && mandalID.length()!=0 && villID!=null && villID.length()!=0 && personName!=null && personName.length()!=0 ))
    		{
    			
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		lStrQuery=  
					"SELECT\n" +
					"dis_form_status,sadarem_id,district_name,mandal_name,village_name,habitation_name,person_surname,person_name,\n" + 
					"person_dob,person_gender,rel_type+' '+relation_name relation_details,\n" + 
					"person_live_status,person_contactno,\n" + 
					"type_of_proof,\n" + 
					"proof_id,\n" + 
					"disability_name,\n" + 
					"person_disp_percent,\n" + 
					"CASE WHEN person_disp_percent>=disp_min_per THEN 'Eligible' ELSE 'Ineligible' END cert_status,\n" + 
					"certificate_type,\n" + 
					"certificate_issue_date,\n" + 
					"cert_expire_date\n" + 
					"FROM sadarem_view_complete_details WITH (NOLOCK) WHERE  is_active='Y' \n" ;
    		
    		if(sadaremID.length()>0 && aadhaarID.length()>0)
    		{
    			lStrQuery+=" AND sadarem_id=? AND proof_id=?";
    		
				paramList = new ArrayList();
        		tempList	= new ArrayList();
        		tempList.add("S");
        		tempList.add(sadaremID);
        		paramList.add(tempList);
        		
        		tempList	= new ArrayList();
        		tempList.add("S");
        		tempList.add(aadhaarID);
        		paramList.add(tempList);
    		}
    		else if(sadaremID.length()>0 && aadhaarID.length()==0)
    		{
    			lStrQuery+=" AND sadarem_id=?";
    		
				paramList = new ArrayList();
        		tempList	= new ArrayList();
        		tempList.add("S");
        		tempList.add(sadaremID);
        		paramList.add(tempList);
    		}
    		else if(sadaremID.length()==0 && aadhaarID.length()>0)
    		{
    			lStrQuery+=" AND proof_id=?";
    		
				paramList = new ArrayList();
        		tempList	= new ArrayList();
        		tempList.add("S");
        		tempList.add(aadhaarID);
        		paramList.add(tempList);
    		}
    		else if(distID!=null && distID.length()!=0 && mandalID!=null && mandalID.length()!=0 && villID!=null && villID.length()!=0 && personName!=null && personName.length()!=0 )
    		{
    			lStrQuery+=" AND districtid=? AND \n" + 
					" mandalid=? AND \n" + 
					" villageid=? AND \n" + 
					" UPPER(RTRIM(LTRIM(person_name))) LIKE ?\n " ;
        		
				paramList = new ArrayList();
        		tempList	= new ArrayList();
        		tempList.add("S");
        		tempList.add(distID);
        		paramList.add(tempList);
        		
        		tempList	= new ArrayList();
        		tempList.add("S");
        		tempList.add(mandalID);
        		paramList.add(tempList);

        		tempList	= new ArrayList();
        		tempList.add("S");
        		tempList.add(villID);
        		paramList.add(tempList);        		

        		tempList	= new ArrayList();
        		tempList.add("S");
        		tempList.add("%"+personName.trim().toUpperCase()+"%");
        		paramList.add(tempList);  
        		
        		if(relationName!=null && relationName.length()!=0 )
        		{
        			lStrQuery+=" AND UPPER(RTRIM(LTRIM(relation_name))) LIKE  ?";
	        		tempList	= new ArrayList();
	        		tempList.add("S");
	        		tempList.add("%"+relationName.trim().toUpperCase()+"%"); 
	        		paramList.add(tempList);
        		}
    		}
    		
		  	log.info("searchForSadaremDetails : "+lStrQuery+"\n ParamList : "+paramList);
		  //	System.out.println("searchForSadaremDetails : "+lStrQuery+"\n ParamList : "+paramList);
		  	
		  	resultList=DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
    		}
    	
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
			 sqle.printStackTrace();
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl : "+lexp.getLocalizedMessage());
			 lexp.printStackTrace();
		 }
    	return resultList;
	} 
	public ArrayList sadaremBasicDetails(String sadaremID,String aadhaarID)
	{
		ArrayList resultList = new ArrayList();
    	try
    	{
    		
    		if((sadaremID!=null && sadaremID.length()!=0 )|| (aadhaarID!=null && aadhaarID.length()!=0))
    		{
    			
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		lStrQuery=  
    				"SELECT dis_form_status,sadarem_id,District_name,Mandal_name,Village_name,Habitation_name,\n"+
    						"person_surname,person_name, person_dob,person_gender,rel_type+' '+relation_name relation_details, \n"+
    						"person_live_status,person_contactno, type_of_proof, proof_id, Disability_name, person_disp_percent, \n"+
    						"CASE WHEN person_disp_percent>=disp_min_per THEN 'Eligible' ELSE 'Ineligible' END cert_status, \n"+
    						"certificate_type, certificate_issue_date, cert_expire_date, \n"+
    						"ISNULL(d.certi_status,'No') certi_status,\n"+
    						"ISNULL(d.cert_upload_date,'-') cert_upload_date,\n"+
    						"ISNULL(d.idcard_status,'No') idcard_status,\n"+
    						"ISNULL(d.idcard_upload_date,'-') idcard_upload_date,				\n"+
    						"ISNULL(d.idcard_filepath,'-') idcard_filepath,ISNULL(d.cert_filepath,'-') cert_filepath,\n"+
    						"ISNULL(d.idcard_type,'-') idcard_type,ISNULL(d.certi_type,'-') certi_type\n"
    						+ ",CONVERT(VARCHAR(11),vi.parta_entry_date,106) "+
    						"FROM SADAREM_VIEW_COMPLETE_DETAILS  vi WITH (NOLOCK) \n"+

    						"LEFT JOIN\n"+

    						"(SELECT DISTINCT ref.person_code sadaremid, \n"+
    						"(CASE WHEN a.filepath IS NULL THEN 'N' ELSE a.filepath END) idcard_filepath, \n"+
    						"(CASE WHEN a.filepath IS NULL THEN 'No' ELSE 'Yes' END) idcard_status,\n"+
    						"ISNULL(CONVERT(varchar(20),a.updateddate,113),'-') idcard_upload_date,\n"+
    						"(CASE WHEN a.categoryid=1 THEN 'Regular type'\n"+
    						"WHEN a.categoryid=2 THEN 'Reassessment type'\n"+
    						"WHEN a.categoryid=3 THEN 'Temporary type' END) idcard_type,\n"+
    						"(CASE WHEN b.filepath IS NULL THEN 'N' ELSE b.filepath END) cert_filepath,\n"+
    						"(CASE WHEN b.filepath IS NULL THEN 'No' ELSE 'Yes' END) certi_status,\n"+
    						"ISNULL(CONVERT(varchar(20),b.updateddate,113),'-') cert_upload_date,\n"+
    						"(CASE WHEN b.categoryid=1 THEN 'Regular type'\n"+
    						"WHEN b.categoryid=2 THEN 'Reassessment type'\n"+
    						"WHEN b.categoryid=3 THEN 'Temporary type' END) certi_type\n"+
    						"\n"+
    						"FROM  \n"+
    						"Certificateuploaddetails ref WITH(NOLOCK) \n"+
    						"LEFT JOIN  \n"+
    						"(SELECT person_code,certificatetype,filepath,updateddate,status,categoryid FROM Certificateuploaddetails WITH(NOLOCK) )a ON (ref.person_code=a.person_code AND a.certificatetype='IDCard'AND ref.status=a.status AND ref.categoryid=a.categoryid) \n"+
    						"  LEFT JOIN  \n"+
    						"(SELECT person_code,certificatetype,filepath,updateddate,status,categoryid FROM Certificateuploaddetails WITH(NOLOCK)) b ON (ref.person_code=b.person_code AND b.certificatetype='Certificate' AND ref.status=b.status AND ref.categoryid=b.categoryid) \n"+
    						"  WHERE ref.status='Active') d ON (d.sadaremid=sadarem_id) WHERE  vi.is_active='Y' \n";
    		
    		if(sadaremID.length()>0 && aadhaarID.length()>0)
    		{
    			lStrQuery+="  AND sadarem_id=? AND proof_id=?";
    		
				paramList = new ArrayList();
        		tempList	= new ArrayList();
        		tempList.add("S");
        		tempList.add(sadaremID);
        		paramList.add(tempList);
        		
        		tempList	= new ArrayList();
        		tempList.add("S");
        		tempList.add(aadhaarID);
        		paramList.add(tempList);
    		}
    		else if(sadaremID.length()>0 && aadhaarID.length()==0)
    		{
    			lStrQuery+=" AND sadarem_id=?";
    		
				paramList = new ArrayList();
        		tempList	= new ArrayList();
        		tempList.add("S");
        		tempList.add(sadaremID);
        		paramList.add(tempList);
    		}
    		else if(sadaremID.length()==0 && aadhaarID.length()>0)
    		{
    			lStrQuery+=" AND proof_id=?";
    		
				paramList = new ArrayList();
        		tempList	= new ArrayList();
        		tempList.add("S");
        		tempList.add(aadhaarID);
        		paramList.add(tempList);
    		}
    	
    		
		  	log.info("searchForSadaremDetails : "+lStrQuery+"\n ParamList : "+paramList);
		  	//System.out.println("searchForSadaremDetails : "+lStrQuery+"\n ParamList : "+paramList);
		  	
		  	resultList=DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
    		}
    	
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
			 sqle.printStackTrace();
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl : "+lexp.getLocalizedMessage());
			 lexp.printStackTrace();
		 }
    	return resultList;
	} 
	public String checkAadharTagging(String aadharNo,String sadaremId)
	{
		String status="",qry="";
		ArrayList paramList = new ArrayList();
		ArrayList tempList = null;
		
		qry = "select top 1 person_code from tblperson_personal_details  WITH(NOLOCK) where person_code  <> ? and proof_id=? and status='Active'";

		try 
		{
			tempList = new ArrayList();
			tempList.add("S");
			tempList.add(sadaremId);
			paramList.add(tempList);
			
			tempList = new ArrayList();
			tempList.add("S");
			tempList.add(aadharNo);
			paramList.add(tempList);
		
			
			//System.out.println(qry+"--"+paramList);
			
			status = CommonUtility.checkNullObj(DataAccess.getReturnResultByPstmt(qry, paramList));
		
			//System.out.println("status-->"+status);
			//status = CommonUtility.checkNullObj(DataAccess.getReturnResult(qry));
			
			if(!(status.length()>0))
			{
				qry = "select person_code from tblperson_personal_details_new(nolock) where person_code  <> ? and proof_id=? and status='Active'";
				
			
				status = CommonUtility.checkNullObj(DataAccess.getReturnResultByPstmt(qry, paramList));
				}
			
		} 
		catch (Exception e) 
		{
		  e.printStackTrace();
		}
		
		return status;
	}
	  public ArrayList getMonths(int year) {
	        Calendar cal = Calendar.getInstance();
	        int currentYear = cal.get(Calendar.YEAR);
	        int currentMonth = cal.get(Calendar.MONTH);
	        ArrayList Month = new ArrayList();
	        ArrayList totalMonth = new ArrayList();
	        ArrayList totalMnthList = null;
	        totalMnthList = new ArrayList();	        
	        totalMnthList.add("01");
	        totalMnthList.add("JAN");	        
	        totalMonth.add(totalMnthList);
	        
	        totalMnthList = new ArrayList();        
	        totalMnthList.add("02");
	        totalMnthList.add("FEB");	        
	        totalMonth.add(totalMnthList);
	        
	        totalMnthList = new ArrayList();	        
	        totalMnthList.add("03");
	        totalMnthList.add("MAR");	        
	        totalMonth.add(totalMnthList);
	        
	        totalMnthList = new ArrayList();	       
	        totalMnthList.add("04");
	        totalMnthList.add("APR");	        
	        totalMonth.add(totalMnthList);
	        
	        totalMnthList = new ArrayList();
	        totalMnthList.add("05");
	        totalMnthList.add("MAY");	        
	        totalMonth.add(totalMnthList);
	        
	        totalMnthList = new ArrayList();
	        totalMnthList.add("06");
	        totalMnthList.add("JUN");	        
	        totalMonth.add(totalMnthList);
	        
	        totalMnthList = new ArrayList();
	        totalMnthList.add("07");
	        totalMnthList.add("JUL");	        
	        totalMonth.add(totalMnthList);
	        
	        totalMnthList = new ArrayList();
	        totalMnthList.add("08");
	        totalMnthList.add("AUG");	        
	        totalMonth.add(totalMnthList);
	        
	        totalMnthList = new ArrayList();
	        totalMnthList.add("09");
	        totalMnthList.add("SEP");	        
	        totalMonth.add(totalMnthList);
	        
	        totalMnthList = new ArrayList();
	        totalMnthList.add("10");
	        totalMnthList.add("OCT");	        
	        totalMonth.add(totalMnthList);
	        
	        totalMnthList = new ArrayList();
	        totalMnthList.add("11");
	        totalMnthList.add("NOV");	        
	        totalMonth.add(totalMnthList);
	        
	        totalMnthList = new ArrayList();
	        totalMnthList.add("12");
	        totalMnthList.add("DEC");	        
	        totalMonth.add(totalMnthList);
	        
	         ArrayList mthList = null;
	        if (year == currentYear) {
	            for (int i = 0; i <= currentMonth; i++) {
	                ArrayList m = new ArrayList();
	                mthList = new ArrayList();

	           
	                
	               

	                Month.add(totalMonth.get(i));
	            }
	        } else {
	            for (int i = 0; i <= 11; i++) {
	            	  ArrayList m = new ArrayList();
		                mthList = new ArrayList();


		        


	                Month.add(totalMonth.get(i));
	            }
	        }
	        return Month;
	    }
	  public String insertOrUpdateOtp(String sadaremId, String OTP,String MobNo) throws Exception {
			String returnMsg="N";
			String query="";
			boolean isAlreadyPresent=false;
			boolean commitFlag=false;
			int count=0;
			PreparedStatement pstmt=null;
			Connection con=null;
			ResultSet rs=null;
			try{
			if(!(CommonUtility.checkNullObj(sadaremId).equals("")) && !(CommonUtility.checkNullObj(OTP).equals(""))){
				con=DBConnection.getConnection();
				con.setAutoCommit(false);

					query="INSERT INTO OPENISSUE_OTP_MASTER(OTP,FK_SADAREM_ID,CONTACT_NO,DATE_ADDED,DATE_UPDATED) VALUES(?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
					pstmt=null;
					count=0;
					pstmt=con.prepareStatement(query);
					pstmt.setString(1,OTP);
					pstmt.setString(2,sadaremId);
					pstmt.setString(3,MobNo);
					count=pstmt.executeUpdate();
					if(count==1){
						returnMsg="Y";
						commitFlag=true;
					 }
					else returnMsg="N";

				}
			}
			catch(Exception e){
				returnMsg="N";
				System.out.println("Exception in insertOrUpdateOtp Method ::"+e.getLocalizedMessage());
				e.printStackTrace();
			}
			finally{
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(commitFlag)
					con.commit();
				else
					con.rollback();
				if(con!=null)
					DBConnection.closeConnection();
			}
			return returnMsg;
		}

		public String UpdateOtpDeliveredFlag(String sadaremId, String Flag,String MobNo) throws Exception {

			String returnMsg="N";
			String query="";
			boolean isAlreadyPresent=false;
			boolean commitFlag=false;
			int count=0;
			PreparedStatement pstmt=null;
			Connection con=null;
			ResultSet rs=null;
			try{
			if(!(CommonUtility.checkNullObj(sadaremId).equals(""))){
				query="SELECT COUNT(*) FROM OPENISSUE_OTP_MASTER with(nolock) WHERE FK_SADAREM_ID=? AND DELIVERED_FLAG IS NULL";
				con=DBConnection.getConnection();
				con.setAutoCommit(false);
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, sadaremId);
				rs=pstmt.executeQuery();
				while(rs.next()){
					count=Integer.parseInt(rs.getString(1));
				}
				if(count==1)
					isAlreadyPresent=true;
				if(isAlreadyPresent && Flag.equals("Y"))
					query=" UPDATE OPENISSUE_OTP_MASTER SET DELIVERED_FLAG=CASE WHEN DELIVERED_FLAG IS NULL THEN 'Y' ELSE DELIVERED_FLAG END, \n"+
						  " ACTIVE_FLAG=CASE WHEN DELIVERED_FLAG IS NULL THEN 'Y' ELSE 'N' END, \n"+
						  " DATE_UPDATED=CURRENT_TIMESTAMP WHERE FK_SADAREM_ID=? \n";
				else if(isAlreadyPresent && Flag.equals("N"))
					query=" UPDATE OPENISSUE_OTP_MASTER SET DELIVERED_FLAG=CASE WHEN DELIVERED_FLAG IS NULL THEN 'N' ELSE DELIVERED_FLAG END, \n" +
						  " ACTIVE_FLAG=CASE WHEN DELIVERED_FLAG IS NULL THEN 'N' ELSE 'N' END, \n"+
						  " DATE_UPDATED=CURRENT_TIMESTAMP WHERE FK_SADAREM_ID=? ";
				if(isAlreadyPresent){
				pstmt=null;
				count=0;
				pstmt=con.prepareStatement(query);
				pstmt.setString(1,sadaremId);
				count=pstmt.executeUpdate();
				if(count>=1){
					returnMsg="Y";
					commitFlag=true;
				 }
				}
			  }
			}
			catch(Exception e){
				returnMsg="N";
				System.out.println("Exception in UpdateOtpDeliveredFlag Method ::"+e.getLocalizedMessage());
				e.printStackTrace();
			}
			finally{
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(commitFlag)
					con.commit();
				else
					con.rollback();
				if(con!=null)
					DBConnection.closeConnection();
			}
			return returnMsg;
		
		}
	
			
		public boolean sendTKTRequestStatusbySMS(String reqID,String author)
		{
			boolean status= false;
			
			PreparedStatement pstmt=null;
			
			try
			{
				lcon=DBConnection.getConnection();
				
				lStrQuery=
						"INSERT INTO tkt_sms_status_log\n" +
								"(trans_id,tkt_req_id,sadarem_id,status_flag,contact_no,sms_txt,is_sent,is_active,created_by,created_date)\n" + 
								"SELECT req.tkt_req_id+req.status_flag trans_id,\n" + 
								"req.tkt_req_id,req.sadarem_id,req.status_flag,\n" + 
								"ISNULL(req.status_contno,'') contact_num,\n" + 
								"('Req Id:'+req.tkt_req_id+'. Status :'+flag.tkt_flag_status) sms_msg,\n" + 
								"'N' is_sent,'Y' is_active,? created_by,current_timestamp created_date\n" + 
								" FROM\n" + 
								"tkt_request_master req with(nolock),\n" + 
								"tkt_request_tobe_modify_dtls mo with(nolock),\n" + 
								"tkt_status_flag_master flag with(nolock)\n" + 
								"WHERE\n" + 
								"req.tkt_req_id = mo.tkt_req_id AND\n" + 
								//"req.req_sms='Y' AND\n" + 
								"req.tkt_req_id=? AND\n" + 
								"req.status_flag=flag.tkt_flag_id \n";
								//+ "AND ISNULL(req.contactNo,mo.contactNo) IS NOT NULL";
 
				
					pstmt=lcon.prepareStatement(lStrQuery);
					pstmt.setString(1,author);
					pstmt.setString(2,reqID);
					int reCount = pstmt.executeUpdate();
					
					if(reCount>0)
					{
						status = true;
						 
					}
					else
					{
						status = false;
					}
					
					lcon.close();
			}
			 catch(SQLException sqle)
			 {    
					status = false;
					System.out.println("fail in sendTKTRequestStatusbySMS");
				 log.info("SQLException in CommonDAOImpl @ sendTKTRequestStatusbySMS :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
			 }
			 catch(Exception lexp)
			 {
					status = false;

					System.out.println("fail in sendTKTRequestStatusbySMS");
				 log.info("Exception in CommonDAOImpl @ sendTKTRequestStatusbySMS : "+lexp.getLocalizedMessage());
			 }
			finally
			{
				DBConnection.closeStatement(pstmt);
				DBConnection.closeConnection(lcon);
			}
			
			return status;
		} 
		
		public String getContactNoOfLogin(String username)
		{
			String qry="",contactNo="";
			ArrayList paramList = new ArrayList();
			ArrayList tempList = new ArrayList();
			
			qry="select contactno from login_details with(nolock) where username=?";
			try
			{     
				tempList = new ArrayList();
				tempList.add("S");
				tempList.add(username);
				paramList.add(tempList);
		   // contactNo = DataAccess.getReturnResult(qry);  
				
			contactNo = DataAccess.getReturnResultByPstmt(qry, paramList);
			}
			catch(Exception e)
			{
			 log.info("Exception in CommonDAOImpl @ getContactNoOfLogin"+e.getLocalizedMessage());	
			}
			return contactNo;
		}
		
       public String getDistrictId(String sadaremId,String AadharId)
       {
    	   String districtId = "",qry="";
    		ArrayList paramList = new ArrayList();
			ArrayList tempList = null;
	    	try
	    	{
	    	   if(sadaremId.length()>0 && AadharId.length()>0)
	    	   {
	    		 qry = "select districtid from tblperson_personal_details  WITH(NOLOCK) where person_code=? and proof_id=?"; 
	    		 tempList = new ArrayList();
					tempList.add("S");
					tempList.add(sadaremId);
					paramList.add(tempList);
					
					tempList = new ArrayList();
					tempList.add("S");
					tempList.add(AadharId);
					paramList.add(tempList);
	    	   }
	    	   else if(sadaremId.length()>0)
	    	   {
	    		   qry = "select districtid from tblperson_personal_details  WITH(NOLOCK) where person_code=? ";
	    		   tempList = new ArrayList();
					tempList.add("S");
					tempList.add(sadaremId);
					paramList.add(tempList);
	    	   }
	    	   else if(AadharId.length()>0)
	    	   {
	    		   qry = "select districtid from tblperson_personal_details  WITH(NOLOCK) where  proof_id=?"; 
	    		   tempList = new ArrayList();
					tempList.add("S");
					tempList.add(AadharId);
					paramList.add(tempList);
	    	   }
	    	   

	    	   districtId = DataAccess.getReturnResultByPstmt(qry, paramList);
	    	   
	    	  
	    	}
	    	catch(Exception e )
	    	{
	    		log.info("Exception raised in getDistrictId"+e.getLocalizedMessage());
	    	}
    	   return districtId; 
       }
       
       
       public String getMandalId(String sadaremId,String AadharId,String districtId)
       {
    	   String mandalId = "",qry="";
    		ArrayList paramList = new ArrayList();
			ArrayList tempList = null;
	    	try
	    	{
	    	   if(sadaremId.length()>0 && AadharId.length()>0)
	    	   {
	    		 qry = "select mandalid from tblperson_personal_details  WITH(NOLOCK) where person_code=? and proof_id=? and districtid=?";
	    		 tempList = new ArrayList();
					tempList.add("S");
					tempList.add(sadaremId);
					paramList.add(tempList);
					
					tempList = new ArrayList();
					tempList.add("S");
					tempList.add(AadharId);
					paramList.add(tempList);
					
					tempList = new ArrayList();
					tempList.add("S");
					tempList.add(districtId);
					paramList.add(tempList);
	    	   }
	    	   else if(sadaremId.length()>0)
	    	   {
	    		   qry = "select mandalid from tblperson_personal_details  WITH(NOLOCK) where person_code=? and districtid=?" ;
	    		   tempList = new ArrayList();
					tempList.add("S");
					tempList.add(sadaremId);
					paramList.add(tempList);
					
					tempList = new ArrayList();
					tempList.add("S");
					tempList.add(districtId);
					paramList.add(tempList);
	    	   }
	    	   else if(AadharId.length()>0)
	    	   {
	    		   qry = "select mandalid from tblperson_personal_details WITH(NOLOCK) where  proof_id=?  and districtid=?" ;
	    		   tempList = new ArrayList();
					tempList.add("S");
					tempList.add(AadharId);
					paramList.add(tempList);
					
					tempList = new ArrayList();
					tempList.add("S");
					tempList.add(districtId);
					paramList.add(tempList);
	    	   }
	    	   
	    	

	    	   mandalId = DataAccess.getReturnResultByPstmt(qry, paramList);
	    	}
	    	catch(Exception e )
	    	{
	    		log.info("Exception raised in getMandalId"+e.getLocalizedMessage());
	    	}
    	   return mandalId; 
       }
       
       
       public boolean generateOTPforappllateAuthority(String sadaremID,String reqId,String contactNo,String author,int myrecount)
       {
    	   boolean status = false;
    	   try
    	   {
    		   
    		   if(myrecount<3)
    		   {  
    			   String newOTPnum = CommonUtility.generateRandomStr(4);

   			    String isDelivered = "N";
        		   
        		   String message = "Your (OTP) is "+ newOTPnum+ " .Please Enter the OTP to complete Appellate Authority Process of SADAREM ID "+sadaremID+" & Grievances ID : "+reqId.trim()+".Do not share this OTP to anyone.";
    			    ArrayList smsResult = (ArrayList) SMSUtility.sendmysms(contactNo,message);
    			   
    			  
    					if (smsResult.size() > 0 )
    					{ 
    						isDelivered=smsResult.get(0).toString();
    					} 
    			   

    	        		  // System.out.println("myrecount : "+myrecount+" / smsResult : "+smsResult);
					if(isDelivered.equalsIgnoreCase("N"))
					{
						generateOTPforappllateAuthority(sadaremID,reqId,contactNo,author,myrecount+1);
					}
					else
					{
						status = insertOrUpdateOTPAppellate(sadaremID,reqId,contactNo,newOTPnum,isDelivered,author);
					}
    		   }
    		   else
    		   {
    			   status = false;
    		   }
    		   
    	   }
    	   catch(Exception e)
    	   {
    		   e.printStackTrace();
    	   }
    	   
    	   return status;
       }
       
       
       public boolean insertOrUpdateOTPAppellate(String sadaremID,String reqId,String contactNo,String newOTPnum,String isDelivered,String author)
       {
    	   boolean resultStatus = false;
    		PreparedStatement pstmt=null;
    		lcon= null;
    		
    	   try
    	   {
    		   lcon=DBConnection.getConnection();
				 
				lStrQuery = 
								"MERGE tkt_apa_otp_details t\n" +
								"USING\n" + 
								"(\n" + 
								"SELECT ? sadarem_id,? tkt_req_id,? contact_no,count(*) mycount\n" + 
								"FROM tkt_apa_otp_details\n" + 
								"WHERE\n" + 
								" sadarem_id=? AND\n" + 
								" tkt_req_id=? \n" + 
								") s\n" + 
								"ON (t.tkt_req_id = s.tkt_req_id AND t.sadarem_id = s.sadarem_id AND  t.contact_no = s.contact_no)\n" + 
								"WHEN MATCHED AND (s.mycount>0) THEN\n" + 
								"  UPDATE\n" + 
								"  SET req_otp_no=?,is_delivered=?,t.updated_by=?,t.updated_date = current_timestamp \n" + 
								"WHEN NOT MATCHED THEN\n" + 
								"INSERT (sadarem_id,tkt_req_id,contact_no,req_otp_no,is_delivered,created_by,created_date)\n" + 
								"values(s.sadarem_id,s.tkt_req_id,s.contact_no,?,?,?,current_timestamp);";
				
				//System.out.println("lStrQuery : \n "+lStrQuery);
				
				
					pstmt =lcon.prepareStatement(lStrQuery);
				
					
					pstmt.setString(1, sadaremID);
					pstmt.setString(2 , reqId);					 
					pstmt.setString(3, contactNo);
					pstmt.setString(4, sadaremID);
					pstmt.setString(5, reqId);
					pstmt.setString(6, newOTPnum);
					pstmt.setString(7, isDelivered);
					pstmt.setString(8, author);
					pstmt.setString(9, newOTPnum);
					pstmt.setString(10, isDelivered);
					pstmt.setString(11, author);
					
					//System.out.println("sadaremID : "+sadaremID +" reqId : "+reqId+" contactNo : "+contactNo+" newOTPnum:"+newOTPnum+" isDelivered :"+isDelivered+" author:"+author);
					
					int res = pstmt.executeUpdate();
					
					if(res>0)
					{
						resultStatus = true;
					}
					else
					{
						resultStatus = false;
					}
					
					lcon.close();
					
					
    	   }
    	   catch(SQLException e)
    	   {
    		   e.printStackTrace();
    		   resultStatus = false;
    	   }
    	   catch(Exception e)
    	   {
    		   e.printStackTrace();
    		   resultStatus = false;
    	   }
    	   finally 
    	   {
    		   DBConnection.closeConnection(lcon);
    		   DBConnection.closeStatement(pstmt);
    	   }
    	   
    	   
    	   
    	   return resultStatus;
       }
     

		/* public String insertOrUpdateAppellateOtp(HashMap otpDtls) throws Exception {
				String returnMsg="N";
				String query="",sadaremId,otp,reqId,contactNo,loginId;
				boolean isAlreadyPresent=false;
				boolean commitFlag=false;
				int count=0;
				PreparedStatement pstmt=null;
				Connection con=null;
				ResultSet rs=null;
				contactNo = CommonUtility.checkNullObj(otpDtls.get("MobNo"));
				sadaremId = CommonUtility.checkNullObj(otpDtls.get("sadaremId"));
				reqId = CommonUtility.checkNullObj(otpDtls.get("reqId"));
				otp   = CommonUtility.checkNullObj(otpDtls.get("OTP"));
				loginId =CommonUtility.checkNullObj(otpDtls.get("loginId"));
				try{
				if(!(CommonUtility.checkNullObj(sadaremId).equals("")) && !(CommonUtility.checkNullObj(otp).equals(""))){
					con=DBConnection.getConnection();
					con.setAutoCommit(false);

						query="INSERT INTO tkt_apa_otp_details(sadarem_id,tkt_req_id,contact_no,req_otp_no,created_by,created_date) VALUES(?,?,?,?,?,CURRENT_TIMESTAMP)";
						pstmt=null;
						count=0;
						pstmt=con.prepareStatement(query);
						pstmt.setString(1,sadaremId);
						pstmt.setString(2,reqId);
						pstmt.setString(3,contactNo);
						pstmt.setString(4,otp);
						pstmt.setString(5,loginId);
						count=pstmt.executeUpdate();
						if(count==1){
							returnMsg="Y";
							commitFlag=true;
						 }
						else returnMsg="N";

					}
				}
				catch(Exception e){
					returnMsg="N";
					System.out.println("Exception in insertOrUpdateOtp Method ::"+e.getLocalizedMessage());
					e.printStackTrace();
				}
				finally{
					if(rs!=null)
						rs.close();
					if(pstmt!=null)
						pstmt.close();
					if(commitFlag)
						con.commit();
					else
						con.rollback();
					if(con!=null)
						DBConnection.closeConnection();
				}
				return returnMsg;
			}*/

			/*public String AppellateUpdateOtpDeliveredFlag(String sadaremId,String Flag,String MobNo,String loginId) throws Exception {

				String returnMsg="N";
				String query="";
				boolean isAlreadyPresent=false;
				boolean commitFlag=false;
				int count=0;
				PreparedStatement pstmt=null;
				Connection con=null;
				ResultSet rs=null;
				try{
				if(!(CommonUtility.checkNullObj(sadaremId).equals(""))){
					query="SELECT COUNT(*) FROM tkt_apa_otp_details WHERE sadarem_id=? AND is_delivered='P'";
					con=DBConnection.getConnection();
					con.setAutoCommit(false);
					pstmt=con.prepareStatement(query);
					pstmt.setString(1, sadaremId);
					rs=pstmt.executeQuery();
					while(rs.next()){
						count=Integer.parseInt(rs.getString(1));
					}
					if(count==1)
						isAlreadyPresent=true;
					if(isAlreadyPresent && Flag.equals("Y"))
						query=" UPDATE tkt_apa_otp_details SET is_delivered=CASE WHEN is_delivered='P' THEN 'Y' ELSE is_delivered END, \n"+
							  " UPDATED_BY='"+loginId+"', \n"+
							  " UPDATED_DATE=CURRENT_TIMESTAMP WHERE sadarem_id=? \n";
					else if(isAlreadyPresent && Flag.equals("N"))
						query=" UPDATE tkt_apa_otp_details SET is_delivered=CASE WHEN is_delivered='P' THEN 'N' ELSE is_delivered END, \n" +
							  " UPDATED_BY='"+loginId+"', \n"+
							  " UPDATED_DATE=CURRENT_TIMESTAMP WHERE sadarem_id=? ";
					if(isAlreadyPresent){
					pstmt=null;
					count=0;
					pstmt=con.prepareStatement(query);
					pstmt.setString(1,sadaremId);
					count=pstmt.executeUpdate();
					if(count>=1){
						returnMsg="Y";
						commitFlag=true;
					 }
					}
				  }
				}
				catch(Exception e){
					returnMsg="N";
					System.out.println("Exception in UpdateOtpDeliveredFlag Method ::"+e.getLocalizedMessage());
					e.printStackTrace();
				}
				finally{
					if(rs!=null)
						rs.close();
					if(pstmt!=null)
						pstmt.close();
					if(commitFlag)
						con.commit();
					else
						con.rollback();
					if(con!=null)
						DBConnection.closeConnection();
				}
				return returnMsg;
			
			}*/
       
       public ArrayList getSADAREMIDByAadhaarID(String aadhaarID)
       {
    	   ArrayList resultList = new ArrayList();
    	   
    	   try
    	   {

/*log.info("getSADAREMIDByAadhaarID aadhaarID : "+aadhaarID);*/
    		   if(!aadhaarID.trim().equals(""))
    		   {
		    		   ArrayList paramList = new ArrayList();
		    		   ArrayList tempList  = new ArrayList();
		    		   
		    		   lStrQuery =
		    				   " SELECT DISTINCT person_code,CONVERT(VARCHAR,created_date,109)+' - R' created_date \n" +
		    				   " FROM tblperson_personal_details WITH(NOLOCK) \n" + 
		    				   " WHERE LTRIM(RTRIM(proof_id))=? \n" +  
		    				   " UNION \n" +  
		    				   " SELECT DISTINCT person_code,CONVERT(VARCHAR,created_date,109)+' - T' created_date \n" + 
		    				   " FROM tblperson_personal_details_new WITH(NOLOCK) \n" + 
		    				   " WHERE LTRIM(RTRIM(proof_id))=? \n"+
		    				   " UNION \n" +  
		    				   " SELECT DISTINCT ISNULL(sadarem_id,request_id) sadarem_id,CONVERT(VARCHAR,created_date,109)+' - A' created_date \n"+
		    				    " FROM sadarem_without_proof_request_master  WITH(NOLOCK) \n" + 
		    				   " WHERE LTRIM(RTRIM(proof_id))=? and req_status NOT IN ('R','D')";
		    		   
		    		   tempList  = new ArrayList();
		    		   tempList.add("S");
		    		   tempList.add(aadhaarID.trim());
		    		   paramList.add(tempList);
		    		   
		    		   tempList  = new ArrayList();
		    		   tempList.add("S");
		    		   tempList.add(aadhaarID.trim());
		    		   paramList.add(tempList);
		    		   
		    		   tempList  = new ArrayList();
		    		   tempList.add("S");
		    		   tempList.add(aadhaarID.trim());
		    		   paramList.add(tempList);
		    		   

					/*log.info("paramList : "+paramList);
					log.info("lStrQuery : "+lStrQuery);*/
		    		   
		    		   resultList = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		    		   
		    		   
    		   }
    		   else
    		   {
    			   resultList = new ArrayList();
    		   }
    		   	//log.info("getSADAREMIDByAadhaarID : "+resultList);
    	   }
    	   catch(Exception e)
    	   {
    		   log.error(e);
    		   e.printStackTrace();
    	   }

    	   	return resultList;
       }
       public ArrayList searchForSadaremHistory(String sadaremID,String aadhaarID,String distID,String mandalID,String villID,String personName,String relationName)
   		{
			   		ArrayList resultList = new ArrayList();
			       	try
			       	{
			       		
			       		if(((sadaremID!=null && sadaremID.length()!=0 )|| (aadhaarID!=null && aadhaarID.length()!=0)) || (distID!=null && distID.length()!=0 && mandalID!=null && mandalID.length()!=0 && villID!=null && villID.length()!=0 && personName!=null && personName.length()!=0 ))
			       		{
			       			
			       		ArrayList paramList = new ArrayList();
			       		ArrayList tempList	= new ArrayList();
			       		
			       		
			       		lStrQuery=" select Created_Date,FirstLogin,TotalDisability,UPdated_Date,"
			   					+ " case when Multiple_Disability_Sub_ID>0 then 'Multiple Disability'\n"
			   					+ "when HearingImpairment>0 then 'Hearing Impairment'\n"
			   					+ "when VisualImpairment>0 then 'Visual Impairment'\n"
			   					+ "when MentalRetardation>0 then 'Mental Retardation'\n"
			   					+ "when MentalIllness>0 then 'Mental Illness'\n"
			   					+ "when TotalLocomotor>0 then 'Locomotor/OH' \n"
			   					+"else '-' end as 'Type Of Disability'\n"
			   					+ " from tblPerson_Disability_TotalValue_Details with(nolock) \n";
			       		if(sadaremID.length()>0 && aadhaarID.length()>0)
			       		{    					
			       			lStrQuery=lStrQuery	+ " where Person_Code=? \n"
			       					+ " and Person_Code in (select person_code from tblperson_personal_details with(nolock) where Proof_Id=? )\n ";
			       		
			   				paramList = new ArrayList();
			           		tempList	= new ArrayList();
			           		tempList.add("S");
			           		tempList.add(sadaremID);
			           		paramList.add(tempList);
			           		
			           		tempList	= new ArrayList();
			           		tempList.add("S");
			           		tempList.add(aadhaarID);
			           		paramList.add(tempList);
			       		}
			       		else if(sadaremID.length()>0 && aadhaarID.length()==0)
			       		{
			       			lStrQuery=lStrQuery	
			       					+ "where Person_Code=? \n";
			       		
			   				paramList = new ArrayList();
			           		tempList	= new ArrayList();
			           		tempList.add("S");
			           		tempList.add(sadaremID);
			           		paramList.add(tempList);
			       		}
			       		else if(sadaremID.length()==0 && aadhaarID.length()>0)
			       		{
			       			lStrQuery=lStrQuery	
			       					+ " where Person_Code in (select person_code from tblperson_personal_details with(nolock) where Proof_Id=? )\n";
			       		
			   				paramList = new ArrayList();
			           		tempList	= new ArrayList();
			           		tempList.add("S");
			           		tempList.add(aadhaarID);
			           		paramList.add(tempList);
			       		}
			       		else if(distID!=null && distID.length()!=0 && mandalID!=null && mandalID.length()!=0 && villID!=null && villID.length()!=0 && personName!=null && personName.length()!=0 )
			       		{
			       			lStrQuery=lStrQuery	
			       					+ " where Person_Code in(select sadarem_id FROM sadarem_view_complete_details WITH (NOLOCK) WHERE  is_active='Y'";
			       			lStrQuery+=" AND districtid=? AND \n" + 
			   					" mandalid=? AND \n" + 
			   					" villageid=? AND \n" + 
			   					" RTRIM(LTRIM(person_name)) = ?\n " ;
			           		
			   				paramList = new ArrayList();
			           		tempList	= new ArrayList();
			           		tempList.add("S");
			           		tempList.add(distID);
			           		paramList.add(tempList);
			           		
			           		tempList	= new ArrayList();
			           		tempList.add("S");
			           		tempList.add(mandalID);
			           		paramList.add(tempList);
			
			           		tempList	= new ArrayList();
			           		tempList.add("S");
			           		tempList.add(villID);
			           		paramList.add(tempList);        		
			
			           		tempList	= new ArrayList();
			           		tempList.add("S");
			           		tempList.add(personName.trim()); 
			           		paramList.add(tempList);  
			           		
			           		if(relationName!=null && relationName.length()!=0 )
			           		{
			           			lStrQuery+=" AND RTRIM(LTRIM(relation_name))= ?)  \n ";
			   	        		tempList	= new ArrayList();
			   	        		tempList.add("S");
			   	        		tempList.add(relationName.trim());
			   	        		paramList.add(tempList);
			           		}
			       		}
			       		
			       		
			       		lStrQuery+=
			       				" UNION ALL SELECT r.created_date, r.firstlogin,0 TotalDisability, r.updated_date,d.disability_name+' (Direct Rejection)' type_of_disp\n" +
			       						"FROM\n" + 
			       						"tblRejectPerson_Details r with(nolock) ,\n" + 
			       						"tblDisability_Details d with(nolock)\n" + 
			       						" WHERE\n" + 
			       						"r.disability_id = d.disability_id \n ";
			       		if(sadaremID.length()>0 && aadhaarID.length()>0)
			       		{    					
			       			lStrQuery+=" AND  Person_Code=? \n"
			       					+ " and Person_Code in (select person_code from tblperson_personal_details with(nolock) where Proof_Id=? )\n ";
			       		 
			           		tempList	= new ArrayList();
			           		tempList.add("S");
			           		tempList.add(sadaremID);
			           		paramList.add(tempList);
			           		
			           		tempList	= new ArrayList();
			           		tempList.add("S");
			           		tempList.add(aadhaarID);
			           		paramList.add(tempList);
			       		}
			       		else if(sadaremID.length()>0 && aadhaarID.length()==0)
			       		{
			       			lStrQuery+=" AND  Person_Code=?  \n";
			       		 
			           		tempList	= new ArrayList();
			           		tempList.add("S");
			           		tempList.add(sadaremID);
			           		paramList.add(tempList);
			       		}
			       		else if(sadaremID.length()==0 && aadhaarID.length()>0)
			       		{
			       			lStrQuery+=" AND  Person_Code in (select person_code from tblperson_personal_details with(nolock) where Proof_Id=? ) ";
			       		 
			           		tempList	= new ArrayList();
			           		tempList.add("S");
			           		tempList.add(aadhaarID);
			           		paramList.add(tempList);
			       		}
			       		else if(distID!=null && distID.length()!=0 && mandalID!=null && mandalID.length()!=0 && villID!=null && villID.length()!=0 && personName!=null && personName.length()!=0 )
			       		{
			       			lStrQuery+=" AND Person_Code in(select sadarem_id FROM sadarem_view_complete_details WITH (NOLOCK) WHERE  is_active='Y'";
			       			lStrQuery+=" AND districtid=? AND \n" + 
			   					" mandalid=? AND \n" + 
			   					" villageid=? AND \n" + 
			   					" RTRIM(LTRIM(person_name)) = ?\n " ;
			           		 
			           		tempList	= new ArrayList();
			           		tempList.add("S");
			           		tempList.add(distID);
			           		paramList.add(tempList);
			           		
			           		tempList	= new ArrayList();
			           		tempList.add("S");
			           		tempList.add(mandalID);
			           		paramList.add(tempList);
			
			           		tempList	= new ArrayList();
			           		tempList.add("S");
			           		tempList.add(villID);
			           		paramList.add(tempList);        		
			
			           		tempList	= new ArrayList();
			           		tempList.add("S");
			           		tempList.add(personName.trim());
			           		paramList.add(tempList);  
			           		
			           		if(relationName!=null && relationName.length()!=0 )
			           		{
			           			lStrQuery+=" AND RTRIM(LTRIM(relation_name))= ?) ";
			   	        		tempList	= new ArrayList();
			   	        		tempList.add("S");
			   	        		tempList.add(relationName.trim());
			   	        		paramList.add(tempList);
			           		}
			       		}
			       		
			       		lStrQuery+=" order by Created_Date ";
			   		  	log.info("searchForSadaremHistoryDetails : "+lStrQuery+"\n ParamList : "+paramList);
			   		  	// System.out.println("searchForSadaremDetails : "+lStrQuery+"\n ParamList : "+paramList);
			   		  	
			   		  	resultList=DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			       		}
			       	
			       	}    
			   		 catch(SQLException sqle)
			   		 {    
			   			 log.info("SQLException in CommonDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
			   			 sqle.printStackTrace();
			   		 }
			   		 catch(Exception lexp)
			   		 {
			   			 log.info("Exception in CommonDAOImpl : "+lexp.getLocalizedMessage());
			   			 lexp.printStackTrace();
			   		 }
			       	return resultList;		
		   		}
	
			public String searchForStatusRemarks(String sadaremID, String aadhaarID)
			{
				String returnRes="";
				try
				{	  
					ArrayList paramList = new ArrayList();
		       		ArrayList tempList	= new ArrayList();
		       		
		       		
		       		lStrQuery="select reasonforpersonstatus,status from tblperson_personal_details with(nolock) where status!='Active'\n";
		       		if(sadaremID.length()>0 && aadhaarID.length()>0)
		       		{    					
		       			lStrQuery=lStrQuery	+ " and Person_Code=? \n"
		       					+ " and Person_Code in (select person_code from tblperson_personal_details with(nolock) where Proof_Id=? )";
		       		
		   				paramList = new ArrayList();
		           		tempList	= new ArrayList();
		           		tempList.add("S");
		           		tempList.add(sadaremID);
		           		paramList.add(tempList);
		           		
		           		tempList	= new ArrayList();
		           		tempList.add("S");
		           		tempList.add(aadhaarID);
		           		paramList.add(tempList);
		       		}
		       		else if(sadaremID.length()>0 && aadhaarID.length()==0)
		       		{
		       			lStrQuery=lStrQuery	
		       					+ "and Person_Code=? \n";
		       		
		   				paramList = new ArrayList();
		           		tempList	= new ArrayList();
		           		tempList.add("S");
		           		tempList.add(sadaremID);
		           		paramList.add(tempList);
		       		}
		       		else if(sadaremID.length()==0 && aadhaarID.length()>0)
		       		{
		       			lStrQuery=lStrQuery	
		       					+ "and  Person_Code in (select person_code from tblperson_personal_details with(nolock) where Proof_Id=? ) ";
		       		
		   				paramList = new ArrayList();
		           		tempList	= new ArrayList();
		           		tempList.add("S");
		           		tempList.add(aadhaarID);
		           		paramList.add(tempList);
		       		}
		   		  	log.info("searchForSadaremDetails : "+lStrQuery+"\n ParamList : "+paramList);		   		  	
		   		  	ArrayList resultList = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		   		  	if(resultList==null || resultList.size()==0)
		   		  	{
		   		  		return "There is No Data found. \n";
		   		  	}
		   		  	resultList = (ArrayList) resultList.get(0);
		   		  	returnRes=statusCaseExplation(resultList.get(0).toString(),resultList.get(1).toString());
		       	}    
		   		 catch(SQLException sqle)
		   		 {    
		   			 log.info("SQLException in CommonDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		   			 sqle.printStackTrace();
		   		 }
		   		 catch(Exception lexp)
		   		 {
		   			 log.info("Exception in CommonDAOImpl : "+lexp.getLocalizedMessage());
		   			 lexp.printStackTrace();
		   		 }
				return returnRes;
			}
			public String statusCaseExplation(String reasonforpersonstatus,String status)
			{
				status=status.trim();
				String returnstmt="The Person status is 'In-Active'. \n";
				if(status.equalsIgnoreCase("Inactive"))
				{
					if(reasonforpersonstatus.contains("Blocked"))
					{
						returnstmt+="The Person blocked By Higher authority.";
					}
					else if(reasonforpersonstatus.contains("Dead"))
					{
						returnstmt+="The Person is not Alive.";
					}
					else if(reasonforpersonstatus.contains("Duplicate"))
					{
						returnstmt+="Duplicate records may Exist for this Person.";
					}
					else if(reasonforpersonstatus.contains("Default"))
					{
						returnstmt+="Duplicate records may Exist for this Person.";
					}
					else if(reasonforpersonstatus.contains("Ineligible"))
					{
						returnstmt+="The Status is Not Eligible.";
					}
					else if(reasonforpersonstatus.contains("Not Eligible"))
					{
						returnstmt+="The Status is Not Eligible.";
					}
				}
				else if(status.equalsIgnoreCase("Default"))
				{
					if(reasonforpersonstatus.contains("Blocked"))
					{
						returnstmt+="The Person is blocked by Higher Authority.";
					}
					else if(reasonforpersonstatus.contains("Dead"))
					{
						returnstmt+="The Person is Not Alive.";
					}
					else if(reasonforpersonstatus.contains("Duplicate"))
					{
						returnstmt+="Duplicate Records may Exist for this Person.";
					}
					else if(reasonforpersonstatus.contains("Ineligible"))
					{
						returnstmt+="The Status is Not Eligible.";
					}
					else if(reasonforpersonstatus.contains("Not Eligible"))
					{
						returnstmt+="The Status is Not Eligible.";
					}
				}
				else if(status.equalsIgnoreCase("Duplicate"))
				{
					returnstmt+="Duplicate Records may Exist for this Person.";
				}
				return returnstmt;
			}
			public String getPanchayatNameByDistIdMandalIdPanchId(String distId,String mandalId, String panchId)
			{
				String PanchayatName="";
				ArrayList paramList = new ArrayList();
	    		ArrayList tempList	= new ArrayList();
				String qry="";
		    	try
		    	{		    	   
		    		 qry = "select Panchayat_Name from tblPanchayat_Details with(nolock) where DISTRICT_ID=? and Mandal_ID=? and Panchayat_ID=?";
		    	   
		    		 tempList.add("S");
		    		 tempList.add(distId);
		    		 paramList.add(tempList);
		    		 
		    		 tempList = new ArrayList();
		    		 tempList.add("S");
		    		 tempList.add(mandalId);
		    		 paramList.add(tempList);
		    		 
		    		 tempList = new ArrayList();
		    		 tempList.add("S");
		    		 tempList.add(panchId);
		    		 paramList.add(tempList);
		    		
		    		 
		    		 PanchayatName = DataAccess.getReturnResultByPstmt(qry, paramList);
		    	}
		    	catch(Exception e )
		    	{
		    		log.info("Exception raised in getPanchayatNameByDistIdMandalId"+e.getLocalizedMessage());
		    	}
	    	   
				
				return PanchayatName;
			}
			public String getHabitatNameByDistIdMandalIdPanchIdVillageIdHabitateId(String distId,String mandalId,String panchId, String villageId, String habitateId)
			{
				String habitateName="";
				ArrayList paramList = new ArrayList();
	    		ArrayList tempList	= new ArrayList();
				String qry="";
		    	try
		    	{
		    	   
		    		 qry = "select Habitation_Name from tblHabitation_Details with(nolock) where District_ID=? and Mandal_ID=? and Panchayat_ID=? and Village_ID=? and Habitation_ID=?";
		    	   
		    		 tempList.add("S");
		    		 tempList.add(distId);
		    		 paramList.add(tempList);
		    		 
		    		 tempList = new ArrayList();
		    		 tempList.add("S");
		    		 tempList.add(mandalId);
		    		 paramList.add(tempList);
		    		 
		    		 tempList = new ArrayList();
		    		 tempList.add("S");
		    		 tempList.add(panchId);
		    		 paramList.add(tempList);
		    		 
		    		 tempList = new ArrayList();
		    		 tempList.add("S");
		    		 tempList.add(villageId);
		    		 paramList.add(tempList);
		    		 
		    		 tempList = new ArrayList();
		    		 tempList.add("S");
		    		 tempList.add(habitateId);
		    		 paramList.add(tempList);
		    		 
		    		 habitateName = DataAccess.getReturnResultByPstmt(qry, paramList);
		    	}
		    	catch(Exception e )
		    	{
		    		log.info("Exception raised in getHabitatNameByDistIdMandalIdPanchIdVillageId"+e.getLocalizedMessage());
		    	}
	    	   
				
				return habitateName;
			}
			public String getPartBStatusRemarks(String SadaremID)
			{
				String Remarks=""; 
				String status = null;
				String category = null;
		    	try
		    	{   	
		    		
		    		ArrayList paramList = new ArrayList ();
		    		ArrayList tempList = new ArrayList();
		    		
		    		 tempList = new ArrayList();
		    		 tempList.add("S");
		    		 tempList.add(SadaremID);
		    		 paramList.add(tempList);
		    		 
		    		 tempList = DataAccess.pickDataByPrepareStmt("select status,categoryId from AppellateAuthorityandTemporary_RegistrationDetails(nolock) where person_code=? and vieweditmode='Edit' ", paramList, false, false); 		 
		    		
		    	 // System.out.println("CommonDAOImpl status : "+tempList);
		    		
		    		if(tempList!=null && tempList.size()==1)
		    		{ 
		    			status = (((ArrayList)tempList.get(0)).get(0)).toString();
		    			category = (((ArrayList)tempList.get(0)).get(1)).toString();			
		    		}
		    		
		    		if(status!=null && category!=null )
		    		{
		    			if(category.length()>0 && category.trim().equals("2"))
		    			{
		    				if(status.length()>0 )
		    				{
		    					if(status.equals("Active"))
			    				{
		    						Remarks = "Please fill Part-B Reassessment in Internet Explorer ";			    					
			    				}
		    					else if(status.equals("Inactive"))
			    				{
		    						Remarks = "Please Schedule Coresponding Village in CAMP-Incharge login to fill Part-B ";
			    				}
		    					
		    				}
		    			}
		    			else if(category.length()>0 && category.trim().equals("3"))
		    			{
		    				if(status.length()>0 )
		    				{
		    					if(status.equals("Active"))
			    				{
		    						Remarks = "Please fill Part-B under Temporary Assessment in Internet Explorer ";			    					
			    				}
		    					else if(status.equals("Inactive"))
			    				{
		    						Remarks = "Please Schedule Coresponding Village in CAMP-Incharge login to fill Part-B ";
			    				}		    					
		    				}
		    			}
		    		}		    	   
		    	}
		    	catch(Exception e )
		    	{
		    		e.printStackTrace();
		    		log.info("Exception raised in getPartBStatusRemarks"+e.getLocalizedMessage());
		    	}				
				return Remarks;
			}
			public String getvillageNameByDistIdMandalIdVillageId(String distId,String mandalId ,String villageId)
			{
				String villageName="";
				ArrayList paramList = new ArrayList();
	    		ArrayList tempList	= new ArrayList();
				String qry="";
		    	try
		    	{
		    	   
		    		 qry = "select Village_Name from tblVillage_Details with(nolock) where District_ID=? and Mandal_ID=? and Village_ID=?";
		    	   
		    		 tempList.add("S");
		    		 tempList.add(distId);
		    		 paramList.add(tempList);
		    		 
		    		 tempList = new ArrayList();
		    		 tempList.add("S");
		    		 tempList.add(mandalId);
		    		 paramList.add(tempList);
		    		 
		    		 
		    		 tempList = new ArrayList();
		    		 tempList.add("S");
		    		 tempList.add(villageId);
		    		 paramList.add(tempList);
		    		 
		    		 villageName = DataAccess.getReturnResultByPstmt(qry, paramList);
		    	}
		    	catch(Exception e )
		    	{
		    		log.info("Exception raised in getHabitatNameByDistIdMandalIdPanchIdVillageId"+e.getLocalizedMessage());
		    	}				
				return villageName;
			}
			
			public ArrayList insertPersonalDetails(HashMap partAdetails) // Subbu to be modified
			{
				ArrayList resultset=new ArrayList();
		        CallableStatement calstmt = null;
		        Statement st = null;
		        ResultSet rs = null;
		        Connection con = null;
		        String personcodemax = null;
		        String resultMSG=null;
		        String formDate= (String) partAdetails.get("formDate");
		        String formno=(String) partAdetails.get("formno" );
		        String districtid = (String) partAdetails.get("district_id");
		        String mandalid = (String) partAdetails.get("mandal_id");
		        String panchayatid = (String) partAdetails.get("panchayat_id");
		        String villageid = (String) partAdetails.get("village_id");
		        String habitationid = (String) partAdetails.get("habitation_id");
		        String assemblyid = "";//partAdetails.get();
		        String PinCode=(String)partAdetails.get("PinCode" );
		        String contid=(String)partAdetails.get("contid" );
		        String email=(String)partAdetails.get("email" );
		        String houseNumber=(String)partAdetails.get("houseNumber" );
		        String surName=(String)partAdetails.get("surName" );
		        String firstName=(String)partAdetails.get("firstName" );
		        String surnameenglish=(String)partAdetails.get("surnameenglish" );
		        String telugupersonname=(String)partAdetails.get("telugupersonname" );
		        String dob=(String)partAdetails.get("dob" );
		        String 		        gender=(String)partAdetails.get("gender" );
		        String 		        education=(String)partAdetails.get("education" );
		        String 		        employment=(String)partAdetails.get("employment" );
		        String 		        marital=(String)partAdetails.get("marital" );
		        String 		        caste=(String)partAdetails.get("caste" );
		        String 		        religion=(String)partAdetails.get("religion" );
		        String 		        RationcardNo=(String)partAdetails.get("Rcard" );
		        String 		        rtype=(String)partAdetails.get("rtype" );
		        String 		        rationCardSlno=(String)partAdetails.get("rationCardSlno" );
		        String 		        epiccardno=(String)partAdetails.get("epiccardno" );
		        String 		        pensioncardno=(String)partAdetails.get("pensioncardno" );
		        String 		        mole1=(String)partAdetails.get("mole1" );
		        String 		        mole2=(String)partAdetails.get("mole2" );
		        String 		        parents_marriage=(String)partAdetails.get("parents_marriage" );
		        String 		        relationName=(String)partAdetails.get("relationName" );
		        String 		        relationType=(String)partAdetails.get("relationType" );
		        String 		        telugufathername=(String)partAdetails.get("telugufathername" );
		        String 		        aadharCardNo=(String)partAdetails.get("aadharCardNo" );
		        //String 		        personcodemax=(String)partAdetails.get("personcodemax" );		        
		        String 		        loginid=(String)partAdetails.get("loginid" );		        
		        String 		        Systemip=(String)partAdetails.get("Systemip" );
		        String 		        personcode=(String)partAdetails.get("personcode" );
		        //String 		        status=(String)partAdetails.get("status" );
		        int campId=(Integer) partAdetails.get("campId");
		        
		        String reasonForStatus = null;
		        int partAEnteredStatus = 0;
		        String habCode = null;
		        String campdate = null;
		        PreparedStatement ps = null;
		        boolean success = true;
		        Statement st1 = null;
		        String reqIdForPartA =null;
		        try 
		        {


		            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(formDate);
		            String formdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
		            Date ddate = new SimpleDateFormat("dd/mm/yyyy").parse(dob);
		            String dobdate = new SimpleDateFormat("mm/dd/yyyy").format(ddate);
		            
		            reqIdForPartA=getRequestIdForPartADeowitoutProof();


		                /** Check Habcode base on selecting territorydetails */
		            habCode = this.checkHabitation( districtid, mandalid, panchayatid, villageid, habitationid);
		                
		            ArrayList CheckRecordExist=getSADAREMIDByAadhaarID(aadharCardNo);
		            
		            if(CheckRecordExist!=null && CheckRecordExist.size()==0)
		            {
		                if (habCode != null) 
		                {
		                    personcodemax = getHabitationCode(districtid, mandalid, panchayatid, assemblyid, villageid, habitationid);
		                    con = DBConnection.getConnection();
		                    con.setAutoCommit(false);
		                    
		                    if (habCode.equals(personcodemax.substring(0, 14)) || (districtid+mandalid+villageid).equals(personcodemax.substring(0,7)))
		                    {

		                        calstmt = con.prepareCall("{Call PRO_PartA_Without_Proof_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

		                        calstmt.setString(1, null);
		                        calstmt.setString(2, formno);
		                        calstmt.setString(3, formdate);
		                        calstmt.setString(4, convertFirstLetterToUpperCase(surName));
		                        calstmt.setString(5, convertFirstLetterToUpperCase(firstName));
		                        calstmt.setString(6, gender);
		                        calstmt.setString(7, dobdate);
		                        //if (partadto.getNoOfYears() != null && partadto.getNoOfYears().length() > 0 && !partadto.getNoOfYears().equals(null))
		                        {
		                            calstmt.setString(8, null);
		                        }
		                        calstmt.setString(9, religion);
		                        calstmt.setString(10, caste);
		                        calstmt.setString(11, marital);
		                        calstmt.setString(12, education);
		                        calstmt.setString(13, employment);
		                        calstmt.setString(14, relationType);
		                        calstmt.setString(15, relationName);
		                        calstmt.setString(16, rtype);
		                        calstmt.setString(17, RationcardNo);
		                        if(!epiccardno.equals(""))
		                        {
		                        	calstmt.setBoolean(18, true);
		                        }
		                        else
		                        {
		                        	calstmt.setBoolean(18, false);
		                        }
		                        
		                        calstmt.setString(19, epiccardno);
		                        calstmt.setBoolean(20, true);
		                        calstmt.setString(21, "Disabled");
		                        calstmt.setString(22, "ADPIII");
		                        calstmt.setString(23, convertFirstLetterToUpperCase(mole1));
		                        calstmt.setString(24, convertFirstLetterToUpperCase(mole2));
		                        calstmt.setString(25, houseNumber);
		                        calstmt.setString(26, contid);
		                        calstmt.setString(27, email);
		                        calstmt.setString(28, districtid);
		                        calstmt.setString(29, mandalid);
		                        calstmt.setString(30, villageid);
		                        calstmt.setString(31, habitationid);
		                        calstmt.setString(32, PinCode);
		                        calstmt.setString(33, telugupersonname);
		                        calstmt.setString(34, telugufathername);
		                        calstmt.setString(35, "Eligible");
		                        calstmt.setString(36, parents_marriage);
		                        calstmt.setString(37, null);
		                        calstmt.setString(38, null);
		                        calstmt.setString(39, loginid);
		                        calstmt.setString(40, Systemip);
		                        calstmt.setInt(41, campId);
		                        calstmt.setString(42, "PhaseIII");
		                        calstmt.setString(43, "Live");
		                        calstmt.setString(44, habCode);
		                        calstmt.setInt(45, 1);
		                        calstmt.setInt(46, 1);
		                        calstmt.setString(47, rationCardSlno);
		                        calstmt.setString(48, aadharCardNo);
		                        calstmt.setString(49, "P");
		                        calstmt.setString(50, reqIdForPartA);
		                        calstmt.setString(51, panchayatid);

		                        partAEnteredStatus = calstmt.executeUpdate();		                        
					    		
					    		 if (partAEnteredStatus != 0) 
			                     {
					    			 partAEnteredStatus=0;					    			 
					    			 partAdetails.put("reqId",reqIdForPartA);
					    			 partAEnteredStatus = insertProofDtlsPartAWithoutProofDEO(partAdetails,con);
			                     }
			                          
								
		                        
		                        if (partAEnteredStatus != 0) 
		                        {
		                            //transactionService.insertTransactionalDetails(ds, "PartA Details Entered", personcodemax, request);
		                        	if(issueTrackingCommonUtil.createDirectory(CommonConstants.PARTA_ATTACHMENT_PATH+reqIdForPartA)==true) 
									{								
											  if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)partAdetails.get("photoDoc"),CommonConstants.PARTA_ATTACHMENT_PATH+reqIdForPartA+"/"+aadharCardNo+".jpg")==true)
											  {
												  if(issueTrackingCommonUtil.createDirectory(CommonConstants.PARTA_ATTACHMENT_PATH+reqIdForPartA)==true)
													 {
														 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)partAdetails.get("fileDoc1"),CommonConstants.PARTA_ATTACHMENT_PATH+reqIdForPartA+"/"+(String)partAdetails.get("filePath1")+".pdf")==true)
														 {
															 if((Long)partAdetails.get("fileSize2")!=0)
															 {
																 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)partAdetails.get("fileDoc2"),CommonConstants.PARTA_ATTACHMENT_PATH+reqIdForPartA+"/"+(String)partAdetails.get("filePath2")+".pdf")==true)
																 {
																	 resultMSG="Success";																	 
																	 con.commit();
																	 resultset=getPartABasicDtls(reqIdForPartA,aadharCardNo);
																	 //System.out.println("-------"+partAWithoutProofCAReject( aadharCardNo,  reqIdForPartA,  "R"));
																 }
																 else
																 {
																	  con.rollback();
																	  resultMSG="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
																 }
															 }
															 else
															 {
																 resultMSG="Success";
																 con.commit();
																 resultset=getPartABasicDtls(reqIdForPartA,aadharCardNo);
															 }
															 
														 }
														 else
														 {
															 con.rollback();
															 resultMSG="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
														 }
													 }
													 else
													 {
														 con.rollback();
														 resultMSG="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
													 }
											 }
											 else
											 {
												 con.rollback();
												 resultMSG="<font color='red'>07.Sorry we cannot process your request.Please try later !!</font>";
											 }								
								  }
								 else
								 {
										  con.rollback();
										  resultMSG="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
								 }
		                      }
		                    } 
		                    else 
		                    {
		                    	resultMSG = "Not Matching with the given Habitation details. <br> Please select other territory details."; 
		                    }
		                } 
		                else
		                {
		                	resultMSG = "Not Matching with the given Habitation details. <br> Please select other territory details.";
		                }
		            }
		            else
		            {
		            	resultMSG="Don't Re-Load the Page<br>(OR)<br>Part-A request already raised and pending at Camp-Admin";
		            }
		        } 
		        catch (Exception exception) 
		        {
		             exception.printStackTrace();
		             resultMSG="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
		        	try
		        	{
		        		con.rollback();
		        		con.close();
		        	}
		        	catch(Exception e)
		        	{
		        		e.printStackTrace();
		        	}

		        }
		        finally
		        {
		        	DBConnection.closeConnection(con); 
		        	resultset.add(resultMSG);
		        }
		        deletePartAWithoutProofDirofProof(reqIdForPartA);
		        
		        return resultset;
		    }
			public String checkHabitation( String districtId, String mandalId, String panchayatId, String villageId, String habitationId) 
			{
		        String habCode = ""; 
		        try 
		        { 
		         ArrayList paramList = new ArrayList();
		         ArrayList tempList  = new ArrayList();
		         
		            lStrQuery = 
		            		" SELECT habitation_code FROM tblhabitation_details \n"+
            				"  WHERE  district_id=? AND mandal_id=? AND  \n"+
            				" panchayat_id=? AND village_id=? AND habitation_id=? ";
		        	
		            tempList  = new ArrayList();
		            tempList.add("S");
		            tempList.add(districtId);
		            paramList.add(tempList);
		            
		            tempList  = new ArrayList();
		            tempList.add("S");
		            tempList.add(mandalId);
		            paramList.add(tempList);
		             		            
		            tempList  = new ArrayList();
		            tempList.add("S");
		            tempList.add(panchayatId);
		            paramList.add(tempList);
		            
		            tempList  = new ArrayList();
		            tempList.add("S");
		            tempList.add(villageId);
		            paramList.add(tempList);
		            
		            tempList  = new ArrayList();
		            tempList.add("S");
		            tempList.add(habitationId);
		            paramList.add(tempList);
		            
		            habCode =DataAccess.getReturnResultByPstmt(lStrQuery, paramList);

		        }
		        catch (Exception e)
		        {
		        	e.printStackTrace();
		        	log.error(e);
		        } 
		         
		        return habCode;
		    }
			
			public String getHabitationCode(String districtid, String mandalid, String panchayatid, String assemblyid, String villageid, String habitationid)
			{
		        Connection con = null;
		        Statement stmt1 = null;
		        Statement stmt2 = null;
		        ResultSet rs1 = null;
		        ResultSet rs2 = null;
		        String habitationUpdatedCode = null;
		        String habitationCode = null;
		        String habitationMaxCode = null;
		        PreparedStatement st = null;
		        try {
		            con = DBConnection.getConnection();
		            	
		            CommonDAO comObj = new CommonDAOImpl();
		         
		    String getHabitationCodeQuery ="select habitation_code from tblHabitation_Details with(nolock) where district_id=? " +
		                     " and mandal_id=? and Panchayat_ID=? " +
		                     "  and village_id=? and habitation_id=?";
		    
		    st = con.prepareStatement(getHabitationCodeQuery);

		         
            st.setString(1, districtid);
            st.setString(2, mandalid);
            st.setString(3, panchayatid);
            st.setString(4, villageid);
            st.setString(5, habitationid);
	            
            rs1 = st.executeQuery();
      
		    
		    
		    
		    
		          //  stmt1 = con.createStatement();

		          //  rs1 = stmt1.executeQuery(getHabitationCodeQuery);
		            while (rs1.next()) 
		            {
		                habitationCode = rs1.getString(1);
		            }
		            // String getMaxHabitationCodeQuery = "select Person_Code from tblPerson_Personal_Details  group by Person_Code having max(cast(Person_Code as bigint))=(select max(cast(Person_Code as bigint)) from tblPerson_Personal_Details where District_Id='" + districtid + "' and Mandal_ID='" + mandalid + "' and Village_ID='" + villageid + "' and Habitation_ID='" + habitationid + "')";

		            String getMaxHabitationCodeQuery = "select max(Person_Code) from tblPerson_Personal_Details_new   with (nolock) where substring(person_code,1,14) = ? ";
		            
		            st = con.prepareStatement(getMaxHabitationCodeQuery);
		            st.setString(1, habitationCode);
				            
		            rs2 = st.executeQuery();
		          //  stmt2 = con.createStatement();
		            rs2 = st.executeQuery();
		            
		            while (rs2.next()) 
		            {
		                habitationMaxCode = rs2.getString(1);
		            }
		                   
		            if (habitationMaxCode != null) 
		            {

		                long longCode = Long.parseLong(habitationMaxCode);
		                
		                //System.out.println("habitationMaxCode : "+habitationMaxCode+"===="+habitationMaxCode.substring(habitationMaxCode.length()-3,habitationMaxCode.length()));

		                if (habitationMaxCode.length() == 18 || habitationMaxCode.substring((habitationMaxCode.length() - 3), (habitationMaxCode.length())).equals("999"))
		                {
		                	habitationUpdatedCode = comObj.getNewSADAREMID(districtid, mandalid, villageid);
		                } 
		                else
		                {
		                    if (habitationMaxCode.startsWith("0"))
		                    {
		                        String last3Digits = habitationMaxCode.substring((habitationMaxCode.length() - 3), (habitationMaxCode.length()));
		                        if (last3Digits.equals("999")) 
		                        {
		                            StringBuffer strbuf = new StringBuffer(habitationCode);
		                            habitationUpdatedCode = strbuf.append("1000").toString();
		                        }
		                        else 
		                        {
		                            longCode++;
		                            String withZero = String.valueOf(longCode);
		                            habitationUpdatedCode = paddingString(withZero, withZero.length() + 1, '0');
		                        }
		                    } 
		                    else 
		                    {
		                        String last3Digits = (String.valueOf(longCode)).substring(String.valueOf(longCode).length() - 3, (String.valueOf(longCode).length()));
		                        if (last3Digits.equals("999"))
		                        {
		                            StringBuffer strbuf = new StringBuffer(habitationCode);
		                            habitationUpdatedCode = strbuf.append("1000").toString();
		                        } 
		                        else
		                        {
		                            longCode++;
		                            habitationUpdatedCode = String.valueOf(longCode);
		                        }
		                    }
		                }
		            } 
		            else
		            {
		                StringBuffer strbuf = new StringBuffer(habitationCode);
		                habitationUpdatedCode = strbuf.append("001").toString();
		            }

		        } 
		        
		        catch (Exception sqlEx) 
		        {
		        	sqlEx.printStackTrace();
		        	DBConnection.closeConnection(con);
		        }
		        finally
		        {
		        	DBConnection.closeConnection(con);
		        }
		        
		        return habitationUpdatedCode;
		    }
			public String convertFirstLetterToUpperCase(String inputString)
			{
		        if (inputString.equals(""))
		        {
		            return inputString;
		        }
		        else
		        {
		            StringBuffer inputStrBuffer = new StringBuffer(inputString);
		            String firstLetterUpperCase = inputStrBuffer.substring(0, 1).toUpperCase();
		            inputStrBuffer.replace(0, 1, firstLetterUpperCase);
		            return inputStrBuffer.toString();
		        }

		    }
			public String paddingString(String s, int n, char c)
			{
		        StringBuffer str = new StringBuffer(s);
		        int strLength = str.length();
		        if (n > 0 && n > strLength)
		        {
		            for (int i = 0; i <= n; i++)
		            {
		                if (i < n - strLength)
		                {
		                    str.insert(0, c);
		                }
		            }
		        }
		        return str.toString();
		    }
			public int insertProofDtlsPartAWithoutProofDEO(HashMap PartADEOProofInsertdtls,Connection lcon)
			{
				String status="";
				PreparedStatement lPstmt = null;
				int result =0;
				String reqId=(String)PartADEOProofInsertdtls.get("reqId");
				String filePath1=reqId+(String)PartADEOProofInsertdtls.get("filePath1");
				
				try
				{
					lStrQuery =  "insert into  sadarem_without_proof_doc_details\n"+
			              "(request_id,proof_type_id,proof_number,proof_doc_path,proof_doc_type,is_active,created_by,created_date)\n"+
			              "values \n"+
			               "(?,?,?,?,?,?,?,getdate())";
			              
			               if(!PartADEOProofInsertdtls.get("proofTypeID2").equals("-1"))
			               {
			            	   lStrQuery +=" , (?,?,?,?,?,?,?,getdate())";
			               }
					
				
				    lPstmt = lcon.prepareStatement(lStrQuery);
				    lPstmt.setString(1,(String)PartADEOProofInsertdtls.get("reqId") );
				    lPstmt.setString(2,(String)PartADEOProofInsertdtls.get("proofTypeID1") );
				    lPstmt.setString(3,(String)PartADEOProofInsertdtls.get("proofId1") );
				    lPstmt.setString(4,reqId+(String)PartADEOProofInsertdtls.get("filePath1")+"."+(String)PartADEOProofInsertdtls.get("docType1") );
				    lPstmt.setString(5,(String)PartADEOProofInsertdtls.get("docType1") );
				    lPstmt.setString(6,"Y");
				    lPstmt.setString(7,(String)PartADEOProofInsertdtls.get("loginid") );
				    
				    if(!PartADEOProofInsertdtls.get("proofTypeID2").equals("-1"))
		            {
				    	lPstmt.setString(8,(String)PartADEOProofInsertdtls.get("reqId") );
					    lPstmt.setString(9,(String)PartADEOProofInsertdtls.get("proofTypeID2") );
					    lPstmt.setString(10,(String)PartADEOProofInsertdtls.get("proofId2") );
					    lPstmt.setString(11,reqId+(String)PartADEOProofInsertdtls.get("filePath2")+"."+(String)PartADEOProofInsertdtls.get("docType2")  );
					    lPstmt.setString(12,(String)PartADEOProofInsertdtls.get("docType2") );
					    lPstmt.setString(13,"Y");
					    lPstmt.setString(14,(String)PartADEOProofInsertdtls.get("loginid") );
		            }
				    
				    
				  	
				    result = lPstmt.executeUpdate();
				  
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					try
					{
						lPstmt.close();
					}
					catch (SQLException e) 
					{
					   log.info("Error occurred in insertProofDtls"+e);
						e.printStackTrace();
					}
				}
				
				return result;
			}
			
			public String getRequestIdForPartADeowitoutProof()
			{
				String RequestId="";
		    	try
		    	{
		    		ArrayList paramList = new ArrayList();
		    		ArrayList tempList	= new ArrayList();
		    		
		    		lStrQuery=  
		    				"SELECT (CONVERT(VARCHAR(10), getdate() ,12))+RIGHT(REPLICATE('0', 4) + CAST(( COUNT(*)+1) AS VARCHAR(4)), 4)\n" +
		    						" FROM sadarem_without_proof_request_master with(nolock)\n" + 
		    						" WHERE\n" + 
		    						" (CONVERT(VARCHAR(10), created_date ,12))=(CONVERT(VARCHAR(10), getdate() ,12)) \n";


		    		
				  	//log.info("get lStrQuery : "+lStrQuery+"\n ParamList : "+paramList);
				  	
				  	RequestId=DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
		    		
		    	
		    	}    
				 catch(SQLException sqle)
				 {    
					 log.info("SQLException in CommonDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
				 }
				 catch(Exception lexp)
				 {
					 log.info("Exception in CommonDAOImpl : "+lexp.getLocalizedMessage());
				 }
		    	
		    	return RequestId;	
			}
			public ArrayList getPartABasicDtls(String reqIdForPartA,String aadharCardNo)
			{
				String qry="";
				ArrayList paramList = new ArrayList();
	    		ArrayList tempList	= new ArrayList();
				try
				{
					qry="SELECT  s.request_id, s.sadarem_id,s.Reference_Form_Number,s.Surname,s.First_Name,s.Relation_Name,s.Personname_Telugu, \n"
							+ "s.Relationname_Telugu,s.Date_of_Birth,s.Mole_One,s.mole_two,s.created_date,s.Proof_Id,s.Proofdoc_Type, \n"
							+ "case  s.Gender_ID when '1' then 'Male' when '2' then 'Female' end 'Gender', d.District_Name, m.Mandal_Name, \n"
							+ "v.Village_Name, edu.edu_desc, emp.employement_type_desc, s.phone_no, \n"
							+ "camp.Name+' '+camp.Address as 'Camp dtls',marital.marital_status_desc, s.created_by ,rel.relation_name\n"
							+ "from sadarem_without_proof_request_master s with(nolock) \n"
							+ "left join  tblDistrict_Details d with(nolock) on d.District_ID=s.District_ID \n"
							+ "left join  tblmandal_Details m with(nolock) on m.Mandal_ID=s.Mandal_ID and m.District_ID=s.District_ID \n"
							+ "left join  tblvillage_Details v with(nolock) on v.District_ID=s.District_ID and v.Mandal_ID=s.Mandal_ID and v.Village_ID=s.Village_ID \n"
							+ "left join  sadarem_education_master edu with(nolock) on edu.edu_id=s.Education_ID "
							+ "left join  sadarem_employement_type_master emp with(nolock) on emp.employement_type_id=s.Employment_ID \n"
							+ "left join  tblcamp_details camp with(nolock) on camp.Camp_ID=s.Camp_ID and camp.District_ID=s.District_ID \n "
							+ "left join  sadarem_marital_status_master marital with(nolock) on marital.marital_status_id=s.Marital_Status_ID \n"
							+ "left join  sadarem_gender_relation_type_mapping rel with(nolock) on s.relationship_id=rel.relation_id and rel.gender_id=s.gender_id \n"
							+ "where s.Proof_Id=? and s.request_id=? \n";
					
					tempList.add("S");
					tempList.add(aadharCardNo);					
					paramList.add(tempList);
					
					tempList = new ArrayList();	
					tempList.add("S");
					tempList.add(reqIdForPartA);					
					paramList.add(tempList);
					
					
					
					tempList = new ArrayList();
					tempList = DataAccess.pickDataByPrepareStmt(qry, paramList, false, false);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
						
				return tempList;
			}
			public ArrayList getRelationDetails(String GenderId)
			{
				String qry="";
				ArrayList paramList = new ArrayList();
	    		ArrayList tempList	= new ArrayList();
				try
				{
					qry="SELECT  relation_id,relation_name\n"							
							+ "from sadarem_gender_relation_type_mapping with(nolock)  \n"
							+ "where gender_id=? order by relation_id\n";
					
					tempList.add("S");
					tempList.add(GenderId);					
					paramList.add(tempList);				
					
					tempList = new ArrayList();
					tempList = DataAccess.pickDataByPrepareStmt(qry, paramList, false, false);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
						
				return tempList;
			}
			public ArrayList getMaritalList()
			{
				String qry="";
				ArrayList paramList = new ArrayList();
	    		ArrayList tempList	= new ArrayList();
				try
				{
					qry="SELECT  marital_status_id,marital_status_desc\n"							
							+ "from sadarem_marital_status_master with(nolock) order by marital_status_id";					
					
					tempList = DataAccess.pickDataByPrepareStmt(qry, paramList, false, false);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
						
				return tempList;
			}
			
			public HashMap getGEODetailsbySADAREMID(String sadaremID)
			{
				ArrayList resulSet = new ArrayList();
				HashMap result = new HashMap();
		    	try
		    	{
		    		//System.out.println("called");
		    		ArrayList paramList = new ArrayList();
		    		ArrayList tempList	= new ArrayList(); 
		    		
		    		lStrQuery=  "SELECT TOP 1  districtid, mandalid, villageid,habtationid ,HabCode,mydate FROM "
		    						+ "		( SELECT person_code,district_id districtid,mandal_id mandalid,village_id villageid,habitation_id habtationid ,HabCode ,Created_Date mydate  FROM tblperson_personal_details WITH(NOLOCK)  UNION ALL SELECT person_code,district_id districtid,mandal_id mandalid,village_id villageid,habitation_id habtationid ,HabCode ,DATEADD (YEAR , -1 , CONVERT(date,'01-01-2009',103) )  mydate  FROM tblperson_personal_details_new(nolock)  )a  "
		    						+ " where a.person_code=?   ORDER BY mydate DESC ";
		    		
		    		tempList.add("S");
		    		tempList.add(sadaremID);
		    		paramList.add(tempList);
		    		
				  	log.info("get lStrQuery : "+lStrQuery+"\n ParamList : ");
				  	
				  //	System.out.println("lStrQuery  "+lStrQuery);
				  	
				  	resulSet=DataAccess.pickDataByPrepareStmt(lStrQuery,paramList, true, false);
				  	
				  	//System.out.println("resulSet-->"+resulSet);
				  	
				  	result=	CommonUtility.getHashMapfromArrayListWithcolumnName(resulSet);
		    		 
		    	
		    	}    
				 catch(SQLException sqle)
				 {    
					 log.info("SQLException in CommonDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
				 }
				 catch(Exception lexp)
				 {
					 log.info("Exception in CommonDAOImpl : "+lexp.getLocalizedMessage());
				 }
		    	
		    	return result;	
			} 
			public ArrayList getLoginUrlList(int roleId)
			{
				String qry="";
				ArrayList paramList = new ArrayList();
	    		ArrayList tempList	= new ArrayList();
				try
				{
					qry="select target_url from services s with(nolock),roles_services r with(nolock) where s.service_id=r.service_id and role_id=? and r.is_active='Y' and target_url IS NOT NULL and target_url!=''";
					
					tempList.add("I");
					tempList.add(roleId);					
					paramList.add(tempList);				
					
					tempList = new ArrayList();
					tempList = DataAccess.pickDataByPrepareStmt(qry, paramList, false, false);
					
					paramList=new ArrayList();
					
					//System.out.println("vi"+tempList);
					
					if(tempList.size()>0)
					{
					//paramList=(ArrayList)tempList.get(0);
					//System.out.println("==>"+paramList);
					
					for(int i=0;i<tempList.size();i++)
					{
						
						if(tempList.get(i)==null || tempList.get(i)=="" || tempList.get(i)==" ")
						{
							tempList.remove(i);
						}
						
					}
					
					}
					//System.out.println("-->"+tempList);
					
					
				
				
				}catch(Exception e)
				{
					e.printStackTrace();
				}
						
				return tempList;
				
			}
		
			
			 public  ArrayList getAllMenuUrlList()
				{
				  ArrayList menuList=new ArrayList();
				  PreparedStatement logchk=null;
				  try
				  {
		  			lcon = DBConnection.getConnection();
		  			
				  	String lStrQuery=  

							"SELECT LTRIM(RTRIM(target_url)) FROM services with(NOLOCK) WHERE is_active='Y' and target_url IS NOT NULL";

				  	//System.out.println("lStrQuery : "+lStrQuery);
				  	 logchk	=	lcon.prepareStatement(lStrQuery);
				  	  
				   
				  	 		ResultSet lrs=logchk.executeQuery();
				     
						     while(lrs.next())
					          {
						    	 if(lrs.getString(1)!=null && ((lrs.getString(1)).length())>0)
						    	 {
						    		 menuList.add(lrs.getString(1).trim());
						    	 }
						    	
					          }

				  	
					}    
				   catch(SQLException sqle)
				   {
				       log.info("SQLException in CommonDAOImpl @ getMenuUrlList :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
				      sqle.printStackTrace();
				    }
				   catch(Exception lexp)
				   {
				  	 log.info("Exception in CommonDAOImpl @ getMenuUrlList :: "+lexp.getLocalizedMessage());
				   }
				   finally 
				   {
					     if(logchk!=null) 
					     {
					         DBConnection.closeStatement(logchk);
					         logchk=null;
					     }
					     if(lcon!=null) 
					     {
					         DBConnection.closeConnection(lcon);
					         lcon=null;
					     }
				   }
				      return menuList;
				}
			 
			 public String getHabitatName(String distId,String mandalId,String villageId, String habitateId)
				{
					String habitateName="";
					ArrayList paramList = new ArrayList();
		    		ArrayList tempList	= new ArrayList();
					String qry="";
			    	try
			    	{ 			    	   
			    		 qry = "select Habitation_Name from tblHabitation_Details with(nolock) where District_ID=? and Mandal_ID=? and  Village_ID=? and Habitation_ID=?";
			    	   
			    		 tempList.add("S");
			    		 tempList.add(distId);
			    		 paramList.add(tempList);
			    		 
			    		 tempList = new ArrayList();
			    		 tempList.add("S");
			    		 tempList.add(mandalId);
			    		 paramList.add(tempList);
			    		  
			    		 
			    		 tempList = new ArrayList();
			    		 tempList.add("S");
			    		 tempList.add(villageId);
			    		 paramList.add(tempList);
			    		 
			    		 tempList = new ArrayList();
			    		 tempList.add("S");
			    		 tempList.add(habitateId);
			    		 paramList.add(tempList);
			    		 
			    		 habitateName = DataAccess.getReturnResultByPstmt(qry, paramList);
			    	}
			    	catch(Exception e )
			    	{
			    		log.info("Exception raised in getHabitatName"+e.getLocalizedMessage());
			    	}
		    	   
					
					return habitateName;
				}			 
			 
			 public String getDisabilityName(String disableId)
				{
				 String habitationIdGen = null;
			        String assemblyIdGen = null;
			        Connection con = null;
			        ResultSet habcodeRS = null;
			        ResultSet assemblyRS = null;
			        String habcodeSQL = null;
			        String assemblySQL = null;  
			        PreparedStatement st = null;
			        try 
			        {
			            con = DBConnection.getConnection();
			         //   st = con.createStatement();

			            assemblySQL ="select disability_name from dbo.tblDisability_Details with(nolock) where disability_id=?";
			            
			            st = con.prepareStatement(assemblySQL);
			            st.setString(1, disableId);  
			           
				            
			            habcodeRS = st.executeQuery();
			            
			            if (habcodeRS != null) 
			            {
			                while (habcodeRS.next())
			                {
			                    habitationIdGen = habcodeRS.getString(1);
			                }
			            }

			        }
			        catch (Exception e)
			        {
			        	e.printStackTrace();
			            //int exResult = ExceptionDAO.saveException(ds, e.toString(), "checkHabitation", "PartADAO", "Code");           

			        } finally {
			            try {
			                DBConnection.closeConnection(con);
			                DBConnection.closeStatement(st);
			                DBConnection.closeResultSet(assemblyRS);
			                DBConnection.closeResultSet(habcodeRS);
			            } catch (Exception e) {
			                e.printStackTrace();
			            }
			        }
			        return habitationIdGen;
		}
			
	public ArrayList getAllRolesList()
 	{
 		ArrayList resultList = new ArrayList();
 		try
 		{
 			lStrQuery=
 					" SELECT DISTINCT r.role_id,r.role_name \n"+
					" FROM  \n"+
					"  roles r WITH(NOLOCK), \n"+
					"  login_details l WITH(NOLOCK)  \n"+
					" WHERE  \n"+
					" r.is_active='Y' AND  \n"+
					" r.role_id<>0 AND \n"+
					" r.role_id = l.role_id AND \n"+
					" l.is_active='Y' \n"+
					" ORDER BY role_name";

				log.info("getAllRolesList lStrQuery : "+lStrQuery);
  	
					resultList=(ArrayList)DataAccess.pickData(lStrQuery, false, false);
 		}
 		catch(Exception e)
 		{
 			e.printStackTrace();
 		}
 		
 		return resultList;
 	}
	public void exportExcel(ArrayList HeaderList ,ArrayList resultList ,HttpServletRequest request, HttpServletResponse response, String title)
	{

 		OutputStream out = null;

		 try 
		 {
			 String  currentdate = CommonUtility.getDateTime("dd/MM/yyyy_hh_mm_ss"); 
				//System.out.println("enter excel");
		     response.setContentType("application/pms.ms-excel");
		     response.setHeader("Content-Disposition", "attachment;filename="+title+"_"+currentdate+".xls");
		     WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
		     WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		     String  currentdate1 = CommonUtility.getDateTime("dd/MM/yyyy hh:mm:ss"); 
		     WritableCellFormat cell = new WritableCellFormat();
		     WritableCellFormat cellLeft = new WritableCellFormat();
		     cell.setBorder(Border.ALL, BorderLineStyle.THIN);
		     cell.setBorder(Border.ALL, BorderLineStyle.THIN);
		     cellLeft.setBorder(Border.ALL, BorderLineStyle.THIN);
		     cell.setAlignment(Alignment.CENTRE);
		     cellLeft.setAlignment(Alignment.LEFT);
		     cell.setVerticalAlignment(VerticalAlignment.CENTRE);
		
		     WritableCellFormat cell111 = new WritableCellFormat(bold);		   
		     
		     
		     WritableCellFormat cell12 = new WritableCellFormat(bold);
		     cell12.setBorder(Border.ALL, BorderLineStyle.THIN);
		     cell12.setBorder(Border.ALL, BorderLineStyle.THIN);
		     cell12.setAlignment(Alignment.CENTRE);
		     cell12.setVerticalAlignment(VerticalAlignment.CENTRE);
		     
		     
		     
		
		     WritableCellFormat cell13 = new WritableCellFormat(bold);
		     cell13.setBorder(Border.ALL, BorderLineStyle.THIN);
		     cell13.setBorder(Border.ALL, BorderLineStyle.THIN);
		     cell13.setAlignment(Alignment.LEFT);
		     cellLeft.setAlignment(Alignment.LEFT);
		     
		     WritableCellFormat integerstyle = new WritableCellFormat(NumberFormats.INTEGER);
		    //Number num1=new Number(2,1,2.121212,integerstyle);
		     integerstyle.setAlignment(Alignment.RIGHT);
		     integerstyle.setBorder(Border.ALL, BorderLineStyle.THIN);
		     integerstyle.setBorder(Border.ALL, BorderLineStyle.THIN);
		     int page = 1;
		     WritableSheet s = w.createSheet("Sheet" + page, page - 1);
		     if (resultList.size() == 0) 
		     {
		         s.addCell(new Label(0, 0, title));
		     } 
		     else 
		     {		         
		        s.mergeCells(0, 0, HeaderList.size(), 0);//Fromcol,fromrow,tocol,torow
		        s.addCell(new Label(0, 0, title, cell12));         
		     }
		     
		     s.addCell(new Label(0, 1,"S.No.",cell12));
			  for(int i=1;i<HeaderList.size()+1;i++)
			  {
					  s.addCell(new Label(i, 1,HeaderList.get(i-1).toString(),cell12));
					  //setWidth(s, 4, 30);
			  }
				    
		     ArrayList templist = new ArrayList();
		     ArrayList templist1 = new ArrayList();
		     for (int row = 2; row < resultList.size()+2; row++) 
		     {
		    	 templist = (ArrayList)resultList.get(row-2);
		    	 for(int col=0,fcol=0;col<HeaderList.size();col++,fcol++)
		    	 {
		    		 if(fcol==0)
		    		 {
		    			 s.addCell(new Label(0, row,row-1+"",cell13));
		    		 }
		    			 s.addCell(new Label(col+1, row,templist.get(col).toString(),cell13));
		    		 
		    	 }	         
		     }	     
		     w.write();
		     w.close();
		
		 } 
		 catch (Exception e)
		 {
		     e.printStackTrace();
		 } 
		 finally
		 {
		     if (out != null)
		     {
		         try 
		         {
		             out.close();
		         } 
		         catch (IOException ex) 
		         {
		             ex.printStackTrace();
		         }
		     }
		 }

	}
	public ArrayList getEmployeeInformation(String roleId, String distId, String campId, String mandalId)
	{

		ArrayList resultList = new ArrayList();
		
		try
		{
			ArrayList paramList = new ArrayList();
			ArrayList tempList = new ArrayList();
			lStrQuery =
					" SELECT user_name,user_id,\n" +
					" person_name,role_name,district_name,camp_name,mandal_name,\n" + 
					" role_id,district_id,camp_id,mandal_id,is_active,user_status,created_by,\n" + 
					" CONVERT(VARCHAR(20),created_date,113) created_date\n" + 
					" FROM SADAREM_VIEW_USER_LOGIN_DTLS\n" + 
					" WHERE user_id IS NOT NULL \n";
			
			
			
			if(roleId!=null && !roleId.equals("") && !roleId.equals("-1") && !roleId.equals("NA")  )
			{
				lStrQuery +=" AND role_id=? \n"; 

				tempList  = new ArrayList();
				tempList.add("S");
				tempList.add(roleId);
				paramList.add(tempList);
			}
			
			if(distId!=null && !distId.equals("") && !distId.equals("-1") && !distId.equals("NA"))
			{
				lStrQuery +=" AND district_id=? \n";  
				
				tempList  = new ArrayList();
				tempList.add("S");
				tempList.add(distId);
				paramList.add(tempList);
			}	
			
			if(campId!=null && !campId.equals("") && !campId.equals("-1") && !campId.equals("NA"))
			{
				lStrQuery +=" AND camp_id=? \n";  
				
				tempList  = new ArrayList();
				tempList.add("S");
				tempList.add(campId);
				paramList.add(tempList);
			}		
			
			if(mandalId!=null && !mandalId.equals("") && !mandalId.equals("-1") && !mandalId.equals("NA"))
			{
				lStrQuery +=" AND mandal_id=? \n";  
				
				tempList  = new ArrayList();
				tempList.add("S");
				tempList.add(mandalId);
				paramList.add(tempList);
			}		
						 
				resultList = CommonUtility.getHashMapElementsFromArraylistElements((ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, true, false));
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultList;
		
	
	}

	public HashMap getSADAREMBasicDetailsByID(String sadarem_id)
	{
		HashMap resultList = new HashMap();
		try
		{
			ArrayList paramList = new ArrayList();
			ArrayList tempList	= new ArrayList();
				lStrQuery = 
							"SELECT\n" +
							" sadarem_id,districtid,mandalid,villageid,habtationid,\n" + 
							" caste_id,edu_id,emp_type_id,marital_status_id,religion_id,\n" + 
							" district_name,mandal_name,village_name,habitation_name,\n" + 
							" person_surname,person_name,personname_telugu,person_dob,\n" + 
							" type_of_proof,proof_id,person_gender,rel_type,relation_name,\n" + 
							" relation_telugu_name,person_contactno,category_id,category_count,\n" + 
							" person_status,person_live_status,dis_form_status,caste_desc,\n" + 
							" education_desc,employement_type_desc,marital_status_desc,\n" + 
							" religion_desc,rec_modified_date,parta_entry_date,parta_campid,\n" + 
							" partb_campid,parta_campname,partb_campname,parents_marriage,\n" + 
							" is_active,disb_id,disability_name,disp_min_per,person_disp_percent,\n" + 
							" cause_of_disp,certificate_type,certificate_issue_date,temp_cert_expire_year,\n" + 
							" cert_expire_date,hospital_name,hospital_address,first_doctor_name,\n" + 
							" first_doctor_regnumber,first_doctor_designation,second_doctor_name,\n" + 
							" second_doctor_regnumber,second_doctor_designation,\n" + 
							" third_doctor_name,third_doctor_regnumber,third_doctor_designation\n" + 
							"FROM sadarem_view_complete_details WITH(NOLOCK)\n" + 
							" WHERE\n" + 
							" sadarem_id=?";
				
				  	tempList  = new ArrayList();
		            tempList.add("S");
		            tempList.add(sadarem_id);
		            paramList.add(tempList);
		            
			
			resultList = CommonUtility.getHashMapfromArrayListWithcolumnName(DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, true, false));

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultList;
	}
	

	public static void deletePartAWithoutProofDirofProof(String request_id)
	{
		try 
		{
		//	System.out.println("tkt_req_id in delete : "+tkt_req_id);
			if(request_id!=null && !request_id.equals("") && request_id.length()>0)
			{
				int mycount = Integer.parseInt(DataAccess.getReturnResult("SELECT COUNT(*) mycount FROM sadarem_without_proof_request_master WITH(NOLOCK) WHERE LTRIM(RTRIM(request_id))='"+request_id.trim().toUpperCase()+"'"));
				if(mycount==0)
				{
					issueTrackingCommonUtil.deleteDirectory(new File(CommonConstants.PARTA_ATTACHMENT_PATH+request_id));
				}
			}
		}
		catch(Exception e)
		{
			log.error(e);
			e.printStackTrace();
		}
	}
	
	
	
	public HashMap getSADAREMPartABasicDetailsByID(String sadarem_id)
	{
		HashMap requestList = new HashMap();
		try
		{
			ArrayList paramList = new ArrayList();
			ArrayList tempList  = new ArrayList();
			
			lStrQuery =  
					
					"SELECT sadarem_id,reference_form_number,district_id,mandal_id,\n" +
					" panchayat_id,village_id,habitation_id,habcode,gender_id,religion_id,\n" + 
					" caste_id,marital_status_id,education_id,employment_id,surname,\n" + 
					" first_name,personname_telugu,CONVERT(VARCHAR(10),date_of_birth,103) date_of_birth, \n"+
					" relationship_id, relation_name,relationname_telugu,mole_one,mole_two,house_number,\n" + 
					" pin_code,phone_no,email,parents_marriage,rationcard_type,rationcard_slno,\n" + 
					" rationcard_number,epic_card,epiccard_no,pension_card,pension_type,\n" + 
					" pensioncard_no,CONVERT(VARCHAR(10),form_fill_date,103) form_filled_date,\n"+
					" camp_id,proof_id,proofdoc_type,\n" + 
					" district_name,mandal_name,panchayat_name,village_name,habitation_name,\n" + 
					" gender_name,relationship_type_desc,religion_desc,caste_desc,\n" + 
					" marital_status_desc,edu_desc,employement_type_desc,camp_name,\n" + 
					" parents_marriage_desc,rationcard_type_desc\n" +					 
					" FROM SADAREM_VIEW_PARTA_FORM_BASIC_DETAILS\n" + 
					" WHERE\n" + 
					"sadarem_id=?";

					
					tempList  = new ArrayList();
					tempList.add("S");
					tempList.add(sadarem_id);
					paramList.add(tempList);
					
					tempList  = new ArrayList();
					tempList=DataAccess.pickDataByPrepareStmtArrayListHashMap(lStrQuery, paramList);
					
					if(tempList.size()==1)
					{
						requestList = (HashMap)tempList.get(0);
					}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return requestList;
	}
	
	public String getPartBFormStatus(String sadarem_id)
	{
		String Status="";
    	try 
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		lStrQuery=  
    						"SELECT CASE WHEN SUM(a.mycount) >0 THEN 'View' ELSE 'Edit' END status\n" +
    						"FROM\n" + 
    						"(\n" + 
    						"SELECT COUNT(*) mycount FROM tblPerson_Personal_Details with(nolock) WHERE View_Edit_Mode='View' AND person_code=?\n" + 
    						"UNION ALL\n" + 
    						"SELECT COUNT(*) mycount FROM tblPerson_disability_details with(nolock) WHERE status='Active' AND person_code =?\n" + 
    						"UNION ALL\n" + 
    						"SELECT COUNT(*) mycount FROM tblPerson_Disability_TotalValue_Details with(nolock) WHERE status='Active' AND person_code =?\n" + 
    						"UNION ALL\n" + 
    						"SELECT COUNT(*) mycount FROM tblPerson_Causes_of_Disability_Details with(nolock) WHERE status='Active' AND person_code =?\n" + 
    						"UNION ALL\n" + 
    						"SELECT COUNT(*) mycount FROM tblRejectPerson_Details with(nolock) WHERE status='Active' AND person_code =?\n" + 
    						") a";

			    		tempList	= new ArrayList();
			    		tempList.add("S");
			    		tempList.add(sadarem_id);
			    		paramList.add(tempList);
			
			    		tempList	= new ArrayList();
			    		tempList.add("S");
			    		tempList.add(sadarem_id);
			    		paramList.add(tempList);
			
			    		tempList	= new ArrayList();
			    		tempList.add("S");
			    		tempList.add(sadarem_id);
			    		paramList.add(tempList);
			
			    		tempList	= new ArrayList();
			    		tempList.add("S");
			    		tempList.add(sadarem_id);
			    		paramList.add(tempList);
			
			    		tempList	= new ArrayList();
			    		tempList.add("S");
			    		tempList.add(sadarem_id);
			    		paramList.add(tempList);
    		
			    		log.info("get lStrQuery : "+lStrQuery+"\n ParamList : ");
		  	
			    		Status=DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
    		
    	
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl : "+lexp.getLocalizedMessage());
		 }
    	
    	return Status;	
	}
	
	

	public ArrayList getSADAREMOrginalDetails(String sadarem_id,String aadhaar_id)
	{
		ArrayList resultList = new ArrayList();
    	try 
    	{
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		lStrQuery=   
					"SELECT a.*,\n" +
					"CASE WHEN (a.disability_status='Y' AND percentage_status='Y' AND cause_disp_status='Y') OR rejection_status='Y' THEN 'Y' ELSE 'N' END record_status\n" + 
					" FROM\n" + 
					"(\n" + 
					"SELECT b.person_code,b.reference_form_number,\n" + 
					"CONVERT(VARCHAR(17),b.form_fill_date,113) form_fill_date,\n" + 
					"b.surname,b.first_name,b.gender gender_id,gt.gender_name,\n" + 
					"CONVERT(VARCHAR(12),b.date_of_birth,113) date_of_birth,\n" + 
					"ISNULL(b.phone_no,'-') person_contactno,\n" + 
					"b.ReasonforPersonStatus person_live_status,\n" + 
					"b.relationship relation_id,gt.relation_name relation_type, b.relation_name,\n" + 
					"b.mole_one,b.mole_two,b.personname_telugu,b.fathername_telugu,\n" + 
					"b.parents_marriage,b.person_status,b.status,b.view_edit_mode,b.proof_id,b.proofdoc_type,\n" + 
					"b.district_id,b.mandal_id,b.village_id,b.habitation_id,b.habcode,\n" + 
					"d.district_name,m.mandal_name,v.village_name,h.habitation_name,\n" + 
					"(SELECT CASE WHEN COUNT(*)>0 THEN 'Y' ELSE 'N' END status FROM tblPerson_disability_details WITH(NOLOCK) WHERE status='Active' AND person_code=b.person_code) disability_status,\n" + 
					"(SELECT CASE WHEN COUNT(*)>0 THEN 'Y' ELSE 'N' END status FROM tblPerson_Disability_TotalValue_Details WITH(NOLOCK) WHERE status='Active' AND person_code=b.person_code) percentage_status,\n" + 
					"(SELECT CASE WHEN COUNT(*)>0 THEN 'Y' ELSE 'N' END status FROM tblPerson_Causes_of_Disability_Details WITH(NOLOCK) WHERE status='Active' AND person_code=b.person_code) cause_disp_status,\n" + 
					"(SELECT CASE WHEN COUNT(*)>0 THEN 'Y' ELSE 'N' END status FROM tblRejectPerson_Details WITH(NOLOCK) WHERE status='Active' AND person_code=b.person_code) rejection_status\n" + 
					"FROM tblperson_personal_details b WITH(NOLOCK)\n" + 
					"LEFT JOIN sadarem_gender_relation_type_mapping gt WITH(NOLOCK) ON (b.gender=gt.gender_id AND b.relationship=gt.relation_id)\n" + 
					"LEFT JOIN tbldistrict_details d WITH(NOLOCK) ON (b.district_id=d.district_id)\n" + 
					"LEFT JOIN tblmandal_details m WITH(NOLOCK) ON (b.district_id=m.district_id AND b.mandal_id=m.mandal_id)\n" + 
					"LEFT JOIN tblvillage_details v WITH(NOLOCK) ON (b.district_id=v.district_id AND b.mandal_id=v.mandal_id AND b.village_id=v.village_id)\n" + 
					"LEFT JOIN tblhabitation_details h WITH(NOLOCK) ON (b.district_id=h.district_id AND b.mandal_id=h.mandal_id AND b.village_id=h.village_id AND b.habitation_id=h.habitation_id AND b.habcode=h.habitation_code)\n" + 
					") a\n";
    		
    		     if(!sadarem_id.equals("") && !aadhaar_id.equals("") && sadarem_id.length()==17 && aadhaar_id.length()==12)
    		     {
    		    	 lStrQuery+= " WHERE person_code=? AND proof_id=? "; 

			    		tempList	= new ArrayList();
			    		tempList.add("S");
			    		tempList.add(sadarem_id.trim());
			    		paramList.add(tempList);
			
			    		tempList	= new ArrayList();
			    		tempList.add("S");
			    		tempList.add(aadhaar_id.trim());
			    		paramList.add(tempList);
			
    		     }
    		     else  if(!sadarem_id.equals("") && sadarem_id.length()==17)
    		     {
    		    	 lStrQuery+= " WHERE person_code=? "; 

			    		tempList	= new ArrayList();
			    		tempList.add("S");
			    		tempList.add(sadarem_id.trim());
			    		paramList.add(tempList); 
    		     }
    		     else if(!aadhaar_id.equals("") && aadhaar_id.length()==12)
    		     {
    		    	 lStrQuery+= " WHERE proof_id=? "; 

			    		tempList	= new ArrayList();
			    		tempList.add("S");
			    		tempList.add(aadhaar_id.trim());
			    		paramList.add(tempList); 
    		     }
			    	 
    		
			    		log.info("get lStrQuery : "+lStrQuery+"\n ParamList : "+paramList);
		  	
			    		resultList=DataAccess.pickDataByPrepareStmtArrayListHashMap(lStrQuery, paramList);
    		
    	
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl : "+lexp.getLocalizedMessage());
		 }
    	
    	return resultList;	
	}
	
	
	
	
	public static String getLastUpdatedDate() 
	{
        String updatedDate = null; 
        try 
        {
        	
        	updatedDate = DataAccess.getReturnResult("select convert(varchar,LastUpdatedDate,103) from LastUpdatedDate");
        
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return updatedDate;
    }
	

	
	 public static int lastUpdatedDate()
	    {
	        int updatedDate = 0;
	        Connection con = null;
	        Statement st = null;
	        String sql = null;
	        try 
	        {
	            con = DBConnection.getConnection();
	            st = con.createStatement();

	            sql = "update LastUpdatedDate set LastUpdatedDate=getDate()";
	            updatedDate = st.executeUpdate(sql);

	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	        finally 
	        {
	            try 
	            {
	                if (con != null) 
	                {
	                    con.close();
	                }
	                if (st != null)
	                {
	                    st.close();
	                }
	            }
	            catch (Exception e) 
	            {
	                e.printStackTrace();
	            }
	        }
	        return updatedDate;
	    }
}
