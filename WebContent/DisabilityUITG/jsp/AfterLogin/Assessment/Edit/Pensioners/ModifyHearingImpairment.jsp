<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

 <html:html>
     <HEAD>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>Disabilities</title>
         <script LANGUAGE="JavaScript" SRC="<%=request.getContextPath() %>/DisabilityUITG/js/jsmenu/lw_layers.js"></script>
         <script LANGUAGE="JavaScript" SRC="<%=request.getContextPath() %>/DisabilityUITG/js/jsmenu/menu.js"></script><%-- 
         <script language="JavaScript1.2" src="<%=request.getContextPath() %>/DisabilityUITG/js/jsmenu/coolmenus3.js"></script>
         <script language="javascript" src="<%=request.getContextPath() %>/DisabilityUITG/js/jsmenu/cal2.js"></script>
         <script language="javascript" src="<%=request.getContextPath() %>/DisabilityUITG/js/jsmenu/cal_conf2.js"></script> --%>
         
         <TITLE> New Document </TITLE>
         <META property="Generator" CONTENT="EditPlus">
         <META property="Author" CONTENT="">
         <META property="Keywords" CONTENT="">
         <META property="Description" CONTENT="">
     </HEAD>

     <style type="text/css">
         v\:* { behavior: url(#default#VML); }
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
         document.forms[0].action="LocomotorSublinkUpdateForwdAction.do?getDisabilityPercentages=getDisabilityPercentages";
         document.forms[0].submit();
         }
     </script>
     <script language=JavaScript>


          function textCounter2()
        {
   if (document.forms[0].anyotherhearingimpairement.value.length > 100) // if too long...trim it!
       document.forms[0].anyotherhearingimpairement.value = document.forms[0].anyotherhearingimpairement.value.substring(0,100);
    // otherwise, update 'characters left' counter
        }


    function validateForm(thisForm){
        var flag = true;
        if(flag){
            if (thisForm.getAttribute('submitted')){
                flag = false;
                return flag;
            }else{
                thisForm.setAttribute('submitted','true');
                 document.getElementById('trunButton').disabled = true;
            }
        }
        return flag;
    }

     </script>
    <script language="javascript" src="./DisabilityUITG/js/hearing.js"></script>
     <BODY>
         <html:form action="/hearingupdate.do?updateHearing=updateHearing"
                    onsubmit="return validateForm(this)" method="post" styleId="hearingactionform">
             <input type="hidden" name="<%= Constants.TOKEN_KEY %>"
            value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY) %>" >

             <table  align="center" cellspacing="1" cellpadding="0"class="inputform" width="90%">

                 <tr>
                     <th colspan="5">10.&nbsp;&nbsp UPDATE HEARING IMPAIRMENT</th>
                 </tr>
        

                 <tr>
                     <th><br>Category</th>
                     <th><br>Type of Impairment</th>
                     <th><br>DB Level</th>
                     <th><br>Speech discrimination</th>
                     <th><br>% age of Impairment</th>
                 </tr>

                 <tr>
                     <td ><br>I</td>
                     <td ><br>Mild hearing Impairment</td>
                     <td ><br>DB 26 to 40 dB in better ear</td>
                     <td ><br>80 to 100% in better ear</td>
                     <td ><br>Less then 40% to 50%</td>
                 </tr>

                 <tr>
                     <td  rowspan="2"><br>II</td>
                     <td ><br>&nbsp;&nbsp;(A)Moderate hearing</td>
                      <td ><br>41to 60dB in better ear</td>
                      <td ><br>50 to 80% in better ear</td>
                      <td ><br>40% to 50%</td>
                 </tr>

                 <tr>
                     <td ><br>&nbsp;&nbsp;(B)Severe hearing Impairment</td>
                     <td ><br>61 to 70 dB in hearing impairment in better ear</td>
                     <td ><br>40 to 50% in better ear</td>
                     <td ><br>51% to 70%</td>
                 </tr>

                 <tr>
                     <td  rowspan="2"><br>III</td>
                     <td ><br>&nbsp;&nbsp;(A)Profound hearing Impairement</td>
                     <td ><br>71 to 90 dB</td>
                     <td ><br>Less than 40% in better ear</td>
                     <td ><br>71% to 100%</td>
                 </tr>

                 <tr>
                     <td ><br>&nbsp;&nbsp;(B)Total deafness</td>
                     <td ><br>91 dB & above in better ear to hearing</td>
                     <td ><br>Very Poor discrimination</td>
                     <td ><br>100%</td>
                 </tr>

        
                 <tr>
                 <th colspan="5" >Pure Tone Audiometry<font size="2">(please assess the frequencies and write the
                         values in appropriate boxes)</font></th>
                 </tr>
             </table>
             <table  align="center" cellspacing="1" cellpadding="0"border="0" class="inputform" width="90%">
                 <tr>
                     <th align="center" >Frequency(HZ)</th>
                     <th>250</th>
                     <th >500<font color="red"><b>*</b></font></th>
                     <th >1000<font color="red"><b>*</b></font></th>
                     <th >2000<font color="red"><b>*</b></font></th>
                     <th >4000</th>
                     <th >8000</th>
                     <th >DB Level</th>
                     <th >Clickhere</th>
                 </tr>
                 <tr>
                     <td align="center" >Right Ear</td>
                     <td><html:text property="rightear250" styleId="rightear250" size="5" maxlength="3" style="width:50px" /></td>
                     <td><html:text property="rightear500" styleId="rightear500" size="5" maxlength="3" onkeyup="righteardblevelcalculation()" onblur="righteardblevelcalculation()" style="width:50px" /></td>
                     <td><html:text property="rightear1000" styleId="rightear1000" size="5" maxlength="3" onkeyup="righteardblevelcalculation()" onblur="righteardblevelcalculation()" style="width:50px" /></td>
                     <td><html:text property="rightear2000" styleId="rightear2000" size="5" maxlength="3" onkeyup="righteardblevelcalculation()" onblur="righteardblevelcalculation()" style="width:50px" /></td>
                     <td><html:text property="rightear4000" styleId="rightear4000" size="5" maxlength="3" style="width:50px" /></td>
                     <td><html:text property="rightear8000" styleId="rightear8000" size="5" maxlength="3" style="width:50px" /></td>
                     <td><html:text property="righteardblevel" styleId="righteardblevel" size="5" onclick="righteardblevelcalculation()" readonly="true" style="width:50px" /></td>
                     <td rowspan="2"><html:button property="" value="Graph" onclick="Calculate()" onfocus="validatecharecters()" style="width:50px" /></td>
                 </tr>
                 <tr>
                     <td align="center" >Left Ear</td>
                     <td><html:text property="leftear250" styleId="leftear250" size="5" maxlength="3" style="width:50px" /></td>
                     <td><html:text property="leftear500" styleId="leftear500" size="5" maxlength="3" onkeyup="righteardblevelcalculation()" onblur="righteardblevelcalculation()" style="width:50px" /></td>
                     <td><html:text property="leftear1000" styleId="leftear1000" size="5" maxlength="3" onkeyup="righteardblevelcalculation()" onblur="righteardblevelcalculation()" style="width:50px" /></td>
                     <td><html:text property="leftear2000" styleId="leftear2000" size="5" maxlength="3" onkeyup="righteardblevelcalculation()" onblur="righteardblevelcalculation()" style="width:50px" /></td>
                     <td><html:text property="leftear4000" styleId="leftear4000" size="5" maxlength="3" style="width:50px" /></td>
                     <td><html:text property="leftear8000" styleId="leftear8000" size="5" maxlength="3" style="width:50px" /></td>
                     <td><html:text property="lefteardblevel" styleId="lefteardblevel" size="5" onclick="righteardblevelcalculation()" readonly="true" style="width:50px" /></td>

                 </tr>
                 <tr id="row1">
                     <td align="center" colspan="8">
                         <div id="graphdiv" align="center" class="graph" class="button"></div>
                     <html:button value="Print" property="print" styleId="print" style="visibility:hidden" onclick="valuessending()">
                         </html:button>

                     </td>
                     <td align="left"><div  id="static" style="visibility:hidden"><font color="red"><font color="red" size="4">
                     .</font>_____ Right Ear</font><br><font color="blue">o ------ Left Ear</font></div></td>
                 </tr>


             </table>

             <table  align="center" cellspacing="1" cellpadding="0"class="inputform" width="90%">
                 <tr>
                 <th colspan="2" align="center" class="heading">Speech Discrimination</th>
                </tr>
                </table>
                     <table  align="center" cellspacing="1" cellpadding="0"class="inputform" width="90%">
                 <tr>
                     <td >%of speech discrimination in Right Ear</td>
                     <td><html:text property="speechaudiometryrightear_pta" styleId="speechaudiometryrightear_pta" size="5" maxlength="3" /></td>
                 </tr>
                 <tr>
                     <td >%of speech discrimination in Left Ear</td>
                     <td><html:text property="speechaudiometryleftear_pta" styleId="speechaudiometryleftear_pta" size="5" maxlength="3" /></td>
                 </tr>

             </table><br>


             <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">

                 <tr>
                     <th colspan="2" class="heading">Need Assessment/Referred/Recommended</th>
                 </tr>
             </table>



             <table  align="center" cellspacing="1" cellpadding="0"border="0" class="inputform" width="90%">
                   <%
                        int caAU = 0, ca = 0,caPer=0;
                        if (session.getAttribute("ageAU") != null) {
                            caAU = Integer.parseInt((String) session.getAttribute("ageAU"));
                        }else if(session.getAttribute("agePersonalInsert") != null) {
                               caPer = Integer.parseInt((String) session.getAttribute("agePersonalInsert"));
                         }else {
                            if(session.getAttribute("agePersonal")!=null){
                            ca = Integer.parseInt((String) session.getAttribute("agePersonal"));
                            }
                        }
                        if (caAU <= 3 && ca <= 3 && caPer <= 3) {
            %>

                <tr>
                     <td colspan="2" >1. Early Intervention Services &nbsp;(For Children Below 3 Years)</td>
                 </tr>
                 <tr>
                     <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Speech Therapy</td>
                     <td><html:checkbox property="speechtheraphy" styleId="speechtheraphy" value=" EarlyIntervention Speech Therapy" style="width:25px"> </html:checkbox></td>
                 </tr>
                 <tr>
                     <td width="50%" >2. Surgery</td>
                 <td width="48%"><html:text property="surgery" styleId="surgery" maxlength="75" style="width:250px"></html:text> </td>
                 </tr>
                 <tr>
                     <td >3. Speech Therapy</td>
                     <td><html:checkbox property="speechtherapy" styleId="speechtherapy"  value="Speech Therapy " disabled="true" style="width:25px"> </html:checkbox> </td>
                 </tr>
                 <tr>
                     <td  >4. Language Development</td>
                     <td><html:checkbox property="languagedevelopment" styleId="languagedevelopment" value="Language Development" disabled="true" style="width:25px"></html:checkbox> </td>
                 </tr>

               <%} else {%>
                 <tr>
                     <td colspan="2" >1. Early Intervention Services &nbsp;(For Children Below 3 Years)</td>
                 </tr>
                 <tr>
                     <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Speech Therapy</td>
                     <td><html:checkbox property="speechtheraphy" styleId="speechtheraphy" value=" EarlyIntervention Speech Therapy" disabled="true" style="width:25px"> </html:checkbox></td>
                 </tr>
                 <tr>
                     <td width="50%" >2. Surgery</td>
                 <td width="48%"><html:text property="surgery" styleId="surgery" maxlength="75" style="width:250px"></html:text> </td>
                 </tr>
                 <tr>
                     <td >3. Speech Therapy</td>
                 <td><html:checkbox property="speechtherapy" styleId="speechtherapy" value="Speech Therapy " style="width:25px" style="width:25px"> </html:checkbox> </td>
                 </tr>
                 <tr>
                     <td  >4. Language Development</td>
                     <td><html:checkbox property="languagedevelopment"  styleId="languagedevelopment" value="Language Development" style="width:25px"></html:checkbox> </td>
                 </tr>
                 <%}%>
                 <tr>
                     <td   colspan="2">5.  Assistive & Augmentative Devices For  Hearing  Impairment</td>
                 </tr>
                 <tr>
                     <td width="50%"  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;i.Hearing Aid <font size="1">(Select Fields)</font></td>
                     <td width="48%" >
                     <html:select property="hearingaidselect"  styleId="hearingaidselect" >
                     <html:option value="" styleId="selectedhearingaid">---  Select One ---</html:option>
                     <html:option value="Low Intensity">Low Intensity</html:option>
                     <html:option value="Moderate Intensity">Moderate Intensity</html:option>
                     <html:option value="High Intensity">High Intensity</html:option>
                 </html:select>
                 <html:select property="hearingaidtype"  styleId="hearingaidtype"  >
                 <html:option value="" styleId="hearingaidtype">---  Select One ---</html:option>
                 <html:option value="S-Cord">S-Cord</html:option>
                 <html:option value="V-Cord">V-Cord</html:option></html:select>
                     </td>
                 </tr>
                 <tr>
                     <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Cochlear Implantation</td>
                     <td> <html:checkbox property="cochlearimplantation" styleId="cochlearimplantation" value="Cochlear Implantation" style="width:25px"> </html:checkbox> </td>
                 </tr>
                 <tr>
                     <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.Implantable Hearing Aid</td>
                     <td> <html:checkbox property="implantablehearingaid" styleId="implantablehearingaid" value="Implantable Hearing Aid" style="width:25px"> </html:checkbox> </td>
                 </tr>

                 <tr>
                     <td  >6. Any Other Hearing Impairment Needs</td>
                     <td><html:textarea rows="4" cols="30" property="anyotherhearingimpairement" styleId="anyotherhearingimpairement" onkeydown="textCounter2()" onkeyup="textCounter2()" style="width:300px">
                         </html:textarea></td>
                 </tr>
    
                 <tr>
                     <th colspan="4"><html:button property=""  value="Back" onclick="goBack()" styleClass="button" />
                  <html:submit  value="Update" styleId="trunButton"  onfocus="validatecharecters()" onclick="return getingvalues(this)" onmouseover="righteardblevelcalculation()" styleClass="button"/>
                    <html:button  property="" value="Reset" onclick="resetbutton()" styleClass="button" /></th>

             </table>

         </html:form>
     </BODY>
 </html:html>
