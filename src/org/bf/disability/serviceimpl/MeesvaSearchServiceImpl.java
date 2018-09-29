/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.MeesvaSearchDAO;
import org.bf.disability.dto.MeesevaSearchDTO;
import org.bf.disability.form.MeesvaSearchForm;
import org.bf.disability.service.MeesvaSearchService;

/**
 *
 * @author 693461
 */
public class MeesvaSearchServiceImpl implements MeesvaSearchService {

    public String getSadaremIdDetails(DataSource ds, MeesvaSearchForm meesvaSearchForm) throws SADAREMDBException, SQLException {

        String flagStatus = null;
        MeesvaSearchDAO meesvaSearchDAO = new MeesvaSearchDAO();
        try {
            flagStatus = meesvaSearchDAO.getSadaremIdDetails(ds, meesvaSearchForm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flagStatus;
    }

    public ArrayList getStatusCertificateType(DataSource ds, MeesvaSearchForm meesvaSearchForm) throws SADAREMDBException, SQLException {
        ArrayList statusCertificateType = null;
        MeesvaSearchDAO meesvaSearchDAO = new MeesvaSearchDAO();

        try {
            //statusCertificateType = meesvaSearchDAO.getStatusCertificateType(ds, meesvaSearchForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusCertificateType;
    }

    public ArrayList getCertificateValidDetails(DataSource ds, MeesvaSearchForm meesvaSearchForm) throws SADAREMDBException, SQLException {
        MeesvaSearchDAO meesvaSearchDAO = new MeesvaSearchDAO();
        ArrayList certificateValidDetails = null;

        try {
            certificateValidDetails = meesvaSearchDAO.getCertificateValidDetails(ds, meesvaSearchForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certificateValidDetails;

    }

    public MeesevaSearchDTO getRequestDetails(DataSource ds, String applicationNo) throws SADAREMDBException, SQLException {
        MeesvaSearchDAO meesvaSearchDAO = new MeesvaSearchDAO();
        MeesevaSearchDTO meesevaSearchDTO = null;

        try {
            meesevaSearchDTO = meesvaSearchDAO.getRequestDetails(ds, applicationNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return meesevaSearchDTO;
    }

    public int insertSearchMeesevaSearchDetails(DataSource ds, MeesvaSearchForm meesvaSearchForm) throws SADAREMDBException, SQLException {
        int insertSearchMeesevaSearchDetails = 0;
        MeesvaSearchDAO meesvaSearchDAO = new MeesvaSearchDAO();
        try {
            insertSearchMeesevaSearchDetails = meesvaSearchDAO.insertSearchMeesevaSearchDetails(ds, meesvaSearchForm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertSearchMeesevaSearchDetails;
    }

    public MeesevaSearchDTO getSerachAcknowledgementDetails(DataSource ds, MeesvaSearchForm meesvaSearchForm) throws SADAREMDBException, SQLException {

        MeesevaSearchDTO meesevaSearchDTO = new MeesevaSearchDTO();
        MeesvaSearchDAO meesvaSearchDAO = new MeesvaSearchDAO();
        try {
            meesevaSearchDTO = meesvaSearchDAO.getSerachAcknowledgementDetails(ds, meesvaSearchForm);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return meesevaSearchDTO;
    }

    public int updateSearchMEESEVATransctionId(DataSource ds, String applicationId, String meesevaId) throws SADAREMDBException, SQLException {
        int updateSearch = 0;
        MeesvaSearchDAO meesvaSearchDAO = new MeesvaSearchDAO();
        try {
            updateSearch = meesvaSearchDAO.updateSearchMEESEVATransctionId(ds, applicationId, meesevaId);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return updateSearch;

    }

    public ArrayList getSADAREMIDPercentageDetails(DataSource ds, MeesvaSearchForm meesvaSearchForm, String sadaremId) throws SADAREMDBException, SQLException {
        ArrayList percentageDetails = null;
        MeesvaSearchDAO meesvaSearchDAO = new MeesvaSearchDAO();
        try {
            percentageDetails = meesvaSearchDAO.getSADAREMIDPercentageDetails(ds, meesvaSearchForm, sadaremId);

        } catch (Exception e) {
         e.printStackTrace();
        }
        return percentageDetails;
    }

    public int getPrintMeesevaSearchDetails(DataSource ds, MeesvaSearchForm meesvaSearchForm) throws SADAREMDBException, SQLException {
        int printMeesevaDetails=0;
         MeesvaSearchDAO meesvaSearchDAO = new MeesvaSearchDAO();
        try {
            printMeesevaDetails = meesvaSearchDAO.getprintMeesevaSearchDetails(ds, meesvaSearchForm);

        } catch (Exception e) {
         e.printStackTrace();
        }
         return  printMeesevaDetails;
    }
}
