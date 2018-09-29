/*
 * TerritoryInformationDAO.java
 *
 * Created on January 21, 2009, 4:56 PM
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

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.http.HttpRequest;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.TerritoryInformationDTO;
import org.bf.disability.util.RequestParameterHolder;
/*
 * TerritoryInformationDAO.java
 *
 * Created on December 11, 2008, 11:21 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author kiran_h
 */
public class TerritoryInformationDAO {

    /** Creates a new instance of TerritoryInformationDAO */
    public TerritoryInformationDAO() {
    }

    public static ArrayList getTerritoryDetails(DataSource ds, RequestParameterHolder holder) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList territory_info = new ArrayList();
        ArrayList territory_id_list = new ArrayList();
        ArrayList territory_name_list = new ArrayList();
        int decisionParameter = 0;
        String query = null;

        try {

            con = DBConnection.getConnection();

            decisionParameter = Integer.parseInt(holder.getTerritory());

            /*  switch(decisionParameter)
            {
            case 1: query="select District_ID ,District_Name from tblDistrict_Details order by District_Name";
            break;
            case 2: query="select Mandal_ID ,Mandal_Name from tblMandal_Details where District_ID='"+holder.getDistrictid()+"' order by Mandal_Name";
            break;
            case 3: query="select Assembly_ID ,Assembly_Name from tblAssembly_Details where District_ID='"+holder.getDistrictid()+"' order by Assembly_Name";
            break;
            case 4: query="select Panchayat_ID ,Panchayat_Name from tblPanchayat_Details where District_ID='"+holder.getDistrictid()+"' and Mandal_ID='"+holder.getMandalid()+"' order by Panchayat_Name";
            break;
            case 5: query="select Village_ID ,Village_Name from tblVillage_Details where District_ID='"+holder.getDistrictid()+"' and Mandal_ID='"+holder.getMandalid()+"' order by Village_Name";
            break;
            case 6: query="select Habitation_ID ,Habitation_Name from tblHabitation_Details where District_ID='"+holder.getDistrictid()+"' and Mandal_ID='"+holder.getMandalid()+"' and Village_ID='"+holder.getVillageid()+"' order by Habitation_Name";
            break;
            case 7: query="select distinct p.village_id,p.village_name from tblVillage_Details p left join tblHabitation_Details h on( p.district_id=p.district_id and p.mandal_id=p.mandal_id and p.village_id=h.village_id) where p.district_id='"+holder.getDistrictid()+"' and p.mandal_id='"+holder.getMandalid()+"' and h.district_id='"+holder.getDistrictid()+"' and h.mandal_id='"+holder.getMandalid()+"' and h.panchayat_id='"+holder.getPanchayatid()+"' order by village_name";
            break;
            case 8: query="select Habitation_ID ,Habitation_Name from tblHabitation_Details where District_ID='"+holder.getDistrictid()+"' and Mandal_ID='"+holder.getMandalid()+"' and Village_ID='"+holder.getVillageid()+"' order by Habitation_Name";
            break; 
            }*/ 
          //  System.out.println("decisionParameter : "+decisionParameter);
            
            switch (decisionParameter) {
                case 1:
                    query = "select District_ID ,District_Name from tblDistrict_Details(nolock) order by District_Name";
                    pstmt = con.prepareStatement(query);
                    break;
                case 2:
                    query = "select Mandal_ID ,Mandal_Name from tblMandal_Details(nolock) where status='Active' and District_ID=? order by Mandal_Name";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, holder.getDistrictid());
                    break;
                case 3:
                    query = "select Assembly_ID ,Assembly_Name from tblAssembly_Details(nolock) where District_ID=? order by Assembly_Name";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, holder.getDistrictid());
                    break;
                case 4:
                    query = "select Panchayat_ID ,Panchayat_Name from tblPanchayat_Details(nolock) where status='Active' and District_ID=? and Mandal_ID=? order by Panchayat_Name";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, holder.getDistrictid());
                    pstmt.setString(2, holder.getMandalid());
                    break;
                case 5:
                    query = "select Village_ID ,Village_Name from tblVillage_Details(nolock) where District_ID=? and Mandal_ID=? order by Village_Name";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, holder.getDistrictid());
                    pstmt.setString(2, holder.getMandalid());
                    break;
                case 6:
                    query = "select Habitation_ID ,Habitation_Name from tblHabitation_Details(nolock) where District_ID=? and Mandal_ID=? and Village_ID=? and status='Active' order by Habitation_Name";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, holder.getDistrictid());
                    pstmt.setString(2, holder.getMandalid());
                    pstmt.setString(3,  holder.getVillageid());
                    break;
                case 7:
                    query = "select distinct p.village_id,p.village_name from tblVillage_Details(nolock) p left join tblHabitation_Details h on( p.district_id=p.district_id and p.mandal_id=p.mandal_id and p.village_id=h.village_id) "
                    		+ "where p.district_id=? and p.mandal_id=? and h.district_id=? and h.mandal_id=? and h.panchayat_id=? order by village_name";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, holder.getDistrictid());
                    pstmt.setString(2, holder.getMandalid());
                    pstmt.setString(3,  holder.getDistrictid());
                    pstmt.setString(4,  holder.getMandalid());
                    pstmt.setString(5,  holder.getPanchayatid());
                    break;
                case 8:
                    query = "select Habitation_ID ,Habitation_Name from tblHabitation_Details(nolock) where District_ID=? and Mandal_ID=? and Village_ID=? and status='Active' order by Habitation_Name";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, holder.getDistrictid());
                    pstmt.setString(2, holder.getMandalid());
                    pstmt.setString(3,  holder.getVillageid());
                    break;
                case 9:
                    query = "select Mandal_ID as urban_id,Mandal_Name as urban_name from tblMandal_Details(nolock) where status='Active' and District_ID=? and mandal_id>79 order by Mandal_Name";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, holder.getDistrictid());
                    break;
                case 10:
                    query = "select Mandal_ID as urban_id,Mandal_Name as urban_name from tblMandal_Details(nolock) where status='Active' and District_ID=? and mandal_id<80 order by Mandal_Name";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, holder.getDistrictid());
                    break;
                case 11:
                    query = "select habitation_code ,Habitation_Name from tblHabitation_Details(nolock) where District_ID=? and Mandal_ID=? and Village_ID=? and status='Active' order by Habitation_Name";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, holder.getDistrictid());
                    pstmt.setString(2, holder.getMandalid());
                    pstmt.setString(3,  holder.getVillageid());
                    break;
                case 12:
                    query = "select Habitation_ID ,Habitation_Name from tblHabitation_Details(nolock) where District_ID=? "
                            + " and Mandal_ID=?  and panchayat_id=? and Village_ID=? "
                            + "  order by Habitation_Name;";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, holder.getDistrictid());
                    pstmt.setString(2, holder.getMandalid());
                    pstmt.setString(3,  holder.getPanchayatid());
                    pstmt.setString(4,  holder.getVillageid());

               
                    break;

                case 13:

                    query = "select distinct p.village_id,p.village_name from tblVillage_Details(nolock) p left join tblHabitation_Details(nolock) h "
                            + "on( p.district_id=p.district_id and p.mandal_id=p.mandal_id and p.village_id=h.village_id) "
                            + "where p.district_id=? and p.mandal_id=?"
                            + " and h.district_id=? and h.mandal_id=? "
                            + "and h.panchayat_id=? order by village_name";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, holder.getDistrictid());
                    pstmt.setString(2, holder.getMandalid());
                    pstmt.setString(3,  holder.getDistrictid());
                    pstmt.setString(4,  holder.getMandalid());
                    pstmt.setString(5,  holder.getPanchayatid());
                    break;
            }



            
            rs = pstmt.executeQuery();

            while (rs.next()) {
                territory_id_list.add(rs.getString(1));
                territory_name_list.add(rs.getString(2)); 
                
              //  System.out.println("-->"+rs.getString(1)+"   "+rs.getString(2));
            }




        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTerritoryDetails", "TerritoryInformationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryInformationDAO", "getTerritoryDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTerritoryDetails", "TerritoryInformationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryInformationDAO", "getTerritoryDetails");
        } finally {
            territory_info.add(territory_name_list);
            territory_info.add(territory_id_list);
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return territory_info;
    }

    public static void updateTerritoryInformation(DataSource ds, TerritoryInformationDTO dto_obj) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        try {

            con = DBConnection.getConnection();
            String decisionParameter = dto_obj.getModifywhom();
            switch (Integer.parseInt(decisionParameter)) {
                case 1:
                    query = "update tblDistrict_Details set District_Name=? where District_ID=?";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, dto_obj.getModifiedname());
                    pstmt.setString(2, dto_obj.getDistrictid());
                    break;
                case 2:
                    query = "update tblMandal_Details set Mandal_Name=? where District_ID=? and Mandal_ID=?";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, dto_obj.getModifiedname());
                    pstmt.setString(2, dto_obj.getDistrictid());
                    pstmt.setString(3, dto_obj.getMandalid());
                    break;
                case 3:
                    query = "update tblAssembly_Details set Assembly_Name=? where District_ID=? and Assembly_ID=?";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, dto_obj.getModifiedname());
                    pstmt.setString(2, dto_obj.getDistrictid());
                    pstmt.setString(3, dto_obj.getAssemblyid());
                    break;
                case 4:
                    query = "update tblPanchayat_Details set Panchayat_Name=? where District_ID=? and Mandal_ID=? and Panchayat_ID=?";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, dto_obj.getModifiedname());
                    pstmt.setString(2, dto_obj.getDistrictid());
                    pstmt.setString(3, dto_obj.getMandalid());
                    pstmt.setString(4, dto_obj.getPanchayatid());
                    break;
                case 5:
                    query = "update tblVillage_Details set Village_Name=? where District_ID=? and Mandal_ID=? and Village_ID=?";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, dto_obj.getModifiedname());
                    pstmt.setString(2, dto_obj.getDistrictid());
                    pstmt.setString(3, dto_obj.getMandalid());
                    pstmt.setString(4, dto_obj.getVillageid());
                    break;
                case 6:
                    query = "update tblHabitation_Details set Habitation_Name=? where District_ID=? and Mandal_ID=? and Village_ID=? and Habitation_ID=?";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, dto_obj.getModifiedname());
                    pstmt.setString(2, dto_obj.getDistrictid());
                    pstmt.setString(3, dto_obj.getMandalid());
                    pstmt.setString(4, dto_obj.getVillageid());
                    pstmt.setString(5, dto_obj.getHabitationid());
                    break;
            }

            
            pstmt.execute();


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateTerritoryInformation", "TerritoryInformationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryInformationDAO", "updateTerritoryInformation");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateTerritoryInformation", "TerritoryInformationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryInformationDAO", "updateTerritoryInformation");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);


        }

    }

    public static void insertTerritoryInformation(DataSource ds, TerritoryInformationDTO dto_obj, String maxvalue) throws SADAREMDBException, SQLException {
        Connection con = null;
        //  PreparedStatement pstmt=null;
        CallableStatement cs = null;
        // Statement stmt=null;
        int value = 0;
        try {

            con = DBConnection.getConnection();
            String decisionParameter = dto_obj.getAddwhom();
            if (maxvalue == null) {
                maxvalue = "0";
            }
            value = Integer.parseInt(maxvalue);
            value++;


            switch (Integer.parseInt(decisionParameter)) {

                case 1:
                case 2:
                case 4:
                case 6:

                    if (value > 9) {
                        maxvalue = maxvalue.valueOf(value);
                    } else {
                        maxvalue = "0" + maxvalue.valueOf(value);
                    }
                    break;

                case 3:
                case 5:
                    if (value < 10) {
                        maxvalue = "00" + maxvalue.valueOf(value);
                    } else if (value >= 10 && value < 100) {
                        maxvalue = "0" + maxvalue.valueOf(value);
                    } else {
                        maxvalue = maxvalue.valueOf(value);
                    }
                    break;


            }


            switch (Integer.parseInt(decisionParameter)) {
                case 1:
                    cs = con.prepareCall("{Call SP_tblDistrict_Details_Insert(?,?,?,?)}");
                    cs.setString(1, maxvalue);
                    cs.setString(2, dto_obj.getNewterritory());
                    cs.setString(3, "role");
                    cs.setString(4, dto_obj.getIpaddress());
                    break;

                case 2:
                    cs = con.prepareCall("{Call SP_tblMandal_Details_Insert(?,?,?,?)}");

                    cs.setString(1, dto_obj.getDistrictid());
                    cs.setString(2, maxvalue);
                    cs.setString(3, dto_obj.getNewterritory());
                    cs.setString(4, dto_obj.getIpaddress());
                    break;

                case 3:
                    cs = con.prepareCall("{Call SP_tblAssembly_Details_Insert(?,?,?,?)}");
                    cs.setString(1, dto_obj.getDistrictid());
                    cs.setString(2, maxvalue);
                    cs.setString(3, dto_obj.getNewterritory());
                    cs.setString(4, dto_obj.getIpaddress());
                    break;

                case 4:
                    cs = con.prepareCall("{Call SP_tblPanchayat_Details_Insert(?,?,?,?,?)}");
                    cs.setString(1, dto_obj.getDistrictid());
                    cs.setString(2, dto_obj.getMandalid());
                    cs.setString(3, maxvalue);
                    cs.setString(4, dto_obj.getNewterritory());
                    cs.setString(5, dto_obj.getIpaddress());
                    break;

                case 5:
                    cs = con.prepareCall("{Call SP_tblVillage_Details_Insert(?,?,?,?,?)}");

                    cs.setString(1, dto_obj.getDistrictid());
                    cs.setString(2, dto_obj.getMandalid());
                    cs.setString(3, maxvalue);
                    cs.setString(4, dto_obj.getNewterritory());
                    cs.setString(5, dto_obj.getIpaddress());
                    break;

                case 6:
                    String habitation_code = dto_obj.getDistrictid() + dto_obj.getAssemblyid() + dto_obj.getMandalid() + dto_obj.getVillageid() + maxvalue + dto_obj.getPanchayatid();
                    cs = con.prepareCall("{Call SP_tblHabitation_Details_Insert(?,?,?,?,?,?,?,?,?)}");

                    cs.setString(1, dto_obj.getDistrictid());
                    cs.setString(2, dto_obj.getAssemblyid());
                    cs.setString(3, dto_obj.getMandalid());
                    cs.setString(4, dto_obj.getVillageid());
                    cs.setString(5, maxvalue);
                    cs.setString(6, dto_obj.getPanchayatid());
                    cs.setString(7, habitation_code);
                    cs.setString(8, dto_obj.getNewterritory());
                    cs.setString(9, dto_obj.getIpaddress());

            }

            cs.executeUpdate();
//            stmt=con.createStatement();
//            stmt.execute(query);


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertTerritoryInformation", "TerritoryInformationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryInformationDAO", "insertTerritoryInformation");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertTerritoryInformation", "TerritoryInformationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryInformationDAO", "insertTerritoryInformation");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cs);


        }

    }

    public static String getMaxValueFromTerritory(DataSource ds, TerritoryInformationDTO dto_obj) throws SADAREMDBException, SQLException {
        Connection con = null;
        // PreparedStatement pstmt=null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        String maxvalue = null;
        try {

            con = DBConnection.getConnection();
            String decisionParameter = dto_obj.getAddwhom();
            switch (Integer.parseInt(decisionParameter)) {
                case 1:
                    query = "select max(District_ID) from tblDistrict_Details";
                    pstmt = con.prepareStatement(query);
                    break;
                case 2:
                    query = "select max(Mandal_ID) from tblMandal_Details where District_ID=?";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, dto_obj.getDistrictid());
                    break;
                case 3:
                    query = "select max(Assembly_ID) from tblAssembly_Details";
                    pstmt = con.prepareStatement(query);
                    break;
                case 4:
                    query = "select max(Panchayat_ID) from tblPanchayat_Details where District_ID=? and Mandal_ID=?";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, dto_obj.getDistrictid());
                    pstmt.setString(2, dto_obj.getMandalid());
                    break;
                case 5:
                    query = "select max(Village_ID) from tblVillage_Details where District_ID=? and Mandal_ID=?";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, dto_obj.getDistrictid());
                    pstmt.setString(2, dto_obj.getMandalid());
                    break;
                case 6:
                    query = "select max(Habitation_ID) from tblHabitation_Details where District_ID=? and Assembly_ID=? and Mandal_ID=? and Village_ID=? and Panchayat_ID=?";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, dto_obj.getDistrictid());
                    pstmt.setString(2, dto_obj.getAssemblyid());
                    pstmt.setString(3, dto_obj.getMandalid());
                    pstmt.setString(4, dto_obj.getVillageid());
                    pstmt.setString(5, dto_obj.getPanchayatid());
                    break;
            }

            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                maxvalue = rs.getString(1);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaxValueFromTerritory", "TerritoryInformationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryInformationDAO", "getMaxValueFromTerritory");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaxValueFromTerritory", "TerritoryInformationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryInformationDAO", "getMaxValueFromTerritory");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return maxvalue;
    }
}
