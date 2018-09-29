<%-- 
    Document   : Blog
    Created on : 25 Nov, 2013, 12:35:53 PM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script type="text/javascript">
            var xmlHTTP = false;
            var ajaxRequest;
            //Browser Support Code
            function ajaxFunction(){
                //  var ajaxRequest;  // The variable that makes Ajax possible!

                try{
                    // Opera 8.0+, Firefox, Safari
                    ajaxRequest = new XMLHttpRequest();
                }catch (e){
                    // Internet Explorer Browsers
                    try{
                        ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
                    }catch (e) {
                        try{
                            ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
                        }catch (e){
                            // Something went wrong
                            alert("Your browser broke!");
                            return false;
                        }
                    }
                }
            }


            function retrieveAnswers(postId, subject)
            {
                
                ajaxFunction();
                ajaxRequest.onreadystatechange=parseAnswersList;
                var url="blog.do?mode=getAnswers&postIdForAnswer="+postId+"&SubjectIdForAnswer="+subject+"";
                ajaxRequest.open("GET",url,true)
                ajaxRequest.send(null);
            }
         
            function parseAnswersList() {

                if(!(ajaxRequest.readyState == 4 || ajaxRequest.readyState == "complete")) {
                    var answers=ajaxRequest.responseText;
                    if(answers!=null) {
                        document.getElementById("answersBlock").style.display="";
                        document.getElementById("answersDIV").innerHTML = answers;
                    }
                    
                }

            }
        </script>

        <script type="text/javascript">
            function showPostArea(id) {
                document.getElementById("blogEntryArea"+id).style.display="";
                document.getElementById("blogEntryArea"+(id-1)).style.display="none";
            }

            function saveDetails(id) {
               
                if(document.forms[0].elements["name"+id].value=="") {
                    alert("Please Enter Name");

                    document.forms[0].elements["name"+id].value=="";
                    document.forms[0].elements["name"+id].focus();
                    return false;
                }else if(document.forms[0].elements["description"+id].value=="") {
                    alert("Please Enter Description");
                    document.forms[0].elements["description"+id].value=="";
                    document.forms[0].elements["description"+id].focus();
                    return false;
                }else if(document.forms[0].elements["mobile"+id].value=="") {
                    alert("Please Enter Mobile Number");
                    document.forms[0].elements["mobile"+id].value=="";
                    document.forms[0].elements["mobile"+id].focus();
                    return false;
                }
                document.forms[0].mode.value="saveDetails";
                document.forms[0].submit();
            }

            

            function getPosts(id)
            {               
                document.forms[0].action ="./blog.do?mode=getPosts&id="+id;
                document.forms[0].submit();               
            }

        

        </script>
    </head>
    <body>
        <html:form action="/blog" focus="name" enctype="multipart/form-data">
            <html:hidden property="mode"/>

            <logic:present name="msg">
                <center><%=request.getAttribute("msg")%></center>
            </logic:present>
            <table cellpadding="0" cellspacing="0" border="0"class="inputform"  width="90%" >

                <tr>
                    <th colspan="2">Blog</th>
                </tr>

                <tr>
                    <td align="left" width="70%" valign="top">
                        <logic:notEmpty name="postedBlogs">
                            <table align="left" cellpadding="0" cellspacing="1" width="100%" class="inputform">
                                <logic:iterate id="ccla" name="postedBlogs">
                                    <tr>
                                        <td width="25%">
                                            ${ccla.uploadFile}<font color="gray" style="font-style: italic;">&nbsp;&nbsp;&nbsp;Posted On : ${ccla.postedDate}</font>
                                            <br/>
                                            <font color="gray" style="font-style: italic;"><b>${ccla.name}</b></font>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3" width="75%">
                                             ${ccla.description}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="left">
                                            <a href="javascript:retrieveAnswers('${ccla.rowId}','${ccla.subject}');">Discussions ( <font color="blue">${ccla.answerCount}</font> )</a>
                                            &nbsp;&nbsp;
                                            <a href="javascript:showPostArea('${ccla.rowId}');">Reply</a>
                                        </td>
                                    </tr>
                                    <tr style="display: none;" id="answersBlock">
                                        <td>
                                            <span id="answersDIV"></span>
                                        </td>
                                    </tr>
                                    <tr align="center" style="display: none;" id="blogEntryArea${ccla.rowId}">
                                    <input type="hidden" name="rowIdForInsert" value="${ccla.rowId}"/>
                                    <input type="hidden" name="subjectIdForReply" value="${ccla.subject}"/>
                                    <td>
                                        <table align="center" cellpadding="0" cellspacing="0" border="1" class="inputform">
                                            <tr>
                                                <td >
                                                    <table align="center" cellpadding="0" cellspacing="1" border="0" class="inputform" width="90%" style="border:1px solid #6c9719;" >
                                                        <tr align="right">
                                                            <td>
                                                                Name
                                                            </td>
                                                            <td align="left">
                                                                <input type="text" name="name${ccla.rowId}" onkeypress="return inputLimiter(event,'Letters');" maxlength="100" size="44"/>
                                                            </td>
                                                        </tr>
                                                        <tr align="right">
                                                            <td>
                                                                Description
                                                            </td>
                                                            <td align="left">
                                                                <textarea name="description${ccla.rowId}" onkeypress="return inputLimiter(event,'Letters');" rows="5" cols="200" ></textarea>
                                                            </td>
                                                        </tr>
                                                        <tr align="right">
                                                            <td>
                                                                Mobile
                                                            </td>
                                                            <td align="left">
                                                                <input type="text" name="mobile${ccla.rowId}" onkeypress="return inputLimiter(event,'Numbers');" maxlength="10" size="44" />
                                                            </td>
                                                        </tr>
                                                        <tr align="right">
                                                            <td>
                                                                Email
                                                            </td>
                                                            <td align="left">
                                                                <input type="text" name="email${ccla.rowId}" onchange="checkEmail(this.value)" maxlength="100" size="44" />
                                                            </td>
                                                        </tr>                                                        
                                                        <tr>
                                                            <td colspan="2" align="right">
                                                                <html:button property="but" value="Post Blog" onclick="saveDetails(${ccla.rowId});"/>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                        </tr>
                    </logic:iterate>
                </table>
            </logic:notEmpty>
        </td>
    <td align="right" width="30%" valign="top" height="275px">
        <logic:notEmpty name="blogSubjects">
            <table align="right" cellpadding="0" cellspacing="1"  class="inputform">
                <tr>
                    <th align="left">
                          Blog Subjects
                    </th>
                </tr>
                <logic:iterate id="ccla" name="blogSubjects">
                    <tr>
                        <td align="left">
                            <a href="#" onclick="getPosts('${ccla.subjectid}');">${ccla.subject} ( ${ccla.count} )</a>
                        </td>
                    </tr>
                </logic:iterate>
            </table>
        </logic:notEmpty>
    </td>
</tr>
<logic:present name="notAvailablePostedBlogs">
    <tr align="left" valign="top">
        <td align="left" colspan="2">
            <center>^^^^^^^^^^^^^^^^<font color="red" style="font-style: italic">&nbsp;<bean:write name="notAvailablePostedBlogs"/>&nbsp;</font>^^^^^^^^^^^^^^^^</center>
        </td>
    </tr>
</logic:present>
</table>
</html:form>
</body>
</html>
