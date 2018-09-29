/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.form;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.struts.action.ActionForm;

/**
 *
 * @author 310926
 */
public class ReportToCaptureDeadDetailsForm extends ActionForm {

    private String mode;
    private String personcode;
    private String district_id;
    private String aadharRadio;
    private String aadharCardNo;
    private String aadharRemarks;
    private String deadRadio;
    private String deathdate;
    private String SHGRadio;
    private String shgReason;
    private String shgname;
    private String shgid;
    private String shgdate;
    private String appRadio;
    private String appReason;
    private String typeOfApp;
    private String appOrganization;
    private String appdate;
    private String surRadio;
    private String surReason;
    private String surtypeOfApp;
    private String surOrganization;
    private String surdate;
    private String pRadio;
    private String pReason;
    private String pAccNo;
    private String pBank;
    private String pBranch;
    private String pIFSCCode;
    private String aasaraRadio;
    private String aRemarks;
    private String aasaraPensionId;
    private String systemIp;
    private String loginId;
    private String serveDoneBy;
    private String serveid;
    private String designation;
    private String recivedDate;
    private String dataentryName;
    private String dataOperatordate;
    private String voId;
    private String voName;
     private String shgId;
     private String voidData;
     private  String nameOfPwdShg;
      private ArrayList shgList = new ArrayList();
    private ArrayList voList = new ArrayList();


    public String getNameOfPwdShg() {
        return nameOfPwdShg;
    }

    public String getVoidData() {
        return voidData;
    }

    public void setVoidData(String voidData) {
        this.voidData = voidData;
    }

    public ArrayList getShgList() {
        return shgList;
    }

    public void setShgList(ArrayList shgList) {
        this.shgList = shgList;
    }

    public void setNameOfPwdShg(String nameOfPwdShg) {
        this.nameOfPwdShg = nameOfPwdShg;
    }

    public String getVoId() {
        return voId;
    }

    public String getShgId() {
        return shgId;
    }

    public void setShgId(String shgId) {
        this.shgId = shgId;
    }

    public void setVoId(String voId) {
        this.voId = voId;
    }

    public ArrayList getVoList() {
        return voList;
    }

    public void setVoList(ArrayList voList) {
        this.voList = voList;
    }

    public String getVoName() {
        return voName;
    }

    public void setVoName(String voName) {
        this.voName = voName;
    }
    


    public String getpIFSCCode() {
        return pIFSCCode;
    }

    public void setpIFSCCode(String pIFSCCode) {
        this.pIFSCCode = pIFSCCode;
    }


    
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getSystemIp() {
        return systemIp;
    }

    public void setSystemIp(String systemIp) {
        this.systemIp = systemIp;
    }

    
    public String getaRemarks() {
        return aRemarks;
    }

    public void setaRemarks(String aRemarks) {
        this.aRemarks = aRemarks;
    }

    public String getAasaraPensionId() {
        return aasaraPensionId;
    }

    public void setAasaraPensionId(String aasaraPensionId) {
        this.aasaraPensionId = aasaraPensionId;
    }

    public String getAasaraRadio() {
        return aasaraRadio;
    }

    public void setAasaraRadio(String aasaraRadio) {
        this.aasaraRadio = aasaraRadio;
    }

    public String getpAccNo() {
        return pAccNo;
    }

    public void setpAccNo(String pAccNo) {
        this.pAccNo = pAccNo;
    }

    public String getpBank() {
        return pBank;
    }

    public void setpBank(String pBank) {
        this.pBank = pBank;
    }

    public String getpBranch() {
        return pBranch;
    }

    public void setpBranch(String pBranch) {
        this.pBranch = pBranch;
    }

    public String getpRadio() {
        return pRadio;
    }

    public void setpRadio(String pRadio) {
        this.pRadio = pRadio;
    }

    public String getpReason() {
        return pReason;
    }

    public void setpReason(String pReason) {
        this.pReason = pReason;
    }

    
    public String getSurOrganization() {
        return surOrganization;
    }

    public void setSurOrganization(String surOrganization) {
        this.surOrganization = surOrganization;
    }

    public String getSurRadio() {
        return surRadio;
    }

    public void setSurRadio(String surRadio) {
        this.surRadio = surRadio;
    }

    public String getSurReason() {
        return surReason;
    }

    public void setSurReason(String surReason) {
        this.surReason = surReason;
    }

    public String getSurdate() {
        return surdate;
    }

    public void setSurdate(String surdate) {
        this.surdate = surdate;
    }

    public String getSurtypeOfApp() {
        return surtypeOfApp;
    }

    public void setSurtypeOfApp(String surtypeOfApp) {
        this.surtypeOfApp = surtypeOfApp;
    }

    public String getAppOrganization() {
        return appOrganization;
    }

    public void setAppOrganization(String appOrganization) {
        this.appOrganization = appOrganization;
    }

    public String getAppRadio() {
        return appRadio;
    }

    public void setAppRadio(String appRadio) {
        this.appRadio = appRadio;
    }

    public String getAppReason() {
        return appReason;
    }

    public void setAppReason(String appReason) {
        this.appReason = appReason;
    }

    public String getAppdate() {
        return appdate;
    }

    public void setAppdate(String appdate) {
        this.appdate = appdate;
    }

    public String getTypeOfApp() {
        return typeOfApp;
    }

    public void setTypeOfApp(String typeOfApp) {
        this.typeOfApp = typeOfApp;
    }

    public String getShgReason() {
        return shgReason;
    }

    public void setShgReason(String shgReason) {
        this.shgReason = shgReason;
    }

    public String getShgdate() {
        return shgdate;
    }

    public void setShgdate(String shgdate) {
        this.shgdate = shgdate;
    }

    public String getShgid() {
        return shgid;
    }

    public void setShgid(String shgid) {
        this.shgid = shgid;
    }

    public String getShgname() {
        return shgname;
    }

    public void setShgname(String shgname) {
        this.shgname = shgname;
    }

    public String getSHGRadio() {
        return SHGRadio;
    }

    public void setSHGRadio(String SHGRadio) {
        this.SHGRadio = SHGRadio;
    }

    public String getDeadRadio() {
        return deadRadio;
    }

    public void setDeadRadio(String deadRadio) {
        this.deadRadio = deadRadio;
    }

    public String getDeathdate() {
        return deathdate;
    }

    public void setDeathdate(String deathdate) {
        this.deathdate = deathdate;
    }

    public String getAadharCardNo() {
        return aadharCardNo;
    }

    public void setAadharCardNo(String aadharCardNo) {
        this.aadharCardNo = aadharCardNo;
    }

    public String getAadharRadio() {
        return aadharRadio;
    }

    public void setAadharRadio(String aadharRadio) {
        this.aadharRadio = aadharRadio;
    }

    public String getAadharRemarks() {
        return aadharRemarks;
    }

    public void setAadharRemarks(String aadharRemarks) {
        this.aadharRemarks = aadharRemarks;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPersoncode() {
        return personcode;
    }

    public void setPersoncode(String personcode) {
        this.personcode = personcode;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }
    public String getServeDoneBy() {
        return serveDoneBy;
    }

    public void setServeDoneBy(String serveDoneBy) {
        this.serveDoneBy = serveDoneBy;
    }

    public String getDataOperatordate() {
        return dataOperatordate;
    }

    public void setDataOperatordate(String dataOperatordate) {
        this.dataOperatordate = dataOperatordate;
    }

    public String getDataentryName() {
        return dataentryName;
    }

    public void setDataentryName(String dataentryName) {
        this.dataentryName = dataentryName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getRecivedDate() {
        return recivedDate;
    }

    public void setRecivedDate(String recivedDate) {
        this.recivedDate = recivedDate;
    }

    public String getServeid() {
        return serveid;
    }

    public void setServeid(String serveid) {
        this.serveid = serveid;
    }
}
