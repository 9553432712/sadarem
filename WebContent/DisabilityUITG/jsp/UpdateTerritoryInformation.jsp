<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head><br><br><br><br>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script language="javascript">
            var x;
            
            function displayFields(val)
            {
                
                hideFields();
                removeWhenNoElementSelected(10,15);
                document.getElementById("modifiedname").value="";
                
                switch(val)
                {
                    case "1": showFields(1,3);
                              createDistrictObject();  
                              break;
                    case "2": showFields(1,4);
                              createDistrictObject();  
                              break;
                    case "3": showFields(1,3);
                              document.getElementById("7").style.display="";
                              document.getElementById("7").style.visible="true";
                              createDistrictObject();  
                              break;
                    case "4": showFields(1,4);
                              document.getElementById("8").style.display="";
                              document.getElementById("8").style.visible="true";
                              createDistrictObject();
                              break;
                    case "5": showFields(1,5);
                              createDistrictObject();  
                              break;
                    case "6": showFields(1,6);
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
             for(var i=1;i<=8;i++)
             {
                
                document.getElementById(i).style.display="none";
                document.getElementById(i).style.visible="hidden";
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
            
            function createAssemblyObject()
            {
                
                x=new ActiveXObject("Msxml2.XMLHTTP");
                x.onreadystatechange=getAssemblyDetails;
                
                var did=document.getElementById("districtid").value;
                var url="TerritoyInformationAction.do?parameter=getTerritoryList&districtid="+did+"&territory=3";
                
                x.open("Get", url, true);
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
                
                x.open("Get", url, true);
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
            
            function createVillageObject()
            {
                
                x=new ActiveXObject("Msxml2.XMLHTTP");
                x.onreadystatechange=getVillageDetails;
                var xx="getVillageList";
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
            
            function createHabitationObject()
            {
                
                x=new ActiveXObject("Msxml2.XMLHTTP");
                x.onreadystatechange=getHabitationDetails;
                var xx="getHabitationList";
                var did=document.getElementById("districtid").value;
                var mid=document.getElementById("mandalid").value;
                var vid=document.getElementById("villageid").value;
                var url="TerritoyInformationAction.do?parameter=getTerritoryList&districtid="+did+"&mandalid="+mid+"&villageid="+vid+"&territory=6";
                
                x.open("Get", url, true);
                x.send();
            }

            function getHabitationDetails()
            {
                        
             var res1,res2;
             removeall("habitationid");
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
                addoption(res1,res2,"habitationid");
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
                var decide=document.getElementById("modifywhom").value;
                var distval=document.getElementById("districtid").value;
                if(distval==""||distval==0)
                {
                removeWhenNoElementSelected(11,15);
                document.getElementById("modifiedname").value="";
                }
                else
                {
                if(decide=="1")
                getSelectedItemName("districtid");
                else if(decide=="3")
                {
                removeWhenNoElementSelected(11,15);
                document.getElementById("modifiedname").value="";
                createAssemblyObject();
                }
                else
                {
                removeWhenNoElementSelected(11,15);
                document.getElementById("modifiedname").value="";
                createMandalObject();
                }
                }
            }
            
            function mandalWhichtoInvoke()
            {
                var decide=document.getElementById("modifywhom").value;
                var mandval=document.getElementById("mandalid").value;
                if(mandval==""||mandval=="0")
                {
                removeWhenNoElementSelected(12,15);
                document.getElementById("modifiedname").value="";
                }
                else
                {
                if(decide=="2")
                getSelectedItemName("mandalid");
                else if(decide=="4")
                {
                removeWhenNoElementSelected(12,15);
                document.getElementById("modifiedname").value="";
                createPanchayatObject();
                
                }
                else
                {
                removeWhenNoElementSelected(12,15);
                document.getElementById("modifiedname").value="";
                createVillageObject();
                }
                }
            }
            
            function villageWhichtoInvoke()
            {
                var decide=document.getElementById("modifywhom").value;
                var villageval=document.getElementById("villageid").value;
                if(villageval==""||villageval=="0")
                {
                removeWhenNoElementSelected(13,15);
                document.getElementById("modifiedname").value="";
                }
                else
                {
                if(decide=="5")
                {
                removeWhenNoElementSelected(13,15);
                document.getElementById("modifiedname").value="";
                getSelectedItemName("villageid");
                }
                if(decide=="6")
                {
                removeWhenNoElementSelected(13,15);
                document.getElementById("modifiedname").value="";
                createHabitationObject();
                }
                }
            }
            
            function getSelectedItemName(territory)
            {
                var x=document.getElementById(territory);
                var selected=document.getElementById(territory).selectedIndex;
                if(selected!=""||selected=="0")
                {
                for (i=0;i<x.length;i++)
                {
                    if(i==selected)
                    document.getElementById("modifiedname").value=x.options[i].text;
               
                }
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
        </script>
        <script language="javascript">
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
                    var territory=document.getElementById("modifywhom").value;
                    
                    
                    if(territory!=0)
                    {
                    if (validate_required(districtid,"Select a District Name")==false)
                    {
                    districtid.focus();
                    return false
                    }
                    }
                    
                    
                                        
                    if(territory==2||territory==5||territory==6||territory==4)
                    {
                    if (validate_required(mandalid,"Select a Mandal Name")==false)
                    {
                    mandalid.focus();
                    return false
                    }
                    }
                    
                    
                    
                    if(territory==5||territory==6)
                    {
                    if (validate_required(villageid,"Select a Village Name")==false)
                    {
                    villageid.focus();
                    return false
                    }
                    }
                    
                    if(territory==3)
                    {
                    if (validate_required(assemblyid,"Select a Assembly Name")==false)
                    {
                    assemblyid.focus();
                    return false
                    }
                    }
                    
                    if(territory==4)
                    {                    
                    if (validate_required(panchayatid,"Select a Panchayat Name")==false)
                    {
                    panchayatid.focus();
                    return false
                    }
                    }
                    
                    if(territory==6)
                    {
                    if (validate_required(habitationid,"Select a Habitation Name")==false)
                    {
                    habitationid.focus();
                    return false
                    }
                    }
                    
                    if (validate_required(modifiedname,"Modified Name cannot be empty")==false)
                    {
                    modifiedname.focus();
                    return false
                    }
                    
                    if(!isNaN(document.getElementById("modifiedname").value))
                    {
                    alert('Modified name cannot be a number');
                    document.getElementById("modifiedname").value="";
                    modifiedname.focus();
                    return false
                    }
                    
                }
            }
            
            function trimFormfields(thisform)
            {
                
                 thisform.modifiedname.value=trim(thisform.modifiedname.value," ");
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
        </script>
    </head>
    <body >
    <html:form action="TerritoyInformationAction.do?parameter=updateTerritoryInformation" method="post" onsubmit="return validate_form(this)">
        <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="33%">
            <tr>
                <td align="center" style="font-weight:bold" colspan="2" class="heading">Territory Update Module</td>
            </tr>
        </table>
        <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="33%">
    <tr><td class="label">
         Modify</td><td>
        <html:select property="modifywhom" onchange="displayFields(this.value)">
            
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
        <html:select styleId="10" property="districtid" onchange="districtWhichtoInvoke()" style="width: 120px">
            <html:option value="">-select-</html:option>
        </html:select>
        
        </td></tr>
        
        <tr id="4" style="visible:hidden;display:none"><td class="label">
        
        Mandal Name</td><td>
        <html:select styleId="11" property="mandalid" onchange="mandalWhichtoInvoke()" style="width: 205px">
            <html:option value="">-select-</html:option>
        </html:select>
        
        </td></tr>
        
        <tr id="7" style="visible:hidden;display:none"><td class="label">
        
        Assembly Name</td><td>
        <html:select styleId="14" property="assemblyid" onchange="getSelectedItemName(this.name)" style="width: 135px">
            <html:option value="">-select-</html:option>
        </html:select>
        
        </td></tr>
        
        <tr id="8"  style="visible:hidden;display:none"><td class="label">
        
        Panchayat Name</td><td>
        <html:select styleId="15" property="panchayatid" onchange="getSelectedItemName(this.name)" style="width: 220px">
            <html:option value="">-select-</html:option>
        </html:select>
        
        </td></tr>
        
        <tr id="5" style="visible:hidden;display:none"><td class="label">
       
        Village Name</td><td>
        <html:select styleId="12" property="villageid" onchange="villageWhichtoInvoke()" style="width: 220px">
            <html:option value="">-select-</html:option>
        </html:select>
        
        </td></tr>
        
        <tr id="6" style="visible:hidden;display:none"><td class="label">
        
        Habitation Name</td><td>
        <html:select styleId="13" property="habitationid" onchange="getSelectedItemName(this.name)" style="width: 270px">
            <html:option value="">-select-</html:option>
        </html:select>
        
        </td></tr>
        
        <tr id="2" style="visible:hidden;display:none"><td class="label">
        
        Modify Name to</td><td><html:text styleId="16" property="modifiedname"/>
       
        </td></tr>
        
        <tr id="1" style="visible:hidden;display:none" ><td align="center" colspan="2">
        
                <html:submit value="Update" styleClass="button"/>
        
        </td></tr>
        
        </table>
    </html:form>
    </body>
</html>
