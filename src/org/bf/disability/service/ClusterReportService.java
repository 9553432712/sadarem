/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.util.ArrayList;
import org.bf.disability.form.ClusterReportForm;
import org.bf.disability.Exception.SADAREMDBException;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author 747577
 */
public interface ClusterReportService {

    public ArrayList getReportTypes(DataSource ds) throws SADAREMDBException, SQLException;

    public ArrayList getDistricts(DataSource ds) throws SADAREMDBException, SQLException;

    public ArrayList getClusters(DataSource ds, String districtName) throws SADAREMDBException, SQLException;

    public ArrayList getMandals(DataSource ds, String clusterName) throws SADAREMDBException, SQLException;

//    public ArrayList getClusterReport(DataSource ds, ClusterReportForm clusterReportForm, String reportType) throws SADAREMDBException, SQLException;
//
//    public ArrayList getDistrictClusterReport(DataSource ds, ClusterReportForm clusterReportForm, String reportType) throws SADAREMDBException, SQLException;
//
//    public ArrayList getClusterConfirmationNoReport(DataSource ds, ClusterReportForm clusterReportForm, String reportType) throws SADAREMDBException, SQLException;
    public ArrayList getStateClusterReportList(DataSource ds, ClusterReportForm clusterReportForm) throws SADAREMDBException, SQLException;

    public ArrayList getStateWiseDistrictBreakup(DataSource ds, String reportId, int columnsCount) throws SADAREMDBException, SQLException;

    public int getStateWiseDistrictColumnCount(DataSource ds, String reportType) throws SADAREMDBException, SQLException;

    public ArrayList getStateWiseDistrictColumnNames(DataSource ds, String reportType,ClusterReportForm clusterReportForm) throws SADAREMDBException, SQLException;

    public ArrayList districtClusterReportList(DataSource ds, ClusterReportForm clusterReportForm);

    public ArrayList getBreakupList(DataSource ds, String reportId, int columnsCount, ClusterReportForm clusterReportForm);
}
