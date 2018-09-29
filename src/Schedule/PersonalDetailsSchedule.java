/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Schedule;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 567999
 */
public class PersonalDetailsSchedule implements Job
{
	static final private Logger log = Logger.getLogger(PersonalDetailsSchedule.class);
     
    public void execute(JobExecutionContext jec) throws JobExecutionException 
    {
       // System.out.println("11111111111111111111111");
    
        try {
        	  ArrayList DistrictList = new ArrayList();
           CommonDAO comObj = new CommonDAOImpl();
           
            DistrictList = comObj.getDistrictIDSList();
            
            if (DistrictList != null && DistrictList.size() > 0) 
            {
            	
               /* for (int i = 0; i < DistrictList.size(); i++) 
                {
                    insertPersonalDetails(DistrictList.get(i).toString());
                }*/
            }
//             insertPersonalDetails("14");
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        } 
        finally 
        {
           
        }
    }

   

    public int insertPersonalDetails(String distrcitId)
    {
        Connection con = null;
        ResultSet rs = null;
        CallableStatement cstmt = null;
        int i = 0;
        try
        {
           con =DBConnection.getConnection();

           	//(Person_Code,DoctorName,RegistrationNo,Designation,Railway_Certificate,Status,Insert_date) values(?,?,?,?,?,?,GETDATE()
            cstmt = con.prepareCall("{Call SP_tblPerson_Personal_Details_Insert_scheduler(?)}");
            cstmt.setString(1, distrcitId);
            i = cstmt.executeUpdate();
            if (i > 0) 
            {
                i = 1;
            }
        } 
        catch (SQLException sqle) 
        {
        	 log.info("SQLException in PersonalDetailsSchedule @ insertPersonalDetails :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
        	 sqle.printStackTrace();
        }
        catch (Exception lexp) 
        {
        	log.info("SQLException in PersonalDetailsSchedule @ insertPersonalDetails ::  "+lexp.getLocalizedMessage());
        	lexp.printStackTrace();
        } 
        finally
        {
        	DBConnection.closeConnection(con);
        	DBConnection.closeResultSet(rs);
        	DBConnection.closeStatement(cstmt);
        	 try { rs.close(); } catch(Exception e) {}
	         try { cstmt.close(); } catch(Exception e) {}
	         try { con.close(); } catch(Exception e) {}
        }

        return i;
    }
}

    
