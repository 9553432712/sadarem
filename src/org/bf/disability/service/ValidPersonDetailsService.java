/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.ValidPersonDetailsForm;

/**
 *
 * @author 728056
 */
public interface ValidPersonDetailsService {

    public boolean checkDetails(DataSource ds, ValidPersonDetailsForm detailsForm) throws SADAREMDBException, SQLException;

    public int updateDetails(DataSource ds, ValidPersonDetailsForm detailsForm) throws SADAREMDBException, SQLException;
}
