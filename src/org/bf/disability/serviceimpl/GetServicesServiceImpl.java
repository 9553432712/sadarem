/*
 * GetServicesServiceImpl.java
 *
 * Created on September 13, 2008, 11:08 AM
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
import org.bf.disability.dao.GetServicesDAO;
import org.bf.disability.service.GetServicesService;

/**
 * This class has the implementation functionality for GetServicesService
 * interface.
 * @author SVS Ganesh
 * @version 1.0
 */
public class GetServicesServiceImpl implements GetServicesService {

    GetServicesDAO getservicesdao = new GetServicesDAO();

    /** Creates a new instance of GetServicesServiceImpl */
    public ArrayList getService(DataSource ds, String roleid) throws SADAREMDBException, SQLException {

        ArrayList servicelist = new ArrayList();
        try {
            servicelist = getservicesdao.getService(ds, roleid);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getService", "GetServicesServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GetServicesServiceImpl", "getService");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getService", "GetServicesServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GetServicesServiceImpl", "getService");
        }
        return servicelist;
    }
}
