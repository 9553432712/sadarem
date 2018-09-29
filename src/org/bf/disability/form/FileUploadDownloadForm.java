/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import java.util.ArrayList;

/**
 *
 * @author 693461
 */
public class FileUploadDownloadForm extends ActionForm {

    private FormFile uploadFile;
    private String mode;
    private String district_id = null;
    private String subjectData;
    private String bodyMailData;
    private ArrayList districtlist = new ArrayList();

    public ArrayList getDistrictlist() {
        return districtlist;
    }

    public void setDistrictlist(ArrayList districtlist) {
        this.districtlist = districtlist;
    }

    public String getBodyMailData() {
        return bodyMailData;
    }

    public void setBodyMailData(String bodyMailData) {
        this.bodyMailData = bodyMailData;
    }

    public String getSubjectData() {
        return subjectData;
    }

    public void setSubjectData(String subjectData) {
        this.subjectData = subjectData;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public FormFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(FormFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }
}
