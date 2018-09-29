<%-- 
    Document   : CMPUploads
    Created on : 17 Oct, 2013, 11:15:58 AM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<script src="./DisabilityUITG/js/Ajax.js"></script>
<script src="./DisabilityUITG/js/Validation.js"></script>
<script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
<%
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
            int i = 1;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="title"/></title>

        <script>
            function getValue() {
                document.forms[0].mode.value="generateCircularNumber";
                document.forms[0].submit();
            }

            function saveDetails() {
               
                document.forms[0].cirOne.value=document.forms[0].memoNumberOne.value;
                document.forms[0].cirTwo.value=document.forms[0].memoNumberTwo.value;
                if(document.forms[0].elements['documentType'].value=="00") {
                    alert("Please Select Document Type");
                    document.forms[0].elements['documentType'].value="00";
                    document.forms[0].elements['documentType'].focus();
                    return false;
                }else if(document.forms[0].elements['fileNumber'].value=="") {
                    alert("Please Enter File Number");
                    document.forms[0].elements['fileNumber'].value="";
                    document.forms[0].elements['fileNumber'].focus();
                    return false;
                }else if(document.forms[0].elements['issueingDate'].value=="") {
                    alert("Please Select Issuing Date");
                    document.forms[0].elements['issueingDate'].value="";
                    document.forms[0].elements['issueingDate'].focus();
                    return false;
                }else if(document.forms[0].elements['subject'].value=="") {
                    alert("Please Enter Subject");
                    document.forms[0].elements['subject'].value="";
                    document.forms[0].elements['subject'].focus();
                    return false;
                }else if(document.forms[0].elements['reference'].value=="") {
                    alert("Please Enter Reference");
                    document.forms[0].elements['reference'].value="";
                    document.forms[0].elements['reference'].focus();
                    return false;
                }
                document.forms[0].mode.value="saveDetails";
                document.forms[0].submit();
            }

            function updateDetails() {
                if(document.forms[0].elements['documentType'].value=="00") {
                    alert("Please Select Document Type");
                    document.forms[0].elements['documentType'].value="00";
                    document.forms[0].elements['documentType'].focus();
                    return false;
                }else if(document.forms[0].elements['fileNumber'].value=="") {
                    alert("Please Enter File Number");
                    document.forms[0].elements['fileNumber'].value="";
                    document.forms[0].elements['fileNumber'].focus();
                    return false;
                }else if(document.forms[0].elements['issueingDate'].value=="") {
                    alert("Please Select Issuing Date");
                    document.forms[0].elements['issueingDate'].value="";
                    document.forms[0].elements['issueingDate'].focus();
                    return false;
                }else if(document.forms[0].elements['subject'].value=="") {
                    alert("Please Enter Subject");
                    document.forms[0].elements['subject'].value="";
                    document.forms[0].elements['subject'].focus();
                    return false;
                }else if(document.forms[0].elements['reference'].value=="") {
                    alert("Please Enter Reference");
                    document.forms[0].elements['reference'].value="";
                    document.forms[0].elements['reference'].focus();
                    return false;
                }

                document.forms[0].mode.value="updateDetails";
                document.forms[0].submit();
            }



            function goForDelete(id)
            {
                var conf=confirm("Are You Sure! you want to Delete ?")
                if (conf==true)
                {
                    document.forms[0].action ="./cmpUploads.do?mode=inactiveRecord&inactive="+id;
                    document.forms[0].submit();
                }
            }
        </script>
        <script type="text/javascript">
            function filter (phrase, _id){
                var words = phrase.value.toLowerCase().split(" ");
                var table = document.getElementById(_id);
                var ele;
                for (var r = 1; r < table.rows.length; r++){
                    ele = table.rows[r].innerHTML.replace(/<[^>]+>/g,"");
                    var displayStyle = 'none';
                    document.getElementById('firstRow').style.display='';
                    //  document.getElementById('secRow').style.display='';
                    for (var i = 0; i < words.length; i++) {
                        if (ele.toLowerCase().indexOf(words[i])>=0) {
                            displayStyle = '';
                        }else {
                            displayStyle = 'none';
                            break;
                        }
                    }
                    table.rows[r].style.display = displayStyle;
                }
            }
        </script>
    </head>
    <body>
        <html:form action="/cmpUploads" focus="documentType" enctype="multipart/form-data">
            <html:hidden property="mode"/>


            <table align="center" cellpadding="0" cellspacing="0" border="0" width="60%">
                <tr>
                    <td valign="top">
                        <table align="left" cellpadding="0" cellspacing="0" border="0" class="inputform" width="60%">
                            <tr>
                                <th ><font size="4">UPLOAD CIRCULARS / MEMOS / PROCEEDINGS</font></th>
                            </tr>

                            <logic:present name="msg">
                                <tr>
                                    <td>
                                        <center><bean:write name="msg"/></center>
                                    </td>
                                </tr>
                            </logic:present>

                            <tr>
                                <td>
                                    <table align="center" cellpadding="0" cellspacing="1" border="0" class="table"  style="border:1px solid #6c9719;">
                                        <tr align="right">
                                            <th>
                                                Document Type
                                            </th>
                                            <td align="left">
                                                <html:select property="documentType" onchange="getValue();" style="width:300px">
                                                    <html:option value="00">-- Document Type --</html:option>
                                                    <html:option value="Circular">Circular</html:option>
                                                    <html:option value="Memo">Memo</html:option>
                                                    <html:option value="Proceeding">Proceeding</html:option>
                                                </html:select>
                                            </td>
                                        </tr>
                                        <logic:present name="circularNumber">
                                            <tr align="right">
                                                <th>
                                                    Number
                                                </th>
                                                <td align="left">
                                                    <font size="3" color="white"><b><%=request.getAttribute("circularNumber")%></b></font>

                                                </td>
                                            </tr>
                                        </logic:present>
                                        <tr align="right">
                                            <th>
                                                Issuing Date
                                            </th>
                                            <td align="left">
                                                <%-- <html:text property="" readonly="true" size="40"/>
                                                 <a href="javascript:show_calendar('forms[0].issueingDate');"
                                                    onmouseover="window.status='Date Picker';return true;"
                                                    onmouseout="window.status='';return true;">
                                                      <img src="./E-ServicesUI/images/calendar.png" border="0"></a>--%>

                                                <html:text property="issueingDate" styleId="8"  readonly="true" size="12"/>
                                                <a  href="javascript:show_calendar('forms[0].issueingDate');"
                                                    onmouseover="window.status='Date Picker';return true;"
                                                    onmouseout="window.status='';return true;">
                                                    <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                            </td>
                                        </tr>
                                        <tr align="right">
                                            <th>
                                                Subject
                                            </th>
                                            <td align="left">
                                                <html:textarea property="subject" onkeypress="return inputLimiter(event,'Letters');" onkeydown="return space(event,this)" cols="100" rows="5" />
                                            </td>
                                        </tr>
                                        <tr align="right">
                                            <th>
                                                Reference
                                            </th>
                                            <td align="left">
                                                <html:textarea property="reference" onkeypress="return inputLimiter(event,'Letters');" onkeydown="return space(event,this)" cols="100" rows="5"/>
                                            </td>
                                        </tr>
                                        <tr align="right">
                                            <th>Upload <span id="printTextUpload"></span></th>
                                            <td align="left">
                                                <div id="imageId">
                                                    <html:file property="uploadFiles" size="30" onchange="javascript: uploadPDF(this,'imageId')" />
                                                </div>
                                            </td>
                                        </tr>
                                        <logic:notPresent name="enableEdit">
                                            <tr>
                                                <th colspan="2" align="center">
                                                    <html:button property="subprop" value="Submit" onclick="saveDetails();"/>
                                                </th>
                                            </tr>
                                        </logic:notPresent>
                                        <logic:present name="enableEdit">
                                            <tr>
                                                <th colspan="2" align="center">
                                                    <html:button property="subprop" value="Update" onclick="updateDetails();" />
                                                </th>
                                            </tr>
                                            <input type="hidden" name="rowIdForUpdate" value="<%=request.getParameter("RowId")%>">
                                        </logic:present>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>

                <tr>
                    <td align="center">
                        <table align="center" cellpadding="0" cellspacing="0" border="0" width="96%" class="inputform" id="mohan">
                            <tr>
                                <td colspan="8" style="align:right;">
                                    <html:text property="filt" onkeyup="filter(this, 'mohan', '1')"  size="18" onmousedown="disableText();" style="font-style: italic;color:gray;"/>
                                </td>
                            </tr>
                            <tr id="firstRow">
                                <th>
                                    Sno
                                </th>
                                <th>
                                    Document Type
                                </th>
                                <th>
                                    Circular / Memos / Proceedings No
                                </th>
                                <th>
                                    Issuing Date
                                </th>
                                <th>
                                    Subject
                                </th>
                                <th>
                                    Reference
                                </th>
                                <th>
                                    View | Download
                                </th>
                                <th>
                                    Action
                                </th>
                            </tr>
                            <logic:empty name="cmpUploadsDetails">
                                <tr align="center">
                                    <td colspan="10" align="center"><font color="red" size="2">Circulars/Memos/Proceeding Details Not Available</font></td>
                                </tr>
                            </logic:empty>
                            <logic:notEmpty name="cmpUploadsDetails">
                                <logic:iterate id="ccla" name="cmpUploadsDetails" scope="request">
                                    <tr>
                                        <td width="2px" valign="top">
                                            <center> <%=i++%>.</center>
                                        </td>
                                        <td valign="top">
                                            ${ccla.DocumentType}
                                        </td>
                                        <td valign="top">
                                            ${ccla.CMPNumber}
                                        </td>

                                        <td valign="top">
                                            ${ccla.IssuingDate}
                                        </td>
                                        <td width="250px" valign="top">
                                            ${ccla.Subject}
                                        </td>
                                        <td valign="top">
                                            ${ccla.Reference}
                                        </td>
                                        <td>
                                            <a href="./cmpUploads.do?mode=downloadGo&Go=${ccla.UploadFile}">
                                                <img src="./images/View.png" alt=""/>
                                            </a>&nbsp;&nbsp;
                                            <a href="./cmpUploads.do?mode=downloadGo&Go=${ccla.UploadFile}">
                                                <img src="./images/download.png" alt=""/>
                                            </a>
                                        </td>
                                        <td width="12%">
                                            <center>    <a href="./cmpUploads.do?mode=getValuesWhenClickOnEdit&RowId=${ccla.RowId}"><img src="images/Edit.png" alt="Edit" ></a>&nbsp; <a href="#" onclick="goForDelete(${ccla.RowId});" id="hide${row.RowId}"><img src="images/delete.png" alt="delete"  ></a></center>
                                        </td>
                                    </tr>
                                </logic:iterate>
                            </logic:notEmpty>
                        </table>

                    </td>
                </tr>
            </table>

        </html:form>
        <%if (request.getParameter("RowId") != null) {%>
        <input type="hidden" name="row" value="<%=request.getParameter("RowId")%>" id="rowValue">
        <script>
            document.getElementById("hide"+document.getElementById("rowValue").value).disabled=true;
        </script>

        <%}%>
    </body>
    <script>
        document.forms[0].elements['filt'].value="Search...";
        function disableText() {
            document.forms[0].elements['filt'].value="";
        }
    </script>
</html>
