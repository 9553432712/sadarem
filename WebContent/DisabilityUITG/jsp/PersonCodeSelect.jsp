<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%String message=null;%>
<html>
    <script type="text/javascript">
function validate_required(field)
{
with (field)
{
if (value==null||value=="")
  {alert("SADAREM ID must be filled out!");return false;}
var numericExpression = /^[0-9]+$/;
if(!value.match(numericExpression))
   {alert("SADAREM ID must be Integer");return false;}
if(value.length !=17)
   {alert("SADAREM ID must be exactly of 17 numbers");return false;}


}
}

function validate_form(thisform)
{
with (thisform)
{
if (validate_required(personcode)==false)
  {personcode.focus();return false;}
  if (validate_required(personcode)==false)
  {personcode.focus();return false;}
}
}
</script>
    <body>
        <form action="personCodeCheck.do" onsubmit="return validate_form(this)" method="post">
            <table >
<tr align="right">                

              <td align="right">  <%  message=(String)request.getAttribute("msg"); %>
  <% if(message!=null)  { %> <font color="red"><b><%=message %> <% } %></b></td></tr>
<tr>                    
<td width="80%" align="right"><font size="4" color="blue" > <b>Please Enter SADAREM ID : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
                    </td>
                    <td width="20%" align="right"><input type="text"  maxlength="17" size="17" name="personcode"/> 
                    </td>
                </tr>
                <tr>
                </tr><tr>
                </tr><tr>
                </tr><tr>
                </tr><tr>
                </tr><tr>
                </tr><tr>
                </tr><tr>
                </tr>
                <tr ><td align="right">
                    <input type="submit" value="Submit" />
                </td>
                <td align="left">
                    <input type="reset" value="Reset" />
                </td></tr>
            </table>  
        </form>
    </body>
</html>
