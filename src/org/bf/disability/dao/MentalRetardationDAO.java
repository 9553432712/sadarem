
/*
 * MentalRetardationDAO.java
 *
 * Created on July 16, 2008, 12:27 PM
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.dto.MRBinetKamatDetailedTestDTO;
import org.bf.disability.dto.MentalRetardationDTO;
import org.bf.disability.dto.MentalRetardationTestsDTO;
import org.bf.disability.service.TransactionService;
import org.bf.disability.servicefactory.TransactionFactory;

import com.tcs.sgv.common.util.DBConnection;

/**
 * This DAO is used for inserting ,retriving and updating into database .
 * @author Sunima
 */
public class MentalRetardationDAO {

    /**
     * this method is used for insert into SP_tblMental_Retardation_Details_Insert
     * @param datasource
     * @param mentalretardationtestdto
     * @return int
     */
    public int insertData(DataSource ds, MentalRetardationDTO modifydto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        CallableStatement cstmt1 = null;
        Connection con = null;
        int insert = 0;

        String code = modifydto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            details = dao.getCategoryDetails(ds, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            /* Getting the categeoryIdncategoryCount */

            cstmt1 = con.prepareCall("{Call SP_tblMental_Retardation_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt1.setString(1, code);
            cstmt1.setString(2, (modifydto.getIq()));
            cstmt1.setString(3, modifydto.getChronologicalage());
            cstmt1.setDouble(4, modifydto.getTotalpercent());
            cstmt1.setString(5, modifydto.getSpeechtheraphy());
            cstmt1.setString(6, modifydto.getBehaviormodification());
            //cstmt1.setString(7,modifydto.getSurgery());//delete
            cstmt1.setString(7, modifydto.getSensoryintegrationcognitive());//newly added
            cstmt1.setString(8, modifydto.getSpeechtherapy());
            //cstmt1.setString(9,modifydto.getLanguagedevelopment());//deleted
            cstmt1.setString(9, modifydto.getPhysiotherapy());//newly added
            cstmt1.setString(10, modifydto.getOccupationaltherapy());//newly added
            cstmt1.setString(11, modifydto.getPsycotherapybehaviour());
            cstmt1.setString(12, modifydto.getCognitivebehaviourtherapy());//newly added
            cstmt1.setString(13, modifydto.getParientfamilyintervention());//newly added
            cstmt1.setString(14, modifydto.getLegalguardian());
            cstmt1.setString(15, modifydto.getLearningmaterials());
            cstmt1.setString(16, modifydto.getSpecialsoftware());
            cstmt1.setString(17, modifydto.getToys());
            cstmt1.setString(18, modifydto.getAnyothermentalretardation());
            cstmt1.setString(19, modifydto.getLoginid());
            cstmt1.setString(20, modifydto.getSystemip());

            cstmt1.setInt(21, Integer.parseInt(details[0].toString()));
            cstmt1.setInt(22, Integer.parseInt(details[1].toString()));

            insert = cstmt1.executeUpdate();
            cstmt1.close();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in inserting Mental Retradation Details", code);
            // End
            insert = -2;
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "insertData", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "insertData");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt1);

        }
        return insert;

    }

    /**
     * this method is used for insert into SP_tblMental_Retardation_Details_Insert
     * @param datasource
     * @param mentalretardationtestdto
     * @return int
     */
    public int insertDataAU(DataSource ds, MentalRetardationDTO modifydto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        CallableStatement cstmt1 = null;
        Connection con = null;
        int insert = 0;
        String code = modifydto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO(); 
      //  Statement stmt = null;
        PreparedStatement pstmt =null ;
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, code);

            con = DBConnection.getConnection();
            con.setAutoCommit(false);


            //stmt = con.createStatement();
            pstmt = con.prepareStatement("select * from tblMental_Retardation_Details where Person_Code=? and status='Active'");
            pstmt.setString(1, code);
            rs = pstmt.executeQuery();
            if (!rs.next()) {

                cstmt1 = con.prepareCall("{Call SP_tblMental_Retardation_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt1.setString(1, code);
                cstmt1.setString(2, (modifydto.getIq()));
                cstmt1.setString(3, modifydto.getChronologicalage());
                cstmt1.setDouble(4, modifydto.getTotalpercent());
                cstmt1.setString(5, modifydto.getSpeechtheraphy());
                cstmt1.setString(6, modifydto.getBehaviormodification());
                //cstmt1.setString(7,modifydto.getSurgery());//delete
                cstmt1.setString(7, modifydto.getSensoryintegrationcognitive());//newly added
                cstmt1.setString(8, modifydto.getSpeechtherapy());
                //cstmt1.setString(9,modifydto.getLanguagedevelopment());//deleted
                cstmt1.setString(9, modifydto.getPhysiotherapy());//newly added
                cstmt1.setString(10, modifydto.getOccupationaltherapy());//newly added
                cstmt1.setString(11, modifydto.getPsycotherapybehaviour());
                cstmt1.setString(12, modifydto.getCognitivebehaviourtherapy());//newly added
                cstmt1.setString(13, modifydto.getParientfamilyintervention());//newly added
                cstmt1.setString(14, modifydto.getLegalguardian());
                cstmt1.setString(15, modifydto.getLearningmaterials());
                cstmt1.setString(16, modifydto.getSpecialsoftware());
                cstmt1.setString(17, modifydto.getToys());
                cstmt1.setString(18, modifydto.getAnyothermentalretardation());
                cstmt1.setString(19, modifydto.getLoginid());
                cstmt1.setString(20, modifydto.getSystemip());

                cstmt1.setInt(21, Integer.parseInt(details[0].toString()));
                cstmt1.setInt(22, Integer.parseInt(details[1].toString()));

                insert = cstmt1.executeUpdate();
                cstmt1.close();
                con.commit();
                con.setAutoCommit(true);
            } else {
                insert = -1;
            }
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in inserting Mental Retradation Details", code);
            // End
            insert = -2;
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "insertDataAU", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "insertDataAU");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt1);
            DBConnection.closeStatement(pstmt);
           // DBConnection.closeStatement(stmt);
        }
        return insert;

    }

    /**
     * this method is used to select .
     * @param datasource
     * @param personcode
     * @return modifydto
     */
    public MentalRetardationDTO getMentalDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        CallableStatement cstmt = null;
        Connection con = null;
        MentalRetardationDTO modifydto = new MentalRetardationDTO();
        String str = null;
        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_tblMental_Retardation_Details_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                modifydto.setIq(rs.getString("Mental_Age"));
                modifydto.setChronologicalage(rs.getString("Chronological_Age"));
                modifydto.setTotalpercent(rs.getDouble("Total_Value"));
                modifydto.setSpeechtheraphy(rs.getString("SpeechTherapyforthreeyears"));
                modifydto.setBehaviormodification(rs.getString("BehaviourModificationforthreeyears"));
                // modifydto.setSurgery(rs.getString("Surgerey"));//deleted
                modifydto.setSpeechtherapy(rs.getString("SpeechTherapy"));
                // modifydto.setLanguagedevelopment(rs.getString("LanguageDevelopment"));//deleted
                modifydto.setPsycotherapybehaviour(rs.getString("PsycotherapyBehaviour"));
                modifydto.setLegalguardian(rs.getString("LegalGuardian"));
                modifydto.setLearningmaterials(rs.getString("LearningMaterial"));
                modifydto.setSpecialsoftware(rs.getString("SpecialSoftware"));
                modifydto.setToys(rs.getString("Toys"));
                modifydto.setAnyothermentalretardation(rs.getString("AnyOther"));
                //Newely Added Fields 
                modifydto.setPhysiotherapy(rs.getString("Physiotherapy"));
                modifydto.setOccupationaltherapy(rs.getString("OccupationalTherapy"));
                modifydto.setCognitivebehaviourtherapy(rs.getString("CognitiveBehaviourTherapy"));
                modifydto.setParientfamilyintervention(rs.getString("ParentFamilyIntervention"));
                modifydto.setSensoryintegrationcognitive(rs.getString("SensoryIntegrationCognitive"));


            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getMentalDetails", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getMentalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getMentalDetails", "MentalRetardationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getMentalDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return modifydto;
    }

    /**
     * this method is used to update the data .
     * @param datasource
     * @param modifydto
     */
    public void updateDetails(DataSource datasource, MentalRetardationDTO modifydto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        CallableStatement cs = null;
        Connection con = null;

        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        String code = modifydto.getPersoncode();
        try {

            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(datasource, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);


            if (modifydto.getTotalpercent() == 0) {
                // query="delete from tblMental_Retardation_Details where Person_Code='"+modifydto.getPersoncode()+"'";
                CommonDAO.changeStatusToInactiveForResetInUpdate(datasource, modifydto.getPersoncode(), "tblMental_Retardation_Details");
                //   stmt=con.createStatement();
                //   int t=stmt.executeUpdate(query);
            }


            cs = con.prepareCall("{Call SP_tblMental_Retardation_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, modifydto.getPersoncode());
            cs.setString(2, (modifydto.getIq()));
            cs.setString(3, modifydto.getChronologicalage());
            cs.setDouble(4, modifydto.getTotalpercent());
            cs.setString(5, modifydto.getSpeechtheraphy());
            cs.setString(6, modifydto.getBehaviormodification());
            // cs.setString(7,modifydto.getSurgery());//Deleted
            cs.setString(7, modifydto.getSensoryintegrationcognitive());//newly added
            cs.setString(8, modifydto.getSpeechtherapy());
            // cs.setString(9,modifydto.getLanguagedevelopment());//Deleted
            cs.setString(9, modifydto.getPhysiotherapy());//newly added
            cs.setString(10, modifydto.getOccupationaltherapy());//newly added
            cs.setString(11, modifydto.getPsycotherapybehaviour());
            cs.setString(12, modifydto.getCognitivebehaviourtherapy());//newly added
            cs.setString(13, modifydto.getParientfamilyintervention());//newly added
            cs.setString(14, modifydto.getLegalguardian());
            cs.setString(15, modifydto.getLearningmaterials());
            cs.setString(16, modifydto.getSpecialsoftware());
            cs.setString(17, modifydto.getToys());
            cs.setString(18, modifydto.getAnyothermentalretardation());
            cs.setString(19, modifydto.getLoginid());
            cs.setString(20, modifydto.getSystemip());

            cs.setInt(21, Integer.parseInt(details[0].toString()));
            cs.setInt(22, Integer.parseInt(details[1].toString()));


            cs.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(datasource, "Error in updating Mental Retradation Details", code);
            // End
            int exResult = ExceptionDAO.saveException(datasource, exception.toString(), "updateDetails", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "updateDetails");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(cs);

        }


    }

    /**
     * this method is used to check the person code.
     * @param ds
     * @param personcode
     */
    public boolean checkPersoncode(String personcode, DataSource ds) throws SADAREMDBException, SQLException {
        boolean personcodecheckflag = false;
        Connection con = null;
        ResultSet rs = null;
        //Statement stmt = null;  
        PreparedStatement pstmt = null;
        try {
            con = DBConnection.getConnection();
            String query = "select Person_Code from tblMental_Retardation_Details where Person_Code=? and status='Active'";
           // stmt = con.createStatement();
            
            pstmt = con.prepareStatement(query);
            
           pstmt.setString(1, personcode);
           
            rs = pstmt.executeQuery();
            if (rs.next() == false) {
                personcodecheckflag = false;
            } else {
                personcodecheckflag = true;
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersoncode", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "checkPersoncode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersoncode", "MentalRetardationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "checkPersoncode");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            //DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(pstmt);

        }
        return personcodecheckflag;
    }

    /**
     * this method is used for insert into SP_tblPerson_MR_PAT_Test_Details_Insert
     * @param datasource
     * @param mentalRetardationTestsDTO
     */
    public void insertMRAlexanderTestDetails(DataSource ds, String personcode, MentalRetardationTestsDTO mentalRetardationTestsDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cs = null;

        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        String code = mentalRetardationTestsDTO.getPersoncode();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, mentalRetardationTestsDTO.getPersoncode());
            con = DBConnection.getConnection();
            con.setAutoCommit(false);



            cs = con.prepareCall("{Call SP_tblPerson_MR_PAT_Test_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, mentalRetardationTestsDTO.getPersoncode());
            cs.setString(2, mentalRetardationTestsDTO.getPat_Second_One());
            cs.setString(3, mentalRetardationTestsDTO.getPat_Second_Two());
            cs.setString(4, mentalRetardationTestsDTO.getPat_Second_Three());
            cs.setString(5, mentalRetardationTestsDTO.getPat_Second_Four());
            cs.setString(6, mentalRetardationTestsDTO.getPat_Second_Five());
            cs.setString(7, mentalRetardationTestsDTO.getPat_Second_Six());
            cs.setString(8, mentalRetardationTestsDTO.getPat_Second_Seven());
            cs.setString(9, mentalRetardationTestsDTO.getPat_Second_Eight());
            cs.setString(10, mentalRetardationTestsDTO.getPat_Second_Nine());

            cs.setString(11, mentalRetardationTestsDTO.getPat_SA_One());
            cs.setString(12, mentalRetardationTestsDTO.getPat_SA_Two());
            cs.setString(13, mentalRetardationTestsDTO.getPat_SA_Three());
            cs.setString(14, mentalRetardationTestsDTO.getPat_SA_Four());
            cs.setString(15, mentalRetardationTestsDTO.getPat_SA_Five());
            cs.setString(16, mentalRetardationTestsDTO.getPat_SA_Six());
            cs.setString(17, mentalRetardationTestsDTO.getPat_SA_Seven());
            cs.setString(18, mentalRetardationTestsDTO.getPat_SA_Eight());
            cs.setString(19, mentalRetardationTestsDTO.getPat_SA_Nine());

            cs.setString(20, mentalRetardationTestsDTO.getPat_Remarks_One());
            cs.setString(21, mentalRetardationTestsDTO.getPat_Remarks_Two());
            cs.setString(22, mentalRetardationTestsDTO.getPat_Remarks_Three());
            cs.setString(23, mentalRetardationTestsDTO.getPat_Remarks_Four());
            cs.setString(24, mentalRetardationTestsDTO.getPat_Remarks_Five());
            cs.setString(25, mentalRetardationTestsDTO.getPat_Remarks_Six());
            cs.setString(26, mentalRetardationTestsDTO.getPat_Remarks_Seven());
            cs.setString(27, mentalRetardationTestsDTO.getPat_Remarks_Eight());
            cs.setString(28, mentalRetardationTestsDTO.getPat_Remarks_Nine());

            cs.setInt(29, Integer.parseInt(mentalRetardationTestsDTO.getPat_Year()));
            cs.setInt(30, Integer.parseInt(mentalRetardationTestsDTO.getPat_Month()));
            cs.setDouble(31, mentalRetardationTestsDTO.getPat_IQ());

            cs.setString(32, mentalRetardationTestsDTO.getLoginID());
            cs.setString(33, mentalRetardationTestsDTO.getSystemIP());


            cs.setInt(34, Integer.parseInt(details[0].toString()));
            cs.setInt(35, Integer.parseInt(details[1].toString()));

            cs.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in inserting MR Alexander Test Details", code);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "insertMRAlexanderTestDetails", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "insertMRAlexanderTestDetails");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cs);


        }
    }

    /**
     * this method is used for insert into SP_tblPerson_MR_PAT_Test_Details_Insert
     * @param datasource
     * @param mentalRetardationTestsDTO
     */
    public int insertMRAlexanderTestDetailsAU(DataSource ds, String personcode, MentalRetardationTestsDTO mentalRetardationTestsDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i = 0;
        Connection con = null;
        CallableStatement cs = null;
       // Statement stmt = null;
        PreparedStatement pstmt = null ; 
        
        
        ResultSet rs = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        String code = mentalRetardationTestsDTO.getPersoncode();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, mentalRetardationTestsDTO.getPersoncode());
            con = DBConnection.getConnection();
            con.setAutoCommit(false);



          //  stmt = con.createStatement();
            pstmt = con.prepareStatement("select * from tblPerson_MR_PAT_Test_Details where Person_Code=? and status='Active'");
            
           pstmt.setString(1, mentalRetardationTestsDTO.getPersoncode()); 
            
            rs = pstmt.executeQuery();
            if (!rs.next()) {

                cs = con.prepareCall("{Call SP_tblPerson_MR_PAT_Test_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

                cs.setString(1, mentalRetardationTestsDTO.getPersoncode());
                cs.setString(2, mentalRetardationTestsDTO.getPat_Second_One());
                cs.setString(3, mentalRetardationTestsDTO.getPat_Second_Two());
                cs.setString(4, mentalRetardationTestsDTO.getPat_Second_Three());
                cs.setString(5, mentalRetardationTestsDTO.getPat_Second_Four());
                cs.setString(6, mentalRetardationTestsDTO.getPat_Second_Five());
                cs.setString(7, mentalRetardationTestsDTO.getPat_Second_Six());
                cs.setString(8, mentalRetardationTestsDTO.getPat_Second_Seven());
                cs.setString(9, mentalRetardationTestsDTO.getPat_Second_Eight());
                cs.setString(10, mentalRetardationTestsDTO.getPat_Second_Nine());

                cs.setString(11, mentalRetardationTestsDTO.getPat_SA_One());
                cs.setString(12, mentalRetardationTestsDTO.getPat_SA_Two());
                cs.setString(13, mentalRetardationTestsDTO.getPat_SA_Three());
                cs.setString(14, mentalRetardationTestsDTO.getPat_SA_Four());
                cs.setString(15, mentalRetardationTestsDTO.getPat_SA_Five());
                cs.setString(16, mentalRetardationTestsDTO.getPat_SA_Six());
                cs.setString(17, mentalRetardationTestsDTO.getPat_SA_Seven());
                cs.setString(18, mentalRetardationTestsDTO.getPat_SA_Eight());
                cs.setString(19, mentalRetardationTestsDTO.getPat_SA_Nine());

                cs.setString(20, mentalRetardationTestsDTO.getPat_Remarks_One());
                cs.setString(21, mentalRetardationTestsDTO.getPat_Remarks_Two());
                cs.setString(22, mentalRetardationTestsDTO.getPat_Remarks_Three());
                cs.setString(23, mentalRetardationTestsDTO.getPat_Remarks_Four());
                cs.setString(24, mentalRetardationTestsDTO.getPat_Remarks_Five());
                cs.setString(25, mentalRetardationTestsDTO.getPat_Remarks_Six());
                cs.setString(26, mentalRetardationTestsDTO.getPat_Remarks_Seven());
                cs.setString(27, mentalRetardationTestsDTO.getPat_Remarks_Eight());
                cs.setString(28, mentalRetardationTestsDTO.getPat_Remarks_Nine());

                cs.setInt(29, Integer.parseInt(mentalRetardationTestsDTO.getPat_Year()));
                cs.setInt(30, Integer.parseInt(mentalRetardationTestsDTO.getPat_Month()));
                cs.setDouble(31, mentalRetardationTestsDTO.getPat_IQ());

                cs.setString(32, mentalRetardationTestsDTO.getLoginID());
                cs.setString(33, mentalRetardationTestsDTO.getSystemIP());

                cs.setInt(34, Integer.parseInt(details[0].toString()));
                cs.setInt(35, Integer.parseInt(details[1].toString()));

                i = cs.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            } else {
                i = -1;
            }
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in inserting MR Alexander Test Details", code);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "insertMRAlexanderTestDetailsAU", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "insertMRAlexanderTestDetailsAU");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cs);

            DBConnection.closeResultSet(rs);
            //DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(pstmt);

        }
        return i;
    }

    /**
     * this method is used to select .
     * @param ds
     * @param personcode
     * @return mentalRetardationTestsDTO
     */
    public MentalRetardationTestsDTO getMRAlexanderTestDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        CallableStatement cstmt = null;
        Connection con = null;
        MentalRetardationTestsDTO mentalRetardationTestsDTO = new MentalRetardationTestsDTO();
        String str = null;

        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_tblPerson_MR_PAT_Test_Details_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                mentalRetardationTestsDTO.setPat_Second_One(rs.getString(1));
                mentalRetardationTestsDTO.setPat_Second_Two(rs.getString(2));
                mentalRetardationTestsDTO.setPat_Second_Three(rs.getString(3));
                mentalRetardationTestsDTO.setPat_Second_Four(rs.getString(4));
                mentalRetardationTestsDTO.setPat_Second_Five(rs.getString(5));
                mentalRetardationTestsDTO.setPat_Second_Six(rs.getString(6));
                mentalRetardationTestsDTO.setPat_Second_Seven(rs.getString(7));
                mentalRetardationTestsDTO.setPat_Second_Eight(rs.getString(8));
                mentalRetardationTestsDTO.setPat_Second_Nine(rs.getString(9));

                mentalRetardationTestsDTO.setPat_SA_One(rs.getString(10));
                mentalRetardationTestsDTO.setPat_SA_Two(rs.getString(11));
                mentalRetardationTestsDTO.setPat_SA_Three(rs.getString(12));
                mentalRetardationTestsDTO.setPat_SA_Four(rs.getString(13));
                mentalRetardationTestsDTO.setPat_SA_Five(rs.getString(14));
                mentalRetardationTestsDTO.setPat_SA_Six(rs.getString(15));
                mentalRetardationTestsDTO.setPat_SA_Seven(rs.getString(16));
                mentalRetardationTestsDTO.setPat_SA_Eight(rs.getString(17));
                mentalRetardationTestsDTO.setPat_SA_Nine(rs.getString(18));

                mentalRetardationTestsDTO.setPat_Remarks_One(rs.getString(19));
                mentalRetardationTestsDTO.setPat_Remarks_Two(rs.getString(20));
                mentalRetardationTestsDTO.setPat_Remarks_Three(rs.getString(21));
                mentalRetardationTestsDTO.setPat_Remarks_Four(rs.getString(22));
                mentalRetardationTestsDTO.setPat_Remarks_Five(rs.getString(23));
                mentalRetardationTestsDTO.setPat_Remarks_Six(rs.getString(24));
                mentalRetardationTestsDTO.setPat_Remarks_Seven(rs.getString(25));
                mentalRetardationTestsDTO.setPat_Remarks_Eight(rs.getString(26));
                mentalRetardationTestsDTO.setPat_Remarks_Nine(rs.getString(27));

                mentalRetardationTestsDTO.setPat_Year(rs.getString(28));
                mentalRetardationTestsDTO.setPat_Month(rs.getString(29));
                mentalRetardationTestsDTO.setPat_IQ(rs.getFloat(30));
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMRAlexanderTestDetails", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getMRAlexanderTestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMRAlexanderTestDetails", "MentalRetardationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getMRAlexanderTestDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return mentalRetardationTestsDTO;
    }

    /**
     * this method is used for insert into SP_tblPerson_MR_SFB_Test_Details_Insert
     * @param ds 
     * @param mentalretardationtestdto 
     */
    public int MRSfbinsertData(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        CallableStatement cstmt1 = null;
        Connection con = null;
        int insert = 0;
        String code = mentalretardationtestdto.getPersoncode();

        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();


        try {

            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);


            cstmt1 = con.prepareCall("{Call SP_tblPerson_MR_SFB_Test_Details_Insert(?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt1.setString(1, code);
            cstmt1.setString(2, mentalretardationtestdto.getSfbtrialone());
            cstmt1.setString(3, mentalretardationtestdto.getSfbtrialtwo());
            cstmt1.setString(4, mentalretardationtestdto.getSfbtrialthree());
            cstmt1.setString(5, mentalretardationtestdto.getSfbyear());
            cstmt1.setString(6, mentalretardationtestdto.getSfbmonth());
            cstmt1.setDouble(7, mentalretardationtestdto.getSfbiq());
            cstmt1.setString(8, mentalretardationtestdto.getLoginid());
            cstmt1.setString(9, mentalretardationtestdto.getSystemip());

            cstmt1.setInt(10, Integer.parseInt(details[0].toString()));
            cstmt1.setInt(11, Integer.parseInt(details[0].toString()));

            insert = cstmt1.executeUpdate();
            cstmt1.close();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in inserting MRSFB Details", code);
            // End

            int exResult = ExceptionDAO.saveException(ds, e.toString(), "MRSfbinsertData", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "MRSfbinsertData");



        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt1);


        }
        return insert;

    }

    /**
     * this method is used for insert into SP_tblPerson_MR_SFB_Test_Details_Insert
     * @param ds
     * @param mentalretardationtestdto
     */
    public int MRSfbinsertDataAU(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        CallableStatement cstmt1 = null;
        Connection con = null;
        int insert = 0;
        //Statement stmt = null;
        PreparedStatement pstmt =null ;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
// Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        String code = mentalretardationtestdto.getPersoncode();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, mentalretardationtestdto.getPersoncode());
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

           // stmt = con.createStatement();
            pstmt = con.prepareStatement("select * from tblPerson_MR_SFB_Test_Details where Person_Code=? and status='Active'");
            
            pstmt.setString(1, mentalretardationtestdto.getPersoncode());
            
            rs = pstmt.executeQuery();
            if (!rs.next()) {

                cstmt1 = con.prepareCall("{Call SP_tblPerson_MR_SFB_Test_Details_Insert(?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt1.setString(1, mentalretardationtestdto.getPersoncode());
                cstmt1.setString(2, mentalretardationtestdto.getSfbtrialone());
                cstmt1.setString(3, mentalretardationtestdto.getSfbtrialtwo());
                cstmt1.setString(4, mentalretardationtestdto.getSfbtrialthree());
                cstmt1.setString(5, mentalretardationtestdto.getSfbyear());
                cstmt1.setString(6, mentalretardationtestdto.getSfbmonth());
                cstmt1.setDouble(7, mentalretardationtestdto.getSfbiq());

                cstmt1.setString(8, mentalretardationtestdto.getLoginid());
                cstmt1.setString(9, mentalretardationtestdto.getSystemip());

                cstmt1.setInt(10, Integer.parseInt(details[0].toString()));
                cstmt1.setInt(11, Integer.parseInt(details[1].toString()));

                insert = cstmt1.executeUpdate();
                cstmt1.close();
                con.commit();
                con.setAutoCommit(true);
            } else {
                insert = -1;
            }
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in inserting MRSFB Details", code);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "MRSfbinsertDataAU", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "MRSfbinsertDataAU");



        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt1); 
            DBConnection.closeStatement(pstmt);


        }
        return insert;

    }

    /**
     * this method is used to select .
     * @param ds
     * @param personcode
     * @return mentalRetardationTestsDTO
     */
    public MentalRetardationTestsDTO getMRseguintestDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        CallableStatement cstmt = null;
        Connection con = null;
        MentalRetardationTestsDTO mentalretardationtestdto = new MentalRetardationTestsDTO();
        String str = null;

        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_tblPerson_MR_SFB_Test_Details_Select(?)}");

            cstmt.setString(1, personcode);

            rs = cstmt.executeQuery();
            while (rs.next()) {
                mentalretardationtestdto.setSfbtrialone(rs.getString("SFB_Trail_One"));
                mentalretardationtestdto.setSfbtrialtwo(rs.getString("SFB_Trail_Two"));
                mentalretardationtestdto.setSfbtrialthree(rs.getString("SFB_Trail_Three"));
                mentalretardationtestdto.setSfbyear(String.valueOf(rs.getInt("SFB_Year")));

                mentalretardationtestdto.setSfbmonth(String.valueOf(rs.getInt("SFB_Month")));

                mentalretardationtestdto.setSfbiq(rs.getFloat("SFB_IQ"));


            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getMRseguintestDetails", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getMRseguintestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getMRseguintestDetails", "MentalRetardationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getMRseguintestDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return mentalretardationtestdto;
    }

    /**
     * this method is used for insert into SP_tblPerson_MR_MISIC_Test_Details_Insert
     * @param ds
     * @param mentalretardationtestdto
     */
    public int MRMalinsinsertData(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        CallableStatement cstmt1 = null;
        Connection con = null;
        int insert = 0;
        String code = mentalretardationtestdto.getPersoncode();

        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);



            cstmt1 = con.prepareCall("{Call SP_tblPerson_MR_MISIC_Test_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt1.setString(1, code);
            cstmt1.setString(2, mentalretardationtestdto.getMisicinformationraw());
            cstmt1.setString(3, mentalretardationtestdto.getMisicinformationtq());
            cstmt1.setString(4, mentalretardationtestdto.getMisiccomprehensionrawscore());
            cstmt1.setString(5, mentalretardationtestdto.getMisiccomprehensiontq());
            cstmt1.setString(6, mentalretardationtestdto.getMisicarithmeticrawscore());
            cstmt1.setString(7, mentalretardationtestdto.getMisicarithmetictq());
            cstmt1.setString(8, mentalretardationtestdto.getMisicsimilaritiesrawscore());
            cstmt1.setString(9, mentalretardationtestdto.getMisicsimilaritiestq());
            cstmt1.setString(10, mentalretardationtestdto.getMisicvocabularyrawscore());
            cstmt1.setString(11, mentalretardationtestdto.getMisicvocabularytq());
            cstmt1.setString(12, mentalretardationtestdto.getMisicdigitspanrawscore());
            cstmt1.setString(13, mentalretardationtestdto.getMisicdigitspantq());
            cstmt1.setString(14, mentalretardationtestdto.getMisicpcrawscore());
            cstmt1.setString(15, mentalretardationtestdto.getMisicpctq());
            cstmt1.setString(16, mentalretardationtestdto.getMisicbdrawscore());
            cstmt1.setString(17, mentalretardationtestdto.getMisicbdtq());
            cstmt1.setString(18, mentalretardationtestdto.getMisicoarawscore());
            cstmt1.setString(19, mentalretardationtestdto.getMisicoatq());
            cstmt1.setString(20, mentalretardationtestdto.getMisiccodingrawscore());
            cstmt1.setString(21, mentalretardationtestdto.getMisiccodingtq());
            cstmt1.setString(22, mentalretardationtestdto.getMisicmazesrawscore());
            cstmt1.setString(23, mentalretardationtestdto.getMisicmazestq());
            cstmt1.setDouble(24, mentalretardationtestdto.getMisiciq());
            cstmt1.setString(25, mentalretardationtestdto.getLoginid());
            cstmt1.setString(26, mentalretardationtestdto.getSystemip());

            cstmt1.setInt(27, Integer.parseInt(details[0].toString()));
            cstmt1.setInt(28, Integer.parseInt(details[1].toString()));

            insert = cstmt1.executeUpdate();
            cstmt1.close();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in inserting MRMalins Details", code);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "MRMalinsinsertData", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "MRMalinsinsertData");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt1);


        }
        return insert;

    }

    /**
     * this method is used for insert into SP_tblPerson_MR_MISIC_Test_Details_Insert
     * @param ds
     * @param mentalretardationtestdto
     */
    public int MRMalinsinsertDataAU(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        CallableStatement cstmt1 = null;
        Connection con = null;
        int insert = 0;
        //Statement stmt = null;
        PreparedStatement pstmt =null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        String code = mentalretardationtestdto.getPersoncode();
        try {

            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, mentalretardationtestdto.getPersoncode());
            con = DBConnection.getConnection();
            con.setAutoCommit(false);


            //stmt = con.createStatement();
            pstmt = con.prepareStatement("select * from tblPerson_MR_MISIC_Test_Details where Person_Code=? and status='Active'");
            
            pstmt.setString(1, mentalretardationtestdto.getPersoncode());
            rs = pstmt.executeQuery();
            if (!rs.next()) {

                cstmt1 = con.prepareCall("{Call SP_tblPerson_MR_MISIC_Test_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt1.setString(1, mentalretardationtestdto.getPersoncode());
                cstmt1.setString(2, mentalretardationtestdto.getMisicinformationraw());

                cstmt1.setString(3, mentalretardationtestdto.getMisicinformationtq());
                cstmt1.setString(4, mentalretardationtestdto.getMisiccomprehensionrawscore());
                cstmt1.setString(5, mentalretardationtestdto.getMisiccomprehensiontq());
                cstmt1.setString(6, mentalretardationtestdto.getMisicarithmeticrawscore());
                cstmt1.setString(7, mentalretardationtestdto.getMisicarithmetictq());
                cstmt1.setString(8, mentalretardationtestdto.getMisicsimilaritiesrawscore());
                cstmt1.setString(9, mentalretardationtestdto.getMisicsimilaritiestq());
                cstmt1.setString(10, mentalretardationtestdto.getMisicvocabularyrawscore());
                cstmt1.setString(11, mentalretardationtestdto.getMisicvocabularytq());
                cstmt1.setString(12, mentalretardationtestdto.getMisicdigitspanrawscore());
                cstmt1.setString(13, mentalretardationtestdto.getMisicdigitspantq());
                cstmt1.setString(14, mentalretardationtestdto.getMisicpcrawscore());
                cstmt1.setString(15, mentalretardationtestdto.getMisicpctq());
                cstmt1.setString(16, mentalretardationtestdto.getMisicbdrawscore());
                cstmt1.setString(17, mentalretardationtestdto.getMisicbdtq());
                cstmt1.setString(18, mentalretardationtestdto.getMisicoarawscore());
                cstmt1.setString(19, mentalretardationtestdto.getMisicoatq());
                cstmt1.setString(20, mentalretardationtestdto.getMisiccodingrawscore());
                cstmt1.setString(21, mentalretardationtestdto.getMisiccodingtq());
                cstmt1.setString(22, mentalretardationtestdto.getMisicmazesrawscore());
                cstmt1.setString(23, mentalretardationtestdto.getMisicmazestq());
                cstmt1.setDouble(24, mentalretardationtestdto.getMisiciq());
                cstmt1.setString(25, mentalretardationtestdto.getLoginid());
                cstmt1.setString(26, mentalretardationtestdto.getSystemip());

                cstmt1.setInt(27, Integer.parseInt(details[0].toString()));
                cstmt1.setInt(28, Integer.parseInt(details[1].toString()));

                insert = cstmt1.executeUpdate();
                cstmt1.close();
                con.commit();
                con.setAutoCommit(true);
            } else {
                insert = -1;
            }
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in inserting MRMalins Details", code);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "MRMalinsinsertDataAU", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "MRMalinsinsertDataAU");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt1);
            DBConnection.closeStatement(pstmt);


        }
        return insert;

    }

    /**
     * this method is used to select .
     * @param datasource
     * @param personcode
     * @return mentalretardatiotestdto
     */
    public MentalRetardationTestsDTO getMRmalinstestDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        CallableStatement cstmt = null;
        Connection con = null;
        MentalRetardationTestsDTO mentalretardationtestdto = new MentalRetardationTestsDTO();
        String str = null;

        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_tblPerson_MR_MISIC_Test_Details_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                mentalretardationtestdto.setMisicinformationraw(rs.getString("MISIC_Information_RawScore"));


                mentalretardationtestdto.setMisicinformationtq(rs.getString("MISIC_Information_TQ"));
                mentalretardationtestdto.setMisiccomprehensionrawscore(rs.getString("MISIC_Comprehension_RawScore"));
                mentalretardationtestdto.setMisiccomprehensiontq(rs.getString("MISIC_Comprehension_TQ"));
                mentalretardationtestdto.setMisicarithmeticrawscore(rs.getString("MISIC_Arithematic_RawScore"));
                mentalretardationtestdto.setMisicarithmetictq(rs.getString("MISIC_Arithematic_TQ"));
                mentalretardationtestdto.setMisicsimilaritiesrawscore(rs.getString("MISIC_Simillarities_RawScore"));
                mentalretardationtestdto.setMisicsimilaritiestq(rs.getString("MISIC_Simillarities_TQ"));
                mentalretardationtestdto.setMisicvocabularyrawscore(rs.getString("MISIC_Vocabulary_RawScore"));
                mentalretardationtestdto.setMisicvocabularytq(rs.getString("MISIC_Vocabulary_TQ"));
                mentalretardationtestdto.setMisicdigitspanrawscore(rs.getString("MISIC_DigitSpan_RawScore"));
                mentalretardationtestdto.setMisicdigitspantq(rs.getString("MISIC_DigitSpan_TQ"));
                mentalretardationtestdto.setMisicpcrawscore(rs.getString("MISIC_PC_RawScore"));
                mentalretardationtestdto.setMisicpctq(rs.getString("MISIC_PC_TQ"));
                mentalretardationtestdto.setMisicbdrawscore(rs.getString("MISIC_BD_RawScore"));
                mentalretardationtestdto.setMisicbdtq(rs.getString("MISIC_BD_TQ"));
                mentalretardationtestdto.setMisicoarawscore(rs.getString("MISIC_OA_RawScore"));
                mentalretardationtestdto.setMisicoatq(rs.getString("MISIC_OA_TQ"));
                mentalretardationtestdto.setMisiccodingrawscore(rs.getString("MISIC_Coding_RawScore"));
                mentalretardationtestdto.setMisiccodingtq(rs.getString("MISIC_Coding_TQ"));
                mentalretardationtestdto.setMisicmazesrawscore(rs.getString("MISIC_Mazes_RawScore"));
                mentalretardationtestdto.setMisicmazestq(rs.getString("MISIC_Mazes_TQ"));
                mentalretardationtestdto.setMisiciq(rs.getDouble("MISIC_IQ"));




            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getMRmalinstestDetails", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getMRmalinstestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getMRmalinstestDetails", "MentalRetardationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getMRmalinstestDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return mentalretardationtestdto;
    }

    /**
     * this method is used to update the data .
     * @param ds
     * @param mentalretardationtestdto
     */
    public void updateMRSfbDetails(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {



        ResultSet rs = null;
        CallableStatement cstmt1 = null;
        Connection con = null;
        int insert = 0;

        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        String code = mentalretardationtestdto.getPersoncode();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, mentalretardationtestdto.getPersoncode());
            con = DBConnection.getConnection();
            con.setAutoCommit(false);



            cstmt1 = con.prepareCall("{Call SP_tblPerson_MR_SFB_Test_Details_Update(?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt1.setString(1, mentalretardationtestdto.getPersoncode());
            cstmt1.setString(2, mentalretardationtestdto.getSfbtrialone());
            cstmt1.setString(3, mentalretardationtestdto.getSfbtrialtwo());
            cstmt1.setString(4, mentalretardationtestdto.getSfbtrialthree());
            cstmt1.setString(5, mentalretardationtestdto.getSfbyear());
            cstmt1.setString(6, mentalretardationtestdto.getSfbmonth());
            cstmt1.setDouble(7, mentalretardationtestdto.getSfbiq());
            cstmt1.setString(8, mentalretardationtestdto.getLoginid());
            cstmt1.setString(9, mentalretardationtestdto.getSystemip());


            cstmt1.setInt(10, Integer.parseInt(details[0].toString()));
            cstmt1.setInt(11, Integer.parseInt(details[1].toString()));

            cstmt1.executeUpdate();
            cstmt1.close();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in Updating MRSFB Details", code);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "updateMRSfbDetails", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "updateMRSfbDetails");



        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt1);


        }


    }

    /**
     * this method is used to update the data .
     * @param ds
     * @param mentalretardationtestdto
     */
    public void updateMalinstestDetails(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        CallableStatement cstmt1 = null;
        Statement stmt = null;
        Connection con = null;
        int insert = 0;
        String query = null;
        String code = mentalretardationtestdto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();

        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, mentalretardationtestdto.getPersoncode());
            con = DBConnection.getConnection();
            con.setAutoCommit(false);




            if (mentalretardationtestdto.getMisiciq() == 0) {
//                query="delete from tblPerson_MR_MISIC_Test_Details where Person_Code='"+mentalretardationtestdto.getPersoncode()+"'";
//                stmt=con.createStatement();
//                int t=stmt.executeUpdate(query);
                CommonDAO.changeStatusToInactiveForResetInUpdate(ds, mentalretardationtestdto.getPersoncode(), "tblPerson_MR_MISIC_Test_Details");
            }
            cstmt1 = con.prepareCall("{Call SP_tblPerson_MR_MISIC_Test_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt1.setString(1, mentalretardationtestdto.getPersoncode());
            cstmt1.setString(2, mentalretardationtestdto.getMisicinformationraw());
            cstmt1.setString(3, mentalretardationtestdto.getMisicinformationtq());
            cstmt1.setString(4, mentalretardationtestdto.getMisiccomprehensionrawscore());

            cstmt1.setString(5, mentalretardationtestdto.getMisiccomprehensiontq());
            cstmt1.setString(6, mentalretardationtestdto.getMisicarithmeticrawscore());
            cstmt1.setString(7, mentalretardationtestdto.getMisicarithmetictq());
            cstmt1.setString(8, mentalretardationtestdto.getMisicsimilaritiesrawscore());
            cstmt1.setString(9, mentalretardationtestdto.getMisicsimilaritiestq());
            cstmt1.setString(10, mentalretardationtestdto.getMisicvocabularyrawscore());
            cstmt1.setString(11, mentalretardationtestdto.getMisicvocabularytq());
            cstmt1.setString(12, mentalretardationtestdto.getMisicdigitspanrawscore());
            cstmt1.setString(13, mentalretardationtestdto.getMisicdigitspantq());
            cstmt1.setString(14, mentalretardationtestdto.getMisicpcrawscore());
            cstmt1.setString(15, mentalretardationtestdto.getMisicpctq());
            cstmt1.setString(16, mentalretardationtestdto.getMisicbdrawscore());
            cstmt1.setString(17, mentalretardationtestdto.getMisicbdtq());
            cstmt1.setString(18, mentalretardationtestdto.getMisicoarawscore());
            cstmt1.setString(19, mentalretardationtestdto.getMisicoatq());
            cstmt1.setString(20, mentalretardationtestdto.getMisiccodingrawscore());
            cstmt1.setString(21, mentalretardationtestdto.getMisiccodingtq());
            cstmt1.setString(22, mentalretardationtestdto.getMisicmazesrawscore());
            cstmt1.setString(23, mentalretardationtestdto.getMisicmazestq());
            cstmt1.setDouble(24, mentalretardationtestdto.getMisiciq());
            cstmt1.setString(25, mentalretardationtestdto.getLoginid());
            cstmt1.setString(26, mentalretardationtestdto.getSystemip());

            cstmt1.setInt(27, Integer.parseInt(details[0].toString()));
            cstmt1.setInt(28, Integer.parseInt(details[1].toString()));

            cstmt1.executeUpdate();
            cstmt1.close();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in Updating Mental Illness Details", code);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "updateMalinstestDetails", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "updateMalinstestDetails");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(cstmt1);


        }


    }

    /**
     * this method is used for insert into SP_tblPerson_MR_DST_Test_Details_Insert
     * @param ds
     * @param personcode
     * @param year
     * @param month
     * @param developmentalresult
     * @param loginid
     * @param systemip
     */
    public int insertDevelopmentalData(DataSource ds, String personcode, String year, String month, double developmentalresult, String loginid, String systemip, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int developmentaldata = 0;
        ResultSet rs = null;
        CallableStatement cstmt1 = null;
        Connection con = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, personcode);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);



            cstmt1 = con.prepareCall("{Call SP_tblPerson_MR_DST_Test_Details_Insert(?,?,?,?,?,?,?,?)}");
            cstmt1.setString(1, personcode);
            cstmt1.setInt(2, (Integer.parseInt(year)));
            cstmt1.setDouble(3, (Double.parseDouble(month)));
            cstmt1.setDouble(4, developmentalresult);
            cstmt1.setString(5, loginid);
            cstmt1.setString(6, systemip);

            cstmt1.setInt(7, Integer.parseInt(details[0].toString()));
            cstmt1.setInt(8, Integer.parseInt(details[1].toString()));

            developmentaldata = cstmt1.executeUpdate();
            cstmt1.close();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in Inserting Developmental Details", personcode);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "insertDevelopmentalData", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "insertDevelopmentalData");



        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt1);


        }
        return developmentaldata;
    }

    /**
     * this method is used for insert into SP_tblPerson_MR_DST_Test_Details_Insert
     * @param ds
     * @param personcode
     * @param year
     * @param month
     * @param developmentalresult
     * @param loginid
     * @param systemip
     */
    public int insertDevelopmentalDataAU(DataSource ds, String personcode, String year, String month, double developmentalresult, String loginid, String systemip, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int developmentaldata = 0;
        ResultSet rs = null;
        CallableStatement cstmt1 = null;
        Connection con = null;
       // Statement stmt = null;
        PreparedStatement pstmt =null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        int i = 0;
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, personcode);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);



           //  stmt = con.createStatement();
            
            pstmt = con.prepareStatement("select * from tblPerson_MR_DST_Test_Details where Person_Code=? and status='Active'");
            
            pstmt.setString(1,personcode);
            
            rs = pstmt.executeQuery();
            if (!rs.next()) {


                cstmt1 = con.prepareCall("{Call SP_tblPerson_MR_DST_Test_Details_Insert(?,?,?,?,?,?)}");
                cstmt1.setString(1, personcode);
                cstmt1.setInt(2, (Integer.parseInt(year)));
                cstmt1.setDouble(3, (Double.parseDouble(month)));
                cstmt1.setDouble(4, developmentalresult);
                cstmt1.setString(5, loginid);
                cstmt1.setString(6, systemip);

                cstmt1.setInt(7, Integer.parseInt(details[0].toString()));
                cstmt1.setInt(8, Integer.parseInt(details[1].toString()));

                developmentaldata = cstmt1.executeUpdate();
                cstmt1.close();
                con.commit();
                con.setAutoCommit(true);
            } else {
                i = -1;
            }
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in Inserting Developmental Details", personcode);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "insertDevelopmentalDataAU", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "insertDevelopmentalDataAU");



        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt1);
            DBConnection.closeStatement(pstmt);


        }
        return i;
    }

    /**
     * this method is used for insert into SP_tblPerson_MR_VSMS_Test_Details_Insert
     * @param ds
     * @param personcode
     * @param year
     * @param month
     * @param vinelandresult
     * @param loginid
     * @param systemip
     */
    public int insertVinelandData(DataSource ds, String personcode, String year, String month, double vinelandresult, String loginid, String systemip, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int vinelanddata = 0;
        ResultSet rs = null;
        CallableStatement cstmt1 = null;
        Connection con = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, personcode);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);



            cstmt1 = con.prepareCall("{Call SP_tblPerson_MR_VSMS_Test_Details_Insert(?,?,?,?,?,?,?,?)}");
            cstmt1.setString(1, personcode);
            cstmt1.setInt(2, (Integer.parseInt(year)));
            cstmt1.setDouble(3, (Double.parseDouble(month)));
            cstmt1.setDouble(4, vinelandresult);
            cstmt1.setString(5, loginid);
            cstmt1.setString(6, systemip);

            cstmt1.setInt(7, Integer.parseInt(details[0].toString()));
            cstmt1.setInt(8, Integer.parseInt(details[1].toString()));

            vinelanddata = cstmt1.executeUpdate();
            cstmt1.close();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in Inserting Vinela Details", personcode);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "insertVinelandData", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "insertVinelandData");



        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt1);


        }
        return vinelanddata;
    }

    /**
     * this method is used for insert into SP_tblPerson_MR_VSMS_Test_Details_Insert
     * @param ds
     * @param personcode
     * @param year
     * @param month
     * @param vinelandresult
     * @param loginid
     * @param systemip
     */
    public int insertVinelandDataAU(DataSource ds, String personcode, String year, String month, double vinelandresult, String loginid, String systemip, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int vinelanddata = 0;
        ResultSet rs = null;
        CallableStatement cstmt1 = null;
        Connection con = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        //Statement stmt = null;
        PreparedStatement pstmt =null;
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();

        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, personcode);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            //stmt = con.createStatement();
            pstmt = con.prepareStatement("select * from tblPerson_MR_VSMS_Test_Details where Person_Code=? and status='Active'");
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            if (!rs.next()) {


                cstmt1 = con.prepareCall("{Call SP_tblPerson_MR_VSMS_Test_Details_Insert(?,?,?,?,?,?)}");
                cstmt1.setString(1, personcode);
                cstmt1.setInt(2, (Integer.parseInt(year)));
                cstmt1.setDouble(3, (Double.parseDouble(month)));
                cstmt1.setDouble(4, vinelandresult);
                cstmt1.setString(5, loginid);
                cstmt1.setString(6, systemip);

                cstmt1.setInt(7, Integer.parseInt(details[0].toString()));
                cstmt1.setInt(8, Integer.parseInt(details[1].toString()));

                vinelanddata = cstmt1.executeUpdate();
                cstmt1.close();
                con.commit();
                con.setAutoCommit(true);
            } else {
                vinelanddata = -1;
            }
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in Inserting Vinela Details", personcode);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "insertVinelandDataAU", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "insertVinelandDataAU");



        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt1);
            DBConnection.closeStatement(pstmt);


        }
        return vinelanddata;
    }

    /**
     * this method is used for insert into SP_tblPerson_MR_BKT_Test_Details_Insert
     * @param ds
     * @param mentalretardationtestdto
     */
    public int insertBinetKamaldata(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int binetkamaldata = 0;
        ResultSet rs = null;
        CallableStatement cstmt1 = null;
        Connection con = null;

        String code = mentalretardationtestdto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);



            cstmt1 = con.prepareCall("{Call SP_tblPerson_MR_BKT_Test_Details_Insert(?,?,?,?,?,?,?,?,?,?)}");
            cstmt1.setString(1, code);
            cstmt1.setString(2, mentalretardationtestdto.getBictbasalage());
            cstmt1.setString(3, mentalretardationtestdto.getBictterminalage());
            cstmt1.setInt(4, (Integer.parseInt(mentalretardationtestdto.getBictyear())));
            cstmt1.setInt(5, (Integer.parseInt(mentalretardationtestdto.getBictmonth())));
            cstmt1.setDouble(6, mentalretardationtestdto.getBictiq());
            cstmt1.setString(7, mentalretardationtestdto.getLoginid());
            cstmt1.setString(8, mentalretardationtestdto.getSystemip());

            cstmt1.setInt(9, Integer.parseInt(details[0].toString()));
            cstmt1.setInt(10, Integer.parseInt(details[1].toString()));

            binetkamaldata = cstmt1.executeUpdate();
            cstmt1.close();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in Inserting BinetKamal Details", code);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "insertBinetKamaldata", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "insertBinetKamaldata");



        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt1);


        }
        return binetkamaldata;
    }

    /**
     * this method is used for insert into SP_tblPerson_MR_BKT_Test_Details_Insert
     * @param ds
     * @param mentalretardationtestdto
     */
    public int insertBinetKamaldataAU(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto) throws SADAREMDBException, SQLException {
        int binetkamaldata = 0;
        ResultSet rs = null;
        CallableStatement cstmt1 = null;
        Connection con = null;
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        String code = mentalretardationtestdto.getPersoncode();
        try {

            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);


            cstmt1 = con.prepareCall("{Call SP_tblPerson_MR_BKT_Test_Details_Insert(?,?,?,?,?,?,?,?)}");
            cstmt1.setString(1, mentalretardationtestdto.getPersoncode());
            cstmt1.setString(2, mentalretardationtestdto.getBictbasalage());
            cstmt1.setString(3, mentalretardationtestdto.getBictterminalage());
            cstmt1.setInt(4, (Integer.parseInt(mentalretardationtestdto.getBictyear())));
            cstmt1.setInt(5, (Integer.parseInt(mentalretardationtestdto.getBictmonth())));
            cstmt1.setDouble(6, mentalretardationtestdto.getBictiq());
            cstmt1.setString(7, mentalretardationtestdto.getLoginid());
            cstmt1.setString(8, mentalretardationtestdto.getSystemip());


            binetkamaldata = cstmt1.executeUpdate();
            cstmt1.close();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in Inserting BinetKamal Details", code);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "insertBinetKamaldataAU", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "insertBinetKamaldataAU");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt1);


        }
        return binetkamaldata;
    }

    /**
     * this method is used to select .
     * @param ds
     * @param personcode
     * @return mentalretardatiotestdto
     */
    public MentalRetardationTestsDTO getDevelopmentalData(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        MentalRetardationTestsDTO mentalretardatiotestdto = new MentalRetardationTestsDTO();
        ResultSet rs = null;
        CallableStatement cstmt = null;
        Connection con = null;

        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_tblPerson_MR_DST_Test_Details_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                mentalretardatiotestdto.setDstyear((rs.getString(1)));
                mentalretardatiotestdto.setDstmonth(rs.getString(2));
                mentalretardatiotestdto.setDstdq(rs.getDouble(3));
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDevelopmentalData", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getDevelopmentalData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDevelopmentalData", "MentalRetardationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getDevelopmentalData");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return mentalretardatiotestdto;
    }

    /**
     * this method is used to select .
     * @param ds
     * @param personcode
     * @return mentalretardatiotestdto
     */
    public MentalRetardationTestsDTO getVinelandData(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        MentalRetardationTestsDTO mentalretardatiotestdto = new MentalRetardationTestsDTO();
        ResultSet rs = null;
        CallableStatement cstmt = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_tblPerson_MR_VSMS_Test_Details_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                mentalretardatiotestdto.setVsmsyear(rs.getString(1));
                mentalretardatiotestdto.setVsmsmonth(rs.getString(2));
                mentalretardatiotestdto.setVsmsvq(rs.getDouble(3));
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVinelandData", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getVinelandData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVinelandData", "MentalRetardationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getVinelandData");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return mentalretardatiotestdto;
    }

    /**
     * this method is used to select .
     * @param ds
     * @param personcode
     * @return mentalretardatiotestdto
     */
    public MentalRetardationTestsDTO gettBinetKamaldata(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        MentalRetardationTestsDTO mentalretardatiotestdto = new MentalRetardationTestsDTO();
        ResultSet rs = null;
        CallableStatement cstmt = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_tblPerson_MR_BKT_Test_Details_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                mentalretardatiotestdto.setBictbasalage(rs.getString(1));
                mentalretardatiotestdto.setBictterminalage(rs.getString(2));
                mentalretardatiotestdto.setBictyear(rs.getString(3));
                mentalretardatiotestdto.setBictmonth(rs.getString(4));
                mentalretardatiotestdto.setBictiq(rs.getDouble(5));
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "gettBinetKamaldata", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "gettBinetKamaldata");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "gettBinetKamaldata", "MentalRetardationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "gettBinetKamaldata");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }

        return mentalretardatiotestdto;
    }

    /**
     * this method is used for update the data.
     * @param ds
     * @param personcode
     * @param year
     * @param month
     * @param developmentalresult
     * @param loginid
     * @param systemip
     */
    public int updateDevelopmentalData(DataSource ds, String personcode, String year, String month, double developmentalresult, String loginid, String systemip, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int developmentaldata = 0;
        ResultSet rs = null;
        CallableStatement cstmt1 = null;
        Connection con = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, personcode);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);



            cstmt1 = con.prepareCall("{Call SP_tblPerson_MR_DST_Test_Details_Update(?,?,?,?,?,?,?,?)}");
            cstmt1.setString(1, personcode);
            cstmt1.setInt(2, (Integer.parseInt(year)));
            cstmt1.setDouble(3, (Double.parseDouble(month)));
            cstmt1.setDouble(4, developmentalresult);
            cstmt1.setString(5, loginid);
            cstmt1.setString(6, systemip);

            cstmt1.setInt(7, Integer.parseInt(details[0].toString()));
            cstmt1.setInt(8, Integer.parseInt(details[1].toString()));

            developmentaldata = cstmt1.executeUpdate();
            cstmt1.close();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in Updating Developmantal Details", personcode);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "updateDevelopmentalData", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "updateDevelopmentalData");




        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt1);


        }
        return developmentaldata;
    }

    /**
     * this method is used for update the data.
     * @param ds
     * @param personcode
     * @param year
     * @param month
     * @param vinelandresult
     * @param loginid
     * @param systemip
     */
    public int updateVinelandData(DataSource ds, String personcode, String year, String month, double vinelandresult, String loginid, String systemip, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int vinelanddata = 0;
        ResultSet rs = null;
        CallableStatement cstmt1 = null;
        Connection con = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, personcode);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);




            cstmt1 = con.prepareCall("{Call SP_tblPerson_MR_VSMS_Test_Details_Update(?,?,?,?,?,?,?,?)}");
            cstmt1.setString(1, personcode);
            cstmt1.setInt(2, (Integer.parseInt(year)));
            cstmt1.setDouble(3, (Double.parseDouble(month)));
            cstmt1.setDouble(4, vinelandresult);
            cstmt1.setString(5, loginid);
            cstmt1.setString(6, systemip);

            cstmt1.setInt(7, Integer.parseInt(details[0].toString()));
            cstmt1.setInt(8, Integer.parseInt(details[1].toString()));

            vinelanddata = cstmt1.executeUpdate();
            cstmt1.close();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in Updating Vinelan Details", personcode);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "updateVinelandData", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "updateVinelandData");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt1);


        }
        return vinelanddata;
    }

    /**
     * this method is used for update the data.
     * @param ds
     * @param mentalretardationtestdto
     */
    public int updateBinetKamaldata(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int binetkamaldata = 0;
        ResultSet rs = null;
        CallableStatement cstmt1 = null;
        Connection con = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        String code = mentalretardationtestdto.getPersoncode();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, mentalretardationtestdto.getPersoncode());
            con = DBConnection.getConnection();
            con.setAutoCommit(false);



            cstmt1 = con.prepareCall("{Call SP_tblPerson_MR_BKT_Test_Details_Update(?,?,?,?,?,?,?,?,?,?)}");
            cstmt1.setString(1, mentalretardationtestdto.getPersoncode());
            cstmt1.setString(2, mentalretardationtestdto.getBictbasalage());
            cstmt1.setString(3, mentalretardationtestdto.getBictterminalage());
            cstmt1.setInt(4, (Integer.parseInt(mentalretardationtestdto.getBictyear())));
            cstmt1.setInt(5, (Integer.parseInt(mentalretardationtestdto.getBictmonth())));
            cstmt1.setDouble(6, mentalretardationtestdto.getBictiq());
            cstmt1.setString(7, mentalretardationtestdto.getLoginid());
            cstmt1.setString(8, mentalretardationtestdto.getSystemip());

            cstmt1.setInt(9, Integer.parseInt(details[0].toString()));
            cstmt1.setInt(10, Integer.parseInt(details[1].toString()));

            binetkamaldata = cstmt1.executeUpdate();
            cstmt1.close();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in Updating BinetKamal Details", code);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "updateBinetKamaldata", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "updateBinetKamaldata");



        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt1);


        }
        return binetkamaldata;
    }

    /**
     * this method is used for update the data.
     * @param ds
     * @param personcode
     * @param mentalRetardationTestsDTO
     */
    public void updateMRAlexanderTestDetails(DataSource ds, String personcode, MentalRetardationTestsDTO mentalRetardationTestsDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cs = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        String code = mentalRetardationTestsDTO.getPersoncode();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, mentalRetardationTestsDTO.getPersoncode());
            con = DBConnection.getConnection();
            con.setAutoCommit(false);



            cs = con.prepareCall("{Call SP_tblPerson_MR_PAT_Test_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, mentalRetardationTestsDTO.getPersoncode());
            cs.setString(2, mentalRetardationTestsDTO.getPat_Second_One());
            cs.setString(3, mentalRetardationTestsDTO.getPat_Second_Two());
            cs.setString(4, mentalRetardationTestsDTO.getPat_Second_Three());
            cs.setString(5, mentalRetardationTestsDTO.getPat_Second_Four());
            cs.setString(6, mentalRetardationTestsDTO.getPat_Second_Five());
            cs.setString(7, mentalRetardationTestsDTO.getPat_Second_Six());
            cs.setString(8, mentalRetardationTestsDTO.getPat_Second_Seven());
            cs.setString(9, mentalRetardationTestsDTO.getPat_Second_Eight());
            cs.setString(10, mentalRetardationTestsDTO.getPat_Second_Nine());

            cs.setString(11, mentalRetardationTestsDTO.getPat_SA_One());
            cs.setString(12, mentalRetardationTestsDTO.getPat_SA_Two());
            cs.setString(13, mentalRetardationTestsDTO.getPat_SA_Three());
            cs.setString(14, mentalRetardationTestsDTO.getPat_SA_Four());
            cs.setString(15, mentalRetardationTestsDTO.getPat_SA_Five());
            cs.setString(16, mentalRetardationTestsDTO.getPat_SA_Six());
            cs.setString(17, mentalRetardationTestsDTO.getPat_SA_Seven());
            cs.setString(18, mentalRetardationTestsDTO.getPat_SA_Eight());
            cs.setString(19, mentalRetardationTestsDTO.getPat_SA_Nine());

            cs.setString(20, mentalRetardationTestsDTO.getPat_Remarks_One());
            cs.setString(21, mentalRetardationTestsDTO.getPat_Remarks_Two());
            cs.setString(22, mentalRetardationTestsDTO.getPat_Remarks_Three());
            cs.setString(23, mentalRetardationTestsDTO.getPat_Remarks_Four());
            cs.setString(24, mentalRetardationTestsDTO.getPat_Remarks_Five());
            cs.setString(25, mentalRetardationTestsDTO.getPat_Remarks_Six());
            cs.setString(26, mentalRetardationTestsDTO.getPat_Remarks_Seven());
            cs.setString(27, mentalRetardationTestsDTO.getPat_Remarks_Eight());
            cs.setString(28, mentalRetardationTestsDTO.getPat_Remarks_Nine());

            cs.setInt(29, Integer.parseInt(mentalRetardationTestsDTO.getPat_Year()));
            cs.setInt(30, Integer.parseInt(mentalRetardationTestsDTO.getPat_Month()));
            cs.setDouble(31, mentalRetardationTestsDTO.getPat_IQ());

            cs.setString(32, mentalRetardationTestsDTO.getLoginID());
            cs.setString(33, mentalRetardationTestsDTO.getSystemIP());

            cs.setInt(34, Integer.parseInt(details[0].toString()));
            cs.setInt(35, Integer.parseInt(details[1].toString()));

            cs.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in Updating MRAlexander Test Details", code);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "updateMRAlexanderTestDetails", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "updateMRAlexanderTestDetails");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cs);


        }


    }

    /**
     * this method is used to check the person code.
     * @param ds
     * @param personcode
     * @param tableName
     */
    public boolean checkForPersonCode(DataSource ds, String personcode, String tableName) throws SADAREMDBException, SQLException {
        boolean isPersonCodeExist = false;
        Connection con = null;
        ResultSet rs = null;
     //   Statement stmt = null;
        PreparedStatement pstmt = null;

        try {
            con = DBConnection.getConnection();
            
            String query = "select Person_Code from " + tableName + " where Person_Code=? and status='Active'";
            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            //stmt = con.createStatement();
            rs = pstmt.executeQuery();
            if (rs.next() == false) {
                isPersonCodeExist = false;
            } else {
                isPersonCodeExist = true;
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkForPersonCode", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "checkForPersonCode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkForPersonCode", "MentalRetardationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "checkForPersonCode");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return isPersonCodeExist;
    }

    /**
     * this method is used for insert into SP_tblPerson_MR_BBPTI_Test_Details_Insert
     * @param ds
     * @param personcode
     * @param mentalRetardationTestsDTO
     */
    public void insertBhatiaTestDetails(DataSource ds, String personcode, MentalRetardationTestsDTO mentalRetardationTestsDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cs = null;

        String code = mentalRetardationTestsDTO.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);


            cs = con.prepareCall("{Call SP_tblPerson_MR_BBPTI_Test_Details_Insert(?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, code);
            cs.setString(2, mentalRetardationTestsDTO.getBbpti_KOHS_Block_Design_Test());
            cs.setString(3, mentalRetardationTestsDTO.getBbpti_Passalong_Test());
            cs.setString(4, mentalRetardationTestsDTO.getBbpti_PatternDrawing_Test());
            cs.setString(5, mentalRetardationTestsDTO.getBbpti_ImmediateMemory_Test());
            cs.setString(6, mentalRetardationTestsDTO.getBbpti_PictureConstruction_Test());
            cs.setString(7, mentalRetardationTestsDTO.getBbpti_IQ());
            cs.setString(8, mentalRetardationTestsDTO.getLoginID());
            cs.setString(9, mentalRetardationTestsDTO.getSystemIP());

            cs.setInt(10, Integer.parseInt(details[0].toString()));
            cs.setInt(11, Integer.parseInt(details[1].toString()));

            cs.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in inserting BhatiaTest Details", code);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "insertBhatiaTestDetails", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "insertBhatiaTestDetails");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cs);


        }
    }

    /**
     * this method is used for insert into SP_tblPerson_MR_BBPTI_Test_Details_Insert
     * @param ds
     * @param personcode
     * @param mentalRetardationTestsDTO
     */
    public int insertBhatiaTestDetailsAU(DataSource ds, String personcode, MentalRetardationTestsDTO mentalRetardationTestsDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cs = null;
        Statement st = null;
        ResultSet rs = null;
      //  Statement stmt = null;
        PreparedStatement pstmt = null ;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        int i = 0;
        String code = mentalRetardationTestsDTO.getPersoncode();
        try {

            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, mentalRetardationTestsDTO.getPersoncode());

            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            //stmt = con.createStatement(); 
            
       pstmt = con.prepareStatement("select * from tblPerson_MR_BBPTI_Test_Details where Person_Code=? and status='Active'");
       pstmt.setString(1, mentalRetardationTestsDTO.getPersoncode());  
       rs = pstmt.executeQuery();
        if (!rs.next()) {
                cs = con.prepareCall("{Call SP_tblPerson_MR_BBPTI_Test_Details_Insert(?,?,?,?,?,?,?,?,?,?,?)}");

                cs.setString(1, mentalRetardationTestsDTO.getPersoncode());
                cs.setString(2, mentalRetardationTestsDTO.getBbpti_KOHS_Block_Design_Test());
                cs.setString(3, mentalRetardationTestsDTO.getBbpti_Passalong_Test());
                cs.setString(4, mentalRetardationTestsDTO.getBbpti_PatternDrawing_Test());
                cs.setString(5, mentalRetardationTestsDTO.getBbpti_ImmediateMemory_Test());
                cs.setString(6, mentalRetardationTestsDTO.getBbpti_PictureConstruction_Test());
                cs.setString(7, mentalRetardationTestsDTO.getBbpti_IQ());
                cs.setString(8, mentalRetardationTestsDTO.getLoginID());
                cs.setString(9, mentalRetardationTestsDTO.getSystemIP());

                cs.setInt(10, Integer.parseInt(details[0].toString()));
                cs.setInt(11, Integer.parseInt(details[1].toString()));

                cs.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            } else {
                i = -1;
            }
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in inserting BhatiaTest Details", code);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "insertBhatiaTestDetailsAU", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "insertBhatiaTestDetailsAU");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cs);

            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(st);

        }
        return i;
    }

    /**
     * this method is used to select .
     * @param ds
     * @param personcode
     * @return mentalRetardationTestsDTO
     */
    public MentalRetardationTestsDTO getBhatiaTestDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        CallableStatement cstmt = null;
        Connection con = null;
        MentalRetardationTestsDTO mentalRetardationTestsDTO = new MentalRetardationTestsDTO();
        String str = null;


        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_tblPerson_MR_BBPTI_Test_Details_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                mentalRetardationTestsDTO.setBbpti_KOHS_Block_Design_Test(rs.getString(1));
                mentalRetardationTestsDTO.setBbpti_Passalong_Test(rs.getString(2));
                mentalRetardationTestsDTO.setBbpti_PatternDrawing_Test(rs.getString(3));
                mentalRetardationTestsDTO.setBbpti_ImmediateMemory_Test(rs.getString(4));
                mentalRetardationTestsDTO.setBbpti_PictureConstruction_Test(rs.getString(5));
                mentalRetardationTestsDTO.setBbpti_IQ(rs.getString(6));

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getBhatiaTestDetails", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getBhatiaTestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getBhatiaTestDetails", "MentalRetardationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getBhatiaTestDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return mentalRetardationTestsDTO;
    }

    /**
     * this method is used to update the data .
     * @param ds
     * @param personcode
     * @param mentalRetardationTestsDTO
     */
    public void updateBhatiaTestDetails(DataSource ds, String personcode, MentalRetardationTestsDTO mentalRetardationTestsDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cs = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        String code = mentalRetardationTestsDTO.getPersoncode();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, mentalRetardationTestsDTO.getPersoncode());
            con = DBConnection.getConnection();
            con.setAutoCommit(false);



            cs = con.prepareCall("{Call SP_tblPerson_MR_BBPTI_Test_Details_Update(?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, mentalRetardationTestsDTO.getPersoncode());
            cs.setString(2, mentalRetardationTestsDTO.getBbpti_KOHS_Block_Design_Test());
            cs.setString(3, mentalRetardationTestsDTO.getBbpti_Passalong_Test());
            cs.setString(4, mentalRetardationTestsDTO.getBbpti_PatternDrawing_Test());
            cs.setString(5, mentalRetardationTestsDTO.getBbpti_ImmediateMemory_Test());
            cs.setString(6, mentalRetardationTestsDTO.getBbpti_PictureConstruction_Test());
            cs.setString(7, mentalRetardationTestsDTO.getBbpti_IQ());
            cs.setString(8, mentalRetardationTestsDTO.getLoginID());
            cs.setString(9, mentalRetardationTestsDTO.getSystemIP());

            cs.setInt(10, Integer.parseInt(details[0].toString()));
            cs.setInt(11, Integer.parseInt(details[1].toString()));


            cs.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in Updating BhatiaTest Details", code);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "updateBhatiaTestDetails", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "updateBhatiaTestDetails");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cs);


        }
    }

    /**
     * this method is used to select .
     * @param ds
     * @param personcode
     * @return mentalRetardationTestsDTO
     */
    public MentalRetardationTestsDTO getMentalRetardationIQValues(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        MentalRetardationTestsDTO mentalretardatiotestdto = new MentalRetardationTestsDTO();
        ResultSet rs1 = null, rs2 = null, rs3 = null, rs4 = null, rs5 = null, rs6 = null, rs7 = null;
        PreparedStatement stmt1 = null, stmt2 = null, stmt3 = null, stmt4 = null, stmt5 = null, stmt6 = null, stmt7 = null;
        Connection con = null;
        double zero = 0;
        try {
            con = DBConnection.getConnection();
            String query1 = "select B.BBPTI_IQ from tblPerson_MR_BBPTI_Test_Details B where B.Person_Code=? and Status='Active'";
            stmt1 = con.prepareStatement(query1);
            
            stmt1.setString(1, personcode);
            
            rs1 = stmt1.executeQuery();
            if (rs1.next()) {
                mentalretardatiotestdto.setBbpti_IQ(rs1.getString(1));
            } else {
                mentalretardatiotestdto.setBbpti_IQ("0");
            }
            String query2 = "select BK.BKT_IQ from tblPerson_MR_BKT_Test_Details BK where BK.Person_Code=? and Status='Active'";
            stmt2 = con.prepareStatement(query2);            
            stmt2.setString(1, personcode);
            
            rs2 = stmt2.executeQuery();
            if (rs2.next()) {
                mentalretardatiotestdto.setBictiq(rs2.getDouble(1));
            } else {
                mentalretardatiotestdto.setBictiq(zero);
            }
            String query3 = "select D.DST_DQ from tblPerson_MR_DST_Test_Details D where D.Person_Code=? and Status='Active'";
           // stmt3 = con.createStatement();
            stmt3 = con.prepareStatement(query3);            
            stmt3.setString(1, personcode);
            
            rs3 = stmt3.executeQuery();
            if (rs3.next()) {
                mentalretardatiotestdto.setDstdq(rs3.getDouble(1));
            } else {
                mentalretardatiotestdto.setDstdq(zero);
            }
            String query4 = "select M.MISIC_IQ from tblPerson_MR_MISIC_Test_Details M where M.Person_Code=? and Status='Active'";
            //stmt4 = con.createStatement();
            stmt4 = con.prepareStatement(query4);            
            stmt4.setString(1, personcode);
            
            rs4 = stmt4.executeQuery();
            if (rs4.next()) {
                mentalretardatiotestdto.setMisiciq(rs4.getDouble(1));
            } else {
                mentalretardatiotestdto.setMisiciq(zero);
            }
            String query5 = "select P.PAT_IQ from tblPerson_MR_PAT_Test_Details P where P.Person_Code=? and Status='Active'";
           // stmt5 = con.createStatement();
            stmt5 = con.prepareStatement(query5);            
            stmt5.setString(1, personcode);
            
            rs5 = stmt5.executeQuery();
            if (rs5.next()) {
                mentalretardatiotestdto.setPat_IQ(rs5.getDouble(1));
            } else {
                mentalretardatiotestdto.setPat_IQ(zero);
            }
            String query6 = "select S.SFB_IQ from tblPerson_MR_SFB_Test_Details S where S.Person_Code=? and Status='Active'";
           // stmt6 = con.createStatement();
            stmt6 = con.prepareStatement(query6);            
            stmt6.setString(1, personcode);
            
            rs6 = stmt6.executeQuery();
            if (rs6.next()) {
                mentalretardatiotestdto.setSfbiq(rs6.getDouble(1));
            } else {
                mentalretardatiotestdto.setSfbiq(zero);
            }
            String query7 = "select V.VSMS_SQ from tblPerson_MR_VSMS_Test_Details V where V.Person_Code=? and Status='Active'";
           // stmt7 = con.createStatement();
            stmt7 = con.prepareStatement(query7);            
            stmt7.setString(1, personcode);
            
            rs7 = stmt7.executeQuery();
            if (rs7.next()) {
                mentalretardatiotestdto.setVsmsvq(rs7.getDouble(1));
            } else {
                mentalretardatiotestdto.setVsmsvq(zero);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMentalRetardationIQValues", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getMentalRetardationIQValues");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMentalRetardationIQValues", "MentalRetardationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getMentalRetardationIQValues");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs1);
            DBConnection.closeResultSet(rs2);
            DBConnection.closeResultSet(rs3);
            DBConnection.closeResultSet(rs4);
            DBConnection.closeResultSet(rs5);
            DBConnection.closeResultSet(rs6);
            DBConnection.closeResultSet(rs7);
            DBConnection.closeStatement(stmt1);
            DBConnection.closeStatement(stmt2);
            DBConnection.closeStatement(stmt3);
            DBConnection.closeStatement(stmt4);
            DBConnection.closeStatement(stmt5);
            DBConnection.closeStatement(stmt6);
            DBConnection.closeStatement(stmt7);

        }
        return mentalretardatiotestdto;
    }

    /**
     * this method is used to select .
     * @param ds
     * @param personcode
     * @return modifydto
     */
    public MentalRetardationDTO getTestDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        MentalRetardationDTO modifydto = new MentalRetardationDTO();
        Connection con = null;
        ResultSet rs = null;
      //  Statement stmt = null;
        PreparedStatement pstmt = null;
        
        try {
            con = DBConnection.getConnection();
            String query = "select Mental_Age,Chronological_Age from dbo.tblMental_Retardation_Details where  Person_Code=? and status='Active'";
         //   stmt = con.createStatement();
            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                modifydto.setMentalage(rs.getString(1));
                modifydto.setChronologicalage(rs.getString(2));
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTestDetails", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getTestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTestDetails", "MentalRetardationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getTestDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return modifydto;
    }

    /**
     * this method is used to select .
     * @param ds
     * @param personcode
     * @return modifydto
     */
    public MentalRetardationDTO getTestDetailsAU(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        MentalRetardationDTO modifydto = new MentalRetardationDTO();
        Connection con = null;
        ResultSet rs = null;
      //  Statement stmt = null;
        PreparedStatement pstmt =null;
        try {
            con = DBConnection.getConnection();
            String query = "select Mental_Age,Chronological_Age from dbo.tblMental_Retardation_Details where  Person_Code=? and status='Active'";
           // stmt = con.createStatement();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,personcode);
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                modifydto.setMentalage(rs.getString(1));
                modifydto.setChronologicalage(rs.getString(2));
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTestDetailsAU", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getTestDetailsAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTestDetailsAU", "MentalRetardationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getTestDetailsAU");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return modifydto;

    }

    /**
     * this method is used for insert into SP_tblPerson_MR_BKT_Detailed_Test_Details_Insert
     * @param ds
     * @param mRBKDTestDTO
     */
    public void insertMRBinetKamatDetailedTestDetails(DataSource ds, MRBinetKamatDetailedTestDTO mRBKDTestDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cs = null;
        String code = mRBKDTestDTO.getPersoncode();
        HttpSession session = request.getSession();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, mRBKDTestDTO.getPersoncode());
            con = DBConnection.getConnection();
            con.setAutoCommit(false);



            cs = con.prepareCall("{Call SP_tblPerson_MR_BKT_Detailed_Test_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, code);
            cs.setInt(2, mRBKDTestDTO.getAgeLevel3_Item1());
            cs.setInt(3, mRBKDTestDTO.getAgeLevel3_Item2());
            cs.setInt(4, mRBKDTestDTO.getAgeLevel3_Item3());
            cs.setInt(5, mRBKDTestDTO.getAgeLevel3_Item4());
            cs.setInt(6, mRBKDTestDTO.getAgeLevel3_Item5());
            cs.setInt(7, mRBKDTestDTO.getAgeLevel3_Item6());
            cs.setInt(8, mRBKDTestDTO.getAgeLevel4_Item1());
            cs.setInt(9, mRBKDTestDTO.getAgeLevel4_Item2());
            cs.setInt(10, mRBKDTestDTO.getAgeLevel4_Item3());
            cs.setInt(11, mRBKDTestDTO.getAgeLevel4_Item4());
            cs.setInt(12, mRBKDTestDTO.getAgeLevel4_Item5());
            cs.setInt(13, mRBKDTestDTO.getAgeLevel4_Item6());
            cs.setInt(14, mRBKDTestDTO.getAgeLevel5_Item1());
            cs.setInt(15, mRBKDTestDTO.getAgeLevel5_Item2());
            cs.setInt(16, mRBKDTestDTO.getAgeLevel5_Item3());
            cs.setInt(17, mRBKDTestDTO.getAgeLevel5_Item4());
            cs.setInt(18, mRBKDTestDTO.getAgeLevel5_Item5());
            cs.setInt(19, mRBKDTestDTO.getAgeLevel5_Item6());
            cs.setInt(20, mRBKDTestDTO.getAgeLevel6_Item1());
            cs.setInt(21, mRBKDTestDTO.getAgeLevel6_Item2());
            cs.setInt(22, mRBKDTestDTO.getAgeLevel6_Item3());
            cs.setInt(23, mRBKDTestDTO.getAgeLevel6_Item4());
            cs.setInt(24, mRBKDTestDTO.getAgeLevel6_Item5());
            cs.setInt(25, mRBKDTestDTO.getAgeLevel6_Item6());
            cs.setInt(26, mRBKDTestDTO.getAgeLevel7_Item1());
            cs.setInt(27, mRBKDTestDTO.getAgeLevel7_Item2());
            cs.setInt(28, mRBKDTestDTO.getAgeLevel7_Item3());
            cs.setInt(29, mRBKDTestDTO.getAgeLevel7_Item4());
            cs.setInt(30, mRBKDTestDTO.getAgeLevel7_Item5());
            cs.setInt(31, mRBKDTestDTO.getAgeLevel7_Item6());
            cs.setInt(32, mRBKDTestDTO.getAgeLevel8_Item1());
            cs.setInt(33, mRBKDTestDTO.getAgeLevel8_Item2());
            cs.setInt(34, mRBKDTestDTO.getAgeLevel8_Item3());
            cs.setInt(35, mRBKDTestDTO.getAgeLevel8_Item4());
            cs.setInt(36, mRBKDTestDTO.getAgeLevel8_Item5());
            cs.setInt(37, mRBKDTestDTO.getAgeLevel8_Item6());
            cs.setInt(38, mRBKDTestDTO.getAgeLevel9_Item1());
            cs.setInt(39, mRBKDTestDTO.getAgeLevel9_Item2());
            cs.setInt(40, mRBKDTestDTO.getAgeLevel9_Item3());
            cs.setInt(41, mRBKDTestDTO.getAgeLevel9_Item4());
            cs.setInt(42, mRBKDTestDTO.getAgeLevel9_Item5());
            cs.setInt(43, mRBKDTestDTO.getAgeLevel9_Item6());
            cs.setInt(44, mRBKDTestDTO.getAgeLevel10_Item1());
            cs.setInt(45, mRBKDTestDTO.getAgeLevel10_Item2());
            cs.setInt(46, mRBKDTestDTO.getAgeLevel10_Item3());
            cs.setInt(47, mRBKDTestDTO.getAgeLevel10_Item4());
            cs.setInt(48, mRBKDTestDTO.getAgeLevel10_Item5());
            cs.setInt(49, mRBKDTestDTO.getAgeLevel10_Item6());
            cs.setInt(50, mRBKDTestDTO.getAgeLevel12_Item1());
            cs.setInt(51, mRBKDTestDTO.getAgeLevel12_Item2());
            cs.setInt(52, mRBKDTestDTO.getAgeLevel12_Item3());
            cs.setInt(53, mRBKDTestDTO.getAgeLevel12_Item4());
            cs.setInt(54, mRBKDTestDTO.getAgeLevel12_Item5());
            cs.setInt(55, mRBKDTestDTO.getAgeLevel12_Item6());
            cs.setInt(56, mRBKDTestDTO.getAgeLevel14_Item1());
            cs.setInt(57, mRBKDTestDTO.getAgeLevel14_Item2());
            cs.setInt(58, mRBKDTestDTO.getAgeLevel14_Item3());
            cs.setInt(59, mRBKDTestDTO.getAgeLevel14_Item4());
            cs.setInt(60, mRBKDTestDTO.getAgeLevel14_Item5());
            cs.setInt(61, mRBKDTestDTO.getAgeLevel14_Item6());
            cs.setInt(62, mRBKDTestDTO.getAgeLevel16_Item1());
            cs.setInt(63, mRBKDTestDTO.getAgeLevel16_Item2());
            cs.setInt(64, mRBKDTestDTO.getAgeLevel16_Item3());
            cs.setInt(65, mRBKDTestDTO.getAgeLevel16_Item4());
            cs.setInt(66, mRBKDTestDTO.getAgeLevel16_Item5());
            cs.setInt(67, mRBKDTestDTO.getAgeLevel16_Item6());
            cs.setInt(68, mRBKDTestDTO.getAgeLevel19_Item1());
            cs.setInt(69, mRBKDTestDTO.getAgeLevel19_Item2());
            cs.setInt(70, mRBKDTestDTO.getAgeLevel19_Item3());
            cs.setInt(71, mRBKDTestDTO.getAgeLevel19_Item4());
            cs.setInt(72, mRBKDTestDTO.getAgeLevel19_Item5());
            cs.setInt(73, mRBKDTestDTO.getAgeLevel19_Item6());
            cs.setInt(74, mRBKDTestDTO.getAgeLevel22_Item1());
            cs.setInt(75, mRBKDTestDTO.getAgeLevel22_Item2());
            cs.setInt(76, mRBKDTestDTO.getAgeLevel22_Item3());
            cs.setInt(77, mRBKDTestDTO.getAgeLevel22_Item4());
            cs.setInt(78, mRBKDTestDTO.getAgeLevel22_Item5());
            cs.setInt(79, mRBKDTestDTO.getAgeLevel22_Item6());


            cs.setInt(80, mRBKDTestDTO.getAgeLevel3_ItemAlt1());
            cs.setInt(81, mRBKDTestDTO.getAgeLevel3_ItemAlt2());
            cs.setInt(82, mRBKDTestDTO.getAgeLevel3_ItemAlt3());
            cs.setInt(83, mRBKDTestDTO.getAgeLevel4_ItemAlt1());
            cs.setInt(84, mRBKDTestDTO.getAgeLevel4_ItemAlt2());
            cs.setInt(85, mRBKDTestDTO.getAgeLevel4_ItemAlt3());
            cs.setInt(86, mRBKDTestDTO.getAgeLevel5_ItemAlt1());
            cs.setInt(87, mRBKDTestDTO.getAgeLevel5_ItemAlt2());
            cs.setInt(88, mRBKDTestDTO.getAgeLevel5_ItemAlt3());
            cs.setInt(89, mRBKDTestDTO.getAgeLevel6_ItemAlt1());
            cs.setInt(90, mRBKDTestDTO.getAgeLevel6_ItemAlt2());
            cs.setInt(91, mRBKDTestDTO.getAgeLevel6_ItemAlt3());
            cs.setInt(92, mRBKDTestDTO.getAgeLevel7_ItemAlt1());
            cs.setInt(93, mRBKDTestDTO.getAgeLevel7_ItemAlt2());
            cs.setInt(94, mRBKDTestDTO.getAgeLevel7_ItemAlt3());
            cs.setInt(95, mRBKDTestDTO.getAgeLevel8_ItemAlt1());
            cs.setInt(96, mRBKDTestDTO.getAgeLevel8_ItemAlt2());
            cs.setInt(97, mRBKDTestDTO.getAgeLevel8_ItemAlt3());
            cs.setInt(98, mRBKDTestDTO.getAgeLevel9_ItemAlt1());
            cs.setInt(99, mRBKDTestDTO.getAgeLevel9_ItemAlt2());
            cs.setInt(100, mRBKDTestDTO.getAgeLevel9_ItemAlt3());
            cs.setInt(101, mRBKDTestDTO.getAgeLevel10_ItemAlt1());
            cs.setInt(102, mRBKDTestDTO.getAgeLevel10_ItemAlt2());
            cs.setInt(103, mRBKDTestDTO.getAgeLevel10_ItemAlt3());
            cs.setInt(104, mRBKDTestDTO.getAgeLevel12_ItemAlt1());
            cs.setInt(105, mRBKDTestDTO.getAgeLevel12_ItemAlt2());
            cs.setInt(106, mRBKDTestDTO.getAgeLevel12_ItemAlt3());
            cs.setInt(107, mRBKDTestDTO.getAgeLevel14_ItemAlt1());
            cs.setInt(108, mRBKDTestDTO.getAgeLevel14_ItemAlt2());
            cs.setInt(109, mRBKDTestDTO.getAgeLevel14_ItemAlt3());
            cs.setInt(110, mRBKDTestDTO.getAgeLevel16_ItemAlt1());
            cs.setInt(111, mRBKDTestDTO.getAgeLevel16_ItemAlt2());
            cs.setInt(112, mRBKDTestDTO.getAgeLevel16_ItemAlt3());
            cs.setInt(113, mRBKDTestDTO.getAgeLevel19_ItemAlt1());
            cs.setInt(114, mRBKDTestDTO.getAgeLevel19_ItemAlt2());
            cs.setInt(115, mRBKDTestDTO.getAgeLevel19_ItemAlt3());
            cs.setInt(116, mRBKDTestDTO.getAgeLevel22_ItemAlt1());
            cs.setInt(117, mRBKDTestDTO.getAgeLevel22_ItemAlt2());
            cs.setInt(118, mRBKDTestDTO.getAgeLevel22_ItemAlt3());
            cs.setString(119, mRBKDTestDTO.getLoginID());
            cs.setString(120, mRBKDTestDTO.getSystemIP());

            cs.setInt(121, Integer.parseInt(details[0].toString()));
            cs.setInt(122, Integer.parseInt(details[1].toString()));

            cs.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in inserting MRBinet Kamat Details", code);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "insertMRBinetKamatDetailedTestDetails", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "insertMRBinetKamatDetailedTestDetails");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cs);


        }
    }

    /**
     * this method is used for insert into SP_tblPerson_MR_BKT_Detailed_Test_Details_Insert
     * @param ds
     * @param mRBKDTestDTO
     */
    public int insertMRBinetKamatDetailedTestDetailsAU(DataSource ds, MRBinetKamatDetailedTestDTO mRBKDTestDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cs = null;
        //Statement stmt = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        HttpSession session = request.getSession();
        String code = mRBKDTestDTO.getPersoncode();
        int i = 0;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, mRBKDTestDTO.getPersoncode());
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            //stmt = con.createStatement();
            pstmt=con.prepareStatement("select * from tblPerson_MR_BKT_Test_Details where Person_Code=? and status='Active'");
            pstmt.setString(1, mRBKDTestDTO.getPersoncode());
            rs = pstmt.executeQuery();
            if (!rs.next()) {

                cs = con.prepareCall("{Call SP_tblPerson_MR_BKT_Detailed_Test_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

                cs.setString(1, mRBKDTestDTO.getPersoncode());

                cs.setInt(2, mRBKDTestDTO.getAgeLevel3_Item1());
                cs.setInt(3, mRBKDTestDTO.getAgeLevel3_Item2());
                cs.setInt(4, mRBKDTestDTO.getAgeLevel3_Item3());
                cs.setInt(5, mRBKDTestDTO.getAgeLevel3_Item4());
                cs.setInt(6, mRBKDTestDTO.getAgeLevel3_Item5());
                cs.setInt(7, mRBKDTestDTO.getAgeLevel3_Item6());
                cs.setInt(8, mRBKDTestDTO.getAgeLevel4_Item1());
                cs.setInt(9, mRBKDTestDTO.getAgeLevel4_Item2());
                cs.setInt(10, mRBKDTestDTO.getAgeLevel4_Item3());
                cs.setInt(11, mRBKDTestDTO.getAgeLevel4_Item4());
                cs.setInt(12, mRBKDTestDTO.getAgeLevel4_Item5());
                cs.setInt(13, mRBKDTestDTO.getAgeLevel4_Item6());
                cs.setInt(14, mRBKDTestDTO.getAgeLevel5_Item1());
                cs.setInt(15, mRBKDTestDTO.getAgeLevel5_Item2());
                cs.setInt(16, mRBKDTestDTO.getAgeLevel5_Item3());
                cs.setInt(17, mRBKDTestDTO.getAgeLevel5_Item4());
                cs.setInt(18, mRBKDTestDTO.getAgeLevel5_Item5());
                cs.setInt(19, mRBKDTestDTO.getAgeLevel5_Item6());
                cs.setInt(20, mRBKDTestDTO.getAgeLevel6_Item1());
                cs.setInt(21, mRBKDTestDTO.getAgeLevel6_Item2());
                cs.setInt(22, mRBKDTestDTO.getAgeLevel6_Item3());
                cs.setInt(23, mRBKDTestDTO.getAgeLevel6_Item4());
                cs.setInt(24, mRBKDTestDTO.getAgeLevel6_Item5());
                cs.setInt(25, mRBKDTestDTO.getAgeLevel6_Item6());
                cs.setInt(26, mRBKDTestDTO.getAgeLevel7_Item1());
                cs.setInt(27, mRBKDTestDTO.getAgeLevel7_Item2());
                cs.setInt(28, mRBKDTestDTO.getAgeLevel7_Item3());
                cs.setInt(29, mRBKDTestDTO.getAgeLevel7_Item4());
                cs.setInt(30, mRBKDTestDTO.getAgeLevel7_Item5());
                cs.setInt(31, mRBKDTestDTO.getAgeLevel7_Item6());
                cs.setInt(32, mRBKDTestDTO.getAgeLevel8_Item1());
                cs.setInt(33, mRBKDTestDTO.getAgeLevel8_Item2());
                cs.setInt(34, mRBKDTestDTO.getAgeLevel8_Item3());
                cs.setInt(35, mRBKDTestDTO.getAgeLevel8_Item4());
                cs.setInt(36, mRBKDTestDTO.getAgeLevel8_Item5());
                cs.setInt(37, mRBKDTestDTO.getAgeLevel8_Item6());
                cs.setInt(38, mRBKDTestDTO.getAgeLevel9_Item1());
                cs.setInt(39, mRBKDTestDTO.getAgeLevel9_Item2());
                cs.setInt(40, mRBKDTestDTO.getAgeLevel9_Item3());
                cs.setInt(41, mRBKDTestDTO.getAgeLevel9_Item4());
                cs.setInt(42, mRBKDTestDTO.getAgeLevel9_Item5());
                cs.setInt(43, mRBKDTestDTO.getAgeLevel9_Item6());
                cs.setInt(44, mRBKDTestDTO.getAgeLevel10_Item1());
                cs.setInt(45, mRBKDTestDTO.getAgeLevel10_Item2());
                cs.setInt(46, mRBKDTestDTO.getAgeLevel10_Item3());
                cs.setInt(47, mRBKDTestDTO.getAgeLevel10_Item4());
                cs.setInt(48, mRBKDTestDTO.getAgeLevel10_Item5());
                cs.setInt(49, mRBKDTestDTO.getAgeLevel10_Item6());
                cs.setInt(50, mRBKDTestDTO.getAgeLevel12_Item1());
                cs.setInt(51, mRBKDTestDTO.getAgeLevel12_Item2());
                cs.setInt(52, mRBKDTestDTO.getAgeLevel12_Item3());
                cs.setInt(53, mRBKDTestDTO.getAgeLevel12_Item4());
                cs.setInt(54, mRBKDTestDTO.getAgeLevel12_Item5());
                cs.setInt(55, mRBKDTestDTO.getAgeLevel12_Item6());
                cs.setInt(56, mRBKDTestDTO.getAgeLevel14_Item1());
                cs.setInt(57, mRBKDTestDTO.getAgeLevel14_Item2());
                cs.setInt(58, mRBKDTestDTO.getAgeLevel14_Item3());
                cs.setInt(59, mRBKDTestDTO.getAgeLevel14_Item4());
                cs.setInt(60, mRBKDTestDTO.getAgeLevel14_Item5());
                cs.setInt(61, mRBKDTestDTO.getAgeLevel14_Item6());
                cs.setInt(62, mRBKDTestDTO.getAgeLevel16_Item1());
                cs.setInt(63, mRBKDTestDTO.getAgeLevel16_Item2());
                cs.setInt(64, mRBKDTestDTO.getAgeLevel16_Item3());
                cs.setInt(65, mRBKDTestDTO.getAgeLevel16_Item4());
                cs.setInt(66, mRBKDTestDTO.getAgeLevel16_Item5());
                cs.setInt(67, mRBKDTestDTO.getAgeLevel16_Item6());
                cs.setInt(68, mRBKDTestDTO.getAgeLevel19_Item1());
                cs.setInt(69, mRBKDTestDTO.getAgeLevel19_Item2());
                cs.setInt(70, mRBKDTestDTO.getAgeLevel19_Item3());
                cs.setInt(71, mRBKDTestDTO.getAgeLevel19_Item4());
                cs.setInt(72, mRBKDTestDTO.getAgeLevel19_Item5());
                cs.setInt(73, mRBKDTestDTO.getAgeLevel19_Item6());
                cs.setInt(74, mRBKDTestDTO.getAgeLevel22_Item1());
                cs.setInt(75, mRBKDTestDTO.getAgeLevel22_Item2());
                cs.setInt(76, mRBKDTestDTO.getAgeLevel22_Item3());
                cs.setInt(77, mRBKDTestDTO.getAgeLevel22_Item4());
                cs.setInt(78, mRBKDTestDTO.getAgeLevel22_Item5());
                cs.setInt(79, mRBKDTestDTO.getAgeLevel22_Item6());


                cs.setInt(80, mRBKDTestDTO.getAgeLevel3_ItemAlt1());
                cs.setInt(81, mRBKDTestDTO.getAgeLevel3_ItemAlt2());
                cs.setInt(82, mRBKDTestDTO.getAgeLevel3_ItemAlt3());
                cs.setInt(83, mRBKDTestDTO.getAgeLevel4_ItemAlt1());
                cs.setInt(84, mRBKDTestDTO.getAgeLevel4_ItemAlt2());
                cs.setInt(85, mRBKDTestDTO.getAgeLevel4_ItemAlt3());
                cs.setInt(86, mRBKDTestDTO.getAgeLevel5_ItemAlt1());
                cs.setInt(87, mRBKDTestDTO.getAgeLevel5_ItemAlt2());
                cs.setInt(88, mRBKDTestDTO.getAgeLevel5_ItemAlt3());
                cs.setInt(89, mRBKDTestDTO.getAgeLevel6_ItemAlt1());
                cs.setInt(90, mRBKDTestDTO.getAgeLevel6_ItemAlt2());
                cs.setInt(91, mRBKDTestDTO.getAgeLevel6_ItemAlt3());
                cs.setInt(92, mRBKDTestDTO.getAgeLevel7_ItemAlt1());
                cs.setInt(93, mRBKDTestDTO.getAgeLevel7_ItemAlt2());
                cs.setInt(94, mRBKDTestDTO.getAgeLevel7_ItemAlt3());
                cs.setInt(95, mRBKDTestDTO.getAgeLevel8_ItemAlt1());
                cs.setInt(96, mRBKDTestDTO.getAgeLevel8_ItemAlt2());
                cs.setInt(97, mRBKDTestDTO.getAgeLevel8_ItemAlt3());
                cs.setInt(98, mRBKDTestDTO.getAgeLevel9_ItemAlt1());
                cs.setInt(99, mRBKDTestDTO.getAgeLevel9_ItemAlt2());
                cs.setInt(100, mRBKDTestDTO.getAgeLevel9_ItemAlt3());
                cs.setInt(101, mRBKDTestDTO.getAgeLevel10_ItemAlt1());
                cs.setInt(102, mRBKDTestDTO.getAgeLevel10_ItemAlt2());
                cs.setInt(103, mRBKDTestDTO.getAgeLevel10_ItemAlt3());
                cs.setInt(104, mRBKDTestDTO.getAgeLevel12_ItemAlt1());
                cs.setInt(105, mRBKDTestDTO.getAgeLevel12_ItemAlt2());
                cs.setInt(106, mRBKDTestDTO.getAgeLevel12_ItemAlt3());
                cs.setInt(107, mRBKDTestDTO.getAgeLevel14_ItemAlt1());
                cs.setInt(108, mRBKDTestDTO.getAgeLevel14_ItemAlt2());
                cs.setInt(109, mRBKDTestDTO.getAgeLevel14_ItemAlt3());
                cs.setInt(110, mRBKDTestDTO.getAgeLevel16_ItemAlt1());
                cs.setInt(111, mRBKDTestDTO.getAgeLevel16_ItemAlt2());
                cs.setInt(112, mRBKDTestDTO.getAgeLevel16_ItemAlt3());
                cs.setInt(113, mRBKDTestDTO.getAgeLevel19_ItemAlt1());
                cs.setInt(114, mRBKDTestDTO.getAgeLevel19_ItemAlt2());
                cs.setInt(115, mRBKDTestDTO.getAgeLevel19_ItemAlt3());
                cs.setInt(116, mRBKDTestDTO.getAgeLevel22_ItemAlt1());
                cs.setInt(117, mRBKDTestDTO.getAgeLevel22_ItemAlt2());
                cs.setInt(118, mRBKDTestDTO.getAgeLevel22_ItemAlt3());

                cs.setString(119, mRBKDTestDTO.getLoginID());
                cs.setString(120, mRBKDTestDTO.getSystemIP());

                cs.setInt(121, Integer.parseInt(details[0].toString()));
                cs.setInt(122, Integer.parseInt(details[1].toString()));
                i = cs.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            } else {
                i = -1;
            }
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in inserting MRBinet Kamat Details", code);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "insertMRBinetKamatDetailedTestDetailsAU", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "insertMRBinetKamatDetailedTestDetailsAU");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cs);

            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);

        }
        return i;
    }

    /**
     * this method is used to select .
     * @param ds
     * @param personcode
     * @return MRBKDTestDTO
     */
    public MRBinetKamatDetailedTestDTO getMRBinetKamatDetailedTestDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        CallableStatement cstmt = null;
        Connection con = null;
        MRBinetKamatDetailedTestDTO MRBKDTestDTO = new MRBinetKamatDetailedTestDTO();
        String str = null;

        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call tblPerson_MR_BKT_Detailed_Test_Details_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {

                MRBKDTestDTO.setAgeLevel3_Item1(rs.getInt(2));
                MRBKDTestDTO.setAgeLevel3_Item2(rs.getInt(3));
                MRBKDTestDTO.setAgeLevel3_Item3(rs.getInt(4));
                MRBKDTestDTO.setAgeLevel3_Item4(rs.getInt(5));
                MRBKDTestDTO.setAgeLevel3_Item5(rs.getInt(6));
                MRBKDTestDTO.setAgeLevel3_Item6(rs.getInt(7));
                MRBKDTestDTO.setAgeLevel4_Item1(rs.getInt(8));
                MRBKDTestDTO.setAgeLevel4_Item2(rs.getInt(9));
                MRBKDTestDTO.setAgeLevel4_Item3(rs.getInt(10));
                MRBKDTestDTO.setAgeLevel4_Item4(rs.getInt(11));
                MRBKDTestDTO.setAgeLevel4_Item5(rs.getInt(12));
                MRBKDTestDTO.setAgeLevel4_Item6(rs.getInt(13));
                MRBKDTestDTO.setAgeLevel5_Item1(rs.getInt(14));
                MRBKDTestDTO.setAgeLevel5_Item2(rs.getInt(15));
                MRBKDTestDTO.setAgeLevel5_Item3(rs.getInt(16));
                MRBKDTestDTO.setAgeLevel5_Item4(rs.getInt(17));
                MRBKDTestDTO.setAgeLevel5_Item5(rs.getInt(18));
                MRBKDTestDTO.setAgeLevel5_Item6(rs.getInt(19));
                MRBKDTestDTO.setAgeLevel6_Item1(rs.getInt(20));
                MRBKDTestDTO.setAgeLevel6_Item2(rs.getInt(21));
                MRBKDTestDTO.setAgeLevel6_Item3(rs.getInt(22));
                MRBKDTestDTO.setAgeLevel6_Item4(rs.getInt(23));
                MRBKDTestDTO.setAgeLevel6_Item5(rs.getInt(24));
                MRBKDTestDTO.setAgeLevel6_Item6(rs.getInt(25));
                MRBKDTestDTO.setAgeLevel7_Item1(rs.getInt(26));
                MRBKDTestDTO.setAgeLevel7_Item2(rs.getInt(27));
                MRBKDTestDTO.setAgeLevel7_Item3(rs.getInt(28));
                MRBKDTestDTO.setAgeLevel7_Item4(rs.getInt(29));
                MRBKDTestDTO.setAgeLevel7_Item5(rs.getInt(30));
                MRBKDTestDTO.setAgeLevel7_Item6(rs.getInt(31));
                MRBKDTestDTO.setAgeLevel8_Item1(rs.getInt(32));
                MRBKDTestDTO.setAgeLevel8_Item2(rs.getInt(33));
                MRBKDTestDTO.setAgeLevel8_Item3(rs.getInt(34));
                MRBKDTestDTO.setAgeLevel8_Item4(rs.getInt(35));
                MRBKDTestDTO.setAgeLevel8_Item5(rs.getInt(36));
                MRBKDTestDTO.setAgeLevel8_Item6(rs.getInt(37));
                MRBKDTestDTO.setAgeLevel9_Item1(rs.getInt(38));
                MRBKDTestDTO.setAgeLevel9_Item2(rs.getInt(39));
                MRBKDTestDTO.setAgeLevel9_Item3(rs.getInt(40));
                MRBKDTestDTO.setAgeLevel9_Item4(rs.getInt(41));
                MRBKDTestDTO.setAgeLevel9_Item5(rs.getInt(42));
                MRBKDTestDTO.setAgeLevel9_Item6(rs.getInt(43));
                MRBKDTestDTO.setAgeLevel10_Item1(rs.getInt(44));
                MRBKDTestDTO.setAgeLevel10_Item2(rs.getInt(45));
                MRBKDTestDTO.setAgeLevel10_Item3(rs.getInt(46));
                MRBKDTestDTO.setAgeLevel10_Item4(rs.getInt(47));
                MRBKDTestDTO.setAgeLevel10_Item5(rs.getInt(48));
                MRBKDTestDTO.setAgeLevel10_Item6(rs.getInt(49));
                MRBKDTestDTO.setAgeLevel12_Item1(rs.getInt(50));
                MRBKDTestDTO.setAgeLevel12_Item2(rs.getInt(51));
                MRBKDTestDTO.setAgeLevel12_Item3(rs.getInt(52));
                MRBKDTestDTO.setAgeLevel12_Item4(rs.getInt(53));
                MRBKDTestDTO.setAgeLevel12_Item5(rs.getInt(54));
                MRBKDTestDTO.setAgeLevel12_Item6(rs.getInt(55));
                MRBKDTestDTO.setAgeLevel14_Item1(rs.getInt(56));
                MRBKDTestDTO.setAgeLevel14_Item2(rs.getInt(57));
                MRBKDTestDTO.setAgeLevel14_Item3(rs.getInt(58));
                MRBKDTestDTO.setAgeLevel14_Item4(rs.getInt(59));
                MRBKDTestDTO.setAgeLevel14_Item5(rs.getInt(60));
                MRBKDTestDTO.setAgeLevel14_Item6(rs.getInt(61));
                MRBKDTestDTO.setAgeLevel16_Item1(rs.getInt(62));
                MRBKDTestDTO.setAgeLevel16_Item2(rs.getInt(63));
                MRBKDTestDTO.setAgeLevel16_Item3(rs.getInt(64));
                MRBKDTestDTO.setAgeLevel16_Item4(rs.getInt(65));
                MRBKDTestDTO.setAgeLevel16_Item5(rs.getInt(66));
                MRBKDTestDTO.setAgeLevel16_Item6(rs.getInt(67));
                MRBKDTestDTO.setAgeLevel19_Item1(rs.getInt(68));
                MRBKDTestDTO.setAgeLevel19_Item2(rs.getInt(69));
                MRBKDTestDTO.setAgeLevel19_Item3(rs.getInt(70));
                MRBKDTestDTO.setAgeLevel19_Item4(rs.getInt(71));
                MRBKDTestDTO.setAgeLevel19_Item5(rs.getInt(72));
                MRBKDTestDTO.setAgeLevel19_Item6(rs.getInt(73));
                MRBKDTestDTO.setAgeLevel22_Item1(rs.getInt(74));
                MRBKDTestDTO.setAgeLevel22_Item2(rs.getInt(75));
                MRBKDTestDTO.setAgeLevel22_Item3(rs.getInt(76));
                MRBKDTestDTO.setAgeLevel22_Item4(rs.getInt(77));
                MRBKDTestDTO.setAgeLevel22_Item5(rs.getInt(78));
                MRBKDTestDTO.setAgeLevel22_Item6(rs.getInt(79));


                MRBKDTestDTO.setAgeLevel3_ItemAlt1(rs.getInt(80));
                MRBKDTestDTO.setAgeLevel3_ItemAlt2(rs.getInt(81));
                MRBKDTestDTO.setAgeLevel3_ItemAlt3(rs.getInt(82));
                MRBKDTestDTO.setAgeLevel4_ItemAlt1(rs.getInt(83));
                MRBKDTestDTO.setAgeLevel4_ItemAlt2(rs.getInt(84));
                MRBKDTestDTO.setAgeLevel4_ItemAlt3(rs.getInt(85));
                MRBKDTestDTO.setAgeLevel5_ItemAlt1(rs.getInt(86));
                MRBKDTestDTO.setAgeLevel5_ItemAlt2(rs.getInt(87));
                MRBKDTestDTO.setAgeLevel5_ItemAlt3(rs.getInt(88));
                MRBKDTestDTO.setAgeLevel6_ItemAlt1(rs.getInt(89));
                MRBKDTestDTO.setAgeLevel6_ItemAlt2(rs.getInt(90));
                MRBKDTestDTO.setAgeLevel6_ItemAlt3(rs.getInt(91));
                MRBKDTestDTO.setAgeLevel7_ItemAlt1(rs.getInt(92));
                MRBKDTestDTO.setAgeLevel7_ItemAlt2(rs.getInt(93));
                MRBKDTestDTO.setAgeLevel7_ItemAlt3(rs.getInt(94));
                MRBKDTestDTO.setAgeLevel8_ItemAlt1(rs.getInt(95));
                MRBKDTestDTO.setAgeLevel8_ItemAlt2(rs.getInt(96));
                MRBKDTestDTO.setAgeLevel8_ItemAlt3(rs.getInt(97));
                MRBKDTestDTO.setAgeLevel9_ItemAlt1(rs.getInt(98));
                MRBKDTestDTO.setAgeLevel9_ItemAlt2(rs.getInt(99));
                MRBKDTestDTO.setAgeLevel9_ItemAlt3(rs.getInt(100));
                MRBKDTestDTO.setAgeLevel10_ItemAlt1(rs.getInt(101));
                MRBKDTestDTO.setAgeLevel10_ItemAlt2(rs.getInt(102));
                MRBKDTestDTO.setAgeLevel10_ItemAlt3(rs.getInt(103));
                MRBKDTestDTO.setAgeLevel12_ItemAlt1(rs.getInt(104));
                MRBKDTestDTO.setAgeLevel12_ItemAlt2(rs.getInt(105));
                MRBKDTestDTO.setAgeLevel12_ItemAlt3(rs.getInt(106));
                MRBKDTestDTO.setAgeLevel14_ItemAlt1(rs.getInt(107));
                MRBKDTestDTO.setAgeLevel14_ItemAlt2(rs.getInt(108));
                MRBKDTestDTO.setAgeLevel14_ItemAlt3(rs.getInt(109));
                MRBKDTestDTO.setAgeLevel16_ItemAlt1(rs.getInt(110));
                MRBKDTestDTO.setAgeLevel16_ItemAlt2(rs.getInt(111));
                MRBKDTestDTO.setAgeLevel16_ItemAlt3(rs.getInt(112));
                MRBKDTestDTO.setAgeLevel19_ItemAlt1(rs.getInt(113));
                MRBKDTestDTO.setAgeLevel19_ItemAlt2(rs.getInt(114));
                MRBKDTestDTO.setAgeLevel19_ItemAlt3(rs.getInt(115));
                MRBKDTestDTO.setAgeLevel22_ItemAlt1(rs.getInt(116));
                MRBKDTestDTO.setAgeLevel22_ItemAlt2(rs.getInt(117));
                MRBKDTestDTO.setAgeLevel22_ItemAlt3(rs.getInt(118));

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMRBinetKamatDetailedTestDetails", "MentalRetardationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getMRBinetKamatDetailedTestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMRBinetKamatDetailedTestDetails", "MentalRetardationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getMRBinetKamatDetailedTestDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return MRBKDTestDTO;

    }

    /**
     * this method is used to update the data .
     * @param ds
     * @param mRBKDTestDTO
     */
    public void updateMRBinetKamatDetailedTestDetails(DataSource ds, MRBinetKamatDetailedTestDTO mRBKDTestDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cs = null;
        HttpSession session = request.getSession();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        String code = mRBKDTestDTO.getPersoncode();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, mRBKDTestDTO.getPersoncode());
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            cs = con.prepareCall("{Call SP_tblPerson_MR_BKT_Detailed_Test_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, mRBKDTestDTO.getPersoncode());

            cs.setInt(2, mRBKDTestDTO.getAgeLevel3_Item1());
            cs.setInt(3, mRBKDTestDTO.getAgeLevel3_Item2());
            cs.setInt(4, mRBKDTestDTO.getAgeLevel3_Item3());
            cs.setInt(5, mRBKDTestDTO.getAgeLevel3_Item4());
            cs.setInt(6, mRBKDTestDTO.getAgeLevel3_Item5());
            cs.setInt(7, mRBKDTestDTO.getAgeLevel3_Item6());
            cs.setInt(8, mRBKDTestDTO.getAgeLevel4_Item1());
            cs.setInt(9, mRBKDTestDTO.getAgeLevel4_Item2());
            cs.setInt(10, mRBKDTestDTO.getAgeLevel4_Item3());
            cs.setInt(11, mRBKDTestDTO.getAgeLevel4_Item4());
            cs.setInt(12, mRBKDTestDTO.getAgeLevel4_Item5());
            cs.setInt(13, mRBKDTestDTO.getAgeLevel4_Item6());
            cs.setInt(14, mRBKDTestDTO.getAgeLevel5_Item1());
            cs.setInt(15, mRBKDTestDTO.getAgeLevel5_Item2());
            cs.setInt(16, mRBKDTestDTO.getAgeLevel5_Item3());
            cs.setInt(17, mRBKDTestDTO.getAgeLevel5_Item4());
            cs.setInt(18, mRBKDTestDTO.getAgeLevel5_Item5());
            cs.setInt(19, mRBKDTestDTO.getAgeLevel5_Item6());
            cs.setInt(20, mRBKDTestDTO.getAgeLevel6_Item1());
            cs.setInt(21, mRBKDTestDTO.getAgeLevel6_Item2());
            cs.setInt(22, mRBKDTestDTO.getAgeLevel6_Item3());
            cs.setInt(23, mRBKDTestDTO.getAgeLevel6_Item4());
            cs.setInt(24, mRBKDTestDTO.getAgeLevel6_Item5());
            cs.setInt(25, mRBKDTestDTO.getAgeLevel6_Item6());
            cs.setInt(26, mRBKDTestDTO.getAgeLevel7_Item1());
            cs.setInt(27, mRBKDTestDTO.getAgeLevel7_Item2());
            cs.setInt(28, mRBKDTestDTO.getAgeLevel7_Item3());
            cs.setInt(29, mRBKDTestDTO.getAgeLevel7_Item4());
            cs.setInt(30, mRBKDTestDTO.getAgeLevel7_Item5());
            cs.setInt(31, mRBKDTestDTO.getAgeLevel7_Item6());
            cs.setInt(32, mRBKDTestDTO.getAgeLevel8_Item1());
            cs.setInt(33, mRBKDTestDTO.getAgeLevel8_Item2());
            cs.setInt(34, mRBKDTestDTO.getAgeLevel8_Item3());
            cs.setInt(35, mRBKDTestDTO.getAgeLevel8_Item4());
            cs.setInt(36, mRBKDTestDTO.getAgeLevel8_Item5());
            cs.setInt(37, mRBKDTestDTO.getAgeLevel8_Item6());
            cs.setInt(38, mRBKDTestDTO.getAgeLevel9_Item1());
            cs.setInt(39, mRBKDTestDTO.getAgeLevel9_Item2());
            cs.setInt(40, mRBKDTestDTO.getAgeLevel9_Item3());
            cs.setInt(41, mRBKDTestDTO.getAgeLevel9_Item4());
            cs.setInt(42, mRBKDTestDTO.getAgeLevel9_Item5());
            cs.setInt(43, mRBKDTestDTO.getAgeLevel9_Item6());
            cs.setInt(44, mRBKDTestDTO.getAgeLevel10_Item1());
            cs.setInt(45, mRBKDTestDTO.getAgeLevel10_Item2());
            cs.setInt(46, mRBKDTestDTO.getAgeLevel10_Item3());
            cs.setInt(47, mRBKDTestDTO.getAgeLevel10_Item4());
            cs.setInt(48, mRBKDTestDTO.getAgeLevel10_Item5());
            cs.setInt(49, mRBKDTestDTO.getAgeLevel10_Item6());
            cs.setInt(50, mRBKDTestDTO.getAgeLevel12_Item1());
            cs.setInt(51, mRBKDTestDTO.getAgeLevel12_Item2());
            cs.setInt(52, mRBKDTestDTO.getAgeLevel12_Item3());
            cs.setInt(53, mRBKDTestDTO.getAgeLevel12_Item4());
            cs.setInt(54, mRBKDTestDTO.getAgeLevel12_Item5());
            cs.setInt(55, mRBKDTestDTO.getAgeLevel12_Item6());
            cs.setInt(56, mRBKDTestDTO.getAgeLevel14_Item1());
            cs.setInt(57, mRBKDTestDTO.getAgeLevel14_Item2());
            cs.setInt(58, mRBKDTestDTO.getAgeLevel14_Item3());
            cs.setInt(59, mRBKDTestDTO.getAgeLevel14_Item4());
            cs.setInt(60, mRBKDTestDTO.getAgeLevel14_Item5());
            cs.setInt(61, mRBKDTestDTO.getAgeLevel14_Item6());
            cs.setInt(62, mRBKDTestDTO.getAgeLevel16_Item1());
            cs.setInt(63, mRBKDTestDTO.getAgeLevel16_Item2());
            cs.setInt(64, mRBKDTestDTO.getAgeLevel16_Item3());
            cs.setInt(65, mRBKDTestDTO.getAgeLevel16_Item4());
            cs.setInt(66, mRBKDTestDTO.getAgeLevel16_Item5());
            cs.setInt(67, mRBKDTestDTO.getAgeLevel16_Item6());
            cs.setInt(68, mRBKDTestDTO.getAgeLevel19_Item1());
            cs.setInt(69, mRBKDTestDTO.getAgeLevel19_Item2());
            cs.setInt(70, mRBKDTestDTO.getAgeLevel19_Item3());
            cs.setInt(71, mRBKDTestDTO.getAgeLevel19_Item4());
            cs.setInt(72, mRBKDTestDTO.getAgeLevel19_Item5());
            cs.setInt(73, mRBKDTestDTO.getAgeLevel19_Item6());
            cs.setInt(74, mRBKDTestDTO.getAgeLevel22_Item1());
            cs.setInt(75, mRBKDTestDTO.getAgeLevel22_Item2());
            cs.setInt(76, mRBKDTestDTO.getAgeLevel22_Item3());
            cs.setInt(77, mRBKDTestDTO.getAgeLevel22_Item4());
            cs.setInt(78, mRBKDTestDTO.getAgeLevel22_Item5());
            cs.setInt(79, mRBKDTestDTO.getAgeLevel22_Item6());


            cs.setInt(80, mRBKDTestDTO.getAgeLevel3_ItemAlt1());
            cs.setInt(81, mRBKDTestDTO.getAgeLevel3_ItemAlt2());
            cs.setInt(82, mRBKDTestDTO.getAgeLevel3_ItemAlt3());
            cs.setInt(83, mRBKDTestDTO.getAgeLevel4_ItemAlt1());
            cs.setInt(84, mRBKDTestDTO.getAgeLevel4_ItemAlt2());
            cs.setInt(85, mRBKDTestDTO.getAgeLevel4_ItemAlt3());
            cs.setInt(86, mRBKDTestDTO.getAgeLevel5_ItemAlt1());
            cs.setInt(87, mRBKDTestDTO.getAgeLevel5_ItemAlt2());
            cs.setInt(88, mRBKDTestDTO.getAgeLevel5_ItemAlt3());
            cs.setInt(89, mRBKDTestDTO.getAgeLevel6_ItemAlt1());
            cs.setInt(90, mRBKDTestDTO.getAgeLevel6_ItemAlt2());
            cs.setInt(91, mRBKDTestDTO.getAgeLevel6_ItemAlt3());
            cs.setInt(92, mRBKDTestDTO.getAgeLevel7_ItemAlt1());
            cs.setInt(93, mRBKDTestDTO.getAgeLevel7_ItemAlt2());
            cs.setInt(94, mRBKDTestDTO.getAgeLevel7_ItemAlt3());
            cs.setInt(95, mRBKDTestDTO.getAgeLevel8_ItemAlt1());
            cs.setInt(96, mRBKDTestDTO.getAgeLevel8_ItemAlt2());
            cs.setInt(97, mRBKDTestDTO.getAgeLevel8_ItemAlt3());
            cs.setInt(98, mRBKDTestDTO.getAgeLevel9_ItemAlt1());
            cs.setInt(99, mRBKDTestDTO.getAgeLevel9_ItemAlt2());
            cs.setInt(100, mRBKDTestDTO.getAgeLevel9_ItemAlt3());
            cs.setInt(101, mRBKDTestDTO.getAgeLevel10_ItemAlt1());
            cs.setInt(102, mRBKDTestDTO.getAgeLevel10_ItemAlt2());
            cs.setInt(103, mRBKDTestDTO.getAgeLevel10_ItemAlt3());
            cs.setInt(104, mRBKDTestDTO.getAgeLevel12_ItemAlt1());
            cs.setInt(105, mRBKDTestDTO.getAgeLevel12_ItemAlt2());
            cs.setInt(106, mRBKDTestDTO.getAgeLevel12_ItemAlt3());
            cs.setInt(107, mRBKDTestDTO.getAgeLevel14_ItemAlt1());
            cs.setInt(108, mRBKDTestDTO.getAgeLevel14_ItemAlt2());
            cs.setInt(109, mRBKDTestDTO.getAgeLevel14_ItemAlt3());
            cs.setInt(110, mRBKDTestDTO.getAgeLevel16_ItemAlt1());
            cs.setInt(111, mRBKDTestDTO.getAgeLevel16_ItemAlt2());
            cs.setInt(112, mRBKDTestDTO.getAgeLevel16_ItemAlt3());
            cs.setInt(113, mRBKDTestDTO.getAgeLevel19_ItemAlt1());
            cs.setInt(114, mRBKDTestDTO.getAgeLevel19_ItemAlt2());
            cs.setInt(115, mRBKDTestDTO.getAgeLevel19_ItemAlt3());
            cs.setInt(116, mRBKDTestDTO.getAgeLevel22_ItemAlt1());
            cs.setInt(117, mRBKDTestDTO.getAgeLevel22_ItemAlt2());
            cs.setInt(118, mRBKDTestDTO.getAgeLevel22_ItemAlt3());

            cs.setString(119, mRBKDTestDTO.getLoginID());
            cs.setString(120, mRBKDTestDTO.getSystemIP());

            cs.setInt(121, Integer.parseInt(details[0].toString()));
            cs.setInt(122, Integer.parseInt(details[1].toString()));

            cs.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in Updating MRBinet Kamat Details", code);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "updateMRBinetKamatDetailedTestDetails", "MentalRetardationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "updateMRBinetKamatDetailedTestDetails");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cs);


        }
    }

    public ArrayList getMentalItems(DataSource ds, String personCode) throws SADAREMDBException, SQLException {

        ArrayList list = null;
        Connection con = null;
      //  Statement stmt = null;
        PreparedStatement pstmt = null ;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            String query = "select dst_year,dst_dq from tblPerson_MR_DST_Test_Details  where Person_Code=? and status='Active'";
           // stmt = con.createStatement();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personCode);

            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    list = new ArrayList();
                    list.add(rs.getString(1));
                    list.add(rs.getString(2));
                }
            }

        } //end of try block
        catch (Exception e) {
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "getMentalItems", "MentalRetardationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getMentalItems");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }

        return list;
    }

    public ArrayList getMentalItemsVsms(DataSource ds, String personCode) throws SADAREMDBException, SQLException {

        ArrayList list = null;
        Connection con = null;
     //   Statement stmt = null;
        PreparedStatement pstmt =null ;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            String query = "select vsms_year,vsms_sq from tblPerson_MR_VSMS_Test_Details  where Person_Code=? and status='Active'";
           // stmt = con.createStatement();
            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personCode);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    list = new ArrayList();
                    list.add(rs.getString(1));
                    list.add(rs.getString(2));
                }
            }

        } //end of try block
        catch (Exception e) {
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "getMentalItemsVsms", "MentalRetardationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationDAO", "getMentalItemsVsms");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }

        return list;
    }
}











