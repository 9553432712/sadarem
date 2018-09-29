/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.NoPhotosDataDAO;
import org.bf.disability.form.NoPhotosDataForm;
import org.bf.disability.service.NoPhotosDataService;

/**
 *
 * @author 728056
 */
public class NoPhotosDataServiceImpl implements NoPhotosDataService {

    public ArrayList getNophotosData(DataSource ds, NoPhotosDataForm photosDataForm) throws SADAREMDBException,SQLException {
        ArrayList photo = new ArrayList();
        NoPhotosDataDAO npddao = new NoPhotosDataDAO();
        try {
            photo = npddao.getNophotosData(ds, photosDataForm);
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getNophotosData", "NoPhotosDataServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NoPhotosDataServiceImpl", "getNophotosData");
        }
        return photo;

    }
       public ArrayList getCampDetails(DataSource ds ,String district_id) throws SADAREMDBException,SQLException {
        ArrayList photo = new ArrayList();
        NoPhotosDataDAO npddao = new NoPhotosDataDAO();
        try {
            photo = npddao.getCampDetails(ds, district_id);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCampDetails", "NoPhotosDataServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NoPhotosDataServiceImpl", "getCampDetails");
        }
        return photo;

    }
}
