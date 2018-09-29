/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Festival;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.bf.disability.Exception.SADAREMException;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 484898
 */
public class FestivalDAO {

    public String getFestivalImage(DataSource ds) throws SADAREMException {
        String path = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;
        try {
            con = DBConnection.getConnection();
            sql = "select folderpath+'#'+filename as path from Festivals where convert(varchar,date,103)=(select convert(varchar,getdate(),103)) and status='Active'";
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                path = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SADAREMException();
        } finally {
            try {
                DBConnection.closeResultSet(rs);
                DBConnection.closeStatement(ps);
                DBConnection.closeConnection(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return path;
    }

    public void copyDirectory(File sourceLocation, File targetLocation)
            throws IOException {

        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }

            String[] children = sourceLocation.list();
            for (int i = 0; i < children.length; i++) {
                copyDirectory(new File(sourceLocation, children[i]),
                        new File(targetLocation, children[i]));
            }
        } else {

            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);

            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
    }
}
