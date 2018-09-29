/*
 * LocomotorneedsDAO.java
 *
 * Created on August 5, 2008, 2:06 PM
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

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.LocomotorneedsDTO;
import org.bf.disability.service.TransactionService;
import org.bf.disability.servicefactory.TransactionFactory;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is for doing manipulation on locomotorneeds with respective database
 * @author Bapi Naidu .T
 * @version 1.0
 */
public class LocomotorneedsDAO {

    /**
     * 
     * @param datasource 
     * @param locomotorneedsdto 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public int insertLocomotorneeds(DataSource datasource, LocomotorneedsDTO locomotorneedsdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        Connection con = null;
      //  Statement stmt = null;
        CallableStatement cstmt = null;
        PreparedStatement pstmt = null;
        String code = locomotorneedsdto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        int i = 0;
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {

            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(datasource, code);
            con = DBConnection.getConnection();
 //     
 
            pstmt = con.prepareStatement("select Person_Code from tblPerson_LocomotorNeeds_Details where Person_Code=?");
            pstmt.setString(1, code);
            rs = pstmt.executeQuery();
            
            
            if (!rs.next()) {
                cstmt = con.prepareCall("{Call SP_tblPerson_LocomotorNeeds_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, locomotorneedsdto.getPersoncode());
                cstmt.setString(2, locomotorneedsdto.getPhysiotheraphy());
                cstmt.setString(3, locomotorneedsdto.getOccupationaltheraphy());
                cstmt.setString(4, locomotorneedsdto.getSurgery());
                cstmt.setString(5, locomotorneedsdto.getPhysiotherapy());
                cstmt.setString(6, locomotorneedsdto.getOccupationaltherapy());
                cstmt.setString(7, locomotorneedsdto.getSelectwheelchair());
                cstmt.setString(8, locomotorneedsdto.getSelecttricycle());
                cstmt.setString(9, locomotorneedsdto.getSelectwalkingstick());
                cstmt.setString(10, locomotorneedsdto.getSelectcrutches());
                cstmt.setString(11, locomotorneedsdto.getCrutchestype());
                cstmt.setString(12, locomotorneedsdto.getSelectwalkingframe());
                cstmt.setString(13, locomotorneedsdto.getAeroplanesplint());
                cstmt.setString(14, locomotorneedsdto.getFigure8splint());
                cstmt.setString(15, locomotorneedsdto.getForearmsplint());
                cstmt.setString(16, locomotorneedsdto.getHandsplint());
                cstmt.setString(17, locomotorneedsdto.getShoulderprosthesis());
                cstmt.setString(18, locomotorneedsdto.getAboveelbowprosthesis());
                cstmt.setString(19, locomotorneedsdto.getElbowdisarticulationprosthesis());
                cstmt.setString(20, locomotorneedsdto.getBelowelbowprosthesis());
                cstmt.setString(21, locomotorneedsdto.getWristdisarticulationprosthesis());
                cstmt.setString(22, locomotorneedsdto.getHandprosthesis());
                cstmt.setString(23, locomotorneedsdto.getCosmeticfingerprosthesis());
                cstmt.setString(24, locomotorneedsdto.getHkafo());
                cstmt.setString(25, locomotorneedsdto.getKafo());
                cstmt.setString(26, locomotorneedsdto.getAfo());
                cstmt.setString(27, locomotorneedsdto.getKneeorthosis());
                cstmt.setString(28, locomotorneedsdto.getDbsplint());
                cstmt.setString(29, locomotorneedsdto.getModifiedshoe());
                cstmt.setString(30, locomotorneedsdto.getSerialcasting());
                cstmt.setString(31, locomotorneedsdto.getHipprothesis());
                cstmt.setString(32, locomotorneedsdto.getAbovekneeprothesis());
                cstmt.setString(33, locomotorneedsdto.getKneedisarticulation());
                cstmt.setString(34, locomotorneedsdto.getBelowkneeprothesis());
                cstmt.setString(35, locomotorneedsdto.getSymeprothesis());
                cstmt.setString(36, locomotorneedsdto.getPartialfoodprothesis());
                cstmt.setString(37, locomotorneedsdto.getCervicalcollar());
                cstmt.setString(38, locomotorneedsdto.getLsbrace());
                cstmt.setString(39, locomotorneedsdto.getTlsobrace());
                cstmt.setString(40, locomotorneedsdto.getFeeding());
                cstmt.setString(41, locomotorneedsdto.getBathing());
                cstmt.setString(42, locomotorneedsdto.getBrushing());
                cstmt.setString(43, locomotorneedsdto.getCombing());
                cstmt.setString(44, locomotorneedsdto.getDressing());
                cstmt.setString(45, locomotorneedsdto.getWriting());
                cstmt.setString(46, locomotorneedsdto.getDrivingcycling());
                cstmt.setString(47, locomotorneedsdto.getBedtransfer());
                cstmt.setString(48, locomotorneedsdto.getAnyotherinlocomotor());
                cstmt.setString(49, locomotorneedsdto.getLoginid());
                cstmt.setString(50, locomotorneedsdto.getSystemip());

                cstmt.setInt(51, Integer.parseInt(details[0].toString()));
                cstmt.setInt(52, Integer.parseInt(details[1].toString()));

                i = cstmt.executeUpdate();
            } else {
                i = -1;
            }
        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(datasource, "Error in inserting Need Assessment Details", code);
            // End
            int exResult = ExceptionDAO.saveException(datasource, exception.toString(), "insertLocomotorneeds", "LocomotorneedsDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LocomotorneedsDAO", "insertLocomotorneeds");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
//          /  DBConnection.closeStatement(stmt);
            //DBConnection.closeStatement(st);
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
    public LocomotorneedsDTO getLocomotorneeds(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        Connection con = null;
        CallableStatement cstmt = null;
        LocomotorneedsDTO locomotorneedsdto = new LocomotorneedsDTO();
        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_tblPerson_LocomotorNeeds_Details_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                locomotorneedsdto.setPhysiotheraphy(rs.getString("Physiotherapyforthreeyears"));
                locomotorneedsdto.setOccupationaltheraphy(rs.getString("OccupationalTherapyforthreeyears"));
                locomotorneedsdto.setSurgery(rs.getString("Surgery"));
                locomotorneedsdto.setPhysiotherapy(rs.getString("Physiotherapy"));
                locomotorneedsdto.setOccupationaltherapy(rs.getString("OccupationalTherapy"));
                locomotorneedsdto.setSelectwheelchair(rs.getString("WheelChair"));
                locomotorneedsdto.setSelecttricycle(rs.getString("Tricycle"));
                locomotorneedsdto.setSelectwalkingstick(rs.getString("WalkingStick"));
                locomotorneedsdto.setSelectcrutches(rs.getString("CrutchesType"));
                locomotorneedsdto.setCrutchestype(rs.getString("CrutchesSubType"));
                locomotorneedsdto.setSelectwalkingframe(rs.getString("WalkingFrame"));
                locomotorneedsdto.setAeroplanesplint(rs.getString("AeroplaneSplint"));
                locomotorneedsdto.setFigure8splint(rs.getString("FigureEightSplint"));
                locomotorneedsdto.setForearmsplint(rs.getString("ForeArmSplint"));
                locomotorneedsdto.setHandsplint(rs.getString("HandSplint"));
                locomotorneedsdto.setShoulderprosthesis(rs.getString("ShoulderProsthesis"));
                locomotorneedsdto.setAboveelbowprosthesis(rs.getString("AboveElbowProsthesis"));
                locomotorneedsdto.setElbowdisarticulationprosthesis(rs.getString("ElbowDisarticulationProsthesis"));
                locomotorneedsdto.setBelowelbowprosthesis(rs.getString("BelowElbowProsthesis"));
                locomotorneedsdto.setWristdisarticulationprosthesis(rs.getString("WristDisarticulationProsthesis"));
                locomotorneedsdto.setHandprosthesis(rs.getString("HandProsthesis"));
                locomotorneedsdto.setCosmeticfingerprosthesis(rs.getString("CosmeticFingerProsthesis"));
                locomotorneedsdto.setHkafo(rs.getString("HKAFO"));
                locomotorneedsdto.setKafo(rs.getString("KAFO"));
                locomotorneedsdto.setAfo(rs.getString("AFO"));
                locomotorneedsdto.setKneeorthosis(rs.getString("KneeOrthosis"));
                locomotorneedsdto.setDbsplint(rs.getString("DBSplint"));
                locomotorneedsdto.setModifiedshoe(rs.getString("ModifiedShoe"));
                locomotorneedsdto.setSerialcasting(rs.getString("SerialCasting"));
                locomotorneedsdto.setHipprothesis(rs.getString("HipDisarticulationProsthesis"));
                locomotorneedsdto.setAbovekneeprothesis(rs.getString("AboveKneeProsthesis"));
                locomotorneedsdto.setKneedisarticulation(rs.getString("KneeDisarticulationProsthesis"));
                locomotorneedsdto.setBelowkneeprothesis(rs.getString("BelowKneeProsthesis"));
                locomotorneedsdto.setSymeprothesis(rs.getString("SymesProsthesis"));
                locomotorneedsdto.setPartialfoodprothesis(rs.getString("PartialFootProsthesis"));
                locomotorneedsdto.setCervicalcollar(rs.getString("CervicalCollar"));
                locomotorneedsdto.setLsbrace(rs.getString("LBBrace"));
                locomotorneedsdto.setTlsobrace(rs.getString("TLSOBrace"));
                locomotorneedsdto.setFeeding(rs.getString("Feeding"));
                locomotorneedsdto.setBathing(rs.getString("Toileting"));
                locomotorneedsdto.setBrushing(rs.getString("Brushing"));
                locomotorneedsdto.setCombing(rs.getString("Combing"));
                locomotorneedsdto.setDressing(rs.getString("Dressing"));
                locomotorneedsdto.setWriting(rs.getString("Writing"));
                locomotorneedsdto.setDrivingcycling(rs.getString("Driving"));
                locomotorneedsdto.setBedtransfer(rs.getString("BedTransfer"));
                locomotorneedsdto.setAnyotherinlocomotor(rs.getString("AnyOther"));

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getLocomotorneeds", "LocomotorneedsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LocomotorneedsDAO", "getLocomotorneeds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getLocomotorneeds", "LocomotorneedsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LocomotorneedsDAO", "getLocomotorneeds");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return locomotorneedsdto;
    }

    /**
     * 
     * @param datasource 
     * @param locomotorneedsdto 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public int updateLocomotorneeds(DataSource datasource, LocomotorneedsDTO locomotorneedsdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        Connection con = null;
        CallableStatement cstmt = null;
        //Statement stmt = null; 
        PreparedStatement pstmt =null;
        String code = locomotorneedsdto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        int i = 0;
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(datasource, code);
            con = DBConnection.getConnection();
            
            pstmt = con.prepareStatement("select Person_Code from tblPerson_LocomotorNeeds_Details where Person_Code=?"); 
            
            pstmt.setString(1, code);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                cstmt = con.prepareCall("{Call SP_tblPerson_LocomotorNeeds_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, locomotorneedsdto.getPersoncode());
                cstmt.setString(2, locomotorneedsdto.getPhysiotheraphy());
                cstmt.setString(3, locomotorneedsdto.getOccupationaltheraphy());
                cstmt.setString(4, locomotorneedsdto.getSurgery());
                cstmt.setString(5, locomotorneedsdto.getPhysiotherapy());
                cstmt.setString(6, locomotorneedsdto.getOccupationaltherapy());
                cstmt.setString(7, locomotorneedsdto.getSelectwheelchair());
                cstmt.setString(8, locomotorneedsdto.getSelecttricycle());
                cstmt.setString(9, locomotorneedsdto.getSelectwalkingstick());
                cstmt.setString(10, locomotorneedsdto.getSelectcrutches());
                cstmt.setString(11, locomotorneedsdto.getCrutchestype());
                cstmt.setString(12, locomotorneedsdto.getSelectwalkingframe());
                cstmt.setString(13, locomotorneedsdto.getAeroplanesplint());
                cstmt.setString(14, locomotorneedsdto.getFigure8splint());
                cstmt.setString(15, locomotorneedsdto.getForearmsplint());
                cstmt.setString(16, locomotorneedsdto.getHandsplint());
                cstmt.setString(17, locomotorneedsdto.getShoulderprosthesis());
                cstmt.setString(18, locomotorneedsdto.getAboveelbowprosthesis());
                cstmt.setString(19, locomotorneedsdto.getElbowdisarticulationprosthesis());
                cstmt.setString(20, locomotorneedsdto.getBelowelbowprosthesis());
                cstmt.setString(21, locomotorneedsdto.getWristdisarticulationprosthesis());
                cstmt.setString(22, locomotorneedsdto.getHandprosthesis());
                cstmt.setString(23, locomotorneedsdto.getCosmeticfingerprosthesis());
                cstmt.setString(24, locomotorneedsdto.getHkafo());
                cstmt.setString(25, locomotorneedsdto.getKafo());
                cstmt.setString(26, locomotorneedsdto.getAfo());
                cstmt.setString(27, locomotorneedsdto.getKneeorthosis());
                cstmt.setString(28, locomotorneedsdto.getDbsplint());
                cstmt.setString(29, locomotorneedsdto.getModifiedshoe());
                cstmt.setString(30, locomotorneedsdto.getSerialcasting());
                cstmt.setString(31, locomotorneedsdto.getHipprothesis());
                cstmt.setString(32, locomotorneedsdto.getAbovekneeprothesis());
                cstmt.setString(33, locomotorneedsdto.getKneedisarticulation());
                cstmt.setString(34, locomotorneedsdto.getBelowkneeprothesis());
                cstmt.setString(35, locomotorneedsdto.getSymeprothesis());
                cstmt.setString(36, locomotorneedsdto.getPartialfoodprothesis());
                cstmt.setString(37, locomotorneedsdto.getCervicalcollar());
                cstmt.setString(38, locomotorneedsdto.getLsbrace());
                cstmt.setString(39, locomotorneedsdto.getTlsobrace());
                cstmt.setString(40, locomotorneedsdto.getFeeding());
                cstmt.setString(41, locomotorneedsdto.getBathing());
                cstmt.setString(42, locomotorneedsdto.getBrushing());
                cstmt.setString(43, locomotorneedsdto.getCombing());
                cstmt.setString(44, locomotorneedsdto.getDressing());
                cstmt.setString(45, locomotorneedsdto.getWriting());
                cstmt.setString(46, locomotorneedsdto.getDrivingcycling());
                cstmt.setString(47, locomotorneedsdto.getBedtransfer());
                cstmt.setString(48, locomotorneedsdto.getAnyotherinlocomotor());
                cstmt.setString(49, locomotorneedsdto.getLoginid());
                cstmt.setString(50, locomotorneedsdto.getSystemip());

                cstmt.setInt(51, Integer.parseInt(details[0].toString()));
                cstmt.setInt(52, Integer.parseInt(details[1].toString()));

                i = cstmt.executeUpdate();
            } else if (i == 0) {

                i = insertLocomotorneeds(datasource, locomotorneedsdto, request);
            }

        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(datasource, "Error in Updating Need Assessment Details", code);
            // End
            int exResult = ExceptionDAO.saveException(datasource, exception.toString(), "updateLocomotorneeds", "LocomotorneedsDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LocomotorneedsDAO", "updateLocomotorneeds");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            //DBConnection.closeStatement(stmt); 
            DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(cstmt);

        }
        return i;
    }

    /**
     *
     * @param datasource
     * @param locomotorneedsdto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return
     */
    public int insertLocomotorneedsAU(DataSource datasource, LocomotorneedsDTO locomotorneedsdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        Connection con = null; 
        //Statement stmt = null; 
        PreparedStatement pstmt = null;
        CallableStatement cstmt = null;
        String code = locomotorneedsdto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        int i = 0;
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(datasource, code);
            con = DBConnection.getConnection();



          //  stmt = con.createStatement();
            
            pstmt = con.prepareStatement("select * from tblPerson_LocomotorNeeds_Details where Person_Code=? and status='Active'");
            
            pstmt.setString(1,code);
            
            rs = pstmt.executeQuery();
            
            if (!rs.next()) {

                cstmt = con.prepareCall("{Call SP_tblPerson_LocomotorNeeds_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, locomotorneedsdto.getPersoncode());
                cstmt.setString(2, locomotorneedsdto.getPhysiotheraphy());
                cstmt.setString(3, locomotorneedsdto.getOccupationaltheraphy());
                cstmt.setString(4, locomotorneedsdto.getSurgery());
                cstmt.setString(5, locomotorneedsdto.getPhysiotherapy());
                cstmt.setString(6, locomotorneedsdto.getOccupationaltherapy());
                cstmt.setString(7, locomotorneedsdto.getSelectwheelchair());
                cstmt.setString(8, locomotorneedsdto.getSelecttricycle());
                cstmt.setString(9, locomotorneedsdto.getSelectwalkingstick());
                cstmt.setString(10, locomotorneedsdto.getSelectcrutches());
                cstmt.setString(11, locomotorneedsdto.getCrutchestype());
                cstmt.setString(12, locomotorneedsdto.getSelectwalkingframe());
                cstmt.setString(13, locomotorneedsdto.getAeroplanesplint());
                cstmt.setString(14, locomotorneedsdto.getFigure8splint());
                cstmt.setString(15, locomotorneedsdto.getForearmsplint());
                cstmt.setString(16, locomotorneedsdto.getHandsplint());
                cstmt.setString(17, locomotorneedsdto.getShoulderprosthesis());
                cstmt.setString(18, locomotorneedsdto.getAboveelbowprosthesis());
                cstmt.setString(19, locomotorneedsdto.getElbowdisarticulationprosthesis());
                cstmt.setString(20, locomotorneedsdto.getBelowelbowprosthesis());
                cstmt.setString(21, locomotorneedsdto.getWristdisarticulationprosthesis());
                cstmt.setString(22, locomotorneedsdto.getHandprosthesis());
                cstmt.setString(23, locomotorneedsdto.getCosmeticfingerprosthesis());
                cstmt.setString(24, locomotorneedsdto.getHkafo());
                cstmt.setString(25, locomotorneedsdto.getKafo());
                cstmt.setString(26, locomotorneedsdto.getAfo());
                cstmt.setString(27, locomotorneedsdto.getKneeorthosis());
                cstmt.setString(28, locomotorneedsdto.getDbsplint());
                cstmt.setString(29, locomotorneedsdto.getModifiedshoe());
                cstmt.setString(30, locomotorneedsdto.getSerialcasting());
                cstmt.setString(31, locomotorneedsdto.getHipprothesis());
                cstmt.setString(32, locomotorneedsdto.getAbovekneeprothesis());
                cstmt.setString(33, locomotorneedsdto.getKneedisarticulation());
                cstmt.setString(34, locomotorneedsdto.getBelowkneeprothesis());
                cstmt.setString(35, locomotorneedsdto.getSymeprothesis());
                cstmt.setString(36, locomotorneedsdto.getPartialfoodprothesis());
                cstmt.setString(37, locomotorneedsdto.getCervicalcollar());
                cstmt.setString(38, locomotorneedsdto.getLsbrace());
                cstmt.setString(39, locomotorneedsdto.getTlsobrace());
                cstmt.setString(40, locomotorneedsdto.getFeeding());
                cstmt.setString(41, locomotorneedsdto.getBathing());
                cstmt.setString(42, locomotorneedsdto.getBrushing());
                cstmt.setString(43, locomotorneedsdto.getCombing());
                cstmt.setString(44, locomotorneedsdto.getDressing());
                cstmt.setString(45, locomotorneedsdto.getWriting());
                cstmt.setString(46, locomotorneedsdto.getDrivingcycling());
                cstmt.setString(47, locomotorneedsdto.getBedtransfer());
                cstmt.setString(48, locomotorneedsdto.getAnyotherinlocomotor());
                cstmt.setString(49, locomotorneedsdto.getLoginid());
                cstmt.setString(50, locomotorneedsdto.getSystemip());

                cstmt.setInt(51, Integer.parseInt(details[0].toString()));
                cstmt.setInt(52, Integer.parseInt(details[1].toString()));

                i = cstmt.executeUpdate();
            } else {
                i = -1;
            }

        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(datasource, "Error in Inserting Need Assessment Details", code);
            // End
            int exResult = ExceptionDAO.saveException(datasource, exception.toString(), "insertLocomotorneedsAU", "LocomotorneedsDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LocomotorneedsDAO", "insertLocomotorneedsAU");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            //DBConnection.closeStatement(stmt); 
            DBConnection.closeStatement(pstmt);
            //DBConnection.closeStatement(st);
            DBConnection.closeStatement(cstmt);

        }
        return i;
    }
}
