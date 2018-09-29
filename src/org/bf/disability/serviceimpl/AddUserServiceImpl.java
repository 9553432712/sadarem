/*
 * AddUserServiceImpl.java
 *
 * Created on September 12, 2008, 5:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.AddUserDAO;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.form.Roles;
import org.bf.disability.service.AddUserService;

/**
 * This class has the implementation functionality for AddUserService interface.
 * @author Deviprasad
 * @version 1.0
 */
public class AddUserServiceImpl implements AddUserService 
{

    public int addUser(DataSource ds, Roles adduser, String districtid_id, String loginid_id, int campid_id, String localaddr, String userId) throws SADAREMDBException, SQLException 
    {

        AddUserDAO adduserdao = new AddUserDAO();

        try 
        {
            return adduserdao.addUser(ds, adduser, districtid_id, loginid_id, campid_id, localaddr, userId);
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "addUser", "AddUserServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddUserServiceImpl", "addUser");
        }
    }
    
   
}
