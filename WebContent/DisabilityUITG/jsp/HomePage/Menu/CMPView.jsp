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
<%
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
            int i = 1;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">
            function filter (phrase, _id){
                var words = phrase.value.toLowerCase().split(" ");
                var table = document.getElementById(_id);
                var ele;
                for (var r = 1; r < table.rows.length; r++){
                    ele = table.rows[r].innerHTML.replace(/<[^>]+>/g,"");
                    var displayStyle = 'none';
                    document.getElementById('firstRow').style.display='';
                //    document.getElementById('secRow').style.display='';
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
        <html:form action="/cmpView">
            <html:hidden property="mode"/>

            <table align="center" cellpadding="0" cellspacing="0" border="0" width="100%" class="inputform">
                <tr>
                    <td valign="top">
                        <table align="left" cellpadding="0" cellspacing="0" border="0" width="100%">
                            <tr>
                                <th > CIRCULARS / MEMOS / PROCEEDINGS</th>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                            </tr>
                        </table>
                    </td>
                </tr>

                <tr >
                    <td align="center">

                        <table align="center" cellpadding="0" cellspacing="0" border="1" width="96%" class="inputform" id="mohan">
                            <logic:present name="errorMsg">
                                <tr align="center">
                                    <td align="center" colspan="5">
                                        <font color="red" size="2"><bean:write name="errorMsg"/></font>
                                    </td>
                                </tr>
                            </logic:present>
                            <tr>
                                <td colspan="5" align="right">
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
                                    Go / Circular / Memo Number
                                </th>
                                <th>
                                    Issuing Date
                                </th>
                               <%-- <th>
                                    View | Download
                                </th>--%>
                            </tr>
                            <logic:empty name="cmpUploadsDetails">
                                <tr align="center">
                                    <td colspan="10" align="center"><font color="red" size="2">Circulars/Memos/Proceeding Details Not Updated</font></td>
                                </tr>
                            </logic:empty>

                            <logic:notEmpty name="cmpUploadsDetails">
                                <logic:iterate id="ccla" name="cmpUploadsDetails" scope="request">
                                    <tr>
                                        <td width="8%">
                                            <%=i++%>.
                                        </td>
                                        <td width="12px">
                                            ${ccla.DocumentType}
                                        </td>
                                        <td>
                                            ${ccla.CMPNumber}
                                        </td>
                                        <td width="20px">
                                            ${ccla.IssuingDate}
                                        </td>
                                        <%--<td width="12%">
                                            <!--<a href="./cmpView.do?mode=viewGo&Go=${ccla.UploadFile}" onclick="window.open('./PdfWebPath/${ccla.UploadFile}','myWindow',',menubar=no,toolbar=no');">-->
                                            <a href="./cmpView.do?mode=downloadGo&Go=${ccla.UploadFile}">
                                                <img src="./DisabilityUITG/images/view.jpg" alt="" style="width: 25px"/>
                                            </a>&nbsp;&nbsp;
                                            <a href="./cmpView.do?mode=downloadGo&Go=${ccla.UploadFile}">
                                                <img src="./DisabilityUITG/images/download.png" alt=""/>
                                            </a>
                                        </td>--%>
                                    </tr>
                                </logic:iterate>
                            </logic:notEmpty>
                        </table>
                    </td>
                </tr>
            </table>
        </html:form>
    </body>
    <script>
        document.forms[0].elements['filt'].value="Search...";
        function disableText() {
            document.forms[0].elements['filt'].value="";
        }
    </script>
</html>