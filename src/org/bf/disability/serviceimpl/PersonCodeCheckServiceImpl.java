/*
 * PersonCodeCheckServiceImpl.java
 *
 * Created on September 13, 2008, 10:57 AM
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
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.PersonCodeCheckDAO;
import org.bf.disability.service.PersonCodeCheckService;

/**
 * This class has the implementation functionality for PersonCodeCheckService
 * interface.
 * @author Deviprasad T
 * @version 1.0
 */
public class PersonCodeCheckServiceImpl implements PersonCodeCheckService {

    public boolean checkPersonCode(String personcode, DataSource ds) throws SADAREMDBException, SQLException {
        PersonCodeCheckDAO PersonCodeCheckDAO = new PersonCodeCheckDAO();
        boolean falg;
        try {
            falg = PersonCodeCheckDAO.checkPersonCode(personcode, ds);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkPersonCode", "PersonCodeCheckServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckServiceImpl", "checkPersonCode");
        }
        return falg;
    }

    public ArrayList getCalculatedValues(String presoncode, DataSource ds) throws SADAREMDBException, SQLException {
        PersonCodeCheckDAO PersonCodeCheckDAO = new PersonCodeCheckDAO();
        ArrayList calculatedValueslist;
        try {
            calculatedValueslist = PersonCodeCheckDAO.getCalculatedValues(presoncode, ds);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCalculatedValues", "PersonCodeCheckServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonCodeCheckServiceImpl", "getCalculatedValues");
        }
        return calculatedValueslist;

    }
}
