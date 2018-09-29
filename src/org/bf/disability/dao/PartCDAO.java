/*
 * PartCDAO.java
 *
 * Created on June 16, 2008, 4:42 PM
 */
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
import org.bf.disability.dto.PartCUpdateDTO;
import org.bf.disability.service.TransactionService;
import org.bf.disability.servicefactory.TransactionFactory;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is for insert,select and update on partcdata to database
 * @author Ragavendra
 * @version 1.0
 */
public class PartCDAO {

    /**
     * 
     * @param ds
     * @param partcdto 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public int insertPartCService(DataSource ds, PartCUpdateDTO partcdto, String personcode, HttpServletRequest request) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        Connection con = null;
        CallableStatement cs = null;
        int i2 = 0;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();

        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();

        try {


            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, personcode);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            //  int a = 10/0;

            cs = con.prepareCall("{Call SP_tblAllDisabilityPerson_Common_Needs_Insert(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, personcode);
            cs.setString(2, partcdto.getEarlyeducationservices());
            cs.setString(3, partcdto.getEducationservies());
            cs.setString(4, partcdto.getVocationaltraining());
            cs.setString(5, partcdto.getIndividual());
            cs.setString(6, partcdto.getFamily());
            cs.setString(7, partcdto.getAnyotherservices());
            //  cs.setBoolean(8, partcdto.isRailwaycertificate());
            cs.setString(8, partcdto.getLoginid());
            cs.setString(9, partcdto.getSystemip());

            cs.setInt(10, Integer.parseInt(details[0].toString()));
            cs.setInt(11, Integer.parseInt(details[1].toString()));

            i2 = cs.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            if (i2 != 0) // Added by mohan on 07/02/2012
            {
                transactionService.updateTransactionStatus(ds, "Completed", personcode);
            }
            // End

        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in PartC Details", personcode);
            // Endint exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertPartCService", "PartCDAO", "DataBase");
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "insertPartCService", "PartCDAO", "DataBase");

           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartCDAO", "insertPartCService");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cs);

        }
        return i2;
    }

    /**
     *
     * @param ds
     * @param partcdto
     * @param personcode
     * @throws org.bf.disability.Exception.SADAREMException
     * @return
     */
    public int insertPartCServiceAU(DataSource ds, PartCUpdateDTO partcdto, String personcode, HttpServletRequest request) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        Connection con = null;
        CallableStatement cs = null;
        int i2 = 0;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        PreparedStatement pstmt = null;
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();

        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, personcode);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);



            pstmt = con.prepareStatement("select * from tblAllDisabilityPerson_Common_Needs where Person_Code=? and status='Active'");
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            
            if (!rs.next()) {

                cs = con.prepareCall("{Call SP_tblAllDisabilityPerson_Common_Needs_Insert(?,?,?,?,?,?,?,?,?,?,?)}");
                cs.setString(1, personcode);
                cs.setString(2, partcdto.getEarlyeducationservices());
                cs.setString(3, partcdto.getEducationservies());
                cs.setString(4, partcdto.getVocationaltraining());
                cs.setString(5, partcdto.getIndividual());
                cs.setString(6, partcdto.getFamily());
                cs.setString(7, partcdto.getAnyotherservices());
                //  cs.setBoolean(8, partcdto.isRailwaycertificate());
                cs.setString(8, partcdto.getLoginid());
                cs.setString(9, partcdto.getSystemip());

                cs.setInt(10, Integer.parseInt(details[0].toString()));
                cs.setInt(11, Integer.parseInt(details[1].toString()));

                i2 = cs.executeUpdate();
                if (i2 != 0) // Added by mohan on 07/02/2012
                {

                    transactionService.updateTransactionStatus(ds, "Completed", personcode);

                }
                // End
                con.commit();
                con.setAutoCommit(true);
            } else {
                i2 = -1;
            }

        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in PartC Details", personcode);
            // End
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "insertPartCServiceAU", "PartCDAO", "DataBase");

           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartCDAO", "insertPartCServiceAU");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cs);

            DBConnection.closeStatement(pstmt);
        }
        return i2;
    }

    /**
     * 
     * @param ds
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    //added by rekha
    public String eligibiltyForRailwayCertificate(DataSource ds, String personcode) throws SADAREMDBException, SQLException {

        boolean exi = false;
        ResultSet rs = null;
        Connection con = null;
        String result = null;
        PreparedStatement ps = null;
        //ds ds = null;
        float hi = 0;
        float vi = 0;
        float mr = 0;
        float tl = 0;
        try {
            con = DBConnection.getConnection();

            ps = con.prepareStatement("select Person_Code,HearingImpairment,VisualImpairment, MentalRetardation, TotalLocomotor from tblPerson_Disability_TotalValue_Details where person_code=? and Status='Active';");
            ps.setString(1, personcode);
            rs = ps.executeQuery();
            if (rs.next()) {
                hi = rs.getFloat(2);
                vi = rs.getFloat(3);
                mr = rs.getFloat(4);
                tl = rs.getFloat(5);
                if (vi >= 100) {
                    exi = true;
                    result = "vi";
                } else if (hi >= 100) {
                    result = "hi";
                    exi = true;
                } else if (mr >= 50) {
                    result = "mr";
                    exi = true;
                } else if (tl >= 40) {
                    //if(tl>=vi&& tl>=mr && tl>=hi){
                    exi = true;
                    result = "tl";
                    // }
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "eligibiltyForRailwayCertificate", "PartCDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartCDAO", "eligibiltyForRailwayCertificate");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "eligibiltyForRailwayCertificate", "PartCDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartCDAO", "eligibiltyForRailwayCertificate");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            //DBConnection.closeStatement(cs);

            DBConnection.closeStatement(ps);
        }
        return result;
    }

    public String eligibiltyForRailwayCertificate(DataSource ds, String personcode, int maxid) throws SADAREMDBException, SQLException {

        boolean exi = false;
        ResultSet rs = null;
        Connection con = null;
        String result = null;
        PreparedStatement ps = null;
        //ds ds = null;
        float hi = 0;
        float vi = 0;
        float mr = 0;
        float tl = 0;
        try {
            con = DBConnection.getConnection();

            ps = con.prepareStatement("select Person_Code,HearingImpairment,VisualImpairment, MentalRetardation, TotalLocomotor from tblPerson_Disability_TotalValue_Details where person_code=? and Status='Active';");
            ps.setString(1, personcode);
            rs = ps.executeQuery();
            if (rs.next()) {
                hi = rs.getFloat(2);
                vi = rs.getFloat(3);
                mr = rs.getFloat(4);
                tl = rs.getFloat(5);

                if (maxid == 1) {
                    if (mr >= 50) {
                        result = "mr";
                        exi = true;
                    } else if (tl >= 40) {
                        exi = true;
                        result = "tl";
                    } else if (vi >= 100) {
                        exi = true;
                        result = "vi";
                    } else if (hi >= 100) {
                        result = "hi";
                        exi = true;
                    }

                } else if (maxid == 2) {
                    if (vi >= 100) {
                        exi = true;
                        result = "vi";
                    } else if (hi >= 100) {
                        result = "hi";
                        exi = true;
                    } else if (mr >= 50) {
                        result = "mr";
                        exi = true;

                    } else if (tl >= 40) {
                        exi = true;
                        result = "tl";
                    }

                } else if (maxid == 3) {
                    if (hi >= 100) {
                        result = "hi";
                        exi = true;
                    }
                    if (vi >= 100) {
                        exi = true;
                        result = "vi";
                    } else if (mr >= 50) {
                        result = "mr";
                        exi = true;

                    } else if (tl >= 40) {
                        exi = true;
                        result = "tl";
                    }

                } else if (maxid == 4) {
                    if (mr >= 50) {
                        result = "mr";
                        exi = true;

                    } else if (vi >= 100) {
                        exi = true;
                        result = "vi";
                    } else if (hi >= 100) {
                        result = "hi";
                        exi = true;
                    } else if (tl >= 40) {
                        exi = true;
                        result = "tl";
                    }

                } else if (maxid == 5) {
                    if (vi >= 100) {
                        exi = true;
                        result = "vi";
                    } else if (hi >= 100) {
                        result = "hi";
                        exi = true;
                    } else if (mr >= 50) {
                        result = "mr";
                        exi = true;

                    } else if (tl >= 40) {
                        exi = true;
                        result = "tl";
                    }
                }

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "eligibiltyForRailwayCertificate", "PartCDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartCDAO", "eligibiltyForRailwayCertificate");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "eligibiltyForRailwayCertificate", "PartCDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartCDAO", "eligibiltyForRailwayCertificate");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            //DBConnection.closeStatement(cs);

            DBConnection.closeStatement(ps);
        }
        return result;
    }

    public PartCUpdateDTO getLocomotorneeds(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        PartCUpdateDTO partcdto = new PartCUpdateDTO();
        String sql = null;
        PreparedStatement pstmt = null;
        try {
            con = DBConnection.getConnection();

            cstmt = con.prepareCall("{Call SP_tblAllDisabilityPerson_Common_Needs_Select(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                partcdto.setEarlyeducationservices(rs.getString("EarlyEducation"));
                partcdto.setEducationservies(rs.getString("EducationServices"));
                partcdto.setVocationaltraining(rs.getString("VocationalTraining"));
                partcdto.setIndividual(rs.getString("Individual"));
                partcdto.setFamily(rs.getString("Family"));
                partcdto.setAnyotherservices(rs.getString("OtherServices"));
                //  partcdto.setRailwaycertificate(rs.getBoolean("Railway_Certificate"));
            }
            // Added by Mohan on 09/01/2012
            sql = "select ReassessmentText from dbo.ReassessmentDetails where person_code=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    partcdto.setReassessment(rs.getString(1));
                }
            }
            //End
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getLocomotorneeds", "PartCDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartCDAO", "getLocomotorneeds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getLocomotorneeds", "PartCDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartCDAO", "getLocomotorneeds");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

            DBConnection.closeStatement(pstmt);
        }
        return partcdto;
    }

    /**
     * 
     * @param ds
     * @param partcdto 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public int updatePartc(DataSource ds, PartCUpdateDTO partcdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String query = null;
        PreparedStatement pstmt = null;
        String personcode = partcdto.getPersoncode();
        int i = 0;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        HttpSession session = request.getSession();


        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {

            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, partcdto.getPersoncode());
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            

            pstmt = con.prepareStatement("select Person_Code from tblAllDisabilityPerson_Common_Needs where Person_Code=? and status='Active'");
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                cs = con.prepareCall("{Call SP_tblAllDisabilityPerson_Common_Needs_Update(?,?,?,?,?,?,?,?,?,?,?)}");
                cs.setString(1, partcdto.getPersoncode());
                cs.setString(2, partcdto.getEarlyeducationservices());
                cs.setString(3, partcdto.getEducationservies());
                cs.setString(4, partcdto.getVocationaltraining());
                cs.setString(5, partcdto.getIndividual());
                cs.setString(6, partcdto.getFamily());
                cs.setString(7, partcdto.getAnyotherservices());
                // cs.setBoolean(8,partcdto.isRailwaycertificate());
                cs.setString(8, partcdto.getLoginid());
                cs.setString(9, partcdto.getSystemip());

                cs.setInt(10, Integer.parseInt(details[0].toString()));
                cs.setInt(11, Integer.parseInt(details[1].toString()));


                // Added by mohan on 09/01/2012

                
                if (session.getAttribute("roleId").toString().equals("5") && Integer.parseInt(details[0].toString()) == 2) {
                    query = "update ReassessmentDetails set ReassessmentText=? where person_code=?";
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, partcdto.getReassessment());
                    pstmt.setString(2, partcdto.getPersoncode());
                    int a = pstmt.executeUpdate();

                    if (a == 0) {
                        insertReassesment(ds, partcdto, personcode, request);
                    } else {
                        // Added by mohan on 07/02/2012
                        transactionService.updateTransactionStatus(ds, "Completed", personcode);
                        // End
                    }
                }
                //End
                cs.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            } else {
                insertPartCService(ds, partcdto, personcode, request);
                if (Integer.parseInt(details[0].toString()) == 2) {
                    insertReassesment(ds, partcdto, personcode, request);
                }
            }
        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in PartC Details", personcode);
            // End
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "updatePartc", "PartCDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartCDAO", "updatePartc");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(cs);

        }
        return i;
    }

    public int insertReassesment(DataSource ds, PartCUpdateDTO partcdto, String personcode, HttpServletRequest request) throws SADAREMDBException, SQLException {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        Statement st = null;
        int i2 = 0;
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        int count = 0;
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(ds, personcode);
            con = DBConnection.getConnection();


            String catid = partcdto.getCatId();
            if (catid != null && catid.equalsIgnoreCase("2")) {
                pstmt = con.prepareStatement("insert into ReassessmentDetails(Person_code,ReassessmentText,Login_ID,System_IP_Address,Created_Date,Updated_Date,CategoryID,CategoryCount) values(?,?,?,?,GETDATE(),GETDATE(),?,?)");
                pstmt.setString(1, personcode);
                pstmt.setString(2, partcdto.getReassessment());
                pstmt.setString(3, partcdto.getLoginid());
                pstmt.setString(4, partcdto.getSystemip());

                pstmt.setInt(5, Integer.parseInt(details[0].toString()));
                pstmt.setInt(6, Integer.parseInt(details[1].toString()));

                i2 = pstmt.executeUpdate();

                if (i2 != 0) // Added by mohan on 07/02/2012
                {
                    transactionService.updateTransactionStatus(ds, "Completed", personcode);
                }
            } else {
                transactionService.updateTransactionStatus(ds, "Completed", personcode);
            }
            // End


        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(ds, "Error in PartC Details", personcode);
            // End
            int exResult = ExceptionDAO.saveException(ds, exception.toString(), "insertReassesment", "PartCDAO", "DataBase");
           con.rollback();

            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartCDAO", "insertReassesment");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            //  DBConnection.closeStatement(cs);
            DBConnection.closeStatement(st);

            DBConnection.closeStatement(pstmt);
        }
        return i2;
    }
}
