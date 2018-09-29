
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
 <%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.*,java.text.*"%>
<%@page session="true"%>


<html:html>

    <head>
        <script>
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
            function validate_form(thisform)
            {
            with (thisform)
            {
            if (validate_required(district_name,"District cannot be empty")==false)
            {
            district_name.focus();
            return false
            }
                        
            }
            }
   
          
        </script>
	   
    </head>

    <BODY>

    

    <html:form action="addDistrict.do?addDistrict=addDistrict" onsubmit="return validate_form(this)" >
        
        <center><table  border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="80%" >
        <tr class="tbl_bg2_content_hdr"> 
            <td ><center><font size="4">Add District</font></center></td>
        </tr></table> 
        <table  border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="80%" height="40%">		
                            
            <tr class="tbl_bg2_content_hdr_new"> <font size="2"><td><b> District Name</font></td>
                <td colspan="3"> 
                    <html:text property="district_name" value=""/>
                </td>
            </tr>
            <tr class="tbl_bg2_content_hdr_new">
                <td colspan="4" align="center"><html:submit value="submit" /></td>
            </tr>
			
        </table> </center>
    </html:form>
    
     
    </body>
</html:html>
