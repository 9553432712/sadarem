
<style>
.col
{
	padding-right:0px!important;
	padding-left:0px!important;
}
.navbar-collapse
 {
    padding-right: 5px !important;
    padding-left: 0px !important;
     padding-right: 0px !important;
    padding-left: 0px !important;
	background-image: url('<%=request.getContextPath()%>/images/but_bg.png') !important ;
	background-repeat : repeat-x !important; 
	height:30px!important;
}

.navbar-nav>li>a 
{
    padding-top: 5px !important;
    padding-bottom: 0px !important;
    height: 30px !important;
    vertical-align: middle;
    font-weight:bold;
}

.row
{
	margin-right:0px !important;
	margin-left:0px !important;
}


</style>

<script type="text/javascript">

var screenWidth = window.screen.width;
var screenHeight = window.screen.height;

    function openRequestedPopup()
    {
        var windowObjectReference;
        var strWindowFeatures = "menubar=no,location=no,resizable=no,scrollbars=yes,status=yes,target=_parent";
        windowObjectReference = window.open("login.do", "SADAREM", strWindowFeatures);
    }
    
    function fun_loadQuickSearch(targ)
    {   
	   
    	var mywind=window.open("<%=request.getContextPath()%>/loadsearchsadaremdtls.do", targ, "width="+screenWidth+", height="+screenHeight+",scrollbars,resizable,screeny=top,toolbar=NO,location=no,directories=no,status=yes,scrollbars=yes,titlebar=no,addressbar=no,left=0,top=0,target=_blank");
    	mywind.focus();
    }
	
    function createTarget(t)
    {   
    	var wind=window.open("<%=request.getContextPath()%>/login.do", t, "width="+screenWidth+", height="+screenHeight+",scrollbars,resizable,screeny=top,toolbar=NO,location=no,directories=no,status=yes,scrollbars=yes,titlebar=no,addressbar=no,left=0,top=0,target=_blank");
    	wind.focus();
    }
    function fun_openIssueTrackingSystem(targ)
    {   
	   
    	var mywind=window.open("<%=request.getContextPath()%>/openIsuueTrackinghome.do", targ, "width="+screenWidth+", height="+screenHeight+",scrollbars,resizable,screeny=top,toolbar=NO,location=no,directories=no,status=yes,scrollbars=yes,titlebar=no,addressbar=no,left=0,top=0,target=_blank");
    	mywind.focus();
    }
    
    function fun_openIssueTrackingSystemStatus(targ) 
    {   
	   
    	var mywind=window.open("<%=request.getContextPath()%>/openIsuueTrackingStatushome.do", targ, "width="+screenWidth+", height="+screenHeight+",scrollbars,resizable,screeny=top,toolbar=NO,location=no,directories=no,status=yes,scrollbars=yes,titlebar=no,addressbar=no,left=0,top=0,target=_blank");
    	mywind.focus();
    }

</script>

<div class="main_container">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="row responsive" style="background-image:url(<%=request.getContextPath()%>/images/head_bg.png); background-repeat:repeat-x;padding-right:0px!important;padding-left:0px!important;">
                        <div class="col-md-2" style="float:left;"><img src="<%=request.getContextPath()%>/images/logo.png" class="img-responsive" /></div>
                        <div class="col-md-6"><img src="<%=request.getContextPath()%>/images/heading_title.png" class="img-responsive" /></div>
                        <div class="col-md-2" style="float:right;"><img src="<%=request.getContextPath()%>/images/sero_logo.png" class="img-responsive" style="float:right;" /></div>
                    </div>
                <!--Begin Navigation-->
                
                   
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav" >
                            <li class="active"><a href="<%=request.getContextPath()%>/sadaremdefault.do">Home</a></li><%-- 
                            <li class=""> <a href="<%=request.getContextPath()%>/analysisReportMain.do">Analysis</a></li>
         --%>                    <li class=""> <a href="<%=request.getContextPath()%>/aboutHome.do">About Us</a></li>
                            <li class=""> <a href="<%=request.getContextPath()%>/contactUs.do?mode=getContactDetails">Contact Us</a></li>
                            <li class=""> <a href="<%=request.getContextPath()%>/blog.do">Blog</a></li>
                            <li class=""> <a href="<%=request.getContextPath()%>/feedback.do?getFeedback=getFeedback">Feedback</a></li>
                            <li class=""> <a href="<%=request.getContextPath()%>/newgallery.do">Gallery</a></li>    
                            <!-- <li class=""><a href="http://www.rdcallcentre.telangana.gov.in/insertSadaremRegistrationDetails.do" target="_blank">SADAREM Grievances</a></li>-->
                            <%-- <li class=""><a href="<%=request.getContextPath()%>/campDetailsDateWiseReport.do">Camp Details</a></li> --%>
                            <li class=""><a href="#" onclick="fun_loadQuickSearch('searchsadarem')"><b>Quick Search</b></a></li>
                            <li class=""><a href="#" onclick="fun_openIssueTrackingSystem('OpenIssueTracking')" style="color:red!important;"><b>SADAREM Grievances</b></a></li>
<!--                                    <li><a href="#" onclick="fun_openIssueTrackingSystemStatus('OpenIssueTrackingStatus')" style="color:red!important;"><b>SADAREM Grievances Status</b></a></li>-->
                     
                            <li class=""> <a href="#" onclick="createTarget('t')">Login</a></li>
                            <li><a href="http://www.serp.telangana.gov.in/" target="_blank">SERP</a></li>
                        </ul>

                    </div>
                    <!--/.nav-collapse -->

                <!--End Navigation-->
</div>


