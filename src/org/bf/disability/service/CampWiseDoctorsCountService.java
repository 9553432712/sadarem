/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.CampWiseDoctorsCountForm;

/**
 *
 * @author 693461
 */
public interface CampWiseDoctorsCountService {

    public String getCampName(DataSource ds, int campId, String districtId) throws SADAREMDBException, SQLException;

    public ArrayList getCampBasedDisabilityDetails(DataSource ds, int campId, String date, String districtId) throws SADAREMDBException, SQLException;

//  public ArrayList getCampBasedDoctorCountDetails(DataSource ds,String campDate, String disabilityId,String campBasedId) throws SADAREMDBException, SQLException;
    public int insertPwdDoctorCountDetails(DataSource ds, String campDate, int campId, String districtId, String disabilityId, String assessedPwdCount, String docRegNo, String loginId, String SystemIp) throws SADAREMDBException, SQLException;

    public boolean EmailBasedOnDisabilityDoctorsCount(DataSource ds, ArrayList doctorCount) throws SADAREMDBException, SQLException;

    public String getCertificateCountDetails(DataSource ds, int campId, String district, String date) throws SADAREMDBException, SQLException;

    public String getPartAEnteredCountDetails(DataSource ds, int campId, String district, String date) throws SADAREMDBException, SQLException;

    public ArrayList getCampBasedDisabilityDetailsForMail(DataSource ds, int campId, String district, String date) throws SADAREMDBException, SQLException;

    //balu code

    public ArrayList getTypeofDisability(DataSource ds) throws SADAREMDBException, SQLException;

    public ArrayList getDocDetails(DataSource ds,int campId, String disabilityid, String docReg) throws SADAREMDBException, SQLException;

    public int insertDoctor(DataSource ds,String loginid, int campid, String disabilityid, CampWiseDoctorsCountForm formBean) throws SADAREMDBException, SQLException;

    public int insertDocotorCount(DataSource ds,String loginid, int campid, String districtid, CampWiseDoctorsCountForm formBean) throws SADAREMDBException, SQLException;

    public ArrayList getCampWiseDoctorsAssCountDetails(DataSource ds,int campId, String date, String disabilityid) throws SADAREMDBException, SQLException;

    public int checkDoctorCount(DataSource ds,int campId, String date, String disabilityid) throws SADAREMDBException, SQLException;

    public String getDisabilityName(DataSource ds,String disabilityid) throws SADAREMDBException, SQLException;

    public int checkDoctorWithRegistrationNo(DataSource ds, int campId, String disabilityid, String docreg);
}
