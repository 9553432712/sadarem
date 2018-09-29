
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.*,java.text.*"%>
<%@page session="true"%>


<html:html>

    <head><br><br>
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
    // validation for Spaces canot be taken
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

    </script>


    </head>

    <BODY>

        

        <html:form action="updatecamp.do?updateCamp=updateCamp"  onsubmit="return validate_form(this)">

             <input type="hidden" name="status" value="finish"/>

           <table  align="center" cellspacing="1" cellpadding="8" class="inputform" width="60%">
                <tr>
                    <th class="heading" align="center" >Update Medical Authority</th>
                </tr>
            </table>
            <table  align="center" cellspacing="1" cellpadding="8" class="inputform" border="1" width="60%">



                <%   String id = (String) request.getAttribute("id");
                     String venue = (String) request.getAttribute("venue");
                     String name = (String) request.getAttribute("name");
                     String address = (String) request.getAttribute("address");
                     
                %>

                <html:hidden property="camp_id" value="<%=id%>"/>
                 <tr>
                 <th class="labelBlue"><b>Venue</b> </th>
                <td><html:text  property="camp_venue" value="<%=venue%>" maxlength="75" onblur="checkcampvenue();"  onkeypress="return alpha(event)" ></html:text></td>
            </tr>
                <tr>
                <th class="labelBlue"><b>Name of the Medical Authority</b> </th>
                <td><html:text  property="camp_name" value="<%=name%>" maxlength="50" onblur="checkcampname();"  onkeypress="return alpha(event)"></html:text></td>
            </tr>

                <tr>
                <th class="labelBlue"><b>Address of the Medical Authority</b> </th>
                <td><html:text  property="camp_address" value="<%=address%>" maxlength="50" onblur="checkcampaddress()"></html:text></td>
            </tr>
               <tr>
                   <td colspan="2" align="center"><input type="Submit" value="Update"  class="button"></td>
  </tr>



            </table>



        </html:form>


    </body>
</html:html>
