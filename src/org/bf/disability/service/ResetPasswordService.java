/*
 * DistrictService.java
 *
 * Created on December 7, 2006, 12:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.Users;

/**
 * This interface has an abstract method, whose implementation contains the logic to 
 * perform reset password operation.
 * @author Deviprasad
 * @version 1.0
 */
public interface ResetPasswordService {

    /** Creates a new instance of DistrictService */
    public int resetPassword(DataSource ds, Users users) throws SADAREMDBException, SQLException;
}
