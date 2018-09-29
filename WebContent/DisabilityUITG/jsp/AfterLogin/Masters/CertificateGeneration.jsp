<%-- 
    Document   : CertificateGeneration
    Created on : Mar 5, 2013, 12:53:37 PM
    Author     : 728056
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
            String propertyName = null, styleId = null;
            int i = 1;
            int j = 1;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/DateValidation.js"></script>
        <title>SADAREM</title>

        <script>
            function textCounter(field,cntfield,maxlimit) {
                if (field.value.length > maxlimit){  // if too long...trim it!
                    field.value = field.value.substring(0, maxlimit);
                    // otherwise, update 'characters left' counter
                }
                else{
                    cntfield.value = maxlimit - field.value.length;
                }

            }
            function space(evt,thisvalue)
            {
                var number = thisvalue.value;
                if(number.length < 1 ){
                    if(evt.keyCode == 32 || evt.keyCode == 39) {
                        return false;
                    }
                }else if(evt.keyCode == 39){
                    return false;
                }
                return true;
            }
            function chkDOBDate(obj){
                if (isDate(obj.value)==false){
                    obj.value = "";
                }else{
                    var currentDate = new Date();
                    var currentDay = currentDate.getDate();
                    var currentMonth = currentDate.getMonth()+1;
                    var currentYear = currentDate.getFullYear();
                    var enteredDate=obj.value.split("/");
                    var  enteredDay=enteredDate[0];
                    var  enteredMonth=enteredDate[1];
                    var  enteredYear=enteredDate[2];
                    if(enteredYear==currentYear){
                        if(enteredMonth<=currentMonth && enteredYear<=currentYear){
                            if(enteredDay<=currentDay &&  enteredMonth<=currentMonth && enteredYear<=currentYear){
                                return true;
                            }else{
                                alert("Future Date Not Allowed");
                                obj.value = "";
                            }
                        }else{
                            alert("Future Date Not Allowed");
                            obj.value = "";
                        }
                    }else if(enteredYear<currentYear){
                        return true;
                    }else if(enteredYear>currentYear){
                        alert("Future Date Not Allowed");
                        obj.value = "";
                    }
                }
            }
            function checkAllBoxes(element){
                var CheckBoxArray = document.getElementsByName("checkbox");
                if(element.checked == true){
                    for(var i=0;i<CheckBoxArray.length;i++){
                        CheckBoxArray[i].checked = true;

                    }
                }
                else{
                    for(var i=0;i<CheckBoxArray.length;i++){
                        CheckBoxArray[i].checked = false;
                    }
                }
            }
            function checkValues(){
                var checkCount = 0;
                //var selectedList=new Array();
                var sCheck = document.getElementsByName("checkbox");
                for(var i=0;i<sCheck.length;i++){
                    if(sCheck[i].checked){
                        if(document.getElementById(i+"#1")!=null && document.getElementById(i+"#1").value==""){
                            alert("Please Enter Remarks");
                            document.getElementById(i+"#1").focus();
                            return false;
                        } if(document.getElementById(i+"#2")!=null && document.getElementById(i+"#2").value==""){
                            alert("Please Enter Date");
                            document.getElementById(i+"#2").focus();
                            return false;
                        }
                        //selectedList.add(sCheck[i].value);
                        checkCount++;

                    }
                }
            <%-- alert("selectedList"+selectedList.length);--%>
         if(checkCount==0){
             alert("Select Atleast One checkbox");
             return false;
         }else
         {
             
                 document.forms[0].mode.value = "insertDetails";
             document.forms[0].submit();

         }
     }


     function villageNames(){

         document.forms[0].village.value = "0";
         document.forms[0].mode.value = "getVillageNames";
         document.forms[0].submit();
     }
     function HabitationNames(){
         document.forms[0].habitation.value = "0";
         document.forms[0].mode.value = "getHabitationNames";
         document.forms[0].submit();
     }
     function getDetails(){
         document.forms[0].mode.value = "getDetails";
         document.forms[0].submit();
     }
        </script>
    </head>

    <body>
        <html:form action="certificateGeneration">
            <html:hidden property="mode"/>
            <logic:present name="msg1">
                <center><font color="red" size="2"><b><bean:write name="msg1"/></b></font> </center>
            </logic:present>
            <logic:present name="msg2">
                <center><font color="green" size="3"><b><bean:write name="msg2"/></b></font> </center>
            </logic:present>
            <table border="0" cellspacing="0" cellpadding="0" align="center" width="100%">
                <tr>
                    <td align="center">
                        <table  align="center" cellspacing="1" cellpadding="5" class="inputform" width="90%">
                            <tr  align="center">
                                <th colspan="15">District Wise Certificate Issue Updation</th>
                            </tr>
                           
                            <tr>
                                <td  valign="middle" width="8%">Mandal</td>
                                <td><html:select styleId="2" property="mandal" onchange="villageNames();" style="height:25px;">
                                        <html:option value="0">ALL</html:option>
                                        <html:optionsCollection property="mandalList" label="mandalname" value="mandal"/>
                                    </html:select>
                                </td>

                                <td  valign="middle" width="8%">Village</td>

                                <td><html:select styleId="3" property="village"  onchange="HabitationNames();" style="height:25px;">
                                        <html:option value="0">ALL</html:option>
                                        <html:optionsCollection property="villageList" label="villagename" value="village"/>
                                    </html:select></td>

                                <td  valign="middle" width="8%">Habitation</td>
                                <td><html:select styleId="3" property="habitation"  style="height:25px;">
                                        <html:option value="0">ALL</html:option>
                                        <html:optionsCollection property="habitationList" label="habitationname" value="habitation"/>
                                    </html:select></td>
                                
                            </tr>
                           <tr> <th  colspan="6">
                                <html:button  property="sub" value="Submit" onclick="getDetails();"/>
                                </th></tr>
                        </table>
                    </td>
                </tr>
                <tr  align="center">
                    <td align="center" colspan="6" height="5px">

                        &nbsp;
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <logic:notEmpty name="msg">
                            <div style="overflow:auto;height:300px;width:860px" align="center">
                                <table border="0" cellspacing="1" cellpadding="0" align="center" width="90%" class="inputform">
                                    <tr>
                                        <th  align="center">
                                            Sno
                                        </th>
                                        <th  align="center">
                                            Pension ID
                                        </th>
                                        <th  align="center">
                                            SADAREM ID
                                        </th>
                                        <th  align="center">
                                            Name
                                        </th>
                                        <th  align="center">
                                            Relation Name
                                        </th>
                                        <th  align="center">
                                            Age
                                        </th>
                                        <th  align="center">
                                            Qualification
                                        </th>
                                        <th  align="center">
                                            Type Disabilty
                                        </th>
                                        <th  align="center">
                                            Disability Percentage
                                        </th>
                                        <th  align="center">
                                            Contact number
                                        </th>
                                        <th  align="center">
                                            PWD Status
                                        </th>
                                        <th  align="center">
                                            Assessment Status
                                        </th>
                                        <th  align="center">
                                            PensionPhase
                                        </th>
                                        <th  align="center">
                                            Certificate
                                        </th>

                                        <th  align="center">
                                            Remarks
                                        </th>
                                        <th  align="center">
                                            Date<br/>
                                            (DD/MM/YYYY)
                                        </th>
                                        <th >
                                            All<html:checkbox property="checkbox" styleId="checkbox"  onclick="checkAllBoxes(this)"  />
                                        </th>

                                    </tr>
                                    <logic:iterate name="msg" id="row">
                                        <html:hidden property="dynaProperty(noOfRows)" name="CertificateGenerationForm" styleId="noOfRows" />
                                        <tr>
                                            <td  align="center">
                                                <%=i++%> .
                                            </td>
                                            <td >
                                                ${row.pensioncard_no}
                                            </td>
                                            <td >
                                                ${row.person_code}
                                            </td>
                                            <td >
                                                ${row.name}
                                            </td>
                                            <td >
                                                ${row.relation_name}
                                            </td>
                                            <td   style="text-align: center">
                                                ${row.age}
                                            </td>
                                            <td  >
                                                ${row.qly}
                                            </td>
                                            <td >
                                                ${row.disability}
                                            </td>
                                            <td   style="text-align: center">
                                                ${row.percentage}
                                            </td>
                                            <td  style="text-align: center">
                                                ${row.mobile}
                                            </td>
                                            <td   >
                                                ${row.personStatus}
                                            </td>
                                            <td  align="center">
                                                ${row.assesmentStatus}
                                            </td>
                                            <td  align="center">
                                                ${row.pensionPhase}
                                            </td>

                                            <td  align="center">
                                                <a href="javascript:void(0);" onclick ="window.open('searchForCertificate.do?searchCertificate=searchCertificate&print=certificate&personcode=${row.person_code}&display=certificatejsp', 'Ratting','resizable=yes, scrollbars=yes,')">View</a>
                                            </td>

                                            <td >
                                                <%
                                                            propertyName = "dynaProperty(" + j + "#1)";
                                                            styleId = j + "#1";
                                                %>

                                                <html:textarea property="<%=propertyName%>"  styleId="<%=styleId%>"
                                                               style="width:165px;height:50px;font-size:14px;"
                                                                onkeypress="return space(event,this)" />

                                                <br><input readonly type="hidden" name="mes" size="4" maxlength="4" value="1000" style="width:40px"/>
                                            </td>

                                            <td align="center">
                                                <%
                                                            propertyName = "dynaProperty(" + j + "#2)";
                                                            styleId = j + "#2";
                                                %>

                                                <input type="text" name="<%=propertyName%>"  id="<%=styleId%>" maxlength="10" onblur="return chkDOBDate(this);" onkeypress="return space(event,this)" style="width:80px"/>
                                            </td>
                                            <td  >
                                                <html:checkbox property="checkbox" styleId="checkbox{i}" value="${row.pensioncard_no}!${row.person_code}"/>
                                            </td>
                                        </tr>
                                        <%j++;%>
                                    </logic:iterate>
                                </table>

                                <tr>
                                    <td align="center">
                                        <table border="0" cellspacing="0" cellpadding="0" align="center" class="inputform" width="90%">
                                            <tr>
                                                <th align="center">
                                                   <html:button  property="sub1" value="Submit" onclick="checkValues();"/>
                                                </th>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                    </logic:notEmpty>
                        </div>
                    </td>
                </tr>
                <%--<tr>
                    <td align="center">
                        <table border="0" cellspacing="0" cellpadding="0" align="center" width="100%">
                            <tr>
                                <td align="center">
                                    <html:button  property="sub1" value="Submit" onclick="checkValues();"/>
                                </td>
                            </tr>
                        </table>--%>
                <%--</td>
            </tr>--%>
            </table>
        </html:form>
    </body>
</html>
