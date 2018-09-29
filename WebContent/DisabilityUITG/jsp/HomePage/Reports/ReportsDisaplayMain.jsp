<% 
if(session.getAttribute("roleId") !=null && !session.getAttribute("roleId").toString().equals(""))
{	
%>

<html>
    <head>
        <title>SADAREM</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

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
                }else if(id == "education"){
                    window.open("educationWiseReports.do");
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
                } else if(id == "empRep"){
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
                }else if(id == "searchReport"){
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
                
                else if(id == "SHGEligibleReport"){
                    window.open("sHGEligibleReport.do?mode=getSHGEligible");
                }
                else if(id == "dashboard"){
                    window.open("dashboardReport.do?mode=gerDashBoardReport6");
                }else if(id == "director"){
                    window.open("directorReportOutside.do");
                }else if(id == "cmDashBoard"){
                    window.open("dashboardReport.do");
                }else if(id == "DisabilityPensions"){
                    window.open("PwdsDisabilityPension.do?mode=getDisabilityPensionsDetails");
                }else if(id == "sadaremnothavingaadhracardnos"){
                    window.open("sadaremidNothavingaadhracardNos.do");
                }else if(id == "dataValidation"){
                    window.open("dataValidationReport.do");
                }else if(id == "pwdValAbstReport"){
                    window.open("pwdValAbstReport.do");
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
            .toggle_container img {
                float: left;
                margin: 10px 15px 15px 0;
                padding: 5px;
                background: #ddd;
                border: 1px solid #ccc;
            }
            h3 {background-color:#626262; color:#fff; padding:5px; text-align:center; font-size:12px;}


        </style>

    </head>

    <BODY onLoad="doMenubody('none');LinksManagement();">
        <html:form action="districtstatusreportforPartA.do?distWisestatusreportforPartA=distWisestatusreportforPartA"
                   styleId="partAForm" onsubmit="return validate_form(this)" style="margin:0px; padding:0px;">

            <h3>REPORTS</h3>

            <table width="100%" border="0" cellspacing="0" cellpadding="5" class="wrapper" align="center" >
                <tr>
                    <td width="50%" valign="top">
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
                                        --%>         <p>R1.1 :<a id="dashboard"  href="#" onClick="go(this.id)"> SADAREM Assessment Status Report</a></p>
                                        <%--    <p>R2.7 :<a id="Persentage"  href="#" onClick="go(this.id)"> Percentage Wise Assessed Report</a></p>--%>
                                        <p>R1.2 :<a id="director"  href="#" onClick="go(this.id)"> SADAREM Assessment Report Date and Camp Wise</a></p>
                                        <%--    <p>R2.9 :<a id="cmDashBoard"  href="#" onClick="go(this.id)"> SADAREM Assessment,Pensions,Percentage Report</a></p>
                                            <p>R2.10 :<a id="DisabilityPensions"  href="#" onClick="go(this.id)"> PWDs-Pensions Covered Disability Wise</a></p>
                                        --%>            <p>R1.3 :<a id="sadaremnothavingaadhracardnos"  href="#" onClick="go(this.id)"> SADAREM ID Not Tagged To AADHARCARD No</a></p>
                                        <p>R1.4 :<a id="rejectedAadharMapping"  href="#" onClick="go(this.id)"> Rejected AADHAR Mapping Report</a></p>
                                        <p>R1.5 :<a id="dataValidation"  href="#" onClick="go(this.id)"> PwD Data Validation Report</a></p>
                                       <!--  <p>R1.6 :<a id="pwdValAbstReport"  href="#" onClick="go(this.id)"> PwD Validation Abstract Report</a></p> -->
  										 <p>R1.6 :<a href="pwdValAbstReport16.do"> PwD Validation Abstract Report <font color="red">* New</font></a></p>
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
                                            <p>R3.1 : <a id="education" href="#" onClick="go(this.id)">PWD's Educational wise - Details</a></p>
                                            <p>R3.2 : <a id="caste" href="#" onClick="go(this.id)">PWD's Caste Wise - Details</a></p>
                                            <p>R3.3 : <a id="age" href="#" onClick="go(this.id)">PWD's Age Wise - Details</a></p>
                                            <p>R3.4 : <a id="marital" href="#" onClick="go(this.id)">PWD's Marital Status Wise - Details</a></p>
                                            <p>R3.5 : <a id="empRep" href="#" onClick="go(this.id)">PWD's Employment Status - Details</a></p>
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
                                            <p>R4.1 : <a id="criteria" href="#" onClick="go(this.id)">Search by pension number / SADAREMID / Territory wise / Name/ Relative Name</a></p>
                                            <p>R4.2 : <a id="searchCriteria" href="#" onClick="go(this.id)">Search by Age and Gender/Type of disability/ Qualification/ Territory</a></p>
                                            <p>R4.3 : <a id="searchRationMember" href="#" onClick="go(this.id)">Ration card Members Details </a></p>
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
                                        </div>
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
                                      
                                        <p>R8.1 :<a id="certificateReport" href="#" onClick="go(this.id)">Certificate Upload Report</a></p>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>

        </html:form>





    </BODY>
    <html>
<%
}
else
{
%>
	<html>
	<head>
	<title>:: SADAREM ::</title>
	</head>
	<body>
	
	<div style="width: 100%;height:300px;align:center;valign: middle;">
	
		<center>
			<h2 style="color: red;">You are not authorized to access</h2>
			<br/><br/>
			<font color="blue"><b>Please login to access</b></font>
		</center>
			
	</div>
	</body>
	
	</html>
<%
}
%>
