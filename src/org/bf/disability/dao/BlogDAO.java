/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.struts.upload.FormFile;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.BlogDTO;

import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;
/**
 *
 * @author 484898
 */
public class BlogDAO {

    /**
     * This method is for getting the blog subjects details
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public ArrayList getBlogSubjects(DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList blogSubjects = new ArrayList();
        HashMap map = null;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = null;
        String count = null;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();

            sql = "select subjectid,subject from dbo.BlogSubjectMaster where status='Active'";
            rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("subjectid", rs.getString(1));
                    map.put("subject", rs.getString(2));

                    count = getCountOfBlog(con, rs.getString(1));
                    map.put("count", count);
                    blogSubjects.add(map);
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
        return blogSubjects;
    }

    /**
     * This method is for getting the blog subjects details
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public ArrayList getPostedBlogs(BlogDTO blogDTO,DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList postedBlogs = new ArrayList();
        HashMap map = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        String count = null;

        try {
            con = DBConnection.getConnection();
            
            sql.append("SELECT A.NAME,A.DESCRIPTION,A.MOBILE,A.EMAIL,A.UPLOADFILE,CONVERT(VARCHAR,A.POSTEDDATE,103) AS POSTEDDATE,"
                    + "A.POSTID,A.SUBJECT,B.SUBJECT FROM BLOGPOSTS A JOIN BLOGSUBJECTMASTER B ON(A.SUBJECT=B.SUBJECTID) "
                    + "WHERE A.STATUS='Active' AND B.STATUS='Active'");

            if (blogDTO.getSubjectMode() != null && blogDTO.getSubjectMode().length() > 0) {
                sql.append(" AND A.SUBJECT=?");
            }
            pstmt = con.prepareStatement(sql.toString());
            if (blogDTO.getSubjectMode() != null && blogDTO.getSubjectMode().length() > 0)
            {
                pstmt.setString(1, blogDTO.getSubjectMode());
            }
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("name", rs.getString(1));
                    map.put("description", rs.getString(2));
                    map.put("mobile", rs.getString(3));
                    map.put("email", rs.getString(4));
                    if (rs.getString(5) != null && rs.getString(5).equals("-")) {
                        map.put("uploadFile", "<img src=\"./images/photo.png\" width=\"90px\" height=\"100px\" alt=\"\"/>");
                    } else {
                        copyPhotoRelativePath(rs.getString(5), blogDTO.getUrl());
                        map.put("uploadFile", "<img src=\"./Blog/" + rs.getString(5) + "\" width=\"90px\" height=\"100px\" alt=\"\"/>");
                    }
                    map.put("postedDate", rs.getString(6));
                    map.put("rowId", rs.getString(7));
                    map.put("subject", rs.getString(8));
                    map.put("subjectName", rs.getString(9));
                    count = getCountOfBlogAnswers(con, rs.getString(7), rs.getString(8));
                    //DataBasePlugin.getString("select count(*) from BlogAnswers where postid='" + rs.getString(7) + "' and subject='" + rs.getString(8) + "'", "ccla");
                    map.put("answerCount", count);
                    postedBlogs.add(map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return postedBlogs;
    }

    /**
     * This method is for getting the blog subjects details
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public static String getAnswers(String postId, String subjectId,DataSource ds) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        int i = 1;
        StringBuffer result = new StringBuffer();
        try {

            con = DBConnection.getConnection();


            sql = "select a.name,a.subject,a.postid,a.description,a.mobile,b.email,a.uploadfile,convert(varchar,a.postedDate,103) as postedDate,c.subject from dbo.BlogAnswers a join BlogPosts b "
                    + "on(a.subject=b.subject and b.postid=a.postid)  join BlogSubjectMaster c on(a.subject=c.subjectid and b.subject=c.subjectid)"
                    + " where a.postid=? and a.subject=? "
                    + "and a.status='Active' and b.status='Active'";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, postId);
            pstmt.setString(2, subjectId);
            rs = pstmt.executeQuery();
            if (rs != null) {
                result.append("<table width=\"95%;\" align=\"right\" cellpadding=\"1\" cellspacing=\"0\">");
                while (rs.next()) {

                    result.append("<tr align=\"left\">");
                    result.append("<td align=\"left\"><font color=\"gray\" style=\"font-style: italic;\" size=\"2px\"><b>" + i++ + ".</b>) &nbsp; Subject : " + rs.getString(9) + ".</font></td>");
                    result.append("<td>&nbsp</td>");
                    result.append("</tr>");

                    result.append("<tr>");
                    result.append("<td>&nbsp;</td>");
                    result.append("<td>&nbsp;</td>");
                    result.append("</tr>");

                    result.append("<tr>");
                    result.append("<td colspan=\"3\"><font color=\"gray\" style=\"font-style: italic;\" size=\"2px\"><p align=\"justify\" style=\"text-indent: 35px\">" + rs.getString(4) + "</p></font><br/></td>");
                    result.append("</tr>");

                    result.append("<tr>");
                    result.append("<td align=\"left\"><font color=\"gray\" style=\"font-style: italic;\" size=\"2px\">Posted By : " + rs.getString(1) + "</font></td>");
                    result.append("<td  align=\"right\"><font color=\"gray\" style=\"font-style: italic;\" size=\"2px\">Mobile : " + rs.getString(5) + "</font></td>");
                    result.append("</tr>");

                    result.append("<tr>");
                    result.append("<td align=\"left\"><font color=\"gray\" style=\"font-style: italic;\" size=\"2px\">Posted On : " + rs.getString(8) + "</font></td>");
                    result.append("<td  align=\"right\"><font color=\"gray\" style=\"font-style: italic;\" size=\"2px\">Mobile : " + rs.getString(6) + "</font></td>");
                    result.append("</tr>");

                    result.append("<tr>");
                    result.append("<td colspan=\"2\">--------------------------------------------------------------------------------------------------------</td>");
                    result.append("</tr>");
                }
                result.append("</table>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return result.toString();
    }

    /**
     * This method is for getting the blog subjects
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public ArrayList getSubjects(DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList subjects = new ArrayList();
        BlogDTO blogDTO = null;
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        String sql = null;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();

            sql = "select SubjectId,Subject from BlogSubjectMaster where status='Active' order by subjectid";
            rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    blogDTO = new BlogDTO();
                    blogDTO.setSubjectId(rs.getString(1));
                    blogDTO.setSubjectName(rs.getString(2));
                    subjects.add(blogDTO);
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
        return subjects;
    }

    /**
     * This method is for getting the subjects for add subjects screen
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public ArrayList getsubjectDetails(DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList subjects = new ArrayList();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();

            String query = "select SubjectId,Subject from BlogSubjectMaster where status='Active' order by subjectid";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Map m = new HashMap();
                    m.put("SubjectId", rs.getString(1));
                    m.put("Subject", rs.getString(2));

                    subjects.add(m);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(ps);
			DBConnection.closeConnection(con); 
        }

        return subjects;
    }

    /**
     * This method is for saving the blog details
     * @param blogDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public String saveBlogDetails(BlogDTO blogDTO, HttpServletRequest request,DataSource ds) throws SADAREMDBException, SQLException {
        String insertStatus = null;
        int status = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            String query = "INSERT INTO BlogAnswers(Name,Subject,PostId,Description,Mobile,Email,PostedDate,UpdatedDate,SystemIp) "
                    + "VALUES(?,?,?,?,?,?,getDate(),getDate(),?)";
            ps = con.prepareStatement(query);
            ps.setString(1, blogDTO.getName());
            ps.setString(2,blogDTO.getSubjectIdForReply());
            ps.setString(3,  blogDTO.getPostId());
            ps.setString(4, blogDTO.getDescription());
            ps.setString(5, blogDTO.getMobile());
            ps.setString(6, blogDTO.getEmail());
            ps.setString(7, blogDTO.getSystemIp());
            status = ps.executeUpdate();
            if (status != 0) {
                insertStatus = "<font color=\"green\">Posted Successfully</font>";

            } else {
                insertStatus = "<font color=\"red\">Error :: Error in While Submitting the Blog</font>";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally 
        {
			DBConnection.closeStatement(ps);
			DBConnection.closeConnection(con); 
        }
        return insertStatus;
    }

    /**
     * This method is for update the subjects details
     * @param blogDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public String updateSubjects(BlogDTO blogDTO,DataSource ds) throws SADAREMDBException, SQLException {
        String updateStatus = null;
        int status = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            String query = "update BlogSubjectMaster set subject=?,"
                    + "UpdatedDate=getDate(),SystemIP=? where subjectId=? and status='Active'";
            ps = con.prepareStatement(query);
            ps.setString(1, blogDTO.getAddSubject());
            ps.setString(2, blogDTO.getSystemIp());
            ps.setString(3, blogDTO.getSubjectId());
            status = ps.executeUpdate();

            if (status != 0) {
                updateStatus = "<font color=\"green\">Subject Updated Successfully</font>";

            } else {
                updateStatus = "<font color=\"red\">Error :: Error in Updating Subject</font>";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally 
        {
			DBConnection.closeStatement(ps);
			DBConnection.closeConnection(con); 
        }
        return updateStatus;
    }

    /**
     * This method is for update the subjects details
     * @param blogDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public String inactiveRecord(BlogDTO blogDTO,DataSource ds) throws SADAREMDBException, SQLException {
        String inactiveStatus = null;
        int status = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            String query = "update BlogSubjectMaster set status='Inactive',"
                    + "UpdatedDate=getDate(),SystemIP=? "
                    + "where subjectId=? and status='Active'";
            ps = con.prepareStatement(query);
            ps.setString(1, blogDTO.getSystemIp());
            ps.setString(2, blogDTO.getSubjectId());
            status = ps.executeUpdate();


            if (status != 0) {
                inactiveStatus = "<font color=\"green\">Selected Record Deleted Successfully</font>";

            } else {
                inactiveStatus = "<font color=\"red\">Error :: Error in Deleting</font>";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally 
        {
			DBConnection.closeStatement(ps);
			DBConnection.closeConnection(con); 
        }
        return inactiveStatus;
    }

    /**
     * This method is for update the subjects details
     * @param blogDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public String inactiveBlogPostRecord(BlogDTO blogDTO,DataSource ds) throws SADAREMDBException, SQLException {
        String inactiveStatus = null;
        int status = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            String query = "update BlogPosts set status='Inactive',"
                    + "UpdatedDate=getDate(),SystemIP=? "
                    + "where postid=? and status='Active'";
            ps = con.prepareStatement(query);
            ps.setString(1, blogDTO.getSystemIp());
            ps.setString(2, blogDTO.getPostId());
            status = ps.executeUpdate();


            if (status != 0) {
                inactiveStatus = "<font color=\"green\">Selected Record Deleted Successfully</font>";

            } else {
                inactiveStatus = "<font color=\"red\">Error :: Error in Deleting</font>";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally 
        {
			DBConnection.closeStatement(ps);
			DBConnection.closeConnection(con); 
        }
        return inactiveStatus;
    }

    /**
     * This method is for Adding the subjects
     * @param blogDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public String addSubjects(BlogDTO blogDTO,DataSource ds) throws SADAREMDBException, SQLException {
        String insertStatus = null;
        int status = 0;
        String subjectId = null;
        int finalValue = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            String query = "select max(subjectId)+1 from BlogSubjectMaster where status='Active'";
           
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            // Get max id from blog subject master table
            if (rs != null) {
                while (rs.next()) {
                    subjectId = rs.getString(1);
                }
            }

            if(subjectId==null){
                subjectId="1";
            }
            if (subjectId != null && subjectId.length() > 0) {
                if (Integer.parseInt(subjectId) == 0) {
                    finalValue = 1;
                } else {
                    finalValue = Integer.parseInt(subjectId);
                }
                String insertQuesy = "INSERT INTO BlogSubjectMaster(SubjectId,Subject,CreatedDate,UpdatedDate,SystemIp) "
                        + "VALUES(?,?,"
                        + "getDate(),getDate(),?)";
                ps = con.prepareStatement(insertQuesy);
                ps.setInt(1, finalValue);
                ps.setString(2, blogDTO.getAddSubject());
                ps.setString(3, blogDTO.getSystemIp());
                status = ps.executeUpdate();
                if (status != 0) {
                    insertStatus = "<font color=\"green\">Subject Added Successfully</font>";

                } else {
                    insertStatus = "<font color=\"red\">Error :: Error in Adding Subject</font>";
                }
            } else {
                insertStatus = "<font color=\"red\">Error :: Error in Adding Subject</font>";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(ps);
			DBConnection.closeConnection(con); 
        }
        return insertStatus;
    }

    /**
     * This method is for saving the blog details Post
     * @param blogDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public String savePostBlogDetails(BlogDTO blogDTO, HttpServletRequest request,DataSource ds) throws SADAREMDBException, SQLException {
        String insertStatus = null;
        int status = 0;
        boolean flag = true;
        String fileName = null;
        String extension = null;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();

            if (blogDTO.getPostUploadPhoto() != null && blogDTO.getPostUploadPhoto().toString().length() > 0) {
                fileName = blogDTO.getPostUploadPhoto().getFileName();
                int dotPos = fileName.lastIndexOf(".");
                extension = fileName.substring(dotPos);
                fileName = blogDTO.getMobile() + extension;
            } else {
                fileName = "-";
            }

            if (blogDTO.getPostUploadPhoto() != null && blogDTO.getPostUploadPhoto().toString().length() > 0) {
                flag = uploadImages(blogDTO.getPostUploadPhoto(), fileName, request, blogDTO.getUrl());
            }

            if (flag) {

                String query = "INSERT INTO BlogPosts(Name,Subject,Description,Mobile,Email,UploadFile,PostedDate,UpdatedDate,SystemIp) "
                        + "VALUES(?,?,?,?,?,?,getDate(),getDate(),?)";
                ps = con.prepareStatement(query);
                ps.setString(1, blogDTO.getName());
                ps.setString(2, blogDTO.getSubject());
                ps.setString(3, blogDTO.getDescription());
                ps.setString(4, blogDTO.getMobile());
                ps.setString(5, blogDTO.getEmail());
                ps.setString(6, fileName);
                ps.setString(7, blogDTO.getSystemIp());
                status = ps.executeUpdate();
                if (status != 0) {
                    insertStatus = "<font color=\"green\">Posted Successfully</font>";
                } else {
                    insertStatus = "<font color=\"green\">Error :: Error in Leaving the Posted</font>";
                }
            } else {
                insertStatus = "<font color=\"green\">Error :: Error While Uploading the Blog Image</font>";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally 
        {
			DBConnection.closeStatement(ps);
			DBConnection.closeConnection(con); 
        }
        return insertStatus;
    }

    /**
     * This method is for saving the blog details Post
     * @param blogDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public String updatePostBlogDetails(BlogDTO blogDTO, HttpServletRequest request,DataSource ds) throws SADAREMDBException, SQLException {
        String insertStatus = null;
        int status = 0;
        boolean flag = true;
        String fileName = null;
        String extension = null;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            if (blogDTO.getPostUploadPhoto() != null && blogDTO.getPostUploadPhoto().toString().length() > 0) {
                fileName = blogDTO.getPostUploadPhoto().getFileName();
                int dotPos = fileName.lastIndexOf(".");
                extension = fileName.substring(dotPos);
                fileName = "Updated_" + blogDTO.getMobile() + extension;
                flag = uploadImages(blogDTO.getPostUploadPhoto(), fileName, request, blogDTO.getUrl());
            } else {
                fileName = "-";
            }

            if (flag) {
                String query = "UPDATE BlogPosts SET Name =?,Subject =? "
                        + ",Description =? ,Mobile =? ,Email =?"
                        + ",UploadFile =? ,UpdatedDate =getDate(),SystemIp =? WHERE postid=? and status='Active'";
                ps = con.prepareStatement(query);
                ps.setString(1, blogDTO.getName());
                ps.setString(2, blogDTO.getSubject());
                ps.setString(3, blogDTO.getDescription());
                ps.setString(4, blogDTO.getMobile());
                ps.setString(5, blogDTO.getEmail());
                ps.setString(6, fileName);
                ps.setString(7, blogDTO.getSystemIp());
                ps.setString(8, blogDTO.getPostId());
                status = ps.executeUpdate();

                if (status != 0) {
                    insertStatus = "<font color=\"green\">Posted Blog Updated Successfully</font>";

                } else {
                    insertStatus = "<font color=\"red\">Error :: Error While Posted Blog Updated</font>";
                }
            } else {
                insertStatus = "<font color=\"red\">Error :: Error While Uploading the Blog Image</font>";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        finally 
        {
			DBConnection.closeStatement(ps);
			DBConnection.closeConnection(con); 
        }
        return insertStatus;
    }

    /**
     * This method is for upload the ccla gos
     * @param uploadFileName
     * @param goNumber
     * @param request
     * @param url
     * @return
     */
    public boolean uploadImages(FormFile uploadFileName, String name, HttpServletRequest request, String url) {
        String extension = null;
        String strDirectoytemp = null;
        boolean flag = false;
        try {

            String fileName = uploadFileName.getFileName();
            int dotPos = fileName.lastIndexOf(".");
            extension = fileName.substring(dotPos);

            fileName = name + extension;
            strDirectoytemp = "D:\\SADAREMTG\\BLOG\\";

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

    /**
     * This method is used for copy files for source folder to destination folder
     * @param fileName
     * @param url
     * @throws Exception
     */
    public void copyPhotoRelativePath(String mobile, String url) throws Exception {
        String strDirectoy = null;

        try {

            File dir = new File("D:\\SADAREMTG\\BLOG\\" + mobile + ".jpg");
            if (dir.exists()) {
                BufferedImage imagePath = ImageIO.read(dir);
                if (imagePath != null && !imagePath.equals("")) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(imagePath, "jpg", baos);
                    byte[] bytesOut = baos.toByteArray();
                    String file = mobile;
                    strDirectoy = url + "Blog\\";
                    File directory = new File(strDirectoy);

                    if (!directory.exists()) {
                        directory.mkdirs();
                    }

                    File fileToCreate = new File(strDirectoy, file);
                    FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                    fileOutStream.write(bytesOut);
                    fileOutStream.flush();
                    fileOutStream.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public String getCountOfBlog(Connection con, String subject) throws SADAREMDBException, SQLException {
        String count = null;
        try {
        	ArrayList paramList = new ArrayList();
        	ArrayList tempList = new ArrayList();
        	
            String query = "select count(*) from blogposts where subject=?";
            
            tempList = new ArrayList();
            tempList.add("S");
            tempList.add(subject);
            paramList.add(tempList);
            
           count = DataAccess.getReturnResultByPstmt(query, paramList);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        } 

        return count;
    }

    public String getCountOfBlogAnswers(Connection con, String post, String subject) throws SADAREMDBException, SQLException 
    {
     String result = null;
    	
        try {
        	
        	ArrayList paramList = new ArrayList();
        	ArrayList tempList = new ArrayList();
        	
        	
            String query = "select count(*) from BlogAnswers where postid=? and subject=?";

            tempList = new ArrayList();
            tempList.add("S");
            tempList.add(post);
            paramList.add(tempList);
            
            tempList = new ArrayList();
            tempList.add("S");
            tempList.add(subject);
            paramList.add(tempList);
            
            
            result = DataAccess.getReturnResultByPstmt(query, paramList);
            
           
        } catch (Exception e) 
        {
            e.printStackTrace();
        } 

        return result;
    }
}
