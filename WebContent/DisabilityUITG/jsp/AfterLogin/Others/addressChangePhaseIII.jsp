<%--
    Document   : issueRaiseApproval
    Created on : Nov 28, 2012, 1:20:33 PM
    Author     : 693461
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page  import="java.util.Iterator"%>
<%@page  import="java.util.ArrayList"%>
<%@page  import="org.bf.disability.dto.IssueRaiseApprovalDTO"%>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script src="./DisabilityUITG/js/modalpopup.js" type="text/javascript"></script>
        <script src="./DisabilityUITG/js/jquery-min.js" type="text/javascript"></script>

        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
        <script language="javascript" src="./DisabilityUITG/css/jqueryslidemenu.js"></script>
        <script language="javascript" src="./DisabilityUITG/css/jquery.min.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/PartADetailsExistingPensionNumber.js"></script>
        <script>
            function addoption(result1,result2,name)
            {
                var opt=document.createElement("option");
                opt.text=result1;
                opt.value=result2;
                try {
                    document.getElementById(name).add(opt);
                }catch(ex)
                {
                    if(name=="mandal_id") {
                        document.forms[0].mandal_id.appendChild(opt,null);
                    }else if(name=="village_id") {
                        document.forms[0].village_id.appendChild(opt,null);
                    }else if(name=="habitation_id") {
                        document.forms[0].habitation_id.appendChild(opt,null);
                    }else if(name=="panchayat_id") {
                        document.forms[0].panchayat_id.appendChild(opt,null);
                    }
                }
            }
            function removeall(name)
            {
                if(name=="mandal_id") {
                    var x1=document.forms[0].mandal_id.options.length;
                }else if(name=="village_id") {
                    var x1=document.forms[0].village_id.options.length;
                }else if(name=="habitation_id") {
                    var x1=document.forms[0].habitation_id.options.length;
                }else if(name=="panchayat_id") {
                    var x1=document.forms[0].panchayat_id.options.length;
                }
                for(i=x1;i>0;i--) {
                    if(name=="mandal_id") {
                        document.forms[0].mandal_id.options[i]=null;
                    }else if(name=="village_id") {
                        document.forms[0].village_id.options[i]=null;
                    } else if(name=="habitation_id") {
                        document.forms[0].habitation_id.options[i]=null;
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

            function getPanchayatList() {
                createPanchayatObject();
            }
            var x = null;
            function  createPanchayatObject()
            {
                x = GetXmlHttpObject()
                x.onreadystatechange=getPanchayatDetails;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+document.forms[0].district_id.value+"&mandalid="+document.forms[0].mandal_id.value+"&territory=4";
                x.open("GET",url,true)
                x.send();
            }

            function getPanchayatDetails()
            {
                var rs1,rs2;
                removeall("panchayat_id");
                removeall("habitation_id");
                removeall("village_id");
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
                var villageid = document.forms[0].panchayat_id.value;
                var slcBx = document.getElementById("3");
                document.getElementById("panchayatname").value = slcBx.options[slcBx.selectedIndex].text;
                if(villageid == ""){
                    removeLists(6,6);
                }else{
                    createHabitationObject();
                }
            }

            function  createVillageObject()
            {
                //var distid;
                x=GetXmlHttpObject()
                x.onreadystatechange=getVillageDetails;
                var pan=document.forms[0].panchayat_id.value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+document.forms[0].district_id.value+"&mandalid="+document.forms[0].mandal_id.value+"&panchayatid="+pan+"&territory=7";
                x.open("GET",url,true)
                x.send();
            }

            function getVillageDetails()
            {
                var rs1,rs2;
                removeall("village_id");
                // removeall("panchayat_id");
                //    removeall("habitation_id");
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
                var villageid = document.forms[0].village_id.value;
                var slcBx = document.getElementById("4");
                document.getElementById("villagename").value = slcBx.options[slcBx.selectedIndex].text;
                if(villageid == ""){
                    removeLists(5,6);
                }else{
                    createPanchayatObject();
                }
            }






            function  createHabitationObject()
            {
                x=GetXmlHttpObject()
                x.onreadystatechange=getHabitationDetails;

                var villageid = document.forms[0].village_id.value;
                var panchayat = document.forms[0].panchayat_id.value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+document.forms[0].district_id.value+"&mandalid="+document.forms[0].mandal_id.value+"&villageid="+villageid+"&panchayatid="+panchayat+"&territory=11";

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
        </script>
        <script>
            <%--function createDistrictObject()
            {

                x=GetXmlHttpObject();
                x.onreadystatechange=getDistrictDetails;
                var url="getTerritory.do?parameter=getTerritoryList&territory=1";
                x.open("Get", url, true);
                x.send();
            }

            function getDistrictDetails()
            {

                var res1,res2;

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
                    }
                }

            }--%>
                function getIsseRaiseDetails() {

                    if(document.forms[0].elements['categoryFormId'].value=="0") {
                        alert("Please Select Category!");
                        document.forms[0].elements['categoryFormId'].focus();
                        return false;
                    }
                    if(document.forms[0].elements['sadaremId'].value=="") {
                        alert("Please Enter SADAREM ID!");
                        document.forms[0].elements['sadaremId'].focus();
                        return false;
                    }
                    if(document.forms[0].elements['mobile'].value=="") {
                        alert("Please Enter Mobile!");
                        document.forms[0].elements['mobile'].focus();
                        return false;
                    }

                    if(document.forms[0].elements['decription'].value=="") {
                        alert("Please Enter Decription!");
                        document.forms[0].elements['decription'].focus();
                        return false;
                    }
                    
                    if(document.forms[0].elements['categoryFormId'].value=="35") {
                        if(document.forms[0].habitation_id.value=="0"){
                            alert("Please Select Address\n Mandal\n Panchayat\n Village\n Habitation");
                            document.forms[0].habitation_id.focus();
                            return false;
                        }
                      //   alert("Note: For Address Change PhaseIII SADAREMID will be Completely Deleted!");
                      alert("Are you sure you want to change your address?");
                    
                    }
                    document.forms[0].action="./issueRaiseApproval.do?mode=insertIssueRaise&flag=1";
                    document.forms[0].submit();

                }

                function getCategoryId(){
                    document.forms[0].mode.value ="getCategoryWiseDetails";
                    document.forms[0].submit();
                }


                function textCounter(field,cntfield,maxlimit) {
                    if (field.value.length > maxlimit){  // if too long...trim it!
                        field.value = field.value.substring(0, maxlimit);
                        // otherwise, update 'characters left' counter
                    }
                    else{
                        cntfield.value = maxlimit - field.value.length;
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
                function isAlpha(keyCode,name){
                    if (keyCode == 16)
                        isShift = true;
                    var str = name.value;
                    if(str.substring(0,1)==" ")
                    {
                        name.value ="";
                        name.focus();
                        return ((keyCode >= 65 && keyCode <= 90) || keyCode == 8 || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46)
                    }else{
                        return ((keyCode >= 65 && keyCode <= 90) || keyCode == 8 || keyCode == 32 || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46)
                    }
                }

                function space(evt,thisvalue)
                {
                    var number = thisvalue.value;
                    var charCode = (evt.which) ? evt.which : event.keyCode
                    if(number.length < 1){
                        if(evt.keyCode == 32) {

                            return false;
                        }
                    }
                    return true;
                }

                function zeroFunction(ids) {

                    var stdCode= document.forms[0].elements[ids].value;
                    if(stdCode.substr(0,1)=="0") {
                        alert("First Digit Should not be ZERO");
                        document.forms[0].elements[ids].value="";
                    }
                }
           
           
                function validMobileNo(){
                    if(document.forms[0].elements["mobile"].value.length < 10) {
                        alert("Mobile Number Should be TEN Digits");
                        document.forms[0].elements["mobile"].value="";
                        document.forms[0].elements["mobile"].focus();
                        return false;
                    }
                }
                function validSADAREMID(){
                    if(document.forms[0].elements["sadaremId"].value.length <17) {
                        alert("SADAREM ID  Should be Seventeen Digits");
                        document.forms[0].elements["sadaremId"].value="";
                        document.forms[0].elements["sadaremId"].focus();
                        return false;
                    }else if(document.forms[0].categoryFormId.value=="35"){
                        document.forms[0].action="./issueRaiseApproval.do?mode=insertIssueRaise&flag=validate";
                        document.forms[0].submit();
                    }
                }

                function allnumeric(inputtxt)
                {
                    var numbers =/^[0-9]+$/;
                    if(inputtxt.value.match(numbers))
                    {
                        //alert('Your Registration number has accepted....');
                        return true;
                    }
                    else
                    {
                        alert('Please input numeric characters only');
                        inputtxt.focus();
                        inputtxt.value="";
                        return false;
                    }

                }

                function onlyNumbersForMobile(evt)
                {
                    var e = event || evt; // for trans-browser compatibility
                    var charCode = e.which || e.keyCode;

                    if (charCode > 31 && (charCode < 48 || charCode > 57))
                        return false;

                    return true;

                }

                function onlystring(event,thisval)
                {
                    if(space(event,thisval)){
                        var e = event || evt || window.event; // for trans-browser compatibility
                        var charCode = e.which || e.keyCode;
                        if (charCode > 31 && ((charCode > 64&&charCode < 91)||(charCode>96&&charCode<123)||charCode==32 )){

                            return true;
                        }
                        return false;

                    }

                    return false;
                }
                function allnumeric(inputtxt)
                {

                    var numbers =/^[0-9]+$/;

                    if(inputtxt.value.match(numbers))
                    {
                        //alert('Your Registration number has accepted....');


                        return true;
                    }
                    else
                    {
                        alert('Please input numeric characters only');
                        inputtxt.focus();
                        inputtxt.value="";
                        return false;
                    }

                }
        </script>

    </head>
    <%-- <body onload="displayHiddenFields();">--%>
    <%try{ %>
    <body>
        <html:form action="/issueRaiseApproval">
            <html:hidden property="mode"/>
            <html:hidden property="district_id"/>
            <html:hidden property="addresschangeSADAREMID"/>


            <logic:present name="msg">
                <p id="succmsg">${msg}</p>
            </logic:present>

            <logic:present name="error">
                <p id="errmsg">${error}</p>
            </logic:present>
            <logic:present name="addressMsg">
                <script>
                    alert('${addressMsg}');
                </script>
            </logic:present>

            <table cellpadding="0" cellspacing="1" align="center" width="90%" border="0" class="inputform">
                <tr>
                    <th colspan="4">
                        Issue Raising Details
                    </th>
                </tr>
                <tr>
                    <td>
                        <table cellpadding="0" cellspacing="0" align="center" width="100%" border="0" >
                            <tr>
                                <td>Category :<font color="red"><b>*</b></font> </td>
                                <td colspan="3">
                                    <html:select styleId="3" property="categoryFormId" onchange="getCategoryId(this);" style="width:320px">
                                        <html:option value="0" >Select</html:option>
                                        <html:optionsCollection property="categoryList" label="categoryname" value="categoryId"/>
                                    </html:select>
                                </td>

                            </tr>

                            <tr>
                                <td id="sadaremId">SADAREM ID :<font color="red"><b>*</b></font></td>
                                <td  id="sadaremId1">
                                    <html:text property="sadaremId" maxlength="17" onchange="validSADAREMID();" onkeypress="return onlyNumbers();" styleId="saId1" style="width=180px"/>
                                </td>
                            </tr>
                            <tr>

                            </tr>
                            <tr>
                                <td>CampIncharge Mobile :<font color="red"><b>* </b></font></td>
                                <td> <html:text property="mobile"  maxlength="10" onkeypress="return onlyNumbers();" onkeyup="zeroFunction('mobile'),allnumeric(this);"  onchange="validMobileNo();" style="width=180px"/>
                                </td>
                            </tr>
                            <tr>

                                <td colspan="4">
                                    <logic:present name="addchange">
                                         <fieldset>
                                            <legend style="font-size: 12px; font-weight: bold">Present Address</legend>
                                        <table cellpadding="0" cellspacing="0" border="1" width="100%">
                                            <tr>
                                                <th>
                                                    District
                                                </th>
                                                <th>
                                                    Mandal
                                                </th>
                                                <th>
                                                    Panchayat
                                                </th>
                                                <th>
                                                    Village
                                                </th>
                                                
                                                <th>
                                                    Habitation
                                                </th> 
                                                <th>
                                                  House No.
                                                </th>
                                                <th>
                                                PIN Code
                                                </th>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <logic:present name="distname">
                                                        <bean:write name="distname"/>
                                                    </logic:present>
                                                </td>
                                                <td>
                                                    <logic:present name="mandalname">
                                                        <bean:write name="mandalname"/>
                                                    </logic:present>
                                                </td>
                                                <td>
                                                    <logic:present name="panchayatname">
                                                        <bean:write name="panchayatname"/>
                                                    </logic:present>
                                                </td>
<td>
                                                    <logic:present name="villagename">
                                                        <bean:write name="villagename"/>
                                                    </logic:present>
                                                </td>

                                                <td>
                                                    <logic:present name="habitationname">
                                                        <bean:write name="habitationname"/>
                                                    </logic:present>
                                                </td>
                                                <td>
                                                    <logic:present name="houseNo">
                                                        <bean:write name="houseNo"/>
                                                    </logic:present>
                                                </td>
                                                <td>
                                                    <logic:present name="pinCode">
                                                        <bean:write name="pinCode"/>
                                                    </logic:present>
                                                </td>
                                            </tr>
                                        </table>
                                             </fieldset>
                                        <br/>
                                        <fieldset>
                                            <legend style="font-size: 12px; font-weight: bold">Address</legend>
                                        <table border="0"  cellspacing="0" cellpadding="0"  class="innterTable1" width="100%">
                                            <tr>
                                                <td>Mandal<font color="red"><b>*</b></font></td>
                                                <td>
                                                    <html:select styleId="7" property="mandal_id" style="width:160px;" onchange="createPanchayatObject();">
                                                        <html:option  value="0">-- Select --</html:option>
                                                        <html:optionsCollection property="mandallist" label="mandal_name" value="mandal_id"  />
                                                    </html:select>
                                                </td>
                                                <td >Panchayat<font color="red"><b>*</b></font> </td>
                                                <td>
                                                    <html:select styleId="3" property="panchayat_id" style="width:160px;" onchange="createVillageObject();">
                                                        <html:option  value="0">-- Select --</html:option>
                                                    </html:select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td >Town/Village<font color="red"><b>*</b></font> </td>
                                                <td>
                                                    <html:select property="village_id" style="width:160px;" onchange="createHabitationObject();">
                                                        <html:option value="0">-- Select --</html:option>
                                                    </html:select>
                                                </td>
                                                <td>Habitation/Ward No<font color="red"><b>*</b></font> </td>
                                                <td>
                                                    <html:select styleId="6" property="habitation_id" style="width:160px;">
                                                        <html:option  value="0">-- Select --</html:option>
                                                    </html:select>
                                                </td>
                                            </tr>
                                          
                                              <tr>
                                             <td>House No.</td>
                                             <td><html:text property="houseNo"></html:text></td>
                                            <td>PIN Code</td>
                                             <td><html:text property="pin" maxlength="6" onkeypress = "return onlyNumbers();"></html:text></td>
                                            </tr>
                                            
                                            
                                        </table>
                                            </fieldset>
                                    </logic:present>
                                </td>
                            </tr>
                            <tr align="left">
                                <td>
                                    Description :<font color="red"><b>* </b></font></td>
                                <td colspan="3" valign="bottom" >   <html:textarea property="decription" onkeypress="return space(event,this),onlystring(event,this);" rows="5" cols="30" onkeydown="textCounter(this,document.forms[0].remLen1,500)" onkeyup="textCounter(this,document.forms[0].remLen1,500)" style="width:320px"/>
                                    <input readonly type="text" name="remLen1"  maxlength="3" value="500" style="width:30px">

                                </td>

                            </tr>
                        </table>
                    </td>
                </tr>


                <tr>
                    <th  align="center" colspan="4">
                        <html:button property="but" value="Submit" onclick="getIsseRaiseDetails();" styleId="but" />
                    </th>

                </tr>

            </table>
            <logic:present name="addressChange">
                <table cellpadding="10" cellspacing="0" border="0" align="center" width="90%">
                    <tr>
<!--                         <td style="color:blue;font-size:14px;text-align: left"><u><b>Note:</b></u><span  style="color:#000;font-size:12px;">&nbsp;For Address Change PhaseIII SADAREMID will be Completely Deleted.</span>&nbsp;&nbsp;</td> -->
                    </tr>
                </table>
            </logic:present>


            <%-- <a href="./issueRaiseApproval.do?mode=rationcardFormat">Here</a>--%>
            <br/>
            <div id="dvPopup" style="display:none;background-color:#fff;width:500px;border:2px #d0d0d0 solid;color:#fff;font-size: 11px;">

                <table cellpadding="0" cellspacing="0" border="0" align="center" width="100%">
                    <tr><td style="background-color:#483D8B;color:#ffffff;padding:5px;text-align: center;font-size:18px;font-weight:bold;">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Information
                        </td>

                    </tr>
                    <tr>
                        <td  style="background-color:#fff;line-height: 18px;color:black;padding:15px;text-align:left;font-size:11px;font-weight:bold;">
                            <ul style="list-style-type: none">
                                <li> Please verify the card details in the following URL when ever you get ration card details not available.</li>
                                <li> http://epds.nic.in</li>
                                <li>  Path : Query --> BPL Rationcard History</li>
                                <li>  If the details were present over there,</li>
                                <li>   Please raise request with details in below specified Rationcard format.</li>
                            </ul>
                        </td>
                    </tr>

                    <tr>
                        <td align="center" style="background-color:white;color:white;padding:10px;"">
                            <input type="button" value="Close" onclick="HideModalPopup('dvPopup'); return false;"/>
                        </td>
                    </tr>
                </table>
            </div>

        </html:form>
    </body>
<%}catch(Exception e){System.out.println(e);} %>
</html>