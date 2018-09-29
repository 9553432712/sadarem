<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>


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

        </script>
    </head>


    <body>
        <% String personcode = (String) request.getParameter("personcode");%>
        <% int x = 0;%>
        <html:form action="upload.do?photoview=fileUpload" enctype="multipart/form-data" onsubmit="return StartUpload();">

            <center>

                <input type="hidden" name="personcode" value="<%=personcode%>">
                <input type="hidden" name="photoUploadWithPersonCodeFlag" value="true"/>

                <table  width="400" border="1" cellpadding="1" cellspacing="0" align="left" class="inputform">

                    <tr><td  align="center"></td></tr>

                    <tr><th style="background-color:#8b8b8b;color:#fff;font-size: 15px">Select Image</th></tr>

                    <tr><td ><html:file   property="theFile" onchange="checkExt(this.value)"/></td></tr>

                    <tr><td style="background-color:#8b8b8b;text-align: center"><html:submit/><html:reset/></td></tr>

                </table>

            </center>

        </html:form>
    </body>
</html>