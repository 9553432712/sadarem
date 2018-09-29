/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.CampDailyReportDTO;
import org.bf.disability.dto.CampDetailsDTO;
import org.bf.disability.form.CampDailyReportForm;
import org.bf.disability.form.CampDetailsForm;

import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author SADAREM
 */
public class CampDailyReportDAO {

    public ArrayList getCampBasedOnLoginDetails(DataSource ds, String districtId) throws SADAREMDBException, SQLException {

        ArrayList campList = new ArrayList();
        ArrayList CampList = new ArrayList();
     //   Connection con = null;
     //   PreparedStatement pstmt = null;
     //   ResultSet rs = null;
        String query = null;
        CampDailyReportDTO campDailyReportDTO = null;
        try {

        //    con = DBConnection.getConnection();
            query = "select camp_id,VenueName,address,name from tblCamp_Details where district_id=? AND upper(Status)='ACTIVE' order by name";

         //   pstmt = con.prepareStatement(query);

            
            ArrayList inner	= new ArrayList(); 
            
            ArrayList resultList = new ArrayList();
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();

                	   tempList.add("S");
   			    		tempList.add(districtId);
   			    		paramList.add(tempList);
   			    		
        		
    		  //	log.info("getWithoutProofPartaList lStrQuery : "+query);
    		  	
   			    		campList=(ArrayList)DataAccess.pickDataByPrepareStmt(query, paramList, false, false);     
            
            
   			    	    if (campList != null && campList.size()>0) {
   			    	    	
   			                for(int i=0;i<campList.size();i++) {
   			                	
   			                	inner = (ArrayList) campList.get(i);
   			                	
   			                    campDailyReportDTO = new CampDailyReportDTO();
   			                    campDailyReportDTO.setCampId((String)inner.get(0));
   			                    campDailyReportDTO.setCampName((String)inner.get(3) + " ("+inner.get(1)+"), " + inner.get(2));

   			                 CampList.add(campDailyReportDTO);
   			                }
   			            }    
           
   			    		
   			    		
            
      /*      rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    campDailyReportDTO = new CampDailyReportDTO();
                    campDailyReportDTO.setCampId(rs.getString(1));
                    campDailyReportDTO.setCampName(rs.getString(4) + " ("+rs.getString(2)+"), " + rs.getString(3));

                    campList.add(campDailyReportDTO);
                }
            }*/
            
            
            
            
            
            
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampBasedOnLoginDetails", "CampDailyReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "getCampBasedOnLoginDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampBasedOnLoginDetails", "CampDailyReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "getCampBasedOnLoginDetails");
        } finally {
          //  DBConnection.closeConnection(con);
         //   DBConnection.closeResultSet(rs);
          //  DBConnection.closeStatement(pstmt);

        }
        return CampList;
    }

    public ArrayList getCampDailyReportDetails(DataSource ds, String district_id) throws SADAREMDBException, SQLException {

        ArrayList campList = new ArrayList();
        ArrayList campDataList = new ArrayList();
        ArrayList campDataListt = new ArrayList();
      //  Connection con = null;
      //  PreparedStatement pstmt = null;
        String query = null;
    //    ResultSet rs = null;
        CampDailyReportDTO campDailyReportDTO = null;
        Map<String, Object> campDailyDetails = null;
        int i = 1;

        try {
         //   con = DBConnection.getConnection();

            query = "select camp_id,name,address,venuename from tblCamp_Details where district_id =?";

          //  pstmt = con.prepareStatement(query);

            
            ArrayList inner	= new ArrayList(); 
            
            ArrayList resultList = new ArrayList();
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();

                	   tempList.add("S");
   			    		tempList.add(district_id);
   			    		paramList.add(tempList);
   			    		
        		
    		  //	log.info("getWithoutProofPartaList lStrQuery : "+query);
    		  	
   			    		campDataList=(ArrayList)DataAccess.pickDataByPrepareStmt(query, paramList, false, false);     
            
            
   			    	    if (campDataList != null && campDataList.size()>0) {
   			    	    	
   			                for(int j=0;j<campDataList.size();j++) {
   			                	
   			                	inner = (ArrayList) campDataList.get(j);	
   			                 campDailyDetails = new HashMap<String, Object>();
   			                 
   		                    campDailyDetails.put("sno", i);
   		                    campDailyDetails.put("camp_id", (String)inner.get(0));
   		                    campDailyDetails.put("name", (String)inner.get(1));
   		                    campDailyDetails.put("address", (String)inner.get(2));
   		                    campDailyDetails.put("venuename", (String)inner.get(3));
   		                    
   		                 campDataListt.add(campDailyDetails);
   		                    i++;
   			                }
   			            }    
            
            
            
/*            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    campDailyDetails = new HashMap<String, Object>();
                    campDailyDetails.put("sno", i);
                    campDailyDetails.put("camp_id", rs.getString(1));
                    campDailyDetails.put("name", rs.getString(2));
                    campDailyDetails.put("address", rs.getString(3));
                    campDailyDetails.put("venuename", rs.getString(4));
                    campDataList.add(campDailyDetails);
                    i++;
                }
            }*/


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCampDailyReportDetails", "CampDailyReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "getCampDailyReportDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCampDailyReportDetails", "CampDailyReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "getCampDailyReportDetails");
        } finally {
         //  DBConnection.closeConnection(con);
         //  DBConnection.closeResultSet(rs);
         //  DBConnection.closeStatement(pstmt);

        }

        return campDataListt;

    }

    public ArrayList getDisabilityDetails(DataSource ds) throws SADAREMDBException, SQLException {

        ArrayList disabilityList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        //  FunctionalNeedReportDTO functionalNeedReportDTO = null;
        CampDailyReportDTO campDailyReportDTO = null;

        try {
            con = DBConnection.getConnection();

            query = "select disability_id,disability_name from dbo.tblDisability_Details";

            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    campDailyReportDTO = new CampDailyReportDTO();
                    campDailyReportDTO.setDisabiltyId(rs.getString(1));
                    campDailyReportDTO.setDisabilityName(rs.getString(2));
                    disabilityList.add(campDailyReportDTO);
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDisabilityDetails", "CampDailyReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "getDisabilityDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDisabilityDetails", "CampDailyReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "getDisabilityDetails");
        }finally {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(pstmt);

        }

        return disabilityList;
    }

    /**
     * This method is for getting the disability list
     * @param ds
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public ArrayList getDisabilityList(DataSource ds) throws SADAREMDBException, SQLException {

        ArrayList disabilityList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        //  FunctionalNeedReportDTO functionalNeedReportDTO = null;
        Map map = null;

        try {
            con = DBConnection.getConnection();

            query = "select disability_id,disability_name from dbo.tblDisability_Details where disability_id not in(6)";

            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("disability_id", rs.getString(1));
                    map.put("disability_name", rs.getString(2));
                    disabilityList.add(map);
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityDetails", "CampDailyReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "getDisabilityDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityDetails", "CampDailyReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "getDisabilityDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }

        return disabilityList;
    }
    public synchronized int insertPwdAssessementDetails(DataSource ds, CampDailyReportForm campDailyReportForm, String districtid, String loginID, String systemIP) throws SADAREMDBException, SQLException {

        int i = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;

        try {

            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(campDailyReportForm.getCampdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            con = DBConnection.getConnection();

            query = "INSERT INTO tblDailycampwisepwdAssessedDetails(District_ID,Camp_ID,Assesseddate,Disability_ID,"
                    + "PwdAttended,PwdAssessed,DoctorsCount,LoginID,SystemIP,CreatedDate,UpdatedDate,"
                    + "Status) VALUES (?,?,?,?,?,?,?,?,?,GetDate(),GetDate(),'Active')";

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, districtid);
            pstmt.setString(2, campDailyReportForm.getCampData());
            pstmt.setString(3, fromdate);
            pstmt.setString(4, campDailyReportForm.getDisabiltyId());
            pstmt.setString(5, campDailyReportForm.getPwdAttended());
            pstmt.setString(6, campDailyReportForm.getPwdAssessed());
            pstmt.setString(7, campDailyReportForm.getDoctorsCount());
            pstmt.setString(8, loginID);
            pstmt.setString(9, systemIP);

            i = pstmt.executeUpdate();


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertPwdAssessementDetails", "CampDailyReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "insertPwdAssessementDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertPwdAssessementDetails", "CampDailyReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "insertPwdAssessementDetails");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeStatement(pstmt);

        }


        return i;
    }

    public int getCampDetailsDateWise(DataSource ds, CampDetailsForm sueRaiseApprovalForm, String districtid, String loginID, String systemIP) throws SADAREMDBException, SQLException {
        int i = 0;
      //  Connection con = null;
     //   PreparedStatement pstmt = null;
        String query = null;
     //   ResultSet rs = null;

        try {

            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(sueRaiseApprovalForm.getCampDate());
            String campDate = new SimpleDateFormat("dd/mm/yyyy").format(fdate);
         //   con = DBConnection.getConnection();
            query = "select count(*) from CampDetailsDateWise where convert(varchar,campdate,103)=? and campid=?";

        //    pstmt = con.prepareStatement(query);

       /*     rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    i = rs.getInt(1);
                }
            }*/
       
            ArrayList inner	= new ArrayList(); 
            ArrayList result = new ArrayList();
            ArrayList resultList = new ArrayList();
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();

                	   tempList.add("S");
   			    		tempList.add(campDate);
   			    		paramList.add(tempList);
   			    		
   			    		tempList = new ArrayList();
   			    	    tempList.add("S");
			    		tempList.add(sueRaiseApprovalForm.getCampId());
			    		paramList.add(tempList);
			    		
			    		
    		  //	log.info("getWithoutProofPartaList lStrQuery : "+query);
    		  	
			    		result=(ArrayList)DataAccess.pickDataByPrepareStmt(query, paramList, false, false);     
            
           // System.out.println("result-->"+result);
   			    	    if (result != null && result.size()>0) {
   			    	    	
   			                for(int k=0;k<result.size();k++) {
   			                	
   			                	//inner = (ArrayList) result.get(k);
   			                	
   			                 i =Integer.parseInt((String)result.get(0));
   			                }
   			            }    
   			    	    
   			    	    

        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDetailsDateWise", "CampDailyReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "getCampDetailsDateWise");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDetailsDateWise", "CampDailyReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "getCampDetailsDateWise");
        } finally {
         //   DBConnection.closeConnection(con);
         //   DBConnection.closeStatement(pstmt);

        }


        return i;
    }

    //kavya
    public int insertCampDetailsDateWise(DataSource ds, CampDetailsForm sueRaiseApprovalForm, String districtid, String loginID, String systemIP) throws SADAREMDBException, SQLException {
        int i = 0;
        Connection con = null;

        PreparedStatement pstmt = null;
        String query = null;

        try {

            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(sueRaiseApprovalForm.getCampDate());
            String campDate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            con = DBConnection.getConnection();
            
            StringBuffer str= new StringBuffer();
            

            String[] arr11=((String)sueRaiseApprovalForm.getDisabilityCount()).split(",");
            
            for(int j=0;j<arr11.length;j++)
            {
            	str.append("?,");
            }

             query = "insert into CampDetailsDateWise(campId,districtId,campDate,login,systemIp,"+sueRaiseApprovalForm.getColumns()+""+sueRaiseApprovalForm.getColumnsPending()+" createdDate,updatedDate,status) \n"
            		 + "values(?,?,?,?,?,"+str.toString()+""+str.toString()+"\n"
                     + "getDate(),getDate(),'Active');";
            
           // System.out.println("insertttttttttt"+query);
            
            pstmt = con.prepareStatement(query);
                        
            pstmt.setString(1, sueRaiseApprovalForm.getCampId());
            pstmt.setString(2, districtid);
            pstmt.setString(3, campDate);
            pstmt.setString(4, loginID);
            pstmt.setString(5, systemIP);
           // pstmt.setString(6, str.toString()+""+str.toString());
            
            int k=6;
            for(int y=0;y<arr11.length;y++,k++)
            {           
            pstmt.setString(k, arr11[y]);
            }
            
            for(int h=0;h<arr11.length;h++,k++)
            {           
            pstmt.setString(k, arr11[h]);
            }
            i = pstmt.executeUpdate();
            


        } catch (SQLException sqlEx) 
        {
        	sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertCampDetailsDateWise", "CampDailyReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "insertCampDetailsDateWise");
        } catch (Exception sqlEx) {
        	sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertCampDetailsDateWise", "CampDailyReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "insertCampDetailsDateWise");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);

        }


        return i;
    }

    public ArrayList getCampReportBasedOnMonthYearDistrict(DataSource ds, CampDetailsDTO campDetailsDTO, String flag) throws SADAREMDBException, SQLException {
        ArrayList campReportList = new ArrayList();
        ArrayList ReportList = new ArrayList();
          		String query="";
        Map m = null;
        try {
         //  // con = DBConnection.getConnection();

            query=
					"SELECT convert(varchar, campDate, 103) campDate,\n" +
					"       d.campId,\n" + 
					"       ISNULL(c.Name,'-') campName,\n" + 
					"       ISNULL(c.VenueName,'-') venue,\n" + 
					"       ISNULL(c.Address,'No details found') address,\n" + 
					"       b.District_Name,\n" + 
					"       d.districtId\n" + 
					"  FROM CampDetailsDateWise d , tblCamp_Details c ,tblDistrict_Details b\n" + 
					"   where \n" + 
					"   d.districtId = c.District_ID AND\n" + 
					"   d.districtId = b.District_ID AND\n" + 
					"   d.campId = c.Camp_ID AND \n" ;
            if (flag != null) 
            {
                if (flag.equalsIgnoreCase("S")) 
                {
                    query+=" Convert(DateTime, Convert(VarChar, campdate, 101)) >= Convert(DateTime, Convert(VarChar, GetDate(), 101)) ";

                } 
                else 
                {
                  //  query+=" datepart(yyyy,d.campDate) ='" + campDetailsDTO.getYear()+ "' and datepart(mm,d.campDate) = '"+ campDetailsDTO.getMonth()+ "'";
                    query+=" datepart(yyyy,d.campDate) =? and datepart(mm,d.campDate) = ?";
                }
            }
            
            if (!campDetailsDTO.getDistrict_id().equals("0")) 
            {
              //  query+=" and d.districtId='" + campDetailsDTO.getDistrict_id()+"'";
                
                query+=" and d.districtId=?";

            }
            query+=" order by d.campDate ";
            
        //    System.out.println("query : "+query.toString());
            
            
         //   pstmt = con.prepareStatement(query.toString());
            
            
            ArrayList resultList = new ArrayList();
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		//try
    		//{
    		  if (flag != null) 
              {
                  if (!flag.equalsIgnoreCase("S")) 
                  {
                	   tempList.add("S");
   			    		tempList.add(campDetailsDTO.getYear());
   			    		paramList.add(tempList);
   			    		
   			    		
   			    		tempList = new ArrayList();
			    		tempList.add("S");
			    		tempList.add(campDetailsDTO.getMonth());
			    		paramList.add(tempList);
                	  
                  }
                  
               }
    		  if (!campDetailsDTO.getDistrict_id().equals("0")) 
              {
    			  tempList = new ArrayList();
		    		tempList.add("S");
		    		tempList.add(campDetailsDTO.getDistrict_id());
		    		paramList.add(tempList);
              }
        		         
    			    		
    			    		
    			    		
    			    		
        		
    		  //	log.info("getWithoutProofPartaList lStrQuery : "+query);
    		  	
    			    		campReportList=(ArrayList)DataAccess.pickDataByPrepareStmt(query, paramList, false, false);
        		
    			    	//	System.out.println("campReportList--->"+campReportList);
    			    		
        		paramList = null;
        		tempList  = null;
            ArrayList inner = new ArrayList();
            
        		   if (campReportList != null && campReportList.size()>0) {
                       for(int i=0;i<campReportList.size();i++){
                    	   
                    	   inner = (ArrayList) campReportList.get(i);
                    	   
                           m = new HashMap();
                           m.put("campDate", inner.get(0) );
                           if (inner.get(2) != null) {
                               m.put("campName", inner.get(2)+"");
                           } else {
                               m.put("campName", "-");
                           }
                              m.put("venue",  inner.get(3));
                           if (inner.get(4) != null) {
                               m.put("address",  inner.get(4)+ ".");
                           } else {
                               m.put("address", "No details found");
                           }
                           ReportList.add(m);
                           
                           
                       }
                   }     
         //   System.out.println("ReportList-------------->"+ReportList);
            
            
            
            
     /*       rs = pstmt.executeQuery();
            

            if (rs != null) {
                while (rs.next()) {
                    m = new HashMap();
                    m.put("campDate", rs.getString(1) );
                    if (rs.getString(3) != null) {
                        m.put("campName", rs.getString(3));
                    } else {
                        m.put("campName", "-");
                    }
                       m.put("venue",  rs.getString(4));
                    if (rs.getString(5) != null) {
                        m.put("address",  rs.getString(5)+ ".");
                    } else {
                        m.put("address", "No details found");
                    }
                    campReportList.add(m);
                }
            }*/

        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampReportBasedOnMonthYearDistrict", "CampDailyReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "getCampReportBasedOnMonthYearDistrict");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampReportBasedOnMonthYearDistrict", "CampDailyReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "getCampReportBasedOnMonthYearDistrict");
        } finally {
          //  DBConnection.closeConnection(con);
          //  DBConnection.closeResultSet(rs);
          //  DBConnection.closeStatement(pstmt);

        }

        return ReportList;
    }

    public ArrayList getCampDisabilityWiseReport(DataSource ds,
            CampDetailsDTO campDetailsDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList campReport = new ArrayList();
        HashMap m = null;
        try {

            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call CAMPLEVELABSTRACT(?,?,?,?)}");

            cstmt.setString(1, campDetailsDTO.getMonth());
            cstmt.setInt(2, campDetailsDTO.getYear());
            cstmt.setString(3, campDetailsDTO.getDistrict_id());
            cstmt.setString(4, campDetailsDTO.getCampId());
            rs = cstmt.executeQuery();
            while (rs.next()) {
                m = new HashMap();
                m.put("date", rs.getString(1));
                m.put("partA", rs.getString(2));
                m.put("TAP", rs.getString(3));
                m.put("PI", rs.getString(4));
                m.put("VI", rs.getString(5));
                m.put("HI", rs.getString(6));
                m.put("MR", rs.getString(7));
                m.put("MI", rs.getString(8));
                m.put("MD", rs.getString(9));
                m.put("total",rs.getString(10));
                m.put("DR", rs.getString(11));
                m.put("AR", rs.getString(12));
                m.put("TR", rs.getString(13));
                campReport.add(m);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDisabilityWiseReport", "CampDailyReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "getCampDisabilityWiseReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDisabilityWiseReport", "CampDailyReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "getCampDisabilityWiseReport");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);
            DBConnection.closeConnection(con);
        }
        return campReport;
    }
     public String getCampName(DataSource ds, String campId) throws SADAREMDBException, SQLException {

        String campName="";

        String query = null;
        try {

            query = "select VenueName,address from tblCamp_Details where camp_id=? order by name";

          ///  
            
            ArrayList inner	= new ArrayList(); 
            
            ArrayList resultList = new ArrayList();
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();

                	   tempList.add("S");
   			    		tempList.add(campId);
   			    		paramList.add(tempList);

    		  	
   			    		resultList=(ArrayList)DataAccess.pickDataByPrepareStmt(query, paramList, false, false);     
            
          
            
   			    	    if (resultList != null && resultList.size()>0) {
   			    	    	
   			                for(int i=0;i<resultList.size();i++) {
   			                	
   			                	inner = (ArrayList) resultList.get(i);
   			                	
   			                 campName=inner.get(0) + ", " + inner.get(1);
   			             
   			                }
   			            }   
          

        } catch (SQLException sqlEx) {
        	sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampName", "CampDailyReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "getCampName");
        } catch (Exception sqlEx) {
        	sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampName", "CampDailyReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "getCampName");
        } finally {
          //  DBConnection.closeConnection(con);
          //  DBConnection.closeResultSet(rs);
          //  DBConnection.closeStatement(pstmt);

        }
        return campName;
    }

    /**
     * This method is for checking the assessment status by camp wise
     * @param campDetailsForm
     * @return
     * @throws SADAREMDBException
     */
    public String checkCampAssessmentStatus(DataSource ds, CampDetailsForm campDetailsForm) throws SADAREMDBException, SQLException {
        String checkStatus = null;
        Connection con = null;
        ResultSet rs = null;
        ResultSet personalRs = null;
        PreparedStatement pstmt = null;
        String previousDateQuery = null;
        String checkPersonalDetailsQuery = null;
        String previousDate = null;
        int cnt = 0;
        int personalCount = 0;
        int finalcount = 0;

        try {
            con = DBConnection.getConnection();
            

            // Getting the Max date from previous camp details
            previousDateQuery = "select max(convert(varchar,campdate,103)),coalesce(sum(hicount+vicount+hicount+mrcount+micount+mdcount),0) as cnt from CampDetailsDateWise "
                    + "where districtid=? and campid=? ";
            pstmt = con.prepareStatement(previousDateQuery);
            pstmt.setString(1, campDetailsForm.getDistrict_id());
            pstmt.setString(2, campDetailsForm.getCampId());
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    previousDate = rs.getString(1);
                    cnt = rs.getInt(2);
                }
            }
            // Getting the personal details count between two dates
            if (previousDate != null && previousDate.length() > 0) {

                checkPersonalDetailsQuery = "select count(*) from tblperson_personal_details  with (nolock) where districtid=? "
                        + "and camp_id=? and convert(varchar,created_date,103) between ? and convert(varchar,getDate(),103)";
                pstmt = con.prepareStatement(checkPersonalDetailsQuery);
                pstmt.setString(1, campDetailsForm.getDistrict_id());
                pstmt.setString(2, campDetailsForm.getCampId());
                pstmt.setString(3, previousDate);

                personalRs = pstmt.executeQuery();

                if (personalRs != null && personalRs.next()) {
                    personalCount = personalRs.getInt(1);
                }

                finalcount = cnt - personalCount; // Personal details table count - camp details table

            } else {
                checkStatus = "<font color=\"red\">Previous Camp Date Not Available.</font>";
            }

            if (finalcount != 0 || finalcount < 0) {
                checkStatus = "<font color=\"red\">Previous Camp Assessment is Not Completed.</font>";
            } else if (finalcount == 0) {
                checkStatus = "success";
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampName", "CampDailyReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "getCampName");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampName", "CampDailyReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportDAO", "getCampName");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeResultSet(personalRs);
            DBConnection.closeStatement(pstmt);
        }
        return checkStatus;

    }
}
