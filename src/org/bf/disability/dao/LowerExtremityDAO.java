/*
 * LowerExtremityDAO.java
 *
 * Created on June 25, 2008, 12:27 PM
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
import org.bf.disability.dto.LowerExtremityDTO;
import org.bf.disability.service.TransactionService;
import org.bf.disability.servicefactory.TransactionFactory;

import com.tcs.sgv.common.util.DBConnection;

/**
 * This DAO is used for inserting ,retriving and updating into database .
 * @author Bapi Naidu .T
 * @version 1.0
 */
public class LowerExtremityDAO {

    private boolean personcode;
    private Statement stmt;

    /**
     * this method is used for insert into SP_LEMC_ROM_Details_Insert,SP_LEM_MuscleStrength_Details_Insert,
     * 
     * @return lowerextremitydto
     * @param datasource 
     * @param lowerextremitydto 
     * @throws org.bf.disability.Exception.SADAREMException 
     */
    public int insertLowerExtremityDetails(DataSource datasource, LowerExtremityDTO lowerextremitydto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        Connection con = null;
        CallableStatement cstmt = null;
        int i = 0;
        String code = lowerextremitydto.getPersoncode();

        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();

        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();

        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(datasource, code);
            con = DBConnection.getConnection();

            con.setAutoCommit(false);



            cstmt = con.prepareCall("{Call SP_LEMC_ROM_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, code);
            cstmt.setString(2, lowerextremitydto.getRomhipflexionextensionright());
            cstmt.setString(3, lowerextremitydto.getRomhipflexionextensionleft());
            cstmt.setString(4, lowerextremitydto.getRomhipabductionadductionright());
            cstmt.setString(5, lowerextremitydto.getRomhipabductionadductionleft());
            cstmt.setString(6, lowerextremitydto.getRomhiprotationright());
            cstmt.setString(7, lowerextremitydto.getRomhiprotationleft());
            cstmt.setString(8, lowerextremitydto.getRomkneeflexionextensionright());
            cstmt.setString(9, lowerextremitydto.getRomkneeflexionextensionleft());
            cstmt.setString(10, lowerextremitydto.getRomankledorsiflexionpalmarflexionright());
            cstmt.setString(11, lowerextremitydto.getRomankledorsiflexionpalmarflexionleft());
            cstmt.setString(12, lowerextremitydto.getRomankleinversioneversionright());
            cstmt.setString(13, lowerextremitydto.getRomankleinversioneversionleft());
            cstmt.setDouble(14, lowerextremitydto.getRomright());
            cstmt.setDouble(15, lowerextremitydto.getRomleft());
            cstmt.setString(16, lowerextremitydto.getLoginid());
            cstmt.setString(17, lowerextremitydto.getSystemip());

            cstmt.setInt(18, Integer.parseInt(details[0].toString()));
            cstmt.setInt(19, Integer.parseInt(details[1].toString()));


            i = cstmt.executeUpdate();
            cstmt.close();

            cstmt = con.prepareCall("{Call SP_LEM_MuscleStrength_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, code);
            cstmt.setString(2, lowerextremitydto.getMshipflexormusclesright());
            cstmt.setString(3, lowerextremitydto.getMshipflexormusclesleft());
            cstmt.setString(4, lowerextremitydto.getMshipextensormusclesright());
            cstmt.setString(5, lowerextremitydto.getMshipextensormusclesleft());
            cstmt.setString(6, lowerextremitydto.getMshiprotatormusclesright());
            cstmt.setString(7, lowerextremitydto.getMshiprotatormusclesleft());
            cstmt.setString(8, lowerextremitydto.getMshipabductormusclesright());
            cstmt.setString(9, lowerextremitydto.getMshipabductormusclesleft());
            cstmt.setString(10, lowerextremitydto.getMshipadductormusclesright());
            cstmt.setString(11, lowerextremitydto.getMshipadductormusclesleft());
            cstmt.setString(12, lowerextremitydto.getMskneeflexormusclesright());
            cstmt.setString(13, lowerextremitydto.getMskneeflexormusclesleft());
            cstmt.setString(14, lowerextremitydto.getMskneeextensormusclesright());
            cstmt.setString(15, lowerextremitydto.getMskneeextensormusclesleft());
            cstmt.setString(16, lowerextremitydto.getMsankleplanterflexormusclesright());
            cstmt.setString(17, lowerextremitydto.getMsankleplanterflexormusclesleft());
            cstmt.setString(18, lowerextremitydto.getMsankledorsiflexormusclesright());
            cstmt.setString(19, lowerextremitydto.getMsankledorsiflexormusclesleft());
            cstmt.setString(20, lowerextremitydto.getMsankleinvertormusclesright());
            cstmt.setString(21, lowerextremitydto.getMsankleinvertormusclesleft());
            cstmt.setString(22, lowerextremitydto.getMsankleevertormusclesright());
            cstmt.setString(23, lowerextremitydto.getMsankleevertormusclesleft());
            cstmt.setDouble(24, lowerextremitydto.getMsright());
            cstmt.setDouble(25, lowerextremitydto.getMsleft());
            cstmt.setString(26, lowerextremitydto.getLoginid());
            cstmt.setString(27, lowerextremitydto.getSystemip());

            cstmt.setInt(28, Integer.parseInt(details[0].toString()));
            cstmt.setInt(29, Integer.parseInt(details[1].toString()));


            int j = cstmt.executeUpdate();
            cstmt.close();

            cstmt = con.prepareCall("{Call SP_LEPC_Details_Insert(?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, code);
            cstmt.setString(2, lowerextremitydto.getDeformity());
            cstmt.setString(3, lowerextremitydto.getPain());
            cstmt.setString(4, lowerextremitydto.getLoss_of_Function());
            cstmt.setString(5, lowerextremitydto.getComplications());
            cstmt.setString(6, lowerextremitydto.getShortening());
            cstmt.setDouble(7, lowerextremitydto.getExtra());
            cstmt.setString(8, lowerextremitydto.getLoginid());
            cstmt.setString(9, lowerextremitydto.getSystemip());

            cstmt.setInt(10, Integer.parseInt(details[0].toString()));
            cstmt.setInt(11, Integer.parseInt(details[1].toString()));

            int k = cstmt.executeUpdate();
            cstmt.close();

            cstmt = con.prepareCall("{Call SP_LESC_ClinicalMethod_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, code);
            cstmt.setString(2, lowerextremitydto.getWorking_on_plane());
            cstmt.setString(3, lowerextremitydto.getWorking_on_slope());
            cstmt.setString(4, lowerextremitydto.getWorking_on_stairs());
            cstmt.setString(5, lowerextremitydto.getStanding_on_both_legs());
            cstmt.setString(6, lowerextremitydto.getStanding_on_effected());
            cstmt.setString(7, lowerextremitydto.getSquatting_on_floor());
            cstmt.setString(8, lowerextremitydto.getSitting_cross_leg());
            cstmt.setString(9, lowerextremitydto.getKneeling());
            cstmt.setString(10, lowerextremitydto.getTaking_turns());
            cstmt.setDouble(11, lowerextremitydto.getStability());
            cstmt.setDouble(12, lowerextremitydto.getLowerextremity());
            cstmt.setString(13, lowerextremitydto.getLoginid());
            cstmt.setString(14, lowerextremitydto.getSystemip());

            cstmt.setInt(15, Integer.parseInt(details[0].toString()));
            cstmt.setInt(16, Integer.parseInt(details[1].toString()));


            int l = cstmt.executeUpdate();
            cstmt.close();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(datasource, "Error in Inserting Lower Extrimity Details", code);
            // End
            int exResult = ExceptionDAO.saveException(datasource, exception.toString(), "insertLowerExtremityDetails", "LowerExtremityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LowerExtremityDAO", "insertLowerExtremityDetails");

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
     * @return list
     */
    public LowerExtremityDTO getLowerExtremityDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        Connection con = null;
        CallableStatement cstmt = null;

        LowerExtremityDTO lowerextremitydto = new LowerExtremityDTO();
        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_LEMC_ROM_Details_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                lowerextremitydto.setRomhipflexionextensionright(String.valueOf(rs.getInt(1)));
                lowerextremitydto.setRomhipflexionextensionleft(String.valueOf(rs.getInt(2)));
                lowerextremitydto.setRomhipabductionadductionright(String.valueOf(rs.getInt(3)));
                lowerextremitydto.setRomhipabductionadductionleft(String.valueOf(rs.getInt(4)));
                lowerextremitydto.setRomhiprotationright(String.valueOf(rs.getInt(5)));
                lowerextremitydto.setRomhiprotationleft(String.valueOf(rs.getInt(6)));
                lowerextremitydto.setRomkneeflexionextensionright(String.valueOf(rs.getInt(7)));
                lowerextremitydto.setRomkneeflexionextensionleft(String.valueOf(rs.getInt(8)));
                lowerextremitydto.setRomankledorsiflexionpalmarflexionright(String.valueOf(rs.getInt(9)));
                lowerextremitydto.setRomankledorsiflexionpalmarflexionleft(String.valueOf(rs.getInt(10)));
                lowerextremitydto.setRomankleinversioneversionright(String.valueOf(rs.getInt(11)));
                lowerextremitydto.setRomankleinversioneversionleft(String.valueOf(rs.getInt(12)));
                lowerextremitydto.setRomright(rs.getFloat(13));
                lowerextremitydto.setRomleft(rs.getFloat(14));
            }
            rs.close();
            cstmt.close();

            cstmt = con.prepareCall("{Call SP_LEM_MuscleStrength_Details_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                lowerextremitydto.setMshipflexormusclesright(String.valueOf(rs.getInt(1)));
                lowerextremitydto.setMshipflexormusclesleft(String.valueOf(rs.getInt(2)));
                lowerextremitydto.setMshipextensormusclesright(String.valueOf(rs.getInt(3)));
                lowerextremitydto.setMshipextensormusclesleft(String.valueOf(rs.getInt(4)));
                lowerextremitydto.setMshipabductormusclesright(String.valueOf(rs.getInt(5)));
                lowerextremitydto.setMshipabductormusclesleft(String.valueOf(rs.getInt(6)));
                lowerextremitydto.setMshipadductormusclesright(String.valueOf(rs.getInt(7)));
                lowerextremitydto.setMshipadductormusclesleft(String.valueOf(rs.getInt(8)));
                lowerextremitydto.setMshiprotatormusclesright(String.valueOf(rs.getInt(9)));
                lowerextremitydto.setMshiprotatormusclesleft(String.valueOf(rs.getInt(10)));
                lowerextremitydto.setMskneeflexormusclesright(String.valueOf(rs.getInt(11)));
                lowerextremitydto.setMskneeflexormusclesleft(String.valueOf(rs.getInt(12)));
                lowerextremitydto.setMskneeextensormusclesright(String.valueOf(rs.getInt(13)));
                lowerextremitydto.setMskneeextensormusclesleft(String.valueOf(rs.getInt(14)));
                lowerextremitydto.setMsankleplanterflexormusclesright(String.valueOf(rs.getInt(15)));
                lowerextremitydto.setMsankleplanterflexormusclesleft(String.valueOf(rs.getInt(16)));
                lowerextremitydto.setMsankledorsiflexormusclesright(String.valueOf(rs.getInt(17)));
                lowerextremitydto.setMsankledorsiflexormusclesleft(String.valueOf(rs.getInt(18)));
                lowerextremitydto.setMsankleinvertormusclesright(String.valueOf(rs.getInt(19)));
                lowerextremitydto.setMsankleinvertormusclesleft(String.valueOf(rs.getInt(20)));
                lowerextremitydto.setMsankleevertormusclesright(String.valueOf(rs.getInt(21)));
                lowerextremitydto.setMsankleevertormusclesleft(String.valueOf(rs.getInt(22)));
                lowerextremitydto.setMsright(rs.getFloat(23));
                lowerextremitydto.setMsleft(rs.getFloat(24));
            }
            rs.close();
            cstmt.close();

            cstmt = con.prepareCall("{Call SP_LEPC_Details_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                lowerextremitydto.setDeformity(String.valueOf(rs.getInt(1)));
                lowerextremitydto.setPain(String.valueOf(rs.getInt(2)));
                lowerextremitydto.setLoss_of_Function(String.valueOf(rs.getInt(3)));
                lowerextremitydto.setComplications(String.valueOf(rs.getInt(4)));
                lowerextremitydto.setShortening(String.valueOf(rs.getFloat(5)));
                lowerextremitydto.setExtra(rs.getFloat(6));

            }
            rs.close();
            cstmt.close();

            cstmt = con.prepareCall("{Call SP_LESC_ClinicalMethod_Details_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                lowerextremitydto.setWorking_on_plane(rs.getString(1));
                lowerextremitydto.setWorking_on_slope(rs.getString(2));
                lowerextremitydto.setWorking_on_stairs(rs.getString(3));
                lowerextremitydto.setStanding_on_both_legs(rs.getString(4));
                lowerextremitydto.setStanding_on_effected(rs.getString(5));
                lowerextremitydto.setSquatting_on_floor(rs.getString(6));
                lowerextremitydto.setSitting_cross_leg(rs.getString(7));
                lowerextremitydto.setKneeling(rs.getString(8));
                lowerextremitydto.setTaking_turns(rs.getString(9));
                lowerextremitydto.setStability(rs.getInt(10));
            }
            rs.close();
            cstmt.close();
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getLowerExtremityDetails", "LowerExtremityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LowerExtremityDAO", "getLowerExtremityDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getLowerExtremityDetails", "LowerExtremityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LowerExtremityDAO", "getLowerExtremityDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return lowerextremitydto;
    }

    /**
     * 
     * @param datasource 
     * @param lowerextremitydto 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public int updateLowerExtremityDetails(DataSource datasource, LowerExtremityDTO lowerextremitydto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        Connection con = null;
        CallableStatement cstmt = null;
        String code = lowerextremitydto.getPersoncode();
        String query;
        String personcode = lowerextremitydto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        int i = 0;
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {

            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(datasource, code);
            con = DBConnection.getConnection();


            if (lowerextremitydto.getLowerextremity() == 0) {
                CommonDAO.changeStatusToInactiveForResetInUpdate(datasource, personcode, "tblLower_Extremity_MobilityComponent_MuscleStrength_Details");
                CommonDAO.changeStatusToInactiveForResetInUpdate(datasource, personcode, "tblLower_Extremity_MobilityComponent_ROM_Details");
                CommonDAO.changeStatusToInactiveForResetInUpdate(datasource, personcode, "tblLower_Extremity_PresenceofComplications_Details");
                CommonDAO.changeStatusToInactiveForResetInUpdate(datasource, personcode, "tblLower_Extremity_StabilityComponent_ClinicalMethod_Details");
            } else {
                con.setAutoCommit(false);
                cstmt = con.prepareCall("{Call SP_LEMC_ROM_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, lowerextremitydto.getPersoncode());
                cstmt.setString(2, lowerextremitydto.getRomhipflexionextensionright());
                cstmt.setString(3, lowerextremitydto.getRomhipflexionextensionleft());
                cstmt.setString(4, lowerextremitydto.getRomhipabductionadductionright());
                cstmt.setString(5, lowerextremitydto.getRomhipabductionadductionleft());
                cstmt.setString(6, lowerextremitydto.getRomhiprotationright());
                cstmt.setString(7, lowerextremitydto.getRomhiprotationleft());
                cstmt.setString(8, lowerextremitydto.getRomkneeflexionextensionright());
                cstmt.setString(9, lowerextremitydto.getRomkneeflexionextensionleft());
                cstmt.setString(10, lowerextremitydto.getRomankledorsiflexionpalmarflexionright());
                cstmt.setString(11, lowerextremitydto.getRomankledorsiflexionpalmarflexionleft());
                cstmt.setString(12, lowerextremitydto.getRomankleinversioneversionright());
                cstmt.setString(13, lowerextremitydto.getRomankleinversioneversionleft());
                cstmt.setDouble(14, lowerextremitydto.getRomright());
                cstmt.setDouble(15, lowerextremitydto.getRomleft());
                cstmt.setString(16, lowerextremitydto.getLoginid());
                cstmt.setString(17, lowerextremitydto.getSystemip());

                cstmt.setInt(18, Integer.parseInt(details[0].toString()));
                cstmt.setInt(19, Integer.parseInt(details[1].toString()));

                int x = cstmt.executeUpdate();
                cstmt.close();

                cstmt = con.prepareCall("{Call SP_LEM_MuscleStrength_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, lowerextremitydto.getPersoncode());
                cstmt.setString(2, lowerextremitydto.getMshipflexormusclesright());
                cstmt.setString(3, lowerextremitydto.getMshipflexormusclesleft());
                cstmt.setString(4, lowerextremitydto.getMshipextensormusclesright());
                cstmt.setString(5, lowerextremitydto.getMshipextensormusclesleft());
                cstmt.setString(6, lowerextremitydto.getMshiprotatormusclesright());
                cstmt.setString(7, lowerextremitydto.getMshiprotatormusclesleft());
                cstmt.setString(8, lowerextremitydto.getMshipabductormusclesright());
                cstmt.setString(9, lowerextremitydto.getMshipabductormusclesleft());
                cstmt.setString(10, lowerextremitydto.getMshipadductormusclesright());
                cstmt.setString(11, lowerextremitydto.getMshipadductormusclesleft());
                cstmt.setString(12, lowerextremitydto.getMskneeflexormusclesright());
                cstmt.setString(13, lowerextremitydto.getMskneeflexormusclesleft());
                cstmt.setString(14, lowerextremitydto.getMskneeextensormusclesright());
                cstmt.setString(15, lowerextremitydto.getMskneeextensormusclesleft());
                cstmt.setString(16, lowerextremitydto.getMsankleplanterflexormusclesright());
                cstmt.setString(17, lowerextremitydto.getMsankleplanterflexormusclesleft());
                cstmt.setString(18, lowerextremitydto.getMsankledorsiflexormusclesright());
                cstmt.setString(19, lowerextremitydto.getMsankledorsiflexormusclesleft());
                cstmt.setString(20, lowerextremitydto.getMsankleinvertormusclesright());
                cstmt.setString(21, lowerextremitydto.getMsankleinvertormusclesleft());
                cstmt.setString(22, lowerextremitydto.getMsankleevertormusclesright());
                cstmt.setString(23, lowerextremitydto.getMsankleevertormusclesleft());
                cstmt.setDouble(24, lowerextremitydto.getMsright());
                cstmt.setDouble(25, lowerextremitydto.getMsleft());
                cstmt.setString(26, lowerextremitydto.getLoginid());
                cstmt.setString(27, lowerextremitydto.getSystemip());

                cstmt.setInt(28, Integer.parseInt(details[0].toString()));
                cstmt.setInt(29, Integer.parseInt(details[1].toString()));

                int j = cstmt.executeUpdate();
                cstmt.close();

                cstmt = con.prepareCall("{Call SP_LEPC_Details_Update(?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, lowerextremitydto.getPersoncode());
                cstmt.setString(2, lowerextremitydto.getDeformity());
                cstmt.setString(3, lowerextremitydto.getPain());
                cstmt.setString(4, lowerextremitydto.getLoss_of_Function());
                cstmt.setString(5, lowerextremitydto.getComplications());
                cstmt.setString(6, lowerextremitydto.getShortening());
                cstmt.setDouble(7, lowerextremitydto.getExtra());
                cstmt.setString(8, lowerextremitydto.getLoginid());
                cstmt.setString(9, lowerextremitydto.getSystemip());

                cstmt.setInt(10, Integer.parseInt(details[0].toString()));
                cstmt.setInt(11, Integer.parseInt(details[1].toString()));

                int k = cstmt.executeUpdate();
                cstmt.close();

                cstmt = con.prepareCall("{Call SP_LESC_ClinicalMethod_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, lowerextremitydto.getPersoncode());
                cstmt.setString(2, lowerextremitydto.getWorking_on_plane());
                cstmt.setString(3, lowerextremitydto.getWorking_on_slope());
                cstmt.setString(4, lowerextremitydto.getWorking_on_stairs());
                cstmt.setString(5, lowerextremitydto.getStanding_on_both_legs());
                cstmt.setString(6, lowerextremitydto.getStanding_on_effected());
                cstmt.setString(7, lowerextremitydto.getSquatting_on_floor());
                cstmt.setString(8, lowerextremitydto.getSitting_cross_leg());
                cstmt.setString(9, lowerextremitydto.getKneeling());
                cstmt.setString(10, lowerextremitydto.getTaking_turns());
                cstmt.setDouble(11, lowerextremitydto.getStability());
                cstmt.setDouble(12, lowerextremitydto.getLowerextremity());
                cstmt.setString(13, lowerextremitydto.getLoginid());
                cstmt.setString(14, lowerextremitydto.getSystemip());

                cstmt.setInt(15, Integer.parseInt(details[0].toString()));
                cstmt.setInt(16, Integer.parseInt(details[1].toString()));

                int l = cstmt.executeUpdate();
                cstmt.close();
                con.setAutoCommit(true);
                i = 1;
            }
        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(datasource, "Error in Updating Lower Extrimity Details", code);
            // End
            int exResult = ExceptionDAO.saveException(datasource, exception.toString(), "updateLowerExtremityDetails", "LowerExtremityDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LowerExtremityDAO", "updateLowerExtremityDetails");

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
        boolean code = false;
        Connection con = null;
        Statement stmt = null; 
        PreparedStatement pstmt = null ;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            String query = "select Person_Code from tblLower_Extremity_MobilityComponent_ROM_Details where Person_Code=? and status='Active'";
          //  stmt = con.createStatement();
            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            
            rs = pstmt.executeQuery();
            if (rs.next() == false) {
                code = false;
            } else {
                code = true;
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "personCode", "LowerExtremityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LowerExtremityDAO", "personCode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "personCode", "LowerExtremityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LowerExtremityDAO", "personCode");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(pstmt);

        }
        return code;
    }

    /**
     * this method is used for insert into SP_LEMC_ROM_Details_Insert,SP_LEM_MuscleStrength_Details_Insert,
     *
     * @return lowerextremitydto
     * @param datasource
     * @param lowerextremitydto
     * @throws org.bf.disability.Exception.SADAREMException
     */
    public int insertLowerExtremityDetailsAU(DataSource datasource, LowerExtremityDTO lowerextremitydto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        Connection con = null;
        CallableStatement cstmt = null;
        //Statement stmt = null;
        PreparedStatement pstmt = null ;
        int i = 0;
        String code = lowerextremitydto.getPersoncode();

        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();

        try {

            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(datasource, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);


          //  stmt = con.createStatement();
            
            pstmt = con.prepareStatement("select * from tblLower_Extremity_MobilityComponent_ROM_Details where Person_Code=? and status='Active'");
            pstmt.setString(1, code);
            rs = pstmt.executeQuery();
            if (!rs.next()) {

                cstmt = con.prepareCall("{Call SP_LEMC_ROM_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, code);
                cstmt.setString(2, lowerextremitydto.getRomhipflexionextensionright());
                cstmt.setString(3, lowerextremitydto.getRomhipflexionextensionleft());
                cstmt.setString(4, lowerextremitydto.getRomhipabductionadductionright());
                cstmt.setString(5, lowerextremitydto.getRomhipabductionadductionleft());
                cstmt.setString(6, lowerextremitydto.getRomhiprotationright());
                cstmt.setString(7, lowerextremitydto.getRomhiprotationleft());
                cstmt.setString(8, lowerextremitydto.getRomkneeflexionextensionright());
                cstmt.setString(9, lowerextremitydto.getRomkneeflexionextensionleft());
                cstmt.setString(10, lowerextremitydto.getRomankledorsiflexionpalmarflexionright());
                cstmt.setString(11, lowerextremitydto.getRomankledorsiflexionpalmarflexionleft());
                cstmt.setString(12, lowerextremitydto.getRomankleinversioneversionright());
                cstmt.setString(13, lowerextremitydto.getRomankleinversioneversionleft());
                cstmt.setDouble(14, lowerextremitydto.getRomright());
                cstmt.setDouble(15, lowerextremitydto.getRomleft());
                cstmt.setString(16, lowerextremitydto.getLoginid());
                cstmt.setString(17, lowerextremitydto.getSystemip());

                cstmt.setInt(18, Integer.parseInt(details[0].toString()));
                cstmt.setInt(19, Integer.parseInt(details[1].toString()));

                /*  if(session.getAttribute("categoryIdAu")!=null && session.getAttribute("categoryCountAu")!=null) {
                cstmt.setInt(18, Integer.parseInt((String)session.getAttribute("categoryIdAu")));
                cstmt.setInt(19, Integer.parseInt((String)session.getAttribute("categoryCountAu")));
                }else {
                cstmt.setInt(18, 1);
                cstmt.setInt(19, 1);
                }*/
                i = cstmt.executeUpdate();
                cstmt.close();

                cstmt = con.prepareCall("{Call SP_LEM_MuscleStrength_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, code);
                cstmt.setString(2, lowerextremitydto.getMshipflexormusclesright());
                cstmt.setString(3, lowerextremitydto.getMshipflexormusclesleft());
                cstmt.setString(4, lowerextremitydto.getMshipextensormusclesright());
                cstmt.setString(5, lowerextremitydto.getMshipextensormusclesleft());
                cstmt.setString(6, lowerextremitydto.getMshiprotatormusclesright());
                cstmt.setString(7, lowerextremitydto.getMshiprotatormusclesleft());
                cstmt.setString(8, lowerextremitydto.getMshipabductormusclesright());
                cstmt.setString(9, lowerextremitydto.getMshipabductormusclesleft());
                cstmt.setString(10, lowerextremitydto.getMshipadductormusclesright());
                cstmt.setString(11, lowerextremitydto.getMshipadductormusclesleft());
                cstmt.setString(12, lowerextremitydto.getMskneeflexormusclesright());
                cstmt.setString(13, lowerextremitydto.getMskneeflexormusclesleft());
                cstmt.setString(14, lowerextremitydto.getMskneeextensormusclesright());
                cstmt.setString(15, lowerextremitydto.getMskneeextensormusclesleft());
                cstmt.setString(16, lowerextremitydto.getMsankleplanterflexormusclesright());
                cstmt.setString(17, lowerextremitydto.getMsankleplanterflexormusclesleft());
                cstmt.setString(18, lowerextremitydto.getMsankledorsiflexormusclesright());
                cstmt.setString(19, lowerextremitydto.getMsankledorsiflexormusclesleft());
                cstmt.setString(20, lowerextremitydto.getMsankleinvertormusclesright());
                cstmt.setString(21, lowerextremitydto.getMsankleinvertormusclesleft());
                cstmt.setString(22, lowerextremitydto.getMsankleevertormusclesright());
                cstmt.setString(23, lowerextremitydto.getMsankleevertormusclesleft());
                cstmt.setDouble(24, lowerextremitydto.getMsright());
                cstmt.setDouble(25, lowerextremitydto.getMsleft());
                cstmt.setString(26, lowerextremitydto.getLoginid());
                cstmt.setString(27, lowerextremitydto.getSystemip());

                cstmt.setInt(28, Integer.parseInt(details[0].toString()));
                cstmt.setInt(29, Integer.parseInt(details[1].toString()));

                /* if(session.getAttribute("categoryIdAu")!=null && session.getAttribute("categoryCountAu")!=null) {
                cstmt.setInt(28, Integer.parseInt((String)session.getAttribute("categoryIdAu")));
                cstmt.setInt(29, Integer.parseInt((String)session.getAttribute("categoryCountAu")));
                }else {
                cstmt.setInt(28, 1);
                cstmt.setInt(29, 1);
                }*/
                int j = cstmt.executeUpdate();
                cstmt.close();

                cstmt = con.prepareCall("{Call SP_LEPC_Details_Insert(?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, code);
                cstmt.setString(2, lowerextremitydto.getDeformity());
                cstmt.setString(3, lowerextremitydto.getPain());
                cstmt.setString(4, lowerextremitydto.getLoss_of_Function());
                cstmt.setString(5, lowerextremitydto.getComplications());
                cstmt.setString(6, lowerextremitydto.getShortening());
                cstmt.setDouble(7, lowerextremitydto.getExtra());
                cstmt.setString(8, lowerextremitydto.getLoginid());
                cstmt.setString(9, lowerextremitydto.getSystemip());

                cstmt.setInt(10, Integer.parseInt(details[0].toString()));
                cstmt.setInt(11, Integer.parseInt(details[1].toString()));

                /* if(session.getAttribute("categoryIdAu")!=null && session.getAttribute("categoryCountAu")!=null) {
                cstmt.setInt(10, Integer.parseInt((String)session.getAttribute("categoryIdAu")));
                cstmt.setInt(11, Integer.parseInt((String)session.getAttribute("categoryCountAu")));
                }else {
                cstmt.setInt(10, 1);
                cstmt.setInt(11, 1);
                }*/
                int k = cstmt.executeUpdate();
                cstmt.close();

                cstmt = con.prepareCall("{Call SP_LESC_ClinicalMethod_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, code);
                cstmt.setString(2, lowerextremitydto.getWorking_on_plane());
                cstmt.setString(3, lowerextremitydto.getWorking_on_slope());
                cstmt.setString(4, lowerextremitydto.getWorking_on_stairs());
                cstmt.setString(5, lowerextremitydto.getStanding_on_both_legs());
                cstmt.setString(6, lowerextremitydto.getStanding_on_effected());
                cstmt.setString(7, lowerextremitydto.getSquatting_on_floor());
                cstmt.setString(8, lowerextremitydto.getSitting_cross_leg());
                cstmt.setString(9, lowerextremitydto.getKneeling());
                cstmt.setString(10, lowerextremitydto.getTaking_turns());
                cstmt.setDouble(11, lowerextremitydto.getStability());
                cstmt.setDouble(12, lowerextremitydto.getLowerextremity());
                cstmt.setString(13, lowerextremitydto.getLoginid());
                cstmt.setString(14, lowerextremitydto.getSystemip());

                cstmt.setInt(15, Integer.parseInt(details[0].toString()));
                cstmt.setInt(16, Integer.parseInt(details[1].toString()));

                /* if(session.getAttribute("categoryIdAu")!=null && session.getAttribute("categoryCountAu")!=null) {
                cstmt.setInt(15, Integer.parseInt((String)session.getAttribute("categoryIdAu")));
                cstmt.setInt(16, Integer.parseInt((String)session.getAttribute("categoryCountAu")));
                }else {
                cstmt.setInt(15, 1);
                cstmt.setInt(16, 1);
                }*/
                int l = cstmt.executeUpdate();
                cstmt.close();
                con.commit();
                con.setAutoCommit(true);
            } else {
                i = -1;
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "insertLowerExtremityDetailsAU", "LowerExtremityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LowerExtremityDAO", "insertLowerExtremityDetailsAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "insertLowerExtremityDetailsAU", "LowerExtremityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LowerExtremityDAO", "insertLowerExtremityDetailsAU");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);
            DBConnection.closeStatement(stmt); 
            DBConnection.closeStatement(pstmt);

        }
        return i;
    }
}







