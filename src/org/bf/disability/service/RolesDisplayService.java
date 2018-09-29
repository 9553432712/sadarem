/*
 * SocialAuditService.java
 *
 * Created on February 5, 2007, 4:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.RolesDisplayDAO;

/**
 * This interface has an abstract method, whose implementation deals with the logic
 * of retriving roles from database
 * @author Deviprasad
 * @version 1.0
 */
public interface RolesDisplayService {

    public ArrayList getRoles(DataSource ds) throws SADAREMDBException, SQLException;
}
