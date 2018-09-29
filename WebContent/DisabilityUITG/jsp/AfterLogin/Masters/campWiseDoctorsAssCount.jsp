<%--
    Document   : campWiseDoctorsAssCount
    Created on : Jul 17, 2012, 11:50:32 AM
    Author     : 693461
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%@page  import="java.util.ArrayList"%>
<%@page  import="java.util.Map"%>

<%
            int i = 1;
            String doctorName = "";
            String docDesig = "";
            ArrayList nextList = new ArrayList();
            if (request.getAttribute("doctorName") != null) {
                doctorName = request.getAttribute("doctorName").toString();
            }
            if (request.getAttribute("docDesig") != null) {
                docDesig = request.getAttribute("docDesig").toString();
            }
            if (request.getAttribute("nextList") != null) {
                nextList = (ArrayList) request.getAttribute("nextList");
                session.setAttribute("sessionList", nextList);

            }


%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>:::SADAREM:::</title>
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <script>
            function OpenWindow(url)
            {
                var features="width=800,height=500,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes,top=10,left=50,resizable=yes,titlebar=no,directories=no";
                popup1 =window.open(url,"_blank",features);
            }
        </script>
        <script type="">

            function resetValues(){
                document.forms[0].docreg.value="";
                document.forms[0].docname.value="";
                document.forms[0].docdesig.value="";
                document.forms[0].count.value="";

            }
            function getAssessedPwdDoctorsCount(){
                var checkCount = 0;
                var sCheck = document.getElementsByName("countOfPwd");
               
                for(var i=0;i<sCheck.length;i++){
                    if(document.forms[0].elements['countOfPwd'].value!=""){
                        checkCount++;
                    }
                }

                if(checkCount==0){
                    alert("Enter atleast one AssesedCountOfPwD's");
                    return false;
                }else
                {
                    document.forms[0].mode.value = "getDoctorsCountDetails";
                    document.forms[0].submit();

                }
            }
            function submitDate(){              
                var d = document.forms[0];
                var toDate=d.campDate.value;
                var selYY=toDate.substr(6,4);
                var selMM=toDate.substr(3,2);
                var selDD=toDate.substr(0,2);
                var today=new Date();
               
                var currentYY=today.getFullYear();
                var currentMM= today.getMonth()+1;
                var currentDD= today.getDate();
                //var ss=ddtoday+"/"+mmtoday+"/"+yytoday;
                var flag=0;
                if(selYY>currentYY){
                    flag=1;
                }
                if(selYY==currentYY && selMM>currentMM){
                    flag=1;
                }
                if(selYY==currentYY && selMM==currentMM && selDD>currentDD){
                    flag=1;
                }
                if(  flag){
                    alert("Assessment Date should not be future date");
                    d.campDate.value="";
                    d.campDate.focus();
                    return false;
                }

                if(d.campDate.value==""){
                    alert("Please Select Assessment Date");
                    d.campDate.focus();
                    return false;
                }else {
                    d.mode.value="submitDate";
                    d.submit();
                }
                
            }
            function getDoctorNameAndDesg(){
               
                var d = document.forms[0];
                if(d.docreg.value==""){
                    alert("Please Enter Doctor Registration Number");
                    d.docreg.focus();
                    return false;
                }else {
            <%
                        int number = 0;
                        for (int y = 0; y < nextList.size(); y++) {
                            Map m = (Map) nextList.get(y);
                            number = Integer.parseInt(m.get("doctorRegNumber").toString());
            %>
                      
                        if(d.docreg.value=='<%=number%>'){
                            alert("Given Doctor Registration Number already exist.Please insert another Doctor number");
                            return false;
                        }
            <%
                        }
            %>
                        d.mode.value="getDoctorNameAndDesg";
                        d.submit();
                        return true;
                    }

                }
                function onlyNumbers(evt)
                {
                    var e = event || evt; // for trans-browser compatibility
                    var charCode = e.which || e.keyCode;

                    if (charCode > 31 && (charCode < 48 || charCode > 57))
                        return false;

                    return true;

                }
                function insertDocotorCount(){
                  
                    var d = document.forms[0];
                    var count = document.getElementsByName('count');
                    var checkCount = 0;
                    for(var i =0; i<count.length; i++){
                        if(count[i].value!=""){
                            checkCount++;
                        }
                    }
                    if(d.docreg.value==""){
                        alert("Please Enter Doctor Registration Number");
                        d.docreg.focus();
                        return false;
                    }if(d.docname.value=="" || d.docdesig.value==""){
                        alert("Please Enter Valid Doctor Registration Number");
                        d.docreg.focus();
                        return false;
                    } if(checkCount==0){
                        alert("Enter atleast one AssesedCountOfPwD's");
                        return false;
                    }
                    else
                    {
                        var slcBx1 = document.getElementById("1");
                        document.forms[0].disName.value = slcBx1.options[slcBx1.selectedIndex].text;
                        d.mode.value="insertDocotorCount";
                        d.submit();
                    }
                }
                function allDoctorsInsert(){
                    var d = document.forms[0];
                    d.mode.value="allDoctorsInsert";
                    d.submit();
                }
        </script>
    </head>
    <body>
        <html:form action="/campWiseDoctorsCount">
            <html:hidden property="mode"/>   
            <logic:present name="msg">
                <table ><tr><td style="font-size: 12;color: red;font-weight: bold">${msg}</td></tr></table>
            </logic:present>

            <logic:present name="pwdDoctorCountDetails">
                <center><font color="blue"><bean:write name="pwdDoctorCountDetails"/></font></center>
            </logic:present>
         
                <table cellpadding="0" cellspacing="1" width="50%" border="0" class="inputform" align="center">
                <tr>
                    <th colspan="2">
                        CampWise Assessed Doctors Details
                    </th>
                </tr>
                <tr>
                    <td >Assessment Date<font color="red"><b>*</b></font></td>
                    <td  >
                        <html:text property="campDate" readonly="true" size="12"/>
                        <a  href="javascript:show_calendar('forms[0].campDate');"
                            onmouseover="window.status='Date Picker';return true;"
                            onmouseout="window.status='';return true;">
                            <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                    </td></tr><tr>
                    <th colspan="2">
                        <html:button property="but" value="Submit" onclick="submitDate();"/>
                    </th>
                </tr>

            </table>
            <br/>
            <logic:present name="disabilityList">
                <table  align="center" cellspacing="1" border="0" cellpadding="0" width="90%" class="inputform">
                    <tr>
                        <th >
                            Type of Disability
                        </th>
                        <th >
                            Doctor Registration Number
                        </th>

                        <th >
                            Doctor Name
                        </th>
                        <th >
                            Doctor Designation
                        </th>
                        <th >
                            AssesedCountOfPwD's
                        </th>
                    </tr>
                    <tr>
                        <td >
                            <html:select styleId="1" property="disabilityid"  onchange="resetValues();" style="width:180px">
                                <html:optionsCollection property="disabilityList" label="disName" value="disid"  />
                            </html:select>
                            <html:hidden property="disName" />
                        </td>
                        <td>
                            <html:text property="docreg" size="15"  onchange="return getDoctorNameAndDesg(this);" onkeypress="return onlyNumbers(event);" maxlength="10"/>

                        </td>

                        <td>
                       

                            <html:text property="docname"  readonly="true" value="<%=doctorName%>"/>
                        </td>

                        <td>
                           
                            <html:text property="docdesig"  readonly="true" value="<%=docDesig%>"/>
                        </td>

                        <td>
                           
                            <html:text property="count"   maxlength="10" onkeypress="return onlyNumbers(event);"/>
                        </td>
                    </tr>

                    <tr>
                        <td  colspan="5">
                            <a href="javascript:OpenWindow('campWiseDoctorsCount.do?mode=addDoctor&campDate=<%=request.getParameter("campDate")%>');" style="text-decoration: none; font-weight: bold; font-size: 15px; color: blue;">Add New Doctor</a>
                        </td>
                    </tr>
               
                    <tr>
                        <th colspan="5" >
                            <html:button property="btn" value="ADD" onclick="insertDocotorCount()"/>
                        </th>
                    </tr>
                </table>
                <br/>
            </logic:present>
            <logic:present name="nextList">
                <table  align="center" cellspacing="1" border="0" cellpadding="0" width="90%" class="inputform">
                    <tr>
                        <th >
                            Doctor Name
                        </th>
                        <th >
                            Doctor Registration Number
                        </th>
                        <th>
                            Doctor Designation
                        </th>
                        <th>
                            Type of Disability
                        </th>
                        <th >
                            AssesedCountOfPwD's
                        </th>
                    </tr>
                    <logic:iterate name="nextList" id="row" >
                        <tr>
                            <td  style="text-align: center">
                                ${row.doctorName}
                            </td>
                            <td   style="text-align: center">
                                ${row.doctorRegNumber}
                                <html:hidden property="doctorReg" value="${row.doctorRegNumber}"/>
                            </td>
                            <td  style="text-align: center">
                                ${row.desig}
                            </td>
                            <td style="text-align: center" >
                                ${row.disability}
                                <html:hidden property="disabilityId" value="${row.disabilityId}"/>
                            </td>
                            <td  style="text-align: center">
                                ${row.count}
                            </td>
                        </tr>
                    </logic:iterate>
                    <tr>
                        <th colspan="5">
                            <html:button property="btn" value="Submit" onclick="allDoctorsInsert()"/>
                        </th>
                    </tr>

                </table>

            </logic:present>

        </html:form>
    </body>
</html>
