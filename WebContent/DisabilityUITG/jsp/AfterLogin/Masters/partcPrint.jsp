<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="org.bf.disability.dto.PartCUpdateDTO" %>




<html:html locale="true">

    <script language="javascript" >
    function textCounter2()
        {
   if (document.forms[0].anyotherservices.value.length > 100) // if too long...trim it!
       document.forms[0].anyotherservices.value = document.forms[0].anyotherservices.value.substring(0,100);
    // otherwise, update 'characters left' counter
        }


         function avoidDuplicateForm(thisform){
        if (thisform.getAttribute('submitted'))
            return false;
        thisform.setAttribute('submitted','true');
    }

        </script>

    <script>
	function disableForm(theform) {
		if (document.all || document.getElementById) {
			for (i = 0; i < theform.length; i++) {
			var formElement = theform.elements[i];
				if (true) {
					formElement.disabled = true;
				}
			}
		}
	}
</script>
<style type="text/css">

/* GRID Starts */

.gridbg1 {
	background-color:#f4f4f4; text-align:left;  border-bottom:1px #b0b0b0 solid;  border-left:1px #b0b0b0 solid; vertical-align:middle; font-size:11px;  font-weight:400; height:20px; padding-left:5px; padding-right:5px; padding:4px;
}
.gridbg2 {
	background-color:#eae9e9; text-align:center;  border-bottom:1px #b0b0b0 solid;  border-left:1px #b0b0b0 solid; vertical-align:middle; font-size:10px;  font-weight:400; height:20px; padding-left:5px; padding-right:5px;
}
.gridhdbg {
	background-color:#b1b0b0; text-align:center;  border-bottom:1px #b0b0b0 solid;  border-left:1px #575757 solid; vertical-align:middle; font-size:12px;  font-weight:bold; height:20px;
}
.gridtb {
	border-right:1px #b0b0b0 solid; border-top:1px #b0b0b0 solid;text-align:center;
}

/* GRID Ends */
</style>

    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Disabilities</title>

    <script language="javascript" src="./DisabilityUITG/js/partc.js"></script>

    <body onload="disableForm(PartCUpdateForm);">


        
        <html:form action="/partcPrint.do?selectPartc=selectPartcPrint" method="post" styleId="PartCUpdateForm">

 <table align="center" cellspacing="0" cellpadding="0" width="45%">
            <tr>
                <td align="center"><font color="blue"><b>SADAREM ID : <%=session.getAttribute("personcode")%></b></font></td>
            </tr>
       </table><br/>
       <table  align="center" cellspacing="1" cellpadding="5" border="1" class="gridtb" width="85%">
           <tr>
           <td colspan="3" class="gridhdbg" align="center">Need Assessment/Refered/Recommended(Common for all Disabilities)</td>
            </tr>

            <tr>
                <td class="gridhdbg">&nbsp;</td>
           <td class="gridhdbg" align="center">Old Values</td>
           <td class="gridhdbg" align="center">New Values</td>
            </tr>

            <tr> <td  class="gridbg1">
            1. Early Education Services (For Children Below 5 years)</td>
                <td class="gridbg1" align="center">
                <html:checkbox property="earlyeducationservices" value="Early Education Services"  >
                </html:checkbox>
            </td>

            <td class="gridbg1" align="center">
               <input type="checkbox" name="mohan" value="4" />
            </td>

            </tr>

            <tr> <td class="gridbg1">
                    2. Education Services(Select Fields)</td>
            <td class="gridbg1"> <font size="2">
            <html:select property="educationservies" value=""  multiple="multiple">
                <html:option value="Home Based Education">Home Based Education</html:option>
                <html:option value="Special School">Special School</html:option>
                <html:option value="Inclusive Education">Inclusive Education</html:option></font>
            </html:select></td>


              <td class="gridbg1"> <font size="2">
                      <select name="mohan" value=""  multiple="multiple">
                <option value="Home Based Education">Home Based Education</option>
                <option value="Special School">Special School</option>
                <option value="Inclusive Education">Inclusive Education</option></font>
            </select></td>


            </tr>


            <tr>
                <td class="gridbg1">3. Vocational Training(Select Fields)</td>
                <td class="gridbg1">
                    <html:select property="vocationaltraining" value=""  multiple="multiple">
                    <html:option value="For employment in public/ pvt. sector">For Employment In Public/ Pvt. Sector</html:option>
                    <html:option value="For self-employment">For Self-Employment</html:option>

                    </html:select>
                </td>

                <td class="gridbg1">
                   <select property="mohan" value="" multiple="multiple" >
                    <option value="For employment in public/ pvt. sector">For Employment In Public/ Pvt. Sector</option>
                    <option value="For self-employment">For Self-Employment</option>

                    </select>
                </td>

            </tr>



            <tr>
                <td class="gridbg1" colspan="3"> 4.Counseling & Guidance</td>
            </tr>
            <tr> <td class="gridbg1">
                    <font size="2">      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Individual</font></td>
                <td><html:checkbox property="individual" value="Individual" > </html:checkbox> </td>
                 <td>
               <input type="checkbox" name="mohan" value="4" />
            </td>
            </tr>
            <tr>
                <td class="gridbg1"><font size="2">    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Family</font></td>
                <td><html:checkbox property="family" value="Family" > </html:checkbox></td>
                 <td>
               <input type="checkbox" name="mohan" value="4" />
            </td>
            </tr>


            <tr>
                <td  class="gridbg1">5. Any Other General Needs ( Only 100 Characters )</td>
                <td class="gridbg1"> <html:textarea rows="4" cols="30" property="anyotherservices" onkeydown="textCounter2()" onkeyup="textCounter2()"></html:textarea></td>
                 <td  class="gridbg1"><TEXTAREA NAME="mohan" COLS=30 ROWS=4></TEXTAREA>

</td>
            </tr>

           <tr>
                 <td  class="label" >6. Whether the person is Eligible for Railway Certificate </td>
               <td class="label"> <input type="checkbox" name="rekha" value="1"/>  Yes
            <input type="checkbox" name="rekha" value="0"/>  No</td>
            </tr>
             <tr>
                <td  class="label">7.Justify About Reassessment ( Only 100 Characters )</td>
                <td class="label"> <textarea rows="4" cols="30" name="rekha" onkeydown="textCounter2()" onkeyup="textCounter2()"></textarea></td>
            </tr>


         <%--        <% if (totalinInt >= 40) {%>
        <tr>

            <% if ("1".equals(disabilityId) || "1".equals(maxId) || "OH".equals(secondmax)) {%>
            <td  class="label">6. Eligible for Railway Concession Certificate<br>
                (Connot Travel without the Assistance of an Escort)
            </td>
            <td>  <html:checkbox property="railwaycertificate" value="1"></html:checkbox></td>
            <%  } else if ("2".equals(disabilityId) || "2".equals(maxId) || "VI".equals(secondmax)) {%>
            <td  class="label">6. Eligible for Railway Concession Certificate<br>
                (Completely Blind Person)
            </td>
            <td>  <html:checkbox property="railwaycertificate" value="1"></html:checkbox></td>
            <%  } else if ("3".equals(disabilityId) || "3".equals(maxId) || "HI".equals(secondmax)) {%>
            <td  class="label">6. Eligible for Railway Concession Certificate<br>
                (Totally Deaf and Dumb Person)
            </td>
            <td>  <html:checkbox property="railwaycertificate" value="1"></html:checkbox></td>
            <%  } else if ("4".equals(disabilityId) || "4".equals(maxId) || "MR".equals(secondmax)) {%>
            <td  class="label">6. Eligible for Railway Concession Certificate<br>
                (Connot Travel without an Escort)
            </td>
            <td>  <html:checkbox property="railwaycertificate" value="1"></html:checkbox></td>
            <%--    <%  } else if ("5".equals(disabilityId) || "5".equals(maxId)) { %>
                      Connot Travel without an escort
                <%  }  --%>
         <%--   <%  }%>

        </tr>
        <% }%> --%>
       </table>

<br><br>



        </html:form>

<form action="">
     <table align=center>


            <html:button property="bu" value="Go to Home" onclick="goHome()" styleClass="button"/>
            <html:button property="Reset" value="Print" onclick="window.print()" styleClass="button"/>
            </table>

</form>

    </body>
 <script>

                function goHome()
{
            document.forms[0].action="PrintFormatsSelection.do";
            document.forms[0].submit();
}

         </script>
</html:html>
