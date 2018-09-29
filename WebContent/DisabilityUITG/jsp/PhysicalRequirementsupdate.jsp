<%-- 
    Document   : PhysicalRequirementsupdate
    Created on : Jul 30, 2012, 3:05:51 PM
    Author     : 490058
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@ page import="java.util.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <head>
        <%Integer olddisablityid = (Integer) request.getAttribute("olddisablityid");%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script language="JavaScript">

function mode(){
    alert("mode");
    document.partAForm.mode.value="update";
}

            function displayAppropriate()
            {
                var mode=document.partAForm.mode.value;
               // alert("mode onload==="+mode);
                if(mode=="dis"){
                var theDay =document.partAForm.disabilityId.value;
                //alert("disbilityid==="+theDay);
                switch (theDay)

                {
                    case "1":{
                            document.getElementById("locomotor").style.visibility = "Visible";
                            document.getElementById("locomotor").style.display = "Inline";
                            document.getElementById("commonCondition").style.visibility = "Visible";
                            document.getElementById("commonCondition").style.display = "Inline";
                          //  document.getElementById("conditionofdisabilityvisual").style.visibility = "Visible";
                          //  document.getElementById("conditionofdisabilityvisual").style.display = "Inline";
                          

                            break;
                        }
                    case "2":{
                            document.getElementById("visual").style.visibility = "Visible";
                            document.getElementById("visual").style.display = "Inline";
                            document.getElementById("commonCondition").style.visibility = "Visible";
                            document.getElementById("commonCondition").style.display = "Inline";
                        
                            break;
                        }

                    case "3":{
                            document.getElementById("hearing").style.visibility = "Visible";
                            document.getElementById("hearing").style.display = "Inline";
                          
                            break;
                        }
                    case "4":{
                            document.getElementById("commonCondition").style.visibility = "Visible";
                            document.getElementById("commonCondition").style.display = "Inline";
                           
                           
                            break;
                        }
                    case "5":{
                            alert("Physical reqirements are not suggested for Mentalillness disability");
                            document.getElementById("hideTB").style.visibility="InVisible";
                            return false;
                            
                            break;
                        }
                    case "6":{
                            document.getElementById("locomotor").style.visibility = "Visible";
                            document.getElementById("locomotor").style.display = "Inline";
                            document.getElementById("visual").style.visibility = "Visible";
                            document.getElementById("visual").style.display = "Inline";
                            document.getElementById("hearing").style.visibility = "Visible";
                            document.getElementById("hearing").style.display = "Inline";
                            document.getElementById("commonCondition").style.visibility = "Visible";
                            document.getElementById("commonCondition").style.display = "Inline";
                          
                            break;
                        }
                    case "7":{
                            break;
                        }

                }}else{
                flag=true;
                }

            }



            function deselect(read)
            {

                document.partAForm.disabilityLocoSubIds.value = null;

            }
            function getData(read)
            {
                document.forms[0].partAStatus.value="update";
                document.forms[0].submit();
            }




            function disabile()
            {
                if(document.forms[0].education.selectedIndex == 1)
                {
                    document.forms[0].edustatus.disabled = true;
                    document.forms[0].edustatus.value = 0;
                }
                else
                {
                    document.forms[0].edustatus.disabled = false;
                }
            }







            function validate_required(field,alerttxt)
            {
              //  alert("disb========"+field+alerttxt);
              //  alert("value========"+value);
                with (field)
                {//alert("value========"+value);
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
                var flag = false;
                with (thisform)
                {
                         var disabilty =document.partAForm.disabilityId.value;
                       //  alert("disbaility=="+disabilty + document.getElementsByName("se_can").length);
                 switch (disabilty)

                 {

                     case "2":{
                            var se=document.getElementsByName("se_can");var coun=0,rwcan=0;
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               coun++;
                            }}
                        se=document.getElementsByName("rw_can");
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               rwcan++;
                            }}

                        if(coun>=1 && rwcan>=1){
                            document.getElementById("hideTB").style.display='';
                           // document.getElementById("conditiondisabilityvisual").focus();
                            return true;
                        }else{
                                alert("SE-can perform work by seeing, RW_can must be selected(Yes/No)");
                                flag=false;
                                document.getElementById("hideTB").style.display='';
                                //document.getElementById("conditiondisabilityvisual").focus();
                                return flag;

                            }
                          break;
                    }

                    case "3":{


                          var se=document.getElementsByName("h_can");var coun=0;
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               coun++;
                            }}
                        if(coun>=1){
                            document.getElementById("hideTB").style.display='';
                           // document.getElementById("conditiondisabilityvisual").focus();
                            return true;
                        }else{
                                alert("H-can perform work by hearing/speaking must be selected(Yes/No)");
                                flag=false;
                                document.getElementById("hideTB").style.display='';
                             //   document.getElementById("conditiondisabilityvisual").focus();
                                return flag;

                            }
                          break;

                    }
               case "1":{
                          var se=document.getElementsByName("f_can");
                          var fcan=0,lcan=0,ppcan=0,kccan=0,bcan=0,scan=0,stcan=0,wcan=0,rwcan=0;
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               fcan++;
                            }}
                             se=document.getElementsByName("l_can");
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               lcan++;
                            }}

                             se=document.getElementsByName("pp_can");
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               ppcan++;
                            }}

                             se=document.getElementsByName("kc_can");
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               kccan++;
                            }}

                             se=document.getElementsByName("b_can");
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               bcan++;
                            }}

                             se=document.getElementsByName("s_can");
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               scan++;
                            }}

                             se=document.getElementsByName("st_can");
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               stcan++;
                            }}

                             se=document.getElementsByName("w_can");
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               wcan++;
                            }}
                        se=document.getElementsByName("rw_can");
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               rwcan++;
                            }}
            if(fcan>=1 && lcan>=1 && ppcan>=1&&kccan>=1&&bcan>=1&&scan>=1&&stcan>=1&&wcan>=1 &&rwcan>=1){
                document.getElementById("hideTB").style.display='';
                           // document.getElementById("conditiondisabilityvisual").focus();
                            return true;
		}else{
                     alert("f-can,pp_can,l_can,kc_can,b_can,s_can,st_can,RW_can must be selected(Yes/No)");
                            document.getElementById("hideTB").style.display='';
                           // document.getElementById("conditiondisabilityvisual").focus();
                            return false;}



                          break;
                    }
                     case "6":{
                            var se=document.getElementsByName("f_can");
                           // alert('seeeeeee==='+se + se.length);
                          var fcan=0,lcan=0,ppcan=0,kccan=0,bcan=0,scan=0,stcan=0,wcan=0,secan=0,hcan=0,rwcan=0;
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               fcan++;
                            }}
                             se=document.getElementsByName("l_can");
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               lcan++;
                            }}

                             se=document.getElementsByName("pp_can");
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               ppcan++;
                            }}

                             se=document.getElementsByName("kc_can");
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               kccan++;
                            }}

                             se=document.getElementsByName("b_can");
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               bcan++;
                            }}

                             se=document.getElementsByName("s_can");
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               scan++;
                            }}

                             se=document.getElementsByName("st_can");
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               stcan++;
                            }}

                             se=document.getElementsByName("w_can");
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               wcan++;
                            }}
                         se=document.getElementsByName("h_can");
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               hcan++;
                            }} se=document.getElementsByName("se_can");
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               secan++;
                            }}
                        se=document.getElementsByName("rw_can");
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               rwcan++;
                            }}

            if(fcan>=1 && lcan>=1 && ppcan>=1&&kccan>=1&&bcan>=1&&scan>=1&&stcan>=1&&wcan>=1 && secan>=1 &&hcan>=1 && rwcan>=1){
                document.getElementById("hideTB").style.display='';
                          //  document.getElementById("conditiondisabilityvisual").focus();
                            return true;
		}else{
                    flag=false;
                     alert("f-can,pp_can,l_can,kc_can,b_can,s_can,st_can,w_can, se_can,h_can,rw_can must be selected(Yes/No)");
                            document.getElementById("hideTB").style.display='';
                           // document.getElementById("conditiondisabilityvisual").focus();
                            return flag;}



                          break;
                    }
                    case "4":{

                           var se=document.getElementsByName("rw_can");
                           var rwcan=0;
                           for( i=0;i<se.length;i++){
                            if(se[i].checked){
                               rwcan++;
                            }}

                        if(rwcan>=1){
                            document.getElementById("hideTB").style.display='';
                            document.getElementById("conditiondisabilityvisual").focus();
                            return true;
                        }else{
                                alert("RW-can perform work by reading and writing must be selected(Yes/No)");
                                flag=false;
                                document.getElementById("hideTB").style.display='';
                                //document.getElementById("conditiondisabilityvisual").focus();
                                return flag;

                            }
                          break;
                    }

                    case "5":{
                            alert("Physical reqirements are not suggested for Mentalillness disability")
                            return false;
                    }

                    }

                    }
                    return flag;
                }

                function validateForm(thisForm){
                    var flag = false;
                    thisForm.mode.value="update";
                    flag = validate_form(thisForm);
                   if(flag){
                        if (thisForm.getAttribute('submitted')){
                            flag = false;
                            return flag;
                        }else{
                            thisForm.setAttribute('submitted','true');
                            document.getElementById('subDisablButton').disabled = true;
                        }
                    }
                    return flag;
                    
                }



        </script>
        
    </head>


    <body onload="displayAppropriate(this.value)">

        <html:form action="CertificatewithPhysicalRequirement.do"  styleId="partAForm"  onsubmit="return validateForm(this)">
            <input type="hidden" name="partAStatus" value="finish"/>
            <input type="hidden" name="previousdisabilityid" value="<%=olddisablityid%>"/>
            <br>
            <input type="hidden" name="<%= Constants.TOKEN_KEY%>"
                   value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY)%>" >

            +<br>
            <table  align="center" cellspacing="0" cellpadding="5" class="innerTable" width="85%">
                <tr>
                    <td align="center" class="heading">Update Physical Requirements Details</td>
                </tr>
            </table>
          
               

                <html:hidden property="disabilityId"/>
                <html:hidden property="personcode"/>
                <html:hidden property="mode" value="dis"/>

              
     
         



            <div id="locomotor" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="0" cellpadding="5" border="1" class="innerTable1" width="85%">
                    
                    <tr>
                        <td class="label">F-can perform work by manipulating with fingers</td>
                        <td colspan="3" class="label"><html:radio  property="f_can" value ="1" >Yes</html:radio><html:radio  property="f_can" value ="0" >No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">PP-can perform work by pulling and pushing</td>
                        <td colspan="3" class="label"><html:radio  property="pp_can" value ="1" >Yes</html:radio><html:radio  property="pp_can" value ="0" >No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">L-can perform work by lifting</td>
                        <td colspan="3" class="label"><html:radio  property="l_can" value ="1" >Yes</html:radio><html:radio  property="l_can" value ="0" >No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">KC-can perform work by kneeling and crouching</td>
                        <td colspan="3" class="label"><html:radio  property="kc_can" value ="1" >Yes</html:radio><html:radio  property="kc_can" value ="0" >No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">B-can perform work by bending</td>
                        <td colspan="3"  class="label"><html:radio  property="b_can" value ="1" >Yes</html:radio><html:radio  property="b_can" value ="0" >No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">S-can perform work by sitting</td>
                        <td colspan="3" class="label"><html:radio  property="s_can" value ="1" >Yes</html:radio><html:radio  property="s_can" value ="0" >No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">ST-can perform work by standing</td>
                        <td colspan="3" class="label"><html:radio  property="st_can" value ="1" >Yes</html:radio><html:radio  property="st_can" value ="0" >No</html:radio></td>
                    </tr>
                    <tr>
                        <td class="label">W-can perform work by walking</td>
                        <td colspan="3" class="label"><html:radio  property="w_can" value ="1" >Yes</html:radio><html:radio  property="w_can" value ="0" >No</html:radio></td>

                </table>
            </div>

            <div id="visual" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="0" cellpadding="5" border="1" class="innerTable1" width="85%">
                    
                    <tr>
                        <td class="label">SE-can perform work by seeing</td>
                        <td class="label"><html:radio  property="se_can" value ="1" >Yes</html:radio><html:radio  property="se_can" value ="0" >No</html:radio></td>
                    </tr>

                </table>
            </div>
            <div id="hearing" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="0" cellpadding="5" border="1" class="innerTable1" width="85%">
                  
                    <tr>
                        <td class="label" width="32%">H-can perform work by hearing/speaking</td>
                        <td  class="label"><html:radio  property="h_can" value ="1" >Yes</html:radio><html:radio  property="h_can" value ="0" >No</html:radio></td>
                    </tr>
                </table>
            </div>
            <div id="commonCondition" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="0" cellpadding="5" border="1" class="innerTable1" width="85%">
                    <tr>
                        <td class="label" width="32%">RW-can perform work by reading and writing</td>
                        <td class="label"><html:radio  property="rw_can" value ="1" >Yes</html:radio><html:radio  property="rw_can" value ="0" >No</html:radio></td>
                    </tr>
                </table>
            </div>
     
            <table align="center" id="hideTB" >
                <tr align="center">
                    <td colspan="4" align="center">
                        <html:submit value="Update" styleId="subDisablButton"  styleClass="button" />&nbsp;&nbsp;

                        <html:reset value="Reset" styleClass="button"/></td> </tr>
            </table>
        </html:form>
    </body>
</html:html>

