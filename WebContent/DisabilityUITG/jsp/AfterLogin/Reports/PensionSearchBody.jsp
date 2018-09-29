<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,java.text.*"%>
<%@page session="true"%>
<%int i = 1;%>

<html>
    <head>


        <script>
            
            function validateform(){
                if(document.forms[0].elements['personcode'].value=="")
                {
                    alert("Please Enter Pension Number");
                    document.getElementById("districtid").focus()
                    return false;
                }else if(document.forms[0].elements['personcode'].value!="")
                {
                    if(document.forms[0].elements['districtid'].value=="") {
                        alert("Please Select District");
                        document.getElementById("districtid").focus()
                        return false;
                    }
                }

                document.forms[0].action="searchpension.do?SearchPage=SearchPension";
                document.forms[0].submit();

              
            }

          function openRequestedPopup()
            {

                var windowObjectReference;
                var strWindowFeatures = "menubar=no,location=no,resizable=no,scrollbars=yes,status=yes,target=_parent";



            }


        </script>

    </head>  
    <body onload="openRequestedPopup()">
        <html:form action="/searchpension.do" method="post">
            <input type="hidden" name="SearchPage" id="SearchPage" value="SearchPension">

            <logic:present name="searchmsg">
                <p id="errmsg">${searchmsg}</p>
            </logic:present>


            <table cellpadding="0" cellspacing="0" align="center" width="90%" border="0" class="inputform" >
                <tr><th class="heading" colspan="4" align="center">Search For Pension Number</th></tr>


                <tr>
                    <td >Pension Number :<font color="red"><b>*</b></font></td><td>
                        <html:text property="personcode" size="30" maxlength="17" />
                    </td>
                    <td >
                        District :<font color="red"><b>*</b></font></td>
                    <td>  <html:select property="districtid" >
                           <html:option value="0">--Select--</html:option>
                                                <html:optionsCollection property="districtlist" label="district_name" value="district_id"  />
                        </html:select>

                    </td></tr>

                <tr>
                    <th  align="center"   colspan="4">
                        <input type="button" value="Search" onclick="validateform();">
                        <input type="reset" value="Reset">

                    </th>
                </tr>


            </table><br/>





        </html:form>


    </body>
</html>