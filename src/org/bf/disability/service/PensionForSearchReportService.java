/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;

/**
 *
 * @author 484898
 */
public interface PensionForSearchReportService {

    public ArrayList getMandals(DataSource datasource, String districtid) throws SADAREMDBException, SQLException;

    public ArrayList getVillageAll(DataSource datasource, String districtid, String mandalid) throws SADAREMDBException, SQLException;

    public ArrayList getPhaseSearchDetails(DataSource datasource, String pensionCode, String sadaremId, String districtId, String mandalId, String villageId) throws SADAREMDBException, SQLException;
}
