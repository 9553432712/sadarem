<%--
    Document   : appleteAuthorityPersonalDetails
    Created on : Jul 30, 2011, 11:08:21 AM
    Author     : SADAREM
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>
<%
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
            int i = 0;
            int j = 0;
%>

        <%

                   // String aa = (String) session.getAttribute("dis");
                    String per=request.getParameter("sadaremCode").toString();

            %>

<html:html>
    <head>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script>
            function goForPartB() {



              var  k=document.forms[0].reason.value;

            if(k==""){
                alert("Please Enter reason for Reassessment!!!");
                return false;
            }else{

                if (k!=null && k.length > 100) // if too long...trim it!
                    k = k.substring(0,100);

                document.forms[0].mode.value="goForPartB";
                document.forms[0].submit();

            }


            }
            function back(){
                 document.forms[0].action="PrintFormatsSelection.do";
                 document.forms[0].submit();
            }




        </script>
        <style type="text/css">
            @media print
            {
                #non-printable { display: none; }
                #printable { display: block; }
            }
        </style>


        <script language="JavaScript">
 function displayAppropriate()
            {
              //  var mode=document.partAForm.mode.value;
               // alert("mode onload==="+mode);
               // if(mode=="dis"){
                var theDay =document.getElementById("disabilityId").value;
               // alert("disbilityid==="+theDay);
                switch (theDay)

                {
                    case "1":{
                            document.getElementById("locomotor").style.visibility = "Visible";
                            document.getElementById("locomotor").style.display = "Inline";
                            document.getElementById("commonCondition").style.visibility = "Visible";
                            document.getElementById("commonCondition").style.display = "Inline";
                          //  document.getElementById("conditionofdisabilityvisual").style.visibility = "Visible";
                          //  document.getElementById("conditionofdisabilityvisual").style.display = "Inline";


                            break;
                        }
                    case "2":{
                            document.getElementById("visual").style.visibility = "Visible";
                            document.getElementById("visual").style.display = "Inline";
                            document.getElementById("commonCondition").style.visibility = "Visible";
                            document.getElementById("commonCondition").style.display = "Inline";

                            break;
                        }

                    case "3":{
                            document.getElementById("hearing").style.visibility = "Visible";
                            document.getElementById("hearing").style.display = "Inline";

                            break;
                        }
                    case "4":{
                            document.getElementById("commonCondition").style.visibility = "Visible";
                            document.getElementById("commonCondition").style.display = "Inline";


                            break;
                        }
                    case "5":{
                            alert("Physical reqirements are not suggested for Mentalillness disability");
                            document.getElementById("hideTB").style.visibility="InVisible";
                            return false;

                            break;
                        }
                    case "6":{
                            document.getElementById("locomotor").style.visibility = "Visible";
                            document.getElementById("locomotor").style.display = "Inline";
                            document.getElementById("visual").style.visibility = "Visible";
                            document.getElementById("visual").style.display = "Inline";
                            document.getElementById("hearing").style.visibility = "Visible";
                            document.getElementById("hearing").style.display = "Inline";
                            document.getElementById("commonCondition").style.visibility = "Visible";
                            document.getElementById("commonCondition").style.display = "Inline";

                            break;
                        }
                    case "7":{
                            break;
                        }

                }

            }

         function selectdata(valu){
              var theDay =document.getElementById("disabilityId").value ;
               // alert("disbilityid==="+theDay);
          var se=document.getElementsByName("reassessment");var coun=0;
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               coun++;
                            }}
                           // alert("enter select======"+valu+se[0].value+se[1].value);
                            if(valu!=null && valu==1){
                               // alert("enter rad======="+document.getElementById("next1").style);
                      document.getElementById("next1").style.visibility = "Visible";
                      document.getElementById("next1").style.display = "Inline";
                      document.getElementById("print").style.display = "none"
                      document.getElementById("nex").style.visibility = "Visible";
                      document.getElementById("nex").style.display = "Inline";

                       //  displayAppropriate(thisForm);
                            }else if(valu!=null && valu==2){

                               //  alert("enter rad=======");
                      document.getElementById("print").style.visibility = "Visible";
                      document.getElementById("print").style.display = "Inline";
                      document.getElementById("next1").style.display = "none"
                      document.getElementById("nex").style.display = "none"

                       // displayAppropriate(thisForm);
                            }


             /*   switch (theDay)

                {
                    case "1":{
                            document.getElementById("locomotor").style.visibility = "Visible";
                            document.getElementById("locomotor").style.display = "Inline";
                            document.getElementById("commonCondition").style.visibility = "Visible";
                            document.getElementById("commonCondition").style.display = "Inline";
                          //  document.getElementById("conditionofdisabilityvisual").style.visibility = "Visible";
                          //  document.getElementById("conditionofdisabilityvisual").style.display = "Inline";


                            break;
                        }
                    case "2":{
                            document.getElementById("visual").style.visibility = "Visible";
                            document.getElementById("visual").style.display = "Inline";
                            document.getElementById("commonCondition").style.visibility = "Visible";
                            document.getElementById("commonCondition").style.display = "Inline";

                            break;
                        }

                    case "3":{
                            document.getElementById("hearing").style.visibility = "Visible";
                            document.getElementById("hearing").style.display = "Inline";

                            break;
                        }
                    case "4":{
                            document.getElementById("commonCondition").style.visibility = "Visible";
                            document.getElementById("commonCondition").style.display = "Inline";


                            break;
                        }
                    case "5":{
                            alert("Physical reqirements are not suggested for Mentalillness disability");
                            document.getElementById("hideTB").style.visibility="InVisible";
                            return false;

                            break;
                        }
                    case "6":{
                            document.getElementById("locomotor").style.visibility = "Visible";
                            document.getElementById("locomotor").style.display = "Inline";
                            document.getElementById("visual").style.visibility = "Visible";
                            document.getElementById("visual").style.display = "Inline";
                            document.getElementById("hearing").style.visibility = "Visible";
                            document.getElementById("hearing").style.display = "Inline";
                            document.getElementById("commonCondition").style.visibility = "Visible";
                            document.getElementById("commonCondition").style.display = "Inline";

                            break;
                        }
                    case "7":{
                            break;
                        }

                }*/


         }



               /*     if(count>=1){
                            if(se[0].checked=true)
                    {
                        alert(se[0].checked.value);
                        alert("enter yes=======");
                      document.getElementById("next1").style.visibility = "Visible";
                           // document.getElementById("next1").style.display = "Inline";
                        }
                else if(se[1].checked=true)
                    {
                alert(se[1].checked.value);
                    alert("enter no========");
                      document.getElementById("print").style.visibility = "Visible";
                           // document.getElementById("print").style.display = "Inline";
}}
*/





            function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }

            function ristrictZero() {
                if(document.forms[0].elements['rationCardSlNo'].value=="0") {
                    document.forms[0].elements['rationCardSlNo'].value="";
                }

            }

            function checkRationCard(val) {

                if(val.substring(0,3)!="WAP" && val.substring(0,3)!="PAP" && val.substring(0,3)!="AAY" &&
                    val.substring(0,3)!="AAP" && val.substring(0,3)!="YAP" && val.substring(0,3)!="wap" &&
                    val.substring(0,3)!="pap" && val.substring(0,3)!="aay" &&  val.substring(0,3)!="aap" &&
                    val.substring(0,3)!="yap" && val.substring(0,3)!="TAP" && val.substring(0,3)!="RAP" &&
                    val.substring(0,3)!="tap" && val.substring(0,3)!="rap" && val.substring(0,3)!="WAD" && val.substring(0,3)!="wad") {
                    alert("Please Enter Valid RationCard Number");
                    document.forms[0].elements['rationCardNo'].value="";
                    document.forms[0].elements['rationCardNo'].focus();
                    return false;
                }
            }
 var exts = "jpg|gif|png|bmp|mpg|mpeg";
            function checkExt(value){
                if(value=="")return true;
                var re = new RegExp("^.+\.("+exts+")$","i");
                if(!re.test(value))
                {
                    alert("Extension not allowed for file: \"" + value + "\"\nOnly these extensions are allowed: "+exts.replace(/\|/g,',')+" \n\n");
                    var file = document.getElementById("photo");
                    file.form.reset();

                    //document.getElementById("photo").value="";
                    return false;
                }
                return true;
            }
        </script>
    </head>




    <body onload="displayAppropriate(this.value);">


 <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">

    <html:form action="/appleteAuthorityPersonalDetailsPrint" enctype="multipart/form-data" >
        <html:hidden property="mode"/>

        <html:hidden property="disabilityId" />
        <logic:notEmpty name="personDetails">
            <div>
            <table align="center" cellpadding="0" cellspacing="0" border="1" class="innerTable" width="100%">
                <tr>
                    <td colspan="6" align="center" class="heading">
                        Physical Requirements <br/>


                    </td>
                </tr>
                <logic:iterate id="row" name="personDetails">
                    <%if (i == 0) {%>
                    <tr>
                        <td class="label">
                            Pension Code :
                        </td>
                        <td class="label">
                            ${row.pension_id}
                        </td>
                        <td class="label">
                            Name :
                        </td>
                        <td class="label">
                            ${row.name}
                        </td>
                        <td rowspan="5" class="label" align="center" style="border-style:dotted;background-color:inherit " colspan="2">
                            <img src="./DisabilityUITG/uploadedphotos/${row.district_name}/${row.sadaremCode}.JPG" height="150" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/><br/>
                            <b> ${row.sadaremCode}</b>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">
                            Relation Name :
                        </td>
                        <td class="label">
                            ${row.relation_name}
                        </td>
                        <td class="label">
                            Gender :
                        </td>
                        <td class="label">
                            ${row.gender}
                        </td>
                    </tr>
                    <tr>
                        <td class="label">
                            Age :
                        </td>
                        <td class="label">
                            ${row.age}
                        </td>
                        <td class="label">
                            Religion :
                        </td>
                        <td class="label">
                            ${row.religion}
                        </td>
                    </tr>
                    <tr>
                        <td class="label">
                            Caste :
                        </td>
                        <td class="label">
                            ${row.caste}
                        </td>
                        <td class="label">
                            DOB :
                        </td>
                        <td class="label">
                            ${row.dob}
                        </td>
                    </tr>
                    <tr>
                         <td class="label" colspan="1">
                            Type of Certificate :
                        </td>
                        <td class="label" >
                             ${row.type}
                        </td>


                        <td class="label">
                            Identification Marks :
                        </td>
                        <td class="label">
                            ${row.moleOne}<br/>${row.moleTwo}
                        </td>
                    </tr>
                    <tr>
                        <td class="label" colspan="1">
                            RationCard No :&nbsp;
                        </td>
                        <td class="label">${row.rationcard}</td>

                        <td class="label" colspan="1">
                            RationCard SlNo :  &nbsp;
                        </td>
                        <td class="label">${row.rationcard_slno}</td>
                        <td class="label" colspan="1">
                            District :
                        </td>
                        <td class="label">${row.district_name}</td>
                    </tr>
                    <tr>
                        <td class="label">
                            Mandal :
                        </td>
                        <td class="label">
                            ${row.mandal_name}
                        </td>

                        <td class="label">
                            Village :
                        </td>
                        <td class="label">
                            ${row.village_name}
                        </td>
                        <td class="label">
                            Habitation :
                        </td>
                        <td class="label">
                            ${row.habitation_name}
                        </td>
                        <html:hidden property="printProperty" value="${row.personstatusPrint}"/>
                    </tr>

                   <!-- <tr><td class="label" colspan="6">Medical Board Details:</td></tr>

                     <tr>
                        <td class="label" colspan="2">
                            HospitalName :
                        </td>
                        <td class="label" >

                        </td>

                        <td class="label" colspan="2">
                            Venue :
                        </td>
                        <td class="label" >

                        </td>

                    </tr>-->
                     <tr>
                         <td class="label" colspan="1">
                            Venue Details :
                        </td>
                        <td class="label" >
                             ${row.hname},${row.hadd}
                        </td>

                        <td class="label" colspan="1">
                            Percentage Of Disability :
                        </td>
                        <td class="label" >
                             ${row.per}
                        </td>

                        <td class="label">
                            Disability :
                        </td>
                        <td class="label">
                            ${row.disability}
                        </td>


                    </tr><tr>
                        <td class="label">
                            Doctor Name :
                        </td>
                        <td class="label">
                            ${row.fname}
                        </td>

                        <td class="label">
                            Doctor RegNo :
                        </td>
                        <td class="label">
                            ${row.freg}
                        </td>
                        <td class="label">
                            Doctor Designation :
                        </td>
                        <td class="label">
                            ${row.fdes}
                        </td>

                    </tr>


                    <%i++;%>
                    <%}%>



            <br/>

           </logic:iterate>
            </table></div>

                        <div id="locomotor" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="0" cellpadding="0" border="1" class="innerTable" width="100%">
                    <tr>
                        <td class="label">F-can perform work by manipulating with fingers</td>
                        <td colspan="4" class="label"><html:radio  property="f_can" value ="1" disabled="true">Yes</html:radio><html:radio  property="f_can" value ="0" disabled="true">No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">PP-can perform work by pulling and pushing</td>
                        <td colspan="4" class="label"><html:radio  property="pp_can" value ="1" disabled="true">Yes</html:radio><html:radio  property="pp_can" value ="0" disabled="true">No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">L-can perform work by lifting</td>
                        <td colspan="4" class="label"><html:radio  property="l_can" value ="1" disabled="true">Yes</html:radio><html:radio  property="l_can" value ="0" disabled="true">No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">KC-can perform work by kneeling and crouching</td>
                        <td colspan="4" class="label"><html:radio  property="kc_can" value ="1"disabled="true" >Yes</html:radio><html:radio  property="kc_can" value ="0" disabled="true">No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">B-can perform work by bending</td>
                        <td colspan="4"  class="label"><html:radio  property="b_can" value ="1"disabled="true" >Yes</html:radio><html:radio  property="b_can" value ="0" disabled="true">No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">S-can perform work by sitting</td>
                        <td colspan="4" class="label"><html:radio  property="s_can" value ="1" disabled="true">Yes</html:radio><html:radio  property="s_can" value ="0" disabled="true">No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">ST-can perform work by standing</td>
                        <td colspan="4" class="label"><html:radio  property="st_can" value ="1" disabled="true">Yes</html:radio><html:radio  property="st_can" value ="0" disabled="true">No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">W-can perform work by walking</td>
                        <td colspan="4" class="label"><html:radio  property="w_can" value ="1"disabled="true" >Yes</html:radio><html:radio  property="w_can" value ="0" disabled="true">No</html:radio></td>

                </table>
            </div>

            <div id="visual" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="0" cellpadding="0" border="1" class="innerTable" width="100%">

                    <tr>
                        <td class="label"  >SE-can perform work by seeing</td>
                        <td class="label" colspan="4" ><html:radio  property="se_can" value ="1" disabled="true">Yes</html:radio><html:radio  property="se_can" value ="0" disabled="true">No</html:radio></td>
                    </tr>

                </table>
            </div>
            <div id="hearing" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="0" cellpadding="0" border="1" class="innerTable" width="100%">

                    <tr>
                        <td class="label"  >H-can perform work by hearing/speaking</td>
                        <td  class="label" colspan="4"><html:radio  property="h_can" value ="1" disabled="true">Yes</html:radio><html:radio  property="h_can" value ="0" disabled="true">No</html:radio></td>
                    </tr>
                </table>
            </div>
            <div id="commonCondition" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="0" cellpadding="0" border="1" class="innerTable" width="100%">
                    <tr>
                        <td class="label" >RW-can perform work by reading and writing</td>
                        <td class="label" colspan="4"><html:radio  property="rw_can" value ="1" disabled="true">Yes</html:radio><html:radio  property="rw_can" value ="0" disabled="true">No</html:radio></td>
                    </tr>
                </table>
            </div>


                    <div><table border="1" align="center" cellpadding="0" cellspacing="0" class="innerTable" width="100%">
                        <tr><td class="label"><font color="red"><b>Need to be ReAssess</b></font>
                       <html:radio  property="reassessment" value ="1"
                                     onclick="selectdata(this.value);">Yes</html:radio>
                                     <html:radio  property="reassessment" value ="2"
                                                  onclick="selectdata(this.value);" >No</html:radio></td>
                        <td>  <div  id="nex" style="visibility:hidden;display:none">

                     <table align="left" class="label"><tr><td>Reason for Reassessment:</td>
                             <td>
                         <html:textarea property="reason"  rows="2" />
                    </td>
                        </tr></table></div></td>
                        <td class="label">
                            <a href="showCalculationsAction.do?print=certificate&flag=true&decisionparameter=true&personCodeFlag=<%=per %>" target="_blank" ><font size="3"><B>Percentage Calculation</B></font></a>
                        </td>



                        </tr></table></div>

              <div id="non-printable">
        <div id="print" style="visibility:hidden;display:none" >
                 <table align="center" cellpadding="0" cellspacing="0" border="0">
                <tr>
                    <td>
                        <html:button property="butone" value="Print " onclick="window.print();"/>

                    </td>
                    <td>&nbsp;
                        <html:button property="butone" value="Back" onclick="back();"/>

                    </td>
                </tr>
            </table></div>


                         <div id="next1" style="visibility:hidden;display:none">
                 <table align="center" cellpadding="0" cellspacing="0" border="0">


                     <tr align="center"> <td><table align="center"><tr><td>
                        <html:button property="but" value="Next" onclick="goForPartB();"/>&nbsp;&nbsp;
                    </td>

                    <td>
                        <html:button property="butone" value="Print" onclick="window.print();"/>
                    </td>

                                 <td>&nbsp;
                        <html:button property="butone" value="Back" onclick="back();"/>

                    </td></tr></table></td>


                </tr>
            </table>

            </div></div>
        </logic:notEmpty>
    </html:form>
</body>
</html:html>