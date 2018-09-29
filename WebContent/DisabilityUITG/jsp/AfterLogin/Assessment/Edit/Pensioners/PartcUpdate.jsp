<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.bf.disability.dao.PartCDAO"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@ page import="java.util.*,java.text.*"%>
<%@page session="true"%>
<% Double totaldisability = (Double) request.getAttribute("total");
            String personcode = (String) session.getAttribute("personcode");
            //double total=totaldisability.doubleValue();
            int totalinInt = totaldisability.intValue();
            String maxId = (String) request.getAttribute("maxId");
            String disabilityId = (String) request.getAttribute("disabilityId");
            String secondmax = (String) request.getAttribute("2ndmax");


%>




<html:html>

    <script language="javascript" >
        function textCounter2()
        {
            if (document.forms[0].anyotherservices.value.length > 100) // if too long...trim it!
                document.forms[0].anyotherservices.value = document.forms[0].anyotherservices.value.substring(0,100);
            // otherwise, update 'characters left' counter
        }

        function avoidDuplicateForm(thisform){
        <%if (request.getAttribute("categoryIdPartC") != null && (session.getAttribute("roleId").toString().equals("3") || session.getAttribute("roleId").toString().equals("5")) && request.getAttribute("categoryIdPartC").toString().equals("2")) {%>
            // Added by mohan
            // var c=document.forms[0].scode.value;
            //   if(c!=null){
            var k=document.forms[0].reassessment.value ;
            if(k==""){
                alert("Please Enter Justification for Reassessment");
                return false;
            }else{

                if (document.forms[0].reassessment!=null && document.forms[0].reassessment.value.length > 100) // if too long...trim it!
                    document.forms[0].reassessment.value = document.forms[0].reassessment.value.substring(0,100);
                //return true;
            }
            //  }
            //End
                <%}%>

                if (thisform.getAttribute('submitted')){
                return false;
                }
                else{                  
            thisform.setAttribute('submitted','true');            
            document.getElementById('toatlDisButton').disabled = true;
            }
        }

    </script>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Disabilities</title>
</head>

<body>

    <%
                String exists = null;
                exists = (String) request.getAttribute("exists");

//disability type stored in session for inserting into railway doctors details table
                request.getSession().setAttribute("rail_impair", exists);

                String e = (String) request.getSession().getAttribute("railway");


    %>




<script language="javascript" src="./DisabilityUITG/js/PartcUpdate.js"></script>
<html:form action="/insertsuccess1.do?updatePartc=updatePartc" method="post" styleId="PartCUpdateForm"
           onsubmit="return avoidDuplicateForm(this)">
    <input type="hidden" name="<%= Constants.TOKEN_KEY%>"
           value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY)%>" >
    <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
        <tr>
            <th colspan="8">Need Assessment/Referred/Recommended(Common for all Disabilities)</th>
        </tr>

        <tr>
            <td  >1. Early Education Services (For Children Below 5 years)</td>
            <td><html:checkbox property="earlyeducationservices" value="Early Education Services"  style="width:25px">
                </html:checkbox>
            </td>
        </tr>
        <tr>
            <td >2. Education Services (Select Fields) </td>
            <td > <font size="2">
                    <html:select property="educationservies"  style="width:250px">
                        <html:option value="">---  Select One ---</html:option>
                        <html:option value="Home Based Education">Home Based Education</html:option>
                        <html:option value="Special School">Special School</html:option>
                        <html:option value="Inclusive Education">Inclusive Education</html:option></font>
                </html:select></td>
        </tr>


        <tr>
            <td >3. Vocational Training (Select Fields) </td>
            <td >
                <html:select property="vocationaltraining"  style="width:250px">
                    <html:option value="" >---  Select One ---</html:option>
                    <html:option value="For employment in public/ pvt. sector">For Employment In Public/ Pvt. Sector</html:option>
                    <html:option value="For self-employment">For Self-Employment</html:option>
                </html:select>
            </td>
        </tr>

        <tr>
            <td colspan="8">4. Counseling & Guidance</td>
        </tr>
        <tr>
            <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i. Individual</td>
            <td><html:checkbox property="individual" value="Individual" style="width:25px"> </html:checkbox> </td>
        </tr>
        <tr>
            <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii. Family</td>
            <td><html:checkbox property="family" value="Family" style="width:25px"> </html:checkbox></td>
        </tr>


        <%
                    String raileli = (String) request.getAttribute("rail_eli");

                    int i = 5;

 //if (totalinInt >= 40 && raileli.equalsIgnoreCase("1")) {
                    if (totalinInt >= 40 && exists != null && exists.equalsIgnoreCase("mr")) {
                       i++;%>
        <tr><td  >5. Whether the person is <B>Mentally Retarded who cannot travel without an escort</B> for the purpose of issuing Railway concession certificate<br>

            </td>
            <td>  <% if (e != null && e.equalsIgnoreCase("1")) {                    %>

                <input type="radio" name="railwaycertificate"  value="1" checked="true" style="width:25px">Yes
                <input type="radio" name="railwaycertificate" value="0" style="width:25px">No

                <%} else if (e != null && e.equalsIgnoreCase("0")) {%>
                <input type="radio" name="railwaycertificate"  value="1" style="width:25px">Yes
                <input type="radio" name="railwaycertificate" checked="true" value="0" style="width:25px">No
                <%} else {%>
                <input type="radio" name="railwaycertificate"  value="1" style="width:25px">Yes
                <input type="radio" name="railwaycertificate"  value="0" style="width:25px">No
                <%}%></td></tr>


        <%  }

                   if (totalinInt >= 40 && exists != null && exists.equalsIgnoreCase("vi")) {
                       i++;%>
        <tr> <td  >5. Whether the person is <B>Completely Blind</B> for the purpose of issuing railway concession <br>

            </td>
            <td>  <% if (e != null && e.equalsIgnoreCase("1")) {                           %>

                <input type="radio" name="railwaycertificate"  value="1" checked="true" style="width:25px">Yes &nbsp;&nbsp;
                <input type="radio" name="railwaycertificate" value="0" style="width:25px">No

                <%} else if (e != null && e.equalsIgnoreCase("0")) {%>
                <input type="radio" name="railwaycertificate"  value="1" style="width:25px">Yes &nbsp;&nbsp;
                <input type="radio" name="railwaycertificate" checked="true" value="0" style="width:25px">No
                <%} else {%>
                <input type="radio" name="railwaycertificate"  value="1" style="width:25px">Yes &nbsp;&nbsp;
                <input type="radio" name="railwaycertificate"  value="0" style="width:25px">No
                <%}%></td> </tr>


        <%  }

                    if (totalinInt >= 40 && exists != null && exists.equalsIgnoreCase("tl")) {
                       i++;%>
        <tr> <td  >5. Whether the person is Orthopeadically Handicapped/ Paraplegic person/Patient <B>Who Can not travel without the assistance of an Escort</B> for the purpose of issuing Railway Concession certificate  <br>

            </td>
            <td> 
                <% if (e != null && e.equalsIgnoreCase("1")) {                        %>

                <input type="radio" name="railwaycertificate"  value="1" checked="true" style="width:25px">Yes
                <input type="radio" name="railwaycertificate" value="0" style="width:25px">No

                <%} else if (e != null && e.equalsIgnoreCase("0")) {%>
                <input type="radio" name="railwaycertificate"  value="1" style="width:25px">Yes 
                <input type="radio" name="railwaycertificate" checked="true" value="0" style="width:25px">No
                <%} else {%>
                <input type="radio" name="railwaycertificate"  value="1" style="width:25px">Yes 
                <input type="radio" name="railwaycertificate"  value="0" style="width:25px">No
                <%}%>
            </td> </tr>


        <%  }
                   if (totalinInt >= 40 && exists != null && exists.equalsIgnoreCase("hi")) {
                       i++;%>
        <tr><td  >5. Whether the person is <B>Totally Deaf & Dumb (Both affliction together in the same person)</B> for the purpose of issuing Railway concession certificate  <br>

            </td>
            <td>  <% if (e != null && e.equalsIgnoreCase("1")) {                            %>

                <input type="radio" name="railwaycertificate"  value="1" checked="true" style="width:25px">Yes 
                <input type="radio" name="railwaycertificate" value="0" style="width:25px">No

                <%} else if (e != null && e.equalsIgnoreCase("0")) {%>
                <input type="radio" name="railwaycertificate"  value="1" style="width:25px">Yes
                <input type="radio" name="railwaycertificate" checked="true" value="0" style="width:25px">No
                <%} else {%>
                <input type="radio" name="railwaycertificate"  value="1" style="width:25px">Yes 
                <input type="radio" name="railwaycertificate"  value="0" style="width:25px">No
                <%}%></td> </tr>


        <%  }%>

        <tr>
            <td  ><%=i%>. Any Other  General Needs ( Only 100 Characters )</td>
            <td><html:textarea rows="4" cols="30" property="anyotherservices" onkeydown="textCounter2()" onkeyup="textCounter2()"></html:textarea>    </td>
        </tr>
        <% if (session.getAttribute("roleId").toString().equals("5") && request.getAttribute("categoryIdPartC").toString().equals("2")) {                        %>
        <tr>
            <td  >7.Justify About Reassessment</td>
            <td >
                <html:textarea rows="4" cols="30" property="reassessment"></html:textarea>
            </td>
        </tr>
        <%}%>
        
        <% if (session.getAttribute("roleId").toString().equals("3") && request.getAttribute("categoryIdPartC").toString().equals("2")) { %>
        <tr>
            <td  >7.Justify About Reassessment</td>
            <td >
                <html:textarea rows="4" cols="30" property="reassessment" style="width:600px"></html:textarea>
            </td>
        </tr>
        <%}%>
  
    <% request.getSession().removeAttribute("railway");%>
   

    <tr><th colspan="8">

                <html:submit value="Update" styleId="toatlDisButton" styleClass="button"></html:submit>
                <html:button property="" value="Reset" onclick="goReset()" styleClass="button"/></th>
        </tr>
    </table>


</html:form>
    <% request.removeAttribute("categoryIdPartC"); %>
</body>

</html:html>