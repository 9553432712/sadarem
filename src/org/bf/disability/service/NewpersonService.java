/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.NewPersonDetailsForm;

/**
 *
 * @author 695536
 */
public interface NewpersonService {

    public String getPhysicalImpairmentDetails(DataSource ds, NewPersonDetailsForm newPersonDetailsForm) throws SADAREMDBException, SQLException;

    public int updatedetails(DataSource ds, NewPersonDetailsForm newPersonDetailsForm, int requestId) throws SADAREMDBException, SQLException;
}
