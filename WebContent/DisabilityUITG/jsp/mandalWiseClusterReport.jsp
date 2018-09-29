<%--
    Document   : mandalWiseClusterReport
    Created on : Jan 15, 2013, 6:23:58 PM
    Author     : 695536
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <link rel="stylesheet" href="./DisabilityUITG/css/Sadarem-Style.css" type="text/css">
        <link rel="stylesheet" href="./DisabilityUITG/css/cssmaster.css" type="text/css">
        <link rel="stylesheet" href="./DisabilityUITG/css/jqueryslidemenu.css" type="text/css">
        <script language="javascript" src="./DisabilityUITG/js/Mainheader-1.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
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



        <script>
            function mandaldata(){

                document.forms[0].mode.value = "getMandalData";
                document.forms[0].submit();
            }
            function villageData(){
                document.forms[0].mode.value = "getvillageData";
                document.forms[0].submit();
            }
            function pwdData(){
                if(document.forms[0].elements['district'].value=="00"){
                    alert("Select any one District");
                    document.forms[0].elements['district'].focus();
                    return false;
                }else
                    document.forms[0].mode.value = "getpwdDData";
                document.forms[0].submit();
            }
        </script>
    </head>
    <body>

        <html:form action="mandalWiseClusterReport">
            <html:hidden property="mode"/>
            <table width="99%" border="0" cellspacing="0" cellpadding="10" align="center">
                <tr>
                    <td height="445" align="center" bgcolor="#e4f5fd" valign="top">
                        <input type="hidden" name="districtName"/>
                        <input type="hidden" name="mandalName"/>
                        <input type="hidden" name="villageName"/>

                        <br>
                        <table border="0" cellspacing="0" cellpadding="0" width="80%" align="center">
                            <tr>
                                <td colspan="3">
                                    <logic:present name="msg">
                                        <center><font color="red" size="2"><b><bean:write name="msg"/></b></font> </center>
                                    </logic:present>
                                </td>
                            </tr>
                            <tr>
                                <td><img src="./DisabilityUITG/images/srch-lft-top-img.png" width="11" height="28" /></td>
                                <td width="99%" background="./DisabilityUITG/images/srch-top-bg.png" class="label" align="center"><strong>Reports on Mobilisation of PWD</strong></td>
                                <td><img src="./DisabilityUITG/images/srch-rgt-top-img.png" width="11" height="28" /></td>
                            </tr>

                            <tr>
                                <td bgcolor="#f4f4f4" style="border-left:1px #7c7c7c solid;">&nbsp;</td>
                                <td height="40" align="left" valign="middle" bgcolor="#f4f4f4" width="100%">


                                    <table align="center" cellspacing="1" cellpadding="5" width="100%" border="0">
                                        <tr>
                                            <td class="label" valign="middle">District <font color="red">*</font></td>
                                            <td><html:select styleId="1" property="district" onchange="mandaldata();" style="height:25px;">
                                                    <html:option value="00">---Select---</html:option>
                                                    <html:optionsCollection property="districtList" label="districtname" value="district"/>
                                                </html:select>
                                            </td>


                                            <td class="label" valign="middle" width="8%">Mandal</td>
                                            <td><html:select styleId="2" property="mandal" onchange="villageData();" style="height:25px;">
                                                    <html:option value="00">---All---</html:option>
                                                    <html:optionsCollection property="mandalList" label="mandalname" value="mandal"/>
                                                </html:select>
                                            </td>

                                            <td class="label" valign="middle" width="8%">Village</td>
                                            <td><html:select styleId="3" property="village" style="height:25px;">
                                                    <html:option value="00">---All---</html:option>
                                                    <html:optionsCollection property="villageList" label="villagename" value="village"/>
                                                </html:select></td>
                                        </tr>
                                        <tr>
                                            <td colspan="6" align="center">
                                                <html:button property="sub" value="Submit" onclick="pwdData();"/>
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
                        <br><br>
                        <%int i = 1;%>
                        <%int j = 1;%>
                        <%int k = 1;%>

                        <logic:notEmpty name="PwdData">
                            <table align="center" border="0" cellpadding="0" cellspacing="0" width="90%">
                                <tr>
                                    <td align="left"><font color="#326fb2"><h4><br>SADAREM assets Data</h4></font></td>
                                    <td  align="right">
                                        <a href="mandalWiseClusterReportExcel.xls?mode=getassetsData&distId=${distId}&mandalId=${mandalId}">
                                            <img src="DisabilityUITG/images/excel-ico.jpg"
                                                 height="25" width="25" style="border:0px"/></a> </td></tr>
                            </table>
                            <table align="center"  cellpadding="0" cellspacing="0" width="90%" style="border:1px solid #ccc">

                                <tr>

                                    <td align="center" class="formhdbg">
                                        SNo.
                                    </td>
                                    <%--         <td class="formhdbg" align="center">District Name </td>--%>
                                    <td class="formhdbg" align="center">Mandal Name </td>
                                    <td class="formhdbg" align="center" > No.of Pwd's(0-55) under Sadarem </td>
                                    <td class="formhdbg" align="center" >No.of pwd's Mobilised </td>
                                    <td class="formhdbg" align="center">Mobilised % </td>

                                </tr>
                                <logic:iterate id="row" name="PwdData">
                                    <tr>
                                        <td class="formaltbg" align="center">
                                            <%=i++%>.
                                        </td>
                                        <td class="formaltbg" align="left">${row.assesedcount}</td>
                                        <td class="formaltbg" align="center">${row.mobilised}</td>
                                        <td class="formaltbg" align="center">${row.percentage}</td>
                                        <td class="formaltbg" align="center">${row.MandalName}</td>
                                        <%--<td class="formaltbg" align="center">${row.DistrictName}</td>--%>
                                    </tr>
                                </logic:iterate>
                            </table>
                            <br>

                        </logic:notEmpty>
                        <logic:notEmpty name="PwdData1">
                            <table align="center" border="0" cellpadding="0" cellspacing="0" width="90%">
                                <tr>
                                    <td align="left"><font color="#326fb2"><h4><br>Data as per IB Database</h4></font></td>
                                    <td  align="right">
                                        <a href="mandalWiseClusterReportExcel.xls?mode=getIBDatabase&distId=${distId}&mandalId=${mandalId}">
                                            <img src="DisabilityUITG/images/excel-ico.jpg"
                                                 height="25" width="25" style="border:0px"/></a></td></tr>
                            </table>
                            <table align="center" border="0" cellpadding="0" cellspacing="0" width="90%" style="border:1px solid #ccc">

                                <tr>

                                    <td align="center" class="formhdbg">
                                        SNo.
                                    </td>
                                    <%--     <td class="formhdbg" align="center">District Name </td>--%>
                                    <td class="formhdbg" align="center">Mandal Name </td>
                                    <td class="formhdbg" align="center" >No.of SHG's of Pwd formed
                                        as per PMI of PD </td>
                                    <td class="formhdbg" align="center" >No.of SHG's Pwd Registred
                                        with IB data base book keeping</td>
                                    <td class="formhdbg" align="center">Mobilised % </td>

                                </tr>
                                <logic:iterate id="row" name="PwdData1">
                                    <tr>
                                        <td class="formaltbg" align="center">
                                            <%=j++%>.
                                        </td>
                                        <td class="formaltbg" align="center">${row.assesedcount1}</td>
                                        <td class="formaltbg" align="center">${row.mobilised1}</td>
                                        <td class="formaltbg" align="center">${row.percentage1}</td>
                                        <td class="formaltbg" align="center">${row.MandalName1}</td>
                                        <%--<td class="formaltbg" align="center">${row.DistrictName1}</td>--%>
                                    </tr>
                                </logic:iterate>


                            </table>
                        </logic:notEmpty>
                        <br>

                        <logic:notEmpty name="PwdData2">
                            <table align="center" border="0" cellpadding="0" cellspacing="0" width="90%">
                                <tr>
                                    <td align="left"><font color="#326fb2"><h4><br>Data as per MIS Database</h4></font></td>
                                    <td  align="right">
                                        <a href="mandalWiseClusterReportExcel.xls?mode=getMISDatabase&distId=${distId}&mandalId=${mandalId}">
                                            <img src="DisabilityUITG/images/excel-ico.jpg"
                                                 height="25" width="25" style="border:0px"/></a> </td></tr>
                            </table>
                            <table align="center"  cellpadding="0" cellspacing="0" width="90%" style="border:1px solid #ccc">




                                <tr>

                                    <td align="center" class="formhdbg">
                                        SNo.
                                    </td>
                                    <%--      <td class="formhdbg" align="center">District Name </td>--%>
                                    <td class="formhdbg" align="center">Mandal Name </td>
                                    <td class="formhdbg" align="center" >No.of pwd's formed
                                        as per MIS of PD  </td>
                                    <td class="formhdbg" align="center" >No.of SHG's Linked to Bank
                                        at least one </td>
                                    <td class="formhdbg" align="center" >Amount Bank Linked </td>

                                </tr>
                                <logic:iterate id="row" name="PwdData2">
                                    <tr>
                                        <td class="formaltbg" align="center">
                                            <%=k++%>.
                                        </td>
                                        <td class="formaltbg" align="center">${row.assesedcount2}</td>
                                        <td class="formaltbg" align="center">${row.mobilised2}</td>
                                        <td class="formaltbg" align="center">${row.percentage2}</td>
                                        <td class="formaltbg" align="center">${row.MandalName2}</td>
                                        <%--<td class="formaltbg" align="center">${row.DistrictName2}</td>--%>
                                    </tr>
                                </logic:iterate>


                            </table>
                        </logic:notEmpty>
            </table>
        </html:form>

    </body>
</html>