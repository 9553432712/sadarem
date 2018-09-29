/*
 * AddHabitationForm.java
 *
 * Created on June 18, 2008, 3:33 PM
 */
package org.bf.disability.form;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionFormBean;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.dto.AddHabitationDTO;

/**
 * This class contains all the setter and getter methods.
 * These methods are used either to hold the form values or to populate
 * the data on to the form.
 * All the data members and the member functions in this class are
 * self-explanatory
 * @author Pramod Kumar G
 * @version 1.0
 */
public class AddHabitationForm extends ActionForm {

    private String district_id;
    private String assembly_id;
    private String mandal_id;
    private String village_id;
    private String panchayat_id;
    private String panchayat_name;
    private String village_name;
    private String district_name;
    private String mandal_name;
    private String assembly_name;
    private String habitation_name;
    private String system_ip;
    private String login_id;


    private String habitation_id;
    private String habitation_code;
    public String camp_name;
    public String camp_address;
    public String camp_venue;
    public String person_name;
    public String user_name;
    public String password;
    public int role_id;
    public int camp_id;
    private ArrayList districtlist = new ArrayList();
    private ArrayList mandallist = new ArrayList();
    private ArrayList panchayatlist = new ArrayList();
    private ArrayList villagelist = new ArrayList();
    private ArrayList assemblylist = new ArrayList();
    public ArrayList camplist = new ArrayList();
    public ArrayList rolelist = new ArrayList();
    private ArrayList userdetailslist=new ArrayList();
    private int roleid;
    private String rowid;
    private String encrptPwd;

    public String getEncrptPwd() {
        return encrptPwd;
    }

    public void setEncrptPwd(String encrptPwd) {
        this.encrptPwd = encrptPwd;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public AddHabitationForm() {
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getMandal_id() {
        return mandal_id;
    }

    public void setMandal_id(String mandal_id) {
        this.mandal_id = mandal_id;
    }

    public String getVillage_id() {
        return village_id;
    }

    public void setVillage_id(String village_id) {
        this.village_id = village_id;
    }

    public String getPanchayat_id() {
        return panchayat_id;
    }

    public void setPanchayat_id(String panchayat_id) {
        this.panchayat_id = panchayat_id;
    }

    public String getPanchayat_name() {
        return panchayat_name;
    }

    public void setPanchayat_name(String panchayat_name) {
        this.panchayat_name = panchayat_name;
    }

    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getMandal_name() {
        return mandal_name;
    }

    public void setMandal_name(String mandal_name) {
        this.mandal_name = mandal_name;
    }

    public String getHabitation_id() {
        return habitation_id;
    }

    public void setHabitation_id(String habitation_id) {
        this.habitation_id = habitation_id;
    }

    public ArrayList getDistrictlist() {
        return districtlist;
    }

    public void setDistrictlist(ArrayList districtlist) {
        this.districtlist = districtlist;
    }

    public ArrayList getMandallist() {
        return mandallist;
    }

    public void setMandallist(ArrayList mandallist) {
        this.mandallist = mandallist;
    }

    public ArrayList getPanchayatlist() {
        return panchayatlist;
    }

    public void setPanchayatlist(ArrayList panchayatlist) {
        this.panchayatlist = panchayatlist;
    }

    public ArrayList getVillagelist() {
        return villagelist;
    }

    public void setVillagelist(ArrayList villagelist) {
        this.villagelist = villagelist;
    }

    public ArrayList getAssemblylist() {
        return assemblylist;
    }

    public void setAssemblylist(ArrayList assemblylist) {
        this.assemblylist = assemblylist;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
//          this.districtlist=null ;
//          this.mandallist=null;
//          this.panchayatlist=null;
//          this.villagelist=null;
//          this.habitationlist=null;
//
//          this. district_id=null;
//          this. mandal_id=null;
//          this. village_id=null;
//          this. panchayat_id=null;
//          this. panchayat_name=null;
//          this. village_name=null;
//          this. district_name=null;
//          this. mandal_name=null;
//          this. habitation_id=null;
//          this. habitation_name=null;
    }

    public String getAssembly_id() {
        return assembly_id;
    }

    public void setAssembly_id(String assembly_id) {
        this.assembly_id = assembly_id;
    }

    public String getAssembly_name() {
        return assembly_name;
    }

    public void setAssembly_name(String assembly_name) {
        this.assembly_name = assembly_name;
    }

    public String getHabitation_name() {
        return habitation_name;
    }

    public void setHabitation_name(String habitation_name) {
        this.habitation_name = habitation_name;
    }

    public String getSystem_ip() {
        return system_ip;
    }

    public void setSystem_ip(String system_ip) {
        this.system_ip = system_ip;
    }

    public String getHabitation_code() {
        return habitation_code;
    }

    public void setHabitation_code(String habitation_code) {
        this.habitation_code = habitation_code;
    }

    /**
     * @return the camp_name
     */
    public String getCamp_name() {
        return camp_name;
    }

    public String getCamp_venue() {
        return camp_venue;
    }

    public void setCamp_venue(String camp_venue) {
        this.camp_venue = camp_venue;
    }

    /**
     * @param camp_name the camp_name to set
     */
    public void setCamp_name(String camp_name) {
        this.camp_name = camp_name;
    }

    /**
     * @return the camp_address
     */
    public String getCamp_address() {
        return camp_address;
    }

    /**
     * @param camp_address the camp_address to set
     */
    public void setCamp_address(String camp_address) {
        this.camp_address = camp_address;
    }

    /**
     * @return the person_name
     */
    public String getPerson_name() {
        return person_name;
    }

    /**
     * @param person_name the person_name to set
     */
    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    /**
     * @return the user_name
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * @param user_name the user_name to set
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the camplist
     */
    public ArrayList getCamplist() {
        return camplist;
    }

    /**
     * @param camplist the camplist to set
     */
    public void setCamplist(ArrayList camplist) {
        this.camplist = camplist;
    }

    /**
     * @return the rolelist
     */
    public ArrayList getRolelist() {
        return rolelist;
    }

    /**
     * @param rolelist the rolelist to set
     */
    public void setRolelist(ArrayList rolelist) {
        this.rolelist = rolelist;
    }

    /**
     * @return the role_id
     */
    public int getRole_id() {
        return role_id;
    }

    /**
     * @param role_id the role_id to set
     */
    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    /**
     * @return the camp_id
     */
    public int getCamp_id() {
        return camp_id;
    }

    /**
     * @param camp_id the camp_id to set
     */
    public void setCamp_id(int camp_id) {
        this.camp_id = camp_id;
    }

    

    /**
     * @return the roleid
     */
    public int getRoleid() {
        return roleid;
    }

    /**
     * @param roleid the roleid to set
     */
    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    /**
     * @return the userdetailslist
     */
    public ArrayList getUserdetailslist() {
        return userdetailslist;
    }

    /**
     * @param userdetailslist the userdetailslist to set
     */
    public void setUserdetailslist(ArrayList userdetailslist) {
        this.userdetailslist = userdetailslist;
    }

    /**
     * @return the rowid
     */
    public String getRowid() {
        return rowid;
    }

    /**
     * @param rowid the rowid to set
     */
    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

   
   

   
   
}
