package org.bf.disability.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ShgloginForm extends ActionForm {

    private String password;
    private String userid;
    private String newpassword;
    private String confirmpassword;
    // added for reset and Loct the UserLogins
    private String rowId;
    private String userName;
    private String loginid;
    private String mode;
    private String encrptPwd;
    private String txtInput;

    public String getTxtInput() {
        return txtInput;
    }

    public void setTxtInput(String txtInput) {
        this.txtInput = txtInput;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    
    public String getEncrptPwd() {
        return encrptPwd;
    }

    public void setEncrptPwd(String encrptPwd) {
        this.encrptPwd = encrptPwd;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.userid = null;
        this.password = null;
        this.confirmpassword = null;
        this.userName = null;
    }

    /**
     * @return the newpassword
     */
    public String getNewpassword() {
        return newpassword;
    }

    /**
     * @param newpassword the newpassword to set
     */
    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    /**
     * @return the rowId
     */
    public String getRowId() {
        return rowId;
    }

    /**
     * @return the confirmpassword
     */
    public String getConfirmpassword() {
        return confirmpassword;
    }

    /**
     * @param confirmpassword the confirmpassword to set
     */
    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    /**
     * @param rowId the rowId to set
     */
    public void setRowId(String rowId) {
        this.rowId = rowId;
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
     * @return the loginid
     */
    public String getLoginid() {
        return loginid;
    }

    /**
     * @param loginid the loginid to set
     */
    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }
}

