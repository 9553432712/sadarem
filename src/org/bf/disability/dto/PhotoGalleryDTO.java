/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dto;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author 484898
 */
public class PhotoGalleryDTO {

    private String event;
    private String description;
    //private FormFile uploadPhoto;
    private String mode;
    private String url;
    private String noOfPhotos;
    private String rowId;
    private String conditionFlag;
    private List formFiles = new ArrayList();

    public List getFormFiles() {
        return formFiles;
    }

    public void setImageList(List imageList) {
        this.formFiles = imageList;
    }

    public void setFormFiles(List formFiles) {
        this.formFiles = formFiles;
    }

    public List getUploadPhoto() {
        return this.formFiles;
    }

    public void setUploadPhoto(int iIndex, FormFile formFile) {
        if (!formFile.getFileName().equals("")) {
            if (this.formFiles.size() <= iIndex) {
                // Fill the list to the specified size
                for (int i = this.formFiles.size(); i < iIndex; i++) {
                    this.formFiles.add(null);
                }
                this.formFiles.add(formFile);

            } else {
                this.formFiles.set(iIndex, formFile);
            }
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNoOfPhotos() {
        return noOfPhotos;
    }

    public void setNoOfPhotos(String noOfPhotos) {
        this.noOfPhotos = noOfPhotos;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getConditionFlag() {
        return conditionFlag;
    }

    public void setConditionFlag(String conditionFlag) {
        this.conditionFlag = conditionFlag;
    }
    
}
