/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.ReportToCaptureDeadDetailsForm;

/**
 *
 * @author 310926
 */
public interface ReportToCaptureDeadDetailsService {

    public ArrayList validateSADAREMID(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException;

    public int insertDetails(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException;

    public int alreadyExist(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException ;

    public ArrayList getVoList(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException ;

    public ArrayList voNameshgMappingDetails(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException ;

    public ArrayList shgMappingName(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException ;

    public int getMappingCount(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException ;

    public ArrayList getShgDropdownDetails(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException ;

    public String getShgIdDetails(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException ;
}
