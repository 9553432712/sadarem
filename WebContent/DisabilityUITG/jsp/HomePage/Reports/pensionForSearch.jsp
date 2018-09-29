<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%
int i = 1;
String strMyStyle="";
%>
<html>
    <head>
	    <script src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
		<link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
		<title>:: SADAREM :: Quick Search</title>
		
		<style type="text/css">
			/* Process Layer Started */
	     	
	     	#processlayer
				{
					width: 300px;
					height: 50px;
					background:#ECF1EF;
					border: red dotted 5px;
					text-align: center;
					position: fixed;
					margin-right: -100px;
					margin-top: -75px;
					right: 50%;
					top: 50%;
					z-index: 99999;
					display: none;
				}
				
				#blocklayer
				{
					position: absolute;
					left: 0;
					top: 0;
					background: #ECF1EF;
				}
	     	
	    	 /* 	 Process Layer Ended  */
     	
		</style>
        <script>
        window.moveTo(screen.availLeft, screen.availTop,screen.width-screen.availWidth,screen.height-screen.availHeight);
		 window.resizeTo(screen.availWidth,screen.availHeight);


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


            function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }
            function validateform(){
             
                if(document.forms[0].elements['sadaremId'].value!="") 
                {
                    if(document.forms[0].elements['sadaremId'].value.length < "17") 
                    {
                        alert("Please Enter the Valid Sadarem Number");
                        //document.forms[0].elements['sadaremId'].value="";
                        document.forms[0].elements['sadaremId'].focus();
                        return false;
                    }
                }


                if( document.forms[0].elements['district_id'].value=="0" &&
                    document.forms[0].elements['mandal_id'].value=="0" && document.forms[0].elements['village_id'].value=="0" &&
                    document.forms[0].elements['name'].value=="" && document.forms[0].elements['parentsName'].value=="" &&
                    document.forms[0].elements['sadaremId'].value=="" && document.forms[0].elements['rationCardNo'].value==""&& document.forms[0].elements['aadharcardNumber'].value=="" ) {
                    alert("Please Select Atleast one Option");
                    return false;
                }
                var rationval = document.forms[0].elements['rationCardNo'].value;
                if(rationval.length>0){
                    if(document.forms[0].elements["rationCardNo"].value.length < 15) {
                        alert("Please Enter Valid RationCard No");
                        document.forms[0].elements["rationCardNo"].focus();
                        document.forms[0].elements["rationCardNo"].value="";
                        return false;
                    }else if(rationval.substring(0,3)!="WAP" && rationval.substring(0,3)!="PAP" && rationval.substring(0,3)!="AAY" &&
                        rationval.substring(0,3)!="AAP" && rationval.substring(0,3)!="YAP" && rationval.substring(0,3)!="wap" &&
                        rationval.substring(0,3)!="pap" && rationval.substring(0,3)!="aay" &&  rationval.substring(0,3)!="aap" &&
                        rationval.substring(0,3)!="yap" && rationval.substring(0,3)!="RAP" && rationval.substring(0,3)!="rap"
                        && rationval.substring(0,3)!="TAP" && rationval.substring(0,3)!="tap"
                        && rationval.substring(0,3)!="WAD" && rationval.substring(0,3)!="wad") {
                        alert("Please Enter Valid RationCard Number");
                        document.forms[0].elements["rationCardNo"].focus();
                        document.forms[0].elements["rationCardNo"].value="";
                        return false;
                    }
                }

                if(document.forms[0].elements['aadharcardNumber'].value!="") {
                 
                    if(document.forms[0].elements['aadharcardNumber'].value.length < "12") {
                     
                        alert("Please Enter the Valid Aadhar Card Number");
                        document.forms[0].elements['aadharcardNumber'].value="";
                        document.forms[0].elements['aadharcardNumber'].focus();
                        return false;
                    }
                }

                <%--if(document.forms[0].elements['personcode'].value!="")
                {
                    if(document.forms[0].elements['district_id'].value=="0") {
                        alert("Please Select District");
                        document.getElementById("district_id").focus()
                        return false;
                    }
                }--%>
                if(document.forms[0].elements['name'].value!="")
                {
                    if(document.forms[0].elements['district_id'].value=="0") {
                        alert("Please Select District");
                        document.getElementById("district_id").focus()
                        return false;
                    }
                }
                if(document.forms[0].elements['parentsName'].value!="")
                {
                    if(document.forms[0].elements['district_id'].value=="0") {
                        alert("Please Select District");
                        document.getElementById("district_id").focus()
                        return false;
                    }
                }
                
                /*Screen Locking Started */
      			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
      		    $('#processlayer').css({"display": "block","z-index":"110000"});
  				/*Screen Locking Ended */
  			
                document.forms[0].status.value="getResults";
                document.forms[0].submit();


            }

            function resetValues() {
                <%--document.forms[0].elements['personcode'].value="";--%>
                document.forms[0].elements['district_id'].value="0";
                document.forms[0].elements['mandal_id'].value="0";
                document.forms[0].elements['village_id'].value="0";
                document.forms[0].elements['sadaremId'].value="";
                document.forms[0].elements['rationCardNo'].value="";
                document.forms[0].elements['aadharcardNumber'].value="";
                document.forms[0].elements['name'].value="";
                document.forms[0].elements['parentsName'].value="";
                document.forms[0].status.value="";
            }

            function getData() {
                document.forms[0].status.value="getMandal";
                document.forms[0].submit();
            }

            function getHab() {
                document.forms[0].status.value="getVillage";
                document.forms[0].submit();
            }

            function deselect() {
                document.forms[0].elements['sadaremId'].value="";
                document.forms[0].elements['rationCardNo'].value="";
                document.forms[0].elements['aadharcardNumber'].value="";
                document.forms[0].elements['name'].value="";
                document.forms[0].elements['parentsName'].value="";

            }

            function sadaremDeselectId() {
                <%--document.forms[0].elements['personcode'].value="";--%>
                document.forms[0].elements['name'].value="";
                document.forms[0].elements['parentsName'].value="";
                document.forms[0].elements['district_id'].value="0";
                document.forms[0].elements['mandal_id'].value="0";
                document.forms[0].elements['village_id'].value="0";

            }




            function rationCardDeselectId() {
                document.forms[0].elements['sadaremId'].value="";
                document.forms[0].elements['aadharcardNumber'].value="";
                <%--document.forms[0].elements['personcode'].value="";--%>
                document.forms[0].elements['name'].value="";
                document.forms[0].elements['parentsName'].value="";
                document.forms[0].elements['district_id'].value="0";
                document.forms[0].elements['mandal_id'].value="0";
                document.forms[0].elements['village_id'].value="0";

            }

            function deselectaadharcar() {
                document.forms[0].elements['sadaremId'].value="";
                document.forms[0].elements['rationCardNo'].value="";
                <%--document.forms[0].elements['personcode'].value="";--%>
                document.forms[0].elements['name'].value="";
                document.forms[0].elements['parentsName'].value="";
                document.forms[0].elements['district_id'].value="0";
                document.forms[0].elements['mandal_id'].value="0";
                document.forms[0].elements['village_id'].value="0";

            }

            function deselectName() {
                <%--document.forms[0].elements['personcode'].value="";--%>
                document.forms[0].elements['sadaremId'].value="";
                // document.forms[0].elements['district_id'].value="0";
            }

        </script>

    </head>
   
    <body >
    
    <!-- Screen Lock Started Here -->
	<div id="processlayer">
		<font color="blue" size="2">Validating Please Wait...</font><br/>
		<img src="<%=request.getContextPath()%>/images/processing.gif" border="0"/>
	</div>
	<div id="blocklayer">
	</div>
<!-- Screen Lock Ended Here -->
 <%try{ %>
        <%
        int x = 1;
        
        %>
        <html:form action="/searchForPension.do" method="post" >
        			<html:hidden property="status"/>
                    <logic:present name="msg"> 
                    	<center><font color="red"><bean:write name="msg"/></font></center>
                	</logic:present>
            <table width="50%" border="0" align="center" cellpadding="0" cellspacing="0" style="border-left:#234466 solid 1px;border-right:#234466 solid 1px;">
                <tr>
                <th class="hd_gd" style="height: 50px;">R 4.1 : Search by pension number / SADAREM ID / Territory wise / Name/ Relative Name</th>
                <tr>
                <tr>
                    <td>
                    <br/>
                        <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="gd_row">
                            <%--<tr>
                                <td  width="50%" align="right">Pension ID</td>
                               <td class="secondrow" align="center">:</td>
                                <td>
                                    <html:text property="personcode" size="50" maxlength="17" onfocus="deselect()" onkeypress="return isNumberKey()"/><br/>
                                </td>
                            </tr>--%>
                            <tr>
                                <td class="secondrow" width="50%" height="25px;" align="left" valign="middle"><b>SADAREM ID</b></td>
                               <td class="secondrow" align="center">:</td>
                              <td class="firstrow" width="50%">
                                    <html:text property="sadaremId" size="30" maxlength="17" onkeypress="return onlyNumbers();" onfocus="sadaremDeselectId()"/>
                                </td>
                            </tr>
                            <tr>
                            	<td class="firstrow" colspan="3" align="center" valign="middle"><font color="red"><b>(OR)</b></font></td>
                            <tr>
                                <td class="secondrow" width="50%" height="25px;" align="left" valign="middle"><b>Ration card Number</b></td>
                                <td class="secondrow">: </td>
                                <td class="firstrow" width="50%">
                                    <html:text property="rationCardNo" size="30" maxlength="17" onfocus="rationCardDeselectId()"/>
                                </td>
                            </tr>
                            <tr>
                            	<td class="firstrow" colspan="3" align="center" valign="middle"><font color="red"><b>(OR)</b></font></td>
                            <tr>
                            <tr>
                                <td class="secondrow" width="50%" height="25px;" align="left" valign="middle"><b>Aadhar Card Number</b></td>
                               <td class="secondrow" align="center">:</td>
                                <td class="firstrow" width="50%">
                                    <html:text property="aadharcardNumber" size="30" maxlength="12" onkeypress="return onlyNumbers();" onfocus="deselectaadharcar()"  />
                                </td>
                            </tr>
                            <tr>
                            	<td class="firstrow" colspan="3" align="center" valign="middle"><font color="red"><b>(OR)</b></font></td>
                            <tr>
                            <tr>
                                <td class="secondrow" width="50%" height="25px;" align="left" valign="middle"><b>District</b></td>
                               <td class="secondrow" align="center">:</td>
                              <td class="firstrow" width="50%">
                                    <html:select styleId="1" property="district_id" onchange="getData()" style="width:165px;height:22PX;">
                                        <html:option value="0">-- Districts --</html:option>
                                        <html:optionsCollection   property="districtList" label="district_name" value="district_id"  />
                                    </html:select><br/>
                                </td>
                            </tr>
                            <tr>
                                <td class="secondrow" width="50%" height="25px;" align="left" valign="middle"><b>Mandal</b></td>
                               <td class="secondrow" align="center">:</td>
                                <td class="firstrow" valign="middle">
                                    <html:select styleId="2" property="mandal_id" onchange="getHab()" style="width:165px;height:22PX;">
                                        <html:option  value="0">-- Mandals --</html:option>
                                        <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"  />
                                    </html:select><br/>

                                </td>
                            </tr>
                            <tr>
                                <td class="secondrow" width="50%" height="25px;" align="left" valign="middle"><b>Village</b></td>
                               <td class="secondrow" align="center">:</td>
                                <td class="firstrow" width="50%">
                                    <html:select styleId="3" property="village_id" style="width:165px;height:22PX;">
                                        <html:option  value="0">-- Villages --</html:option>
                                        <html:optionsCollection property="villagelist" label="village_name" value="village_id"  />
                                    </html:select><br/>

                                </td>
                            </tr>

                            <tr>
                                <td class="secondrow" width="50%" height="25px;" align="left" valign="middle"><b>Name</b></td>
                                <td class="secondrow" align="center">:</td>
                                <td class="firstrow" width="50%">
                                    <html:text property="name" size="30" onfocus="deselectName()" /><br/>
                                </td>
                            </tr>
                            <tr>
                                <td class="secondrow" width="50%" height="25px;" align="left" valign="middle"><b>Relation</b></td>
                                <td class="secondrow" align="center">:</td>
                                <td class="firstrow" width="50%" valign="middle">
                                    <html:text property="parentsName" size="30" />
                                </td>
                            </tr>
                        </table>
                        <br/>
                    </td>
                </tr>


                <tr>
                    <th class="hd_gd">
                        <input type="button" value="Search" onclick="validateform();">
                        <input type="button" value="Reset" onclick="resetValues();">
                        <input type="button" value=" Close " onclick="window.close()">

                    </th>
                </tr>
            </table>
            <br/>

            <logic:notEmpty name="areaWise">

              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="gd_row">

                    <tr>
                        <th class="hd_gd">S.No.</th>
                        <th class="hd_gd">Pension ID</th>
                        <th class="hd_gd">SADAREM ID</th>
                        <th class="hd_gd">Name</th>
                        <th class="hd_gd">Relation Name</th>
                        <th class="hd_gd">Age</th>
                        <th class="hd_gd">Qualification</th>
                        <th class="hd_gd">Type Disabilty</th>
                        <th class="hd_gd">Disability Percentage</th>
                        <th class="hd_gd">Contact number</th>
                        <th class="hd_gd">Pension Status</th>
                        <th class="hd_gd">Assessment Status</th>
                        <th class="hd_gd">Issue Date</th>
                        <th class="hd_gd">Certificate Type</th>
                        <th class="hd_gd">Certificate Expires on</th>
                        <th class="hd_gd">Proof Details</th>
                        <%--<th  >
                            PensionPhase
                        </th>
                        <th  >
                            Certificate
                        </th>--%>
                    </tr>
                    <logic:iterate name="areaWise" id="row">

					<%
					 if(i%2==0)
					  {
						  strMyStyle="secondrow";
					  }
					  else
					  {
						  strMyStyle="firstrow";
					  }
					%>
                        <tr>
                            <td class="<%=strMyStyle%>" align="center"> <%=i++%> </td>
                            <td class="<%=strMyStyle%>" align="left">${row.pensioncard_no} </td>
                            <td class="<%=strMyStyle%>" align="left">${row.person_code}</td>
                            <td class="<%=strMyStyle%>" align="left">${row.name}</td>
                            <td class="<%=strMyStyle%>" align="left">${row.relation_name}</td>
                            <td class="<%=strMyStyle%>" align="right">${row.age}</td>
                            <td class="<%=strMyStyle%>" align="left">${row.qly}</td>
                            <td class="<%=strMyStyle%>" align="left">${row.disability}</td>
                            <td class="<%=strMyStyle%>" align="right">${row.percentage}</td>
                            <td class="<%=strMyStyle%>" align="left">${row.mobile}</td>
                            <td class="<%=strMyStyle%>" align="left">${row.personStatus}</td>
                            <td class="<%=strMyStyle%>" align="left">${row.assesmentStatus}</td>
                            <td class="<%=strMyStyle%>" style="text-align:center">${row.issuedate}</td>
                            <td class="<%=strMyStyle%>" align="left">${row.certificate_type}</td>
                            <td class="<%=strMyStyle%>" style="text-align:center">${row.certificate_expire_date}</td>
                            <td class="<%=strMyStyle%>" align="left">${row.idproff_details}</td>
                           <%-- <td  >
                                ${row.pensionPhase}
                            </td>

                            <%

                                        String s = "personStatusDet" + x;
                                        String personcode = "person_code" + x;
                                        if (request.getAttribute(s) != null) {
                                            if (request.getAttribute(s).equals("Dead")) {%>
                            <td  >
                                <b> - </b>
                            </td>
                            <%} else if (request.getAttribute(personcode) != null) {
                                                                            if (request.getAttribute(personcode).equals("-")) {%>
                            <td  >
                                <b> - </b>
                            </td>
                            <%  } else {%>

                            <td  >
                                <a href="javascript:void(0);" onclick ="window.open('searchForCertificate.do?searchCertificate=searchCertificate&print=certificate&personcode=${row.person_code}&display=certificatejsp', 'Ratting','resizable=yes, scrollbars=yes,')">View</a>
                            </td>

                            <%}
                                                                        } else {%>

                            <td  >
                                <a href="javascript:void(0);" onclick ="window.open('searchForCertificate.do?searchCertificate=searchCertificate&print=certificate&personcode=${row.person_code}&display=certificatejsp', 'Ratting','resizable=yes, scrollbars=yes,')">View</a>
                            </td>

                            <%}
                                            x++;
                                        }

                            %>--%>
                        </tr>
                    </logic:iterate>
                </table>
            </logic:notEmpty>

        </html:form>
<%}catch(Exception e){e.printStackTrace();} %>
    </body>
</html>