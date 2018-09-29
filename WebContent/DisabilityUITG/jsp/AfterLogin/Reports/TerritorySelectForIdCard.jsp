
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

    

    <html:form action="/TerritorySelectForIdcard.do" method="get" onsubmit="return validate_form(this)"   >
        <input type="hidden" name="status" value="finish"/>
        <input type="hidden" naem="idcard" value="card" />
        <input type="hidden" name="print" value="idcard" />
        <table  align="center" cellspacing="1" cellpadding="8" class="inputform" width="90%">
            <tr>
                <th  colspan="4">Select the Territory</th>
            </tr>
            <tr>
                <td>
                    <table  align="center" cellspacing="0" cellpadding="0" class="inputform" width="100%">
                        <tr><td >District<font color="red"><b>*</b></font></td>
                            <td>
                                <html:select  property="district_id" onchange="getData(this.value)" >
                                    <html:option  value="">--SELECT--</html:option>
                                    <html:optionsCollection   property="districtlist" label="district_name" value="district_id"  />
                                </html:select>
                            </td>
                            <td >Mandal<font color="red"><b>*</b></font></td>
                            <td>
                                <html:select property="mandal_id" onchange="getData(this.value)" >
                                    <html:option  value="">--SELECT--</html:option>
                                    <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"  />
                                </html:select>
                            </td>
                        </tr>

                        <tr><td >Village<font color="red"><b>*</b></font></td>
                            <td>
                                <html:select property="village_id" onchange="getData(this.value)">
                                    <html:option  value="">--SELECT--</html:option>
                                    <html:optionsCollection   property="villagelist" label="village_name" value="village_id"  />
                                </html:select>
                            </td>
                            <td >Habitation<font color="red"><b>*</b></font></td>
                            <td>
                                <html:select property="habitation_id" onchange="getData(this.value)">
                                    <html:option  value="">--SELECT--</html:option>
                                    <html:optionsCollection   property="habitationlist" label="habitation_name" value="habitation_id"  />
                                </html:select>
                            </td>
                        </tr>
                        <input type="hidden" name="personstatus" value="Eligible" />


                        <tr><td >SADAREM ID<font color="red"><b>*</b></font></td>
                            <td colspan="3">
                                <html:select property="personcode" >
                                    <html:option  value="">--SELECT--</html:option>
                                    <html:optionsCollection property="personcodelist" label="personcode" value="personcode" />
                                </html:select>
                            </td></tr>
                    </table>
                </td>
            </tr>


            <!--    <tr><td></td><td><b><font size="2">OR</font></b></td><tr>

                <tr>
   <tr class="tbl_bg2_content_hdr_new"> <font size="2"><td><b>Enter SADAREM ID</font></td><td colspan="3">
                      <input type="text"  maxlength="17" size="17" name="enterpersoncode" />
                       </td>
                   </tr>-->

            <tr>
                <th colspan="4" align="center"><input type="Submit" name="card" value="Submit" class="button"></th>
            </tr>

        </table> 
    </html:form>


</body>
</html:html>
