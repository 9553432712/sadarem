/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.FunctionalNeedReportDTO;

import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author SADAREM
 */
public class ReAssessmentCampWiseReportDAO {

    public ArrayList getDistrictDetials(DataSource ds) throws SADAREMDBException, SQLException {

        ArrayList districtList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        //ReAssessmentCampWiseReportDTO  reAssessmentCampWiseReportDTO = null;

        FunctionalNeedReportDTO functionalNeedReportDTO = null;

        try {
            con = DBConnection.getConnection();
            query = "select District_ID,District_Name from dbo.tblDistrict_Details order by District_Name";

            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();

            if (rs != null) {

                while (rs.next()) {
                    functionalNeedReportDTO = new FunctionalNeedReportDTO();

                    functionalNeedReportDTO.setDistrict_id(rs.getString(1));
                    functionalNeedReportDTO.setDistrictName(rs.getString(2));


                    districtList.add(functionalNeedReportDTO);

                }

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistrictDetials", "ReAssessmentCampWiseReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReAssessmentCampWiseReportDAO", "getDistrictDetials");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistrictDetials", "ReAssessmentCampWiseReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReAssessmentCampWiseReportDAO", "getDistrictDetials");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return districtList;
    }

    public ArrayList getCampDetails(DataSource ds, String district_id) throws SADAREMDBException, SQLException {

        ArrayList campListt = new ArrayList();
        ArrayList campList = new ArrayList();
        
     //   Connection con = null;
     //   PreparedStatement pstmt = null;
     //   ResultSet rs = null;
        String query = null;
        FunctionalNeedReportDTO functionalNeedReportDTO = null;

        try {

       //     con = DBConnection.getConnection();

            query = "select camp_id, venueName from dbo.tblCamp_Details where district_id=? and status='Active' ";
           // pstmt = con.prepareStatement(query);
            
            
            //int a
            
            ArrayList inner	= new ArrayList(); 
            
            ArrayList resultList = new ArrayList();
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();

                	   tempList.add("S");
   			    		tempList.add(district_id);
   			    		paramList.add(tempList);
   			    		
        		
    		  //	log.info("getWithoutProofPartaList lStrQuery : "+query);
    		  	
   			    		campListt=(ArrayList)DataAccess.pickDataByPrepareStmt(query, paramList, false, false);     
            
            
   			    	    if (campListt != null && campListt.size()>0) {
   			    	    	
   			                for(int i=0;i<campListt.size();i++) {
   			                	
   			                	inner = (ArrayList) campListt.get(i);
   			                	
   	 			            functionalNeedReportDTO = new FunctionalNeedReportDTO();
   		                    functionalNeedReportDTO.setCamp_id((String)inner.get(0));
   		                    functionalNeedReportDTO.setCamp_name((String)inner.get(1));

   			                 campList.add(functionalNeedReportDTO);
   			                }
   			            }   
            
            
            System.out.println("-------"+campList);
            
              //            
     /*       rs = pstmt.executeQuery();

            if (rs != null) {

                while (rs.next()) {
                    functionalNeedReportDTO = new FunctionalNeedReportDTO();
                    functionalNeedReportDTO.setCamp_id(rs.getString(1));
                    functionalNeedReportDTO.setCamp_name(rs.getString(2));
                    campList.add(functionalNeedReportDTO);
                }

            }*/

        } catch (SQLException sqlEx) { 
        	sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDetails", "ReAssessmentCampWiseReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReAssessmentCampWiseReportDAO", "getCampDetails");
        } catch (Exception sqlEx) {
        	sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDetails", "ReAssessmentCampWiseReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReAssessmentCampWiseReportDAO", "getCampDetails");
        } finally {
           // DBConnection.closeConnection(con);
           // DBConnection.closeResultSet(rs);
           // DBConnection.closeStatement(pstmt);

        }


        return campList;
    }

    public ArrayList getReAssessmentDetails(DataSource ds, String district_id, String camp_id) throws SADAREMDBException, SQLException {

        ArrayList assesmentList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        Map disabilityData = null;
        PreparedStatement PstmtInActive = null;
        PreparedStatement PstmtActive = null;
        PreparedStatement PstmtDoctorDetails = null;
        PreparedStatement rejectedPsInactive = null;
        PreparedStatement rejectedActive = null;

        ResultSet rs = null;
       ResultSet resultSetDataInActive = null;
        ResultSet resultSetDataActive = null;
        ResultSet resultSetDoctorDetails = null;
        ResultSet rejectedInactive = null;
        ResultSet rejectedResultSetActive = null;

        String query = null;
        String InactiveQuery = null;
        String ActiveQuery = null;
        String DoctorDetailsQuery = null;
        String rejectedQuery = null;
        ArrayList resultlist = new ArrayList();
        ArrayList inner1	= new ArrayList();
        ArrayList inner2 = new ArrayList();
        ArrayList inner3	= new ArrayList();
        ArrayList inner4	= new ArrayList();
        ArrayList inner5	= new ArrayList();


      /*  try {

            con = DBConnection.getConnection(); 
            query = "select distinct a.person_id ,a.person_code,a.surname + space(2) + a.first_name as name,DATEDIFF(year,a.Date_of_Birth,GETDATE()),"
                    + " r.reassessmenttext from tblperson_personal_details a  with (nolock) "
                    // + " join tblPerson_Disability_Details b on(a.person_code=b.person_code) and r.person_code=b.person_code"
                    + " join ReassessmentDetails r with (nolock) on(a.person_code=r.person_code )" + " "
                    + "where a.district_id=? and a.camp_id= ? order by a.person_code";

            
            	ArrayList inner	= new ArrayList(); 
            
            ArrayList resultList = new ArrayList();
            ArrayList resultList1 = new ArrayList();
            ArrayList resultList2 = new ArrayList();
            ArrayList resultList3 = new ArrayList();
            ArrayList resultList4 = new ArrayList();
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();

                	    tempList.add("S");
   			    		tempList.add(district_id);
   			    		paramList.add(tempList);
   			    		
   			    	   tempList = new ArrayList();
   			    	    tempList.add("S");
			    		tempList.add(camp_id);
			    		paramList.add(tempList);
   			    		
    		  	
			    		//resultlist=(ArrayList)DataAccess.pickDataByPrepareStmt(query, paramList, false, false);     
            
   			    	    if (resultlist != null && resultlist.size()>0) {
   			    	    	
   			                for(int i=0;i<resultlist.size();i++) {
   			                	
   			                	inner = (ArrayList) resultlist.get(i);
   			                	
   			                 disabilityData = new HashMap();

   		                    disabilityData.put("pensionCard", (String)inner.get(0));
   		                    disabilityData.put("personCode", (String)inner.get(1));
   		                    disabilityData.put("Name", (String)inner.get(2));
   		                    disabilityData.put("age", (String)inner.get(3));
   		                    disabilityData.put("reAssessmentText", (String)inner.get(4));
   		                    
   		              
   		                 pstmt = con.prepareStatement(query);
   		                 
   		              pstmt.setString(1, district_id);
   		            pstmt.setString(2, camp_id);
   		          

    			            rs = pstmt.executeQuery();
 
    			            if (rs != null) {

    			                while (rs.next()) {
    			                    disabilityData = new HashMap();

    			                    disabilityData.put("pensionCard", rs.getString(1));
    			                    disabilityData.put("personCode", rs.getString(2));

    			                    disabilityData.put("Name", rs.getString(3));

    			                    disabilityData.put("age", rs.getString(4));

    			                    //   disabilityData.put("disability", rs.getString(4));

    			                    disabilityData.put("reAssessmentText", rs.getString(5));      
   		                    
   		                //    System.out.println("))))))>>"+rs.getString(1)+"-"+rs.getString(2)+"-"+rs.getString(3)+"-"+rs.getString(4)+"-"+rs.getString(5));
   		                    
   		                 InactiveQuery = "select t.totaldisability,"
   	                            + "case when b.disability_id=1 "
   	                            + " then 'Locomotor/OH'when b.disability_id=2 then 'Visual Impairment'when b.disability_id=3"
   	                            + " then 'Hearing Impairment'when b.disability_id=4 then 'Mental Retardation'when b.disability_id=5"
   	                            + " then 'Mental Illness'when b.disability_id=6 "
   	                            + " then 'Multiple Disability'else 'NA' end as disability "
   	                            + " from dbo.tblPerson_Disability_TotalValue_Details t with (nolock)"
   	                            + "join tblPerson_Disability_Details b with (nolock) on(t.person_code=b.person_code)"
   	                            + "and t.person_code=? and t.status='Inactive' and b.status='Inactive'";    
   		                    

   		              tempList = new ArrayList();
   		              paramList = new ArrayList();
                  	        tempList.add("S");
     			    		tempList.add((String)inner.get(1));
     			    		paramList.add(tempList);
     			    		

      		  	
     			    		resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(InactiveQuery, paramList, false, false);       

     			    	   if (resultList != null && resultList.size()>0) {
      			    	    	
      			                for(int j=0;j<resultList.size();j++) {
      			                	
      			                	inner1 = (ArrayList) resultList.get(j);
      			                	
      			                  disabilityData.put("totalDisabilityInActive",(String) inner1.get(0));
      	                        disabilityData.put("disability", (String)inner1.get(1)); 	
      			                	
      			                }
      			                
     			    	   }
     			    	   else
     			    	   {
     			    		     rejectedQuery = "select "
     	                                + "case when disability_id=1 "
     	                                + " then 'Locomotor/OH'when disability_id=2 then 'Visual Impairment'when disability_id=3"
     	                                + " then 'Hearing Impairment'when disability_id=4 then 'Mental Retardation'when disability_id=5"
     	                                + " then 'Mental Illness'when disability_id=6 "
     	                                + " then 'Multiple Disability'else 'NA' end as disability "
     	                                + " from dbo.tblRejectPerson_Details with (nolock) where person_code =?";
     			    		     
     			    		     
     			    		     tempList = new ArrayList();
     		   		              paramList = new ArrayList();
     		                  	        tempList.add("S");
     		     			    		tempList.add((String)inner.get(1));
     		     			    		paramList.add(tempList);
     		     			    		

     		      		  	
     		     			    		resultList1=(ArrayList)DataAccess.pickDataByPrepareStmt(rejectedQuery, paramList, false, false);       
 
     		     			    		 if (resultList1 != null && resultList1.size()>0) {
     		      			    	    	
     		      			                for(int j=0;j<resultList1.size();j++) {
     		      			                	
     		      			                disabilityData.put("totalDisabilityInActive", "DR");
     	     	                            disabilityData.put("disability",(String) resultList1.get(0));
     		      			                	
     		      			                }
     		      			                }
     		     			  else {
     	                            		disabilityData.put("totalDisabilityInActive", "DR");
     	                        } 
     		     			    		 
     		     			    		 
     			    	   }

     			    	   
     	                    ActiveQuery = "select totaldisability, "
     	                            + " case when b.disability_id=1 "
     	                            + " then 'Locomotor/OH'when b.disability_id=2 then 'Visual Impairment'when b.disability_id=3"
     	                            + " then 'Hearing Impairment'when b.disability_id=4 then 'Mental Retardation'when b.disability_id=5"
     	                            + " then 'Mental Illness'when b.disability_id=6 "
     	                            + " then 'Multiple Disability'else 'NA' end as disability "
     	                            + "from dbo.tblPerson_Disability_TotalValue_Details t with (nolock) join "
     	                            + "tblPerson_Disability_Details b with (nolock) on(t.person_code=b.person_code) and t.person_code=?"
     	                            + "and t.status='Active' and b.status='Active'";

     	                   tempList = new ArrayList();
		   		              paramList = new ArrayList();
		   		              
		                  	        tempList.add("S");
		     			    		tempList.add((String)inner.get(1));
		     			    		paramList.add(tempList);
		     			    		

		      		  	
		     			    		resultList2=(ArrayList)DataAccess.pickDataByPrepareStmt(ActiveQuery, paramList, false, false);       
	
		     			    		
		     			    		 if (resultList2 != null && resultList2.size()>0) {
  		      			    	    	
  		      			                for(int j=0;j<resultList2.size();j++) {
  		      			            	inner3 = (ArrayList) resultList2.get(j);
  		      			            disabilityData.put("totaldisabilityActive", (String)inner3.get(0));
  	     	                        disabilityData.put("disability", (String)inner3.get(1));	
  		      			                }
  		      			                }
		     			    		 else
		     			    		 {
		     			    	
		     			    			 rejectedQuery = "select "
		      	                                + "case when disability_id=1 "
		      	                                + " then 'Locomotor/OH'when disability_id=2 then 'Visual Impairment'when disability_id=3"
		      	                                + " then 'Hearing Impairment'when disability_id=4 then 'Mental Retardation'when disability_id=5"
		      	                                + " then 'Mental Illness'when disability_id=6 "
		      	                                + " then 'Multiple Disability'else 'NA' end as disability "
		      	                                + " from dbo.tblRejectPerson_Details with (nolock)"
		      	                                + "where person_code =? and status ='Active'";
    			 
		     			    		    tempList = new ArrayList();
		  		   		              paramList = new ArrayList();
		  		   		              
		  		                  	        tempList.add("S");
		  		     			    		tempList.add((String)inner.get(1));
		  		     			    		paramList.add(tempList);
		  		     			    		

		  		      		  	
		  		     			    		resultList3=(ArrayList)DataAccess.pickDataByPrepareStmt(rejectedQuery, paramList, false, false);       
		  		     	
		  		     			    	 if (resultList3 != null && resultList3.size()>0) {
		  		      			    	    	
		  		      			                for(int j=0;j<resultList3.size();j++) {
		  		      			            	//inner4 = (ArrayList) resultList3.get(j);
		  		      			            disabilityData.put("totaldisabilityActive", "DR");
		     	                            disabilityData.put("disability",(String) resultList3.get(0));
		  		      			                }
		  		      			                }
		  		     			    	else {
		     	                            disabilityData.put("totaldisabilityActive", "DR");
		     	                        }
		  		     			    		
		     			    			 
		     			    		 }

		     	                    DoctorDetailsQuery = "select ISNULL(First_Doctor_Name,'-'),ISNULL(First_Doctor_RegNumber,'-'),ISNULL(First_Doctor_Designation,'-') from dbo.tblPerson_Disability_Details with (nolock) where  person_code=? and status ='Active'";
		     	          


	     			    		    tempList = new ArrayList();
	  		   		              paramList = new ArrayList();
	  		   		              
	  		                  	        tempList.add("S");
	  		     			    		tempList.add((String)inner.get(1));
	  		     			    		paramList.add(tempList);
	  		     			    		

	  		      		  	
	  		     			    		resultList4=(ArrayList)DataAccess.pickDataByPrepareStmt(DoctorDetailsQuery, paramList, false, false);       
	  		     			  
	  		     			    	 if (resultList4 != null && resultList4.size()>0) {
	  		      			    	    	
	  		      			                for(int j=0;j<resultList4.size();j++) {
	  		      			            	inner5 = (ArrayList) resultList4.get(j);
	  		      			            	
	  		      			            disabilityData.put("firstDoctorName", (String)inner5.get(0));
		     	                        disabilityData.put("firstDoctorRegNumber", (String)inner5.get(1));
		     	                        disabilityData.put("firstDoctorDesignation", (String)inner5.get(2));        
		     	                        }
	  		      			                }
	  		     			    
		     	
		     	                    assesmentList.add(disabilityData);    		

     	              
	
     			    		

   		                 
   			                }
   			            }       */
            
		    	 try {

   			            con = DBConnection.getConnection(); 
   			            query = "select distinct a.person_id ,a.person_code,a.surname + space(2) + a.first_name as name,DATEDIFF(year,a.Date_of_Birth,GETDATE()),"
   			                    + " r.reassessmenttext from tblperson_personal_details a  with (nolock) "
   			                    // + " join tblPerson_Disability_Details b on(a.person_code=b.person_code) and r.person_code=b.person_code"
   			                    + " join ReassessmentDetails r with (nolock) on(a.person_code=r.person_code )" + " "
   			                    + "where a.district_id=? and a.camp_id= ? order by a.person_code";


   			            pstmt = con.prepareStatement(query);

   			         
     		              pstmt.setString(1, district_id);
     		            pstmt.setString(2, camp_id);
     		           
   			            
   			            
   			            rs = pstmt.executeQuery();

   			            if (rs != null) {

   			                while (rs.next()) {
   			                    disabilityData = new HashMap();

   			                    disabilityData.put("pensionCard", rs.getString(1));
   			                    disabilityData.put("personCode", rs.getString(2));

   			                    disabilityData.put("Name", rs.getString(3));

   			                    disabilityData.put("age", rs.getString(4));

   			                    //   disabilityData.put("disability", rs.getString(4));

   			                    disabilityData.put("reAssessmentText", rs.getString(5));
   			                    InactiveQuery = "select t.totaldisability,"
   			                            + "case when b.disability_id=1 "
   			                            + " then 'Locomotor/OH'when b.disability_id=2 then 'Visual Impairment'when b.disability_id=3"
   			                            + " then 'Hearing Impairment'when b.disability_id=4 then 'Mental Retardation'when b.disability_id=5"
   			                            + " then 'Mental Illness'when b.disability_id=6 "
   			                            + " then 'Multiple Disability'else 'NA' end as disability "
   			                            + " from dbo.tblPerson_Disability_TotalValue_Details t with (nolock)"
   			                            + "join tblPerson_Disability_Details b with (nolock) on(t.person_code=b.person_code)"
   			                            + "and t.person_code=? and t.status='Inactive' and b.status='Inactive'";


   			                    PstmtInActive = con.prepareStatement(InactiveQuery);
   			                    
   			                 
   			                 PstmtInActive.setString(1, rs.getString(2));
   		   		          
   		   		          
   			                    

   			                    resultSetDataInActive = PstmtInActive.executeQuery();

   			                    if (resultSetDataInActive.next()) {

   			                        disabilityData.put("totalDisabilityInActive", resultSetDataInActive.getString(1));
   			                        disabilityData.put("disability", rs.getString(2));

   			                    } else {
   			                        //rejected que4y
   			                        rejectedQuery = "select "
   			                                + "case when disability_id=1 "
   			                                + " then 'Locomotor/OH'when disability_id=2 then 'Visual Impairment'when disability_id=3"
   			                                + " then 'Hearing Impairment'when disability_id=4 then 'Mental Retardation'when disability_id=5"
   			                                + " then 'Mental Illness'when disability_id=6 "
   			                                + " then 'Multiple Disability'else 'NA' end as disability "
   			                                + " from dbo.tblRejectPerson_Details with (nolock) where person_code =?";
   			                        
   			                        rejectedPsInactive = con.prepareStatement(rejectedQuery);
   			                     rejectedPsInactive.setString(1, rs.getString(2));
   			                        
   			                        
   			                        rejectedInactive = rejectedPsInactive.executeQuery();

   			                        if (rejectedInactive.next()) {
   			                            disabilityData.put("totalDisabilityInActive", "DR");
   			                            disabilityData.put("disability", rs.getString(1));


   			                        } else {
   			                            disabilityData.put("totalDisabilityInActive", "DR");
   			                        }
   			                    }

   			                    ActiveQuery = "select totaldisability, "
   			                            + " case when b.disability_id=1 "
   			                            + " then 'Locomotor/OH'when b.disability_id=2 then 'Visual Impairment'when b.disability_id=3"
   			                            + " then 'Hearing Impairment'when b.disability_id=4 then 'Mental Retardation'when b.disability_id=5"
   			                            + " then 'Mental Illness'when b.disability_id=6 "
   			                            + " then 'Multiple Disability'else 'NA' end as disability "
   			                            + "from dbo.tblPerson_Disability_TotalValue_Details t with (nolock) join "
   			                            + "tblPerson_Disability_Details b with (nolock) on(t.person_code=b.person_code) and t.person_code=?"
   			                            + "and t.status='Active' and b.status='Active'";



   			                    PstmtActive = con.prepareStatement(ActiveQuery);
   			                    
   			                 PstmtActive.setString(1, rs.getString(2));
   			                 
   			                    resultSetDataActive = PstmtActive.executeQuery();

   			                    if (resultSetDataActive.next()) {

   			                        disabilityData.put("totaldisabilityActive", resultSetDataActive.getString(1));
   			                        disabilityData.put("disability", resultSetDataActive.getString(2));

   			                    } else {
   			                        //rejected que4y
   			                        rejectedQuery = "select "
   			                                + "case when disability_id=1 "
   			                                + " then 'Locomotor/OH'when disability_id=2 then 'Visual Impairment'when disability_id=3"
   			                                + " then 'Hearing Impairment'when disability_id=4 then 'Mental Retardation'when disability_id=5"
   			                                + " then 'Mental Illness'when disability_id=6 "
   			                                + " then 'Multiple Disability'else 'NA' end as disability "
   			                                + " from dbo.tblRejectPerson_Details with (nolock)"
   			                                + "where person_code =? and status ='Active'";

   			                        rejectedActive = con.prepareStatement(rejectedQuery);
   			                        
   			                     rejectedActive.setString(1, rs.getString(2)); 
   			                        
   			                        rejectedResultSetActive = rejectedActive.executeQuery();
   			                        if (rejectedResultSetActive.next()) {
   			                            disabilityData.put("totaldisabilityActive", "DR");
   			                            disabilityData.put("disability", rejectedResultSetActive.getString(1));
   			                        } else {
   			                            disabilityData.put("totaldisabilityActive", "DR");
   			                        }
   			                    }

   			                    DoctorDetailsQuery = "select First_Doctor_Name,First_Doctor_RegNumber,First_Doctor_Designation from dbo.tblPerson_Disability_Details with (nolock) where  person_code=? and status ='Active'";
   			                    PstmtDoctorDetails = con.prepareStatement(DoctorDetailsQuery);
   			                    
   			                 PstmtDoctorDetails.setString(1, rs.getString(2)); 
   			                    
   			                    resultSetDoctorDetails = PstmtDoctorDetails.executeQuery();

   			                    while (resultSetDoctorDetails.next()) {
   			                        disabilityData.put("firstDoctorName", resultSetDoctorDetails.getString(1));
   			                        disabilityData.put("firstDoctorRegNumber", resultSetDoctorDetails.getString(2));
   			                        disabilityData.put("firstDoctorDesignation", resultSetDoctorDetails.getString(3));

   			                    }
   			                    assesmentList.add(disabilityData);
   			                }


   			            }

        } catch (SQLException sqlEx) {
        	sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getReAssessmentDetails", "ReAssessmentCampWiseReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReAssessmentCampWiseReportDAO", "getReAssessmentDetails");
        } catch (Exception sqlEx) {
        	sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getReAssessmentDetails", "ReAssessmentCampWiseReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReAssessmentCampWiseReportDAO", "getReAssessmentDetails");
        } finally {
          DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeResultSet(resultSetDataInActive);
            DBConnection.closeResultSet(resultSetDataActive);
            DBConnection.closeResultSet(resultSetDoctorDetails);
            DBConnection.closeResultSet(rejectedInactive);
            DBConnection.closeResultSet(rejectedResultSetActive);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(PstmtInActive);
            DBConnection.closeStatement(PstmtDoctorDetails);
            DBConnection.closeStatement(rejectedPsInactive);
            DBConnection.closeStatement(rejectedActive);
            DBConnection.closeStatement(PstmtActive);


        }
        return assesmentList;
    }
}
