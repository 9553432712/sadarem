/*
 * CommonReportDetails.java
 *
 * Created on August 22, 2008, 3:05 PM
 */

package org.bf.disability.util;
import org.bf.disability.dto.CommonReportDTO;
import org.bf.disability.form.CommonReportForm;

import java.util.*;
public class  CommonReportDetails{
    CommonReportDTO surgeryreportdto=new CommonReportDTO();
    ArrayList reportlist=new ArrayList();
    public CommonReportForm getCommonReportDetails
            (String surgerytype,CommonReportForm surgeryform) {
        
        String districtid=surgeryform.getDistrictid();
        String Column_Value=surgeryform.getFieldvalue();
        String Second_Column_Value=surgeryform.getSecondfieldvalue();
        if(surgerytype.equals("Physiotherapy")&&
                Column_Value.equals("physiotheraphy")&&
                Second_Column_Value.equals("physiotherapy")) {
            surgeryform.setTablename("tblPerson_LocomotorNeeds_Details");
            surgeryform.setFirstcolumn("Physiotherapyforthreeyears");
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setSecondcolumn("Physiotherapy");
            surgeryform.setSecondfieldvalue(Second_Column_Value);
        } else if(surgerytype.equals("OccupationalTherapy")&&
                Column_Value.equals("OccupationalTherapy")) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblPerson_LocomotorNeeds_Details");
        } else if(surgerytype.equals("SpeechTherapy")&&
                Column_Value.equals("EarlyIntervention Speech Therapy")&&
                Second_Column_Value.equals("Speech Therapy")) {
            surgeryform.setTablename("tblHearing_Impairment_Details");
            surgeryform.setFirstcolumn("SpeechTherapyforthreeyears");
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setSecondcolumn("SpeechTherapy");
            surgeryform.setSecondfieldvalue(Second_Column_Value);
        } else if(surgerytype.equals("CouncellingGuidance")&&
                Column_Value.equals("Individual")&&
                Second_Column_Value.equals("Family")) {
            surgeryform.setTablename("tblAllDisabilityPerson_Common_Needs");
            surgeryform.setFirstcolumn("Individual");
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setSecondcolumn("Family");
            surgeryform.setSecondfieldvalue(Second_Column_Value);
        } else if(surgerytype.equals("PsycotherapyBehaviour")&&
                Column_Value.equals("Psycotherapy/Behaviour Modification")) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblMental_Retardation_Details");
        } else if(surgerytype.equals("NursingHomes")&&
                Column_Value.equals("Psychiatric Hospitals/ Nursing Homes")) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblMental_Disability_Illness_Details");
        } else if(surgerytype.equals("Surgerey")) {
             surgeryform.setFieldvalue("");
              surgeryform.setTablename("tblHearing_Impairment_Details");              
        } else if(surgerytype.equals("CochlearImplantation")&&
                Column_Value.equals("Cochlear Implantation")) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblHearing_Impairment_Details");
        } else if(surgerytype.equals("EarlyEducation") &&
                Column_Value.equals("Early Education Services") ) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblAllDisabilityPerson_Common_Needs");
        } else if(surgerytype.equals("EducationServices") &&
                Column_Value.equals("Home Based Education") ) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblAllDisabilityPerson_Common_Needs");
        } else if(surgerytype.equals("EducationServices")&&
                Column_Value.equals("Special School")) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblAllDisabilityPerson_Common_Needs");
        } else if(surgerytype.equals("EducationServices")&&
                Column_Value.equals("Inclusive Education")) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblAllDisabilityPerson_Common_Needs");
        } else if(surgerytype.equals("prosthesis")) {
            surgeryform.setFieldvalue("SpecialSchool");
            surgeryform.setTablename("tblAllDisabilityPerson_Common_Needs");
        } else if(surgerytype.equals("orthosis")) {
            surgeryform.setFieldvalue("SpecialSchool");
            surgeryform.setTablename("tblAllDisabilityPerson_Common_Needs");
        }  else if(surgerytype.equals("HearingAidType")) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblHearing_Impairment_Details");
        } else if(surgerytype.equals("LowVisionAids")&&
                Column_Value.equals("Low Vision Aids")) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblVisualImpairment_Details");
        } else if(surgerytype.equals("WheelChair")&&
                Column_Value.equals("Small Wheel Chair")) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblPerson_LocomotorNeeds_Details");
        } else if(surgerytype.equals("WheelChair")&&
                Column_Value.equals("Large Wheel Chair")) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblPerson_LocomotorNeeds_Details");
        } else if(surgerytype.equals("tricycle")&&
                Column_Value.equals("Large Tricycle")) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblPerson_LocomotorNeeds_Details");
        } else if(surgerytype.equals("tricycle")&&
                Column_Value.equals("Small Tricycle")) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblPerson_LocomotorNeeds_Details");
        } else if(surgerytype.equals("walkingstick")&&
                Column_Value.equals("Large Walking Stick")) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblPerson_LocomotorNeeds_Details");
        } else if(surgerytype.equals("walkingstick")&&
                Column_Value.equals("Small Walking Stick")) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblPerson_LocomotorNeeds_Details");
        } else if(surgerytype.equals("CrutchesType")&&
                Column_Value.equals("Medium Crutches")) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblPerson_LocomotorNeeds_Details");
        } else if(surgerytype.equals("CrutchesType")&&
                Column_Value.equals("Small Crutches")) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblPerson_LocomotorNeeds_Details");
        } else if(surgerytype.equals("CrutchesType")&&
                Column_Value.equals("Large Crutches")) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblPerson_LocomotorNeeds_Details");
        } else if(surgerytype.equals("WalkingFrame")) {
            //    surgeryform.setFieldvalue(Column_Value);
            //   surgeryform.setTablename("tblPerson_LocomotorNeeds_Details");
        } else if(surgerytype.equals("LowVisionAids")&&
                Column_Value.equals("Low Vision Aids")) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblVisualImpairment_Details");
        } else if(surgerytype.equals("SpeechSynthesizer")&&
                Column_Value.equals("Speech Synthesizer")) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblVisualImpairment_Details");
        } else if(surgerytype.equals("TalkingWatch")&&
                Column_Value.equals("Talking Watch/Calculator")) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblVisualImpairment_Details");
        } else if(surgerytype.equals("walkingstick")&&
                Column_Value.equals("Small WalkingFrame")) {
            surgeryform.setFieldvalue("Small Walking Stick");
            surgeryform.setTablename("tblPerson_LocomotorNeeds_Details");
        } else if(surgerytype.equals("Feeding")) {
               surgeryform.setFieldvalue("Feeding");
              surgeryform.setTablename("tblPerson_LocomotorNeeds_Details");
        } else if(surgerytype.equals("Toileting")) {
               surgeryform.setFieldvalue("Toileting/Bathing");
              surgeryform.setTablename("tblPerson_LocomotorNeeds_Details");
        } else if(surgerytype.equals("Brushing")) {
               surgeryform.setFieldvalue("Brushing");
              surgeryform.setTablename("tblPerson_LocomotorNeeds_Details");
        } else if(surgerytype.equals("Combing")) {
               surgeryform.setFieldvalue("Combing");
              surgeryform.setTablename("tblPerson_LocomotorNeeds_Details");
        } else if(surgerytype.equals("Dressing")) {
               surgeryform.setFieldvalue("Dressing");
              surgeryform.setTablename("tblPerson_LocomotorNeeds_Details");
        } else if(surgerytype.equals("Writing")) {
               surgeryform.setFieldvalue("Writing");
              surgeryform.setTablename("tblPerson_LocomotorNeeds_Details");
        } else if(surgerytype.equals("Driving")) {
               surgeryform.setFieldvalue("Driving/Cycling");
              surgeryform.setTablename("tblPerson_LocomotorNeeds_Details");
        } else if(surgerytype.equals("BedTransfer")) {
               surgeryform.setFieldvalue("Bed Transfer");
              surgeryform.setTablename("tblPerson_LocomotorNeeds_Details");
        } else if(surgerytype.equals("AlarmingDevices")&&
                Column_Value.equals("Sound Producing")) {
            surgeryform.setFieldvalue(Column_Value);
            surgeryform.setTablename("tblHearing_Impairment_Details");
        }
        
        
        return surgeryform;
    }
    
    
    
}
 