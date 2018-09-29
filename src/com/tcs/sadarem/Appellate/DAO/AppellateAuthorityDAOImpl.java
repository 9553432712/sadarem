package com.tcs.sadarem.Appellate.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.AppletAuthorityDAO;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dto.DoctorsInformationDTO;
import org.bf.disability.service.TransactionService;
import org.bf.disability.servicefactory.TransactionFactory;

import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

public class AppellateAuthorityDAOImpl implements AppellateAuthorityDAO 
{
	String lStrQuery="";
	public ArrayList getReassessmentDataList(String status)
	{
		ArrayList reassessList = new ArrayList();
	
	  try
	  {
		  lStrQuery =   
					"SELECT v.sadarem_id,t.tkt_req_id,\n" +
					"case when m.APELATE_CATEGORY='D' then 'Doubtful Case' when m.APELATE_CATEGORY='F' then 'Fraud Case'\n" + 
					"     when m.APELATE_CATEGORY='T' then 'Third time Reassessment' end  category,\n" + 
					"person_surname+''+v.person_name person_name,district_name,mandal_name,\n" + 
					"village_name,ISNULL((SELECT d.disability_name FROM tbldisability_details d WITH(NOLOCK) WHERE d.disability_id = t.disability_type),'-') disability_name,\n" + 
					"t.updated_date last_updated_date,DATEDIFF(day,t.updated_date ,current_timestamp) pending_days,CASE WHEN status_flag='HOLD' THEN 'Y' ELSE 'N' END status\n" + 
					"FROM\n" + 
					" sadarem_view_complete_details v WITH(NOLOCK),\n" + 
					"tkt_request_master t WITH(NOLOCK),\n" + 
					"tkt_request_tobe_modify_dtls m WITH(NOLOCK)\n" + 
					"WHERE\n" + 
					" v.sadarem_id = t.sadarem_id AND\n" + 
					" t.tkt_req_id=m.tkt_req_id AND\n" + 
					" v.sadarem_id = m.sadarem_id AND\n" + 
					" dis_form_status='APA' AND\n" + 
					" Category_ID=4 AND\n" + 
					" t.tkt_type_id='S018' \n" ;
		  if(status.equalsIgnoreCase("P"))
		  {
			  lStrQuery +=
					  	 " AND t.status_flag IN (SELECT decision_flag FROM  tkt_status_flow_mapping WHERE tkt_type_id='S018' AND request_type='A' AND is_final='Y') \n"+
					  	 " AND t.status_flag<>'HOLD' \n";
					  
		  }
		  else if(status.equalsIgnoreCase("H"))
		  {
			  lStrQuery +=" AND t.status_flag='HOLD' AND\n";
		  }
					
		  lStrQuery +=" AND IS_ASSESSED='N'\n" + 
					" ORDER BY t.updated_date,district_name,mandal_name";


		
		  //System.out.println("getReassessmentDataList Query : \n"+lStrQuery);
		  
		reassessList = DataAccess.pickData(lStrQuery, false, false);
	  }
	  catch(Exception e)
	  {
		  System.out.println("Exception raised in getReassessmentData"+e);
	  }
		return reassessList;
	}
	
	public int checkSADAREMIDFORAPPELLATE(String sadaremId)
	{
		String count="",qry="";
		ArrayList tempList = new ArrayList();
		ArrayList paramList = new ArrayList();
		int statusCount=0;
		try
		{
		  qry="select count(*) from tblperson_personal_details p WITH(NOLOCK),tkt_request_master t WITH(NOLOCK)  where  p.person_code = t.sadarem_id and\n"+
		            " view_edit_mode='APA' and status='Active' and tkt_type_id='S018' and  CategoryID=4 and person_code=? ";
		 
		 
		 
		 // count = DataAccess.getReturnResult(qry);
		 
		  tempList.add("S");
			tempList.add(sadaremId);
			paramList.add(tempList);
		 
			count = DataAccess.getReturnResultByPstmt(qry, paramList);
		  
		  
		  statusCount = Integer.parseInt(count);
		}
		catch(Exception e)
		{
			statusCount=0;
		}
		return statusCount;
	}
	
	public int checkOtpForSadarem(String sadaremId,String reqId,String contactNo,String otp)
	{
		int count =0;
		try
		{
			ArrayList paramList = new ArrayList();
			ArrayList tempList = new ArrayList();
			
			lStrQuery = "SELECT count(*) FROM tkt_apa_otp_details WHERE req_otp_no=? AND sadarem_id=? AND tkt_req_id=? AND contact_no=?";

			tempList = new ArrayList();
			tempList.add("S");
			tempList.add(otp);
			paramList.add(tempList);
			
			tempList = new ArrayList();
			tempList.add("S");
			tempList.add(sadaremId);
			paramList.add(tempList);
			
			tempList = new ArrayList();
			tempList.add("S");
			tempList.add(reqId);
			paramList.add(tempList);
			
			tempList = new ArrayList();
			tempList.add("S");
			tempList.add(contactNo);
			paramList.add(tempList);
						
			
			
			count = Integer.parseInt( CommonUtility.checkNullObj(DataAccess.getReturnResultByPstmt(lStrQuery, paramList)));
		}
		catch (Exception e) 
		{
			System.out.println("Exception in AppellateAuthorityDAOImpl @ checkOtpForSadarem method : "+e.getMessage());
		  e.printStackTrace();
		}
		return count;
	}

	public void updatePersonStatus(String personStatus, String sadaremId,String CategoryId, String Categorycount,int campId,String  loginid, Connection con) 
	{
		int result = 0;
        String sql = null;
      //  Statement st = null;
        PreparedStatement pstmt = null ;
        try 
        {
             
            sql = "update tblPerson_personal_details set person_status=?,categoryid=?,categorycount=?,RCamp_ID=?,RLogin=?,RDate=getDate()  where person_code=?";
            
            
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, personStatus);
            pstmt.setString(2, CategoryId);
            pstmt.setString(3, Categorycount);
            pstmt.setInt(4, campId );
            pstmt.setString(5, loginid); 
            pstmt.setString(6, sadaremId);
            
            result = pstmt.executeUpdate();

        } 
        catch (Exception exception)
        {
            exception.printStackTrace();
        } 
        finally 
        {
            try 
            {
                if (con != null)
                {
                    //DBConnection.closeConnection(con);
                    DBConnection.closeStatement(pstmt);                    
                }
            } 
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }		
	}

	public void checkPersonStatusForAppealAuthority(String sadaremId,String personStatus,Connection con) 
	{
	        String sql = null;      
	        PreparedStatement pstmt = null ;
	        try 
	        {
	           
	            sql = "update AppellateAuthorityandTemporary_RegistrationDetails set ViewEditMode='View',status='Inactive',updateddate=getDate(),disabilitystatus=? where person_code=?";
	            pstmt = con.prepareStatement(sql);
	            
	            pstmt.setString(1, personStatus);
	            pstmt.setString(2, sadaremId );
	            pstmt.executeUpdate();

	        } 
	        catch (SQLException sqlEx)
	        {	
	        	System.out.println("Exception in checkPersonStatusForAppealAuthority");
	        	sqlEx.printStackTrace();
	        } 
	        catch (Exception sqlEx)
	        {
	        	System.out.println("Exception in checkPersonStatusForAppealAuthority");
	        	sqlEx.printStackTrace();
	        } 
	        finally
	        {
	            try
	            {
	                if (con != null)
	                {
	                    DBConnection.closeStatement(pstmt);
	                    //DBConnection.closeConnection(con);
	                }
	            } 
	            catch (Exception e) 
	            {
	                e.printStackTrace();
	            }
	        }		
	}
	public int insertDisabilityDetailsAppleteDirectReject(String personcode, DoctorsInformationDTO DoctorsInfdto, String personStatus,String loginId,int disbID, String sysIP, int campId) throws SADAREMDBException, SQLException
	{			

        CallableStatement calstmt = null;
        Connection con = null;
        int i = 0;
        String Status = personStatus;
        String sql = null;
        String categoryID = null;
        String categoryCount = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        String personCode = null;
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try 
        {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(null, personcode);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            /** Updating the person Status Eligible / Rejected Based on selection and update categoryid and categorycount in tblPerson_personal_details*/		
            updatePersonStatus(personStatus,personcode,"4","1",campId,loginId,con);
            /** method for updating the view_edit_mode,updated_date,edit to view ,Active to Inactive in AppellateAuthorityandTemporary_RegistrationDetails*/
            checkPersonStatusForAppealAuthority(personcode,personStatus, con);
            
            /* To check the person status weather he is Eligible / Rejected start here **/
            
            sql = "select person_status from tblperson_personal_details  with (nolock) where person_code=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            if (rs != null)
            {
                while (rs.next())
                {
                    personCode = rs.getString(1);
                }
            }
            
            
            String HospName = null;
            String HospAddress = null;
            String Venue = null;
            
            sql = "select Name,Address,VenueName from tblcamp_details  with (nolock) where Camp_ID=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, campId);
            rs = pstmt.executeQuery();
            if (rs != null)
            {
                while (rs.next())
                {
                	HospName = rs.getString(1);
                	HospAddress =  rs.getString(2);
                	Venue =  rs.getString(3);
                }
            }

            /* End */
            if ("Rejected".equals(Status) && "Rejected".equals(personCode)) 
            {
                calstmt = con.prepareCall("{Call SP_tblRejectPerson_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                calstmt.setString(1, personcode);
                calstmt.setInt(2, disbID);
                calstmt.setString(3, null);
                calstmt.setString(4, null);
                calstmt.setString(5, null);
                calstmt.setString(6, null);
                //calstmt.setBoolean(6,partadto.isSpeechtherapy());
                calstmt.setString(7, null);
                calstmt.setString(8, null);
                calstmt.setString(9, null);
                calstmt.setString(10, null);
                calstmt.setString(11, null);
                calstmt.setString(12, null);
                calstmt.setString(13, null);
                calstmt.setString(14, HospName);
                calstmt.setString(15, HospAddress);
                calstmt.setString(16, DoctorsInfdto.getDoctorname1());
                calstmt.setString(17, DoctorsInfdto.getRegisterno1());
                calstmt.setString(18, DoctorsInfdto.getDesignation1());
                calstmt.setString(19, DoctorsInfdto.getDoctorname2());
                calstmt.setString(20, DoctorsInfdto.getRegisterno2());
                calstmt.setString(21, DoctorsInfdto.getDesignation2());
                calstmt.setString(22, DoctorsInfdto.getDoctorname3());
                calstmt.setString(23, DoctorsInfdto.getRegisterno3());
                calstmt.setString(24, DoctorsInfdto.getDesignation3());
                calstmt.setString(25, Venue);
                calstmt.setString(26, loginId);
                calstmt.setString(27, sysIP);
                calstmt.setInt(28, Integer.parseInt(details[0].toString()));
                calstmt.setInt(29, Integer.parseInt(details[1].toString()));
                i = calstmt.executeUpdate();
                
            }
            calstmt.close();
            if(i==1)
            {
                con.commit();
            }
            else
            {
            	con.rollback();
            }
            con.setAutoCommit(true);



        } 
        catch (Exception exception)
        {
            int exResult = ExceptionDAO.saveException(null, exception.toString(), "insertDisabilityDetailsAppleteDirectReject", "AppletteAu", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartADAO", "insertDisabilityDetailsAU");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(calstmt);

            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }
        return i;
    }
	
	public int updateHoldStatusofAppleteAuthority(String reqId,String remarks,String loginId,String ipaddress)
	{
		int result =0;
		Connection con = null;
	    PreparedStatement lPstmt = null;
		
		try
		{
			   con = DBConnection.getConnection();
		       con.setAutoCommit(false);
			   
			    
			    lStrQuery =  "insert into tkt_request_status_history(tkt_req_id,status_flag,doc_verify_status,status_remarks,modified_ip_address,modified_by,modified_date)"
			    		+ "select tkt_req_id,status_flag,doc_verify_status,status_remarks,ISNULL(update_ip_address,req_ip_address),updated_by,updated_date from tkt_request_master where tkt_req_id=?";	
			    
			    			    	
			    	
			    	lPstmt = con.prepareStatement(lStrQuery);
			    
			     lPstmt.setString(1,reqId );
			   int  rs = lPstmt.executeUpdate();
			    
			    if(rs > 0)
			    {
			    	rs = 0;
			    	lStrQuery =
			    			"update tkt_request_master SET \n"+
			    			" status_flag=?, \n"+
			    			" status_remarks=?, \n"+
			    			" doc_verify_status=?, \n"+
			    			" updated_date=getdate(), \n"+
			    			" updated_by=?, \n"+
			    			" update_ip_address=? WHERE tkt_req_id=?";
			    	
					  lPstmt = con.prepareStatement(lStrQuery);
					  
					     lPstmt.setString(1,"HOLD" );
				    	 lPstmt.setString(2,remarks );
				    	 lPstmt.setString(3,"Y" );
				    	 lPstmt.setString(4, loginId);
				    	 lPstmt.setString(5, ipaddress);
				    	 lPstmt.setString(6, reqId);
				    	 rs = lPstmt.executeUpdate();
				    	 
				    	 if(rs>0)
				    	 {
				    		 con.commit();
				    		 lPstmt.close();
				    		 con.close();
				    		 result = 1;
				    	 }
				    	 else
				    	 {
				    		 con.rollback();
				    		 lPstmt.close();
				    		 con.close(); 
				    		 result = 2;
				    	 }
			    }
			    else
			    {
			    	con.rollback();
		    		 lPstmt.close();
		    		 con.close(); 
		    		 result =3;
			    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result =-1;
		}
		finally
		{
			DBConnection.closeConnection(con);
			DBConnection.closeStatement(lPstmt);
		}
		
		return result;
	}
	public ArrayList getAppellateStatusTrackerReport()
	{
		ArrayList ResultList = new ArrayList();
	
	  try
	  {
		  lStrQuery =   

				  " select case when  v.districtid is null then 'Total' else v.districtid end 'District ID' , case when  v.District_Name is null then 'Total' else v.District_Name end 'District Name',	  \n" +
				  "  				count (case when t.tkt_type_id='S018' and f.decision_flag=t.status_flag  then t.tkt_req_id end) 'Total requests raised',  \n" +
				  "  				 \n" +
				  "  				count (case when t.tkt_type_id='S018' and f.decision_flag=t.status_flag and f.is_final='Y' and v.disp_min_per is not null  then 'Approved'  end) + count (case when t.tkt_type_id='S018' and f.decision_flag=t.status_flag and t.status_flag='HOLD' and v.disp_min_per is not null  then 'Approved'  end) - count (case when v.dis_form_status='APA' and t.status_flag!='HOLD' and f.decision_flag=t.status_flag and f.is_final='Y' and v.disp_min_per is not null then 'Total Pending' end) - count (case when v.dis_form_status='APA' and t.status_flag='HOLD' and v.disp_min_per is not null   then 'Total HOLDED' end) +  count (case when v.dis_form_status='APA' and t.status_flag!='HOLD' and f.decision_flag=t.status_flag and f.is_final='Y' then 'Total Pending' end) + count (case when v.dis_form_status='APA' and t.status_flag='HOLD'   then 'Total HOLDED' end) 'Total Approved',   \n" +
				  "  				 \n" +
				  "  				--assessed  \n" +
				  "  				count (case when t.tkt_type_id='S018' and f.decision_flag=t.status_flag and f.is_final='Y' and v.disp_min_per is not null  then 'Approved'  end) + count (case when t.tkt_type_id='S018' and f.decision_flag=t.status_flag and t.status_flag='HOLD' and v.disp_min_per is not null  then 'Approved'  end) - count (case when v.dis_form_status='APA' and t.status_flag!='HOLD' and f.decision_flag=t.status_flag and f.is_final='Y' and v.disp_min_per is not null then 'Total Pending' end) - count (case when v.dis_form_status='APA' and t.status_flag='HOLD' and v.disp_min_per is not null   then 'Total HOLDED' end) 'Total Assessed',  \n" +
				  "  				count (case when v.dis_form_status='APA' and t.status_flag!='HOLD' and f.decision_flag=t.status_flag and f.is_final='Y' then 'Total Pending' end) 'Total Pending',  \n" +
				  "  				count (case when v.dis_form_status='APA' and t.status_flag='HOLD'   then 'Total HOLDED' end) 'Total HOLDED',  \n" +
				  "  				count (case when t.tkt_type_id='S018' and f.decision_flag=t.status_flag and f.is_final='Y' and v.disp_min_per is not null  then 'Approved'  end) + count (case when t.tkt_type_id='S018' and f.decision_flag=t.status_flag and t.status_flag='HOLD' and v.disp_min_per is not null  then 'Approved'  end) - count (case when v.dis_form_status='APA' and t.status_flag!='HOLD' and f.decision_flag=t.status_flag and f.is_final='Y' and v.disp_min_per is not null then 'Total Pending' end) - count (case when v.dis_form_status='APA' and t.status_flag='HOLD' and v.disp_min_per is not null   then 'Total HOLDED' end) 'Total Assessed',  \n" +
				  "  				count (case when v.dis_form_status='View' and f.is_final='Y' and v.person_disp_percent>=v.disp_min_per then 'Total Eligible' end  )  'Total Eligible',  \n" +
				  "  				count (case when v.dis_form_status='View' and f.is_final='Y' and v.person_disp_percent<v.disp_min_per then 'Total Rejected' end  )  'Total Rejected',				  \n" +
				  "  				count (case when v.dis_form_status='View' and f.is_final='Y' and m.APELATE_CATEGORY='D' then 'Doubtful Case' end) 'Doubtful Case Total',  \n" +
				  "  				count (case when v.dis_form_status='View' and f.is_final='Y' and m.APELATE_CATEGORY='D' then (case when v.person_disp_percent>=v.disp_min_per then 'Doubtful Eligible' end) end) 'Doubtful Case Eligible',  \n" +
				  "  				count (case when v.dis_form_status='View' and f.is_final='Y' and m.APELATE_CATEGORY='D' then (case when v.person_disp_percent<v.disp_min_per then 'Doubtful Rejected' end) end) 'Doubtful Case Rejected',  \n" +
				  "  				count (case	when v.dis_form_status='View' and f.is_final='Y' and m.APELATE_CATEGORY='T' then 'Third time Reassessment Total' end )  'Third time Reassessment'	,			  \n" +
				  "  				count (case when v.dis_form_status='View' and f.is_final='Y' and m.APELATE_CATEGORY='T' then (case when v.person_disp_percent>=v.disp_min_per then 'Third time Eligible' end) end) 'Third time Case Eligible',  \n" +
				  "  				count (case when v.dis_form_status='View' and f.is_final='Y' and m.APELATE_CATEGORY='T' then (case when v.person_disp_percent<v.disp_min_per then 'Third time Rejected' end) end) 'Third time Case Rejected'  \n" +
				  "  				from sadarem_view_complete_details v,  \n" +
				  "  				tkt_request_master t,tkt_request_tobe_modify_dtls m--, tblPerson_Disability_Details con  \n" +
				  "  				,tkt_status_flow_mapping f  \n" +
				  "  				where v.sadarem_id = t.sadarem_id and t.tkt_req_id=m.tkt_req_id and v.sadarem_id = m.sadarem_id  \n" +
				  "  				and t.tkt_type_id='S018' --and con.Person_Code=v.sadarem_id and con.Status='Active'   \n" +
				  "  				 and t.tkt_type_id=f.tkt_type_id and t.status_flag=f.decision_flag   \n" +
				  "  				 group by GROUPING sets((v.districtid, v.District_Name),()) order by 1  \n" ;
					


		
		  
		ResultList = DataAccess.pickData(lStrQuery, false, false);
	  }
	  catch(Exception e)
	  {
		  System.out.println("Exception raised in getAppellateStatusTrackerReport"+e);
	  }
		return ResultList;
	}
	
}
