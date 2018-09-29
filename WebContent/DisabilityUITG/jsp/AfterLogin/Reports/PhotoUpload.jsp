<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<html>
    <head>
        <title>SADAREM</title>
        <%
                    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
                    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
                    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
                    String aadharEnable = (String) request.getAttribute("aadharEnable");
                    String uploadButton = null;
                    if (request.getAttribute("uploadButton") != null) {
                        uploadButton = (String) request.getAttribute("uploadButton");
                    }
                    String personcode = "";
                    if (request.getAttribute("personcode") != null) {
                        personcode = (String) request.getAttribute("personcode").toString();
                    }
                    String invalid = "";
                    if (request.getAttribute("invalid") != null) {
                        invalid = (String) request.getAttribute("invalid").toString();
                    }


                    String wpii = null;
                    if (request.getAttribute("pensioncardno") != null) {
                        wpii = (String) request.getAttribute("pensioncardno");


                    
                    }
                    
                    
                    String districtId=(String)session.getAttribute("districtId");
			           String logDistName=(String)session.getAttribute("logDistName");
        %>
					      
<script language="javascript" src="./DisabilityUITG/js/GenerateAadharCardValidation.js"></script>
        <script language="JavaScript">
            var exts = "jpg|gif|png|bmp|mpg|mpeg";
            function StartUpload()
            {
                var len=document.forms[0].length-1;
                for (var i=0;i<len;i++)
                {
                    current = document.forms[0].elements[i];
                    if(current.type=='file' && current.value=='')
                    {
                        alert('Select images to upload');
                        return false;
                    }
                    else if(current.type=='file' && current.value!='' &&  !checkExt(current.value))
                    {
                        return false;
                    }
                    else if(current.type=='text' && current.value=='')
                    {
                        alert('Enter Description');
                        return false;
                    }
                }
            }
            function checkExt(value){
                if(value=="")return true;
                var re = new RegExp("^.+\.("+exts+")$","i");
                if(!re.test(value))
                {
                    alert("Extension not allowed for file: \"" + value + "\"\nOnly these extensions are allowed: "+exts.replace(/\|/g,',')+" \n\n");
                    return false;
                }
                return true;
            }



            function validate_required(field,type)
            {
                with (field)
                { if(type="personcode"){
                        if (value==null||value=="")
                        {alert("Please Enter SADAREM ID");return false;}
                        var numericExpression = /^[0-9]+$/;
                        if(!value.match(numericExpression))
                        {alert("SADAREM ID must be Integer");return false;}
                        if(value.length !=17)
                        {alert("SADAREM ID must be exactly of 17 numbers");return false;}

                    }else if(type="aadharCardNo"){
                        if (value==null||value=="")
                        {alert("AadharCard Number must be filled out!");return false;}

                        if(value != null && value !=""){
                            if(value.length!=12 ){
                                alert(" Please Enter Valid AdhaarCard Number.AdhaarCard Number Must have 12 digits");
                                return false;
                            }
                        }
                    }
                }

            }

            function validate_form(thisform)
            {
                with (thisform)
                {
                    if (validate_required(personcode,"personcode")==false)
                    {personcode.focus();return false;}
                    if (validate_required(personcode,"personcode")==false)
                    {personcode.focus();return false;}
                    if( StartUpload()== false){
                        return false;
                    }
                    if (validate_required(aadharCardNo,"aadharCardNo")==false)
                    {
                        aadharCardNo.focus();
                        return false;
                    }
                    if (validateVerhoeff(aadharCardNo)==false)
                    {

                        aadharCardNo.value="";
                        aadharCardNo.focus();
                        return false;
                    }
                    return true;

                }
            }


            function viewPhoto(){
                var personcode = document.getElementById("personcodeId").value;
                if(personcode==""){
                    alert("Please Enter SADAREM ID");
                }else{
                    createPhotoViewObject();
                }
            }
            function uploadPhoto(){
                var personcode = document.getElementById("personcodeId").value;
                
            <%
                        if (aadharEnable != null && !aadharEnable.equalsIgnoreCase("")) {%>
                                var aadharCardNo = document.getElementById("aadharCardNo").value;
                    
                   
                                if (aadharCardNo==null||aadharCardNo=="")
                                {alert("Enter AadharCard Number!");return false;}

                                if(aadharCardNo != null && aadharCardNo !=""){
                                    if(aadharCardNo.length!=12 ){
                                        alert(" Please Enter Valid AdhaarCard Number.AdhaarCard Number Must have 12 digits");
                                        document.getElementById("aadharCardNo").value="";
                                        //document.getElementById("aadharCardNo").focus;
                                        return false;
                                    }
                                     if (validateVerhoeff(aadharCardNo)==false){
                                         document.getElementById("aadharCardNo").value="";
                                         return false;
                                     }
            <%--alert(validateVerhoeff(aadharCardNo));
            if (validateVerhoeff(aadharCardNo)==false)
            {
                document.getElementById("aadharCardNo").value="";
                //document.getElementById("aadharCardNo").focus;
                return false;
            }--%>
                    }
            <%}%>
                    if(personcode==""){
                        alert("Please Enter SADAREM ID");
                        return false;
                    }else{
                        PhotoUpload();
                    }
                }
                function PhotoUpload(){
                    var personcode = document.getElementById("personcodeId").value;

            <%
                        if (aadharEnable != null && !aadharEnable.equalsIgnoreCase("")) {%>
                                var aadharCardNo = document.getElementById("aadharCardNo").value;
                                document.forms[0].action="upload.do?personcode="+personcode+"&aadharCardNo="+aadharCardNo+"&photoview=aadharValidate&photoUploadWithPersonCodeFlag=true";

            <%} else {%>
                    document.forms[0].action="upload.do?personcode="+personcode+"&photoview=upload&photoUploadWithPersonCodeFlag=true";
            <%}%>
                    document.forms[0].submit();
                }
                function createPhotoViewObject(){
                    x = GetXmlHttpObject();
                    x.onreadystatechange=getDetails;
                    var personcode=document.getElementById("personcodeId").value;
                    var url="upload.do?personcode="+personcode+"&photoview=view&photoUploadWithPersonCodeFlag=true";
                    x.open("GET",url,true)
                    x.send();


                }
                function GetXmlHttpObject(){
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

                function getDetails()
                {
                    var rs1,rs2,rs3;

                    // document.getElementById("imageID").src = "";
                    // alert(document.getElementById("imageID").src)
                    if(x.readyState==4 || x.readyState=="complete")
                    {
                        var m=x.responseXML.documentElement;
                        var z=0;
                        rs1=m.getElementsByTagName("personcode")[z].childNodes[0].nodeValue;
                        rs2=m.getElementsByTagName("districtname")[z].childNodes[0].nodeValue;
                        rs3=m.getElementsByTagName("message")[z].childNodes[0].nodeValue;
                        viewPhotoMode(rs1,rs2,rs3);
                    }
                }


                function viewPhotoMode(personcode,districtname,message){
                    if(message != null && message !="" && message != "no"){
                        document.getElementById("photo").style.visibility = "hidden";
                        document.getElementById("photo").style.display = "none";
                        alert(message);
                    }else if(personcode != "no" && districtname != "no"){
                        var DisplayImg  = document.getElementById("imageID");
                        DisplayImg.src="./DisabilityUITG/uploadedphotos/"+districtname+"/"+personcode+".JPG";
                        document.getElementById("photo").style.visibility = "Visible";
                        document.getElementById("photo").style.display = "Inline";

                    }
                }

                function photoUpload(){
                    var upload=document.getElementById("selectphoto").value;
                    if(upload == "upload"){
                        document.getElementById("uploadphoto").style.visibility = "Visible";
                        document.getElementById("uploadphoto").style.display = "Inline";
                        document.getElementById("viewphoto").style.visibility = "hidden";
                        document.getElementById("viewphoto").style.display = "none";
                        document.getElementById("photo").style.visibility = "hidden";
                        document.getElementById("photo").style.display = "none";            
                    }
                }
                function photoView(){
                    var upload=document.getElementById("view").value;
                    if(upload == "view"){
                        document.getElementById("viewphoto").style.visibility = "Visible";
                        document.getElementById("viewphoto").style.display = "Inline";
                        document.getElementById("uploadphoto").style.visibility = "hidden";
                        document.getElementById("uploadphoto").style.display = "none";            
                    }
                }

                function hiddenFields(){
                    document.getElementById("viewphoto").style.visibility = "hidden";
                    document.getElementById("viewphoto").style.display = "none";
                    document.getElementById("uploadphoto").style.visibility = "hidden";
                    document.getElementById("uploadphoto").style.display = "none";
                    document.getElementById("photo").style.visibility = "hidden";
                    document.getElementById("photo").style.display = "none";
                    document.getElementById("personcodeId").value='<%=personcode%>';
                    document.getElementById("uploadScanphoto").style.visibility = "hidden";
                    document.getElementById("uploadScanphoto").style.display = "none";
            <%
                            if (aadharEnable != null && !aadharEnable.equalsIgnoreCase("")) {%>
                                    document.getElementById("aadharCard").style.visibility = "visible";
                                    document.getElementById("aadharCard").style.display = "block";
                    
                                    document.getElementById("uploadphoto").style.visibility = "visible";
                                    document.getElementById("uploadphoto").style.display = "block";
                                    document.getElementById('selectphoto').checked=true;

            <%} else {%>
                    document.getElementById("aadharCard").style.visibility = "hidden";
                    document.getElementById("aadharCard").style.display = "none";
                    

            <%}%>
            <%
                        if (uploadButton != null && !uploadButton.equalsIgnoreCase("")) {%>
                                document.getElementById("uploadScanphoto").style.visibility = "visible";
                                document.getElementById("uploadScanphoto").style.display = "block";
                                document.getElementById('selectphoto').checked=true;

            <%} else {%>
                    document.getElementById("uploadScanphoto").style.visibility = "hidden";
                    document.getElementById("uploadScanphoto").style.display = "none";

            <%}%>

                }
                function hidePhoto(){
                    document.getElementById("viewphoto").style.visibility = "hidden";
                    document.getElementById("viewphoto").style.display = "none";
                    document.getElementById("uploadphoto").style.visibility = "hidden";
                    document.getElementById("uploadphoto").style.display = "none";
                    document.getElementById("photo").style.visibility = "hidden";
                    document.getElementById("photo").style.display = "none";
                    document.getElementById("aadharCard").style.visibility = "hidden";
                    document.getElementById("aadharCard").style.display = "none";
            
                }

                function openwindow(){
                    var personcode=document.getElementById("personcodeId").value;
                    if(personcode != null && personcode !=""){
                        window.open("photoupload.do?personcode="+personcode+"","Ratting","width=500,height=200,0,status=0");
                    }else{
                        alert("Please enter SADAREM ID")
                    }
                }

                function isNumberKey(e)
                {
                    //var number = document.getElementById("aadharCardNo").value;

                    AllowableCharacters='1234567890';
                    var k = document.all?parseInt(e.keyCode): parseInt(e.which);

                    if (k!=13 && k!=8 && k!=0){
                        if ((e.ctrlKey==false) && (e.altKey==false)) {
                            return (AllowableCharacters.indexOf(String.fromCharCode(k))!=-1);
                        } else {
                            return true;
                        }
                    } else {
                        return true;
                    }

                }
                function setaddharflag(){
                    var personcode=document.getElementById("personcodeId").value;
           
      
                    if(personcode != null && personcode !=""){
                        if(personcode.length==17)
                        {
                        	            if(personcode.substring(0,2)=='<%=districtId%>')
                        		             {
                                          document.forms[0].action="upload.do?personcode="+personcode+"&photoUploadWithPersonCodeFlag=true";
                                          document.forms[0].submit();
                                              }
                        	           else
                        		               {
                        		           alert("Sadarem Id does not belongs to " +'<%=logDistName%>'+ " district");
                        		           return false;
                        		                 }
                        } 
                        else
                        {
                            alert("Invalid SADAREM ID");
                            return false;
                        }
                        }else if(personcode == null || personcode==""){
                        alert("Please Enter SADAREM ID");
                        return false;
                        }
                }
        </script>

        <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
        <META HTTP-EQUIV="Expires" CONTENT="-1">
        <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">


    </head>

    <body onload="hiddenFields();">
        <% int x = 0;%>
        <html:form action="upload.do" enctype="multipart/form-data" onsubmit="return validate_form(this);" focus="personcode">
            <input type="hidden" name="photoUploadWithPersonCodeFlag" value="true"/>
            <logic:present name="errmsg">
                <p id="errmsg"><bean:write name="errmsg"/>
                </p>

            </logic:present>
            <logic:present name="succMsg">
                <p id="succmsg"><bean:write name="succMsg"/>
                </p>

            </logic:present>
            <logic:present name="uploadButton">
                <p id="errmsg">Photo not exist in AADHAR Web Service. Kindly upload the scan copy
                </p>

            </logic:present>
            <center>
                <table   cellspacing="0" cellpadding="0" class="inputform" border="1" width="80%">

                    <tr>
                        <th colspan="4"> Upload / View Photo </th>
                    </tr>
                    <tr>
                        <td>
                            <table   cellspacing="0" cellpadding="0" class="inputform" border="0" width="100%">
                                <tr>
                                    <td > Enter SADAREM ID </td>
                                    <%if (personcode == "") {%>
                                    <td ><input type="text" name="personcode" maxlength="17" size="17"  id="personcodeId"/></td>
                                        <%} else {%>
                                    <td ><input type="text" name="personcode" maxlength="17" size="17"  id="personcodeId" value='<%=personcode%>'/></td>
                                        <%}%>
                                    <td  colspan="3">
                                        <input type="button" name="Submit" value="Submit" onclick="setaddharflag()" /></td>

                                </tr>

                                <logic:present name="invalid">
                                    <tr>
                                    <td  width="20%">Upload Photo</td>
                                    <td width="5%"><input type="radio" name="photoupload" value="upload"  id="selectphoto" onclick="photoUpload();" ></td>

                                        <%if (wpii != null && wpii.equalsIgnoreCase("WPIII")) {%>


                                        <%} else {%>

                                        <td  >  <div id="aadharCard" style="visibility:hidden;display:none">
                                                <table   cellspacing="0" cellpadding="0" border="0"   width="100%">
                                                    <tr>
                                                        <td>AadharCard Number</td>
                                                        <td><input type="text" onkeypress="return isNumberKey(event)" maxlength="12" name="aadharCardNo" /></td>
                                                    </tr></table>
                                            </div></td>
                                            <%}%>

                                    <td  >  <div id="uploadphoto" style="visibility:hidden;display:none">
                                            <table   cellspacing="0" cellpadding="0" border="0"   width="100%">
                                                <tr>
                                                    <td >
                                                        <input type="button" name="Upload" value="Upload" onclick="uploadPhoto()" /></td>
                                                </tr></table>
                                        </div></td>
                                    <td  >
                                        <div id="uploadScanphoto" style="visibility:hidden;display:none">
                                            <table  cellspacing="0" cellpadding="0" border="0"  width="100%">
                                                <tr>
                                                    <td  align="left" > To Upload Photo
                                                        <a href="javascript:void(0);"  onClick="openwindow();">
                                                            <u>&nbsp;Click here</u></a></td>
                                                            <%--<td align="left" >
                                                                <html:file   property="theFile" onchange="checkExt(this.value)" />&nbsp;&nbsp;
                                                                <html:submit styleClass="button"/>
                                                            </td>--%>
                                                </tr>
                                            </table>
                                        </div>

                                    </td>

                                </tr>
                                <tr>
                                    <td >View Photo</td>
                                    <td><input type="radio" name="photoupload" value="view"  id="view" onclick="photoView();" ></td>
                                    <td colspan="3">
                                        <div id="viewphoto" style="visibility:hidden;display:none">
                                            <table   cellspacing="0" cellpadding="0" border="0"   width="100%">
                                                <tr>
                                                    <td >
                                                        <input type="button" name="view" value="View" onclick="viewPhoto()" /></td>
                                                </tr></table>
                                        </div>
                                </tr>
                                </logic:present>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <th align="center"  colspan="4">
                            <html:reset styleClass="button" onclick="hidePhoto();"/></th>
                    </tr>

                </table>
                <center>
                    <div id="photo" style="visibility:hidden;display:none">
                        <table><tr>
                                <td><img align="center" id="imageID" width="100" height="100">
                                </td>
                            </tr>
                        </table>
                    </div>
                </center>
            </center>
        </html:form>
    </body>
    <HEAD>
        <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
    </HEAD>
</html>