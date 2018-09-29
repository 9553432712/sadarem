/*
 * VsmsScreeningTestDAO.java
 *
 * Created on October 16, 2008, 3:54 PM
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
import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.dto.VsmsScreeningTestDTO;
import org.bf.disability.service.TransactionService;
import org.bf.disability.servicefactory.TransactionFactory;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is for doing manipulation on vsmsscreening to database
 * @author Sunima
 * @ 
 * @ This DAO is use for Inserting,Selecting,Updating the VsmsTestDetailsData.
 * @version 1.0
 */
public class VsmsScreeningTestDAO {

    /**
     * this method is used for insert into SP_tblVSMS_Test_Details_for_Zero_to_ThreeYears_Age_Persons_Insert,SP_tblVSMS_Test_Details_for_FourYears_to_SixYears_Age_Persons_Insert,SP_tblVSMS_Test_Details_for_SevenYears_to_NineYears_Age_Persons_Insert,SP_tblVSMS_Test_Details_for_TenYears_to_FifteenYears_Age_Persons_Insert.
     * 
     * @param ds 
     * @param vsmsscreeningtestdto 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public int insertVsmsScreeningTest(DataSource ds, VsmsScreeningTestDTO vsmsscreeningtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        
        CallableStatement cstmt = null;
        Connection con = null;
        int i = 0;
        String code = vsmsscreeningtestdto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();

        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();

        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);


            cstmt = con.prepareCall("{Call SP_tblVSMS_Test_Details_for_Zero_to_ThreeYears_Age_Persons_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, code);
            cstmt.setInt(2, vsmsscreeningtestdto.getVsms_0to1_CoolsLaughs());
            cstmt.setInt(3, vsmsscreeningtestdto.getVsms_0to1_Balenceshead());
            cstmt.setInt(4, vsmsscreeningtestdto.getVsms_0to1_Graphsobject());
            cstmt.setInt(5, vsmsscreeningtestdto.getVsms_0to1_Reachesforfamiliarpersons());
            cstmt.setInt(6, vsmsscreeningtestdto.getVsms_0to1_Rolls());
            cstmt.setInt(7, vsmsscreeningtestdto.getVsms_0to1_Reachesforobjects());
            cstmt.setInt(8, vsmsscreeningtestdto.getVsms_0to1_Occupies());
            cstmt.setInt(9, vsmsscreeningtestdto.getVsms_0to1_Sits());
            cstmt.setInt(10, vsmsscreeningtestdto.getVsms_0to1_Pulls());
            cstmt.setInt(11, vsmsscreeningtestdto.getVsms_0to1_Talks());
            cstmt.setInt(12, vsmsscreeningtestdto.getVsms_0to1_Drinks());
            cstmt.setInt(13, vsmsscreeningtestdto.getVsms_0to1_Moves());
            cstmt.setInt(14, vsmsscreeningtestdto.getVsms_0to1_Grasps());
            cstmt.setInt(15, vsmsscreeningtestdto.getVsms_0to1_Demands());
            cstmt.setInt(16, vsmsscreeningtestdto.getVsms_0to1_Stands());
            cstmt.setInt(17, vsmsscreeningtestdto.getVsms_0to1_Doesnotdrool());
            cstmt.setInt(18, vsmsscreeningtestdto.getVsms_0to1_Follows());
            cstmt.setInt(19, vsmsscreeningtestdto.getVsms_1to2_Walks());
            cstmt.setInt(20, vsmsscreeningtestdto.getVsms_1to2_Marks());
            cstmt.setInt(21, vsmsscreeningtestdto.getVsms_1to2_Masticates());
            cstmt.setInt(22, vsmsscreeningtestdto.getVsms_1to2_Pulls());
            cstmt.setInt(23, vsmsscreeningtestdto.getVsms_1to2_Transfers());
            cstmt.setInt(24, vsmsscreeningtestdto.getVsms_1to2_Overcomessimpleobstacles());
            cstmt.setInt(25, vsmsscreeningtestdto.getVsms_1to2_Fetches());
            cstmt.setInt(26, vsmsscreeningtestdto.getVsms_1to2_Drinks());
            cstmt.setInt(27, vsmsscreeningtestdto.getVsms_1to2_Walkswithoutsupport());
            cstmt.setInt(28, vsmsscreeningtestdto.getVsms_1to2_Plays());
            cstmt.setInt(29, vsmsscreeningtestdto.getVsms_1to2_Eats());
            cstmt.setInt(30, vsmsscreeningtestdto.getVsms_1to2_Goes());
            cstmt.setInt(31, vsmsscreeningtestdto.getVsms_1to2_Discriminates());
            cstmt.setInt(32, vsmsscreeningtestdto.getVsms_1to2_Usesnames());
            cstmt.setInt(33, vsmsscreeningtestdto.getVsms_1to2_Walksupstairs());
            cstmt.setInt(34, vsmsscreeningtestdto.getVsms_1to2_Unwarps());
            cstmt.setInt(35, vsmsscreeningtestdto.getVsms_1to2_Talks());
            cstmt.setInt(36, vsmsscreeningtestdto.getVsms_2to3_Signals());
            cstmt.setInt(37, vsmsscreeningtestdto.getVsms_2to3_Initiates());
            cstmt.setInt(38, vsmsscreeningtestdto.getVsms_2to3_Removesshirt());
            cstmt.setInt(39, vsmsscreeningtestdto.getVsms_2to3_Eatswithspoon());
            cstmt.setInt(40, vsmsscreeningtestdto.getVsms_2to3_Getsdrink());
            cstmt.setInt(41, vsmsscreeningtestdto.getVsms_2to3_Dries());
            cstmt.setInt(42, vsmsscreeningtestdto.getVsms_2to3_Avoids());
            cstmt.setInt(43, vsmsscreeningtestdto.getVsms_2to3_Putsonshirt());
            cstmt.setInt(44, vsmsscreeningtestdto.getVsms_2to3_Candopaperfolding());
            cstmt.setInt(45, vsmsscreeningtestdto.getVsms_2to3_Relates());
            cstmt.setString(46, vsmsscreeningtestdto.getLoginid());
            cstmt.setString(47, vsmsscreeningtestdto.getSystemip());

            cstmt.setInt(48, Integer.parseInt(details[0].toString()));
            cstmt.setInt(49, Integer.parseInt(details[1].toString()));

            i = cstmt.executeUpdate();
            cstmt.close();


            cstmt = con.prepareCall("{Call SP_tblVSMS_Test_Details_for_FourYears_to_SixYears_Age_Persons_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, code);
            cstmt.setInt(2, vsmsscreeningtestdto.getVsms_3to4_Walksdownsstairs());
            cstmt.setInt(3, vsmsscreeningtestdto.getVsms_3to4_Playscooperatively());
            cstmt.setInt(4, vsmsscreeningtestdto.getVsms_3to4_Buttons());
            cstmt.setInt(5, vsmsscreeningtestdto.getVsms_3to4_Helps());
            cstmt.setInt(6, vsmsscreeningtestdto.getVsms_3to4_Performs());
            cstmt.setInt(7, vsmsscreeningtestdto.getVsms_3to4_Washes());
            cstmt.setInt(8, vsmsscreeningtestdto.getVsms_4to5_Cares());
            cstmt.setInt(9, vsmsscreeningtestdto.getVsms_4to5_Prints());
            cstmt.setInt(10, vsmsscreeningtestdto.getVsms_4to5_Goesaboutneighbourhood());
            cstmt.setInt(11, vsmsscreeningtestdto.getVsms_4to5_Dresses());
            cstmt.setInt(12, vsmsscreeningtestdto.getVsms_4to5_Usespencil());
            cstmt.setInt(13, vsmsscreeningtestdto.getVsms_4to5_Playscompetitive());
            cstmt.setInt(14, vsmsscreeningtestdto.getVsms_5to6_Useshoops());
            cstmt.setInt(15, vsmsscreeningtestdto.getVsms_5to6_Printssimplewords());
            cstmt.setInt(16, vsmsscreeningtestdto.getVsms_5to6_Playssimplegames());
            cstmt.setInt(17, vsmsscreeningtestdto.getVsms_5to6_trusted());
            cstmt.setInt(18, vsmsscreeningtestdto.getVsms_5to6_Goestoschool());
            cstmt.setString(19, vsmsscreeningtestdto.getLoginid());
            cstmt.setString(20, vsmsscreeningtestdto.getSystemip());

            cstmt.setInt(21, Integer.parseInt(details[0].toString()));
            cstmt.setInt(22, Integer.parseInt(details[1].toString()));

            int j = cstmt.executeUpdate();
            cstmt.close();



            cstmt = con.prepareCall("{Call SP_tblVSMS_Test_Details_for_SevenYears_to_NineYears_Age_Persons_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, code);
            cstmt.setInt(2, vsmsscreeningtestdto.getVsms_6to7_Mixes());
            cstmt.setInt(3, vsmsscreeningtestdto.getVsms_6to7_Usespencilorchalk());
            cstmt.setInt(4, vsmsscreeningtestdto.getVsms_6to7_Batches());
            cstmt.setInt(5, vsmsscreeningtestdto.getVsms_6to7_Goestobed());
            cstmt.setInt(6, vsmsscreeningtestdto.getVsms_7to8_Candifferentiatebetween());
            cstmt.setInt(7, vsmsscreeningtestdto.getVsms_7to8_Helps());
            cstmt.setInt(8, vsmsscreeningtestdto.getVsms_7to8_Understands());
            cstmt.setInt(9, vsmsscreeningtestdto.getVsms_7to8_Participates());
            cstmt.setInt(10, vsmsscreeningtestdto.getVsms_7to8_Combs());
            cstmt.setInt(11, vsmsscreeningtestdto.getVsms_8to9_Usestools());
            cstmt.setInt(12, vsmsscreeningtestdto.getVsms_8to9_routinehouseholdtasks());
            cstmt.setInt(13, vsmsscreeningtestdto.getVsms_8to9_Readsonowninitiative());
            cstmt.setInt(14, vsmsscreeningtestdto.getVsms_8to9_Batchesselfunaided());
            cstmt.setString(15, vsmsscreeningtestdto.getLoginid());
            cstmt.setString(16, vsmsscreeningtestdto.getSystemip());

            cstmt.setInt(17, Integer.parseInt(details[0].toString()));
            cstmt.setInt(18, Integer.parseInt(details[1].toString()));

            int k = cstmt.executeUpdate();
            cstmt.close();


            cstmt = con.prepareCall("{Call SP_tblVSMS_Test_Details_for_TenYears_to_FifteenYears_Age_Persons_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, code);
            cstmt.setInt(2, vsmsscreeningtestdto.getVsms_9to10_Caresforself());
            cstmt.setInt(3, vsmsscreeningtestdto.getVsms_9to10_Makesminorpurchases());
            cstmt.setInt(4, vsmsscreeningtestdto.getVsms_9to10_Goesabouthome());
            cstmt.setInt(5, vsmsscreeningtestdto.getVsms_10to11_Distinguishes());
            cstmt.setInt(6, vsmsscreeningtestdto.getVsms_10to11_Makesindependentchoice());
            cstmt.setInt(7, vsmsscreeningtestdto.getVsms_10to11_smallremunerativework());
            cstmt.setInt(8, vsmsscreeningtestdto.getVsms_10to11_Follows());
            cstmt.setInt(9, vsmsscreeningtestdto.getVsms_11to12_simplecreative());
            cstmt.setInt(10, vsmsscreeningtestdto.getVsms_11to12_lefttocare());
            cstmt.setInt(11, vsmsscreeningtestdto.getVsms_11to12_Enjoys());
            cstmt.setInt(12, vsmsscreeningtestdto.getVsms_12to15_Playsdifficult());
            cstmt.setInt(13, vsmsscreeningtestdto.getVsms_12to15_Exercisescomplete());
            cstmt.setInt(14, vsmsscreeningtestdto.getVsms_12to15_Buys());
            cstmt.setInt(15, vsmsscreeningtestdto.getVsms_12to15_Engages());
            cstmt.setInt(16, vsmsscreeningtestdto.getVsms_12to15_Performs());
            cstmt.setString(17, vsmsscreeningtestdto.getLoginid());
            cstmt.setString(18, vsmsscreeningtestdto.getSystemip());

            cstmt.setInt(19, Integer.parseInt(details[0].toString()));
            cstmt.setInt(20, Integer.parseInt(details[1].toString()));

            int l = cstmt.executeUpdate();
            cstmt.close();


            con.commit();
            con.setAutoCommit(true);
        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in inserting Vsms Screening Details", code);
            // End
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "insertVsmsScreeningTest", "VsmsScreeningTestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VsmsScreeningTestDAO", "insertVsmsScreeningTest");

        } finally {
            DBConnection.closeConnection(con);
			DBConnection.closeStatement(cstmt);
			DBConnection.closeConnection(con);

        }
        return i;
    }

    /**
     * this method is used for insert into SP_tblVSMS_Test_Details_for_Zero_to_ThreeYears_Age_Persons_Insert,SP_tblVSMS_Test_Details_for_FourYears_to_SixYears_Age_Persons_Insert,SP_tblVSMS_Test_Details_for_SevenYears_to_NineYears_Age_Persons_Insert,SP_tblVSMS_Test_Details_for_TenYears_to_FifteenYears_Age_Persons_Insert.
     *
     * @param ds
     * @param vsmsscreeningtestdto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return
     */
    public int insertVsmsScreeningTestAU(DataSource ds, VsmsScreeningTestDTO vsmsscreeningtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        CallableStatement cstmt = null;
        Connection con = null;
        int i = 0;
        String code = vsmsscreeningtestdto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();

        PreparedStatement pstmt = null;
        String zero = null;
        String four = null;
        String seven = null;

        try {

            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);


            pstmt = con.prepareStatement("select * from tblVSMS_Test_Details_for_Zero_to_ThreeYears_Age_Persons where Person_Code=? and status='Active'");
            pstmt.setString(1, code);
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                zero = rs.getString(1);
            }
            pstmt = con.prepareStatement("select * from tblVSMS_Test_Details_for_FourYears_to_SixYears_Age_Persons where Person_Code=? and status='Active'");
            pstmt.setString(1, code);
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                four = rs.getString(1);
            }
            pstmt = con.prepareStatement("select * from tblVSMS_Test_Details_for_SevenYears_to_NineYears_Age_Persons where Person_Code=? and status='Active'");
            pstmt.setString(1, code);
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                seven = rs.getString(1);
            }

            if (zero != null || four != null || seven != null) {

                cstmt = con.prepareCall("{Call SP_tblVSMS_Test_Details_for_Zero_to_ThreeYears_Age_Persons_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, code);
                cstmt.setInt(2, vsmsscreeningtestdto.getVsms_0to1_CoolsLaughs());
                cstmt.setInt(3, vsmsscreeningtestdto.getVsms_0to1_Balenceshead());
                cstmt.setInt(4, vsmsscreeningtestdto.getVsms_0to1_Graphsobject());
                cstmt.setInt(5, vsmsscreeningtestdto.getVsms_0to1_Reachesforfamiliarpersons());
                cstmt.setInt(6, vsmsscreeningtestdto.getVsms_0to1_Rolls());
                cstmt.setInt(7, vsmsscreeningtestdto.getVsms_0to1_Reachesforobjects());
                cstmt.setInt(8, vsmsscreeningtestdto.getVsms_0to1_Occupies());
                cstmt.setInt(9, vsmsscreeningtestdto.getVsms_0to1_Sits());
                cstmt.setInt(10, vsmsscreeningtestdto.getVsms_0to1_Pulls());
                cstmt.setInt(11, vsmsscreeningtestdto.getVsms_0to1_Talks());
                cstmt.setInt(12, vsmsscreeningtestdto.getVsms_0to1_Drinks());
                cstmt.setInt(13, vsmsscreeningtestdto.getVsms_0to1_Moves());
                cstmt.setInt(14, vsmsscreeningtestdto.getVsms_0to1_Grasps());
                cstmt.setInt(15, vsmsscreeningtestdto.getVsms_0to1_Demands());
                cstmt.setInt(16, vsmsscreeningtestdto.getVsms_0to1_Stands());
                cstmt.setInt(17, vsmsscreeningtestdto.getVsms_0to1_Doesnotdrool());
                cstmt.setInt(18, vsmsscreeningtestdto.getVsms_0to1_Follows());
                cstmt.setInt(19, vsmsscreeningtestdto.getVsms_1to2_Walks());
                cstmt.setInt(20, vsmsscreeningtestdto.getVsms_1to2_Marks());
                cstmt.setInt(21, vsmsscreeningtestdto.getVsms_1to2_Masticates());
                cstmt.setInt(22, vsmsscreeningtestdto.getVsms_1to2_Pulls());
                cstmt.setInt(23, vsmsscreeningtestdto.getVsms_1to2_Transfers());
                cstmt.setInt(24, vsmsscreeningtestdto.getVsms_1to2_Overcomessimpleobstacles());
                cstmt.setInt(25, vsmsscreeningtestdto.getVsms_1to2_Fetches());
                cstmt.setInt(26, vsmsscreeningtestdto.getVsms_1to2_Drinks());
                cstmt.setInt(27, vsmsscreeningtestdto.getVsms_1to2_Walkswithoutsupport());
                cstmt.setInt(28, vsmsscreeningtestdto.getVsms_1to2_Plays());
                cstmt.setInt(29, vsmsscreeningtestdto.getVsms_1to2_Eats());
                cstmt.setInt(30, vsmsscreeningtestdto.getVsms_1to2_Goes());
                cstmt.setInt(31, vsmsscreeningtestdto.getVsms_1to2_Discriminates());
                cstmt.setInt(32, vsmsscreeningtestdto.getVsms_1to2_Usesnames());
                cstmt.setInt(33, vsmsscreeningtestdto.getVsms_1to2_Walksupstairs());
                cstmt.setInt(34, vsmsscreeningtestdto.getVsms_1to2_Unwarps());
                cstmt.setInt(35, vsmsscreeningtestdto.getVsms_1to2_Talks());
                cstmt.setInt(36, vsmsscreeningtestdto.getVsms_2to3_Signals());
                cstmt.setInt(37, vsmsscreeningtestdto.getVsms_2to3_Initiates());
                cstmt.setInt(38, vsmsscreeningtestdto.getVsms_2to3_Removesshirt());
                cstmt.setInt(39, vsmsscreeningtestdto.getVsms_2to3_Eatswithspoon());
                cstmt.setInt(40, vsmsscreeningtestdto.getVsms_2to3_Getsdrink());
                cstmt.setInt(41, vsmsscreeningtestdto.getVsms_2to3_Dries());
                cstmt.setInt(42, vsmsscreeningtestdto.getVsms_2to3_Avoids());
                cstmt.setInt(43, vsmsscreeningtestdto.getVsms_2to3_Putsonshirt());
                cstmt.setInt(44, vsmsscreeningtestdto.getVsms_2to3_Candopaperfolding());
                cstmt.setInt(45, vsmsscreeningtestdto.getVsms_2to3_Relates());
                cstmt.setString(46, vsmsscreeningtestdto.getLoginid());
                cstmt.setString(47, vsmsscreeningtestdto.getSystemip());

                cstmt.setInt(48, Integer.parseInt(details[0].toString()));
                cstmt.setInt(49, Integer.parseInt(details[1].toString()));

                i = cstmt.executeUpdate();
                cstmt.close();


                cstmt = con.prepareCall("{Call SP_tblVSMS_Test_Details_for_FourYears_to_SixYears_Age_Persons_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, code);
                cstmt.setInt(2, vsmsscreeningtestdto.getVsms_3to4_Walksdownsstairs());
                cstmt.setInt(3, vsmsscreeningtestdto.getVsms_3to4_Playscooperatively());
                cstmt.setInt(4, vsmsscreeningtestdto.getVsms_3to4_Buttons());
                cstmt.setInt(5, vsmsscreeningtestdto.getVsms_3to4_Helps());
                cstmt.setInt(6, vsmsscreeningtestdto.getVsms_3to4_Performs());
                cstmt.setInt(7, vsmsscreeningtestdto.getVsms_3to4_Washes());
                cstmt.setInt(8, vsmsscreeningtestdto.getVsms_4to5_Cares());
                cstmt.setInt(9, vsmsscreeningtestdto.getVsms_4to5_Prints());
                cstmt.setInt(10, vsmsscreeningtestdto.getVsms_4to5_Goesaboutneighbourhood());
                cstmt.setInt(11, vsmsscreeningtestdto.getVsms_4to5_Dresses());
                cstmt.setInt(12, vsmsscreeningtestdto.getVsms_4to5_Usespencil());
                cstmt.setInt(13, vsmsscreeningtestdto.getVsms_4to5_Playscompetitive());
                cstmt.setInt(14, vsmsscreeningtestdto.getVsms_5to6_Useshoops());
                cstmt.setInt(15, vsmsscreeningtestdto.getVsms_5to6_Printssimplewords());
                cstmt.setInt(16, vsmsscreeningtestdto.getVsms_5to6_Playssimplegames());
                cstmt.setInt(17, vsmsscreeningtestdto.getVsms_5to6_trusted());
                cstmt.setInt(18, vsmsscreeningtestdto.getVsms_5to6_Goestoschool());
                cstmt.setString(19, vsmsscreeningtestdto.getLoginid());
                cstmt.setString(20, vsmsscreeningtestdto.getSystemip());

                cstmt.setInt(21, Integer.parseInt(details[0].toString()));
                cstmt.setInt(22, Integer.parseInt(details[1].toString()));

                int j = cstmt.executeUpdate();
                cstmt.close();



                cstmt = con.prepareCall("{Call SP_tblVSMS_Test_Details_for_SevenYears_to_NineYears_Age_Persons_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, code);
                cstmt.setInt(2, vsmsscreeningtestdto.getVsms_6to7_Mixes());
                cstmt.setInt(3, vsmsscreeningtestdto.getVsms_6to7_Usespencilorchalk());
                cstmt.setInt(4, vsmsscreeningtestdto.getVsms_6to7_Batches());
                cstmt.setInt(5, vsmsscreeningtestdto.getVsms_6to7_Goestobed());
                cstmt.setInt(6, vsmsscreeningtestdto.getVsms_7to8_Candifferentiatebetween());
                cstmt.setInt(7, vsmsscreeningtestdto.getVsms_7to8_Helps());
                cstmt.setInt(8, vsmsscreeningtestdto.getVsms_7to8_Understands());
                cstmt.setInt(9, vsmsscreeningtestdto.getVsms_7to8_Participates());
                cstmt.setInt(10, vsmsscreeningtestdto.getVsms_7to8_Combs());
                cstmt.setInt(11, vsmsscreeningtestdto.getVsms_8to9_Usestools());
                cstmt.setInt(12, vsmsscreeningtestdto.getVsms_8to9_routinehouseholdtasks());
                cstmt.setInt(13, vsmsscreeningtestdto.getVsms_8to9_Readsonowninitiative());
                cstmt.setInt(14, vsmsscreeningtestdto.getVsms_8to9_Batchesselfunaided());
                cstmt.setString(15, vsmsscreeningtestdto.getLoginid());
                cstmt.setString(16, vsmsscreeningtestdto.getSystemip());

                cstmt.setInt(17, Integer.parseInt(details[0].toString()));
                cstmt.setInt(18, Integer.parseInt(details[1].toString()));

                int k = cstmt.executeUpdate();
                cstmt.close();


                cstmt = con.prepareCall("{Call SP_tblVSMS_Test_Details_for_TenYears_to_FifteenYears_Age_Persons_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, code);
                cstmt.setInt(2, vsmsscreeningtestdto.getVsms_9to10_Caresforself());
                cstmt.setInt(3, vsmsscreeningtestdto.getVsms_9to10_Makesminorpurchases());
                cstmt.setInt(4, vsmsscreeningtestdto.getVsms_9to10_Goesabouthome());
                cstmt.setInt(5, vsmsscreeningtestdto.getVsms_10to11_Distinguishes());
                cstmt.setInt(6, vsmsscreeningtestdto.getVsms_10to11_Makesindependentchoice());
                cstmt.setInt(7, vsmsscreeningtestdto.getVsms_10to11_smallremunerativework());
                cstmt.setInt(8, vsmsscreeningtestdto.getVsms_10to11_Follows());
                cstmt.setInt(9, vsmsscreeningtestdto.getVsms_11to12_simplecreative());
                cstmt.setInt(10, vsmsscreeningtestdto.getVsms_11to12_lefttocare());
                cstmt.setInt(11, vsmsscreeningtestdto.getVsms_11to12_Enjoys());
                cstmt.setInt(12, vsmsscreeningtestdto.getVsms_12to15_Playsdifficult());
                cstmt.setInt(13, vsmsscreeningtestdto.getVsms_12to15_Exercisescomplete());
                cstmt.setInt(14, vsmsscreeningtestdto.getVsms_12to15_Buys());
                cstmt.setInt(15, vsmsscreeningtestdto.getVsms_12to15_Engages());
                cstmt.setInt(16, vsmsscreeningtestdto.getVsms_12to15_Performs());
                cstmt.setString(17, vsmsscreeningtestdto.getLoginid());
                cstmt.setString(18, vsmsscreeningtestdto.getSystemip());

                cstmt.setInt(19, Integer.parseInt(details[0].toString()));
                cstmt.setInt(20, Integer.parseInt(details[1].toString()));

                int l = cstmt.executeUpdate();
                cstmt.close();


                con.commit();
                con.setAutoCommit(true);
            } else {
                i = -1;
            }
        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in inserting Vsms Screening Details", code);
            // End
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "insertVsmsScreeningTestAU", "VsmsScreeningTestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VsmsScreeningTestDAO", "insertVsmsScreeningTestAU");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);
            DBConnection.closeStatement(pstmt);

        }
        return i;
    }

    /**
     * this method is used for select the data.
     * 
     * @return vsmsscreeningtestdto
     * @param datasource 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     */
    public VsmsScreeningTestDTO getVsmsScreeningTest(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        CallableStatement cstmt = null;
        Connection con = null;

        VsmsScreeningTestDTO vsmsscreeningtestdto = new VsmsScreeningTestDTO();
        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_tblVSMS_Test_Details_for_Zero_to_ThreeYears_Age_Persons_Select_Basedon_Person_Code(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                vsmsscreeningtestdto.setVsms_0to1_CoolsLaughs(rs.getInt(2));
                vsmsscreeningtestdto.setVsms_0to1_Balenceshead(rs.getInt(3));
                vsmsscreeningtestdto.setVsms_0to1_Graphsobject(rs.getInt(4));
                vsmsscreeningtestdto.setVsms_0to1_Reachesforfamiliarpersons(rs.getInt(5));
                vsmsscreeningtestdto.setVsms_0to1_Rolls(rs.getInt(6));
                vsmsscreeningtestdto.setVsms_0to1_Reachesforobjects(rs.getInt(7));
                vsmsscreeningtestdto.setVsms_0to1_Occupies(rs.getInt(8));
                vsmsscreeningtestdto.setVsms_0to1_Sits(rs.getInt(9));
                vsmsscreeningtestdto.setVsms_0to1_Pulls(rs.getInt(10));
                vsmsscreeningtestdto.setVsms_0to1_Talks(rs.getInt(11));
                vsmsscreeningtestdto.setVsms_0to1_Drinks(rs.getInt(12));
                vsmsscreeningtestdto.setVsms_0to1_Moves(rs.getInt(13));
                vsmsscreeningtestdto.setVsms_0to1_Grasps(rs.getInt(14));
                vsmsscreeningtestdto.setVsms_0to1_Demands(rs.getInt(15));
                vsmsscreeningtestdto.setVsms_0to1_Stands(rs.getInt(16));
                vsmsscreeningtestdto.setVsms_0to1_Doesnotdrool(rs.getInt(17));
                vsmsscreeningtestdto.setVsms_0to1_Follows(rs.getInt(18));
                vsmsscreeningtestdto.setVsms_1to2_Walks(rs.getInt(19));
                vsmsscreeningtestdto.setVsms_1to2_Marks(rs.getInt(20));
                vsmsscreeningtestdto.setVsms_1to2_Masticates(rs.getInt(21));
                vsmsscreeningtestdto.setVsms_1to2_Pulls(rs.getInt(22));
                vsmsscreeningtestdto.setVsms_1to2_Transfers(rs.getInt(23));
                vsmsscreeningtestdto.setVsms_1to2_Overcomessimpleobstacles(rs.getInt(24));
                vsmsscreeningtestdto.setVsms_1to2_Fetches(rs.getInt(25));
                vsmsscreeningtestdto.setVsms_1to2_Drinks(rs.getInt(26));
                vsmsscreeningtestdto.setVsms_1to2_Walkswithoutsupport(rs.getInt(27));
                vsmsscreeningtestdto.setVsms_1to2_Plays(rs.getInt(28));
                vsmsscreeningtestdto.setVsms_1to2_Eats(rs.getInt(29));
                vsmsscreeningtestdto.setVsms_1to2_Goes(rs.getInt(30));
                vsmsscreeningtestdto.setVsms_1to2_Discriminates(rs.getInt(31));
                vsmsscreeningtestdto.setVsms_1to2_Usesnames(rs.getInt(32));
                vsmsscreeningtestdto.setVsms_1to2_Walksupstairs(rs.getInt(33));
                vsmsscreeningtestdto.setVsms_1to2_Unwarps(rs.getInt(34));
                vsmsscreeningtestdto.setVsms_1to2_Talks(rs.getInt(35));
                vsmsscreeningtestdto.setVsms_2to3_Signals(rs.getInt(36));
                vsmsscreeningtestdto.setVsms_2to3_Initiates(rs.getInt(37));
                vsmsscreeningtestdto.setVsms_2to3_Removesshirt(rs.getInt(38));
                vsmsscreeningtestdto.setVsms_2to3_Eatswithspoon(rs.getInt(39));
                vsmsscreeningtestdto.setVsms_2to3_Getsdrink(rs.getInt(40));
                vsmsscreeningtestdto.setVsms_2to3_Dries(rs.getInt(41));
                vsmsscreeningtestdto.setVsms_2to3_Avoids(rs.getInt(42));
                vsmsscreeningtestdto.setVsms_2to3_Putsonshirt(rs.getInt(43));
                vsmsscreeningtestdto.setVsms_2to3_Candopaperfolding(rs.getInt(44));
                vsmsscreeningtestdto.setVsms_2to3_Relates(rs.getInt(45));

            }
            rs.close();
            cstmt.close();



            cstmt = con.prepareCall("{Call SP_tblVSMS_Test_Details_for_FourYears_to_SixYears_Age_Persons_Select_Basedon_Person_Code(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                vsmsscreeningtestdto.setVsms_3to4_Walksdownsstairs(rs.getInt(1));
                vsmsscreeningtestdto.setVsms_3to4_Playscooperatively(rs.getInt(2));
                vsmsscreeningtestdto.setVsms_3to4_Buttons(rs.getInt(3));
                vsmsscreeningtestdto.setVsms_3to4_Helps(rs.getInt(4));
                vsmsscreeningtestdto.setVsms_3to4_Performs(rs.getInt(5));
                vsmsscreeningtestdto.setVsms_3to4_Washes(rs.getInt(6));
                vsmsscreeningtestdto.setVsms_4to5_Cares(rs.getInt(7));
                vsmsscreeningtestdto.setVsms_4to5_Prints(rs.getInt(8));
                vsmsscreeningtestdto.setVsms_4to5_Goesaboutneighbourhood(rs.getInt(9));
                vsmsscreeningtestdto.setVsms_4to5_Dresses(rs.getInt(10));
                vsmsscreeningtestdto.setVsms_4to5_Usespencil(rs.getInt(11));
                vsmsscreeningtestdto.setVsms_4to5_Playscompetitive(rs.getInt(12));
                vsmsscreeningtestdto.setVsms_5to6_Useshoops(rs.getInt(13));
                vsmsscreeningtestdto.setVsms_5to6_Printssimplewords(rs.getInt(14));
                vsmsscreeningtestdto.setVsms_5to6_Playssimplegames(rs.getInt(15));
                vsmsscreeningtestdto.setVsms_5to6_trusted(rs.getInt(16));
                vsmsscreeningtestdto.setVsms_5to6_Goestoschool(rs.getInt(17));

            }
            rs.close();
            cstmt.close();

            cstmt = con.prepareCall("{Call SP_tblVSMS_Test_Details_for_SevenYears_to_NineYears_Age_Persons_Select_Basedon_Person_Code(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                vsmsscreeningtestdto.setVsms_6to7_Mixes(rs.getInt(1));
                vsmsscreeningtestdto.setVsms_6to7_Usespencilorchalk(rs.getInt(2));
                vsmsscreeningtestdto.setVsms_6to7_Batches(rs.getInt(3));
                vsmsscreeningtestdto.setVsms_6to7_Goestobed(rs.getInt(4));
                vsmsscreeningtestdto.setVsms_7to8_Candifferentiatebetween(rs.getInt(5));
                vsmsscreeningtestdto.setVsms_7to8_Helps(rs.getInt(6));
                vsmsscreeningtestdto.setVsms_7to8_Understands(rs.getInt(7));
                vsmsscreeningtestdto.setVsms_7to8_Participates(rs.getInt(8));
                vsmsscreeningtestdto.setVsms_7to8_Combs(rs.getInt(9));
                vsmsscreeningtestdto.setVsms_8to9_Usestools(rs.getInt(10));
                vsmsscreeningtestdto.setVsms_8to9_routinehouseholdtasks(rs.getInt(11));
                vsmsscreeningtestdto.setVsms_8to9_Readsonowninitiative(rs.getInt(12));
                vsmsscreeningtestdto.setVsms_8to9_Batchesselfunaided(rs.getInt(13));

            }
            rs.close();
            cstmt.close();


            cstmt = con.prepareCall("{Call SP_tblVSMS_Test_Details_for_TenYears_to_FifteenYears_Age_Persons_Select_Basedon_Person_Code(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                vsmsscreeningtestdto.setVsms_9to10_Caresforself(rs.getInt(1));
                vsmsscreeningtestdto.setVsms_9to10_Makesminorpurchases(rs.getInt(2));
                vsmsscreeningtestdto.setVsms_9to10_Goesabouthome(rs.getInt(3));
                vsmsscreeningtestdto.setVsms_10to11_Distinguishes(rs.getInt(4));
                vsmsscreeningtestdto.setVsms_10to11_Makesindependentchoice(rs.getInt(5));
                vsmsscreeningtestdto.setVsms_10to11_smallremunerativework(rs.getInt(6));
                vsmsscreeningtestdto.setVsms_10to11_Follows(rs.getInt(7));
                vsmsscreeningtestdto.setVsms_11to12_simplecreative(rs.getInt(8));
                vsmsscreeningtestdto.setVsms_11to12_lefttocare(rs.getInt(9));
                vsmsscreeningtestdto.setVsms_11to12_Enjoys(rs.getInt(10));
                vsmsscreeningtestdto.setVsms_12to15_Playsdifficult(rs.getInt(11));
                vsmsscreeningtestdto.setVsms_12to15_Exercisescomplete(rs.getInt(12));
                vsmsscreeningtestdto.setVsms_12to15_Buys(rs.getInt(13));
                vsmsscreeningtestdto.setVsms_12to15_Engages(rs.getInt(14));
                vsmsscreeningtestdto.setVsms_12to15_Performs(rs.getInt(15));
            }
            rs.close();
            cstmt.close();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVsmsScreeningTest", "VsmsScreeningTestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VsmsScreeningTestDAO", "getVsmsScreeningTest");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVsmsScreeningTest", "VsmsScreeningTestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VsmsScreeningTestDAO", "getVsmsScreeningTest");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return vsmsscreeningtestdto;
    }

    /**
     * this method is used for updating the data.
     * 
     * @param ds 
     * @param vsmsscreeningtestdto 
     * @throws org.bf.disability.Exception.SADAREMException 
     */
    public void updateVsmsScreeningTest(DataSource ds, VsmsScreeningTestDTO vsmsscreeningtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        Connection con = null;
        CallableStatement cstmt = null;
        String code = vsmsscreeningtestdto.getPersoncode();
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


            if (vsmsscreeningtestdto.getMonth() == 0) {

                //   query="delete from tblVSMS_Test_Details_for_Zero_to_ThreeYears_Age_Persons where Person_Code="+code+";delete from tblVSMS_Test_Details_for_FourYears_to_SixYears_Age_Persons where Person_Code="+code+";delete from tblVSMS_Test_Details_for_SevenYears_to_NineYears_Age_Persons where Person_Code="+code+";delete from tblVSMS_Test_Details_for_TenYears_to_FifteenYears_Age_Persons where Person_Code="+code+";";
//                stmt=con.createStatement();
                //              int t=stmt.executeUpdate(query);
                CommonDAO.changeStatusToInactiveForResetInUpdate(ds, code, "tblVSMS_Test_Details_for_Zero_to_ThreeYears_Age_Persons");
                CommonDAO.changeStatusToInactiveForResetInUpdate(ds, code, "tblVSMS_Test_Details_for_FourYears_to_SixYears_Age_Persons");
                CommonDAO.changeStatusToInactiveForResetInUpdate(ds, code, "tblVSMS_Test_Details_for_SevenYears_to_NineYears_Age_Persons");
                CommonDAO.changeStatusToInactiveForResetInUpdate(ds, code, "tblVSMS_Test_Details_for_TenYears_to_FifteenYears_Age_Persons");
                vsmsscreeningtestdto.setMonth(0);
                vsmsscreeningtestdto.setYear(0);
                con.commit();
                con.setAutoCommit(true);
            } else {
                cstmt = con.prepareCall("{Call SP_tblVSMS_Test_Details_for_Zero_to_ThreeYears_Age_Persons_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, vsmsscreeningtestdto.getPersoncode());
                cstmt.setInt(2, vsmsscreeningtestdto.getVsms_0to1_CoolsLaughs());
                cstmt.setInt(3, vsmsscreeningtestdto.getVsms_0to1_Balenceshead());
                cstmt.setInt(4, vsmsscreeningtestdto.getVsms_0to1_Graphsobject());
                cstmt.setInt(5, vsmsscreeningtestdto.getVsms_0to1_Reachesforfamiliarpersons());
                cstmt.setInt(6, vsmsscreeningtestdto.getVsms_0to1_Rolls());
                cstmt.setInt(7, vsmsscreeningtestdto.getVsms_0to1_Reachesforobjects());
                cstmt.setInt(8, vsmsscreeningtestdto.getVsms_0to1_Occupies());
                cstmt.setInt(9, vsmsscreeningtestdto.getVsms_0to1_Sits());
                cstmt.setInt(10, vsmsscreeningtestdto.getVsms_0to1_Pulls());
                cstmt.setInt(11, vsmsscreeningtestdto.getVsms_0to1_Talks());
                cstmt.setInt(12, vsmsscreeningtestdto.getVsms_0to1_Drinks());
                cstmt.setInt(13, vsmsscreeningtestdto.getVsms_0to1_Moves());
                cstmt.setInt(14, vsmsscreeningtestdto.getVsms_0to1_Grasps());
                cstmt.setInt(15, vsmsscreeningtestdto.getVsms_0to1_Demands());
                cstmt.setInt(16, vsmsscreeningtestdto.getVsms_0to1_Stands());
                cstmt.setInt(17, vsmsscreeningtestdto.getVsms_0to1_Doesnotdrool());
                cstmt.setInt(18, vsmsscreeningtestdto.getVsms_0to1_Follows());
                cstmt.setInt(19, vsmsscreeningtestdto.getVsms_1to2_Walks());
                cstmt.setInt(20, vsmsscreeningtestdto.getVsms_1to2_Marks());
                cstmt.setInt(21, vsmsscreeningtestdto.getVsms_1to2_Masticates());
                cstmt.setInt(22, vsmsscreeningtestdto.getVsms_1to2_Pulls());
                cstmt.setInt(23, vsmsscreeningtestdto.getVsms_1to2_Transfers());
                cstmt.setInt(24, vsmsscreeningtestdto.getVsms_1to2_Overcomessimpleobstacles());
                cstmt.setInt(25, vsmsscreeningtestdto.getVsms_1to2_Fetches());
                cstmt.setInt(26, vsmsscreeningtestdto.getVsms_1to2_Drinks());
                cstmt.setInt(27, vsmsscreeningtestdto.getVsms_1to2_Walkswithoutsupport());
                cstmt.setInt(28, vsmsscreeningtestdto.getVsms_1to2_Plays());
                cstmt.setInt(29, vsmsscreeningtestdto.getVsms_1to2_Eats());
                cstmt.setInt(30, vsmsscreeningtestdto.getVsms_1to2_Goes());
                cstmt.setInt(31, vsmsscreeningtestdto.getVsms_1to2_Discriminates());
                cstmt.setInt(32, vsmsscreeningtestdto.getVsms_1to2_Usesnames());
                cstmt.setInt(33, vsmsscreeningtestdto.getVsms_1to2_Walksupstairs());
                cstmt.setInt(34, vsmsscreeningtestdto.getVsms_1to2_Unwarps());
                cstmt.setInt(35, vsmsscreeningtestdto.getVsms_1to2_Talks());
                cstmt.setInt(36, vsmsscreeningtestdto.getVsms_2to3_Signals());
                cstmt.setInt(37, vsmsscreeningtestdto.getVsms_2to3_Initiates());
                cstmt.setInt(38, vsmsscreeningtestdto.getVsms_2to3_Removesshirt());
                cstmt.setInt(39, vsmsscreeningtestdto.getVsms_2to3_Eatswithspoon());
                cstmt.setInt(40, vsmsscreeningtestdto.getVsms_2to3_Getsdrink());
                cstmt.setInt(41, vsmsscreeningtestdto.getVsms_2to3_Dries());
                cstmt.setInt(42, vsmsscreeningtestdto.getVsms_2to3_Avoids());
                cstmt.setInt(43, vsmsscreeningtestdto.getVsms_2to3_Putsonshirt());
                cstmt.setInt(44, vsmsscreeningtestdto.getVsms_2to3_Candopaperfolding());
                cstmt.setInt(45, vsmsscreeningtestdto.getVsms_2to3_Relates());
                cstmt.setString(46, vsmsscreeningtestdto.getLoginid());
                cstmt.setString(47, vsmsscreeningtestdto.getSystemip());

                cstmt.setInt(48, Integer.parseInt(details[0].toString()));
                cstmt.setInt(49, Integer.parseInt(details[1].toString()));
                cstmt.executeUpdate();


                cstmt = con.prepareCall("{Call SP_tblVSMS_Test_Details_for_FourYears_to_SixYears_Age_Persons_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, vsmsscreeningtestdto.getPersoncode());
                cstmt.setInt(2, vsmsscreeningtestdto.getVsms_3to4_Walksdownsstairs());
                cstmt.setInt(3, vsmsscreeningtestdto.getVsms_3to4_Playscooperatively());
                cstmt.setInt(4, vsmsscreeningtestdto.getVsms_3to4_Buttons());
                cstmt.setInt(5, vsmsscreeningtestdto.getVsms_3to4_Helps());
                cstmt.setInt(6, vsmsscreeningtestdto.getVsms_3to4_Performs());
                cstmt.setInt(7, vsmsscreeningtestdto.getVsms_3to4_Washes());
                cstmt.setInt(8, vsmsscreeningtestdto.getVsms_4to5_Cares());
                cstmt.setInt(9, vsmsscreeningtestdto.getVsms_4to5_Prints());
                cstmt.setInt(10, vsmsscreeningtestdto.getVsms_4to5_Goesaboutneighbourhood());
                cstmt.setInt(11, vsmsscreeningtestdto.getVsms_4to5_Dresses());
                cstmt.setInt(12, vsmsscreeningtestdto.getVsms_4to5_Usespencil());
                cstmt.setInt(13, vsmsscreeningtestdto.getVsms_4to5_Playscompetitive());
                cstmt.setInt(14, vsmsscreeningtestdto.getVsms_5to6_Useshoops());
                cstmt.setInt(15, vsmsscreeningtestdto.getVsms_5to6_Printssimplewords());
                cstmt.setInt(16, vsmsscreeningtestdto.getVsms_5to6_Playssimplegames());
                cstmt.setInt(17, vsmsscreeningtestdto.getVsms_5to6_trusted());
                cstmt.setInt(18, vsmsscreeningtestdto.getVsms_5to6_Goestoschool());
                cstmt.setString(19, vsmsscreeningtestdto.getLoginid());
                cstmt.setString(20, vsmsscreeningtestdto.getSystemip());

                cstmt.setInt(21, Integer.parseInt(details[0].toString()));
                cstmt.setInt(22, Integer.parseInt(details[1].toString()));
                cstmt.executeUpdate();


                cstmt = con.prepareCall("{Call SP_tblVSMS_Test_Details_for_SevenYears_to_NineYears_Age_Persons_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, vsmsscreeningtestdto.getPersoncode());
                cstmt.setInt(2, vsmsscreeningtestdto.getVsms_6to7_Mixes());
                cstmt.setInt(3, vsmsscreeningtestdto.getVsms_6to7_Usespencilorchalk());
                cstmt.setInt(4, vsmsscreeningtestdto.getVsms_6to7_Batches());
                cstmt.setInt(5, vsmsscreeningtestdto.getVsms_6to7_Goestobed());
                cstmt.setInt(6, vsmsscreeningtestdto.getVsms_7to8_Candifferentiatebetween());
                cstmt.setInt(7, vsmsscreeningtestdto.getVsms_7to8_Helps());
                cstmt.setInt(8, vsmsscreeningtestdto.getVsms_7to8_Understands());
                cstmt.setInt(9, vsmsscreeningtestdto.getVsms_7to8_Participates());
                cstmt.setInt(10, vsmsscreeningtestdto.getVsms_7to8_Combs());
                cstmt.setInt(11, vsmsscreeningtestdto.getVsms_8to9_Usestools());
                cstmt.setInt(12, vsmsscreeningtestdto.getVsms_8to9_routinehouseholdtasks());
                cstmt.setInt(13, vsmsscreeningtestdto.getVsms_8to9_Readsonowninitiative());
                cstmt.setInt(14, vsmsscreeningtestdto.getVsms_8to9_Batchesselfunaided());
                cstmt.setString(15, vsmsscreeningtestdto.getLoginid());
                cstmt.setString(16, vsmsscreeningtestdto.getSystemip());

                cstmt.setInt(17, Integer.parseInt(details[0].toString()));
                cstmt.setInt(18, Integer.parseInt(details[1].toString()));
                cstmt.executeUpdate();


                cstmt = con.prepareCall("{Call SP_tblVSMS_Test_Details_for_TenYears_to_FifteenYears_Age_Persons_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, vsmsscreeningtestdto.getPersoncode());
                cstmt.setInt(2, vsmsscreeningtestdto.getVsms_9to10_Caresforself());
                cstmt.setInt(3, vsmsscreeningtestdto.getVsms_9to10_Makesminorpurchases());
                cstmt.setInt(4, vsmsscreeningtestdto.getVsms_9to10_Goesabouthome());
                cstmt.setInt(5, vsmsscreeningtestdto.getVsms_10to11_Distinguishes());
                cstmt.setInt(6, vsmsscreeningtestdto.getVsms_10to11_Makesindependentchoice());
                cstmt.setInt(7, vsmsscreeningtestdto.getVsms_10to11_smallremunerativework());
                cstmt.setInt(8, vsmsscreeningtestdto.getVsms_10to11_Follows());
                cstmt.setInt(9, vsmsscreeningtestdto.getVsms_11to12_simplecreative());
                cstmt.setInt(10, vsmsscreeningtestdto.getVsms_11to12_lefttocare());
                cstmt.setInt(11, vsmsscreeningtestdto.getVsms_11to12_Enjoys());
                cstmt.setInt(12, vsmsscreeningtestdto.getVsms_12to15_Playsdifficult());
                cstmt.setInt(13, vsmsscreeningtestdto.getVsms_12to15_Exercisescomplete());
                cstmt.setInt(14, vsmsscreeningtestdto.getVsms_12to15_Buys());
                cstmt.setInt(15, vsmsscreeningtestdto.getVsms_12to15_Engages());
                cstmt.setInt(16, vsmsscreeningtestdto.getVsms_12to15_Performs());
                cstmt.setString(17, vsmsscreeningtestdto.getLoginid());
                cstmt.setString(18, vsmsscreeningtestdto.getSystemip());

                cstmt.setInt(19, Integer.parseInt(details[0].toString()));
                cstmt.setInt(20, Integer.parseInt(details[1].toString()));

                cstmt.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            }
        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in Updating VSMS Screening Test Details", code);
            // End
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "updateVsmsScreeningTest", "VsmsScreeningTestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VsmsScreeningTestDAO", "updateVsmsScreeningTest");



        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);


        }


    }

    /**
     * 
     * @ This Method is use for checking personcode.
     * @param personcode 
     * @param datasource 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return int
     */
    public boolean checkPersonCode(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        boolean code = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            String query = "select Person_Code from tblVSMS_Test_Details_for_Zero_to_ThreeYears_Age_Persons where Person_Code=? and status='Active'" + ";select Person_Code from tblVSMS_Test_Details_for_FourYears_to_SixYears_Age_Persons where Person_Code=? and status='Active'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            pstmt.setString(2, personcode);
            rs = pstmt.executeQuery();
            if (rs.next() == false) {
                code = false;
            } else {
                code = true;
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersonCode", "VsmsScreeningTestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VsmsScreeningTestDAO", "checkPersonCode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersonCode", "VsmsScreeningTestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VsmsScreeningTestDAO", "checkPersonCode");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return code;
    }

    public ArrayList getMentalItems(DataSource ds, String personCode) throws SADAREMDBException, SQLException {

        ArrayList list = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            String query = "select vsms_year,vsms_sq from tblPerson_MR_VSMS_Test_Details  where Person_Code=? and status='Active'";
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
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMentalItems", "VsmsScreeningTestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VsmsScreeningTestDAO", "getMentalItems");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMentalItems", "VsmsScreeningTestDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VsmsScreeningTestDAO", "getMentalItems");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }

        return list;
    }
}
