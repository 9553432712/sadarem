<%-- 
    Document   : fileUploadDownload
    Created on : Jul 17, 2012, 11:50:32 AM
    Author     : 693461
--%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%@page  import="java.util.ArrayList"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>:::SADAREM:::</title>

        <%
                    ArrayList files = (ArrayList) request.getAttribute("files");

                  
                   String districtName = (String)session.getAttribute("district_name");
                   String district_idData = null;

                   if((String)session.getAttribute("district_id")!=null){
                   }
                   
        %>
        <script>

            function uploadDataFile(){

                if(document.forms[0].elements['district_id'].value=="0") {
                    alert("Please Select District");
                    document.forms[0].elements['district_id'].focus();
                    return false;
                }else if(document.forms[0].elements['subjectData'].value=="") {
                    alert("Please Enter Mail Subject");
                    document.forms[0].elements['subjectData'].focus();
                    return false;
                }else if(document.forms[0].elements['bodyMailData'].value=="") {
                    alert("Please Enter Mail Body");
                    document.forms[0].elements['bodyMailData'].focus();
                    return false;
                } else if(document.forms[0].elements['uploadFile'].value=="") {
                    alert("Please Browse the File");
                    document.forms[0].elements['uploadFile'].focus();
                    return false;
                }
                else{
                document.forms[0].mode.value="uploadFileDetails";
                document.forms[0].submit();
                }
            }

            function downloadFile(){

                document.forms[0].mode.value="downloadFileDetails";
                document.forms[0].submit();

            }

            function getLinkDetails(id) {
                document.forms[0].mode.value="LinkDownloadDetails";
                document.forms[0].submit();
                
            }

            function getFiles(id){
                document.forms[0].district_id.value=id.value;
                document.forms[0].mode.value="getFiles";
                document.forms[0].submit();
            }

            
        </script>

    </head>

    <link rel="stylesheet" href="./DisabilityUITG/css/cssmaster.css" type="text/css">

    <body>
        <html:form action="/fileUploadDownload" enctype="multipart/form-data">
            <html:hidden property="mode"/>

       
            <p id="succmsg"><logic:present name="uploadSuccess">
              ${uploadSuccess}
            </logic:present></p>
            <table cellpadding="0" cellspacing="0" align="center" width="50%" class="inputform" border="0">
                <tr>
                    <th colspan="2">
                        Upload/Download
                    </th>
                </tr>
                <tr align="center">
                    <td >District : &nbsp;</td>
                    <td  valign="middle" align="left">
                        <html:select property="district_id" style="height:25px;" onchange="getFiles(this);" style="width:200px">
                            <html:option  value="0">--Select--</html:option>
                                        <html:optionsCollection property="districtlist" label="district_name" value="district_id"  />
                        </html:select>
                    </td>
                    
                </tr>
                <tr>
                    <td >Mail Subject :&nbsp; </td>
                     <td  valign="middle" align="left">
                        <html:textarea property="subjectData" style="width:200px"/>
                    </td>
                    
                </tr>
                <tr>
                    <td >Mail Description :&nbsp; </td>
                    <td  valign="middle" align="left">
                        <html:textarea property="bodyMailData" style="width:200px"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="left">
                        <html:file property="uploadFile" style="width:300px"/>
                    </td>
                </tr>
                <tr>
                    <th colspan="2">
                        <html:button property="but" value="Upload" onclick="uploadDataFile();" />
                    </th>
                    </tr>


               
                  </table>
                         <br/><br/>
                         <table cellpadding="8" cellspacing="1" width="60%" class="innerTable" align="center">

                <logic:notEmpty name="files">

                    <%
                                for (int i = 0; i < files.size(); i++) {
                    %>
                    <tr>
                        <%String dist=(String)request.getAttribute("dist");
                        //        getParameter("district_id");
                        %>
                        <td align="center">
                            <a target="_blank" href="fileUploadDownload.do?mode=FilelinkDownlaodDetails&district_id=<%=dist %>&imgId=<%=files.get(i)%>"><%=files.get(i)%></a>
                        </td>
                    </tr>
                    <%
                        }
                    %>

                </logic:notEmpty>
            </table>
        </html:form>
    </body>
</html>
