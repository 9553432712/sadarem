/*
 * SocialAuditService.java
 *
 * Created on February 5, 2007, 4:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.service;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import java.sql.SQLException;

/**
 * This interface contains an abstract method getService(), whose implementation
 * contains the logic for retriving services based on user role
 * @version 1.0
 * @author Pramod Kumar G
 */
public interface AddRoleDisplayService {
    
    
    public ArrayList getService(DataSource ds)throws SADAREMDBException, SQLException;
    
}
