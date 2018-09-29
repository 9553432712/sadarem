package com.tcs.sadarem.openreports.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;

import com.tcs.sadarem.openreports.actions.EditPwdDetailsActionForm;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

public class UpdateCaptureDeadDetailsImpl implements UpdateCaptureDeadDetailsDAO 
{
	
	
	String lStrQuery="";
	Connection lcon;

	public ArrayList getPWDdata(String distid, String mandalid,String villageid, String habitationid,String sadaremid,String roleid) 
	{
		   ArrayList Datalist = new ArrayList();
		  
		try
			{
		  if(sadaremid == null || sadaremid.length()<17) 
		  {
			  sadaremid = "-1";
		  }
		  else if (sadaremid.length()>=17)
		  {
			  distid="-1";
			  mandalid="-1";
			  villageid="-1";
			  habitationid="-1";
		  }
 		 
			lStrQuery = " {CALL GET_EDIT_PWD_DETAILS(?,?,?,?,?,?)}";
			
			  ArrayList paramList = new ArrayList();
			  paramList.add(0,distid);
			  paramList.add(1,mandalid);
			  paramList.add(2,villageid);
			  paramList.add(3,habitationid);
			  paramList.add(4,sadaremid);
			  paramList.add(5,roleid);
			 
		   System.out.println("mandalid"+mandalid+"fghdf"+sadaremid+""+paramList);
			 Datalist = DataAccess.getStoredProcedureResultSet(lStrQuery,paramList,0,false,false);
			
			if(Datalist.size()>0)
			{
				Datalist = (ArrayList)Datalist.get(0);
			}
			}
			catch(SQLException sqle)
			{
				System.out.println(sqle.getErrorCode());
				sqle.printStackTrace();
			}
			catch(Exception e)
			{
				System.out.println(e.getLocalizedMessage());
				e.printStackTrace();
				
			}
	return Datalist;	
	}
	public ArrayList getSadaremPWDdata(String selSadaremId) 
	{
		ArrayList Datalist = new ArrayList();
		  ArrayList paramList = new ArrayList();
		  ArrayList tempList = new ArrayList();
		try
			{
		
					lStrQuery = "select Person_code,AADHAR_Exist,aadharCardNo,aadharRemarks,dead_or_alive,convert(varchar(20),deathdate,103) \n"+
								",Member_of_SHG,shgReason,shgname,shgid,convert(varchar(20),shgdate,103),AIDS_and_Appliances,appliancesReason,appliancestype\n"+
								",appliancesOrganization,convert(varchar(20),appliancesdate,103),Surgical_correction,SurgicalReason,SurgicaltypeOfApp,SurgicalOrganization\n"+
								",convert(varchar(20),Surgicaldate,103),PMJDY_details,PMJDYReason,PMJDYAccNo,PMJDYBank,PMJDYBranch,PMJDYIFSCCode,AASARA_ID,AASARARemarks\n"+
								",aasaraPensionId,servveid,serveDoneBy,designation,convert(varchar(20),recivedDate,103)\n"+
								"from datavalidationforpwd(nolock)where person_code=?";
			
			    		tempList.add("S");
			    		tempList.add(selSadaremId);
			    		paramList.add(tempList);
			
			 Datalist = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
						  
			}
			catch(SQLException sqle)
			{
				System.out.println(sqle.getErrorCode());
				sqle.printStackTrace();
			}
			catch(Exception e)
			{
				System.out.println(e.getLocalizedMessage());
				e.printStackTrace();
				
			}
	return Datalist;
	}
	public ArrayList getNewSadaremPWDdata(String selSadaremId) 
	{
		ArrayList Datalist = new ArrayList();
		  ArrayList paramList = new ArrayList();
		  ArrayList tempList = new ArrayList();
		try
			{
		
					lStrQuery =  "select SADAREM_ID,IS_AADHAR_EXIST,AADHAR_ID,AADHAR_REMARKS,IS_ALIVE,convert(varchar(20),DEATH_DATE,103) \n"+
								",IS_SHG_MEMBER,REASON_FOR_NOTSHG,SHG_NAME,SHG_ID,convert(varchar(20),SHG_JOIN_DATE,103),IS_AIDS_APPLIANCES,APPLIANCES_remarks,APPLIANCES_TYPE\n"+
								",APPLIANCES_ORGANIZATION,convert(varchar(20),APPLIANCES_DATE,103),IS_SURGICAL,SURGICAL_REMARKS,TYPEOF_SURGICAL,SURGICAL_ORGANIZATION"+
								",convert(varchar(20),SURGICAL_DATE,103),IS_PMJDY,PMJDY_REMARKS,PMJDY_ACCNO,PMJDY_BANK,PMJDY_BRANCH,PMJDY_IFSCCODE,IS_AASARA_COVERED,AASARA_REMARKS"+
								",AASARA_PENSIONID,SKS_ID,VERIFIED_BY,DESIGNATION_OF_VERIFIER,convert(varchar(20),VERIFICATION_DATE,103)\n"+
								"from PWD_DATA_VERIFICATION_DETAILS(nolock)where SADAREM_ID = ?";
			
			    		tempList.add("S");
			    		tempList.add(selSadaremId);
			    		paramList.add(tempList);
			
			 Datalist = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
						  
			}
			catch(SQLException sqle)
			{
				System.out.println(sqle.getErrorCode());
				sqle.printStackTrace();
			}
			catch(Exception e)
			{
				System.out.println(e.getLocalizedMessage());
				e.printStackTrace();
				
			}
	return Datalist;
	}
	public 	int getMappingCount(String sadaremID) throws Exception
	{
		String qry="";
		int result=0;
		
		qry="select count(*) from TASKTRACKINGTG..shgmappingDetails where  sadaremid="+sadaremID;
		
		result = Integer.parseInt(DataAccess.getReturnResult(qry));
		
		
		return result;
		
	}
	
	public ArrayList getVoList(String distId,String mandalId,String pancId) throws Exception{
		
		ArrayList voList = new ArrayList();
		try
		{
			ArrayList paramList = new ArrayList();
			ArrayList tempList = new ArrayList();
			
			lStrQuery = "SELECT VO_ID,VO_NAME FROM SHGMASTER..VOMASTER WHERE DISTRICT_ID=?"
                + " AND MANDAL_ID=?   ORDER BY VO_NAME";
		
			tempList = new ArrayList();
			tempList.add("I");
			tempList.add(distId);
			paramList.add(tempList);
			
			tempList = new ArrayList();
			tempList.add("I");
			tempList.add(mandalId);
			paramList.add(tempList);
		
		voList = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return voList;
	}
	
	
		public ArrayList getShgList(String distId,String voId) throws Exception{
		
		ArrayList shgList = new ArrayList();
		
		try
		{
		
			ArrayList paramList = new ArrayList();
			ArrayList tempList = new ArrayList();
			
			lStrQuery =  "select shgid,nameofpwdshg,void from TASKTRACKINGTG..SHGInformation  where void =? \n"
                + " union select shg_id collate Latin1_General_CI_AI,shg_name collate Latin1_General_CI_AI,vo_id collate Latin1_General_CI_AI from SHGMASTER..Shg_master" + distId + "" +
                " where vo_id =?";
		
		
		tempList = new ArrayList();
		tempList.add("S");
		tempList.add(voId);
		paramList.add(tempList);
		

		tempList = new ArrayList();
		tempList.add("S");
		tempList.add(voId);
		paramList.add(tempList);
		
		shgList = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return shgList;
	}
	
	public String getSelVoId(String distId,String shgid) throws Exception
	{
		String qry="",selVoId="";
		
		
		qry="select vo_id from SHGMASTER..Shg_master"+distId ;
				
		if(shgid.trim().length()>0)
		{	
		  qry +=" where shg_id='"+shgid+"'";
		}
		System.out.println("qry"+qry);
		selVoId = DataAccess.getReturnResult(qry);
		
		return selVoId;
		
	}
	
   public ArrayList getVoNameShgMappingDtls(String shgid,String distId)
   {
	   ArrayList voDtls = new ArrayList();
	   try 
	   {
		   ArrayList paramList = new ArrayList();
		   ArrayList tempList  = new ArrayList();
		   
		   lStrQuery = "SELECT vo_id,vo_name  FROM SHGMASTER..VOMASTER where district_id=?";
		   
		   tempList = new ArrayList();
			tempList.add("S");
			tempList.add(distId);
			paramList.add(tempList);
	   
	    if(shgid!=null && shgid.length()>0)
	    {
	    	lStrQuery += "   and vo_id = (select vo_id from SHGMASTER..Shg_master" +distId+"  where shg_id=?)";
	    	
	    	  tempList = new ArrayList();
				tempList.add("S");
				tempList.add(shgid);
				paramList.add(tempList);
	    }
	  System.out.println("vo "+lStrQuery);
	  
		voDtls = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
	} 
	   catch (Exception e) 
	{
		e.printStackTrace();
	}
	  return voDtls ; 
   }
public String submitConfirmedDetails(String sadaremid,String loginid,String roleid)
{
	 PreparedStatement pst = null;
	 String relustmsg="";
      int rs;
 	
 	try 
 	{
 		lcon = DBConnection.getConnection();
		//lcon.setAutoCommit(false);
		//System.out.println("desf"+"23".equals(roleid)+" "+roleid);
 	  if(CommonConstants.DPMLOGINROLEID.equals(roleid))
 	  { 
 		 lStrQuery = "update PWD_DATA_VERIFICATION_DETAILS  set is_confirmed_CI='Y',confirmed_by_CI=?,updated_by=?,updated_date=getdate() where sadarem_id =?";
 	  }
 	  else
 	  {
 		 lStrQuery = "update PWD_DATA_VERIFICATION_DETAILS  set is_confirmed_deo='Y',confirmed_by_deo=?,updated_by=?,updated_date=getdate() where sadarem_id =?";  
 	  }
		
    	 
		pst = lcon.prepareStatement(lStrQuery);
    	 
		pst.setString(1,loginid);
		pst.setString(2,loginid);
		pst.setString(3,sadaremid);
	     rs=pst.executeUpdate();
	    
	      if(rs>0)
	      {
	    	  relustmsg = "<font color='green'>Confirmed Successfully</font>"; 
	    	  
	      }
	      else
	      {
	    	  relustmsg = "<font color='red'>Confirm Failed</font>";
	      }
	      
	}
 	catch (SQLException e) 
 	{
		
		e.printStackTrace();
	}
 	 finally 
	   {
		 if(pst!=null) 
		  {
		      DBConnection.closeStatement(pst);
		      pst = null;
		  }
		  if(lcon!=null) 
		  {
		     DBConnection.closeConnection(lcon);
		     lcon=null;
		  }
	   }
	return relustmsg;
}
public ArrayList getEligibleDetails(String selSadaremId) 
{
			ArrayList Datalist = new ArrayList();
			ArrayList EligibleDetails = new ArrayList();
			try
				{
						lStrQuery = " {CALL SadaremidEligibleOrRejected(?)}";
							
						  ArrayList paramlist = new ArrayList();
						  paramlist.add(selSadaremId);
					
						 Datalist = DataAccess.getStoredProcedureResultSet(lStrQuery,paramlist,0,false,false);
					
					if(Datalist.size()>0 )
					{
						EligibleDetails =(ArrayList)Datalist.get(0);
					}
						  
							  
				}
				catch(SQLException sqle)
				{
					System.out.println(sqle.getErrorCode());
					sqle.printStackTrace();
				}
				catch(Exception e)
				{
					System.out.println(e.getLocalizedMessage());
					e.printStackTrace();
					
				}
		return EligibleDetails;	
}
public String insertRejectedpwdDetails(DataSource ds,String sadaremid,String aadharid,String serveid1,String serveDoneBy1,String designation1,String recivedDate1,String loginId,String aadharexist)
{
	int status=0;
	String msg="";
	 Connection con = null;
     PreparedStatement pstmt = null;
    
	
	 try {
		    lcon = DBConnection.getConnection();
			 
		
		
	    
	String	 qry ="insert into PWD_DATA_VERIFICATION_DETAILS (SADAREM_ID,AADHAR_ID,\n"+
				"SKS_ID,VERIFIED_BY,DESIGNATION_OF_VERIFIER,VERIFICATION_DATE,IS_ACTIVE,CREATED_BY,CREATED_DATE,is_eligable,IS_AADHAR_EXIST\n"+
				")\n"+
				"values(?,?,?,?,?,?,'Y','"+loginId+"',getdate(),'N',?)";
	
			 pstmt = lcon.prepareStatement(qry);
			 pstmt.setString(1,sadaremid);
			 
			 if(aadharid != null && aadharid.length()>0)
			 {
			   pstmt.setString(2,aadharid);
			 }
			 else
			 {
					System.out.println("aadhargfd"+aadharid);
				 pstmt.setNull(2, java.sql.Types.VARCHAR);
			 }
			 pstmt.setString(3,serveid1);
			 pstmt.setString(4,serveDoneBy1);
			 pstmt.setString(5,designation1);
			 if(recivedDate1!= null && recivedDate1.length()>0)
			 {
			 pstmt.setDate(6,  CommonUtility.getSqlDate(recivedDate1, "dd/MM/yyyy"));
			 }
			 else
			 {
				 pstmt.setNull(6, java.sql.Types.DATE); 
			 }
			 pstmt.setString(7,aadharexist);
			 status = pstmt.executeUpdate();
			 System.out.println("status"+status);
			 
			   if(status>0)
			      {
			    	  msg = "Succesfully"; 
			    	  
			      }
			      else
			      {
			    	  msg = "failed"; 
			      }
			      
			 
	
		
	 }
	 catch (Exception e)
	 	{
			 e.printStackTrace();
		} 
	 finally 
	   {
		 if(pstmt!=null) 
		  {
		      DBConnection.closeStatement(pstmt);
		      pstmt = null;
		  }
		  if(lcon!=null) 
		  {
		     DBConnection.closeConnection(lcon);
		     lcon=null;
		  }
	   }
	return msg;
}

public String updateRejectedpwdDetails(String sadaremid,String aadharid,String serveid1,String serveDoneBy1,String designation1,String recivedDate1,String loginId,String aadharexist)
{
	 PreparedStatement pst = null;
	 String relustmsg="";
      int rs1,rs2;
 	
 	try 
 	{
 		lcon = DBConnection.getConnection();
 		String qry = 
 				"insert into PWD_DATA_VERIFICATION_DETAILS_log (SADAREM_ID,AADHAR_ID,\n"+ 
 				" SKS_ID,VERIFIED_BY,DESIGNATION_OF_VERIFIER,VERIFICATION_DATE,IS_ACTIVE,CREATED_BY,CREATED_DATE,is_eligable,IS_AADHAR_EXIST,logged_date)\n"+
 		        "select SADAREM_ID,AADHAR_ID, \n"+
 			    "SKS_ID,VERIFIED_BY,DESIGNATION_OF_VERIFIER,VERIFICATION_DATE,IS_ACTIVE,CREATED_BY,CREATED_DATE,is_eligable,IS_AADHAR_EXIST,getdate()\n"+	
 			    "from PWD_DATA_VERIFICATION_DETAILS where sadarem_id='"+sadaremid+"'"; 
 			
 			pst = lcon.prepareStatement(qry);
 			rs1 =  pst.executeUpdate();
		
 			if(rs1>0)
 			{
					lStrQuery = "update PWD_DATA_VERIFICATION_DETAILS  set AADHAR_ID=?,SKS_ID=?,VERIFIED_BY=?,DESIGNATION_OF_VERIFIER=?,VERIFICATION_DATE=? \n"+
					             ",updated_by='"+loginId+"',updated_date=getdate(),is_eligable='N',IS_AADHAR_EXIST=? where SADAREM_ID =?";
			    	 
					pst = lcon.prepareStatement(lStrQuery);
			   
					System.out.println("in dao "+aadharid);
					 if(aadharid != null && aadharid.trim().length()>0)
					 {
					   pst.setString(1,aadharid);
					 }
					 else
					 {
						 pst.setNull(1, java.sql.Types.VARCHAR);
					 }
					pst.setString(2,serveid1);
					 pst.setString(3,serveDoneBy1);
					 pst.setString(4,designation1);
					 if(recivedDate1!= null && recivedDate1.length()>0)
					 {
					   pst.setDate(5,  CommonUtility.getSqlDate(recivedDate1, "dd/MM/yyyy"));
					 }
					 else
					 {
						 pst.setNull(5, java.sql.Types.DATE); 
					 }
					 pst.setString(6,aadharexist);
					 pst.setString(7,sadaremid);	
				     rs2=pst.executeUpdate();
				    
				      if(rs2>0)
				      {
				    	  relustmsg =  "Succesfully";  
				    	  
				      }
				      else
				      {
				    	  relustmsg = "failed";  
				      }
 			}
 			else
 			{
 				relustmsg = "failed";  
 			}
	}
 	
 	catch (SQLException e) 
 	{
		
		e.printStackTrace();
	}
 	finally 
	   {
		 if(pst!=null) 
		  {
		      DBConnection.closeStatement(pst);
		      pst = null;
		  }
		  if(lcon!=null) 
		  {
		     DBConnection.closeConnection(lcon);
		     lcon=null;
		  }
	   }
	return relustmsg;
}
public ArrayList getRejectDetails(String selSadaremId) 
{
				ArrayList Datalist = new ArrayList();
				  ArrayList paramList = new ArrayList();
				  ArrayList tempList = new ArrayList();
				try
					{
				
							lStrQuery = "select Person_code,AADHAR_Exist,aadharCardNo,servveid,serveDoneBy,designation,convert(varchar(20),recivedDate,103) \n"+
										"from datavalidationforpwd(nolock)where person_code=? "; 
					
					    		tempList.add("S");
					    		tempList.add(selSadaremId);
					    		paramList.add(tempList);
					 Datalist = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
					}
					catch(SQLException sqle)
					{
						System.out.println(sqle.getErrorCode());
						sqle.printStackTrace();
					}
					catch(Exception e)
					{
						System.out.println(e.getLocalizedMessage());
						e.printStackTrace();
						
					}
			return Datalist;
}
	
public ArrayList getRejectedSadaremDetails(String sadaremid) 
{
				ArrayList Datalist = new ArrayList();
				  ArrayList paramList = new ArrayList();
				  ArrayList tempList = new ArrayList();
				try
					{
				
							lStrQuery = "SELECT SADAREM_ID,FIRST_NAME+SPACE(2)+SURNAME PERSON_NAME,RELATION_NAME, \n"+
										"CASE WHEN P.GENDER='1' THEN 'MALE' WHEN P.GENDER=2 THEN 'FEMALE' END GENDER, \n"+
										"year(getdate())-year(P.DATE_OF_BIRTH ) AGE,M.MANDAL_NAME,V.VILLAGE_NAME,H.HABITATION_NAME,isnull(PHONE_NO,'-') CONTACT_NO, \n"+
										"CASE WHEN PW.IS_AADHAR_EXIST='EXIST' THEN PW.AADHAR_ID ELSE isnull(PW.AADHAR_REMARKS,'-') END AADHAR, \n"+
										"PW.SKS_ID,PW.verified_by,PW.designation_of_verifier,convert(varchar(20),PW.verification_date,103) \n"+
										"FROM  PWD_DATA_VERIFICATION_DETAILS PW (NOLOCK) \n"+
										"INNER JOIN TBLPERSON_PERSONAL_DETAILS P (NOLOCK) ON P.PERSON_CODE =PW.SADAREM_ID  \n"+
										"INNER JOIN  TBLMANDAL_DETAILS  M (NOLOCK) ON M.DISTRICT_ID =SUBSTRING(PW.SADAREM_ID,1,2) AND M.MANDAL_ID =SUBSTRING(PW.SADAREM_ID,6,2) \n"+
										"INNER JOIN TBLVILLAGE_DETAILS V (NOLOCK) ON V.DISTRICT_ID =SUBSTRING(PW.SADAREM_ID,1,2) AND V.MANDAL_ID =SUBSTRING(PW.SADAREM_ID,6,2) AND V.VILLAGE_ID=SUBSTRING(PW.SADAREM_ID,8,3) \n"+
										"INNER JOIN TBLHABITATION_DETAILS H (NOLOCK) ON  H.DISTRICT_ID =SUBSTRING(PW.SADAREM_ID,1,2) AND H.MANDAL_ID =SUBSTRING(PW.SADAREM_ID,6,2) AND H.VILLAGE_ID=SUBSTRING(PW.SADAREM_ID,8,3) \n"+
										"AND H.HABITATION_ID=SUBSTRING(PW.SADAREM_ID,11,2) AND PW.SADAREM_ID=? ";
					
					    		tempList.add("S");
					    		tempList.add(sadaremid);
					    		paramList.add(tempList);
					 Datalist = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
					}
					catch(SQLException sqle)
					{
						System.out.println(sqle.getErrorCode());
						sqle.printStackTrace();
					}
					catch(Exception e)
					{
						System.out.println(e.getLocalizedMessage());
						e.printStackTrace();
						
					}
			return Datalist;
}
	
public ArrayList getNewRejectDetails(String selSadaremId) 
{
				ArrayList Datalist = new ArrayList();
				  ArrayList paramList = new ArrayList();
				  ArrayList tempList = new ArrayList();
				try
					{
				
							lStrQuery = "select SADAREM_ID,IS_AADHAR_EXIST,AADHAR_ID,SKS_ID,VERIFIED_BY,DESIGNATION_OF_VERIFIER,convert(varchar(20),VERIFICATION_DATE,103)\n"+
										"from PWD_DATA_VERIFICATION_DETAILS(nolock)where SADAREM_ID=? ";
					
					    		tempList.add("S");
					    		tempList.add(selSadaremId);
					    		paramList.add(tempList);
					 Datalist = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
					}
					catch(SQLException sqle)
					{
						System.out.println(sqle.getErrorCode());
						sqle.printStackTrace();
					}
					catch(Exception e)
					{
						System.out.println(e.getLocalizedMessage());
						e.printStackTrace();
						
					}
			return Datalist;
}
public String isOldOrNew(String sadaremId)
{
	String oldOrNew="";
	String qry="";
	qry = "select count(*) from PWD_DATA_VERIFICATION_DETAILS where sadarem_id="+sadaremId;
	try {
		oldOrNew = DataAccess.getReturnResult(qry);
		if(Integer.parseInt(oldOrNew)>0)
			oldOrNew= "N";
		else
			oldOrNew ="Y"; 
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	
	return oldOrNew;
}

public int insertOldPwdDetails(DataSource ds,EditPwdDetailsActionForm form,String loginid) throws ParseException
{
	int status=0;
	String qry="";
	 Connection con = null;
     PreparedStatement pstmt = null;
     
	qry ="insert into PWD_DATA_VERIFICATION_DETAILS (SADAREM_ID,IS_AADHAR_EXIST,AADHAR_ID,IS_ALIVE,DEATH_DATE,IS_SHG_MEMBER,VO_ID,SHG_ID,SHG_JOIN_DATE,\n"+
				"REASON_FOR_NOTSHG,IS_AIDS_APPLIANCES,APPLIANCES_TYPE,APPLIANCES_ORGANIZATION,APPLIANCES_DATE,APPLIANCES_remarks,IS_SURGICAL,\n"+
				"TYPEOF_SURGICAL,SURGICAL_ORGANIZATION,SURGICAL_DATE,SURGICAL_REMARKS,IS_PMJDY,PMJDY_ACCNO,PMJDY_BANK,PMJDY_BRANCH,PMJDY_IFSCCODE,\n"+
				"IS_AASARA_COVERED,AASARA_PENSIONID,AASARA_REMARKS,SKS_ID,VERIFIED_BY,DESIGNATION_OF_VERIFIER,VERIFICATION_DATE,\n"+
				"IS_ACTIVE,CREATED_BY,CREATED_DATE,pmjdy_remarks,shg_name)\n"+
				"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'Y','"+loginid+"',getdate(),?,?)"; 
	 try {
		 
		 
		 /*Date deathDate = new SimpleDateFormat("yyyy/mm/dd").parse(form.getDeadDate());
		 Date aidDate = new SimpleDateFormat("yyyy/mm/dd").parse(form.getAidsdate());
		   
		 Date surDate = new SimpleDateFormat("yyyy/mm/dd").parse(form.getSurdate());
		   
		 Date recDate = new SimpleDateFormat("yyyy/mm/dd").parse(form.getRecivedDate());
		  
		 Date shgData = new SimpleDateFormat("yyyy/mm/dd").parse(form.getShgdate());*/ 
		 
		
		 System.out.println(form.getExist()+""+form.getShg()+""+form.getAlive());
			 
		 
		con = DBConnection.getConnection();
	
	 pstmt = con.prepareStatement(qry);
	 pstmt.setString(1, form.getPersoncode());
	 pstmt.setString(2, form.getExist());
	 if(form.getAadharno()!=null && form.getAadharno().length()>0)
	 {
	   pstmt.setString(3, form.getAadharno());
	 }
	 else
	 {
		 pstmt.setNull(3, java.sql.Types.VARCHAR);
	 }
	 pstmt.setString(4, form.getAlive());
	 if(form.getDeadDate()!= null && form.getDeadDate().length()>0)
	 {
		 pstmt.setDate(5, CommonUtility.getSqlDate(form.getDeadDate(), "dd/MM/yyyy"));
	 }
	 else
	 {
	  pstmt.setNull(5, java.sql.Types.DATE);
	 }
	 pstmt.setString(6, form.getShg());
	 pstmt.setString(7, form.getVo());
	 pstmt.setString(8, form.getShglist());
	 if(form.getShgdate()!=null && form.getShgdate().length()>0)
	 {
	  pstmt.setDate(9, CommonUtility.getSqlDate(form.getShgdate(), "dd/MM/yyyy"));
	 }
	 else
	 {
		 pstmt.setNull(9, java.sql.Types.DATE); 
	 }
	 pstmt.setString(10, form.getNotshgreason());
	 pstmt.setString(11, form.getAids());
	 pstmt.setString(12, form.getApplType());
	 pstmt.setString(13, form.getAidsorganisanation());
	 if(form.getAidsdate()!=null && form.getAidsdate().length()>0)
	 {
	 pstmt.setDate(14, CommonUtility.getSqlDate(form.getAidsdate(), "dd/MM/yyyy"));
	 }
	 else
	 {
		 pstmt.setNull(14, java.sql.Types.DATE); 
	 }
	 pstmt.setString(15, form.getAidsreason());
	 pstmt.setString(16, form.getSurgical());
	 pstmt.setString(17, form.getSurType());
	 pstmt.setString(18, form.getSurorganisanation());
	 if(form.getSurdate()!=null && form.getSurdate().length()>0)
	 {
	   pstmt.setDate(19,CommonUtility.getSqlDate(form.getSurdate(), "dd/MM/yyyy"));
	 }
	 else
	 {
		 pstmt.setNull(19, java.sql.Types.DATE); 
	 }
	 pstmt.setString(20, form.getSurreason());
	 pstmt.setString(21, form.getPmjdy());
	 pstmt.setString(22, form.getAccno());
	 pstmt.setString(23, form.getBank());
	 pstmt.setString(24, form.getBranch());
	 pstmt.setString(25, form.getIfsc());
	 pstmt.setString(26, form.getAasara());
	 pstmt.setString(27, "");
	 pstmt.setString(28, form.getAasarareason());
	 pstmt.setString(29, form.getServeid());
	 pstmt.setString(30, form.getServeDoneBy());
	 pstmt.setString(31, form.getDesignation());
	 if(form.getRecivedDate()!= null && form.getRecivedDate().length()>0)
	 {
	 pstmt.setDate(32,  CommonUtility.getSqlDate(form.getRecivedDate(), "dd/MM/yyyy"));
	 }
	 else
	 {
		 pstmt.setNull(32, java.sql.Types.DATE); 
	 }
	 pstmt.setString(33,form.getPmjnreason());
	 pstmt.setString(34, form.getShgname());
	 status = pstmt.executeUpdate();
	 System.out.println("in dq"+status);
	 
	 } catch (SQLException e) {
			 e.printStackTrace();
		}
	 finally 
	   {
		 if(pstmt!=null) 
		  {
		      DBConnection.closeStatement(pstmt);
		      pstmt = null;
		  }
		  if(lcon!=null) 
		  {
		     DBConnection.closeConnection(lcon);
		     lcon=null;
		  }
	   }
	
	return status;
}


public int updateOldPwdDetails(DataSource ds,EditPwdDetailsActionForm form,String loginid) throws ParseException
{
	int status1=0,status2=0;
	String qry="";
	 Connection con = null;
     PreparedStatement pstmt = null;
     
	
	 try {
		 
		 
		 
		 
		 con = DBConnection.getConnection();
		 
		 
		 
		 qry =
				" insert into PWD_DATA_VERIFICATION_DETAILS_log (SADAREM_ID,IS_AADHAR_EXIST,AADHAR_ID,IS_ALIVE,DEATH_DATE,IS_SHG_MEMBER,VO_ID,SHG_ID,SHG_JOIN_DATE, \n"+
					"	 REASON_FOR_NOTSHG,IS_AIDS_APPLIANCES,APPLIANCES_TYPE,APPLIANCES_ORGANIZATION,APPLIANCES_DATE,APPLIANCES_remarks,IS_SURGICAL, \n"+
					"	 TYPEOF_SURGICAL,SURGICAL_ORGANIZATION,SURGICAL_DATE,SURGICAL_REMARKS,IS_PMJDY,PMJDY_ACCNO,PMJDY_BANK,PMJDY_BRANCH,PMJDY_IFSCCODE, \n"+
					"	 IS_AASARA_COVERED,AASARA_PENSIONID,AASARA_REMARKS,SKS_ID,VERIFIED_BY,DESIGNATION_OF_VERIFIER,VERIFICATION_DATE, \n"+
					"	IS_ACTIVE,CREATED_BY,CREATED_DATE,pmjdy_remarks,shg_name,logged_date )\n"+
					"	select SADAREM_ID,IS_AADHAR_EXIST,AADHAR_ID,IS_ALIVE,DEATH_DATE,IS_SHG_MEMBER,VO_ID,SHG_ID,SHG_JOIN_DATE,\n"+ 
					"	 REASON_FOR_NOTSHG,IS_AIDS_APPLIANCES,APPLIANCES_TYPE,APPLIANCES_ORGANIZATION,APPLIANCES_DATE,APPLIANCES_remarks,IS_SURGICAL,\n"+ 
					"	 TYPEOF_SURGICAL,SURGICAL_ORGANIZATION,SURGICAL_DATE,SURGICAL_REMARKS,IS_PMJDY,PMJDY_ACCNO,PMJDY_BANK,PMJDY_BRANCH,PMJDY_IFSCCODE,\n"+ 
					"	 IS_AASARA_COVERED,AASARA_PENSIONID,AASARA_REMARKS,SKS_ID,VERIFIED_BY,DESIGNATION_OF_VERIFIER,VERIFICATION_DATE, \n"+
					"	IS_ACTIVE,CREATED_BY,CREATED_DATE,pmjdy_remarks,shg_name,getdate()  from PWD_DATA_VERIFICATION_DETAILS where sadarem_id='"+form.getPersoncode()+"'";
		  
		 pstmt = con.prepareStatement(qry);
		 System.out.println("qry"+qry);
		 status1 = pstmt.executeUpdate();
		
		 if(status1>0)
		 {
				 qry ="update PWD_DATA_VERIFICATION_DETAILS set SADAREM_ID=?,IS_AADHAR_EXIST=?,AADHAR_ID=?,IS_ALIVE=?,DEATH_DATE=?,IS_SHG_MEMBER=?,VO_ID=?,SHG_ID=?,SHG_JOIN_DATE=?,\n"+
							"REASON_FOR_NOTSHG=?,IS_AIDS_APPLIANCES=?,APPLIANCES_TYPE=?,APPLIANCES_ORGANIZATION=?,APPLIANCES_DATE=?,APPLIANCES_remarks=?,IS_SURGICAL=?,\n"+
							"TYPEOF_SURGICAL=?,SURGICAL_ORGANIZATION=?,SURGICAL_DATE=?,SURGICAL_REMARKS=?,IS_PMJDY=?,PMJDY_ACCNO=?,PMJDY_BANK=?,PMJDY_BRANCH=?,PMJDY_IFSCCODE=?,\n"+
							"IS_AASARA_COVERED=?,AASARA_PENSIONID=?,AASARA_REMARKS=?,SKS_ID=?,VERIFIED_BY=?,DESIGNATION_OF_VERIFIER=?,VERIFICATION_DATE=?,\n"+
							"UPDATED_BY='"+loginid+"',UPDATED_DATE=getdate(),pmjdy_remarks=?,shg_name=?\n"+
							"where sadarem_id=?";
				 
		 
		  
				 pstmt = con.prepareStatement(qry);
				 pstmt.setString(1, form.getPersoncode());
				 pstmt.setString(2, form.getExist());
				 
				 if(form.getAadharno()!=null && form.getAadharno().length()>0)
				 {
				   pstmt.setString(3, form.getAadharno());
				 }
				 else
				 {
					 pstmt.setNull(3, java.sql.Types.VARCHAR);
				 }
				 pstmt.setString(4, form.getAlive());
				 if(form.getDeadDate()!= null && form.getDeadDate().length()>0)
				 {
					 pstmt.setDate(5, CommonUtility.getSqlDate(form.getDeadDate(), "dd/MM/yyyy"));
				 }
				 else
				 {
				  pstmt.setNull(5, java.sql.Types.DATE);
				 }
				 pstmt.setString(6, form.getShg());
				 pstmt.setString(7, form.getVo());
				 pstmt.setString(8, form.getShglist());
				 if(form.getShgdate()!=null && form.getShgdate().length()>0)
				 {
				  pstmt.setDate(9, CommonUtility.getSqlDate(form.getShgdate(), "dd/MM/yyyy"));
				 }
				 else
				 {
					 pstmt.setNull(9, java.sql.Types.DATE); 
				 }
				 pstmt.setString(10, form.getNotshgreason());
				 pstmt.setString(11, form.getAids());
				 pstmt.setString(12, form.getApplType());
				 pstmt.setString(13, form.getAidsorganisanation());
				 if(form.getAidsdate()!=null && form.getAidsdate().length()>0)
				 {
				 pstmt.setDate(14, CommonUtility.getSqlDate(form.getAidsdate(), "dd/MM/yyyy"));
				 }
				 else
				 {
					 pstmt.setNull(14, java.sql.Types.DATE); 
				 }
				 pstmt.setString(15, form.getAidsreason());
				 pstmt.setString(16, form.getSurgical());
				 pstmt.setString(17, form.getSurType());
				 pstmt.setString(18, form.getSurorganisanation());
				 if(form.getSurdate()!=null && form.getSurdate().length()>0)
				 {
				   pstmt.setDate(19,CommonUtility.getSqlDate(form.getSurdate(), "dd/MM/yyyy"));
				 }
				 else
				 {
					 pstmt.setNull(19, java.sql.Types.DATE); 
				 }
				 pstmt.setString(20, form.getSurreason());
				 pstmt.setString(21, form.getPmjdy());
				 pstmt.setString(22, form.getAccno());
				 pstmt.setString(23, form.getBank());
				 pstmt.setString(24, form.getBranch());
				 pstmt.setString(25, form.getIfsc());
				 pstmt.setString(26, form.getAasara());
				 pstmt.setString(27, "");
				 pstmt.setString(28, form.getAasarareason());
				 pstmt.setString(29, form.getServeid());
				 pstmt.setString(30, form.getServeDoneBy());
				 pstmt.setString(31, form.getDesignation());
				 if(form.getRecivedDate()!= null && form.getRecivedDate().length()>0)
				 {
				 pstmt.setDate(32,  CommonUtility.getSqlDate(form.getRecivedDate(), "dd/MM/yyyy"));
				 }
				 else
				 {
					 pstmt.setNull(32, java.sql.Types.DATE); 
				 }
				 pstmt.setString(33,form.getPmjnreason());
				 pstmt.setString(34, form.getShgname());
				 pstmt.setString(35, form.getPersoncode());
				 
				 status2 = pstmt.executeUpdate();
		 }
	 System.out.println("in dq"+status2);
	 
	 } catch (SQLException e) {
			 e.printStackTrace();
		}
	 finally 
	   {
		 if(pstmt!=null) 
		  {
		      DBConnection.closeStatement(pstmt);
		      pstmt = null;
		  }
		  if(lcon!=null) 
		  {
		     DBConnection.closeConnection(lcon);
		     lcon=null;
		  }
	   }
	
	return status2;
}
public ArrayList getReqSadaremDetails(String sadaremid)
{
		ArrayList Datalist = new ArrayList();
		  ArrayList paramList = new ArrayList();
		  ArrayList tempList = new ArrayList();
		try
			{ 
		
			
					lStrQuery = 
							"SELECT SADAREM_ID,FIRST_NAME+SPACE(2)+SURNAME PERSON_NAME,RELATION_NAME, \n"+
							"CASE WHEN P.GENDER='1' THEN 'MALE' WHEN P.GENDER=2 THEN 'FEMALE' END GENDER,year(getdate())-year(P.DATE_OF_BIRTH ) AGE,M.MANDAL_NAME,V.VILLAGE_NAME,H.HABITATION_NAME,isnull(PHONE_NO,'-') CONTACT_NO , \n"+
							"CASE WHEN PW.IS_AADHAR_EXIST='EXIST' THEN PW.AADHAR_ID ELSE ISNULL(PW.AADHAR_REMARKS,'-') END AADHAR, \n"+
							"CASE WHEN PW.IS_ALIVE='ALIVE' THEN 'ALIVE' ELSE 'DEATH'+SPACE(2)+CONVERT(VARCHAR(20),PW.DEATH_DATE,103) END PERSON_STATUS, \n"+
							"CASE WHEN PW.IS_SHG_MEMBER=1 THEN 'EXISTS' ELSE 'Not Applicable , '+SPACE(2)+PW.REASON_FOR_NOTSHG END PERSON_SHG, \n"+
							"PW.VO_ID,PW.SHG_ID,PW.SHG_NAME,convert(varchar(20),PW.SHG_JOIN_DATE ,103), \n"+
							"CASE WHEN PW.IS_AIDS_APPLIANCES='Received' THEN 'Received' WHEN PW.IS_AIDS_APPLIANCES='NotReceived' THEN 'Not Received , '+SPACE(2)+ISNULL(PW.APPLIANCES_REMARKS,'') ELSE 'Not Required' END AIDS_APPLIANCES, \n"+
							"PW.APPLIANCES_TYPE,PW.APPLIANCES_ORGANIZATION,convert(varchar(20),PW.APPLIANCES_DATE ,103), \n"+
							"CASE WHEN PW.IS_SURGICAL='DONE' THEN 'DONE' WHEN PW.IS_SURGICAL='RequiredSurgery' THEN 'Required Surgery ,'+SPACE(4)+ISNULL(PW.SURGICAL_REMARKS,'')  ELSE 'Not Required'END IS_SURGICAL, \n"+
							"PW.TYPEOF_SURGICAL,PW.SURGICAL_ORGANIZATION,convert(varchar(20),PW.SURGICAL_DATE ,103), \n"+
							"CASE WHEN PW.IS_PMJDY='Covered' THEN 'Covered' ELSE 'Not Covered , '+SPACE(4)+ISNULL(PW.PMJDY_REMARKS,'') END  IS_PMJDY, \n"+
							"PW.PMJDY_ACCNO ,PW.PMJDY_BANK,PW.PMJDY_BRANCH,PW.PMJDY_IFSCCODE, \n"+
							"CASE WHEN PW.IS_AASARA_COVERED='Sanctioned' THEN 'Sanctioned' ELSE 'Not Sanctioned , '+SPACE(4)+ISNULL(PW.AASARA_REMARKS,'') END  IS_AASARA_COVERED, \n"+
							"PW.AASARA_PENSIONID,PW.SKS_ID, \n"+
							"PW.verified_by,PW.designation_of_verifier,convert(varchar(20),PW.verification_date,103) \n"+
							"FROM  PWD_DATA_VERIFICATION_DETAILS PW (NOLOCK) \n"+
							"INNER JOIN TBLPERSON_PERSONAL_DETAILS P (NOLOCK) ON P.PERSON_CODE =PW.SADAREM_ID  \n"+
							"INNER JOIN  TBLMANDAL_DETAILS  M (NOLOCK) ON M.DISTRICT_ID =SUBSTRING(PW.SADAREM_ID,1,2) AND M.MANDAL_ID =SUBSTRING(PW.SADAREM_ID,6,2)  \n"+
							"INNER JOIN TBLVILLAGE_DETAILS V (NOLOCK) ON V.DISTRICT_ID =SUBSTRING(PW.SADAREM_ID,1,2) AND V.MANDAL_ID =SUBSTRING(PW.SADAREM_ID,6,2) AND V.VILLAGE_ID=SUBSTRING(PW.SADAREM_ID,8,3) \n"+
							"INNER JOIN TBLHABITATION_DETAILS H (NOLOCK) ON  H.DISTRICT_ID =SUBSTRING(PW.SADAREM_ID,1,2) AND H.MANDAL_ID =SUBSTRING(PW.SADAREM_ID,6,2) AND H.VILLAGE_ID=SUBSTRING(PW.SADAREM_ID,8,3) \n"+
							"AND H.HABITATION_ID=SUBSTRING(PW.SADAREM_ID,11,2) AND PW.SADAREM_ID=? ";
			    		tempList.add("S");
			    		tempList.add(sadaremid);
			    		paramList.add(tempList);
			    		
			 Datalist = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			}
			catch(SQLException sqle)
			{
				System.out.println(sqle.getErrorCode());
				sqle.printStackTrace();
			}
			catch(Exception e)
			{
				System.out.println(e.getLocalizedMessage());
				e.printStackTrace();
				
			}
		return Datalist;
}
public ArrayList getBisicDetails(String selSadaremId) 
{
	ArrayList Datalist = new ArrayList();
	  ArrayList paramList = new ArrayList();
	  ArrayList tempList = new ArrayList();
	try
		{
	
				lStrQuery = 
						"SELECT PERSON_CODE,FIRST_NAME+SPACE(2)+SURNAME PERSON_NAME,RELATION_NAME, \n"+
						"CASE WHEN P.GENDER='1' THEN 'MALE' WHEN P.GENDER=2 THEN 'FEMALE' END GENDER, \n"+
						" P.AGE_YEARS AGE,M.MANDAL_NAME,V.VILLAGE_NAME,H.HABITATION_NAME,isnull(PHONE_NO,'-') CONTACT_NO  \n"+
						"FROM  TBLPERSON_PERSONAL_DETAILS P (NOLOCK)  \n"+
						"INNER JOIN  TBLMANDAL_DETAILS  M (NOLOCK) ON M.DISTRICT_ID =SUBSTRING(P.PERSON_CODE,1,2) AND M.MANDAL_ID =SUBSTRING(P.PERSON_CODE,6,2)  \n"+
						"INNER JOIN TBLVILLAGE_DETAILS V (NOLOCK) ON V.DISTRICT_ID =SUBSTRING(P.PERSON_CODE,1,2) AND V.MANDAL_ID =SUBSTRING(P.PERSON_CODE,6,2) AND V.VILLAGE_ID=SUBSTRING(P.PERSON_CODE,8,3) \n"+
						"INNER JOIN TBLHABITATION_DETAILS H (NOLOCK) ON  H.DISTRICT_ID =SUBSTRING(P.PERSON_CODE,1,2) AND H.MANDAL_ID =SUBSTRING(P.PERSON_CODE,6,2) AND H.VILLAGE_ID=SUBSTRING(P.PERSON_CODE,8,3) \n"+
						"AND H.HABITATION_ID=SUBSTRING(P.PERSON_CODE,11,2) AND P.PERSON_CODE=?";
		    		
				     tempList.add("S");
		    		tempList.add(selSadaremId);
		    		paramList.add(tempList);
		    		
		 Datalist = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle.getErrorCode());
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
			
		}
	return Datalist;
}

public int getAadharCount(String sadaremid,String aadharId)
{
	String qry ="",count="0";
	
	qry = "select count(*) from pwd_data_verification_details where sadarem_id <> '"+sadaremid+"' and aadhar_id='"+aadharId+"'";
	
	try {
		count = DataAccess.getReturnResult(qry);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return Integer.parseInt(count);
	
}

public ArrayList getpwdupdatedvalAbstReport(String fromdate, String todate,String distId,String mandalId,String villageId,String areatype) 
{
	 
	ArrayList Datalist = new ArrayList();
	ArrayList ReportDatalist = new ArrayList();
	try
		{
	
		      /*lStrQuery = " {CALL myproc(?,?,?,?)}";*/
				lStrQuery = " {CALL PRO_SADAREM_PWD_DATA_VALIDATION_REPORT (?,?,?,?,?,?)}";
					
				  ArrayList paramlist = new ArrayList();
				  
				  paramlist.add(fromdate);
				  paramlist.add(todate);
				  paramlist.add(distId);
				  paramlist.add(mandalId);
				 // paramlist.add("-1");
				 paramlist.add(villageId);
				 paramlist.add(areatype);
				  
				  //System.out.println("paramlist : "+paramlist);
				 
				 Datalist = DataAccess.getStoredProcedureResultSet(lStrQuery,paramlist,0,false,false);
			
			if(Datalist.size()>0 )
			{
				ReportDatalist =(ArrayList)Datalist.get(0);
			}
				  
					  
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle.getErrorCode());
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
			
		}
return ReportDatalist;	
}
public ArrayList getPwdDetaildata(String distid, String mandalid,String villageid, String habitationid,String sadaremid) 
{
	   ArrayList Datalist = new ArrayList();
	  
	try
		{
	  if(sadaremid == null || sadaremid.length()<17)
	  {
		  sadaremid = "-1";
	  }
	  else if (sadaremid.length()==17)
	  {
		  distid="-1";
		  mandalid="-1";
		  villageid="-1";
		  habitationid="-1";
	  }
		 
		lStrQuery = " {CALL GET_EDIT_OLD_PWD_DETAILS(?,?,?,?,?)}";
		
		  ArrayList paramList = new ArrayList();
		  paramList.add(0,distid);
		  paramList.add(1,mandalid);
		  paramList.add(2,villageid);
		  paramList.add(3,habitationid);
		  paramList.add(4,sadaremid);
		 
		 
	   System.out.println("mandalid"+mandalid+"fghdf"+sadaremid+""+paramList);
		 Datalist = DataAccess.getStoredProcedureResultSet(lStrQuery,paramList,0,false,false);
		
		if(Datalist.size()>0)
		{
			Datalist = (ArrayList)Datalist.get(0);
		}
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle.getErrorCode());
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
			
		}
return Datalist;	
}



public ArrayList getDetailedLatestPWDdata(String distid, String mandalid,String villageid, String habitationid,String sadaremid,String roleid) 
{
	   ArrayList Datalist = new ArrayList();
	  
	try
		{
	  if(sadaremid == null || sadaremid.length()<17) 
	  {
		  sadaremid = "-1";
	  }
	  else if (sadaremid.length()>=17)
	  {
		  distid="-1";
		  mandalid="-1";
		  villageid="-1";
		  habitationid="-1";
	  }
		 
		lStrQuery = " {CALL GET_EDIT_PWD_DETAILED_DATA(?,?,?,?,?,?)}";
		
		  ArrayList paramList = new ArrayList();
		  paramList.add(0,distid);
		  paramList.add(1,mandalid);
		  paramList.add(2,villageid);
		  paramList.add(3,habitationid);
		  paramList.add(4,sadaremid);
		  paramList.add(5,roleid);
		 
	   System.out.println("mandalid"+mandalid+"fghdf"+sadaremid+""+paramList);
		 Datalist = DataAccess.getStoredProcedureResultSet(lStrQuery,paramList,0,false,false);
		
		if(Datalist.size()>0)
		{
			Datalist = (ArrayList)Datalist.get(0);
		}
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle.getErrorCode());
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
			
		}
return Datalist;	
}

}
