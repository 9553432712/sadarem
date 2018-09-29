package com.tcs.sadarem.withoutproofparta.DAO;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.tcs.sadarem.util.DataAccess;

public class PartAWithoutProofWorklistDAOImpl implements PartAWithoutProofWorklistDAO
{
	public ArrayList getPendingWithoutProofPartaList(String distID,String reqStatus,String fromdate, String todate)
	{
		ArrayList resultList = new ArrayList();
		ArrayList paramList = new ArrayList();
		ArrayList tempList	= new ArrayList();
		String lStrQuery="";
		try
		{


            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(fromdate);
            String formdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date ddate = new SimpleDateFormat("dd/mm/yyyy").parse(todate);
            String dobdate = new SimpleDateFormat("mm/dd/yyyy").format(ddate);
			
           String qry="SELECT  s.request_id, s.sadarem_id,s.Reference_Form_Number,s.Surname,s.First_Name,s.Relation_Name,s.Personname_Telugu, \n"
					+ "s.Relationname_Telugu,s.Date_of_Birth,s.Mole_One,s.mole_two,s.created_date,s.Proof_Id,s.Proofdoc_Type, \n"
					+ "case  s.Gender_ID when '1' then 'Male' when '2' then 'Female' end 'Gender', d.District_Name, m.Mandal_Name, \n"
					+ "v.Village_Name, edu.edu_desc, emp.employement_type_desc, s.phone_no, \n"
					+ "camp.Name+' '+camp.Address as 'Camp dtls',marital.marital_status_desc, s.created_by ,rel.relation_name,sadarem_id,s.updated_date,status_remarks\n"
					+ "from sadarem_without_proof_request_master s \n"
					+ "left join  tblDistrict_Details d on d.District_ID=s.District_ID \n"
					+ "left join  tblmandal_Details m on m.Mandal_ID=s.Mandal_ID and m.District_ID=s.District_ID \n"
					+ "left join  tblvillage_Details v on v.District_ID=s.District_ID and v.Mandal_ID=s.Mandal_ID and v.Village_ID=s.Village_ID \n"
					+ "left join  sadarem_education_master edu on edu.edu_id=s.Education_ID "
					+ "left join  sadarem_employement_type_master emp on emp.employement_type_id=s.Employment_ID \n"
					+ "left join  tblcamp_details camp on camp.Camp_ID=s.Camp_ID and camp.District_ID=s.District_ID \n "
					+ "left join  sadarem_marital_status_master marital on marital.marital_status_id=s.Marital_Status_ID \n"
					+ "left join  sadarem_gender_relation_type_mapping rel on s.relationship_id=rel.relation_id and rel.gender_id=s.gender_id \n"
					+ " where req_status=? and s.district_id=? and CONVERT(VARCHAR(12),s.created_date,101) between CONVERT(VARCHAR(12),?,101)  and CONVERT(VARCHAR(12),?,101)" ;
			
			
			tempList.add("S");
    		tempList.add(reqStatus);
    		paramList.add(tempList);
    		
    		
    		tempList	= new ArrayList();
			tempList.add("S");
	    	tempList.add(distID);
	    	paramList.add(tempList);
	    	
	    	tempList	= new ArrayList();
			tempList.add("S");
	    	tempList.add(formdate);
	    	paramList.add(tempList);
	    	
	    	tempList	= new ArrayList();
			tempList.add("S");
	    	tempList.add(dobdate);
	    	paramList.add(tempList);
	    	

			resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(qry, paramList, false, false);
			paramList = null;
		    tempList  = null;		
		}   
		 catch(SQLException sqle)
		 {    
			 System.out.println("Exception in WithoutProofPartADAOImpl @ getPendingWithoutProofPartaList : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 	System.out.println("Exception in WithoutProofPartADAOImpl @ getPendingWithoutProofPartaList : "+lexp.getLocalizedMessage());
		 }

		return resultList;
	}
 
}
