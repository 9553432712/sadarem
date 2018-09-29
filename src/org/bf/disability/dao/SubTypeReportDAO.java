/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.form.SubTypeReportForm;

import com.tcs.sgv.common.util.DBConnection;

import javax.sql.DataSource;

/**
 *
 * @author 747577
 */
public class SubTypeReportDAO {

    public ArrayList gettypeOfDisability(DataSource ds) throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList typeOfDisabilityList = new ArrayList();
        ResultSet rs = null;
        Map map = null;
        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            // this query gets district id and district name
            String query = "select disability_id ,disability_Name from tblDisability_Details order by disability_Name";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                map = new HashMap();
                map.put("disabilityid", rs.getString(1));
                map.put("disabilityname", rs.getString(2));
                typeOfDisabilityList.add(map);
            }
        } //end of try block
        catch (Exception e) {
        }//end of catch block
        finally {
            if (con != null) {
                con.close();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

        }//end of finally block
        return typeOfDisabilityList;
    }

    public ArrayList getSubtypeOfDisability(DataSource ds, String disabilityId) throws SADAREMDBException, SQLException {
        Connection con = null;
        String query = null;
        PreparedStatement pstmt = null;
        ArrayList subTypeOfDisabilityList = new ArrayList();
        ResultSet rs = null;
        Map map = null;
        try {
            con = DBConnection.getConnection();
            // this query gets district id and district name
            if (disabilityId != null && disabilityId.equalsIgnoreCase("lds")) {
                disabilityId = "1";
                query = "select Sub_Disability_ID ,Sub_Disability_Name from tblSubDisability_Details where Disability_ID=? order by Sub_Disability_Name";
            } else if (disabilityId != null && disabilityId.equalsIgnoreCase("vids")) {
                disabilityId = "2";
                query = "select Sub_Disability_ID ,Sub_Disability_Name from tblSubDisability_Details where Disability_ID=? order by Sub_Disability_Name";
            } else if (disabilityId != null && disabilityId.equalsIgnoreCase("hids")) {
                disabilityId = "3";
                query = "select Sub_Disability_ID ,Sub_Disability_Name from tblSubDisability_Details where Disability_ID=? order by Sub_Disability_Name";
            } else if (disabilityId != null && disabilityId.equalsIgnoreCase("mrds")) {
                disabilityId = "4";
                query = "select Sub_Disability_ID ,Sub_Disability_Name from tblSubDisability_Details where Disability_ID=? order by Sub_Disability_Name";
            } else if (disabilityId != null && disabilityId.equalsIgnoreCase("mids")) {
                disabilityId = "5";
                query = "select Sub_Disability_ID ,Sub_Disability_Name from tblSubDisability_Details where Disability_ID=? order by Sub_Disability_Name";
            }
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, disabilityId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                map = new HashMap();
                map.put("subdisabilityid", rs.getString(1));
                String s1 = rs.getString(2).substring(0, 2);
                if (s1.equals("A-")) {
                    map.put("subdisabilityname", rs.getString(2).substring(4));
                } else {
                    map.put("subdisabilityname", rs.getString(2));
                }
                subTypeOfDisabilityList.add(map);
            }
        } //end of try block
        catch (Exception e) {
        }//end of catch block
        finally {
            if (con != null) {
                con.close();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }//end of finally block
        return subTypeOfDisabilityList;
    }

    public ArrayList getSubTypeReportDetails(DataSource ds, SubTypeReportForm subTypeReportForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        CallableStatement cs = null;
        String sql = null;
        ArrayList subTypeReportList = new ArrayList();
        String district_id = subTypeReportForm.getDistricts_id();
        String mandal_id = subTypeReportForm.getMandal_id();
        String village_id = subTypeReportForm.getVillage_id();
        String habitation_id = subTypeReportForm.getHabitation_id();
        String disabilityid = subTypeReportForm.getTypeOfDisability_id();
        String subTypeOfDisability_id = subTypeReportForm.getSubTypeOfDisability_id();
        Map map = null;
        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            if (district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) {
                cs = con.prepareCall("{Call LOCOMOTORSUBTYPEWISEMALEANDFEMALE_REPORT_DISTRICT_D_ALL(?,?)}");
                cs.setString(1, disabilityid);
                cs.setString(2, subTypeOfDisability_id);
                rs = cs.executeQuery();
                Integer total = 0;
                while (rs.next()) {
                    map = new HashMap();
                    map.put("district_id", rs.getString(1));
                    map.put("id", rs.getString(1));
                    map.put("name", rs.getString(2));
                    map.put("count", rs.getString(3));
                    total += Integer.parseInt(rs.getString(3));
                    map.put("total", total);
                    map.put("disability_id", disabilityid);
                    map.put("subTypeOfDisability_id", subTypeOfDisability_id);
                    subTypeReportList.add(map);
                }
            } else if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) {
                cs = con.prepareCall("{Call LOCOMOTORSUBTYPEWISEMALEANDFEMALE_REPORT_DISTRICT_D(?,?,?)}");
                cs.setString(1, district_id);
                cs.setString(2, disabilityid);
                cs.setString(3, subTypeOfDisability_id);
                rs = cs.executeQuery();
                Integer total = 0;
                while (rs.next()) {
                    map = new HashMap();
                    map.put("district_id", district_id);
                     map.put("id", rs.getString(1));
                    map.put("mandal_id", rs.getString(1));
                    map.put("name", rs.getString(2));
                    map.put("count", rs.getString(3));
                    total += Integer.parseInt(rs.getString(3));
                    map.put("total", total);
                    map.put("disability_id", disabilityid);
                    map.put("subTypeOfDisability_id", subTypeOfDisability_id);
                    subTypeReportList.add(map);
                }
            } else if (!district_id.equals("0") && !mandal_id.equals("0") && village_id.equals("0")) {
                cs = con.prepareCall("{Call LOCOMOTORSUBTYPEWISEMALEANDFEMALE_REPORT_MANDAL_D(?,?,?,?)}");
                cs.setString(1, district_id);
                cs.setString(2, mandal_id);
                cs.setString(3, disabilityid);
                cs.setString(4, subTypeOfDisability_id);
                rs = cs.executeQuery();
                Integer total = 0;
                while (rs.next()) {
                    map = new HashMap();
                    map.put("district_id", district_id);
                    map.put("mandal_id", mandal_id);
                     map.put("id", rs.getString(1));
                    map.put("village_id", rs.getString(1));
                    map.put("name", rs.getString(2));
                    map.put("count", rs.getString(3));
                    total += Integer.parseInt(rs.getString(3));
                    map.put("total", total);
                    map.put("disability_id", disabilityid);
                    map.put("subTypeOfDisability_id", subTypeOfDisability_id);
                    subTypeReportList.add(map);
                }
            } else if (!district_id.equals("0") && !mandal_id.equals("0") && !village_id.equals("0") && habitation_id.equals("0")) {
                cs = con.prepareCall("{Call LOCOMOTORSUBTYPEWISEMALEANDFEMALE_REPORT_VILLAGE_D(?,?,?,?,?,?)}");
                cs.setString(1, district_id);
                cs.setString(2, mandal_id);
                cs.setString(3, village_id);
                cs.setString(4, "0");
                cs.setString(5, disabilityid);
                cs.setString(6, subTypeOfDisability_id);
                rs = cs.executeQuery();
                Integer total = 0;
                while (rs.next()) {
                    map = new HashMap();
                    map.put("district_id", district_id);
                    map.put("mandal_id", mandal_id);
                    map.put("village_id", village_id);
                     map.put("id", rs.getString(1));
                    map.put("habitation_id", rs.getString(1));
                    map.put("name", rs.getString(2));
                    map.put("count", rs.getString(3));
                    total += Integer.parseInt(rs.getString(3));
                    map.put("total", total);
                    map.put("disability_id", disabilityid);
                    map.put("subTypeOfDisability_id", subTypeOfDisability_id);
                    subTypeReportList.add(map);
                }
            }

        } //end of try block
        catch (Exception e) {
        }//end of catch block
        finally {
            if (con != null) {
                con.close();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(cs);

        }//end of finally block
        return subTypeReportList;
    }

    public ArrayList getAddressDetails(DataSource ds, SubTypeReportForm subTypeReportForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        CallableStatement cs = null;
        ArrayList addressList = new ArrayList();
        String district_id = subTypeReportForm.getDistricts_id();
        String mandal_id = subTypeReportForm.getMandal_id();
        String village_id = subTypeReportForm.getVillage_id();
        String habitation_id = subTypeReportForm.getHabitation_id();
        String disabilityid = subTypeReportForm.getTypeOfDisability_id();
        String subTypeOfDisability_id = subTypeReportForm.getSubTypeOfDisability_id();
        Map map = null;
        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            cs = con.prepareCall("{Call LOCOMOTARINDIVIDUALDETAILS(?,?,?,?,?,?)}");
            cs.setString(1, district_id);
            cs.setString(2, mandal_id);
            cs.setString(3, village_id);
            cs.setString(4, habitation_id);
            cs.setString(5, disabilityid);
            cs.setString(6, subTypeOfDisability_id);
            rs = cs.executeQuery();
            while (rs.next()) {
                String caste = "", hno = "";
                map = new HashMap();

                map.put("sadaremcode", rs.getString(1));
                map.put("name", rs.getString(2));
                map.put("relationname", rs.getString(3));
                map.put("age", rs.getString(5));
                if (rs.getString(6).equals("1")) {
                    caste = "ST";
                } else if (rs.getString(6).equals("2")) {
                    caste = "SC";
                } else if (rs.getString(6).equals("3")) {
                    caste = "BC";
                } else if (rs.getString(6).equals("4")) {
                    caste = "OC";
                } else if (rs.getString(6).equals("5")) {
                    caste = "Minority";
                } else if (rs.getString(6).equals("6")) {
                    caste = "NA";
                }
                map.put("caste", caste);
                if (rs.getString(8).isEmpty()) {
                    hno = rs.getString(8);
                } else {
                    hno = rs.getString(8) + ",";
                }
                map.put("address", hno + rs.getString(12) + "," + rs.getString(11) + "," + rs.getString(10) + "," + rs.getString(9));
                addressList.add(map);
            }
        } //end of try block
        catch (Exception e) {
        }//end of catch block
        finally {
            if (con != null) {
                con.close();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(cs);

        }//end of finally block
        return addressList;
    }

    public String getMandalName(DataSource ds, SubTypeReportForm subTypeReportForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String mandalName = null;
        String query = null;
        try {
            if (subTypeReportForm.getDistricts_id() != null && subTypeReportForm.getMandal_id() != null && !subTypeReportForm.getMandal_id().equals("0")) {
                con = DBConnection.getConnection();
                
                query = "select Mandal_Name from tblMandal_Details where mandal_id=? and district_id=?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, subTypeReportForm.getMandal_id());
                pstmt.setString(2, subTypeReportForm.getDistricts_id());
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    mandalName = rs.getString("Mandal_Name");

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandalName", "SubTypeReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "SubTypeReportDAO", "getMandalName");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandalName", "SubTypeReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "SubTypeReportDAO", "getMandalName");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return mandalName;
    }

    public String getvillageName(DataSource ds, SubTypeReportForm subTypeReportForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String villageName = null;
        try {
            if (!subTypeReportForm.getVillage_id().equals("0")) {
                con = DBConnection.getConnection();
                
                String query = "select village_name from tblVillage_Details where village_id=? and mandal_id=? and district_id=?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, subTypeReportForm.getVillage_id());
                pstmt.setString(2, subTypeReportForm.getMandal_id());
                pstmt.setString(3, subTypeReportForm.getDistricts_id());
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    villageName = rs.getString("village_name");
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getvillageName", "SubTypeReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "SubTypeReportDAO", "getvillageName");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getvillageName", "SubTypeReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "SubTypeReportDAO", "getvillageName");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return villageName;
    }

    public String gethabitationName(DataSource ds, SubTypeReportForm subTypeReportForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String habitationName = null;
        try {
            if (!subTypeReportForm.getHabitation_id().equals("0")) {

                con = DBConnection.getConnection();
                
                String query = "select habitation_name from tblHabitation_Details where habitation_id=? and village_id=? and mandal_id=? and district_id=?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, subTypeReportForm.getHabitation_id());
                pstmt.setString(2, subTypeReportForm.getVillage_id());
                pstmt.setString(3, subTypeReportForm.getMandal_id());
                pstmt.setString(4, subTypeReportForm.getDistricts_id());
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    habitationName = rs.getString("habitation_name");
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "gethabitationName", "SubTypeReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "SubTypeReportDAO", "gethabitationName");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "gethabitationName", "SubTypeReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "SubTypeReportDAO", "gethabitationName");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return habitationName;
    }

    public String getdisabulityName(DataSource ds, SubTypeReportForm subTypeReportForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String disabilityName = null;
        try {
            if (!subTypeReportForm.getTypeOfDisability_id().equals("0")) {
                con = DBConnection.getConnection();
                
                String query = "select disability_name from tblDisability_Details where disability_id=? ";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, subTypeReportForm.getTypeOfDisability_id());
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    disabilityName = rs.getString("disability_name").replace("A.", "");
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getdisabulityName", "SubTypeReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "SubTypeReportDAO", "getdisabulityName");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getdisabulityName", "SubTypeReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "SubTypeReportDAO", "getdisabulityName");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return disabilityName;
    }

    public String getsubdisabulityName(DataSource ds, SubTypeReportForm subTypeReportForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sub_disabilityName = null;
        try {
            if (!subTypeReportForm.getSubTypeOfDisability_id().equals("0")) {
                con = DBConnection.getConnection();
                
                String query = "select sub_disability_name from tblSubDisability_Details where sub_disability_id=?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1,  subTypeReportForm.getSubTypeOfDisability_id());
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    sub_disabilityName = rs.getString("sub_disability_name");

                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getsubdisabulityName", "SubTypeReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "SubTypeReportDAO", "getsubdisabulityName");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getsubdisabulityName", "SubTypeReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "SubTypeReportDAO", "getsubdisabulityName");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return sub_disabilityName;
    }
}
