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

/**
 * This interface has an abstract method, whose implementation is to get services
 * based on the given role id
 * @version 1.0
 */
public interface GetServicesService {

    /** Creates a new instance of SocialAuditService */
    public ArrayList getService(DataSource ds, String roleid) throws SADAREMDBException, SQLException;
}
