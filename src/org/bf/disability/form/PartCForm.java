

package org.bf.disability.form;
import org.bf.disability.action.PartcAction;
import org.apache.struts.action.*;



public class PartCForm extends ActionForm {
    
   
    private String individual;
    private String family;   
    private String educationservies;  
    private String vocationaltraining;
     private String earlyeducationservices;  

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
   
  
    
   
    
}

