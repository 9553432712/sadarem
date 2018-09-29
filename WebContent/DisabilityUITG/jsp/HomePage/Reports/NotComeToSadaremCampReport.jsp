<%-- 
    Document   : NotComeToSadaremCampReport
    Created on : Jan 28, 2011, 3:50:24 PM
    Author     : 509865
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>
<%@page session="true"%>
<%try{ %>
<%
            String districtName = (String) request.getParameter("districtName");
            String phaseName = (String) request.getParameter("phase");
            String district_id = (String) request.getParameter("district_id");
            ArrayList notCometoSadaremList = new ArrayList();
            notCometoSadaremList = (ArrayList) request.getAttribute("notComeToCampList");
            int totalNotCome = 0, totalonlyPartA = 0;
            Iterator iter = notCometoSadaremList.iterator();
            while (iter.hasNext()) {
                FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                totalNotCome = totalNotCome + totalDTO.getNotComrtoCamp();
                totalonlyPartA = totalonlyPartA + totalDTO.getOnlyPartA();
            }
%>
<Style>
   .border_hd_gd
   {
     border : #234466 1px solid;
   }
   .gridStyle
 {
	WIDTH: 100%; BORDER-COLLAPSE: collapse; FONT-FAMILY: verdana
}
</Style>

<html:html>
    <head>

        <title>SADAREM</title>
        <script type="text/javascript" src="./DisabilityUITG/css/jqueryslidemenu.js"></script>
        <script type="text/javascript" src="./DisabilityUITG/css/jquery.min.js"></script>
        <script type="text/javascript" src="./DisabilityUITG/js/Ajax.js"></script>
        <script type="text/javascript" src="./DisabilityUITG/js/date-picker"></script>
        <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="./DisabilityUITG/js/date-picker"></script>
    </head>

    <body >
      

        <html:form action="notComeToCamp.do?getNotComeToCamp=getNotComeToCamp&reportCategory=1" method="post" onsubmit="return validate_form(this)">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
                  <input type="hidden" name="districtName"/>
                <input type="hidden" name="pahseName"/>
					  <tr>
					    <th class="hd_gd" align="center" valign="middle">R2.1 : SADAREM Camp Not Attended PWD's</th>
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
			 <tr>
                  <td >District<font color="red"><b>*</b></font></td>
                  <td >
                        <html:select styleId="1" property="district_id" style="height:25px;">
                         <html:option  value="">--Select--</html:option>
                        <html:optionsCollection   property="districtlist" label="district_name" value="district_id"  />
                        </html:select>
                   </td>
                      <td >Phase<font color="red"><b>*</b></font></td>
                      <td >
                         <html:select styleId="2" property="phase" style="height:25px;">
                         <html:option value="ALL">All</html:option>
                         </html:select>
                     </td>
                          <td colspan="4" align="center"><html:submit property="Submit" value="Submit" styleClass="button"/></td>
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
			
  					</td>
  				</tr>
  				<%
                                String msg = (String) request.getAttribute("msg");

                    %>
                    <% if (msg != null) {%><table align="center"><tr><td><%=msg%></td></tr></table> <% }%>
  				</table> 
        
        <br>
       <logic:present name="notComeToCampList" scope="request">
       <table width="90%">
				                   <tr>
						             <td  align="right" width="89%" >
									    <a href="notCometoSadaremCampExcel.xls?districtName=<%=districtName%>&phase=<%=phaseName%>" target="_blank">
                    Excel <img height="30" width="30" src="DisabilityUITG/images/excel.jpg">
									  </a>
						     	     </td> 
						            </tr>
						       </table> 
           <table width="60%" border="0" cellspacing="1" cellpadding="0" align="center" >
                <tr><th class="hd_gd"  align="center" colspan="4">District: <%=districtName%>, Phase: <%=phaseName%>, Who are Not Come to SADAREM Camp Report</th></tr>
                <tr>
                    <th class="hd_gd" align="center" >S.No</th>
                    <th class="hd_gd"  align="center">Mandal Name</th>
                  <%--  <th  align="center">Not Come to SADAREM Camp</th>--%>
                    <%int j = 0;
                                if (phaseName.equalsIgnoreCase("Reassment") || phaseName.equalsIgnoreCase("Renual")) {
                                    j = 0;
                                } else {
                                    j = 1;
                                }
                                if (j == 1) {%>
                    <th class="hd_gd"  align="center">Only Part-A(Doctor Not Assessed)</th>
                    <%}%>
                </tr>
                <tr>
                <th class="hd_gd" align="center" >1</th>
                <th class="hd_gd" align="center" >2</th>
                <th class="hd_gd" align="center" >3</th>
                </tr>
                <% int i = 0;
                String classStyle="";%>
                <logic:iterate id="modify" name="notComeToCampList" scope="request" indexId="count">
                
                 <%   if(count.intValue()%2==0)
			        			  {
			        			  	classStyle="secondrow";
			        			  }
			        			  else
			        			  {
				        			  	classStyle="firstrow";
			        			  }%>
                   
                    <tr>
                        <td class="<%=classStyle%>"  style="text-align: center"   ><%=++i%></td>
                        <td  class="<%=classStyle%>" align="left"><bean:write name="modify" property="mandalName"/></td>
                        <%if (j == 1) {%>
                        <%--<td   style="text-align: center"><a href="notComrToSadaremCampPersonal.xls?getNotComeToCampPersonalDetails=getNotComeToCampPersonalDetails&dID=<%=district_id%>&mID=<bean:write name="modify" property="mandal_id"/>&phase=<%=phaseName%>&reportType=notcome&dName=<%=districtName%>" target="_blank">
                                <bean:write name="modify" property="notComrtoCamp"/></a></td>--%>
                                <%}%>
                        <td class="<%=classStyle%>"  style="text-align: center; border-right:#234466 1px solid;">
                      
                         <a href="notComrToSadaremCampPersonal.xls?getNotComeToCampPersonalDetails=getNotComeToCampPersonalDetails&dID=<%=district_id%>&mID=<bean:write name="modify" property="mandal_id"/>&phase=<%=phaseName%>&reportType=parta&dName=<%=districtName%>" target="_blank">
                                <bean:write name="modify" property="onlyPartA"/></a></td>

                    </tr>
                    
                </logic:iterate>
                <tr>
                    <td  class="hd_gd" colspan="2"  style="text-align: center">Total</td>
                    <%if (j == 1) {%>
                    <%--<td   style="text-align: center"  style="font-weight: bold">
                            <%=totalNotCome%></td>--%>
                            <%}%>
                    <td  class="hd_gd" style="text-align: center"  >
                            <%=totalonlyPartA%></td>

                </tr>
            </table><br>
            
        </logic:present> 
    </html:form>
</body>

</html:html>
<%}catch(Exception e){System.out.println(e);} %>
