package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.DoctorsInformationDTO;
/*
 * DoctorsInformationService.java
 *
 */

/**
 * This interface has abstract methods, whose implementation deals with data
 * manipulation operations like insertion, updataion, and deletion of doctor details
 * @author Pramod Kumar G
 * @version 1.0
 */
public interface DoctorsInformationService {

    public int insertDoctorsDetails(DataSource datasource, DoctorsInformationDTO doctorsinfodto, String localaddr, String districtid_id, String loginid_id, int campid_id) throws SADAREMDBException, SQLException;

    public DoctorsInformationDTO selectDoctorDetails(DataSource datasource, String disabilitytype, int campid_id) throws SADAREMDBException, SQLException;

    public DoctorsInformationDTO selectDoctors(DataSource datasource, String personCode) throws SADAREMDBException, SQLException;

    public int updateDoctorDetails(DataSource datasource, DoctorsInformationDTO doctorsinfodto, int campid_id) throws SADAREMDBException, SQLException;

    public int updateDoctors(DataSource datasource, DoctorsInformationDTO doctorsinfodto, String personcode, int campid_id,String role_id) throws SADAREMDBException, SQLException;

    public ArrayList selectDisabilityTypes(DataSource datasource) throws SADAREMDBException, SQLException;

    public  int addUser(DataSource ds, DoctorsInformationDTO doctorsinfodto, String districtid_id, String loginid_id, int campid_id, String localaddr, String flag) throws SADAREMDBException, SQLException ;
}
