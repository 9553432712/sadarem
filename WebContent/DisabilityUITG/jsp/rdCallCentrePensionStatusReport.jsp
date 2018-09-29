<%--
    Document   : rdCallCentrePensionStatusReport
    Created on : May 3, 2013, 2:24:43 PM
    Author     : 728056
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.Map" %>

<!DOCTYPE HTML PUBLIC "-W3CDTD HTML 4.01 TransitionalEN"
    "http:www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <link rel="stylesheet" href="./DisabilityUITG/css/Sadarem-Style.css" type="text/css">
        <link rel="stylesheet" href="./DisabilityUITG/css/cssmaster.css" type="text/css">
        <link rel="stylesheet" href="./DisabilityUITG/css/jqueryslidemenu.css" type="text/css">
        <script language="javascript" src="./DisabilityUITG/js/Mainheader-1.js"></script>
        <script type="text/javascript" src="http:ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>


        <style type="text/css">


            .arrowlistmenu{
                width: 185px; /*width of accordion menu*/
            }

            .arrowlistmenu .menuheader{ /*CSS class for menu headers in general (expanding or not!)*/
                                        font: bold 11px Verdana;
                                        color: #828282;


                                        padding-bottom:7px; /*header text is indented 10px*/
                                        cursor: hand;
                                        border-bottom:1px solid #828282;
                                        padding-left:5px;
            }

            .arrowlistmenu .openheader{ /*CSS class to apply to expandable header when it's expanded*/
                                        border-bottom:1px solid #000000;
                                        background-image:url(./DisabilityUITG/images/titlebar-active.png);
                                        padding-bottom:4px;
                                        color:#000000;
            }

            .arrowlistmenu ul{ /*CSS for UL of each sub menu*/
                               list-style-type: none;
                               margin: 0;
                               padding: 0;
                               margin-bottom: 8px; /*bottom spacing between each UL and rest of content*/
            }

            .arrowlistmenu ul li{
                padding-bottom: 2px; /*bottom spacing between menu items*/
            }

            .arrowlistmenu ul li a{
                color: #A70303;
                /*custom bullet list image*/
                display: block;
                padding:0px 0px 0px 5px;
                margin:0px 0px 0px 25px;
                text-decoration: none;
                line-height:20px;
                vertical-align:middle;
                font-weight: normal;
                border-bottom: 1px solid #dadada;
                list-style-image:url(./DisabilityUITG/images/arrowbullet.png);
                font-size: 11px;
            }

            .arrowlistmenu ul li a:visited{
                color: #ssdd33;
            }

            .arrowlistmenu ul li a:hover{ /*hover state CSS*/
                                          color: #ff9900;
                                          background-color:#FFFFFF; height:100%;
            }


        </style>


        <script language="javascript" src="./DisabilityUITG/css/jqueryslidemenu.js"></script>
        <script language="javascript" src="./DisabilityUITG/css/jquery.min.js"></script>


        <%   ArrayList statusList = new ArrayList();
                    String fromdate = "";
                    String todate = "";
                    String requestId = "";
                    String districtId = "";
                    String level = "";

                    if (request.getAttribute("fromdate") != null) {
                        fromdate = request.getAttribute("fromdate").toString();
                    }
                    if (request.getAttribute("todate") != null) {
                        todate = request.getAttribute("todate").toString();
                    }
                    if (request.getAttribute("requestId") != null) {
                        requestId = request.getAttribute("requestId").toString();
                    }
                    if (request.getAttribute("districtId") != null) {
                        districtId = request.getAttribute("districtId").toString();
                    }
                    if (request.getAttribute("level") != null) {
                        level = request.getAttribute("level").toString();
                    }

                    int totalRegistered = 0;
                    int rdcallCenterPending = 0;
                    int rdcallCenterClosed = 0;
                    int sadaremPending = 0;
                    int sadaremClosed = 0;
                    int newCertificate = 0;
                    int nameChange = 0;
                    int relationName = 0;
                    int dateOfBirth = 0;
                    int spellingMistake = 0;
                    int identificationMarksChange = 0;
                    int duplicateCertificate = 0;
                    int reassessment = 0;
                    int otherNeedsHIPhysicalRequirements = 0;
                    int renualCertificate = 0;
                    int assessementComptdNtGettingCertificate = 0;
                    int notGettingPenion = 0;
                    int notEligible = 0;
                    int abayastam = 0;
                    int iCFSDelation = 0;
                    int fingerPrintProblems = 0;
                    int aliveButpensionDead = 0;
                    int gettingDoublePension = 0;
                    int others = 0;
                    if (request.getAttribute("statusList") != null) {
                        statusList = (ArrayList) request.getAttribute("statusList");
                        request.setAttribute("statusList", statusList);
                        Iterator iter = statusList.iterator();
                        while (iter.hasNext()) {
                            Map m = (Map) iter.next();
                            totalRegistered = totalRegistered + Integer.parseInt(m.get("totalRegistered").toString());
                            rdcallCenterPending = rdcallCenterPending + Integer.parseInt(m.get("rdcallCenterPending").toString());
                            rdcallCenterClosed = rdcallCenterClosed + Integer.parseInt(m.get("rdcallCenterClosed").toString());
                            sadaremPending = sadaremPending + Integer.parseInt(m.get("sadaremPending").toString());
                            sadaremClosed = sadaremClosed + Integer.parseInt(m.get("sadaremClosed").toString());
                            newCertificate = newCertificate + Integer.parseInt(m.get("NewCertificate").toString());
                            nameChange = nameChange + Integer.parseInt(m.get("NameChange").toString());
                            relationName = relationName + Integer.parseInt(m.get("RelationName").toString());
                            dateOfBirth = dateOfBirth + Integer.parseInt(m.get("DateOfBirth").toString());
                            spellingMistake = spellingMistake + Integer.parseInt(m.get("SpellingMistake").toString());
                            identificationMarksChange = identificationMarksChange + Integer.parseInt(m.get("IdentificationMarksChange").toString());
                            duplicateCertificate = duplicateCertificate + Integer.parseInt(m.get("DuplicateCertificate").toString());
                            reassessment = reassessment + Integer.parseInt(m.get("Reassessment").toString());
                            otherNeedsHIPhysicalRequirements = otherNeedsHIPhysicalRequirements + Integer.parseInt(m.get("PhysicalRequirements").toString());
                            renualCertificate = renualCertificate + Integer.parseInt(m.get("RenualCertificate").toString());
                            assessementComptdNtGettingCertificate = assessementComptdNtGettingCertificate + Integer.parseInt(m.get("AssessementComptdNtGettingCertificate").toString());
                            notGettingPenion = notGettingPenion + Integer.parseInt(m.get("NotGettingPenion").toString());
                            notEligible = notEligible + Integer.parseInt(m.get("NotEligible").toString());
                            abayastam = abayastam + Integer.parseInt(m.get("Abayastam").toString());
                            iCFSDelation = iCFSDelation + Integer.parseInt(m.get("ICFSDelation").toString());
                            fingerPrintProblems = fingerPrintProblems + Integer.parseInt(m.get("FingerPrintProblems").toString());
                            aliveButpensionDead = aliveButpensionDead + Integer.parseInt(m.get("AliveButpensionDead").toString());
                            gettingDoublePension = gettingDoublePension + Integer.parseInt(m.get("GettingDoublePension").toString());
                            others = others + Integer.parseInt(m.get("Others").toString());
                        }
                    }%>

        <script language="javascript" src="./DisabilityUITG/css/jqueryslidemenu.js"></script>
        <script language="javascript" src="./DisabilityUITG/css/jquery.min.js"></script>
        <script  type="text/javascript">


            function ShowDate()
            {
                var dt = new Date();
                var d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                document.getElementById(8).value = d;

            }

            function getReport() {
                var fromDate= document.forms[0].fromdate.value;
                var toDate= document.forms[0].todate.value;
                if(fromDate==null || fromDate=="")
                {
                    alert("Please enter FromDate");
                    document.forms[0].fromdate.focus();
                    return false;
                }
                if(toDate==null || toDate=="")
                {
                    alert("Please enter ToDate");
                    document.forms[0].todate.focus();
                    return false;
                }else {

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
                        alert("From date is after Today's Date");
                        document.forms[0].fromdate.value="";
                        return false;
                    }
                    if (today < dob)
                    {
                        alert("To date is after Today's Date");
                        document.forms[0].todate.value="";
                        return false;
                    }
                    if (dob < etd)
                    {
                        alert("From date is greater than To date");
                        document.forms[0].fromdate.value="";
                        return false;
                    }

                    document.forms[0].mode.value="getReport";
                    document.forms[0].submit();
                    return true;
                }

            }


        </script>

    </head>

    <%if(request.getAttribute("statusList") != null){ %>
    <body>
        <%}else {%>
         <body onload="ShowDate()">
        <%}%>

    
    <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>

    <html:form action="/rdCallCentrePensionStatusReport.do" >
        <input type="hidden" name="mode"/>

        <table width="99%" border="0" cellspacing="0" cellpadding="10" align="center">
            <tr>
                <td height="445" align="center" bgcolor="#e4f5fd" valign="top">
                    <input type="hidden" name="districtName"/>

                    <table border="0" cellspacing="0" cellpadding="0" width="90%" align="center" >
                        <tr>
                            <td><img src="./DisabilityUITG/images/srch-lft-top-img.png" width="11" height="28" /></td>
                            <td width="99%" background="./DisabilityUITG/images/srch-top-bg.png" class="label" align="center"><strong>Pension Status Report Details</strong></td>
                            <td><img src="./DisabilityUITG/images/srch-rgt-top-img.png" width="11" height="28" /></td>
                        </tr>
                        <tr>
                            <td bgcolor="#f4f4f4" style="border-left:1px #7c7c7c solid;">&nbsp;</td>
                            <td height="40" align="left" valign="middle" bgcolor="#f4f4f4">

                                <table  align="center" cellspacing="1" cellpadding="5" width="100%">
                                    <tr>
                                        <td colspan="6">
                                            <logic:present name="msg">
                                                <center><font color="red" size="2"><b><bean:write name="msg"/></b></font> </center>
                                            </logic:present>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="middle" class="label" >District</td>
                                        <td align="left" valign="middle" >
                                            <html:select  property="district_id"  style="width:150px;height:25px;font-size:11px;">
                                                <html:option value="all">ALL</html:option>
                                                <html:optionsCollection   property="districtList" label="district_name" value="district_id"  />
                                            </html:select>
                                        </td>
                                        <td valign="middle" class="label">Category</td>
                                        <td align="left" valign="middle">
                                            <html:select property="requestId"  style="width:150px;height:25px;font-size:11px;">
                                                <html:option value="all">ALL</html:option>
                                              <%--  <html:optionsCollection   property="categoryList" label="requestName" value="requestId"  />--%>
                                            </html:select>
                                        </td>
                                        <td valign="middle" class="label" >Levels</td>
                                        <td align="left" valign="middle">
                                            <html:select property="level"  style="width:150px;height:25px;font-size:11px;">
                                                <html:option value="all">ALL</html:option>
                                               <%-- <html:option value="1">Level1</html:option>
                                                <html:option value="2">Level2</html:option>
                                                <html:option value="3">Level3</html:option>
                                                <html:option value="4">Level4</html:option>--%>
                                            </html:select>
                                        </td>


                                    </tr></tr>
                        <tr>
                            <td class="label" colspan="2">From Date<font color="red"><b>*</b></font>
                                <html:text property="fromdate" readonly="true" size="12" />
                                <a  href="javascript:show_calendar('forms[0].fromdate');"
                                    onmouseover="window.status='Date Picker';return true;"
                                    onmouseout="window.status='';return true;">
                                    <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                            </td>
                           <td class="label" colspan="2">To Date<font color="red"><b>*</b></font>
                                        <html:text property="todate" styleId="8"  readonly="true" size="12"/>
                                        <a  href="javascript:show_calendar('forms[0].todate');"
                                            onmouseover="window.status='Date Picker';return true;"
                                            onmouseout="window.status='';return true;">
                                    <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                            </td>
                        </tr>

                        <tr>
                            <td colspan="6" align="center">

                                <html:button property="but" onclick="return getReport();" value="Submit"/>
                            </td>
                        </tr>

                    </table>

                </td>
                <td bgcolor="#f4f4f4" style="border-right:1px #7c7c7c solid;">&nbsp;</td>
            </tr>
            <tr>
                <td height="11"><img src="./DisabilityUITG/images/srch-lft-btm-img.png" width="11" height="11" /></td>
                <td background="./DisabilityUITG/images/srch-btm-bg.png"></td>
                <td><img src="./DisabilityUITG/images/srch-rgt-btm-img.png" width="11" height="11" /></td>
            </tr>
        </table>
        <br/>
       

        <logic:notEmpty name="statusList" scope="request">
  <font style="color: red;font-size: 15px;">Note:Displaying Report As per yesterday's Data</font>
  <br/>
  <br/>
  <div style="border:1px solid #000000;
                 background-color: #FFFFFF;width:950px; height:300px;overflow:auto;">
                <table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
                   
                    <tr>
                        <td valign="top">


                            <table align="center" cellspacing="1" border="0" cellpadding="4" width="100%" style="border:1px solid #fff">
                                <tr>
                                    <td align="center" class="formhdbg"><b>S.No.</b></td>
                                    <td align="center" class="formhdbg"><b>District</b></td>
                                    <td align="center" class="formhdbg"><b>Total Registered</b></td>
                                    <td align="center" class="formhdbg"><b>Rd Call Centre Pending</b></td>
                                    <td align="center" class="formhdbg"><b>Rd Call Centre Closed</b></td>
                                    <td align="center" class="formhdbg"><b>SADAREM Pending</b></td>
                                    <td align="center" class="formhdbg"><b>SADAREM Closed</b></td>
                                    <td align="center" class="formhdbg"><b>New Certificate</b></td>
                                    <td align="center" class="formhdbg"><b>Name Change</b></td>
                                    <td align="center" class="formhdbg"><b>Relation Name</b></td>
                                    <td align="center" class="formhdbg"><b>Date of Birth</b></td>
                                    <td align="center" class="formhdbg"><b>Spelling Mistake</b></td>
                                    <td align="center" class="formhdbg"><b>Identification Marks Change</b></td>
                                    <td align="center" class="formhdbg"><b>Duplicate Certificate</b></td>
                                    <td align="center" class="formhdbg"><b>Reassessment</b></td>
                                    <td align="center" class="formhdbg"><b>Physical Requirements</b></td>
                                    <td align="center" class="formhdbg"><b>Renual Certificate</b></td>
                                    <td align="center" class="formhdbg"><b>Assessement Completed But not Getting Certificate</b></td>
                                    <td align="center" class="formhdbg"><b>Not Getting Penion</b></td>
                                    <td align="center" class="formhdbg"><b>Having Certificate But not Getting Pension Like (Not Eligible)</b></td>
                                    <td align="center" class="formhdbg"><b>Having Certificate But not Getting Pension Like (Abayastam)</b></td>
                                    <td align="center" class="formhdbg"><b>Having Certificate But not Getting Pension Like (ICFS Delation)</b></td>
                                    <td align="center" class="formhdbg"><b>Having Certificate But not Getting Pension Like (Finger Print Problems)</b></td>
                                    <td align="center" class="formhdbg"><b>Alive But In The Pension “Dead”</b></td>
                                    <td align="center" class="formhdbg"><b>Getting Double Pension</b></td>
                                    <td align="center" class="formhdbg"><b>Others</b></td>

                                </tr>


                                <% int i = 0;
                                %>
                                <logic:iterate id="rows" name="statusList" scope="request">
                                    <tr>
                                        <td  align="center" class="formaltbg"><%=++i%></td>

                                        <td align="center" class="formaltbg">  ${rows.districtName}</td>
                                        <td align="center" class="formaltbg">${rows.totalRegistered}</td>
                                        <td align="center" class="formaltbg">${rows.rdcallCenterPending}</td>
                                        <td align="center" class="formaltbg">${rows.rdcallCenterClosed}</td>
                                        <td align="center" class="formaltbg">${rows.sadaremPending}</td>
                                        <td align="center" class="formaltbg">${rows.sadaremClosed}</td>
                                        <td align="center" class="formaltbg">${rows.NewCertificate}</td>
                                        <td align="center" class="formaltbg">${rows.NameChange}</td>
                                        <td align="center" class="formaltbg">${rows.RelationName}</td>
                                        <td align="center" class="formaltbg">${rows.DateOfBirth}</td>
                                        <td align="center" class="formaltbg">${rows.SpellingMistake}</td>
                                        <td align="center" class="formaltbg">${rows.IdentificationMarksChange}</td>
                                        <td align="center" class="formaltbg">${rows.DuplicateCertificate}</td>
                                        <td align="center" class="formaltbg">${rows.Reassessment}</td>
                                        <td align="center" class="formaltbg">${rows.PhysicalRequirements}</td>
                                        <td align="center" class="formaltbg">${rows.RenualCertificate}</td>
                                        <td align="center" class="formaltbg">${rows.AssessementComptdNtGettingCertificate}</td>
                                        <td align="center" class="formaltbg">${rows.NotGettingPenion}</td>
                                        <td align="center" class="formaltbg">${rows.NotEligible}</td>
                                        <td align="center" class="formaltbg">${rows.Abayastam}</td>
                                        <td align="center" class="formaltbg">${rows.ICFSDelation}</td>
                                        <td align="center" class="formaltbg">${rows.FingerPrintProblems}</td>
                                        <td align="center" class="formaltbg">${rows.AliveButpensionDead}</td>
                                        <td align="center" class="formaltbg">${rows.GettingDoublePension}</td>
                                        <td align="center" class="formaltbg">${rows.Others}</td>


                                    </tr>
                                </logic:iterate>
                                <tr>
                                    <td colspan="2" align="center" class="formhdbg"><b>Total</b></td>
                                    <td align="center" class="formhdbg"><b><%=totalRegistered%></b></td>
                                    <td align="center" class="formhdbg"><b><%=rdcallCenterPending%></b></td>
                                    <td align="center" class="formhdbg"><b><%=rdcallCenterClosed%></b></td>
                                    <td align="center" class="formhdbg"><b><%=sadaremPending%></b></td>
                                    <td align="center" class="formhdbg"><b><%=sadaremClosed%></b></td>
                                    <td align="center" class="formhdbg"><b><%=newCertificate%></b></td>
                                    <td align="center" class="formhdbg"><b><%=nameChange%></b></td>

                                    <td align="center" class="formhdbg"><b><%=relationName%></b></td>
                                    <td align="center" class="formhdbg"><b><%=dateOfBirth%></b></td>
                                    <td align="center" class="formhdbg"><b><%=spellingMistake%></b></td>
                                    <td align="center" class="formhdbg"><b><%=identificationMarksChange%></b></td>
                                    <td align="center" class="formhdbg"><b><%=duplicateCertificate%></b></td>
                                    <td align="center" class="formhdbg"><b><%=reassessment%></b></td>
                                    <td align="center" class="formhdbg"><b><%=otherNeedsHIPhysicalRequirements%></b></td>
                                    <td align="center" class="formhdbg"><b><%=renualCertificate%></b></td>
                                    <td align="center" class="formhdbg"><b><%=assessementComptdNtGettingCertificate%></b></td>
                                    <td align="center" class="formhdbg"><b><%=notGettingPenion%></b></td>
                                    <td align="center" class="formhdbg"><b><%=notEligible%></b></td>
                                    <td align="center" class="formhdbg"><b><%=abayastam%></b></td>
                                    <td align="center" class="formhdbg"><b><%=iCFSDelation%></b></td>
                                    <td align="center" class="formhdbg"><b><%=fingerPrintProblems%></b></td>
                                    <td align="center" class="formhdbg"><b><%=aliveButpensionDead%></b></td>
                                    <td align="center" class="formhdbg"><b><%=gettingDoublePension%></b></td>
                                    <td align="center" class="formhdbg"><b><%=others%></b></td>

                                </tr>
                            </table>
                        </td></tr></table>

                <br/><br>
               
                
            </div>
                                    <br/>
              <table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
                    <tr>
                        <td>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td align="center">

                            <a href="rdCallCentrePensionStatusReport.xls?mode=getExcel&fromdate=<%=fromdate%>&todate=<%=todate%>&requestId=<%=requestId%>&districtId=<%=districtId%>&level=<%=level%>"  target="_blank">
                                Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                     height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                            <a href="rdCallCentrePensionStatusReport.xls?mode=getPrint&fromdate=<%=fromdate%>&todate=<%=todate%>&requestId=<%=requestId%>&districtId=<%=districtId%>&level=<%=level%>"  target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>

                        </td>
                    </tr>

                </table>

        </logic:notEmpty>

    </html:form>
</body>
</html>

