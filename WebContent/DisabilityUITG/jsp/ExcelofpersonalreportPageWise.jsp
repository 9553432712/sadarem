<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 
Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   

<html:html>
    <head>
    
     
    </head>
   
    <body>
  
     
        <table  border="1" align="center" cellspacing="1" cellpadding="5"  width="100%">
            <tr align="center"  width="100%"> 
            <td><b><font color="red">Excel sheet for Personal Details of <%= session.getAttribute("SurgeryType") %></font></b></td></tr>  
            <tr>
            <td>
                 <table  border="1" align="center" cellspacing="1" cellpadding="5"  width="70%" >
            </tr>
 <tr class="tbl_bg2_content_hdr_new" valign="middle">
                    <td>S.No</td>  
                    <td>Name</td>
                    <td>Age</td>
                    <td>Sex</td>
                    <td>Address</td>
                     <td>Type of disability</td>
                    <td>Sub type of Disability</td>
                    <td>Parts Involed</td>
                     <td>Percentage of Disability</td>
                     
                    <td>Caste </td>
                    <td>Occupation</td>    
 </tr><% int i=((Integer)session.getAttribute("counter")).intValue();  %>
                           
               <logic:iterate id="modify" name="arraylist1" scope="session" >
                                        
                                      
                
                    <tr>
                        
                        <td class="cellheader"><%=i++%></td>
                        <td><bean:write name="modify" property="name"/></td>
                        <td><bean:write name="modify" property="age"/></td>
                        <td><bean:write name="modify" property="gender"/></td>
                        <td><bean:write name="modify" property="houseno"/><br>
                        <bean:write name="modify" property="districtname"/><br> 
                        <bean:write name="modify" property="mandalname"/> <br>
                        <bean:write name="modify" property="villagename"/> <br>
                        <bean:write name="modify" property="habitationname"/></td> 
                        <td><bean:write name="modify" property="typeofdisability"/></td>
                        <td><bean:write name="modify" property="subtype"/></td>
                        <td><bean:write name="modify" property="subsubtype"/></td>
                        <td><bean:write name="modify" property="totalpercent"/></td>
                        
                        <td><bean:write name="modify" property="caste"/></td> 
                        <td><bean:write name="modify" property="occupation"/></td> 
                   
                    
                    </tr>
                </logic:iterate>
      
            </table>
            
            
            
            
         

            
            
            
            
            
            
        </table>
  
 
    </body>
    
    <p>&nbsp;</p>
  
</html:html>
            