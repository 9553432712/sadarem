<%-- 
    Document   : NotComeToSadaremCamp
    Created on : Jan 28, 2011, 5:43:06 PM
    Author     : 509865
--%>




<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,java.text.*"%>
<%@page session="true"%>


<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <link rel="stylesheet" href="./DisabilityUITG/css/Sadarem-Style.css" type="text/css">
        <link rel="stylesheet" href="./DisabilityUITG/css/jqueryslidemenu.css" type="text/css">

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

        <script >

            function createDistrictObject()
            {
                x=GetXmlHttpObject()
                x.onreadystatechange=getDistrictDetails;
                var url="getTerritory.do?parameter=getTerritoryList&territory=1";
                x.open("Get", url, true);
                x.send();
            }

            function getDistrictDetails()
            {
                var res1,res2;

                if(x.readyState==4 || x.readyState=="complete")
                {
                    var m=x.responseXML.documentElement;
                    var z=0;
                    var countss=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                    m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
                    z=1;
                    while(z<=countss)
                    {
                        res1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                        res2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
                        addoption(res1,res2,"district_id");
                        z++;
                    }
                }

            }



            function addoption(result1,result2,name)
            {
                var opt=document.createElement("OPTION");
                opt.text=result1;
                opt.value=result2;
                document.getElementById(name).add(opt);
            }


            function GetXmlHttpObject()
            {
                var objXmlHttp=null
                if(window.XMLHttpRequest)
                {
                    objXmlHttp=new XMLHttpRequest();
                }
                else if(window.ActiveXObject)
                {
                    objXmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
                }
                return objXmlHttp;
            }
            function validate_required(field,alerttxt)
            {

                with (field)
                {
                    if (value==null || value=="")
                    {
                        alert(alerttxt);
                        return true
                    }
                    else
                    {
                        return false
                    }
                }
            }

            function validate_form(thisform)
            {
                with (thisform)
                {
                    if (validate_required(district_id, "Select District") == true)
                    {
                        district_id.focus();
                        return false
                    }
                    if (validate_required(phase, "Select Phase") == true)
                    {
                        phase.focus();
                        return false
                    }
                }
                selectedNames();
            }

            function selectedNames()
            {
                var slcBx1 = document.getElementById("1");
                document.getElementById("districtName").value = slcBx1.options[slcBx1.selectedIndex].text;
            }




        </script>
    </head>

    <body onload="createDistrictObject()">

    
    <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>

    <html:form action="notComeToCamp.do?getNotComeToCamp=getNotComeToCamp&reportCategory=2" method="post" onsubmit="return validate_form(this)">

        <table width="99%" border="0" cellspacing="0" cellpadding="10" align="center">
            <tr>
                <input type="hidden" name="districtName"/>
                <td height="445" align="center" bgcolor="#e4f5fd" valign="top">
                    <table border="0" cellspacing="0" cellpadding="0" width="90%" align="center">
                        <tr>
                            <td><img src="./DisabilityUITG/images/srch-lft-top-img.png" width="11" height="28" /></td>
                            <td width="99%" background="./DisabilityUITG/images/srch-top-bg.png" class="label"><strong>Select Report for Only Part-A Entered Details</strong></td>
                            <td><img src="./DisabilityUITG/images/srch-rgt-top-img.png" width="11" height="28" /></td>
                        </tr>
                        <tr>
                            <td bgcolor="#f4f4f4" style="border-left:1px #7c7c7c solid;">&nbsp;</td>
                            <td height="40" align="left" valign="middle" bgcolor="#f4f4f4">

                                <table  align="center" cellspacing="1" cellpadding="5" width="80%">
                                    <tr>
                                        <td valign="middle" class="label" width="20">District<font color="red"><b>*</b></font></td>
                                        <td align="left" valign="middle">
                                            <html:select styleId="1" property="district_id" style="height:25px;">
                                                <html:option  value="">Select</html:option>
                                                <html:optionsCollection   property="districtlist" label="district_name" value="district_id"  />
                                            </html:select>
                                        </td>
                                        <td class="label" valign="middle" width="20">Phase<font color="red"><b>*</b></font></td>
                                        <td align="left" valign="middle">
                                            <html:select styleId="2" property="phase" style="height:25px;">
                                                <html:option value="">  --SELECT--   </html:option>
                                                <html:option value="1">Phase-I</html:option>
                                                <html:option value="2">Phase-II</html:option>
                                                <html:option value="3">Phase-III</html:option>
                                                <html:option value="4">Phase-IV</html:option>
                                                <html:option value="5">All</html:option>
                                            </html:select>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td colspan="4" align="center"><html:submit property="Submit" value="Submit" styleClass="button"/></td>
                                    </tr>
                                </table>
                                <%
                                            String msg = (String) request.getAttribute("msg");

                                %>
                                <% if (msg != null) {%><table align="center"><tr><td><%=msg%></td></tr></table> <% }%>
                            </td>
                            <td bgcolor="#f4f4f4" style="border-right:1px #7c7c7c solid;">&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="11"><img src="./DisabilityUITG/images/srch-lft-btm-img.png" width="11" height="11" /></td>
                            <td background="./DisabilityUITG/images/srch-btm-bg.png"></td>
                            <td><img src="./DisabilityUITG/images/srch-rgt-btm-img.png" width="11" height="11" /></td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>

    </html:form>
</body>
</html:html>
