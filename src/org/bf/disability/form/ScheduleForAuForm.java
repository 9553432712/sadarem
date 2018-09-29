/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

/**
 *
 * @author 509862
 */
public class ScheduleForAuForm extends ActionForm {

    HttpServletRequest request = null;

  

  


    public String[] getMandal_id() {
        return mandal_id;
    }

    public void setMandal_id(String[] mandal_id) {
        this.mandal_id = mandal_id;
    }



  
    private String mode;
 

  

    public String[] getVillage_id() {
        return village_id;
    }

    public void setVillage_id(String[] village_id) {
        this.village_id = village_id;
    }




    public ArrayList getVillagelist() {
        return villagelist;
    }

    public void setVillagelist(ArrayList villagelist) {
        this.villagelist = villagelist;
    }
    
    private String[] mandal_id;
    private String[] village_id;

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }
    private String fromdate;
    private ArrayList mandallist = new ArrayList();
    private ArrayList villagelist = new ArrayList();

    public String[] getSelectedMandalsHidden() {
        return selectedMandalsHidden;
    }

    public void setSelectedMandalsHidden(String[] selectedMandalsHidden) {
        this.selectedMandalsHidden = selectedMandalsHidden;
    }
    private String[] selectedMandalsHidden;

   
   
    public ArrayList getMandallist() {


        return mandallist;
    }

    public void setMandallist(ArrayList mandallist) {
        this.mandallist = mandallist;
    }

   
    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

}
