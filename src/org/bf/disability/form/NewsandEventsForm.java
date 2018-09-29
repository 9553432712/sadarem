/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.form;

import org.apache.struts.upload.FormFile;

/**
 *
 * @author 728056
 */
public class NewsandEventsForm extends org.apache.struts.action.ActionForm {

    private String mode;
    private String newsTitle;
    private String newsDescription;
    private String sub;
    private String sub1;
    private String about;
    private String sugDescription;
    private String newDataId;
    private FormFile newsUpload;
    private String fileName;
    private String rowId;
    private String newsId;
    private String newsData;
    private String fromDate;
    private String toDate;
    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    
    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getNewsData() {
        return newsData;
    }

    public void setNewsData(String newsData) {
        this.newsData = newsData;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FormFile getNewsUpload() {
        return newsUpload;
    }

    public void setNewsUpload(FormFile newsUpload) {
        this.newsUpload = newsUpload;
    }

    public String getNewDataId() {
        return newDataId;
    }

    public void setNewDataId(String newDataId) {
        this.newDataId = newDataId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getSugDescription() {
        return sugDescription;
    }

    public void setSugDescription(String sugDescription) {
        this.sugDescription = sugDescription;
    }

    public String getSub1() {
        return sub1;
    }

    public void setSub1(String sub1) {
        this.sub1 = sub1;
    }
}
