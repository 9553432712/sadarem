<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<%@taglib  uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib  uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="javax.servlet.http.HttpServletRequest" %>
<%@page import="org.bf.disability.form.TerritoryForm" %>
<%@page import="java.util.*"%>
<%@page import="org.bf.disability.dto.TerritoryDTO"%>

<% TerritoryForm territoryform=new  TerritoryForm(); %>
<% String fromdate_in_jsp=(String)request.getAttribute("FromDateToPWDTerritory");
    String todate_in_jsp=(String)request.getAttribute("ToDateToPWDTerritory");

%>

<html:html>
    <html:errors/>
    <head> </head>
    <BODY >
        <div style="border:1px solid #000000; background-color: #FFFFFF;width:100%;
           height:100%; overflow-x:scroll; overflow-y:scroll;"> 
        
        
    
        <table   border="0" align="center" cellspacing="1" cellpadding="5"
            height="3%" class=""  width="100%" >
           
            <logic:present name="districtlist" scope="request">
          
    <%
        ArrayList districtname=(ArrayList)request.getAttribute("districtlist");
        ArrayList districtid=(ArrayList)request.getAttribute("districtid");

        for(int i=0;i<districtname.size();i++) {
    %>
    <tr><td>[D]&nbsp;&nbsp;<a  href="getmandalsandreports.do?
getMandalReport=getMandalReport&districtid=<%= (String)districtid.get(i)%>
&district_namefromjsp=<%= (String)districtname.get(i) %>
&from_date_to_action=<%= fromdate_in_jsp %>&to_date_to_action=<%= todate_in_jsp %>
 " onclick="getvalidation()"  > <b><%= (String)districtname.get(i) %> </b></a></td></tr>
          
                <logic:present name="mandallist" scope="request">
     
<%

    String dist=(String)request.getAttribute("comparedistrictname");

    if(dist.equals((String)districtname.get(i))) {


        ArrayList mandalnamelistforreport=(ArrayList)request.getAttribute("mandallist");
        ArrayList mandalidlistforreport=(ArrayList)request.getAttribute("mandalid");


        for(int j=0;j<mandalnamelistforreport.size();j++) {


%>
                <tr><td style="padding-left:45px;padding-top:3px;">[M]&nbsp;&nbsp;
 <a href="getvillagereports.do?getVillageReport=getVillageReport&districtid=<%= (String)districtid.get(i)%>
&districtname=<%= (String)districtname.get(i)%>&mandalid=<%= (String)mandalidlistforreport.get(j)%>
&from_date_to_action=<%= fromdate_in_jsp %>&to_date_to_action=<%= todate_in_jsp %>
 &mandal_namefromjsp=<%= (String)mandalnamelistforreport.get(j)%>"><b>
<%= (String)mandalnamelistforreport.get(j)%></b></a>  </td></tr> 
                
        
        
                <logic:present name="villagelist" scope="request">
    <%
        String mandal=(String)request.getAttribute("comparemandalname");
        if(mandal.equals((String)mandalnamelistforreport.get(j))) {
            ArrayList villagenamefromreport=(ArrayList)request.getAttribute("villagelist");
            ArrayList villageidfromreport=(ArrayList)request.getAttribute("villageid");
            for(int k=0;k<villagenamefromreport.size();k++) {

                String str;

    %>
 <tr><td style="padding-left:95px;padding-top:3px;">[V]&nbsp;&nbsp;
<a href="gethabitationreports.do?getHabitationReport=getHabitationReport&districtid=
<%= (String)districtid.get(i)%>&districtname=<%= (String)districtname.get(i)%>
&mandalid=<%= (String)mandalidlistforreport.get(j)%>&mandal_namefromjsp=
<%= (String)mandalnamelistforreport.get(j)%>&from_date_to_action=
<%= fromdate_in_jsp %>&to_date_to_action=<%= todate_in_jsp %>&villageid=
<%= (String)villageidfromreport.get(k)%>&village_namefromjsp=
<%= (String)villagenamefromreport.get(k)%>"><b>
<%= (String)villagenamefromreport.get(k)%></b></a></td></tr> 
                    
      
  
                    <logic:present name="habitationlist" scope="request">
<%
    String village=(String)request.getAttribute("comparevillagename");


    if(village.equals(((String)villagenamefromreport.get(k)).trim())) {

        ArrayList habitationnamefromreport=(ArrayList)request.getAttribute("habitationlist");
        ArrayList habitationidfromreport=(ArrayList)request.getAttribute("habitationid");
        for(int m=0;m<habitationnamefromreport.size();m++) {

%>
  <tr><td style="padding-left:145px;padding-top:3px;">
[H]&nbsp;&nbsp;&nbsp;&nbsp;<a href="gethabitationreports.do?
getHabitationReport=getHabitationReport&districtid=
<%= (String)districtid.get(i)%>&districtname=
<%= (String)districtname.get(i)%>&mandalid=
<%= (String)mandalidlistforreport.get(j)%>&mandal_namefromjsp=
<%= (String)mandalnamelistforreport.get(j)%>&villageid=
<%= (String)villageidfromreport.get(k)%>&village_namefromjsp=
<%= (String)villagenamefromreport.get(k)%>&from_date_to_action=
<%= fromdate_in_jsp %>&to_date_to_action=<%= todate_in_jsp %>
&habitationid=<%=(String)habitationidfromreport.get(m)%>&habitation_namefromjsp=
<%=(String)habitationnamefromreport.get(m)%> "><b>
<%= (String)habitationnamefromreport.get(m)%></b></a></td></tr> 
                        <%
                            }}
                        %>
      
                    </logic:present>
                    <%
                        }}
                    %>
         
                </logic:present>
                <%
                    }}

                %>   </logic:present>
                <%
                        }
                %>
         
            </logic:present>
           
          
        </table>
  
    </div>
        
 
    </body>
 
    
</html:html>