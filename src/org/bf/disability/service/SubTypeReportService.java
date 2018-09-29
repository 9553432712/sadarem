/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.SubTypeReportForm;

/**
 *
 * @author 747577
 */
public interface SubTypeReportService {

    public ArrayList getDistricts(DataSource ds) throws SADAREMDBException, SQLException;

    public ArrayList getMandals(DataSource ds, String distId) throws SADAREMDBException, SQLException;

    public ArrayList getVillages(DataSource ds, String distId, String mandalId, String panchayatId) throws SADAREMDBException, SQLException;

    public ArrayList getHabitations(DataSource ds, String distId, String mandalId, String panchayatId, String villageId) throws SADAREMDBException, SQLException;

    public ArrayList getSubTypeReportDetails(DataSource ds,SubTypeReportForm subTypeReportForm) throws SADAREMDBException, SQLException;

    public ArrayList gettypeOfDisability(DataSource ds) throws SADAREMDBException, SQLException;

    public ArrayList getSubtypeOfDisability(DataSource ds,String disabilityId) throws SADAREMDBException, SQLException;

    public ArrayList getAddressDetails(DataSource ds,SubTypeReportForm subTypeReportForm) throws SADAREMDBException, SQLException;

    public String getMandalName(DataSource ds,SubTypeReportForm subTypeReportForm) throws SADAREMDBException, SQLException;

    public String getvillageName(DataSource ds,SubTypeReportForm subTypeReportForm) throws SADAREMDBException, SQLException;

    public String gethabitationName(DataSource ds,SubTypeReportForm subTypeReportForm) throws SADAREMDBException, SQLException;

    public String getdisabulityName(DataSource ds,SubTypeReportForm subTypeReportForm) throws SADAREMDBException, SQLException;

    public String getsubdisabulityName(DataSource ds,SubTypeReportForm subTypeReportForm) throws SADAREMDBException, SQLException;
}
