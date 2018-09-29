<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<head>

    <title>SADAREM</title>
    <link href="./Styles/RR-Style.css" type="text/css" rel="stylesheet" />
    <link href="./Styles/Left-Menu.css" type="text/css" rel="stylesheet" />


</head>
<%
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
            int i = 1;
%>

<body style="padding:0px;margin:0px;background-color: #f1f1ed">
    <html:form action="newsTitles">



        <table class="inputform" width="90%" align="left" border="0" cellpadding="0" cellspacing="1"  >
            <logic:notEmpty name="titles">

                <th> News Titles</th>


                <logic:iterate name="titles" id="row">
                    <tr>
                        <td align="left"><a href="#" onclick="window.open('./newsTitles.do?mode=getNewsDetails&id=${row.id}')">${row.title}</a></td>
                    </tr>
                </logic:iterate>

            </logic:notEmpty>


        </table>

        <!-- Body Ends -->

    </html:form>

</body>


</html>
