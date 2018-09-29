package org.bf.disability.common.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;


import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.LabelValueBean;
import org.bf.disability.Exception.SADAREMException;
import java.sql.CallableStatement;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.Constants.CommonConstants;

public class DataBasePlugin implements PlugIn {

    static String civilDB = "civilDB";
   // static String sadaremDB = "sadarem";
    static DataSource datasourceCivil;
   // static DataSource datasourceSadarem;
    static ServletContext context = null;

    public DataBasePlugin() {
        //    	initializing datasource
    }

    public void destroy() {
    }
    /* (non-Javadoc)
     * @see org.apache.struts.action.PlugIn#init(org.apache.struts.action.ActionServlet, org.apache.struts.config.ModuleConfig)
     */

    public void init(ActionServlet actionServlet, ModuleConfig modConfig) throws ServletException {
        ServletContext context = actionServlet.getServletContext();
        datasourceCivil = (DataSource) context.getAttribute(civilDB);
       // datasourceSadarem = (DataSource) context.getAttribute(sadaremDB);
    }

    /** Generalized function to get Connection */
    public static Connection getConnection(String user) throws SADAREMDBException, SQLException {

        Connection con = null;

        try {

            if (user.equals(civilDB)) {
                con = datasourceCivil.getConnection();}
//            } else if (user.equals(sadaremDB)) {
//                con = datasourceSadarem.getConnection();
//            }

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return con;
    }

    /** Generalized function to get Connection */
    public static Connection getCivilConnection() {

        Connection con = null;

        try {

            Class.forName("net.sourceforge.jtds.jdbcx.JtdsDataSource");
            con = DriverManager.getConnection("jdbc:jtds:sqlserver:/10.100.100.3:1433/CivilSupply_Database", "sa", "sadarem@123");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    /** Generalized function to close all connections */
    public synchronized static void close(Connection con) {

        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Function to return ArrayList and Map start*/
    /** General Function to execute any select Query
     * @return Double Dimension ArrayList
     */
    public synchronized static ArrayList<ArrayList<String>> selectQueryDouble(String sql, String user) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<ArrayList<String>> matrix = new ArrayList<ArrayList<String>>();
        try {
            con = getConnection(user);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            matrix = getRs2ArrayList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con);
        }
        return matrix;
    }

    /** General Function to execute any select Query
     * @return Single Dimension ArrayList
     */
    public synchronized static ArrayList<String> selectQuery(String sql, String user) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<String> matrix = new ArrayList<String>();
        try {
            con = getConnection(user);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            matrix = getRsSingleArrayList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con);
        }
        return matrix;
    }

    /** General Function to execute any select Query
     * @return String
     */
//	This method is user for getting the data form the database and put into ArrayList
    public static ArrayList getselectQryfor2D(String sql, String user) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;//Declare a method of type ArrayList
        ArrayList QRY = new ArrayList();
        try {
            con = getConnection(user);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            QRY = getArrayList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                close(con);                //Closing the database connections
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return QRY;
    }

    public static ArrayList getArrayList(ResultSet rs) {
        ArrayList arr = new ArrayList();
        ArrayList row = null;
        try {
            if (rs != null) {
                int count = count(rs);
                while (rs.next()) {
                    row = new ArrayList();
                    for (int i = 1; i <= count; i++) {
                        row.add(rs.getString(i));
                    }
                    arr.add(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

//	This method returns the column count of the resultset
    public static int count(ResultSet rs) {
        int rowCount = 0;
        try {
            ResultSetMetaData rsm = rs.getMetaData();
            rowCount = rsm.getColumnCount();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    public static int executeUpdate(String sql, String user) {               //Declare a method executeUpdate
        int insert = 0;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            //	con = dataSource.getConnection();
            con = getConnection(user);                          //connect the database
            st = con.createStatement();
            insert = st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con);
        }
        return insert;
    }

    public static ArrayList getLabelValueBeans1(List twoDimList, boolean forCombo) {
        ArrayList lvBeans = new ArrayList();
        Iterator iter = twoDimList.iterator();
        while (iter.hasNext()) {
            ArrayList rowItem = (ArrayList) iter.next();
            if (rowItem.size() > 1) {
                lvBeans.add(new LabelValueBean((String) rowItem.get(0), (String) rowItem.get(1)));
            } else {
                lvBeans.add(new LabelValueBean((String) rowItem.get(0), (String) rowItem.get(0)));
            }
        }
        if (forCombo) {
            lvBeans.add(0, new LabelValueBean("--Select--", ""));
        }
        return lvBeans;
    }

    public synchronized static String selectQueryString(String sql, String user) throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String value = "";
        try {
            con = getConnection(user);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    value = rs.getString(1).trim();
                }
            }
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            close(con);
        }
        return value;
    }

    /** General Function to execute any select Query
     * @return List for Maps
     */
    public static List<Map<String, Object>> selectQueryMap(String sql, String user) {
        Connection con = null;

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        try {
            con = getConnection(user);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            result = processResultSet(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con);
        }
        return result;
    }

    /* Function to return ArrayList and Maps end*/
    /* DML Funtions Start*/
    /** General Function to do Data Manipultaions i.e. Insert,Update,Delete
     * @return boolean
     */
    public synchronized static boolean executeDML(String sql, String user) {

        Connection con = null;
        PreparedStatement ps = null;

        int value = 0;
        try {
            con = getConnection(user);
            ps = con.prepareStatement(sql);
            value = ps.executeUpdate();
            if (value > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con);
        }
        return false;
    }

    /** General Function to execute Batch Query
     * @return boolean
     */
    public static int[] executeBatch(ArrayList sqls, Connection conn) throws SQLException {

        Statement st = null;
        if (null == conn || null == sqls || 0 == sqls.size()) {
            throw new SQLException("executeBatch(): NULL Connection/Query ");
        }
        st = conn.createStatement();

        for (int i = 0; i < sqls.size(); i++) {
            st.addBatch((String) sqls.get(i));
        }

        int count[] = st.executeBatch();
        return count;
    }

    /** General Function to execute Insert Query in for loop
     * @return boolean
     */
    public static boolean executeInsertBoolean(Connection con, String SQL) {
        boolean flag = false;
        Statement st = null;
        try {
            st = con.createStatement();
            int k = st.executeUpdate(SQL);
            if (k == Statement.EXECUTE_FAILED) {
                con.rollback();
            } else {
                con.commit();
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /* DML Funtions End*/
    /** function which returns No. of Rows in a ResultSet */
    public static int getRowCount(ResultSet rs) {
        int rowCount = 0;
        try {
            while (rs.next()) {
                rowCount++;
            }
            rs.first();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    /** function which returns No. of Columns Count in a ResultSet */
    public static int getColCount(ResultSet rs) {
        int rowCount = 0;
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            rowCount = rsmd.getColumnCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    /* Funtions to convert ResultSet into ArrayList start*/
    /** General Functions to convert ResultSet into Single and Double Dimension ArrayList
     * @return ArrayList
     */
    public static ArrayList<ArrayList<String>> getRs2ArrayList(ResultSet rs) {
        ArrayList<ArrayList<String>> matrix = new ArrayList<ArrayList<String>>();
        ArrayList<String> row = new ArrayList<String>();

        try {
            if (rs != null) {
                int colCount = getColCount(rs);
                while (rs.next()) {
                    row = new ArrayList<String>();
                    for (int i = 1; i <= colCount; i++) {
                        row.add(rs.getString(i).trim());
                    }
                    matrix.add(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matrix;
    }

    public static ArrayList<String> getRsSingleArrayList(ResultSet rs) {
        ArrayList<String> matrix = new ArrayList<String>();
        try {
            if (rs != null) {
                int colCount = getColCount(rs);
                while (rs.next()) {
                    for (int i = 1; i <= colCount; i++) {
                        matrix.add(rs.getString(i).trim());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matrix;
    }

    /* Funtions to convert ResultSet into ArrayList end*/
    /* Funtions to convert ResultSet into List of Maps Start*/
    /** General Functions to convert ResultSet into List of Maps
     * @return List
     */
    private static List<Map<String, Object>> processResultSet(ResultSet rs) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        ResultSetMetaData rm = rs.getMetaData();
        int cols = rm.getColumnCount();
        if (rs.next()) {
            result = new ArrayList<Map<String, Object>>();
            do {
                Map<String, Object> row = new HashMap<String, Object>(cols);
                for (int i = 1; i <= cols; i++) {
                    String columnName = rm.getColumnName(i);
                    Object columnValue = rs.getObject(i);
                    row.put(columnName, columnValue);
                }
                result.add(row);

            } while (rs.next());
        }
        return result;
    }

    /* Funtions to convert ResultSet into List of Maps End*/
    /** General Functions to execute Select Query
     * @return ArrayList of LabelValueBean
     */
    public static ArrayList<LabelValueBean> getLabelValueBean(String sql, boolean forCombo, String user) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<LabelValueBean> matrix = new ArrayList<LabelValueBean>();
        try {
            if (forCombo) {
                matrix.add(new org.apache.struts.util.LabelValueBean("-----Select-----", ""));
            }
            con = getConnection(user);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    matrix.add(new org.apache.struts.util.LabelValueBean(rs.getString(2).trim(), rs.getString(1).trim()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con);
        }
        return matrix;
    }

    /** General Functions to execute Select Query
     * @return String Array
     */
    public static String[] returnStringArray(String sql, String user) {

        ArrayList vals = selectQuery(sql, user);
        String assignedServices[] = new String[vals.size()];

        try {

            for (int i = 0; i < vals.size(); i++) {
                assignedServices[i] = vals.get(i).toString().trim();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return assignedServices;
    }

    public static ArrayList<LabelValueBean> getLabelValueBean1(String sql, boolean forCombo, String user) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<LabelValueBean> matrix = new ArrayList<LabelValueBean>();
        try {
            if (forCombo) {
                matrix.add(new org.apache.struts.util.LabelValueBean("-----Select-----", ""));
            }
            con = getConnection(user);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    matrix.add(new org.apache.struts.util.LabelValueBean(rs.getString(2).trim(), rs.getString(1).trim()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con);
        }
        return matrix;
    }

    public static String getFormat(double s3) {
        long m = (long) s3;
        String s4 = String.valueOf(m);
        String s = s4;
        String s2 = "";
        int l = s.length();
        if (l < 4) {
            s2 = s4;
        } else {
            String s1 = s.substring(0, l - 3);

            int k = s1.length();
            char ch[] = s1.toCharArray();
            if (k > 4) {
                for (int i = 0; i < ch.length - 4; i++) {
                    s2 += String.valueOf((ch[i]));
                }
                s2 += String.valueOf(",");
                for (int i = ch.length - 4; i < ch.length; i = i + 2) {
                    s2 += String.valueOf(ch[i]);
                    s2 += String.valueOf(ch[i + 1]);
                    s2 += String.valueOf(",");
                }
            } else {
                if (l % 2 == 0) {
                    s2 += String.valueOf(ch[0]);
                    s2 += String.valueOf(",");
                    for (int i = 1; i < ch.length; i = i + 2) {
                        s2 += String.valueOf(ch[i]);
                        s2 += String.valueOf(ch[i + 1]);
                        s2 += String.valueOf(",");
                    }
                } else {
                    for (int i = 0; i < ch.length; i = i + 2) {
                        s2 += String.valueOf(ch[i]);
                        s2 += String.valueOf(ch[i + 1]);
                        s2 += String.valueOf(",");
                    }
                }
            }
            s2 += s.substring(l - 3, l);
        }
        return s2;
    }

    public static void closeConnection(Connection con) throws SADAREMDBException, SQLException {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            con = null;
        }
    }

    public static void closeStatement(Statement stmt) throws SADAREMDBException, SQLException {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            stmt = null;
        }
    }

    public static void closePreparedStatement(PreparedStatement pstmt) throws SADAREMDBException, SQLException {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                pstmt = null;
            }
        }
    }

    public static void closeResultSet(ResultSet rs) throws SADAREMDBException, SQLException {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            rs = null;
        }
    }

    public static void closeCallableStatement(CallableStatement calstmt) throws SADAREMDBException, SQLException {
        if (calstmt != null) {
            try {
                calstmt.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            calstmt = null;
        }
    }

    public static void closeAll(Connection con, Statement stmt, PreparedStatement pstmt, ResultSet rs) throws SADAREMDBException, SQLException {


        closeConnection(con);
        closeStatement(stmt);
        closePreparedStatement(pstmt);
        closeResultSet(rs);

    }

    public static void rollbackConnection(Connection con) throws SADAREMDBException, SQLException {
        try {
            con.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
