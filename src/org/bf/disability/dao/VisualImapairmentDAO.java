// This VisualImapairmentDAO is used for CardioPulmonary Deseases, Dwarfism and MentalIllness
package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.dto.CardioPulmonaryDTO;
import org.bf.disability.dto.DwarfismDTO;
import org.bf.disability.dto.MentalIllnessDTO;
import org.bf.disability.service.TransactionService;
import org.bf.disability.servicefactory.TransactionFactory;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is for doing manipulation on visulaimpairment to database
 * @author Kiran .H
 * @version 1.0
 */
public class VisualImapairmentDAO {

    /**
     * 
     * @param datasource 
     * @param personcode 
     * @param cardiopulmonaryvalue 
     * @param Systemip 
     * @param loginid 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public int insertCardioPulmonary(DataSource ds, String personcode, float cardiopulmonaryvalue, String Systemip, String loginid, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i = 0;
        Connection con = null;
        CallableStatement cstmt = null;
        CallableStatement cstmt1 = null;
        ResultSet rs = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();


        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, personcode);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);


            cstmt = con.prepareCall("{Call SP_CPC_Details_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            if (!rs.next()) {
                cstmt1 = con.prepareCall("{Call SP_CPC_Details_Insert(?,?,?,?,?,?)}");
                cstmt1.setString(1, personcode);
                cstmt1.setFloat(2, cardiopulmonaryvalue);
                cstmt1.setString(3, loginid);
                cstmt1.setString(4, Systemip);

                cstmt1.setInt(5, Integer.parseInt(details[0].toString()));
                cstmt1.setInt(6, Integer.parseInt(details[1].toString()));

                i = cstmt1.executeUpdate();

                con.commit();
                con.setAutoCommit(true);
            } else {
                i = -1;
            }
        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in inserting CARDIOPULMONARY DISEASES Details", personcode);
            // End
            con.rollback();
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "insertCardioPulmonary", "VisualImapairmentDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "insertCardioPulmonary");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);
            DBConnection.closeStatement(cstmt1);



        }

        return i;
    }

    /**
     *
     * @param datasource
     * @param personcode
     * @param cardiopulmonaryvalue
     * @param Systemip
     * @param loginid
     * @throws org.bf.disability.Exception.SADAREMException
     * @return
     */
    public int insertCardioPulmonaryAU(DataSource ds, String personcode, float cardiopulmonaryvalue, String Systemip, String loginid, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        CallableStatement cstmt1 = null;
        ResultSet rs = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();

        try {

            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, personcode);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);


            pstmt = con.prepareStatement("select * from tblCardio_Pulmonary_Conditions_Details where Person_Code=? and status='Active'");
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            if (!rs.next()) {

                cstmt1 = con.prepareCall("{Call SP_CPC_Details_Insert(?,?,?,?,?,?)}");
                cstmt1.setString(1, personcode);
                cstmt1.setFloat(2, cardiopulmonaryvalue);
                cstmt1.setString(3, loginid);
                cstmt1.setString(4, Systemip);
                cstmt1.setInt(5, Integer.parseInt(details[0].toString()));
                cstmt1.setInt(6, Integer.parseInt(details[1].toString()));

                i = cstmt1.executeUpdate();

                con.commit();
                con.setAutoCommit(true);
            } else {
                i = -1;
            }
        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in inserting CARDIOPULMONARY DISEASES Details", personcode);
            // End
            con.rollback();
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "insertCardioPulmonaryAU", "VisualImapairmentDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "insertCardioPulmonaryAU");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(cstmt1);



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
    public float getCardioPulmonaryDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        float cardiopulmonary = 0;

        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_CPC_Details_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            if (rs.next()) {
                cardiopulmonary = rs.getFloat("Pulmonary_Condition");
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCardioPulmonaryDetails", "VisualImapairmentDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "getCardioPulmonaryDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCardioPulmonaryDetails", "VisualImapairmentDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "getCardioPulmonaryDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return cardiopulmonary;
    }

    /**
     * 
     * @param ds 
     * @param personcode 
     * @param cardiopulmonary 
     * @param systemip 
     * @param loginid 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public int updateCardioPolumonary(DataSource ds, String personcode, float cardiopulmonary, String systemip, String loginid, HttpServletRequest request) throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        Statement stmt = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        int i = 0, j = 0;
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();

        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, personcode);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            if (cardiopulmonary == 0) {
                // stmt=con.createStatement();
                // j=stmt.executeUpdate("delete from tblCardio_Pulmonary_Conditions_Details where person_code='"+personcode+"'");
                CommonDAO.changeStatusToInactiveForResetInUpdate(ds, personcode, "tblCardio_Pulmonary_Conditions_Details");
                con.setAutoCommit(true);
            } else {
                cstmt = con.prepareCall("{Call SP_CPC_Details_Update(?,?,?,?,?,?)}");
                cstmt.setString(1, personcode);
                cstmt.setFloat(2, cardiopulmonary);
                cstmt.setString(3, loginid);
                cstmt.setString(4, systemip);

                cstmt.setInt(5, Integer.parseInt(details[0].toString()));
                cstmt.setInt(6, Integer.parseInt(details[1].toString()));

                i = cstmt.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            }
            if (j == 0 && i == 0) {
                i = insertCardioPulmonary(ds, String.valueOf(personcode), cardiopulmonary, systemip, loginid, request);
            }
        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in Updating CARDIOPULMONARY DISEASES Details", personcode);
            // End
            con.rollback();
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "updateCardioPolumonary", "VisualImapairmentDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "updateCardioPolumonary");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(cstmt);

        }

        return i > j ? i : j;
    }

    /**
     * 
     * @param datasource 
     * @param dto 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public int insertDwarfism(DataSource ds, DwarfismDTO dto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cstmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String code = dto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();

        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();


        int i = 0;

        try {

            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            pstmt = con.prepareStatement("select * from tblDwarfism_Details where Person_Code=?");
            pstmt.setString(1, code);
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                cstmt = con.prepareCall("{Call SP_tblDwarfism_Details_Insert(?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, code);
                cstmt.setInt(2, dto.getAgeyears());
                cstmt.setInt(3, dto.getAgemonths());
                cstmt.setFloat(4, dto.getHeight());
                cstmt.setFloat(5, dto.getDwarfism());
                cstmt.setString(6, dto.getLoginid());
                cstmt.setString(7, dto.getSystemip());

                cstmt.setInt(8, Integer.parseInt(details[0].toString()));
                cstmt.setInt(9, Integer.parseInt(details[1].toString()));
                i = cstmt.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            } else {
                i = -1;
            }
        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error insert Dwasim Details", code);
            // End
            con.rollback();
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "insertDwarfism", "VisualImapairmentDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "insertDwarfism");

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
     * @param datasource
     * @param dto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return
     */
    public int insertDwarfismAU(DataSource ds, DwarfismDTO dto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cstmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String code = dto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        int i = 0;
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();

        try {

            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);


            pstmt = con.prepareStatement("select * from tblDwarfism_Details where Person_Code=? and status='Active'");
            pstmt.setString(1,code);
            rs = pstmt.executeQuery();
            
            if (!rs.next()) {


                cstmt = con.prepareCall("{Call SP_tblDwarfism_Details_Insert(?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, code);
                cstmt.setInt(2, dto.getAgeyears());
                cstmt.setInt(3, dto.getAgemonths());
                cstmt.setFloat(4, dto.getHeight());
                cstmt.setFloat(5, dto.getDwarfism());
                cstmt.setString(6, dto.getLoginid());
                cstmt.setString(7, dto.getSystemip());
                cstmt.setInt(8, Integer.parseInt(details[0].toString()));
                cstmt.setInt(9, Integer.parseInt(details[1].toString()));

                i = cstmt.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            } else {
                i = -1;
            }

        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in insert Dwasim Details", code);
            // End
            con.rollback();
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "insertDwarfismAU", "VisualImapairmentDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "insertDwarfismAU");

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
     * @param personid 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public String getGender(DataSource ds, String personid) throws SADAREMDBException, SQLException {

        Connection con = null;
        //CallableStatement cstmt=null;

        PreparedStatement pstmt = null;
        ResultSet rs = null;




        String gender = null;
        try {

            con = DBConnection.getConnection();
            pstmt = con.prepareStatement("select Gender from tblPerson_Personal_Details  with (nolock) where Person_Code=?");
            pstmt.setString(1, personid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                gender = rs.getString("Gender");
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getGender", "VisualImapairmentDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "getGender");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getGender", "VisualImapairmentDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "getGender");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);



        }
        return gender;
    }

    /**
     * 
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public DwarfismDTO getDwarfismDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;


        DwarfismDTO dwarfismdto = new DwarfismDTO();
        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_tblDwarfism_Details_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                dwarfismdto.setAgeyears(rs.getInt("Person_Years"));
                dwarfismdto.setAgemonths(rs.getInt("Person_Month"));
                dwarfismdto.setHeight(rs.getFloat("Person_Height"));
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDwarfismDetails", "VisualImapairmentDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "getDwarfismDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDwarfismDetails", "VisualImapairmentDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "getDwarfismDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return dwarfismdto;
    }

    /**
     * 
     * @param ds 
     * @param dwarfismdto 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public int updateDwarfism(DataSource ds, DwarfismDTO dwarfismdto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        PreparedStatement pstmt = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        int i = 0, j = 0;
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        String code = dwarfismdto.getPersoncode();
        try {

            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, dwarfismdto.getPersoncode());
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            if (dwarfismdto.getDwarfism() == 0) {
                //pstmt = con.prepareStatement("delete from tblDwarfism_Details where Person_Code='"+dwarfismdto.getPersoncode()+"'");
                //j=pstmt.executeUpdate();
                CommonDAO.changeStatusToInactiveForResetInUpdate(ds, dwarfismdto.getPersoncode(), "tblDwarfism_Details");

            } else {
                cstmt = con.prepareCall("{Call SP_tblDwarfism_Details_Update(?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, dwarfismdto.getPersoncode());
                cstmt.setInt(2, dwarfismdto.getAgeyears());
                cstmt.setInt(3, dwarfismdto.getAgemonths());
                cstmt.setFloat(4, dwarfismdto.getHeight());
                cstmt.setFloat(5, dwarfismdto.getDwarfism());
                cstmt.setString(6, dwarfismdto.getLoginid());
                cstmt.setString(7, dwarfismdto.getSystemip());

                cstmt.setInt(8, Integer.parseInt(details[0].toString()));
                cstmt.setInt(9, Integer.parseInt(details[1].toString()));

                i = cstmt.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            }
            if (i == 0 && j == 0) {
                i = insertDwarfism(ds, dwarfismdto, request);
            }
        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in updating Dwasim Details", code);
            // End
            con.rollback();
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "updateDwarfism", "VisualImapairmentDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "updateDwarfism");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);
            DBConnection.closeStatement(pstmt);

        }
        return i > j ? i : j;
    }

    /**
     * 
     * @param datasource 
     * @param dto 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public int insertMentalIllness(DataSource ds, MentalIllnessDTO dto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String code = dto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        int i = 0;
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            pstmt = con.prepareStatement("select * from tblMental_Disability_Illness_Details where Person_Code=? and status='Active'");
            pstmt.setString(1, dto.getPersoncode());
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                cstmt = con.prepareCall("{Call SP_MDI_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, code);
                cstmt.setInt(2, dto.getSelfcare());
                cstmt.setInt(3, dto.getPersonalactivities());
                cstmt.setInt(4, dto.getCommunication());
                cstmt.setInt(5, dto.getWork());
                cstmt.setInt(6, dto.getDuration());
                cstmt.setInt(7, dto.getGlobalscore());
                cstmt.setInt(8, dto.getMentaldisability());
                cstmt.setString(9, dto.getBehaviormodification());
                cstmt.setString(10, dto.getSurgery());
                cstmt.setString(11, dto.getPsycotherapybehaviour());
                cstmt.setString(12, dto.getManagerproperties());
                cstmt.setString(13, dto.getPsychiatrichospital());
                cstmt.setString(14, dto.getLoginid());
                cstmt.setString(15, dto.getSystemip());
                cstmt.setString(16, dto.getAnyotherneed());

                cstmt.setInt(17, Integer.parseInt(details[0].toString()));
                cstmt.setInt(18, Integer.parseInt(details[1].toString()));

                i = cstmt.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            } else {
                i = -1;
            }


        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in Inserting MentalIllness Details", code);
            // End
            con.rollback();
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "insertMentalIllness", "VisualImapairmentDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "insertMentalIllness");


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
     * @param datasource
     * @param dto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return
     */
    public int insertMentalIllnessAU(DataSource ds, MentalIllnessDTO dto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
// Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        String code = dto.getPersoncode();

        int i = 0;
        try {

            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, dto.getPersoncode());
            con = DBConnection.getConnection();
            con.setAutoCommit(false);


            pstmt = con.prepareStatement("select * from tblMental_Disability_Illness_Details where Person_Code=? and status='Active'");
            pstmt.setString(1, dto.getPersoncode());
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                cstmt = con.prepareCall("{Call SP_MDI_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, dto.getPersoncode());
                cstmt.setInt(2, dto.getSelfcare());
                cstmt.setInt(3, dto.getPersonalactivities());
                cstmt.setInt(4, dto.getCommunication());
                cstmt.setInt(5, dto.getWork());
                cstmt.setInt(6, dto.getDuration());
                cstmt.setInt(7, dto.getGlobalscore());
                cstmt.setInt(8, dto.getMentaldisability());
                cstmt.setString(9, dto.getBehaviormodification());
                cstmt.setString(10, dto.getSurgery());
                cstmt.setString(11, dto.getPsycotherapybehaviour());
                cstmt.setString(12, dto.getManagerproperties());
                cstmt.setString(13, dto.getPsychiatrichospital());
                cstmt.setString(14, dto.getLoginid());
                cstmt.setString(15, dto.getSystemip());
                cstmt.setString(16, dto.getAnyotherneed());

                cstmt.setInt(17, Integer.parseInt(details[0].toString()));
                cstmt.setInt(18, Integer.parseInt(details[1].toString()));

                i = cstmt.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            } else {
                i = -1;
            }


        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in Inserting MentalIllness Details", code);
            // End

            con.rollback();
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "insertMentalIllnessAU", "VisualImapairmentDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "insertMentalIllnessAU");


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
    public MentalIllnessDTO getMentalIllnessDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;


        MentalIllnessDTO millnessdto = new MentalIllnessDTO();
        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_MDI_Details_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                millnessdto.setSelfcare(rs.getInt("Self_Score"));
                millnessdto.setPersonalactivities(rs.getInt("Personal_Activites"));
                millnessdto.setCommunication(rs.getInt("Communication"));
                millnessdto.setWork(rs.getInt("Work_Ability"));
                millnessdto.setDuration(rs.getInt("Duration"));
                millnessdto.setGlobalscore(rs.getInt("Global_Score"));
                millnessdto.setBehaviormodification(rs.getString("Behaviour"));
                millnessdto.setSurgery(rs.getString("Surgery"));
                millnessdto.setPsycotherapybehaviour(rs.getString("Psycotherapy_Behaviour"));
                millnessdto.setManagerproperties(rs.getString("ManagerProperties"));
                millnessdto.setPsychiatrichospital(rs.getString("NursingHomes"));
                millnessdto.setAnyotherneed(rs.getString("Anyother"));
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMentalIllnessDetails", "VisualImapairmentDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "getMentalIllnessDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMentalIllnessDetails", "VisualImapairmentDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "getMentalIllnessDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);



        }

        return millnessdto;
    }

    /**
     * 
     * @param ds 
     * @param millnessdto 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public int updateMentalIllnesDetails(DataSource ds, MentalIllnessDTO millnessdto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        PreparedStatement pstmt = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        String code = millnessdto.getPersoncode();
// Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();

        int i = 0, j = 0;
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, millnessdto.getPersoncode());
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            if (millnessdto.getMentaldisability() == 0) {
                // pstmt = con.prepareStatement("delete from tblMental_Disability_Illness_Details where Person_Code='"+millnessdto.getPersoncode()+"'");
                //j=pstmt.executeUpdate();
                CommonDAO.changeStatusToInactiveForResetInUpdate(ds, millnessdto.getPersoncode(), "tblMental_Disability_Illness_Details");
                //con.setAutoCommit(true);
            } else {
                cstmt = con.prepareCall("{Call SP_MDI_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, millnessdto.getPersoncode());
                cstmt.setInt(2, millnessdto.getSelfcare());
                cstmt.setInt(3, millnessdto.getPersonalactivities());
                cstmt.setInt(4, millnessdto.getCommunication());
                cstmt.setInt(5, millnessdto.getWork());
                cstmt.setInt(6, millnessdto.getDuration());
                cstmt.setInt(7, millnessdto.getGlobalscore());
                cstmt.setInt(8, millnessdto.getMentaldisability());
                cstmt.setString(9, millnessdto.getBehaviormodification());
                cstmt.setString(10, millnessdto.getSurgery());
                cstmt.setString(11, millnessdto.getPsycotherapybehaviour());
                cstmt.setString(12, millnessdto.getManagerproperties());
                cstmt.setString(13, millnessdto.getPsychiatrichospital());
                cstmt.setString(16, millnessdto.getAnyotherneed());
                cstmt.setString(14, millnessdto.getLoginid());
                cstmt.setString(15, millnessdto.getSystemip());

                cstmt.setInt(17, Integer.parseInt(details[0].toString()));
                cstmt.setInt(18, Integer.parseInt(details[1].toString()));

                i = cstmt.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            }

            if (i == 0 && j == 0) {
                i = insertMentalIllness(ds, millnessdto, request);
            }

        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in Updating MentalIllness Details", code);
            // End

            con.rollback();
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "updateMentalIllnesDetails", "VisualImapairmentDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "updateMentalIllnesDetails");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);
            DBConnection.closeStatement(pstmt);

        }
        return i > j ? i : j;
    }

    /**
     * 
     * @param ds 
     * @param personcode 
     * @param visualimparment 
     * @param systemip 
     * @param loginid 
     * @param cardioPulmonarydto 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public int insertVisualImparment(DataSource ds, String personcode, float visualimparment, String systemip, String loginid, CardioPulmonaryDTO cardioPulmonarydto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
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


            pstmt = con.prepareStatement("select * from tblVisualImpairment_Details where person_code=?");
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                cstmt = con.prepareCall("{Call SP_tblVisualImpairment_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, personcode);
                cstmt.setFloat(2, visualimparment);
                cstmt.setString(3, cardioPulmonarydto.getSurgery());
                cstmt.setString(4, cardioPulmonarydto.getWhitecane());
                cstmt.setString(5, cardioPulmonarydto.getBrailleequipments());
                cstmt.setString(6, cardioPulmonarydto.getArithmeticframes());
                cstmt.setString(7, cardioPulmonarydto.getLowvisionaids());
                cstmt.setString(8, cardioPulmonarydto.getSpeechsynthesizer());
                cstmt.setString(9, cardioPulmonarydto.getBrailleshorthandmachines());
                cstmt.setString(10, cardioPulmonarydto.getTalkingwatchcalculator());
                cstmt.setString(11, cardioPulmonarydto.getAnyadlequipmentes());
                cstmt.setString(12, cardioPulmonarydto.getAnyotherspecify());
                cstmt.setString(13, loginid);
                cstmt.setString(14, systemip);

                cstmt.setInt(15, Integer.parseInt(details[0].toString()));
                cstmt.setInt(16, Integer.parseInt(details[1].toString()));

                i = cstmt.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            } else {
                i = -1;
            }
        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error Adding Visual Impairment Details", personcode);
            // End

            con.rollback();
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "insertVisualImparment", "VisualImapairmentDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "insertVisualImparment");


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
     * @param visualimparment
     * @param systemip
     * @param loginid
     * @param cardioPulmonarydto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return
     */
    public int insertVisualImparmentApUpdate(DataSource ds, String personcode, float visualimparment, String systemip, String loginid, CardioPulmonaryDTO cardioPulmonarydto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
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

            pstmt = con.prepareStatement("select * from tblVisualImpairment_Details where person_code=? and status='Active'");
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                cstmt = con.prepareCall("{Call SP_tblVisualImpairment_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, personcode);
                cstmt.setFloat(2, visualimparment);
                cstmt.setString(3, cardioPulmonarydto.getSurgery());
                cstmt.setString(4, cardioPulmonarydto.getWhitecane());
                cstmt.setString(5, cardioPulmonarydto.getBrailleequipments());
                cstmt.setString(6, cardioPulmonarydto.getArithmeticframes());
                cstmt.setString(7, cardioPulmonarydto.getLowvisionaids());
                cstmt.setString(8, cardioPulmonarydto.getSpeechsynthesizer());
                cstmt.setString(9, cardioPulmonarydto.getBrailleshorthandmachines());
                cstmt.setString(10, cardioPulmonarydto.getTalkingwatchcalculator());
                cstmt.setString(11, cardioPulmonarydto.getAnyadlequipmentes());
                cstmt.setString(12, cardioPulmonarydto.getAnyotherspecify());
                cstmt.setString(13, loginid);
                cstmt.setString(14, systemip);

                cstmt.setInt(15, Integer.parseInt(details[0].toString()));
                cstmt.setInt(16, Integer.parseInt(details[1].toString()));

                i = cstmt.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            } else {
                i = -1;
            }
        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error Updating Visual Impairment Details", personcode);
            // End

            con.rollback();
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "insertVisualImparmentApUpdate", "VisualImapairmentDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "insertVisualImparmentApUpdate");


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
     * @param visualimparment
     * @param systemip
     * @param loginid
     * @param cardioPulmonarydto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return
     */
    public int insertVisualImparmentAU(DataSource ds, String personcode, float visualimparment, String systemip, String loginid, CardioPulmonaryDTO cardioPulmonarydto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
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

            pstmt = con.prepareStatement("select * from tblVisualImpairment_Details where person_code=? and status='Active'");
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                cstmt = con.prepareCall("{Call SP_tblVisualImpairment_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, personcode);
                cstmt.setFloat(2, visualimparment);
                cstmt.setString(3, cardioPulmonarydto.getSurgery());
                cstmt.setString(4, cardioPulmonarydto.getWhitecane());
                cstmt.setString(5, cardioPulmonarydto.getBrailleequipments());
                cstmt.setString(6, cardioPulmonarydto.getArithmeticframes());
                cstmt.setString(7, cardioPulmonarydto.getLowvisionaids());
                cstmt.setString(8, cardioPulmonarydto.getSpeechsynthesizer());
                cstmt.setString(9, cardioPulmonarydto.getBrailleshorthandmachines());
                cstmt.setString(10, cardioPulmonarydto.getTalkingwatchcalculator());
                cstmt.setString(11, cardioPulmonarydto.getAnyadlequipmentes());
                cstmt.setString(12, cardioPulmonarydto.getAnyotherspecify());
                cstmt.setString(13, loginid);
                cstmt.setString(14, systemip);
                cstmt.setString(14, systemip);

                cstmt.setInt(15, Integer.parseInt(details[0].toString()));
                cstmt.setInt(16, Integer.parseInt(details[1].toString()));

                i = cstmt.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            } else {
                i = -1;
            }

        } catch (Exception exception) {

            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error Adding Visual Impairment Details", personcode);
            // End
            con.rollback();
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "insertVisualImparmentAU", "VisualImapairmentDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "insertVisualImparmentAU");

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
    public CardioPulmonaryDTO getVisualImpairment(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        float visual = 0;
        CardioPulmonaryDTO dto = new CardioPulmonaryDTO();

        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_tblVisualImpairment_Details_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                dto.setVisualimpairment(rs.getString("Visual_Impairment"));

                dto.setSurgery(rs.getString("Surgery"));
                dto.setWhitecane(rs.getString("WhiteCane"));
                dto.setBrailleequipments(rs.getString("BrailleEquipments"));
                dto.setArithmeticframes(rs.getString("ArithmeticFrames"));
                dto.setLowvisionaids(rs.getString("LowVisionAids"));
                dto.setSpeechsynthesizer(rs.getString("SpeechSynthesizer"));
                dto.setBrailleshorthandmachines(rs.getString("ShortHand"));
                dto.setTalkingwatchcalculator(rs.getString("TalkingWatch"));
                dto.setAnyadlequipmentes(rs.getString("AnyADL"));
                dto.setAnyotherspecify(rs.getString("AnyOther"));
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVisualImpairment", "VisualImapairmentDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "getVisualImpairment");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVisualImpairment", "VisualImapairmentDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "getVisualImpairment");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return dto;
    }

    /**
     * 
     * @param ds 
     * @param personcode 
     * @param visualimparment 
     * @param systemip 
     * @param loginid 
     * @param cardioPulmonarydto 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public int updateVisualImpairment(DataSource ds, String personcode, double visualimparment, String systemip, String loginid, CardioPulmonaryDTO cardioPulmonarydto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        Statement stmt = null;

        int i = 0;
        int j = 0;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();


        try {

            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, personcode);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);


            stmt = con.createStatement();

            if (visualimparment == 0) {

                CommonDAO.changeStatusToInactiveForResetInUpdate(ds, personcode, "tblVisualImpairment_Details");
                con.setAutoCommit(true);
            } else {
                cstmt = con.prepareCall("{Call SP_tblVisualImpairment_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, personcode);
                cstmt.setDouble(2, visualimparment);
                cstmt.setString(3, cardioPulmonarydto.getSurgery());
                cstmt.setString(4, cardioPulmonarydto.getWhitecane());
                cstmt.setString(5, cardioPulmonarydto.getBrailleequipments());
                cstmt.setString(6, cardioPulmonarydto.getArithmeticframes());
                cstmt.setString(7, cardioPulmonarydto.getLowvisionaids());
                cstmt.setString(8, cardioPulmonarydto.getSpeechsynthesizer());
                cstmt.setString(9, cardioPulmonarydto.getBrailleshorthandmachines());
                cstmt.setString(10, cardioPulmonarydto.getTalkingwatchcalculator());
                cstmt.setString(11, cardioPulmonarydto.getAnyadlequipmentes());
                cstmt.setString(12, cardioPulmonarydto.getAnyotherspecify());
                cstmt.setString(13, loginid);
                cstmt.setString(14, systemip);

                cstmt.setInt(15, Integer.parseInt(details[0].toString()));
                cstmt.setInt(16, Integer.parseInt(details[1].toString()));

                i = cstmt.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            }

            if (i == 0 && j == 0) {
                String vi = String.valueOf(visualimparment);
                i = insertVisualImparmentApUpdate(ds, personcode, Float.parseFloat(vi), systemip, loginid, cardioPulmonarydto, request);
            }

        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error Updating Visual Impairment Details", personcode);
            // End

            con.rollback();
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "updateVisualImpairment", "VisualImapairmentDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImapairmentDAO", "updateVisualImpairment");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(cstmt);

        }
        return i > j ? i : j;
    }
}
