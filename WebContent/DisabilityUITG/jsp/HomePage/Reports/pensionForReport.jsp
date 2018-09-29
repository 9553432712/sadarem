<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%int i = 1;%>
<html>
    <head>



        <script>


            function selectedNames()
            {
                var slcBx1 = document.getElementById("1");
                var slcBx2 = document.getElementById("2");
                var slcBx3 = document.getElementById("3");

                document.getElementById("districtName").value = slcBx1.options[slcBx1.selectedIndex].text;
                document.getElementById("mandalName").value = slcBx2.options[slcBx2.selectedIndex].text;
                document.getElementById("villageName").value = slcBx3.options[slcBx3.selectedIndex].text;

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

            function disabileDistrict()
            {
                var x=document.getElementById("district_id").value;
                if(x == 0)
                {
                    document.forms[0].mandal_id.disabled = true;
                    document.forms[0].village_id.disabled = true;

                }
                else
                {
                    document.forms[0].mandal_id.disabled = false;
                    document.forms[0].village_id.disabled = true;
                    document.forms[0].village_id.value="0";
                }

            }

            function disabileMandal()
            {
                var x=document.getElementById("mandal_id").value;
                if(x == 0)
                {
                    document.forms[0].village_id.value="0";
                    document.forms[0].village_id.disabled = true;

                }
                else
                {
                    document.forms[0].village_id.disabled = false;
                    document.forms[0].village_id.value="0";
                }

            }

            function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }
            function validateform(){

                if(document.forms[0].elements['sadaremId'].value!="") {
                    if(document.forms[0].elements['sadaremId'].value.length < "17") {
                        alert("Please Enter the Valid Sadarem Number");
                        //document.forms[0].elements['sadaremId'].value="";
                        document.forms[0].elements['sadaremId'].focus();
                        return false;
                    }
                }


                if(document.forms[0].elements['personcode'].value=="" && document.forms[0].elements['district_id'].value=="0" &&
                    document.forms[0].elements['sadaremId'].value=="") {
                    alert("Please Select Atleast one Option");
                    return false;
                }

                if(document.forms[0].elements['personcode'].value!="")
                {
                    if(document.forms[0].elements['district_id'].value=="0") {
                        alert("Please Select District");
                        document.getElementById("district_id").focus()
                        return false;
                    }
                }

                document.forms[0].mode.value="getResults";
                document.forms[0].submit();


            }

            function resetValues() {
                document.forms[0].elements['personcode'].value="";
                document.forms[0].elements['district_id'].value="0";
                document.forms[0].elements['sadaremId'].value="";
                document.forms[0].mode.value="";
            }

            function getData() {
                document.forms[0].mode.value="";
                document.forms[0].submit();
            }

            function getHab() {
                document.forms[0].mode.value="";
                document.forms[0].submit();
            }

            function deselect() {
                document.forms[0].elements['sadaremId'].value="";
                document.forms[0].elements['name'].value="";
                document.forms[0].elements['parentsName'].value="";

            }

            function deselectId() {
                document.forms[0].elements['personcode'].value="";
                document.forms[0].elements['district_id'].value="0";
                document.forms[0].elements['mandal_id'].value="0";
                document.forms[0].elements['village_id'].value="0";

            }

           

        </script>

    </head>
    <body>
        <html:form action="/pensionForReport.do" method="post" >
            <html:hidden property="mode"/>

            <logic:present name="msg">
                <p id="errmsg"><bean:write name="msg"/></p>
            </logic:present>

                <table border="1" cellspacing="0" cellpadding="0" width="60%" align="center" class="inputform">
                <tr>
                    <th colspan="4">
                        R5.3 &nbsp; Search by PensionNo Report
                    </th>
                </tr>
                <tr>
                    <td>
                        <table border="0" cellspacing="0" cellpadding="0" width="60%" align="center" class="inputform">
                            <tr>
                    <td  width="50%" >Pension Code </td>
                    <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>
                        <html:text property="personcode" maxlength="" size="30" onfocus="deselect()" onkeypress="return isNumberKey()"/>
                    </td>
                </tr>
                <tr>
                    <td  width="50%" >SADAREM ID </td>
                    <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>
                        <html:text property="sadaremId" maxlength="17" size="30" onfocus="deselectId()" onkeypress="return onlyNumbers()"/>
                    </td>
                </tr>
                <tr>
                    <td  width="50%" >District </td>
                    <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                            <td align="left" valign="middle">
                                                <html:select styleId="1" property="district_id"  style="width:165px;">
                                                    <html:option value="0">-- Districts --</html:option>
                                                    <html:optionsCollection property="districtList" label="district_name" value="district_id"  />
                        </html:select><br/>
                    </td>
                </tr>
                        </table>
                    </td>
                </tr>
                
                <tr>
                    <th colspan="3">
                        <input type="button" value="Search" onclick="validateform();">
                        <input type="button" value="Reset" onclick="resetValues();">

                    </th>
                </tr>
            </table>
            <br/>
            <logic:notEmpty name="searchList">
                <table  align="center" cellspacing="0" cellpadding="0" width="90%" class="table">

                    <tr>
                        <th  >
                            SlNo
                        </th>
                        <th  >
                            PensionID
                        </th>
                        <th  >
                           SADAREM ID
                       </th>
                        <th  >
                            Person Name
                         </th>
                        <th  >
                            Gender
                       </th>
                        <th  >
                            Education
                         </th>
                        <th  >
                            Relation Name
                         </th>
                        <th  >
                            Type of Disability
                        </th>
                        <th  >
                            Total Disability
                        </th>
                        <th  >
                            Remarks
                        </th>
                        <th  >
                            Status
                         </th>
                    </tr>
                    <logic:iterate name="searchList"  id="row">

                        <tr>
                            <td  >
                                <%=i++%>.
                            </td>
                            <td  >
                                ${row.pensionid}
                            </td>
                            <td  >
                                ${row.sadaremcode}
                            </td>
                            <td  >
                                ${row.personname}
                            </td>
                            <td  >
                                ${row.gender}
                            </td>
                            <td  >
                                ${row.education}
                            </td>
                            <td  >
                                ${row.relationname}
                            </td>
                            <td  >
                                ${row.typeofDisability}
                            </td>
                            <td  >
                                ${row.totalDisability}
                            </td>
                            <td  >
                                ${row.remarks}
                            </td>
                            <td  >
                                ${row.reasonforpersonstatus}
                            </td>
                        </tr>

                    </logic:iterate>

                </table>
            </logic:notEmpty>

            <br/>

        </html:form>
    </body>

</html>