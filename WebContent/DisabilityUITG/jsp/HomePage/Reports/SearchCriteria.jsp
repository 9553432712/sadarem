<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.*,java.text.*"%>
<%@page session="true"%>
<%int i = 1;%>

<%--
    Document   : SearchCriteria
    Created on : May 26, 2011, 5:56:44 PM
    Author     : 509862
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
 <head>
	<meta http-equiv="Content-Type"    content="text/html;charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10"/>
    <meta http-equiv="X-UA-Compatible" content="chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>      
     <title>SADAREM</title>
 <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>



        <script>
            function getDetails() {
                if(document.forms[0].elements['from'].value=="" && document.forms[0].elements['to'].value=="" &&
                    document.forms[0].elements['gender'].value=="select" && document.forms[0].elements['type_disability'].value=="" &&
                    document.forms[0].elements['qualification'].value=="" &&
                    document.forms[0].elements['district_id'].value=="0" && document.forms[0].elements['2'].value=="" &&
                    document.forms[0].elements['3'].value=="" && document.forms[0].elements['4'].value=="") {
                    alert("Please Select Atlease One Option");
                    return false;
                }
                document.forms[0].mode.value="getDetails";
                document.forms[0].submit();
            }

            function getData() {
                document.forms[0].mode.value="";
                document.forms[0].status.value="update";
                document.forms[0].submit();
            }

            function disableCheck() {
                document.getElementById('chkMandal').checked=false;
                return false;
            }

            function disableVillage() {
                document.getElementById('chkVillage').checked=false;
                return false;
            }

        </script>
  <Style>
   .hd_gd
   {
     border : #234466 1px solid;
   }
   .gridStyle
   {
	WIDTH: 100%; BORDER-COLLAPSE: collapse; FONT-FAMILY: verdana
   }
</Style>
    </head>

    <html:form action="/searchCriteria.do" >
        <body onload=" OnBodyLoad(2,3);">
           <!-- Screen Lock Started Here -->
	<div id="processlayer" >
		<font color="blue" size="2">Processing Please Wait...</font><br/>
		<img src="<%=request.getContextPath()%>/images/processing.gif" border="0"/>
	</div>
	<div id="blocklayer">
	</div>
<!-- Screen Lock Ended Here -->

                    <html:hidden property="mode"/>
                    <html:hidden property="status"/>
                <input type="hidden" name="district_name" id="districtname"/>
                <input type="hidden" name="mandal_name" id="mandalname"/>
                <input type="hidden" name="village_name" id="villagename"/>
                <input type="hidden" name="panchayat_name" id="panchayatname"/>
                <input type="hidden" name="habitation_name" id="habitationname"/>
                <br/>
                <logic:present name="noData">
                    <center ><font color="red">${noData}</font></center>
                </logic:present>

                    <table  align="center"  border="0" cellpadding="0" width="100%">
                <tr>
                    <th colspan="4" class="hd_gd" align="center" valign="middle">
                            R4.1 &nbsp; Search by Age and Gender/Type of disability/ Qualification/ Territory
                        </th>
                    </tr>
                
                <tr>
                    <td >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
									    <tr>
					                     <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_lft_top.png" width="16" height="16" /></td>
					                     <td width="100%" align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_top_bg.png); background-repeat:repeat-x;"></td>
					                     <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_rgt_top.png" width="16" height="16" /></td>
					                   </tr>
					                   <tr>
					                     <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_lft_bg.png); background-repeat:repeat-y;">&nbsp;</td>
					                     <td align="left" valign="top" >
					    		           <table  cellspacing="0"   width="100%" border="0px">
									        <tr height="30">
                                <td colspan="2">
                                   <b> Age :</b>
                                
                                    From :  <html:text property="from"/>
                               
                                    To :  <html:text property="to"/>
                                </td>
                                <td > <b> Gender :</b>
                                    <html:select property="gender">
                                        <html:option value="select">-- select --</html:option>
                                        <html:option value="0">All</html:option>
                                        <html:option value="1">Male</html:option>
                                        <html:option value="2">Female</html:option>
                                    </html:select>
                                </td>
                            </tr>

                            <tr height="40px">
                                <td colspan="2"><b>Type of Disability : </b> 
                                    <html:select property="type_disability"   styleId="type">
                                        <html:option value="All">ALL</html:option>
                                        <html:option value="1">Locomotor/OH</html:option>
                                        <html:option value="2">Visual Impairment</html:option>
                                        <html:option value="3">Hearing Impairment</html:option>
                                        <html:option value="4">Mental Retardation</html:option>
                                        <html:option value="5">Mental Illness</html:option>
                                        <html:option value="6">Multiple Disabilities</html:option>


                                    </html:select>
                                </td>
                                
                                <td><b>Qualification :</b> 
                                    <html:select property="qualification"  styleId="quali">
                                        <html:option value="All">ALL</html:option>
                                        <html:option value="2">Below 10th</html:option>
                                        <html:option value="3">10th Pass</html:option>
                                        <html:option value="4">Intermediate</html:option>
                                        <html:option value="5">Diploma</html:option>
                                        <html:option value="6">Graduate</html:option>
                                        <html:option value="7">Post Graduate</html:option>
                                    </html:select>
                                </td>
                            </tr>

                            <tr height="40px"> 
                                <td  valign="top" style="padding-top:10;"><b>District :</b>
                                    <html:select property="district_id" onchange="getData()">
                                        <html:option  value="0">--ALL--</html:option>
                                        <html:optionsCollection property="districtlist" label="district_name" value="district_id"  />
                                    </html:select>
                                </td>

                                <td  ><b>Mandal :</b>

                                    <html:select styleId="2" property="mandal_id"    onchange="getData()" >
                                        <html:option  value="0">--ALL--</html:option>
                                        <html:optionsCollection property="mandallist" label="mandal_name" value="mandal_id"  />
                                    </html:select> 
                                </td>

                                <td  ><b>Village :</b>
                                    <html:select styleId="3" property="village_id"  onchange="getData()"  >
                                        <html:option  value="0">--ALL--</html:option>
                                        <html:optionsCollection property="villagelist" label="village_name" value="village_id"  />
                                    </html:select>
                                    
                                </td>
                                <td ><b>Habitation :</b>
                                    <html:select styleId="4" property="habitation_id" >
                                        <html:option  value="0">--ALL--</html:option>
                                        <html:optionsCollection   property="habitationlist" label="habitation_name" value="habitation_id"  />
                                    </html:select>
                                </td>
                            </tr>
                            
                            <tr height="40px">
                            
                                <th colspan="4" style="padding-top:20;">
                                    <html:button property="but" value="Search" onclick="getDetails()" style="background:#234466;color:white;"/>
                                </th>
                            </tr>     
									    </table> 
									  </td>
					                <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_rgt_bg.png); background-repeat:repeat-y">&nbsp;</td>
					             </tr>
								<tr>
						        <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_lft_bom.png" width="16" height="19" /></td>
						        <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_bom_bg.png); background-repeat:repeat-x;">&nbsp;</td>
						        <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_rgt_bom.png" width="16" height="19" /></td>
						      </tr>
						    </table>
                </td></tr>
              </table><br>
            <logic:present name="ageData" scope="request">

                <logic:notEmpty name="ageData" scope="request">
                
                <% session.setAttribute("ageData", (ArrayList) request.getAttribute("ageData"));%>
                <table align="center" width="60%" cellpadding="0" cellspacing="0" border="0">
                    <tr>
                        <td   align="right" valign="middle">

                            <a href="criteriaExcelView.xls" target="_blank">
							  <img src="DisabilityUITG/images/excel.jpg" height="35" width="25" border="0" title="Excel"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="criteriaPrintView.xls" target="_blank" >
							 <img src="DisabilityUITG/images/print.gif" height="25" width="25" border="0" title="Print"/></a>                  </td>
                    </tr>
                </table>
          
                 
                 <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tbody>
                 <tr>
                  <td id="tdGridContent" height="450" valign="top" width="100%" align="middle">
		        <table width="90%" align="center" >
				   <tbody>
				   <tr>
				   <td style="display:none" id="td_grid"  class="scrollme" valign="top" width="100%" colspan="3"  align="middle">
				   <div>
			       <table border="1" align="center" cellspacing="0" rules="all" class="gridStyle"    id="ctl00_dtg_Grid" style="BORDER-COLLAPSE: collapse; border-left: #234466 solid 1px !important;"  width="100%">
				   <tbody>
                
  			    	<tr height="40px">
                  		<th class="hd_gd" align="center" valign="middle">
                            S.No.
                        </th>
                         <th class="hd_gd" align="center" valign="middle">
                            Pension ID
                        </th>
                         <th class="hd_gd" align="center" valign="middle">
                            SADAREM ID
                        </th>
                         <th class="hd_gd" align="center" valign="middle">
                            Name
                        </th>
                        <th class="hd_gd" align="center" valign="middle">
                            Relation
                        </th>

                         <th class="hd_gd" align="center" valign="middle">
                            Age
                        </th>

                        <th class="hd_gd" align="center" valign="middle">
                            Qualification
                        </th>

                        <th class="hd_gd" align="center" valign="middle">
                            Type Disabilty
                        </th>

                         <th class="hd_gd" align="center" valign="middle">
                            Disability Percentage
                        </th>

                        <th class="hd_gd" align="center" valign="middle">
                            Contact number
                        </th>
                        
                        <th class="hd_gd" align="center" valign="middle">
                           District
                        </th>
                        
                        <th class="hd_gd" align="center" valign="middle">
                            Mandal
                        </th>
                        
                        <th class="hd_gd" align="center" valign="middle">
                           Village
                        </th>
                    </tr>
                    <tr height="60px">
                  		<th class="hd_gd" align="center" valign="middle">
                            1
                        </th>
                         <th class="hd_gd" align="center" valign="middle">
                            2
                        </th>
                         <th class="hd_gd" align="center" valign="middle">
                            3
                        </th>
                         <th class="hd_gd" align="center" valign="middle">
                            4
                        </th>
                        <th class="hd_gd" align="center" valign="middle">
                            5
                        </th>

                         <th class="hd_gd" align="center" valign="middle">
                            6
                        </th>

                        <th class="hd_gd" align="center" valign="middle">
                            7
                        </th>

                        <th class="hd_gd" align="center" valign="middle">
                           8
                        </th>

                         <th class="hd_gd" align="center" valign="middle">
                           9
                        </th>

                        <th class="hd_gd" align="center" valign="middle">
                            10
                        </th>
                        
                         <th class="hd_gd" align="center" valign="middle">
                            11
                        </th>
                        
                         <th class="hd_gd" align="center" valign="middle">
                            12
                        </th>
                        
                         <th class="hd_gd" align="center" valign="middle">
                            13
                        </th>
                    </tr>
                    
                    <%String classStyle=""; %>
                    <logic:iterate name="ageData" id="row" indexId="count">
                    
                                             <% if(count.intValue()%2==0)
              			        			     {
                			        			  	classStyle="secondrow";
                			        			  }
                			        			  else
                			        			  {
                				        			  	classStyle="firstrow";
                			        			  } %>
                        <tr>
                            <td class="<%=classStyle%>" style="text-align: center">
                                <%=i++%> .
                            </td>
                            <td class="<%=classStyle%>" style="text-align: center">
                                ${row.PensionCard_No}
                            </td>
                            <td class="<%=classStyle%>" style="text-align: center">
                                ${row.Person_Code}
                            </td>
                            <td class="<%=classStyle%>" style="text-align: left">
                                ${row.name}
                            </td>
                            <td class="<%=classStyle%>" style="text-align: left">
                                ${row.relation_name}
                            </td>
                            <td class="<%=classStyle%>" style="text-align: center">
                                ${row.age}
                            </td>
                            <td class="<%=classStyle%>" style="text-align: left">
                                ${row.qly}
                            </td>
                            <td class="<%=classStyle%>" style="text-align: left">
                                ${row.disability}
                            </td>
                                <td class="<%=classStyle%>" style="text-align: center">
                                ${row.percentage}
                            </td>
                                <td class="<%=classStyle%>" style="text-align: center">
                                ${row.mobile}
                            </td>
                              <td class="<%=classStyle%>" style="text-align: left">
                                ${row.district_name}
                            </td>
                              <td class="<%=classStyle%>" style="text-align: left">
                                ${row.mandal_name}
                            </td>
                              <td class="<%=classStyle%>" style="text-align: left">
                                ${row.village_name}
                            </td>

                        </tr>
                    </logic:iterate>
                    
                </tbody>
	</table>
	</div>
	</td>
	</tr>
	</tbody>
	</table>
	</td></tr></tbody></table>
            </logic:notEmpty>
           
            </logic:present>
        </body>
    </html:form>


</html>
