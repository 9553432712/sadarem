/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.SearchForRationCardForm;

/**
 *
 * @author 525483
 */
public interface SearchForRationCardservice {

    public ArrayList getRationCardDetails(SearchForRationCardForm searchForRationCardForm, DataSource ds) throws SADAREMDBException, SQLException;
}
