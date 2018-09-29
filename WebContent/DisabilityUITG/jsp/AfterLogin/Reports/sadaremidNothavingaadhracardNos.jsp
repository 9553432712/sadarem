<%--
 Document   : sHGEligibleReport
    Created on : 5 Mar, 2014, 3:34:36 PM
    Author     : 728056
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.*"%>
<%@page session="true"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.ReportDTO;" %>
<style>
 .classstyle2 {border-right : #234466 1px solid;}
 
</style>
<%
            int i = 1;
            String distName = null,mandalName=null,villageName=null;
            String mandal_id = null;
            String village_id = null;
            
            String dis = null;


            if (request.getAttribute("district_id") != null) {
                dis = (String) request.getAttribute("district_id");

            }
            if (request.getAttribute("mandal_id") != null) {
                mandal_id = (String) request.getAttribute("mandal_id");


            }
            if (request.getAttribute("village_id") != null) {
                village_id = (String) request.getAttribute("village_id");

            }



            if (request.getAttribute("distName") != null) {
                distName = (String) request.getAttribute("distName");
             }
            if (request.getAttribute("mandalName") != null) {
            	mandalName = (String) request.getAttribute("mandalName");
             }
              if (request.getAttribute("villageName") != null) {
            	 villageName = (String) request.getAttribute("villageName");
              }

ArrayList shgabstract = (ArrayList) request.getAttribute("shgAbstract"); %>




<html:html>
    <head>
    <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
        <script language="JavaScript" src="./DisabilityUITG/js/Ajax.js"></script>
        
         <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>

<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>
        
        <script>
            function getValidation(){
                var d  = document.forms[0];
                if(d.district_id.value=="0"){
                    alert("Please Select District");
                    d.district_id.focus();
                    return false;
                }else if(d.mandal_id.value=="0"){
                    alert("Please Select Mandal");
                    d.mandal_id.focus();
                    return false;
                }
                else{
                    d.mode.value='unspecified';
                    d.submit();
                    
                }
                // document.getElementById("print").style.display="none";
            }

          

            function removeLists(start,end){
                for(k=start;k<=end;k++)
                {
                    var x1=document.getElementById(k).length;
                    for(i=x1;i>1;i--)
                        document.getElementById(k).options[i]=null;
                    document.getElementById(k).value="";
                }
            }
        </script>

        <script>


            function districtList(){
           

                var disid =  document.forms[0].district_id.value;
                if(disid == ""){
                    removeLists(2,3);
                }else{
                    createMandalObject();
                }
            }

            function  createMandalObject()
            {

                x=GetXmlHttpObject()
                x.onreadystatechange=getMandalDetails;
                var distid= document.forms[0].district_id.value;
                var url="getTerritory.do?parameter=getNewTerritoryList&districtid="+distid+"&territory=2";
                x.open("GET",url,true);
                x.send();
            }

            function getMandalDetails()
            {

                var rs1,rs2;
                removealls("mandal_id");
                removealls("village_id");
                document.forms[0].mandal_id.value="0";

                if(x.readyState==4 || x.readyState=="complete")
                {
                    var m=x.responseXML.documentElement;
                    var z=0;
                    var counts=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                    m.getElementsByTagName("id")[z].childNodes[0].nodeValues;
                    z=1;
                    while(z<=counts)
                    {
                        rs1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                        rs2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
                        addoption1(rs1,rs2,"mandal_id");
                        z++;
                    }
                }
            }

            function mandalList(){
 
                        

                var manid= document.forms[0].mandal_id.value;
                if(manid==""){
                    removeLists(4,6);
                }else{

                    createVillageObject();
                }

            }



            function  createVillageObject()
            {

                var distid;
                x=GetXmlHttpObject()
                x.onreadystatechange=getVillageDetails;
                distid= document.forms[0].district_id.value;
                if(distid == ""){
                    distid = document.getElementById("districtid").value;
                }
                var mandid= document.forms[0].mandal_id.value; 

                var url="getTerritory.do?parameter=getNewTerritoryList&districtid="+distid+"&mandalid="+mandid+"&territory=5";

                x.open("GET",url,true)
                x.send();
            }
            function getVillageDetails()
            {
                var rs1,rs2;
                removealls("village_id");
                document.forms[0].village_id.value="0";

                if(x.readyState==4 || x.readyState=="complete")
                {
                    var m=x.responseXML.documentElement;
                    var z=0;
                    var counts=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                    m.getElementsByTagName("id")[z].childNodes[0].nodeValues;
                    z=1;
                    while(z<=counts)
                    {
                        rs1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                        rs2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
                        addoption1(rs1,rs2,"village_id");
                        z++;
                    }
                }

            }

            function addoption1(result1,result2,name)
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
                    }else if(name=="village_id") {
                        document.forms[0].village_id.appendChild(opt,null);
                    }

                }

            }

            function removealls(name)
            {
                if(name=="mandal_id") {
                    var x1=document.forms[0].mandal_id.options.length;
                }
                else if(name=="district_id") {
                    var x1=document.forms[0].district_id.options.length;
                }else if(name=="village_id") {
                    var x1=document.forms[0].village_id.options.length;
                }

                for(i=x1;i>0;i--) {
                    if(name=="mandal_id") {
                        document.forms[0].mandal_id.options[i]=null;
                    } else if(name=="district_id") {
                        document.forms[0].district_id.options[i]=null;
                    }else if(name=="village_id") {
                        document.forms[0].village_id.options[i]=null;
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

                     

            function getDetails(){
                document.getElementById("login").style.display="none";
                document.getElementById("sadaremdata").style.display="none";
                 document.getElementById("print").style.display="none";
            }


        </script>
    </head>
    <body onload="OnBodyLoad(2,3);">


        <html:form action="/sadaremidNothavingaadhracardNos.do">
            <html:hidden property="mode"/>

	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
					  <tr>
					    <th class="hd_gd" align="center" valign="middle">R1.3 : SADAREM ID Not Tagged To AADHARCARD No</th>
					  </tr>
	  				<tr>
  					<td>
  							<form name="ExpiredSadaremdataform"  method="post">
  							<input type="hidden" name="distid" id="distid" value="" readonly="readonly">
  							<input type="hidden" name="mandalid" id="mandalid" value="" readonly="readonly">
  							<input type="hidden" name="fromdate" id="fromdate" value="" readonly="readonly">
  							<input type="hidden" name="todate" id="todate" value="" readonly="readonly">
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
									        <tr height="30">
										    <td valign="middle" class="label" width="8%">District<font color="red"><b>*</b></font></td>
                    <td align="left" valign="middle">
                        <html:select styleId="1" property="district_id" onchange="districtList(this.value),getDetails();" style="height:25px;font-size:11px;">
                            <html:option  value="0">--SELECT--</html:option>
                            <html:optionsCollection   property="districtlist" label="district_name" value="district_id"  />
                        </html:select>
                    </td>
										      <td valign="middle" class="label" width="8%" >Mandal<font color="red"><b>*</b></font></td>
                    <td align="left" valign="middle" >
                        <html:select styleId="2" property="mandal_id" style="width:150px;height:25px;font-size:11px;" onchange="mandalList(this.value),getDetails();" >
                            <html:option  value="0" >--SELECT--</html:option>
                            <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"/>
                        </html:select>
                    </td>
										<td valign="middle" class="label" width="8%">Village</td>
                    <td align="left" valign="middle" >
                        <html:select styleId="4" property="village_id"  style="height:25px;font-size:11px;" onchange="getDetails();">
                            <html:option  value="-1">--ALL--</html:option>
                            <html:optionsCollection property="villagelist" label="village_name" value="village_id"/>
                        </html:select>
                    </td>
                     <th colspan="6" align="center"><html:button property="but" onclick="getValidation();" value="Submit"/>
                    </th>
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
					    	
						</form>
  					</td>
  				</tr>
  				</table>
          <br/>
            <logic:notEmpty name="shgAbstract">


                <logic:iterate name="shgAbstract" id="row" scope="request" length="1" >

                    <table border="0" cellspacing="1" cellpadding="0" width="90%" align="center" id="sadaremdata">
                        <tr> 
                            <td  colspan="12" style="text-align: right">
                                <a href="sadaremidNothavingaadhracardNos.xls?mode=excelWriting&districtid=${row.districtId}&district_name=<%=distName%>&mandalid=${row.mandalId}&mandal_name=<%=mandalName%>&villageid=${row.villageid}&village_name=<%=villageName %>" target="_blank">
                                    Export<img src="DisabilityUITG/images/excel.jpg" height="20" width="20"/></a> &nbsp; &nbsp; &nbsp;

                            

                            </td>
                        </tr></table>
                    </logic:iterate>



 				
 				    <div id="login" >
	    				       
	    		<table width="70%"  <% if(shgabstract.size()>14)
					  {	%> border="0"
					  <%}else{
						  %> style="FONT-FAMILY: verdana;  FONT-SIZE:12;"<% }%> align="center" cellpadding="0" cellspacing="0" style="overflow: none;" >
	    		
	    		<%String classstyle2="";
					  if(shgabstract.size()>14)
					  { 	%>
	    		
	    		<tbody>
                 <tr>
                <td id="tdGridContent" height="450" valign="top" width="100%" align="middle">
		        <table width="90%" align="center">
				   <tbody>
				   <tr>
				   <td style="display:none" id="td_grid"  class="scrollme" valign="top" width="100%"  align="middle">
				   <div>
			       <table border="1" align="center" cellspacing="0" rules="all" class="gridStyle"  id="ctl00_dtg_Grid" style="BORDER-COLLAPSE: collapse"  width="100%">
				   <tbody>
                <%} else{  classstyle2 = "classstyle2";} %>
  				<tr class="gridHdrStyle">
  				 	 <th class="hd_gd" style="background-color: #628BB5; color: #ffffff" >S.No</th>
                            <th class="hd_gd">Date Of Issue</th>
                            <th class="hd_gd">SADAREM ID</th>
                            <th class="hd_gd">Person Name</th>
                            <th class="hd_gd">Relation Name</th>
                            <th class="hd_gd">Gender</th>
                            <th class="hd_gd">Age</th>
                            <th class="hd_gd">Phone No</th>
                            <th class="hd_gd">House Number</th>
                            <th class="hd_gd">Village</th>
                            <th class="hd_gd" style="background-color: #628BB5; color: #ffffff" >Mandal</th>
                            <th class="hd_gd" style="border-right : #234466 1px solid;" >District</th>
                            
                            
                        </tr>
                        <tr  class="gridHdrStyle" style="background-color: #628BB5">
	  				 	<th class="hd_gd" style="height: 50px !important;">1</th>
	  				 	<th class="hd_gd" style="height: 50px !important;">2</th>
	  				 	<th class="hd_gd" style="height: 50px !important;">3</th>
	  				 	<th class="hd_gd" style="height: 50px !important;">4</th>
	  				 	<th class="hd_gd" style="height: 50px !important;">5</th>
	  				 	<th class="hd_gd" style="height: 50px !important;">6</th>
	  				 	<th class="hd_gd" style="height: 50px !important;">7</th>
	  				 	<th class="hd_gd" style="height: 50px !important;">8</th>
	  				 	<th class="hd_gd" style="height: 50px !important;">9</th>
	  				 	<th class="hd_gd" style="height: 50px !important;">10</th>
	  				 	<th class="hd_gd" style="height: 50px !important;">11</th>
	  				 	<th class="hd_gd" style="height: 50px !important;">12</th>
  				 
  				 	
  				</tr> 
  					<%  String classStyle =""; %>
  				 <logic:iterate name="shgAbstract" id="row" scope="request" indexId="count">
  				   <%
						
								  if(count.intValue()%2==0)
			        			  {
			        			  	classStyle="secondrow";
			        			  }
			        			  else
			        			  {
				        			  	classStyle="firstrow";
			        			  }
  				   
  				                  
  				   
						
					  %>
                            <tr  >
                                <td <% if(shgabstract.size()<=14 && count.intValue() == shgabstract.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> class="<%=classStyle%>" align="center" valign="middle">${row.sno}</td>
                                 <td <% if(shgabstract.size()<=14 && count.intValue() == shgabstract.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> class="<%=classStyle%>"  width="15%">
                                    ${row.Date_of_Issue}


                                </td>
                                <td <% if(shgabstract.size()<=14 && count.intValue() == shgabstract.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> class="<%=classStyle%>">

                                    ${row.SADAREMCODE}
                                </td>
                                <td <% if(shgabstract.size()<=14 && count.intValue() == shgabstract.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> class="<%=classStyle%>">${row.PERSONNAME}</td>
                                <td <% if(shgabstract.size()<=14 && count.intValue() == shgabstract.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> class="<%=classStyle%>" >

                                    ${row.RELATIONNAME}

                                </td>
                                <td <% if(shgabstract.size()<=14 && count.intValue() == shgabstract.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> class="<%=classStyle%>">

                                    ${row.GENDER}
                                </td>
                                <td <% if(shgabstract.size()<=14 && count.intValue() == shgabstract.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> class="<%=classStyle%>" align="center" valign="middle">

                                    ${row.age}
                                </td>
                               <td <% if(shgabstract.size()<=14 && count.intValue() == shgabstract.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> class="<%=classStyle%>" align="center" valign="middle">
                                    ${row.phone_no}


                                </td>
                               <td <% if(shgabstract.size()<=14 && count.intValue() == shgabstract.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> class="<%=classStyle%>">
                                    ${row.house_number}


                                </td>

                                <td <% if(shgabstract.size()<=14 && count.intValue() == shgabstract.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> class="<%=classStyle%>">

                                    ${row.village_name}
                                </td>

                               
                                <td <% if(shgabstract.size()<=14 && count.intValue() == shgabstract.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> class="<%=classStyle%>">
                                    ${row.mandal_name}


                                </td>
                                <td <% if(shgabstract.size()<=14 && count.intValue() == shgabstract.size()-1) {%>style="border-right : #234466 1px solid;border-bottom : #234466 1px solid"<%}else if(shgabstract.size()<=14){ %>style="border-right : #234466 1px solid;"<%} %> class="<%=classStyle%>" >
                                    ${row.district_name}


                                </td>
                                                                
                            </tr>
                        </logic:iterate>
					  
					  
						<%
					  if(shgabstract.size()>14)
					  {	%>  
	</tbody>
	</table>
	</div>
	</td>
	</tr>
	</tbody>
	</table>
    </td>
   </tr>
 </tbody>

 <%} %>
</table>   </div>           
    

                <br><br>
             

            </logic:notEmpty>

                 <logic:present name="msg">
                <font color="red">
                    ${msg}</font>
                </logic:present>
            </html:form>
    </body>
</html:html>
