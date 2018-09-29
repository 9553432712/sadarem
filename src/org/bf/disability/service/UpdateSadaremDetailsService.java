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
import org.bf.disability.form.UpdateSadaremDetailsForm;

/**
 *
 * @author 728056
 */
public interface UpdateSadaremDetailsService {

    public ArrayList getDetails(DataSource ds, HttpSession session) throws SADAREMDBException, SQLException;

    public boolean insertData(DataSource ds, UpdateSadaremDetailsForm detailsForm, HttpSession session) throws SADAREMDBException, SQLException;
//    public ArrayList getPendingDetails(DataSource ds, HttpSession session) throws SADAREMDBException,SQLException;
}
