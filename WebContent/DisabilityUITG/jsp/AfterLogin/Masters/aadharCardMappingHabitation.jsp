<%-- 
    Document   : aadharCardMappingHabitation
    Created on : 1 Nov, 2014, 3:33:29 PM
    Author     : 310926
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.*,com.tcs.sadarem.util.CommonUtility,com.tcs.sadarem.util.ComboUtil"%>
<%@page session="true"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.ReportDTO;" %>

<%
            int i = 1;
            String disName = null;
            String mandal_id = null;
            String village_id = null;
			ArrayList mandalList = new ArrayList();
            String dis = null;
              
            mandalList =(ArrayList) request.getAttribute("mandallist");
            String roleId = CommonUtility.checkNullObj(session.getAttribute("roleId"));
            

            if (request.getAttribute("district_id") != null) 
            {
                dis = (String) request.getAttribute("district_id");
            }
            if (request.getAttribute("mandal_id") != null)
            {
                mandal_id = (String) request.getAttribute("mandal_id");
            }
            if (request.getAttribute("village_id") != null) 
            {
                village_id = (String) request.getAttribute("village_id");
            }
            if (request.getAttribute("districtName") != null) 
            {
                disName = (String) request.getAttribute("districtName");
            }

%>
<html:html>
    <head>
        <script language="JavaScript" src="./DisabilityUITG/js/Ajax.js"></script>
        <title>SADAREM</title>
        <script>
            var flag=null;
            <%
                        if (request.getAttribute("flag") != null) {%>
                            flag='<%=request.getAttribute("flag")%>';
            <%}%>

                function getValidation(){
                    var d  = document.forms[0];
                    if(d.mandal_id.value=="0"){
                        alert("Please Select Mandal");
                        d.mandal_id.focus();
                        return false;
                    }
                    else{
                        d.mode.value='unspecified';
                        d.submit();

                    }
                    // document.getElementById("print").style.display="none";
                }



                function removeLists(start,end){
                    for(k=start;k<=end;k++)
                    {
                        var x1=document.getElementById(k).length;
                        for(i=x1;i>1;i--)
                            document.getElementById(k).options[i]=null;
                        document.getElementById(k).value="";
                    }
                }
        </script>

        <script>

            function villageList(){
                var manid= document.forms[0].mandal_id.value;
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
                distid= document.forms[0].district_id.value;
                if(distid == ""){
                    distid = document.getElementById("districtid").value;
                }
                var mandid= document.forms[0].mandal_id.value;

                var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&mandalid="+mandid+"&territory=5";

                x.open("GET",url,true)
                x.send();
            }
            function getVillageDetails()
            {
                var rs1,rs2;
                removealls("village_id");
                document.forms[0].village_id.value="All";

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
                        addoption1(rs1,rs2,"village_id");
                        z++;
                    }
                }

            }


            function habitationList(){
                var manid= document.forms[0].mandal_id.value;
                if(manid==""){
                    removeLists(4,6);
                }else{
                    createHabitationObject();
                }
            }
            function  createHabitationObject()
            {

                var distid;
                x=GetXmlHttpObject()
                x.onreadystatechange=getHabitationDetails;
                distid= document.forms[0].district_id.value;
                if(distid == ""){
                    distid = document.getElementById("districtid").value;
                }
                var mandid= document.forms[0].mandal_id.value;
                var villageid= document.forms[0].village_id.value;

                var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&mandalid="+mandid+"&villageid="+villageid+"&territory=6";

                x.open("GET",url,true)
                x.send();
            }
            function getHabitationDetails()
            {
                var rs1,rs2;
                removealls("habitation_id");
                document.forms[0].habitation_id.value="All";

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
                        addoption1(rs1,rs2,"habitation_id");
                        z++;
                    }
                }

            }


            function addoption1(result1,result2,name)
            {
                var opt=document.createElement("OPTION");
                opt.text=result1;
                opt.value=result2;

                try{
                    document.getElementById(name).add(opt);
                }catch(ex){
                    if(name=="district_id") {
                        document.forms[0].district_id.appendChild(opt,null);
                    }else  if(name=="mandal_id") {
                        document.forms[0].mandal_id.appendChild(opt,null);
                    }else if(name=="village_id") {
                        document.forms[0].village_id.appendChild(opt,null);
                    }else if(name=="habitation_id") {
                        document.forms[0].habitation_id.appendChild(opt,null);
                    }

                }

            }

            function removealls(name)
            {
                if(name=="mandal_id") {
                    var x1=document.forms[0].mandal_id.options.length;
                }
                else if(name=="district_id") {
                    var x1=document.forms[0].district_id.options.length;
                }else if(name=="village_id") {
                    var x1=document.forms[0].village_id.options.length;
                }else if(name=="habitation_id") {
                    var x1=document.forms[0].habitation_id.options.length;
                }

                for(i=x1;i>0;i--) {
                    if(name=="mandal_id") {
                        document.forms[0].mandal_id.options[i]=null;
                    } else if(name=="district_id") {
                        document.forms[0].district_id.options[i]=null;
                    }else if(name=="village_id") {
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



            function getDetails(){
                document.getElementById("login").style.display="none";
                document.getElementById("sadaremdata").style.display="none";
            }
            function changeSelection(id){

                if(id=='Habitation'){
                    document.getElementById('habitation').style.display="block";
                }else{
                    document.forms[0].action='aadharCardMapping.do?mode=unspecified&flag=sadarem';
                    document.forms[0].submit();
                }
            }
            function bodyLoad(){
               
                if(flag!=null && flag=='habitation'){
                    document.getElementById('3').checked=true;
                    document.getElementById('habitation').style.display="block";
                }

            }


        </script>
        
      
    </head>
    <body onload="bodyLoad();">


        <html:form action="/aadharCardMapping?mode=aadharHabitation&flag=habitation">
            <html:hidden property="mode"/>
            <html:hidden property="district_id"/>
            <logic:present name="nodata">
                <p id="errmsg">${nodata}</p>
            </logic:present>

            
             

            <table border="0" cellspacing="1" cellpadding="0" width="90%" align="center" class="inputform">
            
                <tr>
                  <th colspan="6">AadharCard Mapping</th>
                </tr>
                
                <tr>
                    <td colspan="6" align="center">
                        <html:radio property="typeOfInput" styleId="2" value="SADAREMID" onclick="changeSelection(this.value);" style="border:0">SADAREM ID</html:radio>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <html:radio property="typeOfInput" styleId="3" value="Habitation" onclick="changeSelection(this.value);" style="border:0">Habitation Wise</html:radio>
                    </td>
                </tr>
                <tr id="habitation" style="display: none;">


                    <td valign="middle" class="label" width="8%" >Mandal<font color="red"><b>*</b></font></td>
                    <td align="left" valign="middle" >
                     
                     <%if("99".equals(roleId)){ %>
                     
                      <%=ComboUtil.createStrComboBoxAuto("mandal_id",mandalList,mandal_id,"select-optionItem1","disabled",true,true,"")%>
                       
                     
                     <%}else{ %> 
                        <html:select styleId="2" property="mandal_id" style="width:150px;height:25px;font-size:11px;" onchange="villageList(this.value),getDetails();" >
                            <html:option  value="0" >--SELECT--</html:option>
                            <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"/>
                        </html:select>
                      <%} %>
                        
                    </td>
                    <td valign="middle" class="label" width="8%">Village</td>
                    <td align="left" valign="middle" >
                        <html:select styleId="4" property="village_id"  style="height:25px;font-size:11px;" onchange="habitationList(this.value),getDetails();">
                            <html:option  value="All">--ALL--</html:option>
                            <html:optionsCollection property="villagelist" label="village_name" value="village_id"/>
                        </html:select>
                    </td>
                    <td valign="middle" class="label" width="8%">Habitation</td>
                    <td align="left" valign="middle" >
                        <html:select styleId="4" property="habitation_id"  style="height:25px;font-size:11px;" onchange="getDetails();">
                            <html:option  value="All">--ALL--</html:option>
                            <html:optionsCollection property="habitationlist" label="habitation_name" value="habitation_id"/>
                        </html:select>
                    </td>

                </tr>
                <tr>
                    <th colspan="6" align="center"><html:button property="but" onclick="getValidation();" value="Submit"/>
                    </th>
                </tr>
            </table>



            <br/>
            <logic:notEmpty name="shgAbstract">


                <%--<logic:iterate name="shgAbstract" id="row" scope="request" length="1" >

                    <table border="0" cellspacing="1" cellpadding="0" width="90%" align="center" id="sadaremdata">
                        <tr>
                            <td  colspan="12" style="text-align: right">
                                <a href="sadaremidNothavingaadhracardNos.xls?mode=excelWriting&districtid=${row.districtId}&district_name=${row.district_name}&mandalid=${row.mandalId}&mandal_name=${row.mandal_name}&villageid=${row.villageid}&village_name=${row.village_name}" target="_blank">
                                    Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="20" width="20"/></a> &nbsp; &nbsp; &nbsp;

                                <a href="sadaremidNothavingaadhracardNos.xls?mode=print&districtids=${row.districtId}&district_names=${row.district_name}&mandalids=${row.mandalId}&mandal_names=${row.mandal_name}&villageids=${row.villageid}&village_names=${row.village_name}" target="_blank">
                                    Print <img src="DisabilityUITG/images/print.gif" height="20" width="20"/></a> &nbsp; &nbsp; &nbsp;


                            </td>
                        </tr></table>
                    </logic:iterate>--%>
 <table border="0" cellspacing="1" cellpadding="0" width="90%" align="center">
                        <tr valign="top">
                            <td  valign="top">
                <div style="overflow:auto; width:900px; height:350px" id="login">


                    <table border="0" cellspacing="1" cellpadding="0" width="90%" align="center"  class="inputform">
                        <tr valign="top">
                            <th  valign="top">S.No</th>
                            <th>SADAREM ID</th>
                            <th>Person Name</th>
                            <th>Relation Name</th>
                            <%-- <th>Age</th>
                             <th>Gender</th>
                             <th>Education</th>--%>
                            <th>RationCard Number</th>
                            <th>Pension ID</th>
                            <%--<th>Assessment Status</th>--%>
                            <th>Address</th>
                            <%--<th>Phone No</th>--%>
                            <th>Action</th>
                        </tr>
                        <logic:iterate name="shgAbstract" id="row" scope="request">
                            <tr valign="top">
                                <td  valign="top">${row.sno}</td>
                                <td>

                                    ${row.SADAREMCODE}
                                </td>
                                <td>${row.PERSONNAME}</td>
                                <td>

                                    ${row.RELATIONNAME}

                                </td>

                                <%-- <td>

                                    ${row.age}
                                </td>
                                <td>

                                    ${row.Gender}
                                </td>

                                <td>
                                    ${row.EDUCATION}


                                </td>--%>

                                <td>

                                    ${row.RATIONCARD_NUMBER}
                                </td>

                                <td>
                                    ${row.PENSIONID}


                                </td>
                               <%-- <td>
                                    ${row.ASSESSEMENTSTATUS}


                                </td>--%>
                                <td>
                                    ${row.Address}


                                </td>
                               <%-- <td>
                                    ${row.phone_no}


                                </td>--%>
                                <td>

                                    <a href="javascript:void(0);" onclick ="window.open('aadharCardMapping.do?mode=getAadharCardNoDetails&personcode=${row.SADAREMCODE}&rationCardNo=${row.RATIONCARD_NUMBER}', 'Ratting','resizable=yes, scrollbars=yes,')">Update</a>

                                </td>
                            </tr>
                        </logic:iterate>


                    </table>
</div>
         </td>
                            </tr>
                      


                    </table>       <br><br>
                <%-- <div id="print">--%>
           
            <%--  </div>--%>

        </logic:notEmpty>

            <logic:present name="errmsg">
                <script language="javascript" type="text/javascript">
                    alert("${errmsg}");
                </script>
            </logic:present>
             <logic:present name="succmsg">
                <script language="javascript" type="text/javascript">
                    alert("${succmsg}");
                </script>
            </logic:present>
            <logic:present name="closewindow">
                <script>
                    window.opener.location.reload();
                    window.close();
                </script>
            </logic:present>
        </html:form>
</body>
</html:html>
