/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;

/**
 *
 * @author vijay
 */
public interface FilterCandidateService {

// public ArrayList getDistrictDetails(DataSource ds) throws SADAREMDBException,SQLException;
    public ArrayList getMandalDetails(DataSource ds, String district_id) throws SADAREMDBException,SQLException;

    public ArrayList getVillageDetails(DataSource ds, String district_id, String mandal_id) throws SADAREMDBException,SQLException;

    public ArrayList getReportRationCardDuplicateDetails(DataSource ds, String district_id, String mandal_id, String village_id) throws SADAREMDBException,SQLException;

    public int getUpdateDuplicateDetails(DataSource ds, String pensionCard, String duplicatepensioncode, String districtid) throws SADAREMDBException,SQLException;

    public String getDistrictNameDetails(DataSource ds, String district_id) throws SADAREMDBException,SQLException;

 
}
