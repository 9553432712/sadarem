/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.SearchRequestStatusDTO;

/**
 *
 * @author 310926
 */
public interface SearchRequestStatusService {

    public ArrayList getDetails(DataSource ds, SearchRequestStatusDTO searchRequestStatusDTO) throws SADAREMDBException, SQLException;
}
