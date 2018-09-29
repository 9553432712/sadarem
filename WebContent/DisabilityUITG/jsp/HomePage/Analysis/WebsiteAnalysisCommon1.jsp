<%--
    Document   : WebsiteAnalysisCommon1
    Created on : Mar 17, 2014, 3:12:20 PM
    Author     : 747577
--%>


<html>
    <head>
        <title>SADAREM</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

        <script>

            function clickOnLink(id){
                if(id == "niramayaReport"){
                    window.open("getDistrictidForNiramaya.do?getDistrictReportNiramaya=getDistrictReportNiramaya");
                } else if(id == "analysisReport"){
                    window.open("analysisReport.do");
                } else if(id == "generalNeedsReport"){
                    window.open("generalNeedsReport.do");
                }
                else if(id == "TotalReport"){
                    window.open("TotalReports1.do");
                }else if(id=="oh"){
                    window.open("OHReports.xls?OHReports=OHReports");
                }else if(id=="ohf"){
                    window.open("LocomotorFunctiionalneedReports.do?LocomotorReports=LocomotorFunctiionalneedReports");
                }else if(id=="vi"){
                    window.open("VIReports.do?VIReports=VIReports");
                }else if(id=="vif"){
                    window.open("VIFunctiionalneedReports.do?VIFunctiionalneedReports=VIFunctiionalneedReports");
                }else if(id=="hi"){
                    window.open("HIReports.do?HIReports=HIReports");
                }else if(id=="hif"){
                    window.open("HIFunctiionalneedReports.do?HIFunctiionalneedReports=HIFunctiionalneedReports");
                }else if(id=="mr"){
                    window.open("MRReports.do?MRReports=MRReports");
                }else if(id=="mrf"){
                    window.open("MRFunctiionalneedReports.do?MRFunctiionalneedReports=MRFunctiionalneedReports");
                }else if(id=="mi"){
                    window.open("MIReports.do?MIReports=MIReports");
                }else if(id=="mif"){
                    window.open("MIFunctiionalneedReports.do?MIFunctiionalneedReports=MIFunctiionalneedReports");
                }else if(id=="lds"){
                    window.open("subTypeReport.do?&ids=lds");
                }else if(id=="vids"){
                    window.open("subTypeReport.do?&ids=vids");
                }else if(id=="hids"){
                    window.open("subTypeReport.do?&ids=hids");
                }else if(id=="mrds"){
                    window.open("subTypeReport.do?&ids=mrds");
                }else if(id=="mids"){
                    window.open("subTypeReport.do?&ids=mids");
                }


            }
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
    </head>

    <BODY onLoad="doMenubody('none');LinksManagement();">
    <html:form action="districtstatusreportforPartA.do?distWisestatusreportforPartA=distWisestatusreportforPartA"
               styleId="partAForm" style="padding:0px; margin:0px;" onsubmit="return validate_form(this)">



        <table width="100%" border="0" cellspacing="0" cellpadding="5" class="wrapper" align="center" >
            <tr>
                <td width="50%" valign="top">
                    <table width="100%" border="0" cellspacing="1" cellpadding="1" class="expand_wrapper" align="center">
                        <tr>
                            <td>
                                <h2 class="expand_heading"><a href="#">A1 : Report For Health Insurance Scheme</a></h2>
                                <div class="toggle_container">
                                    <div class="box">
                                        <p>A1.1 : <a href="javascript:void(0);" onClick="clickOnLink(this.id);" id="niramayaReport">Niramaya Health Insurance Scheme </a></p>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h2 class="expand_heading"><a href="#">A2 : Functional Needs Reports</a></h2>
                                <div class="toggle_container">
                                    <div class="box">
                                        <p> A2.1 : <a href="#" onclick="clickOnLink(this.id);" id="analysisReport">Specific Needs</a></p>
                                        <p> A2.2 : <a href="#" onclick="clickOnLink(this.id);" id="generalNeedsReport">General Needs </a></p>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h2 class="expand_heading"><a href="#">A3 : General Profile of PwDs (All Disabilities)</a></h2>
                                <div class="toggle_container">
                                    <div class="box">
                                        <p>A3.1 : <a href="#" onclick="clickOnLink(this.id);" id="TotalReport">General Profile of Eligible PwDs (All Disabilities)</a></p>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h2 class="expand_heading"><a href="#">A4 : OH (Percentage And Specific of Disabilities)</a></h2>
                                <div class="toggle_container">
                                    <div class="box">
                                        <p>A4.1 : <a href="#" onclick="clickOnLink(this.id);" id="oh">Causes, subtypes, Percentages</a></p>
                                        <p>A4.2 : <a href="#" onclick="clickOnLink(this.id);" id="ohf">Functional needs</a></p>
                                        <p>A4.3 : <a href="#" onclick="clickOnLink(this.id);" id="lds">Disability Sub Type Report</a></p>
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
                                <h2 class="expand_heading"><a href="#">R5: VI (Percentage And Specific of Disabilities)</a></h2>
                                <div class="toggle_container">
                                    <div class="box">
                                        <p>A5.1 : <a href="#" onclick="clickOnLink(this.id);" id="vi">Causes, Performance ability, Categories, Percentages</a></p>
                                        <p>A5.2 : <a href="#" onclick="clickOnLink(this.id);" id="vif">Functional needs</a></p>
                                        <p>A5.3 : <a href="#" onclick="clickOnLink(this.id);" id="vids">Disability Sub Type Report</a></p>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h2 class="expand_heading"><a href="#">A6: HI (Percentage And Specific of Disabilities)</a></h2>
                                <div class="toggle_container">
                                    <div class="box">
                                        <p>A6.1 : <a href="#" onclick="clickOnLink(this.id);" id="hi">Causes, Performance ability, Percentages</a></p>
                                        <p>A6.2 : <a href="#" onclick="clickOnLink(this.id);" id="hif">Functional needs</a></p>
                                        <p>A6.3 : <a href="#" onclick="clickOnLink(this.id);" id="hids">Disability Sub Type Report</a></p>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h2 class="expand_heading"><a href="#">A7: MR (Percentage And Specific of Disabilities)</a></h2>
                                <div class="toggle_container">
                                    <div class="box">
                                        <p>A7.1 : <a href="#" onclick="clickOnLink(this.id);" id="mr">Causes, Performance ability, Percentages</a></p>
                                        <p>A7.2 : <a href="#" onclick="clickOnLink(this.id);" id="mrf">Functional needs</a></p>
                                        <p>A7.3 : <a href="#" onclick="clickOnLink(this.id);" id="mrds">Disability Sub Type Report</a></p>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h2 class="expand_heading"><a href="#">A8: MI (Percentage And Specific of Disabilities)</a></h2>
                                <div class="toggle_container">
                                    <div class="box">
                                        <p>A8.1 : <a href="#" onclick="clickOnLink(this.id);" id="mi">Causes, Percentages</a></p>
                                        <p>A8.2 : <a href="#" onclick="clickOnLink(this.id);" id="mif">Functional needs</a></p>
                                        <p>A8.3 : <a href="#" onclick="clickOnLink(this.id);" id="mids">Disability Sub Type Report</a></p>
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
