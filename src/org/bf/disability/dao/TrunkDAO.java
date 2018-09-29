/*
 * TrunkDAO.java
 *
 * Created on July 9, 2008, 12:40 PM
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

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.dto.TrunkDTO;
import org.bf.disability.service.TransactionService;
import org.bf.disability.servicefactory.TransactionFactory;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is for manipulating trunk detail to database
 * @author Bapinaidu.t
 * @version 1.0
 */
public class TrunkDAO {

    /**
     * Creates a new instance of TrunkDAO
     * @param datasource 
     * @param trunkdto 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
	 public int insertTrunkDetails(DataSource datasource, TrunkDTO trunkdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
    
        ResultSet rs = null;
        Connection con = null;
        CallableStatement cstmt = null;
        String code = trunkdto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();

// Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();

        int i = 0;
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(datasource, code);

            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            cstmt = con.prepareCall("{Call SP_tblTraumatic_Lesions_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, code);
            cstmt.setInt(2, Integer.parseInt(trunkdto.getCompression()));
            cstmt.setInt(3, Integer.parseInt(trunkdto.getPosterior_fusion()));
            cstmt.setInt(4, Integer.parseInt(trunkdto.getPosterior_persistent()));
            cstmt.setInt(5, Integer.parseInt(trunkdto.getSevere_fire()));
            cstmt.setInt(6, Integer.parseInt(trunkdto.getSevere_inadequate()));
            cstmt.setInt(7, Integer.parseInt(trunkdto.getCervical_disc()));
            cstmt.setInt(8, Integer.parseInt(trunkdto.getCervical_pain()));
            cstmt.setInt(9, Integer.parseInt(trunkdto.getThoracic_less()));
            cstmt.setInt(10, Integer.parseInt(trunkdto.getThoracic_more()));
            cstmt.setInt(11, Integer.parseInt(trunkdto.getThoracic_fusion()));
            cstmt.setInt(12, Integer.parseInt(trunkdto.getThoracic_radiologically()));
            cstmt.setInt(13, Integer.parseInt(trunkdto.getFracture_less()));
            cstmt.setInt(14, Integer.parseInt(trunkdto.getFracture_more()));
            cstmt.setInt(15, Integer.parseInt(trunkdto.getFracture_radiologically()));
            cstmt.setInt(16, Integer.parseInt(trunkdto.getDisc_persistent()));
            cstmt.setInt(17, Integer.parseInt(trunkdto.getDisc_pain()));
            cstmt.setInt(18, Integer.parseInt(trunkdto.getDisc_diseases()));
            cstmt.setInt(19, Integer.parseInt(trunkdto.getDisc_stifness()));
            cstmt.setInt(20, trunkdto.getTraumatic());
            cstmt.setString(21, trunkdto.getLoginid());
            cstmt.setString(22, trunkdto.getSystemip());

            cstmt.setInt(23, Integer.parseInt(details[0].toString()));
            cstmt.setInt(24, Integer.parseInt(details[1].toString()));

            i = cstmt.executeUpdate();
            cstmt.close();

            cstmt = con.prepareCall("{Call SP_tblTNTL_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, code);
            cstmt.setBoolean(2, trunkdto.isScoliosis());
            cstmt.setInt(3, Integer.parseInt(trunkdto.getScoliosis_measure()));
            cstmt.setInt(4, Integer.parseInt(trunkdto.getScoliosis_torso()));
            cstmt.setDouble(5, trunkdto.getTotalscoliosis());
            cstmt.setBoolean(6, trunkdto.isKyphosis());
            cstmt.setInt(7, Integer.parseInt(trunkdto.getKyphosis_measure()));
            cstmt.setInt(8, Integer.parseInt(trunkdto.getKyphosis_torso()));
            cstmt.setInt(9, Integer.parseInt(trunkdto.getHead()));
            cstmt.setDouble(10, trunkdto.getTotalkyphosis());
            cstmt.setInt(11, Integer.parseInt(trunkdto.getCardio_chest()));
            cstmt.setInt(12, Integer.parseInt(trunkdto.getCardio_counting()));
            cstmt.setInt(13, Integer.parseInt(trunkdto.getCardio_associatepain()));
            cstmt.setInt(14, Integer.parseInt(trunkdto.getCardio_associatecosmetic()));
            cstmt.setString(15, trunkdto.getCardio_associateleg());
            cstmt.setDouble(16, trunkdto.getNontraumatic());
            cstmt.setDouble(17, trunkdto.getTrunk());
            cstmt.setString(18, trunkdto.getLoginid());
            cstmt.setString(19, trunkdto.getSystemip());


            cstmt.setInt(20, Integer.parseInt(details[0].toString()));
            cstmt.setInt(21, Integer.parseInt(details[1].toString()));

            int j = cstmt.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(datasource, "Error in Inserting Trunk Details", code);
            // End
            con.rollback();
            int exResult = ExceptionDAO.saveException(datasource, exception.toString(), "insertTrunkDetails", "TrunkDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TrunkDAO", "insertTrunkDetails");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return i;
    }

    /**
     * Creates a new instance of TrunkDAO
     * @param datasource
     * @param trunkdto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return
     */
    public int insertTrunkDetailsAU(DataSource datasource, TrunkDTO trunkdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        Connection con = null;
        CallableStatement cstmt = null;
        String code = trunkdto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        PreparedStatement pstmt = null;
        int i = 0;

        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(datasource, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            pstmt = con.prepareStatement("select * from tblTraumatic_Lesions_Details where Person_Code=? and status='Active'");
            pstmt.setString(1, code);
            rs = pstmt.executeQuery();
            if (!rs.next()) {

                cstmt = con.prepareCall("{Call SP_tblTraumatic_Lesions_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, code);
                cstmt.setInt(2, Integer.parseInt(trunkdto.getCompression()));
                cstmt.setInt(3, Integer.parseInt(trunkdto.getPosterior_fusion()));
                cstmt.setInt(4, Integer.parseInt(trunkdto.getPosterior_persistent()));
                cstmt.setInt(5, Integer.parseInt(trunkdto.getSevere_fire()));
                cstmt.setInt(6, Integer.parseInt(trunkdto.getSevere_inadequate()));
                cstmt.setInt(7, Integer.parseInt(trunkdto.getCervical_disc()));
                cstmt.setInt(8, Integer.parseInt(trunkdto.getCervical_pain()));
                cstmt.setInt(9, Integer.parseInt(trunkdto.getThoracic_less()));
                cstmt.setInt(10, Integer.parseInt(trunkdto.getThoracic_more()));
                cstmt.setInt(11, Integer.parseInt(trunkdto.getThoracic_fusion()));
                cstmt.setInt(12, Integer.parseInt(trunkdto.getThoracic_radiologically()));
                cstmt.setInt(13, Integer.parseInt(trunkdto.getFracture_less()));
                cstmt.setInt(14, Integer.parseInt(trunkdto.getFracture_more()));
                cstmt.setInt(15, Integer.parseInt(trunkdto.getFracture_radiologically()));
                cstmt.setInt(16, Integer.parseInt(trunkdto.getDisc_persistent()));
                cstmt.setInt(17, Integer.parseInt(trunkdto.getDisc_pain()));
                cstmt.setInt(18, Integer.parseInt(trunkdto.getDisc_diseases()));
                cstmt.setInt(19, Integer.parseInt(trunkdto.getDisc_stifness()));
                cstmt.setInt(20, trunkdto.getTraumatic());
                cstmt.setString(21, trunkdto.getLoginid());
                cstmt.setString(22, trunkdto.getSystemip());

                cstmt.setInt(23, Integer.parseInt(details[0].toString()));
                cstmt.setInt(24, Integer.parseInt(details[1].toString()));

                i = cstmt.executeUpdate();
                cstmt.close();

                cstmt = con.prepareCall("{Call SP_tblTNTL_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, code);
                cstmt.setBoolean(2, trunkdto.isScoliosis());
                cstmt.setInt(3, Integer.parseInt(trunkdto.getScoliosis_measure()));
                cstmt.setInt(4, Integer.parseInt(trunkdto.getScoliosis_torso()));
                cstmt.setDouble(5, trunkdto.getTotalscoliosis());
                cstmt.setBoolean(6, trunkdto.isKyphosis());
                cstmt.setInt(7, Integer.parseInt(trunkdto.getKyphosis_measure()));
                cstmt.setInt(8, Integer.parseInt(trunkdto.getKyphosis_torso()));
                cstmt.setInt(9, Integer.parseInt(trunkdto.getHead()));
                cstmt.setDouble(10, trunkdto.getTotalkyphosis());
                cstmt.setInt(11, Integer.parseInt(trunkdto.getCardio_chest()));
                cstmt.setInt(12, Integer.parseInt(trunkdto.getCardio_counting()));
                cstmt.setInt(13, Integer.parseInt(trunkdto.getCardio_associatepain()));
                cstmt.setInt(14, Integer.parseInt(trunkdto.getCardio_associatecosmetic()));
                cstmt.setString(15, trunkdto.getCardio_associateleg());
                cstmt.setDouble(16, trunkdto.getNontraumatic());
                cstmt.setDouble(17, trunkdto.getTrunk());
                cstmt.setString(18, trunkdto.getLoginid());
                cstmt.setString(19, trunkdto.getSystemip());

                cstmt.setInt(20, Integer.parseInt(details[0].toString()));
                cstmt.setInt(21, Integer.parseInt(details[1].toString()));

                int j = cstmt.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            } else {
                i = -1;
            }
        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(datasource, "Error in Inserting Trunk Details", code);
            // End
            con.rollback();
            int exResult = ExceptionDAO.saveException(datasource, exception.toString(), "insertTrunkDetailsAU", "TrunkDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TrunkDAO", "insertTrunkDetailsAU");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);
            DBConnection.closeStatement(pstmt);

        }
        return i;
    }

    /**
     * 
     * @param datasource 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public TrunkDTO getTrunkDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        TrunkDTO trunkdto = new TrunkDTO();
        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_tblTraumatic_Lesions_Details_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                trunkdto.setCompression(String.valueOf(rs.getInt(1)));
                trunkdto.setPosterior_fusion(String.valueOf(rs.getInt(2)));
                trunkdto.setPosterior_persistent(String.valueOf(rs.getInt(3)));
                trunkdto.setSevere_fire(String.valueOf(rs.getInt(4)));
                trunkdto.setSevere_inadequate(String.valueOf(rs.getInt(5)));
                trunkdto.setCervical_disc(String.valueOf(rs.getInt(6)));
                trunkdto.setCervical_pain(String.valueOf(rs.getInt(7)));
                trunkdto.setThoracic_less(String.valueOf(rs.getInt(8)));
                trunkdto.setThoracic_more(String.valueOf(rs.getInt(9)));
                trunkdto.setThoracic_fusion(String.valueOf(rs.getInt(10)));
                trunkdto.setThoracic_radiologically(String.valueOf(rs.getInt(11)));
                trunkdto.setFracture_less(String.valueOf(rs.getInt(12)));
                trunkdto.setFracture_more(String.valueOf(rs.getInt(13)));
                trunkdto.setFracture_radiologically(String.valueOf(rs.getInt(14)));
                trunkdto.setDisc_persistent(String.valueOf(rs.getInt(15)));
                trunkdto.setDisc_pain(String.valueOf(rs.getInt(16)));
                trunkdto.setDisc_diseases(String.valueOf(rs.getInt(17)));
                trunkdto.setDisc_stifness(String.valueOf(rs.getInt(18)));
                trunkdto.setTraumatic(rs.getInt(19));
            }
            rs.close();
            cstmt.close();
            cstmt = con.prepareCall("{Call SP_tblTNTL_Details_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                trunkdto.setScoliosis(rs.getBoolean(1));
                trunkdto.setScoliosis_measure(String.valueOf(rs.getInt(2)));
                trunkdto.setScoliosis_torso(String.valueOf(rs.getInt(3)));
                trunkdto.setTotalscoliosis(rs.getInt(4));
                trunkdto.setKyphosis(rs.getBoolean(5));
                trunkdto.setKyphosis_measure(String.valueOf(rs.getInt(6)));
                trunkdto.setKyphosis_torso(String.valueOf(rs.getInt(7)));
                trunkdto.setHead(String.valueOf(rs.getInt(8)));
                trunkdto.setTotalkyphosis(rs.getInt(9));
                trunkdto.setCardio_chest(String.valueOf(rs.getInt(10)));
                trunkdto.setCardio_counting(String.valueOf(rs.getInt(11)));
                trunkdto.setCardio_associatepain(String.valueOf(rs.getInt(12)));
                trunkdto.setCardio_associatecosmetic(String.valueOf(rs.getInt(13)));
                trunkdto.setCardio_associateleg(String.valueOf(rs.getFloat(14)));
                trunkdto.setNontraumatic(rs.getFloat(15));
                trunkdto.setTrunk(rs.getFloat(16));
            }
            rs.close();
            cstmt.close();
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getTrunkDetails", "TrunkDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TrunkDAO", "getTrunkDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getTrunkDetails", "TrunkDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TrunkDAO", "getTrunkDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return trunkdto;
    }

    /**
     * 
     * @param datasource 
     * @param trunkdto 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public int updateTrunkDetails(DataSource datasource, TrunkDTO trunkdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        String code = trunkdto.getPersoncode();
        int i = 0;
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(datasource, trunkdto.getPersoncode());
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            if (trunkdto.getTrunk() == 0) {
                CommonDAO.changeStatusToInactiveForResetInUpdate(datasource, trunkdto.getPersoncode(), "tblTrunk_NonTraumaticLesions_Details");
                CommonDAO.changeStatusToInactiveForResetInUpdate(datasource, trunkdto.getPersoncode(), "tblTraumatic_Lesions_Details");
            } else {
                cstmt = con.prepareCall("{Call SP_tblTraumatic_Lesions_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, trunkdto.getPersoncode());
                cstmt.setInt(2, Integer.parseInt(trunkdto.getCompression()));
                cstmt.setInt(3, Integer.parseInt(trunkdto.getPosterior_fusion()));
                cstmt.setInt(4, Integer.parseInt(trunkdto.getPosterior_persistent()));
                cstmt.setInt(5, Integer.parseInt(trunkdto.getSevere_fire()));
                cstmt.setInt(6, Integer.parseInt(trunkdto.getSevere_inadequate()));
                cstmt.setInt(7, Integer.parseInt(trunkdto.getCervical_disc()));
                cstmt.setInt(8, Integer.parseInt(trunkdto.getCervical_pain()));
                cstmt.setInt(9, Integer.parseInt(trunkdto.getThoracic_less()));
                cstmt.setInt(10, Integer.parseInt(trunkdto.getThoracic_more()));
                cstmt.setInt(11, Integer.parseInt(trunkdto.getThoracic_fusion()));
                cstmt.setInt(12, Integer.parseInt(trunkdto.getThoracic_radiologically()));
                cstmt.setInt(13, Integer.parseInt(trunkdto.getFracture_less()));
                cstmt.setInt(14, Integer.parseInt(trunkdto.getFracture_more()));
                cstmt.setInt(15, Integer.parseInt(trunkdto.getFracture_radiologically()));
                cstmt.setInt(16, Integer.parseInt(trunkdto.getDisc_persistent()));
                cstmt.setInt(17, Integer.parseInt(trunkdto.getDisc_pain()));
                cstmt.setInt(18, Integer.parseInt(trunkdto.getDisc_diseases()));
                cstmt.setInt(19, Integer.parseInt(trunkdto.getDisc_stifness()));
                cstmt.setInt(20, trunkdto.getTraumatic());
                cstmt.setString(21, trunkdto.getLoginid());
                cstmt.setString(22, trunkdto.getSystemip());

                cstmt.setInt(23, Integer.parseInt(details[0].toString()));
                cstmt.setInt(24, Integer.parseInt(details[1].toString()));

                i = cstmt.executeUpdate();
                cstmt.close();

                cstmt = con.prepareCall("{Call SP_tblTNTL_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, trunkdto.getPersoncode());
                cstmt.setBoolean(2, trunkdto.isScoliosis());
                cstmt.setInt(3, Integer.parseInt(trunkdto.getScoliosis_measure()));
                cstmt.setInt(4, Integer.parseInt(trunkdto.getScoliosis_torso()));
                cstmt.setDouble(5, trunkdto.getTotalscoliosis());
                cstmt.setBoolean(6, trunkdto.isKyphosis());
                cstmt.setInt(7, Integer.parseInt(trunkdto.getKyphosis_measure()));
                cstmt.setInt(8, Integer.parseInt(trunkdto.getKyphosis_torso()));
                cstmt.setInt(9, Integer.parseInt(trunkdto.getHead()));
                cstmt.setDouble(10, trunkdto.getTotalkyphosis());
                cstmt.setInt(11, Integer.parseInt(trunkdto.getCardio_chest()));
                cstmt.setInt(12, Integer.parseInt(trunkdto.getCardio_counting()));
                cstmt.setInt(13, Integer.parseInt(trunkdto.getCardio_associatepain()));
                cstmt.setInt(14, Integer.parseInt(trunkdto.getCardio_associatecosmetic()));
                cstmt.setString(15, trunkdto.getCardio_associateleg());
                cstmt.setDouble(16, trunkdto.getNontraumatic());
                cstmt.setDouble(17, trunkdto.getTrunk());
                cstmt.setString(18, trunkdto.getLoginid());
                cstmt.setString(19, trunkdto.getSystemip());

                cstmt.setInt(20, Integer.parseInt(details[0].toString()));
                cstmt.setInt(21, Integer.parseInt(details[1].toString()));

                int j = cstmt.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            }
        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(datasource, "Error in Updating Trunk Details", code);
            // End
            con.rollback();
            int exResult = ExceptionDAO.saveException(datasource, exception.toString(), "updateTrunkDetails", "TrunkDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TrunkDAO", "updateTrunkDetails");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return i;
    }

    /**
     * 
     * @param datasource 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return int
     */
    public boolean personCode(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean code = false;
        try {
            con = DBConnection.getConnection();
            String query = "select Person_Code from tblTraumatic_Lesions_Details where Person_Code=? and status='Active'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            if (rs.next() == false) {
                code = false;
            } else {
                code = true;
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "personCode", "TrunkDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TrunkDAO", "personCode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "personCode", "TrunkDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TrunkDAO", "personCode");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return code;
    }
}
