/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dto;

/**
 *
 * @author 484898
 */
public class DoctorSignDTO {

    private String doctorRegNumber;
    private String districtCode;
    private String[] doSign;
    private String systemIp;
    

    public String getDoctorRegNumber() {
        return doctorRegNumber;
    }

    public void setDoctorRegNumber(String doctorRegNumber) {
        this.doctorRegNumber = doctorRegNumber;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String[] getDoSign() {
        return doSign;
    }

    public void setDoSign(String[] doSign) {
        this.doSign = doSign;
    }

    public String getSystemIp() {
        return systemIp;
    }

    public void setSystemIp(String systemIp) {
        this.systemIp = systemIp;
    }
    
    
}
