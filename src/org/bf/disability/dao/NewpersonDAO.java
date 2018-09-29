/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.NewPersonDetailsForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 695536
 */
public class NewpersonDAO {

    public String getPhysicalImpairmentDetails(DataSource ds, NewPersonDetailsForm newPersonDetailsForm) throws SADAREMDBException, SQLException {
        String physicalData = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        try {
            con = DBConnection.getConnection();
            query = "select b.disability_id,a.person_code from tbl_Person_Disability_Cando_Details a,"
                    + " tblPerson_Disability_Details b,tblPerson_Disability_TotalValue_Details c,"
                    + " tblperson_personal_details d  with (nolock) where a.person_code = b.person_code and "
                    + " a.person_code = c.person_code and a.person_code = d.person_code and a.s_can is null"
                    + " and a.s_can is null and a.f_can is null and a.pp_can is null and a.l_can is null "
                    + " and a.kc_can is null and a.b_can is null and a.st_can is null and a.w_can is null "
                    + " and a.rw_can is null and  a.h_can is null and a.se_can is null  and b.status='Active' "
                    + " and c.status='Active' and d.status='Active' "
                    + " and d.view_edit_mode='View' and a.person_code = ?";


            pstmt = con.prepareStatement(query);
            pstmt.setString(1, newPersonDetailsForm.getPerson());
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    physicalData = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPhysicalImpairmentDetails", "NewpersonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewpersonDAO", "getPhysicalImpairmentDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPhysicalImpairmentDetails", "NewpersonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewpersonDAO", "getPhysicalImpairmentDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return physicalData;
    }

    public int updatedetails(DataSource ds, NewPersonDetailsForm newPersonDetailsForm, int requestId) throws SADAREMDBException, SQLException {
        int daodata = 0;
        int statusUpdate = 0;
        int dateUpdate = 0;
        int count=0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        try {
            con = DBConnection.getConnection();
            query = "update  tbl_Person_Disability_Cando_Details set status='Active'";
            if (newPersonDetailsForm.getF_can() != null) {
                query += ",f_can='" + newPersonDetailsForm.getF_can() + "'";
                
            }
            if (newPersonDetailsForm.getPp_can() != null) {
                query += ",pp_can='" + newPersonDetailsForm.getPp_can() + "'";
                
            }
            if (newPersonDetailsForm.getL_can() != null) {
                query += ",L_Can='" + newPersonDetailsForm.getL_can() + "'";
                
            }
            if (newPersonDetailsForm.getKc_can() != null) {
                query += ",KC_Can='" + newPersonDetailsForm.getKc_can() + "'";
                
            }
            if (newPersonDetailsForm.getB_can() != null) {
                query += ",B_Can='" + newPersonDetailsForm.getB_can() + "'";
                
            }
            if (newPersonDetailsForm.getS_can() != null) {
                query += ",S_Can='" + newPersonDetailsForm.getS_can() + "'";
                
            }
            if (newPersonDetailsForm.getSt_can() != null) {
                query += ",ST_Can='" + newPersonDetailsForm.getSt_can() + "'";
                
            }
            if (newPersonDetailsForm.getW_can() != null) {
                query += ",W_Can='" + newPersonDetailsForm.getW_can() + "'";
                
            }
            if (newPersonDetailsForm.getRw_can() != null) {
                query += ",RW_Can='" + newPersonDetailsForm.getRw_can() + "'";
                
            }
            if (newPersonDetailsForm.getSe_can() != null) {
                query += ",SE_Can='" + newPersonDetailsForm.getSe_can() + "'";
                
            }
            if (newPersonDetailsForm.getH_can() != null) {
                query += ",H_Can='" + newPersonDetailsForm.getH_can() + "'";
                
            }
            
            query += ",Updated_Date=GETDATE() where Person_Code=? and status='Active'";
            pstmt = con.prepareStatement(query);
            count = 1;
            if (newPersonDetailsForm.getF_can() != null)
            {
                pstmt.setString(count++,newPersonDetailsForm.getF_can());                
            }
            if (newPersonDetailsForm.getPp_can() != null)
            {
                pstmt.setString(count++,newPersonDetailsForm.getPp_can());
            }
            if (newPersonDetailsForm.getL_can() != null)
            {
                pstmt.setString(count++,newPersonDetailsForm.getL_can());
            }
            if (newPersonDetailsForm.getKc_can() != null)
            {
                pstmt.setString(count++,newPersonDetailsForm.getKc_can());
            }
            if (newPersonDetailsForm.getB_can() != null)
            {
                pstmt.setString(count++,newPersonDetailsForm.getB_can());
            }
            if (newPersonDetailsForm.getS_can() != null)
            {
                pstmt.setString(count++,newPersonDetailsForm.getS_can());
            }
            if (newPersonDetailsForm.getSt_can() != null)
            {
                pstmt.setString(count++, newPersonDetailsForm.getSt_can());
            }
            if (newPersonDetailsForm.getW_can() != null)
            {
                pstmt.setString(count++,newPersonDetailsForm.getW_can());
            }
            if (newPersonDetailsForm.getRw_can() != null)
            {
                pstmt.setString(count++,newPersonDetailsForm.getRw_can());
            }
            if (newPersonDetailsForm.getSe_can() != null)
            {
            	pstmt.setString(count++,newPersonDetailsForm.getSe_can());
            }
            if (newPersonDetailsForm.getH_can() != null)
            {                
                pstmt.setString(count++,newPersonDetailsForm.getH_can());
            }
            pstmt.setString(count, newPersonDetailsForm.getPerson());
            daodata = pstmt.executeUpdate();
            statusUpdate = updateRequestStatus(ds, requestId);
            dateUpdate = updateRequestUpdatedDate(ds, requestId);
            if (daodata == 1 && statusUpdate == 1 && dateUpdate == 1) {
                daodata = 1;
            } else {
                daodata = 0;
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatedetails", "NewpersonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewpersonDAO", "updatedetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatedetails", "NewpersonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewpersonDAO", "updatedetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);

        }
        return daodata;

    }

    public int updateRequestStatus(DataSource ds, int requestid) throws SADAREMDBException, SQLException {
        int daodata = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        try {
            con = DBConnection.getConnection();

            query = "update  RequestTypeMapping set requeststatus='Approval', updatedpersonstatus='PersonUpdate'"
                    + "where requestid=?and requeststatus='Pending'";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, requestid);
            daodata = pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateRequestStatus", "NewpersonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewpersonDAO", "updateRequestStatus");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateRequestStatus", "NewpersonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewpersonDAO", "updateRequestStatus");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);

        }
        return daodata;

    }

    public int updateRequestUpdatedDate(DataSource ds, int requestid) throws SADAREMDBException, SQLException {
        int daodata = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        try {
            con = DBConnection.getConnection();
            query = "update  RequestDetails set updated_date=GETDATE()"
                    + "where requestid=? and status='Active'";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, requestid);
            daodata = pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateRequestUpdatedDate", "NewpersonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewpersonDAO", "updateRequestUpdatedDate");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateRequestUpdatedDate", "NewpersonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewpersonDAO", "updateRequestUpdatedDate");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);

        }
        return daodata;

    }

    public int getIdDetails(DataSource ds, String personCode) throws SADAREMDBException, SQLException {
        int daodata = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            query = "select count(*) from RequestDetails r "
                    + "join RequestTypeMapping c on (c.requestid =r.requestid) "
                    + "where r.person_code=? and c.requestTypeId='10' "
                    + "and r.status='Active' and requeststatus='Pending'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personCode);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    daodata = rs.getInt(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getIdDetails", "NewpersonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewpersonDAO", "getIdDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getIdDetails", "NewpersonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewpersonDAO", "getIdDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return daodata;

    }

    public int getRequestId(DataSource ds, String personCode) throws SADAREMDBException, SQLException {
        int requestId = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            query = "select r.requestid from RequestDetails r "
                    + "join RequestTypeMapping c on (c.requestid =r.requestid) "
                    + "where r.person_code=? and c.requestTypeId='10' "
                    + "and r.status='Active' and requeststatus='Pending'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personCode);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    requestId = rs.getInt(1);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestId", "NewpersonDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewpersonDAO", "getRequestId");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestId", "NewpersonDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewpersonDAO", "getRequestId");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return requestId;

    }
}
