/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

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
import org.bf.disability.form.NoPhotosDataForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 728056
 */
public class NoPhotosDataDAO {

    public ArrayList getNophotosData(DataSource ds, NoPhotosDataForm photosDataForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        StringBuffer sql = new StringBuffer();
        ResultSet rs = null;
        HashMap map = null;
        ArrayList Nophotoslist = new ArrayList();
        try {
            con = DBConnection.getConnection();
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(photosDataForm.getFromDate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(photosDataForm.getToDate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);

            sql.append("select distinct p.pensioncard_no,t.person_code,p.Surname+' '+p.first_name,p.relation_name,"
                    + "p.rationcard_number , M.MANDAL_NAME,V.VILLAGE_NAME,H.Habitation_Name,p.Age_Years,p.House_Number,p.Gender from dbo.tblPerson_Personal_Details p with (nolock),"
                    + "dbo.tblPerson_Disability_Details z,dbo.tblPerson_Disability_TotalValue_Details t,TBLMANDAL_DETAILS M,TBLVILLAGE_DETAILS V,"
                    + "dbo.tblHabitation_Details H,tblcamp_details d where p.person_code=t.person_code and p.person_code = z.person_code and "
                    + "P.DISTRICT_ID = M.DISTRICT_ID AND P.MANDAL_ID = M.MANDAL_ID and "
                    + "P.DISTRICT_ID = V.DISTRICT_ID AND P.MANDAL_ID = V.MANDAL_ID AND P.VILLAGE_ID = V.VILLAGE_ID and "
                    + "p.habcode = h.habitation_code and z.hospital_name = d.name and z.hospital_address = d.address and z.venuename = d.venuename "
                    + "and p.status = 'Active' and z.status = 'Active' and t.status = 'Active' and p.districtid =?"
                    + " and p.person_code not in (select person_code from dbo.tblPerson_Photo_Details where  P.DISTRICT_ID=?" + " ) "
                    + "and (DATEADD(DD,0,DATEDIFF(DD,0,z.updated_date))) BETWEEN ? AND ?");
            

            if (photosDataForm.getCampId() != null && !photosDataForm.getCampId().equalsIgnoreCase("0")) {
                sql.append(" and d.camp_id = ?");
            }
           
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setString(1, photosDataForm.getDistrictId());
            pstmt.setString(2, photosDataForm.getDistrictId());
            pstmt.setString(3, fromdate);
            pstmt.setString(4, todate);
            if (photosDataForm.getCampId() != null && !photosDataForm.getCampId().equalsIgnoreCase("0"))
            {
            	pstmt.setString(5, photosDataForm.getCampId());
            }
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map = new HashMap();
                    map.put("Pensioncardno", rs.getString(1));
                    map.put("PersonCode", rs.getString(2));
                    map.put("firstname", rs.getString(3));
                    map.put("age", rs.getString(9));
                    map.put("relationname", rs.getString(4));
                    map.put("rationcardnumber", rs.getString(5));
                    if (rs.getString(10) != null && !rs.getString(10).equalsIgnoreCase("")) {
                        map.put("houseNumber", rs.getString(10));
                    } else {
                        map.put("houseNumber", "-");
                    }

                    map.put("MANDALNAME", rs.getString(6));
                    map.put("VILLAGENAME", rs.getString(7));
                    map.put("HabitationName", rs.getString(8));
                    if (rs.getString(11) != null) {
                        if (rs.getString(11).equals("2")) {
                            map.put("Gender", "Female");
                        } else {
                            map.put("Gender", "Male");
                        }

                    } else {
                        map.put("Gender", "-");
                    }

                    Nophotoslist.add(map);
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getNophotosData", "NoPhotosDataDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NoPhotosDataDAO", "getNophotosData");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getNophotosData", "NoPhotosDataDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NoPhotosDataDAO", "getNophotosData");
        } finally {
            DBConnection.closeStatement(pstmt);
        }
        return Nophotoslist;
    }

    public ArrayList getCampDetails(DataSource ds, String district_id) throws SADAREMDBException, SQLException {
        ArrayList campDetailsList = new ArrayList();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sql = null;
        Map campDetails = null;
        int i = 1;
        try {
            con = DBConnection.getConnection();
            
            sql = "select camp_id,name,address,venueName from dbo.tblCamp_Details where district_id=? order by name";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, district_id);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    campDetails = new HashMap();
                    campDetails.put("camp_id", rs.getString(1));
                    campDetails.put("camp_name", rs.getString(2) + ", " + rs.getString(3));
                    campDetailsList.add(campDetails);
                    i++;
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDetails", "NoPhotosDataDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NoPhotosDataDAO", "getCampDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDetails", "NoPhotosDataDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NoPhotosDataDAO", "getCampDetails");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }
        return campDetailsList;
    }
}
