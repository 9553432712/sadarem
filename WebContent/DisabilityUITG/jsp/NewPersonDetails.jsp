<%--
    Document   : NewPersonDetails
    Created on : Oct 31, 2012, 4:30:25 PM
    Author     : 695536
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <%
                String physicalData = (String) request.getAttribute("physicalData");
                String readOnly = null;
                if((String) request.getAttribute("readOnly")!=null)
                    readOnly=(String) request.getAttribute("readOnly");
                //  int id=Integer.parseInt(physicalData);

                int id = 0;
                if (physicalData != null) {
                    id = Integer.parseInt(physicalData);
                }
    %>

    <script type="text/javascript">
        // var vv=document.forms[0];
        function personid(){
            if(document.forms[0].person.value==""){
                alert("Enter SADAREM ID");
                return false;
            }else{
                document.forms[0].mode.value="physicalImparimentDetails";
                document.forms[0].submit();
            }
        }
        function update(){
            if("1"==<%=physicalData%>){

                if(document.forms[0].f_can[0].checked==false && document.forms[0].f_can[1].checked==false){
                    alert("F-can must be selected(Yes/No)");
                    return false;
                }else if(document.forms[0].pp_can[0].checked==false && document.forms[0].pp_can[1].checked==false){
                    alert("PP-can must be selected(Yes/No)");
                    return false;
                }else if(document.forms[0].l_can[0].checked==false && document.forms[0].l_can[1].checked==false){
                    alert("L-can must be selected(Yes/No)");
                    return false;
                }else if(document.forms[0].kc_can[0].checked==false && document.forms[0].kc_can[1].checked==false){
                    alert("KC-can must be selected(Yes/No)");
                    return false;
                }else if(document.forms[0].b_can[0].checked==false && document.forms[0].b_can[1].checked==false){
                    alert("B-can must be selected(Yes/No)");
                    return false;
                }else if(document.forms[0].s_can[0].checked==false && document.forms[0].s_can[1].checked==false){
                    alert("S-can must be selected(Yes/No)");
                    return false;
                }else if(document.forms[0].st_can[0].checked==false && document.forms[0].st_can[1].checked==false){
                    alert("ST-can must be selected(Yes/No)");
                    return false;
                }else if(document.forms[0].w_can[0].checked==false && document.forms[0].w_can[1].checked==false){
                    alert("W-can must be selected(Yes/No)");
                    return false;
                }else if(document.forms[0].rw_can[0].checked==false && document.forms[0].rw_can[1].checked==false){
                    alert("RW-can must be selected(Yes/No)");
                    return false;
                }
                else {
                    document.forms[0].mode.value="updatedata";
                    document.forms[0].submit();
                }
            }

            if("2"==<%=physicalData%>){

                if(document.forms[0].se_can[0].checked==false && document.forms[0].se_can[1].checked==false){
                    alert("SE-can must be selected(Yes/No)");
                    return false;
                }else if(document.forms[0].rw_can[0].checked==false && document.forms[0].rw_can[1].checked==false){
                    alert("RW-can must be selected(Yes/No)");
                    return false;
                }
                else {
                    document.forms[0].mode.value="updatedata";
                    document.forms[0].submit();
                }
            }
            if("3"==<%=physicalData%>){

                if(document.forms[0].h_can[0].checked==false && document.forms[0].h_can[1].checked==false){
                    alert("H-can must be selected(Yes/No)");
                    return false;
                }else  {
                    document.forms[0].mode.value="updatedata";
                    document.forms[0].submit();
                }
            }
            if("4"==<%=physicalData%>){

                if(document.forms[0].rw_can[0].checked==false && document.forms[0].rw_can[1].checked==false){
                    alert("RW-can must be selected(Yes/No)");
                    return false;
                }
                else {
                    document.forms[0].mode.value="updatedata";
                    document.forms[0].submit();
                }
            }
            if("5"==<%=physicalData%>){

                if(document.forms[0].f_can[0].checked==false && document.forms[0].f_can[1].checked==false){
                    alert("F-can must be selected(Yes/No)");
                    return false;
                }else if(document.forms[0].pp_can[0].checked==false && document.forms[0].pp_can[1].checked==false){
                    alert("PP-can must be selected(Yes/No)");
                    return false;
                }else if(document.forms[0].l_can[0].checked==false && document.forms[0].l_can[1].checked==false){
                    alert("L-can must be selected(Yes/No)");
                    return false;
                }else if(document.forms[0].kc_can[0].checked==false && document.forms[0].kc_can[1].checked==false){
                    alert("KC-can must be selected(Yes/No)");
                    return false;
                }else if(document.forms[0].b_can[0].checked==false && document.forms[0].b_can[1].checked==false){
                    alert("B-can must be selected(Yes/No)");
                    return false;
                }else if(document.forms[0].s_can[0].checked==false && document.forms[0].s_can[1].checked==false){
                    alert("S-can must be selected(Yes/No)");
                    return false;
                }else if(document.forms[0].st_can[0].checked==false && document.forms[0].st_can[1].checked==false){
                    alert("ST-can must be selected(Yes/No)");
                    return false;
                }else if(document.forms[0].w_can[0].checked==false && document.forms[0].w_can[1].checked==false){
                    alert("W-can must be selected(Yes/No)");
                    return false;
                }else if(document.forms[0].rw_can[0].checked==false && document.forms[0].rw_can[1].checked==false){
                    alert("RW-can must be selected(Yes/No)");
                    return false;
                }else if(document.forms[0].se_can[0].checked==false && document.forms[0].se_can[1].checked==false){
                    alert("SE-can must be selected(Yes/No)");
                    return false;
                }else if(document.forms[0].h_can[0].checked==false && document.forms[0].h_can[1].checked==false){
                    alert("H-can must be selected(Yes/No)");
                    return false;
                }else {

                    document.forms[0].mode.value="updatedata";
                    document.forms[0].submit();
                }
            }
        }

        function onlyNumbers(evt)
        {
            var e = event || evt; // for trans-browser compatibility
            var charCode = e.which || e.keyCode;

            if (charCode > 31 && (charCode < 48 || charCode > 57))
                return false;

            return true;

        }

    </script>
    <body>
        <html:form action="newPersonDetails">
            <html:hidden property="mode"/>
            <input type="hidden" name="idVal" value="<%=id%>"/>


            <logic:present name="msg">
                <br><br>
                <p align="center"><font color="red">${msg}</font></p>
            </logic:present><br>

            <logic:present name="msg1">

                <p align="center"><font color="red">${msg1}</font></p>
            </logic:present>


            <table align="center" border="0" cellpadding="0" cellspacing="0"  class="innerTable" width="50%">

                <%
                if(readOnly!=null){%>
                <tr>
                    <td class="label" align="right">
                        SADAREM ID :
                    </td>
                    <td class="label" >
                        <html:text property="person" style="color:#a8a8a8"  readonly="true"  />
                    </td>


                </tr>
                <%readOnly=null;
                }else {%>

                <tr>
                    <td class="label" align="right">
                        SADAREM ID :
                    </td>
                    <td class="label" >
                        <html:text property="person" maxlength="17" size="25" onkeypress="return onlyNumbers();" />
                    </td>


                </tr>
                <%}%>
                <tr>
                    <td align="center" colspan="2">
                        <html:button property="but" value="Submit" onclick="personid();"/>
                    </td>
                </tr>

              
            </table><br><br>

            <logic:equal name="physicalData" value="1" >
                <table  align="center" cellspacing="0" cellpadding="5" border="1"  width="85%">


                    <caption> Locomotor Disability/OH Subtypes</caption>

                    <tr>
                        <td class="label">F-can perform work by manipulating with fingers<font color="red"><b>*</b></font></td>
                        <td colspan="3" class="label"><html:radio  property="f_can" value ="1" >Yes</html:radio><html:radio  property="f_can" value ="0" >No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">PP-can perform work by pulling and pushing<font color="red"><b>*</b></font></td>
                        <td colspan="3" class="label"><html:radio  property="pp_can" value ="1" >Yes</html:radio><html:radio  property="pp_can" value ="0" >No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">L-can perform work by lifting<font color="red"><b>*</b></font></td>
                        <td colspan="3" class="label"><html:radio  property="l_can" value ="1" >Yes</html:radio><html:radio  property="l_can" value ="0" >No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">KC-can perform work by kneeling and crouching<font color="red"><b>*</b></font></td>
                        <td colspan="3" class="label"><html:radio  property="kc_can" value ="1" >Yes</html:radio><html:radio  property="kc_can" value ="0" >No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">B-can perform work by bending<font color="red"><b>*</b></font></td>
                        <td colspan="3"  class="label"><html:radio  property="b_can" value ="1" >Yes</html:radio><html:radio  property="b_can" value ="0" >No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">S-can perform work by sitting<font color="red"><b>*</b></font></td>
                        <td colspan="3" class="label"><html:radio  property="s_can" value ="1" >Yes</html:radio><html:radio  property="s_can" value ="0" >No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">ST-can perform work by standing<font color="red"><b>*</b></font></td>
                        <td colspan="3" class="label"><html:radio  property="st_can" value ="1" >Yes</html:radio><html:radio  property="st_can" value ="0" >No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">W-can perform work by walking<font color="red"><b>*</b></font></td>
                        <td colspan="3" class="label"><html:radio  property="w_can" value ="1" >Yes</html:radio><html:radio  property="w_can" value ="0" >No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">RW-can perform work by reading and writing<font color="red"><b>*</b></font></td>
                        <td colspan="3" class="label"><html:radio  property="rw_can" value ="1" >Yes</html:radio><html:radio  property="rw_can" value ="0" >No</html:radio></td>
                    </tr>


                </table>

            <tr>
                <td align="center"> <html:button property="loco" value="Submit" onclick="update();"/></td>
            </tr>
        </logic:equal>


        <logic:equal name="physicalData" value="2">
            <table  align="center" cellspacing="0" cellpadding="5" border="1" class="innerTable1" width="85%">

                <caption>Visual Impairment Subtypes</caption>

                <tr>
                    <td class="label">SE-can perform work by seeing<font color="red"><b>*</b></font></td>
                    <td class="label"><html:radio  property="se_can" value ="1" >Yes</html:radio><html:radio  property="se_can" value ="0" >No</html:radio></td>
                </tr>
                <tr>
                    <td class="label">RW-can perform work by reading and writing<font color="red"><b>*</b></font></td>
                    <td colspan="3" class="label"><html:radio  property="rw_can" value ="1" >Yes</html:radio><html:radio  property="rw_can" value ="0" >No</html:radio></td>
                </tr>


            </table>

            <tr>
                <td align="center"> <html:button property="loco" value="Submit" onclick="update();"/></td>
            </tr>
        </logic:equal>
        <logic:equal name="physicalData" value="3">
            <table  align="center" cellspacing="0" cellpadding="5" border="1" class="innerTable1" width="85%">
                <caption>Hearing Impairment</caption>

                <tr>
                    <td class="label" width="32%">H-can perform work by hearing/speaking<font color="red"><b>*</b></font></td>
                    <td  colspan="3" class="label"><html:radio  property="h_can" value ="1" >Yes</html:radio><html:radio  property="h_can" value ="0" >No</html:radio></td>
                </tr>

            </table>

            <tr>
                <td colspan="3" class="label" align="center"> <html:button property="loco" value="Submit" onclick="update();"/></td>
            </tr>
        </logic:equal>
        <logic:equal name="physicalData" value="4">
            <table  align="center" cellspacing="0" cellpadding="5" border="1" class="innerTable1" width="85%">
                <caption>Mental Retardation </caption>

                <tr>
                    <td class="label">RW-can perform work by reading and writing<font color="red"><b>*</b></font></td>
                    <td colspan="3" class="label"><html:radio  property="rw_can" value ="1" >Yes</html:radio><html:radio  property="rw_can" value ="0" >No</html:radio></td>
                </tr>

            </table>

            <tr>
                <td align="center"> <html:button property="loco" value="Submit" onclick="update();"/></td>
            </tr>
        </logic:equal>
        <logic:equal name="physicalData" value="5">
            <table  align="center" cellspacing="0" cellpadding="5" border="1"  width="85%">
                <caption>Multiple Disability</caption>


                <tr>
                    <td class="label">F-can perform work by manipulating with fingers<font color="red"><b>*</b></font></td>
                    <td colspan="3" class="label"><html:radio  property="f_can" value ="1" >Yes</html:radio><html:radio  property="f_can" value ="0" >No</html:radio></td>
                </tr>
                <tr>
                    <td class="label">PP-can perform work by pulling and pushing<font color="red"><b>*</b></font></td>
                    <td colspan="3" class="label"><html:radio  property="pp_can" value ="1" >Yes</html:radio><html:radio  property="pp_can" value ="0" >No</html:radio></td>
                </tr>
                <tr>
                    <td class="label">L-can perform work by lifting<font color="red"><b>*</b></font></td>
                    <td colspan="3" class="label"><html:radio  property="l_can" value ="1" >Yes</html:radio><html:radio  property="l_can" value ="0" >No</html:radio></td>
                </tr>
                <tr>
                    <td class="label">KC-can perform work by kneeling and crouching<font color="red"><b>*</b></font></td>
                    <td colspan="3" class="label"><html:radio  property="kc_can" value ="1" >Yes</html:radio><html:radio  property="kc_can" value ="0" >No</html:radio></td>
                </tr>
                <tr>
                    <td class="label">B-can perform work by bending<font color="red"><b>*</b></font></td>
                    <td colspan="3"  class="label"><html:radio  property="b_can" value ="1" >Yes</html:radio><html:radio  property="b_can" value ="0" >No</html:radio></td>
                </tr>
                <tr>
                    <td class="label">S-can perform work by sitting<font color="red"><b>*</b></font></td>
                    <td colspan="3" class="label"><html:radio  property="s_can" value ="1" >Yes</html:radio><html:radio  property="s_can" value ="0" >No</html:radio></td>
                </tr>
                <tr>
                    <td class="label">ST-can perform work by standing<font color="red"><b>*</b></font></td>
                    <td colspan="3" class="label"><html:radio  property="st_can" value ="1" >Yes</html:radio><html:radio  property="st_can" value ="0" >No</html:radio></td>
                </tr>
                <tr>
                    <td class="label">W-can perform work by walking<font color="red"><b>*</b></font></td>
                    <td colspan="3" class="label"><html:radio  property="w_can" value ="1" >Yes</html:radio><html:radio  property="w_can" value ="0" >No</html:radio></td>
                </tr>

                <tr>
                    <td class="label">RW-can perform work by reading and writing<font color="red"><b>*</b></font></td>
                    <td colspan="3" class="label"><html:radio  property="rw_can" value ="1" >Yes</html:radio><html:radio  property="rw_can" value ="0" >No</html:radio></td>
                </tr>
                <tr>
                    <td class="label">SE-can perform work by seeing<font color="red"><b>*</b></font></td>
                    <td colspan="3" class="label"><html:radio  property="se_can" value ="1" >Yes</html:radio><html:radio  property="se_can" value ="0" >No</html:radio></td>
                </tr>
                <tr>
                    <td class="label" width="32%">H-can perform work by hearing/speaking<font color="red"><b>*</b></font></td>
                    <td  colspan="3" class="label"><html:radio  property="h_can" value ="1" >Yes</html:radio><html:radio  property="h_can" value ="0" >No</html:radio></td>
                </tr>

            </table>


            <tr>
                <td colspan="3" class="label" align="center"> <html:button property="loco" value="Update" onclick="update();"/></td>
            </tr>
        </logic:equal>

    </html:form>
</body>
</html>
