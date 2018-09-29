
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
            if (validate_required(district_id,"Select District")==false)
            {
            district_id.focus();
            return false
            }

           if (validate_required(camp_venue,"Please Enter Venue Name")==false)
            {
            camp_venue.focus();
            return false

            }
            
            if (validate_required(camp_name,"Name of the Medical Authority")==false)
            {
            camp_name.focus();
            return false
            }

             if (validate_required(camp_address,"Address of the Medical Authority")==false)
            {
            camp_address.focus();
            return false
            }
          
            
            }
            }
   function checkcampvenue()
     {
      var str = document.forms[0].camp_venue.value;
       if(str.substring(0,1)==" ")
     {
        alert("first space can not be space")
        document.forms[0].camp_venue.value="";
        document.forms[0].camp_venue.focus();
        return false;
     }
     document.forms[0].camp_venue.value=str.substring(0,1).toUpperCase()+str.substring(1,str.length);
     return true;
     }
      
     function checkcampname()
     {
      var str = document.forms[0].camp_name.value;
       if(str.substring(0,1)==" ")
     {
        alert("first space can not be space")
        document.forms[0].camp_name.value="";
        document.forms[0].camp_name.focus();
        return false;
     }
     document.forms[0].camp_name.value=str.substring(0,1).toUpperCase()+str.substring(1,str.length);
     return true;
     }
     function checkcampaddress()
     {
      var str = document.forms[0].camp_address.value;
       if(str.substring(0,1)==" ")
     {
        alert("first space can not be space")
        document.forms[0].camp_address.value="";
        document.forms[0].camp_address.focus();
        return false;
     }
     document.forms[0].camp_address.value=str.substring(0,1).toUpperCase()+str.substring(1,str.length);
     return true;
     }

        function getData(read)
        {
            document.forms[0].status.value="update";
            document.forms[0].submit();

        }

    function alpha(e) {
        var k;
        document.all ? k = e.keyCode : k = e.which;
        return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k==32);
    }
    
    </script>
        

    </head>

    <BODY>
    

    <html:form action="addCamp.do?addCamp=addCamp" onsubmit="return validate_form(this)" >
        <input type="hidden" name="status" value="finish"/>
        <br><br><br><br>
        
    <table  align="center" cellspacing="1" cellpadding="8" class="inputform" width="60%">
    <tr>
    <th colspan="2" align="center" class="heading">Add Medical Authority Details</th>
     </tr>
     </table>

    <table  align="center" cellspacing="1" cellpadding="8" class="inputform" width="60%">
    <tr><td class="columnHeight20" colspan="2"></td></tr>
    <tr>
        <th class="label">District</th>

            <td>
                <html:select  property="district_id">
                <html:option  value="">--SELECT--</html:option>
                <html:optionsCollection   property="districtlist" label="district_name" value="district_id"  />
                </html:select>
            </td>
            </tr>

             <tr>
            <th  class="label">Place of Venue</th>
            <td>
                <html:text property="camp_venue" value="" onblur="checkcampvenue();" onkeypress="return alpha(event)"  maxlength="75"/>
            </td>
            </tr>


            <tr>
            <th  class="label">Name of the Medical Authority</th>
            <td>
                <html:text property="camp_name"  onblur="checkcampname();" onkeypress="return alpha(event)" value="" maxlength="50"/>
            </td>
            </tr>
            <tr>
            <th class="label">Address of the Medical Authority</th>
            <td>
                <html:text property="camp_address" value="" onblur="checkcampaddress();" maxlength="50"></html:text>
            </td>
            </tr>
            <tr>
       <td class="columnHeight10" colspan="2"></td>
       </tr>

       <tr>
       <td colspan="2" align = "center"><input type="Submit" value="Submit" class="button" ></td>
       </tr>
       <tr><td class="columnHeight20"></td></tr>

        </table>



    </html:form>


    </body>
</html:html>
