/*
 * SurgeryReportService.java
 *
 * Created on August 4, 2008, 6:15 PM
 */

package org.bf.disability.service;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.sql.DataSource;
import java.io.*;
import java.sql.SQLException;
import javax.sql.*;
import javax.servlet.http.*;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.dao.DailyDisabilityAndPercentageDAO;
import org.bf.disability.dto.DailyDisabilityAndPercentageDTO;
import org.bf.disability.form.CommonReportForm;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.AssessedPWDDetailsDTO;
import org.bf.disability.dto.PartADTO;

/**
 * This interface contains the abstract methods helpful in generating reports
 * on disability wide and disability count wide
 * @author Raghavendra
 * @version 1.0
 */
public interface DailyDisabilityAndPercentageService  {
    public ArrayList getDisabilityWise(DataSource ds,
            DailyDisabilityAndPercentageDTO dailyreportdto)
            throws SADAREMDBException,SQLException;
    public ArrayList getDisabilityPercentage(DataSource ds,
            DailyDisabilityAndPercentageDTO dailyreportdto)
            throws SADAREMDBException,SQLException;
    public ArrayList getPersonPersonalDetailswise(DataSource ds,
            PartADTO partADTO,String districtid)
            throws SADAREMDBException,SQLException;
    public ArrayList getReportForLoginWiseCount(DataSource ds,
            PartADTO partADTO,String districtid)
            throws SADAREMDBException,SQLException;
    public ArrayList getStatusCountForMandal(DataSource ds,
            PartADTO partADTO,String districtid)
            throws SADAREMDBException,SQLException;
    public ArrayList getStatusCountForVillage(DataSource ds,
            PartADTO partADTO,String districtid,String mandalid)
            throws SADAREMDBException,SQLException;
    public ArrayList getStatusCountForPartA(DataSource ds,
            PartADTO partADTO,String districtid)
            throws SADAREMDBException,SQLException;
    public ArrayList distWisestatusreportforPartA(DataSource ds,
            PartADTO partADTO,String districtid)
            throws SADAREMDBException,SQLException;
    public ArrayList distWisestatusreportforPartB(DataSource ds,
            PartADTO partADTO,String districtid)
            throws SADAREMDBException,SQLException;

    public ArrayList mandalWisestatusreportforPartB(DataSource ds,
            PartADTO partADTO,String districtid)
            throws SADAREMDBException,SQLException;
    public ArrayList villageWiseStatusReportForPartB(DataSource ds,
            PartADTO partADTO,String districtid,String mandalid)
            throws SADAREMDBException,SQLException;

    public ArrayList statusreportforPartB(DataSource ds,
            PartADTO partADTO) throws SADAREMDBException,SQLException;

    public ArrayList statusReportforPartBAssessed(DataSource ds,
            String district_id,String mandal_id,String village_id,String phase,String header) throws SADAREMDBException,SQLException;

    public ArrayList statusreportforPartBModified(DataSource ds, AssessedPWDDetailsDTO assessedPWDDetailsDTO)throws SADAREMDBException,SQLException;

    public ArrayList setDistrictList(DataSource ds, AssessedPWDDetailsDTO assessedPWDDetailsDTO)throws SADAREMDBException,SQLException;

    public ArrayList setMandalList(DataSource ds, AssessedPWDDetailsDTO assessedPWDDetailsDTO)throws SADAREMDBException,SQLException;

    public ArrayList setVillageList(DataSource ds, AssessedPWDDetailsDTO assessedPWDDetailsDTO)throws SADAREMDBException,SQLException;

    public ArrayList setPanchayatList(DataSource ds, AssessedPWDDetailsDTO assessedPWDDetailsDTO)throws SADAREMDBException,SQLException;

    public ArrayList getAllDataByMandal(DataSource ds,String districtId, String mandalId)throws SADAREMDBException, SQLException;
    //kavya
    

}

