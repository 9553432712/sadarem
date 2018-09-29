<%-- 
    Document   : DownloadFile
    Created on : Aug 2, 2012, 9:22:24 AM
    Author     : 693461
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%@page  import="java.util.ArrayList"%>


<html>
    <head>
        <%
                    //if (request.getParameter("uploadFile") != null) {
                    //    String uploadFile = (String) request.getParameter("uploadFile");
                    //}

                    // if(request.getAttribute("files") !=null){
                    ArrayList files = (ArrayList) request.getAttribute("files");

                    //  }
                    String districtName = (String) session.getAttribute("district_name");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <html:form action="/downloadFile">

        <body>
            <logic:present name="msg">
                <p id="errmsg" >${msg}</p>
            </logic:present>
            <table cellpadding="0" cellspacing="0" align="center" class="inputform" width="50%" border="1">


                <tr align="center">
                    <th>
                        DownLoad Files Details
                    </th>
                </tr>

          
            
            <tr align="center">
                    <td>
                <table cellpadding="0" cellspacing="0" width="50%" class="inputform" align="center" border="0">

                <logic:notEmpty name="files">

                    <%
                                for (int i = 0; i < files.size(); i++) {
                    %>
                    <tr>
                        <td align="center">
                            <a href="fileUploadDownload.do?mode=LinkDownloadDetails&imgId=<%=files.get(i)%>"><%=files.get(i)%></a>
                        </td>
                    </tr>

                    <%
                                }
                    %>

                </logic:notEmpty>
            </table>
                    </td>
            </tr>
  </table>

        </body>
    </html:form>
</html>
