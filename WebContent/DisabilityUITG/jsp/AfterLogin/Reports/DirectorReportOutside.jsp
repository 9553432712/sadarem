<%--
    Document   : DirectorReport
    Created on : 1 Aug, 2014, 6:28:50 PM
    Author     : 484898
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.ReportDTO" %>
<%@page import="org.bf.disability.Constants.CommonConstants,com.tcs.sadarem.util.CommonUtility" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script type="text/javascript" src="<%=request.getContextPath()%>/DisabilityUITG/js/jquery.min.js"></script>
      <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>
      <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
     <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
      <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
        <title>SADAREM</title>
        <%
                    ArrayList stateReport = new ArrayList();
                    stateReport = (ArrayList) request.getAttribute("stateReport");
                    int totalApplied = 0, totalAssessed = 0, rejected = 0;
                    String selFromDate="",selToDate="";
                    int eligible = 0, oh = 0, vi = 0;
                    int hi = 0, mr = 0, mi = 0, md = 0, total = 0;
                    selFromDate = CommonUtility.checkNullObj(request.getAttribute("selFromDate"));
                    selToDate = CommonUtility.checkNullObj(request.getAttribute("selToDate"));
                    if (stateReport != null) {
                        Iterator iter = stateReport.iterator();
                        while (iter.hasNext()) {
                            ReportDTO totalDTO = (ReportDTO) iter.next();
                            totalApplied = totalApplied + totalDTO.getTotalApplied();
                            totalAssessed = totalAssessed + totalDTO.getTotalAssessed();
                            rejected = rejected + totalDTO.getRejected();
                            eligible = eligible + totalDTO.getEligible();
                            oh = oh + totalDTO.getOh();
                            vi = vi + totalDTO.getVi();
                            hi = hi + totalDTO.getHi();
                            mr = mr + totalDTO.getMr();
                            mi = mi + totalDTO.getMi();
                            md = md + totalDTO.getMd();
                            total = total + totalDTO.getTotal();
                        }
                    }

        %>
<script type="text/javascript">
function postRequest(strURL,textID) 
{
    
	var xmlHttp;
    if (window.XMLHttpRequest)  // Mozilla, Safari, ...
	 {
          xmlHttp = new XMLHttpRequest();
    }
    else if (window.ActiveXObject)  // IE
	{
          xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlHttp.open('POST', strURL, true);
    xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xmlHttp.onreadystatechange = function()
     {
	    
    	if (xmlHttp.readyState == 1) 
        {
    		updatepage("<center><img src='<%=request.getContextPath()%>/images/loading.gif' width='15' height='15'></center>",textID);
        }
	    else if(xmlHttp.readyState == 2)
	    {
	    	updatepage('Wait..',textID);
	    }
	    else if(xmlHttp.readyState == 3)
	    {
	    	updatepage('Please Wait..',textID);
	    }
	    else if (xmlHttp.readyState == 4) 
        {
	    	var errorCode = xmlHttp.getResponseHeader('errorCode');
	    
	    	if(errorCode=="" || errorCode==null ) // Check null to for mozilla
	    	{
        		updatepage(xmlHttp.responseText,textID);
        		
	    	}
	    	else
	    	{
	    		$('#errorMsg').html(errorCode).fadeIn(100);
	    		location.replace(window.location);
	    	}
    	}
	};
	xmlHttp.send(strURL);
}
function updatepage(msg,id)
{		
	if(msg!="" && msg!="null")
	{
		document.getElementById(id).innerHTML=msg;
	}
}
</script>
        <script>
        $(document).ready(function() 
        		{
        	
 
        		new JsDatePick({
        	    	 
//        			minDate: new Date(today.getYear(), today.getMonth() +1, today.getDay()),
          			useMode:2,
          			target:"fromdate",
          			//dateFormat:"%d-%M-%Y",
          			//selectedDate:{day:25,month:12,year:2013},
          			yearsRange:[2009,2100],
          			minDate: 0,
          			limitToToday:true,
          			cellColorScheme:"beige",
          			dateFormat:"%d/%m/%Y",
          			imgPath:"img/",
          			weekStartDay:1
                 });	
        		
        g_calendarObject_end = new JsDatePick({
        	    	 
//        			minDate: new Date(today.getYear(), today.getMonth() +1, today.getDay()),
          			useMode:2,
          			target:"todate",
          			//dateFormat:"%d-%M-%Y",
          			//selectedDate:{day:25,month:12,year:2013},
          			yearsRange:[2009,2100],
          			minDate: 0,
          			limitToToday:true,
          			cellColorScheme:"beige",
          			dateFormat:"%d/%m/%Y",
          			imgPath:"img/",
          			weekStartDay:1
                 });	
    	$('#district_id').change(function()
    			{	
    		
    				postRequest("<%=request.getContextPath()%>/ajax.do?action=loadcamprep&distId="+ $('#district_id').val()+"&randomid="+Math.random(),"campTDID");
    				
    			});
    	$('#year').change(function()
    			{	
    		
    				postRequest("<%=request.getContextPath()%>/ajax.do?action=loadmonths&yearId="+ $('#year').val()+"&randomid="+Math.random(),"monthsID");
    				
    			});
    	   
                });
            var typeOfselection="";
            var reportType="";
            <%if (request.getAttribute("typeOfSearchValue") != null) {%>
                typeOfselection='<%=request.getAttribute("typeOfSearchValue")%>';
            <%}%>
            <%if (request.getAttribute("reportTypeValue") != null) {%>
                reportType='<%=request.getAttribute("reportTypeValue")%>';

            <%}%>

                function validate_required(field,alerttxt)
                {

                    with (field)
                    {
                        if (value==null||value==""||value=="0")
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
                    var flage="true";
            <%
                        session.removeAttribute("stateReport");
            %>
                    with (thisform)
                    {
                        if(document.getElementById('3').checked){
                            if (validate_required(financialYear,"Financial Year Wise Selection must be selected!")==false)
                            {
                                flage="false";
                                financialYear.focus();
                                return false;
                            }
                        }
                        else if(document.getElementById('2').checked){
                            if (validate_required(year,"Year must be selected!")==false)
                            {
                                flage="false";
                                year.focus();
                                return false;
                            } if (validate_required(month,"Month must be selected!")==false)
                            {
                                flage="false";
                                month.focus();
                                return false;
                            }
                        }else if(document.getElementById('1').checked){
                            if (validate_required(fromdate,"From Date must be selected!")==false)
                            {
                                flage="false";
                                fromdate.focus();
                                return false;
                            }
                            if (validate_required(todate,"To Date must be selected!")==false)
                            {
                                flage="false";
                                todate.focus();
                                return false;
                            }

                            var fromDate= document.forms[0].fromdate.value;
                            var toDate= document.forms[0].todate.value;
                            var yye=fromDate.substr(6,4);
                            var mme=fromDate.substr(3,2);
                            var dde=fromDate.substr(0,2);
                            var yyb=toDate.substr(6,4);
                            var mmb=toDate.substr(3,2);
                            var ddb=toDate.substr(0,2);
                            var dob = new  Date();
                            var etd = new  Date();
                            var today=new Date();
                            etd.setFullYear(yye,mme-1,dde);
                            dob.setFullYear(yyb,mmb-1,ddb)
                            var y1=today.getYear();
                            var y2= dob.getYear();
                            if (today < etd)
                            {
                                flage="false";
                                alert("From date is before Today's Date");
                                document.forms[0].fromdate.value="";
                                return false;
                            }
                            if (today < dob)
                            {
                                flage="false";
                                alert("To date is before Today's Date");
                                document.forms[0].todate.value="";
                                return false;
                            }
                            if (dob < etd)
                            {
                                flage="false";
                                alert("From date is greater than To date");
                                document.forms[0].fromdate.value="";
                                return false;
                            }
                        }
                        if(document.getElementById('10').checked){
                            if (validate_required(district_id,"District must be Selected!")==false)
                            {
                                flage="false";
                                district_id.focus();
                                return false;
                            }
                            if (validate_required(campId,"Camp must be Selected!")==false)
                            {
                                flage="false";
                                campId.focus();
                                return false;
                            }
                        }

                        if(flage="true"){
                            document.forms[0].mode.value='getResultsForDirector';
                            document.forms[0].submit();
                        }

                    }

                }


                function changeSelection(id){
                    if(id=='FinancialYear'){

                        document.getElementById('betweenDates').style.display="none";
                        document.getElementById('month').style.display="none";
                        document.getElementById('financialYear').style.display="block";
                        document.getElementById('reportType').style.display="block";
                        document.forms[0].elements["month"].value="0";
                        document.forms[0].elements["year"].value="0";
                        document.getElementById("9").checked = false;
                        document.getElementById("10").checked = false;
                        document.getElementById("11").checked = false;
                        document.getElementById('campwise').style.display="none";
                        document.getElementById('disid').style.display = 'none';

                        typeOfselection="FinancialYear";
                    }else if(id=='Month'){


                        document.getElementById('betweenDates').style.display="none";
                        document.getElementById('financialYear').style.display="none";
                        document.getElementById('month').style.display="block";
                        document.getElementById('reportType').style.display="block";
                        document.forms[0].elements["financialYear"].value="0";
                        document.getElementById("9").checked = false;
                        document.getElementById("10").checked = false;
                        document.getElementById("11").checked = false;
                        document.getElementById('campwise').style.display="none";
                        document.getElementById('disid').style.display = 'none';
                        typeOfselection="Month";
                    }else if(id=='Date'){
                        document.getElementById('betweenDates').style.display="block";
                        document.getElementById('financialYear').style.display="none";
                        document.getElementById('month').style.display="none";
                        document.getElementById('reportType').style.display="block";
                        document.forms[0].elements["month"].value="0";
                        document.forms[0].elements["year"].value="0";
                        document.forms[0].elements["financialYear"].value="0";
                        document.getElementById("9").checked = false;
                        document.getElementById("10").checked = false;
                        document.getElementById("11").checked = false;
                        document.getElementById('campwise').style.display="none";
                        document.forms[0].elements['district_id'].value="0";
                        document.forms[0].elements['campId'].value="0";
                        document.getElementById('disid').style.display = 'none';

                        typeOfselection="Date";
                    } if(id=='Camp'){
                        document.getElementById('campwise').style.display="block";
                        document.getElementById('submitButton').style.display="block";
                        document.forms[0].elements['district_id'].value="0";
                        document.forms[0].elements['campId'].value="0";
                        document.getElementById('disid').style.display = 'none';
                        reportType="Camp";
                    }else if(id=='District'){
                        document.getElementById('campwise').style.display="none";
                        document.getElementById('submitButton').style.display="block";                  
                        document.forms[0].elements['district_id'].value="0";
                        document.forms[0].elements['campId'].value="0";
                      
                        document.getElementById('disid').style.display = 'none';
                        reportType="District";
                    }else if(id=='district'){
                        document.getElementById('campwise').style.display="none";
                        document.getElementById('submitButton').style.display="block";
                        document.getElementById('campId').value="0";
                        document.forms[0].elements['district_id'].value="0";
                        document.getElementById('disid').style.display = 'none';
                        reportType="District";
                    }else if(id=='Disability'){
                        document.getElementById('campwise').style.display="none";
                        document.getElementById('submitButton').style.display="block";
                        document.forms[0].elements['district_id'].value="0";
                        document.forms[0].elements['campId'].value="0";
                        document.getElementById('disid').style.display = 'none';
                        reportType="Disability";
                    }

                }

           /*      function getMonths(){
                    document.forms[0].mode.value = "unspecified";
                    document.forms[0].submit();
                } */

                function bodyLoadfunction(){
                	 

                    if(typeOfselection=='FinancialYear'){
                        document.getElementById('betweenDates').style.display="none";
                        document.getElementById('month').style.display="none";
                        document.getElementById('financialYear').style.display="block";
                        document.getElementById('reportType').style.display="block";
                      
                        typeOfselection="FinancialYear";
                        document.getElementById('3').checked=true;
                    }else if(typeOfselection=='Month'){
                        document.getElementById('betweenDates').style.display="none";
                        document.getElementById('financialYear').style.display="none";
                        document.getElementById('month').style.display="block";
                        document.getElementById('reportType').style.display="block";
                      
                        typeOfselection="Date Wise";
                        document.getElementById('2').checked=true;
                    }else if(typeOfselection=='Date'){
                        document.getElementById('betweenDates').style.display="block";
                        document.getElementById('financialYear').style.display="none";
                        document.getElementById('month').style.display="none";
                        document.getElementById('reportType').style.display="block";
                     
                        typeOfselection="Date";
                        document.getElementById('1').checked=true;
                    } if(reportType=='Camp'){
                        document.getElementById('campwise').style.display="block";
                        document.getElementById('submitButton').style.display="block";
                      
                        document.getElementById('10').checked=true;
                        reportType="Camp";
                    }else if(reportType=='District'){

                        document.getElementById('campwise').style.display="none";
                        document.getElementById('submitButton').style.display="block";
                       
                        document.getElementById('9').checked=true;
                        reportType="District";
                    }else if(reportType=='Mandal'){
                        document.getElementById('campwise').style.display="none";
                        document.getElementById('submitButton').style.display="block";
                       
                        document.getElementById('9').checked=true;
                        reportType="Mandal";
                    }else if(reportType=='Disability'){
                        document.getElementById('campwise').style.display="none";
                        document.getElementById('submitButton').style.display="block";
                 
                        document.getElementById('11').checked=true;
                        reportType="Disability";
                    }
                }
//                 function ShowDate()
//                 {
//                     var dt = new Date();
//                     var d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
//                     document.getElementById(5).value = d;
//                  }
        </script>
        <Style>
		   .hd_gd
		   {
		     border : #234466 1px solid;
		   }
		   .gridStyle
		   {
			WIDTH: 100%; BORDER-COLLAPSE: collapse; FONT-FAMILY: verdana
		   }
      </Style>
    <body onload="OnBodyLoad(1,3);bodyLoadfunction();">

        <html:form styleId="fm1" action="/directorReportOutside.do?mode=getResultsForDirector" method="post" onsubmit="return validate_form(this);">
            <html:hidden property="mode"/>

            <br/>


            <table  align="center" cellspacing="1" cellpadding="0" width="100%" >
                <tr><th colspan="4" class="hd_gd">R1.2 : SADAREM Assessment Report Date and Camp Wise</th></tr>
            </table> 
            
            
               <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
									    <tr>
					                     <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_lft_top.png" width="16" height="16" /></td>
					                     <td width="100%" align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_top_bg.png); background-repeat:repeat-x;"></td>
					                     <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_rgt_top.png" width="16" height="16" /></td>
					                   </tr>
					                   <tr>
					                     <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_lft_bg.png); background-repeat:repeat-y;">&nbsp;</td>
					                     <td align="left" valign="top" >
					    		           <table  cellspacing="0" width="80%" border="0px">
									        <tr height="30">
                    <td colspan="6">
                        <html:radio property="typeOfSearch" styleId="1" value="Date" onclick="changeSelection(this.value);" style="width=125px">Date </html:radio>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <html:radio property="typeOfSearch" styleId="2" value="Month" onclick="changeSelection(this.value);" style="width=125px">Month </html:radio>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <html:radio property="typeOfSearch" styleId="3" value="FinancialYear" onclick="changeSelection(this.value);" style="width=125px">Financial Year </html:radio>
                    </td>
                </tr>

                <tr   id="betweenDates" style="display: none;"  >
                    <td > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;From Date<font color="red"><b>*</b></font></td>
                    <td>
                        <input type="text"  id="fromdate"  name="fromdate" value='<%=selFromDate %>' readonly="readonly"/>
                    </td>
                    <td>To Date<font color="red"><b>*</b></font></td>
                    <td>
                         <input type="text"  id="todate"  name="todate" value='<%=selToDate %>'      readonly="readonly"/>
                    </td>
                </tr>

                <tr id="month" style="display: none;"  >
                    <td> Year <font color="red"><b>*</b></font></td>
                    <td >
                        <html:select styleId="year" property="year" > <!-- onchange="getMonths(this.value)"> -->
                            <html:option value="0">  --SELECT--  </html:option>
                            <html:optionsCollection property="yearList" label="year" value="year"/>

                        </html:select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>

                    <td> Month <font color="red"><b>*</b></font></td>
                    <td id="monthsID">
                        <html:select styleId="7" property="month"  >
                            <html:option value="0">  --SELECT--  </html:option>
                            <html:optionsCollection property="monthList" label="month" value="id"/>
                        </html:select>
                    </td>
                </tr>

                <tr  id="financialYear" style="display: none;"  >
                    <td colspan="4"> Financial Year <font color="red"><b>*</b></font>
                        <html:select styleId="8" property="financialYear" >
                            <html:option value="0">  --SELECT--  </html:option>
                            <html:optionsCollection property="financialYearList" label="finyear" value="finyear"/>
                        </html:select>
                    </td>
                </tr>

                <tr  >
                    <td id="reportType" style="display: none;"  colspan="4">
                        <html:radio property="reportType" styleId="9" value="District" onclick="changeSelection(this.value);" style="width=125px">District Wise </html:radio>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <html:radio property="reportType" styleId="10" value="Camp" onclick="changeSelection(this.value);" style="width=125px">Camp Wise </html:radio>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <html:radio property="reportType" styleId="11" value="Disability" onclick="changeSelection(this.value);" style="width=125px">Disability Wise </html:radio>
                    </td>
                </tr>

                <tr  id="campwise" style="display: none;" bgcolor="white" >
                    <td>
                        District  <font color="red">*</font>
                    </td>
                    <td id='district_idtd'>
                        <html:select property="district_id" styleId="district_id"><!-- onchange="getCamps()"> -->
                            <html:option value="0">-- Districts --</html:option>
                            <html:optionsCollection property="districtlist" label="district_name" value="district_id"/>
                        </html:select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>

                    <td> Camps <font color="red">*</font></td>
                    <td id="campTDID">
                        <html:select property="campId" style="width:520px;">
                            <html:option value="0">-- Camps --</html:option>
                            <html:option value="All">All</html:option>
                            <html:optionsCollection property="campList" label="campName" value="campId"/>
                        </html:select>
                    </td>
                </tr>
                <tr  id="submitButton" style="display: none;"  >
                    <th colspan="12" align="center"><input type="Submit" name="card" value="Submit" class="button" >&nbsp;&nbsp;</th>
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

            <!-- Content Starts -->
            <logic:present name="stateReport" scope="request">
                <logic:notPresent name="disabilitywise">

                    <%
                                session.setAttribute("stateReport", stateReport);
                    %>


                    <div id="disid">
                        <logic:present name="heading" scope="request">
                            <table cellspacing="0" border="0" cellpadding="0" width="96%" >
                                <tr style="text-align:right;">
                                <td >
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </td>
                                <td >
                                    
                                    <a href="directorReport.do?mode=excel&heading=${heading}"  target="_blank">
                                      <img src="./DisabilityUITG/images/excel.jpg" width="25" height="35" border="0"></a>
                                </td>
                                </tr> 

                            </table>
                        </logic:present>
                    
                  
                        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tbody>
                 <tr>
                  <td id="tdGridContent" height="450" valign="top" width="100%" align="middle">
		        <table width="90%" align="center" >
				   <tbody>
				   <tr>
				   <td style="display:none" id="td_grid"  class="scrollme" valign="top" width="100%" colspan="3"  align="middle">
				   <div>
			       <table border="1" align="center" cellspacing="0" rules="all" class="gridStyle"    id="ctl00_dtg_Grid" style="BORDER-COLLAPSE: collapse; border-left: #234466 solid 1px !important;"  width="100%">
				   <tbody>
                
  				<tr >
  				
                                <th rowspan="2" class="hd_gd" align="center" valign="middle">S.No</th> 
                                <logic:present name="camp">
                                    <th  rowspan="2" class="hd_gd" align="center" valign="middle">Medical Board</th>
                                    <th  rowspan="2" class="hd_gd" align="center" valign="middle">MedicalBoard Address</th>
                                </logic:present>
                                <logic:present name="mandal">
                                    <th  rowspan="2" class="hd_gd" align="center" valign="middle">Mandal</th>
                                </logic:present>
                                <logic:present name="district">
                                    <th  rowspan="2" class="hd_gd" align="center" valign="middle">District</th>
                                </logic:present>

                                <th colspan="4" class="hd_gd" align="center" valign="middle">PwDs ASSESSMENT STATUS</th>
                                <th colspan="7" class="hd_gd" align="center" valign="middle">PwDs PENSIONS COVERED</th>
                            </tr>
                            <logic:present name="district">
                                <tr>
                                    <th class="hd_gd" align="center" valign="middle">Total Applied</th>
                                    <th class="hd_gd" align="center" valign="middle">Assessed</th>
                                    <th  class="hd_gd" align="center" valign="middle">Eligible (>40%)<br/>(4-6)</th>
                                    <th class="hd_gd" align="center" valign="middle">Rejected</th>
                                    
                                    <th class="hd_gd" align="center" valign="middle">Ortho</th>
                                    <th class="hd_gd" align="center" valign="middle">Visual</th>
                                    <th class="hd_gd" align="center" valign="middle">Speech & Hearing</th>
                                    <th class="hd_gd" align="center" valign="middle">Mental Retardation</th>
                                    <th class="hd_gd" align="center" valign="middle">Mental Illness</th>
                                    <th class="hd_gd" align="center" valign="middle">Multiple Disability</th>
                                    <th class="hd_gd" align="center" valign="middle">Total Pensioners<br/>(7+8+9+10+11+12)</th>
                                </tr>
                                <tr>
                                    <th class="hd_gd" align="center" valign="middle">(1)</th>
                                    <th class="hd_gd" align="center" valign="middle">(2)</th>
                                    <th class="hd_gd" align="center" valign="middle">(3)</th>
                                    <th class="hd_gd" align="center" valign="middle">(4)</th>
                                    <th class="hd_gd" align="center" valign="middle">(5)</th>
                                    <th class="hd_gd" align="center" valign="middle">(6)</th>
                                    <th class="hd_gd" align="center" valign="middle">(7)</th>
                                    <th class="hd_gd" align="center" valign="middle">(8)</th>
                                    <th class="hd_gd" align="center" valign="middle">(9)</th>
                                    <th class="hd_gd" align="center" valign="middle">(10)</th>
                                    <th class="hd_gd" align="center" valign="middle">(11)</th>
                                    <th class="hd_gd" align="center" valign="middle">(12)</th>
                                    <th class="hd_gd" align="center" valign="middle">(13)</th>
                                </tr>
                            </logic:present>
                            <logic:present name="mandal">
                                <tr>
                                    <th class="hd_gd" align="center" valign="middle">Total Applied</th>
                                    <th class="hd_gd" align="center" valign="middle">Assessed</th>
                                  
                                    <th  class="hd_gd" align="center" valign="middle">Eligible (>40%)<br/>(4-6)</th>
                                      <th class="hd_gd" align="center" valign="middle">Rejected</th>
                                    <th class="hd_gd" align="center" valign="middle">Ortho</th>
                                    <th class="hd_gd" align="center" valign="middle">Visual</th>
                                    <th class="hd_gd" align="center" valign="middle">Speech & Hearing</th>
                                    <th class="hd_gd" align="center" valign="middle">Mental Retardation</th>
                                    <th class="hd_gd" align="center" valign="middle">Mental Illness</th>
                                    <th class="hd_gd" align="center" valign="middle">Multiple Disability</th>
                                    <th class="hd_gd" align="center" valign="middle">Total Pensioners<br/>(7+8+9+10+11+12)</th>
                                </tr>
                                <tr>
                                    <th class="hd_gd" align="center" valign="middle">(1)</th>
                                    <th class="hd_gd" align="center" valign="middle">(2)</th>
                                    <th class="hd_gd" align="center" valign="middle">(3)</th>
                                    <th class="hd_gd" align="center" valign="middle">(4)</th>
                                    <th class="hd_gd" align="center" valign="middle">(5)</th>
                                    <th class="hd_gd" align="center" valign="middle">(6)</th>
                                    <th class="hd_gd" align="center" valign="middle">(7)</th>
                                    <th class="hd_gd" align="center" valign="middle">(8)</th>
                                    <th class="hd_gd" align="center" valign="middle">(9)</th>
                                    <th class="hd_gd" align="center" valign="middle">(10)</th>
                                    <th class="hd_gd" align="center" valign="middle">(11)</th>
                                    <th class="hd_gd" align="center" valign="middle">(12)</th>
                                    <th class="hd_gd" align="center" valign="middle">(13)</th>
                                </tr>
                            </logic:present>
                            <logic:present name="camp">
                                <tr>
                                    <th class="hd_gd" align="center" valign="middle">Total Applied</th>
                                    <th class="hd_gd" align="center" valign="middle">Assessed</th>
                                  
                                    <th  class="hd_gd" align="center" valign="middle">Eligible (>40%)<br/>(5-7)</th>
                                      <th class="hd_gd" align="center" valign="middle">Rejected</th>
                                    <th class="hd_gd" align="center" valign="middle">Ortho</th>
                                    <th class="hd_gd" align="center" valign="middle">Visual</th>
                                    <th class="hd_gd" align="center" valign="middle">Speech & Hearing</th>
                                    <th class="hd_gd" align="center" valign="middle">Mental Retardation</th>
                                    <th class="hd_gd" align="center" valign="middle">Mental Illness</th>
                                    <th class="hd_gd" align="center" valign="middle">Multiple Disability</th>
                                    <th class="hd_gd" align="center" valign="middle">Total Pensioners<br/>(8+9+10+11+12+13)</th>
                                </tr>
                                <tr>
                                    <th class="hd_gd" align="center" valign="middle">(1)</th>
                                    <th class="hd_gd" align="center" valign="middle">(2)</th>
                                    <th class="hd_gd" align="center" valign="middle">(3)</th>
                                    <th class="hd_gd" align="center" valign="middle">(4)</th>
                                    <th class="hd_gd" align="center" valign="middle">(5)</th>
                                    <th class="hd_gd" align="center" valign="middle">(6)</th>
                                    <th class="hd_gd" align="center" valign="middle">(7)</th>
                                    <th class="hd_gd" align="center" valign="middle">(8)</th>
                                    <th class="hd_gd" align="center" valign="middle">(9)</th>
                                    <th class="hd_gd" align="center" valign="middle">(10)</th>
                                    <th class="hd_gd" align="center" valign="middle">(11)</th>
                                    <th class="hd_gd" align="center" valign="middle">(12)</th>
                                    <th class="hd_gd" align="center" valign="middle">(13)</th>
                                    <th class="hd_gd" align="center" valign="middle">(14)</th>
                                </tr>
                            </logic:present>
                            <% int i = 0;String classStyle="";%>

                            <logic:iterate id="modify" name="stateReport" scope="request" indexId="count">
                               <%if(count.intValue()%2==0)
		        			     {
			        			  	classStyle="secondrow";
			        			  }
			        			  else
			        			  {
				        			  	classStyle="firstrow";
			        			  } %>

                                <tr >
                                    <td class="<%=classStyle%>"><%=++i%></td>
                                    <logic:present name="camp">
                                        <td width="200px" class="<%=classStyle%>">
                                            ${modify.campName}
                                        </td>
                                        <td class="<%=classStyle%>">
                                            ${modify.campVenue}
                                        </td>
                                    </logic:present>
                                    <logic:present name="mandal">
                                        <td class="<%=classStyle%>">
                                            ${modify.mandal_name}
                                        </td>
                                    </logic:present>
                                    <logic:present name="district">
                                        <td class="<%=classStyle%>">

                                            <a href="./directorReportOutside.do?mode=getResultsForDirector&repType=<%=request.getParameter("reportType")%>&districtId=${modify.district_id}&srchType=<%=request.getParameter("typeOfSearch")%>&yr=<%=request.getParameter("year")%>&directorFlag=1&mnt=<%=request.getParameter("month")%>&frDate=<%=request.getParameter("fromdate")%>&tDate=<%=request.getParameter("todate")%>&finYear=<%=request.getParameter("financialYear")%>">${modify.district_name}</a>
                                        </td>
                                    </logic:present>



                                    <logic:present name="district">
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=1&districtId=${modify.district_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.totalApplied}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=2&districtId=${modify.district_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.totalAssessed}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=4&districtId=${modify.district_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.eligible}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=3&districtId=${modify.district_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.rejected}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=5&districtId=${modify.district_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.oh}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=6&districtId=${modify.district_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.vi}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=7&districtId=${modify.district_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.hi}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=8&districtId=${modify.district_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.mr}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=9&districtId=${modify.district_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.mi}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=10&districtId=${modify.district_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.md}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=11&districtId=${modify.district_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.total}</a></td>
                                    </logic:present>
                                    <logic:present name="mandal">
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=1&districtId=${modify.district_id}&mandalId=${modify.mandal_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.totalApplied}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=2&districtId=${modify.district_id}&mandalId=${modify.mandal_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.totalAssessed}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=4&districtId=${modify.district_id}&mandalId=${modify.mandal_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.eligible}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=3&districtId=${modify.district_id}&mandalId=${modify.mandal_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.rejected}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=5&districtId=${modify.district_id}&mandalId=${modify.mandal_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.oh}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=6&districtId=${modify.district_id}&mandalId=${modify.mandal_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.vi}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=7&districtId=${modify.district_id}&mandalId=${modify.mandal_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.hi}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=8&districtId=${modify.district_id}&mandalId=${modify.mandal_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.mr}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=9&districtId=${modify.district_id}&mandalId=${modify.mandal_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.mi}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=10&districtId=${modify.district_id}&mandalId=${modify.mandal_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.md}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=11&districtId=${modify.district_id}&mandalId=${modify.mandal_id}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.total}</a></td>
                                    </logic:present>
                                    <logic:present name="camp">
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=1&districtId=${modify.district_id}&campId=${modify.campId}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.totalApplied}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=2&districtId=${modify.district_id}&campId=${modify.campId}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.totalAssessed}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=4&districtId=${modify.district_id}&campId=${modify.campId}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.eligible}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=3&districtId=${modify.district_id}&campId=${modify.campId}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.rejected}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=5&districtId=${modify.district_id}&campId=${modify.campId}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.oh}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=6&districtId=${modify.district_id}&campId=${modify.campId}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.vi}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=7&districtId=${modify.district_id}&campId=${modify.campId}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.hi}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=8&districtId=${modify.district_id}&campId=${modify.campId}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.mr}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=9&districtId=${modify.district_id}&campId=${modify.campId}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.mi}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=10&districtId=${modify.district_id}&campId=${modify.campId}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.md}</a></td>
                                        <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&reporttypeId=11&districtId=${modify.district_id}&campId=${modify.campId}&individualList=individualList&heading=${heading}" target="_blank"> ${modify.total}</a></td>
                                    </logic:present>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <logic:present name="camp">
                                    <th class="hd_gd" colspan="3">Total</th>
                                </logic:present>
                                <logic:present name="district">
                                    <th class="hd_gd" colspan="2">Total</th>
                                </logic:present>
                                <logic:present name="mandal">
                                    <th class="hd_gd" colspan="2">Total</th>
                                </logic:present>
                                <th class="hd_gd"><%=totalApplied%></th>
                                <th class="hd_gd"><%=totalAssessed%></th>
                                <th class="hd_gd"><%=eligible%></th>
                                <th class="hd_gd"><%=rejected%></th>
                                <th class="hd_gd"><%=oh%></th>
                                <th class="hd_gd"><%=vi%></th>
                                <th class="hd_gd"><%=hi%></th>
                                <th class="hd_gd"><%=mr%></th>
                                <th class="hd_gd"><%=mi%></th>
                                <th class="hd_gd"><%= md%></th>
                                <th class="hd_gd"><%= total%></th>
                            </tr>
                                                       </tbody>
	</table>
	</div>
	</td>
	</tr>
	</tbody>
	</table>
	</td></tr></tbody></table></div>
                </logic:notPresent>




                <table id="disid">
                    <tr >
                        <td>
                            <logic:present name="disabilitywise" scope="request">
                                <table cellspacing="0" border="0" cellpadding="0" width="90%" align="center" >
                                    <%
                                                session.setAttribute("stateReport", stateReport);
                                    %>
                                    <tr>
                                        <logic:present name="districtwise">
                                            <td >
                                                District:ALL&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mandal:All&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Village:All
                                            </td>
                                        </logic:present>
                                        <logic:present name="mandalwise">
                                            <td>
                                                District:${districtName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mandal:All&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Village:All
                                            </td>

                                        </logic:present>
                                        <logic:present name="villagewise">
                                            <td>
                                                District:${districtName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mandal:${mandalName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Village:All
                                            </td>
                                        </logic:present>
                                        <logic:present name="habitationwise">
                                            <td>
                                                District:${districtName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mandal:${mandalName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Village:${villageName}
                                            </td>
                                        </logic:present>
                                        <logic:present name="districtwise">

                                            <td align="right" colspan="5">
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            </td>
                                            <td align="right" id="dt">
                                               
                                                <a href="directorReportOutside.do?mode=excel&heading=${heading}&districtId=${id.district_id}&districtname=${id.district_name}&srchType=<%=request.getParameter("typeOfSearch")%>&repType=<%=request.getParameter("reportType")%>&frDate=<%=request.getParameter("fromdate")%>&tDate=<%=request.getParameter("todate")%>&finYear=<%=request.getParameter("financialYear")%>&yr=<%=request.getParameter("year")%>&mnt=<%=request.getParameter("month")%>"  target="_blank">
                                                   <img src="./DisabilityUITG/images/excel.jpg" width="25" height="35" border="0">  </a>
                                            </td>
                                        </logic:present>
                                        <logic:present name="mandalwise">

                                            <td style="text-align:right;">
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            </td>
                                            <td >
                                               
                                                <a href="directorReportOutside.do?mode=excel&heading=${heading}&districtId=${id.district_id}&mandalId=${id.mandal_id}&srchType=${id.typeOfSearch}&repType=${id.reportType}&frDate=${id.fromdate}&tDate=${id.todate}&finYear=<%=request.getAttribute("financialYear")%>&yr=<%=request.getAttribute("year")%>&mnt=<%=request.getAttribute("month")%>"  target="_blank">
                                                     <img src="./DisabilityUITG/images/excel.jpg" width="25" height="35" border="0"></a>
                                            </td>

                                        </logic:present>
                                        <logic:present name="villagewise">
                                            <td align="right" colspan="5">
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            </td>
                                            <td align="right" id="dt">
                                                
                                                <a href="directorReportOutside.do?mode=excel&heading=${heading}&districtId=${id.district_id}&mandalId=${id.mandal_id}&villageId=${id.village_id}&srchType=${id.typeOfSearch}&repType=${id.reportType}&frDate=${id.fromdate}&tDate=${id.todate}&finYear=<%=request.getAttribute("financialYear")%>&yr=<%=request.getAttribute("year")%>&mnt=<%=request.getAttribute("month")%>"  target="_blank">
                                                   <img src="./DisabilityUITG/images/excel.jpg" width="25" height="35" border="0"></a>
                                            </td>
                                        </logic:present>
                                        <logic:present name="habitationwise">
                                            <td align="right" colspan="5">
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            </td>
                                            <td align="right" id="dt">
                                               
                                                <a href="directorReportOutside.do?mode=excel&heading=${heading}&districtId=${id.district_id}&mandalId=${id.mandal_id}&villageId=${id.village_id}&srchType=${id.typeOfSearch}&repType=${id.reportType}&frDate=${id.fromdate}&tDate=${id.todate}&finYear=<%=request.getAttribute("financialYear")%>&yr=<%=request.getAttribute("year")%>&mnt=<%=request.getAttribute("month")%>"  target="_blank">
                                                     <img src="./DisabilityUITG/images/excel.jpg" width="25" height="35" border="0"></a>
                                            </td>
                                        </logic:present>

                                    </tr>

                                </table>
                                  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tbody>
                 <tr>
                  <td id="tdGridContent" height="450" valign="top" width="100%" align="middle">
		        <table width="90%" align="center" >
				   <tbody>
				   <tr>
				   <td style="display:none" id="td_grid"  class="scrollme" valign="top" width="100%" colspan="3"  align="middle">
				   <div>
			       <table border="1" align="center" cellspacing="0" rules="all" class="gridStyle"    id="ctl00_dtg_Grid" style="BORDER-COLLAPSE: collapse; border-left: #234466 solid 1px !important;"  width="100%">
				   <tbody>
                
  				<tr >
                                        <th rowspan="2" class="hd_gd">S.No</th>
                                        <logic:present name="districtwise">
                                            <th rowspan="2" class="hd_gd">
                                                District
                                            </th>
                                        </logic:present>
                                        <logic:present name="mandalwise">
                                            <th rowspan="2" class="hd_gd">
                                                Mandal
                                            </th>
                                        </logic:present>
                                        <logic:present name="villagewise">
                                            <th rowspan="2" class="hd_gd">
                                                Village
                                            </th>
                                        </logic:present>
                                        <logic:present name="habitationwise">
                                            <th rowspan="2" class="hd_gd">
                                                Habitation
                                            </th>
                                        </logic:present>
                                        <th colspan="3" class="hd_gd">
                                            Locomotor/OH
                                        </th>
                                        <th colspan="3" class="hd_gd">
                                            Visual Impairment
                                        </th>
                                        <th colspan="3" class="hd_gd">
                                            Hearing Impairment
                                        </th>
                                        <th colspan="3" class="hd_gd">
                                            Mental Retardation
                                        </th>
                                        <th colspan="3" class="hd_gd">
                                            Mental Illness
                                        </th>
                                        <th colspan="3" class="hd_gd">
                                            Multiple Disability
                                        </th>

                                         <th colspan="3" class="hd_gd">
                                            Total Disability
                                        </th>
                                    </tr>
                                    <tr>
                                        <th class="hd_gd"> Assessed </th>
                                        <th class="hd_gd"> Eligible </th>
                                        <th class="hd_gd"> Rejected </th>

                                        <th class="hd_gd"> Assessed </th>
                                        <th class="hd_gd"> Eligible </th>
                                        <th class="hd_gd"> Rejected </th>

                                        <th class="hd_gd"> Assessed </th>
                                        <th class="hd_gd"> Eligible </th>
                                        <th class="hd_gd"> Rejected </th>

                                        <th class="hd_gd"> Assessed </th>
                                        <th class="hd_gd"> Eligible </th>
                                        <th class="hd_gd"> Rejected </th>

                                        <th class="hd_gd"> Assessed </th>
                                        <th class="hd_gd"> Eligible </th>
                                        <th class="hd_gd"> Rejected </th>

                                        <th class="hd_gd"> Assessed </th>
                                        <th class="hd_gd"> Eligible </th>
                                        <th class="hd_gd"> Rejected </th>

                                        <th class="hd_gd"> Assessed </th>
                                        <th class="hd_gd"> Eligible </th>
                                        <th class="hd_gd"> Rejected </th>
                                    </tr>
                                    <tr>
                                        <th class="hd_gd">(1)</th>
                                        <th class="hd_gd">(2)</th>
                                        <th class="hd_gd">(3)</th>
                                        <th class="hd_gd">(4)</th>
                                        <th class="hd_gd">(5)</th>
                                        <th class="hd_gd">(6)</th>
                                        <th class="hd_gd">(7)</th>
                                        <th class="hd_gd">(8)</th>
                                        <th class="hd_gd">(9)</th>
                                        <th class="hd_gd">(10)</th>
                                        <th class="hd_gd">(11)</th>
                                        <th class="hd_gd">(12)</th>
                                        <th class="hd_gd">(13)</th>
                                        <th class="hd_gd">(14)</th>
                                        <th class="hd_gd">(15)</th>
                                        <th class="hd_gd">(16)</th>
                                        <th class="hd_gd">(17)</th>
                                        <th class="hd_gd">(18)</th>
                                        <th class="hd_gd">(19)</th>
                                        <th class="hd_gd">(20)</th>
                                        <th class="hd_gd">(21)</th>
                                        <th class="hd_gd">(22)</th>
                                        <th class="hd_gd">(23)</th>
                                    </tr>
                                    <% int k = 0; String classStyle="";%>
                                    <logic:iterate id="id" name="stateReport" scope="request" indexId="count">
		                                    <%if(count.intValue()%2==0)
						        			     {
							        			  	classStyle="secondrow";
							        			  }
							        			  else
							        			  {
								        			  	classStyle="firstrow";
					        			      } %>
                                        <tr >
                                            <td class="<%=classStyle%>"><%=++k%></td>
                                            <logic:present name="districtwise">
                                                <td class="<%=classStyle%>"><a href="./directorReportOutside.do?mode=getResultsForDirector&repType=<%=request.getParameter("reportType")%>&districtId=${id.district_id}&districtname=${id.district_name}&srchType=<%=request.getParameter("typeOfSearch")%>&frDate=<%=request.getParameter("fromdate")%>&tDate=<%=request.getParameter("todate")%>&finYear=<%=request.getParameter("financialYear")%>&yr=<%=request.getParameter("year")%>&directorFlag=1&mnt=<%=request.getParameter("month")%>">${id.district_name}</a></td>
                                            </logic:present>
                                            <logic:present name="mandalwise">
                                                <td class="<%=classStyle%>"><a href="./directorReportOutside.do?mode=getResultsForDirector&districtname=${districtName}&mandalname=${id.mandal_name}&districtId=${id.district_id}&mandalId=${id.mandal_id}&srchType=${id.typeOfSearch}&repType=${id.reportType}&frDate=${id.fromdate}&tDate=${id.todate}&finYear=<%=request.getAttribute("financialYear")%>&yr=<%=request.getAttribute("year")%>&directorFlag=1&mnt=<%=request.getAttribute("month")%>">${id.mandal_name}</a></td>
                                            </logic:present>
                                            <logic:present name="villagewise">
                                                <td class="<%=classStyle%>"><a href="./directorReportOutside.do?mode=getResultsForDirector&districtname=${districtName}&mandalname=${mandalName}&villagename=${id.village_name}&districtId=${id.district_id}&mandalId=${id.mandal_id}&villageId=${id.village_id}&srchType=${id.typeOfSearch}&repType=${id.reportType}&frDate=${id.fromdate}&tDate=${id.todate}&finYear=<%=request.getAttribute("financialYear")%>&yr=<%=request.getAttribute("year")%>&directorFlag=1&mnt=<%=request.getAttribute("month")%>">${id.village_name}</a></td>
                                                <%--<td>${id.village_name}</td>--%>
                                            </logic:present>
                                            <logic:present name="habitationwise">
                                                <%--<td><a href="./directorReportOutside.do?mode=getResultsForDirector&districtId=${id.district_id}&mandalId=${id.mandal_id}&villageId=${id.village_id}&srchType=${id.typeOfSearch}&repType=${id.reportType}&frDate=<%=request.getParameter("fromdate")%>&tDate=<%=request.getParameter("todate")%>&finYear=<%=request.getParameter("financialYear")%>">${id.village_name}</a></td>--%>
                                                <td class="<%=classStyle%>">${id.habitation_name}</td>
                                            </logic:present>
                                            <logic:present name="districtwise">
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=1&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.ohassessed}</a></td>
                                                <td class="<%=classStyle%>"style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=2&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.oheligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=3&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.ohrejected}</a></td>

                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=4&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.viassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&&disabilityId=5&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.vieligible}</a></td>
                                                <td class="<%=classStyle%>"style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=6&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.virejected}</a></td>

                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=7&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.hiassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=8&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.hieligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=9&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.hirejected}</a></td>

                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=10&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mrassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=11&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mreligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=12&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mrrejected}</a></td>

                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=13&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.miassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=14&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mieligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=15&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mirejected}</a></td>

                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=16&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mdassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=17&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mdeligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=18&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mdrejected}</a></td>

                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=19&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.totalAssessedDis}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=20&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.totalEligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=21&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.totalRejected}</a></td>
                                      
                                            </logic:present>

                                            <logic:present name="mandalwise">
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=1&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.ohassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=2&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.oheligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=3&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.ohrejected}</a></td>

                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=4&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.viassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=5&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.vieligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=6&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.virejected}</a></td>

                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=7&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.hiassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=8&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.hieligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=9&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.hirejected}</a></td>

                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=10&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mrassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=11&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mreligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=12&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mrrejected}</a></td>

                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=13&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.miassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=14&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mieligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=15&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mirejected}</a></td>

                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=16&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mdassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=17&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mdeligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=18&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mdrejected}</a></td>


                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=19&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.totalAssessedDis}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=20&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.totalEligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=21&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.totalRejected}</a></td>
                                            </logic:present>

                                            <logic:present name="villagewise">
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=1&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.ohassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=2&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.oheligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=3&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.ohrejected}</a></td>

                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=4&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.viassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&&disabilityId=5&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.vieligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=6&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.virejected}</a></td>

                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=7&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.hiassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=8&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.hieligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=9&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.hirejected}</a></td>

                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=10&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mrassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=11&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mreligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=12&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mrrejected}</a></td>

                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=13&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.miassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=14&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mieligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=15&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mirejected}</a></td>

                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=16&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mdassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=17&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mdeligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=18&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mdrejected}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=19&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.totalAssessedDis}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=20&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.totalEligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=21&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.totalRejected}</a></td>
                                            </logic:present>

                                            <logic:present name="habitationwise">
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=1&habcode=${id.habitation_id}&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.ohassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=2&habcode=${id.habitation_id}&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.oheligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=3&habcode=${id.habitation_id}&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.ohrejected}</a></td>

                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=4&habcode=${id.habitation_id}&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.viassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&&disabilityId=5&habcode=${id.habitation_id}&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.vieligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=6&habcode=${id.habitation_id}&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.virejected}</a></td>

                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=7&habcode=${id.habitation_id}&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.hiassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=8&habcode=${id.habitation_id}&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.hieligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=9&habcode=${id.habitation_id}&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.hirejected}</a></td>

                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=10&habcode=${id.habitation_id}&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mrassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=11&habcode=${id.habitation_id}&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mreligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=12&habcode=${id.habitation_id}&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mrrejected}</a></td>

                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=13&habcode=${id.habitation_id}&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.miassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=14&habcode=${id.habitation_id}&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mieligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=15&habcode=${id.habitation_id}&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mirejected}</a></td>

                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=16&habcode=${id.habitation_id}&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mdassessed}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=17&habcode=${id.habitation_id}&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mdeligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=18&habcode=${id.habitation_id}&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.mdrejected}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=19&habcode=${id.habitation_id}&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.totalAssessedDis}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=20&habcode=${id.habitation_id}&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.totalEligible}</a></td>
                                                <td class="<%=classStyle%>" style="text-align: center"><a href="directorReportOutside.do?mode=getDirectorReportIndividualDetails&disabilityId=21&habcode=${id.habitation_id}&villageId=${id.village_id}&mandalId=${id.mandal_id}&districtId=${id.district_id}&individualList=individualList&heading=${heading}" target="_blank">${id.totalRejected}</a></td>
                                            </logic:present>

                                        </tr>
                                    </logic:iterate>
                                    <tr>
                                        <td colspan="2" style="text-align: center" class="hd_gd">
                                            <b>Total</b>
                                        </td>
                                        <td style="text-align: center" class="hd_gd">
                                            <b>${id.totalOHassessed}</b>
                                        </td>
                                        <td style="text-align: center" class="hd_gd">
                                            <b>${id.totalOHeligible}</b>
                                        </td>
                                        <td style="text-align: center" class="hd_gd">
                                            <b>${id.totalOHrejected}</b>
                                        </td>

                                        <td style="text-align: center" class="hd_gd" >
                                            <b>${id.totalVIassessed}</b>
                                        </td>
                                        <td style="text-align: center" class="hd_gd">
                                            <b>${id.totalVIeligible}</b>
                                        </td>
                                        <td style="text-align: center" class="hd_gd">
                                            <b>${id.totalVIrejected}</b>
                                        </td>

                                        <td style="text-align: center" class="hd_gd">
                                            <b>${id.totalHIassessed}</b>
                                        </td>
                                        <td style="text-align: center" class="hd_gd">
                                            <b>${id.totalHIeligible}</b>
                                        </td>
                                        <td style="text-align: center" class="hd_gd">
                                            <b>${id.totalHIrejected}</b>
                                        </td>

                                        <td style="text-align: center" class="hd_gd">
                                            <b>${id.totalMRassessed}</b>
                                        </td>
                                        <td style="text-align: center" class="hd_gd">
                                            <b>${id.totalMReligible}</b>
                                        </td>
                                        <td style="text-align: center" class="hd_gd">
                                            <b>${id.totalMRrejected}</b>
                                        </td>

                                        <td style="text-align: center" class="hd_gd">
                                            <b>${id.totalMIassessed}</b>
                                        </td>
                                        <td style="text-align: center" class="hd_gd">
                                            <b>${id.totalMIeligible}</b>
                                        </td>
                                        <td style="text-align: center" class="hd_gd">
                                            <b>${id.totalMIrejected}</b>
                                        </td>

                                        <td style="text-align: center" class="hd_gd">
                                            <b>${id.totalMDassessed}</b>
                                        </td>
                                        <td style="text-align: center" class="hd_gd">
                                            <b>${id.totalMDeligible}</b>
                                        </td>
                                        <td style="text-align: center" class="hd_gd">
                                            <b>${id.totalMDrejected}</b>
                                        </td>
                                        <td style="text-align: center" class="hd_gd">
                                            <b>${id.totalAssessmentDisability}</b>
                                        </td>
                                        <td style="text-align: center" class="hd_gd">
                                            <b>${id.totalEligibleDisability}</b>
                                        </td>
                                        <td style="text-align: center" class="hd_gd">
                                            <b>${id.totalRejectedDisability}</b>
                                        </td>
                                    </tr>
                                </table></div></td></tr>
                                                        </tbody></table></td></tr></tbody></table></logic:present></td></tr></table>
                <%--</logic:present>--%>
            </logic:present>



        </html:form>
    </body>


    <script src="./DisabilityUITG/js/jquery-min.js"></script>
    <logic:present name="camp">
        <script>
            document.getElementById("cmp").style.display="";
            document.getElementById("cmpval").style.display="";
            document.getElementById("data").style.display="";
        </script>
    </logic:present>
    <logic:present name="cmpStat">
        <script>
            document.getElementById("campwise").style.display="";
            document.getElementById("submitButton").style.display="";
        </script>
    </logic:present>
    <script>
        // The below two methods are for getting the table data



      /*   function getCamps() {
            document.forms[0].elements["mode"].value="unspecified";
            document.forms[0].submit();
        } */
    </script>

</html>
