/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package org.bf.disability.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.upload.FormFile;
import org.bf.disability.dto.UploadGosandActsDTO;
import org.bf.disability.form.UploadGosandActsForm;
import org.bf.disability.util.FileUtils;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 695048
 */
public class UploadGosandActsDAO {

    /**
     * This method is for uploading upload GO's and Acts
     * @param dto
     * @param gosandActsForm
     * @param request
     * @param url
     * @return
     * @throws Exception
     */
    public int insertDetails(DataSource ds, UploadGosandActsDTO dto, UploadGosandActsForm gosandActsForm, HttpServletRequest request, String url) throws Exception {
        Connection con = null;
        //Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int gosActsDetails = 0;
        String query = null;
        HttpSession session = request.getSession();
        try {
            con = DBConnection.getConnection();
            
            Date date = new SimpleDateFormat("dd/mm/yyyy").parse(gosandActsForm.getDateofApplication());
            String applicationDate = new SimpleDateFormat("mm/dd/yyyy").format(date);


            query = "INSERT INTO [UploadGosAndActs]([section],[GoType],[Go_no],[GoCategory],"
                    + "[UploadGo],[subject],[Created_Date],[updated_Date],GoDate,system_ip_address,login_id) "
                    + " VALUES(?,?,?,?,"
                    + "?,?,getDate(),getDate(),?,?,?)";
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, gosandActsForm.getGoSection());
            pstmt.setString(2, gosandActsForm.getGoType());
            pstmt.setString(3, gosandActsForm.getGoNumber());
            pstmt.setString(4, gosandActsForm.getGoCategory());
            pstmt.setString(5, gosandActsForm.getGoNumber());
            pstmt.setString(6, gosandActsForm.getGodescription());
            pstmt.setString(7, applicationDate);
            pstmt.setString(8, request.getRemoteAddr());
            pstmt.setString(9, session.getAttribute("username").toString());
            gosActsDetails = pstmt.executeUpdate();
            if (gosActsDetails != 0) {
                uploadMOM(gosandActsForm.getUploadGosActs(), gosandActsForm, request, gosandActsForm.getGoNumber(), url);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return gosActsDetails;
    }

    /**
     * This method is for report of  GO's and Acts
    @param uploadGosandActsDTO
     * @param request
     * @param url
     * @return
     * @throws Exception
     */
    public ArrayList getGosUploadDetails(DataSource ds, UploadGosandActsDTO uploadGosandActsDTO) throws Exception {
        ArrayList gosAndActs = new ArrayList();
        String query = null;
        Connection con = null;
        //Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        HashMap uploadDetails = null;
        try {
            con = DBConnection.getConnection();
            
            if (uploadGosandActsDTO.getLoginId() != null && uploadGosandActsDTO.getLoginId().length() > 0) {
                query = " SELECT Department,section,a.GoType,a.Go_no ,a.GoCategory,a.UploadGo,a.subject,convert(varchar,a.Created_Date,103) as created_Date,"
                        + "convert(varchar,a.updated_Date,103) as updated_Date ,a.rowid, convert(varchar,Godate,103) as Godate,a.SubSection,a.Existing_new,a.subject_id FROM [UploadGosAndActs] a "
                        + " where a.status='Active' and login_id=?  and  a.subsection is null and a.subject_id is null order by a.Go_no";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, uploadGosandActsDTO.getLoginId());
                
            } else {
                query = " SELECT Department,section,a.GoType,a.Go_no ,a.GoCategory,a.UploadGo,a.subject,convert(varchar,a.Created_Date,103) as created_Date,"
                        + "convert(varchar,a.updated_Date,103) as updated_Date ,a.rowid, convert(varchar,Godate,103) as Godate,a.SubSection,a.Existing_new,a.subject_id FROM [UploadGosAndActs] a "
                        + " where a.status='Active'  and  a.subsection is null and a.subject_id is null order by a.Go_no";
                pstmt = con.prepareStatement(query);
            }
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    uploadDetails = new HashMap();
                    uploadDetails.put("department", rs.getString(1));
                    uploadDetails.put("section", rs.getString(2));
                    uploadDetails.put("GoType", rs.getString(3));
                    uploadDetails.put("Go_no", rs.getString(4));
                    uploadDetails.put("GoCategory", rs.getString(5));
                    uploadDetails.put("UploadGo", rs.getString(6));
                    uploadDetails.put("subject", rs.getString(7));
                    uploadDetails.put("CreatedDate", rs.getString(8));
                    uploadDetails.put("updatedDate", rs.getString(9));
                    uploadDetails.put("rowid", rs.getString(10));
                    uploadDetails.put("Godate", rs.getString(11));
                    gosAndActs.add(uploadDetails);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return gosAndActs;
    }

    public ArrayList getEditDetails(DataSource ds, String rowId) throws Exception {
        ArrayList getEditDetails = new ArrayList();
        String query = null;
        Connection con = null;
        //Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            
            query = " SELECT [section],[GoType],[Go_no] ,[GoCategory],"
                    + "[subject],convert(varchar,godate,103) as date FROM [UploadGosAndActs] "
                    + " where status='Active' and rowid=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,rowId);

            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    getEditDetails.add(rs.getString(1));
                    getEditDetails.add(rs.getString(2));
                    getEditDetails.add(rs.getString(3));
                    getEditDetails.add(rs.getString(4));
                    getEditDetails.add(rs.getString(5));
                    getEditDetails.add(rs.getString(6));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return getEditDetails;
    }

    /**
     * Method is for getting subject in select box.
     * @param uploadGosandActsDTO
     * @param request
     * @param url
     * @return
     * @throws Exception
     */
    public ArrayList getSubject(DataSource ds, UploadGosandActsDTO uploadGosandActsDTO) throws Exception {
        ArrayList getDetails = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UploadGosandActsDTO gosandActsDTO = null;
        String query = null;
        try {
            con = DBConnection.getConnection();
            
            query = " select subject_ShortName,subject from SubjectsMaster where section=? and sub_section=? ";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, uploadGosandActsDTO.getGoSection());
            pstmt.setString(2, uploadGosandActsDTO.getSubSection());
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    gosandActsDTO = new UploadGosandActsDTO();
                    gosandActsDTO.setSubject_id(rs.getString(1));
                    gosandActsDTO.setSubjectName(rs.getString(2));
                    getDetails.add(gosandActsDTO);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return getDetails;
    }

    public ArrayList getGosDetailsHomePage(DataSource ds, UploadGosandActsForm gosandActsForm) throws Exception {
        ArrayList gosAndActs = new ArrayList();
        String query = null;;
        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        HashMap uploadDetails = null;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            query=" SELECT department,section,a.GoType,a.Go_no ,a.GoCategory,a.UploadGo,a.subject,"
                    + "convert(varchar,a.Created_Date,103) as created_Date,convert(varchar,a.updated_Date,103) as updated_Date ,a.rowid, "
                    + "convert(varchar,Godate,103) as Godate,a.SubSection,a.Existing_new,a.subject_id FROM [UploadGosAndActs] a "
                    + " where a.status='Active' order by [Go_no]";
            rs = st.executeQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    uploadDetails = new HashMap();
                    uploadDetails.put("department", rs.getString(1));
                    uploadDetails.put("section", rs.getString(2));
                    uploadDetails.put("GoType", rs.getString(3));
                    uploadDetails.put("Go_no", rs.getString(4));
                    uploadDetails.put("GoCategory", rs.getString(5));
                    uploadDetails.put("UploadGo", rs.getString(6));
                    uploadDetails.put("subject", rs.getString(7));
                    uploadDetails.put("CreatedDate", rs.getString(8));
                    uploadDetails.put("updatedDate", rs.getString(9));
                    uploadDetails.put("rowid", rs.getString(10));
                    uploadDetails.put("Godate", rs.getString(11));
                    gosAndActs.add(uploadDetails);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return gosAndActs;
    }

    public int inupdateUploadGo(DataSource ds, UploadGosandActsDTO uploadGosandActsDTO, UploadGosandActsForm gosandActsForm, HttpServletRequest request, String url, String rowId) throws Exception, SQLException {
        int details = 0;
        String query = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        FileUtils utils = new FileUtils();
        try {
            con = DBConnection.getConnection();
            
            Date date = new SimpleDateFormat("dd/mm/yyyy").parse(uploadGosandActsDTO.getDateofApplication());
            String applicationDate = new SimpleDateFormat("mm/dd/yyyy").format(date);
            if (!gosandActsForm.getUploadGosActs().equals("")) {
                query = "update UploadGosAndActs set Department=?,section=?,"
                        + "GoType=?,Go_no=?,GoCategory=?,"
                        + "subject=?,godate=?,"
                        + " uploadgo=?,updated_date=getDate() where rowid=? ";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, gosandActsForm.getGoDepartment());
                pstmt.setString(2, uploadGosandActsDTO.getGoSection());
                pstmt.setString(3, uploadGosandActsDTO.getGoType() );
                pstmt.setString(4, uploadGosandActsDTO.getGoNumber());
                pstmt.setString(5, uploadGosandActsDTO.getGoCategory());
                pstmt.setString(6, uploadGosandActsDTO.getGodescription());
                pstmt.setString(7, applicationDate);
                pstmt.setString(8, uploadGosandActsDTO.getGoNumber());
                pstmt.setString(9, rowId);
            } else {
                query = "update UploadGosAndActs set Department=?,section=?,"
                        + "GoType=?,Go_no=?,GoCategory=?,"
                        + "subject=?,godate=?,"
                        + " updated_date=getDate() where rowid=? ";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, gosandActsForm.getGoDepartment());
                pstmt.setString(2, uploadGosandActsDTO.getGoSection());
                pstmt.setString(3, uploadGosandActsDTO.getGoType() );
                pstmt.setString(4, uploadGosandActsDTO.getGoNumber());
                pstmt.setString(5, uploadGosandActsDTO.getGoCategory());
                pstmt.setString(6, uploadGosandActsDTO.getGodescription());
                pstmt.setString(7, applicationDate);
                pstmt.setString(8, rowId);
            }


            details = pstmt.executeUpdate();

            if (!gosandActsForm.getUploadGosActs().equals("") && details != 0) {
                uploadMOM(gosandActsForm.getUploadGosActs(), gosandActsForm, request, gosandActsForm.getGoNumber(), url);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }

        return details;
    }

    public int inActiveStatus(DataSource ds, String rowID) throws Exception, SQLException {
        int details = 0;
        String query = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            query = "update UploadGosAndActs set status='inActive',updated_date=getDate() where rowid=?";
            pstmt= con.prepareStatement(query);
            pstmt.setString(1, rowID);
            details = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return details;
    }

    public ArrayList getDepartmentList(DataSource ds, UploadGosandActsForm form) throws Exception, SQLException {
        ArrayList departmentList = new ArrayList();
        String query = null;
        HashMap map = null;
        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            query = "  select DepartmentId,DepartmentName from  dbo.DepartmentMaster where status='Active'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("goDepartment", rs.getString(1));
                    map.put("departmentName", rs.getString(2));
                    departmentList.add(map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return departmentList;
    }

    public boolean uploadMOM(FormFile uploadFile, UploadGosandActsForm bdrform, HttpServletRequest request, String accNo, String url) {
        String extension = null;
        String strDirectoy = null;
        String strDirectoytemp = null;
        boolean flag = false;
        try {
            FormFile myFile = bdrform.getUploadGosActs();
            String fileName = myFile.getFileName();
            int dotPos = fileName.lastIndexOf(".");
            extension = fileName.substring(dotPos);
            int urlLength = url.length();
            int portLocal = request.getLocalPort();

            /* Upload file for Agency MOM */

            fileName = accNo + extension;
            strDirectoytemp = FileUtils.filePath("departmentGos") + accNo;
            //    strDirectoytemp = "E:\\REEMAPDEMO\\BankCheques\\" + accNo;
            if (portLocal == 8084) {
                strDirectoy = url.substring(0, urlLength - 11) + "\\web\\SADAREM\\" + accNo + ".pdf";
            } else {
                strDirectoy = url + "\\Gos\\" + accNo + ".pdf";
            }

            /* End of Agency MOM Upload */
            File directory = new File(strDirectoy);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File fileToCreate = new File(strDirectoy, fileName);
            //If file does not exists create file
            FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
            fileOutStream.write(myFile.getFileData());
            fileOutStream.flush();
            fileOutStream.close();
            if (strDirectoytemp != null && !"".equals(strDirectoytemp)) {
                File directorytemp = new File(strDirectoytemp);
                if (!directorytemp.exists()) {
                    directorytemp.mkdirs();
                }
                File fileToCreatetemp = new File(strDirectoytemp, fileName);
                //If file does not exists create file
                FileOutputStream fileOutStreamtemp = new FileOutputStream(fileToCreatetemp);
                fileOutStreamtemp.write(myFile.getFileData());
                fileOutStreamtemp.flush();
                fileOutStreamtemp.close();
            }
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


     public ArrayList getGosDetailsBySection(DataSource ds, UploadGosandActsForm gosandActsForm,String section) throws Exception {
        ArrayList gosAndActs = new ArrayList();
        String query = null;;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        HashMap uploadDetails = null;
        try {
            con = DBConnection.getConnection();
            query=" SELECT department,section,a.GoType,a.Go_no ,a.GoCategory,a.UploadGo,a.subject,"
                    + "convert(varchar,a.Created_Date,103) as created_Date,convert(varchar,a.updated_Date,103) as updated_Date ,a.rowid, "
                    + "convert(varchar,Godate,103) as Godate,a.SubSection,a.Existing_new,a.subject_id FROM [UploadGosAndActs] a "
                    + " where a.status='Active' and section=? order by [Go_no]";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, section);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    uploadDetails = new HashMap();
                    uploadDetails.put("department", rs.getString(1));
                    uploadDetails.put("section", rs.getString(2));
                    uploadDetails.put("GoType", rs.getString(3));
                    uploadDetails.put("Go_no", rs.getString(4));
                    uploadDetails.put("GoCategory", rs.getString(5));
                    uploadDetails.put("UploadGo", rs.getString(6));
                    uploadDetails.put("subject", rs.getString(7));
                    uploadDetails.put("CreatedDate", rs.getString(8));
                    uploadDetails.put("updatedDate", rs.getString(9));
                    uploadDetails.put("rowid", rs.getString(10));
                    uploadDetails.put("Godate", rs.getString(11));
                    gosAndActs.add(uploadDetails);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return gosAndActs;
    }

}
