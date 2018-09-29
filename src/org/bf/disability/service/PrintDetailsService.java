/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.PrintDetailsResultForm;

/**
 *
 * @author 747577
 */
public interface PrintDetailsService {

    public ArrayList getCampDetails(DataSource ds,String district_id) throws SADAREMDBException, SQLException;

    public ArrayList getFullCampDetails(DataSource ds,PrintDetailsResultForm printDetailsResultForm) throws SADAREMDBException, SQLException;

    public void insertCertificatePrintDetails(DataSource ds,String sId, String cId, String cType, String lId, String campID, String distId) throws SADAREMDBException, SQLException,Exception;

    public ArrayList getPrintCertificateDetails(DataSource ds,PrintDetailsResultForm printDetailsResultForm) throws SADAREMDBException, SQLException;

    public ArrayList getCampCountDetails(DataSource ds,PrintDetailsResultForm printDetailsResultForm) throws SADAREMDBException, SQLException;
}
