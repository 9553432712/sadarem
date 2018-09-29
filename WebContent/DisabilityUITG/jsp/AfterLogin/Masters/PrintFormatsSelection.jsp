<%-- 
    Document   : PrintFormatsSelection
    Created on : Aug 9, 2012, 6:49:21 PM
    Author     : 490058
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">


    function selectflow(val){

        if(val!=null && val==2){
            // alert("value========="+val);
            document.getElementById("selectFlow").value='appellate';
            document.getElementById("catId").value='2';
            document.forms[0].action="appletAuthorityPrints.do?mode=getValuesForPrints&selectFlow=appellate&catId=2";
            document.forms[0].submit();

        }else if(val!=null && val==3){
            // alert("value========="+val);
            document.getElementById("selectFlow").value='temporay';
            document.getElementById("catId").value='3';
            document.forms[0].action="appletAuthorityPrints.do?mode=getValuesForPrints&selectFlow=temporay&catId=3";
            document.forms[0].submit();

        }else if(val!=null && val==4){
            // alert("value========="+val);
            document.getElementById("selectFlow").value='Physical';
            document.forms[0].action="appletAuthorityPrints.do?mode=getValuesForPrints&selectFlow=Physical";
            document.forms[0].submit();

        }
        //
    }
</script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>

        <table align="center">  <logic:present name="msg">
                <tr>
                    <td align="center" colspan="2">
                        <font color="red" size="2"><b><bean:write name="msg"/></b></font>
                    </td>
                </tr></table>
            </logic:present>
            <html:form action="appletAuthorityPrints.do"  method="post" styleId="partAForm" onsubmit="return validate_form(this)" >
                <html:hidden property="selectFlow" styleId="selectFlow"/>
                <html:hidden property="catId" styleId="catId"/>
                <html:hidden property="catCount" styleId="catCount"/>
            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                <tr><th colspan="3" align="center" class="heading">Select Appellate Formats/Temporary Formats/Physical Requirements Formats</th></tr>
                <tr>
                    <td align="center"><input style="width: 30px" type="radio" name="ration" id="4" value="2" onclick="return selectflow(this.value)">Appellate Formats</td>
                    <td align="center"><input style="width: 30px" type="radio" name="normal" id="1" value="3" onclick="return selectflow(this.value)">Temporary Formats</td>
                    <td align="center"><input style="width: 30px" type="radio" name="appellate" id="2" value="4" onclick="return selectflow(this.value)">Physical Requirements Formats</td>
                </tr>
               <%-- <tr>
                    <td width="100%"colspan="4">- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  </td>
                </tr>--%>
                <tr><td colspan="4" class="Note"><b style="color: red">Note:</b></td></tr>
                <tr><td class="labelBlue" colspan="4">
                <li>&nbsp;&nbsp; Please Select Appellate Formats Radio Button to get Appellate formats.</td>
                    </tr>
                <tr>
                    <td class="labelBlue" colspan="4">
                <li>&nbsp;&nbsp; Please select Temporary Formats Radio Button to get Temporary formats. </td>
                    </tr>
                <tr>
                    <td class="labelBlue" colspan="4">
                <li>&nbsp;&nbsp; Please select Physical Requirements Formats Radio Button to get Physical Requirements formats.</td>
                    </tr>

            </table>

        </html:form>
    </body>
</html>
