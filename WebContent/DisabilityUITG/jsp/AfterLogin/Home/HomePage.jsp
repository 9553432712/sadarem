<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%--<script  type="text/javascript">
    function openRequestedPopup()
    { 

        var windowObjectReference;
        var strWindowFeatures = "menubar=no,location=no,resizable=no,scrollbars=yes,status=yes,target=_parent";
        windowObjectReference = window.open("login.do", "SADAREM", strWindowFeatures);

self.close();

    }
</script>--%>
<HTML>
    <head> 
   <script>

            function go(id){

                if(id == "partacount"){
                    window.open("districtstatusreportforPartA.do?distWisestatusreportforPartA=distWisestatusreportforPartA");
                }else if(id == "partbcount"){
                    window.open("districtWiseStatusReportForPartBViewNew.do");
                }else if(id == "disabilitywise"){
                    window.open("disabilitywiseCount.do");
                }else if(id == "rejectedAadharMapping"){
                    window.open("rejectedAadharMapping.do");
                }else if(id == "Persentage"){
                    window.open("percentageWiseReport.do");
                }else if(id == "notComeToSadaremCamp"){
                    window.open("notComrToSadaremCamp.do");
                }else if(id == "onlyPartA"){
                    window.open("onlyPartA.do");
                }else if(id == "demogrepy"){
                    window.open("demogrepy.do");
                }else if(id == "cumulativereport"){
                    window.open("cumulativereport.do?getcumulativeReport=getcumulativeReport");
                }else if(id == "criteria"){
                    window.open("searchForPension.do");
                }else if(id == "searchCriteria"){
                    window.open("searchCriteria.do");
                }
                else if(id == "searchCriterianew"){
                    window.open("newMultipleCasesReport.do");
                }
                else if(id == "education"){
                    window.open("educationWiseReports.do");
                }
                else if(id == "educationnew"){
                    window.open("newEducationWiseDetails.do");
                }else if(id == "caste"){
                    window.open("casteWiseReports.do");
                }else if(id == "age"){
                    window.open("ageWiseReports.do");
                }else if(id == "marital"){
                    window.open("maritalStatus.do");
                }else if(id == "parentsMarital"){
                    window.open("parentsMarriage.do");
                }else if(id == "empRep"){
                    window.open("empReport.do");
                }
                else if(id == "empRepnew"){
                    window.open("newEmploymentReport.do");
                }else if(id == "empRep"){
                    window.open("empReport.do");
                } else if(id == "appealRegister"){
                    window.open("AppealAuthorityReport.do");
                }else if(id == "TotalReport"){
                    window.open("TotalReports1.do");
                }else if(id == "rachaBanda"){
                    window.open("rachaBanda.do");
                }else if(id == "reassessment"){
                    window.open("AppellateReport.do");
                }else if(id == "agewise"){
                    window.open("AgeWiseReports.do");
                }else if(id == "reAssessmentCampRep"){
                    window.open("reAssessmentCampWiseReport.do");
                }else if(id == "expiredSadarem"){
                    window.open("expiredSadaremCertisReport.do");
                }else if(id == "expiredSadaremnew"){
                    window.open("newExpiredSadaremCertificates.do");
                }else if(id == "districtexpiredSadarem"){
                    window.open("districtexpiredSadaremCertisReport.do");
                }  
                  
               else if(id == "searchReport"){
                    window.open("pensionForReport.do");
                } else if(id == "searchRationMember"){
                    window.open("rationcardMembersDetails.do");
                }else if(id == "physicalRequirementsReport"){
                    window.open("physicalRequirement.do");
                }else if(id == "clusterWiseReport"){
                    window.open("State.do");
                }else if(id == "marriageReport"){
                    window.open("http://sadarem.ap.gov.in/ESAPS/miaReport.do");
                }else if(id == "GrievancesStatusReport"){
                    window.open("callCentreReport.do?mode=getReportBasedOnStatus");
                }else if(id == "GrievancesMonthReport"){
                    window.open("callCentreReport.do?mode=getMonthWiseCallCentreReport");
                }else if(id == "GrievancesRequestReport"){
                    window.open("callCentreReport.do?mode=getMonthWiseCallCentreReport&page=onlyDist");

                }else if(id == "SHGReport"){
                    window.open("report.do");


                }else if(id == "certificateReport"){
                    window.open("certificateUploadReport.do");
                }
                else if(id == "certificateReportnew"){
                    window.open("newCertiUploadReport.do");
                }
                
                else if(id == "SHGEligibleReport"){
                    window.open("sHGEligibleReport.do?mode=getSHGEligible");
                }
                else if(id == "dashboard"){
                    window.open("dashboardReport.do?mode=gerDashBoardReport6");
                }
                else if(id == "newdashboard"){
                    window.open("newtotalassessmentreport.do");
                }else if(id == "director"){
                    window.open("directorReportOutside.do");
                }else if(id == "directornew"){
                    window.open("newDirectorReportOutside.do");
                }
                else if(id == "newdirector"){
                    window.open("directorNewReportOutside.do");
                }
                
                else if(id == "cmDashBoard"){
                    window.open("dashboardReport.do");
                }else if(id == "DisabilityPensions"){
                    window.open("PwdsDisabilityPension.do?mode=getDisabilityPensionsDetails");
                }else if(id == "sadaremnothavingaadhracardnos"){
                    window.open("sadaremidNothavingaadhracardNos.do");
                }else if(id == "sadaremnothavingaadharnew"){
                    window.open("newSadaremNotHavingAadhar.do");
                }else if(id == "abstractsadaremnothavingaadhracardnos"){
                    window.open("sadaremidNothavingaadhracardNos.do?mode=loadabstractsadaremnothavingaadhar");
                }  
                else if(id == "dataValidation")
                {
                    window.open("dataValidationReport.do");
                }
                else if(id == "pwdValAbstReport"){
                    window.open("pwdValAbstReport.do");
                }
                else if(id=="pwdValAbstReport16")
                	{
                	window.open("pwdValAbstReport16.do");
                	}

            }

        </script>
        <script type="text/javascript" src="./DisabilityUITG/js/accordion_jquery.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $(".toggle_container").hide();
                $("h2.expand_heading").toggle(function(){
                    $(this).addClass("active");
                }, function () {
                    $(this).removeClass("active");
                });
                $("h2.expand_heading").click(function(){
                    $(this).next(".toggle_container").slideToggle("slow");
                });
                $(".expand_all").toggle(function(){
                    $(this).addClass("expanded");
                }, function () {
                    $(this).removeClass("expanded");
                });
                $(".expand_all").click(function(){
                    $(".toggle_container").slideToggle("slow");
                });
            });
        </script>
        <style type="text/css">


            .wrapper {
                width: 400px;
                margin: 0 auto;
            }
            .expand_top,.expand_wrapper
            {
                width: 400px;
                padding:0px;
                margin:0px 0px 5px 0px;
                float:left;
            }
            h1 {
                font: 4em normal Georgia, 'Times New Roman', Times, serif;
                text-align:center;
                padding: 20px 0;
                color: #ffffff;
            }

            h2.expand_heading {
                padding: 0 0 0 20px;
                margin: 0 0 2px 0;
                background: url(./DisabilityUITG/images/expand_collapse.png) no-repeat;
                height: 32px;
                line-height: 32px;
                width: 400px;
                text-indent:8px;
                font-size: 12px;
                font-weight: bold;
                float: left;
            }
            .expand_all
            {
                cursor:default;
            }
            h2.expand_heading a {
                color: #fff;
                text-decoration: none;
                display: block;
            }
            h2.expand_heading a:hover {
                color: #eaff00;
            }

            h2.active {background-position: left bottom;}
            .toggle_container {
                margin: 0 0 5px;
                padding: 0;
                background: #ffffff;
                overflow: hidden;
                font-size:12px;
                width: 400px;
                clear: both;
            }
            .toggle_container .box {
                padding: 2px;
            }
            .toggle_container .box p {
                padding: 3px 0;
                margin: 3px 0;
                text-align: left;
                border-bottom:1px dotted #ccc;
                font-size:12px;
                color:#000;
                font-weight:normal;
            }
            .toggle_container .box p a{
                padding: 3px 0;
                margin: 3px 0;
                text-align: left;
                border-bottom:1px dotted #ccc;
                font-size:12px;
                color:#000;
                font-weight:normal;
            }
            .toggle_container h3 {
                font: 2.0em normal Georgia, "Times New Roman", Times, serif;
                margin: 0 0 5px;
                padding: 0 0 5px 0;
                color:#000000;
                border-bottom: 1px dotted #ccc;
            }
         /*   .toggle_container img {
                float: left;
                margin: 10px 15px 15px 0;
                padding: 5px;
                background: #ddd;
                border: 1px solid #ccc;
            } */
            h3 {background-color:#626262; color:#fff; padding:5px; text-align:center; font-size:12px;}
	   fieldset
	  {
	   margin:5px;    
	   border: 2px solid #4b5457;
	   padding:6px;
	  -webkit-border-radius: 10px;
	  -moz-border-radius: 10px;
	   border-radius: 10px;
	}
	legend {
	  font-family:Verdana, Geneva, sans-serif;
	  font-size:14px;
	  border: 4px solid #c2c6c7;
	  border-radius: 6px;
	  background-color:rgb(155, 10, 39);
	  padding: 10px;
	  color:#ffffff;
	  margin:0px auto;
	  text-align:center;
	  margin-bottom:5px;
	}
	strong
	{
	color : rgb(211, 71, 76);
	}
  .box_lft_common_login{ margin-top:10px; width:250px; height:300px; float:left; padding:2px 2px 2px 2px; margin:0px 0px 0px 15px; display:inline; background: rgb(236, 236, 236); border:1px solid rgb(5, 5, 5); -moz-border-radius: 10px;  -webkit-border-radius: 10px; -khtml-border-radius: 10px; border-radius: 10px;text-align:justify; line-height:20px;}
  .box_lft_headtxt{width:140px; height:30px; position:relative; padding:7px 0px 0px 40px; margin:0px auto; margin-top:-13px; background:url(./images/bg_head_ano.png) no-repeat top center; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:bold; color:#FFFFFF;  letter-spacing:1px;}
   
        </style>

        <title>SADAREM</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    </head>
    <BODY >
        <logic:present name="SuccessMSG">
            <script language="javascript" type="text/javascript">
                alert("${SuccessMSG}");
            </script>
        </logic:present>

        <p  id="succmsg">
            <%String msg = (String) request.getAttribute("msg");
                    if (msg != null && !"".equals(msg)) {%>
            <%=msg%>
            <%}%>
        </p>



        <%
                    String roleId = "";
                    if (session.getAttribute("roleId") != null) {
                        roleId = session.getAttribute("roleId").toString();
                    }
                    String districtId = "";
                    if (session.getAttribute("districtId") != null) {
                        districtId = session.getAttribute("districtId").toString();
                    }
                    String mandalId = "";
                    if (session.getAttribute("mandalId") != null) {
                        mandalId = session.getAttribute("mandalId").toString();
                    }


        %>

        <html:form action="/loginHome" > 

                <%if (roleId.equals("23") || roleId.equals("24") || roleId.equals("25")) {%>
			<table width="90%" border="0" cellspacing="0" cellpadding="0" class="inputform" >
                <logic:notEmpty name="greivanceList">
                    <tr>
                        <td>
                            <table align="center" cellspacing="1" border="0" class="inputform"  cellpadding="0" width="80%"  >
                                <tr>
                                    <th  align="center" colspan="3">Grievances Dashboard
                                    </th>
                                </tr>

                                <tr>
                                    <th align="center"  width="10%">Grievances</th>
                                    <th align="center"  width="10%">Pending</th>
                                    <th align="center"  width="10%">Closed</th>

                                </tr>
                                <logic:iterate id="row" name="greivanceList">


                                    <tr><td align="left">
                                            ${row.requestType}
                                        </td>

                                        <td align="center"><%--<a href="#" onclick="window.open('countPersonalData.do?mode=getGrievanceCountData&roleId=<%=roleId%>&districtId=<%=districtId%>&mandalId=<%=mandalId%>&requestType=${row.requestType}&type=pending')">--%>${row.pending}<%--</a>--%></td>
                                        <td align="center"><%--<a href="#" onclick="window.open('countPersonalData.do?mode=getGrievanceCountData&roleId=<%=roleId%>&districtId=<%=districtId%>&mandalId=<%=mandalId%>&requestType=${row.requestType}&type=closed')">--%>${row.closed}<%--</a>--%></td>
                                    </tr>
                                </logic:iterate>
                            </table>
                        </td>
                    </tr>
                </table>

            </logic:notEmpty>
            <%            } %>

<table width="90%" border="0" cellspacing="0" cellpadding="0" class="inputform" rules="none">
        <tr>
   <td width="40%" valign="top">
         <fieldset>
            <div class='box_lft_headtxt' align="center">Reports&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
             <table width="100%" border="0" cellspacing="0" cellpadding="5" class="wrapper" align="center" >
                <tr>
                    <td width="30%" valign="top"> 
                        <table width="100%" border="0" cellspacing="1" cellpadding="1" class="expand_wrapper" align="center">
                        <%--<tr>
                                <td>
                                    <h2 class="expand_heading"><a href="#">R1 : PWDs Personal Information</a></h2>
                                    <div class="toggle_container">
                                        <div class="box">
                                            <p>R1.1 : <a id="partacount" href="#" onClick="go(this.id)">PWDs Personal Information (Part A Filled) Abstract</a></p>
                                        </div>
                                    </div>
                                </td> </tr>--%>
                            <tr>
                                <td>
                                    <h2 class="expand_heading"><a href="#">R1 : Assessment Abstract</a></h2>
                                    <div class="toggle_container">
                                        <div class="box"> 
                                        <%-- <p>R2.1 : <a id="cumulativereport"  href="#" onClick="go(this.id)"> State Wise Cumulative Report</a></p>
                                            <p>R2.2 : <a id="partbcount"  href="#" onClick="go(this.id)">District and Phase wise Assessed PWD's cumulative Report</a></p>
                                            <p>R2.3 : <a id="reassessment"  href="#" onClick="go(this.id)">District wise Reassessed PWD's cumulative Report</a></p>
                                            <p>R2.4 : <a id="disabilitywise"  href="#" onClick="go(this.id)">State Level Disability Wise Count</a></p>
                                            <p>R2.5 :<a id="agewise"  href="#" onClick="go(this.id)"> Agewise Assessed Report</a></p>
                                        --%>  <p>R1.0 : <a id="newdashboard" href="#" onClick="go(this.id)"><%-- <span><img src="<%=request.getContextPath() %>/images/new-blink..gif" height="16"></span> --%>Total Assessed Report District Wise</a></p>      
                                        <p>R1.1 :<a id="dashboard"  href="#" onClick="go(this.id)"> SADAREM Assessment Status Report</a></p>
                                        <%--    <p>R2.7 :<a id="Persentage"  href="#" onClick="go(this.id)"> Percentage Wise Assessed Report</a></p>--%>
                                      <!--   <p>R1.2 :<a id="director"  href="#" onClick="go(this.id)"> SADAREM Assessment Report Date and Camp Wise</a></p> -->
                                        <p>R1.2 : <a id="directornew"  href="#" onClick="go(this.id)"> <%-- <span><img src="<%=request.getContextPath() %>/images/new-blink..gif" height="16"></span> --%>SADAREM Assessment Report District and Camp Wise</a></p>
                                          <p>R1.2.1 : <a id="newdirector"  href="#" onClick="go(this.id)"> <%-- <span><img src="<%=request.getContextPath() %>/images/new-blink..gif" height="16"></span> --%>SADAREM Assessment Report District Wise</a></p>
                                     
                                        <%--    <p>R2.9 :<a id="cmDashBoard"  href="#" onClick="go(this.id)"> SADAREM Assessment,Pensions,Percentage Report</a></p>
                                            <p>R2.10 :<a id="DisabilityPensions"  href="#" onClick="go(this.id)"> PWDs-Pensions Covered Disability Wise</a></p>
                                        --%> <!--  <p>R1.3 :<a id="sadaremnothavingaadhracardnos"  href="#" onClick="go(this.id)"> SADAREM ID Not Tagged To AADHARCARD No</a></p> -->
                                        <p>R1.3 :<a id="sadaremnothavingaadharnew"  href="#" onClick="go(this.id)"><%-- <span><img src="<%=request.getContextPath() %>/images/new-blink..gif" height="16"></span> --%> SADAREM ID Not Tagged To AADHARCARD No</a></p>
                                       <!--  <p>R1.3.1 :<a id="abstractsadaremnothavingaadhracardnos"  href="#" onClick="go(this.id)"> Abstract Report of SADAREM ID Not Tagged To AADHARCARD No</a></p> -->
                                        <p>R1.4 :<a id="rejectedAadharMapping"  href="#" onClick="go(this.id)"> Rejected AADHAR Mapping Report</a></p>
 
                                       <!--  <p>R1.6 :<a id="pwdValAbstReport"  href="#" onClick="go(this.id)"> PwD Validation Abstract Report</a></p> -->
  							<!-- 			 <p>R1.6 :<a id="pwdValAbstReport16"  href="#" onClick="go(this.id)"  > PwD Validation Abstract Report <font color="red">* New</font></a></p> -->
                                        </div>
                                    </div>
                                </td> 
                            </tr>
                            <tr>
                                <td> 
                                    <h2 class="expand_heading"><a href="#">R2: SADAREM Camp Not Attended PWD's</a></h2>
                                    <div class="toggle_container">
                                        <div class="box">
                                            <p>R2.1 : <a id="notComeToSadaremCamp" href="#" onClick="go(this.id)">SADAREM Camp Not Attended PWD's</a></p>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h2 class="expand_heading"><a href="#">R3: PWD's Demography Details Reports</a></h2>
                                    <div class="toggle_container">
                                        <div class="box"> 
                                           <!--  <p>R3.1 : <a id="education" href="#" onClick="go(this.id)">PWD's Educational wise - Details</a></p> -->
                                            <p>R3.1 : <a id="educationnew" href="#" onClick="go(this.id)"><%-- <span><img src="<%=request.getContextPath() %>/images/new-blink..gif" height="16"></span> --%>PWD's Educational wise - Details</a></p>
                                            <p>R3.2 : <a id="caste" href="#" onClick="go(this.id)">PWD's Caste Wise - Details</a></p>
                                            <p>R3.3 : <a id="age" href="#" onClick="go(this.id)">PWD's Age Wise - Details</a></p>
                                            <p>R3.4 : <a id="marital" href="#" onClick="go(this.id)">PWD's Marital Status Wise - Details</a></p>
                                           <!--  <p>R3.5 : <a id="empRep" href="#" onClick="go(this.id)">PWD's Employment Status - Details</a></p> -->
                                             <p>R3.5 : <a id="empRepnew" href="#" onClick="go(this.id)"><%-- <span><img src="<%=request.getContextPath() %>/images/new-blink..gif" height="16"></span> --%>PWD's Employment Status - Details</a></p>
                                            <p>R3.6 : <a id="parentsMarital" href="#" onClick="go(this.id)">Consanguineous Marriage of PWD's Parents - Details</a></p>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h2 class="expand_heading"><a href="#">R4: Criteria Details Reports</a></h2>
                                    <div class="toggle_container">
                                        <div class="box">
                                           <!--  <p>R4.1 : <a id="criteria" href="#" onClick="go(this.id)">Search by pension number / SADAREMID / Territory wise / Name/ Relative Name</a></p> -->
                                            <p>R4.1 : <a id="searchCriteria" href="#" onClick="go(this.id)">Search by Age and Gender/Type of disability/ Qualification/ Territory</a></p>
                                           <!--  <p>R4.2 : <a id="searchRationMember" href="#" onClick="go(this.id)">Ration card Members Details </a></p> -->
                                             <p>R4.2 : <a id="searchCriterianew" href="#" onClick="go(this.id)"><%-- <span><img src="<%=request.getContextPath() %>/images/new-blink..gif" height="16"></span> --%>Search by Age/Gender/Caste/Disability/Employment/Qualification/Territory</a></p> 
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="50%" valign="top">
                        <table width="100%" border="0" cellspacing="1" cellpadding="1" class="expand_wrapper" align="center">
                            <tr>
                                <td>
                                    <h2 class="expand_heading"><a href="#">R5: Appellate Authority Registred Status Report</a></h2>
                                    <div class="toggle_container">
                                        <div class="box">
                                            <p>R5.1 : <a id="appealRegister" href="#" onClick="go(this.id)">Appellate Authority Registred Status Report</a></p>
                                            <p>R5.2 : <a id="reAssessmentCampRep" href="#" onClick="go(this.id)">Re-Assessment Camp Wise Report</a></p>
                                             <!--   <p>R5.3 : <a id="expiredSadarem" href="#" onClick="go(this.id)">Expired SADAREM Certificates</a></p> -->
                                      <p>R5.3 : <a id="expiredSadaremnew" href="#" onClick="go(this.id)"><%-- <span><img src="<%=request.getContextPath() %>/images/new-blink..gif" height="16"></span> --%>Expired SADAREM Certificates</a></p>
                                       <!--    <p>R5.3.1 : <a id="districtexpiredSadarem" href="#" onClick="go(this.id)">District Wise Count of Expired SADAREM Certificates</a></p> --> 
                                    </div>
                                </td>
                            </tr>
                            <%--<tr>
                                <td>
                                    <h2 class="expand_heading"><a href="#">R7: Physical Requirements Report</a></h2>
                                    <div class="toggle_container">
                                        <div class="box">
                                            <p>R7.1 :<a id="physicalRequirementsReport" href="#" onClick="go(this.id)">Physical Requirements Report</a></p>
                                        </div>
                                    </div>
                                </td>
                         </tr>--%>
                            <tr>
                                <td>
                                    <h2 class="expand_heading"><a href="#">R6: RDCall Center SADAREM Grievances Report</a></h2>
                                    <div class="toggle_container">
                                        <div class="box">
                                            <p>R6.1 :<a id="GrievancesStatusReport" href="#" onClick="go(this.id)"> District and Status Wise Grievances Report</a></p>
                                            <p>R6.2 :<a id="GrievancesMonthReport" href="#" onClick="go(this.id)">District and Month Wise Grievances Report</a></p>
                                            <p>R6.3 :<a id="GrievancesRequestReport" href="#" onClick="go(this.id)">District and Request Type Wise Grievances Report</a></p>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h2 class="expand_heading"><a href="#">R7: SHG's Eligible Report</a></h2>
                                    <div class="toggle_container">
                                        <div class="box">
                                         <p>R7.1 :<a id="SHGEligibleReport" href="#" onClick="go(this.id)">SHG's Eligible Report</a></p>
                                        <%--<p>R7.1 :<a id="certificareReport" href="#" onClick="go(this.id)">certificareReport</a></p>--%>
                                        </div>
                                    </div>
                                </td>
                            </tr>

                             <tr>
                                <td>
                                    <h2 class="expand_heading"><a href="#">R8: Other Reports</a></h2>
                                    <div class="toggle_container">
                                        <div class="box">
                                      
                                        <!-- <p>R8.1 :<a id="certificateReport" href="#" onClick="go(this.id)">Certificate Upload Report</a></p> -->
                                       <p>R8.1 :<a id="certificateReportnew" href="#" onClick="go(this.id)"><%-- <span><img src="<%=request.getContextPath() %>/images/new-blink..gif" height="16"></span> --%>Certificate Upload Report</a></p>  
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </td>

                  
                </tr>
            </table>      </fieldset>
</td>

<td style="color:#000" valign="top">
<%
if (!(roleId.equals("23") || roleId.equals("24") || roleId.equals("25"))) 
{
%> 
 <div  style="float:right; display:inline; margin-right:5em; height:235px; width:304px;" class="box_lft_common_login">
                    <div class="portlet_tbl_bg"><div align="left" class="box_lft_headtxt">Alerts &amp; Updates</div></div>
                    <div class="scroller">
                        <marquee direction="up" scrollamount="1" height="170px" onmouseover="stop()" onmouseout="start()">
                            <strong>

                                <a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* New Grievance Part-B re-Entry is included in Issue Tracking system.</b></a> <br/><br/>
								<a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* New Grievance Sadarem ID Deletion is included in Issue Tracking system.</b></a> <br/><br/>
                                <a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* User Manual for beneficiary, for raising the grievances in Open Issue Tracking System.</b></a> <br/>
                                <a href="#"onclick="window.open('./DisabilityUITG/documents/OpenIssueTrackingSystem.pdf','','width=900,height=700,toolbar=no,scrollbars=yes');"class="topmenu">Click here to know more.</a>

                                <br/><br/> 

                                <a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* Usermanual for raising the grievance "Appellete authority at SPMU"  .</b></a> <br/>
                                <a href="#"onclick="window.open('./DisabilityUITG/documents/Manual_Appellate_Authority_Reassessment_Issue_Type.pdf','','width=900,height=700,toolbar=no,scrollbars=yes');"class="topmenu">Click here to know more.</a>
                                <br/><br/> 
                                
                                <a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* Usermanual for raising the grievance "Sadarem ID Deletion"  .</b></a> <br/>
                                <a href="#"onclick="window.open('./DisabilityUITG/documents/SadaremID Deletion.pdf','','width=900,height=700,toolbar=no,scrollbars=yes');"class="topmenu">Click here to know more.</a>
                                <br/><br/> 
                                
                                
                                <a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* Newly added Ration Card Serial Number Updated Report.</b></a> <br/>
                                <a href="#"onclick="window.open('./DisabilityUITG/documents/RationCardSerialnoupdatedUserManual.pdf','','width=900,height=700,toolbar=no,scrollbars=yes');"class="topmenu">Click here to know more.</a>
                                <br/><br/>

                                <a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* Newly added Appellate Authority Schedule Form and Appellate Assessment Process.</b></a> <br/>
                                <a href="#"onclick="window.open('./DisabilityUITG/documents/AppealAuthority_Schdule_Assessmnet_Process.pdf','','width=900,height=700,toolbar=no,scrollbars=yes');"class="topmenu">Click here to know more.</a>
                                <br/><br/>

                                <a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* Newly added Railway Concession certificate through SADAREM Application.</b></a> <br/>
                                <a href="#"onclick="window.open('./DisabilityUITG/documents/Railway_Concession_certificate_Guidelines.pdf','','width=900,height=700,toolbar=no,scrollbars=yes');"class="topmenu">Click here to know more.</a>
                                <br/><br/>

                                <a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* Newly added Options in SADAREM for Railway Concession.</b></a> <br/>
                                <a href="#"onclick="window.open('./DisabilityUITG/documents/Option_in_SADAREM_Guidelines.pdf','','width=900,height=700,toolbar=no,scrollbars=yes');"class="topmenu">Click here to know more.</a>
                                <br/><br/>

                                <a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* Newly added Appellate Authority Schedule & Assessment.</b></a> <br/>
                                <a href="#"onclick="window.open('./DisabilityUITG/documents/AppealAuthority_Schdule_Assessmnet_Process.pdf','','width=900,height=700,toolbar=no,scrollbars=yes');"class="topmenu">Click here to know more.</a>
                                <br/><br/>

                                <a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* Newly added Appellate Authority Registration.</b></a> <br/>
                                <a href="#"onclick="window.open('./DisabilityUITG/documents/Appellate_Authority_Registration.pdf','','width=900,height=700,toolbar=no,scrollbars=yes');"class="topmenu">Click here to know more.</a>
                                <br/><br/>


                                <a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* Newly added Railway Concession Certificate.</b></a> <br/>
                                <a href="#"onclick="window.open('./DisabilityUITG/documents/RailwayConcession.pdf','','width=900,height=700,toolbar=no,scrollbars=yes');"class="topmenu">Click here to know more.</a>
                                <br/><br/>

                                <a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* Newly added Permission for Employee Details Entry Form.</b></a> <br/>
                                <a href="#"onclick="window.open('./DisabilityUITG/documents/EmplyeeEntryForm.pdf','','width=900,height=700,toolbar=no,scrollbars=yes');"class="topmenu">Click here to know more.</a>
                                <br/><br/>


                                <a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* Newly added General Needs Reports for all Disabilities.</b></a> <br/>
                                <a href="#"onclick="window.open('./DisabilityUITG/documents/GeneralNeedsReport.pdf','','width=900,height=700,toolbar=no,scrollbars=yes');"class="topmenu">Click here to know more.</a>
                                <br/><br/>


                                <a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* Newly added Who are not come to SADAREM Camp and Only Part-A Assessed.</b></a> <br/>
                                <a href="#"onclick="window.open('./DisabilityUITG/documents/WhoarenotcometoSADAREM.pdf','','width=900,height=700,toolbar=no,scrollbars=yes');"class="topmenu">Click here to know more.</a>
                                <br/><br/>
                                
                                <a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* Newly added Functional Needs Reports for all Disabilities.</b></a> <br/>
                                <a href="#"onclick="window.open('./DisabilityUITG/documents/FunctionalNeedsReport.pdf','','width=900,height=700,toolbar=no,scrollbars=yes');"class="topmenu">Click here to know more.</a>
                                <br/><br/>

                                <%-- <a style="text-decoration:none;" class="newsMessage"><b>* Newly added RailWay Concession form for Person with disability.</b></a> <br/>
                                <a href="#"onclick="window.open('./DisabilityUITG/documents/RailwayConcession.doc','','width=900,height=700,toolbar=no,scrollbars=yes');"class="topmenu">Click here to know more.</a>
                                <br/><br/> --%>
                                <a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* Newly added PDF Generation for Doctor Entered Fields.
                                    </b></a><br/>
                                <a href="#"onclick="window.open('./DisabilityUITG/documents/PDFGeneration.pdf','','width=900,height=700,toolbar=no,scrollbars=yes');"class="topmenu">Click here to know more.</a>
                                <br/><br/>
                                <a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* Issue Tracking Flow Manual for Mandal and CAMP-ADMIN logins.</b></a> <br/>
                                <a href="#"onclick="window.open('./DisabilityUITG/documents/ManualManda_CampAdmin.pdf','','width=900,height=700,toolbar=no,scrollbars=yes');"class="topmenu">Click here to know more.</a>
                                <br/><br/>
                                <a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* Issue Tracking Flow Manual for DPM logins.</b></a> <br/>
                                <a href="#"onclick="window.open('./DisabilityUITG/documents/Manual_DPM.pdf','','width=900,height=700,toolbar=no,scrollbars=yes');"class="topmenu">Click here to know more.</a>
                                <br/><br/>
                                <a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* Issue Tracking Flow Manual for PD, SPM and Director logins.</b></a> <br/>
                                <a href="#"onclick="window.open('./DisabilityUITG/documents/Manual_for_PD_SPMUSERP_DIRECTOR.pdf','','width=900,height=700,toolbar=no,scrollbars=yes');"class="topmenu">Click here to know more.</a>
                                <br/><br/>

                                <a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* Please Enter Required Funtional Needs Assesment for Person With Disability.</b></a> <br/><br/>

                              <!--   <a style="text-decoration:none;" class="newsMessage" style="color:#000"><b>* Please Enter Part-A Details Properly.</b></a> <br/><br/> -->

                            </strong>
                        </marquee>
                    </div></div> <%} %></td>

        <!-- <tr>
             <td  align="center" class="heading">Welcome to </td>
         </tr>
         <tr>
             <td align="center" class="subHeading"> Software for Assessment of Disabled for Access Rehabilitation and Empowerment</td>
         </tr>-->
      
</tr>








    </table>
 

</html:form>
</BODY>
</HTML>
