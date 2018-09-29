/*
 * EvaluationDAO.java
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
import org.bf.disability.dto.EvaluationDTO;
import org.bf.disability.service.TransactionService;
import org.bf.disability.servicefactory.TransactionFactory;

import com.tcs.sgv.common.util.DBConnection;

/**
 * This DAO is used for inserting ,retriving and updating into database .
 * @author Sunima
 */
public class EvaluationDAO {

    /**
     * this method is used for insert into SP_PIN_Details_Insert
     * @param datasource 
     * @param evaluationdto 
     * @return int
     */
    public int insertData(DataSource ds, EvaluationDTO evaluationdto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        CallableStatement cstmt1 = null;
        Connection con = null;
        int evalinsert = 0;
        String code = evaluationdto.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Statement st = null;

        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {

            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);


            cstmt1 = con.prepareCall("{Call SP_PIN_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt1.setString(1, code);
            cstmt1.setInt(2, Integer.parseInt(evaluationdto.getDominantupperextremity()));
            cstmt1.setInt(3, Integer.parseInt(evaluationdto.getLossofsensationupper()));
            cstmt1.setInt(4, Integer.parseInt(evaluationdto.getLossofsensationlower()));
            cstmt1.setInt(5, Integer.parseInt(evaluationdto.getNeurologicalstatus()));
            cstmt1.setInt(6, Integer.parseInt(evaluationdto.getIntellectualimpairment()));
            cstmt1.setInt(7, Integer.parseInt(evaluationdto.getSpeechdefect()));
            cstmt1.setInt(8, Integer.parseInt(evaluationdto.getE6a()));
            cstmt1.setInt(9, Integer.parseInt(evaluationdto.getE6b()));
            cstmt1.setInt(10, Integer.parseInt(evaluationdto.getE6c()));
            cstmt1.setInt(11, Integer.parseInt(evaluationdto.getE6d()));
            cstmt1.setInt(12, Integer.parseInt(evaluationdto.getE6e()));
            cstmt1.setInt(13, Integer.parseInt(evaluationdto.getE6f()));
            cstmt1.setInt(14, Integer.parseInt(evaluationdto.getE6g()));
            cstmt1.setInt(15, Integer.parseInt(evaluationdto.getE6h()));
            cstmt1.setInt(16, Integer.parseInt(evaluationdto.getSca()));
            cstmt1.setInt(17, Integer.parseInt(evaluationdto.getScb()));
            cstmt1.setInt(18, Integer.parseInt(evaluationdto.getScc()));
            cstmt1.setInt(19, Integer.parseInt(evaluationdto.getScd()));
            cstmt1.setString(20, evaluationdto.getMotorsystemLeftOrRight());
            cstmt1.setInt(21, Integer.parseInt(evaluationdto.getMotorsystem()));
            if (evaluationdto.getSensorysystemh() != null) {
                cstmt1.setInt(22, Integer.parseInt(evaluationdto.getSensorysystemh()));
            } else {
                cstmt1.setInt(22, 0);
            }
            if (evaluationdto.getSensorysystemf() != null) {
                cstmt1.setInt(23, Integer.parseInt(evaluationdto.getSensorysystemf()));
            } else {
                cstmt1.setInt(23, 0);
            }
            if (evaluationdto.getSensorysystemt() != null) {
                cstmt1.setInt(24, Integer.parseInt(evaluationdto.getSensorysystemt()));
            } else {
                cstmt1.setInt(24, 0);
            }
            cstmt1.setInt(25, Integer.parseInt(evaluationdto.getSensorysystem()));
            cstmt1.setInt(26, Integer.parseInt(evaluationdto.getSensorysystem1()));
            cstmt1.setInt(27, Integer.parseInt(evaluationdto.getSensorysystem2()));
            cstmt1.setInt(28, Integer.parseInt(evaluationdto.getBladderinvolvement()));
            cstmt1.setInt(29, Integer.parseInt(evaluationdto.getInjury()));
            cstmt1.setInt(30, Integer.parseInt(evaluationdto.getAtaxia()));
            cstmt1.setDouble(31, evaluationdto.getTotalpercent());
            cstmt1.setString(32, evaluationdto.getLoginid());
            cstmt1.setString(33, evaluationdto.getSystemip());

            cstmt1.setInt(34, Integer.parseInt(details[0].toString()));
            cstmt1.setInt(35, Integer.parseInt(details[1].toString()));

            evalinsert = cstmt1.executeUpdate();
            cstmt1.close();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            transactionService.updateTransactionDetails(ds, "Error in inserting EVALUATION OF PHYSICAL IMPAIRMENTS Details", code);
            evalinsert = -2;
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertData", "EvaluationDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EvaluationDAO", "insertData");
        } catch (Exception sqlEx) {
            transactionService.updateTransactionDetails(ds, "Error in inserting EVALUATION OF PHYSICAL IMPAIRMENTS Details", code);
            evalinsert = -2;
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertData", "EvaluationDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EvaluationDAO", "insertData");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cstmt1);
           DBConnection.closeConnection(con);
        }
        return evalinsert;
    }

    /**
     * this method is used for insert into SP_PIN_Details_Insert
     * @param datasource
     * @param evaluationdto
     * @return int
     */
    public int insertDataAU(DataSource ds, EvaluationDTO evaluationdto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ResultSet rs = null;
        CallableStatement cstmt1 = null;
        Connection con = null;
        int evalinsert = 0;
    //    Statement stmt = null;
        PreparedStatement pstmt = null;
        String code = evaluationdto.getPersoncode();
        // HttpSession session = request.getSession();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();

        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);



           // stmt = con.createStatement();
            
            pstmt=con.prepareStatement("select * from tblPhysical_Impairments_in_Neurological_Details where Person_Code=? and status='Active'");
            
            pstmt.setString(1, code);
            
            rs = pstmt.executeQuery();
            
            
            
            
            if (!rs.next()) {

                cstmt1 = con.prepareCall("{Call SP_PIN_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt1.setString(1, code);
                cstmt1.setInt(2, Integer.parseInt(evaluationdto.getDominantupperextremity()));
                cstmt1.setInt(3, Integer.parseInt(evaluationdto.getLossofsensationupper()));
                cstmt1.setInt(4, Integer.parseInt(evaluationdto.getLossofsensationlower()));
                cstmt1.setInt(5, Integer.parseInt(evaluationdto.getNeurologicalstatus()));
                cstmt1.setInt(6, Integer.parseInt(evaluationdto.getIntellectualimpairment()));
                cstmt1.setInt(7, Integer.parseInt(evaluationdto.getSpeechdefect()));
                cstmt1.setInt(8, Integer.parseInt(evaluationdto.getE6a()));
                cstmt1.setInt(9, Integer.parseInt(evaluationdto.getE6b()));
                cstmt1.setInt(10, Integer.parseInt(evaluationdto.getE6c()));
                cstmt1.setInt(11, Integer.parseInt(evaluationdto.getE6d()));
                cstmt1.setInt(12, Integer.parseInt(evaluationdto.getE6e()));
                cstmt1.setInt(13, Integer.parseInt(evaluationdto.getE6f()));
                cstmt1.setInt(14, Integer.parseInt(evaluationdto.getE6g()));
                cstmt1.setInt(15, Integer.parseInt(evaluationdto.getE6h()));
                cstmt1.setInt(16, Integer.parseInt(evaluationdto.getSca()));
                cstmt1.setInt(17, Integer.parseInt(evaluationdto.getScb()));
                cstmt1.setInt(18, Integer.parseInt(evaluationdto.getScc()));
                cstmt1.setInt(19, Integer.parseInt(evaluationdto.getScd()));
                cstmt1.setString(20, evaluationdto.getMotorsystemLeftOrRight());
                cstmt1.setInt(21, Integer.parseInt(evaluationdto.getMotorsystem()));
                if (evaluationdto.getSensorysystemh() != null) {
                    cstmt1.setInt(22, Integer.parseInt(evaluationdto.getSensorysystemh()));
                } else {
                    cstmt1.setInt(22, 0);
                }
                if (evaluationdto.getSensorysystemf() != null) {
                    cstmt1.setInt(23, Integer.parseInt(evaluationdto.getSensorysystemf()));
                } else {
                    cstmt1.setInt(23, 0);
                }
                if (evaluationdto.getSensorysystemt() != null) {
                    cstmt1.setInt(24, Integer.parseInt(evaluationdto.getSensorysystemt()));
                } else {
                    cstmt1.setInt(24, 0);
                }
                cstmt1.setInt(25, Integer.parseInt(evaluationdto.getSensorysystem()));
                cstmt1.setInt(26, Integer.parseInt(evaluationdto.getSensorysystem1()));
                cstmt1.setInt(27, Integer.parseInt(evaluationdto.getSensorysystem2()));
                cstmt1.setInt(28, Integer.parseInt(evaluationdto.getBladderinvolvement()));
                cstmt1.setInt(29, Integer.parseInt(evaluationdto.getInjury()));
                cstmt1.setInt(30, Integer.parseInt(evaluationdto.getAtaxia()));
                cstmt1.setDouble(31, evaluationdto.getTotalpercent());
                cstmt1.setString(32, evaluationdto.getLoginid());
                cstmt1.setString(33, evaluationdto.getSystemip());

                cstmt1.setInt(34, Integer.parseInt(details[0].toString()));
                cstmt1.setInt(35, Integer.parseInt(details[1].toString()));

                evalinsert = cstmt1.executeUpdate();
                cstmt1.close();
                con.commit();
                con.setAutoCommit(true);
            } else {
                evalinsert = -1;
            }
        } catch (SQLException sqlEx) {
            transactionService.updateTransactionDetails(ds, "Error in inserting EVALUATION OF PHYSICAL IMPAIRMENTS Details", code);
            evalinsert = -2;
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertDataAU", "EvaluationDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EvaluationDAO", "insertDataAU");
        } catch (Exception sqlEx) {
            transactionService.updateTransactionDetails(ds, "Error in inserting EVALUATION OF PHYSICAL IMPAIRMENTS Details", code);
            evalinsert = -2;
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertDataAU", "EvaluationDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EvaluationDAO", "insertDataAU");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cstmt1);
           //DBConnection.closeStatement(stmt); 
           DBConnection.closeStatement(pstmt);
        }
        return evalinsert;
    }

    /**
     * this method is used to select .
     * @param datasource 
     * @param personcode 
     * @return evaluationdto
     */
    public EvaluationDTO getEvaluationDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        CallableStatement cstmt = null;
        Connection con = null;
        String str = null;

        EvaluationDTO evaluationdto = new EvaluationDTO();
        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_PIN_Details_Select(?)}");
            cstmt.setString(1, personcode);

            rs = cstmt.executeQuery();
            while (rs.next()) {
                evaluationdto.setDominantupperextremity(String.valueOf(rs.getInt("Involvement_Upper_Extremity")));
                evaluationdto.setLossofsensationupper(String.valueOf(rs.getInt("Lossof_Sensation_Upper_Extremity")));
                evaluationdto.setLossofsensationlower(String.valueOf(rs.getInt("Lossof_Sensation_Lower_Extremity")));
                evaluationdto.setNeurologicalstatus(String.valueOf(rs.getInt("Altered_Sensorium")));
                evaluationdto.setIntellectualimpairment(String.valueOf(rs.getInt("Degreeof_Mental_Retardation")));
                evaluationdto.setSpeechdefect(String.valueOf(rs.getInt("Speech_Defect")));
                evaluationdto.setE6a(String.valueOf(rs.getInt("Oculomotor_Nerve")));
                evaluationdto.setE6b(String.valueOf(rs.getInt("Trochear_Nerve")));
                evaluationdto.setE6c(String.valueOf(rs.getInt("Abducens_Nerve")));
                evaluationdto.setE6d(String.valueOf(rs.getInt("Facial_Nerve")));
                evaluationdto.setE6e(String.valueOf(rs.getInt("Accessory_Nerve")));
                evaluationdto.setE6f(String.valueOf(rs.getInt("Hypoglossal_Nerve")));
                evaluationdto.setE6g(String.valueOf(rs.getInt("Trigeminal_Nerve")));
                evaluationdto.setE6h(String.valueOf(rs.getInt("Vagus_Nerve")));
                evaluationdto.setSca(String.valueOf(rs.getInt("Olfactory_Nerve")));
                evaluationdto.setScb(String.valueOf(rs.getInt("Optic_Nerve")));
                evaluationdto.setScc(String.valueOf(rs.getInt("Vestibulocochlear_Nerve")));
                evaluationdto.setScd(String.valueOf(rs.getInt("Glossopharyngeal_Nerve")));
                evaluationdto.setMotorsystemLeftOrRight(rs.getString("Neurological_Involvement_Side"));
                evaluationdto.setMotorsystem(String.valueOf(rs.getInt("Neurological_Involvement")));
                evaluationdto.setSensorysystemh(String.valueOf(rs.getInt("Extentof_Sensory_Hand")));
                evaluationdto.setSensorysystemf(String.valueOf(rs.getInt("Extentof_Sensory_Feet")));
                evaluationdto.setSensorysystemt(String.valueOf(rs.getInt("Extentof_Sensory_Trunk")));
                evaluationdto.setSensorysystem(String.valueOf(rs.getInt("Anaesthesia")));
                evaluationdto.setSensorysystem1(String.valueOf(rs.getInt("Hypoaesthesia")));
                evaluationdto.setSensorysystem2(String.valueOf(rs.getInt("Paraesthesis")));
                evaluationdto.setBladderinvolvement(String.valueOf(rs.getInt("Bladder_Involvement")));
                evaluationdto.setInjury(String.valueOf(rs.getInt("Frequentlyof_Convulsions")));
                evaluationdto.setAtaxia(String.valueOf(rs.getInt("Severityof_Ataxia")));
                evaluationdto.setTotalpercent(rs.getDouble("Total_Value"));

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getEvaluationDetails", "EvaluationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EvaluationDAO", "getEvaluationDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getEvaluationDetails", "EvaluationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EvaluationDAO", "getEvaluationDetails");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cstmt);

        }

        return evaluationdto;
    }

    /**
     * this method is used to update the data .
     * @param datasource
     * @param evaluationdto
     */
    public void updateDetails(DataSource datasource, EvaluationDTO evaluationdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        CallableStatement cs = null;
        Connection con = null;
        //HttpSession session = request.getSession();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        String code = evaluationdto.getPersoncode();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(datasource, evaluationdto.getPersoncode());
            con = DBConnection.getConnection();
            con.setAutoCommit(false);



            if (evaluationdto.getTotalpercent() == 0) {
                CommonDAO.changeStatusToInactiveForResetInUpdate(datasource, evaluationdto.getPersoncode(), "tblPhysical_Impairments_in_Neurological_Details");

//               query="delete from tblPhysical_Impairments_in_Neurological_Details where Person_Code="+evaluationdto.getPersoncode()+"";
//               stmt=con.createStatement();
//               int t=stmt.executeUpdate(query);
            } else {
                cs = con.prepareCall("{Call SP_PIN_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cs.setString(1, evaluationdto.getPersoncode());
                cs.setInt(2, Integer.parseInt(evaluationdto.getDominantupperextremity()));
                cs.setInt(3, Integer.parseInt(evaluationdto.getLossofsensationupper()));
                cs.setInt(4, Integer.parseInt(evaluationdto.getLossofsensationlower()));
                cs.setInt(5, Integer.parseInt(evaluationdto.getNeurologicalstatus()));
                cs.setInt(6, Integer.parseInt(evaluationdto.getIntellectualimpairment()));
                cs.setInt(7, Integer.parseInt(evaluationdto.getSpeechdefect()));
                cs.setInt(8, Integer.parseInt(evaluationdto.getE6a()));
                cs.setInt(9, Integer.parseInt(evaluationdto.getE6b()));
                cs.setInt(10, Integer.parseInt(evaluationdto.getE6c()));
                cs.setInt(11, Integer.parseInt(evaluationdto.getE6d()));
                cs.setInt(12, Integer.parseInt(evaluationdto.getE6e()));
                cs.setInt(13, Integer.parseInt(evaluationdto.getE6f()));
                cs.setInt(14, Integer.parseInt(evaluationdto.getE6g()));
                cs.setInt(15, Integer.parseInt(evaluationdto.getE6h()));
                cs.setInt(16, Integer.parseInt(evaluationdto.getSca()));
                cs.setInt(17, Integer.parseInt(evaluationdto.getScb()));
                cs.setInt(18, Integer.parseInt(evaluationdto.getScc()));
                cs.setInt(19, Integer.parseInt(evaluationdto.getScd()));
                cs.setString(20, evaluationdto.getMotorsystemLeftOrRight());
                cs.setInt(21, Integer.parseInt(evaluationdto.getMotorsystem()));




                if (evaluationdto.getSensorysystemh() != null) {
                    cs.setInt(22, Integer.parseInt(evaluationdto.getSensorysystemh()));
                } else {
                    cs.setInt(22, 0);
                }

                if (evaluationdto.getSensorysystemf() != null) {
                    cs.setInt(23, Integer.parseInt(evaluationdto.getSensorysystemf()));
                } else {
                    cs.setInt(23, 0);
                }
                if (evaluationdto.getSensorysystemt() != null) {
                    cs.setInt(24, Integer.parseInt(evaluationdto.getSensorysystemt()));
                } else {
                    cs.setInt(24, 0);
                }
                cs.setInt(25, Integer.parseInt(evaluationdto.getSensorysystem()));
                cs.setInt(26, Integer.parseInt(evaluationdto.getSensorysystem1()));
                cs.setInt(27, Integer.parseInt(evaluationdto.getSensorysystem2()));
                cs.setInt(28, Integer.parseInt(evaluationdto.getBladderinvolvement()));
                cs.setInt(29, Integer.parseInt(evaluationdto.getInjury()));
                cs.setInt(30, Integer.parseInt(evaluationdto.getAtaxia()));
                cs.setDouble(31, evaluationdto.getTotalpercent());
                cs.setString(32, evaluationdto.getLoginid());
                cs.setString(33, evaluationdto.getSystemip());

                cs.setInt(34, Integer.parseInt(details[0].toString()));
                cs.setInt(35, Integer.parseInt(details[1].toString()));

                cs.executeUpdate();
            }

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            transactionService.updateTransactionDetails(datasource, "Error in updating EVALUATION OF PHYSICAL IMPAIRMENTS Details", code);
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateDetails", "EvaluationDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EvaluationDAO", "updateDetails");
        } catch (Exception sqlEx) {
            transactionService.updateTransactionDetails(datasource, "Error in updating EVALUATION OF PHYSICAL IMPAIRMENTS Details", code);
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateDetails", "EvaluationDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EvaluationDAO", "updateDetails");
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

        ResultSet rs = null;
      //  Statement stmt = null;
        
        PreparedStatement pstmt =null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            String query = "select Person_Code from dbo.tblPhysical_Impairments_in_Neurological_Details where Person_Code=? and status='Active'";
            
            pstmt =con.prepareStatement(query);
            pstmt.setString(1, personcode);          
            rs = pstmt.executeQuery(); 
            
            if (rs.next() == false) {
                personcodecheckflag = false;
            } else {
                personcodecheckflag = true;
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkPersoncode", "EvaluationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EvaluationDAO", "checkPersoncode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkPersoncode", "EvaluationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EvaluationDAO", "checkPersoncode");
        } finally {
           DBConnection.closeResultSet(rs);
          // DBConnection.closeStatement(stmt);
           DBConnection.closeConnection(con); 
           DBConnection.closeStatement(pstmt);

        }
        return personcodecheckflag;
    }
}








            
   
