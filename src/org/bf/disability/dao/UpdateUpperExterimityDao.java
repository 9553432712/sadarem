/*
 * UpdateUpperExterimityDao.java
 *
 * Created on June 30, 2008, 1:06 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.UpperExtrimityDto;
import org.bf.disability.service.TransactionService;
import org.bf.disability.servicefactory.TransactionFactory;

import com.tcs.sgv.common.util.DBConnection;

/**
 * 
 * @author svsganesh
 * @version 1.0
 */
public class UpdateUpperExterimityDao {

    /**
     * 
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

        int romupdate = 0, msupdate = 0, coordinateupdate = 0, penhentionupdate = 0, sensationupdate = 0, handupdate = 0, strentghupdate = 0, extraupdate = 0;

        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();

        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        String code = upperextrimitydto.getPersoncode();

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
            cstmt.setInt(8, Integer.parseInt(upperextrimitydto.getInches()));
            cstmt.setDouble(9, Double.parseDouble(upperextrimitydto.getTotalextra()));
            cstmt.setString(10, upperextrimitydto.getLoginid());
            cstmt.setString(11, upperextrimitydto.getSystemip());

            cstmt.setInt(12, Integer.parseInt(details[0].toString()));
            cstmt.setInt(13, Integer.parseInt(details[1].toString()));

            extraupdate = cstmt.executeUpdate();

            con.commit();
            con.setAutoCommit(true);


        } catch (SQLException sqlEx) {
            transactionService.updateTransactionDetails(ds, "Error in Updating Rom Details", code);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateRomData", "UpdateUpperExterimityDao", "DataBase");
            con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpdateUpperExterimityDao", "updateRomData");
        } catch (Exception sqlEx) {
            transactionService.updateTransactionDetails(ds, "Error in Updating Rom Details", code);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateRomData", "UpdateUpperExterimityDao", "Code");
            con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpdateUpperExterimityDao", "updateRomData");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);


        }
        return romupdate;
    }
}
