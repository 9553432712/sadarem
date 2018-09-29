<%--
    Document   : RationCardMembersSearch
    Created on : 28-04-2010, 6:18
    Author     : SADAREM
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,java.text.*"%>
<%@page session="true"%>

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
         <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>   
        <script >
            function submitValues(){
                var val = document.forms[0].elements['rationCardNo'].value;
                if(document.forms[0].elements["rationCardNo"].value==""){
                    alert("Please Enter RationCard No");
                    document.forms[0].elements["rationCardNo"].focus();
                    document.forms[0].elements["rationCardNo"].value="";
                    return false;
                }else if(document.forms[0].elements["rationCardNo"].value.length < 15) {
                    alert("Please Enter Valid RationCard No");
                    document.forms[0].elements["rationCardNo"].focus();
                    document.forms[0].elements["rationCardNo"].value="";
                    return false;
                }else if(val.substring(0,3)!="WAP" && val.substring(0,3)!="PAP" && val.substring(0,3)!="AAY" &&
                    val.substring(0,3)!="AAP" && val.substring(0,3)!="YAP" && val.substring(0,3)!="wap" &&
                    val.substring(0,3)!="pap" && val.substring(0,3)!="aay" &&  val.substring(0,3)!="aap" &&
                    val.substring(0,3)!="yap" && val.substring(0,3)!="RAP" && val.substring(0,3)!="rap"
                    && val.substring(0,3)!="TAP" && val.substring(0,3)!="tap"
                    && val.substring(0,3)!="WAD" && val.substring(0,3)!="wad") {
                    alert("Please Enter Valid RationCard Number");
                    document.forms[0].elements["rationCardNo"].focus();
                    document.forms[0].elements["rationCardNo"].value="";
                    return false;
                }else {
                    document.forms[0].mode.value="getDetails";
                    document.forms[0].submit();
                }

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

    <body > 

    
    <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>

    <html:form action="rationcardMembersDetails" focus="rationCardNo">
        <html:hidden property="mode"/>

        <table  align="center"  border="0" cellpadding="0" width="100%">
                <tr>
                    <th colspan="4" class="hd_gd" align="center" valign="middle">
                    RationCard Members Search
                </th>
            </tr>
            <tr>
                <td>
                   <table width="60%" border="0" align="center" cellpadding="0" cellspacing="0">
									    <tr>
					                     <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_lft_top.png" width="16" height="16" /></td>
					                     <td width="100%" align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_top_bg.png); background-repeat:repeat-x;"></td>
					                     <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_rgt_top.png" width="16" height="16" /></td>
					                   </tr>
					                   <tr>
					                     <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_lft_bg.png); background-repeat:repeat-y;">&nbsp;</td>
					                     <td align="left" valign="top" >
					    		           <table  cellspacing="0" width="90%" border="0px">
									        <tr height="30">
                            <td style="padding-left:50px;"> RationCard Number<font color="red"><b>*</b></font> &nbsp;&nbsp;  <html:text property="rationCardNo" maxlength="15"/>
                                &nbsp;  
                            </td>
                            <td colspan="2" align="left">

                                <html:button  value="Get Members" property="sub" onclick="submitValues();"/>
                            </td>
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


        <logic:present name="msg">
            <table align="center">
                <p id="errmsg"><bean:write name="msg"/></p>
            </table>

        </logic:present>
        <logic:notEmpty name="RationCardMember">
           <table border="1" align="center" cellspacing="0" rules="all" class="gridStyle"    id="ctl00_dtg_Grid" style="BORDER-COLLAPSE: collapse; border-left: #234466 solid 1px !important;"  width="100%">
				 <tr>
                    <th class="hd_gd" align="center" valign="middle">
                        Sl No
                    </th>
                    <th class="hd_gd" align="center" valign="middle">
                        Member Name
                    </th>

                    <th class="hd_gd" align="center" valign="middle">
                        Age
                    </th>
                </tr>
                <%String classStyle=""; %> 
                <logic:iterate id="row" name="RationCardMember" indexId="count">

                           <%if(count.intValue()%2 == 0){ 
                              classStyle="firstrow";
                           }
                           else
                           {
                              classStyle="secondrow";
                           }
                           
                           
                           %>
                    <tr>
                        <td class=<%=classStyle%> style="text-align: center">${row.slNo}</td>
                        <td class=<%=classStyle%> style="text-align: center">${row.memberName}</td>
                        <td class=<%=classStyle%> style="text-align: center">${row.age}</td>
                    </tr>
                </logic:iterate>
            </table>
        </logic:notEmpty>


    </html:form>
</body>
</html:html>
