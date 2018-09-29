<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Meta Data -->
    <meta charset="utf-8">
    <title>SADAREM</title>
    
    <style>
    
    
    col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3, .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9 {
     padding-right:5px!important;
    padding-left: 0px!important;
   </style>
    
</head>

<body> 
<div class="container-fluid" style="min-height: 400px!important;">
        <!--Begin Wrapper-->
        <div id="wrapper">

            <!--Begin Header-->
            
            <!--End Header-->

            <!--Begin body-->
            <!--End 2nd Row-->

<!-- 		   <div style="color:red!important; font-size:18px;"> -->
<!-- 				              <MARQUEE onmouseover="this.scrollAmount='0'" onmouseout="this.scrollAmount='6'" scrollAmount="6" scrollDelay="2" direction="left"> -->
<!-- 				                    SADAREMTG Website will be down due to planned maintenance activity  from 23-Oct-2015 09:00 PM(Friday) to 25-Oct-2015 09:00PM(Sunday). -->
<!-- 				                    We regret the inconvenience caused. -->
				                
<!-- 				               </MARQUEE> -->
				       
<!-- 		   </div> -->
            <div class="row" style="margin-top:10px; margin:2px;"> 
               
				<%@include file="/common/sadaremcountgraph.jsp" %>
            </div>

            <!--End 2nd Row-->


            <!--3rd Row-->

            <div class="row" style="margin:2px; padding-right:0px !important; padding-left:0px !important;">
                <div class="col-md-3" style="padding-right:5px !important;">
                    <div class="Notification-block" style="background-color:#fcb406 !important;">
					<div >
                        <div style=" height: 36px; float:left;">
                            <img src="<%=request.getContextPath()%>/images/notifications_icon.png" width="32" height="32" /></div>
                        <div style="padding-left: 6px; font-size: 14px; padding-top: 2px; color: #000; font-weight: bold; float:left;">
                            Notifications
                         </div>
						</div>
                        <div style="background-color: #FFF; height: 180px; margin-top:40px;">
                        	<marquee direction="up" behavior="scroll" scrolldelay="2" scrollamount="2" height="179px">
	                            <ul style="list-style-type: none; margin-left:0px; padding-left:0px;">
	                                <li>
	                                	<a href="#" class="Notifilink">
	                                		Welcome to SADAREM Portal
	                                	</a>
	                                </li>
	                            </ul>
                           </marquee>
                        </div>

                    </div>
                </div>
                <div class="col-md-5" style="padding-right:5px !important;   width:48% !important">

                    <div id="main-carousel" class="carousel slide" data-ride="carousel">

                        <div class="carousel-inner">
                            <div class="item active">
                                <img src="<%=request.getContextPath()%>/images/img1.jpg" alt="main-slider-1" />

                            </div>

                            <div class="item"><img src="<%=request.getContextPath()%>/images/img2.jpg" alt="main-slider-2" /></div>


                        </div>
                        <a class="left carousel-control" href="#main-carousel" role="button" data-slide="prev"><i class="fa fa-angle-left"></i></a>
                        <a class="right carousel-control" href="#main-carousel" role="button" data-slide="next"><i class="fa fa-angle-right"></i></a>
                    </div>
                </div>
                <div class="col-md-3" style="padding-left:0px !important; padding-right:0px !important;  margin-left:0px;">
	                    <div class="Notification-block" style="background-color:#77c0e6 !important; padding:10px; border-bottom:#77c0e6 solid 1px;"> 
	                        <div style="background-color: #FFF;">
	                            <div style="float: left; font-size: 16px; padding-top: 7px; color: #000; padding-left:10px; font-weight: bold;">
	                                About SADAREM
	                            </div>
	                            <br/>
	                            <div style="background-color: #FFF; height: 180px; margin-top:10px;">
	                                <p style="padding:5px;font-face:Times New Roman;text-align:left;  padding-left:10px; font-size: 13px; line-height: 20px;">
	                                	Objective of the SADAREM initiative is to create a Dynamic Web enable system for comprehensive access, rehabilitation and empowerment, through automation, capacity building, assessment of persons with disabilities (PWDs) and maintaining Decision Support System (DSS).
	                                </p>
	                                <div style="float:right;">
	                                	<a href="<%=request.getContextPath()%>/aboutHome.do"><img src="<%=request.getContextPath()%>/images/read_more.png" width="89" height="23"></a>
	                                </div>
	                            </div>
	                        </div>
	                     </div>
                    </div>
                </div>
            </div>


            <div class="row" style="margin:2px;  padding-right:0px !important; padding-left:0px !important;">
                <div class="col-md-12">
                    <div style="padding-bottom:10px;padding-top:15px;">
                        <article id="demo-accordion-01" class="z-demo-accordion" data-role="z-accordion" data-options='{"active": 2,"responsive": true,"orientation": "horizontal",  "theme": "silver", "shadows": true,"bordered": true, "slider": true, "rounded": false, "width": 1600, "height":350, "dotNav":true, "containerHeight": 320, "headerSize":68, "animation": {"duration":400, "easing": "easeInOutQuart"}, "showIcons": true,"headerFontSize": 1.6, "autoplay": {"interval":6000}}'>
                            <section style="background:#3bcce7 !important; " >

                                <h3 style="background:#3bcce7 !important; border-bottom:#0f8095 solid 1px;color:#fff !important;">Gos & Acts</h3>

                                <div style="background-color:#3bcce7">
                                    <div class="wbg" style="overflow-y:scroll;">
                                    <table border="0" cellspacing="1" cellpadding="0"  class="table table-striped table-bordered table-hover table-condensed">
                
							                <tr>
							                    <th colspan="9">SADAREM GO's</th>
							                </tr>
							                <tr>
							                    <th width="6%">S.No.</th>
							                    <th width="22%">G.O. No.</th>
							                    <th width="16%">G.O. Date</th>
							                    <th width="50%" style="text-align:center; padding-left:15px;">Description</th>
							                      <th width="16%">Download</th>
							                </tr>
							                
							                    <tr>
							                        <td style="text-align:center;">1.</td>
							                        <td align="center" style="text-align:left; vertical-align: middle;">
							                           G.O.Ms.No. 371
							                        </td>
							                        <td align="center" style="text-align:center; vertical-align: middle;">02/12/2010</td>
							                        <td style="text-align:left; vertical-align: middle;">Social Security Pensions - Assessment of degree of disability through software -  Guidelines for assessment - Issue of certificates - Orders - Issued.</td>
							                        
							                           <td align="center" style="text-align:center; vertical-align: middle; ">
							                            <a target="_blank" href="<%=request.getContextPath()%>/Gos.do?mode=downloadFileHomePage&amp;id=G.O.Ms.No. 371" style="text-decoration: none"><img src="<%=request.getContextPath() %>/images/download.png" style="border: 0px"></a>
							                        </td>
							                    </tr>
							                
							                    <tr>
							                        <td style="text-align:center;">2.</td>
							                        <td align="center" style="text-align:left; vertical-align: middle;">
							                           G.O.Ms.No.31
							                        </td>
							                        <td align="center" style="text-align:center; vertical-align: middle; ">01/12/2009</td>
							                        <td style="text-align:left;">
							                        		Women Development, Child Welfare and Disabled Welfare Department -  Persons with Disabilities - Guidelines for evaluation of various disabilities and  procedure for certification -Comprehensive Orders -Issued.
							                        </td>
							                        <td align="center" style="text-align:center; vertical-align: middle;">
							                            <a target="_blank" href="<%=request.getContextPath()%>/Gos.do?mode=downloadFileHomePage&amp;id=G.O.Ms.No.31" style="text-decoration: none"><img src="<%=request.getContextPath() %>/images/download.png" style="border: 0px"></a>
							                        </td>
							                    </tr>                

           									</table>
           							</div>
                                </div>
                            </section>
                            <section>
                                <h3 style="background:#f4ca02 !important;  border-bottom:#a58a0c solid 1px;color:#fff !important;">Govt Websites </h3>
                                <div style="background-color:#f4ca02">
                                    <div class="wbg"  style="overflow-y:scroll;">
                                    <table  align="center" border="0" cellspacing="0" cellpadding="0" class="table table-striped table-bordered table-hover table-condensed">
            <tbody><tr><th colspan="6">Important Links</th></tr>
            <tr align="center">
                <th>S.No.</th>
                <th>Website eName</th>
                <th>Website Link</th>



            </tr>
            

                <tr align="left">
                    <td style="text-align: center">
                        1.
                    </td>
                    <td style="text-align: left">Ministry of Social justice and Empowerment</td>
                    <td style="text-align: center"><a href="http://www.socialjustice.nic.in/" target="_blank">LINK</a></td>


                </tr>
            

                <tr align="left">
                    <td style="text-align: center">
                        2.
                    </td>
                    <td style="text-align: left">The National Trust</td>
                    <td style="text-align: center"><a href="http://www.thenationaltrust.in/" target="_blank">LINK</a></td>


                </tr>
            

                <tr align="left">
                    <td style="text-align: center">
                        3.
                    </td>
                    <td style="text-align: left">Rehabilitation Council of India</td>
                    <td style="text-align: center"><a href="http://www.rehabcouncil.nic.in/" target="_blank">LINK</a></td>


                </tr>
            

                <tr align="left">
                    <td style="text-align: center">
                        4.
                    </td>
                    <td style="text-align: left">Office of The Chief Commissioner for Persons with  Disabilities</td>
                    <td style="text-align: center"><a href="http://www.ccdisabilities.nic.in/" target="_blank">LINK</a></td>


                </tr>
            

                <tr align="left">
                    <td style="text-align: center">
                        5.
                    </td>
                    <td style="text-align: left">National Handicapped Finance and Development Corporation</td>
                    <td style="text-align: center"><a href="http://www.nhfdc.org/" target="_blank">LINK</a></td>


                </tr>
            

                <tr align="left">
                    <td style="text-align: center">
                        6.
                    </td>
                    <td style="text-align: left">Ali Yavar Jung National Institute for the Hearing Handicapped</td>
                    <td style="text-align: center"><a href="http://ayjnihh.nic.in/index.asp" target="_blank">LINK</a></td>


                </tr>
            

                <tr align="left">
                    <td style="text-align: center">
                        7.
                    </td>
                    <td style="text-align: left">National Institute for the Mentally Handicapped</td>
                    <td style="text-align: center"><a href="http://www.nimhindia.org/" target="_blank">LINK</a></td>


                </tr>
            

                <tr align="left">
                    <td style="text-align: center">
                        8.
                    </td>
                    <td style="text-align: left">National Institute of Rehabilitation Training and Research</td>
                    <td style="text-align: center"><a href="http://www.nirtar.nic.in/" target="_blank">LINK</a></td>


                </tr>
            

                <tr align="left">
                    <td style="text-align: center">
                        9.
                    </td>
                    <td style="text-align: left">National Institute for the Visually Handicapped</td>
                    <td style="text-align: center"><a href="http://www.nivh.in/" target="_blank">LINK</a></td>


                </tr>
            

                <tr align="left">
                    <td style="text-align: center">
                        10.
                    </td>
                    <td style="text-align: left">Pt. Deendayal Updadhyaya Institute for the Physically Handicapped</td>
                    <td style="text-align: center"><a href="http://www.iphnewdelhi.in/" target="_blank">LINK</a></td>


                </tr>
            

                <tr align="left">
                    <td style="text-align: center">
                        11.
                    </td>
                    <td style="text-align: left">Artificial Limbs Manufacturing Corporation of India</td>
                    <td style="text-align: center"><a href="http://www.artlimbs.com/" target="_blank">LINK</a></td>


                </tr>
            


        
    </tbody></table></div>
                                </div>
                            </section>
                            <section>
                                <h3 style="background:#c77b41 !important;  border-bottom:#a6500f solid 1px; color:#fff !important;">Documents</h3>
                                <div style="background-color:#c77b41">
                                    <div class="wbg"  style="overflow-y:scroll;"><table border="0" cellspacing="1" cellpadding="0"  class="table table-striped table-bordered table-hover table-condensed">

             <tbody><tr>
                <th colspan="2">SADAREM Documents</th><th>
              </th></tr>
   
                    <tr>
                        <th>Sl.No</th>
                        <th>Description</th>
                        <th>Download Link</th>
                    </tr>

                    <tr>
                        <td>1</td>
                        <td>SCHEME OF ASSISTANCE TO DISABLED PERSONS FOR PURCHASE / FITTING
                            OF AIDS / APPLIANCES</td>
                        <td><a target="_blank" href="./DisabilityUITG/Downloads/Docs/ADIP_scheme.pdf">Download</a></td>
                    </tr>

                    <tr>
                        <td>2</td>
                        <td>Centrally Sponsored Scheme (CSS) of 'Inclusive Education of the<br>
                            Disabled at Secondary Stage (IEDSS)'</td>
                        <td><a target="_blank" href="./DisabilityUITG/Downloads/Docs/IEDSS.pdf">Download</a></td>
                    </tr>
               
    </tbody></table></div>
                                </div>
                            </section>
                            <section>
                                <h3 style="background:#377c9c !important;  border-bottom:#096793 solid 1px; color:#fff !important;">Circulars</h3>
                                <div style="background-color:#377c9c">
                                    <div class="wbg"  style="overflow-y:scroll;"><table align="center" cellpadding="0" cellspacing="0" border="0"  class="table table-striped table-bordered table-hover table-condensed">
                            
                            <tbody><tr>
                                <td colspan="5" align="left">
                                    <input type="text" name="filt" size="18" value="" onmousedown="disableText();" onkeyup="filter(this, 'mohan', '1')" >
                                </td>
                            </tr>
                            <tr id="firstRow">
                                <th>
                                    S.No.
                                </th>
                                <th>
                                    Go / Circular / Memo Number
                                </th>
                                <th>
                                    Description
                                </th>
                                <th>
                                   Download
                                </th>
                            </tr>
                            
                                <tr align="center">
                                  <td>
                                   1
                                </td>
                                <td>
                                     24/SERP/TS/PwD/2015
                                </td>
                                <td align="left">
                                    Conduct of Screening camp on 12.12.2015 at Nizamabad District and mobilization of screening physically disabled<br> patients for surgical correction at Aakar Aasha Hospital
                                </td>
                                  <td><a target="_blank" href="./DisabilityUITG/Downloads/GOs/SADAREM/24_SERPTS_PWD_04_12_15.pdf">Download</a></td>
                                
                                </tr>
                                <tr align="center">
                                  <td>
                                   2
                                </td>
                                <td>
                                     PWD/10/SADAREM/2014
                                </td>
                                <td align="left">
                                     SERP - ID for PwD - comprehensive guidelines for ensuring proper conduct of disability assessment and issue of disability certificates through "SADAREM" software.
                                </td>
                                  <td><a target="_blank" href="./DisabilityUITG/Downloads/GOs/SADAREM/All_TS_Districts_Collectors_on_22.11.20160001.pdf">Download</a></td>
                                
                                </tr>
                                <tr align="center">
                                  <td>
                                   3
                                </td>
                                <td>
                                     146/SADAREM/TVVP/2016
                                </td>
                                <td align="left">
                                    TS - SERO - ID for PwD - Formation of new Medical Boads for Assessment of Degree of disability through SADAREM Software - Instructions - Issued.
                                </td>
                                  <td><a target="_blank" href="./DisabilityUITG/Downloads/GOs/SADAREM/TS_SADAREM_on_22.11.20160001.pdf">Download</a></td>
                                
                                </tr>
                            

                            
                        </tbody></table></div>
                                </div>
                            </section>
                            <section>
                                <h3 style="background:#7cc400 !important;  border-bottom:#5c811d solid 1px; color:#fff !important;">Awards </h3>
                                <div style="background-color:#7cc400">
                                    <div class="wbg">
        
				        				<table border="0" cellspacing="0" cellpadding="0" width="96%" class="table-responceie">
				                                    <tr>
				                                   		 <td align="center" colspan="2"><h3> Awards &amp; Achievements </h3	></td>
				                                	</tr>
				                        			<tr>
				                           			 <td align="center" valign="middle" >
							                                <table width="80%" border="0" cellspacing="0" cellpadding="0" height="165" style="border:1px #8dbccf solid;">      <tbody>
							                                    <tr>
							                                        <td align="center" valign="middle" background="<%=request.getContextPath()%>/images/display-ban.gif" style="background-position:left top; background-repeat:no-repeat;">
							                                        <img src="<%=request.getContextPath()%>/images/manthan-banner.png" width="244" height="122">
							                                        </td>
							                                    </tr>
							                              </table>
							                              </td>
							                             <td width="19%" align="center" valign="top">
							                             <img src="<%=request.getContextPath()%>/images/Awards.gif">
							                             </td>
							                       </tr>
				                   			</table>
        
   									 </div>
   
                                </div>
                            </section>
                        </article>
                    </div>
                </div>
            </div>

          
        </div>
</div>

    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/google-analytics.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/scripts.min.js"></script>
</body>

</html>