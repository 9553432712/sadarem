
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

            if (validate_required(mandal_id,"Select Mandal")==false)
            {
            mandal_id.focus();
            return false
            }

            if (validate_required(village_id,"Select Village")==false)
            {
            village_id.focus();
            return false
            }
            if (validate_required(habitation_id,"Select Habitation")==false)
            {
            habitation_id.focus();
            return false
            }
             if (validate_required(personcode,"Select personcode")==false)
            {
            personcode.focus();
            return false
            }
            }
            }

            function getData(read)
            {

            document.forms[0].status.value="update";
            document.forms[0].submit();
            }
        </script>

    </head>

    <BODY>

    

    <html:form action="/TerritorySelectForUpdate.do" method="get" onsubmit="return validate_form(this)"   >
        <input type="hidden" name="status" value="finish"/><br>
        <center>
            <br><br><br>
       <table  align="center" cellspacing="1" cellpadding="5" class="innerTable" width="60%">
       <tr>
            <td colspan="2" align="center" class="heading">Select the Territory For Update</td>
        </tr></table>
        <table  align="center" cellspacing="1" cellpadding="5" class="innerTable1" width="60%">
          <%--  <tr class="tbl_bg2_content_hdr_new"> <font size="2"><td><b> District</font></td>
                <td colspan="3">
                    <html:select  property="district_id" onchange="getData(this.value)" >
                        <html:option  value="">--SELECT--</html:option>
                        <html:optionsCollection   property="districtlist" label="district_name" value="district_id"  />
                    </html:select>
                </td>
            </tr> --%>
           <tr><td class="columnHeight20" colspan="2"></td></tr>
            <tr><td align = "center" class="label">Mandal</td>
                <td class="textbox">
                    <html:select property="mandal_id" onchange="getData(this.value)" >
                        <html:option  value="">--SELECT--</html:option>
                        <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"  />
                    </html:select>
                </td>
            </tr>

            <tr><td align = "center" class="label">Village</td>
                <td class="textbox">
                    <html:select property="village_id" onchange="getData(this.value)">
                        <html:option  value="">--SELECT--</html:option>
                        <html:optionsCollection   property="villagelist" label="village_name" value="village_id"  />
                    </html:select>
                </td>
            </tr>
            <tr><td align = "center" class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Habitation</td>
                <td class="textbox">
                <html:select property="habitation_id" onchange="getData(this.value)">
                    <html:option  value="">--SELECT--</html:option>
                    <html:optionsCollection   property="habitationlist" label="habitation_name" value="habitation_id"  />
                </html:select>
            </td>
            </tr>
            <tr><td align = "center" class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PersonStatus</td>
                <td class="textbox">
             <html:select property="personstatus" onchange="getData(this.value)">
                  <html:option  value="0">  --SELECT--   </html:option>
                    <html:option value="Eligible">Eligible</html:option>
                    <html:option value="Rejected">Rejected</html:option>
             </html:select>
             </td></tr>

             <tr><td align = "center" class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SADAREM ID</td>
             <td class="textbox">
             <html:select property="personcode" >
                   <html:option  value="">--SELECT--</html:option>
                 <html:optionsCollection property="personcodelist" label="personcode" value="personcode" />
             </html:select>
             </td></tr>

         <!--    <tr><td></td><td><b><font size="2">OR</font></b></td><tr>

             <tr>
<tr class="tbl_bg2_content_hdr_new"> <font size="2"><td><b>Enter Personcode</font></td><td colspan="3">
                   <input type="text"  maxlength="17" size="17" name="enterpersoncode" />
                    </td>
                </tr>-->

           <tr>
       <td class="columnHeight10" colspan="2"></td>
       </tr>

       <tr>
       <td colspan="2" align = "center"><input type="Submit" value="Submit" class="button" ></td>
       </tr>
       <tr><td class="columnHeight20"></td></tr>

        </table> </center>
    </html:form>


    </body>
</html:html>
