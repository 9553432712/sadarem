/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.CertificateGenerationForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 728056
 */
public class CertificateGenerationDAO {

    public ArrayList getMandalList(DataSource ds, String districtid) throws SADAREMDBException, SQLException 
    {
        ArrayList MandalList = new ArrayList();
        Connection con = null; 
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        HashMap<Object, Object> map = null;
        try {
            con = DBConnection.getConnection();
            
            sql = "select Mandal_ID,Mandal_Name,District_ID from dbo.tblMandal_Details where District_ID=? order by Mandal_Name";

            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, districtid);
            
            rs = pstmt.executeQuery();

            if (rs != null) 
            {
                while (rs.next()) 
                {
                    map = new HashMap();
                    map.put("mandal", rs.getString(1));
                    map.put("mandalname", rs.getString(2));
                    MandalList.add(map);
                }
            }
        }
        catch (SQLException sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandalList", "CertificateGenerationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificateGenerationDAO", "getMandalList");
        } 
        catch (Exception sqlEx)
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandalList", "CertificateGenerationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificateGenerationDAO", "getMandalList");
        } 
        finally
        {
        	DBConnection.closeConnection(con);
        	DBConnection.closeResultSet(rs);
        	DBConnection.closeStatement(pstmt);
        }

        return MandalList;
    }

    public ArrayList getviiageNames(DataSource ds, CertificateGenerationForm cgform, String districtid) throws SADAREMDBException, SQLException
    {
        ArrayList VillageNames = new ArrayList();
        
        Connection con = null; 
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        HashMap<Object, Object> map = null;
        try {
            con = DBConnection.getConnection();
            
            sql = "select Village_ID,Village_Name,Mandal_ID from dbo.tblVillage_Details "
                    + "where District_ID=? AND Mandal_ID=? ORDER BY Village_Name";

            pstmt=con.prepareStatement(sql);
            pstmt.setString(1,districtid);
            pstmt.setString(2,cgform.getMandal());
            
            rs = pstmt.executeQuery(sql);

            if (rs != null) 
            {
                while (rs.next()) 
                {
                    map = new HashMap();
                    map.put("village", rs.getString(1));
                    map.put("villagename", rs.getString(2));
                    VillageNames.add(map);
                }
            }
        } catch (Exception e) {
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "getviiageNames", "CertificateGenerationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificateGenerationDAO", "getviiageNames");

        } finally 
        { 
        	DBConnection.closeConnection(con);
        	DBConnection.closeResultSet(rs);
        	DBConnection.closeStatement(pstmt);
        }

        return VillageNames;
    }

    public ArrayList getHabitationNames(DataSource ds, CertificateGenerationForm cgform, String districtid) throws SADAREMDBException, SQLException 
    {
        ArrayList HabitationNames = new ArrayList();
        Connection con = null; 
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        HashMap<Object, Object> map = null;
        try {
            con = DBConnection.getConnection(); 
            sql = "select Habitation_Id,Habitation_Name from dbo.tblHabitation_Details where district_id=? "
                    + " and Mandal_ID=? and Village_ID=? and status='Active' order by Habitation_Name";

            
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, districtid);
            pstmt.setString(2, cgform.getMandal());
            pstmt.setString(3, cgform.getVillage());
            
            
            rs = pstmt.executeQuery();
            
            if (rs != null) 
            {
                while (rs.next()) 
                {
                    map = new HashMap();
                    map.put("habitation", rs.getString(1));
                    map.put("habitationname", rs.getString(2));
                    HabitationNames.add(map);
                }
            }
        }
        catch (SQLException sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHabitationNames", "CertificateGenerationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificateGenerationDAO", "getHabitationNames");
        }
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHabitationNames", "CertificateGenerationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificateGenerationDAO", "getHabitationNames");
        } 
        finally
        {

        	DBConnection.closeConnection(con);
        	DBConnection.closeResultSet(rs);
        	DBConnection.closeStatement(pstmt);
        }

        return HabitationNames;
    }

    public ArrayList getPersonDetails(DataSource ds, CertificateGenerationForm cgform, String districtid) throws SADAREMDBException, SQLException {
        ArrayList Data = new ArrayList();
        String sql1 = "";
        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            sql = "select a.* from (select distinct a.Surname +' '+ a.First_Name as name,a.Age_Years as age, "
                    + "case when a.Education=1 then 'Illiterate' when a.Education=2 then 'Below 10th' "
                    + "when a.Education=3 then '10th Class'when a.Education=4 then 'Intermediate' when a.Education=5 then 'Diploma/ITI'"
                    + "when a.Education=6 then 'Graduate' when a.Education=7 then 'Post Graduate' else 'Illiterate' end as qly,case  "
                    + "when c.disability_id='1' then 'Locomotor/OH' when c.disability_id='2' then 'Visual Impairment' when c.disability_id='3' then 'Hearing Impairment' "
                    + "when c.disability_id='4' then 'Mental Retardation' when c.disability_id='5' then 'Mental Illness'"
                    + "when c.disability_id='6' then 'Multiple Disability' end as disability, cast(d.totaldisability as varchar) totaldisability,coalesce(a.Phone_No,'0') as mobile,"
                    + " a.relation_name,a.person_code,a.pensioncard_no,a.reasonforpersonstatus, case when a.categoryid=2 then 'Reassessed' when a.categoryid in(1,3) "
                    + "and a.view_edit_mode ='View' then 'Regular' when a.categoryid in(1,3) and a.view_edit_mode ='Edit' then 'partA Entered'Else '*****' "
                    + "end as categoryId,a.pensionphase from dbo.tblPerson_Personal_Details a   with (nolock) left join tblPerson_disability_details c on(a.person_code=c.person_code) left join dbo.tblPerson_Disability_TotalValue_Details d "
                    + "on(a.person_code=d.person_code and d.person_code=c.person_code) where a.status='Active' and c.status='Active' and d.status='Active'  "
                    + "and d.created_date =  (select max (created_date) from dbo.tblPerson_Disability_TotalValue_Details where person_code = a.person_code ) ~replaceStr "
                    + "union "
                    + "select distinct a.Surname +' '+ a.First_Name as name,a.Age_Years as age,case when a.Education=1 then 'Illiterate' when a.Education=2 then 'Below 10th'"
                    + "when a.Education=3 then '10th Class'when a.Education=4 then 'Intermediate' when a.Education=5 then 'Diploma/ITI'when a.Education=6 then 'Graduate'"
                    + "when a.Education=7 then 'Post Graduate' else 'Illiterate' end as qly,'NA' disability,'NA'  totaldisability,coalesce(a.Phone_No,'0') as mobile,"
                    + " a.relation_name,a.person_code,a.pensioncard_no,a.person_status reasonforpersonstatus,case when a.categoryid=2 then 'Reassessed' when a.categoryid in(1,3) "
                    + "and a.view_edit_mode ='View' then 'Regular' when a.categoryid in(1,3) and a.view_edit_mode ='Edit' then 'partA Entered' Else '*****' "
                    + "end as categoryId,a.pensionphase from dbo.tblPerson_Personal_Details a  with (nolock) join tblRejectPerson_Details r on(a.person_code=r.person_code) "
                    + "where a.status='Active'  and a.view_edit_mode='View' and r.status='Active' ~replaceStr)a where a.person_code not in ( select person_code from dbo.sadarem_Certificate_Issued ) order by person_code";



            if (districtid != null && !districtid.equalsIgnoreCase("0")) 
            {
                sql1 += " and a.district_Id='" + districtid + "' ";
            }
            
            if (cgform.getMandal() != null && !cgform.getMandal().equalsIgnoreCase("0")) 
            {
                sql1 += " and a.mandal_id='" + cgform.getMandal() + "' ";
            }
            
            if (cgform.getVillage() != null && !cgform.getVillage().equalsIgnoreCase("0")) 
            {
                sql1 += " and a.village_id='" + cgform.getVillage() + "' ";
            }
            
            if (cgform.getHabitation() != null && !cgform.getHabitation().equalsIgnoreCase("0"))
            {
                if (districtid.equals("16") && cgform.getMandal().equals("84") && cgform.getVillage().equals("004") && cgform.getHabitation().equals("01")) 
                {
                    sql1 += " and a.Habitation_ID in('00','" + cgform.getHabitation() + "') ";
                }
                else
                {
                    sql1 += " and a.Habitation_ID='" + cgform.getHabitation() + "' ";
                }
            }
            sql = sql.replaceAll("~replaceStr", sql1);

            rs = st.executeQuery(sql);


            if (rs != null) {
                while (rs.next()) {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("name", rs.getString(1));
                    map.put("age", rs.getString(2));
                    map.put("qly", rs.getString(3));
                    map.put("disability", rs.getString(4));
                    map.put("percentage", rs.getString(5));
                    map.put("mobile", rs.getString(6));
                    map.put("relation_name", rs.getString(7));
                    map.put("person_code", rs.getString(8));
                    map.put("pensioncard_no", rs.getString(9));
                    map.put("personStatus", rs.getString(10));
                    map.put("assesmentStatus", rs.getString(11));
                    map.put("pensionPhase", rs.getString(12));

                    Data.add(map);

                }
            }
        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonDetails", "CertificateGenerationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificateGenerationDAO", "getPersonDetails");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonDetails", "CertificateGenerationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificateGenerationDAO", "getPersonDetails");
        } finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return Data;
    }

    public boolean insertDetails(DataSource ds, CertificateGenerationForm cgform) throws SADAREMDBException, SQLException {
        boolean insert = false;
        Connection con = null; 
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        HashMap<Object, Object> map = null;
        try {
            con = DBConnection.getConnection();
            
            
            if (cgform.getCheckbox() != null && cgform.getCheckbox().length > 0) 
            {
                String[] sArray = cgform.getCheckbox();
                String[] sArray1 = cgform.getRemarks();
                String[] sArray2 = cgform.getFromdate();
                
                
            /*    sql = 
                    	" insert into dbo.sadarem_Certificate_Issued(Person_Code,PensionCard_No,remarks,date,createddate,updateddate,loginid,systemip,status,camp_id) \n"
                    	+ " values('" + sadaremId + "','" + sar[0] + "',"
                        + "'" + sArray1[i] + "','" + fromdate + "',getDate(),getDate(),'" + cgform.getLoginId() + "','" + InetAddress.getLocalHost().getHostAddress() + "','Active','" + campId + "')";
*/
                
                sql =  " INSERT INTO sadarem_Certificate_Issued(Person_Code,PensionCard_No,remarks,date,createddate,updateddate,loginid,systemip,status,camp_id) \n"
                    	+ " values(?,?,?,?,getDate(),getDate(),?,?,'Active',?)";

                pstmt = con.prepareStatement(sql);
                
                String[] sar = null;
                Date fdate   = null;
                String fromdate = null;
                String sadaremId = "";

                String campId ="";
                for (int i = 0; i < sArray.length; i++) 
                {
                    sar = sArray[i].split("!");

                      sadaremId = sar[1];
                      campId = getCampIdFromSADAREMID(ds, con, sadaremId);
                    
                     fdate = new SimpleDateFormat("dd/mm/yyyy").parse(sArray2[i]);
                     fromdate = new SimpleDateFormat("yyyy-mm-dd HH:MM:SS").format(fdate);
                   
                    pstmt.setString(1,sadaremId);
                    pstmt.setString(2,sar[0]);
                    pstmt.setString(3,sArray1[i]);
                    pstmt.setString(4,fromdate);
                    pstmt.setString(5,cgform.getLoginId());
                    pstmt.setString(6,InetAddress.getLocalHost().getHostAddress() );
                    pstmt.setString(7,campId); 
                    
                    pstmt.addBatch();
                }
            }
            
            int[] i = pstmt.executeBatch();


            insert = i.length > 0 ? true : false;
            
        }
        catch (SQLException sqlEx) 
        {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertDetails", "CertificateGenerationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificateGenerationDAO", "insertDetails");
        }
        catch (Exception sqlEx) 
        {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertDetails", "CertificateGenerationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificateGenerationDAO", "insertDetails");
        }
        finally
        { 
        	DBConnection.closeConnection(con);
        	DBConnection.closeResultSet(rs);
        	DBConnection.closeStatement(pstmt);
        }
        return insert;
    }

    public String getCampIdFromSADAREMID(DataSource ds, Connection con, String SADAREMID) throws SADAREMDBException, SQLException {
        String campId = "";
        Statement st = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        HashMap<Object, Object> map = null;
        boolean flag = true;
        try {

            st = con.createStatement();

            sql = "select b.camp_id from tblPerson_Disability_Details a join tblCamp_Details b on (a.Hospital_Name=b.name and a.Hospital_Address=b.Address and a.venuename=b.VenueName)"
                    + " where a.person_code='" + SADAREMID + "'";

            rs = st.executeQuery(sql);


            if (rs != null) {
                while (rs.next()) {
                    flag = false;
                    campId = rs.getString(1);
                }
            }
            if (flag) {
                sql = "select b.camp_id from tblRejectPerson_Details a join tblCamp_Details b on (a.Hospital_Name=b.name and a.Hospital_Address=b.Address and a.venuename=b.VenueName)"
                        + " where a.person_code='" + SADAREMID + "'";

                rs = st.executeQuery(sql);


                if (rs != null) {
                    while (rs.next()) {
                        flag = false;
                        campId = rs.getString(1);
                    }
                }
            }
        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampIdFromSADAREMID", "CertificateGenerationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificateGenerationDAO", "getCampIdFromSADAREMID");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampIdFromSADAREMID", "CertificateGenerationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificateGenerationDAO", "getCampIdFromSADAREMID");
        }finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return campId;
    }
}
