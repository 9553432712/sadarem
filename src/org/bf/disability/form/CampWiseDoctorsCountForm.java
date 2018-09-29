/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.form;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author 693461
 */
public class CampWiseDoctorsCountForm extends org.apache.struts.action.ActionForm {

    private String date;
    private String mode;
    private String medicalBoard;
    private String campId;
    private String campName;
    private ArrayList campList = new ArrayList();
    private String doctorCount;
    private String doctorName;
    private String campBasedData;
    private String campDate;
    private String[] hiddenDatacountOfPwd;
    private String medicalBoardId;
    private String[] disabilityId;
    private int[] doctorsCountDisabilityId;
    private int[] assessedDoctorCount;
    private String mailcountOfPwdDetails;
    private String[] countOfPwd;
    private String[] docRegNo;
    private ArrayList insertdata = new ArrayList();
    private ArrayList disabilityList = new ArrayList();
    private String disabilityid = null;
    private String[] totalCount;
    private String docname;
    private String docreg;
    private String docdesig;
    private String[] doctorReg;
    private String[] disId;
    private String disName;
    private String count;
     private ArrayList sessionList = new ArrayList();

    public ArrayList getSessionList() {
        return sessionList;
    }

    public void setSessionList(ArrayList sessionList) {
        this.sessionList = sessionList;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public ArrayList getInsertdata() {
        return insertdata;
    }

    public void setInsertdata(ArrayList insertdata) {
        this.insertdata = insertdata;
    }

    public String[] getDocRegNo() {
        return docRegNo;
    }

    public void setDocRegNo(String[] docRegNo) {
        this.docRegNo = docRegNo;
    }

    public String getMailcountOfPwdDetails() {
        return mailcountOfPwdDetails;
    }

    public void setMailcountOfPwdDetails(String mailcountOfPwdDetails) {
        this.mailcountOfPwdDetails = mailcountOfPwdDetails;
    }

    public int[] getDoctorsCountDisabilityId() {
        return doctorsCountDisabilityId;
    }

    public void setDoctorsCountDisabilityId(int[] doctorsCountDisabilityId) {
        this.doctorsCountDisabilityId = doctorsCountDisabilityId;
    }

    public int[] getAssessedDoctorCount() {
        return assessedDoctorCount;
    }

    public void setAssessedDoctorCount(int[] assessedDoctorCount) {
        this.assessedDoctorCount = assessedDoctorCount;
    }

    public String[] getDisabilityId() {
        return disabilityId;
    }

    public void setDisabilityId(String[] disabilityId) {
        this.disabilityId = disabilityId;
    }

    public String getMedicalBoardId() {
        return medicalBoardId;
    }

    public void setMedicalBoardId(String medicalBoardId) {
        this.medicalBoardId = medicalBoardId;
    }

    public String[] getHiddenDatacountOfPwd() {
        return hiddenDatacountOfPwd;
    }

    public void setHiddenDatacountOfPwd(String[] hiddenDatacountOfPwd) {
        this.hiddenDatacountOfPwd = hiddenDatacountOfPwd;
    }

    public String getCampDate() {
        return campDate;
    }

    public void setCampDate(String campDate) {
        this.campDate = campDate;
    }

    public String getCampBasedData() {
        return campBasedData;
    }

    public void setCampBasedData(String campBasedData) {
        this.campBasedData = campBasedData;
    }

    public String getDoctorCount() {
        return doctorCount;
    }

    public void setDoctorCount(String doctorCount) {
        this.doctorCount = doctorCount;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String[] getCountOfPwd() {
        return countOfPwd;
    }

    public void setCountOfPwd(String[] countOfPwd) {
        this.countOfPwd = countOfPwd;
    }

    public String getCampId() {
        return campId;
    }

    public void setCampId(String campId) {
        this.campId = campId;
    }

    public ArrayList getCampList() {
        return campList;
    }

    public void setCampList(ArrayList campList) {
        this.campList = campList;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMedicalBoard() {
        return medicalBoard;
    }

    public void setMedicalBoard(String medicalBoard) {
        this.medicalBoard = medicalBoard;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public ArrayList getDisabilityList() {
        return disabilityList;
    }

    public void setDisabilityList(ArrayList disabilityList) {
        this.disabilityList = disabilityList;
    }

    public String getDisabilityid() {
        return disabilityid;
    }

    public void setDisabilityid(String disabilityid) {
        this.disabilityid = disabilityid;
    }

    public String[] getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String[] totalCount) {
        this.totalCount = totalCount;
    }

    public String getDocdesig() {
        return docdesig;
    }

    public void setDocdesig(String docdesig) {
        this.docdesig = docdesig;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public String getDocreg() {
        return docreg;
    }

    public void setDocreg(String docreg) {
        this.docreg = docreg;
    }

    public String[] getDoctorReg() {
        return doctorReg;
    }

    public void setDoctorReg(String[] doctorReg) {
        this.doctorReg = doctorReg;
    }

    public String[] getDisId() {
        return disId;
    }

    public void setDisId(String[] disId) {
        this.disId = disId;
    }

    

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        setCountOfPwd(null);

    }
}
