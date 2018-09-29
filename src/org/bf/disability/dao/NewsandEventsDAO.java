/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.upload.FormFile;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.form.NewsandEventsForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 728056
 */
public class NewsandEventsDAO {

    public int insertNewsDetails(NewsandEventsForm eventsForm, HttpServletRequest request, String url, DataSource ds) throws Exception {
        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        int newsDetails = 0;
        int regID = 0;
        int regIDData = 0;
        int dataId = 0;
        PreparedStatement pstmt1 = null;
        String uploadName = null;
        String extension = null;
        FormFile file = null;
        String extensionRegId = null;
        String systemIp = null;
        String loginId = null;
        HttpSession session = request.getSession();
        Date date = new SimpleDateFormat("dd/mm/yyyy").parse(eventsForm.getFromDate());
        String fromDate = new SimpleDateFormat("mm/dd/yyyy").format(date);
        Date date1 = new SimpleDateFormat("dd/mm/yyyy").parse(eventsForm.getToDate());
        String toDate = new SimpleDateFormat("mm/dd/yyyy").format(date1);
        try {
            con = con = DBConnection.getConnection();
            if (eventsForm.getNewsUpload() != null && eventsForm.getNewsUpload().getFileSize() > 0) {
                file = eventsForm.getNewsUpload();
                uploadName = file.getFileName();
                int dotPos = uploadName.lastIndexOf(".");
                extension = uploadName.substring(dotPos);
            }

            sql = "select max(News_Id)+1 from NewsAndEvents";
            pstmt1 = con.prepareStatement(sql);
            rs = pstmt1.executeQuery();
            if (rs != null && rs.next()) {
                regID = rs.getInt(1);
            }
            if (regID == 0) {
                regIDData = 1;

            } else {
                regIDData = regID;
            }

            systemIp = request.getRemoteAddr();
            loginId = session.getAttribute("username").toString();

            if (eventsForm.getNewsUpload() != null && eventsForm.getNewsUpload().getFileSize() > 0) {
                extensionRegId = regIDData + extension;
                sql = "INSERT INTO dbo.NewsAndEvents(News_Id,News_Title,Description,UploadPath,fileName,Created_Date,Updated_Date,Status,systemIp,loginId,fromdate,todate,flag)"
                        + " VALUES(?,?,?,?,?,getDate(),getDate(),'Active',?,?,?,?,?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, regIDData);
                pstmt.setString(2, eventsForm.getNewsTitle());
                pstmt.setString(3, eventsForm.getNewsDescription());
                pstmt.setString(4, extensionRegId);
                pstmt.setString(5, file.getFileName());
                pstmt.setString(6, systemIp);
                pstmt.setString(7, loginId);
                pstmt.setString(8, fromDate);
                pstmt.setString(9, toDate);
                pstmt.setString(10,  eventsForm.getFlag());
            } else {
                sql = "INSERT INTO dbo.NewsAndEvents(News_Id,News_Title,Description,Created_Date,Updated_Date,Status,systemIp,loginId,fromdate,todate,flag)"
                        + " VALUES(?,?,?,getDate(),getDate(),'Active',?,?,?,?,?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, regIDData);
                pstmt.setString(2, eventsForm.getNewsTitle());
                pstmt.setString(3, eventsForm.getNewsDescription());
                pstmt.setString(4, systemIp);
                pstmt.setString(5, loginId);
                pstmt.setString(6, fromDate);
                pstmt.setString(7, toDate);
                pstmt.setString(8,  eventsForm.getFlag());
            }

            
            newsDetails = pstmt.executeUpdate();
            if (eventsForm.getNewsUpload() != null && eventsForm.getNewsUpload().getFileSize() > 0) {
                CommonDetails.uploadingFile(eventsForm.getNewsUpload(), filePath("newsEvents"), eventsForm.getNewsUpload().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);  
			DBConnection.closeStatement(pstmt);
			DBConnection.closeStatement(st);
			DBConnection.closeConnection(con); 
        }
        return newsDetails;


    }

    public ArrayList getNewsTitles(DataSource ds) throws Exception {
        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        ArrayList newsTitles = new ArrayList();
        HashMap<Object, Object> map = null;
        try {
            con  = DBConnection.getConnection();
            sql = "select News_Title,News_Id,flag from dbo.NewsAndEvents where status ='Active' and todate>=GETDATE() order by News_Id desc";

            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap<Object, Object>();
                    map.put("title", rs.getString(1));
                    map.put("id", rs.getString(2));
                    if (rs.getString(3) != null && rs.getString(3).equals("New")) {
                        map.put("flag", "<img src='./DisabilityUITG/images/new.gif' border='0' height='20px' width='30px' />");
                    } else if (rs.getString(3) != null && rs.getString(3).equals("Imp")) {
                        map.put("flag", "<img src='./DisabilityUITG/images/Imp.gif' border='0' height='20px' width='30px'/>");
                    }
                    newsTitles.add(map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally 
        {
            DBConnection.closeResultSet(rs);  
			DBConnection.closeStatement(pstmt);
			DBConnection.closeStatement(st);
			DBConnection.closeConnection(con); 
        }
        return newsTitles;
    }

    public ArrayList getNews(String id, HttpServletRequest request, String url, DataSource ds) throws Exception {
        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        ArrayList newsDescription = new ArrayList();
        HashMap<Object, Object> map = null;
        try {
            con = con = DBConnection.getConnection();
            sql = "select  News_Title,Description,News_Id,uploadpath,filename from dbo.NewsAndEvents where News_Id=(?) and status ='Active'";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap<Object, Object>();
                    map.put("titleName", rs.getString(1));
                    map.put("Description", rs.getString(2));
                    map.put("news_Id", rs.getString(3));
                    map.put("ext", rs.getString(4));
                    map.put("filename", rs.getString(5));
                    if (rs.getString(5) != null && !rs.getString(5).equalsIgnoreCase("")) {
                        map.put("iframe", "<img alt='View' src='./DisabilityUITG//images/view.jpg' height='65px' style='cursor: pointer' width='65px' onclick=\"newwindow('" + rs.getString(4) + "')\"/>");
                        map.put("file", "<a href='newsTitles.do?mode=downLoadDetails&news_Id=" + rs.getString(3) + "&ext=" + rs.getString(4) + "&title=" + rs.getString(1) + "&fileName=" + rs.getString(5) + "'><img style='border:0px' src='./DisabilityUITG/images/download.jpg' alt='Download' height='65px' width='65px'/></a>");
                    }
                    newsDescription.add(map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally 
        {
            DBConnection.closeResultSet(rs);  
			DBConnection.closeStatement(pstmt);
			DBConnection.closeStatement(st);
			DBConnection.closeConnection(con); 
        }
        return newsDescription;
    }

    public int insertSuggestionsin(NewsandEventsForm eventsForm, DataSource ds) throws Exception {
        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        int newsDetails = 0;
        HashMap<Object, Object> map = null;
        try {
            con = con = DBConnection.getConnection();
            sql = "INSERT INTO dbo.SuggestionsinPortal(about,SuggestionDescription )VALUES(?,?)";

            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, eventsForm.getAbout());
            pstmt.setString(2, eventsForm.getSugDescription());
            newsDetails = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);  
			DBConnection.closeStatement(pstmt);
			DBConnection.closeStatement(st);
			DBConnection.closeConnection(con); 
        }
        return newsDetails;


    }

    public ArrayList getSuggestion(DataSource ds) throws Exception {
        ArrayList SuggestionsDetails = new ArrayList();
        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;


        HashMap<Object, Object> map = null;

        try {

            con = con = DBConnection.getConnection();
            sql = "SELECT about,SuggestionDescription FROM dbo.SuggestionsinPortal";

            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap<Object, Object>();
                    map.put("about", rs.getString(1));
                    map.put("Description", rs.getString(2));
                    SuggestionsDetails.add(map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);  
			DBConnection.closeStatement(pstmt);
			DBConnection.closeStatement(st);
			DBConnection.closeConnection(con); 
        }
        return SuggestionsDetails;
    }

    //Display Data for NewsAndEvents
    public ArrayList getNewsEvents(String loginId, DataSource ds) throws Exception {
        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        HashMap map = null;
        ArrayList newsList = new ArrayList();
        String fileName = null;
        try {
            con = con = DBConnection.getConnection();
            if (loginId != null && loginId.length() > 0) {
                sql = "select news_id,news_title,Description,filename,uploadpath,convert(varchar,fromdate,103) as date,convert(varchar,todate,103) as date from NewsAndEvents  where  loginid=? and Status ='Active' order by News_Id desc";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, loginId);
            } else {
                sql = "select news_id,news_title,Description,filename,uploadpath,convert(varchar,fromdate,103) as date,convert(varchar,todate,103) as date from NewsAndEvents  where  Status ='Active' order by News_Id desc";
                pstmt = con.prepareStatement(sql);
            }
            
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("newsId", rs.getString(1));
                    map.put("newsTitle", rs.getString(2));
                    map.put("description", rs.getString(3));
                    // fileName = rs.getString(4); view
                    map.put("filename", "<a href=\"./newsandEvents.do?mode=newsDownLoadDetails&id=" + rs.getString(1) + "&uploadpath=" + rs.getString(5) + "&fileName=" + rs.getString(4) + "\"><img src=\"./DisabilityUITG/images/View.png\" height='25px' width='25px' border=\"0\"/></a>");
                    map.put("fromdate", rs.getString(6));
                    map.put("todate", rs.getString(7));
                    newsList.add(map);
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);  
			DBConnection.closeStatement(pstmt);
			DBConnection.closeStatement(st);
			DBConnection.closeConnection(con); 
        }
        return newsList;
    }

    public ArrayList editNewsDetails(NewsandEventsForm eventsForm, String newsId, DataSource ds) throws Exception {
        ArrayList editList = new ArrayList();
        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        HashMap<Object, Object> map = null;
        try {
            con = con = DBConnection.getConnection();
            sql = "select News_Title,Description,convert(varchar,fromdate,103) as date,convert(varchar,todate,103) as date,flag from dbo.NewsAndEvents where news_id =?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, newsId);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    editList.add(rs.getString(1));
                    editList.add(rs.getString(2));
                    editList.add(rs.getString(3));
                    editList.add(rs.getString(4));
                    editList.add(rs.getString(5));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally 
        {
            DBConnection.closeResultSet(rs);  
			DBConnection.closeStatement(pstmt);
			DBConnection.closeStatement(st);
			DBConnection.closeConnection(con); 
        }

        return editList;


    }

    public int updateNewsDetails(NewsandEventsForm eventsForm, String rowId, HttpServletRequest request, String url, DataSource ds) throws Exception {

        int updateDetails = 0;
        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        String query = null;
        String file = null;
        String extension = null;
        String totalExt = null;
        Date date = new SimpleDateFormat("dd/mm/yyyy").parse(eventsForm.getFromDate());
        String fromDate = new SimpleDateFormat("mm/dd/yyyy").format(date);
        Date date1 = new SimpleDateFormat("dd/mm/yyyy").parse(eventsForm.getToDate());
        String toDate = new SimpleDateFormat("mm/dd/yyyy").format(date1);
        try {
            con = con = DBConnection.getConnection();
            if (eventsForm.getNewsUpload() != null && eventsForm.getNewsUpload().toString().length() > 0) {
                file = eventsForm.getNewsUpload().getFileName();
                int dotPos = file.lastIndexOf(".");
                extension = file.substring(dotPos);
                totalExt = rowId + extension;
                query = "UPDATE NewsAndEvents SET updated_Date=getDate(),News_Title = ?,"
                        + " Description = ?,UploadPath =?, filename=?,"
                        + " Status= 'Active',fromdate=?,todate=?,flag=? WHERE News_Id =?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, eventsForm.getNewsTitle());
                pstmt.setString(2, eventsForm.getNewsDescription());
                pstmt.setString(3, totalExt);
                pstmt.setString(4, eventsForm.getNewsUpload().toString());
                pstmt.setString(5, fromDate);
                pstmt.setString(6, toDate);
                pstmt.setString(7, eventsForm.getFlag());
                pstmt.setString(8, rowId);
            } else {
                query = "UPDATE NewsAndEvents SET updated_Date=getDate(),News_Title = ?,"
                        + " Description = ?,"
                        + " Status= 'Active',fromdate=?,todate=?,flag=? WHERE News_Id =?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, eventsForm.getNewsTitle());
                pstmt.setString(2, eventsForm.getNewsDescription());
                pstmt.setString(3, fromDate);
                pstmt.setString(4, toDate);
                pstmt.setString(5, eventsForm.getFlag());
                pstmt.setString(6, rowId);

            }

            

            updateDetails = pstmt.executeUpdate();

            if (updateDetails != 0 && eventsForm.getNewsUpload() != null && eventsForm.getNewsUpload().toString().length() > 0) {
                CommonDetails.uploadingFile(eventsForm.getNewsUpload(), filePath("newsEvents"), eventsForm.getNewsUpload().toString());

            }


        } catch (Exception e) {
            e.printStackTrace();
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);  
			DBConnection.closeStatement(pstmt);
			DBConnection.closeStatement(st);
			DBConnection.closeConnection(con); 
        }
        return updateDetails;


    }

    public int deleteNewsDetails(NewsandEventsForm eventsForm, String rowId, DataSource ds) throws Exception {
        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        int deleteNewsDetails = 0;


        String query = null;


        try {
            con = con = DBConnection.getConnection();

            query = "UPDATE dbo.NewsAndEvents set status ='Inactive',updated_Date=getDate()"
                    + " WHERE News_Id =?";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, rowId);
            deleteNewsDetails = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally 
        {
            DBConnection.closeResultSet(rs);  
			DBConnection.closeStatement(pstmt);
			DBConnection.closeStatement(st);
			DBConnection.closeConnection(con); 
        }
        return deleteNewsDetails;


    }
    public static String filePath(String path) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("com.myapp.struts.ApplicationResource");
        String filePath = resourceBundle.getString("drive") + resourceBundle.getString(path);
        return filePath;
    }

    /**
     * This method is for getting the visitorCount
     * @return String
     * @throws CCLAException
     */
    public String visitorCount(DataSource ds) throws Exception {

        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        String sql = null;
        String vistitorCount = null;

        String maxcount = "";
        String query = null;


        try {
            con = con = DBConnection.getConnection();

            query = "select coalesce(max(visitorCount),'0')+1 from visitorcount where status='Active'";

            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                maxcount = rs.getString(1);
            }

            pstmt = con.prepareStatement("update visitorcount set visitorCount=?");
            pstmt.setString(1, maxcount);

            pstmt.executeUpdate();



            pstmt = con.prepareStatement("select max(visitorCount) from visitorcount where status='Active'");
            rs1 = pstmt.executeQuery();
            while (rs1.next()) {
                vistitorCount = rs1.getString(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally 
        {
            DBConnection.closeResultSet(rs);  
			DBConnection.closeStatement(pstmt);
			DBConnection.closeStatement(st);
			DBConnection.closeConnection(con); 
        }
        return vistitorCount;


    }
}
