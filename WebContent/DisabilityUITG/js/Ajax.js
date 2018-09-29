/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function addoption(result1,result2,name)
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
        }else  if(name=="village_id") {
            document.forms[0].village_id.appendChild(opt,null);
        }else  if(name=="panchayat_id") {
            document.forms[0].panchayat_id.appendChild(opt,null);
        }

    }
}
function removeall(name)
{
    if(name=="mandal_id") {
        var x1=document.forms[0].mandal_id.options.length;
    }
    else if(name=="district_id") {
        var x1=document.forms[0].district_id.options.length;
    }else if(name=="village_id") {
        var x1=document.forms[0].village_id.options.length;
    }else if(name=="panchayat_id") {
        var x1=document.forms[0].panchayat_id.options.length;
    }

    for(i=x1;i>0;i--) {
        if(name=="mandal_id") {
            document.forms[0].mandal_id.options[i]=null;
        } else if(name=="district_id") {
            document.forms[0].district_id.options[i]=null;
        }else if(name=="village_id") {
            document.forms[0].village_id.options[i]=null;
        }else if(name=="panchayat_id") {
            document.forms[0].panchayat_id.options[i]=null;
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

function removeLists(start,end){

    for(k=start;k<=end;k++)
    {
        var x1=document.getElementById(k).length;
        for(i=x1;i>1;i--)
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

    var disid =  document.forms[0].district_id.value;
    if(disid == ""){
        removeLists(2,3);
    }else{
        createMandalObject();
    }
}

function  createMandalObject()
{

    x=GetXmlHttpObject()
    x.onreadystatechange=getMandalDetails;
    var distid= document.forms[0].district_id.value;
    if(document.forms[0].urban_id.value=='Urban'){
        var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&territory=9";

    }else if(document.forms[0].urban_id.value=='Rural'){
        var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&territory=10";
    }
    else{
        var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&territory=2";
    }
    x.open("GET",url,true)
    x.send();
}


function districtNonUrbanList(){

    var disid =  document.forms[0].district_id.value;
    if(disid == ""){
        removeLists(2,3);
    }else{
        createNonUrbanMandalObject();
    }
}

function  createNonUrbanMandalObject()
{

    x=GetXmlHttpObject()
    x.onreadystatechange=getMandalDetails;
    var distid= document.forms[0].district_id.value;

    var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&territory=2";

    x.open("GET",url,true)
    x.send();
}
function getMandalDetails()
{
    var rs1,rs2;
    removeall("mandal_id");
    document.forms[0].mandal_id.value="0";

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

    var manid= document.forms[0].mandal_id.value;
    if(manid==""){
        removeLists(4,6);
    }else{

        createVillageObject();
    }
}

function  createVillageObject(list)
{
    val=list;
    var distid;
    x=GetXmlHttpObject()
    x.onreadystatechange=getVillageDetails;
    distid= document.forms[0].district_id.value;
    if(distid == "" &&  document.forms[0].districtid.value!=null){
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

function mandalPanchayatList(){

    var manid= document.forms[0].mandal_id.value;
    if(manid==""){
        removeLists(4,6);
    }else{

        createPanchayatVillageObject();
    }
}

function  createPanchayatVillageObject()
{
    var distid;
    x=GetXmlHttpObject()
    x.onreadystatechange=getVillageDetails;
    distid= document.forms[0].district_id.value;
    if(distid == ""){
        distid = document.forms[0].district_id.value;
    }
    var mandid= document.forms[0].mandal_id.value;
    var pancid= document.forms[0].panchayat_id.value;
    var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&mandalid="+mandid+"&panchayatid="+pancid+"&territory=7";
    x.open("GET",url,true)
    x.send();
}
function getPanchayatVillageDetails()
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
function  createPanchayatObject()
{
    var distid;
    x=GetXmlHttpObject()
    x.onreadystatechange=getPanchayatDetails;
    distid= document.forms[0].district_id.value;
    if(distid == ""){
        distid = document.forms[0].district_id.value;
    }
    var mandid= document.forms[0].mandal_id.value;
    var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&mandalid="+mandid+"&territory=4";
    x.open("GET",url,true)
    x.send();
}
function getPanchayatDetails()
{
    var rs1,rs2;
    removeall("panchayat_id");
    document.forms[0].panchayat_id.value="0";

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
function panchList(){

    var vilid= document.forms[0].panchayat_id.value;
    if(vilid==""){
        removeLists(4,6);
    }else{

        createVillageObject();
    }
}
function selectedNames()
{
    var slcBx1 = document.getElementById("1");
    var slcBx2 = document.getElementById("2");
    var slcBx3 = document.getElementById("3");
    var slcBx4 = document.getElementById("4");

    document.getElementById("districtName").value = slcBx1.options[slcBx1.selectedIndex].text;
    document.getElementById("mandalName").value = slcBx2.options[slcBx2.selectedIndex].text;
    document.getElementById("villageName").value = slcBx3.options[slcBx3.selectedIndex].text;
    document.getElementById("habitationName").value = slcBx4.options[slcBx4.selectedIndex].text;

}

function disabileDistrict()
{
    var x=document.forms[0].district_id.value;
    if(x == 0)
    {
        document.forms[0].mandal_id.disabled = true;
        document.forms[0].village_id.disabled = true;

    }
    else
    {
        document.forms[0].mandal_id.disabled = false;
        document.forms[0].village_id.disabled = true;
        document.forms[0].village_id.value="0";
    }

}

function disabileMandal()
{
    var x=document.forms[0].mandal_id.value;
    if(x == 0)
    {
        document.forms[0].village_id.value="0";
        document.forms[0].village_id.disabled = true;

    }
    else
    {
        document.forms[0].village_id.disabled = false;
        document.forms[0].village_id.value="0";
    }

}

function droupDownOptions()
            {

                var z=document.forms[0].typeofdisability.value;
                var select=document.forms[0].reportcategory;
                var options=select.getElementsByTagName("option");
                var i;
                for (i=options.length-1; i>0; i--)

                {
                    select.removeChild(options[i]);
                }

                if(z == "" || z == 1 || z == 2 || z == 3 || z == 4 || z == 6)
                {
                        var newOption1 = document.createElement('option');

                        newOption1.text = "Medical Intervention";
                        newOption1.value="1";
                        try
                        {
                            // for IE earlier than version 8
                            document.forms[0].reportcategory.add(newOption1,select.options[null]);
                        }
                        catch (e)
                        {
                           document.forms[0].reportcategory.add(newOption1,null);
                        }
                        var newOption2 =  document.createElement('option');

                        newOption2.text = "Assistive Devices";
                        newOption2.value="2";
                        try
                        {
                            // for IE earlier than version 8
                            document.forms[0].reportcategory.add(newOption2,select.options[null]);
                        }
                        catch (e)
                        {
                            document.forms[0].reportcategory.add(newOption2,null);
                        }
                        var newOption3 =document.createElement('option');

                        newOption3.text = "Other Needs";
                        newOption3.value="3";
                        try
                        {
                            // for IE earlier than version 8
                            document.forms[0].reportcategory.add(newOption3,select.options[null]);
                        }
                        catch (e)
                        {
                            document.forms[0].reportcategory.add(newOption3,null);
                        }
                    }

                    if(z == 5)
                    {

                        var newOption1 =document.createElement('option');
                        newOption1.value="1";
                        newOption1.text = "Medical Intervention";
                        try
                        {
                            // for IE earlier than version 8
                           document.forms[0].reportcategory.add(newOption1,select.options[null]);
                        }
                        catch (e)
                        {
                            document.forms[0].reportcategory.add(newOption1,null);
                        }
                        var newOption2 = document.createElement('option');
                        newOption2.value="3";
                        newOption2.text = "Other Needs";
                        try
                        {
                            // for IE earlier than version 8
                           document.forms[0].reportcategory.add(newOption2,select.options[null]);
                        }
                        catch (e)
                        {
                           document.forms[0].reportcategory.add(newOption2,null);
                        }
                    }
                    disabileSubCategory();

                }

                function droupDownSubCategoryOptions()
                {
                    var z=document.forms[0].typeofdisability.value;
                    var select=document.forms[0].reportSubcategory;

                var options=select.getElementsByTagName("option");
                var i;
                for (i=options.length-1; i>0; i--)

                {
                    select.removeChild(options[i]);
                }

                if(z == 1 )
                {
                        var newOption1 = document.createElement('option');
                        newOption1.value="1";
                        newOption1.text = "Prosthesis";
                        try
                        {
                            // for IE earlier than version 8
                            document.forms[0].reportSubcategory.add(newOption1,select.options[null]);
                        }
                        catch (e)
                        {
                            document.forms[0].reportSubcategory.add(newOption1,null);
                        }
                        var newOption2 = document.createElement('option');
                        newOption2.value="2";
                        newOption2.text = "Orthosis";
                        try
                        {
                            // for IE earlier than version 8
                            document.forms[0].reportSubcategory.add(newOption2,select.options[null]);
                        }
                        catch (e)
                        {
                           document.forms[0].reportSubcategory.add(newOption2,null);
                        }
                        var newOption3 =document.createElement('option');
                        newOption3.value="3";
                        newOption3.text = "Mobility Aids";
                        try
                        {
                            // for IE earlier than version 8
                          document.forms[0].reportSubcategory.add(newOption3,select.options[null]);
                        }
                        catch (e)
                        {
                           document.forms[0].reportSubcategory.add(newOption3,null);
                        }
                        var newOption4 = document.createElement('option');
                        newOption4.value="4";
                        newOption4.text = "Walking Aids";
                        try
                        {
                            // for IE earlier than version 8
                            document.forms[0].reportSubcategory.add(newOption4,select.options[null]);
                        }
                        catch (e)
                        {
                           document.forms[0].reportSubcategory.add(newOption4,null);
                        }
                        var newOption5 = document.createElement('option');
                        newOption5.value="5";
                        newOption5.text = "ADL Equipments";
                        try
                        {
                            // for IE earlier than version 8
                            document.forms[0].reportSubcategory.add(newOption5,select.options[null]);
                        }
                        catch (e)
                        {
                           document.forms[0].reportSubcategory.add(newOption5,null);
                        }
                    }

            }