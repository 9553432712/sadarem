/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.MandalWiseClusterReportForm;

/**
 *
 * @author 695536
 */
public interface MandalWiseClusterReportService {

    public ArrayList getDistrictNames(DataSource ds, MandalWiseClusterReportForm pwdReportsForm) throws SADAREMDBException, SQLException;

    public ArrayList getMandalNames(DataSource ds, MandalWiseClusterReportForm pwdReportsForm) throws SADAREMDBException, SQLException;

    public ArrayList getVillageNames(DataSource ds, MandalWiseClusterReportForm pwdReportsForm) throws SADAREMDBException, SQLException;

    public ArrayList getPwdData(DataSource ds, MandalWiseClusterReportForm pwdReportsForm) throws SADAREMDBException, SQLException;

    public ArrayList getPwdData1(DataSource ds, MandalWiseClusterReportForm pwdReportsForm) throws SADAREMDBException, SQLException;

    public ArrayList getPwdData2(DataSource ds, MandalWiseClusterReportForm pwdReportsForm) throws SADAREMDBException, SQLException;
}
