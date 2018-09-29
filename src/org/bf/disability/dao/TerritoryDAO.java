/*
 * TerritoryDAO.java
 *
 * Created on June 18, 2008, 4:07 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.service.AppletAuthorityService;
import org.bf.disability.service.TransactionService;
import org.bf.disability.servicefactory.AppletAuthorityServiceFactory;
import org.bf.disability.servicefactory.TransactionFactory;

import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is for getting territory lists from database
 * @author Kiran .H
 * @version 1.0
 */
public class TerritoryDAO {

    public TerritoryDAO() {
    }


    String qryString = null;
    
    
    /**
     *
     * @param datasource
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public String getDistrictsName(DataSource ds, String districtid) throws SADAREMDBException, SQLException
    {
        Connection con = null;

        ResultSet rs = null;
        TerritoryDTO territoryDTO = null;
        String districtName = null;
        PreparedStatement pstmt = null;


        try {

            con = DBConnection.getConnection();
            String query = "select district_name from tblDistrict_Details(nolock) where district_id=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtid);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                territoryDTO = new TerritoryDTO();
                territoryDTO.setDistrict_name((rs.getString("district_name")));
                districtName = rs.getString("district_name");

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistrictsName", "TerritoryDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getDistrictsName");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistrictsName", "TerritoryDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getDistrictsName");
        } finally {


            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }
        return districtName;
    }

    /**
     *
     * @param datasource
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getDistricts(DataSource ds) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList districtlist = new ArrayList();
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            // this query gets district id and district name
            String query = "select district_id ,district_name from tblDistrict_Details(nolock) order by district_name";
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                TerritoryDTO territoryDTO = new TerritoryDTO();
                territoryDTO.setDistrict_id((rs.getString("district_id")));
                territoryDTO.setDistrict_name((rs.getString("district_name")));
                districtlist.add(territoryDTO);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistricts", "TerritoryDAO", "DataBase");
            System.out.println(sqlEx);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getDistricts");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistricts", "TerritoryDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getDistricts");
        } finally {

            DBConnection.closeConnection(con); 
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return districtlist;
    }

    /**
     *
     * @param datasource
     * @param districtid
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getMandals(DataSource ds, String districtid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        ArrayList mandallist = new ArrayList();



        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "select mandal_id,mandal_name from tblMandal_Details(nolock) where district_id=? order by mandal_name";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtid);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                TerritoryDTO territory = new TerritoryDTO();
                territory.setMandal_id(rs.getString("mandal_id"));
                territory.setMandal_name(rs.getString("mandal_name"));
                mandallist.add(territory);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandals", "TerritoryDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getMandals");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandals", "TerritoryDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getMandals");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }

        return mandallist;
    }

    public int insertRailwayDoctorDetails(DataSource ds, String pid, String dname, String regno, String designation, String cer, String ip, String loginid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        PreparedStatement ps = null;
        PreparedStatement pstmt = null;
        int i = 0;



        try {
            con = DBConnection.getConnection();


            ps = con.prepareStatement("select * from RailwaypassDoctor_Details(nolock) where person_code=? ");
            ps.setString(1, pid);
            rs = ps.executeQuery();
            if (rs.next()) {
                pstmt = con.prepareStatement("update RailwaypassDoctor_Details set Status=? where person_code=?");
                pstmt.setString(1, "Inactive");
                pstmt.setString(2, pid);
                pstmt.executeUpdate();
            }
//(Person_Code,DoctorName,RegistrationNo,Designation,Railway_Certificate,Status,Insert_date) values(?,?,?,?,?,?,GETDATE()
            stmt = con.prepareStatement("insert into RailwaypassDoctor_Details values (?,?,?,?,?,?,GETDATE(),GETDATE(),?,?)");
            stmt.setString(1, pid);
            stmt.setString(2, dname);
            stmt.setString(3, regno);
            stmt.setString(4, designation);
            stmt.setString(5, cer);

            stmt.setString(6, "Active");
            stmt.setString(7, ip);
            stmt.setString(8, loginid);
            i = stmt.executeUpdate();
            if (i > 0) {
                i = 1;

                //  stmt=con.prepareStatement("update tblAllDisabilityPerson_Common_Needs set railway_certificate=? where person_code=?");
                //  stmt.setString(1, cer);
                //   stmt.setString(2, pid);

                //   if(stmt.executeUpdate()>0){

                //  }


            }

            return i;


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertRailwayDoctorDetails", "TerritoryDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "insertRailwayDoctorDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertRailwayDoctorDetails", "TerritoryDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "insertRailwayDoctorDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

            DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(ps);

        }

        //  return mandallist;
    }

    /**
     *
     * @param datasource
     * @param districtid
     * @param mandalid
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getPanchayats(DataSource ds, String districtid, String mandalid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList panchayatlist = new ArrayList();


        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "select panchayat_id,panchayat_name from  tblPanchayat_Details(nolock) where district_id=? and  mandal_id=? order by panchayat_name";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtid);
            pstmt.setString(2, mandalid);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                TerritoryDTO territory = new TerritoryDTO();
                territory.setPanchayat_id(rs.getString("panchayat_id"));
                territory.setPanchayat_name(rs.getString("panchayat_name"));
                panchayatlist.add(territory);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPanchayats", "TerritoryDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getPanchayats");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPanchayats", "TerritoryDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getPanchayats");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return panchayatlist;
    }

    /**
     *
     * @param datasource
     * @param districtid
     * @param mandalid
     * @param panchayatid
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getVillages(DataSource ds, String districtid, String mandalid, String panchayatid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList villagelist = new ArrayList();
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            //String query = "select village_id,village_name from tblVillage_Details where district_id='" + districtid + "' and  mandal_id='" + mandalid + "' order by village_name";

            String query = "select distinct p.village_id,p.village_name "
            		+ "from tblVillage_Details p left join tblHabitation_Details h on( p.district_id=p.district_id and p.mandal_id=p.mandal_id and p.village_id=h.village_id) "
            		+ "where p.district_id=? and p.mandal_id=? and h.district_id=? and h.mandal_id=? and h.panchayat_id=? order by village_name";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtid);
            pstmt.setString(2, mandalid);
            pstmt.setString(3, districtid);
            pstmt.setString(4, mandalid);
            pstmt.setString(5, panchayatid);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                TerritoryDTO territory = new TerritoryDTO();
                territory.setVillage_id(rs.getString("village_id"));
                territory.setVillage_name(rs.getString("village_name"));
                villagelist.add(territory);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillages", "TerritoryDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getVillages");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillages", "TerritoryDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getVillages");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return villagelist;
    }

    /**
     *
     * @param datasource
     * @param districtid
     * @param mandalid
     * @param panchayatid
     * @param villageid
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getHabitations(DataSource ds, String districtid, String mandalid, String panchayatid, String villageid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList habitationlist = new ArrayList();
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "select habitation_id,habitation_name from tblHabitation_Details(nolock) where district_id=? and  mandal_id=?  and village_id=? and status='Active' order by habitation_name";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtid);
            pstmt.setString(2, mandalid);
            pstmt.setString(3, villageid);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                TerritoryDTO territory = new TerritoryDTO();
                territory.setHabitation_id(rs.getString("habitation_id"));
                territory.setHabitation_name(rs.getString("habitation_name"));

                habitationlist.add(territory);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHabitations", "TerritoryDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getHabitations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHabitations", "TerritoryDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getHabitations");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return habitationlist;
    }

    /**
     *
     * @param datasource
     * @param districtid
     * @param mandalid
     * @param panchayatid
     * @param villageid
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getVillageAll(DataSource ds, String districtid, String mandalid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList villagelist = new ArrayList();
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "select village_id,village_name from tblVillage_Details(nolock) where district_id=? and  mandal_id=?   order by village_name";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtid);
            pstmt.setString(2, mandalid);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                TerritoryDTO territory = new TerritoryDTO();
                territory.setVillage_id(rs.getString("village_id"));
                territory.setVillage_name(rs.getString("village_name"));

                villagelist.add(territory);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillageAll", "TerritoryDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getVillageAll");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillageAll", "TerritoryDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getVillageAll");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return villagelist;
    }

    /**
     *
     * @param districtid
     * @param mandalid
     * @param panchayatid
     * @param villageid
     * @param habitationid
     * @param datasource
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public String getHabitationCode(String districtid, String mandalid, String panchayatid, String assemblyid, String villageid, String habitationid, DataSource ds) throws SADAREMDBException, SQLException {
    	 String habitationUpdatedCode = null;
    	    CommonDAO comObj = new CommonDAOImpl();
    	    try {
    	/*      Connection con = null;
        //Statement stmt1 = null;
        //Statement stmt2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        String habitationUpdatedCode = null;
        String habitationCode = null;
        String habitationMaxCode = null;
        PreparedStatement pstmt=null;
        try {
            con = DBConnection.getConnection();
            	
            CommonDAO comObj = new CommonDAOImpl();
         
    String getHabitationCodeQuery ="select habitation_code from tblHabitation_Details where district_id=? " +
                     " and mandal_id=? and Panchayat_ID=? " +
                     "  and village_id=? and habitation_id=?";
            pstmt = con.prepareStatement(getHabitationCodeQuery);
            pstmt.setString(1, districtid);
            pstmt.setString(2, mandalid);
            pstmt.setString(3, panchayatid);
            pstmt.setString(4, villageid);
            pstmt.setString(5, habitationid);

            rs1 = pstmt.executeQuery();
            while (rs1.next()) 
            {
                habitationCode = rs1.getString(1);
            }
            // String getMaxHabitationCodeQuery = "select Person_Code from tblPerson_Personal_Details  group by Person_Code having max(cast(Person_Code as bigint))=(select max(cast(Person_Code as bigint)) from tblPerson_Personal_Details where District_Id='" + districtid + "' and Mandal_ID='" + mandalid + "' and Village_ID='" + villageid + "' and Habitation_ID='" + habitationid + "')";

            String getMaxHabitationCodeQuery = "select max(Person_Code) from tblPerson_Personal_Details_new   with (nolock) where HabCode = ?";
            pstmt = con.prepareStatement(getMaxHabitationCodeQuery);
            pstmt.setString(1, habitationCode);
            rs2 = pstmt.executeQuery();
            while (rs2.next()) 
            {
                habitationMaxCode = rs2.getString(1);
            }*/
         
        	habitationUpdatedCode = comObj.getNewSADAREMID(districtid, mandalid, villageid);
       //      System.out.println("->"+habitationUpdatedCode);
            
       /*     
            if (habitationMaxCode != null) 
            {

                long longCode = Long.parseLong(habitationMaxCode);
                
                

                if (habitationMaxCode.length() == 18 || habitationMaxCode.substring((habitationMaxCode.length() - 3), (habitationMaxCode.length())).equals("999"))
                {
                	habitationUpdatedCode = comObj.getNewSADAREMID(districtid, mandalid, villageid);
                } 
                else
                {
                    if (habitationMaxCode.startsWith("0"))
                    {
                        String last3Digits = habitationMaxCode.substring((habitationMaxCode.length() - 3), (habitationMaxCode.length()));
                        if (last3Digits.equals("999")) 
                        {
                            StringBuffer strbuf = new StringBuffer(habitationCode);
                            habitationUpdatedCode = strbuf.append("1000").toString();
                        }
                        else 
                        {
                            longCode++;
                            String withZero = String.valueOf(longCode);
                            habitationUpdatedCode = paddingString(withZero, withZero.length() + 1, '0');
                        }
                    } 
                    else 
                    {
                        String last3Digits = (String.valueOf(longCode)).substring(String.valueOf(longCode).length() - 3, (String.valueOf(longCode).length()));
                        if (last3Digits.equals("999"))
                        {
                            StringBuffer strbuf = new StringBuffer(habitationCode);
                            habitationUpdatedCode = strbuf.append("1000").toString();
                        } 
                        else
                        {
                            longCode++;
                            habitationUpdatedCode = String.valueOf(longCode);
                        }
                    }
                }
            } 
            else
            {
                StringBuffer strbuf = new StringBuffer(habitationCode);
                habitationUpdatedCode = strbuf.append("001").toString();
            }
*/
        } /*catch (SQLException sqlEx)
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHabitationCode", "TerritoryDAO", "DataBase");
            System.out.println(sqlEx);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getHabitationCode");
        }*/ 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHabitationCode", "TerritoryDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getHabitationCode");
        }
        finally
        {
         //   DBConnection.closeConnection(con);
         //   DBConnection.closeResultSet(rs1);
         //   DBConnection.closeResultSet(rs2);

        }

      //  System.out.println("habitationUpdatedCode : "+habitationUpdatedCode);
        
        return habitationUpdatedCode;
    }

    /**
     *
     * @param s
     * @param n
     * @param c
     * @return
     */
    public String paddingString(String s, int n, char c) {
        StringBuffer str = new StringBuffer(s);
        int strLength = str.length();
        if (n > 0 && n > strLength) {
            for (int i = 0; i <= n; i++) {

                if (i < n - strLength) {
                    str.insert(0, c);
                }

            }
        }
        return str.toString();
    }

    /**
     *
     * @param districtid
     * @param mandalid
     * @param panchayatid
     * @param villageid
     * @param habitationid
     * @param personstatus
     * @param datasource
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getPersonCode(String districtid, String mandalid, String panchayatid, String villageid, String habitationid, String personstatus, DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList personcodelist = new ArrayList();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        TerritoryDTO territory;
        try {
            con = DBConnection.getConnection();
            String query = "select Person_Code from  tblPerson_Personal_Details   with (nolock) where District_Id=? and Mandal_ID=? and Village_ID=? and Habitation_ID=? and Person_Status=?";
            if (districtid.equals("16") && mandalid.equals("84") && villageid.equals("004") && habitationid.equals("01")) {
                query = "select Person_Code from  tblPerson_Personal_Details   with (nolock) where District_Id=? and Mandal_ID=? and Village_ID=? and Habitation_ID in('00',?) and Person_Status=?";
            }

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtid);
            pstmt.setString(2, mandalid);
            pstmt.setString(3, villageid);
            pstmt.setString(4, habitationid);
            pstmt.setString(5, personstatus);
            rs = pstmt.executeQuery();
            while (rs.next()) {

                territory = new TerritoryDTO();
                territory.setPersoncode(rs.getString(1));
                personcodelist.add(territory);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonCode", "TerritoryDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getPersonCode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonCode", "TerritoryDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getPersonCode");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return personcodelist;
    }

    /**
     *
     * @param datasource
     * @param personcode
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public TerritoryDTO getTotalValues(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        TerritoryDTO territory = new TerritoryDTO();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        double zero = 0;
        try {
            con = DBConnection.getConnection();
            String query = "select UpperExtrimity,LowerExtrimity,Amputation,Transverse,Trunk,"
                    + "Evaluation,Cardiopulmonary,Dwarfism,HearingImpairment,VisualImpairment,MentalRetardation,"
                    + "MentalIllness,TotalLocomotor,TotalDisability,Multiple_Disability_Sub_ID from "
                    + "tblPerson_Disability_TotalValue_Details(nolock) where Person_code=? and Status='Active'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                territory.setUpperExtremity_Total(rs.getString(1));
                territory.setLowerExtremity_Total(rs.getString(2));
                territory.setAmputation_Total(rs.getString(3));
                territory.setTransverse_Total(rs.getString(4));
                territory.setTrunk_Total(rs.getString(5));
                territory.setPhysical_Impairments_Total(rs.getString(6));
                territory.setPulmonary_Condition(rs.getString(7));
                territory.setDwarfism_Total(rs.getString(8));
                territory.setHearing_Percentage(rs.getString(9));
                territory.setVisual_Impairment(rs.getString(10));
                territory.setMental_Retardation_Total(rs.getString(11));
                territory.setMental_Disability_Total(rs.getString(12));
                territory.setLocomotortotal(rs.getDouble(13));
                territory.setDisabilityvalue(rs.getDouble(14));
                territory.setMultipleRadio(rs.getString(15));

            } else {
                territory.setUpperExtremity_Total(String.valueOf(zero));
                territory.setLowerExtremity_Total(String.valueOf(zero));
                territory.setAmputation_Total(String.valueOf(zero));
                territory.setTransverse_Total(String.valueOf(zero));
                territory.setTrunk_Total(String.valueOf(zero));
                territory.setPhysical_Impairments_Total(String.valueOf(zero));
                territory.setPulmonary_Condition(String.valueOf(zero));
                territory.setDwarfism_Total(String.valueOf(zero));
                territory.setHearing_Percentage(String.valueOf(zero));
                territory.setVisual_Impairment(String.valueOf(zero));
                territory.setMental_Retardation_Total(String.valueOf(zero));
                territory.setMental_Disability_Total(String.valueOf(zero));
                territory.setLocomotortotal(zero);
                territory.setDisabilityvalue(zero);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTotalValues", "TerritoryDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getTotalValues");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTotalValues", "TerritoryDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getTotalValues");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }

        return territory;
    }

    /**
     *
     * @param datasource
     * @param list
     * @param list1
     * @param totaldisability
     * @param personcode
     * @param loginid
     * @param systemip
     * @param maxMultipleId
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
  
	public int totalValueInsert(DataSource ds, TerritoryDTO territoryDTO, LinkedList list, LinkedList list1, double totaldisability, String personcode, String loginid, String systemip, int maxMultipleId, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i=0,j = 0;
        Connection con = null;
        CallableStatement cstmt = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();

        HttpSession session = request.getSession();
       
       
        PreparedStatement pstmt = null;
        ResultSet rs = null;
       
       


        String sql = null;



        try {
            /* Getting the categeoryIdncategoryCount */
        	 TransactionService transactionService = TransactionFactory.getTransaction();
        	 AppletAuthorityService appletAuthorityService = AppletAuthorityServiceFactory.getAppletAuthorityServiceImpl();
            details = dao.getCategoryDetails(ds, personcode);
            con = DBConnection.getConnection();
            
            //query for finding personcode
            cstmt = con.prepareCall("{Call SP_tblPerson_Disability_TotalValue_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, personcode);
            if (territoryDTO.getUpperExtremity_Total() != null) {
                cstmt.setDouble(2, (new Double(territoryDTO.getUpperExtremity_Total())));
            } else {
                cstmt.setString(2, null);
            }
            if (territoryDTO.getLowerExtremity_Total() != null) {
                cstmt.setDouble(3, (new Double(territoryDTO.getLowerExtremity_Total())));
            } else {
                cstmt.setString(3, null);
            }

            if (territoryDTO.getAmputation_Total() != null) {
                cstmt.setDouble(4, (new Double(territoryDTO.getAmputation_Total())));
            } else {
                cstmt.setString(4, null);
            }

            if (territoryDTO.getTransverse_Total() != null) {
                cstmt.setDouble(5, (new Double(territoryDTO.getTransverse_Total())));
            } else {
                cstmt.setString(5, null);
            }
            if (territoryDTO.getTrunk_Total() != null) {
                cstmt.setDouble(6, (new Double(territoryDTO.getTrunk_Total())));
            } else {
                cstmt.setString(6, null);
            }
            if (territoryDTO.getPhysical_Impairments_Total() != null) {
                cstmt.setDouble(7, (new Double(territoryDTO.getPhysical_Impairments_Total())));
            } else {
                cstmt.setString(7, null);
            }
            if (territoryDTO.getPulmonary_Condition() != null) {
                cstmt.setDouble(8, (new Double(territoryDTO.getPulmonary_Condition())));
            } else {
                cstmt.setString(8, null);
            }
            if (territoryDTO.getDwarfism_Total() != null) {
                cstmt.setDouble(9, (new Double(territoryDTO.getDwarfism_Total())));
            } else {
                cstmt.setString(9, null);
            }
            if (territoryDTO.getHearing_Percentage() != null) {
                cstmt.setDouble(10, (new Double(territoryDTO.getHearing_Percentage())));
            } else {
                cstmt.setString(10, null);
            }

            if (territoryDTO.getVisual_Impairment() != null) {
                cstmt.setDouble(11, (new Double(territoryDTO.getVisual_Impairment())));
            } else {
                cstmt.setString(11, null);
            }

            if (territoryDTO.getMental_Retardation_Total() != null) {
                cstmt.setDouble(12, (new Double(territoryDTO.getMental_Retardation_Total())));
            } else {
                cstmt.setString(12, null);
            }
            if (territoryDTO.getMental_Disability_Total() != null) {
                cstmt.setDouble(13, (new Double(territoryDTO.getMental_Disability_Total())));
            } else {
                cstmt.setString(13, null);
            }
            if (territoryDTO.getTotalforphysical() != null) {
                cstmt.setDouble(14, (new Double(territoryDTO.getTotalforphysical())));
            } else {
                cstmt.setString(14, null);
            }
            cstmt.setInt(15, maxMultipleId);
            if (territoryDTO.getTotaldisability() != null) {
                cstmt.setDouble(16, (new Double(territoryDTO.getTotaldisability())));
            } else {
                cstmt.setString(16, null);
            }
            cstmt.setString(17, loginid);
            cstmt.setString(18, systemip);

            cstmt.setInt(19, Integer.parseInt(details[0].toString()));
            cstmt.setInt(20, Integer.parseInt(details[1].toString()));


            /*if(session.getAttribute("categoryIdAu")!=null && session.getAttribute("categoryCountAu")!=null) {
            cstmt.setInt(19, Integer.parseInt((String)session.getAttribute("categoryIdAu")));
            cstmt.setInt(20, Integer.parseInt((String)session.getAttribute("categoryCountAu")));
            }else {
            cstmt.setInt(19, 1);
            cstmt.setInt(20, 1);
            }*/


            i = cstmt.executeUpdate(); 
            if(i!= 0)
            {
            	
            	 sql = "update tblperson_personal_details set reasonforpersonstatus='Live' where Person_Code=? ";
            	 pstmt = con.prepareStatement(sql);
            	 pstmt.setString(1, personcode);
                 j=pstmt.executeUpdate();
                 
                 if(j!= 0)
                 {
                 
            	 transactionService.updateTransactionStatus(ds, "Completed", personcode); 
            	  sql = "select Person_Code from AppellateAuthorityandTemporary_RegistrationDetails(nolock) where Person_Code=? and status='Active'";
            	  pstmt = con.prepareStatement(sql);
             	 pstmt.setString(1, personcode);
                  rs = pstmt.executeQuery();
                  
                  if (rs.next()) { 
                 	 
                 	 appletAuthorityService.checkPersonStatusForAppealAuthority(ds,personcode,session.getAttribute("personstatus").toString());
                  } 
                 	
                  }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "totalValueInsert", "TerritoryDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "totalValueInsert");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "totalValueInsert", "TerritoryDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "totalValueInsert");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);
            DBConnection.closeStatement(pstmt);

        }
        return i;
    }

    /**
     *
     * @param datasource
     * @param list
     * @param list1
     * @param totaldisability
     * @param personcode
     * @param loginid
     * @param systemip
     * @param maxMultipleId
     * @throws org.bf.disability.Exception.SADAREMException
     * @return
     */
    public synchronized int totalValueUpdate(DataSource ds, TerritoryDTO territoryDTO, LinkedList list, LinkedList list1, double totaldisability, String personcode, String loginid, String systemip, int maxMultipleId, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i=0,j = 0;
        Connection con = null;
        CallableStatement cstmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();

        HttpSession session = request.getSession();

        String sql = null;
        try {

            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, personcode);
            con = DBConnection.getConnection();
            
            TransactionService transactionService = TransactionFactory.getTransaction();

            AppletAuthorityService appletAuthorityService = AppletAuthorityServiceFactory.getAppletAuthorityServiceImpl();

            sql = "select Person_Code from tblPerson_Disability_TotalValue_Details(nolock) where Person_Code=? and status='Active'";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                cstmt = con.prepareCall("{Call SP_tblPerson_Disability_TotalValue_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, personcode);
                if (territoryDTO.getUpperExtremity_Total() != null) {
                    cstmt.setDouble(2, (new Double(territoryDTO.getUpperExtremity_Total())));
                } else {
                    cstmt.setString(2, null);
                }
                if (territoryDTO.getLowerExtremity_Total() != null) {
                    cstmt.setDouble(3, (new Double(territoryDTO.getLowerExtremity_Total())));
                } else {
                    cstmt.setString(3, null);
                }

                if (territoryDTO.getAmputation_Total() != null) {
                    cstmt.setDouble(4, (new Double(territoryDTO.getAmputation_Total())));
                } else {
                    cstmt.setString(4, null);
                }

                if (territoryDTO.getTransverse_Total() != null) {
                    cstmt.setDouble(5, (new Double(territoryDTO.getTransverse_Total())));
                } else {
                    cstmt.setString(5, null);
                }
                if (territoryDTO.getTrunk_Total() != null) {
                    cstmt.setDouble(6, (new Double(territoryDTO.getTrunk_Total())));
                } else {
                    cstmt.setString(6, null);
                }
                if (territoryDTO.getPhysical_Impairments_Total() != null) {
                    cstmt.setDouble(7, (new Double(territoryDTO.getPhysical_Impairments_Total())));
                } else {
                    cstmt.setString(7, null);
                }
                if (territoryDTO.getPulmonary_Condition() != null) {
                    cstmt.setDouble(8, (new Double(territoryDTO.getPulmonary_Condition())));
                } else {
                    cstmt.setString(8, null);
                }
                if (territoryDTO.getDwarfism_Total() != null) {
                    cstmt.setDouble(9, (new Double(territoryDTO.getDwarfism_Total())));
                } else {
                    cstmt.setString(9, null);
                }
                if (territoryDTO.getHearing_Percentage() != null) {
                    cstmt.setDouble(10, (new Double(territoryDTO.getHearing_Percentage())));
                } else {
                    cstmt.setString(10, null);
                }

                if (territoryDTO.getVisual_Impairment() != null) {
                    cstmt.setDouble(11, (new Double(territoryDTO.getVisual_Impairment())));
                } else {
                    cstmt.setString(11, null);
                }

                if (territoryDTO.getMental_Retardation_Total() != null) {
                    cstmt.setDouble(12, (new Double(territoryDTO.getMental_Retardation_Total())));
                } else {
                    cstmt.setString(12, null);
                }
                if (territoryDTO.getMental_Disability_Total() != null) {
                    cstmt.setDouble(13, (new Double(territoryDTO.getMental_Disability_Total())));
                } else {
                    cstmt.setString(13, null);
                }
                if (territoryDTO.getTotalforphysical() != null) {
                    cstmt.setDouble(14, (new Double(territoryDTO.getTotalforphysical())));
                } else {
                    cstmt.setString(14, null);
                }

//                cstmt.setDouble(3, ((Double) list1.get(1)).doubleValue());
//
//                cstmt.setDouble(4, ((Double) list1.get(2)).doubleValue());

//                cstmt.setDouble(5, ((Double) list1.get(3)).doubleValue());
//
//                cstmt.setDouble(6, ((Double) list1.get(4)).doubleValue());
//                cstmt.setDouble(7, ((Double) list1.get(5)).doubleValue());
//
//                cstmt.setDouble(8, ((Double) list1.get(6)).doubleValue());
//                cstmt.setDouble(9, ((Double) list1.get(7)).doubleValue());


//                cstmt.setDouble(10, ((Double) list.get(0)).doubleValue());
//
//                cstmt.setDouble(11, ((Double) list.get(1)).doubleValue());
//
//                cstmt.setDouble(12, ((Double) list.get(2)).doubleValue());
//                cstmt.setDouble(13, ((Double) list.get(3)).doubleValue());
//                cstmt.setDouble(14, ((Double) list.get(4)).doubleValue());

                cstmt.setInt(15, maxMultipleId);
                if (territoryDTO.getTotaldisability() != null) {
                    cstmt.setDouble(16, (new Double(territoryDTO.getTotaldisability())));
                } else {
                    cstmt.setString(16, null);
                }
                cstmt.setString(17, loginid);
                cstmt.setString(18, systemip);

                cstmt.setInt(19, Integer.parseInt(details[0].toString()));
                cstmt.setInt(20, Integer.parseInt(details[1].toString()));

                /* if (session.getAttribute("categoryIdUpdateAu") != null && session.getAttribute("categoryCountUpdateAu") != null) {
                cstmt.setInt(19, Integer.parseInt((String) session.getAttribute("categoryIdUpdateAu")));
                cstmt.setInt(20, Integer.parseInt((String) session.getAttribute("categoryCountUpdateAu")));
                } else {
                cstmt.setInt(19, 1);
                cstmt.setInt(20, 1);
                }*/

                i = cstmt.executeUpdate(); 
                if(i!= 0)
                {
                	 sql = "update tblperson_personal_details set ReasonforPersonStatus='Live' where Person_Code=? ";
                	 pstmt = con.prepareStatement(sql);
                	 pstmt.setString(1, personcode);
                     j=pstmt.executeUpdate();
                     
                     if(j!= 0)
                     {
                     
                	
                	 transactionService.updateTransactionStatus(ds, "Completed", personcode); 
                	 

                     sql = "select Person_Code from AppellateAuthorityandTemporary_RegistrationDetails(nolock) where Person_Code=? and status='Active' ";
                     pstmt = con.prepareStatement(sql);
                     pstmt.setString(1, personcode);
                     rs = pstmt.executeQuery();
                           
                     if (rs.next()) {
                    	 
                    	 appletAuthorityService.checkPersonStatusForAppealAuthority(ds,personcode,session.getAttribute("personstatus").toString());
                     }
                     }
                }
            } else {
                totalValueInsert(ds, territoryDTO, list, list1, totaldisability, personcode, loginid, systemip, maxMultipleId, request);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "totalValueUpdate", "TerritoryDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "totalValueUpdate");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "totalValueUpdate", "TerritoryDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "totalValueUpdate");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(cstmt);

        }
        return i;
    }

    /**
     *
     * @param ds
     * @param personcode
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getPersonStatus(DataSource ds, String personcode) throws SADAREMDBException, SQLException {

        ArrayList personStatusList = new ArrayList();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        TerritoryDTO territory;
        PreparedStatement pstmt = null;
        try {
            con = DBConnection.getConnection();
            ///  String query = "select distinct(P.Person_Status),D.District_Name,M.Mandal_Name,V.Village_Name,H.Habitation_Name,P.District_Id,P.Village_Id,P.Mandal_Id,P.Habitation_Id,P.Status,P.ReasonforPersonStatus from tblPerson_Personal_Details P, dbo.tblMandal_Details M ,dbo.tblDistrict_Details D,dbo.tblVillage_Details V,dbo.tblHabitation_Details H where P.District_ID = D.District_ID and P.Mandal_ID = M.Mandal_ID and P.District_ID = M.District_ID and P.Village_ID = V.Village_ID and P.District_ID = V.District_ID and P.Mandal_ID = V.Mandal_ID and P.Habitation_ID = H.Habitation_ID and P.District_ID = H.District_ID and P.Mandal_ID = H.Mandal_ID and P.Village_ID = H.Village_ID and   P.Person_Code='" + personcode + "'";
            String query = 
            		"SELECT DISTINCT(P.Person_Status),D.District_Name,M.Mandal_Name, \n"
            		+ "V.Village_Name,H.Habitation_Name,P.district_id  AS DISTRICTID,P.VILLAGE_ID  AS VILLAGEID,P.MANDAL_ID  AS MANDALID,P.Habitation_ID AS HABITATIONID, \n"
            		+ "P.Status,P.ReasonforPersonStatus,coalesce(p.proof_id,'-'),coalesce(p.proofdoc_type,'-')  \n"
            		+ "FROM DBO.TBLPERSON_PERSONAL_DETAILS P   with (nolock) ,DBO.TBLDISTRICT_DETAILS D,DBO.TBLMANDAL_DETAILS M, DBO.TBLVILLAGE_DETAILS  V,DBO.TBLHABITATION_DETAILS H  \n"
            		+ "WHERE D.DISTRICT_ID = p.district_id AND \n"
            		+ " M.DISTRICT_ID = p.district_id AND \n"
            		+ " M.MANDAL_ID = P.MANDAL_ID AND \n"
            		+ " V.DISTRICT_ID = p.district_id AND \n"
            		+ " V.MANDAL_ID = P.MANDAL_ID AND \n"
            		+ " V.VILLAGE_ID = P.VILLAGE_ID AND \n"
            		+ " H.HABITATION_CODE = P.HABCODE AND \n"
            		+ " P.Person_Code=?";
            
            
            
           // System.out.println("getPersonStatus Query : \n"+query);
            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                territory = new TerritoryDTO();
                territory.setPersonstatus(rs.getString(1));
                territory.setDistrict_name(rs.getString(2));
                territory.setMandal_name(rs.getString(3));
                territory.setVillage_name(rs.getString(4));
                territory.setHabitation_name(rs.getString(5));
                territory.setDistrict_id(rs.getString(6));
                territory.setVillage_id(rs.getString(7));
                territory.setMandal_id(rs.getString(8));
                territory.setHabitation_id(rs.getString(9));
                territory.setStatus(rs.getString(10));
                territory.setReasonforstatus(rs.getString(11));
                territory.setProof_id(rs.getString(12));
                territory.setProofdoc_type(rs.getString(13));
                personStatusList.add(territory);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonStatus", "TerritoryDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getPersonStatus");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonStatus", "TerritoryDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getPersonStatus");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

        }
        return personStatusList;
    }

    /**
     *
     * @param districtid
     * @param mandalid
     * @param panchayatid
     * @param villageid
     * @param habitationid
     * @param datasource
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public String getHabitationCodeRationCard(String districtid, String mandalid, String panchayatid, String assemblyid, String villageid, String habitationid, DataSource ds) throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt1 = null;
        Statement stmt2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        String habitationUpdatedCode = null;
        String habitationCode = null;
        String habitationMaxCode = null;
        PreparedStatement pstmt1 = null;
        try {
            con = DBConnection.getConnection();

            String getHabitationCodeQuery = "select habitation_code from tblHabitation_Details(nolock) where district_id=? and mandal_id=? and village_id=? and habitation_id=? and Panchayat_ID=?";

            pstmt1 = con.prepareStatement(getHabitationCodeQuery);
            pstmt1.setString(1, districtid);
            pstmt1.setString(2, mandalid);
            pstmt1.setString(3, villageid);
            pstmt1.setString(4, habitationid);
            pstmt1.setString(5, panchayatid);

            rs1 = pstmt1.executeQuery();
            while (rs1.next()) {
                habitationCode = rs1.getString(1);
            }
            // String getMaxHabitationCodeQuery = "select Person_Code from tblPerson_Personal_Details  group by Person_Code having max(cast(Person_Code as bigint))=(select max(cast(Person_Code as bigint)) from tblPerson_Personal_Details where District_Id='" + districtid + "' and Mandal_ID='" + mandalid + "' and Village_ID='" + villageid + "' and Habitation_ID='" + habitationid + "')";

            String getMaxHabitationCodeQuery = "select max(Person_Code) from tblPerson_Personal_Details_new(nolock)   with (nolock) where habcode = ?";

            pstmt1 = con.prepareStatement(getMaxHabitationCodeQuery);
            pstmt1.setString(1, habitationCode);
            rs2 = pstmt1.executeQuery();
            while (rs2.next()) {
                habitationMaxCode = rs2.getString(1);
            }
            if (habitationMaxCode != null) {

                long longCode = Long.parseLong(habitationMaxCode);

                if (habitationMaxCode.length() == 18 && habitationMaxCode.substring((habitationMaxCode.length() - 3), (habitationMaxCode.length())).equals("999")) {
                    //do something that prevent user from entering further more records
                } else {
                    if (habitationMaxCode.startsWith("0")) {
                        String last3Digits = habitationMaxCode.substring((habitationMaxCode.length() - 3), (habitationMaxCode.length()));
                        if (last3Digits.equals("999")) {
                            StringBuffer strbuf = new StringBuffer(habitationCode);
                            habitationUpdatedCode = strbuf.append("1000").toString();
                        } else {
                            longCode++;
                            String withZero = String.valueOf(longCode);
                            habitationUpdatedCode = paddingString(withZero, withZero.length() + 1, '0');
                        }
                    } else {
                        String last3Digits = (String.valueOf(longCode)).substring(String.valueOf(longCode).length() - 3, (String.valueOf(longCode).length()));
                        if (last3Digits.equals("999")) {
                            StringBuffer strbuf = new StringBuffer(habitationCode);
                            habitationUpdatedCode = strbuf.append("1000").toString();
                        } else {
                            longCode++;
                            habitationUpdatedCode = String.valueOf(longCode);
                        }
                    }
                }
            } else {
                StringBuffer strbuf = new StringBuffer(habitationCode);
                habitationUpdatedCode = strbuf.append("001").toString();
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHabitationCodeRationCard", "TerritoryDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getHabitationCodeRationCard");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHabitationCodeRationCard", "TerritoryDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "getHabitationCodeRationCard");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs1);
            DBConnection.closeResultSet(rs2);
            DBConnection.closeStatement(stmt1);
            DBConnection.closeStatement(stmt2);

        }

        return habitationUpdatedCode;
    }

    public boolean isEligibleforCertificate(DataSource ds, String personcode, HttpServletRequest req) throws SADAREMDBException, SQLException
    {
        boolean exi = false;  
        try 
        {
        	ArrayList paramList = new ArrayList();
        	ArrayList tempList  = new ArrayList();
        	 
    
           	qryString ="select person_code,years_for_temparary, case when years_for_temparary=1 \n"
                    + " then years_for_temparary*365 when years_for_temparary=2 then years_for_temparary*365 when \n"
                    + " years_for_temparary=3 then years_for_temparary*365 when years_for_temparary=4 then \n"
                    + " years_for_temparary*365 when years_for_temparary=5 then years_for_temparary*365  end \n"
                    + " as noofdays from tblPerson_Causes_of_Disability_Details where years_for_temparary is not null \n"
                    + " and years_for_temparary!=0 and status='active' and person_code=? \n";
           	
           	paramList = new ArrayList();
           
           	tempList  = new ArrayList();
           	tempList.add("S");
           	tempList.add(personcode);
           	paramList.add(tempList);
           	
           	ArrayList result = (ArrayList)DataAccess.pickDataByPrepareStmt(qryString, paramList, false, false);
           	
           	 
            if (result.size()>0) 
            {
            	
            	ArrayList dataList = (ArrayList)result.get(0);

            	qryString =  "select d.created_date,c.years_for_temparary,c.person_code,d.disability_id,t.totaldisability, '3' as categoryid, 1 as categorycount, p.rationcard_number, p.rationcard_slno, p.person_status, 'Edit'as vieweditmode,getdate() as  created_date, getdate() as updated_date,'Active' as Status,  'kavitha999'as loginid,'Active'  as flagdelete from tblperson_personal_details p   with (nolock) join tblPerson_Causes_of_Disability_Details c on(c.person_code=p.person_code and "
                        + " c.years_for_temparary=?) join dbo.tblPerson_Disability_Details d on(p.person_code=d.person_code) join  dbo.tblPerson_Disability_TotalValue_Details t on(p.person_code=t.person_code) "
                        + " and DATEDIFF(DAY,d.created_date,GETDATE())>=? and p.status='Active' and p.person_code=? and d.status='Active' and t.status='Active' and c.status='Active'";
            	
            	paramList = new ArrayList();
            	
            	tempList  = new ArrayList();
               	tempList.add("S");
               	tempList.add(dataList.get(1));
               	paramList.add(tempList);
               	

            	tempList  = new ArrayList();
               	tempList.add("S");
               	tempList.add(dataList.get(2));
               	paramList.add(tempList); 
               	
            	tempList  = new ArrayList();
               	tempList.add("S");
               	tempList.add(personcode);
               	paramList.add(tempList);
               	
               	result = (ArrayList)DataAccess.pickDataByPrepareStmt(qryString, paramList, false, false);
               	
                if (result.size()>0) 
                {
                    req.setAttribute("msg", "Temporary Certificate date is expired!!!");
                    exi = true;
                }
            }
        } 
        catch (SQLException sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "isEligibleforCertificate", "TerritoryDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "isEligibleforCertificate");
        } 
        catch (Exception sqlEx)
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "isEligibleforCertificate", "TerritoryDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryDAO", "isEligibleforCertificate");
        } 
         
        return exi;


    }

    public static String getSADAREMDistrictId(DataSource ds, String esapsDistrictId) throws SADAREMDBException, SQLException {
        String districtId = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            qry = "select District_ID from tblDistrict_Details(nolock) where esapsDisitrictId=?";
            pstmt = con.prepareStatement(qry);
            pstmt.setString(1, esapsDistrictId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                districtId = rs.getString(1);
            }
        } catch (Exception e) {
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return districtId;
    }

    public int getPersoncodeDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        int count = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            qry = "select count(*) from tblperson_personal_details_new(nolock) where person_code=?";
            
            pstmt = con.prepareStatement(qry);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return count;
    }
}
