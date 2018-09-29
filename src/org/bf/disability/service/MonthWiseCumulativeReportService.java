/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;

/**
 *
 * @author 747577
 */
public interface MonthWiseCumulativeReportService {

    public ArrayList getDistricts(DataSource datasource) throws SADAREMDBException, SQLException;

    public ArrayList getMandals(DataSource datasource, String distId) throws SADAREMDBException, SQLException;

    public ArrayList getVillages(DataSource datasource, String distId, String mandalId, String panchayatId) throws SADAREMDBException, SQLException;

    public ArrayList getHabitations(DataSource datasource, String distId, String mandalId, String panchayatId, String villageId) throws SADAREMDBException, SQLException;

    public ArrayList getReportMonthWise(DataSource datasource, String fromDate, String toDate, String district, String mandal, String village) throws SADAREMDBException, SQLException, ParseException;
}
