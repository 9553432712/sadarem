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
%>

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

            function viewPhoto(){
                    createPhotoViewObject();
            }

            function createPhotoViewObject(){
                x = GetXmlHttpObject();
                x.onreadystatechange=getDetails;
                
                var url="upload.do?photoview=view";
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
                //response.AppendHeader("pragma", "no-store,no-cache") // HTTP 1.0
                //Response.AppendHeader("cache-control", "no-cache, no-store,must-revalidate, max-age=-1") // HTTP 1.1
                //Response.AppendHeader("expires", "0")
                document.getElementById("imageID").src = "";

                if(x.readyState==4 || x.readyState=="complete")
                {
                    
                    var m = x.responseXML.documentElement;
                    var z=0;
                        rs1=m.getElementsByTagName("personcode")[z].childNodes[0].nodeValue;
                        rs2=m.getElementsByTagName("districtname")[z].childNodes[0].nodeValue;
                        rs3=m.getElementsByTagName("message")[z].childNodes[0].nodeValue;
                    viewPhotoMode(rs1,rs2,rs3);
                }
            }


            function viewPhotoMode(personcode,districtname,message){
                if(message != null && message !="" && message !="no"){
                    document.getElementById("photo").style.visibility = "hidden";
                    document.getElementById("photo").style.display = "none";
                    alert(message);
                }else{
                    var DisplayImg  = document.getElementById("imageID");
                    DisplayImg.src="./DisabilityUITG/uploadedphotos/"+personcode+"/Profile/profilepic.JPG";
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

            }
            function hidePhoto(){
               document.getElementById("viewphoto").style.visibility = "hidden";
                document.getElementById("viewphoto").style.display = "none";
                document.getElementById("uploadphoto").style.visibility = "hidden";
                document.getElementById("uploadphoto").style.display = "none";
                document.getElementById("photo").style.visibility = "hidden";
                document.getElementById("photo").style.display = "none";
            }


        </script>

    </head>
    
    <body onload="hiddenFields();">
        <% int x = 0;%>
        <html:form action="upload.do" enctype="multipart/form-data" onsubmit="return StartUpload();">
            <center>
                
                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="90%">
                    <input type="hidden" name="photoUploadWithPersonCodeFlag" value="uploadview"/>
                    <tr align="center"><td class="heading">photo Upload/View</td></tr>
                </table>
                 <table   cellspacing="1" cellpadding="8" class="innerTable1" width="90%">
                    <tr>
                        <td class="label" width="20%">Upload Photo</td>
                             <td width="5%"><input type="radio" name="photoupload" value="upload"  id="selectphoto" onclick="photoUpload();" ></td>
                        <td class="label" >
                            <div id="uploadphoto" style="visibility:hidden;display:none">
                                <table  cellspacing="1" cellpadding="8"  width="100%">
                                    <tr>
                                        <td align="left" > <html:file   property="theFile" onchange="checkExt(this.value)" />&nbsp;&nbsp;<html:submit styleClass="button"/></td>
                                    </tr>
                                </table>
                            </div>

                        </td>
                        <td rowspan="2" align="right">
                             <div id="photo" style="visibility:hidden;display:none">
                        <table><tr>
                                <td><img align="center" id="imageID" width="100" height="100">
                                </td>
                            </tr>
                        </table>
                    </div>
                        </td>

                    </tr>
                    <tr>
                        <td class="label">View Photo</td>
                            <td><input type="radio" name="photoupload" value="view"  id="view" onclick="photoView();" ></td>
                        <td class="label">
                            <div id="viewphoto" style="visibility:hidden;display:none">
                                <table   cellspacing="1" cellpadding="8"  width="100%">
                                    <tr>
                                        <td class="label">
                                            <input type="button" name="view" value="view" onclick="viewPhoto()" class="button"/></td>
                                    </tr></table>
                            </div>
                       <tr>
                           <td align="center"  colspan="4">
                               <html:reset styleClass="button" onclick="hidePhoto();"/>&nbsp;&nbsp;
                               <input type="button" class="button" value="CloseWindow" onclick="javascript:window.close();"/></td>
                         </tr>

                </table>

            </center>
        </html:form>
    </body>
</html>