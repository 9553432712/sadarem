/*
 * PersonalReportService.java
 *
 * Created on August 12, 2008, 4:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import java.sql.SQLException;
import org.bf.disability.dao.CommonPersonalReportDAO;
import org.bf.disability.dto.CommonReportDTO;

/**
 *
 * @author Sunima.dila
 */
public interface CommonPersonalReportService {

    /** Creates a new instance of PersonalReportService */
    /** Creates a new instance of ReportService */
    public ArrayList getPersonalReportDetails(DataSource datasource, CommonReportDTO surgeryreportdto, String surgerytype, int start, int endrange) throws SADAREMDBException,SQLException;

    public ArrayList getPersonalReportformandalDetails(DataSource datasource, CommonReportDTO surgeryreportdto, String surgerytype, int start, int endrange) throws SADAREMDBException,SQLException;

    public ArrayList getPersonalReportforvillageDetails(DataSource datasource, CommonReportDTO surgeryreportdto, String surgerytype, int start, int endrange) throws SADAREMDBException,SQLException;

    public ArrayList getPersonalReportforhabitationDetails(DataSource datasource, CommonReportDTO surgeryreportdto, String surgerytype, int start, int endrange) throws SADAREMDBException,SQLException;

    public ArrayList getNiramayaDistrictPersonalDetails(DataSource datasource, CommonReportDTO surgeryreportdto, int start, int endrange, String disabilityName) throws SADAREMDBException,SQLException;

    public ArrayList getNiramayaMandalPersonalDetails(DataSource datasource, CommonReportDTO surgeryreportdto, int start, int endrange, String disabilityName) throws SADAREMDBException,SQLException;

    public ArrayList getNiramayaVillagePersonalDetails(DataSource datasource, CommonReportDTO surgeryreportdto, int start, int endrange, String disabilityName) throws SADAREMDBException,SQLException;

    public ArrayList getNiramayaHabitationPersonalDetails(DataSource datasource, CommonReportDTO surgeryreportdto, int start, int endrange, String disabilityName) throws SADAREMDBException,SQLException;
}


