package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.dto.LoginDTO;
import org.bf.disability.form.ShgloginForm;

/**
 * This interface has abstract methods, whose implementation deals with the
 * verification of a user, and check for the services available for the respective
 * user
 * @version 1.0
 */
public interface LoginService {

    public LoginDTO getValidUser(DataSource ds, String userid, String password) throws SADAREMException, SQLException;

    public String getRoleId(DataSource ds, String userid) throws SADAREMException, SQLException;

    public Vector getServicesSQL(DataSource ds, String roleid) throws SADAREMException, SQLException;

    public int unAuthorisedAccessStatus(DataSource ds, String userid) throws SADAREMException, SQLException;

    public int changePassword(DataSource ds, ShgloginForm ShgloginForm, String username) throws SADAREMException, SQLException;

    public ArrayList getCountOfApprovalPendingofRole(DataSource ds, String districtId, String roleid, String mandalId) throws SADAREMException, SQLException;

    public String getCampNames(DataSource ds, String disId) throws SADAREMException, SQLException;

    public LoginDTO getRDCallCenterValidUser(DataSource ds, String userid) throws SADAREMException, SQLException;

    
    public int updatePasswordFlag(DataSource ds, String username, String password,String encrptPassword, String districtId, String roleId) throws SADAREMException, SQLException;

    public int insertLoginStatus(DataSource ds, String userid, String systemIp, String status) throws SADAREMException, SQLException;

    public int loginStatusCnt(DataSource ds, String userid) throws SADAREMException, SQLException;

    public LoginDTO getUserDetails(DataSource ds, String userid) throws SADAREMException, SQLException;
}
