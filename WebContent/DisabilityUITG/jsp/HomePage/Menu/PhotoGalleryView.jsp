<%-- 
    Document   : PhotoGallery
    Created on : 12 Nov, 2013, 2:30:22 PM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%int i = 1;%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script type="text/javascript">
            function getPhotos(id)
            {
               
                document.forms[0].action ="./galleryView.do?mode=getPhotos&event="+id;
                document.forms[0].submit();
              
            }
        </script>
    </head>
    <body>
        <html:form action="/photos">
            <html:hidden property="mode"/>


            <table align="center" cellpadding="0" cellspacing="0" border="0" class="inputform" width="90%">
                <tr>
                    <th class="nav-txt" align="center">&nbsp; Photo Gallery</th>
                </tr>
                <logic:empty name="galleryEvents">
                    <tr align="center">
                        <td colspan="5" align="center"><font color="red" size="2">Photo Gallery Details Not Updated</font></td>
                    </tr>
                </logic:empty>
                <logic:notEmpty name="galleryEvents">
                    <logic:iterate id="row" name="galleryEvents" scope="request">
                        <tr>
                            <td >
                                <br/> <%=i++%>.<a href="#" onclick="getPhotos('${row.event}')"> ${row.event}</a>
                            </td>
                        </tr>
                    </logic:iterate>
                </logic:notEmpty>
            </table>


        </html:form>
    </body>
</html>
