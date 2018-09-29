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
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.NewCertificateDTO;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.service.TransactionService;
import org.bf.disability.servicefactory.TerritoryServiceFactory;
import org.bf.disability.servicefactory.TransactionFactory;

import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 310926
 */
public class NewCertificateDAO {

    /**
     *
     * @param ds
     * @param DTO
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public String getPartACheckForDuplicate(DataSource ds, NewCertificateDTO NewCertificateDTO) throws SADAREMDBException, SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String partACheckForDuplicatePersonCode = null;
        try {

            con = DBConnection.getConnection();
            pstmt = con.prepareStatement("select Person_Code from tblPerson_Personal_Details  with (nolock) where "
                    + " District_Id=? and Mandal_Id =? and Village_Id = ? and  Habitation_ID= ? "
                    + "and UPPER(Surname) =?  and UPPER(First_Name)=?    and  Age_Years=? and  Gender=? "
                    + "and Relation_Name =? and UPPER(House_Number) =?");

            pstmt.setString(1, NewCertificateDTO.getDistrict_id());
            pstmt.setString(2, NewCertificateDTO.getMandal_id());
            pstmt.setString(3, NewCertificateDTO.getVillage_id());
            pstmt.setString(4, NewCertificateDTO.getHabitation_id());
            pstmt.setString(5, NewCertificateDTO.getSurname().toUpperCase());
            pstmt.setString(6, NewCertificateDTO.getFirstname().toUpperCase());
            //pstmt.setString(7,"null");
            pstmt.setString(7, NewCertificateDTO.getNoOfYears());
            pstmt.setString(8, NewCertificateDTO.getGender());
            pstmt.setString(9, NewCertificateDTO.getGsurname());
            pstmt.setString(10, NewCertificateDTO.getHouseno().toUpperCase());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                partACheckForDuplicatePersonCode = rs.getString(1);

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPartACheckForDuplicate", "NewCertificateDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewCertificateDAO", "getPartACheckForDuplicate");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPartACheckForDuplicate", "NewCertificateDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewCertificateDAO", "getPartACheckForDuplicate");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }


        return partACheckForDuplicatePersonCode;
    }

    public synchronized boolean isDuplicateSlno(NewCertificateDTO NewCertificateDTO, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        String sql = null;
        boolean exists_slno = false;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            
            con.setAutoCommit(false);
            sql = "select * from tblperson_personal_details  with (nolock) where rationcard_number=? and RationCard_Slno=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, NewCertificateDTO.getCard());
            pstmt.setString(2, NewCertificateDTO.getRationCardSlno());
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    exists_slno = true;
                }
            }

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "isDuplicateSlno", "NewCertificateDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewCertificateDAO", "isDuplicateSlno");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "isDuplicateSlno", "NewCertificateDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewCertificateDAO", "isDuplicateSlno");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);

            DBConnection.closeResultSet(rs);
        }
        return exists_slno;
    }

    /**
     *
     * @param partadto
     * @param ds
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public synchronized String insertPersonalDetailsForRationCard(NewCertificateDTO newCertificateDTO, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException {

        CallableStatement calstmt = null;
        Connection con = null;
        String personcodemax = null;
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        String districtid = newCertificateDTO.getDistrict_id();
        String mandalid = newCertificateDTO.getMandal_id();
        String villageid = newCertificateDTO.getVillage_id();
        String habitationid = newCertificateDTO.getHabitation_id();
        String panchayatid = newCertificateDTO.getPanchayat_id();
        String assemblyid = null;
        HttpSession session = request.getSession();
        String reasonForStatus = null;
        int partAEnteredStatus = 0;
        String habCode = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(newCertificateDTO.getFromdate());
            String formdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date ddate = new SimpleDateFormat("dd/mm/yyyy").parse(newCertificateDTO.getDobday());
            String dobdate = new SimpleDateFormat("mm/dd/yyyy").format(ddate);


            session.removeAttribute("categoryIdAu");
            session.removeAttribute("categoryCountAu");

            int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));

            /** Check Habcode base on selecting territorydetails */
            habCode = this.checkHabitation(ds, districtid, mandalid, panchayatid, villageid, habitationid);

            if (habCode != null) {
                personcodemax = territoryservice.getHabitationCodeRationCard(districtid, mandalid, panchayatid, assemblyid, villageid, habitationid, ds);
                

                if (habCode.equals(personcodemax.substring(0, 14))) {
                    if (newCertificateDTO.getPersonstatus() != null) {
                        calstmt = con.prepareCall("{Call SP_tblPerson_Personal_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

                        calstmt.setString(1, personcodemax);
                        calstmt.setString(2, newCertificateDTO.getFormno());
                        calstmt.setString(3, formdate);
                        calstmt.setString(4, convertFirstLetterToUpperCase(newCertificateDTO.getSurname()));
                        calstmt.setString(5, newCertificateDTO.getFirstname());
                        calstmt.setString(6, newCertificateDTO.getGender());
                        calstmt.setString(7, dobdate);
                        if (newCertificateDTO.getNoOfYears() != null && newCertificateDTO.getNoOfYears().length() > 0
                                && !newCertificateDTO.getNoOfYears().equals(null)) {
                            calstmt.setString(8, newCertificateDTO.getNoOfYears());
                        }
                        calstmt.setString(9, newCertificateDTO.getReligion());
                        calstmt.setString(10, newCertificateDTO.getCaste());
                        calstmt.setString(11, newCertificateDTO.getMarital());
                        calstmt.setString(12, newCertificateDTO.getEducation());
                        calstmt.setString(13, newCertificateDTO.getEmployment());
                        calstmt.setString(14, newCertificateDTO.getGrelation());
                        calstmt.setString(15, newCertificateDTO.getGsurname());
                        calstmt.setString(16, newCertificateDTO.getRtype());
                        calstmt.setString(17, newCertificateDTO.getCard());
                        calstmt.setBoolean(18, newCertificateDTO.isEpiccard());
                        calstmt.setString(19, newCertificateDTO.getEpiccardno());
                        calstmt.setBoolean(20, true);
                        calstmt.setString(21, "Disabled");
                        calstmt.setString(22, "PIII");
                        calstmt.setString(23, convertFirstLetterToUpperCase(newCertificateDTO.getMole1()));
                        calstmt.setString(24, convertFirstLetterToUpperCase(newCertificateDTO.getMole2()));
                        calstmt.setString(25, newCertificateDTO.getHouseno());
                        calstmt.setString(26, newCertificateDTO.getPhoneno());
                        calstmt.setString(27, newCertificateDTO.getEmailId());
                        calstmt.setString(28, newCertificateDTO.getDistrict_id());
                        calstmt.setString(29, newCertificateDTO.getMandal_id());
                        calstmt.setString(30, newCertificateDTO.getVillage_id());
                        calstmt.setString(31, newCertificateDTO.getHabitation_id());
                        calstmt.setString(32, newCertificateDTO.getPin());
                        calstmt.setString(33, newCertificateDTO.getTelugupersonname());
                        calstmt.setString(34, newCertificateDTO.getTelugufathername());
                        calstmt.setString(35, newCertificateDTO.getPersonstatus());
                        calstmt.setString(36, newCertificateDTO.getParents_marriage());
                        calstmt.setString(37, newCertificateDTO.getType_disability());
                        calstmt.setString(38, newCertificateDTO.getExistingpercentage());
                        calstmt.setString(39, newCertificateDTO.getLoginid());
                        calstmt.setString(40, newCertificateDTO.getSystemip());
                        calstmt.setInt(41, campId);
                        calstmt.setString(42, "PhaseIII");
                        calstmt.setString(43, "Live");
                        calstmt.setString(44, habCode);
                        calstmt.setInt(45, 1);
                        calstmt.setInt(46, 1);
                        calstmt.setString(47, newCertificateDTO.getRationCardSlno());
                        calstmt.setString(48, newCertificateDTO.getAadharCardNo());

                        partAEnteredStatus = calstmt.executeUpdate();
                        con.commit();
                        con.setAutoCommit(true);
                        if (partAEnteredStatus != 0) {
                            transactionService.insertTransactionalDetails(ds, "PartA Details Entered", personcodemax, request);

                        }
                    }
                } else {
                    personcodemax = "Not Match";
                }
            } else {
                personcodemax = "Not Match";
            }
        } catch (SQLException sqlEx) {
            transactionService.updateTransactionDetails(ds, "Error PartA Details", personcodemax);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPersonalDetailsForRationCard", "NewCertificateDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewCertificateDAO", "insertPersonalDetailsForRationCard");
        } catch (Exception sqlEx) {
            transactionService.updateTransactionDetails(ds, "Error PartA Details", personcodemax);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPersonalDetailsForRationCard", "NewCertificateDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewCertificateDAO", "insertPersonalDetailsForRationCard");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(calstmt);


        }
        return personcodemax;
    }

    public String checkHabitation(DataSource ds, String districtId, String mandalId, String panchayatId, String villageId, String habitationId) throws SADAREMDBException, SQLException {
        String habitationIdGen = null;
        String assemblyIdGen = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet habcodeRS = null;
        ResultSet assemblyRS = null;
        String habcodeSQL = null;
        String assemblySQL = null;
        try {
            con = DBConnection.getConnection();
            

            assemblySQL = "select assembly_id from tblHabitation_Details where district_id=? and "
                    + "mandal_id=? and panchayat_id=? and "
                    + "village_id=? and habitation_id=?";
            pstmt = con.prepareStatement(assemblySQL);
            pstmt.setString(1, districtId);
            pstmt.setString(2, mandalId);
            pstmt.setString(3, panchayatId);
            pstmt.setString(4, villageId);
            pstmt.setString(5, habitationId);
            assemblyRS = pstmt.executeQuery();
            if (assemblyRS != null) {
                while (assemblyRS.next()) {
                    assemblyIdGen = assemblyRS.getString(1);
                }
            }

            habcodeSQL = "select habitation_code from tblHabitation_Details where district_id=? and assembly_id=? and "
                    + "mandal_id=? and panchayat_id=? and "
                    + "village_id=? and habitation_id=?";
            pstmt = con.prepareStatement(habcodeSQL);
            pstmt.setString(1, districtId);
            pstmt.setString(2, assemblyIdGen);
            pstmt.setString(3, mandalId);
            pstmt.setString(4, panchayatId);
            pstmt.setString(5, villageId);
            pstmt.setString(6, habitationId);
            assemblyRS = pstmt.executeQuery();
            habcodeRS = pstmt.executeQuery();

            if (habcodeRS != null) {
                while (habcodeRS.next()) {
                    habitationIdGen = habcodeRS.getString(1);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkHabitation", "NewCertificateDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewCertificateDAO", "checkHabitation");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkHabitation", "NewCertificateDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewCertificateDAO", "checkHabitation");
        } finally {
            try {
                DBConnection.closeConnection(con);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeResultSet(assemblyRS);
                DBConnection.closeResultSet(habcodeRS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return habitationIdGen;
    }

    /**
     *
     * @param inputString
     * @return
     */
    public String convertFirstLetterToUpperCase(String inputString) {
        if (inputString.equals("")) {
            return inputString;
        } else {
            StringBuffer inputStrBuffer = new StringBuffer(inputString);
            String firstLetterUpperCase = inputStrBuffer.substring(0, 1).toUpperCase();
            inputStrBuffer.replace(0, 1, firstLetterUpperCase);
            return inputStrBuffer.toString();
        }

    }

    public String getdistrictName(DataSource ds, String districtId) throws SADAREMDBException, SQLException {
        String districtName = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SQL = null;
        try {
            con = DBConnection.getConnection();
            

            SQL = "select district_name from dbo.tblDistrict_Details where district_id=? ";
            pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, districtId);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    districtName = rs.getString(1);
                }
            }



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getdistrictName", "NewCertificateDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewCertificateDAO", "getdistrictName");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getdistrictName", "NewCertificateDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewCertificateDAO", "getdistrictName");
        } finally {
            try {
                DBConnection.closeConnection(con);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeResultSet(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return districtName;
    }
}
