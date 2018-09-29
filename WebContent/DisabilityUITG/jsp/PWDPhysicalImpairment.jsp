<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.*,java.text.*"%>
<%@page session="true"%>

<html:html>
    
<body>
<html:form action="mentalillness.do" method="post">
      <table  border="0" align="center" cellspacing="1" cellpadding="4" class="tbl_bg2" width="100%">
                 <tr class="tbl_bg2_content_hdr">
                     <td><center><font size="3" face="Arial">3. Report on Nos of PWD requiring different types of prosthesis/orthosis/mobility aids/walking aids (Physical impairement)</font></center></td>
                  </tr> 
             </table>
  <table  border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="100%">
                <tr class="tbl_bg2_content_hdr_new" valign="middle">
                    <td>S.No</td>  
                    <td>Name</td>
                    <td>Age</td>
                    <td>Sex</td>
                    <td>Address</td>
                    <td>Sub type of physical impairement</td>
                    <td>Cause</td>
                    <td>Part Involved</td>
                      
                    <td>Percentage of Disability</td>
                    <td>Prescribed Orthosis </td>
                    <td>Prescribed Prosthesis</td>                    
                    <td>Prescribed wheelchair</td>                
                    <td>Prescribed  Tri cycle</td>                  
                    <td>Prescribed walking sticks</td>                   
                    <td>Prescribed  crutches</td>             
                    <td>Prescribed walking frame</td>
                    <td>Any ADL equipment</td>
                  
                    
                </tr><% int i=1;  %>
                 <% 
                        String Dirst =(String)session.getAttribute("district_name");
                        String mandal =(String)session.getAttribute("mandal_name");
                        String vilage =(String)session.getAttribute("village_name");
                        String habtation =(String)session.getAttribute("habitation_name");
                        String panchayat =(String)session.getAttribute("panchayat_name");

                    %>
                    <logic:iterate id="details" name="Impairment" scope="request"> 
                     <tr class="tbl_bg2_content">
                     
                   <td class="cellheader"><%=i++%></td>
                   <td class="cellheader"><bean:write name="details" property="name"/>        </td>
                   <td class="cellheader"><bean:write name="details" property="age"/>   </td>
                   <td class="cellheader"><bean:write name="details" property="gender"/></td> 
                    <td class="cellheader"><bean:write name="details" property="houseno"/><br><%=vilage%><br> <%=mandal%>  <br><%=Dirst%></td> 
 
                   <td class="cellheader"><bean:write name="details" property="subtype"/>     </td> 
                   <td class="cellheader"><bean:write name="details" property="cause"/>    </td> 
                   <td class="cellheader"><bean:write name="details" property="partinvolved"/>            </td>
                    
                   <td class="cellheader"><bean:write name="details" property="totalpersent"/></td>
                  
                     <td class="cellheader"><bean:write name="details" property="orthosis"/>        </td>
                   <td class="cellheader"><bean:write name="details" property="prosthesis"/>   </td>
                   <td class="cellheader"><bean:write name="details" property="selectwheelchair"/>    </td>  
                   <td class="cellheader"><bean:write name="details" property="selecttricycle"/>     </td> 
                   <td class="cellheader"><bean:write name="details" property="selectwalkingstick"/>    </td> 
                   <td class="cellheader"><bean:write name="details" property="selectcrutches"/>            </td> 
                   <td class="cellheader"><bean:write name="details" property="selectwalkingframe"/></td>

                    <td class="cellheader"><bean:write name="details" property="adlequipment"/></td>
               </tr>
               
        </logic:iterate>
    </table>
</html:form>
</html:html>