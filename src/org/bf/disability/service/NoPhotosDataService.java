/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.NoPhotosDataForm;

/**
 *
 * @author 728056
 */
public interface NoPhotosDataService {

    public ArrayList getNophotosData(DataSource ds, NoPhotosDataForm photosDataForm) throws SADAREMDBException, SQLException;

     public ArrayList getCampDetails(DataSource ds, String districtId) throws SADAREMDBException, SQLException;
}
