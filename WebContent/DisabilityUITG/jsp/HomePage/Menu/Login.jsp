<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page  import="java.util.ArrayList"%>
<%

            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
            int i = 1;

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <LINK REL="stylesheet" href="./DisabilityUITG/css/sadarem_styles.css">
        <script language="javascript" src="./DisabilityUITG/js/passwordEncryption.js"></script>
        <script language="javascript" src="./DisabilityUITG/js/modalpopup.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <title>SADAREM</title>

        <style type="text/css">
            body{padding:0px; margin:0px; font-family:Georgia, "Times New Roman", Times, serif; font-weight:bold; font-size:16px; font-style:italic;}

        </style>
        <script>
            function checkLogin() {
                document.forms[0].elements["encrptPwd"].value = enctypemd5(document.forms[0].elements["password"].value);
            }
            function isNumberKey(evt)
            {
                var charCode = (evt.which) ? evt.which : event.keyCode
                if(charCode == 13) {
                    document.forms[0].action= "./search.do?SearchPage=SearchPage&personcode="+document.forms[0].elements["personcode"].value;
                    document.forms[0].submit();
                }else if (charCode > 31 && (charCode < 48 || charCode > 57) ) {
                    return false;
                }
                return true;
            }
           

            function photo()
            {
                window.location = "photos.do";
            }
            function awards()
            {
                window.location = "Awards.do";
            }
            function enctypemd5(pwd){
                var encpwd=calcMD5(pwd);
                document.forms[0].encrptPwd.value=encpwd;
                //document.forms[0].submit();
                return encpwd;
            }
            function close_window() {

                document.close();
            }
            function openRDCALLCenter()
            {
                var windowObjectReference;
                var strWindowFeatures = "menubar=no,location=no,resizable=no,scrollbars=yes,status=yes,target=_blank";
                windowObjectReference = window.open("http://rdcallcentre.telangana.gov.in/insertSadaremRegistrationDetails.do", "_blank", strWindowFeatures);

            }
            function openSearch()
            {
                var windowObjectReference;
                var strWindowFeatures = "menubar=no,location=no,resizable=yes,scrollbars=yes,status=yes,target=_blank";
                windowObjectReference = window.open("searchForPension.do", "_blank", strWindowFeatures);

            }
            function openFunctionalNeeds()
            {
                var windowObjectReference;
                var strWindowFeatures = "menubar=no,location=no,resizable=no,scrollbars=yes,status=yes,target=_blank";
                windowObjectReference = window.open("http://sadarem.telangana.gov.in/esaps/", "_blank", strWindowFeatures);

            }
            function openIDPDPMS()
            {
                var windowObjectReference;
                var strWindowFeatures = "menubar=no,location=no,resizable=no,scrollbars=yes,status=yes,target=_blank";
                windowObjectReference = window.open("http://ikppwd.telangana.gov.in/", "_blank", strWindowFeatures);

            }
            function openSERP()
            {
                var windowObjectReference;
                var strWindowFeatures = "menubar=no,location=no,resizable=no,scrollbars=yes,status=yes,target=_blank";
                windowObjectReference = window.open("http://serp.telangana.gov.in/", "_blank", strWindowFeatures);

            }
            function openindia()
            {
                var windowObjectReference;
                var strWindowFeatures = "menubar=no,location=no,resizable=no,scrollbars=yes,status=yes,target=_blank";
                windowObjectReference = window.open("http://india.gov.in/", "_blank", strWindowFeatures);

            }
            function openRequestedPopup()
            {
                var windowObjectReference;
                var strWindowFeatures = "menubar=no,location=no,resizable=no,scrollbars=yes,status=yes,target=_parent";
                windowObjectReference = window.open("login.do", "_blank", strWindowFeatures);

                this.window.close();

            }
        </script>
        <script language="javascript" type="text/javascript">
            function popUp(){
                ShowModalPopup('dvPopup');
            }
        </script>
        <script language="javascript" type="text/javascript">

            function sendURL()
            {
                var personCode = document.getElementById("personcode").value;
                  document.getElementById('sadaremprocessing').style.display='';
                window.location = "search.do?SearchPage=SearchPage&personcode="+personCode;


            }

            function rationCardURL()
            {    var rationcard = document.getElementById("rationcardnumber").value;
                if(rationcard.length>0){
                    if(rationcard.length < 15) {
                        alert("Please Enter Valid RationCard No");
                        document.forms[0].elements["rationcardnumber"].focus();
                        document.forms[0].elements["rationcardnumber"].value="";
                        return false;
                    }else if(rationcard.substring(0,3)!="WAP" && rationcard.substring(0,3)!="PAP" && rationcard.substring(0,3)!="AAY" &&
                        rationcard.substring(0,3)!="AAP" && rationcard.substring(0,3)!="YAP" && rationcard.substring(0,3)!="wap" &&
                        rationcard.substring(0,3)!="pap" && rationcard.substring(0,3)!="aay" &&  rationcard.substring(0,3)!="aap" &&
                        rationcard.substring(0,3)!="yap" && rationcard.substring(0,3)!="RAP" && rationcard.substring(0,3)!="rap"
                        && rationcard.substring(0,3)!="TAP" && rationcard.substring(0,3)!="tap"
                        && rationcard.substring(0,3)!="WAD" && rationcard.substring(0,3)!="wad") {
                        alert("Please Enter Valid RationCard Number");
                        document.forms[0].elements["rationcardnumber"].focus();
                        document.forms[0].elements["rationcardnumber"].value="";
                        return false;
                    }
                }
                document.getElementById('processing').style.display='';
                     window.location = "search.do?SearchPage=searchRationCardDetaails&rationcard="+rationcard+"&type="+2;

                 }


                 function searchaadhracardsendURL()
                 {
                     var aadhracard = document.getElementById("aadhracardnumber").value;
                     if(aadhracard.length<12){
                         alert("Please Enter Valid Aadhar card number")
                         document.forms[0].elements["aadhracardnumber"].focus();
                         document.forms[0].elements["aadhracardnumber"].value="";
                         return false;
                     }
                 document.getElementById('aadharCardprocessing').style.display='';
                     window.location = "search.do?SearchPage=searchRationCardDetaails&aadhracard="+aadhracard+"&type="+4;

                 }


                 function changeSelectionID()
                 {

                     window.location = "search.do?SearchPage=pensioncardDetails&type="+3;


                 }


                 function changeSelection(id){

                     if(id=="1"){
                         document.getElementById('one').style.display='';
                         document.getElementById('two').style.display='none';
                         document.getElementById('three').style.display='none';
                         document.getElementById('four').style.display='none';
                         document.forms[0].elements['rationcardnumber'].value="";
                         document.forms[0].elements['pensionid'].value="";
                         document.forms[0].elements['aadhracardno'].value="";
                         document.forms[0].elements['two'].value="";
                         document.forms[0].elements['four'].value="";

                     }else if(id=="2"){
                         document.getElementById('one').style.display='none';
                         document.getElementById('two').style.display='';
                         document.getElementById('three').style.display='none';
                         document.getElementById('four').style.display='none';
                         document.forms[0].elements['personcode'].value="";
                         document.forms[0].elements['pensionid'].value="";
                         document.forms[0].elements['aadhracardno'].value="";
                         document.forms[0].elements['pensionid'].value="";
                         document.forms[0].elements['aadhracardno'].value="";
                         document.forms[0].elements['two'].value="";
                         // document.forms[0].elements['four'].value="";
                     }else if(id=="3"){
                         document.getElementById('one').style.display='none';
                         document.getElementById('two').style.display='none';
                         document.getElementById('three').style.display='';
                         document.getElementById('four').style.display='none';
                         document.forms[0].elements['personcode'].value="";
                         document.forms[0].elements['rationcardnumber'].value="";
                         //document.forms[0].elements['pensionid'].value="";
                         document.forms[0].elements['aadhracardno'].value="";
                     }else if(id=="4"){
                         document.getElementById('one').style.display='none';
                         document.getElementById('two').style.display='none';
                         document.getElementById('three').style.display='none';
                         document.getElementById('four').style.display='';
                         document.forms[0].elements['personcode'].value="";
                         document.forms[0].elements['rationcardnumber'].value="";
                         document.forms[0].elements['pensionid'].value="";
                         // document.forms[0].elements['aadhracardno'].value="";
                     }else{
                         //document.getElementById('one').style.display='none';
                         document.getElementById('two').style.display='none';
                         document.getElementById('three').style.display='none';
                         document.getElementById('four').style.display='none';
                         document.forms[0].elements['personcode'].value="";
                         document.forms[0].elements['rationcardnumber'].value="";
                         document.forms[0].elements['pensionid'].value="";
                         document.forms[0].elements['aadhracardno'].value="";
                         document.getElementById('one').checked=true;
                     }




                 }

                 var type="";

            <%
                        if (request.getAttribute("type") != null) {%>
                            type='<%=request.getAttribute("type")%>';
            <%}%>


                function bodyLoadfunction(){
                    if(type=='2'){
                        document.getElementById('one').style.display="none";
                        document.getElementById('two').style.display="block";
                        document.getElementById('four').style.display="none";
                        document.forms[0].elements['pensionid'].value="";
                        document.forms[0].elements['aadhracardno'].value="";
                        document.forms[0].elements['rationcardnumber'].value="";
                        document.forms[0].elements['personcode'].value="";
                        type="2";

                    } else if(type=='4'){
                        document.getElementById('one').style.display="none";
                        document.getElementById('two').style.display="none";
                        document.getElementById('four').style.display="block";
                        document.forms[0].elements['pensionid'].value="";
                        document.forms[0].elements['aadhracardno'].value="";
                        document.forms[0].elements['rationcardnumber'].value="";
                        document.forms[0].elements['personcode'].value="";

                        type="4";
                    }
                    else if(type=='1'){
                        document.getElementById('one').style.display="block";
                        document.getElementById('two').style.display="none";
                        document.getElementById('four').style.display="none";
                        document.forms[0].elements['pensionid'].value="";
                        document.forms[0].elements['aadhracardno'].value="";
                        document.forms[0].elements['rationcardnumber'].value="";
                        document.forms[0].elements['personcode'].value="";
                        type="1";
                    }

                }





        </script>
    </head>
    <logic:present name="popup">
        <body onload="bodyLoadfunction(),popUp();">
        </logic:present>
        <logic:notPresent name="popup">
        <body onload="bodyLoadfunction();">
        </logic:notPresent>


        <form action="login.do">
            <input type="hidden" name="logintime"/>
            <input type="hidden" name="firstvalue" id="text1"/>
            <input type="hidden" name="mode"/>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
            	<tr>
	            	<td style="background-color:#6b2525;">
	            		<%@ include file="/DisabilityUITG/Scripts/Header.jsp" %>
	            	</td>
            	</tr>
                <tr>
                    <td style="background-image:url(./DisabilityUITG/images/Midbg.png); background-repeat:repeat-x">
                        <table width="980px" border="0" cellspacing="3" cellpadding="3" align="center">
                            
                            <tr>
                                <td style="color:#fff; background-color:#591a1a;text-align:left; line-height:30px;width:200px; font-size:14px;font-weight:normal;">
                                    
                                    <ul>
                                       
                                        <li><a href='analysisReportMain.do' class='a' style="color: white"> Analysis</a></li>
                                        <li ><a href='Gos.do' class='a' style="color: white"> GOs & ACTs</a></li>
                                        <li> <a href='impLink.do?mode=getReportDetails' style="color: white">RelatedLinks</a></li>
                                        <li><a href='docs.do' class='a' style="color: white">Documents</a></li>
                                        <li><a href='cmpView.do' class='a' style="color: white"> Circulars/Proceedings</a></li>
                                        <!--   <li> <a href='photos.do' class='a' style="color: white">Photo Gallery</a></li>
                                        <li> <a href='newgallery.do' class='a' style="color: white">Photo Gallery</a></li> -->
                                    </ul>
                                </td>

                                <td height="253px" valign="top" align="center"><img src="./DisabilityUITG/images/banner.gif" /></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="background-image:url(./DisabilityUITG/images/updates.png); background-repeat:repeat-x; color:#FFFFFF" height="60px">
                        <table width="950px" border="0" cellspacing="0" cellpadding="0" align="center" >
                            <tr>
                                <td style="color: white" width="200px">Whats New on Portal :</td>
                     <td style="color:#fff; font-size:12px;border:1px solid #5d2120;font-weight: bold" >
                <MARQUEE onmouseover="this.scrollAmount='0'" onmouseout="this.scrollAmount='3'" scrollAmount="3" scrollDelay="2" direction="left">
                                Welcome to New SADAREM Portal of Telangana
               
               </MARQUEE>
                    </td>
                
                
                
            </table>
        </td>
    </tr>
    <tr>
        <td bgcolor="#FFFFFF">
            <table width="980px" border="0" cellspacing="2" cellpadding="2" align="center" >
                <tr>
                    <td valign="top">
                        <table width="220px" border="0" cellspacing="0" cellpadding="0" align="left">
                            <tr>
                                <td align="left"><img src="./DisabilityUITG/images/News.png" /></td>
                            </tr>
                            <tr>
                                <td align="left" style="color:#000;padding-left:20px;  line-height:30px;width:200px; font-size:12px;font-weight:normal;">
                            <marquee onmouseover="this.scrollAmount='0'" onmouseout="this.scrollAmount='2'" scrollamount="2"
                                     scrolldelay="2" direction="up" height="200">
                                <logic:present name="news">
                                    <logic:iterate id="row" name="news">

                                        <a href="newsTitles.do?mode=getNewsDetails&id=${row.id}" class="scrooltext">${row.title}</a><br/>
                                        <br/>
                                    </logic:iterate>
                                </logic:present>

                            </marquee>
                    </td>
                </tr>
            </table>
        </td>
        <td align="center" valign="top" bgcolor="">
            <a href="./campDetailsDateWiseReport.do">

                <img src="./DisabilityUITG/images/TG_Map.png"  height="220" /></a></td>
        <td align="center" valign="top">
            <table border="0" cellspacing="3" cellpadding="3" align="center">
               <tr>
                     <td><a href="javascript:openSearch()" ><img src="./DisabilityUITG/images/Search_1.png" width="277"/></a></td>
                </tr>
                <tr>
                    <td><a href='Awards.do'><img src="./DisabilityUITG/images/award.png" width="277"/></a></td>
                </tr>
                <tr>
                    <td><a href="javascript:openRDCALLCenter()" ><img src="./DisabilityUITG/images/pink.png" width="277"/></a></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</td>
</tr>
<tr>
    <td style="background-image:url(./DisabilityUITG/images/line.png); background-repeat:repeat-x"  height="1px" ></td>
</tr>
<tr>
    <td colspan="3" style=" background-color:#FFFFFF;color:#990000;font-size:16px;font-weight:bold; " valign="top" >
        <table width="900px" border="0" cellspacing="3" cellpadding="3" align="center" >
            <tr>
                <td align="center" > <a href="http://serp.telangana.gov.in/" target="_blank"><img src="./DisabilityUITG/images/serp_tg.png" /><br /></a></td>
                <td align="center" > <a href="http://sadarem.telangana.gov.in/esaps/"  target="_blank"><img src="./DisabilityUITG/images/FNP_TG.png"/><br /></a></td>
                <td align="center" ><a href="http://ikppwd.telangana.gov.in/"  target="_blank"><img src="./DisabilityUITG/images/IDPDPMS_TG.png"/><br /></a></td>
                <td align="center" ><a href="http://india.gov.in/"  target="_blank"><img src="./DisabilityUITG/images/IndiaPortal_TG.png" /><br /></a></td>

            </tr>
        </table>
    </td>
</tr>
<tr>
    <td align="center">
		<%@include file="/DisabilityUITG/Scripts/LoginFooter.jsp" %>
	</td>
</tr>
</table>
<div id="dvPopup" style="display:none;">
    <table cellpadding="0" cellspacing="0" border="0" align="center" height="450px" width="800px">
        <tr>
            <td style="background-image:url('./Festival/${file}'); background-repeat: no-repeat;" valign="top" align="right"
                height="600px" width="800px">
                <a href="javascript:void(0);" onclick ="HideModalPopup('dvPopup'); return false;">
                    <img src="./DisabilityUITG/images/close.jpg" border="0"/></a>
            </td>
        </tr>
    </table>
</div>

</form>
</body>
</html>