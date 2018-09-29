package org.bf.disability.service;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.HearingImpairmentDto;
import javax.servlet.http.HttpServletRequest;
import org.bf.disability.form.HearingImpairmentActionForm;

/**
 * 
 * @author Sunima
 */
public interface HearingImpairmentService {

    /**
     * Creates a new instance of HearingImpairmentService
     */
    public int insertData(DataSource ds, HearingImpairmentDto hearingdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertDataAU(DataSource ds, HearingImpairmentDto hearingdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public HearingImpairmentDto getHearingDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    //  public String getHearingPercent(DataSource datasource,String personcode)throws SADAREMDBException,SQLException;
    public void updateDetails(DataSource datasource, HearingImpairmentActionForm hearingform, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public boolean checkPersoncode(String personcode, DataSource ds) throws SADAREMDBException, SQLException;
}
