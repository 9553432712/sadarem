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

<%
            int i = 1;
            String disName = null;
            String mandal_id = null;
            String village_id = null;
            String panchayat_id = null;
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
            if (request.getAttribute("panchayat_id") != null) {
                panchayat_id = (String) request.getAttribute("panchayat_id");

            }


            if (request.getAttribute("districtName") != null) {
                disName = (String) request.getAttribute("districtName");

            }

            ArrayList shgabstract = (ArrayList) request.getAttribute("shgAbstract");


%>
<html:html>
    <head>
        <script language="JavaScript" src="./DisabilityUITG/js/Ajax.js"></script>
        <script>

            function openWindow(requestId,flag) {
               
                window.open("./sHGEligibleReport.do?mode=eligiblePersonalDetails&requestId="+requestId+"&district_id=<%=dis%>&mandal_id=<%=mandal_id%>&panchayat_id=<%=panchayat_id%>&village_id=<%=village_id%>&districtName=<%=request.getAttribute("districtName")%>&mandalName=<%=request.getAttribute("mandalName")%>&panchayathName=<%=request.getAttribute("panchayathName")%>&VillagelName=<%=request.getAttribute("VillagelName")%>&flag="+flag+"','_blank','toolbar=no, scrollbars=yes, resizable=yes,width=1100, height=800");
            }
            function getDetails() {
                if(document.forms[0].elements['district_id'].value==""||document.forms[0].elements['district_id'].value=="0") {
                    alert("Please Select District");
                    document.forms[0].elements['district_id'].value.focus();
                    return false;
                }
                else{
                    var slcBx1 = document.getElementById("1");
                    var slcBx2 = document.getElementById("2");
                    var slcBx3 = document.getElementById("3");
                    var slcBx4 = document.getElementById("4");
                    document.getElementById("districtName").value = slcBx1.options[slcBx1.selectedIndex].text;
                    document.getElementById("mandalName").value = slcBx2.options[slcBx2.selectedIndex].text;
                    document.getElementById("panchayathName").value = slcBx3.options[slcBx3.selectedIndex].text;
                    document.getElementById("VillagelName").value = slcBx4.options[slcBx4.selectedIndex].text;
                    
                    document.forms[0].mode.value="getSHGEligible";
                    document.forms[0].submit();

                }
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
                removeall("district_id");

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
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&territory=2";
                x.open("GET",url,true)
                x.send();
            }

            function getMandalDetails()
            {

                var rs1,rs2;
                removeall("mandal_id");
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
                        addoption(rs1,rs2,"mandal_id");
                        z++;
                    }
                }
            }

            function mandalList(){

                var manid= document.forms[0].mandal_id.value;
                if(manid==""){
                    removeLists(4,6);
                }else{

                    createPanchayatObject();
                }
            }
            function panchList(){

                var vilid= document.forms[0].panchayat_id.value;
                if(vilid==""){
                    removeLists(4,6);
                }else{

                    createVillageObject();
                }
            }
            function  createPanchayatObject()
            {
                var distid;
                x=GetXmlHttpObject()
                x.onreadystatechange=getPanchayatDetails;
                distid= document.forms[0].district_id.value;
                if(distid == ""){
                    distid = document.getElementById("districtid").value;
                }
                var mandid= document.forms[0].mandal_id.value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&mandalid="+mandid+"&territory=4";
                x.open("GET",url,true)
                x.send();
            }
            function getPanchayatDetails()
            {
                var rs1,rs2;
                removeall("panchayat_id");
                document.forms[0].panchayat_id.value="0";

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
                        addoption(rs1,rs2,"panchayat_id");
                        z++;
                    }
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
                var pancid= document.forms[0].panchayat_id.value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&mandalid="+mandid+"&panchayatid="+pancid+"&territory=7";
                x.open("GET",url,true)
                x.send();
            }
            function getVillageDetails()
            {
                var rs1,rs2;
                removeall("village_id");
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
                        addoption(rs1,rs2,"village_id");
                        z++;
                    }
                }

            } function addoption(result1,result2,name)
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
                    }else  if(name=="panchayat_id") {
                        document.forms[0].panchayat_id.appendChild(opt,null);
                    }else if(name=="village_id") {
                        var x1=document.forms[0].village_id.options.length;
                    }

                }

            }

            function removeall(name)
            {
                if(name=="mandal_id") {
                    var x1=document.forms[0].mandal_id.options.length;
                }
                else if(name=="district_id") {
                    var x1=document.forms[0].district_id.options.length;
                }else if(name=="panchayat_id") {
                    var x1=document.forms[0].panchayat_id.options.length;
                }else if(name=="village_id") {
                    var x1=document.forms[0].village_id.options.length;
                }

                for(i=x1;i>0;i--) {
                    if(name=="mandal_id") {
                        document.forms[0].mandal_id.options[i]=null;
                    } else if(name=="district_id") {
                        document.forms[0].district_id.options[i]=null;
                    }else if(name=="panchayat_id") {
                        document.forms[0].panchayat_id.options[i]=null;
                    }else if(name=="village_id") {
                        var x1=document.forms[0].village_id.options.length;
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

        </script>
        <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>




    </head>
    <body>

  
        <html:form action="/sHGEligibleReport.do">
            <html:hidden property="mode"/>
            <input type="hidden" name="districtName" id="districtName"/>
            <input type="hidden" name="mandalName" id="mandalName"/>
            <input type="hidden" name="panchayathName" id="panchayathName"/>
            <input type="hidden" name="VillagelName" id="VillagelName"/>

                  

              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
					  <tr>
					    <th class="hd_gd" align="center" valign="middle">R.7.1 SHG's  Eligible Report </th>
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
                                        <td valign="middle" class="label" width="8%">District<font color="red"><b>*</b></font></td>
                                        <td align="left" valign="middle">
                                            <html:select styleId="1" property="district_id" onchange="districtList(this.value)" style="height:25px;font-size:11px;">
                                                <html:option  value="0">--SELECT--</html:option>
                                                <html:optionsCollection   property="districtlist" label="district_name" value="district_id"  />
                                            </html:select>
                                        </td>

                                        <td valign="middle" class="label" width="8%">Mandal</td>
                                        <td align="left" valign="middle">
                                            <html:select styleId="2" property="mandal_id" style="width:150px;height:25px;font-size:11px;" onchange="mandalList(this.value)" >
                                                <html:option  value="0" >ALL</html:option>
                                                <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"/>
                                            </html:select>
                                        </td>
                                        <th rowspan="2" align="left"><html:button property="but" onclick="getDetails();" value="Submit"/>
                                        </th>
                                    </tr><tr>
                                   
                                        <td valign="middle" class="label" width="8%">Panchayat</td>
                                        <td align="left" valign="middle">
                                            <html:select styleId="3" property="panchayat_id" style="width:150px;height:25px;font-size:11px;" onchange="panchList(this.value);" >
                                                <html:option  value="0" >ALL</html:option>
                                                <html:optionsCollection   property="panchayatlist" label="panchayat_name" value="panchayat_id"/>
                                            </html:select>
                                        </td>
                                        <td   valign="middle" class="label" width="8%">Village</td>
                                        <td  align="left" valign="middle">
                                            <html:select styleId="4" property="village_id" style="height:25px;font-size:11px;">
                                                <html:option  value="0">ALL</html:option>
                                                <html:optionsCollection property="villagelist" label="village_name" value="village_id"/>
                                            </html:select>
                                        </td>

                                   
                                        
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
  				</table>      
  				 <logic:notEmpty name="shgAbstract">
  				<table width="90%">
				                   <tr>
						             <td  align="right" width="89%" >
									    <a href="sHGEligibleReport.xls?mode=getSHGEligible&page=Excel&district_id=<%=dis%>&mandal_id=<%=mandal_id%>&panchayat_id=<%=panchayat_id%>&village_id=<%=village_id%>&districtName=<%=request.getAttribute("districtName")%>&mandalName=<%=request.getAttribute("mandalName")%>&panchayathName=<%=request.getAttribute("panchayathName")%>&VillagelName=<%=request.getAttribute("VillagelName")%>">
                            Excel <img src="DisabilityUITG/images/excel.jpg"
                                                 height="30" width="30"/></a>
						     	     </td> 
						            </tr>
						       </table>  
                     
                    <br/>
                   
                       

                                    <table  align="center" cellspacing="1"  cellpadding="0" cellpadding="4"  width="70%">
                                        <tr><th class="hd_gd"  align="center" colspan="11">SHG's Eligible Report</th></tr>
                                        <tr>
                                            <th class="hd_gd" >
                                                S.No
                                            </th>


                                            <% if (mandal_id.equals("0")) {%>
                                            <th class="hd_gd" align="center">Mandal</th>
                                            <% } else if (panchayat_id.equals("0")) {%>
                                            <th class="hd_gd" align="center">Panchayat</th>
                                            <%} else if (village_id.equals("0")) {%>
                                            <th class="hd_gd" align="center">Village</th>
                                            <%} else {%>
                                            <th class="hd_gd" align="center">Habitation</th>
                                            <%}%>

                                            <th class="hd_gd" >
                                                Eligible PwDs
                                            </th>
                                        </tr>
                                        <tr>
                                           <th class="hd_gd" >
                                                1
                                            </th>
                                               <th class="hd_gd" >
                                                2
                                            </th>
                                               <th class="hd_gd" >
                                                3
                                            </th></tr>
                                        <logic:empty name="shgAbstract">
                                            <tr>
                                                <td align="left" colspan="8">
                                                    <font color="red" size="3">Data Not Available</font>
                                                </td>
                                            </tr>
                                        </logic:empty>
<%String  classStyle="";%>
                                        <logic:iterate id="row" name="shgAbstract" indexId="count">
                                        
                                      <%   if(count.intValue()%2==0)
                                        {
			        			  	classStyle="secondrow";
			        			  }
			        			  else
			        			  {
				        			  	classStyle="firstrow";
			        			  }
                                      %>
                                        
                                        
                                            <tr>
                                                <td class="<%=classStyle%>" <% if(count.intValue() == shgabstract.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %>align="center" class="formaltbg">
                                                    <%=i++%>.
                                                </td>

                                                <td class="<%=classStyle%>" <% if(count.intValue() == shgabstract.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %>align="left" class="formaltbg">${row.name} </td>

                                                <td class="<%=classStyle%>" <% if(count.intValue() == shgabstract.size()-1) {%>style="border-bottom : #234466 1px solid;border-right:#234466 1px solid;"<%} %> align="center" style="border-right:#234466 1px solid;">
                                                    <a href="javascript:void(0)" onclick="openWindow('${row.id}',0);"> ${row.count}</a>
                                                </td>

                                            </tr>
                                        </logic:iterate>
                                    </table>
                                
                        <br/>
                       <%--  <a href="sHGEligibleReport.xls?mode=getSHGEligible&page=Excel&district_id=<%=dis%>&mandal_id=<%=mandal_id%>&panchayat_id=<%=panchayat_id%>&village_id=<%=village_id%>&districtName=<%=request.getAttribute("districtName")%>&mandalName=<%=request.getAttribute("mandalName")%>&panchayathName=<%=request.getAttribute("panchayathName")%>&VillagelName=<%=request.getAttribute("VillagelName")%>">
                            Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                 height="25" width="25"/></a> --%>
                        </logic:notEmpty>
                        <logic:present name="shgAbstract">
                            <logic:empty name="shgAbstract">

                            <center style="color: red;">
                                No SHG Eligible Details Found
                            </center>
                        </logic:empty>
                    </logic:present>
      

    </html:form>
</body>
</html:html>
