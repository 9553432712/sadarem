package org.bf.disability.form;

import org.apache.struts.action.ActionForm;

/**
 * This form contains the fields for adding a new user
 * @version 1.0
 */
public class AddUser extends ActionForm {

    public AddUser() {
    }
    public String userid;
    public String password;
    public String username;
    public String roleid;
    public String encrptPwd;

    public String getEncrptPwd() {
        return encrptPwd;
    }

    public void setEncrptPwd(String encrptPwd) {
        this.encrptPwd = encrptPwd;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }
}
