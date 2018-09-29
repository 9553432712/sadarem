<%-- 
    Document   : NotComeToSadaremCamp
    Created on : Jan 25, 2011, 5:43:06 PM
    Author     : 509865
--%>




<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,java.text.*"%>
<%@page session="true"%>

 
<html:html>
    <head>
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/Ajax.js"></script>
 <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="./DisabilityUITG/js/date-picker"></script>


        <script >

            function createDistrictObject()
            {
                x=GetXmlHttpObject()
                x.onreadystatechange=getDistrictDetails;
                var url="getTerritory.do?parameter=getTerritoryList&territory=1";
                x.open("Get", url, true);
                x.send();
            }

            function getDistrictDetails()
            {
                var res1,res2;

                if(x.readyState==4 || x.readyState=="complete")
                {
                    var m=x.responseXML.documentElement;
                    var z=0;
                    var countss=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                    m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
                    z=1;
                    while(z<=countss)
                    {
                        res1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                        res2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
                        addoption(res1,res2,"district_id");
                        z++;
                    }
                }

            }



            function addoption(result1,result2,name)
            {
                var opt=document.createElement("OPTION");
                opt.text=result1;
                opt.value=result2;
                try{
                    document.getElementById(name).add(opt);
                }catch(ex){
                    if(name=="district_id") {
                        document.forms[0].district_id.appendChild(opt,null);
                    }else  if(name=="mandal_id") {
                        document.forms[0].mandal_id.appendChild(opt,null);
                    }else  if(name=="village_id") {
                        document.forms[0].village_id.appendChild(opt,null);
                    }else  if(name=="panchayat_id") {
                        document.forms[0].panchayat_id.appendChild(opt,null);
                    }

                }
            }


            function GetXmlHttpObject()
            {
                var objXmlHttp=null
                if(window.XMLHttpRequest)
                {
                    objXmlHttp=new XMLHttpRequest();
                }
                else if(window.ActiveXObject)
                {
                    objXmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
                }
                return objXmlHttp;
            }
            function validate_required(field,alerttxt)
            {

                with (field)
                {
                    if (value==null || value=="")
                    {
                        alert(alerttxt);
                        return true
                    }
                    else
                    {
                        return false
                    }
                }
            }

            function validate_form(thisform)
            {
                with (thisform)
                {
                    if (validate_required(district_id, "Select District") == true)
                    {
                        district_id.focus();
                        return false
                    }
                    if (validate_required(phase, "Select Phase") == true)
                    {
                        phase.focus();
                        return false
                    }
                }
                selectedNames();
            }

            function selectedNames()
            {
                var slcBx1 = document.getElementById("1");
                document.getElementById("districtName").value = slcBx1.options[slcBx1.selectedIndex].text;
                var slcBx2 = document.getElementById("2");
                document.getElementById("pahseName").value = slcBx2.options[slcBx2.selectedIndex].text;
            }




        </script>
    </head>

    <body onload="createDistrictObject()">


        <html:form action="notComeToCamp.do?getNotComeToCamp=getNotComeToCamp&reportCategory=1" method="post" onsubmit="return validate_form(this)">
     
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
                  <input type="hidden" name="districtName"/>
                <input type="hidden" name="pahseName"/>
					  <tr>
					    <th class="hd_gd" align="center" valign="middle">R2.1 : SADAREM Camp Not Attended PWD's</th>
					  </tr>
	  				<tr>
  					<td>
  							
	 <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
		 <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_lft_top.png" width="16" height="16" /></td>
		 <td width="100%" align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_top_bg.png); background-repeat:repeat-x;"></td>
		<td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_rgt_top.png" width="16" height="16" /></td>
	 </tr>
			 <tr>
				 <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_lft_bg.png); background-repeat:repeat-y;">&nbsp;</td>
			     <td align="left" valign="top" >
	<table  cellspacing="0" width="100%" border="0px">
			 <tr>
                  <td >District<font color="red"><b>*</b></font></td>
                  <td >
                        <html:select styleId="1" property="district_id" style="height:25px;">
                         <html:option  value="">--Select--</html:option>
                        <html:optionsCollection   property="districtlist" label="district_name" value="district_id"  />
                        </html:select>
                   </td>
                      <td >Phase<font color="red"><b>*</b></font></td>
                      <td >
                         <html:select styleId="2" property="phase" style="height:25px;">
                         <html:option value="ALL">All</html:option>
                         </html:select>
                     </td>
                          <td colspan="4" align="center"><html:submit property="Submit" value="Submit" styleClass="button"/></td>
                             </tr>
	</table> 
		      </td>
				 <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_rgt_bg.png); background-repeat:repeat-y">&nbsp;</td>
				</tr>
				<tr>
				<td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_lft_bom.png" width="16" height="19" /></td>
				 <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_bom_bg.png); background-repeat:repeat-x;">&nbsp;</td>
				<td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_rgt_bom.png" width="16" height="19" /></td>
				</tr>
				</table>
			
  					</td>
  				</tr>
  				<%
                                String msg = (String) request.getAttribute("msg");

                    %>
                    <% if (msg != null) {%><table align="center"><tr><td><%=msg%></td></tr></table> <% }%>
  				</table> 
        </html:form>
    </body>
</html:html>
