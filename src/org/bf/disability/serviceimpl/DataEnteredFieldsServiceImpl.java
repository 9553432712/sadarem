/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.DataEnteredFieldsServiceDAO;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.service.DataEnteredFieldsService;

/**
 *
 * @author t_bapinaidu
 */
public class DataEnteredFieldsServiceImpl implements DataEnteredFieldsService{

    public Map<String,List<String>> getDataEnteredFieldsDetails(DataSource datasource,String personCode) throws SADAREMDBException,SQLException {

        Map<String,List<String>> dataEnteredListMap = null;
        DataEnteredFieldsServiceDAO dataEnteredFieldsServiceDAO = null;
        try{

            dataEnteredListMap = new LinkedHashMap<String,List<String>>();
            dataEnteredFieldsServiceDAO = new DataEnteredFieldsServiceDAO();
            dataEnteredListMap = dataEnteredFieldsServiceDAO.getDataEnteredFieldsDetails(datasource,personCode);
        }catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDataEnteredFieldsDetails", "DataEnteredFieldsServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DataEnteredFieldsServiceImpl", "getDataEnteredFieldsDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDataEnteredFieldsDetails", "DataEnteredFieldsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DataEnteredFieldsServiceImpl", "getDataEnteredFieldsDetails");
        }

        return dataEnteredListMap;
    }

 public Map<String,List<String>> getDataEnteredFieldsDetailsAU(DataSource datasource,String personCode) throws SADAREMDBException,SQLException {

        Map<String,List<String>> dataEnteredListMap = null;
        DataEnteredFieldsServiceDAO dataEnteredFieldsServiceDAO = null;
        try{

            dataEnteredListMap = new LinkedHashMap<String,List<String>>();
            dataEnteredFieldsServiceDAO = new DataEnteredFieldsServiceDAO();
            dataEnteredListMap = dataEnteredFieldsServiceDAO.getDataEnteredFieldsDetailsAU(datasource,personCode);
        }  catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDataEnteredFieldsDetailsAU", "DataEnteredFieldsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DataEnteredFieldsServiceImpl", "getDataEnteredFieldsDetailsAU");
        }

        return dataEnteredListMap;
    }



}
