<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<%@taglib  uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib  uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<% String fromdate=(String)request.getAttribute("fromDate");
   String todate=(String)request.getAttribute("toDate");   %>
   
<html>
<html:errors/>
<BODY>
    
    <br><br>
    <table  border="1" align="center" cellspacing="1" cellpadding="5"  width="90%" style="border-collapse:collapse" bordercolor="#808000" bgcolor="white">
       
              <tr ><td align="center"><a href="getDistrictidForSurgery.do?getDistrictReport=getDistrictReport&columnname=Physiotherapy&firstcolumn=Physiotherapyforthreeyears&secondcolumn=Physiotherapy&columnnvalue=physiotheraphy&secondcolumnvalue=physiotherapy&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>" style="color:black;font-weight:bold">Physiotherapy</a>
              </td></tr>
 
            <tr><td align="center"><a href="getDistrictidForSurgery.do?getDistrictReport=getDistrictReport&columnname=OccupationalTherapy&columnnvalue=OccupationalTherapy&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>" style="color:black;font-weight:bold">Occupational Therapy</a>
 </td></tr>
 
            <tr ><td align="center"><a href="getDistrictidForSurgery.do?getDistrictReport=getDistrictReport&columnname=SpeechTherapy&firstcolumn=SpeechTherapyforthreeyears&secondcolumn=SpeechTherapy&columnnvalue=EarlyIntervention Speech Therapy&secondcolumnvalue=Speech Therapy&tablename=tblHearing_Impairment_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>" style="color:black;font-weight:bold">Speech Therapy</a>
 </td></tr>
 
            <tr ><td align="center"><a href="getDistrictidForSurgery.do?getDistrictReport=getDistrictReport&columnname=CouncellingGuidance&firstcolumn=Individual&secondcolumn=Family&columnnvalue=Individual&secondcolumnvalue=Family&tablename=tblAllDisabilityPerson_Common_Needs&FromDate=<%= fromdate %>&ToDate=<%= todate%>" style="color:black;font-weight:bold">Councelling Guidance</a>
 </td></tr>

           <tr ><td align="center"><a href="getDistrictidForSurgery.do?getDistrictReport=getDistrictReport&columnname=PsycotherapyBehaviour&columnnvalue=Psycotherapy/Behaviour Modification&tablename=tblMental_Retardation_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>" style="color:black;font-weight:bold">Psychotherapy And Behavior Modification</a>
 </td></tr>

           <tr ><td align="center"><a href="getDistrictidForSurgery.do?getDistrictReport=getDistrictReport&columnname=NursingHomes&columnnvalue=Psychiatric Hospitals/ Nursing Homes&tablename=tblMental_Disability_Illness_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>" style="color:black;font-weight:bold">Psychiatric Admission</a>
 </td></tr>

            <tr ><td align="center"><a href="getDistrictidForSurgery.do?getDistrictReport=getDistrictReport&columnname=Surgerey&columnnvalue=Surgerey&tablename=tblHearing_Impairment_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>" style="color:black;font-weight:bold">Surgery</a>
 </td></tr>
 
           <tr ><td align="center"><a href="getDistrictidForSurgery.do?getDistrictReport=getDistrictReport&columnname=CochlearImplantation&columnnvalue=Cochlear Implantation&tablename=tblHearing_Impairment_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%> " style="color:black;font-weight:bold">Cochlear Implantation</a>
 </td></tr>
        
        
      </table>
   

</BODY>
</HTML>