<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
    <HEAD>
        
        <script language="JavaScript">
            function goBack()
            {
            document.forms[0].action="MentalRetardationForwdActionPrint.do";
            document.forms[0].submit();
            }
        </script>
        <script language="JavaScript">
            function age_3_selectall(){
            if(document.getElementById("age3").checked)
            {
            var count=evaluateForLoopForSelectAll(37,39);
            if(count>0)
            {
            alert("Not Allowed");
            document.getElementById("age3").checked=false;
            }else{
            document.MRBinetKamatDetailedTestForm.ageLevel3_Item1.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel3_Item2.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel3_Item3.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel3_Item4.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel3_Item5.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel3_Item6.checked=true;

            }
            }
            else
            {
            document.MRBinetKamatDetailedTestForm.ageLevel3_Item1.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel3_Item2.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel3_Item3.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel3_Item4.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel3_Item5.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel3_Item6.checked=false;


            }
            }

            function age_4_selectall(){
            if(document.getElementById("age4").checked)
            {
            var count=evaluateForLoopForSelectAll(47,49);
            if(count>0)
            {
            alert("Not Allowed");
            document.getElementById("age4").checked=false;
            }else{
            document.MRBinetKamatDetailedTestForm.ageLevel4_Item1.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel4_Item2.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel4_Item3.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel4_Item4.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel4_Item5.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel4_Item6.checked=true;
            document.getElementById("age4").checked=true;
            }
            }
            else
            {
            document.MRBinetKamatDetailedTestForm.ageLevel4_Item1.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel4_Item2.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel4_Item3.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel4_Item4.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel4_Item5.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel4_Item6.checked=false;
            document.getElementById("age4").checked=false;
            }
            }

            function age_5_selectall(){
            if(document.getElementById("age5").checked)
            {
            var count=evaluateForLoopForSelectAll(57,59);
            if(count>0)
            {
            alert("Not Allowed");
            document.getElementById("age5").checked=false;
            }else{
            document.MRBinetKamatDetailedTestForm.ageLevel5_Item1.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel5_Item2.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel5_Item3.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel5_Item4.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel5_Item5.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel5_Item6.checked=true;
            }
            }
            else
            {
            document.MRBinetKamatDetailedTestForm.ageLevel5_Item1.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel5_Item2.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel5_Item3.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel5_Item4.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel5_Item5.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel5_Item6.checked=false;

            }
            }

            function age_6_selectall(){
            if(document.getElementById("age6").checked)
            {
            var count=evaluateForLoopForSelectAll(67,69);
            if(count>0)
            {
            alert("Not Allowed");
            document.getElementById("age6").checked=false;
            }else{
            document.MRBinetKamatDetailedTestForm.ageLevel6_Item1.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel6_Item2.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel6_Item3.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel6_Item4.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel6_Item5.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel6_Item6.checked=true;
            }
            }
            else
            {
            document.MRBinetKamatDetailedTestForm.ageLevel6_Item1.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel6_Item2.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel6_Item3.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel6_Item4.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel6_Item5.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel6_Item6.checked=false;

            }
            }

            function age_7_selectall(){
            if(document.getElementById("age7").checked)
            {
            var count=evaluateForLoopForSelectAll(77,79);
            if(count>0)
            {
            alert("Not Allowed");
            document.getElementById("age7").checked=false;
            }else{
            document.MRBinetKamatDetailedTestForm.ageLevel7_Item1.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel7_Item2.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel7_Item3.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel7_Item4.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel7_Item5.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel7_Item6.checked=true;
            }
            }
            else
            {
            document.MRBinetKamatDetailedTestForm.ageLevel7_Item1.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel7_Item2.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel7_Item3.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel7_Item4.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel7_Item5.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel7_Item6.checked=false;

            }
            }
            function age_8_selectall(){
            if(document.getElementById("age8").checked)
            {
            var count=evaluateForLoopForSelectAll(87,89);
            if(count>0)
            {
            alert("Not Allowed");
            document.getElementById("age8").checked=false;
            }else{
            document.MRBinetKamatDetailedTestForm.ageLevel8_Item1.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel8_Item2.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel8_Item3.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel8_Item4.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel8_Item5.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel8_Item6.checked=true;
            }
            }
            else
            {
            document.MRBinetKamatDetailedTestForm.ageLevel8_Item1.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel8_Item2.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel8_Item3.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel8_Item4.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel8_Item5.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel8_Item6.checked=false;

            }
            }
            function age_9_selectall(){
            if(document.getElementById("age9").checked)
            {
            var count=evaluateForLoopForSelectAll(97,99);
            if(count>0)
            {
            alert("Not Allowed");
            document.getElementById("age9").checked=false;
            }else{
            document.MRBinetKamatDetailedTestForm.ageLevel9_Item1.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel9_Item2.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel9_Item3.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel9_Item4.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel9_Item5.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel9_Item6.checked=true;
            }
            }
            else
            {
            document.MRBinetKamatDetailedTestForm.ageLevel9_Item1.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel9_Item2.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel9_Item3.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel9_Item4.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel9_Item5.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel9_Item6.checked=false;

            }
            }
            function age_10_selectall(){
            if(document.getElementById("age10").checked)
            {
            var count=evaluateForLoopForSelectAll(107,109);
            if(count>0)
            {
            alert("Not Allowed");
            document.getElementById("age10").checked=false;
            }else{
            document.MRBinetKamatDetailedTestForm.ageLevel10_Item1.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel10_Item2.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel10_Item3.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel10_Item4.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel10_Item5.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel10_Item6.checked=true;
            }
            }
            else
            {
            document.MRBinetKamatDetailedTestForm.ageLevel10_Item1.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel10_Item2.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel10_Item3.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel10_Item4.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel10_Item5.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel10_Item6.checked=false;

            }
            }

            function age_12_selectall(){
            if(document.getElementById("age12").checked)
            {
            var count=evaluateForLoopForSelectAll(127,129);
            if(count>0)
            {
            alert("Not Allowed");
            document.getElementById("age12").checked=false;
            }else{
            document.MRBinetKamatDetailedTestForm.ageLevel12_Item1.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel12_Item2.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel12_Item3.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel12_Item4.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel12_Item5.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel12_Item6.checked=true;
            }
            }
            else
            {
            document.MRBinetKamatDetailedTestForm.ageLevel12_Item1.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel12_Item2.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel12_Item3.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel12_Item4.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel12_Item5.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel12_Item6.checked=false;

            }
            }
            function age_14_selectall(){
            if(document.getElementById("age14").checked)
            {
            var count=evaluateForLoopForSelectAll(147,149);
            if(count>0)
            {
            alert("Not Allowed");
            document.getElementById("age14").checked=false;
            }else{
            document.MRBinetKamatDetailedTestForm.ageLevel14_Item1.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel14_Item2.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel14_Item3.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel14_Item4.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel14_Item5.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel14_Item6.checked=true;
            }
            }
            else
            {
            document.MRBinetKamatDetailedTestForm.ageLevel14_Item1.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel14_Item2.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel14_Item3.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel14_Item4.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel14_Item5.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel14_Item6.checked=false;

            }
            }
            function age_16_selectall(){
            if(document.getElementById("age16").checked)
            {
            var count=evaluateForLoopForSelectAll(167,169);
            if(count>0)
            {
            alert("Not Allowed");
            document.getElementById("age16").checked=false;
            }else{
            document.MRBinetKamatDetailedTestForm.ageLevel16_Item1.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel16_Item2.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel16_Item3.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel16_Item4.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel16_Item5.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel16_Item6.checked=true;
            }
            }
            else
            {
            document.MRBinetKamatDetailedTestForm.ageLevel16_Item1.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel16_Item2.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel16_Item3.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel16_Item4.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel16_Item5.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel16_Item6.checked=false;

            }
            }
            function age_19_selectall(){
            if(document.getElementById("age19").checked)
            {
            var count=evaluateForLoopForSelectAll(197,199);
            if(count>0)
            {
            alert("Not Allowed");
            document.getElementById("age19").checked=false;
            }else{
            document.MRBinetKamatDetailedTestForm.ageLevel19_Item1.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel19_Item2.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel19_Item3.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel19_Item4.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel19_Item5.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel19_Item6.checked=true;
            }
            }
            else
            {
            document.MRBinetKamatDetailedTestForm.ageLevel19_Item1.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel19_Item2.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel19_Item3.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel19_Item4.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel19_Item5.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel19_Item6.checked=false;

            }
            }

            function age_22_selectall(){
            if(document.getElementById("age22").checked)
            {
            var count=evaluateForLoopForSelectAll(227,229);
            if(count>0)
            {
            alert("Not Allowed");
            document.getElementById("age22").checked=false;
            }else{
            document.MRBinetKamatDetailedTestForm.ageLevel22_Item1.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel22_Item2.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel22_Item3.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel22_Item4.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel22_Item5.checked=true;
            document.MRBinetKamatDetailedTestForm.ageLevel22_Item6.checked=true;
            }
            }
            else
            {
            document.MRBinetKamatDetailedTestForm.ageLevel22_Item1.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel22_Item2.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel22_Item3.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel22_Item4.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel22_Item5.checked=false;
            document.MRBinetKamatDetailedTestForm.ageLevel22_Item6.checked=false;

            }
            }


            function evaluate6(styleID){
            if(styleID>=31 && styleID<=39)
            evaluateForLoop(31,39,styleID);
            if(styleID>=31 && styleID<=39)
            evaluateForLoop(31,39,styleID);

            if(styleID>=41 && styleID<=49)
            evaluateForLoop(41,49,styleID);

            if(styleID>=51 && styleID<=59)
            evaluateForLoop(51,59,styleID);

            if(styleID>=61 && styleID<=69)
            evaluateForLoop(61,69,styleID);

            if(styleID>=71 && styleID<=79)
            evaluateForLoop(71,79,styleID);

            if(styleID>=81 && styleID<=89)
            evaluateForLoop(81,89,styleID);

            if(styleID>=91 && styleID<=99)
            evaluateForLoop(91,99,styleID);

            if(styleID>=101 && styleID<=109)
            evaluateForLoop(101,109,styleID);

            if(styleID>=121 && styleID<=129)
            evaluateForLoop(121,129,styleID);

            if(styleID>=141 && styleID<=149)
            evaluateForLoop(141,149,styleID);

            if(styleID>=161 && styleID<=169)
            evaluateForLoop(161,169,styleID);

            if(styleID>=191 && styleID<=199)
            evaluateForLoop(191,199,styleID);

            if(styleID>=221 && styleID<=229)
            evaluateForLoop(221,229,styleID);

            }

            function selectSelectAllCheckBoxes()
            {
            var count;
            count=evaluateForLoopForSelectAll(31,36);
            if(count==6)
            document.getElementById("age3").checked=true;
            else
            document.getElementById("age3").checked=false;

            count=evaluateForLoopForSelectAll(41,46);
            if(count==6)
            document.getElementById("age4").checked=true;
            else
            document.getElementById("age4").checked=false;

            count=evaluateForLoopForSelectAll(51,56);
            if(count==6)
            document.getElementById("age5").checked=true;
            else
            document.getElementById("age5").checked=false;

            count=evaluateForLoopForSelectAll(61,66);
            if(count==6)
            document.getElementById("age6").checked=true;
            else
            document.getElementById("age6").checked=false;

            count=evaluateForLoopForSelectAll(71,76);
            if(count==6)
            document.getElementById("age7").checked=true;
            else
            document.getElementById("age7").checked=false;

            count=evaluateForLoopForSelectAll(81,86);
            if(count==6)
            document.getElementById("age8").checked=true;
            else
            document.getElementById("age8").checked=false;

            count=evaluateForLoopForSelectAll(91,96);
            if(count==6)
            document.getElementById("age9").checked=true;
            else
            document.getElementById("age9").checked=false;

            count=evaluateForLoopForSelectAll(101,106);
            if(count==6)
            document.getElementById("age10").checked=true;
            else
            document.getElementById("age10").checked=false;

            count=evaluateForLoopForSelectAll(121,126);
            if(count==6)
            document.getElementById("age12").checked=true;
            else
            document.getElementById("age12").checked=false;

            count=evaluateForLoopForSelectAll(141,146);
            if(count==6)
            document.getElementById("age14").checked=true;
            else
            document.getElementById("age14").checked=false;

            count=evaluateForLoopForSelectAll(161,166);
            if(count==6)
            document.getElementById("age16").checked=true;
            else
            document.getElementById("age16").checked=false;

            count=evaluateForLoopForSelectAll(191,196);
            if(count==6)
            document.getElementById("age19").checked=true;
            else
            document.getElementById("age19").checked=false;

            count=evaluateForLoopForSelectAll(221,226);
            if(count==6)
            document.getElementById("age22").checked=true;
            else
            document.getElementById("age22").checked=false;


            }


            function evaluateForLoop(i,j,k){
            var count=0;
            for(var l=i;l<=j;l++)
            if(document.getElementById(l).checked)
            count++;
            if(count>6){
            alert("7th click Not Allowed");
            document.getElementById(k).checked=false;
            }
            }

            function  evaluateForLoopForSelectAll(i,j){
            var count=0;
            for(var l=i;l<=j;l++)
            if(document.getElementById(l).checked)
            count++;
            return count;

            }

        </script>

        <script language="JavaScript">
            function evaluateForLoopReset(i,j){

            for(var l=i;l<=j;l++)
            if(document.getElementById(l).checked)
            document.getElementById(l).checked=false;

            }

            function reSet()
            {

            evaluateForLoopReset(31,39);
            evaluateForLoopReset(41,49);
            evaluateForLoopReset(51,59);
            evaluateForLoopReset(61,69);
            evaluateForLoopReset(71,79);
            evaluateForLoopReset(81,89);
            evaluateForLoopReset(91,99);
            evaluateForLoopReset(101,109);
            evaluateForLoopReset(121,129);
            evaluateForLoopReset(141,149);
            evaluateForLoopReset(161,169);
            evaluateForLoopReset(191,199);
            evaluateForLoopReset(221,229);
            document.getElementById("age3").checked=false;
            document.getElementById("age4").checked=false;
            document.getElementById("age5").checked=false;
            document.getElementById("age6").checked=false;
            document.getElementById("age7").checked=false;
            document.getElementById("age8").checked=false;
            document.getElementById("age9").checked=false;
            document.getElementById("age10").checked=false;
            document.getElementById("age12").checked=false;
            document.getElementById("age14").checked=false;
            document.getElementById("age16").checked=false;
            document.getElementById("age19").checked=false;
            document.getElementById("age22").checked=false;
            }

             function avoidDuplicateForm(thisform){
        if (thisform.getAttribute('submitted'))
            return false;
        thisform.setAttribute('submitted','true');
    }
        </script>
        <script>
	function disableForm(theform) {
		if (document.all || document.getElementById) {
			for (i = 0; i < theform.length; i++) {
			var formElement = theform.elements[i];
				if (true) {
					formElement.disabled = true;
				}
			}
		}
	}
</script>

    </HEAD>

    <BODY ONLOAD="selectSelectAllCheckBoxes(),disableForm(MRBinetKamatDetailedTestForm);">
        <html:form action="/getMRBinetKamatDetailedTestDetailsPrint" styleId="MRBinetKamatDetailedTestForm">
        <table align="center" cellspacing="0" cellpadding="0" width="45%">
            <tr>
                <td align="center"><font color="blue"><b>SADAREM ID : <%=session.getAttribute("personcode")%></b></font></td>
            </tr>
       </table><br/>
            <center>BKT Detailed Test Details</center>
            <table  align="center" cellspacing="1" cellpadding="8" border="1" class="innerTable1" width="85%">
                <tr >
                <td  rowspan="3" width="10%" align="center" class="labelBlue">Item No</td>
                <td width="90%"colspan="28" align="center" class="labelBlue">AGE LEVELS</td>
                <tr>
                <tr >
                    <td align="center" colspan="2"><font size="3"><b>3</b></font>
                    </td>
                    <td align="center" colspan="2"><font size="3"><b>4</b></font>
                    </td>
                    <td align="center" colspan="2"><font size="3"><b>5</b></font>
                    </td>
                    <td align="center" colspan="2"><font size="3"><b>6</b></font>
                    </td>
                    <td align="center" colspan="2"><font size="3"><b>7</b></font>
                    </td>
                    <td align="center" colspan="2"><font size="3"><b>8</b></font>
                    </td>
                    <td align="center" colspan="2"><font size="3"><b>9</b></font>
                    </td>
                    <td align="center" colspan="2"><font size="3"><b>10</b></font>
                    </td>
                    <td align="center" colspan="2"><font size="3"><b>12</b></font>
                    </td>
                    <td align="center" colspan="2"><font size="3"><b>14</b></font>
                    </td>
                    <td align="center" colspan="2"><font size="3"><b>16</b></font>
                    </td>
                    <td align="center" colspan="2"><font size="3"><b>19</b></font>
                    </td >
                    <td align="center" colspan="2"><font size="3"><b>22</b></font>
                    </td>
                </tr>

                <tr>
                    <td>&nbsp;</td>
                    <td>
                        Old
                    </td>
                    <td>
                      New
                    </td>
                     <td>
                        Old
                    </td>
                    <td>
                      New
                    </td>
                     <td>
                        Old
                    </td>
                    <td>
                      New
                    </td>
                     <td>
                        Old
                    </td>
                    <td>
                      New
                    </td>
                     <td>
                        Old
                    </td>
                    <td>
                      New
                    </td>
                     <td>
                        Old
                    </td>
                    <td>
                      New
                    </td>
                     <td>
                        Old
                    </td>
                    <td>
                      New
                    </td>
                     <td>
                        Old
                    </td>
                    <td>
                      New
                    </td>
                     <td>
                        Old
                    </td>
                    <td>
                      New
                    </td>
                     <td>
                        Old
                    </td>
                    <td>
                      New
                    </td>
                     <td>
                        Old
                    </td>
                    <td>
                      New
                    </td>
                     <td>
                        Old
                    </td>
                    <td>
                      New
                    </td>
                     <td>
                        Old
                    </td>
                    <td>
                      New
                    </td>
                </tr>
                <tr>
                    <td align="center" class="labelBlue"><font size="3"><b>1</b></font>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel3_Item1" value="2" styleId="31" onclick="evaluate6(31),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel4_Item1" value="2" styleId="41" onclick="evaluate6(41),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel5_Item1" value="2" styleId="51" onclick="evaluate6(51),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel6_Item1" value="2" styleId="61" onclick="evaluate6(61),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel7_Item1" value="2" styleId="71" onclick="evaluate6(71),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel8_Item1" value="2" styleId="81" onclick="evaluate6(81),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel9_Item1" value="2" styleId="91" onclick="evaluate6(91),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel10_Item1" value="2" styleId="101" onclick="evaluate6(101),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel12_Item1" value="4" styleId="121" onclick="evaluate6(121),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel14_Item1" value="4" styleId="141" onclick="evaluate6(141),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel16_Item1" value="4" styleId="161" onclick="evaluate6(161),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel19_Item1" value="6" styleId="191" onclick="evaluate6(191),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel22_Item1" value="6" styleId="221"onclick="evaluate6(221),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                </tr>
                <tr>
                    <td align="center" class="labelBlue"><font size="3"><b>2
                    </td>
                    <td align="center"><html:checkbox property="ageLevel3_Item2" value="2" styleId="32" onclick="evaluate6(32),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel4_Item2" value="2" styleId="42" onclick="evaluate6(42),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel5_Item2" value="2" styleId="52" onclick="evaluate6(52),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel6_Item2" value="2" styleId="62" onclick="evaluate6(62),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel7_Item2" value="2" styleId="72" onclick="evaluate6(72),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel8_Item2" value="2" styleId="82" onclick="evaluate6(82),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel9_Item2" value="2" styleId="92" onclick="evaluate6(92),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel10_Item2" value="2" styleId="102" onclick="evaluate6(102),selectSelectAllCheckBoxes()"/>
                    </td> <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>

                    <td align="center"><html:checkbox property="ageLevel12_Item2" value="4" styleId="122" onclick="evaluate6(122),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel14_Item2" value="4" styleId="142" onclick="evaluate6(142),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel16_Item2" value="4" styleId="162" onclick="evaluate6(162),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel19_Item2" value="6" styleId="192" onclick="evaluate6(192),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel22_Item2" value="6" styleId="222" onclick="evaluate6(222),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                </tr>
                <tr>

                    <td align="center" class="labelBlue"><font size="3"><b>3
                    </td>
                    <td align="center"><html:checkbox property="ageLevel3_Item3" value="2" styleId="33" onclick="evaluate6(33),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel4_Item3" value="2" styleId="43" onclick="evaluate6(43),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel5_Item3" value="2" styleId="53" onclick="evaluate6(53),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel6_Item3" value="2" styleId="63" onclick="evaluate6(63),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel7_Item3" value="2" styleId="73" onclick="evaluate6(73),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel8_Item3" value="2" styleId="83" onclick="evaluate6(83),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel9_Item3" value="2" styleId="93" onclick="evaluate6(93),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel10_Item3" value="2" styleId="103" onclick="evaluate6(103),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel12_Item3" value="4" styleId="123" onclick="evaluate6(123),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel14_Item3" value="4" styleId="143" onclick="evaluate6(143),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel16_Item3" value="4" styleId="163" onclick="evaluate6(163),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel19_Item3" value="6" styleId="193" onclick="evaluate6(193),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel22_Item3" value="6" styleId="223" onclick="evaluate6(223),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                </tr>
                <tr>
                    <td align="center" class="labelBlue"><font size="3"><b>4</b></font></td>
                    <td align="center"><html:checkbox property="ageLevel3_Item4" value="2" styleId="34" onclick="evaluate6(34),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel4_Item4" value="2" styleId="44" onclick="evaluate6(44),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel5_Item4" value="2" styleId="54" onclick="evaluate6(54),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel6_Item4" value="2" styleId="64" onclick="evaluate6(64),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel7_Item4" value="2" styleId="74" onclick="evaluate6(74),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel8_Item4" value="2" styleId="84" onclick="evaluate6(84),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel9_Item4" value="2" styleId="94" onclick="evaluate6(94),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel10_Item4" value="2" styleId="104" onclick="evaluate6(104),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel12_Item4" value="4" styleId="124" onclick="evaluate6(124),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel14_Item4" value="4" styleId="144" onclick="evaluate6(144),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel16_Item4" value="4" styleId="164" onclick="evaluate6(164),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel19_Item4" value="6" styleId="194" onclick="evaluate6(194),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel22_Item4" value="6" styleId="224" onclick="evaluate6(224),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                </tr>
                <tr>
                    <td align="center" class="labelBlue"><font size="3"><b>5</b></font></td>
                    <td align="center"><html:checkbox property="ageLevel3_Item5" value="2" styleId="35" onclick="evaluate6(35),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel4_Item5" value="2" styleId="45"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel5_Item5" value="2" styleId="55"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel6_Item5" value="2" styleId="65"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel7_Item5" value="2" styleId="75"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel8_Item5" value="2" styleId="85"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel9_Item5" value="2" styleId="95"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel10_Item5" value="2" styleId="105"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel12_Item5" value="4" styleId="125"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel14_Item5" value="4" styleId="145"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel16_Item5" value="4" styleId="165"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel19_Item5" value="6" styleId="195"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel22_Item5" value="6" styleId="225"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                </tr>
                <tr>

                    <td align="center" class="labelBlue"><font size="3"><b>6</b></font></td>
                    <td align="center"><html:checkbox property="ageLevel3_Item6" value="2" styleId="36" onclick="evaluate6(36),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel4_Item6" value="2" styleId="46" onclick="evaluate6(46),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel5_Item6" value="2" styleId="56" onclick="evaluate6(56),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel6_Item6" value="2" styleId="66" onclick="evaluate6(66),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel7_Item6" value="2" styleId="76" onclick="evaluate6(76),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel8_Item6" value="2" styleId="86" onclick="evaluate6(86),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel9_Item6" value="2" styleId="96" onclick="evaluate6(96),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel10_Item6" value="2" styleId="106" onclick="evaluate6(106),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel12_Item6" value="4" styleId="126" onclick="evaluate6(126),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel14_Item6" value="4" styleId="146" onclick="evaluate6(146),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel16_Item6" value="4" styleId="166" onclick="evaluate6(166),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel19_Item6" value="6" styleId="196" onclick="evaluate6(196),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                    <td align="center"><html:checkbox property="ageLevel22_Item6" value="6" styleId="226" onclick="evaluate6(226),selectSelectAllCheckBoxes()"/>
                    </td>
                     <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    </td>
                </tr>
                <tr class="labelBlue">
                    <td align="center" class="labelBlue"><font size="2"><b>Alt. No. 1</b></font></td>
                    <td align="center"><html:checkbox property="ageLevel3_ItemAlt1" value="2" styleId="37" onclick="evaluate6(37),selectSelectAllCheckBoxes()"/>

                         <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    <td align="center"><html:checkbox property="ageLevel4_ItemAlt1" value="2" styleId="47" onclick="evaluate6(47),selectSelectAllCheckBoxes()"/>

                     <td align="center"><input type="checkbox" name="mohan" value="4"/>

                    <td align="center"><html:checkbox property="ageLevel5_ItemAlt1" value="2" styleId="57" onclick="evaluate6(57),selectSelectAllCheckBoxes()"/>

                     <td align="center"><input type="checkbox" name="mohan" value="4"/>

                    <td align="center"><html:checkbox property="ageLevel6_ItemAlt1" value="2" styleId="67" onclick="evaluate6(67),selectSelectAllCheckBoxes()"/>

                     <td align="center"><input type="checkbox" name="mohan" value="4"/>

                    <td align="center"><html:checkbox property="ageLevel7_ItemAlt1" value="2" styleId="77" onclick="evaluate6(77),selectSelectAllCheckBoxes()"/>

                     <td align="center"><input type="checkbox" name="mohan" value="4"/>

                    <td align="center"><html:checkbox property="ageLevel8_ItemAlt1" value="2" styleId="87" onclick="evaluate6(87),selectSelectAllCheckBoxes()"/>

                     <td align="center"><input type="checkbox" name="mohan" value="4"/>

                    <td align="center"><html:checkbox property="ageLevel9_ItemAlt1" value="2" styleId="97" onclick="evaluate6(97),selectSelectAllCheckBoxes()"/>

                     <td align="center"><input type="checkbox" name="mohan" value="4"/>

                    <td align="center"><html:checkbox property="ageLevel10_ItemAlt1" value="2" styleId="107" onclick="evaluate6(107),selectSelectAllCheckBoxes()"/>

                     <td align="center"><input type="checkbox" name="mohan" value="4"/>

                    <td align="center"><html:checkbox property="ageLevel12_ItemAlt1" value="4" styleId="127" onclick="evaluate6(127),selectSelectAllCheckBoxes()"/>

                     <td align="center"><input type="checkbox" name="mohan" value="4"/>

                    <td align="center"><html:checkbox property="ageLevel14_ItemAlt1" value="4" styleId="147" onclick="evaluate6(147),selectSelectAllCheckBoxes()"/>

                     <td align="center"><input type="checkbox" name="mohan" value="4"/>

                    <td align="center"><html:checkbox property="ageLevel16_ItemAlt1" value="4" styleId="167" onclick="evaluate6(167),selectSelectAllCheckBoxes()"/>

                     <td align="center"><input type="checkbox" name="mohan" value="4"/>

                    <td align="center"><html:checkbox property="ageLevel19_ItemAlt1" value="6" styleId="197" onclick="evaluate6(197),selectSelectAllCheckBoxes()"/>

                     <td align="center"><input type="checkbox" name="mohan" value="4"/>

                    <td align="center"><html:checkbox property="ageLevel22_ItemAlt1" value="6" styleId="227" onclick="evaluate6(227),selectSelectAllCheckBoxes()"/>

                    <td align="center"><input type="checkbox" name="mohan" value="4"/>
                </tr>
                <tr>
                    <td align="center" class="labelBlue"><font size="2"><b>Alt. No. 2</b></font></td>
                    <td align="center"><html:checkbox property="ageLevel3_ItemAlt2" value="2" styleId="38" onclick="evaluate6(38),selectSelectAllCheckBoxes()"/>
                   <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    <td align="center"><html:checkbox property="ageLevel4_ItemAlt2" value="2" styleId="48" onclick="evaluate6(48),selectSelectAllCheckBoxes()"/>
                    <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    <td align="center"><html:checkbox property="ageLevel5_ItemAlt2" value="2" styleId="58" onclick="evaluate6(58),selectSelectAllCheckBoxes()"/>
                    <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    <td align="center"><html:checkbox property="ageLevel6_ItemAlt2" value="2" styleId="68" onclick="evaluate6(68),selectSelectAllCheckBoxes()"/>
                    <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    <td align="center"><html:checkbox property="ageLevel7_ItemAlt2" value="2" styleId="78" onclick="evaluate6(78),selectSelectAllCheckBoxes()"/>
                    <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    <td align="center"><html:checkbox property="ageLevel8_ItemAlt2" value="2" styleId="88" onclick="evaluate6(88),selectSelectAllCheckBoxes()"/>
                   <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    <td align="center"><html:checkbox property="ageLevel9_ItemAlt2" value="2" styleId="98" onclick="evaluate6(98),selectSelectAllCheckBoxes()"/>
                    <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    <td align="center"><html:checkbox property="ageLevel10_ItemAlt2" value="2" styleId="108" onclick="evaluate6(108),selectSelectAllCheckBoxes()"/>
                   <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    <td align="center"><html:checkbox property="ageLevel12_ItemAlt2" value="4" styleId="128" onclick="evaluate6(128),selectSelectAllCheckBoxes()"/>
                    <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    <td align="center"><html:checkbox property="ageLevel14_ItemAlt2" value="4" styleId="148" onclick="evaluate6(148),selectSelectAllCheckBoxes()"/>
                    <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    <td align="center"><html:checkbox property="ageLevel16_ItemAlt2" value="4" styleId="168" onclick="evaluate6(168),selectSelectAllCheckBoxes()"/>
                   <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    <td align="center"><html:checkbox property="ageLevel19_ItemAlt2" value="6" styleId="198" onclick="evaluate6(198),selectSelectAllCheckBoxes()"/>
                    <td align="center"><input type="checkbox" name="mohan" value="4"/>
                    <td align="center"><html:checkbox property="ageLevel22_ItemAlt2" value="6" styleId="228" onclick="evaluate6(228),selectSelectAllCheckBoxes()"/>
                    <td align="center"><input type="checkbox" name="mohan" value="4"/>
                </tr>
                <tr>
                 <td align="center" class="labelBlue"><font size="2"><b>Alt. No. 3</b></font></td>
                <td align="center"><html:checkbox property="ageLevel3_ItemAlt3" value="2" styleId="39" onclick="evaluate6(39),selectSelectAllCheckBoxes()"/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><html:checkbox property="ageLevel4_ItemAlt3" value="2" styleId="49" onclick="evaluate6(49),selectSelectAllCheckBoxes()"/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><html:checkbox property="ageLevel5_ItemAlt3" value="2" styleId="59" onclick="evaluate6(59),selectSelectAllCheckBoxes()"/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><html:checkbox property="ageLevel6_ItemAlt3" value="2" styleId="69" onclick="evaluate6(69),selectSelectAllCheckBoxes()"/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><html:checkbox property="ageLevel7_ItemAlt3" value="2" styleId="79" onclick="evaluate6(79),selectSelectAllCheckBoxes()"/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><html:checkbox property="ageLevel8_ItemAlt3" value="2" styleId="89" onclick="evaluate6(89),selectSelectAllCheckBoxes()"/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><html:checkbox property="ageLevel9_ItemAlt3" value="2" styleId="99" onclick="evaluate6(99),selectSelectAllCheckBoxes()"/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><html:checkbox property="ageLevel10_ItemAlt3" value="2" styleId="109" onclick="evaluate6(109),selectSelectAllCheckBoxes()"/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><html:checkbox property="ageLevel12_ItemAlt3" value="4" styleId="129" onclick="evaluate6(129),selectSelectAllCheckBoxes()"/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><html:checkbox property="ageLevel14_ItemAlt3" value="4" styleId="149" onclick="evaluate6(149),selectSelectAllCheckBoxes()"/>
                </td><td align="center"><input type="checkbox" name="mohan" value="4"/>

                <td align="center"><html:checkbox property="ageLevel16_ItemAlt3" value="4" styleId="169" onclick="evaluate6(169),selectSelectAllCheckBoxes()"/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><html:checkbox property="ageLevel19_ItemAlt3" value="6" styleId="199" onclick="evaluate6(199),selectSelectAllCheckBoxes()"/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><html:checkbox property="ageLevel22_ItemAlt3" value="6" styleId="229" onclick="evaluate6(229),selectSelectAllCheckBoxes()"/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <tr>



                <tr>
                    <td align="center" class="labelBlue"><font size="2"><b>SelectAll</b></font></td>
                <td align="center"><input type="checkbox" id="age3" onclick="age_3_selectall(),selectSelectAllCheckBoxes()"/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><input type="checkbox"  id="age4" onclick="age_4_selectall() "/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><input type="checkbox"  id="age5" onclick="age_5_selectall() "/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><input type="checkbox"  id="age6" onclick="age_6_selectall() "/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><input type="checkbox"  id="age7" onclick="age_7_selectall() "/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><input type="checkbox"  id="age8" onclick="age_8_selectall() "/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><input type="checkbox"  id="age9" onclick="age_9_selectall() "/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><input type="checkbox"  id="age10" onclick="age_10_selectall() "/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><input type="checkbox"  id="age12" onclick="age_12_selectall() "/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><input type="checkbox"  id="age14" onclick="age_14_selectall() "/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><input type="checkbox" id="age16" onclick="age_16_selectall() "/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><input type="checkbox"  id="age19" onclick="age_19_selectall() "/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <td align="center"><input type="checkbox"  id="age22" onclick="age_22_selectall() "/>
                </td>
                <td align="center"><input type="checkbox" name="mohan" value="4"/>
                <tr>

            </table>
            <br>

        </html:form>
            <form action="">
                 <table align="center">

                <tr >
                      <td><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/></td>
        <td><html:button property="" value="Print" onclick="window.print()" styleClass="button"/></td>
                </tr>
            </table>
            </form>
    </BODY>
    <script>

                function goURL()
{
            document.forms[0].action="partcPrint.do?selectPartc=selectPartc";
            document.forms[0].submit();
}

         </script>
</HTML>

