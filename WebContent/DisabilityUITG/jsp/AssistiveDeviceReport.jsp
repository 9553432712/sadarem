<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<%@taglib  uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib  uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<% String fromdate=(String)request.getAttribute("fromDate");
   String todate=(String)request.getAttribute("toDate");
   
 %>

<html>
<html:errors/>
<BODY>
    
    <div style="border:1px solid #000000; background-color: #FFFFFF;width:100%; height:100%; overflow-x:scroll; overflow-y:scroll; scrollbar-face-color:#D8E9F3; scrollbar-arrow-color:white; scrollbar-highlight-color:white"> 
    <br><br>
            <table  border="1" align="center" cellspacing="1" cellpadding="5" width="90%" style="border-collapse:collapse" bordercolor="#808000">
        
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=prosthesis&ShoulderProsthesis=ShoulderProsthesis&AboveElbowProsthesis=AboveElbowProsthesis&ElbowDisarticulationProsthesis=ElbowDisarticulationProsthesis&BelowElbowProsthesis=BelowElbowProsthesis&WristDisarticulationProsthesis=WristDisarticulationProsthesis&HandProsthesis=HandProsthesis&CosmeticFingerProsthesis=CosmeticFingerProsthesis&HipDisarticulationProsthesis=HipDisarticulationProsthesis&AboveKneeProsthesis=AboveKneeProsthesis&KneeDisarticulationProsthesis=KneeDisarticulationProsthesis&BelowKneeProsthesis=BelowKneeProsthesis&SymesProsthesis=SymesProsthesis&PartialFootProsthesis=PartialFootProsthesis&columnnvalue=Prosthesis&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> Prosthesis</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=orthosis&aeroplanesplint=AeroplaneSplint&figureeightspint=FigureEightSplint&forearmsplint=ForeArmSplint&handsplint=HandSplint&hkafo=HKAFO&kafo=KAFO&afo=AFO&kneeorthosis=KneeOrthosis&dbsplint=DBSplint&modifiedshoe=ModifiedShoe&serialcasting=SerialCasting&cervicalcollar=CervicalCollar&lbbrace=LBBrace&tlsobrace=TLSOBrace&columnnvalue=Orthosis&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>">Orthosis</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=HearingAidType&columnnvalue=HearingAidType&tablename=tblHearing_Impairment_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> Hearing Aids</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=LowVisionAids&columnnvalue=Low Vision Aids&tablename=tblVisualImpairment_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>">Vision Aids</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=WheelChair&columnnvalue=Small Wheel Chair&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>">Small Wheel Chair</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=WheelChair&columnnvalue=Large Wheel Chair&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>">Large Wheel Chair</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=tricycle&columnnvalue=Large Tricycle&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> Large Tricycle</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=tricycle&columnnvalue=Small Tricycle&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> Small Tricycle</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=walkingstick&columnnvalue=Large Walking Stick&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> Large Walking Sticks</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=walkingstick&columnnvalue=Small Walking Stick&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> Small Walking Sticks</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=CrutchesType&columnnvalue=Large Crutches&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> Large Crutches</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=CrutchesType&columnnvalue=Medium Crutches&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> Medium Crutches</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=CrutchesType&columnnvalue=Small Crutches&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> Small Crutches</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=WalkingFrame&columnnvalue=Small WalkingFrame&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> Walking Frame</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=LowVisionAids&columnnvalue=Low Vision Aids&tablename=tblVisualImpairment_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> Low Vision aids</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold"href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=SpeechSynthesizer&columnnvalue=Speech Synthesizer&tablename=tblVisualImpairment_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> Speech Sythesizer</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=TalkingWatch&columnnvalue=Talking Watch/Calculator&tablename=tblVisualImpairment_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> Talking Watch/Calculator</a>
            </td></tr>
            <tr><td><table  border="1" align="center" cellspacing="1" cellpadding="5" width="90%" style="border-collapse:collapse" bordercolor="#808000">
                        <tr><td align="center" style="color: blue;font-weight:bold">Any Activities for Daily Living (ADL) Equipments</td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=Feeding&columnnvalue=Feeding&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> Feeding</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=Toileting&columnnvalue=Toileting/Bathing&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> Toileting/Bathing</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=Brushing&columnnvalue=Brushing&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> Brushing</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=Combing&columnnvalue=Combing&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> Combing</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=Dressing&columnnvalue=Dressing&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> Dressing</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=Writing&columnnvalue=Writing&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> Writing</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=Driving&columnnvalue=Driving/Cycling&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> Driving</a>
            </td></tr>
            <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=BedTransfer&columnnvalue=Bed Transfer&tablename=tblPerson_LocomotorNeeds_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> BedTransfer</a>
            </td></tr>
            </table></td></tr>
           <%-- <tr ><td align="center"><a style="color:black;font-weight:bold" href="getDistrictidForAssistive.do?getDistrictReport=getDistrictReport&columnname=AlarmingDevices&columnnvalue=Sound Producing&tablename=tblHearing_Impairment_Details&FromDate=<%= fromdate %>&ToDate=<%= todate%>"> Alarming Devices</a>
            </td></tr> --%>
       
        
    </table><br><br></div>
   

</BODY>
</HTML>