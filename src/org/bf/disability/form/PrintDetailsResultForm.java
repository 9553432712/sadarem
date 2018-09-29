/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.form;

import java.util.ArrayList;
import org.apache.struts.action.ActionForm;

/**
 *
 * @author 747577
 */
public class PrintDetailsResultForm extends ActionForm{

    private String mode;
     private String s_id;
    private String district_id;
    private String camp_id;

    ArrayList campList = new ArrayList();

    public ArrayList getCampList() {
        return campList;
    }

    public void setCampList(ArrayList campList) {
        this.campList = campList;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getCamp_id() {
        return camp_id;
    }

    public void setCamp_id(String camp_id) {
        this.camp_id = camp_id;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }



}
