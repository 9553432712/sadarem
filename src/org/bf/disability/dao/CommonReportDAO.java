package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.CommonReportDTO;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is for feetching commonreport information from database
 * @author ragavendra
 * @version 1.0
 */
public class CommonReportDAO {

    /**
     * this method is for fetching for commonreport under districtwise from database
     * @param ds 
     * @param surgeryreportdto 
     * @param Surgerytype 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getDistrictReport(DataSource ds,
            CommonReportDTO surgeryreportdto, String Surgerytype)
            throws SADAREMDBException, SQLException {

        Connection con = null;

        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList districtcertificatelist = new ArrayList();
        try {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(surgeryreportdto.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(surgeryreportdto.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);

            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            if (Surgerytype.equals("Physiotherapy")
                    || Surgerytype.equals("SpeechTherapy")
                    || Surgerytype.equals("CouncellingGuidance")) {
                cstmt = con.prepareCall("{Call SP_Reports_to_select_from_singletable_and_Multiplecolumns_Districtwise(?,?,?,?,?,?,?)}");

                // modified my ganesh

                //end of modifiy
                cstmt.setString(1, surgeryreportdto.getTablename());
                cstmt.setString(2, surgeryreportdto.getFirstcolumn());

                cstmt.setString(3, surgeryreportdto.getFieldvalue());
                cstmt.setString(4, surgeryreportdto.getSecondcolumn());
                cstmt.setString(5, surgeryreportdto.getSecondfieldvalue());
                cstmt.setString(6, fromdate);
                cstmt.setString(7, todate);
            } else if (Surgerytype.equals("orthosis")) {
                cstmt = con.prepareCall("{Call SP_Reports_for_Orthosis_Districtwise(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
            } else if (Surgerytype.equals("prosthesis")) {
                cstmt = con.prepareCall("{Call SP_Reports_for_Prosthesis_Districtwise(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
            } else if (Surgerytype.equals("HearingAidType")) {

                cstmt = con.prepareCall("{Call SP_Reports_for_HearingAidType_Districtwise(?,?)}");

                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
            } else if (Surgerytype.equals("WalkingFrame")) {
                cstmt = con.prepareCall("{Call SP_Reports_for_WalkingFrame_Districtwise(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
            } else {
                cstmt = con.prepareCall("{Call SP_Reports_to_select_from_singletable_and_singlecolumn_Districtwise(?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getTablename());
                cstmt.setString(2, Surgerytype);
                cstmt.setString(3, surgeryreportdto.getFieldvalue());
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
            }
            rs = cstmt.executeQuery();
            con.commit();
            con.setAutoCommit(true);
            while (rs.next()) {
                CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                surgeryreportdto1.setDistrictid(rs.getString("district_id"));
                surgeryreportdto1.setDistrictcount(rs.getString("NumberofPersons"));
                surgeryreportdto1.setDistrict(rs.getString("district_name"));
                districtcertificatelist.add(surgeryreportdto1);
            }

        } catch (ParseException parseException) {
            int exResult = ExceptionDAO.saveException(ds,parseException.toString(),  "getDistrictReport", "CommonReportDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, parseException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportDAO", "getDistrictReport");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDistrictReport", "CommonReportDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportDAO", "getDistrictReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDistrictReport", "CommonReportDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportDAO", "getDistrictReport");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cstmt);

           DBConnection.closeConnection(con);
        }
        return districtcertificatelist;
    }

    /**
     * this method is for fetching for commonreport under mandalwise from database
     * @param datasource 
     * @param surgeryreportdto 
     * @param Surgerytype 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getMandalReport(DataSource datasource, CommonReportDTO surgeryreportdto, String Surgerytype) throws SADAREMDBException, SQLException {
        CallableStatement cstmt = null;
        Connection con = null;

        ResultSet rs = null;
        ArrayList districtcertificatelist = new ArrayList();
        try {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(surgeryreportdto.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(surgeryreportdto.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);

            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            if (Surgerytype.equals("Physiotherapy") || Surgerytype.equals("SpeechTherapy") || Surgerytype.equals("CouncellingGuidance")) {
                cstmt = con.prepareCall("{Call SP_Reports_to_select_from_singletable_and_Multiplecolumns_Mandalwise(?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getTablename());
                cstmt.setString(3, surgeryreportdto.getFirstcolumn());
                cstmt.setString(4, surgeryreportdto.getFieldvalue());
                cstmt.setString(5, surgeryreportdto.getSecondcolumn());
                cstmt.setString(6, surgeryreportdto.getSecondfieldvalue());
                cstmt.setString(7, fromdate);
                cstmt.setString(8, todate);
            } else if (Surgerytype.equals("orthosis")) {
                cstmt = con.prepareCall("{Call SP_Reports_for_Orthosis_Mandalwise(?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
            } else if (Surgerytype.equals("prosthesis")) {
                cstmt = con.prepareCall("{Call SP_Reports_for_Prosthesis_Mandalwise(?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
            } else if (Surgerytype.equals("HearingAidType")) {
                cstmt = con.prepareCall("{Call SP_Reports_for_HearingAidType_Mandalwise(?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
            } else if (Surgerytype.equals("WalkingFrame")) {
                cstmt = con.prepareCall("{Call SP_Reports_for_WalkingFrame_Mandalwise(?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
            } else {
                cstmt = con.prepareCall("{Call SP_Reports_to_select_from_singletable_and_singlecolumn_Mandalwise(?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getTablename());
                cstmt.setString(3, Surgerytype);
                cstmt.setString(4, surgeryreportdto.getFieldvalue());
                cstmt.setString(5, fromdate);
                cstmt.setString(6, todate);
            }
            rs = cstmt.executeQuery();
            con.commit();
            con.setAutoCommit(true);
            while (rs.next()) {
                CommonReportDTO surgeryreportdto2 = new CommonReportDTO();
                surgeryreportdto2.setMandalcount(rs.getString("NumberofPesons"));
                surgeryreportdto2.setDistrictname(rs.getString("District_Name"));
                surgeryreportdto2.setDistrictid(rs.getString("District_ID"));
                surgeryreportdto2.setMandalid(rs.getString("Mandal_ID"));
                surgeryreportdto2.setMandal(rs.getString("Mandal_Name"));
                districtcertificatelist.add(surgeryreportdto2);
            }

        } catch (ParseException parseException) {
            int exResult = ExceptionDAO.saveException(datasource,parseException.toString(),  "getMandalReport", "CommonReportDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, parseException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportDAO", "getMandalReport");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMandalReport", "CommonReportDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportDAO", "getMandalReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMandalReport", "CommonReportDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportDAO", "getMandalReport");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cstmt);

           DBConnection.closeConnection(con);
        }
        return districtcertificatelist;
    }

    /**
     * this method is for fetching for commonreport under villiagewise from database
     * @param datasource 
     * @param commonreportdto 
     * @param surgerytype 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getVillageReport(DataSource datasource, CommonReportDTO commonreportdto, String surgerytype) throws SADAREMDBException, SQLException {
        // method to retrive village level report accourding to surgery type -krishna
        CallableStatement cstmt = null;
        Connection con = null;

        ResultSet rs = null;
        ArrayList villagewiselist = new ArrayList();
        try {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(commonreportdto.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);


            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(commonreportdto.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);

            String districtid = commonreportdto.getDistrictid();
            String mandalid = commonreportdto.getMandalid();
            String tablename = commonreportdto.getTablename();
            String fieldvalue = commonreportdto.getFieldvalue();




            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            if (surgerytype.equals("orthosis")) {
                cstmt = con.prepareCall("{Call SP_Reports_for_Orthosis_Villagewise(?,?,?,?)}");
                cstmt.setString(1, districtid);
                cstmt.setString(2, mandalid);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);


            } else if (surgerytype.equals("prosthesis")) {

                cstmt = con.prepareCall("{Call SP_Reports_for_Prosthesis_Villagewise(?,?,?,?)}");
                cstmt.setString(1, districtid);
                cstmt.setString(2, mandalid);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);

            } else if (surgerytype.equals("Physiotherapy") || surgerytype.equals("SpeechTherapy") || surgerytype.equals("CouncellingGuidance")) {
                cstmt = con.prepareCall("{Call SP_Reports_to_select_from_singletable_and_Multiplecolumns_Villagewise(?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, districtid);
                cstmt.setString(2, mandalid);
                cstmt.setString(3, tablename);
                cstmt.setString(4, commonreportdto.getFirstcolumn());
                cstmt.setString(5, commonreportdto.getFieldvalue());
                cstmt.setString(6, commonreportdto.getSecondcolumn());
                cstmt.setString(7, commonreportdto.getSecondfieldvalue());
                cstmt.setString(8, fromdate);
                cstmt.setString(9, todate);
            } else if (surgerytype.equals("HearingAidType")) {
                cstmt = con.prepareCall("{Call SP_Reports_for_HearingAidType_Villagewise(?,?,?,?)}");
                cstmt.setString(1, districtid);
                cstmt.setString(2, mandalid);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);


            } else if (surgerytype.equals("WalkingFrame")) {
                cstmt = con.prepareCall("{Call SP_Reports_for_WalkingFrame_Villagewise(?,?,?,?)}");
                cstmt.setString(1, districtid);
                cstmt.setString(2, mandalid);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);

            } else {
                cstmt = con.prepareCall("{Call SP_Reports_to_select_from_singletable_and_singlecolumn_Villagewise(?,?,?,?,?,?,?)}");
                cstmt.setString(1, districtid);
                cstmt.setString(2, mandalid);
                cstmt.setString(3, tablename);
                cstmt.setString(4, surgerytype);
                cstmt.setString(5, fieldvalue);
                cstmt.setString(6, fromdate);
                cstmt.setString(7, todate);

            }
            rs = cstmt.executeQuery();
            con.commit();
            con.setAutoCommit(true);
            while (rs.next()) {
                CommonReportDTO commonreportdto1 = new CommonReportDTO();
                commonreportdto1.setDistrictid(districtid);
                commonreportdto1.setMandalid(mandalid);
                commonreportdto1.setDistrictname(rs.getString("District_Name"));
                commonreportdto1.setMandalname(rs.getString("Mandal_Name"));
                commonreportdto1.setVillageid(rs.getString("Village_ID"));
                commonreportdto1.setVillagecount(rs.getString("NumberofPersons"));
                commonreportdto1.setVillagename(rs.getString("Village_Name"));
                villagewiselist.add(commonreportdto1);
            }

        } catch (ParseException parseException) {
            int exResult = ExceptionDAO.saveException(datasource,parseException.toString(),  "getVillageReport", "CommonReportDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, parseException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportDAO", "getVillageReport");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVillageReport", "CommonReportDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportDAO", "getVillageReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVillageReport", "CommonReportDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportDAO", "getVillageReport");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cstmt);

           DBConnection.closeConnection(con);
        }
        return villagewiselist;
    }

    /**
     * this method is for fetching for commonreport under habitationwise from database
     * @param datasource 
     * @param commonreportdto 
     * @param surgerytype 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getHabitationReport(DataSource datasource, CommonReportDTO commonreportdto,
            String surgerytype) throws SADAREMDBException, SQLException {
        CallableStatement cstmt = null;
        Connection con = null;

        ResultSet rs = null;
        ArrayList habitationwiselist = new ArrayList();
        try {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(commonreportdto.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(commonreportdto.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);

            String districtid = commonreportdto.getDistrictid();
            String mandalid = commonreportdto.getMandalid();
            String villageid = commonreportdto.getVillageid();
            String tablename = commonreportdto.getTablename();
            String fieldvalue = commonreportdto.getFieldvalue();

            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            if (surgerytype.equals("orthosis")) {
                cstmt = con.prepareCall("{Call SP_Reports_for_Orthosis_Habitationwise(?,?,?,?,?)}");
                cstmt.setString(1, districtid);
                cstmt.setString(2, mandalid);
                cstmt.setString(3, villageid);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);

            } else if (surgerytype.equals("prosthesis")) {
                cstmt = con.prepareCall("{Call SP_Reports_for_Prosthesis_Habitationwise(?,?,?,?,?)}");
                cstmt.setString(1, districtid);
                cstmt.setString(2, mandalid);
                cstmt.setString(3, villageid);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);

            } else if (surgerytype.equals("Physiotherapy") || surgerytype.equals("SpeechTherapy") || surgerytype.equals("CouncellingGuidance")) {
                cstmt = con.prepareCall("{Call SP_Reports_to_select_from_singletable_and_Multiplecolumns_Habitationwise(?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, districtid);
                cstmt.setString(2, mandalid);
                cstmt.setString(3, villageid);
                cstmt.setString(4, tablename);
                cstmt.setString(5, commonreportdto.getFirstcolumn());
                cstmt.setString(6, commonreportdto.getFieldvalue());
                cstmt.setString(7, commonreportdto.getSecondcolumn());
                cstmt.setString(8, commonreportdto.getSecondfieldvalue());
                cstmt.setString(9, fromdate);
                cstmt.setString(10, todate);
            } else if (surgerytype.equals("HearingAidType")) {
                cstmt = con.prepareCall("{Call SP_Reports_for_HearingAidType_Habitationwise(?,?,?,?,?)}");
                cstmt.setString(1, districtid);
                cstmt.setString(2, mandalid);
                cstmt.setString(3, villageid);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);

            } else if (surgerytype.equals("WalkingFrame")) {
                cstmt = con.prepareCall("{Call SP_Reports_for_WalkingFrame_Habitationwise(?,?,?,?,?)}");
                cstmt.setString(1, districtid);
                cstmt.setString(2, mandalid);
                cstmt.setString(3, villageid);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);


            } else {
                cstmt = con.prepareCall("{Call SP_Reports_to_select_from_singletable_and_singlecolumn_Habitationwise(?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, districtid);
                cstmt.setString(2, mandalid);
                cstmt.setString(3, villageid);
                cstmt.setString(4, tablename);
                cstmt.setString(5, surgerytype);
                cstmt.setString(6, fieldvalue);
                cstmt.setString(7, fromdate);
                cstmt.setString(8, todate);
            }
            rs = cstmt.executeQuery();
            con.commit();
            con.setAutoCommit(true);

            while (rs.next()) {
                CommonReportDTO commonreportdto1 = new CommonReportDTO();
                commonreportdto1.setDistrictid(districtid);
                commonreportdto1.setMandalid(mandalid);
                commonreportdto1.setVillageid(villageid);
                commonreportdto1.setDistrictname(rs.getString("District_Name"));
                commonreportdto1.setMandalname(rs.getString("Mandal_Name"));
                commonreportdto1.setVillagename("Village_Name");
                commonreportdto1.setHabitationid(rs.getString("Habitation_ID"));
                commonreportdto1.setHabitationcount(rs.getString("NumberofPersons"));
                commonreportdto1.setHabitationname(rs.getString("Habitation_Name"));
                habitationwiselist.add(commonreportdto1);
            }


        } catch (ParseException parseException) {
            int exResult = ExceptionDAO.saveException(datasource,parseException.toString(),  "getHabitationReport", "CommonReportDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, parseException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportDAO", "getHabitationReport");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHabitationReport", "CommonReportDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportDAO", "getHabitationReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHabitationReport", "CommonReportDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportDAO", "getHabitationReport");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cstmt);

           DBConnection.closeConnection(con);
        }

        return habitationwiselist;
    }

    /**
     * this method is for fetching for Niramaya under districtwise from database
     * @param ds
     * @param From date
     * @param Todate
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getNiramayaDistrictReport(DataSource ds)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList districtniramayalist = null;
        try {
            con = DBConnection.getConnection();
            districtniramayalist = new ArrayList();
            cstmt = con.prepareCall("{Call SP_REPORTFORNIRAMAYADATADISTRICTWISE}");
            rs = cstmt.executeQuery();
            while (rs.next()) {
                CommonReportDTO niramayareportdto = new CommonReportDTO();
                niramayareportdto.setDistrictid(rs.getString("DISTRICTID"));
                niramayareportdto.setDistrict(rs.getString("DISTRICTNAME"));
                niramayareportdto.setDistrictMRCount(rs.getString("MENTALRETARDATION"));
                niramayareportdto.setDistrictCEREBRALPALSYCount(rs.getString("CEREBRALPALSY"));
                niramayareportdto.setDistrictMULTIPLECount(rs.getString("MULTIPLEDISABILITY"));
                districtniramayalist.add(niramayareportdto);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getNiramayaDistrictReport", "CommonReportDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportDAO", "getNiramayaDistrictReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getNiramayaDistrictReport", "CommonReportDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportDAO", "getNiramayaDistrictReport");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cstmt);

           DBConnection.closeConnection(con);
        }
        return districtniramayalist;
    }

    /**
     * this method is for fetching for Niramaya under Mandalwise from database
     * @param ds
     * @param From date
     * @param Todate
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getNiramayaMandalReport(DataSource ds, String District_Id)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList districtniramayalist = null;
        try {
            con = DBConnection.getConnection();
            districtniramayalist = new ArrayList();
            cstmt = con.prepareCall("{Call SP_REPORTFORNIRAMAYADATAMANDALWISE(?)}");
            cstmt.setString(1, District_Id);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                CommonReportDTO niramayareportdto = new CommonReportDTO();
                niramayareportdto.setMandalid(rs.getString("MANDALID"));
                niramayareportdto.setMandalname(rs.getString("MANDALNAME"));
                niramayareportdto.setMandalMRCount(rs.getString("MENTALRETARDATION"));
                niramayareportdto.setMandalCEREBRALPALSYCount(rs.getString("CEREBRALPALSY"));
                niramayareportdto.setMandalMULTIPLECount(rs.getString("MULTIPLEDISABILITY"));
                districtniramayalist.add(niramayareportdto);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getNiramayaMandalReport", "CommonReportDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportDAO", "getNiramayaMandalReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getNiramayaMandalReport", "CommonReportDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportDAO", "getNiramayaMandalReport");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cstmt);

           DBConnection.closeConnection(con);
        }
        return districtniramayalist;
    }

    /**
     * this method is for fetching for Niramaya under Mandalwise from database
     * @param ds
     * @param From date
     * @param Todate
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getNiramayaVillageReport(DataSource ds, String District_Id, String mandalId)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList districtniramayalist = null;
        try {
            con = DBConnection.getConnection();
            districtniramayalist = new ArrayList();
            cstmt = con.prepareCall("{Call SP_REPORTFORNIRAMAYADATAVILLAGEWISE(?,?)}");
            cstmt.setString(1, District_Id);
            cstmt.setString(2, mandalId);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                CommonReportDTO niramayareportdto = new CommonReportDTO();
                niramayareportdto.setVillageid(rs.getString("VILLAGEID"));
                niramayareportdto.setVillagename(rs.getString("VILLAGENAME"));
                niramayareportdto.setVillageMRCount(rs.getString("MENTALRETARDATION"));
                niramayareportdto.setVillageCEREBRALPALSYCount(rs.getString("CEREBRALPALSY"));
                niramayareportdto.setVillageMULTIPLECount(rs.getString("MULTIPLEDISABILITY"));
                districtniramayalist.add(niramayareportdto);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getNiramayaVillageReport", "CommonReportDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportDAO", "getNiramayaVillageReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getNiramayaVillageReport", "CommonReportDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportDAO", "getNiramayaVillageReport");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cstmt);

           DBConnection.closeConnection(con);
        }
        return districtniramayalist;
    }

    /**
     * this method is for fetching for Niramaya under Villagewise from database
     * @param ds
     * @param From date
     * @param Todate
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getNiramayaHabitationReport(DataSource ds, String District_Id, String mandalId, String villageId)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList districtniramayalist = null;
        int mrCount = 0;
        int cCount = 0;
        int mulCount = 0;
        try {
            con = DBConnection.getConnection();
            districtniramayalist = new ArrayList();
            cstmt = con.prepareCall("{Call SP_REPORTFORNIRAMAYADATAHABITATIONWISE(?,?,?)}");
            cstmt.setString(1, District_Id);
            cstmt.setString(2, mandalId);
            cstmt.setString(3, villageId);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                CommonReportDTO niramayareportdto = new CommonReportDTO();
                niramayareportdto.setHabitationid(rs.getString("HABITATIONID"));
                niramayareportdto.setHabitationname(rs.getString("HABITATIONNAME"));
                niramayareportdto.setHabitationMRCount(rs.getString("MENTALRETARDATION"));
                niramayareportdto.setHabitationCEREBRALPALSYCount(rs.getString("CEREBRALPALSY"));
                niramayareportdto.setHabitationMULTIPLECount(rs.getString("MULTIPLEDISABILITY"));
                if ((District_Id.equals("16") && mandalId.equals("84") && villageId.equals("004")) && (rs.getString("HABITATIONID").equals("00") || rs.getString("HABITATIONID").equals("01"))) {

                    if (rs.getString("HABITATIONID").equals("00")) {

                        mrCount = rs.getInt("MENTALRETARDATION");
                        cCount = rs.getInt("CEREBRALPALSY");
                        mulCount = rs.getInt("MULTIPLEDISABILITY");

                    }
                    if (rs.getString("HABITATIONID").equals("01")) {
                        
                        mrCount = mrCount + rs.getInt("MENTALRETARDATION");
                        cCount = cCount + rs.getInt("CEREBRALPALSY");
                        mulCount = mulCount + rs.getInt("MULTIPLEDISABILITY");

                        niramayareportdto.setHabitationMRCount(String.valueOf(mrCount));
                        niramayareportdto.setHabitationCEREBRALPALSYCount(String.valueOf(cCount));
                        niramayareportdto.setHabitationMULTIPLECount(String.valueOf(mulCount));
                        
                        districtniramayalist.add(niramayareportdto);
                    }
                } else {
                    districtniramayalist.add(niramayareportdto);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getNiramayaHabitationReport", "CommonReportDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportDAO", "getNiramayaHabitationReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getNiramayaHabitationReport", "CommonReportDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonReportDAO", "getNiramayaHabitationReport");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cstmt);

           DBConnection.closeConnection(con);
        }
        return districtniramayalist;
    }
}
