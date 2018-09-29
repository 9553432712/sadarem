<%-- 
    Document   : PhotoGallery
    Created on : 17 Oct, 2013, 12:32:22 PM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
            int j = 1, k = 1;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="JavaScript" src="./DisabilityUITG/js/validation.js"></script>
        <script>
            function storeValues() {

                if(document.forms[0].elements['event'].value=="") {
                    alert("Please Enter Event Name");
                    document.forms[0].elements['event'].value="";
                    document.forms[0].elements['event'].focus();
                    return false;
                }else if(document.forms[0].elements['description'].value=="00") {
                    alert("Please Enter Description");
                    document.forms[0].elements['description'].value="00";
                    document.forms[0].elements['description'].focus();
                    return false;
                }else if(document.forms[0].elements['noOfPhotos'].value=="") {
                    alert("Please Enter No of Photos");
                    document.forms[0].elements['noOfPhotos'].value="";
                    document.forms[0].elements['noOfPhotos'].focus();
                    return false;
                }

                for(var i = 0;i < document.forms[0].elements["photoEnteredCount"].value;i++) {
                    if(document.forms[0].elements["uploadPhoto["+i+"]"].value=="") {
                        alert("Please Upload The "+(i+1)+ " Photo");
                        return false;
                    }
                }            

                document.forms[0].mode.value="saveDetails";
                document.forms[0].submit();
            }

            function getPhotoNos() {
                document.forms[0].mode.value="getPhotoCount";
                document.forms[0].submit();
            }

            function goForEdit(id,event,description) {
                document.forms[0].elements["event"].value=event;
                document.forms[0].elements["description"].value=description;
                document.forms[0].rowForEdit.value=id;
            }
            function goForDelete(id)
            {
                var conf=confirm("Are You Sure! you want to Delete ?")
                if (conf==true)
                {
                    document.forms[0].action ="./photoGallery.do?mode=inactiveRecord&inactive="+id;
                    document.forms[0].submit();
                }
            }

        </script>
    </head>
    <body>
        <html:form action="/photoGallery" enctype="multipart/form-data" focus="event" method="post">
            <input type="hidden" name="csrfPreventionSalt" value="${randNumb}"/>
            <html:hidden property="mode"/>
            <input type="hidden" name="rowForEdit">
            <input type="hidden" name="photoEnteredCount" value="<%=request.getAttribute("totalPhotos")%>">
            <logic:present name="msg">
                <center><%=request.getAttribute("msg")%></center>
            </logic:present>


            <table align="center" cellpadding="0" cellspacing="1" border="0" class="inputform" width="90%">
                <tr>
                    <th colspan="2" >
                        Photo Gallery
                    </th>
                </tr>
                <tr align="right">
                    <td>
                        Event
                    </td>
                    <td align="left">
                        <html:text property="event" onkeypress="return inputLimiter(event,'Letters');" maxlength="100" size="44" onkeyup="javascript:removeSpecialChar(this)"/>
                    </td>
                </tr>
                <tr align="right">
                    <td>
                        Description
                    </td>
                    <td align="left">
                        <html:textarea property="description" onkeypress="return inputLimiter(event,'Letters');" cols="38" rows="5" />
                    </td>
                </tr>
                <tr align="right">
                    <td>
                        No of Photos
                    </td>
                    <td align="left">
                        <html:text property="noOfPhotos" onkeypress="return inputLimiter(event,'Numbers');" maxlength="2" onchange="getPhotoNos(this.value);" size="44" onkeyup="javascript:removeSpecialChar(this)"/><br/>
                        <font color="blue">( Mention photos count that you want to upload )</font>
                    </td>
                </tr>
                <logic:present name="totalPhotos" scope="request">

                    <%for (int i = 0; i < Integer.parseInt(request.getAttribute("totalPhotos").toString()); i++) {%>
                    <tr align="right">
                        <td>
                            Upload Photo : <%=i + 1%>
                        </td>
                        <td align="left">
                            <input type="file" name="uploadPhoto[<%=i%>]" size="31"/>
                        </td>
                    </tr>
                    <%}%>
                    <tr>
                        <th colspan="2" style="text-align: center">
                            <html:button property="subbut" value="Submit" onclick="storeValues();"/>
                        </th>
                    </tr>
                </logic:present>

            </table>
                        <br/>
            <table align="center" cellpadding="0" cellspacing="1" border="0" class="inputform" width="90%">
                <logic:notEmpty name="galleryEvents">
                    <tr>
                        <th class="nav-txt"><font size="3">
                                Events :</font>
                        </th>
                    </tr>

                <logic:iterate id="row" name="galleryEvents" scope="request">
                <tr>
                    <td>
                        <br/> &nbsp;&nbsp;&nbsp;&nbsp;<%=k++%>.<a href="./photoGallery.do?mode=getEvents&eventFlag=${row.event}"> ${row.event}</a>
                    </td>
                </tr>
            </logic:iterate>
        </logic:notEmpty>

        <tr>
            <td>&nbsp;</td>
        </tr>

        <logic:notEmpty name="galleryDetails">
            <tr>
                <td align="center">
                    <table align="center" cellpadding="0" cellspacing="1" border="0" width="100%" class="inputform">
                        <tr>
                            <th>
                                Sno
                            </th>
                            <th>
                                Event
                            </th>
                            <th>
                                Discription
                            </th>
                            <th>
                                View
                            </th>
                            <th>
                                Action
                            </th>
                        </tr>


                        <logic:iterate id="row" name="galleryDetails" scope="request">
                            <tr>
                                <td width="12px;">
                                    <center> <%=j++%>.</center>
                                </td>
                                <td>
                                    ${row.event}
                                </td>
                                <td>
                                    ${row.description}
                                </td>

                                <td width="30px">
                                    <center><img src="./PhotoGallery/${row.uploadphoto}" width="71px" height="88px" alt=""/></center>
                                </td>
                                <td width="12%" style="text-align:center">
                                   <%-- <a href="#" onclick="goForEdit('${row.RowId}','${row.event}','${row.description}')"><img src="images/Edit.png" alt="Edit" ></a>&nbsp;--%>
                                    <a href="#" onclick="goForDelete('${row.RowId}')"><img src="images/delete.png" alt="delete"  ></a>
                                </td>
                            </tr>
                        </logic:iterate>
                    </table>
                </td>
            </tr>
        </logic:notEmpty>
</table>
    </html:form>
</body>
</html>
