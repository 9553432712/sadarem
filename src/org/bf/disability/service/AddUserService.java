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
import org.bf.disability.form.Roles;


/**
 * This interface contains an abstract method, whose implementation contains the
 * logic to adding a user
 * @version 1.0
 */
public interface AddUserService {

    /** Creates a new instance of DistrictService */
   public int addUser(DataSource ds,Roles adduser,String districtid_id,String loginid_id,int campid_id,String localaddr,String userId)throws SADAREMDBException, SQLException;


}