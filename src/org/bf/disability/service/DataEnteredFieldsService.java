/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;

/**
 *
 * @author t_bapinaidu
 */
public interface DataEnteredFieldsService {

    public Map<String, List<String>> getDataEnteredFieldsDetails(DataSource datasource, String personCode) throws SADAREMDBException, SQLException;

    public Map<String, List<String>> getDataEnteredFieldsDetailsAU(DataSource datasource, String personCode) throws SADAREMDBException, SQLException;
}
