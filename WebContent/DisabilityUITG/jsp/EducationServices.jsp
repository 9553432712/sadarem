<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<%@taglib  uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib  uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<% String fromdate=(String)request.getAttribute("fromDate");
   String todate=(String)request.getAttribute("toDate");
    %>
<html>
<head>
<style type="text/css">
    colors{color:black;font-weight:bold};
</style>
</head>
<html:errors/>
<BODY>
    <br><br>
    <table  border="1" align="center"  valign="bottom" class="text1" cellspacing="1" cellpadding="5" width="90%" style="border-collapse:collapse" bordercolor="#808000">
        
        
            <tr ><td align="center" ><a href="getDistrictidForEducation.do?getDistrictReport=getDistrictReport&columnname=EarlyEducation&columnnvalue=Early Education Services&tablename=tblAllDisabilityPerson_Common_Needs&FromDate=<%= fromdate %>&ToDate=<%= todate%>" style="color:black;font-weight:bold">Children Below 5 Years Requiring Early Education Services</a>
            </td></tr> 
            <tr ><td align="center" ><a href="getDistrictidForEducation.do?getDistrictReport=getDistrictReport&columnname=EducationServices&columnnvalue=Home Based Education&tablename=tblAllDisabilityPerson_Common_Needs&FromDate=<%= fromdate %>&ToDate=<%= todate%>" style="color:black;font-weight:bold">Home Based Education</a>
            </td></tr>
 
            <tr ><td align="center"><a href="getDistrictidForEducation.do?getDistrictReport=getDistrictReport&columnname=EducationServices&columnnvalue=Special School&tablename=tblAllDisabilityPerson_Common_Needs&FromDate=<%= fromdate %>&ToDate=<%= todate%>" style="color:black;font-weight:bold">Special School</a>
            </td></tr>
            <tr ><td align="center"><a href="getDistrictidForEducation.do?getDistrictReport=getDistrictReport&columnname=EducationServices&columnnvalue=Inclusive Education&tablename=tblAllDisabilityPerson_Common_Needs&FromDate=<%= fromdate %>&ToDate=<%= todate%>" style="color:black;font-weight:bold">Inclusive Education</a>
           
        
    </table>
   
</BODY>
</HTML>
