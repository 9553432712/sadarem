<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<html>

<head >
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


</head>
<body class="body" >

<table  align="center" height="100%" class="table_one">
<tr>

<div style="overflow:scroll"><td width="80%"><tiles:insert attribute="body" ignore="true"/></td></div>
</tr>
<tr  valign="top" height="10%">
<td width="10%" colspan="2" valign="center"><tiles:insert attribute="footer" ignore="true" /></td>
</tr>
</table>
</body>

</html>