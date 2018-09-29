/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.FilterCandidateDAO;
import org.bf.disability.service.FilterCandidateService;

/**
 *
 * @author vijay
 */
public class FilterCandidateServiceImpl implements FilterCandidateService {

    /*  public ArrayList getDistrictDetails(DataSource ds) throws SADAREMDBException,SQLException {

    FilterCandidateDAO filterCandidateDAO = new FilterCandidateDAO();

    ArrayList districtList = new ArrayList();

    try {

    districtList = filterCandidateDAO.getDistrictDetails(ds);

    } catch (Exception e) {

    e.printStackTrace();
    }

    return districtList;


    } */
    public ArrayList getMandalDetails(DataSource ds, String district_id) throws SADAREMDBException, SQLException {


        FilterCandidateDAO filterCandidateDAO = new FilterCandidateDAO();

        ArrayList mandalList = new ArrayList();

        try {

            mandalList = filterCandidateDAO.getMandalDetails(ds, district_id);



        } catch (Exception e) {

            e.printStackTrace();
        }

        return mandalList;


    }

    public ArrayList getVillageDetails(DataSource ds, String district_id, String mandal_id) throws SADAREMDBException, SQLException {

        ArrayList villageList = new ArrayList();
        FilterCandidateDAO filterCandidateDAO = new FilterCandidateDAO();


        try {
            villageList = filterCandidateDAO.getVillageDetails(ds, district_id, mandal_id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return villageList;
    }

    public ArrayList getReportRationCardDuplicateDetails(DataSource ds, String district_id, String mandal_id, String village_id) throws SADAREMDBException, SQLException {

        ArrayList duplicateRationCardDetails = new ArrayList();

        FilterCandidateDAO filterCandidateDAO = new FilterCandidateDAO();

        try {

            duplicateRationCardDetails = filterCandidateDAO.getReportRationCardDuplicateDetails(ds, district_id, mandal_id, village_id);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return duplicateRationCardDetails;
    }

    public int getUpdateDuplicateDetails(DataSource ds, String pensionCard, String duplicatePersonCode, String districtid) throws SADAREMDBException, SQLException {

        int updateDuplicateDetails = 0;

        FilterCandidateDAO filterCandidateDAO = new FilterCandidateDAO();

        try {
            updateDuplicateDetails = filterCandidateDAO.getUpdateDuplicateDetails(ds, pensionCard, duplicatePersonCode, districtid);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return updateDuplicateDetails;

    }

    public String getDistrictNameDetails(DataSource ds, String district_id) throws SADAREMDBException, SQLException {

        String districtName = null;
        FilterCandidateDAO filterCandidateDAO = new FilterCandidateDAO();

        try {
            districtName = filterCandidateDAO.getDistrictNameDetails(ds, district_id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return districtName;
    }
}
