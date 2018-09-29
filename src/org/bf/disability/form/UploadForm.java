/*
 * UploadForm.java

 */
package org.bf.disability.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import java.util.List;


/**
 * This class contains the fields, required to upload a photo
 * @version 1.0
 */
public class UploadForm extends org.apache.struts.action.ActionForm {
    
    private FormFile theFile=null;   
  


  
   

      
    
    public FormFile getTheFile() {               
        return this.theFile;
    }
    public void setTheFile(FormFile theFile) {               
        this.theFile = theFile;
    } 
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {        
        theFile=null;       
    }
    
  
}