/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.dto;

/**
 *
 * @author 693461
 */
public class DiagnosisDisabilityUpdateDTO {
     private String personCode;
   private String mode;
   private String diagnosisDisability;

    public String getDiagnosisDisability() {
        return diagnosisDisability;
    }

    public void setDiagnosisDisability(String diagnosisDisability) {
        this.diagnosisDisability = diagnosisDisability;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

}
