<%-- 
    Document   : rdcallcentrerequestSearch
    Created on : Jun 13, 2013, 11:19:07 AM
    Author     : 728056
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script>
            function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }
            function getDetails(){
                if( document.forms[0].requestNumber.value==null ||document.forms[0].requestNumber.value=="" ){
                    alert("Please Enter Request Number");
                    document.forms[0].requestNumber.focus();
                    return false;
                }else{
                    document.forms[0].mode.value="getRdcallcentreRequestSearch";
                    document.forms[0].submit();
                    return true;
                }


            }
        </script>

    </head>
    <body >
   

    <html:form  action="rdcallcentrerequestSearch.do?mode=getRdcallcentreRequestSearch"  method="post"  >
        <html:hidden property="mode"/>
        <table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">
            <tr>
                <td align="center"  valign="top">

                    <br/>
                    <logic:present name="msg">

                        <p id="errmsg">No Data Found</p>

                    </logic:present>

                    <br/>

                    <logic:notEmpty name="request">

                        <table align="center" border="0" cellpadding="0" cellspacing="1" width="60%"  class="inputform">
                            <logic:iterate id="row" name="request">
                                <tr>
                                    <td  align="center" width="30%">Request Number</td>
                                    <td  align="center" width="70%">${row.requestNumber}</td>
                                </tr>
                                <tr>
                                    <td  align="center">Name</td>
                                    <td  align="center">${row.Name}</td>
                                </tr>
                                <tr>
                                    <td  align="center">Relation Name</td>
                                    <td  align="center">${row.RelationName}</td>
                                </tr>
                                <tr>
                                    <td  align="center">Address</td>
                                    <td  align="center">${row.address}</td>
                                </tr>
                                <tr>

                                    <td  align="center">Request Type</td>
                                    <td  align="center">${row.RequestType}</td>
                                </tr>
                                <tr>
                                    <td  align="center">Category Name</td>
                                    <td  align="center">${row.CategoryName}</td>
                                </tr>
                                <tr>

                                    <td  align="center">SubCategory Name</td>
                                    <td  align="center">${row.SubCategoryName}</td>
                                </tr>
                                <tr>
                                    <td  align="center">Generated SADAREM ID</td>
                                    <td  align="center">${row.GenaratdSadaremID}</td>
                                </tr>
                                <tr>

                                    <td  align="center">Status</td>
                                    <td  align="center">${row.SaStatus}</td>
                                </tr>
                                <tr>

                                    <td  align="center">Remarks</td>
                                    <td  align="center">${row.remarks}</td>
                                </tr>
                            </logic:iterate>
                        </table>
                    </logic:notEmpty>
                </td>
            </tr>
        </table>




    </html:form>
</body>
</html:html>