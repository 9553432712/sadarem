package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.dto.AmputationDto;
import org.bf.disability.service.TransactionService;
import org.bf.disability.servicefactory.TransactionFactory;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this class for manipulating amputation details with database
 * @author Devi Prasad .G
 * @version 1.0
 */
public class AmputationDao {

    /**
     * 
     * @param ds 
     * @param amputationdto 
     * @throws org.bf.disability.Exception.SADAREMException 
     */
    public void insertAmputationDetails(DataSource ds, AmputationDto amputationdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int flag_UpperLimb = 0;

        Connection con = null;
        CallableStatement calstmt = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        String code = amputationdto.getPersoncode();
        //  HttpSession session = request.getSession();
        String[] details = null;
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /** Getting the categoryid,categorycount */
            details = dao.getCategoryDetails(ds, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);


            calstmt = con.prepareCall("{Call SP_tblAULA_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            calstmt.setString(1, code);
            calstmt.setInt(2, amputationdto.getUpper_fore_right());
            calstmt.setInt(3, amputationdto.getUpper_fore_left());
            calstmt.setInt(4, amputationdto.getUpper_shoulder_right());
            calstmt.setInt(5, amputationdto.getUpper_shoulder_left());
            calstmt.setInt(6, amputationdto.getUpper_aboveelbowupper_right());
            calstmt.setInt(7, amputationdto.getUpper_aboveelbowupper_left());
            calstmt.setInt(8, amputationdto.getUpper_elbowlower_right());
            calstmt.setInt(9, amputationdto.getUpper_elbowlower_left());
            calstmt.setInt(10, amputationdto.getUpper_elbowdis_right());
            calstmt.setInt(11, amputationdto.getUpper_elbowdis_left());
            calstmt.setInt(12, amputationdto.getUpper_belowelbowupper_right());
            calstmt.setInt(13, amputationdto.getUpper_belowelbowupper_left());
            calstmt.setInt(14, amputationdto.getUpper_belowelbowlower_right());
            calstmt.setInt(15, amputationdto.getUpper_belowelbowlower_left());
            calstmt.setInt(16, amputationdto.getUpper_waistdis_right());
            calstmt.setInt(17, amputationdto.getUpper_waistdis_left());
            calstmt.setInt(18, amputationdto.getUpper_handcarpel_right());
            calstmt.setInt(19, amputationdto.getUpper_handcarpel_left());
            calstmt.setInt(20, amputationdto.getUpper_thumbCM_right());
            calstmt.setInt(21, amputationdto.getUpper_thumbCM_left());
            calstmt.setInt(22, amputationdto.getUpper_thumbMCP_right());
            calstmt.setInt(23, amputationdto.getUpper_thumbMCP_left());
            calstmt.setInt(24, amputationdto.getUpper_thumbIP_right());
            calstmt.setInt(25, amputationdto.getUpper_thumbIP_left());
            calstmt.setDouble(26, amputationdto.getUpperamputation());
            calstmt.setDouble(27, amputationdto.getAmputationtotal());
            calstmt.setString(28, amputationdto.getLoginid());
            calstmt.setString(29, amputationdto.getSystemIP());


            calstmt.setInt(30, Integer.parseInt(details[0].toString()));
            calstmt.setInt(31, Integer.parseInt(details[1].toString()));

            calstmt.executeUpdate();
            calstmt.close();

            calstmt = con.prepareCall("{Call SP_tblAULAF_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            calstmt.setString(1, code);
            calstmt.setInt(2, amputationdto.getUpper_MPIndex_right());
            calstmt.setInt(3, amputationdto.getUpper_MPIndex_left());
            calstmt.setInt(4, amputationdto.getUpper_MPMiddle_right());
            calstmt.setInt(5, amputationdto.getUpper_MPMiddle_left());
            calstmt.setInt(6, amputationdto.getUpper_MPRing_right());
            calstmt.setInt(7, amputationdto.getUpper_MPRing_left());
            calstmt.setInt(8, amputationdto.getUpper_MPLittle_right());
            calstmt.setInt(9, amputationdto.getUpper_MPLittle_left());
            calstmt.setInt(10, amputationdto.getUpper_PIPIndex_right());
            calstmt.setInt(11, amputationdto.getUpper_PIPIndex_left());
            calstmt.setInt(12, amputationdto.getUpper_PIPMiddle_right());
            calstmt.setInt(13, amputationdto.getUpper_PIPMiddle_left());
            calstmt.setInt(14, amputationdto.getUpper_PIPRing_right());
            calstmt.setInt(15, amputationdto.getUpper_PIPRing_left());
            calstmt.setInt(16, amputationdto.getUpper_PIPLittle_right());
            calstmt.setInt(17, amputationdto.getUpper_PIPLittle_left());
            calstmt.setInt(18, amputationdto.getUpper_DIPIndex_right());
            calstmt.setInt(19, amputationdto.getUpper_DIPIndex_left());
            calstmt.setInt(20, amputationdto.getUpper_DIPMiddle_right());
            calstmt.setInt(21, amputationdto.getUpper_DIPMiddle_left());
            calstmt.setInt(22, amputationdto.getUpper_DIPRing_right());
            calstmt.setInt(23, amputationdto.getUpper_DIPRing_left());
            calstmt.setInt(24, amputationdto.getUpper_DIPLittle_right());
            calstmt.setInt(25, amputationdto.getUpper_DIPLittle_left());
            calstmt.setString(26, amputationdto.getLoginid());
            calstmt.setString(27, amputationdto.getSystemIP());

            calstmt.setInt(28, Integer.parseInt(details[0].toString()));
            calstmt.setInt(29, Integer.parseInt(details[1].toString()));

            calstmt.executeUpdate();
            calstmt.close();

            calstmt = con.prepareCall("{Call SP_tblALLA_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            calstmt.setString(1, code);
            calstmt.setInt(2, amputationdto.getLower_hind_right());
            calstmt.setInt(3, amputationdto.getLower_hind_left());
            calstmt.setInt(4, amputationdto.getLower_hip_right());
            calstmt.setInt(5, amputationdto.getLower_hip_left());
            calstmt.setInt(6, amputationdto.getLower_AKupper_right());
            calstmt.setInt(7, amputationdto.getLower_AKupper_left());
            calstmt.setInt(8, amputationdto.getLower_AKlower_right());
            calstmt.setInt(9, amputationdto.getLower_AKlower_left());
            calstmt.setInt(10, amputationdto.getLower_truknee_right());
            calstmt.setInt(11, amputationdto.getLower_truknee_left());
            calstmt.setInt(12, amputationdto.getLower_bk8cm_right());
            calstmt.setInt(13, amputationdto.getLower_bk8cm_left());
            calstmt.setInt(14, amputationdto.getLower_bklower_right());
            calstmt.setInt(15, amputationdto.getLower_bklower_left());
            calstmt.setInt(16, amputationdto.getLower_truankle_right());
            calstmt.setInt(17, amputationdto.getLower_truankle_left());
            calstmt.setInt(18, amputationdto.getLower_symes_right());
            calstmt.setInt(19, amputationdto.getLower_symes_left());
            calstmt.setInt(20, amputationdto.getLower_uptomid_right());
            calstmt.setInt(21, amputationdto.getLower_uptomid_left());
            calstmt.setInt(22, amputationdto.getLower_uptofore_right());
            calstmt.setInt(23, amputationdto.getLower_uptofore_left());
            calstmt.setInt(24, amputationdto.getLower_alltoe_right());
            calstmt.setInt(25, amputationdto.getLower_alltoe_left());
            calstmt.setInt(26, amputationdto.getLower_1sttoe_right());
            calstmt.setInt(27, amputationdto.getLower_1sttoe_left());
            calstmt.setInt(28, amputationdto.getLower_2ndtoe_right());
            calstmt.setInt(29, amputationdto.getLower_2ndtoe_left());
            calstmt.setInt(30, amputationdto.getLower_3rdtoe_right());
            calstmt.setInt(31, amputationdto.getLower_3rdtoe_left());
            calstmt.setInt(32, amputationdto.getLower_4thtoe_right());
            calstmt.setInt(33, amputationdto.getLower_4thtoe_left());
            calstmt.setInt(34, amputationdto.getLower_5thtoe_right());
            calstmt.setInt(35, amputationdto.getLower_5thtoe_left());
            calstmt.setDouble(36, amputationdto.getLoweramputation());
            calstmt.setString(37, amputationdto.getLoginid());
            calstmt.setString(38, amputationdto.getSystemIP());


            calstmt.setInt(39, Integer.parseInt(details[0].toString()));
            calstmt.setInt(40, Integer.parseInt(details[1].toString()));

            calstmt.executeUpdate();
            calstmt.close();


            calstmt = con.prepareCall("{Call SP_tblAmputaion_Complication_Details_Insert(?,?,?,?,?,?,?,?,?,?,?)}");
            calstmt.setString(1, code);
            calstmt.setInt(2, amputationdto.getFitting_of_prosthesis());
            calstmt.setInt(3, amputationdto.getProximal_joint());
            calstmt.setInt(4, amputationdto.getNeuroma());
            calstmt.setInt(5, amputationdto.getInfection());
            calstmt.setInt(6, amputationdto.getDominant());
            calstmt.setDouble(7, amputationdto.getComplicationstotal());
            calstmt.setString(8, amputationdto.getLoginid());
            calstmt.setString(9, amputationdto.getSystemIP());

            calstmt.setInt(10, Integer.parseInt(details[0].toString()));
            calstmt.setInt(11, Integer.parseInt(details[1].toString()));

            calstmt.executeUpdate();
            calstmt.close();

            con.commit();
            con.setAutoCommit(true);

        } catch (SQLException sqlEx) {
            transactionService.updateTransactionDetails(ds, "Error in inserting Amputation Details", code);
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertAmputationDetails", "AmputationDao", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AmputationDao", "insertAmputationDetails");
        } catch (Exception sqlEx) {
            transactionService.updateTransactionDetails(ds, "Error in inserting Amputation Details", code);
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertAmputationDetails", "AmputationDao", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AmputationDao", "insertAmputationDetails");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeStatement(calstmt);

        }

    }

    /**
     *
     * @param ds
     * @param amputationdto
     * @throws org.bf.disability.Exception.SADAREMException
     */
    public int insertAmputationDetailsAU(DataSource ds, AmputationDto amputationdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int flag_UpperLimb = 0;
        int i = 0;
        Connection con = null;
        CallableStatement calstmt = null;

        String code = amputationdto.getPersoncode();
        //  HttpSession session = request.getSession();
      //  Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {

            /** Getting the categoryid,categorycount */
            details = dao.getCategoryDetails(ds, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);


          //  stmt = con.createStatement();
            pstmt=con.prepareStatement("select * from tblAmputation_UpperLimbAmputation_Details where Person_Code=?");
            pstmt.setString(1, code);
            
            rs = pstmt.executeQuery();
            if (!rs.next()) {

                calstmt = con.prepareCall("{Call SP_tblAULA_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                calstmt.setString(1, code);
                calstmt.setInt(2, amputationdto.getUpper_fore_right());
                calstmt.setInt(3, amputationdto.getUpper_fore_left());
                calstmt.setInt(4, amputationdto.getUpper_shoulder_right());
                calstmt.setInt(5, amputationdto.getUpper_shoulder_left());
                calstmt.setInt(6, amputationdto.getUpper_aboveelbowupper_right());
                calstmt.setInt(7, amputationdto.getUpper_aboveelbowupper_left());
                calstmt.setInt(8, amputationdto.getUpper_elbowlower_right());
                calstmt.setInt(9, amputationdto.getUpper_elbowlower_left());
                calstmt.setInt(10, amputationdto.getUpper_elbowdis_right());
                calstmt.setInt(11, amputationdto.getUpper_elbowdis_left());
                calstmt.setInt(12, amputationdto.getUpper_belowelbowupper_right());
                calstmt.setInt(13, amputationdto.getUpper_belowelbowupper_left());
                calstmt.setInt(14, amputationdto.getUpper_belowelbowlower_right());
                calstmt.setInt(15, amputationdto.getUpper_belowelbowlower_left());
                calstmt.setInt(16, amputationdto.getUpper_waistdis_right());
                calstmt.setInt(17, amputationdto.getUpper_waistdis_left());
                calstmt.setInt(18, amputationdto.getUpper_handcarpel_right());
                calstmt.setInt(19, amputationdto.getUpper_handcarpel_left());
                calstmt.setInt(20, amputationdto.getUpper_thumbCM_right());
                calstmt.setInt(21, amputationdto.getUpper_thumbCM_left());
                calstmt.setInt(22, amputationdto.getUpper_thumbMCP_right());
                calstmt.setInt(23, amputationdto.getUpper_thumbMCP_left());
                calstmt.setInt(24, amputationdto.getUpper_thumbIP_right());
                calstmt.setInt(25, amputationdto.getUpper_thumbIP_left());
                calstmt.setDouble(26, amputationdto.getUpperamputation());
                calstmt.setDouble(27, amputationdto.getAmputationtotal());
                calstmt.setString(28, amputationdto.getLoginid());
                calstmt.setString(29, amputationdto.getSystemIP());

                calstmt.setInt(30, Integer.parseInt(details[0].toString()));
                calstmt.setInt(31, Integer.parseInt(details[1].toString()));

                calstmt.executeUpdate();
                calstmt.close();

                calstmt = con.prepareCall("{Call SP_tblAULAF_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                calstmt.setString(1, code);
                calstmt.setInt(2, amputationdto.getUpper_MPIndex_right());
                calstmt.setInt(3, amputationdto.getUpper_MPIndex_left());
                calstmt.setInt(4, amputationdto.getUpper_MPMiddle_right());
                calstmt.setInt(5, amputationdto.getUpper_MPMiddle_left());
                calstmt.setInt(6, amputationdto.getUpper_MPRing_right());
                calstmt.setInt(7, amputationdto.getUpper_MPRing_left());
                calstmt.setInt(8, amputationdto.getUpper_MPLittle_right());
                calstmt.setInt(9, amputationdto.getUpper_MPLittle_left());
                calstmt.setInt(10, amputationdto.getUpper_PIPIndex_right());
                calstmt.setInt(11, amputationdto.getUpper_PIPIndex_left());
                calstmt.setInt(12, amputationdto.getUpper_PIPMiddle_right());
                calstmt.setInt(13, amputationdto.getUpper_PIPMiddle_left());
                calstmt.setInt(14, amputationdto.getUpper_PIPRing_right());
                calstmt.setInt(15, amputationdto.getUpper_PIPRing_left());
                calstmt.setInt(16, amputationdto.getUpper_PIPLittle_right());
                calstmt.setInt(17, amputationdto.getUpper_PIPLittle_left());
                calstmt.setInt(18, amputationdto.getUpper_DIPIndex_right());
                calstmt.setInt(19, amputationdto.getUpper_DIPIndex_left());
                calstmt.setInt(20, amputationdto.getUpper_DIPMiddle_right());
                calstmt.setInt(21, amputationdto.getUpper_DIPMiddle_left());
                calstmt.setInt(22, amputationdto.getUpper_DIPRing_right());
                calstmt.setInt(23, amputationdto.getUpper_DIPRing_left());
                calstmt.setInt(24, amputationdto.getUpper_DIPLittle_right());
                calstmt.setInt(25, amputationdto.getUpper_DIPLittle_left());
                calstmt.setString(26, amputationdto.getLoginid());
                calstmt.setString(27, amputationdto.getSystemIP());

                calstmt.setInt(28, Integer.parseInt(details[0].toString()));
                calstmt.setInt(29, Integer.parseInt(details[1].toString()));

                calstmt.executeUpdate();
                calstmt.close();

                calstmt = con.prepareCall("{Call SP_tblALLA_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                calstmt.setString(1, code);
                calstmt.setInt(2, amputationdto.getLower_hind_right());
                calstmt.setInt(3, amputationdto.getLower_hind_left());
                calstmt.setInt(4, amputationdto.getLower_hip_right());
                calstmt.setInt(5, amputationdto.getLower_hip_left());
                calstmt.setInt(6, amputationdto.getLower_AKupper_right());
                calstmt.setInt(7, amputationdto.getLower_AKupper_left());
                calstmt.setInt(8, amputationdto.getLower_AKlower_right());
                calstmt.setInt(9, amputationdto.getLower_AKlower_left());
                calstmt.setInt(10, amputationdto.getLower_truknee_right());
                calstmt.setInt(11, amputationdto.getLower_truknee_left());
                calstmt.setInt(12, amputationdto.getLower_bk8cm_right());
                calstmt.setInt(13, amputationdto.getLower_bk8cm_left());
                calstmt.setInt(14, amputationdto.getLower_bklower_right());
                calstmt.setInt(15, amputationdto.getLower_bklower_left());
                calstmt.setInt(16, amputationdto.getLower_truankle_right());
                calstmt.setInt(17, amputationdto.getLower_truankle_left());
                calstmt.setInt(18, amputationdto.getLower_symes_right());
                calstmt.setInt(19, amputationdto.getLower_symes_left());
                calstmt.setInt(20, amputationdto.getLower_uptomid_right());
                calstmt.setInt(21, amputationdto.getLower_uptomid_left());
                calstmt.setInt(22, amputationdto.getLower_uptofore_right());
                calstmt.setInt(23, amputationdto.getLower_uptofore_left());
                calstmt.setInt(24, amputationdto.getLower_alltoe_right());
                calstmt.setInt(25, amputationdto.getLower_alltoe_left());
                calstmt.setInt(26, amputationdto.getLower_1sttoe_right());
                calstmt.setInt(27, amputationdto.getLower_1sttoe_left());
                calstmt.setInt(28, amputationdto.getLower_2ndtoe_right());
                calstmt.setInt(29, amputationdto.getLower_2ndtoe_left());
                calstmt.setInt(30, amputationdto.getLower_3rdtoe_right());
                calstmt.setInt(31, amputationdto.getLower_3rdtoe_left());
                calstmt.setInt(32, amputationdto.getLower_4thtoe_right());
                calstmt.setInt(33, amputationdto.getLower_4thtoe_left());
                calstmt.setInt(34, amputationdto.getLower_5thtoe_right());
                calstmt.setInt(35, amputationdto.getLower_5thtoe_left());
                calstmt.setDouble(36, amputationdto.getLoweramputation());
                calstmt.setString(37, amputationdto.getLoginid());
                calstmt.setString(38, amputationdto.getSystemIP());

                calstmt.setInt(39, Integer.parseInt(details[0].toString()));
                calstmt.setInt(40, Integer.parseInt(details[1].toString()));

                calstmt.executeUpdate();
                calstmt.close();



                calstmt = con.prepareCall("{Call SP_tblAmputaion_Complication_Details_Insert(?,?,?,?,?,?,?,?,?,?,?)}");
                calstmt.setString(1, code);
                calstmt.setInt(2, amputationdto.getFitting_of_prosthesis());
                calstmt.setInt(3, amputationdto.getProximal_joint());
                calstmt.setInt(4, amputationdto.getNeuroma());
                calstmt.setInt(5, amputationdto.getInfection());
                calstmt.setInt(6, amputationdto.getDominant());
                calstmt.setDouble(7, amputationdto.getComplicationstotal());
                calstmt.setString(8, amputationdto.getLoginid());
                calstmt.setString(9, amputationdto.getSystemIP());

                calstmt.setInt(10, Integer.parseInt(details[0].toString()));
                calstmt.setInt(11, Integer.parseInt(details[1].toString()));

                calstmt.executeUpdate();
                calstmt.close();

                con.commit();
                con.setAutoCommit(true);
            } else {
                i = -1;
            }

        } catch (SQLException sqlEx) {
            transactionService.updateTransactionDetails(ds, "Error in inserting Amputation Details", code);
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertAmputationDetailsAU", "AmputationDao", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AmputationDao", "insertAmputationDetailsAU");
        } catch (Exception sqlEx) {
            transactionService.updateTransactionDetails(ds, "Error in inserting Amputation Details", code);
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertAmputationDetailsAU", "AmputationDao", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AmputationDao", "insertAmputationDetailsAU");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeStatement(calstmt); 
           DBConnection.closeStatement(pstmt);

        }
        return i;
    }

    /**
     * 
     * @param personCode 
     * @param ds 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return lsit
     */
    public AmputationDto getAmputationDetails(String personCode, DataSource ds) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement calstmt = null;
        ResultSet rs = null;
        AmputationDto ampdto = new AmputationDto();
        try {
            con = DBConnection.getConnection();
            calstmt = con.prepareCall("{Call SP_tblAULA_Details_Select(?)}");
            calstmt.setString(1, personCode);
            rs = calstmt.executeQuery();
            while (rs.next()) {
                ampdto.setUpper_fore_right(rs.getInt(1));
                ampdto.setUpper_fore_left(rs.getInt(2));
                ampdto.setUpper_shoulder_right(rs.getInt(3));
                ampdto.setUpper_shoulder_left(rs.getInt(4));
                ampdto.setUpper_aboveelbowupper_right(rs.getInt(5));
                ampdto.setUpper_aboveelbowupper_left(rs.getInt(6));
                ampdto.setUpper_elbowlower_right(rs.getInt(7));
                ampdto.setUpper_elbowlower_left(rs.getInt(8));
                ampdto.setUpper_elbowdis_right(rs.getInt(9));
                ampdto.setUpper_elbowdis_left(rs.getInt(10));
                ampdto.setUpper_belowelbowupper_right(rs.getInt(11));
                ampdto.setUpper_belowelbowupper_left(rs.getInt(12));
                ampdto.setUpper_belowelbowlower_right(rs.getInt(13));
                ampdto.setUpper_belowelbowlower_left(rs.getInt(14));
                ampdto.setUpper_waistdis_right(rs.getInt(15));
                ampdto.setUpper_waistdis_left(rs.getInt(16));
                ampdto.setUpper_handcarpel_right(rs.getInt(17));
                ampdto.setUpper_handcarpel_left(rs.getInt(18));
                ampdto.setUpper_thumbCM_right(rs.getInt(19));
                ampdto.setUpper_thumbCM_left(rs.getInt(20));
                ampdto.setUpper_thumbMCP_right(rs.getInt(21));
                ampdto.setUpper_thumbMCP_left(rs.getInt(22));
                ampdto.setUpper_thumbIP_right(rs.getInt(23));
                ampdto.setUpper_thumbIP_left(rs.getInt(24));
                ampdto.setUpperamputation(rs.getDouble(25));
                ampdto.setAmputationtotal(rs.getDouble(26));
            }
            rs.close();
            calstmt.close();

            calstmt = con.prepareCall("{Call SP_tblAULAF_Details_Select(?)}");
            calstmt.setString(1, personCode);
            rs = calstmt.executeQuery();
            while (rs.next()) {
                ampdto.setUpper_MPIndex_right(rs.getInt(1));
                ampdto.setUpper_MPIndex_left(rs.getInt(2));
                ampdto.setUpper_MPMiddle_right(rs.getInt(3));
                ampdto.setUpper_MPMiddle_left(rs.getInt(4));
                ampdto.setUpper_MPRing_right(rs.getInt(5));
                ampdto.setUpper_MPRing_left(rs.getInt(6));
                ampdto.setUpper_MPLittle_right(rs.getInt(7));
                ampdto.setUpper_MPLittle_left(rs.getInt(8));
                ampdto.setUpper_PIPIndex_right(rs.getInt(9));
                ampdto.setUpper_PIPIndex_left(rs.getInt(10));
                ampdto.setUpper_PIPMiddle_right(rs.getInt(11));
                ampdto.setUpper_PIPMiddle_left(rs.getInt(12));
                ampdto.setUpper_PIPRing_right(rs.getInt(13));
                ampdto.setUpper_PIPRing_left(rs.getInt(14));
                ampdto.setUpper_PIPLittle_right(rs.getInt(15));
                ampdto.setUpper_PIPLittle_left(rs.getInt(16));
                ampdto.setUpper_DIPIndex_right(rs.getInt(17));
                ampdto.setUpper_DIPIndex_left(rs.getInt(18));
                ampdto.setUpper_DIPMiddle_right(rs.getInt(19));
                ampdto.setUpper_DIPMiddle_left(rs.getInt(20));
                ampdto.setUpper_DIPRing_right(rs.getInt(21));
                ampdto.setUpper_DIPRing_left(rs.getInt(22));
                ampdto.setUpper_DIPLittle_right(rs.getInt(23));
                ampdto.setUpper_DIPLittle_left(rs.getInt(24));
            }
            rs.close();
            calstmt.close();

            calstmt = con.prepareCall("{Call SP_tblALLA_Details_Select(?)}");
            calstmt.setString(1, personCode);
            rs = calstmt.executeQuery();
            while (rs.next()) {

                ampdto.setLower_hind_right(rs.getInt(1));
                ampdto.setLower_hind_left(rs.getInt(2));
                ampdto.setLower_hip_right(rs.getInt(3));
                ampdto.setLower_hip_left(rs.getInt(4));
                ampdto.setLower_AKupper_right(rs.getInt(5));
                ampdto.setLower_AKupper_left(rs.getInt(6));
                ampdto.setLower_AKlower_right(rs.getInt(7));
                ampdto.setLower_AKlower_left(rs.getInt(8));
                ampdto.setLower_truknee_right(rs.getInt(9));
                ampdto.setLower_truknee_left(rs.getInt(10));
                ampdto.setLower_bk8cm_right(rs.getInt(11));
                ampdto.setLower_bk8cm_left(rs.getInt(12));
                ampdto.setLower_bklower_right(rs.getInt(13));
                ampdto.setLower_bklower_left(rs.getInt(14));
                ampdto.setLower_truankle_right(rs.getInt(15));
                ampdto.setLower_truankle_left(rs.getInt(16));
                ampdto.setLower_symes_right(rs.getInt(17));
                ampdto.setLower_symes_left(rs.getInt(18));
                ampdto.setLower_uptomid_right(rs.getInt(19));
                ampdto.setLower_uptomid_left(rs.getInt(20));
                ampdto.setLower_uptofore_right(rs.getInt(21));
                ampdto.setLower_uptofore_left(rs.getInt(22));
                ampdto.setLower_alltoe_right(rs.getInt(23));
                ampdto.setLower_alltoe_left(rs.getInt(24));
                ampdto.setLower_1sttoe_right(rs.getInt(25));
                ampdto.setLower_1sttoe_left(rs.getInt(26));
                ampdto.setLower_2ndtoe_right(rs.getInt(27));
                ampdto.setLower_2ndtoe_left(rs.getInt(28));
                ampdto.setLower_3rdtoe_right(rs.getInt(29));
                ampdto.setLower_3rdtoe_left(rs.getInt(30));
                ampdto.setLower_4thtoe_right(rs.getInt(31));
                ampdto.setLower_4thtoe_left(rs.getInt(32));
                ampdto.setLower_5thtoe_right(rs.getInt(33));
                ampdto.setLower_5thtoe_left(rs.getInt(34));
                ampdto.setLoweramputation(rs.getDouble(35));
            }

            rs.close();
            calstmt.close();

            calstmt = con.prepareCall("{Call SP_tblAmputaion_Complication_Details_Select(?)}");
            calstmt.setString(1, personCode);
            rs = calstmt.executeQuery();

            while (rs.next()) {
                ampdto.setFitting_of_prosthesis(rs.getInt(1));
                ampdto.setProximal_joint(rs.getInt(2));
                ampdto.setNeuroma(rs.getInt(3));
                ampdto.setInfection(rs.getInt(4));
                ampdto.setDominant(rs.getInt(5));
                ampdto.setComplicationstotal(rs.getDouble(6));

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAmputationDetails", "AmputationDao", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AmputationDao", "getAmputationDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAmputationDetails", "AmputationDao", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AmputationDao", "getAmputationDetails");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(calstmt);

        }


        return ampdto;
    }

    /**
     * 
     * @param ds 
     * @param amputationdto 
     * @throws org.bf.disability.Exception.SADAREMException 
     */
    public void updateAmputationDetails(DataSource ds, AmputationDto amputationdto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement calstmt = null;
        HttpSession session = request.getSession();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        String code = amputationdto.getPersoncode();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            details = dao.getCategoryDetails(ds, amputationdto.getPersoncode());
            con = DBConnection.getConnection();
            con.setAutoCommit(false);



            calstmt = con.prepareCall("{Call SP_tblAULA_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            calstmt.setString(1, amputationdto.getPersoncode());
            calstmt.setInt(2, amputationdto.getUpper_fore_right());
            calstmt.setInt(3, amputationdto.getUpper_fore_left());
            calstmt.setInt(4, amputationdto.getUpper_shoulder_right());
            calstmt.setInt(5, amputationdto.getUpper_shoulder_left());
            calstmt.setInt(6, amputationdto.getUpper_aboveelbowupper_right());
            calstmt.setInt(7, amputationdto.getUpper_aboveelbowupper_left());
            calstmt.setInt(8, amputationdto.getUpper_elbowlower_right());
            calstmt.setInt(9, amputationdto.getUpper_elbowlower_left());
            calstmt.setInt(10, amputationdto.getUpper_elbowdis_right());
            calstmt.setInt(11, amputationdto.getUpper_elbowdis_left());
            calstmt.setInt(12, amputationdto.getUpper_belowelbowupper_right());
            calstmt.setInt(13, amputationdto.getUpper_belowelbowupper_left());
            calstmt.setInt(14, amputationdto.getUpper_belowelbowlower_right());
            calstmt.setInt(15, amputationdto.getUpper_belowelbowlower_left());
            calstmt.setInt(16, amputationdto.getUpper_waistdis_right());
            calstmt.setInt(17, amputationdto.getUpper_waistdis_left());
            calstmt.setInt(18, amputationdto.getUpper_handcarpel_right());
            calstmt.setInt(19, amputationdto.getUpper_handcarpel_left());
            calstmt.setInt(20, amputationdto.getUpper_thumbCM_right());
            calstmt.setInt(21, amputationdto.getUpper_thumbCM_left());
            calstmt.setInt(22, amputationdto.getUpper_thumbMCP_right());
            calstmt.setInt(23, amputationdto.getUpper_thumbMCP_left());
            calstmt.setInt(24, amputationdto.getUpper_thumbIP_right());
            calstmt.setInt(25, amputationdto.getUpper_thumbIP_left());
            calstmt.setDouble(26, amputationdto.getUpperamputation());
            calstmt.setDouble(27, amputationdto.getAmputationtotal());
            calstmt.setString(28, amputationdto.getLoginid());
            calstmt.setString(29, amputationdto.getSystemIP());

            calstmt.setInt(30, Integer.parseInt(details[0].toString()));
            calstmt.setInt(31, Integer.parseInt(details[1].toString()));
            calstmt.executeUpdate();

            calstmt = con.prepareCall("{Call SP_tblAULAF_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            calstmt.setString(1, amputationdto.getPersoncode());
            calstmt.setInt(2, amputationdto.getUpper_MPIndex_right());
            calstmt.setInt(3, amputationdto.getUpper_MPIndex_left());
            calstmt.setInt(4, amputationdto.getUpper_MPMiddle_right());
            calstmt.setInt(5, amputationdto.getUpper_MPMiddle_left());
            calstmt.setInt(6, amputationdto.getUpper_MPRing_right());
            calstmt.setInt(7, amputationdto.getUpper_MPRing_left());
            calstmt.setInt(8, amputationdto.getUpper_MPLittle_right());
            calstmt.setInt(9, amputationdto.getUpper_MPLittle_left());
            calstmt.setInt(10, amputationdto.getUpper_PIPIndex_right());
            calstmt.setInt(11, amputationdto.getUpper_PIPIndex_left());
            calstmt.setInt(12, amputationdto.getUpper_PIPMiddle_right());
            calstmt.setInt(13, amputationdto.getUpper_PIPMiddle_left());
            calstmt.setInt(14, amputationdto.getUpper_PIPRing_right());
            calstmt.setInt(15, amputationdto.getUpper_PIPRing_left());
            calstmt.setInt(16, amputationdto.getUpper_PIPLittle_right());
            calstmt.setInt(17, amputationdto.getUpper_PIPLittle_left());
            calstmt.setInt(18, amputationdto.getUpper_DIPIndex_right());
            calstmt.setInt(19, amputationdto.getUpper_DIPIndex_left());
            calstmt.setInt(20, amputationdto.getUpper_DIPMiddle_right());
            calstmt.setInt(21, amputationdto.getUpper_DIPMiddle_left());
            calstmt.setInt(22, amputationdto.getUpper_DIPRing_right());
            calstmt.setInt(23, amputationdto.getUpper_DIPRing_left());
            calstmt.setInt(24, amputationdto.getUpper_DIPLittle_right());
            calstmt.setInt(25, amputationdto.getUpper_DIPLittle_left());
            calstmt.setString(26, amputationdto.getLoginid());
            calstmt.setString(27, amputationdto.getSystemIP());

            calstmt.setInt(28, Integer.parseInt(details[0].toString()));
            calstmt.setInt(29, Integer.parseInt(details[1].toString()));
            calstmt.executeUpdate();

            calstmt = con.prepareCall("{Call SP_tblALLA_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            calstmt.setString(1, amputationdto.getPersoncode());
            calstmt.setInt(2, amputationdto.getLower_hind_right());
            calstmt.setInt(3, amputationdto.getLower_hind_left());
            calstmt.setInt(4, amputationdto.getLower_hip_right());
            calstmt.setInt(5, amputationdto.getLower_hip_left());
            calstmt.setInt(6, amputationdto.getLower_AKupper_right());
            calstmt.setInt(7, amputationdto.getLower_AKupper_left());
            calstmt.setInt(8, amputationdto.getLower_AKlower_right());
            calstmt.setInt(9, amputationdto.getLower_AKlower_left());
            calstmt.setInt(10, amputationdto.getLower_truknee_right());
            calstmt.setInt(11, amputationdto.getLower_truknee_left());
            calstmt.setInt(12, amputationdto.getLower_bk8cm_right());
            calstmt.setInt(13, amputationdto.getLower_bk8cm_left());
            calstmt.setInt(14, amputationdto.getLower_bklower_right());
            calstmt.setInt(15, amputationdto.getLower_bklower_left());
            calstmt.setInt(16, amputationdto.getLower_truankle_right());
            calstmt.setInt(17, amputationdto.getLower_truankle_left());
            calstmt.setInt(18, amputationdto.getLower_symes_right());
            calstmt.setInt(19, amputationdto.getLower_symes_left());
            calstmt.setInt(20, amputationdto.getLower_uptomid_right());
            calstmt.setInt(21, amputationdto.getLower_uptomid_left());
            calstmt.setInt(22, amputationdto.getLower_uptofore_right());
            calstmt.setInt(23, amputationdto.getLower_uptofore_left());
            calstmt.setInt(24, amputationdto.getLower_alltoe_right());
            calstmt.setInt(25, amputationdto.getLower_alltoe_left());
            calstmt.setInt(26, amputationdto.getLower_1sttoe_right());
            calstmt.setInt(27, amputationdto.getLower_1sttoe_left());
            calstmt.setInt(28, amputationdto.getLower_2ndtoe_right());
            calstmt.setInt(29, amputationdto.getLower_2ndtoe_left());
            calstmt.setInt(30, amputationdto.getLower_3rdtoe_right());
            calstmt.setInt(31, amputationdto.getLower_3rdtoe_left());
            calstmt.setInt(32, amputationdto.getLower_4thtoe_right());
            calstmt.setInt(33, amputationdto.getLower_4thtoe_left());
            calstmt.setInt(34, amputationdto.getLower_5thtoe_right());
            calstmt.setInt(35, amputationdto.getLower_5thtoe_left());
            calstmt.setDouble(36, amputationdto.getLoweramputation());
            calstmt.setString(37, amputationdto.getLoginid());
            calstmt.setString(38, amputationdto.getSystemIP());

            calstmt.setInt(39, Integer.parseInt(details[0].toString()));
            calstmt.setInt(40, Integer.parseInt(details[1].toString()));
            calstmt.executeUpdate();

            calstmt = con.prepareCall("{Call SP_tblAmputaion_Complication_Details_Update(?,?,?,?,?,?,?,?,?,?,?)}");
            calstmt.setString(1, amputationdto.getPersoncode());
            calstmt.setInt(2, amputationdto.getFitting_of_prosthesis());
            calstmt.setInt(3, amputationdto.getProximal_joint());
            calstmt.setInt(4, amputationdto.getNeuroma());
            calstmt.setInt(5, amputationdto.getInfection());
            calstmt.setInt(6, amputationdto.getDominant());
            calstmt.setDouble(7, amputationdto.getComplicationstotal());
            calstmt.setString(8, amputationdto.getLoginid());
            calstmt.setString(9, amputationdto.getSystemIP());

            calstmt.setInt(10, Integer.parseInt(details[0].toString()));
            calstmt.setInt(11, Integer.parseInt(details[1].toString()));
            calstmt.executeUpdate();

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            transactionService.updateTransactionDetails(ds, "Error in Updating Amputation Details", code);
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateAmputationDetails", "AmputationDao", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AmputationDao", "updateAmputationDetails");
        } catch (Exception sqlEx) {
            transactionService.updateTransactionDetails(ds, "Error in Updating Amputation Details", code);
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateAmputationDetails", "AmputationDao", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AmputationDao", "updateAmputationDetails");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeStatement(calstmt);



        }
    }

    /**
     * 
     * @param personcode 
     * @param ds 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return value
     */
    public boolean checkPersoncode(String personcode, DataSource ds) throws SADAREMDBException, SQLException {
        boolean personcodecheckflag = false;
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        PreparedStatement pstmt = null ;
        try {
            con = DBConnection.getConnection();
            String query = "select Person_Code from tblAmputation_UpperLimbAmputation_Details where Person_Code=? and status='Active'";             
            
            pstmt = con.prepareStatement(query); 
            pstmt.setString(1,personcode );            
            rs = pstmt.executeQuery();
            
            if (rs.next() == false) {
                personcodecheckflag = false;
            } else {
                personcodecheckflag = true;
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkPersoncode", "AmputationDao", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AmputationDao", "checkPersoncode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkPersoncode", "AmputationDao", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AmputationDao", "checkPersoncode");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(pstmt);

        }
        return personcodecheckflag;
    }

    /**
     * 
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     */
    public void deleteAmputaionUpdateRecord(DataSource ds, String personcode) throws SADAREMDBException, SQLException {



        Connection con = null;
        Statement stmt = null;

        try {
            CommonDAO.changeStatusToInactiveForResetInUpdate(ds, personcode, "tblAmputation_UpperLimbAmputation_Details");
            CommonDAO.changeStatusToInactiveForResetInUpdate(ds, personcode, "tblAmputaion_UpperLimbAmputation_Finger_Details");
            CommonDAO.changeStatusToInactiveForResetInUpdate(ds, personcode, "tblAmputation_LowerLimbAmputation_Details");
            CommonDAO.changeStatusToInactiveForResetInUpdate(ds, personcode, "tblAmputation_Complication_Details");
            con = DBConnection.getConnection();

            con.setAutoCommit(false);
            stmt = con.createStatement();
            // stmt.execute(queryDeleteAmpUpperLimb);
            // stmt.execute(queryDeleteAmpUpperFingers);
            // stmt.execute(queryDeleteAmpLowerLimb);
            // stmt.execute(queryDeleteAmpComplications);
            con.commit();
            con.setAutoCommit(true);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "deleteAmputaionUpdateRecord", "AmputationDao", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AmputationDao", "deleteAmputaionUpdateRecord");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "deleteAmputaionUpdateRecord", "AmputationDao", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AmputationDao", "deleteAmputaionUpdateRecord");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeStatement(stmt);


        }

    }
}
