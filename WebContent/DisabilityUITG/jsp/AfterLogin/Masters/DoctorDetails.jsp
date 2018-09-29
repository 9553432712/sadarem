<%-- 
    Document   : DoctorDetails
    Created on : Jul 4, 2011, 11:29:02 AM
    Author     : 509862
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
 <%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<%--
    Document   : RailwayCertificatewithpersoncode
    Created on : Jun 30, 2011, 11:11:33 AM
    Author     : 509862
--%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<% String pensionNumberFlow = request.getParameter("pensionNumberFlow");
   String status = (String) request.getAttribute("massage"); %>
 <script type="text/javascript">
     function validate_required(field)
     {
     with (field)
     {
     if (value==null||value=="")
     {
         //alert("Please Enter PersonCode");return false;}
     var numericExpression = /^[0-9]+$/;
     if(!value.match(numericExpression))
     {//alert("PersonCode must be Integer");return false;}
     if(value.length !=17)
     {//alert("PersonCode must be exactly of 17 numbers");return false;}


     }
     }}}}

     function validate_form1(value)
     {

     //document.forms.
     var i=document.getElementById("railwaycertificate")[0];
     var j=document.getElementById("railwaycertificate")[1];
    alert(value);
   // alert(j);
     if(value==0 || value==1){


return true;
}else{
   // alert(i);
  //  alert(j);
  alert("Please Select(Y/N) for  Railway Eligibility");
    return false;

}
     with (thisform)
     {
     if (validate_required(personcode)==false)
     {//personcode.focus();return false;}
     if (validate_required(personcode)==false)
     {//personcode.focus();return false;}
     }
    // if ( ( partAForm.railwaycertificate[0].checked == false ) && ( partAForm.railwaycertificate[1].checked == false ) )
     //{ alert ( "Please choose your Eligibility for railway Certificate " ); return false; }
//onsubmit="return validate_form()

     }
     }}
 function validate_form(partAForm)
     {
         var radio_choice = false;
    // var counter=0;

        var doc=document.getElementById("doctorname").value;
        var reg=document.getElementById("regno").value;
          var des=document.getElementById("designation").value;

//alert(doc);
        if((doc=="" || doc==null) || (reg=="" || reg==null)  || (des=="" || des==null)) {

        alert("enter DoctorName, Designation and RegNo");
        return false;

}
if(!partAForm.railwaycertificate.checked){
for (counter = 0; counter < partAForm.railwaycertificate.length; counter++)
{
if (partAForm.railwaycertificate[counter].checked)
radio_choice = true;
}

if (!radio_choice)
{
alert("Please select Railway Certificate Eligibility(Y/N)")
return false;
}
else{

return true;}
}





}

     
     
 </script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SADAREM</title>
</head>
<body onload="document.partAForm.personcode.focus()">
<html:form action="RailwayWithPersoncode.do" method="post" styleId="partAForm" onsubmit="return validate_form(this)">
<input type="hidden" name="flag" value="true"/>
<input type="hidden" name="status" value="finish" />
<input type="hidden" name="pensionNumberFlow" value=<%=pensionNumberFlow%> />

    <%  String percentage=(String)request.getAttribute("percentage");
        float j=Float.parseFloat(percentage);
        int s=(int)j;
    String disablity_type=(String)request.getAttribute("type");
    String doctorname=(String)request.getAttribute("doctorname");
    String designation=(String)request.getAttribute("designation");
    
    String regno=(String)request.getAttribute("regno");
    String personcode=(String)request.getAttribute("personcode");
    String cer=(String)request.getAttribute("cer");
   
    String pos=(String)request.getAttribute("position");
    boolean exis=false;

   // String exists=(String)request.getAttribute("exits");
   
 if(doctorname==null)
     {
     doctorname="";
     regno="";
     designation="";
     }

if(doctorname.contains("Dr.")) {
    exis=true;
    }
%>
<input type="hidden" name="personcode" value=<%=personcode%> />
    <input type="hidden"  maxlength="17" size="17" name="TypeofDisability"  value="<%=disablity_type%>" readonly="true"/>
    <input type="hidden"  maxlength="17" size="17" name="Disability Percentage" value="<%=s %>" readonly="true"/>
<table align="center" cellspacing="1" cellpadding="8" class="inputform" width="60%">
    <tr><th class="heading" colspan="2" align="center">Enter Details For Raiway Concession</th></tr>
<tr>
<td class="label" align="left">Type of Disability :</td>
<td class="label" >
    <%=disablity_type%>
</td>
</tr>
<tr>
<td class="label" align="left">Disability Percentage :</td>
<td class="label">
    <%=s %> %
</td>

</tr>

<%  String exists=(String)request.getAttribute("exists");

int i=1;
%>
<%
session.removeAttribute("Mdspecialistprefix");
session.removeAttribute("des");

boolean a=false;
if (exists!=null && exists.equalsIgnoreCase("mr")) {

    //if(designation.equalsIgnoreCase("psychologist")){
    String aa=(String)request.getSession().getAttribute("dr_exists");
    if(aa!=null && aa.equalsIgnoreCase("yes")){
        request.getSession().removeAttribute("dr_exists");
        }
       /* if(designation!=null){
        String de1=designation.toLowerCase();
            if(de1.equalsIgnoreCase("psychologist")||de1.contains("psychologist")){
               
                a=true;
                request.getSession().setAttribute("dr_exists", "yes");
            }
}*/

    %>
  
  <tr><td  class="label"> Whether the person is <B>Mentally Retarded who cannot travel without an escort</B> for the purpose of issuing Railway concession certificate<br>
      </td>
                  <td colspan="3" class="label">
              <% if(cer!=null && !cer.equalsIgnoreCase("0")){  %>
            <input type="radio" style="width:25px" name="railwaycertificate" checked="true" value="1" >Yes &nbsp;&nbsp;
             <input type="radio" style="width:25px" name="railwaycertificate" value="0">No


            <%}else if(cer!=null && cer.equalsIgnoreCase("0")) {%>
              <input type="radio" style="width:25px" name="railwaycertificate" value="1">Yes &nbsp;&nbsp;

              <input type="radio" style="width:25px" name="railwaycertificate" value="0" checked="true">No <%}

            else if(cer==null){
            %>
            <input type="radio" style="width:25px" name="railwaycertificate" value="1">Yes &nbsp;&nbsp;

              <input type="radio" style="width:25px" name="railwaycertificate" value="0">No

            <%}%>
                    </td></tr>
       <%  }

       if (exists!=null && exists.equalsIgnoreCase("vi")) {%>
 <tr> <td  class="label"> Whether the person is <B>Completely Blind</B> for the purpose of issuing railway concession <br>
            </td>
            <td colspan="3" class="label">
              <% if(cer!=null && !cer.equalsIgnoreCase("0")){  %>
            <input type="radio" style="width:25px" name="railwaycertificate" checked="true" value="1">Yes &nbsp;&nbsp;
             <input type="radio" style="width:25px" name="railwaycertificate" value="0">No
            <%}else if(cer!=null && cer.equalsIgnoreCase("0")) {%>
              <input type="radio" style="width:25px" name="railwaycertificate" value="1">Yes &nbsp;&nbsp;

              <input type="radio" style="width:25px" name="railwaycertificate" value="0" checked="true">No <%}

            else if(cer==null){
            %>
            <input type="radio" style="width:25px" name="railwaycertificate" value="1">Yes &nbsp;&nbsp;

              <input type="radio" style="width:25px" name="railwaycertificate" value="0">No
        
            <%}%>
                    </td>
 </tr>
       <%  }
if ( exists!=null && exists.equalsIgnoreCase("tl")) {%>
 <tr> <td  class="label"> Whether the person is Orthopeadically Handicapped/ Paraplegic person/Patient <B>Who Can not travel without the assistance of an Escort</B> for the purpose of issuing Railway Concession certificate  <br>
            </td>
           <td colspan="3" class="label">
              <% if(cer!=null && !cer.equalsIgnoreCase("0")){  %>
            <input type="radio" style="width:25px" name="railwaycertificate" checked="true" value="1">Yes &nbsp;&nbsp;
             <input type="radio" style="width:25px" name="railwaycertificate" value="0">No


            <%}else if(cer!=null && cer.equalsIgnoreCase("0")) {%>
              <input type="radio" style="width:25px" name="railwaycertificate" value="1">Yes &nbsp;&nbsp;

              <input type="radio" style="width:25px" name="railwaycertificate" value="0" checked="true">No <%}

            else if(cer==null){
            %>
            <input type="radio" style="width:25px" name="railwaycertificate" value="1">Yes &nbsp;&nbsp;

              <input type="radio" style="width:25px" name="railwaycertificate" value="0">No

            <%}%>
                    </td></tr>
       <%  }if ( exists!=null && exists.equalsIgnoreCase("hi")) { %>
  <tr><td  class="label"> Whether the person is <B>Totally Deaf & Dumb (Both affliction together in the same person)</B> for the purpose of issuing Railway concession certificate  <br>
            </td>
              <td colspan="3" class="label">
              <% if(cer!=null && !cer.equalsIgnoreCase("0")){  %>
            <input type="radio" style="width:25px" name="railwaycertificate" checked="true" value="1">Yes &nbsp;&nbsp;
             <input type="radio" style="width:25px" name="railwaycertificate" value="0">No


            <%}else if(cer!=null && cer.equalsIgnoreCase("0")) {%>
              <input type="radio" style="width:25px" name="railwaycertificate" value="1">Yes &nbsp;&nbsp;

              <input type="radio" style="width:25px" name="railwaycertificate" value="0" checked="true">No <%}

            else if(cer==null){
            %>
            <input type="radio" style="width:25px" name="railwaycertificate" value="1">Yes &nbsp;&nbsp;

              <input type="radio" style="width:25px" name="railwaycertificate" value="0">No

            <%}%>
                    </td> </tr>
       <%  }%>
       <% if(cer!=null && cer.equalsIgnoreCase("0")){  %>
  <tr><td class="heading" colspan="2" align="left">Railway Certificate Rejected by  
          
         <%=doctorname%></td></tr><%}%>
  <tr><td class="heading" colspan="2" align="left">Doctor Details:</td></tr>
       <tr>
           <td class="label" align="left">
               <div>
               <select name="prefix" size="1">
           <%if(exis && exists!=null && exists.equalsIgnoreCase("mr")){%>
<option selected="yes" value="Dr" >Dr</option>
<option  value="Psychologist">Psychologist</option><%}else if(!exis && exists!=null && exists.equalsIgnoreCase("mr")){%>

<option value="Dr">Dr</option>
<option selected="yes" value="Psychologist">Psychologist</option><%}else{%>

<option value="Dr"  selected="yes">Dr</option>
<%}%>
</select>

               </div>

Doctor Name</td>
<td class="label">
    <input type="text"  maxlength="17" size="17" name="doctorname"  value="<%=doctorname%>"/>
</td>
</tr>
<tr>
<td class="label" align="left">Regn.No</td>
<td class="label">
<input type="text"  maxlength="17" size="17" name="regno" value="<%=regno%>"/>

</td>
</tr>
<tr>
<td class="label" align="left">Designation</td>

<td class="label">


<input type="text"  maxlength="25" size="25" name="designation" value="<%=designation%>"/>

</td>
</tr>





<tr>
<th align="center" colspan="2">
<html:submit value="Submit" styleClass="button"/>&nbsp;&nbsp;
<input type="reset" value="Reset" class="button" />
</th>
</table>
        </html:form>
    </body>
</html>