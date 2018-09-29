/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.struts.upload.FormFile;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.CMPUploadDTO;

import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sgv.common.util.DBConnection;
/**
 *
 * @author 484898
 */
public class CMPUploadDAO {

    /**
     * Method for saving the Memo/Circular/Proceeding details
     * @param cMPUploadDTO
     * @param request
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public int saveDetails(CMPUploadDTO cMPUploadDTO, HttpServletRequest request,DataSource ds) throws SADAREMDBException, SQLException {
        int insertStatus = 0;
        boolean flag = true;
        String fileName = null;
        String extension = null;

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnection.getConnection();

            if (cMPUploadDTO.getUploadFiles() != null && cMPUploadDTO.getUploadFiles().toString().length() > 0) {
                fileName = cMPUploadDTO.getUploadFiles().getFileName();
                int dotPos = fileName.lastIndexOf(".");
                extension = fileName.substring(dotPos);
                fileName = cMPUploadDTO.getMemoNumber().replaceAll("/", "_") + extension;
                flag = uploadGos(cMPUploadDTO.getUploadFiles(), cMPUploadDTO.getMemoNumber().replaceAll("/", "_"), request, cMPUploadDTO.getUrl());
            } else {
                fileName = "-";
            }
            if (flag) {
                String query = "INSERT INTO CMPUploads(CMPNumber,FileNumber,CircularCount,DocumentType,IssuingYear,IssuingDate,Subject,Reference,UploadFile,"
                        + "CreatedDate,UpdatedDate) VALUES (?,?,'0',?,CONVERT(DATE,?,103),?,?,?,?,getDate(),getDate())";
              
                ps = con.prepareStatement(query);
                ps.setString(1, cMPUploadDTO.getMemoNumber());
                ps.setString(2, cMPUploadDTO.getFileNumber());
                ps.setString(3, cMPUploadDTO.getDocumentType());
                ps.setString(4, CommonUtility.getDateTime("yyyy"));
                ps.setString(5, cMPUploadDTO.getIssueingDate().toString());
                ps.setString(6, cMPUploadDTO.getSubject());
                ps.setString(7, cMPUploadDTO.getReference());
                ps.setString(8, fileName);
                
                insertStatus = ps.executeUpdate();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        DBConnection.closeConnection(con);
        DBConnection.closeStatement(ps);
        }
        return insertStatus;
    }

    /**
     * This method is for updating the record in a table when uploaded file is empty
     * @param uploadGosDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public int updateRecordWhenUploadFails(CMPUploadDTO cMPUploadDTO,DataSource ds) throws SADAREMDBException, SQLException {
        int updateStatus = 0;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnection.getConnection();
            String query = "update CMPUploads set status='Inactive' where CMPNumber=?";
         
            ps = con.prepareStatement(query);
            ps.setString(1, cMPUploadDTO.getMemoNumber());
            updateStatus = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        DBConnection.closeConnection(con);
        DBConnection.closeStatement(ps);
        }
        return updateStatus;
    }

    /**
     * Method for Update Keycontacts
     * @param cMPUploadDTO
     * @param request
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public int updateDetails(CMPUploadDTO cMPUploadDTO, HttpServletRequest request,DataSource ds) throws SADAREMDBException, SQLException {
        int insertStatus = 0;
        boolean flag = true;
        String extension = null;
        String fileName = null;
        StringBuffer sbf = new StringBuffer();
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            if (cMPUploadDTO.getUploadFiles() != null && cMPUploadDTO.getUploadFiles().toString().length() > 0) {
                fileName = cMPUploadDTO.getUploadFiles().getFileName();
                int dotPos = fileName.lastIndexOf(".");
                extension = fileName.substring(dotPos);
                fileName = cMPUploadDTO.getMemoNumber().replaceAll("/", "_") + extension;
            }

            sbf.append("update CMPUploads set CMPNumber=?,"
                    + "DocumentType=?,"
                    + "IssuingDate=CONVERT(DATE,?,103),"
                    + "Subject=?,Reference=?,");

            if (cMPUploadDTO.getUploadFiles() != null && cMPUploadDTO.getUploadFiles().toString().length() > 0) {
                sbf.append("UploadFile=?,");
            }
            sbf.append("updatedDate=getDate() where rowid=?");
            
            ps = con.prepareStatement(sbf.toString());
            ps.setString(1, cMPUploadDTO.getMemoNumber());
            ps.setString(2, cMPUploadDTO.getDocumentType());
            ps.setString(3, cMPUploadDTO.getIssueingDate().toString());
            ps.setString(4, cMPUploadDTO.getSubject());
            ps.setString(5, cMPUploadDTO.getReference());
            if (cMPUploadDTO.getUploadFiles() != null && cMPUploadDTO.getUploadFiles().toString().length() > 0)
            {
                ps.setString(6, fileName);
                ps.setString(7, cMPUploadDTO.getRowId());
            }
            else
            {
            	ps.setString(6, cMPUploadDTO.getRowId());
            }
            
            insertStatus = ps.executeUpdate();


            if (insertStatus != 0) {
                if (cMPUploadDTO.getUploadFiles() != null && cMPUploadDTO.getUploadFiles().toString().length() > 0) {
                    flag = uploadGos(cMPUploadDTO.getUploadFiles(), cMPUploadDTO.getMemoNumber().replaceAll("/", "_"), request, cMPUploadDTO.getUrl());
                    if (!flag) { // If file uploads fails
                        updateRecordWhenUploadFails(cMPUploadDTO,ds); // Record update when upload file is failed
                        insertStatus = 0;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DBConnection.closeConnection(con);
        DBConnection.closeStatement(ps);
        }
        return insertStatus;
    }

    /**
     * Method for Inactive the CMP details
     * @param cMPUploadDTO
     * @param request
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public int inactiveRecord(CMPUploadDTO cMPUploadDTO,DataSource ds) throws SADAREMDBException, SQLException {
        int insertStatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement("Update CMPUploads set status='InActive' where rowId=?");
            ps.setString(1, cMPUploadDTO.getRowId());
            insertStatus = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DBConnection.closeConnection(con);
        DBConnection.closeStatement(ps);
        }
        return insertStatus;
    }

    /**
     * Method for Getting the CMP Details
     * @param cMPUploadDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public ArrayList getCMPUploadsDetails(CMPUploadDTO cMPUploadDTO,DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList CMPDetails = new ArrayList();
        HashMap<String, Object> contacts = null;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        StringBuffer sbf = new StringBuffer();
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            sbf.append("SELECT RowId,CMPNumber,DocumentType,convert(varchar,IssuingDate,103),Subject,Reference,UploadFile,CircularCount,IssuingYear,FileNumber "
                    + "FROM CMPUploads where status='Active' ");

            if (cMPUploadDTO.getRoleId() != null && cMPUploadDTO.getRoleId().toString().length() > 0) {
                sbf.append("and RoleId='" + cMPUploadDTO.getRoleId() + "'");
            }

            sbf.append(" order by rowid");
            if (cMPUploadDTO.getRoleId() != null && cMPUploadDTO.getRoleId().toString().length() > 0)
            {
                sbf.append("and RoleId=?");
                ps.setString(1, cMPUploadDTO.getRoleId());
            }
            rs = st.executeQuery(sbf.toString());
            if (rs != null) {
                while (rs.next()) {
                    contacts = new HashMap<String, Object>();
                    contacts.put("RowId", rs.getString(1));
                    contacts.put("CMPNumber", rs.getString(2));
                    contacts.put("DocumentType", CommonUtility.emptyCheck(rs.getString(3)));
                    contacts.put("IssuingDate", CommonUtility.emptyCheck(rs.getString(4)));
                    contacts.put("Subject", rs.getString(5));
                    contacts.put("Reference", rs.getString(6));
                    contacts.put("UploadFile", CommonUtility.emptyCheck(rs.getString(7)));
                    contacts.put("CircularCount", rs.getString(8));
                    contacts.put("IssuingYear", rs.getString(9));
                    contacts.put("FileNumber", rs.getString(10));
                    CMPDetails.add(contacts);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(ps);
			DBConnection.closeStatement(st);
			DBConnection.closeConnection(con); 
        }

        return CMPDetails;
    }

    /**
     * This method is for getting the keycontacts for Editing
     * @param cCLAKeyContactsDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public ArrayList getValuesWhenClickOnEdit(CMPUploadDTO cMPUploadDTO,DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList CMPDetails = new ArrayList();
        HashMap<String, Object> contacts = null;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            st = con.createStatement();

            rs = st.executeQuery("SELECT RowId,CMPNumber,DocumentType,convert(varchar,IssuingDate,103),Subject,Reference,UploadFile,RoleId,CircularCount,IssuingYear,FileNumber "
                    + "FROM CMPUploads where rowid='" + cMPUploadDTO.getRowId() + "' and status='Active' order by rowid");
            if (rs != null) {
                while (rs.next()) {
                    contacts = new HashMap<String, Object>();
                    contacts.put("RowId", rs.getString(1));
                    contacts.put("CMPNumber", rs.getString(2));
                    contacts.put("DocumentType", CommonUtility.emptyCheck(rs.getString(3)));
                    contacts.put("IssuingDate", CommonUtility.emptyCheck(rs.getString(4)));
                    contacts.put("Subject", rs.getString(5));
                    contacts.put("Reference", rs.getString(6));
                    contacts.put("UploadFile", CommonUtility.emptyCheck(rs.getString(7)));
                    contacts.put("RoleId", rs.getString(8));
                    contacts.put("CircularCount", rs.getString(9));
                    contacts.put("IssuingYear", rs.getString(10));
                    contacts.put("FileNumber", rs.getString(11));
                    CMPDetails.add(contacts);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(st);
			DBConnection.closeConnection(con); 
        }
        return CMPDetails;
    }

    /**
     * This method is for generating the Corcular/Memo/Proceeding numbers
     * @param cMPUploadDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public synchronized String generateCircularNumber(CMPUploadDTO cMPUploadDTO,DataSource ds) throws SADAREMDBException, SQLException {
        String circularNumber = null;
        String finalString = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            if (cMPUploadDTO.getDocumentType() != null && cMPUploadDTO.getDocumentType().length() > 0
                    && cMPUploadDTO.getRoleId() != null && cMPUploadDTO.getRoleId().length() > 0) {


                ps = con.prepareStatement("select coalesce(max(coalesce(circularCount,'0'))+1,'1') as CircularCount from dbo.CMPUploads "
                        + "where DocumentType='" + cMPUploadDTO.getDocumentType() + "' and roleid='" + cMPUploadDTO.getRoleId() + "' "
                        + "and issuingYear='" +CommonUtility.getDateTime("yyyy")+ "' and status='Active'");

                rs = ps.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        circularNumber = rs.getString(1);
                    }
                }


                finalString = "&nbsp;&nbsp;<input type=\"text\" name=\"memoNumberOne\" size=\"2\" disabled onkeydown=\"return space(event,this)\" style=\"text-align:center;\" value=" + circularNumber + "><input type=\"hidden\" name=\"cirOne\"  value=" + circularNumber + ">" + "&nbsp;&nbsp;/"
                        + "&nbsp;&nbsp;<input type=\"text\" name=\"fileNumber\" size=\"15\" onkeydown=\"return space(event,this)\">" + "&nbsp;&nbsp;/"
                        + "&nbsp;&nbsp;<input type=\"text\" name=\"memoNumberTwo\" size=\"2\" disabled onkeydown=\"return space(event,this)\" style=\"text-align:center;\" value=" + CommonUtility.getDateTime("yyyy")+ "><input type=\"hidden\" name=\"cirTwo\" value=" + CommonUtility.getDateTime("yyyy")+ ">";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        DBConnection.closeConnection(con);
        DBConnection.closeStatement(ps);
        DBConnection.closeResultSet(rs);
        }
        return finalString;
    }

    /**
     * This method is for upload the ccla gos
     * @param uploadFileName
     * @param goNumber
     * @param request
     * @param url
     * @return
     */
    public boolean uploadGos(FormFile uploadFileName, String goNumber, HttpServletRequest request, String url) {
        String extension = null;
        String strDirectoytemp = null;
        boolean flag = false;
        try {

            String fileName = uploadFileName.getFileName();
            int dotPos = fileName.lastIndexOf(".");
            extension = fileName.substring(dotPos);

            fileName = goNumber + extension;
            strDirectoytemp = CommonConstants.CIRCULARS_MEMOS_PROCEEDINGS;

            if (strDirectoytemp != null && !"".equals(strDirectoytemp) && strDirectoytemp.length() > 0) { // If directory is not exists it will create
                File directorytemp = new File(strDirectoytemp);
                if (!directorytemp.exists()) {
                    directorytemp.mkdirs();
                }

                File fileToCreatetemp = new File(strDirectoytemp, fileName); // Copy the file into directory
                FileOutputStream fileOutStreamtemp = new FileOutputStream(fileToCreatetemp); // Write the file content into buffer

                if (uploadFileName.getFileSize() > 0) {
                    fileOutStreamtemp.write(uploadFileName.getFileData());
                    fileOutStreamtemp.flush();
                    fileOutStreamtemp.close();
                    flag = true;
                } else {
                    flag = false;
                }
            } else {
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
