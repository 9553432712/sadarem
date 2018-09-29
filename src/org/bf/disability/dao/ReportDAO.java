/*
 * ReportDAO.java
 *
 * Created on July 15, 2008, 3:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.bf.disability.Constants.CertificateConstants;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.dto.CertificateDTO;
import org.bf.disability.dto.ReportDTO;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.form.TerritoryForm;

import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is for getting details for certificate and identity card from database
 * @author by Bapinaidu
 * @version 1.0
 */
public class ReportDAO
{
    /*
     * This method is used to get required data
     *  for certificate from database
     *
     */

	
    /**
     * 
     * @param datasource 
     * @param territoryForm 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
	String lStrQuery ;
    public ArrayList getDetailsForCertificate(DataSource ds,
            TerritoryForm territoryForm,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList reportlist = new ArrayList();
        TerritoryDTO territorydto = null;
        Connection con = null;
        //Statement stmt = null;
        CallableStatement cstmt = null;
        //Statement stmt2=null;
        ResultSet rs = null;
        String[] cause = new String[10];
        String numberInword = null;
        String maxDisability = null;
        List causeofDisabilityList = null;
        List locomotorSubDisabilityList = null;
        List locomotorSubSubDisabilityList = null;
        List locomotorarmlegeffectedList = null;
        List locomotorphysicalrequirements = null;
        String disabilitycondition = null;
        String numberInwords = null;
        String FirstName = null;
        String Surname = null;
        String Name = null;
        String relationName = null;
        String disabilityreason = null;
        String hospitalName = null;
        String hospitalAddress = null;
        String firstdoctorname = null;
        String seconddoctorname = null;
        String thirddoctorname = null;
        String rightEarDB = null;
        String leftEarDB = null;
        String districtName = null;
        String mandalName = null;
        String vilageName = null;
        String habitaionName = null;
        boolean railwaycertificate;
        PreparedStatement cs = null;
        try {
            causeofDisabilityList = new ArrayList();
            locomotorSubDisabilityList = new ArrayList();
            locomotorphysicalrequirements = new ArrayList();
            con = DBConnection.getConnection();

            cstmt = con.prepareCall("{Call SP_SELECTDATAFORCERTIFICATES_NEW(?)}");
            cstmt.setString(1, personcode);
            rs = cstmt.executeQuery();
            if (rs.next()) {
                territorydto = new TerritoryDTO();

                territorydto.setPersoncode(rs.getString("Person_Code"));
                FirstName = convertFirstLetterToUpperCase(rs.getString("First_Name"));
                Surname = convertFirstLetterToUpperCase(rs.getString("Surname"));
                
                territorydto.setCategory(rs.getString("CategoryID"));
                
                if(Surname != null )                //Surname is not mandatory.So in order to avoid null values from db this check is given
                {
                  Name = Surname + " " + FirstName;
                }
                else
                {
                	Name = FirstName;
                }
                if (null != Name && !"".equals(Name)) {
                    territorydto.setName(Name);
                }
                if (rs.getString("Gender").equals("1")) {
                    territorydto.setGender("Male");
                    territorydto.setGenderInitial1("He");
                    territorydto.setGenderInitial2("his");
                    territorydto.setGenderInTelugu(CertificateConstants.MALE_TELUGU);
                    territorydto.setMaritialstatus("Shri");
                    int Relationship = rs.getInt("Relationship");
                    switch (Relationship) {
                    
                     case 0: territorydto.setRelationship(CertificateConstants.CARE_OF);
                         break;
		             case 1:
		            	 territorydto.setRelationship(CertificateConstants.SON_OF);
		                 break;
		             case 2:
		            	 territorydto.setRelationship(CertificateConstants.GAURDIAN_OF);
		                 break;
		             
		             case 3:
		            	 territorydto.setRelationship(CertificateConstants.HUSBAND_OF);
		                 break;
		             default:
		                 break;
                       
                    }
                } else if (rs.getString("Gender").equals("2")) {
                    territorydto.setGender("Female");
                    territorydto.setGenderInTelugu(CertificateConstants.FEMALE_TELUGU);
                    territorydto.setGenderInitial1("She");
                    territorydto.setGenderInitial2("her");
                    if (rs.getString("Marital_Status").equals("1")) {
                        territorydto.setMaritialstatus("Smt");
                    } else if (rs.getString("Marital_Status").equals("2")) {
                        territorydto.setMaritialstatus("Kumari");
                    } else if (rs.getString("Marital_Status").equals("3")
                            || rs.getString("Marital_Status").equals("4")
                            || rs.getString("Marital_Status").equals("5")) {
                        territorydto.setMaritialstatus("Ms");
                    }
                    int Relationship = rs.getInt("Relationship");
                    switch (Relationship)
                    {
	                 case 0:territorydto.setRelationship(CertificateConstants.CARE_OF);
	                    break;
		             case 1:
		            	 territorydto.setRelationship(CertificateConstants.DAUGHTER_OF);
		                 break;
		             case 2:
		            	 territorydto.setRelationship(CertificateConstants.GAURDIAN_OF);
		                 break;
		             case 3:
		            	 territorydto.setRelationship(CertificateConstants.WIFE_OF);
		                 break;
		              default:
		                 break;
                    }
                }
                String dob = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd ").parse(rs.getString("Date_of_Birth")));
                territorydto.setDateofbirth(dob);
                territorydto.setAge(rs.getString("Age_Years"));
                territorydto.setMoleone(rs.getString("Mole_One"));
                territorydto.setMoletwo(rs.getString("Mole_Two"));

                relationName = convertFirstLetterToUpperCase(rs.getString("Relation_Name"));
                if (null != relationName && !"".equals(relationName)) {
                    territorydto.setRelationname(relationName);
                }
                territorydto.setHousenumber(rs.getString("House_Number"));
                territorydto.setTelugupersonname(rs.getString("Personname_Telugu"));
                territorydto.setTelugufathername(rs.getString("Fathername_Telugu"));
                territorydto.setTypeofdisability(rs.getString("Disability_ID"));
                territorydto.setFlag(rs.getString("flag"));
                //newly changed by bapinaidu for perfomance nad removed unnessasary code.

                int disabilityid = rs.getInt("Disability_ID");
                switch (disabilityid) {
                    case 1:
                        territorydto = locomotorDetails(rs, territorydto);
                        break;
                    case 2:
                        if (rs.getFloat("VisualImpairment") != 0) {
                            territorydto.setVisualImpairmentPercentage(Math.round(rs.getFloat("VisualImpairment")));
                            territorydto = visualDetails(rs, territorydto);
                        }
                        break;

                    case 3:
                        territorydto = hearingDetails(rs, territorydto);
                        break;
                    case 4:
                        territorydto = mentalRetardationDetails(rs, territorydto);
                        break;
                    case 5:
                        territorydto = mentalIllnessDetails(rs, territorydto);
                        break;
                    case 6:
                        int multipledisabilityid = rs.getInt("Multiple_Disability_Sub_ID");
                        territorydto.setComment("yes");
                        List multipleDisabiliPhyscicalRequirementsList = new ArrayList();
                        territorydto.setMultipleRadio(String.valueOf(multipledisabilityid));
                        if (rs.getFloat("TotalLocomotor") != 0) {
                            territorydto.setPhysicalImpairmentPercentage(Math.round(rs.getFloat("TotalLocomotor")));
                            territorydto = locomotorDetails(rs, territorydto);
                            multipleDisabiliPhyscicalRequirementsList.add(territorydto.getPhysicalImpairmentPhysicalRequirementList());
                        }
                        if (rs.getFloat("HearingImpairment") != 0) {
                            territorydto.setHearingImpairmentPercentage(Math.round(rs.getFloat("HearingImpairment")));
                            territorydto = hearingDetails(rs, territorydto);
                            multipleDisabiliPhyscicalRequirementsList.add(territorydto.getHearingImpairmentPhysicalRequirementList());
                        }
                        if (rs.getFloat("VisualImpairment") != 0) {
                            territorydto.setVisualImpairmentPercentage(Math.round(rs.getFloat("VisualImpairment")));
                            territorydto = visualDetails(rs, territorydto);
                            multipleDisabiliPhyscicalRequirementsList.add(territorydto.getVisualImpairmentPhysicalRequirementList());
                        }
                        if (rs.getFloat("MentalRetardation") != 0) {
                            territorydto.setMentalRetardationPercentage(Math.round(rs.getFloat("MentalRetardation")));
                            territorydto = mentalRetardationDetails(rs, territorydto);
                            multipleDisabiliPhyscicalRequirementsList.add(territorydto.getMentalRetardationPhysicalRequirementList());
                        }
                        if (rs.getFloat("MentalIllness") != 0) {
                            territorydto.setMentalIllnessPercentage(Math.round(rs.getFloat("MentalIllness")));
                            territorydto = mentalIllnessDetails(rs, territorydto);
                        }
                        if (multipleDisabiliPhyscicalRequirementsList != null && !multipleDisabiliPhyscicalRequirementsList.isEmpty()) {
                            String multipleDisabiliPhyscicalRequirements = separatewithcammamultiple(multipleDisabiliPhyscicalRequirementsList);
                            territorydto.setMultipleDisabilityPhysicalRequirementList(multipleDisabiliPhyscicalRequirements);
                        }
                        territorydto.setDisabilitytype(CertificateConstants.MULTIPLE_DISABILITYTYPE);
                        territorydto.setDisabilityTypeInIdCard(CertificateConstants.MULTIPLE_DISABILITYTYPE);
                        territorydto.setDisabilityTypeInTelugu(CertificateConstants.MULTIPLE_DISABILITYTYPE_TELUGU);
                        break;
                    default:
                        break;
                }
                disabilityreason = convertFirstLetterToUpperCase(rs.getString("Diagnosis_of_Disability"));
                if (null != disabilityreason && !"".equals(disabilityreason)) {
                    territorydto.setDisabilityreason(disabilityreason);
                }
                hospitalName = convertFirstLetterToUpperCase(rs.getString("Hospital_Name"));
                if (null != hospitalName && !"".equals(hospitalName)) {
                    territorydto.setHospitalname(hospitalName);
                }
                hospitalAddress = convertFirstLetterToUpperCase(rs.getString("Hospital_Address"));
                if (null != hospitalAddress && !"".equals(hospitalAddress)) {
                    territorydto.setHospitaladdress(hospitalAddress);
                }
                firstdoctorname = convertFirstLetterToUpperCase(rs.getString("First_Doctor_Name"));
                if (null != firstdoctorname && !"".equals(firstdoctorname)) {
                    territorydto.setFirstdoctorname(firstdoctorname);
                }
                territorydto.setFirstdoctornumber(rs.getString("First_Doctor_RegNumber"));
                territorydto.setFirstdesgination(rs.getString("First_Doctor_Designation"));
                seconddoctorname = convertFirstLetterToUpperCase(rs.getString("Second_Doctor_Name"));
                if (null != seconddoctorname && !"".equals(seconddoctorname)) {
                    territorydto.setSeconddoctorname(seconddoctorname);
                }
                territorydto.setSeconddoctornumber(rs.getString("Second_Doctor_RegNumber"));
                territorydto.setSeconddesgination(rs.getString("Second_Doctor_Designation"));
                thirddoctorname = convertFirstLetterToUpperCase(rs.getString("Third_Doctor_Name"));
                if (null != thirddoctorname && !"".equals(thirddoctorname)) {
                    territorydto.setThirddoctorname(thirddoctorname);
                }
                territorydto.setThirddoctornumber(rs.getString("Third_Doctor_RegNumber"));
                territorydto.setThirddesgination(rs.getString("Third_Doctor_Designation"));

                if (rs.getString("Condition_of_Disability") != null && !rs.getString("Condition_of_Disability").equals("") && rs.getString("Condition_of_Disability").equals("1")) {
                    territorydto.setValidity(CertificateConstants.VALIDITY_PERMANENT);
                    if ("idcard".equals(territoryForm.getDifferenceFlag()) || "idcardprint".equals(territoryForm.getDifferenceFlag())) {
                        territorydto.setPeriod(CertificateConstants.LIFETIME_PERIOD);
                    }
                    territorydto.setKindofdisability(CertificateConstants.VALIDITY_NOTRECOMENDED);
                    territorydto.setDisabilityduration(CertificateConstants.DISABILITY_DURATION_PERMANENT_TELUGU);
                    territorydto.setTeluguDisabilityName(CertificateConstants.TELUGU_DISABILITY_NAME_PERMANENT);
                }
                if (rs.getString("Condition_of_Disability") != null && !rs.getString("Condition_of_Disability").equals("") && rs.getString("Condition_of_Disability").equals("2")) {
                    territorydto.setValidity(CertificateConstants.VALIDITY_PERMANENT);
                    if ("idcard".equals(territoryForm.getDifferenceFlag()) || "idcardprint".equals(territoryForm.getDifferenceFlag())) {
                        territorydto.setPeriod(CertificateConstants.LIFETIME_PERIOD);
                    }
                    territorydto.setKindofdisability(CertificateConstants.VALIDITY_NOTRECOMENDED);
                    territorydto.setDisabilityduration(CertificateConstants.DISABILITY_DURATION_PERMANENT_TELUGU);
                    territorydto.setTeluguDisabilityName(CertificateConstants.TELUGU_DISABILITY_NAME_PERMANENT);
                }
                if (rs.getString("Condition_of_Disability") != null && !rs.getString("Condition_of_Disability").equals("") && rs.getString("Condition_of_Disability").equals("3")) {
                    territorydto.setValidity(CertificateConstants.VALIDITY_TEMPORARY);
                    territorydto.setTeluguDisabilityName(CertificateConstants.TELUGU_DISABILITY_NAME_TEMPARARY);
                    territorydto.setKindofdisability("recommended after a period of");
                    int yearsoftemporary = rs.getInt("Years_for_Temparary");
                    switch (yearsoftemporary) {
                        case 1:
                            territorydto.setPeriod("1 year.");
                            territorydto.setDisabilityduration(CertificateConstants.ONE_YEAR_TELUGU);
                            break;
                        case 2:
                            territorydto.setPeriod("2 years.");
                            territorydto.setDisabilityduration(CertificateConstants.TWO_YEARS_TELUGU);
                            break;
                        case 3:
                            territorydto.setPeriod("3 years.");
                            territorydto.setDisabilityduration(CertificateConstants.THREE_YEARS_TELUGU);
                            break;
                        case 4:
                            territorydto.setPeriod("4 years.");
                            territorydto.setDisabilityduration(CertificateConstants.FOUR_YEARS_TELUGU);
                            break;
                        case 5:
                            territorydto.setPeriod("5 years.");
                            territorydto.setDisabilityduration(CertificateConstants.FIVE_YEARS_TELUGU);
                            break;
                    }
                }
                if (rs.getInt("Disability_ID") != 2) {
                    if (rs.getString("Congenital") != null && rs.getString("Congenital").equals("1")) {
                        causeofDisabilityList.add(CertificateConstants.CONGENITAL);
                    }
                    if (rs.getString("Hereditary") != null && rs.getString("Hereditary").equals("1")) {
                        causeofDisabilityList.add(CertificateConstants.HEREDITARY);
                    }
                    if (rs.getString("BirthInjury") != null && rs.getString("BirthInjury").equals("1")) {
                        causeofDisabilityList.add(CertificateConstants.BIRTHINJURY);
                    }
                    if (rs.getString("Malnutrition") != null && rs.getString("Malnutrition").equals("1")) {
                        causeofDisabilityList.add(CertificateConstants.MALNUTRITION);
                    }
                    if (rs.getString("Disease") != null && rs.getString("Disease").equals("1")) {
                        causeofDisabilityList.add(CertificateConstants.DISEASEANDINFECTION);
                    }
                    if (rs.getString("Accident") != null && rs.getString("Accident").equals("1")) {
                        causeofDisabilityList.add(CertificateConstants.ACCIDENT);
                    }
                    if (rs.getString("High_Fever") != null && !"".equals(rs.getString("High_Fever"))
                            && rs.getString("High_Fever").equals("1")) {
                        causeofDisabilityList.add(CertificateConstants.MENINGITIS);
                    }
                    if (rs.getString("Epilepsy") != null && !"".equals(rs.getString("Epilepsy"))
                            && rs.getString("Epilepsy").equals("1")) {
                        causeofDisabilityList.add(CertificateConstants.EPILEPSY);
                    }

                    if (rs.getString("Birth_Asphyxia") != null && !"".equals(rs.getString("Birth_Asphyxia"))
                            && rs.getString("Birth_Asphyxia").equals("1")) {
                        causeofDisabilityList.add(CertificateConstants.BIRTHASPHYXIA);
                    }
                    if (rs.getString("Other_Cause") != null && !"".equals(rs.getString("Other_Cause"))) {
                        causeofDisabilityList.add(rs.getString("Other_Cause"));
                    }

                }
                if (rs.getInt("Disability_ID") == 2 || (rs.getInt("Disability_ID") == 6
                        && rs.getFloat("VisualImpairment") != 0)) {
                    // added for VisuallImpairment Certificate
                    if (rs.getString("Congenital_BetterEye") != null && !"".equals(rs.getString("Congenital_BetterEye"))
                            && rs.getString("Congenital_BetterEye").equals("1")) {
                        causeofDisabilityList.add(CertificateConstants.CONGENITAL_BETTEREYE);
                    }
                    if (rs.getString("Congenital_WorseEye") != null && !"".equals(rs.getString("Congenital_WorseEye"))
                            && rs.getString("Congenital_WorseEye").equals("1")) {
                        causeofDisabilityList.add(CertificateConstants.CONGENITAL_WORSEEYE);
                    }
                    if (rs.getString("Hereditary_BetterEye") != null && !"".equals(rs.getString("Hereditary_BetterEye"))
                            && rs.getString("Hereditary_BetterEye").equals("1")) {
                        causeofDisabilityList.add(CertificateConstants.HEREDITARY_BETTEREYE);
                    }
                    if (rs.getString("Hereditary_WorseEye") != null && !"".equals(rs.getString("Hereditary_WorseEye"))
                            && rs.getString("Hereditary_WorseEye").equals("1")) {
                        causeofDisabilityList.add(CertificateConstants.HEREDITARY_WORSEEYE);
                    }
                    if (rs.getString("BirthInjury_BetterEye") != null && !"".equals(rs.getString("BirthInjury_BetterEye"))
                            && rs.getString("BirthInjury_BetterEye").equals("1")) {
                        causeofDisabilityList.add(CertificateConstants.BIRTHINJURY_BETTEREYE);
                    }
                    if (rs.getString("BirthInjury_WorseEye") != null && !"".equals(rs.getString("BirthInjury_WorseEye"))
                            && rs.getString("BirthInjury_WorseEye").equals("1")) {
                        causeofDisabilityList.add(CertificateConstants.BIRTHINJURY_WORSEEYE);
                    }

                    if (rs.getString("Disease_BetterEye") != null && !"".equals(rs.getString("Disease_BetterEye"))
                            && rs.getString("Disease_BetterEye").equals("1")) {
                        causeofDisabilityList.add(CertificateConstants.DISEASEANDINFECTION_BETTEREYE);
                    }
                    if (rs.getString("Disease_WorseEye") != null && !"".equals(rs.getString("Disease_WorseEye"))
                            && rs.getString("Disease_WorseEye").equals("1")) {
                        causeofDisabilityList.add(CertificateConstants.DISEASEANDINFECTION_WORSEEYE);
                    }
                    if (rs.getString("Malnutrition_BetterEye") != null && !"".equals(rs.getString("Malnutrition_BetterEye"))
                            && rs.getString("Malnutrition_BetterEye").equals("1")) {
                        causeofDisabilityList.add(CertificateConstants.MALNUTRITION_BETTEREYE);
                    }
                    if (rs.getString("Malnutrition_WorseEye") != null && !"".equals(rs.getString("Malnutrition_WorseEye"))
                            && rs.getString("Malnutrition_WorseEye").equals("1")) {
                        territorydto.setMalnutrition_WorseEye(CertificateConstants.MALNUTRITION_WORSEEYE);
                    }
                    if (rs.getString("Accident_BetterEye") != null && !"".equals(rs.getString("Accident_BetterEye"))
                            && rs.getString("Accident_BetterEye").equals("1")) {
                        causeofDisabilityList.add(CertificateConstants.ACCIDENT_BETTEREYE);
                    }
                    if (rs.getString("Accident_WorseEye") != null && !"".equals(rs.getString("Accident_WorseEye"))
                            && rs.getString("Accident_WorseEye").equals("1")) {
                        causeofDisabilityList.add(CertificateConstants.ACCIDENT_WORSEEYE);
                    }
                    if (rs.getString("Other_Cause") != null && !"".equals(rs.getString("Other_Cause"))) {
                        causeofDisabilityList.add(rs.getString("Other_Cause"));
                    }
                }
                if (causeofDisabilityList != null && !causeofDisabilityList.isEmpty()) {
                    String totalcauseofDisability = separatewithcamma(causeofDisabilityList);
                    territorydto.setTotalcauseofdisabilities(totalcauseofDisability);
                }
                int number = new Double((rs.getDouble("TotalDisability"))).intValue();
                numberInwords = NumbertoWord(number);
                if (numberInwords != null && !"".equals(numberInwords)) {
                    territorydto.setPercentageInWord(numberInwords + " " + "percent");
                }
                territorydto.setDisabilityPercentage(number);
                territorydto.setDisabilityvalue(rs.getDouble("TotalDisability"));
                if (null != rs.getString("Global_Score") && !"0".equals(rs.getString("Global_Score"))) {
                    territorydto.setGlobal_Disablity(rs.getString("Global_Score"));
                    String GDInwords = NumbertoWord(Integer.parseInt(territorydto.getGlobal_Disablity()));
                    territorydto.setGlobal_Disablity_InWords(GDInwords);
                }
                if (null != rs.getString("Mental_Age") && !"0".equals(rs.getString("Mental_Age"))) {
                    String roundedMentalretardation = String.valueOf(Math.round(Double.parseDouble(rs.getString("Mental_Age"))));
                    territorydto.setMentalRetardation_IQ(roundedMentalretardation);
                    String mentalRetardationInwords = NumbertoWord(Integer.parseInt(roundedMentalretardation));
                    if (null != mentalRetardationInwords && !"".equals(mentalRetardationInwords)) {
                        territorydto.setMentalRetardation_IQ_Inwords(mentalRetardationInwords);
                    }
                }
                if (null != rs.getString("AirConduction_Left_Ear_DB_Level") && null != rs.getString("AirConduction_Right_Ear_DB_Level")) {
                    rightEarDB = rs.getString("AirConduction_Right_Ear_DB_Level");
                    leftEarDB = rs.getString("AirConduction_Left_Ear_DB_Level");
                    if ((null != rightEarDB && !"".equals(rightEarDB)) && (null != leftEarDB && !"".equals(leftEarDB))) {
                        if (Integer.parseInt(rightEarDB) <= Integer.parseInt(leftEarDB)) {
                            territorydto.setHearing_DB(rightEarDB);
                        } else if (Integer.parseInt(leftEarDB) <= Integer.parseInt(rightEarDB)) {
                            territorydto.setHearing_DB(leftEarDB);
                        }
                        String dbInwords = NumbertoWord(Integer.parseInt(territorydto.getHearing_DB()));
                        if (null != dbInwords && !"".equals(dbInwords)) {
                            territorydto.setHearing_DB_In_Words(dbInwords);
                        }
                    }
                }
                if (rs.getString("Created_Date") != null) {
                    String formdate = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd ").parse(rs.getString("Created_Date")));
                    territorydto.setDateofisse(formdate);
                }
                districtName = convertFirstLetterToUpperCase(rs.getString("District_Name"));
                if (null != districtName && !"".equals(districtName)) {
                    territorydto.setDistrict_name(districtName);
                }
                mandalName = convertFirstLetterToUpperCase(rs.getString("Mandal_Name"));
                if (null != mandalName && !"".equals(mandalName)) {
                    territorydto.setMandal_name(mandalName);
                }
                vilageName = convertFirstLetterToUpperCase(rs.getString("Village_Name"));
                if (null != vilageName && !"".equals(vilageName)) {
                    territorydto.setVillage_name(vilageName);
                }
                habitaionName = convertFirstLetterToUpperCase(rs.getString("Habitation_Name"));
                if (null != habitaionName && !"".equals(habitaionName)) {
                    territorydto.setHabitation_name(habitaionName);
                }
                rs.close();
                if ("Physical(Locomotor/Orthopaedic) Disability".equals(territorydto.getDisabilitytype())
                        || "Physical(Locomotor/Orthopaedic) Disability".equals(territorydto.getPhysicalImpairmentName())) {

                    cstmt = con.prepareCall("{Call SP_SELECTDATAFORLOCOMOTORCERTIFICATE(?)}");
                    cstmt.setString(1, personcode);
                    rs = cstmt.executeQuery();
                    while (rs.next()) {
                        locomotorarmlegeffectedList = new ArrayList();
                        if (rs.getInt("LIFTING_OVERHEAD_OBJECTS") == 9 || rs.getInt("TOUCHING_NOSE") == 9 || rs.getInt("EATINGSTYLE") == 9
                                || rs.getInt("COMBINGANDPLAITING") == 9 || rs.getInt("PUTTING_ON_A_SHIRT") == 9 || rs.getInt("ABLUTIONGLASS") == 9
                                || rs.getInt("BUTTONING") == 9 || rs.getInt("TIENARADHOTI") == 9 || rs.getInt("WRITING") == 9 || rs.getInt("DRINKINGWATER") == 9
                                || rs.getInt("WORKING_ON_PLANE") == 5 || rs.getInt("WORKING_ON_PLANE") == 10 || rs.getInt("WALKING_ON_SLOPE") == 5 || rs.getInt("WALKING_ON_SLOPE") == 10
                                || rs.getInt("CLIMBING_STAIRS") == 5 || rs.getInt("CLIMBING_STAIRS") == 10 || rs.getInt("STANDING_BOTHLEGS") == 5 || rs.getInt("STANDING_BOTHLEGS") == 10
                                || rs.getInt("STANDING_EFFECTED_LEG") == 5 || rs.getInt("STANDING_EFFECTED_LEG") == 10 || rs.getInt("SQUATTING_FLOOR") == 5 || rs.getInt("SQUATTING_FLOOR") == 10
                                || rs.getInt("SITTINGCROSS_LEG") == 5 || rs.getInt("SITTINGCROSS_LEG") == 10 || rs.getInt("KNEELING") == 5 || rs.getInt("KNEELING") == 10
                                || rs.getInt("TAKING_TURNS") == 5 || rs.getInt("TAKING_TURNS") == 10) {
                            locomotorarmlegeffectedList.add("Impaired reach");
                        }
                        if (rs.getInt("STRENGTH_GRIPSTRENGTH_RIGHTSIDE") == 20 || rs.getInt("STRENGTH_GRIPSTRENGTH_LEFTSIDE") == 20
                                || rs.getInt("STRENGTH_PINCHSTRENGTH_RIGHTSIDE") == 10 || rs.getInt("STRENGTH_PINCHSTRENGTH_LEFTSIDE") == 10) {
                            locomotorarmlegeffectedList.add("Weakness of grip");
                        }
                        if (rs.getInt("SEVERITYOF_ATAXIA") == 25 || rs.getInt("SEVERITYOF_ATAXIA") == 50 || rs.getInt("SEVERITYOF_ATAXIA") == 75
                                || rs.getInt("SEVERITYOF_ATAXIA") == 100) {
                            locomotorarmlegeffectedList.add("Ataxia");
                        }
                        if (locomotorarmlegeffectedList != null && !locomotorarmlegeffectedList.isEmpty()) {
                            String totallocomotorarmlegeffectedlist = separatewithcamma(locomotorarmlegeffectedList);
                            territorydto.setTotallocomotorarmlegeffectedlist(totallocomotorarmlegeffectedlist);
                        }
                    }
                }

                reportlist.add(territorydto);
            }
            /*  cs = con.prepareStatement("select Railway_Certificate  from tblAllDisabilityPerson_Common_Needs where Person_Code='" + personcode + "'");
            rs = cs.executeQuery();
            while (rs.next()) {
            territorydto.setRailwaycertificate(rs.getBoolean("Railway_Certificate"));
            }*/
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDetailsForCertificate", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getDetailsForCertificate");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDetailsForCertificate", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getDetailsForCertificate");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

            DBConnection.closeStatement(cs);
        }
        return reportlist;
    }

    /*
     * This method will get the required data
     *  for identity card from database
     **/
    /**
     * 
     * @param datasource 
     * @param territoryForm 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getDetaiilsForIdCard(DataSource ds,
            TerritoryForm territoryForm,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList cardlist = new ArrayList();
        TerritoryDTO territorydto = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String[] cause = new String[10];
        String Name = null;
        String maxDisability = null;
        try {
            con = DBConnection.getConnection();
            String strQuery = "select PP.Person_Code,PP.Gender,PP.First_Name,PP.Surname ,PD.Disability_ID,PT.TotalDisability,"
                    + "PP.House_Number,PP.Relation_Name,PT.Created_Date,PP.Date_of_Birth,PP.Mole_one,PP.Mole_Two,PD.First_Doctor_Name,"
                    + "PD.First_Doctor_RegNumber,PD.First_Doctor_Designation,PD.Second_Doctor_Name,PD.Second_Doctor_RegNumber,"
                    + "PD.Second_Doctor_Designation,PD.Third_Doctor_Name,PD.Third_Doctor_RegNumber,PD.Third_Doctor_Designation,"
                    + "PT.Multiple_Disability_Sub_ID,PC.Condition_Of_Disability,PT.VisualImpairment from tblPerson_Personal_Details PP  with (nolock) ,"
                    + "tblPerson_Causes_of_Disability_Details PC with(nolock) ,tblVillage_Details V with(nolock) ,tblPerson_Disability_Details PD with(nolock) ,"
                    + "tblPerson_Disability_TotalValue_Details PT with(nolock)  where V.Village_Id=PP.Village_Id and  V.Mandal_Id=PP.Mandal_Id and "
                    + "V.District_Id=PP.District_Id and PP.Person_Code= PC.Person_Code and PP.Person_Code= PD.Person_Code and "
                    + "PP.Person_Code= PT.Person_Code and PP.Person_Code=?";
            pstmt = con.prepareStatement(strQuery);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                territorydto = new TerritoryDTO();
                territorydto.setPersoncode(rs.getString("Person_Code"));
                if (rs.getString(2).equals("1")) {
                    territorydto.setGender("Male");
                } else {
                    territorydto.setGender("Female");
                }
                territorydto.setRelationname(convertFirstLetterToUpperCase(rs.getString("Relation_Name")));
                String FirstName = convertFirstLetterToUpperCase(rs.getString("First_Name"));
                //String LastName=convertFirstLetterToUpperCase(rs.getString("Last_Name"));
                String Surname = convertFirstLetterToUpperCase(rs.getString("Surname"));
//                String Name1= (FirstName.concat(LastName));
//                String Name= (Surname.concat(Name1));
                Name = Surname + " " + FirstName;
                int namelength = Name.length();


                boolean brFlag = false;
                if (Name.length() <= 28) {
                    brFlag = true;
                    for (int i = 0; i <= (28 - namelength); i++) {
                        Name = Name + " ";
                    }

                }

                territorydto.setName(Name);
                territorydto.setBrFlag(brFlag);
                if (rs.getInt(5) == 1) {
                    territorydto.setDisabilitytype("Physical");
                } else if (rs.getInt(5) == 2) {
                    if (Math.round(rs.getFloat("VisualImpairment")) != 0) {
                        if (Math.round(rs.getFloat("VisualImpairment")) == 40) {
                            territorydto.setDisabilitytype("Visual Low Vision");
                        } else if (Math.round(rs.getFloat("VisualImpairment")) == 75 || Math.round(rs.getFloat("VisualImpairment")) == 100 || Math.round(rs.getFloat("VisualImpairment")) == 30) {
                            territorydto.setDisabilitytype("Visual Blind ness");
                        }
                    }
                } else if (rs.getInt(5) == 3) {
                    territorydto.setDisabilitytype("Hearing Impairment");
                } else if (rs.getInt(5) == 4) {
                    territorydto.setDisabilitytype("Mental Retardation");
                } else if (rs.getInt(5) == 5) {
                    territorydto.setDisabilitytype("Mental Illness");
                } else if (rs.getInt(5) == 6) {

                    territorydto.setDisabilitytype("Multiple Disabilities");
//                    if(rs.getInt(22)==1)
//                    territorydto.setDisabilitytype("Physical");
//                else if(rs.getInt(22)==2)
//                    territorydto.setDisabilitytype("Visual");
//                else if(rs.getInt(22)==3)
//                    territorydto.setDisabilitytype("Hearing Impairment");
//                else if(rs.getInt(22)==4)
//                    territorydto.setDisabilitytype("Mental Retardation");
//                else if(rs.getInt(22)==5)
//                    territorydto.setDisabilitytype("Mental Illness");
                }


                territorydto.setDisabilityvalue(rs.getDouble(6));
                territorydto.setHousenumber(rs.getString(7));
                territorydto.setRelationname(convertFirstLetterToUpperCase(rs.getString(8)));
                // converting date into required date formate(dd/mm/yyyy)
                String formdate = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd ").parse(rs.getString(9)));
                territorydto.setDateofisse(formdate);
                String dob = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd ").parse(rs.getString(10)));
                territorydto.setDateofbirth(dob);
                territorydto.setMoleone(convertFirstLetterToUpperCase(rs.getString(11)));
                territorydto.setMoletwo(convertFirstLetterToUpperCase(rs.getString(12)));

                territorydto.setFirstdoctorname(convertFirstLetterToUpperCase(rs.getString(13)));
                territorydto.setFirstdoctornumber(rs.getString(14));
                territorydto.setFirstdesgination(rs.getString(15));
                territorydto.setSeconddoctorname(convertFirstLetterToUpperCase(rs.getString(16)));
                territorydto.setSeconddoctornumber(rs.getString(17));
                territorydto.setSeconddesgination(rs.getString(18));
                territorydto.setThirddoctorname(convertFirstLetterToUpperCase(rs.getString(19)));
                territorydto.setThirddoctornumber(rs.getString(20));
                territorydto.setThirddesgination(rs.getString(21));

                if (rs.getString(23).equals("1") || rs.getString(23).equals("2")) {
                    territorydto.setValidity("Permanent");
                } else {
                    territorydto.setValidity("Temporary");
                }

                cardlist.add(territorydto);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDetaiilsForIdCard", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getDetaiilsForIdCard");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDetaiilsForIdCard", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getDetaiilsForIdCard");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return cardlist;
    }
    /*
     * This method will get the Telugufields
     *  for particular Pwd from database
     **/

    /**
     * 
     * @param datasource 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public TerritoryDTO getTeluguName(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        TerritoryDTO territorydto = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();

            String strQuery = "select P.Personname_Telugu,P.Fathername_Telugu,P.Gender,PD.Disability_ID,D.District_Name,M.Mandal_Name,"
                    + "V.Village_Name,H.Habitation_Name,PD.Diagnosis_of_Disability,PC.Condition_Of_Disability ,PC.Years_for_Temparary,"
                    + "PT.Multiple_Disability_Sub_ID,PT.VisualImpairment from tblPerson_Personal_Details P  with (nolock) ,tblPerson_Disability_Details PD with(nolock) ,"
                    + "tblDistrict_Details D with(nolock) ,tblMandal_Details M with(nolock) ,tblVillage_Details V with(nolock) ,tblHabitation_Details H with(nolock) ,"
                    + "tblPerson_Causes_of_Disability_Details PC with(nolock) ,tblPerson_Disability_TotalValue_Details PT with(nolock)   where"
                    + " P.Person_Code = PC.Person_Code and P.Person_Code = PD.Person_Code and P.District_ID = D.District_ID and "
                    + "P.Mandal_ID = M.Mandal_ID and P.District_ID = M.District_ID and P.Village_ID = V.Village_ID and "
                    + "P.District_ID = V.District_ID and P.Mandal_ID = V.Mandal_ID and P.Habitation_ID = H.Habitation_ID and"
                    + " P.District_ID = H.District_ID and P.Mandal_ID = H.Mandal_ID and P.Village_ID = H.Village_ID and"
                    + " P.Person_Code= PT.Person_Code and  P.Person_Code =?";
            pstmt = con.prepareStatement(strQuery);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {

                territorydto = new TerritoryDTO();
                territorydto.setTelugupersonname(rs.getString(1));

                territorydto.setTelugufathername(rs.getString(2));
                if (rs.getString(3).equals("1")) {
                    territorydto.setGender("&#3114;&#3137;&#3120;&#3137;&#3127;&#3137;&#3105;&#3137; ");
                } else {
                    territorydto.setGender("&#3128;&#3149;&#3108;&#3149;&#3120;&#3136;");
                }
                if (rs.getString(4).equals("1")) {
                    territorydto.setDisabilitytype("&#3126;&#3134;&#3120;&#3136;&#3120;&#3093; &#3125;&#3135;&#3093;&#3122;&#3134;&#3074;&#3095;&#3108;&#3149;&#3125;&#3074;");
                } else if (rs.getString(4).equals("2")) {
                    if (Math.round(rs.getFloat("VisualImpairment")) != 0) {
                        if (Math.round(rs.getFloat("VisualImpairment")) == 40) {
                            territorydto.setDisabilitytype("&#3129;&#3149;&#3120;&#3128;&#3149;&#3125; &#3110;&#3139;&#3127;&#3149;&#3103;&#3135;");
                        } else if (Math.round(rs.getFloat("VisualImpairment")) == 75 || Math.round(rs.getFloat("VisualImpairment")) == 100 || Math.round(rs.getFloat("VisualImpairment")) == 30) {
                            territorydto.setDisabilitytype("&#3110;&#3139;&#3127;&#3149;&#3103;&#3135;&#3122;&#3147;&#3114;&#3074;");
                        }
                    }
                } else if (rs.getString(4).equals("3")) {
                    territorydto.setDisabilitytype("&#3125;&#3135;&#3112;&#3135;&#3093;&#3135;&#3105;&#3135; &#3122;&#3147;&#3114;&#3074; ");
                } else if (rs.getString(4).equals("4")) {
                    territorydto.setDisabilitytype("&#3116;&#3137;&#3110;&#3149;&#3110;&#3135; &#3118;&#3134;&#3112;&#3149;&#3119;&#3108; ");
                } else if (rs.getString(4).equals("5")) {
                    territorydto.setDisabilitytype("&#3118;&#3134;&#3112;&#3128;&#3135;&#3093; &#3083;&#3095;&#3149;&#3118;&#3108;");
                } else if (rs.getString(4).equals("6")) {
                    territorydto.setDisabilitytype("&#3116;&#3129;&#3137; &#3125;&#3135;&#3093;&#3122;&#3134;&#3074;&#3095;&#3108;&#3149;&#3125;&#3074;");
//                    if(rs.getString(12).equals("1"))
//                    territorydto.setDisabilitytype("&#3126;&#3134;&#3120;&#3136;&#3120;&#3093; &#3125;&#3135;&#3093;&#3122;&#3134;&#3074;&#3095;&#3108;&#3149;&#3125;&#3074;");
//                else if(rs.getString(12).equals("2"))
//                    territorydto.setDisabilitytype("&#3110;&#3139;&#3127;&#3149;&#3103;&#3135;&#3122;&#3147;&#3114;&#3074;");
//                else if(rs.getString(12).equals("3"))
//                    territorydto.setDisabilitytype("&#3125;&#3135;&#3112;&#3135;&#3093;&#3135;&#3105;&#3135; &#3122;&#3147;&#3114;&#3074; ") ;
//                else if(rs.getString(12).equals("4"))
//                    territorydto.setDisabilitytype("&#3116;&#3137;&#3110;&#3149;&#3110;&#3135; &#3118;&#3134;&#3112;&#3149;&#3119;&#3108; ");
//                else if(rs.getString(12).equals("5"))
//                    territorydto.setDisabilitytype("&#3118;&#3134;&#3112;&#3128;&#3135;&#3093; &#3083;&#3095;&#3149;&#3118;&#3108;");
                }



                territorydto.setDistrict_name(rs.getString(5));
                territorydto.setMandal_name(convertFirstLetterToUpperCase(rs.getString(6)));

                territorydto.setVillage_name(convertFirstLetterToUpperCase(rs.getString(7)));
                territorydto.setDisabilityreason(convertFirstLetterToUpperCase(rs.getString(9)));

                if (rs.getString(10).equals("1") || rs.getString(10).equals("2")) {
                    territorydto.setDisabilityduration("&#3100;&#3136;&#3125;&#3135;&#3108;&#3093;&#3134;&#3122;&#3074;");
                    territorydto.setTeluguDisabilityName("&#3100;&#3136;&#3125;&#3135;&#3108;&#3093;&#3134;&#3122;&#3074;");
                } else if (rs.getString(10).equals("3")) {
                    territorydto.setTeluguDisabilityName("&#3108;&#3134;&#3108;&#3149;&#3093;&#3134;&#3122;&#3135;&#3093;&#3074;&#9;&#9;&#9;");
                    if (rs.getString(11).equals("1")) {
                        territorydto.setDisabilityduration("&#49; &#3128;&#3074;&#3125;&#3108;&#3149;&#3128;&#3120;&#3118;&#3137;&#3122;&#3137;");
                    } else if (rs.getString(11).equals("2")) {
                        territorydto.setDisabilityduration("&#50; &#3128;&#3074;&#3125;&#3108;&#3149;&#3128;&#3120;&#3118;&#3137;&#3122;&#3137;");
                    } else if (rs.getString(11).equals("3")) {
                        territorydto.setDisabilityduration("&#51; &#3128;&#3074;&#3125;&#3108;&#3149;&#3128;&#3120;&#3118;&#3137;&#3122;&#3137;");
                    } else if (rs.getString(11).equals("4")) {
                        territorydto.setDisabilityduration("&#52; &#3128;&#3074;&#3125;&#3108;&#3149;&#3128;&#3120;&#3118;&#3137;&#3122;&#3137;");
                    } else if (rs.getString(11).equals("5")) {
                        territorydto.setDisabilityduration("&#53; &#3128;&#3074;&#3125;&#3108;&#3149;&#3128;&#3120;&#3118;&#3137;&#3122;&#3137;");
                    }

                }

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTeluguName", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getTeluguName");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTeluguName", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getTeluguName");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return territorydto;
    }
    /*
     * This method will get the required data
     *  for rejected Pwd from database
     **/

    /**
     * 
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getRejectedData(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList rejectedlist = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String status = "Rejected";
        CertificateDTO certificatedto = null;
        try {
            con = DBConnection.getConnection();

//            String query = "select P.Person_Code, P.First_Name,P.Surname,P.Gender,P.Relation_Name,P.House_Number,"
//                    + "D.District_Name,M.Mandal_Name,V.Village_Name,H.Habitation_Name,P.Relationship,R.First_Doctor_Name,"
//                    + "R.First_Doctor_Designation,R.First_Doctor_RegNumber,P.Created_Date,R.Disability_ID,R.Hospital_Name,R.Hospital_Address,P.Marital_Status from "
//                    + "tblPerson_Personal_Details P,tblDistrict_Details D,"
//                    + "tblMandal_Details M,tblVillage_Details V,tblHabitation_Details H,tblRejectPerson_Details R where "
//                    + " P.Person_Code = R.Person_Code and P.District_ID = D.District_ID and P.Mandal_ID = M.Mandal_ID and "
//                    + "P.District_ID = M.District_ID and P.Village_ID = V.Village_ID and P.District_ID = V.District_ID and"
//                    + " P.Mandal_ID = V.Mandal_ID and P.Habitation_ID = H.Habitation_ID and P.District_ID = H.District_ID and"
//                    + " P.Mandal_ID = H.Mandal_ID and P.Village_ID = H.Village_ID and  P.Person_Code ='" + personcode + "' and P.Person_Status='"+status+"'";

            /** String query = "select P.Person_Code, P.First_Name,P.Surname,P.Gender,P.Relation_Name,"
            + "P.House_Number,D.District_Name,M.Mandal_Name,V.Village_Name,H.Habitation_Name,"
            + "P.Relationship,R.First_Doctor_Name,R.First_Doctor_Designation,R.First_Doctor_RegNumber,"
            + "R.Created_Date,R.Disability_ID,R.Hospital_Name,R.Hospital_Address,P.Marital_Status "
            + "from tblPerson_Personal_Details P,tblRejectPerson_Details R,tblDistrict_Details "
            + "D,tblMandal_Details M,tblVillage_Details V,tblHabitation_Details H where"
            + " R.PERSON_CODE   = P.PERSON_CODE   AND D.DISTRICT_ID   = P.DISTRICT_ID AND "
            + "M.DISTRICT_ID  = P.DISTRICT_ID AND M.MANDAL_ID  = P.MANDAL_ID  AND"
            + " V.DISTRICT_ID   = P.DISTRICT_ID AND V.MANDAL_ID = P.MANDAL_ID AND"
            + " V.VILLAGE_ID = P.VILLAGE_ID AND H.DISTRICT_ID = P.DISTRICT_ID AND "
            + "H.MANDAL_ID = P.MANDAL_ID AND H.VILLAGE_ID = P.VILLAGE_ID AND "
            + "H.HABITATION_ID = P.HABITATION_ID AND P.STATUS = 'Active'AND "
            + "R.STATUS = 'Active' AND P.PERSON_CODE = '" + personcode + "'"; */
            String query = "SELECT P.Person_Code,P.First_Name,P.Surname,P.Gender,P.Relation_Name, "
                    + "P.House_Number,D.District_Name,M.Mandal_Name,V.Village_Name, "
                    + "H.Habitation_Name,P.Relationship,R.First_Doctor_Name, "
                    + "R.First_Doctor_Designation,R.First_Doctor_RegNumber, "
                    + "R.Created_Date,R.Disability_ID,R.Hospital_Name,R.Hospital_Address, "
                    + "P.Marital_Status,case when (DATEADD(DD,0,DATEDIFF(DD,0,r.Created_Date)))>'06/01/2014' then 1 else 0  end flag FROM "
                    + "tblPerson_Personal_Details P  with (nolock) , "
                    + "tblRejectPerson_Details R with(nolock) , "
                    + "tblDistrict_Details D with(nolock) , "
                    + "tblMandal_Details M with(nolock) , "
                    + "tblVillage_Details V with(nolock)  , "
                    + "tblHabitation_Details H with(nolock)  "
                    + "WHERE "
                    + "R.PERSON_CODE = P.PERSON_CODE AND "
                    + "D.DISTRICT_ID = P.District_ID AND "
                    + "M.DISTRICT_ID = P.District_ID AND "
                    + "M.MANDAL_ID = P.Mandal_ID AND "
                    + "V.DISTRICT_ID = P.District_ID AND "
                    + "V.MANDAL_ID = P.Mandal_ID AND "
                    + "V.VILLAGE_ID = P.Village_ID AND "
                    + "H.HABITATION_CODE = P.HABCODE AND "
                    + "P.STATUS = 'Active' AND "
                    + "R.STATUS = 'Active' AND "
                    + "P.PERSON_CODE = ?";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                certificatedto = new CertificateDTO();
                certificatedto.setPersoncode(rs.getString(1));
                String FirstName = convertFirstLetterToUpperCase(rs.getString("First_Name"));
                // String LastName=convertFirstLetterToUpperCase(rs.getString("Last_Name"));
                String Surname = convertFirstLetterToUpperCase(rs.getString("Surname"));
                String Name = Surname + " " + FirstName;
                certificatedto.setName(Name);
                if (rs.getString(4).equals("1")) {
                    certificatedto.setMaritialstatus("Shri");
                    if (rs.getString(11).equals("1")) {
                        certificatedto.setRelationship("S/o");
                    } else if (rs.getString(11).equals("2")) {
                        certificatedto.setRelationship("S/o");
                    } else if (rs.getString(11).equals("3")) {
                        certificatedto.setRelationship("G/o");
                    } else if (rs.getString(11).equals("4")) {
                        certificatedto.setRelationship("G/o");
                    } else if (rs.getString(11).equals("5")) {
                        certificatedto.setRelationship("G/o");
                    } else if (rs.getString(11).equals("6")) {
                        certificatedto.setRelationship("G/o");
                    } else if (rs.getString(11).equals("7")) {
                        certificatedto.setRelationship("H/o");
                    }
                } else {
                    if (rs.getString("Marital_Status").equals("1")) {
                        certificatedto.setMaritialstatus("Smt");
                    } else if (rs.getString("Marital_Status").equals("2")) {
                        certificatedto.setMaritialstatus("Kumari");
                    } else if (rs.getString("Marital_Status").equals("3")
                            || rs.getString("Marital_Status").equals("4")
                            || rs.getString("Marital_Status").equals("5")) {
                        certificatedto.setMaritialstatus("Ms");
                    }

                    if (rs.getString(11).equals("1")) {
                        certificatedto.setRelationship("D/o");
                    } else if (rs.getString(11).equals("2")) {
                        certificatedto.setRelationship("D/o");
                    } else if (rs.getString(11).equals("3")) {
                        certificatedto.setRelationship("W/o");
                    } else if (rs.getString(11).equals("4")) {
                        certificatedto.setRelationship("G/o");
                    } else if (rs.getString(11).equals("5")) {
                        certificatedto.setRelationship("G/o");
                    } else if (rs.getString(11).equals("6")) {
                        certificatedto.setRelationship("G/o");
                    } else if (rs.getString(11).equals("7")) {
                        certificatedto.setRelationship("H/o");
                    }
                }
                certificatedto.setRelationname(convertFirstLetterToUpperCase(rs.getString(5)));
                certificatedto.setHousenumber(rs.getString(6));
                certificatedto.setDistrictname(rs.getString(7));

                certificatedto.setMandalname(convertFirstLetterToUpperCase(rs.getString(8)));

                certificatedto.setVillagename(convertFirstLetterToUpperCase(rs.getString(9)));
                certificatedto.setHabitationname(convertFirstLetterToUpperCase(rs.getString(10)));

                certificatedto.setDoctorname(convertFirstLetterToUpperCase(rs.getString(12)));
                certificatedto.setDoctordesignation(rs.getString(13));
                certificatedto.setDoctornumber(rs.getString(14));
                String formdate = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd ").parse(rs.getString(15)));
                certificatedto.setIssuedate(formdate);
                certificatedto.setHospitalname(convertFirstLetterToUpperCase(rs.getString("Hospital_Name")));
                certificatedto.setHospitaladdress(convertFirstLetterToUpperCase(rs.getString("Hospital_Address")));
                certificatedto.setDisabilityidforrejecte(rs.getString(16));
                certificatedto.setFlag(rs.getString("flag"));
                rejectedlist.add(certificatedto);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedData", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedData", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedData");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return rejectedlist;
    }
    /*
     * This method will gives Status for
     *  particular Pwd from database
     **/

    /**
     * 
     * @param ds 
     * @param personcode 
     * @return list
     * @throws org.bf.disability.Exception.SADAREMException 
     */
    public ArrayList getStatus(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList personlist = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        TerritoryDTO territorydto = null;
        try {
            con = DBConnection.getConnection();
            String query = "select P.Person_Status,D.Disability_ID from tblPerson_Personal_Details  "
                    + "P  with (nolock) ,dbo.tblPerson_Disability_Details D with(nolock)  where  P.Person_Code = D.Person_Code and P.Person_Code=?"
                    + "and d.status='Active' ";
            pstmt = con. prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                territorydto = new TerritoryDTO();
                territorydto.setPersonstatus(rs.getString(1));
                territorydto.setDisabilitytype(rs.getString(2));
                personlist.add(territorydto);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStatus", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getStatus");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStatus", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getStatus");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return personlist;
    }
    
    
    public String getReasonForPersonStatus(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        String personStatus="";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        TerritoryDTO territorydto = null;
        try {
            con = DBConnection.getConnection();
            String query = "select  reasonforpersonstatus from tblPerson_Personal_Details with(nolock)   where person_code=?";
             // System.out.println("query"+query);     
                  
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	personStatus = rs.getString(1);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStatus", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getStatus");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStatus", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getStatus");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return personStatus;
    }
    public ArrayList getCatIdOfPerson(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        int personStatus=0;
        ArrayList data=new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        TerritoryDTO territorydto = null;
        try {
            con = DBConnection.getConnection();
            String query = "select  CategoryID,status,view_edit_mode from tblPerson_Personal_Details with(nolock)  where person_code=?";
                 
                  
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	data.add(rs.getInt(1));
            	data.add(rs.getString(2));
            	data.add(rs.getString(3));
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStatus", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getStatus");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStatus", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getStatus");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return data;
    }
    
    public String getStatusOfPerson(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        String personStatus="";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        TerritoryDTO territorydto = null;
        try {
            con = DBConnection.getConnection();
            String query = "select  status from APPELLATEAUTHORITYANDTEMPORARY_REGISTRATIONDETAILS with(nolock)   where person_code=?";
              //System.out.println("query"+query);     
                  
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	personStatus = rs.getString(1);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStatus", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getStatus");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStatus", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getStatus");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return personStatus;
    }
    public String getViewmodeOfPerson(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        String personStatus="";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        TerritoryDTO territorydto = null;
        try {
            con = DBConnection.getConnection();
            String query = "select  ViewEditMode from APPELLATEAUTHORITYANDTEMPORARY_REGISTRATIONDETAILS with(nolock)  where person_code=?";
              //System.out.println("query"+query);     
                  
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	personStatus = rs.getString(1);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStatus", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getStatus");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStatus", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getStatus");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return personStatus;
    }
    
    
    
    
    
    
    /*
     * This method will get Locomotor Pwd data
     *  for Certificate from database
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */

    public ArrayList getLocomotorData(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList locomotorlist = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        TerritoryDTO territorydto = null;
        try {
            con = DBConnection.getConnection();
            String query = "select P.Person_Code ,L.Surgery,L.Physiotherapy,C.OtherServices,C.Individual,C.Family from tblPerson_LocomotorNeeds_Details L with(nolock) ,tblPerson_Personal_Details P  with (nolock) ,tblAllDisabilityPerson_Common_Needs C with(nolock)  where P.Person_Code = L.Person_Code and P.Person_Code=C.Person_Code and "
                    + "P.Person_Code=? and p.status='Active' "
                    + "and L.status='Active' "
                    + "and c.status='Active'";



            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                territorydto = new TerritoryDTO();
                territorydto.setPersoncode(rs.getString(1));
                territorydto.setSurgery(rs.getString(2));

                territorydto.setPhysiotherapy(rs.getString(3));

                territorydto.setAnyother(rs.getString(4));


                if ((rs.getString(5) != null) || (rs.getString(6) != null)) {

                    territorydto.setCouncelling("Counseling & Guidance");
                    if (territorydto.getSurgery() == "" || territorydto.getPhysiotherapy() == null || territorydto.getAnyother() == "" || territorydto.getCouncelling() == null) {

                        territorydto.setRejected("show");
                    }
                }

                locomotorlist.add(territorydto);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getLocomotorData", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getLocomotorData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getLocomotorData", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getLocomotorData");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return locomotorlist;
    }
    /*
     * This method will get Visual Pwd data
     *  for Certificate from database
     **/

    /**
     * 
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getVisualData(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList visuallist = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        TerritoryDTO territorydto = null;
        try {
            con = DBConnection.getConnection();
            String query = "select P.Person_Code ,V.Surgery,V.LowVisionAids,C.OtherServices,C.Individual,C.Family from tblAllDisabilityPerson_Common_Needs C with(nolock) ,tblPerson_Personal_Details P  with (nolock) ,tblVisualImpairment_Details V with(nolock) "
                    + "where P.Person_Code = C.Person_Code and P.Person_Code=V.Person_Code and P.Person_Code=? and p.status='Active' "
                    + "and v.status='Active' "
                    + "and c.status='Active'";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                territorydto = new TerritoryDTO();
                territorydto.setPersoncode(rs.getString(1));
                territorydto.setSurgery(rs.getString(2));
                territorydto.setPhysiotherapy(rs.getString(3));
                territorydto.setAnyother(rs.getString(4));

                if ((rs.getString(5) != null) || (rs.getString(6) != null)) {
                    territorydto.setCouncelling("Counseling & Guidance");
                    if (territorydto.getSurgery() == "" || territorydto.getPhysiotherapy() == null || territorydto.getAnyother() == "" || territorydto.getCouncelling() == null) {

                        territorydto.setRejected("show");
                    }
                }
                visuallist.add(territorydto);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVisualData", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getVisualData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVisualData", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getVisualData");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return visuallist;
    }
    /*
     * This method will get Hearing Pwd data
     *  for Certificate from database
     **/

    /**
     * 
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getHearingData(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList hearinglist = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        TerritoryDTO territorydto = null;
        try {
            con = DBConnection.getConnection();
            String query = "select P.Person_Code ,H.Surgerey,H.HearingAidType,C.OtherServices,C.Individual,C.Family from tblAllDisabilityPerson_Common_Needs C,tblPerson_Personal_Details P  with (nolock) , dbo.tblHearing_Impairment_Details H with(nolock)  where P.Person_Code = C.Person_Code and P.Person_Code=H.Person_Code "
                    + "and P.Person_Code=? and p.status='Active' "
                    + "and c.status='Active' "
                    + "and h.status='Active'";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                territorydto = new TerritoryDTO();
                territorydto.setPersoncode(rs.getString(1));
                territorydto.setSurgery(rs.getString(2));
                territorydto.setPhysiotherapy(rs.getString(3));


                territorydto.setAnyother(rs.getString(4));


                if ((rs.getString(5) != null) || (rs.getString(6) != null)) {
                    territorydto.setCouncelling("Counseling & Guidance");
                    if (territorydto.getSurgery() == "" || territorydto.getPhysiotherapy() == null || territorydto.getAnyother() == "" || territorydto.getCouncelling() == null) {

                        territorydto.setRejected("show");
                    }
                }
                hearinglist.add(territorydto);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHearingData", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getHearingData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHearingData", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getHearingData");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return hearinglist;
    }
    /*
     * This method will get Mentalillness Pwd data
     *  for Certificate from database
     **/

    /**
     * 
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getMetalillnessData(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList illinesslist = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        TerritoryDTO territorydto = null;
        try {
            con = DBConnection.getConnection();
            String query = "select P.Person_Code ,I.Surgery,I.NursingHomes,C.OtherServices,C.Individual,"
                    + "C.Family from tblAllDisabilityPerson_Common_Needs C with(nolock) ,tblPerson_Personal_Details P  with (nolock) ,"
                    + "dbo.tblMental_Disability_Illness_Details I with(nolock)  where P.Person_Code = C.Person_Code and"
                    + " P.Person_Code=I.Person_Code and P.Person_Code=? and p.status='Active' "
                    + "and c.status='Active' "
                    + "and I.status='Active'";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                territorydto = new TerritoryDTO();
                territorydto.setPersoncode(rs.getString(1));
                territorydto.setSurgery(rs.getString(2));
                territorydto.setPhysiotherapy(rs.getString(3));
                territorydto.setAnyother(rs.getString(4));

                if ((rs.getString(5) != null) || (rs.getString(6) != null)) {

                    territorydto.setCouncelling("Counseling & Guidance");
                    if (territorydto.getSurgery() == "" || territorydto.getPhysiotherapy() == null || territorydto.getAnyother() == "" || territorydto.getCouncelling() == null) {

                        territorydto.setRejected("show");
                    }
                }
                illinesslist.add(territorydto);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMetalillnessData", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMetalillnessData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMetalillnessData", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMetalillnessData");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return illinesslist;
    }
    /*
     * This method will get MentalRetardation Pwd data
     *  for Certificate from database
     **/

    /**
     * 
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getMentalRetardationData(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList retardationlist = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        TerritoryDTO territorydto = null;
        try {
            con = DBConnection.getConnection();
            String query = "select P.Person_Code ,R.PsycotherapyBehaviour,C.OtherServices,C.Individual,C.Family from tblAllDisabilityPerson_Common_Needs C with(nolock) ,tblPerson_Personal_Details P  with (nolock) ,tblMental_Retardation_Details R with(nolock) where P.Person_Code = C.Person_Code and P.Person_Code=R.Person_Code and  "
                    + "P.Person_Code=? and p.status='Active' "
                    + "and c.status='Active' "
                    + "and R.status='Active'";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                territorydto = new TerritoryDTO();
                territorydto.setPersoncode(rs.getString(1));
                // territorydto.setSurgery(rs.getString(2));// column deleted
                territorydto.setPhysiotherapy(rs.getString(2));


                territorydto.setAnyother(rs.getString(3));
                if ((rs.getString(4) != null) || (rs.getString(5) != null)) {
                    territorydto.setCouncelling("Counseling & Guidance");
                    if (territorydto.getSurgery() == "" || territorydto.getPhysiotherapy() == null || territorydto.getAnyother() == "" || territorydto.getCouncelling() == null) {

                        territorydto.setRejected("show");
                    }
                }
                retardationlist.add(territorydto);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMentalRetardationData", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMentalRetardationData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMentalRetardationData", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMentalRetardationData");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return retardationlist;
    }
    /*
     * This method will get locomotor Pwd data
     *  for RejectedCertificate from database
     **/

    /**
     * 
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getRejectedLocomotorData(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {


        ArrayList locomotorlist = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        TerritoryDTO territorydto = null;
        try {
            con = DBConnection.getConnection();
            String query = "select Person_Code,Reffered_To,Surgery,Counciling_Guidence,Physiotherapy,AnyOther from tblRejectPerson_Details with(nolock)  where Person_Code =? and status='Active'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                territorydto = new TerritoryDTO();
                territorydto.setPersoncode(rs.getString("Person_Code"));
                territorydto.setReferredto(rs.getString("Reffered_To"));
                territorydto.setSurgeryforrejected(rs.getString("Surgery"));
                territorydto.setCouncellingandguidance(rs.getString("Counciling_Guidence"));
                territorydto.setLowvisionaid(rs.getString("Physiotherapy"));

                territorydto.setAnyotherneed(rs.getString("AnyOther"));
                if (territorydto.getReferredto() == "" || territorydto.getSurgeryforrejected() == "" || territorydto.getCouncellingandguidance() == null || territorydto.getLowvisionaid() == null || territorydto.getAnyotherneed() == "") {
                    territorydto.setRejected("show");
                }
                locomotorlist.add(territorydto);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedLocomotorData", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedLocomotorData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedLocomotorData", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedLocomotorData");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return locomotorlist;
    }
    /*
     * This method will get Visual Pwd data
     *  for RejectedCertificate from database
     **/

    /**
     * 
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getRejectedVisualData(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList visuallist = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        TerritoryDTO territorydto = null;
        try {
            con = DBConnection.getConnection();
            String query = "select Person_Code,Reffered_To,Surgery,Counciling_Guidence,LowVision,AnyOther from tblRejectPerson_Details with(nolock)  where Person_Code =? and status='Active'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                territorydto = new TerritoryDTO();
                territorydto.setPersoncode(rs.getString("Person_Code"));
                territorydto.setReferredto(rs.getString("Reffered_To"));
                territorydto.setSurgeryforrejected(rs.getString("Surgery"));
                territorydto.setCouncellingandguidance(rs.getString("Counciling_Guidence"));
                territorydto.setLowvisionaid(rs.getString("LowVision"));
                territorydto.setAnyotherneed(rs.getString("AnyOther"));
                if (territorydto.getReferredto() == "" || territorydto.getSurgeryforrejected() == "" || territorydto.getCouncellingandguidance() == null || territorydto.getLowvisionaid() == null || territorydto.getAnyotherneed() == "") {
                    territorydto.setRejected("show");
                }
                visuallist.add(territorydto);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedVisualData", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedVisualData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedVisualData", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedVisualData");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return visuallist;
    }
    /*
     * This method will get Hearing Pwd data
     *  for RejectedCertificate from database
     **/

    /**
     * 
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getRejectedHearingData(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList hearinglist = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        TerritoryDTO territorydto = null;
        try {
            con = DBConnection.getConnection();
            String query = "select Person_Code,Reffered_To,Surgery,Counciling_Guidence,Hearing_Aid,AnyOther from tblRejectPerson_Details with(nolock)  where Person_Code =? and status='Active'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                territorydto = new TerritoryDTO();
                territorydto.setPersoncode(rs.getString("Person_Code"));
                territorydto.setReferredto(rs.getString("Reffered_To"));
                territorydto.setSurgeryforrejected(rs.getString("Surgery"));
                territorydto.setCouncellingandguidance(rs.getString("Counciling_Guidence"));
                territorydto.setLowvisionaid(rs.getString("Hearing_Aid"));
                territorydto.setAnyotherneed(rs.getString("AnyOther"));
                if (territorydto.getReferredto() == "" || territorydto.getSurgeryforrejected() == "" || territorydto.getCouncellingandguidance() == null || territorydto.getLowvisionaid() == null || territorydto.getAnyotherneed() == "") {
                    territorydto.setRejected("show");
                }
                hearinglist.add(territorydto);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedHearingData", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedHearingData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedHearingData", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedHearingData");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return hearinglist;
    }
    /*
     * This method will get MentalIllness Pwd data
     *  for RejectedCertificate from database
     **/

    /**
     * 
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getRejectedMetalillnessData(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList illinesslist = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        TerritoryDTO territorydto = null;
        try {
            con = DBConnection.getConnection();
            String query = "select Person_Code,Reffered_To,Phychotherapy,Counciling_Guidence,Addmission,AnyOther from tblRejectPerson_Details with(nolock)  where Person_Code =? and status='Active'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                territorydto = new TerritoryDTO();
                territorydto.setPersoncode(rs.getString("Person_Code"));
                territorydto.setReferredto(rs.getString("Reffered_To"));
                territorydto.setSurgeryforrejected(rs.getString("Phychotherapy"));
                territorydto.setCouncellingandguidance(rs.getString("Counciling_Guidence"));
                territorydto.setLowvisionaid(rs.getString("Addmission"));
                territorydto.setAnyotherneed(rs.getString("AnyOther"));
                if (territorydto.getReferredto() == "" || territorydto.getSurgeryforrejected() == "" || territorydto.getCouncellingandguidance() == null || territorydto.getLowvisionaid() == null || territorydto.getAnyotherneed() == "") {
                    territorydto.setRejected("show");
                }
                illinesslist.add(territorydto);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedMetalillnessData", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedMetalillnessData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedMetalillnessData", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedMetalillnessData");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return illinesslist;
    }
    /*
     * This method will get MentalRetardation Pwd data
     *  for RejectedCertificate from database
     **/

    /**
     * 
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getRejectedMentalRetardationData(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList retardationlist = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        TerritoryDTO territorydto = null;
        try {
            con = DBConnection.getConnection();
            String query = "select Person_Code,Reffered_To,Surgery,Counciling_Guidence,Behaviour,AnyOther from tblRejectPerson_Details with(nolock)  where Person_Code =? and status='Active'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                territorydto = new TerritoryDTO();
                territorydto.setPersoncode(rs.getString("Person_Code"));
                territorydto.setReferredto(rs.getString("Reffered_To"));
                territorydto.setSurgeryforrejected(rs.getString("Surgery"));
                territorydto.setCouncellingandguidance(rs.getString("Counciling_Guidence"));
                territorydto.setLowvisionaid(rs.getString("Behaviour"));
                territorydto.setAnyotherneed(rs.getString("AnyOther"));
                if (territorydto.getReferredto() == "" || territorydto.getSurgeryforrejected() == "" || territorydto.getCouncellingandguidance() == null || territorydto.getLowvisionaid() == null || territorydto.getAnyotherneed() == "") {
                    territorydto.setRejected("show");
                }
                retardationlist.add(territorydto);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedMentalRetardationData", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedMentalRetardationData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRejectedMentalRetardationData", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getRejectedMentalRetardationData");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return retardationlist;
    }
    /*
     * This method will get Locomotorsubdetail
     *  for Certificate from database
     **/

    /**
     * 
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getLocomotorSubDetails(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList locomotorsubdetails = new ArrayList();
        ArrayList subdetails = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String[] str = new String[20];
        String[] str1 = new String[30];

        CertificateDTO certificatedto = null;
        try {
            con = DBConnection.getConnection();

            String query = "select Person_Code,Sub_Sub_Disability_ID,Sub_Disability_ID from tblPerson_Disability_Details with(nolock)  where Person_Code =?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                certificatedto = new CertificateDTO();
                certificatedto.setPersoncode(rs.getString(1));
                if (rs.getString(2) != null) {
                    str = rs.getString(2).split(",");



                    for (int i = 0; i < str.length; i++) {
                        if (str[i].equals("1")) {
                            certificatedto.setPartInvolved1("-Right Upper Limb");
                        } else if (str[i].equals("2")) {
                            certificatedto.setPartInvolved2("-Left Upper Limb");
                        } else if (str[i].equals("3")) {
                            certificatedto.setPartInvolved3("-Bilateral Upper Limb");
                        } else if (str[i].equals("4")) {
                            certificatedto.setPartInvolved4("-Right Lower Limb");
                        } else if (str[i].equals("5")) {
                            certificatedto.setPartInvolved5("-Left Lower Limb");
                        } else if (str[i].equals("6")) {
                            certificatedto.setPartInvolved6("-Bilateral Lower Limb");//Changed as Upper to Lower
                        } else if (str[i].equals("7")) {
                            certificatedto.setPartInvolved7("-Spine");
                        } else if (str[i].equals("8")) {
                            certificatedto.setPartInvolved8("-Trunk");
                        } else if (str[i].equals("9")) {
                            certificatedto.setPartInvolved9("-All 4 Limbs");
                        } else if (str[i].equals("10")) {
                            certificatedto.setPartInvolved10("-Whole Body");
                        } else if (str[i].equals("11")) {
                            certificatedto.setPartInvolved11("-Hips");//Added
                        }
                    }
                }
                if (rs.getString(3) != null) {
                    str1 = rs.getString(3).split(",");

                    for (int j = 0; j < str1.length; j++) {
                        if (str1[j].equals("1")) {
                            certificatedto.setOhsubtypes1("- Cerebral Palsy (CP) ");
                        } else if (str1[j].equals("2")) {
                            certificatedto.setOhsubtypes2("- Post Polio Residual Paralysis (PPRP)");
                        } else if (str1[j].equals("3")) {
                            certificatedto.setOhsubtypes3("- Post Hansen's Disease Sequel");
                        } else if (str1[j].equals("4")) {
                            certificatedto.setOhsubtypes4("-  Muscular Dystrophy");
                        } else if (str1[j].equals("5")) {
                            certificatedto.setOhsubtypes5("-  Post Traumatic Amputation");
                        } else if (str1[j].equals("6")) {
                            certificatedto.setOhsubtypes6("-  Post Traumatic Sequel - Limbs");
                        } else if (str1[j].equals("7")) {
                            certificatedto.setOhsubtypes7("- Post Traumatic Sequel - Spine");
                        } else if (str1[j].equals("8")) {
                            certificatedto.setOhsubtypes8("-  Post Head Injury Sequel");
                        } else if (str1[j].equals("9")) {
                            certificatedto.setOhsubtypes9("- Post Burn Injury Sequel");
                        } else if (str1[j].equals("10")) {
                            certificatedto.setOhsubtypes10("- Cerebro-Vascular Accidents  ");
                        } else if (str1[j].equals("11")) {
                            certificatedto.setOhsubtypes11("- Dwarfism");
                        } else if (str1[j].equals("12")) {
                            certificatedto.setOhsubtypes12("- Congenital Deformities of Limbs");
                        } else if (str1[j].equals("13")) {
                            certificatedto.setOhsubtypes13("- Congenital Deformities of Spine ");
                        } else if (str1[j].equals("14")) {
                            certificatedto.setOhsubtypes14("- Cardio Pulmonary Disease");
                        } else if (str1[j].equals("15")) {
                            certificatedto.setOhsubtypes15("- Others");

                        }
                    }




                }
                locomotorsubdetails.add(certificatedto);

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getLocomotorSubDetails", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getLocomotorSubDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getLocomotorSubDetails", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getLocomotorSubDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return locomotorsubdetails;
    }
    /*
     * This method will get Visualsubdetail
     *  for Certificate from database
     **/

    /**
     * 
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getVisualSubDetails(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList visualsubdetails = new ArrayList();
        ArrayList subdetails = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String[] str = new String[20];
        CertificateDTO certificatedto = null;
        try {
            con = DBConnection.getConnection();

            String query = "select Person_Code,Sub_Disability_ID from tblPerson_Disability_Details with(nolock)  where Person_Code =?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                certificatedto = new CertificateDTO();
                certificatedto.setPersoncode(rs.getString(1));
                if (rs.getString(2) != null) {
                    str = rs.getString(2).split(",");



                    for (int i = 0; i < str.length; i++) {
                        if (str[i].equals("62")) {
                            certificatedto.setPartInvolved1("-Complete Loss Of Vision-Better Eye");
                        } else if (str[i].equals("63")) {
                            certificatedto.setPartInvolved2("-Complete Loss Of Vision-Worse Eye");
                        } else if (str[i].equals("93")) {
                            certificatedto.setPartInvolved3("-Low Vision-Better Eye");
                        } else if (str[i].equals("94")) {
                            certificatedto.setPartInvolved4("-Low Vision-Worse Eye");
                        }
                    }
                }
                visualsubdetails.add(certificatedto);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVisualSubDetails", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getVisualSubDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVisualSubDetails", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getVisualSubDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return visualsubdetails;
    }
    /*
     * This method will get Hearingsubdetail
     *  for Certificate from database
     **/

    /**
     * 
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getHearingSubDetails(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList hearingsubdetails = new ArrayList();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        CertificateDTO certificatedto = null;
        try {
            con = DBConnection.getConnection();

            String query = "select Person_Code,Sub_Disability_ID from tblPerson_Disability_Details with(nolock)  where Person_Code =?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                certificatedto = new CertificateDTO();
                certificatedto.setPersoncode(rs.getString(1));
                if (rs.getString(2) != null) {
                    if (rs.getString(2).equals("64")) {
                        certificatedto.setPartInvolved1("-Mild");
                    } else if (rs.getString(2).equals("65")) {
                        certificatedto.setPartInvolved2("-Moderate");
                    } else if (rs.getString(2).equals("66")) {
                        certificatedto.setPartInvolved3("-Severe");
                    } else if (rs.getString(2).equals("67")) {
                        certificatedto.setPartInvolved4("-Profound");

                    }
                }
                hearingsubdetails.add(certificatedto);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHearingSubDetails", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getHearingSubDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHearingSubDetails", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getHearingSubDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return hearingsubdetails;
    }
    /*
     * This method will get MentalRetardationsubdetail
     *  for Certificate from database
     **/

    /**
     * 
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getMRSubDetails(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList retardationlist = new ArrayList();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        CertificateDTO certificatedto = null;
        try {
            con = DBConnection.getConnection();

            String query = "select Person_Code,Sub_Disability_ID from tblPerson_Disability_Details with(nolock)  where Person_Code =?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                certificatedto = new CertificateDTO();
                certificatedto.setPersoncode(rs.getString(1));
                if (rs.getString(2) != null) {
                    if (rs.getString(2).equals("69")) {
                        certificatedto.setPartInvolved1("-Mild");
                    } else if (rs.getString(2).equals("70")) {
                        certificatedto.setPartInvolved2("-Moderate");
                    } else if (rs.getString(2).equals("71")) {
                        certificatedto.setPartInvolved3("-Severe");
                    } else if (rs.getString(2).equals("72")) {
                        certificatedto.setPartInvolved4("-Profound");

                    }
                }
                retardationlist.add(certificatedto);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMRSubDetails", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMRSubDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMRSubDetails", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMRSubDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return retardationlist;
    }
    /*
     * This method will get MentalIllnesssubdetail
     *  for Certificate from database
     **/

    /**
     * 
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getMISubDetails(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList illinesslist = new ArrayList();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        CertificateDTO certificatedto = null;
        try {
            con = DBConnection.getConnection();

            String query = "select Person_Code,Sub_Disability_ID from tblPerson_Disability_Details with(nolock)  where Person_Code =?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                certificatedto = new CertificateDTO();
                certificatedto.setPersoncode(rs.getString(1));
                if (rs.getString(2) != null) {
                    if (rs.getString(2).equals("74")) {
                        certificatedto.setPartInvolved1("-Mild");
                    } else if (rs.getString(2).equals("75")) {
                        certificatedto.setPartInvolved2("-Moderate");
                    } else if (rs.getString(2).equals("76")) {
                        certificatedto.setPartInvolved3("-Severe");
                    } else if (rs.getString(2).equals("77")) {
                        certificatedto.setPartInvolved4("-Profound");

                    }
                }
                illinesslist.add(certificatedto);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMISubDetails", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMISubDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMISubDetails", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMISubDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return illinesslist;
    }
    /*
     * This method will get Multiplesubdetail
     *  for Certificate from database
     **/

    /**
     * 
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getMultipleSubDetails(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList locomotorsubdetails = new ArrayList();
        ArrayList subdetails = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String[] str = new String[20];
        String[] str1 = new String[20];
        CertificateDTO certificatedto = null;
        try {
            con = DBConnection.getConnection();

            String query = "select Person_Code,Sub_Disability_ID,Sub_Sub_Disability_ID from tblPerson_Disability_Details with(nolock)  where Person_Code =?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                certificatedto = new CertificateDTO();
                certificatedto.setPersoncode(rs.getString(1));
                if (rs.getString(3) != null) {
                    str = rs.getString(3).split(",");



                    for (int i = 0; i < str.length; i++) {
                        if (str[i].equals("1")) {
                            certificatedto.setPartInvolved1("-Right Upper Limb");
                        } else if (str[i].equals("2")) {
                            certificatedto.setPartInvolved2("-Left Upper Limb");
                        } else if (str[i].equals("3")) {
                            certificatedto.setPartInvolved3("-Bilateral Upper Limb");
                        } else if (str[i].equals("4")) {
                            certificatedto.setPartInvolved4("-Right Lower Limb");
                        } else if (str[i].equals("5")) {
                            certificatedto.setPartInvolved5("-Left Lower Limb");
                        } else if (str[i].equals("6")) {
                            certificatedto.setPartInvolved6("-Bilateral Lower Limb");//Changed as Upper to Lower
                        } else if (str[i].equals("7")) {
                            certificatedto.setPartInvolved7("-Spine");
                        } else if (str[i].equals("8")) {
                            certificatedto.setPartInvolved8("-Trunk");
                        } else if (str[i].equals("9")) {
                            certificatedto.setPartInvolved9("-All 4 Limbs");
                        } else if (str[i].equals("10")) {
                            certificatedto.setPartInvolved10("-Whole Body");
                        } else if (str[i].equals("11")) {
                            certificatedto.setPartInvolved11("-Hips");//added
                        }
                    }
                }
                if (rs.getString(2) != null) {
                    str1 = rs.getString(2).split(",");
                    for (int j = 0; j < str1.length; j++) {
                        if (str1[j].equals("62")) {
                            certificatedto.setPartInvolved11("-Complete Loss Of Vision-Better Eye");
                        } else if (str1[j].equals("63")) {
                            certificatedto.setPartInvolved12("-Complete Loss Of Vision-Worse Eye");
                        } else if (str1[j].equals("93")) {
                            certificatedto.setPartInvolved13("-Low Vision-Better Eye");
                        } else if (str1[j].equals("94")) {
                            certificatedto.setPartInvolved14("-Low Vision-Worse Eye");
                        } else if (str1[j].equals("64")) {
                            certificatedto.setPartInvolved15("-Mild");
                        } else if (str1[j].equals("65")) {
                            certificatedto.setPartInvolved15("-Moderate");
                        } else if (str1[j].equals("65")) {
                            certificatedto.setPartInvolved15("-Severe");
                        } else if (str1[j].equals("67")) {
                            certificatedto.setPartInvolved15("-Profound");
                        } else if (str1[j].equals("69")) {
                            certificatedto.setPartInvolved16("-Mild");
                        } else if (str1[j].equals("70")) {
                            certificatedto.setPartInvolved16("-Moderate");
                        } else if (str1[j].equals("71")) {
                            certificatedto.setPartInvolved16("-Severe");
                        } else if (str1[j].equals("72")) {
                            certificatedto.setPartInvolved16("-Profound");
                        } else if (str1[j].equals("74")) {
                            certificatedto.setPartInvolved17("-Mild");
                        } else if (str1[j].equals("75")) {
                            certificatedto.setPartInvolved17("-Moderate ");
                        } else if (str1[j].equals("76")) {
                            certificatedto.setPartInvolved17("-Severe");
                        } else if (str1[j].equals("77")) {
                            certificatedto.setPartInvolved17("-Profound");
                        }
                    }
                }
                locomotorsubdetails.add(certificatedto);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleSubDetails", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMultipleSubDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleSubDetails", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMultipleSubDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return locomotorsubdetails;
    }
    /*
     * This method will get Multiple detail
     *  for Certificate from database
     **/

    /**
     * 
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getMultipleData(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList multipllist = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        TerritoryDTO territorydto = null;
        try {
            con = DBConnection.getConnection();
            String query = "select Person_Code,OtherServices,Individual,Family from tblAllDisabilityPerson_Common_Needs with(nolock)  "
                    + "where Person_Code=? and status='Active'";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                territorydto = new TerritoryDTO();
                territorydto.setPersoncode(rs.getString(1));
                territorydto.setSurgery("");
                territorydto.setPhysiotherapy("");

                territorydto.setAnyother(rs.getString(2));
                if ((rs.getString(3) != null) || (rs.getString(4) != null)) {
                    territorydto.setCouncelling("Counseling & Guidance");
                }
                multipllist.add(territorydto);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleData", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMultipleData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleData", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMultipleData");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return multipllist;
    }
    /*
     * This method is for converting firstletter to uppercase
     */

    /**
     * 
     * @param inputString 
     * @return string
     */
    public String convertFirstLetterToUpperCase(String inputString) {
        String name = null;
        if (null != inputString && !"".equals(inputString)) {
            StringBuffer inputStrBuffer = new StringBuffer(inputString);
            String firstLetterUpperCase = inputStrBuffer.substring(0, 1).toUpperCase();
            inputStrBuffer.replace(0, 1, firstLetterUpperCase);
            name = inputStrBuffer.toString();
        }
        return name;
    }
    /*
     * This method is for converting Number into words.
     *
     */
    public static String NumberInSingleDigit[] = {"", "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ",
        "Eight ", "Nine ", "Ten ", "Eleven ", "Twelve ", "Thirteen ",
        "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ",
        "Eighteen ", "Nineteen "};
    public static String NumberInTensDigit[] = {"Twenty ", "Thirty ", "Fourty ",
        "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};

    /**
     * this method is for converting number into word
     * @param Str 
     * @return stirng
     */
    public String NumbertoWord(int Str) {
        String numberInWord = null;
        int convettingNumber = 0;
        convettingNumber = (Str);
        if (convettingNumber == 0) {
            numberInWord = "Zero";
        }
        if (convettingNumber == 100) {
            numberInWord = "Hundred";
        }
        if (convettingNumber >= 0 && convettingNumber <= 99) {
            if (convettingNumber >= 1 && convettingNumber <= 19) {
                numberInWord = NumberInSingleDigit[convettingNumber];
            } else if (convettingNumber >= 20 && convettingNumber <= 99) {
                numberInWord = NumberInTensDigit[(convettingNumber / 10) - 2];
                numberInWord =
                        numberInWord + NumberInSingleDigit[convettingNumber - ((convettingNumber / 10) * 10)];
            }
        } else if (convettingNumber > 100 && convettingNumber <= 999) {
            numberInWord = NumberInSingleDigit[(convettingNumber / 100)];
            if ("00".equals(String.valueOf(convettingNumber).substring(1, 3).toString())) {
                numberInWord = numberInWord + "Hundred";
            } else {
                numberInWord =
                        numberInWord + "Hundred " + NumbertoWord(Integer.parseInt(String.valueOf(convettingNumber).substring(1, 3).toString()));
            }
        }

        return numberInWord;
    }

    /**
     * 
     * @param ds 
     * @param personcode 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return string
     */
    public String maxValueForMultipleDisability(DataSource ds, String personcode) throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String maxDisability = null;

        try {
            con = DBConnection.getConnection();

            pstmt = con.prepareStatement("select TotalLocomotor,HearingImpairment,VisualImpairment,MentalRetardation,MentalIllness from dbo.tblPerson_Disability_TotalValue_Details with(nolock)  where Person_Code=?");
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();

            ResultSetMetaData mdata = rs.getMetaData();
            int count = mdata.getColumnCount();

            Map m = new HashMap();
            while (rs.next()) {
                for (int j = 1; j <= count; j++) {
                    m.put(new Double(rs.getDouble(j)), mdata.getColumnName(j));
                }
            }

            Set ss = m.keySet();
            Iterator it = ss.iterator();
            ArrayList arr = new ArrayList();
            while (it.hasNext()) {
                arr.add(it.next());

            }
            Collections.sort(arr);

            double maxValue = ((Double) arr.get(arr.size() - 1)).doubleValue();

            maxDisability = (String) m.get(new Double(maxValue));
            if (maxDisability.equalsIgnoreCase("TotalLocomotor")) {
                maxDisability = "Physically";
            } else if (maxDisability.equals("VisualImpairment")) {
                maxDisability = "Visual";
            } else if (maxDisability.equals("HearingImpairment")) {
                maxDisability = "Hearing Impairment";
            } else if (maxDisability.equals("MentalRetardation")) {
                maxDisability = "Mental Retardation";
            } else if (maxDisability.equals("MentalIllness")) {
                maxDisability = "Mental Illness";
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "maxValueForMultipleDisability", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "maxValueForMultipleDisability");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "maxValueForMultipleDisability", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "maxValueForMultipleDisability");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return maxDisability;

    }

    public ArrayList getLocomotorConditionDetails(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList locomotorsubdetails = new ArrayList();
        ArrayList subdetails = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String[] str = new String[20];
        String[] str1 = new String[30];
        String st = null, oh = null;
        CertificateDTO certificatedto = null;
        try {
            con = DBConnection.getConnection();

            String query = "select Person_Code,F_Can,PP_Can,L_Can,KC_Can,B_Can,S_Can,ST_Can,W_Can,RW_Can from tbl_Person_Disability_Cando_Details with(nolock) where Person_Code =?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                certificatedto = new CertificateDTO();
                certificatedto.setPersoncode(rs.getString(1));
                if (rs.getString(2) != null) {
                    st = rs.getString(2);

                    if (st.equals("1")) {
                        certificatedto.setOhcondition1("F-can perform work by manipulating with fingers ");

                    }
//                else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition1("F-can perform work by manipulating with fingers ");
//
//                }

                }
                if (rs.getString(3) != null) {
                    st = rs.getString(3);

                    if (st.equals("1")) {
                        certificatedto.setOhcondition2("PP-can perform work by pulling and pushing ");

                    }
//                 else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition2("PP-can perform work by pulling and pushing ");
//
//                }

                }
                if (rs.getString(4) != null) {
                    st = rs.getString(4);

                    if (st.equals("1")) {
                        certificatedto.setOhcondition3("L-can perform work by lifting ");

                    }
//                 else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition3("L-can perform work by lifting ");
//
//                }

                }
                if (rs.getString(5) != null) {
                    st = rs.getString(5);

                    if (st.equals("1")) {
                        certificatedto.setOhcondition4("KC-can perform work by kneeling and crouching ");

                    }
//                 else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition4("KC-can perform work by kneeling and crouching  ");
//
//                }

                }
                if (rs.getString(6) != null) {
                    st = rs.getString(6);

                    if (st.equals("1")) {
                        certificatedto.setOhcondition5("B-can perform work by bending ");

                    }
//                 else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition5("B-can perform work by bending ");
//
//                }

                }
                if (rs.getString(7) != null) {
                    st = rs.getString(7);

                    if (st.equals("1")) {
                        certificatedto.setOhcondition6("S-can perform work by sitting ");

                    }
//                 else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition6("S-can perform work by sitting ");
//
//                }

                }
                if (rs.getString(8) != null) {
                    st = rs.getString(8);

                    if (st.equals("1")) {
                        certificatedto.setOhcondition7("ST-can perform work by standing ");

                    }
//                 else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition7("PP-can perform work by standing "+oh);
//
//                }

                }
                if (rs.getString(9) != null) {
                    st = rs.getString(9);

                    if (st.equals("1")) {
                        certificatedto.setOhcondition8("W-can perform work by walking ");

                    }
//                 else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition8("W-can perform work by walking ");
//
//                }

                }
                if (rs.getString(10) != null) {
                    st = rs.getString(10);
                    if (st.equals("1")) {
                        certificatedto.setOhcondition9("RW-can perform work by reading and writing ");

                    }
//                 else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition9("RW-can perform work by reading and writing ");
//
//                }

                }
                locomotorsubdetails.add(certificatedto);

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getLocomotorConditionDetails", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getLocomotorConditionDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getLocomotorConditionDetails", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getLocomotorConditionDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return locomotorsubdetails;
    }
    /*
     * This method will get Visualsubdetail
     *  for Certificate from database
     **/

    /**
     *
     * @param ds
     * @param personcode
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getVisualConditionDetails(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList visualsubdetails = new ArrayList();
        ArrayList subdetails = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String[] str = new String[20];
        CertificateDTO certificatedto = null;
        String st = null, oh = null;
        try {
            con = DBConnection.getConnection();

            String query = "select Person_Code,SE_Can,RW_Can from tbl_Person_Disability_Cando_Details with(nolock) where Person_Code =?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                certificatedto = new CertificateDTO();
                certificatedto.setPersoncode(rs.getString(1));
                if (rs.getString(2) != null) {
                    st = rs.getString(2);
                    if (st.equals("1")) {
                        certificatedto.setOhcondition9("SE-can perform work by seeing ");

                    }
//                     else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition9("SE-can perform work by seeing ");
//
//                }



                }
                if (rs.getString(3) != null) {
                    st = rs.getString(3);
                    if (st.equals("1")) {
                        oh = "yes";
                        certificatedto.setOhcondition10("RW-can perform work by reading and writing ");

                    }
//                     else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition10("RW-can perform work by reading and writing ");
//
//                }



                }
                visualsubdetails.add(certificatedto);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVisualConditionDetails", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getVisualConditionDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVisualConditionDetails", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getVisualConditionDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return visualsubdetails;
    }
    /*
     * This method will get Hearingsubdetail
     *  for Certificate from database
     **/

    /**
     *
     * @param ds
     * @param personcode
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getHearingConditionDetails(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList hearingsubdetails = new ArrayList();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        CertificateDTO certificatedto = null;
        String st = null, oh = null;
        try {
            con = DBConnection.getConnection();

            String query = "select Person_Code,H_Can from tbl_Person_Disability_Cando_Details with(nolock)  where Person_Code =?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                certificatedto = new CertificateDTO();
                certificatedto.setPersoncode(rs.getString(1));
                if (rs.getString(2) != null) {
                    st = rs.getString(2);
                    if (st.equals("1")) {
                        certificatedto.setOhcondition11("H-can perform work by hearing/speaking ");

                    }
//                     else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition11("H-can perform work by hearing/speaking ");
//
//                }
                }
                hearingsubdetails.add(certificatedto);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHearingConditionDetails", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getHearingConditionDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHearingConditionDetails", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getHearingConditionDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return hearingsubdetails;
    }
    /*
     * This method will get MentalRetardationsubdetail
     *  for Certificate from database
     **/

    /**
     *
     * @param ds
     * @param personcode
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getMRConditionDetails(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList retardationlist = new ArrayList();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        CertificateDTO certificatedto = null;
        String st = null, oh = null;
        try {
            con = DBConnection.getConnection();

            String query = "select Person_Code,RW_Can from tbl_Person_Disability_Cando_Details with(nolock)  where Person_Code =?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                certificatedto = new CertificateDTO();
                certificatedto.setPersoncode(rs.getString(1));
                if (rs.getString(2) != null) {
                    st = rs.getString(2);
                    if (st.equals("1")) {
                        certificatedto.setOhcondition9("RW-can perform work by reading and writing ");

                    }
//                     else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition1("RW-can perform work by reading and writing ");
//
//                }
                }
                retardationlist.add(certificatedto);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMRConditionDetails", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMRConditionDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMRConditionDetails", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMRConditionDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return retardationlist;
    }

    /**
     *
     * @param ds
     * @param personcode
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getMultipleConditionDetails(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        ArrayList retardationlist = new ArrayList();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        CertificateDTO certificatedto = null;
        String st = null, oh = null;
        try {
            con = DBConnection.getConnection();

            String query = "select Person_Code,F_Can,PP_Can,L_Can,KC_Can,B_Can,S_Can,ST_Can,W_Can,SE_Can,RW_Can,H_Can from tbl_Person_Disability_Cando_Details with(nolock)  where Person_Code =?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                certificatedto = new CertificateDTO();
                certificatedto.setPersoncode(rs.getString(1));
                if (rs.getString(2) != null) {
                    st = rs.getString(2);

                    if (st.equals("1")) {
                        certificatedto.setOhcondition1("F-can perform work by manipulating with fingers ");

                    }
//                else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition1("F-can perform work by manipulating with fingers ");
//
//                }

                }
                if (rs.getString(3) != null) {
                    st = rs.getString(3);

                    if (st.equals("1")) {
                        certificatedto.setOhcondition2("PP-can perform work by pulling and pushing ");
                    }
//                else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition2("PP-can perform work by pulling and pushing ");
//
//                }

                }
                if (rs.getString(4) != null) {
                    st = rs.getString(4);

                    if (st.equals("1")) {
                        certificatedto.setOhcondition3("L-can perform work by lifting ");

                    }
//                 else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition3("L-can perform work by lifting ");
//
//                }

                }
                if (rs.getString(5) != null) {
                    st = rs.getString(5);

                    if (st.equals("1")) {
                        certificatedto.setOhcondition4("KC-can perform work by kneeling and crouching ");

                    }
//                 else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition4("KC-can perform work by kneeling and crouching  ");
//
//                }

                }
                if (rs.getString(6) != null) {
                    st = rs.getString(6);

                    if (st.equals("1")) {
                        certificatedto.setOhcondition5("B-can perform work by bending ");

                    }
//                 else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition5("B-can perform work by bending ");
//
//                }

                }
                if (rs.getString(7) != null) {
                    st = rs.getString(7);

                    if (st.equals("1")) {
                        certificatedto.setOhcondition6("S-can perform work by sitting ");

                    }
//                 else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition6("S-can perform work by sitting ");
//
//                }

                }
                if (rs.getString(8) != null) {
                    st = rs.getString(8);

                    if (st.equals("1")) {
                        certificatedto.setOhcondition7("ST-can perform work by standing ");

                    }
//                 else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition7("PP-can perform work by standing "+oh);
//
//                }

                }
                if (rs.getString(9) != null) {
                    st = rs.getString(9);

                    if (st.equals("1")) {
                        certificatedto.setOhcondition8("W-can perform work by walking ");

                    }
//                 else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition8("W-can perform work by walking ");
//
//                }

                }
                if (rs.getString(10) != null) {
                    st = rs.getString(10);

                    if (st.equals("1")) {
                        certificatedto.setOhcondition9("RW-can perform work by reading and writing ");

                    }
//                 else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition9("RW-can perform work by reading and writing ");
//
//                }

                }
                if (rs.getString(2) != null) {
                    st = rs.getString(2);
                    if (st.equals("1")) {
                        certificatedto.setOhcondition11("H-can perform work by hearing/speaking ");

                    }
//                     else if(st.equals("0")){
//                    oh="no";
//                   certificatedto.setOhcondition11("H-can perform work by hearing/speaking ");
//
//                }
                }
                retardationlist.add(certificatedto);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleConditionDetails", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMultipleConditionDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleConditionDetails", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getMultipleConditionDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return retardationlist;
    }

    public String separatewithcamma(List causeofDisabilityList) {

        StringBuffer stringBuffer = new StringBuffer();
        if (causeofDisabilityList != null && !causeofDisabilityList.isEmpty()) {

            for (int i = 0; i < causeofDisabilityList.size(); i++) {
                String causename = (String) causeofDisabilityList.get(i);
                stringBuffer.append(causename);
                if (i + 1 < causeofDisabilityList.size()) {
                    if (causeofDisabilityList.get(i + 1) != null) {
                        stringBuffer.append(",");
                    }
                } else {
                    stringBuffer.append(".");
                }
            }
        }


        return stringBuffer.toString();
    }

    public String separatewithcammamultiple(List causeofDisabilityList) throws SADAREMDBException, SQLException {

        List removedDuplicatesList = new ArrayList();
        String stringBuffer = null;
        String physicalRequirement = null;
        try {
            if (causeofDisabilityList != null && !causeofDisabilityList.isEmpty()) {

                for (int i = 0; i < causeofDisabilityList.size(); i++) {
                    String causenametemp = (String) causeofDisabilityList.get(i);
                    if (causenametemp != null && !causenametemp.isEmpty()) {
                        String causename = causenametemp.replace(".", "");
                        String[] causenamearr = causename.split(",");
                        for (int j = 0; j < causenamearr.length; j++) {
                            removedDuplicatesList.add(causenamearr[j]);

                        }
                    }
                }
                Set<String> removedDuplicateset = new HashSet<String>(removedDuplicatesList);
                // List physicalRequirementList = new ArrayList(removedDuplicateset);
                stringBuffer = removedDuplicateset.toString();
                String causenamestringBuffer = stringBuffer.replace("[", "");
                physicalRequirement = causenamestringBuffer.replace("]", "");
                //stringBuffer = separatewithcamma(physicalRequirementList);
            }
        } catch (Exception sqlEx) {
        }
        return physicalRequirement;
    }

    public TerritoryDTO locomotorDetails(ResultSet rs, TerritoryDTO territorydto) throws SADAREMDBException, SQLException {
        List locomotorSubDisabilityList = null;
        List locomotorSubSubDisabilityList = null;
        List locomotorarmlegeffectedList = null;
        List locomotorphysicalrequirements = null;
        try {
            locomotorSubDisabilityList = new ArrayList();
            locomotorphysicalrequirements = new ArrayList();
            territorydto.setDisabilitytype(CertificateConstants.LOCOMOTOR_DISABILITYTYPE);
            territorydto.setPhysicalImpairmentName(CertificateConstants.LOCOMOTOR_DISABILITYTYPE);
            territorydto.setDisabilityTypeInIdCard(CertificateConstants.IDCARD_LOCOMOTOR_DISABILITYTYPE);
            territorydto.setDisabilityTypeInTelugu(CertificateConstants.LOCOMOTOR_DISABILITYTYPE_TELUGU);
            // territorydto.setDisabilityintellec("Physical Impairment");
            //  territorydto.setLoco("locomotor");
            territorydto.setComment("yes");

            //added by bapinaidu for geting subdisabilities for locomotor disability.
            if (rs.getString("Sub_Disability_ID") != null && !"".equals(rs.getString("Sub_Disability_ID"))) {
                String[] sp = rs.getString("Sub_Disability_ID").split(",");
                for (int i = 0; i < sp.length; i++) {
                    int k = Integer.parseInt(sp[i]);
                    switch (k) {
                        case 1:
                            locomotorSubDisabilityList.add("Cerebral Palsy (CP)");
                            break;
                        case 2:
                            locomotorSubDisabilityList.add("Post Polio Residual Paralysis (PPRP)");
                            break;
                        case 3:
                            locomotorSubDisabilityList.add("Post Hansen's Disease Sequel");
                            break;
                        case 4:
                            locomotorSubDisabilityList.add("Muscular Dystrophy");
                            break;
                        case 5:
                            locomotorSubDisabilityList.add("Post Traumatic Amputation");
                            break;
                        case 6:
                            locomotorSubDisabilityList.add("Post Traumatic Sequel-Limbs");
                            break;
                        case 7:
                            locomotorSubDisabilityList.add("Post Traumatic Sequel-Spine");
                            break;
                        case 8:
                            locomotorSubDisabilityList.add("Post Head Injury Sequel");
                            break;
                        case 9:
                            locomotorSubDisabilityList.add("Post Burn Injury Sequel");
                            break;
                        case 10:
                            locomotorSubDisabilityList.add("Cerebro-Vascular Accidents");
                            break;
                        case 11:
                            locomotorSubDisabilityList.add("Dwarfism");
                            break;
                        case 12:
                            locomotorSubDisabilityList.add("Congenital Deformities of Limbs");
                            break;
                        case 13:
                            locomotorSubDisabilityList.add("Congenital Deformities of Spine");
                            break;
                        case 14:
                            locomotorSubDisabilityList.add("Cardio Pulmonary Disease");
                            break;
                        case 15:
                            if (!"null".equals(rs.getString("Any_Other")) && rs.getString("Any_Other") != null
                                    && !"".equals(rs.getString("Any_Other"))) {
                                locomotorSubDisabilityList.add(rs.getString("Any_Other"));
                            }
                            break;
                        case 32:
                            locomotorSubDisabilityList.add("Muscular Weakness");
                            break;
                        default:
                            break;
                    }
                }
                String locomotorsubdisabilities = separatewithcamma(locomotorSubDisabilityList);
                territorydto.setTotalsubdisabilities(locomotorsubdisabilities);
                territorydto.setPhysicalImpairmentTotalsubdisabilities(locomotorsubdisabilities);
            }


            if (rs.getString("Sub_Sub_Disability_ID") != null && !"".equals(rs.getString("Sub_Sub_Disability_ID"))) {
                locomotorSubSubDisabilityList = new ArrayList();
                String[] ssd = rs.getString("Sub_Sub_Disability_ID").split(",");
                for (int j = 0; j < ssd.length; j++) {
                    int s = Integer.parseInt(ssd[j]);
                    switch (s) {
                        case 1:
                            locomotorSubSubDisabilityList.add("Right Upper Limb");
                            break;
                        case 2:
                            locomotorSubSubDisabilityList.add("Left Upper Limb");
                            break;
                        case 3:
                            locomotorSubSubDisabilityList.add("Bilateral Upper Limb");
                            break;
                        case 4:
                            locomotorSubSubDisabilityList.add("Right Lower Limb");
                            break;
                        case 5:
                            locomotorSubSubDisabilityList.add("Left Lower Limb");
                            break;
                        case 6:
                            locomotorSubSubDisabilityList.add("Bilateral Lower Limb");
                            break;
                        case 7:
                            locomotorSubSubDisabilityList.add("Spine");
                            break;
                        case 8:
                            locomotorSubSubDisabilityList.add("Trunk");
                            break;
                        case 9:
                            locomotorSubSubDisabilityList.add("All 4 Limbs");
                            break;
                        case 10:
                            locomotorSubSubDisabilityList.add("Whole Body");
                            break;
                        case 11:
                            locomotorSubSubDisabilityList.add("Hips");
                            break;
                    }
                }
                String locomotosubsubdisabilities = separatewithcamma(locomotorSubSubDisabilityList);
                territorydto.setTotalsubsubdisabilities(locomotosubsubdisabilities);
            }

            if ("1".equals(rs.getString("F_Can"))) {
                locomotorphysicalrequirements.add("F-can perform work by manipulating with fingers");
            }
            if ("1".equals(rs.getString("PP_Can"))) {
                locomotorphysicalrequirements.add("PP-can perform work by pulling and pushing");
            }
            if ("1".equals(rs.getString("L_Can"))) {
                locomotorphysicalrequirements.add("L-can perform work by lifting");
            }
            if ("1".equals(rs.getString("KC_Can"))) {
                locomotorphysicalrequirements.add("KC-can perform work by kneeling and crouching");
            }
            if ("1".equals(rs.getString("B_Can"))) {
                locomotorphysicalrequirements.add("B-can perform work by bending");
            }
            if ("1".equals(rs.getString("S_Can"))) {
                locomotorphysicalrequirements.add("S-can perform work by sitting");
            }
            if ("1".equals(rs.getString("ST_Can"))) {
                locomotorphysicalrequirements.add("ST-can perform work by standing");
            }
            if ("1".equals(rs.getString("W_Can"))) {
                locomotorphysicalrequirements.add("W-can perform work by walking");
            }
            if ("1".equals(rs.getString("RW_Can"))) {
                locomotorphysicalrequirements.add("RW-can perform work by reading and writing");
            }
            if (locomotorphysicalrequirements != null && !locomotorphysicalrequirements.isEmpty()) {
                String locomotorphysicalrequirementdetails = separatewithcamma(locomotorphysicalrequirements);
                territorydto.setPhysicalImpairmentPhysicalRequirementList(locomotorphysicalrequirementdetails);
            }


        } catch (Exception sqlEx) {
            // sqlEx.printStackTrace();
        }
        return territorydto;
    }

    public TerritoryDTO hearingDetails(ResultSet rs, TerritoryDTO territorydto) throws SADAREMDBException, SQLException {

        String disabilitycondition = null;
        List locomotorphysicalrequirements = null;

        try {
            locomotorphysicalrequirements = new ArrayList();
            territorydto.setDisabilitytype(CertificateConstants.HEARING_DISABILITYTYPE);
            //territorydto.setDisabilityintellec("Hearing/Speech Impairment");
            territorydto.setHearingImpairmentName(CertificateConstants.HEARING_DISABILITYTYPE);
            territorydto.setDisabilityTypeInIdCard(CertificateConstants.HEARING_DISABILITYTYPE);
            territorydto.setDisabilityTypeInTelugu(CertificateConstants.HEARING_DISABILITYTYPE_TELUGU);
            if ("1".equals(rs.getString("H_Can"))) {
                locomotorphysicalrequirements.add("H-can perform work by hearing/speaking");
            }
            if (locomotorphysicalrequirements != null && !locomotorphysicalrequirements.isEmpty()) {
                String locomotorphysicalrequirementdetails = separatewithcamma(locomotorphysicalrequirements);
                territorydto.setHearingImpairmentPhysicalRequirementList(locomotorphysicalrequirementdetails);
            }
            if (rs.getDouble("TotalDisability") >= 40.0 && rs.getDouble("TotalDisability") <= 50.0) {
                disabilitycondition = CertificateConstants.MODERATE_DISABILITYTYPE;
            }
            if (rs.getDouble("TotalDisability") >= 51.0 && rs.getDouble("TotalDisability") <= 70.0) {
                disabilitycondition = CertificateConstants.SEVERE_DISABILITYTYPE;

            }
            if (rs.getDouble("TotalDisability") >= 71.0 && rs.getDouble("TotalDisability") <= 100.0) {
                disabilitycondition = CertificateConstants.PROFOUND_DISABILITYTYPE;
            }
            if (disabilitycondition != null && !"".equals(disabilitycondition)) {
                territorydto.setDisabilitycondition(disabilitycondition);
                territorydto.setHearingImpairmentCondition(disabilitycondition);

            }
        } catch (SQLException sqlEx) {
        } catch (Exception sqlEx) {
            //  sqlEx.printStackTrace();
        }
        return territorydto;
    }

    public TerritoryDTO visualDetails(ResultSet rs, TerritoryDTO territorydto) throws SADAREMDBException, SQLException {

        List locomotorphysicalrequirements = null;
        List locomotorSubDisabilityList = null;
        try {
            locomotorSubDisabilityList = new ArrayList();
            locomotorphysicalrequirements = new ArrayList();
            territorydto.setDisabilitytype(CertificateConstants.VISUAL_DISABILITYTYPE);
            territorydto.setVisualImpairmentName(CertificateConstants.VISUAL_DISABILITYTYPE);
            territorydto.setComment("yes");
            if (territorydto.getVisualImpairmentPercentage() == 40) {
                territorydto.setVisualImpairmentTotalsubdisabilities("Low Vision");
                territorydto.setDisabilityTypeInIdCard(CertificateConstants.IDCARD_VISUAL_LOWVISION_DISABILITYTYPE);
                territorydto.setDisabilityTypeInTelugu(CertificateConstants.VISUAL_DISABILITYTYPE_LOWVISION_TELUGU);
            } else if (territorydto.getVisualImpairmentPercentage() == 75 || territorydto.getVisualImpairmentPercentage() == 100 || territorydto.getVisualImpairmentPercentage() == 30) {
                territorydto.setVisualImpairmentTotalsubdisabilities("Blind ness");
                territorydto.setDisabilityTypeInIdCard(CertificateConstants.IDCARD_VISUAL_BLINDNESS_DISABILITYTYPE);
                territorydto.setDisabilityTypeInTelugu(CertificateConstants.VISUAL_DISABILITYTYPE_BLINDNESS_TELUGU);
            }
            if ("1".equals(rs.getString("RW_Can"))) {
                locomotorphysicalrequirements.add("RW-can perform work by reading and writing");
            }
            if ("1".equals(rs.getString("SE_Can"))) {
                locomotorphysicalrequirements.add("SE-can perform work by seeing");
            }
            if (locomotorphysicalrequirements != null && !locomotorphysicalrequirements.isEmpty()) {
                String locomotorphysicalrequirementdetails = separatewithcamma(locomotorphysicalrequirements);
                territorydto.setVisualImpairmentPhysicalRequirementList(locomotorphysicalrequirementdetails);
            }

        } catch (Exception sqlEx) {
            // sqlEx.printStackTrace();
        }
        return territorydto;
    }

    public TerritoryDTO mentalRetardationDetails(ResultSet rs, TerritoryDTO territorydto) throws SADAREMDBException, SQLException {

        List locomotorphysicalrequirements = null;
        String disabilitycondition = null;
        try {
            locomotorphysicalrequirements = new ArrayList();
            territorydto.setDisabilitytype(CertificateConstants.MENTALRETARDATION_DISABILITYTYPE);
            // territorydto.setDisabilityintellec("Mental Retardation");
            territorydto.setMentalRetardationName(CertificateConstants.MENTALRETARDATION_DISABILITYTYPE);
            territorydto.setDisabilityTypeInIdCard(CertificateConstants.MENTALRETARDATION_DISABILITYTYPE);
            territorydto.setDisabilityTypeInTelugu(CertificateConstants.MENTALRETARDATION_DISABILITYTYPE_TELUGU);
            if ("1".equals(rs.getString("RW_Can"))) {
                locomotorphysicalrequirements.add("RW-can perform work by reading and writing");
            }
            if (locomotorphysicalrequirements != null && !locomotorphysicalrequirements.isEmpty()) {
                String locomotorphysicalrequirementdetails = separatewithcamma(locomotorphysicalrequirements);
                territorydto.setMentalRetardationPhysicalRequirementList(locomotorphysicalrequirementdetails);
            }
            if (rs.getDouble("TotalDisability") == 50.0) {
                disabilitycondition = CertificateConstants.MILD_DISABILITYTYPE;
            }

            if (rs.getDouble("TotalDisability") == 75.0) {
                disabilitycondition = CertificateConstants.MODERATE_DISABILITYTYPE;
            }

            if (rs.getDouble("TotalDisability") == 90.0) {
                disabilitycondition = CertificateConstants.SEVERE_DISABILITYTYPE;
            }

            if (rs.getDouble("TotalDisability") == 100.0) {
                disabilitycondition = CertificateConstants.PROFOUND_DISABILITYTYPE;
            }
            if (disabilitycondition != null && !"".equals(disabilitycondition)) {
                territorydto.setDisabilitycondition(disabilitycondition);
                territorydto.setMentalRetardationCondition(disabilitycondition);
            }
        } catch (Exception sqlEx) {
            // sqlEx.printStackTrace();
        }
        return territorydto;
    }

    public TerritoryDTO mentalIllnessDetails(ResultSet rs, TerritoryDTO territorydto) throws SADAREMDBException, SQLException {

        String disabilitycondition = null;
        try {
            territorydto.setDisabilitytype(CertificateConstants.MENTALILLNESS_DISABILITYTYPE);
            // territorydto.setDisabilityintellec("Mental Illness");
            territorydto.setMentalIllnessName(CertificateConstants.MENTALILLNESS_DISABILITYTYPE);
            territorydto.setDisabilityTypeInIdCard(CertificateConstants.MENTALILLNESS_DISABILITYTYPE);
            territorydto.setDisabilityTypeInTelugu(CertificateConstants.MENTALILLNESS_DISABILITYTYPE_TELUGU);

            if (rs.getDouble("TotalDisability") >= 40.0 && rs.getDouble("TotalDisability") <= 70.0) {
                disabilitycondition = CertificateConstants.MODERATE_DISABILITYTYPE;
            }
            if (rs.getDouble("TotalDisability") >= 71.0 && rs.getDouble("TotalDisability") <= 99.0) {
                disabilitycondition = CertificateConstants.SEVERE_DISABILITYTYPE;
            }
            if (rs.getDouble("TotalDisability") == 100.0) {
                disabilitycondition = CertificateConstants.PROFOUND_DISABILITYTYPE;
            }
            if (disabilitycondition != null && !"".equals(disabilitycondition)) {
                territorydto.setDisabilitycondition(disabilitycondition);
                territorydto.setMentalIllnessCondition(disabilitycondition);
            }

        } catch (Exception sqlEx) {
            // sqlEx.printStackTrace();
        }
        return territorydto;
    }

    public ArrayList getSHGAbstract(DataSource ds, String districtId, String mandalId, HttpServletRequest request) throws Exception {

        ArrayList<Object> shgList = new ArrayList<Object>();
        Map shgData = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {

            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call [sp_Report7.1_ShGs_abstractreport](?,?)}");
            cstmt.setString(1, districtId);
            cstmt.setString(2, mandalId);
            rs = cstmt.executeQuery();
            int total_pensioners = 0;
            int age18Pensioners = 0;
            int age18to55Pensioners = 0;
            int age55Pensioners = 0;
            int deadPensioners = 0;
            int migratant_Pensioners = 0;
            int pensioners_Who_can_be_deleted = 0;
            int eligible_pensioners = 0;
            int totalSHGMembers = 0;
            int percentageSHGMembers = 0;
            int i = 1;
            if (rs != null) {
                while (rs.next()) {
                    shgData = new HashMap();
                    shgData.put("sno", i);
                    shgData.put("districtId", rs.getString(1));
                    shgData.put("madnalId", rs.getString(2));
                    shgData.put("districtName", rs.getString(3));
                    shgData.put("madnalName", rs.getString(4));
                    shgData.put("total_pensioners", rs.getString(5));
                    shgData.put("Age18Pensioners", rs.getString(6));
                    shgData.put("Age18to55Pensioners", rs.getString(7));
                    shgData.put("Age55Pensioners", rs.getString(8));
                    shgData.put("DeadPensioners", rs.getString(9));
                    shgData.put("Migratant_Pensioners", rs.getString(10));
                    shgData.put("Pensioners_Who_can_be_deleted", rs.getString(11));
                    shgData.put("Eligible_pensioners", rs.getString(12));
                    shgData.put("totalSHGMembers", rs.getString(13));
                    shgData.put("percentageSHGMembers", rs.getString(14));
                    total_pensioners = total_pensioners + rs.getInt(5);
                    age18Pensioners = age18Pensioners + rs.getInt(6);
                    age18to55Pensioners = age18to55Pensioners + rs.getInt(7);
                    age55Pensioners = age55Pensioners + rs.getInt(8);
                    deadPensioners = deadPensioners + rs.getInt(9);
                    migratant_Pensioners = migratant_Pensioners + rs.getInt(10);
                    pensioners_Who_can_be_deleted = pensioners_Who_can_be_deleted + rs.getInt(11);
                    eligible_pensioners = eligible_pensioners + rs.getInt(12);
                    totalSHGMembers = totalSHGMembers + rs.getInt(13);
                    percentageSHGMembers = percentageSHGMembers + rs.getInt(14);
                    shgList.add(shgData);
                    i++;
                }

                if (shgList.size() > 0) {
                    ArrayList totalList = new ArrayList();
                    shgData = new HashMap();
                    //float f=
//                    float total = percentageSHGMembers /shgList.size();
//
//                    String a = Float.toString(total);
//
//                    int p = a.lastIndexOf(".");
//

                    shgData.put("madnalName", "Total");
                    shgData.put("total_pensioners", total_pensioners);
                    shgData.put("Age18Pensioners", age18Pensioners);
                    shgData.put("Age18to55Pensioners", age18to55Pensioners);
                    shgData.put("Age55Pensioners", age55Pensioners);
                    shgData.put("DeadPensioners", deadPensioners);
                    shgData.put("Migratant_Pensioners", migratant_Pensioners);
                    shgData.put("Pensioners_Who_can_be_deleted", pensioners_Who_can_be_deleted);
                    shgData.put("Eligible_pensioners", eligible_pensioners);
                    shgData.put("totalSHGMembers", totalSHGMembers);

                    shgData.put("percentageSHGMembers", percentageSHGMembers);
                    totalList.add(shgData);
                    request.setAttribute("totalList", totalList);
                }
            }
        } catch (SQLException sqlEx) {
            // sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSHGAbstract", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getSHGAbstract");
        } catch (Exception sqlEx) {
            // sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSHGAbstract", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getSHGAbstract");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }

        return shgList;
    }

    public ArrayList getShgPersonalDetails(DataSource ds, String districtId, String mandalId, String flag) throws Exception {

        ArrayList<Object> shgList = new ArrayList<Object>();
        Map<String, String> shgData = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        Connection con = null;
        try {
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call [sp_Report7.1_ShGs_mandalwise_detail](?,?,?)}");

            cstmt.setString(1, districtId);
            cstmt.setString(2, mandalId);
            cstmt.setString(3, flag);
            rs = cstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    shgData = new HashMap<String, String>();
                    if (flag.equalsIgnoreCase("9")) {
                        shgData.put("SADAREMCODE", rs.getString(1));
                        shgData.put("personName", rs.getString(2));
                        shgData.put("relationName", rs.getString(3));
                        shgData.put("education", rs.getString(4));
                        shgData.put("emp", rs.getString(5));
                        shgData.put("martitalStatus", rs.getString(6));
                        shgData.put("disabledperscentage", rs.getString(7));
                        shgData.put("caste", rs.getString(8));
                        shgData.put("districtName", rs.getString(9));
                        shgData.put("mandalName", rs.getString(10));
                        shgData.put("villageName", rs.getString(11));
                        shgData.put("habitationName", rs.getString(12));
                        shgData.put("flag", flag);
                        // shgList.add(shgData);
                    } else {

                        if(rs.getString(1)!=null){
                            shgData.put("pensionId", rs.getString(1));
                        }else{
                              shgData.put("pensionId","-");
                        }

                        if(rs.getString(2)!=null){
                             shgData.put("habCode", rs.getString(2));
                        }else{
                             shgData.put("habCode", "-");
                        }

                        if(rs.getString(3)!=null){
                             shgData.put("SADAREMCODE", rs.getString(3));
                        }else{
                             shgData.put("SADAREMCODE", "-");
                        }

                        if(rs.getString(4)!=null){
                             shgData.put("personName", rs.getString(4));
                        }else{
                             shgData.put("personName", "-");
                        }


                        if(rs.getString(5)!=null){
                             shgData.put("gender", rs.getString(5));
                        }else{
                             shgData.put("gender", "-");
                        }

                        if(rs.getString(6)!=null){
                            shgData.put("age", rs.getString(6));
                        }else{
                             shgData.put("age", "-");
                        }

                        if(rs.getString(7)!=null){
                             shgData.put("education", rs.getString(7));
                        }else{
                             shgData.put("education", "-");
                        }


                        if( rs.getString(8)!=null){
                             shgData.put("caste", rs.getString(8));
                        }else{
                             shgData.put("caste","-");
                        }


                        if(rs.getString(9)!=null){
                              shgData.put("relationName", rs.getString(9));
                        }else{
                              shgData.put("relationName", "-");
                        }


                        if(rs.getString(10)!=null){
                            shgData.put("rationCardNo", rs.getString(10));
                        }else{
                            shgData.put("rationCardNo", "-");
                        }


                        if(rs.getString(11)!=null){
                            shgData.put("typeOfDisability", rs.getString(11));
                        }else{
                             shgData.put("typeOfDisability", "-");
                        }


                        if(rs.getString(12)!=null){
                            shgData.put("assesmentStatus", rs.getString(12));
                        }else{
                             shgData.put("assesmentStatus", rs.getString(12));
                        }
                        if (rs.getString(13) != null && rs.getString(13) != "") {
                            shgData.put("houseNo", rs.getString(13));
                        } else {
                            shgData.put("houseNo", "--");
                        }
                        if (rs.getString(14) != null) {
                            shgData.put("habitationName", rs.getString(14));
                        } else {
                            shgData.put("habitationName", "--");
                        }

                        if (rs.getString(15) != null) {
                            shgData.put("villageName", rs.getString(15));
                        } else {
                            shgData.put("villageName", "--");
                        }

                        if (rs.getString(16) != null) {
                            shgData.put("mandalName", rs.getString(16));
                        } else {
                            shgData.put("mandalName", "--");
                        }

                        if (rs.getString(17) != null) {
                            shgData.put("districtName", rs.getString(17));
                        } else {
                            shgData.put("districtName", "--");
                        }

                        if (rs.getString(18) != null) {
                            shgData.put("phoneNo", rs.getString(18));
                        } else {
                            shgData.put("phoneNo", "--");
                        }
                        shgData.put("flag", flag);

                    }
                    shgList.add(shgData);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getShgPersonalDetails", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getShgPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getShgPersonalDetails", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getShgPersonalDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }

        return shgList;

    }

    public ArrayList getSHGEligibleReport(DataSource ds, String district_id, String mandal_id, String panchayat_id, String village_id) throws SADAREMDBException, SQLException {
        ArrayList<Object> shgList = new ArrayList<Object>();
        Map shgData = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            String district = "All";
            String mandal = "All";
            String panchayat = "All";
            String village = "All";


            if (district_id != null && !district_id.equals("0") && !district_id.equals("null")) {
                district = district_id;
            }
            if (mandal_id != null && !mandal_id.equals("0") && !mandal_id.equals("null")) {
                mandal = mandal_id;
            }
            if (panchayat_id != null && !panchayat_id.equals("0") && !panchayat_id.equals("null")) {
                panchayat = panchayat_id;
            }
            if (village_id != null && !village_id.equals("0") && !village_id.equals("null")) {
                village = village_id;
            }
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call sp_ShGs_abstractreport_eligible(?,?,?,?)}");
            cstmt.setString(1, district);
            cstmt.setString(2, mandal);
            cstmt.setString(3, panchayat);
            cstmt.setString(4, village);


            rs = cstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    shgData = new HashMap();

                    shgData.put("id", rs.getString(1));
                    shgData.put("name", rs.getString(2));
                    shgData.put("count", rs.getInt(3));

                    shgList.add(shgData);

                }
            }



        } catch (SQLException sqlEx) {
            // sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSHGEligibleReport", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getSHGEligibleReport");
        } catch (Exception sqlEx) {
            // sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSHGEligibleReport", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getSHGEligibleReport");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }

        return shgList;
    }

    public ArrayList getShgEligiblePersonalDetails(DataSource ds, String district_id, String mandal_id, String panchayat_id, String village_id, String requestedId, String flag) throws SADAREMDBException, SQLException {
        ArrayList<Object> shgList = new ArrayList<Object>();
        Map shgData = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();

            StringBuffer sb = new StringBuffer();
            sb.append("SELECT DISTINCT coalesce(P.PENSIONCARD_NO,'-') AS PENSIONID,P.HABCODE ,P.PERSON_CODE AS SADAREMCODE,"
                    + " coalesce(P.SURNAME,'-') + SPACE(2) + P.FIRST_NAME AS PERSONNAME , 'GENDER' = CASE WHEN Gender = 1 THEN 'Male' WHEN Gender = 2 THEN "
                    + "'Female' ELSE 'Not Entered' END, coalesce(DATEDIFF (year ,P.date_of_birth,getdate()),'-') age_years,'EDUCATION' = CASE WHEN P.EDUCATION = 1 THEN 'Illiterate' WHEN P.EDUCATION = 2"
                    + " THEN 'Below 10th' WHEN P.EDUCATION = 3 THEN '10th Class' WHEN P.EDUCATION = 4 THEN 'Intermediate' WHEN P.EDUCATION = 5"
                    + " THEN 'Diploma/I.T.I' WHEN P.EDUCATION = 6 THEN 'Graduate' WHEN P.EDUCATION = 7 THEN 'Postgraduate' ELSE 'Not Entered' "
                    + "END,'Employment' = CASE WHEN P.Employment = 1 THEN 'Govt' WHEN P.Employment = 2 THEN 'Private' WHEN P.Employment = 3 "
                    + "THEN 'Self-Employed' WHEN P.Employment = 4 THEN 'Un-Employed' WHEN P.Employment = 5 THEN 'Wage-Employee' ELSE 'Not Entered' END,"
                    + "'MARITALSTATUS' =CASE WHEN P.MARITAL_STATUS = 1 THEN 'MARRIED' WHEN P.MARITAL_STATUS = 2 THEN 'UNMARRIED' WHEN P.MARITAL_STATUS = 3 THEN"
                    + " 'DIVORCEE' WHEN P.MARITAL_STATUS = 4 THEN 'WIDOW' WHEN P.MARITAL_STATUS = 5 THEN 'WIDOWER' ELSE 'Not Entered' END,"
                    + " 'CASTE' = CASE WHEN P.CASTE = 1 THEN 'ST' WHEN P.CASTE = 2 THEN 'SC' WHEN P.CASTE = 3 THEN 'BC' WHEN P.CASTE = 4 THEN 'OC' "
                    + "WHEN P.CASTE = 5 THEN 'Minority' WHEN P.CASTE = 6 THEN 'NA' ELSE 'Not Entered' END,P.RELATION_NAME AS RELATIONNAME ,"
                    + "coalesce( P.RATIONCARD_NUMBER,'-') RATIONCARD_NUMBER,'TYPEOFDISABILITY' = CASE WHEN PD.DISABILITY_ID = 1 THEN 'Locomotor/OH' WHEN PD.DISABILITY_ID = 2 THEN 'Visual Impairment' "
                    + "WHEN PD.DISABILITY_ID = 3 THEN 'Hearing Impairment' WHEN PD.DISABILITY_ID = 4 THEN 'Mental Retardation' WHEN PD.DISABILITY_ID = 5 THEN 'Mental Illness' "
                    + "WHEN PD.DISABILITY_ID = 6 THEN 'Multiple Disability' ELSE 'Not Entered' END, 'ASSESSEMENTSTATUS' = CASE WHEN PD.CategoryID = 1 THEN 'Regular' "
                    + "WHEN PD.CategoryID = 2 THEN 'Reassessed' WHEN PD.CategoryID = 3 THEN 'Temporary' ELSE 'Not Entered' END,"
                    + "house_number +','+f.habitation_name+','+e.village_name+','+d.mandal_name+','+s.district_name Address,coalesce(phone_no,'-')phone_no,"
                    + "'Assessed' Assessmentstatus,pt.totaldisability FROM DBO.TBLPERSON_PERSONAL_DETAILS P  with (nolock) , DBO.TBLPERSON_DISABILITY_DETAILS PD with(nolock) , "
                    + "DBO.TBLPERSON_DISABILITY_TOTALVALUE_DETAILS PT with(nolock)  ,DBO.tbldistrict_details s with(nolock) ,DBO.tblmandal_details d with(nolock) ,"
                    + "DBO.tblvillage_details e with(nolock) ,DBO.tblHabitation_Details f with(nolock)  WHERE PD.PERSON_CODE      = P.PERSON_CODE  AND PT.PERSON_CODE      = P.PERSON_CODE "
                    + " AND PD.PERSON_CODE      = PT.PERSON_CODE AND p.district_id = s.district_id and p.district_id = d.district_id and "
                    + "p.mandal_id = d.mandal_id and p.district_id = e.district_id and p.mandal_id = e.mandal_id and p.village_id = e.village_id and "
                    + "p.habcode = f.habitation_code and  p.district_id = e.district_id and "
                    + "p.mandal_id = e.mandal_id and p.village_id = e.village_id and p.habcode = f.habitation_code and p.district_id = ? ");

            if (mandal_id != null && mandal_id.trim().equalsIgnoreCase("0"))
            {
                sb.append(" and p.mandal_id = ? ");
            }
            else if (panchayat_id != null && panchayat_id.trim().equalsIgnoreCase("0")) 
            {
                sb.append(" and p.mandal_id = ?  and substring(habcode,13,2) = ? ");
            } 
            else if (village_id != null && village_id.trim().equalsIgnoreCase("0"))
            {
                sb.append(" and p.mandal_id = ?  and substring(habcode,13,2) = ? and p.village_id = ? ");
            } 
            else if (village_id != null && !village_id.trim().equalsIgnoreCase("0"))
            {
                sb.append(" and p.mandal_id = ?  and substring(habcode,13,2) = ? and p.village_id = ? and p.Habitation_ID=?  ");
            }


            sb.append(" and pt.totaldisability > = 40 and DATEDIFF (year ,date_of_birth,getdate()) < = 55   AND P.STATUS='ACTIVE'   AND PD.STATUS='ACTIVE' AND PT.STATUS='ACTIVE' order by PERSONNAME");

            ps = con.prepareStatement(sb.toString());
            ps.setString(1, district_id);
            if (mandal_id != null && mandal_id.trim().equalsIgnoreCase("0"))
            {
            	ps.setString(2, requestedId.trim());
            }
            else if (panchayat_id != null && panchayat_id.trim().equalsIgnoreCase("0")) 
            {
            	ps.setString(2,mandal_id.trim());
            	ps.setString(3,requestedId.trim());
            } 
            else if (village_id != null && village_id.trim().equalsIgnoreCase("0"))
            {
            	ps.setString(2,mandal_id.trim());
            	ps.setString(3,panchayat_id.trim());
            	ps.setString(4,requestedId.trim());
            } 
            else if (village_id != null && !village_id.trim().equalsIgnoreCase("0"))
            {
            	ps.setString(2,mandal_id.trim());
            	ps.setString(3,panchayat_id.trim());
            	ps.setString(4,village_id.trim());
            	ps.setString(5,requestedId.trim());
            }
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    shgData = new HashMap<String, String>();
                    shgData.put("PERSONNAME", rs.getString("PERSONNAME"));
                    shgData.put("SADAREMCODE", rs.getString("SADAREMCODE"));
                    shgData.put("PENSIONID", rs.getString("PENSIONID"));
                    shgData.put("RATIONCARD_NUMBER", rs.getString("RATIONCARD_NUMBER"));
                    shgData.put("Gender", rs.getString("GENDER"));
                    shgData.put("age_years", rs.getString("age_years"));
                    shgData.put("EDUCATION", rs.getString("EDUCATION"));
                    shgData.put("CASTE", rs.getString("CASTE"));
                    shgData.put("RELATIONNAME", rs.getString("RELATIONNAME"));
                    shgData.put("TYPEOFDISABILITY", rs.getString("TYPEOFDISABILITY"));
                    shgData.put("ADDRESS", rs.getString("Address"));
                    shgData.put("phone_no", rs.getString("phone_no"));
                    shgData.put("DISABILITY", rs.getInt("totaldisability"));
                    shgList.add(shgData);
                }
            }



        } catch (SQLException sqlEx) {
            // sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSHGEligibleReport", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getSHGEligibleReport");
        } catch (Exception sqlEx) {
            //  sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSHGEligibleReport", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getSHGEligibleReport");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(ps);

        }

        return shgList;
    }

    /**
     *
     * @param ds
     * @param reportDTO
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public ArrayList getCMReport(DataSource ds,
            ReportDTO reportDTO)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        ArrayList<ReportDTO> reportList = new ArrayList<ReportDTO>();
        district_id = reportDTO.getDistrict_id();
        mandal_id = reportDTO.getMandal_id();
        village_id = reportDTO.getVillage_id();
        String total = "0";
        int totalApplied = 0, totalAssessed = 0, totalrejected = 0;
        int totaleligible = 0, totalohAssessed = 0, totalohEligible = 0;
        int totalohRejected = 0, totalViAssessed = 0, totalViEligible = 0, totalViRejected = 0, totalHiAssessed = 0;
        int totalHiEligible = 0, totalHiRejected = 0;
        int totalMREligible = 0, totalMRRejected = 0, totalMRAssessed = 0;
        int totalMiEligible = 0, totalMiRejected = 0, totalMiAssessed = 0;
        int totalMDEligible = 0, totalMDRejected = 0, totalMDAssessed = 0;
        BigDecimal s = new BigDecimal("100.00");
        MathContext mc = new MathContext(2);
        try {
            con = DBConnection.getConnection();
            if (district_id == "0") {
                cstmt = con.prepareCall("{Call [Sp_Report1.1_Sadarem_Assessed_Detailsfor_State]}");

            } else if (mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call [Sp_Report1.1_Sadarem_Assessed_Detailsfor_District](?)}");
                cstmt.setString(1, district_id);
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call [Sp_Report1.1_Sadarem_Assessed_Detailsfor_Mandal](?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
            } else {
                cstmt = con.prepareCall("{Call [Sp_Report1.1_Sadarem_Assessed_Detailsfor_Village](?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
            }

            rs = cstmt.executeQuery();
            while (rs.next()) {
                ReportDTO reportDetailsDTO =
                        new ReportDTO();
                if (district_id == "0") {
                    reportDetailsDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    reportDetailsDTO.setDistrict_name(rs.getString("DISTRICT_NAME"));
                } else if (mandal_id.equals("0")) {
                    reportDetailsDTO.setDistrict_id(district_id);

                    reportDetailsDTO.setMandal_id(rs.getString("mandal_id"));
                    reportDetailsDTO.setMandal_name(rs.getString("mandal_name"));
                } else if (village_id.equals("0")) {
                    reportDetailsDTO.setDistrict_id(district_id);
                    reportDetailsDTO.setMandal_id(mandal_id);
                    reportDetailsDTO.setVillage_id(rs.getString("village_id"));
                    reportDetailsDTO.setVillage_name(rs.getString("village_name"));
                } else {
                    reportDetailsDTO.setDistrict_id(district_id);
                    reportDetailsDTO.setMandal_id(mandal_id);
                    reportDetailsDTO.setVillage_id(village_id);
                    reportDetailsDTO.setHabitation_id(rs.getString("habitation_code"));
                    reportDetailsDTO.setHabitation_name(rs.getString("habitation_name"));
                }

                reportDetailsDTO.setTotalAppliedCM(NumberFormat.getIntegerInstance().format(rs.getInt("totalapplied")));

                reportDetailsDTO.setTotalAssessedCM(NumberFormat.getIntegerInstance().format(rs.getInt("totalassessed")));
                reportDetailsDTO.setRejectedCM(NumberFormat.getIntegerInstance().format(rs.getInt("rejected")));
                reportDetailsDTO.setEligibleCM(NumberFormat.getIntegerInstance().format(rs.getInt("eligible")));







                reportDetailsDTO.setOhassessed(NumberFormat.getIntegerInstance().format(rs.getInt("OHTotalAssessed")));
                reportDetailsDTO.setViassessed(NumberFormat.getIntegerInstance().format(rs.getInt("VITotalAssessed")));
                reportDetailsDTO.setHiassessed(NumberFormat.getIntegerInstance().format(rs.getInt("HITotalAssessed")));
                reportDetailsDTO.setMrassessed(NumberFormat.getIntegerInstance().format(rs.getInt("MRTotalAssessed")));
                reportDetailsDTO.setMiassessed(NumberFormat.getIntegerInstance().format(rs.getInt("MITotalAssessed")));
                reportDetailsDTO.setMdassessed(NumberFormat.getIntegerInstance().format(rs.getInt("MDTotalAssessed")));

                //Rejected
                reportDetailsDTO.setOhrejected(NumberFormat.getIntegerInstance().format(rs.getInt("OHRejected")));
                reportDetailsDTO.setVirejected(NumberFormat.getIntegerInstance().format(rs.getInt("VIRejected")));
                reportDetailsDTO.setHirejected(NumberFormat.getIntegerInstance().format(rs.getInt("HIRejected")));
                reportDetailsDTO.setMrrejected(NumberFormat.getIntegerInstance().format(rs.getInt("MRRejected")));

                reportDetailsDTO.setMirejected(NumberFormat.getIntegerInstance().format(rs.getInt("MIRejected")));
                reportDetailsDTO.setMdrejected(NumberFormat.getIntegerInstance().format(rs.getInt("MDRejected")));


                //Eleigible
                reportDetailsDTO.setOheligible(NumberFormat.getIntegerInstance().format(rs.getInt("OHEligible")));
                reportDetailsDTO.setVieligible(NumberFormat.getIntegerInstance().format(rs.getInt("VIEligible")));
                reportDetailsDTO.setHieligible(NumberFormat.getIntegerInstance().format(rs.getInt("HIEligible")));
                reportDetailsDTO.setMreligible(NumberFormat.getIntegerInstance().format(rs.getInt("MREligible")));
                reportDetailsDTO.setMieligible(NumberFormat.getIntegerInstance().format(rs.getInt("MIEligible")));
                reportDetailsDTO.setMdeligible(NumberFormat.getIntegerInstance().format(rs.getInt("MDEligible")));

                if (rs.getInt("totalapplied") != 0) {
                    reportDetailsDTO.setCertificatePercentage(s.subtract(round(((rs.getFloat("totalassessed") / rs.getFloat("totalapplied")) * 100), 2)));

                } else {
                    float c = 0;
                    reportDetailsDTO.setCertificatePercentage(round(c, 2));
                }
                // reportDetailsDTO.setCertificatePercentage(s.subtract(round(((rs.getFloat("totalassessed") / rs.getFloat("totalapplied")) * 100), 2)));

                reportDetailsDTO.setShowDifference(NumberFormat.getIntegerInstance().format(rs.getInt("totalapplied") - rs.getInt("totalassessed")));

//                total = NumberFormat.getIntegerInstance().format(rs.getInt("oh") + rs.getInt("vi") + rs.getInt("hi") + rs.getInt("mr")
//                        + rs.getInt("mi") + rs.getInt("md"));
//                reportDetailsDTO.setTotalCM(total);



                totalApplied = totalApplied + rs.getInt("totalapplied");
                totalAssessed = totalAssessed + rs.getInt("totalassessed");
                totalrejected = totalrejected + rs.getInt("rejected");
                totaleligible = totaleligible + rs.getInt("eligible");
                //Grnad Total for Oh
                totalohAssessed = totalohAssessed + rs.getInt("OHTotalAssessed");
                totalohEligible = totalohEligible + rs.getInt("OHEligible");
                totalohRejected = totalohRejected + rs.getInt("OHRejected");
                //Grnad Total for Vi
                totalViAssessed = totalViAssessed + rs.getInt("VITotalAssessed");
                totalViEligible = totalViEligible + rs.getInt("VIEligible");
                totalViRejected = totalViRejected + rs.getInt("VIRejected");

                //Grnad Total for HI
                totalHiAssessed = totalHiAssessed + rs.getInt("HITotalAssessed");
                totalHiEligible = totalHiEligible + rs.getInt("HIEligible");
                totalHiRejected = totalHiRejected + rs.getInt("HIRejected");

                //Grnad Total for MR
                totalMRAssessed = totalMRAssessed + rs.getInt("MRTotalAssessed");
                totalMREligible = totalMREligible + rs.getInt("MREligible");
                totalMRRejected = totalMRRejected + rs.getInt("MRRejected");

                //Grnad Total for MI
                totalMiAssessed = totalMiAssessed + rs.getInt("MITotalAssessed");
                totalMiEligible = totalMiEligible + rs.getInt("MIEligible");
                totalMiRejected = totalMiRejected + rs.getInt("MIRejected");

                //Grnad Total for MI
                totalMDAssessed = totalMDAssessed + rs.getInt("MDTotalAssessed");
                totalMDEligible = totalMDEligible + rs.getInt("MDEligible");
                totalMDRejected = totalMDRejected + rs.getInt("MDRejected");

//                totalmr = totalmr + rs.getInt("mr");
//                totalmi = totalmi + rs.getInt("mi");
//                totalmd = totalmd + rs.getInt("md");
//                totaltotal = totaloh + totalvi + totalhi + totalmr + totalmi + totalmd;

                reportList.add(reportDetailsDTO);

            }
            ReportDTO reportDetailsDTO =
                    new ReportDTO();
            reportDetailsDTO.setGrandtotalAppliedCM(NumberFormat.getIntegerInstance().format(totalApplied));
            //reportDetailsDTO.setTotalApplied(0);
            reportDetailsDTO.setGrandtotalAssessedCM(NumberFormat.getIntegerInstance().format(totalAssessed));
            reportDetailsDTO.setTotalrejectedCM(NumberFormat.getIntegerInstance().format(totalrejected));
            reportDetailsDTO.setTotaleligibleCM(NumberFormat.getIntegerInstance().format(totaleligible));

            //Oh Total
            reportDetailsDTO.setGrandtotalOhAssessed(NumberFormat.getIntegerInstance().format(totalohAssessed));
            reportDetailsDTO.setGrandtotalOhEligible(NumberFormat.getIntegerInstance().format(totalohEligible));
            reportDetailsDTO.setGrandtotalOhRejected(NumberFormat.getIntegerInstance().format(totalohRejected));

            //VI Total
            reportDetailsDTO.setGrandtotalViAssessed(NumberFormat.getIntegerInstance().format(totalViAssessed));
            reportDetailsDTO.setGrandtotalViEligible(NumberFormat.getIntegerInstance().format(totalViEligible));
            reportDetailsDTO.setGrandtotalViRejected(NumberFormat.getIntegerInstance().format(totalViRejected));

            //HI Total
            reportDetailsDTO.setGrandtotalHiAssessed(NumberFormat.getIntegerInstance().format(totalHiAssessed));
            reportDetailsDTO.setGrandtotalHiEligible(NumberFormat.getIntegerInstance().format(totalHiEligible));
            reportDetailsDTO.setGrandtotalHiRejected(NumberFormat.getIntegerInstance().format(totalHiRejected));

            //MR Total
            reportDetailsDTO.setGrandtotalMrAssessed(NumberFormat.getIntegerInstance().format(totalMRAssessed));
            reportDetailsDTO.setGrandtotalMrEligible(NumberFormat.getIntegerInstance().format(totalMREligible));
            reportDetailsDTO.setGrandtotalMrRejected(NumberFormat.getIntegerInstance().format(totalMRRejected));


            //MI Total
            reportDetailsDTO.setGrandtotalMiAssessed(NumberFormat.getIntegerInstance().format(totalMiAssessed));
            reportDetailsDTO.setGrandtotalMiEligible(NumberFormat.getIntegerInstance().format(totalMiEligible));
            reportDetailsDTO.setGrandtotalMiRejected(NumberFormat.getIntegerInstance().format(totalMiRejected));

            //MD Total
            reportDetailsDTO.setGrandtotalMdAssessed(NumberFormat.getIntegerInstance().format(totalMDAssessed));
            reportDetailsDTO.setGrandtotalMdEligible(NumberFormat.getIntegerInstance().format(totalMDEligible));
            reportDetailsDTO.setGrandtotalMdRejected(NumberFormat.getIntegerInstance().format(totalMDRejected));

//            reportDetailsDTO.setTotalmrCM(NumberFormat.getIntegerInstance().format(totalmr));
//            reportDetailsDTO.setTotalmiCM(NumberFormat.getIntegerInstance().format(totalmi));
//            reportDetailsDTO.setTotalmdCM(NumberFormat.getIntegerInstance().format(totalmd));
//            reportDetailsDTO.setTotaltotalCM(NumberFormat.getIntegerInstance().format(totaltotal));

            //System.out.println("3456666===="+s.subtract(round(((Float.parseFloat(Integer.toString(totalAssessed)) / Float.parseFloat(Integer.toString(totalApplied))) * 100), 2));

            if (totalApplied != 0) {
                reportDetailsDTO.setCertificatePercentage(s.subtract(round(((Float.parseFloat(Integer.toString(totalAssessed)) / Float.parseFloat(Integer.toString(totalApplied))) * 100), 2)));

            } else {
                float c = 0;
                reportDetailsDTO.setCertificatePercentage(round(c, 2));
            }

            reportDetailsDTO.setShowDifference(NumberFormat.getIntegerInstance().format(totalApplied - totalAssessed));

            reportList.add(reportDetailsDTO);
        } catch (SQLException sqlEx) {
            ExceptionDAO.saveException(ds, sqlEx.toString(), "getCMReport", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getCMReport");
        } catch (Exception sqlEx) {
            // sqlEx.printStackTrace();
            ExceptionDAO.saveException(ds, sqlEx.toString(), "getCMReport", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getCMReport");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return reportList;
    }

    public ArrayList getIndividualDashBoardDetails(DataSource ds, ReportDTO reportDTO) throws SADAREMDBException, SQLException {
        ArrayList individualDashBoardList = new ArrayList();
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String query = null;
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String habitationCode = null;
        Map map = null;
        try {

            con = DBConnection.getConnection();
            districtId = reportDTO.getDistrict_id();
            mandalId = reportDTO.getMandal_id();
            villageId = reportDTO.getVillage_id();
            habitationCode = reportDTO.getHabitation_id();

            if (districtId != null) {
                cstmt = con.prepareCall("{Call [Sp_Report1.1_Sadarem_Assessed_Individual_Detailsfor_District](?,?)}");
                cstmt.setString(1, districtId);
                cstmt.setString(2, reportDTO.getFlag());
            }
            if (districtId != null && mandalId != null) {
                cstmt = con.prepareCall("{Call [Sp_Report1.1_Sadarem_Assessed_Individual_Detailsfor_mandal](?,?,?)}");

                cstmt.setString(1, districtId);
                cstmt.setString(2, mandalId);
                cstmt.setString(3, reportDTO.getFlag());
            }
            if (districtId != null && mandalId != null && villageId != null) {
                cstmt = con.prepareCall("{Call [Sp_Report1.1_Sadarem_Assessed_Individual_Detailsfor_village](?,?,?,?)}");

                cstmt.setString(1, districtId);
                cstmt.setString(2, mandalId);
                cstmt.setString(3, villageId);
                cstmt.setString(4, reportDTO.getFlag());
            }
            if (districtId != null && mandalId != null && villageId != null && habitationCode != null) {

                cstmt = con.prepareCall("{Call [Sp_Report1.1_Sadarem_Assessed_Individual_Detailsfor_Habitation](?,?,?,?,?)}");
                cstmt.setString(1, districtId);
                cstmt.setString(2, mandalId);
                cstmt.setString(3, villageId);
                cstmt.setString(4, habitationCode);
                cstmt.setString(5, reportDTO.getFlag());
            }

            rs = cstmt.executeQuery();
            if ( rs != null) 
            {
                while (rs.next()) {
                    map = new HashMap();
                    if (rs.getString(1) != null) {
                        map.put("pensionId", rs.getString(1));
                    } else {
                        map.put("pensionId", "--");
                    }
                    if (rs.getString(2) != null) {
                        map.put("habCode", rs.getString(2));
                    } else {
                        map.put("habCode", "--");
                    }

                    if (rs.getString(3) != null) {
                        map.put("SADAREMCODE", rs.getString(3));
                    } else {
                        map.put("SADAREMCODE", "--");
                    }
                    if (rs.getString(4) != null) {
                        map.put("personName", rs.getString(4));
                    } else {
                        map.put("personName", "--");
                    }

                    if (rs.getString(5) != null) {
                        map.put("gender", rs.getString(5));
                    } else {
                        map.put("gender", "--");
                    }
                    if (rs.getString(6) != null) {
                        map.put("age", rs.getString(6));
                    } else {
                        map.put("age", "--");
                    }
                    if (rs.getString(7) != null) {
                        map.put("education", rs.getString(7));
                    } else {
                        map.put("education", "--");
                    }
                    if (rs.getString(8) != null) {
                        map.put("caste", rs.getString(8));
                    } else {
                        map.put("caste", "--");
                    }
                    if (rs.getString(9) != null) {
                        map.put("relationName", rs.getString(9));
                    } else {
                        map.put("relationName", "--");
                    }

                    if (rs.getString(10) != null) {
                        map.put("rationCardNo", rs.getString(10));
                    } else {
                        map.put("rationCardNo", "--");
                    }
                    if (rs.getString(11) != null) {
                        map.put("typeOfDisability", rs.getString(11));
                    } else {
                        map.put("typeOfDisability", "--");
                    }

                    if (rs.getString(12) != null) {
                        map.put("percentage", rs.getString(12));
                    } else {
                        map.put("percentage", "--");
                    }

                    if (rs.getString(13) != null) {
                        map.put("assessmentStatus", rs.getString(13));
                    } else {
                        map.put("assessmentStatus", "--");
                    }


                    if (rs.getString(14) != null) {
                        map.put("houseNo", rs.getString(14));
                    } else {
                        map.put("houseNo", "--");
                    }

                    if (rs.getString(15) != null) {
                        map.put("habitationName", rs.getString(15));
                    } else {
                        map.put("habitationName", "--");

                    }
                    if (rs.getString(16) != null) {
                        map.put("villageName", rs.getString(16));
                    } else {
                        map.put("villageName", "--");
                    }

                    if (rs.getString(17) != null) {
                        map.put("mandalName", rs.getString(17));
                    } else {
                        map.put("mandalName", "--");
                    }
                    if (rs.getString(18) != null) {
                        map.put("districtName", rs.getString(18));
                    } else {
                        map.put("districtName", "--");
                    }

                    if (rs.getString(19) != null) {
                        map.put("updatedDate", rs.getString(19));
                    } else {
                        map.put("updatedDate", "--");
                    }
                    if (rs.getString(20) != null) {
                        map.put("phoneNo", rs.getString(20));
                    } else {
                        map.put("phoneNo", "--");
                    }
                    individualDashBoardList.add(map);
                }

            }

            cstmt.close();
            con.close();
            rs.close();
            
            
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getIndividualDashBoardDetails", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getIndividualDashBoardDetails");
        } catch (Exception sqlEx) {
            //  sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getIndividualDashBoardDetails", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getIndividualDashBoardDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }

        return individualDashBoardList;

    }

    public ArrayList collectorReport(DataSource ds,
            ReportDTO reportDTO)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        ArrayList<ReportDTO> reportList = new ArrayList<ReportDTO>();
        district_id = reportDTO.getDistrict_id();
        String givenFromDate = reportDTO.getFromdate();
        String givenToDate = reportDTO.getTodate();
        int total = 0;

        try {
            CommonDAO commonDAO = new CommonDAO();
            DailyDisabilityAndPercentageDAO dailyDisabilityAndPercentageDAO = new DailyDisabilityAndPercentageDAO();
            con = DBConnection.getConnection();

            if (reportDTO.getTypeOfSearch() != null) {
                if (reportDTO.getTypeOfSearch().equalsIgnoreCase("FinancialYear")) {

                    String[] financialYear = reportDTO.getFinancialYear().split("-");
                    int givenYear = Integer.parseInt(financialYear[0]);
                    givenFromDate = "01/04/" + givenYear;
                    givenToDate = dailyDisabilityAndPercentageDAO.getAssignedDate(givenYear + 1, ds, givenFromDate);

                } else if (reportDTO.getTypeOfSearch().equalsIgnoreCase("Month")) {
                    givenFromDate = commonDAO.getMonthStartDate(ds, reportDTO.getMonth(), reportDTO.getYear());
                    givenToDate = commonDAO.getMonthEndtDate(ds, reportDTO.getMonth(), reportDTO.getYear());
                }
            } else {
                reportDTO.setReportType("Camp");
                reportDTO.setCampId("All");
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                //get current date time with Date()
                Date date = new Date();
                java.util.Date da = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(da);
                cal.add(Calendar.MONTH, -1);
                da = cal.getTime();
                givenFromDate = dateFormat.format(da);
                givenToDate = dateFormat.format(date);
            }
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(givenFromDate);
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(givenToDate);
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);

            con = DBConnection.getConnection();
            if (reportDTO.getReportType() != null && reportDTO.getReportType().equalsIgnoreCase("Mandal")) {

                cstmt = con.prepareCall("{Call SP_CM_DASHBOARD_District_New(?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
            } else if (reportDTO.getReportType() != null && reportDTO.getReportType().equalsIgnoreCase("Camp")) {
                cstmt = con.prepareCall("{Call SP_CM_DASHBOARD_Camp_New(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, reportDTO.getCampId());
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                rs = cstmt.executeQuery();
            }

            if (rs != null) {
                while (rs.next()) {

                    ReportDTO reportDetailsDTO =
                            new ReportDTO();
                    if (reportDTO.getReportType() != null && reportDTO.getReportType().equalsIgnoreCase("Mandal")) {
                        reportDetailsDTO.setMandal_id(rs.getString("mandal_ID"));
                        reportDetailsDTO.setMandal_name(rs.getString("mandal_NAME"));
                    } else if (reportDTO.getReportType() != null && reportDTO.getReportType().equalsIgnoreCase("Camp")) {
                        reportDetailsDTO.setCampName(rs.getString("name"));
                        reportDetailsDTO.setCampVenue(rs.getString("venuename"));

                    }
                    reportDetailsDTO.setTotalApplied(rs.getInt("totalapplied"));
                    //reportDetailsDTO.setTotalApplied(0);
                    reportDetailsDTO.setTotalAssessed(rs.getInt("totalassessed"));
                    reportDetailsDTO.setRejected(rs.getInt("rejected"));
                    reportDetailsDTO.setEligible(rs.getInt("eligible"));
                    reportDetailsDTO.setOh(rs.getInt("oh"));
                    reportDetailsDTO.setVi(rs.getInt("vi"));
                    reportDetailsDTO.setHi(rs.getInt("hi"));
                    reportDetailsDTO.setMr(rs.getInt("mr"));
                    reportDetailsDTO.setMi(rs.getInt("mi"));
                    reportDetailsDTO.setMd(rs.getInt("md"));
                    total = rs.getInt("oh") + rs.getInt("vi") + rs.getInt("hi") + rs.getInt("mr")
                            + rs.getInt("mi") + rs.getInt("md");
                    reportDetailsDTO.setTotal(total);
                    reportList.add(reportDetailsDTO);

                }
            }
        } catch (SQLException sqlEx) {
            //  sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "collectorReport", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "collectorReport");
        } catch (Exception sqlEx) {
            //  sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "collectorReport", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "collectorReport");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return reportList;
    }

    public ArrayList directorReport(DataSource ds,
            ReportDTO reportDTO)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String district_name = null;

        String mandal_id = reportDTO.getMandal_id();
        String village_id = reportDTO.getVillage_id();
        ArrayList<ReportDTO> reportList = new ArrayList<ReportDTO>();
        district_id = reportDTO.getDistrict_id();
        district_name = reportDTO.getDistrict_name();
        String givenFromDate = reportDTO.getFromdate();
        String givenToDate = reportDTO.getTodate();
        int total = 0;
        HashMap map = null;

        try {
            CommonDAO commonDAO = new CommonDAO();
            DailyDisabilityAndPercentageDAO dailyDisabilityAndPercentageDAO = new DailyDisabilityAndPercentageDAO();
            con = DBConnection.getConnection();

            if (reportDTO.getTypeOfSearch() != null) {
                if (reportDTO.getTypeOfSearch().equalsIgnoreCase("FinancialYear")) {

                    String[] financialYear = reportDTO.getFinancialYear().split("-");
                    int givenYear = Integer.parseInt(financialYear[0]);
                    givenFromDate = "01/04/" + givenYear;
                    givenToDate = dailyDisabilityAndPercentageDAO.getAssignedDate(givenYear + 1, ds, givenFromDate);

                } else if (reportDTO.getTypeOfSearch().equalsIgnoreCase("Month")) {
                    givenFromDate = commonDAO.getMonthStartDate(ds, reportDTO.getMonth(), reportDTO.getYear());
                    givenToDate = commonDAO.getMonthEndtDate(ds, reportDTO.getMonth(), reportDTO.getYear());
                }
            } else {
                // reportDTO.setReportType("Camp");
                // reportDTO.setCampId("All");
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                //get current date time with Date()
                Date date = new Date();
                java.util.Date da = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(da);
                cal.add(Calendar.MONTH, -1);
                da = cal.getTime();
                givenFromDate = dateFormat.format(da);
                givenToDate = dateFormat.format(date);
            }
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(givenFromDate);
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date fdate1 = new SimpleDateFormat("dd/mm/yyyy").parse(givenFromDate);
            String fromdate1 = new SimpleDateFormat("dd/mm/yyyy").format(fdate1);
            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(givenToDate);
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
            Date tdate1 = new SimpleDateFormat("dd/mm/yyyy").parse(givenToDate);
            String todate1 = new SimpleDateFormat("dd/mm/yyyy").format(tdate1);
            con = DBConnection.getConnection();
            if (reportDTO.getReportType() != null && reportDTO.getReportType().equalsIgnoreCase("Disability")) {
                if (village_id != null && !village_id.equalsIgnoreCase("") && !village_id.equalsIgnoreCase("0")) {
                    cstmt = con.prepareCall("{Call [Sp_Report1.2_Sadarem_Assessed_DISABILITY_Detailsfor_village](?,?,?,?,?)}");
                    cstmt.setString(1, reportDTO.getDistrict_id());
                    cstmt.setString(2, reportDTO.getMandal_id());
                    cstmt.setString(3, reportDTO.getVillage_id());
                    cstmt.setString(4, fromdate);
                    cstmt.setString(5, todate);
                } else if (mandal_id != null && !mandal_id.equalsIgnoreCase("") && !mandal_id.equalsIgnoreCase("0")) {
                    cstmt = con.prepareCall("{Call [Sp_Report1.2_Sadarem_Assessed_DISABILITY_Detailsfor_mandal](?,?,?,?)}");
                    cstmt.setString(1, reportDTO.getDistrict_id());
                    cstmt.setString(2, reportDTO.getMandal_id());
                    cstmt.setString(3, fromdate);
                    cstmt.setString(4, todate);
//                     } else if (mandal_id != null && mandal_id != "" && mandal_id != "0") {
                } else if (district_id != null &&  !district_id.equalsIgnoreCase("")  && !district_id.equalsIgnoreCase("0")) {
                    cstmt = con.prepareCall("{Call [Sp_Report1.2_Sadarem_Assessed_DISABILITY_Detailsfor_district](?,?,?)}");
                    cstmt.setString(1, reportDTO.getDistrict_id());
                    cstmt.setString(2, fromdate);
                    cstmt.setString(3, todate);
                } else {
                    cstmt = con.prepareCall("{Call [Sp_Report1.2_Sadarem_Assessed_DISABILITY_Detailsfor_state](?,?)}");
                    cstmt.setString(1, fromdate);
                    cstmt.setString(2, todate);
                }
                rs = cstmt.executeQuery();
            } else {
                if (reportDTO.getReportType() != null && reportDTO.getReportType().equalsIgnoreCase("District")) {
                    cstmt = con.prepareCall("{Call SP_DIRECTORDASHBOARD_STATE_New(?,?)}");
                    cstmt.setString(1, fromdate);
                    cstmt.setString(2, todate);
                    rs = cstmt.executeQuery();
                } else if (reportDTO.getReportType() != null && reportDTO.getReportType().equalsIgnoreCase("Mandal")) {
                    reportDTO.setDistrict_id(district_id);
                    cstmt = con.prepareCall("{Call SP_CM_DASHBOARD_District_New(?,?,?)}");
                    cstmt.setString(1, district_id);
                    cstmt.setString(2, fromdate);
                    cstmt.setString(3, todate);
                    rs = cstmt.executeQuery();
                } else if (reportDTO.getReportType() != null && reportDTO.getReportType().equalsIgnoreCase("Camp")) {
                    cstmt = con.prepareCall("{Call SP_CM_DASHBOARD_Camp_New(?,?,?,?)}");
                    cstmt.setString(1, district_id);
                    cstmt.setString(2, reportDTO.getCampId());
                    cstmt.setString(3, fromdate);
                    cstmt.setString(4, todate);
                    rs = cstmt.executeQuery();
                }

            }
            int totalOHassessed = 0, totalVIassessed = 0, totalHIassessed = 0, totalMRassessed = 0, totalMIassessed = 0, totalMDassessed = 0,
                    totalOHeligible = 0, totalVIeligible = 0, totalHIeligible = 0, totalMReligible = 0, totalMIeligible = 0, totalMDeligible = 0,
                    totalOHrejected = 0, totalVIrejected = 0, totalHIrejected = 0, totalMRrejected = 0, totalMIrejected = 0, totalMDrejected = 0,
                    totalAssessmentDisability = 0, totalEligibleDisability = 0, totalRejectedDisability = 0;
           
            if (rs != null) {
                if (reportDTO.getReportType() != null && reportDTO.getReportType().equalsIgnoreCase("Disability")) {
                    while (rs.next()) {

                        ReportDTO reportDetailsDTO = new ReportDTO();

                        reportDetailsDTO.setTypeOfSearch(reportDTO.getTypeOfSearch());
                        reportDetailsDTO.setReportType(reportDTO.getReportType());
                        reportDetailsDTO.setDistrict_id(district_id);
                        reportDetailsDTO.setDistrict_name(district_name);
                        reportDetailsDTO.setMandal_id(mandal_id);
                        reportDetailsDTO.setVillage_id(village_id);
                        reportDetailsDTO.setFromdate(fromdate1);
                        reportDetailsDTO.setTodate(todate1);
                        //reportDetailsDTO.setTodate(todate);

                        if (village_id != null && !village_id.equalsIgnoreCase("") && !village_id.equalsIgnoreCase("0")) {
                            reportDetailsDTO.setHabitation_id(rs.getString("habitation_code"));
                            reportDetailsDTO.setHabitation_name(rs.getString("habitation_name"));
                        } else if (mandal_id != null &&  !mandal_id.equalsIgnoreCase("") && !mandal_id.equalsIgnoreCase("0")) {

                            reportDetailsDTO.setVillage_id(rs.getString("village_id"));
                            reportDetailsDTO.setVillage_name(rs.getString("village_name"));
                            reportDetailsDTO.setDistrict_name(district_name);
                            // } else if (mandal_id != null && mandal_id != "" && mandal_id != "0") {
                        } else if (district_id != null && !district_id.equalsIgnoreCase("")  && !district_id.equalsIgnoreCase("0")) {
                            reportDetailsDTO.setMandal_id(rs.getString("mandal_id"));
                            reportDetailsDTO.setMandal_name(rs.getString("mandal_name"));
                            reportDetailsDTO.setDistrict_name(district_name);
                        } else {
                            reportDetailsDTO.setDistrict_id(rs.getString("district_id"));
                            reportDetailsDTO.setDistrict_name(rs.getString("district_name"));

                        }

                        reportDetailsDTO.setOhassessed(rs.getString(3));
                        reportDetailsDTO.setViassessed(rs.getString(4));
                        reportDetailsDTO.setHiassessed(rs.getString(5));
                        reportDetailsDTO.setMrassessed(rs.getString(6));
                        reportDetailsDTO.setMiassessed(rs.getString(7));
                        reportDetailsDTO.setMdassessed(rs.getString(8));
                        reportDetailsDTO.setOhrejected(rs.getString(9));
                        reportDetailsDTO.setVirejected(rs.getString(10));
                        reportDetailsDTO.setHirejected(rs.getString(11));
                        reportDetailsDTO.setMrrejected(rs.getString(12));
                        reportDetailsDTO.setMirejected(rs.getString(13));
                        reportDetailsDTO.setMdrejected(rs.getString(14));
                        reportDetailsDTO.setOheligible(rs.getString(15));
                        reportDetailsDTO.setVieligible(rs.getString(16));
                        reportDetailsDTO.setHieligible(rs.getString(17));
                        reportDetailsDTO.setMreligible(rs.getString(18));
                        reportDetailsDTO.setMieligible(rs.getString(19));
                        reportDetailsDTO.setMdeligible(rs.getString(20));
                        reportDetailsDTO.setTotalAssessedDis(rs.getString(21));
                        reportDetailsDTO.setTotalEligible(rs.getString(22));
                        reportDetailsDTO.setTotalRejected(rs.getString(23));

                        reportList.add(reportDetailsDTO);
                        //total
                        totalOHassessed = totalOHassessed + rs.getInt(3);
                        totalVIassessed = totalVIassessed + rs.getInt(4);
                        totalHIassessed = totalHIassessed + rs.getInt(5);
                        totalMRassessed = totalMRassessed + rs.getInt(6);
                        totalMIassessed = totalMIassessed + rs.getInt(7);
                        totalMDassessed = totalMDassessed + rs.getInt(8);

                        totalOHrejected = totalOHrejected + rs.getInt(9);
                        totalVIrejected = totalVIrejected + rs.getInt(10);
                        totalHIrejected = totalHIrejected + rs.getInt(11);
                        totalMRrejected = totalMRrejected + rs.getInt(12);
                        totalMIrejected = totalMIrejected + rs.getInt(13);
                        totalMDrejected = totalMDrejected + rs.getInt(14);

                        totalOHeligible = totalOHeligible + rs.getInt(15);
                        totalVIeligible = totalVIeligible + rs.getInt(16);
                        totalHIeligible = totalHIeligible + rs.getInt(17);
                        totalMReligible = totalMReligible + rs.getInt(18);
                        totalMIeligible = totalMIeligible + rs.getInt(19);
                        totalMDeligible = totalMDeligible + rs.getInt(20);
                        totalAssessmentDisability = totalAssessmentDisability + rs.getInt(21);
                        totalEligibleDisability = totalEligibleDisability + rs.getInt(22);
                        totalRejectedDisability = totalRejectedDisability + rs.getInt(23);
                    }
                    if (reportList.size() > 0) {
                        ReportDTO reportDetailsDTO = new ReportDTO();
                        reportDetailsDTO.setTotalOHassessed(totalOHassessed);
                        reportDetailsDTO.setTotalVIassessed(totalVIassessed);
                        reportDetailsDTO.setTotalHIassessed(totalHIassessed);
                        reportDetailsDTO.setTotalMRassessed(totalMRassessed);
                        reportDetailsDTO.setTotalMIassessed(totalMIassessed);
                        reportDetailsDTO.setTotalMDassessed(totalMDassessed);

                        reportDetailsDTO.setTotalOHrejected(totalOHrejected);
                        reportDetailsDTO.setTotalVIrejected(totalVIrejected);
                        reportDetailsDTO.setTotalHIrejected(totalHIrejected);
                        reportDetailsDTO.setTotalMRrejected(totalMRrejected);
                        reportDetailsDTO.setTotalMIrejected(totalMIrejected);
                        reportDetailsDTO.setTotalMDrejected(totalMDrejected);

                        reportDetailsDTO.setTotalOHeligible(totalOHeligible);
                        reportDetailsDTO.setTotalVIeligible(totalVIeligible);
                        reportDetailsDTO.setTotalHIeligible(totalHIeligible);
                        reportDetailsDTO.setTotalMReligible(totalMReligible);
                        reportDetailsDTO.setTotalMIeligible(totalMIeligible);
                        reportDetailsDTO.setTotalMDeligible(totalMDeligible);
                        reportDetailsDTO.setTotalAssessmentDisability(totalAssessmentDisability);
                        reportDetailsDTO.setTotalEligibleDisability(totalEligibleDisability);
                        reportDetailsDTO.setTotalRejectedDisability(totalRejectedDisability);
                        reportList.add(reportDetailsDTO);
                        //total end
                    }

                } else {
                    while (rs.next()) {

                        ReportDTO reportDetailsDTO = new ReportDTO();
                        if (reportDTO.getReportType() != null && reportDTO.getReportType().equalsIgnoreCase("District")) {
                            reportDetailsDTO.setDistrict_id(rs.getString("district_id"));
                            reportDetailsDTO.setDistrict_name(rs.getString("district_name"));
                        } else if (reportDTO.getReportType() != null && reportDTO.getReportType().equalsIgnoreCase("Mandal")) {
                            reportDetailsDTO.setMandal_id(rs.getString("mandal_ID"));
                            reportDetailsDTO.setMandal_name(rs.getString("mandal_NAME"));
                            reportDetailsDTO.setDistrict_id(district_id);
                        } else if (reportDTO.getReportType() != null && reportDTO.getReportType().equalsIgnoreCase("Camp")) {

                            reportDetailsDTO.setDistrict_id(district_id);
                            reportDetailsDTO.setCampId(reportDTO.getCampId());
                            reportDetailsDTO.setCampName(rs.getString("name"));
                            reportDetailsDTO.setCampVenue(rs.getString("address"));

                        }
                        reportDetailsDTO.setTotalApplied(rs.getInt("totalapplied"));
                        //reportDetailsDTO.setTotalApplied(0);
                        reportDetailsDTO.setTotalAssessed(rs.getInt("totalassessed"));
                        reportDetailsDTO.setRejected(rs.getInt("rejected"));
                        reportDetailsDTO.setEligible(rs.getInt("eligible"));
                        reportDetailsDTO.setOh(rs.getInt("oh"));
                        reportDetailsDTO.setVi(rs.getInt("vi"));
                        reportDetailsDTO.setHi(rs.getInt("hi"));
                        reportDetailsDTO.setMr(rs.getInt("mr"));
                        reportDetailsDTO.setMi(rs.getInt("mi"));
                        reportDetailsDTO.setMd(rs.getInt("md"));
                        total = rs.getInt("oh") + rs.getInt("vi") + rs.getInt("hi") + rs.getInt("mr")
                                + rs.getInt("mi") + rs.getInt("md");
                        reportDetailsDTO.setTotal(total);
                        reportList.add(reportDetailsDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            //  sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "directorReport", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "directorReport");
        } catch (Exception sqlEx) {
            // sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "directorReport", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "directorReport");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }

        return reportList;
    }

    public ArrayList getCMReport_v1(DataSource ds,
            ReportDTO reportDTO)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        ArrayList<ReportDTO> reportList = new ArrayList<ReportDTO>();
        district_id = reportDTO.getDistrict_id();
        mandal_id = reportDTO.getMandal_id();
        village_id = reportDTO.getVillage_id();
        int totalApplied = 0, totalAssessed = 0, totalrejected = 0;
        int totaleligible = 0, totaleligiblebelow80 = 0, totaleligibleabove80 = 0;
        int totaltotal = 0;
        BigDecimal s = new BigDecimal("100.00");
        MathContext mc = new MathContext(2);

        try {
            con = DBConnection.getConnection();
            if (district_id == "0") {

                cstmt = con.prepareCall("{Call SP_CM_DASHBOARD_STATE_v1()}");
            } else if (mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_CM_DASHBOARD_District_v1(?)}");
                cstmt.setString(1, district_id);
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_CM_DASHBOARD_Mandal_v1(?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
            } else {
                cstmt = con.prepareCall("{Call SP_CM_DASHBOARD_VILLAGE_v1(?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
            }

            rs = cstmt.executeQuery();
            while (rs.next()) {
                ReportDTO reportDetailsDTO = new ReportDTO();
                if (district_id == "0") {
                    reportDetailsDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    reportDetailsDTO.setDistrict_name(rs.getString("DISTRICT_NAME"));
                } else if (mandal_id.equals("0")) {
                    reportDetailsDTO.setDistrict_id(district_id);
                    reportDetailsDTO.setMandal_id(rs.getString("mandal_id"));
                    reportDetailsDTO.setMandal_name(rs.getString("mandal_name"));
                } else if (village_id.equals("0")) {
                    reportDetailsDTO.setDistrict_id(district_id);
                    reportDetailsDTO.setMandal_id(mandal_id);
                    reportDetailsDTO.setVillage_id(rs.getString("village_id"));
                    reportDetailsDTO.setVillage_name(rs.getString("village_name"));
                } else {
                    reportDetailsDTO.setDistrict_id(district_id);
                    reportDetailsDTO.setMandal_id(mandal_id);
                    reportDetailsDTO.setVillage_id(village_id);
                    reportDetailsDTO.setHabitation_id(rs.getString("habitation_code"));
                    reportDetailsDTO.setHabitation_name(rs.getString("habitation_name"));
                }

                reportDetailsDTO.setTotalAppliedCM(NumberFormat.getIntegerInstance().format(rs.getInt("totalapplied")));
                //reportDetailsDTO.setTotalApplied(0);
                reportDetailsDTO.setTotalAssessedCM(NumberFormat.getIntegerInstance().format(rs.getInt("totalassessed")));
                reportDetailsDTO.setRejectedCM(NumberFormat.getIntegerInstance().format(rs.getInt("rejected")));
                reportDetailsDTO.setEligibleCM(NumberFormat.getIntegerInstance().format(rs.getInt("eligible")));
                reportDetailsDTO.setEligiblebelow80(NumberFormat.getIntegerInstance().format(rs.getInt("eligible40to79")));
                reportDetailsDTO.setEligibleabove80(NumberFormat.getIntegerInstance().format(rs.getInt("eligible80above")));
                if (rs.getInt("totalapplied") != 0) {
                    reportDetailsDTO.setCertificatePercentage(s.subtract(round(((rs.getFloat("totalassessed") / rs.getFloat("totalapplied")) * 100), 2)));
                } else {
                    float c = 0;
                    reportDetailsDTO.setCertificatePercentage(round(c, 2));
                }

                reportDetailsDTO.setShowDifference(NumberFormat.getIntegerInstance().format(rs.getInt("totalapplied") - rs.getInt("totalassessed")));
                reportDetailsDTO.setTotalCM(NumberFormat.getIntegerInstance().format(rs.getInt("totalpensioners")));

                reportDetailsDTO.setAssessedPercentage(round(rs.getFloat("totalassessedpercentage"), 2));
                reportDetailsDTO.setRejectedPercentage(round(rs.getFloat("rejectedpercentage"), 2));
                reportDetailsDTO.setEligiblePercentage(round(rs.getFloat("eligiblepercentage"), 2));
                reportDetailsDTO.setEligiblebelow80Percentage(round(rs.getFloat("eligible40to79percentage"), 2));
                reportDetailsDTO.setEligibleabove80Percentage(round(rs.getFloat("eligible80abovepercentage"), 2));
                reportDetailsDTO.setPensionerPercentage(round(rs.getFloat("pensionerspercentage"), 2));

                totalApplied = totalApplied + rs.getInt("totalapplied");
                totalAssessed = totalAssessed + rs.getInt("totalassessed");
                totalrejected = totalrejected + rs.getInt("rejected");
                totaleligible = totaleligible + rs.getInt("eligible");
                totaleligiblebelow80 = totaleligiblebelow80 + rs.getInt("eligible40to79");
                totaleligibleabove80 = totaleligibleabove80 + rs.getInt("eligible80above");
                totaltotal = totaltotal + rs.getInt("totalpensioners");


                reportList.add(reportDetailsDTO);

            }
            ReportDTO reportDetailsDTO =
                    new ReportDTO();
            reportDetailsDTO.setGrandtotalAppliedCM(NumberFormat.getIntegerInstance().format(totalApplied));
            //reportDetailsDTO.setTotalApplied(0);
            reportDetailsDTO.setGrandtotalAssessedCM(NumberFormat.getIntegerInstance().format(totalAssessed));
            reportDetailsDTO.setTotalrejectedCM(NumberFormat.getIntegerInstance().format(totalrejected));
            reportDetailsDTO.setTotaleligibleCM(NumberFormat.getIntegerInstance().format(totaleligible));
            reportDetailsDTO.setTotaleligiblebelow80(NumberFormat.getIntegerInstance().format(totaleligiblebelow80));
            reportDetailsDTO.setTotaleligibleabove80(NumberFormat.getIntegerInstance().format(totaleligibleabove80));
            reportDetailsDTO.setTotaltotalCM(NumberFormat.getIntegerInstance().format(totaltotal));
            if (totalApplied != 0) {
                reportDetailsDTO.setCertificatePercentage(s.subtract(round(((Float.parseFloat(Integer.toString(totalAssessed)) / Float.parseFloat(Integer.toString(totalApplied))) * 100), 2)));
                reportDetailsDTO.setTotalassessedPercentage(round(((Float.parseFloat(Integer.toString(totalAssessed)) / Float.parseFloat(Integer.toString(totalApplied))) * 100), 2));
            } else {
                float c = 0;
                reportDetailsDTO.setCertificatePercentage(round(c, 2));
                reportDetailsDTO.setTotalassessedPercentage(round(0, 2));

            }

            reportDetailsDTO.setShowDifference(NumberFormat.getIntegerInstance().format((totalApplied - totalAssessed)));

            if (totalAssessed != 0) {
                reportDetailsDTO.setTotalrejectedPercentage(round(((Float.parseFloat(Integer.toString(totalrejected)) / Float.parseFloat(Integer.toString(totalAssessed))) * 100), 2));
                reportDetailsDTO.setTotaleligiblePercentage(round(((Float.parseFloat(Integer.toString(totaleligible)) / Float.parseFloat(Integer.toString(totalAssessed))) * 100), 2));
                reportDetailsDTO.setTotaleligiblebelow80Percentage(round(((Float.parseFloat(Integer.toString(totaleligiblebelow80)) / Float.parseFloat(Integer.toString(totalAssessed))) * 100), 2));
                reportDetailsDTO.setTotaleligibleabove80Percentage(round(((Float.parseFloat(Integer.toString(totaleligibleabove80)) / Float.parseFloat(Integer.toString(totalAssessed))) * 100), 2));
                reportDetailsDTO.setTotalpensionerPercentage(round(((Float.parseFloat(Integer.toString(totaltotal)) / Float.parseFloat(Integer.toString(totalAssessed))) * 100), 2));

            } else {
                reportDetailsDTO.setTotalrejectedPercentage(round(0, 2));
                reportDetailsDTO.setTotaleligiblePercentage(round(0, 2));
                reportDetailsDTO.setTotaleligiblebelow80Percentage(round(0, 2));
                reportDetailsDTO.setTotaleligibleabove80Percentage(round(0, 2));
                reportDetailsDTO.setTotalpensionerPercentage(round(0, 2));
            }

            reportList.add(reportDetailsDTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCMReport_v1", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getCMReport_v1");
        } catch (Exception sqlEx) {
            // sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCMReport_v1", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getCMReport_v1");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return reportList;
    }

    public ArrayList individualDetails(DataSource ds, ReportDTO reportDTO, String heading)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String district_name = null;

        String mandal_id = reportDTO.getMandal_id();
        String village_id = reportDTO.getVillage_id();
        String habitation_id = reportDTO.getHabcode();
        ArrayList reportList = new ArrayList<ReportDTO>();
        district_id = reportDTO.getDistrict_id();
        district_name = reportDTO.getDistrict_name();
        String givenFromDate = reportDTO.getFromdate();
        String givenToDate = reportDTO.getTodate();
        int total = 0;
        HashMap map = null;

        try {
            CommonDAO commonDAO = new CommonDAO();
            DailyDisabilityAndPercentageDAO dailyDisabilityAndPercentageDAO = new DailyDisabilityAndPercentageDAO();
            con = DBConnection.getConnection();
            if (reportDTO.getTypeOfSearch() != null) {
                if (reportDTO.getTypeOfSearch().equalsIgnoreCase("FinancialYear")) {
                    String[] financialYear = reportDTO.getFinancialYear().split("-");
                    int givenYear = Integer.parseInt(financialYear[0]);
                    givenFromDate = "01/04/" + givenYear;
                    givenToDate = dailyDisabilityAndPercentageDAO.getAssignedDate(givenYear + 1, ds, givenFromDate);

                } else if (reportDTO.getTypeOfSearch().equalsIgnoreCase("Month")) {

                    givenFromDate = commonDAO.getMonthStartDate(ds, reportDTO.getMonth(), reportDTO.getYear());
                    givenToDate = commonDAO.getMonthEndtDate(ds, reportDTO.getMonth(), reportDTO.getYear());
                }
            } else {
            }
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(givenFromDate);
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(givenToDate);
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            if (heading.split(",")[3].equals("Disability") || heading.split(",")[2].equals("Disability")) {

                if (habitation_id != null && habitation_id != "" && habitation_id != "0") {
                    cstmt = con.prepareCall("{Call [Sp_Report1.2_Sadarem_Assessed_Individual_Detailsfor_disability_Habitation](?,?,?,?,?,?,?)}");
                    cstmt.setString(1, reportDTO.getDistrict_id());
                    cstmt.setString(2, reportDTO.getMandal_id());
                    cstmt.setString(3, reportDTO.getVillage_id());
                    cstmt.setString(4, reportDTO.getHabcode());
                    cstmt.setString(5, fromdate);
                    cstmt.setString(6, todate);
                    cstmt.setString(7, reportDTO.getDisabilityId());
                } else if (village_id != null && village_id != "" && district_id != null && district_id != "" && !district_id.equalsIgnoreCase("0") && mandal_id != null && mandal_id != "" && mandal_id != "0") {
                    cstmt = con.prepareCall("{Call [Sp_Report1.2_Sadarem_Assessed_Individual_Detailsfor_disability_Village](?,?,?,?,?,?)}");
                    cstmt.setString(1, reportDTO.getDistrict_id());
                    cstmt.setString(2, reportDTO.getMandal_id());
                    cstmt.setString(3, reportDTO.getVillage_id());
                    cstmt.setString(4, fromdate);
                    cstmt.setString(5, todate);
                    cstmt.setString(6, reportDTO.getDisabilityId());
                } else if (mandal_id != null && mandal_id != "" && mandal_id != "0") {
                    //} else if (village_id == null && district_id != null && district_id != "" && !district_id.equalsIgnoreCase("0") && mandal_id != null && mandal_id != "" && mandal_id != "0") {
                    cstmt = con.prepareCall("{Call [Sp_Report1.2_Sadarem_Assessed_Individual_Detailsfor_disability_mandal](?,?,?,?,?)}");
                    cstmt.setString(1, reportDTO.getDistrict_id());
                    cstmt.setString(2, reportDTO.getMandal_id());
                    cstmt.setString(3, fromdate);
                    cstmt.setString(4, todate);
                    cstmt.setString(5, reportDTO.getDisabilityId());
                } else {
                    //System.out.println("44--" + "District_id---" + reportDTO.getDistrict_id() + "fromdate---" + fromdate + "todate---" + todate + "DisabilityId---" + reportDTO.getDisabilityId());
                    cstmt = con.prepareCall("{Call [Sp_Report1.2_Sadarem_Assessed_Individual_Detailsfor_disability_District](?,?,?,?)}");
                    cstmt.setString(1, reportDTO.getDistrict_id());
                    cstmt.setString(2, fromdate);
                    cstmt.setString(3, todate);
                    cstmt.setString(4, reportDTO.getDisabilityId());
                }
                rs = cstmt.executeQuery();
            } else {
                if (reportDTO.getReportType() != null && reportDTO.getReportType().equalsIgnoreCase("District")) {

                    cstmt = con.prepareCall("{Call [Sp_Report1.2_Sadarem_Assessed_Individualdetails_Districtwise](?,?,?,?)}");
                    cstmt.setString(1, reportDTO.getDistrict_id());
                    cstmt.setString(2, fromdate);
                    cstmt.setString(3, todate);
                    cstmt.setString(4, reportDTO.getReporttypeId());
                    rs = cstmt.executeQuery();
                } else if (reportDTO.getReportType() != null && reportDTO.getReportType().equalsIgnoreCase("Mandal")) {

                    cstmt = con.prepareCall("{Call [Sp_Report1.2_Sadarem_Assessed_Individualdetails_Mandalwise](?,?,?,?,?)}");
                    cstmt.setString(1, reportDTO.getDistrict_id());
                    cstmt.setString(2, reportDTO.getMandal_id());
                    cstmt.setString(3, fromdate);
                    cstmt.setString(4, todate);
                    cstmt.setString(5, reportDTO.getReporttypeId());
                    rs = cstmt.executeQuery();
                } else if (reportDTO.getReportType() != null && reportDTO.getReportType().equalsIgnoreCase("Camp")) {


                    cstmt = con.prepareCall("{Call [Sp_Report1.2_Sadarem_Assessed_Individualdetails_Campwise](?,?,?,?,?)}");
                    cstmt.setString(1, district_id);
                    cstmt.setString(2, reportDTO.getCampId());
                    cstmt.setString(3, fromdate);
                    cstmt.setString(4, todate);
                    cstmt.setString(5, reportDTO.getReporttypeId());
                    rs = cstmt.executeQuery();
                }

            }
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    ReportDTO reportDetailsDTO = new ReportDTO();
                    if (reportDTO.getReportType() != null && reportDTO.getReportType().equalsIgnoreCase("Disability")) {
                        reportDetailsDTO.setTypeOfSearch(reportDTO.getTypeOfSearch());
                        reportDetailsDTO.setReportType(reportDTO.getReportType());
                        reportDetailsDTO.setDistrict_id(reportDTO.getDistrict_id());
                        reportDetailsDTO.setDistrict_name(district_name);
                        reportDetailsDTO.setMandal_id(reportDTO.getMandal_id());
                        reportDetailsDTO.setVillage_id(village_id);

                    }
                    
                    if (rs.getString(1) != null && rs.getString(1) != "") {
                        map.put("pensionid", rs.getString(1));
                    } else {
                        map.put("pensionid", "-");
                    }
                    if (rs.getString(2) != null && rs.getString(2) != "") {
                        map.put("habcode", rs.getString(2));
                    } else {
                        map.put("habcode", "-");
                    }
                    map.put("sadaremcode", rs.getString(3));
                    if (rs.getString(4) != null && rs.getString(4) != "") {
                        map.put("pensionname", rs.getString(4));
                    } else {
                        map.put("pensionname", "-");
                    }
                    map.put("gender", rs.getString(5));
                    if (rs.getString(6) != null && rs.getString(6) != "") {
                        map.put("age", rs.getString(6));
                    } else {
                        map.put("age", "-");
                    }
                    map.put("education", rs.getString(7));
                    map.put("cast", rs.getString(8));
                    if (rs.getString(9) != null && rs.getString(9) != "") {
                        map.put("relationname", rs.getString(9));
                    } else {
                        map.put("relationname", "-");
                    }
                    if (rs.getString(10) != null && rs.getString(10) != "") {
                        map.put("rationcardnumber", rs.getString(10));
                    } else {
                        map.put("rationcardnumber", "-");
                    }
                    map.put("typeofdisability", rs.getString(11));
                    map.put("percentage", rs.getString(12));
                    map.put("assessmentstatus", rs.getString(13));
                    if (rs.getString(14) != null && rs.getString(14) != "") {
                        map.put("houseno", rs.getString(14));
                    } else {
                        map.put("houseno", "-");
                    }
                    map.put("habitationname", rs.getString(15));
                    map.put("villagename", rs.getString(16));
                    map.put("mandalname", rs.getString(17));
                    map.put("districtname", rs.getString(18));
                    map.put("date", rs.getString(20));
                    map.put("fromdate", reportDTO.getFromdate());
                    map.put("todate", reportDTO.getTodate());
                    map.put("heading", heading);
                    if (rs.getString(19) != null && rs.getString(19) != "") {
                        map.put("phone", rs.getString("phone_no"));
                    } else {
                        map.put("phone", "-");
                    }
                    map.put("district_ids", reportDTO.getDistrict_id());
                    map.put("mandal_ids", reportDTO.getMandal_id());
                    map.put("village_ids", reportDTO.getVillage_id());
                    map.put("habcodes", reportDTO.getHabcode());
                    map.put("heading", heading);
                    map.put("reporttypeId", reportDTO.getReporttypeId());
                    map.put("disabilityId", reportDTO.getDisabilityId());
                    map.put("financialYear", reportDTO.getFinancialYear());

                    //map.put("fromdate", reportDTO.getFromdate());
                    //map.put("todate", reportDTO.getTodate());
                    map.put("month", reportDTO.getMonth());
                    map.put("year", reportDTO.getYear());
                    map.put("campId", reportDTO.getCampId());

                    reportList.add(map);


                }

            }
        } catch (SQLException sqlEx) {
            // sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "individualDetails", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "individualDetails");
        } catch (Exception sqlEx) {
            // sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "individualDetails", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "individualDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return reportList;
    }

    public static BigDecimal round(float d, int decimalPlace) {

        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd;
    }

    public ArrayList getSadaremnothavingaadhracardnos(DataSource ds, String districtId, String mandalId, String villageid, String habitationid) throws Exception {

        ArrayList<Object> shgList = new ArrayList<Object>();
        Map shgData = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {

        	if(villageid.equals("All") || villageid.equals(""))
        	{
        		villageid="-1";
        	}
        	if(habitationid.equals("All") || habitationid.equals(""))
        	{
        		habitationid = "-1";
        	}
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call sp_individualdetails_aadhar_ss(?,?,?)}");


            cstmt.setString(1, districtId);

            cstmt.setString(2, mandalId);

            cstmt.setString(3, villageid);
            //cstmt.setString(4, habitationid);
            rs = cstmt.executeQuery();
            
            int sno = 1;
            if (rs != null) {
                while (rs.next()) {
                	
                    shgData = new HashMap<String, String>();
                    shgData.put("sno", sno);

                    if (rs.getString("Date_of_Issue") != null) {
                        shgData.put("Date_of_Issue", rs.getString("Date_of_Issue"));

                    } else {
                        shgData.put("Date_of_Issue", "-");
                    }
                    
                    if (rs.getString("SADAREM_ID") != null) {
                        shgData.put("SADAREMCODE", rs.getString("SADAREM_ID"));
                    } else {
                        shgData.put("SADAREMCODE", "-");
                    }
                  /*  if (rs.getString("HABCODE") != null) {
                        shgData.put("HABCODE", rs.getString("HABCODE"));

                    } else {
                        shgData.put("HABCODE", "-");
                    }
*/
                   

                   

                    if (rs.getString("PERSONNAME") != null) {
                        shgData.put("PERSONNAME", rs.getString("PERSONNAME"));
                    } else {
                        shgData.put("PERSONNAME", "-");
                    }
                    if (rs.getString("RELATIONNAME") != null) {
                        shgData.put("RELATIONNAME", rs.getString("RELATIONNAME"));
                    } else {
                        shgData.put("RELATIONNAME", rs.getString("RELATIONNAME"));
                    }

                    if (rs.getString("GENDER") != null) {
                        shgData.put("GENDER", rs.getString("GENDER"));
                    } else {
                        shgData.put("GENDER", rs.getString("GENDER"));
                    }


                    if (rs.getString("age") != null) {
                        shgData.put("age", rs.getString("age"));
                    } else {
                        shgData.put("age", rs.getString("age"));
                    }
                    if (rs.getString("phone_no") != null) {

                        shgData.put("phone_no", rs.getString("phone_no"));
                    } else {
                        shgData.put("phone_no", "-");
                    }
                    shgData.put("house_number", rs.getString("house_number"));
		                
                    /* if (rs.getString("EDUCATION") != null) {
		                        shgData.put("EDUCATION", rs.getString("EDUCATION"));
		                    } else {
		                        shgData.put("EDUCATION", rs.getString("EDUCATION"));
		                    }
		
		                    if (rs.getString("CASTE") != null) {
		                        shgData.put("CASTE", rs.getString("CASTE"));
		                    } else {
		                        shgData.put("CASTE", rs.getString("CASTE"));
		                    }
		
		
		                  
		
		                    if (rs.getString("RATIONCARD_NUMBER") != null) {
		
		                        shgData.put("RATIONCARD_NUMBER", rs.getString("RATIONCARD_NUMBER"));
		                    } else {
		                        shgData.put("RATIONCARD_NUMBER", "-");
		                    }
		
		
		                    shgData.put("ASSESSEMENTSTATUS", rs.getString("ASSESSEMENTSTATUS"));
		                    */
                   

                    shgData.put("districtId", districtId);
                    shgData.put("mandalId", mandalId);
                    shgData.put("villageid", villageid);
                    shgData.put("district_name", rs.getString("District_Name"));
                    shgData.put("mandal_name", rs.getString("Mandal_Name"));
                    if (villageid.equals("-1")) {
                        shgData.put("village_name", rs.getString("Village_Name"));


                    } else {
                        shgData.put("village_name", rs.getString("Village_Name"));

                    }
                    shgList.add(shgData);
                    sno++;
                    
                } 
            }
        } catch (SQLException sqlEx) {
           sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSadaremnothavingaadhracardnos", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getSadaremnothavingaadhracardnos");
        } catch (Exception sqlEx) {
           sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSadaremnothavingaadhracardnos", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getSadaremnothavingaadhracardnos");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
    
        return shgList;
    }
    public ArrayList getabstractSadaremnothavingaadhracardnos(DataSource ds, String districtId, String mandalId, String villageid, String habitationid) throws Exception {

        ArrayList<Object> shgList = new ArrayList<Object>();
        Map shgData = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {

            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call sp_individualdetails_aadhar_abstract(?,?,?)}");
            //System.out.println("in----"+ districtId +""+mandalId+""+villageid );

            cstmt.setString(1, districtId);

            cstmt.setString(2, mandalId);

            cstmt.setString(3, villageid);
          //  cstmt.setString(4, habitationid);
            rs = cstmt.executeQuery();
            //System.out.println("rs"+rs);
            int sno = 1;
            if (rs != null) {
                while (rs.next()) {
                    shgData = new HashMap<String, String>();
                        shgData.put("sno", sno);
                    	if (districtId.equals("-1") ) {
                        shgData.put("location_name", rs.getString("district_name") );
                    } else if(!districtId.equals("-1") && mandalId.equals("-1")){
                        shgData.put("location_name",rs.getString("mandal_name") );

                    }else if(!districtId.equals("-1") && !mandalId.equals("-1") && villageid.equals("-1")){
                    	shgData.put("location_name",rs.getString("village_name") );	
                    }else if(!districtId.equals("-1") && !mandalId.equals("-1") && !villageid.equals("-1")){
                    	shgData.put("location_name",rs.getString("habitation_name") );	
                    }  
                    
                    
                    shgData.put("total",rs.getString("TOTAL"));
                    shgList.add(shgData);
                    sno++;

                }
            }
            System.out.println("shg"+shgData);
        } catch (SQLException sqlEx) {
             sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSadaremnothavingaadhracardnos", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getSadaremnothavingaadhracardnos");
        } catch (Exception sqlEx) {
             sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSadaremnothavingaadhracardnos", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getSadaremnothavingaadhracardnos");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }

        return shgList;
    }

    public String getVillageAll(DataSource ds, String districtid, String mandalid, String villageid) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map m = null;
        String dname = null;
        try {
            con = DBConnection.getConnection();

            String query = "select village_name from tblVillage_Details with(nolock)  where district_id=? and  mandal_id=? and village_id=?  order by village_name";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtid);
            pstmt.setString(2, mandalid);
            pstmt.setString(3, villageid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                dname = rs.getString(1);
            }
        } catch (SQLException sqlEx) {
            // sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillageAll", "ReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getVillageAll");
        } catch (Exception sqlEx) {
            // sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillageAll", "ReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getVillageAll");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }
        return dname;
    }

	public ArrayList getOnlyPartADetails(String personcode)
	{
		ArrayList basicDetails = new ArrayList();
	
		try{
			
			ArrayList paramList = new ArrayList();
			ArrayList tempList = new ArrayList();
			
			lStrQuery =    "select a.person_code,a.surname + '' +a.first_name name,a.relation_name,case when gender=1 then 'Male' else 'Female' end gender\n"+
					 " ,b.district_name,c.mandal_name,a.reasonforpersonstatus\n"+
					 "from tblperson_personal_details a with(nolock)  join tbldistrict_details b with(nolock) \n"+
					 "on a.districtid = b.district_id \n"+
					 "join tblmandal_details c with(nolock)  on a.districtid = c.district_id and a.mandalid = c.mandal_id and a.person_code=?";
			
			tempList = new ArrayList();
			tempList.add("S");
			tempList.add(personcode);
			paramList.add(tempList);
			
			        basicDetails = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			      //  System.out.println("vv"+basicDetails);
		}catch(Exception e)
		{
			System.out.println(""+e);
		}
		
		return basicDetails;
	}
	
	public ArrayList getCampDistIds(String personcode)
	{
		ArrayList basicDetails = new ArrayList();
		
		try{
			
			ArrayList paramList = new ArrayList();
			ArrayList tempList = new ArrayList();
			
			lStrQuery = "SELECT a.partB_campid,a.district_id\n"+
					  "from tblperson_personal_details a with(nolock)  where a.person_code=?";
			
			tempList = new ArrayList();
			tempList.add("S");
			tempList.add(personcode);
			paramList.add(tempList);
			
			        basicDetails = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			      //  System.out.println("vv"+basicDetails);
		}catch(Exception e)
		{
			System.out.println(""+e);
		}
		
		return basicDetails;
	}
	 
}


