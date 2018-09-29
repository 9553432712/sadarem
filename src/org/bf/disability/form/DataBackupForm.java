/*
 * DataBackupForm.java
 *
 * Created on September 22, 2008, 11:24 AM
 */

package org.bf.disability.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * This class contains the fields, required to take database backup
 * @author Pramod Kumar
 * @version 1.0
 */

public class DataBackupForm extends org.apache.struts.action.ActionForm 
{
   private String backupdate; 

    public String getBackupdate() {
        return backupdate;
    }

    public void setBackupdate(String backupdate) {
        this.backupdate = backupdate;
    }
   
}
