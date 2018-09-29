/*
 * PersonalReportService.java
 *
 * Created on August 12, 2008, 4:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.PersonalReportDAO;
import org.bf.disability.dto.CommonReportDTO;

/**
 *
 * @author Sunima.dila
 */
public class PersonalReportService {

    /** Creates a new instance of PersonalReportService */
    /** Creates a new instance of ReportService */
    public ArrayList getPersonalReportDetails(DataSource datasource,
            CommonReportDTO surgeryreportdto, String surgerytype)
            throws SADAREMDBException, SQLException {
        PersonalReportDAO reportdao = new PersonalReportDAO();

        return reportdao.getPersonalReportDetails(datasource, surgeryreportdto,
                surgerytype);

    }

    public ArrayList getPersonalReportformandalDetails(DataSource datasource,
            CommonReportDTO surgeryreportdto, String surgerytype) throws SADAREMDBException, SQLException {
        PersonalReportDAO reportdao = new PersonalReportDAO();


        return reportdao.getPersonalReportformandalDetails(datasource,
                surgeryreportdto, surgerytype);

    }

    public ArrayList getPersonalReportforvillageDetails(DataSource datasource,
            CommonReportDTO surgeryreportdto, String surgerytype)
            throws SADAREMDBException, SQLException {
        PersonalReportDAO reportdao = new PersonalReportDAO();


        return reportdao.getPersonalReportforvillageDetails(datasource,
                surgeryreportdto, surgerytype);

    }

    public ArrayList getPersonalReportforhabitationDetails(DataSource datasource, CommonReportDTO surgeryreportdto,
            String surgerytype) throws SADAREMDBException, SQLException {
        PersonalReportDAO reportdao = new PersonalReportDAO();


        return reportdao.getPersonalReportforhabitationDetails(datasource,
                surgeryreportdto, surgerytype);

    }
}
