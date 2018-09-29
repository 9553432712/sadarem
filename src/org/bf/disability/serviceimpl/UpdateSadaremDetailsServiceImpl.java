/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.UpdateSadaremDetailsDAO;
import org.bf.disability.form.UpdateSadaremDetailsForm;
import org.bf.disability.service.UpdateSadaremDetailsService;

/**
 *
 * @author 728056
 */
public class UpdateSadaremDetailsServiceImpl implements UpdateSadaremDetailsService {

    public ArrayList getDetails(DataSource ds, HttpSession session) throws SADAREMDBException,SQLException {
        ArrayList data = new ArrayList();
        UpdateSadaremDetailsDAO detailsDAO = new UpdateSadaremDetailsDAO();
        try {
            data = detailsDAO.getDetails(ds, session);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public boolean insertData(DataSource ds, UpdateSadaremDetailsForm detailsForm, HttpSession session) throws SADAREMDBException,SQLException {
        boolean data = false;
        UpdateSadaremDetailsDAO detailsDAO = new UpdateSadaremDetailsDAO();
        try {
            data = detailsDAO.insertData(ds, detailsForm, session);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;

    }

//    public ArrayList getPendingDetails(DataSource ds, HttpSession session) throws SADAREMDBException,SQLException {
//        ArrayList data1 = new ArrayList();
//        UpdateSadaremDetailsDAO detailsDAO = new UpdateSadaremDetailsDAO();
//        try {
//            data1 = detailsDAO.getPendingDetails(ds, session);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return data1;
//    }
}
