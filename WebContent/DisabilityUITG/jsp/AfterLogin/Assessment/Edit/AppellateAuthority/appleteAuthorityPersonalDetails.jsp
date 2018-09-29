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
<%@page import="java.util.HashMap" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO,com.tcs.sadarem.util.CommonUtility" %>
 <%try{ %>
<%
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
            int i = 0;
            int j = 0;
            boolean flag = true;
            if (request.getAttribute("personDetails") != null) {
                ArrayList<HashMap> requestedList = (ArrayList<HashMap>) request.getAttribute("personDetails");
                for (HashMap map : requestedList) {

                    if (map != null && map.get("RDFLAG") != null && (CommonUtility.checkNullObj(map.get("RDFLAG")).equalsIgnoreCase("Rdcallcenter") ||
                    		CommonUtility.checkNullObj(map.get("PHENSIONPHASE")).equals("ADPIII"))) {
                        flag = false;
                    }
                }
            }

%>

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script>
            function goForPartB(thisform) {
            <%if (flag) {%>
                    if(document.forms[0].elements['rationCardNo'].value=="") {
                        alert("Please Enter the RationCard Number");
                        document.forms[0].elements['rationCardNo'].value="";
                        document.forms[0].elements['rationCardNo'].focus();
                        return false;
                    }else if(document.forms[0].elements['rationCardSlNo'].value=="") {
                        alert("Please Enter the RationCard Serial Number");
                        document.forms[0].elements['rationCardSlNo'].value="";
                        document.forms[0].elements['rationCardSlNo'].focus();
                        return false;
                    }else if(document.forms[0].elements['rationCardNo'].value.length < "15") {
                        alert("Please Enter the Valid RationCard Number");
                        document.forms[0].elements['rationCardNo'].value="";
                        document.forms[0].elements['rationCardNo'].focus();
                        return false;
                    }
            <%}%>
                    if (thisform.getAttribute('submitted'))
                        return false;
                    thisform.setAttribute('submitted','true');
                    document.getElementById('nextButton').disabled = true;

            <%if (request.getAttribute("selectedValue") != null) {%>
                    document.getElementById("selectFlow").value='<%=request.getAttribute("selectedValue").toString()%>';
            <%}%>
                    document.forms[0].mode.value="goForPartB";
                    document.forms[0].submit();
                }
        </script>

        <script language="JavaScript">
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
                    val.substring(0,3)!="yap" && val.substring(0,3)!="RAP" && val.substring(0,3)!="rap"
                    && val.substring(0,3)!="TAP" && val.substring(0,3)!="tap"
                    && val.substring(0,3)!="WAD" && val.substring(0,3)!="wad") {
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
    <body>
        <html:form action="/appleteAuthorityPersonalDetails" enctype="multipart/form-data" >
            <html:hidden property="mode"/>
            <input type="hidden" name="selectFlow" id="selectFlow"/>
            <logic:notEmpty name="personDetails">
                <table  align="center"  cellspacing="1" cellpadding="0" class="inputform" width="90%">
                    <tr><th colspan="6">Second Time Re-Assessment</th>
                        <logic:iterate id="row" name="personDetails">
                            <%if (i == 0) {%>
                        <tr>
                            <td>
                                Pension Code :
                            </td>
                            <td >
                                ${row.pension_id}
                            </td>
                            <td >
                                Name :
                            </td>
                            <td >
                                ${row.name}
                            </td>

                            <td rowspan="5"  align="center" style="border-style:dotted;background-color:inherit " colspan="2">
                                <%-- <img src="./DisabilityUITG/uploadedphotos/${row.district_name}/${row.sadaremCode}.JPG" height="150" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/> --%>
                                <img src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=${row.sadaremCode}" height="150" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/>
                                <br/>
                                <b> ${row.sadaremCode}</b>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                Relation Name :
                            </td>
                            <td >
                                ${row.relation_name}
                            </td>
                            <td >
                                Gender :
                            </td>
                            <td >
                                ${row.gender}
                            </td>
                        </tr>
                        <tr>
                            <td >
                                Age :
                            </td>
                            <td >
                                ${row.age}
                            </td>
                            <td >
                                Religion :
                            </td>
                            <td >
                                ${row.religion}
                            </td>

                        </tr>
                        <tr>

                            <td >
                                Caste :
                            </td>
                            <td >
                                ${row.caste}
                            </td>
                            <td >
                                DOB :
                            </td>
                            <td >
                                ${row.dob}
                            </td>
                        </tr>
                        <tr>

                            <td >
                                Disability :
                            </td>
                            <td >
                                ${row.disability}
                            </td>

                            <td >
                                Identification Marks :
                            </td>
                            <td >
                                ${row.moleOne}<br/>${row.moleTwo}
                            </td>
                        </tr>
                        <tr>
                            <td  colspan="1">
                                RationCard No :&nbsp; <%if (flag) {%><font color="red"><b>*</b></font><%}%>
                            </td>
                            <td><html:text property="rationCardNo" value="${row.rationcard}" onchange="checkRationCard(this.value)" maxlength="15" /></td>

                            <td  colspan="1">
                                RationCard SlNo :  &nbsp; <%if (flag) {%><font color="red"><b>*</b></font><%}%>
                            </td>
                            <td><html:text property="rationCardSlNo" size="2" maxlength="1" onkeypress="return onlyNumbers();" value="${row.rationcard_slno}" onkeyup="ristrictZero()"/></td>
                            <td  colspan="1">
                                District :
                            </td>
                            <td >${row.district_name}</td>
                        </tr>
                        <tr>

                            <td >
                                Mandal :
                            </td>
                            <td >
                                ${row.mandal_name}
                            </td>

                            <td >
                                Village :
                            </td>
                            <td >
                                ${row.village_name}
                            </td>
                            <td >
                                Habitation :
                            </td>
                            <td >
                                ${row.habitation_name}
                            </td>

                        </tr>
                        <tr>
                            <td >
                                Upload Photo :

                            </td>
                            <td colspan="6">
                                <html:file property="photoUpload" onchange="checkExt(this.value)" styleId="photo" style="width:300px"/>
                            </td>

                        </tr>
                        <%i++;%>
                        <%}%>
                    </logic:iterate>
             <%--   </table>
                <table align="center" cellpadding="0" cellspacing="0" border="1"  width="90%" class="inputform">--%>
                    <logic:iterate id="row" name="personDetails">
                        <%if (j == 0) {%>
                        <tr align="center">
                            <td colspan="6"  align="center" style="text-align: center">
                                <input type="radio" name="personStatusForAU" style="width:25px;" value="Eligible" ${row.person_statusEli} > Eligible
                           <input type="radio" name="personStatusForAU" style="width:25px;" value="Rejected" ${row.person_statusRej} > Rejected</td>
                           
                        </tr>
                        <%j++;%>
                        <%}%>
                    </logic:iterate>
               
                    <tr>
                        <th colspan="6">
                            <html:button property="but" value="Next >> " onclick="goForPartB(this);" styleId="nextButton"/>
                        </th>
                    </tr>
                </table>
            </logic:notEmpty>
        </html:form>
    </body>
</html:html>

<%}catch(Exception e){e.printStackTrace();}%>