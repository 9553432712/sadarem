<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<html>
    <head>
        <script type="text/javascript" src="./DisabilityUITG/js/tabber.js"></script>
        <link rel="stylesheet" href="./DisabilityUITG/css/tabber.css" TYPE="text/css" MEDIA="screen">

        <script type="text/javascript">
            document.write('<style type="text/css">.tabber{display:none;}<\/style>');
        </script>
        <script type="text/javascript">
            function goBack()
            {

                document.forms[0].action="MentalRetardationForwdActionPrint.do";
                document.forms[0].submit();
            }
        </script>
        <script type="text/javascript">
            function goReset()
            {
                document.getElementById("3MSelectAll").checked="";
                document.getElementById("vsms_0to1_CoolsLaughs").checked="";
                document.getElementById("vsms_0to1_Balenceshead").checked="";
                document.getElementById("vsms_0to1_Graphsobject").checked="";
                document.getElementById("vsms_0to1_Reachesforfamiliarpersons").checked="";
                document.getElementById("vsms_0to1_Rolls").checked="";
                document.getElementById("vsms_0to1_Reachesforobjects").checked="";
                document.getElementById("vsms_0to1_Occupies").checked="";
                document.getElementById("vsms_0to1_Sits").checked="";
                document.getElementById("vsms_0to1_Pulls").checked="";
                document.getElementById("vsms_0to1_Talks").checked="";
                document.getElementById("vsms_0to1_Drinks").checked="";
                document.getElementById("vsms_0to1_Moves").checked="";
                document.getElementById("vsms_0to1_Grasps").checked="";
                document.getElementById("vsms_0to1_Demands").checked="";
                document.getElementById("vsms_0to1_Stands").checked="";
                document.getElementById("vsms_0to1_Doesnotdrool").checked="";
                document.getElementById("vsms_0to1_Follows").checked="";
                document.getElementById("1YSelectAll").checked="";
                document.getElementById("vsms_1to2_Walks").checked="";
                document.getElementById("vsms_1to2_Marks").checked="";
                document.getElementById("vsms_1to2_Masticates").checked="";
                document.getElementById("vsms_1to2_Pulls").checked="";
                document.getElementById("vsms_1to2_Transfers").checked="";
                document.getElementById("vsms_1to2_Overcomessimpleobstacles").checked="";
                document.getElementById("vsms_1to2_Fetches").checked="";
                document.getElementById("vsms_1to2_Drinks").checked="";
                document.getElementById("vsms_1to2_Walkswithoutsupport").checked="";
                document.getElementById("vsms_1to2_Plays").checked="";
                document.getElementById("vsms_1to2_Eats").checked="";
                document.getElementById("vsms_1to2_Goes").checked="";
                document.getElementById("vsms_1to2_Discriminates").checked="";
                document.getElementById("vsms_1to2_Usesnames").checked="";
                document.getElementById("vsms_1to2_Walksupstairs").checked="";
                document.getElementById("vsms_1to2_Unwarps").checked="";
                document.getElementById("vsms_1to2_Talks").checked="";
                document.getElementById("2YSelectAll").checked="";
                document.getElementById("vsms_2to3_Signals").checked="";
                document.getElementById("vsms_2to3_Initiates").checked="";
                document.getElementById("vsms_2to3_Removesshirt").checked="";
                document.getElementById("vsms_2to3_Eatswithspoon").checked="";
                document.getElementById("vsms_2to3_Getsdrink").checked="";
                document.getElementById("vsms_2to3_Dries").checked="";
                document.getElementById("vsms_2to3_Avoids").checked="";
                document.getElementById("vsms_2to3_Putsonshirt").checked="";
                document.getElementById("vsms_2to3_Candopaperfolding").checked="";
                document.getElementById("vsms_2to3_Relates").checked="";
                document.getElementById("3YSelectAll").checked="";
                document.getElementById("vsms_3to4_Walksdownsstairs").checked="";
                document.getElementById("vsms_3to4_Playscooperatively").checked="";
                document.getElementById("vsms_3to4_Buttons").checked="";
                document.getElementById("vsms_3to4_Helps").checked="";
                document.getElementById("vsms_3to4_Performs").checked="";
                document.getElementById("vsms_3to4_Washes").checked="";
                document.getElementById("4YSelectAll").checked="";
                document.getElementById("vsms_4to5_Cares").checked="";
                document.getElementById("vsms_4to5_Prints").checked="";
                document.getElementById("vsms_4to5_Goesaboutneighbourhood").checked="";
                document.getElementById("vsms_4to5_Dresses").checked="";
                document.getElementById("vsms_4to5_Usespencil").checked="";
                document.getElementById("vsms_4to5_Playscompetitive").checked="";
                document.getElementById("5YSelectAll").checked="";
                document.getElementById("vsms_5to6_Useshoops").checked="";
                document.getElementById("vsms_5to6_Printssimplewords").checked="";
                document.getElementById("vsms_5to6_Playssimplegames").checked="";
                document.getElementById("vsms_5to6_trusted").checked="";
                document.getElementById("vsms_5to6_Goestoschool").checked="";
                document.getElementById("6YSelectAll").checked="";
                document.getElementById("vsms_6to7_Mixes").checked="";
                document.getElementById("vsms_6to7_Usespencilorchalk").checked="";
                document.getElementById("vsms_6to7_Batches").checked="";
                document.getElementById("vsms_6to7_Goestobed").checked="";
                document.getElementById("7YSelectAll").checked="";
                document.getElementById("vsms_7to8_Candifferentiatebetween").checked="";
                document.getElementById("vsms_7to8_Helps").checked="";
                document.getElementById("vsms_7to8_Understands").checked="";
                document.getElementById("vsms_7to8_Participates").checked="";
                document.getElementById("vsms_7to8_Combs").checked="";
                document.getElementById("8YSelectAll").checked="";
                document.getElementById("vsms_8to9_Usestools").checked="";
                document.getElementById("vsms_8to9_routinehouseholdtasks").checked="";
                document.getElementById("vsms_8to9_Readsonowninitiative").checked="";
                document.getElementById("vsms_8to9_Batchesselfunaided").checked="";
                document.getElementById("9YSelectAll").checked="";
                document.getElementById("vsms_9to10_Caresforself").checked="";
                document.getElementById("vsms_9to10_Makesminorpurchases").checked="";
                document.getElementById("vsms_9to10_Goesabouthome").checked="";
                document.getElementById("10YSelectAll").checked="";
                document.getElementById("vsms_10to11_Distinguishes").checked="";
                document.getElementById("vsms_10to11_Makesindependentchoice").checked="";
                document.getElementById("vsms_10to11_smallremunerativework").checked="";
                document.getElementById("vsms_10to11_Follows").checked="";
                document.getElementById("11YSelectAll").checked="";
                document.getElementById("vsms_11to12_simplecreative").checked="";
                document.getElementById("vsms_11to12_lefttocare").checked="";
                document.getElementById("vsms_11to12_Enjoys").checked="";
                document.getElementById("12YSelectAll").checked="";
                document.getElementById("vsms_12to15_Playsdifficult").checked="";
                document.getElementById("vsms_12to15_Exercisescomplete").checked="";
                document.getElementById("vsms_12to15_Buys").checked="";
                document.getElementById("vsms_12to15_Engages").checked="";
                document.getElementById("vsms_12to15_Performs").checked="";


            }







            function enable(i,j)
            {
                var x=i;
                for(x=i;x<=j;x++)
                    document.getElementById(x).checked=true;
            }
            function disable(i,j)
            {
                var x=i;
                for(x=i;x<=j;x++)
                    document.getElementById(x).checked=false;
            }

            function enableOrDisable3MonthsFields()
            {

                if( document.getElementById("3MSelectAll").checked==true)
                    enable(1,17);
                else
                    disable(1,17);
            }

            function enableOrDisable1to2yearsFields()
            {

                if( document.getElementById("1YSelectAll").checked==true)
                    enable(18,34);
                else
                    disable(18,34);
            }
            function enableOrDisable2to3yearsFields()
            {

                if( document.getElementById("2YSelectAll").checked==true)
                    enable(35,44);
                else
                    disable(35,44);
            }
            function enableOrDisable3to4yearsFields()
            {

                if( document.getElementById("3YSelectAll").checked==true)
                    enable(45,50);
                else
                    disable(45,50);
            }
            function enableOrDisable4to5yearsFields()
            {

                if( document.getElementById("4YSelectAll").checked==true)
                    enable(51,56);
                else
                    disable(51,56);
            }
            function enableOrDisable5to6yearsFields()
            {

                if( document.getElementById("5YSelectAll").checked==true)
                    enable(57,61);
                else
                    disable(57,61);
            }
            function enableOrDisable6to7yearsFields()
            {

                if( document.getElementById("6YSelectAll").checked==true)
                    enable(62,65);
                else
                    disable(62,65);
            }
            function enableOrDisable7to8yearsFields()
            {

                if( document.getElementById("7YSelectAll").checked==true)
                    enable(66,70);
                else
                    disable(66,70);
            }
            function enableOrDisable8to9yearsFields()
            {

                if( document.getElementById("8YSelectAll").checked==true)
                    enable(71,74);
                else
                    disable(71,74);
            }
            function enableOrDisable10to11yearsFields()
            {

                if( document.getElementById("9YSelectAll").checked==true)
                    enable(75,77);
                else
                    disable(75,77);
            }
            function enableOrDisable11to12yearsFields()
            {

                if( document.getElementById("10YSelectAll").checked==true)
                    enable(78,81);
                else
                    disable(78,81);
            }
            function enableOrDisable12to15yearsFields()
            {

                if( document.getElementById("11YSelectAll").checked==true)
                    enable(82,84);
                else
                    disable(82,84);
            }
            function enableOrDisable15yearsFields()
            {

                if( document.getElementById("12YSelectAll").checked==true)
                    enable(85,89);
                else
                    disable(85,89);
            }




        </script>


        <script type="text/javascript">
            function checkSelectAllBasedonOnload()
            {

                subfunctionforSelectAll(1,17,"3MSelectAll");
                subfunctionforSelectAll(18,34,"1YSelectAll");
                subfunctionforSelectAll(35,44,"2YSelectAll");
                subfunctionforSelectAll(45,50,"3YSelectAll");
                subfunctionforSelectAll(51,56,"4YSelectAll");
                subfunctionforSelectAll(57,61,"5YSelectAll");
                subfunctionforSelectAll(62,65,"6YSelectAll");
                subfunctionforSelectAll(66,70,"7YSelectAll");
                subfunctionforSelectAll(71,74,"8YSelectAll");

                subfunctionforSelectAll(75,77,"9YSelectAll");
                subfunctionforSelectAll(78,81,"10YSelectAll");
                subfunctionforSelectAll(82,84,"11YSelectAll");
                subfunctionforSelectAll(85,89,"12YSelectAll");
            }
            function subfunctionforSelectAll(min,max,name)
            {
                var count=0;
                var conditionalValue=(max-min+1);
                var loopvariable=0;

                for(loopvariable=min;loopvariable<=max;loopvariable++)
                    if(document.getElementById(loopvariable).checked)
                        count++;

                if(count==conditionalValue)
                    document.getElementById(name).checked=true;
                else
                    document.getElementById(name).checked=false;
            }


            function avoidDuplicateForm(thisform){
                if (thisform.getAttribute('submitted'))
                    return false;
                thisform.setAttribute('submitted','true');
            }
        </script>
        <script>
            function disableForm(theform) {
                if (document.all || document.getElementById) {
                    for (i = 0; i < theform.length; i++) {
                        var formElement = theform.elements[i];
                        if (true) {
                            formElement.disabled = true;
                        }
                    }
                }
            }
        </script>
        <style type="text/css">

            /* GRID Starts */

            .gridbg1 {
                background-color:#f4f4f4; text-align:left;  border-bottom:1px #000 solid;  border-left:1px #000 solid; vertical-align:middle; font-size:11px;  font-weight:400; height:20px; padding-left:5px; padding-right:5px; padding:4px;
            }
            .gridbg2 {
                background-color:#eae9e9; text-align:center;  border-bottom:1px #b0b0b0 solid;  border-left:1px #b0b0b0 solid; vertical-align:middle; font-size:10px;  font-weight:400; height:20px; padding-left:5px; padding-right:5px;
            }
            .gridhdbg {
                background-color:#b1b0b0; text-align:center;  border-bottom:1px #000 solid;  border-left:1px #000 solid; vertical-align:middle; font-size:12px;  font-weight:bold; height:20px;
            }
            .gridtb {
                border-right:1px #000 solid; border-top:1px #000 solid;
            }

            /* GRID Ends */
        </style>
    </head>
    <body onLoad="disableForm(vsms11);">


        <html:form action="/vsmsscreeningtestselectPrint.do" styleId="vsms11">

        
        <table align="center" cellspacing="0" cellpadding="0" width="45%">
            <tr>
                <td align="center"><font color="blue"><b>SADAREM ID : <%=session.getAttribute("personcode")%></b></font></td>
            </tr>
        </table><br/>
        <table  align="center" cellspacing="0" cellpadding="8" class="innerTable" width="85%">
            <tr>
                <td align="center" class="heading">VINELAND SOCIAL MATURITY SCALE RECORD SHEET</td>
            </tr></table>
        <div class="tabber">
            <div class="tabbertab">
                <h2>(0-3)YEARS</h2>
                <table border="0" class="tbl_bg2" width="100%" cellspacing="4">
                    <tr class="tbl_bg2_content">
                        <td width="50%" valign="top">
                            <table border=0 class="gridtb" cellpadding="0" cellspacing="0" width="100%">
                                <tr class="tbl_bg2_content">
                                    <td class="gridhdbg">&nbsp;</td>
                                    <td class="gridhdbg">&nbsp;</td>
                                    <td class="gridhdbg">Old Values</td>
                                    <td class="gridhdbg">New Values</td>
                                    <td class="gridhdbg">Months</td>
                                    <td class="gridhdbg">Days</td>

                                </tr>
                                <tr class="tbl_bg2_content">
                                    <td class="gridhdbg">&nbsp;</td>
                                    <td class="gridhdbg"><b>0-1 YEAR<b></td>
                                                <td class="gridhdbg">Select All <input type="checkbox" id="3MSelectAll" value="true" onClick="enableOrDisable3MonthsFields()" disabled="true"/></td>
                                                <td class="gridhdbg">&nbsp;</td>
                                                <td class="gridhdbg">&nbsp;</td>
                                                <td class="gridhdbg">&nbsp;</td>
                                                </tr>
                                                <tr class="tbl_bg2_content">
                                                    <td class="gridbg1">1.&nbsp;</td>
                                                    <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Coos Laughs</font></td>
                                                    <td align="left" class="gridbg1"> <html:checkbox styleId="1" property="vsms_0to1_CoolsLaughs"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                    <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                    <td class="gridbg1">&nbsp;</td>
                                                    <td class="gridbg1">0.7 </td>
                                                </tr>
                                                <tr class="tbl_bg2_content">
                                                    <td class="gridbg1">2.&nbsp;</td>
                                                    <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Balences head</font></td>
                                                    <td align="left" class="gridbg1"> <html:checkbox  styleId="2" property="vsms_0to1_Balenceshead"  value="1"  onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                    <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                    <td class="gridbg1">&nbsp;</td>
                                                    <td class="gridbg1">1.4</td>
                                                </tr>
                                                <tr class="tbl_bg2_content">
                                                    <td class="gridbg1">3.&nbsp;</td>
                                                    <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Graphs object within<br>&nbsp;&nbsp;&nbsp;&nbsp;reach</font></td>
                                                    <td align="left" class="gridbg1"> <html:checkbox styleId="3" property="vsms_0to1_Graphsobject"  value="1"  onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                    <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                    <td class="gridbg1">&nbsp;</td>
                                                    <td class="gridbg1">2.1</td>
                                                </tr>
                                                <tr class="tbl_bg2_content">
                                                    <td class="gridbg1">4.&nbsp;</td>
                                                    <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Reaches for familiar <br>&nbsp;&nbsp;&nbsp;&nbsp;persons</font></td>
                                                    <td align="left" class="gridbg1"> <html:checkbox styleId="4" property="vsms_0to1_Reachesforfamiliarpersons"  value="1"  onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                    <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                    <td class="gridbg1">&nbsp;</td>
                                                    <td class="gridbg1">2.8</td>
                                                </tr>
                                                <tr class="tbl_bg2_content">
                                                    <td class="gridbg1">5.&nbsp;</td>
                                                    <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Rolls over(unassisted)</font></td>
                                                    <td align="left" class="gridbg1"> <html:checkbox styleId="5" property="vsms_0to1_Rolls"  value="1"  onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                    <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                    <td class="gridbg1">&nbsp;</td>
                                                    <td class="gridbg1">3.5</td>
                                                </tr>
                                                <tr class="tbl_bg2_content">
                                                    <td class="gridbg1">6.&nbsp;</td>
                                                    <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Reaches for neraby <br>&nbsp;&nbsp;&nbsp;&nbsp;objects</font></td>
                                                    <td align="left" class="gridbg1"> <html:checkbox styleId="6" property="vsms_0to1_Reachesforobjects"  value="1"  onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                    <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                    <td class="gridbg1">&nbsp;</td>
                                                    <td class="gridbg1">4.2</td>
                                                </tr>
                                                <tr class="tbl_bg2_content">
                                                    <td class="gridbg1">7.&nbsp;</td>
                                                    <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Occupies self unattened</font></td>
                                                    <td align="left" class="gridbg1"> <html:checkbox styleId="7" property="vsms_0to1_Occupies"  value="1"  onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                    <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                    <td class="gridbg1">&nbsp;</td>
                                                    <td class="gridbg1">4.9</td>
                                                </tr>

                                                <tr class="tbl_bg2_content">
                                                    <td class="gridbg1">8.&nbsp;</td>
                                                    <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Sits unsupported</font></td>
                                                    <td align="left" class="gridbg1"> <html:checkbox styleId="8" property="vsms_0to1_Sits"  value="1"  onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                    <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                    <td class="gridbg1">&nbsp;</td>
                                                    <td class="gridbg1">5.6</td>
                                                </tr>
                                                <tr class="tbl_bg2_content">
                                                    <td class="gridbg1">9.&nbsp;</td>
                                                    <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Pulls self upright</font></td>
                                                    <td align="left" class="gridbg1"> <html:checkbox  styleId="9" property="vsms_0to1_Pulls"  value="1"   onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                    <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                    <td class="gridbg1">&nbsp;</td>
                                                    <td class="gridbg1">6.3</td>
                                                </tr>
                                                <tr class="tbl_bg2_content">
                                                    <td class="gridbg1">10.&nbsp;</td>
                                                    <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Talks imitates sounds</font></td>
                                                    <td align="left" class="gridbg1"> <html:checkbox styleId="10" property="vsms_0to1_Talks"  value="1"  onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                    <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                    <td class="gridbg1">&nbsp;</td>
                                                    <td class="gridbg1">7.0</td>
                                                </tr>
                                                <tr class="tbl_bg2_content">
                                                    <td class="gridbg1">11.&nbsp;</td>
                                                    <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Drinks from cup or glass &nbsp;&nbsp;&nbsp;&nbsp;assisted</font></td>
                                                    <td align="left" class="gridbg1"> <html:checkbox  styleId="11" property="vsms_0to1_Drinks"  value="1"  onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                    <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                    <td class="gridbg1">&nbsp;</td>
                                                    <td class="gridbg1">7.7</td>
                                                </tr>
                                                <tr class="tbl_bg2_content">
                                                    <td class="gridbg1">12.&nbsp;</td>
                                                    <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Moves about on floor<br>&nbsp;&nbsp;&nbsp;&nbsp;(Creeping,crawling)</font></td>
                                                    <td align="left" class="gridbg1"> <html:checkbox styleId="12" property="vsms_0to1_Moves"  value="1"  onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                    <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                    <td class="gridbg1">&nbsp;</td>
                                                    <td class="gridbg1">8.4</td>
                                                </tr>

                                                <tr class="tbl_bg2_content">
                                                    <td class="gridbg1">13.&nbsp;</td>
                                                    <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Grasps with thumb and &nbsp;&nbsp;&nbsp;&nbsp;finger</font></td>
                                                    <td align="left" class="gridbg1"> <html:checkbox styleId="13" property="vsms_0to1_Grasps"  value="1"  onclick="checkSelectAllBasedonOnload()"  disabled="true"/></td>
                                                    <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                    <td class="gridbg1">&nbsp;</td>
                                                    <td class="gridbg1">9.1</td>
                                                </tr>
                                                <tr class="tbl_bg2_content">
                                                    <td class="gridbg1">14.&nbsp;</td>
                                                    <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Demands personal<br>&nbsp;&nbsp;&nbsp;&nbsp;attention</font></td>
                                                    <td align="left" class="gridbg1"> <html:checkbox styleId="14" property="vsms_0to1_Demands"  value="1"  onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                    <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                    <td class="gridbg1">&nbsp;</td>
                                                    <td class="gridbg1">9.8</td>
                                                </tr>
                                                <tr class="tbl_bg2_content">
                                                    <td class="gridbg1">15.&nbsp;</td>
                                                    <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Stands alone</font></td>
                                                    <td align="left" class="gridbg1"> <html:checkbox styleId="15" property="vsms_0to1_Stands"  value="1"  onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                    <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                    <td class="gridbg1">&nbsp;</td>
                                                    <td class="gridbg1">10.6</td>
                                                </tr>
                                                <tr class="tbl_bg2_content">
                                                    <td class="gridbg1">16.&nbsp;</td>
                                                    <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Does not drool</font></td>
                                                    <td align="left" class="gridbg1"> <html:checkbox styleId="16" property="vsms_0to1_Doesnotdrool"  value="1"  onclick="checkSelectAllBasedonOnload()" disabled="true" /></td>
                                                    <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                    <td class="gridbg1">&nbsp;</td>
                                                    <td class="gridbg1">11.3</td>
                                                </tr>
                                                <tr class="tbl_bg2_content">
                                                    <td class="gridbg1">17.&nbsp;</td>
                                                    <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Follows simple<br>&nbsp;&nbsp;&nbsp;&nbsp;instructions</font></td>
                                                    <td align="left" class="gridbg1"> <html:checkbox styleId="17" property="vsms_0to1_Follows"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                    <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                    <td class="gridbg1">&nbsp;</td>
                                                    <td class="gridbg1">12.0</td>
                                                </tr>


                                                </table>
                                                </td>
                                                <td valign="top">
                                                    <table border=0 class="gridtb" cellpadding="0" cellspacing="0" width="100%">
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridhdbg">&nbsp;</td>
                                                            <td class="gridhdbg">&nbsp;</td>
                                                            <td class="gridhdbg">Old Values</td>
                                                            <td class="gridhdbg">New Values</td>
                                                            <td class="gridhdbg">Months</td>
                                                            <td class="gridhdbg">Days</td>

                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridhdbg">&nbsp;</td>
                                                            <td class="gridhdbg"><b>1-2 YEARS</b></td>
                                                            <td class="gridhdbg">Select All
                                                                <input type="checkbox" id="1YSelectAll" value="true" onClick="enableOrDisable1to2yearsFields()" disabled="true"/>
                                                            </td>
                                                            <td class="gridhdbg">&nbsp;</td>
                                                            <td class="gridhdbg">&nbsp;</td>
                                                            <td class="gridhdbg">&nbsp;</td>
                                                        </tr>

                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">18.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Walks about room unattened</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="18" property="vsms_1to2_Walks"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                            <td class="gridbg1">1</td>
                                                            <td class="gridbg1">0.7</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">19.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Marks with pencil or crayon or chalk </font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="19" property="vsms_1to2_Marks"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                            <td class="gridbg1">1</td>
                                                            <td class="gridbg1">1.4</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">20.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Masticates (chews) solid or semi-solid food</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="20" property="vsms_1to2_Masticates"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                            <td class="gridbg1">1</td>
                                                            <td class="gridbg1">2.1</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">21.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Pulls off clothes</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="21" property="vsms_1to2_Pulls"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                            <td class="gridbg1">1</td>
                                                            <td class="gridbg1">2.8</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">22.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Transfers objects</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="22" property="vsms_1to2_Transfers"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                            <td class="gridbg1">1</td>
                                                            <td class="gridbg1">3.5</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">23.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Overcomes simple obstacles</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="23" property="vsms_1to2_Overcomessimpleobstacles"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                            <td class="gridbg1">1</td>
                                                            <td class="gridbg1">4.2</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">24.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Fetches or carries familiar objects</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="24" property="vsms_1to2_Fetches"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td><td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                            <td class="gridbg1">1</td>
                                                            <td class="gridbg1">4.9</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">25.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp; Drinks from cup or glass unassisted</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="25" property="vsms_1to2_Drinks"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                            <td class="gridbg1">1</td>
                                                            <td class="gridbg1">5.6</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">26.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Walks without support</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="26" property="vsms_1to2_Walkswithoutsupport"  value="1"  onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                            <td class="gridbg1">1</td>
                                                            <td class="gridbg1">6.3</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">27.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp; Plays with other children</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="27" property="vsms_1to2_Plays"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                            <td class="gridbg1">1</td>
                                                            <td class="gridbg1">7.0</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">28.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Eats with own hands(biscuits,bread etc)</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="28"property="vsms_1to2_Eats"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                            <td class="gridbg1">1</td>
                                                            <td class="gridbg1">7.7</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">29.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Goes about house or yard</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="29"property="vsms_1to2_Goes"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                            <td class="gridbg1">1</td>
                                                            <td class="gridbg1">8.4</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">30.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Discriminates edible substances from non-edibles</font></td>

                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="30" property="vsms_1to2_Discriminates"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                            <td class="gridbg1">1</td>
                                                            <td class="gridbg1">9.2</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">31.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Uses names of familiar objects</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="31" property="vsms_1to2_Usesnames"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                            <td class="gridbg1">1</td>
                                                            <td class="gridbg1">9.8</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">32.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Walks upstairs unassisted</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="32" property="vsms_1to2_Walksupstairs"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                            <td class="gridbg1">1</td>
                                                            <td class="gridbg1">10.6</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">33.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Unwraps sweets,chocolates</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="33" property="vsms_1to2_Unwarps"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                            <td class="gridbg1">1</td>
                                                            <td class="gridbg1">11.3</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">34.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Talks in short sentences</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="34" property="vsms_1to2_Talks"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                            <td class="gridbg1">1</td>
                                                            <td class="gridbg1">12.0</td>
                                                        </tr>

                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg2">&nbsp;</td>
                                                            <td class="gridbg2"><b>2-3 YEARS</b></td>
                                                            <td class="gridbg2">Select All
                                                                <input type="checkbox" id="2YSelectAll" value="true" onClick="enableOrDisable2to3yearsFields()" disabled="true"/>
                                                            </td>
                                                            <td class="gridbg2">&nbsp;</td>
                                                            <td class="gridbg2">&nbsp;</td>
                                                            <td class="gridbg2">&nbsp;</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">35.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Signals to go to toilet</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="35" property="vsms_2to3_Signals"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                            <td class="gridbg1">2</td>
                                                            <td class="gridbg1">1.2</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">36.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Initiates own play<br>&nbsp;&nbsp;&nbsp;&nbsp;activities</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="36" property="vsms_2to3_Initiates"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                            <td class="gridbg1">2</td>
                                                            <td class="gridbg1">2.4</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">37.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Removes shirt or frock if<br>&nbsp;&nbsp;&nbsp;&nbsp;unbuttoned</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="37" property="vsms_2to3_Removesshirt"  value="1"  onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                            <td class="gridbg1">2</td>
                                                            <td class="gridbg1">3.6</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">38.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Eats with spoon/hands<br>&nbsp;&nbsp;&nbsp;&nbsp;(food)</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="38" property="vsms_2to3_Eatswithspoon"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                            <td class="gridbg1">2</td>
                                                            <td class="gridbg1">4.8</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">39.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Gets drink(water)<br>&nbsp;&nbsp;&nbsp;&nbsp;unassisted</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="39" property="vsms_2to3_Getsdrink"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                            <td class="gridbg1">2</td>
                                                            <td class="gridbg1">6.0</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">40.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Dries own hands</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="40" property="vsms_2to3_Dries"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                            <td class="gridbg1">2</td>
                                                            <td class="gridbg1">7.2</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">41.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Avoids simple hazards</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="41" property="vsms_2to3_Avoids"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                            <td class="gridbg1">2</td>
                                                            <td class="gridbg1">8.4</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">42.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Puts on shirt or frock<br>&nbsp;&nbsp;&nbsp;&nbsp;unassisted</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="42" property="vsms_2to3_Putsonshirt"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                            <td class="gridbg1">2</td>
                                                            <td class="gridbg1">9.6</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">43.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Can do paper folding</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="43" property="vsms_2to3_Candopaperfolding"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                            <td class="gridbg1">2</td>
                                                            <td class="gridbg1">10.8</td>
                                                        </tr>
                                                        <tr class="tbl_bg2_content">
                                                            <td class="gridbg1">44.&nbsp;</td>
                                                            <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Relates experiences</font></td>
                                                            <td align="left" class="gridbg1"> <html:checkbox styleId="44" property="vsms_2to3_Relates"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                            <td class="gridbg1">2</td>
                                                            <td class="gridbg1">12.0</td>
                                                        </tr>



                                                    </table>
                                                </td>
                                                </tr>
                                                </table>
                                                </div>
                                                <div class="tabbertab">
                                                    <h2>(4-6)YEARS</h2>
                                                    <table border="0" class="tbl_bg2" width="100%" cellspacing="4">
                                                        <tr class="tbl_bg2_content">
                                                            <td valign="top" width="50%">
                                                                <table border=0 class="gridtb" cellpadding="0" cellspacing="0" width="100%">
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg">Old Values</td>
                                                                        <td class="gridhdbg">New Values</td>
                                                                        <td class="gridhdbg">Months</td>
                                                                        <td class="gridhdbg">Days</td>

                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg"><b>3-4 YEARS</b></td>
                                                                        <td class="gridhdbg">Select All <input type="checkbox" id="3YSelectAll" value="true" onClick="enableOrDisable3to4yearsFields()" disabled="true"/></td>
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">45.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Walks downsstairs one step <br>&nbsp;&nbsp;&nbsp;&nbsp;at a time</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="45" property="vsms_3to4_Walksdownsstairs"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">3</td>
                                                                        <td class="gridbg1">2</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">46.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Plays cooperatively at<br>&nbsp;&nbsp;&nbsp;&nbsp;kindergarten level</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="46" property="vsms_3to4_Playscooperatively"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">3</td>
                                                                        <td class="gridbg1">4</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">47.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Buttons shirt or frock</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="47" property="vsms_3to4_Buttons"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">3</td>
                                                                        <td class="gridbg1">6</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">48.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Helps at little household<br>&nbsp;&nbsp;&nbsp;&nbsp;tasks</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="48" property="vsms_3to4_Helps"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">3</td>
                                                                        <td class="gridbg1">8</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">49.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Performs for others</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="49" property="vsms_3to4_Performs"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">3</td>
                                                                        <td class="gridbg1">10</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">50.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Washes hands unaided</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="50" property="vsms_3to4_Washes"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                                        <td class="gridbg1">3</td>
                                                                        <td class="gridbg1">12</td>
                                                                    </tr>

                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg2">&nbsp;</td>
                                                                        <td class="gridbg2"><b>4-5 YEARS</b></td>
                                                                        <td class="gridbg2">Select All
                                                                            <input type="checkbox" id="4YSelectAll" value="true" onClick="enableOrDisable4to5yearsFields()" disabled="true"/>
                                                                        </td>
                                                                        <td class="gridbg2">&nbsp;</td>
                                                                        <td class="gridbg2">&nbsp;</td>
                                                                        <td class="gridbg2">&nbsp;</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">51.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Cares for self at toilet</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="51" property="vsms_4to5_Cares"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                                        <td class="gridbg1">4</td>
                                                                        <td class="gridbg1">2</td>
                                                                    </tr>

                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">52.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Washes face unassisted<br></font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="52" property="vsms_4to5_Prints"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>


                                                                        <td class="gridbg1">4</td>
                                                                        <td class="gridbg1">4</td>
                                                                    </tr>

                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">53.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Goes about<br>&nbsp;&nbsp;&nbsp;&nbsp;neighbourhood<br>&nbsp;&nbsp;&nbsp;&nbsp;unattened</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="53" property="vsms_4to5_Goesaboutneighbourhood"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>


                                                                        <td class="gridbg1">4</td>
                                                                        <td class="gridbg1">6</td>
                                                                    </tr>

                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">54.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Dresses self except for<br>&nbsp;&nbsp;&nbsp;&nbsp;tying</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="54" property="vsms_4to5_Dresses"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                                        <td class="gridbg1">4</td>
                                                                        <td class="gridbg1">8</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">55.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Uses pencil or crayon <br>&nbsp;&nbsp;&nbsp;&nbsp;or chalk for<br>&nbsp;&nbsp;&nbsp;&nbsp;drawing</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="55" property="vsms_4to5_Usespencil"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                                        <td class="gridbg1">4</td>
                                                                        <td class="gridbg1">10</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">56.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Plays competitive <br>&nbsp;&nbsp;&nbsp;&nbsp;exercise games</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="56" property="vsms_4to5_Playscompetitive"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                                        <td class="gridbg1">4</td>
                                                                        <td class="gridbg1">12</td>
                                                                    </tr>



                                                                </table>
                                                            </td>
                                                            <td valign="top" width="50%">
                                                                <table border=0 class="gridtb" cellpadding="0" cellspacing="0" width="100%">
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg">Old Values</td>
                                                                        <td class="gridhdbg">New Values</td>
                                                                        <td class="gridhdbg">Months</td>
                                                                        <td class="gridhdbg">Days</td>

                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg"><b>5-6 YEARS</b></td>
                                                                        <td class="gridhdbg">Select All <input type="checkbox" id="5YSelectAll" value="true" onClick="enableOrDisable5to6yearsFields()" disabled="true"/></td>
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">57</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Uses hoops,files,kites or uses knife</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="57" property="vsms_5to6_Useshoops"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">5</td>
                                                                        <td class="gridbg1">2.4</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">58</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Prints(writes) simple words</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="58" property="vsms_5to6_Printssimplewords"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">5</td>
                                                                        <td class="gridbg1">4.8</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">59.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Plays simple games which require talking turns</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="59" property="vsms_5to6_Playssimplegames"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">5</td>
                                                                        <td class="gridbg1">7.2</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">60.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Is trusted with money</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="60" property="vsms_5to6_trusted"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">5</td>
                                                                        <td class="gridbg1">9.6</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">61.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;&nbsp;&nbsp;Goes to school unattened</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="61" property="vsms_5to6_Goestoschool"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">5</td>
                                                                        <td class="gridbg1">12.0</td>
                                                                    </tr>


                                                                </table>

                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <div class="tabbertab">
                                                    <h2>(7-9)YEARS</h2>
                                                    <table border="0" class="tbl_bg2" width="100%" cellspacing="4">
                                                        <tr class="tbl_bg2_content">
                                                            <td width="50%" valign="top">
                                                                <table border=0 class="gridtb" cellpadding="0" cellspacing="0" width="100%">
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg">Old Values</td>
                                                                        <td class="gridhdbg">New Values</td>
                                                                        <td class="gridhdbg">Months</td>
                                                                        <td class="gridhdbg">Days</td>

                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg"><b>6-7 YEARS</b></td>
                                                                        <td class="gridhdbg">Select All <input type="checkbox" id="6YSelectAll" value="true" onClick="enableOrDisable6to7yearsFields()" disabled="true"/></td>
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">62.</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Mixes rice properly unassisted</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="62" property="vsms_6to7_Mixes"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">6</td>
                                                                        <td class="gridbg1">3</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">63.</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Uses pencil or chalk for writing</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="63" property="vsms_6to7_Usespencilorchalk"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                                        <td class="gridbg1">6</td>
                                                                        <td class="gridbg1">6</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">64.</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Bathes self assisted</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="64" property="vsms_6to7_Batches"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">6</td>
                                                                        <td class="gridbg1">9</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">65.</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Goes to bed unassisted</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="65" property="vsms_6to7_Goestobed"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">6</td>
                                                                        <td class="gridbg1">12</td>
                                                                    </tr>

                                                                    <tr class="tbl_bg2_content"><td colspan="6"><hr></td></tr>

                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">&nbsp;</td>
                                                                        <td class="gridbg2"><b>7-8 YEARS</b></td>
                                                                        <td class="gridbg2">Select All
                                                                            <input type="checkbox" id="7YSelectAll" value="true" onClick="enableOrDisable7to8yearsFields()" disabled="true"/>
                                                                        </td>
                                                                        <td class="gridbg2">&nbsp;</td>
                                                                        <td class="gridbg2">&nbsp;</td>
                                                                        <td class="gridbg2">&nbsp;</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td>66.</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Can differentiate between AM & PM</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="66" property="vsms_7to8_Candifferentiatebetween"  value="1"  onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">7</td>
                                                                        <td class="gridbg1">2.4</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td>67.</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Helps himself during meals</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="67" property="vsms_7to8_Helps"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">7</td>
                                                                        <td class="gridbg1">4.8</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td>68.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Understands and keeps family secrets</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="68" property="vsms_7to8_Understands"  value="1" onclick="checkSelectAllBasedonOnload()"  disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">7</td>
                                                                        <td class="gridbg1">7.2</td>
                                                                    </tr>

                                                                    <tr class="tbl_bg2_content">
                                                                        <td>69.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Participates in pre-adolescent play</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="69" property="vsms_7to8_Participates"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">7</td>
                                                                        <td class="gridbg1">9.6</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td>70.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Combs or brushes hair</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="70" property="vsms_7to8_Combs"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">7</td>
                                                                        <td class="gridbg1">12.0</td>
                                                                    </tr>


                                                                </table>
                                                            </td>
                                                            <td width="50%" valign="top">
                                                                <table border=0 class="gridtb" cellpadding="0" cellspacing="0" width="100%">
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg">Old Values</td>
                                                                        <td class="gridhdbg">New Values</td>
                                                                        <td class="gridhdbg">Months</td>
                                                                        <td class="gridhdbg">Days</td>

                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg"><b>8-9 YEARS</b></td>
                                                                        <td class="gridhdbg">Select All <input type="checkbox" id="8YSelectAll" value="true" onClick="enableOrDisable8to9yearsFields()" disabled="true"/></td>
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">71.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Uses tools or utensils</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="71" property="vsms_8to9_Usestools"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">8</td>
                                                                        <td class="gridbg1">3</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">72.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Does routine household tasks</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="72" property="vsms_8to9_routinehouseholdtasks"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">8</td>
                                                                        <td class="gridbg1">6</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">73.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Reads on own initiative</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="73" property="vsms_8to9_Readsonowninitiative"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                                        <td class="gridbg1">8</td>
                                                                        <td class="gridbg1">9</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">74.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Bathes self unaided</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="74" property="vsms_8to9_Batchesselfunaided"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">8</td>
                                                                        <td class="gridbg1">12</td>
                                                                    </tr>



                                                                </table>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <div class="tabbertab">
                                                    <h2>(10-15)YEARS </h2>
                                                    <table border="0" class="tbl_bg2" width="100%" cellspacing="4">
                                                        <tr class="tbl_bg2_content">
                                                            <td width="50%" valign="top">
                                                                <table border=0 class="gridtb" cellpadding="0" cellspacing="0" width="100%">
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg">Old Values</td>
                                                                        <td class="gridhdbg">New Values</td>
                                                                        <td class="gridhdbg">Months</td>
                                                                        <td class="gridhdbg">Days</td>

                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg"><b>9-10 YEARS</b></td>
                                                                        <td class="gridhdbg">Select All <input type="checkbox" id="9YSelectAll" value="true" onClick="enableOrDisable10to11yearsFields()" disabled="true"/></td>
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                        <td class="gridhdbg">&nbsp;</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">75.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Cares for self at meals</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="75" property="vsms_9to10_Caresforself"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">9</td>
                                                                        <td class="gridbg1">4</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">76.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Makes minor purchases</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="76" property="vsms_9to10_Makesminorpurchases"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">9</td>
                                                                        <td class="gridbg1">8</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg1">77.&nbsp;</td>
                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Goes about home town freely</font></td>
                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="77" property="vsms_9to10_Goesabouthome"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                        <td class="gridbg1">9</td>
                                                                        <td class="gridbg1">12</td>
                                                                    </tr>
                                                                    <tr class="tbl_bg2_content"><td colspan="6"><hr></td></tr>

                                                                    <tr class="tbl_bg2_content">
                                                                        <td class="gridbg2">&nbsp;</td>
                                                                        <td class="gridbg2"><b>10-11 YEARS<b></td>
                                                                                    <td class="gridbg2">Select All
                                                                                        <input type="checkbox" id="10YSelectAll" value="true" onClick="enableOrDisable11to12yearsFields()" disabled="true"/>
                                                                                    </td>
                                                                                    <td class="gridbg2">&nbsp;</td>
                                                                                    <td class="gridbg2">&nbsp;</td>
                                                                                    <td class="gridbg2">&nbsp;</td>
                                                                                    </tr>
                                                                                    <tr class="tbl_bg2_content">
                                                                                        <td class="gridbg1">78.&nbsp;</td>
                                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Distinguishes between friends and play mates</font></td>
                                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="78" property="vsms_10to11_Distinguishes"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                                                        <td class="gridbg1">10</td>
                                                                                        <td class="gridbg1">3</td>
                                                                                    </tr>

                                                                                    <tr class="tbl_bg2_content">
                                                                                        <td class="gridbg1">79.&nbsp;</td>
                                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Makes independent choice of shops</font></td>
                                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="79" property="vsms_10to11_Makesindependentchoice"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                                                        <td class="gridbg1">10</td>
                                                                                        <td class="gridbg1">6</td>
                                                                                    </tr>

                                                                                    <tr class="tbl_bg2_content">
                                                                                        <td class="gridbg1">80.&nbsp;</td>
                                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Does small remunerative work:makes articles</font></td>
                                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="80" property="vsms_10to11_smallremunerativework"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>


                                                                                        <td class="gridbg1">10</td>
                                                                                        <td class="gridbg1">9</td>
                                                                                    </tr>

                                                                                    <tr class="tbl_bg2_content">
                                                                                        <td class="gridbg1">81.&nbsp;</td>
                                                                                        <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Follows local current events</font></td>
                                                                                        <td align="left" class="gridbg1"> <html:checkbox styleId="81" property="vsms_10to11_Follows"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                                        <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                                        <td class="gridbg1">10</td>
                                                                                        <td class="gridbg1">12</td>
                                                                                    </tr>




                                                                                    </table>
                                                                                    </td>
                                                                                    <td valign="top" width="50%">
                                                                                        <table border=0 class="gridtb" cellpadding="0" cellspacing="0" width="100%">
                                                                                            <tr class="tbl_bg2_content">
                                                                                                <td class="gridhdbg">&nbsp;</td>
                                                                                                <td class="gridhdbg">&nbsp;</td>
                                                                                                <td class="gridhdbg">Old Values</td>
                                                                                                <td class="gridhdbg">New Values</td>
                                                                                                <td class="gridhdbg">Months</td>
                                                                                                <td class="gridhdbg">Days</td>

                                                                                            </tr>
                                                                                            <tr class="tbl_bg2_content">
                                                                                                <td class="gridhdbg">&nbsp;</td>
                                                                                                <td class="gridhdbg"><b>11-12 YEARS<b></td>
                                                                                                            <td class="gridhdbg">Select All <input type="checkbox" id="11YSelectAll" value="true" onClick="enableOrDisable12to15yearsFields()" disabled="true"/></td>
                                                                                                            <td class="gridhdbg">&nbsp;</td>
                                                                                                            <td class="gridhdbg">&nbsp;</td>
                                                                                                            <td class="gridhdbg">&nbsp;</td>
                                                                                                            </tr>
                                                                                                            <tr class="tbl_bg2_content">
                                                                                                                <td class="gridbg1">82.&nbsp;</td>
                                                                                                                <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Does simple creative work</font></td>
                                                                                                                <td align="left" class="gridbg1"> <html:checkbox styleId="82" property="vsms_11to12_simplecreative"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                                                                <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                                                                <td class="gridbg1">11</td>
                                                                                                                <td class="gridbg1">4</td>
                                                                                                            </tr>
                                                                                                            <tr class="tbl_bg2_content">
                                                                                                                <td class="gridbg1">83.&nbsp;</td>
                                                                                                                <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Is left to care for self or others</font></td>
                                                                                                                <td align="left" class="gridbg1"> <html:checkbox styleId="83" property="vsms_11to12_lefttocare"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                                                                <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                                                                <td class="gridbg1">11</td>
                                                                                                                <td class="gridbg1">8</td>
                                                                                                            </tr>
                                                                                                            <tr class="tbl_bg2_content">
                                                                                                                <td class="gridbg1">84.&nbsp;</td>
                                                                                                                <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Enjoys reading,books,newspapers,magazines</font></td>
                                                                                                                <td align="left" class="gridbg1"> <html:checkbox styleId="84" property="vsms_11to12_Enjoys"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                                                                <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                                                                <td class="gridbg1">11</td>
                                                                                                                <td class="gridbg1">12</td>
                                                                                                            </tr>


                                                                                                            <tr class="tbl_bg2_content"><td colspan="6"><hr></td></tr>

                                                                                                            <tr class="tbl_bg2_content">
                                                                                                                <td class="gridbg2">&nbsp;</td>
                                                                                                                <td class="gridbg2"><b>12-15 YEARS</b></td>
                                                                                                                <td class="gridbg2">Select All
                                                                                                                    <input type="checkbox" id="12YSelectAll" value="true" onClick="enableOrDisable15yearsFields()" disabled="true"/>
                                                                                                                </td>
                                                                                                                <td class="gridbg2">&nbsp;</td>
                                                                                                                <td class="gridbg2">&nbsp;</td>
                                                                                                                <td class="gridbg2">&nbsp;</td>
                                                                                                            </tr>
                                                                                                            <tr class="tbl_bg2_content">
                                                                                                                <td class="gridbg1">85.&nbsp;</td>
                                                                                                                <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Plays difficult games</font></td>
                                                                                                                <td align="left" class="gridbg1"> <html:checkbox styleId="85" property="vsms_12to15_Playsdifficult"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                                                                <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                                                                <td class="gridbg1">12</td>
                                                                                                                <td class="gridbg1">7.2</td>
                                                                                                            </tr>

                                                                                                            <tr class="tbl_bg2_content">
                                                                                                                <td class="gridbg1">86.&nbsp;</td>
                                                                                                                <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Exercises complete care of dress</font></td>
                                                                                                                <td align="left" class="gridbg1"> <html:checkbox styleId="86" property="vsms_12to15_Exercisescomplete"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                                                                <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                                                                                <td class="gridbg1">12</td>
                                                                                                                <td class="gridbg1">14.4</td>
                                                                                                            </tr>

                                                                                                            <tr class="tbl_bg2_content">
                                                                                                                <td class="gridbg1">87.&nbsp;</td>
                                                                                                                <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Buys own clothing accessories</font></td>
                                                                                                                <td align="left" class="gridbg1"> <html:checkbox styleId="87" property="vsms_12to15_Buys"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                                                                <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                                                                                <td class="gridbg1">12</td>
                                                                                                                <td class="gridbg1">21.6</td>
                                                                                                            </tr>

                                                                                                            <tr class="tbl_bg2_content">
                                                                                                                <td class="gridbg1">88.&nbsp;</td>
                                                                                                                <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Engages in adolescent group activities</font></td>
                                                                                                                <td align="left" class="gridbg1"> <html:checkbox styleId="88" property="vsms_12to15_Engages"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                                                                <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                                                                                <td class="gridbg1">12</td>
                                                                                                                <td class="gridbg1">28.8</td>
                                                                                                            </tr>
                                                                                                            <tr class="tbl_bg2_content">
                                                                                                                <td class="gridbg1">89.&nbsp;</td>
                                                                                                                <td align="left" class="gridbg1"> <font size="1">&nbsp;&nbsp;Performs responsible routine chores</font></td>
                                                                                                                <td align="left" class="gridbg1"> <html:checkbox styleId="89" property="vsms_12to15_Performs"  value="1" onclick="checkSelectAllBasedonOnload()" disabled="true"/></td>
                                                                                                                <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>

                                                                                                                <td class="gridbg1">12</td>
                                                                                                                <td class="gridbg1">36.0</td>
                                                                                                            </tr>



                                                                                                            </table>
                                                                                                            </td>
                                                                                                            </tr>
                                                                                                            </table>
                                                                                                            </div>
                                                                                                            </div><br>
                                                                                                        </html:form>
                                                                                                        <form action="">
                                                                                                            <table align="center">

                                                                                                                <tr >
                                                                                                                    <td><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/></td>
                                                                                                                    <td><html:button property="" value="Print" onclick="window.print()" styleClass="button"/></td>
                                                                                                                </tr>
                                                                                                            </table>
                                                                                                        </form>
                                                                                                        </body>
                                                                                                        <script>

                                                                                                            function goURL()
                                                                                                            {
                                                                                                                document.forms[0].action="partcPrint.do?selectPartc=selectPartc";
                                                                                                                document.forms[0].submit();
                                                                                                            }

                                                                                                        </script>
                                                                                                        </html>