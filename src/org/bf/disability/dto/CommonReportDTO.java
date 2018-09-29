package org.bf.disability.dto;
/**
 * This class contains the fields, required to generating reports
 * @author sunima
 * @version 1.0
 */
public class CommonReportDTO {
    private String houseno;
    private String mandalname;
    private String districtname;
    private String district;
    private String districtid;
    private String districtcount;
    private String mandal;
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
    
    //from sunima report
    private String name;
    private String age;
    private String gender;
    private String address;
    private String personcode;
    private  String fathername;
    private  String pincode;
    private  String dateOfBirth;
    private  String maritalStatus;
    private  String education;
    
    private String typeofdisability;
    private String subtype;
    private String subsubtype;
    private String totalpercent;
    
    private String annualincome;
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
    private String villageMULTIPLECount;
    private String habitationMULTIPLECount;
    
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
    
    public String setDistrictcount() {
        return districtcount;
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
    
    public String getAnnualincome() {
        return annualincome;
    }
    
    public void setAnnualincome(String annualincome) {
        this.annualincome = annualincome;
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
    
    public String getHouseno() {
        return houseno;
    }
    
    public void setHouseno(String houseno) {
        this.houseno = houseno;
    }
    
    public String getMandalname() {
        return mandalname;
    }
    
    public void setMandalname(String mandalname) {
        this.mandalname = mandalname;
    }
    
    public String getDistrictname() {
        return districtname;
    }
    
    public void setDistrictname(String districtname) {
        this.districtname = districtname;
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
     * @return the districtmrcount
     */
    public String getDistrictMRCount() {
        return districtMRCount;
    }

    /**
     * @param districtmrcount the districtmrcount to set
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
     * @return the fathername
     */
    public String getFathername() {
        return fathername;
    }

    /**
     * @param fathername the fathername to set
     */
    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    /**
     * @return the pincode
     */
    public String getPincode() {
        return pincode;
    }

    /**
     * @param pincode the pincode to set
     */
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    /**
     * @return the dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the maritalStatus
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * @param maritalStatus the maritalStatus to set
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * @return the education
     */
    public String getEducation() {
        return education;
    }

    /**
     * @param education the education to set
     */
    public void setEducation(String education) {
        this.education = education;
    }
    
}
