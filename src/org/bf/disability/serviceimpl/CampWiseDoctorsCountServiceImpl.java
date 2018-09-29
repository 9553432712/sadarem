/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.CampWiseDoctorsCountDAO;
import org.bf.disability.form.CampWiseDoctorsCountForm;
import org.bf.disability.service.CampWiseDoctorsCountService;

/**
 *
 * @author 693461
 */
public class CampWiseDoctorsCountServiceImpl implements CampWiseDoctorsCountService {

    public String getCampName(DataSource ds, int campId, String districtId) throws SADAREMDBException, SQLException {

        String campName = null;
        CampWiseDoctorsCountDAO campWiseDoctorsCountDAO = new CampWiseDoctorsCountDAO();


        try {

            campName = campWiseDoctorsCountDAO.getCampName(ds, campId, districtId);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return campName;

    }

    public ArrayList getCampBasedDisabilityDetails(DataSource ds, int campId, String date, String districtId) throws SADAREMDBException, SQLException {


        ArrayList campBasedDisabilityDetails = new ArrayList();
        CampWiseDoctorsCountDAO campWiseDoctorsCountDAO = new CampWiseDoctorsCountDAO();

        try {
            campBasedDisabilityDetails = campWiseDoctorsCountDAO.getCampBasedDisabilityDetails(ds, campId, date, districtId);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return campBasedDisabilityDetails;
    }


    /*  public ArrayList getCampBasedDoctorCountDetails(DataSource ds, String campDate, String disabilityId,String campBasedId) throws SADAREMDBException, SQLException{


    ArrayList campBasedDoctorList = new ArrayList();
    CampWiseDoctorsCountDAO campWiseDoctorsCountDAO = new CampWiseDoctorsCountDAO();

    try {
    campBasedDoctorList = campWiseDoctorsCountDAO.getCampBasedDoctorCountDetails(ds, campDate,disabilityId,campBasedId);

    } catch (Exception e) {
    e.printStackTrace();
    }

    return campBasedDoctorList;
    }*/
    public int insertPwdDoctorCountDetails(DataSource ds, String campDate, int campId, String districtId, String disabilityId, String assessedPwdCount, String docRegNo, String loginId, String SystemIp) throws SADAREMDBException, SQLException {

        int pwdDoctorCountDetails = 0;
        CampWiseDoctorsCountDAO campWiseDoctorsCountDAO = new CampWiseDoctorsCountDAO();

        try {
            pwdDoctorCountDetails = campWiseDoctorsCountDAO.insertPwdDoctorCountDetails(ds, campDate, campId, districtId, disabilityId, assessedPwdCount, docRegNo, loginId, SystemIp);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pwdDoctorCountDetails;


    }

    public boolean EmailBasedOnDisabilityDoctorsCount(DataSource ds, ArrayList doctorCount) throws SADAREMDBException, SQLException {

        boolean msg = false;
        CampWiseDoctorsCountDAO campWiseDoctorsCountDAO = new CampWiseDoctorsCountDAO();


        try {
            // msg = campWiseDoctorsCountDAO.EmailBasedOnDisabilityDoctorsCount(ds, doctorCount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    public String getCertificateCountDetails(DataSource ds, int campId, String district, String date) throws SADAREMDBException, SQLException {

        String certificateCountDetails = null;
        CampWiseDoctorsCountDAO campWiseDoctorsCountDAO = new CampWiseDoctorsCountDAO();

        try {
            certificateCountDetails = campWiseDoctorsCountDAO.getCertificateCountDetails(ds, campId, district, date);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return certificateCountDetails;

    }

    public String getPartAEnteredCountDetails(DataSource ds, int campId, String district, String date) throws SADAREMDBException, SQLException {

        String partAEnteredCountDetails = null;
        CampWiseDoctorsCountDAO campWiseDoctorsCountDAO = new CampWiseDoctorsCountDAO();

        try {
            partAEnteredCountDetails = campWiseDoctorsCountDAO.getPartAEnteredCountDetails(ds, campId, district, date);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return partAEnteredCountDetails;
    }

    public ArrayList getCampBasedDisabilityDetailsForMail(DataSource ds, int campId, String district, String date) throws SADAREMDBException, SQLException {
        ArrayList campBasedDisabilityDetailsForMail = null;
        CampWiseDoctorsCountDAO campWiseDoctorsCountDAO = new CampWiseDoctorsCountDAO();

        try {
            campBasedDisabilityDetailsForMail = campWiseDoctorsCountDAO.getCampBasedDisabilityDetailsForMail(ds, campId, district, date);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return campBasedDisabilityDetailsForMail;
    }

    public ArrayList getTypeofDisability(DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList list = null;
        CampWiseDoctorsCountDAO campWiseDoctorsCountDAO = new CampWiseDoctorsCountDAO();
        try {
            list = campWiseDoctorsCountDAO.getTypeofDisability(ds);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList getDocDetails(DataSource ds,int campId, String disabilityid, String docReg) throws SADAREMDBException, SQLException{
        ArrayList list = null;
        CampWiseDoctorsCountDAO campWiseDoctorsCountDAO = new CampWiseDoctorsCountDAO();
        try {
            list = campWiseDoctorsCountDAO.getDocDetails(ds,campId,disabilityid,docReg);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int insertDoctor(DataSource ds,String loginid, int campid, String disabilityid, CampWiseDoctorsCountForm formBean) throws SADAREMDBException, SQLException{
        int success =  0;
        CampWiseDoctorsCountDAO campWiseDoctorsCountDAO = new CampWiseDoctorsCountDAO();
        try {
            success = campWiseDoctorsCountDAO.insertDoctor(ds,loginid, campid, disabilityid, formBean);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    public int insertDocotorCount(DataSource ds,String loginid, int campid, String districtid, CampWiseDoctorsCountForm formBean) throws SADAREMDBException, SQLException{
        int success =  0;
        CampWiseDoctorsCountDAO campWiseDoctorsCountDAO = new CampWiseDoctorsCountDAO();
        try {
            success = campWiseDoctorsCountDAO.insertDocotorCount(ds,loginid, campid, districtid, formBean);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
    public ArrayList getCampWiseDoctorsAssCountDetails(DataSource ds,int campId, String date, String disabilityid) throws SADAREMDBException, SQLException{
        ArrayList list = null;
        CampWiseDoctorsCountDAO campWiseDoctorsCountDAO = new CampWiseDoctorsCountDAO();
        try {
            list = campWiseDoctorsCountDAO.getCampWiseDoctorsAssCountDetails(ds,campId, date, disabilityid);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public int checkDoctorCount(DataSource ds,int campId, String date, String disabilityid) throws SADAREMDBException, SQLException{
        int count=0;
        CampWiseDoctorsCountDAO campWiseDoctorsCountDAO = new CampWiseDoctorsCountDAO();
        try {
            count = campWiseDoctorsCountDAO.checkDoctorCount(ds,campId, date, disabilityid);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    public String getDisabilityName(DataSource ds,String disabilityid) throws SADAREMDBException, SQLException{
        String disabilityName = null;
        CampWiseDoctorsCountDAO campWiseDoctorsCountDAO = new CampWiseDoctorsCountDAO();
        try {
            disabilityName = campWiseDoctorsCountDAO.getDisabilityName(ds,disabilityid);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return disabilityName;
    }

    public int checkDoctorWithRegistrationNo(DataSource ds, int campId, String disabilityid, String docreg) {
        int count=0;
        CampWiseDoctorsCountDAO campWiseDoctorsCountDAO = new CampWiseDoctorsCountDAO();
        try {
            count = campWiseDoctorsCountDAO.checkDoctorWithRegistrationNo(ds,campId, disabilityid, docreg);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
