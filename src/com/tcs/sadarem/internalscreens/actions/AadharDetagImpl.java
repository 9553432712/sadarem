package com.tcs.sadarem.internalscreens.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

public class AadharDetagImpl implements AadharDetagDAO
{

	
	
	public ArrayList getSadaremCommonData(String sadaremid)
	{

		ArrayList sadaremData = new ArrayList();
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		String qry="";
		//System.out.println("----------------getSadaremCommonData called-");
		
		 try
		 {         
			 
				qry =  " select sadarem_id,v.districtid,v.mandalid,v.villageid,v.habtationid,district_name,mandal_name,village_name,\n"+
					   "habitation_name,person_surname,person_name,v.personname_telugu,CONVERT(varchar(10),person_dob,103) dob,\n"+
						"ISNULL(type_of_proof,'-') proofdoc_type,ISNULL(v.proof_id,'-') proof_id,person_gender,rel_type,\n"+
						"v.relation_name,relation_telugu_name,ISNULL(person_contactno,'-')contact_no,\n"+
						"category_id,CategoryCount,v.person_status,person_live_status,dis_form_status,\n"+
						"reltype.relation_id,per.house_number,per.pin_code,v.disb_id,v.Disability_Name,\n"+
						"disp_min_per,person_disp_percent,cause_of_disp,temp_cert_expire_year, ISNULL(certificate_issue_date,'-') certificate_issue_date,\n"+
						"cert_expire_date,v.edu_id\n"+
                        "FROM\n"+
						"tblPerson_Personal_Details per WITH(NOLOCK) ,\n"+
						"sadarem_gender_relation_type_mapping reltype  WITH(NOLOCK),\n"+
						"sadarem_view_complete_details v WITH(NOLOCK) \n"+
						"WHERE\n"+
						"ISNULL(per.gender,0)=reltype.gender_id AND\n"+
						"ISNULL(per.Relationship,0)=reltype.relation_id AND\n"+
						"per.Person_Code=v.sadarem_id and v.sadarem_id=?  ";
				

			  
         tempList.add("S");
         tempList.add(sadaremid);
         paramList.add(tempList);	
      //   System.out.println("basicdata444"+qry);   
		sadaremData = DataAccess.pickDataByPrepareStmt(qry, paramList, false, false);
		 
		 }
		 catch(Exception e)
		 {
		
			System.out.println("Exception raised in AadharDetagImpl @ getSadaremCommonData"+e);
			e.printStackTrace();
		 }
		return sadaremData;
	}	
	
	public static String checkNullObj(Object aoCheckObject)
    {
        if (aoCheckObject == null)
        {
            return "";
        }

        return aoCheckObject.toString();
    }
	
	public int RemarksLogInsert(String sadaremID, String aadharId,String remark, String victimeName, String createdBy)
	{
		int result=0;
		Connection connection=null;
		PreparedStatement updateLastName = null;
		try
		{
				 connection = DBConnection.getConnection();
				String changeLastName = "insert into Aadhar_Detag_Log ( sadarem_id , Aadhar_Id , remarks ,person_name ,created_by, created_date ,updated_by ,updated_date)"
					+ " values (?,?,?,?,?,getdate(),null,null)";
				 updateLastName = connection.prepareStatement(changeLastName);

				updateLastName.setString(1, sadaremID); // Set lastname placeholder value
				updateLastName.setString(2, aadharId); // Set author ID placeholder value
				updateLastName.setString(3, remark);
				updateLastName.setString(4, victimeName);
				updateLastName.setString(5, createdBy);
				 result = updateLastName.executeUpdate();
				 DBConnection.closeConnection(connection);
				 updateLastName.close();
		}
		catch (SQLException e)
		{
			DBConnection.closeConnection(connection);
			e.printStackTrace();
		}
		finally
		{
			try
			{
				updateLastName.close();
				DBConnection.closeConnection(connection); 
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public int AadharDetagqry(String sadaremId) 
	{
		int result=0,result1=0,result2=0;
		Connection connection=null;
		PreparedStatement updateLastName = null;
		try
		{
			 String qry="update tblperson_personal_details set Proofdoc_Type=null, Proof_Id=null,updated_date=getdate() where Person_Code=? ";
			 connection = DBConnection.getConnection();
			 connection.setAutoCommit(false);
			 updateLastName = connection.prepareStatement(qry);

			 updateLastName.setString(1, sadaremId);
			 result = updateLastName.executeUpdate();
			 
			   if(result>0)
			   {
					String qry1="update tblperson_personal_details_new set Proofdoc_Type=null, Proof_Id=null,updated_date=getdate() where Person_Code=? ";
					PreparedStatement update = connection.prepareStatement(qry1);
					update.setString(1, sadaremId);
					result1 = update.executeUpdate(); 
		       
				   if(result>0)
				   {
						String qry2="update sadarem_without_proof_request_master  set Proofdoc_Type='NA', Proof_Id='NA',updated_date=getdate() where sadarem_id=? ";
						PreparedStatement update1 = connection.prepareStatement(qry2);
						update1.setString(1, sadaremId);
						result2 = update1.executeUpdate();
						if(result2>=0)
						{
							connection.commit();
						}
						else
						{
							connection.rollback();
						}
			       }
				   else
					{
						connection.rollback();
					}
			   }
			   else
				{
					connection.rollback();
				}
	   DBConnection.closeConnection(connection); 
		}
		catch(Exception e)
		{
			DBConnection.closeConnection(connection);
			e.printStackTrace();
		}
		finally
		{
			try
			{
				updateLastName.close();
				DBConnection.closeConnection(connection); 
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			return result1;	
	}
}
