<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%

            Integer year = null;
            Double month = null;
            if (request.getAttribute("dstupdateyears") != null) {
                year = (Integer) request.getAttribute("dstupdateyears");

            } else {
                year = 0;
            }
            if (request.getAttribute("dstupdatemonths") != null) {
                month = (Double) request.getAttribute("dstupdatemonths");

            } else {
                month = 0.0;
            }




%>



<html:html>
    <HEAD>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


    </HEAD>

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
                if (validate_required(dstyear,"Enter Years")==false)
                {
                    dstyear.focus();
                    return false
                }
                if (validate_required(dstmonth,"Enter Months")==false)
                {
                    dstmonth.focus();
                    return false
                }else{
                    document.getElementById('updateBut').disabled = true;
                    return true;
                }
                             
            }
        }
   
           
    </script>


    <body>
        <html:errors/>
        <html:form action="mrdevelopmenttestupdate.do?updateDevelopmentalTestScreening=updateDevelopmentalTestScreening" onsubmit="return validate_form(this)"  styleId="mentalretardationactiontestform">


            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="50%">
                <tr>
                    <th colspan="4" align="center">1.&nbsp;&nbsp;Update Developmental Screening Test (D.S.T)</th>
                </tr>

                <tr>
                    <td  colspan="3"><br>Developmental Age</td>

                </tr>
                <tr>
                    <td colspan="3"><br>Years <html:text property="dstyear" value="<%=String.valueOf(year)%>"  readonly="true"/>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        Months<html:text property="dstmonth" value="<%=String.valueOf(month)%>" size="5" readonly="true"/></td>

                </tr>


                <tr>



                    <th colspan="8"><html:submit  value="Update" styleClass="button" styleId="updateBut"/></th>

                </tr>


            </table>


        </html:form>
    </body>

</html:html>


