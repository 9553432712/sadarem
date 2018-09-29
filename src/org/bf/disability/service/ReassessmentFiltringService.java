/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import java.sql.SQLException;

/**
 *
 * @author 484898
 */
public interface ReassessmentFiltringService {

    public ArrayList getReassessmentDetails(DataSource ds, String districtId, String mandalId, String villageId, String habitationId) throws SADAREMDBException, SQLException;

    public int updateFlagForPersons(DataSource ds, String personIds) throws SADAREMDBException, SQLException;

    public ArrayList getHabitations(DataSource datasource, String districtid, String mandalid, String villageid) throws SADAREMDBException, SQLException;
}
