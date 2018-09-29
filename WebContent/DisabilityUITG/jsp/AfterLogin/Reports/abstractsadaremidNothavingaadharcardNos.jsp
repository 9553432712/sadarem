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
<%@page import="org.bf.disability.dto.ReportDTO" %>
<%@page import="java.util.ArrayList,com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility" %>


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

ArrayList shgabstract = (ArrayList) request.getAttribute("shgAbstract");
%>


<html:html>
<%try{ %>
    <head>
        <script language="JavaScript" src="./DisabilityUITG/js/Ajax.js"></script>
        
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
         <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
          <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>

<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>
        <script  type="text/javascript">
       
            function getValidation(){
                var d  = document.forms[0];
               /* if(d.district_id.value=="0"){
                    alert("Please Select District");
                    d.district_id.focus();
                    return false;
                }else if(d.mandal_id.value=="0"){
                    alert("Please Select Mandal");
                    d.mandal_id.focus();
                    return false;
                }
                else{ */
                    d.mode.value='loadabstractsadaremnothavingaadhar';
                    d.submit();
                    
              /*   } */
                // document.getElementById("print").style.display="none";
            }

            <%


            String SelFromDate			= CommonUtility.checkNullObj(request.getAttribute("SelFromDate"));
            String SelToDate			= CommonUtility.checkNullObj(request.getAttribute("SelToDate"));%>

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
                x.open("GET",url,true)
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

           <%--  function fn_excel()
            {
            	
            	 var disid = '<%=dis%>';
            	var mandalid = '<%=mandal_id%>';
            	var villageid = '<%=village_id%>';
            	var distname = '<%=distName%>';
            	var mandname = '<%=mandalName%>';
            	var villagename='<%=villageName%>';
            	
            	
            	 
            	document.forms[0].action="<%=request.getContextPath()%>/sadaremidNothavingaadhracardNos.xls?mode=excelWritingabstract&district_name="+distname+"&mandal_name="+mandname+"&village_name="+villagename+"&districtid="+disid+"&mandalid="+mandalid+"&villageid="+villageid;
            	document.forms[0].submit();
            	
            }  --%>
        </script>
    </head>
    <body onload="OnBodyLoad(2,3);">


        <html:form action="/sadaremidNothavingaadhracardNos.do">
            <html:hidden property="mode"/>

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
					  <tr>
					    <th class="hd_gd" align="center" valign="middle">R1.3 :Abstract Report of SADAREM ID Not Tagged To AADHARCARD No</th>
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
										     <td valign="middle" class="label" width="8%">District </td>
                    <td align="left" valign="middle">
                        <html:select styleId="1" property="district_id" onchange="districtList(this.value),getDetails();" style="height:25px;font-size:11px;">
                            <html:option  value="-1">--ALL--</html:option>
                            <html:optionsCollection   property="districtlist" label="district_name" value="district_id"  />
                        </html:select>
                    </td>
					 <td valign="middle" class="label" width="8%" > Mandal </td>
                    <td align="left" valign="middle" >
                        <html:select styleId="2" property="mandal_id" style="width:150px;height:25px;font-size:11px;" onchange="mandalList(this.value),getDetails();" >
                            <html:option  value="-1" >--ALL--</html:option>
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
                                 <a href="sadaremidNothavingaadhracardNos.xls?mode=excelWritingabstract&district_name=<%=distName %>&mandal_name=<%=mandalName %>&village_name=<%=villageName %>&districtid=<%=dis %>&mandalid=<%=mandal_id %>&villageid=<%=village_id %>" target="_blank">
										Export<img height="30" width="30" src="<%=request.getContextPath()%>/images/excel.jpg" border="0" align="right" title="Export Excel"/>
									  </a>
                               

                            </td>
                        </tr></table>
                    </logic:iterate>
               
               
               
               

                
                
     <div id="login">
	    				       
	    		<table width="40%" <%
					  if(shgabstract.size()>14)
					  {	%> border="0"
					  <%}else{
						  %> style="FONT-FAMILY: verdana; FONT-SIZE:12;"<% }%> 
						    align="center" cellpadding="0" cellspacing="0" style="overflow: none;" >
	    		
	    		
	    		<%
					  if(shgabstract.size()>14)
					  {	%>
	    		<tbody>
                 <tr>
                <td id="tdGridContent" height="450" valign="top" width="100%" align="middle">
		        <table width="50%" align="center">
				   <tbody>
				   <tr>
				   <td style="display:none" id="td_grid"  class="scrollme" valign="top" width="100%"  align="middle">
				   <div>
			       <table border="0" align="center" cellspacing="0" rules="all" class="gridStyle"  id="ctl00_dtg_Grid"  class="gd_row" style="BORDER-COLLAPSE: collapse"  width="60%">
				   <tbody>
                <%} %>
  				<tr class="gridHdrStyle">
  				 	
                            <th class="hd_gd" width="5%">S.No</th>
                            <th class="hd_gd" width="60%">
                           <%  if (dis.equals("-1") ) {
                        out.write("District");
                    } else if(!dis.equals("-1") && mandal_id.equals("-1")){
                    	 out.write("Mandal");

                    }else if(!dis.equals("-1") && !mandal_id.equals("-1") && village_id.equals("-1")){
                    	 out.write("Village");	
                    }
                    else if(!dis.equals("-1") && !mandal_id.equals("-1") && !village_id.equals("-1")){
                   	 out.write("Habitation");	
                   }
                    %>
                            </th>
                            <th class="hd_gd"  width="20%">Count of SADAREM Id's <br>Not tagged to Aadhar Number</th>
                            
                        </tr>
                        <tr  class="gridHdrStyle"  >
			  				 	<th class="hd_gd" align="center" valign="middle" style="height: 40px !important;">1</th>
			  				 	<th class="hd_gd" align="center" valign="middle" style="height: 40px !important;">2</th>
			  				 	<th class="hd_gd" align="center" valign="middle" style="height: 40px !important;">3</th></tr>                        
                           
                            <tr>
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
                            <tr>
                                  <% if(count.intValue()+1==shgabstract.size()){%>
                        	 <td  class="hd_gd" style="background-color: #628BB5; color: #ffffff" >
                              </td>
                               <%  }else{%>
  	                              <td <% if(shgabstract.size()<=14 && count.intValue() == shgabstract.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> class="<%=classStyle%>" align="center" valign="middle">
                                    ${row.sno}     
                             <%} %>
                               
                                  </td>
                                
                               
                                   
                               
                         <% if(count.intValue()+1==shgabstract.size()){%>
                        	 <td class="hd_gd"  >
                              <%  out.write("Total");%></td>
                               <%  }else{%>
  	                              <td <% if(shgabstract.size()<=14 && count.intValue() == shgabstract.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> class="<%=classStyle%>">
                                    ${row.location_name}     
                             <%} %>
                               
                                  </td>
                                  
                                  <% if(count.intValue()+1==shgabstract.size()){%>
                        	 <td class="hd_gd"  align="center" text-align="center" colspan='2'>
                              ${row.total}</td>
                               <%  }else{%>
  	                              <td <% if(shgabstract.size()<=14 && count.intValue() == shgabstract.size()-1) {%>style="border-bottom : #234466 1px solid"<%} else if(shgabstract.size()<=14){ %>style="border-right : #234466 1px solid;"<%} %> class="<%=classStyle%>" align="center">
                                    ${row.total}     
                             <%} %>
                               
                                  </td>
                                  
                             </tr>
                        </logic:iterate>
					  
					  
			<%
					  if(shgabstract.size()>17)
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
    
    <%}catch(Exception e){e.printStackTrace();} %>
</html:html>
