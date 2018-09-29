/*
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.DataSource;

import org.bf.disability.form.ImportantLinkForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 693461
 */
public class ImportantLinkDAO {

    public int insertImpLinkDetails(DataSource ds, ImportantLinkForm importantLinkForm) throws Exception {
        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = null;

        int impDetails = 0;
        try {
            con = DBConnection.getConnection();
            sql = "INSERT INTO ImpLinkDetails(WebSiteName"
                    + ",WebSiteUrl,Created_Date,Updated_Date,Status,loginid,systemip)"
                    + " VALUES(?,?,getDate(),getDate(),'Active',?,?)";

            pstmt = con.prepareStatement(sql);
            
            
            pstmt.setString(1, importantLinkForm.getWebSiteName());
            pstmt.setString(2, importantLinkForm.getWebSiteUrl());
            pstmt.setString(3, importantLinkForm.getLoginId());
            pstmt.setString(4, importantLinkForm.getSystemIp());
            
            
            impDetails = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return impDetails;


    }

    public ArrayList getImpLinkDetails(DataSource ds) throws Exception {

        ArrayList impList = new ArrayList();
        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        String query = null;
        HashMap mapData = null;


        try {
            con = DBConnection.getConnection();
            query = "select RowId,WebSiteName,WebSiteUrl from dbo.ImpLinkDetails where status ='Active'";

            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            String urlPath;
            if (rs != null) {
                while (rs.next()) {
                    mapData = new HashMap();
                    mapData.put("rowId", rs.getString(1));
                    mapData.put("webSiteName", rs.getString(2));
                    if (rs.getString(3).length() > 4 && rs.getString(3).substring(0, 4).equalsIgnoreCase("http")) {
                        urlPath = rs.getString(3);
                    } else {
                        urlPath = "http://" + rs.getString(3);
                    }
                    mapData.put("webSiteUrl", urlPath);
                    impList.add(mapData);
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
        return impList;


    }

    public ArrayList getEditLinkDetails(DataSource ds, String rowId) throws Exception {

        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = null;
        ArrayList editList = new ArrayList();
        HashMap mapData = null;


        try {

            con = DBConnection.getConnection();
            query = "select WebSiteName,WebSiteUrl from dbo.ImpLinkDetails where status ='Active' and "
                    + " RowId =?";
            pstmt = con.prepareStatement(query);

            
            pstmt.setString(1, rowId);
            
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    mapData = new HashMap();

                    editList.add(rs.getString(1));
                    editList.add(rs.getString(2));
                    editList.add(mapData);

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
        return editList;
    }

    public int updateImpLinkDetails(DataSource ds, ImportantLinkForm importantLinkForm, String webSiteId) throws Exception {

        int updateDetails = 0;
        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = null;

        try {
            con = DBConnection.getConnection();
            query = "UPDATE ImpLinkDetails  SET WebSiteName = ? ,WebSiteUrl = ?"
                    + ",Updated_Date = getDate() "
                    + " WHERE RowId =?";

            pstmt = con.prepareStatement(query); 
            
            pstmt.setString(1, 	importantLinkForm.getWebSiteName()); 
            pstmt.setString(2,  importantLinkForm.getWebSiteUrl() ); 
            pstmt.setString(3, 	webSiteId); 
            
            
            updateDetails = pstmt.executeUpdate();



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return updateDetails;

    }

    public int deleteImpLinkDetails(DataSource ds, String webSiteId) throws Exception {

        int updateDetails = 0;
        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = null;

        try {
            con = DBConnection.getConnection();
            query = "UPDATE ImpLinkDetails SET Status ='Inactive',updated_date=getDate() WHERE RowId =?";

            pstmt = con.prepareStatement(query);  
            
            pstmt.setString(1, webSiteId);
            updateDetails = pstmt.executeUpdate();



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeResultSet(rs);
        }
        return updateDetails;

    }

    public ArrayList getReportDetails(DataSource ds) throws Exception {

        ArrayList updateList = new ArrayList();
        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = null;

        HashMap mapData = new HashMap();


        try {
            con = DBConnection.getConnection();
            query = "select WebSiteName,WebSiteUrl from dbo.ImpLinkDetails where status ='Active'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            String urlPath;
            if (rs != null) {
                while (rs.next()) {
                    mapData = new HashMap();
                    mapData.put("webSiteName", rs.getString(1));
                    if (rs.getString(2).length() > 4 && rs.getString(2).substring(0, 4).equalsIgnoreCase("http")) {
                        urlPath = rs.getString(2);
                    } else {
                        urlPath = "http://" + rs.getString(2);
                    }
                    mapData.put("webSiteUrl", urlPath);
                    updateList.add(mapData);

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
        return updateList;


    }
}
