/*
 * Village.java
 */

package org.bf.disability.form;
import org.apache.struts.action.ActionForm;


/**
 * This class contains the fields, required to hold user details
 * @version 1.0
 */
public class Users  extends ActionForm {
    
    /** Creates a new instance of Village */
    public Users() {
    }
    public String userid;
    public String password;

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
}
