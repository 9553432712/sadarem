<%@page contentType="text/html"%>    
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
<head>
<script language="javascript">
     function displayFields(val)
            {
                
                hideFields();
                removeWhenNoElementSelected(10,14);
                document.getElementById("newterritory").value="";
                
                switch(val)
                {
                    case "1": showFields(1,2);
                              break;
                    case "2": showFields(1,3);
                              createDistrictObject();
                              break;
                    case "3": showFields(1,3);
                              createDistrictObject();
                              break;
                    case "4": showFields(1,4);
                              createDistrictObject();
                              break;
                    case "5": showFields(1,4);
                              createDistrictObject();
                              break;
                    case "6": showFields(1,7);
                              createDistrictObject();
                              break;
                }
                
            }
            function showFields(initial,final)
            {
             for(var i=initial;i<=final;i++)
             {
                
                document.getElementById(i).style.display="";
                document.getElementById(i).style.visible="true";
             }
            
            }
            
            function hideFields()
            {
             for(var i=1;i<=7;i++)
             {
                
                document.getElementById(i).style.display="none";
                document.getElementById(i).style.visible="hidden";
             }
            
            }
            
            function removeWhenNoElementSelected(start,end)
            {
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
                
                x=new ActiveXObject("Msxml2.XMLHTTP");
                x.onreadystatechange=getDistrictDetails;
                
                var url="TerritoyInformationAction.do?parameter=getTerritoryList&territory=1";
                
                x.open("Get", url, true);
                x.send();
            }

            function getDistrictDetails()
            {
           
                   
            var res1,res2;
            removeall("districtid");
            if(x.readyState==4)
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
                addoption(res1,res2,"districtid");
                z++;
                }
            }
            }
            
            function createMandalObject()
            {
                
                x=new ActiveXObject("Msxml2.XMLHTTP");
                x.onreadystatechange=getMandalDetails;
                
                var did=document.getElementById("districtid").value;
                var url="TerritoyInformationAction.do?parameter=getTerritoryList&districtid="+did+"&territory=2";
                
                x.open("Get", url, true);
                x.send();
            }

            function getMandalDetails()
            {
            
             var res1,res2;
             removeall("mandalid");
             if(x.readyState==4)
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
                addoption(res1,res2,"mandalid");
                z++;
                }
            }
            }
            
            function createVillageObject()
            {
                
                x=new ActiveXObject("Msxml2.XMLHTTP");
                x.onreadystatechange=getVillageDetails;
                
                var did=document.getElementById("districtid").value;
                var mid=document.getElementById("mandalid").value;
                var url="TerritoyInformationAction.do?parameter=getTerritoryList&districtid="+did+"&mandalid="+mid+"&territory=5";
                
                x.open("Get", url, true);
                x.send();
            }

            function getVillageDetails()
            {
                        
             var res1,res2;
             removeall("villageid");
             if(x.readyState==4)
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
                addoption(res1,res2,"villageid");
                z++;
                }
            }
            }
            
            function createAssemblyObject()
            {
                
                x=new ActiveXObject("Msxml2.XMLHTTP");
                x.onreadystatechange=getAssemblyDetails;
                
                var did=document.getElementById("districtid").value;
                var url="TerritoyInformationAction.do?parameter=getTerritoryList&districtid="+did+"&territory=3";
                
                x.open("Get", url, false);
                x.send();
            }

            function getAssemblyDetails()
            {
                        
             var res1,res2;
             removeall("assemblyid");
             if(x.readyState==4)
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
                addoption(res1,res2,"assemblyid");
                z++;
                }
            }
            }
            
            function createPanchayatObject()
            {
                
                x=new ActiveXObject("Msxml2.XMLHTTP");
                x.onreadystatechange=getPanchayatDetails;
               
                var did=document.getElementById("districtid").value;
                var mid=document.getElementById("mandalid").value;
                var url="TerritoyInformationAction.do?parameter=getTerritoryList&districtid="+did+"&mandalid="+mid+"&territory=4";
                
                x.open("Get", url, false);
                x.send();
            }

            function getPanchayatDetails()
            {
                        
             var res1,res2;
             removeall("panchayatid");
             if(x.readyState==4)
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
                addoption(res1,res2,"panchayatid");
                z++;
                }
            }
            }
            
            function removeall(name)
            {
                var x1=document.getElementById(name).length;
                for(i=x1;i>0;i--)
                document.getElementById(name).options[i]=null;
                document.getElementById(name).value="";
            }
            function addoption(result1,result2,name)
            {
                var opt=document.createElement("OPTION");
                opt.text=result1;
                opt.value=result2;
                document.getElementById(name).options.add(opt);
            }
            
            function districtWhichtoInvoke()
            {
                var decide=document.getElementById("addwhom").value;
                var distval=document.getElementById("districtid").value;
                if(distval=="")
                {
                removeWhenNoElementSelected(11,14);
                document.getElementById("newterritory").value="";
                }
                else
                {
                if(decide=="3")
                {
                removeWhenNoElementSelected(11,14);
                document.getElementById("newterritory").value="";
                }
                else if(decide=="6")
                {
                removeWhenNoElementSelected(11,14);
                document.getElementById("newterritory").value="";
                createAssemblyObject();
                createMandalObject();
                }
                else
                {
                removeWhenNoElementSelected(11,14);
                document.getElementById("newterritory").value="";
                createMandalObject();
                }
                }
            }
            
            function mandalWhichtoInvoke()
            {
                var decide=document.getElementById("addwhom").value;
                var mandval=document.getElementById("mandalid").value;
                if(mandval=="")
                {
                removeWhenNoElementSelected(12,12);
                removeWhenNoElementSelected(14,14);
                document.getElementById("newterritory").value="";
                }
                else
                {
                if(decide=="4")
                {
                removeWhenNoElementSelected(12,14);
                document.getElementById("newterritory").value="";
                               
                }
                else if(decide=="6")
                {
                removeWhenNoElementSelected(12,12);
                removeWhenNoElementSelected(14,14);
                document.getElementById("newterritory").value="";
                createPanchayatObject();
                createVillageObject();
                }
                else
                {
                removeWhenNoElementSelected(12,14);
                document.getElementById("newterritory").value="";
                createVillageObject();
                }
                }
            }
            
            function villageWhichtoInvoke()
            {
                var decide=document.getElementById("addwhom").value;
                var villageval=document.getElementById("villageid").value;
                if(villageval=="")
                {
                document.getElementById("newterritory").value="";
                }
                
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
                trimFormfields(thisform);
                with (thisform)
                {
                    var territory=document.getElementById("addwhom").value;
                    
                    
                    if(territory>1)
                    {
                    
                    if (validate_required(districtid,"Select a District Name")==false)
                    {
                    districtid.focus();
                    return false
                    }
                    }
                    
                    if(territory==6)
                    {
                    if (validate_required(assemblyid,"Select a Assembly Name")==false)
                    {
                    assemblyid.focus();
                    return false
                    }
                    }
                                        
                    if(territory==5||territory==6||territory==4)
                    {
                    if (validate_required(mandalid,"Select a Mandal Name")==false)
                    {
                    mandalid.focus();
                    return false
                    }
                    }
                    
                    if(territory==6)
                    {                    
                    if (validate_required(panchayatid,"Select a Panchayat Name")==false)
                    {
                    panchayatid.focus();
                    return false
                    }
                    }
                    
                    if(territory==6)
                    {
                    if (validate_required(villageid,"Select a Village Name")==false)
                    {
                    villageid.focus();
                    return false
                    }
                    }
               
                    if (validate_required(newterritory,"New Name cannot be empty")==false)
                    {
                    newterritory.focus();
                    return false
                    }
                    
                    if(!isNaN(document.getElementById("newterritory").value))
                    {
                    alert('New name cannot be a number');
                    document.getElementById("newterritory").value="";
                    newterritory.focus();
                    return false
                    }
                    
                }
            }
            
            function trimFormfields(thisform)
            {
                
                 thisform.newterritory.value=trim(thisform.newterritory.value," ");
            }
            function trim(str, chars) 
            {
            return ltrim(rtrim(str, chars), chars);
            }

            function ltrim(str, chars) 
            {
            chars = chars || "\\s";
            return str.replace(new RegExp("^[" + chars + "]+", "g"), "");
            }

            function rtrim(str, chars) 
            {
            chars = chars || "\\s";
            return str.replace(new RegExp("[" + chars + "]+$", "g"), "");
            }
            function alpha(e) {
            var k;
            document.all ? k = e.keyCode : k = e.which;
            return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k==32);
            }
    
</script>
</head>
<body >
    <html:form action="TerritoyInformationAction.do?parameter=insertTerritoryInformation" styleId="TerritoryInformationForm" method="post" onsubmit="return validate_form(this)">
        <br><br><br><br>
        <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="50%">
            <tr>
                <td align="center" style="font-weight:bold" colspan="2" class="heading">Territory Add Module</td>
            </tr>
        </table>
        <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="50%">
    <tr><td class="label">
         Add</td><td>
        <html:select property="addwhom" onchange="displayFields(this.value)" >
            
            <html:option value="0">-select-</html:option>
            <html:option value="1">District</html:option>
            <html:option value="2">Mandal</html:option>
            <html:option value="3">Assembly</html:option>
            <html:option value="4">Panchayat</html:option>
            <html:option value="5">Village</html:option>
            <html:option value="6">Habitation</html:option>
                
        </html:select>
  </td></tr>
  
        <tr id="3" style="visible:hidden;display:none"><td class="label">
       
        District Name</td><td>
        <html:select styleId="10" property="districtid"  onchange="districtWhichtoInvoke()" style="width: 120px">
            <html:option value="">-select-</html:option>
        </html:select>
        
        </td></tr>
        
        <tr id="6" style="visible:hidden;display:none"><td class="label">
        
        Assembly Name</td><td>
        <html:select styleId="13" property="assemblyid" style="width: 135px">
            <html:option value="">-select-</html:option>
        </html:select>
        
        </td></tr>
        
        <tr id="4" style="visible:hidden;display:none"><td class="label">
        
        Mandal Name</td><td>
        <html:select styleId="11" property="mandalid" onchange="mandalWhichtoInvoke()" style="width: 205px">
            <html:option value="">-select-</html:option>
        </html:select>
        
        </td></tr>
        
        
        <tr id="7"  style="visible:hidden;display:none"><td class="label">
        
        Panchayat Name</td><td>
        <html:select styleId="14" property="panchayatid" style="width: 220px">
            <html:option value="">-select-</html:option>
        </html:select>
        
        </td></tr>
        
        <tr id="5" style="visible:hidden;display:none"><td class="label">
       
        Village Name</td><td>
        <html:select styleId="12" property="villageid" onchange="villageWhichtoInvoke()" style="width: 220px">
            <html:option value="">-select-</html:option>
        </html:select>
        
        </td></tr>
        
        <tr id="2" style="visible:hidden;display:none"><td class="label">
        
                Add Name to</td><td><html:text styleId="15" property="newterritory" onkeypress="return alpha(event)"/>
       
        </td></tr>
        
        <tr id="1" style="visible:hidden;display:none" ><td align="center" colspan="2">
        
                <html:submit value="Add" styleClass="button"/>
        
        </td></tr>
        
        
        
        
        
        </table>
    </html:form>
    </body>
</html:html>

