
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,java.text.*"%>
<%@page session="true"%>


<html:html>


    <head>
        <script >

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

            function districtList(){
                var disid = document.getElementById("district_id").value;
                var slcBx = document.getElementById("1");
                var slcTxt = slcBx.options[slcBx.selectedIndex].text;
                document.getElementById("districtname").value = slcTxt;
                if(disid == ""){
                    removeLists(2,6);
                }else{
                    createAssemblyObject();
                }
            }

            function  createAssemblyObject()
            {
                x=GetXmlHttpObject()
                x.onreadystatechange=getAssemblyDetails;
                var distid=document.getElementById("district_id").value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&territory=3";
                x.open("GET",url,true)
                x.send();
            }

            function getAssemblyDetails()
            {
                var rs1,rs2;
                removeall("assembly_id");
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
                        addoption(rs1,rs2,"assembly_id");
                        z++;
                    }
                }
            }

            function assemblyList(){
                var assemblyid = document.getElementById("assembly_id").value;
                if(assemblyid == ""){
                    removeLists(3,6);
                }else{
                    createMandalObject();
                }
            }

            function  createMandalObject()
            {
                x=GetXmlHttpObject()
                x.onreadystatechange=getMandalDetails;
                var distid=document.getElementById("district_id").value;
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
                var slcBx = document.getElementById("3");
                document.getElementById("mandalname").value = slcBx.options[slcBx.selectedIndex].text;
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
                distid=document.getElementById("district_id").value;
                if(distid == ""){
                    distid = document.getElementById("districtid").value;
                }
                var mandid=document.getElementById("mandal_id").value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&mandalid="+mandid+"&territory=5";
                x.open("GET",url,true)
                x.send();
            }

            function getVillageDetails()
            {
                var rs1,rs2;
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
                        addoption(rs1,rs2,"village_id");
                        z++;
                    }
                }
            }

            function villagesList(){
                var villageid = document.getElementById("village_id").value;
                var slcBx = document.getElementById("4");
                document.getElementById("villagename").value = slcBx.options[slcBx.selectedIndex].text;
                if(villageid == ""){
                    removeLists(5,6);
                }else{
                    createPanchayatObject();
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
                var villageid = document.getElementById("village_id").value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&mandalid="+mandid+"&villageid="+villageid+"&territory=4";
                x.open("GET",url,true)
                x.send();
            }

            function getPanchayatDetails()
            {
                var rs1,rs2;
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
                        addoption(rs1,rs2,"panchayat_id");
                        z++;
                    }
                }
            }

            function panchayatList(){
                var villageid = document.getElementById("panchayat_id").value;
                var slcBx = document.getElementById("5");
                document.getElementById("panchayatname").value = slcBx.options[slcBx.selectedIndex].text;
                if(villageid == ""){
                    removeLists(6,6);
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
                var villageid = document.getElementById("village_id").value;
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

            function getPersonCodeList(){
                var personstatus = document.getElementById("7").value;
                var slcBx = document.getElementById("6");
                document.getElementById("habitationname").value = slcBx.options[slcBx.selectedIndex].text;
                if(personstatus == 0 || personstatus == ""){
                    removeLists(8,8);
                }else{
                    createPersonCodesObject();
                }
            }

            function  createPersonCodesObject()
            {
                x=GetXmlHttpObject()
                x.onreadystatechange=getPersonCodesDetails;
                var distid=document.getElementById("district_id").value;
                if(distid == ""){
                    distid = document.getElementById("districtid").value;
                }
                var mandid=document.getElementById("mandal_id").value;
                var villageid = document.getElementById("village_id").value;
                var habitationid = document.getElementById("habitation_id").value;
                var panchayatid = document.getElementById("panchayat_id").value;
                var personstatus = document.getElementById("personstatus").value;
                var url="getPersoncodes.do?parameter=selectPersonCodesList&districtid="+distid+"&mandalid="+mandid+"&villageid="+villageid+"&habitationid="+habitationid+"&panchayatid="+panchayatid+"&personstatus="+personstatus+"";
                x.open("GET",url,true)
                x.send();
            }

            function getPersonCodesDetails()
            {
                var rs1,rs2;
                removeall("personcode");
                if(x.readyState==4 || x.readyState=="complete")
                {
                    var m=x.responseXML.documentElement;
                    var z=0;
                    var counts=m.getElementsByTagName("personcode")[z].childNodes[0].nodeValue;
                    z=1;
                    while(z<=counts)
                    {
                        rs1=m.getElementsByTagName("personcode")[z].childNodes[0].nodeValue;
                        addoption(rs1,rs1,"personcode");
                        z++;
                    }
                }
            }

            function accessPersonStatus(){

                document.getElementById("districtdisplay").style.display="none";
                document.getElementById("districtdisplay").style.visible="hidden";
                document.getElementById("assemblydisplay").style.display="none";
                document.getElementById("assemblydisplay").style.visible="hidden";


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
                    disideFlag(permissionFlag,districtid);
                }
            }


            function disideFlag(permissionFlag,districtid){
                if(permissionFlag == "true"){
                    document.getElementById("districtdisplay").style.display="inline";
                    document.getElementById("districtdisplay").style.visible="true";
                    document.getElementById("assemblydisplay").style.display="inline";
                    document.getElementById("assemblydisplay").style.visible="true";
                    createDistrictObject();
                }else{
                    document.getElementById("districtid").value = districtid;
                    createOnlyMandalObject(districtid);
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
                    if (validate_required(personcode,"Select personcode")==false)
                    {
                        personcode.focus();
                        return false
                    }
                }
            }

            function getData(read)
            {

                document.forms[0].status.value="update";
                document.forms[0].submit();
            }


        </script>
        <% String restrictPartA = (String) request.getParameter("restrictPartBUpdateByTerr");%>
    </head>

    <body onload="accessPersonStatus();">

    

    <html:form action="TerritorySelectForUpdate.do?getPersonDetails=getPersonDetails" method="post" onsubmit="return validate_form(this)"   >
        <input type="hidden" name="status" value="finish"/>
        <input type="hidden" name="districtid" />
        <input type="hidden" name="district_name" id="districtname"/>
        <input type="hidden" name="mandal_name" id="mandalname"/>
        <input type="hidden" name="village_name" id="villagename"/>
        <input type="hidden" name="panchayat_name" id="panchayatname"/>
        <input type="hidden" name="habitation_name" id="habitationname"/>
        <input type="hidden" name="restrictPartBUpdateByTerr" value="<%=restrictPartA%>"/>
        <center>

            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%" border="1">
                <tr>
                    <th colspan="4" align="center" >Select the Territory For Update</th>
                </tr>
                <tr>
                    <td>
                        <table  align="center" cellspacing="1" cellpadding="0" class="innerTable1" width="100%">
                            <tr id="districtdisplay"> <td align = "center" ><font size="2"> District <font color="red"><b>*</b></font></font></td>
                                <td colspan="3">
                                    <html:select styleId="1" property="district_id" onchange="districtList(this.value)">
                                        <html:option  value="" >--SELECT--</html:option>
                                        <html:optionsCollection   property="districtlist" label="district_name"  value="district_id"  />
                                    </html:select>
                                </td>
                            </tr>

                            <tr id="assemblydisplay"><td align = "center" >Assembly<font color="red"><b>*</b></font></td>
                                <td class="textbox">
                                    <html:select styleId="2" property="assembly_id" onchange="assemblyList()" >
                                        <html:option  value="">--SELECT--</html:option>
                                        <html:optionsCollection   property="assemblylist" label="assembly_name" value="assembly_id"  />
                                    </html:select>
                                </td>
                            </tr>

                            <tr><td align = "center" >Mandal<font color="red"><b>*</b></font></td>
                                <td class="textbox">
                                    <html:select styleId="3" property="mandal_id" onchange="mandalList()" >
                                        <html:option  value="">--SELECT--</html:option>
                                        <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"  />
                                    </html:select>
                                </td>
                                <td align = "center" >Village<font color="red"><b>*</b></font></td>
                                <td class="textbox">
                                    <html:select styleId="4" property="village_id" onchange="villagesList()">
                                        <html:option  value="">--SELECT--</html:option>
                                        <html:optionsCollection   property="villagelist" label="village_name" value="village_id"  />
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td align = "center" >Panchayat<font color="red"><b>*</b></font></td>
                                <td class="textbox">
                                    <html:select styleId="5" property="panchayat_id" onchange="panchayatList()">
                                        <html:option  value="">--SELECT--</html:option>
                                        <html:optionsCollection   property="panchayatlist" label="panchayat_name" value="panchayat_id"  />
                                    </html:select>
                                </td>
                                <td align = "center" >Habitation<font color="red"><b>*</b></font></td>
                                <td class="textbox">
                                    <html:select styleId="6" property="habitation_id">
                                        <html:option  value="">--SELECT--</html:option>
                                        <html:optionsCollection   property="habitationlist" label="habitation_name" value="habitation_id"  />
                                    </html:select>
                                </td>
                            </tr>

                            <tr>
                                <td align = "center" >PersonStatus<font color="red"><b>*</b></font></td>
                                <td class="textbox">
                                    <html:select styleId="7" property="personstatus" onchange="getPersonCodeList(this.value)">
                                        <html:option  value="0">  --SELECT--   </html:option>
                                        <html:option value="Eligible">Eligible</html:option>
                                        <html:option value="Rejected">Rejected</html:option>
                                    </html:select>
                                </td>
                                <td align = "center" >Personcode<font color="red"><b>*</b></font></td>
                                <td class="textbox">
                                    <html:select styleId="8" property="personcode" >
                                        <html:option  value="">--SELECT--</html:option>
                                        <html:optionsCollection property="personcodelist" label="personcode" value="personcode" />
                                    </html:select>
                                </td></tr>

                            <!--    <tr><td></td><td><b><font size="2">OR</font></b></td><tr>
                                <tr>
                   <tr class="tbl_bg2_content_hdr_new"> <font size="2"><td><b>Enter Personcode</font></td><td colspan="3">
                                      <input type="text"  maxlength="17" size="17" name="enterpersoncode" />
                                       </td>
                                   </tr>-->

                        </table>
                    </td>
                </tr>
                <tr>
                    <th colspan="4" align = "center"><input type="Submit" value="Submit" class="button" ></th>
                </tr>

            </table>

        </center>
    </html:form>


</body>
</html:html>
