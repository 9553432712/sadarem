/*
 * LoginDTO.java
 */

package org.bf.disability.dto;

/**
 *It deals with login action
 */
public class LoginDTO {
    
    private String loginid;
    private int serviceid;
    private String servicename;
    private String pswd;
    private String targeturl;

    //added newly for Login user based on campId and DistrictID
    private String personName;
    private String userName;
    private int roleId;
    private String districtId;
    private int campId;
    private String mandal_Id;
    private String contact_no;
    private String email_id;
    // added for restricting more then one person loging with same userid
    private boolean userStatus;

    // added for reset and Loct the UserLogins
    private String rowId;
    private String rolename;
    private String lockstatus;
    private String status;
    private String pwdFlag;

    public String getPwdFlag() {
        return pwdFlag;
    }

    public void setPwdFlag(String pwdFlag) {
        this.pwdFlag = pwdFlag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMandal_Id() {
        return mandal_Id;
    }

    public void setMandal_Id(String mandal_Id) {
        this.mandal_Id = mandal_Id;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public int getServiceid() {
        return serviceid;
    }

    public void setServiceid(int serviceid) {
        this.serviceid = serviceid;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getTargeturl() {
        return targeturl;
    }

    public void setTargeturl(String targeturl) {
        this.targeturl = targeturl;
    }

    /**
     * @return the userName
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * @param userName the userName to set
     */
    public void setPersonName(String personName)
    {
        this.personName = personName;
    }


    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the roleId
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the districtId
     */
    public String getDistrictId() {
        return districtId;
    }

    /**
     * @param districtId the districtId to set
     */
    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    /**
     * @return the campId
     */
    public int getCampId() {
        return campId;
    }

    /**
     * @param campId the campId to set
     */
    public void setCampId(int campId) {
        this.campId = campId;
    }

    /**
     * @return the userStatus
     */
    public boolean isUserStatus() 
    {
        return userStatus;
    }

    /**
     * @param userStatus the userStatus to set
     */
    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * @return the rowId
     */
    public String getRowId() {
        return rowId;
    }

    /**
     * @param rowId the rowId to set
     */
    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getLockstatus() {
		return lockstatus;
	}

	public void setLockstatus(String lockstatus) {
		this.lockstatus = lockstatus;
	}

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	
    
    
}
