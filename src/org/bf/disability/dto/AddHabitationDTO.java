package org.bf.disability.dto;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionFormBean;
import org.apache.struts.action.ActionMapping;

/**
 * This class contains the same information as in AddHabitationForm.
 * This class is helpful in dealing with the database.
 * Almost all the data members and member functions in this class are
 * self-explanatory.
 * @author Pramod Kumar G
 */
public class AddHabitationDTO {

    /** Creates a new instance of AddHabitationDTO */
    public AddHabitationDTO() {
    }
    private String district_id;
    private String mandal_id;
    private String village_id;
    private String panchayat_id;
    private String panchayat_name;
    private String village_name;
    private String district_name;
    private String mandal_name;
    private String habitation_id;
    private String habitation_name;
    private String assembly_id;
    private String assembly_name;
    private String system_ip;
    private String habitation_code;
    private String camp_name;
    private String camp_address;
    public String Camp_ID;
    public String camp_venue;
    private String login_id;

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getCamp_venue() {
        return camp_venue;
    }

    public void setCamp_venue(String camp_venue) {
        this.camp_venue = camp_venue;
    }
    public int Camp_id;

    public int getCamp_id() {
        return Camp_id;
    }

    public void setCamp_id(int Camp_id) {
        this.Camp_id = Camp_id;
    }
    public String Name;
    public String Address;
    public int role_id;
    public String role_name;
    public String person_name;
    public String user_name;
    public String password;
    private String rowid;
    public String encrptPwd;

    public String getEncrptPwd() {
        return encrptPwd;
    }

    public void setEncrptPwd(String encrptPwd) {
        this.encrptPwd = encrptPwd;
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

    public String getHabitation_name() {
        return habitation_name;
    }

    public void setHabitation_name(String habitation_name) {
        this.habitation_name = habitation_name;
    }

    public String getAssembly_id() {
        return assembly_id;
    }

    public void setAssembly_id(String Assembly_id) {
        this.assembly_id = Assembly_id;
    }

    public String getAssembly_name() {
        return assembly_name;
    }

    public void setAssembly_name(String assembly_name) {
        this.assembly_name = assembly_name;
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
     * @return the Camp_ID
     */
    public String getCamp_ID() {
        return Camp_ID;
    }

    /**
     * @param Camp_ID the Camp_ID to set
     */
    public void setCamp_ID(String Camp_ID) {
        this.Camp_ID = Camp_ID;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @param Address the Address to set
     */
    public void setAddress(String Address) {
        this.Address = Address;
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
     * @return the role_name
     */
    public String getRole_name() {
        return role_name;
    }

    /**
     * @param role_name the role_name to set
     */
    public void setRole_name(String role_name) {
        this.role_name = role_name;
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
