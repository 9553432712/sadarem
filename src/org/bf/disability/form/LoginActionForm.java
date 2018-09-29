/*
 * LoginActionForm.java
 *
 * Created on April 8, 2008, 5:06 PM
 */

package org.bf.disability.form;




/**
 * This class contains the fields, required to Logging into the system
 * @version 1.0
 */
public class LoginActionForm extends org.apache.struts.action.ActionForm {
    
    private String loginid;
    private int serviceid;
    private String servicename;
     private String targeturl;
    private String pswd;

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
    
    
}
