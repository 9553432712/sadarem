
/*
 * HearingImpairmentDao.java
 *
 * Created on July 16, 2008, 12:27 PM
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
import org.bf.disability.dto.HearingImpairmentDto;
import org.bf.disability.form.HearingImpairmentActionForm;
import org.bf.disability.service.TransactionService;
import org.bf.disability.servicefactory.TransactionFactory;

import com.tcs.sgv.common.util.DBConnection;

/**
 * This DAO is used for inserting ,retriving and updating into database .
 * @author Sunima
 */
public class HearingImpairmentDao {

    /**
     * this method is used for insert into SP_tblHearing_Impairment_Details_Insert
     * @param datasource
     * @param hearingdto
     * @return int
     */
    public int insertData(DataSource ds, HearingImpairmentDto hearingdto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        CallableStatement cstmt1 = null;
        Connection con = null;
        int insert = 0;
        String code = hearingdto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);



            cstmt1 = con.prepareCall("{Call SP_tblHearing_Impairment_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt1.setString(1, code);
            //cstmt1.setInt(2,Integer.parseInt(hearingdto.getHearing_dblr()));
            cstmt1.setString(2, hearingdto.getSpeechtheraphy());
            cstmt1.setString(3, hearingdto.getSurgery());
            cstmt1.setString(4, hearingdto.getSpeechtherapy());
            cstmt1.setString(5, hearingdto.getLanguagedevelopment());
            cstmt1.setString(6, hearingdto.getHearingaidselect());
            cstmt1.setString(7, hearingdto.getHearingaidtype());
            cstmt1.setString(8, hearingdto.getCochlearimplantation());
            cstmt1.setString(9, hearingdto.getImplantablehearingaid());
            cstmt1.setString(10, hearingdto.getAnyotherhearingimpairement());
            cstmt1.setString(11, hearingdto.getLoginid());
            cstmt1.setString(12, hearingdto.getSystemip());

            cstmt1.setInt(13, Integer.parseInt(details[0].toString()));
            cstmt1.setInt(14, Integer.parseInt(details[1].toString()));

            insert = cstmt1.executeUpdate();
            cstmt1.close();



            cstmt1 = con.prepareCall("{Call SP_tblHearing_Impairment_Test_Details_for_Persons_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt1.setString(1, code);
            //cstmt1.setInt(2,Integer.parseInt(hearingdto.getHearing_dblr()));
            cstmt1.setString(2, hearingdto.getRightear250());
            cstmt1.setString(3, hearingdto.getRightear500());
            cstmt1.setString(4, hearingdto.getRightear1000());
            cstmt1.setString(5, hearingdto.getRightear2000());
            cstmt1.setString(6, hearingdto.getRightear4000());
            cstmt1.setString(7, hearingdto.getRightear8000());
            cstmt1.setString(8, hearingdto.getLeftear250());
            cstmt1.setString(9, hearingdto.getLeftear500());
            cstmt1.setString(10, hearingdto.getLeftear1000());
            cstmt1.setString(11, hearingdto.getLeftear2000());
            cstmt1.setString(12, hearingdto.getLeftear4000());
            cstmt1.setString(13, hearingdto.getLeftear8000());
            cstmt1.setString(14, hearingdto.getSpeechaudiometryrightear_pta());
            cstmt1.setString(15, hearingdto.getSpeechaudiometryleftear_pta());
            cstmt1.setString(16, hearingdto.getRighteardblevel());
            cstmt1.setString(17, hearingdto.getLefteardblevel());
            cstmt1.setDouble(18, hearingdto.getTotalpercent());
            cstmt1.setString(19, hearingdto.getLoginid());
            cstmt1.setString(20, hearingdto.getSystemip());

            cstmt1.setInt(21, Integer.parseInt(details[0].toString()));
            cstmt1.setInt(22, Integer.parseInt(details[1].toString()));

            insert = cstmt1.executeUpdate();
            cstmt1.close();

            con.commit();
            con.setAutoCommit(true);

        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in inserting Hearing Impairment Details", code);
            // End
            insert = -2;
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "insertData", "HearingImpairmentDao", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentDao", "insertData");



        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt1);


        }
        return insert;
    }

    /**
     * this method is used for insert into SP_tblHearing_Impairment_Details_Insert
     * @param datasource
     * @param hearingdto
     * @return int
     */
    public int insertDataAU(DataSource ds, HearingImpairmentDto hearingdto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        CallableStatement cstmt1 = null;
        //Statement stmt = null; 
        PreparedStatement pstmt = null ;

        Connection con = null;
        int insert = 0;
        String code = hearingdto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();

        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);



            //stmt = con.createStatement();
            //rs = stmt.executeQuery();
            pstmt =con.prepareStatement("select * from tblHearing_Impairment_Details where person_code=? and status='Active'");
            
            
            pstmt.setString(1,code);
            
            rs = pstmt.executeQuery();
            
            
            
            if (!rs.next()) {

                cstmt1 = con.prepareCall("{Call SP_tblHearing_Impairment_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt1.setString(1, code);
                //cstmt1.setInt(2,Integer.parseInt(hearingdto.getHearing_dblr()));
                cstmt1.setString(2, hearingdto.getSpeechtheraphy());
                cstmt1.setString(3, hearingdto.getSurgery());
                cstmt1.setString(4, hearingdto.getSpeechtherapy());
                cstmt1.setString(5, hearingdto.getLanguagedevelopment());
                cstmt1.setString(6, hearingdto.getHearingaidselect());
                cstmt1.setString(7, hearingdto.getHearingaidtype());
                cstmt1.setString(8, hearingdto.getCochlearimplantation());
                cstmt1.setString(9, hearingdto.getImplantablehearingaid());
                cstmt1.setString(10, hearingdto.getAnyotherhearingimpairement());
                cstmt1.setString(11, hearingdto.getLoginid());
                cstmt1.setString(12, hearingdto.getSystemip());

                cstmt1.setInt(13, Integer.parseInt(details[0].toString()));
                cstmt1.setInt(14, Integer.parseInt(details[1].toString()));

                insert = cstmt1.executeUpdate();
                cstmt1.close();



                cstmt1 = con.prepareCall("{Call SP_tblHearing_Impairment_Test_Details_for_Persons_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt1.setString(1, code);
                //cstmt1.setInt(2,Integer.parseInt(hearingdto.getHearing_dblr()));
                cstmt1.setString(2, hearingdto.getRightear250());
                cstmt1.setString(3, hearingdto.getRightear500());
                cstmt1.setString(4, hearingdto.getRightear1000());
                cstmt1.setString(5, hearingdto.getRightear2000());
                cstmt1.setString(6, hearingdto.getRightear4000());
                cstmt1.setString(7, hearingdto.getRightear8000());
                cstmt1.setString(8, hearingdto.getLeftear250());
                cstmt1.setString(9, hearingdto.getLeftear500());
                cstmt1.setString(10, hearingdto.getLeftear1000());
                cstmt1.setString(11, hearingdto.getLeftear2000());
                cstmt1.setString(12, hearingdto.getLeftear4000());
                cstmt1.setString(13, hearingdto.getLeftear8000());
                cstmt1.setString(14, hearingdto.getSpeechaudiometryrightear_pta());
                cstmt1.setString(15, hearingdto.getSpeechaudiometryleftear_pta());
                cstmt1.setString(16, hearingdto.getRighteardblevel());
                cstmt1.setString(17, hearingdto.getLefteardblevel());
                cstmt1.setDouble(18, hearingdto.getTotalpercent());
                cstmt1.setString(19, hearingdto.getLoginid());
                cstmt1.setString(20, hearingdto.getSystemip());

                cstmt1.setInt(21, Integer.parseInt(details[0].toString()));
                cstmt1.setInt(22, Integer.parseInt(details[1].toString()));

                insert = cstmt1.executeUpdate();
                cstmt1.close();

                con.commit();
                con.setAutoCommit(true);
            } else {
                insert = -1;
            }

        } catch (Exception e) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in inserting Hearing Impairment Details", code);
            // End
            insert = -2;
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "insertDataAU", "HearingImpairmentDao", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentDao", "insertDataAU");


        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt1);
            //DBConnection.closeStatement(stmt); 
            DBConnection.closeStatement(pstmt);


        }
        return insert;
    }

    /**
     * this method is used to select .
     * @param datasource
     * @param personcode
     * @return hearingdto
     */
    public HearingImpairmentDto getHearingDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        CallableStatement cstmt = null;

        Connection con = null;
        String str = null;
        HearingImpairmentDto hearingdto = new HearingImpairmentDto();
        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_tblHearing_Impairment_Details_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                //hearingdto.setHearing_dblr(String.valueOf(rs.getInt("DB_Level")).trim());
                hearingdto.setSpeechtheraphy(rs.getString("SpeechTherapyforthreeyears"));
                hearingdto.setSurgery(rs.getString("Surgerey"));
                hearingdto.setSpeechtherapy(rs.getString("SpeechTherapy"));
                hearingdto.setLanguagedevelopment(rs.getString("LanguageDevelopment"));
                hearingdto.setHearingaidselect(rs.getString("HearingAidType"));
                hearingdto.setHearingaidtype(rs.getString("HearingAidSubType"));
                hearingdto.setCochlearimplantation(rs.getString("CochlearImplantation"));
                hearingdto.setImplantablehearingaid(rs.getString("ImplantableHearingAid"));
                hearingdto.setAnyotherhearingimpairement(rs.getString("AnyOther"));

            }
            rs.close();
            cstmt.close();
            cstmt = con.prepareCall("{Call SP_tblHearing_Impairment_Test_Details_for_Persons_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                //hearingdto.setHearing_dblr(String.valueOf(rs.getInt("DB_Level")).trim());
                hearingdto.setRightear250(rs.getString("AirConduction_Right_Ear_250"));
                hearingdto.setRightear500(rs.getString("AirConduction_Right_Ear_500"));
                hearingdto.setRightear1000(rs.getString("AirConduction_Right_Ear_1000"));
                hearingdto.setRightear2000(rs.getString("AirConduction_Right_Ear_2000"));
                hearingdto.setRightear4000(rs.getString("AirConduction_Right_Ear_4000"));
                hearingdto.setRightear8000(rs.getString("AirConduction_Right_Ear_8000"));
                hearingdto.setLeftear250(rs.getString("AirConduction_Left_Ear_250"));
                hearingdto.setLeftear500(rs.getString("AirConduction_Left_Ear_500"));
                hearingdto.setLeftear1000(rs.getString("AirConduction_Left_Ear_1000"));
                hearingdto.setLeftear2000(rs.getString("AirConduction_Left_Ear_2000"));
                hearingdto.setLeftear4000(rs.getString("AirConduction_Left_Ear_4000"));
                hearingdto.setLeftear8000(rs.getString("AirConduction_Left_Ear_8000"));
                hearingdto.setSpeechaudiometryrightear_pta(rs.getString("SpeechAudiometry_PTA_Right_Ear"));
                hearingdto.setSpeechaudiometryleftear_pta(rs.getString("SpeechAudiometry_PTA_Left_Ear"));
                hearingdto.setRighteardblevel(rs.getString("AirConduction_Right_Ear_DB_Level"));
                hearingdto.setLefteardblevel(rs.getString("AirConduction_Left_Ear_DB_Level"));
                hearingdto.setTotalpercent(rs.getDouble("Person_Hearing_Percentage"));
            }
            rs.close();
            cstmt.close();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHearingDetails", "HearingImpairmentDao", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentDao", "getHearingDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHearingDetails", "HearingImpairmentDao", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentDao", "getHearingDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return hearingdto;
    }

    /**
     * this method is used to update the data .
     * @param datasource
     * @param hearingdto
     */
    /* public void updateDetails(DataSource datasource,HearingImpairmentDto hearingdto,HttpServletRequest request,double percent)throws  SADAREMException {
    ResultSet rs=null;
    Statement stmt=null;
    CallableStatement cs=null;
    Connection con=null;
    HttpSession session = request.getSession();
    String code= hearingdto.getPersoncode();

    try{
    con=DBConnection.getConnection();
    con.setAutoCommit(false);

    if(percent==0){
    CommonDAO.changeStatusToInactiveForResetInUpdate(datasource,code,"tblHearing_Impairment_Details");
    CommonDAO.changeStatusToInactiveForResetInUpdate(datasource,code,"tblHearing_Impairment_Test_Details_for_Persons");
    }

    else{

    cs=con.prepareCall("{Call SP_tblHearing_Impairment_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
    cs.setString(1,hearingdto.getPersoncode());
    //cs.setInt(2,Integer.parseInt(hearingdto.getHearing_dblr()));
    cs.setString(2,hearingdto.getSpeechtheraphy());
    cs.setString(3,hearingdto.getSurgery());
    cs.setString(4,hearingdto.getSpeechtherapy());
    cs.setString(5,hearingdto.getLanguagedevelopment());
    cs.setString(6,hearingdto.getHearingaidselect());
    cs.setString(7,hearingdto.getHearingaidtype());
    cs.setString(8,hearingdto.getCochlearimplantation());
    cs.setString(9,hearingdto.getImplantablehearingaid());
    cs.setString(10,hearingdto.getAnyotherhearingimpairement());
    cs.setString(11,hearingdto.getLoginid());
    cs.setString(12,hearingdto.getSystemip());

    if(session.getAttribute("categoryIdUpdateAu")!=null && session.getAttribute("categoryCountUpdateAu")!=null) {
    cs.setInt(13, Integer.parseInt((String)session.getAttribute("categoryIdUpdateAu")));
    cs.setInt(14, Integer.parseInt((String)session.getAttribute("categoryCountUpdateAu")));
    }else {
    cs.setInt(13, 1);
    cs.setInt(14, 1);
    }


    cs.executeUpdate();
    cs.close();



    cs=con.prepareCall("{Call SP_tblHearing_Impairment_Test_Details_for_Persons_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
    cs.setString(1,hearingdto.getPersoncode());
    //cs.setInt(2,Integer.parseInt(hearingdto.getHearing_dblr()));
    cs.setString(2,hearingdto.getRightear250());
    cs.setString(3,hearingdto.getRightear500());
    cs.setString(4,hearingdto.getRightear1000());
    cs.setString(5,hearingdto.getRightear2000());
    cs.setString(6,hearingdto.getRightear4000());
    cs.setString(7,hearingdto.getRightear8000());
    cs.setString(8,hearingdto.getLeftear250());
    cs.setString(9,hearingdto.getLeftear500());
    cs.setString(10,hearingdto.getLeftear1000());
    cs.setString(11,hearingdto.getLeftear2000());
    cs.setString(12,hearingdto.getLeftear4000());
    cs.setString(13,hearingdto.getLeftear8000());

    cs.setString(14,hearingdto.getSpeechaudiometryrightear_pta());

    cs.setString(15,hearingdto.getSpeechaudiometryleftear_pta());

    cs.setString(16,hearingdto.getRighteardblevel());
    cs.setString(17,hearingdto.getLefteardblevel());
    cs.setDouble(18,hearingdto.getTotalpercent());
    cs.setString(19,hearingdto.getLoginid());
    cs.setString(20,hearingdto.getSystemip());

    if(session.getAttribute("categoryIdUpdateAu")!=null && session.getAttribute("categoryCountUpdateAu")!=null) {
    cs.setInt(21, Integer.parseInt((String)session.getAttribute("categoryIdUpdateAu")));
    cs.setInt(22, Integer.parseInt((String)session.getAttribute("categoryCountUpdateAu")));
    }else {
    cs.setInt(21, 1);
    cs.setInt(22, 1);
    }
    cs.executeUpdate();
    cs.close();

    con.commit();
    con.setAutoCommit(true);
    }
    } catch(SQLException sqlException) {
    sqlException.printStackTrace();
    throw new SADAREMException();
    } catch(Exception exception) {
    exception.printStackTrace();
    throw new SADAREMException();

    }

    finally{

    DBConnection.closeResultSet(rs);
    DBConnection.closeStatement(stmt);
    DBConnection.closeStatement(cs);
    DBConnection.closeConnection(con);
    }

    }*/
    /**
     * this method is used to update the data .
     * @param datasource
     * @param hearingdto
     */
    public void updateDetails(DataSource datasource, HearingImpairmentActionForm hearingdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        CallableStatement cs = null;
        Connection con = null;
        // HttpSession session = request.getSession();

        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        String code = hearingdto.getPersoncode();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(datasource, code);

            con = DBConnection.getConnection();
            
            con.setAutoCommit(false);



            if (hearingdto.getTotalpercent() == 0) {

                CommonDAO.changeStatusToInactiveForResetInUpdate(datasource, code, "tblHearing_Impairment_Details");
                CommonDAO.changeStatusToInactiveForResetInUpdate(datasource, code, "tblHearing_Impairment_Test_Details_for_Persons");
            } else {
                cs = con.prepareCall("{Call SP_tblHearing_Impairment_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cs.setString(1, hearingdto.getPersoncode());
                //cs.setInt(2,Integer.parseInt(hearingdto.getHearing_dblr()));
                cs.setString(2, hearingdto.getSpeechtheraphy());
                cs.setString(3, hearingdto.getSurgery());
                cs.setString(4, hearingdto.getSpeechtherapy());
                cs.setString(5, hearingdto.getLanguagedevelopment());
                cs.setString(6, hearingdto.getHearingaidselect());
                cs.setString(7, hearingdto.getHearingaidtype());
                cs.setString(8, hearingdto.getCochlearimplantation());
                cs.setString(9, hearingdto.getImplantablehearingaid());
                cs.setString(10, hearingdto.getAnyotherhearingimpairement());
                cs.setString(11, hearingdto.getLoginid());
                cs.setString(12, hearingdto.getSystemip());

                cs.setInt(13, Integer.parseInt(details[0].toString()));
                cs.setInt(14, Integer.parseInt(details[1].toString()));

                cs.executeUpdate();
                cs.close();



                cs = con.prepareCall("{Call SP_tblHearing_Impairment_Test_Details_for_Persons_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cs.setString(1, hearingdto.getPersoncode());
                //cs.setInt(2,Integer.parseInt(hearingdto.getHearing_dblr()));
                cs.setString(2, hearingdto.getRightear250());
                cs.setString(3, hearingdto.getRightear500());
                cs.setString(4, hearingdto.getRightear1000());
                cs.setString(5, hearingdto.getRightear2000());
                cs.setString(6, hearingdto.getRightear4000());
                cs.setString(7, hearingdto.getRightear8000());
                cs.setString(8, hearingdto.getLeftear250());
                cs.setString(9, hearingdto.getLeftear500());
                cs.setString(10, hearingdto.getLeftear1000());
                cs.setString(11, hearingdto.getLeftear2000());
                cs.setString(12, hearingdto.getLeftear4000());
                cs.setString(13, hearingdto.getLeftear8000());

                cs.setString(14, hearingdto.getSpeechaudiometryrightear_pta());

                cs.setString(15, hearingdto.getSpeechaudiometryleftear_pta());

                cs.setString(16, hearingdto.getRighteardblevel());
                cs.setString(17, hearingdto.getLefteardblevel());
                cs.setDouble(18, hearingdto.getTotalpercent());
                cs.setString(19, hearingdto.getLoginid());
                cs.setString(20, hearingdto.getSystemip());

                cs.setInt(21, Integer.parseInt(details[0].toString()));
                cs.setInt(22, Integer.parseInt(details[1].toString()));

                cs.executeUpdate();
                cs.close();

                con.commit();
                con.setAutoCommit(true);
            }
        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(datasource, "Error in inserting EVALUATION OF PHYSICAL IMPAIRMENTS Details", code);
            // End

            int exResult = ExceptionDAO.saveException(datasource, exception.toString(), "updateDetails", "HearingImpairmentDao", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentDao", "updateDetails");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(cs);

        }

    }

    /**
     * this method is used to check the person code.
     * @param ds
     * @param personcode
     */
    public boolean checkPersoncode(String personcode, DataSource ds) throws SADAREMDBException, SQLException {
        boolean personcodecheckflag = false;
        Connection con = null;
        ResultSet rs = null;
       // Statement stmt = null;
        PreparedStatement pstmt = null ;
        try {
            con = DBConnection.getConnection(); 
            
            String query = "select Person_Code from tblHearing_Impairment_Details where Person_Code=? and status='Active'";            
            pstmt = con.prepareStatement(query);            
            pstmt.setString(1, personcode);
            
            rs = pstmt.executeQuery();
            
            if (rs.next() == false) {
                personcodecheckflag = false;
            } else {
                personcodecheckflag = true;
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersoncode", "HearingImpairmentDao", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentDao", "checkPersoncode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersoncode", "HearingImpairmentDao", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentDao", "checkPersoncode");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            //DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(pstmt);

        }
        return personcodecheckflag;
    }

    public String getHearingPercent(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        String personcodecheckflag = null;
        Connection con = null;
        ResultSet rs = null;
        //Statement stmt = null;
        PreparedStatement pstmt = null ;
        try {
            con = DBConnection.getConnection();
            String query = "select person_hearing_percentage from tblHearing_Impairment_Test_Details_for_Persons where person_code=? and status='Active'";
            
          pstmt = con.prepareStatement(query);  
            
          pstmt.setString(1, personcode);

          
            //stmt = con.createStatement();
            rs = pstmt.executeQuery();
            if (rs.next()) {
                personcodecheckflag = rs.getString(1);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHearingPercent", "HearingImpairmentDao", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentDao", "getHearingPercent");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHearingPercent", "HearingImpairmentDao", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentDao", "getHearingPercent");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            //DBConnection.closeStatement(stmt); 
            DBConnection.closeStatement(pstmt);


        }
        return personcodecheckflag;
    }

    public HearingImpairmentDto getHearingDetailsforprint(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        CallableStatement cstmt = null;
        PreparedStatement pstmt = null;
        Connection con = null;
        ResultSet rs1 = null;
        String str = null;
        HearingImpairmentDto hearingdto = null;
        try {
            con = DBConnection.getConnection();

            pstmt = con.prepareStatement("select * from tblHearing_Impairment_Test_Details_for_Persons where person_code=? and status='active'");
            pstmt.setString(1, personcode);
            rs1 = pstmt.executeQuery();
            if (rs1.next()) {
                hearingdto = new HearingImpairmentDto();
                cstmt = con.prepareCall("{Call SP_tblHearing_Impairment_Details_Select(?)}");
                cstmt.setString(1, personcode);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    //hearingdto.setHearing_dblr(String.valueOf(rs.getInt("DB_Level")).trim());
                    hearingdto.setSpeechtheraphy(rs.getString("SpeechTherapyforthreeyears"));
                    hearingdto.setSurgery(rs.getString("Surgerey"));
                    hearingdto.setSpeechtherapy(rs.getString("SpeechTherapy"));
                    hearingdto.setLanguagedevelopment(rs.getString("LanguageDevelopment"));
                    hearingdto.setHearingaidselect(rs.getString("HearingAidType"));
                    hearingdto.setHearingaidtype(rs.getString("HearingAidSubType"));
                    hearingdto.setCochlearimplantation(rs.getString("CochlearImplantation"));
                    hearingdto.setImplantablehearingaid(rs.getString("ImplantableHearingAid"));
                    hearingdto.setAnyotherhearingimpairement(rs.getString("AnyOther"));

                }

                cstmt = con.prepareCall("{Call SP_tblHearing_Impairment_Test_Details_for_Persons_Select(?)}");
                cstmt.setString(1, personcode);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    //hearingdto.setHearing_dblr(String.valueOf(rs.getInt("DB_Level")).trim());
                    hearingdto.setRightear250(rs.getString("AirConduction_Right_Ear_250"));
                    hearingdto.setRightear500(rs.getString("AirConduction_Right_Ear_500"));
                    hearingdto.setRightear1000(rs.getString("AirConduction_Right_Ear_1000"));
                    hearingdto.setRightear2000(rs.getString("AirConduction_Right_Ear_2000"));
                    hearingdto.setRightear4000(rs.getString("AirConduction_Right_Ear_4000"));
                    hearingdto.setRightear8000(rs.getString("AirConduction_Right_Ear_8000"));
                    hearingdto.setLeftear250(rs.getString("AirConduction_Left_Ear_250"));
                    hearingdto.setLeftear500(rs.getString("AirConduction_Left_Ear_500"));
                    hearingdto.setLeftear1000(rs.getString("AirConduction_Left_Ear_1000"));
                    hearingdto.setLeftear2000(rs.getString("AirConduction_Left_Ear_2000"));
                    hearingdto.setLeftear4000(rs.getString("AirConduction_Left_Ear_4000"));
                    hearingdto.setLeftear8000(rs.getString("AirConduction_Left_Ear_8000"));
                    hearingdto.setSpeechaudiometryrightear_pta(rs.getString("SpeechAudiometry_PTA_Right_Ear"));
                    hearingdto.setSpeechaudiometryleftear_pta(rs.getString("SpeechAudiometry_PTA_Left_Ear"));
                    hearingdto.setRighteardblevel(rs.getString("AirConduction_Right_Ear_DB_Level"));
                    hearingdto.setLefteardblevel(rs.getString("AirConduction_Left_Ear_DB_Level"));
                    hearingdto.setTotalpercent(rs.getDouble("Person_Hearing_Percentage"));
                }

                pstmt = con.prepareStatement("select surname,first_name,personname_telugu from tblperson_personal_details  with (nolock) where person_code=?");
                pstmt.setString(1, personcode);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    hearingdto.setTeluguname(rs.getString(1) + " " + rs.getString(2));
                }
                hearingdto.setPersoncode(personcode);


            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getHearingDetailsforprint", "HearingImpairmentDao", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentDao", "getHearingDetailsforprint");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getHearingDetailsforprint", "HearingImpairmentDao", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentDao", "getHearingDetailsforprint");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeResultSet(rs1);
            DBConnection.closeStatement(cstmt);
            //DBConnection.closeStatement(pstmt); 
            DBConnection.closeStatement(pstmt);

        }
        return hearingdto;
    }

    public HearingImpairmentDto Checknulls(HearingImpairmentDto h) throws SADAREMDBException, SQLException {


        String s = null;

        try {

            s = h.getLeftear250();
            if (s == null || (s != null && s.equalsIgnoreCase(""))) {
                s = "0";
                h.setLeftear250(s);
            }
            s = h.getLeftear500();
            if (s == null || (s != null && s.equalsIgnoreCase(""))) {
                s = "0";
                h.setLeftear500(s);
            }
            s = h.getLeftear1000();
            if (s == null || (s != null && s.equalsIgnoreCase(""))) {
                s = "0";
                h.setLeftear1000(s);
            }
            s = h.getLeftear2000();
            if (s == null || (s != null && s.equalsIgnoreCase(""))) {
                s = "0";
                h.setLeftear2000(s);
            }
            s = h.getLeftear4000();
            if (s == null || (s != null && s.equalsIgnoreCase(""))) {
                s = "0";
                h.setLeftear4000(s);
            }
            s = h.getLeftear8000();
            if (s == null || (s != null && s.equalsIgnoreCase(""))) {
                s = "0";
                h.setLeftear8000(s);
            }
            s = h.getRightear250();
            if (s == null || (s != null && s.equalsIgnoreCase(""))) {
                s = "0";
                h.setRightear250(s);
            }
            s = h.getRightear500();
            if (s == null || (s != null && s.equalsIgnoreCase(""))) {
                s = "0";
                h.setRightear500(s);
            }
            s = h.getRightear1000();
            if (s == null || (s != null && s.equalsIgnoreCase(""))) {
                s = "0";
                h.setRightear1000(s);
            }
            s = h.getRightear2000();
            if (s == null || (s != null && s.equalsIgnoreCase(""))) {
                s = "0";
                h.setRightear2000(s);
            }
            s = h.getRightear4000();
            if (s == null || (s != null && s.equalsIgnoreCase(""))) {
                s = "0";
                h.setRightear4000(s);
            }
            s = h.getRightear8000();
            if (s == null || (s != null && s.equalsIgnoreCase(""))) {
                s = "0";
                h.setRightear8000(s);
            }
            String speechaudiometryrightear_pta = h.getSpeechaudiometryrightear_pta();

            String speechaudiometryleftear_pta = h.getSpeechaudiometryleftear_pta();

            s = h.getSpeechaudiometryrightear_pta();
            if (s == null || (s != null && s.equalsIgnoreCase(""))) {
                s = "0";
                h.setSpeechaudiometryrightear_pta(s);
            }
            s = h.getSpeechaudiometryleftear_pta();
            if (s == null || (s != null && s.equalsIgnoreCase(""))) {
                s = "0";
                h.setSpeechaudiometryleftear_pta(s);
            }


        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        } finally {
        }
        return h;
    }
}



