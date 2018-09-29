/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.MeesevaSearchDTO;
import org.bf.disability.form.MeesvaSearchForm;

/**
 *
 * @author 693461
 */
public interface MeesvaSearchService {

    public String getSadaremIdDetails(DataSource ds, MeesvaSearchForm meesvaSearchForm) throws SADAREMDBException, SQLException;

    public ArrayList getStatusCertificateType(DataSource ds, MeesvaSearchForm meesvaSearchForm) throws SADAREMDBException, SQLException;

    public ArrayList getCertificateValidDetails(DataSource ds, MeesvaSearchForm meesvaSearchForm) throws SADAREMDBException, SQLException;

    public MeesevaSearchDTO getRequestDetails(DataSource ds, String applicationNo) throws SADAREMDBException, SQLException;

    public int insertSearchMeesevaSearchDetails(DataSource ds, MeesvaSearchForm meesvaSearchForm) throws SADAREMDBException, SQLException;

    public MeesevaSearchDTO getSerachAcknowledgementDetails(DataSource ds, MeesvaSearchForm meesvaSearchForm) throws SADAREMDBException, SQLException;

    public int updateSearchMEESEVATransctionId(DataSource ds, String applicationId, String meesevaId) throws SADAREMDBException, SQLException;

    public ArrayList getSADAREMIDPercentageDetails(DataSource ds, MeesvaSearchForm meesvaSearchForm, String sadaremId) throws SADAREMDBException, SQLException;
    
    public int getPrintMeesevaSearchDetails(DataSource ds, MeesvaSearchForm meesvaSearchForm) throws SADAREMDBException, SQLException;




    
}
