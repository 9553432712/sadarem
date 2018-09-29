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
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.CampDailyReportDTO;
import org.bf.disability.dto.FunctionalNeedReportDTO;
import org.bf.disability.form.CampDailyReportForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 728056
 */
public class FunctionalReportDAO {

    /**
     * this method fetches getEducationWiseReport from database
     * @param ds
     * @param dailydisabilityandpercentagedto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getEducationWiseReport(DataSource ds,
            FunctionalNeedReportDTO educationwiseDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        ArrayList<FunctionalNeedReportDTO> educationwiseList = null;
        district_id = educationwiseDTO.getDistrict_id();
        mandal_id = educationwiseDTO.getMandal_id();
        village_id = educationwiseDTO.getVillage_id();
        String urban_id = educationwiseDTO.getUrban_id();
        educationwiseList = new ArrayList<FunctionalNeedReportDTO>();
        int total = 0;

        try {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(educationwiseDTO.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date fdates = new SimpleDateFormat("dd/mm/yyyy").parse(educationwiseDTO.getFromdate());
            String fromdates = new SimpleDateFormat("dd/mm/yyyy").format(fdates);

            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(educationwiseDTO.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
            Date tdates = new SimpleDateFormat("dd/mm/yyyy").parse(educationwiseDTO.getTodate());
            String todates = new SimpleDateFormat("dd/mm/yyyy").format(tdates);
            con = DBConnection.getConnection();

            if (district_id.equals("0")) {

                if (urban_id.equals("Urban")) {
                 
                    cstmt = con.prepareCall("{Call SP_DISTRICTWISEEDUCATION_REPORT_URBAN(?,?)}");
                } else if (urban_id.equals("Rural")) {
                 
                    cstmt = con.prepareCall("{Call SP_DISTRICTWISEEDUCATION_REPORT_RURAL(?,?)}");
                } else {
                
                    cstmt = con.prepareCall("{Call SP_DISTRICTWISEEDUCATION_REPORT(?,?)}");
                }
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO districteducationwiseDTO =
                            new FunctionalNeedReportDTO();
                    districteducationwiseDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    districteducationwiseDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    districteducationwiseDTO.setNotEntered(rs.getInt("NOTENTERED"));
                    districteducationwiseDTO.setIlliterate(rs.getInt("ILLITERATE"));
                    districteducationwiseDTO.setBelowTenth(rs.getInt("BELOWTENTH"));
                    districteducationwiseDTO.setTenth(rs.getInt("TENTH"));
                    districteducationwiseDTO.setIntermediate(rs.getInt("INTER"));
                    districteducationwiseDTO.setDiplomaOrITI(rs.getInt("DIPLOMA"));
                    districteducationwiseDTO.setGraduate(rs.getInt("GRADUATE"));
                    districteducationwiseDTO.setPostGraduate(rs.getInt("POSTGRADUATE"));
                    total = rs.getInt("ILLITERATE") + rs.getInt("BELOWTENTH") + rs.getInt("TENTH") + rs.getInt("INTER")
                            + rs.getInt("DIPLOMA") + rs.getInt("GRADUATE") + rs.getInt("POSTGRADUATE") + rs.getInt("NOTENTERED");
                    districteducationwiseDTO.setTotal(total);
                    districteducationwiseDTO.setUrban_id(urban_id);
                    districteducationwiseDTO.setFromdate(fromdates);
                    districteducationwiseDTO.setTodate(todates);
                    educationwiseList.add(districteducationwiseDTO);

                }


            } else if (mandal_id.equals("0")) {
              
                if (urban_id.equals("Urban")) {
                 
                    cstmt = con.prepareCall("{Call SP_MANDALWISEEDUCATION_REPORT_URBAN(?,?,?)}");
                } else if (urban_id.equals("Rural")) {
                 
                    cstmt = con.prepareCall("{Call SP_MANDALWISEEDUCATION_REPORT_RURAL(?,?,?)}");
                } else {
                  
                    cstmt = con.prepareCall("{Call SP_MANDALWISEEDUCATION_REPORT(?,?,?)}");
                }

                cstmt.setString(1, district_id);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO mandaleducationwiseDTO =
                            new FunctionalNeedReportDTO();
                    mandaleducationwiseDTO.setDistrict_id(district_id);
                    mandaleducationwiseDTO.setMandal_id(rs.getString("MANDAL_ID"));
                    mandaleducationwiseDTO.setVillage_id("a");
                    mandaleducationwiseDTO.setHabitation_id("a");
                    mandaleducationwiseDTO.setMandalName(rs.getString("MANDAL_NAME"));
                    mandaleducationwiseDTO.setNotEntered(rs.getInt("NOTENTERED"));
                    mandaleducationwiseDTO.setIlliterate(rs.getInt("ILLITERATE"));
                    mandaleducationwiseDTO.setBelowTenth(rs.getInt("BELOWTENTH"));
                    mandaleducationwiseDTO.setTenth(rs.getInt("TENTH"));
                    mandaleducationwiseDTO.setIntermediate(rs.getInt("INTER"));
                    mandaleducationwiseDTO.setDiplomaOrITI(rs.getInt("DIPLOMA"));
                    mandaleducationwiseDTO.setGraduate(rs.getInt("GRADUATE"));
                    mandaleducationwiseDTO.setPostGraduate(rs.getInt("POSTGRADUATE"));
                    total = rs.getInt("ILLITERATE") + rs.getInt("BELOWTENTH") + rs.getInt("TENTH") + rs.getInt("INTER")
                            + rs.getInt("DIPLOMA") + rs.getInt("GRADUATE") + rs.getInt("POSTGRADUATE") + rs.getInt("NOTENTERED");
                    mandaleducationwiseDTO.setTotal(total);
                    mandaleducationwiseDTO.setUrban_id(urban_id);
                    mandaleducationwiseDTO.setFromdate(fromdates);
                    mandaleducationwiseDTO.setTodate(todates);
                    educationwiseList.add(mandaleducationwiseDTO);

                }
            } else if (village_id.equals("0")) {
              

                cstmt = con.prepareCall("{Call SP_VILLAGEWISEEDUCATION_REPORT(?,?,?,?)}");

                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO villageeducationwiseDTO =
                            new FunctionalNeedReportDTO();
                    villageeducationwiseDTO.setDistrict_id(district_id);
                    villageeducationwiseDTO.setMandal_id(mandal_id);
                    villageeducationwiseDTO.setVillage_id(rs.getString("VILLAGE_ID"));
                    villageeducationwiseDTO.setHabitation_id("a");
                    villageeducationwiseDTO.setVillageName(rs.getString("VILLAGE_NAME"));
                    villageeducationwiseDTO.setNotEntered(rs.getInt("NOTENTERED"));
                    villageeducationwiseDTO.setIlliterate(rs.getInt("ILLITERATE"));
                    villageeducationwiseDTO.setBelowTenth(rs.getInt("BELOWTENTH"));
                    villageeducationwiseDTO.setTenth(rs.getInt("TENTH"));
                    villageeducationwiseDTO.setIntermediate(rs.getInt("INTER"));
                    villageeducationwiseDTO.setDiplomaOrITI(rs.getInt("DIPLOMA"));
                    villageeducationwiseDTO.setGraduate(rs.getInt("GRADUATE"));
                    villageeducationwiseDTO.setPostGraduate(rs.getInt("POSTGRADUATE"));
                    total = rs.getInt("ILLITERATE") + rs.getInt("BELOWTENTH") + rs.getInt("TENTH") + rs.getInt("INTER")
                            + rs.getInt("DIPLOMA") + rs.getInt("GRADUATE") + rs.getInt("POSTGRADUATE") + rs.getInt("NOTENTERED");
                    villageeducationwiseDTO.setTotal(total);
                    villageeducationwiseDTO.setUrban_id(urban_id);
                    villageeducationwiseDTO.setFromdate(fromdates);
                    villageeducationwiseDTO.setTodate(todates);
                    educationwiseList.add(villageeducationwiseDTO);

                }
            } else if (!village_id.equals("0")) {
                int NOTENTERED = 0;
                int ILLITERATE = 0;
                int BELOWTENTH = 0;
                int TENTH = 0;
                int INTER = 0;
                int DIPLOMA = 0;
                int GRADUATE = 0;
                int POSTGRADUATE = 0;
                int dtoTotal=0;
                cstmt = con.prepareCall("{Call SP_HABITATIONWISEEDUCATION_REPORT(?,?,?,?,?)}");


                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO habitationeducationwiseDTO =
                            new FunctionalNeedReportDTO();
                    habitationeducationwiseDTO.setDistrict_id(district_id);
                    habitationeducationwiseDTO.setMandal_id(mandal_id);
                    habitationeducationwiseDTO.setVillage_id(village_id);
                    habitationeducationwiseDTO.setHabitation_id(rs.getString("HABITATION_ID"));
                    habitationeducationwiseDTO.setHabitationName(rs.getString("HABITATION_NAME"));
                    habitationeducationwiseDTO.setNotEntered(rs.getInt("NOTENTERED"));
                    habitationeducationwiseDTO.setIlliterate(rs.getInt("ILLITERATE"));
                    habitationeducationwiseDTO.setBelowTenth(rs.getInt("BELOWTENTH"));
                    habitationeducationwiseDTO.setTenth(rs.getInt("TENTH"));
                    habitationeducationwiseDTO.setIntermediate(rs.getInt("INTER"));
                    habitationeducationwiseDTO.setDiplomaOrITI(rs.getInt("DIPLOMA"));
                    habitationeducationwiseDTO.setGraduate(rs.getInt("GRADUATE"));
                    habitationeducationwiseDTO.setPostGraduate(rs.getInt("POSTGRADUATE"));
                    total = rs.getInt("ILLITERATE") + rs.getInt("BELOWTENTH") + rs.getInt("TENTH") + rs.getInt("INTER")
                            + rs.getInt("DIPLOMA") + rs.getInt("GRADUATE") + rs.getInt("POSTGRADUATE") + rs.getInt("NOTENTERED");
                    habitationeducationwiseDTO.setTotal(total);
                    habitationeducationwiseDTO.setUrban_id(urban_id);
                    habitationeducationwiseDTO.setFromdate(fromdates);
                    habitationeducationwiseDTO.setTodate(todates);
                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATION_ID").equals("00") || rs.getString("HABITATION_ID").equals("01"))) {

                        if (rs.getString("HABITATION_ID").equals("00")) {

                            NOTENTERED = habitationeducationwiseDTO.getNotEntered();
                            ILLITERATE = habitationeducationwiseDTO.getIlliterate();
                            BELOWTENTH = habitationeducationwiseDTO.getBelowTenth();
                            TENTH = habitationeducationwiseDTO.getTenth();
                            INTER = habitationeducationwiseDTO.getIntermediate();
                            DIPLOMA = habitationeducationwiseDTO.getDiplomaOrITI();
                            GRADUATE = habitationeducationwiseDTO.getGraduate();
                            POSTGRADUATE = habitationeducationwiseDTO.getPostGraduate();
                            dtoTotal=habitationeducationwiseDTO.getTotal();
                        }
                        if (rs.getString("HABITATION_ID").equals("01")) {

                            habitationeducationwiseDTO.setNotEntered(NOTENTERED + habitationeducationwiseDTO.getNotEntered());
                            habitationeducationwiseDTO.setIlliterate(ILLITERATE + habitationeducationwiseDTO.getIlliterate());
                            habitationeducationwiseDTO.setBelowTenth(BELOWTENTH + habitationeducationwiseDTO.getBelowTenth());
                            habitationeducationwiseDTO.setTenth(TENTH = habitationeducationwiseDTO.getTenth());
                            habitationeducationwiseDTO.setIntermediate(INTER + habitationeducationwiseDTO.getIntermediate());
                            habitationeducationwiseDTO.setDiplomaOrITI(DIPLOMA + habitationeducationwiseDTO.getDiplomaOrITI());
                            habitationeducationwiseDTO.setGraduate(GRADUATE + habitationeducationwiseDTO.getGraduate());
                            habitationeducationwiseDTO.setPostGraduate(POSTGRADUATE + habitationeducationwiseDTO.getPostGraduate());
                            habitationeducationwiseDTO.setTotal(dtoTotal+habitationeducationwiseDTO.getTotal());
                            
                            educationwiseList.add(habitationeducationwiseDTO);
                        }
                    } else {
                        educationwiseList.add(habitationeducationwiseDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getEducationWiseReport", "FunctionalReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getEducationWiseReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getEducationWiseReport", "FunctionalReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getEducationWiseReport");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return educationwiseList;
    }

    /**
     * this method fetches getCasteWiseDetails from database
     * @param ds
     * @param dailydisabilityandpercentagedto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getCasteWiseDetails(DataSource ds, String district_id, String mandal_id, String village_id, String caste, FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {


        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        CallableStatement cstmt = null;
        ArrayList data = new ArrayList();
        HashMap results = null;
        String urbanId = functionalNeedReportDTO.getUrban_id();

        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();

            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("dd/mm/yyyy").format(fdate);

            Date fdates = new SimpleDateFormat("dd/mm/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdates = new SimpleDateFormat("mm/dd/yyyy").format(fdates);

            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("dd/mm/yyyy").format(tdate);

            Date tdates = new SimpleDateFormat("dd/mm/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todates = new SimpleDateFormat("mm/dd/yyyy").format(tdates);

            if (district_id == null) {
                district_id = "0";
            }
            if (mandal_id == null) {
                mandal_id = "0";
            }
            if (village_id == null) {
                village_id = "0";
            }
            if (caste == null) {
                caste = "0";
            }
            if (district_id.equals("0") && caste.equals("0")) {

                if (urbanId.equals("Urban")) {
                    cstmt = con.prepareCall("{Call [SP_report3.2_caste_Districtwise_report_URBAN](?,?)}");
                } else if (urbanId.equals("Rural")) {
                    cstmt = con.prepareCall("{Call [SP_report3.2_caste_Districtwise_report_rural](?,?)}");
                } else {
                    cstmt = con.prepareCall("{Call [SP_report3.2_caste_Districtwise](?,?)}");
                }
                cstmt.setString(1, fromdates);
                cstmt.setString(2, todates);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("name", rs.getString(2));
                    results.put("st", rs.getString(3));
                    results.put("sc", rs.getString(4));
                    results.put("bc", rs.getString(5));
                    results.put("oc", rs.getString(6));
                    results.put("mina", rs.getString(7));
                    results.put("na", rs.getString(8));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    results.put("urban_id", urbanId);
                    data.add(results);
                }
            }

            /** Getting the details from mandal wise and details */
            if (village_id.equals("0") && !district_id.equals("0") && mandal_id.equals("0") && caste.equals("0")) {

                if (urbanId.equals("Urban")) {
                    cstmt = con.prepareCall("{Call [SP_report3.2_caste_MANDALwise_report_URBAN](?,?,?)}");
                } else if (urbanId.equals("Rural")) {
                    cstmt = con.prepareCall("{Call [SP_report3.2_caste_MANDALwise_report_RURAL](?,?,?)}");
                } else {
                    cstmt = con.prepareCall("{Call [SP_report3.2_caste_MANDALwise_report](?,?,?)}");
                }
                cstmt.setString(1, district_id);
                cstmt.setString(2, fromdates);
                cstmt.setString(3, todates);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("mandal_id", rs.getString(2));
                    results.put("name", rs.getString(3));
                    results.put("st", rs.getString(4));
                    results.put("sc", rs.getString(5));
                    results.put("bc", rs.getString(6));
                    results.put("oc", rs.getString(7));
                    results.put("mina", rs.getString(8));
                    results.put("na", rs.getString(9));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    results.put("urban_id", urbanId);
                    data.add(results);

                }

            }

            /** Getting the details from village wise and details */
            if (village_id.equals("0") && !district_id.equals("0") && !mandal_id.equals("0") && caste.equals("0")) {


                cstmt = con.prepareCall("{Call [SP_report3.2_caste_Villagewise_report](?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, fromdates);
                cstmt.setString(4, todates);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("mandal_id", rs.getString(2));
                    results.put("village_id", rs.getString(3));
                    results.put("name", rs.getString(4));
                    results.put("st", rs.getString(5));
                    results.put("sc", rs.getString(6));
                    results.put("bc", rs.getString(7));
                    results.put("oc", rs.getString(8));
                    results.put("mina", rs.getString(9));
                    results.put("na", rs.getString(10));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    results.put("urban_id", urbanId);
                    data.add(results);
                }

            }


            /**Get Habitation details */
            if (!village_id.equals("0") && !district_id.equals("0") && !mandal_id.equals("0") && caste.equals("0")) {
                int st = 0;
                int sc = 0;
                int bc = 0;
                int oc = 0;
                int mina = 0;
                int na = 0;
                cstmt = con.prepareCall("{Call [SP_report3.2_caste_Habitationwise_report](?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromdates);
                cstmt.setString(5, todates);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("mandal_id", rs.getString(2));
                    results.put("village_id", rs.getString(3));
                    results.put("hab_id", rs.getString(4));
                    results.put("name", rs.getString(5));
                    results.put("st", rs.getString(6));
                    results.put("sc", rs.getString(7));
                    results.put("bc", rs.getString(8));
                    results.put("oc", rs.getString(9));
                    results.put("mina", rs.getString(10));
                    results.put("na", rs.getString(11));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    results.put("urban_id", urbanId);
                    if ((rs.getString(1).equals("16") && rs.getString(2).equals("84") && rs.getString(3).equals("004")) && (rs.getString(4).equals("00") || rs.getString(4).equals("01"))) {

                        if (rs.getString(4).equals("00")) {
                            st = rs.getInt(6);
                            sc = rs.getInt(7);
                            bc = rs.getInt(8);
                            oc = rs.getInt(9);
                            mina = rs.getInt(10);
                            na = rs.getInt(11);
                        }
                        if (rs.getString(4).equals("01")) {
                            st = st + rs.getInt(6);
                            sc = sc + rs.getInt(7);
                            bc = bc + rs.getInt(8);
                            oc = oc + rs.getInt(9);
                            mina = mina + rs.getInt(10);
                            na = na + rs.getInt(11);

                            results.put("st", st);
                            results.put("sc", sc);
                            results.put("bc", bc);
                            results.put("oc", oc);
                            results.put("mina", mina);
                            results.put("na", na);
                            results.put("fromdate", fromdate);
                            results.put("todate", todate);
                            results.put("urban_id", urbanId);
                            data.add(results);
                        }
                    } else {

                        data.add(results);
                    }

                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCasteWiseDetails", "FunctionalReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getCasteWiseDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCasteWiseDetails", "FunctionalReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getCasteWiseDetails");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                DBConnection.closeResultSet(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return data;
    }

    public ArrayList getAgeDetails(DataSource ds, String district_id, String mandal_id, String village_id, String fromAge, String toAge, FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        CallableStatement cstmt = null;
        ArrayList data = new ArrayList();
        HashMap results = null;
        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();

            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("dd/mm/yyyy").format(fdate);
            Date fdates = new SimpleDateFormat("dd/mm/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdates = new SimpleDateFormat("mm/dd/yyyy").format(fdates);
            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("dd/mm/yyyy").format(tdate);
            Date tdates = new SimpleDateFormat("dd/mm/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todates = new SimpleDateFormat("mm/dd/yyyy").format(tdates);
            String urbanId = functionalNeedReportDTO.getUrban_id();


            if (district_id.equals("0")) {

                data.clear();
                if (urbanId.equals("Urban")) {
                    cstmt = con.prepareCall("{Call [SP_Report3.3_Gender_Districtwise_report_URBAN](?,?,?,?)}");
                } else if (urbanId.equals("Rural")) {
                    cstmt = con.prepareCall("{Call [SP_report3.3_Gender_Districtwise_report_RURAL](?,?,?,?)}");

                } else {
                    cstmt = con.prepareCall("{Call [SP_report3.3_Gender_Districtwise_report](?,?,?,?)}");
                }

                cstmt.setString(1, fromAge);
                cstmt.setString(2, toAge);
                cstmt.setString(3, fromdates);
                cstmt.setString(4, todates);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("name", rs.getString(2));
                    results.put("total", rs.getString(3));
                    results.put("male", rs.getString(4));
                    results.put("female", rs.getString(5));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    results.put("fromage", fromAge);
                    results.put("toage", toAge);
                    results.put("urban_id", urbanId);
                    data.add(results);
                }

            }

            if (!district_id.equals("0") && mandal_id.equals("0")) {
                data.clear();
                if (urbanId.equals("Urban")) {
                    cstmt = con.prepareCall("{Call [SP_report3.3_Gender_MAndalwise_report_URBAN](?,?,?,?,?)}");
                } else if (urbanId.equals("Rural")) {
                    cstmt = con.prepareCall("{Call [SP_report3.3_Gender_MAndalwise_report_RURAL](?,?,?,?,?)}");
                } else {
                    cstmt = con.prepareCall("{Call [SP_report3.3_Gender_MAndalwise_report](?,?,?,?,?)}");
                }
                cstmt.setString(1, district_id);
                cstmt.setString(2, fromAge);
                cstmt.setString(3, toAge);
                cstmt.setString(4, fromdates);
                cstmt.setString(5, todates);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("mandal_id", rs.getString(2));
                    results.put("name", rs.getString(3));
                    results.put("total", rs.getString(4));
                    results.put("male", rs.getString(5));
                    results.put("female", rs.getString(6));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    results.put("fromage", fromAge);
                    results.put("toage", toAge);
                    results.put("urban_id", urbanId);
                    data.add(results);
                }

            }

            if (!district_id.equals("0") && !mandal_id.equals("0")) {

                data.clear();
                cstmt = con.prepareCall("{Call [SP_report3.3_Gender_Villagewise_report](?,?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, fromAge);
                cstmt.setString(4, toAge);
                 cstmt.setString(5, fromdates);
                cstmt.setString(6, todates);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("mandal_id", rs.getString(2));
                    results.put("village_id", rs.getString(3));
                    results.put("name", rs.getString(4));
                    results.put("total", rs.getString(5));
                    results.put("male", rs.getString(6));
                    results.put("female", rs.getString(7));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    results.put("fromage", fromAge);
                    results.put("toage", toAge);
                    results.put("urban_id", urbanId);
                    data.add(results);
                }

            }

            if (!district_id.equals("0") && !mandal_id.equals("0") && !village_id.equals("0")) {

                data.clear();

                int total = 0;
                int male = 0;
                int female = 0;
                cstmt = con.prepareCall("{Call [SP_report3.3_Gender_Habitationwise_report](?,?,?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromAge);
                cstmt.setString(5, toAge);
                 cstmt.setString(6, fromdates);
                cstmt.setString(7, todates);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("mandal_id", rs.getString(2));
                    results.put("village_id", rs.getString(3));
                    results.put("habitation_id", rs.getString(4));
                    results.put("name", rs.getString(5));
                    results.put("total", rs.getString(6));
                    results.put("male", rs.getString(7));
                    results.put("female", rs.getString(8));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    results.put("fromage", fromAge);
                    results.put("toage", toAge);
                    results.put("urban_id", urbanId);
                    if ((rs.getString(1).equals("16") && rs.getString(2).equals("84") && rs.getString(3).equals("004")) && (rs.getString(4).equals("00") || rs.getString(4).equals("01"))) {

                        if (rs.getString(4).equals("00")) {
                            total = rs.getInt(6);
                            male = rs.getInt(7);
                            female = rs.getInt(8);
                        }
                        if (rs.getString(4).equals("01")) {

                            total = total + rs.getInt(6);
                            male = male + rs.getInt(7);
                            female = female + rs.getInt(8);

                            results.put("total", total);
                            results.put("male", male);
                            results.put("female", female);
                            results.put("fromdate", fromdate);
                            results.put("todate", todate);
                            results.put("fromage", fromAge);
                            results.put("toage", toAge);
                            results.put("urban_id", urbanId);
                            data.add(results);
                        }
                    } else {
                        data.add(results);
                    }
                }

            }



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAgeDetails", "FunctionalReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getAgeDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAgeDetails", "FunctionalReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getAgeDetails");
        } finally {

            try {
                if (con != null) {
                    con.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                DBConnection.closeResultSet(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return data;
    }

    /**
     *
     * @param datasource
     * @param districtid
     * @param mandalid
     * @param villageid
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getMaritalStatusDetails(DataSource datasource, String districtId, String mandalId, String villageId, String habCode, FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        Statement stmt = null;
        ArrayList maritalList = new ArrayList();
        ResultSet rs = null;
        CallableStatement cstmt = null;
        HashMap results = null;
        try 
        {
            con = DBConnection.getConnection();
            stmt = con.createStatement();

            String urbanId = functionalNeedReportDTO.getUrban_id();
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("dd/mm/yyyy").format(fdate);

            Date fdates = new SimpleDateFormat("dd/mm/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdates = new SimpleDateFormat("mm/dd/yyyy").format(fdates);

            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("dd/mm/yyyy").format(tdate);

            Date tdates = new SimpleDateFormat("dd/mm/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todates = new SimpleDateFormat("mm/dd/yyyy").format(tdates);

            if (districtId.equals("0") && mandalId.equals("0") && villageId.equals("0")) 
            {

                if (urbanId.equals("Urban"))
                {
                    cstmt = con.prepareCall("{Call [SP_report3.4_Maritalstatus_Districtwise_report_URBAN](?,?)}");


                } else if (urbanId.equals("Rural"))
                {

                    cstmt = con.prepareCall("{Call [SP_report3.4_Maritalstatus_Districtwise_report_RURAL](?,?)}");
                } 
                else 
                {

                    cstmt = con.prepareCall("{Call [SP_report3.4_Maritalstatus_Districtwise_report](?,?)}");

                }
                  cstmt.setString(1, fromdates);
                cstmt.setString(2, todates);

                rs = cstmt.executeQuery();
                while (rs.next())
                {
                    results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("name", rs.getString(2));
                    results.put("married", rs.getString(3));
                    results.put("unmarried", rs.getString(4));
                    results.put("divorcee", rs.getString(5));
                    results.put("widow", rs.getString(6));
                    results.put("widower", rs.getString(7));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    results.put("urban_id", urbanId);
                    maritalList.add(results);
                }

            } else if (!districtId.equals("0") && mandalId.equals("0") && villageId.equals("0"))
            {
                if (urbanId.equals("Urban")) 
                {

                    cstmt = con.prepareCall("{Call [SP_report3.4_Maritalstatus_Mandalwise_report_URBAN](?,?,?)}");

                } else if (urbanId.equals("Rural"))
                {
                    cstmt = con.prepareCall("{Call [SP_report3.4_Maritalstatus_Mandalwise_report_RURAL](?,?,?)}");
                } 
                else 
                {
                    cstmt = con.prepareCall("{Call [SP_report3.4_Maritalstatus_Mandalwise_report](?,?,?)}");
                }

                 cstmt.setString(1, districtId);
                 cstmt.setString(2, fromdates);
                cstmt.setString(3, todates);
                rs = cstmt.executeQuery();
                while (rs.next())
                {
                    results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("mandal_id", rs.getString(2));
                    results.put("name", rs.getString(3));
                    results.put("married", rs.getString(4));
                    results.put("unmarried", rs.getString(5));
                    results.put("divorcee", rs.getString(6));
                    results.put("widow", rs.getString(7));
                    results.put("widower", rs.getString(8));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                     results.put("districtId", districtId);
                    results.put("urban_id", urbanId);
                    maritalList.add(results);
                }

            } 
            else if (!districtId.equals("0") && !mandalId.equals("0") && villageId.equals("0")) 
            {
                cstmt = con.prepareCall("{Call [SP_Report3.4_Maritalstatus_Villagewise_report](?,?,?,?)}");

                cstmt.setString(1, districtId);
              	cstmt.setString(2, mandalId);
              	cstmt.setString(3, fromdates);
                cstmt.setString(4, todates);
                
                rs = cstmt.executeQuery();
                
                while (rs.next()) 
                {
                    results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("mandal_id", rs.getString(2));
                    results.put("village_id", rs.getString(3));
                    results.put("name", rs.getString(4));
                    results.put("married", rs.getString(5));
                    results.put("unmarried", rs.getString(6));
                    results.put("divorcee", rs.getString(7));
                    results.put("widow", rs.getString(8));
                    results.put("widower", rs.getString(9));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                     results.put("districtId", districtId);
                    results.put("mandalId", mandalId);
                    results.put("urban_id", urbanId);
                    maritalList.add(results);
                }
            } 
            else if (!districtId.equals("0") && !mandalId.equals("0") && !villageId.equals("0")) 
            {
                int married = 0;
                int unmarried = 0;
                int divorcee = 0;
                int widow = 0;
                int widower = 0;
                cstmt = con.prepareCall("{Call [SP_Report3.4_Maritalstatus_Habitationwise_report](?,?,?,?,?)}");


                cstmt.setString(1, districtId);
                cstmt.setString(2, mandalId);
                cstmt.setString(3, villageId);
                cstmt.setString(4, fromdates);
                cstmt.setString(5, todates);
                rs = cstmt.executeQuery();
                while (rs.next())
                {
                    results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("mandal_id", rs.getString(2));
                    results.put("village_id", rs.getString(3));
                    results.put("habitation_id", rs.getString(4));
                    results.put("name", rs.getString(5));
                    results.put("married", rs.getString(6));
                    results.put("unmarried", rs.getString(7));
                    results.put("divorcee", rs.getString(8));
                    results.put("widow", rs.getString(9));
                    results.put("widower", rs.getString(10));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    results.put("urban_id", urbanId);


                    if ((rs.getString(1).equals("16") && rs.getString(2).equals("84") && rs.getString(3).equals("004")) && (rs.getString(4).equals("00") || rs.getString(4).equals("01"))) {

                        if (rs.getString(4).equals("00")) 
                        {
                            married = rs.getInt(6);
                            unmarried = rs.getInt(7);
                            divorcee = rs.getInt(8);
                            widow = rs.getInt(9);
                            widower = rs.getInt(10);
                        }
                        
                        if (rs.getString(4).equals("01")) 
                        {

                            married = married + rs.getInt(6);
                            unmarried = unmarried + rs.getInt(7);
                            divorcee = divorcee + rs.getInt(8);
                            widow = widow + rs.getInt(9);
                            widower = widower + rs.getInt(10);

                            results.put("married", married);
                            results.put("unmarried", unmarried);
                            results.put("divorcee", divorcee);
                            results.put("widow", widow);
                            results.put("widower", widower);
                            results.put("fromdate", fromdate);
                            results.put("todate", todate);
                            results.put("urban_id", urbanId);
                            maritalList.add(results);
                        }
                    } 
                    else 
                    {
                        maritalList.add(results);
                    }
                }
            }




        } catch (SQLException sqlEx)
        {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getMaritalStatusDetails", "FunctionalReportDAO", "DataBase");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getMaritalStatusDetails");
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getMaritalStatusDetails", "FunctionalReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getMaritalStatusDetails");
        }
        finally 
        {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
        }
        return maritalList;
    }

    public ArrayList getEmpWiseDetails(DataSource ds, String district_id, String mandal_id, String village_id, FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
       CallableStatement cstmt = null;
        ArrayList data = new ArrayList();
        int sno = 1;
        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();

            if (district_id == null) {
                district_id = "0";
            }
            if (mandal_id == null) {
                mandal_id = "0";
            }
            if (village_id == null) {
                village_id = "0";
            }
            String urbanId = "0";
            if (functionalNeedReportDTO.getUrban_id() != null) {
                urbanId = functionalNeedReportDTO.getUrban_id();
            }
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("dd/mm/yyyy").format(fdate);
            Date fdates = new SimpleDateFormat("dd/mm/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdates = new SimpleDateFormat("mm/dd/yyyy").format(fdates);
            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("dd/mm/yyyy").format(tdate);
            Date tdates = new SimpleDateFormat("dd/mm/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todates = new SimpleDateFormat("mm/dd/yyyy").format(tdates);
            if (district_id.equals("0")) {
                if (urbanId.equals("Urban")) {
                    cstmt = con.prepareCall("{Call [SP_report3.5_EMployment_Districtwise_report_URBAN](?,?)}");
                } else if (urbanId.equals("Rural")) {

                    cstmt = con.prepareCall("{Call [SP_report3.5_EMployment_Districtwise_report_RURAL](?,?)}");
                } else {

                    cstmt = con.prepareCall("{Call [SP_report3.5_EMployment_Districtwise_report](?,?)}");
                }



                 cstmt.setString(1, fromdates);
                cstmt.setString(2, todates);
                 rs = cstmt.executeQuery();
                while (rs.next()) {

                    HashMap results = new HashMap();
                    results.put("sno", sno++);
                    results.put("district_id", rs.getString(1));
                    results.put("name", rs.getString(2));
                    results.put("gov", rs.getString(3));
                    results.put("pri", rs.getString(4));
                    results.put("self", rs.getString(5));
                    results.put("unemp", rs.getString(6));
                    results.put("wage", rs.getString(7));
                    results.put("na", rs.getString(8));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    results.put("urban_id", urbanId);
                    data.add(results);
                }
            }
            /** Getting the details from mandal wise and details */
            if (village_id.equals("0") && !district_id.equals("0") && mandal_id.equals("0")) {
                if (urbanId.equals("Urban"))
                {

                      cstmt = con.prepareCall("{Call [SP_report3.5_EMployment_Mandalwise_report_URBAN](?,?,?)}");
                               } else if (urbanId.equals("Rural")) {

                    cstmt = con.prepareCall("{Call [SP_report3.5_EMployment_Mandalwise_report_RURAL](?,?,?)}");
                                  } else {

                    cstmt = con.prepareCall("{Call [SP_report3.5_EMployment_Mandalwise_report](?,?,?)}");


                                  }
  cstmt.setString(1, district_id);
               cstmt.setString(2, fromdates);
                cstmt.setString(3, todates);


             
                 rs = cstmt.executeQuery();
                while (rs.next()) {
                    HashMap results = new HashMap();
                    results.put("sno", sno++);
                    results.put("district_id", rs.getString(1));
                    results.put("mandal_id", rs.getString(2));
                    results.put("name", rs.getString(3));
                    results.put("gov", rs.getString(4));
                    results.put("pri", rs.getString(5));
                    results.put("self", rs.getString(6));
                    results.put("unemp", rs.getString(7));
                    results.put("wage", rs.getString(8));
                    results.put("na", rs.getString(9));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    results.put("urban_id", urbanId);
                    data.add(results);
                }
            }
            /** Getting the details from village wise and details */
            if (village_id.equals("0") && !district_id.equals("0") && !mandal_id.equals("0")) {
               cstmt = con.prepareCall("{Call [SP_report3.5_EMployment_Villagewise_report](?,?,?,?)}");

                 cstmt.setString(1, district_id);
                   cstmt.setString(2, mandal_id);
               cstmt.setString(3, fromdates);
                cstmt.setString(4, todates);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    HashMap results = new HashMap();
                    results.put("sno", sno++);
                    results.put("district_id", rs.getString(1));
                    results.put("mandal_id", rs.getString(2));
                    results.put("village_id", rs.getString(3));
                    results.put("name", rs.getString(4));
                    results.put("gov", rs.getString(5));
                    results.put("pri", rs.getString(6));
                    results.put("self", rs.getString(7));
                    results.put("unemp", rs.getString(8));
                    results.put("wage", rs.getString(9));
                    results.put("na", rs.getString(10));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    results.put("urban_id", urbanId);
                    data.add(results);
                }
            }
            /**Get Habitation details */
            if (!village_id.equals("0") && !district_id.equals("0") && !mandal_id.equals("0")) {
                int sNO = sno;
                int gov = 0;
                int pri = 0;
                int self = 0;
                int unemp = 0;
                int wage = 0;
                int na = 0;

               cstmt = con.prepareCall("{Call [SP_report3.5_EMployment_Habitationwise_report](?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromdates);
                cstmt.setString(5, todates);
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    HashMap results = new HashMap();
                    results.put("sno", sno++);
                    results.put("district_id", rs.getString(1));
                    results.put("mandal_id", rs.getString(2));
                    results.put("village_id", rs.getString(3));
                    results.put("hab_id", rs.getString(4));
                    results.put("name", rs.getString(5));
                    results.put("gov", rs.getString(6));
                    results.put("pri", rs.getString(7));
                    results.put("self", rs.getString(8));
                    results.put("unemp", rs.getString(9));
                    results.put("wage", rs.getString(10));
                    results.put("na", rs.getString(11));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    results.put("urban_id", urbanId);
                    if ((rs.getString(1).equals("16") && rs.getString(2).equals("84") && rs.getString(3).equals("004")) && (rs.getString(4).equals("00") || rs.getString(4).equals("01"))) {

                        if (rs.getString(4).equals("00")) {

                            gov = rs.getInt(6);
                            pri = rs.getInt(7);
                            self = rs.getInt(8);
                            unemp = rs.getInt(9);
                            wage = rs.getInt(10);
                            na = rs.getInt(11);
                        }
                        if (rs.getString(4).equals("01")) {
                            sno = sNO;
                            gov = gov + rs.getInt(6);
                            pri = pri + rs.getInt(7);
                            self = self + rs.getInt(8);
                            unemp = unemp + rs.getInt(9);
                            wage = wage + rs.getInt(10);
                            na = na + rs.getInt(11);
                            results.put("sno", sno);
                            results.put("gov", gov);
                            results.put("pri", pri);
                            results.put("self", self);
                            results.put("unemp", unemp);
                            results.put("wage", wage);
                            results.put("na", na);
                            results.put("fromdate", fromdate);
                            results.put("todate", todate);
                            results.put("urban_id", urbanId);
                            data.add(results);
                        }
                    } else {

                        data.add(results);
                    }
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getEmpWiseDetails", "FunctionalReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getEmpWiseDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getEmpWiseDetails", "FunctionalReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getMaritalStatusPersonalDetails");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                DBConnection.closeResultSet(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }
     public ArrayList getDisabilityCount(DataSource ds, String district_id, String mandal_id, String village_id, String fdate, String tdate, String disabilityid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        CallableStatement cs = null;
        String sql = null;
        // ArrayList data = new ArrayList();
        String query = null;
        String tablename = null, colu = null, totall = null;
        //   String type = null;
        PreparedStatement ps = null;
        Object col = null;
        NumberFormat form = NumberFormat.getInstance();
        form.setMinimumIntegerDigits(2);
        form.setMinimumFractionDigits(2);
        form.setMaximumFractionDigits(2);
        HashMap h1 = null;
        String[] aa = {"c.Congenital", "c.Hereditary", "c.BirthInjury", "c.Birth_Asphyxia", "c.high_fever",
            "c.Epilepsy", "c.Disease", "c.Malnutrition", "c.Accident ", "c.Other_Cause"
        };
        String kk = null;
        ArrayList a = new ArrayList();
        String co = null;
        double male = 0, female = 0, tot = 0;
        if (district_id == null) {
            district_id = "0";
        }
        if (mandal_id == null) {
            mandal_id = "0";
        }
        if (village_id == null) {
            village_id = "0";
        }
        try {
            if (fdate != null && fdate.contains("-")) {
                //fdate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fdate));
                ;
            } else {
                fdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
            }
            if (tdate != null && tdate.contains("-")) {
                ;
            } else {
                tdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(tdate));
            }
            con = DBConnection.getConnection();
            stmt = con.createStatement();



            if (disabilityid.equals("1")) {

                if (district_id.equals("0")) {
                    cs = con.prepareCall("{Call LOCOMOTORSUBTYPEWISEMALEANDFEMALE_REPORTS(?,?,?)}");
                    cs.setInt(1, Integer.parseInt(disabilityid));
                    cs.setString(2, fdate);
                    cs.setString(3, tdate);

                } else if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) {
                    cs = con.prepareCall("{Call LOCOMOTORSUBTYPEWISEMALEANDFEMALE_REPORT_DISTRICT(?,?,?,?)}");
                    cs.setString(1, district_id);
                    cs.setInt(2, Integer.parseInt(disabilityid));
                    cs.setString(3, fdate);
                    cs.setString(4, tdate);
                } else if (!district_id.equals("0") && !mandal_id.equals("0") && village_id.equals("0")) {
                    cs = con.prepareCall("{Call LOCOMOTORSUBTYPEWISEMALEANDFEMALE_REPORT_MANDAL(?,?,?,?,?)}");
                    cs.setString(1, district_id);
                    cs.setString(2, mandal_id);
                    cs.setInt(3, Integer.parseInt(disabilityid));
                    cs.setString(4, fdate);
                    cs.setString(5, tdate);
                } else if (!district_id.equals("0") && district_id != null && !mandal_id.equals("0") && !village_id.equals("0")) {
                    cs = con.prepareCall("{Call LOCOMOTORSUBTYPEWISEMALEANDFEMALE_REPORT_VILLAGE(?,?,?,?,?,?)}");
                    cs.setString(1, district_id);
                    cs.setString(2, mandal_id);
                    cs.setString(3, village_id);
                    cs.setInt(4, Integer.parseInt(disabilityid));
                    cs.setString(5, fdate);
                    cs.setString(6, tdate);
                }

                rs = cs.executeQuery();
                while (rs.next()) {
                    h1 = new HashMap();
                    male = rs.getInt(4);
                    female = rs.getInt(5);
                    tot = male + female;
                    //  h1.put("tablet", tablename);
                    //  h1.put("columnt", totall);
                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }
                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }//(pdd.sub_disability_id like ''5,%'' or pdd.sub_disability_id=''5'' and p.gender=2)
                    colu = "%28pdd.sub_disability_id like %27" + rs.getInt(2) + "%2C%25" + "%27 or  pdd.sub_disability_id like %27%25%2C" + rs.getInt(2) + "%27 or pdd.sub_disability_id=%27" + rs.getInt(2) + "%27%29 and p.gender=1";
                    h1.put("tablem", " ");
                    h1.put("columnm", colu);
                    colu = "%28pdd.sub_disability_id like %27" + rs.getInt(2) + "%2C%25" + "%27 or pdd.sub_disability_id like %27%25%2C" + rs.getInt(2) + "%27 or pdd.sub_disability_id=%27" + rs.getInt(2) + "%27%29 and p.gender=2";

                    h1.put("tablef", " ");
                    h1.put("columnf", colu);
                    colu = "%28pdd.sub_disability_id like %27" + rs.getInt(2) + "%2C%25" + "%27 or pdd.sub_disability_id like %27%25%2C" + rs.getInt(2) + "%27 or pdd.sub_disability_id=%27" + rs.getInt(2) + "%27%29 ";
                    h1.put("tablet", " ");
                    h1.put("columnt", colu);
                    co = rs.getString(3);
                    if (co.contains("'")) {
                        co = co.replace("'", "");
                    }
                    h1.put("col", co);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }

            }
            if (district_id.equals("0")) 
            {
                for (int ll = 0; ll < aa.length; ll++)
                {
                    col = aa[ll];
                    h1 = new HashMap();
                    if (ll < 9) 
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c with(nolock)  inner join "
                                + " tblPerson_personal_details p with(nolock)  on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd with(nolock) on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t with(nolock)  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=? and " + col + "=1 and p.gender=1 "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?"
                                + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=1 and " + col + "=1  and pdd.Disability_ID=" + disabilityid;
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=" + disabilityid;
                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    } 
                    else 
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c with(nolock)  inner join "
                                + " tblPerson_personal_details p with(nolock) on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd with(nolock)  on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t with(nolock)  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=? and " + col + "!='' and p.gender=1 "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?"
                                + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'";
                        tablename = "join tblPerson_Causes_of_Disability_Details c with(nolock)  on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=1 and " + col + "!=%27%27   and pdd.Disability_ID=" + disabilityid;
                        totall = col + "!=%27%27  and c.status=%27active%27 and pdd.Disability_ID=" + disabilityid;

                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    }
                    ps = con.prepareStatement(query);  
                    
                    ps.setString(1, disabilityid); 
                    ps.setString(2, fdate); 
                    ps.setString(3, tdate); 
                    
                    
                    
                    
                    rs = ps.executeQuery(); 
                    if (rs.next()) {
                        male = (rs.getInt(1));
                    }//c.Other_Cause!= ''
                    
                    if (ll < 9) 
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=?  and " + col + "=1 and p.gender=2 "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ?  AND ?"
                                + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=2 and " + col + "=1  and pdd.Disability_ID=" + disabilityid;
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=" + disabilityid;
                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    } 
                    else 
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=?  and " + col + "!='' and p.gender=2 "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?"
                                + " and  c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' ";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=2 and " + col + "!=%27%27   and pdd.Disability_ID=" + disabilityid;
                        totall = col + "!=%27%27  and c.status=%27active%27 and pdd.Disability_ID=" + disabilityid;

                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    }
                    ps = con.prepareStatement(query); 
                    ps.setString(1, disabilityid); 
                    ps.setString(2, fdate); 
                    ps.setString(3, tdate); 
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getInt(1);
                    }
                    tot = male + female;
                    h1.put("tablet", tablename);
                    h1.put("columnt", totall);
                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }
                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }

                    co = (String) col;
                    co = co.substring(2, co.length());
                    if (co.contains("high_fever")) {
                        co = "Meningitis";
                    }
                    h1.put("col", co);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);
                }
                if (disabilityid != null && disabilityid.equalsIgnoreCase("3")) {
                    h1 = new HashMap();

                    query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                            + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                            + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where pdd.Disability_ID=?  and a.H_can=1 and p.gender=1 and  "
                            + " (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                            + "BETWEEN   ? AND ?"
                            + " and  c.status='Active' and p.status='Active' and a.status='Active' and pdd.status='Active' and t.status='Active'";

                    tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                            + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                    colu = " p.gender=1 and a.status=%27active%27 and c.status=%27active%27 and  a.H_can=1 and pdd.Disability_ID=" + disabilityid;
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query); 
                    ps.setString(1, disabilityid); 
                    ps.setString(2, fdate); 
                    ps.setString(3, tdate); 
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        male = rs.getDouble(1);

                    }

                    query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                            + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                            + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where pdd.Disability_ID=? and a.H_can=1 and p.gender=2 and  "
                            + "  (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                            + "BETWEEN   ? AND ?"
                            + " and c.status='Active' and p.status='Active' and a.status='Active' and pdd.status='Active' and t.status='Active'";
                    tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                            + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                    colu = " p.gender=2 and  a.status=%27active%27 and c.status=%27active%27 and a.H_can=1 and pdd.Disability_ID=" + disabilityid;
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query); 
                    ps.setString(1, disabilityid); 
                    ps.setString(2, fdate); 
                    ps.setString(3, tdate); 
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getDouble(1);

                    }
                    tot = male + female;
                    colu = "  a.H_can=1 and a.status=%27active%27 and c.status=%27active%27and pdd.Disability_ID=" + disabilityid;

                    h1.put("tablet", tablename);
                    h1.put("columnt", colu);
                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }
                    h1.put("col", "H- can perform work by Hearing ");
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }
                String[] age = {" 0 and 40 ", " 41 and 60 ", "61 and 80 ", "81"};
                String[] percentag = {"Below 40%", "41% to 60%", "61% to 80%", "81% to 100%"};
                for (int ll = 0; ll < age.length; ll++) {
                    h1 = new HashMap();
                    col = age[ll];
                    if (ll <= 2) {
                        query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                                + " on(p.person_code=pdd.person_code ) JOIN tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code)"
                                + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active'  and t.totalDisability between " + col + " and "
                                + " p.gender=1 and pdd.disability_id=?  and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                                + " BETWEEN   ? AND ?"; 
                        tablename = "";
                        colu = " p.gender=1 and t.totalDisability between " + col + "  and pdd.Disability_ID=" + disabilityid;
                        totall = " t.totalDisability between " + col + "  and pdd.Disability_ID=" + disabilityid;
                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    } else {
                        query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                                + " on(p.person_code=pdd.person_code ) JOIN tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code)"
                                + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active'  and t.totalDisability >=81 and "
                                + " p.gender=1 and pdd.disability_id=?  and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                                + " BETWEEN   ? AND ? ";
                        totall = " t.totalDisability between >=81  and pdd.Disability_ID=" + disabilityid;
                        tablename = "";

                        colu = " p.gender=1 and t.totalDisability >=81  and pdd.Disability_ID=" + disabilityid;

                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    }
                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1, disabilityid); 
                    ps.setString(2, fdate); 
                    ps.setString(3, tdate); 
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        male = rs.getDouble(1);

                    }
                    if (ll <= 2) {
                        query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                                + " on(p.person_code=pdd.person_code ) JOIN tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code)"
                                + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active'  and t.totalDisability between " + col + " and "
                                + " p.gender=2 and pdd.disability_id=?  and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                                + " BETWEEN   ? AND ?";
                        tablename = "";
 
                        colu = " p.gender=2 and t.totalDisability between " + col + "  and pdd.Disability_ID=" + disabilityid;
                        totall = " t.totalDisability between " + col + "  and pdd.Disability_ID=" + disabilityid;
                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    } else {
                        query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                                + " on(p.person_code=pdd.person_code ) JOIN tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code)"
                                + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active'  and t.totalDisability >=81 and "
                                + " p.gender=2 and pdd.disability_id=?  and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                                + " BETWEEN   ? AND ?";
                        tablename = "";

                        colu = " p.gender=2 and t.totalDisability >=81  and pdd.Disability_ID=" + disabilityid;
                        totall = " t.totalDisability between >=81  and pdd.Disability_ID=" + disabilityid;

                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    }

                    ps = con.prepareStatement(query);
                    
                    ps.setString(1, disabilityid); 
                    ps.setString(2, fdate); 
                    ps.setString(3, tdate); 
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getDouble(1);

                    }
                    tot = male + female;
                    h1.put("tablet", tablename);
                    h1.put("columnt", totall);
                    h1.put("male", (int) male);

                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }
                    h1.put("female", (int) female);

                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }
                    h1.put("col", percentag[ll]);
                    kk = percentag[ll];
                    kk = kk.replace("%", "%25");
                    h1.put("coll", kk);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);
                }

            } else if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) {
                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();
                    if (ll < 9) {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=? and " + col + "=1 and p.gender=1 and district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?"
                                + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'";

                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=1 and " + col + "=1  and pdd.Disability_ID=" + disabilityid;
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=" + disabilityid;
                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    } else {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=? and " + col + "!='' and p.gender=1 and district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?"
                                + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=1 and " + col + "!=%27%27   and pdd.Disability_ID=" + disabilityid;
                        totall = col + "!=%27%27  and c.status=%27active%27 and pdd.Disability_ID=" + disabilityid;

                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    }
                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1, disabilityid); 
                    ps.setString(2, district_id); 
                    ps.setString(3, fdate); 
                    ps.setString(4, tdate);  
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        male = (rs.getInt(1));
                    }//c.Other_Cause!= ''
                    if (ll < 9) {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=? and " + col + "=1 and p.gender=2 and district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ? "
                                + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=2 and " + col + "=1  and pdd.Disability_ID=" + disabilityid;
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=" + disabilityid;
                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    } else {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=? and " + col + "!='' and p.gender=2 and district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?"
                                + " and  c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' ";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=2 and " + col + "!=%27%27   and pdd.Disability_ID=" + disabilityid;
                        totall = col + "!=%27%27  and c.status=%27active%27 and pdd.Disability_ID=" + disabilityid;

                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    }
                    ps = con.prepareStatement(query);   
                    
                    ps.setString(1, disabilityid); 
                    ps.setString(2, district_id); 
                    ps.setString(3, fdate); 
                    ps.setString(4, tdate); 
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getInt(1);
                    }


                    tot = male + female;

                    h1.put("tablet", tablename);
                    h1.put("columnt", totall);

                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }

                    co = (String) col;
                    co = co.substring(2, co.length());
                    if (co.contains("high_fever")) {
                        co = "Meningitis";
                    }
                    h1.put("col", co);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);
                }
                if (disabilityid != null && disabilityid.equalsIgnoreCase("3")) {
                    h1 = new HashMap();
                    query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                            + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                            + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where pdd.Disability_ID=? and a.H_can=1 and p.gender=1 and  "
                            + " district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                            + "BETWEEN   ? AND ?"
                            + " and  c.status='Active' and p.status='Active' and a.status='Active' and pdd.status='Active' and t.status='Active'";
                    tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                            + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                    colu = " p.gender=1 and a.status=%27active%27 and c.status=%27active%27 and  a.H_can=1 and pdd.Disability_ID=" + disabilityid;
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1, disabilityid); 
                    ps.setString(2, district_id); 
                    ps.setString(3, fdate); 
                    ps.setString(4, tdate); 
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        male = rs.getDouble(1);

                    }

                    query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                            + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                            + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where pdd.Disability_ID=? and a.H_can=1 and p.gender=2 and  "
                            + " district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                            + "BETWEEN   ? AND ?"
                            + " and c.status='Active' and p.status='Active' and a.status='Active' and pdd.status='Active' and t.status='Active'";
                    tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                            + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                    colu = " p.gender=2 and  a.status=%27active%27 and c.status=%27active%27 and a.H_can=1 and pdd.Disability_ID=" + disabilityid;
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query);  
                    
                    ps.setString(1, disabilityid); 
                    ps.setString(2, district_id); 
                    ps.setString(3, fdate); 
                    ps.setString(4, tdate); 
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getDouble(1);

                    }
                    tot = male + female;
                    colu = "  a.H_can=1 and a.status=%27active%27 and c.status=%27active%27and pdd.Disability_ID=" + disabilityid;

                    h1.put("tablet", tablename);
                    h1.put("columnt", colu);
                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }


                    h1.put("col", "H- can perform work by Hearing ");
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }
                String[] age = {" 0 and 40 ", " 41 and 60 ", "61 and 80 ", "81"};
                String[] percentag = {"Below 40%", "41% to 60%", "61% to 80%", "81% to 100%"};

                for (int ll = 0; ll < age.length; ll++) {
                    h1 = new HashMap();
                    col = age[ll];
                    if (ll <= 2) {
                        query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                                + " on(p.person_code=pdd.person_code ) JOIN tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code)"
                                + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active'  and t.totalDisability between " + col + " and "
                                + " p.gender=1 and pdd.disability_id=? and district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                                + " BETWEEN   ? AND ?";
                        tablename = "";

                        colu = " p.gender=1 and t.totalDisability between " + col + "  and pdd.Disability_ID=" + disabilityid;
                        totall = " t.totalDisability between " + col + "  and pdd.Disability_ID=" + disabilityid;
                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    } else {
                        query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                                + " on(p.person_code=pdd.person_code ) JOIN tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code)"
                                + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active'  and t.totalDisability >=81 and "
                                + " p.gender=1 and pdd.disability_id=? and district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                                + " BETWEEN   ? AND ?";
                        totall = " t.totalDisability between >=81  and pdd.Disability_ID=" + disabilityid;
                        tablename = "";

                        colu = " p.gender=1 and t.totalDisability >=81  and pdd.Disability_ID=" + disabilityid;

                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    }
                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1, disabilityid); 
                    ps.setString(2, district_id); 
                    ps.setString(3, fdate); 
                    ps.setString(4, tdate); 
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        male = rs.getDouble(1);

                    }
                    if (ll <= 2) {
                        query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                                + " on(p.person_code=pdd.person_code ) JOIN tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code)"
                                + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active'  and t.totalDisability between " + col + " and "
                                + " p.gender=2 and pdd.disability_id=? and district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                                + " BETWEEN   ? AND ?";
                        tablename = "";

                        colu = " p.gender=2 and t.totalDisability between " + col + "  and pdd.Disability_ID=" + disabilityid;
                        totall = " t.totalDisability between " + col + "  and pdd.Disability_ID=" + disabilityid;
                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    } else {
                        query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                                + " on(p.person_code=pdd.person_code ) JOIN tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code)"
                                + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active'  and t.totalDisability >=81 and "
                                + " p.gender=2 and pdd.disability_id=? and district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                                + " BETWEEN   ? AND ?";
                        totall = " t.totalDisability >=81  and pdd.Disability_ID=" + disabilityid;
                        tablename = "";

                        colu = " p.gender=2 and t.totalDisability >=81  and pdd.Disability_ID=" + disabilityid;

                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    }

                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1, disabilityid); 
                    ps.setString(2, district_id); 
                    ps.setString(3, fdate); 
                    ps.setString(4, tdate); 
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getDouble(1);

                    }
                    tot = male + female;
                    h1.put("tablet", tablename);
                    h1.put("columnt", totall);

                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }
                    h1.put("col", percentag[ll]);
                    kk = percentag[ll];
                    kk = kk.replace("%", "%25");
                    h1.put("coll", kk);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);


                }

            } 
            else if (!district_id.equals("0") && !mandal_id.equals("0") && village_id.equals("0"))
            {


                for (int ll = 0; ll < aa.length; ll++)
                {
                    col = aa[ll];
                    h1 = new HashMap();
                    if (ll < 9)
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=? and " + col + "=1 and p.gender=1 and district_id=? and mandal_id=?"
                                		+ " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?"
                                + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=1 and " + col + "=1  and pdd.Disability_ID=" + disabilityid;
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=" + disabilityid;
                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    } 
                    else 
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=? and " + col + "!='' and p.gender=1 and district_id=? and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?"
                                + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'";
                    }

                    tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                    colu = " c.status=%27active%27 and p.gender=1 and " + col + "!=%27%27   and pdd.Disability_ID=" + disabilityid;
                    totall = col + "!=%27%27  and c.status=%27active%27 and pdd.Disability_ID=" + disabilityid;

                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1, disabilityid); 
                    ps.setString(2, district_id);  
                    ps.setString(3, mandal_id); 
                    ps.setString(4, fdate); 
                    ps.setString(5, tdate); 
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {

                        male = (rs.getInt(1));
                    }//c.Other_Cause!= ''

                    if (ll < 9) {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=? and " + col + "=1 and p.gender=2 and district_id=? and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?"
                                + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=2 and " + col + "=1  and pdd.Disability_ID=" + disabilityid;
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=" + disabilityid;
                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    } else {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=? and " + col + "!='' and p.gender=2 and district_id=? and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?"
                                + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'";
                    }
                    tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                    colu = " c.status=%27active%27 and p.gender=2 and " + col + "!=%27%27   and pdd.Disability_ID=" + disabilityid;
                    totall = col + "!=%27%27  and c.status=%27active%27 and pdd.Disability_ID=" + disabilityid;

                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query); 
                    
                    
                    ps.setString(1, disabilityid); 
                    ps.setString(2, district_id);  
                    ps.setString(3, mandal_id); 
                    ps.setString(4, fdate); 
                    ps.setString(5, tdate); 
                    
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getInt(1);
                    }
                    tot = male + female;
                    h1.put("tablet", tablename);
                    h1.put("columnt", totall);
                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }
                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }

                    co = (String) col;
                    co = co.substring(2, co.length());
                    if (co.contains("high_fever")) {
                        co = "Meningitis";
                    }
                    h1.put("col", co);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }
                if (disabilityid != null && disabilityid.equalsIgnoreCase("3")) {
                    h1 = new HashMap();
                    query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                            + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                            + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where pdd.Disability_ID=? and a.H_can=1 and p.gender=1 and  "
                            + " district_id=? and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                            + "BETWEEN   ? AND ?"
                            + " and c.status='Active' and p.status='Active' and a.status='Active' and pdd.status='Active' and t.status='Active'";
                    tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                            + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                    colu = " p.gender=1 and a.status=%27active%27 and c.status=%27active%27 and  a.H_can=1 and pdd.Disability_ID=" + disabilityid;
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query); 
                    
                    
                    ps.setString(1, disabilityid); 
                    ps.setString(2, district_id);  
                    ps.setString(3, mandal_id); 
                    ps.setString(4, fdate); 
                    ps.setString(5, tdate); 
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        male = rs.getDouble(1);

                    }

                    query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                            + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                            + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where pdd.Disability_ID=? and a.H_can=1 and p.gender=2 and  "
                            + " district_id=? and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                            + "BETWEEN   ? AND ?"
                            + " and c.status='Active' and p.status='Active' and a.status='Active' and pdd.status='Active' and t.status='Active'";
                    tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                            + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                    colu = " p.gender=2 and  a.status=%27active%27 and c.status=%27active%27 and a.H_can=1 and pdd.Disability_ID=" + disabilityid;
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1, disabilityid); 
                    ps.setString(2, district_id);  
                    ps.setString(3, mandal_id); 
                    ps.setString(4, fdate); 
                    ps.setString(5, tdate); 
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getDouble(1);

                    }
                    tot = male + female;
                    colu = "  a.H_can=1 and a.status=%27active%27 and c.status=%27active%27and pdd.Disability_ID=" + disabilityid;
                    h1.put("tablet", tablename);
                    h1.put("columnt", colu);
                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }


                    h1.put("col", "H- can perform work by Hearing ");
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);
                }

                String[] age = {" 0 and 40 ", " 41 and 60 ", "61 and 80 ", "81"};
                String[] percentag = {"Below 40%", "41% to 60%", "61% to 80%", "81% to 100%"};

                for (int ll = 0; ll < age.length; ll++) {
                    h1 = new HashMap();
                    col = age[ll];
                    if (ll <= 2) {
                        query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                                + " on(p.person_code=pdd.person_code ) JOIN tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code)"
                                + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active'  and t.totalDisability between " + col + " and "
                                + " p.gender=1 and pdd.disability_id=? and district_id=? and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                                + " BETWEEN   ? AND ?";
                        tablename = "";

                        colu = " p.gender=1 and t.totalDisability between " + col + "  and pdd.Disability_ID=" + disabilityid;
                        totall = " t.totalDisability between " + col + "  and pdd.Disability_ID=" + disabilityid;
                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    } else {
                        query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                                + " on(p.person_code=pdd.person_code ) JOIN tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code)"
                                + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active'  and t.totalDisability >=81 and "
                                + " p.gender=1 and pdd.disability_id=? and district_id=? and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                                + " BETWEEN   ? AND ?";
                        totall = " t.totalDisability between >=81  and pdd.Disability_ID=" + disabilityid;
                        tablename = "";

                        colu = " p.gender=1 and t.totalDisability >=81  and pdd.Disability_ID=" + disabilityid;

                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    }
                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1, disabilityid); 
                    ps.setString(2, district_id);  
                    ps.setString(3, mandal_id); 
                    ps.setString(4, fdate); 
                    ps.setString(5, tdate); 
                    
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        male = rs.getDouble(1);

                    }
                    if (ll <= 2) {
                        query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                                + " on(p.person_code=pdd.person_code ) JOIN tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code)"
                                + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active'  and t.totalDisability between " + col + " and "
                                + " p.gender=2 and pdd.disability_id= ? and district_id=? and mandal_id=?"
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                                + " BETWEEN   ? AND ?";
                        tablename = "";

                        colu = " p.gender=2 and t.totalDisability between " + col + "  and pdd.Disability_ID=" + disabilityid;
                        totall = " t.totalDisability between " + col + "  and pdd.Disability_ID=" + disabilityid;
                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    } else {
                        query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                                + " on(p.person_code=pdd.person_code ) JOIN tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code)"
                                + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active'  and t.totalDisability >=81 and "
                                + " p.gender=2 and pdd.disability_id=? and district_id=? and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                                + " BETWEEN   ? AND ?";
                        totall = " t.totalDisability  >=81  and pdd.Disability_ID=" + disabilityid;
                        tablename = "";

                        colu = " p.gender=2 and t.totalDisability >=81  and pdd.Disability_ID=" + disabilityid;

                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    }

                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1, disabilityid); 
                    ps.setString(2, district_id);  
                    ps.setString(3, mandal_id); 
                    ps.setString(4, fdate); 
                    ps.setString(5, tdate); 
                    
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getDouble(1);

                    }
                    tot = male + female;
                    h1.put("tablet", tablename);
                    h1.put("columnt", totall);

                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }
                    h1.put("col", percentag[ll]);
                    kk = percentag[ll];
                    kk = kk.replace("%", "%25");
                    h1.put("coll", kk);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);
                }

            } else if (!district_id.equals("0") && district_id != null && !mandal_id.equals("0") && !village_id.equals("0")) {

                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();
                    if (ll < 9) {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=? and " + col + "=1 and p.gender=1 and district_id=? and mandal_id=? and village_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?"
                                + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=1 and " + col + "=1  and pdd.Disability_ID=" + disabilityid;
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=" + disabilityid;
                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    } else {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=? and " + col + "!='' and p.gender=1 and district_id=? and mandal_id=? and village_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?"
                                + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=1 and " + col + "!=%27%27   and pdd.Disability_ID=" + disabilityid;
                        totall = col + "!=%27%27  and c.status=%27active%27 and pdd.Disability_ID=" + disabilityid;

                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    }


                    ps = con.prepareStatement(query);  
                    
                    ps.setString(1, disabilityid); 
                    ps.setString(2, district_id);  
                    ps.setString(3, mandal_id); 
                    ps.setString(4, village_id); 
                    ps.setString(5, fdate); 
                    ps.setString(6, tdate); 
                    
                    
                    rs = ps.executeQuery(); 
                    if (rs.next()) {

                        male = (rs.getInt(1));
                    }//c.Other_Cause!= ''

                    if (ll < 9) {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=? and " + col + "=1 and p.gender=2 and district_id=? and mandal_id=? and village_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?"
                                + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=2 and " + col + "=1  and pdd.Disability_ID=" + disabilityid;
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=" + disabilityid;
                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    } else {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=? and " + col + "!='' and p.gender=2 and district_id=? and mandal_id=? and village_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?"
                                + " and  c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=2 and " + col + "!=%27%27   and pdd.Disability_ID=" + disabilityid;
                        totall = col + "!=%27%27  and c.status=%27active%27 and pdd.Disability_ID=" + disabilityid;

                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    }
                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1, disabilityid); 
                    ps.setString(2, district_id);  
                    ps.setString(3, mandal_id); 
                    ps.setString(4, village_id); 
                    ps.setString(5, fdate); 
                    ps.setString(6, tdate); 
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getInt(1);
                    }


                    tot = male + female;

                    h1.put("tablet", tablename);
                    h1.put("columnt", totall);
                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }

                    co = (String) col;
                    co = co.substring(2, co.length());
                    if (co.contains("high_fever")) {
                        co = "Meningitis";
                    }
                    h1.put("col", co);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);
                }
                if (disabilityid != null && disabilityid.equalsIgnoreCase("3")) {
                    h1 = new HashMap();
                    query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                            + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                            + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where pdd.Disability_ID=? and a.H_can=1 and p.gender=1 and  "
                            + " district_id=? and mandal_id=? and village_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                            + "BETWEEN   ? AND ?"
                            + " and c.status='Active' and p.status='Active' and a.status='Active' and pdd.status='Active' and t.status='Active'";
                    tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                            + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                    colu = " p.gender=1 and a.status=%27active%27 and c.status=%27active%27 and  a.H_can=1 and pdd.Disability_ID=" + disabilityid;
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1, disabilityid); 
                    ps.setString(2, district_id);  
                    ps.setString(3, mandal_id); 
                    ps.setString(4, village_id); 
                    ps.setString(5, fdate); 
                    ps.setString(6, tdate); 
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        male = rs.getDouble(1);

                    }

                    query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                            + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                            + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where pdd.Disability_ID=? and a.H_can=1 and p.gender=2 and  "
                            + " district_id=? and mandal_id=? and village_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                            + " BETWEEN   ? AND ?"
                            + " and c.status='Active' and p.status='Active' and a.status='Active' and pdd.status='Active' and t.status='Active'";

                    tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                            + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                    colu = " p.gender=2 and  a.status=%27active%27 and c.status=%27active%27 and a.H_can=1 and pdd.Disability_ID=" + disabilityid;
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query);   
                    
                    ps.setString(1, disabilityid); 
                    ps.setString(2, district_id);  
                    ps.setString(3, mandal_id); 
                    ps.setString(4, village_id); 
                    ps.setString(5, fdate); 
                    ps.setString(6, tdate); 
                    
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getDouble(1);

                    }
                    tot = male + female;

                    colu = "  a.H_can=1 and a.status=%27active%27 and c.status=%27active%27and pdd.Disability_ID=" + disabilityid;

                    h1.put("tablet", tablename);
                    h1.put("columnt", colu);

                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }


                    h1.put("col", "H- can perform work by Hearing ");
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }
                String[] age = {" 0 and 40 ", " 41 and 60 ", "61 and 80 ", "81"};
                String[] percentag = {"Below 40%", "41% to 60%", "61% to 80%", "81% to 100%"};

                for (int ll = 0; ll < age.length; ll++) 
                {
                    h1 = new HashMap();
                    col = age[ll];
                    if (ll <= 2) 
                    {
                        query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                                + " on(p.person_code=pdd.person_code ) JOIN tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code)"
                                + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active'  and t.totalDisability between " + col + " and "
                                + " p.gender=1 and pdd.disability_id=? and district_id=? and mandal_id=? and village_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                                + " BETWEEN   ? AND ?";
                        tablename = "";

                        colu = " p.gender=1 and t.totalDisability between " + col + "  and pdd.Disability_ID=" + disabilityid;
                        totall = " t.totalDisability between " + col + "  and pdd.Disability_ID=" + disabilityid;
                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    } 
                    else 
                    {
                        query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                                + " on(p.person_code=pdd.person_code ) JOIN tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code)"
                                + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active'  and t.totalDisability >=81 and "
                                + " p.gender=1 and pdd.disability_id=? and district_id=? and mandal_id=? and village_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                                + "BETWEEN   ? AND ?";

                        totall = " t.totalDisability >=81  and pdd.Disability_ID=" + disabilityid;
                        tablename = "";

                        colu = " p.gender=1 and t.totalDisability >=81  and pdd.Disability_ID=" + disabilityid;

                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    }
                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1, disabilityid); 
                    ps.setString(2, district_id);  
                    ps.setString(3, mandal_id); 
                    ps.setString(4, village_id); 
                    ps.setString(5, fdate); 
                    ps.setString(6, tdate); 
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        male = rs.getDouble(1);

                    }
                    if (ll <= 2) 
                    {
                        query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                                + " on(p.person_code=pdd.person_code ) JOIN tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code)"
                                + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active'  and t.totalDisability between " + col + " and "
                                + " p.gender=2 and pdd.disability_id=? and district_id=? and mandal_id=? and village_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                                + "BETWEEN   ? AND ?";
                        tablename = "";

                        colu = " p.gender=2 and t.totalDisability between " + col + "  and pdd.Disability_ID=" + disabilityid;
                        totall = " t.totalDisability between " + col + "  and pdd.Disability_ID=" + disabilityid;
                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    } 
                    else
                    {
                        query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                                + " on(p.person_code=pdd.person_code ) JOIN tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code)"
                                + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active'  and t.totalDisability >=81 and "
                                + " p.gender=2 and pdd.disability_id=? and district_id=? and mandal_id=? and village_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                                + "BETWEEN   ? AND ?";

                        totall = " t.totalDisability >=81  and pdd.Disability_ID=" + disabilityid;
                        tablename = "";

                        colu = " p.gender=2 and t.totalDisability >=81  and pdd.Disability_ID=" + disabilityid;

                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    }

                    ps = con.prepareStatement(query);  
                    
                    ps.setString(1, disabilityid); 
                    ps.setString(2, district_id);  
                    ps.setString(3, mandal_id); 
                    ps.setString(4, village_id); 
                    ps.setString(5, fdate); 
                    ps.setString(6, tdate); 
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getDouble(1);

                    }
                    tot = male + female;
                    h1.put("tablet", tablename);
                    h1.put("columnt", totall);

                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }
                    h1.put("col", percentag[ll]);
                    kk = percentag[ll];
                    kk = kk.replace("%", "%25");
                    h1.put("coll", kk);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);



                }



            }



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getMaritalStatusPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getMaritalStatusPersonalDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(ps);
            DBConnection.closeStatement(cs);
        }

        return a;
    }

    public ArrayList getAppellateageCount(DataSource ds, String district_id, String mandal_id, String village_id, String fdate, String tdate, String fage, String tage)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList data = new ArrayList();
        CallableStatement cstmt = null;

        try {


            if (fdate != null && fdate.contains("-")) {
                //fdate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fdate));
            } else {
                fdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
            }

            if (tdate != null && tdate.contains("-")) {
            } else {
                tdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(tdate));
            }
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            if (district_id == null) {
                district_id = "0";
            }
            if (mandal_id == null) {
                mandal_id = "0";
            }
            if (village_id == null) {
                village_id = "0";
            }//and createddate <='2011-08-29' and createddate>= '2011-08-28
//(DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  '08/28/2011' AND '08/28/2011'
            if (district_id.equals("0")) {
                //  cstmt = con.prepareCall("{Call REPORTFORAPPEALAUTHORITYRESULTSTATUS(?,?)}");

                cstmt = con.prepareCall("{Call REPORTFORAPPEALAUTHORITYDISTRICTAGERESULTSTATUS(?,?,?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fdate);
                cstmt.setString(5, tdate);
                cstmt.setString(6, fage);
                cstmt.setString(7, tage);
                rs = cstmt.executeQuery();

                while (rs.next()) {

                    HashMap results = new HashMap();
                    results.put("district_id", rs.getString(2));
                    results.put("name", rs.getString(3));
                    results.put("treg", rs.getString(4));
                    results.put("tass", rs.getString(5));
                    results.put("teli", rs.getString(6));
                    results.put("trej", rs.getString(7));
                    results.put("tar", rs.getString(8));
                    results.put("tdr", rs.getString(9));
                    results.put("fDate", fdate);
                    results.put("tDate", tdate);
                    results.put("fage", fage);
                    results.put("tage", tage);

                    data.add(results);
                }
            }
            /** Getting the details from mandal wise and details */
            if (village_id.equals("0") && !district_id.equals("0") && mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call REPORTFORAPPEALAUTHORITYDISTRICTAGERESULTSTATUS(?,?,?,?,?,?,?)}");

                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fdate);
                cstmt.setString(5, tdate);
                cstmt.setString(6, fage);
                cstmt.setString(7, tage);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    HashMap results = new HashMap();
                    results.put("district_id", district_id);
                    results.put("mandal_id", rs.getString(2));
                    results.put("name", rs.getString(3));
                    results.put("treg", rs.getString(4));
                    results.put("tass", rs.getString(5));
                    results.put("teli", rs.getString(6));
                    results.put("trej", rs.getString(7));
                    results.put("tar", rs.getString(8));
                    results.put("tdr", rs.getString(9));
                    results.put("fDate", fdate);
                    results.put("tDate", tdate);
                    results.put("fage", fage);
                    results.put("tage", tage);

                    data.add(results);
                }
            }
            /** Getting the details from village wise and details */
            if (village_id.equals("0") && !district_id.equals("0") && !mandal_id.equals("0")) {
                // cstmt = con.prepareCall("{Call REPORTFORAPPEALAUTHORITYVILLAGERESULTSTATUS(?,?,?,?)}");

                cstmt = con.prepareCall("{Call REPORTFORAPPEALAUTHORITYDISTRICTAGERESULTSTATUS(?,?,?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fdate);
                cstmt.setString(5, tdate);
                cstmt.setString(6, fage);
                cstmt.setString(7, tage);
                rs = cstmt.executeQuery();
                while (rs.next()) {

                    HashMap results = new HashMap();
                    results.put("district_id", district_id);
                    results.put("mandal_id", mandal_id);
                    results.put("village_id", rs.getString(2));
                    results.put("name", rs.getString(3));
                    results.put("treg", rs.getString(4));
                    results.put("tass", rs.getString(5));
                    results.put("teli", rs.getString(6));
                    results.put("trej", rs.getString(7));
                    results.put("tar", rs.getString(8));
                    results.put("tdr", rs.getString(9));
                    results.put("fDate", fdate);
                    results.put("tDate", tdate);
                    results.put("fage", fage);
                    results.put("tage", tage);

                    data.add(results);
                }
            }
            /**Get Habitation details */
            if (!village_id.equals("0") && !district_id.equals("0") && !mandal_id.equals("0")) {
                // cstmt = con.prepareCall("{Call REPORTFORAPPEALAUTHORITYHABRESULTSTATUS(?,?,?,?,?)}");
                cstmt = con.prepareCall("{Call REPORTFORAPPEALAUTHORITYDISTRICTAGERESULTSTATUS(?,?,?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fdate);
                cstmt.setString(5, tdate);
                cstmt.setString(6, fage);
                cstmt.setString(7, tage);
                rs = cstmt.executeQuery();

                int treg = 0;
                int tass = 0;
                int teli = 0;
                int trej = 0;
                int tar = 0;
                int tdr = 0;
                while (rs.next()) {

                    HashMap results = new HashMap();
                    results.put("district_id", district_id);
                    results.put("mandal_id", mandal_id);
                    results.put("village_id", village_id);
                    results.put("hab_id", rs.getString(2));
                    results.put("name", rs.getString(3));
                    results.put("treg", rs.getString(4));
                    results.put("tass", rs.getString(5));
                    results.put("teli", rs.getString(6));
                    results.put("trej", rs.getString(7));
                    results.put("tar", rs.getString(8));
                    results.put("tdr", rs.getString(9));
                    results.put("fDate", fdate);
                    results.put("tDate", tdate);
                    results.put("fage", fage);
                    results.put("tage", tage);
                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString(2).equals("00") || rs.getString(2).equals("01"))) {

                        if (rs.getString(2).equals("00")) {

                            treg = rs.getInt(4);
                            tass = rs.getInt(5);
                            teli = rs.getInt(6);
                            trej = rs.getInt(7);
                            tar = rs.getInt(8);
                            tdr = rs.getInt(9);
                        }
                        if (rs.getString(2).equals("01")) {

                            treg = treg + rs.getInt(4);
                            tass = tass + rs.getInt(5);
                            teli = teli + rs.getInt(6);
                            trej = trej + rs.getInt(7);
                            tar = tar + rs.getInt(8);
                            tdr = tdr + rs.getInt(9);

                            results.put("treg", treg);
                            results.put("tass", tass);
                            results.put("teli", teli);
                            results.put("trej", trej);
                            results.put("tar", tar);
                            results.put("tdr", tdr);
                            data.add(results);
                        }
                    } else {
                        data.add(results);
                    }
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getMaritalStatusPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getMaritalStatusPersonalDetails");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                DBConnection.closeResultSet(rs);
                DBConnection.closeStatement(cstmt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public ArrayList getPersonalAgeWiseDetails(DataSource ds, String district_id, String mandal_id, String village_id, String hab, String fdate, String tdate, String fromAge, String toAge, String type) throws SADAREMDBException, SQLException 
    {//need to change

        Connection con = null;
      //  Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder();
        StringBuilder sql1 = new StringBuilder();
        StringBuilder col = new StringBuilder();
        ArrayList data = new ArrayList();
        HashMap results = null;
        try
        {//and t.CATEGORYID  = 2 and pdd.CATEGORYID  = 2 ,  pdd.disability_id as Disability, t.totaldisability as Percentage
            // " from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code ) "+
            con = DBConnection.getConnection();
          //  stmt = con.createStatement();//


            /*   if(typeof==1)
            type="Locomotor Disability";
            else if(typeof==2)
            type="Visual Imapirment";  else if(typeof==3)
            type="Hearing Impairment"; else if(typeof==4)
            type="Mental Retardation"; else if(typeof==5)
            type="Mental Illness"; else if(typeof==6)
            type="Multiple Disabilities ";*/

            if (type != null && type.equalsIgnoreCase("reg"))
            {
                //sql1.append("JOIN APPELLATEAUTHORITYANDTEMPORARY_REGISTRATIONDETAILS t  on(p.person_code=t.person_code) ")
                ;
            } 
            else if (type != null && type.equalsIgnoreCase("tar")) 
            {
                sql1.append(" JOIN tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code)"
                        + "CROSS APPLY "
                        + " DATA_PERSON_CODE(p.person_code) DPC"
                        + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                        + "and t.status='Active' and pdd.status='Active'  and t.TOTALDISABILITY <40 "
                        + "and (DATEADD(DD,0,DATEDIFF(DD,0,t.CREATED_DATE))) BETWEEN  ? AND ?");
                col.append(",  pdd.disability_id as Disability, t.totaldisability as Percentage ");
            } 
            else if (type != null && type.equalsIgnoreCase("eli")) 
            {
                sql1.append("JOIN tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                        + " CROSS APPLY"
                        + " DATA_PERSON_CODE(p.person_code) DPC"
                        + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                        + " and t.status='Active' and pdd.status='Active'  and t.TOTALDISABILITY >=40 "
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,t.CREATED_DATE))) BETWEEN  ? AND ?");
                col.append(",  pdd.disability_id as Disability, t.totaldisability as Percentage ");
            }
            else if (type != null && type.equalsIgnoreCase("tdr"))
            {
                sql1.append("JOIN TBLREJECTPERSON_DETAILS t "
                        + " CROSS APPLY "
                        + " DATA_PERSON_CODE(p.person_code) DPC"
                        + " on(p.person_code=t.person_code) and t.status='Active' "
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,t.CREATED_DATE))) BETWEEN  ? AND ?");
                col.append(" ,t.disability_id as Disability , '-' as percentage ");
            }/*else if(type!=null && type.equalsIgnoreCase("ass")){
            sql1.append("JOIN tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) and t.status='Active'");
            }*/


            sql.append("select  p.person_code,p.surname,p.age_years,p.First_Name ,p.relation_name,p.gender,p.house_number, "
                    + " d.district_name as  district_name,m.mandal_name as mandal_name, "
                    + " v.village_name as village_name,h.habitation_name as habName, p.pensioncard_no  " + col
                    + " from dbo.tblperson_personal_details p "
                    + " CROSS APPLY "
                    + " DATA_HAB_CODE(p.HABCODE) DHC "
                    + " join tbldistrict_details D on(P.district_id=d.district_id) join tblmandal_details M on(P.mandal_id=M.mandal_id and P.district_id=m.district_id) "
                    + " join  tblPanchayat_Details Pa on(DHC.PANCHAYAT_ID=pa.Panchayat_ID and P.mandal_id=pa.mandal_id  and  P.district_id=pa.district_id) "
                    + " join tblvillage_details V on(P.village_id=v.village_id  and P.mandal_id=v.mandal_id and P.district_id=v.district_id ) "
                    + " join tblhabitation_details h on(DHC.HABITATION_ID=h.habitation_id and "
                    + " P.village_id=h.village_id and DHC.PANCHAYAT_ID=h.Panchayat_ID and P.mandal_id=h.mandal_id  and "
                    + " P.district_id=h.district_id and DHC.ASSEMBLY_ID = h.assembly_id) "
                    + sql1 + " and  p.status='Active'  " //" and substring(p.habcode, 1,2)='"+district_id +"'" +
                    //" and DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)) BETWEEN From_Date as varchar(20))+char(39)+' AND'+char(39)+cast(@To_Date as varchar(20))+char(39)"
                    //" and (DATEADD(DD,0,DATEDIFF(DD,0,p.UPDATED_DATE))) BETWEEN  '" + fdate + "' AND '" + tdate + "'"
                    );


            /*if(type!=null && type.equalsIgnoreCase("tar")){
            sql.append(col);
            }else if(type!=null && type.equalsIgnoreCase("eli")){

            }else if(type!=null && type.equalsIgnoreCase("tdr")){

            }*/
            if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) 
            {
                sql.append("and DPC.DISTRICT_ID=?");
                // sql.append("and substring(pdd.PERSON_CODE, 1,2)='"+district_id +"'") ;

            } 
            else if (!district_id.equals("0") && !mandal_id.equals("0") && village_id.equals("0")) 
            {
                sql.append("and DPC.DISTRICT_ID=? and DPC.MANDAL_ID=?");
                // sql.append("and substring(pdd.PERSON_CODE, 1,2)='"+district_id +"' and substring(pdd.PERSON_CODE,6,2)='"+mandal_id+"'") ;
                // sql.append("and substring(T.PERSON_CODE, 1,2)='"+district_id +"' and substring(T.PERSON_CODE,6,2)='"+mandal_id+"' and substring(pdd.PERSON_CODE, 1,2)='"+district_id +"' and substring(pdd.PERSON_CODE,6,2)='"+mandal_id+"'") ;

            } 
            else if (!district_id.equals("0") && !mandal_id.equals("0") && !village_id.equals("0") && hab.equalsIgnoreCase("0")) 
            {
                sql.append("and DPC.DISTRICT_ID=? and DPC.MANDAL_ID=? and DPC.VILLAGE_ID=?");
                // sql.append("and substring(pdd.PERSON_CODE, 1,2)='"+district_id +"' and substring(pdd.PERSON_CODE,6,2)='"+mandal_id+"' and substring(pdd.PERSON_CODE,8,3)='"+village_id+"'") ;

            } 
            else if (!district_id.equals("0") && !mandal_id.equals("0") && !village_id.equals("0") && !hab.equalsIgnoreCase("0")) 
            {
                sql.append("and DPC.DISTRICT_ID=? and DPC.MANDAL_ID=? and DPC.VILLAGE_ID=? and DPC.HABITATION_ID=?");
                // sql.append("and substring(pdd.PERSON_CODE, 1,2)='"+district_id +"' and substring(pdd.PERSON_CODE,6,2)='"+mandal_id+"' and substring(pdd.PERSON_CODE,8,3)='"+village_id+"' and substring(pdd.PERSON_CODE,11,2)'= "+hab+"'") ;

            }
            sql.append(" and p.AGE_YEARS +DATEDIFF(YEAR, p.CREATED_DATE,GETDATE()) BETWEEN  ? and ? ");


            String gender = null;
            int dis = 0;
            String distype = null;
            
            pstmt=con.prepareStatement(sql.toString());
            pstmt.setString(1, fdate);
            pstmt.setString(2, tdate); 
            
            int h=3;
            if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) 
            {
            	
            	   pstmt.setString(h, district_id);h++; 
            }else if (!district_id.equals("0") && !mandal_id.equals("0") && village_id.equals("0")) 
            {
            	 pstmt.setString(h, district_id);h++; 
            	 pstmt.setString(h, mandal_id); h++;
          
            }
            else if (!district_id.equals("0") && !mandal_id.equals("0") && !village_id.equals("0") && hab.equalsIgnoreCase("0"))
            {
            	
            	 pstmt.setString(h, district_id);h++; 
            	 pstmt.setString(h, mandal_id); h++;
            	 pstmt.setString(h, village_id); h++;	
            	
            }else if (!district_id.equals("0") && !mandal_id.equals("0") && !village_id.equals("0") && !hab.equalsIgnoreCase("0")) 
            {
            	
            	 pstmt.setString(h, district_id);h++; 
            	 pstmt.setString(h, mandal_id); h++;
            	 pstmt.setString(h, village_id); h++;
            	 pstmt.setString(h, hab); h++;
            	 
            	
            }
            pstmt.setString(h, fromAge);h++; 
            pstmt.setString(h, toAge);h++; 
            
     
            
            
            
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                gender = rs.getString(6);
                if (gender != null && gender.equalsIgnoreCase("2"))
                {
                    gender = "Female";

                } 
                else if (gender != null && gender.equalsIgnoreCase("1"))
                {
                    gender = "Male";

                } 
                else 
                {
                    gender = "NE";
                }
                dis = rs.getInt(13);
                if (dis == 1) 
                {
                    distype = "Locomotor Disability";
                }
                else if (dis == 2)
                {
                    distype = "Visual Imapirment";
                } 
                else if (dis == 3) 
                {
                    distype = "Hearing Impairment";
                } 
                else if (dis == 4) 
                {
                    distype = "Mental Retardation";
                }
                else if (dis == 5) 
                {
                    distype = "Mental Illness";
                } 
                else if (dis == 6) 
                {
                    distype = "Multiple Disabilities ";
                }
                results = new HashMap();
                results.put("person_code", "SID" + rs.getString(1));
                results.put("surname", rs.getString(2));
                results.put("age", rs.getString(3));
                results.put("name", rs.getString(2) + " " + rs.getString(4));
                results.put("rname", rs.getString(5));
                results.put("gender", gender);
                results.put("disability", distype);
                results.put("percentage", rs.getString(14));
                results.put("houseno", rs.getString(7) + ".");
                results.put("dname", rs.getString(8));
                results.put("mname", rs.getString(9));
                results.put("vname", rs.getString(10));
                results.put("hname", rs.getString(11));
                results.put("fDate", fdate);
                results.put("tDate", tdate);
                results.put("pension_no", rs.getString(12));

                data.add(results);
            }
        } catch (SQLException sqlEx)
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getMaritalStatusPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getMaritalStatusPersonalDetails");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                DBConnection.closeResultSet(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return data;
    }

    public ArrayList getVisiualDisabilityCount(DataSource ds, String district_id, String mandal_id, String village_id, String fdate, String tdate) throws SADAREMDBException, SQLException 
    {



        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;
        // ArrayList data = new ArrayList();
        String query = null;
        // String type = null;
        PreparedStatement ps = null;
        Object col = null;

        NumberFormat form = NumberFormat.getInstance();
        form.setMinimumIntegerDigits(2);
        form.setMinimumFractionDigits(2);
        form.setMaximumFractionDigits(2);
        HashMap h1 = null;
        String kk = null;

        String[] aa = {"c.Congenital", "c.Hereditary", "c.BirthInjury",
            "c.Disease", "c.Malnutrition", "c.Accident ", "c.Other_Cause"
        };
        String tablename = null, colu = null, totall = null;
//double mp=0, fp=0,tp=0;
        ArrayList a = new ArrayList();
        String co = null;
        int k = 1;
        double male = 0, female = 0, tot = 0;
        if (district_id == null) {
            district_id = "0";
        }
        if (mandal_id == null) {
            mandal_id = "0";
        }
        if (village_id == null) {
            village_id = "0";
        }
        try {
            if (fdate != null && fdate.contains("-")) {
                ;

            } else {
                fdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
            }

            if (tdate != null && tdate.contains("-")) {
                ;
            } else {
                tdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(tdate));
            }
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            if (district_id.equals("0"))
            {
                for (int ll = 0; ll < aa.length; ll++) 
                {
                    col = aa[ll];
                    h1 = new HashMap();
                    if (ll < 6) 
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=2 and " + col + "=1 and p.gender=1 "
                                + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=1 and " + col + "=1  and pdd.Disability_ID=2";
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=2 ";
                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);

                    } 
                    else
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=2 and " + col + "!='' and p.gender=1 "
                                + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";


                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=1 and " + col + "!=%27%27   and pdd.Disability_ID=2";
                        totall = col + "!=%27%27  and c.status=%27active%27 and  pdd.Disability_ID=2 ";

                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);


                    }

                    ps = con.prepareStatement(query); 
                    ps.setString(1,fdate);
                    ps.setString(2,tdate);
                    
                    rs = ps.executeQuery();
                    if (rs.next()) 
                    {

                        male = (rs.getInt(1));
                    }//c.Other_Cause!= ''

                    if (ll < 6) 
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=2 and " + col + "=1 and p.gender=2 "
                                + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=2 and " + col + "=1  and pdd.Disability_ID=2";
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=2 ";
                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    } 
                    else 
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=2 and " + col + "!='' and p.gender=2 "
                                + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";
//&#039;

                        colu = " c.status=%27active%27 and p.gender=2 and " + col + "!=%27%27 and pdd.Disability_ID=2";
                        totall = col + "!=%27%27  and c.status=%27active%27 and  pdd.Disability_ID=2 ";

                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    }
                    ps = con.prepareStatement(query); 
                    ps.setString(1,fdate);
                    ps.setString(2,tdate);
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) 
                    {
                        female = rs.getInt(1);
                    }


                    tot = male + female;
                    h1.put("tablet", tablename);
                    h1.put("columnt", totall);
                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } 
                    else 
                    {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0)
                    {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } 
                    else 
                    {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }

                    co = (String) col;
                    co = co.substring(2, co.length());
                    if (co.contains("high_fever")) {
                        co = "Meningitis";
                    }
                    h1.put("col", co);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }

                h1 = new HashMap();

                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " join tblPerson_Disability_Details pdd on(a.person_code=pdd.person_code )"
                        + "join tblPerson_Disability_TotalValue_Details t  on(a.person_code=t.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " where pdd.Disability_ID=2 and a.se_can=1 and p.gender=1 and  "
                        + " c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";



                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=1 and  a.status=%27active%27 and c.status=%27active%27 and   a.se_can=1 and pdd.Disability_ID=2";
                h1.put("tablem", tablename);
                h1.put("columnm", colu);
                ps = con.prepareStatement(query); 
                ps.setString(1,fdate);
                ps.setString(2,tdate);
                rs = ps.executeQuery();
                if (rs.next()) {
                    male = rs.getDouble(1);

                }

                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " join tblPerson_Disability_Details pdd on(a.person_code=pdd.person_code )"
                        + "join tblPerson_Disability_TotalValue_Details t  on(a.person_code=t.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " where pdd.Disability_ID=2 and a.se_can=1 and p.gender=2 and  "
                        + " c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";

                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=2 and  a.status=%27active%27 and c.status=%27active%27 and   a.se_can=1 and pdd.Disability_ID=2";
                h1.put("tablef", tablename);
                h1.put("columnf", colu);
                ps = con.prepareStatement(query); 
                ps.setString(1,fdate);
                ps.setString(2,tdate);
                rs = ps.executeQuery();
                if (rs.next()) {
                    female = rs.getDouble(1);

                }
                tot = male + female;

                colu = "  a.se_can=1 and   a.status=%27active%27 and c.status=%27active%27 and  pdd.Disability_ID=2";

                h1.put("tablet", tablename);
                h1.put("columnt", colu);

                h1.put("male", (int) male);
                if (male != 0) {
                    h1.put("mp", form.format(100 * (male / tot)));
                } else {
                    h1.put("mp", 0);
                }

                h1.put("female", (int) female);
                if (female != 0) {
                    h1.put("fp", form.format(100 * (female / tot)));
                } else {
                    h1.put("fp", 0);
                }
                h1.put("tot", (int) tot);
                if (tot != 0) {
                    h1.put("tp", "100");
                } else {
                    h1.put("tp", "0");
                }
                h1.put("col", "SE- can perform work by Seeing");
                h1.put("district", district_id);
                h1.put("mandal", mandal_id);
                h1.put("village", village_id);
                h1.put("fdate", fdate);
                h1.put("tdate", tdate);
                a.add(h1);

                h1 = new HashMap();
                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                        + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                        + " where pdd.Disability_ID=2 and a.rw_can=1 and p.gender=1 and  "
                        + " c.status='Active' and a.status='Active' and  p.status='Active' and pdd.status='Active' and t.status='Active' "
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";
                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=1 and   a.status=%27active%27 and c.status=%27active%27 and  a.rw_can=1 and pdd.Disability_ID=2";
                h1.put("tablem", tablename);
                h1.put("columnm", colu);
                ps = con.prepareStatement(query);  
                
                ps.setString(1,fdate);
                ps.setString(2,tdate);
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    male = rs.getDouble(1);
                }

                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                        + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                        + " where pdd.Disability_ID=2 and a.rw_can=1 and p.gender=2 and  "
                        + " c.status='Active' and p.status='Active' and a.status='Active' and pdd.status='Active' and t.status='Active' "
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";

                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=2 and  a.status=%27active%27 and c.status=%27active%27 and   a.rw_can=1 and pdd.Disability_ID=2";
                h1.put("tablef", tablename);
                h1.put("columnf", colu);

                ps = con.prepareStatement(query); 
                
                ps.setString(1,fdate);
                ps.setString(2,tdate);
                rs = ps.executeQuery();
                if (rs.next()) {
                    female = rs.getDouble(1);

                }
                tot = male + female;

                colu = " a.rw_can=1 and   a.status=%27active%27 and c.status=%27active%27 and  pdd.Disability_ID=2 ";
                h1.put("tablet", tablename);
                h1.put("columnt", colu);
                h1.put("male", (int) male);
                if (male != 0) {
                    h1.put("mp", form.format(100 * (male / tot)));
                } else {
                    h1.put("mp", 0);
                }

                h1.put("female", (int) female);
                if (female != 0) {
                    h1.put("fp", form.format(100 * (female / tot)));
                } else {
                    h1.put("fp", 0);
                }
                h1.put("tot", (int) tot);
                if (tot != 0) {
                    h1.put("tp", "100");
                } else {
                    h1.put("tp", "0");
                }
                h1.put("col", "RW- Can perform work by Reading and Writing");
                h1.put("district", district_id);
                h1.put("mandal", mandal_id);
                h1.put("village", village_id);
                h1.put("fdate", fdate);
                h1.put("tdate", tdate);
                a.add(h1);

                String[] age = {"20", "40", "75", "100", "101", "30"};
                String[] percentag = {"Category 0 (20%)", "Category 1 (40%)", "Category 2 (75%)", "Category 3 (100%)", "Category 4 (100%)", "One eyed person (30%)"};
                ///String kk=null;
                for (int ll = 0; ll < age.length; ll++) {
                    h1 = new HashMap();
                    col = age[ll];

                    query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                            + " on(p.person_code=pdd.person_code ) JOIN tblVisualImpairment_Details t  on(p.person_code=t.person_code) "
                            + " join tblPerson_Disability_TotalValue_Details d  on(p.person_code=d.person_code) "
                            + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active' and d.status='Active' and t.visual_impairment =" + col + " and "
                            + " p.gender=1 and pdd.disability_id=2 "
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                            + "BETWEEN   ? AND ?";



                    tablename = "join tblVisualImpairment_Details c on(p.person_code=c.person_code) ";

                    colu = " p.gender=1 and c.status=%27active%27 and c.visual_impairment=" + col + "  and pdd.Disability_ID=2";

                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);


                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1,fdate);
                    ps.setString(2,tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        male = rs.getDouble(1);

                    }
                    query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                            + " on(p.person_code=pdd.person_code ) JOIN tblVisualImpairment_Details t  on(p.person_code=t.person_code)"
                            + " join tblPerson_Disability_TotalValue_Details d  on(p.person_code=d.person_code) "
                            + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active' and d.status='Active' and t.visual_impairment =" + col + " and "
                            + " p.gender=2 and pdd.disability_id=2   and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                            + "BETWEEN   ? AND ?";

                    tablename = "join tblVisualImpairment_Details c on(p.person_code=c.person_code) ";

                    colu = " p.gender=2 and  c.status=%27active%27 and c.visual_impairment=" + col + "  and pdd.Disability_ID=2";

                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query); 
                    
                    
                    ps.setString(1,fdate);
                    ps.setString(2,tdate);
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getDouble(1);

                    }
                    tot = male + female;


                    colu = "c.visual_impairment=" + col + "  and  c.status=%27active%27 and pdd.Disability_ID=2";
                    h1.put("tablet", tablename);
                    h1.put("columnt", colu);

                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }
                    kk = percentag[ll];
                    kk = kk.replace("%", "%25");
                    h1.put("coll", kk);
                    h1.put("col", percentag[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);


                }

            } else if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0"))
            {
                for (int ll = 0; ll < aa.length; ll++) 
                {
                    col = aa[ll];
                    h1 = new HashMap();
                    if (ll < 6) 
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=2 and " + col + "=1 and p.gender=1 and district_id=? and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=1 and " + col + "=1  and pdd.Disability_ID=2";
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=2 ";
                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);    






                        ;
                    } 
                    else
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=2 and " + col + "!='' and p.gender=1 and district_id=? and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=1 and " + col + "!=%27%27   and pdd.Disability_ID=2";
                        totall = col + "!=%27%27  and c.status=%27active%27 and pdd.Disability_ID=2 ";

                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    }


                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1,district_id);
                    ps.setString(2,fdate);
                    ps.setString(3,tdate);
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {

                        male = (rs.getInt(1));
                    }//c.Other_Cause!= ''

                    if (ll < 6)
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=2 and " + col + "=1 and p.gender=2 and district_id=? and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = "c.status=%27active%27 and  p.gender=2 and " + col + "=1  and pdd.Disability_ID=2";
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=2 ";
                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    } 
                    else {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=2 and " + col + "!='' and p.gender=2 and district_id=? and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=2 and " + col + "!=%27%27   and pdd.Disability_ID=2";
                        totall = col + "!=%27%27  and c.status=%27active%27 and pdd.Disability_ID=2 ";

                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    }
                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1,district_id);
                    ps.setString(2,fdate);
                    ps.setString(3,tdate);
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getInt(1);
                    }


                    tot = male + female;

                    h1.put("tablet", tablename);
                    h1.put("columnt", totall);
                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }

                    co = (String) col;
                    co = co.substring(2, co.length());
                    if (co.contains("high_fever")) {
                        co = "Meningitis";
                    }
                    h1.put("col", co);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }

                h1 = new HashMap();

                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " join tblPerson_Disability_Details pdd on(a.person_code=pdd.person_code )"
                        + "join tblPerson_Disability_TotalValue_Details t  on(a.person_code=t.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " where pdd.Disability_ID=2 and a.se_can=1 and p.gender=1 and  "
                        + " district_id=? and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";
                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=1 and   a.status=%27active%27 and c.status=%27active%27 and  a.se_can=1 and pdd.Disability_ID=2";
                h1.put("tablem", tablename);
                h1.put("columnm", colu);
                ps = con.prepareStatement(query); 
                ps.setString(1,district_id);
                ps.setString(2,fdate);
                ps.setString(3,tdate);
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    male = rs.getDouble(1);

                }

                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " join tblPerson_Disability_Details pdd on(a.person_code=pdd.person_code )"
                        + "join tblPerson_Disability_TotalValue_Details t  on(a.person_code=t.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " where pdd.Disability_ID=2 and a.se_can=1 and p.gender=2 and  "
                        + " district_id=? and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";

                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=2 and  a.status=%27active%27 and c.status=%27active%27 and   a.se_can=1 and pdd.Disability_ID=2";
                h1.put("tablef", tablename);
                h1.put("columnf", colu);

                ps = con.prepareStatement(query);
                ps.setString(1,district_id);
                ps.setString(2,fdate);
                ps.setString(3,tdate);
                rs = ps.executeQuery();
                if (rs.next()) {
                    female = rs.getDouble(1);

                }
                tot = male + female;
                colu = "  a.se_can=1 and  a.status=%27active%27 and c.status=%27active%27 and   pdd.Disability_ID=2";

                h1.put("tablet", tablename);
                h1.put("columnt", colu);

                h1.put("male", (int) male);
                if (male != 0) {
                    h1.put("mp", form.format(100 * (male / tot)));
                } else {
                    h1.put("mp", 0);
                }

                h1.put("female", (int) female);
                if (female != 0) {
                    h1.put("fp", form.format(100 * (female / tot)));
                } else {
                    h1.put("fp", 0);
                }
                h1.put("tot", (int) tot);
                if (tot != 0) {
                    h1.put("tp", "100");
                } else {
                    h1.put("tp", "0");
                }
                h1.put("col", "SE- can perform work by Seeing");
                h1.put("district", district_id);
                h1.put("mandal", mandal_id);
                h1.put("village", village_id);
                h1.put("fdate", fdate);
                h1.put("tdate", tdate);
                a.add(h1);

                h1 = new HashMap();
                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                        + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                        + " where pdd.Disability_ID=2 and a.rw_can=1 and p.gender=1 and  "
                        + " district_id=? and c.status='Active' and a.status='Active' and  p.status='Active' and pdd.status='Active' and t.status='Active' "
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";


                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=1 and   a.status=%27active%27 and c.status=%27active%27 and  a.rw_can=1 and pdd.Disability_ID=2";
                h1.put("tablem", tablename);
                h1.put("columnm", colu);


                ps = con.prepareStatement(query); 
                
                ps.setString(1,district_id);
                ps.setString(2,fdate);
                ps.setString(3,tdate);
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    male = rs.getDouble(1);

                }

                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                        + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                        + " where pdd.Disability_ID=2 and a.rw_can=1 and p.gender=2 and  "
                        + " district_id=? and c.status='Active' and p.status='Active' and a.status='Active' and pdd.status='Active' and t.status='Active' "
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";


                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=2 and   a.status=%27active%27 and c.status=%27active%27 and  a.rw_can=1 and pdd.Disability_ID=2";
                h1.put("tablef", tablename);
                h1.put("columnf", colu);

                ps = con.prepareStatement(query); 
                
                ps.setString(1,district_id);
                ps.setString(2,fdate);
                ps.setString(3,tdate);
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    female = rs.getDouble(1);

                }
                tot = male + female;

                colu = " a.rw_can=1 and   a.status=%27active%27 and c.status=%27active%27 and  pdd.Disability_ID=2 ";
                h1.put("tablet", tablename);
                h1.put("columnt", colu);

                h1.put("male", (int) male);
                if (male != 0) {
                    h1.put("mp", form.format(100 * (male / tot)));
                } else {
                    h1.put("mp", 0);
                }

                h1.put("female", (int) female);
                if (female != 0) {
                    h1.put("fp", form.format(100 * (female / tot)));
                } else {
                    h1.put("fp", 0);
                }
                h1.put("tot", (int) tot);
                if (tot != 0) {
                    h1.put("tp", "100");
                } else {
                    h1.put("tp", "0");
                }
                h1.put("col", "RW- Can perform work by Reading and Writing");
                h1.put("district", district_id);
                h1.put("mandal", mandal_id);
                h1.put("village", village_id);
                h1.put("fdate", fdate);
                h1.put("tdate", tdate);
                a.add(h1);





                String[] age = {"20", "40", "75", "100", "101", "30"};
                String[] percentag = {"Category 0 (20%)", "Category 1 (40%)", "Category 2 (75%)", "Category 3 (100%)", "Category 4 (100%)", "One eyed person (30%)"};

                for (int ll = 0; ll < age.length; ll++) {
                    h1 = new HashMap();
                    col = age[ll];

                    query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                            + " on(p.person_code=pdd.person_code ) JOIN tblVisualImpairment_Details t  on(p.person_code=t.person_code) "
                            + " join tblPerson_Disability_TotalValue_Details d  on(p.person_code=d.person_code) "
                            + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active' and d.status='Active' and t.visual_impairment =" + col + " and "
                            + " p.gender=1 and pdd.disability_id=2 and district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                            + "BETWEEN   ? AND ?";


                    tablename = "join tblVisualImpairment_Details c on(p.person_code=c.person_code) ";

                    colu = " p.gender=1 and  c.status=%27active%27 and c.visual_impairment=" + col + "  and pdd.Disability_ID=2";

                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1,district_id);
                    ps.setString(2,fdate);
                    ps.setString(3,tdate);
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        male = rs.getDouble(1);

                    }

                    query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                            + " on(p.person_code=pdd.person_code ) JOIN tblVisualImpairment_Details t  on(p.person_code=t.person_code)"
                            + " join tblPerson_Disability_TotalValue_Details d  on(p.person_code=d.person_code) "
                            + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active' and d.status='Active' and t.visual_impairment =" + col + " and "
                            + " p.gender=2 and pdd.disability_id=2 and district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                            + "BETWEEN   ? AND ?";

                    tablename = "join tblVisualImpairment_Details c on(p.person_code=c.person_code) ";

                    colu = " p.gender=2 and  c.status=%27active%27 and c.visual_impairment=" + col + "  and pdd.Disability_ID=2";

                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1,district_id);
                    ps.setString(2,fdate);
                    ps.setString(3,tdate);
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getDouble(1);

                    }
                    tot = male + female;
                    colu = "c.visual_impairment=" + col + "  and  c.status=%27active%27 and pdd.Disability_ID=2";
                    h1.put("tablet", tablename);
                    h1.put("columnt", colu);

                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }
                    h1.put("col", percentag[ll]);
                    kk = percentag[ll];
                    kk = kk.replace("%", "%25");
                    h1.put("coll", kk);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);


                }

            } else if (!district_id.equals("0") && !mandal_id.equals("0") && village_id.equals("0")) {


                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();
                    if (ll < 6) {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=2 and " + col + "=1 and p.gender=1 and district_id=? and mandal_id=? and c.status='Active' and p.status='Active' and pdd.status= 'Active'and t.status= 'Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=1 and " + col + "=1  and pdd.Disability_ID=2";
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=2 ";
                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    } else {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=2 and " + col + "!='' and p.gender=1 and district_id=? and c.status='Active' and p.status='Active' and pdd.status= 'Active'and t.status= 'Active' "
                                + " and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=1 and " + col + "!=%27%27   and pdd.Disability_ID=2";
                        totall = col + "!=%27%27  and c.status=%27active%27 and pdd.Disability_ID=2 ";

                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    }


                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,fdate);
                    ps.setString(4,tdate);
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {

                        male = (rs.getInt(1));
                    }//c.Other_Cause!= ''

                    if (ll < 6) 
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=2 and " + col + "=1 and p.gender=2 and district_id=? and c.status='Active' and p.status='Active' and pdd.status= 'Active'and t.status= 'Active' "
                                + " and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=2 and " + col + "=1  and pdd.Disability_ID=2";
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=2 ";
                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    } 
                    else
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=2 and " + col + "!='' and p.gender=2 and district_id=? and c.status='Active' and p.status='Active' and pdd.status= 'Active'and t.status= 'Active' "
                                + " and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = "c.status=%27active%27 and  p.gender=2 and " + col + "!=%27%27   and pdd.Disability_ID=2";
                        totall = col + "!=%27%27  and c.status=%27active%27 and pdd.Disability_ID=2 ";

                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    }
                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,fdate);
                    ps.setString(4,tdate);
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) 
                    {
                        female = rs.getInt(1);
                    }


                    tot = male + female;

                    h1.put("tablet", tablename);
                    h1.put("columnt", totall);

                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }

                    co = (String) col;
                    co = co.substring(2, co.length());
                    if (co.contains("high_fever")) {
                        co = "Meningitis";
                    }
                    h1.put("col", co);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }

                h1 = new HashMap();
                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " join tblPerson_Disability_Details pdd on(a.person_code=pdd.person_code )"
                        + "join tblPerson_Disability_TotalValue_Details t  on(a.person_code=t.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " where pdd.Disability_ID=2 and a.se_can=1 and p.gender=1 and  "
                        + " district_id=? and mandal_id=? and c.status='Active' and p.status='Active' and a.status= 'Active' and pdd.status= 'Active'and t.status= 'Active' "
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";
                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=1 and  a.status=%27active%27 and c.status=%27active%27 and   a.se_can=1 and pdd.Disability_ID=2";
                h1.put("tablem", tablename);
                h1.put("columnm", colu);

                ps = con.prepareStatement(query);  
                
                ps.setString(1,district_id);
                ps.setString(2,mandal_id);
                ps.setString(3,fdate);
                ps.setString(4,tdate);
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    male = rs.getDouble(1);

                }

                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " join tblPerson_Disability_Details pdd on(a.person_code=pdd.person_code )"
                        + "join tblPerson_Disability_TotalValue_Details t  on(a.person_code=t.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " where pdd.Disability_ID=2 and a.se_can=1 and p.gender=2 and  "
                        + " district_id=? and mandal_id=? and c.status='Active' and p.status='Active' and a.status= 'Active' and pdd.status= 'Active'and t.status= 'Active' "
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";

                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=2 and   a.status=%27active%27 and c.status=%27active%27 and  a.se_can=1 and pdd.Disability_ID=2";
                h1.put("tablef", tablename);
                h1.put("columnf", colu);


                ps = con.prepareStatement(query); 
                
                ps.setString(1,district_id);
                ps.setString(2,mandal_id);
                ps.setString(3,fdate);
                ps.setString(4,tdate); 
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    female = rs.getDouble(1);

                }
                tot = male + female;

                colu = "  a.se_can=1 and   a.status=%27active%27 and c.status=%27active%27 and  pdd.Disability_ID=2";

                h1.put("tablet", tablename);
                h1.put("columnt", colu);

                h1.put("male", (int) male);
                if (male != 0) {
                    h1.put("mp", form.format(100 * (male / tot)));
                } else {
                    h1.put("mp", 0);
                }

                h1.put("female", (int) female);
                if (female != 0) {
                    h1.put("fp", form.format(100 * (female / tot)));
                } else {
                    h1.put("fp", 0);
                }
                h1.put("tot", (int) tot);
                if (tot != 0) {
                    h1.put("tp", "100");
                } else {
                    h1.put("tp", "0");
                }
                h1.put("col", "SE- can perform work by Seeing");
                h1.put("district", district_id);
                h1.put("mandal", mandal_id);
                h1.put("village", village_id);
                h1.put("fdate", fdate);
                h1.put("tdate", tdate);
                a.add(h1);

                h1 = new HashMap();
                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                        + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                        + " where pdd.Disability_ID=2 and a.rw_can=1 and p.gender=1 and  "
                        + " district_id=? and mandal_id=? and c.status='Active' and p.status='Active' and a.status= 'Active' and pdd.status= 'Active'and t.status= 'Active'"
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";
                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=1 and   a.status=%27active%27 and c.status=%27active%27 and  a.rw_can=1 and pdd.Disability_ID=2";
                h1.put("tablem", tablename);
                h1.put("columnm", colu);

                ps = con.prepareStatement(query); 
                ps.setString(1,district_id);
                ps.setString(2,mandal_id);
                ps.setString(3,fdate);
                ps.setString(4,tdate); 
                
                
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    male = rs.getDouble(1);

                }

                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                        + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                        + " where pdd.Disability_ID=2 and a.rw_can=1 and p.gender=2 and  "
                        + " district_id=? and mandal_id=? and c.status='Active' and a.status= 'Active' and p.status= 'Active' and pdd.status= 'Active'and t.status= 'Active' "
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";
                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=2 and  a.status=%27active%27 and c.status=%27active%27 and   a.rw_can=1 and pdd.Disability_ID=2";
                h1.put("tablef", tablename);
                h1.put("columnf", colu);

                ps = con.prepareStatement(query);   
                
                ps.setString(1,district_id);
                ps.setString(2,mandal_id);
                ps.setString(3,fdate);
                ps.setString(4,tdate); 
                 
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    female = rs.getDouble(1);

                }
                tot = male + female;
                colu = " a.rw_can=1 and  a.status=%27active%27 and c.status=%27active%27 and   pdd.Disability_ID=2 ";
                h1.put("tablet", tablename);
                h1.put("columnt", colu);

                h1.put("male", (int) male);
                if (male != 0) {
                    h1.put("mp", form.format(100 * (male / tot)));
                } else {
                    h1.put("mp", 0);
                }

                h1.put("female", (int) female);
                if (female != 0) {
                    h1.put("fp", form.format(100 * (female / tot)));
                } else {
                    h1.put("fp", 0);
                }
                h1.put("tot", (int) tot);
                if (tot != 0) {
                    h1.put("tp", "100");
                } else {
                    h1.put("tp", "0");
                }
                h1.put("col", "RW- Can perform work by Reading and Writing");
                h1.put("district", district_id);
                h1.put("mandal", mandal_id);
                h1.put("village", village_id);
                h1.put("fdate", fdate);
                h1.put("tdate", tdate);
                a.add(h1);










                String[] age = {"20", "40", "75", "100", "101", "30"};
                String[] percentag = {"Category 0 (20%)", "Category 1 (40%)", "Category 2 (75%)", "Category 3 (100%)", "Category 4 (100%)", "One eyed person (30%)"};


                for (int ll = 0; ll < age.length; ll++)
                {
                    h1 = new HashMap();
                    col = age[ll];






                    query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                            + " on(p.person_code=pdd.person_code ) JOIN tblVisualImpairment_Details t  on(p.person_code=t.person_code)"
                            + " join tblPerson_Disability_TotalValue_Details d  on(p.person_code=d.person_code) "
                            + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active'  and t.visual_impairment =" + col + " and "
                            + " d.status='Active' and p.gender=1 and pdd.disability_id=2 and district_id=? and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                            + "BETWEEN   ? AND ?";
                    tablename = "join tblVisualImpairment_Details c on(p.person_code=c.person_code) ";

                    colu = " p.gender=1 and  c.status=%27active%27 and c.visual_impairment=" + col + "  and pdd.Disability_ID=2";

                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);


                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,fdate);
                    ps.setString(4,tdate); 
                     
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        male = rs.getDouble(1);

                    }

                    query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                            + " on(p.person_code=pdd.person_code ) JOIN tblVisualImpairment_Details t  on(p.person_code=t.person_code)"
                            + " join tblPerson_Disability_TotalValue_Details d  on(p.person_code=d.person_code) "
                            + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active'  and d.status='Active' and t.visual_impairment =" + col + " and "
                            + " p.gender=2 and pdd.disability_id= 2 and district_id=? and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                            + "BETWEEN   ? AND ?";
                    tablename = "join tblVisualImpairment_Details c on(p.person_code=c.person_code) ";

                    colu = " p.gender=2 and  c.status=%27active%27 and c.visual_impairment=" + col + "  and pdd.Disability_ID=2";

                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query);  
                    
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,fdate);
                    ps.setString(4,tdate); 
                     
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getDouble(1);

                    }
                    tot = male + female;

                    colu = "c.visual_impairment=" + col + "  and  c.status=%27active%27 and pdd.Disability_ID=2";
                    h1.put("tablet", tablename);
                    h1.put("columnt", colu);

                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }
                    h1.put("col", percentag[ll]);
                    kk = percentag[ll];
                    kk = kk.replace("%", "%25");
                    h1.put("coll", kk);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);



                }

            } 
            else if (!district_id.equals("0") && district_id != null && !mandal_id.equals("0") && !village_id.equals("0"))
            {

                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();
                    if (ll < 6) {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=2 and " + col + "=1 and p.gender=1 and district_id=? and mandal_id=? and village_id=? and c.status='Active'  and p.status= 'Active' and pdd.status= 'Active'and t.status= 'Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=1 and " + col + "=1  and pdd.Disability_ID=2";
                        totall = col + "=1  and  c.status=%27active%27 and pdd.Disability_ID=2 ";
                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    } else {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=2 and " + col + "!='' and p.gender=1 and district_id=? and mandal_id='" + mandal_id
                                + "' and village_id=? and c.status='Active'  and p.status= 'Active' and pdd.status= 'Active'and t.status= 'Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=1 and " + col + "!=%27%27   and pdd.Disability_ID=2";
                        totall = col + "!=%27%27  and c.status=%27active%27 and  pdd.Disability_ID=2 ";

                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    }


                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,village_id);
                    ps.setString(4,fdate);
                    ps.setString(5,tdate); 
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {

                        male = (rs.getInt(1));
                    }//c.Other_Cause!= ''

                    if (ll < 6)
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=2 and " + col + "=1 and p.gender=2 and district_id=? and mandal_id=? and village_id=? and c.status='Active'  and p.status= 'Active'  and pdd.status= 'Active'and t.status= 'Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=2 and " + col + "=1  and pdd.Disability_ID=2";
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=2 ";
                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    } 
                    else 
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=2 and " + col + "!='' and p.gender=2 and district_id=? and mandal_id=? and village_id=? and c.status='Active'  and p.status= 'Active'  and pdd.status= 'Active'and t.status= 'Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " c.status=%27active%27 and p.gender=2 and " + col + "!=%27%27   and pdd.Disability_ID=2";
                        totall = col + "!=%27%27  and c.status=%27active%27 and pdd.Disability_ID=2 ";

                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);

                    }
                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,village_id);
                    ps.setString(4,fdate);
                    ps.setString(5,tdate); 
             
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next())
                    {
                        female = rs.getInt(1);
                    }


                    tot = male + female;


                    h1.put("tablet", tablename);
                    h1.put("columnt", totall);
                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }

                    co = (String) col;
                    co = co.substring(2, co.length());

                    if (co.contains("high_fever")) {
                        co = "Meningitis";
                    }
                    h1.put("col", co);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);
                }
                h1 = new HashMap();
                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " join tblPerson_Disability_Details pdd on(a.person_code=pdd.person_code )"
                        + "join tblPerson_Disability_TotalValue_Details t  on(a.person_code=t.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " where pdd.Disability_ID=2 and a.se_can=1 and p.gender=1 and  "
                        + " district_id=? and mandal_id=? and village_id=? and c.status='Active' and a.status= 'Active' and p.status= 'Active' and pdd.status= 'Active'and t.status= 'Active' "
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";
                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=1 and   a.status=%27active%27 and c.status=%27active%27 and  a.se_can=1 and pdd.Disability_ID=2";
                h1.put("tablem", tablename);
                h1.put("columnm", colu);


                ps = con.prepareStatement(query);
                
                ps.setString(1,district_id);
                ps.setString(2,mandal_id);
                ps.setString(3,village_id);
                ps.setString(4,fdate);
                ps.setString(5,tdate); 
                
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    male = rs.getDouble(1);

                }

                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " join tblPerson_Disability_Details pdd on(a.person_code=pdd.person_code )"
                        + "join tblPerson_Disability_TotalValue_Details t  on(a.person_code=t.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " where pdd.Disability_ID=2 and a.se_can=1 and p.gender=2 and  "
                        + " district_id=? and mandal_id=? and village_id=? and c.status='Active' and a.status= 'Active' and p.status= 'Active'  and pdd.status= 'Active'and t.status= 'Active'"
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";
                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=2 and   a.status=%27active%27 and c.status=%27active%27 and  a.se_can=1 and pdd.Disability_ID=2";
                h1.put("tablef", tablename);
                h1.put("columnf", colu);
                ps = con.prepareStatement(query); 
                
                ps.setString(1,district_id);
                ps.setString(2,mandal_id);
                ps.setString(3,village_id);
                ps.setString(4,fdate);
                ps.setString(5,tdate); 
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    female = rs.getDouble(1);

                }
                tot = male + female;
                colu = "  a.se_can=1 and a.status=%27active%27 and c.status=%27active%27 and  pdd.Disability_ID=2";

                h1.put("tablet", tablename);
                h1.put("columnt", colu);

                h1.put("male", (int) male);
                if (male != 0) {
                    h1.put("mp", form.format(100 * (male / tot)));
                } else {
                    h1.put("mp", 0);
                }

                h1.put("female", (int) female);
                if (female != 0) {
                    h1.put("fp", form.format(100 * (female / tot)));
                } else {
                    h1.put("fp", 0);
                }
                h1.put("tot", (int) tot);
                if (tot != 0) {
                    h1.put("tp", "100");
                } else {
                    h1.put("tp", "0");
                }
                h1.put("col", "SE- can perform work by Seeing");
                h1.put("district", district_id);
                h1.put("mandal", mandal_id);
                h1.put("village", village_id);
                h1.put("fdate", fdate);
                h1.put("tdate", tdate);
                a.add(h1);

                h1 = new HashMap();
                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                        + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                        + " where pdd.Disability_ID=2 and a.rw_can=1 and p.gender=1 and  "
                        + " district_id=? and mandal_id=? and village_id=? and c.status='Active' and a.status= 'Active' and p.status= 'Active'  and pdd.status= 'Active'and t.status= 'Active' "
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";
                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=1 and   a.status=%27active%27 and c.status=%27active%27 and  a.rw_can=1 and pdd.Disability_ID=2";
                h1.put("tablem", tablename);
                h1.put("columnm", colu);


                ps = con.prepareStatement(query);  
                
                ps.setString(1,district_id);
                ps.setString(2,mandal_id);
                ps.setString(3,village_id);
                ps.setString(4,fdate);
                ps.setString(5,tdate); 
             
                
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    male = rs.getDouble(1);

                }

                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                        + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                        + " where pdd.Disability_ID=2 and a.rw_can=1 and p.gender=2 and  "
                        + " district_id=? and mandal_id=? and village_id=? and c.status='Active' and a.status= 'Active' and p.status= 'Active' and pdd.status= 'Active'and t.status= 'Active' "
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";
                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=2 and   a.status=%27active%27 and c.status=%27active%27 and  a.rw_can=1 and pdd.Disability_ID=2";
                h1.put("tablef", tablename);
                h1.put("columnf", colu);

                ps = con.prepareStatement(query); 
                
                ps.setString(1,district_id);
                ps.setString(2,mandal_id);
                ps.setString(3,village_id);
                ps.setString(4,fdate);
                ps.setString(5,tdate); 
             
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    female = rs.getDouble(1);

                }
                tot = male + female;
                colu = " a.rw_can=1 and  a.status=%27active%27 and c.status=%27active%27 and   pdd.Disability_ID=2 ";
                h1.put("tablet", tablename);
                h1.put("columnt", colu);
                h1.put("male", (int) male);
                if (male != 0) {
                    h1.put("mp", form.format(100 * (male / tot)));
                } else {
                    h1.put("mp", 0);
                }

                h1.put("female", (int) female);
                if (female != 0) {
                    h1.put("fp", form.format(100 * (female / tot)));
                } else {
                    h1.put("fp", 0);
                }
                h1.put("tot", (int) tot);
                if (tot != 0) {
                    h1.put("tp", "100");
                } else {
                    h1.put("tp", "0");
                }
                h1.put("col", "RW- Can perform work by Reading and Writing");
                h1.put("district", district_id);
                h1.put("mandal", mandal_id);
                h1.put("village", village_id);
                h1.put("fdate", fdate);
                h1.put("tdate", tdate);
                a.add(h1);

                h1 = new HashMap();




                String[] age = {"20", "40", "75", "100", "101", "30"};
                String[] percentag = {"Category 0 (20%)", "Category 1 (40%)", "Category 2 (75%)", "Category 3 (100%)", "Category 4 (100%)", "One eyed person (30%)"};


                for (int ll = 0; ll < age.length; ll++) {
                    h1 = new HashMap();
                    col = age[ll];

                    query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                            + " on(p.person_code=pdd.person_code ) JOIN tblVisualImpairment_Details t  on(p.person_code=t.person_code)"
                            + " join tblPerson_Disability_TotalValue_Details d  on(p.person_code=d.person_code) "
                            + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active' and d.status='Active' and t.visual_impairment =" + col + " and "
                            + " p.gender=1 and pdd.disability_id=2 and district_id=? and mandal_id=? and village_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                            + "BETWEEN   ? AND ?";
                    tablename = "join tblVisualImpairment_Details c on(p.person_code=c.person_code) ";

                    colu = " p.gender=1 and  c.status=%27active%27 and c.visual_impairment=" + col + "  and pdd.Disability_ID=2";

                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query);
                    
                    
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,village_id);
                    ps.setString(4,fdate);
                    ps.setString(5,tdate); 
           
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        male = rs.getDouble(1);

                    }

                    query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                            + " on(p.person_code=pdd.person_code ) JOIN tblVisualImpairment_Details t  on(p.person_code=t.person_code)"
                            + " join tblPerson_Disability_TotalValue_Details d  on(p.person_code=d.person_code) "
                            + "  and pdd.status='Active'  and p.status='Active'  and d.status='Active' and t.status='Active'  and t.visual_impairment =" + col + " and "
                            + " p.gender=2 and pdd.disability_id=2 and district_id=? and mandal_id=? and village_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                            + "BETWEEN   ? AND ?";
                    tablename = "join tblVisualImpairment_Details c on(p.person_code=c.person_code) ";

                    colu = " p.gender=2 and  c.status=%27active%27 and c.visual_impairment=" + col + "  and pdd.Disability_ID=2";

                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,village_id);
                    ps.setString(4,fdate);
                    ps.setString(5,tdate); 
           
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getDouble(1);

                    }
                    tot = male + female;
                    colu = "c.visual_impairment=" + col + "  and  c.status=%27active%27 and pdd.Disability_ID=2";
                    h1.put("tablet", tablename);
                    h1.put("columnt", colu);

                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }
                    h1.put("col", percentag[ll]);
                    kk = percentag[ll];
                    kk = kk.replace("%", "%25");
                    h1.put("coll", kk);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);
                }



            }



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getMaritalStatusPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getMaritalStatusPersonalDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(ps);

        }

        return a;
    }

    public ArrayList getViFunctionalneedsCount(DataSource ds, String district_id, String mandal_id, String village_id, String fdate, String tdate)
            throws SADAREMDBException, SQLException {



        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;
        // ArrayList data = new ArrayList();
        String query = null;
        String tablename = null, colu = null, totall = null;
        //  String type = null;
        String colll = null;
        PreparedStatement ps = null;
        String col = null;

        NumberFormat form = NumberFormat.getInstance();
        form.setMinimumIntegerDigits(2);
        form.setMinimumFractionDigits(2);
        form.setMaximumFractionDigits(2);
        HashMap h1 = null;//Meningitis

        String[] name = {"Surgery ", "White cane / Blind stick", "Braille Equipments", "Arithmetic Frames/ Abacus",
            "Low Vision Aids (Spectacles, Magnifiers)", "Speech Synthesizer",
            "Braille Short Hand Machines/ Type Writers", "Talking Watch/ Calculator", "Ay ADL Equipment",
            "Visual Impairment Other Special needs"};
        String[] name1 = {"Early Education (For children below 5 years)",
            "Home Based Education", "Special School", "Inclusive education",
            "For Employment in public/ pvt sector", "For self employment ", "Individual (PwD)",
            "Family", "Visual Impairment Other General Needs"};
        String[] aa = {"c.surgery is not null", "c.whitecane='White Cane/Blind Stick'", "c.brailleequipments='Braille Equipments'",
            "c.arithmeticframes='Arithmetic Frames/Abacus'", "c.lowvisionaids='Low Vision Aids'",
            "c.speechsynthesizer='Speech Synthesizer'", "c.shorthand='Braille Short Hand Machines'",
            "c.talkingwatch='Talking Watch/Calculator'", "c.anyadl='1'", "c.anyother is not null"
        };

        String[] aa1 = {"c.earlyeducation ='Early Education Services'", "c.educationservices ='Home Based Education'", "c.educationservices ='Special School'",
            "c.educationservices ='Inclusive Education'", "c.vocationaltraining ='For employment in public/ pvt. sector'",
            "c.vocationaltraining ='For self-employment'", "c.individual ='individual'",
            "c.family ='family' ", "c.otherservices!=''"
        };



        ArrayList a = new ArrayList();



        double tot = 0, less14 = 0, great14 = 0, tl = 0, tg = 0, tot1 = 0;
        double l1 = 0, l2 = 0, l3 = 0, g1 = 0, g2 = 0, g3 = 0, t1 = 0, t2 = 0, t3 = 0;

        if (district_id == null) {
            district_id = "0";
        }
        if (mandal_id == null) {
            mandal_id = "0";
        }
        if (village_id == null) {
            village_id = "0";
        }



        try {
            if (fdate != null && fdate.contains("-")) {
                //fdate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fdate));
                ;

            } else {
                fdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
            }

            if (tdate != null && tdate.contains("-")) {
                ;
            } else {
                tdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(tdate));
            }
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            if (district_id.equals("0")) {
                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblVisualImpairment_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=2 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblVisualImpairment_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27 and " + colll + "  and  pdd.Disability_ID=2 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);


                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1,fdate);
                    ps.setString(2,tdate);
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 8) {
                        tl = tl + less14;
                    }
                    query = "select count(*) from  dbo.tblVisualImpairment_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=2 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblVisualImpairment_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=2 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query);
                    
                    
                    ps.setString(1,fdate);
                    ps.setString(2,tdate);
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }

                    if (ll < 8) {
                        tg = tg + great14;
                    }


                    tot = less14 + great14;
                    colll = col.replace("'", "%27");
                    tablename = "join tblVisualImpairment_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + " and pdd.Disability_ID=2 ";
                    h1.put("tablet", tablename);
                    h1.put("columnt", colu);
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);

                    }
                    h1.put("col", name[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
//if(ll==7){
                    h1.put("tl", tl);
                    h1.put("tg", tg);
                    tot1 = tl + tg;
                    if (tl != 0) {
                        h1.put("tlp", form.format(100 * (tl / tot1)));

                    } else {
                        h1.put("tlp", 0);

                    }
                    if (tg != 0) {
                        h1.put("tgp", form.format(100 * (tg / tot1)));

                    } else {
                        h1.put("tgp", 0);


                    }
                    a.add(h1);

                }


                for (int ll = 0; ll < aa1.length; ll++) {
                    col = aa1[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=2 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=2 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query); 
                    
                    
                    ps.setString(1,fdate);
                    ps.setString(2,tdate);
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 4 && ll > 0) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        l3 = l3 + less14;
                    }
                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=2 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=2 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query); 
                    
                    
                    ps.setString(1,fdate);
                    ps.setString(2,tdate);
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 4 && ll > 0) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        g3 = g3 + great14;
                    }
                    tot = less14 + great14;
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + " and pdd.Disability_ID=2 ";
                    h1.put("tablet", tablename);
                    h1.put("columnt", colu);
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);




                    }
                    h1.put("col", name1[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }



            } //and district_id='"+district_id +
            else if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) {
                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblVisualImpairment_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=2 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'  and district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblVisualImpairment_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27 and " + colll + "  and  pdd.Disability_ID=2 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1,district_id);
                    ps.setString(2,fdate);
                    ps.setString(3,tdate);
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 8) {
                        tl = tl + less14;
                    }



                    query = "select count(*) from  dbo.tblVisualImpairment_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=2 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblVisualImpairment_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=2 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query);
                    
                    
                    ps.setString(1,district_id);
                    ps.setString(2,fdate);
                    ps.setString(3,tdate);
                
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 8) {
                        tg = tg + great14;
                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);

                    }
                    h1.put("col", name[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);

                    h1.put("tl", (int) tl);
                    h1.put("tg", (int) tg);
                    tot1 = tl + tg;
                    if (tl != 0) {
                        h1.put("tlp", form.format(100 * (tl / tot1)));

                    } else {
                        h1.put("tlp", 0);

                    }
                    if (tg != 0) {
                        h1.put("tgp", form.format(100 * (tg / tot1)));

                    } else {
                        h1.put("tgp", 0);


                    }
                    a.add(h1);

                }

                for (int ll = 0; ll < aa1.length; ll++) {
                    col = aa1[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=2 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=?  and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=2 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query);
                    
                    ps.setString(1,district_id);
                    ps.setString(2,fdate);
                    ps.setString(3,tdate);
           
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }

                    if (ll < 4 && ll > 0) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        l3 = l3 + less14;
                    }
                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=2 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=2 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1,district_id);
                    ps.setString(2,fdate);
                    ps.setString(3,tdate);
           
                 
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 4 && ll > 0) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        g3 = g3 + great14;
                    }


                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);



                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);
                    }
                    h1.put("col", name1[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);


                }

            }// "' and mandal_id='"+mandal_id +
            else if (!district_id.equals("0") && !mandal_id.equals("0") && village_id.equals("0")) {


                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblVisualImpairment_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=2 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'  and district_id=? and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblVisualImpairment_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27 and " + colll + "  and  pdd.Disability_ID=2 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query); 
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,fdate);
                    ps.setString(4,tdate);
           
                 
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }

                    if (ll < 8) {
                        tl = tl + less14;
                    }
                    query = "select count(*) from  dbo.tblVisualImpairment_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=2 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblVisualImpairment_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=2 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,fdate);
                    ps.setString(4,tdate);
           
                 
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 8) {
                        tg = tg + great14;
                    }


                    h1.put("tl", (int) tl);
                    h1.put("tg", (int) tg);
                    tot1 = tl + tg;
                    if (tl != 0) {
                        h1.put("tlp", form.format(100 * (tl / tot1)));

                    } else {
                        h1.put("tlp", 0);

                    }
                    if (tg != 0) {
                        h1.put("tgp", form.format(100 * (tg / tot1)));

                    } else {
                        h1.put("tgp", 0);


                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    h1.put("col", name[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }


                for (int ll = 0; ll < aa1.length; ll++) {
                    col = aa1[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=2 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=2 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,fdate);
                    ps.setString(4,tdate);
           
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 4 && ll > 0) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        l3 = l3 + less14;
                    }
                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=2 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=2 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,fdate);
                    ps.setString(4,tdate);
           
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 4 && ll > 0) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        g3 = g3 + great14;
                    }


                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);
                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);
                    }
                    h1.put("col", name1[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);


                }

// "' and village_id='"+village_id +

            } else if (!district_id.equals("0") && district_id != null && !mandal_id.equals("0") && !village_id.equals("0")) {

                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblVisualImpairment_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=2 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'  and district_id=? and mandal_id=? and village_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblVisualImpairment_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27 and " + colll + "  and  pdd.Disability_ID=2 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query);
                    
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,village_id);
                    ps.setString(4,fdate);
                    ps.setString(5,tdate);
           
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 8) {
                        tl = tl + less14;
                    }
                    query = "select count(*) from  dbo.tblVisualImpairment_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=2 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? and village_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblVisualImpairment_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=2 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,village_id);
                    ps.setString(4,fdate);
                    ps.setString(5,tdate);
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }

                    if (ll < 8) {
                        tg = tg + great14;
                    }


                    h1.put("tl", (int) tl);
                    h1.put("tg", (int) tg);
                    tot1 = tl + tg;
                    if (tl != 0) {
                        h1.put("tlp", form.format(100 * (tl / tot1)));

                    } else {
                        h1.put("tlp", 0);

                    }
                    if (tg != 0) {
                        h1.put("tgp", form.format(100 * (tg / tot1)));

                    } else {
                        h1.put("tgp", 0);


                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    h1.put("col", name[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }


                for (int ll = 0; ll < aa1.length; ll++) {
                    col = aa1[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=2 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? and village_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=2 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,village_id);
                    ps.setString(4,fdate);
                    ps.setString(5,tdate);
              
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }

                    if (ll < 4 && ll > 0) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        l3 = l3 + less14;
                    }


                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=2 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? and village_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=2 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);


                    ps = con.prepareStatement(query);  
                    
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,village_id);
                    ps.setString(4,fdate);
                    ps.setString(5,tdate);
              
                  
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 4 && ll > 0) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 6 && l1 <= 7) {
                        g3 = g3 + great14;
                    }


                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);
                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    h1.put("col", name1[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }



            }



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getMaritalStatusPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getMaritalStatusPersonalDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(ps);

        }

        return a;
    }

    public ArrayList getMentalRetardationDisabilityCount(DataSource ds, String district_id, String mandal_id, String village_id, String fdate, String tdate)
            throws SADAREMDBException, SQLException {


        String tablename = null, colu = null, totall = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;
        // ArrayList data = new ArrayList();
        String query = null;
        //   String type = null;
        PreparedStatement ps = null;
        Object col = null;

        NumberFormat form = NumberFormat.getInstance();
        form.setMinimumIntegerDigits(2);
        form.setMinimumFractionDigits(2);
        form.setMaximumFractionDigits(2);
        HashMap h1 = null;//Meningitis
        String[] aa = {"c.Congenital", "c.Hereditary", "c.BirthInjury", "c.Birth_Asphyxia", "c.high_fever",
            "c.Epilepsy", "c.Disease", "c.Malnutrition", "c.Accident ", "c.Other_Cause"
        };
        String kk = null;
//double mp=0, fp=0,tp=0;

        ArrayList a = new ArrayList();

        String co = null;

        double male = 0, female = 0, tot = 0;

        if (district_id == null) {
            district_id = "0";
        }
        if (mandal_id == null) {
            mandal_id = "0";
        }
        if (village_id == null) {
            village_id = "0";
        }



        try {
            if (fdate != null && fdate.contains("-")) {
                //fdate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fdate));
                ;

            } else {
                fdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
            }

            if (tdate != null && tdate.contains("-")) {
                ;
            } else {
                tdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(tdate));
            }
            con = DBConnection.getConnection();
            stmt = con.createStatement();

            if (district_id.equals("0")) {
                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();
                    if (ll < 9) 
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=4 and " + col + "=1 and p.gender=1 "
                                + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ? ";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " p.gender=1 and c.status=%27active%27 and " + col + "=1  and pdd.Disability_ID=4";
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=4 ";
                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                        ;
                    } 
                    else 
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=4 and " + col + "!='' and p.gender=1 "
                                + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " p.gender=1 and c.status=%27active%27 and " + col + "!=%27%27   and pdd.Disability_ID=4";
                        totall = col + "!=%27%27  and c.status=%27active%27 and pdd.Disability_ID=4 ";

                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    }


                    ps = con.prepareStatement(query);  
                    
             
                    ps.setString(1,fdate);
                    ps.setString(2,tdate);
               
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {

                        male = (rs.getInt(1));
                    }//c.Other_Cause!= ''

                    if (ll < 9)
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=4 and " + col + "=1 and p.gender=2 "
                                + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " p.gender=2 and c.status=%27active%27 and " + col + "=1  and pdd.Disability_ID=4";
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=4 ";
                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    } 
                    else 
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=4 and " + col + "!='' and p.gender=2 "
                                + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " p.gender=2 and c.status=%27active%27 and  " + col + "!=%27%27   and pdd.Disability_ID=4";
                        totall = col + "!=%27%27  and c.status=%27active%27 and pdd.Disability_ID=4 ";

                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    }


                    ps = con.prepareStatement(query); 
                    
              
                    ps.setString(1,fdate);
                    ps.setString(2,tdate);
              
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getInt(1);
                    }


                    tot = male + female;

                    h1.put("tablet", tablename);
                    h1.put("columnt", totall);
                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }

                    co = (String) col;
                    co = co.substring(2, co.length());

                    if (co.contains("high_fever")) {
                        co = "Meningitis";
                    }
                    h1.put("col", co);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }

                h1 = new HashMap();
                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                        + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                        + " where pdd.Disability_ID=4 and a.rw_can=1 and p.gender=1 and  "
                        + "  c.status='Active' and a.status='Active' and  p.status='Active' and pdd.status='Active' and t.status='Active'"
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";
                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=1 and   a.status=%27active%27 and c.status=%27active%27 and a.rw_can=1 and pdd.Disability_ID=4";
                h1.put("tablem", tablename);
                h1.put("columnm", colu);

                ps = con.prepareStatement(query);
                

                ps.setString(1,fdate);
                ps.setString(2,tdate);
          
                rs = ps.executeQuery();
                if (rs.next()) {
                    male = rs.getDouble(1);

                }

                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                        + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                        + " where pdd.Disability_ID=4 and a.rw_can=1 and p.gender=2 and  "
                        + " c.status='Active' and p.status='Active' and a.status='Active' and pdd.status='Active' and t.status='Active'"
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";
                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=2 and  a.rw_can=1 and  a.status=%27active%27 and c.status=%27active%27 and pdd.Disability_ID=4";
                h1.put("tablef", tablename);
                h1.put("columnf", colu);


                ps = con.prepareStatement(query);
                

                ps.setString(1,fdate);
                ps.setString(2,tdate);
          
                
                rs = ps.executeQuery();
                if (rs.next())
                {
                    female = rs.getDouble(1);

                }
                tot = male + female;
                colu = "  a.rw_can=1 and a.status=%27active%27 and c.status=%27active%27 and pdd.Disability_ID=4";

                h1.put("tablet", tablename);
                h1.put("columnt", colu);
                h1.put("male", (int) male);
                if (male != 0) {
                    h1.put("mp", form.format(100 * (male / tot)));
                } else {
                    h1.put("mp", 0);
                }

                h1.put("female", (int) female);
                if (female != 0) {
                    h1.put("fp", form.format(100 * (female / tot)));
                } else {
                    h1.put("fp", 0);
                }
                h1.put("tot", (int) tot);
                if (tot != 0) {
                    h1.put("tp", "100");
                } else {
                    h1.put("tp", "0");
                }
                h1.put("col", "RW- Can perform work by Reading and Writing");
                h1.put("district", district_id);
                h1.put("mandal", mandal_id);
                h1.put("village", village_id);
                h1.put("fdate", fdate);
                h1.put("tdate", tdate);
                a.add(h1);
                String[] age = {"25", "50", "75", "90", "100"};
                String[] percentag = {"25%", "50%", "75%", "90%", "100%"};

                for (int ll = 0; ll < age.length; ll++) {
                    h1 = new HashMap();
                    col = age[ll];

                    query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                            + " on(p.person_code=pdd.person_code ) JOIN tblMental_Retardation_Details t  on(p.person_code=t.person_code)"
                            + " join tblPerson_Disability_TotalValue_Details d  on(p.person_code=d.person_code) "
                            + "  and pdd.status='Active' and d.status='Active' and p.status='Active'  and t.status='Active'  and t.total_value =" + col + " and "
                            + " p.gender=1 and pdd.disability_id=4 "
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                            + "BETWEEN   ? AND ?";
                    tablename = "join tblMental_Retardation_Details c on(p.person_code=c.person_code) ";

                    colu = " p.gender=1  and c.status=%27active%27  and c.total_value=" + col + "  and pdd.Disability_ID=4";

                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);


                    ps = con.prepareStatement(query); 
                    

                    ps.setString(1,fdate);
                    ps.setString(2,tdate);
              
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        male = rs.getDouble(1);

                    }

                    query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                            + " on(p.person_code=pdd.person_code ) JOIN tblMental_Retardation_Details t  on(p.person_code=t.person_code)"
                            + " join tblPerson_Disability_TotalValue_Details d  on(p.person_code=d.person_code) "
                            + "  and pdd.status='Active' and d.status='Active' and p.status='Active'  and t.status='Active'  and t.total_value =" + col + " and "
                            + " p.gender=2 and pdd.disability_id=4  and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                            + "BETWEEN   ? AND ?";
                    tablename = "join tblMental_Retardation_Details c on(p.person_code=c.person_code) ";

                    colu = " p.gender=2 and c.status=%27active%27  and c.total_value=" + col + "  and pdd.Disability_ID=4";

                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);


                    ps = con.prepareStatement(query);
                    
                    ps.setString(1,fdate);
                    ps.setString(2,tdate);
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getDouble(1);

                    }
                    tot = male + female;
                    colu = "c.total_value=" + col + "  and c.status=%27active%27  and pdd.Disability_ID=4";
                    h1.put("tablet", tablename);
                    h1.put("columnt", colu);

                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }
                    h1.put("col", percentag[ll]);
                    kk = percentag[ll];
                    kk = kk.replace("%", "%25");
                    h1.put("coll", kk);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);


                }

            } else if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0"))
            {
                for (int ll = 0; ll < aa.length; ll++) 
                {
                    col = aa[ll];
                    h1 = new HashMap();
                    if (ll < 9)
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=4 and " + col + "=1 and p.gender=1 and district_id=? and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " p.gender=1 and c.status=%27active%27 and " + col + "=1  and pdd.Disability_ID=4";
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=4 ";
                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);


                        ;
                    } else
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=4 and " + col + "!='' and p.gender=1 and district_id=? and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " p.gender=1 and c.status=%27active%27 and " + col + "!=%27%27   and pdd.Disability_ID=4";
                        totall = col + "!=%27%27  and c.status=%27active%27 and pdd.Disability_ID=4 ";

                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    }


                    ps = con.prepareStatement(query);
                    
                    ps.setString(1,district_id);
                    ps.setString(2,fdate);
                    ps.setString(3,tdate);
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {

                        male = (rs.getInt(1));
                    }//c.Other_Cause!= ''

                    if (ll < 9) {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=4 and " + col + "=1 and p.gender=2 and district_id=? and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " p.gender=2 and " + col + "=1  and c.status=%27active%27 and pdd.Disability_ID=4";
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=4 ";
                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    } else {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=4 and " + col + "!='' and p.gender=2 and district_id=? and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " p.gender=2 and " + col + "!=%27%27   and c.status=%27active%27 and pdd.Disability_ID=4";
                        totall = col + "!=%27%27  and c.status=%27active%27 and  pdd.Disability_ID=4 ";

                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    }

                    ps = con.prepareStatement(query); 
                    
                    ps.setString(1,district_id);
                    ps.setString(2,fdate);
                    ps.setString(3,tdate);
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getInt(1);
                    }


                    tot = male + female;
                    h1.put("tablet", tablename);
                    h1.put("columnt", totall);

                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }

                    co = (String) col;
                    co = co.substring(2, co.length());

                    h1.put("col", co);
                    if (co.contains("high_fever")) {
                        co = "Meningitis";
                    }
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }

                h1 = new HashMap();
                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                        + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                        + " where pdd.Disability_ID=4 and a.rw_can=1 and p.gender=1 and  "
                        + " district_id=? and c.status='Active' and a.status='Active' and  p.status='Active' and pdd.status='Active' and t.status='Active' "
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";
                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=1 and  a.status=%27active%27 and c.status=%27active%27 and a.rw_can=1 and pdd.Disability_ID=4";
                h1.put("tablem", tablename);
                h1.put("columnm", colu);

                ps = con.prepareStatement(query); 
                
                ps.setString(1,district_id);
                ps.setString(2,fdate);
                ps.setString(3,tdate);
              
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    male = rs.getDouble(1);

                }

                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                        + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                        + " where pdd.Disability_ID=4 and a.rw_can=1 and p.gender=2 and  "
                        + " district_id=? and c.status='Active' and p.status='Active' and a.status='Active' and pdd.status='Active' and t.status='Active'"
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";
                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=2 and  a.rw_can=1 and a.status=%27active%27 and c.status=%27active%27 and pdd.Disability_ID=4";
                h1.put("tablef", tablename);
                h1.put("columnf", colu);
                ps = con.prepareStatement(query);
                
                ps.setString(1,district_id);
                ps.setString(2,fdate);
                ps.setString(3,tdate);
          
                
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    female = rs.getDouble(1);

                }
                tot = male + female;
                colu = "  a.rw_can=1 and a.status=%27active%27 and c.status=%27active%27 and pdd.Disability_ID=4";

                h1.put("tablet", tablename);
                h1.put("columnt", colu);

                h1.put("male", (int) male);
                if (male != 0) {
                    h1.put("mp", form.format(100 * (male / tot)));
                } else {
                    h1.put("mp", 0);
                }

                h1.put("female", (int) female);
                if (female != 0) {
                    h1.put("fp", form.format(100 * (female / tot)));
                } else {
                    h1.put("fp", 0);
                }
                h1.put("tot", (int) tot);
                if (tot != 0) {
                    h1.put("tp", "100");
                } else {
                    h1.put("tp", "0");
                }
                h1.put("col", "RW- Can perform work by Reading and Writing");
                h1.put("district", district_id);
                h1.put("mandal", mandal_id);
                h1.put("village", village_id);
                h1.put("fdate", fdate);
                h1.put("tdate", tdate);
                a.add(h1);





                String[] age = {"25", "50", "75", "90", "100"};
                String[] percentag = {"25%", "50%", "75%", "90%", "100%"};

                for (int ll = 0; ll < age.length; ll++) {
                    h1 = new HashMap();
                    col = age[ll];

                    query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                            + " on(p.person_code=pdd.person_code ) JOIN tblMental_Retardation_Details t  on(p.person_code=t.person_code)"
                            + " join tblPerson_Disability_TotalValue_Details d  on(p.person_code=d.person_code) "
                            + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active' and d.status='Active' and t.total_value =" + col + " and "
                            + " p.gender=1 and pdd.disability_id=4 and district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                            + "BETWEEN   ? AND ?";

                    tablename = "join tblMental_Retardation_Details c on(p.person_code=c.person_code) ";

                    colu = " p.gender=1 and c.status=%27active%27  and c.total_value=" + col + "  and pdd.Disability_ID=4";

                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);


                    ps = con.prepareStatement(query);
                    ps.setString(1,district_id);
                    ps.setString(2,fdate);
                    ps.setString(3,tdate);
              
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next())
                    {
                        male = rs.getDouble(1);

                    }

                    query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                            + " on(p.person_code=pdd.person_code ) JOIN tblMental_Retardation_Details t  on(p.person_code=t.person_code)"
                            + " join tblPerson_Disability_TotalValue_Details d  on(p.person_code=d.person_code) "
                            + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active' and d.status='Active' and t.total_value =" + col + " and "
                            + " p.gender=2 and pdd.disability_id=4 and district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                            + "BETWEEN   ? AND ?";

                    tablename = "join tblMental_Retardation_Details c on(p.person_code=c.person_code) ";

                    colu = " p.gender=2 and c.status=%27active%27  and c.total_value=" + col + "  and pdd.Disability_ID=4";

                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query);
                    
                    ps.setString(1,district_id);
                    ps.setString(2,fdate);
                    ps.setString(3,tdate);
              
                    rs = ps.executeQuery();
                    if (rs.next())
                    {
                        female = rs.getDouble(1);

                    }
                    tot = male + female;
                    colu = "c.total_value=" + col + "  and c.status=%27active%27  and pdd.Disability_ID=4";
                    h1.put("tablet", tablename);
                    h1.put("columnt", colu);

                    h1.put("male", (int) male);
                    if (male != 0) 
                    {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } 
                    else 
                    {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) 
                    {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } 
                    else
                    {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }
                    h1.put("col", percentag[ll]);
                    kk = percentag[ll];
                    kk = kk.replace("%", "%25");
                    h1.put("coll", kk);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);


                }

            } 
            else if (!district_id.equals("0") && !mandal_id.equals("0") && village_id.equals("0"))
            {


                for (int ll = 0; ll < aa.length; ll++)
                {
                    col = aa[ll];
                    h1 = new HashMap();
                    if (ll < 9) {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=4 and " + col + "=1 and p.gender=1 and district_id=? and mandal_id=? and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " p.gender=1 and c.status=%27active%27 and " + col + "=1  and pdd.Disability_ID=4";
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=4 ";
                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    }
                    else
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=4 and " + col + "!='' and p.gender=1 and district_id=? and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                                + " and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " p.gender=1 and " + col + "!=%27%27   and c.status=%27active%27 and  pdd.Disability_ID=4";
                        totall = col + "!=%27%27  and c.status=%27active%27 and pdd.Disability_ID=4 ";

                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    }


                    ps = con.prepareStatement(query);
                    
                    
                    
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,fdate);
                    ps.setString(4,tdate);
              
            
                    rs = ps.executeQuery();
                    if (rs.next()) {

                        male = (rs.getInt(1));
                    }//c.Other_Cause!= ''

                    if (ll < 9)
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=4 and " + col + "=1 and p.gender=2 and district_id=? and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                                + " and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " p.gender=2 and " + col + "=1  and c.status=%27active%27 and pdd.Disability_ID=4";
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=4 ";
                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    } 
                    else 
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=4 and " + col + "!='' and p.gender=2 and district_id=? and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                                + " and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " p.gender=2 and " + col + "!=%27%27   and c.status=%27active%27 and pdd.Disability_ID=4";
                        totall = col + "!=%27%27  and pdd.Disability_ID=4 c.status=%27active%27 and ";

                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    }

                    ps = con.prepareStatement(query);
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,fdate);
                    ps.setString(4,tdate);
              
                    
                    rs = ps.executeQuery();
                    if (rs.next())
                    {
                        female = rs.getInt(1);
                    }


                    tot = male + female;
                    h1.put("tablet", tablename);
                    h1.put("columnt", totall);
                    h1.put("male", (int) male);
                    if (male != 0)
                    {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } 
                    else 
                    {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }

                    co = (String) col;
                    co = co.substring(2, co.length());

                    if (co.contains("high_fever")) {
                        co = "Meningitis";
                    }
                    h1.put("col", co);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }
                h1 = new HashMap();
                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                        + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                        + " where pdd.Disability_ID=4 and a.rw_can=1 and p.gender=1 and  "
                        + " district_id=? and mandal_id=? and c.status='Active' and p.status='Active' and a.status= 'Active' and pdd.status='Active' and t.status='Active'"
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";
                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=1 and  a.status=%27active%27 and c.status=%27active%27 and a.rw_can=1 and pdd.Disability_ID=4";
                h1.put("tablem", tablename);
                h1.put("columnm", colu);
                ps = con.prepareStatement(query);
                
                ps.setString(1,district_id);
                ps.setString(2,mandal_id);
                ps.setString(3,fdate);
                ps.setString(4,tdate);
          
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    male = rs.getDouble(1);

                }

                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                        + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                        + " where pdd.Disability_ID=4 and a.rw_can=1 and p.gender=2 and  "
                        + " district_id=? and mandal_id=? and c.status='Active' and a.status= 'Active' and p.status= 'Active' and pdd.status='Active' and t.status='Active' "
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";
                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=2 and  a.status=%27active%27 and c.status=%27active%27 and a.rw_can=1 and pdd.Disability_ID=4";
                h1.put("tablef", tablename);
                h1.put("columnf", colu);
                ps = con.prepareStatement(query); 
                
                ps.setString(1,district_id);
                ps.setString(2,mandal_id);
                ps.setString(3,fdate);
                ps.setString(4,tdate);
          
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    female = rs.getDouble(1);

                }
                tot = male + female;
                colu = "  a.rw_can=1 and a.status=%27active%27 and c.status=%27active%27 and pdd.Disability_ID=4";

                h1.put("tablet", tablename);
                h1.put("columnt", colu);
                h1.put("male", (int) male);
                if (male != 0) {
                    h1.put("mp", form.format(100 * (male / tot)));
                } else {
                    h1.put("mp", 0);
                }

                h1.put("female", (int) female);
                if (female != 0) {
                    h1.put("fp", form.format(100 * (female / tot)));
                } else {
                    h1.put("fp", 0);
                }
                h1.put("tot", (int) tot);
                if (tot != 0) {
                    h1.put("tp", "100");
                } else {
                    h1.put("tp", "0");
                }
                h1.put("col", "RW- Can perform work by Reading and Writing");
                h1.put("district", district_id);
                h1.put("mandal", mandal_id);
                h1.put("village", village_id);
                h1.put("fdate", fdate);
                h1.put("tdate", tdate);
                a.add(h1);










                String[] age = {"25", "50", "75", "90", "100"};
                String[] percentag = {"25%", "50%", "75%", "90%", "100%"};


                for (int ll = 0; ll < age.length; ll++) {
                    h1 = new HashMap();
                    col = age[ll];

                    query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                            + " on(p.person_code=pdd.person_code ) JOIN tblMental_Retardation_Details t  on(p.person_code=t.person_code)"
                            + " join tblPerson_Disability_TotalValue_Details d  on(p.person_code=d.person_code) "
                            + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active' and d.status='Active' and t.total_value =" + col + " and "
                            + " p.gender=1 and pdd.disability_id=4 and district_id=? and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                            + "BETWEEN   ? AND ?";
                    tablename = "join tblMental_Retardation_Details c on(p.person_code=c.person_code) ";

                    colu = " p.gender=1 and c.total_value=" + col + "  and c.status=%27active%27  and pdd.Disability_ID=4";

                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query); 
                    
                    
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,fdate);
                    ps.setString(4,tdate);
              
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        male = rs.getDouble(1);

                    }

                    query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                            + " on(p.person_code=pdd.person_code ) JOIN tblMental_Retardation_Details t  on(p.person_code=t.person_code)"
                            + " join tblPerson_Disability_TotalValue_Details d  on(p.person_code=d.person_code) "
                            + "  and pdd.status='Active'  and p.status='Active'  and t.status='Active' and d.status='Active' and t.total_value =" + col + " and "
                            + " p.gender=2 and pdd.disability_id= 4 and district_id=? and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                            + "BETWEEN   ? AND ?";
                    tablename = "join tblMental_Retardation_Details c on(p.person_code=c.person_code) ";

                    colu = " p.gender=2 and c.total_value=" + col + "  and c.status=%27active%27  and  pdd.Disability_ID=4";

                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query);
                    
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,fdate);
                    ps.setString(4,tdate);
              
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getDouble(1);

                    }
                    tot = male + female;
                    colu = "c.total_value=" + col + "  and c.status=%27active%27  and pdd.Disability_ID=4";
                    h1.put("tablet", tablename);
                    h1.put("columnt", colu);
                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }
                    h1.put("col", percentag[ll]);
                    kk = percentag[ll];
                    kk = kk.replace("%", "%25");
                    h1.put("coll", kk);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);



                }








            } 
            else if (!district_id.equals("0") && district_id != null && !mandal_id.equals("0") && !village_id.equals("0"))
            {

                for (int ll = 0; ll < aa.length; ll++)
                {
                    col = aa[ll];
                    h1 = new HashMap();
                    if (ll < 9) {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=4 and " + col + "=1 and p.gender=1 and district_id=? and mandal_id=? and village_id=? and c.status='Active'  and p.status= 'Active' and pdd.status='Active' and t.status='Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " p.gender=1 and c.status=%27active%27 and " + col + "=1  and pdd.Disability_ID=4";
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=4 ";
                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    } 
                    else
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=4 and " + col + "!='' and p.gender=1 and district_id=? and mandal_id=? and village_id=? and c.status='Active'  and p.status= 'Active'  and pdd.status='Active' and t.status='Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " p.gender=1 and " + col + "!=%27%27   and c.status=%27active%27 and pdd.Disability_ID=4";
                        totall = col + "!=%27%27  and c.status=%27active%27 and pdd.Disability_ID=4 ";

                        h1.put("tablem", tablename);
                        h1.put("columnm", colu);
                    }


                    ps = con.prepareStatement(query);
                    
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,village_id);
                    ps.setString(4,fdate);
                    ps.setString(5,tdate);
              
                    
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {

                        male = (rs.getInt(1));
                    }//c.Other_Cause!= ''

                    if (ll < 9) 
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=4 and " + col + "=1 and p.gender=2 and district_id=? and mandal_id=? and village_id=? and c.status='Active'  and p.status= 'Active' and pdd.status='Active' and t.status='Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " p.gender=2 and c.status=%27active%27 and " + col + "=1  and pdd.Disability_ID=4";
                        totall = col + "=1  and c.status=%27active%27 and pdd.Disability_ID=4 ";
                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    } 
                    else
                    {
                        query = "select count(*) from  dbo.tblPerson_Causes_of_Disability_Details c inner join "
                                + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                                + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                                + "join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                                + "where pdd.Disability_ID=4 and " + col + "!='' and p.gender=2 and district_id=? and mandal_id=? and village_id=? and c.status='Active'  and p.status= 'Active' and pdd.status='Active' and t.status='Active' "
                                + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                        tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) ";

                        colu = " p.gender=2 and c.status=%27active%27 and " + col + "!=%27%27   and pdd.Disability_ID=4";
                        totall = col + "!=%27%27  and c.status=%27active%27 and  pdd.Disability_ID=4 ";

                        h1.put("tablef", tablename);
                        h1.put("columnf", colu);
                    }

                    ps = con.prepareStatement(query);
                    
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,village_id);
                    ps.setString(4,fdate);
                    ps.setString(5,tdate);
              
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getInt(1);
                    }


                    tot = male + female;
                    h1.put("tablet", tablename);
                    h1.put("columnt", totall);

                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }

                    co = (String) col;
                    co = co.substring(2, co.length());

                    if (co.contains("high_fever")) {
                        co = "Meningitis";
                    }
                    h1.put("col", co);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);

                    a.add(h1);

                }


                h1 = new HashMap();
                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                        + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                        + " where pdd.Disability_ID=4 and a.rw_can=1 and p.gender=1 and  "
                        + " district_id=? and mandal_id=? and village_id=? and c.status='Active' and a.status= 'Active' and p.status= 'Active' and pdd.status='Active' and t.status='Active' "
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";
                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=1 and a.status=%27active%27 and c.status=%27active%27 and  a.rw_can=1 and pdd.Disability_ID=4";
                h1.put("tablem", tablename);
                h1.put("columnm", colu);

                ps = con.prepareStatement(query);
                
                ps.setString(1,district_id);
                ps.setString(2,mandal_id);
                ps.setString(3,village_id);
                ps.setString(4,fdate);
                ps.setString(5,tdate);
          
                
                rs = ps.executeQuery();
                if (rs.next())
                {
                    male = rs.getDouble(1);

                }

                query = "select count(*) from  tblPerson_Causes_of_Disability_Details c "
                        + " inner join dbo.tbl_Person_Disability_Cando_Details a on (a.person_code=c.person_code) "
                        + " inner join tblPerson_personal_details p on (p.person_code=c.person_code)  "
                        + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                        + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                        + " where pdd.Disability_ID=4 and a.rw_can=1 and p.gender=2 and  "
                        + " district_id=? and mandal_id=? and village_id=? and c.status='Active' and a.status= 'Active' and p.status= 'Active' and pdd.status='Active' and t.status='Active' "
                        + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date))) "
                        + "BETWEEN   ? AND ?";
                tablename = "join tblPerson_Causes_of_Disability_Details c on(p.person_code=c.person_code) join "
                        + " tbl_Person_Disability_Cando_Details a on(a.person_code=c.person_code) ";
                colu = " p.gender=2 and a.status=%27active%27 and c.status=%27active%27 and  a.rw_can=1 and pdd.Disability_ID=4";
                h1.put("tablef", tablename);
                h1.put("columnf", colu);

                ps = con.prepareStatement(query);  
                
                ps.setString(1,district_id);
                ps.setString(2,mandal_id);
                ps.setString(3,village_id);
                ps.setString(4,fdate);
                ps.setString(5,tdate);
          
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    female = rs.getDouble(1);

                }
                tot = male + female;
                colu = "  a.rw_can=1 and a.status=%27active%27 and c.status=%27active%27 and pdd.Disability_ID=4";

                h1.put("tablet", tablename);
                h1.put("columnt", colu);

                h1.put("male", (int) male);
                if (male != 0) {
                    h1.put("mp", form.format(100 * (male / tot)));
                } else {
                    h1.put("mp", 0);
                }

                h1.put("female", (int) female);
                if (female != 0) {
                    h1.put("fp", form.format(100 * (female / tot)));
                } else {
                    h1.put("fp", 0);
                }
                h1.put("tot", (int) tot);
                if (tot != 0) {
                    h1.put("tp", "100");
                } else {
                    h1.put("tp", "0");
                }
                h1.put("col", "RW- Can perform work by Reading and Writing");
                h1.put("district", district_id);
                h1.put("mandal", mandal_id);
                h1.put("village", village_id);
                h1.put("fdate", fdate);
                h1.put("tdate", tdate);
                a.add(h1);

                h1 = new HashMap();






                String[] age = {"25", "50", "75", "90", "100"};
                String[] percentag = {"25%", "50%", "75%", "90%", "100%"};


                for (int ll = 0; ll < age.length; ll++) {
                    h1 = new HashMap();
                    col = age[ll];

                    query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                            + " on(p.person_code=pdd.person_code ) JOIN tblMental_Retardation_Details t  on(p.person_code=t.person_code)"
                            + " join tblPerson_Disability_TotalValue_Details d  on(p.person_code=d.person_code) "
                            + "  and pdd.status='Active'  and p.status='Active' and d.status='Active' and t.status='Active'  and t.total_value =" + col + " and "
                            + " p.gender=1 and pdd.disability_id=2 and district_id=? and mandal_id=? and village_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                            + "BETWEEN   ? AND ?";
                    tablename = "join tblMental_Retardation_Details c on(p.person_code=c.person_code) ";

                    colu = " p.gender=1 and c.total_value=" + col + "  and c.status=%27active%27  and pdd.Disability_ID=4";

                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query);
                    
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,village_id);
                    ps.setString(4,fdate);
                    ps.setString(5,tdate);
           
                    
                    rs = ps.executeQuery();
                    if (rs.next()) 
                    {
                        male = rs.getDouble(1);

                    }

                    query = "select count(*) from dbo.tblperson_personal_details p  join tblPerson_Disability_Details pdd "
                            + " on(p.person_code=pdd.person_code ) JOIN tblMental_Retardation_Details t  on(p.person_code=t.person_code)"
                            + " join tblPerson_Disability_TotalValue_Details d  on(p.person_code=d.person_code) "
                            + "  and pdd.status='Active'  and p.status='Active'  and d.status='Active' and t.status='Active'  and t.total_value =" + col + " and "
                            + " p.gender=2 and pdd.disability_id=2 and district_id=? and mandal_id=? and village_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))"
                            + "BETWEEN   ? AND ?";
                    tablename = "join tblMental_Retardation_Details c on(p.person_code=c.person_code) ";

                    colu = " p.gender=2 and c.total_value=" + col + "  and c.status=%27active%27  and pdd.Disability_ID=4";

                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);


                    ps = con.prepareStatement(query);
                    
                    ps.setString(1,district_id);
                    ps.setString(2,mandal_id);
                    ps.setString(3,village_id);
                    ps.setString(4,fdate);
                    ps.setString(5,tdate);
           
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        female = rs.getDouble(1);

                    }
                    tot = male + female;

                    colu = "c.total_value=" + col + "  and c.status=%27active%27  and pdd.Disability_ID=4";
                    h1.put("tablet", tablename);
                    h1.put("columnt", colu);

                    h1.put("male", (int) male);
                    if (male != 0) {
                        h1.put("mp", form.format(100 * (male / tot)));
                    } else {
                        h1.put("mp", 0);
                    }

                    h1.put("female", (int) female);
                    if (female != 0) {
                        h1.put("fp", form.format(100 * (female / tot)));
                    } else {
                        h1.put("fp", 0);
                    }
                    h1.put("tot", (int) tot);
                    if (tot != 0) {
                        h1.put("tp", "100");
                    } else {
                        h1.put("tp", "0");
                    }
                    h1.put("col", percentag[ll]);
                    kk = percentag[ll];
                    kk = kk.replace("%", "%25");
                    h1.put("coll", kk);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getMaritalStatusPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getMaritalStatusPersonalDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

            DBConnection.closeStatement(ps);
        }

        return a;
    }

    public ArrayList getAppealPersonalDetails(DataSource ds, String district_id, String mandal_id, String village_id, String fromdate, String todate, String disabilityStatus, String disability, String baseDistrict, String baseMandal, String baseVillage, String basehab, String pensionPhase) throws SADAREMDBException, SQLException
    {//not changed for security audit
        ArrayList<Object> appealPersonalDetails = new ArrayList<Object>();
        Connection con = null;
        Statement cstmt = null;
        ResultSet rs = null;
        String sql = null;
        String sql1 = null;
        HashMap<String, Object> map = null;
        String disabilityId = null;
        String phase = null;
        String equation = null;
        try
        {
            con = DBConnection.getConnection();
            cstmt = con.createStatement();

            /* Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(fromDate);
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fDate);

            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(toDate);
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tDate);*/

            if (fromdate != null && fromdate.contains("-"))
            {
                //fdate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fdate));
                ;

            }
            else 
            {
                fromdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fromdate));
            }

            if (pensionPhase.equalsIgnoreCase("5"))
            {
                phase = "PHASEI','PhaseII','PhaseIV";
                equation = "in";

            } 
            else if (pensionPhase.equalsIgnoreCase("1")) 
            {
                phase = "PHASEI";
                equation = "=";
            } 
            else if (pensionPhase.equalsIgnoreCase("2"))
            {
                phase = "PHASEII";
                equation = "=";
            } 
            else if (pensionPhase.equalsIgnoreCase("4"))
            {
                phase = "PhaseIV";
                equation = "=";
            }


            if (todate != null && todate.contains("-")) 
            {
                ;
            } 
            else 
            {
                todate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(todate));
            }
            if (!disabilityStatus.equals("Total Registred")) 
            {
                sql = "SELECT P.PERSON_CODE,P.SURNAME+SPACE(2)+P.FIRST_NAME AS NAME,P.RELATION_NAME,P.AGE_YEARS,"
                        + "CASE WHEN P.GENDER='1' THEN 'Male' ELSE 'Female' END AS GENDER,"
                        + "case when D.DISABILITY_ID=1 then 'Locomotor' when D.DISABILITY_ID=2 then 'Visual' "
                        + "when D.DISABILITY_ID=3 then 'Hearing' when D.DISABILITY_ID=4 then 'Mental Retradation' "
                        + "when D.DISABILITY_ID=5 then 'Mental Illness' else 'MultipleDisability'  end as DISABILITY,"
                        + "MD.DISTRICT_NAME,MM.MANDAL_NAME,MV.VILLAGE_NAME,D.hospital_name,D.Hospital_address FROM TBLPERSON_PERSONAL_DETAILS P,"
                        + "TBLDISTRICT_DETAILS MD,TBLMANDAL_DETAILS MM,TBLVILLAGE_DETAILS  MV,"
                        + "TBLPERSON_DISABILITY_DETAILS D,TBLPERSON_DISABILITY_TOTALVALUE_DETAILS T ";

                sql1 = "SELECT P.PERSON_CODE,P.SURNAME+SPACE(2)+P.FIRST_NAME AS NAME,P.RELATION_NAME,P.AGE_YEARS,"
                        + "CASE WHEN P.GENDER='1' THEN 'Male' ELSE 'Female' END AS GENDER,"
                        + "case when R.DISABILITY_ID=1 then 'Locomotor' when R.DISABILITY_ID=2 then 'Visual' "
                        + "when R.DISABILITY_ID=3 then 'Hearing' when R.DISABILITY_ID=4 then 'Mental Retradation' "
                        + "when R.DISABILITY_ID=5 then 'Mental Illness' else 'MultipleDisability'  end as DISABILITY,"
                        + "MD.DISTRICT_NAME,MM.MANDAL_NAME,MV.VILLAGE_NAME,R.hospital_name,R.Hospital_address FROM TBLPERSON_PERSONAL_DETAILS P,"
                        + "TBLDISTRICT_DETAILS MD,TBLMANDAL_DETAILS MM,TBLVILLAGE_DETAILS  MV,"
                        + " TBLREJECTPERSON_DETAILS R ";

                //    if (district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0") && baseDistrict != null) {
                if (!baseDistrict.equals("") && baseMandal.equals("") && baseVillage.equals(""))
                {
                    sql += "WHERE SUBSTRING(D.PERSON_CODE,1,2) = '" + baseDistrict + "' AND SUBSTRING(T.PERSON_CODE,1,2) = '" + baseDistrict + "'";
                    if (disabilityStatus.equals("Locomotor-Rejected") || disabilityStatus.equals("Visual-Rejected") || disabilityStatus.equals("Visible-Rejected") || disabilityStatus.equals("Hearing-Rejected") || disabilityStatus.equals("Mental Retradation-Rejected") || disabilityStatus.equals("MentalIllness-Rejected") || disabilityStatus.equals("Multiple Disability-Rejected"))
                    {


                        sql1 += "WHERE SUBSTRING(R.PERSON_CODE,1,2) = '" + baseDistrict + "' ";


                    }
                }

                // if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0") && baseMandal != null) {
                if (!baseDistrict.equals("") && baseVillage.equals("") && !baseMandal.equals(""))
                {

                    sql += "WHERE SUBSTRING(D.PERSON_CODE,1,2) = '" + baseDistrict + "' AND SUBSTRING(T.PERSON_CODE,1,2) = '" + baseDistrict + "' AND SUBSTRING(D.PERSON_CODE,6,2) = '" + baseMandal + "' AND SUBSTRING(T.PERSON_CODE,6,2) = '" + baseMandal + "'";
                    if (disabilityStatus.equals("Locomotor-Rejected") || disabilityStatus.equals("Visual-Rejected") || disabilityStatus.equals("Visible-Rejected") || disabilityStatus.equals("Hearing-Rejected") || disabilityStatus.equals("Mental Retradation-Rejected") || disabilityStatus.equals("MentalIllness-Rejected") || disabilityStatus.equals("Multiple Disability-Rejected"))
                    {


                        sql1 += "WHERE SUBSTRING(R.PERSON_CODE,1,2) = '" + baseDistrict + "' AND SUBSTRING(R.PERSON_CODE,6,2) = '" + baseMandal + "' ";


                    }

                } //else if (district_id.equals("0") && !mandal_id.equals("0") && village_id.equals("0")) {
                else if (!baseDistrict.equals("") && !baseMandal.equals("") && !baseVillage.equals("") && basehab.equals(""))
                {

                    sql += "WHERE SUBSTRING(D.PERSON_CODE,1,2) = '" + baseDistrict + "' AND SUBSTRING(T.PERSON_CODE,1,2) = '" + baseDistrict + "' AND SUBSTRING(D.PERSON_CODE,6,2) = '" + baseMandal + "' AND SUBSTRING(T.PERSON_CODE,6,2) = '" + baseMandal + "' AND SUBSTRING(D.PERSON_CODE,8,3) = '" + baseVillage + "' AND SUBSTRING(T.PERSON_CODE,8,3) = '" + baseVillage + "' ";
                    if (disabilityStatus.equals("Locomotor-Rejected") || disabilityStatus.equals("Visual-Rejected") || disabilityStatus.equals("Visible-Rejected") || disabilityStatus.equals("Hearing-Rejected") || disabilityStatus.equals("Mental Retradation-Rejected") || disabilityStatus.equals("MentalIllness-Rejected") || disabilityStatus.equals("Multiple Disability-Rejected"))
                    {


                        sql1 += "WHERE SUBSTRING(R.PERSON_CODE,1,2) = '" + baseDistrict + "' AND SUBSTRING(R.PERSON_CODE,6,2) = '" + baseMandal + "' AND SUBSTRING(R.PERSON_CODE,8,3) = '" + baseVillage + "' ";


                    }

                }//else if(!district_id.equals("0") && !mandal_id.equals("0") && village_id.equals("0") && baseVillage!=null){
                else if (!baseDistrict.equals("") && !baseMandal.equals("") && !baseVillage.equals("") && !basehab.equals(""))
                {


                    if (disabilityStatus.equals("Locomotor-Rejected") || disabilityStatus.equals("Visual-Rejected") || disabilityStatus.equals("Visible-Rejected") || disabilityStatus.equals("Hearing-Rejected") || disabilityStatus.equals("Mental Retradation-Rejected") || disabilityStatus.equals("MentalIllness-Rejected") || disabilityStatus.equals("Multiple Disability-Rejected")) 
                    {


                        sql1 += "WHERE SUBSTRING(R.PERSON_CODE,1,2) = '" + baseDistrict + "' AND SUBSTRING(R.PERSON_CODE,6,2) = '" + baseMandal + "' AND SUBSTRING(R.PERSON_CODE,8,3) = '" + baseVillage + "' AND SUBSTRING(R.PERSON_CODE,11,2)='" + basehab + "' ";
                        ;


                    }

                    sql += "WHERE SUBSTRING(D.PERSON_CODE,1,2) = '" + district_id + "' AND SUBSTRING(T.PERSON_CODE,1,2) = '" + district_id + "' AND SUBSTRING(D.PERSON_CODE,6,2) = '" + baseMandal + "' AND SUBSTRING(T.PERSON_CODE,6,2) = '" + baseMandal + "' AND SUBSTRING(D.PERSON_CODE,8,3) = '" + baseVillage + "' AND SUBSTRING(T.PERSON_CODE,8,3) = '" + baseVillage + "' "
                            + " AND SUBSTRING(D.PERSON_CODE,11,2) = '" + basehab + "' AND SUBSTRING(T.PERSON_CODE,11,2) = '" + basehab + "' ";
                }
                if (!disabilityStatus.equals("Total Eligible"))
                {
                    sql += "AND D.DISABILITY_ID = '" + disability + "'";
                    sql1 += " AND R.DISABILITY_ID= '" + disability + "'";
                }


                sql += "AND D.PERSON_CODE   = T.PERSON_CODE  AND P.PERSON_CODE = T.PERSON_CODE AND "
                        + "D.PERSON_CODE = P.PERSON_CODE AND P.DISTRICT_ID = MD.DISTRICT_ID AND  "
                        + "P.DISTRICT_ID = MM.DISTRICT_ID AND P.MANDAL_ID = MM.MANDAL_ID AND "
                        + "P.DISTRICT_ID = MV.DISTRICT_ID AND P.MANDAL_ID = MV.MANDAL_ID AND "
                        + "P.VILLAGE_ID = MV.VILLAGE_ID AND D.CATEGORYID = 2 AND T.CATEGORYID = 2  "
                        + "AND D.STATUS = 'Active' AND T.STATUS = 'Active' AND "
                        + " (DATEADD(DD,0,DATEDIFF(DD,0,T.updated_date))) "
                        + "BETWEEN   '" + fromdate + "' AND '" + todate + "' AND P.PENSIONPHASE " + equation + " ('" + phase + "') AND ";

                if (disabilityStatus.equals("Locomotor-Rejected") || disabilityStatus.equals("Visual-Rejected") || disabilityStatus.equals("Visible-Rejected") || disabilityStatus.equals("Hearing-Rejected") || disabilityStatus.equals("Mental Retradation-Rejected") || disabilityStatus.equals("MentalIllness-Rejected") || disabilityStatus.equals("Multiple Disability-Rejected"))
                {
                    sql += "T.TOTALDISABILITY < 40";

                } 
                else 
                {
                    sql += "T.TOTALDISABILITY >=40 ";
                }

                /*  sql += "AND (DATEADD(DD,0,DATEDIFF(DD,0,T.UPDATED_DATE))) "
                + "BETWEEN '" + fromdate + "' AND '" + todate + "' ";*/

            } 
            else if (disabilityStatus.equals("Total Registred"))
            {

                sql = "SELECT P.PERSON_CODE,P.SURNAME+SPACE(2)+P.FIRST_NAME AS NAME,P.RELATION_NAME,P.AGE_YEARS,"
                        + "CASE WHEN P.GENDER='1' THEN 'Male' ELSE 'Female' END AS GENDER,'-' AS  DISABILITY,D.DISTRICT_NAME,"
                        + "M.MANDAL_NAME,V.VILLAGE_NAME FROM TBLPERSON_PERSONAL_DETAILS P,"
                        + "APPELLATEAUTHORITYANDTEMPORARY_REGISTRATIONDETAILS A,TBLDISTRICT_DETAILS D,"
                        + "TBLMANDAL_DETAILS M,TBLVILLAGE_DETAILS V WHERE "
                        + "P.PERSON_CODE = A.PERSON_CODE AND district_id = D.DISTRICT_ID  "
                        + "AND district_id = M.DISTRICT_ID AND mandal_id = M.MANDAL_ID  "
                        + "AND district_id = V.DISTRICT_ID AND mandal_id = V.MANDAL_ID "
                        + "AND village_id = V.VILLAGE_ID AND P.PENSIONPHASE " + equation + " ('" + phase + "')";

                /*  if (district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0") && baseDistrict != null) {

                sql += "AND SUBSTRING(A.PERSON_CODE,1,2) = '" + baseDistrict + "' ORDER BY P.PERSON_CODE";

                }

                if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0") && baseMandal != null) {

                sql += "AND SUBSTRING(A.PERSON_CODE,1,2) = '" + district_id + "' AND SUBSTRING(A.PERSON_CODE,6,2) = '" + baseMandal + "' ORDER BY P.PERSON_CODE";

                } else if (district_id.equals("0") && !mandal_id.equals("0") && village_id.equals("0")) {

                sql += "AND SUBSTRING(A.PERSON_CODE,1,2) = '" + district_id + "' AND SUBSTRING(A.PERSON_CODE,6,2) = '" + baseMandal + "' AND SUBSTRING(D.PERSON_CODE,8,3) = '" + baseVillage + "' ORDER BY P.PERSON_CODE";

                }*/
                if (!baseDistrict.equals("") && baseMandal.equals("") && baseVillage.equals(""))
                {

                    sql += "AND SUBSTRING(A.PERSON_CODE,1,2) = '" + baseDistrict + "' ORDER BY P.PERSON_CODE";

                }

                if (!baseDistrict.equals("") && baseVillage.equals("") && !baseMandal.equals(""))
                {

                    sql += "AND SUBSTRING(A.PERSON_CODE,1,2) = '" + baseDistrict + "' AND SUBSTRING(A.PERSON_CODE,6,2) = '" + baseMandal + "' ORDER BY P.PERSON_CODE";

                } 
                else if (!baseDistrict.equals("") && !baseMandal.equals("") && !baseVillage.equals("") && basehab.equals(""))
                {

                    sql += "AND SUBSTRING(A.PERSON_CODE,1,2) = '" + baseDistrict + "' AND SUBSTRING(A.PERSON_CODE,6,2) = '" + baseMandal + "' AND SUBSTRING(A.PERSON_CODE,8,3) = '" + baseVillage + "' ORDER BY P.PERSON_CODE";

                } 
                else if (!baseDistrict.equals("") && !baseMandal.equals("") && !baseVillage.equals("") && !basehab.equals(""))
                {

                    sql += "AND SUBSTRING(A.PERSON_CODE,1,2) = '" + baseDistrict + "' AND SUBSTRING(A.PERSON_CODE,6,2) = '" + baseMandal + "' AND SUBSTRING(A.PERSON_CODE,8,3) = '" + baseVillage + "' AND SUBSTRING(A.PERSON_CODE,11,2)='" + basehab + "' ORDER BY P.PERSON_CODE";

                }

            }


            sql1 += " AND P.PERSON_CODE   = R.PERSON_CODE  AND P.DISTRICT_ID = MD.DISTRICT_ID AND  "
                    + "P.DISTRICT_ID = MM.DISTRICT_ID AND P.MANDAL_ID = MM.MANDAL_ID AND "
                    + "P.DISTRICT_ID = MV.DISTRICT_ID AND P.MANDAL_ID = MV.MANDAL_ID AND "
                    + "P.VILLAGE_ID = MV.VILLAGE_ID AND R.CATEGORYID = 2  "
                    + "AND R.STATUS = 'Active'  AND "
                    + " (DATEADD(DD,0,DATEDIFF(DD,0,R.updated_date))) "
                    + "BETWEEN   '" + fromdate + "' AND '" + todate + "' AND  P.PENSIONPHASE " + equation + " ('" + phase + "') ";

            if (disabilityStatus.equals("Locomotor-Rejected") || disabilityStatus.equals("Visual-Rejected") || disabilityStatus.equals("Visible-Rejected") || disabilityStatus.equals("Hearing-Rejected") || disabilityStatus.equals("Mental Retradation-Rejected") || disabilityStatus.equals("MentalIllness-Rejected") || disabilityStatus.equals("Multiple Disability-Rejected"))
            {
                rs = cstmt.executeQuery(sql1);

                if (rs != null)
                {

                    while (rs.next()) 
                    {
                        map = new HashMap<String, Object>();
                        map.put("personCode", rs.getString(1));
                        map.put("name", rs.getString(2));
                        map.put("relationName", rs.getString(3));
                        map.put("age", rs.getString(4));
                        map.put("gender", rs.getString(5));
                        map.put("disability", rs.getString(6));
                        map.put("district", rs.getString(7));
                        map.put("mandal", rs.getString(8));
                        map.put("village", rs.getString(9));

                        map.put("venuename", rs.getString(10));
                        map.put("venueadd", rs.getString(11));
                        appealPersonalDetails.add(map);

                    }
                }
            }



            rs = cstmt.executeQuery(sql);

            if (rs != null)
            {

                while (rs.next())
                {
                    map = new HashMap<String, Object>();
                    map.put("personCode", rs.getString(1));
                    map.put("name", rs.getString(2));
                    map.put("relationName", rs.getString(3));
                    map.put("age", rs.getString(4));
                    map.put("gender", rs.getString(5));
                    map.put("disability", rs.getString(6));
                    map.put("district", rs.getString(7));
                    map.put("mandal", rs.getString(8));
                    map.put("village", rs.getString(9));
                    if (!disabilityStatus.equals("Total Registred"))
                    {
                        map.put("venuename", rs.getString(10));
                        map.put("venueadd", rs.getString(11));
                    }
                    appealPersonalDetails.add(map);

                }
            }


        } 
        catch (SQLException sqlEx)
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAppealPersonalDetails", "FunctionalReportDAO", "DataBase");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getAppealPersonalDetails");
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAppealPersonalDetails", "FunctionalReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getAppealPersonalDetails");
        } 
        finally
        {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }

        return appealPersonalDetails;
    }

    public ArrayList getOHFunctionalneedsCount(DataSource ds, String district_id, String mandal_id, String village_id, String fdate, String tdate)
            throws SADAREMDBException, SQLException {


        String tablename = null, colu = null, totall = null;
        String colll = null;

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;
        ArrayList data = new ArrayList();
        String query = null;
        String type = null;
        PreparedStatement ps = null;
        String col = null;

        NumberFormat form = NumberFormat.getInstance();
        form.setMinimumIntegerDigits(2);
        form.setMinimumFractionDigits(2);
        form.setMaximumFractionDigits(2);
        HashMap h1 = null;//Meningitis

        String[] name2 = {"Surgery ", "White cane / Blind stick", "Braille Equipments", "Arithmetic Frames/ Abacus",
            "Low Vision Aids (Spectacles, Magnifiers)", "Speech Synthesizer",
            "Braille Short Hand Machines/ Type Writers", "Talking Watch/ Calculator", "Ay ADL Equipment",
            "Visual Impairment Other Special needs"};
        String[] name = {"Physiotherapy  (Below 3 years children)", "Occupational Therapy (Below 3 years)", "Physiotherapy (Above 3 years)",
            "Occupational  Therapy (Above 3 years)", "Surgery",
            "Wheel Chair  - Small", "Wheel Chair - Large", "Tricycle - Small",
            "Tricycle - Large", "Walking Stick - Small", "Walking Stick - Large",
            "Axillary Crutch - Small",
            "Axillary Crutch - Medium",
            "Axillary Crutch - Large",
            "Axillary Crutch - Extra Large",
            "Elbow Crutch - Small",
            "Elbow Crutch - Medium",
            "Elbow Crutch - Large",
            "Elbow Crutch - Extra Large",
            "Gutter Crutch - Small",
            "Gutter Crutch - Medium",
            "Gutter Crutch - Large",
            "Gutter Crutch - Extra Large",
            "Tripod Crutch - Small",
            "Tripod Crutch - Medium",
            "Tripod Crutch - Large",
            "Tripod Crutch - Extra Large",
            "Walking Frame/ walker - Small", "Walking Frame/ walker - Large", "Aeroplane Splint",
            "Figure-8 Splint", "Fore-arm Splint", "Hand Splint", "HKAFO",
            "KAFO", "AFO", "Knee Orthosis", "DB Splint", "Modified Shoe (Surgical shoe)",
            "Serial Casting (for CTEV)", "Cervical Collar", "LS Brace", "TLSO Brace (for scoliosis/ kyphosis)",
            "Shoulder Prosthesis", "Above Elbow  Prosthesis",
            "Elbow Disarticulation Prosthesis", "Below Elbow  Prosthesis",
            "Wrist Disarticulation Prosthesis", "Hand Prosthesis",
            "Cosmetic Finger Prosthesis", "Hip Disarticulation Prosthesis",
            "Above Knee Prosthesis", "Knee Disarticulation Prosthesis",
            "Below Knee Prosthesis", "Syme's Prosthesis", "Partial Foot Prosthesis",
            "Feeding", "Toileting/Bathing'", "Brushing", "Combing",
            "Dressing", "Writing", "Driving/Cycling", "Bed Transfer", "Special Needs Others"
        };

        String[] name1 = {"Early Education (For children below 5 years)",
            "Home Based Education", "Special School", "Inclusive education",
            "For Employment in public/ pvt sector", "For self employment ", "Individual (PwD)",
            "Family", "General Needs Others"};
        String[] aa = {"c.Physiotherapyforthreeyears='physiotheraphy'",
            "c.OccupationalTherapy='occupationaltherapy'", "c.Physiotherapy='physiotherapy'",
            "c.OccupationalTherapy='occupationaltherapy'", "c.Surgery is not null",
            "c.WheelChair='Small Wheel Chair'", "c.WheelChair='Large Wheel Chair'", "c.Tricycle='Small Tricycle'",
            "c.Tricycle='Large Tricycle'", "c.WalkingStick='Small Walking Stick'",
            "c.WalkingStick='Large Walking Stick'",
            "c.CrutchesSubType='Axillary' and c.CrutchesType='Small Crutches'",
            "c.CrutchesSubType='Axillary' and c.CrutchesType='Medium Crutches'",
            "c.CrutchesSubType='Axillary' and c.CrutchesType='Large Crutches'",
            "c.CrutchesSubType='Axillary' and c.CrutchesType='Extra Large Crutches'",
            "c.CrutchesSubType= 'Elbow' and c.CrutchesType='Small Crutches'",
            "c.CrutchesSubType= 'Elbow' and c.CrutchesType='Medium Crutches'",
            "c.CrutchesSubType= 'Elbow' and c.CrutchesType='Large Crutches'",
            "c.CrutchesSubType= 'Elbow' and c.CrutchesType='Extra Large Crutches'",
            "c.CrutchesSubType= 'Gutter' and c.CrutchesType='Small Crutches'",
            "c.CrutchesSubType= 'Gutter' and c.CrutchesType='Medium Crutches'",
            "c.CrutchesSubType=  'Gutter' and c.CrutchesType='Large Crutches'",
            "c.CrutchesSubType= 'Gutter' and c.CrutchesType='Extra Large Crutches'",
            "c.CrutchesSubType= 'Tripod' and c.CrutchesType='Small Crutches'",
            "c.CrutchesSubType=  'Tripod' and c.CrutchesType='Medium Crutches'",
            "c.CrutchesSubType= 'Tripod' and c.CrutchesType='Large Crutches'",
            "c.CrutchesSubType= 'Tripod' and c.CrutchesType='Extra Large Crutches'",
            "c.WalkingFrame='Small WalkingFrame'", "c.WalkingFrame='Large WalkingFrame'", "c.AeroplaneSplint='Aeroplane Splint'",
            "c.FigureEightSplint='Figure-8 Splint'", "c.ForeArmSplint='ForeArm Splint'", "c.HandSplint='Hand Splint'", "c.HKAFO='HKAFO'",
            "c.KAFO='KAFO'", "c.AFO='AFO'", "c.KneeOrthosis='Knee Orthosis'", "c.DBSplint='DB Splint'", "c.ModifiedShoe='Modified Shoe'",
            "c.SerialCasting='Serial Casting'", "c.CervicalCollar='Cervical Collar'", "c.LBBrace='LS Brace'", "c.TLSOBrace='TLSO Brace'",
            "c.ShoulderProsthesis='Shoulder Prosthesis'", "c.AboveElbowProsthesis='Above Elbow  Prosthesis'",
            "c.ElbowDisarticulationProsthesis='Elbow Disarticulation Prosthesis'", "c.BelowElbowProsthesis='Below Elbow  Prosthesis'",
            "c.WristDisarticulationProsthesis='Wrist Disarticulation Prosthesis'", "c.HandProsthesis='Hand Prosthesis'",
            "c.CosmeticFingerProsthesis='Cosmetic Finger Prosthesis'", "c.HipDisarticulationProsthesis='Hip Disarticulation Prosthesis'",
            "c.AboveKneeProsthesis='Above Knee Prosthesis'", "c.KneeDisarticulationProsthesis='Knee Disarticulation Prosthesis'",
            "c.BelowKneeProsthesis='Below Knee Prosthesis'", "c.SymesProsthesis='Symes Prosthesis'", "c.PartialFootProsthesis='Partial Foot Prosthesis'",
            "c.Feeding='Feeding'", "c.Toileting='Toileting/Bathing'", "c.Brushing='Brushing'", "c.Combing='Combing'",
            "c.Dressing='Dressing'", "c.Writing='Writing'", "c.Driving='Driving/Cycling'", "c.BedTransfer='Bed Transfer'", "c.anyother is not null"
        };

        String[] aa1 = {"c.earlyeducation ='Early Education Services'", "c.educationservices ='Home Based Education'", "c.educationservices ='Special School'",
            "c.educationservices ='Inclusive Education'", "c.vocationaltraining ='For employment in public/ pvt. sector'",
            "c.vocationaltraining ='For self-employment'", "c.individual ='individual'",
            "c.family ='family' ", "c.otherservices!=''"
        };



        ArrayList a = new ArrayList();



        double tot = 0, tl = 0, tg = 0, tlp = 0, tgp = 0, tot1 = 0;
        int less14 = 0, great14 = 0, l14 = 0, g14 = 0;
        double l1 = 0, l2 = 0, l3 = 0, g1 = 0, g2 = 0, g3 = 0, t1 = 0, t2 = 0, t3 = 0;

        if (district_id == null) {
            district_id = "0";
        }
        if (mandal_id == null) {
            mandal_id = "0";
        }
        if (village_id == null) {
            village_id = "0";
        }



        try {
            if (fdate != null && fdate.contains("-")) {
                //fdate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fdate));
                ;

            } else {
                fdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
            }

            if (tdate != null && tdate.contains("-")) {
                ;
            } else {
                tdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(tdate));
            }
            con = DBConnection.getConnection();
            stmt = con.createStatement();

            if (district_id.equals("0")) {
                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblPerson_LocomotorNeeds_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=1 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblPerson_LocomotorNeeds_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27 and " + colll + "  and  pdd.Disability_ID=1 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, fdate);
                    ps.setString(2, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                        l14 = (rs.getInt(1));

                    }
                    if (ll < 4) {
                        l1 = l1 + less14;
                    } else if (ll >= 4 && ll <= 6) {
                        if (ll == 4) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 7 && ll < 9) {
                        if (ll == 7) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 9 && ll <= 10) {
                        if (ll == 9) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 11 && ll < 27) {
                        if (ll == 11) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 27 && ll <= 28) {
                        if (ll == 27) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 29 && ll <= 32) {
                        if (ll == 29) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 33 && ll <= 39) {
                        if (ll == 33) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 40 && ll <= 42) {
                        if (ll == 40) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 43 && ll <= 49) {
                        if (ll == 43) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 50 && ll <= 55) {
                        if (ll == 50) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 56 && ll <= 63) {
                        if (ll == 56) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    }





                    query = "select count(*) from  dbo.tblPerson_LocomotorNeeds_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=1 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblPerson_LocomotorNeeds_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=1 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, fdate);
                    ps.setString(2, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                        g14 = rs.getInt(1);

                    }

                    if (ll < 4) {
                        g1 = g1 + great14;
                    } else if (ll >= 4 && ll <= 6) {
                        if (ll == 4) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 7 && ll < 9) {
                        if (ll == 7) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 9 && ll <= 10) {
                        if (ll == 9) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 11 && ll < 27) {
                        if (ll == 11) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 27 && ll <= 28) {
                        if (ll == 27) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 29 && ll <= 32) {
                        if (ll == 29) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 33 && ll <= 39) {
                        if (ll == 33) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 40 && ll <= 42) {
                        if (ll == 40) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 43 && ll <= 49) {
                        if (ll == 43) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 50 && ll <= 55) {
                        if (ll == 50) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 56 && ll <= 63) {
                        if (ll == 56) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    }





                    tot = l14 + g14;
                    h1.put("l", (int) l14);
                    if (l14 != 0) {
                        h1.put("lp", form.format(100 * (l14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) g14);
                    if (g14 != 0) {
                        h1.put("gp", form.format(100 * (g14 / tot)));

                    } else {
                        h1.put("gp", 0);



                    }
                    h1.put("col", name[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
//if(ll==7){
                    h1.put("tl", l1);//tl
                    h1.put("tg", g1);
                    tot1 = l1 + g1;
                    if (l1 != 0) {
                        h1.put("tlp", form.format(100 * (l1 / tot1)));

                    } else {
                        h1.put("tlp", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp", form.format(100 * (g1 / tot1)));

                    } else {
                        h1.put("tgp", 0);



//}

                    }
                    a.add(h1);


                }
                l1 = 0;
                g1 = 0;
                t1 = 0;
                tot = 0;
                tot1 = 0;
                l1 = 0;
                g1 = 0;
                t1 = 0;

                for (int ll = 0; ll < aa1.length; ll++) {
                    col = aa1[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=1 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=1 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, fdate);
                    ps.setString(2, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 4 && ll > 0) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        l3 = l3 + less14;
                    }
                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=1 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=1 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);


                    ps = con.prepareStatement(query);
                    ps.setString(1, fdate);
                    ps.setString(2, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 4 && ll > 0) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        g3 = g3 + great14;
                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);

                    }
                    h1.put("col", name1[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }
            } else if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) {
                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblPerson_LocomotorNeeds_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=1 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'  and district_id=?"
                            + "  and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblPerson_LocomotorNeeds_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27 and " + colll + "  and  pdd.Disability_ID=1 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, fdate);
                    ps.setString(3, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                        l14 = (rs.getInt(1));

                    }
                    if (ll < 4) {
                        l1 = l1 + less14;
                    } else if (ll >= 4 && ll <= 6) {
                        if (ll == 4) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 7 && ll < 9) {
                        if (ll == 7) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 9 && ll <= 10) {
                        if (ll == 9) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 11 && ll < 27) {
                        if (ll == 11) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 27 && ll <= 28) {
                        if (ll == 27) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 29 && ll <= 32) {
                        if (ll == 29) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 33 && ll <= 39) {
                        if (ll == 33) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 40 && ll <= 42) {
                        if (ll == 40) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 43 && ll <= 49) {
                        if (ll == 43) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 50 && ll <= 55) {
                        if (ll == 50) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 56 && ll <= 63) {
                        if (ll == 56) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    }



                    query = "select count(*) from  dbo.tblPerson_LocomotorNeeds_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=1 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=?"
                            + "  and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblPerson_LocomotorNeeds_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=1 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);


                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, fdate);
                    ps.setString(3, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                        g14 = rs.getInt(1);

                    }

                    if (ll < 4) {
                        g1 = g1 + great14;
                    } else if (ll >= 4 && ll <= 6) {
                        if (ll == 4) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 7 && ll < 9) {
                        if (ll == 7) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 9 && ll <= 10) {
                        if (ll == 9) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 11 && ll < 27) {
                        if (ll == 11) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 27 && ll <= 28) {
                        if (ll == 27) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 29 && ll <= 32) {
                        if (ll == 29) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 33 && ll <= 39) {
                        if (ll == 33) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 40 && ll <= 42) {
                        if (ll == 40) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 43 && ll <= 49) {
                        if (ll == 43) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 50 && ll <= 55) {
                        if (ll == 50) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 56 && ll <= 63) {
                        if (ll == 56) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    }
                    tot = l14 + g14;
                    h1.put("l", (int) l14);
                    if (l14 != 0) {
                        h1.put("lp", form.format(100 * (l14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) g14);
                    if (g14 != 0) {
                        h1.put("gp", form.format(100 * (g14 / tot)));

                    } else {
                        h1.put("gp", 0);
                    }
                    h1.put("col", name[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);


                    h1.put("tl", (int) l1);
                    h1.put("tg", (int) g1);
                    tot1 = l1 + g1;
                    if (l1 != 0) {
                        h1.put("tlp", form.format(100 * (l1 / tot1)));

                    } else {
                        h1.put("tlp", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp", form.format(100 * (g1 / tot1)));

                    } else {
                        h1.put("tgp", 0);

                    }
                    a.add(h1);

                }
                l1 = 0;
                g1 = 0;
                t1 = 0;
                tot = 0;
                tot1 = 0;
                l1 = 0;
                g1 = 0;
                t1 = 0;

                for (int ll = 0; ll < aa1.length; ll++) {
                    col = aa1[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=1 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=?"
                            + "  and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=1 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, fdate);
                    ps.setString(3, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }

                    if (ll < 4 && ll > 0) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        l3 = l3 + less14;
                    }
                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=1 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=?"
                            + "  and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=1 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, fdate);
                    ps.setString(3, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 4 && ll > 0) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        g3 = g3 + great14;
                    }


                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);



                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);
                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);
                    }
                    h1.put("col", name1[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }

            }// "' and mandal_id='"+mandal_id +
            else if (!district_id.equals("0") && !mandal_id.equals("0") && village_id.equals("0")) {
                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblPerson_LocomotorNeeds_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=1 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'  and district_id=? and mandal_id=? "
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblPerson_LocomotorNeeds_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27 and " + colll + "  and  pdd.Disability_ID=1 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, fdate);
                    ps.setString(4, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                        l14 = (rs.getInt(1));

                    }
                    if (ll < 4) {
                        l1 = l1 + less14;
                    } else if (ll >= 4 && ll <= 6) {
                        if (ll == 4) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 7 && ll < 9) {
                        if (ll == 7) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 9 && ll <= 10) {
                        if (ll == 9) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 11 && ll < 27) {
                        if (ll == 11) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 27 && ll <= 28) {
                        if (ll == 27) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 29 && ll <= 32) {
                        if (ll == 29) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 33 && ll <= 39) {
                        if (ll == 33) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 40 && ll <= 42) {
                        if (ll == 40) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 43 && ll <= 49) {
                        if (ll == 43) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 50 && ll <= 55) {
                        if (ll == 50) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 56 && ll <= 63) {
                        if (ll == 56) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    }


                    query = "select count(*) from  dbo.tblPerson_LocomotorNeeds_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=1 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? "
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblPerson_LocomotorNeeds_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=1 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, fdate);
                    ps.setString(4, tdate);
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        great14 = rs.getInt(1);
                        g14 = rs.getInt(1);

                    }

                    if (ll < 4) {
                        g1 = g1 + great14;
                    } else if (ll >= 4 && ll <= 6) {
                        if (ll == 4) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 7 && ll < 9) {
                        if (ll == 7) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 9 && ll <= 10) {
                        if (ll == 9) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 11 && ll < 27) {
                        if (ll == 11) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 27 && ll <= 28) {
                        if (ll == 27) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 29 && ll <= 32) {
                        if (ll == 29) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 33 && ll <= 39) {
                        if (ll == 33) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 40 && ll <= 42) {
                        if (ll == 40) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 43 && ll <= 49) {
                        if (ll == 43) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 50 && ll <= 55) {
                        if (ll == 50) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 56 && ll <= 63) {
                        if (ll == 56) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    }
                    tot = l14 + g14;
                    h1.put("l", (int) l14);
                    if (l14 != 0) {
                        h1.put("lp", form.format(100 * (l14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) g14);
                    if (g14 != 0) {
                        h1.put("gp", form.format(100 * (g14 / tot)));

                    } else {
                        h1.put("gp", 0);
                    }
                    h1.put("col", name[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    h1.put("tl", (int) l1);
                    h1.put("tg", (int) g1);
                    tot1 = l1 + g1;
                    if (l1 != 0) {
                        h1.put("tlp", form.format(100 * (l1 / tot1)));

                    } else {
                        h1.put("tlp", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp", form.format(100 * (g1 / tot1)));

                    } else {
                        h1.put("tgp", 0);

                    }
                    a.add(h1);
                }
                l1 = 0;
                g1 = 0;
                t1 = 0;
                tot = 0;
                tot1 = 0;
                l1 = 0;
                g1 = 0;
                t1 = 0;

                for (int ll = 0; ll < aa1.length; ll++) {
                    col = aa1[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=1 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? "
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=1 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, fdate);
                    ps.setString(4, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 4 && ll > 0) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        l3 = l3 + less14;
                    }
                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=1 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? "
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=1 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, fdate);
                    ps.setString(4, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 4 && ll > 0) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        g3 = g3 + great14;
                    }


                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);
                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);
                    }
                    h1.put("col", name1[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }



            } else if (!district_id.equals("0") && district_id != null && !mandal_id.equals("0") && !village_id.equals("0")) {

                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblPerson_LocomotorNeeds_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=1 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'  and district_id=? and mandal_id=? and village_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblPerson_LocomotorNeeds_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27 and " + colll + "  and  pdd.Disability_ID=1 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, village_id);
                    ps.setString(4, fdate);
                    ps.setString(5, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                        l14 = (rs.getInt(1));

                    }
                    if (ll < 4) {
                        l1 = l1 + less14;
                    } else if (ll >= 4 && ll <= 6) {
                        if (ll == 4) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 7 && ll < 9) {
                        if (ll == 7) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 9 && ll <= 10) {
                        if (ll == 9) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 11 && ll < 27) {
                        if (ll == 11) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 27 && ll <= 28) {
                        if (ll == 27) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 29 && ll <= 32) {
                        if (ll == 29) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 33 && ll <= 39) {
                        if (ll == 33) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 40 && ll <= 42) {
                        if (ll == 40) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 43 && ll <= 49) {
                        if (ll == 43) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 50 && ll <= 55) {
                        if (ll == 50) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    } else if (ll >= 56 && ll <= 63) {
                        if (ll == 56) {
                            l1 = 0;

                        }
                        l1 = l1 + less14;
                    }


                    query = "select count(*) from  dbo.tblPerson_LocomotorNeeds_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=1 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? and village_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblPerson_LocomotorNeeds_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=1 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, village_id);
                    ps.setString(4, fdate);
                    ps.setString(5, tdate);
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        great14 = rs.getInt(1);
                        g14 = rs.getInt(1);

                    }

                    if (ll < 4) {
                        g1 = g1 + great14;
                    } else if (ll >= 4 && ll <= 6) {
                        if (ll == 4) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 7 && ll < 9) {
                        if (ll == 7) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 9 && ll <= 10) {
                        if (ll == 9) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 11 && ll < 27) {
                        if (ll == 11) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 27 && ll <= 28) {
                        if (ll == 27) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 29 && ll <= 32) {
                        if (ll == 29) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 33 && ll <= 39) {
                        if (ll == 33) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 40 && ll <= 42) {
                        if (ll == 40) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 43 && ll <= 49) {
                        if (ll == 43) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 50 && ll <= 55) {
                        if (ll == 50) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    } else if (ll >= 56 && ll <= 63) {
                        if (ll == 56) {
                            g1 = 0;

                        }
                        g1 = g1 + great14;
                    }





                    tot = l14 + g14;
                    h1.put("l", (int) l14);
                    if (l14 != 0) {
                        h1.put("lp", form.format(100 * (l14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) g14);
                    if (g14 != 0) {
                        h1.put("gp", form.format(100 * (g14 / tot)));

                    } else {
                        h1.put("gp", 0);




                    }
                    h1.put("col", name[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);

                    h1.put("tl", (int) l1);
                    h1.put("tg", (int) g1);
                    tot1 = l1 + g1;
                    if (l1 != 0) {
                        h1.put("tlp", form.format(100 * (l1 / tot1)));

                    } else {
                        h1.put("tlp", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp", form.format(100 * (g1 / tot1)));

                    } else {
                        h1.put("tgp", 0);


                    }
                    a.add(h1);
                }

                l1 = 0;
                g1 = 0;
                t1 = 0;
                tot = 0;
                tot1 = 0;

                for (int ll = 0; ll < aa1.length; ll++) {
                    col = aa1[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=1 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? and village_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=1 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);


                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, village_id);
                    ps.setString(4, fdate);
                    ps.setString(5, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }

                    if (ll < 4 && ll > 0) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        l3 = l3 + less14;
                    }


                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=1 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? and village_id=? "
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=1 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, village_id);
                    ps.setString(4, fdate);
                    ps.setString(5, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 4 && ll > 0) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 6 && l1 <= 7) {
                        g3 = g3 + great14;
                    }


                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);





                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    h1.put("col", name1[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getOHFunctionalneedsCount", "FunctionalReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getOHFunctionalneedsCount");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getOHFunctionalneedsCount", "FunctionalReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getOHFunctionalneedsCount");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

            DBConnection.closeStatement(ps);
        }

        return a;
    }

    public ArrayList getMrFunctionalneedsCount(DataSource ds, String district_id, String mandal_id, String village_id, String fdate, String tdate)
            throws SADAREMDBException, SQLException {



        String tablename = null, colu = null, totall = null;

        Connection con = null;
        String colll = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;
        // ArrayList data = new ArrayList();
        String query = null;
        //     String type = null;
        PreparedStatement ps = null;
        String col = null;

        NumberFormat form = NumberFormat.getInstance();
        form.setMinimumIntegerDigits(2);
        form.setMinimumFractionDigits(2);
        form.setMaximumFractionDigits(2);
        HashMap h1 = null;//Meningitis

        String[] name = {"Speech Therapy (for children below 3 years)",
            "Behavior Modification (for children below 3 years)",
            "Speech therapy and Language development (Above 3 years)",
            "Sensory Integration/ Cognitive stimulation",
            "Physiotherapy", "Occupational Therapy",
            "Psychotherapy/ Behavior modification", "Parent/ family Intervention", "Legal Guardian (for persons with MR,CP,MD,Autism)",
            "Learning materials for MR (Books, CD, etc)",
            "Special software",
            "Toys",
            "Any other Mental retardation  special needs"
        };
        String[] name1 = {"Early Education (For children below 5 years)",
            "Home Based Education", "Special School", "Inclusive education",
            "For Employment in public/ pvt sector", "For self employment ", "Individual (PwD)",
            "Family", "Mental retardation  Other General Needs"};
        String[] aa = {"c.SpeechTherapyforthreeyears='EarlyIntervention Speech Therapy'",
            "c.BehaviourModificationforthreeyears='Behavior Modification'", "c.SpeechTherapy='Speech Therapy'",
            "c.SensoryIntegrationCognitive='SensoryIntegrationcognitive'", "c.Physiotherapy='Physiotherapy'",
            "c.OccupationalTherapy='OccupationalTherapy'", "c.PsycotherapyBehaviour='Psycotherapy/Behaviour Modification'",
            "c.ParentFamilyIntervention='ParientFamilyintervention'", "c.LegalGuardian='Legal Guardian'",
            "c.LearningMaterial='Learning Materials For M.R'", "c.SpecialSoftware='Special Software'", "c.Toys='Toys'", "c.anyother is not null"
        };

        String[] aa1 = {"c.earlyeducation ='Early Education Services'", "c.educationservices ='Home Based Education'", "c.educationservices ='Special School'",
            "c.educationservices ='Inclusive Education'", "c.vocationaltraining ='For employment in public/ pvt. sector'",
            "c.vocationaltraining ='For self-employment'", "c.individual ='individual'",
            "c.family ='family' ", "c.otherservices!=''"
        };

        ArrayList a = new ArrayList();

        double tot = 0, less14 = 0, great14 = 0;
        double l1 = 0, l2 = 0, l3 = 0, g1 = 0, g2 = 0, g3 = 0, t1 = 0, t2 = 0, t3 = 0;

        if (district_id == null) {
            district_id = "0";
        }
        if (mandal_id == null) {
            mandal_id = "0";
        }
        if (village_id == null) {
            village_id = "0";
        }



        try {
            if (fdate != null && fdate.contains("-")) {
                //fdate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fdate));
                ;

            } else {
                fdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
            }

            if (tdate != null && tdate.contains("-")) {
                ;
            } else {
                tdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(tdate));
            }
            con = DBConnection.getConnection();
            stmt = con.createStatement();



            if (district_id.equals("0")) {
                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblMental_Retardation_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=4 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblMental_Retardation_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27 and " + colll + "  and  pdd.Disability_ID=4 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, fdate);
                    ps.setString(2, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 7) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 7 && ll < 9) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 9 && ll <= 11) {
                        l3 = l3 + less14;
                    }
                    query = "select count(*) from  dbo.tblMental_Retardation_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=4 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblMental_Retardation_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=4 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, fdate);
                    ps.setString(2, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }

                    if (ll < 7) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 7 && ll < 9) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 9 && ll <= 11) {
                        g3 = g3 + great14;
                    }

                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);



                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);

                    }
                    h1.put("col", name[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);

                    a.add(h1);
                }

                l1 = 0;
                l2 = 0;
                l3 = 0;
                g1 = 0;
                g2 = 0;
                g3 = 0;
                t1 = 0;
                t2 = 0;
                t3 = 0;
                for (int ll = 0; ll < aa1.length; ll++) {
                    col = aa1[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=4 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=4 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, fdate);
                    ps.setString(2, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 4 && ll > 0) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        l3 = l3 + less14;
                    }
                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=4 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=4 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, fdate);
                    ps.setString(2, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 4 && ll > 0) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        g3 = g3 + great14;
                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);


                    }
                    h1.put("col", name1[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }
            } else if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) {
                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblMental_Retardation_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=4 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'  and district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblMental_Retardation_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27 and " + colll + "  and  pdd.Disability_ID=4 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, fdate);
                    ps.setString(3, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 7) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 7 && ll < 9) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 9 && ll <= 11) {
                        l3 = l3 + less14;
                    }



                    query = "select count(*) from  dbo.tblMental_Retardation_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=4 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblMental_Retardation_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=4 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, fdate);
                    ps.setString(3, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 7) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 7 && ll < 9) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 9 && ll <= 11) {
                        g3 = g3 + great14;
                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);










                    }
                    h1.put("col", name[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);

                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);

                    }
                    a.add(h1);

                }

                l1 = 0;
                l2 = 0;
                l3 = 0;
                g1 = 0;
                g2 = 0;
                g3 = 0;
                t1 = 0;
                t2 = 0;
                t3 = 0;
                for (int ll = 0; ll < aa1.length; ll++) {
                    col = aa1[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=4 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=4 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, fdate);
                    ps.setString(3, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }

                    if (ll < 4 && ll > 0) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        l3 = l3 + less14;
                    }
                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=4 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=4 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, fdate);
                    ps.setString(3, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 4 && ll > 0) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        g3 = g3 + great14;
                    }


                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);



                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    h1.put("col", name1[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);


                }

            }// "' and mandal_id='"+mandal_id +
            else if (!district_id.equals("0") && !mandal_id.equals("0") && village_id.equals("0")) {


                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblMental_Retardation_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=4 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'  and district_id=? and mandal_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblMental_Retardation_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27 and " + colll + "  and  pdd.Disability_ID=4 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, fdate);
                    ps.setString(4, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }

                    if (ll < 7) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 7 && ll < 9) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 9 && ll <= 11) {
                        l3 = l3 + less14;
                    }
                    query = "select count(*) from  dbo.tblMental_Retardation_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=4 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? "
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblMental_Retardation_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=4 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, fdate);
                    ps.setString(4, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 7) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 7 && ll < 9) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 9 && ll <= 11) {
                        g3 = g3 + great14;
                    }

                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);





                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    h1.put("col", name[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }

                l1 = 0;
                l2 = 0;
                l3 = 0;
                g1 = 0;
                g2 = 0;
                g3 = 0;
                t1 = 0;
                t2 = 0;
                t3 = 0;
                ;
                for (int ll = 0; ll < aa1.length; ll++) {
                    col = aa1[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=4 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=4 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);


                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, fdate);
                    ps.setString(4, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 4 && ll > 0) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        l3 = l3 + less14;
                    }

                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=4 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=4 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, fdate);
                    ps.setString(4, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 4 && ll > 0) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        g3 = g3 + great14;
                    }


                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);






                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    h1.put("col", name1[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);
                }

// "' and village_id='"+village_id +

            } else if (!district_id.equals("0") && district_id != null && !mandal_id.equals("0") && !village_id.equals("0")) {

                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblMental_Retardation_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=4 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'  and district_id=? and mandal_id=? and village_id=? "
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblMental_Retardation_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27 and " + colll + "  and  pdd.Disability_ID=4 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, village_id);
                    ps.setString(4, fdate);
                    ps.setString(5, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 7) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 7 && ll < 9) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 9 && ll <= 11) {
                        l3 = l3 + less14;
                    }
                    query = "select count(*) from  dbo.tblMental_Retardation_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=4 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? and village_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblMental_Retardation_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=4 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, village_id);
                    ps.setString(4, fdate);
                    ps.setString(5, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }

                    if (ll < 7) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 7 && ll < 9) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 9 && ll <= 11) {
                        g3 = g3 + great14;
                    }

                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);


                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    h1.put("col", name[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }

                l1 = 0;
                l2 = 0;
                l3 = 0;
                g1 = 0;
                g2 = 0;
                g3 = 0;
                t1 = 0;
                t2 = 0;
                t3 = 0;
                for (int ll = 0; ll < aa1.length; ll++) {
                    col = aa1[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=4 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? and village_id=? and "
                            + "(DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=4 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);


                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, village_id);
                    ps.setString(4, fdate);
                    ps.setString(5, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }

                    if (ll < 4 && ll > 0) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        l3 = l3 + less14;
                    }


                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=4 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? and village_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";


                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=4 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, village_id);
                    ps.setString(4, fdate);
                    ps.setString(5, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 4 && ll > 0) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        g3 = g3 + great14;
                    }


                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);





                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    h1.put("col", name1[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMrFunctionalneedsCount", "FunctionalReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getMrFunctionalneedsCount");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMrFunctionalneedsCount", "FunctionalReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getMrFunctionalneedsCount");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

            DBConnection.closeStatement(ps);
        }

        return a;
    }

    public ArrayList getMiFunctionalneedsCount(DataSource ds, String district_id, String mandal_id, String village_id, String fdate, String tdate) throws SADAREMDBException, SQLException
    {



        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;
        //  ArrayList data = new ArrayList();
        String query = null;
        // String type = null;
        PreparedStatement ps = null;
        String col = null;

        NumberFormat form = NumberFormat.getInstance();
        form.setMinimumIntegerDigits(2);
        form.setMinimumFractionDigits(2);
        form.setMaximumFractionDigits(2);
        String colll = null;
        HashMap h1 = null;//Meningitis
        String[] name = {"Behavior Modification (for children below 3 years)",
            "Psychotherapy/ Behaviour modification", "Surgery", "Manager to take care of properties",
            "Admission in psychiatric hospitals/ Nursing homes", "Any other Mental retardation  special needs"
        };
        String[] name1 = {"Early Education (For children below 5 years)",
            "Home Based Education", "Special School", "Inclusive education",
            "For Employment in public/ pvt sector", "For self employment ", "Individual (PwD)",
            "Family", "Mental Illness  Other General Needs"};
        String[] aa = {"c.Behaviour='Behavior Modification'",
            "c.Psycotherapy_Behaviour='Psycotherapy/Behaviour Modification'",
            "c.Surgery='NEUROOSURGERY'",
            "c.ManagerProperties='Manager To Take Care'",
            "c.NursingHomes='Psychiatric Hospitals/ Nursing Homes'",
            "c.AnyOther is not null"
        };

        String[] aa1 = {"c.earlyeducation ='Early Education Services'", "c.educationservices ='Home Based Education'", "c.educationservices ='Special School'",
            "c.educationservices ='Inclusive Education'", "c.vocationaltraining ='For employment in public/ pvt. sector'",
            "c.vocationaltraining ='For self-employment'", "c.individual ='individual'",
            "c.family ='family' ", "c.otherservices!=''"
        };

        String tablename = null, colu = null, totall = null;

        ArrayList a = new ArrayList();

        double tot = 0, less14 = 0, great14 = 0, tl = 0, tg = 0, tot1 = 0;
        double l1 = 0, l2 = 0, l3 = 0, g1 = 0, g2 = 0, g3 = 0, t1 = 0, t2 = 0, t3 = 0;

        if (district_id == null) {
            district_id = "0";
        }
        if (mandal_id == null) {
            mandal_id = "0";
        }
        if (village_id == null) {
            village_id = "0";
        }



        try {
            if (fdate != null && fdate.contains("-")) {
                //fdate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fdate));
                ;

            } else {
                fdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
            }

            if (tdate != null && tdate.contains("-")) {
                ;
            } else {
                tdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(tdate));
            }
            con = DBConnection.getConnection();
            stmt = con.createStatement();


            if (district_id.equals("0")) {
                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblMental_Disability_Illness_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=5 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblMental_Disability_Illness_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27 and " + colll + "  and  pdd.Disability_ID=5 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, fdate);
                    ps.setString(2, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 5) {
                        tl = tl + less14;
                    }
                    query = "select count(*) from  dbo.tblMental_Disability_Illness_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=5 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblMental_Disability_Illness_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=5 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, fdate);
                    ps.setString(2, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }

                    if (ll < 5) {
                        tg = tg + great14;
                    }


                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);

                    }
                    h1.put("col", name[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
//if(ll==7){
                    h1.put("tl", tl);
                    h1.put("tg", tg);
                    tot1 = tl + tg;
                    if (tl != 0) {
                        h1.put("tlp", form.format(100 * (tl / tot1)));

                    } else {
                        h1.put("tlp", 0);

                    }
                    if (tg != 0) {
                        h1.put("tgp", form.format(100 * (tg / tot1)));

                    } else {
                        h1.put("tgp", 0);
//}

                    }
                    a.add(h1);

                }


                for (int ll = 0; ll < aa1.length; ll++) {
                    col = aa1[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=5 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=5 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, fdate);
                    ps.setString(2, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 4 && ll > 0) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        l3 = l3 + less14;
                    }
                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=5 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=5 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, fdate);
                    ps.setString(2, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 4 && ll > 0) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        g3 = g3 + great14;
                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);




                    }
                    h1.put("col", name1[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }



            } //and district_id='"+district_id +
            else if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) {
                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblMental_Disability_Illness_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=5 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'  and district_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblMental_Disability_Illness_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27 and " + colll + "  and  pdd.Disability_ID=5 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, fdate);
                    ps.setString(3, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 5) {
                        tl = tl + less14;
                    }



                    query = "select count(*) from  dbo.tblMental_Disability_Illness_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=5 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblMental_Disability_Illness_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=5 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, fdate);
                    ps.setString(3, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 5) {
                        tg = tg + great14;
                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);










                    }
                    h1.put("col", name[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);

                    h1.put("tl", (int) tl);
                    h1.put("tg", (int) tg);
                    tot1 = tl + tg;
                    if (tl != 0) {
                        h1.put("tlp", form.format(100 * (tl / tot1)));

                    } else {
                        h1.put("tlp", 0);

                    }
                    if (tg != 0) {
                        h1.put("tgp", form.format(100 * (tg / tot1)));

                    } else {
                        h1.put("tgp", 0);


                    }
                    a.add(h1);

                }


                for (int ll = 0; ll < aa1.length; ll++) {
                    col = aa1[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=5 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=5 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, fdate);
                    ps.setString(3, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }

                    if (ll < 4 && ll > 0) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        l3 = l3 + less14;
                    }
                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=5 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=5 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, fdate);
                    ps.setString(3, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 4 && ll > 0) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        g3 = g3 + great14;
                    }


                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);



                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    h1.put("col", name1[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }

            }// "' and mandal_id='"+mandal_id +
            else if (!district_id.equals("0") && !mandal_id.equals("0") && village_id.equals("0")) {


                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblMental_Disability_Illness_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=5 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'  and district_id=? and mandal_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblMental_Disability_Illness_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27 and " + colll + "  and  pdd.Disability_ID=5 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, fdate);
                    ps.setString(4, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }

                    if (ll < 5) {
                        tl = tl + less14;
                    }
                    query = "select count(*) from  dbo.tblMental_Disability_Illness_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=5 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";


                    colll = col.replace("'", "%27");
                    tablename = "join tblMental_Disability_Illness_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=5 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, fdate);
                    ps.setString(4, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 5) {
                        tg = tg + great14;
                    }


                    h1.put("tl", (int) tl);
                    h1.put("tg", (int) tg);
                    tot1 = tl + tg;
                    if (tl != 0) {
                        h1.put("tlp", form.format(100 * (tl / tot1)));

                    } else {
                        h1.put("tlp", 0);

                    }
                    if (tg != 0) {
                        h1.put("tgp", form.format(100 * (tg / tot1)));

                    } else {
                        h1.put("tgp", 0);


                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    h1.put("col", name[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }

                for (int ll = 0; ll < aa1.length; ll++) {
                    col = aa1[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=5 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=5 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);


                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, fdate);
                    ps.setString(4, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 4 && ll > 0) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        l3 = l3 + less14;
                    }
                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=5 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";


                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=5 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, fdate);
                    ps.setString(4, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 4 && ll > 0) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        g3 = g3 + great14;
                    }


                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);






                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    h1.put("col", name1[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }

// "' and village_id='"+village_id +

            } else if (!district_id.equals("0") && district_id != null && !mandal_id.equals("0") && !village_id.equals("0")) {

                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblMental_Disability_Illness_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=5 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'  and district_id=? and mandal_id=? and village_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblMental_Disability_Illness_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27 and " + colll + "  and  pdd.Disability_ID=5 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, village_id);
                    ps.setString(4, fdate);
                    ps.setString(5, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 5) {
                        tl = tl + less14;
                    }
                    query = "select count(*) from  dbo.tblMental_Disability_Illness_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=5 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? and village_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";


                    colll = col.replace("'", "%27");
                    tablename = "join tblMental_Disability_Illness_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=5 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, village_id);
                    ps.setString(4, fdate);
                    ps.setString(5, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }

                    if (ll < 5) {
                        tg = tg + great14;
                    }


                    h1.put("tl", (int) tl);
                    h1.put("tg", (int) tg);
                    tot1 = tl + tg;
                    if (tl != 0) {
                        h1.put("tlp", form.format(100 * (tl / tot1)));

                    } else {
                        h1.put("tlp", 0);

                    }
                    if (tg != 0) {
                        h1.put("tgp", form.format(100 * (tg / tot1)));

                    } else {
                        h1.put("tgp", 0);


                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    h1.put("col", name[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);
                }
                for (int ll = 0; ll < aa1.length; ll++) {
                    col = aa1[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=5 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? and village_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=5 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, village_id);
                    ps.setString(4, fdate);
                    ps.setString(5, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }

                    if (ll < 4 && ll > 0) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        l3 = l3 + less14;
                    }


                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=5 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? and village_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=5 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, village_id);
                    ps.setString(4, fdate);
                    ps.setString(5, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 4 && ll > 0) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 6 && l1 <= 7) {
                        g3 = g3 + great14;
                    }


                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);
                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    h1.put("col", name1[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMiFunctionalneedsCount", "FunctionalReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getMiFunctionalneedsCount");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMiFunctionalneedsCount", "FunctionalReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getMiFunctionalneedsCount");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

            DBConnection.closeStatement(ps);
        }

        return a;
    }

    public ArrayList getHiFunctionalneedsCount(DataSource ds, String district_id, String mandal_id, String village_id, String fdate, String tdate)
            throws SADAREMDBException, SQLException {



        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;
        // ArrayList data = new ArrayList();
        String query = null;
        // String type = null;
        PreparedStatement ps = null;
        String col = null;

        NumberFormat form = NumberFormat.getInstance();
        form.setMinimumIntegerDigits(2);
        form.setMinimumFractionDigits(2);
        form.setMaximumFractionDigits(2);
        HashMap h1 = null;//Meningitis
        String[] name = {"Speech Therapy (for children below 3 years)",
            "Speech therapy (Above 3 years)", "Language Development", "S- cord low intensity hearing Aids",
            "S- cord Moderate intensity hearing Aids", "S- cord High intensity hearing Aids",
            "V- cord low intensity hearing Aids", "V- cord Moderate intensity hearing Aids",
            "V- cord High intensity hearing Aids",
            "Hearing Impairment Other Special needs",
            "Cochlear Implantation",
            "Implantable Hearing Aid",
            "Any other Hearing Aid special needs"
        };
        String[] name1 = {"Early Education (For children below 5 years)",
            "Home Based Education", "Special School", "Inclusive education",
            "For Employment in public/ pvt sector", "For self employment ", "Individual (PwD)",
            "Family", "Hearing Impairment Other General Needs"};
        String[] aa = {"c.SpeechTherapyforthreeyears='EarlyIntervention Speech Therapy'",
            "c.SpeechTherapy='Speech Therapy'",
            "c.LanguageDevelopment='Language Development'",
            "c.HearingAidType='Low Intensity' and c.HearingAidSubType='S-Cord'",
            "c.HearingAidType='Moderate Intensity' and c.HearingAidSubType='S-Cord'",
            "c.HearingAidType='High Intensity' and c.HearingAidSubType='S-Cord'",
            "c.HearingAidType='Low Intensity' and c.HearingAidSubType='V-Cord'",
            "c.HearingAidType='Moderate Intensity' and c.HearingAidSubType='V-Cord'",
            "c.HearingAidType='High Intensity' and c.HearingAidSubType='V-Cord'",
            "c.CochlearImplantation='Cochlear Implantation'",
            "c.ImplantableHearingAid='Implantable Hearing Aid'",
            "c.anyother is not null"
        };

        String[] aa1 = {"c.earlyeducation ='Early Education Services'", "c.educationservices ='Home Based Education'", "c.educationservices ='Special School'",
            "c.educationservices ='Inclusive Education'", "c.vocationaltraining ='For employment in public/ pvt. sector'",
            "c.vocationaltraining ='For self-employment'", "c.individual ='individual'",
            "c.family ='family' ", "c.otherservices!=''"
        };


        String colll = null;
        ArrayList a = new ArrayList();

        String tablename = null, colu = null, totall = null;
        double tot = 0, less14 = 0, great14 = 0, tl = 0, tg = 0, tot1 = 0;
        double l1 = 0, l2 = 0, l3 = 0, g1 = 0, g2 = 0, g3 = 0, t1 = 0, t2 = 0, t3 = 0;

        if (district_id == null) {
            district_id = "0";
        }
        if (mandal_id == null) {
            mandal_id = "0";
        }
        if (village_id == null) {
            village_id = "0";
        }



        try {
            if (fdate != null && fdate.contains("-"))
            {
                //fdate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fdate));
                ;

            } 
            else
            {
                fdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
            }

            if (tdate != null && tdate.contains("-"))
            {
                ;
            }
            else
            {
                tdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(tdate));
            }
            con = DBConnection.getConnection();
            //stmt = con.createStatement();

            if (district_id.equals("0"))
            {
                for (int ll = 0; ll < aa.length; ll++)
                {
                    col = aa[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblHearing_Impairment_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=3 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblHearing_Impairment_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27 and " + colll + "  and  pdd.Disability_ID=3 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, fdate);
                    ps.setString(2, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 9) {
                        tl = tl + less14;
                    }
                    query = "select count(*) from  dbo.tblHearing_Impairment_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=3 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' "
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblHearing_Impairment_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=3 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, fdate);
                    ps.setString(2, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }

                    if (ll < 9) {
                        tg = tg + great14;
                    }


                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);

                    }
                    h1.put("col", name[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
//if(ll==7){
                    h1.put("tl", tl);
                    h1.put("tg", tg);
                    tot1 = tl + tg;
                    if (tl != 0) {
                        h1.put("tlp", form.format(100 * (tl / tot1)));

                    } else {
                        h1.put("tlp", 0);

                    }
                    if (tg != 0) {
                        h1.put("tgp", form.format(100 * (tg / tot1)));

                    } else {
                        h1.put("tgp", 0);
//}

                    }
                    a.add(h1);

                }


                for (int ll = 0; ll < aa1.length; ll++) {
                    col = aa1[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=3 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  '" + fdate + "' AND '" + tdate + "'";

                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=3 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 4 && ll > 0) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        l3 = l3 + less14;
                    }
                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=3 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  '" + fdate + "' AND '" + tdate + "'";

                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=3 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);

                    ps = con.prepareStatement(query);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 4 && ll > 0) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        g3 = g3 + great14;
                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);
                    }
                    h1.put("col", name1[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }

            } //and district_id='"+district_id +
            else if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0"))
            {
                for (int ll = 0; ll < aa.length; ll++)
                {
                    col = aa[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblHearing_Impairment_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=3 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'  and district_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblHearing_Impairment_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27 and " + colll + "  and  pdd.Disability_ID=3 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, fdate);
                    ps.setString(3, tdate);
                    
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 9) {
                        tl = tl + less14;
                    }



                    query = "select count(*) from  dbo.tblHearing_Impairment_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=3 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id='" + district_id
                            + "' and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  '" + fdate + "' AND '" + tdate + "'";
                    colll = col.replace("'", "%27");
                    tablename = "join tblHearing_Impairment_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=3 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 9) {
                        tg = tg + great14;
                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);
                    }
                    h1.put("col", name[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);

                    h1.put("tl", (int) tl);
                    h1.put("tg", (int) tg);
                    tot1 = tl + tg;
                    if (tl != 0) {
                        h1.put("tlp", form.format(100 * (tl / tot1)));

                    } else {
                        h1.put("tlp", 0);

                    }
                    if (tg != 0) {
                        h1.put("tgp", form.format(100 * (tg / tot1)));

                    } else {
                        h1.put("tgp", 0);
                    }
                    a.add(h1);

                }


                for (int ll = 0; ll < aa1.length; ll++) {
                    col = aa1[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=3 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id='" + district_id
                            + "' and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  '" + fdate + "' AND '" + tdate + "'";


                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=3 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }

                    if (ll < 4 && ll > 0) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        l3 = l3 + less14;
                    }
                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=3 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id='" + district_id
                            + "' and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  '" + fdate + "' AND '" + tdate + "'";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=3 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 4 && ll > 0) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        g3 = g3 + great14;
                    }


                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);



                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);
                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);
                    }
                    h1.put("col", name1[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }

            }// "' and mandal_id='"+mandal_id +
            else if (!district_id.equals("0") && !mandal_id.equals("0") && village_id.equals("0"))
            {


                for (int ll = 0; ll < aa.length; ll++)
                {
                    col = aa[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblHearing_Impairment_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=3 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'  and district_id=?" 
                            + " and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblHearing_Impairment_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27 and " + colll + "  and  pdd.Disability_ID=3 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3,fdate);
                    ps.setString(4, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }

                    if (ll < 9) {
                        tl = tl + less14;
                    }
                    query = "select count(*) from  dbo.tblHearing_Impairment_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=3 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblHearing_Impairment_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=3 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, fdate);
                    ps.setString(4, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 9) {
                        tg = tg + great14;
                    }


                    h1.put("tl", (int) tl);
                    h1.put("tg", (int) tg);
                    tot1 = tl + tg;
                    if (tl != 0) {
                        h1.put("tlp", form.format(100 * (tl / tot1)));

                    } else {
                        h1.put("tlp", 0);

                    }
                    if (tg != 0) {
                        h1.put("tgp", form.format(100 * (tg / tot1)));

                    } else {
                        h1.put("tgp", 0);


                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    h1.put("col", name[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }


                for (int ll = 0; ll < aa1.length; ll++) {
                    col = aa1[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=3 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";

                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=3 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, fdate);
                    ps.setString(4, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 4 && ll > 0) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        l3 = l3 + less14;
                    }
                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=3 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=3 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, fdate);
                    ps.setString(4, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 4 && ll > 0) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        g3 = g3 + great14;
                    }


                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);

                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    h1.put("col", name1[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }

// "' and village_id='"+village_id +

            } else if (!district_id.equals("0") && district_id != null && !mandal_id.equals("0") && !village_id.equals("0")) {

                for (int ll = 0; ll < aa.length; ll++) {
                    col = aa[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblHearing_Impairment_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=3 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active'  and district_id=? and mandal_id=? and village_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? and ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblHearing_Impairment_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27 and " + colll + "  and  pdd.Disability_ID=3 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, village_id);
                    ps.setString(4, fdate);
                    ps.setString(5, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }
                    if (ll < 9) {
                        tl = tl + less14;
                    }
                    query = "select count(*) from  dbo.tblHearing_Impairment_Details c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=3 "
                            + " and p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? and village_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblHearing_Impairment_Details c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=3 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, village_id);
                    ps.setString(4, fdate);
                    ps.setString(5, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }

                    if (ll < 9) {
                        tg = tg + great14;
                    }


                    h1.put("tl", (int) tl);
                    h1.put("tg", (int) tg);
                    tot1 = tl + tg;
                    if (tl != 0) {
                        h1.put("tlp", form.format(100 * (tl / tot1)));

                    } else {
                        h1.put("tlp", 0);

                    }
                    if (tg != 0) {
                        h1.put("tgp", form.format(100 * (tg / tot1)));

                    } else {
                        h1.put("tgp", 0);


                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    h1.put("col", name[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }


                for (int ll = 0; ll < aa1.length; ll++) {
                    col = aa1[ll];
                    h1 = new HashMap();

                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=3 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) <=14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? and village_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=3 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 <=14 ";
                    h1.put("tablem", tablename);
                    h1.put("columnm", colu);

                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, village_id);
                    ps.setString(4, fdate);
                    ps.setString(5, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        less14 = (rs.getInt(1));
                    }

                    if (ll < 4 && ll > 0) {
                        l1 = l1 + less14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        l2 = l2 + less14;
                    }
                    if (ll >= 6 && ll <= 7) {
                        l3 = l3 + less14;
                    }


                    query = "select count(*) from  dbo.tblAllDisabilityPerson_Common_Needs c inner join "
                            + " tblPerson_personal_details p on (p.person_code=c.person_code) "
                            + " join tblPerson_Disability_Details pdd on(p.person_code=pdd.person_code )"
                            + " join tblPerson_Disability_TotalValue_Details t  on(p.person_code=t.person_code) "
                            + " where " + col + " and pdd.disability_id=3 and"
                            + " p.age_years +datediff(year, p.created_date,getdate()) >14"
                            + " and c.status='Active' and p.status='Active' and pdd.status='Active' and t.status='Active' and district_id=? and mandal_id=? and village_id=?"
                            + " and (DATEADD(DD,0,DATEDIFF(DD,0,p.updated_date)))  BETWEEN  ? AND ?";
                    colll = col.replace("'", "%27");
                    tablename = "join tblAllDisabilityPerson_Common_Needs c on(p.person_code=c.person_code) ";
                    colu = "c.status=%27active%27  and " + colll + "  and pdd.Disability_ID=3 and p.age_years %2Bdatediff%28year%2C p.created_date %2C getdate%28%29%29 >14 ";
                    h1.put("tablef", tablename);
                    h1.put("columnf", colu);
                    ps = con.prepareStatement(query);
                    ps.setString(1, district_id);
                    ps.setString(2, mandal_id);
                    ps.setString(3, village_id);
                    ps.setString(4, fdate);
                    ps.setString(5, tdate);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        great14 = rs.getInt(1);
                    }
                    if (ll < 4 && ll > 0) {
                        g1 = g1 + great14;
                    }
                    if (ll >= 4 && ll <= 5) {
                        g2 = g2 + great14;
                    }
                    if (ll >= 6 && l1 <= 7) {
                        g3 = g3 + great14;
                    }


                    t1 = l1 + g1;
                    t2 = l2 + g2;
                    t3 = l3 + g3;

                    h1.put("l1", (int) l1);
                    h1.put("g1", (int) g1);
                    h1.put("l2", (int) l2);
                    h1.put("g2", (int) g2);
                    h1.put("l3", (int) l3);
                    h1.put("g3", (int) g3);

                    if (l1 != 0) {
                        h1.put("tlp1", form.format(100 * (l1 / t1)));

                    } else {
                        h1.put("tlp1", 0);

                    }
                    if (g1 != 0) {
                        h1.put("tgp1", form.format(100 * (g1 / t1)));

                    } else {
                        h1.put("tgp1", 0);


                    }
                    if (l2 != 0) {
                        h1.put("tlp2", form.format(100 * (l2 / t2)));

                    } else {
                        h1.put("tlp2", 0);

                    }
                    if (g2 != 0) {
                        h1.put("tgp2", form.format(100 * (g2 / t2)));

                    } else {
                        h1.put("tgp2", 0);


                    }
                    if (l3 != 0) {
                        h1.put("tlp3", form.format(100 * (l3 / t3)));

                    } else {
                        h1.put("tlp3", 0);

                    }
                    if (g3 != 0) {
                        h1.put("tgp3", form.format(100 * (g3 / t3)));

                    } else {
                        h1.put("tgp3", 0);
                    }
                    tot = less14 + great14;
                    h1.put("l", (int) less14);
                    if (less14 != 0) {
                        h1.put("lp", form.format(100 * (less14 / tot)));

                    } else {
                        h1.put("lp", 0);


                    }
                    h1.put("g", (int) great14);
                    if (great14 != 0) {
                        h1.put("gp", form.format(100 * (great14 / tot)));

                    } else {
                        h1.put("gp", 0);


                    }
                    h1.put("col", name1[ll]);
                    h1.put("district", district_id);
                    h1.put("mandal", mandal_id);
                    h1.put("village", village_id);
                    h1.put("fdate", fdate);
                    h1.put("tdate", tdate);
                    a.add(h1);

                }



            }



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHiFunctionalneedsCount", "FunctionalReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getHiFunctionalneedsCount");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHiFunctionalneedsCount", "FunctionalReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getHiFunctionalneedsCount");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

            DBConnection.closeStatement(ps);
        }

        return a;
    }

    public ArrayList getRationPersonalReportDetails(DataSource ds, String district_id, String mandal_id, String village_id) throws SADAREMDBException, SQLException {
        ArrayList<Object> personalDetails = new ArrayList<Object>();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sql = null;
        Map<String, Object> rationPersonalData = null;
        int i = 1;
        try {

            con = DBConnection.getConnection();


            sql = "select person_code,surname + SPACE(2)+ first_name as name,relation_name from "
                    + "tblperson_personal_details  with (nolock) where district_id=? and "
                    + "mandal_id=? and village_id=? and rationcard_slno is null";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, district_id);
            pstmt.setString(2, mandal_id);
            pstmt.setString(3, village_id);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {

                    rationPersonalData = new HashMap<String, Object>();
                    rationPersonalData.put("sno", i);
                    rationPersonalData.put("person_code", rs.getString(1));
                    rationPersonalData.put("name", rs.getString(2));
                    rationPersonalData.put("relation", rs.getString(3));
                    personalDetails.add(rationPersonalData);
                    i++;
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRationPersonalReportDetails", "FunctionalReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getRationPersonalReportDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRationPersonalReportDetails", "FunctionalReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getRationPersonalReportDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }


        return personalDetails;
    }

    public ArrayList getCampDetails(DataSource ds, String district_id) throws SADAREMDBException, SQLException {
        ArrayList<Object> campDetailsList = new ArrayList<Object>();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sql = null;
        Map<String, Object> campDetails = null;
        int i = 1;
        try {

            con = DBConnection.getConnection();
            

            sql = "select camp_id,name,address venueName from dbo.tblCamp_Details with(nolock)  where district_id=? and status='Active' order by name";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, district_id);
            
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {

                    campDetails = new HashMap<String, Object>();
                    campDetails.put("sno", i);
                    campDetails.put("camp_id", rs.getString(1));
                    campDetails.put("camp_name", rs.getString(2));
                    campDetails.put("venue_name", rs.getString(3));
                    campDetailsList.add(campDetails);
                    i++;
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDetails", "FunctionalReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getCampDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDetails", "FunctionalReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getCampDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }


        return campDetailsList;
    }

    public ArrayList getCampDailyReportDetails(DataSource ds, String district_id) throws SADAREMDBException, SQLException {

        ArrayList campList = new ArrayList();
        ArrayList campDataList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        CampDailyReportDTO campDailyReportDTO = null;
        Map<String, Object> campDailyDetails = null;
        int i = 1;

        try {
            con = DBConnection.getConnection();

            query = "select camp_id,name,address,venuename from tblCamp_Details with(nolock)  where district_id =? and status='Active' ";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, district_id);

            rs = pstmt.executeQuery();

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
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDailyReportDetails", "FunctionalReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getCampDailyReportDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDailyReportDetails", "FunctionalReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getCampDailyReportDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }

        return campDataList;

    }

    public ArrayList getDisabilityDetails(DataSource ds) throws SADAREMDBException, SQLException {

        ArrayList disabilityList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        FunctionalNeedReportDTO functionalNeedReportDTO = null;
        CampDailyReportDTO campDailyReportDTO = null;

        try {
            con = DBConnection.getConnection();

            query = "select disability_id,disability_name from dbo.tblDisability_Details with(nolock)  ";
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
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityDetails", "FunctionalReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getDisabilityDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityDetails", "FunctionalReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getDisabilityDetails");
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
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPwdAssessementDetails", "FunctionalReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "insertPwdAssessementDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPwdAssessementDetails", "FunctionalReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "insertPwdAssessementDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);

        }


        return i;
    }

    public ArrayList getNotComeToCampPersonalDetailsTotal(DataSource ds,
            String districtID, String phaseName, String reportType)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList<FunctionalNeedReportDTO> notCometoSadaremPersonalList = null;
        notCometoSadaremPersonalList = new ArrayList<FunctionalNeedReportDTO>();
        try {

            con = DBConnection.getConnection();
            if (reportType.equals("parta")) {
                cstmt = con.prepareCall("{Call SP_PERSONALDETAILS_ONLYFORPARTAPERSONS_NEW_ALL(?,?)}");
                cstmt.setString(1, districtID);
                cstmt.setString(2, phaseName);

                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO onlyPartAPersonalDTO =
                            new FunctionalNeedReportDTO();
                    onlyPartAPersonalDTO.setPensionid(rs.getString("PENSIONCARD_NO"));
                    onlyPartAPersonalDTO.setPersoncode(rs.getString("PERSON_CODE"));
                    onlyPartAPersonalDTO.setName(rs.getString("PERSONNAME"));
                    onlyPartAPersonalDTO.setRalationName(rs.getString("RELATION_NAME"));
                    onlyPartAPersonalDTO.setGender(rs.getString("Gender"));
                    onlyPartAPersonalDTO.setAge(rs.getString("AGE_YEARS"));
                    onlyPartAPersonalDTO.setRationcard(rs.getString("RATIONCARD_NUMBER"));
                    onlyPartAPersonalDTO.setMandalName(rs.getString("MANDAL_NAME"));
                    onlyPartAPersonalDTO.setVillageName(rs.getString("VILLAGE_NAME"));
                    onlyPartAPersonalDTO.setHabitationName(rs.getString("HABITATION_NAME"));
                    onlyPartAPersonalDTO.setDisabilityType(rs.getString("TYPEOF_DISABILITY"));
                    onlyPartAPersonalDTO.setPhase(rs.getString("PENSIONPHASE"));
                    onlyPartAPersonalDTO.setHouseno(rs.getString("HOUSE_NUMBER"));
                    onlyPartAPersonalDTO.setCategoryID(rs.getString("Catid"));
                    notCometoSadaremPersonalList.add(onlyPartAPersonalDTO);
                }

            } else if (reportType.equals("notcome")) {
                cstmt = con.prepareCall("{Call SP_PERSONALDETAILS_NOTCOMETOSADAREM_ALL(?,?)}");
                cstmt.setString(1, districtID);
                cstmt.setString(2, phaseName);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO ontCometoCampPersonalDTO =
                            new FunctionalNeedReportDTO();
                    ontCometoCampPersonalDTO.setPensionid(rs.getString("PENSIONID"));
                    ontCometoCampPersonalDTO.setFirstName(rs.getString("FIRSTNAME"));
                    ontCometoCampPersonalDTO.setMiddleName(rs.getString("MID_NAME"));
                    ontCometoCampPersonalDTO.setLastName(rs.getString("LASTNAME"));
                    ontCometoCampPersonalDTO.setRalationName(rs.getString("FNAME"));
                    ontCometoCampPersonalDTO.setGender(rs.getString("SEX"));
                    ontCometoCampPersonalDTO.setAge(rs.getString("AGE"));
                    ontCometoCampPersonalDTO.setRationcard(rs.getString("RATIONCARDNO"));
                    ontCometoCampPersonalDTO.setMandalName(rs.getString("MANDAL_NAME"));
                    ontCometoCampPersonalDTO.setVillageName(rs.getString("VILLAGE"));
                    ontCometoCampPersonalDTO.setHabitationName(rs.getString("HABITATION_NAME"));
                    ontCometoCampPersonalDTO.setPhase(rs.getString("PENSIONPHASE"));
                    ontCometoCampPersonalDTO.setHouseno(rs.getString("HNO"));
                    ontCometoCampPersonalDTO.setSubtypeofDisability(rs.getString("DISABILITY"));
                    ontCometoCampPersonalDTO.setCategoryID("Not Assessed");
                    ontCometoCampPersonalDTO.setDisabilityType(rs.getString("Phc"));
                    notCometoSadaremPersonalList.add(ontCometoCampPersonalDTO);
                }
            }



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getNotComeToCampPersonalDetailsTotal", "FunctionalReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getNotComeToCampPersonalDetailsTotal");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getNotComeToCampPersonalDetailsTotal", "FunctionalReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getNotComeToCampPersonalDetailsTotal");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return notCometoSadaremPersonalList;
    }

     public String getAreaName(DataSource ds, String districtid, String mandalid, String villageid) throws SADAREMDBException, SQLException  {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String areaname = null;
        String query = null;
        String dist = "All";
        String mand = "All";
        String vill = "All";
        try {
            con = DBConnection.getConnection();
            
            if (districtid != null) {
                query = "select district_name from tblDistrict_Details with(nolock)  where district_id=?" ;
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, districtid);
                rs = pstmt.executeQuery();
               
                if (rs != null) {
                    while (rs.next()) {
                        dist = rs.getString(1);
                    }
                }
            }
            if (mandalid != null) {
                query = "select mandal_name from tblMandal_Details with(nolock)  where district_id=? and mandal_id=?";
                
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, districtid);
                pstmt.setString(2, mandalid);
                
                rs = pstmt.executeQuery();
                
                if (rs != null) {
                    while (rs.next()) {
                        mand = rs.getString(1);
                    }
                }
            }
            if (mandalid != null) {
                query = "select village_name from tblVillage_Details with(nolock)  where district_id=? and mandal_id=? and village_id=?" ;
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, districtid);
                pstmt.setString(2, mandalid);
                pstmt.setString(3, villageid);
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        vill = rs.getString(1);
                    }
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAreaName", "FunctionalReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getAreaName");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAreaName", "FunctionalReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalReportDAO", "getAreaName");

        } finally {
            areaname = "District : " + dist + "&nbsp;&nbsp;&nbsp;&nbsp;Mandal : " + mand + "&nbsp;&nbsp;&nbsp;&nbsp;Village : " + vill;
              DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }

        return areaname;
    }
}
