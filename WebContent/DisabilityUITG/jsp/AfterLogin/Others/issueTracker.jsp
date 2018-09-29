<%--
    Document   : issueRaiseApproval
    Created on : Nov 28, 2012, 1:20:33 PM
    Author     : 693461
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page  import="java.util.Iterator"%>
<%@page  import="java.util.ArrayList"%>
<%@page  import="org.bf.disability.dto.IssueRaiseApprovalDTO"%>

<%
            ArrayList campList = (ArrayList) request.getAttribute("campList");
            int i = 1;
            String value = null;
            if (request.getAttribute("cat35Id") != null) {
                value = (String) request.getAttribute("cat35Id");
            }

%>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script>
            function clearSaidInputs(evt,thisvalue,categoryFormId){
                if(space(evt,thisvalue)){
                    if(thisvalue.value.length<1){
                        document.forms[0].categoryFormId.value='0';
                    }
                }else{
                    return space(evt,thisvalue);
                }
            }
            function space(evt,thisvalue)
            {
                var number = thisvalue.value;
                var charCode = (evt.which) ? evt.which : event.keyCode
                if(number.length < 1){
                    if(evt.keyCode == 32) {

                        return false;
                    }
                }
                return true;
            }
            function getCategoryId(id){
                if(id!=0){
                    document.forms[0].elements['id'].value="";
                }
            }
            function issueTrackerDetails() {
                document.forms[0].mode.value ="issueTrackerDetails";
                document.forms[0].submit();
            }
         
        </script>
        <style>
            select{width:175px;border:1px #ccc solid;}
        </style>
    </head>

    <body >
        <html:form action="/issueRaiseApproval">
            <html:hidden property="mode"/>

            <logic:notEmpty name="msg">
                <p id="errmsg">${msg}</p>
            </logic:notEmpty>

            <table border="0" cellspacing="1" cellpadding="0" width="90%" align="center" class="inputform">
                <tr>
                    <th colspan="6">
                        Issue Tracker Details
                    </th>
                </tr>
                <tr>

                    <td>Category :</td>
                    <td >
                        <html:select styleId="3" property="categoryFormId"  onchange="getCategoryId(this.value);" style="width:280px">
                            <html:option value="0">All</html:option>
                            <html:optionsCollection property="categoryList" label="categoryname" value="categoryId" />
                        </html:select>
                    </td>
                    <td >Issue&nbsp;ID:</td>
                    <td >
                        <html:text property="id" maxlength="8" onkeypress="return onlyNumbers();" onkeyup="allnumeric(this);"
                                   onkeydown="return clearSaidInputs(event,this,document.forms[0].categoryFormId.value);"/>
                    </td>

                    <%--<logic:notPresent name="categoryFormId">--%>
                    <td>Status :</td>

                    <td >
                        <html:select  property="status">
                            <html:option value="0">All</html:option>
                            <html:option value="Pending">Pending</html:option>
                            <html:option value="Resolved">Resolved</html:option>
                            <html:option value="Rejected">Reject</html:option>
                            <html:option value="Hold">Hold</html:option>
                        </html:select><br/>
                    </td>
                </tr>
                <tr>
                    <th colspan="10">
                        <html:submit  value="Get Details"  onclick=" return issueTrackerDetails();" />
                    </th>
                </tr>

            </table>

            <br>

                        <logic:notEmpty name="issueList">
                            <div style="overflow:scroll; width:950px; height:335px" align="center">
                            <%--<div style="width:900px; height:300px; overflow:scroll;border:1px solid #ccc" align="center">--%>
                            <table cellpadding="0" cellspacing="1" align="center" width="90%" border="0" class="inputform">
                                    <tr>
                                        <th>
                                            S.NO
                                        </th>
                                        <th>
                                            IssueID
                                        </th>

                                        <th>
                                            Category
                                        </th>
                                        <th>
                                            SADAREM ID
                                        </th>

                                        <th>
                                            Pension ID
                                        </th>

                                        <th>
                                            Resolution Remarks
                                        </th>
                                        <th>
                                            Issue Raised Date
                                        </th>
                                        <th>
                                            Issue Resolved Date
                                        </th>
                                        <th>
                                            Request Status
                                        </th>
                                        <th>
                                            Action
                                        </th>

                                    </tr>
                                    <logic:iterate name="issueList" id="row">
                                        <tr>
                                            <td>
                                                <%=i++%>.
                                            </td>
                                            <td >
                                                ${row.requestId}
                                            </td>

                                            <td >
                                                ${row.categoryName}
                                            </td>
                                            <td >
                                                ${row.sadaremId}
                                            </td>
                                            <td >
                                                ${row.pensionId}
                                            </td>


                                            <td >
                                                ${row.comments}
                                            </td><td >
                                                ${row.raisedDate}
                                            </td>

                                            <td >
                                                ${row.solvedDate}
                                            </td>
                                            <td >
                                                ${row.requeststatus}
                                            </td>
                                            <td >
                                                <a href="#" onclick="window.open('issueRaiseApproval.do?mode=getIssueResolvedDetails&requestStatus= ${row.requeststatus}&categoryId=${row.categoryId}&requestId=${row.requestId}')">View</a>
                                            </td>
                                        </tr>

                                    </logic:iterate>

                                </table>
                            </div>
                        </logic:notEmpty>
                   
                </html:form>
    </body>

</html>
