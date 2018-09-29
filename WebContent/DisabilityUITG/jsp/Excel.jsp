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
            <td><b><font color="red">District Wise Report For <%= session.getAttribute("SurgeryType") %></font></b></td></tr>  
            <tr>
            <td>
            
            
           
            
            
            
            
            <table  border="1" align="center" cellspacing="1" cellpadding="5"  width="70%" >
               
               
    
                        
             
               <logic:iterate id="modify" name="arraylist" >
                   
                   
                   
                   
                     
                    
                     
                     
                     
                
                    <tr>
                       
                       
                        <td> <bean:write name="modify" property="district"/></td>
                         <td><bean:write name="modify" property="districtcount"/></td>
                    </tr>
                </logic:iterate>
      
            </table>
            
            
            
            
         

            
            
            
            
            
            
        </table>
  
 
    </body>
    
    <p>&nbsp;</p>
  
</html:html>