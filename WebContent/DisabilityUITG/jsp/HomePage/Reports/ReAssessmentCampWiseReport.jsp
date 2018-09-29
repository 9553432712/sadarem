<%--
    Document   : empReport
    Created on : Jun 27, 2011, 3:33:27 PM
    Author     : 490058
--%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script> 
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/Ajax.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/Validations.s"></script> 
        <script language="JavaScript">
        <%
        int i = 1;
 %>
            function selectedNames()
            {
                var slcBx1 = document.getElementById("1");
                var slcBx2 = document.getElementById("2");
                var slcBx3 = document.getElementById("3");

                document.getElementById("districtName").value = slcBx1.options[slcBx1.selectedIndex].text;
                document.getElementById("mandalName").value = slcBx2.options[slcBx2.selectedIndex].text;
                document.getElementById("villageName").value = slcBx3.options[slcBx3.selectedIndex].text;

            }

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

        
            function getDetails() {
                if(document.forms[0].elements['district_id'].value=="0"){

                    alert("Please Select DistrictID!");

                    document.forms[0].elements['district_id'].value=="";
                    document.forms[0].elements['district_id'].focus();
                }else if(document.forms[0].elements['camp_id'].value=="0"){
                    alert("Please Select Camp ID");
                    document.forms[0].elements['camp_id'].value ="";
                    document.forms[0].elements['camp_id'].focus();
                }
                else{
                    document.forms[0].mode.value="ReAssessmentValues";
                    document.forms[0].submit();
                    
                }
            }

            function getCampDetails(){
                document.forms[0].submit();
            }
        </script>

    </head>
    <body onload="OnBodyLoad(1,3);">


        <html:form  action="reAssessmentCampWiseReport.do"  method="post">
            <html:hidden property="mode"/>


            <logic:present name="msgss">
                <font color="red" size="2"><bean:write name="msgss"/></font>
            </logic:present>

           <table  align="center"  border="0" cellpadding="0" width="100%">
                <tr>
                    <th colspan="4" class="hd_gd" align="center" valign="middle">
                        R5.2 &nbsp; Re-Assessment Camp Wise Reports
                    </th>
                </tr>
                <tr>
                    <td>
                    
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
									    <tr>
					                     <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_lft_top.png" width="16" height="16" /></td>
					                     <td width="100%" align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_top_bg.png); background-repeat:repeat-x;"></td>
					                     <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_rgt_top.png" width="16" height="16" /></td>
					                   </tr>
					                   <tr>
					                     <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_lft_bg.png); background-repeat:repeat-y;">&nbsp;</td>
					                     <td align="left" valign="top" >
					    		           <table  cellspacing="0" width="100%" border="0px">
									        <tr height="30">
                                <td class="label" >District<font color="red"><b>*</b></font></td>
                                <td align="left">
                                    <html:select styleId="1" property="district_id" onchange="getCampDetails()" style="height:25px;">
                                        <html:option value="0">--Select--</html:option>
                                        <html:optionsCollection property="districtList" label="districtName" value="district_id"  />
                                    </html:select>
                                </td>
                                <td >Camp<font color="red"><b>*</b></font></td>
                                <td align="left">
                                    <html:select property="camp_id" style="width:230px">
                                        <html:option value="0">--Select--</html:option>
                                        <html:optionsCollection property="campList" label="camp_name" value="camp_id"/>

                                    </html:select>
                                </td>
                           
                                <td style="text-align: center" colspan="8">
                                    <html:button property="but" onclick="getDetails()" value="Submit"/>
                                </td>
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


            <logic:notEmpty name="AssesmentList">
             <table width="80%">
                 
                 <tr>
                 <td align="right" colspan="4">
                    <a href="./reAssessmentCampWiseReport.xls?status=getReportExcel&districtId=<%=request.getParameter("district_id")%>&campId=<%=request.getParameter("camp_id")%>">
                         <img src="DisabilityUITG/images/excel.jpg"
                                             height="35" width="25"/></a> &nbsp; &nbsp; &nbsp;

                    <a href="reAssessmentCampWiseReport.xls?status=getReportPrint&districtId=<%=request.getParameter("district_id")%>&campId=<%=request.getParameter("camp_id")%>">
                         <img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                </td></tr>
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
                
  				<tr >
                  
                    <th class="hd_gd" align="center" valign="middle">
                         SNo
                        </th>

                         <th class="hd_gd" align="center" valign="middle">
                            PersonID
                        </th>
                         <th class="hd_gd" align="center" valign="middle">
                            SADAREMID
                        </th>
                          <th class="hd_gd" align="center" valign="middle">
                            Name
                        </th>
                         <th class="hd_gd" align="center" valign="middle">
                            Age
                        </th>
                         <th class="hd_gd" align="center" valign="middle">
                            Disability Type
                        </th>
                        <th class="hd_gd" align="center" valign="middle">
                            Old Percentage
                        </th>
                         <th class="hd_gd" align="center" valign="middle">
                            New Percentage
                        </th>
                         <th class="hd_gd" align="center" valign="middle">
                            ReAssessmentText
                        </th>
                         <th class="hd_gd" align="center" valign="middle">
                            Doctor's Name
                        </th>
                        <th class="hd_gd" align="center" valign="middle">
                            Doctor's RegNumber
                        </th>
                         <th class="hd_gd" align="center" valign="middle">
                            Doctor's Designation
                        </th>

                    </tr>
                    <tr >
                  
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

                    </tr>
					<%String classStyle=""; %>
                    <logic:iterate name="AssesmentList" id="row" indexId="count">

                           <%if(count.intValue()%2 == 0){ 
                              classStyle="firstrow";
                           }
                           else
                           {
                              classStyle="secondrow";
                           }
                           
                           
                           %>

                        <tr>
                            <td  align="center" class=<%=classStyle%>>
                                <%=i++%>
                            </td>

                            <td  align="center" class=<%=classStyle%>>
                                ${row.pensionCard}
                            </td>
                            <td   align="center" class=<%=classStyle%>>
                                ${row.personCode}
                            </td>
                            <td  align="left" class=<%=classStyle%>>
                                ${row.Name}
                            </td>
                            <td  align="center" class=<%=classStyle%>>
                                ${row.age}
                            </td>
                            <td  align="left" class=<%=classStyle%>>
                                ${row.disability}
                            </td>

                            <td  align="center" class=<%=classStyle%>>
                                ${row.totalDisabilityInActive}
                            </td>
                            <td  align="center" class=<%=classStyle%>>
                                ${row.totaldisabilityActive}
                            </td>
                            <td  align="center" class=<%=classStyle%>>
                                ${row.reAssessmentText}
                            </td>
                            <td  align="left" class=<%=classStyle%>>
                                ${row.firstDoctorName}
                            </td>
                            <td  align="center" class=<%=classStyle%>>
                                ${row.firstDoctorRegNumber}
                            </td>
                            <td  align="left" class=<%=classStyle%>>
                                ${row.firstDoctorDesignation}
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
                   

                <%session.setAttribute("AssesmentListData", (ArrayList) request.getAttribute("AssesmentList"));%>


               

            </logic:notEmpty>
            <br>

        </html:form>
    </body>
</html:html>