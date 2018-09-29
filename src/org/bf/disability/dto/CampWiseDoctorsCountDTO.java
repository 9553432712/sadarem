/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.dto;

import java.util.ArrayList;

/**
 *
 * @author 693461
 */
public class CampWiseDoctorsCountDTO {

    private String date;
    private String mode;
    private String medicalBoard;

    private String campId;
    private String campName;
    private ArrayList campList = new ArrayList();

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



    public String getCampId() {
        return campId;
    }

    public void setCampId(String campId) {
        this.campId = campId;
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
    

}
