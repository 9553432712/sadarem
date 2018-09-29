package org.bf.disability.form;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.*;


/**
 * This class contains the fields, required to generating reports
 * @author sunima
 * @version 1.0
 */
public class CommonReportForm extends ActionForm {
    
    private String district;
    private String districtid;
    private String districtcount;
    private String mandal;
    private String mandalname;
    private String mandalid;
    private String mandalcount;
    private String villagename;
    private String villagecount;
    private String villageid;
    private String habitationname;
    private String habitationcount;
    private String habitationid;
    private String tablename;
    private String fieldname;
    private String fieldvalue;
    private String cochlearimplantation;
    private String surgerytype;
    private String firstcolumn;
    private String secondcolumn;
    private String secondfieldvalue;
//from sunima report form
    private String name;
    private String age;
    private String gender;
    private String address;
    private String personcode;
    
    private String typeofdisability;
    private String subtype;
    private String subsubtype;
    private String totalpercent;
    
    
    private String caste;
    private String occupation;
    private String fromdate;
    private String todate;
    private String disabilityname;

    // Niramaya Report
    //Mental Retardation
    private String districtMRCount;
    private String mandalMRCount;
    private String villageMRCount;
    private String habitationMRCount;
    //Locomotor CP
    private String districtCEREBRALPALSYCount;
    private String mandalCEREBRALPALSYCount;
    private String villageCEREBRALPALSYCount;
    private String habitationCEREBRALPALSYCount;
    //Multiple Disability
    private String districtMULTIPLECount;
    private String mandalMULTIPLECount;
    private Collection niramayaReportCollection;
    private String villageMULTIPLECount;
    private String habitationMULTIPLECount;
    
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.fromdate=null;
        this.todate=null;
        
    }
    
    public String getDistrict() {
        return district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
    
    public String getDistrictid() {
        return districtid;
    }
    
    public void setDistrictid(String districtid) {
        this.districtid = districtid;
    }
    public String getDistrictcount() {
        return districtcount;
    }
    
    public void setDistrictcount(String districtcount) {
        this.districtcount = districtcount;
    }
    
    public String getMandal() {
        return mandal;
    }
    
    public void setMandal(String mandal) {
        this.mandal = mandal;
    }
    
    public String getMandalid() {
        return mandalid;
    }
    
    public void setMandalid(String mandalid) {
        this.mandalid = mandalid;
    }
    
    public String getMandalcount() {
        return mandalcount;
    }
    
    public void setMandalcount(String mandalcount) {
        this.mandalcount = mandalcount;
    }
    
    public String getTablename() {
        return tablename;
    }
    
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }
    
    public String getFieldname() {
        return fieldname;
    }
    
    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }
    
    public String getFieldvalue() {
        return fieldvalue;
    }
    
    public void setFieldvalue(String fieldvalue) {
        this.fieldvalue = fieldvalue;
    }
    
    public String getCochlearimplantation() {
        return cochlearimplantation;
    }
    
    public void setCochlearimplantation(String cochlearimplantation) {
        this.cochlearimplantation = cochlearimplantation;
    }
    
    public String getVillagename() {
        return villagename;
    }
    
    public void setVillagename(String villagename) {
        this.villagename = villagename;
    }
    
    public String getVillagecount() {
        return villagecount;
    }
    
    public void setVillagecount(String villagecount) {
        this.villagecount = villagecount;
    }
    
    public String getVillageid() {
        return villageid;
    }
    
    public void setVillageid(String villageid) {
        this.villageid = villageid;
    }
    
    public String getHabitationname() {
        return habitationname;
    }
    
    public void setHabitationname(String habitationname) {
        this.habitationname = habitationname;
    }
    
    public String getHabitationcount() {
        return habitationcount;
    }
    
    public void setHabitationcount(String habitationcount) {
        this.habitationcount = habitationcount;
    }
    
    public String getHabitationid() {
        return habitationid;
    }
    
    public void setHabitationid(String habitationid) {
        this.habitationid = habitationid;
    }
    
    public String getSurgerytype() {
        return surgerytype;
    }
    
    public void setSurgerytype(String surgerytype) {
        this.surgerytype = surgerytype;
    }
    
    public String getFirstcolumn() {
        return firstcolumn;
    }
    
    public void setFirstcolumn(String firstcolumn) {
        this.firstcolumn = firstcolumn;
    }
    
    public String getSecondcolumn() {
        return secondcolumn;
    }
    
    public void setSecondcolumn(String secondcolumn) {
        this.secondcolumn = secondcolumn;
    }
    
    public String getSecondfieldvalue() {
        return secondfieldvalue;
    }
    
    public void setSecondfieldvalue(String secondfieldvalue) {
        this.secondfieldvalue = secondfieldvalue;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAge() {
        return age;
    }
    
    public void setAge(String age) {
        this.age = age;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPersoncode() {
        return personcode;
    }
    
    public void setPersoncode(String personcode) {
        this.personcode = personcode;
    }
    
    public String getTypeofdisability() {
        return typeofdisability;
    }
    
    public void setTypeofdisability(String typeofdisability) {
        this.typeofdisability = typeofdisability;
    }
    
    public String getSubtype() {
        return subtype;
    }
    
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }
    
    public String getSubsubtype() {
        return subsubtype;
    }
    
    public void setSubsubtype(String subsubtype) {
        this.subsubtype = subsubtype;
    }
    
    public String getTotalpercent() {
        return totalpercent;
    }
    
    public void setTotalpercent(String totalpercent) {
        this.totalpercent = totalpercent;
    }
    
    
    
    public String getCaste() {
        return caste;
    }
    
    public void setCaste(String caste) {
        this.caste = caste;
    }
    
    public String getOccupation() {
        return occupation;
    }
    
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    
    public String getFromdate() {
        return fromdate;
    }
    
    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }
    
    public String getTodate() {
        return todate;
    }
    
    public void setTodate(String todate) {
        this.todate = todate;
    }
    
    public String getDisabilityname() {
        return disabilityname;
    }
    
    public void setDisabilityname(String disabilityname) {
        this.disabilityname = disabilityname;
    }

    /**
     * @return the districtMRCount
     */
    public String getDistrictMRCount() {
        return districtMRCount;
    }

    /**
     * @param districtMRCount the districtMRCount to set
     */
    public void setDistrictMRCount(String districtMRCount) {
        this.districtMRCount = districtMRCount;
    }

    /**
     * @return the districtCEREBRALPALSYCount
     */
    public String getDistrictCEREBRALPALSYCount() {
        return districtCEREBRALPALSYCount;
    }

    /**
     * @param districtCEREBRALPALSYCount the districtCEREBRALPALSYCount to set
     */
    public void setDistrictCEREBRALPALSYCount(String districtCEREBRALPALSYCount) {
        this.districtCEREBRALPALSYCount = districtCEREBRALPALSYCount;
    }

    /**
     * @return the districtMULTIPLECount
     */
    public String getDistrictMULTIPLECount() {
        return districtMULTIPLECount;
    }

    /**
     * @param districtMULTIPLECount the districtMULTIPLECount to set
     */
    public void setDistrictMULTIPLECount(String districtMULTIPLECount) {
        this.districtMULTIPLECount = districtMULTIPLECount;
    }

    /**
     * @return the mandalMRCount
     */
    public String getMandalMRCount() {
        return mandalMRCount;
    }

    /**
     * @param mandalMRCount the mandalMRCount to set
     */
    public void setMandalMRCount(String mandalMRCount) {
        this.mandalMRCount = mandalMRCount;
    }

    /**
     * @return the villageMRCount
     */
    public String getVillageMRCount() {
        return villageMRCount;
    }

    /**
     * @param villageMRCount the villageMRCount to set
     */
    public void setVillageMRCount(String villageMRCount) {
        this.villageMRCount = villageMRCount;
    }

   

    /**
     * @return the mandalCEREBRALPALSYCount
     */
    public String getMandalCEREBRALPALSYCount() {
        return mandalCEREBRALPALSYCount;
    }

    /**
     * @param mandalCEREBRALPALSYCount the mandalCEREBRALPALSYCount to set
     */
    public void setMandalCEREBRALPALSYCount(String mandalCEREBRALPALSYCount) {
        this.mandalCEREBRALPALSYCount = mandalCEREBRALPALSYCount;
    }

    /**
     * @return the villageCEREBRALPALSYCount
     */
    public String getVillageCEREBRALPALSYCount() {
        return villageCEREBRALPALSYCount;
    }

    /**
     * @param villageCEREBRALPALSYCount the villageCEREBRALPALSYCount to set
     */
    public void setVillageCEREBRALPALSYCount(String villageCEREBRALPALSYCount) {
        this.villageCEREBRALPALSYCount = villageCEREBRALPALSYCount;
    }

    /**
     * @return the habitationCEREBRALPALSYCount
     */
    public String getHabitationCEREBRALPALSYCount() {
        return habitationCEREBRALPALSYCount;
    }

    /**
     * @param habitationCEREBRALPALSYCount the habitationCEREBRALPALSYCount to set
     */
    public void setHabitationCEREBRALPALSYCount(String habitationCEREBRALPALSYCount) {
        this.habitationCEREBRALPALSYCount = habitationCEREBRALPALSYCount;
    }

    /**
     * @return the mandalMULTIPLECount
     */
    public String getMandalMULTIPLECount() {
        return mandalMULTIPLECount;
    }

    /**
     * @param mandalMULTIPLECount the mandalMULTIPLECount to set
     */
    public void setMandalMULTIPLECount(String mandalMULTIPLECount) {
        this.mandalMULTIPLECount = mandalMULTIPLECount;
    }

    /**
     * @return the villageMULTIPLECount
     */
    public String getVillageMULTIPLECount() {
        return villageMULTIPLECount;
    }

    /**
     * @param villageMULTIPLECount the villageMULTIPLECount to set
     */
    public void setVillageMULTIPLECount(String villageMULTIPLECount) {
        this.villageMULTIPLECount = villageMULTIPLECount;
    }

    

    /**
     * @return the mandalname
     */
    public String getMandalname() {
        return mandalname;
    }

    /**
     * @param mandalname the mandalname to set
     */
    public void setMandalname(String mandalname) {
        this.mandalname = mandalname;
    }

    /**
     * @return the habitationMRCount
     */
    public String getHabitationMRCount() {
        return habitationMRCount;
    }

    /**
     * @param habitationMRCount the habitationMRCount to set
     */
    public void setHabitationMRCount(String habitationMRCount) {
        this.habitationMRCount = habitationMRCount;
    }

    /**
     * @return the habitationMULTIPLECount
     */
    public String getHabitationMULTIPLECount() {
        return habitationMULTIPLECount;
    }

    /**
     * @param habitationMULTIPLECount the habitationMULTIPLECount to set
     */
    public void setHabitationMULTIPLECount(String habitationMULTIPLECount) {
        this.habitationMULTIPLECount = habitationMULTIPLECount;
    }

    /**
     * @return the niramayaReportCollection
     */
    public Collection getNiramayaReportCollection() {
        return niramayaReportCollection;
    }

    /**
     * @param niramayaReportCollection the niramayaReportCollection to set
     */
    public void setNiramayaReportCollection(Collection niramayaReportCollection) {
        this.niramayaReportCollection = niramayaReportCollection;
    }

   
    
    
}
