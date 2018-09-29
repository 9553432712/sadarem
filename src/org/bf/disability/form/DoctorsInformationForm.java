package org.bf.disability.form;
import org.apache.struts.validator.ValidatorActionForm;


/**
 * This class contains the fields related to doctors and hospital, used in 
 * DoctorsInformation module
 * @author Pramod Kumar
 * @version 1.0
 */
public class DoctorsInformationForm extends ValidatorActionForm 
{
private String doctorname1;
private String registerno1;
private String designation1;
private String doctorname2;
private String registerno2;
private String designation2;
private String doctorname3;
private String registerno3;
private String designation3;
private String typeofdisability;
private String hospitalname;
private String hospitaladdress;

private String specialistprefix;


    public String getDoctorname1() {
        return doctorname1;
    }

    public void setDoctorname1(String doctorname1) {
        this.doctorname1 = doctorname1;
    }

    public String getRegisterno1() {
        return registerno1;
    }

    public void setRegisterno1(String registerno1) {
        this.registerno1 = registerno1;
    }

    public String getDesignation1() {
        return designation1;
    }

    public void setDesignation1(String designation1) {
        this.designation1 = designation1;
    }

    public String getDoctorname2() {
        return doctorname2;
    }

    public void setDoctorname2(String doctorname2) {
        this.doctorname2 = doctorname2;
    }

    public String getRegisterno2() {
        return registerno2;
    }

    public void setRegisterno2(String registerno2) {
        this.registerno2 = registerno2;
    }

    public String getDesignation2() {
        return designation2;
    }

    public void setDesignation2(String designation2) {
        this.designation2 = designation2;
    }

    public String getDoctorname3() {
        return doctorname3;
    }

    public void setDoctorname3(String doctorname3) {
        this.doctorname3 = doctorname3;
    }

    public String getRegisterno3() {
        return registerno3;
    }

    public void setRegisterno3(String registerno3) {
        this.registerno3 = registerno3;
    }

    public String getDesignation3() {
        return designation3;
    }

    public void setDesignation3(String designation3) {
        this.designation3 = designation3;
    }

    public String getTypeofdisability() {
        return typeofdisability;
    }

    public void setTypeofdisability(String typeofdisability) {
        this.typeofdisability = typeofdisability;
    }

    public String getHospitalname() {
        return hospitalname;
    }

    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname;
    }

    public String getHospitaladdress() {
        return hospitaladdress;
    }

    public void setHospitaladdress(String hospitaladdress) {
        this.hospitaladdress = hospitaladdress;
    }

    /**
     * @return the specialistprefix
     */
    public String getSpecialistprefix() {
        return specialistprefix;
    }

    /**
     * @param specialistprefix the specialistprefix to set
     */
    public void setSpecialistprefix(String specialistprefix) {
        this.specialistprefix = specialistprefix;
    }
   
}
