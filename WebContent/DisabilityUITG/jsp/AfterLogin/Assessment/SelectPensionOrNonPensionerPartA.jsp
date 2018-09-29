<%--
    Document   : SelectPensionerOrNonPensionerPartA
    Created on : Jun 11, 2012, 12:59:14 PM
    Author     : 310926
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

        if(val!=null && val==1){
            //alert("value========="+val);

            document.forms[0].action="existingPensionNumberFlow.do?restrictPartA=pensionNumberRestrictPartA";
            document.forms[0].submit();

        }else if(val!=null && val==2){
            // alert("value========="+val);
            // document.getElementById("selectFlow").value='rati';
            document.forms[0].action="RationcardDetails.do?PartArestrict=pensionNumberRestrictPartA";
            document.forms[0].submit();

        }else if(val!=null && val==3){
            // alert("value========="+val);
            // document.getElementById("selectFlow").value='rati';
            document.forms[0].action="aadharCardDetails.do?mode=getExistingData&PartArestrict=true";
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

        <html:form action="SelectPensionerOrNonPensionerPartA.do"  method="post" styleId="partAForm" onsubmit="return validate_form(this)" >


            <logic:present name="msg">
                <%--   <tr>
                       <td  align="center" colspan="2">
                           <font color="red" size="2"><b><></b></font>
                       </td>
                   </tr>--%>
                <p id="errmsg"><bean:write name="msg"/></p>

            </logic:present>

            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="50%">





                <th colspan="2">Select AadharCard Number </th>
                <tr>
                    <td style="text-align: center">
                       <%-- <input type="radio"  style="width:30px" name="normal" id="1" value="1" onclick="return selectflow(this.value)"/>Pensioners &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp
                        <input type="radio" style="width:30px" name="ration" id="2" value="2" onclick="return selectflow(this.value)"/>Non-Pensioners&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp
                        --%><input type="radio" style="width:30px" name="aadhar" id="3" value="3" onclick="return selectflow(this.value)"/>AadharCard Number<%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp--%>
                    </td>
                </tr>
            </table>
            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="50%" border="0" >
                <tr> <th colspan="2" align="left" >Note</th></tr>

                <tr><td colspan="2">
               <%-- <li>&nbsp;&nbsp;For Pensioner , Please select Pensioner Radio Button
                    <br/>  <br/>

                <li>&nbsp;&nbsp;For Non-pensioner , Please select Non-pensioner Radio Button
                     <br/>  <br/>--%>

                <li>&nbsp;&nbsp;For AadharCard Number , Please select AadharCard Number Radio Button</td>
                    </tr>
            </table>

        </html:form>
    </body>
</html>