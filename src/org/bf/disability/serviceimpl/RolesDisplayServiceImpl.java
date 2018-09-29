/*
 * RolesDisplayServiceImpl.java
 *
 * Created on September 13, 2008, 12:23 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

/**
 *
 * @author kiran_h
 */
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.RolesDisplayDAO;
import org.bf.disability.service.RolesDisplayService;

/**
 * This class has the implementation functionality for RolesDisplayService
 * interface.
 * @author Deviprasad T
 * @version 1.0
 */
public class RolesDisplayServiceImpl implements RolesDisplayService {

    /** Creates a new instance of SocialAuditService */
    public ArrayList getRoles(DataSource ds) throws SADAREMDBException, SQLException {

        RolesDisplayDAO rolesdisplaydao = new RolesDisplayDAO();
        return rolesdisplaydao.getRoles(ds);

    }
}
