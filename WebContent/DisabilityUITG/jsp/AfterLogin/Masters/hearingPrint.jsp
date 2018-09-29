
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%><HEAD><br>


 <html:html>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>Disabilities</title>
         <script LANGUAGE="JavaScript" SRC="../js/lw_layers.js"></script>
         <script LANGUAGE="JavaScript" SRC="../js/menu.js"></script>
         <script language="JavaScript1.2" src="DisabilityUITG/js/coolmenus3.js"></script>
         <script language="javascript" src="DisabilityUITG/js/cal2.js"></script>
         <script language="javascript" src="DisabilityUITG/js/cal_conf2.js"></script>
         
         <TITLE> New Document </TITLE>
         <META property="Generator" CONTENT="EditPlus">
         <META property="Author" CONTENT="">
         <META property="Keywords" CONTENT="">
         <META property="Description" CONTENT="">
     </HEAD>

     <style type="text/css">
         v\: { behavior: url(#default#VML); }
     </style>
     <script src="./DisabilityUITG/js/XYGraph.js" type="text/javascript"></script>
     <script src="./DisabilityUITG/js/XYGraphBoneconduction.js" type="text/javascript"></script>




     <script type="text/javascript">

         function getingvalues() {
         var rightear=document.getElementById("righteardblevel").value;
         var leftear=document.getElementById("lefteardblevel").value;
         var a;
         var betterear;
         var poorerear;
         if(rightear < leftear){
         betterear=parseInt(rightear);
         poorerear=parseInt(leftear);
         a=1;
         }else{
         betterear=parseInt(leftear);
         poorerear=parseInt(rightear);
         a=0;
         }
         if(a==1){
         var rightearspeech=document.getElementById("speechaudiometryrightear_pta").value;
         var answer;
         if(rightear>25 && rightear<40){

         if(80> rightearspeech && rightearspeech <100){
          answer = confirm("For Better Ear " +betterear+ " DB Level, SpeechDiscrimination range should be between 80-100%. Click ok to change else Click cancel")
         }
         }
         else if(rightear>41 && rightear<61){
         if(rightearspeech>50 && rightearspeech<80 ){
         return true;
         }else{
          answer = confirm("For Better Ear " +betterear+ " DB Level, SpeechDiscrimination range should be between 50-80%. Click ok to change else Click cancel")
         }
         }
          else if(rightear>60 && rightear<71){
         if(rightearspeech>40 && rightearspeech<50){
         return true;
         }else{
          answer = confirm("For Better Ear " +betterear+ " DB Level ,SpeechDiscrimination range should be between 40-50%. Click ok to change else Click cancel")
         }
         }
         else if(rightear>70 && rightear<90){

         if(rightearspeech>0 && rightearspeech<40){
         return true;
         }else{
          answer = confirm("For Better Ear " +betterear+ " DB Level ,SpeechDiscrimination range should be LessThen 40%. Click ok to change else Click cancel")
         }
         }
         else if(rightear>90){
         if(rightearspeech>0){
         answer = confirm("For Better Ear " +betterear+ " DB Level ,SpeechDiscrimination range should be zero. Click ok to change else Click cancel")
         }
         }
         if (answer){
         document.forms[0].speechaudiometryrightear_pta.value="";
         document.forms[0].speechaudiometryrightear_pta.focus();
         return false
         }
         else{
         return true
         }


         }else{
         var leftearspeech=document.getElementById("speechaudiometryleftear_pta").value;
         var answer;
         if(leftear>25 && leftear<40){
         if(80> leftearspeech && leftearspeech <100){
          answer = confirm("For Better Ear " +betterear+ " DB Level ,SpeechDiscrimination range should be between 80-100%. Click ok to change else Click cancel")
        }
        }
      else if(leftear>41 && leftear<61){
         if(leftearspeech>50 && leftearspeech<80 ){
         return true;
         }else{
          answer = confirm("For Better Ear " +betterear+ " DB Level, SpeechDiscrimination range should be between 50-80%. Click ok to change else Click cancel")
         }
         }
          else if(leftear>60 && leftear<71){
         if(leftearspeech>40 && leftearspeech<50){
         return true;
         }else{
          answer = confirm("For Better Ear " +betterear+ " DB Level, SpeechDiscrimination range should be between 40-50%. Click ok to change else Click cancel")
         }
         }
         else if(leftear>70 && leftear<90){

         if(leftearspeech>0 && leftearspeech<40){
         return true;
         }else{
          answer = confirm("For Better Ear " +betterear+ " DB Level ,SpeechDiscrimination range should be LessThen 40%. Click ok to change else Click cancel")
         }
         }
         else if(leftear>90){
         if(leftearspeech>0){
         answer = confirm("For Better Ear " +betterear+ " DB Level, SpeechDiscrimination range should be zero. Click ok to change else Click cancel")
         }
         }

        if (answer){
         document.forms[0].speechaudiometryleftear_pta.value="";
         document.forms[0].speechaudiometryleftear_pta.focus();
         return false
         }
         else{
         return true
         }

         }


         }

     </script>
     <script type="text/javascript">

         var MyGraph;
         function Calculate() {

         MyGraph = new XYGraph(); // define new XYGraph object
         var MyLine = new XYLine();   // define new XYLine object


         var re250=document.getElementById("rightear250").value;
         var re500=document.getElementById("rightear500").value;
         var re1000=document.getElementById("rightear1000").value;
         var re2000=document.getElementById("rightear2000").value;
         var re4000=document.getElementById("rightear4000").value;
         var re8000=document.getElementById("rightear8000").value;

         var le250=document.getElementById("leftear250").value;
         var le500=document.getElementById("leftear500").value;
         var le1000=document.getElementById("leftear1000").value;
         var le2000=document.getElementById("leftear2000").value;
         var le4000=document.getElementById("leftear4000").value;
         var le8000=document.getElementById("leftear8000").value;

         if(re500==""){
         alert("Right Ear 500 must be entered");
         return false
         }
         if(re1000==""){
         alert("Right Ear 1000 must be entered");
         return false
         }
         if(re2000==""){
         alert("Right Ear 2000 must be entered");
         return false
         }
         if(le500==""){
         alert("Left Ear 500 must be entered");
         return false
         }
         if(le1000==""){
         alert("Left Ear 1000 must be entered");
         return false
         }
         if(le2000==""){
         alert("Left Ear 2000 must be entered");
         return false
         }
         var first=250;
         var second=500;
         var third=1000;
         var fourth=2000;
         var fifth=4000;
         var sixth=8000;


         if(re250==""){
         first=parseInt(second);
         re250=parseInt(re500);
         }
         if(re4000==""){
         fifth=parseInt(fourth);
         re4000=parseInt(re2000);
         }
         if(re8000==""){
         sixth=parseInt(fifth);
         re8000=parseInt(re4000);

         }




         MyLine.x = [first,second,third,fourth,fifth,sixth];
         MyLine.y = [re250,re500,re1000,re2000,re4000,re8000];



         <!--MyGraph.useryticlabels=["0","10","20","30","40","50","60","70","80","90","100","120","130"];-->
         <!--MyGraph.userxticlabels=["0","1000","2000","","4000","","","","8000"];-->

         MyGraph.xscale=1000;
         MyGraph.yscale=10;
         graphdiv.innerHTML=MyGraph.Plot(MyLine);
         document.getElementById("print").style.visibility='visible';
         document.getElementById("static").style.visibility='visible';
         document.getElementById("row1").style.visibility='visible';
         document.getElementById("row1").style.display='inline';
         Calculateleft();
         }




         function Calculateleft() {
         var MyLine2 = new XYLine2();   // define new XYLine object

         var le250=document.getElementById("leftear250").value;
         var le500=document.getElementById("leftear500").value;
         var le1000=document.getElementById("leftear1000").value;
         var le2000=document.getElementById("leftear2000").value;
         var le4000=document.getElementById("leftear4000").value;
         var le8000=document.getElementById("leftear8000").value;

         var first=250;
         var second=500;
         var third=1000;
         var fourth=2000;
         var fifth=4000;
         var sixth=8000;


         if(le250==""){
         first=parseInt(second);
         le250=parseInt(le500);
         }
         if(le4000==""){
         fifth=parseInt(fourth);
         le4000=parseInt(le2000);
         }
         if(le8000==""){
         sixth=parseInt(fifth);
         le8000=parseInt(le4000);
         }
         MyLine2.x = [first,second,third,fourth,fifth,sixth];
         MyLine2.y = [le250,le500,le1000,le2000,le4000,le8000];



         <!--MyGraph.useryticlabels=["0","10","20","30","40","50","60","70","80","90","100","120","130"];-->
         <!--MyGraph.userxticlabels=["0","1000","2000","","4000","","","","8000"];-->

         MyGraph.xscale=1000;
         MyGraph.yscale=10;
         graphdiv.innerHTML=MyGraph.Plot(MyLine2);

         document.getElementById("print").style.visibility='visible';
         document.getElementById("static").style.visibility='visible';



         }

     </script>
     <script language="javascript" >

         function righteardblevelcalculation(){

         var rightear500=document.getElementById("rightear500").value;
         var rightear1000=document.getElementById("rightear1000").value;
         var rightear2000=document.getElementById("rightear2000").value;

         var leftear500=document.getElementById("leftear500").value;
         var leftear1000=document.getElementById("leftear1000").value;
         var leftear2000=document.getElementById("leftear2000").value;

         if(rightear500!="" && rightear1000!="" && rightear2000!=""){
         document.getElementById("righteardblevel").value=Math.round((parseInt(rightear500)+parseInt(rightear1000)+parseInt(rightear2000))/3);
          }else{
           document.getElementById("righteardblevel").value = "";
         }
         if(leftear500!="" && leftear1000!="" && leftear2000!=""){
         document.getElementById("lefteardblevel").value=Math.round((parseInt(leftear500)+parseInt(leftear1000)+parseInt(leftear2000))/3);
          }else{
           document.getElementById("lefteardblevel").value = "";
         }
     }




     </script>
     <script language="javascript" >

         function  valuessending(){
         var rightear250=document.getElementById("rightear250").value;
         var rightear500=document.getElementById("rightear500").value;
         var rightear1000=document.getElementById("rightear1000").value;
         var rightear2000=document.getElementById("rightear2000").value;
         var rightear4000=document.getElementById("rightear4000").value;
         var rightear8000=document.getElementById("rightear8000").value;

         var le250=document.getElementById("leftear250").value;
         var le500=document.getElementById("leftear500").value;
         var le1000=document.getElementById("leftear1000").value;
         var le2000=document.getElementById("leftear2000").value;
         var le4000=document.getElementById("leftear4000").value;
         var le8000=document.getElementById("leftear8000").value;

         var sre_pta=document.getElementById("speechaudiometryrightear_pta").value;
         var sle_pta=document.getElementById("speechaudiometryleftear_pta").value;
         if(rightear500==""){
         alert("Right Ear 500 must be entered");
         document.hearingactionform.rightear500.focus();
         return false
         }
         if(rightear1000==""){
         alert("Right Ear 1000 must be entered");
         document.hearingactionform.rightear1000.focus();
         return false
         }
         if(rightear2000==""){
         alert("Right Ear 2000 must be entered");
         document.hearingactionform.rightear2000.focus();
         return false
         }

         if(le500==""){
         alert("Left Ear 500 must be entered");
         document.hearingactionform.leftear500.focus();
         return false
         }
         if(le1000==""){
         alert("Left Ear 1000 must be entered");
         document.hearingactionform.leftear1000.focus();
         return false
         }
         if(le2000==""){
         alert("Left Ear 2000 must be entered");
         document.hearingactionform.leftear2000.focus();
         return false
         }
         var printWindow =window.open('./DisabilityUITG/jsp/print.jsp?rightear250='+rightear250+'&rightear500='+rightear500+'&rightear1000='+rightear1000+'&rightear2000='+rightear2000+'&rightear4000='+rightear4000+'&rightear8000='+rightear8000+'&le250='+le250+'&le500='+le500+'&le1000='+le1000+'&le2000='+le2000+'&le4000='+le4000+'&le8000='+le8000+'&sre_pta='+sre_pta+'&sle_pta='+sle_pta+'');
         }

         function goBack()
         {
         document.forms[0].action="LocomotorSublinkForwdAction.do?getDisabilityPercentages=getDisabilityPercentages";
         document.forms[0].submit();
         }
     </script>
     <script language=JavaScript>

         function goBack()
         {
         document.forms[0].action="LocomotorSublinkUpdateForwdAction.do?getDisabilityPercentages=getDisabilityPercentages";
         document.forms[0].submit();
         }

          function textCounter2()
        {
   if (document.forms[0].anyotherhearingimpairement.value.length > 100) // if too long...trim it!
       document.forms[0].anyotherhearingimpairement.value = document.forms[0].anyotherhearingimpairement.value.substring(0,100);
    // otherwise, update 'characters left' counter
        }


    function validateForm(thisForm){
        var flag = true;
        flag = validate_form(thisForm);
        if(flag){
            if (thisForm.getAttribute('submitted')){
                flag = false;
                return flag;
            }else{
                thisForm.setAttribute('submitted','true');
            }
        }
        return flag;
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
<style type="text/css">

/* GRID Starts */

.gridbg1 {
	background-color:#f4f4f4; text-align:left;  border-bottom:1px #000 solid;  border-left:1px #000 solid; vertical-align:middle; font-size:11px;  font-weight:400; height:20px; padding-left:5px; padding-right:5px; padding:4px;
}
.gridbg2 {
	background-color:#eae9e9; text-align:center;  border-bottom:1px #b0b0b0 solid;  border-left:1px #b0b0b0 solid; vertical-align:middle; font-size:10px;  font-weight:400; height:20px; padding-left:5px; padding-right:5px;
}
.gridhdbg {
	background-color:#b1b0b0; text-align:center;  border-bottom:1px #000 solid;  border-left:1px #000 solid; vertical-align:middle; font-size:12px;  font-weight:bold; height:20px;
}
.gridtb {
	border-right:1px #000 solid; border-top:1px #000 solid;
}

/* GRID Ends */
</style>

    <script language="javascript" src="./DisabilityUITG/js/hearing.js"></script>
    <BODY onLoad="disableForm(hearingactionform);">
         <html:form action="/hearingUpdatePrint.do" method="post" styleId="hearingactionform">
             <input type="hidden" name="<%= Constants.TOKEN_KEY %>"
            value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY) %>" >
              <table align="center" cellspacing="0" cellpadding="0" width="45%">
            <tr>
                <td align="center"><font color="blue"><b>SADAREM ID : <%=session.getAttribute("personcode")%></b></font></td>
            </tr>
       </table>
             <table  align="center" cellspacing="1" cellpadding="1" class="innerTable" width="85%">

                 <tr>
                 <td colspan=5 align="center" class="gridhdbg" style="border:1px #000 solid;"> HEARING IMPAIRMENT</td>
                 </tr>
             </table>

             <table  align="center" cellspacing="0" cellpadding="0" border="0" class="gridtb" width="85%">

                 <tr>
                     <td class="gridhdbg"><br>Category</td>
                     <td class="gridhdbg"><br>
                     Type of Impairment</td>
                     <td class="gridhdbg"><br>
                     DB Level</td>
                     <td class="gridhdbg"><br>
                     Speech discrimination</td>
                     <td class="gridhdbg"><br>
                     % age of Impairment</td>
                 </tr>

                 <tr>
                     <td class="gridbg1"><br>I</td>
                     <td class="gridbg1"><br>Mild hearing Impairment</td>
                 <td class="gridbg1"><br>DB 26 to 40 dB in better ear</td>
                 <td class="gridbg1"><br>80 to 100% in better ear</td>
                   <td class="gridbg1"><br>Less then 40% to 50%</td>
                 </tr>

                 <tr>
                     <td class="gridbg1" rowspan="2"><br>II</td>
                     <td class="gridbg1"><br>&nbsp;&nbsp;(A)Moderate hearing</td>
                <td class="gridbg1"><br>41to 60dB in better ear</td>
                <td class="gridbg1"><br>50 to 80% in better ear</td>
                   <td class="gridbg1"><br>40% to 50%</td>
                 </tr>

                 <tr>
                     <td class="gridbg1"><br>&nbsp;&nbsp;(B)Severe hearing Impairment</td>
                     <td class="gridbg1"><br>
                     61 to 70 dB in hearing impairment in better ear</td>
                     <td class="gridbg1"><br>
                     40 to 50% in better ear</td>
                     <td class="gridbg1"><br>
                     51% to 70%</td>
                 </tr>

                 <tr>
                     <td class="gridbg1" rowspan="2"><br>III</td>
                     <td class="gridbg1"><br>&nbsp;&nbsp;(A)Profound hearing Impairement</td>
                 <td class="gridbg1"><br>71 to 90 dB</td>
                 <td class="gridbg1"><br>Less than 40% in better ear</td>
                   <td class="gridbg1"><br>71% to 100%</td>
                 </tr>

                 <tr>
                     <td class="gridbg1"><br>
                     &nbsp;&nbsp;(B)Total deafness</td>
                     <td class="gridbg1"><br>
                     91 dB & above in better ear to hearing</td>
                     <td class="gridbg1"><br>
                     Very Poor discrimination</td>
                     <td class="gridbg1"><br>100%</td>
                 </tr>

             </table>
             <table  align="center" border="0" cellspacing="0" cellpadding="0" class="gridtb" width="85%">
                 <tr>
                 <td colspan=9 class="gridhdbg" style="border:1px #000 solid;">Pure Tone Audiometry<font size="2">(please assess the frequencies and write the
                         values in appropriate boxes)</font></td>
                 </tr>
             </table>
             <table  align="center" cellspacing="0" cellpadding="0" border="0" class="gridtb" width="85%">
                 <tr>
                     <td align="center" class="gridhdbg">Frequency(HZ)</td>
                     <td class="gridhdbg">250</td>
                     <td class="gridhdbg">500<font color="red"><b>*</b></font></td>
                     <td class="gridhdbg">1000<font color="red"><b>*</b></font></td>
                     <td class="gridhdbg">2000<font color="red"><b>*</b></font></td>
                     <td class="gridhdbg">4000</td>
                     <td class="gridhdbg">8000</td>
                     <td class="gridhdbg">DB Level</td>
                     <td class="gridhdbg">Clickhere</td>
                 </tr>
                 <tr>
                     <td align="center" class="gridbg1">Right Ear</td>
                     <td class="gridbg1"><html:text property="rightear250" size="5" maxlength="3" /></td>
                 <td class="gridbg1"><html:text property="rightear500" size="5" maxlength="3" onkeyup="righteardblevelcalculation()" onblur="righteardblevelcalculation()"/></td>
                 <td class="gridbg1"><html:text property="rightear1000" size="5" maxlength="3" onkeyup="righteardblevelcalculation()" onblur="righteardblevelcalculation()"/></td>
                 <td class="gridbg1"><html:text property="rightear2000" size="5" maxlength="3" onkeyup="righteardblevelcalculation()" onblur="righteardblevelcalculation()"/></td>
                 <td class="gridbg1"><html:text property="rightear4000" size="5" maxlength="3"/></td>
                 <td class="gridbg1"><html:text property="rightear8000" size="5" maxlength="3" /></td>
                 <td class="gridbg1"><html:text property="righteardblevel" size="5" onclick="righteardblevelcalculation()" readonly="true"/></td>
                   <td rowspan="2" class="gridbg1"><html:button property="" value="Graph" onclick="Calculate()" onfocus="validatecharecters()" /></td>
                 </tr>
                 <tr>
                     <td align="center" class="gridbg1">Left Ear</td>
                     <td class="gridbg1"><html:text property="leftear250" size="5" maxlength="3"/></td>
                 <td class="gridbg1"><html:text property="leftear500" size="5" maxlength="3" onkeyup="righteardblevelcalculation()" onblur="righteardblevelcalculation()"/></td>
                 <td class="gridbg1"><html:text property="leftear1000" size="5" maxlength="3" onkeyup="righteardblevelcalculation()" onblur="righteardblevelcalculation()"/></td>
                 <td class="gridbg1"><html:text property="leftear2000" size="5" maxlength="3" onkeyup="righteardblevelcalculation()" onblur="righteardblevelcalculation()"/></td>
                 <td class="gridbg1"><html:text property="leftear4000" size="5" maxlength="3"/></td>
                 <td class="gridbg1"><html:text property="leftear8000" size="5" maxlength="3"/></td>
                   <td class="gridbg1"><html:text property="lefteardblevel" size="5" onclick="righteardblevelcalculation()" readonly="true"/></td>

                 </tr>


                  <tr>
                     <td align="center" class="gridbg1">Right Ear</td>
                     <td class="gridbg1"><input type="text" name="rekha" size="5" maxlength="3" /></td>
                   <td class="gridbg1"><input type="text" name="rekha" size="5" maxlength="3" onKeyUp="righteardblevelcalculation()" onBlur="righteardblevelcalculation()"/></td>
                   <td class="gridbg1"><input type="text" name="rekha" size="5" maxlength="3" onKeyUp="righteardblevelcalculation()" onBlur="righteardblevelcalculation()"/></td>
                   <td class="gridbg1"><input type="text" name="rekha" size="5" maxlength="3" onKeyUp="righteardblevelcalculation()" onBlur="righteardblevelcalculation()"/></td>
                   <td class="gridbg1"><input type="text" name="rekha" size="5" maxlength="3"/></td>
                   <td class="gridbg1"><input type="text" name="rekha" size="5" maxlength="3" /></td>
                   <td class="gridbg1"><input type="text" name="rekha" size="5" onClick="righteardblevelcalculation()" readonly="true"/></td>
                    <td rowspan="2" class="gridbg1"><html:button property="" value="Graph" onclick="Calculate()" onfocus="validatecharecters()" /></td>
                 </tr>

                 <tr>
                     <td align="center" class="gridbg1">Left Ear</td>
                     <td class="gridbg1"><input type="text" name="rekha" size="5" maxlength="3"/></td>
                 <td class="gridbg1"><input type="text" name="rekha" size="5" maxlength="3" onKeyUp="righteardblevelcalculation()" onBlur="righteardblevelcalculation()"/></td>
                 <td class="gridbg1"><input type="text" name="rekha" size="5" maxlength="3" onKeyUp="righteardblevelcalculation()" onBlur="righteardblevelcalculation()"/></td>
                 <td class="gridbg1"><input type="text" name="rekha"size="5" maxlength="3" onKeyUp="righteardblevelcalculation()" onBlur="righteardblevelcalculation()"/></td>
                 <td class="gridbg1"><input type="text" name="rekha" size="5" maxlength="3"/></td>
                 <td class="gridbg1"><input type="text" name="rekha" size="5" maxlength="3"/></td>
                   <td class="gridbg1"><input type="text" name="rekha" size="5" onClick="righteardblevelcalculation()" readonly="true"/></td>

                 </tr>

                 <tr id="row1">
                     <td colspan="8" align="center" class="gridbg1">
                         <div id="graphdiv" align="center" class="button"></div>
                     <html:button value="Print" property="print" style="visibility:hidden" onclick="valuessending()">
                         </html:button>

                     </td>
                     <td align="left" class="gridbg1"><div  id="static" style="visibility:hidden"><font color="red"><font color="red" size="4">
                     .</font>_____ Right Ear</font><br><font color="blue">o ------ Left Ear</font></div></td>
                 </tr>

             </table><br>
             <table  align="center" cellspacing="0" cellpadding="0" class="gridtb" width="85%">
                 <tr>
                 <td colspan="2" align="center" class="gridhdbg" style="border:1px #000 solid;">Speech Discrimination</td>
                </tr>
                </table>
                     <table  align="center" cellspacing="0" cellpadding="0" class="gridtb" width="85%">
                      <tr>
                     <td class="gridhdbg" >&nbsp;</td>
                       <td class="gridhdbg">Old</td>
                        <td class="gridhdbg">New</td>
                      </tr>
                 <tr>
                     <td class="gridbg1">%of speech discrimination in Right Ear</td>
                     <td class="gridbg1"><html:text property="speechaudiometryrightear_pta" size="5" maxlength="3" /></td>
                   <td class="gridbg1"><input type="text" name="rekha" size="5" maxlength="3" /></td>
                 </tr>
                 <tr>
                     <td class="gridbg1">%of speech discrimination in Left Ear</td>
                     <td class="gridbg1"><html:text property="speechaudiometryleftear_pta" size="5" maxlength="3" /></td>
                   <td class="gridbg1"><input type="text" name="rekha" size="5" maxlength="3" /></td>

                 </tr>

             </table>


             <table  align="center" cellspacing="0" cellpadding="0" class="gridtb" width="85%">

           <tr>
                 <td colspan="3" class="gridhdbg" style="border:1px #000 solid;">Need Assessment/Referred/Recommended</td>
                 </tr>
             </table>



             <table  align="center" cellspacing="0" cellpadding="0" border="0" class="gridtb" width="85%">
                 <tr>
                    <td  class="gridhdbg">&nbsp;</td>
                     <td  class="gridhdbg">Old</td>
                      <td  class="gridhdbg">New</td>

                 </tr>

                <tr>
                    <td colspan="3" class="gridbg2">1. Early Intervention Services &nbsp;(For Children Below 3 Years)</td>
                 </tr>

                 <tr>
                     <td  class="gridbg1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Speech Therapy</td>
                     <td class="gridbg1"><html:checkbox property="speechtheraphy" value=" EarlyIntervention Speech Therapy" > </html:checkbox></td>
                   <td class="gridbg1"><input type="checkbox" value=" EarlyIntervention Speech Therapy" > </td>
                 </tr>
                 <tr>
                     <td  class="gridbg1">2. Surgery</td>
                 <td class="gridbg1" ><html:text property="surgery" maxlength="75"></html:text> </td>
                 <td class="gridbg1" ><input type="text" maxlength="75"/> </td>

                 </tr>
                 <tr>
                     <td class="gridbg1">3. Speech Therapy</td>
                     <td class="gridbg1"><html:checkbox property="speechtherapy" value="Speech Therapy "> </html:checkbox> </td>
                   <td class="gridbg1"><input type="checkbox" value="Speech Therapy "/>  </td>
                 </tr>
                 <tr>
                     <td  class="gridbg1">4. Language Development</td>
                     <td class="gridbg1"><html:checkbox property="languagedevelopment" value="Language Development"></html:checkbox> </td>
                   <td class="gridbg1"><input type="checkbox" value="Language Development"> </td>

                 <tr>
                     <td  class="gridbg2" colspan="3">5.  Assistive & Augmentative Devices For  Hearing  Impairment</td>
                 </tr>
                 <tr>
                     <td width="40%"  class="gridbg1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;i.Hearing Aid <font size="1">(Select Fields)</font></td>
                     <td width=25%"" class="gridbg1">
                         <html:select property="hearingaidselect"  size="3">
                   <!--  <html:option value="" styleId="selectedhearingaid">---  Select One ---</html:option>-->
                     <html:option value="Low Intensity">Low Intensity</html:option>
                     <html:option value="Moderate Intensity">Moderate Intensity</html:option>
                     <html:option value="High Intensity">High Intensity</html:option>
                 </html:select>
                 <html:select property="hearingaidtype"  size="2"  >
               <!--  <html:option value="" styleId="hearingaidtype">---  Select One ---</html:option>-->
                 <html:option value="S-Cord">S-Cord</html:option>
                 <html:option value="V-Cord">V-Cord</html:option></html:select>
                     </td>

                     <td width="25%" class="gridbg1" >
                            <select property="hearingaidselect" size="3" >
                    <!-- <option value="" styleId="selectedhearingaid">---  Select One ---</option>-->
                     <option value="Low Intensity">Low Intensity</option>
                     <option value="Moderate Intensity">Moderate Intensity</option>
                     <option value="High Intensity">High Intensity</option>
                 </select>
                  <select property="hearingaidtype"  size="2"  >
                  <option value="S-Cord">S-Cord</option>
                 <option value="V-Cord">V-Cord</option></select>
                     </td>




                 </tr>
                 <tr>
                     <td  class="gridbg1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Cochlear Implantation</td>
                     <td class="gridbg1"> <html:checkbox property="cochlearimplantation" value="Cochlear Implantation"> </html:checkbox> </td>
                     <td class="gridbg1"> <input type="checkbox" name="rekha" value="Cochlear Implantation"/>  </td>
                 </tr>
                 <tr>
                     <td  class="gridbg1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.Implantable Hearing Aid</td>
                     <td class="gridbg1"> <html:checkbox property="implantablehearingaid" value="Implantable Hearing Aid" > </html:checkbox> </td>
                     <td class="gridbg1"> <input type="checkbox"  value="Implantable Hearing Aid" />  </td>
                 </tr>

                 <tr>
                     <td  class="gridbg1">6. Any Other Hearing Impairment Needs</td>
                     <td class="gridbg1"><html:textarea rows="4" cols="30" property="anyotherhearingimpairement" onkeydown="textCounter2()" onkeyup="textCounter2()">
                         </html:textarea></td>

                     <td class="gridbg1"><textarea rows="4" cols="30" property="anyotherhearingimpairement" onKeyDown="textCounter2()" onKeyUp="textCounter2()">
                         </textarea></td>

                 </tr>
             </table><br>



         </html:form>
             <form action="">
                  <table align="center">
                 <tr>

                     <td><html:button  property="but" value="Next" onfocus="validatecharecters()" onclick="goURL()" onmouseover="righteardblevelcalculation()" styleClass="button"/></td>
                     <td><html:button  property="" value="Print" onclick="window.print();" styleClass="button" /></td>

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
 </html:html>
