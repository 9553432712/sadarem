/*
 * SurgeryReportService.java
 *
 * Created on August 4, 2008, 6:15 PM
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.CommonReportDTO;

/**
 *
 * @author ragahavendra_t
 * @version
 */
public interface CommonReportService {

    public ArrayList getDistrictReport(DataSource ds,
            CommonReportDTO surgeryreportdto, String Surgerytype)
            throws SADAREMDBException, SQLException;

    public ArrayList getMandalReport(DataSource datasource, CommonReportDTO surgeryreportdto,
            String Surgerytype) throws SADAREMDBException, SQLException;

    public ArrayList getVillageReport(DataSource datasource,
            CommonReportDTO commonreportdto, String surgerytype)
            throws SADAREMDBException, SQLException;

    public ArrayList getHabitationReport(DataSource datasource,
            CommonReportDTO commonreportdto, String surgerytype)
            throws SADAREMDBException, SQLException;

    public ArrayList getNiramayaDistrictReport(DataSource datasource)
            throws SADAREMDBException, SQLException;

    public ArrayList getNiramayaMandalReport(DataSource datasource, String District_Id)
            throws SADAREMDBException, SQLException;

    public ArrayList getNiramayaVillageReport(DataSource datasource, String District_Id, String mandalId)
            throws SADAREMDBException, SQLException;

    public ArrayList getNiramayaHabitationReport(DataSource datasource, String District_Id, String mandalId, String villageId)
            throws SADAREMDBException, SQLException;
}

