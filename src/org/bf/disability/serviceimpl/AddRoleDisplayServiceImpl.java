/*
 * AddRoleDisplayServiceImpl.java
 *
 * Created on September 12, 2008, 5:30 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.AddRoleDisplayDAO;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.service.AddRoleDisplayService;


/**
 * This class has the implementation functionality for AddRoleDisplayService interface.
 * @author Deviprasad
 * @version 1.0
 */
public class AddRoleDisplayServiceImpl implements AddRoleDisplayService {
    public ArrayList getService(DataSource ds) throws SADAREMDBException, SQLException
    {
        ArrayList servicelist = new ArrayList();
        AddRoleDisplayDAO addroledisplaydao = new AddRoleDisplayDAO();
        try{
            return addroledisplaydao.getService(ds);
        }catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getService", "AddRoleDisplayServiceImpl","Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddRoleDisplayServiceImpl", "getService");
        }
    }
}
