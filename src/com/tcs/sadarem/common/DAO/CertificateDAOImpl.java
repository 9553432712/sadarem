package com.tcs.sadarem.common.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.bf.disability.Constants.CertificateConstants;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dto.CertificateDTO;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.form.TerritoryForm;

import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

public class CertificateDAOImpl implements CertificateDAO 
{

	static final private Logger log = Logger.getLogger(CertificateDAOImpl.class);
 
	String lStrQuery;
	

	   public static String NumberInSingleDigit[] = {"", "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ",
	        "Eight ", "Nine ", "Ten ", "Eleven ", "Twelve ", "Thirteen ",
	        "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ",
	        "Eighteen ", "Nineteen "};
	    public static String NumberInTensDigit[] = {"Twenty ", "Thirty ", "Fourty ",
	        "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};

	
	
	public  ArrayList getOnlyPartADetails(String personcode)
	{
		ArrayList basicDetails = new ArrayList();
	
		try
		{

        	log.info(" getOnlyPartADetails  start ");
			ArrayList paramList = new ArrayList();
			ArrayList tempList = new ArrayList();
			
			String lStrQuery =    "select a.person_code,a.surname + '' +a.first_name name,a.relation_name,case when gender=1 then 'Male' else 'Female' end gender\n"+
					 " ,b.district_name,c.mandal_name,a.reasonforpersonstatus\n"+
					 "from tblperson_personal_details a with(nolock)  join tbldistrict_details b with(nolock) \n"+
					 "on a.districtid = b.district_id \n"+
					 "join tblmandal_details c with(nolock)  on a.districtid = c.district_id and a.mandalid = c.mandal_id and a.person_code=?";

        	log.info("getOnlyPartADetails  try sql: ");
			tempList = new ArrayList();
			tempList.add("S");
			tempList.add(personcode);
			paramList.add(tempList);
			
		//	log.info("lStrQuery : "+lStrQuery+"\nparamList : "+paramList);			
			        basicDetails = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			      //  System.out.println("vv"+basicDetails);
		}catch(Exception e)
		{
			System.out.println(""+e);
			log.error(e);
		}
		
		return basicDetails;
	}
	
	  public String getReasonForPersonStatus(String personcode)
	  {
	        String personStatus=""; 
	        try 
	        { 
	        	ArrayList paramList = new ArrayList();
	        	ArrayList tempList	= new ArrayList();
	        	
	            lStrQuery = "select  reasonforpersonstatus from tblPerson_Personal_Details with(nolock)   where person_code=?";
	             // System.out.println("query"+query);     
	                  
	            tempList	= new ArrayList();
	            tempList.add("S");
	            tempList.add(personcode);
	            paramList.add(tempList);
	            
	            personStatus = DataAccess.getReturnResultByPstmt(lStrQuery, paramList);

	        }
	        catch(Exception e)
	        {
	        	log.error(e);

	        }
	        return personStatus;
	    }
	  
	  public ArrayList getCampDistIds(String personcode)
		{
			ArrayList basicDetails = new ArrayList();
			
			try
			{
				
				ArrayList paramList = new ArrayList();
				ArrayList tempList = new ArrayList();
				
				lStrQuery = "SELECT a.partB_campid,a.district_id\n"+
						  "from tblperson_personal_details a with(nolock)  where a.person_code=?";
				
				tempList = new ArrayList();
				tempList.add("S");
				tempList.add(personcode);
				paramList.add(tempList);
				
				        basicDetails = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
				      //  System.out.println("vv"+basicDetails);
			}catch(Exception e)
			{
				System.out.println(""+e);
			}
			
			return basicDetails;
		}
	  
	  
	  public boolean checkForAppellateHasRaised(String personCode)
	    { 
	        boolean result = false; 
	        try
	        { 
	        	ArrayList paramList = new ArrayList();
				ArrayList tempList = new ArrayList();
	            lStrQuery = " select COUNT(*)  from tkt_request_master  where 	tkt_type_id='S018' and status_flag='6A5' and sadarem_id=?";
	            
	            tempList = new ArrayList();
				tempList.add("S");
				tempList.add(personCode);
				paramList.add(tempList);
				
				 if(Integer.parseInt(DataAccess.getReturnResultByPstmt(lStrQuery, paramList))>0)
				 {
					 result=true;
				 }
				 else
				 {
					 result = false; 
				 }
	           
	        } 
	        catch (Exception e)
	        {

				 result=true;
	          log.error(e);
	        }
	        
	    	return result;
	    }
	  
	  
	  public ArrayList getCatIdOfPerson(String personCode)
      {
	        ArrayList resultList=new ArrayList();
	        try 
	        { 
	        	ArrayList paramList = new ArrayList();
				ArrayList tempList = new ArrayList();
	        	
	          lStrQuery = "select  CategoryID,status,view_edit_mode from tblPerson_Personal_Details with(nolock)  where person_code=?";
	          
	          tempList = new ArrayList();
				tempList.add("S");
				tempList.add(personCode);
				paramList.add(tempList);    
	               
				

		          tempList = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
				if(tempList.size()>0)
				{
					resultList = (ArrayList)tempList.get(0);
				}
	          
	        } 
	        catch(Exception e)
	        {
	        	log.error(e);
	        }
	        return resultList;
	    }

	  public String getAppellateAuthorityTempStatus(String personcode)
	  {
	        String personStatus="";
	        
	        try 
	        {
	        	ArrayList paramList = new ArrayList();
				ArrayList tempList = new ArrayList();
	        	
	            lStrQuery = "select  status from APPELLATEAUTHORITYANDTEMPORARY_REGISTRATIONDETAILS with(nolock)   where person_code=?";
	              //System.out.println("query"+query);     
	                  
	            tempList	= new ArrayList();
	            tempList.add("S");
	            tempList.add(personcode);
	            paramList.add(tempList);
	            
	            personStatus = DataAccess.getReturnResultByPstmt(lStrQuery, paramList);

	        }
	        catch (Exception e) 
	        {
	        	log.error(e);	
	        }
	        
	        return personStatus;
	    }
	  
	  public String getAppellateAuthorityViewEditStatus(String personcode)
	  {
	        String personStatus="";
	      
	        try
	        { 
	        	ArrayList paramList = new ArrayList();
				ArrayList tempList = new ArrayList();
	        	
	            String query = "select  ViewEditMode from APPELLATEAUTHORITYANDTEMPORARY_REGISTRATIONDETAILS with(nolock)  where person_code=?";
	              //System.out.println("query"+query);     
	     
	            tempList	= new ArrayList();
	            tempList.add("S");
	            tempList.add(personcode);
	            paramList.add(tempList);
	            
	            personStatus = DataAccess.getReturnResultByPstmt(lStrQuery, paramList);

	        }
	        catch (Exception e) 
	        {
	        	log.error(e);	
	        }
	        
	        return personStatus; 
	    }
	  
	  public HashMap getSADAREMDetailsByID(String sadarem_id)
	  {
		  HashMap resultList = new HashMap();
		  try
		  {

	        	ArrayList paramList = new ArrayList();
				ArrayList tempList = new ArrayList();
				
			  lStrQuery =
					  " SELECT sadarem_id,districtid,mandalid,villageid,habtationid,caste_id,\n"
					  + " edu_id,emp_type_id,marital_status_id,religion_id,district_name,mandal_name,\n"
					  + " village_name,habitation_name,person_surname,person_name,personname_telugu,\n"
					  + " person_dob,type_of_proof,proof_id,person_gender,rel_type,relation_name,relation_telugu_name,\n"
					  + " person_contactno,category_id,category_count,person_status,person_live_status,dis_form_status,\n"
					  + " caste_desc,education_desc,employement_type_desc,marital_status_desc,religion_desc,rec_modified_date,\n"
					  + " parta_entry_date,parta_campid,partb_campid,parta_campname,partb_campname,parents_marriage,is_active,\n"
					  + " disb_id,disability_name,disp_min_per,person_disp_percent,cause_of_disp,certificate_type,\n"
					  + " certificate_issue_date,temp_cert_expire_year,cert_expire_date,hospital_name,hospital_address,\n"
					  + " first_doctor_name,first_doctor_regnumber,first_doctor_designation,second_doctor_name,\n"
					  + " second_doctor_regnumber,second_doctor_designation,third_doctor_name,\n"
					  + " third_doctor_regnumber,third_doctor_designation "
					  + "FROM sadarem_view_complete_details WITH(NOLOCK) \n"+ 
					  " WHERE sadarem_id=? ";
			  

	            tempList	= new ArrayList();
	            tempList.add("S");
	            tempList.add(sadarem_id);
	            paramList.add(tempList);
	            
	            tempList	= new ArrayList();
	            tempList =DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, true, false);
	            
	            if(tempList.size()==2)
	            {
	            	resultList = CommonUtility.getHashMapfromArrayListWithcolumnName(tempList);
	            }
			  
		  }
		  catch(Exception e)
		  {
			  log.error(e);
		  }
		  
		  return resultList;
	  }
	  
	   public ArrayList getDetailsForCertificate(DataSource ds, TerritoryForm territoryForm, String personcode) throws SADAREMDBException, SQLException
	    {
	        ArrayList reportlist = new ArrayList();
	        TerritoryDTO territorydto = null;
	        Connection con = null;
	        //Statement stmt = null;
	        CallableStatement cstmt = null;
	        //Statement stmt2=null;
	        ResultSet rs = null;
	        List causeofDisabilityList = null;
	        List locomotorSubDisabilityList = null;
	        List locomotorSubSubDisabilityList = null;
	        List locomotorarmlegeffectedList = null;
	        List locomotorphysicalrequirements = null;
	        String disabilitycondition = null;
	        String numberInwords = null;
	        String FirstName = null;
	        String Surname = null;
	        String Name = null;
	        String relationName = null;
	        String disabilityreason = null;
	        String hospitalName = null;
	        String hospitalAddress = null;
	        String firstdoctorname = null;
	        String seconddoctorname = null;
	        String thirddoctorname = null;
	        String rightEarDB = null;
	        String leftEarDB = null;
	        String districtName = null;
	        String mandalName = null;
	        String vilageName = null;
	        String habitaionName = null;
	        PreparedStatement cs = null;
	        try 
	        { 
	        	log.info("Before Connection");
	            causeofDisabilityList = new ArrayList();
	            locomotorSubDisabilityList = new ArrayList();
	            locomotorphysicalrequirements = new ArrayList();
	            con = DBConnection.getConnection();

	        	log.info("after Connection");
	            cstmt = con.prepareCall("{Call SP_SELECTDATAFORCERTIFICATES_NEW(?)}");
	            cstmt.setString(1, personcode);

	        	log.info("after set paramater");
	            rs = cstmt.executeQuery();

	        	log.info("after executeQuery");
	            if (rs.next()) 
	            {
		        	log.info("in resultList");
	                territorydto = new TerritoryDTO();
		        	log.info("after TerritoryDTO object");

	                territorydto.setPersoncode(rs.getString("Person_Code"));
	                FirstName = convertFirstLetterToUpperCase(rs.getString("First_Name"));
	                Surname =  convertFirstLetterToUpperCase(rs.getString("Surname"));
	                
	                territorydto.setCategory(rs.getString("CategoryID"));
	                
	                if(Surname != null )                //Surname is not mandatory.So in order to avoid null values from db this check is given
	                {
	                  Name = Surname + " " + FirstName;
	                }
	                else
	                {
	                	Name = FirstName;
	                }
	                if (null != Name && !"".equals(Name)) {
	                    territorydto.setName(Name);
	                }
	                if (rs.getString("Gender").equals("1")) {
	                    territorydto.setGender("Male");
	                    territorydto.setGenderInitial1("He");
	                    territorydto.setGenderInitial2("his");
	                    territorydto.setGenderInTelugu(CertificateConstants.MALE_TELUGU);
	                    territorydto.setMaritialstatus("Shri");
	                    int Relationship = rs.getInt("Relationship");
	                    switch (Relationship) {
	                    
	                     case 0: territorydto.setRelationship(CertificateConstants.CARE_OF);
	                         break;
			             case 1:
			            	 territorydto.setRelationship(CertificateConstants.SON_OF);
			                 break;
			             case 2:
			            	 territorydto.setRelationship(CertificateConstants.GAURDIAN_OF);
			                 break;
			             
			             case 3:
			            	 territorydto.setRelationship(CertificateConstants.HUSBAND_OF);
			                 break;
			             default:
			                 break;
	                       
	                    }
	                } else if (rs.getString("Gender").equals("2")) {
	                    territorydto.setGender("Female");
	                    territorydto.setGenderInTelugu(CertificateConstants.FEMALE_TELUGU);
	                    territorydto.setGenderInitial1("She");
	                    territorydto.setGenderInitial2("her");
	                    if (rs.getString("Marital_Status").equals("1")) {
	                        territorydto.setMaritialstatus("Smt");
	                    } else if (rs.getString("Marital_Status").equals("2")) {
	                        territorydto.setMaritialstatus("Kumari");
	                    } else if (rs.getString("Marital_Status").equals("3")
	                            || rs.getString("Marital_Status").equals("4")
	                            || rs.getString("Marital_Status").equals("5")) {
	                        territorydto.setMaritialstatus("Ms");
	                    }
	                    int Relationship = rs.getInt("Relationship");
	                    switch (Relationship)
	                    {
		                 case 0:territorydto.setRelationship(CertificateConstants.CARE_OF);
		                    break;
			             case 1:
			            	 territorydto.setRelationship(CertificateConstants.DAUGHTER_OF);
			                 break;
			             case 2:
			            	 territorydto.setRelationship(CertificateConstants.GAURDIAN_OF);
			                 break;
			             case 3:
			            	 territorydto.setRelationship(CertificateConstants.WIFE_OF);
			                 break;
			              default:
			                 break;
	                    }
	                }
	                String dob = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd ").parse(rs.getString("Date_of_Birth")));
	                territorydto.setDateofbirth(dob);
	                territorydto.setAge(rs.getString("Age_Years"));
	                territorydto.setMoleone(rs.getString("Mole_One"));
	                territorydto.setMoletwo(rs.getString("Mole_Two"));

	                relationName =  convertFirstLetterToUpperCase(rs.getString("Relation_Name"));
	                if (null != relationName && !"".equals(relationName)) {
	                    territorydto.setRelationname(relationName);
	                }
	                territorydto.setHousenumber(rs.getString("House_Number"));
	                territorydto.setTelugupersonname(rs.getString("Personname_Telugu"));
	                territorydto.setTelugufathername(rs.getString("Fathername_Telugu"));
	                territorydto.setTypeofdisability(rs.getString("Disability_ID"));
	                territorydto.setFlag(rs.getString("flag"));
	                //newly changed by bapinaidu for perfomance nad removed unnessasary code.

	                int disabilityid = rs.getInt("Disability_ID");
	                switch (disabilityid) 
	                {
	                    case 1:
	                        territorydto =  locomotorDetails(rs, territorydto);
	                        break;
	                    case 2:
	                        if (rs.getFloat("VisualImpairment") != 0) {
	                            territorydto.setVisualImpairmentPercentage(Math.round(rs.getFloat("VisualImpairment")));
	                            territorydto =  visualDetails(rs, territorydto);
	                        }
	                        break;

	                    case 3:
	                        territorydto =  hearingDetails(rs, territorydto);
	                        break;
	                    case 4:
	                        territorydto =  mentalRetardationDetails(rs, territorydto);
	                        break;
	                    case 5:
	                        territorydto =  mentalIllnessDetails(rs, territorydto);
	                        break;
	                    case 6:
	                        int multipledisabilityid = rs.getInt("Multiple_Disability_Sub_ID");
	                        territorydto.setComment("yes");
	                        List multipleDisabiliPhyscicalRequirementsList = new ArrayList();
	                        territorydto.setMultipleRadio(String.valueOf(multipledisabilityid));
	                        if (rs.getFloat("TotalLocomotor") != 0) 
	                        {
	                            territorydto.setPhysicalImpairmentPercentage(Math.round(rs.getFloat("TotalLocomotor")));
	                            territorydto =  locomotorDetails(rs, territorydto);
	                            multipleDisabiliPhyscicalRequirementsList.add(territorydto.getPhysicalImpairmentPhysicalRequirementList());
	                        }
	                        
	                        if (rs.getFloat("HearingImpairment") != 0) 
	                        {
	                            territorydto.setHearingImpairmentPercentage(Math.round(rs.getFloat("HearingImpairment")));
	                            territorydto =  hearingDetails(rs, territorydto);
	                            multipleDisabiliPhyscicalRequirementsList.add(territorydto.getHearingImpairmentPhysicalRequirementList());
	                        }
	                        
	                        if (rs.getFloat("VisualImpairment") != 0) 
	                        {
	                            territorydto.setVisualImpairmentPercentage(Math.round(rs.getFloat("VisualImpairment")));
	                            territorydto =  visualDetails(rs, territorydto);
	                            multipleDisabiliPhyscicalRequirementsList.add(territorydto.getVisualImpairmentPhysicalRequirementList());
	                        }
	                        if (rs.getFloat("MentalRetardation") != 0)
	                        {
	                            territorydto.setMentalRetardationPercentage(Math.round(rs.getFloat("MentalRetardation")));
	                            territorydto =  mentalRetardationDetails(rs, territorydto);
	                            multipleDisabiliPhyscicalRequirementsList.add(territorydto.getMentalRetardationPhysicalRequirementList());
	                        }
	                        if (rs.getFloat("MentalIllness") != 0) {
	                            territorydto.setMentalIllnessPercentage(Math.round(rs.getFloat("MentalIllness")));
	                            territorydto =  mentalIllnessDetails(rs, territorydto);
	                        }
	                        if (multipleDisabiliPhyscicalRequirementsList != null && !multipleDisabiliPhyscicalRequirementsList.isEmpty()) 
	                        {
	                            String multipleDisabiliPhyscicalRequirements =  separatewithcammamultiple(multipleDisabiliPhyscicalRequirementsList);
	                            territorydto.setMultipleDisabilityPhysicalRequirementList(multipleDisabiliPhyscicalRequirements);
	                        }
	                        territorydto.setDisabilitytype(CertificateConstants.MULTIPLE_DISABILITYTYPE);
	                        territorydto.setDisabilityTypeInIdCard(CertificateConstants.MULTIPLE_DISABILITYTYPE);
	                        territorydto.setDisabilityTypeInTelugu(CertificateConstants.MULTIPLE_DISABILITYTYPE_TELUGU);
	                        break;
	                    default:
	                        break;
	                }
	                disabilityreason =  convertFirstLetterToUpperCase(rs.getString("Diagnosis_of_Disability"));
	                if (null != disabilityreason && !"".equals(disabilityreason)) {
	                    territorydto.setDisabilityreason(disabilityreason);
	                }
	                hospitalName =  convertFirstLetterToUpperCase(rs.getString("Hospital_Name"));
	                if (null != hospitalName && !"".equals(hospitalName)) {
	                    territorydto.setHospitalname(hospitalName);
	                }
	                hospitalAddress =  convertFirstLetterToUpperCase(rs.getString("Hospital_Address"));
	                if (null != hospitalAddress && !"".equals(hospitalAddress)) {
	                    territorydto.setHospitaladdress(hospitalAddress);
	                }
	                firstdoctorname =  convertFirstLetterToUpperCase(rs.getString("First_Doctor_Name"));
	                if (null != firstdoctorname && !"".equals(firstdoctorname)) {
	                    territorydto.setFirstdoctorname(firstdoctorname);
	                }
	                territorydto.setFirstdoctornumber(rs.getString("First_Doctor_RegNumber"));
	                territorydto.setFirstdesgination(rs.getString("First_Doctor_Designation"));
	                seconddoctorname =  convertFirstLetterToUpperCase(rs.getString("Second_Doctor_Name"));
	                if (null != seconddoctorname && !"".equals(seconddoctorname)) {
	                    territorydto.setSeconddoctorname(seconddoctorname);
	                }
	                territorydto.setSeconddoctornumber(rs.getString("Second_Doctor_RegNumber"));
	                territorydto.setSeconddesgination(rs.getString("Second_Doctor_Designation"));
	                thirddoctorname =  convertFirstLetterToUpperCase(rs.getString("Third_Doctor_Name"));
	                if (null != thirddoctorname && !"".equals(thirddoctorname)) {
	                    territorydto.setThirddoctorname(thirddoctorname);
	                }
	                territorydto.setThirddoctornumber(rs.getString("Third_Doctor_RegNumber"));
	                territorydto.setThirddesgination(rs.getString("Third_Doctor_Designation"));

	                if (rs.getString("Condition_of_Disability") != null && !rs.getString("Condition_of_Disability").equals("") && rs.getString("Condition_of_Disability").equals("1")) {
	                    territorydto.setValidity(CertificateConstants.VALIDITY_PERMANENT);
	                    if ("idcard".equals(territoryForm.getDifferenceFlag()) || "idcardprint".equals(territoryForm.getDifferenceFlag())) {
	                        territorydto.setPeriod(CertificateConstants.LIFETIME_PERIOD);
	                    }
	                    territorydto.setKindofdisability(CertificateConstants.VALIDITY_NOTRECOMENDED);
	                    territorydto.setDisabilityduration(CertificateConstants.DISABILITY_DURATION_PERMANENT_TELUGU);
	                    territorydto.setTeluguDisabilityName(CertificateConstants.TELUGU_DISABILITY_NAME_PERMANENT);
	                }
	                if (rs.getString("Condition_of_Disability") != null && !rs.getString("Condition_of_Disability").equals("") && rs.getString("Condition_of_Disability").equals("2")) {
	                    territorydto.setValidity(CertificateConstants.VALIDITY_PERMANENT);
	                    if ("idcard".equals(territoryForm.getDifferenceFlag()) || "idcardprint".equals(territoryForm.getDifferenceFlag())) {
	                        territorydto.setPeriod(CertificateConstants.LIFETIME_PERIOD);
	                    }
	                    territorydto.setKindofdisability(CertificateConstants.VALIDITY_NOTRECOMENDED);
	                    territorydto.setDisabilityduration(CertificateConstants.DISABILITY_DURATION_PERMANENT_TELUGU);
	                    territorydto.setTeluguDisabilityName(CertificateConstants.TELUGU_DISABILITY_NAME_PERMANENT);
	                }
	                if (rs.getString("Condition_of_Disability") != null && !rs.getString("Condition_of_Disability").equals("") && rs.getString("Condition_of_Disability").equals("3")) {
	                    territorydto.setValidity(CertificateConstants.VALIDITY_TEMPORARY);
	                    territorydto.setTeluguDisabilityName(CertificateConstants.TELUGU_DISABILITY_NAME_TEMPARARY);
	                    territorydto.setKindofdisability("recommended after a period of");
	                    int yearsoftemporary = rs.getInt("Years_for_Temparary");
	                    switch (yearsoftemporary) {
	                        case 1:
	                            territorydto.setPeriod("1 year.");
	                            territorydto.setDisabilityduration(CertificateConstants.ONE_YEAR_TELUGU);
	                            break;
	                        case 2:
	                            territorydto.setPeriod("2 years.");
	                            territorydto.setDisabilityduration(CertificateConstants.TWO_YEARS_TELUGU);
	                            break;
	                        case 3:
	                            territorydto.setPeriod("3 years.");
	                            territorydto.setDisabilityduration(CertificateConstants.THREE_YEARS_TELUGU);
	                            break;
	                        case 4:
	                            territorydto.setPeriod("4 years.");
	                            territorydto.setDisabilityduration(CertificateConstants.FOUR_YEARS_TELUGU);
	                            break;
	                        case 5:
	                            territorydto.setPeriod("5 years.");
	                            territorydto.setDisabilityduration(CertificateConstants.FIVE_YEARS_TELUGU);
	                            break;
	                    }
	                }
	                if (rs.getInt("Disability_ID") != 2) {
	                    if (rs.getString("Congenital") != null && rs.getString("Congenital").equals("1")) {
	                        causeofDisabilityList.add(CertificateConstants.CONGENITAL);
	                    }
	                    if (rs.getString("Hereditary") != null && rs.getString("Hereditary").equals("1")) {
	                        causeofDisabilityList.add(CertificateConstants.HEREDITARY);
	                    }
	                    if (rs.getString("BirthInjury") != null && rs.getString("BirthInjury").equals("1")) {
	                        causeofDisabilityList.add(CertificateConstants.BIRTHINJURY);
	                    }
	                    if (rs.getString("Malnutrition") != null && rs.getString("Malnutrition").equals("1")) {
	                        causeofDisabilityList.add(CertificateConstants.MALNUTRITION);
	                    }
	                    if (rs.getString("Disease") != null && rs.getString("Disease").equals("1")) {
	                        causeofDisabilityList.add(CertificateConstants.DISEASEANDINFECTION);
	                    }
	                    if (rs.getString("Accident") != null && rs.getString("Accident").equals("1")) {
	                        causeofDisabilityList.add(CertificateConstants.ACCIDENT);
	                    }
	                    if (rs.getString("High_Fever") != null && !"".equals(rs.getString("High_Fever"))
	                            && rs.getString("High_Fever").equals("1")) {
	                        causeofDisabilityList.add(CertificateConstants.MENINGITIS);
	                    }
	                    if (rs.getString("Epilepsy") != null && !"".equals(rs.getString("Epilepsy"))
	                            && rs.getString("Epilepsy").equals("1")) {
	                        causeofDisabilityList.add(CertificateConstants.EPILEPSY);
	                    }

	                    if (rs.getString("Birth_Asphyxia") != null && !"".equals(rs.getString("Birth_Asphyxia"))
	                            && rs.getString("Birth_Asphyxia").equals("1")) {
	                        causeofDisabilityList.add(CertificateConstants.BIRTHASPHYXIA);
	                    }
	                    if (rs.getString("Other_Cause") != null && !"".equals(rs.getString("Other_Cause"))) {
	                        causeofDisabilityList.add(rs.getString("Other_Cause"));
	                    }

	                }
	                if (rs.getInt("Disability_ID") == 2 || (rs.getInt("Disability_ID") == 6
	                        && rs.getFloat("VisualImpairment") != 0)) {
	                    // added for VisuallImpairment Certificate
	                    if (rs.getString("Congenital_BetterEye") != null && !"".equals(rs.getString("Congenital_BetterEye"))
	                            && rs.getString("Congenital_BetterEye").equals("1")) {
	                        causeofDisabilityList.add(CertificateConstants.CONGENITAL_BETTEREYE);
	                    }
	                    if (rs.getString("Congenital_WorseEye") != null && !"".equals(rs.getString("Congenital_WorseEye"))
	                            && rs.getString("Congenital_WorseEye").equals("1")) {
	                        causeofDisabilityList.add(CertificateConstants.CONGENITAL_WORSEEYE);
	                    }
	                    if (rs.getString("Hereditary_BetterEye") != null && !"".equals(rs.getString("Hereditary_BetterEye"))
	                            && rs.getString("Hereditary_BetterEye").equals("1")) {
	                        causeofDisabilityList.add(CertificateConstants.HEREDITARY_BETTEREYE);
	                    }
	                    if (rs.getString("Hereditary_WorseEye") != null && !"".equals(rs.getString("Hereditary_WorseEye"))
	                            && rs.getString("Hereditary_WorseEye").equals("1")) {
	                        causeofDisabilityList.add(CertificateConstants.HEREDITARY_WORSEEYE);
	                    }
	                    if (rs.getString("BirthInjury_BetterEye") != null && !"".equals(rs.getString("BirthInjury_BetterEye"))
	                            && rs.getString("BirthInjury_BetterEye").equals("1")) {
	                        causeofDisabilityList.add(CertificateConstants.BIRTHINJURY_BETTEREYE);
	                    }
	                    if (rs.getString("BirthInjury_WorseEye") != null && !"".equals(rs.getString("BirthInjury_WorseEye"))
	                            && rs.getString("BirthInjury_WorseEye").equals("1")) {
	                        causeofDisabilityList.add(CertificateConstants.BIRTHINJURY_WORSEEYE);
	                    }

	                    if (rs.getString("Disease_BetterEye") != null && !"".equals(rs.getString("Disease_BetterEye"))
	                            && rs.getString("Disease_BetterEye").equals("1")) {
	                        causeofDisabilityList.add(CertificateConstants.DISEASEANDINFECTION_BETTEREYE);
	                    }
	                    if (rs.getString("Disease_WorseEye") != null && !"".equals(rs.getString("Disease_WorseEye"))
	                            && rs.getString("Disease_WorseEye").equals("1")) {
	                        causeofDisabilityList.add(CertificateConstants.DISEASEANDINFECTION_WORSEEYE);
	                    }
	                    if (rs.getString("Malnutrition_BetterEye") != null && !"".equals(rs.getString("Malnutrition_BetterEye"))
	                            && rs.getString("Malnutrition_BetterEye").equals("1")) {
	                        causeofDisabilityList.add(CertificateConstants.MALNUTRITION_BETTEREYE);
	                    }
	                    if (rs.getString("Malnutrition_WorseEye") != null && !"".equals(rs.getString("Malnutrition_WorseEye"))
	                            && rs.getString("Malnutrition_WorseEye").equals("1")) {
	                        territorydto.setMalnutrition_WorseEye(CertificateConstants.MALNUTRITION_WORSEEYE);
	                    }
	                    if (rs.getString("Accident_BetterEye") != null && !"".equals(rs.getString("Accident_BetterEye"))
	                            && rs.getString("Accident_BetterEye").equals("1")) {
	                        causeofDisabilityList.add(CertificateConstants.ACCIDENT_BETTEREYE);
	                    }
	                    if (rs.getString("Accident_WorseEye") != null && !"".equals(rs.getString("Accident_WorseEye"))
	                            && rs.getString("Accident_WorseEye").equals("1")) {
	                        causeofDisabilityList.add(CertificateConstants.ACCIDENT_WORSEEYE);
	                    }
	                    if (rs.getString("Other_Cause") != null && !"".equals(rs.getString("Other_Cause"))) {
	                        causeofDisabilityList.add(rs.getString("Other_Cause"));
	                    }
	                }
	                if (causeofDisabilityList != null && !causeofDisabilityList.isEmpty()) {
	                    String totalcauseofDisability =  separatewithcamma(causeofDisabilityList);
	                    territorydto.setTotalcauseofdisabilities(totalcauseofDisability);
	                }
	                int number = new Double((rs.getDouble("TotalDisability"))).intValue();
	                numberInwords =  NumbertoWord(number);
	                if (numberInwords != null && !"".equals(numberInwords)) {
	                    territorydto.setPercentageInWord(numberInwords + " " + "percent");
	                }
	                territorydto.setDisabilityPercentage(number);
	                territorydto.setDisabilityvalue(rs.getDouble("TotalDisability"));
	                if (null != rs.getString("Global_Score") && !"0".equals(rs.getString("Global_Score"))) {
	                    territorydto.setGlobal_Disablity(rs.getString("Global_Score"));
	                    String GDInwords =  NumbertoWord(Integer.parseInt(territorydto.getGlobal_Disablity()));
	                    territorydto.setGlobal_Disablity_InWords(GDInwords);
	                }
	                if (null != rs.getString("Mental_Age") && !"0".equals(rs.getString("Mental_Age"))) {
	                    String roundedMentalretardation = String.valueOf(Math.round(Double.parseDouble(rs.getString("Mental_Age"))));
	                    territorydto.setMentalRetardation_IQ(roundedMentalretardation);
	                    String mentalRetardationInwords =  NumbertoWord(Integer.parseInt(roundedMentalretardation));
	                    if (null != mentalRetardationInwords && !"".equals(mentalRetardationInwords)) {
	                        territorydto.setMentalRetardation_IQ_Inwords(mentalRetardationInwords);
	                    }
	                }
	                if (null != rs.getString("AirConduction_Left_Ear_DB_Level") && null != rs.getString("AirConduction_Right_Ear_DB_Level")) {
	                    rightEarDB = rs.getString("AirConduction_Right_Ear_DB_Level");
	                    leftEarDB = rs.getString("AirConduction_Left_Ear_DB_Level");
	                    if ((null != rightEarDB && !"".equals(rightEarDB)) && (null != leftEarDB && !"".equals(leftEarDB))) {
	                        if (Integer.parseInt(rightEarDB) <= Integer.parseInt(leftEarDB)) {
	                            territorydto.setHearing_DB(rightEarDB);
	                        } else if (Integer.parseInt(leftEarDB) <= Integer.parseInt(rightEarDB)) {
	                            territorydto.setHearing_DB(leftEarDB);
	                        }
	                        String dbInwords =  NumbertoWord(Integer.parseInt(territorydto.getHearing_DB()));
	                        if (null != dbInwords && !"".equals(dbInwords)) {
	                            territorydto.setHearing_DB_In_Words(dbInwords);
	                        }
	                    }
	                }
	                if (rs.getString("Created_Date") != null) {
	                    String formdate = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd ").parse(rs.getString("Created_Date")));
	                    territorydto.setDateofisse(formdate);
	                }
	                districtName =  convertFirstLetterToUpperCase(rs.getString("District_Name"));
	                if (null != districtName && !"".equals(districtName)) {
	                    territorydto.setDistrict_name(districtName);
	                }
	                mandalName =  convertFirstLetterToUpperCase(rs.getString("Mandal_Name"));
	                if (null != mandalName && !"".equals(mandalName)) {
	                    territorydto.setMandal_name(mandalName);
	                }
	                vilageName =  convertFirstLetterToUpperCase(rs.getString("Village_Name"));
	                if (null != vilageName && !"".equals(vilageName)) {
	                    territorydto.setVillage_name(vilageName);
	                }
	                habitaionName =  convertFirstLetterToUpperCase(rs.getString("Habitation_Name"));
	                if (null != habitaionName && !"".equals(habitaionName)) {
	                    territorydto.setHabitation_name(habitaionName);
	                }
	                rs.close();
	                if ("Physical(Locomotor/Orthopaedic) Disability".equals(territorydto.getDisabilitytype())
	                        || "Physical(Locomotor/Orthopaedic) Disability".equals(territorydto.getPhysicalImpairmentName())) {

	                    cstmt = con.prepareCall("{Call SP_SELECTDATAFORLOCOMOTORCERTIFICATE(?)}");
	                    cstmt.setString(1, personcode);
	                    rs = cstmt.executeQuery();
	                    while (rs.next()) {
	                        locomotorarmlegeffectedList = new ArrayList();
	                        if (rs.getInt("LIFTING_OVERHEAD_OBJECTS") == 9 || rs.getInt("TOUCHING_NOSE") == 9 || rs.getInt("EATINGSTYLE") == 9
	                                || rs.getInt("COMBINGANDPLAITING") == 9 || rs.getInt("PUTTING_ON_A_SHIRT") == 9 || rs.getInt("ABLUTIONGLASS") == 9
	                                || rs.getInt("BUTTONING") == 9 || rs.getInt("TIENARADHOTI") == 9 || rs.getInt("WRITING") == 9 || rs.getInt("DRINKINGWATER") == 9
	                                || rs.getInt("WORKING_ON_PLANE") == 5 || rs.getInt("WORKING_ON_PLANE") == 10 || rs.getInt("WALKING_ON_SLOPE") == 5 || rs.getInt("WALKING_ON_SLOPE") == 10
	                                || rs.getInt("CLIMBING_STAIRS") == 5 || rs.getInt("CLIMBING_STAIRS") == 10 || rs.getInt("STANDING_BOTHLEGS") == 5 || rs.getInt("STANDING_BOTHLEGS") == 10
	                                || rs.getInt("STANDING_EFFECTED_LEG") == 5 || rs.getInt("STANDING_EFFECTED_LEG") == 10 || rs.getInt("SQUATTING_FLOOR") == 5 || rs.getInt("SQUATTING_FLOOR") == 10
	                                || rs.getInt("SITTINGCROSS_LEG") == 5 || rs.getInt("SITTINGCROSS_LEG") == 10 || rs.getInt("KNEELING") == 5 || rs.getInt("KNEELING") == 10
	                                || rs.getInt("TAKING_TURNS") == 5 || rs.getInt("TAKING_TURNS") == 10) {
	                            locomotorarmlegeffectedList.add("Impaired reach");
	                        }
	                        if (rs.getInt("STRENGTH_GRIPSTRENGTH_RIGHTSIDE") == 20 || rs.getInt("STRENGTH_GRIPSTRENGTH_LEFTSIDE") == 20
	                                || rs.getInt("STRENGTH_PINCHSTRENGTH_RIGHTSIDE") == 10 || rs.getInt("STRENGTH_PINCHSTRENGTH_LEFTSIDE") == 10) {
	                            locomotorarmlegeffectedList.add("Weakness of grip");
	                        }
	                        if (rs.getInt("SEVERITYOF_ATAXIA") == 25 || rs.getInt("SEVERITYOF_ATAXIA") == 50 || rs.getInt("SEVERITYOF_ATAXIA") == 75
	                                || rs.getInt("SEVERITYOF_ATAXIA") == 100) {
	                            locomotorarmlegeffectedList.add("Ataxia");
	                        }
	                        if (locomotorarmlegeffectedList != null && !locomotorarmlegeffectedList.isEmpty()) {
	                            String totallocomotorarmlegeffectedlist =  separatewithcamma(locomotorarmlegeffectedList);
	                            territorydto.setTotallocomotorarmlegeffectedlist(totallocomotorarmlegeffectedlist);
	                        }
	                    }
	                }

	                reportlist.add(territorydto);
	            }
	            /*  cs = con.prepareStatement("select Railway_Certificate  from tblAllDisabilityPerson_Common_Needs where Person_Code='" + personcode + "'");
	            rs = cs.executeQuery();
	            while (rs.next()) {
	            territorydto.setRailwaycertificate(rs.getBoolean("Railway_Certificate"));
	            }*/
	        }
	        catch (SQLException sqlEx) 
	        {
	        	log.error(sqlEx);
	            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDetailsForCertificate", "ReportDAO", "DataBase");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getDetailsForCertificate");
	        }
	        catch (Exception e) 
	        {
	        	log.error(e);
	            int exResult = ExceptionDAO.saveException(ds, e.toString(), "getDetailsForCertificate", "ReportDAO", "Code");
	            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getDetailsForCertificate");
	        }
	        finally 
	        {
	            DBConnection.closeConnection(con);
	            DBConnection.closeResultSet(rs);
	            DBConnection.closeStatement(cstmt);

	            DBConnection.closeStatement(cs);
	        }
	        return reportlist;
	    }
	   
	   
	   public String NumbertoWord(int Str) 
	   {
	        String numberInWord = null;
	        int convettingNumber = 0;
	        convettingNumber = (Str);
	        if (convettingNumber == 0) {
	            numberInWord = "Zero";
	        }
	        if (convettingNumber == 100) {
	            numberInWord = "Hundred";
	        }
	        if (convettingNumber >= 0 && convettingNumber <= 99) {
	            if (convettingNumber >= 1 && convettingNumber <= 19) {
	                numberInWord = NumberInSingleDigit[convettingNumber];
	            } else if (convettingNumber >= 20 && convettingNumber <= 99) {
	                numberInWord = NumberInTensDigit[(convettingNumber / 10) - 2];
	                numberInWord =
	                        numberInWord + NumberInSingleDigit[convettingNumber - ((convettingNumber / 10) * 10)];
	            }
	        } else if (convettingNumber > 100 && convettingNumber <= 999) {
	            numberInWord = NumberInSingleDigit[(convettingNumber / 100)];
	            if ("00".equals(String.valueOf(convettingNumber).substring(1, 3).toString())) {
	                numberInWord = numberInWord + "Hundred";
	            } else {
	                numberInWord =
	                        numberInWord + "Hundred " + NumbertoWord(Integer.parseInt(String.valueOf(convettingNumber).substring(1, 3).toString()));
	            }
	        }

	        return numberInWord;
	    }


	    public String separatewithcamma(List causeofDisabilityList) {

	        StringBuffer stringBuffer = new StringBuffer();
	        if (causeofDisabilityList != null && !causeofDisabilityList.isEmpty()) {

	            for (int i = 0; i < causeofDisabilityList.size(); i++) {
	                String causename = (String) causeofDisabilityList.get(i);
	                stringBuffer.append(causename);
	                if (i + 1 < causeofDisabilityList.size()) {
	                    if (causeofDisabilityList.get(i + 1) != null) {
	                        stringBuffer.append(",");
	                    }
	                } else {
	                    stringBuffer.append(".");
	                }
	            }
	        } 
	        return stringBuffer.toString();
	    }
	   
	    
	    public String separatewithcammamultiple(List causeofDisabilityList) throws SADAREMDBException, SQLException {

	        List removedDuplicatesList = new ArrayList();
	        String stringBuffer = null;
	        String physicalRequirement = null;
	        try {
	            if (causeofDisabilityList != null && !causeofDisabilityList.isEmpty()) {

	                for (int i = 0; i < causeofDisabilityList.size(); i++) {
	                    String causenametemp = (String) causeofDisabilityList.get(i);
	                    if (causenametemp != null && !causenametemp.isEmpty()) {
	                        String causename = causenametemp.replace(".", "");
	                        String[] causenamearr = causename.split(",");
	                        for (int j = 0; j < causenamearr.length; j++) {
	                            removedDuplicatesList.add(causenamearr[j]);

	                        }
	                    }
	                }
	                Set<String> removedDuplicateset = new HashSet<String>(removedDuplicatesList);
	                // List physicalRequirementList = new ArrayList(removedDuplicateset);
	                stringBuffer = removedDuplicateset.toString();
	                String causenamestringBuffer = stringBuffer.replace("[", "");
	                physicalRequirement = causenamestringBuffer.replace("]", "");
	                //stringBuffer = separatewithcamma(physicalRequirementList);
	            }
	        } catch (Exception sqlEx) {
	        }
	        return physicalRequirement;
	    }
	    
	   public String convertFirstLetterToUpperCase(String inputString) {
	        String name = null;
	        if (null != inputString && !"".equals(inputString)) {
	            StringBuffer inputStrBuffer = new StringBuffer(inputString);
	            String firstLetterUpperCase = inputStrBuffer.substring(0, 1).toUpperCase();
	            inputStrBuffer.replace(0, 1, firstLetterUpperCase);
	            name = inputStrBuffer.toString();
	        }
	        return name;
	    }

		
	    public TerritoryDTO locomotorDetails(ResultSet rs, TerritoryDTO territorydto) throws SADAREMDBException, SQLException {
	        List locomotorSubDisabilityList = null;
	        List locomotorSubSubDisabilityList = null;
	        List locomotorarmlegeffectedList = null;
	        List locomotorphysicalrequirements = null;
	        try {
	            locomotorSubDisabilityList = new ArrayList();
	            locomotorphysicalrequirements = new ArrayList();
	            territorydto.setDisabilitytype(CertificateConstants.LOCOMOTOR_DISABILITYTYPE);
	            territorydto.setPhysicalImpairmentName(CertificateConstants.LOCOMOTOR_DISABILITYTYPE);
	            territorydto.setDisabilityTypeInIdCard(CertificateConstants.IDCARD_LOCOMOTOR_DISABILITYTYPE);
	            territorydto.setDisabilityTypeInTelugu(CertificateConstants.LOCOMOTOR_DISABILITYTYPE_TELUGU);
	            // territorydto.setDisabilityintellec("Physical Impairment");
	            //  territorydto.setLoco("locomotor");
	            territorydto.setComment("yes");

	            //added by bapinaidu for geting subdisabilities for locomotor disability.
	            if (rs.getString("Sub_Disability_ID") != null && !"".equals(rs.getString("Sub_Disability_ID"))) {
	                String[] sp = rs.getString("Sub_Disability_ID").split(",");
	                for (int i = 0; i < sp.length; i++) {
	                    int k = Integer.parseInt(sp[i]);
	                    switch (k) {
	                        case 1:
	                            locomotorSubDisabilityList.add("Cerebral Palsy (CP)");
	                            break;
	                        case 2:
	                            locomotorSubDisabilityList.add("Post Polio Residual Paralysis (PPRP)");
	                            break;
	                        case 3:
	                            locomotorSubDisabilityList.add("Post Hansen's Disease Sequel");
	                            break;
	                        case 4:
	                            locomotorSubDisabilityList.add("Muscular Dystrophy");
	                            break;
	                        case 5:
	                            locomotorSubDisabilityList.add("Post Traumatic Amputation");
	                            break;
	                        case 6:
	                            locomotorSubDisabilityList.add("Post Traumatic Sequel-Limbs");
	                            break;
	                        case 7:
	                            locomotorSubDisabilityList.add("Post Traumatic Sequel-Spine");
	                            break;
	                        case 8:
	                            locomotorSubDisabilityList.add("Post Head Injury Sequel");
	                            break;
	                        case 9:
	                            locomotorSubDisabilityList.add("Post Burn Injury Sequel");
	                            break;
	                        case 10:
	                            locomotorSubDisabilityList.add("Cerebro-Vascular Accidents");
	                            break;
	                        case 11:
	                            locomotorSubDisabilityList.add("Dwarfism");
	                            break;
	                        case 12:
	                            locomotorSubDisabilityList.add("Congenital Deformities of Limbs");
	                            break;
	                        case 13:
	                            locomotorSubDisabilityList.add("Congenital Deformities of Spine");
	                            break;
	                        case 14:
	                            locomotorSubDisabilityList.add("Cardio Pulmonary Disease");
	                            break;
	                        case 15:
	                            if (!"null".equals(rs.getString("Any_Other")) && rs.getString("Any_Other") != null
	                                    && !"".equals(rs.getString("Any_Other"))) {
	                                locomotorSubDisabilityList.add(rs.getString("Any_Other"));
	                            }
	                            break;
	                        case 32:
	                            locomotorSubDisabilityList.add("Muscular Weakness");
	                            break;
	                        default:
	                            break;
	                    }
	                }
	                String locomotorsubdisabilities = separatewithcamma(locomotorSubDisabilityList);
	                territorydto.setTotalsubdisabilities(locomotorsubdisabilities);
	                territorydto.setPhysicalImpairmentTotalsubdisabilities(locomotorsubdisabilities);
	            }


	            if (rs.getString("Sub_Sub_Disability_ID") != null && !"".equals(rs.getString("Sub_Sub_Disability_ID"))) {
	                locomotorSubSubDisabilityList = new ArrayList();
	                String[] ssd = rs.getString("Sub_Sub_Disability_ID").split(",");
	                for (int j = 0; j < ssd.length; j++) {
	                    int s = Integer.parseInt(ssd[j]);
	                    switch (s) {
	                        case 1:
	                            locomotorSubSubDisabilityList.add("Right Upper Limb");
	                            break;
	                        case 2:
	                            locomotorSubSubDisabilityList.add("Left Upper Limb");
	                            break;
	                        case 3:
	                            locomotorSubSubDisabilityList.add("Bilateral Upper Limb");
	                            break;
	                        case 4:
	                            locomotorSubSubDisabilityList.add("Right Lower Limb");
	                            break;
	                        case 5:
	                            locomotorSubSubDisabilityList.add("Left Lower Limb");
	                            break;
	                        case 6:
	                            locomotorSubSubDisabilityList.add("Bilateral Lower Limb");
	                            break;
	                        case 7:
	                            locomotorSubSubDisabilityList.add("Spine");
	                            break;
	                        case 8:
	                            locomotorSubSubDisabilityList.add("Trunk");
	                            break;
	                        case 9:
	                            locomotorSubSubDisabilityList.add("All 4 Limbs");
	                            break;
	                        case 10:
	                            locomotorSubSubDisabilityList.add("Whole Body");
	                            break;
	                        case 11:
	                            locomotorSubSubDisabilityList.add("Hips");
	                            break;
	                    }
	                }
	                String locomotosubsubdisabilities = separatewithcamma(locomotorSubSubDisabilityList);
	                territorydto.setTotalsubsubdisabilities(locomotosubsubdisabilities);
	            }

	            if ("1".equals(rs.getString("F_Can"))) {
	                locomotorphysicalrequirements.add("F-can perform work by manipulating with fingers");
	            }
	            if ("1".equals(rs.getString("PP_Can"))) {
	                locomotorphysicalrequirements.add("PP-can perform work by pulling and pushing");
	            }
	            if ("1".equals(rs.getString("L_Can"))) {
	                locomotorphysicalrequirements.add("L-can perform work by lifting");
	            }
	            if ("1".equals(rs.getString("KC_Can"))) {
	                locomotorphysicalrequirements.add("KC-can perform work by kneeling and crouching");
	            }
	            if ("1".equals(rs.getString("B_Can"))) {
	                locomotorphysicalrequirements.add("B-can perform work by bending");
	            }
	            if ("1".equals(rs.getString("S_Can"))) {
	                locomotorphysicalrequirements.add("S-can perform work by sitting");
	            }
	            if ("1".equals(rs.getString("ST_Can"))) {
	                locomotorphysicalrequirements.add("ST-can perform work by standing");
	            }
	            if ("1".equals(rs.getString("W_Can"))) {
	                locomotorphysicalrequirements.add("W-can perform work by walking");
	            }
	            if ("1".equals(rs.getString("RW_Can"))) {
	                locomotorphysicalrequirements.add("RW-can perform work by reading and writing");
	            }
	            if (locomotorphysicalrequirements != null && !locomotorphysicalrequirements.isEmpty()) {
	                String locomotorphysicalrequirementdetails = separatewithcamma(locomotorphysicalrequirements);
	                territorydto.setPhysicalImpairmentPhysicalRequirementList(locomotorphysicalrequirementdetails);
	            }


	        } catch (Exception sqlEx) {
	            // sqlEx.printStackTrace();
	        }
	        return territorydto;
	    }

		 public TerritoryDTO hearingDetails(ResultSet rs, TerritoryDTO territorydto) throws SADAREMDBException, SQLException {

	        String disabilitycondition = null;
	        List locomotorphysicalrequirements = null;

	        try {
	            locomotorphysicalrequirements = new ArrayList();
	            territorydto.setDisabilitytype(CertificateConstants.HEARING_DISABILITYTYPE);
	            //territorydto.setDisabilityintellec("Hearing/Speech Impairment");
	            territorydto.setHearingImpairmentName(CertificateConstants.HEARING_DISABILITYTYPE);
	            territorydto.setDisabilityTypeInIdCard(CertificateConstants.HEARING_DISABILITYTYPE);
	            territorydto.setDisabilityTypeInTelugu(CertificateConstants.HEARING_DISABILITYTYPE_TELUGU);
	            if ("1".equals(rs.getString("H_Can"))) {
	                locomotorphysicalrequirements.add("H-can perform work by hearing/speaking");
	            }
	            if (locomotorphysicalrequirements != null && !locomotorphysicalrequirements.isEmpty()) {
	                String locomotorphysicalrequirementdetails = separatewithcamma(locomotorphysicalrequirements);
	                territorydto.setHearingImpairmentPhysicalRequirementList(locomotorphysicalrequirementdetails);
	            }
	            if (rs.getDouble("TotalDisability") >= 40.0 && rs.getDouble("TotalDisability") <= 50.0) {
	                disabilitycondition = CertificateConstants.MODERATE_DISABILITYTYPE;
	            }
	            if (rs.getDouble("TotalDisability") >= 51.0 && rs.getDouble("TotalDisability") <= 70.0) {
	                disabilitycondition = CertificateConstants.SEVERE_DISABILITYTYPE;

	            }
	            if (rs.getDouble("TotalDisability") >= 71.0 && rs.getDouble("TotalDisability") <= 100.0) {
	                disabilitycondition = CertificateConstants.PROFOUND_DISABILITYTYPE;
	            }
	            if (disabilitycondition != null && !"".equals(disabilitycondition)) {
	                territorydto.setDisabilitycondition(disabilitycondition);
	                territorydto.setHearingImpairmentCondition(disabilitycondition);

	            }
	        } catch (SQLException sqlEx) {
	        } catch (Exception sqlEx) {
	            //  sqlEx.printStackTrace();
	        }
	        return territorydto;
	    }

	    public TerritoryDTO visualDetails(ResultSet rs, TerritoryDTO territorydto) throws SADAREMDBException, SQLException {

	        List locomotorphysicalrequirements = null;
	        List locomotorSubDisabilityList = null;
	        try {
	            locomotorSubDisabilityList = new ArrayList();
	            locomotorphysicalrequirements = new ArrayList();
	            territorydto.setDisabilitytype(CertificateConstants.VISUAL_DISABILITYTYPE);
	            territorydto.setVisualImpairmentName(CertificateConstants.VISUAL_DISABILITYTYPE);
	            territorydto.setComment("yes");
	            if (territorydto.getVisualImpairmentPercentage() == 40) {
	                territorydto.setVisualImpairmentTotalsubdisabilities("Low Vision");
	                territorydto.setDisabilityTypeInIdCard(CertificateConstants.IDCARD_VISUAL_LOWVISION_DISABILITYTYPE);
	                territorydto.setDisabilityTypeInTelugu(CertificateConstants.VISUAL_DISABILITYTYPE_LOWVISION_TELUGU);
	            } else if (territorydto.getVisualImpairmentPercentage() == 75 || territorydto.getVisualImpairmentPercentage() == 100 || territorydto.getVisualImpairmentPercentage() == 30) {
	                territorydto.setVisualImpairmentTotalsubdisabilities("Blind ness");
	                territorydto.setDisabilityTypeInIdCard(CertificateConstants.IDCARD_VISUAL_BLINDNESS_DISABILITYTYPE);
	                territorydto.setDisabilityTypeInTelugu(CertificateConstants.VISUAL_DISABILITYTYPE_BLINDNESS_TELUGU);
	            }
	            if ("1".equals(rs.getString("RW_Can"))) {
	                locomotorphysicalrequirements.add("RW-can perform work by reading and writing");
	            }
	            if ("1".equals(rs.getString("SE_Can"))) {
	                locomotorphysicalrequirements.add("SE-can perform work by seeing");
	            }
	            if (locomotorphysicalrequirements != null && !locomotorphysicalrequirements.isEmpty()) {
	                String locomotorphysicalrequirementdetails = separatewithcamma(locomotorphysicalrequirements);
	                territorydto.setVisualImpairmentPhysicalRequirementList(locomotorphysicalrequirementdetails);
	            }

	        } catch (Exception sqlEx) {
	            // sqlEx.printStackTrace();
	        }
	        return territorydto;
	    }

	    public TerritoryDTO mentalRetardationDetails(ResultSet rs, TerritoryDTO territorydto) throws SADAREMDBException, SQLException {

	        List locomotorphysicalrequirements = null;
	        String disabilitycondition = null;
	        try {
	            locomotorphysicalrequirements = new ArrayList();
	            territorydto.setDisabilitytype(CertificateConstants.MENTALRETARDATION_DISABILITYTYPE);
	            // territorydto.setDisabilityintellec("Mental Retardation");
	            territorydto.setMentalRetardationName(CertificateConstants.MENTALRETARDATION_DISABILITYTYPE);
	            territorydto.setDisabilityTypeInIdCard(CertificateConstants.MENTALRETARDATION_DISABILITYTYPE);
	            territorydto.setDisabilityTypeInTelugu(CertificateConstants.MENTALRETARDATION_DISABILITYTYPE_TELUGU);
	            if ("1".equals(rs.getString("RW_Can"))) {
	                locomotorphysicalrequirements.add("RW-can perform work by reading and writing");
	            }
	            if (locomotorphysicalrequirements != null && !locomotorphysicalrequirements.isEmpty()) {
	                String locomotorphysicalrequirementdetails = separatewithcamma(locomotorphysicalrequirements);
	                territorydto.setMentalRetardationPhysicalRequirementList(locomotorphysicalrequirementdetails);
	            }
	            if (rs.getDouble("TotalDisability") == 50.0) {
	                disabilitycondition = CertificateConstants.MILD_DISABILITYTYPE;
	            }

	            if (rs.getDouble("TotalDisability") == 75.0) {
	                disabilitycondition = CertificateConstants.MODERATE_DISABILITYTYPE;
	            }

	            if (rs.getDouble("TotalDisability") == 90.0) {
	                disabilitycondition = CertificateConstants.SEVERE_DISABILITYTYPE;
	            }

	            if (rs.getDouble("TotalDisability") == 100.0) {
	                disabilitycondition = CertificateConstants.PROFOUND_DISABILITYTYPE;
	            }
	            if (disabilitycondition != null && !"".equals(disabilitycondition)) {
	                territorydto.setDisabilitycondition(disabilitycondition);
	                territorydto.setMentalRetardationCondition(disabilitycondition);
	            }
	        } catch (Exception sqlEx) {
	            // sqlEx.printStackTrace();
	        }
	        return territorydto;
	    }

	    public TerritoryDTO mentalIllnessDetails(ResultSet rs, TerritoryDTO territorydto) throws SADAREMDBException, SQLException {

	        String disabilitycondition = null;
	        try {
	            territorydto.setDisabilitytype(CertificateConstants.MENTALILLNESS_DISABILITYTYPE);
	            // territorydto.setDisabilityintellec("Mental Illness");
	            territorydto.setMentalIllnessName(CertificateConstants.MENTALILLNESS_DISABILITYTYPE);
	            territorydto.setDisabilityTypeInIdCard(CertificateConstants.MENTALILLNESS_DISABILITYTYPE);
	            territorydto.setDisabilityTypeInTelugu(CertificateConstants.MENTALILLNESS_DISABILITYTYPE_TELUGU);

	            if (rs.getDouble("TotalDisability") >= 40.0 && rs.getDouble("TotalDisability") <= 70.0) {
	                disabilitycondition = CertificateConstants.MODERATE_DISABILITYTYPE;
	            }
	            if (rs.getDouble("TotalDisability") >= 71.0 && rs.getDouble("TotalDisability") <= 99.0) {
	                disabilitycondition = CertificateConstants.SEVERE_DISABILITYTYPE;
	            }
	            if (rs.getDouble("TotalDisability") == 100.0) {
	                disabilitycondition = CertificateConstants.PROFOUND_DISABILITYTYPE;
	            }
	            if (disabilitycondition != null && !"".equals(disabilitycondition)) {
	                territorydto.setDisabilitycondition(disabilitycondition);
	                territorydto.setMentalIllnessCondition(disabilitycondition);
	            }

	        } catch (Exception sqlEx) {
	            // sqlEx.printStackTrace();
	        }
	        return territorydto;
	    }
	  
	    
	    public ArrayList getRejectedData( String personcode)throws SADAREMDBException, SQLException 
	    {
	        ArrayList rejectedlist = new ArrayList();
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Connection con = null;
	        String status = "Rejected";
	        CertificateDTO certificatedto = null;
	        try {
	            con = DBConnection.getConnection();

//	            String query = "select P.Person_Code, P.First_Name,P.Surname,P.Gender,P.Relation_Name,P.House_Number,"
//	                    + "D.District_Name,M.Mandal_Name,V.Village_Name,H.Habitation_Name,P.Relationship,R.First_Doctor_Name,"
//	                    + "R.First_Doctor_Designation,R.First_Doctor_RegNumber,P.Created_Date,R.Disability_ID,R.Hospital_Name,R.Hospital_Address,P.Marital_Status from "
//	                    + "tblPerson_Personal_Details P,tblDistrict_Details D,"
//	                    + "tblMandal_Details M,tblVillage_Details V,tblHabitation_Details H,tblRejectPerson_Details R where "
//	                    + " P.Person_Code = R.Person_Code and P.District_ID = D.District_ID and P.Mandal_ID = M.Mandal_ID and "
//	                    + "P.District_ID = M.District_ID and P.Village_ID = V.Village_ID and P.District_ID = V.District_ID and"
//	                    + " P.Mandal_ID = V.Mandal_ID and P.Habitation_ID = H.Habitation_ID and P.District_ID = H.District_ID and"
//	                    + " P.Mandal_ID = H.Mandal_ID and P.Village_ID = H.Village_ID and  P.Person_Code ='" + personcode + "' and P.Person_Status='"+status+"'";

	            /** String query = "select P.Person_Code, P.First_Name,P.Surname,P.Gender,P.Relation_Name,"
	            + "P.House_Number,D.District_Name,M.Mandal_Name,V.Village_Name,H.Habitation_Name,"
	            + "P.Relationship,R.First_Doctor_Name,R.First_Doctor_Designation,R.First_Doctor_RegNumber,"
	            + "R.Created_Date,R.Disability_ID,R.Hospital_Name,R.Hospital_Address,P.Marital_Status "
	            + "from tblPerson_Personal_Details P,tblRejectPerson_Details R,tblDistrict_Details "
	            + "D,tblMandal_Details M,tblVillage_Details V,tblHabitation_Details H where"
	            + " R.PERSON_CODE   = P.PERSON_CODE   AND D.DISTRICT_ID   = P.DISTRICT_ID AND "
	            + "M.DISTRICT_ID  = P.DISTRICT_ID AND M.MANDAL_ID  = P.MANDAL_ID  AND"
	            + " V.DISTRICT_ID   = P.DISTRICT_ID AND V.MANDAL_ID = P.MANDAL_ID AND"
	            + " V.VILLAGE_ID = P.VILLAGE_ID AND H.DISTRICT_ID = P.DISTRICT_ID AND "
	            + "H.MANDAL_ID = P.MANDAL_ID AND H.VILLAGE_ID = P.VILLAGE_ID AND "
	            + "H.HABITATION_ID = P.HABITATION_ID AND P.STATUS = 'Active'AND "
	            + "R.STATUS = 'Active' AND P.PERSON_CODE = '" + personcode + "'"; */
	            String query = "SELECT P.Person_Code,P.First_Name,P.Surname,P.Gender,P.Relation_Name, "
	                    + "P.House_Number,D.District_Name,M.Mandal_Name,V.Village_Name, "
	                    + "H.Habitation_Name,P.Relationship,R.First_Doctor_Name, "
	                    + "R.First_Doctor_Designation,R.First_Doctor_RegNumber, "
	                    + "R.Created_Date,R.Disability_ID,R.Hospital_Name,R.Hospital_Address, "
	                    + "P.Marital_Status,case when (DATEADD(DD,0,DATEDIFF(DD,0,r.Created_Date)))>'06/01/2014' then 1 else 0  end flag FROM "
	                    + "tblPerson_Personal_Details P  with (nolock) , "
	                    + "tblRejectPerson_Details R with(nolock) , "
	                    + "tblDistrict_Details D with(nolock) , "
	                    + "tblMandal_Details M with(nolock) , "
	                    + "tblVillage_Details V with(nolock)  , "
	                    + "tblHabitation_Details H with(nolock)  "
	                    + "WHERE "
	                    + "R.PERSON_CODE = P.PERSON_CODE AND "
	                    + "D.DISTRICT_ID = P.District_ID AND "
	                    + "M.DISTRICT_ID = P.District_ID AND "
	                    + "M.MANDAL_ID = P.Mandal_ID AND "
	                    + "V.DISTRICT_ID = P.District_ID AND "
	                    + "V.MANDAL_ID = P.Mandal_ID AND "
	                    + "V.VILLAGE_ID = P.Village_ID AND "
	                    + "H.HABITATION_CODE = P.HABCODE AND "
	                    + "P.STATUS = 'Active' AND "
	                    + "R.STATUS = 'Active' AND "
	                    + "P.PERSON_CODE = ?";

	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, personcode);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                certificatedto = new CertificateDTO();
	                certificatedto.setPersoncode(rs.getString(1));
	                String FirstName = convertFirstLetterToUpperCase(rs.getString("First_Name"));
	                // String LastName=convertFirstLetterToUpperCase(rs.getString("Last_Name"));
	                String Surname = convertFirstLetterToUpperCase(rs.getString("Surname"));
	                String Name = Surname + " " + FirstName;
	                certificatedto.setName(Name);
	                if (rs.getString(4).equals("1")) {
	                    certificatedto.setMaritialstatus("Shri");
	                    if (rs.getString(11).equals("1")) {
	                        certificatedto.setRelationship("S/o");
	                    } else if (rs.getString(11).equals("2")) {
	                        certificatedto.setRelationship("S/o");
	                    } else if (rs.getString(11).equals("3")) {
	                        certificatedto.setRelationship("G/o");
	                    } else if (rs.getString(11).equals("4")) {
	                        certificatedto.setRelationship("G/o");
	                    } else if (rs.getString(11).equals("5")) {
	                        certificatedto.setRelationship("G/o");
	                    } else if (rs.getString(11).equals("6")) {
	                        certificatedto.setRelationship("G/o");
	                    } else if (rs.getString(11).equals("7")) {
	                        certificatedto.setRelationship("H/o");
	                    }
	                } else {
	                    if (rs.getString("Marital_Status").equals("1")) {
	                        certificatedto.setMaritialstatus("Smt");
	                    } else if (rs.getString("Marital_Status").equals("2")) {
	                        certificatedto.setMaritialstatus("Kumari");
	                    } else if (rs.getString("Marital_Status").equals("3")
	                            || rs.getString("Marital_Status").equals("4")
	                            || rs.getString("Marital_Status").equals("5")) {
	                        certificatedto.setMaritialstatus("Ms");
	                    }

	                    if (rs.getString(11).equals("1")) {
	                        certificatedto.setRelationship("D/o");
	                    } else if (rs.getString(11).equals("2")) {
	                        certificatedto.setRelationship("D/o");
	                    } else if (rs.getString(11).equals("3")) {
	                        certificatedto.setRelationship("W/o");
	                    } else if (rs.getString(11).equals("4")) {
	                        certificatedto.setRelationship("G/o");
	                    } else if (rs.getString(11).equals("5")) {
	                        certificatedto.setRelationship("G/o");
	                    } else if (rs.getString(11).equals("6")) {
	                        certificatedto.setRelationship("G/o");
	                    } else if (rs.getString(11).equals("7")) {
	                        certificatedto.setRelationship("H/o");
	                    }
	                }
	                certificatedto.setRelationname(convertFirstLetterToUpperCase(rs.getString(5)));
	                certificatedto.setHousenumber(rs.getString(6));
	                certificatedto.setDistrictname(rs.getString(7));

	                certificatedto.setMandalname(convertFirstLetterToUpperCase(rs.getString(8)));

	                certificatedto.setVillagename(convertFirstLetterToUpperCase(rs.getString(9)));
	                certificatedto.setHabitationname(convertFirstLetterToUpperCase(rs.getString(10)));

	                certificatedto.setDoctorname(convertFirstLetterToUpperCase(rs.getString(12)));
	                certificatedto.setDoctordesignation(rs.getString(13));
	                certificatedto.setDoctornumber(rs.getString(14));
	                String formdate = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd ").parse(rs.getString(15)));
	                certificatedto.setIssuedate(formdate);
	                certificatedto.setHospitalname(convertFirstLetterToUpperCase(rs.getString("Hospital_Name")));
	                certificatedto.setHospitaladdress(convertFirstLetterToUpperCase(rs.getString("Hospital_Address")));
	                certificatedto.setDisabilityidforrejecte(rs.getString(16));
	                certificatedto.setFlag(rs.getString("flag"));
	                rejectedlist.add(certificatedto);
	            }

	        } catch (SQLException sqlEx) 
	        {
	           log.error(sqlEx);
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedData");
	        } 
	        catch (Exception sqlEx)
	        {
	            log.error(sqlEx);
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedData");
	        } 
	        finally 
	        {
	            DBConnection.closeConnection(con);
	            DBConnection.closeResultSet(rs);
	            DBConnection.closeStatement(pstmt);

	        }
	        return rejectedlist;
	    }
	    
	    //------------------------------------------------ Disability Details ------------------------------
	    
	    /*
	     * This method will get Locomotor Pwd data
	     *  for Certificate from database
	     * @param ds 
	     * @param personcode 
	     * @throws org.bf.disability.Exception.SADAREMException 
	     * @return list
	     */

	    public ArrayList getLocomotorData(String personcode)throws SADAREMDBException, SQLException 
	    {
	        ArrayList locomotorlist = new ArrayList();
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Connection con = null;
	        TerritoryDTO territorydto = null;
	        try {
	            con = DBConnection.getConnection();
	            String query = "select P.Person_Code ,L.Surgery,L.Physiotherapy,C.OtherServices,C.Individual,C.Family from tblPerson_LocomotorNeeds_Details L with(nolock) ,tblPerson_Personal_Details P  with (nolock) ,tblAllDisabilityPerson_Common_Needs C with(nolock)  where P.Person_Code = L.Person_Code and P.Person_Code=C.Person_Code and "
	                    + "P.Person_Code=? and p.status='Active' "
	                    + "and L.status='Active' "
	                    + "and c.status='Active'";



	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, personcode);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                territorydto = new TerritoryDTO();
	                territorydto.setPersoncode(rs.getString(1));
	                territorydto.setSurgery(rs.getString(2));

	                territorydto.setPhysiotherapy(rs.getString(3));

	                territorydto.setAnyother(rs.getString(4));


	                if ((rs.getString(5) != null) || (rs.getString(6) != null)) {

	                    territorydto.setCouncelling("Counseling & Guidance");
	                    if (territorydto.getSurgery() == "" || territorydto.getPhysiotherapy() == null || territorydto.getAnyother() == "" || territorydto.getCouncelling() == null) {

	                        territorydto.setRejected("show");
	                    }
	                }

	                locomotorlist.add(territorydto);
	            }
	        } catch (SQLException sqlEx) {
	        	log.error(sqlEx);
	            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getLocomotorData", "ReportDAO", "DataBase");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getLocomotorData");
	        } catch (Exception sqlEx) {
	        	log.error(sqlEx);
	            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getLocomotorData", "ReportDAO", "Code");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getLocomotorData");
	        } finally {
	            DBConnection.closeConnection(con);
	            DBConnection.closeResultSet(rs);
	            DBConnection.closeStatement(pstmt);

	        }
	        return locomotorlist;
	    }
	    /*
	     * This method will get Visual Pwd data
	     *  for Certificate from database
	     **/

	    /**
	     * 
	     * @param ds 
	     * @param personcode 
	     * @throws org.bf.disability.Exception.SADAREMException 
	     * @return list
	     */
	    public ArrayList getVisualData(String personcode)throws SADAREMDBException, SQLException 
	    {
	        ArrayList visuallist = new ArrayList();
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Connection con = null;
	        TerritoryDTO territorydto = null;
	        try {
	            con = DBConnection.getConnection();
	            String query = "select P.Person_Code ,V.Surgery,V.LowVisionAids,C.OtherServices,C.Individual,C.Family from tblAllDisabilityPerson_Common_Needs C with(nolock) ,tblPerson_Personal_Details P  with (nolock) ,tblVisualImpairment_Details V with(nolock) "
	                    + "where P.Person_Code = C.Person_Code and P.Person_Code=V.Person_Code and P.Person_Code=? and p.status='Active' "
	                    + "and v.status='Active' "
	                    + "and c.status='Active'";

	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, personcode);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                territorydto = new TerritoryDTO();
	                territorydto.setPersoncode(rs.getString(1));
	                territorydto.setSurgery(rs.getString(2));
	                territorydto.setPhysiotherapy(rs.getString(3));
	                territorydto.setAnyother(rs.getString(4));

	                if ((rs.getString(5) != null) || (rs.getString(6) != null)) {
	                    territorydto.setCouncelling("Counseling & Guidance");
	                    if (territorydto.getSurgery() == "" || territorydto.getPhysiotherapy() == null || territorydto.getAnyother() == "" || territorydto.getCouncelling() == null) {

	                        territorydto.setRejected("show");
	                    }
	                }
	                visuallist.add(territorydto);
	            }
	        } catch (SQLException sqlEx) {
	        	log.error(sqlEx);
	            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVisualData", "ReportDAO", "DataBase");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getVisualData");
	        } catch (Exception sqlEx) {
	        	log.error(sqlEx);
	            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVisualData", "ReportDAO", "Code");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getVisualData");
	        } finally {
	            DBConnection.closeConnection(con);
	            DBConnection.closeResultSet(rs);
	            DBConnection.closeStatement(pstmt);

	        }
	        return visuallist;
	    }
	    /*
	     * This method will get Hearing Pwd data
	     *  for Certificate from database
	     **/

	    /**
	     * 
	     * @param ds 
	     * @param personcode 
	     * @throws org.bf.disability.Exception.SADAREMException 
	     * @return list
	     */
	    public ArrayList getHearingData(String personcode)throws SADAREMDBException, SQLException 
	    {
	        ArrayList hearinglist = new ArrayList();
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Connection con = null;
	        TerritoryDTO territorydto = null;
	        try {
	            con = DBConnection.getConnection();
	            String query = "select P.Person_Code ,H.Surgerey,H.HearingAidType,C.OtherServices,C.Individual,C.Family from tblAllDisabilityPerson_Common_Needs C,tblPerson_Personal_Details P  with (nolock) , dbo.tblHearing_Impairment_Details H with(nolock)  where P.Person_Code = C.Person_Code and P.Person_Code=H.Person_Code "
	                    + "and P.Person_Code=? and p.status='Active' "
	                    + "and c.status='Active' "
	                    + "and h.status='Active'";

	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, personcode);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                territorydto = new TerritoryDTO();
	                territorydto.setPersoncode(rs.getString(1));
	                territorydto.setSurgery(rs.getString(2));
	                territorydto.setPhysiotherapy(rs.getString(3));


	                territorydto.setAnyother(rs.getString(4));


	                if ((rs.getString(5) != null) || (rs.getString(6) != null)) {
	                    territorydto.setCouncelling("Counseling & Guidance");
	                    if (territorydto.getSurgery() == "" || territorydto.getPhysiotherapy() == null || territorydto.getAnyother() == "" || territorydto.getCouncelling() == null) {

	                        territorydto.setRejected("show");
	                    }
	                }
	                hearinglist.add(territorydto);
	            }
	        } catch (SQLException sqlEx) {
	        	log.error(sqlEx);
	           // int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHearingData", "ReportDAO", "DataBase");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getHearingData");
	        } catch (Exception sqlEx) {
	        	log.error(sqlEx);
	            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHearingData", "ReportDAO", "Code");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getHearingData");
	        } finally {
	            DBConnection.closeConnection(con);
	            DBConnection.closeResultSet(rs);
	            DBConnection.closeStatement(pstmt);

	        }
	        return hearinglist;
	    }
	    /*
	     * This method will get Mentalillness Pwd data
	     *  for Certificate from database
	     **/

	    /**
	     * 
	     * @param ds 
	     * @param personcode 
	     * @throws org.bf.disability.Exception.SADAREMException 
	     * @return list
	     */
	    public ArrayList getMetalillnessData(String personcode)throws SADAREMDBException, SQLException 
	    {
	        ArrayList illinesslist = new ArrayList();
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Connection con = null;
	        TerritoryDTO territorydto = null;
	        try {
	            con = DBConnection.getConnection();
	            String query = "select P.Person_Code ,I.Surgery,I.NursingHomes,C.OtherServices,C.Individual,"
	                    + "C.Family from tblAllDisabilityPerson_Common_Needs C with(nolock) ,tblPerson_Personal_Details P  with (nolock) ,"
	                    + "dbo.tblMental_Disability_Illness_Details I with(nolock)  where P.Person_Code = C.Person_Code and"
	                    + " P.Person_Code=I.Person_Code and P.Person_Code=? and p.status='Active' "
	                    + "and c.status='Active' "
	                    + "and I.status='Active'";

	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, personcode);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                territorydto = new TerritoryDTO();
	                territorydto.setPersoncode(rs.getString(1));
	                territorydto.setSurgery(rs.getString(2));
	                territorydto.setPhysiotherapy(rs.getString(3));
	                territorydto.setAnyother(rs.getString(4));

	                if ((rs.getString(5) != null) || (rs.getString(6) != null)) {

	                    territorydto.setCouncelling("Counseling & Guidance");
	                    if (territorydto.getSurgery() == "" || territorydto.getPhysiotherapy() == null || territorydto.getAnyother() == "" || territorydto.getCouncelling() == null) {

	                        territorydto.setRejected("show");
	                    }
	                }
	                illinesslist.add(territorydto);
	            }
	        } catch (SQLException sqlEx) {
	        	log.error(sqlEx);
	           // int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMetalillnessData", "ReportDAO", "DataBase");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMetalillnessData");
	        } catch (Exception sqlEx) {
	        	log.error(sqlEx);
	           // int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMetalillnessData", "ReportDAO", "Code");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMetalillnessData");
	        } finally {
	            DBConnection.closeConnection(con);
	            DBConnection.closeResultSet(rs);
	            DBConnection.closeStatement(pstmt);

	        }
	        return illinesslist;
	    }
	    /*
	     * This method will get MentalRetardation Pwd data
	     *  for Certificate from database
	     **/

	    /**
	     * 
	     * @param ds 
	     * @param personcode 
	     * @throws org.bf.disability.Exception.SADAREMException 
	     * @return list
	     */
	    public ArrayList getMentalRetardationData(String personcode)throws SADAREMDBException, SQLException 
	    {
	        ArrayList retardationlist = new ArrayList();
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Connection con = null;
	        TerritoryDTO territorydto = null;
	        try {
	            con = DBConnection.getConnection();
	            String query = "select P.Person_Code ,R.PsycotherapyBehaviour,C.OtherServices,C.Individual,C.Family from tblAllDisabilityPerson_Common_Needs C with(nolock) ,tblPerson_Personal_Details P  with (nolock) ,tblMental_Retardation_Details R with(nolock) where P.Person_Code = C.Person_Code and P.Person_Code=R.Person_Code and  "
	                    + "P.Person_Code=? and p.status='Active' "
	                    + "and c.status='Active' "
	                    + "and R.status='Active'";

	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, personcode);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                territorydto = new TerritoryDTO();
	                territorydto.setPersoncode(rs.getString(1));
	                // territorydto.setSurgery(rs.getString(2));// column deleted
	                territorydto.setPhysiotherapy(rs.getString(2));


	                territorydto.setAnyother(rs.getString(3));
	                if ((rs.getString(4) != null) || (rs.getString(5) != null)) {
	                    territorydto.setCouncelling("Counseling & Guidance");
	                    if (territorydto.getSurgery() == "" || territorydto.getPhysiotherapy() == null || territorydto.getAnyother() == "" || territorydto.getCouncelling() == null) {

	                        territorydto.setRejected("show");
	                    }
	                }
	                retardationlist.add(territorydto);
	            }
	        } catch (SQLException sqlEx) {
	        	log.error(sqlEx);
	           // int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMentalRetardationData", "ReportDAO", "DataBase");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMentalRetardationData");
	        } catch (Exception sqlEx) {
	        	log.error(sqlEx);
	        	//int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMentalRetardationData", "ReportDAO", "Code");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMentalRetardationData");
	        } finally {
	            DBConnection.closeConnection(con);
	            DBConnection.closeResultSet(rs);
	            DBConnection.closeStatement(pstmt);

	        }
	        return retardationlist;
	    }
	    /*
	     * This method will get locomotor Pwd data
	     *  for RejectedCertificate from database
	     **/

	    /**
	     * 
	     * @param ds 
	     * @param personcode 
	     * @throws org.bf.disability.Exception.SADAREMException 
	     * @return list
	     */
	    public ArrayList getRejectedLocomotorData(String personcode)throws SADAREMDBException, SQLException 
	    {


	        ArrayList locomotorlist = new ArrayList();
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Connection con = null;
	        TerritoryDTO territorydto = null;
	        try {
	            con = DBConnection.getConnection();
	            String query = "select Person_Code,Reffered_To,Surgery,Counciling_Guidence,Physiotherapy,AnyOther from tblRejectPerson_Details with(nolock)  where Person_Code =? and status='Active'";
	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, personcode);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                territorydto = new TerritoryDTO();
	                territorydto.setPersoncode(rs.getString("Person_Code"));
	                territorydto.setReferredto(rs.getString("Reffered_To"));
	                territorydto.setSurgeryforrejected(rs.getString("Surgery"));
	                territorydto.setCouncellingandguidance(rs.getString("Counciling_Guidence"));
	                territorydto.setLowvisionaid(rs.getString("Physiotherapy"));

	                territorydto.setAnyotherneed(rs.getString("AnyOther"));
	                if (territorydto.getReferredto() == "" || territorydto.getSurgeryforrejected() == "" || territorydto.getCouncellingandguidance() == null || territorydto.getLowvisionaid() == null || territorydto.getAnyotherneed() == "") {
	                    territorydto.setRejected("show");
	                }
	                locomotorlist.add(territorydto);
	            }
	        } catch (SQLException sqlEx) {
	        	log.error(sqlEx);
	            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedLocomotorData", "ReportDAO", "DataBase");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedLocomotorData");
	        } catch (Exception sqlEx) {
	        	log.error(sqlEx);
	            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedLocomotorData", "ReportDAO", "Code");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedLocomotorData");
	        } finally {
	            DBConnection.closeConnection(con);
	            DBConnection.closeResultSet(rs);
	            DBConnection.closeStatement(pstmt);

	        }
	        return locomotorlist;
	    }
	    /*
	     * This method will get Visual Pwd data
	     *  for RejectedCertificate from database
	     **/

	    /**
	     * 
	     * @param ds 
	     * @param personcode 
	     * @throws org.bf.disability.Exception.SADAREMException 
	     * @return list
	     */
	    public ArrayList getRejectedVisualData(String personcode)throws SADAREMDBException, SQLException 
	    {
	        ArrayList visuallist = new ArrayList();
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Connection con = null;
	        TerritoryDTO territorydto = null;
	        try {
	            con = DBConnection.getConnection();
	            String query = "select Person_Code,Reffered_To,Surgery,Counciling_Guidence,LowVision,AnyOther from tblRejectPerson_Details with(nolock)  where Person_Code =? and status='Active'";
	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, personcode);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                territorydto = new TerritoryDTO();
	                territorydto.setPersoncode(rs.getString("Person_Code"));
	                territorydto.setReferredto(rs.getString("Reffered_To"));
	                territorydto.setSurgeryforrejected(rs.getString("Surgery"));
	                territorydto.setCouncellingandguidance(rs.getString("Counciling_Guidence"));
	                territorydto.setLowvisionaid(rs.getString("LowVision"));
	                territorydto.setAnyotherneed(rs.getString("AnyOther"));
	                if (territorydto.getReferredto() == "" || territorydto.getSurgeryforrejected() == "" || territorydto.getCouncellingandguidance() == null || territorydto.getLowvisionaid() == null || territorydto.getAnyotherneed() == "") {
	                    territorydto.setRejected("show");
	                }
	                visuallist.add(territorydto);
	            }
	        } catch (SQLException sqlEx) 
	        {
	        	log.error(sqlEx);
	           // int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedVisualData", "ReportDAO", "DataBase");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedVisualData");
	        } catch (Exception sqlEx) {
	        	log.error(sqlEx);
	        	//int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedVisualData", "ReportDAO", "Code");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedVisualData");
	        } finally {
	            DBConnection.closeConnection(con);
	            DBConnection.closeResultSet(rs);
	            DBConnection.closeStatement(pstmt);

	        }
	        return visuallist;
	    }
	    /*
	     * This method will get Hearing Pwd data
	     *  for RejectedCertificate from database
	     **/

	    /**
	     * 
	     * @param ds 
	     * @param personcode 
	     * @throws org.bf.disability.Exception.SADAREMException 
	     * @return list
	     */
	    public ArrayList getRejectedHearingData(String personcode)throws SADAREMDBException, SQLException 
	    {
	        ArrayList hearinglist = new ArrayList();
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Connection con = null;
	        TerritoryDTO territorydto = null;
	        try {
	            con = DBConnection.getConnection();
	            String query = "select Person_Code,Reffered_To,Surgery,Counciling_Guidence,Hearing_Aid,AnyOther from tblRejectPerson_Details with(nolock)  where Person_Code =? and status='Active'";
	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, personcode);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                territorydto = new TerritoryDTO();
	                territorydto.setPersoncode(rs.getString("Person_Code"));
	                territorydto.setReferredto(rs.getString("Reffered_To"));
	                territorydto.setSurgeryforrejected(rs.getString("Surgery"));
	                territorydto.setCouncellingandguidance(rs.getString("Counciling_Guidence"));
	                territorydto.setLowvisionaid(rs.getString("Hearing_Aid"));
	                territorydto.setAnyotherneed(rs.getString("AnyOther"));
	                if (territorydto.getReferredto() == "" || territorydto.getSurgeryforrejected() == "" || territorydto.getCouncellingandguidance() == null || territorydto.getLowvisionaid() == null || territorydto.getAnyotherneed() == "") {
	                    territorydto.setRejected("show");
	                }
	                hearinglist.add(territorydto);
	            }
	        }
	        catch (SQLException sqlEx) 
	        {
	        	log.error(sqlEx);
	        	// int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedHearingData", "ReportDAO", "DataBase");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedHearingData");
	        }
	        catch (Exception sqlEx)
	        {
	        	log.error(sqlEx);
	            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedHearingData", "ReportDAO", "Code");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedHearingData");
	        } finally {
	            DBConnection.closeConnection(con);
	            DBConnection.closeResultSet(rs);
	            DBConnection.closeStatement(pstmt);

	        }
	        return hearinglist;
	    }
	    /*
	     * This method will get MentalIllness Pwd data
	     *  for RejectedCertificate from database
	     **/

	    /**
	     * 
	     * @param ds 
	     * @param personcode 
	     * @throws org.bf.disability.Exception.SADAREMException 
	     * @return list
	     */
	    public ArrayList getRejectedMetalillnessData(String personcode)throws SADAREMDBException, SQLException 
	    {
	        ArrayList illinesslist = new ArrayList();
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Connection con = null;
	        TerritoryDTO territorydto = null;
	        try {
	            con = DBConnection.getConnection();
	            String query = "select Person_Code,Reffered_To,Phychotherapy,Counciling_Guidence,Addmission,AnyOther from tblRejectPerson_Details with(nolock)  where Person_Code =? and status='Active'";
	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, personcode);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                territorydto = new TerritoryDTO();
	                territorydto.setPersoncode(rs.getString("Person_Code"));
	                territorydto.setReferredto(rs.getString("Reffered_To"));
	                territorydto.setSurgeryforrejected(rs.getString("Phychotherapy"));
	                territorydto.setCouncellingandguidance(rs.getString("Counciling_Guidence"));
	                territorydto.setLowvisionaid(rs.getString("Addmission"));
	                territorydto.setAnyotherneed(rs.getString("AnyOther"));
	                if (territorydto.getReferredto() == "" || territorydto.getSurgeryforrejected() == "" || territorydto.getCouncellingandguidance() == null || territorydto.getLowvisionaid() == null || territorydto.getAnyotherneed() == "") {
	                    territorydto.setRejected("show");
	                }
	                illinesslist.add(territorydto);
	            }
	        } catch (SQLException sqlEx) {
	        	log.error(sqlEx);
	           // int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedMetalillnessData", "ReportDAO", "DataBase");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedMetalillnessData");
	        } catch (Exception sqlEx) {
	        	log.error(sqlEx);
	            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedMetalillnessData", "ReportDAO", "Code");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedMetalillnessData");
	        } finally {
	            DBConnection.closeConnection(con);
	            DBConnection.closeResultSet(rs);
	            DBConnection.closeStatement(pstmt);

	        }
	        return illinesslist;
	    }
	    /*
	     * This method will get MentalRetardation Pwd data
	     *  for RejectedCertificate from database
	     **/

	    /**
	     * 
	     * @param ds 
	     * @param personcode 
	     * @throws org.bf.disability.Exception.SADAREMException 
	     * @return list
	     */
	    public ArrayList getRejectedMentalRetardationData(String personcode)throws SADAREMDBException, SQLException 
	    {
	        ArrayList retardationlist = new ArrayList();
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Connection con = null;
	        TerritoryDTO territorydto = null;
	        try {
	            con = DBConnection.getConnection();
	            String query = "select Person_Code,Reffered_To,Surgery,Counciling_Guidence,Behaviour,AnyOther from tblRejectPerson_Details with(nolock)  where Person_Code =? and status='Active'";
	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, personcode);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                territorydto = new TerritoryDTO();
	                territorydto.setPersoncode(rs.getString("Person_Code"));
	                territorydto.setReferredto(rs.getString("Reffered_To"));
	                territorydto.setSurgeryforrejected(rs.getString("Surgery"));
	                territorydto.setCouncellingandguidance(rs.getString("Counciling_Guidence"));
	                territorydto.setLowvisionaid(rs.getString("Behaviour"));
	                territorydto.setAnyotherneed(rs.getString("AnyOther"));
	                if (territorydto.getReferredto() == "" || territorydto.getSurgeryforrejected() == "" || territorydto.getCouncellingandguidance() == null || territorydto.getLowvisionaid() == null || territorydto.getAnyotherneed() == "") {
	                    territorydto.setRejected("show");
	                }
	                retardationlist.add(territorydto);
	            }
	        } catch (SQLException sqlEx) {
	        	log.error(sqlEx);
	            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedMentalRetardationData", "ReportDAO", "DataBase");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedMentalRetardationData");
	        } catch (Exception sqlEx) {
	        	log.error(sqlEx);
	        	//int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedMentalRetardationData", "ReportDAO", "Code");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedMentalRetardationData");
	        } finally {
	            DBConnection.closeConnection(con);
	            DBConnection.closeResultSet(rs);
	            DBConnection.closeStatement(pstmt);

	        }
	        return retardationlist;
	    }
	    /*
	     * This method will get Locomotorsubdetail
	     *  for Certificate from database
	     **/

	    /**
	     * 
	     * @param ds 
	     * @param personcode 
	     * @throws org.bf.disability.Exception.SADAREMException 
	     * @return list
	     */
	    public ArrayList getLocomotorSubDetails(String personcode)throws SADAREMDBException, SQLException
	    {
	        ArrayList locomotorsubdetails = new ArrayList();
	        ArrayList subdetails = new ArrayList();
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Connection con = null;
	        String[] str = new String[20];
	        String[] str1 = new String[30];

	        CertificateDTO certificatedto = null;
	        try {
	            con = DBConnection.getConnection();

	            String query = "select Person_Code,Sub_Sub_Disability_ID,Sub_Disability_ID from tblPerson_Disability_Details with(nolock)  where Person_Code =?";
	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, personcode);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                certificatedto = new CertificateDTO();
	                certificatedto.setPersoncode(rs.getString(1));
	                if (rs.getString(2) != null) {
	                    str = rs.getString(2).split(",");



	                    for (int i = 0; i < str.length; i++) {
	                        if (str[i].equals("1")) {
	                            certificatedto.setPartInvolved1("-Right Upper Limb");
	                        } else if (str[i].equals("2")) {
	                            certificatedto.setPartInvolved2("-Left Upper Limb");
	                        } else if (str[i].equals("3")) {
	                            certificatedto.setPartInvolved3("-Bilateral Upper Limb");
	                        } else if (str[i].equals("4")) {
	                            certificatedto.setPartInvolved4("-Right Lower Limb");
	                        } else if (str[i].equals("5")) {
	                            certificatedto.setPartInvolved5("-Left Lower Limb");
	                        } else if (str[i].equals("6")) {
	                            certificatedto.setPartInvolved6("-Bilateral Lower Limb");//Changed as Upper to Lower
	                        } else if (str[i].equals("7")) {
	                            certificatedto.setPartInvolved7("-Spine");
	                        } else if (str[i].equals("8")) {
	                            certificatedto.setPartInvolved8("-Trunk");
	                        } else if (str[i].equals("9")) {
	                            certificatedto.setPartInvolved9("-All 4 Limbs");
	                        } else if (str[i].equals("10")) {
	                            certificatedto.setPartInvolved10("-Whole Body");
	                        } else if (str[i].equals("11")) {
	                            certificatedto.setPartInvolved11("-Hips");//Added
	                        }
	                    }
	                }
	                if (rs.getString(3) != null) {
	                    str1 = rs.getString(3).split(",");

	                    for (int j = 0; j < str1.length; j++) {
	                        if (str1[j].equals("1")) {
	                            certificatedto.setOhsubtypes1("- Cerebral Palsy (CP) ");
	                        } else if (str1[j].equals("2")) {
	                            certificatedto.setOhsubtypes2("- Post Polio Residual Paralysis (PPRP)");
	                        } else if (str1[j].equals("3")) {
	                            certificatedto.setOhsubtypes3("- Post Hansen's Disease Sequel");
	                        } else if (str1[j].equals("4")) {
	                            certificatedto.setOhsubtypes4("-  Muscular Dystrophy");
	                        } else if (str1[j].equals("5")) {
	                            certificatedto.setOhsubtypes5("-  Post Traumatic Amputation");
	                        } else if (str1[j].equals("6")) {
	                            certificatedto.setOhsubtypes6("-  Post Traumatic Sequel - Limbs");
	                        } else if (str1[j].equals("7")) {
	                            certificatedto.setOhsubtypes7("- Post Traumatic Sequel - Spine");
	                        } else if (str1[j].equals("8")) {
	                            certificatedto.setOhsubtypes8("-  Post Head Injury Sequel");
	                        } else if (str1[j].equals("9")) {
	                            certificatedto.setOhsubtypes9("- Post Burn Injury Sequel");
	                        } else if (str1[j].equals("10")) {
	                            certificatedto.setOhsubtypes10("- Cerebro-Vascular Accidents  ");
	                        } else if (str1[j].equals("11")) {
	                            certificatedto.setOhsubtypes11("- Dwarfism");
	                        } else if (str1[j].equals("12")) {
	                            certificatedto.setOhsubtypes12("- Congenital Deformities of Limbs");
	                        } else if (str1[j].equals("13")) {
	                            certificatedto.setOhsubtypes13("- Congenital Deformities of Spine ");
	                        } else if (str1[j].equals("14")) {
	                            certificatedto.setOhsubtypes14("- Cardio Pulmonary Disease");
	                        } else if (str1[j].equals("15")) {
	                            certificatedto.setOhsubtypes15("- Others");

	                        }
	                    }




	                }
	                locomotorsubdetails.add(certificatedto);

	            }
	        } catch (SQLException sqlEx) 
	        {
	        	log.error(sqlEx);
	           // int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getLocomotorSubDetails", "ReportDAO", "DataBase");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getLocomotorSubDetails");
	        }
	        catch (Exception sqlEx)
	        {
	        	log.error(sqlEx);
	            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getLocomotorSubDetails", "ReportDAO", "Code");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getLocomotorSubDetails");
	        } 
	        finally 
	        {
	            DBConnection.closeConnection(con);
	            DBConnection.closeResultSet(rs);
	            DBConnection.closeStatement(pstmt);

	        }
	        return locomotorsubdetails;
	    }
	    /*
	     * This method will get Visualsubdetail
	     *  for Certificate from database
	     **/

	    /**
	     * 
	     * @param ds 
	     * @param personcode 
	     * @throws org.bf.disability.Exception.SADAREMException 
	     * @return list
	     */
	    public ArrayList getVisualSubDetails(String personcode)throws SADAREMDBException, SQLException 
	    {
	        ArrayList visualsubdetails = new ArrayList();
	        ArrayList subdetails = new ArrayList();
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Connection con = null;
	        String[] str = new String[20];
	        CertificateDTO certificatedto = null;
	        try {
	            con = DBConnection.getConnection();

	            String query = "select Person_Code,Sub_Disability_ID from tblPerson_Disability_Details with(nolock)  where Person_Code =?";
	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, personcode);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                certificatedto = new CertificateDTO();
	                certificatedto.setPersoncode(rs.getString(1));
	                if (rs.getString(2) != null) {
	                    str = rs.getString(2).split(",");



	                    for (int i = 0; i < str.length; i++) {
	                        if (str[i].equals("62")) {
	                            certificatedto.setPartInvolved1("-Complete Loss Of Vision-Better Eye");
	                        } else if (str[i].equals("63")) {
	                            certificatedto.setPartInvolved2("-Complete Loss Of Vision-Worse Eye");
	                        } else if (str[i].equals("93")) {
	                            certificatedto.setPartInvolved3("-Low Vision-Better Eye");
	                        } else if (str[i].equals("94")) {
	                            certificatedto.setPartInvolved4("-Low Vision-Worse Eye");
	                        }
	                    }
	                }
	                visualsubdetails.add(certificatedto);
	            }
	        } catch (SQLException sqlEx) 
	        {
	        	log.error(sqlEx);
	            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVisualSubDetails", "ReportDAO", "DataBase");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getVisualSubDetails");
	        }
	        catch (Exception sqlEx) 
	        {
	        	log.error(sqlEx);
	           // int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVisualSubDetails", "ReportDAO", "Code");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getVisualSubDetails");
	        } 
	        finally 
	        {
	            DBConnection.closeConnection(con);
	            DBConnection.closeResultSet(rs);
	            DBConnection.closeStatement(pstmt);

	        }
	        return visualsubdetails;
	    }
	    /*
	     * This method will get Hearingsubdetail
	     *  for Certificate from database
	     **/

	    /**
	     * 
	     * @param ds 
	     * @param personcode 
	     * @throws org.bf.disability.Exception.SADAREMException 
	     * @return list
	     */
	    public ArrayList getHearingSubDetails(String personcode)throws SADAREMDBException, SQLException 
	    {
	        ArrayList hearingsubdetails = new ArrayList();

	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Connection con = null;
	        CertificateDTO certificatedto = null;
	        try {
	            con = DBConnection.getConnection();

	            String query = "select Person_Code,Sub_Disability_ID from tblPerson_Disability_Details with(nolock)  where Person_Code =?";
	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, personcode);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                certificatedto = new CertificateDTO();
	                certificatedto.setPersoncode(rs.getString(1));
	                if (rs.getString(2) != null) {
	                    if (rs.getString(2).equals("64")) {
	                        certificatedto.setPartInvolved1("-Mild");
	                    } else if (rs.getString(2).equals("65")) {
	                        certificatedto.setPartInvolved2("-Moderate");
	                    } else if (rs.getString(2).equals("66")) {
	                        certificatedto.setPartInvolved3("-Severe");
	                    } else if (rs.getString(2).equals("67")) {
	                        certificatedto.setPartInvolved4("-Profound");

	                    }
	                }
	                hearingsubdetails.add(certificatedto);
	            }
	        }
	        catch (SQLException sqlEx) 
	        {
	        	log.error(sqlEx);
	            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHearingSubDetails", "ReportDAO", "DataBase");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getHearingSubDetails");
	        } 
	        catch (Exception sqlEx)
	        {
	        	log.error(sqlEx);
	            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHearingSubDetails", "ReportDAO", "Code");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getHearingSubDetails");
	        } 
	        finally 
	        {
	            DBConnection.closeConnection(con);
	            DBConnection.closeResultSet(rs);
	            DBConnection.closeStatement(pstmt);

	        }
	        return hearingsubdetails;
	    }
	    /*
	     * This method will get MentalRetardationsubdetail
	     *  for Certificate from database
	     **/

	    /**
	     * 
	     * @param ds 
	     * @param personcode 
	     * @throws org.bf.disability.Exception.SADAREMException 
	     * @return list
	     */
	    public ArrayList getMRSubDetails(String personcode)throws SADAREMDBException, SQLException 
	    {
	        ArrayList retardationlist = new ArrayList();

	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Connection con = null;
	        CertificateDTO certificatedto = null;
	        try {
	            con = DBConnection.getConnection();

	            String query = "select Person_Code,Sub_Disability_ID from tblPerson_Disability_Details with(nolock)  where Person_Code =?";
	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, personcode);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                certificatedto = new CertificateDTO();
	                certificatedto.setPersoncode(rs.getString(1));
	                if (rs.getString(2) != null) {
	                    if (rs.getString(2).equals("69")) {
	                        certificatedto.setPartInvolved1("-Mild");
	                    } else if (rs.getString(2).equals("70")) {
	                        certificatedto.setPartInvolved2("-Moderate");
	                    } else if (rs.getString(2).equals("71")) {
	                        certificatedto.setPartInvolved3("-Severe");
	                    } else if (rs.getString(2).equals("72")) {
	                        certificatedto.setPartInvolved4("-Profound");

	                    }
	                }
	                retardationlist.add(certificatedto);
	            }
	        } catch (SQLException sqlEx) 
	        {
	        	log.error(sqlEx);
	            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMRSubDetails", "ReportDAO", "DataBase");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMRSubDetails");
	        } 
	        catch (Exception sqlEx) 
	        {
	        	log.error(sqlEx);
	           // int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMRSubDetails", "ReportDAO", "Code");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMRSubDetails");
	        } finally {
	            DBConnection.closeConnection(con);
	            DBConnection.closeResultSet(rs);
	            DBConnection.closeStatement(pstmt);

	        }
	        return retardationlist;
	    }
	    /*
	     * This method will get MentalIllnesssubdetail
	     *  for Certificate from database
	     **/

	    /**
	     * 
	     * @param ds 
	     * @param personcode 
	     * @throws org.bf.disability.Exception.SADAREMException 
	     * @return list
	     */
	    public ArrayList getMISubDetails(  String personcode) throws SADAREMDBException, SQLException
	    {
	        ArrayList illinesslist = new ArrayList();

	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Connection con = null;
	        CertificateDTO certificatedto = null;
	        try {
	            con = DBConnection.getConnection();

	            String query = "select Person_Code,Sub_Disability_ID from tblPerson_Disability_Details with(nolock)  where Person_Code =?";
	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, personcode);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                certificatedto = new CertificateDTO();
	                certificatedto.setPersoncode(rs.getString(1));
	                if (rs.getString(2) != null) {
	                    if (rs.getString(2).equals("74")) {
	                        certificatedto.setPartInvolved1("-Mild");
	                    } else if (rs.getString(2).equals("75")) {
	                        certificatedto.setPartInvolved2("-Moderate");
	                    } else if (rs.getString(2).equals("76")) {
	                        certificatedto.setPartInvolved3("-Severe");
	                    } else if (rs.getString(2).equals("77")) {
	                        certificatedto.setPartInvolved4("-Profound");

	                    }
	                }
	                illinesslist.add(certificatedto);
	            }
	        }
	        catch (SQLException sqlEx) 
	        {
	        	log.error(sqlEx);
	            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMISubDetails", "ReportDAO", "DataBase");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMISubDetails");
	        } 
	        catch (Exception sqlEx) 
	        {
	        	log.error(sqlEx);
	            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMISubDetails", "ReportDAO", "Code");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMISubDetails");
	        } 
	        finally 
	        {
	            DBConnection.closeConnection(con);
	            DBConnection.closeResultSet(rs);
	            DBConnection.closeStatement(pstmt);

	        }
	        return illinesslist;
	    }
	    /*
	     * This method will get Multiplesubdetail
	     *  for Certificate from database
	     **/

	    /**
	     * 
	     * @param ds 
	     * @param personcode 
	     * @throws org.bf.disability.Exception.SADAREMException 
	     * @return list
	     */
	    public ArrayList getMultipleSubDetails(String personcode)throws SADAREMDBException, SQLException 
	    {
	        ArrayList locomotorsubdetails = new ArrayList();
	        ArrayList subdetails = new ArrayList();
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Connection con = null;
	        String[] str = new String[20];
	        String[] str1 = new String[20];
	        CertificateDTO certificatedto = null;
	        try {
	            con = DBConnection.getConnection();

	            String query = "select Person_Code,Sub_Disability_ID,Sub_Sub_Disability_ID from tblPerson_Disability_Details with(nolock)  where Person_Code =?";
	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, personcode);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                certificatedto = new CertificateDTO();
	                certificatedto.setPersoncode(rs.getString(1));
	                if (rs.getString(3) != null) {
	                    str = rs.getString(3).split(",");



	                    for (int i = 0; i < str.length; i++) {
	                        if (str[i].equals("1")) {
	                            certificatedto.setPartInvolved1("-Right Upper Limb");
	                        } else if (str[i].equals("2")) {
	                            certificatedto.setPartInvolved2("-Left Upper Limb");
	                        } else if (str[i].equals("3")) {
	                            certificatedto.setPartInvolved3("-Bilateral Upper Limb");
	                        } else if (str[i].equals("4")) {
	                            certificatedto.setPartInvolved4("-Right Lower Limb");
	                        } else if (str[i].equals("5")) {
	                            certificatedto.setPartInvolved5("-Left Lower Limb");
	                        } else if (str[i].equals("6")) {
	                            certificatedto.setPartInvolved6("-Bilateral Lower Limb");//Changed as Upper to Lower
	                        } else if (str[i].equals("7")) {
	                            certificatedto.setPartInvolved7("-Spine");
	                        } else if (str[i].equals("8")) {
	                            certificatedto.setPartInvolved8("-Trunk");
	                        } else if (str[i].equals("9")) {
	                            certificatedto.setPartInvolved9("-All 4 Limbs");
	                        } else if (str[i].equals("10")) {
	                            certificatedto.setPartInvolved10("-Whole Body");
	                        } else if (str[i].equals("11")) {
	                            certificatedto.setPartInvolved11("-Hips");//added
	                        }
	                    }
	                }
	                if (rs.getString(2) != null) {
	                    str1 = rs.getString(2).split(",");
	                    for (int j = 0; j < str1.length; j++) {
	                        if (str1[j].equals("62")) {
	                            certificatedto.setPartInvolved11("-Complete Loss Of Vision-Better Eye");
	                        } else if (str1[j].equals("63")) {
	                            certificatedto.setPartInvolved12("-Complete Loss Of Vision-Worse Eye");
	                        } else if (str1[j].equals("93")) {
	                            certificatedto.setPartInvolved13("-Low Vision-Better Eye");
	                        } else if (str1[j].equals("94")) {
	                            certificatedto.setPartInvolved14("-Low Vision-Worse Eye");
	                        } else if (str1[j].equals("64")) {
	                            certificatedto.setPartInvolved15("-Mild");
	                        } else if (str1[j].equals("65")) {
	                            certificatedto.setPartInvolved15("-Moderate");
	                        } else if (str1[j].equals("65")) {
	                            certificatedto.setPartInvolved15("-Severe");
	                        } else if (str1[j].equals("67")) {
	                            certificatedto.setPartInvolved15("-Profound");
	                        } else if (str1[j].equals("69")) {
	                            certificatedto.setPartInvolved16("-Mild");
	                        } else if (str1[j].equals("70")) {
	                            certificatedto.setPartInvolved16("-Moderate");
	                        } else if (str1[j].equals("71")) {
	                            certificatedto.setPartInvolved16("-Severe");
	                        } else if (str1[j].equals("72")) {
	                            certificatedto.setPartInvolved16("-Profound");
	                        } else if (str1[j].equals("74")) {
	                            certificatedto.setPartInvolved17("-Mild");
	                        } else if (str1[j].equals("75")) {
	                            certificatedto.setPartInvolved17("-Moderate ");
	                        } else if (str1[j].equals("76")) {
	                            certificatedto.setPartInvolved17("-Severe");
	                        } else if (str1[j].equals("77")) {
	                            certificatedto.setPartInvolved17("-Profound");
	                        }
	                    }
	                }
	                locomotorsubdetails.add(certificatedto);
	            }
	        }
	        catch (SQLException sqlEx) 
	        {
	        	log.error(sqlEx);
	            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleSubDetails", "ReportDAO", "DataBase");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMultipleSubDetails");
	        } 
	        catch (Exception sqlEx) 
	        {
	        	log.error(sqlEx);
	            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleSubDetails", "ReportDAO", "Code");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMultipleSubDetails");
	        }
	        finally 
	        {
	            DBConnection.closeConnection(con);
	            DBConnection.closeResultSet(rs);
	            DBConnection.closeStatement(pstmt);

	        }
	        return locomotorsubdetails;
	    }
	    /*
	     * This method will get Multiple detail
	     *  for Certificate from database
	     **/

	    /**
	     * 
	     * @param ds 
	     * @param personcode 
	     * @throws org.bf.disability.Exception.SADAREMException 
	     * @return list
	     */
	    public ArrayList getMultipleData(String personcode)throws SADAREMDBException, SQLException 
	    {
	        ArrayList multipllist = new ArrayList();
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Connection con = null;
	        TerritoryDTO territorydto = null;
	        try {
	            con = DBConnection.getConnection();
	            String query = "select Person_Code,OtherServices,Individual,Family from tblAllDisabilityPerson_Common_Needs with(nolock)  "
	                    + "where Person_Code=? and status='Active'";

	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, personcode);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                territorydto = new TerritoryDTO();
	                territorydto.setPersoncode(rs.getString(1));
	                territorydto.setSurgery("");
	                territorydto.setPhysiotherapy("");

	                territorydto.setAnyother(rs.getString(2));
	                if ((rs.getString(3) != null) || (rs.getString(4) != null)) {
	                    territorydto.setCouncelling("Counseling & Guidance");
	                }
	                multipllist.add(territorydto);
	            }
	        }
	        catch (SQLException sqlEx) 
	        {
	            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleData", "ReportDAO", "DataBase");
	        	log.error(sqlEx);
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMultipleData");
	        }
	        catch (Exception sqlEx) 
	        {
	        	log.error(sqlEx);
	            //int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleData", "ReportDAO", "Code");
	            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMultipleData");
	        }
	        finally 
	        {
	            DBConnection.closeConnection(con);
	            DBConnection.closeResultSet(rs);
	            DBConnection.closeStatement(pstmt);

	        }
	        return multipllist;
	    }
	  
	    
	    
	     
	   
}
