package com.tcs.sadarem.common.actions;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Constants.CommonConstants;

import Aadhar.AadhaarUtility;

import com.ecentric.servicemodels.AadhaarProfile;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAOImpl;
import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.reports.DAO.CommonReportsDAO;
import com.tcs.sadarem.reports.DAOImpl.CommonReportsDAOImpl;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.CommonValidators;
import com.tcs.sadarem.util.RequestUtility;
//Import required java libraries

public class CommonAction extends DispatchAction
{
	
	static String message ="";
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "loadpage";
	       try
	       {
		       CommonDAO comObj = new CommonDAOImpl();		
		     // System.out.println("in unspecified");
		
		      	ArrayList districtList = (ArrayList)comObj.getDistrictList();		
		      	request.setAttribute("districtList", districtList);

			       response.getWriter().write("Loading..");
			       
	       }
	       catch(Exception e)
	       {
	    	   e.printStackTrace();
	       }
	       return mapping.findForward(target);
   }
	
	public ActionForward searchbyids(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "loadpage";
	       try
	       {
	    	   message ="";
		       CommonDAO comObj = new CommonDAOImpl();
		
		      // System.out.println("in searchbyids");
		       
		       String sadaremID = CommonUtility.checkNullObj(request.getParameter("sadaremid"));
		       String aadhaarID = CommonUtility.checkNullObj(request.getParameter("aadhaarid"));
		
		      	ArrayList districtList = (ArrayList)comObj.getDistrictList();
		      	ArrayList resultList   = new ArrayList();
		      	String statusRemarks="";
		      	
		      	if(sadaremID.length()==17 || aadhaarID.length()==12)
		      	{
		      		resultList = (ArrayList)comObj.searchForSadaremDetails(sadaremID, aadhaarID, "", "", "", "", "");
		      		
		      		if(resultList.size()==0)
		      		{
		      			statusRemarks=comObj.searchForStatusRemarks(sadaremID, aadhaarID);
		      			message = statusRemarks+"        ::   ";
		      			//message = "Please provide valid SADAREM ID or Aadhaar ID. No details found for ";
		      			if(sadaremID.length()==17)
		      			{ 
		      				message+=" SADAREM ID : "+sadaremID;
		      			}
		      			
		      			if(sadaremID.length()==17 && aadhaarID.length()==12)
		      			{
		      				message+=" OR ";
		      			}
		      			
		      			if(aadhaarID.length()==12)
		      			{
		      				message+=" Aadhaar ID : "+aadhaarID;
		      			}
		      		}
		      	}
		      	else
		      	{
		      		message = "Please provide valid SADAREM ID or Aadhaar ID";
		      	}
		      	
		      	request.setAttribute("districtList", districtList);
		      	request.setAttribute("resultList", resultList);
		      	request.setAttribute("message", message);
	       }
	       catch(Exception e)
	       {
	    	   e.printStackTrace();
	       }
       return mapping.findForward(target);
   }
	
	public ActionForward searchbynames(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "loadpage";
	       try
	       {

		       //System.out.println("in searchbynames");
	    	   
	    	   message ="";
		       CommonDAO comObj = new CommonDAOImpl();
		
		       String distID	= CommonUtility.checkNullObj(request.getParameter("district"));
		       String mandalID	= CommonUtility.checkNullObj(request.getParameter("mandal"));
		       String villageID	= CommonUtility.checkNullObj(request.getParameter("village"));
		       String pername	= CommonUtility.checkNullObj(request.getParameter("personname"));
		       String relname	= CommonUtility.checkNullObj(request.getParameter("relationname"));
		
		       ArrayList resultList   = new ArrayList();
		       
		       if(
		    		   !distID.equals("") && 
		    		   !mandalID.equals("") &&
		    		   !villageID.equals("") &&
		    		   !pername.equals("")
		    		   
		    	 )
		       {
		    	   resultList = (ArrayList)comObj.searchForSadaremDetails("", "",distID, mandalID, villageID, pername, relname);
		    	   
		    	   if(resultList.size()==0)
		      		{
		      			message = "No details found with Person Name :  "+pername;
		      			
		      			if(!relname.equals("") && relname.length()>0)
		      			{
		      				message+= " and Relation's Name : "+relname;
		      			}
		      		}
		       }
		       else
		       {
		    	   message = "Please provide valid input details.";
		       }
		       
		      	ArrayList districtList = (ArrayList)comObj.getDistrictList();
		      	request.setAttribute("districtList", districtList);
		      	request.setAttribute("districtList", districtList);
		      	request.setAttribute("resultList", resultList);
		      	request.setAttribute("message", message);
	       }
	       catch(Exception e)
	       {
	    	   e.printStackTrace();
	       }
       return mapping.findForward(target);
   }
	

	
	public ActionForward getaadhaardetailsbyuid(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "aadhaarprofilepage";

	   AadhaarProfile myaadhaarProfile = null;
	   String selUid = CommonUtility.checkNullObj(request.getParameter("strUID"));
	   String message = "";
	       try
	       {
	    	
		     if(selUid!=null && !selUid.equals("") && selUid.length()==12)
		     {
		    	 myaadhaarProfile = (AadhaarProfile)AadhaarUtility.getAadhaarProfileByUID(selUid);
		     }
	       }
	       catch(Exception e)
	       {
	    	   e.printStackTrace();
	       }

	       request.setAttribute("selUid", selUid);
	       request.setAttribute("AadhaarProfileData", myaadhaarProfile);
	       
       return mapping.findForward(target);
   }
	
	public ActionForward getaadhaarinfoforparta(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "partaentryaadhar";

	   AadhaarProfile myaadhaarProfile = new AadhaarProfile();
	   String searchaadhaar = CommonUtility.checkNullObj(request.getParameter("searchaadhaar"));
	   String message = "";
	   ArrayList aadhaarTaggedSadaremIDList = new ArrayList();
	       try
	       {
	    	    CommonDAO comObj = new CommonDAOImpl();
	    	
		     if(searchaadhaar!=null && !searchaadhaar.equals("") && searchaadhaar.length()==12)
		     {
		    	 myaadhaarProfile = (AadhaarProfile)AadhaarUtility.getAadhaarProfileByUID(searchaadhaar.trim());	    	
		    	 
		    	 	/* myaadhaarProfile.setBase64file(CommonUtility.checkNullObj("100"));
					myaadhaarProfile.setBuildingName(CommonUtility.checkNullObj("100"));
					myaadhaarProfile.setCareof(CommonUtility.checkNullObj("100"));
					myaadhaarProfile.setDistrict(CommonUtility.checkNullObj("100"));
					myaadhaarProfile.setDistrict_name(CommonUtility.checkNullObj("100"));
					myaadhaarProfile.setDob(CommonUtility.checkNullObj("100"));
					myaadhaarProfile.setEid(CommonUtility.checkNullObj("100"));
					myaadhaarProfile.setGender(CommonUtility.checkNullObj("100"));
					myaadhaarProfile.setMandal(CommonUtility.checkNullObj("100"));
					myaadhaarProfile.setMandal_name(CommonUtility.checkNullObj("100"));
					myaadhaarProfile.setName(CommonUtility.checkNullObj("100"));
					myaadhaarProfile.setPhoneNo(CommonUtility.checkNullObj("100"));
					myaadhaarProfile.setPincode(CommonUtility.checkNullObj("100"));
					myaadhaarProfile.setStatecode(CommonUtility.checkNullObj("1"));
					myaadhaarProfile.setStatus(CommonUtility.checkNullObj("100"));
					myaadhaarProfile.setStreet(CommonUtility.checkNullObj("100"));
					myaadhaarProfile.setUid(searchaadhaar);
					myaadhaarProfile.setVillage(CommonUtility.checkNullObj("100"));
					myaadhaarProfile.setVillage_name(CommonUtility.checkNullObj("100"));
					myaadhaarProfile.setSrdhwstxn(CommonUtility.checkNullObj("100")); */
		    	 
		    	 aadhaarTaggedSadaremIDList = comObj.getSADAREMIDByAadhaarID(searchaadhaar);
		     }

	    	   message = CommonUtility.checkNullObj(myaadhaarProfile.getError_msg()).trim(); 
	       }
	       catch(Exception e)
	       {
	    	   e.printStackTrace();
	    	   message="01 : Sorry we are not able get the details from Aadhaar Server. Please try after some time";
	       }
	       
	        
 
	       
	       request.setAttribute("searchaadhaar", searchaadhaar);
	       request.setAttribute("AadhaarProfileData", myaadhaarProfile);
	       request.setAttribute("aadhaarTaggedSadaremIDList", aadhaarTaggedSadaremIDList);
	       request.setAttribute("message", message);
	       
       return mapping.findForward(target);
   }
	public ActionForward noProofMethod(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		String target="partanoproofdeo";
		String AdhaarNo="";
		
		 HttpSession session = null;
	        String districtid = null;
	        String districtName = null;
	        String todate = null; 
	        session = request.getSession(true);
	        CommonDAO obj= new CommonDAOImpl();
    	    CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl(); 
            CommonReportsDAO masters 	  = new CommonReportsDAOImpl();
	        try
	        {
	            
	            if (session.getAttribute("districtId") != null)
	            {
	                districtid = (String) session.getAttribute("districtId");
	                
	                districtName=obj.getDistrictName(districtid);
	                todate = CommonUtility.getDateTime("yyyy-MM-dd");
	                AdhaarNo=(String) request.getParameter("adhaarid1");	                
	            } 

	            request.setAttribute("genderList",obj.getGenderList());
	            request.setAttribute("EducationList", masters.getEducationList());
	            request.setAttribute("EmploymentList", masters.getEmploymentList());
	            request.setAttribute("CasteList", masters.getCasteList());
	            request.setAttribute("MaritalStatusList", obj.getMaritalList());
	            request.setAttribute("proofTypeList", comObj.getproofdoucments());
	            request.setAttribute("todate",CommonUtility.getDateinFormat(todate, "yyyy-MM-dd", "dd/MM/yyyy"));	            
	            request.setAttribute("districtName", districtName);
	            request.setAttribute("districtid", districtid);
	            request.setAttribute("mandalList", obj.getMandalListByDistrictID(districtid));
	            request.setAttribute("AdhaarNo", AdhaarNo);
	            
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	            request.setAttribute("MSG", "<font color='red'size='3'><b>Error while raising the request.<br>Please Try Again..!</b></font>"); 	 		
		 		return mapping.findForward("exception");
	        }
	   
		return mapping.findForward(target);
	}
	
	public ActionForward validateProof(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		
		
		
		String target="partanoproofdeoInsert";
		/*String mandalName="aaaaaaaa";
		String panchayaName="bbbbbbb";
	    String villageName="nnnnnnnn";
	    String hibitatName="bbbbbbbbbb";
	    String districtName="eeeeeeeee";*/
	    String ResultMSG=null;
	    ArrayList basidtls= new ArrayList();
	    
	    
        
	    HttpSession session = null;
	    session = request.getSession(true);
	    
	    
		HashMap partAInsertInputs = new HashMap();
		HashMap photoChangModifyDtls = new HashMap();
		ArrayList sadaremData = new ArrayList();
		int basicInsertStatus=0;
		
		HashMap partADtls=(HashMap) RequestUtility.getReqParamList(request, session, false, false, null);
	    String ipaddress= CommonUtility.getClientIPAddress(request);
	   
	    try{
	    	
	    	
	      //Personal Details
	        String aadharCardNo="";
	        String surName="";
	        String firstName="";
	        String surnameenglish="";
	        String telugupersonname="";
	        String gender="";
	        String education="";
	        String employment="";
	        String marital="";
	        String caste="";
	        String religion="";
	        String relationName="";
	        String Rcard="";
	        String rtype="";
	        String rationCardSlno="";
	        String epiccardno=null;
	        String pensioncardno="";
	        String mole1="";
	        String mole2="";
	        String parents_marriage=null;
	        String telugufathername="";        
	        
	        //String partACheckForDuplicatePersonCode = null;
	        String personcodemax = null;
	        String relationType = null;
	        //String status = null;
	        String personcode = null;
	        String loginid = null;		        
	        
	        String houseNumber="";
	        int pensionNumberCheck = 0;		        
	        String dob = "";
            String Systemip = request.getRemoteAddr();//CommonUtility.getClientIPAddress(request);
            
            String formDate="";
            if(CommonValidators.dateValidater(((FileItem) partADtls.get("dateOfAss")).getString()))
            {
            	formDate=((FileItem) partADtls.get("dateOfAss")).getString();
            }
	    	String formno= CommonValidators.stripXSS(((FileItem) partADtls.get("formno")).getString());
		 	String district_id = CommonValidators.stripXSS(((FileItem) partADtls.get("district_id")).getString());
	        String mandal_id = CommonValidators.stripXSS(((FileItem) partADtls.get("mandals")).getString());
	        String village_id = CommonValidators.stripXSS(((FileItem) partADtls.get("villages")).getString());
	        String habitation_id = CommonValidators.stripXSS(((FileItem) partADtls.get("habitation")).getString());
	        String panchayat_id = CommonValidators.stripXSS(((FileItem) partADtls.get("panchayats")).getString());
	        String PinCode = convertToNullWhenNoInput(CommonValidators.stripXSS(((FileItem) partADtls.get("pin")).getString()));
	        String contid="";
	        if( CommonValidators.mobileNumberValidator((CommonValidators.stripXSS(((FileItem) partADtls.get("contid")).getString()))))
	        {
	        	contid = convertToNullWhenNoInput(CommonValidators.stripXSS(((FileItem) partADtls.get("contid")).getString()));
	        }
	        String email = convertToNullWhenNoInput(CommonValidators.stripXSS(((FileItem) partADtls.get("email")).getString()));
	        
	        houseNumber=convertToNullWhenNoInput(CommonValidators.stripXSS(((FileItem) partADtls.get("houseno")).getString()));
	        
			 surName=CommonValidators.stripXSS(((FileItem) partADtls.get("surname")).getString());
	         firstName=CommonValidators.stripXSS(((FileItem) partADtls.get("firstname")).getString());
	         surnameenglish=CommonValidators.stripXSS(((FileItem) partADtls.get("surnameenglish")).getString());
	         telugupersonname=CommonValidators.stripXSS(((FileItem) partADtls.get("telugupersonname")).getString());	         
	         if(CommonValidators.dateValidater(CommonValidators.stripXSS(((FileItem)partADtls.get("dob")).getString())))
	         {
	        	 dob = CommonValidators.stripXSS(((FileItem)partADtls.get("dob")).getString());
	         }
	         gender=CommonValidators.stripXSS(((FileItem) partADtls.get("gender")).getString());
	         education=CommonValidators.stripXSS(convertToNullWhenNoInput(((FileItem) partADtls.get("education")).getString()));
	         employment=CommonValidators.stripXSS(convertToNullWhenNoInput(((FileItem) partADtls.get("employment")).getString()));
	         marital=CommonValidators.stripXSS(convertToNullWhenNoInput(((FileItem) partADtls.get("marital")).getString()));
	         caste=CommonValidators.stripXSS(convertToNullWhenNoInput(((FileItem) partADtls.get("caste")).getString()));
	         religion=CommonValidators.stripXSS(convertToNullWhenNoInput(((FileItem) partADtls.get("religion")).getString()));
	         Rcard=CommonValidators.stripXSS(convertToNullWhenNoInput(((FileItem) partADtls.get("card")).getString()));
	         
	         if((FileItem) partADtls.get("rtype")!=null)
	         {
	        	 rtype=CommonValidators.stripXSS(convertToNullWhenNoInput(((FileItem) partADtls.get("rtype")).getString()));
	         }
	         
	         rationCardSlno=CommonValidators.stripXSS(convertToNullWhenNoInput((((FileItem) partADtls.get("rationCardSlno")).getString())));
	         epiccardno=CommonValidators.stripXSS(((FileItem) partADtls.get("epiccardno")).getString());
	         pensioncardno=convertToNullWhenNoInput(((FileItem) partADtls.get("pensioncardno")).getString());
	         mole1=CommonValidators.stripXSS(((FileItem) partADtls.get("mole1")).getString());
	         mole2=CommonValidators.stripXSS(((FileItem) partADtls.get("mole2")).getString());
	         
	         if((FileItem) partADtls.get("parents_marriage")!=null)
	         {
	        	 parents_marriage=CommonValidators.stripXSS(((FileItem) partADtls.get("parents_marriage")).getString());
	         }
	         
	         		         
	         relationName=CommonValidators.stripXSS(((FileItem) partADtls.get("GName")).getString());
	         relationType = CommonValidators.stripXSS(((FileItem) partADtls.get("grelation")).getString());		         
	         //partACheckForDuplicatePersonCode =((FileItem) partADtls.get("telugufathername")).getString();
	         telugufathername = CommonValidators.stripXSS(((FileItem) partADtls.get("telugufathername")).getString());		         
	         aadharCardNo =CommonValidators.stripXSS(((FileItem) partADtls.get("aadharCardNo")).getString());
	         //personcode = ((FileItem) partADtls.get("district_id")).getString();
	         loginid = CommonValidators.stripXSS((String) session.getAttribute("loginid"));
             int campId = 0 ;
             if(session.getAttribute("campId")!=null)
             {
            	  campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
             }
             else
             {
            	 	request.setAttribute("ResultMSG", "<font style='color:red;font-weight:bold'>Error in Form Filling. Mandatory Details are missing</font>");
		        	return mapping.findForward("partanoproofdeoInsert");
             }
	    	
	         //Contact Details
	         	partAInsertInputs.put("formDate", formDate);
		        partAInsertInputs.put("formno", formno);
		        partAInsertInputs.put("district_id", district_id);
		        partAInsertInputs.put("mandal_id", mandal_id);
		        partAInsertInputs.put("village_id", village_id);
		        partAInsertInputs.put("panchayat_id", panchayat_id);
		        partAInsertInputs.put("habitation_id", habitation_id);
		        partAInsertInputs.put("PinCode", PinCode);
		        partAInsertInputs.put("contid", contid);
		        partAInsertInputs.put("email", email);
		        partAInsertInputs.put("houseNumber", houseNumber);
	    	//Personal Details
		        partAInsertInputs.put("surName", surName);
		        partAInsertInputs.put("firstName", firstName);
		        partAInsertInputs.put("surnameenglish", surnameenglish);
		        partAInsertInputs.put("telugupersonname", telugupersonname);
		        partAInsertInputs.put("dob", dob);
		        partAInsertInputs.put("gender", gender);
		        partAInsertInputs.put("education", education);
		        partAInsertInputs.put("employment", employment);
		        partAInsertInputs.put("marital", marital);
		        partAInsertInputs.put("caste", caste);
		        partAInsertInputs.put("religion", religion);
		        partAInsertInputs.put("Rcard", Rcard);
		        partAInsertInputs.put("rtype", rtype);
		        partAInsertInputs.put("rationCardSlno", rationCardSlno);
		        partAInsertInputs.put("epiccardno", epiccardno);
		        partAInsertInputs.put("pensioncardno", pensioncardno);
		        partAInsertInputs.put("mole1", mole1);
		        partAInsertInputs.put("mole2", mole2);
		        partAInsertInputs.put("parents_marriage", parents_marriage);
		        partAInsertInputs.put("relationName", relationName);
		        partAInsertInputs.put("relationType", relationType);
		        partAInsertInputs.put("telugufathername", telugufathername);
		        partAInsertInputs.put("aadharCardNo", aadharCardNo);
		        partAInsertInputs.put("personcodemax", personcodemax);		        
		        partAInsertInputs.put("loginid", loginid);		        
		        partAInsertInputs.put("Systemip", Systemip);
		        partAInsertInputs.put("personcode", personcode);
		        //partAInsertInputs.put("status", status);
		        partAInsertInputs.put("campId", campId);
		        
		        
		      //Contact Details check 
		        if(formno.trim().length()==0 || formDate.trim().length()==0 || contid.trim().length()!=10 || district_id.trim().equals("")  ||district_id.trim().equals("-1")  || mandal_id.trim().equals("") ||  mandal_id.trim().equals("-1") || panchayat_id.trim().equals("") || panchayat_id.trim().equals("-1") ||village_id.trim().equals("") ||village_id.trim().equals("-1") ||habitation_id.trim().equals("")||habitation_id.trim().equals("-1"))
		        {
		        	request.setAttribute("ResultMSG", "<font style='color:red;font-weight:bold'>Error in Form Filling. Mandatory Contact Details should be filled</font>");
		        	return mapping.findForward(target);
		        }
		        //Personal Details check
		        if(surName.trim().equals("")  || firstName.trim().equals("") ||surnameenglish.trim().equals("") ||telugupersonname.trim().equals("") 
		        		||dob.trim().equals("")  || gender.trim().equals("") ||marital.trim().equals("") ||mole1.trim().equals("") ||mole2.trim().equals("")
		        			||relationName.trim().equals("")  || relationType.trim().equals("") ||relationType.trim().equals("-1")||telugufathername.trim().equals("") )
		        {
		        	request.setAttribute("ResultMSG", "<font style='color:red;font-weight:bold'>Error in Form Filling. Mandatory Personal Details should be filled</font>");
		        	return mapping.findForward(target);
		        }  
		        //Photo Check
		        //validateProof(request);
		        
			
	           //String CheckDuplicate= partADAO.getPartACheckForDuplicate(ds,district_id, mandal_id, village_id, habitation_id, surName, firstName, lastName, Systemip, gender, relationName, houseNumber);
		        
		        
		        CommonDAO obj= new CommonDAOImpl();
	            //obj.insertPersonalDetails(partAInsertInputs);
		        
	            	
					 ArrayList FileTypeAllowedList = new ArrayList();
				     FileTypeAllowedList.add("pdf");
				     String fileContentTypeAllowed = "application/pdf";
				     String fileName1="",filetype1="",fileContentType1="";
					 long file_size1  = 0;
					 String fileName2="",filetype2="",fileContentType2="";
					 long file_size2  = 0;
					 long photoFileSize=0;
					 
					    ArrayList photoFileTypeAllowedList = new ArrayList();
				    	photoFileTypeAllowedList.add("jpg");
				    	photoFileTypeAllowedList.add("jpeg");
				    	photoFileTypeAllowedList.add("png");
				    	String photofileContentTypeAllowed = "image/jpg,image/png,image/jpeg";
				    	String fileName="",filetype="",photofileName="",photofiletype="",fileContentType="",photoContentType="";
				    
					
				   
				    
				    String proofType1   = "PR001";//((FileItem) partADtls.get("proofidentity_1")).getString();
				    String proofId1     = ((FileItem) partADtls.get("proofid_1")).getString();
				    FileItem file1      = ((FileItem) partADtls.get("proofDoc_1"));
				    
				    String proofType2   = ((FileItem) partADtls.get("proofidentity_2")).getString();
				    String proofId2     = ((FileItem) partADtls.get("proofid_2")).getString();
				    FileItem file2      = ((FileItem) partADtls.get("proofDoc_2"));
				    
				    FileItem photoFile =  ((FileItem) partADtls.get("manualPhoto")); 
					
				    file_size1 = file1.getSize();
					if(file_size1 != 0) 
					{
					      fileName1 = file1.getName();
						  filetype1 = fileName1.substring((fileName1.lastIndexOf(".")+1));
						  fileContentType1 = file1.getContentType().toLowerCase();
					}					
					file_size2 = file2.getSize();
					if(file_size2 != 0) 
					{
					      fileName2 = file2.getName();
						  filetype2 = fileName2.substring((fileName2.lastIndexOf(".")+1));
						  fileContentType2 = file2.getContentType().toLowerCase();
					}							 
									photoFileSize = photoFile.getSize();
									partAInsertInputs.put("proofTypeID1",proofType1 );
									partAInsertInputs.put("proofId1",proofId1);
									partAInsertInputs.put("docType1",filetype1 );
									partAInsertInputs.put("filePath1","/"+proofId1); 
									partAInsertInputs.put("fileDoc1",file1);
									partAInsertInputs.put("fileSize1",file_size1);
									
									
									partAInsertInputs.put("proofTypeID2",proofType2 );
									partAInsertInputs.put("proofId2",proofId2);
									partAInsertInputs.put("docType2",filetype2 );
									partAInsertInputs.put("filePath2","/"+proofId2); 
									partAInsertInputs.put("fileDoc2",file2);
									partAInsertInputs.put("fileSize2",file_size2);
									if(photoFileSize !=0)
									{
										  photofileName = photoFile.getName();
										  photofiletype = photofileName.substring((photofileName.lastIndexOf(".")+1));
										  photoContentType = photoFile.getContentType().toLowerCase();
											partAInsertInputs.put("photodocType",photofiletype );
											partAInsertInputs.put("photoDoc",photoFile );
									}								
									if(!photoFileTypeAllowedList.contains(photofiletype.toLowerCase()))
									{
										request.setAttribute("ResultMSG", "<font color='red'>Please upload valid jpg/png/jpeg photo.</font>");
							        	return mapping.findForward(target);
									}
									else if(photoFileSize>CommonConstants.PHOTODOCMAXLIMIT)
									{
										request.setAttribute("ResultMSG",  "<font color='red'>Please upload valid photo with size less than 100KB.</font>");
							        	return mapping.findForward(target);
									}									
									else if(!FileTypeAllowedList.contains(filetype1.toLowerCase()))
									{
										request.setAttribute("ResultMSG", "<font color='red'>Please upload valid PDF document.</font>");
							        	return mapping.findForward(target);
									}
									else if(file_size1>CommonConstants.PROOFDOCMAXLIMIT)
									{
										request.setAttribute("ResultMSG","<font color='red'>Please upload valid document with size less than 5MB.</font>");
							        	return mapping.findForward(target);
									}
									else if(fileContentTypeAllowed.indexOf(fileContentType1)==-1)
									{ 
										request.setAttribute("ResultMSG", "<font color='red'>Please upload valid PDF document.</font>");
							        	return mapping.findForward(target);
									}
									/*else if(proofType2.trim().equals("P001")||proofType2.trim().equals("P008")||proofType2.trim().equals("P009")||proofType2.trim().equals("P010"))
									{ 
										request.setAttribute("ResultMSG", "<font color='red'>Please upload different type document.</font>");
							        	return mapping.findForward(target);
									}
									else if(file_size2 != 0 && !FileTypeAllowedList.contains(filetype2.toLowerCase()))
									{
										request.setAttribute("ResultMSG", "<font color='red'>Please upload valid PDF document.</font>");
							        	return mapping.findForward(target);
									}
									else if(file_size2 != 0 && file_size2>CommonConstants.PROOFDOCMAXLIMIT)
									{
										request.setAttribute("ResultMSG", "<font color='red'>Please upload valid document with size less than 5MB.</font>");
							        	return mapping.findForward(target);
									}
									else if(file_size2 != 0 && fileContentTypeAllowed.indexOf(fileContentType2)==-1)
									{
										request.setAttribute("ResultMSG", "<font color='red'>Please upload valid PDF document.</font>");
							        	return mapping.findForward(target);
									}*/
									else
									{
										basidtls=obj.insertPersonalDetails(partAInsertInputs);
										//System.out.println(basidtls);
									}
									
										request.setAttribute("basidtls", basidtls);
									
							          
					        
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	request.setAttribute("MSG", "<font color='red'size='3'><b>Error while raising the request.<br>Please Try Again..!</b></font>"); 	 		
	 		return mapping.findForward("exception");
	    	
	    }
	    return mapping.findForward(target);
	
	}
	public String convertToNullWhenNoInput(String s)
	{
		if(s==null ||s.trim().equals("") || s.trim().equals("-1"))
		{
			return null;
		}
		return s;
	}
	
}
