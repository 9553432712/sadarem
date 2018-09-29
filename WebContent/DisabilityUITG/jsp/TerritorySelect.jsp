
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.*,java.text.*"%>
<%@page session="true"%>


<html:html>


    <head>
        <script>
            function goBack()
            {
                document.forms[0].action="DecisionForPWDPensionRestrictPartA.do";
                document.forms[0].submit();
            }
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

                    if (validate_required(mandal_id,"Select Mandal")==false)
                    {
                        mandal_id.focus();
                        return false
                    }

                    if (validate_required(village_id,"Select Village")==false)
                    {
                        village_id.focus();
                        return false
                    }
                    if (validate_required(habitation_id,"Select Habitation")==false)
                    {
                        habitation_id.focus();
                        return false
                    }
                    <!-- modified by ganesh -->
                    if (validate_required(typeofdisability,"Select Type of Disability")==false)
                    {
                        typeofdisability.focus();
                        return false
                    }
                    <!-- modified by ganesh  -->
                }
            }


            function prasad()
            {
                var count=0;
                count++;
                devi();
            }
            function getData(read)
            {
                document.forms[0].status.value="update";
                document.forms[0].submit();
            }

            function devi()
            {
                alert(count);
                if(count==1)
                    document.territoryform.mandal_id.selectedIndex=0;
            }
            function goBack()
            {
                document.forms[0].action="DecisionForPWDPensionRestrictPartA.do";
                document.forms[0].submit();
            }
        </script>

    </head>

    <BODY>

    

    <html:form action="/TerritorySelect.do" method="get" styleId="territoryform" onsubmit="return validate_form(this)" >

        <input type="hidden" name="status" value="finish"/><br>
        <br><br><br><br>
        <table  align="center" cellspacing="0" cellpadding="5" class="innerTable" width="50%">
            <tr><td colspan="2" align="center" class="heading">Select the Territory For Add</td></tr>
        </table>

        <table  align="center" cellspacing="0" cellpadding="5" class="innerTable1" width="50%">

            <%--
            <tr class="tbl_bg2_content_hdr_new"> <font size="2"><td><b> District</font></td>
            <td colspan="3">
            <html:select  property="district_id" onchange="getData(this.value)" >
            <html:option  value="">--SELECT--</html:option>
            <html:optionsCollection   property="districtlist" label="district_name" value="district_id"  />
            </html:select>
            </td>
            </tr> --%>


            <tr>
                <td class="columnHeight20" colspan="2"></td>
            </tr>

            <tr>
                <td align = "center" class="label">Mandal</td>
                <td class="textbox">
                    <html:select property="mandal_id" onchange="getData(this.value)" >
                        <html:option  value="">--SELECT--</html:option>
                        <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"  />
                    </html:select>
                </td>
            </tr>

            <tr>
                <td align = "center" class="label">Village</td>
                <td class="textbox">
                    <html:select property="village_id" onchange="getData(this.value)">
                        <html:option  value="">--SELECT--</html:option>
                        <html:optionsCollection  property="villagelist" label="village_name" value="village_id"  />
                    </html:select>
                </td>
            </tr>

            <tr>
                <td  align = "center" class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Habitation</td>
                <td class="textbox">
                    <html:select property="habitation_id">
                        <html:option  value="">--SELECT--</html:option>
                        <html:optionsCollection   property="habitationlist" label="habitation_name" value="habitation_id"  />
                    </html:select>
                </td>
            </tr>

            <tr><td class="columnHeight10" colspan="2"></td></tr>

            <tr>
                <td colspan="2" align = "center"><input type="Submit" value="Submit" class="button" >&nbsp;&nbsp;
                    <html:button property="" value="Back" onclick="goBack()" styleClass="button"/></td>
            </tr>
            <tr><td class="columnHeight20" colspan="2"></td></tr>

        </table>

    </html:form>


</body>
</html:html>
