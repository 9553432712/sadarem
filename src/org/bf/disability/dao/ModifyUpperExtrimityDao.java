/*
 * ModifyUpperExtrimityDao.java
 *
 * Created on June 26, 2008, 10:42 AM
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

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.UpperExtrimityDto;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author svsganesh
 */
public class ModifyUpperExtrimityDao {

    public UpperExtrimityDto selectUpperExterimityData(DataSource ds, String personcode) throws SADAREMDBException, SQLException {

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
            }
            cstmt6 = con.prepareCall("{Call SP_UEHC_Strength_Details_Select(?)}");
            cstmt6.setString(1, personcode);
            rs6 = cstmt6.executeQuery();
            while (rs6.next()) {
                upperextrimitydto.setHand_stgrip_right(rs6.getString(1));
                upperextrimitydto.setHand_stgrip_left(rs6.getString(2));
                upperextrimitydto.setHand_stpinch_right(rs6.getString(3));
                upperextrimitydto.setHand_stpinch_left(rs6.getString(4));
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
            }
        } catch (SQLException sqlException) {
            int exResult = ExceptionDAO.saveException(ds, sqlException.toString(), "selectUpperExterimityData", "ModifyUpperExtrimityDao", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ModifyUpperExtrimityDao", "selectUpperExterimityData");

        } catch (Exception e) {
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "selectUpperExterimityData", "ModifyUpperExtrimityDao", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ModifyUpperExtrimityDao", "selectUpperExterimityData");

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

    public void deleteUpperExtremityUpdateRecord(DataSource ds, String personcode) throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        try {

            String queryDeleteCoordinate = "delete from  tblUpper_Extremity_ArmComponent_Coordinated_Activities_Details where Person_Code=? and status='Active'";
            
            pstmt=con.prepareStatement(queryDeleteCoordinate);           
            pstmt.setString(1, personcode);
            pstmt.executeUpdate();
            
            String queryDeleteMucsle = "delete from  tblUpper_Extremity_ArmComponent_MuscleStrength_Details where Person_Code=? and status='Active'";
            
            pstmt=con.prepareStatement(queryDeleteMucsle);           
            pstmt.setString(1, personcode);
            pstmt.executeUpdate();
            
            
            String queryDeleteRom = "delete from  tblUpper_Extremity_ArmComponent_ROM_Details where Person_Code=? and status='Active'";
            
            pstmt=con.prepareStatement(queryDeleteRom);           
            pstmt.setString(1, personcode);
            pstmt.executeUpdate();
            
            String queryDeletePrehenasion = "delete from  tblUpper_Extremity_HandComponent_Prehention_Details where Person_Code=? and status='Active'";
            
            pstmt=con.prepareStatement(queryDeletePrehenasion);           
            pstmt.setString(1, personcode);
            pstmt.executeUpdate();
            
            String queryDeleteSensation = "delete from  tblUpper_Extremity_HandComponent_Sensation_Details where Person_Code=? and status='Active'";
            
            pstmt=con.prepareStatement(queryDeleteSensation);           
            pstmt.setString(1, personcode);
            pstmt.executeUpdate();
            
            String queryDeleteStrength = "delete from  tblUpper_Extremity_HandComponent_Strength_Details where Person_Code=? and status='Active'";
            
            pstmt=con.prepareStatement(queryDeleteStrength);           
            pstmt.setString(1, personcode);
            pstmt.executeUpdate();
            
            String queryDeleteComplications = "delete from  tblUpper_Extremity_PresenceofComplications_Details where Person_Code=? and status='Active'";
            
            pstmt=con.prepareStatement(queryDeleteComplications);           
            pstmt.setString(1, personcode);
            pstmt.executeUpdate();
            
            con = DBConnection.getConnection();
          //  stmt = con.createStatement();
         /*   stmt.execute(queryDeleteCoordinate);
            stmt.execute(queryDeleteMucsle);
            stmt.execute(queryDeleteRom);
            stmt.execute(queryDeletePrehenasion);
            stmt.execute(queryDeleteSensation);
            stmt.execute(queryDeleteStrength);
            stmt.execute(queryDeleteComplications);*/

        } catch (SQLException sqlException) {
            int exResult = ExceptionDAO.saveException(ds, sqlException.toString(), "deleteUpperExtremityUpdateRecord", "ModifyUpperExtrimityDao", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ModifyUpperExtrimityDao", "deleteUpperExtremityUpdateRecord");

        } catch (Exception e) {
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "deleteUpperExtremityUpdateRecord", "ModifyUpperExtrimityDao", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ModifyUpperExtrimityDao", "deleteUpperExtremityUpdateRecord");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);

        }

    }
}
