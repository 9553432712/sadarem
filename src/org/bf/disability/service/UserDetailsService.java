/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;

/**
 *
 * @author t_bapinaidu
 */
public interface UserDetailsService {

    public List getUserDetails(DataSource ds, int campId, String loginId) throws SADAREMDBException, SQLException;

    public int updateLoginDetails(DataSource ds, String loginId, String loginStatus, String selectedRowId) throws SADAREMDBException, SQLException;
}
