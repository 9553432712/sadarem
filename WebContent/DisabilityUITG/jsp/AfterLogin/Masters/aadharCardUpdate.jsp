<%--
   Document   : aadharCardUpdate
   Created on : 1 Nov, 2014, 3:33:29 PM
   Author     : 310926
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page  import="java.util.*;" %>
<%String propertyName = null, styleId = null, radiostyleId = null;
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
            int i = 1;
            ArrayList wsRationCardDetailsList = null;
            if (request.getAttribute("wsRationCardDetailsList") != null) {
                wsRationCardDetailsList = (ArrayList) request.getAttribute("wsRationCardDetailsList");
            }
            int j = 0;
            String personCode = null;
            if (request.getAttribute("personcode") != null) {
                personCode = request.getAttribute("personcode").toString();
            }
            String rationCardNo = null;
            if (request.getAttribute("rationCardNo") != null) {
                rationCardNo = request.getAttribute("rationCardNo").toString();
            }
            String aadharCardNo = null;
            if (request.getAttribute("aadharCardNo") != null) {
                aadharCardNo = request.getAttribute("aadharCardNo").toString();
            }
            int p = 1;

%>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%=basePath%>Styles/RR-Style.css" type="text/css" rel="stylesheet" />
        <link href="<%=basePath%>Styles/Left-Menu.css" type="text/css" rel="stylesheet" />

        <script type="text/javascript">
            var  adharCardNo = null;
            <% if (request.getAttribute("aadharCardNo") != null) {%>
                aadharCardNo = '<%=request.getAttribute("aadharCardNo")%>';
            <%}%>
                function getAadharCardDetails(){
                    var personcode='<%=personCode%>';
                    var rationCardNo='<%=rationCardNo%>';
                    if(document.forms[0].elements["aadharCardId"].value.length <12){
                        alert("Aadhar Card No Should be 12 Digits");
                        document.forms[0].elements["aadharCardId"].value="";
                        document.forms[0].elements["aadharCardId"].focus();
                        return false;
                    }

            <%--  document.forms[0].action='aadharCardMapping.do?mode=getAadharCardDetails&flag=habitation&personcode='+personcode+'&rationCardNo='+rationCardNo;
              document.forms[0].submit();--%>
                  }
                  function getAadharCardDetails1(rowid){
                      var personcode='<%=personCode%>';
                      var rationCardNo='<%=rationCardNo%>';
                    
                      var aadhar=document.getElementById(rowid+"#1").value;
                   
                      if(aadhar.length !=12){
                          var aadharCardNo=document.getElementById(rowid+"#2");
                          //aadharCardNo.checked=false;
                          alert("Aadhar Card No Should be 12 Digits");
                          document.getElementById(rowid+"#1").value="";
                          document.getElementById(rowid+"#1").focus();
                          return false;
                      }

            <%--    document.forms[0].action='aadharCardMapping.do?mode=getAadharCardDetails&flag=habitation&aadharCardId='+aadhar+'&personcode='+personcode+'&rationCardNo='+rationCardNo;
                document.forms[0].submit();--%>
                    }
                    function clearValue(){
                        //  document.forms[0].elements["aadharCardId"].value="";
                    }
                    function updatePersonalDetails(p){
                        
                        var aadhargiven=null;
                        var personcode='<%=personCode%>';
                        var rationCardNo='<%=rationCardNo%>';
                        var rows=p;
                        var checked=false;
                        var selected=false;
                        
                        var scheck=document.getElementsByName("radioValue");
                   
                        for(var i=0;i<scheck.length;i++){
                            if(scheck[i].checked){
                                checked=true;
                                var a=scheck[i].value;
                                    selected=a;
                            }
                        }                 
                        for(var i=1;i<=rows;i++){
                            if( document.getElementById(i+"#1")!=null &&
                                document.getElementById(i+"#1").value!=null &&  document.getElementById(i+"#1").value!=""){
                                aadhargiven=document.getElementById(i+"#1").value;
                            }
                        }                       
                        
                        
                        if(document.forms[0].elements["radioValue"]!=null){
                         
                            if(!checked){
                                alert("Please Select Atleast One Radio Button");
                                return false;
                            }else if(selected=="on" && aadhargiven==null){
                                alert("Please Enter AadharCard No");
                                document.getElementById("aadharCardId").focus();
                                return false;
                            }
                        }else{
                            if(document.forms[0].elements["aadharCardId"].value.length==0){
                                alert("Please Enter AadharCard No");
                                document.getElementById("aadharCardId").focus();
                                return false;
                            }

                        }
                        if(aadhargiven==null){
                            aadhargiven =document.forms[0].elements["aadharCardId"].value;
                        }
                        document.getElementById('detailsButton').style.display='none';
                        document.getElementById('processing').style.display='';
                        document.forms[0].action='aadharCardMapping.do?mode=updatePersonalDetails&flag=habitation&aadharCardId='+aadhargiven+'&personcode='+personcode+'&rationCardNo='+rationCardNo;
                        document.forms[0].submit();
                    }
                    function onlyNumbers(evt) {
                        var charCode = (evt.which) ? evt.which : event.keyCode
                        if (charCode > 31 && (charCode < 48 || charCode > 57))
                            return false;

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


                    function enableaadhar(rowid){
            <%--  var rows='<%=p%>';
              alert(rows)
              for(var i=1;i<=rows;i++){
alert(i);
alert(rowid);
                  if(rowid==i){--%>
                          var aadharCardNo=document.getElementById(rowid+"#1");
                          aadharCardNo.readOnly=false;<%--}else{
                      var aadharCardNo=document.getElementById(rowid+"#1");
                      aadharCardNo.readOnly=true;
                  }
              }--%>
                  }
        </script>

    </head>
    <body >
        <html:form action="/aadharCardMapping">
            <html:hidden property="mode"/>

            <input type="hidden" name="personC" value="<%=personCode%>"/>

            <logic:present name="errmsg">
                <font color="red">
                    ${errmsg}</font>
                </logic:present>
                <logic:present name="nodata">
                <p id="errmsg">${nodata}</p>
            </logic:present>
            <table    align="center" width="90%" border="0" cellspacing="1" cellpadding="0" class="inputform">

                <logic:notEmpty name="sadaremCommonDetails">
                    <tr>
                        <th colspan="11">AadharCard Mapping</th>
                    </tr>
                    <tr>

                        <th>
                            <b> SADAREM ID</b>
                        </th>
                        <th>
                            <b>  Pension ID</b>
                        </th>
                        <th>
                            <b>  Name</b>
                        </th>
                        <th>
                            <b>  Relation Name</b>
                        </th>
                        <th>
                            <b> Gender</b>
                        </th>
                        <th>
                            <b> Age</b>
                        </th>
                        <th>
                            <b> Education</b>
                        </th>
                        <th>
                            <b> Caste</b>
                        </th>
                        <th>
                            <b> Ration Card Number</b>
                        </th>
                        <th>
                            <b> Assessment Status</b>
                        </th>
                        <th>
                            <b> Address</b>
                        </th>


                    </tr>
                    <logic:iterate name="sadaremCommonDetails" id ="row">
                        <tr>


                            <td>
                                ${row.personCode}
                                <html:hidden property="aadharPersonCode" value="${row.personCode}"/>
                            </td>
                            <td>
                                ${row.pensionCardNo}
                            </td>

                            <td>
                                ${row.name}
                            </td>
                            <td>
                                ${row.relationName}
                            </td>
                            <td>
                                ${row.gender}
                            </td>
                            <td>
                                ${row.age}
                            </td>

                            <td>
                                ${row.education}
                            </td>
                            <td>
                                ${row.caste}
                            </td>
                            <td>
                                ${row.rationCardNo}
                            </td>
                            <td>
                                ${row.assessmentstatus}
                            </td>
                            <td>
                                ${row.address}
                            </td>


                        </tr>

                    </logic:iterate>

                </logic:notEmpty>
            </table>
            <br/>
            <table    align="center" width="90%" border="0" cellspacing="1" cellpadding="0" class="inputform">
                <logic:notEmpty name="sadaremDetails">

                    <tr>
                        <th>
                            <b>S.No</b>
                        </th>
                        <th>
                            <b> SADAREM ID</b>
                        </th>
                        <th>
                            <b>  Name</b>
                        </th>
                        <th>
                            <b>  Relation Name</b>
                        </th>
                        <th>
                            <b>  Pension ID</b>
                        </th>
                        <th>
                            <b> Age</b>
                        </th>
                        <th>
                            <b> Gender</b>
                        </th>
                        <th>
                            <b> Aadhar Card No</b>
                        </th>


                    </tr>
                    <logic:iterate name="sadaremDetails" id ="row">
                        <tr>
                            <td>
                                <%=i++%>.
                            </td>

                            <td>
                                ${row.personCode}
                                <html:hidden property="aadharPersonCode" value="${row.personCode}"/>
                            </td>
                            <td>
                                ${row.name}
                            </td>
                            <td>
                                ${row.relationName}
                            </td>
                            <td>
                                ${row.pensionCardNo}
                            </td>

                            <td>
                                ${row.age}
                            </td>
                            <td>
                                ${row.gender}
                            </td>
                            <td>
                                <html:text property="aadharCardId" maxlength="12" onchange="getAadharCardDetails();" onkeypress="return onlyNumbers(event);"/>
                            </td>



                        </tr>

                    </logic:iterate>

                </logic:notEmpty>

                <logic:notEmpty name="wsRationCardDetailsList">

                    <tr>
                        <th>
                            <b>S.No</b>
                        </th>

                        <th>
                            <b>  Name</b>
                        </th>
                        <th>
                            <b>  Relation Name</b>
                        </th>

                        <th>
                            <b> Age</b>
                        </th>
                        <th>
                            <b> Map To <br>Aadhar Card</b>
                        </th>
                        <th>
                            <b> Aadharcard Number</b>
                        </th>





                    </tr>
                    <%int z = 1;%>
                    <logic:iterate name="wsRationCardDetailsList" id ="row" >
                        <tr>

                            <td>
                                ${row.slNo}
                            </td>
                            <td>
                                ${row.memberName}
                            </td>
                            <td>
                                ${row.relationName}
                            </td>


                            <td  style="text-align: center" >
                                ${row.age}
                            </td>

                        <input type="hidden" name="aadharCardId"/>

                        <%
                                    if (wsRationCardDetailsList != null) {
                                        for (int k = 0; k < wsRationCardDetailsList.size(); k++) {
                                            Map map = new HashMap();
                                            map = (Map) wsRationCardDetailsList.get(k);
                                            if (map.get("aadharcardNo") != null) {
                                                String aadharNo = map.get("aadharcardNo").toString();
                                                if (j == k) {
                                                    if (aadharNo != null && aadharNo != "" && aadharNo.length() > 0) {%>
                        <td style="text-align: center">
                            <input type="radio" name="radioValue" id="checkbox{z}" value="${row.aadharcardNo}"   style="width: 15px" onclick="clearValue();"/>
                        </td>
                        <td style="text-align: center">
                            ${row.aadharcardNo}
                        </td>

                        <%                                                                } else {
                        %>
                        <td style="text-align: center">
                            <input type="radio" name="radioValue" id="checkbox{z}"  onchange="enableaadhar('<%=p%>')" style="width: 15px"/>
                        </td>
                        <td style="text-align: center">
                            <%--<html:text property="aadharCardId" maxlength="12"   onchange="getAadharCardDetails1('<%=j%>');" onkeypress="return onlyNumbers(event);"/>
                            --%>
                            <%
                                                                                    propertyName = "dynaProperty(" + p + "#1)";
                                                                                    styleId = p + "#1";
                                                                                    radiostyleId = p + "#2";
                            %>

                            <input type="text" name="<%=propertyName%>"  id="<%=styleId%>" maxlength="12" onchange="getAadharCardDetails1('<%=p%>');" onkeypress="return onlyNumbers(event);" style="width:120px" readonly="true"/>

                        </td>
                        <%p++;%>

                        <%                 }
                                                }
                                            }
                                        }
                                    }
                        %>
                    </tr>
                    <%z++;%>
                    <input type="hidden"  value="<%=j++%>">
                </logic:iterate>

            </logic:notEmpty>
            <logic:present name="updateButton">
                <tr>
                    <th colspan="10" align="center" valign="middle" class="formbg2" style="text-align:center;">
                        <%--<html:button property="sub" value="Update" onclick="updatePersonalDetails('<%=p%>');"/>--%>
                        <input type="button" value="Submit" id="detailsButton" onclick="updatePersonalDetails('<%=p%>');">
                    </th>
                </tr>
            </logic:present>





        </table><br/>
        <table  align="center" cellspacing="1" cellpadding="0"  width="50%" border="0">
            <tr>
                <td  align="center" id="processing" style="display: none;">
                    <img src='<%=basePath%>DisabilityUITG/images/ajax-loader.gif'/>Please Wait..
                </td>
            </tr>
        </table>

        <!-- Body Ends -->
    </html:form>

</body>
</html>
