<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html>
<head>
<title>SADAREM</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

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
background-image:url(../images/titlebar-active.png);
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

</head>

<body>


<table width="99%" border="0" cellspacing="0" cellpadding="15" align="center" bgcolor="#e4f5fd">  
  <tr>
    <td align="center" valign="top" height="445"><table width="98%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="81%" align="center" valign="top"><table border="0" cellspacing="0" cellpadding="0" width="96%">
          <tr>
            <td><img src="./DisabilityUITG/images/srch-lft-top-img.png" width="11" height="28"></td>
            <td width="99%" background="./DisabilityUITG/images/srch-top-bg.png" style="font-size:11px; font-family:Verdana, Arial, Helvetica, sans-serif;"><strong>RELATED LINKS</strong></td>
            <td><img src="./DisabilityUITG/images/srch-rgt-top-img.png" width="11" height="28"></td>
          </tr>
          <tr>
            <td bgcolor="#f4f4f4" style="border-left:1px #7c7c7c solid;">&nbsp;</td>
            <td height="40" align="left" valign="middle" bgcolor="#f4f4f4"  style="border-right:1px #999999 solid;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="6%" align="center" valign="middle" class="formhdbg"><strong>Sl.No</strong></td>
                  <td width="21%" align="center" valign="middle" class="formhdbg"><strong>Logo</strong></td>
                  <td width="56%" align="center" valign="middle" class="formhdbg"><strong>Department</strong></td>
                  <td width="17%" align="center" valign="middle" class="formhdbg"><strong>Website Link</strong></td>
                </tr>
                <tr>
                  <td align="center" valign="middle" class="formbg">1</td>
                  <td align="center" valign="middle" class="formbg"><img src="./DisabilityUITG/Downloads/Logos/Serp-logo.gif" width="60" height="60"></td>
                  <td align="left" valign="middle" class="formbg">Soceity for Elimination of Rural Poverty</td>
                  <td align="center" valign="middle" class="formbg"><a target="_blank" href="http://www.serp.ap.gov.in">LINK</a></td>
                </tr>
                <tr>
                  <td align="center" valign="middle" class="formaltbg">2</td>
                  <td align="center" valign="middle" class="formaltbg"><img src="./DisabilityUITG/Downloads/Logos/wcdsc.ap.nic.in.jpg" width="60" height="60"></td>
                  <td align="left" valign="middle" class="formaltbg">Telangana Vikalangula Co-Operative Corporation</td>
                  <td align="center" valign="middle" class="formaltbg"><a target="_blank" href="http://wcdsc.ap.nic.in/apvcc/">LINK</a></td>
                </tr>
                <tr>
                  <td align="center" valign="middle" class="formaltbg">3</td>
                  <td align="center" valign="middle" class="formaltbg"><img src="./DisabilityUITG/Downloads/Logos/ssa.JPG" width="60" height="60"></td>
                  <td align="left" valign="middle" class="formaltbg">Sarva Shiksha Abhiyan Telangana</td>
                  <td align="center" valign="middle" class="formaltbg"><a target="_blank" href="http://ssa.ap.nic.in/IED1.html">LINK</a></td>
                </tr>
                <tr>
                  <td align="center" valign="middle" class="formaltbg">4</td>
                  <td align="center" valign="middle" class="formaltbg"><img src="./DisabilityUITG/Downloads/Logos/ccdisabilities.png" width="60" height="60"></td>
                  <td align="left" valign="middle" class="formaltbg">Ministry of Social justice and Empowerment</td>
                  <td align="center" valign="middle" class="formaltbg"><a target="_blank" href="http://www.socialjustice.nic.in">LINK</a></td>
                </tr>
                <tr>
                  <td align="center" valign="middle" class="formaltbg">5</td>
                  <td align="center" valign="middle" class="formaltbg"><img src="./DisabilityUITG/Downloads/Logos/ccdisabilities.png" width="60" height="60"></td>
                  <td align="left" valign="middle" class="formaltbg">Office of The Chief Commissioner for Persons with Disabilities</td>
                  <td align="center" valign="middle" class="formaltbg"><a target="_blank" href="http://www.ccdisabilities.nic.in">LINK</a></td>
                </tr>
                <tr>
                  <td align="center" valign="middle" class="formaltbg">6</td>
                  <td align="center" valign="middle" class="formaltbg"><img src="./DisabilityUITG/Downloads/Logos/nhfdc.jpg" width="60" height="60"></td>
                  <td align="left" valign="middle" class="formaltbg">Nationla Handicapped Finance and Development Corporation</td>
                  <td align="center" valign="middle" class="formaltbg"><a target="_blank" href="http://www.nhfdc.org">LINK</a></td>
                </tr>
                <tr>
                  <td align="center" valign="middle" class="formaltbg">7</td>
                  <td align="center" valign="middle" class="formaltbg"><img src="./DisabilityUITG/Downloads/Logos/nationaltrust.JPG" width="60" height="60"></td>
                  <td align="left" valign="middle" class="formaltbg">The National Trust</td>
                  <td align="center" valign="middle" class="formaltbg"><a target="_blank" href="http://www.thenationaltrust.in/">LINK</a></td>
                </tr>
                <tr>
                  <td align="center" valign="middle" class="formaltbg">8</td>
                  <td align="center" valign="middle" class="formaltbg"><img src="./DisabilityUITG/Downloads/Logos/rehabcouncil.jpg" width="60" height="60"></td>
                  <td align="left" valign="middle" class="formaltbg">Rehabilitation Council of India</td>
                  <td align="center" valign="middle" class="formaltbg"><a target="_blank" href="http://www.rehabcouncil.nic.in">LINK</a></td>
                </tr>
                <tr>
                  <td align="center" valign="middle" class="formaltbg">9</td>
                  <td align="center" valign="middle" class="formaltbg"><img src="./DisabilityUITG/Downloads/Logos/ayjnihh.gif" width="60" height="60"></td>
                  <td align="left" valign="middle" class="formaltbg">Ali Yavar Jung National Institute for the Hearing Handicapped</td>
                  <td align="center" valign="middle" class="formaltbg"><a target="_blank" href="http://ayjnihh.nic.in/index.asp">LINK</a></td>
                </tr>
                <tr>
                  <td align="center" valign="middle" class="formaltbg">10</td>
                  <td align="center" valign="middle" class="formaltbg"><img src="./DisabilityUITG/Downloads/Logos/nimhindia.org.jpg" width="60" height="60"></td>
                  <td align="left" valign="middle" class="formaltbg">National Institute for the Mentally Handicapped</td>
                  <td align="center" valign="middle" class="formaltbg"><a target="_blank" href="http://www.nimhindia.org/">LINK</a></td>
                </tr>
                <tr>
                  <td align="center" valign="middle" class="formaltbg">11</td>
                  <td align="center" valign="middle" class="formaltbg">&nbsp;</td>
                  <td align="left" valign="middle" class="formaltbg">National Institute of Rehabilitation Training and Research</td>
                  <td align="center" valign="middle" class="formaltbg"><a target="_blank" href="http://www.nirtar.nic.in">LINK</a></td>
                </tr>
                <tr>
                  <td align="center" valign="middle" class="formaltbg">12</td>
                  <td align="center" valign="middle" class="formaltbg"><img src="./DisabilityUITG/Downloads/Logos/NIVH_logo_1.png" width="60" height="60"></td>
                  <td align="left" valign="middle" class="formaltbg">National Institute for the Visually Handicapped</td>
                  <td align="center" valign="middle" class="formaltbg"><a target="_blank" href="http://www.nivh.in/">LINK</a></td>
                </tr>
                <tr>
                  <td align="center" valign="middle" class="formaltbg">13</td>
                  <td align="center" valign="middle" class="formaltbg"><img src="./DisabilityUITG/Downloads/Logos/iphnewdelhi.jpg" width="60" height="60"></td>
                  <td align="left" valign="middle" class="formaltbg">Pt. Deendayal Updadhyaya Institute for the Physically Handicapped</td>
                  <td align="center" valign="middle" class="formaltbg"><a target="_blank" href="http://www.iphnewdelhi.in/">LINK</a></td>
                </tr>
                <tr>
                  <td align="center" valign="middle" class="formaltbg">14</td>
                  <td align="center" valign="middle" class="formaltbg"><img src="./DisabilityUITG/Downloads/Logos/artlimbs.gif" width="60" height="60"></td>
                  <td align="left" valign="middle" class="formaltbg">Artificial Limbs Manufacturing Corporation of India</td>
                  <td align="center" valign="middle" class="formaltbg"><a target="_blank" href="http://www.artlimbs.com/">LINK</a></td>
                </tr>


            </table></td>
            <td bgcolor="#f4f4f4" style="border-right:1px #7c7c7c solid;">&nbsp;</td>
          </tr>
          <tr>
            <td height="11"><img src="./DisabilityUITG/images/srch-lft-btm-img.png" width="11" height="11"></td>
            <td background="./DisabilityUITG/images/srch-btm-bg.png"></td>
            <td><img src="./DisabilityUITG/images/srch-rgt-btm-img.png" width="11" height="11"></td>
          </tr>
        </table></td>
        <td width="19%" align="center" valign="top"><img src="./DisabilityUITG/images/Lnk-ico.gif"></td>
      </tr>
    </table></td>
  </tr>  
</table>
<script language="javascript" src="./DisabilityUITG/js/Mainfooter.js"></script>

</body>
</html>