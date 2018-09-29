<%-- 
    Document   : ReassessmentFiltring
    Created on : Feb 10, 2012, 11:09:13 AM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList;" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            String district_id = (String) session.getAttribute("districtId");
            int i = 1;
            int j = 0;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script>
            function removeLists(start,end){
                for(k=start;k<=end;k++)
                {
                    var x1=document.getElementById(k).length;
                    for(i=x1;i>1;i--)
                        document.getElementById(k).options[i]=null;
                    document.getElementById(k).value="";
                }
            }
         

            function mandalList()
            {
                             
                var manid=document.forms[0].mandal_id.value;
                if(manid==""){
                    removeLists(4,6);
                }else{
                    createVillageObject();
                }
            }

            function  createVillageObject()
            {
                var distid;
                x=GetXmlHttpObject()
                x.onreadystatechange=getVillageDetails;
                distid=document.forms[0].districtid.value;
                if(distid == "")
                {
                    distid = document.forms[0].districtid.value;
                }
                var mandid=document.forms[0].mandal_id.value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&mandalid="+mandid+"&territory=5";
                x.open("GET",url,true)
                x.send();
            }

            function getVillageDetails()
            {
                var rs1,rs2;
                removeall("village_id");

                document.forms[0].village_id.value="0";

                if(x.readyState==4 || x.readyState=="complete")
                {
                    var m=x.responseXML.documentElement;
                    var z=0;
                    var counts=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                    m.getElementsByTagName("id")[z].childNodes[0].nodeValues;
                    z=1;
                    while(z<=counts)
                    {
                        rs1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                        rs2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
                        addoption(rs1,rs2,"village_id");
                        z++;
                    }
                }
            }



            function habitationList(){
                
                var villageid = document.forms[0].village_id.value;
             
                if(villageid == ""){
                    removeLists(6,6);
                }else if(villageid!="0"){
                    createHabitationObject();
                }else {
                    document.forms[0].habitation_id.value="0";
                }
            }

            function  createHabitationObject()
            {
                x=GetXmlHttpObject()
                x.onreadystatechange=getHabitationDetails;
                var distid=document.forms[0].districtid.value;
                if(distid == ""){
                    distid = document.forms[0].districtid.value;
                }
                var mandid=document.forms[0].mandal_id.value;
                var villageid = document.forms[0].village_id.value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&mandalid="+mandid+"&villageid="+villageid+"&territory=6";
                x.open("GET",url,true)
                x.send();
            }

            function getHabitationDetails()
            {
                var rs1,rs2;
                removeall("habitation_id");
                if(x.readyState==4 || x.readyState=="complete")
                {
                    var m=x.responseXML.documentElement;
                    var z=0;
                    var counts=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                    m.getElementsByTagName("id")[z].childNodes[0].nodeValues;
                    z=1;
                    while(z<=counts)
                    {
                        rs1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                        rs2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
                        addoption(rs1,rs2,"habitation_id");
                        z++;
                    }
                }
            }

            function addoption(result1,result2,name)
            {
                var opt=document.createElement("OPTION");
                opt.text=result1;
                opt.value=result2;
                try{
                    document.getElementById(name).add(opt);
                }catch(ex){
                    if(name=="mandal_id") {
                        document.forms[0].mandal_id.appendChild(opt,null);
                    }else  if(name=="village_id") {
                        document.forms[0].village_id.appendChild(opt,null);
                    }else  if(name=="habitation_id") {
                        document.forms[0].habitation_id.appendChild(opt,null);
                    }



                }
            }
            function removeall(name)
            {
                if(name=="mandal_id") {
                    var x1=document.forms[0].mandal_id.options.length;
                }
                else if(name=="village_id") {
                    var x1=document.forms[0].village_id.options.length;
                }else if(name=="habitation_id") {
                    var x1=document.forms[0].habitation_id.options.length;
                }

                for(i=x1;i>0;i--) {
                    if(name=="mandal_id") {
                        document.forms[0].mandal_id.options[i]=null;
                    } else if(name=="village_id") {
                        document.forms[0].village_id.options[i]=null;
                    }else if(name=="habitation_id") {
                        document.forms[0].habitation_id.options[i]=null;
                    }
                }

            }

            function GetXmlHttpObject()
            {
                var objXmlHttp=null
                if(window.XMLHttpRequest)
                {
                    objXmlHttp=new XMLHttpRequest();
                }
                else if(window.ActiveXObject)
                {
                    objXmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
                }
                return objXmlHttp;
            }

            function getReport() {

                document.forms[0].mode.value="getReport";
                document.forms[0].submit();
            }

            function deleteRecords() {
            
                document.forms[0].mode.value="deleteRecords";
                document.forms[0].submit();
               
            }

            function validate() {
                var chks =document.getElementsByName('checkedCandidates');
                var hasChecked = false;
                for (var k = 0; k < chks.length; k++)
                {
                    if (chks[k].checked)
                    {
                        hasChecked = true;
                        break;
                    }
                }
                if (hasChecked == false)
                {
                    alert("Please select at least one record.");
                    return false;
                }

                if(hasChecked)
                    deleteRecords();
                return true;

            }

            function checkAll(field)
            {
                if(document.getElementById("check").checked == true) {
                    for (i = 0; i < field.length; i++)
                        field[i].checked = true ;
                }else {
                    for (i = 0; i < field.length; i++)
                        field[i].checked = false ;
                }
            }

        </script>
    </head>

    <body>
        <html:form action="/reassessmentFiltring">
            <html:hidden property="mode"/>
            <input type="hidden" name="districtid" value="<%=district_id%>">

            <table align="center" cellspacing="1" cellpadding="0" class="inputform" border="0" width="90%">
                <tr>
                    <th colspan="6">
                         Re-Assessment for Leftover list
                    </th>
                </tr>
                <tr>
                    <td>Mandal :</td>
                    <td>
                        <html:select property="mandal_id" onchange="mandalList()">
                            <html:option  value="0">--- ALL ---</html:option>
                            <html:optionsCollection property="mandal_list" label="mandal_name" value="mandal_id"/>
                        </html:select>
                    </td>
                    <td>Village :</td>
                    <td >
                        <html:select property="village_id" style="height:25px;" onchange="habitationList()">
                            <html:option  value="0">--- ALL ---</html:option>
                            <html:optionsCollection property="village_list" label="village_name" value="village_id"/>
                        </html:select>
                    </td>
                    <td>Habitation :</td>
                    <td>
                        <html:select property="habitation_id" style="height:25px;">
                            <html:option  value="0">--- ALL ---</html:option>
                            <html:optionsCollection property="habitation_list" label="habitation_name" value="habitation_id"/>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <th colspan="6">
                        <html:button property="but" value="Get Report" onclick="getReport();" />
                    </th>
                </tr>
            </table><br/>

            <logic:present name="msg">
                <p id="errmsg"><bean:write name="msg"/></p>
            </logic:present>

            <logic:notEmpty name="reassessmentDetails">
                <div style="overflow:scroll; width:950px; height:335px" align="center">
                <table align="center" cellpadding="0" cellspacing="1" border="0" class="inputform" width="90%">
                    <tr>
                        <th>
                            Sno
                        </th>
                        <th>
                            SADAREM ID
                        </th>
                        <th>
                            Name
                        </th>
                        <th>
                            Relation Name
                        </th>
                        <th>
                            Percentage
                        </th>
                        <th>
                            Disability Status
                        </th>
                        <th>
                            Action <input type=checkbox name="CheckAll" onClick="checkAll(document.forms[0].checkedCandidates)" id="check">

                        </th>
                    </tr>
                    
                    <logic:iterate name="reassessmentDetails" id="row">
                        <tr>
                            <td>
                                <%=i++%>.
                            </td>
                            <td>
                                ${row.person_code}
                            </td>
                            <td>
                                ${row.name}
                            </td>
                            <td>
                                ${row.relation_name}
                            </td>
                            <td>
                                ${row.percentage}
                            </td>
                            <td>
                                ${row.disabilityStatus}
                            </td>
                            <td width="75px" style="text-align:center">
                                <html:multibox property="checkedCandidates" value="${row.person_code}" />
                            </td>
                        </tr>
                    </logic:iterate>
                    <tr>
                        <th colspan="7">
                            <html:button property="bur" value="Delete" onclick="return validate();"/>
                        </th>
                    </tr>
                </table>
                </div>
            </logic:notEmpty>

        </html:form>
    </body>
</html>
