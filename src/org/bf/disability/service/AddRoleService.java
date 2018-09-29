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
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.dao.AddRoleDAO;
import org.bf.disability.dao.AddServicestoRoleDAO;
import org.bf.disability.form.ServiceBean;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;


/**
 * This interface contains abstract mehtods, whose implementation contains logic 
 * to add a new role or to add services to the specified role
 * @version 1.0
 */
public interface AddRoleService {
    
    /** Creates a new instance of DistrictService */
   public int addRole(DataSource ds,String rolename)throws  SADAREMDBException, SQLException;

   public int addServicestoRole(DataSource ds,int roleid,ArrayList servicelist)throws SADAREMDBException, SQLException;

}