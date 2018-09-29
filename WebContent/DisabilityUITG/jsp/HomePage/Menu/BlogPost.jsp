<%-- 
    Document   : BlogPost
    Created on : 27 Nov, 2013, 2:32:09 PM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%int i = 1;%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script type="text/javascript">
            function saveBlogPostDetails() {
                if(document.forms[0].elements["name"].value=="") {
                    alert("Please Enter Name");
                    document.forms[0].elements["name"].value=="";
                    document.forms[0].elements["name"].focus();
                    return false;
                }else if(document.forms[0].elements["description"].value=="") {
                    alert("Please Enter Description");
                    document.forms[0].elements["description"].value=="";
                    document.forms[0].elements["description"].focus();
                    return false;
                }else if(document.forms[0].elements["mobile"].value=="") {
                    alert("Please Enter Mobile Number");
                    document.forms[0].elements["mobile"].value=="";
                    document.forms[0].elements["mobile"].focus();
                    return false;
                }else if(document.forms[0].elements["mobile"].value=="") {
                    alert("Please Enter Mobile Number");
                    document.forms[0].elements["mobile"].value=="";
                    document.forms[0].elements["mobile"].focus();
                    return false;
                }
                document.forms[0].mode.value="savePostBlogDetails";
                document.forms[0].submit();
            }

            function updateBlogPost() {
                if(document.forms[0].elements["name"].value=="") {
                    alert("Please Enter Name");
                    document.forms[0].elements["name"].value=="";
                    document.forms[0].elements["name"].focus();
                    return false;
                }else if(document.forms[0].elements["description"].value=="") {
                    alert("Please Enter Description");
                    document.forms[0].elements["description"].value=="";
                    document.forms[0].elements["description"].focus();
                    return false;
                }else if(document.forms[0].elements["mobile"].value=="") {
                    alert("Please Enter Mobile Number");
                    document.forms[0].elements["mobile"].value=="";
                    document.forms[0].elements["mobile"].focus();
                    return false;
                }else if(document.forms[0].elements["mobile"].value=="") {
                    alert("Please Enter Mobile Number");
                    document.forms[0].elements["mobile"].value=="";
                    document.forms[0].elements["mobile"].focus();
                    return false;
                }
                document.forms[0].mode.value="updatePostBlogDetails";
                document.forms[0].submit();
            }

            function goForEdit(id,name,subject,description,mobile,email) {
               
                document.forms[0].elements["name"].value=name;
                document.forms[0].elements["subject"].value=subject;
                document.forms[0].elements["description"].value=description;
                document.forms[0].elements["mobile"].value=mobile;
                document.forms[0].elements["email"].value=email;
                document.forms[0].rowForEdit.value=id;
                
                document.getElementById("insert").style.display="none";
                document.getElementById("update").style.display="";
            }

            function goForDelete(id)
            {
                var conf=confirm("Are You Sure! you want to Delete ?")
                if (conf==true)
                {
                    document.forms[0].action ="./blogPost.do?mode=inactiveBlogPostRecord&inactive="+id;
                    document.forms[0].submit();
                }
            }

        </script>

    </head>
    <body>
        <html:form action="/blogPost" focus="name" enctype="multipart/form-data" >
            <html:hidden property="mode"/>
            <input type="hidden" name="rowForEdit">
            <logic:present name="msg">
                <center><%=request.getAttribute("msg")%></center>
            </logic:present>
            <table align="center" cellpadding="0" cellspacing="0" border="0" width="90%" class="inputform">
                <tr>
                    <td valign="top">
                        <table cellpadding="0" cellspacing="0" border="0" class="inputform">
                            
                                <th >Leave a Blog Post</th>
                            
                           
                            <tr>

                                <td>
                                    <table align="center" cellpadding="0" cellspacing="1" border="0" class="inputform" >
                                        <tr >
                                            <td>
                                                Name
                                            </td>
                                            <td >
                                                <html:text property="name" onkeypress="return inputLimiter(event,'Letters');" maxlength="100" size="44"/>
                                            </td>
                                        </tr>
                                        <tr >
                                            <td>
                                                Subjects
                                            </td>
                                            <td >
                                                <html:select property="subject" style="width:300px;">
                                                    <html:option value="00">- - Subjects - -</html:option>
                                                    <html:optionsCollection property="subjects" label="subjectName" value="subjectId"/>
                                                </html:select>
                                            </td>
                                        </tr>
                                        <tr >
                                            <td>
                                                Description
                                            </td>
                                            <td >
                                                <html:textarea property="description" onkeypress="return inputLimiter(event,'Letters');" rows="5" cols="100" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                Mobile
                                            </td>
                                            <td >
                                                <html:text property="mobile" onkeypress="return inputLimiter(event,'Numbers');" maxlength="10" size="44" />
                                            </td>
                                        </tr>
                                        <tr >
                                            <td>
                                                Email
                                            </td>
                                            <td >
                                                <html:text property="email" onchange="checkEmail(this.value)" maxlength="100" size="44" />
                                            </td>
                                        </tr>
                                        <tr >
                                            <td>
                                                Upload Photo
                                            </td>
                                            <td >
                                                <div id="imageId">
                                                    <html:file property="postUploadPhoto" size="31" onchange="javascript: uploadJPG(this,'imageId')"/>
                                                </div>
                                            </td>
                                        </tr>

                                        <logic:notPresent name="updateStat">
                                            <tr id="insert">
                                                <th colspan="2" align="center">
                                                    <html:button property="but" value="Post Blog" onclick="saveBlogPostDetails();"/>
                                                </th>
                                            </tr>
                                        </logic:notPresent>
                                        <tr id="update" style="display: none;">
                                            <th colspan="2" align="center">
                                                <html:button property="subprop" value="Update Blog Post" onclick="updateBlogPost();" />
                                            </th>
                                        </tr>
                                        <logic:present name="updateStat">
                                            <tr id="update">
                                                <th colspan="2" align="center">
                                                    <html:button property="subprop" value="Update Blog Post" onclick="updateBlogPost();" />
                                                </th>
                                            </tr>
                                        </logic:present>

                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td align="center"><br/>
                        <table align="center" cellpadding="0" cellspacing="0" border="1" width="96%" class="inputform">
                            <tr>
                                <th>
                                    Sno
                                </th>
                                <th>
                                    Name
                                </th>
                                <th>
                                    Subject
                                </th>
                                <th>
                                    Description
                                </th>
                                <th>
                                    Mobile
                                </th>
                                <th>
                                    Email
                                </th>
                                <th>
                                    Posted Date
                                </th>
                                <th>
                                    Action
                                </th>
                            </tr>
                            <logic:empty name="blogsList">
                                <tr align="center">
                                    <td colspan="10" align="center"><font color="red" size="2">Posted Blogs Not Updated</font></td>
                                </tr>
                            </logic:empty>
                            <logic:notEmpty name="blogsList">
                                <logic:iterate id="ccla" name="blogsList" scope="request">
                                    <tr>
                                        <td style="width: 20px">
                                            <center> <%=i++%>.</center>
                                        </td>
                                        <td>
                                            ${ccla.name}
                                        </td>
                                        <td>
                                            ${ccla.subjectName}
                                        </td>
                                        <td>
                                            ${ccla.description}
                                        </td>
                                        <td>
                                            ${ccla.mobile}
                                        </td>
                                        <td>
                                            ${ccla.email}
                                        </td>
                                        <td>
                                            ${ccla.postedDate}
                                        </td>
                                        <td width="12%" style="text-align:center">
                                            <a href="#" onclick="goForEdit('${ccla.rowId}','${ccla.name}','${ccla.subject}','${ccla.description}','${ccla.mobile}','${ccla.email}','${ccla.postedDate}');">
                                                <img src="./DisabilityUITG/images/Edit.png" alt="Edit" ></a>&nbsp; <a href="#" onclick="goForDelete(${ccla.rowId});"><img src="./DisabilityUITG/images/delete.png" alt="delete" style="width: 25px" ></a>
                                        </td>
                                    </tr>
                                </logic:iterate>
                            </logic:notEmpty>
                        </table>

                    </td>
                </tr>
            </table>
        </html:form>
    </body>
</html>
