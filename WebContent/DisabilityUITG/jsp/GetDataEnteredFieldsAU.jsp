<%-- 
    Document   : GetDataEnteredFieldsAU
    Created on : Aug 5, 2011, 10:24:26 AM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>


        <% Map<String, List<String>> dataEnteredListMap = (HashMap) request.getAttribute("dataEnteredListMap");

                    List<String> dataEnteredList = dataEnteredListMap.get("mainsectionslist");
                    List<String> locomotorsublist = dataEnteredListMap.get("locomotorsublist");

                    List<String> mentalretardationtests = dataEnteredListMap.get("mentalretardationtests");
                    int tempLocoList = 0;
                    if(null != locomotorsublist){tempLocoList = locomotorsublist.size();}
                    int tempMentalList = 0;
                    if(null != mentalretardationtests){tempMentalList = mentalretardationtests.size();}



                    String personcode = (String) request.getAttribute("personcode");
                    //request.setAttribute("dataEnteredListtemp", dataEnteredList);
        %>

        <script language="JavaScript">
            function generatePDF()
            {
                document.forms[0].reportdisplay.value="pdf";
                document.forms[0].submit();
            }
            function validateCheckBox()
                            {
                                var chks =  <%=dataEnteredList.size()%>;
                                var hasChecked = false;
                                for (var i =1; i <= chks; i++)
                                {
                                    if (document.getElementById(i).checked)
                                    {
                                        hasChecked = true;
                                        generatePDF();
                                        break;
                                    }
                                }
                                if (hasChecked == false)
                                {
                                    alert("Please select at least one.");
                                    return false;
                                }
                                return true;
                            }

            function checkedAll(){
                var maindisabilitysize = <%=dataEnteredList.size()%>;
                if(maindisabilitysize>0){
                    for (var i =1; i <= maindisabilitysize; i++)
                    {
                        if(document.getElementById("checkall").checked){
                            document.getElementById(i).checked = true;
                            var disabilityname = document.getElementById(i).value;
                            if(disabilityname == "Locomotor"){
                                disablesublist();
                            }else if(disabilityname == "MentalRetardation"){
                                disableMRsublist();
                            }
                        }else if(!document.getElementById("checkall").checked){
                            document.getElementById(i).checked = false;
                            var disabilityname = document.getElementById(i).value;
                            if(disabilityname == "Locomotor"){
                                disablesublist();
                            }else if(disabilityname == "MentalRetardation"){
                                disableMRsublist();
                            }

                        }
                    }
                }
            }



            function disablesublist(){
                var maindisabilitysize = <%=dataEnteredList.size()%>;
                if(maindisabilitysize>0){
                    for (var i =1; i <= maindisabilitysize; i++)
                    {
                        var disabilityname = document.getElementById(i).value;


                        if(disabilityname == "Locomotor" && document.getElementById(i).checked){

                            var locomotorsublistsize = <%=tempLocoList%>;
                            if(locomotorsublistsize>0){
                                for (var j =1; j <= locomotorsublistsize; j++)
                                {
                                    document.getElementById(j+"s").checked = true;
                                    document.getElementById(j+"s").disabled = false;

                                }
                            }
                        }else if(disabilityname == "Locomotor" && !document.getElementById(i).checked){
                            var locomotorsublistsize = <%=tempLocoList%>;
                            if(locomotorsublistsize>0){
                                for (var j =1; j <= locomotorsublistsize; j++)
                                {
                                    document.getElementById(j+"s").checked = false;
                                    document.getElementById(j+"s").disabled = true;

                                }
                            }
                        }
                    }
                }
            }




            function disableMRsublist(){
                var maindisabilitysize = <%=dataEnteredList.size()%>;
                if(maindisabilitysize>0){
                    for (var i =1; i <= maindisabilitysize; i++)
                    {
                        var disabilityname = document.getElementById(i).value;

                        if(disabilityname == "MentalRetardation" && document.getElementById(i).checked){
                            var mentalretardationtestssize = <%=tempMentalList%>;
                            if(mentalretardationtestssize>0){
                                for (var j =1; j <= mentalretardationtestssize; j++)
                                {
                                    document.getElementById(j+"M").checked = true;
                                    document.getElementById(j+"M").disabled = false;

                                }
                            }
                        }else if(disabilityname == "MentalRetardation" && !document.getElementById(i).checked){
                            var mentalretardationtestssize = <%=tempMentalList%>;
                            if(mentalretardationtestssize>0){
                                for (var j =1; j <= mentalretardationtestssize; j++)
                                {
                                    document.getElementById(j+"M").checked = false;
                                    document.getElementById(j+"M").disabled = true;

                                }
                            }
                        }
                    }
                }
            }

            function disableLocoMain(){
            var chks = <%=tempLocoList%>;
            var hasChecked = false;
            if(chks > 0){
            for (var j =1; j <= chks; j++)
            {
            if (document.getElementById(j+"s").checked)
            {
              hasChecked = true;
              break;
            }
            }
            }
            if (hasChecked == false)
            {
                var maindisabilitysize = <%=dataEnteredList.size()%>;
                if(maindisabilitysize>0){
                    for (var i =1; i <= maindisabilitysize; i++)
                    {
                      var disabilityname = document.getElementById(i).value;
                      if(disabilityname == "Locomotor"){
                          document.getElementById(i).checked = false;
                          disablesublist();
                      }
                    }
                }
             }
            }

            function disableMRMain(){
            var chks = <%=tempMentalList%>;
            var hasChecked = false;
            if(chks > 0){
            for (var j =1; j <= chks; j++)
            {
            if (document.getElementById(j+"M").checked)
            {
              hasChecked = true;
              break;
            }
            }
            }
            if (hasChecked == false)
            {
                var maindisabilitysize = <%=dataEnteredList.size()%>;
                if(maindisabilitysize>0){
                    for (var i =1; i <= maindisabilitysize; i++)
                    {
                      var disabilityname = document.getElementById(i).value;
                      if(disabilityname == "MentalRetardation"){
                          document.getElementById(i).checked = false;
                          disableMRsublist();
                      }
                    }
                }
             }
            }


        </script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Get Data Entered Fields</title>
    </head>
    <body onload="disablesublist();disableMRsublist();">
        <html:form action="getDataEnteredDetailsAU.do?getDataEnteredFieldDetails=getDataEnteredFieldDetails" method="post">

            <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
                <tr>
                <input type="hidden" name="personcode" value="<%=personcode%>"/>
                <input type="hidden" name="reportdisplay"/>
                <input type="hidden" name="dataEnteredListtemp" value="<%=dataEnteredList%>"/>
                <td align="center" class="heading">Data Entered Fields</td>
            </tr>
        </table>

        <table  align="center" cellspacing="0" cellpadding="5" class="innerTable1" width="85%" border="0">
            <tr>
                <td align="center" class="labelBlue" width="30%"><font size="2"><b>select All</b></font> <input type='checkbox' name='checkall' onclick="checkedAll();"></td>
                <td align="left" class="labelBlue" width="70%"><font size="2"><b>Name of the Sections</b></font></td>
            </tr>
            <% int i = 1;
                        for (String dataEnteredName : dataEnteredList) {
            %>
            <tr>
                <td align="center" class="label">
                    <% if ("Locomotor".equals(dataEnteredName)) {%>
                    <input type="checkbox" name="dataenteredname" id="<%=i++%>" value="<%=dataEnteredName%>" onclick="disablesublist();"/>
                    <%} else if ("MentalRetardation".equals(dataEnteredName)) {%>
                    <input type="checkbox" name="dataenteredname" id="<%=i++%>" value="<%=dataEnteredName%>" onclick="disableMRsublist();"/>
                    <%} else {%>
                    <input type="checkbox" name="dataenteredname" id="<%=i++%>" value="<%=dataEnteredName%>" />
                    <%}%>
                </td>
                <td align="left" class="label">
                    <%=dataEnteredName%></td>
                    <% if ("Locomotor".equals(dataEnteredName)) {
                                                    int j = 1;
                       if(locomotorsublist != null && !locomotorsublist.isEmpty()){
                       for (String locomotorsubname : locomotorsublist) {
                    %>
            <tr>
                <td align="right" class="label">
                    <input type="checkbox" name="locomotorsubname" id="<%=j++%>s" value="<%=locomotorsubname%>" onclick="disableLocoMain();"/>

                </td>
                <td align="left" class="label">
                    <%=locomotorsubname%></td>
            </tr>
            <%}
              }
             } else if ("MentalRetardation".equals(dataEnteredName)) {
                 int k = 1;
               if(mentalretardationtests != null && !mentalretardationtests.isEmpty()){
                 for (String testname : mentalretardationtests) {%>
            <tr>
                <td align="right" class="label">
                    <input type="checkbox" name="mentalretardationtest" id="<%=k++%>M" value="<%=testname%>" onclick="disableMRMain();"/>

                </td>
                <td align="left" class="label">
                    <%=testname%></td>
            </tr>

            <%}
             }
             }%>
        </tr>

        <%}%>
    </table>


    <table align="center"><tr>
            <td align="center">
                <html:button property="GeneratePdf" value="GeneratePdf" styleClass="button"  onclick="return validateCheckBox();"/>&nbsp;&nbsp;
            </td>

        </tr>
    </table>


</html:form>
</body>
</html>
