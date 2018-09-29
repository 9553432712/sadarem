/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;

/**
 *
 * @author SADAREM
 */
public interface RationCardReportService {

    public ArrayList getMandals(DataSource datasource, String districtid) throws SADAREMDBException, SQLException;

    public ArrayList getVillageAll(DataSource datasource, String districtid, String mandalid) throws SADAREMDBException, SQLException;

    public ArrayList getHabitation(DataSource datasource, String districtid, String mandalid, String villageid) throws SADAREMDBException, SQLException;

    public ArrayList getRationPersonalReportDetails(DataSource ds, String district_id, String mandal_id, String village_id, String habitation_id, String personStatus) throws SADAREMDBException, SQLException;

    public ArrayList getRationCardMembersDetails(DataSource ds,DataSource civilDs, String rationCardNumber, HttpServletRequest request) throws SADAREMDBException, SQLException;
}
