package org.bf.disability.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.bf.disability.Exception.SADAREMDBException;

import com.tcs.sgv.common.util.DBConnection;

public class ExceptionDAO {

    /**
     * This method returns the list of availble districts from the database.
     *
     * @return ArrayList Object
     * @param datasource DataSource Object
     * @throws java.lang.Exception
     */
    public static int saveException(DataSource ds, String exception, String method, String DAO, String type) throws SADAREMDBException, SQLException {
        Connection con = null;
        // PreparedStatement pstmt=null;
        PreparedStatement stmt = null;
        int exe = 0;
        ResultSet rs = null;

        try {
            //con =DBConnection.getConnection();

            con = DBConnection.getConnection();
            // this query gets district id and district name
            String query = "INSERT INTO SADAREM_Exceptions VALUES (?,?,?,getDate(),getDate(),?)";


            stmt = con.prepareStatement(query);
            stmt.setString(1, exception);
            stmt.setString(2, method);
            stmt.setString(3, DAO);
            stmt.setString(4, type);
            exe = stmt.executeUpdate();

            //end of try block
        } catch (Exception e) {
            e.printStackTrace();
        }//end of catch block
        finally {

            try {
                if (con != null) {
                    con.close();
                }
                if(stmt!=null)
                {
                	stmt.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

        }//end of finally block
        return exe;
    }//end of getDistricts()
}
