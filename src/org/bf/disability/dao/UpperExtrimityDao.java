/*
 * UpperExtrimityDao.java
 *
 * Created on June 11, 2008, 10:04 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.dto.UpperExtrimityDto;
import org.bf.disability.service.TransactionService;
import org.bf.disability.servicefactory.TransactionFactory;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is for doing manipulation on upperextrimity on database
 * @author svsganesh
 * @version 1.0
 */
public class UpperExtrimityDao {

    /**
     * This method checks the person code
     * @param personcode 
     * @param ds 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return int
     */
    public boolean checkPersoncode(String personcode,
            DataSource ds)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        boolean personcodecheckflag = false;

        try {
            con = DBConnection.getConnection();
            //query for finding personcode
            String query = "select Person_Code from "
                    + "tblUpper_Extremity_HandComponent_Strength_Details where"
                    + " Person_Code=? and status='Active'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            if (rs.next() == false) {
                personcodecheckflag = false;
            } else {
                personcodecheckflag = true;
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersoncode", "UpperExtrimityDao", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpperExtrimityDao", "checkPersoncode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersoncode", "UpperExtrimityDao", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpperExtrimityDao", "checkPersoncode");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return personcodecheckflag;
    }

    /**
     * This method insert upperextremity data into
     * there respective database tables
     * @param ds 
     * @param upperextrimitydto 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public int inserUpperExtremityData(DataSource ds,
            UpperExtrimityDto upperextrimitydto, HttpServletRequest request)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cstmt = null;
        int insertupperextremitydata = 0, rominsert = 0, coordinateinsert = 0;
        int penhentioninsert = 0, sensationinsert = 0;
        int msinsert = 0, extrainsert = 0, handinsert = 0, strentghinsert = 0;
        String code = upperextrimitydto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {

            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);


            //Callable Statement  for inserting ROM details
            cstmt = con.prepareCall("{Call SP_UEAC_ROM_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, code);
            cstmt.setInt(2, Integer.parseInt(upperextrimitydto.getRom_sf_right()));
            cstmt.setInt(3, Integer.parseInt(upperextrimitydto.getRom_sf_left()));
            cstmt.setInt(4, Integer.parseInt(upperextrimitydto.getRom_sr_right()));
            cstmt.setInt(5, Integer.parseInt(upperextrimitydto.getRom_sr_left()));
            cstmt.setInt(6, Integer.parseInt(upperextrimitydto.getRom_sa_right()));
            cstmt.setInt(7, Integer.parseInt(upperextrimitydto.getRom_sa_left()));
            cstmt.setInt(8, Integer.parseInt(upperextrimitydto.getRom_ef_right()));
            cstmt.setInt(9, Integer.parseInt(upperextrimitydto.getRom_ef_left()));
            cstmt.setInt(10, Integer.parseInt(upperextrimitydto.getRom_es_right()));
            cstmt.setInt(11, Integer.parseInt(upperextrimitydto.getRom_es_left()));
            cstmt.setInt(12, Integer.parseInt(upperextrimitydto.getRom_wd_right()));
            cstmt.setInt(13, Integer.parseInt(upperextrimitydto.getRom_wd_left()));
            cstmt.setInt(14, Integer.parseInt(upperextrimitydto.getRom_wr_right()));
            cstmt.setInt(15, Integer.parseInt(upperextrimitydto.getRom_wr_left()));
            cstmt.setDouble(16, Double.parseDouble(upperextrimitydto.getRomright()));
            cstmt.setDouble(17, Double.parseDouble(upperextrimitydto.getRomleft()));
            cstmt.setString(18, upperextrimitydto.getLoginid());
            cstmt.setString(19, upperextrimitydto.getSystemip());

            cstmt.setInt(20, Integer.parseInt(details[0].toString()));
            cstmt.setInt(21, Integer.parseInt(details[1].toString()));


            rominsert = cstmt.executeUpdate();
//Callable Statement for inserting Musclestrength details
            cstmt = con.prepareCall("{Call SP_UEAC_MuscleStrength_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, code);
            cstmt.setInt(2, Integer.parseInt(upperextrimitydto.getMs_sf_right()));
            cstmt.setInt(3, Integer.parseInt(upperextrimitydto.getMs_sf_left()));
            cstmt.setInt(4, Integer.parseInt(upperextrimitydto.getMs_se_right()));
            cstmt.setInt(5, Integer.parseInt(upperextrimitydto.getMs_se_left()));
            cstmt.setInt(6, Integer.parseInt(upperextrimitydto.getMs_sab_right()));
            cstmt.setInt(7, Integer.parseInt(upperextrimitydto.getMs_sab_left()));
            cstmt.setInt(8, Integer.parseInt(upperextrimitydto.getMs_sad_right()));
            cstmt.setInt(9, Integer.parseInt(upperextrimitydto.getMs_sad_left()));
            cstmt.setInt(10, Integer.parseInt(upperextrimitydto.getMs_sext_right()));
            cstmt.setInt(11, Integer.parseInt(upperextrimitydto.getMs_sext_left()));
            cstmt.setInt(12, Integer.parseInt(upperextrimitydto.getMs_sint_right()));
            cstmt.setInt(13, Integer.parseInt(upperextrimitydto.getMs_sint_left()));
            cstmt.setInt(14, Integer.parseInt(upperextrimitydto.getMs_ef_right()));
            cstmt.setInt(15, Integer.parseInt(upperextrimitydto.getMs_ef_left()));
            cstmt.setInt(16, Integer.parseInt(upperextrimitydto.getMs_ee_right()));
            cstmt.setInt(17, Integer.parseInt(upperextrimitydto.getMs_ee_left()));
            cstmt.setInt(18, Integer.parseInt(upperextrimitydto.getMs_ep_right()));
            cstmt.setInt(19, Integer.parseInt(upperextrimitydto.getMs_ep_left()));
            cstmt.setInt(20, Integer.parseInt(upperextrimitydto.getMs_es_right()));
            cstmt.setInt(21, Integer.parseInt(upperextrimitydto.getMs_es_left()));
            cstmt.setInt(22, Integer.parseInt(upperextrimitydto.getMs_wd_right()));
            cstmt.setInt(23, Integer.parseInt(upperextrimitydto.getMs_wd_left()));
            cstmt.setInt(24, Integer.parseInt(upperextrimitydto.getMs_wp_right()));
            cstmt.setInt(25, Integer.parseInt(upperextrimitydto.getMs_wp_left()));
            cstmt.setInt(26, Integer.parseInt(upperextrimitydto.getMs_wr_right()));
            cstmt.setInt(27, Integer.parseInt(upperextrimitydto.getMs_wr_left()));
            cstmt.setInt(28, Integer.parseInt(upperextrimitydto.getMs_wu_right()));
            cstmt.setInt(29, Integer.parseInt(upperextrimitydto.getMs_wu_left()));
            cstmt.setDouble(30, Double.parseDouble(upperextrimitydto.getMsright()));
            cstmt.setDouble(31, Double.parseDouble(upperextrimitydto.getMsleft()));
            cstmt.setString(32, upperextrimitydto.getLoginid());
            cstmt.setString(33, upperextrimitydto.getSystemip());

            cstmt.setInt(34, Integer.parseInt(details[0].toString()));
            cstmt.setInt(35, Integer.parseInt(details[1].toString()));


            msinsert = cstmt.executeUpdate();
            //Callable Statement for inserting Coordinate details
            cstmt = con.prepareCall("{Call SP_UEAC_Coordinated_Activities_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, code);
            cstmt.setInt(2, Integer.parseInt(upperextrimitydto.getCoordinate_lifting()));
            cstmt.setInt(3, Integer.parseInt(upperextrimitydto.getCoordinate_touching()));
            cstmt.setInt(4, Integer.parseInt(upperextrimitydto.getCoordinate_eating()));
            cstmt.setInt(5, Integer.parseInt(upperextrimitydto.getCoordinate_combing()));
            cstmt.setInt(6, Integer.parseInt(upperextrimitydto.getCoordinate_putting()));
            cstmt.setInt(7, Integer.parseInt(upperextrimitydto.getCoordinate_ablution()));
            cstmt.setInt(8, Integer.parseInt(upperextrimitydto.getCoordinate_buttoning()));
            cstmt.setInt(9, Integer.parseInt(upperextrimitydto.getCoordinate_tie()));
            cstmt.setInt(10, Integer.parseInt(upperextrimitydto.getCoordinate_writing()));
            cstmt.setInt(11, Integer.parseInt(upperextrimitydto.getCoordinate_drinking()));
            cstmt.setInt(12, Integer.parseInt(upperextrimitydto.getCoordinate()));
            cstmt.setString(13, upperextrimitydto.getLoginid());
            cstmt.setString(14, upperextrimitydto.getSystemip());

            cstmt.setInt(15, Integer.parseInt(details[0].toString()));
            cstmt.setInt(16, Integer.parseInt(details[1].toString()));


            coordinateinsert = cstmt.executeUpdate();

            //Callable Statement for inserting  prehensation details
            cstmt = con.prepareCall("{Call SP_UEHC_Prehention_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, code);
            cstmt.setInt(2, Integer.parseInt(upperextrimitydto.getHand_opindex_right()));
            cstmt.setInt(3, Integer.parseInt(upperextrimitydto.getHand_opindex_left()));
            cstmt.setInt(4, Integer.parseInt(upperextrimitydto.getHand_opmiddle_right()));
            cstmt.setInt(5, Integer.parseInt(upperextrimitydto.getHand_opmiddle_left()));
            cstmt.setInt(6, Integer.parseInt(upperextrimitydto.getHand_opring_right()));
            cstmt.setInt(7, Integer.parseInt(upperextrimitydto.getHand_opring_left()));
            cstmt.setInt(8, Integer.parseInt(upperextrimitydto.getHand_oplittle_right()));
            cstmt.setInt(9, Integer.parseInt(upperextrimitydto.getHand_oplittle_left()));
            cstmt.setInt(10, Integer.parseInt(upperextrimitydto.getHand_lakey_right()));
            cstmt.setInt(11, Integer.parseInt(upperextrimitydto.getHand_lakey_left()));
            cstmt.setInt(12, Integer.parseInt(upperextrimitydto.getHand_cylarge_right()));
            cstmt.setInt(13, Integer.parseInt(upperextrimitydto.getHand_cylarge_left()));
            cstmt.setInt(14, Integer.parseInt(upperextrimitydto.getHand_cysmall_right()));
            cstmt.setInt(15, Integer.parseInt(upperextrimitydto.getHand_cysmall_left()));
            cstmt.setInt(16, Integer.parseInt(upperextrimitydto.getHand_splarge_right()));
            cstmt.setInt(17, Integer.parseInt(upperextrimitydto.getHand_splarge_left()));
            cstmt.setInt(18, Integer.parseInt(upperextrimitydto.getHand_spsmall_right()));
            cstmt.setInt(19, Integer.parseInt(upperextrimitydto.getHand_spsmall_left()));
            cstmt.setInt(20, Integer.parseInt(upperextrimitydto.getHand_hook_right()));
            cstmt.setInt(21, Integer.parseInt(upperextrimitydto.getHand_hook_left()));
            cstmt.setInt(22, Integer.parseInt(upperextrimitydto.getPrehention()));
            cstmt.setString(23, upperextrimitydto.getLoginid());
            cstmt.setString(24, upperextrimitydto.getSystemip());

            cstmt.setInt(25, Integer.parseInt(details[0].toString()));
            cstmt.setInt(26, Integer.parseInt(details[1].toString()));


            penhentioninsert = cstmt.executeUpdate();
            //Callable Statement for inserting  Sensation details
            cstmt = con.prepareCall("{Call SP_UEHC_Sensation_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, code);
            cstmt.setInt(2, Integer.parseInt(upperextrimitydto.getHand_sethumb_right()));
            cstmt.setInt(3, Integer.parseInt(upperextrimitydto.getHand_sethumb_left()));
            cstmt.setInt(4, Integer.parseInt(upperextrimitydto.getHand_seindex_right()));
            cstmt.setInt(5, Integer.parseInt(upperextrimitydto.getHand_seindex_left()));
            cstmt.setInt(6, Integer.parseInt(upperextrimitydto.getHand_semiddle_right()));
            cstmt.setInt(7, Integer.parseInt(upperextrimitydto.getHand_semiddle_left()));
            cstmt.setInt(8, Integer.parseInt(upperextrimitydto.getHand_sering_right()));
            cstmt.setInt(9, Integer.parseInt(upperextrimitydto.getHand_sering_left()));
            cstmt.setInt(10, Integer.parseInt(upperextrimitydto.getHand_selittle_right()));
            cstmt.setInt(11, Integer.parseInt(upperextrimitydto.getHand_selittle_left()));
            cstmt.setInt(12, Integer.parseInt(upperextrimitydto.getSensation()));
            cstmt.setString(13, upperextrimitydto.getLoginid());
            cstmt.setString(14, upperextrimitydto.getSystemip());

            cstmt.setInt(15, Integer.parseInt(details[0].toString()));
            cstmt.setInt(16, Integer.parseInt(details[1].toString()));


            sensationinsert = cstmt.executeUpdate();
            //Callable Statement for inserting  Strenght details
            cstmt = con.prepareCall("{Call SP_UEHC_Strength_Details_Insert(?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, code);
            cstmt.setInt(2, Integer.parseInt(upperextrimitydto.getHand_stgrip_right()));
            cstmt.setInt(3, Integer.parseInt(upperextrimitydto.getHand_stgrip_left()));
            cstmt.setInt(4, Integer.parseInt(upperextrimitydto.getHand_stpinch_right()));
            cstmt.setInt(5, Integer.parseInt(upperextrimitydto.getHand_stpinch_left()));
            cstmt.setInt(6, Integer.parseInt(upperextrimitydto.getStrength()));
            cstmt.setDouble(7, (upperextrimitydto.getUpperExterimity_total()));
            cstmt.setString(8, upperextrimitydto.getLoginid());
            cstmt.setString(9, upperextrimitydto.getSystemip());

            cstmt.setInt(10, Integer.parseInt(details[0].toString()));
            cstmt.setInt(11, Integer.parseInt(details[1].toString()));

            strentghinsert = cstmt.executeUpdate();
            //Callable Statement for inserting  Complications details
            cstmt = con.prepareCall("{Call SP_UEPC_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, code);
            cstmt.setInt(2, Integer.parseInt(upperextrimitydto.getCom_inflection()));
            cstmt.setInt(3, Integer.parseInt(upperextrimitydto.getCom_Deformity()));
            cstmt.setInt(4, Integer.parseInt(upperextrimitydto.getCom_Misalignment()));
            cstmt.setInt(5, Integer.parseInt(upperextrimitydto.getCom_Contracture()));
            cstmt.setInt(6, Integer.parseInt(upperextrimitydto.getCom_LossofCosmeticappearance()));
            cstmt.setInt(7, Integer.parseInt(upperextrimitydto.getCom_domionantupperextremity()));
            cstmt.setFloat(8, Float.parseFloat(upperextrimitydto.getInches()));
            cstmt.setDouble(9, Double.parseDouble(upperextrimitydto.getTotalextra()));
            cstmt.setString(10, upperextrimitydto.getLoginid());
            cstmt.setString(11, upperextrimitydto.getSystemip());

            cstmt.setInt(12, Integer.parseInt(details[0].toString()));
            cstmt.setInt(13, Integer.parseInt(details[1].toString()));

            extrainsert = cstmt.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in UpperExtreimity Details Inserting", code);
            // End

            con.rollback();
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "inserUpperExtremityData", "UpperExtrimityDao", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpperExtrimityDao", "inserUpperExtremityData");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);

        }
        return insertupperextremitydata;
    }

    /**
     * This method update upperextremity data into
     *  there respective database tables for particular 
     *  Pwd
     * @param ds 
     * @param upperextrimitydto 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public int updateRomData(DataSource ds,
            UpperExtrimityDto upperextrimitydto, HttpServletRequest request)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;

        int romupdate = 0, msupdate = 0, coordinateupdate = 0, penhentionupdate = 0,
                sensationupdate = 0, handupdate = 0, strentghupdate = 0, extraupdate = 0;
        String code = upperextrimitydto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();

        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, upperextrimitydto.getPersoncode());
            con = DBConnection.getConnection();

            con.setAutoCommit(false);

            cstmt = con.prepareCall("{Call SP_UEAC_ROM_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, upperextrimitydto.getPersoncode());
            cstmt.setInt(2, Integer.parseInt(upperextrimitydto.getRom_sf_right()));
            cstmt.setInt(3, Integer.parseInt(upperextrimitydto.getRom_sf_left()));
            cstmt.setInt(4, Integer.parseInt(upperextrimitydto.getRom_sr_right()));
            cstmt.setInt(5, Integer.parseInt(upperextrimitydto.getRom_sr_left()));
            cstmt.setInt(6, Integer.parseInt(upperextrimitydto.getRom_sa_right()));
            cstmt.setInt(7, Integer.parseInt(upperextrimitydto.getRom_sa_left()));
            cstmt.setInt(8, Integer.parseInt(upperextrimitydto.getRom_ef_right()));
            cstmt.setInt(9, Integer.parseInt(upperextrimitydto.getRom_ef_left()));
            cstmt.setInt(10, Integer.parseInt(upperextrimitydto.getRom_es_right()));
            cstmt.setInt(11, Integer.parseInt(upperextrimitydto.getRom_es_left()));
            cstmt.setInt(12, Integer.parseInt(upperextrimitydto.getRom_wd_right()));
            cstmt.setInt(13, Integer.parseInt(upperextrimitydto.getRom_wd_left()));
            cstmt.setInt(14, Integer.parseInt(upperextrimitydto.getRom_wr_right()));
            cstmt.setInt(15, Integer.parseInt(upperextrimitydto.getRom_wr_left()));
            cstmt.setDouble(16, Double.parseDouble(upperextrimitydto.getRomright()));
            cstmt.setDouble(17, Double.parseDouble(upperextrimitydto.getRomleft()));
            cstmt.setString(18, upperextrimitydto.getLoginid());
            cstmt.setString(19, upperextrimitydto.getSystemip());

            cstmt.setInt(20, Integer.parseInt(details[0].toString()));
            cstmt.setInt(21, Integer.parseInt(details[1].toString()));


            romupdate = cstmt.executeUpdate();



            //Ms
            cstmt = con.prepareCall("{Call SP_UEAC_MuscleStrength_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, upperextrimitydto.getPersoncode());
            cstmt.setInt(2, Integer.parseInt(upperextrimitydto.getMs_sf_right()));
            cstmt.setInt(3, Integer.parseInt(upperextrimitydto.getMs_sf_left()));
            cstmt.setInt(4, Integer.parseInt(upperextrimitydto.getMs_se_right()));
            cstmt.setInt(5, Integer.parseInt(upperextrimitydto.getMs_se_left()));
            cstmt.setInt(6, Integer.parseInt(upperextrimitydto.getMs_sab_right()));
            cstmt.setInt(7, Integer.parseInt(upperextrimitydto.getMs_sab_left()));
            cstmt.setInt(8, Integer.parseInt(upperextrimitydto.getMs_sad_right()));
            cstmt.setInt(9, Integer.parseInt(upperextrimitydto.getMs_sad_left()));
            cstmt.setInt(10, Integer.parseInt(upperextrimitydto.getMs_sext_right()));
            cstmt.setInt(11, Integer.parseInt(upperextrimitydto.getMs_sext_left()));
            cstmt.setInt(12, Integer.parseInt(upperextrimitydto.getMs_sint_right()));
            cstmt.setInt(13, Integer.parseInt(upperextrimitydto.getMs_sint_left()));
            cstmt.setInt(14, Integer.parseInt(upperextrimitydto.getMs_ef_right()));
            cstmt.setInt(15, Integer.parseInt(upperextrimitydto.getMs_ef_left()));
            cstmt.setInt(16, Integer.parseInt(upperextrimitydto.getMs_ee_right()));
            cstmt.setInt(17, Integer.parseInt(upperextrimitydto.getMs_ee_left()));
            cstmt.setInt(18, Integer.parseInt(upperextrimitydto.getMs_ep_right()));
            cstmt.setInt(19, Integer.parseInt(upperextrimitydto.getMs_ep_left()));
            cstmt.setInt(20, Integer.parseInt(upperextrimitydto.getMs_es_right()));
            cstmt.setInt(21, Integer.parseInt(upperextrimitydto.getMs_es_left()));
            cstmt.setInt(22, Integer.parseInt(upperextrimitydto.getMs_wd_right()));
            cstmt.setInt(23, Integer.parseInt(upperextrimitydto.getMs_wd_left()));
            cstmt.setInt(24, Integer.parseInt(upperextrimitydto.getMs_wp_right()));
            cstmt.setInt(25, Integer.parseInt(upperextrimitydto.getMs_wp_left()));
            cstmt.setInt(26, Integer.parseInt(upperextrimitydto.getMs_wr_right()));
            cstmt.setInt(27, Integer.parseInt(upperextrimitydto.getMs_wr_left()));
            cstmt.setInt(28, Integer.parseInt(upperextrimitydto.getMs_wu_right()));
            cstmt.setInt(29, Integer.parseInt(upperextrimitydto.getMs_wu_left()));
            cstmt.setDouble(30, Double.parseDouble(upperextrimitydto.getMsright()));
            cstmt.setDouble(31, Double.parseDouble(upperextrimitydto.getMsleft()));
            cstmt.setString(32, upperextrimitydto.getLoginid());
            cstmt.setString(33, upperextrimitydto.getSystemip());

            cstmt.setInt(34, Integer.parseInt(details[0].toString()));
            cstmt.setInt(35, Integer.parseInt(details[1].toString()));


            msupdate = cstmt.executeUpdate();


            //coordinate

            cstmt = con.prepareCall("{Call SP_UEAC_Coordinated_Activities_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, upperextrimitydto.getPersoncode());
            cstmt.setInt(2, Integer.parseInt(upperextrimitydto.getCoordinate_lifting()));
            cstmt.setInt(3, Integer.parseInt(upperextrimitydto.getCoordinate_touching()));
            cstmt.setInt(4, Integer.parseInt(upperextrimitydto.getCoordinate_eating()));
            cstmt.setInt(5, Integer.parseInt(upperextrimitydto.getCoordinate_combing()));
            cstmt.setInt(6, Integer.parseInt(upperextrimitydto.getCoordinate_putting()));
            cstmt.setInt(7, Integer.parseInt(upperextrimitydto.getCoordinate_ablution()));
            cstmt.setInt(8, Integer.parseInt(upperextrimitydto.getCoordinate_buttoning()));
            cstmt.setInt(9, Integer.parseInt(upperextrimitydto.getCoordinate_tie()));
            cstmt.setInt(10, Integer.parseInt(upperextrimitydto.getCoordinate_writing()));
            cstmt.setInt(11, Integer.parseInt(upperextrimitydto.getCoordinate_drinking()));
            cstmt.setInt(12, Integer.parseInt(upperextrimitydto.getCoordinate()));
            cstmt.setString(13, upperextrimitydto.getLoginid());
            cstmt.setString(14, upperextrimitydto.getSystemip());

            cstmt.setInt(15, Integer.parseInt(details[0].toString()));
            cstmt.setInt(16, Integer.parseInt(details[1].toString()));

            coordinateupdate = cstmt.executeUpdate();


            //strength
            cstmt = con.prepareCall("{Call SP_UEHC_Prehention_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, upperextrimitydto.getPersoncode());
            cstmt.setInt(2, Integer.parseInt(upperextrimitydto.getHand_opindex_right()));
            cstmt.setInt(3, Integer.parseInt(upperextrimitydto.getHand_opindex_left()));
            cstmt.setInt(4, Integer.parseInt(upperextrimitydto.getHand_opmiddle_right()));
            cstmt.setInt(5, Integer.parseInt(upperextrimitydto.getHand_opmiddle_left()));
            cstmt.setInt(6, Integer.parseInt(upperextrimitydto.getHand_opring_right()));
            cstmt.setInt(7, Integer.parseInt(upperextrimitydto.getHand_opring_left()));
            cstmt.setInt(8, Integer.parseInt(upperextrimitydto.getHand_oplittle_right()));
            cstmt.setInt(9, Integer.parseInt(upperextrimitydto.getHand_oplittle_left()));
            cstmt.setInt(10, Integer.parseInt(upperextrimitydto.getHand_lakey_right()));
            cstmt.setInt(11, Integer.parseInt(upperextrimitydto.getHand_lakey_left()));
            cstmt.setInt(12, Integer.parseInt(upperextrimitydto.getHand_cylarge_right()));
            cstmt.setInt(13, Integer.parseInt(upperextrimitydto.getHand_cylarge_left()));
            cstmt.setInt(14, Integer.parseInt(upperextrimitydto.getHand_cysmall_right()));
            cstmt.setInt(15, Integer.parseInt(upperextrimitydto.getHand_cysmall_left()));
            cstmt.setInt(16, Integer.parseInt(upperextrimitydto.getHand_splarge_right()));
            cstmt.setInt(17, Integer.parseInt(upperextrimitydto.getHand_splarge_left()));
            cstmt.setInt(18, Integer.parseInt(upperextrimitydto.getHand_spsmall_right()));
            cstmt.setInt(19, Integer.parseInt(upperextrimitydto.getHand_spsmall_left()));
            cstmt.setInt(20, Integer.parseInt(upperextrimitydto.getHand_hook_right()));
            cstmt.setInt(21, Integer.parseInt(upperextrimitydto.getHand_hook_left()));
            cstmt.setInt(22, Integer.parseInt(upperextrimitydto.getPrehention()));
            cstmt.setString(23, upperextrimitydto.getLoginid());
            cstmt.setString(24, upperextrimitydto.getSystemip());

            cstmt.setInt(25, Integer.parseInt(details[0].toString()));
            cstmt.setInt(26, Integer.parseInt(details[1].toString()));


            penhentionupdate = cstmt.executeUpdate();
            cstmt = con.prepareCall("{Call SP_UEHC_Sensation_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, upperextrimitydto.getPersoncode());
            cstmt.setInt(2, Integer.parseInt(upperextrimitydto.getHand_sethumb_right()));
            cstmt.setInt(3, Integer.parseInt(upperextrimitydto.getHand_sethumb_left()));
            cstmt.setInt(4, Integer.parseInt(upperextrimitydto.getHand_seindex_right()));
            cstmt.setInt(5, Integer.parseInt(upperextrimitydto.getHand_seindex_left()));
            cstmt.setInt(6, Integer.parseInt(upperextrimitydto.getHand_semiddle_right()));
            cstmt.setInt(7, Integer.parseInt(upperextrimitydto.getHand_semiddle_left()));
            cstmt.setInt(8, Integer.parseInt(upperextrimitydto.getHand_sering_right()));
            cstmt.setInt(9, Integer.parseInt(upperextrimitydto.getHand_sering_left()));
            cstmt.setInt(10, Integer.parseInt(upperextrimitydto.getHand_selittle_right()));
            cstmt.setInt(11, Integer.parseInt(upperextrimitydto.getHand_selittle_left()));
            cstmt.setInt(12, Integer.parseInt(upperextrimitydto.getSensation()));
            cstmt.setString(13, upperextrimitydto.getLoginid());
            cstmt.setString(14, upperextrimitydto.getSystemip());

            cstmt.setInt(15, Integer.parseInt(details[0].toString()));
            cstmt.setInt(16, Integer.parseInt(details[1].toString()));


            sensationupdate = cstmt.executeUpdate();
            cstmt = con.prepareCall("{Call SP_UEHC_Strength_Details_Update(?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, upperextrimitydto.getPersoncode());
            cstmt.setInt(2, Integer.parseInt(upperextrimitydto.getHand_stgrip_right()));
            cstmt.setInt(3, Integer.parseInt(upperextrimitydto.getHand_stgrip_left()));
            cstmt.setInt(4, Integer.parseInt(upperextrimitydto.getHand_stpinch_right()));
            cstmt.setInt(5, Integer.parseInt(upperextrimitydto.getHand_stpinch_left()));
            cstmt.setInt(6, Integer.parseInt(upperextrimitydto.getStrength()));
            cstmt.setDouble(7, (upperextrimitydto.getUpperExterimity_total()));
            cstmt.setString(8, upperextrimitydto.getLoginid());
            cstmt.setString(9, upperextrimitydto.getSystemip());

            cstmt.setInt(10, Integer.parseInt(details[0].toString()));
            cstmt.setInt(11, Integer.parseInt(details[1].toString()));

            strentghupdate = cstmt.executeUpdate();

            //complications



            cstmt = con.prepareCall("{Call SP_UEPC_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, upperextrimitydto.getPersoncode());
            cstmt.setInt(2, Integer.parseInt(upperextrimitydto.getCom_inflection()));
            cstmt.setInt(3, Integer.parseInt(upperextrimitydto.getCom_Deformity()));
            cstmt.setInt(4, Integer.parseInt(upperextrimitydto.getCom_Misalignment()));
            cstmt.setInt(5, Integer.parseInt(upperextrimitydto.getCom_Contracture()));
            cstmt.setInt(6, Integer.parseInt(upperextrimitydto.getCom_LossofCosmeticappearance()));
            cstmt.setInt(7, Integer.parseInt(upperextrimitydto.getCom_domionantupperextremity()));
            cstmt.setFloat(8, Float.parseFloat(upperextrimitydto.getInches()));
            cstmt.setDouble(9, Double.parseDouble(upperextrimitydto.getTotalextra()));
            cstmt.setString(10, upperextrimitydto.getLoginid());
            cstmt.setString(11, upperextrimitydto.getSystemip());

            cstmt.setInt(12, Integer.parseInt(details[0].toString()));
            cstmt.setInt(13, Integer.parseInt(details[1].toString()));


            extraupdate = cstmt.executeUpdate();

            con.commit();
            con.setAutoCommit(true);


        } catch (Exception e) {

            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error Upper Extrimity Details Updating", code);
            // End


            con.rollback();
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "updateRomData", "UpperExtrimityDao", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpperExtrimityDao", "updateRomData");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);

        }
        return romupdate;
    }

    /**
     * This method seleting  upperextremity data 
     *   database tables for particular Pwd
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public UpperExtrimityDto selectUpperExterimityData(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt1 = null;
        CallableStatement cstmt2 = null;
        CallableStatement cstmt3 = null;
        CallableStatement cstmt4 = null;
        CallableStatement cstmt5 = null;
        CallableStatement cstmt6 = null;
        CallableStatement cstmt7 = null;
        ResultSet rs1 = null, rs2 = null, rs3 = null, rs4 = null, rs5 = null, rs6 = null, rs7 = null;
        UpperExtrimityDto upperextrimitydto = new UpperExtrimityDto();
        try {
            con = DBConnection.getConnection();
            cstmt1 = con.prepareCall("{Call SP_UEAC_ROM_Details_Select(?)}");
            cstmt1.setString(1, personcode);
            rs1 = cstmt1.executeQuery();

            while (rs1.next()) {
                upperextrimitydto.setRom_sf_right(rs1.getString(1));
                upperextrimitydto.setRom_sf_left((rs1.getString(2)));
                upperextrimitydto.setRom_sr_right(rs1.getString(3));
                upperextrimitydto.setRom_sr_left(rs1.getString(4));
                upperextrimitydto.setRom_sa_right(rs1.getString(5));
                upperextrimitydto.setRom_sa_left(rs1.getString(6));
                upperextrimitydto.setRom_ef_right(rs1.getString(7));
                upperextrimitydto.setRom_ef_left(rs1.getString(8));
                upperextrimitydto.setRom_es_right(rs1.getString(9));
                upperextrimitydto.setRom_es_left(rs1.getString(10));
                upperextrimitydto.setRom_wd_right(rs1.getString(11));
                upperextrimitydto.setRom_wd_left(rs1.getString(12));
                upperextrimitydto.setRom_wr_right(rs1.getString(13));
                upperextrimitydto.setRom_wr_left(rs1.getString(14));
                upperextrimitydto.setRomright(rs1.getString(15));
                upperextrimitydto.setRomleft(rs1.getString(16));

            }

            cstmt2 = con.prepareCall("{Call SP_UEAC_MuscleStrength_Details_Select(?)}");
            cstmt2.setString(1, personcode);
            rs2 = cstmt2.executeQuery();
            while (rs2.next()) {
                upperextrimitydto.setMs_sf_right(rs2.getString(1));
                upperextrimitydto.setMs_sf_left(rs2.getString(2));
                upperextrimitydto.setMs_se_right(rs2.getString(3));
                upperextrimitydto.setMs_se_left(rs2.getString(4));
                upperextrimitydto.setMs_sab_right(rs2.getString(5));
                upperextrimitydto.setMs_sab_left(rs2.getString(6));
                upperextrimitydto.setMs_sad_right(rs2.getString(7));
                upperextrimitydto.setMs_sad_left(rs2.getString(8));
                upperextrimitydto.setMs_sext_right(rs2.getString(9));
                upperextrimitydto.setMs_sext_left(rs2.getString(10));
                upperextrimitydto.setMs_sint_right(rs2.getString(11));
                upperextrimitydto.setMs_sint_left(rs2.getString(12).trim());
                upperextrimitydto.setMs_ef_right(rs2.getString(13));
                upperextrimitydto.setMs_ef_left(rs2.getString(14));
                upperextrimitydto.setMs_ee_right(rs2.getString(15));
                upperextrimitydto.setMs_ee_left(rs2.getString(16));
                upperextrimitydto.setMs_ep_right(rs2.getString(17));
                upperextrimitydto.setMs_ep_left(rs2.getString(18));
                upperextrimitydto.setMs_es_right(rs2.getString(19));
                upperextrimitydto.setMs_es_left(rs2.getString(20));
                upperextrimitydto.setMs_wd_right(rs2.getString(21));
                upperextrimitydto.setMs_wd_left(rs2.getString(22));
                upperextrimitydto.setMs_wp_right(rs2.getString(23));
                upperextrimitydto.setMs_wp_left(rs2.getString(24));
                upperextrimitydto.setMs_wr_right(rs2.getString(25));
                upperextrimitydto.setMs_wr_left(rs2.getString(26));
                upperextrimitydto.setMs_wu_right(rs2.getString(27));
                upperextrimitydto.setMs_wu_left(rs2.getString(28));
                upperextrimitydto.setMsright(rs2.getString(29));
                upperextrimitydto.setMsleft(rs2.getString(30));

            }

            cstmt3 = con.prepareCall("{Call SP_UEAC_Coordinated_Activities_Details_Select(?)}");
            cstmt3.setString(1, personcode);
            rs3 = cstmt3.executeQuery();
            while (rs3.next()) {
                upperextrimitydto.setCoordinate_lifting(rs3.getString(1));
                upperextrimitydto.setCoordinate_touching(rs3.getString(2));
                upperextrimitydto.setCoordinate_eating(rs3.getString(3));
                upperextrimitydto.setCoordinate_combing(rs3.getString(4));
                upperextrimitydto.setCoordinate_putting(rs3.getString(5));
                upperextrimitydto.setCoordinate_ablution(rs3.getString(6));
                upperextrimitydto.setCoordinate_buttoning(rs3.getString(7));
                upperextrimitydto.setCoordinate_tie(rs3.getString(8));
                upperextrimitydto.setCoordinate_writing(rs3.getString(9));
                upperextrimitydto.setCoordinate_drinking(rs3.getString(10));
                upperextrimitydto.setCoordinate(rs3.getString(11));
            }

            cstmt4 = con.prepareCall("{Call SP_UEHC_Prehention_Details_Select(?)}");
            cstmt4.setString(1, personcode);
            rs4 = cstmt4.executeQuery();
            while (rs4.next()) {
                upperextrimitydto.setHand_opindex_right(rs4.getString(1));
                upperextrimitydto.setHand_opindex_left(rs4.getString(2));
                upperextrimitydto.setHand_opmiddle_right(rs4.getString(3));
                upperextrimitydto.setHand_opmiddle_left(rs4.getString(4));
                upperextrimitydto.setHand_opring_right(rs4.getString(5));
                upperextrimitydto.setHand_opring_left(rs4.getString(6));
                upperextrimitydto.setHand_oplittle_right(rs4.getString(7));
                upperextrimitydto.setHand_oplittle_left(rs4.getString(8));
                upperextrimitydto.setHand_lakey_right(rs4.getString(9));
                upperextrimitydto.setHand_lakey_left(rs4.getString(10));
                upperextrimitydto.setHand_cylarge_right(rs4.getString(11));
                upperextrimitydto.setHand_cylarge_left(rs4.getString(12));
                upperextrimitydto.setHand_cysmall_right(rs4.getString(13));
                upperextrimitydto.setHand_cysmall_left(rs4.getString(14));
                upperextrimitydto.setHand_splarge_right(rs4.getString(15));
                upperextrimitydto.setHand_splarge_left(rs4.getString(16));
                upperextrimitydto.setHand_spsmall_right(rs4.getString(17));
                upperextrimitydto.setHand_spsmall_left(rs4.getString(18));
                upperextrimitydto.setHand_hook_right(rs4.getString(19));
                upperextrimitydto.setHand_hook_left(rs4.getString(20));
                upperextrimitydto.setPrehention(rs4.getString(21));
            }
            cstmt5 = con.prepareCall("{Call SP_UEHC_Sensation_Details_Select(?)}");
            cstmt5.setString(1, personcode);
            rs5 = cstmt5.executeQuery();
            while (rs5.next()) {
                upperextrimitydto.setHand_sethumb_right(rs5.getString(1));
                upperextrimitydto.setHand_sethumb_left(rs5.getString(2));
                upperextrimitydto.setHand_seindex_right(rs5.getString(3));
                upperextrimitydto.setHand_seindex_left(rs5.getString(4));
                upperextrimitydto.setHand_semiddle_right(rs5.getString(5));
                upperextrimitydto.setHand_semiddle_left(rs5.getString(6));
                upperextrimitydto.setHand_sering_right(rs5.getString(7));
                upperextrimitydto.setHand_sering_left(rs5.getString(8));
                upperextrimitydto.setHand_selittle_right(rs5.getString(9));
                upperextrimitydto.setHand_selittle_left(rs5.getString(10));
                upperextrimitydto.setSensation(rs5.getString(11));
            }
            cstmt6 = con.prepareCall("{Call SP_UEHC_Strength_Details_Select(?)}");
            cstmt6.setString(1, personcode);
            rs6 = cstmt6.executeQuery();
            while (rs6.next()) {
                upperextrimitydto.setHand_stgrip_right(rs6.getString(1));
                upperextrimitydto.setHand_stgrip_left(rs6.getString(2));
                upperextrimitydto.setHand_stpinch_right(rs6.getString(3));
                upperextrimitydto.setHand_stpinch_left(rs6.getString(4));
                upperextrimitydto.setStrength(rs6.getString(5));
                upperextrimitydto.setUpperExterimity_total(rs6.getDouble(6));
            }
            cstmt7 = con.prepareCall("{Call SP_UEPC_Details_Select(?)}");
            cstmt7.setString(1, personcode);
            rs7 = cstmt7.executeQuery();
            while (rs7.next()) {
                upperextrimitydto.setCom_inflection(rs7.getString(1));
                upperextrimitydto.setCom_Deformity(rs7.getString(2));
                upperextrimitydto.setCom_Misalignment(rs7.getString(3));
                upperextrimitydto.setCom_Contracture(rs7.getString(4));
                upperextrimitydto.setCom_LossofCosmeticappearance(rs7.getString(5));
                upperextrimitydto.setCom_domionantupperextremity(rs7.getString(6));
                upperextrimitydto.setInches(rs7.getString(7));
                upperextrimitydto.setTotalextra(rs7.getString(8));
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "selectUpperExterimityData", "UpperExtrimityDao", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpperExtrimityDao", "selectUpperExterimityData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "selectUpperExterimityData", "UpperExtrimityDao", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpperExtrimityDao", "selectUpperExterimityData");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs1);
            DBConnection.closeResultSet(rs2);
            DBConnection.closeResultSet(rs3);
            DBConnection.closeResultSet(rs4);
            DBConnection.closeResultSet(rs5);
            DBConnection.closeResultSet(rs6);
            DBConnection.closeResultSet(rs7);
            DBConnection.closeStatement(cstmt1);
            DBConnection.closeStatement(cstmt2);
            DBConnection.closeStatement(cstmt3);
            DBConnection.closeStatement(cstmt4);
            DBConnection.closeStatement(cstmt5);
            DBConnection.closeStatement(cstmt6);
            DBConnection.closeStatement(cstmt7);

        }
        return upperextrimitydto;
    }

    /**
     * This method delete upperextremity data 
     * from respective database tables
     * of particular Pwd
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     */
    public void deleteUpperExtremityUpdateRecord(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {


        try {
            CommonDAO.changeStatusToInactiveForResetInUpdate(ds, personcode, "tblUpper_Extremity_ArmComponent_Coordinated_Activities_Details");

            CommonDAO.changeStatusToInactiveForResetInUpdate(ds, personcode, "tblUpper_Extremity_ArmComponent_MuscleStrength_Details");

            CommonDAO.changeStatusToInactiveForResetInUpdate(ds, personcode, "tblUpper_Extremity_ArmComponent_ROM_Details");

            CommonDAO.changeStatusToInactiveForResetInUpdate(ds, personcode, "tblUpper_Extremity_HandComponent_Prehention_Details");

            CommonDAO.changeStatusToInactiveForResetInUpdate(ds, personcode, "tblUpper_Extremity_HandComponent_Sensation_Details");

            CommonDAO.changeStatusToInactiveForResetInUpdate(ds, personcode, "tblUpper_Extremity_HandComponent_Strength_Details");

            CommonDAO.changeStatusToInactiveForResetInUpdate(ds, personcode, "tblUpper_Extremity_PresenceofComplications_Details");




        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deleteUpperExtremityUpdateRecord", "UpperExtrimityDao", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpperExtrimityDao", "deleteUpperExtremityUpdateRecord");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deleteUpperExtremityUpdateRecord", "UpperExtrimityDao", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpperExtrimityDao", "deleteUpperExtremityUpdateRecord");
        } finally {
        }

    }

    /**
     * This method insert upperextremity data into
     * there respective database tables
     * @param ds
     * @param upperextrimitydto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return
     */
    public int inserUpperExtremityDataAU(DataSource ds,
            UpperExtrimityDto upperextrimitydto, HttpServletRequest request)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cstmt = null;
        int insertupperextremitydata = 0, rominsert = 0, coordinateinsert = 0;
        int penhentioninsert = 0, sensationinsert = 0;
        int msinsert = 0, extrainsert = 0, handinsert = 0, strentghinsert = 0;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        String code = upperextrimitydto.getPersoncode();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);



            pstmt = con.prepareStatement("select * from tblUpper_Extremity_ArmComponent_ROM_Details where Person_Code=? and status='Active'");
            pstmt.setString(1, code);
            rs = pstmt.executeQuery();
            
            if (!rs.next()) {
                //Callable Statement  for inserting ROM details
                cstmt = con.prepareCall("{Call SP_UEAC_ROM_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, code);
                cstmt.setInt(2, Integer.parseInt(upperextrimitydto.getRom_sf_right()));
                cstmt.setInt(3, Integer.parseInt(upperextrimitydto.getRom_sf_left()));
                cstmt.setInt(4, Integer.parseInt(upperextrimitydto.getRom_sr_right()));
                cstmt.setInt(5, Integer.parseInt(upperextrimitydto.getRom_sr_left()));
                cstmt.setInt(6, Integer.parseInt(upperextrimitydto.getRom_sa_right()));
                cstmt.setInt(7, Integer.parseInt(upperextrimitydto.getRom_sa_left()));
                cstmt.setInt(8, Integer.parseInt(upperextrimitydto.getRom_ef_right()));
                cstmt.setInt(9, Integer.parseInt(upperextrimitydto.getRom_ef_left()));
                cstmt.setInt(10, Integer.parseInt(upperextrimitydto.getRom_es_right()));
                cstmt.setInt(11, Integer.parseInt(upperextrimitydto.getRom_es_left()));
                cstmt.setInt(12, Integer.parseInt(upperextrimitydto.getRom_wd_right()));
                cstmt.setInt(13, Integer.parseInt(upperextrimitydto.getRom_wd_left()));
                cstmt.setInt(14, Integer.parseInt(upperextrimitydto.getRom_wr_right()));
                cstmt.setInt(15, Integer.parseInt(upperextrimitydto.getRom_wr_left()));
                cstmt.setDouble(16, Double.parseDouble(upperextrimitydto.getRomright()));
                cstmt.setDouble(17, Double.parseDouble(upperextrimitydto.getRomleft()));
                cstmt.setString(18, upperextrimitydto.getLoginid());
                cstmt.setString(19, upperextrimitydto.getSystemip());

                cstmt.setInt(20, Integer.parseInt(details[0].toString()));
                cstmt.setInt(21, Integer.parseInt(details[1].toString()));


                rominsert = cstmt.executeUpdate();
//Callable Statement for inserting Musclestrength details
                cstmt = con.prepareCall("{Call SP_UEAC_MuscleStrength_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, code);
                cstmt.setInt(2, Integer.parseInt(upperextrimitydto.getMs_sf_right()));
                cstmt.setInt(3, Integer.parseInt(upperextrimitydto.getMs_sf_left()));
                cstmt.setInt(4, Integer.parseInt(upperextrimitydto.getMs_se_right()));
                cstmt.setInt(5, Integer.parseInt(upperextrimitydto.getMs_se_left()));
                cstmt.setInt(6, Integer.parseInt(upperextrimitydto.getMs_sab_right()));
                cstmt.setInt(7, Integer.parseInt(upperextrimitydto.getMs_sab_left()));
                cstmt.setInt(8, Integer.parseInt(upperextrimitydto.getMs_sad_right()));
                cstmt.setInt(9, Integer.parseInt(upperextrimitydto.getMs_sad_left()));
                cstmt.setInt(10, Integer.parseInt(upperextrimitydto.getMs_sext_right()));
                cstmt.setInt(11, Integer.parseInt(upperextrimitydto.getMs_sext_left()));
                cstmt.setInt(12, Integer.parseInt(upperextrimitydto.getMs_sint_right()));
                cstmt.setInt(13, Integer.parseInt(upperextrimitydto.getMs_sint_left()));
                cstmt.setInt(14, Integer.parseInt(upperextrimitydto.getMs_ef_right()));
                cstmt.setInt(15, Integer.parseInt(upperextrimitydto.getMs_ef_left()));
                cstmt.setInt(16, Integer.parseInt(upperextrimitydto.getMs_ee_right()));
                cstmt.setInt(17, Integer.parseInt(upperextrimitydto.getMs_ee_left()));
                cstmt.setInt(18, Integer.parseInt(upperextrimitydto.getMs_ep_right()));
                cstmt.setInt(19, Integer.parseInt(upperextrimitydto.getMs_ep_left()));
                cstmt.setInt(20, Integer.parseInt(upperextrimitydto.getMs_es_right()));
                cstmt.setInt(21, Integer.parseInt(upperextrimitydto.getMs_es_left()));
                cstmt.setInt(22, Integer.parseInt(upperextrimitydto.getMs_wd_right()));
                cstmt.setInt(23, Integer.parseInt(upperextrimitydto.getMs_wd_left()));
                cstmt.setInt(24, Integer.parseInt(upperextrimitydto.getMs_wp_right()));
                cstmt.setInt(25, Integer.parseInt(upperextrimitydto.getMs_wp_left()));
                cstmt.setInt(26, Integer.parseInt(upperextrimitydto.getMs_wr_right()));
                cstmt.setInt(27, Integer.parseInt(upperextrimitydto.getMs_wr_left()));
                cstmt.setInt(28, Integer.parseInt(upperextrimitydto.getMs_wu_right()));
                cstmt.setInt(29, Integer.parseInt(upperextrimitydto.getMs_wu_left()));
                cstmt.setDouble(30, Double.parseDouble(upperextrimitydto.getMsright()));
                cstmt.setDouble(31, Double.parseDouble(upperextrimitydto.getMsleft()));
                cstmt.setString(32, upperextrimitydto.getLoginid());
                cstmt.setString(33, upperextrimitydto.getSystemip());

                cstmt.setInt(34, Integer.parseInt(details[0].toString()));
                cstmt.setInt(35, Integer.parseInt(details[1].toString()));

                msinsert = cstmt.executeUpdate();
                //Callable Statement for inserting Coordinate details
                cstmt = con.prepareCall("{Call SP_UEAC_Coordinated_Activities_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, code);
                cstmt.setInt(2, Integer.parseInt(upperextrimitydto.getCoordinate_lifting()));
                cstmt.setInt(3, Integer.parseInt(upperextrimitydto.getCoordinate_touching()));
                cstmt.setInt(4, Integer.parseInt(upperextrimitydto.getCoordinate_eating()));
                cstmt.setInt(5, Integer.parseInt(upperextrimitydto.getCoordinate_combing()));
                cstmt.setInt(6, Integer.parseInt(upperextrimitydto.getCoordinate_putting()));
                cstmt.setInt(7, Integer.parseInt(upperextrimitydto.getCoordinate_ablution()));
                cstmt.setInt(8, Integer.parseInt(upperextrimitydto.getCoordinate_buttoning()));
                cstmt.setInt(9, Integer.parseInt(upperextrimitydto.getCoordinate_tie()));
                cstmt.setInt(10, Integer.parseInt(upperextrimitydto.getCoordinate_writing()));
                cstmt.setInt(11, Integer.parseInt(upperextrimitydto.getCoordinate_drinking()));
                cstmt.setInt(12, Integer.parseInt(upperextrimitydto.getCoordinate()));
                cstmt.setString(13, upperextrimitydto.getLoginid());
                cstmt.setString(14, upperextrimitydto.getSystemip());

                cstmt.setInt(15, Integer.parseInt(details[0].toString()));
                cstmt.setInt(16, Integer.parseInt(details[1].toString()));

                coordinateinsert = cstmt.executeUpdate();

                //Callable Statement for inserting  prehensation details
                cstmt = con.prepareCall("{Call SP_UEHC_Prehention_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, code);
                cstmt.setInt(2, Integer.parseInt(upperextrimitydto.getHand_opindex_right()));
                cstmt.setInt(3, Integer.parseInt(upperextrimitydto.getHand_opindex_left()));
                cstmt.setInt(4, Integer.parseInt(upperextrimitydto.getHand_opmiddle_right()));
                cstmt.setInt(5, Integer.parseInt(upperextrimitydto.getHand_opmiddle_left()));
                cstmt.setInt(6, Integer.parseInt(upperextrimitydto.getHand_opring_right()));
                cstmt.setInt(7, Integer.parseInt(upperextrimitydto.getHand_opring_left()));
                cstmt.setInt(8, Integer.parseInt(upperextrimitydto.getHand_oplittle_right()));
                cstmt.setInt(9, Integer.parseInt(upperextrimitydto.getHand_oplittle_left()));
                cstmt.setInt(10, Integer.parseInt(upperextrimitydto.getHand_lakey_right()));
                cstmt.setInt(11, Integer.parseInt(upperextrimitydto.getHand_lakey_left()));
                cstmt.setInt(12, Integer.parseInt(upperextrimitydto.getHand_cylarge_right()));
                cstmt.setInt(13, Integer.parseInt(upperextrimitydto.getHand_cylarge_left()));
                cstmt.setInt(14, Integer.parseInt(upperextrimitydto.getHand_cysmall_right()));
                cstmt.setInt(15, Integer.parseInt(upperextrimitydto.getHand_cysmall_left()));
                cstmt.setInt(16, Integer.parseInt(upperextrimitydto.getHand_splarge_right()));
                cstmt.setInt(17, Integer.parseInt(upperextrimitydto.getHand_splarge_left()));
                cstmt.setInt(18, Integer.parseInt(upperextrimitydto.getHand_spsmall_right()));
                cstmt.setInt(19, Integer.parseInt(upperextrimitydto.getHand_spsmall_left()));
                cstmt.setInt(20, Integer.parseInt(upperextrimitydto.getHand_hook_right()));
                cstmt.setInt(21, Integer.parseInt(upperextrimitydto.getHand_hook_left()));
                cstmt.setInt(22, Integer.parseInt(upperextrimitydto.getPrehention()));
                cstmt.setString(23, upperextrimitydto.getLoginid());
                cstmt.setString(24, upperextrimitydto.getSystemip());


                cstmt.setInt(25, Integer.parseInt(details[0].toString()));
                cstmt.setInt(26, Integer.parseInt(details[1].toString()));

                penhentioninsert = cstmt.executeUpdate();
                //Callable Statement for inserting  Sensation details
                cstmt = con.prepareCall("{Call SP_UEHC_Sensation_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, code);
                cstmt.setInt(2, Integer.parseInt(upperextrimitydto.getHand_sethumb_right()));
                cstmt.setInt(3, Integer.parseInt(upperextrimitydto.getHand_sethumb_left()));
                cstmt.setInt(4, Integer.parseInt(upperextrimitydto.getHand_seindex_right()));
                cstmt.setInt(5, Integer.parseInt(upperextrimitydto.getHand_seindex_left()));
                cstmt.setInt(6, Integer.parseInt(upperextrimitydto.getHand_semiddle_right()));
                cstmt.setInt(7, Integer.parseInt(upperextrimitydto.getHand_semiddle_left()));
                cstmt.setInt(8, Integer.parseInt(upperextrimitydto.getHand_sering_right()));
                cstmt.setInt(9, Integer.parseInt(upperextrimitydto.getHand_sering_left()));
                cstmt.setInt(10, Integer.parseInt(upperextrimitydto.getHand_selittle_right()));
                cstmt.setInt(11, Integer.parseInt(upperextrimitydto.getHand_selittle_left()));
                cstmt.setInt(12, Integer.parseInt(upperextrimitydto.getSensation()));
                cstmt.setString(13, upperextrimitydto.getLoginid());
                cstmt.setString(14, upperextrimitydto.getSystemip());

                cstmt.setInt(15, Integer.parseInt(details[0].toString()));
                cstmt.setInt(16, Integer.parseInt(details[1].toString()));

                sensationinsert = cstmt.executeUpdate();
                //Callable Statement for inserting  Strenght details
                cstmt = con.prepareCall("{Call SP_UEHC_Strength_Details_Insert(?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, code);
                cstmt.setInt(2, Integer.parseInt(upperextrimitydto.getHand_stgrip_right()));
                cstmt.setInt(3, Integer.parseInt(upperextrimitydto.getHand_stgrip_left()));
                cstmt.setInt(4, Integer.parseInt(upperextrimitydto.getHand_stpinch_right()));
                cstmt.setInt(5, Integer.parseInt(upperextrimitydto.getHand_stpinch_left()));
                cstmt.setInt(6, Integer.parseInt(upperextrimitydto.getStrength()));
                cstmt.setDouble(7, (upperextrimitydto.getUpperExterimity_total()));
                cstmt.setString(8, upperextrimitydto.getLoginid());
                cstmt.setString(9, upperextrimitydto.getSystemip());

                cstmt.setInt(10, Integer.parseInt(details[0].toString()));
                cstmt.setInt(11, Integer.parseInt(details[1].toString()));

                /*if (session.getAttribute("categoryIdAu") != null && session.getAttribute("categoryCountAu") != null) {
                cstmt.setInt(10, Integer.parseInt((String) session.getAttribute("categoryIdAu")));
                cstmt.setInt(11, Integer.parseInt((String) session.getAttribute("categoryCountAu")));
                } else {
                cstmt.setInt(10, 1);
                cstmt.setInt(11, 1);
                }*/
                strentghinsert = cstmt.executeUpdate();
                //Callable Statement for inserting  Complications details
                cstmt = con.prepareCall("{Call SP_UEPC_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, code);
                cstmt.setInt(2, Integer.parseInt(upperextrimitydto.getCom_inflection()));
                cstmt.setInt(3, Integer.parseInt(upperextrimitydto.getCom_Deformity()));
                cstmt.setInt(4, Integer.parseInt(upperextrimitydto.getCom_Misalignment()));
                cstmt.setInt(5, Integer.parseInt(upperextrimitydto.getCom_Contracture()));
                cstmt.setInt(6, Integer.parseInt(upperextrimitydto.getCom_LossofCosmeticappearance()));
                cstmt.setInt(7, Integer.parseInt(upperextrimitydto.getCom_domionantupperextremity()));
                cstmt.setFloat(8, Float.parseFloat(upperextrimitydto.getInches()));
                cstmt.setDouble(9, Double.parseDouble(upperextrimitydto.getTotalextra()));
                cstmt.setString(10, upperextrimitydto.getLoginid());
                cstmt.setString(11, upperextrimitydto.getSystemip());

                cstmt.setInt(12, Integer.parseInt(details[0].toString()));
                cstmt.setInt(13, Integer.parseInt(details[1].toString()));

                extrainsert = cstmt.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            } else {
                insertupperextremitydata = -1;
            }
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error PartC Details", code);
            // End

            con.rollback();
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "inserUpperExtremityDataAU", "UpperExtrimityDao", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpperExtrimityDao", "inserUpperExtremityDataAU");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);

            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }
        return insertupperextremitydata;
    }
}
