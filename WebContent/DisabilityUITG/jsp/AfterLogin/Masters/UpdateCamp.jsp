             
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
            if (validate_required(district_id,"Select District")==false)
            {
            district_id.focus();
            return false
            }

           if (validate_required(camp_venue,"Please Enter Venue Name")==false)
            {
            camp_venue.focus();
            return false
            }
            if (validate_required(camp_name,"Name of the Medical Authority")==false)
            {
            camp_name.focus();
            return false
            }

             if (validate_required(camp_address,"Address of the Medical Authority")==false)
            {
            camp_address.focus();
            return false
            }

            }
            }

            function getData(read)
            {
                document.forms[0].status.value="update";
                document.forms[0].submit();
            }
        </script>

    </head>

    <BODY><br><br>

        

        <html:form action="/selectcamp.do?selectCamp=selectCamp" onsubmit="return validate_form(this)">
            <% ArrayList campdetailslist = (ArrayList) request.getAttribute("campDetailsList"); %>
            <input type="hidden" name="status" value="finish"/>
           
           
           <table  align="center" cellspacing="1" cellpadding="8" class="table" width="85%">
                <tr>
                    <th class="heading" align="center">Update Medical Authority</th>
                </tr>
            </table>
            <table  align="center" cellspacing="1" cellpadding="8" class="table" width="85%">
                <tr>
                    <td class="labelBlue" align="right">District</td>

                    <td colspan="2">
                        <html:select  property="district_id" onchange="getData(this.value)">
                            <html:option  value="">--SELECT--</html:option>
                            <html:optionsCollection   property="districtlist" label="district_name" value="district_id"/>
                        </html:select>
                    </td>

            </tr>
            </table>
            

            <table  align="center" cellspacing="1" cellpadding="8" border="1" class="table" width="85%">

                <logic:notEmpty name="campDetailsList" scope="request">
                    <tr>
                        <th width="42%" class="labelBlue" align="center">Venue</th>
                    <th width="42%" class="labelBlue" align="center">Name of the Medical Authority</th>
                   
                    <th  class="labelBlue" align="center">Update</th>
                    <th  class="labelBlue" align="center">Delete</th>
                </tr>
                    <logic:iterate id="id1" collection="<%=campdetailslist%>" >
                        <tr>
                            <td width="42%"><center><font size="2"><bean:write name="id1" property="camp_venue" /></font></center></td>
                            <td width="42%"><center><font size="2"><bean:write name="id1" property="camp_name" /></font></center></td>

                           


                            <td><html:hidden name="id1" property="camp_id" ></html:hidden>
                                <html:hidden name="id1" property="camp_address"/>

                            <a href="campdispaly.do?updateCampDiplay=updateCampDiplay&campid=<bean:write name="id1" property="camp_id"/>
                               &camp_venue=<bean:write name="id1" property="camp_venue"/>
                               &camp_address=<bean:write name="id1" property="camp_address"/>
                               &camp_name=<bean:write name="id1" property="camp_name"/>"><center><font size="2">Update</font></center></a> </td>
                            <td><html:hidden name="id1" property="camp_id" ></html:hidden>
                                <html:hidden name="id1" property="camp_address"/>

                            <a href="campdelete.do?deleteCamp=deleteCamp&campid=<bean:write name="id1" property="camp_id"/>
                              &district_id=<bean:write name="id1" property="district_id"/>"><center><font size="2">Delete</font></center></a> </td>
                        </tr></logic:iterate>
                </logic:notEmpty>
            </table>



        </html:form>


    </body>
</html:html>
