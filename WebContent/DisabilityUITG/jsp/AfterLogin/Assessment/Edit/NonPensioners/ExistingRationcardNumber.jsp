<%-- 
    Document   : ExistingRationcardNumber
    Created on : Aug 6, 2011, 3:31:52 PM
    Author     : SADAREM
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%String message = null;

            String restrictPartA = null;
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
            restrictPartA = request.getParameter("PartArestrict");
            if (restrictPartA == null || "".equals(restrictPartA)) {
                restrictPartA = (String) session.getAttribute("PartArestrict");
            }
            String rid = (String) request.getAttribute("rationid");

            request.setAttribute("rationid", rid);
            int i = 1;
            ArrayList rationCardDetails = new ArrayList();
            if (request.getAttribute("rationCardDetails") != null) {
                rationCardDetails = (ArrayList) request.getAttribute("rationCardDetails");
            }

%>
<html:html>
    <script type="text/javascript">

        function submitValues() {
            
            var val = document.forms[0].elements['rationCardNo'].value;
            if(document.forms[0].elements['rationCardNo'].value=="") {
                alert("Please Enter RationCard Number");
                document.forms[0].elements['rationCardNo'].focus();
                return false;
            }else if(document.forms[0].elements['rationCardNo'].value.length < 15) {
                alert("Please Enter Valid RationCard Number");
                document.forms[0].elements['rationCardNo'].value="";
                document.forms[0].elements['rationCardNo'].focus();
                return false;
            }else if(val.substring(0,3)!="WAP" && val.substring(0,3)!="PAP" && val.substring(0,3)!="AAY"
                && val.substring(0,3)!="AAP" && val.substring(0,3)!="YAP" 
                && val.substring(0,3)!="wap" && val.substring(0,3)!="pap" && val.substring(0,3)!="aay"
                &&  val.substring(0,3)!="aap" && val.substring(0,3)!="yap" 
                && val.substring(0,3)!="RAP" && val.substring(0,3)!="rap"
                && val.substring(0,3)!="TAP" && val.substring(0,3)!="tap"
                && val.substring(0,3)!="WAD" && val.substring(0,3)!="wad") {
                alert("Please Enter Valid RationCard Number");
                // document.forms[0].elements['rationCardNo'].value="";
                document.forms[0].elements['rationCardNo'].focus();
                return false;
            }
            document.getElementById('detailsButton').style.display='none';
            document.getElementById('processing').style.display='';
          

            
            document.forms[0].mode.value="getData";
            document.forms[0].submit();
        }

        function submitRationCardDetails() {
            var size='<%=rationCardDetails.size()%>';
            if(size>1){
                var rationLength=document.forms[0].elements['rationCardslNo'].length;
                for(var i=0;i<rationLength;i++){

                    if(document.forms[0].elements['rationCardslNo'][i].value=="") {
                        alert("Enter Rationcard Serial Number");
                        document.forms[0].elements['rationCardslNo'][i].focus();
                        return false;
                    }
                }}
            else{
                if(document.forms[0].elements['rationCardslNo'].value=="") {
                    alert("Enter Rationcard Serial Number");
                    document.forms[0].elements['rationCardslNo'].focus();
                    return false;
                }
            }

            document.forms[0].mode.value="submitRationCardDetails";
            document.forms[0].submit();
        }
        
            function validateForm(thisform)
            {
                if (thisform.getAttribute('submitted'))
                    return false;
                thisform.setAttribute('submitted','true');
            }
             function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }

       
    </script>
    <link rel="stylesheet" href="./DisabilityUITG/css/Sadarem-Style.css" type="text/css">
  
    <body >

        <FONT COLOR="RED" size="2"><marquee ><B>For Address changed Rationcards, Raise request in IssueRaising screen to do assessment in changed district. </B></marquee>  </FONT>
        <html:form action="RationcardDetails.do" focus="rationCardNo"
                   onsubmit="return validateForm(this)"  >
            
            <html:hidden property="mode"/>
            <logic:present name="msgSuccess">
                <p id="succmsg"><bean:write name="msgSuccess"/></p>
            </logic:present>
            <logic:present name="msgFailure">
                <p id="errmsg"><bean:write name="msgFailure"/></p>
            </logic:present>
            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="50%" border="0">
                <tr>
                    <th colspan="2">Enter Existing Rationcard Number</th>
                </tr>

                <tr>
                    <td >RationCard Number :</td>
                    <td><html:text property="rationCardNo" maxlength="15" size="21" /><br/> <font color="red" size="2">(Please Enter Capital Letters)</font>

                    </td>
                </tr>
                <tr  id="detailsButton">
                    <th colspan="2">
                        <html:button property="but" value="Get Details" onclick="submitValues();"/>
                    </th>
                   
                </tr>
            </table><br/>

            <table  align="center" cellspacing="1" cellpadding="0"  width="50%" border="0">
                <tr>
                    <td  align="center" id="processing" style="display: none;">
                            <img src='<%=basePath%>DisabilityUITG/images/ajax-loader.gif'/>
                    </td>
                </tr>
            </table>
            <logic:notEmpty name="rationCardDetails">
                <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                    <tr>
                        <th  colspan="8" align="center">
                            Person / Person's Details
                        </th>
                    </tr>
                    <tr>
                        <th  align="center">
                            Slno
                        </th>
                        <th  align="center">
                            SADAREM ID
                        </th>
                        <th  align="center">
                            Name
                        </th>
                        <th  align="center">
                            Relation
                        </th>
                        <th  align="center">
                            Gender
                        </th>
                        <th  align="center">
                            Date of Birth
                        </th>
                        <th  align="center">
                            Age
                        </th>
                        <th  align="center">
                            RationCard SerialNo
                        </th>
                    </tr>

                    <logic:iterate id="row" name="rationCardDetails">
                        <tr>
                            <td align="center">
                                <%=i++%>.
                            </td>
                            <td>
                                ${row.person_code}
                            </td>
                            <td>
                                ${row.name}
                            </td>
                            <td>
                                ${row.Relation_name}
                            </td>
                            <td>
                                ${row.gender}
                            </td>
                            <td>
                                ${row.date_of_birth}
                            </td>
                            <td>
                                ${row.age_years}
                            </td>
                            <td align="center" >
                                <html:text property="rationCardslNo" maxlength="2" value="" onkeydown="return onlyNumbers();" onkeypress="return onlyNumbers();"  />
                                <html:hidden property="rationCardHidden" value="${row.person_code}"/>
                            </td>
                        </tr>

                    </logic:iterate>
                    <tr>
                        <th colspan="8">
                            <html:button property="buttons" value="Submit" onclick="submitRationCardDetails();"/>
                        </th>
                    </tr>

                </table>

            </logic:notEmpty>


        </html:form>

    </body>


</html:html>
