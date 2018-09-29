<%-- 
    Document   : NewsandEvents
    Created on : Jun 7, 2013, 4:22:04 PM
    Author     : 728056
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>
    <%
                String newsId = (String) request.getAttribute("newsId");
                int i = 1;
                String path = request.getContextPath();
                String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <script>
            function textCounter(field,cntfield,maxlimit) {
                if (field.value.length > maxlimit){  // if too long...trim it!
                    field.value = field.value.substring(0, maxlimit);
                    // otherwise, update 'characters left' counter
                }
                else{
                    cntfield.value = maxlimit - field.value.length;
                }

            }
            function newsNevents(){
                var fromdate = document.forms[0].elements['fromDate'].value;
                var todate =document.forms[0].elements['toDate'].value;

                if(document.forms[0].elements['newsTitle'].value==""){
                    alert("Please Enter Title");
                    document.forms[0].elements['newsTitle'].focus();
                    return false;
                }else if(document.forms[0].elements['newsDescription'].value==""){
                    alert("Please Enter Description");
                    document.forms[0].elements['newsDescription'].focus();
                    return false;
                }else if(document.forms[0].elements['fromDate'].value=="") {
                    alert("Please Enter  FromDate");
                    document.forms[0].elements['fromDate'].focus();
                    return false;
                }else if(document.forms[0].elements['toDate'].value=="") {
                    alert("Please Enter To Date");
                    document.forms[0].elements['toDate'].focus();
                    return false;
                }
                else{
                    document.forms[0].mode.value="insertNewsNevents";
                    document.forms[0].submit();
                }

            }
            
            function updateNews(){	
                var fromdate = document.forms[0].elements['fromDate'].value;
                var todate =document.forms[0].elements['toDate'].value;
      
                if(document.forms[0].elements['newsTitle'].value==""){
                    alert("Please Enter Title");
                    document.forms[0].elements['newsTitle'].focus();
                    return false;
                }else if(document.forms[0].elements['newsDescription'].value==""){
                    alert("Please Enter Description");
                    document.forms[0].elements['newsDescription'].focus();
                    return false;
                }else if(document.forms[0].elements['fromDate'].value=="") {
                    alert("Please Enter  FromDate");
                    document.forms[0].elements['fromDate'].focus();
                    return false;
                }else if(document.forms[0].elements['toDate'].value=="") {
                    alert("Please Enter To Date");
                    document.forms[0].elements['toDate'].focus();
                    return false;
                }
                document.forms[0].mode.value="updateNewsDetails";
                document.forms[0].submit();

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

            function capitalizeMe(obj) {
                var val = obj.value;
                var newVal = '';
                if(val != ""){
                    val = val.split(' ');
                    for(var c=0; c < val.length; c++) {
                        newVal += val[c].substring(0,1).toUpperCase() +
                            val[c].substring(1,val[c].length) + ' ';
                    }
                }
                obj.value = newVal;

            }

            function disp_confirm(id){
                var row=document.forms[0].newsId.value;
                if(row!=id){
                    var name=confirm("Are You Sure! you want to Delete ?")
                    if (name==true)
                    {

                        document.forms[0].action ="./newsandEvents.do?mode=deleteNewsDetails&type=newsEvents&tokenCodeChecking=${tokenCode}&newsId="+id;
                        document.forms[0].submit();
                        //window.close();
                    } else { }
                }
                else{
                    alert('Cannot Delete the Field that is selected');

                }

            }

          
            function uploadPhoto(str,divid)
            {

                var ext = str.value;
                ext = ext.substring(ext.lastIndexOf(".")+1,ext.length);
                if(ext != 'jpg' && ext != 'jpeg' && ext != 'png' && ext != 'bmp')
                {
                    alert('You selected a " .'+ext+' "  file; Please Select a " .jpg/.jpeg/.png/.bmp " file');

                    document.getElementById(divid).innerHTML =document.getElementById(divid).innerHTML;


                    return false;
                }
            }


        </script>
        <script>
            $(function() {
                $( "#fromdate" ).datepicker( {
                    "showAnim": "slideDown" ,
                    showOn: "button",
                    buttonImage: "./DisabilityUITG/images/calendar.png",
                    buttonImageOnly: true
                    ,changeMonth: true,
                    changeYear: true,
                    dateFormat:"dd/mm/yy",
                    minDate:'0d',
                    onClose: function( selectedDate ) {
                        $( "#todate" ).datepicker( "option", "minDate", selectedDate );
                    }
                });
                $( "#todate" ).datepicker( {
                    "showAnim": "slideDown" ,
                    showOn: "button",
                    buttonImage: "./DisabilityUITG/images/calendar.png",
                    buttonImageOnly: true
                    ,changeMonth: true,
                    changeYear: true,
                    dateFormat:"dd/mm/yy",
                    minDate:'0d',
                    onClose: function( selectedDate ) {
                        $( "#fromdate" ).datepicker( "option", "maxDate", selectedDate );
                    }
                });
            });
        </script>
    </head>
    <body>
        <html:form action="/newsandEvents"  enctype="multipart/form-data">
            <html:hidden property="mode"/>
            <html:hidden property="newDataId" value="<%=newsId%>"/>
            <html:hidden property="newsId"/>
            <input type="hidden" name="tokenCodeChecking" value="${tokenCode}"/>

            <logic:present name="Suuces">

                <center>
                    ${Suuces}
                </center>
            </logic:present>

            <logic:present name="msg">
                <center>
                    ${msg}
                </center>
            </logic:present>


            <logic:present name="fail">
                <center> <font color="red" size="2">
                        ${fail}
                    </font>
                </center>
            </logic:present>
            <table align="center" width="70%" border="1" cellspacing="0" cellpadding="0" class="inputform">
                <tr>
                    <th colspan="4"  >News and Events</th>
                </tr>

                <tr>
                    <td  >Title <font color="red"><b>* </b></font></td>
                    <td><html:text property="newsTitle" maxlength="50" onkeypress="return space(event,this);" onchange="capitalizeMe(this);" style="height:25px;width:250px"/></td>
                </tr>
                <tr>
                    <td  >Description<font color="red"><b>* </b></font></td>
                    <td><html:textarea property="newsDescription"  style="border:1px #ccc solid;height:75px" rows="20" cols="80" onkeydown="textCounter(this,document.forms[0].remLen1,1000)" onkeyup="textCounter(this,document.forms[0].remLen1,1000)" onkeypress="return space(event,this);" onchange="capitalizeMe(this);" style="height:70px;width:250px"/>
                        <br><input readonly type="text" name="remLen1" size="4" maxlength="4" value="1000" style="width:40px"/></td>
                </tr>

                <tr>
                    <td width="22%"  align="right">From Date: <font color="red">*</font></td>
                    <td width="28%" class="formbg2" align="left"><html:text property="fromDate" styleId="fromdate" style="height:25px;width=200px" readonly="true" title="dd/mm/yyyy" /></td>
                </tr>
                <tr>
                    <td width="22%"  align="right">To Date: <font color="red">*</font></td>
                    <td width="28%" class="formbg2" align="left"><html:text property="toDate" styleId="todate" style="height:25px;width=200px" readonly="true" title="dd/mm/yyyy" /></td>
                </tr>
                <tr>
                    <td width="22%"  align="right">Is It New / Important: <font color="red"></font></td>
                    <td width="28%" class="formbg2" align="left" style="width: 25px">
                        New<html:radio property="flag" value="New"  />&nbsp;&nbsp;
                        Important<html:radio property="flag" value="Imp"  />

                    </td>
                </tr>


                <tr>
                    <td  >News Upload <font color="red"></font></td>
                    <td>
                        <div id="photo">
                            <html:file property="newsUpload" size="30" style="height:25px;width:250px"/>
                        </div>

                    </td>
                </tr>



                <br/>

                <%if (request.getAttribute("editData") == null) {%>
                <tr>
                    <td colspan="2" style="text-align: center">
                        <html:button property="sub" value="Submit" onclick="return newsNevents();"/>
                    </td>
                </tr>


                <%}%>

                <%if (request.getAttribute("editData") != null) {%>

                <tr>
                    <td colspan="2" style="text-align: center">
                        <html:button property="sub" value="update" onclick="return updateNews();"/>
                    </td>
                </tr>
                <%}%>

            </table><br/>

            <logic:notEmpty name="newList">
                <table align="center" width="90%" border="0" cellspacing="0" cellpadding="0" class="inputform">
                    <tr>
                        <th >News and Events</th>
                    </tr>
                    <tr>
                        <td align="center" valign="top" >
                            <table width="100%" border="0" cellspacing="1" cellpadding="0" class="inputform">
                                <th >S No.</th>
                                <th>NewsTitle</th>
                                <th >Description</th>
                                <th >From Date</th>
                                <th>To Date</th>
                                <th>View</th>
                                <th>Edit</th>
                                <th>Delete</th>
                                <logic:iterate id="row" name="newList">
                                    <tr>
                                        <td   align="right"><%=i++%></td>
                                        <td   style="text-align: left">${row.newsTitle}</td>
                                        <%--<td  class="formbg2" align="right">${row.description}</td>--%>
                                        <td  style="text-align: left;word-wrap:break-word;height: 100%;vertical-align: top">${row.description}</td>
                                        <td   align="center">&nbsp;${row.fromdate}</td>
                                        <td   align="center">&nbsp;${row.todate}</td>
                                        <td   align="right">${row.filename}</td>

                                        <td   align="right"><a href="./newsandEvents.do?mode=editNewsDetails&type=newsEvents&newsId=${row.newsId}"><img src="./DisabilityUITG/images/Edit.png"  height="25px" width="25px" style="border:0"/></a></td>
                                        <td   align="right">

                                            <a href="#"><img src="./DisabilityUITG/images/delete.png" border="0" onclick="disp_confirm(${row.newsId});" height="25px" width="25px"  alt="Delete"/></a>
                                        </td>
                                    </tr>
                                </logic:iterate>
                            </table>
                        </td>
                    </tr>
                </table>
            </logic:notEmpty>



        </html:form>
        <%if (request.getParameter("newsId") != null) {%>
        <input type="hidden" name="row" value="<%=request.getParameter("newsId")%>" id="rowValue">
        <script>
            document.getElementById("hide"+document.getElementById("rowValue").value).disabled=true;
        </script>

        <%}%>

    </body>
</html>
