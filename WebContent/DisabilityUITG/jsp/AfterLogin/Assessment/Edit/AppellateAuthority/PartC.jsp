<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="org.bf.disability.dto.PartCUpdateDTO" %>
<% Double totaldisability = (Double) request.getAttribute("total");
            //double total=totaldisability.doubleValue();
            int totalinInt = totaldisability.intValue();
            String maxId = (String) request.getAttribute("maxId");
            String disabilityId = (String) request.getAttribute("disabilityId");
            String secondmax = (String) request.getAttribute("2ndmax");%>



<html:html locale="true">

    <script language="javascript" >
        function textCounter2()
        {
            if (document.forms[0].anyotherservices.value.length > 100) // if too long...trim it!
                document.forms[0].anyotherservices.value = document.forms[0].anyotherservices.value.substring(0,100);
            // otherwise, update 'characters left' counter
        }


        function avoidDuplicateForm(thisform){
            <% if(session.getAttribute("sadaremCodeAu")!=null){  %>
            // Added by rekha
            var c=document.forms[0].scode.value;
            if(c!=null){
                var k=document.forms[0].reassessment.value ;
                if(k==""){
                    alert("Please Enter Justification About Reassessment");
                    return false;
                }else{

                    if (document.forms[0].reassessment!=null && document.forms[0].reassessment.value.length > 100) // if too long...trim it!
                        document.forms[0].reassessment.value = document.forms[0].reassessment.value.substring(0,100);
                    //return true;
                }
            }
            //End
            <%}%>
            if (thisform.getAttribute('submitted')){
                return false;
            }else{
            thisform.setAttribute('submitted','true');            
            document.getElementById('toatlDisButton').disabled = true;
            }
        }

    </script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Disabilities</title>

        <script language="javascript" src="./DisabilityUITG/js/partc.js"></script>

    <body>


    
    <html:form action="/partcinserted.do?partcInsert=partcInsert" method="post" styleId="PartCUpdateForm"
               onsubmit="return avoidDuplicateForm(this)">
<%if(request.getAttribute("selectedValue")!=null){%>
 <input type="hidden" name="selectFlow" id="selectFlow" value="<%=request.getAttribute("selectedValue")%>"/>
<%}%>
      
        <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="90%">
            <tr>
                <th colspan="10">Need Assessment/Refered/Recommended(Common for all Disabilities)</th>
            </tr>
            <%
                        int caAU = 0, ca = 0, caPer = 0;
                        if (session.getAttribute("ageAU") != null) {
                            caAU = Integer.parseInt((String) session.getAttribute("ageAU"));
                        } else if (session.getAttribute("agePersonalInsert") != null) {
                            caPer = Integer.parseInt((String) session.getAttribute("agePersonalInsert"));
                        } else {
                            ca = Integer.parseInt((String) session.getAttribute("agePersonal"));
                        }
                        if (caAU <= 3 && ca <= 3 && caPer <= 3) {
            %>


            <tr> <td>
                    1. Early Education Services (For Children Below 5 years)</td>
                <td>
                    <html:checkbox property="earlyeducationservices" value="Early Education Services" style="width:25px" >
                    </html:checkbox>
                </td></tr>
                <%} else {%>
            <tr> <td>
                    1. Early Education Services (For Children Below 5 years)</td>
                <td>
                    <html:checkbox property="earlyeducationservices" value="Early Education Services"  disabled="true" style="width:25px">
                    </html:checkbox>
                </td></tr>
                <%}%>
            <tr> <td>
                    2. Education Services(Select Fields)</td>
                <td > <font size="2">
                        <html:select property="educationservies" value=""  style="width:250px">
                            <html:option value="" styleId="hear">---  Select One ---</html:option>
                            <html:option value="Home Based Education">Home Based Education</html:option>
                            <html:option value="Special School">Special School</html:option>
                            <html:option value="Inclusive Education">Inclusive Education</html:option></font>
                    </html:select></td>
            </tr>


            <tr> 
                <td>3. Vocational Training(Select Fields)</td>
                <td> 
                    <html:select property="vocationaltraining" value=""  style="width:250px">
                        <html:option value="" styleId="hearer">---  Select One ---</html:option>
                        <html:option value="For employment in public/ pvt. sector">For Employment In Public/ Pvt. Sector</html:option>
                        <html:option value="For self-employment">For Self-Employment</html:option>
                    </html:select>
                </td> 

            </tr>



            <tr> 
                <td colspan="4"> 4.Counseling & Guidance</td>
            </tr>
            <tr> <td>
                    <font size="2">      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Individual</font></td>
                <td><html:checkbox property="individual" value="Individual" style="width:25px"> </html:checkbox> </td>
            </tr>
            <tr>
                <td><font size="2">    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Family</font></td>
                <td><html:checkbox property="family" value="Family" style="width:25px"> </html:checkbox></td>
            </tr>


            <tr>
                <td >5. Any Other General Needs ( Only 100 Characters )</td>
                <td > <html:textarea rows="4" cols="30" property="anyotherservices" onkeydown="textCounter2()" onkeyup="textCounter2()"></html:textarea></td>
            </tr>
            <%--        <% if (totalinInt >= 40) {%>
           <tr>

            <% if ("1".equals(disabilityId) || "1".equals(maxId) || "OH".equals(secondmax)) {%>
            <td  >6. Eligible for Railway Concession Certificate<br>
                (Connot Travel without the Assistance of an Escort)
            </td>
            <td>  <html:checkbox property="railwaycertificate" value="1"></html:checkbox></td>
            <%  } else if ("2".equals(disabilityId) || "2".equals(maxId) || "VI".equals(secondmax)) {%>
            <td  >6. Eligible for Railway Concession Certificate<br>
                (Completely Blind Person)
            </td>
            <td>  <html:checkbox property="railwaycertificate" value="1"></html:checkbox></td>
            <%  } else if ("3".equals(disabilityId) || "3".equals(maxId) || "HI".equals(secondmax)) {%>
            <td  >6. Eligible for Railway Concession Certificate<br>
                (Totally Deaf and Dumb Person)
            </td>
            <td>  <html:checkbox property="railwaycertificate" value="1"></html:checkbox></td>
            <%  } else if ("4".equals(disabilityId) || "4".equals(maxId) || "MR".equals(secondmax)) {%>
            <td  >6. Eligible for Railway Concession Certificate<br>
                (Connot Travel without an Escort)
            </td>
            <td>  <html:checkbox property="railwaycertificate" value="1"></html:checkbox></td>
            <%--    <%  } else if ("5".equals(disabilityId) || "5".equals(maxId)) { %>
                      Connot Travel without an escort
                <%  }  --%>
            <%--   <%  }%>

        </tr>
        <% }%> --%>
            <%
                        String scode = (String) session.getAttribute("sadaremCodeAu");
                        String catid=(String)session.getAttribute("categoryIdAu");
                        if (scode != null) 
                        if(catid!=null && catid.equalsIgnoreCase("2")){
            %>
            <html:hidden property="scode" value="<%=scode%>"/>
            <tr>
                <td>6.Justification About Reassessment<font color="red">*</font></td>
                <td >
                    <html:textarea rows="4" cols="30" property="reassessment"></html:textarea>
                </td>
            </tr>
            <%}%>
       
            <th colspan="10"><html:submit value="Submit" onclick="return CheckValidate()" styleId="toatlDisButton" styleClass="button"></html:submit>
            <html:button property="Reset" value="Reset" onclick="doReset()" styleClass="button"/></th>
        </table>
    </html:form>
</body>
<% session.removeAttribute("teluguNameAu");%>
</html:html>
