<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    
    <body>

   <table width="100%" border="0" ><br><br><br><br>
      <tr>
            <td align="center"><font  size="5" color="red" >Duplicate Record Found Suggested To Refer Again</font></td><br>
            
      </tr>
      <tr><br>
      <td align="center"><font  size="5" color="blue" >SADAREM ID Matched : <%out.write((String)request.getAttribute("partACheckForDuplicatePersonCode"));%></font></td>
       </tr>
   </table>
    
    
    </body>
</html>
