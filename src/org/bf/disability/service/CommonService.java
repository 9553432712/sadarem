/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.dto.WebsiteCommonDTO;

/**
 *
 * @author 509865
 */
public interface CommonService {

    public String selectStatus(DataSource datasource, String personCode) throws SADAREMDBException, SQLException;

    public int updateStatus(DataSource datasource, String personCode, String status) throws SADAREMDBException, SQLException;

    public String checkPersonCodeForSearch(DataSource datasource, String personCode) throws SADAREMDBException, SQLException;

    public int getTotalPercentage(DataSource datasource, String personCode) throws SADAREMDBException, SQLException;

    public String getPersonStatus(DataSource datasource, String personCode) throws SADAREMDBException, SQLException;

    public String getDisabilityId(DataSource datasource, String personCode) throws SADAREMDBException, SQLException;

    public int insertFeedbackDetails(DataSource datasource, WebsiteCommonDTO feedbackdto, String localaddr) throws SADAREMDBException, SQLException;

    public List getFeedbackDetails(DataSource ds) throws SADAREMDBException, SQLException;

    public int updateFeedbackDetails(DataSource ds, String FeedbackIdsStatus, String selectedRowId) throws SADAREMDBException, SQLException;

    public WebsiteCommonDTO selectFeedbackDetails(DataSource ds, String rowId) throws SADAREMDBException, SQLException;

    public List getFeedback(DataSource ds) throws SADAREMDBException, SQLException;

    public boolean checkUploadPhoto(DataSource datasource, String personCode) throws SADAREMDBException, SQLException;

    public TerritoryDTO getDisabilityPercentages(DataSource datasource, String personCode, int disabilityId) throws SADAREMDBException, SQLException;

    public TerritoryDTO getDisabilityPercentagesAU(DataSource datasource, String personCode, int disabilityId) throws SADAREMDBException, SQLException;

    public ArrayList getcumulativeReport(DataSource ds) throws SADAREMDBException, SQLException;
}
