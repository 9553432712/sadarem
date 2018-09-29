<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>

<%
            String selFromDate = (String) request.getAttribute("fromdate");
            String selToDate = (String) request.getAttribute("todate");
            String district_id = (String) request.getParameter("district_id");
            String mandal_id = (String) request.getParameter("mandal_id");
            String village_id = (String) request.getParameter("village_id");
            String urban_id = (String) request.getParameter("urban_id");

            String dName = (String) request.getParameter("districtName");
            String mName = (String) request.getParameter("mandalName");
            String vName = (String) request.getParameter("villageName");
           
            
%>

<html:html>
    <head>
        <title> PWD's Educational wise Details</title>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%-- <script language="JavaScript" src="./DisabilityUITG/js/Ajax.js"></script>
         <script src="./DisabilityUITG/js/Validation.js"></script>--%>
        
        <title>SADAREM</title>
         
        <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
        <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>

        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/Ajax.js"></script>

        <script language="JavaScript" src="./DisabilityUITG/js/Validations.s"></script>
        <%


                    ArrayList educationwiseList = new ArrayList();
                    educationwiseList = (ArrayList) request.getAttribute("educationwiseList");
                    int illiterate = 0, belowTenth = 0, total = 0;
                    int tenth = 0, inter = 0, diploma = 0;
                    int graduate = 0, postGraduate = 0, notEntered = 0;
                    if (educationwiseList != null) {
                        Iterator iter = educationwiseList.iterator();
                        while (iter.hasNext()) {
                            FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                            notEntered = notEntered + totalDTO.getNotEntered();
                            illiterate = illiterate + totalDTO.getIlliterate();
                            belowTenth = belowTenth + totalDTO.getBelowTenth();
                            tenth = tenth + totalDTO.getTenth();
                            inter = inter + totalDTO.getIntermediate();
                            diploma = diploma + totalDTO.getDiplomaOrITI();
                            graduate = graduate + totalDTO.getGraduate();
                            postGraduate = postGraduate + totalDTO.getPostGraduate();
                            total = total + totalDTO.getTotal();
                        }
                    }

        %>


        <script >

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
                });
        
            function ShowDate()
            {
                var dt = new Date();
                var d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                document.getElementById(8).value = d;

            }

            function getDetails() {
                if( validate_form(document.forms[0])==false){
                    return false;
                }else{
                    document.forms[0].mode.value="getDetails";
                    document.forms[0].submit();
                    return true;
                }
            }
            function getData() {
                document.forms[0].mode.value="hide";
                document.forms[0].submit();
            }

            function hideMode() {
                document.forms[0].mode.value="";
                document.forms[0].submit();
            }
           

           

            function selectedNames()
            {
                var slcBx1 = document.getElementById("1");
                var slcBx2 = document.getElementById("2");
                var slcBx3 = document.getElementById("3");

                document.getElementById("districtName").value = slcBx1.options[slcBx1.selectedIndex].text;
                document.getElementById("mandalName").value = slcBx2.options[slcBx2.selectedIndex].text;
                document.getElementById("villageName").value = slcBx3.options[slcBx3.selectedIndex].text;

            }

         
            function validate_required(field,alerttxt)
            {

                with (field)
                {

                    if (field==null||field=="")
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
                    if (validate_required(fromDate,"From Date must be selected!")==false)
                    {
                        document.forms[0].fromdate.focus();
                        return false
                    }
                    if (validate_required(toDate,"To Date must be selected!")==false)
                    {
                        document.forms[0].todate.focus();
                        return false
                    }
                    if (today < etd)
                    {
                        alert("From date is before Today's Date");
                        document.forms[0].fromdate.value="";
                        return false;
                    }
                    if (today < dob)
                    {
                        alert("To date does not exceed Today's Date");
                        document.forms[0].todate.value="";
                        return false;
                    }
                    if (dob < etd)
                    {
                        alert("From date is greater than To date");
                        document.forms[0].fromdate.value="";
                        return false;
                    }
                }
            }

        </script>


    </head>
    <%if (request.getAttribute("educationwiseList") != null) {%>
    <body>
        <%} else {%>
    <body onload="ShowDate();createDistrictObject();">
        <%}%>



        <html:form action="/educationWiseReports.do" >
            <input type="hidden" name="mode"/>

            <input type="hidden" name="districtName" id="districtName"/>
            <input type="hidden" name="mandalName" id="mandalName"/>
            <input type="hidden" name="villageName" id="villageName"/>
            <input type="hidden" name="habitationName" id="habitationName"/>
            <%--  <input type="hidden" name="qualificationName" id="qualificationName"/>--%>
            <logic:present name="msg">
                <center><font color="red">${msg}</font></center>
            </logic:present>

             <table  width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="left" valign="middle" width="100%">

                        <table   width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
                            <tr>
                                <th class="hd_gd" align="center" valign="middle">R3.1 : PWD's Educational wise - Details</th> 
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
									 <tr height="30">
                                     <td  valign="middle" width="12%">Area Type</td>
                                     <td align="left" valign="middle">
                                    <html:select styleId="4" property="urban_id"  onchange="districtList();">
                                        <html:option  value="0">--ALL--</html:option>
                                        <html:option  value="Rural">Rural</html:option>
                                        <html:option  value="Urban">Urban</html:option>
                                    </html:select>

                                </td>
                                <td  colspan="2">From Date<font color="red"><b>*</b></font>
                                     <input type="text"  id="fromdate"  name="fromdate" value='<%=selFromDate %>' style="border:solid 1px #609ebf;" size="10" readonly="readonly"/>
                                </td>
                                <td colspan="2">To Date<font color="red"><b>*</b></font>
                                    <input type="text"  id="todate"  name="todate" value='<%=selToDate %>' style="border:solid 1px #609ebf;" size="10" readonly="readonly"/>
                                </td>
                                 <td>
                                    <html:button property="but" onclick="return getDetails()" value="Submit"/>
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
            <br/>
               <table width="90%">
				     <tr>
                       <td align="right" style="padding-left: 1020px;">
                       <table> 
                       <tr>
                        <logic:iterate name="educationwiseList" id="modify" length="1">
                            <logic:present name="mandal">
                                <th >
                                    <a href="educationWiseReports.do?mode=getDetails&urban_id=${modify.urban_id}&fromdate=${modify.fromdate}&todate=${modify.todate}"> 
                                    <img src="<%=request.getContextPath()%>/images/but_back.png" height="30" width="30" border="0" align="right" title="Back button"/></a>
                                </th>
                            </logic:present>
                            <logic:present name="village">
                                <th>
                                    <a href="educationWiseReports.do?mode=getDetails&district_id=${modify.district_id}&urban_id=${modify.urban_id}&fromdate=${modify.fromdate}&todate=${modify.todate}"> 
                                    <img src="<%=request.getContextPath()%>/images/but_back.png" height="30" width="30" border="0"align="right" title="Back button"/>
                                    </a>
                                </th>
                            </logic:present>

                            <logic:present name="habitation">
                                <th>
                                    <a href="educationWiseReports.do?mode=getDetails&district_id=${modify.district_id}&mandal_id=${modify.mandal_id}&urban_id=${modify.urban_id}&fromdate=${modify.fromdate}&todate=${modify.todate}">
                                    <img src="<%=request.getContextPath()%>/images/but_back.png" height="30" width="30" border="0"align="right" title="Back button"/>
                                    </a>
                                </th>
                            </logic:present>
                        </logic:iterate>
                        </tr>
                       </table>
                       </td>
					   <td  align="right" width="89%" >
						<a href="educationWiseReports.do?status=getEduDetailsPrint" target="_blank">
                         <img src="<%=request.getContextPath()%>/images/printer.gif" height="30" width="30" border="0"align="right" title="Print perview"/>
                        </a>
                        
                        <a href="educationWiseReports.xls?status=getEduDetailsExcel" target="_blank">
						 <img height="30" width="30" src="<%=request.getContextPath()%>/images/excel.jpg" border="0" align="right" title="Export Excel"/>
						</a>
                        
                        
						</td> 
                       
					  </tr>
					</table> 
            <logic:present name="educationwiseList" scope="request">
                
                <table  align="center" cellspacing="1" border="0" cellpadding="4"  width="90%">
                 <tr>
                        <th align="center" class="hd_gd">S.No</th>
                        <logic:present name="district">
                            <th class="hd_gd">District</th>
                        </logic:present>
                        <logic:present name="mandal">
                            <th class="hd_gd">Mandal</th>
                        </logic:present>
                        <logic:present name="village">
                            <th class="hd_gd">Village</th>
                        </logic:present>
                        <logic:present name="habitation">
                            <th class="hd_gd">Habitation</th>
                        </logic:present>
                        <th align="center" class="hd_gd">Not Entered</th>
                        <th align="center" class="hd_gd">Illiterate</th>
                        <th align="center" class="hd_gd">Below 10th</th>
                        <th align="center" class="hd_gd">10th Class</th>
                        <th align="center" class="hd_gd">Inter</th>
                        <th align="center" class="hd_gd">Diploma</th>
                        <th align="center" class="hd_gd">Graduate</th>
                        <th align="center" class="hd_gd">Post Graduate</th>
                        <th align="center" class="hd_gd">Total</th>
                    </tr>
                    <tr>
                      <th align="center" class="hd_gd">1</th>
                      <th align="center" class="hd_gd">2</th>
                      <th align="center" class="hd_gd">3</th>
                      <th align="center" class="hd_gd">4</th>
                      <th align="center" class="hd_gd">5</th>
                      <th align="center" class="hd_gd">6</th>
                      <th align="center" class="hd_gd">7</th>
                      <th align="center" class="hd_gd">8</th>
                      <th align="center" class="hd_gd">9</th>
                      <th align="center" class="hd_gd">10</th>
                      <th align="center" class="hd_gd">11</th>
                    </tr>

                    <% int i = 0;%><%String classStyle=""; %>
                    <logic:iterate id="modify" name="educationwiseList" scope="request" indexId="count">
                      <% if(count.intValue()%2==0)
              		 {
                    	  classStyle="secondrow";
                	 }
                      else
                      {
                    	  classStyle="firstrow";
                    	  
                      } %>
                        <%if (i % 2 == 1) {%>
                        <tr>
                            <td  align="center"   class="<%=classStyle%>"><%=++i%></td>
                            <logic:present name="district">
                                <td class="<%=classStyle%>">
                                    <a href="educationWiseReports.do?mode=getDetails&district_id=${modify.district_id}&urban_id=${modify.urban_id}&fromdate=${modify.fromdate}&todate=${modify.todate}"> <bean:write name="modify" property="districtName"/></a>
                                </td>
                            </logic:present>
                            <logic:present name="mandal">
                                <td class="<%=classStyle%>">
                                    <a href="educationWiseReports.do?mode=getDetails&district_id=${modify.district_id}&mandal_id=${modify.mandal_id}&urban_id=${modify.urban_id}&fromdate=${modify.fromdate}&todate=${modify.todate}"><bean:write name="modify" property="mandalName"/></a>
                                </td>
                            </logic:present>
                            <logic:present name="village">
                                <td class="<%=classStyle%>">
                                    <a href="educationWiseReports.do?mode=getDetails&district_id=${modify.district_id}&mandal_id=${modify.mandal_id}&village_id=${modify.village_id}&urban_id=${modify.urban_id}&fromdate=${modify.fromdate}&todate=${modify.todate}"><bean:write name="modify" property="villageName"/></a>
                                </td>
                            </logic:present>

                            <logic:present name="habitation">
                                <td class="<%=classStyle%>">
                                    <bean:write name="modify" property="habitationName"/>
                                </td>
                            </logic:present>
                            <td  class="<%=classStyle%>" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('personalEducationalDetails.do?nexxt=start&back=start&status=getEducationalDetails&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<bean:write name="modify" property="fromdate"/>&toDate=<bean:write name="modify" property="todate"/>&urbanId=<bean:write name="modify" property="urban_id"/>&education=0&classStatus=Not Entered', 'Ratting','resizable=yes, scrollbars=yes,')"><bean:write name="modify" property="notEntered"/></a></td>
                            <td  class="<%=classStyle%>" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('personalEducationalDetails.do?nexxt=start&back=start&status=getEducationalDetails&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<bean:write name="modify" property="fromdate"/>&toDate=<bean:write name="modify" property="todate"/>&urbanId=<bean:write name="modify" property="urban_id"/>&education=1&classStatus=Illirerate', 'Ratting','resizable=yes, scrollbars=yes,')"><bean:write name="modify" property="illiterate"/></a></td>
                            <td  class="<%=classStyle%>" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('personalEducationalDetails.do?nexxt=start&back=start&status=getEducationalDetails&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<bean:write name="modify" property="fromdate"/>&toDate=<bean:write name="modify" property="todate"/>&urbanId=<bean:write name="modify" property="urban_id"/>&education=2&classStatus=Below 10th', 'Ratting','resizable=yes, scrollbars=yes,')"><bean:write name="modify" property="belowTenth"/></a></td>
                            <td  class="<%=classStyle%>" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('personalEducationalDetails.do?nexxt=start&back=start&status=getEducationalDetails&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<bean:write name="modify" property="fromdate"/>&toDate=<bean:write name="modify" property="todate"/>&urbanId=<bean:write name="modify" property="urban_id"/>&education=3&classStatus=10th Class', 'Ratting','resizable=yes, scrollbars=yes,')"><bean:write name="modify" property="tenth"/></a></td>
                            <td  class="<%=classStyle%>" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('personalEducationalDetails.do?nexxt=start&back=start&status=getEducationalDetails&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<bean:write name="modify" property="fromdate"/>&toDate=<bean:write name="modify" property="todate"/>&urbanId=<bean:write name="modify" property="urban_id"/>&education=4&classStatus=Inter', 'Ratting','resizable=yes, scrollbars=yes,')"><bean:write name="modify" property="intermediate"/></a></td>
                            <td  class="<%=classStyle%>" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('personalEducationalDetails.do?nexxt=start&back=start&status=getEducationalDetails&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<bean:write name="modify" property="fromdate"/>&toDate=<bean:write name="modify" property="todate"/>&urbanId=<bean:write name="modify" property="urban_id"/>&education=5&classStatus=Diploma', 'Ratting','resizable=yes, scrollbars=yes,')"><bean:write name="modify" property="diplomaOrITI"/></a></td>
                            <td  class="<%=classStyle%>" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('personalEducationalDetails.do?nexxt=start&back=start&status=getEducationalDetails&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<bean:write name="modify" property="fromdate"/>&toDate=<bean:write name="modify" property="todate"/>&urbanId=<bean:write name="modify" property="urban_id"/>&education=6&classStatus=Graduate', 'Ratting','resizable=yes, scrollbars=yes,')"><bean:write name="modify" property="graduate"/></a></td>
                            <td  class="<%=classStyle%>" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('personalEducationalDetails.do?nexxt=start&back=start&status=getEducationalDetails&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<bean:write name="modify" property="fromdate"/>&toDate=<bean:write name="modify" property="todate"/>&urbanId=<bean:write name="modify" property="urban_id"/>&education=7&classStatus=Post Graduate', 'Ratting','resizable=yes, scrollbars=yes,')"><bean:write name="modify" property="postGraduate"/></a></td>
                            <td  class="<%=classStyle%>" style="text-align: center; border-right:#234466 1px solid;"><a href="javascript:void(0);" onclick ="window.open('personalEducationalDetails.do?nexxt=start&back=start&status=getEducationalDetails&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<bean:write name="modify" property="fromdate"/>&toDate=<bean:write name="modify" property="todate"/>&urbanId=<bean:write name="modify" property="urban_id"/>&education=12&classStatus=ALL Educations', 'Ratting','resizable=yes, scrollbars=yes,')"><bean:write name="modify" property="total"/></a></td>
                        </tr>
                        <%} else {%>
                        <tr>
                            <td  align="center"  class="<%=classStyle%>" ><%=++i%></td>
                            <logic:present name="district">
                                <td class="<%=classStyle%>">
                                    <a href="educationWiseReports.do?mode=getDetails&district_id=${modify.district_id}&urban_id=${modify.urban_id}&fromdate=${modify.fromdate}&todate=${modify.todate}"> <bean:write name="modify" property="districtName"/></a>
                                </td>
                            </logic:present>
                            <logic:present name="mandal">
                                <td class="<%=classStyle%>">
                                    <a href="educationWiseReports.do?mode=getDetails&district_id=${modify.district_id}&mandal_id=${modify.mandal_id}&urban_id=${modify.urban_id}&fromdate=${modify.fromdate}&todate=${modify.todate}"><bean:write name="modify" property="mandalName"/></a>
                                </td>
                            </logic:present>
                            <logic:present name="village">
                                <td class="<%=classStyle%>">
                                    <a href="educationWiseReports.do?mode=getDetails&district_id=${modify.district_id}&mandal_id=${modify.mandal_id}&village_id=${modify.village_id}&urban_id=${modify.urban_id}&fromdate=${modify.fromdate}&todate=${modify.todate}"><bean:write name="modify" property="villageName"/></a>
                                </td>
                            </logic:present>

                            <logic:present name="habitation">
                                <td class="<%=classStyle%>">
                                    <bean:write name="modify" property="habitationName"/>
                                </td>
                            </logic:present>
                            <td  class="<%=classStyle%>" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('personalEducationalDetails.do?status=getEducationalDetails&back=start&nexxt=start&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<bean:write name="modify" property="fromdate"/>&toDate=<bean:write name="modify" property="todate"/>&urbanId=<bean:write name="modify" property="urban_id"/>&education=0&classStatus=Not Entered', 'Ratting','resizable=yes, scrollbars=yes,')"><bean:write name="modify" property="notEntered"/></a></td>
                            <td  class="<%=classStyle%>" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('personalEducationalDetails.do?status=getEducationalDetails&back=start&nexxt=start&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<bean:write name="modify" property="fromdate"/>&toDate=<bean:write name="modify" property="todate"/>&urbanId=<bean:write name="modify" property="urban_id"/>&education=1&classStatus=Illirerate', 'Ratting','resizable=yes, scrollbars=yes,')"><bean:write name="modify" property="illiterate"/></a></td>
                            <td  class="<%=classStyle%>" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('personalEducationalDetails.do?status=getEducationalDetails&back=start&nexxt=start&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<bean:write name="modify" property="fromdate"/>&toDate=<bean:write name="modify" property="todate"/>&urbanId=<bean:write name="modify" property="urban_id"/>&education=2&classStatus=Below 10th', 'Ratting','resizable=yes, scrollbars=yes,')"><bean:write name="modify" property="belowTenth"/></a></td>
                            <td  class="<%=classStyle%>" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('personalEducationalDetails.do?status=getEducationalDetails&back=start&nexxt=start&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<bean:write name="modify" property="fromdate"/>&toDate=<bean:write name="modify" property="todate"/>&urbanId=<bean:write name="modify" property="urban_id"/>&education=3&classStatus=10th Class', 'Ratting','resizable=yes, scrollbars=yes,')"><bean:write name="modify" property="tenth"/></a></td>
                            <td  class="<%=classStyle%>" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('personalEducationalDetails.do?status=getEducationalDetails&back=start&nexxt=start&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<bean:write name="modify" property="fromdate"/>&toDate=<bean:write name="modify" property="todate"/>&urbanId=<bean:write name="modify" property="urban_id"/>&education=4&classStatus=Inter', 'Ratting','resizable=yes, scrollbars=yes,')"><bean:write name="modify" property="intermediate"/></a></td>
                            <td  class="<%=classStyle%>" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('personalEducationalDetails.do?status=getEducationalDetails&back=start&nexxt=start&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<bean:write name="modify" property="fromdate"/>&toDate=<bean:write name="modify" property="todate"/>&urbanId=<bean:write name="modify" property="urban_id"/>&education=5&classStatus=Diploma', 'Ratting','resizable=yes, scrollbars=yes,')"><bean:write name="modify" property="diplomaOrITI"/></a></td>
                            <td  class="<%=classStyle%>" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('personalEducationalDetails.do?status=getEducationalDetails&back=start&nexxt=start&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<bean:write name="modify" property="fromdate"/>&toDate=<bean:write name="modify" property="todate"/>&urbanId=<bean:write name="modify" property="urban_id"/>&education=6&classStatus=Graduate', 'Ratting','resizable=yes, scrollbars=yes,')"><bean:write name="modify" property="graduate"/></a></td>
                            <td  class="<%=classStyle%>" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('personalEducationalDetails.do?status=getEducationalDetails&back=start&nexxt=start&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<bean:write name="modify" property="fromdate"/>&toDate=<bean:write name="modify" property="todate"/>&urbanId=<bean:write name="modify" property="urban_id"/>&education=7&classStatus=Post Graduate', 'Ratting','resizable=yes, scrollbars=yes,')"><bean:write name="modify" property="postGraduate"/></a></td>
                            <td  class="<%=classStyle%>" style="text-align: center; border-right:#234466 1px solid;" ><a href="javascript:void(0);" onclick ="window.open('personalEducationalDetails.do?status=getEducationalDetails&back=start&nexxt=start&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<bean:write name="modify" property="fromdate"/>&toDate=<bean:write name="modify" property="todate"/>&urbanId=<bean:write name="modify" property="urban_id"/>&education=12&classStatus=ALL Educations', 'Ratting','resizable=yes, scrollbars=yes,')"><bean:write name="modify" property="total"/></a></td>
                        </tr>
                        <%}%>
                    </logic:iterate>
                    <tr>
                        <th  colspan="2" align="center" class="hd_gd">Total</th>
                        <th  style="text-align: center" class="hd_gd"><%=notEntered%></th>
                        <th  style="text-align: center" class="hd_gd"><%=illiterate%></th>
                        <th  style="text-align: center" class="hd_gd"><%=belowTenth%></th>
                        <th  style="text-align: center" class="hd_gd"><%=tenth%></th>
                        <th  style="text-align: center" class="hd_gd"><%=inter%></th>
                        <th  style="text-align: center" class="hd_gd"><%=diploma%></th>
                        <th  style="text-align: center" class="hd_gd"><%=graduate%></th>
                        <th  style="text-align: center" class="hd_gd"><%=postGraduate%></th>
                        <th  style="text-align: center" class="hd_gd"><%= total%></th>
                    </tr>

                </table>
               </logic:present>
        </html:form>
    </body>
</html:html>


