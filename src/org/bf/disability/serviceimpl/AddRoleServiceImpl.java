/*
 * AddRoleServiceImpl.java
 *
 * Created on September 12, 2008, 5:11 PM
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
import org.bf.disability.dao.AddRoleDAO;
import org.bf.disability.dao.AddServicestoRoleDAO;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.form.ServiceBean;
import org.bf.disability.service.AddRoleService;
/**
 * This class has the implementation functionality for AddRoleService interface.
 * @author Deviprasad
 * @version 1.0
 */

public class AddRoleServiceImpl implements AddRoleService{
    AddRoleDAO addroledao=new AddRoleDAO();
    public int addRole(DataSource ds,String rolename) throws SADAREMDBException, SQLException{
        
        try {
            AddRoleDAO addroledao=new AddRoleDAO();
            
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "addRole", "AddRoleServiceImpl","Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddRoleServiceImpl", "addRole");
        }
        return addroledao.addRole(ds,rolename);
    }
    
    
    
    public int addServicestoRole(DataSource ds,int roleid,ArrayList servicelist) throws SADAREMDBException, SQLException {
        AddServicestoRoleDAO addServicestoroledao = new AddServicestoRoleDAO();
        String serviceid;
        
        try {
            for(int i=0;i<servicelist.size();i++) {
                ServiceBean servicebean= (ServiceBean)servicelist.get(i);
                if(servicebean.getSelect()!=null) {
                    if(servicebean.getSelect().equals("on")) {
                        serviceid=servicebean.getService_id();
                        int j=addServicestoroledao.addServicestoRole(ds,Integer.toString(roleid),serviceid);
                        if(j==0)
                            return j;
                    }
                }
            }
        }catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "addServicestoRole", "AddRoleServiceImpl","Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddRoleServiceImpl", "addServicestoRole");
        }
        return 1;
    }
    
}
