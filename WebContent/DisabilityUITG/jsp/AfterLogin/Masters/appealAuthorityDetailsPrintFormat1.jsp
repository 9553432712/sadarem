<%--
    Document   : appealAuthorityDetailsPrintFormat1
    Created on : Aug 10, 2012, 11:54:34 AM
    Author     : 490058
--%>


<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>
<%
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
            int i = 0;
            int j = 0;
%>

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script>
            function goForPartB() {

                document.forms[0].mode.value="goForPartB";
                document.forms[0].submit();
            }
        </script>

        <script language="JavaScript">
            function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }

            function ristrictZero() {
                if(document.forms[0].elements['rationCardSlNo'].value=="0") {
                    document.forms[0].elements['rationCardSlNo'].value="";
                }

            }

            function checkRationCard(val) {

                if(val.substring(0,3)!="WAP" && val.substring(0,3)!="PAP" && val.substring(0,3)!="AAY" &&
                    val.substring(0,3)!="AAP" && val.substring(0,3)!="YAP" && val.substring(0,3)!="wap" &&
                    val.substring(0,3)!="pap" && val.substring(0,3)!="aay" &&  val.substring(0,3)!="aap" &&
                    val.substring(0,3)!="yap" && val.substring(0,3)!="RAP" && val.substring(0,3)!="rap"
                    && val.substring(0,3)!="TAP" && val.substring(0,3)!="tap"
                    && val.substring(0,3)!="WAD" && val.substring(0,3)!="wad") {
                    alert("Please Enter Valid RationCard Number");
                    document.forms[0].elements['rationCardNo'].value="";
                    document.forms[0].elements['rationCardNo'].focus();
                    return false;
                }
            }
 var exts = "jpg|gif|png|bmp|mpg|mpeg";
            function checkExt(value){
                if(value=="")return true;
                var re = new RegExp("^.+\.("+exts+")$","i");
                if(!re.test(value))
                {
                    alert("Extension not allowed for file: \"" + value + "\"\nOnly these extensions are allowed: "+exts.replace(/\|/g,',')+" \n\n");
                    var file = document.getElementById("photo");
                    file.form.reset();

                    //document.getElementById("photo").value="";
                    return false;
                }
                return true;
            }
        </script>
    </head>

    <body>


 <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">

    <html:form action="/appleteAuthorityPersonalDetailsPrint" enctype="multipart/form-data" >
        <html:hidden property="mode"/>
        
        <logic:notEmpty name="personDetails">

            <table align="center" cellpadding="0" cellspacing="0" border="1" class="innerTable" width="80%">
                <tr>
                    <td colspan="6" align="center" class="heading">
                        Appellate Authority 


                    </td>
                </tr>
                <logic:iterate id="row" name="personDetails">
                    <%if (i == 0) {%>
                    <tr>
                        <td class="label">
                            Pension Code :
                        </td>
                        <td class="label">
                            ${row.pension_id}
                        </td>
                        <td class="label">
                            Name :
                        </td>
                        <td class="label">
                            ${row.name}
                        </td>
                        <td rowspan="5" class="label" align="center" style="border-style:dotted;background-color:inherit " colspan="2">
                            <img src="./DisabilityUITG/uploadedphotos/${row.district_name}/${row.sadaremCode}.JPG" height="150" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/><br/>
                            <b> ${row.sadaremCode}</b>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">
                            Relation Name :
                        </td>
                        <td class="label">
                            ${row.relation_name}
                        </td>
                        <td class="label">
                            Gender :
                        </td>
                        <td class="label">
                            ${row.gender}
                        </td>
                    </tr>
                    <tr>
                        <td class="label">
                            Age :
                        </td>
                        <td class="label">
                            ${row.age}
                        </td>
                        <td class="label">
                            Religion :
                        </td>
                        <td class="label">
                            ${row.religion}
                        </td>
                    </tr>
                    <tr>
                        <td class="label">
                            Caste :
                        </td>
                        <td class="label">
                            ${row.caste}
                        </td>
                        <td class="label">
                            DOB :
                        </td>
                        <td class="label">
                            ${row.dob}
                        </td>
                    </tr>
                    <tr>
                        <td class="label">
                            Disability :
                        </td>
                        <td class="label">
                            ${row.disability}
                        </td>

                        <td class="label">
                            Identification Marks :
                        </td>
                        <td class="label">
                            ${row.moleOne}<br/>${row.moleTwo}
                        </td>
                    </tr>
                    <tr>
                        <td class="label" colspan="1">
                            RationCard No :&nbsp;
                        </td>
                        <td class="label">${row.rationcard}</td>

                        <td class="label" colspan="1">
                            RationCard SlNo :  &nbsp;
                        </td>
                        <td class="label">${row.rationcard_slno}</td>
                        <td class="label" colspan="1">
                            District :
                        </td>
                        <td class="label">${row.district_name}</td>
                    </tr>
                    <tr>
                        <td class="label">
                            Mandal :
                        </td>
                        <td class="label">
                            ${row.mandal_name}
                        </td>

                        <td class="label">
                            Village :
                        </td>
                        <td class="label">
                            ${row.village_name}
                        </td>
                        <td class="label">
                            Habitation :
                        </td>
                        <td class="label">
                            ${row.habitation_name}
                        </td>
                        <html:hidden property="printProperty" value="${row.personstatusPrint}"/>
                    </tr>

                    <%i++;%>
                    <%}%>
                </logic:iterate>
            </table>


            <br/>
            <table align="center" cellpadding="0" cellspacing="0" border="0">
                <tr>
                    <td>
                        <html:button property="but" value="Next " onclick="goForPartB();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                    <td>
                        <html:button property="butone" value="Print " onclick="window.print();"/>
                    </td>
                </tr>
            </table>
        </logic:notEmpty>
    </html:form>
</body>
</html:html>
