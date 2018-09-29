<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%
            Integer vinelandyear = (Integer) request.getAttribute("vsmsyear");
            Double vinelandmonth = (Double) request.getAttribute("vsmsmonth");



%>

<html:html>
    <HEAD>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   

</HEAD>

<script language="javascript" >
    

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
            if (validate_required(vsmsyear,"Enter Years")==false)
            {
                vsmsyear.focus();
                return false
            }
            if (validate_required(vsmsmonth,"Enter Months")==false)
            {
                vsmsmonth.focus();
                return false
            }
            if (thisform.getAttribute('submitted'))
                return false;
            else{
                thisform.setAttribute('submitted','true');
                document.getElementById('toatlDisButton').disabled = true;
            }
        }
    }
            
</script>



<body>
    <html:errors/>
    <html:form action="mrvinelandtestupdate.do?updateVinelandTestScreening=updateVinelandTestScreening" onsubmit="return validate_form(this)" styleId="mentalretardationactiontestform" >


        <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="60%">
            <tr>
                <th colspan=4 >2. Update Vineland Social Maturity Scale (V.S.M.S)</th>
            </tr>
      
            <tr>
                <td align="CENTER" ><br>Social Age</td>

            </tr>
            <tr>
                <td align="CENTER" ><br>Years <html:text property="vsmsyear" readonly="true" size="5" value="<%=String.valueOf(vinelandyear)%>"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    Months <html:text property="vsmsmonth" readonly="true" size="5"  value="<%=String.valueOf(vinelandmonth)%>"/></td>

            </tr>
       
            <tr>
                <th colspan="4"><html:submit  value="Update"  styleId="toatlDisButton" styleClass="button"/></th>


            </tr>

        </table>


    </html:form>
</body>

</html:html>

