

package org.bf.disability.form;
import org.bf.disability.action.PartcAction;
import org.apache.struts.action.*;



public class PartCUpdateForm extends ActionForm {
    
   
    private String individual;
    private String family;   
    private String educationservies;  
    private String vocationaltraining;
     private String earlyeducationservices;  
     private String anyotherservices;
     private String loginid;
     private String systemip;
    private String personcode;
    private boolean  railwaycertificate;

    private String reassessment;//Text area for reassesment

    public String getReassessment() {
        return reassessment;
    }

    public void setReassessment(String reassessment) {
        this.reassessment = reassessment;
    }

    public String getIndividual() {
        return individual;
    }

    public void setIndividual(String individual) {
        this.individual = individual;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getEducationservies() {
        return educationservies;
    }

    public void setEducationservies(String educationservies) {
        this.educationservies = educationservies;
    }

    public String getVocationaltraining() {
        return vocationaltraining;
    }

    public void setVocationaltraining(String vocationaltraining) {
        this.vocationaltraining = vocationaltraining;
    }

    public String getEarlyeducationservices() {
        return earlyeducationservices;
    }

    public void setEarlyeducationservices(String earlyeducationservices) {
        this.earlyeducationservices = earlyeducationservices;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getSystemip() {
        return systemip;
    }

    public void setSystemip(String systemip) {
        this.systemip = systemip;
    }

    public String getAnyotherservices() {
        if(!"".equals(anyotherservices)) {
            return  this.anyotherservices;
        } else {
            return null;
        }
    }

    public void setAnyotherservices(String anyotherservices) {
        this.anyotherservices = anyotherservices;
    }

    public String getPersoncode() {
        return personcode;
    }

    public void setPersoncode(String personcode) {
        this.personcode = personcode;
    }

    /**
     * @return the railwaycertificate
     */
    public boolean isRailwaycertificate() {
        return railwaycertificate;
    }

    /**
     * @param railwaycertificate the railwaycertificate to set
     */
    public void setRailwaycertificate(boolean railwaycertificate) {
        this.railwaycertificate = railwaycertificate;
    }

    

    
   
  
    
   
    
}

