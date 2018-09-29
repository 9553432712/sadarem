/*
 * TransverseDAO.java
 *
 * Created on July 1, 2008, 10:57 AM
 */
package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.TransverseDTO;

import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.service.TransactionService;
import org.bf.disability.servicefactory.TransactionFactory;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author demo
 */
/**
 * This class deals with the Database connectivity, 
 * executing database procedures and 
 * return responses to the services methods accordingly.
 */
public class TransverseDAO {

    /**
     * This method deals with the insertion of Form values into the database.
     * 
     * @return int
     * @param datasource DataSource Object
     * @param transversedto TransverseDTO object
     * @throws org.bf.disability.Exception.SADAREMException 
     * @throws java.sql.SQLException 
     */
    public int insertTransverseDetails(DataSource datasource, TransverseDTO transversedto, HttpServletRequest request)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cs = null;
        //Statement st=null;

        String sql = null;
        String code = transversedto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(datasource, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);


            cs = con.prepareCall("{Call SP_CDL_Transverse_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, code);
            cs.setInt(2, transversedto.getShoulderdisarticulationrightside());
            cs.setInt(3, transversedto.getShoulderdisarticulationleftside());
            cs.setInt(4, transversedto.getHipdisarticulationrightside());
            cs.setInt(5, transversedto.getHipdisarticulationleftside());
            cs.setInt(6, transversedto.getAboveelbowamputeerightside());
            cs.setInt(7, transversedto.getAboveelbowamputeeleftside());
            cs.setInt(8, transversedto.getKneeamputeerightside());
            cs.setInt(9, transversedto.getKneeamputeeleftside());
            cs.setInt(10, transversedto.getElbowdisarticulationrightside());
            cs.setInt(11, transversedto.getElbowdisarticulationleftside());
            cs.setInt(12, transversedto.getBelowelbowamputeerightside());
            cs.setInt(13, transversedto.getBelowelbowamputeeleftside());
            cs.setInt(14, transversedto.getWristdisarticulationrightside());
            cs.setInt(15, transversedto.getWristdisarticulationleftside());
            cs.setInt(16, transversedto.getCarpalbonesrightside());
            cs.setInt(17, transversedto.getCarpalbonesleftside());
            cs.setDouble(18, transversedto.getFinalres());
            cs.setString(19, transversedto.getLoginid());
            cs.setString(20, transversedto.getSystemip());

            cs.setInt(21, Integer.parseInt(details[0].toString()));
            cs.setInt(22, Integer.parseInt(details[1].toString()));

            cs.executeUpdate();
            con.commit();
            con.setAutoCommit(true);

        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(datasource, "Error inserting Transverse Details", code);
            // End
            con.rollback();
            int exResult = ExceptionDAO.saveException(datasource, exception.toString(), "insertTransverseDetails", "TransverseDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransverseDAO", "insertTransverseDetails");
        } finally {
            DBConnection.closeConnection(con);
//           DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cs);

        }
        return 0;
    }//end of insertTransverseDetails()

    /**
     * This method deals with the insertion of Form values into the database.
     *
     * @return int
     * @param datasource DataSource Object
     * @param transversedto TransverseDTO object
     * @throws org.bf.disability.Exception.SADAREMException
     * @throws java.sql.SQLException
     */
    public int insertTransverseDetailsAU(DataSource datasource, TransverseDTO transversedto, HttpServletRequest request)
            throws SADAREMDBException, SQLException, SQLException {
        Connection con = null;
        CallableStatement cs = null;
        //Statement st=null;
        int i = 0;
        String code = transversedto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(datasource, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            pstmt = con.prepareStatement("select * from tblCongenitalDeficienciesLimb_Transverse_Details where Person_Code=? and status='Active'");
            pstmt.setString(1, code);
            rs = pstmt.executeQuery();
            if (!rs.next()) {

                cs = con.prepareCall("{Call SP_CDL_Transverse_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cs.setString(1, code);
                cs.setInt(2, transversedto.getShoulderdisarticulationrightside());
                cs.setInt(3, transversedto.getShoulderdisarticulationleftside());
                cs.setInt(4, transversedto.getHipdisarticulationrightside());
                cs.setInt(5, transversedto.getHipdisarticulationleftside());
                cs.setInt(6, transversedto.getAboveelbowamputeerightside());
                cs.setInt(7, transversedto.getAboveelbowamputeeleftside());
                cs.setInt(8, transversedto.getKneeamputeerightside());
                cs.setInt(9, transversedto.getKneeamputeeleftside());
                cs.setInt(10, transversedto.getElbowdisarticulationrightside());
                cs.setInt(11, transversedto.getElbowdisarticulationleftside());
                cs.setInt(12, transversedto.getBelowelbowamputeerightside());
                cs.setInt(13, transversedto.getBelowelbowamputeeleftside());
                cs.setInt(14, transversedto.getWristdisarticulationrightside());
                cs.setInt(15, transversedto.getWristdisarticulationleftside());
                cs.setInt(16, transversedto.getCarpalbonesrightside());
                cs.setInt(17, transversedto.getCarpalbonesleftside());
                cs.setDouble(18, transversedto.getFinalres());
                cs.setString(19, transversedto.getLoginid());
                cs.setString(20, transversedto.getSystemip());

                cs.setInt(21, Integer.parseInt(details[0].toString()));
                cs.setInt(22, Integer.parseInt(details[1].toString()));

                cs.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            } else {
                i = -1;
            }

        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(datasource, "Error inserting Transverse Details", code);
            // End

            con.rollback();
            int exResult = ExceptionDAO.saveException(datasource, exception.toString(), "insertTransverseDetailsAU", "TransverseDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransverseDAO", "insertTransverseDetailsAU");

        } finally {
            DBConnection.closeConnection(con);
            // DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cs);
            DBConnection.closeStatement(pstmt);

        }
        return i;
    }//end of insertTransverseDetails()

    /**
     * This method deals with the selecting Form values from the database.
     * 
     * @return TransverseDTO
     * @param datasource DataSource Oject
     * @param personcode Person_Code value
     * @throws java.lang.Exception 
     */
    public TransverseDTO getTransverseDetails(DataSource datasource, String personcode)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cs = null;
        //Statement st=null;
        ResultSet rs = null;
        TransverseDTO transverseupdatedto = new TransverseDTO();
        try {
            con = DBConnection.getConnection();

            cs = con.prepareCall("{Call SP_CDL_Transverse_Details_Select(?)}");
            cs.setString(1, personcode);

            rs = cs.executeQuery();

            while (rs.next()) {
                transverseupdatedto.setShoulderdisarticulationrightside(rs.getInt(1));
                transverseupdatedto.setShoulderdisarticulationleftside(rs.getInt(2));

                transverseupdatedto.setHipdisarticulationrightside(rs.getInt(3));
                transverseupdatedto.setHipdisarticulationleftside(rs.getInt(4));

                transverseupdatedto.setAboveelbowamputeerightside(rs.getInt(5));
                transverseupdatedto.setAboveelbowamputeeleftside(rs.getInt(6));

                transverseupdatedto.setKneeamputeerightside(rs.getInt(7));
                transverseupdatedto.setKneeamputeeleftside(rs.getInt(8));

                transverseupdatedto.setElbowdisarticulationrightside(rs.getInt(9));
                transverseupdatedto.setElbowdisarticulationleftside(rs.getInt(10));

                transverseupdatedto.setBelowelbowamputeerightside(rs.getInt(11));
                transverseupdatedto.setBelowelbowamputeeleftside(rs.getInt(12));

                transverseupdatedto.setWristdisarticulationrightside(rs.getInt(13));
                transverseupdatedto.setWristdisarticulationleftside(rs.getInt(14));

                transverseupdatedto.setCarpalbonesrightside(rs.getInt(15));
                transverseupdatedto.setCarpalbonesleftside(rs.getInt(16));

                transverseupdatedto.setFinalres(rs.getInt(17));
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getTransverseDetails", "TransverseDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransverseDAO", "getTransverseDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getTransverseDetails", "TransverseDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransverseDAO", "getTransverseDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cs);
        }
        return transverseupdatedto;
    }//end of getTransverseDetails()

    /**
     * This method deals with the insertion of updated values into the database.
     * 
     * @param datasource DataSource Object
     * @param transverseupdatedto TransverseDTO object
     * @throws org.bf.disability.Exception.SADAREMException 
     * @throws java.sql.SQLException 
     */
    public void updateTransverseDetails(DataSource datasource, TransverseDTO transverseupdatedto, HttpServletRequest request)
            throws SADAREMDBException, SQLException, SQLException {
        Connection con = null;
        CallableStatement cs = null;
        Statement st = null;
        ResultSet rs = null;
        String code = transverseupdatedto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();


        try {

            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(datasource, transverseupdatedto.getPersoncode());
            con = DBConnection.getConnection();
            con.setAutoCommit(false);


            if (transverseupdatedto.getFinalres() == 0) {
//       query="delete from tblCongenitalDeficienciesLimb_Transverse_Details where Person_Code= '" + transverseupdatedto.getPersoncode() + "'";
//  
//       st=con.createStatement();
//
//       int temp=st.executeUpdate(query);
                CommonDAO.changeStatusToInactiveForResetInUpdate(datasource, transverseupdatedto.getPersoncode(), "tblCongenitalDeficienciesLimb_Transverse_Details");

                con.setAutoCommit(true);
                return;
            }


            cs = con.prepareCall("{Call SP_CDL_Transverse_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, transverseupdatedto.getPersoncode());
            cs.setInt(2, transverseupdatedto.getShoulderdisarticulationrightside());
            cs.setInt(3, transverseupdatedto.getShoulderdisarticulationleftside());
            cs.setInt(4, transverseupdatedto.getHipdisarticulationrightside());
            cs.setInt(5, transverseupdatedto.getHipdisarticulationleftside());
            cs.setInt(6, transverseupdatedto.getAboveelbowamputeerightside());
            cs.setInt(7, transverseupdatedto.getAboveelbowamputeeleftside());
            cs.setInt(8, transverseupdatedto.getKneeamputeerightside());
            cs.setInt(9, transverseupdatedto.getKneeamputeeleftside());
            cs.setInt(10, transverseupdatedto.getElbowdisarticulationrightside());
            cs.setInt(11, transverseupdatedto.getElbowdisarticulationleftside());
            cs.setInt(12, transverseupdatedto.getBelowelbowamputeerightside());
            cs.setInt(13, transverseupdatedto.getBelowelbowamputeeleftside());
            cs.setInt(14, transverseupdatedto.getWristdisarticulationrightside());
            cs.setInt(15, transverseupdatedto.getWristdisarticulationleftside());
            cs.setInt(16, transverseupdatedto.getCarpalbonesrightside());
            cs.setInt(17, transverseupdatedto.getCarpalbonesleftside());
            cs.setDouble(18, transverseupdatedto.getFinalres());
            cs.setString(19, transverseupdatedto.getLoginid());
            cs.setString(20, transverseupdatedto.getSystemip());

            cs.setInt(21, Integer.parseInt(details[0].toString()));
            cs.setInt(22, Integer.parseInt(details[1].toString()));
            cs.executeUpdate();

            con.commit();
            con.setAutoCommit(true);
        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(datasource, "Error in Updating TRANSVERSE DEFICIENCIES Details", code);
            // End
            con.rollback();
            int exResult = ExceptionDAO.saveException(datasource, exception.toString(), "updateTransverseDetails", "TransverseDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransverseDAO", "updateTransverseDetails");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(cs);

        }

    }//end of updateTransverseDetails()

    /**
     * 
     * @param datasource 
     * @param personcode 
     * @throws java.lang.Exception 
     * @return int
     */
    public boolean personCode(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        boolean code = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        try {
            con = DBConnection.getConnection();
            String query = "select Person_Code from tblCongenitalDeficienciesLimb_Transverse_Details where Person_Code=? and status='Active'";
            pstmt.setString(1, personcode);
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs.next() == false) {
                code = false;
            } else {
                code = true;
            }

        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "personCode", "TransverseDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransverseDAO", "personCode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "personCode", "TransverseDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransverseDAO", "personCode");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return code;
    }//end of personCode()
}//end of TransverseDAO class

