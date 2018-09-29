/*
 * Village.java
 *
 * Created on December 7, 2006, 6:01 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.form;

import java.util.ArrayList;
import org.apache.struts.action.ActionForm;

/**
 * This class contains the fields, that holds information related to Roles.
 * @version 1.0
 */
public class Roles extends ActionForm
{
	public String roleid;
    public String rolename;
    public String mode;
    public String userid;
    public String password;
    public String username;
    public String encrptPwd;
    public String userencrptPwd;
    
    
    public ArrayList rolesList = new ArrayList();

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
          return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getEncrptPwd() {
        return encrptPwd;
    }

    public void setEncrptPwd(String encrptPwd) {
        this.encrptPwd = encrptPwd;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList getRolesList()
    {
        return rolesList;
    }

    public void setRolesList(ArrayList rolesList) 
    {
        this.rolesList = rolesList;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

	public String getUserencrptPwd() 
	{
		return userencrptPwd;
	}

	public void setUserencrptPwd(String userencrptPwd)
	{
		this.userencrptPwd = userencrptPwd;
	}
    
    
}
