<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@page import="java.util.*" %>


<html:html>
    <HEAD>
        <% String basalAgeStr = (String) request.getAttribute("basalAge");
                    String terminalAgeStr = (String) request.getAttribute("terminalAge");
                    String mentalAgeYearsStr = (String) request.getAttribute("mentalAgeYears");
                    String mentalAgeMonthsStr = (String) request.getAttribute("mentalAgeMonths");

                    if (basalAgeStr.equals("0")) {
                        basalAgeStr = "";
                    }
                    if (terminalAgeStr.equals("0")) {
                        terminalAgeStr = "";
                    }

        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       

    </HEAD>
    <script language="javascript" >
        function goBack()
        {
            document.forms[0].action="MentalRetardationForwdAction.do";
            document.forms[0].submit();
        }

    </script>
    <script>
        function validate_required(field,alerttxt)
        {
            with (field)
            {
                if (value==null||value=="")
                {
                    alert(alerttxt);
                    return false
                }
                else
                {
                    return true
                }
            }
        }
        function validate_form(thisform)
        {
            with (thisform)
            {
            
                if (validate_required(bictyear,"Enter Years")==false)
                {
                    bictyear.focus();
                    return false
                }
                if (validate_required(bictmonth,"Enter Months")==false)
                {
                    bictmonth.focus();
                    return false
                }
            }
        }
   
           
    </script>


    <body>
        <html:errors/>
        <html:form action="mrbinetkamattestaction.do?insertBinetKamatTestScreening=insertBinetKamatTestScreening" onsubmit="return validate_form(this)" styleId="mentalretardationactiontestform">

            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="60%">
                <tr>
                    <th colspan="8">3.&nbsp;&nbsp;Add Binet Kamat Test of Intelligence (B.K.T)</th>
                </tr>
           <tr>
                    <td><br><b>Basal Age  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:text property="bictbasalage" size="14" value="<%=basalAgeStr%>" readonly="true"/></td>
                    <td ><br><b>Terminal Age  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:text property="bictterminalage" size="14" value="<%=terminalAgeStr%>" readonly="true"/></td></tr>

                <td colspan="2"><br><b>Mental Age &nbsp;&nbsp;&nbsp; Years:<html:text property="bictyear" size="14" value="<%=mentalAgeYearsStr%>" readonly="true"/>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <b>Months:&nbsp;</b><html:text property="bictmonth" size="14" value="<%=mentalAgeMonthsStr%>" readonly="true"/></td>
            </tr>


            <tr>
                <th colspan="8"><html:submit  value="Add" styleClass="button"/></th>
        </table>

    </html:form>
</body>

</html:html>


