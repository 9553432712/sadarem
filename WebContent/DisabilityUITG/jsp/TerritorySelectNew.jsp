
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.*,java.text.*"%>

<html:html>

    <head>
    	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
	    <style type="text/css">    
		    label
			{
				margin-left:5px!important;
			}
	    </style>
        <%
                    String districtid = "";
                    if (request.getAttribute("districtidData") != null) {
                        districtid = (String) request.getAttribute("districtidData");

                    }

                    request.setAttribute("restrictPartA", "true");

        %>
        <script>
            <%--    <% String restrictPartA = request.getParameter("restrictPartA");%>--%>



                function removeLists(start,end){
                    for(k=start;k<=end;k++)
                    {
                        var x1=document.getElementById(k).length;


                        for(i=x1;i>0;i--)
                            document.getElementById(k).options[i]=null;
                        document.getElementById(k).value="";
                    }
                }

                function createDistrictObject()
                {

                    x=GetXmlHttpObject()
                    x.onreadystatechange=getDistrictDetails;
                    var url="getTerritory.do?parameter=getTerritoryList&territory=1";
                    x.open("Get", url, true);
                    x.send();
                }

                function getDistrictDetails()
                {
                    var res1,res2;
                    removeall("district_id");
                    if(x.readyState==4 || x.readyState=="complete")
                    {
                        var m=x.responseXML.documentElement;
                        var z=0;
                        var countss=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                        m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
                        z=1;
                        while(z<=countss)
                        {
                            res1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                            res2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
                            addoption(res1,res2,"district_id");
                            z++;
                        }
                    }
                }









                function  createMandalObject()
                {

                    x=GetXmlHttpObject()
                    x.onreadystatechange=getMandalDetails;
                    var distid=<%=districtid%>;


                    var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&territory=2";

                    x.open("GET",url,true)
                    x.send();
                }

                function  createOnlyMandalObject(districtId)
                {
                    x=GetXmlHttpObject()
                    x.onreadystatechange=getMandalDetails;
                    var url="getTerritory.do?parameter=getTerritoryList&districtid="+districtId+"&territory=2";
                    x.open("GET",url,true)
                    x.send();
                }

                function getMandalDetails()
                {
                    var rs1,rs2;
              removeall("mandal_id");
              removeall("village_id");
              removeall("panchayat_id");
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
                             addoption(rs1,rs2,"mandal_id");
                             z++;
                         }
                     }
                 }

                 function mandalList(){
                     var manid=document.getElementById("mandal_id").value;
                     var slcBx = document.getElementById("mandal_id");
                     document.getElementById("mandalname").value = slcBx.options[slcBx.selectedIndex].text;
                     if(manid==""){
                         removeLists(3,6);
                     }else{
                         createPanchayatObject()();
                     }
                 }


                 function  createPanchayatObject()
                 {
                     x=GetXmlHttpObject()
                     x.onreadystatechange=getPanchayatDetails;
                     var distid=document.getElementById("district_id").value;
                     if(distid == ""){
                         distid = document.getElementById("districtid").value;
                     }
                     var mandid=document.getElementById("mandal_id").value;
                     var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&mandalid="+mandid+"&territory=4";
                     x.open("GET",url,true)
                     x.send();
                 }

                 function getPanchayatDetails()
                 {
                     var rs1,rs2;

                     removeall("panchayat_id");
                     removeall("village_id");
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
                             addoption(rs1,rs2,"panchayat_id");
                             z++;
                         }
                     }
                 }

                 function panchayatList(){


                     var panchayat = document.getElementById("panchayat_id").value;


                     var slcBx = document.getElementById("village_id");

                     document.getElementById("panchayatname").value = slcBx.options[slcBx.selectedIndex].text;

                     if(panchayat == ""){


                         removeLists(4,6);

                     }else{
                         createVillageObject()();
                     }
                 }

                 function  createVillageObject()
                 {
                     var distid;
                     x=GetXmlHttpObject()
                     x.onreadystatechange=getVillageDetails;
                     distid=document.getElementById("district_id").value;


                     if(distid == ""){
                         distid = document.getElementById("districtid").value;

                     }
                     var mandid=document.getElementById("mandal_id").value;

                     var panchayat=document.getElementById("panchayat_id").value;
                     var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&mandalid="+mandid+"&panchayatid="+panchayat+"&territory=7";


                     x.open("GET",url,true)
                     x.send();
                 }

                 function getVillageDetails()
                 {
                     var rs1,rs2;

                     removeall("village_id");
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
                             addoption(rs1,rs2,"village_id");
                             z++;
                         }
                     }
                 }

                 function villagesList(){


                     var villageid = document.getElementById("village_id").value;

                     var slcBx = document.getElementById("panchayat_id");

                     document.getElementById("villagename").value = slcBx.options[slcBx.selectedIndex].text;

                     if(villageid == ""){


                         removeLists(5,6);

                     }else{
                         createHabitationObject();
                     }
                 }



                 function  createHabitationObject()
                 {
                     x=GetXmlHttpObject()
                     x.onreadystatechange=getHabitationDetails;
                     var distid=document.getElementById("district_id").value;
                     if(distid == ""){
                         distid = document.getElementById("districtid").value;
                     }
                     var mandid=document.getElementById("mandal_id").value;
                     var panchayat=document.getElementById("panchayat_id").value;
                     var villageid = document.getElementById("village_id").value;

                     var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&mandalid="+mandid+"&panchayatid="+panchayat+"&villageid="+villageid+"&territory=12";
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

                 function getHabitationName(){
                     var slcBx = document.getElementById("habitation_id");
                     document.getElementById("habitationname").value = slcBx.options[slcBx.selectedIndex].text;
                 }



                 function accessPersonStatus(){

                     document.getElementById("districtdisplay").style.display="none";
                     document.getElementById("districtdisplay").style.visible="hidden";

                     x=GetXmlHttpObject()
                     x.onreadystatechange = getPersonStatus;
                     var url="accessPersonStatus.do?parameter=accessPersonStatus";
                     x.open("GET",url,true)
                     x.send();

                 }

                 function getPersonStatus(){
                     if(x.readyState==4 || x.readyState=="complete")
                     {
                         var m=x.responseXML.documentElement;
                         var z=0;
                         var permissionFlag = m.getElementsByTagName("adminLevelAccessFlag")[0].firstChild.nodeValue;
                         var districtid = m.getElementsByTagName("districtid")[0].firstChild.nodeValue;
                         var districtname = m.getElementsByTagName("districtname")[0].firstChild.nodeValue;
                         disideFlag(permissionFlag,districtid,districtname);
                     }
                 }


                 function disideFlag(permissionFlag,districtid,districtname){
                     if(permissionFlag == "true"){
                         document.getElementById("districtdisplay").style.display="inline";
                         document.getElementById("districtdisplay").style.visible="true";
                         createDistrictObject();
                     }else{
                         document.getElementById("districtid").value = districtid;
                         document.getElementById("districtname").value = districtname;

                     }
                 }
                 function addoption(result1,result2,name)
                 {
                     var opt=document.createElement("OPTION");
                     opt.text=result1;
                     opt.value=result2;
                     document.getElementById(name).add(opt);
                 }
                 function removeall(name)
                 {
                     var x1=document.getElementById(name).length;

                     for(i=x1;i>0;i--)
                         document.getElementById(name).options[i]=null;
                     document.getElementById(name).value="";

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


                 function goBack()
                 {
                     document.forms[0].action="DecisionForPWDPensionRestrictPartA.do";
                     document.forms[0].submit();
                 }
                 function validate_required(field,alerttxt)
                 {
                     with (field)
                     {
                         if (value==null||value=="")
                         {
                             alert(alerttxt);
                             return false
                         }
                         else
                         {
                             return true
                         }
                     }
                 }
                 function validate_form(thisform)
                 {
                     with (thisform)
                     {

                         if (validate_required(mandal_id,"Select Mandal")==false)
                         {
                             mandal_id.focus();
                             return false
                         }
                         if (validate_required(panchayat_id,"Select Panchayat")==false)
                         {
                             panchayat_id.focus();
                             return false
                         }
                         if (validate_required(village_id,"Select Village")==false)
                         {
                             village_id.focus();
                             return false
                         }
                         if (validate_required(habitation_id,"Select Habitation")==false)
                         {
                             habitation_id.focus();
                             return false
                         }
                         
                         if (validate_required(typeofdisability,"Select Type of Disability")==false)
                         {
                             typeofdisability.focus();
                             return false
                         }
                          
                     }
                 }


                 function prasad()
                 {
                     var count=0;
                     count++;
                     devi();
                 }
                 function getData(read)
                 {
                     document.forms[0].status.value="update";
                     document.forms[0].submit();
                 }

                 function devi()
                 {
                     alert(count);
                     if(count==1)
                         document.territoryform.mandal_id.selectedIndex=0;
                 }
            <%-- function goBack()
             {
                 document.forms[0].action="DecisionForPWDPensionRestrictPartA.do";
                 document.forms[0].submit();
             }--%>
        </script>

    </head>

    <BODY onload="createMandalObject(),accessPersonStatus();">

    <LINK REL="stylesheet" HREF="./DisabilityUITG/css/cssmaster.css">

    <html:form action="TerritorySelectNew.do" method="post"  onsubmit="return validate_form(this)" >

        <input type="hidden" name="status" value="finish"/>
        <input type="hidden" name="districtid" id="districtid"/>
        <%-- <input type="hidden" name="restrictPartA" value="<%=restrictPartA%>"/>--%>
        <input type="hidden" name="district_name" id="districtname"/>
        <input type="hidden" name="mandal_name" id="mandalname"/>
        <input type="hidden" name="village_name" id="villagename"/>
        <input type="hidden" name="panchayat_name" id="panchayatname"/>
        <input type="hidden" name="habitation_name" id="habitationname"/>
        <input type="hidden" name="district_id" id="district_id" value="<%=districtid%>"/>
        <input type="hidden" name="restrictPartA" id="restrictPartA"  value="true"/>
        <input type="hidden" name="personCodeFlag" id="personCodeFlag"  value="personCodeFlag"/>
	
						<div class="col-md-7 col-md-offset-3">
				            		<div class="panel panel-primary">
				            		 
			      						<div class="panel-heading" style="text-align: center;"><b> Select the Territory For Add </b></div>
									    <div class="panel-body" style="text-align: left;">
									    	<div class="form-group">
									    		<div class="row">
									    			<div class="col-md-2">
									    				<label for="usr">Mandal </label>
									    			</div>
									    			<div class="col-md-2">
									    				<html:select styleId="mandal_id" property="mandal_id" onchange="mandalList()" >
						                                    <html:option  value="">--SELECT--</html:option>
						                                    <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"  />
						                                </html:select>
									    			</div>
									    		</div>														
											</div>
											<div class="form-group">
												<div class="row">
									    			<div class="col-md-2">
									    				<label for="usr">Panchayat </label>
									    			</div>
									    			<div class="col-md-2">
									    				<html:select styleId="panchayat_id" property="panchayat_id" onchange="panchayatList()">
						                                    <html:option  value="">--SELECT--</html:option>
						                                    <html:optionsCollection   property="panchayatlist" label="Panchayat_Name" value="Panchayat_ID"  />
						                                </html:select>
									    			</div>
									    		</div>
											</div>
											<div class="form-group">
												<div class="row">
									    			<div class="col-md-2">
									    				<label for="usr">Village </label>
									    			</div>
									    			<div class="col-md-2">
									    				<html:select styleId="village_id" property="village_id" onchange="villagesList()">
						                                    <html:option  value="">--SELECT--</html:option>
						                                    <html:optionsCollection   property="villagelist" label="village_name" value="village_id"  />
						                                </html:select>
									    			</div>
									    		</div>
											</div>
											<div class="form-group">
												<div class="row">
									    			<div class="col-md-2">
									    				<label for="usr">Habitation </label>
									    			</div>
									    			<div class="col-md-2">
									    				<html:select styleId="habitation_id" property="habitation_id">
						                                    <html:option  value="">--SELECT--</html:option>
						                                    <html:optionsCollection   property="habitationlist" label="habitation_name" value="habitation_id"  />
						                                </html:select>
									    			</div>
									    		</div>
											</div>
											<input type="hidden" name="restrictPartA" value="true"/>
											<div class="form-group">
													  <label for="usr" style="float: right;">													  		
								                          		<input type="Submit" class="btn btn-info"  value="Submit" class="button"  onclick="getHabitationName()">
								                          	
													  </label>
												 </div>
									    </div>
									</div>
						</div>

       <%--  <table border="0" cellspacing="1" cellpadding="0" width="50%" align="center" class="inputform">
            <tr><td>


                    <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center" class="inputform">
                        <tr>
                            <th colspan="4">Select the Territory For Add   </th>
                        </tr>




                        <tr>
                            <td>Mandal</td>
                            <td class="textbox">
                                <html:select styleId="mandal_id" property="mandal_id" onchange="mandalList()" >
                                    <html:option  value="">--SELECT--</html:option>
                                    <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"  />
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td >Panchayat</td>
                            <td class="textbox">
                                <html:select styleId="panchayat_id" property="panchayat_id" onchange="panchayatList()">
                                    <html:option  value="">--SELECT--</html:option>
                                    <html:optionsCollection   property="panchayatlist" label="Panchayat_Name" value="Panchayat_ID"  />
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>Village</td>
                            <td class="textbox">
                                <html:select styleId="village_id" property="village_id" onchange="villagesList()">
                                    <html:option  value="">--SELECT--</html:option>
                                    <html:optionsCollection   property="villagelist" label="village_name" value="village_id"  />
                                </html:select>
                            </td>
                        </tr>

                        <tr>
                            <td>Habitation</td>
                            <td class="textbox">
                                <html:select styleId="habitation_id" property="habitation_id">
                                    <html:option  value="">--SELECT--</html:option>
                                    <html:optionsCollection   property="habitationlist" label="habitation_name" value="habitation_id"  />
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                        <input type="hidden" name="restrictPartA" value="true"/>
            </tr> 

            <tr>
                <th  align = "center" colspan="2"><input type="Submit" value="Submit" class="button"  onclick="getHabitationName()">
                </th>
            </tr>


        </table>
    </td></tr>
</table> --%>
</html:form>


</body>
</html:html>
