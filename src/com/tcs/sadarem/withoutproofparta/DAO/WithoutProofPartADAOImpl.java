package com.tcs.sadarem.withoutproofparta.DAO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.bf.disability.Constants.CommonConstants;

import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAOImpl;
import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.issuetracksystem.actions.issueTrackingCommonUtil;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.CommonValidators;
import com.tcs.sadarem.util.DataAccess;
import com.tcs.sadarem.util.SMSUtility;
import com.tcs.sgv.common.util.DBConnection;

public class WithoutProofPartADAOImpl implements WithoutProofPartADAO 
{
	static final private Logger log = Logger.getLogger(CommonDAOImpl.class);
	Connection lcon;
	String lStrQuery;
	
	public ArrayList getPartARequestDetailsListByCampId(String camp_id,String request_type,String start_date,String end_date)
	{
		ArrayList resultList = new ArrayList();
		
		try
		{
			ArrayList paramList = new ArrayList();
			ArrayList tempList	= new ArrayList();
			
			lStrQuery=   
						"SELECT request_id,ISNULL(sadarem_id,'NA') sadarem_id,reference_form_number,district_id,mandal_id,\n" +
						"panchayath_id,village_id,habitation_id,habcode,gender_id,religion_id,\n" + 
						"caste_id,marital_status_id,education_id,employment_id,surname,\n" + 
						"first_name,personname_telugu,CONVERT(VARCHAR(10),date_of_birth,103) date_of_birth,relationship_id,\n" + 
						"relation_name,relationname_telugu,mole_one,mole_two,house_number,\n" + 
						"pin_code,phone_no,email,parents_marriage,rationcard_type,rationcard_slno,\n" + 
						"rationcard_number,epic_card,epiccard_no,pension_card,pension_type,\n" + 
						"pensioncard_no,CONVERT(VARCHAR(10),form_fill_date,103) form_fill_date,camp_id,proof_id,proofdoc_type,\n" + 
						"district_name,mandal_name,panchayat_name,village_name,habitation_name,\n" + 
						"gender_name,relationship_type_desc,religion_desc,caste_desc,\n" + 
						"marital_status_desc,edu_desc,employement_type_desc,camp_name,\n" + 
						"parents_marriage_desc,rationcard_type_desc,req_status,req_status_desc,\n" + 
						"login_id,login_name,CONVERT(VARCHAR(20),form_entered_date,113) form_entered_date\n" + 
						"FROM SADAREM_VIEW_PARTA_REQUEST_DTLS WITH(NOLOCK) \n" + 
						"WHERE\n" + 
						"camp_id=? AND req_status=? ";


						tempList = new ArrayList();	
					 	tempList.add("S");
			    		tempList.add(camp_id);
			    		paramList.add(tempList);
			    		

						tempList = new ArrayList();	
					 	tempList.add("S");
			    		if(request_type==null || request_type.equals(""))
			    		{ 
				    		tempList.add("P");
			    		}
			    		else
			    		{
				    		tempList.add(request_type.trim().toUpperCase());
			    		}
			    		paramList.add(tempList);
			    		
			    		
			    		//System.out.println("CommonValidators.dateTimeValidator(start_date) : "+CommonValidators.dateValidater(start_date));
			    		//System.out.println("CommonValidators.dateTimeValidator(end_date) : "+CommonValidators.dateValidater(end_date));
			    		
			    		//System.out.println("Date between : "+CommonValidators.validateBetweenTwoDateTimes(start_date, "dd/MM/yyyy", end_date, "dd/MM/yyyy", 0, 0));
			    		
			    		if(
			    				!request_type.equals("P") &&
			    				start_date!=null && !start_date.equals("") && 
			    				end_date!=null && !end_date.equals("") &&
			    				CommonValidators.dateValidater(start_date)==true &&
			    				CommonValidators.dateValidater(end_date)==true &&
			    				CommonValidators.validateBetweenTwoDateTimes(start_date, "dd/MM/yyyy", end_date, "dd/MM/yyyy", 0, 0)==true 
			    		)
			    		{
			    			lStrQuery+=" AND CONVERT(DATE,modified_date,103) BETWEEN CONVERT(DATE,?,103) AND CONVERT(DATE,?,103) ";

							tempList = new ArrayList();	
						 	tempList.add("S");
				    		tempList.add(start_date);
				    		paramList.add(tempList);
				    		

							tempList = new ArrayList();	
						 	tempList.add("S");
				    		tempList.add(end_date);
				    		paramList.add(tempList);
			    		}
			    		 
			    		// System.out.println("lStrQuery : \n"+lStrQuery+"\n paramList : "+paramList);
			    		
					resultList=(ArrayList)DataAccess.pickDataByPrepareStmtArrayListHashMap(lStrQuery, paramList);
					paramList = null;
		    		tempList  = null;		
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
		
		return resultList;
	}
	
	
	public ArrayList getPartARequesStatustDetailsListByLoginId(String userId,String request_id,String proof_id,String start_date,String end_date)
	{
		ArrayList resultList = new ArrayList();
		
		try
		{
			ArrayList paramList = new ArrayList();
			ArrayList tempList	= new ArrayList();
			
			lStrQuery=   
						" SELECT request_id,ISNULL(sadarem_id,'NA') sadarem_id,reference_form_number,district_id,mandal_id,\n" +
						" panchayath_id,village_id,habitation_id,habcode,gender_id,religion_id,\n" + 
						" caste_id,marital_status_id,education_id,employment_id,surname,\n" + 
						" first_name,personname_telugu,CONVERT(VARCHAR(10),date_of_birth,103) date_of_birth,relationship_id,\n" + 
						" relation_name,relationname_telugu,mole_one,mole_two,house_number,\n" + 
						" pin_code,phone_no,email,parents_marriage,rationcard_type,rationcard_slno,\n" + 
						" rationcard_number,epic_card,epiccard_no,pension_card,pension_type,\n" + 
						" pensioncard_no,CONVERT(VARCHAR(10),form_fill_date,103) form_fill_date,camp_id,proof_id,proofdoc_type,\n" + 
						" district_name,mandal_name,panchayat_name,village_name,habitation_name,\n" + 
						" gender_name,relationship_type_desc,religion_desc,caste_desc,\n" + 
						" marital_status_desc,edu_desc,employement_type_desc,camp_name,\n" + 
						" parents_marriage_desc,rationcard_type_desc,req_status,req_status_desc,\n" + 
						" login_id,login_name,CONVERT(VARCHAR(20),form_entered_date,113) form_entered_date,CONVERT(VARCHAR(20),modified_date,113) last_updated_date\n" + 
						" FROM SADAREM_VIEW_PARTA_REQUEST_DTLS WITH(NOLOCK) \n" + 
						" WHERE\n" + 
						" login_id=? ";


						tempList = new ArrayList();	
					 	tempList.add("S");
			    		tempList.add(userId);
			    		paramList.add(tempList);
			    		
			    		if(request_id!=null && !request_id.equals(""))
			    		{  
			    			lStrQuery+=" AND request_id = ? \n";
			    			
			    			tempList = new ArrayList();	
			    			tempList.add("S");  
				    		tempList.add(request_id); 
				    		paramList.add(tempList);
			    		}
			    		
			    		if(proof_id!=null && !proof_id.equals(""))
			    		{  
			    			lStrQuery+=" AND proof_id = ? \n";
			    			
			    			tempList = new ArrayList();	
			    			tempList.add("S");  
				    		tempList.add(proof_id); 
				    		paramList.add(tempList);
			    		}
			    		 
			    	  	
			    		if( 
			    				start_date!=null && !start_date.equals("") && 
			    				end_date!=null && !end_date.equals("") &&
			    				CommonValidators.dateValidater(start_date)==true &&
			    				CommonValidators.dateValidater(end_date)==true &&
			    				CommonValidators.validateBetweenTwoDateTimes(start_date, "dd/MM/yyyy", end_date, "dd/MM/yyyy", 0, 0)==true 
			    		)
			    		{
			    			lStrQuery+=" AND CONVERT(DATE,form_entered_date,103) BETWEEN CONVERT(DATE,?,103) AND CONVERT(DATE,?,103) ";

							tempList = new ArrayList();	
						 	tempList.add("S");
				    		tempList.add(start_date);
				    		paramList.add(tempList);
				    		

							tempList = new ArrayList();	
						 	tempList.add("S");
				    		tempList.add(end_date);
				    		paramList.add(tempList);
			    		}
			    		 
			    		lStrQuery+=" ORDER BY modified_date DESC ";

			    		//  System.out.println("lStrQuery : \n"+lStrQuery+"\n paramList : "+paramList);
			    		
					resultList=(ArrayList)DataAccess.pickDataByPrepareStmtArrayListHashMap(lStrQuery, paramList);
					paramList = null;
		    		tempList  = null;		
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
		
		return resultList;
	}
	
	
	
	public String getRelationType(String genderID,String relationID)
	{
		ArrayList resultList = new ArrayList();
		ArrayList paramList = new ArrayList();
		ArrayList tempList	= new ArrayList();
		String result="";
		try
		{
			lStrQuery=
					
					"select relation_name from sadarem_gender_relation_type_mapping(nolock) where gender_id=? and relation_id=?" ;
					
			 	tempList.add("S");
	    		tempList.add(genderID);
	    		paramList.add(tempList);
	    		
	    		tempList = new ArrayList();
	    		tempList.add("S");
	    		tempList.add(relationID);
	    		paramList.add(tempList);
	    		


				log.info("getRelationType lStrQuery : "+lStrQuery);
  	
					result=(String)DataAccess.getReturnResultByPstmt(lStrQuery, paramList);
					paramList = null;
		    		tempList  = null;		
		}   
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in WithoutProofPartADAOImpl @ getRelationType :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in WithoutProofPartADAOImpl @ getRelationType : "+lexp.getLocalizedMessage());
		 }
		
		
		return result;
	}
	
	public HashMap getRequestDetailsByAadhaarIdorRequestId(String aadhaarId,String request_id)
	{
		HashMap resultList = new HashMap();
		try
		{
			ArrayList paramList = new ArrayList();
			ArrayList tempList  = new ArrayList();
			
				lStrQuery = 
						 " SELECT \n"+
						 " r.request_id, \n"+
						 " ISNULL(r.sadarem_id,'NA') sadarem_id, \n"+
						 " r.Reference_Form_Number form_no, \n"+
						 " CONVERT(VARCHAR(12),r.form_fill_date,113) form_filled_date, \n"+
						 " r.surname+' '+r.first_name person_name, \n"+
						 " r.personname_telugu person_name_telugu,  \n"+
						 " CONVERT(VARCHAR(11),r.date_of_birth,113) person_dob, \n"+
						 " r.camp_id, \n"+
						 " c.name+' ('+address+')' camp_name, \n"+
						 " r.created_by enrolled_by, \n"+
						 " CONVERT(VARCHAR(20),r.created_date,113) enrolled_date, \n"+
						 " r.req_status  req_status_flag, \n"+
						 " CASE  \n"+
						 " WHEN r.req_status='P' THEN 'Pending' \n"+
						 " WHEN r.req_status='A' THEN 'Approved' \n"+
						 " WHEN r.req_status='R' THEN 'Rejected' \n"+
						 " WHEN r.req_status='D' THEN 'Deleted' \n"+
						 " END req_status_desc, \n"+
						 " r.proof_id \n"+
						 "  FROM sadarem_without_proof_request_master r WITH(NOLOCK) \n"+
						 "  LEFT JOIN tblCamp_Details c WITH(NOLOCK) ON(r.camp_id=c.camp_id)   \n"+
						 " WHERE req_status IN ('P','A') AND (proof_id=? OR request_id=?) ";

					tempList  = new ArrayList();
					tempList.add("S");
					tempList.add(aadhaarId);
					paramList.add(tempList);
					
					tempList  = new ArrayList();
					tempList.add("S");
					tempList.add(request_id);
					paramList.add(tempList);
					
					tempList = new ArrayList();
					tempList = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, true, false);
				
				if(tempList.size()==2)
				{
					resultList = (HashMap)CommonUtility.getHashMapfromArrayListWithcolumnName(tempList);
				}
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resultList;
	}
	
	public ArrayList getPartAProofDetailsList(String request_id)
	{
		ArrayList resultList = new ArrayList();
		try
		{
			ArrayList paramList = new ArrayList();
			ArrayList tempList  = new ArrayList();
			
				lStrQuery = 
						 " SELECT \n"+
						 " m.proof_type_name,d.proof_number, \n"+
						 " proof_doc_path,proof_doc_type \n"+
						 " FROM  \n"+
						 " sadarem_without_proof_doc_details d WITH(NOLOCK), \n"+
						 "  proof_type_master m WITH(NOLOCK) \n"+
						 "  WHERE  \n"+
						 " d.proof_type_id=m.proof_type_id AND \n"+
						 " d.request_id=? \n"+
						 " ORDER BY d.proof_type_id";

				 
					tempList  = new ArrayList();
					tempList.add("S");
					tempList.add(request_id);
					paramList.add(tempList);
					 
					resultList = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
				 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resultList;
	}
	
	
	
	public ArrayList getWithoutProofPartaList(String reqId,String aadhaarId)
	{
		ArrayList resultList = new ArrayList();
		ArrayList paramList = new ArrayList();
		ArrayList tempList	= new ArrayList();
		try
		{
			lStrQuery=
					
					"select *,(select proof_doc_path+',' from sadarem_without_proof_request_master(nolock) b,sadarem_without_proof_doc_details(nolock) c\n"
					+ "where b.request_id = c.request_id and b.request_id = ?"
					+ " FOR XML PATH('')\n"
					+ ") path from sadarem_without_proof_request_master(nolock) where proof_id=? and  request_id=? order by Updated_Date desc" ;
					
    		
    		            tempList.add("S");
			    		tempList.add(reqId);
			    		paramList.add(tempList);
			    		
			    		tempList = new ArrayList();
			    		tempList.add("S");
			    		tempList.add(aadhaarId);
			    		paramList.add(tempList);
			    		
			    		tempList = new ArrayList();
			    		tempList.add("S");
			    		tempList.add(reqId);
			    		paramList.add(tempList);
    		
		  	log.info("getWithoutProofPartaList lStrQuery : "+lStrQuery);
		  	
    		resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
    		
    		paramList = null;
    		tempList  = null;

	
					
		}   
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in WithoutProofPartADAOImpl @ getPendingWithoutProofPartaList :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in WithoutProofPartADAOImpl @ getPendingWithoutProofPartaList : "+lexp.getLocalizedMessage());
		 }
		
		return resultList;
	}
	
	
	
	public boolean logPartADtlsWithAadhaar(HttpServletRequest request,HashMap formDataList,String district_id,String aadhaar_id,String author)
	{ 
		lcon =null;

		boolean formStatus = false;
        String request_id= "";
		try
		{
			CommonDAO comObj = new CommonDAOImpl();
				 
			boolean is_pension_exist = false,is_epiccard_exist = false;
			//System.out.println("formDataList : "+formDataList);

			String camp_id				 	 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("camp_id")); 						// Camp Id
			String reference_form_number 	 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("reference_form_number")); 		// Form Number
			String form_fill_date 			 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("form_fill_date")); 				// Form Filled Date
			String mandals	 				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("mandals"));						// Mandal ID
			String panchayats 				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("panchayats"));					// Panchayat
			String villages	 				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("villages"));						// Village
			String habitation 				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("habitation"));					// Habitation
			String house_number 			 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("house_number"));					// house number
			String pin_code 				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("pin_code"));						// Pin code
			String phone_no					 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("phone_no"));						// Contact No.
			String email					 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("email"));						// eMail Id
			String surname					 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("surname"));						// Surname
			String first_name				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("first_name"));					// Name
			String personname_telugu		 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("personname_telugu"));			// Person Name in Telugu
			String date_of_birth			 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("date_of_birth"));				// Date of birth
			String gender					 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("gender"));						// Gender
			String grelation				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("grelation")); 					// relation type
			String relation_name			 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("relation_name"));				// Relation name
			String relationname_telugu		 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("relationname_telugu"));			// Relation name in telugu
			String education_id				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("education_id"));					// Qualification
			String employment_id			 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("employment_id"));				// Employement
			String marital_status_id		 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("marital_status_id"));			// Marital Status
			String caste_id					 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("caste_id"));						// Caste
			String religion_id				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("religion_id"));					// Religion
			String rationcard_number		 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("rationcard_number"));			// Ration card No.
			String rationcard_type			 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("rationcard_type"));				// Ration Type
			String rationcard_slno			 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("rationcard_slno"));				// Ration card S.No.
			String mole_one					 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("mole_one"));						// Identity Marks 1
			String mole_two					 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("mole_two"));						// Identity Marks 2
			String pension_type				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("pension_type"));					// Pension Type
			String pensioncard_no			 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("pensioncard_no"));				// Pension No.
			String parents_marriage			 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("parents_marriage"));				// Parents Marriage 
			String epiccard_no				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("epiccard_no"));					// EPIC Card no.
			FileItem profile_pic			 = (FileItem)formDataList.get("profile_pic");														// Profile Picture
			 
		  	FileItem aadhaar_proof_doc		= (FileItem)formDataList.get("aadhaar_proof_doc");													// Aadhaar Soft copy file
			
		  	
		 // 	System.out.println("relationname_telugu : "+relationname_telugu);
			
			String add_proof_type			= CommonUtility.getStringFromFileItem((FileItem)formDataList.get("add_proof_type"));				// 2nd Proof Type
			String add_proof_id				= CommonUtility.getStringFromFileItem((FileItem)formDataList.get("add_proof_id"));					// 2nd Proof Id.			
			FileItem add_proof_doc			= (FileItem)formDataList.get("add_proof_doc");														// 2nd Proof Soft copy file.
			
			String user_ip_address			= CommonUtility.getClientIPAddress(request);
			
			int aadhaar_doc_status = 2 ; // Aadhaar Doc status 1 : Not available ; 2 : Available.
			
			  if(!pensioncard_no.equals(""))
	            {
	            	is_pension_exist = true;
	            }
	            else
	            {
	            	is_pension_exist = false;
	            }
			  
			  if(!epiccard_no.equals(""))
			  {
				  is_epiccard_exist = true;
			  }
			  else
			  {
				  is_epiccard_exist = false;
			  }
			
			  
			  
			
			boolean validationStatus 		= validatePartAFormWithAadhaarID(request,camp_id, reference_form_number, form_fill_date, mandals, panchayats, villages, habitation, house_number, pin_code, phone_no, email, surname, first_name, personname_telugu, date_of_birth, gender, grelation, relation_name, relationname_telugu, education_id, employment_id, marital_status_id, religion_id, caste_id, rationcard_number, rationcard_type, rationcard_slno, mole_one, mole_two, pension_type, pensioncard_no, parents_marriage, epiccard_no, profile_pic, aadhaar_proof_doc, add_proof_type, add_proof_id, add_proof_doc,true,aadhaar_doc_status);
			 
            request_id= comObj.getRequestIdForPartADeowitoutProof();			// generating new request id
          
            if(validationStatus==true)
            {
            	
            	String profile_pic_type="",profile_pic_name="",profile_pic_content_type="";
    			String aadhaar_proof_doc_type="",aadhaar_proof_doc_name="",aadhaar_proof_doc_content_type="";
    			String add_proof_doc_type="",add_proof_doc_name="",add_proof_doc_content_type="";

    			/*Profile Picture Details Started*/
    			
    			if(profile_pic!=null && profile_pic.getSize()>0) 
    			{
    				profile_pic_name = profile_pic.getName();
    				profile_pic_type = profile_pic_name.substring((profile_pic_name.lastIndexOf(".")+1));
    				profile_pic_content_type = profile_pic.getContentType().toLowerCase();
    			}
    			
    			/*Profile Picture Details Ended*/
    			
    			/*Aadhaar Document Details Started*/
    			
    			if(aadhaar_proof_doc!=null && aadhaar_proof_doc.getSize()>0) 
    			{
    				aadhaar_proof_doc_name = aadhaar_proof_doc.getName();
    				aadhaar_proof_doc_type = aadhaar_proof_doc_name.substring((aadhaar_proof_doc_name.lastIndexOf(".")+1));
    				aadhaar_proof_doc_content_type = aadhaar_proof_doc.getContentType().toLowerCase();
    			}		
    			
    			/* Aadhaar Document Details Ended */
    			
    			/*Additional Document Details Started*/
    			
    			if(add_proof_doc!=null && add_proof_doc.getSize()>0) 
    			{
    				add_proof_doc_name = add_proof_doc.getName();
    				add_proof_doc_type = add_proof_doc_name.substring((add_proof_doc_name.lastIndexOf(".")+1));
    				add_proof_doc_content_type = add_proof_doc.getContentType().toLowerCase();
    			}		
    			
    			/* Additional Document Details Ended */
            	
            	
            
            	String habcode			 =  comObj.checkHabitation( district_id, mandals, panchayats, villages, habitation);
            	
            	if(!habcode.equals(""))
            	{
            	
            		lcon = DBConnection.getConnection();
                	lcon.setAutoCommit(false);
                	
                	boolean reqStatus=false, 
                			profilePicStatus=false,
                			aadhaarProofUploadStatus=false,
                			additionalDocUploadStatus = false;  
                	
                	String profile_pic_path	 = request_id+"/"+request_id+".jpg";
            		
	            	reqStatus 		 		 = logPartADetailsWithAaahaarID(lcon, request_id, reference_form_number, form_fill_date,district_id, mandals, panchayats, villages, habitation,habcode, house_number, pin_code, phone_no, email, surname, first_name, personname_telugu, date_of_birth, gender, grelation, relation_name, relationname_telugu, education_id, employment_id, marital_status_id, caste_id,religion_id, rationcard_number, rationcard_type, rationcard_slno, mole_one, mole_two, is_pension_exist, pension_type, pensioncard_no, parents_marriage, is_epiccard_exist, epiccard_no, camp_id, aadhaar_id, user_ip_address, author,profile_pic_path,2);
	            	profilePicStatus 		 = logPartAProfilePic(request_id, profile_pic,profile_pic_path);
	            	aadhaarProofUploadStatus = logPartAProofDocuments(lcon, request_id, "PR001", aadhaar_id, request_id+"/"+aadhaar_id+"."+aadhaar_proof_doc_type, aadhaar_proof_doc_type, aadhaar_proof_doc, author);
	            	
	            	if(add_proof_doc!=null && add_proof_doc.getSize()>0) 
	    			{
	            		additionalDocUploadStatus = logPartAProofDocuments(lcon, request_id, add_proof_type, add_proof_id, request_id+"/"+add_proof_id+"."+add_proof_doc_type, add_proof_doc_type, add_proof_doc, author);
	    			}
	            	else
	            	{
	            		additionalDocUploadStatus = true; 
	            	}
	            	
	            	if(reqStatus==true && profilePicStatus==true && aadhaarProofUploadStatus==true && additionalDocUploadStatus==true)
	            	{

	            		lcon.commit();
	                	formStatus =true;
	            		request.setAttribute("requestDetailsList", getRequestDetailsByAadhaarIdorRequestId(request_id, request_id));
	            		request.setAttribute("alert_msg","Details saved successfully. Please note the details");
	            		request.setAttribute("alert_css","alert-success");
	            	}
	            	else
	            	{
	            		lcon.rollback();
	                	formStatus =false;
	            		request.setAttribute("alert_msg","01) Failed to save the details. Please try again");
	            		request.setAttribute("alert_css","alert-danger"); 
	            	}

			        CommonDAOImpl.deletePartAWithoutProofDirofProof(request_id); // Deletes the directory if the request fails
	            	lcon.close();
            	}
            	else
            	{
                	formStatus =false;
            		request.setAttribute("alert_msg","Not matching with the given habitation details.Please select other territory details.");
            		request.setAttribute("alert_css","alert-danger"); 
            	}
            }
            else
            {
            	formStatus =false;
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
        	formStatus =false;
    		request.setAttribute("alert_msg","Sorry we are not able to process your request.Please try again.!");
    		request.setAttribute("alert_css","alert-danger"); 
		}
		finally
		{
			DBConnection.closeConnection(lcon);
		}
		 
		return formStatus;
	}
	
	
	private boolean validatePartAFormWithAadhaarID
	(
			HttpServletRequest request,String camp_id,String reference_form_number,String form_fill_date,
			String mandals,String panchayats,String villages,String habitation,String house_number,
			String pin_code,String phone_no,String email,String surname,String first_name,String personname_telugu,
			String date_of_birth,String gender,String grelation,String relation_name,String relationname_telugu,
			String education_id,String employment_id,String marital_status_id,String religion_id,String caste_id,String rationcard_number,
			String rationcard_type,String rationcard_slno,String mole_one,String mole_two,String pension_type,
			String pensioncard_no,String parents_marriage,String epiccard_no,FileItem profile_pic,FileItem aadhaar_proof_doc,
			String add_proof_type,String add_proof_id,FileItem add_proof_doc,boolean checkAllAttachements,int aadhaar_doc_status
	)
	{
		boolean status = false;
		
		String alert_css				 = "alert-info";
		String alert_msg				 = "";
		
		try
		{
			int rationStatus = 0;
			int addProofValid = 0;
			 
			long maxProfilePicSize			 = 50*1024;																							// 50 KB
			String profilePiceTypeAllowed	 = "jpeg,png,jpg";																					// File type for profile picture
			String picContendTypeAllowed	 = "image/jpg,image/png,image/jpeg";																// File content type allowed.

			long maxDocProofSize			 = 200*1024;									//200KB
			String proofDocTypeAllowed		 = "pdf,jpeg,jpg,png";
			String proofDocContendTypeAllowed = "application/pdf,image/jpg,image/png,image/jpeg";							
			
			String profile_pic_type="",profile_pic_name="",profile_pic_content_type="";
			String aadhaar_proof_doc_type="",aadhaar_proof_doc_name="",aadhaar_proof_doc_content_type="";
			String add_proof_doc_type="",add_proof_doc_name="",add_proof_doc_content_type="";

			/*Profile Picture Details Started*/
			
			if(profile_pic!=null && profile_pic.getSize()>0) 
			{
				profile_pic_name = profile_pic.getName();
				profile_pic_type = profile_pic_name.substring((profile_pic_name.lastIndexOf(".")+1));
				profile_pic_content_type = profile_pic.getContentType().toLowerCase();
			}
			
			/*Profile Picture Details Ended*/
			
			/*Aadhaar Document Details Started*/
			
			if(aadhaar_proof_doc!=null && aadhaar_proof_doc.getSize()>0) 
			{
				aadhaar_proof_doc_name = aadhaar_proof_doc.getName();
				aadhaar_proof_doc_type = aadhaar_proof_doc_name.substring((aadhaar_proof_doc_name.lastIndexOf(".")+1)).toLowerCase();
				aadhaar_proof_doc_content_type = aadhaar_proof_doc.getContentType().toLowerCase();
			}		
			
			/* Aadhaar Document Details Ended */
			
			/*Additional Document Details Started*/
			
			if(add_proof_doc!=null && add_proof_doc.getSize()>0) 
			{
				add_proof_doc_name = add_proof_doc.getName();
				add_proof_doc_type = add_proof_doc_name.substring((add_proof_doc_name.lastIndexOf(".")+1)).toLowerCase();
				add_proof_doc_content_type = add_proof_doc.getContentType().toLowerCase();
			}		
			
			/* Additional Document Details Ended */
			
			if(!rationcard_number.equals("") && rationcard_number.length()>0)
				{
					rationStatus=rationStatus+1;
				}
			
				if(!rationcard_type.equals("") && !rationcard_type.equals("-1"))
				{
					rationStatus=rationStatus+1;
				}
					
				if(!rationcard_slno.equals("") && rationcard_slno.length()==2)
				{
					rationStatus=rationStatus+1;
				}
				 
				
				if(add_proof_type!=null && !add_proof_type.equals("") && !add_proof_type.equals("-1") )
				{
					addProofValid=addProofValid+1;
				}
				
				if(add_proof_id!=null && !add_proof_id.equals(""))
				{
					addProofValid=addProofValid+1;
				}
					
				if(add_proof_doc!=null && add_proof_doc.getSize()>0)
				{ 
					addProofValid=addProofValid+1;
				}
				
				CommonDAO comObj = new CommonDAOImpl();
				
			if(camp_id==null || camp_id.equals("") || camp_id.equals("0") || CommonUtility.checkNullObj(comObj.getCampName(Integer.parseInt(camp_id))).equals(""))
			{
				alert_msg = "Please Select Camp";
				alert_css = "alert-danger";
			}
			else if(reference_form_number.equals("") || CommonValidators.htmlStrValidation(reference_form_number)==false)
			{
				alert_msg = "Please enter valid Form No.(without html tags)";
				alert_css = "alert-danger";
			}
			else if(form_fill_date.equals("") || CommonValidators.dateValidater(form_fill_date)==false)
			{
				alert_msg = "Please enter valid Date of Assessment (dd/mm/yyyy format)";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(mandals.equals("") || mandals.equals("-1"))
			{
				alert_msg = "Please Select Mandal";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(panchayats.equals("") || panchayats.equals("-1"))
			{
				alert_msg = "Please Select Panchayat";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(villages.equals("") || villages.equals("-1"))
			{
				alert_msg = "Please Select Village";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(habitation.equals("") || habitation.equals("-1"))
			{
				alert_msg = "Please Select Habitation";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(house_number.equals("") || CommonValidators.htmlStrValidation(house_number)==false)
			{
				alert_msg = "Please enter valid House No.(without html tags)";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(pin_code.equals("") || CommonValidators.NumberValidator(pin_code)==false)
			{
				alert_msg = "Please enter valid pin code.(Only Numbers)";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(phone_no.equals("") || CommonValidators.mobileNumberValidator(phone_no)==false)
			{
				alert_msg = "Please enter valid Contact No.(Only Numbers)";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(!email.equals("") && CommonValidators.emailValidator(email)==false)
			{
				alert_msg = "Please enter valid eMail Id.(Only Numbers)";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(!surname.equals("") && CommonValidators.htmlStrValidation(surname)==false)
			{
				alert_msg = "Please enter valid Sur-Name.(without html tags)";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(first_name.equals("") || CommonValidators.htmlStrValidation(first_name)==false)
			{
				alert_msg = "Please enter valid Person Name.(without html tags)";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(personname_telugu.equals(""))
			{
				alert_msg = "Please enter valid Person Full Name in Telugu.";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(date_of_birth.equals("") || CommonValidators.dateValidater(date_of_birth)==false)
			{
				alert_msg = "Please enter valid Date of Assessment (dd/mm/yyyy format)";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(gender.equals("") || gender.equals("-1"))
			{
				alert_msg = "Please Select Gender";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(grelation.equals("") || grelation.equals("-1"))
			{
				alert_msg = "Please Select Type of Relation";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(relation_name.equals("") || CommonValidators.htmlStrValidation(relation_name)==false)
			{
				alert_msg = "Please enter valid Relation Name.(without html tags)";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(relationname_telugu.equals(""))
			{
				alert_msg = "Please enter Relation Name in Telugu";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(education_id.equals("") || education_id.equals("-1"))
			{
				alert_msg = "Please Select Education";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(employment_id.equals("") || employment_id.equals("-1"))
			{
				alert_msg = "Please Select Employment";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(marital_status_id.equals("") || marital_status_id.equals("-1"))
			{
				alert_msg = "Please Select Marital Status";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(religion_id.equals("") || religion_id.equals("-1"))
			{
				alert_msg = "Please Select Religion";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(caste_id.equals("") || caste_id.equals("-1"))
			{
				alert_msg = "Please Select Caste";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(caste_id.equals("") || caste_id.equals("-1"))
			{
				alert_msg = "Please Select Caste";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(rationStatus>0 && rationStatus<3)
			{ 
				alert_msg = "Please ration card details";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(mole_one.equals("") || CommonValidators.htmlStrValidation(mole_one)==false || ( !mole_two.equals("") && CommonValidators.htmlStrValidation(mole_two)==false) || mole_one.equals(mole_two))
			{
				alert_msg = "Please enter valid Mole details.(without html tags)";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(!pension_type.equals("") && !pension_type.equals("-1") && (pensioncard_no.equals("") || CommonValidators.NumberValidator(pensioncard_no)==false))
			{
				alert_msg = "Please enter valid Pension Number(Only Numbers)";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(parents_marriage.equals("") || parents_marriage.equals("-1"))
			{
				alert_msg = "Please select Consanguineous Marriage of Parents";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(!epiccard_no.equals("") && CommonValidators.htmlStrValidation(epiccard_no)==false)
			{
				alert_msg = "Please enter valid EPIC Card No.";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if
			(
				( profile_pic==null || profile_pic.getSize()<=0 || profilePiceTypeAllowed.indexOf(profile_pic_type.toLowerCase())==-1 || 
				picContendTypeAllowed.indexOf(profile_pic_content_type.toLowerCase())==-1 ||
				profile_pic.getSize()>maxProfilePicSize ) && checkAllAttachements==true
				
			)
			{
				alert_msg = "Please upload valid Profile Photo (Passport size photo with less than 50KB and type JPEG/JPG/PNG.)";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if
			(
				checkAllAttachements==true && aadhaar_doc_status==2 &&
				(aadhaar_proof_doc==null || aadhaar_proof_doc.getSize()<=0 || proofDocTypeAllowed.indexOf(aadhaar_proof_doc_type.toLowerCase())==-1 || 
				proofDocContendTypeAllowed.indexOf(aadhaar_proof_doc_content_type.toLowerCase())==-1 ||
				(aadhaar_proof_doc.getSize()>maxDocProofSize)==true )
			)
			{
				alert_msg = "Please upload valid Aadhaar Proof (File size less than "+maxDocProofSize/1024+"KB and type "+proofDocTypeAllowed.replace(","," / ")+".)";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if(addProofValid>0 && addProofValid<3)
			{
				alert_msg = "Please enter valid additional document details";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else if 
			(
				addProofValid==3 && checkAllAttachements==true &&
				( 
					add_proof_doc.getSize()==0 || 
					proofDocTypeAllowed.indexOf(add_proof_doc_type.toLowerCase())==-1 || 
					proofDocContendTypeAllowed.indexOf(add_proof_doc_content_type.toLowerCase())==-1 ||
					add_proof_doc.getSize()>maxDocProofSize
				) 
			)
			{
				alert_msg =" Please upload valid Additional Proof of document (File size less than "+maxDocProofSize/1024+"KB and type "+proofDocTypeAllowed.replace(","," / ")+".)";
				alert_css = "alert-danger";
				status 	  = false;
			}
			else
			{
				status= true;
			}
			
			
		}
		catch(Exception e)
		{
			alert_msg = "01) Sorry we are not able to process your request.Please try again.!";
			alert_css = "alert-danger";
			status 	  = false;
		}
		
		request.setAttribute("alert_msg",alert_msg);
		request.setAttribute("alert_css",alert_css);
		
		return status;
	}
	
	
	public boolean logPartARequestFormWithOutProof(HttpServletRequest request,HashMap formDataList,String district_id,String aadhaar_id,String author)
	{ 
		lcon =null;

		boolean formStatus = false;
        String request_id= "";
		try
		{
			CommonDAO comObj = new CommonDAOImpl();
				 
			boolean is_pension_exist = false,is_epiccard_exist = false;
			//System.out.println("formDataList : "+formDataList);

			String camp_id				 	 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("camp_id"));				 		// Camp ID
			String reference_form_number 	 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("reference_form_number")); 		// Form Number
			String form_fill_date 			 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("form_fill_date")); 				// Form Filled Date
			String mandals	 				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("mandals"));						// Mandal ID
			String panchayats 				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("panchayats"));					// Panchayat
			String villages	 				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("villages"));						// Village
			String habitation 				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("habitation"));					// Habitation
			String house_number 			 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("house_number"));					// house number
			String pin_code 				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("pin_code"));						// Pin code
			String phone_no					 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("phone_no"));						// Contact No.
			String email					 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("email"));						// eMail Id
			String surname					 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("surname"));						// Surname
			String first_name				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("first_name"));					// Name
			String personname_telugu		 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("personname_telugu"));			// Person Name in Telugu
			String date_of_birth			 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("date_of_birth"));				// Date of birth
			String gender					 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("gender"));						// Gender
			String grelation				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("grelation")); 					// relation type
			String relation_name			 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("relation_name"));				// Relation name
			String relationname_telugu		 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("relationname_telugu"));			// Relation name in telugu
			String education_id				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("education_id"));					// Qualification
			String employment_id			 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("employment_id"));				// Employement
			String marital_status_id		 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("marital_status_id"));			// Marital Status
			String caste_id					 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("caste_id"));						// Caste
			String religion_id				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("religion_id"));					// Religion
			String rationcard_number		 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("rationcard_number"));			// Ration card No.
			String rationcard_type			 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("rationcard_type"));				// Ration Type
			String rationcard_slno			 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("rationcard_slno"));				// Ration card S.No.
			String mole_one					 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("mole_one"));						// Identity Marks 1
			String mole_two					 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("mole_two"));						// Identity Marks 2
			String pension_type				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("pension_type"));					// Pension Type
			String pensioncard_no			 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("pensioncard_no"));				// Pension No.
			String parents_marriage			 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("parents_marriage"));				// Parents Marriage 
			String epiccard_no				 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("epiccard_no"));					// EPIC Card no.
			FileItem profile_pic			 = (FileItem)formDataList.get("profile_pic");														// Profile Picture

			String aadhaar_proof_status		 = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("aadhaar_proof_status"));			// Aadhaar Proof Status  

		  	FileItem aadhaar_proof_doc		= (FileItem)formDataList.get("aadhaar_proof_doc");													// Aadhaar Soft copy file
			
		  	
		 // 	System.out.println("relationname_telugu : "+relationname_telugu);
			
			String add_proof_type			= CommonUtility.getStringFromFileItem((FileItem)formDataList.get("add_proof_type"));				// 2nd Proof Type
			String add_proof_id				= CommonUtility.getStringFromFileItem((FileItem)formDataList.get("add_proof_id"));					// 2nd Proof Id.			
			FileItem add_proof_doc			= (FileItem)formDataList.get("add_proof_doc");														// 2nd Proof Soft copy file.
			
			String user_ip_address			= CommonUtility.getClientIPAddress(request);
			
			  if(!pensioncard_no.equals(""))
	            {
	            	is_pension_exist = true;
	            }
	            else
	            {
	            	is_pension_exist = false;
	            }
			  
			  if(!epiccard_no.equals(""))
			  {
				  is_epiccard_exist = true;
			  }
			  else
			  {
				  is_epiccard_exist = false;
			  }
			
			
			boolean validationStatus 		= validatePartAFormWithAadhaarID(request,camp_id, reference_form_number, form_fill_date, mandals, panchayats, villages, habitation, house_number, pin_code, phone_no, email, surname, first_name, personname_telugu, date_of_birth, gender, grelation, relation_name, relationname_telugu, education_id, employment_id, marital_status_id, religion_id, caste_id, rationcard_number, rationcard_type, rationcard_slno, mole_one, mole_two, pension_type, pensioncard_no, parents_marriage, epiccard_no, profile_pic, aadhaar_proof_doc, add_proof_type, add_proof_id, add_proof_doc,true,Integer.parseInt(aadhaar_proof_status));
			 
            request_id= comObj.getRequestIdForPartADeowitoutProof();			// generating new request id
            
            
           if( getCheckStatusofRequestExistByFNameSNameDOB(first_name, surname, date_of_birth)==true)
            {
        	   request.setAttribute("alert_css","alert-danger");
        	   request.setAttribute("alert_msg","Details already exist with same name and date of birth. Please check once");
        		formStatus =false;
            }
            else 
            {
            		if(validationStatus==true)
			            {
			            	
			            	String profile_pic_type="",profile_pic_name="",profile_pic_content_type="";
			    			String aadhaar_proof_doc_type="",aadhaar_proof_doc_name="",aadhaar_proof_doc_content_type="";
			    			String add_proof_doc_type="",add_proof_doc_name="",add_proof_doc_content_type="";
			
			    			/*Profile Picture Details Started*/
			    			
			    			if(profile_pic!=null && profile_pic.getSize()>0) 
			    			{
			    				profile_pic_name = profile_pic.getName();
			    				profile_pic_type = profile_pic_name.substring((profile_pic_name.lastIndexOf(".")+1));
			    				profile_pic_content_type = profile_pic.getContentType().toLowerCase();
			    			}
			    			
			    			/*Profile Picture Details Ended*/
			    			
			    			/*Aadhaar Document Details Started*/
			    			
			    			if(aadhaar_proof_doc!=null && aadhaar_proof_doc.getSize()>0) 
			    			{
			    				aadhaar_proof_doc_name = aadhaar_proof_doc.getName();
			    				aadhaar_proof_doc_type = aadhaar_proof_doc_name.substring((aadhaar_proof_doc_name.lastIndexOf(".")+1));
			    				aadhaar_proof_doc_content_type = aadhaar_proof_doc.getContentType().toLowerCase();
			    			}		
			    			
			    			/* Aadhaar Document Details Ended */
			    			
			    			/*Additional Document Details Started*/
			    			
			    			if(add_proof_doc!=null && add_proof_doc.getSize()>0) 
			    			{
			    				add_proof_doc_name = add_proof_doc.getName();
			    				add_proof_doc_type = add_proof_doc_name.substring((add_proof_doc_name.lastIndexOf(".")+1));
			    				add_proof_doc_content_type = add_proof_doc.getContentType().toLowerCase();
			    			}		
			    			
			    			/* Additional Document Details Ended */
			            	
			            	
			            
			            	String habcode			 =  comObj.checkHabitation( district_id, mandals, panchayats, villages, habitation);
			            	
			            	if(!habcode.equals(""))
			            	{
			            	
			            		lcon = DBConnection.getConnection();
			                	lcon.setAutoCommit(false);
			                	
			                	boolean reqStatus=false, 
			                			profilePicStatus=false,
			                			aadhaarProofUploadStatus=false,
			                			additionalDocUploadStatus = false;  
			                	
			                	String profile_pic_path	 = request_id+"/"+request_id+".jpg";
			            		
				            	reqStatus 		 		 = logPartADetailsWithAaahaarID(lcon, request_id, reference_form_number, form_fill_date,district_id, mandals, panchayats, villages, habitation,habcode, house_number, pin_code, phone_no, email, surname, first_name, personname_telugu, date_of_birth, gender, grelation, relation_name, relationname_telugu, education_id, employment_id, marital_status_id, caste_id,religion_id, rationcard_number, rationcard_type, rationcard_slno, mole_one, mole_two, is_pension_exist, pension_type, pensioncard_no, parents_marriage, is_epiccard_exist, epiccard_no, camp_id, aadhaar_id, user_ip_address, author,profile_pic_path,Integer.parseInt(aadhaar_proof_status));
				            	profilePicStatus 		 = logPartAProfilePic(request_id, profile_pic,profile_pic_path);
				            	
				            	
				            	// Aadhaar Proof Document upload method
				            	if(Integer.parseInt(aadhaar_proof_status)==2 && aadhaar_id!=null && !aadhaar_id.equals("") && CommonValidators.AadhaarNumberValidator(aadhaar_id)==true)
				            	{
				            		aadhaarProofUploadStatus = logPartAProofDocuments(lcon, request_id, "PR001", aadhaar_id, request_id+"/"+aadhaar_id+"."+aadhaar_proof_doc_type, aadhaar_proof_doc_type, aadhaar_proof_doc, author);
				            	}
				            	else if(Integer.parseInt(aadhaar_proof_status)==1)
				            	{
				            		aadhaarProofUploadStatus = true;
				            	}
				            	
				            	// Additional Proof Document upload method
				            	if(add_proof_doc!=null && add_proof_doc.getSize()>0) 
				    			{
				            		additionalDocUploadStatus = logPartAProofDocuments(lcon, request_id, add_proof_type, add_proof_id, request_id+"/"+add_proof_id+"."+add_proof_doc_type, add_proof_doc_type, add_proof_doc, author);
				    			}
				            	else
				            	{
				            		additionalDocUploadStatus = true; 
				            	}
				            	
				            	if(reqStatus==true && profilePicStatus==true && aadhaarProofUploadStatus==true && additionalDocUploadStatus==true)
				            	{
			
				            		lcon.commit();
				                	formStatus =true;
				   					request.setAttribute("PartARequestFullDltsList",(HashMap)getPartAFormDetailsByRequestId(request_id)); 
				            		request.setAttribute("alert_msg","Details saved successfully. Please note the details");
				            		request.setAttribute("alert_css","alert-success");
				            	}
				            	else
				            	{
				            		lcon.rollback();
				                	formStatus =false;
				            		request.setAttribute("alert_msg","01) Failed to save the details. Please try again");
				            		request.setAttribute("alert_css","alert-danger"); 
				            	}
			
						        CommonDAOImpl.deletePartAWithoutProofDirofProof(request_id); // Deletes the directory if the request fails
				            	lcon.close();
			            	}
			            	else
			            	{
			                	formStatus =false;
			            		request.setAttribute("alert_msg","Not matching with the given habitation details.Please select other territory details.");
			            		request.setAttribute("alert_css","alert-danger"); 
			            	}
			            }
			            else
			            {
			            	formStatus =false;
			            }
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
        	formStatus =false;
    		request.setAttribute("alert_msg","Sorry we are not able to process your request.Please try again.!");
    		request.setAttribute("alert_css","alert-danger"); 
		}
		finally
		{
			DBConnection.closeConnection(lcon);
		}
		 
		return formStatus;
	}
	
	
	private boolean logPartADetailsWithAaahaarID
	(
		Connection con,String request_id,String reference_form_number,String form_fill_date,String district_id,
		String mandal_id,String panchayath_id,String village_id,String habitation_id,String habcode,String house_number,
		String pin_code,String phone_no,String email,String surname,String first_name,String personname_telugu,
		String date_of_birth,String gender_id,String relationship_id,String relation_name,String relationname_telugu,
		String education_id,String employment_id,String marital_status_id,String caste_id,String religion_id,
		String rationcard_number,String rationcard_type,String rationcard_slno,String mole_one,String mole_two,
		boolean is_pension_exist,String pension_type,String pensioncard_no,String parents_marriage,
		boolean is_epiccard_exist,String epiccard_no,String camp_id,String aadhaarId,String user_ip_address,String author,
		String profile_pic_path,int aadhaar_doc_status
	)
	{
		boolean status = false;
		PreparedStatement preStm = null;
		try
		{
			lStrQuery = 
					" INSERT INTO sadarem_without_proof_request_master \n"+
					" ( \n"+
					" request_id,sadarem_id,reference_form_number,district_id,mandal_id, \n"+
					" panchayath_id,village_id,habitation_id,habcode,gender_id,religion_id, \n"+
					" caste_id,marital_status_id,education_id,employment_id,surname,first_name, \n"+
					" personname_telugu,date_of_birth,relationship_id,relation_name,relationname_telugu, \n"+
					" mole_one,mole_two,house_number,pin_code,phone_no,email,parents_marriage, \n"+
					" rationcard_type,rationcard_slno,rationcard_number,epic_card,epiccard_no,pension_card, \n"+
					" pension_type,pensioncard_no,form_fill_date,camp_id,proof_id,proofdoc_type,req_status, \n"+
					" status_remarks,system_ip_address,created_by,created_date,updated_by,updated_date,profile_pic_path \n"+
					" )\n"+
					"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,CONVERT(DATE,?,103),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,CONVERT(DATE,?,103),?,?,?,?,?,?,?,CURRENT_TIMESTAMP,?,CURRENT_TIMESTAMP,?)";
						
					preStm = lcon.prepareStatement(lStrQuery);
					
					int param =1;
					
					preStm.setString(param++,request_id);
					preStm.setNull(param++,Types.NULL);
					preStm.setString(param++,reference_form_number);
					preStm.setString(param++,district_id);
					preStm.setString(param++,mandal_id);
					preStm.setString(param++,panchayath_id);
					preStm.setString(param++,village_id);
					preStm.setString(param++,habitation_id);
					preStm.setString(param++,habcode);
					preStm.setString(param++,gender_id);
					preStm.setString(param++,religion_id);
					preStm.setString(param++,caste_id);
					preStm.setString(param++,marital_status_id);
					preStm.setString(param++,education_id);
					preStm.setString(param++,employment_id);
					preStm.setString(param++,surname.trim().toUpperCase());
					preStm.setString(param++,first_name);
					preStm.setString(param++,personname_telugu);
					preStm.setString(param++,date_of_birth);
					preStm.setString(param++,relationship_id);
					preStm.setString(param++,relation_name);
					preStm.setString(param++,relationname_telugu);
					preStm.setString(param++,mole_one);
					preStm.setString(param++,mole_two);
					preStm.setString(param++,house_number);
					preStm.setString(param++,pin_code);
					preStm.setString(param++,phone_no);
					preStm.setString(param++,email);
					preStm.setString(param++,parents_marriage);
					if(rationcard_type!=null && !rationcard_type.equals("") && !rationcard_type.equals("-1") && Integer.parseInt(rationcard_type)>0)
					{
						preStm.setString(param++,rationcard_type);
					}
					else
					{
						preStm.setNull(param++,Types.NULL);
					}
					
					if(!rationcard_slno.equals("") && rationcard_slno.length()>0)
					{
						preStm.setString(param++,rationcard_slno);
					}
					else
					{
						preStm.setNull(param++,Types.NULL);
					}
					
					if(!rationcard_number.equals("") && rationcard_number.length()>0)
					{
						preStm.setString(param++,rationcard_number);
					}
					else
					{
						preStm.setNull(param++,Types.NULL);
					}
					
					
					preStm.setBoolean(param++,is_epiccard_exist);
					
					if(!epiccard_no.equals("") && epiccard_no.length()>0)
					{
						preStm.setString(param++,epiccard_no);
					}
					else
					{
						preStm.setNull(param++,Types.NULL);
					}
					
					
					preStm.setBoolean(param++,is_pension_exist);
					
					if(!pension_type.equals("-1"))
					{
						preStm.setString(param++,pension_type);
					}
					else
					{
						preStm.setNull(param++,Types.NULL);
					}
					 
					if(!pensioncard_no.equals("") && pensioncard_no.length()>0)
					{
						preStm.setString(param++,pensioncard_no);
					}
					else
					{
						preStm.setNull(param++,Types.NULL);
					}
					
					preStm.setString(param++,form_fill_date);
					preStm.setString(param++,camp_id);
					if(aadhaar_doc_status==2)
					{
						preStm.setString(param++,aadhaarId); 
					}
					else
					{
						preStm.setString(param++,"NA");  
					}
					preStm.setString(param++,"Adhaar Card");
					preStm.setString(param++,"P");					// req_status P-Pending
					preStm.setNull(param++,Types.NULL);
					preStm.setString(param++,user_ip_address);
					preStm.setString(param++,author);
					preStm.setString(param++,author);
					preStm.setString(param++,profile_pic_path);
					
					if(preStm.executeUpdate()>0)
					{
						status=true;
					}
					else
					{
						status=false;
					}
					preStm.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status=false;
		}
		finally
		{
			DBConnection.closeStatement(preStm);
		}
		
		return status;
	}
	
	private boolean logPartAProfilePic(String request_id,FileItem profilePic_doc,String fileName)
	{
		boolean status = false;
		try
		{
			if(issueTrackingCommonUtil.createDirectory(CommonConstants.PARTA_ATTACHMENT_PATH+request_id)==true) 
			{								
				status=issueTrackingCommonUtil.writeFileToDirecotry(profilePic_doc,CommonConstants.PARTA_ATTACHMENT_PATH+fileName);
			}
			else
			{
				status=false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status=false;
		}
		
		return status;
	}
	
	
	
	private boolean logPartAProofDocuments
	(
			Connection con,String request_id,String proof_type_id,
			String proof_document_id,String document_path,
			String document_type,FileItem uploaded_doc,String author
	)
	{
		boolean status = false;
		PreparedStatement preStm = null;
		try
		{
			lStrQuery = " INSERT INTO sadarem_without_proof_doc_details \n"+
						" (request_id,proof_type_id,proof_number,proof_doc_path,proof_doc_type,is_active,created_by,created_date,updated_by,updated_date) \n"+
						" VALUES(?,?,?,?,?,?,?,CURRENT_TIMESTAMP,?,CURRENT_TIMESTAMP)";
			preStm = con.prepareStatement(lStrQuery);
			int param=1;

			preStm.setString(param++,request_id);
			preStm.setString(param++,proof_type_id);
			preStm.setString(param++,proof_document_id);
			preStm.setString(param++,document_path);
			preStm.setString(param++,document_type);
			preStm.setString(param++,"Y");
			preStm.setString(param++,author);
			preStm.setString(param++,author);
			
			if(preStm.executeUpdate()>0)
			{
				if(issueTrackingCommonUtil.createDirectory(CommonConstants.PARTA_ATTACHMENT_PATH+request_id)==true) 
				{	
					status =issueTrackingCommonUtil.writeFileToDirecotry(uploaded_doc,CommonConstants.PARTA_ATTACHMENT_PATH+request_id+"/"+proof_document_id+"."+document_type);					 
				}
				else
				{
				 status = false;
				}
			}
			else
			{
			 status = false;
			}
			
			preStm.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = false;
		}
		finally
		{
			DBConnection.closeStatement(preStm);
		}
		
		return status;
	}
	
	
	public HashMap getPartAFormDetailsByRequestId(String request_id)
	{
		HashMap requestList = new HashMap();
		try
		{
			ArrayList paramList = new ArrayList();
			ArrayList tempList  = new ArrayList();
			
			lStrQuery =  
					
					"SELECT request_id,sadarem_id,reference_form_number,district_id,mandal_id,\n" +
					" panchayath_id,village_id,habitation_id,habcode,gender_id,religion_id,\n" + 
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
					" parents_marriage_desc,rationcard_type_desc,req_status,req_status_desc,\n" + 
					" login_id,login_name,profile_pic_path,CONVERT(VARCHAR(20),modified_date,113) last_updated_date\n" + 
					" FROM SADAREM_VIEW_PARTA_REQUEST_DTLS\n" + 
					" WHERE\n" + 
					"request_id=?";

					
					tempList  = new ArrayList();
					tempList.add("S");
					tempList.add(request_id);
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
	
	 
	
	public void logPartARequestUpdate(HttpServletRequest request,String request_id,String district_id,String camp_id,String author)
	{ 
		lcon =null;
 
		try
		{
			CommonDAO comObj = new CommonDAOImpl();
				 
			boolean is_pension_exist = false,is_epiccard_exist = false; 
			
			String reference_form_number 	 = CommonUtility.checkNullObj(request.getParameter("reference_form_number")); 		// Form Number
			String form_fill_date 			 = CommonUtility.checkNullObj(request.getParameter("form_fill_date")); 				// Form Filled Date
			String mandals	 				 = CommonUtility.checkNullObj(request.getParameter("mandals"));						// Mandal ID
			String panchayats 				 = CommonUtility.checkNullObj(request.getParameter("panchayats"));					// Panchayat
			String villages	 				 = CommonUtility.checkNullObj(request.getParameter("villages"));					// Village
			String habitation 				 = CommonUtility.checkNullObj(request.getParameter("habitation"));					// Habitation
			String house_number 			 = CommonUtility.checkNullObj(request.getParameter("house_number"));				// house number
			String pin_code 				 = CommonUtility.checkNullObj(request.getParameter("pin_code"));					// Pin code
			String phone_no					 = CommonUtility.checkNullObj(request.getParameter("phone_no"));					// Contact No.
			String email					 = CommonUtility.checkNullObj(request.getParameter("email"));						// eMail Id
			String surname					 = CommonUtility.checkNullObj(request.getParameter("surname"));						// Surname
			String first_name				 = CommonUtility.checkNullObj(request.getParameter("first_name"));					// Name
			String personname_telugu		 = CommonUtility.checkNullObj(request.getParameter("personname_telugu"));			// Person Name in Telugu
			String date_of_birth			 = CommonUtility.checkNullObj(request.getParameter("date_of_birth"));				// Date of birth
			String gender					 = CommonUtility.checkNullObj(request.getParameter("gender"));						// Gender
			String grelation				 = CommonUtility.checkNullObj(request.getParameter("grelation")); 					// relation type
			String relation_name			 = CommonUtility.checkNullObj(request.getParameter("relation_name"));				// Relation name
			String relationname_telugu		 = CommonUtility.checkNullObj(request.getParameter("relationname_telugu"));			// Relation name in telugu
			String education_id				 = CommonUtility.checkNullObj(request.getParameter("education_id"));				// Qualification
			String employment_id			 = CommonUtility.checkNullObj(request.getParameter("employment_id"));				// Employement
			String marital_status_id		 = CommonUtility.checkNullObj(request.getParameter("marital_status_id"));			// Marital Status
			String caste_id					 = CommonUtility.checkNullObj(request.getParameter("caste_id"));					// Caste
			String religion_id				 = CommonUtility.checkNullObj(request.getParameter("religion_id"));					// Religion
			String rationcard_number		 = CommonUtility.checkNullObj(request.getParameter("rationcard_number"));			// Ration card No.
			String rationcard_type			 = CommonUtility.checkNullObj(request.getParameter("rationcard_type"));				// Ration Type
			String rationcard_slno			 = CommonUtility.checkNullObj(request.getParameter("rationcard_slno"));				// Ration card S.No.
			String mole_one					 = CommonUtility.checkNullObj(request.getParameter("mole_one"));					// Identity Marks 1
			String mole_two					 = CommonUtility.checkNullObj(request.getParameter("mole_two"));					// Identity Marks 2
			String pension_type				 = CommonUtility.checkNullObj(request.getParameter("pension_type"));				// Pension Type
			String pensioncard_no			 = CommonUtility.checkNullObj(request.getParameter("pensioncard_no"));				// Pension No.
			String parents_marriage			 = CommonUtility.checkNullObj(request.getParameter("parents_marriage"));			// Parents Marriage 
			String epiccard_no				 = CommonUtility.checkNullObj(request.getParameter("epiccard_no"));					// EPIC Card no. 
			 
		  	
		 // 	System.out.println("relationname_telugu : "+relationname_telugu);
			 
			
			
		 	
			String user_ip_address			= CommonUtility.getClientIPAddress(request);
			
			  if(!pensioncard_no.equals(""))
	            {
	            	is_pension_exist = true;
	            }
	            else
	            {
	            	is_pension_exist = false;
	            }
			  
			  if(!epiccard_no.equals(""))
			  {
				  is_epiccard_exist = true;
			  }
			  else
			  {
				  is_epiccard_exist = false;
			  }
			
			
			boolean validationStatus 		= validatePartAFormWithAadhaarID(request,camp_id, reference_form_number, form_fill_date, mandals, panchayats, villages, habitation, house_number, pin_code, phone_no, email, surname, first_name, personname_telugu, date_of_birth, gender, grelation, relation_name, relationname_telugu, education_id, employment_id, marital_status_id, religion_id, caste_id, rationcard_number, rationcard_type, rationcard_slno, mole_one, mole_two, pension_type, pensioncard_no, parents_marriage, epiccard_no, null, null, null, null, null,false,-1);
		 
			if(validationStatus==true)
            { 
            	String habcode			 =  comObj.checkHabitation( district_id, mandals, panchayats, villages, habitation);
            	if(habcode!=null && !habcode.equals("")) 
	            {

                	lcon = DBConnection.getConnection();
                	lcon.setAutoCommit(false);
 
                	
                		int reqLogStatus 	= partAWithoutProofInsertLog(lcon,request_id);
		            	boolean reqStatus 	= logPartAUpdatedDetails(lcon, request_id, reference_form_number, form_fill_date, mandals, panchayats, villages, habitation, habcode, house_number, pin_code, phone_no, email, surname, first_name, personname_telugu, date_of_birth, gender, grelation, relation_name, relationname_telugu, education_id, employment_id, marital_status_id, caste_id, religion_id, rationcard_number, rationcard_type, rationcard_slno, mole_one, mole_two, is_pension_exist, pension_type, pensioncard_no, parents_marriage, is_epiccard_exist, epiccard_no, user_ip_address, author);
		              	
		            	if(reqStatus==true && reqLogStatus>0)
		            	{
		            		lcon.commit(); 
		            		request.setAttribute("alert_msg","Details Updated successfully");
		            		request.setAttribute("alert_css","alert-success");
		            	}
		            	else
		            	{
		            		lcon.rollback();
		            		request.setAttribute("alert_msg","Failed to save the details. Please try again");
		            		request.setAttribute("alert_css","alert-danger"); 
		            	}
		 
		            	lcon.close();
	            }
            	else
            	{
            		request.setAttribute("alert_msg","Sorry we are not able to process your request.Please select other teritory details.");
            		request.setAttribute("alert_css","alert-danger");  
            	}
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
    		request.setAttribute("alert_msg","Sorry we are not able to process your request.Please try again.!");
    		request.setAttribute("alert_css","alert-danger"); 
		}
		finally
		{
			DBConnection.closeConnection(lcon);
		}
		 
	}
	public void logPartARejectRequest(HttpServletRequest request,String request_id,String status_remarks,String termFlag,String author)
	{
		lcon = null;
		PreparedStatement preStm = null;
		String alert_msg = "",alert_css = "";
		try
		{
			if(request_id==null || request_id.length()==0)
			{ 
				alert_msg = "Invalid Request Id. Please try again.";
				alert_css = "alert-danger";
			}
			else if(status_remarks==null || status_remarks.length()<15 || CommonValidators.htmlStrValidation(status_remarks)==false)
			{
				alert_msg = "Please enter valid remarks.(with minimum 15 characters and without html text)";
				alert_css = "alert-danger";
			}
			else if(termFlag=="" || termFlag.equals("") || !termFlag.equalsIgnoreCase("Y"))
			{
				alert_msg = "Please accept the decleration.";
				alert_css = "alert-danger";
			}
			else 
			{
			
				lcon = DBConnection.getConnection();
				lcon.setAutoCommit(false);
				
				
				int logStatus = partAWithoutProofInsertLog(lcon,request_id);					// log the request details
				
				lStrQuery = " UPDATE sadarem_without_proof_request_master \n"+
							" SET  req_status=?,status_remarks=?,terms_status=?,\n"+
							" terms_remarks=?,updated_by=?,System_IP_Address=?,updated_date=CURRENT_TIMESTAMP \n"+
							" WHERE request_id=?";
				
				preStm = lcon.prepareStatement(lStrQuery);
				int param=1;
	
				preStm.setString(param++,"R");					//R- Reject
				preStm.setString(param++,status_remarks);
				preStm.setString(param++,termFlag);
				preStm.setString(param++,CommonConstants.PART_A_DELARATION_NOTE); 
				preStm.setString(param++,author);
				preStm.setString(param++,CommonUtility.getClientIPAddress(request));
				preStm.setString(param++,request_id);
				
				if(preStm.executeUpdate()>0 && logStatus>0)
				{
					alert_msg = "Your have successfully Rejected the Request Id : "+request_id;
					alert_css = "alert-success";
					lcon.commit();
				}
				else
				{
					alert_msg = "Request for rejecting Request Id : "+request_id+" failed. Please try again.!";
					alert_css = "alert-danger";
					lcon.rollback();
				}
				preStm.close();
				lcon.close();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			alert_msg = "We are not able to process your request. Please try again!";
			alert_css = "alert-danger";
		}
		finally
		{
			DBConnection.closeStatement(preStm);
			DBConnection.closeConnection(lcon);
		}

		request.setAttribute("alert_msg", alert_msg);
		request.setAttribute("alert_css", alert_css);
	}
	
	
	public boolean logPartARequestApproval(HttpServletRequest request,String request_id,String district_id,String mandal_id,String village_id,String status_remarks,String termFlag,String profile_pic_path,String author)
	{
		lcon = null;
		PreparedStatement preStm = null;
		String alert_msg = "",alert_css = "";
		
		boolean status = false;
		
		try
		{
			if(request_id==null || request_id.length()==0)
			{ 
				alert_msg = "Invalid Request Id. Please try again.";
				alert_css = "alert-danger";
			}
			else if(status_remarks==null || status_remarks.length()<15 || CommonValidators.htmlStrValidation(status_remarks)==false)
			{
				alert_msg = "Please enter valid remarks.(with minimum 15 characters and without html text)";
				alert_css = "alert-danger";
			}
			else if(termFlag=="" || termFlag.equals("") || !termFlag.equalsIgnoreCase("Y"))
			{
				alert_msg = "Please accept the decleration.";
				alert_css = "alert-danger";
			}
			else if(district_id!=null && !district_id.equals("") && mandal_id!=null && !mandal_id.equals("") && village_id!=null && !village_id.equals(""))
			{    
			
				CommonDAO comObj = new CommonDAOImpl();
				
				String newSadaremId = comObj.getNewSADAREMID(district_id, mandal_id, village_id);
				
				if(!newSadaremId.equals("") && newSadaremId.length()==17)
				{
				
						lcon = DBConnection.getConnection();
						lcon.setAutoCommit(false);
						
						
						String reqProfilePicPath 		= CommonConstants.PARTA_ATTACHMENT_PATH+profile_pic_path;								// Profile Pic of request existing
						String sadaremProfilePicPath 	= CommonConstants.SADAREM_DOCUMENTS_PATH+newSadaremId+"\\Profile\\Profilepic.jpg";		// Copy the profile pic from request id director
						
						
						
						boolean logSadaremDtls	= logPartADetailsinSADAREMPartADetails(lcon, newSadaremId, request_id);
						boolean logSadaremPhoto = false;
						
						File directorytemp = new File(CommonConstants.SADAREM_DOCUMENTS_PATH+newSadaremId+"/Profile/");
		                if (!directorytemp.exists())
		                {
		                    directorytemp.mkdirs();
		                    logSadaremPhoto = CommonUtility.copyFileAndDelete(new File(reqProfilePicPath), new File(sadaremProfilePicPath), false, false);
		                }
						
						
						lStrQuery = " UPDATE sadarem_without_proof_request_master \n"+
									" SET  sadarem_id=?,req_status=?,status_remarks=?,terms_status=?,\n"+
									" terms_remarks=?,updated_by=?,System_IP_Address=?,updated_date=CURRENT_TIMESTAMP \n"+
									" WHERE request_id=?";
						
						preStm = lcon.prepareStatement(lStrQuery);
						int param=1;

						preStm.setString(param++,newSadaremId);			
						preStm.setString(param++,"A");					//A- Approve
						preStm.setString(param++,status_remarks);
						preStm.setString(param++,termFlag);
						preStm.setString(param++,CommonConstants.PART_A_DELARATION_NOTE); 
						preStm.setString(param++,author);
						preStm.setString(param++,CommonUtility.getClientIPAddress(request));
						preStm.setString(param++,request_id);
						
						if(preStm.executeUpdate()>0 && logSadaremDtls==true && logSadaremPhoto==true)
						{
							status = true;
							alert_msg = "Your have successfully approved Request Id : "+request_id;
							alert_css = "alert-success";
							lcon.commit();
						}
						else
						{
							status = false;
							alert_msg = "Failed to approve Request Id : "+request_id+". Please try again.!";
							alert_css = "alert-danger";
							lcon.rollback();
						}
						preStm.close();
						lcon.close();
				}
				else
				{
					status = false;
					alert_msg = "01) Sorry we are not able to process your request. Please try again.";
					alert_css = "alert-danger";
				}
			}
			else
			{
				status = false;
				alert_msg = "02) Sorry we are not able to process your request. Please try again.";
				alert_css = "alert-danger";
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			alert_msg = "03) We are not able to process your request. Please try again!";
			alert_css = "alert-danger";
			status = false;
		}
		finally
		{
			DBConnection.closeStatement(preStm);
			DBConnection.closeConnection(lcon);
		}

		request.setAttribute("alert_msg", alert_msg);
		request.setAttribute("alert_css", alert_css);
		
		return status;
	}
	
	
	private boolean logPartADetailsinSADAREMPartADetails(Connection con,String sadarem_id,String request_id)
	{  
		boolean status = false;
		PreparedStatement lPstmt=null;
	  
		try
		{ 
			
			lStrQuery="INSERT INTO tblperson_personal_details_new (  "
					+ "Person_Code,Reference_Form_Number,Form_Fill_Date, Surname,First_Name,Gender,Date_of_Birth,"
					+ "Age_Years, Religion,Caste,Marital_Status,Education,Employment,Relationship,Relation_Name,"
					+ "RationCard_Type,RationCard_Number,EPIC_Card,EPICCard_No,Pension_Card,Pension_Type,PensionCard_No, "
					+ "Mole_One,Mole_Two, House_Number,Phone_No,Email,District_ID,Mandal_ID,Village_ID,Habitation_ID,"
					+ "	Pin_Code,Personname_Telugu,Fathername_Telugu, Person_Status, Parents_Marriage, Typeof_Disability,  "
					+ "Existing_Percentage, Login_ID,System_IP_Address,Created_Date,Updated_Date, Status, View_Edit_Mode,"
					+ "PartA_LoginID, Camp_ID, PensionPhase, PartB_CampID,ReasonforPersonStatus, HabCode, CategoryId,CategoryCount,"
					+ "RationCard_Slno,DistrictID,MandalID,VillageID,HabtationID, proof_id,proofdoc_type) "
					
					
					+ "select ?,Reference_Form_Number,Form_Fill_Date,UPPER(Surname),UPPER(First_Name),Gender_ID,Date_of_Birth,DATEDIFF(hour,Date_of_Birth,GETDATE())/8766,Religion_ID,Caste_ID,Marital_Status_ID,"
					+ "Education_ID,Employment_ID,Relationship_ID, UPPER(Relation_Name),RationCard_Type,RationCard_Number,EPIC_Card,EPICCard_No,Pension_Card,"
					+ "Pension_Type,PensionCard_No,Mole_One,Mole_Two,House_Number,Phone_No,Email,District_ID,Mandal_ID,Village_ID,Habitation_ID,"
					+ "Pin_Code,Personname_Telugu,Relationname_Telugu,'Eligible',Parents_Marriage,null,null,created_by,System_IP_Address,getdate(),"
					+ "Updated_Date,'Active','Edit',created_by,Camp_ID,'PhaseIII',null,'Live',HabCode,'1','1',rationcard_slno,District_ID,Mandal_ID,"
					+ "Village_ID,Habitation_ID,Proof_Id,Proofdoc_Type from Sadarem_without_proof_request_master(nolock) where request_id=?";		
			
				lPstmt=con.prepareStatement(lStrQuery);
				
				lPstmt.setString(1,sadarem_id);//Person_Code
				lPstmt.setString(2, request_id);//requestID
						
			 
			
			 	if(lPstmt.executeUpdate()==1)
				{
			 		lPstmt.close();
			 		lPstmt = null;
			 		
			 		lStrQuery="INSERT INTO tblperson_personal_details (  "
							+ "Person_Code,Reference_Form_Number,Form_Fill_Date, Surname,First_Name,Gender,Date_of_Birth,"
							+ "Age_Years, Religion,Caste,Marital_Status,Education,Employment,Relationship,Relation_Name,"
							+ "RationCard_Type,RationCard_Number,EPIC_Card,EPICCard_No,Pension_Card,Pension_Type,PensionCard_No, "
							+ "Mole_One,Mole_Two, House_Number,Phone_No,Email,District_ID,Mandal_ID,Village_ID,Habitation_ID,"
							+ "	Pin_Code,Personname_Telugu,Fathername_Telugu, Person_Status, Parents_Marriage, Typeof_Disability,  "
							+ "Existing_Percentage, Login_ID,System_IP_Address,Created_Date,Updated_Date, Status, View_Edit_Mode,"
							+ "PartA_LoginID, Camp_ID, PensionPhase, PartB_CampID,ReasonforPersonStatus, HabCode, CategoryId,CategoryCount,"
							+ "RationCard_Slno,DistrictID,MandalID,VillageID,HabtationID, proof_id,proofdoc_type) "
							
							
							+ "select ?,Reference_Form_Number,Form_Fill_Date,UPPER(Surname),UPPER(First_Name),Gender_ID,Date_of_Birth,DATEDIFF(hour,Date_of_Birth,GETDATE())/8766,Religion_ID,Caste_ID,Marital_Status_ID,"
							+ "Education_ID,Employment_ID,Relationship_ID, UPPER(Relation_Name),RationCard_Type,RationCard_Number,EPIC_Card,EPICCard_No,Pension_Card,"
							+ "Pension_Type,PensionCard_No,Mole_One,Mole_Two,House_Number,Phone_No,Email,District_ID,Mandal_ID,Village_ID,Habitation_ID,"
							+ "Pin_Code,Personname_Telugu,Relationname_Telugu,'Eligible',Parents_Marriage,null,null,created_by,System_IP_Address,getdate(),"
							+ "Updated_Date,'Active','Edit',created_by,Camp_ID,'PhaseIII',null,'Live',HabCode,'1','1',rationcard_slno,District_ID,Mandal_ID,"
							+ "Village_ID,Habitation_ID,Proof_Id,Proofdoc_Type from Sadarem_without_proof_request_master(nolock) where request_id=?";		
					
					
						lPstmt=con.prepareStatement(lStrQuery);
						
						lPstmt.setString(1,sadarem_id);//Person_Code
						lPstmt.setString(2, request_id);//requestID
								 
						if(lPstmt.executeUpdate()==1)
						{
							status = true;
						}
						else
						{
							status = false;
						}
						 
						lPstmt.close();
				}
			}
			catch(Exception e)
			{
				status = false;
				e.printStackTrace(); 
			}
			finally
			{
				DBConnection.closeStatement(lPstmt);
			}			
			return status;			
		
	}
	
	
	private boolean logPartAUpdatedDetails
	(
		Connection con,String request_id,String reference_form_number,String form_fill_date,
		String mandal_id,String panchayath_id,String village_id,String habitation_id,String habcode,String house_number,
		String pin_code,String phone_no,String email,String surname,String first_name,String personname_telugu,
		String date_of_birth,String gender_id,String relationship_id,String relation_name,String relationname_telugu,
		String education_id,String employment_id,String marital_status_id,String caste_id,String religion_id,
		String rationcard_number,String rationcard_type,String rationcard_slno,String mole_one,String mole_two,
		boolean is_pension_exist,String pension_type,String pensioncard_no,String parents_marriage,
		boolean is_epiccard_exist,String epiccard_no,String user_ip_address,String author
	)
	{
		boolean status = false;
		PreparedStatement preStm = null;
		try
		{
			lStrQuery = 
					" UPDATE sadarem_without_proof_request_master SET \n"+ 
					" reference_form_number=?,mandal_id=?, \n"+
					" panchayath_id=?,village_id=?,habitation_id=?,habcode=?,gender_id=?,religion_id=?, \n"+
					" caste_id=?,marital_status_id=?,education_id=?,employment_id=?,surname=?,first_name=?, \n"+
					" personname_telugu=?,date_of_birth=CONVERT(DATE,?,103),relationship_id=?,relation_name=?,relationname_telugu=?, \n"+
					" mole_one=?,mole_two=?,house_number=?,pin_code=?,phone_no=?,email=?,parents_marriage=?, \n"+
					" rationcard_type=?,rationcard_slno=?,rationcard_number=?,epic_card=?,epiccard_no=?,pension_card=?, \n"+
					" pension_type=?,pensioncard_no=?,form_fill_date=CONVERT(DATE,?,103),\n"+
					" system_ip_address=?,updated_by=?,updated_date=CURRENT_TIMESTAMP WHERE request_id=? ";
						
					preStm = lcon.prepareStatement(lStrQuery);
					
					int param =1;
					 
					preStm.setString(param++,reference_form_number); 
					preStm.setString(param++,mandal_id);
					preStm.setString(param++,panchayath_id);
					preStm.setString(param++,village_id);
					preStm.setString(param++,habitation_id);
					preStm.setString(param++,habcode);
					preStm.setString(param++,gender_id);
					preStm.setString(param++,religion_id);
					preStm.setString(param++,caste_id);
					preStm.setString(param++,marital_status_id);
					preStm.setString(param++,education_id);
					preStm.setString(param++,employment_id);
					preStm.setString(param++,surname.trim().toUpperCase());
					preStm.setString(param++,first_name.trim().toUpperCase());
					preStm.setString(param++,personname_telugu);
					preStm.setString(param++,date_of_birth);
					preStm.setString(param++,relationship_id);
					preStm.setString(param++,relation_name.trim().toUpperCase());
					preStm.setString(param++,relationname_telugu);
					preStm.setString(param++,mole_one);
					preStm.setString(param++,mole_two);
					preStm.setString(param++,house_number);
					preStm.setString(param++,pin_code);
					preStm.setString(param++,phone_no);
					preStm.setString(param++,email);
					preStm.setString(param++,parents_marriage);
					if(rationcard_type!=null && !rationcard_type.equals("") && !rationcard_type.equals("-1") && Integer.parseInt(rationcard_type)>0)
					{
						preStm.setString(param++,rationcard_type);
					}
					else
					{
						preStm.setNull(param++,Types.NULL);
					}
					
					if(!rationcard_slno.equals("") && rationcard_slno.length()>0)
					{
						preStm.setString(param++,rationcard_slno);
					}
					else
					{
						preStm.setNull(param++,Types.NULL);
					}
					
					if(!rationcard_number.equals("") && rationcard_number.length()>0)
					{
						preStm.setString(param++,rationcard_number);
					}
					else
					{
						preStm.setNull(param++,Types.NULL);
					} 
					
					preStm.setBoolean(param++,is_epiccard_exist);
					
					if(!epiccard_no.equals("") && epiccard_no.length()>0)
					{
						preStm.setString(param++,epiccard_no);
					}
					else
					{
						preStm.setNull(param++,Types.NULL);
					}
					
					
					preStm.setBoolean(param++,is_pension_exist);
					
					if(pension_type!=null && !pension_type.equals("") && !pension_type.equals("-1"))
					{
						preStm.setString(param++,pension_type);
					}
					else
					{
						preStm.setNull(param++,Types.NULL);
					}
					 
					if(!pensioncard_no.equals("") && pensioncard_no.length()>0)
					{
						preStm.setString(param++,pensioncard_no);
					}
					else
					{
						preStm.setNull(param++,Types.NULL);
					}
					
					preStm.setString(param++,form_fill_date);    
					preStm.setString(param++,user_ip_address);
					preStm.setString(param++,author); 
					preStm.setString(param++,request_id);
					
					if(preStm.executeUpdate()>0)
					{
						status=true;
					}
					else
					{
						status=false;
					}
					preStm.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status=false;
		}
		finally
		{
			DBConnection.closeStatement(preStm);
		}
		
		return status;
	}
	
	
	
	public ArrayList getPartARequestStatusAtGlance(String start_date,String end_date)
	{
		ArrayList resultList = new ArrayList();
		try
		{
			
			ArrayList paramList = new ArrayList();
			ArrayList tempList	= new ArrayList();
			
			lStrQuery =  
						"SELECT ISNULL(a.district_id,99) district_id,ISNULL(a.district_name,'Total') district_name,\n" +
						"ISNULL(a.camp_name,'-') camp_name,\n" + 
						"SUM(ISNULL(pending_count,0)) pending_count,\n" + 
						"SUM(ISNULL(approved_count,0)) approved_count,\n" + 
						"SUM(ISNULL(rejected_count,0)) rejected_count,\n" + 
						"SUM(ISNULL(deleted_count,0)) deleted_count,\n" + 
						"SUM(ISNULL(total_count,0)) total_count, \n"+
						" SUM(ISNULL(total_pending,0)) total_pending \n"+
						"FROM\n" + 
						"(\n" + 
						"SELECT c.camp_id,c.name camp_name,c.district_id,d.district_name\n" + 
						"FROM tblcamp_details c WITH(NOLOCK),\n" + 
						"tbldistrict_details d WITH(NOLOCK)\n" + 
						"WHERE c.status='Active' AND\n" + 
						"c.district_id=d.district_id\n" + 
						") a LEFT JOIN\n" + 
						"(\n" + 
						"SELECT camp_id,district_id,\n" + 
						"SUM(CASE WHEN v.req_status='P' THEN 1 ELSE 0 END) pending_count,\n" + 
						"SUM(CASE WHEN v.req_status='A' THEN 1 ELSE 0 END) approved_count,\n" + 
						"SUM(CASE WHEN v.req_status='R' THEN 1 ELSE 0 END) rejected_count,\n" + 
						"SUM(CASE WHEN v.req_status='D' THEN 1 ELSE 0 END) deleted_count,\n" + 
						"COUNT(*) total_count \n"+
						" FROM sadarem_without_proof_request_master v WITH(NOLOCK)\n" + 
						"WHERE\n" + 
						"(CONVERT(DATE,v.created_date,103) BETWEEN  CONVERT(DATE,?,103) AND  CONVERT(DATE,?,103)) OR\n" + 
						"(CONVERT(DATE,v.updated_date,103) BETWEEN  CONVERT(DATE,?,103) AND  CONVERT(DATE,?,103))\n" + 
						"GROUP BY camp_id,district_id\n" + 
						") r ON (a.camp_id=r.camp_id AND a.district_id=r.district_id)\n" + 
						" LEFT JOIN (SELECT camp_id,district_id,COUNT(*) total_pending \n "+
						" FROM sadarem_without_proof_request_master WITH(NOLOCK) WHERE req_status='P' \n"+
						" GROUP BY camp_id,district_id) req \n"+
						" ON (a.camp_id=req.camp_id AND a.district_id=req.district_id) \n"+
						"GROUP BY GROUPING SETS ((a.district_id,district_name,camp_name),())\n" + 
						"ORDER BY district_id,camp_name desc";
 

							tempList = new ArrayList();
							tempList.add("S");
							tempList.add(start_date);
							paramList.add(tempList);
		
							tempList = new ArrayList();
							tempList.add("S");
							tempList.add(end_date);
							paramList.add(tempList);
							 
		
							tempList = new ArrayList();
							tempList.add("S");
							tempList.add(start_date);
							paramList.add(tempList);
		
							tempList = new ArrayList();
							tempList.add("S");
							tempList.add(end_date);
							paramList.add(tempList);
							
							//System.out.println("lStrQuery : \n"+lStrQuery+"\nParamList : "+paramList);
							
							resultList = DataAccess.pickDataByPrepareStmtArrayListHashMap(lStrQuery, paramList);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	
	public boolean getCheckStatusofRequestExistByFNameSNameDOB(String first_name,String sur_name,String date_of_birth)
	{
		boolean status = true;
		try
		{
			ArrayList paramList = new ArrayList();
			ArrayList tempList  = new ArrayList();
			
			
			lStrQuery = "SELECT SUM(mycount) mycount \n"+
						" FROM ( \n"+
						" SELECT COUNT(*) mycount \n"+
						" FROM tblperson_personal_details WITH(NOLOCK) \n"+
						" WHERE \n"+
						" SOUNDEX(first_name)=SOUNDEX(?) AND \n"+
						" SOUNDEX(surname)=SOUNDEX(?) AND \n"+
						" CONVERT(DATE,date_of_birth,103)=CONVERT(DATE,?,103) \n"+
						" UNION ALL \n"+
						" SELECT COUNT(*) mycount \n"+
						" FROM sadarem_without_proof_request_master WITH(NOLOCK) \n"+
						" WHERE \n"+
						" SOUNDEX(first_name)=SOUNDEX(?) AND \n"+
						" SOUNDEX(surname)=SOUNDEX(?) AND \n"+
						" CONVERT(DATE,date_of_birth,103)=CONVERT(DATE,?,103) \n"+
						" )a";
			
						tempList  = new ArrayList();
						tempList.add("S");
						tempList.add(first_name);
						paramList.add(tempList);
						
						tempList  = new ArrayList();
						tempList.add("S");
						tempList.add(sur_name);
						paramList.add(tempList);
						
						tempList  = new ArrayList();
						tempList.add("S");
						tempList.add(date_of_birth);
						paramList.add(tempList);
						
						tempList  = new ArrayList();
						tempList.add("S");
						tempList.add(first_name);
						paramList.add(tempList);
						
						tempList  = new ArrayList();
						tempList.add("S");
						tempList.add(sur_name);
						paramList.add(tempList);
						
						tempList  = new ArrayList();
						tempList.add("S");
						tempList.add(date_of_birth);
						paramList.add(tempList);
						
						
						
						int result = Integer.parseInt(DataAccess.getReturnResultByPstmt(lStrQuery, paramList));
						System.out.println("result : "+result);
						if(result>0)
						{
							status=true;
						}
						else
						{
							status=false;
						}
		}
		catch(Exception e)
		{
			status=true;
			e.printStackTrace();
			log.error(e);
		}
		
		return status;
	}
	
	
	
	
	
	
	
	
	
	
	
	public int updatePartADtls(HashMap basicdtls)
	{
		ArrayList resultList = new ArrayList();
		ArrayList paramList = new ArrayList();
		ArrayList tempList	= new ArrayList();
		
		PreparedStatement lPstmt = null;
		
		String statusMsg = "";
		ArrayList Dtls = new ArrayList();
		Connection con = null;
		int result=0;
		CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
		try
		{
			   
			  // PreparedStatement lPstmt = null;
			    
	    	lStrQuery =  "select request_id,proof_id from sadarem_without_proof_request_master(nolock) where request_id=? and proof_id=?";
		
    		tempList.add("S");
    		tempList.add((String)(basicdtls.get("request_id").toString()));
    		paramList.add(tempList);
    		
    		tempList = new ArrayList();
    		tempList.add("S");
    		tempList.add((String)(basicdtls.get("aadharCardNo").toString()));
    		paramList.add(tempList);
	    	
	    	
    		Dtls = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		   if(Dtls.size() > 0)
		   {
			   con = DBConnection.getConnection();
		       con.setAutoCommit(false);
				   
		       result=partAWithoutProofInsertLog(con, (String)(basicdtls.get("request_id").toString()));
	   
				   if(result>0)
				   {
		
				    	 lStrQuery =  "update 	sadarem_without_proof_request_master set "
				    	 		+ "Reference_Form_Number = ?"
				    	 		+ ",District_ID = ?"
				    	 		+ ",Mandal_ID = ?"
				    	 		+ ",Panchayath_ID = ?"
				    	 		+ ",Village_ID = ?"
				    	 		+ ",Habitation_ID = ?"
				    	 		+ ",HabCode = ?"
				    	 		+ ",Gender_ID = ?"
				    	 		+ ",Religion_ID = ?"
				    	 		+ ",Caste_ID = ?"
				    	 		+ ",Marital_Status_ID = ?"
				    	 		+ ",Education_ID = ?"
				    	 		+ ",Employment_ID = ?"
				    	 		+ ",Surname = ?"
				    	 		+ ",First_Name = ?"
				    	 		+ ",Personname_Telugu = ?"
				    	 		+ ",Date_of_Birth = ?"
				    	 		+ ",Relationship_ID = ?"
				    	 		+ ",Relation_Name = ?"
				    	 		+ ",Relationname_Telugu = ?"
				    	 		+ ",Mole_One = ?"
				    	 		+ ",Mole_Two = ?"
				    	 		+ ",House_Number = ?"
				    	 		+ ",Pin_Code = ?"
				    	 		+ ",Phone_No = ?"
				    	 		+ ",Email = ?"
				    	 		+ ",Parents_Marriage = ?"
				    	 		+ ",RationCard_Type = ?"
				    	 		+ ",rationcard_slno = ?"
				    	 		+ ",RationCard_Number = ?"
				    	 		+ ",EPIC_Card = ?"
				    	 		+ ",EPICCard_No = ?"
				    	 		+ ",Pension_Card = ?"
				    	 		+ ",Pension_Type = ?"
				    	 		+ ",PensionCard_No = ?"
				    	 		+ ",Form_Fill_Date = ?"
				    	 		+ ",Camp_ID = ?"
				    	 		+ ",Proof_Id = ?"
				    	 		+ ",Proofdoc_Type = ?"
				    	 		+ ",updated_by = ?"
				    	 		+ ",Updated_Date	 = getdate()  where request_id=? and proof_id=?";
						   lPstmt = con.prepareStatement(lStrQuery);
						   
						   lPstmt.setString(1,(String)(basicdtls.get("formno").toString()) );
						   lPstmt.setString(2,(String)(basicdtls.get("district_id").toString()) );
						   lPstmt.setString(3,(String)(basicdtls.get("mandal_id").toString()) );
						   lPstmt.setString(4,(String)(basicdtls.get("panchayat_id").toString()) );
						   lPstmt.setString(5,(String)(basicdtls.get("village_id").toString()) );
						   lPstmt.setString(6,(String)(basicdtls.get("habitation_id").toString()) );
						   lPstmt.setString(7,(String)(basicdtls.get("habcode").toString()) );
						   lPstmt.setString(8,(String)(basicdtls.get("gender").toString()) );
						   lPstmt.setString(9,(String)(basicdtls.get("religion").toString()) );
						   lPstmt.setString(10,(String)(basicdtls.get("caste").toString()) );
						   lPstmt.setString(11,(String)(basicdtls.get("marital").toString()) );
						   lPstmt.setString(12,(String)(basicdtls.get("education").toString()) );
						   lPstmt.setString(13,(String)(basicdtls.get("employment").toString()) );
						   lPstmt.setString(14,(String)(basicdtls.get("surName").toString()) );
						   lPstmt.setString(15,(String)(basicdtls.get("firstName").toString()) );
						   lPstmt.setString(16,(String)(basicdtls.get("telugupersonname").toString()) );
						   lPstmt.setString(17,(String)(basicdtls.get("dob").toString()) );
						   lPstmt.setString(18,(String)(basicdtls.get("relationType").toString()) );
						   lPstmt.setString(19,(String)(basicdtls.get("relationName").toString()) );
						   lPstmt.setString(20,(String)(basicdtls.get("telugufathername").toString()) );
						   lPstmt.setString(21,(String)(basicdtls.get("mole1").toString()) );
						   lPstmt.setString(22,(String)(basicdtls.get("mole2").toString()) );
						   lPstmt.setString(23,(String)(basicdtls.get("houseNumber").toString()) );
						   lPstmt.setString(24,(String)(basicdtls.get("PinCode").toString()) );
						   lPstmt.setString(25,(String)(basicdtls.get("contid").toString()) );
						   lPstmt.setString(26,(String)(basicdtls.get("email").toString()) );
						   lPstmt.setString(27,(String)(basicdtls.get("parents_marriage").toString()) );
						   lPstmt.setString(28,(String)(basicdtls.get("rtype").toString()) );
						   lPstmt.setString(29,(String)(basicdtls.get("rationCardSlno").toString()) );
						   lPstmt.setString(30,(String)(basicdtls.get("Rcard").toString()) );
						   lPstmt.setString(31,(String)(basicdtls.get("epicradio").toString()) );
						   lPstmt.setString(32,(String)(basicdtls.get("epiccardno").toString()) );
						   lPstmt.setString(33,(String)(basicdtls.get("pensionradio").toString()) );
						   lPstmt.setString(34,(String)(basicdtls.get("pension_type").toString()) );
						   lPstmt.setString(35,(String)(basicdtls.get("pensioncardno").toString()) );
						   lPstmt.setString(36,(String)(basicdtls.get("formDate").toString()) );
						   lPstmt.setString(37,(String)(basicdtls.get("campId").toString()) );
						   lPstmt.setString(38,(String)(basicdtls.get("aadharCardNo").toString()) );
						   lPstmt.setString(39,(String)(basicdtls.get("proofdoctype").toString()) );
						   lPstmt.setString(40,(String)(basicdtls.get("loginid").toString()) );						   
						   lPstmt.setString(41,(String)(basicdtls.get("request_id").toString()) );
						   lPstmt.setString(42,(String)(basicdtls.get("aadharCardNo").toString()) );
						   
						     result = lPstmt.executeUpdate();
						     
						     if(result>0)
						     {
						    	 con.commit();
						     }
						     else
						     {
						    	 con.rollback();
						     }
					 						     
					   }
				   else
					   {
						   result=0;
						   con.rollback();
					   }
			  
			}
				 
		   
		   
		}
		catch(Exception e)
		{
			result=0;
			e.printStackTrace();
		}
		finally
		{
			   if(con!=null)
	           {
	                DBConnection.closeConnection(con);
	                DBConnection.closeStatement(lPstmt);
	                con=null;
	           }
													
		}
 
		
		
		return result;
	} 	
	
	
	
	
	
	

	
	public int partAWithoutProofCAReject(String adhaarId, String ReqId, String flag , String remarks,String login_id) 
	{
		String qry="";
		String ResulMSG="";
		Connection con = null;
		PreparedStatement pstmt=null;
		int result=0;
		try
		{
			con=DBConnection.getConnection();
			con.setAutoCommit(false);

			
			result=partAWithoutProofInsertLog(con,ReqId);
		
		 if(result>0)
		 {
			qry="UPDATE   Sadarem_without_proof_request_master    SET  req_status=? , status_remarks=?,updated_by=? \n"
					+ " ,updated_date=getdate() WHERE req_status=? AND request_id=? AND Proof_Id=?";

			pstmt=con.prepareStatement(qry);


			pstmt.setString(1, flag);
			pstmt.setString(2, remarks);
			pstmt.setString(3, login_id);
			pstmt.setString(4, "P");
			pstmt.setString(5, ReqId);
			pstmt.setString(6, adhaarId);

			 result=pstmt.executeUpdate();

			if(result==0)
			{
				//ResulMSG="The certificate rejection failed. Please try again after some time!";		
				con.rollback();				
			}
			else
			{
				//ResulMSG="The certificate rejected successfully.";
				con.commit();
				SendSMS(ReqId,login_id,"Rejected");
			}
		}
		 else
		{
			con.rollback();	
		}
		}
		catch(Exception e)
		{
			try {
				con.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
			ResulMSG="Error occured. Please try again after some time!";					 		
		}
		finally
		{
			   if(con!=null)
	           {
	                DBConnection.closeConnection(con);
	                con=null;
	           }										
		}

		return result;				
	}
	
	
	public int aproofPartAWithoutProofCA(String sadarem_id,String request_id ,String aadhaarID,String loginID,String districtName)
	{
		String qry="";
		Connection con = null;
		PreparedStatement lPstmt=null;
		int result=0;
		int res=0;
		
			try
			{
				con=DBConnection.getConnection();
				con.setAutoCommit(false);
				result = insertIntoPersonPersonalDtls( sadarem_id, request_id , aadhaarID, loginID, districtName);
				if(result == 1)
				{
	
					qry="UPDATE   Sadarem_without_proof_request_master    SET  sadarem_id=? , updated_by=? , req_status=?, updated_date=getdate()\n"
							+ " WHERE   request_id=? AND Proof_Id=?";
					lPstmt=con.prepareStatement(qry);
	
					lPstmt.setString(1, sadarem_id);//sadarem_id
					lPstmt.setString(2,loginID );//updated_by
					lPstmt.setString(3, "A");
					lPstmt.setString(4, request_id);
					lPstmt.setString(5, aadhaarID);
					
					result=lPstmt.executeUpdate();
					
					if(result==1)
					{
						
						//result=withoutproofuploadPhoto( aadhaarID, sadarem_id, districtName, request_id);
						 
						if(result>0)
						{
							con.commit();
							SendSMS(request_id,loginID,"Approved");
						}
						else
						{
							result=0;
							con.rollback();
						}
					}
					else
					{
						result=0;
						con.rollback();
					}
				
					
					
					
					
				}
				else
				{
					result=0;
					con.rollback();
				}
			}
			catch(Exception e)
			{
				result=0;
				e.printStackTrace();
								 		
			}
			finally
			{
				   if(con!=null)
		           {
		                DBConnection.closeConnection(con);
		                con=null;
		           }									
			}
		return result;
			
			/*qry="INSERT INTO tblperson_personal_details_new (  "
					+ "Person_Code,Reference_Form_Number,Form_Fill_Date, Surname,First_Name,Gender,Date_of_Birth,"
					+ "Age_Years, Religion,Caste,Marital_Status,Education,Employment,Relationship,Relation_Name,"
					+ "RationCard_Type,RationCard_Number,EPIC_Card,EPICCard_No,Pension_Card,Pension_Type,PensionCard_No, "
					+ "Mole_One,Mole_Two, House_Number,Phone_No,Email,District_ID,Mandal_ID,Village_ID,Habitation_ID,"
					+ "	Pin_Code,Personname_Telugu,Fathername_Telugu, Person_Status, Parents_Marriage, Typeof_Disability,  "
					+ "Existing_Percentage, Login_ID,System_IP_Address,Created_Date,Updated_Date, Status, View_Edit_Mode,"
					+ "PartA_LoginID, Camp_ID, PensionPhase, PartB_CampID,ReasonforPersonStatus, HabCode, CategoryId,CategoryCount,"
					+ "RationCard_Slno,DistrictID,MandalID,VillageID,HabtationID, proof_id,proofdoc_type) "
					
					
					+ "select ?,Reference_Form_Number,Form_Fill_Date,Surname,First_Name,Gender_ID,Date_of_Birth,DATEDIFF(hour,Date_of_Birth,GETDATE())/8766,Religion_ID,Caste_ID,Marital_Status_ID,"
					+ "Education_ID,Employment_ID,Relationship_ID, Relation_Name,RationCard_Type,RationCard_Number,EPIC_Card,EPICCard_No,Pension_Card,"
					+ "Pension_Type,PensionCard_No,Mole_One,Mole_Two,House_Number,Phone_No,Email,District_ID,Mandal_ID,Village_ID,Habitation_ID,"
					+ "Pin_Code,Personname_Telugu,Relationname_Telugu,'Eligible',Parents_Marriage,null,null,created_by,System_IP_Address,getdate(),"
					+ "Updated_Date,'Active','Edit',created_by,Camp_ID,'PhaseIII',null,'Live',HabCode,'1','1',rationcard_slno,District_ID,Mandal_ID,"
					+ "Village_ID,Habitation_ID,Proof_Id,Proofdoc_Type from Sadarem_without_proof_request_master(nolock) where request_id=?";		
			
				lPstmt=con.prepareStatement(qry);
				
				lPstmt.setString(1,sadarem_id);//Person_Code
				lPstmt.setString(2, request_id);//requestID
						
			
			 result=lPstmt.executeUpdate();
			
			if(result==1)
			{
				qry="INSERT INTO tblperson_personal_details (  "
						+ "Person_Code,Reference_Form_Number,Form_Fill_Date, Surname,First_Name,Gender,Date_of_Birth,"
						+ "Age_Years, Religion,Caste,Marital_Status,Education,Employment,Relationship,Relation_Name,"
						+ "RationCard_Type,RationCard_Number,EPIC_Card,EPICCard_No,Pension_Card,Pension_Type,PensionCard_No, "
						+ "Mole_One,Mole_Two, House_Number,Phone_No,Email,District_ID,Mandal_ID,Village_ID,Habitation_ID,"
						+ "	Pin_Code,Personname_Telugu,Fathername_Telugu, Person_Status, Parents_Marriage, Typeof_Disability,  "
						+ "Existing_Percentage, Login_ID,System_IP_Address,Created_Date,Updated_Date, Status, View_Edit_Mode,"
						+ "PartA_LoginID, Camp_ID, PensionPhase, PartB_CampID,ReasonforPersonStatus, HabCode, CategoryId,CategoryCount,"
						+ "RationCard_Slno,DistrictID,MandalID,VillageID,HabtationID, proof_id,proofdoc_type) "
						
						
						+ "select ?,Reference_Form_Number,Form_Fill_Date,Surname,First_Name,Gender_ID,Date_of_Birth,DATEDIFF(hour,Date_of_Birth,GETDATE())/8766,Religion_ID,Caste_ID,Marital_Status_ID,"
						+ "Education_ID,Employment_ID,Relationship_ID, Relation_Name,RationCard_Type,RationCard_Number,EPIC_Card,EPICCard_No,Pension_Card,"
						+ "Pension_Type,PensionCard_No,Mole_One,Mole_Two,House_Number,Phone_No,Email,District_ID,Mandal_ID,Village_ID,Habitation_ID,"
						+ "Pin_Code,Personname_Telugu,Relationname_Telugu,'Eligible',Parents_Marriage,null,null,created_by,System_IP_Address,getdate(),"
						+ "Updated_Date,'Active','Edit',created_by,Camp_ID,'PhaseIII',null,'Live',HabCode,'1','1',rationcard_slno,District_ID,Mandal_ID,"
						+ "Village_ID,Habitation_ID,Proof_Id,Proofdoc_Type from Sadarem_without_proof_request_master(nolock) where request_id=?";		
				
				
					lPstmt=con.prepareStatement(qry);
					
					lPstmt.setString(1,sadarem_id);//Person_Code
					lPstmt.setString(2, request_id);//requestID
							
					result=lPstmt.executeUpdate(); 
					
					if(result==1)
					{
						qry="UPDATE   Sadarem_without_proof_request_master    SET  sadarem_id=? , updated_by=? , req_status=?, updated_date=getdate()\n"
								+ " WHERE   request_id=? AND Proof_Id=?";
						lPstmt=con.prepareStatement(qry);

						lPstmt.setString(1, sadarem_id);//sadarem_id
						lPstmt.setString(2,loginID );//updated_by
						lPstmt.setString(3, "A");
						lPstmt.setString(4, request_id);
						lPstmt.setString(5, aadhaarID);
						
						result=lPstmt.executeUpdate();
						
						if(result==1)
						{
							
							//result=withoutproofuploadPhoto( aadhaarID, sadarem_id, districtName, request_id);
							 
							if(result>0)
							{
								con.commit();
								SendSMS(request_id,loginID,"Approved");
							}
							else
							{
								result=0;
								con.rollback();
							}
						}
						else
						{
							result=0;
							con.rollback();
						}
					
						
						
						
						
					}
					else
					{
						result=0;
						con.rollback();
					}
			}
			else
			{
				result=0;
				con.rollback();
				
			}
		}
		catch(Exception e)
		{
			result=0;
			e.printStackTrace();
							 		
		}
		finally
		{
			   if(con!=null)
	           {
	                DBConnection.closeConnection(con);
	                con=null;
	           }									
		}
		
		return result;	*/		
	}
	 public  void SendSMS(String reqId,String loginId,String rejectapprovemsg)  
		{		
			lStrQuery = " select t.proof_id,t.phone_no,t.request_id from sadarem_without_proof_request_master t(nolock) where t.request_id=? ";
			ArrayList tempList = new ArrayList();
			ArrayList paramList = new ArrayList();
			ArrayList issuedata = new ArrayList();
			tempList.add("S");
			tempList.add(reqId);
			paramList.add(tempList);
			try{		
			issuedata = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			for(int i=0;i<issuedata.size();i++)
			{
				tempList = (ArrayList) issuedata.get(i);
			}
			String MobNo = (String) tempList.get(1);
			String proofID = (String) tempList.get(0);
			String requestID = (String) tempList.get(2);
			CommonDAOImpl comObj = new CommonDAOImpl();
					
			String MsgDeliveredFlag="N";
			if (!MobNo.equals("") || !MobNo.equals("NULL") || !MobNo.equals("null") || !(MobNo.length()==0)) 
			{			
				boolean isInserted = insertRequestStatusbySMS(reqId,loginId,rejectapprovemsg);
				if (isInserted)
				{
					String message = "SADAREM::(REQUESTID)"+requestID+",(PROOFID)"+proofID+". Your Part-A Request has been "+rejectapprovemsg+" by Camp Admin";
					//System.out.println(message);
					if(!MobNo.equals("-") && !MobNo.equals(""))
					{					 
	 			 	   ArrayList smsResult = (ArrayList) SMSUtility.sendmysms(MobNo,message);
	 			 	 //ArrayList smsResult =	new ArrayList();
	 			 	 //smsResult.add("Y"); smsResult.add("Y"); 
						if (smsResult.size() > 0 && smsResult.get(0).toString().equals("Y"))
						{
							MsgDeliveredFlag="Y";
						}
						else
						{
							MsgDeliveredFlag="N";
						}
					}			
					
					if(MsgDeliveredFlag.equals("Y"))
					{
						UpdateisSentSMSFlag(reqId,"Y",MobNo,loginId);					
						//System.out.println("Msg sent in issue approved or rejected case"); 
					}				
				}			
			}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
		} 
	 public void UpdateisSentSMSFlag(String reqId,String flag,String MobNo,String loginId)
	 	{
			String returnMsg="N";
			String query="";
			boolean isrecordPresent=false;
			boolean commitFlag=false;
			int count=0;
			PreparedStatement pstmt=null;
			Connection con=null;
			ResultSet rs=null;
			try{
			if(!(CommonUtility.checkNullObj(reqId).equals("")))
			{
				query="SELECT COUNT(*) FROM sadarem_without_proof_sms_status_log WHERE request_id=?";
				con=DBConnection.getConnection();
				con.setAutoCommit(false);
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, reqId);
				rs=pstmt.executeQuery();
				while(rs.next())
				{
					count=Integer.parseInt(rs.getString(1));
				}
				if(count>0)
					isrecordPresent=true;
				if(isrecordPresent && flag.equalsIgnoreCase("Y"))
				{
					query=" UPDATE sadarem_without_proof_sms_status_log SET is_sent= 'Y', \n"+					  
						  " updated_date=CURRENT_TIMESTAMP,updated_by=? WHERE request_id=? \n";
				}
				if(isrecordPresent)
				{
					pstmt=null;
					count=0;
					pstmt=con.prepareStatement(query);
					pstmt.setString(1,loginId);
					pstmt.setString(2,reqId);
					count=pstmt.executeUpdate();
					if(count>=1)
					{
						//System.out.println("Success isSent Flag Updated");
						con.commit();
						returnMsg="Y";
						commitFlag=true;
					 }
				}		 
			}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		
		}
		public boolean insertRequestStatusbySMS(String reqID,String author,String msg)
		{
			boolean status= false;
			
			PreparedStatement pstmt=null;
			
			try
			{
				lcon=DBConnection.getConnection();
				
				lStrQuery=
						"INSERT INTO sadarem_without_proof_sms_status_log\n" +
								"(request_id,proof_id,status_flag,contact_no,sms_txt,is_sent,is_active,created_by,created_date)\n" + 
								"SELECT \n" + 
								"request_id,proof_id,req_status,\n" + 
								"ISNULL(Phone_no,'') contact_num,\n" + 
								"('Req Id:'+request_id+'. Status :'+?) sms_msg,\n" + 
								"'N' is_sent,'Y' is_active,? created_by,current_timestamp created_date\n" + 
								" FROM\n" + 
								"sadarem_without_proof_request_master with(nolock)\n" + 
									"WHERE\n" + 
								"request_id=? \n" ;
 
				
					pstmt=lcon.prepareStatement(lStrQuery);
				
					pstmt.setString(1,msg);
					pstmt.setString(2,author);
					pstmt.setString(3,reqID);
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
					System.out.println("fail in insertRequestStatusbySMS");
				 log.info("SQLException in WithoutProofPartADAOImpl @ insertRequestStatusbySMS :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
			 }
			 catch(Exception lexp)
			 {
					status = false;

					System.out.println("fail in insertRequestStatusbySMS");
				 log.info("Exception in WithoutProofPartADAOImpl @ insertRequestStatusbySMS : "+lexp.getLocalizedMessage());
			 }
			finally
			{
				DBConnection.closeStatement(pstmt);
				DBConnection.closeConnection(lcon);
			}
			
			return status;
		} 
		
		public int partAWithoutProofInsertLog(Connection con, String ReqId) 
		{ 
			PreparedStatement pstmt=null;
			int result=0;
			try
			{
			 

			lStrQuery = 
					"insert into sadarem_without_proof_request_master_log(request_id,sadarem_id,Reference_Form_Number,District_ID,Mandal_ID,Panchayath_ID \n"
					+ ",Village_ID,Habitation_ID,HabCode,Gender_ID,Religion_ID,Caste_ID,Marital_Status_ID,Education_ID,Employment_ID,Surname,First_Name,Personname_Telugu \n"
					+ ",Date_of_Birth,Relationship_ID,Relation_Name,Relationname_Telugu,Mole_One,Mole_Two,House_Number,Pin_Code,Phone_No,Email,Parents_Marriage \n"
					+ ",RationCard_Type,rationcard_slno,RationCard_Number,EPIC_Card,EPICCard_No,Pension_Card,Pension_Type,PensionCard_No,Form_Fill_Date,Camp_ID \n"
					+ ",Proof_Id,Proofdoc_Type,req_status,status_remarks,System_IP_Address,created_by,Created_Date,updated_by \n"
					+ ",Updated_Date,profile_pic_path,terms_status,terms_remarks) \n"
					+ ""
					+ " select request_id,sadarem_id,Reference_Form_Number,District_ID,Mandal_ID,Panchayath_ID \n"
					+ ",Village_ID,Habitation_ID,HabCode,Gender_ID,Religion_ID,Caste_ID,Marital_Status_ID,Education_ID,Employment_ID,Surname,First_Name,Personname_Telugu \n"
					+ ",Date_of_Birth,Relationship_ID,Relation_Name,Relationname_Telugu,Mole_One,Mole_Two,House_Number,Pin_Code,Phone_No,Email,Parents_Marriage \n"
					+ ",RationCard_Type,rationcard_slno,RationCard_Number,EPIC_Card,EPICCard_No,Pension_Card,Pension_Type,PensionCard_No,Form_Fill_Date,Camp_ID \n"
					+ ",Proof_Id,Proofdoc_Type,req_status,status_remarks,System_IP_Address,created_by,Created_Date,updated_by  \n"
					+ ",Updated_Date,profile_pic_path,terms_status,terms_remarks from  sadarem_without_proof_request_master(nolock) where request_id=? \n"	;
				
					pstmt=con.prepareStatement(lStrQuery);
						
					pstmt.setString(1, ReqId.trim());
					
					 result=pstmt.executeUpdate();
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return result;
		}
		
		 public int withoutproofuploadPhoto(String proof_id,String personcode,String requestID) 
		 {
			 int status=0;
			 String photoPath="";
			 
			 photoPath = CommonConstants.PARTA_ATTACHMENT_PATH+requestID+"/"+proof_id+".jpg";
			 
			 String fileName = "Profilepic" + ".JPG";
			 
	         String strDirectoytemp = CommonConstants.SADAREM_DOCUMENTS_PATH+personcode+"\\Profile\\";
	         File empImage = new File(photoPath);
	         try
	         {
				 FileInputStream instream ;
			     File tempdir = new File(strDirectoytemp);
			     final byte buffer[] = new byte[(int) empImage.length()];
			     
			     instream = new FileInputStream(empImage);
			     instream.read(buffer);
	             if (!tempdir.exists())
	             {
	                 tempdir.mkdirs();
	             }
	             /*File tempdistdir = new File(tempdir + "/" + districtName);
	             if (!tempdistdir.exists())
	             {
	                 tempdistdir.mkdir();
	             }*/
	               

	             BufferedImage imag = ImageIO.read(new ByteArrayInputStream(buffer));
	             ImageIO.write(imag, "jpg", new File(tempdir, "Profilepic.JPG"));
	             status=1;
	             if(instream!=null)
	        	 {
	        		 instream.close();
	             }
	        	 
	         } 
	         catch (Exception e) 
	         {
				e.printStackTrace();
				status = 0;
				 }
	         
			 return status;
		 }
		 
		 public int insertIntoPersonPersonalDtls(String sadarem_id,String request_id ,String aadhaarID,String loginID,String districtName)
			{
				String qry="";
				Connection con = null;
				PreparedStatement lPstmt=null;
				int result=0;
				int res=0;
				
				try
				{
					con=DBConnection.getConnection();
					con.setAutoCommit(false);
					
					qry="INSERT INTO tblperson_personal_details_new (  "
							+ "Person_Code,Reference_Form_Number,Form_Fill_Date, Surname,First_Name,Gender,Date_of_Birth,"
							+ "Age_Years, Religion,Caste,Marital_Status,Education,Employment,Relationship,Relation_Name,"
							+ "RationCard_Type,RationCard_Number,EPIC_Card,EPICCard_No,Pension_Card,Pension_Type,PensionCard_No, "
							+ "Mole_One,Mole_Two, House_Number,Phone_No,Email,District_ID,Mandal_ID,Village_ID,Habitation_ID,"
							+ "	Pin_Code,Personname_Telugu,Fathername_Telugu, Person_Status, Parents_Marriage, Typeof_Disability,  "
							+ "Existing_Percentage, Login_ID,System_IP_Address,Created_Date,Updated_Date, Status, View_Edit_Mode,"
							+ "PartA_LoginID, Camp_ID, PensionPhase, PartB_CampID,ReasonforPersonStatus, HabCode, CategoryId,CategoryCount,"
							+ "RationCard_Slno,DistrictID,MandalID,VillageID,HabtationID, proof_id,proofdoc_type) "
							
							
							+ "select ?,Reference_Form_Number,Form_Fill_Date,Surname,First_Name,Gender_ID,Date_of_Birth,DATEDIFF(hour,Date_of_Birth,GETDATE())/8766,Religion_ID,Caste_ID,Marital_Status_ID,"
							+ "Education_ID,Employment_ID,Relationship_ID, Relation_Name,RationCard_Type,RationCard_Number,EPIC_Card,EPICCard_No,Pension_Card,"
							+ "Pension_Type,PensionCard_No,Mole_One,Mole_Two,House_Number,Phone_No,Email,District_ID,Mandal_ID,Village_ID,Habitation_ID,"
							+ "Pin_Code,Personname_Telugu,Relationname_Telugu,'Eligible',Parents_Marriage,null,null,created_by,System_IP_Address,getdate(),"
							+ "Updated_Date,'Active','Edit',created_by,Camp_ID,'PhaseIII',null,'Live',HabCode,'1','1',rationcard_slno,District_ID,Mandal_ID,"
							+ "Village_ID,Habitation_ID,Proof_Id,Proofdoc_Type from Sadarem_without_proof_request_master(nolock) where request_id=?";		
					
						lPstmt=con.prepareStatement(qry);
						
						lPstmt.setString(1,sadarem_id);//Person_Code
						lPstmt.setString(2, request_id);//requestID
								
					
					 result=lPstmt.executeUpdate();
					
					 	if(result==1)
						{
							qry="INSERT INTO tblperson_personal_details (  "
									+ "Person_Code,Reference_Form_Number,Form_Fill_Date, Surname,First_Name,Gender,Date_of_Birth,"
									+ "Age_Years, Religion,Caste,Marital_Status,Education,Employment,Relationship,Relation_Name,"
									+ "RationCard_Type,RationCard_Number,EPIC_Card,EPICCard_No,Pension_Card,Pension_Type,PensionCard_No, "
									+ "Mole_One,Mole_Two, House_Number,Phone_No,Email,District_ID,Mandal_ID,Village_ID,Habitation_ID,"
									+ "	Pin_Code,Personname_Telugu,Fathername_Telugu, Person_Status, Parents_Marriage, Typeof_Disability,  "
									+ "Existing_Percentage, Login_ID,System_IP_Address,Created_Date,Updated_Date, Status, View_Edit_Mode,"
									+ "PartA_LoginID, Camp_ID, PensionPhase, PartB_CampID,ReasonforPersonStatus, HabCode, CategoryId,CategoryCount,"
									+ "RationCard_Slno,DistrictID,MandalID,VillageID,HabtationID, proof_id,proofdoc_type) "
									
									
									+ "select ?,Reference_Form_Number,Form_Fill_Date,Surname,First_Name,Gender_ID,Date_of_Birth,DATEDIFF(hour,Date_of_Birth,GETDATE())/8766,Religion_ID,Caste_ID,Marital_Status_ID,"
									+ "Education_ID,Employment_ID,Relationship_ID, Relation_Name,RationCard_Type,RationCard_Number,EPIC_Card,EPICCard_No,Pension_Card,"
									+ "Pension_Type,PensionCard_No,Mole_One,Mole_Two,House_Number,Phone_No,Email,District_ID,Mandal_ID,Village_ID,Habitation_ID,"
									+ "Pin_Code,Personname_Telugu,Relationname_Telugu,'Eligible',Parents_Marriage,null,null,created_by,System_IP_Address,getdate(),"
									+ "Updated_Date,'Active','Edit',created_by,Camp_ID,'PhaseIII',null,'Live',HabCode,'1','1',rationcard_slno,District_ID,Mandal_ID,"
									+ "Village_ID,Habitation_ID,Proof_Id,Proofdoc_Type from Sadarem_without_proof_request_master(nolock) where request_id=?";		
							
							
								lPstmt=con.prepareStatement(qry);
								
								lPstmt.setString(1,sadarem_id);//Person_Code
								lPstmt.setString(2, request_id);//requestID
										
								result=lPstmt.executeUpdate();
								if(result==1)
								{
									result=withoutproofuploadPhoto( aadhaarID, sadarem_id,request_id);						 
									if(result>0)
									{
										con.commit();
										SendSMS(request_id,loginID,"Approved");
									}
									else
									{
										result=0;
										con.rollback();
									}
								}
								else
								{
									result=0;
									con.rollback();
								}
						}
					}
					catch(Exception e)
					{
						result=0;
						e.printStackTrace();
										 		
					}
					finally
					{
						   if(con!=null)
				           {
				                DBConnection.closeConnection(con);
				                con=null;
				           }									
					}			
					return result;			
				
			}
		 
		 
}
